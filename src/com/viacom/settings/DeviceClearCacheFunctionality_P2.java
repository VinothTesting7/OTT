package com.viacom.settings;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

public class DeviceClearCacheFunctionality_P2 extends BaseTestV2 {

String testName = "DeviceClearCacheFunctionality_P2";
	
	@Test(dataProvider = "getData")
	public void deviceClearCacheFunctionality_P2(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("DeviceClearCacheFunctionality_P2");
	test.log(LogStatus.INFO, "Starting the test for Verifying the DeviceClearCacheFunctionality_P2 : "+VootConstants.DEVICE_NAME);
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
	  
	    
//		homepagev2.login(un, pwd);
		
		// VK_895 - Verify the tap functionality of Clear Cache option in device settings
		// VK_896 - Verify 'Clear Now' button functionality:
		
		
		
		
		
		// VK_895 - Verify the tap functionality of Clear Cache option in device settings
		
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 80)) {
			homepagev2.profilepic.click();
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, settingsPage.parentZoneButton, 30)) {
				settingsPage.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settingsPage.parentPinContainer, 30)) {
					Thread.sleep(1000);
					settingsPage.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(30000);
					// putting App in background
					settingsPage.putBackGroundApp3();
					// click on Settings Icon in Parent Zone page
					 if (Utilities.explicitWaitVisible(driver, settingsPage.settingsIcon, 80)) {
						 settingsPage.settingsIcon.click();
						 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
						 
						 // click on Device Settings Option
						 if(Utilities.explicitWaitClickable(driver, settingsPage.settingsDevice, 50)) {
							 settingsPage.settingsDevice.click();
							 test.log(LogStatus.INFO, "click on settings Device option");
							 try {
								//scroll to Clear Cache
								 String end = "//android.widget.TextView[@text='Clear Cache' or @text='clear cache' or @text='CLEAR CACHE']";
								 Utilities.verticalSwipe(driver, end);
                                 Utilities.verticalSwipe(driver);
							} catch (Exception e) {
								// TODO: handle exception
							}
							

							 if(Utilities.explicitWaitClickable(driver, settingsPage.deviceClearCacheWithToggleButton, 50)) {
								 settingsPage.deviceClearCacheWithToggleButton.click();
							 	test.log(LogStatus.INFO, "Found Clear Cache option In Device screen ");
		                        if(Utilities.explicitWaitVisible(driver, settingsPage.clearcacheBodyMsg, 20)) {
		                        	test.log(LogStatus.PASS, "Verify the tap functionality of Clear Cache option in device settings");
		            				if(!Utilities.setResultsKids("VK_895", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		                        }else {
		                        	test.log(LogStatus.FAIL, "Verify the tap functionality of Clear Cache option in device settings");
		            				if(!Utilities.setResultsKids("VK_895", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		                        }
							 	
		                        // VK_896 - Verify 'Clear Now' button functionality:
		                         int clearError = 0;
		                         if(Utilities.explicitWaitVisible(driver, settingsPage.deviceClearCache, 20)) {
		                        	 test.log(LogStatus.INFO, "Displayed the header Title 'Clear Cache' on clear cache pop up");
		                        	   if(Utilities.explicitWaitVisible(driver, settingsPage.clearcacheBodyMsg, 20)) {
		                        		   test.log(LogStatus.INFO, "Displayed the body message 'Are You Sure You Want to Clear caches data ?' on clear cache pop up");
		                        		 if(Utilities.explicitWaitVisible(driver, settingsPage.clearCachepopupYESbtn, 20)) {
		                        			 test.log(LogStatus.INFO, "Displayed clear cache 'YES' button on clear cache pop up");
		                        			 if(Utilities.explicitWaitVisible(driver, settingsPage.clearCachepopupNObtn, 20)) {
		                        				 test.log(LogStatus.INFO, "Displayed clear cache 'NO' button on clear cache pop up");
		                        				 settingsPage.prodelPopNoBtn.click();
		                        			 }else {
		                        				 clearError++;
		                        				 test.log(LogStatus.INFO, "Not Displayed clear cache 'NO' button on clear cache pop up");
		                        			 }
		                        		 }else {
		                        			 clearError++;
		                        			 test.log(LogStatus.FAIL, "Not displayed the clear cache 'YES' button on clear cache pop up");
		                        		 }
		                        	 }else {
		                        		 clearError++;
		                        		 test.log(LogStatus.FAIL, "Not displayed the body message on clear cache pop up");
		                        	 }
		                         }else {
		                        	 clearError++;
		                        	 test.log(LogStatus.FAIL, "Not Displayed the header Title 'Clear Cache' on clear cache pop up ");
		                         }
		                        
		                        if(clearError == 0) {
		                        	test.log(LogStatus.PASS, "Verify 'Clear Now' button functionality:");
		            				if(!Utilities.setResultsKids("VK_896", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		                        }else {
		                        	test.log(LogStatus.FAIL, "Verify 'Clear Now' button functionality:");
		            				if(!Utilities.setResultsKids("VK_896", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		                        }
		                      
							 	
							 }else test.log(LogStatus.FAIL, "Not found Clear Cache option in Device Screen");

						 }else BasePageV2.reportFail("Failed to click on settings Device option in settings main page");
					 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
				}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		}else BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");
		
		
		
		
		
/*	 	if(Utilities.explicitWaitClickable(driver,settingsPage.prodelPopYesBtn, 60)) {
	 		settingsPage.prodelPopYesBtn.click();
	 		test.log(LogStatus.INFO, "clicked on Device Clear Cache option pop up Yes button");
	 		Thread.sleep(15000);
	 		settingsPage.putBackGroundApp3();
	 		if(Utilities.explicitWaitVisible(driver, settingsPage.settingsDeviceTile, 60)) {
	 			test.log(LogStatus.INFO, "Naviagted to device settings screen");
	 		}else {
	 			BasePageV2.reportFail("Not able Validate clear cache option functionality ");
	 		}
	 		
	 	}else BasePageV2.reportFail("Not displayed delete pop up yes button");
		*/
		
		
	}	
	
	 @DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					

		}	
	
}
