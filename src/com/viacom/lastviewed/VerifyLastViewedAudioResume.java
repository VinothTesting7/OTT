package com.viacom.lastviewed;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.Activity;
//Author Tanisha
public class VerifyLastViewedAudioResume extends BaseTestV2{
	
	String testName = "VerifyLastViewedAudioResume";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedAudioResume(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedAudioResume");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		//VK_1681 = Verify the playback status for partially watched Audiobook content in Last viewed tray
		//VK_1682 = Verify the playback status for Completely watched Audiobook content
		//VK_1905 = Verify if the Audio cards are added to the 1st position Last viewed tray if user plays the same content again from LV
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 
		 String audio1="";
		 String audio2="";
		 String audio3="";
		 String lastViewed1,lastViewed2,lastViewed3="";
		 String durationPlayed="";
		 int durationPlayedInt=0;
		 int partiallyWatchedFirstTime=0;
		 int partiallyWatchedSecondTime=0;
		 int completelyWatchedTime=0;
		 ArrayList<String> audios=new ArrayList<String>();
			//Login module 
			//homepagev2.logout();
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 
		//Click on Watch tab
		 homepagev2.tabClick("Listen");
		 homepagev2.tabClick("Listen");
		 
		 
		//Click on Famous Story tellers
		String trayLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
		for(int scroll=0;scroll<=2;scroll++) {
			driver.findElement(By.xpath(trayLoc)).click();
		}
		for(int count=0;count<=1;count++) {
			try {
				String audioLocs="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='"+count+"']//android.widget.TextView[@index='2']";
				audios.add(driver.findElementByXPath(audioLocs).getAttribute("text"));
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to fetch audio names");
			}
		}	
		try {
			 audio1=audios.get(0);
			 audio2=audios.get(1);
		}
		catch(Exception e) {
		}

		 //Audio 1 to launch and close
		 test.log(LogStatus.INFO, "-----------------Audio 1 to play partially-----------------");
		 boolean playerDisplayed=false;
		 try {
			 driver.findElement(By.xpath("//android.widget.TextView[@text='"+audio1+"']")).click();
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 30)) {
				 try {
					 listenpagev2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button of audio 1: "+audio1);
					 homepagev2.verifyAndProgressBar();
					 Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);
					 kidsplayerv2.pauseAudio();
					 try {
						 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
						 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
						 partiallyWatchedFirstTime=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed);
						 test.log(LogStatus.INFO, "Duration played in Seconds: "+partiallyWatchedFirstTime);
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to fetch current duration"); 
					 }
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
						
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to tray details");					 
				   }
				   catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Play button of the Audio");
				   }
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Audio 1");
		 }
		 //Audio 2 to play partially
		 test.log(LogStatus.INFO, "----------------Audio 2 to play completely------------------");
		 try {
			 driver.findElement(By.xpath("//android.widget.TextView[@text='"+audio2+"']")).click();
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 30)) {
				 try {
					 listenpagev2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button of audio 2: "+audio2);
					 homepagev2.verifyAndProgressBar();
					 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
					 kidsplayerv2.pauseAudio();
					 if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerListenAgainClose, 10)) {
						 try {
							 listenpagev2.audioPlayerListenAgainClose.click();
							 test.log(LogStatus.INFO, "Closed overlay"); 
						 }
						 catch(Exception e) {
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Closed overlay"); 
						 }
					 }
					 try {
							kidsplayerv2.audioPlayerCloseButton.click(); 
							test.log(LogStatus.INFO, "Closed audio player");
					 }
					 catch(Exception e) {
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
					 }
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to tray details");					 
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Play button of the Audio");
				 }
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Audio 2");
		 }
		//Wait for 3 minutes
		 for(int wait1=0;wait1<=3;wait1++) {
			 for(int wait=0;wait<=60;wait++) {
				 Thread.sleep(1000);
			 }
		 }
		test.log(LogStatus.INFO, "Waited for 3 minutes");
		try {
			driver.closeApp();
		} 
		catch (Exception e) {}
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKbetaSplashActivity"));
		
		homepagev2.tabClick("Listen");
		homepagev2.tabClick("Listen");
		String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
	    	//audio2=lastViewed1;
	    	if(lastViewed1.equals(audio2)) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest audio under last viewed in Audio tab");
	    		//Verify complete resume
	    		try {
	    			homepagev2.lastViewedFirstBookTitle.click();
	    			if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 30)) {
		   				try {
		   					listenpagev2.play_btn.click();
		   					test.log(LogStatus.INFO, "Clicked on PLAY button");
		   					homepagev2.verifyAndProgressBar();
		   					kidsplayerv2.pauseAudio();
							try {
								 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
								 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
								 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed);
								 test.log(LogStatus.INFO, "Duration played in Seconds: "+durationPlayedInt);
								 completelyWatchedTime=durationPlayedInt;
								 if(completelyWatchedTime<=30) {
									 test.log(LogStatus.INFO, "The completely played audio is assumed to have begun from beginning since captured time is below 30 seconds");
									 test.log(LogStatus.PASS, "Verify the playback status for Completely watched Audiobook content is Pass");
									 if(!Utilities.setResultsKids("VK_1682", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 else {
									 test.log(LogStatus.FAIL, "The completely played audio is assumed to NOT have begun from beginning since captured time is above 30 seconds");
									 test.log(LogStatus.FAIL, "Verify the playback status for Completely watched Audiobook content is Fail");
									 if(!Utilities.setResultsKids("VK_1682", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to fetch last player current duration"); 
							}	
		   				}
		   				catch(Exception e) {
		   					test.log(LogStatus.INFO, "Failed to click PLAY button");
		   				}
	    			}
	    		}
	    		catch(Exception e) {
	    			test.log(LogStatus.FAIL, "Failed to play episode that was played completely previously");
	    		}	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last viewed in My Stuff tab instead of "+audio2);
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 1 is not visible under last viewed in Audio tab");
	    }
	    driver.navigate().back();
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
	    	//audio1=lastViewed2;
	    	if(lastViewed2.equals(audio1)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest audio under last viewed in Audio tab");
	    		//Verify partial resume
	    		try {
	    			homepagev2.lastViewedSecondBookTitle.click();
	    			if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 30)) {
		   				try {
		   					listenpagev2.play_btn.click();
		   					test.log(LogStatus.INFO, "Clicked on PLAY button");
		   					homepagev2.verifyAndProgressBar();
							kidsplayerv2.pauseAudio();
							try {
								 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
								 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
								 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed);
								 test.log(LogStatus.INFO, "Duration played in Seconds: "+durationPlayedInt);
								 partiallyWatchedSecondTime=durationPlayedInt;
								 if(partiallyWatchedSecondTime>partiallyWatchedFirstTime) {
									 test.log(LogStatus.INFO, "The partially played audio is assumed to have resumed from where it was paused");
									 test.log(LogStatus.PASS, "Verify the playback status for partially watched Audiobook content in Last viewed tray is Pass");
									 if(!Utilities.setResultsKids("VK_1681", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 else {
									 test.log(LogStatus.FAIL, "The partially played audio is assumed to not have resumed from where it was paused");
									 test.log(LogStatus.FAIL, "Verify the playback status for partially watched Audiobook content in Last viewed tray is Fail");
									 if(!Utilities.setResultsKids("VK_1681", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to fetch last player current duration"); 
							}	
		   				}
		   				catch(Exception e) {
		   					test.log(LogStatus.INFO, "Failed to click PLAY button");
		   				}
	    			}
	    		}
	    		catch(Exception e) {
	    			test.log(LogStatus.FAIL, "Failed to play episode that was played completely previously");
	    		}	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest audio under last viewed in Audio tab instead of "+audio1);
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 2 is not visible under last viewed in Audio tab");
	    }
	    
		//Wait for 4 minutes
		 for(int wait1=0;wait1<=3;wait1++) {
			 for(int wait=0;wait<=60;wait++) {
				 Thread.sleep(1000);
			 }
		 }
		test.log(LogStatus.INFO, "Waited for 3 minutes");
	    
		try {
			driver.closeApp();
		} 
		catch (Exception e) {}
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKbetaSplashActivity"));
		
		homepagev2.tabClick("Listen");
		homepagev2.tabClick("Listen");
		recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
	    	if(lastViewed1.equals(audio1)) {
	    		test.log(LogStatus.PASS, "Verify if the Audio cards are added to the 1st position Last viewed tray if user plays the same content again from LV is Pass");
	    		if(!Utilities.setResultsKids("VK_1905", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, "Verify if the Audio cards are added to the 1st position Last viewed tray if user plays the same content again from LV is Fail");
	    		if(!Utilities.setResultsKids("VK_1905", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 1 is not visible under last viewed in Audio tab");
	    }

		homepagev2.mystuff_tab=null;
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
