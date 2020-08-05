package com.viacom.downloads;

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
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifySeeAllDownladsTest extends BaseTestV2 {

	String testName = "VerifySeeAllDownladsTest";
	String testCase = "'Verify that ''See All'' button is not present for the Downloads tab under My Stuff'";
	String testCase1 = "'Verify 'Edit Downloads' button functionality'";
	String testCase2 = "''";

	String tabName = "";
	int totalNumCards;
	String relatedTray = "";
	Response EbookTitle;
	String EbookMainTitle = "";
	ArrayList<String> Ebooks = null;
	ArrayList<String> Audiobooks = null;

	@Test(dataProvider = "getData")
	public void VerifySeeAllDownlads(Hashtable<String, String> data) throws Exception {

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
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		EbooksPageV2 ebookspage = new EbooksPageV2(driver, test);

		HomePageV2.login("karthiktskaaru5278@gmail.com", "karu5278");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("EbookCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);
				// String ApiName = "EbooksTab";
				Response respTab = Utilities.requestUtility(ApiTab);
				// String tabNameApi = respTab.jsonPath().get("assets[1].title");
				// tabName = tabNameApi.toUpperCase();
				Ebooks = new ArrayList<String>();
				for (int i = 0; i < 5; i++) {
					String books = respTab.jsonPath().get("assets.items[" + i + "].title");
					Ebooks.add(books);
				}
			} else
				System.out.println("Unable to fetch tab from API");
		}

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

		HomePageV2.tabClick("Read");

		for (String ebook : Ebooks) {
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

					} else
						test.log(LogStatus.FAIL, "Ebook Title not displayed in showdetail page of Ebook: " + ebook);

					driver.pressKeyCode(AndroidKeyCode.BACK);
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

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(5000);

		HomePageV2.tabClick("Listen");

		for (String abook : Audiobooks) {
			try {
				String xpath = "//android.widget.TextView[contains(@text,'" + abook + "')]";

				Utilities.verticalSwipe(driver, xpath);
				WebElement ab = driver.findElement(By.xpath(xpath));

				if (Utilities.explicitWaitVisible(driver, ab, 5)) {
					ab.click();
					if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 10)) {
						Utilities.verticalSwipe(driver, homepagev2.downloadItem);
						if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
							homepagev2.downloadItem.click();
							test.log(LogStatus.INFO, "Content '" + abook + "' added to download");
						} else
							BasePageV2.reportFail("Download Status Icon not displayed for Audio book: " + abook);
						driver.pressKeyCode(AndroidKeyCode.BACK);
					} else
						test.log(LogStatus.FAIL,
								"Audio book Title not displayed in showdetail page of Audio book: " + abook);
				} else
					BasePageV2.reportFail("Unable to find Audio Book");
			} catch (Exception e) {
				try {
					String xpath = "//android.widget.TextView[contains(@text,'" + abook + "')]";

					Utilities.verticalSwipe(driver, xpath);
					WebElement ab = driver.findElement(By.xpath(xpath));

					if (Utilities.explicitWaitVisible(driver, ab, 5)) {
						ab.click();
						if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 10)) {
							Utilities.verticalSwipe(driver, homepagev2.downloadItem);
							if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
								homepagev2.downloadItem.click();
								test.log(LogStatus.INFO, "Content '" + abook + "' added to download");
							} else
								BasePageV2.reportFail("Download Status Icon not displayed for Audio book: " + abook);
							driver.pressKeyCode(AndroidKeyCode.BACK);
						} else
							test.log(LogStatus.FAIL,
									"Audio book Title not displayed in showdetail page of Audio book: " + abook);
					} else
						BasePageV2.reportFail("Unable to find Audio Book");
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

		ArrayList<String> downloadsList = null;

		downloadsList.addAll(Audiobooks);
		downloadsList.addAll(Ebooks);

		test.log(LogStatus.INFO, "contents addded to download is: " + downloadsList);

		test.log(LogStatus.INFO, "Number of contents addded to download is: " + downloadsList.size());

		if (downloadsList.size() > 8) {
//			for (String download : downloadsList) {
//				try {
//					String xpath = "//android.widget.TextView[contains(@text,'" + download + "')]";
//
//					WebElement ab = driver.findElement(By.xpath(xpath));
//
//					if (Utilities.explicitWaitVisible(driver, ab, 5))
//						test.log(LogStatus.INFO, "Download content displayed is: " + ab.getText());
//					else
//						BasePageV2.reportFail("Download content not displayed");
//
//				} catch (Exception e) {
//					try {
//						String xpath = "//android.widget.TextView[contains(@text,'" + download + "')]";
//						Utilities.verticalSwipe(driver, xpath);
//
//						WebElement ab = driver.findElement(By.xpath(xpath));
//
//						if (Utilities.explicitWaitVisible(driver, ab, 5))
//							test.log(LogStatus.INFO, "Download content displayed is: " + ab.getText());
//						else
//							BasePageV2.reportFail("Download content not displayed");
//					} catch (Exception e2) {
//					}
//				}
//			}

			for (int i = 0; i < 8; i++) {
				try {
					String xp = "//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='"
							+ i + "']";
					Utilities.verticalSwipe(driver, xp);
					WebElement elem = driver.findElement(By.xpath(xp));
					if (Utilities.explicitWaitVisible(driver, elem, 5))
						test.log(LogStatus.INFO, "Content displayed under downloads tab");
					else
						BasePageV2.reportFail("Downloaded content is not displayed");
				} catch (Exception e) {
				}

			}
		} else
			test.log(LogStatus.INFO, "Number of contents added to download is < 8 ");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.seeall, 5)) {
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		} else {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		}

		Utilities.verticalSwipe(driver, downloadpagev2.editDownloadsMystuff);
		Thread.sleep(5000);
		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editDownloadsMystuff, 5)) {
			downloadpagev2.editDownloadsMystuff.click();
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.editDownloadsScreen, 25)) {
				test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
				HomePageV2.smokeresults(testCase1, rowno1, "Pass");
			} else
				BasePageV2.reportFail("Edit Downloads Page is not displayed");
		} else
			BasePageV2.reportFail("Edit Download button not displayed in 'My Stuff' page");

		for (int i = 0; i < downloadsList.size(); i++) {
			downloadpagev2.deleteDownload();
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
