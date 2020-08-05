package com.viacom.settings;

import java.time.Duration;
import java.util.HashSet;
import java.util.Hashtable;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;
//Suresh
//Modified Tanisha
public class VerifySettingsDeviceScreen extends BaseTestV2 {
  
	
	
		String testName = "VerifySettingsDeviceScreen";

		@Test(dataProvider = "getData")
		public void verifySettingsDeviceScreen(Hashtable<String, String> data) throws Exception {
			test = rep.startTest("VerifySettingsDeviceScreen");
			test.log(LogStatus.INFO, "Starting the test to Verify Settings Device Screen: " + VootConstants.DEVICE_NAME);
			// Check run mode
			if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
				throw new SkipException("Skipping the test as Run Mode was: NO");
			}
			// VK_846 Verify the UI of Device page 
			// VK_883 Verify the default toggle button state for Background Music options in device settings
			// VK_884 Verify 'Background Music' toggle button functionality
		    // VK_847 Verify Back arrow functionality in Device settings screen 
			// VK_903 Validate Help & Support link functionality
			// VK_906 Verify Back arrow functionality from Help and Support screen
			// VK_904 Verify the UI of Help & Support page
			// VK_910 Validate the functionality for 'Rate Us' link
			// VK_913 Verify the functionality for 'Rate us on the App Store/Play Store' link by clicking on 'Yes' on the pop-up message
			// VK_921 Verify the functionality for 'Rate Us' link by tapping on 'No' on the message
			// VK_924 Verify the functionality of 'send feedback' button in pop-up message
			// VK_925 Verify if user can send the feedback successfuly 
			// VK_916 Verify the functionality by tapping on 'Rate us' button in the pop-up
			// VK_899 Validate the functionality by tapping on Share option
			// VK_900 Verify the functionality of share feature by selecting any app
			// VK_926 Verify the scroll functionality in feedback text box 
						
			
			// Launching the Voot-kids App
			launchApp();
			test.log(LogStatus.INFO, "Application launched successfully");
			int err846=0;
			int err884=0;
			int err904=0;
			int err910=0;
			int err913=0;
			
//			LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
			SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
       		HomePageV2 homepagev2 = new HomePageV2(driver, test);
//			BasePageV2 BasePageV2 = new BasePageV2(driver, test);
			HashSet<String> set = new HashSet<String>();
			String end1 = "//android.widget.TextView[@text='Create New Profile']";

			// navigating to settings 'Profiles' page 

//					   HomePageV2.signup();
			
