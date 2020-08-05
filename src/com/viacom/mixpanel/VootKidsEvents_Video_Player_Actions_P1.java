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
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.MixPanelPageVK;
import com.viacom.pagesVersion2.PlayPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.Activity;


//Author: Karthik Date - 14/June/2019
public class VootKidsEvents_Video_Player_Actions_P1 extends BaseTestV2 {

	String testName = "VootKidsEvents_Video_Player_Actions_P1";

	boolean audioFlag = false;
	boolean audioClickFlag = false;
	String mediaId = "";
	String mediaType = "";
	String movieAPI = "";
	String EventName = "Player Action";
	String trayTitle = "";
	String nextPageAPI = "";
	boolean movFlag = false;
	// 'Download' Event Maps - Expected

	static Map<String, String> valuesMaprewind = new HashMap<String, String>();
	static Map<String, String> valuesMapforward = new HashMap<String, String>();
	static Map<String, String> valuesMappause = new HashMap<String, String>();
	static Map<String, String> valuesMapresume = new HashMap<String, String>();
	static Map<String, String> valuesMaplanguageChanged = new HashMap<String, String>();
	static Map<String, String> valuesMapchangequality = new HashMap<String, String>();
	static Map<String, String> valuesMapfavourite = new HashMap<String, String>();
	static Map<String, String> valuesMapunfavourite = new HashMap<String, String>();
	static Map<String, String> valuesMapupArrow = new HashMap<String, String>();

	ArrayList<String> Resultrewind = new ArrayList<String>();
	ArrayList<String> Resultforward = new ArrayList<String>();
	ArrayList<String> Resultpause = new ArrayList<String>();
	ArrayList<String> Resultresume = new ArrayList<String>();
	ArrayList<String> ResultlanguageChanged = new ArrayList<String>();
	ArrayList<String> ResultchangeQuality = new ArrayList<String>();
	ArrayList<String> Resultfavourite = new ArrayList<String>();
	ArrayList<String> Resultunfavourite = new ArrayList<String>();
	ArrayList<String> ResultupArrow = new ArrayList<String>();

	String watchPageAPI = "https://api.vootkids.com/app/curated/v1/kidsCharacters.json?sortBy=mostPopular&limit=8&offSet=0";
	Response resp;
	String showTitle = "";
	String xpath = "";
	String seriesId = "";
	String randAPI = "";

	String Content_Title = "";
	String Content_Type = "";
	String Menu = "";
	String Downloaded_playback = "";
	String Tray_title = "";
	String position_With_The_Tray = "";
	String Genre = "";
	String Content_Duration = "";
	String Content_Upload_Date = "";
	String Action = "";
	String Content_Series = "";
	String SBU = "";
	String playHeadPosition_secs = "";
	String playheadPosition_percent = "";

	@Test(dataProvider = "getData")
	public void MixPanelDownloadScenarios(Hashtable<String, String> data) throws Exception {
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test to verify " + testName + VootConstants.DEVICE_NAME);
		// Open charles application
		test.log(LogStatus.INFO,
				"Validating Validating Player Action Events for Video Player - Episode Content - 9 Scenarios");

		// Launch App

		launchApp();
		String email = data.get("Email");
		String password = data.get("Password");
		HomePageV2 homepage = new HomePageV2(driver, test);
		DownloadsPageV2 downloads = new DownloadsPageV2(driver, test);
		MixPanelPageVK mppage = new MixPanelPageVK(driver, test);
		PlayPageV2 playerpage = new PlayPageV2(driver, test);
		KidsPlayerPageV2 kidsplayerpage = new KidsPlayerPageV2(driver, test);
		// driver.resetApp();
		HomePageV2.logout();

		Utilities.saveCharles(testName);

		HomePageV2.login(email, password);

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

			randAPI = "https://api.vootkids.com/app/ks/v1/allKidsEpisodes.json?pageSize=8&pageIndex=0&refSeriesId="
					+ seriesId;
			resp = Utilities.requestUtility(randAPI);
			mediaId = resp.jsonPath().getString("assets.items[0].mId");
			// Content_Type = resp.jsonPath().getString("assets.items[0].contentType");
			// String content[] = Content_Type.split("\\ ");
			Content_Type = "Video";
			Content_Title = resp.jsonPath().getString("assets.items[0].title");
			mediaType = Integer.toString(resp.jsonPath().getInt("assets.items[0].mediaType"));
			Content_Duration = Integer.toString(resp.jsonPath().getInt("assets.items[0].duration"));
			Genre = resp.jsonPath().getString("assets.items[0].genre");
			SBU = resp.jsonPath().getString("assets.items[0].sbu");
			Tray_title = "Shows Poster";
			playheadPosition_percent = "random";
			playHeadPosition_secs = "random";
			position_With_The_Tray = "0";
			Downloaded_playback = "false";
			Menu = "Watch";
			randAPI = "https://api.vootkids.com/app/playback/v1/playback.json?mediaTypeId=" + mediaType + "&mediaId="
					+ mediaId;
			resp = Utilities.requestUtility(randAPI);
			String startDate = Long.toString(resp.jsonPath().getLong("assets[0].assets[0].items[0].startDate"));
			Content_Upload_Date = Utilities.epochtimeConvertion(startDate);
			Content_Series = resp.jsonPath().getString("assets[0].assets[0].items[0].refSeriesTitle");
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch response of Episode from API");
		}

		test.log(LogStatus.INFO, "Navigating to Movie: " + showTitle);
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

		Utilities.verticalSwipe(driver, homepage.playAudiobookbutton);
		if (Utilities.explicitWaitClickableNew(driver, homepage.playAudiobookbutton, 20))
			homepage.playAudiobookbutton.click();
		else {
			Utilities.saveCharles(testName);
			BasePageV2.reportFail("Unable to play Video book content");
			BasePageV2.takeScreenshot();
		}

		Thread.sleep(20000);
		// Verify UI of Video player
		// Verify UI of Video player controls after tapping on the player screen

		homepage.verifyAndProgressBar();
		kidsplayerpage.pauseVideo();
		Thread.sleep(30000);
		kidsplayerpage.playVideo();
		Thread.sleep(30000);
		homepage.verifyAndProgressBar();
		kidsplayerpage.pauseVideo();

