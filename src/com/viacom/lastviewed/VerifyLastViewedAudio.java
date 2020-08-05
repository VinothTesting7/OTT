package com.viacom.lastviewed;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

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
//Author Tanisha
public class VerifyLastViewedAudio extends BaseTestV2{
	
	String testName = "VerifyLastViewedAudio";
	@Test(dataProvider = "getData")
	public void VerifyLastReadBooks(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedAudio");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}		
		
		//VK_1519 = Verify availability of 'Last Viewed' section
		//VK_1520 = Verify the position of 'Last Viewed' tray in Listen Tab
		//VK_1521 = Verify the UI of 'Last Viewed' section in Listen Tab
		//VK_1678 = Verify if the Audiobook cards are added to the Last viewed tray if user launches and closes the audio player
		//VK_1679 = Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio partially
		//VK_1680 = Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio Completely


		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
			//Login module 
			//homepagev2.logout();
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 String audio1="";
		 String audio2="";
		 String audio3="";
		 String lastViewed1,lastViewed2,lastViewed3="";
		 int errVK_1521=0;
		 int errVK_1678=0;
		 

		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 
		//Click on Read tab
		 homepagev2.tabClick("Listen");
		 homepagev2.tabClick("Listen");
		//Scroll to any books 
		 for(int scroll=0;scroll<=1;scroll++) {
			 Utilities.verticalSwipe(driver); 
		 }
		 Thread.sleep(2000);
		 String audioLocs="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
		 List<WebElement> audios=driver.findElementsByXPath(audioLocs);
		 try {
			 audio1=audios.get(0).getAttribute("text");
			 audio2=audios.get(1).getAttribute("text"); 
			 audio3=audios.get(2).getAttribute("text"); 
		 }
		 catch(Exception e) {
			test.log(LogStatus.INFO, "Failed to fetch audio names"); 
		 }
		 //Audio 1 to launch and close
		 test.log(LogStatus.INFO, "-----------------Audio 1 to launch and close-----------------");
		 boolean playerDisplayed=false;
		 try {
			 driver.findElement(By.xpath("//android.widget.TextView[@text='"+audio1+"']")).click();
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 30)) {
				 try {
					 listenpagev2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button of audio 1: "+audio1);
					 for(int wait=0;wait<=30;wait++) {
						 homepagev2.verifyAndProgressBar();
						 try {
							 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettings, 10))
							 test.log(LogStatus.INFO, "Audio Player is displayed");
							 playerDisplayed=true;
							 try {
								 listenpagev2.playerCloseButton.click();
								 test.log(LogStatus.INFO, "Closed player");
							 }
							 catch(Exception e) {
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
							 }
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio tab");
						 }
						 catch(Exception e) {
							 if(wait==30) {
								 test.log(LogStatus.FAIL, "Player failed to display");
								 break;
							 }
								 
						 }	
						 if(playerDisplayed==true)
							 break;
						 else
							 Thread.sleep(2000);
					 }						 
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
		 test.log(LogStatus.INFO, "----------------Audio 2 to play partially------------------");
		 driver.navigate().back();
		 test.log(LogStatus.INFO, "Navigated back to Audio tab");
		 playerDisplayed=false;
		 try {
			 driver.findElement(By.xpath("//android.widget.TextView[@text='"+audio2+"']")).click();
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 30)) {
				 try {
					 listenpagev2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button of audio 2: "+audio2);
					 for(int wait=0;wait<=30;wait++) {
						 homepagev2.verifyAndProgressBar();
						 try {
							 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettings, 10))
							 test.log(LogStatus.INFO, "Audio Player is displayed");
							 playerDisplayed=true;
							 Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);
							 try {
								 listenpagev2.playerCloseButton.click();
								 test.log(LogStatus.INFO, "Closed player");
							 }
							 catch(Exception e) {
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
							 }
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio tab");
						 }
						 catch(Exception e) {
							 if(wait==30) {
								 test.log(LogStatus.FAIL, "Player failed to display");
								 break;
							 }
								 
						 }	
						 if(playerDisplayed==true)
							 break;
						 else
							 Thread.sleep(2000);
					 }						 
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Play button of the Audio");
				 }
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Audio 2");
		 }
		 
		 //Audio 2 to play partially
		 test.log(LogStatus.INFO, "----------------Audio 3 to play completely------------------");
		 driver.navigate().back();
		 test.log(LogStatus.INFO, "Navigated back to Audio tab");
		 playerDisplayed=false;
		 try {
			 driver.findElement(By.xpath("//android.widget.TextView[@text='"+audio2+"']")).click();
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 30)) {
				 try {
					 listenpagev2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button of audio 3: "+audio3);
					 for(int wait=0;wait<=30;wait++) {
						 homepagev2.verifyAndProgressBar();
						 try {
							 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettings, 10))
							 test.log(LogStatus.INFO, "Audio Player is displayed");
							 playerDisplayed=true;
							 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
							 try {
								 listenpagev2.playerCloseButton.click();
								 test.log(LogStatus.INFO, "Closed player");
							 }
							 catch(Exception e) {
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
							 }
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio tab");
						 }
						 catch(Exception e) {
							 if(wait==30) {
								 test.log(LogStatus.FAIL, "Player failed to display");
								 break;
							 }
								 
						 }	
						 if(playerDisplayed==true)
							 break;
						 else
							 Thread.sleep(2000);
					 }						 
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Play button of the Audio");
				 }
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Audio 2");
		 }
		//Verify last viewed in Audio tab
		test.log(LogStatus.INFO, "----------------Verify last viewed in Audio tab------------------");
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("Listen");
		homepagev2.tabClick("Listen");
		if(Utilities.explicitWaitVisible(driver, listenpagev2.lastAudioAfterCarousal, 30)) {
			test.log(LogStatus.INFO, "Last Viewed tray position is immediately below Carousal in Audio Tab");
			test.log(LogStatus.PASS, "Verify the position of 'Last Viewed' tray in Listen Tab is Pass");
			if(!Utilities.setResultsKids("VK_1520", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Last Viewed tray position is not below Carousal in Audio Tab");
			test.log(LogStatus.FAIL, "Verify the position of 'Last Viewed' tray in Listen Tab is Fail");
			if(!Utilities.setResultsKids("VK_1520", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		if(Utilities.explicitWaitVisible(driver, listenpagev2.lastAudioTitle, 20)) {
			if(listenpagev2.lastAudioTitle.getAttribute("text").equalsIgnoreCase("LAST VIEWED"))
				test.log(LogStatus.INFO, "LAST VIEWED tray title is verified");
			else {
				test.log(LogStatus.FAIL, "LAST VIEWED tray title is not displayed");
				errVK_1521++;
			}
		}
		else {
			test.log(LogStatus.FAIL, "Could not locate LAST READ tray title");
			errVK_1521++;
		}
		if(Utilities.explicitWaitVisible(driver, listenpagev2.lastAudioClear, 20)) {
			if(listenpagev2.lastAudioClear.getAttribute("text").equalsIgnoreCase("Clear"))
				test.log(LogStatus.INFO, "Clear link is verified");
			else {
				test.log(LogStatus.FAIL, "Clear link is not displayed");
				errVK_1521++;
			}
		}
		else {
			test.log(LogStatus.FAIL, "Could not locate Clear link");
			errVK_1521++;
		}
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
	    	test.log(LogStatus.INFO, "Last Viewed tray is displayed with audio card");
	    	//Final verification of VK_1510 if Pass
	    	if(errVK_1521==0) {
	    		test.log(LogStatus.PASS, "Verify the UI of 'Last Viewed' section in Read tab is Pass");
		    	if(!Utilities.setResultsKids("VK_1521", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    	}
	    	test.log(LogStatus.PASS, "Verify availability of 'Last Viewed' section is Pass");
	    	if(!Utilities.setResultsKids("VK_1519", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			

	    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
	    	if(lastViewed1.equals(audio3)) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest audio under last viewed in Audio tab");
	    		test.log(LogStatus.PASS, "Verify if the Audiobook cards are added to the Last viewed tray if user launches and closes the audio player is Pass");
	    		if(!Utilities.setResultsKids("VK_1678", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last viewed in My Stuff tab instead of "+audio3);
	    		test.log(LogStatus.FAIL, "Verify if the Audiobook cards are added to the Last viewed tray if user launches and closes the audio player is Fail");
	    		if(!Utilities.setResultsKids("VK_1678", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 1 is not visible under last viewed in Audio tab");
	    	test.log(LogStatus.FAIL, "Verify availability of 'Last Viewed' section is Fail");
	    	if(!Utilities.setResultsKids("VK_1519", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	

	    }
    	//Final verification of VK_1510
    	if(errVK_1521>0) {
    		test.log(LogStatus.FAIL, "Verify the UI of 'Last Viewed' section in Read tab is Fail");
	    	if(!Utilities.setResultsKids("VK_1510", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
    	}

	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
	    	if(lastViewed2.equals(audio2)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest audio under last viewed in Audio tab");
	    		test.log(LogStatus.PASS, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio partially is Pass");
	    		if(!Utilities.setResultsKids("VK_1679", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last viewed in My Stuff tab instead of "+audio2);
	    		test.log(LogStatus.FAIL, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio partially is Fail");
	    		if(!Utilities.setResultsKids("VK_1679", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 2 is not visible under last viewed in Audio tab");
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdBookTitle, 10)) {
	    	lastViewed3=homepagev2.lastViewedThirdBookTitle.getAttribute("text");
	    	if(lastViewed3.equals(audio1)) {
	    		test.log(LogStatus.INFO, lastViewed3+" is the third latest audio under last viewed in Audio tab");
	    		test.log(LogStatus.PASS, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio Completely is Pass");
	    		if(!Utilities.setResultsKids("VK_1680", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest audio under last viewed in Audio tab instead of "+audio1);
	    		test.log(LogStatus.FAIL, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio Completely is Fail");
	    		if(!Utilities.setResultsKids("VK_1680", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 3 is not visible under last viewed in Audio tab");
	    }
	    basepagev2.takeScreenshot();
	    
	    
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