			 homepagev2.login(data.get("Email"),data.get("Password"));
			
			
			try {
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 20)) {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

				}
			} 
			catch (Exception e) {
				BasePageV2.reportFail("Not able to click on Profile Icon in Home page");
			}
			
			
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisibleNew(driver, settingsPageV2.parentZoneButton, 10)) {
				settingsPageV2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on Parent Zone Button");
				if (Utilities.explicitWaitVisibleNew(driver, settingsPageV2.parentPinContainer, 10)) {
					Thread.sleep(1000);
					settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				}
				else 
					test.log(LogStatus.FAIL, "PIN CONTAINER not found in Parent Zone page ");
			}else 
				BasePageV2.reportFail("PARENT ZONE button not found");
			
			
			// putting App in background
			 driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
			 
			//click on Settings Icon
			 if (Utilities.explicitWaitClickable(driver, settingsPageV2.settingsIcon, 20)) {
					try {
						settingsPageV2.settingsIcon.click();
						test.log(LogStatus.INFO, "Clicked on Settings icon");			
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to click on Settings icon");
					}
			 }
			 else {
						test.log(LogStatus.INFO, "Settings icon is not clickable");
			 }
			 if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.settingsDevice, 10)) {
					settingsPageV2.settingsDevice.click();
					test.log(LogStatus.INFO, "Clicked on Device option");
// Device Ui verifying in settings Page	
					if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.settingsDeviceTile, 10)) {
						test.log(LogStatus.INFO, "Device screen is displayed");
						if(Utilities.explicitWaitClickable(driver, settingsPageV2.deviceBackBtn, 10)) {
							test.log(LogStatus.INFO,"Back button is present in Device settings screen");
							settingsPageV2.deviceBackBtn.click();
							test.log(LogStatus.INFO,"Clicked on Back button in Device settings screen");
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDevice, 10)) {
								test.log(LogStatus.INFO, "Device navigated back to Settings screen");
								test.log(LogStatus.PASS,"Verify Back arrow functionality in Device settings screen in PASS");
								if(!Utilities.setResultsKids("VK_847", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								settingsPageV2.settingsDevice.click();
								test.log(LogStatus.INFO, "Clicked on Device option again");
							}
							else {
								test.log(LogStatus.FAIL,"Device has not navigated back to Settings screen");
								test.log(LogStatus.FAIL,"Verify Back arrow functionality in Device settings screen in FAIL");
								if(!Utilities.setResultsKids("VK_847", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
						}
						else {
							test.log(LogStatus.FAIL,"Device Back button not found in Device Screen Page ");
							test.log(LogStatus.FAIL,"Verify Back arrow functionality in Device settings screen in FAIL");
							if(!Utilities.setResultsKids("VK_847", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.deviceCellularPlybackWithToggleButton, 10)) {
							test.log(LogStatus.INFO, "Cellular Playback with Toggle button is present");
						}
						else {
							test.log(LogStatus.FAIL, "'Cellular playback' option is not Found in Device Page");
							err846++;
						}
						
						if(Utilities.explicitWaitClickable(driver, settingsPageV2.devicedefaultsremquality, 10)) {
							try {
								settingsPageV2.devicedefaultsremquality.click();
								test.log(LogStatus.INFO, "Clicked on Default Stream Quality dropdown");
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityLow, 5)) {
									test.log(LogStatus.INFO, "Stream Quality 'Low' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Stream Quality 'Low' is not displayed in dropdown");
									err846++;
								}
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityMedium, 5)) {
									test.log(LogStatus.INFO, "Stream Quality 'Medium' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Stream Quality 'Medium' is not displayed in dropdown");
									err846++;
								}
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityHigh, 5)) {
									test.log(LogStatus.INFO, "Stream Quality 'High' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Stream Quality 'High' is not displayed in dropdown");
									err846++;
								}
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityAuto, 5)) {
									test.log(LogStatus.INFO, "Stream Quality 'Auto' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Stream Quality 'Auto' is not displayed in dropdown");
									err846++;
								}
								settingsPageV2.streamQualityAuto.click();
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to click on Default Stream Quality dropdown");
							}
						}
						else {
							test.log(LogStatus.FAIL, "'Default Stream quality' option is not Found in Device Page");	
							err846++;
						}
						if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.devicePreferredLanuagewithDropdown, 10)) {
							test.log(LogStatus.INFO, "Located Preferred Language with dropdown");
						}
						else{
							test.log(LogStatus.FAIL, "Preferred Language with dropdown is not displayed");
							err846++;
						}
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceEnableDownloadsWithToggleButton, 10)) {
							test.log(LogStatus.INFO, "Located Enable Downloads with toggle button");
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate Enable Downloads with toggle button");
							err846++;
						}
						Utilities.verticalSwipe(driver);
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceCellularDownloadsWithToggleButton, 10)) {
							test.log(LogStatus.INFO, "Located Cellular Downloads with toggle button");
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate Cellular Downloads with toggle button");
							err846++;
						}
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceVideoDownloadQualityWithDropdown, 10)) {
							test.log(LogStatus.INFO, "Located Download Quality with dropdown");
							try {
								settingsPageV2.deviceVideoDownloadQualityWithDropdown.click();
								test.log(LogStatus.INFO, "Clicked on Download Quality dropdown");
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityLow, 5)) {
									test.log(LogStatus.INFO, "Video Download Quality 'Low' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Video Download Quality 'Low' is not displayed in dropdown");
									err846++;
								}
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityMedium, 5)) {
									test.log(LogStatus.INFO, "Video Download Quality 'Medium' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Video Download Quality 'Medium' is not displayed in dropdown");
									err846++;
								}
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityHigh, 5)) {
									test.log(LogStatus.INFO, "Video Download Quality 'High' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Video Download Quality 'High' is not displayed in dropdown");
									err846++;
									BasePageV2.takeScreenshot();
								}
								settingsPageV2.streamQualityHigh.click();
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to click on Video Downloads Quality dropdown");
							}
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate Video Download Quality with dropdown");
							err846++;
						}
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceAudioDownloadQualityWithDropdown, 10)) {
							test.log(LogStatus.INFO, "Located Audio Download Quality with dropdown");
							try {
								settingsPageV2.deviceAudioDownloadQualityWithDropdown.click();
								test.log(LogStatus.INFO, "Clicked on Audio Download Quality dropdown");
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityLow, 5)) {
									test.log(LogStatus.INFO, "Audio Download Quality 'Low' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Audio Download Quality 'Low' is not displayed in dropdown");
									err846++;
								}
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityMedium, 5)) {
									test.log(LogStatus.INFO, "Audio Download Quality 'Medium' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Audio Download Quality 'Medium' is not displayed in dropdown");
									err846++;
								}
								if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.streamQualityHigh, 5)) {
									test.log(LogStatus.INFO, "Audio Download Quality 'High' is displayed in dropdown");
								}
								else {
									test.log(LogStatus.FAIL, "Audio Download Quality 'High' is not displayed in dropdown");
									err846++;
								}
								settingsPageV2.streamQualityHigh.click();
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to click on Video Downloads Quality dropdown");
							}
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate Video Download Quality with dropdown");
							err846++;
						}
						Utilities.verticalSwipe(driver);
						if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.deviceNotificationsWithToggleButton, 10)) {
							test.log(LogStatus.INFO, "Located Notifications with toggle button");
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate Notifications with toggle button");
							err846++;
						}
						if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.deviceClearCacheWithToggleButton, 10)) {
							test.log(LogStatus.INFO, "Located Clear Cache with right arrow button");
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate Clear Cache with right arrow button");
							err846++;
						}	
					}
					else {
						test.log(LogStatus.FAIL, "Not able to navigate to Device Screen");
						err846++;
					}
				//Final verification of VK_846
					if(err846==0) {
						test.log(LogStatus.PASS, "Verify the UI of Device page is PASS");
						if(!Utilities.setResultsKids("VK_846", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					else {
						test.log(LogStatus.FAIL, "Verify the UI of Device page is FAIL");
						if(!Utilities.setResultsKids("VK_846", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					
				
				//Verify the default toggle button state for Background Music options in device settings
					
				Utilities.verticalSwipe(driver, settingsPageV2.deviceBackgroundMusicSwitch);
				if (settingsPageV2.deviceBackgroundMusicSwitch.getText().equalsIgnoreCase("ON")) {
					test.log(LogStatus.INFO, "Background music is ON by default");
					test.log(LogStatus.PASS, "Verify the default toggle button state for Background Music options in device settings is PASS");
					if(!Utilities.setResultsKids("VK_883", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} 
				else {
					test.log(LogStatus.FAIL,"'Background music' option Switch is not Found or else Switch is in OFF state");
					test.log(LogStatus.PASS, "Verify the default toggle button state for Background Music options in device settings is FAIL");
					if(!Utilities.setResultsKids("VK_883", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();    
				}
				//Verify bck music toggle button functionality
				try {
					if (settingsPageV2.deviceBackgroundMusicSwitch.getText().equalsIgnoreCase("ON")) {
						settingsPageV2.deviceBackgroundMusicSwitch.click();
						test.log(LogStatus.INFO, "Clicked on Background music toggle button");
						if (settingsPageV2.deviceBackgroundMusicSwitch.getText().equalsIgnoreCase("OFF")) {
							test.log(LogStatus.INFO, "Background music Toggle button has changed from ON to OFF");
						}
						else if (settingsPageV2.deviceBackgroundMusicSwitch.getText().equalsIgnoreCase("ON")){
							test.log(LogStatus.FAIL, "Background music Toggle button has not changed from ON to OFF");
							err884++;
						}
						else {
							test.log(LogStatus.FAIL, "Some error occured when changing Background music Toggle button from ON to OFF");
							err884++;
						}
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Some error occured when changing Background music Toggle button from ON to OFF");
				}
				try {
					if (settingsPageV2.deviceBackgroundMusicSwitch.getText().equalsIgnoreCase("OFF")) {
						settingsPageV2.deviceBackgroundMusicSwitch.click();
						test.log(LogStatus.INFO, "Clicked on Background music toggle button");
						if (settingsPageV2.deviceBackgroundMusicSwitch.getText().equalsIgnoreCase("ON")) {
							test.log(LogStatus.INFO, "Background music Toggle button has changed from OFF to ON");
						}
						else if (settingsPageV2.deviceBackgroundMusicSwitch.getText().equalsIgnoreCase("OFF")){
							test.log(LogStatus.FAIL, "Background music Toggle button has not changed from OFF to ON");
							err884++;
						}
						else {
							test.log(LogStatus.FAIL, "Some error occured when changing Background music Toggle button from OFF to ON");
							err884++;
						}
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Some error occured when changing Background music Toggle button from OFF to ON");
					err884++;
				}
				//Final verification of VK_846
				if(err884==0) {
					test.log(LogStatus.PASS, "Verify 'Background Music' toggle button functionality is PASS");
					if(!Utilities.setResultsKids("VK_884", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL, "Verify 'Background Music' toggle button functionality is FAIL");
					if(!Utilities.setResultsKids("VK_884", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}	
					
// Help & Support
				boolean inSettingsScreen=false;
				driver.navigate().back();
				if (Utilities.explicitWaitVisibleNew(driver, settingsPageV2.settingsPageTitle, 10)) {
					test.log(LogStatus.INFO, "Navigated back to Settings page");
					inSettingsScreen=true;
				}
				else {
					test.log(LogStatus.INFO, "Failed to navigate back to Settings page");
				}
				if(inSettingsScreen==true) {
					Utilities.verticalSwipe(driver);
					Utilities.verticalSwipe(driver);
					// Navigating to Help & Support Page
					if(Utilities.explicitWaitClickable(driver,settingsPageV2.settingTextinParantZoneHelpSupport, 10)) {
						try {
							settingsPageV2.settingsHelpSupport.click();
							test.log(LogStatus.INFO, "Clicked on Help & Support option");
							if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.helpPagebackBtn, 10) && Utilities.explicitWaitVisibleNew(driver, settingsPageV2.helpHELPtile, 10)) {
								test.log(LogStatus.INFO, "Help page is displayed with Back button");
								test.log(LogStatus.PASS, "Validate Help & Support link functionality is PASS");
								if(!Utilities.setResultsKids("VK_903", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							else {
								test.log(LogStatus.FAIL, "Help page with Back button is not displayed");
								test.log(LogStatus.PASS, "Validate Help & Support link functionality is FAILs");
								if(!Utilities.setResultsKids("VK_903", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							if(Utilities.explicitWaitClickable(driver, settingsPageV2.helpPagebackBtn, 10)) {
								try {
									settingsPageV2.helpPagebackBtn.click();
									test.log(LogStatus.INFO, "Clicked on Back button in Help page");
									if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.settingsHelpSupport, 10)) {
										test.log(LogStatus.INFO, "Verified app navigated back to Device screen");
										test.log(LogStatus.PASS,"Verify Back arrow functionality from Help and Support screen is PASS");
										if(!Utilities.setResultsKids("VK_906", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									}
									else {
										test.log(LogStatus.FAIL,"App has not navigated back to Device screen");
										test.log(LogStatus.PASS,"Verify Back arrow functionality from Help and Support screen is FAIL");
										if(!Utilities.setResultsKids("VK_906", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										BasePageV2.takeScreenshot();
									}
								}
								catch(Exception e) {
									test.log(LogStatus.INFO, "Failed to click on Back button in Help page");
								}
							}
							else 
								test.log(LogStatus.FAIL,"Back Button in Help page is not clickables");	
						}
						catch(Exception e) {
							test.log(LogStatus.INFO, "Failed to click on Help & Support option");
						}
					}	
					else {
						test.log(LogStatus.FAIL,"Unable to find Help & Support option in Settings Page");
					}
				}
				try {
					settingsPageV2.settingsHelpSupport.click();
					test.log(LogStatus.INFO, "Clicked on Help and Support option");
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to click on Help and Support option");
				}
	
// Help & Support Ui validating
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
					test.log(LogStatus.INFO,"Located Help title");
				}
				else {
					test.log(LogStatus.FAIL,"Failed to located Help title");
					err904++;
				}
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpContactTile, 10)) {
					test.log(LogStatus.INFO,"Located Contact us with right facing arrow");
			    }
				else {
					test.log(LogStatus.FAIL,"Failed to locate Contact us with right facing arrow");
					err904++;
				}
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpFAQs, 10)) {
					test.log(LogStatus.INFO,"Located FAQ's");
				}
				else {
					test.log(LogStatus.FAIL,"Failed to locate FAQ's");
					err904++;
				}
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpTermsConditions, 10)) {
					test.log(LogStatus.INFO,"Located Terms & Conditions with right facing arrow");
				}
				else {
					test.log(LogStatus.FAIL,"Failed to locate Terms & Conditions with right facing arrow");
					err904++;
				}
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpPrivacyPolicy, 10)) {
					test.log(LogStatus.INFO,"Located Privacy Policy with right facing arrow");
				}
				else {
					test.log(LogStatus.FAIL,"Failed to locate Privacy Policy with right facing arrow");	
					err904++;
				}
				Utilities.verticalSwipe(driver);
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpAppVersion, 10)) {
					test.log(LogStatus.INFO,"Located App Version with right facing arrow");
				}
				else {
					test.log(LogStatus.FAIL,"Failed to locate App Version with right facing arrow");	
					err904++;
				}
				if(err904==0) {
					test.log(LogStatus.PASS, "Verify the UI of Help & Support page is PASS");
					if(!Utilities.setResultsKids("VK_904", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL, "Verify the UI of Help & Support page is FAIL");
					if(!Utilities.setResultsKids("VK_904", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}                       	   

			// putting the App in background for 3 seconds and brink it up back 
				driver.runAppInBackground(Duration.ofSeconds(3));
				test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				driver.currentActivity();
				settingsPageV2.helpPagebackBtn.click();
				
			// Rate Us feature functionality and Ui verifying 	
				Utilities.verticalSwipe(driver);
				Utilities.verticalSwipe(driver);
				try {
					settingsPageV2.settingsRateUS.click();
					test.log(LogStatus.INFO, "Clicked on Rate Us option");
					try {
						String str = settingsPageV2.rateUsSubhead.getText().toString().trim();
						String strLower=str.toLowerCase().trim();
						test.log(LogStatus.INFO, "******************");
						 test.log(LogStatus.INFO, ""+str);
						 test.log(LogStatus.INFO, ""+strLower);
						 test.log(LogStatus.INFO, "are you happy with voot kids experience");
						 test.log(LogStatus.INFO, "******************");
						if(("are you happy with voot kids experience").contains(strLower)) {
							test.log(LogStatus.INFO, "Correct message is displayed: "+str);
						}
						else {
							test.log(LogStatus.FAIL, "Incorrect message is displayed: "+str);
							err910++;
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to fetch message from Rate Us pop up");
						err910++;
					}
					if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsPopYesText, 10)) {
						test.log(LogStatus.INFO, "Located Yes in Pop up");
					}
					else {
						test.log(LogStatus.FAIL, "Failed to locate Yes in Pop up");
						err910++;
					}
					if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsPopNoText, 10)) {
						test.log(LogStatus.INFO, "Located No in Pop up");
					}
					else {
						test.log(LogStatus.FAIL, "Failed to locate No in Pop up");
						err910++;
					}
					if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsPopSkipForNow, 10)) {
						test.log(LogStatus.INFO, "Located 'SKIP FOR NOW' in Pop up");
					}
					else {
						test.log(LogStatus.FAIL, "Failed to locate 'SKIP FOR NOW' in Pop up");
						err910++;
					}
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to click Rate Us option");
				}
				if(err910==0) {
					test.log(LogStatus.PASS, "Validate the functionality for 'Rate Us' link is PASS");
					if(!Utilities.setResultsKids("VK_910", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL, "Validate the functionality for 'Rate Us' link is FAIL");
					if(!Utilities.setResultsKids("VK_910", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
				}   
				// clicking on 'yes' in Pop of Rate Us
				try {
					 settingsPageV2.rateUsPopYesText.click();
					 test.log(LogStatus.INFO, "Clicked Yes in Rate Us pop up");
					 if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateusThatsGreat, 10)) {
						 test.log(LogStatus.INFO, "Located 'THAT'S GREAT' pop up title");
					 }
					 else {
						 test.log(LogStatus.INFO, "Failed to locate 'THAT'S GREAT' pop up title");
						 err913++;
					 }
					 if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.thatsGreatSubHead, 10)) {
						 String str=settingsPageV2.thatsGreatSubHead.getAttribute("text").toString().trim();
						 String strlower=str.toLowerCase().trim();
						 test.log(LogStatus.INFO, "******************");
						 test.log(LogStatus.INFO, ""+str);
						 test.log(LogStatus.INFO, ""+strlower);
						 test.log(LogStatus.INFO, "how about rating us on the play store then");
						 test.log(LogStatus.INFO, "******************");
						 test.log(LogStatus.INFO, "how about rating us on the play store then");
						 String compareString="how about rating us on the play store then";
						 if(strlower.contains(compareString)) {
							 test.log(LogStatus.INFO, "Correct string is displayed:"+str);
						 }
						 else {
							 test.log(LogStatus.FAIL, "Incorrect string is displayed:"+str);
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Failed to locate string 'How about rating us on the Play Store then?'");
						 err913++;
					 }
					 if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.thatsGreatRateUs, 10)) {
						 test.log(LogStatus.INFO, "Located RATE US button");
					 }
					 else {
						 test.log(LogStatus.FAIL, "Failed to locate RATE US button");
						 err913++;
					 }
					 if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsPopSkipForNow, 10)) {
							test.log(LogStatus.INFO, "Located 'SKIP FOR NOW' in Pop up");
					 }
					 else {
							test.log(LogStatus.FAIL, "Failed to locate 'SKIP FOR NOW' in Pop up");
							err913++;
					 }
					 
				} 
				catch (Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Yes in Rate Us pop up");
				}
				if(err913==0) {
					test.log(LogStatus.PASS, "Verify the functionality for 'Rate us on the App Store/Play Store' link by clicking on 'Yes' on the pop-up message is PASS");
					if(!Utilities.setResultsKids("VK_913", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL, "Verify the functionality for 'Rate us on the App Store/Play Store' link by clicking on 'Yes' on the pop-up message is FAIL");
					if(!Utilities.setResultsKids("VK_913", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
				}
				try {
					 settingsPageV2.rateUsPopSkipForNow.click();
					 test.log(LogStatus.INFO, "Clicked on Skip For Now");
				}
				 catch(Exception e) {
					 driver.navigate().back();
				}
				driver.runAppInBackground(Duration.ofSeconds(5));
				test.log(LogStatus.INFO, "Put app to background for 5 seconds");
				driver.currentActivity();	  
				// Click 'No' in Pop of Rate Us					   
			    if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.settingsRateUS, 10)) {
				   try {
					   settingsPageV2.settingsRateUS.click();
					   test.log(LogStatus.INFO, "Clicked Rate Us");
				   } 
				   catch (Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click Rate Us");
				   }
				   try {
					   settingsPageV2.rateUsPopNoText.click();
					   test.log(LogStatus.INFO, "Clicked on No in the pop up");
					   if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsSendfeedBack, 10)) {
						   test.log(LogStatus.INFO, "Located SEND FEEDBACK button");
						   test.log(LogStatus.PASS, "Verify the functionality for 'Rate Us' link by tapping on 'No' on the message is PASS");
						   if(!Utilities.setResultsKids("VK_921", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					   }
					   else {
						   test.log(LogStatus.INFO, "Failed to locate SEND FEEDBACK button");
						   test.log(LogStatus.FAIL, "Verify the functionality for 'Rate Us' link by tapping on 'No' on the message is FAIL");
						   if(!Utilities.setResultsKids("VK_921", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						   BasePageV2.takeScreenshot();
					   }
				   }
				   catch(Exception e) {
					   test.log(LogStatus.INFO, "Failed to click No in the pop up");
					   BasePageV2.takeScreenshot();
				   }
				   if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsSendfeedBack, 5)) {
					   try {
							settingsPageV2.rateUsSendfeedBack.click();
							test.log(LogStatus.INFO, "Clicked on SEND FEEDBACK button");
							if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsSendfeedBackPage, 10)) {
								test.log(LogStatus.INFO, "Navigated to Send Feedback page");
								test.log(LogStatus.PASS, "Verify the functionality of 'send feedback' button in pop-up message is PASS");
								if(!Utilities.setResultsKids("VK_924", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							else {
								test.log(LogStatus.FAIL, "Failed to navigate to Send Feedback page");
								test.log(LogStatus.FAIL, "Verify the functionality of 'send feedback' button in pop-up message is FAIL");
								if(!Utilities.setResultsKids("VK_924", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
					   } 
					   catch (Exception e) {
							test.log(LogStatus.FAIL, "Failed to click Send feedback button");
					   }
				   }
				   else {
					   test.log(LogStatus.FAIL, "Send feedback button is not clickable");
				   }
				   //Check scroll functionality
				   if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsSendFeedBackEditText, 10)) {
					   settingsPageV2.rateUsSendFeedBackEditText.sendKeys("Nice entertainment with good collection of Cartoon Videos But need more Cartoon Movies Collections There are many One is to localize your description and thereby reach more markets But before doing this you need to have the perfect app description to translate It is obviously important to put a lot of effort into your app development but when it is time to release you also have to think carefully about how you are going to present it Nice entertainment with good collection of Cartoon Videos But need more Cartoon Movies Collections There are many One is to localize your description and thereby reach more markets But before doing this you need to have the perfect app description to translate It is obviously important to put a lot of effort into your app development but when it is time to release you also have to think carefully about how you are going to present it End of Story");
					   test.log(LogStatus.INFO, "Entered text in text box");
					   //settingsPageV2.rateUsSendFeedBackEditText.sendKeys("Nice entertainment with good collection of episodes, books and audios. But need more Cartoon Movies Collections. There are many. ");
					   try {
						   driver.hideKeyboard();
					   }
					   catch(Exception e) {}
					   if(!Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsSendfeedBackPage, 10)) {
						   test.log(LogStatus.INFO, "Unable to find header so scrolling reverse...");
						   Utilities.verticalSwipeReverse(driver);
						   Utilities.verticalSwipeReverse(driver);
						   Utilities.verticalSwipeReverse(driver);
						   Utilities.verticalSwipeReverse(driver);
						   test.log(LogStatus.INFO, "Scolled reverse to the text box");
						   if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsSendfeedBackPage, 10)) {
							   test.log(LogStatus.INFO, "Found header after scrolling upwards");
							   test.log(LogStatus.PASS, "Verify the scroll functionality in feedback text box  is PASS");
							   if(!Utilities.setResultsKids("VK_926", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						   }
						   else {
							   test.log(LogStatus.FAIL, "Failed to find header after scrolling upwards");
							   test.log(LogStatus.FAIL, "Verify the scroll functionality in feedback text box  is FAIL");
							   if(!Utilities.setResultsKids("VK_926", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						   }
						   
					   }
					   if(Utilities.explicitWaitClickable(driver, settingsPageV2.sendFeedbackCancel, 10)) {
						   driver.navigate().back();
						   try {
							   test.log(LogStatus.INFO, "Navigated back to Settings page");
							   try {
									settingsPageV2.settingsRateUS.click();
									test.log(LogStatus.INFO, "Clicked on Rate Us option");
									 try {
										   settingsPageV2.rateUsPopNoText.click();
										   test.log(LogStatus.INFO, "Clicked on No in the pop up");
										   try {
												settingsPageV2.rateUsSendfeedBack.click();
												test.log(LogStatus.INFO, "Clicked on Send feedback button");
												try {
													settingsPageV2.rateUsSendFeedBackEditText.sendKeys("Nice entertainment with good collection of episodes, books and audios");
													test.log(LogStatus.INFO, "Entered text in edit field");
													 try {
														  settingsPageV2.rateUsSendFeedBackSendBtn.click();
														  test.log(LogStatus.INFO, "Clicked on Send button");
														  if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.rateUsSendFeedBackSuccussMsg, 10)) {
															  test.log(LogStatus.INFO, "Feedback Submitted pop up is displayed");
															  test.log(LogStatus.PASS, "Verify if user can send the feedback successfuly is PASS");
															  if(!Utilities.setResultsKids("VK_925", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
														  }
														  else {
															  test.log(LogStatus.FAIL, "Feedback Submitted pop up is not displayed");
															  test.log(LogStatus.FAIL, "Verify if user can send the feedback successfuly is FAIL");
															  if(!Utilities.setResultsKids("VK_925", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
														  }
														  if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsSendFeedBackCanlBtn, 10)) {
															  try {
																  settingsPageV2.rateUsSendFeedBackCanlBtn.click();
																  test.log(LogStatus.INFO, "Clicked on Cancel button of FEEDBACK SUBMITTED dialog");
															  }
															  catch(Exception e) {
																  test.log(LogStatus.INFO, "Failed to click on Cancel button of FEEDBACK SUBMITTED dialog");
															  }
														  }
														  else {
															  test.log(LogStatus.FAIL, "Cancel button of FEEDBACK SUBMITTED dialog is not clickable");
														  }
													 }
													 catch (Exception e) {
														 test.log(LogStatus.INFO, "Failed to click on Send button"); 
													 }
												}
												catch(Exception e) {
													test.log(LogStatus.INFO, "Failed to enter text in edit field");
												}
										   }
										   catch (Exception e) {
													test.log(LogStatus.FAIL, "Failed to click Send feedback button");
										   }
									 }
									 catch(Exception e) {
										 test.log(LogStatus.INFO, "Failed to click on No in the pop up");
									 }
							   }
							   catch(Exception e) {
								   test.log(LogStatus.INFO, "Failed to click on Cancel button");
							   }
						   }
						   catch (Exception e) {
							   test.log(LogStatus.INFO, "Failed to navigate back to Settings page");
						   }
					   }
				   }
			    }
			 }
	   
			//tap on the 'RATE US' Button
			driver.runAppInBackground(Duration.ofSeconds(3));
			test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			driver.currentActivity();
			
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsRateUS, 10)) {
				settingsPageV2.settingsRateUS.click();
				test.log(LogStatus.INFO, "Clicked on Rate Us");
				settingsPageV2.rateUsPopYesText.click();
				test.log(LogStatus.INFO, "Clicked on Yes");
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.thatsGreatRateUs, 10)) {
					   settingsPageV2.thatsGreatRateUs.click();
					   Thread.sleep(1000);
					   if(Utilities.explicitWaitVisible(driver, settingsPageV2.rateUsBtnTapplayStore, 10)) { // navigate to 'Play Store'(Voot-Kids Store) page
						    test.log(LogStatus.INFO, "Navigated to Play Store");
						    test.log(LogStatus.PASS,"Verify the functionality by tapping on 'Rate us' button in the pop-up is Pass");
						    if(!Utilities.setResultsKids("VK_916", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  
					   }
					   else { 
						   test.log(LogStatus.FAIL,"Unable to Navigate to Play Store Main Page");
						   test.log(LogStatus.FAIL,"Verify the functionality by tapping on 'Rate us' button in the pop-up is Fail");
						   if(!Utilities.setResultsKids("VK_916", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  
					   }
					   
					   for(int i =1 ; i<5;i++) {
							driver.pressKeyCode(AndroidKeyCode.BACK);
							driver.runAppInBackground(Duration.ofSeconds(5));
							test.log(LogStatus.INFO, "Put app to background for 5 seconds");
							driver.currentActivity();
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
								break;
							}
					   }
							   
				}else  
					test.log(LogStatus.FAIL,"'RATE US' button is not clickable");
			}else 
				test.log(LogStatus.FAIL,"'RATE US' option is Settings pages not clickable");
				
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
					   
// tapping 'share' option to navigate to Pre-Installed app's Screen in device	
		   driver.runAppInBackground(Duration.ofSeconds(5));
		   test.log(LogStatus.INFO, "Put app to background for 5 seconds");
		   driver.currentActivity();
		 
		   try {
			   settingsPageV2.settingsShare.click();
			   test.log(LogStatus.INFO, "Clicked Share option in Settings page");
		   } 
		   catch (Exception e) {
			   test.log(LogStatus.FAIL, "Failed to click Share option in Settings page");
		   }			 
		   if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.shareList, 10)) {
				test.log(LogStatus.PASS,"Validate the functionality by tapping on Share option is Pass");
				com.viacom.pagesVersion2.BasePageV2.takeScreenshot();
				if(!Utilities.setResultsKids("VK_899", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		   }
		   else {
			    test.log(LogStatus.FAIL,"Not able to Navigate to pre-installed apps screen in device.");
			    test.log(LogStatus.PASS,"Validate the functionality by tapping on Share option is Fail");
				com.viacom.pagesVersion2.BasePageV2.takeScreenshot();
				if(!Utilities.setResultsKids("VK_899", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		   }		
			/*driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(1000);*/
			driver.runAppInBackground(Duration.ofSeconds(5));
			test.log(LogStatus.INFO, "Put app to background for 5 seconds");
			driver.currentActivity();

			if(Utilities.explicitWaitVisible(driver, settingsPageV2.shareWithGmail, 10)) {
				test.log(LogStatus.INFO, "Share with Gmail is displayed");
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.justOnceBtn, 10)) {
					settingsPageV2.justOnceBtn.click();
					test.log(LogStatus.INFO, "Clicked the JUST ONCE button to share Voot kids to gmail");
				}
				else {
					test.log(LogStatus.INFO, "JUST ONCE button to share Voot kids to gmail is not clickable");
				}
			}
			else if (Utilities.explicitWaitClickable(driver, settingsPageV2.shareGmail, 10)){
				try {
					settingsPageV2.shareGmail.click();
					test.log(LogStatus.INFO, "Clicked on Gmail app");
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Unable to click on Gmail app");
				}
			}
			else {
				Utilities.verticalSwipeReverseGmail(driver);
			}
// Share Feature Functionality verifing
			Thread.sleep(1000);
			if (Utilities.explicitWaitClickable(driver, settingsPageV2.shareGmail, 10)) {
				BasePageV2.takeScreenshot();
				try {
					settingsPageV2.shareGmail.click();
					test.log(LogStatus.INFO, "Clicked on Gmail app");
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Unable to click on Gmail app");
				}
				
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.justOnceBtn, 10)) {
					settingsPageV2.justOnceBtn.click();
					test.log(LogStatus.INFO, "Clicked the JUST ONCE button to share Voot kids to gamil");
				}
				
				if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.shareGmailCompose, 10)) {
					test.log(LogStatus.INFO, "Gmail compose screen is displayed");
					test.log(LogStatus.PASS,"Verified the functionality of share feature by selecting any app is Pass");
					BasePageV2.takeScreenshot();
					if(!Utilities.setResultsKids("VK_900", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.INFO, "Gmail compose screen ihas failed to display");
					test.log(LogStatus.FAIL,"Verified the functionality of share feature by selecting any app is Fail");
					BasePageV2.takeScreenshot();
					if(!Utilities.setResultsKids("VK_900", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
			}
			else {
				test.log(LogStatus.FAIL,"Failed to locate Gmail option");
			}

		}
	
		@DataProvider
		public Object[][] getData() {
			return DataUtil.getData(testName, xls);
		}
		
		
}