		if (Utilities.explicitWaitVisible(driver, homepage.audiobackward, 10)) {
			test.log(LogStatus.INFO, "Clicking on Video 'Rewind' button to Validate 'Rewind 10S' Action");
			homepage.audiobackward.click();
			Thread.sleep(30000);
			homepage.verifyAndProgressBar();
			kidsplayerpage.playVideo();
			kidsplayerpage.pauseVideo();
			if (Utilities.explicitWaitVisible(driver, homepage.audioForward, 10)) {
				test.log(LogStatus.INFO, "Clicking on Video 'Forward' button to Validate 'Forward 10S' Action");

				homepage.audioForward.click();
				Thread.sleep(30000);
				homepage.verifyAndProgressBar();
				kidsplayerpage.playVideo();
				kidsplayerpage.pauseVideo();

				if (Utilities.explicitWaitVisible(driver, kidsplayerpage.playerFavoriteButton, 10)) {

					test.log(LogStatus.INFO, "Clicking on Video 'Favourite' Icon to Validate 'Favourited' Action");
					kidsplayerpage.playerFavoriteButton.click();
					if (Utilities.explicitWaitVisible(driver, mppage.okButton, 3))
						mppage.okButton.click();
					else {
					}

					Thread.sleep(30000);
					test.log(LogStatus.INFO, "Clicking on Video 'Unfavourite' Icon to Validate 'Unfavourited' Action");
					kidsplayerpage.playerFavoriteButton.click();

					if (Utilities.explicitWaitVisible(driver, mppage.okButton, 3))
						mppage.okButton.click();
					else {
					}
					Thread.sleep(30000);
				}

				else {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Video 'Favourite icon' button is not displayed");
					BasePageV2.takeScreenshot();
				}

				if (Utilities.explicitWaitVisible(driver, homepage.audioplaylistExpand, 10)) {
					test.log(LogStatus.INFO,
							"Clicking on Video 'Up Arrow' Icon to Validate 'Below Arrow Clicked' Action");
					homepage.audioplaylistExpand.click();
					Thread.sleep(30000);
					if (Utilities.explicitWaitVisible(driver, homepage.audioplaylistCollapse, 10)) {
						homepage.audioplaylistCollapse.click();

						if (Utilities.explicitWaitClickable(driver, kidsplayerpage.playerSettings, 20)) {
							test.log(LogStatus.INFO,
									"Changing Video quality to Validate 'Video Quality Changed to' Action");
							kidsplayerpage.playerSettings.click();
							Thread.sleep(2000);

							// Selecting stream quality
							if (Utilities.explicitWaitVisibleNew(driver, mppage.streamQuality, 20)) {
								mppage.streamQuality.click();

								if (Utilities.explicitWaitVisibleNew(driver, playerpage.HighQuality, 20)) {
									playerpage.HighQuality.click();
									if (Utilities.explicitWaitVisibleNew(driver, mppage.okButton, 3))
										mppage.okButton.click();
									else {
									}
									Thread.sleep(30000);
									// driver.navigate().back();
								}

								else {
									Utilities.saveandcloseCharles(testName);
									BasePageV2.reportFail("High Quality Option was not displayed");
								}

							} else {
								Utilities.saveandcloseCharles(testName);
								BasePageV2.reportFail("Quality Options List button was not displayed");
							}
							// Selecting Language
							if (Utilities.explicitWaitVisibleNew(driver, mppage.selectLanguage, 20)) {
								mppage.selectLanguage.click();
								if (Utilities.explicitWaitVisibleNew(driver, mppage.hindiLanguage, 20)) {
									mppage.hindiLanguage.click();
									if (Utilities.explicitWaitVisibleNew(driver, mppage.okButton, 3))
										mppage.okButton.click();
									else {
									}
									Thread.sleep(60000);
									driver.navigate().back();
									driver.navigate().back();
									driver.navigate().back();
									Utilities.verticalSwipeDown(driver, homepage.profilepic);
									HomePageV2.tabClick("Listen");
									Utilities.saveandcloseCharles(testName);
									MixPanelPageVK.mixpnlEventsfetchLoggedInuserVideoplayerActionsEvent(email,
											EventName);
								} else {
									Utilities.saveandcloseCharles(testName);
									BasePageV2.reportFail(
											"Languages Failed to display under Select Language option in Player settings");
								}
							} else {
								Utilities.saveandcloseCharles(testName);
								BasePageV2.reportFail("Select Language dropdown was not displayed in player settings");
							}

						} else {
							Utilities.saveandcloseCharles(testName);
							BasePageV2.reportFail("Player Settings option button was not displayed");
						}
					} else {
						Utilities.saveandcloseCharles(testName);
						BasePageV2.reportFail("Video 'Play list Collapsor (Down Arrow)' button is not displayed");
					}
				}

				else {
					Utilities.saveandcloseCharles(testName);
					BasePageV2.reportFail("Video 'Play list expander (Up Arrow)' button is not displayed");

				}

			}

			else {
				Utilities.saveandcloseCharles(testName);
				test.log(LogStatus.FAIL, "Video 'Forward' button is not displayed");
				BasePageV2.takeScreenshot();
			}
		}

		else {
			Utilities.saveandcloseCharles(testName);
			test.log(LogStatus.FAIL, "Video 'Rewind' button is not displayed");
			BasePageV2.takeScreenshot();
		}

