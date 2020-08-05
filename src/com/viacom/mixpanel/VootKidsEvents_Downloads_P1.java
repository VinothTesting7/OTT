package com.viacom.mixpanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.MixPanelPageVK;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.Activity;

//Author: Karthik
public class VootKidsEvents_Downloads_P1 extends BaseTestV2 {
	String testName = "VootKidsEvents_Downloads_P1";
	String apiURL = "";
	String ks = "", userid = "", profileid = "";
	String carouselEpisodeContent = "";
	boolean watchFlag = false;
	boolean watchClickFlag = false;
	String mediaId = "";
	int mediaType = 0;
	boolean searchDone = false;
	boolean downloadFlag = false;
	boolean downloadCompleteFlag = false;

	// 'Download' Event Properties
	String Action_Click = "Click to Download";
	String Action_Complete = "Complete Download";
	String Action_Cancel = "Cancel Download";
	String Download_Quality = "Medium";
	String WiFi_Download = "true";
	String Download_Status_Complete = "Complete";
	String Download_Status_Cancel = "Cancelled";
	String Media_ID = "";
	String Content_Name = "";
	String Content_Type = "";
	String randAPIs = "";
	String EventName = "Download";
	// 'Download' Event Maps - Expected
	Map<String, String> valuesMapClick = new HashMap<String, String>();
	Map<String, String> valuesMapCancel = new HashMap<String, String>();
	Map<String, String> valuesMapDelete = new HashMap<String, String>();
	Map<String, String> valuesMapComplete = new HashMap<String, String>();

	ArrayList<String> ResultClick = new ArrayList<String>();
	ArrayList<String> ResultCancel = new ArrayList<String>();
	ArrayList<String> ResultComplete = new ArrayList<String>();

	ArrayList<String> mediaIds = new ArrayList<String>();
	ArrayList<String> contentnames = new ArrayList<String>();
	ArrayList<String> contentTypes = new ArrayList<String>();

	ArrayList<String> ResultDelete = new ArrayList<String>();
	// APIs Required
	String watchPageAPI = "https://api.vootkids.com/app/curated/v1/kidsCharacters.json?sortBy=mostPopular&limit=8&offSet=0";
	String ReadPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/ebook.json";
	String listenPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/audio.json";
	Response resp;
	String showTitle = "";
	String xpath = "";
	String seriesId = "";

	@Test(dataProvider = "getData")
	public void MixPanelDownloadScenarios(Hashtable<String, String> data) throws Exception {
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO,
				"Starting the test to verify "+testName+" "+ VootConstants.DEVICE_NAME);
		// Open charles application
		test.log(LogStatus.INFO,
				"1. Validating Cancelled, Complete and Click to Download Actions for Event - 'Download' for Episode, Ebook (Detail Page and Read Page), Audio and Movie content - 11 Scenarios");
		test.log(LogStatus.INFO,
				"2. Validating Delete Download Action for Event - 'Download' from My Stuff and Parent zone Page - 2 Scenarios");
		// Launch App

		launchApp();
		String email = data.get("Email");
		String password = data.get("Password");
		SettingsPageV2 settings = new SettingsPageV2(driver, test);
		HomePageV2 homepage = new HomePageV2(driver, test);
		DownloadsPageV2 downloads = new DownloadsPageV2(driver, test);
		MixPanelPageVK mppage = new MixPanelPageVK(driver, test);
		ListenPageV2 listenPage = new ListenPageV2(driver, test);
		// driver.resetApp();
		HomePageV2.logout();

		Utilities.saveCharles(testName);

		HomePageV2.login(email, password);
		MixPanelPageVK.qualitySettings(settings.deviceStremQulMedium, "Medium");

		ResultClick.clear();
		ResultCancel.clear();
		ResultComplete.clear();
		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for Episode content");

		downloads.deleteAllDownloads();

		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		Thread.sleep(10000);

		HomePageV2.tabClick("Watch");
		try {
			resp = Utilities.requestUtility(watchPageAPI);
			showTitle = resp.jsonPath().getString("assets.items[0].title");
			seriesId = resp.jsonPath().getString("assets.items[0].mId");
			randAPIs = "https://api.vootkids.com/app/ks/v1/allKidsEpisodes.json?pageSize=8&pageIndex=0&refSeriesId="
					+ seriesId;
			resp = Utilities.requestUtility(randAPIs);
			mediaId = resp.jsonPath().getString("assets.items[0].mId");
			mediaIds.add(mediaId);
			Content_Type = resp.jsonPath().getString("assets.items[0].contentType");
			contentTypes.add(Content_Type);
			//contentTypes.add(Content_Type);
			Content_Name = resp.jsonPath().getString("assets.items[0].title");
			contentnames.add(Content_Name);
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Episode Response from API");
		}

		// Mapping values:
		valuesMapClick.clear();

		valuesMapClick.put("Action", Action_Click);
		valuesMapClick.put("Download Quality", Download_Quality);
		valuesMapClick.put("WiFi Download", WiFi_Download);
		valuesMapClick.put("Download Status", "Downloading");
		valuesMapClick.put("Media ID", mediaId);
		valuesMapClick.put("Content Name", Content_Name);
		valuesMapClick.put("Content Type", Content_Type);

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

