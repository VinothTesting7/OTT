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

public class VerifyDownloadNotificationTest extends BaseTestV2 {
	String testName = "VerifyDownloadNotificationTest";

	String testCase = "'Verify the navigation by tapping on any of the downloaded contents in notification drawer'";
	String testCase1 = "'Verify the navigation by tapping on any of the downloaded contents from locked screen notification'";
	String testCase2 = "'Verify sorting order of the Downloaded contents in downloads dedicated screen'";
	String testCase3 = "'Verify the card navigation by tapping on downloaded cards'";
	String testCase4 = "'Verify the deleted downloaded contents are reflected in Downloads screen'";

	String tabName = "";
	int totalNumCards;
	String relatedTray = "";
	Response EbookTitle;
	String EbookMainTitle = "";
	ArrayList<String> Abooks = null;
	ArrayList<String> Audiobooks = null;

	String audiobook = "";

	// Author: Karthik T S
	@Test(dataProvider = "getData")

	public void VerifyDownloadNotification(Hashtable<String, String> data) throws Exception {

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

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("EbookCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);
				// String ApiName = "AbooksTab";
				Response respTab = Utilities.requestUtility(ApiTab);
				// String tabNameApi = respTab.jsonPath().get("assets[1].title");
				// tabName = tabNameApi.toUpperCase();
				Abooks = new ArrayList<String>();
				for (int i = 0; i < 3; i++) {
					String books = respTab.jsonPath().get("assets.items[" + i + "].title");
					Abooks.add(books.trim());
				}
			} else
				System.out.println("Unable to fetch tab from API");
		}

		HomePageV2.tabClick("Read");

