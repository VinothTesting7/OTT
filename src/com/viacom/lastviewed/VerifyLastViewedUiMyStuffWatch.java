package com.viacom.lastviewed;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author Tanisha
public class VerifyLastViewedUiMyStuffWatch extends BaseTestV2{
	
	String testName = "VerifyLastViewedUiMyStuffWatch";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedEpisodes(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedUiMyStuffWatch");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		// VK_161 = Verify the UI of 'Last Viewed' section in My Stuff Tab
		// VK_164 = Verify the type of cards available under 'Last Viewed' section
		// VK_1530 = Verify the functionality by tapping on any Tray title 
		// VK_1498 = Verify the position of 'Last Viewed' tray in Watch Tab
		// VK_1499 = Verify the UI of 'Last Viewed' section in Watch Tab
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 EbooksPageV2 ebookspagev2=new EbooksPageV2(driver,test);
		 
		//Login module 
		//homepagev2.logout();
		//homepagev2.login(data.get("Email"),data.get("Password"));
		 int err164=0;
		 int err161=0;
		 int err1530=0;
		 int err1498=0;
		 int err1499=0;
		 String ep1title="";
		 String ep1desc="";
		 String book1title="";
		 String book1desc="";
		 String audio1title="";
		 String audio1desc="";
		 boolean foundTrayWatch=false;
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		test.log(LogStatus.INFO, "-------------------- Play Episode --------------------");
		//Click on Watch tab
		 homepagev2.tabClick("watch");
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstEpisodeInCarousalDesc, 10)) {
			 try {
				 ep1title=watchpagev2.firstEpisodeInCarousalName.getAttribute("text");
				 ep1desc=watchpagev2.firstEpisodeInCarousalDesc.getAttribute("text");
				 watchpagev2.firstEpisodeInCarousalDesc.click();
				 test.log(LogStatus.INFO, "Clicked on first episode in Watch Carousal "+ep1title);
				 homepagev2.verifyAndProgressBar();
				 test.log(LogStatus.INFO, "Player is displayed");
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Navigated back to Watch tab");
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on first episode in Watch Carousal");
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "First episode in Watch Carousal is not clickable");
		 }
		 test.log(LogStatus.INFO, "-------------------- Read Book --------------------");
		//Click on Read tab
		 homepagev2.tabClick("Read");
		 boolean buttonClicked=false;
		 if(Utilities.explicitWaitClickable(driver, readpagev2.firstBookInCarousalDesc, 10)) {
			 try {
				 book1title=readpagev2.firstBookInCarousalName.getAttribute("text");
				 book1desc=readpagev2.firstBookInCarousalDesc.getAttribute("text");
				 readpagev2.firstBookInCarousalDesc.click();
				 test.log(LogStatus.INFO, "Clicked on first book in Read Carousal "+book1title);
				 if(Utilities.explicitWaitClickable(driver, readpagev2.readButton, 30)) {
					 try {
						 readpagev2.readButton.click();
						 test.log(LogStatus.INFO, "Clicked on READ button");
						 buttonClicked=true;
					 }
					 catch(Exception e) {}
				 }
				 else if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 30)) {
					 try {
						 readpagev2.previewButton.click();
						 test.log(LogStatus.INFO, "Clicked on PREVIEW button");
						 buttonClicked=true;
						 Thread.sleep(3000);
					 }
					 catch(Exception e) {}
				 }
				 else {
					 test.log(LogStatus.FAIL, "Read/Preview button not clickable");
				 }
				 if(buttonClicked==true) {
					 readpagev2.dismissReaderCoachCards();
					 openReader();
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to book details page");
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to Read tab");
				 }	 
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on first book in Read Carousal");
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "First book in Read Carousal is not clickable");
		 }
		 test.log(LogStatus.INFO, "-------------------- Play Audio --------------------");
		//Click on Audio tab
		 homepagev2.tabClick("Listen");
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstAudioInCarousalDesc, 10)) {
			 try {
				 audio1title=listenpagev2.firstAudioInCarousalName.getAttribute("text");
				 audio1desc=listenpagev2.firstAudioInCarousalDesc.getAttribute("text");
				 listenpagev2.firstAudioInCarousalName.click();
				 test.log(LogStatus.INFO, "Clicked on first audio in Audio Carousal "+audio1title);
				 try {
					 listenpagev2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button");
					 homepagev2.verifyAndProgressBar();
					 test.log(LogStatus.INFO, "Player is displayed");
					 try {
						 Thread.sleep(2000);
						 listenpagev2.playerCloseButton.click();
						 test.log(LogStatus.INFO, "Closed Audio Player");
					 }
					 catch(Exception e) {
						 test.log(LogStatus.INFO, "Failed to click on Close of Audio Player");
						 driver.navigate().back();
						 test.log(LogStatus.INFO, "Navigated back to Audio details");
					 }			
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to Audio tab");
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to play Audio");
				 }	 
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on first audio in Audio Carousal");
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "First audio in Audio Carousal is not clickable");
		 }
		//Wait for 3 minutes
		for(int wait1=0;wait1<=3;wait1++) {
			 for(int wait=0;wait<=60;wait++) {
				 Thread.sleep(1000);
			 }
		}
		test.log(LogStatus.INFO, "-------------------- Verify  Last Viewed in My Stuff tab --------------------");
		//Verify the presence of icons in My Stuff carousal
		try {
			homepagev2.tabClick("My Stuff");
		}
		catch(Exception e) {}
		boolean foundLVClear=false;
		for(int swipe=0;swipe<=20;swipe++) {
			if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClear, 20)) {
				foundLVClear=true;
				//Verify LAST VIEWED tray title
				 if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedTray, 5)) {
					 if(homepagev2.recentViewedTray.getAttribute("text").equalsIgnoreCase("LAST VIEWED")) {
						 test.log(LogStatus.INFO, "LAST VIEWED tray title is displayed with Clear button");
					 }
					 else {
						 test.log(LogStatus.FAIL, "LAST VIEWED tray title is not displayed with Clear button");
						 err161++;
					 }
				 }
				 else {
					 test.log(LogStatus.FAIL, "LAST VIEWED tray title is not displayed");
					 err161++;
				}
				 //Verify functionality of tray title
				 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTrayTitle, 5)) {
					 try {
						 homepagev2.lastViewedTrayTitle.click(); 
						 test.log(LogStatus.INFO, "Clicked on LAST VIEWED tray title"); 
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to click LAST VIEWED tray title"); 
						 err1530++;
					 }
					 if(homepagev2.backButton.isDisplayed()) {
						 test.log(LogStatus.INFO, "Back button is displayed");
						 try {
							 driver.findElement(By.xpath("//android.widget.TextView[@text='LAST VIEWED' or @text='Last Viewed' or @text='last viewed']"));
							 test.log(LogStatus.INFO, "LAST VIEWED page title is displayed");
							 driver.navigate().back();
							 Utilities.verticalSwipeReverse(driver);
							 Utilities.verticalSwipeReverse(driver);
							 Utilities.verticalSwipeReverse(driver);
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Failed to locate list page title LAST VIEWED");
							 err1530++;
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Back button is not displayed");
						 err1530++;
					 }
	 
				}
				else {
					 test.log(LogStatus.FAIL, "LAST VIEWED tray title is not clickable");
					 err1530++;
				}	
				if(err1530==0) {
					test.log(LogStatus.PASS, "Verify the functionality by tapping on any Tray title is PASS");
					if(!Utilities.setResultsKids("VK_1530", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL, "Verify the functionality by tapping on any Tray title is FAIL");
					if(!Utilities.setResultsKids("VK_1530", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				Utilities.verticalSwipe(driver);
				break;
			}
			else {
				Utilities.verticalSwipe(driver);
				if(swipe==20) {
					test.log(LogStatus.FAIL, "Failed to find Clear button of Last Viewed");
				}
			}
		}
		if(foundLVClear==true) {
			try {
				audio1title=homepagev2.convertCamelCase(audio1title);
				audio1title=audio1title.split("'")[0];
				audio1desc=homepagev2.convertCamelCase(audio1desc);
				audio1desc=audio1desc.split("'")[0];
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,\""+audio1title+"\") or contains(@text,\""+audio1desc+"\")]//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/category_icon' or @resource-id='com.viacom18.vootkids:id/grid_item_category']"));
				test.log(LogStatus.INFO, "Located media type icon for audio "+audio1title);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to locate the media type icon for audio "+audio1title);
				err164++;
				err161++;
			}
			try {
				book1title=homepagev2.convertCamelCase(book1title);
				book1title=book1title.split("'")[0];
				book1desc=homepagev2.convertCamelCase(book1desc);
				book1desc=book1desc.split("'")[0];
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,\""+book1title+"\") or contains(@text,\""+book1desc+"\")]//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/category_icon' or @resource-id='com.viacom18.vootkids:id/grid_item_category']"));
				test.log(LogStatus.INFO, "Located media type icon for book "+book1title);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to locate the media type icon for book "+book1title);
				err164++;
				err161++;
			}
			try {
				ep1title=homepagev2.convertCamelCase(ep1title);
				ep1title=ep1title.split("'")[0];
				ep1desc=homepagev2.convertCamelCase(ep1desc);
				ep1desc=ep1desc.split("'")[0];
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,\""+ep1title+"\") or contains(@text,\""+ep1desc+"\")]//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/category_icon' or @resource-id='com.viacom18.vootkids:id/grid_item_category']"));
				test.log(LogStatus.INFO, "Located media type icon for episode "+ep1title);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to locate the media type icon for episode "+ep1title);
				err164++;
				err161++;
			}
		}
		BasePageV2.takeScreenshot();
		if(err164==0) {
			test.log(LogStatus.PASS, "Verify the type of cards available under 'Last Viewed' section is PASS");
			if(!Utilities.setResultsKids("VK_164", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available under 'Last Viewed' section is FAIL");
			if(!Utilities.setResultsKids("VK_164", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		//Final result verification
		if(err161==0) {
			test.log(LogStatus.PASS, "Verify the UI of 'Last Viewed' section in My Stuff Tab is PASS");
			if(!Utilities.setResultsKids("VK_161", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the UI of 'Last Viewed' section in My Stuff Tab is FAIL");
			if(!Utilities.setResultsKids("VK_161", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
		}	
		for(int swipe=0;swipe<=10;swipe++) {
			Utilities.verticalSwipeReverse(driver);
		}

		test.log(LogStatus.INFO, "-------------------- Verify  Last Viewed in Watch tab --------------------");
		homepagev2.tabClick("Watch");
		boolean foundLiveNow=false;
		boolean foundLastViewed=false;
		int scrollTimes=0;
		while(true) {
			//Search for Live Now and Last Viewed tray
			if(Utilities.explicitWaitVisible(driver, watchpagev2.livenowTab, 1)) {
				foundLiveNow=true; 
				if(foundLiveNow==true && foundLastViewed==false) {
					test.log(LogStatus.INFO, "Found Live Now tab");
					break;
				}
			}
			if(Utilities.explicitWaitVisible(driver, watchpagev2.lastviewedTray, 1)) {
				foundLastViewed=true;
				if(foundLiveNow==false && foundLastViewed==true) {
					test.log(LogStatus.FAIL, "Found Last Viewed tray before Live Now tab");
					test.log(LogStatus.FAIL, "Verify the position of 'Last Viewed' tray in Watch Tab is FAIL");
					if(!Utilities.setResultsKids("VK_1498", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
					foundTrayWatch=true;
					break;
				}
			}
			Utilities.verticalSwipe(driver);
			scrollTimes++;
			if(scrollTimes==30) {
				test.log(LogStatus.FAIL, "Failed to find Live Now tab or Last Viewed tray");
				test.log(LogStatus.FAIL, "Verify the position of 'Last Viewed' tray in Watch Tab is FAIL");
				if(!Utilities.setResultsKids("VK_1498", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				BasePageV2.takeScreenshot();
				break;
			}
		}
		if(foundLiveNow==true && foundLastViewed==false) {
			test.log(LogStatus.INFO, "Searching for Last Viewed tray...");
			scrollTimes=0;
			while(true) {
				scrollTimes++;
				if(Utilities.explicitWaitVisible(driver, watchpagev2.lastviewedTray, 1)) {
					test.log(LogStatus.INFO, "Found Last Viewed after Live Now");
					foundTrayWatch=true;
					test.log(LogStatus.PASS, "Verify the position of 'Last Viewed' tray in Watch Tab is PASS");
					if(!Utilities.setResultsKids("VK_1498", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					Utilities.verticalSwipe(driver);
					if(scrollTimes==10) {
						test.log(LogStatus.FAIL, "Failed to find Last Viewed tray after Live Now");
						test.log(LogStatus.FAIL, "Verify the position of 'Last Viewed' tray in Watch Tab is FAIL");
						if(!Utilities.setResultsKids("VK_1498", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						BasePageV2.takeScreenshot();
						break;
					}
				}	
			}
		}
		if(foundTrayWatch==true) {
			//Verify LAST VIEWED tray title
			if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedTrayTitle, 5)) {
				if(homepagev2.lastViewedTrayTitle.getAttribute("text").equalsIgnoreCase("LAST VIEWED"))
					test.log(LogStatus.INFO, "LAST VIEWED tray title is displayed");
				else {
					test.log(LogStatus.FAIL, "LAST VIEWED tray title is not displayed");
					err1499++;
				}
			}
			else {
				test.log(LogStatus.FAIL, "LAST VIEWED tray title is not displayed");
				err1499++;
			}	 
			 //Verify Clear button
			 if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClear, 3)) {
				 if(homepagev2.recentViewedClear.getAttribute("text").equals("Clear"))
					 test.log(LogStatus.INFO, "Clear button is displayed");
				 else {
					 test.log(LogStatus.FAIL, "Clear button is not displayed");
					 err1499++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Clear button is not displayed");
				 err1499++;
			 }
			Utilities.verticalSwipe(driver); 
			try {
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,\""+ep1title+"\") or contains(@text,\""+ep1desc+"\")]//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/category_icon' or @resource-id='com.viacom18.vootkids:id/grid_item_category']"));
				test.log(LogStatus.INFO, "Located media type icon for episode "+ep1title);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to locate the media type icon for episode "+ep1title);
				err1499++;
			}
			//Final verification
			if(err1499==0) {
				test.log(LogStatus.PASS, "Verify the UI of 'Last Viewed' section in Watch Tab is PASS");
				if(!Utilities.setResultsKids("VK_1499", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			else {
				test.log(LogStatus.FAIL, "Verify the UI of 'Last Viewed' section in Watch Tab is FAIL");
				if(!Utilities.setResultsKids("VK_1499", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				BasePageV2.takeScreenshot();
			}
		}
		else {
			test.log(LogStatus.SKIP, "Verify the UI of 'Last Viewed' section in Watch Tab is Blocked to test");
			if(!Utilities.setResultsKids("VK_1499", "Unable to Test")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		}
	}
	
	public void openReader() throws InterruptedException {
		boolean readerDisplayed=false;
		 for(int wait=0;wait<=30;wait++) {
			 try {
				 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
				 //Add 1447
				 if(wait==30) {
					 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to book details page");
				 }
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "Loader is not displayed");
				 Set<String> CHS = driver.getContextHandles();
				 for(String ch:CHS){
					if(ch.contains("WEBVIEW")){
						test.log(LogStatus.INFO, "eBook Reader is displayed");
						readerDisplayed=true;
						break;
					}
				 }  
			 }	
			 if(readerDisplayed==true)
				 break;
			 else
				 Thread.sleep(1000);
		 }	
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