		Utilities.verticalSwipe(driver, homepage.DownloadStatus);

		test.log(LogStatus.INFO, "Clicking on download Icon");
		if (Utilities.explicitWaitVisibleNew(driver, homepage.DownloadStatus, 20)) {
			homepage.DownloadStatus.click();
			test.log(LogStatus.INFO, "Downloading Episode from 'Download Episodes Screen'");
			xpath = "//android.widget.TextView[@text='" + Content_Name + "']";
			if (Utilities.explicitWaitVisibleNew(driver, mppage.episodeDownloadIcon, 20)) {
				try {
					WebElement downEpi = driver.findElement(By.xpath(xpath));
					test.log(LogStatus.INFO, "Adding content '" + Content_Name + "' for Download");
					downEpi.click();
					Thread.sleep(3000);
					test.log(LogStatus.INFO, "Cancelling download");
					downEpi.click();

					if (Utilities.explicitWaitVisibleNew(driver, mppage.cancelDownloadLink, 20)) {
						mppage.cancelDownloadLink.click();
					} else {
						Utilities.saveandcloseCharles(testName);
						BasePageV2.reportFail("Unable to cancel the download");
					}

					if (Utilities.explicitWaitVisibleNew(driver, homepage.confirmLogout, 20)) {
						homepage.confirmLogout.click();

						valuesMapCancel.clear();
						ResultCancel.clear();
						valuesMapCancel.put("Action", Action_Cancel);
						valuesMapCancel.put("Download Quality", Download_Quality);
						valuesMapCancel.put("WiFi Download", WiFi_Download);
						valuesMapCancel.put("Download Status", Download_Status_Cancel);
						valuesMapCancel.put("Media ID", mediaId);
						valuesMapCancel.put("Content Name", Content_Name);
						valuesMapCancel.put("Content Type", Content_Type);

						downEpi.click();

						if (Utilities.explicitWaitVisibleNew(driver, mppage.downloadedEpisode, 120)) {
							mppage.downloadedEpisode.click();
							test.log(LogStatus.INFO, "Content '" + Content_Name + "' Downloaded Successfully");
							valuesMapComplete.clear();
							ResultComplete.clear();
							valuesMapComplete.put("Action", Action_Complete);
							valuesMapComplete.put("Download Quality", Download_Quality);
							valuesMapComplete.put("WiFi Download", WiFi_Download);
							valuesMapComplete.put("Download Status", Download_Status_Complete);
							valuesMapComplete.put("Media ID", mediaId);
							valuesMapComplete.put("Content Name", Content_Name);
							valuesMapComplete.put("Content Type", Content_Type);
						}
					} else {
						Utilities.saveCharles(testName);
						test.log(LogStatus.FAIL, "Failed to cancel the downloading content '" + Content_Name + "'");
						BasePageV2.takeScreenshot();
					}

				} catch (Exception e) {
					Utilities.saveCharles(testName);
					test.log(LogStatus.FAIL, "Failed to download content '" + Content_Name + "' from download screen");
					BasePageV2.takeScreenshot();
				}
			} else {
				Utilities.saveCharles(testName);
				test.log(LogStatus.FAIL, "Episode contents not displayed under 'Download Episodes Screen'");
				BasePageV2.takeScreenshot();
			}
		} else {
			Utilities.saveCharles(testName);
			test.log(LogStatus.FAIL, "Unable to download Episode content");
			BasePageV2.takeScreenshot();
		}
		Thread.sleep(60000);
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		HomePageV2.tabClick("Listen");
		Utilities.saveandcloseCharles(testName);
		MixPanelPageVK.mixpnlEventsfetchLoggedInuserDownloadEvents(email, EventName, mediaId);
		if (MixPanelPageVK.clickDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapClick.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapClick.get(key);
				String actvalue = MixPanelPageVK.mixevntsClicktoDownload.get(key);

				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultClick.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Click to Download' and value displayed was: " + actvalue);
				} else {
					ResultClick.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Click to Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Click to Download' Event validation fail for Episode content");
		}

		if (MixPanelPageVK.cancelDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapCancel.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapCancel.get(key);
				String actvalue = MixPanelPageVK.mixevntsCancelDownload.get(key);

				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultCancel.add("Pass");
					test.log(LogStatus.INFO,
							"Verified '" + key + "' of Action 'Cancel Download' and value displayed was: " + actvalue);
				} else {
					ResultCancel.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Cancel Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Cancel Download' Event validation fail for Episode content");
		}

		if (MixPanelPageVK.completeDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapComplete.entrySet()) {

				String key = entryMap.getKey();
				String expvalue = valuesMapComplete.get(key);
				String actvalue = MixPanelPageVK.mixevntsCompleteDownload.get(key);

				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultComplete.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
				} else {
					ResultComplete.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Complete Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Complete Download' Event validation fail for Episode content");
		}

