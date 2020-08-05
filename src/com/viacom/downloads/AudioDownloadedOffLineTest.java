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
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionState;

public class AudioDownloadedOffLineTest extends BaseTestV2 {
	String testName = "AudioDownloadedOffLineTest";
	String testCase = "Verify the availablity of Uparrow option if there are more than 2 Audio book contents in downloads tab";
	String testCase1 = "Verify the availablity of Uparrow option if there are more than 2 Audio book contents in downloads tab";
	String EpisodeCard = "";
	String downloadtitle = "";
	String audiobook = "";
	List<String> Audiobooks = new ArrayList<String>();

	@SuppressWarnings("deprecation")
	@Test(dataProvider = "getData")
	public void AudioDownloadedOffLine(Hashtable<String, String> data) throws Exception {

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		// Verify profile selection in 'Select Profile' screen
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase);

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		KidsPlayerPageV2 kidspage = new KidsPlayerPageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);
		HomePageV2.tabClick("Listen");
		for (int i = 0; i < 2; i++) {
			Utilities.verticalSwipe(driver);
		}

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("AudioBookCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);
				System.out.println("Displayed API is: " + ApiTab);
				Response respTab = Utilities.requestUtility(ApiTab);

				// Audiobooks = new ArrayList<String>();
				for (int i = 0; i < 5; i++) {

					try {
						String abooks = respTab.jsonPath().get("assets.items[" + i + "].title");
						System.out.println("Audio book is: " + abooks);
						String[] abooks1 = abooks.split(" ");
						try {
							abooks = abooks1[0].toString() + abooks1[1].toString();
							Audiobooks.add(abooks);
						} catch (Exception e) {
							Audiobooks.add(abooks);
						}
						// abooks = abooks1[0].toString() + abooks1[1].toString();

					} catch (Exception e) {

						continue;
						// break;
					}

				}
			} else
				System.out.println("Unable to fetch tab from API");
		}

		System.out.println("Audio books displayed is: " + Audiobooks);

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

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(5000);
		HomePageV2.tabClick("My Stuff");

		driver.setConnection(new ConnectionState(0));

		Utilities.verticalSwipe(driver, downloadpagev2.downloadsTabMystuffpage);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 20))
			downloadpagev2.downloadsTabMystuffpage.click();
		else
			BasePageV2.reportFail("Downloads tab is not displayed in 'My Stuff' page");

		try {
			WebElement downloaded = driver.findElement(By.xpath(
					"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc = 'DOWNLOAD_COMPLETE']"));
			if (Utilities.explicitWaitVisible(driver, downloaded, 20))
				downloaded.click();
			else
				BasePageV2.reportFail("Downloaded Item is not displayed under 'Downloads tab' in 'My Stuff page'");
		} catch (Exception e) {
			try {
				Utilities.verticalSwipe(driver);
				WebElement downloaded = driver.findElement(By.xpath(
						"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc = 'DOWNLOAD_COMPLETE']"));
				if (Utilities.explicitWaitVisible(driver, downloaded, 20))
					downloaded.click();
				else
					BasePageV2.reportFail("Downloaded Item is not displayed under 'Downloads tab' in 'My Stuff page'");
			} catch (Exception e2) {

			}
		}
		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 20)) {
			homepagev2.audioplayPause.click();

			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 20))
				homepagev2.audioplaylistExpand.click();
			else
				BasePageV2.reportFail("Up arrow not displayed");

		} else
			BasePageV2.reportFail("Unable to play downloaded Video");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.allDownloads, 20)) {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}
		driver.setConnection(new ConnectionState(2));
		downloadpagev2.deleteAllDownloads();
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
