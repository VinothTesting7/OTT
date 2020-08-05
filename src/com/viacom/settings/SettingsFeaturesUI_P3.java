package com.viacom.settings;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class SettingsFeaturesUI_P3 extends BaseTestV2 {

String testName = "SettingsFeaturesUI_P3";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsFeaturesUI_P3");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		WatchPageV2 watchPage = new WatchPageV2(driver, test);
		DownloadsPageV2 downloads = new DownloadsPageV2(driver, test);
		ListenPageV2 listenPage = new ListenPageV2(driver, test);
		
		
		String userName = data.get("Email");
		String password = data.get("Password");

		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		
		// VK_2166 - Verify the functionality of Settings icon present in Parent Zone:
		//VK_2157 - Verify the UI of Help screen:
		
		// VK_2151 - Verify Back arrow icon functionality present in Switch Profile screen:
		
		// click on profile pic icon in home page
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Clicked on profile icon in home page");
			settingsPageV2.putBackGroundApp3();
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.bckBtnSwitchProfile, 50)) {
				settingsPageV2.bckBtnSwitchProfile.click();
				if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
					test.log(LogStatus.PASS, "Verify Back arrow icon functionality present in Switch Profile screen:");
					if(!Utilities.setResultsKids("VK_2151", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} else {
					test.log(LogStatus.FAIL, "Verify Back arrow icon functionality present in Switch Profile screen:");
					BasePageV2.takeScreenshot();
					if(!Utilities.setResultsKids("VK_2151", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				
			}else BasePageV2.reportFail("Failed to click Back button in Switch Profile screen / Not displayed");

		}else BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
	
			
		// VK_2166 - Verify the functionality of Settings icon present in Parent Zone:
		
		//  click on Profile icon in Home page
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			homepagev2.profilepic.click();
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 30)) {
				settingsPageV2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 30)) {
					Thread.sleep(1000);
					settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(30000);
					// putting App in background
					 settingsPageV2.putBackGroundApp3();
					// click on Settings Icon in Parent Zone page
					 if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 50)) {
							settingsPageV2.settingsIcon.click();
						 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
						 Utilities.verticalSwipe(driver);
						 settingsPageV2.putBackGroundApp3();
						 if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsHelpSupport, 50)) {
							 test.log(LogStatus.PASS, "Verify the functionality of Settings icon present in Parent Zone:");
								if(!Utilities.setResultsKids("VK_2166", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							} else {
								test.log(LogStatus.FAIL, "Verify the functionality of Settings icon present in Parent Zone:");
								BasePageV2.takeScreenshot();
								if(!Utilities.setResultsKids("VK_2166", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}	
					 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
				}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
			
		}else {
			BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");
		}
		
		
		//VK_2157 - Verify the UI of Help screen:
		
		int helpSuportError = 0;
		
		 if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsHelpSupport, 50)) {
				settingsPageV2.settingsHelpSupport.click();
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpAndSupportPageTile, 50)) {
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsHelpSupportBackbtn, 20)) {
						test.log(LogStatus.INFO, "Displayed back button in help&Support screen");
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.contactUs, 20)) {
							test.log(LogStatus.INFO, "Displayed Contact Us option in Help&Support screen ");
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpFAQs, 20)) {
								test.log(LogStatus.INFO, "Displayed FAQ's option in Help&Support screen ");
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpTemsConditions, 20)) {
									test.log(LogStatus.INFO, "Displayed Terms&conditions option in Help&Support screen ");
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpPrivacyPolicy, 20)) {
										test.log(LogStatus.INFO, "Displayed Privacy policy option in Help&Support screen ");
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpAppVersion, 20)) {
											test.log(LogStatus.INFO, "Displayed 'App Version' option in Help&Support screen ");
						
										}else {
											test.log(LogStatus.FAIL, "Not displayed 'App Version' option in Help&Support screen");
											helpSuportError++;
											BasePageV2.takeScreenshot();
										}
					
									}else {
										test.log(LogStatus.FAIL, "Not displayed Privacy policy option in Help&Support screen");
										helpSuportError++;
										BasePageV2.takeScreenshot();
									}
							
								}else {
									test.log(LogStatus.FAIL, "Not displayed Terms&Conditions option in Help&Support screen");
									helpSuportError++;
									BasePageV2.takeScreenshot();
								}

								
							}else {
								test.log(LogStatus.FAIL, "Not displayed FAQ's option in Help&Support screen");
								helpSuportError++;
								BasePageV2.takeScreenshot();
							}
							
						}else {
							test.log(LogStatus.FAIL, "Not displayed Contact Us option in Help&Support screen");
							helpSuportError++;
							BasePageV2.takeScreenshot();
						}
			
					}else {
						test.log(LogStatus.FAIL, "Not displayed back botton in Help&Support screen");
						helpSuportError++;
						BasePageV2.takeScreenshot();
					}
					
				}else {
					test.log(LogStatus.FAIL, "Does not navigated to Help&Support screen");
					BasePageV2.takeScreenshot();
					helpSuportError++;
				}
			}else {
				test.log(LogStatus.FAIL, "Not displayed Help&Support option in settings screen / Not click");
				BasePageV2.takeScreenshot();
				helpSuportError++;
			}
		
		
		 if(helpSuportError == 0) {
			 test.log(LogStatus.PASS, "Verify the UI of Help screen:");
				if(!Utilities.setResultsKids("VK_2157", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else {
				test.log(LogStatus.FAIL, "Verify the UI of Help screen:");
				BasePageV2.takeScreenshot();
				if(!Utilities.setResultsKids("VK_2157", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}	
		
		 
		
		 
		 
		 
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	
	
}