		if (ResultClick.contains("Pass") && !ResultClick.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Click To Download' Action for 'Download' Event for Episodes");
		else
			test.log(LogStatus.FAIL, "'Click To Download' Action failed for 'Download' Event for Episodes");

		if (ResultCancel.contains("Pass") && !ResultCancel.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Cancel Download' Action for 'Download' Event for Episodes");
		else
			test.log(LogStatus.FAIL, "'Cancel Download' Action failed for 'Download' Event for Episodes");

		if (ResultComplete.contains("Pass") && !ResultComplete.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Complete Download' Action for 'Download' Event for Episodes");
		else
			test.log(LogStatus.FAIL, "'Complete Download' Action failed for 'Download' Event of Episodes");

		Utilities.openCharles();

		//test.log(LogStatus.INFO, "Killing the Application to validate Download events for Audio book contents");
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		Thread.sleep(10000);
		Utilities.saveCharles(testName);
		ResultClick.clear();
		ResultCancel.clear();
		ResultComplete.clear();
		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for Audio content");

		HomePageV2.tabClick("Listen");
		try {
			resp = Utilities.requestUtility(listenPageAPI);
			showTitle = resp.jsonPath().getString("assets[1].assets[0].items[0].title");

			mediaId = resp.jsonPath().getString("assets[1].assets[0].items[0].mId");
			mediaIds.add(mediaId);
			Content_Type = resp.jsonPath().getString("assets[1].assets[0].items[0].contentType");
			contentTypes.add(Content_Type);
			Content_Name = resp.jsonPath().getString("assets[1].assets[0].items[0].title");
			contentnames.add(Content_Name);
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
		// mppage.DownloadLink.click();
		valuesMapClick.clear();

		valuesMapClick.put("Action", Action_Click);
		valuesMapClick.put("Download Quality", Download_Quality);
		valuesMapClick.put("WiFi Download", WiFi_Download);
		valuesMapClick.put("Download Status", "Downloading");
		valuesMapClick.put("Media ID", mediaId);
		valuesMapClick.put("Content Name", Content_Name);
		valuesMapClick.put("Content Type", Content_Type);

		test.log(LogStatus.INFO, "Clicking on download Icon");

		if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadLink, 20)) {
			test.log(LogStatus.INFO, "Adding content '" + Content_Name + "' for Download");
			mppage.DownloadLink.click();
			// Cancel download was Unable to test as content is downloading fast

			if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadedLink, 120)) {

				Utilities.verticalSwipeDown(driver, homepage.playAudiobookbutton);
				if (Utilities.explicitWaitClickableNew(driver, homepage.playAudiobookbutton, 20))
					homepage.playAudiobookbutton.click();
				else {
					Utilities.saveCharles(testName);
					test.log(LogStatus.FAIL, "Unable to play audio book content");
					BasePageV2.takeScreenshot();
				}
				test.log(LogStatus.INFO, "Content '" + Content_Name + "' Downloaded Successfully");
				valuesMapComplete.clear();
				ResultComplete.clear();
				valuesMapComplete.put("Action", Action_Complete);
				valuesMapComplete.put("Download Quality", Download_Quality);
				valuesMapComplete.put("WiFi Download", WiFi_Download);
				valuesMapComplete.put("Download Status", Download_Status_Complete);
				valuesMapComplete.put("Media ID", mediaId);
				valuesMapComplete.put("Content Name", Content_Name);
				valuesMapComplete.put("Content Type", Content_Type);
			} else {
				Utilities.saveCharles(testName);
				test.log(LogStatus.FAIL, "Unable to download Audio Book content");
				BasePageV2.takeScreenshot();
			}
		} else {
			Utilities.saveCharles(testName);
			test.log(LogStatus.FAIL, "Unable to download Audio Book content");
			BasePageV2.takeScreenshot();
		}

		Thread.sleep(45000);
		driver.navigate().back();
		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		HomePageV2.tabClick("Listen");

		Utilities.saveandcloseCharles(testName);
		MixPanelPageVK.mixevntsClicktoDownload.clear();
		MixPanelPageVK.mixevntsCompleteDownload.clear();

		MixPanelPageVK.mixpnlEventsfetchLoggedInuserDownloadEvents(email, EventName, mediaId);
		if (MixPanelPageVK.clickDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapClick.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapClick.get(key);
				String actvalue = MixPanelPageVK.mixevntsClicktoDownload.get(key);

				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultClick.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Click to Download' and value displayed was: " + actvalue);
				} else {
					ResultClick.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Click to Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Click to Download' Event validation fail for Episode content");
		}

		if (MixPanelPageVK.completeDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapComplete.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapComplete.get(key);
				String actvalue = MixPanelPageVK.mixevntsCompleteDownload.get(key);

				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultComplete.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
				} else {
					ResultComplete.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Complete Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Complete Download' Event validation fail for Episode content");
		}

