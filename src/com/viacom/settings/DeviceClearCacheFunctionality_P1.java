package com.viacom.settings;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

// Author - Suresh 


public class DeviceClearCacheFunctionality_P1 extends BaseTestV2 {

String testName = "DeviceClearCacheFunctionality_P1";
	
	@Test(dataProvider = "getData")
	public void deviceClearCacheFunctionality_P1(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("DeviceClearCacheFunctionality_P1");
	test.log(LogStatus.INFO, "Starting the test for Verifying the DeviceClearCacheFunctionality_P1 : "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
	SearchPageV2 searchpage = new SearchPageV2(driver, test);

		String un=data.get("Email");
		String pwd=data.get("Password");
	  
	    
		homepagev2.login(un, pwd);
		
		
		
		// VK_897 - Verify the app behavior post selecting 'Yes' from Clear Cache confirmation message:
		
		
		// click on search icon in home page
		if (Utilities.explicitWaitVisible(driver, searchpage.Search, 50))
			searchpage.Search.click();
		else
			BasePageV2.reportFail("Search tab not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 20))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, searchpage.searchTextBox, 50)) {
			searchpage.searchTextBox.clear();
			searchpage.searchTextBox.sendKeys("Motu Patlu");
		}
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}

		Utilities.verticalSwipe(driver, searchpage.searchContent);
		Thread.sleep(2000);
		try {
			Thread.sleep(2000);
			searchpage.searchContent.click();
		} catch (Exception e) {
			BasePageV2.reportFail("Content not displayed");
		}
		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, searchpage.searchClear);
		Thread.sleep(2000);
		if (Utilities.explicitWaitVisible(driver, searchpage.searchClear, 50)) {
			searchpage.searchClear.click();
		} else
			BasePageV2.reportFail("Search Clear button not displayed");

		Utilities.verticalSwipe(driver, searchpage.RecentsearchContents);
		Thread.sleep(2000);
		
		if(Utilities.explicitWaitVisible(driver, searchpage.RecentsearchContents, 50)) {
			test.log(LogStatus.INFO, "displayed Recent Searched tray insearch page");
			driver.pressKeyCode(AndroidKeyCode.BACK);
			try {
				// click on clear cache option in Device settings screen
				settingsPage.clearCacheDeviceSettings();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// verify the Recent Searches Tray is there r not in Search page
			if (Utilities.explicitWaitVisible(driver, searchpage.Search, 50))
				searchpage.Search.click();
			else
				BasePageV2.reportFail("Search tab not displayed");
			if(Utilities.explicitWaitVisible(driver, searchpage.RecentsearchContents, 10)) {
				test.log(LogStatus.FAIL, "Verify the app behavior post selecting 'Yes' from Clear Cache confirmation message:");
				if(!Utilities.setResultsKids("VK_897", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}else {
				test.log(LogStatus.PASS, "Verify the app behavior post selecting 'Yes' from Clear Cache confirmation message:");
				if(!Utilities.setResultsKids("VK_897", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			
			
			
		}else BasePageV2.reportFail("Recent Searches tray not displayed in search page");
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	 @DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					

		}	
	
}
