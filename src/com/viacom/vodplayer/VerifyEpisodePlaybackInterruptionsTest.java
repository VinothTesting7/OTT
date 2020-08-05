package com.viacom.vodplayer;

import java.time.Duration;
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
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;

public class VerifyEpisodePlaybackInterruptionsTest extends BaseTestV2 {
	String testName = "VerifyEpisodePlaybackInterruptionsTest";

	String testCase = "'Verify episode playback state post killing & relaunching the application'";//P2
	String testCase1 = "'Verify episode playback state post locking & unlocking the device'"; //P2
	String testCase2 = "'Verify episode playback state post minimize & resuming the application'";//P2

	String EpisodeCard = "";
	String EpisodeTitle = "";
	String EpisodesubTitle = "";
	int pausebef;
	int pauseaft;
	int timemin;

	// Author: Karthik T S

	@Test(dataProvider = "getData")
	public void VerifyEpisodePlaybackInterruptions(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
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
		KidsPlayerPageV2 playerpagev2 = new KidsPlayerPageV2(driver, test);

		HomePageV2.login("karthiktskaaru5278@gmail.com", "karu5278");

		HomePageV2.tabClick("Watch");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("WatchCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);

				Response respTab = Utilities.requestUtility(ApiTab);

				EpisodeCard = respTab.jsonPath().get("assets.items[2].title");
				// Audiobooks.add(abooks);

			} else
				System.out.println("Unable to fetch tab from API");
		}

		try {
			String xpath = "//android.widget.TextView[contains(@text,'" + EpisodeCard + "')]";

			Utilities.verticalSwipe(driver, xpath);
			WebElement ab = driver.findElement(By.xpath(xpath));

			if (Utilities.explicitWaitVisible(driver, ab, 10))
				ab.click();
			else
				BasePageV2.reportFail("Episode card is not displayed");
		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 25))
			homepagev2.playAudiobookbutton.click();
		else
			BasePageV2.reportFail("Play button not displayed in audio detail page");

		if (Utilities.explicitWaitVisible(driver, playerpagev2.videoPlayer, 60)) {

			homepagev2.verifyAndProgressBar();

			playerpagev2.pauseVideo();
			System.out.println(driver.getPageSource());

			// Check for Title
			if (Utilities.explicitWaitVisible(driver, playerpagev2.playerTitle, 60)) {
				EpisodeTitle = playerpagev2.playerTitle.getText();
				test.log(LogStatus.INFO, "Content Title is displyed on the Player screen");
			} else
				BasePageV2.reportFail("Content Title is not displayed");

			// Check for sub Title
			if (Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10)) {
				EpisodesubTitle = playerpagev2.playerSubTitle.getText();
				test.log(LogStatus.INFO, "Content sub Title is displayed on the Player screen");
			} else
				BasePageV2.reportFail("Content sub Title is not displayed");

		} else
			BasePageV2.reportFail("Video Player not displayed");

		if (Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 10)) {
			try {
				String timebefPause = playerpagev2.playerCurrentDuration.getText();
				test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen - " + timebefPause);
				String initpause[] = timebefPause.split(":");
				String durationpause = initpause[0].toString() + initpause[1].toString();
				pausebef = Integer.parseInt(durationpause);
				System.out.println("Duration merge: " + pausebef);

			} catch (Exception e) {
			}

			if (Utilities.explicitWaitVisible(driver, playerpagev2.playerPlayPauseButton, 5))
				playerpagev2.playerPlayPauseButton.click();
			else
				BasePageV2.reportFail("Play button fails to display");
		} else
			BasePageV2.reportFail("Current duration not displayed");

		driver.runAppInBackground(Duration.ofSeconds(5));
		test.log(LogStatus.INFO, "Put app to background for 5 seconds");
		driver.currentActivity();

		if (Utilities.explicitWaitVisible(driver, playerpagev2.videoPlayer, 60))

		{
			homepagev2.verifyAndProgressBar();
			playerpagev2.pauseVideo();

			if (Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 10)) {
				try {
					String timeaftPause = playerpagev2.playerCurrentDuration.getText();
					test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen - " + timeaftPause);
					String initpause[] = timeaftPause.split(":");
					String durationpause = initpause[0].toString() + initpause[1].toString();
					pauseaft = Integer.parseInt(durationpause);
					System.out.println("Duration merge: " + pauseaft);
				} catch (Exception e) {
					// TODO: handle exception
				}

				if (Utilities.explicitWaitVisible(driver, playerpagev2.playerPlayPauseButton, 5))
					playerpagev2.playerPlayPauseButton.click();
				else
					BasePageV2.reportFail("Play button fails to display");
			} else
				BasePageV2.reportFail("Current duration not displayed");
		}

		if (pausebef > 0 && pausebef < pauseaft) {
			BasePageV2.reportPass("Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase2, rowno2, "Fail");
		}

		try {
			if (driver.isDeviceLocked())
				test.log(LogStatus.INFO, "Device is in locked status");
			else {
				test.log(LogStatus.INFO, "Locking the device to check download notification");
				driver.lockDevice();
			}
		} catch (Exception e) {
		}

		try {
			if (driver.isDeviceLocked()) {
				test.log(LogStatus.INFO, "Device is in locked status ulocking");
				driver.unlockDevice();
			} else
				test.log(LogStatus.INFO, "Device is not locked");

		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisible(driver, playerpagev2.videoPlayer, 60)) {
			homepagev2.verifyAndProgressBar();
			playerpagev2.pauseVideo();

			if (Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 10)) {
				try {
					String timeaftPause = playerpagev2.playerCurrentDuration.getText();
					test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen - " + timeaftPause);
					String initpause[] = timeaftPause.split(":");
					String durationpause = initpause[0].toString() + initpause[1].toString();

					timemin = Integer.parseInt(durationpause);
					System.out.println("Duration merge: " + timemin);
				} catch (Exception e) {

				}

				if (Utilities.explicitWaitVisible(driver, playerpagev2.playerPlayPauseButton, 5))
					playerpagev2.playerPlayPauseButton.click();
				else
					BasePageV2.reportFail("Play button fails to display");
			} else
				BasePageV2.reportFail("Current duration not displayed");
		}

		if (pauseaft > 0 && pauseaft < timemin) {
			BasePageV2.reportPass("Test Case: " + testCase1 + " is Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
		}

		test.log(LogStatus.INFO, "Killing the Application to validate: " + testCase);
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		if (Utilities.explicitWaitVisible(driver, playerpagev2.videoPlayer, 5)) {
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is FailS");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}

		else {
			BasePageV2.reportPass("Test Case: " + testCase + " is Pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
