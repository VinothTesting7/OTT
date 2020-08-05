package com.viacom.downloads;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyAudioDownloadesMyStuffTabTest extends BaseTestV2 {
	String testName = "VerifyAudioDownloadesMyStuffTabTest";
	String testCase = "'Verify the availablity of Uparrow option if there are more than 2 Audio Book contents in downloads tab'";
	String testCase1 = "'Verify if Downloaded contents will be played in loop if there are more than 2 contents'";
	String testCase2 = "''";
	ArrayList<String> Audiobooks = null;

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void VerifyAudioDownloadesMyStuffTab(Hashtable<String, String> data) throws Exception {

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Audio Book detail page Functionality");
		test.log(LogStatus.INFO, "Starting the test for 'Sign Up' Functionality: " + VootConstants.DEVICE_NAME);

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
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);

		HomePageV2.login("karthiktskaaru@gmail.com", "karu5278");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("AudioBookCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);

				Response respTab = Utilities.requestUtility(ApiTab);

				Audiobooks = new ArrayList<String>();
				for (int i = 0; i < 5; i++) {
					String abooks = respTab.jsonPath().get("assets.items[" + i + "].title");
					Audiobooks.add(abooks);
				}
			} else
				System.out.println("Unable to fetch tab from API");
		}
		ArrayList<String> audtitles = new ArrayList<String>();
		for (String abook : Audiobooks) {
			try {
				String xpath = "//android.widget.TextView[contains(@text,'" + abook + "')]";

				Utilities.verticalSwipe(driver, xpath);
				WebElement ab = driver.findElement(By.xpath(xpath));

				if (Utilities.explicitWaitVisible(driver, ab, 5)) {
					ab.click();
					if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 10)) {
						String audioTitle = homepagev2.audTitle.getText();
						audtitles.add(audioTitle);
						Utilities.verticalSwipe(driver, homepagev2.downloadItem);
						if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
							homepagev2.downloadItem.click();
							next: for (int j = 1; j <= 100; j++) {
								Thread.sleep(1500);
								if (Utilities.explicitWaitVisible(driver, homepagev2.DownloadedStatus, 5)) {
									test.log(LogStatus.INFO, "Content downloaded successfully");
									test.log(LogStatus.INFO,
											"Download Status displayed as: " + homepagev2.DownloadedStatus.getText());
									break;
								} else
									continue next;
							}

						}

						else
							BasePageV2.reportFail("Download Status Icon not displayed for Audio book: " + abook);
						driver.pressKeyCode(AndroidKeyCode.BACK);
					} else
						test.log(LogStatus.FAIL,
								"Audio book Title not displayed in showdetail page of Audio book: " + abook);
				} else
					BasePageV2.reportFail("Unable to find Audiobook");
			} catch (Exception e) {

				try {
					String xpath = "//android.widget.TextView[contains(@text,'" + abook + "')]";

					Utilities.verticalSwipe(driver, xpath);
					WebElement ab = driver.findElement(By.xpath(xpath));

					if (Utilities.explicitWaitVisible(driver, ab, 5)) {
						ab.click();
						if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 10)) {
							String audioTitle = homepagev2.audTitle.getText();
							audtitles.add(audioTitle);
							Utilities.verticalSwipe(driver, homepagev2.downloadItem);
							if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
								homepagev2.downloadItem.click();
								next: for (int j = 1; j <= 100; j++) {
									Thread.sleep(1500);
									if (Utilities.explicitWaitVisible(driver, homepagev2.DownloadedStatus, 5)) {
										test.log(LogStatus.INFO, "Content downloaded successfully");
										test.log(LogStatus.INFO, "Download Status displayed as: "
												+ homepagev2.DownloadedStatus.getText());
										break;
									} else
										continue next;
								}

							}

							else
								BasePageV2.reportFail("Download Status Icon not displayed for Audio book: " + abook);
							driver.pressKeyCode(AndroidKeyCode.BACK);
						} else
							test.log(LogStatus.FAIL,
									"Audio book Title not displayed in showdetail page of Audio book: " + abook);
					} else
						BasePageV2.reportFail("Unable to find Audiobook");
				} catch (Exception e2) {
				}
			}
		}

		try {
			driver.pressKeyCode(AndroidKeyCode.BACK);
		} catch (Exception e) {
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		Thread.sleep(5000);

		HomePageV2.tabClick("My Stuff");

		Utilities.verticalSwipe(driver, downloadpagev2.downloadsTabMystuffpage);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 5))
			downloadpagev2.downloadsTabMystuffpage.click();
		else
			BasePageV2.reportFail("Downloads Tab is not displayed");

		for (int i = 0; i < 2; i++) {
			Utilities.verticalSwipe(driver);
		}

		try {
			@SuppressWarnings({ "unchecked" })
			List<WebElement> downloadedAudios = driver
					.findElements(By.id("com.viacom18.vootkids:id/recyclerview_listing_items"));

			if (downloadedAudios.size() > 2)
				test.log(LogStatus.INFO,
						"Number of downloaded contents are " + downloadedAudios.size() + " is greater than 2");
			else
				BasePageV2.reportFail("Failed to download 3 contents");
			ArrayList<String> contplayanduparrow = new ArrayList<String>();
			for (WebElement downloadedAudio : downloadedAudios) {
				if (Utilities.explicitWaitVisible(driver, downloadedAudio, 5))
					downloadedAudio.click();
				else
					BasePageV2.reportFail("Downloaded Audio is not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 25)) {
					if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 5)) {
						test.log(LogStatus.PASS,"Test Case: " + testCase + " is Pass");
						HomePageV2.smokeresults(testCase, rowno, "Pass");
						contplayanduparrow.add("Pass");
					} else {
						BasePageV2.takeScreenshot();
						test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
						HomePageV2.smokeresults(testCase, rowno, "Fail");
						contplayanduparrow.add("Fail");
					}

					if (Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 5)) {
						Utilities.scrubtillend(driver, homepagev2.audioseekBar);
						if (Utilities.explicitWaitVisible(driver, homepagev2.listenAgainBtn, 5)) {
							test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
							contplayanduparrow.add("Fail");
							homepagev2.listenAgainBtn.click();
							if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 25)) {
								driver.pressKeyCode(AndroidKeyCode.BACK);
							} else
								test.log(LogStatus.FAIL,
										"Failed to navigate back after tapping on Listen again button");

						} else {
							test.log(LogStatus.INFO, "Test Case: " + testCase1 + " is Pass");
							contplayanduparrow.add("Pass");
							driver.pressKeyCode(AndroidKeyCode.BACK);
						}
					}
				} else
					BasePageV2.reportFail("Downloaded Audio fails to play");
			}

		} catch (Exception e) {
		}

		for (int i = 0; i < 3; i++) {
			downloadpagev2.deleteDownload();
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
