package com.viacom.downloads;

import java.util.Hashtable;
import java.util.List;

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
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyDownloadedAudioPlaybackTest extends BaseTestV2 {

	String testName = "VerifyDownloadedAudioPlaybackTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
	throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Downloaded Audios Playback Functionality");
	test.log(LogStatus.INFO, "Starting the test for Downloaded Audios Playback Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int playbackrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",playbackrow, "Verify if Downloaded contents will be played in loop if there are more than 2 contents");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrowrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",uparrowrow, "Verify the availablity of Uparrow option if there are more than 2 Audio Book contents in downloads tab");	
	
		
	launchApp();
	 HomePageV2 homepagev2=new HomePageV2(driver, test);
	 LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";

	 int err_count=0;	 
	 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
	 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);
	 if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
		homepagev2.downloadsTab.click();
	 else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
	 Utilities.verticalSwipe(driver);
	 Utilities.verticalSwipe(driver);
	 String downloadedTitle1="",downloadedTitle2="";
	 if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 5)){
		test.log(LogStatus.INFO, "Downloaded Audios are : ");
		try{
			downloadedTitle1=downloadspagev2.downloadedContentTitles.get(0).getText();
			test.log(LogStatus.INFO, downloadedTitle1);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Failed to fetch first downloaded item");
		}
		try {
			downloadedTitle2=downloadspagev2.downloadedContentTitles.get(1).getText();
			test.log(LogStatus.INFO, downloadedTitle2);
		}
		catch(Exception e){
			test.log(LogStatus.FAIL, "Failed to fetch second downloaded item");
		}
		if(Utilities.explicitWaitClickable(driver, downloadspagev2.contenttoDownloadandDownloaded, 5)){		  
		    test.log(LogStatus.INFO, "Playing the downloaded Audio - "+downloadedTitle1);
		    downloadspagev2.contenttoDownloadandDownloaded.click();
		}
		else
		    BasePageV2.reportFail("Play icon is not displayed");
		if(Utilities.explicitWaitVisible(driver, playerpagev2.audioPlayerTitle, 60)){
		   test.log(LogStatus.INFO, "Audio title is displayed in the audio player");
		   if(playerpagev2.audioPlayerTitle.getText().equalsIgnoreCase(downloadedTitle1)){
		    	test.log(LogStatus.INFO, "Downloaded Audio Title is matching with the title in player");
		   }
		   else
			   test.log(LogStatus.FAIL, "Audio player title in the Player not matching with downloaded audio title");	 
		}
		else
			test.log(LogStatus.FAIL, "Audio title is not displayed in the player");

		if(Utilities.explicitWaitVisible(driver, playerpagev2.playerBottomUpArrowButton, 60)){
		    test.log(LogStatus.INFO, "Uparrow button is displayed when there are more than 1 content");	
		    test.log(LogStatus.PASS,"Testcase : 'Verify the availablity of Uparrow option if there are more than 2 Audio Book contents in downloads tab' is Passed");
		    BasePageV2.smokeresults("", uparrowrow, "Pass");
		    playerpagev2.playerBottomUpArrowButton.click();
		    if (Utilities.explicitWaitVisible(driver, playerpagev2.playerBottomDownArrowButton, 2)){
		    	
				playerpagev2.playerBottomDownArrowButton.click();
			}
			else 
				test.log(LogStatus.FAIL, " 'Audio playlist collapser (Down arrow)' switch button is not displayed");
		    		 
		 }
		 else
			 test.log(LogStatus.FAIL, "Uparrow button is not displayed when there are more than 2 contents");
		
		 if(Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 60)){
	    	    test.log(LogStatus.INFO, "Scrubbing fully to play the next audio");
				Utilities.scrubtillend(driver, homepagev2.audioseekBar);
				Thread.sleep(5000);
				if(Utilities.explicitWaitVisible(driver, downloadspagev2.listenAgain, 20)){
					test.log(LogStatus.INFO, "Listen Again displayed");
				    test.log(LogStatus.PASS,"Testcase : 'Verify if Downloaded contents will be played in loop if there are more than 2 contents' is Passed");
				    BasePageV2.smokeresults("Verify if Downloaded contents will be played in loop if there are more than 2 contents", playbackrow, "Pass");
				}
				else {
					test.log(LogStatus.FAIL, "Listen Again is not displayed");
				    test.log(LogStatus.FAIL,"Testcase : 'Verify if Downloaded contents will be played in loop if there are more than 2 contents' is Passed");
				    BasePageV2.smokeresults("Verify if Downloaded contents will be played in loop if there are more than 2 contents", playbackrow, "Fail");
				}
		 }
		 else 
			 BasePageV2.reportFail("Unable to find seekbar / Infinite loader observed");
		 
		 /*if(Utilities.explicitWaitVisible(driver, playerpagev2.audioPlayerTitle, 60)){
			if(playerpagev2.audioPlayerTitle.getText().equalsIgnoreCase(downloadedTitle2)){
			    test.log(LogStatus.INFO, "Next content in the tray started playback post completion of 1st content");
			}
			else
				test.log(LogStatus.FAIL, "Audio player title in the Player not matching with next content title in downloads tray");  		 
		 }
		 else
			 test.log(LogStatus.FAIL, "Next content is not played after scrubbing fully");   */
	}
	else
		test.log(LogStatus.FAIL, "No Downloaded audios are present under Downloads Tab");
	  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}