		if (ResultClick.contains("Pass") && !ResultClick.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Click To Download' Action for 'Download' Event for Audio conntent");
		else
			test.log(LogStatus.FAIL, "'Click To Download' Action failed for 'Download' Event of Audio conntent");

		if (ResultComplete.contains("Pass") && !ResultComplete.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Complete Download' Action for 'Download' Event for Audio conntent");
		else
			test.log(LogStatus.FAIL, "'Complete Download' Action failed for 'Download' Event of Audio conntent");

		Utilities.openCharles();

		//test.log(LogStatus.INFO, "Killing the Application to validate Download events for Audio book contents");
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		Utilities.saveCharles(testName);
		ResultClick.clear();
		ResultCancel.clear();
		ResultComplete.clear();

		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for Ebook content");

		HomePageV2.tabClick("Read");
		try {
			resp = Utilities.requestUtility(ReadPageAPI);
			showTitle = resp.jsonPath().getString("assets[1].assets[0].items[1].title");

			mediaId = resp.jsonPath().getString("assets[1].assets[0].items[1].mId");
			mediaIds.add(mediaId);
			Content_Type = resp.jsonPath().getString("assets[1].assets[0].items[1].contentType");
			contentTypes.add(Content_Type);
			Content_Name = resp.jsonPath().getString("assets[1].assets[0].items[1].title");
			contentnames.add(Content_Name);
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Ebook Response from API");
		}

		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for Ebooks");
		test.log(LogStatus.INFO, "Navigating to show: " + showTitle);
		xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
				+ showTitle + "']";
		Utilities.verticalSwipe(driver, xpath);

		try {
			WebElement show = driver.findElement(By.xpath(xpath));
			show.click();
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to navigate to Ebook: " + showTitle);
		}

		Utilities.verticalSwipe(driver, mppage.DownloadLink);
		// mppage.DownloadLink.click();
		valuesMapClick.clear();
		
		valuesMapClick.put("Action", Action_Click);
		valuesMapClick.put("Download Quality", Download_Quality);
		valuesMapClick.put("WiFi Download", WiFi_Download);
		valuesMapClick.put("Download Status", "Downloading");
		valuesMapClick.put("Media ID", mediaId);
		valuesMapClick.put("Content Name", Content_Name);
		valuesMapClick.put("Content Type", Content_Type);

		test.log(LogStatus.INFO, "Clicking on download Icon");

		if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadLink, 20)) {
			test.log(LogStatus.INFO, "Adding content '" + Content_Name + "' for Download");
			mppage.DownloadLink.click();

			if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadedLink, 120)) {
				Utilities.verticalSwipeDown(driver, listenPage.playButtonInBook);
				if (Utilities.explicitWaitClickableNew(driver, listenPage.playButtonInBook, 20))
					listenPage.playButtonInBook.click();
				else {
					Utilities.saveCharles(testName);
					test.log(LogStatus.FAIL, "Read button was not displayed post downloading Ebook");
					BasePageV2.takeScreenshot();
				}
				test.log(LogStatus.INFO, "Content '" + Content_Name + "' Downloaded Successfully");
				valuesMapComplete.clear();
				ResultComplete.clear();
				valuesMapComplete.put("Action", Action_Complete);
				valuesMapComplete.put("Download Quality", Download_Quality);
				valuesMapComplete.put("WiFi Download", WiFi_Download);
				valuesMapComplete.put("Download Status", Download_Status_Complete);
				valuesMapComplete.put("Media ID", mediaId);
				valuesMapComplete.put("Content Name", Content_Name);
				valuesMapComplete.put("Content Type", Content_Type);
			} else {
				Utilities.saveCharles(testName);
				test.log(LogStatus.FAIL, "Unable to download Ebook content");
				BasePageV2.takeScreenshot();
			}
		} else {
			Utilities.saveCharles(testName);
			test.log(LogStatus.FAIL, "Unable to download Ebook content");
			BasePageV2.takeScreenshot();
		}

		Thread.sleep(45000);
		driver.navigate().back();
		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		HomePageV2.tabClick("Listen");
		Thread.sleep(10000);

		Utilities.saveandcloseCharles(testName);
		MixPanelPageVK.mixpnlEventsfetchLoggedInuserDownloadEvents(email, EventName, mediaId);
		if (MixPanelPageVK.clickDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapClick.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapClick.get(key);
				String actvalue = MixPanelPageVK.mixevntsClicktoDownload.get(key);

				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultClick.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Click to Download' and value displayed was: " + actvalue);
				} else {
					ResultClick.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Click to Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Click to Download' Event validation fail for Episode content");
		}

		if (MixPanelPageVK.completeDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapComplete.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapComplete.get(key);
				String actvalue = MixPanelPageVK.mixevntsCompleteDownload.get(key);

				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultComplete.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
				} else {
					ResultComplete.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Complete Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Complete Download' Event validation fail for Episode content");
		}

		if (ResultClick.contains("Pass") && !ResultClick.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Click To Download' Action for 'Download' Event for Ebook conntent");
		else
			test.log(LogStatus.FAIL, "'Click To Download' Action failed for 'Download' Event of Ebook conntent");

		if (ResultComplete.contains("Pass") && !ResultComplete.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Complete Download' Action for 'Download' Event for Ebook conntent");
		else
			test.log(LogStatus.FAIL, "'Complete Download' Action failed for 'Download' Event of Ebook conntent");

		// Validating Movie download scenarios

		/*Utilities.openCharles();

		test.log(LogStatus.INFO, "Killing the Application to validate Download events for Movie content");
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for movie content");
		Thread.sleep(10000);
		Utilities.saveCharles(testName);
		searchDone = searchpagev2.performSearchAndClickOnFirstSearchResultClickMovie("Shiva The Secret World of Vedas City");

		ResultClick.clear();
		ResultCancel.clear();
		ResultComplete.clear();

		if (searchDone == true) {
			Utilities.verticalSwipe(driver, mppage.DownloadLink);
			// mppage.DownloadLink.click();
			valuesMapClick.clear();
			mediaId = "684891";
			mediaIds.add(mediaId);
			Content_Type = "Movie";
			contentTypes.add(Content_Type);
			Content_Name = "Shiva The Secret World of Vedas City";
			contentnames.add(Content_Name);

			valuesMapClick.put("Action", Action_Click);
			valuesMapClick.put("Download Quality", Download_Quality);
			valuesMapClick.put("WiFi Download", WiFi_Download);
			valuesMapClick.put("Download Status", "Downloading");
			valuesMapClick.put("Media ID", mediaId);
			valuesMapClick.put("Content Name", Content_Name);
			valuesMapClick.put("Content Type", Content_Type);

			valuesMapCancel.clear();

			valuesMapCancel.put("Action", Action_Cancel);
			valuesMapCancel.put("Download Quality", Download_Quality);
			valuesMapCancel.put("WiFi Download", WiFi_Download);
			valuesMapCancel.put("Download Status", "Cancelled");
			valuesMapCancel.put("Media ID", mediaId);
			valuesMapCancel.put("Content Name", Content_Name);
			valuesMapCancel.put("Content Type", Content_Type);

			test.log(LogStatus.INFO, "Clicking on download Icon");

			if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadLink, 20)) {
				test.log(LogStatus.INFO, "Adding content '" + Content_Name + "' for Download");
				mppage.DownloadLink.click();
				Thread.sleep(5000);
				mppage.DownloadLink.click();

				if (Utilities.explicitWaitVisibleNew(driver, mppage.cancelDownloadLink, 20)) {
					mppage.cancelDownloadLink.click();
				} else {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Unable to cancel the download");
				}

				if (Utilities.explicitWaitVisibleNew(driver, homepage.confirmLogout, 20)) {
					homepage.confirmLogout.click();

					Utilities.verticalSwipeDown(driver, homepage.playAudiobookbutton);
					if (Utilities.explicitWaitClickableNew(driver, homepage.playAudiobookbutton, 20))
						homepage.playAudiobookbutton.click();
					else {
						Utilities.saveCharles(testName);
						test.log(LogStatus.FAIL, "Unable to play Movie content");
						BasePageV2.takeScreenshot();
					}

				} else {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Unable to cancel the download");
				}

			} else {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Download link was not displayed");
			}

		} else {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Unable to select the Movie from search results");
		}

		Thread.sleep(45000);
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		HomePageV2.tabClick("Listen");
		Utilities.saveandcloseCharles(testName);
		MixPanelPageVK.mixpnlEventsfetchLoggedInuserDownloadEvents(email, EventName, "684891");
		if (MixPanelPageVK.clickDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapClick.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapClick.get(key);
				String actvalue = MixPanelPageVK.mixevntsClicktoDownload.get(key);
				
				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\]", "");
					
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}
				
				try {
					
					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}
				
				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultClick.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Click to Download' and value displayed was: " + actvalue);
				} else {
					ResultClick.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Click to Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Click to Download' Event validation fail for Movie content");
		}

		if (MixPanelPageVK.cancelDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapCancel.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapCancel.get(key);
				String actvalue = MixPanelPageVK.mixevntsCancelDownload.get(key);
				
				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\]", "");
					
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}
				
				try {
					
					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}
				
				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultCancel.add("Pass");
					test.log(LogStatus.INFO,
							"Verified '" + key + "' of Action 'Cancel Download' and value displayed was: " + actvalue);
				} else {
					ResultCancel.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Cancel Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Cancel Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Cancel Download' Event validation fail for Movie content");
		}

		if (ResultClick.contains("Pass") && !ResultClick.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Click To Download' Action for 'Download' Event for Movie conntent");
		else
			test.log(LogStatus.FAIL, "'Click To Download' Action failed for 'Download' Event of Movie conntent");

		if (ResultCancel.contains("Pass") && !ResultCancel.contains("Fail"))
			test.log(LogStatus.PASS, "Verified 'Cancel Download' Action for 'Download' Event of Movie conntent");
		else
			test.log(LogStatus.FAIL, "'Cancel Download' Action failed for 'Download' Event for Movie conntent");*/

		// Validating Episodes downloads under 'Read Page' post swiping 5 pages.

		Utilities.openCharles();
		Utilities.saveCharles(testName);

	//	test.log(LogStatus.INFO, "Killing the Application to validate Download events for Ebook under contents");
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		Utilities.saveCharles(testName);
		ResultClick.clear();
		ResultCancel.clear();
		ResultComplete.clear();

		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for Ebook content in Read Page");

		HomePageV2.tabClick("Read");

		try {
			resp = Utilities.requestUtility(ReadPageAPI);
			showTitle = resp.jsonPath().getString("assets[1].assets[0].items[2].title");

			mediaId = resp.jsonPath().getString("assets[1].assets[0].items[2].mId");
			mediaIds.add(mediaId);
			Content_Type = resp.jsonPath().getString("assets[1].assets[0].items[2].contentType");
			contentTypes.add(Content_Type);
			Content_Name = resp.jsonPath().getString("assets[1].assets[0].items[2].title");
			contentnames.add(Content_Name);
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Ebook Response from API");
		}

		test.log(LogStatus.INFO,
				"Validating download cancel, Complete and Click to Download Actions for Download Event for Ebooks");
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

		if (Utilities.explicitWaitClickableNew(driver, listenPage.playButtonInBook, 20)) {
			listenPage.playButtonInBook.click();
			homepage.verifyAndProgressBar();
			int j = 5;
			downloadFlag = false;
			for (int i = 0; i < 15; i++) {
				Thread.sleep(3000);
				Utilities.horizontalSwipe(driver);
				if (i == j) {
					j = j + i;
					if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadBtn, 20)) {
						test.log(LogStatus.INFO, "Download button was displayed");
						downloadFlag = true;
						break;
					}
				}
			}

			if (downloadFlag == true) {
				try {
					mppage.DownloadBtn.click();
				} catch (Exception e) {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Unable to Click on Download button under Book Reader post swiping 5 pages");
				}

				if (Utilities.explicitWaitVisibleNew(driver, mppage.CancelBtn, 5)) {
					mppage.CancelBtn.click();
				} else {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Cancel button was not displayed post clicking on download button");
				}

				if (Utilities.explicitWaitClickableNew(driver, listenPage.playButtonInBook, 20)) {
					listenPage.playButtonInBook.click();
					homepage.verifyAndProgressBar();
					int k = 5;
					downloadFlag = false;
					for (int i = 0; i < 15; i++) {
						Thread.sleep(3000);
						Utilities.horizontalSwipe(driver);
						if (i == k) {
							j = k + i;
							if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadBtn, 20)) {
								test.log(LogStatus.INFO, "Download button was displayed");
								downloadFlag = true;
								break;
							}
						}
					}

					if (downloadFlag == true) {
						try {
							mppage.DownloadBtn.click();
						} catch (Exception e) {
							Utilities.saveandcloseCharles(testName);
							BasePageV2.reportFail(
									"Unable to Click on Download button under Book Reader post swiping 5 pages");
						}
						downloadCompleteFlag = false;
						for (int i = 0; i < 200; i++) {
							if (!Utilities.explicitWaitVisibleNew(driver, mppage.CancelBtn, 5)) {
								downloadCompleteFlag = true;
								break;
							} else
								Thread.sleep(1000);

						}

						if (downloadCompleteFlag == true)
							test.log(LogStatus.INFO, "Successfully downloaded the Ebook content from 'Reader' screen");
						else {
							Utilities.saveandcloseCharles(testName);
							BasePageV2.reportFail("Unable to Download the content");
						}
					} else {
						Utilities.saveandcloseCharles(testName);
						test.log(LogStatus.FAIL, "Content Not downloaded even after 200 seconds");
						BasePageV2.takeScreenshot();
					}
				} else {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Unable to cancel the download");
				}

			} else {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Download button was not displayed in Read Page");
			}

		}

		else {
			Utilities.saveCharles(testName);
			test.log(LogStatus.FAIL, "Read button was not displayed post downloading Ebook");
			BasePageV2.takeScreenshot();
		}
		driver.navigate().back();
		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		HomePageV2.tabClick("Listen");

		// Just navigating to profile screen for extra action

		if (Utilities.explicitWaitVisibleNew(driver, homepage.profilepic, 5))
			homepage.profilepic.click();

		Thread.sleep(45000);
		valuesMapClick.clear();

		valuesMapClick.put("Action", Action_Click);
		valuesMapClick.put("Download Quality", Download_Quality);
		valuesMapClick.put("WiFi Download", WiFi_Download);
		valuesMapClick.put("Download Status", "Downloading");
		valuesMapClick.put("Media ID", mediaId);
		valuesMapClick.put("Content Name", Content_Name);
		valuesMapClick.put("Content Type", Content_Type);

		valuesMapCancel.clear();
		ResultCancel.clear();
		valuesMapCancel.put("Action", Action_Cancel);
		valuesMapCancel.put("Download Quality", Download_Quality);
		valuesMapCancel.put("WiFi Download", WiFi_Download);
		valuesMapCancel.put("Download Status", Download_Status_Cancel);
		valuesMapCancel.put("Media ID", mediaId);
		valuesMapCancel.put("Content Name", Content_Name);
		valuesMapCancel.put("Content Type", Content_Type);

		valuesMapComplete.clear();
		ResultComplete.clear();
		valuesMapComplete.put("Action", Action_Complete);
		valuesMapComplete.put("Download Quality", Download_Quality);
		valuesMapComplete.put("WiFi Download", WiFi_Download);
		valuesMapComplete.put("Download Status", Download_Status_Complete);
		valuesMapComplete.put("Media ID", mediaId);
		valuesMapComplete.put("Content Name", Content_Name);
		valuesMapComplete.put("Content Type", Content_Type);

		Utilities.saveandcloseCharles(testName);
		MixPanelPageVK.mixpnlEventsfetchLoggedInuserDownloadEvents(email, EventName, mediaId);
		if (MixPanelPageVK.clickDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapClick.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapClick.get(key);
				String actvalue = MixPanelPageVK.mixevntsClicktoDownload.get(key);
				
				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\]", "");
					
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}
				
				try {
					
					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}
				
				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultClick.add("Pass");
					test.log(LogStatus.INFO, "Verified '" + key
							+ "' of Action 'Click to Download' and value displayed was: " + actvalue);
				} else {
					ResultClick.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Click to Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Click to Download' Event validation fail for Ebook under Reader Screen");
		}

		if (MixPanelPageVK.cancelDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapCancel.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapCancel.get(key);
				String actvalue = MixPanelPageVK.mixevntsCancelDownload.get(key);
				
				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\]", "");
					
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}
				
				try {
					
					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}
				
				if (actvalue.equalsIgnoreCase(expvalue)) {
					ResultCancel.add("Pass");
					test.log(LogStatus.INFO,
							"Verified '" + key + "' of Action 'Cancel Download' and value displayed was: " + actvalue);
				} else {
					ResultCancel.add("Fail");
					test.log(LogStatus.FAIL,
							"'" + key + "' of Action 'Click to Download' was fail and value displayed was: " + actvalue
									+ " Expected value --->" + expvalue);
				}
			}
		} else {
			test.log(LogStatus.INFO, "Cancel Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Cancel Download' Event validation fail for Ebook under Reader Screen");
		}

		if (MixPanelPageVK.completeDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapComplete.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapComplete.get(key);
				String actvalue = MixPanelPageVK.mixevntsCompleteDownload.get(key);
				
				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\]", "");
					
				} catch (Exception e) {
					
				}
				
				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}
				
				try {
					
					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}
				 
					if (actvalue.equalsIgnoreCase(expvalue)) {
						ResultComplete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultComplete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}

			
		} else {
			test.log(LogStatus.INFO, "Complete Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Complete Download' Event validation fail for Ebook under Reader Screen");
		}

		if (ResultClick.contains("Pass") && !ResultClick.contains("Fail"))
			test.log(LogStatus.PASS,
					"Verified 'Click To Download' Action for 'Download' Event of Ebook under Reader Screen");
		else
			test.log(LogStatus.FAIL,
					"'Click To Download' Action failed for 'Download' Event of Ebook under Reader Screen");

		if (ResultCancel.contains("Pass") && !ResultCancel.contains("Fail"))
			test.log(LogStatus.PASS,
					"Verified 'Cancel Download' Action for 'Download' Event of Ebook under Reader Screen");
		else
			test.log(LogStatus.FAIL,
					"'Cancel Download' Action failed for 'Download' Event of Ebook under Reader Screen");

		if (ResultComplete.contains("Pass") && !ResultComplete.contains("Fail"))
			test.log(LogStatus.PASS,
					"Verified 'Complete Download' Action for 'Download' Event for of Ebook under Reader Screen");
		else
			test.log(LogStatus.FAIL,
					"'Complete Download' Action failed for 'Download' Event of Ebook under Reader Screen");

		Utilities.openCharles();
		Utilities.saveCharles(testName);

	//	test.log(LogStatus.INFO, "Killing the Application to validate Download events for Audio book contents");
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		Utilities.saveCharles(testName);
		ResultDelete.clear();

		test.log(LogStatus.INFO, "Validating Delete download action in parent zone page under preferences tab");
		if (Utilities.explicitWaitVisibleNew(driver, homepage.profilepic, 20)) {
			homepage.profilepic.click();

			// Thread.sleep(10000);

			if (Utilities.explicitWaitVisible(driver, settings.parentZoneButton, 10)) {
				settings.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settings.parentPinContainer, 10)) {
					Thread.sleep(1000);
					settings.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				} else
					BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
			} else
				BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");

			Thread.sleep(15000);
