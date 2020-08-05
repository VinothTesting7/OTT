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
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionState;

public class DownloadedUparrowFunctionalityTest extends BaseTestV2 {
	String testName = "DownloadedUparrowFunctionalityTest";
	String testCase = "Verify the UI of Uparrow in player for the downloaded content";
	String testCase1 = "Verify the UI of Up arrow in player for the downloaded content";
	String testCase2 = "Verify navigation for the cards present under 'All Downloads' content tray.";
	String testCase3 = "Verify the UI of Uparrow in player for the downloaded content (Off line).";
	String testCase4 = "Verify metadata for the cards present under All Downloads tray";

	String testCase5 = "Verify the availablity of Up arrow option if there are more than 2 VOD contents in downloads tab";

	String EpisodeCard = "";
	String downloadtitle = "";

	@Test(dataProvider = "getData")
	public void DownloadedUparrowFunctionality(Hashtable<String, String> data) throws Exception {

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

		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1, testCase1);

		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2, testCase2);

		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3, testCase3);

		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4, testCase4);

		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5, testCase5);

		// Verify 'Create new' button functionality in 'Select Profile' screen

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		SettingsPageV2 settings = new SettingsPageV2(driver, test);

		KidsPlayerPageV2 kidspage = new KidsPlayerPageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

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

		Utilities.verticalSwipe(driver, downloadpagev2.downloadIconEpisodes);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadIconEpisodes, 20))
			downloadpagev2.downloadIconEpisodes.click();
		else
			BasePageV2.reportFail("Download icon is not displayed in Episode info Page");
		Thread.sleep(10000);

		for (int i = 0; i < 3; i++) {
			try {
				WebElement downloadTitle = driver.findElement(
						By.xpath("//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='" + i
								+ "']//*[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']"));

				WebElement downloadStatusIcon = driver.findElement(By.xpath(
						"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='" + i + "']"));

				if (Utilities.explicitWaitVisible(driver, downloadStatusIcon, 20))
					downloadStatusIcon.click();
				else
					BasePageV2.reportFail("Unable to download the content");

				if (Utilities.explicitWaitVisible(driver, downloadTitle, 10)) {
					downloadtitle = downloadTitle.getText();
				} else
					BasePageV2.reportFail("Content title not displayed for contents in download page");

				for (int j = 0; j < 10; j++) {

					try {
						WebElement downloaded = driver.findElement(By.xpath(
								"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc = 'DOWNLOAD_COMPLETE'][@index='"
										+ j + "']"));
						if (Utilities.explicitWaitVisible(driver, downloaded, 5)) {
							test.log(LogStatus.INFO, "Downloaded Episode '" + downloadtitle + "' successfully");
							break;
						} else
							BasePageV2.reportFail("Failed to download the episode: " + downloadtitle);

					} catch (Exception e) {
						continue;
					}

				}

			} catch (Exception e) {
			}
		}

		try {
			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(5000);
			driver.pressKeyCode(AndroidKeyCode.BACK);
		} catch (Exception e) {
		}

	
		
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(5000);
		HomePageV2.tabClick("My Stuff");

		Utilities.verticalSwipe(driver, downloadpagev2.downloadsTabMystuffpage);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 20))
			downloadpagev2.downloadsTabMystuffpage.click();
		else
			BasePageV2.reportFail("Downloads tab is not displayed in 'My Stuff' page");

		try {
			WebElement downloaded = driver.findElement(By.xpath(
					"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc = 'DOWNLOAD_COMPLETE'][@index='1']"));
			if (Utilities.explicitWaitVisible(driver, downloaded, 20))
				downloaded.click();
			else
				BasePageV2.reportFail("Downloaded Item is not displayed under 'Downloads tab' in 'My Stuff page'");
		} catch (Exception e) {
			try {
				Utilities.verticalSwipe(driver);
				WebElement downloaded = driver.findElement(By.xpath(
						"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc = 'DOWNLOAD_COMPLETE'][@index='1']"));
				if (Utilities.explicitWaitVisible(driver, downloaded, 20))
					downloaded.click();
				else
					BasePageV2.reportFail("Downloaded Item is not displayed under 'Downloads tab' in 'My Stuff page'");
			} catch (Exception e2) {

			}
		}
		if (Utilities.explicitWaitVisible(driver, kidspage.videoPlayer, 20)) {
			kidspage.pauseVideo();

			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");

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

		try {
			driver.setConnection(new ConnectionState(0));
		} catch (Exception e) {

		}

		try {
			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(5000);
			driver.pressKeyCode(AndroidKeyCode.BACK);
		} catch (Exception e) {
		}

		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
		List<String> epiTitleStatus = new ArrayList<String>();
		List<String> metadataStatus = new ArrayList<String>();
		for (int i = 0; i < downloadpagev2.downloadedContentTitles.size(); i++) {
			try {
				test.log(LogStatus.INFO, "Downloaded Card title displayed as: "
						+ downloadpagev2.downloadedContentTitles.get(i).getText());
				epiTitleStatus.add("Pass");
				test.log(LogStatus.INFO, "Downloaded Card Metadata is displayed as: "
						+ downloadpagev2.downloadedContentTitles.get(i).getText());
				metadataStatus.add("Pass");
			} catch (Exception e) {
				epiTitleStatus.add("Fail");
				metadataStatus.add("Fail");
				BasePageV2.reportFail("Downloaded content is not displayed in Downloads screen");

			}
		}

		if ((epiTitleStatus.contains("Pass") && !epiTitleStatus.contains("Fail"))
				&& (metadataStatus.contains("Pass") && !metadataStatus.contains("Fail"))) {
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Pass");
			HomePageV2.smokeresults(testCase4, rowno4, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase4, rowno4, "Fail");
		}

		try {
			WebElement downloaded = driver.findElement(By.xpath(
					"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc = 'DOWNLOAD_COMPLETE'][@index='1']"));
			if (Utilities.explicitWaitVisible(driver, downloaded, 20))
				downloaded.click();
			else
				BasePageV2.reportFail("Downloaded Item is not displayed under 'Downloads tab' in 'My Stuff page'");
		} catch (Exception e) {
			try {
				Utilities.verticalSwipe(driver);
				WebElement downloaded = driver.findElement(By.xpath(
						"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc = 'DOWNLOAD_COMPLETE'][@index='1']"));
				if (Utilities.explicitWaitVisible(driver, downloaded, 20))
					downloaded.click();
				else
					BasePageV2.reportFail("Downloaded Item is not displayed under 'Downloads tab' in 'My Stuff page'");
			} catch (Exception e2) {

			}
		}
		if (Utilities.explicitWaitVisible(driver, kidspage.videoPlayer, 20)) {
			kidspage.pauseVideo();

			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");

			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 20)) {
				test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Pass");
				HomePageV2.smokeresults(testCase5, rowno5, "Pass");
				homepagev2.audioplaylistExpand.click();
			} else
				BasePageV2.reportFail("Up arrow not displayed");

		} else
			BasePageV2.reportFail("Unable to play downloaded Video");

		// abcd
		if (Utilities.explicitWaitVisible(driver, downloadpagev2.allDownloads, 20)) {
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
			HomePageV2.smokeresults(testCase3, rowno3, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			HomePageV2.smokeresults(testCase3, rowno3, "Fail");
		}
		try {
			driver.setConnection(new ConnectionState(2));
		} catch (Exception e) {

		}
		downloadpagev2.deleteAllDownloads();

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
