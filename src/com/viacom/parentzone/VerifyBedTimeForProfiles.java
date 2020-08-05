package com.viacom.parentzone;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
// settings parentZonePreferenceBedTimeSwitch
public class VerifyBedTimeForProfiles extends BaseTestV2{
	
	String testName = "VerifyBedTimeForProfiles";
	@Test(dataProvider = "getData")
	public void VerifyBedTimeForProfiles(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyBedTimeForProfiles");
		test.log(LogStatus.INFO, "VerifyBedTimeForProfiles");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_1331 Verify if Bed Time option is specific to Individual child profile

		//Launch Voot kids app
		 launchApp();
		 test.log(LogStatus.INFO, "Launched application");
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
		 String currentTime="";
		 String[] timeToSelect;
		 String sleepTimeProfile1="";
		 String sleepTimeProfile2="";
		 boolean setSleepTime=false;
		 boolean selectedSecondProfile=false;
		 boolean selectedFirstProfile=false;
		 //homepagev2.login(data.get("Email"),data.get("Password"));
		 //Login module to be added
		 //homepagev2.signup();
	 
		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {
			homepagev2.profilepic.click();
			test.log(LogStatus.INFO, "Clicked on Profile pic icon");
			if (Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZoneButton, 20)) {
				settingspagev2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Tapped on Parent Zone button");
				if (Utilities.explicitWaitVisibleNew(driver, launchpagev2.pinContainer, 20)) {
					launchpagev2.pinContainer.clear();
					launchpagev2.pinContainer.sendKeys("1111");
					test.log(LogStatus.INFO, "Entered PIN");
				} 
				else
					BasePageV2.reportFail("Pin Container not displayed");
				if (Utilities.explicitWaitVisibleNew(driver, settingspagev2.parentZonePage, 20)) {
					test.log(LogStatus.INFO, "Parent zone Page is displayed");
					if (Utilities.explicitWaitClickableNew(driver, homepagev2.preferencesTab, 20)) {
						Thread.sleep(2000);
						homepagev2.preferencesTab.click();
						test.log(LogStatus.INFO, "Preferences tab is selected");
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						if(Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZonePreferenceBedTimeSwitchOFF, 10)) {
							try {
								settingspagev2.parentZonePreferenceBedTimeSwitchOFF.click();
								test.log(LogStatus.INFO, "Switched ON Bed time");
							}
							catch(Exception e) {
								test.log(LogStatus.INFO, "Failed to switch ON Bed time");
							}
						}
						if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.parentZonePreferenceBedTimeSwitchON, 10)) {
							test.log(LogStatus.INFO, "Bed time switch is ON");
							currentTime=driver.getDeviceTime();
							//Get time 1 hour more than current device time
							timeToSelect=homepagev2.getTimeToSelect(currentTime);
							if(Utilities.explicitWaitClickableNew(driver, settingspagev2.sleepTime, 10)) {
								try {
									Thread.sleep(2000);
									sleepTimeProfile1=settingspagev2.sleepTime.getAttribute("text");
									test.log(LogStatus.INFO, "Sleep Time displayed for Profile 1 is "+sleepTimeProfile1);
									settingspagev2.sleepTime.click();
									test.log(LogStatus.INFO, "Clicked on sleep time");
									//Select a time 1 hour more than current device time
									if(timeToSelect[1].equals("AM")) {
										if(Utilities.explicitWaitClickableNew(driver, settingspagev2.clockAM, 10)) {
											try {
												settingspagev2.clockAM.click();
												test.log(LogStatus.INFO, "Tapped on AM option");
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to tap on AM option");
											}
										}
										else {
											test.log(LogStatus.FAIL, "AM option is not clickable");
										}
									}
									else if(timeToSelect[1].equals("PM")) {
										if(Utilities.explicitWaitClickableNew(driver, settingspagev2.clockPM, 10)) {
											try {
												settingspagev2.clockPM.click();
												test.log(LogStatus.INFO, "Tapped on PM option");
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to tap on PM option");
											}
										}
										else {
											test.log(LogStatus.FAIL, "PM option is not clickable");
										}
									}
									try {
										driver.findElement(By.xpath("//*[@resource-id='android:id/radial_picker']//*[@content-desc='"+timeToSelect[0]+"']")).click();
										test.log(LogStatus.INFO, "Tapped on time "+timeToSelect[0]);
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to tap on time "+timeToSelect[0]);
									}
									if(Utilities.explicitWaitClickableNew(driver, settingspagev2.clockOK, 10)) {
										try {
											settingspagev2.clockOK.click();
											test.log(LogStatus.INFO, "Tapped on OK button");
											setSleepTime=true;
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to tap on OK button");
										}
									}
									else {
										test.log(LogStatus.FAIL, "OK button is not clickable");
									}
									try {
										Thread.sleep(2000);
										sleepTimeProfile1=settingspagev2.sleepTime.getAttribute("text");
										test.log(LogStatus.INFO, "Sleep Time displayed for Profile 1 after changing is "+sleepTimeProfile1);
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to get the sleep time of Profile 1");
									}
									
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Error when setting sleep time");
								}
							}
							else {
								test.log(LogStatus.FAIL, "Sleep time is not clickable");
							}
						}
						else {
							test.log(LogStatus.FAIL, "Error switching ON Bed time");
						}
					}
					else {
						test.log(LogStatus.FAIL, "Failed to locate Preferences tab");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Failed to navigate to Parent Zone page");
				}
			}
		}
		if(setSleepTime==true) {
			driver.navigate().back();
			try {
				homepagev2.profileListImages.get(1).click();
				test.log(LogStatus.INFO, "Tapped on second profile: "+homepagev2.profileListNames.get(1).getAttribute("text"));
				Thread.sleep(2000);
				selectedSecondProfile=true;
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to select second profile");
			}
		}
		if(selectedSecondProfile==true) {
			if (Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZoneButton, 20)) {
				settingspagev2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Tapped on Parent Zone button");
				if (Utilities.explicitWaitVisibleNew(driver, launchpagev2.pinContainer, 20)) {
					launchpagev2.pinContainer.clear();
					launchpagev2.pinContainer.sendKeys("1111");
					test.log(LogStatus.INFO, "Entered PIN");
				} 
				else
					BasePageV2.reportFail("Pin Container not displayed");
				if (Utilities.explicitWaitVisibleNew(driver, settingspagev2.parentZonePage, 20)) {
					test.log(LogStatus.INFO, "Parent zone Page is displayed");
					if (Utilities.explicitWaitClickableNew(driver, homepagev2.preferencesTab, 20)) {
						Thread.sleep(2000);
						homepagev2.preferencesTab.click();
						test.log(LogStatus.INFO, "Preferences tab is selected");
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						if(Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZonePreferenceBedTimeSwitchOFF, 10)) {
							try {
								settingspagev2.parentZonePreferenceBedTimeSwitchOFF.click();
								test.log(LogStatus.INFO, "Switched ON Bed time");
							}
							catch(Exception e) {
								test.log(LogStatus.INFO, "Failed to switch ON Bed time");
							}
						}
						if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.parentZonePreferenceBedTimeSwitchON, 10)) {
							test.log(LogStatus.INFO, "Bed time switch is ON");
							if(Utilities.explicitWaitClickableNew(driver, settingspagev2.sleepTime, 10)) {
								try {
									Thread.sleep(2000);
									sleepTimeProfile2=settingspagev2.sleepTime.getAttribute("text");
									test.log(LogStatus.INFO, "Sleep Time displayed for Profile 2 is "+sleepTimeProfile2);
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to fetch Sleep time");
								}
							}
							if(sleepTimeProfile1.equals(sleepTimeProfile2)) {
								test.log(LogStatus.FAIL, "Sleep time in Profile 2 is same as in Profile 1");
								test.log(LogStatus.PASS, "Verify if Bed Time option is specific to Individual child profile is FAIL");
								if(!Utilities.setResultsKids("VK_1331", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							
							}
							else {
								test.log(LogStatus.INFO, "Sleep time in Profile 2 is different from Profile 1");
								test.log(LogStatus.PASS, "Verify if Bed Time option is specific to Individual child profile is PASS");
								if(!Utilities.setResultsKids("VK_1331", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							//Turn OFF bed time in PRofile 2
							if(Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZonePreferenceBedTimeSwitchON, 10)) {
								try {
									settingspagev2.parentZonePreferenceBedTimeSwitchON.click();
									test.log(LogStatus.INFO, "Switched OFF Bed time for Profile 2");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to switch OFF Bed time for Profile 2");
								}
							}
							else {
								test.log(LogStatus.FAIL, "Bed time switch is not clickable for Profile 2");
							}
							//Turn OFF bed time in PRofile 1
							driver.navigate().back();
							test.log(LogStatus.INFO, "Navigated back to Switch Profile");
							try {
								homepagev2.profileListImages.get(0).click();
								test.log(LogStatus.INFO, "Tapped on first profile: "+homepagev2.profileListNames.get(0).getAttribute("text"));
								Thread.sleep(2000);
								selectedFirstProfile=true;
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to select first profile");
							}
							if(selectedFirstProfile==true) {
								if (Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZoneButton, 20)) {
									settingspagev2.parentZoneButton.click();
									test.log(LogStatus.INFO, "Tapped on Parent Zone button");
									if (Utilities.explicitWaitVisibleNew(driver, launchpagev2.pinContainer, 20)) {
										launchpagev2.pinContainer.clear();
										launchpagev2.pinContainer.sendKeys("1111");
										test.log(LogStatus.INFO, "Entered PIN");
									} 
									else
										BasePageV2.reportFail("Pin Container not displayed");
									if (Utilities.explicitWaitVisibleNew(driver, settingspagev2.parentZonePage, 20)) {
										test.log(LogStatus.INFO, "Parent zone Page is displayed");
										if (Utilities.explicitWaitClickableNew(driver, homepagev2.preferencesTab, 20)) {
											Thread.sleep(2000);
											homepagev2.preferencesTab.click();
											test.log(LogStatus.INFO, "Preferences tab is selected");
											Utilities.verticalSwipe(driver);
											Utilities.verticalSwipe(driver);
											if(Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZonePreferenceBedTimeSwitchON, 10)) {
												try {
													settingspagev2.parentZonePreferenceBedTimeSwitchON.click();
													test.log(LogStatus.INFO, "Switched OFF Bed time for Profile 1");
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Failed to switch OFF Bed time for Profile 1");
												}
											}
											else {
												test.log(LogStatus.FAIL, "Bed time switch is not clickable");
											}
										}
										else {
											test.log(LogStatus.FAIL, "Preferences tab is not clickable");
										}
									}
									else {
										test.log(LogStatus.INFO, "Parent zone Page is displayed");
									}
								}
							}
						}
					}
				}
			}
		}
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
