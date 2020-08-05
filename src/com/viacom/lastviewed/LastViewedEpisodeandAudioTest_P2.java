package com.viacom.lastviewed;

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
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.MixPanelPageVK;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class LastViewedEpisodeandAudioTest_P2 extends BaseTestV2 {
	String testName = "LastViewedEpisodeandAudioTest_P2";

	String testCase = "Verify if downloaded Episodes are added to Last Viewed Tray without watching";
	String testId = "VK_1859";

	String testCase1 = "Verify if the Downloaded Audiobook cards are added to the Last viewed tray if user launches and closes the audio player";
	String testId1 = "VK_1860";

	String watchPageAPI = "https://api.vootkids.com/app/curated/v1/kidsCharacters.json?sortBy=mostPopular&limit=8&offSet=0";
	String ReadPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/ebook.json";
	String listenPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/audio.json";

	String ebookTitle = "";
	String xpath = "";
	int pagenumber1 = 0;
	int pagenumber2 = 0;
	int totalPages = 0;
	Response resp;
	String Content_Name = "";
	String showTitle = "";
	String randAPIs = "";
	String seriesId = "";

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
		MixPanelPageVK mppage = new MixPanelPageVK(driver, test);
		DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
		String email = data.get("Email");
		String pwd = data.get("Password");

		HomePageV2.login(email, pwd);

		downloadsPage.deleteAllDownloads();

		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(10000);

		homepagev2.clearLastViewedContents();
		Thread.sleep(2000);
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(5000);

		HomePageV2.tabClick("Watch");
		
		try {
			resp = Utilities.requestUtility(watchPageAPI);
			showTitle = resp.jsonPath().getString("assets.items[0].title");
			seriesId = resp.jsonPath().getString("assets.items[0].mId");
			randAPIs = "https://api.vootkids.com/app/ks/v1/allKidsEpisodes.json?pageSize=8&pageIndex=0&refSeriesId="
					+ seriesId;
			resp = Utilities.requestUtility(randAPIs);

			// contentTypes.add(Content_Type);
			Content_Name = resp.jsonPath().getString("assets.items[0].title");

		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Episode Response from API");
		}

		test.log(LogStatus.INFO, "Navigating to show: " + showTitle);
		xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
				+ showTitle + "']";
		Utilities.verticalSwipe(driver, xpath);
		try {
			WebElement show = driver.findElement(By.xpath(xpath));
			show.click();
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
		}

		Utilities.verticalSwipe(driver, homepagev2.DownloadStatus);

		test.log(LogStatus.INFO, "Clicking on download Icon");

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.DownloadStatus, 20)) {
			homepagev2.DownloadStatus.click();
			xpath = "//android.widget.TextView[@text='" + Content_Name + "']";
			if (Utilities.explicitWaitVisibleNew(driver, mppage.episodeDownloadIcon, 20)) {
				try {
					WebElement downEpi = driver.findElement(By.xpath(xpath));
					test.log(LogStatus.INFO, "Adding content '" + Content_Name + "' for Download");
					test.log(LogStatus.INFO, "Downloading Episode content");
					downEpi.click();
				} catch (Exception e) {
					BasePageV2.reportFail("Unable to put content: " + Content_Name + " to download");
				}
			} else {
				BasePageV2.reportFail("Downloadable contents were not displayed");
			}
			if (Utilities.explicitWaitVisibleNew(driver, mppage.downloadedEpisode, 120)) {
				test.log(LogStatus.INFO, "Episode downloaded successfully");
				driver.navigate().back();
				driver.navigate().back();
				driver.navigate().back();
			} else {
				BasePageV2.reportFail("Unable to download the episode: " + Content_Name);
			}
		} else {
			BasePageV2.reportFail("Download Status Icon was not displayed in show detail page of show: " + showTitle);
		}
		
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		String xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipe(driver, xpath, "visible", 10);

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.recentViewedClear, 3)) {
			test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase + " -----> " + testId);
			if (!Utilities.setResultsKids(testId, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		} else {
			test.log(LogStatus.PASS, "Verified Test Case: " + testCase + " -----> " + testId);
			if (!Utilities.setResultsKids(testId, "Pass"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		// Verifying last viewed for Audio content

		HomePageV2.tabClick("Listen");
		try {
			resp = Utilities.requestUtility(listenPageAPI);
			showTitle = resp.jsonPath().getString("assets[1].assets[0].items[0].title");

		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch response Audio book from API");
		}

		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for Audio Books");
		test.log(LogStatus.INFO, "Navigating to show: " + showTitle);
		xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
				+ showTitle + "']";
		Utilities.verticalSwipe(driver, xpath);

		try {
			WebElement show = driver.findElement(By.xpath(xpath));
			show.click();
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
		}

		Utilities.verticalSwipe(driver, mppage.DownloadLink);

		if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadLink, 20)) {
			test.log(LogStatus.INFO, "Adding content '" + Content_Name + "' for Download");
			mppage.DownloadLink.click();

			if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadedLink, 120)) {
				test.log(LogStatus.INFO, "Content downloaded successfully");
				Utilities.verticalSwipeDown(driver, homepagev2.playAudiobookbutton);
				if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 20)) {
					homepagev2.playAudiobookbutton.click();
					homepagev2.verifyAndProgressBar();
					Thread.sleep(10000);
					driver.navigate().back();
					driver.navigate().back();
					driver.navigate().back();
					Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
					HomePageV2.tabClick("Listen");
					
					xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
					Utilities.verticalSwipe(driver, xpath, "visible", 10);
					Utilities.verticalSwipe(driver);
					
					xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
							+ showTitle + "']";
					
					try {
						driver.findElement(By.xpath(xpath));
						test.log(LogStatus.PASS, "Verified Test Case: " + testCase1 + " -----> " + testId1);
						if (!Utilities.setResultsKids(testId1, "Pass"))
							test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase1 + " -----> " + testId1);
						if (!Utilities.setResultsKids(testId1, "Fail"))
							test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
				} else {
					BasePageV2.reportFail("Unable to play audio book content");
					BasePageV2.takeScreenshot();
				}
			} else {
				BasePageV2.reportFail("Unable to download the contentS");
			}
		} else {
			BasePageV2.reportFail("Download link was not displayed in Audio detail page");
		}
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