		for (String ebook : Abooks) {
			try {
				String xpath = "//android.widget.TextView[contains(@text,'" + ebook + "')]";

				Utilities.verticalSwipe(driver, xpath);
				WebElement eb = driver.findElement(By.xpath(xpath));

				if (Utilities.explicitWaitVisible(driver, eb, 5)) {
					eb.click();
					if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 10)) {
						Utilities.verticalSwipe(driver, homepagev2.downloadItem);
						if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
							homepagev2.downloadItem.click();
							test.log(LogStatus.INFO, "Content '" + ebook + "' added to download");
						} else
							BasePageV2.reportFail("Download Status Icon not displayed for Ebook: " + ebook);
						driver.pressKeyCode(AndroidKeyCode.BACK);
					} else
						test.log(LogStatus.FAIL, "Ebook Title not displayed in showdetail page of Ebook: " + ebook);
				} else
					BasePageV2.reportFail("Unable to find Ebook");
			} catch (Exception e) {
				String xpath = "//android.widget.TextView[contains(@text,'" + ebook + "')]";

				Utilities.verticalSwipe(driver, xpath);
				WebElement eb = driver.findElement(By.xpath(xpath));

				if (Utilities.explicitWaitVisible(driver, eb, 5)) {
					eb.click();
					if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 10)) {
						Utilities.verticalSwipe(driver, homepagev2.downloadItem);
						if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
							homepagev2.downloadItem.click();
							test.log(LogStatus.INFO, "Content '" + ebook + "' added to download");
						} else
							BasePageV2.reportFail("Download Status Icon not displayed for Ebook: " + ebook);

					} else
						test.log(LogStatus.FAIL, "Ebook Title not displayed in showdetail page of Ebook: " + ebook);
					driver.pressKeyCode(AndroidKeyCode.BACK);
				} else
					BasePageV2.reportFail("Unable to find Ebook");
			}
		}
		try {
			driver.pressKeyCode(AndroidKeyCode.BACK);
		} catch (Exception e) {
		}

		try {
			driver.openNotifications();
		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.notificationDownloadComplete, 120)) {
			test.log(LogStatus.INFO, "Download completion status displayed in notification");
			downloadpagev2.notificationDownloadComplete.click();
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.DownloadsScreen, 10)) {
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
				HomePageV2.smokeresults(testCase, rowno, "Pass");
			} else
				BasePageV2.reportFail(
						"Downloads page fails to display when tapping on download notifaication for downloaded conten");
		} else
			BasePageV2.reportFail("Download completion status not displayed in notification");

		try {
			if (driver.isDeviceLocked())
				test.log(LogStatus.INFO, "Device is in locked status");
			else {
				test.log(LogStatus.INFO, "Locking the device to check download notification");
				driver.lockDevice();
			}
		} catch (Exception e) {
		}

		if (driver.isDeviceLocked()) {
			test.log(LogStatus.INFO, "Openinng notification in lock screen");
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
		} else
			test.log(LogStatus.INFO, "Device is not locked");

		ArrayList<String> downloadedcontentsds = new ArrayList<String>();
		if (Utilities.explicitWaitVisible(driver, downloadpagev2.notificationDownloadComplete, 60)) {
			downloadpagev2.notificationDownloadComplete.click();
			downloadpagev2.notificationDownloadComplete.click();

			Thread.sleep(1000);
			downloadpagev2.notificationDownloadComplete.click();

			/*
			 * if (Utilities.explicitWaitVisible(driver, downloadpagev2.devicepinentry, 2))
			 * { downloadpagev2.devicepinentry.clear();
			 * downloadpagev2.devicepinentry.sendKeys("1111");
			 * driver.pressKeyCode(AndroidKeyCode.ENTER); } else
			 * BasePageV2.reportFail("Failed to unlock the device");
			 */

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.DownloadsScreen, 10)) {
				test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
				HomePageV2.smokeresults(testCase1, rowno1, "Pass");

				try {
					List<WebElement> downloaded = driver.findElements(By.xpath(
							"//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']"));
					int size = downloaded.size();
					for (int i = 0; i < size; i++) {

						try {
							WebElement downtitle = driver.findElement(By.xpath(
									"//android.widget.FrameLayout[@resouurce-id = 'com.viacom18.vootkids:id/parent_for_download_item'][@index='"
											+ i
											+ "']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']"));

							String titledown = downtitle.getText();
							test.log(LogStatus.INFO,
									"Content " + i + " under downloads tab displayed is: " + titledown);
							downloadedcontentsds.add(titledown.trim());

							if (Utilities.explicitWaitVisible(driver, downtitle, 5)) {
								downtitle.click();
								if (Utilities.explicitWaitVisible(driver, downloadpagev2.readerView, 45)) {
									test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
									HomePageV2.smokeresults(testCase3, rowno3, "Pass");
								} else
									BasePageV2.reportFail("Donloaded contents fails to play");
							} else
								BasePageV2.reportFail("Unable to click downloaded content");

						} catch (Exception e) {
						}

					}

				} catch (Exception e) {
				}

			} else
				BasePageV2.reportFail(
						"Downloads page fails to display when tapping on download notifaication for downloaded content");

		} else
			BasePageV2.reportFail("Unable to Unlock the device");

		if (downloadedcontentsds.equals(Abooks)) {
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase2, rowno2, "Fail");
		}

		for (int i = 0; i < 3; i++) {

			Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 2))
				break;
			else
				driver.pressKeyCode(AndroidKeyCode.BACK);
		}
		Thread.sleep(5000);
		HomePageV2.tabClick("My Stuff");

		downloadpagev2.deleteDownload();

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		HomePageV2.tabClick("Listen");

		String trayName = "NEW AUDIOSTORIES";
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

		try {
			if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 25))
				test.log(LogStatus.INFO, "Navigated to show detail pages");
			else
				BasePageV2.reportFail("Play button not displayed in audio detail page");

		} catch (Exception e) {
			BasePageV2.reportFail("Play button not displayed in audio detail page");
		}

		Utilities.verticalSwipe(driver, homepagev2.downloadItem);
		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
			homepagev2.downloadItem.click();
			test.log(LogStatus.INFO, "Content added to download");
		} else
			BasePageV2.reportFail("Download Status Icon not displayed for content");

		try {
			driver.openNotifications();
		} catch (Exception e) {
		}
		ArrayList<String> anotherdownloadedcontentsds = new ArrayList<String>();
		if (Utilities.explicitWaitVisible(driver, downloadpagev2.notificationDownloadComplete, 60)) {
			test.log(LogStatus.INFO, "Download completion status displayed in notification");
			downloadpagev2.notificationDownloadComplete.click();
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.DownloadsScreen, 10)) {
				try {
					List<WebElement> downloaded = driver.findElements(By.xpath(
							"//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']"));
					int size = downloaded.size();
					for (int i = 0; i < size; i++) {

						try {
							WebElement downtitle = driver.findElement(By.xpath(
									"//android.widget.FrameLayout[@resouurce-id = 'com.viacom18.vootkids:id/parent_for_download_item'][@index='"
											+ i
											+ "']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']"));

							String titledown = downtitle.getText();
							test.log(LogStatus.INFO,
									"Content " + i + " under downloads tab displayed is: " + titledown);
							anotherdownloadedcontentsds.add(titledown.trim());

						} catch (Exception e) {
						}

					}

				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail(
						"Downloads page fails to display when tapping on download notifaication for downloaded conten");
		} else
			BasePageV2.reportFail("Download completion status not displayed in notification");

		if (downloadedcontentsds.equals(anotherdownloadedcontentsds)) {

			test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase4, rowno4, " is Fail");
		} else {
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Pass");
			HomePageV2.smokeresults(testCase4, rowno4, " is Pass");
		}

		for (int i = 0; i < Audiobooks.size(); i++) {
			downloadpagev2.deleteDownload();
		}
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
