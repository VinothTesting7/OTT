package com.viacom.lastviewed;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
public class VerifyLastViewedAudioSettings extends BaseTestV2{
	
	String testName = "VerifyLastViewedAudioSettings";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedAudioSettings(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedAudioSettings");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}		
		
		//P1 VK_1882 = Verify if Last viewed assets are specific to individual profile
		//P1 VK_1883 = Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device
		//P1 VK_1885 = Verify if Cards under Last viewed tray are retained when user clears device app data and logs in
		//P1 VK_1527 = Verify 'Clear' link functionality
		//P1 VK_1528 = Verify the functionality of 'Yes' option in clear link confirmation message
		//P3 VK_1529 = Verify the functionality of 'No' option in clear link confirmation message
		
		
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
		 String lastViewed1,lastViewed2="";
		 int errVK_1882=0;
		 int errVK_1883=0;
		 int errVK_1885=0;
		 int errVK_1527=0;
		 boolean foundTrayProfile1=false;
		 boolean foundTrayProfile2=false;
		 String recentViewedClear="";
	     //Login module 
		 homepagev2.logout();
		 homepagev2.login("8792396107","tanisha19");
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 //Make sure the are two profiles or else create profile
		 homepagev2.createProfileIfOnlyOnePresentAndSelectFirstProfile();
		 //Click on Listen tab
		 homepagev2.tabClick("Listen");
		 homepagev2.tabClick("Listen");
		 //Scroll to any audio 
		 for(int scroll=0;scroll<=1;scroll++) {
			 Utilities.verticalSwipe(driver); 
		 }
		 Thread.sleep(2000);
		 String audioLocs="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
		 List<WebElement> audios=driver.findElementsByXPath(audioLocs);
		 try {
			 audio1=audios.get(0).getAttribute("text");
			 audio2=audios.get(1).getAttribute("text");  
		 }
		 catch(Exception e) {
			test.log(LogStatus.INFO, "Failed to fetch audio names"); 
		 }
		 //Audio 1
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
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio details page");
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
		 //Audio 2
		 driver.navigate().back();
		 test.log(LogStatus.INFO, "Navigated back to audio tab");
		 test.log(LogStatus.INFO, "----------------Audio 2 to play partially------------------");
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
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio details page");
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
		 driver.navigate().back();
		 test.log(LogStatus.INFO, "Navigated back to audio tab");
		 //Logout and Login
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 homepagev2.logout();
		 homepagev2.login("8792396107","tanisha19");
		 if(Utilities.explicitWaitClickableNew(driver, homepagev2.allowpermissionbtn, 10)){
			 try {
				 homepagev2.allowpermissionbtn.click();
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "Failed to tap Allow button from pop up");
			 }
		 }
		 try {
			 Utilities.tap(driver);
			 Utilities.tap(driver);
			 Utilities.tap(driver);
			 Utilities.tap(driver);
		 }
		 catch(Exception e) {
			 test.log(LogStatus.INFO, "Failed to dismiss coach cards");
		 }
		 homepagev2.tabClick("My Stuff");
		 homepagev2.selectFirstProfile();
		 Thread.sleep(3000);
		 homepagev2.tabClick("Listen");
		 homepagev2.tabClick("Listen");
		 //Verify last viewed in Audio tab
		 recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		 for(int i=0;i<40;i++) {
			 try {
				 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				 driver.findElement(By.xpath(recentViewedClear));
				 foundTrayProfile1=true;
			 }
			 catch(Exception e) {
				 Utilities.verticalSwipe(driver);
				 if(i==40) {
					 foundTrayProfile1=false;
				 }
			 }
		 }
		 if(foundTrayProfile1==false) {
			 test.log(LogStatus.FAIL, "Failed to locate Last Viewed tray");
			 test.log(LogStatus.FAIL, "Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device is Fail");
	    	 if(!Utilities.setResultsKids("VK_1883", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
		 else {
			 test.log(LogStatus.INFO, "Swiped till Last viewed tray");
			 Utilities.verticalSwipe(driver);
			 Utilities.verticalSwipe(driver);
		     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
		    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
		    	if(lastViewed1.equals(audio2)) {
		    		test.log(LogStatus.INFO, lastViewed1+" is the first latest audio under last viewed in Audio tab after relogin");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last viewed in My Stuff tab instead of "+audio2+" after relogin");
		    		errVK_1883++;
		    	}
		     }
		     else {
		    	test.log(LogStatus.FAIL, "Last Viewed audio 1 is not visible under last viewed in Audio tab");
		    	errVK_1883++;
		     }
		     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
		    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
		    	if(lastViewed2.equals(audio1)) {
		    		test.log(LogStatus.INFO, lastViewed2+" is the second latest audio under last viewed in Audio tab after relogin");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last viewed in My Stuff tab instead of "+audio1);	    	
		    		errVK_1883++;
		    	}
		     }
		     else {
		    	test.log(LogStatus.FAIL, "Last Viewed audio 2 is not visible under last viewed in Audio tab");
		    	errVK_1883++;
		     }
		     if(errVK_1883==0) {
		    	test.log(LogStatus.PASS, "Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device is Pass");
	    		if(!Utilities.setResultsKids("VK_1883", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		     }
		     else {
		    	test.log(LogStatus.FAIL, "Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device is Fail");
	    		if(!Utilities.setResultsKids("VK_1883", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		     }
		 }
		 driver.runAppInBackground(Duration.ofSeconds(3));
		 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		 driver.currentActivity();
	     Thread.sleep(1000);
	     //Switch to another profile
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 homepagev2.tabClick("My Stuff");
		 homepagev2.selectSecondProfile();
		 Thread.sleep(3000);
		 homepagev2.tabClick("Listen");
		 homepagev2.tabClick("Listen");
		 recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		 for(int i=0;i<40;i++) {
			 try {
				 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				 driver.findElement(By.xpath(recentViewedClear));
				 foundTrayProfile2=true;
			 }
			 catch(Exception e) {
				 Utilities.verticalSwipe(driver);
				 if(i==40) {
					 foundTrayProfile2=false;
				 }
			 }
		 }
		 if(foundTrayProfile2==false) {
			test.log(LogStatus.INFO, "Failed to locate Last viewed tray in profile 2, so profile 1 audios are not displayed in profile 2");
			test.log(LogStatus.PASS, "Verify if Last viewed assets are specific to individual profile is Pass");
    		if(!Utilities.setResultsKids("VK_1882", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
		 else {
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
		    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
		    	if(lastViewed1.equals(audio2)) {
		    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest audio under last viewed in Audio tab same as previous profile");
		    		errVK_1882++;
		    	}
		    	else {
		    		test.log(LogStatus.INFO, lastViewed1+" is the first latest book under last viewed in My Stuff tab instead of "+audio2);	
		    	}
		    }
		    else {
		    	test.log(LogStatus.INFO, "Last Viewed audio 1 is not visible under last viewed in Audio tab");
		    }
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
		    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
		    	if(lastViewed2.equals(audio1)) {
		    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest audio under last viewed in Audio tab same as previous profile");
		    		errVK_1882++;
		    	}
		    	else {
		    		test.log(LogStatus.INFO, lastViewed2+" is the second latest book under last viewed in My Stuff tab instead of "+audio1);	    	
		    	}
		    }
		    else {
		    	test.log(LogStatus.INFO, "Last Viewed audio 2 is not visible under last viewed in Audio tab");
		    }
		    basepagev2.takeScreenshot();
		    
		    if(errVK_1882==0) {
		    	test.log(LogStatus.PASS, "Verify if Last viewed assets are specific to individual profile is Pass");
	    		if(!Utilities.setResultsKids("VK_1882", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Verify if Last viewed assets are specific to individual profile is Fail");
	    		if(!Utilities.setResultsKids("VK_1882", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    }
		}

	    //App reset
	    driver.resetApp();
		test.log(LogStatus.INFO, "Performed App Reset");
		homepagev2.loginAfterReset("8792396107","tanisha19");
		homepagev2.clearCoachCards();
		homepagev2.tabClick("My Stuff");
		homepagev2.selectFirstProfile();
		Thread.sleep(3000);
		homepagev2.tabClick("Listen");
		homepagev2.tabClick("Listen");
		foundTrayProfile1=false;
		//Verify last viewed in Audio tab
		 recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		 for(int i=0;i<40;i++) {
			 try {
				 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				 driver.findElement(By.xpath(recentViewedClear));
				 foundTrayProfile1=true;
			 }
			 catch(Exception e) {
				 Utilities.verticalSwipe(driver);
				 if(i==40) {
					 foundTrayProfile1=false;
				 }
			 }
		 }
		 if(foundTrayProfile1==false) {
			 test.log(LogStatus.FAIL, "Failed to locate Last Viewed tray");
			 test.log(LogStatus.FAIL, "Verify if Cards under Last viewed tray are retained when user clears device app data and logs in is Fail");
	    		if(!Utilities.setResultsKids("VK_1885", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
		 else {
			 test.log(LogStatus.INFO, "Swiped till Last viewed tray");
			 Utilities.verticalSwipe(driver);
			 Utilities.verticalSwipe(driver);
			 if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
			    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
			    	if(lastViewed1.equals(audio2)) {
			    		test.log(LogStatus.INFO, lastViewed1+" is the first latest audio under last viewed in Audio tab same as before app reset");
			    	}
			    	else {
			    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last viewed in My Stuff tab instead of "+audio2);
			    		errVK_1885++;
			    	}
			 }
		     else {
		    	test.log(LogStatus.FAIL, "Last Viewed audio 1 is not visible under last viewed in Audio tab");
		    	errVK_1885++;
		     }
			 if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
			    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
			    	if(lastViewed2.equals(audio1)) {
			    		test.log(LogStatus.INFO, lastViewed2+" is the second latest audio under last viewed in Audio tab same as before app reset");
			    	}
			    	else {
			    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last viewed in My Stuff tab instead of "+audio1);	    	
			    		errVK_1885++;
			    	}
			 }
			 else {
			    	test.log(LogStatus.FAIL, "Last Viewed audio 2 is not visible under last viewed in Audio tab");
			    	errVK_1885++;
			 }
			 basepagev2.takeScreenshot();
		     if(errVK_1885==0) {
		    	test.log(LogStatus.PASS, "Verify if Cards under Last viewed tray are retained when user clears device app data and logs in is Pass");
	    		if(!Utilities.setResultsKids("VK_1885", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		     }
		     else {
		    	test.log(LogStatus.FAIL, "Verify if Cards under Last viewed tray are retained when user clears device app data and logs in is Fail");
	    		if(!Utilities.setResultsKids("VK_1885", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		     }
		 }
	    
	    //Clear Last Viewed
	    //homepagev2.clearLastViewed();
	    
	    //TCS relating Clear link in Audio tab
	    if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClear, 10)) {
			try {
				homepagev2.recentViewedClear.click();
				test.log(LogStatus.INFO, "Clicked on Clear link of Last Viewed in Audio tab");
				//Verify message
				try {
					String messageUI=homepagev2.recentViewedClearMessage.getAttribute("text");
					String messageExpected="Are you sure you want to delete all last viewed items?";
					if(messageUI.equals(messageExpected)) {
						test.log(LogStatus.INFO, "Are you sure you want to delete all last viewed items? -> Is displayed");
					}
					else {
						test.log(LogStatus.FAIL, "Message displayed is-> "+messageUI);
						test.log(LogStatus.FAIL, "Expected Message is-> "+messageExpected);
						errVK_1527++;
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to verify confirmation message in popup of Last Viewed in Watch tab");
				}
				//Verify presence of YES button
				if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClearYes, 5)) {
					test.log(LogStatus.INFO, "Located YES button");
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate YES button");
					errVK_1527++;				
				}
				//Verify presence of No button
				if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClearNo, 5)) {
					test.log(LogStatus.INFO, "Located NO button");
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate NO button");
					errVK_1527++;				
				}
				//Verification of VK_1505
				if(errVK_1527==0) {
					test.log(LogStatus.PASS, "Verify 'Clear' link functionality is PASS");
					if(!Utilities.setResultsKids("VK_1527", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL, "Verify 'Clear' link functionality is FAIL");
					if(!Utilities.setResultsKids("VK_1527", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				//Verify No button functionality
				try {
					homepagev2.recentViewedClearNo.click();
					test.log(LogStatus.INFO, "Clicked on No button in the popup");
					if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
						test.log(LogStatus.INFO, "Verified first audio is still displayed");
						test.log(LogStatus.PASS, "Verify the functionality of 'No' option in clear link confirmation message is PASS");
						if(!Utilities.setResultsKids("VK_1529", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
						try {
							homepagev2.recentViewedClear.click();
							test.log(LogStatus.INFO, "Clicked on Clear link again to verify YES button functionality");
						}
						catch(Exception e) {
							test.log(LogStatus.INFO, "Failed to click on Clear link to verify YES button functionality");
						}
					}
					else {
						test.log(LogStatus.FAIL, "Unable to locate first episode");
						test.log(LogStatus.FAIL, "Verify the functionality of 'No' option in clear link confirmation message is FAIL");	
						if(!Utilities.setResultsKids("VK_1529", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
					}
					
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on No in the popup of Last Viewed in Watch tab");
				}
				//Verify Yes button functionality
				try {
					homepagev2.recentViewedClearYes.click();
					test.log(LogStatus.INFO, "Clicked on Yes button in the popup");
					Thread.sleep(7000);
					if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClear, 10)) {
						test.log(LogStatus.FAIL, "Clear button is displayed, tray is not cleared");
						if(!Utilities.setResultsKids("VK_1528", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
						test.log(LogStatus.FAIL, "Verify the functionality of 'Yes' option in clear link confirmation message is PASS");	
						BasePageV2.takeScreenshot();
					}
					else {
						test.log(LogStatus.INFO, "Clear button is not displayed, tray has been cleared");
						if(!Utilities.setResultsKids("VK_1528", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
						test.log(LogStatus.PASS, "Verify the functionality of 'Yes' option in clear link confirmation message is PASS");	
					}	
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Yes in the popup of Last Viewed in Watch tab");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on Clear link in Last Viewed in Watch tab");
			}
		}
		else {
			test.log(LogStatus.FAIL, "Clear link is not clickable in Last Viewed in Watch tab");
		}
	    
	    
	    
		homepagev2.mystuff_tab=null;
		homepagev2.lastViewedFirstItemTitle=null;
		homepagev2.lastViewedSecondItemTitle=null;
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
