package com.viacom.downloads;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

//Author : Vinoth
public class VerifyEpisodeDownloadQualityTest extends BaseTestV2 {

	String testName = "VerifyEpisodeDownloadQualityTest";
	
	    //Vk_875:Verify if VOD content download initiates in low download quality
		//vk_876:Verify if VOD content download initiates in medium download quality
		//vk_877:Verify if VOD content download initiates in High dow nload quality
	
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("VerifyEpisodeDownloadQualityTest");
	test.log(LogStatus.INFO, "Starting the test for Episode Downloads quality Test - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
     ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	 String timebefPause="";
		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
	 homepagev2.tabClick("Watch");
	 downloadspagev2.setDeviceDownloadQuality("high");
	 int err_count=0;
	  int size=0;
	//click on watch tab	
		//test.log(LogStatus.INFO, "Navigating to Watch Page");
		//homepagev2.tabClick("Watch");
	//Get tray name from API
		String apiTrayName="";
		String uiTrayName="";
		int totalAssets=0;
		String url_kidsCharacters="";
		String api_kidsCharacters="Kids Characters Tray";
		String apiname_kidsCharacters="";
		String trayNameParameter="";
		String trayTitle="";
		Response resp_kidsCharacters=null;
		int rows_kidsCharacters=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
		apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
		if(apiname_kidsCharacters.equals(api_kidsCharacters)){
		url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
		Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
		resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
		totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
		for(int i=0;i<totalAssets;i++) {
		trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
		if(trayNameParameter.equals("allKidsCharacters")) {
		trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
		}
		}
		}
		}	
		trayTitle=trayTitle.toUpperCase();

