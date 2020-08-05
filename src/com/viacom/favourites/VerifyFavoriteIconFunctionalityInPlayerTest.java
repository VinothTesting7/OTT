package com.viacom.favourites;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

//Author : Vinoth

//priority - P2

//VK_2317 - Verify the functionality when user taps on Favorite icon from episode player for the 1st time:
//VK_2319 - Verify the functionality when user taps on Favorite icon from episode player from 2nd content onwards:
//VK_2320 - Verify the functionality when user taps on Favorite icon from movie player for the 1st time:
//VK_2322 - Verify the functionality when user taps on Favorite icon from movie player from 2nd content onwards:

public class VerifyFavoriteIconFunctionalityInPlayerTest extends BaseTestV2 {
	String testName = "VerifyFavoriteIconFunctionalityInPlayerTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("VerifyFavoriteIconFunctionalityInPlayerTest");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Screen UI functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	driver.resetApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);

	String un=data.get("Email");
	String pwd=data.get("Password");
	homepagev2.login(un, pwd);
	
	String timebefPause="";
	 //Click on Watch tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			homepagev2.tabClick("Watch");
	 test.log(LogStatus.INFO, "Clicked on Watch tab");
	 //BasePageV2.takeScreenshot();
	//Click on the first carousal item
	 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
	 watchpagev2.firstItemInCarousal.click();
	 
	 test.log(LogStatus.INFO, "Playing any of the content from carousal");
		  if(Utilities.explicitWaitVisible(driver,playerpagev2.videoPlayer, 3)) {
				  
				  //Check for progress bar
				  homepagev2.verifyAndProgressBar();
				
				  
				  //Pause the video
				  
				  playerpagev2.pauseVideo();
				  test.log(LogStatus.INFO, "Verifying the Favorite icon functionality of VOD Player");	  
			
					if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
					{
						
						if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
						{
							playerpagev2.playerFavoriteButton.click();
							Thread.sleep(3000);
						}
						if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
						{
							if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
								test.log(LogStatus.INFO, "Pop up message is displayed");
							else
								test.log(LogStatus.FAIL, "Pop up message is not displayed");
							if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
							{
								showspagev2.favIconPopupOkButton.click();
								Thread.sleep(3000);
								test.log(LogStatus.PASS, "Testcase : 'Verify the functionality when user taps on Favorite icon from episode player for the 1st time:' is Passed");
								  if(!Utilities.setResultsKids("VK_2317", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
									{
										//test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for a movie card:' is Passed");
										//if(!Utilities.setResultsKids("VK_2317", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
									else
									{
										//test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
										//if(!Utilities.setResultsKids("VK_2315", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
							}
							else
							{
								test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting Episode content in player for first time");
								  if(!Utilities.setResultsKids("VK_2317", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							}
							
						}
						else
						{
							  if(!Utilities.setResultsKids("VK_2317", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							test.log(LogStatus.FAIL, "Pop up is not displayed after favoriting episode content in player for first time");
						}
						
					
						
					if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.playerFavoriteButton, 30))
						{	
							//test.log(LogStatus.INFO, "Favourite icon gets filled after favoriting");
							playerpagev2.playerFavoriteButton.click();
							Thread.sleep(3000);
						}
						else
						{
							test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
					}
					
					if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerCloseButton, 30))
					{
						playerpagev2.playerCloseButton.click();
					}
					else
						{
						test.log(LogStatus.FAIL, "Not able to click on player close button");
						BasePageV2.takeScreenshot();
						}
					
					
	   
		  }
	    }
	 
	 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
	 watchpagev2.firstItemInCarousal.click();
	 
	 test.log(LogStatus.INFO, "Playing any of the content from carousal");
		  if(Utilities.explicitWaitVisible(driver,playerpagev2.videoPlayer, 3)) {
				  
				  //Check for progress bar
				  homepagev2.verifyAndProgressBar();
				
				  
				  //Pause the video
				  
				  playerpagev2.pauseVideo();
				  test.log(LogStatus.INFO, "Verifying the Favorite icon functionality of VOD Player");	  
			
					if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
					{
						
						if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
						{
							playerpagev2.playerFavoriteButton.click();
							Thread.sleep(3000);
						}
						if(!Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 5))
						{
							
								test.log(LogStatus.INFO, "Pop up message is not displayed");
								test.log(LogStatus.PASS, "Testcase : 'Verify the functionality when user taps on Favorite icon from episode player from 2nd content onwards:' is Passed");
								  if(!Utilities.setResultsKids("VK_2319", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
						
						
						}
						else
						{
							if(!Utilities.setResultsKids("VK_2319", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							test.log(LogStatus.FAIL, "Pop up is displayed after favoriting episode content in player for second time");
						
						}
						
					if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.playerFavoriteButton, 30))
						{	
							
							playerpagev2.playerFavoriteButton.click();
							Thread.sleep(3000);
						}
						else
						{
							test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
					}
					
					if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerCloseButton, 30))
					{
						playerpagev2.playerCloseButton.click();
					}
					else
						{
						test.log(LogStatus.FAIL, "Not able to click on player close button");
						BasePageV2.takeScreenshot();
						}
					
					
	   
		  }
	    }
	 driver.resetApp();
	 homepagev2.login(un, pwd);
	 
	 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			homepagev2.tabClick("Watch");
	 test.log(LogStatus.INFO, "Clicked on Watch tab");
	 //BasePageV2.takeScreenshot();
	//Click on the first carousal item
		    Thread.sleep(2000);	
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
			if(trayNameParameter.equals("featuredMovies")) {
			trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
			}
			}
			} 
			}	
			trayTitle=trayTitle.toUpperCase();

