package com.viacom.downloads;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
//Author: Vinoth
public class VerifyAudioDownloadQualityTest extends BaseTestV2 {

	String testName = "VerifyAudioDownloadQualityTest";
	
	//Vk_880:Verify if Audio book content download initiates in Low download quality
	//vk_881:Verify if Audio book content download initiates in medium download quality
	//vk_882:Verify if Audio book content download initiates in high download quality
	//vk_957:Verify that total remaining space available information in displayed below the Downloads tab section under My Stuff
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("VerifyAudioDownloadQualityTest");
	test.log(LogStatus.INFO, "Starting the test to verify Audio content Download quality  - "+VootConstants.DEVICE_NAME);
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
	 homepagev2.tabClick("Listen");
	 Thread.sleep(2000);
	 downloadspagev2.setDeviceAudioDownloadQuality("high");

	     test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
		 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
		 Utilities.verticalSwipe(driver, tray);
		 String audiostorytitle="";
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
		 {
			 //String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
			 //Utilities.verticalSwipe(driver, audiostory);
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 20))
			 {			 
				 listenpagev2.firstTrayAudioStory.click();
			 }
			 else
				 BasePageV2.reportFail("No contents in the tray / Content not clickable");
		 }
		 else
			 BasePageV2.reportFail("Failed to find the tray");
		 

		 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName, 20))
		 {
			 audiostorytitle=listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName.getText().toLowerCase();
		 }
		 else
		 test.log(LogStatus.FAIL, "Audio content title is missing");	
		 
	   // String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	    Utilities.verticalSwipe(driver, listenpagev2.downloadAudioBookLink,"clickable",5);
	
	    if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
		 {
			 listenpagev2.downloadAudioBookLink.click();
			 test.log(LogStatus.INFO, "Clicking on Download Icon in audio Detail page - "+audiostorytitle);
		 }
		 else
			 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+audiostorytitle);
		
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
	
		       Utilities.verticalSwipe(driver, downloadspagev2.availableStorage, "Visible",30);
			
		    if (Utilities.explicitWaitVisible(driver, downloadspagev2.availableStorage, 5))
		    {
		    	Thread.sleep(30000);
		    	try{
		        String storage=downloadspagev2.availableStorage.getText();
		    	test.log(LogStatus.INFO, "Remaining space available is-"+(storage.split(" "))[0]);		    	
		    	test.log(LogStatus.INFO, "Total space available is-"+(storage.split(" "))[2]);
		    	test.log(LogStatus.PASS, "Testcase : 'Verify that total remaining space available information in displayed below the Downloads tab section under My Stuff:' is Passed");
		    	  if(!Utilities.setResultsKids("VK_957", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
		    	}
		    	catch(Exception e)
		    	{
		    		if(!Utilities.setResultsKids("VK_957", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
		    		test.log(LogStatus.FAIL, "Something went wrong when getting storage space");
		    		BasePageV2.takeScreenshot();
		    	}
		    }
			else
			{
			  test.log(LogStatus.FAIL, "Available storage space is not displayed");
			}
			
			
			    if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
				downloadspagev2.editDownloadsMystuff.click();
				else
				BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
		    
			String highQualitySize="";    
			int highSize=0;
			    if(Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsScreen, 5))
				{
			    	try{			    		
			    		String title=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")).getText().toLowerCase();
			    		if(title.equals(audiostorytitle))
			    		{
			    		highQualitySize=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")).getText();
			    	    highQualitySize=highQualitySize.replace("MB", "").split("\\.")[0];
			    	    highSize=Integer.parseInt(highQualitySize.trim());
			    		}
			    		else
			    		{
			    			BasePageV2.reportFail("Downloading audio content is not present in MY Stuff - Edit Downloads page");
			    		}
			    	}
			    	catch(Exception e)
			    	{
			    		e.printStackTrace();
			    		BasePageV2.reportFail("Downloading audio content is not present in MY Stuff - Edit Downloads page");
			    	}
			    	
			    }
				else
				BasePageV2.reportFail("Not navigated to Edit Downloads Screen");
			    test.log(LogStatus.INFO, "High quality size is-"+highQualitySize);
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
	homepagev2.tabClick("Listen");
	Thread.sleep(2000);
	 downloadspagev2.setDeviceAudioDownloadQuality("medium");


		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Navigating to same audio content under tray");
	
		tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
		 Utilities.verticalSwipe(driver, tray);
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
		 {
			 //String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
			 //Utilities.verticalSwipe(driver, audiostory);
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 20))
			 {
				 listenpagev2.firstTrayAudioStory.click();
			 }
			 else
				 BasePageV2.reportFail("No contents in the tray / Content not clickable");
		 }
		 else
			 BasePageV2.reportFail("Failed to find the tray");
		 
        //downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
        Utilities.verticalSwipe(driver, listenpagev2.downloadAudioBookLink,"clickable",5);
	
	    if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
		 {
		
			 listenpagev2.downloadAudioBookLink.click();
			 test.log(LogStatus.INFO, "Clicking on Download Icon in audio Detail page - "+audiostorytitle);
		 }
		 else
			 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+audiostorytitle);
		
	    Thread.sleep(2000);

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
		       Utilities.verticalSwipe(driver, 	downloadspagev2.editDownloadsMystuff, "Clickable",30);
			//Utilities.verticalSwipe(driver);
		       Thread.sleep(2000);
			    if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
				downloadspagev2.editDownloadsMystuff.click();
				else
				BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
		    
			String mediumQualitySize="";    
			int mediumSize=0;
			    if(Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsScreen, 5))
				{
                   try{
			    		
			    		String title=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")).getText().toLowerCase();
			    		if(title.equals(audiostorytitle))
			    		{
			    		mediumQualitySize=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")).getText();
			    	    mediumQualitySize=mediumQualitySize.replace("MB", "").split("\\.")[0];
			    	    mediumSize=Integer.parseInt(mediumQualitySize.trim());
			    		}
			    		else
			    		{
			    			BasePageV2.reportFail("Downloading audio content is not present in MY Stuff - Edit Downloads page");
			    		}
			    	}
			    	catch(Exception e)
			    	{
			    		e.printStackTrace();
			    		BasePageV2.reportFail("Downloading audio content is not present in MY Stuff - Edit Downloads page");
			    	}
			    }
				else
				    BasePageV2.reportFail("Not navigated to Edit Downloads Screen");
			    test.log(LogStatus.INFO, "Medium quality size is-"+mediumQualitySize);    
			    
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
			    
			    
			    if(mediumSize<highSize)
			    {
			    	test.log(LogStatus.INFO, "It is verified that medium quality is les than high quality size");
			    }
			    else
			    {
			    	BasePageV2.reportFail("Medium quality size is higher than High quality size");
			    }
			    
			    Thread.sleep(2000);
				driver.navigate().back();
				Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
				
				// medium size
				homepagev2.tabClick("Listen");
				Thread.sleep(2000);
				 downloadspagev2.setDeviceAudioDownloadQuality("low");
				 Thread.sleep(2000);	
				test.log(LogStatus.INFO, "Navigating to same audio content under tray");
				
					tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
					 Utilities.verticalSwipe(driver, tray);
					 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
					 {
						 //String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
						 //Utilities.verticalSwipe(driver, audiostory);
						 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 20))
						 {
							 listenpagev2.firstTrayAudioStory.click();
						 }
						 else
							 BasePageV2.reportFail("No contents in the tray / Content not clickable");
					 }
					 else
						 BasePageV2.reportFail("Failed to find the tray");
					 
			       // downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
					 Utilities.verticalSwipe(driver, listenpagev2.downloadAudioBookLink,"clickable",5);
				
				    if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
					 {
						 Utilities.verticalSwipe(driver);
						 listenpagev2.downloadAudioBookLink.click();
						 test.log(LogStatus.INFO, "Clicking on Download Icon in audio Detail page - "+audiostorytitle);
					 }
					 else
						 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+audiostorytitle);
					
				    Thread.sleep(2000);

				    driver.navigate().back();
				    Thread.sleep(1000);
				    
				     Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
				     homepagev2.tabClick("My Stuff");
				     
					 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
					 
					 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);

						if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
						homepagev2.downloadsTab.click();
						else
						BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
						  Utilities.verticalSwipe(driver, downloadspagev2.editDownloadsMystuff, "Clickable",30);
					     
							//Utilities.verticalSwipe(driver);
						       Thread.sleep(2000);
						    if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
							downloadspagev2.editDownloadsMystuff.click();
							else
							BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
					    
						String lowQualitySize="";    
						int lowSize=0;
						    if(Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsScreen, 5))
							{
						    	try{			    		
						    		String title=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")).getText().toLowerCase();
						    		if(title.equals(audiostorytitle))
						    		{
						    		lowQualitySize=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")).getText();
						    	    lowQualitySize=lowQualitySize.replace("MB", "").split("\\.")[0];
						    	    lowSize=Integer.parseInt(lowQualitySize.trim());
						    		}
						    		else
						    		{
						    			BasePageV2.reportFail("Downloading audio content is not present in MY Stuff - Edit Downloads page");
						    		}
						    	}
						    	catch(Exception e)
						    	{
						    		e.printStackTrace();
						    		BasePageV2.reportFail("Downloading audio content is not present in MY Stuff - Edit Downloads page");
						    	}
						    }
							else
							    BasePageV2.reportFail("Not navigated to Edit Downloads Screen");
						    
						    test.log(LogStatus.INFO, "Low quality size is-"+lowQualitySize);    
	    
						    
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
						    
						    if(lowSize<mediumSize)
						    {
						    	test.log(LogStatus.INFO, "It is verified that low quality is les than medium quality size");
						    }
						    else
						    {
						    	BasePageV2.reportFail("low quality size is higher than medium quality size");
						    }
		
	    test.log(LogStatus.PASS, "Testcase : 'Verify if Audio book content download initiates in Medium download quality' is Passed"); 
	    test.log(LogStatus.PASS, "Testcase : 'Verify if Audio book content download initiates in High download quality' is Passed");
	    test.log(LogStatus.PASS, "Testcase : 'Verify if Audio book content download initiates in Low download quality' is Passed");
	    
	    if(!Utilities.setResultsKids("VK_880", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
	    if(!Utilities.setResultsKids("VK_881", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
	    if(!Utilities.setResultsKids("VK_882", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
	    
	    
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}