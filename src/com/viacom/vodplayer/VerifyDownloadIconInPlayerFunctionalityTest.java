package com.viacom.vodplayer;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
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
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidKeyCode;

//Author : Vinoth kumar
//pRIORITY - P2

//VK_2221 - Verify the state of episode content in Download Episode screen once the download is initiated & completed from player:
//VK_2223 - Verify the functionality when user taps on Download icon from 2nd time:


public class VerifyDownloadIconInPlayerFunctionalityTest extends BaseTestV2 {
	String testName = "VerifyDownloadIconInPlayerFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("VerifyDownloadIconInPlayerFunctionalityTest");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Screen Playlist UI functionality: "+VootConstants.DEVICE_NAME);
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
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	String timebefPause="";

		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
		 downloadspagev2.setDeviceDownloadQuality("high");
	     //click on watch tab	
		 test.log(LogStatus.INFO, "Navigating to Watch Page");
		 Thread.sleep(2000);
		 homepagev2.tabClick("Watch");
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
		 String apiName="";
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

				  Utilities.verticalSwipe(driver, showspagev2.showDetailPageDownlaodEpisodesButton, "clickable", 2);
				  if(Utilities.explicitWaitClickableNew(driver,showspagev2.showDetailPageDownlaodEpisodesButton , 120))
					{
					  showspagev2.showDetailPageDownlaodEpisodesButton.click();
					}
				  else
					  BasePageV2.reportFail("Not able to click on download episodes button / download episodes button not found "); 
				  
				  if(Utilities.explicitWaitVisibleNew(driver,showspagev2.downloadEpisodesScreenTitle , 30))
					{
					  test.log(LogStatus.INFO, "Downloading the first content from Download Episodes screen");
					  if(Utilities.explicitWaitClickableNew(driver,downloadspagev2.downloadStateIcon , 30))
					  {
						  downloadspagev2.downloadStateIcon.click();
					  }
					  else
						  BasePageV2.reportFail("Not able to click on download icon in Download Episodes screen"); 
					}
				  else
					  BasePageV2.reportFail("Not navigated to download episodes screen"); 
				  
				  driver.pressKeyCode(AndroidKeyCode.BACK);	  
				  
				  if(Utilities.explicitWaitClickableNew(driver,showspagev2.showDetailPagePlayButton, 120))
					{
					  showspagev2.showDetailPagePlayButton.click();
					}
				  else
					  BasePageV2.reportFail("Not able to click on episodes section content  / episode section content not found "); 
			
				  
				/*  Utilities.verticalSwipe(driver, showspagev2.showDetailPageEpisodesSectionFirstVideo, "clickable", 2);	  
				  if(Utilities.explicitWaitClickableNew(driver,showspagev2.showDetailPageEpisodesSectionFirstVideo , 120))
					{
					  showspagev2.showDetailPageEpisodesSectionFirstVideo.click();
					}
				  else
					  BasePageV2.reportFail("Not able to click on episodes section content  / episode section content not found "); 
				 */
				  homepagev2.verifyAndProgressBar();	
				  //Pause the video
				  playerpagev2.pauseVideo();
				 
				  test.log(LogStatus.INFO, "Verifying whether download is in progress in player");
				  //Click on download
				  if(Utilities.explicitWaitClickableNew(driver, watchpagev2.vodPlayerDownloadInProgressButton , 5))
					{
					 test.log(LogStatus.INFO, "Download is in progress state");
					 test.log(LogStatus.PASS, "Testcase : 'Verify the state download icon in player when user initiates download from programme info and play the content:' is Passed");
					 if(!Utilities.setResultsKids("VK_2221", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					}
				  else if(Utilities.explicitWaitClickableNew(driver, watchpagev2.vodPlayerDownloadCompletedButton , 5))
					  {
					   test.log(LogStatus.INFO, "Download is completed");
					   test.log(LogStatus.PASS, "Testcase : 'Verify the state download icon in player when user initiates download from programme info and play the content:' is Passed");
						 if(!Utilities.setResultsKids("VK_2221", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					  }
				  else
				  {
					  if(!Utilities.setResultsKids("VK_2221", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					  BasePageV2.reportFail("Download is not in progress state or completed state");
				  }
				  
				  if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerCloseButton , 20))
					{
					  playerpagev2.playerCloseButton.click();
					}
				  else
				  {
					  BasePageV2.reportFail("Not able to click on close button");
				  }
				  
				  Utilities.verticalSwipe(driver, showspagev2.showDetailPageDownlaodEpisodesButton, "clickable", 2);
				  if(Utilities.explicitWaitClickableNew(driver,showspagev2.showDetailPageDownlaodEpisodesButton , 120))
					{
					  showspagev2.showDetailPageDownlaodEpisodesButton.click();
					}
				  else
					  BasePageV2.reportFail("Not able to click on download episodes button / download episodes button not found "); 
				  
				  if(Utilities.explicitWaitVisibleNew(driver,showspagev2.downloadEpisodesScreenTitle , 30))
					{
					  if(Utilities.explicitWaitClickableNew(driver,downloadspagev2.inprogress , 5))
					  {
						  downloadspagev2.inprogress.click(); 
						  if (Utilities.explicitWaitVisible(driver, downloadspagev2.canceldownloadingThing, 3)) {
					
								downloadspagev2.canceldownloadingThing.click();
								test.log(LogStatus.INFO, "Cancelling the download from Download Episodes screen");
								if (Utilities.explicitWaitVisible(driver, downloadspagev2.ConfirmDeleteDownload, 5))
									downloadspagev2.ConfirmDeleteDownload.click();
								else
									BasePageV2.reportFail("Delete confirmation popup not displayed");
							}

							else
								BasePageV2.reportFail("Cancel Download popup not displayed");
					    }
					  else
						  BasePageV2.reportFail("Not able to click on download icon in Download Episodes screen"); 
					}
				  else
					  BasePageV2.reportFail("download is not in progress"); 
				  
				  
			 }      
				  else
					  BasePageV2.reportFail("Not navigated to download episodes screen"); 
				  
				  
                  driver.pressKeyCode(AndroidKeyCode.BACK);	  
				  
				  if(Utilities.explicitWaitClickableNew(driver,showspagev2.showDetailPagePlayButton, 30))
					{
					  showspagev2.showDetailPagePlayButton.click();
					}
				  else
					  BasePageV2.reportFail("Not able to click on episodes section content  / episode section content not found "); 
			
				  
				/*  Utilities.verticalSwipe(driver, showspagev2.showDetailPageEpisodesSectionFirstVideo, "clickable", 2);	  
				  if(Utilities.explicitWaitClickableNew(driver,showspagev2.showDetailPageEpisodesSectionFirstVideo , 120))
					{
					  showspagev2.showDetailPageEpisodesSectionFirstVideo.click();
					}
				  else
					  BasePageV2.reportFail("Not able to click on episodes section content  / episode section content not found "); 
				 */
				  homepagev2.verifyAndProgressBar();	
				  //Pause the video
				  playerpagev2.pauseVideo();
				 test.log(LogStatus.INFO, "Verifying whether download is cancelled in player");
				  //Click on download
				  if(Utilities.explicitWaitClickableNew(driver, watchpagev2.vodPlayerDownloadInProgressButton , 5))
					{
					  test.log(LogStatus.FAIL, "Download is still in progress state");
						 if(!Utilities.setResultsKids("VK_2223", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
				    }
				  else if(Utilities.explicitWaitClickableNew(driver, watchpagev2.vodPlayerDownloadCompletedButton , 5))
					  {
      					   test.log(LogStatus.FAIL, "Download is completed");
							 if(!Utilities.setResultsKids("VK_2223", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					  }
				  else if(Utilities.explicitWaitClickableNew(driver, watchpagev2.vodPlayerDownloadButton , 5))
				  {
					  if(!Utilities.setResultsKids("VK_2223", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					  test.log(LogStatus.PASS, "Testcase :'Verify the state of download icon in player when user initiates download & cancells the same from 'Download Episodes' screen from programme info and play the content:' is Passed");
				  }	  
				  else
				  {
					  BasePageV2.reportFail("Download icon not found");
				  }
 }


	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}