			//Get show name from API
			String apiName="", mediaid="",mediatype="";
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
			apiName=resp_allCharacters.jsonPath().get("assets.items[0].title");

		  }
		}

			String trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
			String allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
			//Verify if ALL CHARACTERS section is displayed
			boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
			boolean presence=false;
			 if(trayPresence==true) {
				 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
				 for(int scroll=0;scroll<=1;scroll++) {
					 try {
						 driver.findElement(By.xpath(allCharFirstItem)).click();
						 test.log(LogStatus.INFO, "Clicked on movie content");	
						 presence=true;
						 break;
					   }
					 catch(Exception e) {
						 Utilities.verticalSwipe(driver);
						 if(scroll==1)
							 test.log(LogStatus.INFO, "Failed to click on movie content");	
					 }
				 }
			//cHECK FOR PLAY BUTTON
			
			if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPagePlayButton , 30))
			{
				 showspagev2.showDetailPagePlayButton.click();
			}
			else
			BasePageV2.reportFail("Play Button is not displayed in Show Detail page");
			homepagev2.verifyAndProgressBar();
			playerpagev2.pauseVideo();

				  test.log(LogStatus.INFO, "Verifying the Favorite icon functionality of VOD Player");	  
			
					if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
					{
						
						if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
						{
							playerpagev2.playerFavoriteButton.click();
							Thread.sleep(3000);
						}
						if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
						{
							if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
								test.log(LogStatus.INFO, "Pop up message is displayed");
							else
								test.log(LogStatus.FAIL, "Pop up message is not displayed");
							if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
							{
								showspagev2.favIconPopupOkButton.click();
								Thread.sleep(3000);
								test.log(LogStatus.PASS, "Testcase : 'Verify the functionality when user taps on Favorite icon from movie player for the 1st time:' is Passed");
								  if(!Utilities.setResultsKids("VK_2320", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
									{
										
										//test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for a movie card:' is Passed");
										//if(!Utilities.setResultsKids("VK_2317", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
									else
									{
										//test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
										//if(!Utilities.setResultsKids("VK_2315", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
							}
							else
							{
								test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting Episode content in player for first time");
								  if(!Utilities.setResultsKids("VK_2320", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							}
							
						}
						else
						{
							  if(!Utilities.setResultsKids("VK_2320", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							test.log(LogStatus.FAIL, "Pop up is not displayed after favoriting movie content in player for first time");
						}
						
					
						
					if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.playerFavoriteButton, 30))
						{	
							test.log(LogStatus.INFO, "Favourite icon gets filled after favoriting");
							playerpagev2.playerFavoriteButton.click();
							Thread.sleep(3000);
						}
						else
						{
							test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
					}
					
					if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerCloseButton, 30))
					{
						playerpagev2.playerCloseButton.click();
					}
					else
						{
						test.log(LogStatus.FAIL, "Not able to click on player close button");
						BasePageV2.takeScreenshot();
						}
					
					
					
					 if(trayPresence==true) {
						 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
						 for(int scroll=0;scroll<=1;scroll++) {
							 try {
								 driver.findElement(By.xpath(allCharFirstItem)).click();
								 test.log(LogStatus.INFO, "Clicked on movie content");	
								 presence=true;
								 break;
							   }
							 catch(Exception e) {
								 Utilities.verticalSwipe(driver);
								 if(scroll==1)
									 test.log(LogStatus.INFO, "Failed to click on movie content");	
							 }
						 }
					//cHECK FOR PLAY BUTTON
					
					if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPagePlayButton , 30))
					{
						 showspagev2.showDetailPagePlayButton.click();
					}
					else
					BasePageV2.reportFail("Play Button is not displayed in Show Detail page");
					homepagev2.verifyAndProgressBar();
					playerpagev2.pauseVideo();

						  test.log(LogStatus.INFO, "Verifying the Favorite icon functionality of VOD Player");	  
					
							if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
							{
								
								if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerFavoriteButton, 30))
								{
									playerpagev2.playerFavoriteButton.click();
									Thread.sleep(3000);
								}
								
								if(!Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 5))
								{
									
										test.log(LogStatus.INFO, "Pop up message is not displayed");
										test.log(LogStatus.PASS, "Testcase : 'Verify the functionality when user taps on Favorite icon from movie player from 2nd content onwards:' is Passed");
										  if(!Utilities.setResultsKids("VK_2322", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
								}
								else
								{
									if(!Utilities.setResultsKids("VK_2322", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									test.log(LogStatus.FAIL, "Pop up is displayed after favoriting episode content in player for second time");
								
								}
							if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.playerFavoriteButton, 30))
								{	
	
									playerpagev2.playerFavoriteButton.click();
									Thread.sleep(3000);
								}
								else
								{
									test.log(LogStatus.FAIL, "Favourite icon not found");
								}
							}
							else
							{
								test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
							}
							
							if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerCloseButton, 30))
							{
								playerpagev2.playerCloseButton.click();
							}
							else
								{
								test.log(LogStatus.FAIL, "Not able to click on player close button");
								BasePageV2.takeScreenshot();
								}
					
					
					
					
					
					
					
					
					
					
	   
		  }
	    
	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 }}}
	 
		  
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}