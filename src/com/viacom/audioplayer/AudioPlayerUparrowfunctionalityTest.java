package com.viacom.audioplayer;

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
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class AudioPlayerUparrowfunctionalityTest extends BaseTestV2 {
	String testName = "AudioPlayerUparrowfunctionalityTest";
	String testCase = "'Verify the Up arrow functionality'";
	String testCase1 = "'Verify metadata for the cards present under Related tray'";
	String testCase2 = "'Verify the metadata displayed for cards present under Daily Picks section'";
	String testCase3 = "'Verify the displayed screen post completion of current audio playback'";
	String contentTitle = "";
	String testCase4 = "'Verify 'Listen Again' button functionality in 'You Might Also Like' screen'";
	String testCase5 = "'Verify 'Close' button functionality in 'You Might Also Like' screen'";
	String testCase6 = "'Verify the UI of Related section (Audio player)";
	String testCase7 = "'Verify the UI of Daily picks section (Audio player)";
	String testCase10 = "Verify scroll functionality for the cards displayed under 'Audio' content tray";

	String testCase8 = "Audio playback should pause and the button state should change to Play";
	String testCase9 = "The paused audio should resume it`s playback when user taps on the play button and also the pause button should switch to play button";

	String audiobook = "";

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void inlineAudioFunction(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest(testName);
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

		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5, testCase5);

		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6, testCase6);

		int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno7, testCase7);

		int rowno8 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno8, testCase8);

		int rowno9 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno9, testCase9);

		int rowno10 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno10, testCase10);

		/*
		 * int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		 * xls.setCellData("Smoke_Results", "Testcase Name", rowno1, testCase1);
		 * 
		 * int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		 * xls.setCellData("Smoke_Results", "Testcase Name", rowno2, testCase2);
		 */
		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

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

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 2)) {
			homepagev2.playAudiobookbutton.click();
		} else
			BasePageV2.reportFail("Play button is not displayed in audio detail page");

		try {
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 2))
				contentTitle = homepagev2.audioTitle.getText();
			else
				BasePageV2.reportFail("Audio Title Not found");

		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 2)) {
			test.log(LogStatus.INFO,
					" 'Audio playlist expander (up arrow)' switch button is displayed and clicking on it");
			// BasePageV2.takeScreenshot();
			homepagev2.audioplaylistExpand.click();
		} else
			BasePageV2.reportFail(" 'Audio playlist expander (up arrow)' switch button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistCollapse, 2)) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistCollapse, 2)
					&& Utilities.explicitWaitVisible(driver, homepagev2.related, 2)
					&& Utilities.explicitWaitVisible(driver, homepagev2.morefromauthor, 2)
					&& Utilities.explicitWaitVisible(driver, homepagev2.dailypicks, 2)) {
				test.log(LogStatus.INFO, "'Audio playlist collapser (Down arrow)' is displayed");
				test.log(LogStatus.INFO, "'Related tab' is displayed");
				test.log(LogStatus.INFO, "'Daily pics tab' is displayed");
				test.log(LogStatus.INFO, "'More From Author tab' is displayed");
				// BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is passed");
				homepagev2.smokeresults(testCase, rowno, "Pass");
			} else
				BasePageV2.reportFail("Tabs not displayed according to the design when tapping on 'Up Arrow' button");

		} else
			BasePageV2.reportFail(" 'Audio playlist collapser (Down arrow)' switch button is not displayed");

		if (Utilities.explicitWaitClickable(driver, homepagev2.related, 5))
			homepagev2.related.click();
		else
			BasePageV2.reportFail("Related tab not displayed");

		String url = "http://api.vootkids.com/app/recommendation/v1/getRecommendations.json?useCaseId=UCAPPEAUDIO&method=audioRelated&uId=471a76d3bf1241b787895f0828da7269&profileId=5b581c196d8d1912c0c719be&limit=10&offSet=0";
		Response rskids = Utilities.requestUtility(url);
		int totalCards = rskids.jsonPath().get("assets.totalItems");
		test.log(LogStatus.INFO, "Total Number of contents displayed under related tab is: " + totalCards);

		List<String> cardData1 = new ArrayList<String>();

		ArrayList<String> audioTitle = new ArrayList<String>();
		ArrayList<String> audioAuthor = new ArrayList<String>();
		ArrayList<String> audioTitleapi = new ArrayList<String>();
		ArrayList<String> audioAuthorapi = new ArrayList<String>();
		next: for (int i = 0; i < totalCards; i++) {
			try {

				Utilities.verticalSwipe(driver);
				String songAuthor = rskids.jsonPath().get("assets.items[" + i + "].author");
				String songTitle = rskids.jsonPath().get("assets.items[" + i + "].title");
				audioTitleapi.add(songTitle.trim());
				audioAuthorapi.add(songAuthor.trim());
				WebElement title = driver.findElement(By.xpath(
						"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='" + i
								+ "']//android.widget.TextView[@text='" + songTitle + "']"));
				WebElement author = driver.findElement(By.xpath(
						"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='" + i
								+ "']//android.widget.TextView[@text='" + songAuthor + "']"));

				String normalTitle = title.getText().trim();
				String normalauthor = author.getText().trim();
				audioTitle.add(normalTitle.trim());
				audioAuthor.add(normalauthor.trim());

				test.log(LogStatus.INFO, "Content Title of " + i + "st content is displayed as: " + normalTitle);
				test.log(LogStatus.INFO, "Content Author of " + i + "st content is displayed as: " + normalauthor);
			} catch (Exception e) {

				Utilities.verticalSwipe(driver);
				try {
					String songAuthor = rskids.jsonPath().get("assets.items[" + i + "].author");
					String songTitle = rskids.jsonPath().get("assets.items[" + i + "].title");
					WebElement title = driver.findElement(By.xpath(
							"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='"
									+ i + "']//android.widget.TextView[@text='" + songTitle + "']"));
					WebElement author = driver.findElement(By.xpath(
							"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='"
									+ i + "']//android.widget.TextView[@text='" + songAuthor + "']"));
					if (Utilities.explicitWaitVisible(driver, title, 3)) {

						String normalTitle = title.getText().trim();
						String normalauthor = author.getText().trim();
						audioTitle.add(normalTitle.trim());
						audioAuthor.add(normalauthor.trim());

						test.log(LogStatus.INFO,
								"Content Title of " + i + "st content is displayed as: " + normalTitle);
						test.log(LogStatus.INFO,
								"Content Author of " + i + "st content is displayed as: " + normalauthor);
					} else {
						test.log(LogStatus.INFO, "Validated All the contents under related tab contains: " + (i - 1)
								+ " number of cards");
						break;
					}
				} catch (Exception e1) {
					test.log(LogStatus.INFO,
							"Validated All the contents under related tab contains: " + (i - 1) + " number of cards");
					break;
				}

			}

			cardData1.add("Displayed");
			if (Utilities.explicitWaitVisible(driver, homepagev2.categoryicon, 5)) {
				test.log(LogStatus.INFO, "Category Icon displayed");
				cardData1.add("Displayed");
			}

			else {
				test.log(LogStatus.INFO, "Category Icon not displayed");
				cardData1.add("Not Displayed");
			}
		}

		test.log(LogStatus.INFO, "Api Response " + audioAuthorapi);
		test.log(LogStatus.INFO, "Api Response " + audioTitleapi);
		test.log(LogStatus.INFO, "Front end Response " + audioAuthor);
		test.log(LogStatus.INFO, "Front Response " + audioTitle);

		if (cardData1.contains("Displayed") && !cardData1.contains("Not Displayed")) {
			test.log(LogStatus.INFO, "Verified Meta data of cards under 'Related Tab' tab is pass");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is passed");
			homepagev2.smokeresults(testCase6, rowno6, "Pass");
		} else {
			test.log(LogStatus.INFO, "Verified Meta data of cards under 'Related Tab' tab is fail");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
			homepagev2.smokeresults(testCase6, rowno6, "Fail");
		}
		if (audioTitleapi.equals(audioTitle) && audioAuthorapi.equals(audioAuthor)) {
			test.log(LogStatus.INFO, "Meta data in API is matching With Meta Data displayed in front end");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
			homepagev2.smokeresults(testCase1, rowno1, "Pass");
		} else {
			test.log(LogStatus.INFO, "Meta data in API is not matching With Meta Data displayed in front end");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			homepagev2.smokeresults(testCase1, rowno1, "Fail");
		}

		// Daily pic
		if (Utilities.explicitWaitClickable(driver, homepagev2.dailypicks, 5))
			homepagev2.dailypicks.click();
		else
			BasePageV2.reportFail("'Daily Pics' tab not displayed");

		String urldaily = "https://api.vootkids.com/app/audio/v1/getDailyPicks.json?uId=f68aaef787664996915842b7f94ceafc&profileId=5b6a74ab9814ce142c96fdc9&limit=8&offSet=0&ks=djJ8MjI1fPzSHTNrIJ7eKlQYYIS0J23bJrKs0ORv80lkVUVIwED9Ti13yQh4tlYAZ6D_BRLCTLAIlqc6UVKvs8Ly8TjzcGDQvUkEiA8h2KI-5Ad1HA4owfQq8S3TvQhuGRpQhbZwFs_9ZrpWRfxUZgyncZ1C97svbVO2C5pqX7VLK2WLjU-i";
		Response rskidsdaily = Utilities.requestUtility(urldaily);
		String totalCardsdailytab = rskidsdaily.jsonPath().get("assets.sliderCount");
		int totalCardsdaily = Integer.parseInt(totalCardsdailytab);
		test.log(LogStatus.INFO, "Total Number of contents displayed under related tab is: " + totalCardsdaily);

		List<String> cardDataDaily = new ArrayList<String>();
		ArrayList<String> audioTitledaily = new ArrayList<String>();
		ArrayList<String> audioAuthorDaily = new ArrayList<String>();
		ArrayList<String> audioTitleapiDaily = new ArrayList<String>();
		ArrayList<String> audioAuthorapiDaily = new ArrayList<String>();
		next: for (int i = 0; i < totalCardsdaily; i++) {
			try {

				String songAuthor = rskidsdaily.jsonPath().get("assets.items[" + i + "].author");
				String songTitle = rskidsdaily.jsonPath().get("assets.items[" + i + "].title");
				audioTitleapiDaily.add(songTitle.trim());
				audioAuthorapiDaily.add(songAuthor.trim());
				WebElement title = driver.findElement(By.xpath(
						"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='" + i
								+ "']//android.widget.TextView[@text='" + songTitle + "']"));
				WebElement author = driver.findElement(By.xpath(
						"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='" + i
								+ "']//android.widget.TextView[@text='" + songAuthor + "']"));

				String normalTitle = title.getText().trim();
				String normalauthor = author.getText().trim();
				audioTitledaily.add(normalTitle.trim());
				audioAuthorDaily.add(normalauthor.trim());

				test.log(LogStatus.INFO, "Content Title of " + i + " content is displayed as: " + normalTitle);
				test.log(LogStatus.INFO, "Content Author of " + i + " content is displayed as: " + normalauthor);
			} catch (Exception e) {

				Utilities.verticalSwipe(driver);
				try {
					String songAuthor = rskidsdaily.jsonPath().get("assets.items[" + i + "].author");
					String songTitle = rskidsdaily.jsonPath().get("assets.items[" + i + "].title");
					WebElement title = driver.findElement(By.xpath(
							"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='"
									+ i + "']//android.widget.TextView[@text='" + songTitle + "']"));
					WebElement author = driver.findElement(By.xpath(
							"//android.view.ViewGroup[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='"
									+ i + "']//android.widget.TextView[@text='" + songAuthor + "']"));
					if (Utilities.explicitWaitVisible(driver, title, 3)) {
						test.log(LogStatus.INFO, "Verified scrollability of Audio Cards under 'Daily Pics' tab");
						test.log(LogStatus.PASS, "Test Case: " + testCase10 + " is Pass");
						homepagev2.smokeresults(testCase10, rowno10, "Pass");
						String normalTitle = title.getText().trim();
						String normalauthor = author.getText().trim();
						audioTitledaily.add(normalTitle.trim());
						audioAuthorDaily.add(normalauthor.trim());

						test.log(LogStatus.INFO, "Content Title of " + i + " content is displayed as: " + normalTitle);
						test.log(LogStatus.INFO,
								"Content Author of " + i + " content is displayed as: " + normalauthor);
					} else {
						test.log(LogStatus.INFO, "Validated All the contents under related tab contains: " + (i - 1)
								+ " number of cards");
						break;
					}
				} catch (Exception e1) {
					test.log(LogStatus.INFO,
							"Validated All the contents under related tab contains: " + (i - 1) + " number of cards");
					break;
				}

			}
		}
		test.log(LogStatus.INFO, "Api Response " + audioAuthorapiDaily);
		test.log(LogStatus.INFO, "Api Response " + audioTitleapiDaily);
		test.log(LogStatus.INFO, "Front end Response " + audioAuthorDaily);
		test.log(LogStatus.INFO, "Front Response " + audioTitledaily);

		cardDataDaily.add("Displayed");
		if (Utilities.explicitWaitVisible(driver, homepagev2.categoryicon, 5)) {
			test.log(LogStatus.INFO, "Category Icon displayed");
			cardDataDaily.add("Displayed");
		}

		else {
			test.log(LogStatus.INFO, "Category Icon not displayed");
			cardDataDaily.add("Not Displayed");
		}

		if (cardDataDaily.contains("Displayed") && !cardDataDaily.contains("Not Displayed")) {
			test.log(LogStatus.INFO, "Verified Meta data of cards under Daily Pics tab is pass");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase7 + " is passed");
			homepagev2.smokeresults(testCase7, rowno7, "Pass");
		} else {
			test.log(LogStatus.INFO, "Verified Meta data of cards under Daily Pics tab is fail");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " is Fail");
			homepagev2.smokeresults(testCase7, rowno7, "Fail");
		}
		if ((audioTitledaily).equals(audioTitleapiDaily) && audioAuthorDaily.equals(audioAuthorapiDaily)) {
			test.log(LogStatus.INFO, "Meta data in API is matching With Meta Data displayed in front end");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			homepagev2.smokeresults(testCase2, rowno2, "Pass");
		} else {
			test.log(LogStatus.INFO, "Meta data in API is not matching With Meta Data displayed in front end");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			homepagev2.smokeresults(testCase2, rowno2, "Fail");
		}

		try {
			homepagev2.audioplaylistCollapse.click();
		} catch (Exception e) {
			BasePageV2.reportFail("Audio Playlist Collapser 'Down Arrow' not displayed");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 5)) {
			Utilities.scrubtillend(driver, homepagev2.audioseekBar);

		} else
			BasePageV2.reportFail("Unable to scrub the audio");

		List<String> listenUI = new ArrayList<String>();
		if (Utilities.explicitWaitVisible(driver, homepagev2.listenAgainBtn, 2)) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 2)) {
				test.log(LogStatus.INFO, "'Close Button' is displayed when scrubbed to end of audio");
				listenUI.add("Displayed");

			}

			else {
				test.log(LogStatus.FAIL, "Close Button is not displayed when scrubbed to end of audio");
				listenUI.add("Not Displayed");
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.youmayalsoLike, 2)) {
				test.log(LogStatus.INFO, "'You might also like..' text is displayed when scrubbed to end of audio");
				listenUI.add("Displayed");
			}

			else {
				test.log(LogStatus.FAIL, "'You might also like..' text is not displayed when scrubbed to end of audio");
				listenUI.add("Not Displayed");
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.AudioOverlay, 2)) {
				test.log(LogStatus.INFO, "'Audo Overlay' is displayed when scrubbed to end of audio");
				listenUI.add("Displayed");
			}

			else {
				test.log(LogStatus.FAIL, "Audo Overlay' is not displayed when scrubbed to end of audio");
				listenUI.add("Not Displayed");
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.listenAgainBtn, 2)) {
				test.log(LogStatus.INFO, "'Listen Again Button' is displayed when scrubbed to end of audio");
				listenUI.add("Displayed");

			}

			else {
				test.log(LogStatus.FAIL, "'Listen Again Button' is not displayed when scrubbed to end of audio");
				listenUI.add("Not Displayed");
			}

			if (listenUI.contains("Displayed") && !listenUI.contains("Not Displayed")) {
				// BasePageV2.takeScreenshot();

				test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Passed");
				homepagev2.smokeresults(testCase3, rowno3, "Pass");
			} else {
				BasePageV2.takeScreenshot();

				test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
				homepagev2.smokeresults(testCase3, rowno3, "Fail");
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.listenAgainBtn, 2))
				homepagev2.listenAgainBtn.click();
			else
				BasePageV2.reportFail("Listen Again Button not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 2)) {
				String audioTitlePostclickListen = homepagev2.audioTitle.getText();
				test.log(LogStatus.INFO, "Audio Title Before Clicking Listen button is: " + contentTitle);
				test.log(LogStatus.INFO, "Audio Title After Clicking Listen button is: " + audioTitlePostclickListen);
				if (audioTitlePostclickListen.equalsIgnoreCase(contentTitle)) {
					test.log(LogStatus.INFO,
							"Audio Title Before Clicking Listen button and After clicking listen button are matching");
					test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Passed");
					homepagev2.smokeresults(testCase4, rowno4, "Pass");
				} else {
					test.log(LogStatus.INFO,
							"Audio Title Before Clicking Listen button and After clicking listen button are not matching");
					test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Passed");
					homepagev2.smokeresults(testCase4, rowno4, "Fail");
				}
			}
			Thread.sleep(3000);
			Utilities.scrubtillend(driver, homepagev2.audioseekBar);

			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 2))

				homepagev2.audioplayerClose.click();
			else
				BasePageV2.reportFail("Unable to click on close button");

			if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 2)) {
				if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 2)) {
					test.log(LogStatus.INFO,
							"After clicking on close button in audio overlay Navigated to the page from where audio played");
					// BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is passed");
					homepagev2.smokeresults(testCase5, rowno5, "Pass");
				} else
					BasePageV2.reportFail(
							"After clicking on close button in audio overlay not Navigated to the page from where audio played");

			} else
				BasePageV2.reportFail(
						"After clicking on close button in audio overlay not Navigated to the page from where audio played");

		} else
			BasePageV2.reportFail("Listen Again Button not displayed");
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
