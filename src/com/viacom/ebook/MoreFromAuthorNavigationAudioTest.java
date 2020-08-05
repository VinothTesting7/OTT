package com.viacom.ebook;

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

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionState;

public class MoreFromAuthorNavigationAudioTest extends BaseTestV2 {
	String testName = "MoreFromAuthorNavigationAudioTest";
	String testCase = "'Verify card navigation'";
	String testCase1 = "'Verify if the Downloads appear in Download Episodes screen/Downloads tab when user disconnects and reconnects to the network'";
	String testCase2 = "'Verify if downloading content is resumed post disconnecting and reconnecting network'";
	String testCase3 = "Verify if downloading content is resumed post switching the network in wifi";
	String testCase4 = "Verify if downloading content is resumed post Switching between Wifi & Cellular";
	String audiobook = "";
	String cardTitle = "";
	List<String> Result = new ArrayList<String>();
	List<WebElement> download = new ArrayList<WebElement>();

	@SuppressWarnings("deprecation")
	@Test(dataProvider = "getData")
	public void MoreFromAuthorNavigationAudio(Hashtable<String, String> data) throws Exception {

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

		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3, testCase3);

		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4, testCase4);

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		HomePageV2.login("karthiktskaaru5278@gmail.com", "karu5278");

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
				try {
					String[] audiobook1 = audiobook.split(" ");
					audiobook = audiobook1[0].toString() + audiobook1[1].toString();
				} catch (Exception e) {

				}

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

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10))
			homepagev2.audioplaylistExpand.click();
		else
			BasePageV2.reportFail("Audio Playlist Expander is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.morefromauthor, 20)) {

			homepagev2.morefromauthor.click();

			if (homepagev2.morefromauthor.isSelected()) {
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
				HomePageV2.smokeresults(testCase, rowno, "Pass");
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
				BasePageV2.takeScreenshot();
				HomePageV2.smokeresults(testCase, rowno, "Fail");
			}
		} else
			BasePageV2.reportFail("'More From Author' tab is not displayed");

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		HomePageV2.tabClick("Watch");

		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("All Characters")) {
				String ApiEbook = xls.getCellData("Api", "Url", rNum);

				Response EbookTitle = Utilities.requestUtility(ApiEbook);
				cardTitle = EbookTitle.jsonPath().get("assets.items[1].title");

				System.out.println("Displayed Card " + cardTitle);
				String cardend = "//android.widget.TextView[contains(@text,'" + cardTitle + "')]";
				try {
					Utilities.verticalSwipe(driver, cardend);
				} catch (Exception e) {
				}

				try {
					WebElement card = driver.findElement(By.xpath(cardend));
					if (Utilities.explicitWaitVisible(driver, card, 2))
						card.click();
					else
						BasePageV2.reportFail("Episode not displayed");
				} catch (Exception e) {

				}

			}
		}

		Utilities.verticalSwipe(driver, downloadpagev2.downloadIconEpisodes);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadStatusText, 2))
			downloadpagev2.downloadIconEpisodes.click();
		else
			BasePageV2.reportFail("Download Icon is not displayed");
		Thread.sleep(5000);

		try {
			download = driver.findElements(By.xpath(
					"//android.widget.FrameLayout[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']"));

			if (Utilities.explicitWaitVisible(driver, download.get(0), 20))
				download.get(0).click();
			else
				BasePageV2.reportFail("Contents not displayed in 'DOWNLOAD EPISODES' page");
		} catch (Exception e) {

		}
		for (int i = 0; i < 100; i++) {
			Thread.sleep(2000);
			try {
				WebElement loaded = driver.findElement(By.xpath(
						"//*[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE' and @index='0']"));
				if (Utilities.explicitWaitVisible(driver, loaded, 20)) {
					test.log(LogStatus.INFO, "Content downloaded successfully");
					break;
				} else
					BasePageV2.reportFail("Content not downloaded");
			} catch (Exception e) {
				continue;
			}
		}

		for (int i = 1; i <= 3; i++) {
			try {
				WebElement loaded = download.get(i);
				if (Utilities.explicitWaitVisible(driver, loaded, 20))
					loaded.click();
				else
					BasePageV2.reportFail("Failed to add to download");
			} catch (Exception e) {
				continue;
			}

		}
		try {
			driver.setConnection(new ConnectionState(0));
		} catch (Exception e) {
		}

		for (int i = 0; i < 2; i++) {
			driver.pressKeyCode(AndroidKeyCode.BACK);
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(5000);
		HomePageV2.tabClick("My Stuff");
		Utilities.verticalSwipe(driver, downloadpagev2.downloadsTabMystuffpage);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 20))
			downloadpagev2.downloadsTabMystuffpage.click();
		else
			BasePageV2.reportFail("Downloads Tab is not displayed in 'My Stuff' page");

		try {
			driver.setConnection(new ConnectionState(2));
		} catch (Exception e) {
		}

		try {
			Utilities.verticalSwipe(driver);
			WebElement offloaded = driver.findElement(By.xpath(
					"//*[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE' and @index='0']"));
			if (Utilities.explicitWaitVisible(driver, offloaded, 20))
				Result.add("Pass");
			else {
				Result.add("Fail");
				BasePageV2.reportFail(
						"Downloaded content is not displayed after Network interruption in 'My Stuff Page'");
			}
		} catch (Exception e) {

		}

		try {
			Utilities.verticalSwipe(driver);
			WebElement offloaded = driver.findElement(By.xpath(
					"//*[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='IN_PROGRESS' and @index='1']"));
			if (Utilities.explicitWaitVisible(driver, offloaded, 20))
				Result.add("Pass");
			else {
				Result.add("Fail");
				BasePageV2.reportFail(
						"In Progress content is not displayed after Network interruption in 'My Stuff Page'");
			}
		} catch (Exception e) {

		}

		try {
			Utilities.verticalSwipe(driver);
			WebElement offloaded = driver.findElement(By.xpath(
					"//*[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='IN_QUEUE' and @index='2']"));
			if (Utilities.explicitWaitVisible(driver, offloaded, 20))
				Result.add("Pass");
			else {
				Result.add("Fail");
				BasePageV2.reportFail("Queued content is not displayed after Network interruption in 'My Stuff Page'");
			}
		} catch (Exception e) {

		}

		try {
			driver.setConnection(new ConnectionState(2));
		} catch (Exception e) {
		}

		try {
			Utilities.verticalSwipe(driver);
			WebElement offloaded = driver.findElement(By.xpath(
					"//*[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE' and @index='0']"));
			if (Utilities.explicitWaitVisible(driver, offloaded, 20))
				Result.add("Pass");
			else {
				Result.add("Fail");
				BasePageV2.reportFail(
						"Downloaded content is not displayed after Network interruption in 'My Stuff Page'");
			}
		} catch (Exception e) {

		}

		try {
			Utilities.verticalSwipe(driver);
			WebElement offloaded = driver.findElement(By.xpath(
					"//*[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='IN_PROGRESS' and @index='1']"));
			if (Utilities.explicitWaitVisible(driver, offloaded, 20)) {
				Result.add("Pass");
				// abcd
				test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
				HomePageV2.smokeresults(testCase3, rowno3, "Pass");

				test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Pass");
				HomePageV2.smokeresults(testCase4, rowno4, "Pass");

				test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
				HomePageV2.smokeresults(testCase2, rowno2, "Pass");
			}

			else {
				Result.add("Fail");
				BasePageV2.reportFail(
						"In Progress content is not displayed after Network interruption in 'My Stuff Page'");
			}
		} catch (Exception e) {

		}

		try {
			Utilities.verticalSwipe(driver);
			WebElement offloaded = driver.findElement(By.xpath(
					"//*[@resource-id = 'com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='IN_QUEUE' and @index='2']"));
			if (Utilities.explicitWaitVisible(driver, offloaded, 20))
				Result.add("Pass");
			else {
				Result.add("Fail");
				BasePageV2.reportFail("Queued content is not displayed after Network interruption in 'My Stuff Page'");
			}
		} catch (Exception e) {

		}

		if (Result.contains("Pass") && !Result.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
