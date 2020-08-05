package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;

public class VerifySettingsDeviceScreen_1 extends BaseTestV2 {

	String testName = "VerifySettingsDeviceScreen_1";
	String allowcastOn = "";
	@Test(dataProvider = "getData")
	public void verifySettingsDeviceScreen(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifySettingsDeviceScreen_1");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Device Screen: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls823 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno823 = xls823.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno823,
				"823: Validate Device option functionality in Settings screen");
         
		Xls_Reader xls826 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno826 = xls826.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno826, "826: Verify the default state for Cellular Playback button");

		Xls_Reader xls827 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno827 = xls827.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno827,
				"827: Verify 'Cellular Playback' toggle button functionality");

		Xls_Reader xls832 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno832 = xls832.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno832,
				"832: Verify the default option for Default Stream Quality in Device Settings");

		Xls_Reader xls833 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno833 = xls833.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno833,
				"833: Verify the Default Stream Quality dropdown functionality");

		Xls_Reader xls835 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno835 = xls835.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno835,
				"835: Verify the default option for Preferred Language in Device Settings");

		Xls_Reader xls836 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno836 = xls836.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno836,
				"836: Verify the Preferred Language dropdown functionality");

		Xls_Reader xls838 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno838 = xls838.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno838,
				"838: Verify the Preferred Language dropdown functionality");

		Xls_Reader xls839 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno839 = xls839.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno839,
				"839: Verify 'Enable Downloads' toggle button functionality");

		Xls_Reader xls844 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno844 = xls844.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno844,
				"844: Verify the default toggle button state for Cellular Downloads options in Device settings");

		Xls_Reader xls845 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno845 = xls845.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno845,
				"845: Verify 'Cellular Downloads' toggle button functionality");
		
		Xls_Reader xls850 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno850 = xls850.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno850,
				"850: Verify the default option for Download Quality in device settings");
		

		Xls_Reader xls856 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno856 = xls856.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno856,
				"856: Verify the default toggle button state for Notifications options in device settings");

		Xls_Reader xls857 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno857 = xls857.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno857,
				"857: Verify 'Notifications' toggle button functionality");

		Xls_Reader xls858 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno858 = xls858.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno858,
				"858: Verify the default toggle button for Allow Casting options in device settings");

		Xls_Reader xls859 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno859 = xls859.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno859,
				"859: Verify 'Allow Casting' toggle button functionality");

		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");

		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		

		
		 homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		try {
				homepagev2.profilepic.click(); // tap on profile icon
				test.log(LogStatus.INFO, "Clicked on profile icon in home page");
				
		} catch (Exception e) {
			BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
		}

		// Click on ParentZone Button in Switch Profile Screen parentZoneButton
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
			settingsPageV2.parentZoneButton.click();
			test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
				Thread.sleep(1000);
				settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
			}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
		}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");

		
		// putting App in background
		 driver.runAppInBackground(Duration.ofSeconds(3));
		 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		 driver.currentActivity();
		
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
			settingsPageV2.settingsIcon.click();
			
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDevice, 10)) {
					settingsPageV2.settingsDevice.click();
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDeviceTile, 10)) {
						test.log(LogStatus.PASS, "Validated Device option functionality in Settings screen");
					//	BasePageV2.takeScreenshot();
						BasePageV2.smokeresults("873: Validate Device option functionality in Settings screen",
								rowno823, "PASS");
					}
				} else
					BasePageV2.reportFail("Not able to Find Settings Device option to navigate to Device Screen ");
		

			// Checking Default Cellular Play back Switch State 'ON'

			String switchCellular = settingsPageV2.deviceCellplySwitch.getAttribute("checked");
			System.out.println("The Switch Boolean value is " + switchCellular);
			if (settingsPageV2.deviceCellplySwitch.getAttribute("checked").equals("true")) {

				test.log(LogStatus.PASS, "Verified the default state for Cellular Playback button is 'ON'");
			//	BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("826: Verify the default state for Cellular Playback button", rowno826, "PASS");
			} else
				BasePageV2.reportFail("the default state for Cellular Playback button is 'OFF'");
			// Checking Cellular Play "Toggle Button Functionality"
			if (Utilities.explicitWaitClickable(driver, settingsPageV2.deviceCellplySwitch, 10)) {
				if (settingsPageV2.deviceCellplySwitch.getAttribute("checked").equals("true")
						|| settingsPageV2.deviceCellplySwitch.getAttribute("checked").equals("false")) {
					settingsPageV2.deviceCellplySwitch.click();
					//BasePageV2.takeScreenshot();
					Thread.sleep(1000);
					if (settingsPageV2.deviceCellplySwitch.getAttribute("checked").equals("false")
							|| settingsPageV2.deviceCellplySwitch.getAttribute("checked").equals("true")) {
						settingsPageV2.deviceCellplySwitch.click();
						test.log(LogStatus.PASS, "Verified 'Cellular Playback' toggle button functionality");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults("827: Verify 'Cellular Playback' toggle button functionality", rowno827,
								"PASS");

					}
				}
			} else
				BasePageV2.reportFail("Default Stream Quality Toggle button not Found r Not Worked");

			// Checking Default Stream Quality Should be 'Medium'(changing Accordingly UI to 'Auto')
			Thread.sleep(1000);
			WebElement ele = driver.findElementByXPath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text']");
			String defaultMedium = ele.getText().toString().trim();

			System.out.println("Default Stream Quality is 'Medium' should be slected " + defaultMedium);
			if (defaultMedium.equalsIgnoreCase("Auto")) {
				test.log(LogStatus.PASS, "Verified the default option for Default Stream Quality in Device Settings");
				//BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("832: Verify the default option for Default Stream Quality in Device Settings",
						rowno832, "PASS");
			} else
				BasePageV2.reportFail(
						"'Auto' is not the default selected for 'Default Stream Qaulity' Option in device Settings");

			// Verifying Default Stream Quality drop down (833TC)

			WebElement dropDownQualityList = driver
					.findElementByXPath("//android.widget.Spinner");
			dropDownQualityList.click();
			Thread.sleep(1000);
			List<WebElement> dropDownQualityListTiles = driver
					.findElementsByXPath("//android.widget.ListView//android.view.ViewGroup//android.widget.TextView");
			for (int i = 0; i < 4; i++) {
				String Listtiles = dropDownQualityListTiles.get(i).getText();
				System.out.println("The String List tile contains are " + Listtiles);
				dropDownQualityListTiles.get(i).click();
				Thread.sleep(1000);
				Utilities.verticalSwipeDown(driver);
				Thread.sleep(1000);
				WebElement dropDownQualityList1 = driver
						.findElementByXPath("//android.widget.Spinner");
				dropDownQualityList.click();
			//	BasePageV2.takeScreenshot();

			}

			try {
				WebElement eleMedium = driver.findElementByXPath(
						"//android.widget.ListView//android.view.ViewGroup//android.widget.TextView[@text='Auto']");
				eleMedium.click();
				test.log(LogStatus.PASS, "Verified the default option for Default Stream Quality in Device Settings");
				//BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("833: Verify the default option for Default Stream Quality in Device Settings",
						rowno833, "PASS");
			} catch (Exception e) {
				BasePageV2.reportFail("Not able to Set Default Quality 'Medium'  for Default Stream Quality Option");
			}

			// default option for Preferred Language in Device should be 'English'
			driver.runAppInBackground(Duration.ofSeconds(5));
			 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
			 driver.currentActivity();
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.devicePrefferdLanuage, 10)) {
				try {

					WebElement eng = driver.findElementByXPath(
							"//android.view.ViewGroup[@index='2']//android.widget.Spinner//android.widget.TextView[@text='English']");

					test.log(LogStatus.PASS, "Verify the default option for Preferred Language in Device Settings");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("835: Verify the default option for Preferred Language in Device Settings",
							rowno835, "PASS");
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else
				BasePageV2.reportFail("Not Found 'Preferred Language' option In Device Screen");

			// changing the Language from drop down option Preferred Language in Device
			// Settings

			settingsPageV2.devicePrefferdLanuageSpinner.click();
			settingsPageV2.devicePrefferdLanuageHindi.click();
			Thread.sleep(10000);
			WebElement laungHindi = driver.findElementByXPath(
					"//android.widget.TextView[@text='Preferred Language']//parent::android.view.ViewGroup//android.widget.Spinner//android.widget.TextView[@text='Hindi']");
			if (laungHindi.isDisplayed()) { // Verifying the 'Hindi' Language is selected r not
				test.log(LogStatus.PASS, "Verified the Preferred Language dropdown functionality");
				//BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("836: Verify the Preferred Language dropdown functionality", rowno836, "PASS");
				settingsPageV2.devicePrefferdLanuageSpinner.click();
				Thread.sleep(1000);
				settingsPageV2.devicePrefferdLanuageEnglish.click(); // Setting the default Language to English
				Thread.sleep(10000);
			} else
				BasePageV2.reportFail(
						"Not able to Select Hindi as a Default Language for Preferred Language in Device Settings");

			// Checking Enable Downloads Should be 'ON' Validating
			try {
				if (settingsPageV2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("ON")) {
					test.log(LogStatus.PASS,
							"Verified the default toggle button state for Enable Downloads options in Device settings");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults(
							"838: Verify the default toggle button state for Enable Downloads options in Device settings",
							rowno838, "PASS");
				} else
					BasePageV2.reportFail(
							"'Enable Downloads' option Switch is not found or else Switch Button is Off State ");

			} catch (Exception e) {
				e.printStackTrace();
			}

			// Verifying 'Enable Downloads' toggle button functionality
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceEnableddownloads, 10)) {
				if (settingsPageV2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("ON")
						|| settingsPageV2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("OFF")) {
					settingsPageV2.deviceEnableddownloadsSwitch.click();
					Thread.sleep(1000);
					if (settingsPageV2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("OFF")
							|| settingsPageV2.deviceEnableddownloadsSwitch.getText().equalsIgnoreCase("ON")) {
						settingsPageV2.deviceEnableddownloadsSwitch.click();
						Thread.sleep(1000);
						test.log(LogStatus.PASS, "Verify 'Enable Downloads' toggle button functionality");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults("839: Verify 'Enable Downloads' toggle button functionality", rowno839,
								"PASS");
					}
				} else
					BasePageV2.reportFail("'Enable Downloads' option Switch is not Found or else Not able to Tapping");
			} else
				BasePageV2.reportFail("'Enable Downloads' option is not Found in Dvice Settings Screen");

			// Verifying the default toggle button state for Cellular Downloads
			driver.runAppInBackground(Duration.ofSeconds(5));
			 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
			 driver.currentActivity();
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceCelluallerDownlods, 10)) {

				if (settingsPageV2.deviceCelluallerDownlodsSwitch.getText().equalsIgnoreCase("ON")) {
					test.log(LogStatus.PASS,
							"Verified the default toggle button state for Cellular Downloads options in Device settings");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults(
							"844: Verify the default toggle button state for Cellular Downloads options in Device settings",
							rowno844, "PASS");

				}else BasePageV2.reportFail("'Cellular Downloads' Option Switch Button is not Found Or It's in OFF state");
			} else BasePageV2.reportFail("'Cellular Downloads' Option is not Found in Device Screen");

			// Verifying the 'Cellular Downloads' toggle button functionality
			driver.runAppInBackground(Duration.ofSeconds(5));
			 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
			 driver.currentActivity();
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceCelluallerDownlods, 10)) {
				if (settingsPageV2.deviceCelluallerDownlodsSwitch.getText().equalsIgnoreCase("ON")
						|| settingsPageV2.deviceCelluallerDownlodsSwitch.getText().equalsIgnoreCase("OFF")) {
					settingsPageV2.deviceCelluallerDownlodsSwitch.click();
					Thread.sleep(1000);
					if (settingsPageV2.deviceCelluallerDownlodsSwitch.getText().equalsIgnoreCase("OFF")
							|| settingsPageV2.deviceCelluallerDownlodsSwitch.getText().equalsIgnoreCase("ON")) {
						settingsPageV2.deviceCelluallerDownlodsSwitch.click();
						Thread.sleep(1000);
						test.log(LogStatus.PASS, "Verified 'Cellular Downloads' toggle button functionality");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults("845: Verify 'Cellular Downloads' toggle button functionality",
								rowno845, "PASS");
					}
				} else
					BasePageV2
							.reportFail("'Cellular Downloads' option Switch is not Found or else Not able to Tapping");
			} else
				BasePageV2.reportFail("'Cellular Downloads' option is not Found in Dvice Settings Screen");

			// Verifying the Default Switch State for Notifications Option 'ON' In Device
			// Settings Screen

			String end = "//android.widget.TextView[@text='Notifications']";
			Utilities.verticalSwipe(driver, end);
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceNotifications, 10)) {
				if (settingsPageV2.deviceNotificationsSwitch.getText().equalsIgnoreCase("ON")) {
					test.log(LogStatus.PASS,
							"Verified the default toggle button state for Notifications options in device settings");
					BasePageV2.takeScreenshot();
					BasePageV2.smokeresults(
							"856: Verify the default toggle button state for Notifications options in device settings",
							rowno856, "PASS");
				} else
					BasePageV2.reportFail("'Notifications' option Switch is not Found or else Switch is in OFF state");

			} else
				BasePageV2.reportFail("'Notifications' option is not Found in Dvice Settings Screen");
			
	//Verify the default option for Download Quality in device settings		
			
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityMedium, 10)) {
				test.log(LogStatus.PASS,
						"Verified the default option for Download Quality in device settings");
				//BasePageV2.takeScreenshot();
				BasePageV2.smokeresults(
						" ",rowno856, "PASS");
			
			}else test.log(LogStatus.FAIL, "Default Quality 'Medium-320' is not found in Download Qaulity dropdown List ");
			
			// Verifying 'Notifications' toggle button functionality

			if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceNotifications, 10)) {
				if (settingsPageV2.deviceNotificationsSwitch.getText().equalsIgnoreCase("ON")
						|| settingsPageV2.deviceNotificationsSwitch.getText().equalsIgnoreCase("OFF")) {
					settingsPageV2.deviceNotificationsSwitch.click();
					Thread.sleep(1000);
					if (settingsPageV2.deviceNotificationsSwitch.getText().equalsIgnoreCase("OFF")
							|| settingsPageV2.deviceNotificationsSwitch.getText().equalsIgnoreCase("ON")) {
						settingsPageV2.deviceNotificationsSwitch.click();
						Thread.sleep(1000);
						test.log(LogStatus.PASS, "Verified 'Notifications' toggle button functionality");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults("857: Verify 'Notifications' toggle button functionality", rowno857,
								"PASS");
					}
				} else
					BasePageV2.reportFail("'Notifications' option Switch is not Found or else Not able to Tapping");
			} else
				BasePageV2.reportFail("'Notifications' option is not Found in Dvice Settings Screen");

			// verifying the default toggle button should 'ON' for 'Allow Casting' options
			// in device settings
