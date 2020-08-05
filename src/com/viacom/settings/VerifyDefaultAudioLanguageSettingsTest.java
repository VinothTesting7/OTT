package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

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
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifyDefaultAudioLanguageSettingsTest extends BaseTestV2 {

	String pin = "1111";
	String testName = "VerifyDefaultAudioLanguageSettingsTest";
	String testCase = "Verify the default option for Audio Download Quality in device settings";
	String defaultAudioQualityExpected = "Medium";

	@Test(dataProvider = "getData")
	public void VerifyDefaultAudioLanguageSettings(Hashtable<String, String> data) throws Exception {

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		// Verify profile selection in 'Select Profile' screen
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase);

		// Verify 'Create new' button functionality in 'Select Profile' screen

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SettingsPageV2 settings = new SettingsPageV2(driver, test);

		HomePageV2.login("karthiktskaaru5278@gmail.com", "karu5278");

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5)) {
			homepagev2.profilepic.click();
			
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, settings.parentZoneButton, 10)) {
				settings.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settings.parentPinContainer, 10)) {
					Thread.sleep(1000);
					settings.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
			
			
			// putting App in background
			 driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
			 
			if (Utilities.explicitWaitVisible(driver, homepagev2.settings, 5)) {
				homepagev2.settings.click();
				
					Utilities.verticalSwipe(driver, settings.deviceOptionSettings);
					if (Utilities.explicitWaitVisible(driver, settings.deviceOptionSettings, 20)) {
						settings.deviceOptionSettings.click();
					} else
						BasePageV2.reportFail("Device button is not displayed");

					if (Utilities.explicitWaitVisible(driver, settings.deviceOptionpage, 20)) {
						test.log(LogStatus.INFO, "Device Page is displayed");
					} else
						BasePageV2.reportFail("Device Page is not displayed");

					Utilities.verticalSwipe(driver, settings.audioDefaultQuality);

					if (Utilities.explicitWaitVisible(driver, settings.audioDefaultQuality, 20)) {
						String audioDownloadQualityActual = settings.audioDefaultQuality.getText();
						test.log(LogStatus.INFO, "Default Audio Language displayed is: " + audioDownloadQualityActual);
						if (audioDownloadQualityActual.equalsIgnoreCase(defaultAudioQualityExpected)) {
							BasePageV2.reportPass("Test Case: " + testCase + " is Pass");
							HomePageV2.smokeresults(testCase, rowno, "Pass");

						} else {
							test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
							HomePageV2.smokeresults(testCase, rowno, "Fail");
						}

					} else
						BasePageV2.reportFail("Audio Download Quality option is not displayed");

		

			} else
				BasePageV2.reportFail("Setting button not displayed");

		} else
			BasePageV2.reportFail("Profile Pic is not displayed");

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
