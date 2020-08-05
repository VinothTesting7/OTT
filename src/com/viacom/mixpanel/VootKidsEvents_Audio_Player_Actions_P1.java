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

public class VootKidsEvents_Audio_Player_Actions_P1 extends BaseTestV2 {

	String testName = "VootKidsEvents_Audio_Player_Actions_P1";

	boolean audioFlag = false;
	boolean audioClickFlag = false;
	String mediaId = "";
	String mediaType = "";

	String EventName = "Player Action";
	// 'Download' Event Maps - Expected

	static Map<String, String> valuesMaprewind = new HashMap<String, String>();
	static Map<String, String> valuesMapforward = new HashMap<String, String>();
	static Map<String, String> valuesMappause = new HashMap<String, String>();
	static Map<String, String> valuesMapresume = new HashMap<String, String>();
	static Map<String, String> valuesMapminimize = new HashMap<String, String>();
	static Map<String, String> valuesMapchangequality = new HashMap<String, String>();
	static Map<String, String> valuesMapfavourite = new HashMap<String, String>();
	static Map<String, String> valuesMapunfavourite = new HashMap<String, String>();
	static Map<String, String> valuesMapupArrow = new HashMap<String, String>();

	ArrayList<String> Resultrewind = new ArrayList<String>();
	ArrayList<String> Resultforward = new ArrayList<String>();
	ArrayList<String> Resultpause = new ArrayList<String>();
	ArrayList<String> Resultresume = new ArrayList<String>();
	ArrayList<String> Resultminimize = new ArrayList<String>();
	ArrayList<String> ResultchangeQuality = new ArrayList<String>();
	ArrayList<String> Resultfavourite = new ArrayList<String>();
	ArrayList<String> Resultunfavourite = new ArrayList<String>();
	ArrayList<String> ResultupArrow = new ArrayList<String>();