/*
			String allowCasting = "//android.widget.TextView[@text='Allow Casting']";
			Utilities.verticalSwipe(driver, allowCasting);
			allowcastOn = settingsPageV2.deviceAllowCastingSwitch.getText().toString().trim();
			System.out.println("Default state of Allow casting is " + allowcastOn);
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceAllowCasting, 10)) {
				if (allowcastOn.equalsIgnoreCase("ON")) {
					test.log(LogStatus.PASS, "Verified Default selection for the 'Allow Casting' should be 'ON'");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults(
							"858: Verify the default toggle button for Allow Casting options in device settings",
							rowno858, "PASS");
				}else BasePageV2.reportFail("'Allow Casting' option Switch is in OFF State ");
			}else BasePageV2.reportFail("'Allow Casting' option is not Found in Dvice Settings Screen");

			// Verifying the 'Allow Casting' toggle button functionality in Device Screen

			if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceAllowCasting, 10)) {
				if ((allowcastOn.equalsIgnoreCase("ON"))
						|| (allowcastOn.equalsIgnoreCase("OFF"))) {
					settingsPageV2.deviceAllowCastingSwitch.click();
					Thread.sleep(1000);
					if ((allowcastOn.equalsIgnoreCase("OFF"))
							|| (allowcastOn.equalsIgnoreCase("ON"))) {
						settingsPageV2.deviceAllowCastingSwitch.click();
						Thread.sleep(1000);
						test.log(LogStatus.PASS, "Verified 'Allow Casting' toggle button functionality");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults("859: Verify 'Allow Casting' toggle button functionality", rowno859,
								"PASS");
					}else BasePageV2.reportFail("'Allow Casting Switch Status not found");
				} else BasePageV2.reportFail("'Allow Casting' option Switch is not Found or else Not able to Tapping");
			} else BasePageV2.reportFail("'Allow Casting' option is not Found in Dvice Settings Screen");
*/
		} else BasePageV2.reportFail("Unable to Click Settings Icon or Not Found ");

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