//abcd
			/*driver.runAppInBackground(Duration.ofSeconds(3));
			test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			driver.currentActivity();*/

			if (Utilities.explicitWaitVisibleNew(driver, mppage.preferencesTab, 20)) {
				mppage.preferencesTab.click();
				Utilities.verticalSwipe(driver, mppage.manageDownloadsLink);
				if (Utilities.explicitWaitVisibleNew(driver, mppage.manageDownloadsLink, 20)) {
					mppage.manageDownloadsLink.click();

					if (Utilities.explicitWaitVisibleNew(driver, mppage.deleteIcon, 20)) {
						mppage.deleteIcon.click();
						if (Utilities.explicitWaitVisibleNew(driver, mppage.deleteDownloadButton, 20)) {
							mppage.deleteDownloadButton.click();
							if (Utilities.explicitWaitVisibleNew(driver, homepage.confirmLogout, 20))
								homepage.confirmLogout.click();
							else {
								Utilities.saveandcloseCharles(testName);
								BasePageV2.reportFail(
										"Confirm Delete popup was not displayed post tapping on Delete download button");
							}
						} else {
							Utilities.saveandcloseCharles(testName);
							BasePageV2.reportFail("Delete Button was not displayed");
						}
					} else {
						Utilities.saveandcloseCharles(testName);
						BasePageV2.reportFail("Delete Icon was not displayed in edit downloads screen");
					}

				} else {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Manage download link was not displayed");
				}
			}

			else {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Preferences Tab was not displayed under Parent Zone Page");
			}

		} else {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Profile Button was not displayed");
		}
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
		
		HomePageV2.tabClick("Listen");
		Thread.sleep(45000);
		Utilities.saveandcloseCharles(testName);
		MixPanelPageVK.mixpnlEventsfetchLoggedInuserDeleteDownloadEvents(email, EventName);

		valuesMapDelete.clear();
		ResultCancel.clear();
		valuesMapDelete.put("Action", "Delete Downloaded");
		valuesMapDelete.put("Download Quality", Download_Quality);
		valuesMapDelete.put("WiFi Download", WiFi_Download);
		valuesMapDelete.put("Download Status", "Deleted");
		valuesMapDelete.put("Media ID", mediaId);
		valuesMapDelete.put("Content Name", Content_Name);
		valuesMapDelete.put("Content Type", Content_Type);

		if (MixPanelPageVK.completeDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapComplete.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapComplete.get(key);
				String actvalue = MixPanelPageVK.mixevntsCompleteDownload.get(key);
				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (key.equalsIgnoreCase("Content Type")) {
					if (contentTypes.contains(actvalue) || contentTypes.contains(actvalue.toLowerCase())) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}
				
				else if(key.equalsIgnoreCase("Content Name")) {
					if (contentnames.contains(actvalue)) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}
				
				else if(key.equalsIgnoreCase("Media ID")) {
					if (mediaIds.contains(actvalue)) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}
				
				else {
					if (actvalue.contains(expvalue)) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}

			}
		} else {
			test.log(LogStatus.INFO, "Complete Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Complete Download' Event validation fail for Ebook under Reader Screen");
		}

		if (ResultDelete.contains("Pass") && !ResultDelete.contains("Fail"))
			test.log(LogStatus.PASS,
					"Verified 'Delete Downloaded' Action for 'Download' Event under 'Preferences' Tab in Parent Zone Page");
		else
			test.log(LogStatus.FAIL,
					"'Delete Downloaded' Action failed for 'Download' Event under 'Preferences' Tab in Parent Zone Page");
		
		Utilities.openCharles();
		Utilities.saveCharles(testName);

		//test.log(LogStatus.INFO, "Killing the Application to validate Download events for Audio book contents");
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		Utilities.saveCharles(testName);
		ResultDelete.clear();

		test.log(LogStatus.INFO, "Validating Delete download action in Home Page under 'My Stuff' tab");
		
		downloads.deleteAllDownloads();
		
		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		HomePageV2.tabClick("Listen");
		Thread.sleep(45000);
		
		Utilities.saveandcloseCharles(testName);
		MixPanelPageVK.mixpnlEventsfetchLoggedInuserDeleteDownloadEvents(email, EventName);

		valuesMapDelete.clear();
		ResultCancel.clear();
		valuesMapDelete.put("Action", "Delete Downloaded");
		valuesMapDelete.put("Download Quality", Download_Quality);
		valuesMapDelete.put("WiFi Download", WiFi_Download);
		valuesMapDelete.put("Download Status", "Deleted");
		valuesMapDelete.put("Media ID", mediaId);
		valuesMapDelete.put("Content Name", Content_Name);
		valuesMapDelete.put("Content Type", Content_Type);

		if (MixPanelPageVK.completeDownload == true) {
			for (Map.Entry<String, String> entryMap : valuesMapComplete.entrySet()) {
				String key = entryMap.getKey();
				String expvalue = valuesMapComplete.get(key);
				String actvalue = MixPanelPageVK.mixevntsCompleteDownload.get(key);
				try {
					actvalue = actvalue.replaceAll("\\}", "");
				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\]", "");

				} catch (Exception e) {
					
				}

				try {
					actvalue = actvalue.replaceAll("\\{", "");
				} catch (Exception e) {
					
				}

				try {

					actvalue = actvalue.replaceAll("\\[", "");
				} catch (Exception e) {
					
				}

				if (key.equalsIgnoreCase("Content Type")) {
					if (contentTypes.contains(actvalue) || contentTypes.contains(actvalue.toLowerCase())) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}
				
				else if(key.equalsIgnoreCase("Content Name")) {
					if (contentnames.contains(actvalue)) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}
				
				else if(key.equalsIgnoreCase("Media ID")) {
					if (mediaIds.contains(actvalue)) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}
				
				else {
					if (actvalue.contains(expvalue)) {
						ResultDelete.add("Pass");
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' of Action 'Complete Download' and value displayed was: " + actvalue);
					} else {
						ResultDelete.add("Fail");
						test.log(LogStatus.FAIL,
								"'" + key + "' of Action 'Click to Download' was fail and value displayed was: "
										+ actvalue + " Expected value --->" + expvalue);
					}
				}

			}
		} else {
			test.log(LogStatus.INFO, "Complete Download Event fails to trigger");
			test.log(LogStatus.FAIL, "'Complete Download' Event validation fail for Ebook under Reader Screen");
		}

		if (ResultDelete.contains("Pass") && !ResultDelete.contains("Fail"))
			test.log(LogStatus.PASS,
					"Verified 'Delete Downloaded' Action for 'Download' Event under 'My Stuff' Tab in Home Page");
		else
			test.log(LogStatus.FAIL,
					"'Delete Downloaded' Action failed for 'Download' Event under 'My Stuff' Tab in Home Page");
		

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
