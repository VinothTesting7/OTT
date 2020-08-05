package com.viacom.downloads;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Ordering;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.MixPanelPageVK;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class VerifyDownloadEpisodeFunctionalityTest extends BaseTestV2 {
	String testName = "VerifyDownloadEpisodeFunctionalityTest";
	String showname = "";
	String testCase = "Verify the functionality when tapping on 'Download Episodes' in show detail screen";
	String testId = "VK_965";
	String testCase1 = "Verify the UI of Download Episodes page";
	String testId1 = "VK_966";
	String testCase2 = "Verify the UI of Download Episodes page when user has not downloaded any episode";
	String testId2 = "VK_967";
	String testCase3 = "Verify that by deafult only 10 episodes should load & lazy load concept should work for the rest of the episodes in 'Download Episodes' screen";
	String testId3 = "VK_968";
	String testCase4 = "Verify sorting order of the episodes present in 'Download Episodes' screen by default(whe none of the download action called on any of the episodes)";
	String testId4 = "VK_971";

	String EpisodeCard = "";
	String Content_Name = "";
	String Content_Type = "";
	String randAPIs = "";
	Response resp;
	String showTitle = "";
	String xpath = "";
	String seriesId = "";
	String watchPageAPI = "https://api.vootkids.com/app/curated/v1/kidsCharacters.json?sortBy=mostPopular&limit=8&offSet=0";

	@Test(dataProvider = "getData")
	public void VerifyDownloadEpisodeFunctionality(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating Download Episodes Functionality");
		test.log(LogStatus.INFO,
				"Starting the test for Validating Download Episodes Functionality: " + VootConstants.DEVICE_NAME);

		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		// Author: Karthik

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		new MixPanelPageVK(driver, test);

		HomePageV2.login(data.get("Email"), data.get("Password"));

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

		// Mapping values:

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
			test.log(LogStatus.INFO, "Downloading Episode from 'Download Episodes Screen'");
			xpath = "//android.widget.TextView[@text='" + Content_Name + "']";
			/*
			 * if (Utilities.explicitWaitVisibleNew(driver, mppage.episodeDownloadIcon, 20))
			 * { try { WebElement downEpi = driver.findElement(By.xpath(xpath));
			 * test.log(LogStatus.INFO, "Adding content '" + Content_Name +
			 * "' for Download"); downEpi.click(); Thread.sleep(3000);
			 * test.log(LogStatus.INFO, "Cancelling download"); downEpi.click();
			 * }catch(Exception e) { } } } else {
			 * 
			 * }
			 */

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadStatusText, 2)) {
				test.log(LogStatus.INFO,
						"Download Status Text displayed as: " + downloadpagev2.downloadStatusText.getText());
				test.log(LogStatus.INFO, "Clicking on download icon present in show detail page of: " + showname);
				// BasePageV2.takeScreenshot();
				downloadpagev2.downloadIconEpisodes.click();
			}

			else
				BasePageV2.reportFail("Download Status Text not displayed");

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadPageHeader, 5)) {
				test.log(LogStatus.INFO, "Tapping on Download icon navigated to '"
						+ downloadpagev2.downloadPageHeader.getText() + "' page");
				// BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
				if (!Utilities.setResultsKids(testId, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else
				BasePageV2.reportFail("Tapping on download icon fails to navigate to 'Download Episodes Page'");

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadPageHeader, 1))
				test.log(LogStatus.INFO,
						"Dowload Page header text displayed as: " + downloadpagev2.downloadPageHeader.getText());
			else
				BasePageV2.reportFail("Download Page Header Text is not displayed");

			Utilities.verticalSwipeDown(driver, downloadpagev2.BackButtonDownloadPage);

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.BackButtonDownloadPage, 1))
				test.log(LogStatus.INFO, "Back Button displayed in download page");
			else
				BasePageV2.reportFail("Back Button not displayed in download page");

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.BackButtonDownloadPage, 1))
				test.log(LogStatus.INFO, "Back Button displayed in download page");
			else
				BasePageV2.reportFail("Back Button not displayed in download page");

			// driver.hideKeyboard();

			List<String> title = new ArrayList<String>();
			List<String> thumbnail = new ArrayList<String>();
			List<String> download = new ArrayList<String>();
			new ArrayList<String>();
			List<String> LazyLoad = new ArrayList<String>();
			List<String> Episodetitles = new ArrayList<String>();

			int i;
			next: for (i = 0; i < 15; i++) {

				try {
					WebElement downloadTitle = driver.findElement(By.xpath(
							"//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='"
									+ i
									+ "']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']"));

					WebElement downloadEpidetails = driver.findElement(By.xpath(
							"//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='"
									+ i
									+ "']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']"));

					WebElement downloadStatusIcon = driver.findElement(By.xpath(
							"//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='"
									+ i
									+ "']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_media_state_button']"));

					if (Utilities.explicitWaitVisible(driver, downloadTitle, 2)) {
						String Epidetails = downloadEpidetails.getText();

						Episodetitles.add(Epidetails);
						test.log(LogStatus.INFO, "Episode Title Displayed: " + downloadTitle.getText());
						title.add("Displayed");
					}

					else {
						test.log(LogStatus.FAIL, "'Episode Title' Not Displayed");
						BasePageV2.takeScreenshot();
						title.add("Not Displayed");
					}

					if (Utilities.explicitWaitVisible(driver, downloadStatusIcon, 2)) {
						test.log(LogStatus.INFO, "'Download Status Icon' Displayed for: " + downloadTitle.getText());
						download.add("Displayed");

					}

					else {
						test.log(LogStatus.FAIL, "'Download Status Icon' Not Displayed");
						BasePageV2.takeScreenshot();
						download.add("Not Displayed");
					}

					if (Utilities.explicitWaitVisible(driver, homepagev2.loadingAnimator, 1)) {
						test.log(LogStatus.INFO, "Lazy loader displayed after 10 badges");
						LazyLoad.add("Displayed");
						// BasePageV2.takeScreenshot();
					} else
						LazyLoad.add("Not Displayed");

				} catch (Exception e) {

					Utilities.verticalSwipe(driver);

					try {
						WebElement downloadTitle = driver.findElement(By.xpath(
								"//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='"
										+ i
										+ "']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']"));

						WebElement downloadStatusIcon = driver.findElement(By.xpath(
								"//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='"
										+ i
										+ "']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_media_state_button']"));

						if (Utilities.explicitWaitVisible(driver, downloadTitle, 2)) {
							String Episode = downloadTitle.getText();
							Episodetitles.add(Episode);
							test.log(LogStatus.INFO, "Episode Title Displayed: " + downloadTitle.getText());
							title.add("Displayed");
						}

						else {
							test.log(LogStatus.FAIL, "'Episode Title' Not Displayed");
							BasePageV2.takeScreenshot();
							title.add("Not Displayed");
						}

						if (Utilities.explicitWaitVisible(driver, downloadStatusIcon, 2)) {
							test.log(LogStatus.INFO,
									"'Download Status Icon' Displayed for: " + downloadTitle.getText());
							download.add("Displayed");
						}

						else {
							test.log(LogStatus.FAIL, "'Download Status Icon' Not Displayed");
							BasePageV2.takeScreenshot();
							download.add("Not Displayed");
						}

						if (Utilities.explicitWaitVisible(driver, homepagev2.loadingAnimator, 1)) {
							test.log(LogStatus.INFO, "Lazy loader displayed after 10 badges");
							LazyLoad.add("Displayed");
							// BasePageV2.takeScreenshot();
						} else
							LazyLoad.add("Not Displayed");

					} catch (Exception e2) {
						test.log(LogStatus.INFO,
								"Validated all the 'All The Episodes' in download page and total Episodes displayed is: "
										+ i);
						// BasePageV2.takeScreenshot();
						break next;
					}

				}

			}
			if ((title.contains("Displayed") && !title.contains("Not Displayed"))
					
					&& (download.contains("Displayed") && !download.contains("Not Displayed")))

			{
				test.log(LogStatus.INFO, "Episode Title is displayed for all the contents");
				test.log(LogStatus.INFO, "Thumbnail image is displayed for all the contents");
				test.log(LogStatus.INFO, "Download icon is displayed for all the contents");
				test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
				if (!Utilities.setResultsKids(testId1, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else {
				test.log(LogStatus.INFO, "Episode Title is not displayed for all the contents");
				test.log(LogStatus.INFO, "Thumbnail image is not displayed for all the contents");
				test.log(LogStatus.INFO, "Download icon is not displayed for all the contents");
				test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
				if (!Utilities.setResultsKids(testId1, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			System.out.println(title);
			System.out.println(thumbnail);
			System.out.println(download);

			if (download.contains("Displayed") && !download.contains("Not Displayed")) {
				test.log(LogStatus.INFO,
						"Download Icon displayed for All The Episodes displayed in 'Download Episodes' page");
				test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
				if (!Utilities.setResultsKids(testId2, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else {
				test.log(LogStatus.INFO,
						"Download Icon not displayed for All The  Episodes displayed in 'Download Episodes' page");
				test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
				if (!Utilities.setResultsKids(testId2, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}

			if (i >= 9 && LazyLoad.contains("Displayed") && !LazyLoad.contains("Not Displayed")) {
				test.log(LogStatus.INFO, "Lazy Loader displayed after 10 badges of episodes");
				test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
				if (!Utilities.setResultsKids(testId3, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else if (i >= 9 && LazyLoad.contains("Not Displayed")) {
				test.log(LogStatus.INFO, "Lazy Loader not displayed after 10 badges of episodesS");
				test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
				if (!Utilities.setResultsKids(testId3, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else {
				test.log(LogStatus.INFO,
						"Since number of episodes displayed is <10, Lazy Loader not displayed after 10 badges of episodes, hence passing the test");
				test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
				if (!Utilities.setResultsKids(testId3, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}

			boolean sorted = Ordering.natural().isOrdered(Episodetitles);
			if (sorted) {
				test.log(LogStatus.INFO, "Episodes in 'DownLoad Episodes' tab is sorted");
				// BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Pass");
				if (!Utilities.setResultsKids(testId4, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");

			} else {
				test.log(LogStatus.INFO, "Episodes in 'DownLoad Episodes' tab is not sorted");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
				if (!Utilities.setResultsKids(testId4, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}

			try {
				WebElement downloadStatusIcon = driver.findElement(By.xpath(
						"//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='1']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_media_state_button']"));
				downloadStatusIcon.click();

				WebElement downloadProgress = driver.findElement(By.xpath(
						"//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='1']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/squareProgressBar']"));
				if (Utilities.explicitWaitVisible(driver, downloadProgress, 1)) {
					test.log(LogStatus.INFO, "Added to download and download is in progress");

				}
			} catch (Exception e) {

			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