		if (MixPanelPageVK.rewind == true) {
			Action = "Rewind 10s";
			valuesMaprewind.clear();
			Resultrewind.clear();
			valuesMaprewind.put("Action", Action);
			valuesMaprewind.put("Content Type", Content_Type);
			valuesMaprewind.put("Downloaded Playback?", Downloaded_playback);
			valuesMaprewind.put("Menu", Menu);
			valuesMaprewind.put("Tray Title", Tray_title);
			valuesMaprewind.put("Position Within the Tray", position_With_The_Tray);
			valuesMaprewind.put("Content Title", Content_Title);
			valuesMaprewind.put("Content Series", Content_Series);
			valuesMaprewind.put("Genre", Genre);
			valuesMaprewind.put("SBU", SBU);
			valuesMaprewind.put("Content Duration", Content_Duration);
			valuesMaprewind.put("Content Upload Date", Content_Upload_Date);
			valuesMaprewind.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMaprewind.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMaprewind.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMaprewind.get(key);
				String mpVal = MixPanelPageVK.mixevntsrewind.get(key);

				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					try {
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Rewind 10S' Actions of 'Player Actions' Event");
							Resultrewind.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							Resultrewind.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						Resultrewind.add("Fail");
					}

				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					try {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Rewind 10S' Actions of 'Player Actions' Event");
							Resultrewind.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultrewind.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultrewind.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Rewind 10S' Actions of 'Player Actions' Event");
							Resultrewind.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultrewind.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultrewind.add("Fail");
					}

				}

			}

			if (Resultrewind.contains("Pass") && !Resultrewind.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'Rewind 10S' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'Rewind 10S' Action of 'Player Action' event was Fail");
		} else
			test.log(LogStatus.FAIL, "'Rewind 10S' Action was not displayed");

		if (MixPanelPageVK.forward == true) {
			Action = "Forward 10s";
			valuesMapforward.clear();
			Resultforward.clear();
			valuesMapforward.put("Action", Action);
			valuesMapforward.put("Content Type", Content_Type);
			valuesMapforward.put("Downloaded Playback?", Downloaded_playback);
			valuesMapforward.put("Menu", Menu);
			valuesMapforward.put("Tray Title", Tray_title);
			valuesMapforward.put("Position Within the Tray", position_With_The_Tray);
			valuesMapforward.put("Content Title", Content_Title);
			valuesMapforward.put("Content Series", Content_Series);
			valuesMapforward.put("Genre", Genre);
			valuesMapforward.put("SBU", SBU);
			valuesMapforward.put("Content Duration", Content_Duration);
			valuesMapforward.put("Content Upload Date", Content_Upload_Date);
			valuesMapforward.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapforward.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapforward.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapforward.get(key);
				String mpVal = MixPanelPageVK.mixevntsforward.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Forward 10S' Action of 'Player Actions' Event");
						Resultforward.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Forward 10S' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						Resultforward.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Forward 10S' Actions of 'Player Actions' Event");
						Resultforward.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Forward 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultforward.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Forward 10S' Actions of 'Player Actions' Event");
							Resultforward.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Forward 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultforward.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Forward 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultforward.add("Fail");
					}

				}

			}

			if (Resultforward.contains("Pass") && !Resultforward.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'Forward 10S' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'Forward 10S' Action of 'Player Action' event was Fail");
		} else
			test.log(LogStatus.FAIL, "'Forward 10S' Action was not displayed");

		if (MixPanelPageVK.pause == true) {
			Action = "playbackPaused";
			valuesMappause.clear();
			Resultpause.clear();
			valuesMappause.put("Action", Action);
			valuesMappause.put("Content Type", Content_Type);
			valuesMappause.put("Downloaded Playback?", Downloaded_playback);
			valuesMappause.put("Menu", Menu);
			valuesMappause.put("Tray Title", Tray_title);
			valuesMappause.put("Position Within the Tray", position_With_The_Tray);
			valuesMappause.put("Content Title", Content_Title);
			valuesMappause.put("Content Series", Content_Series);
			valuesMappause.put("Genre", Genre);
			valuesMappause.put("SBU", SBU);
			valuesMappause.put("Content Duration", Content_Duration);
			valuesMappause.put("Content Upload Date", Content_Upload_Date);
			valuesMappause.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMappause.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMappause.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMappause.get(key);
				String mpVal = MixPanelPageVK.mixevntspause.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'playbackPaused' Action of 'Player Actions' Event");
						Resultpause.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'playbackPaused' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						Resultpause.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'playbackPaused' Actions of 'Player Actions' Event");
						Resultpause.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'playbackPaused' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultpause.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'playbackPaused' Actions of 'Player Actions' Event");
							Resultpause.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackPaused' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultpause.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'playbackPaused' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultpause.add("Fail");
					}

				}

			}

			if (Resultpause.contains("Pass") && !Resultpause.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'playbackPaused' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'playbackPaused' Action of 'Player Action' event was Fail");
		} else
			test.log(LogStatus.FAIL, "'playbackPaused' Action was not displayed");

		if (MixPanelPageVK.resume == true) {
			Action = "playbackResumed";
			valuesMapresume.clear();
			Resultresume.clear();
			valuesMapresume.put("Action", Action);
			valuesMapresume.put("Content Type", Content_Type);
			valuesMapresume.put("Downloaded Playback?", Downloaded_playback);
			valuesMapresume.put("Menu", Menu);
			valuesMapresume.put("Tray Title", Tray_title);
			valuesMapresume.put("Position Within the Tray", position_With_The_Tray);
			valuesMapresume.put("Content Title", Content_Title);
			valuesMapresume.put("Content Series", Content_Series);
			valuesMapresume.put("Genre", Genre);
			valuesMapresume.put("SBU", SBU);
			valuesMapresume.put("Content Duration", Content_Duration);
			valuesMapresume.put("Content Upload Date", Content_Upload_Date);
			valuesMapresume.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapresume.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapresume.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapresume.get(key);
				String mpVal = MixPanelPageVK.mixevntsresume.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'playbackResumed' Action of 'Player Actions' Event");
						Resultresume.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'playbackResumed' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						Resultresume.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'playbackResumed' Actions of 'Player Actions' Event");
						Resultresume.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'playbackResumed' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultresume.add("Fail");
					}

				} else {

					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'playbackResumed' Actions of 'Player Actions' Event");
							Resultresume.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackResumed' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultresume.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'playbackResumed' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultresume.add("Fail");
					}

				}

			}

			if (Resultresume.contains("Pass") && !Resultresume.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'playbackResumed' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'playbackResumed' Action of 'Player Action' event was Fail");
		} else
			test.log(LogStatus.FAIL, "'playbackResumed' Action was not displayed");

		if (MixPanelPageVK.changelanguage == true) {
			Action = "Language Changed to Hindi";
			valuesMaplanguageChanged.clear();
			ResultlanguageChanged.clear();
			valuesMaplanguageChanged.put("Action", Action);
			valuesMaplanguageChanged.put("Content Type", Content_Type);
			valuesMaplanguageChanged.put("Downloaded Playback?", Downloaded_playback);
			valuesMaplanguageChanged.put("Menu", Menu);
			valuesMaplanguageChanged.put("Tray Title", Tray_title);
			valuesMaplanguageChanged.put("Position Within the Tray", position_With_The_Tray);
			valuesMaplanguageChanged.put("Content Title", Content_Title);
			valuesMaplanguageChanged.put("Content Series", Content_Series);
			valuesMaplanguageChanged.put("Genre", Genre);
			valuesMaplanguageChanged.put("SBU", SBU);
			valuesMaplanguageChanged.put("Content Duration", Content_Duration);
			valuesMaplanguageChanged.put("Content Upload Date", Content_Upload_Date);
			valuesMaplanguageChanged.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMaplanguageChanged.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMaplanguageChanged.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMaplanguageChanged.get(key);
				String mpVal = MixPanelPageVK.mixevntschangelanguage.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Language Changed' Action of 'Player Actions' Event");
						ResultlanguageChanged.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Language Changed' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						ResultlanguageChanged.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Language Changed' Actions of 'Player Actions' Event");
						ResultlanguageChanged.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Language Changed to' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultlanguageChanged.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Language Changed' Actions of 'Player Actions' Event");
							ResultlanguageChanged.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Language Changed' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultlanguageChanged.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Language Changed to' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultlanguageChanged.add("Fail");
					}

				}

			}

			if (ResultlanguageChanged.contains("Pass") && !ResultlanguageChanged.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'Language Changed' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'Language Changed' Action of 'Player Action' event was Fail");

		} else
			test.log(LogStatus.FAIL, "'Language Changed to' Action was not displayed");

		if (MixPanelPageVK.favourite == true) {
			Action = "Favorited";
			valuesMapfavourite.clear();
			Resultfavourite.clear();
			valuesMapfavourite.put("Action", Action);
			valuesMapfavourite.put("Content Type", Content_Type);
			valuesMapfavourite.put("Downloaded Playback?", Downloaded_playback);
			valuesMapfavourite.put("Menu", Menu);
			valuesMapfavourite.put("Tray Title", Tray_title);
			valuesMapfavourite.put("Position Within the Tray", position_With_The_Tray);
			valuesMapfavourite.put("Content Title", Content_Title);
			valuesMapfavourite.put("Content Series", Content_Series);
			valuesMapfavourite.put("Genre", Genre);
			valuesMapfavourite.put("SBU", SBU);
			valuesMapfavourite.put("Content Duration", Content_Duration);
			valuesMapfavourite.put("Content Upload Date", Content_Upload_Date);
			valuesMapfavourite.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapfavourite.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapfavourite.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapfavourite.get(key);
				String mpVal = MixPanelPageVK.mixevntsfavourite.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Favorited' Action of 'Player Actions' Event");
						Resultfavourite.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Favorited' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						Resultfavourite.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Favorited' Actions of 'Player Actions' Event");
						Resultfavourite.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Favorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultfavourite.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Favorited' Actions of 'Player Actions' Event");
							Resultfavourite.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Favorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultfavourite.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Favorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultfavourite.add("Fail");
					}

				}

			}

			if (Resultfavourite.contains("Pass") && !Resultfavourite.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'Favorited' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'Favorited' Action of 'Player Action' event was Fail");

		} else
			test.log(LogStatus.FAIL, "'Favorited' Action was not displayed");

		if (MixPanelPageVK.unfavourite == true) {
			Action = "Unfavorited";
			valuesMapunfavourite.clear();
			Resultunfavourite.clear();
			valuesMapunfavourite.put("Action", Action);
			valuesMapunfavourite.put("Content Type", Content_Type);
			valuesMapunfavourite.put("Downloaded Playback?", Downloaded_playback);
			valuesMapunfavourite.put("Menu", Menu);
			valuesMapunfavourite.put("Tray Title", Tray_title);
			valuesMapunfavourite.put("Position Within the Tray", position_With_The_Tray);
			valuesMapunfavourite.put("Content Title", Content_Title);
			valuesMapunfavourite.put("Content Series", Content_Series);
			valuesMapunfavourite.put("Genre", Genre);
			valuesMapunfavourite.put("SBU", SBU);
			valuesMapunfavourite.put("Content Duration", Content_Duration);
			valuesMapunfavourite.put("Content Upload Date", Content_Upload_Date);
			valuesMapunfavourite.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapunfavourite.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapunfavourite.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapunfavourite.get(key);
				String mpVal = MixPanelPageVK.mixevntsunfavourite.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Unfavorited' Action of 'Player Actions' Event");
						Resultunfavourite.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Unfavorited' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						Resultunfavourite.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Unfavorited' Actions of 'Player Actions' Event");
						Resultunfavourite.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Unfavorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultunfavourite.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Unfavorited' Actions of 'Player Actions' Event");
							Resultunfavourite.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Unfavorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultunfavourite.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Unfavorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultunfavourite.add("Fail");
					}

				}

			}

			if (Resultunfavourite.contains("Pass") && !Resultunfavourite.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'Unfavorited' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'Unfavorited' Action of 'Player Action' event was Fail");
		} else
			test.log(LogStatus.FAIL, "'Unfavorited' Action was not displayed");

		if (MixPanelPageVK.changequality == true) {
			Action = "Video Quality Changed to High";
			valuesMapchangequality.clear();
			ResultchangeQuality.clear();
			valuesMapchangequality.put("Action", Action);
			valuesMapchangequality.put("Content Type", Content_Type);
			valuesMapchangequality.put("Downloaded Playback?", Downloaded_playback);
			valuesMapchangequality.put("Menu", Menu);
			valuesMapchangequality.put("Tray Title", Tray_title);
			valuesMapchangequality.put("Position Within the Tray", position_With_The_Tray);
			valuesMapchangequality.put("Content Title", Content_Title);
			valuesMapchangequality.put("Content Series", Content_Series);
			valuesMapchangequality.put("Genre", Genre);
			valuesMapchangequality.put("SBU", SBU);
			valuesMapchangequality.put("Content Duration", Content_Duration);
			valuesMapchangequality.put("Content Upload Date", Content_Upload_Date);
			valuesMapchangequality.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapchangequality.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapchangequality.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapchangequality.get(key);
				String mpVal = MixPanelPageVK.mixevntschangequality.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' for 'Video Quality Changed to High' Action of 'Player Actions' Event");
						ResultchangeQuality.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Video Quality Changed to High' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						ResultchangeQuality.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event");
						ResultchangeQuality.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultchangeQuality.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event");
							ResultchangeQuality.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultchangeQuality.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultchangeQuality.add("Fail");
					}

				}

			}

			if (ResultchangeQuality.contains("Pass") && !ResultchangeQuality.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'Video Quality Changed to High' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL,
						"Verify 'Video Quality Changed to High' Action of 'Player Action' event was Fail");

		} else
			test.log(LogStatus.FAIL, "'Video Quality Changed to High' Action was not displayed");

		if (MixPanelPageVK.upArrow == true) {
			Action = "Below Arrow Clicked";
			valuesMapupArrow.clear();
			ResultupArrow.clear();
			valuesMapupArrow.put("Action", Action);
			valuesMapupArrow.put("Content Type", Content_Type);
			valuesMapupArrow.put("Downloaded Playback?", Downloaded_playback);
			valuesMapupArrow.put("Menu", Menu);
			valuesMapupArrow.put("Tray Title", Tray_title);
			valuesMapupArrow.put("Position Within the Tray", position_With_The_Tray);
			valuesMapupArrow.put("Content Title", Content_Title);
			valuesMapupArrow.put("Content Series", Content_Series);
			valuesMapupArrow.put("Genre", Genre);
			valuesMapupArrow.put("SBU", SBU);
			valuesMapupArrow.put("Content Duration", Content_Duration);
			valuesMapupArrow.put("Content Upload Date", Content_Upload_Date);
			valuesMapupArrow.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapupArrow.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapupArrow.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapupArrow.get(key);
				String mpVal = MixPanelPageVK.mixevntsupArrow.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Below Arrow Clicked' Action of 'Player Actions' Event");
						ResultupArrow.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Below Arrow Clicked' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						ResultupArrow.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event");
						ResultupArrow.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultupArrow.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event");
							ResultupArrow.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultupArrow.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultupArrow.add("Fail");
					}

				}

			}

			if (ResultupArrow.contains("Pass") && !ResultupArrow.contains("Fail"))
				test.log(LogStatus.PASS, "Verified 'Below Arrow Clicked' Action of 'Player Action' event");
			else
				test.log(LogStatus.FAIL, "Verify 'Below Arrow Clicked' Action of 'Player Action' event was Fail");

		} else
			test.log(LogStatus.FAIL, "'Below Arrow Clicked' Action was not displayed");

		// Movie Content Player Action Validation
		test.log(LogStatus.INFO,
				"Validating Validating Player Action Events for Video Player - Movie Content - 9 Scenarios");
		Utilities.openCharles();
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");
		Utilities.saveCharles(testName);
		movieAPI = "https://api.vootkids.com/app/ui/v1/tabs/watch.json";
		resp = Utilities.requestUtility(movieAPI);
		movFlag = false;
		for (int i = 0; i < 50; i++) {
			trayTitle = resp.jsonPath().getString("assets[" + i + "].title");
			if (trayTitle.contains("Movies")) {
				movFlag = true;
				trayTitle = trayTitle.toUpperCase();

				nextPageAPI = resp.jsonPath().getString("assets[" + i + "].nextPageAPI");

				break;
			}
		}

		test.log(LogStatus.INFO, "Movie Tray displayed was: " + trayTitle);

		try {
			nextPageAPI = nextPageAPI.replaceAll("@limit@", "8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			nextPageAPI = nextPageAPI.replaceAll("@offSet@", "0");
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, "Next Page API displayed was: " + nextPageAPI);

		if (movFlag == true) {
			xpath = "//android.widget.TextView[@text='" + trayTitle + "']";
			Utilities.verticalSwipe(driver, xpath);

			try {
				resp = Utilities.requestUtility(nextPageAPI);
				// resp = Utilities.requestUtility(watchPageAPI);
				showTitle = resp.jsonPath().getString("assets.items[0].title");
				mediaId = resp.jsonPath().getString("assets.items[0].mId");
				Content_Type = "Video";
				Content_Title = resp.jsonPath().getString("assets.items[0].title");
				mediaType = Integer.toString(resp.jsonPath().getInt("assets.items[0].mediaType"));
				Content_Duration = Integer.toString(resp.jsonPath().getInt("assets.items[0].duration"));
				Genre = resp.jsonPath().getString("assets.items[0].genre");
				SBU = resp.jsonPath().getString("assets.items[0].sbu");
				Tray_title = "Shows Poster";
				playheadPosition_percent = "random";
				playHeadPosition_secs = "random";
				position_With_The_Tray = "0";
				Downloaded_playback = "false";
				Menu = "Watch";
				randAPI = "https://api.vootkids.com/app/playback/v1/playback.json?mediaTypeId=" + mediaType
						+ "&mediaId=" + mediaId;
				resp = Utilities.requestUtility(randAPI);
				String startDate = Long.toString(resp.jsonPath().getLong("assets[0].assets[0].items[0].startDate"));
				Content_Upload_Date = Utilities.epochtimeConvertion(startDate);
				Content_Series = "null";
			} catch (Exception e) {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Failed to fetch response from Movie API");
			}

			test.log(LogStatus.INFO, "Navigating to Movie: " + showTitle);
			xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
					+ showTitle + "']";
			Utilities.verticalSwipe(driver, xpath);

			try {
				WebElement show = driver.findElement(By.xpath(xpath));
				show.click();
			} catch (Exception e) {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Failed to navigate to Movie: " + showTitle);
			}

			Utilities.verticalSwipe(driver, homepage.playAudiobookbutton);
			if (Utilities.explicitWaitClickableNew(driver, homepage.playAudiobookbutton, 20))
				homepage.playAudiobookbutton.click();
			else {
				Utilities.saveCharles(testName);
				BasePageV2.reportFail("Unable to play Video book content");
				BasePageV2.takeScreenshot();
			}

			Thread.sleep(20000);
			// Verify UI of Video player
			// Verify UI of Video player controls after tapping on the player screen

			homepage.verifyAndProgressBar();
			kidsplayerpage.pauseVideo();
			Thread.sleep(30000);
			kidsplayerpage.playVideo();
			Thread.sleep(30000);
			homepage.verifyAndProgressBar();
			kidsplayerpage.pauseVideo();

			if (Utilities.explicitWaitVisible(driver, homepage.audiobackward, 10)) {
				test.log(LogStatus.INFO, "Clicking on Video 'Rewind' button to Validate 'Rewind 10S' Action");
				homepage.audiobackward.click();
				Thread.sleep(30000);
				homepage.verifyAndProgressBar();
				kidsplayerpage.playVideo();
				kidsplayerpage.pauseVideo();
				if (Utilities.explicitWaitVisible(driver, homepage.audioForward, 10)) {
					test.log(LogStatus.INFO, "Clicking on Video 'Forward' button to Validate 'Forward 10S' Action");

					homepage.audioForward.click();
					Thread.sleep(30000);
					homepage.verifyAndProgressBar();
					kidsplayerpage.playVideo();
					kidsplayerpage.pauseVideo();

					if (Utilities.explicitWaitVisible(driver, kidsplayerpage.playerFavoriteButton, 10)) {

						test.log(LogStatus.INFO, "Clicking on Video 'Favourite' Icon to Validate 'Favourited' Action");
						kidsplayerpage.playerFavoriteButton.click();
						if (Utilities.explicitWaitVisible(driver, mppage.okButton, 3))
							mppage.okButton.click();
						else {
						}

						Thread.sleep(30000);
						test.log(LogStatus.INFO,
								"Clicking on Video 'Unfavourite' Icon to Validate 'Unfavourited' Action");
						kidsplayerpage.playerFavoriteButton.click();

						if (Utilities.explicitWaitVisible(driver, mppage.okButton, 3))
							mppage.okButton.click();
						else {
						}
						Thread.sleep(30000);
					}

					else {
						Utilities.saveandcloseCharles(testName);
						BasePageV2.reportFail("Video 'Favourite icon' button is not displayed");
						BasePageV2.takeScreenshot();
					}

					if (Utilities.explicitWaitVisible(driver, homepage.audioplaylistExpand, 10)) {
						test.log(LogStatus.INFO,
								"Clicking on Video 'Up Arrow' Icon to Validate 'Below Arrow Clicked' Action");
						homepage.audioplaylistExpand.click();
						Thread.sleep(30000);
						if (Utilities.explicitWaitVisible(driver, homepage.audioplaylistCollapse, 10)) {
							homepage.audioplaylistCollapse.click();

							if (Utilities.explicitWaitClickable(driver, kidsplayerpage.playerSettings, 20)) {
								test.log(LogStatus.INFO,
										"Changing Video quality to Validate 'Video Quality Changed to' Action");
								kidsplayerpage.playerSettings.click();
								Thread.sleep(2000);

								// Selecting stream quality
								if (Utilities.explicitWaitVisibleNew(driver, mppage.streamQuality, 20)) {
									mppage.streamQuality.click();

									if (Utilities.explicitWaitVisibleNew(driver, playerpage.HighQuality, 20)) {
										playerpage.HighQuality.click();
										if (Utilities.explicitWaitVisibleNew(driver, mppage.okButton, 3))
											mppage.okButton.click();
										else {
										}
										Thread.sleep(30000);
										// driver.navigate().back();
									}

									else {
										Utilities.saveandcloseCharles(testName);
										BasePageV2.reportFail("High Quality Option was not displayed");
									}

								} else {
									Utilities.saveandcloseCharles(testName);
									BasePageV2.reportFail("Quality Options List button was not displayed");
								}
								// Selecting Language
								if (Utilities.explicitWaitVisibleNew(driver, mppage.selectLanguage, 20)) {
									mppage.selectLanguage.click();
									if (Utilities.explicitWaitVisibleNew(driver, mppage.hindiLanguage, 20)) {
										mppage.hindiLanguage.click();
										if (Utilities.explicitWaitVisibleNew(driver, mppage.okButton, 3))
											mppage.okButton.click();
										else {
										}
										Thread.sleep(60000);
										driver.navigate().back();
										driver.navigate().back();
										driver.navigate().back();
										Utilities.verticalSwipeDown(driver, homepage.profilepic);
										HomePageV2.tabClick("Read");
										Utilities.saveandcloseCharles(testName);
										MixPanelPageVK.mixpnlEventsfetchLoggedInuserVideoplayerActionsEvent(email,
												EventName);
									} else {
										Utilities.saveandcloseCharles(testName);
										BasePageV2.reportFail(
												"Languages Failed to display under Select Language option in Player settings");
									}
								} else {
									Utilities.saveandcloseCharles(testName);
									BasePageV2.reportFail(
											"Select Language dropdown was not displayed in player settings");
								}

							} else {
								Utilities.saveandcloseCharles(testName);
								BasePageV2.reportFail("Player Settings option button was not displayed");
							}
						} else {
							Utilities.saveandcloseCharles(testName);
							BasePageV2.reportFail("Video 'Play list Collapsor (Down Arrow)' button is not displayed");
						}
					}

					else {
						Utilities.saveandcloseCharles(testName);
						BasePageV2.reportFail("Video 'Play list expander (Up Arrow)' button is not displayed");

					}

				}

				else {
					Utilities.saveandcloseCharles(testName);
					test.log(LogStatus.FAIL, "Video 'Forward' button is not displayed");
					BasePageV2.takeScreenshot();
				}
			}

			else {
				Utilities.saveandcloseCharles(testName);
				test.log(LogStatus.FAIL, "Video 'Rewind' button is not displayed");
				BasePageV2.takeScreenshot();
			}

			if (MixPanelPageVK.rewind == true) {
				Action = "Rewind 10s";
				valuesMaprewind.clear();
				Resultrewind.clear();
				valuesMaprewind.put("Action", Action);
				valuesMaprewind.put("Content Type", Content_Type);
				valuesMaprewind.put("Downloaded Playback?", Downloaded_playback);
				valuesMaprewind.put("Menu", Menu);
				valuesMaprewind.put("Tray Title", Tray_title);
				valuesMaprewind.put("Position Within the Tray", position_With_The_Tray);
				valuesMaprewind.put("Content Title", Content_Title);
				valuesMaprewind.put("Content Series", Content_Series);
				valuesMaprewind.put("Genre", Genre);
				valuesMaprewind.put("SBU", SBU);
				valuesMaprewind.put("Content Duration", Content_Duration);
				valuesMaprewind.put("Content Upload Date", Content_Upload_Date);
				valuesMaprewind.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMaprewind.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMaprewind.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMaprewind.get(key);
					String mpVal = MixPanelPageVK.mixevntsrewind.get(key);

					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						try {
							if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
									|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
								test.log(LogStatus.INFO,
										"Verified '" + key + "' for 'Rewind 10S' Actions of 'Player Actions' Event");
								Resultrewind.add("Pass");

							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpDuration + " Expected Value --> " + expDuration);
								Resultrewind.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							Resultrewind.add("Fail");
						}

					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						try {
							if (mpVal.length() > 0 && !mpVal.equals("null")) {
								test.log(LogStatus.INFO,
										"Verified '" + key + "' for 'Rewind 10S' Actions of 'Player Actions' Event");
								Resultrewind.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								Resultrewind.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultrewind.add("Fail");
						}

					}  else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO,
										"Verified '" + key + "' for 'Rewind 10S' Actions of 'Player Actions' Event");
								Resultrewind.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								Resultrewind.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Rewind 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultrewind.add("Fail");
						}

					}

				}

				if (Resultrewind.contains("Pass") && !Resultrewind.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'Rewind 10S' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'Rewind 10S' Action of 'Player Action' event was Fail");
			} else
				test.log(LogStatus.FAIL, "'Rewind 10S' Action was not displayed");

			if (MixPanelPageVK.forward == true) {
				Action = "Forward 10s";
				valuesMapforward.clear();
				Resultforward.clear();
				valuesMapforward.put("Action", Action);
				valuesMapforward.put("Content Type", Content_Type);
				valuesMapforward.put("Downloaded Playback?", Downloaded_playback);
				valuesMapforward.put("Menu", Menu);
				valuesMapforward.put("Tray Title", Tray_title);
				valuesMapforward.put("Position Within the Tray", position_With_The_Tray);
				valuesMapforward.put("Content Title", Content_Title);
				valuesMapforward.put("Content Series", Content_Series);
				valuesMapforward.put("Genre", Genre);
				valuesMapforward.put("SBU", SBU);
				valuesMapforward.put("Content Duration", Content_Duration);
				valuesMapforward.put("Content Upload Date", Content_Upload_Date);
				valuesMapforward.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMapforward.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMapforward.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMapforward.get(key);
					String mpVal = MixPanelPageVK.mixevntsforward.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Forward 10S' Action of 'Player Actions' Event");
							Resultforward.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Forward 10S' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							Resultforward.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Forward 10S' Actions of 'Player Actions' Event");
							Resultforward.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Forward 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultforward.add("Fail");
						}

					}  else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO,
										"Verified '" + key + "' for 'Forward 10S' Actions of 'Player Actions' Event");
								Resultforward.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Forward 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								Resultforward.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Forward 10S' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultforward.add("Fail");
						}

					}

				}

				if (Resultforward.contains("Pass") && !Resultforward.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'Forward 10S' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'Forward 10S' Action of 'Player Action' event was Fail");
			} else
				test.log(LogStatus.FAIL, "'Forward 10S' Action was not displayed");

			if (MixPanelPageVK.pause == true) {
				Action = "playbackPaused";
				valuesMappause.clear();
				Resultpause.clear();
				valuesMappause.put("Action", Action);
				valuesMappause.put("Content Type", Content_Type);
				valuesMappause.put("Downloaded Playback?", Downloaded_playback);
				valuesMappause.put("Menu", Menu);
				valuesMappause.put("Tray Title", Tray_title);
				valuesMappause.put("Position Within the Tray", position_With_The_Tray);
				valuesMappause.put("Content Title", Content_Title);
				valuesMappause.put("Content Series", Content_Series);
				valuesMappause.put("Genre", Genre);
				valuesMappause.put("SBU", SBU);
				valuesMappause.put("Content Duration", Content_Duration);
				valuesMappause.put("Content Upload Date", Content_Upload_Date);
				valuesMappause.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMappause.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMappause.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMappause.get(key);
					String mpVal = MixPanelPageVK.mixevntspause.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'playbackPaused' Action of 'Player Actions' Event");
							Resultpause.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackPaused' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							Resultpause.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'playbackPaused' Actions of 'Player Actions' Event");
							Resultpause.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackPaused' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultpause.add("Fail");
						}

					} else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO, "Verified '" + key
										+ "' for 'playbackPaused' Actions of 'Player Actions' Event");
								Resultpause.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'playbackPaused' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								Resultpause.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackPaused' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultpause.add("Fail");
						}

					}

				}

				if (Resultpause.contains("Pass") && !Resultpause.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'playbackPaused' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'playbackPaused' Action of 'Player Action' event was Fail");
			} else
				test.log(LogStatus.FAIL, "'playbackPaused' Action was not displayed");

			if (MixPanelPageVK.resume == true) {
				Action = "playbackResumed";
				valuesMapresume.clear();
				Resultresume.clear();
				valuesMapresume.put("Action", Action);
				valuesMapresume.put("Content Type", Content_Type);
				valuesMapresume.put("Downloaded Playback?", Downloaded_playback);
				valuesMapresume.put("Menu", Menu);
				valuesMapresume.put("Tray Title", Tray_title);
				valuesMapresume.put("Position Within the Tray", position_With_The_Tray);
				valuesMapresume.put("Content Title", Content_Title);
				valuesMapresume.put("Content Series", Content_Series);
				valuesMapresume.put("Genre", Genre);
				valuesMapresume.put("SBU", SBU);
				valuesMapresume.put("Content Duration", Content_Duration);
				valuesMapresume.put("Content Upload Date", Content_Upload_Date);
				valuesMapresume.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMapresume.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMapresume.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMapresume.get(key);
					String mpVal = MixPanelPageVK.mixevntsresume.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'playbackResumed' Action of 'Player Actions' Event");
							Resultresume.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackResumed' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							Resultresume.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'playbackResumed' Actions of 'Player Actions' Event");
							Resultresume.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackResumed' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultresume.add("Fail");
						}

					}  else {

						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO, "Verified '" + key
										+ "' for 'playbackResumed' Actions of 'Player Actions' Event");
								Resultresume.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'playbackResumed' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								Resultresume.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'playbackResumed' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultresume.add("Fail");
						}

					}

				}

				if (Resultresume.contains("Pass") && !Resultresume.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'playbackResumed' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'playbackResumed' Action of 'Player Action' event was Fail");
			} else
				test.log(LogStatus.FAIL, "'playbackResumed' Action was not displayed");

			if (MixPanelPageVK.changelanguage == true) {
				Action = "Language Changed to Hindi";
				valuesMaplanguageChanged.clear();
				ResultlanguageChanged.clear();
				valuesMaplanguageChanged.put("Action", Action);
				valuesMaplanguageChanged.put("Content Type", Content_Type);
				valuesMaplanguageChanged.put("Downloaded Playback?", Downloaded_playback);
				valuesMaplanguageChanged.put("Menu", Menu);
				valuesMaplanguageChanged.put("Tray Title", Tray_title);
				valuesMaplanguageChanged.put("Position Within the Tray", position_With_The_Tray);
				valuesMaplanguageChanged.put("Content Title", Content_Title);
				valuesMaplanguageChanged.put("Content Series", Content_Series);
				valuesMaplanguageChanged.put("Genre", Genre);
				valuesMaplanguageChanged.put("SBU", SBU);
				valuesMaplanguageChanged.put("Content Duration", Content_Duration);
				valuesMaplanguageChanged.put("Content Upload Date", Content_Upload_Date);
				valuesMaplanguageChanged.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMaplanguageChanged.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMaplanguageChanged.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMaplanguageChanged.get(key);
					String mpVal = MixPanelPageVK.mixevntschangelanguage.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Language Changed' Action of 'Player Actions' Event");
							ResultlanguageChanged.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Language Changed' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							ResultlanguageChanged.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Language Changed' Actions of 'Player Actions' Event");
							ResultlanguageChanged.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Language Changed to' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultlanguageChanged.add("Fail");
						}

					}

					

					else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO, "Verified '" + key
										+ "' for 'Language Changed' Actions of 'Player Actions' Event");
								ResultlanguageChanged.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Language Changed' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								ResultlanguageChanged.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Language Changed to' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultlanguageChanged.add("Fail");
						}

					}

				}

				if (ResultlanguageChanged.contains("Pass") && !ResultlanguageChanged.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'Language Changed' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'Language Changed' Action of 'Player Action' event was Fail");

			} else
				test.log(LogStatus.FAIL, "'Language Changed to' Action was not displayed");

			if (MixPanelPageVK.favourite == true) {
				Action = "Favorited";
				valuesMapfavourite.clear();
				Resultfavourite.clear();
				valuesMapfavourite.put("Action", Action);
				valuesMapfavourite.put("Content Type", Content_Type);
				valuesMapfavourite.put("Downloaded Playback?", Downloaded_playback);
				valuesMapfavourite.put("Menu", Menu);
				valuesMapfavourite.put("Tray Title", Tray_title);
				valuesMapfavourite.put("Position Within the Tray", position_With_The_Tray);
				valuesMapfavourite.put("Content Title", Content_Title);
				valuesMapfavourite.put("Content Series", Content_Series);
				valuesMapfavourite.put("Genre", Genre);
				valuesMapfavourite.put("SBU", SBU);
				valuesMapfavourite.put("Content Duration", Content_Duration);
				valuesMapfavourite.put("Content Upload Date", Content_Upload_Date);
				valuesMapfavourite.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMapfavourite.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMapfavourite.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMapfavourite.get(key);
					String mpVal = MixPanelPageVK.mixevntsfavourite.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Favorited' Action of 'Player Actions' Event");
							Resultfavourite.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Favorited' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							Resultfavourite.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Favorited' Actions of 'Player Actions' Event");
							Resultfavourite.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Favorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultfavourite.add("Fail");
						}

					}

					 else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO,
										"Verified '" + key + "' for 'Favorited' Actions of 'Player Actions' Event");
								Resultfavourite.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Favorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								Resultfavourite.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Favorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultfavourite.add("Fail");
						}

					}

				}

				if (Resultfavourite.contains("Pass") && !Resultfavourite.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'Favorited' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'Favorited' Action of 'Player Action' event was Fail");

			} else
				test.log(LogStatus.FAIL, "'Favorited' Action was not displayed");

			if (MixPanelPageVK.unfavourite == true) {
				Action = "Unfavorited";
				valuesMapunfavourite.clear();
				Resultunfavourite.clear();
				valuesMapunfavourite.put("Action", Action);
				valuesMapunfavourite.put("Content Type", Content_Type);
				valuesMapunfavourite.put("Downloaded Playback?", Downloaded_playback);
				valuesMapunfavourite.put("Menu", Menu);
				valuesMapunfavourite.put("Tray Title", Tray_title);
				valuesMapunfavourite.put("Position Within the Tray", position_With_The_Tray);
				valuesMapunfavourite.put("Content Title", Content_Title);
				valuesMapunfavourite.put("Content Series", Content_Series);
				valuesMapunfavourite.put("Genre", Genre);
				valuesMapunfavourite.put("SBU", SBU);
				valuesMapunfavourite.put("Content Duration", Content_Duration);
				valuesMapunfavourite.put("Content Upload Date", Content_Upload_Date);
				valuesMapunfavourite.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMapunfavourite.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMapunfavourite.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMapunfavourite.get(key);
					String mpVal = MixPanelPageVK.mixevntsunfavourite.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Unfavorited' Action of 'Player Actions' Event");
							Resultunfavourite.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Unfavorited' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							Resultunfavourite.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Unfavorited' Actions of 'Player Actions' Event");
							Resultunfavourite.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Unfavorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultunfavourite.add("Fail");
						}

					}  else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO,
										"Verified '" + key + "' for 'Unfavorited' Actions of 'Player Actions' Event");
								Resultunfavourite.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Unfavorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								Resultunfavourite.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Unfavorited' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultunfavourite.add("Fail");
						}

					}

				}

				if (Resultunfavourite.contains("Pass") && !Resultunfavourite.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'Unfavorited' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'Unfavorited' Action of 'Player Action' event was Fail");
			} else
				test.log(LogStatus.FAIL, "'Unfavorited' Action was not displayed");

			if (MixPanelPageVK.changequality == true) {
				Action = "Video Quality Changed to High";
				valuesMapchangequality.clear();
				ResultchangeQuality.clear();
				valuesMapchangequality.put("Action", Action);
				valuesMapchangequality.put("Content Type", Content_Type);
				valuesMapchangequality.put("Downloaded Playback?", Downloaded_playback);
				valuesMapchangequality.put("Menu", Menu);
				valuesMapchangequality.put("Tray Title", Tray_title);
				valuesMapchangequality.put("Position Within the Tray", position_With_The_Tray);
				valuesMapchangequality.put("Content Title", Content_Title);
				valuesMapchangequality.put("Content Series", Content_Series);
				valuesMapchangequality.put("Genre", Genre);
				valuesMapchangequality.put("SBU", SBU);
				valuesMapchangequality.put("Content Duration", Content_Duration);
				valuesMapchangequality.put("Content Upload Date", Content_Upload_Date);
				valuesMapchangequality.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMapchangequality.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMapchangequality.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMapchangequality.get(key);
					String mpVal = MixPanelPageVK.mixevntschangequality.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Video Quality Changed to High' Action of 'Player Actions' Event");
							ResultchangeQuality.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Video Quality Changed to High' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							ResultchangeQuality.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event");
							ResultchangeQuality.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultchangeQuality.add("Fail");
						}

					}  else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO, "Verified '" + key
										+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event");
								ResultchangeQuality.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								ResultchangeQuality.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Video Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultchangeQuality.add("Fail");
						}

					}

				}

				if (ResultchangeQuality.contains("Pass") && !ResultchangeQuality.contains("Fail"))
					test.log(LogStatus.PASS,
							"Verified 'Video Quality Changed to High' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL,
							"Verify 'Video Quality Changed to High' Action of 'Player Action' event was Fail");

			} else
				test.log(LogStatus.FAIL, "'Video Quality Changed to High' Action was not displayed");

			if (MixPanelPageVK.upArrow == true) {
				Action = "Below Arrow Clicked";
				valuesMapupArrow.clear();
				ResultupArrow.clear();
				valuesMapupArrow.put("Action", Action);
				valuesMapupArrow.put("Content Type", Content_Type);
				valuesMapupArrow.put("Downloaded Playback?", Downloaded_playback);
				valuesMapupArrow.put("Menu", Menu);
				valuesMapupArrow.put("Tray Title", Tray_title);
				valuesMapupArrow.put("Position Within the Tray", position_With_The_Tray);
				valuesMapupArrow.put("Content Title", Content_Title);
				valuesMapupArrow.put("Content Series", Content_Series);
				valuesMapupArrow.put("Genre", Genre);
				valuesMapupArrow.put("SBU", SBU);
				valuesMapupArrow.put("Content Duration", Content_Duration);
				valuesMapupArrow.put("Content Upload Date", Content_Upload_Date);
				valuesMapupArrow.put("Playhead Position (sec)", playHeadPosition_secs);
				valuesMapupArrow.put("Playhead Position %", playheadPosition_percent);
				for (Map.Entry<String, String> mpmap : valuesMapupArrow.entrySet()) {
					String key = mpmap.getKey();
					String expVal = valuesMapupArrow.get(key);
					String mpVal = MixPanelPageVK.mixevntsupArrow.get(key);
					if (key.equalsIgnoreCase("Content Duration")) {
						int expDuration = Integer.parseInt(expVal) / 1000;
						int mpDuration = Integer.parseInt(mpVal);
						if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
								|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Below Arrow Clicked' Action of 'Player Actions' Event");
							ResultupArrow.add("Pass");

						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Below Arrow Clicked' Action of 'Player Actions' Event was Fail Actual Value --> "
									+ mpDuration + " Expected Value --> " + expDuration);
							ResultupArrow.add("Fail");
						}
					} else if (key.equalsIgnoreCase("Playhead Position %")
							|| key.equalsIgnoreCase("Playhead Position (sec)")) {
						if (mpVal.length() > 0 && !mpVal.equals("null")) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event");
							ResultupArrow.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultupArrow.add("Fail");
						}

					}

					 else {
						try {
							if (mpVal.contains(expVal)) {
								test.log(LogStatus.INFO, "Verified '" + key
										+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event");
								ResultupArrow.add("Pass");
							} else {
								test.log(LogStatus.FAIL, "Verification of '" + key
										+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event was Fail Actual Value --> "
										+ mpVal + " Expected Value --> " + expVal);
								ResultupArrow.add("Fail");
							}
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultupArrow.add("Fail");
						}

					}

				}

				if (ResultupArrow.contains("Pass") && !ResultupArrow.contains("Fail"))
					test.log(LogStatus.PASS, "Verified 'Below Arrow Clicked' Action of 'Player Action' event");
				else
					test.log(LogStatus.FAIL, "Verify 'Below Arrow Clicked' Action of 'Player Action' event was Fail");

			} else
				test.log(LogStatus.FAIL, "'Below Arrow Clicked' Action was not displayed");
		} else {
			Utilities.saveandcloseCharles(testName);
			test.log(LogStatus.WARNING, "Movies tray was not displayed under Watch Page API Response");
		}
		Runtime.getRuntime().gc();

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
