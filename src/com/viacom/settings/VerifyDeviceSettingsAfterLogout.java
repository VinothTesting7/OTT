package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyDeviceSettingsAfterLogout extends BaseTestV2{
	
	String testName = "VerifyDeviceSettingsAfterLogout";
	@Test(dataProvider = "getData")
	public void VerifyDeviceSettingsAfterLogout(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyDeviceSettingsAfterLogout");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		int err1572=0;
		int err1568=0;
		int err1569=0;
		int err1605=0;
		
		Xls_Reader xls1572 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1572=xls1572.getRowCount("Smoke_Results")+1;
		xls1572.setCellData("Smoke_Results", "Testcase Name",rowno1572,"Verify if Device settings are retained post logging out and logging in from same device");

		Xls_Reader xls1568 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1568=xls1568.getRowCount("Smoke_Results")+1;
		xls1568.setCellData("Smoke_Results", "Testcase Name",rowno1568,"Verify if Preferences settings are retained post logging out and logging from same device");

		Xls_Reader xls1569 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1569=xls1569.getRowCount("Smoke_Results")+1;
		xls1569.setCellData("Smoke_Results", "Testcase Name",rowno1569,"Verify if Data in Activity tab is retained post logging out and logging from same device");
		
		Xls_Reader xls1605 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1605=xls1605.getRowCount("Smoke_Results")+1;
		xls1605.setCellData("Smoke_Results", "Testcase Name",rowno1605,"Verify 'Day' , 'Week' , 'Month' tabs functionality");
		
		
		//Launch Voot kids app
		//Login module to be added
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
		 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 
		 //1572
		 String enableDownloadChangedTo="";
		 //1568
		 String dailyUsageChangedTo="";
		 String bedTimeChangedTo="";
		 String emailUpdateChangedTo="";
		 //1569
		 String usageStatsWeekData="";
		 String usageStatsEpisodeData="";
		 String usageStatsTotalHoursData="";
		 String learnStatsFirstBlockNameData="";
		 String learnStatsFirstBlockCorrectData="";
		 String recentActivityFirstItemData="";
		 String recentActivitySecondItemData="";
		 

	//Change Device Settings
	 try {
			if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {
				homepagev2.profilepic.click(); // tap on profile icon
				test.log(LogStatus.INFO, "Clicked on profile icon in home page");
			}
	 }
	 catch (Exception e) {
			BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
	 }
	 
	 
	// Click on ParentZone Button in Switch Profile Screen parentZoneButton
		if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10)) {
			settingspagev2.parentZoneButton.click();
			test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
			if (Utilities.explicitWaitVisible(driver, settingspagev2.parentPinContainer, 10)) {
				Thread.sleep(1000);
				settingspagev2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
			}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
		}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
	 
	// putting App in background
	 driver.runAppInBackground(Duration.ofSeconds(3));
	 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
	 driver.currentActivity();
	 
	 if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsIcon, 10)) {
		settingspagev2.settingsIcon.click();
		test.log(LogStatus.INFO, "Clicked on Settings icon");
		
			if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsDevice, 10)) {
				settingspagev2.settingsDevice.click();
				test.log(LogStatus.INFO, "Clicked on Device option");
				if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsDeviceTile, 10)) {
					test.log(LogStatus.INFO, "Device screen is displayed");	
					test.log(LogStatus.INFO, "CHANGE DEVICE SETTINGS ...");
					//Change Default Stream Quality to High
					try {
						WebElement dropDownQualityList = driver.findElementByXPath("//android.widget.Spinner");
						dropDownQualityList.click();
						test.log(LogStatus.INFO, "Tapped on Default Stream Quality dropdown");
						try {
							WebElement highQuality = driver.findElementByXPath("//android.widget.ListView//android.view.ViewGroup//android.widget.TextView[@text='High']");
							highQuality.click();
							test.log(LogStatus.INFO, "Selected 'High' as the Default Stream Quality");
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to select 'High' as the Default Stream Quality dropdown");
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to click on Default Stream Quality dropdown");
					}
				//Change Preferred language to Kannada
					try {
						settingspagev2.devicePrefferdLanuageSpinner.click();
						test.log(LogStatus.INFO, "Tapped on Preferred language dropdown");
						try {
							settingspagev2.devicePrefrredLanuageKannada.click();
							test.log(LogStatus.INFO, "Selected 'Kannada' as the Preferred Language");
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to select 'Kannada' as the Preferred Language");
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to click on Preferred Language dropdown");
					}
				//Change Enable Downloads
					if (settingspagev2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("ON")) {
						try {
							settingspagev2.deviceEnableddownloadsSwitch.click();
							test.log(LogStatus.INFO, "Disabled Downloads Settings");
							enableDownloadChangedTo="OFF";
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to disable Downloads Settings");
						}
					}
					else {
						try {
							settingspagev2.deviceEnableddownloadsSwitch.click();
							test.log(LogStatus.INFO, "Enabled Downloads Settings");
							enableDownloadChangedTo="ON";
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to enable Downloads Settings");
						}
					}
				
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to Settings screen");
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to Switch Profile screen");
				//TC 1568
					if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10)) {
						settingspagev2.parentZoneButton.click();
						test.log(LogStatus.INFO, "Tapped on Parent Zone button");
						if (Utilities.explicitWaitVisible(driver, launchpagev2.pinContainer, 5)) {
							launchpagev2.pinContainer.clear();
							launchpagev2.pinContainer.sendKeys("1111");
							test.log(LogStatus.INFO, "Entered PIN");
						} 
						else
							BasePageV2.reportFail("Pin Container not displayed");
						if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZonePage, 20)) {
							test.log(LogStatus.INFO, "Parent zone Page is displayed");
							if (Utilities.explicitWaitVisible(driver, settingspagev2.preferencesTab, 20)) {
								settingspagev2.preferencesTab.click();
								test.log(LogStatus.INFO, "Preferences tab is selected");
								test.log(LogStatus.INFO, "CHANGE PREFERENCES ...");
								if (settingspagev2.parentZonePreferenceDailyUsageSwitch.getText().equalsIgnoreCase("ON")) {
									try {
										settingspagev2.parentZonePreferenceDailyUsageSwitch.click();
										test.log(LogStatus.INFO, "Disabled Daily Usage from Preferences");
										dailyUsageChangedTo="OFF";
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to disable Daily Usage from Preferences");
									}
								}
								else {
									try {
										settingspagev2.parentZonePreferenceDailyUsageSwitch.click();
										test.log(LogStatus.INFO, "Enabled Daily Usage from Preferences");
										dailyUsageChangedTo="ON";
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to enable Daily Usage from Preferences");
									}
								}
								Utilities.verticalSwipe(driver);
								if (settingspagev2.parentZonePreferenceBedTimeSwitch.getText().equalsIgnoreCase("ON")) {
									try {
										settingspagev2.parentZonePreferenceBedTimeSwitch.click();
										test.log(LogStatus.INFO, "Disabled Bed Time from Preferences");
										bedTimeChangedTo="OFF";
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to disable Bed Time from Preferences");
									}
								}
								else {
									try {
										settingspagev2.parentZonePreferenceBedTimeSwitch.click();
										test.log(LogStatus.INFO, "Enabled Bed Time from Preferences");
										bedTimeChangedTo="ON";
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to enable Bed Time from Preferences");
									}
								}
								Utilities.verticalSwipe(driver);
								Utilities.verticalSwipe(driver);
								if (settingspagev2.parentZonePreferenceEmailUpdateSwitch.getText().equalsIgnoreCase("ON")) {
									try {
										settingspagev2.parentZonePreferenceEmailUpdateSwitch.click();
										test.log(LogStatus.INFO, "Disabled Email Update from Preferences");
										emailUpdateChangedTo="OFF";
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to disable Email Update from Preferences");
									}
								}
								else {
									try {
										settingspagev2.parentZonePreferenceEmailUpdateSwitch.click();
										test.log(LogStatus.INFO, "Enabled Email Update from Preferences");
										emailUpdateChangedTo="ON";
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to enable Email Update from Preferences");
									}
								}
								//TC 1569	
								Utilities.verticalSwipeReverse(driver);
								Utilities.verticalSwipeReverse(driver);
								Utilities.verticalSwipeReverse(driver);
								Utilities.verticalSwipeReverse(driver);
								test.log(LogStatus.INFO, "Swiped vertically to the top");
								if (Utilities.explicitWaitVisible(driver, settingspagev2.activityTab, 10)) {
									settingspagev2.activityTab.click();
									test.log(LogStatus.INFO, "Activity tab is selected");
									//Usage Week
									Utilities.verticalSwipe(driver);
									Utilities.verticalSwipe(driver);
									//Switch between day-week-month
									if(Utilities.explicitWaitClickable(driver, settingspagev2.dayButton, 10)) {
										try {
											settingspagev2.dayButton.click();
											test.log(LogStatus.INFO, "Clicked Day tab");
											if(settingspagev2.dayButton.getAttribute("selected").equals("true"))
												test.log(LogStatus.INFO, "Verified Day tab is selected");
											else {
												test.log(LogStatus.INFO, "Verified Day tab is not selected");
												err1605++;
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to select Day tab");
											err1605++;
										}
									}
									else {
										test.log(LogStatus.FAIL, "Day tab is not clickable");
										err1605++;
									}
									if(Utilities.explicitWaitClickable(driver, settingspagev2.monthButton, 10)) {
										try {
											settingspagev2.monthButton.click();
											test.log(LogStatus.INFO, "Clicked Month tab");
											if(settingspagev2.monthButton.getAttribute("selected").equals("true"))
												test.log(LogStatus.INFO, "Verified Month tab is selected");
											else {
												test.log(LogStatus.INFO, "Verified Month tab is not selected");
												err1605++;
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to select Month tab");
											err1605++;
										}
									}
									else {
										test.log(LogStatus.FAIL, "Month tab is not clickable");
										err1605++;
									}
									if(Utilities.explicitWaitClickable(driver, settingspagev2.weekButton, 10)) {
										try {
											settingspagev2.weekButton.click();
											test.log(LogStatus.INFO, "Clicked Week tab");
											if(settingspagev2.weekButton.getAttribute("selected").equals("true"))
												test.log(LogStatus.INFO, "Verified Week tab is selected");
											else {
												test.log(LogStatus.INFO, "Verified Week tab is not selected");
												err1605++;
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to select Week tab");
											err1605++;
										}
									}
									else {
										test.log(LogStatus.FAIL, "Week tab is not clickable");
										err1605++;
									}
									//Verification of 1605
									if(err1605==0) {
										homepagev2.smokeresults("Verify 'Day' , 'Week' , 'Month' tabs functionality",rowno1605, "PASS");
										test.log(LogStatus.PASS, "Verify 'Day' , 'Week' , 'Month' tabs functionality is Pass");
									}
									else {
										homepagev2.smokeresults("Verify 'Day' , 'Week' , 'Month' tabs functionality",rowno1605, "FAIL");
										test.log(LogStatus.FAIL, "Verify 'Day' , 'Week' , 'Month' tabs functionality is Fail");
										BasePageV2.takeScreenshot();
									}
									test.log(LogStatus.INFO, "CAPTURE DATA FROM ACTIVITY TAB ...");            
									for(int scroll=0;scroll<=2;scroll++) {
										if(Utilities.explicitWaitVisible(driver, settingspagev2.usageWeek, 10)) {
											usageStatsWeekData=settingspagev2.usageWeek.getAttribute("text");
											test.log(LogStatus.INFO, "Week displayed under Usage Stats: "+usageStatsWeekData);
											break;
										}
										else { 
											Utilities.verticalSwipe(driver);
											if(scroll==2)
												test.log(LogStatus.FAIL, "Week is not displayed under Usage Stats");
										}
									}
									//Usage Episodes
									for(int scroll=0;scroll<=2;scroll++) {
										if(Utilities.explicitWaitVisible(driver, settingspagev2.usageEpisodesCount, 10)) {
											usageStatsEpisodeData=settingspagev2.usageEpisodesCount.getAttribute("text");
											test.log(LogStatus.INFO, "Number of watched Episodes displayed under Usage Stats: "+usageStatsEpisodeData);	
											break;
										}
										else {
											Utilities.verticalSwipe(driver);
											if(scroll==2) 
												test.log(LogStatus.FAIL, "Number of watched Episodes is not displayed under Usage Stats");					
										}
											
									}
									
									//Usage total hours
									for(int scroll=0;scroll<=2;scroll++) {
										if(Utilities.explicitWaitVisible(driver, settingspagev2.usageTotalHours, 10)) {
											usageStatsTotalHoursData=settingspagev2.usageTotalHours.getAttribute("text");
											test.log(LogStatus.INFO, "Total usage hours displayed under Usage Stats: "+usageStatsTotalHoursData);
											break;
										}
										else {
											Utilities.verticalSwipe(driver);
											if(scroll==2) 
												test.log(LogStatus.FAIL, "Total usage hours is not displayed under Usage Stats");
										}
									}
										
									//Learn stats first block name
									for(int scroll=0;scroll<=2;scroll++) {
										if(Utilities.explicitWaitVisible(driver, settingspagev2.learnStatsFirstBlock, 10)) {
											learnStatsFirstBlockNameData=settingspagev2.learnStatsFirstBlock.getAttribute("text");
											test.log(LogStatus.INFO, "First Block under Learn Stats is: "+learnStatsFirstBlockNameData);
											break;
										}
										else {
											Utilities.verticalSwipe(driver);
											if(scroll==2)
												test.log(LogStatus.FAIL, "Unable to fetch name of the first block under Learns Stats");
										}
											
									}
									
									//Learn stats first block correct score
									Utilities.verticalSwipe(driver);
									for(int scroll=0;scroll<=1;scroll++) {
										try {
											if(Utilities.explicitWaitVisible(driver, settingspagev2.learnStatsCorrect.get(0), 10)) {
												learnStatsFirstBlockCorrectData=settingspagev2.learnStatsCorrect.get(0).getAttribute("text");
												test.log(LogStatus.INFO, "Correct Score of First Block under Learn Stats is: "+learnStatsFirstBlockCorrectData);
												break;
											}
										}
										catch(Exception e) {
											Utilities.verticalSwipe(driver);
											if(scroll==1) 
												test.log(LogStatus.FAIL, "Unable to fetch Correct Score of First Block  under Learns Stats");	
										}		
									}
									
									//Scroll until Recent Activity
									for(int scroll=0;scroll<=7;scroll++) {
										if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneRecentActivity, 2)) {
											test.log(LogStatus.INFO, "Swiped to RECENT ACTIVITY");
											break;
										}
										else
											Utilities.verticalSwipe(driver);
									}
									//First item of Recent Activity
									for(int scroll=0;scroll<=1;scroll++) {
										try {
											if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneRecentActivityItemNames.get(0), 2)) {
												recentActivityFirstItemData=settingspagev2.parentZoneRecentActivityItemNames.get(0).getAttribute("text");
												test.log(LogStatus.INFO, "First most recent activity is "+recentActivityFirstItemData);
												break;
											}
										}
										catch(Exception e) {
											Utilities.verticalSwipe(driver);
										    if(scroll==1)
										    	test.log(LogStatus.FAIL, "Unable to fetch first most recent activity details");	
										}					
									}
									//Second item of Recent Activity
									for(int scroll=0;scroll<=2;scroll++) {
										try {
											if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneRecentActivityItemNames.get(1), 2)) {
												recentActivitySecondItemData=settingspagev2.parentZoneRecentActivityItemNames.get(1).getAttribute("text");
												test.log(LogStatus.INFO, "Second most recent activity is "+recentActivitySecondItemData);
												break;
											}
										}
										catch(Exception e) {
											Utilities.verticalSwipe(driver);
										    if(scroll==2)
										    	test.log(LogStatus.FAIL, "Unable to fetch second most recent activity details");		
										}					
									}
									driver.navigate().back();
									test.log(LogStatus.INFO, "Navigated back to Switch Profile screen");
								}
								else 
									test.log(LogStatus.FAIL, "Activity tab is not visible");				
							}
							else 
								test.log(LogStatus.FAIL, "Preferences tab is not visible");							
						}
						else 
							test.log(LogStatus.FAIL, "Parent zone Page is not displayed");					
					}
					else 
						test.log(LogStatus.FAIL, "Parent Zone button is not displayed");
					
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to Home screen");
				//Logout from the app
					homepagev2.logout();
					//driver.close();
					//launchApp();
				//Login
					homepagev2.login(data.get("Email"),data.get("Password"));
				//Verify Device Settings
					try {
							if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {
								homepagev2.profilepic.click(); // tap on profile icon
								test.log(LogStatus.INFO, "Clicked on profile icon in home page");
							}
					}
					catch (Exception e) {
							BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
					}
					
					// Click on ParentZone Button in Switch Profile Screen parentZoneButton
					if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10)) {
						settingspagev2.parentZoneButton.click();
						test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
						if (Utilities.explicitWaitVisible(driver, settingspagev2.parentPinContainer, 10)) {
							Thread.sleep(1000);
							settingspagev2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
						}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
					}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		
					
					if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsIcon, 10)) {
							settingspagev2.settingsIcon.click();
							test.log(LogStatus.INFO, "Clicked on Settings icon");
							
								if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsDevice, 10)) {
									settingspagev2.settingsDevice.click();
									test.log(LogStatus.INFO, "Clicked on Device option");
									if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsDeviceTile, 10)) {
										test.log(LogStatus.INFO, "Device screen is displayed");							
										//Verify Default Stream Quality is High
										WebElement streamQualityElement = driver.findElementByXPath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text']");
										String streamQuality = streamQualityElement.getText().toString().trim();
										if (streamQuality.equalsIgnoreCase("High")) {
											test.log(LogStatus.INFO, "Default Stream Quality is retained to 'High'");
											//Revert Default Stream Quality to Auto
											try {
												WebElement dropDownQualityList = driver.findElementByXPath("//android.widget.Spinner");
												dropDownQualityList.click();
												try {
													WebElement autoQuality = driver.findElementByXPath("//android.widget.ListView//android.view.ViewGroup//android.widget.TextView[@text='Auto']");
													autoQuality.click();
													test.log(LogStatus.INFO, "Reverted Default Stream Quality to 'Auto'");
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Failed to revert Default Stream Quality to 'Auto'");
												}
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to click on Default Stream Quality dropdown to revert back to 'Auto'");
											}
										} 
										else{
											test.log(LogStatus.FAIL, "Default Stream Quality is not retained to 'High'");
											err1572++;
										}
										
										//Verify Preferred language is Kannada
										try {
											WebElement preferredLang = driver.findElementByXPath("//android.view.ViewGroup[@index='2']//android.widget.Spinner//android.widget.TextView[@text='Kannada']");
											test.log(LogStatus.INFO, "Preferred Language is retained to Kannada");
											//Revert Default Stream Quality to Auto
											try {
												settingspagev2.devicePrefferdLanuageSpinner.click();
												try {
													settingspagev2.devicePrefrredLanuageKannada.click();
													test.log(LogStatus.INFO, "Reverted Preferred Language to 'English'");
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Failed to revert Preferred Language to 'English'");
												}
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to click on Preferred Language dropdown to revert Preferred Language to 'English'");
											}
												
										} 
										catch (Exception e) {
											test.log(LogStatus.FAIL, "Preferred Language is not retained to Kannada");
											err1572++;
										}
									    //Verify Enable Downloads settings
										if(enableDownloadChangedTo.equals("OFF")){
											if(settingspagev2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("OFF")) {
												test.log(LogStatus.INFO, "Enable Downloads Switch is retained to OFF");
												//Revert Enable Downloads to ON
												try {
													settingspagev2.deviceEnableddownloadsSwitch.click();
													test.log(LogStatus.INFO, "Reverted Downloads Settings to ON");
													enableDownloadChangedTo="ON";
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Failed to revert Downloads Settings to ON");
												}							
											}
											else {
												test.log(LogStatus.FAIL, "Enable Downloads Switch is not retained to OFF");
												err1572++;
											}
										}
										else {
											if(settingspagev2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("ON")) {
												test.log(LogStatus.INFO, "Enable Downloads Switch is retained to ON");
											}
											else {
												test.log(LogStatus.FAIL, "Enable Downloads Switch is not retained to ON");
												err1572++;
											}	
										}																						
									}
									else 
										test.log(LogStatus.FAIL, "Device screen is not displayed");			
								} 
								else
									test.log(LogStatus.FAIL, "Device option is not displayed");	
							} 
							else
								test.log(LogStatus.FAIL, "PIN container is not displayed");
					}	
					else 
						test.log(LogStatus.FAIL, "Settings icon is not displayed"); 	
				}
				else 
					test.log(LogStatus.FAIL, "Device screen is not displayed");			
		
	
	}	
	else 
		test.log(LogStatus.FAIL, "Settings icon is not displayed"); 
	//Final verification of 1572
	if(err1572==0) {
		
		homepagev2.smokeresults("Verify if Device settings are retained post logging out and logging in from same device",rowno1572, "PASS");
		test.log(LogStatus.PASS, "Verify if Device settings are retained post logging out and logging in from same device is PASS");
	}
	else {
		homepagev2.smokeresults("Verify if Device settings are retained post logging out and logging in from same device",rowno1572, "FAIL");
		test.log(LogStatus.FAIL, "Verify if Device settings are retained post logging out and logging in from same device is FAIL");
		BasePageV2.takeScreenshot();
	}
	
	//Verify the preferences
	driver.navigate().back();
	test.log(LogStatus.INFO, "Navigated back to Settings screen");
	driver.navigate().back();
	test.log(LogStatus.INFO, "Navigated back to Switch Profile screen");

	if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10)) {
		settingspagev2.parentZoneButton.click();
		test.log(LogStatus.INFO, "Tapped on Parent Zone button");
		if (Utilities.explicitWaitVisible(driver, launchpagev2.pinContainer, 5)) {
			launchpagev2.pinContainer.clear();
			launchpagev2.pinContainer.sendKeys("1111");
			test.log(LogStatus.INFO, "Entered PIN");
		} 
		else
			BasePageV2.reportFail("Pin Container not displayed");
		if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZonePage, 20)) {
			test.log(LogStatus.INFO, "Parent zone Page is displayed");
			if (Utilities.explicitWaitVisible(driver, settingspagev2.preferencesTab, 20)) {
				settingspagev2.preferencesTab.click();
				test.log(LogStatus.INFO, "Preferences tab is selected");
				test.log(LogStatus.INFO, "VERIFY PREFERENCES ...");
				if(dailyUsageChangedTo=="OFF") {
					if(settingspagev2.parentZonePreferenceDailyUsageSwitch.getText().equalsIgnoreCase("OFF")) {
						test.log(LogStatus.INFO, "Daily usage setting has been retained to OFF after re-login");
					}
					else {
						test.log(LogStatus.FAIL, "Daily usage setting has not been retained to OFF after re-login");
						err1568++;
					}
				}
				else if(dailyUsageChangedTo=="ON") {
					if(settingspagev2.parentZonePreferenceDailyUsageSwitch.getText().equalsIgnoreCase("ON")) {
						test.log(LogStatus.INFO, "Daily usage setting has been retained to ON after re-login");
					}
					else {
						test.log(LogStatus.FAIL, "Daily usage setting has been not been retained to ON after re-login");
						err1568++;
					}
				}
				else {
					test.log(LogStatus.FAIL, "Failed to verify Daily usage setting after re-login");
					err1568++;
				}
				Utilities.verticalSwipe(driver);
				if(bedTimeChangedTo=="OFF") {
					if (settingspagev2.parentZonePreferenceBedTimeSwitch.getText().equalsIgnoreCase("OFF")) {
						test.log(LogStatus.INFO, "Bed Time switch setting has been retained to OFF after re-login");
					}
					else {
						test.log(LogStatus.FAIL, "Bed Time switch setting has not been retained to OFF after re-login");
						err1568++;
					}
				}
				else if(bedTimeChangedTo=="ON") {
					if (settingspagev2.parentZonePreferenceBedTimeSwitch.getText().equalsIgnoreCase("ON")) {
						test.log(LogStatus.INFO, "Bed Time switch setting has been retained to ON after re-login");
					}
					else {
						test.log(LogStatus.FAIL, "Bed Time switch setting has not been retained to ON after re-login");
						err1568++;
					}
				}
				else {
					test.log(LogStatus.FAIL, "Failed to verify Bed Time switch setting after re-login");
					err1568++;
				}
				Utilities.verticalSwipe(driver);
				Utilities.verticalSwipe(driver);
				if(emailUpdateChangedTo=="OFF") {
					if (settingspagev2.parentZonePreferenceEmailUpdateSwitch.getText().equalsIgnoreCase("OFF")) {
						test.log(LogStatus.INFO, "Email Update switch setting has been retained to OFF after re-login");
					}
					else {
						test.log(LogStatus.FAIL, "Email Update switch setting has not been retained to OFF after re-login");
						err1568++;
					}
				}
				else if(emailUpdateChangedTo=="ON") {
					if (settingspagev2.parentZonePreferenceEmailUpdateSwitch.getText().equalsIgnoreCase("ON")) {
						test.log(LogStatus.INFO, "Email Update switch setting has been retained to ON after re-login");
					}
					else {
						test.log(LogStatus.FAIL, "Email Update switch setting has not been retained to ON after re-login");
						err1568++;
					}
				}
				else {
					test.log(LogStatus.FAIL, "Failed to verify Email Update switch setting after re-login");
					err1568++;
				}
				//Final verification of 1568
				if(err1568==0) {				
					homepagev2.smokeresults("Verify if Preferences settings are retained post logging out and logging from same device",rowno1568, "PASS");
					homepagev2.reportPass("Verify if Preferences settings are retained post logging out and logging from same device is PASS");
				}
				else {
					homepagev2.smokeresults("Verify if Preferences settings are retained post logging out and logging from same device",rowno1568, "FAIL");
					test.log(LogStatus.FAIL, "Verify if Preferences settings are retained post logging out and logging from same device is FAIL");
					BasePageV2.takeScreenshot();
				}
				//Verify the activity tab
				Utilities.verticalSwipeReverse(driver);
				Utilities.verticalSwipeReverse(driver);
				Utilities.verticalSwipeReverse(driver);
				Utilities.verticalSwipeReverse(driver);
				test.log(LogStatus.INFO, "Swiped vertically to the top");
				if (Utilities.explicitWaitVisible(driver, settingspagev2.activityTab, 10)) {
					settingspagev2.activityTab.click();
					test.log(LogStatus.INFO, "Activity tab is selected");
					//Usage Week
					Utilities.verticalSwipe(driver);
					test.log(LogStatus.INFO, "VERIFY DATA FROM ACTIVITY TAB ...");
					for(int scroll=0;scroll<=2;scroll++) {
						if(Utilities.explicitWaitVisible(driver, settingspagev2.usageWeek, 10)) {
							String usageStatsWeekDataAfterReLogin=settingspagev2.usageWeek.getAttribute("text");
							if(usageStatsWeekDataAfterReLogin.equals(usageStatsWeekData)) 
								test.log(LogStatus.INFO, "Week displayed under Usage Stats: "+usageStatsWeekDataAfterReLogin+" is same after re-login");
							else {
								test.log(LogStatus.FAIL, "Week displayed under Usage Stats: "+usageStatsWeekDataAfterReLogin+" is not same after re-login");
								err1569++;
							}
							break;
						}
						else { 
							Utilities.verticalSwipe(driver);
							if(scroll==2) {
								test.log(LogStatus.FAIL, "Failed to locate Week under Usage Stats");
								err1569++;
							}
						}
					}
					
					//Usage Episodes
					for(int scroll=0;scroll<=2;scroll++) {
						if(Utilities.explicitWaitVisible(driver, settingspagev2.usageEpisodesCount, 10)) {
							String usageStatsEpisodeDataAfterReLogin=settingspagev2.usageEpisodesCount.getAttribute("text");
							if(usageStatsEpisodeDataAfterReLogin.equals(usageStatsEpisodeData)) 
								test.log(LogStatus.INFO, "Number of watched Episodes displayed under Usage Stats: "+usageStatsEpisodeDataAfterReLogin+" is same after re-login");			
							else {
								test.log(LogStatus.FAIL, "Number of watched Episodes displayed under Usage Stats: "+usageStatsEpisodeDataAfterReLogin+" is not same after re-login");			
								err1569++;
							}
							break;
						}
						else {
							Utilities.verticalSwipe(driver);
							if(scroll==2) {
								test.log(LogStatus.FAIL, "Failed to locate Number of watched Episodes under Usage Stats");
								err1569++;
							}													
						}							
					}
					
					//Usage total hours
					for(int scroll=0;scroll<=2;scroll++) {
						if(Utilities.explicitWaitVisible(driver, settingspagev2.usageTotalHours, 10)) {
							String usageStatsTotalHoursDataAfterReLogin=settingspagev2.usageTotalHours.getAttribute("text");
							if(usageStatsTotalHoursDataAfterReLogin.equals(usageStatsTotalHoursData)) 
								test.log(LogStatus.INFO, "Total usage hours displayed under Usage Stats: "+usageStatsTotalHoursDataAfterReLogin+" is same after re-login");		
							else {
								test.log(LogStatus.FAIL, "Total usage hours displayed under Usage Stats: "+usageStatsTotalHoursDataAfterReLogin+" is not same after re-login");
								err1569++;
							}
							break;
						}
						else {
							Utilities.verticalSwipe(driver);
							if(scroll==2) 
								test.log(LogStatus.FAIL, "Failed to locate Total usage hours under Usage Stats");
						}
					}
						
					//Learn stats first block name
					for(int scroll=0;scroll<=2;scroll++) {
						if(Utilities.explicitWaitVisible(driver, settingspagev2.learnStatsFirstBlock, 10)) {
							String learnStatsFirstBlockNameDataAfterReLogin=settingspagev2.learnStatsFirstBlock.getAttribute("text");
							if(learnStatsFirstBlockNameDataAfterReLogin.equals(learnStatsFirstBlockNameData))
								test.log(LogStatus.INFO, "First Block under Learn Stats is: "+learnStatsFirstBlockNameDataAfterReLogin+" is same after re-login");
							else {
								test.log(LogStatus.FAIL, "First Block under Learn Stats is: "+learnStatsFirstBlockNameDataAfterReLogin+" is not same after re-login");
								err1569++;
							}
							break;
						}
						else {
							Utilities.verticalSwipe(driver);
							if(scroll==2) {
								test.log(LogStatus.FAIL, "Failed to locate name of the first block under Learns Stats");
								err1569++;
							}
						}
							
					}
					
					//Learn stats first block correct score
					for(int scroll=0;scroll<=1;scroll++) {
						try {
							if(Utilities.explicitWaitVisible(driver, settingspagev2.learnStatsCorrect.get(0), 10)) {
								String learnStatsFirstBlockCorrectDataAfterReLogin=settingspagev2.learnStatsCorrect.get(0).getAttribute("text");
								if(learnStatsFirstBlockCorrectDataAfterReLogin.equals(learnStatsFirstBlockCorrectData))
									test.log(LogStatus.INFO, "Correct Score of First Block under Learn Stats is: "+learnStatsFirstBlockCorrectDataAfterReLogin+" is same after re-login");
								else
									test.log(LogStatus.FAIL, "Correct Score of First Block under Learn Stats is: "+learnStatsFirstBlockCorrectDataAfterReLogin+" is not same after re-login");
								break;
							}
						}
						catch(Exception e) {
							Utilities.verticalSwipe(driver);
							if(scroll==1) {
								test.log(LogStatus.FAIL, "Failed to locate Correct Score of First Block under Learns Stats");	
								err1569++;
							}
						}		
					}
					
					//Scroll until Recent Activity
					for(int scroll=0;scroll<=7;scroll++) {
						if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneRecentActivity, 2)) {
							test.log(LogStatus.INFO, "Swiped to RECENT ACTIVITY");
							break;
						}
						else
							Utilities.verticalSwipe(driver);
					}
					//First item of Recent Activity
					for(int scroll=0;scroll<=1;scroll++) {
						try {
							if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneRecentActivityItemNames.get(0), 2)) {
								String recentActivityFirstItemDataAfterReLogin=settingspagev2.parentZoneRecentActivityItemNames.get(0).getAttribute("text");
								if(recentActivityFirstItemDataAfterReLogin.equals(recentActivityFirstItemData))
									test.log(LogStatus.INFO, "First most recent activity is "+recentActivityFirstItemDataAfterReLogin+" is same after re-login");
								else {
									test.log(LogStatus.FAIL, "First most recent activity is "+recentActivityFirstItemDataAfterReLogin+" is not same after re-login");
									err1569++;
								}
								break;
							}
						}
						catch(Exception e) {
							Utilities.verticalSwipe(driver);
						    if(scroll==1) {
						    	test.log(LogStatus.FAIL, "Failed to locate first most recent activity details");
						    	err1569++;
						    }
						}					
					}
					//Second item of Recent Activity
					for(int scroll=0;scroll<=1;scroll++) {
						try {
							if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneRecentActivityItemNames.get(1), 2)) {
								String recentActivitySecondItemDataAfterReLogin=settingspagev2.parentZoneRecentActivityItemNames.get(1).getAttribute("text");
								if(recentActivitySecondItemDataAfterReLogin.equals(recentActivitySecondItemData))
									test.log(LogStatus.INFO, "First most recent activity is "+recentActivitySecondItemDataAfterReLogin+" is same after re-login");
								else {
									test.log(LogStatus.FAIL, "First most recent activity is "+recentActivitySecondItemDataAfterReLogin+" is not same after re-login");
									err1569++;
								}
								break;
							}
						}					
						catch(Exception e) {
							Utilities.verticalSwipe(driver);
						    if(scroll==1) {
						    	test.log(LogStatus.FAIL, "Failed to locate second most recent activity details");
						    	err1569++;
						    }		    		
						}					
					}
					//Final verification of 1569
					if(err1569==0) {	
						homepagev2.smokeresults("Verify if Data in Activity tab is retained post logging out and logging from same device",rowno1569, "PASS");
						homepagev2.reportPass("Verify if Data in Activity tab is retained post logging out and logging from same device is PASS");
					}
					else {
						homepagev2.smokeresults("Verify if Data in Activity tab is retained post logging out and logging from same device",rowno1569, "FAIL");
						test.log(LogStatus.FAIL, "Verify if Data in Activity tab is retained post logging out and logging from same device is FAIL");
						BasePageV2.takeScreenshot();
					}
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to Switch Profile screen");
				}
				else 
					test.log(LogStatus.FAIL, "Activity tab is not visible");	
			}
			else 
				test.log(LogStatus.FAIL, "Preferences tab is not visible");							
		}
		else 
			test.log(LogStatus.FAIL, "Parent zone Page is not displayed");					
	}
	else 
		test.log(LogStatus.FAIL, "Parent Zone button is not displayed");
	
}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