	String listenPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/audio.json";
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
		test.log(LogStatus.INFO,
				"Starting the test to verify "+testName + VootConstants.DEVICE_NAME);
		// Open charles application
		test.log(LogStatus.INFO, "Validating Validating Player Action Events for Audio Player - 9 Scenarios");

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

		
		downloads.deleteAllDownloads();
		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepage.profilepic);
		Thread.sleep(10000);

		HomePageV2.tabClick("Listen");
		try {
			resp = Utilities.requestUtility(listenPageAPI);
			showTitle = resp.jsonPath().getString("assets[1].assets[0].items[0].title");

			mediaId = resp.jsonPath().getString("assets[1].assets[0].items[0].mId");
			Content_Type = resp.jsonPath().getString("assets[1].assets[0].items[0].contentType");
			String content[] = Content_Type.split("\\ ");
			Content_Type = content[0].toString();
			Content_Title = resp.jsonPath().getString("assets[1].assets[0].items[0].title");
			mediaType=Integer.toString(resp.jsonPath().getInt("assets[1].assets[0].items[0].mediaType"));
			Content_Duration = Integer.toString(resp.jsonPath().getInt("assets[1].assets[0].items[0].duration"));
			Genre = resp.jsonPath().getString("assets[1].assets[0].items[0].genre");
			SBU = resp.jsonPath().getString("assets[1].assets[0].items[0].sbu");
			Tray_title = "Shows Poster";
			playheadPosition_percent = "random";
			playHeadPosition_secs = "random";
			position_With_The_Tray = "0";
			Downloaded_playback = "false";
			Menu = "Listen";
			randAPI = "https://api.vootkids.com/app/playback/v1/playback.json?mediaTypeId="+mediaType+"&mediaId="+mediaId;
			resp = Utilities.requestUtility(randAPI);
			String startDate = Long.toString(resp.jsonPath().getLong("assets[0].assets[0].items[0].startDate"));
			Content_Upload_Date = Utilities.epochtimeConvertion(startDate);
			Content_Series = resp.jsonPath().getString("assets[0].assets[0].items[0].refSeriesTitle");
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

		Utilities.verticalSwipe(driver, homepage.playAudiobookbutton);
		if (Utilities.explicitWaitClickableNew(driver, homepage.playAudiobookbutton, 20))
			homepage.playAudiobookbutton.click();
		else {
			Utilities.saveCharles(testName);
			BasePageV2.reportFail("Unable to play audio book content");
			BasePageV2.takeScreenshot();
		}

		Thread.sleep(20000);
		// Verify UI of audio player
		// Verify UI of audio player controls after tapping on the player screen

		if (Utilities.explicitWaitVisible(driver, homepage.audioplayPause, 10)) {
			test.log(LogStatus.INFO, "Pausing the content to Validate 'Pause' Action");
			homepage.audioplayPause.click();
			Thread.sleep(30000);
			test.log(LogStatus.INFO, "Playing the content to Validate 'Play' Action");
			homepage.audioplayPause.click();
			Thread.sleep(30000);
			homepage.audioplayPause.click();
			if (Utilities.explicitWaitVisible(driver, homepage.audiobackward, 10)) {
				test.log(LogStatus.INFO, "Clicking on Audio 'Rewind' button to Validate 'Rewind 10S' Action");
				homepage.audiobackward.click();
				Thread.sleep(30000);

				if (Utilities.explicitWaitVisible(driver, homepage.audioForward, 10)) {
					test.log(LogStatus.INFO, "Clicking on Audio 'Forward' button to Validate 'Forward 10S' Action");

					homepage.audioForward.click();
					Thread.sleep(30000);

					if (Utilities.explicitWaitVisible(driver, homepage.audiofavouriteIcon, 10)) {

						test.log(LogStatus.INFO, "Clicking on Audio 'Favourite' Icon to Validate 'Favourited' Action");
						homepage.audiofavouriteIcon.click();
						if (Utilities.explicitWaitVisible(driver, mppage.okButton, 3))
							mppage.okButton.click();
						else {
						}
						Thread.sleep(30000);
						test.log(LogStatus.INFO,
								"Clicking on Audio 'Unfavourite' Icon to Validate 'Unfavourited' Action");
						if (Utilities.explicitWaitVisible(driver, homepage.audiofavouriteIcon, 10)) 
							homepage.audiofavouriteIcon.click();
						else {}
						if (Utilities.explicitWaitVisible(driver, mppage.okButton, 3))
							mppage.okButton.click();
						else {
						}
								
							
						
						Thread.sleep(30000);
					}

					else {
						Utilities.saveandcloseCharles(testName);
						BasePageV2.reportFail("Audio 'Favourite icon' button is not displayed");
						BasePageV2.takeScreenshot();
					}

					if (Utilities.explicitWaitVisible(driver, homepage.audiominiplayerSwitch, 10)) {
						test.log(LogStatus.INFO, "Clicking on Audio 'Minimize' Icon to Validate 'Minimise' Action");
						homepage.audiominiplayerSwitch.click();
						Thread.sleep(30000);
						if (Utilities.explicitWaitVisible(driver, homepage.miniplayerThumbnail, 20)) {
							homepage.miniplayerThumbnail.click();
							if (Utilities.explicitWaitVisible(driver, homepage.audioplaylistExpand, 10)) {
								test.log(LogStatus.INFO,
										"Clicking on Audio 'Up Arrow' Icon to Validate 'Below Arrow Clicked' Action");
								homepage.audioplaylistExpand.click();
								Thread.sleep(30000);
								if (Utilities.explicitWaitVisible(driver, homepage.audioplaylistCollapse, 10)) {
									homepage.audioplaylistCollapse.click();
									
									if (Utilities.explicitWaitClickable(driver, mppage.audioSettingsIcon, 20)) {
										test.log(LogStatus.INFO,
												"Changing Audio quality to Validate 'Audio Quality Changed to' Action");
										mppage.audioSettingsIcon.click();
										
										System.out.println("-------------------------------------------------------------------------------");
										System.out.println("-------------------------------------------------------------------------------");
										System.out.println("-------------------------------------------------------------------------------");
										System.out.println("-------------------------------------------------------------------------------");

										System.out.println("Page source of Audio detail page was: "+driver.getPageSource());
										
										System.out.println("-------------------------------------------------------------------------------");
										System.out.println("-------------------------------------------------------------------------------");
										System.out.println("-------------------------------------------------------------------------------");
										System.out.println("-------------------------------------------------------------------------------");
										
										if (Utilities.explicitWaitVisible(driver, mppage.streamQuality, 20)) {
											mppage.streamQuality.click();

											if (Utilities.explicitWaitVisible(driver, playerpage.HighQuality, 20)) {
												playerpage.HighQuality.click();
												Thread.sleep(30000);
												driver.navigate().back();
												driver.navigate().back();
												driver.navigate().back();
												driver.navigate().back();
												Utilities.verticalSwipeDown(driver, homepage.profilepic);
												HomePageV2.tabClick("Read");
												Utilities.saveandcloseCharles(testName);
												MixPanelPageVK.mixpnlEventsfetchLoggedInuserplayerActionsEvent(email,
														EventName);
											}

											else {
												Utilities.saveandcloseCharles(testName);
												BasePageV2.reportFail("High Quality Option was not displayed");
											}

										} else {
											Utilities.saveandcloseCharles(testName);
											BasePageV2.reportFail("Quality Options List button was not displayed");
										}

									} else {
										Utilities.saveandcloseCharles(testName);
										BasePageV2.reportFail("Player Settings option button was not displayed");
									}
								} else {
									Utilities.saveandcloseCharles(testName);
									BasePageV2.reportFail(
											"Audio 'Play list Collapsor (Down Arrow)' button is not displayed");
								}
							}

							else {
								Utilities.saveandcloseCharles(testName);
								BasePageV2.reportFail("Audio 'Play list expander (Up Arrow)' button is not displayed");

							}

						} else {
						}

					} else {

					}

				}

				else {
					Utilities.saveandcloseCharles(testName);
					test.log(LogStatus.FAIL, "Audio 'Forward' button is not displayed");
					BasePageV2.takeScreenshot();
				}
			}

			else {
				Utilities.saveandcloseCharles(testName);
				test.log(LogStatus.FAIL, "Audio 'Rewind' button is not displayed");
				BasePageV2.takeScreenshot();
			}

		} else {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Unable to Pause Audio");
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

		if (MixPanelPageVK.minimize == true) {
			Action = "Minimise";
			valuesMapminimize.clear();
			Resultminimize.clear();
			valuesMapminimize.put("Action", Action);
			valuesMapminimize.put("Content Type", Content_Type);
			valuesMapminimize.put("Downloaded Playback?", Downloaded_playback);
			valuesMapminimize.put("Menu", Menu);
			valuesMapminimize.put("Tray Title", Tray_title);
			valuesMapminimize.put("Position Within the Tray", position_With_The_Tray);
			valuesMapminimize.put("Content Title", Content_Title);
			valuesMapminimize.put("Content Series", Content_Series);
			valuesMapminimize.put("Genre", Genre);
			valuesMapminimize.put("SBU", SBU);
			valuesMapminimize.put("Content Duration", Content_Duration);
			valuesMapminimize.put("Content Upload Date", Content_Upload_Date);
			valuesMapminimize.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapminimize.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapminimize.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapminimize.get(key);
				String mpVal = MixPanelPageVK.mixevntsminimize.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Minimise' Action of 'Player Actions' Event");
						Resultminimize.add("Pass");

					} else {
						test.log(LogStatus.FAIL,
								"Verification of '" + key
										+ "' for 'Minimise' Action of 'Player Actions' Event was Fail Actual Value --> "
										+ mpDuration + " Expected Value --> " + expDuration);
						Resultminimize.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Minimise' Actions of 'Player Actions' Event");
						Resultminimize.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Minimise' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultminimize.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Minimise' Actions of 'Player Actions' Event");
							Resultminimize.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Minimise' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultminimize.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Minimise' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultminimize.add("Fail");
					}
					
				}

			}
			
			if (Resultminimize.contains("Pass") && !Resultminimize.contains("Fail")) 
				test.log(LogStatus.PASS, "Verified 'Minimise' Action of 'Player Action' event");
			 else 
				test.log(LogStatus.FAIL, "Verify 'Minimise' Action of 'Player Action' event was Fail");
			
		} else
			test.log(LogStatus.FAIL, "'Minimise' Action was not displayed");

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
			Action = "Audio Quality Changed to High";
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
								+ "' for 'Audio Quality Changed to High' Action of 'Player Actions' Event");
						ResultchangeQuality.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Audio Quality Changed to High' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						ResultchangeQuality.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event");
						ResultchangeQuality.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultchangeQuality.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event");
							ResultchangeQuality.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultchangeQuality.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultchangeQuality.add("Fail");
					}
					
				}

			}
			
			if (ResultchangeQuality.contains("Pass") && !ResultchangeQuality.contains("Fail")) 
				test.log(LogStatus.PASS, "Verified 'Audio Quality Changed to High' Action of 'Player Action' event");
			 else 
				test.log(LogStatus.FAIL, "Verify 'Audio Quality Changed to High' Action of 'Player Action' event was Fail");
			
		} else
			test.log(LogStatus.FAIL, "'Audio Quality Changed to High' Action was not displayed");

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
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event");
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

		if (MixPanelPageVK.minimize == true) {
			Action = "Minimise";
			valuesMapminimize.clear();
			Resultminimize.clear();
			valuesMapminimize.put("Action", Action);
			valuesMapminimize.put("Content Type", Content_Type);
			valuesMapminimize.put("Downloaded Playback?", Downloaded_playback);
			valuesMapminimize.put("Menu", Menu);
			valuesMapminimize.put("Tray Title", Tray_title);
			valuesMapminimize.put("Position Within the Tray", position_With_The_Tray);
			valuesMapminimize.put("Content Title", Content_Title);
			valuesMapminimize.put("Content Series", Content_Series);
			valuesMapminimize.put("Genre", Genre);
			valuesMapminimize.put("SBU", SBU);
			valuesMapminimize.put("Content Duration", Content_Duration);
			valuesMapminimize.put("Content Upload Date", Content_Upload_Date);
			valuesMapminimize.put("Playhead Position (sec)", playHeadPosition_secs);
			valuesMapminimize.put("Playhead Position %", playheadPosition_percent);
			for (Map.Entry<String, String> mpmap : valuesMapminimize.entrySet()) {
				String key = mpmap.getKey();
				String expVal = valuesMapminimize.get(key);
				String mpVal = MixPanelPageVK.mixevntsminimize.get(key);
				if (key.equalsIgnoreCase("Content Duration")) {
					int expDuration = Integer.parseInt(expVal) / 1000;
					int mpDuration = Integer.parseInt(mpVal);
					if (expDuration == mpDuration || (expDuration > mpDuration && expDuration <= mpDuration + 5)
							|| (expDuration <= mpDuration && expDuration >= mpDuration - 5)) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Minimise' Action of 'Player Actions' Event");
						Resultminimize.add("Pass");

					} else {
						test.log(LogStatus.FAIL,
								"Verification of '" + key
										+ "' for 'Minimise' Action of 'Player Actions' Event was Fail Actual Value --> "
										+ mpDuration + " Expected Value --> " + expDuration);
						Resultminimize.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO,
								"Verified '" + key + "' for 'Minimise' Actions of 'Player Actions' Event");
						Resultminimize.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Minimise' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultminimize.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Minimise' Actions of 'Player Actions' Event");
							Resultminimize.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Minimise' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							Resultminimize.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Minimise' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						Resultminimize.add("Fail");
					}
					
				}

			}
			
			if (Resultminimize.contains("Pass") && !Resultminimize.contains("Fail")) 
				test.log(LogStatus.PASS, "Verified 'Minimise' Action of 'Player Action' event");
			 else 
				test.log(LogStatus.FAIL, "Verify 'Minimise' Action of 'Player Action' event was Fail");
			
		} else
			test.log(LogStatus.FAIL, "'Minimise' Action was not displayed");

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
			Action = "Audio Quality Changed to High";
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
								+ "' for 'Audio Quality Changed to High' Action of 'Player Actions' Event");
						ResultchangeQuality.add("Pass");

					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Audio Quality Changed to High' Action of 'Player Actions' Event was Fail Actual Value --> "
								+ mpDuration + " Expected Value --> " + expDuration);
						ResultchangeQuality.add("Fail");
					}
				} else if (key.equalsIgnoreCase("Playhead Position %")
						|| key.equalsIgnoreCase("Playhead Position (sec)")) {
					if (mpVal.length() > 0 && !mpVal.equals("null")) {
						test.log(LogStatus.INFO, "Verified '" + key
								+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event");
						ResultchangeQuality.add("Pass");
					} else {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultchangeQuality.add("Fail");
					}

				} else {
					try {
						if (mpVal.contains(expVal)) {
							test.log(LogStatus.INFO, "Verified '" + key
									+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event");
							ResultchangeQuality.add("Pass");
						} else {
							test.log(LogStatus.FAIL, "Verification of '" + key
									+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
									+ mpVal + " Expected Value --> " + expVal);
							ResultchangeQuality.add("Fail");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verification of '" + key
								+ "' for 'Audio Quality Changed to High' Actions of 'Player Actions' Event was Fail Actual Value --> "
								+ mpVal + " Expected Value --> " + expVal);
						ResultchangeQuality.add("Fail");
					}
					
				}

			}
			
			if (ResultchangeQuality.contains("Pass") && !ResultchangeQuality.contains("Fail")) 
				test.log(LogStatus.PASS, "Verified 'Audio Quality Changed to High' Action of 'Player Action' event");
			 else 
				test.log(LogStatus.FAIL, "Verify 'Audio Quality Changed to High' Action of 'Player Action' event was Fail");
			
		} else
			test.log(LogStatus.FAIL, "'Audio Quality Changed to High' Action was not displayed");

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
							test.log(LogStatus.INFO,
									"Verified '" + key + "' for 'Below Arrow Clicked' Actions of 'Player Actions' Event");
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

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
