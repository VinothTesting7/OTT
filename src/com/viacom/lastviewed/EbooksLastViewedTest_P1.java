package com.viacom.lastviewed;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class EbooksLastViewedTest_P1 extends BaseTestV2 {
	String testName = "EbooksLastViewedTest_P1";

	String testCase = "Verify the playback status for partially watched downloaded Ebook content in Last viewed tray";
	String testId = "VK_1869";

	String testCase1 = "Verify the playback status for Completely watched downloaded Ebook content in Last viewed tray";
	String testId1 = "VK_1870";

	String testCase2 = "Verify if downloaded Ebook are added to Last Viewed Tray without reading an Ebook";
	String testId2 = "VK_1871";

	String ReadPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/ebook.json";
	String ebookTitle = "";
	String xpath = "";
	int pagenumber1 = 0;
	int pagenumber2 = 0;
	int totalPages = 0;

	@Test(dataProvider = "getData")
	public void EbooksLastViewedTest(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyLastViewedBooks");
		test.log(LogStatus.INFO, "Starting the test on: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
		EbooksPageV2 ebookPage = new EbooksPageV2(driver, test);
		BooksPageV2 booksPage = new BooksPageV2(driver, test);
		String email = data.get("Email");
		String pwd = data.get("Password");

		HomePageV2.login(email, pwd);

		downloadsPage.deleteAllDownloads();

		driver.navigate().back();

		try {
			Response resp = Utilities.requestUtility(ReadPageAPI);
			ebookTitle = resp.jsonPath().getString("assets[1].assets[0].items[1].title");
			System.out.println("Displayed Ebook was: " + ebookTitle);

		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Ebook Response from API");
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		homepagev2.clearLastViewedContents();

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		HomePageV2.tabClick("Read");
		xpath = "//android.widget.TextView[contains(@text,'" + ebookTitle + "')]";

		Utilities.verticalSwipe(driver, xpath);

		try {
			driver.findElement(By.xpath(xpath)).click();
			if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 10))
				test.log(LogStatus.INFO, "Navigated to Audio Playbook button");
			else
				BasePageV2.reportFail("Failed to navigate to detail page of book: " + ebookTitle);

			Utilities.verticalSwipe(driver, downloadsPage.downloadIcon);
			if (Utilities.explicitWaitClickableNew(driver, downloadsPage.downloadIcon, 20)) {
				downloadsPage.downloadIcon.click();
				if (Utilities.explicitWaitClickableNew(driver, downloadsPage.DownloadedStatus, 120))
					test.log(LogStatus.INFO, "Ebook content '" + ebookTitle + "' downloaded successfully");
				else
					BasePageV2.reportFail("Unable to downloaad ebook: " + ebookTitle);
			} else
				BasePageV2.reportFail("Download Icon was not displayed");

			driver.navigate().back();
			Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

			HomePageV2.tabClick("My Stuff");

			xpath = "//android.widget.TextView[@text='LAST VIEWED']";
			Utilities.verticalSwipe(driver, xpath, "visible", 6);

			try {
				driver.findElement(By.xpath(xpath)).click();
				xpath = "//android.widget.TextView[contains(@text,'" + ebookTitle + "')]";

				try {
					driver.findElement(By.xpath(xpath));
					test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase2 + " -----> " + testId2);
					if (!Utilities.setResultsKids(testId2, "Fail"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");

				} catch (Exception e) {
					test.log(LogStatus.PASS, "Verified Test Case: " + testCase2 + " -----> " + testId2);
					if (!Utilities.setResultsKids(testId2, "Pass"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
			} catch (Exception e) {
				test.log(LogStatus.PASS, "Verified Test Case: " + testCase2 + " -----> " + testId2);
				if (!Utilities.setResultsKids(testId2, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}

		} catch (Exception e1) {
			BasePageV2.reportFail("Unable to click on book: " + ebookTitle);
		}

		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		HomePageV2.tabClick("Read");
		xpath = "//android.widget.TextView[contains(@text,'" + ebookTitle + "')]";

		Utilities.verticalSwipe(driver, xpath);

		try {
			driver.findElement(By.xpath(xpath)).click();

			if (Utilities.explicitWaitVisibleNew(driver, ebookPage.Readbutton, 10)) {
				ebookPage.Readbutton.click();

				for (int i = 1; i <= 5; i++) {

					if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 2))
						homepagev2.freshAppNotificationCancel.click();
					else {

					}
				}

				homepagev2.verifyAndProgressBar();
				for (int i = 0; i < 10; i++) {
					Thread.sleep(3000);
					Utilities.horizontalSwipe(driver);
				}

				Utilities.tap(driver);
				Utilities.tap(driver);

				if (Utilities.explicitWaitVisibleNew(driver, booksPage.readerPageNumber, 10)) {
					String[] pageSplit = booksPage.readerPageNumber.getText().split(" ");
					pagenumber1 = Integer.parseInt(pageSplit[0].toString());
					test.log(LogStatus.INFO, "Last Split Page number displayed was: " + pagenumber1);

					driver.navigate().back();
					driver.navigate().back();
					driver.navigate().back();

					Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
				}

			} else {
				BasePageV2.reportFail(
						"Reader page number was not displayed post double tapping on reader screen of the book");
			}
		} catch (Exception e) {

		}

		HomePageV2.tabClick("Read");
		xpath = "//android.widget.TextView[@text='LAST READ']";
		Utilities.verticalSwipe(driver, xpath, "visible", 10);

		try {

			driver.findElement(By.xpath(xpath));
			xpath = "//android.widget.TextView[contains(@text,'" + ebookTitle + "')]";
			Utilities.verticalSwipe(driver, xpath, "visible", 1);

			driver.findElement(By.xpath(xpath)).click();

			if (Utilities.explicitWaitVisibleNew(driver, ebookPage.Readbutton, 10)) {
				ebookPage.Readbutton.click();
				homepagev2.verifyAndProgressBar();
				Utilities.tap(driver);
				Utilities.tap(driver);

				if (Utilities.explicitWaitVisibleNew(driver, booksPage.readerPageNumber, 10)) {
					String[] pageSplit = booksPage.readerPageNumber.getText().split(" ");
					pagenumber2 = Integer.parseInt(pageSplit[0].toString());
					test.log(LogStatus.INFO, "Last Split Page number displayed was: " + pagenumber2);
					totalPages = Integer.parseInt(pageSplit[2].toString());

					if (pagenumber1 == pagenumber2) {
						test.log(LogStatus.PASS, "Verified Test Case: " + testCase + " -----> " + testId);
						if (!Utilities.setResultsKids(testId, "Pass"))
							test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					} else {
						test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase + " -----> " + testId);
						if (!Utilities.setResultsKids(testId, "Fail"))
							test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}

					driver.navigate().back();

					if (totalPages > 40) {
						test.log(LogStatus.SKIP,
								"Skipping the test Case as number of pages is greater than 40 and will consume time to swipe all pages: "
										+ totalPages);
						throw new SkipException("Skipping testCase: " + testCase);
					} else {
						for (int i = 0; i < totalPages - pagenumber2; i++) {
							Thread.sleep(3000);
							Utilities.horizontalSwipe(driver);
						}

						driver.navigate().back();
						driver.navigate().back();

						Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

						HomePageV2.tabClick("Read");
						
						Utilities.verticalSwipe(driver, xpath, "visible", 10);
						xpath = "//android.widget.TextView[@text='LAST READ']";
						
						try {
							driver.findElement(By.xpath(xpath));
							xpath = "//android.widget.TextView[contains(@text,'" + ebookTitle + "')]";
							Utilities.verticalSwipe(driver, xpath, "visible", 1);

							driver.findElement(By.xpath(xpath)).click();
							if (Utilities.explicitWaitVisibleNew(driver, ebookPage.Readbutton, 10)) {
								ebookPage.Readbutton.click();
								homepagev2.verifyAndProgressBar();
								Utilities.tap(driver);
								Utilities.tap(driver);
								if (Utilities.explicitWaitVisibleNew(driver, booksPage.readerPageNumber, 10)) {
									String[] pageSplit1 = booksPage.readerPageNumber.getText().split(" ");
									pagenumber1 = Integer.parseInt(pageSplit1[0].toString());
									test.log(LogStatus.INFO, "Last Split Page number displayed was: " + pagenumber1);
									
									if(pagenumber1==1) {
										test.log(LogStatus.PASS, "Verified Test Case: " + testCase1 + " -----> " + testId1);
										if (!Utilities.setResultsKids(testId1, "Pass"))
											test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									} else {
										test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase1 + " -----> " + testId1);
										if (!Utilities.setResultsKids(testId1, "Fail"))
											test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									}
								}
								else {
									BasePageV2.reportFail("Page number was not displayed post double tapping oon reader screen");
								}
							}
							else {
								BasePageV2.reportFail("Read button was not displayed");
							}
							
						} catch (Exception e) {
							BasePageV2.reportFail("Ebook was not displayed under 'Las Read' tab");
						}
					}

				}

				else {
					BasePageV2.reportFail(
							"Reader page number was not displayed post double tapping on reader screen of the book");
				}

			} else {
				BasePageV2.reportFail("Read tab was not displayed");
			}

		} catch (Exception e) {
			BasePageV2.reportFail("Last Read section was not displayed in the Read page even after 10 swipes");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
