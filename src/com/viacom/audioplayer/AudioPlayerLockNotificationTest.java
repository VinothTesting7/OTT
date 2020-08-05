package com.viacom.audioplayer;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

@SuppressWarnings("deprecation")
public class AudioPlayerLockNotificationTest extends BaseTestV2 {

	String testName = "AudioPlayerLockNotificationTest";
	String testCase = "'Verify Audio player is present on the device locked screen'";
	String testCase1 = "'Verify Audio player is present on the device locked screen for inline audio player'";
	String testCase2 = "''";
	String audiobook = "";
	String audiotitle = "";
	String audiosubTitle = "";

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void AudioPlayerLockNotification(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);

		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase);

		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1, testCase1);

		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2, testCase2);

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		if (driver.isDeviceLocked())
			driver.unlockDevice();
		else
			System.out.println("Device already unlocked");

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		HomePageV2.tabClick("Listen");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("AudioBookCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);

				Response respTab = Utilities.requestUtility(ApiTab);

				audiobook = respTab.jsonPath().get("assets.items[0].title");
				// Audiobooks.add(abooks);

			} else
				System.out.println("Unable to fetch tab from API");
		}

		try {
			String xpath = "//android.widget.TextView[contains(@text,'" + audiobook + "')]";

			Utilities.verticalSwipe(driver, xpath);
			WebElement ab = driver.findElement(By.xpath(xpath));

			if (Utilities.explicitWaitVisible(driver, ab, 10))
				ab.click();
			else
				BasePageV2.reportFail("Audio Book is not displayed");
		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 25))
			homepagev2.playAudiobookbutton.click();
		else
			BasePageV2.reportFail("Play button not displayed in audio detail page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 60))
			test.log(LogStatus.INFO, "Audio is playing");
		else
			BasePageV2.reportFail("Audio fails to play on tapping on Audio card");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 5)) {
			audiotitle = homepagev2.audioTitle.getText();

			test.log(LogStatus.INFO, "Title of playing audio is: " + audiotitle);
		} else
			BasePageV2.reportFail("Audio Title is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiosubTitle, 5)) {
			audiosubTitle = homepagev2.audiosubTitle.getText();
			test.log(LogStatus.INFO, "Sub Title of playing audio is: " + audiosubTitle);
		} else
			BasePageV2.reportFail("Audio Sub Title is not displayed");

		try {
			if (driver.isDeviceLocked())
				test.log(LogStatus.INFO, "Device is already locked");
			else {
				test.log(LogStatus.INFO, "Locking the device");
				driver.lockDevice();
			}

		} catch (Exception e) {
		}

		try {
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
		} catch (Exception e) {
		}

		ArrayList<String> audionotificationui = new ArrayList<String>();
		try {
			String xpath = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.TextView[contains(@text,'"
					+ audiosubTitle + "')]";
			WebElement audioNotification = driver.findElement(By.xpath(xpath));
			if (Utilities.explicitWaitVisible(driver, audioNotification, 10)) {
				audionotificationui.add("Pass");
				test.log(LogStatus.INFO, "Audio player notification displayed in lock screen");
			}

			else {
				audionotificationui.add("Fail");
				BasePageV2.reportFail("Audio player notification is not displayed in lock screen");
			}

		} catch (Exception e) {
		}

		try {
			String xpath = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.ImageButton[@resource-id = 'android:id/action0']";
			WebElement notificationPause = driver.findElement(By.xpath(xpath));
			if (Utilities.explicitWaitVisible(driver, notificationPause, 5)) {
				audionotificationui.add("Pass");
				test.log(LogStatus.INFO, "Pause Button is displayed in notification window");
			}

			else {
				audionotificationui.add("Fail");
				BasePageV2.reportFail("Play Pause Button is not displayed in notification window");
			}

		} catch (Exception e) {
		}

		if (audionotificationui.contains("Pass") && !audionotificationui.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}

		try {
			String xpath = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.TextView[contains(@text,'"
					+ audiotitle + "')]";
			WebElement audioNotification = driver.findElement(By.xpath(xpath));

			if (Utilities.explicitWaitVisible(driver, audioNotification, 5)) {
				audioNotification.click();

				Thread.sleep(500);
				audioNotification.click();

			}

			else
				BasePageV2.reportFail("Audio Notification is not displayed");

			if (Utilities.explicitWaitVisible(driver, audioNotification, 5)) {
				audioNotification.click();

				Thread.sleep(1000);
				audioNotification.click();

			} else
				System.out.println();

		} catch (Exception e) {
		}

		try {
			if (driver.isDeviceLocked())
				driver.unlockDevice();
			else
				test.log(LogStatus.INFO, "Already device is unlocked");
		} catch (Exception e) {
		}

		/*
		 * if (Utilities.explicitWaitVisible(driver, downloadpagev2.devicepinentry, 2))
		 * { downloadpagev2.devicepinentry.clear();
		 * downloadpagev2.devicepinentry.sendKeys("1111");
		 * driver.pressKeyCode(AndroidKeyCode.ENTER); } else
		 * BasePageV2.reportFail("Failed to unlock the device");
		 */

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiominiplayerSwitch, 10))
			homepagev2.audiominiplayerSwitch.click();
		else
			BasePageV2.reportFail("Audio Inline player is not displaed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.miniplayer, 10))
			test.log(LogStatus.INFO, "Mini Player is displayed");
		else
			BasePageV2.reportFail("mini player is not displayed");

		try {
			if (driver.isDeviceLocked())
				test.log(LogStatus.INFO, "Device is already locked");
			else {
				test.log(LogStatus.INFO, "Locking the device");
				driver.lockDevice();
			}

		} catch (Exception e) {
		}

		try {
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
		} catch (Exception e) {
		}

		ArrayList<String> audionotificationuimini = new ArrayList<String>();
		try {
			String xpath = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.TextView[contains(@text,'"
					+ audiotitle + "')]";
			WebElement audioNotificationmini = driver.findElement(By.xpath(xpath));
			if (Utilities.explicitWaitVisible(driver, audioNotificationmini, 10)) {
				audionotificationuimini.add("Pass");
				test.log(LogStatus.INFO, "Audio player notification displayed in lock screen");
			}

			else {
				audionotificationuimini.add("Fail");
				BasePageV2.reportFail("Audio player notification is not displayed in lock screen");
			}

		} catch (Exception e) {
		}

		try {
			String xpath = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.ImageButton[@resource-id = 'android:id/action0']";
			WebElement notificationPause = driver.findElement(By.xpath(xpath));
			if (Utilities.explicitWaitVisible(driver, notificationPause, 5)) {
				audionotificationuimini.add("Pass");
				test.log(LogStatus.INFO, "Pause Button is displayed in notification window");
			}

			else {
				audionotificationuimini.add("Fail");
				BasePageV2.reportFail("Play Pause Button is not displayed in notification window");
			}

		} catch (Exception e) {
		}

		if (audionotificationuimini.contains("Pass") && !audionotificationuimini.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is pass");
			HomePageV2.smokeresults(testCase1, rowno, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase1, rowno, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
