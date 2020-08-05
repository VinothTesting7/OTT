package com.viacom.lastviewed;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;
// Author - Suresh 
public class VerifyLastViewedTrayAudioCard_P2 extends BaseTestV2 {

String testName = "VerifyLastViewedTrayAudioCard_P2";
	
	@Test(dataProvider = "getData")
	public void verifyLastViewedTrayAudioCard_P2(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("VerifyLastViewedTrayAudioCard_P2");
	test.log(LogStatus.INFO, "Starting the test for Verifying the VerifyLastViewedTrayAudioCard_P2 : "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
	SearchPageV2 searchpage = new SearchPageV2(driver, test);
    DownloadsPageV2 downloadPage = new DownloadsPageV2(driver, test);
    WatchPageV2 watchPage = new WatchPageV2(driver, test);
    ListenPageV2 listen = new ListenPageV2(driver, test);
    
	
		String un=data.get("Email");
		String pwd=data.get("Password");
	  
	    
		homepagev2.login(un, pwd);
		
		// VK_1847 - Verify if downloaded Audiobooks are added to Last Viewed Tray without watching the same
		// VK_1853 - Verify if downloaded Ebook are added to Last Viewed Tray without reading an Ebook
		
		// clear Downloads in My Stuff page
		try {
			
			downloadPage.deleteAllDownloadsAndClickMyStuffTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// clear the Last Viewed tray content
		Utilities.verticalSwipe(driver);
		if(Utilities.explicitWaitClickable(driver, watchPage.lastViewedTrayClearLink, 20)) {
			watchPage.clearBtnLastviewed.click();
			if(Utilities.explicitWaitClickable(driver, watchPage.lastViewedYesBtn, 20)){
				watchPage.lastViewedYesBtn.click();
				test.log(LogStatus.INFO, "Clicked on 'YES' button on  Last viewed clear pop up");
				Thread.sleep(6000);
			}
		}else {
			Utilities.verticalSwipeDown(driver);
			Utilities.verticalSwipeDown(driver);
		}
		
		
		// click on Listen Tab in home page
		
		homepagev2.tabClick("Listen");
		// click on Audio card from carousal
		
		try {
			homepagev2.tabClick("Listen");
		} catch (Exception e) {
			// TODO: handle exception
		}
      // click on carousal in Audio page
        if(Utilities.explicitWaitClickable(driver, watchPage.cardinwatchcarousel, 60)) {
	    	watchPage.cardinwatchcarousel.click();
	    	test.log(LogStatus.INFO, "click on carousal in Read page");
	    	Thread.sleep(5000);
        }else {
        	test.log(LogStatus.FAIL, "Failed to click on carousal in Audio page / not displayed");
        }
    
        String audioCardTitle = "";
        try {
			
        	 audioCardTitle = listen.audioDetailsAudioName.getText().toString().trim();
        	 audioCardTitle = homepagev2.convertCamelCase(audioCardTitle);
        	 test.log(LogStatus.INFO, "Downloaded Audio card is : " + audioCardTitle);
        	Utilities.verticalSwipe(driver, watchPage.downloadEpisodesLink);
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
        
      // click on Audio download link in Audio info page
      	if(Utilities.explicitWaitClickable(driver,watchPage.downloadEpisodesLink , 50)) {
    		watchPage.downloadEpisodesLink.click();
    		test.log(LogStatus.INFO, "click on Audio download link in Audio info page");
    		if(Utilities.explicitWaitVisible(driver, downloadPage.DownloadedStatus, 120)) {
    			test.log(LogStatus.INFO, "Completed downloding the Audio card");
    		}else {
    			test.log(LogStatus.FAIL, "Not completed downloading the audio card / Not displayed");
    		}
    		driver.pressKeyCode(AndroidKeyCode.BACK);
    		Utilities.verticalSwipeReverse(driver);
    		
    	}else {
    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
    		BasePageV2.takeScreenshot();
    	}
    
    // Navigating to Downloads page
      	// click on My-Stuff tab in home page
      	try {
			homepagev2.tabClick("My Stuff");
			Utilities.verticalSwipe(driver);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(Utilities.explicitWaitVisible(driver, watchPage.lastviewedTray, 10)){
			watchPage.lastviewedTray.click();
			test.log(LogStatus.INFO, "click on Lase viewed Tray in my stuff page");
			Thread.sleep(5000);
			try {
				driver.findElement(By.xpath("//android.widget.TextView[@text='"+audioCardTitle+"' and @resource-id='com.viacom18.vootkids:id/grid_title']"));
				test.log(LogStatus.FAIL, "Verify if downloaded Audiobooks are added to Last Viewed Tray without watching the same");
				if(!Utilities.setResultsKids("VK_1847", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} catch (Exception e) {
				test.log(LogStatus.PASS, "Verify if downloaded Audiobooks are added to Last Viewed Tray without watching the same");
				if(!Utilities.setResultsKids("VK_1847", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			// Navigate to home page
			driver.navigate().back();
			Utilities.verticalSwipeReverse(driver);
			Utilities.verticalSwipeReverse(driver);
			
			
		}else {
			test.log(LogStatus.PASS, "Verify if downloaded Audiobooks are added to Last Viewed Tray without watching the same");
			if(!Utilities.setResultsKids("VK_1847", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
			Utilities.verticalSwipeDown(driver);
			Utilities.verticalSwipeDown(driver);
		}
		
		// VK_1853 - Verify if downloaded Ebook are added to Last Viewed Tray without reading an Ebook
		
		String bookCardTitle = "";
		
		// click on Read tab to download book
    	try {
    		homepagev2.tabClick("Read");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        if(Utilities.explicitWaitClickable(driver, watchPage.cardinwatchcarousel, 60)) {
        	watchPage.cardinwatchcarousel.click();
	    	test.log(LogStatus.INFO, "click on carousal in Read page");
	    	Thread.sleep(5000);
	        try {
	        	 bookCardTitle = listen.audioDetailsAudioName.getText().toString().trim();
	        	 bookCardTitle = homepagev2.convertCamelCase(bookCardTitle);
	        	 test.log(LogStatus.INFO, "Downloaded book is : " + bookCardTitle);
	        	 
	        	Utilities.verticalSwipe(driver, watchPage.downloadEpisodesLink);
	        	
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	// click on download link to downloads the book
	    	if(Utilities.explicitWaitClickable(driver,watchPage.downloadEpisodesLink , 50)) {
	    		watchPage.downloadEpisodesLink.click();
	    		test.log(LogStatus.INFO, "click on book download link in book info page");
	    		
	    		if(Utilities.explicitWaitVisible(driver, downloadPage.DownloadedStatus, 120)) {
	    			test.log(LogStatus.INFO, "Completed downloding the book");
	    		}else {
	    			test.log(LogStatus.FAIL, "Not completed downloading the book / Not displayed");
	    		}
	    
	    		driver.pressKeyCode(AndroidKeyCode.BACK);
	    		Utilities.verticalSwipeReverse(driver);
	    		
	    	}else {
	    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
	    		BasePageV2.takeScreenshot();
	    	}
	    	
	    	// click on My stuff tab in home page
	    	try {
				homepagev2.tabClick("My Stuff");
				Utilities.verticalSwipe(driver);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(Utilities.explicitWaitVisible(driver, watchPage.lastviewedTray, 10)){
				watchPage.lastviewedTray.click();
				test.log(LogStatus.INFO, "click on Lase viewed Tray in my stuff page");
				Thread.sleep(5000);
				try {
					driver.findElement(By.xpath("//android.widget.TextView[@text='"+bookCardTitle+"' and @resource-id='com.viacom18.vootkids:id/grid_title']"));
					test.log(LogStatus.FAIL, "Verify if downloaded Ebook are added to Last Viewed Tray without reading an Ebook");
					if(!Utilities.setResultsKids("VK_1853", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} catch (Exception e) {
					test.log(LogStatus.PASS, "Verify if downloaded Ebook are added to Last Viewed Tray without reading an Ebook");
					if(!Utilities.setResultsKids("VK_1853", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				// Navigate to home page
				driver.navigate().back();
				Utilities.verticalSwipeReverse(driver);
				Utilities.verticalSwipeReverse(driver);
				
				
			}else {
				test.log(LogStatus.PASS, "Verify if downloaded Ebook are added to Last Viewed Tray without reading an Ebook");
				if(!Utilities.setResultsKids("VK_1853", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
	    	

	    	
        }else {
        	test.log(LogStatus.FAIL, "Not displayed book carousal in Book page / Not click");
        	BasePageV2.takeScreenshot();
        }
    	
    	
	
  }
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}	
	
}