		//Get show name from API
		String showname="", mediaid="",mediatype="";
		String uiName="";
		//Verify from API first item of ALL CHARACTERS
		String url_allCharacters="";
		String api_allCharacters="All Characters";
		String apiname_allCharacters="";
		Response resp_allCharacters=null;
		int rows_allCharacters=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rows_allCharacters;rNum++){
		apiname_allCharacters=xls.getCellData("Api", "API Name", rNum);
		if(apiname_allCharacters.equals(api_allCharacters)){
		url_allCharacters=xls.getCellData("Api", "Url", rNum);
		Map map=BasePageV2.apiparams(2, xls, api_allCharacters);
		resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
		showname=resp_allCharacters.jsonPath().get("assets.items[0].title");

		
	  }
	}

		String trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
		String allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
		//Verify if ALL CHARACTERS section is displayed
		boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
		boolean presence=false;
		 if(trayPresence==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
			 for(int scroll=0;scroll<=1;scroll++) {
				 try {
					 driver.findElement(By.xpath(allCharFirstItem)).click();
					 test.log(LogStatus.INFO, "Clicked on show");	
					 presence=true;
					 break;
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 if(scroll==1)
						 test.log(LogStatus.INFO, "Failed to click on show");	
				 }
			 }
		 }
	    //String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	    //Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
		 Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink,"clickable",5);
	    if(Utilities.explicitWaitClickable(driver, watchpagev2.downloadEpisodesLink, 20))
		 {
			
			 watchpagev2.downloadEpisodesLink.click();
			 test.log(LogStatus.INFO, "Clicking on Download Icon in show Detail page - "+showname);
		 }
		 else
			 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+showname);
		
		
        String episodetitle="";
	    if(Utilities.explicitWaitClickable(driver, showspagev2.downloadEpisodesScreenTitle, 20))
		 {
	    	test.log(LogStatus.INFO, "Navigated to Download Episodes Screen");	    	
	    	test.log(LogStatus.INFO, "Clicking on Download Icon for episode of show - "+showname);
	    	
	    	  if(Utilities.explicitWaitClickable(driver, showspagev2.downloadIconInDownloadsScreen, 20))
	    	  {
	    		  episodetitle=showspagev2.episodeTitleInDownloadEpisodesScreen.getText();
	    		  showspagev2.downloadIconInDownloadsScreen.click();
	    	  }
	    	  else
	    		  BasePageV2.reportFail("Download icon not present");
		 }
		 else
			 BasePageV2.reportFail("Not navigated to Download Episodes Screen");
		
	    driver.navigate().back();
	    Thread.sleep(1000);
	    driver.navigate().back();
	    Thread.sleep(1000);
	    
	     Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	     homepagev2.tabClick("My Stuff");
	     
		 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
		 
		 Utilities.verticalSwipe(driver, homepagev2.downloadsTab,"clickable",30);

			if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
			homepagev2.downloadsTab.click();
			else
			BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
	
			 Utilities.verticalSwipe(driver, downloadspagev2.editDownloadsMystuff,"clickable",30);
			 Thread.sleep(2000);
			    if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
				downloadspagev2.editDownloadsMystuff.click();
				else
				{
					Utilities.verticalSwipe(driver);
					 if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
							downloadspagev2.editDownloadsMystuff.click();
							else
							{
							
								BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
							}
				}
		    
			String highQualitySize="";    
			int highSize=0;
			    if(Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsScreen, 5))
				{
			    	try{
			    		highQualitySize=driver.findElement(By.xpath("//android.widget.TextView[@text='"+episodetitle+"']/..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")).getText();
			    	    highQualitySize=((highQualitySize.split("\\|")[1]).replace("MB", "")).split("\\.")[0];
			    	    highSize=Integer.parseInt(highQualitySize.trim());
			    	
			    	}
			    	catch(Exception e)
			    	{
			    		e.printStackTrace();
			    		BasePageV2.reportFail("Downloading episode content is not present in MY Stuff - Edit Downloads page");
			    	}
			    	
			    }
				else
				BasePageV2.reportFail("Not navigated to Edit Downloads Screen");
			    
				if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteDownload, 2))
				{
					downloadspagev2.deleteDownload.click();
					if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteTitle, 2))
						downloadspagev2.deleteTitle.click();
					else test.log(LogStatus.INFO, "'Delete CTA' not displayed");

					if (Utilities.explicitWaitVisible(driver, downloadspagev2.ConfirmDeleteDownload, 2))
						downloadspagev2.ConfirmDeleteDownload.click();
					else test.log(LogStatus.INFO, "'Confirmation Popup' to delete download not displayed");
				}
				else test.log(LogStatus.INFO, "Downloads is empty / Delete icon is not present");
			    
			    
			    
		    
	driver.navigate().back();
	Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	
	// medium size
	homepagev2.tabClick("Watch");
	 downloadspagev2.setDeviceDownloadQuality("medium");
 err_count=0;
	  size=0;
	//click on watch tab	
		//test.log(LogStatus.INFO, "Navigating to Watch Page");
		//homepagev2.tabClick("Watch");
		
	  rows_kidsCharacters=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
		apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
		if(apiname_kidsCharacters.equals(api_kidsCharacters)){
		url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
		Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
		resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
		totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
		for(int i=0;i<totalAssets;i++) {
		trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
		if(trayNameParameter.equals("allKidsCharacters")) {
		trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
		}
		}
		}
		}	
		trayTitle=trayTitle.toUpperCase();


		 uiName="";
		//Verify from API first item of ALL CHARACTERS
		 url_allCharacters="";
		 api_allCharacters="All Characters";
		 apiname_allCharacters="";
		 resp_allCharacters=null;
		 rows_allCharacters=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rows_allCharacters;rNum++){
		apiname_allCharacters=xls.getCellData("Api", "API Name", rNum);
		if(apiname_allCharacters.equals(api_allCharacters)){
		url_allCharacters=xls.getCellData("Api", "Url", rNum);
		Map map=BasePageV2.apiparams(2, xls, api_allCharacters);
		resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
		showname=resp_allCharacters.jsonPath().get("assets.items[0].title");

		
	  }
	}

		 trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
		 allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
		//Verify if ALL CHARACTERS section is displayed
		 trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
		 presence=false;
		 if(trayPresence==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
			 for(int scroll=0;scroll<=1;scroll++) {
				 try {
					 driver.findElement(By.xpath(allCharFirstItem)).click();
					 test.log(LogStatus.INFO, "Clicked on show");	
					 presence=true;
					 break;
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 if(scroll==1)
						 test.log(LogStatus.INFO, "Failed to click on show");	
				 }
			 }
		 }
		 Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink,"clickable",5);
	    if(Utilities.explicitWaitClickable(driver, watchpagev2.downloadEpisodesLink, 20))
		 {
	
			 watchpagev2.downloadEpisodesLink.click();
			 test.log(LogStatus.INFO, "Clicking on Download Icon in show Detail page - "+showname);
		 }
		 else
			 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+showname);
	
	
	    
	    if(Utilities.explicitWaitClickable(driver, showspagev2.downloadEpisodesScreenTitle, 20))
		 {
	   
	    	
	    	  if(Utilities.explicitWaitClickable(driver, showspagev2.downloadIconInDownloadsScreen, 20))
	    	  {
	    	
	    		  showspagev2.downloadIconInDownloadsScreen.click();
	    	  }
	    	  else
	    		  BasePageV2.reportFail("Download icon not present");
		 }
		 else
			 BasePageV2.reportFail("Not navigated to Download Episodes Screen");
		
	    driver.navigate().back();
	    Thread.sleep(1000);
	    driver.navigate().back();
	    Thread.sleep(1000);
	    
	     Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	     homepagev2.tabClick("My Stuff");
	     
		 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
		 
		 Utilities.verticalSwipe(driver, homepagev2.downloadsTab,"clickable",30);
		 

			if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
			homepagev2.downloadsTab.click();
			else
			BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
			 Utilities.verticalSwipe(driver, downloadspagev2.editDownloadsMystuff,"clickable",30);
			 Thread.sleep(2000);
			        if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
					downloadspagev2.editDownloadsMystuff.click();
					else
					{
						 if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
								downloadspagev2.editDownloadsMystuff.click();
								else
								{
								
									BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
								}
					}
		    
			String MediumQualitySize="";    
			int mediumSize=0;
			    if(Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsScreen, 5))
				{
			    	try{
			    		MediumQualitySize=driver.findElement(By.xpath("//android.widget.TextView[@text='"+episodetitle+"']/..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")).getText();
			    		MediumQualitySize=((MediumQualitySize.split("\\|")[1]).replace("MB", "")).split("\\.")[0];
			    	    mediumSize=Integer.parseInt(MediumQualitySize.trim());
			    	
			    	}
			    	catch(Exception e)
			    	{
			    		BasePageV2.reportFail("Downloading episode content is not present in MY Stuff - Edit Downloads page");
			    	}
			    	
			    }
				else
				BasePageV2.reportFail("Not navigated to Edit Downloads Screen");
	    
	    if(mediumSize<highSize)
	    {
	    	test.log(LogStatus.INFO, "It is verified that medium quality is les than high quality size");
	    }
	    else
	    {
	    	BasePageV2.reportFail("Medium quality size is higher than High quality size");
	    }
	    
	    
					if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteDownload, 2))
					{
						downloadspagev2.deleteDownload.click();
						if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteTitle, 2))
							downloadspagev2.deleteTitle.click();
						else test.log(LogStatus.INFO, "'Delete CTA' not displayed");

						if (Utilities.explicitWaitVisible(driver, downloadspagev2.ConfirmDeleteDownload, 2))
							downloadspagev2.ConfirmDeleteDownload.click();
						else test.log(LogStatus.INFO, "'Confirmation Popup' to delete download not displayed");
					}
					else test.log(LogStatus.INFO, "Downloads is empty / Delete icon is not present");
				    

				driver.navigate().back();
				Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
				
				// medium size
				homepagev2.tabClick("Watch");
				Thread.sleep(2000);
				 downloadspagev2.setDeviceDownloadQuality("low");
			 err_count=0;
				  size=0;
				//click on watch tab	
				//	test.log(LogStatus.INFO, "Navigating to Watch Page");
				  rows_kidsCharacters=xls.getRowCount("Api");
					for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
					apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
					if(apiname_kidsCharacters.equals(api_kidsCharacters)){
					url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
					Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
					resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
					totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
					for(int i=0;i<totalAssets;i++) {
					trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
					if(trayNameParameter.equals("allKidsCharacters")) {
					trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
					}
					}
					}
					}	
					trayTitle=trayTitle.toUpperCase();

		
					 uiName="";
					//Verify from API first item of ALL CHARACTERS
					 url_allCharacters="";
				api_allCharacters="All Characters";
					 apiname_allCharacters="";
					 rows_allCharacters=xls.getRowCount("Api");
					for(int rNum=1;rNum<=rows_allCharacters;rNum++){
					apiname_allCharacters=xls.getCellData("Api", "API Name", rNum);
					if(apiname_allCharacters.equals(api_allCharacters)){
					url_allCharacters=xls.getCellData("Api", "Url", rNum);
					Map map=BasePageV2.apiparams(2, xls, api_allCharacters);
					resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
					showname=resp_allCharacters.jsonPath().get("assets.items[0].title");

					
				  }
				}

					 trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
					allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
					//Verify if ALL CHARACTERS section is displayed
					 trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
					 presence=false;
					 if(trayPresence==true) {
						 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
						 for(int scroll=0;scroll<=1;scroll++) {
							 try {
								 driver.findElement(By.xpath(allCharFirstItem)).click();
								 test.log(LogStatus.INFO, "Clicked on show");	
								 presence=true;
								 break;
							 }
							 catch(Exception e) {
								 Utilities.verticalSwipe(driver);
								 if(scroll==1)
									 test.log(LogStatus.INFO, "Failed to click on show");	
							 }
						 }
					 }
					 Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink,"clickable",5);
				    if(Utilities.explicitWaitClickable(driver, watchpagev2.downloadEpisodesLink, 20))
					 {
						 
						 watchpagev2.downloadEpisodesLink.click();
						 test.log(LogStatus.INFO, "Clicking on Download Icon in show Detail page - "+showname);
					 }
					 else
						 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+showname);
				
				
				    
				    if(Utilities.explicitWaitClickable(driver, showspagev2.downloadEpisodesScreenTitle, 20))
					 {
				   
				    	
				    	  if(Utilities.explicitWaitClickable(driver, showspagev2.downloadIconInDownloadsScreen, 20))
				    	  {
				    	
				    		  showspagev2.downloadIconInDownloadsScreen.click();
				    	  }
				    	  else
				    		  BasePageV2.reportFail("Download icon not present");
					 }
					 else
						 BasePageV2.reportFail("Not navigated to Download Episodes Screen");
					
				    driver.navigate().back();
				    Thread.sleep(1000);
				    driver.navigate().back();
				    Thread.sleep(1000);
				    
				     Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
				     homepagev2.tabClick("My Stuff");
				     
					 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
					 
					 Utilities.verticalSwipe(driver, homepagev2.downloadsTab,"clickable",30);

						if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
						homepagev2.downloadsTab.click();
						else
						BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
						 Utilities.verticalSwipe(driver, downloadspagev2.editDownloadsMystuff,"clickable",30);
						 Thread.sleep(2000);
						 if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
								downloadspagev2.editDownloadsMystuff.click();
								else
								{
									Utilities.verticalSwipe(driver);
									 if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
											downloadspagev2.editDownloadsMystuff.click();
											else
											{
											
												BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
											}
								}
						String lowQualitySize="";    
						int lowSize=0;
						    if(Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsScreen, 5))
							{
						    	try{
						    		lowQualitySize=driver.findElement(By.xpath("//android.widget.TextView[@text='"+episodetitle+"']/..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")).getText();
						    		lowQualitySize=((lowQualitySize.split("\\|")[1]).replace("MB", "")).split("\\.")[0];
						    	    lowSize=Integer.parseInt(lowQualitySize.trim());
						    	
						    	}
						    	catch(Exception e)
						    	{
						    		BasePageV2.reportFail("Downloading episode content is not present in MY Stuff - Edit Downloads page");
						    	}
						    	
						    }
							else
							BasePageV2.reportFail("Not navigated to Edit Downloads Screen");
	    
	    
						    if(lowSize<mediumSize)
						    {
						    	test.log(LogStatus.INFO, "It is verified that low quality is les than medium quality size");
						    }
						    else
						    {
						    	BasePageV2.reportFail("low quality size is higher than medium quality size");
						    }
						    
							if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteDownload, 2))
							{
								downloadspagev2.deleteDownload.click();
								if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteTitle, 2))
								downloadspagev2.deleteTitle.click();
								else test.log(LogStatus.INFO, "'Delete CTA' not displayed");

								if (Utilities.explicitWaitVisible(driver, downloadspagev2.ConfirmDeleteDownload, 2))
								downloadspagev2.ConfirmDeleteDownload.click();
								else test.log(LogStatus.INFO, "'Confirmation Popup' to delete download not displayed");
							}
							else test.log(LogStatus.INFO, "Downloads is empty / Delete icon is not present");
						    
	    
	    test.log(LogStatus.PASS, "Testcase : 'Verify if VOD content download initiates in Low download quality' is Passed");
	    test.log(LogStatus.PASS, "Testcase : 'Verify if VOD content download initiates in Medium download quality' is Passed");
	    test.log(LogStatus.PASS, "Testcase : 'Verify if VOD content download initiates in High download quality' is Passed");
	    if(!Utilities.setResultsKids("VK_875", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
	    if(!Utilities.setResultsKids("VK_876", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
	    if(!Utilities.setResultsKids("VK_877", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
	    
					 
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}