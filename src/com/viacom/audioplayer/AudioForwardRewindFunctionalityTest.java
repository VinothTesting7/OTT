package com.viacom.audioplayer;

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
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class AudioForwardRewindFunctionalityTest extends BaseTestV2 {
	String testName = "AudioForwardRewindFunctionalityTest";

	String testCase = "'Verify the rewind by 10 secs button functionality'";
	String testCase1 = "'Verify the forward by 10 secs button functionality'";
	String testCase2 = "'Verify the audio playback status post performing seek functionality of the audio player'";
	String testCase3 = "''";
	String audiobook = "";
	int durationbefrewind;
	int durationaftrewind;
	int durationaftforward;
	int durationbefScrub;
	int durationaftscrub;

	@Test(dataProvider = "getData")
	public void AudioForwardRewindFunctionality(Hashtable<String, String> data) throws Exception {
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
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SearchPageV2 searchpage = new SearchPageV2(driver, test);
		KidsPlayerPageV2 kidspage = new KidsPlayerPageV2(driver, test);
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

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 5))
			homepagev2.playAudiobookbutton.click();
		else
			BasePageV2.reportFail("Play button is not displayed in audio detail page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 20)) {

			Thread.sleep(60000);

			try {
				homepagev2.audioplayPause.click();
			} catch (Exception e) {
				homepagev2.audioplayPause.click();
			}

		} else
			BasePageV2.reportFail("Unable to pause the audio");

		// Validating Rewind functionality
		if (Utilities.explicitWaitVisible(driver, homepagev2.audiorunningDuration, 20)) {

			try {
				String befrewind = homepagev2.audiorunningDuration.getText();
				String[] rewind = befrewind.split(":");
				String befrew = rewind[0].toString() + rewind[1].toString();
				durationbefrewind = Integer.parseInt(befrew);
			} catch (Exception e) {

			}

		} else
			BasePageV2.reportFail("Audio duration fails to display");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiobackward, 20))
			homepagev2.audiobackward.click();
		else
			BasePageV2.reportFail("Audio Rewind button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiorunningDuration, 20)) {

			try {
				String aftrewind = homepagev2.audiorunningDuration.getText();
				String[] rewind = aftrewind.split(":");
				String aftrew = rewind[0].toString() + rewind[1].toString();
				durationaftrewind = Integer.parseInt(aftrew);
			} catch (Exception e) {
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.audioForward, 20)) {
				homepagev2.audioForward.click();
				if (Utilities.explicitWaitVisible(driver, homepagev2.audiorunningDuration, 20)) {

					try {
						String aftforward = homepagev2.audiorunningDuration.getText();
						String[] forward = aftforward.split(":");
						String aftrewin = forward[0].toString() + forward[1].toString();
						durationaftforward = Integer.parseInt(aftrewin);
					} catch (Exception e) {
					}

					if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 20)) {
						try {
							homepagev2.audioplayPause.click();
						} catch (Exception e) {
							homepagev2.audioplayPause.click();
						}

						if (Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 20)) {
							Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);
							Thread.sleep(10000);
							try {
								homepagev2.audioplayPause.click();
							} catch (Exception e) {
								homepagev2.audioplayPause.click();
							}
						} else
							BasePageV2.reportFail("Audio Seek Bar is not displayed");
					} else
						BasePageV2.reportFail("Unable to play the audio");
				} else
					BasePageV2.reportFail("Audio duration fails to display");
			}

			else
				BasePageV2.reportFail("Audio Forward button is not displayed");
		} else
			BasePageV2.reportFail("Audio duration fails to display");

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

//		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 20)) {
//
//		} else {
//			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 20)) {
//				try {
//					homepagev2.audioplayPause.click();
//				} catch (Exception e) {
//					homepagev2.audioplayPause.click();
//				}
//			} else
//				BasePageV2.reportFail("Unable to pause the audio");
//		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.audiorunningDuration, 20)) {

			try {
				String aftseek = homepagev2.audiorunningDuration.getText();
				String[] scrub = aftseek.split(":");
				String aftscrub = scrub[0].toString() + scrub[1].toString();
				durationaftscrub = Integer.parseInt(aftscrub);
			} catch (Exception e) {

			}

		} else
			BasePageV2.reportFail("Audio duration fails to display");
		if (durationaftrewind == durationbefrewind - 10) {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		} else {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}

		// Validating Forward Functionality

		if (durationaftforward == durationaftrewind + 10) {
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		} else {
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
		}

		durationbefScrub = durationaftforward + 5;

		// Validating Seek Functionality
		Thread.sleep(5000);

		if (durationbefScrub < durationaftscrub) {
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
		} else {
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase2, rowno2, "Fail");
		}
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
