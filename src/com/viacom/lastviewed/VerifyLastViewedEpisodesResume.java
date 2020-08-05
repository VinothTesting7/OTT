package com.viacom.lastviewed;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
//Author Tanisha
public class VerifyLastViewedEpisodesResume extends BaseTestV2{
	
	String testName = "VerifyLastViewedEpisodesResume";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedEpisodesResume(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedEpisodesResume");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		//VK_1676 = Verify the playback status for partially watched episode content in Last viewed tray
		//VK_1677 = Verify the playback status for Completely watched episode content
		//VK_1904 = Verify if the Episode cards are added to the 1st position Last viewed tray if user plays the same content again from LV
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 
		 boolean clickedOnShow=false;
		 String episode1="";
		 String episode2="";
		 String lastViewed1,lastViewed2="";
		 int errVK_1505=0;
		 String durationPlayed="";
		 String durationResumed="";
		 String durationMinString="";
		 String durationSecString="";
		 int durationMinInt=0;
		 int durationSecInt=0;
		 String[] duration=new String[2];
		 int durationPlayedInt=0;
		 int durationResumedInt=0;
		 int partiallyWatchedFirstTime=0;
		 int partiallyWatchedSecondTime=0;
		 int completelyWatchedTime=0;
		//Login module 
		//homepagev2.logout();
		homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 
		//Click on Watch tab
		 homepagev2.tabClick("Watch");
		//Scroll to shows tray
		 for(int scroll=0;scroll<=4;scroll++) {
			 if(Utilities.explicitWaitVisible(driver, watchpagev2.allKidsCharacters2, 5)) 
				 break;
			 else
				 Utilities.verticalSwipe(driver); 
		 }
		 
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.allKidsCharacters2, 10)) {
			 try {
				 watchpagev2.allKidsCharacters2.click();
				 test.log(LogStatus.INFO, "Clicked on 2nd character from kids characters tray");
				 clickedOnShow=true;
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on 2nd character from kids characters tray");
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "2nd character from kids characters tray is not clickable");
		 }
				 
		 if(clickedOnShow==true) {
			 for(int findtray=0;findtray<=3;findtray++) {
				 if(Utilities.explicitWaitClickable(driver, showpagev2.episodesTray, 5)) {
					 test.log(LogStatus.INFO, "Located Episodes tray"); 
					 Utilities.verticalSwipe(driver);
					 Utilities.verticalSwipe(driver);
					 Thread.sleep(2000);
					 if(Utilities.explicitWaitVisible(driver, showpagev2.showDetailsEpisode1, 10)){
						 episode1=showpagev2.showDetailsEpisode1Title.getAttribute("text");
						 try {
							 showpagev2.showDetailsEpisode1Title.click();
							 test.log(LogStatus.INFO, "Clicked on first episode: "+episode1);
							 homepagev2.verifyAndProgressBar();
							 test.log(LogStatus.INFO, "First Episode started to play");
							 kidsplayerv2.pauseVideo();
							 try {
								 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
								 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
								 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed);
								 partiallyWatchedFirstTime=durationPlayedInt;
								 test.log(LogStatus.INFO, "Duration played in Seconds: "+partiallyWatchedFirstTime);
							 }
							 catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to fetch last player current duration"); 
							 }
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to close the player");
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Failed to click on first episode");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "First episode is not visible, hence failed to fetch name");
					 }
					 if(Utilities.explicitWaitVisible(driver, showpagev2.showDetailsEpisode2, 10)){
						 episode2=showpagev2.showDetailsEpisode2Title.getAttribute("text");
						 try {
							 showpagev2.showDetailsEpisode2Title.click();
							 test.log(LogStatus.INFO, "Clicked on second episode: "+episode2);
							 homepagev2.verifyAndProgressBar();
							 test.log(LogStatus.INFO, "Second Episode started to play");
							 kidsplayerv2.pauseVideo();
							 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
							 driver.navigate().back();
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Failed to click on second episode");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Second episode is not visible, hence failed to fetch name");
					 }
					 break;
				 }
				 else {
					 Thread.sleep(1000);
					 Utilities.verticalSwipe(driver);
					 if(findtray==3) {
						 test.log(LogStatus.FAIL, "Could not locate Episodes tray");
					 }
				 }
			 }
		}
		//Wait for 3 minutes
		for(int wait1=0;wait1<=3;wait1++) {
			 for(int wait=0;wait<=60;wait++) {
				 Thread.sleep(1000);
			 }
		}
		test.log(LogStatus.INFO, "Waited for 3 minutes");
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("My Stuff");
		homepagev2.tabClick("Watch");
		//Scroll till Last Viewed
		String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
	    	if(lastViewed1.contains(episode2)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the first latest episode under last viewed in Watch tab");
	    		//Verify complete resume
	    		try {
	    			homepagev2.lastViewedFirstItemTitle.click();
	    			homepagev2.verifyAndProgressBar();
					test.log(LogStatus.INFO, "Played on episode that was played completely");
					kidsplayerv2.pauseVideo();
					try {
						 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
						 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
						 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed); 
						 completelyWatchedTime=durationPlayedInt;
						 test.log(LogStatus.INFO, "Duration played in Seconds: "+completelyWatchedTime);
						 if(completelyWatchedTime<=30) {
							 test.log(LogStatus.INFO, "The completely watched episode is assumed to have begun from beginning since captured time is below 30 seconds");
							 test.log(LogStatus.PASS, "Verify the playback status for Completely watched episode content is Pass");
							 if(!Utilities.setResultsKids("VK_1677", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
						 else {
							 test.log(LogStatus.FAIL, "The completely watched episode is assumed to NOT have begun from beginning since captured time is above 30 seconds");
							 test.log(LogStatus.FAIL, "Verify the playback status for Completely watched episode content is Fail");
							 if(!Utilities.setResultsKids("VK_1677", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to fetch last player current duration"); 
					}
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to close the player");		
	    		}
	    		catch(Exception e) {
	    			test.log(LogStatus.FAIL, "Failed to play episode that was played completely previously");
	    		}	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in Watch tab instead of "+episode2);
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 1 is not visible under last viewed in Watch tab");
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
	    	if(lastViewed2.contains(episode1)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed in Watch tab");
	    		//Verify partial resume
	    		try {
	    			homepagev2.lastViewedSecondItemTitle.click();
	    			homepagev2.verifyAndProgressBar();
					test.log(LogStatus.INFO, "Played episode that was played partially");
					kidsplayerv2.pauseVideo();
					try {
						 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
						 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
						 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed);
						 partiallyWatchedSecondTime=durationPlayedInt;
						 test.log(LogStatus.INFO, "Duration played in Seconds: "+partiallyWatchedSecondTime);
						 if(partiallyWatchedSecondTime>partiallyWatchedFirstTime) {
							 test.log(LogStatus.INFO, "The partially watched episode is assumed to have resumed from where it was paused");
							 test.log(LogStatus.PASS, "Verify the playback status for partially watched episode content in Last viewed tray is Pass");
							 if(!Utilities.setResultsKids("VK_1676", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
						 else {
							 test.log(LogStatus.FAIL, "The partially watched episode is assumed to not have resumed from where it was paused");
							 test.log(LogStatus.FAIL, "Verify the playback status for partially watched episode content in Last viewed tray is Fail");
							 if(!Utilities.setResultsKids("VK_1676", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to fetch last player current duration"); 
					}
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to close the player");		
	    		}
	    		catch(Exception e) {
	    			test.log(LogStatus.FAIL, "Failed to play episode that was played completely previously");
	    		}	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed in Watch tab instead of "+episode1);	    	
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 3 is not visible under last viewed in Watch tab");
	    }
	   //Wait for 3 minutes
	  	for(int wait1=0;wait1<=3;wait1++) {
	  		for(int wait=0;wait<=60;wait++) {
	  			Thread.sleep(1000);
	  		}
	  	}
	  	test.log(LogStatus.INFO, "Waited for 3 minutes");
	  	//Relaunch app
	  	try {
			driver.closeApp();
		} 
		catch (Exception e) {}
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKbetaSplashActivity"));
		test.log(LogStatus.INFO, "Relaunched app");
	  	
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
	    	if(lastViewed1.contains(episode1)) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest episode under last viewed in Watch tab");
	    		test.log(LogStatus.INFO, "Video played recently from Last Viewed takes first place in last viewed");
	    		test.log(LogStatus.PASS, "Verify if the Episode cards are added to the 1st position Last viewed tray if user plays the same content again from LV is Pass");
	    		if(!Utilities.setResultsKids("VK_1904", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in Watch tab instead of "+episode1);
	    		test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the 1st position Last viewed tray if user plays the same content again from LV is Fail");
	    		if(!Utilities.setResultsKids("VK_1904", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 1 is not visible under last viewed in Watch tab");
	    }
	    basepagev2.takeScreenshot();
	    
		homepagev2.mystuff_tab=null;
		watchpagev2.allKidsCharacters2=null;
		showpagev2.episodesTray=null;
		showpagev2.showDetailsEpisode1=null;
		showpagev2.showDetailsEpisode1Title=null;
		showpagev2.showDetailsEpisode2=null;
		showpagev2.showDetailsEpisode2Title=null;
		showpagev2.showDetailsEpisode3=null;
		showpagev2.showDetailsEpisode3Title=null;
		homepagev2.lastViewedFirstItemTitle=null;
		homepagev2.lastViewedSecondItemTitle=null;
		homepagev2.lastViewedThirdItemTitle=null;
		homepagev2.recentViewedClear=null;
		homepagev2.recentViewedClearMessage=null;
		homepagev2.recentViewedClearYes=null;
		homepagev2.recentViewedClearNo=null;
		
		

	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
