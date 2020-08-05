package com.viacom.bitrateValidation;

import java.util.Hashtable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.CharlesPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LinearcontentPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.Activity;

public class BitrateValidationTestLiveVodandMovie_P1 extends BaseTestV2 {
	String testName = "BitrateValidationTestLiveVodandMovie_P1";
	String testCase = "Verify if VOD content plays in Auto stream quality post changing streaming quality options from Settings";
	String testId = "VK_854";

	String testCase1 = "Verify if VOD content plays in Low stream quality post changing streaming quality options from Settings";
	String testId1 = "VK_855";

	String testCase2 = "Verify if VOD content plays in Medium stream quality post changing streaming quality options from Settings";
	String testId2 = "VK_856";

	String testCase3 = "Verify if VOD content plays in High stream quality post changing streaming quality options from Settings";
	String testId3 = "VK_857";

	String testCase4 = "Verify if Movie content plays in Auto stream quality post changing streaming quality options from Settings";
	String testId4 = "VK_2096";

	String testCase5 = "Verify if Movie content plays in Low stream quality post changing streaming quality options from Settings";
	String testId5 = "VK_2097";

	String testCase6 = "Verify if Movie content plays in Medium stream quality post changing streaming quality options from Settings";
	String testId6 = "VK_2098";

	String testCase7 = "Verify if Movie content plays in High stream quality post changing streaming quality options from Settings";
	String testId7 = "VK_2099";

	String testCase8 = "Verify if Live content plays in Auto stream quality post changing streaming quality options from Settings";
	String testId8 = "VK_854";

	String testCase9 = "Verify if Live content plays in Low stream quality post changing streaming quality options from Settings";
	String testId9 = "VK_855";

	String testCase10 = "Verify if Live content plays in Medium stream quality post changing streaming quality options from Settings";
	String testId10 = "VK_856";

	String testCase11 = "Verify if Live content plays in High stream quality post changing streaming quality options from Settings";
	String testId11 = "VK_857";

	int Abitrate = 0;
	int Hbitrate = 0;
	Response resp;
	boolean liveflag = false;
	String nextPageAPI = "";
	String showTitle = "";

	int Lbitrate = 0;
	String movieAPI = "";
	boolean movFlag = false;

	String trayTitle = "";
	String xpath = "";

	int VodAutoBitrate = 0;
	int liveAutoBitrate = 0;
	int movieAutoBitrate = 0;

	int VodhighBitrate = 0;
	int livehighBitrate = 0;
	int moviehighBitrate = 0;

	int VodmediumBitrate = 0;
	int livemediumBitrate = 0;
	int moviemediumBitrate = 0;

	int VodlowBitrate = 0;
	int livelowBitrate = 0;
	int movielowBitrate = 0;

	@Test(dataProvider = "getData")
	public void BitrateCheckLiveandVod(Hashtable<String, String> data) throws Exception {
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test to verify " + testName + VootConstants.DEVICE_NAME);
		// Open charles application
		test.log(LogStatus.INFO,
				"Validating Bitrate Validation Test cases for Episode VOD, Movies and Live content");
		

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		LinearcontentPageV2 linearpage = new LinearcontentPageV2(driver, test);
		SettingsPageV2 settings = new SettingsPageV2(driver, test);
		CharlesPageV2 charlespage = new CharlesPageV2(driver, test);
		String email = data.get("Email");
		String pwd = data.get("Password");

		HomePageV2.login(email, pwd);

		CharlesPageV2.qualitySettings(settings.deviceStremQulAuto, "Auto");

		HomePageV2.tabClick("Watch");

		CharlesPageV2.bitrates.clear();
		
		if (Utilities.explicitWaitVisible(driver, charlespage.mastheadContent, 20)) {
			charlespage.mastheadContent.click();
			Thread.sleep(30000);
		}
			
		else {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Vod Masthead carousel was not displayed in watch page");
		}

		homepagev2.verifyAndProgressBar();

		driver.navigate().back();
		Utilities.saveandcloseCharles(testName);

		CharlesPageV2.bitratescapture();

		// System.out.println("bit rate displayed for Vod content Auto quality was: " +
		// CharlesPageV2.bitrates);

		VodAutoBitrate = CharlesPageV2.bitrates.get(0);
		CharlesPageV2.bitrates.clear();

		test.log(LogStatus.INFO, "Bitrate displayed for Vod content Auto quality was: " + VodAutoBitrate);

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		Utilities.openCharles();

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
				homepagev2.verifyAndProgressBar();
				Thread.sleep(30000);
			} catch (Exception e) {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
			}

			driver.navigate().back();
			Utilities.saveandcloseCharles(testName);

			CharlesPageV2.bitratescapture();

			// System.out.println("bit rate displayed for Vod content Auto quality was: " +
			// CharlesPageV2.bitrates);

			movieAutoBitrate = CharlesPageV2.bitrates.get(0);
			CharlesPageV2.bitrates.clear();

			test.log(LogStatus.INFO, "Bitrate displayed for Movie content Auto quality was: " + movieAutoBitrate);
		} else {
			test.log(LogStatus.WARNING, "Movie Content was not displayed in Watch Page");
			Utilities.saveandcloseCharles("testName");
		}

		// test.log(LogStatus.INFO, "Bitrate displayed for Vod content Auto quality was:
		// " + VodAutoBitrate);

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");

		String end = "//android.widget.TextView[contains(@text,'Live Now')]";
		try {
			Utilities.verticalSwipe(driver, end);
		} catch (Exception e) {
			BasePageV2.reportFail("Live Now tab not displayed");
		}

		if (Utilities.explicitWaitVisible(driver, linearpage.linearcontent, 20)) {
			linearpage.linearcontent.click();
			homepagev2.verifyAndProgressBar();
			driver.navigate().back();
			Thread.sleep(5000);
			Utilities.saveandcloseCharles(testName);

			liveflag = true;
			CharlesPageV2.bitratescapture();

			liveAutoBitrate = CharlesPageV2.bitrates.get(0);
			test.log(LogStatus.INFO, "Bitrate displayed for Live content Auto quality was: " + liveAutoBitrate);

		} else {
			liveflag = false;
			Utilities.saveandcloseCharles(testName);
			test.log(LogStatus.WARNING, "Live Content not available");
		}

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		CharlesPageV2.qualitySettings(settings.deviceStremQulHigh, "High");

		HomePageV2.tabClick("Watch");

		if (Utilities.explicitWaitVisible(driver, charlespage.mastheadContent, 20))
			charlespage.mastheadContent.click();
		else {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Vod Masthead carousel was not displayed in watch page");
		}

		homepagev2.verifyAndProgressBar();
		driver.navigate().back();
		Utilities.saveandcloseCharles(testName);

		CharlesPageV2.bitratescapture();

		// System.out.println("bit rate displayed for Vod content Auto quality was: " +
		// CharlesPageV2.bitrates);

		VodhighBitrate = CharlesPageV2.bitrates.get(0);

		test.log(LogStatus.INFO, "Bitrate displayed for Vod content High quality was: " + VodhighBitrate);

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();
		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");
		if (movFlag == true) {
			xpath = "//android.widget.TextView[@text='" + trayTitle + "']";
			Utilities.verticalSwipe(driver, xpath);

			try {
				resp = Utilities.requestUtility(nextPageAPI);
				// resp = Utilities.requestUtility(watchPageAPI);
				showTitle = resp.jsonPath().getString("assets.items[0].title");

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
				homepagev2.verifyAndProgressBar();
				Thread.sleep(30000);
			} catch (Exception e) {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
			}

			driver.navigate().back();
			Utilities.saveandcloseCharles(testName);

			CharlesPageV2.bitratescapture();

			// System.out.println("bit rate displayed for Vod content Auto quality was: " +
			// CharlesPageV2.bitrates);

			moviehighBitrate = CharlesPageV2.bitrates.get(0);
			CharlesPageV2.bitrates.clear();

			test.log(LogStatus.INFO, "Bitrate displayed for Movie content High quality was: " + moviehighBitrate);
		} else {
			test.log(LogStatus.WARNING, "Movie Content was not displayed in Watch Page");
		}

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();
		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");

		if (liveflag == true) {
			try {
				Utilities.verticalSwipe(driver, end);
			} catch (Exception e) {
				BasePageV2.reportFail("Live Now tab not displayed");
			}

			if (Utilities.explicitWaitVisible(driver, linearpage.linearcontent, 20)) {
				linearpage.linearcontent.click();
				homepagev2.verifyAndProgressBar();
				driver.navigate().back();
				Utilities.saveandcloseCharles(testName);
				liveflag = true;
				CharlesPageV2.bitratescapture();

				livehighBitrate = CharlesPageV2.bitrates.get(0);
				test.log(LogStatus.INFO, "Bitrate displayed for Live content High quality was: " + livehighBitrate);

			} else {
				liveflag = false;
				Utilities.saveandcloseCharles(testName);
				test.log(LogStatus.WARNING, "Live Content not available");
			}

		} else
			test.log(LogStatus.WARNING, "Live Content not available");

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		CharlesPageV2.qualitySettings(settings.deviceStremQulMedium, "Medium");

		HomePageV2.tabClick("Watch");

		if (Utilities.explicitWaitVisible(driver, charlespage.mastheadContent, 20))
			charlespage.mastheadContent.click();
		else {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Vod Masthead carousel was not displayed in watch page");
		}

		homepagev2.verifyAndProgressBar();
		driver.navigate().back();
		Utilities.saveandcloseCharles(testName);

		CharlesPageV2.bitratescapture();

		// System.out.println("bit rate displayed for Vod content Auto quality was: " +
		// CharlesPageV2.bitrates);

		VodmediumBitrate = CharlesPageV2.bitrates.get(0);

		test.log(LogStatus.INFO, "Bitrate displayed for Vod content Medium quality was: " + VodmediumBitrate);

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");

		if (movFlag == true) {
			xpath = "//android.widget.TextView[@text='" + trayTitle + "']";
			Utilities.verticalSwipe(driver, xpath);

			try {
				resp = Utilities.requestUtility(nextPageAPI);
				// resp = Utilities.requestUtility(watchPageAPI);
				showTitle = resp.jsonPath().getString("assets.items[0].title");

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
				homepagev2.verifyAndProgressBar();
				Thread.sleep(30000);
			} catch (Exception e) {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
			}

			driver.navigate().back();
			Utilities.saveandcloseCharles(testName);

			CharlesPageV2.bitratescapture();

			// System.out.println("bit rate displayed for Vod content Auto quality was: " +
			// CharlesPageV2.bitrates);

			moviemediumBitrate = CharlesPageV2.bitrates.get(0);
			CharlesPageV2.bitrates.clear();

			test.log(LogStatus.INFO, "Bitrate displayed for Movie content Medium quality was: " + moviemediumBitrate);
		} else {
			test.log(LogStatus.WARNING, "Movie Content was not displayed in Watch Page");
		}

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");

		if (liveflag == true) {
			try {
				Utilities.verticalSwipe(driver, end);
			} catch (Exception e) {
				BasePageV2.reportFail("Live Now tab not displayed");
			}

			if (Utilities.explicitWaitVisible(driver, linearpage.linearcontent, 20)) {
				linearpage.linearcontent.click();
				homepagev2.verifyAndProgressBar();
				driver.navigate().back();
				Utilities.saveandcloseCharles(testName);

				CharlesPageV2.bitratescapture();

				livemediumBitrate = CharlesPageV2.bitrates.get(0);
				test.log(LogStatus.INFO, "Bitrate displayed for Live content Medium quality was: " + livemediumBitrate);

			} else {
				liveflag = false;
				Utilities.saveandcloseCharles(testName);
				test.log(LogStatus.WARNING, "Live Content not available");
			}

		} else
			test.log(LogStatus.WARNING, "Live Content not available");

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		CharlesPageV2.qualitySettings(settings.deviceStremQullow, "Low");

		HomePageV2.tabClick("Watch");

		if (Utilities.explicitWaitVisible(driver, charlespage.mastheadContent, 20))
			charlespage.mastheadContent.click();
		else {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Vod Masthead carousel was not displayed in watch page");
		}

		homepagev2.verifyAndProgressBar();
		driver.navigate().back();
		Utilities.saveandcloseCharles(testName);

		CharlesPageV2.bitratescapture();

		// System.out.println("bit rate displayed for Vod content Auto quality was: " +
		// CharlesPageV2.bitrates);

		VodlowBitrate = CharlesPageV2.bitrates.get(0);

		test.log(LogStatus.INFO, "Bitrate displayed for Vod content Low quality was: " + VodlowBitrate);

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");

		if (movFlag == true) {
			xpath = "//android.widget.TextView[@text='" + trayTitle + "']";
			Utilities.verticalSwipe(driver, xpath);

			try {
				resp = Utilities.requestUtility(nextPageAPI);
				// resp = Utilities.requestUtility(watchPageAPI);
				showTitle = resp.jsonPath().getString("assets.items[0].title");

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
				
				if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 20))
					homepagev2.playAudiobookbutton.click();
				else {
					Utilities.saveCharles(testName);
					BasePageV2.reportFail("Unable to play Video book content");
					BasePageV2.takeScreenshot();
				}
				
				homepagev2.verifyAndProgressBar();
				Thread.sleep(30000);
			} catch (Exception e) {
				Utilities.saveandcloseCharles(testName);
				BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
			}

			driver.navigate().back();
			Utilities.saveandcloseCharles(testName);

			CharlesPageV2.bitratescapture();

			// System.out.println("bit rate displayed for Vod content Auto quality was: " +
			// CharlesPageV2.bitrates);

			movielowBitrate = CharlesPageV2.bitrates.get(0);
			CharlesPageV2.bitrates.clear();

			test.log(LogStatus.INFO, "Bitrate displayed for Movie content Low quality was: " + movielowBitrate);
		} else {
			test.log(LogStatus.WARNING, "Movie Content was not displayed in Watch Page");
		}

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		CharlesPageV2.bitrates.clear();

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		Thread.sleep(10000);
		HomePageV2.tabClick("Watch");
		if (liveflag == true) {
			try {
				Utilities.verticalSwipe(driver, end);
			} catch (Exception e) {
				BasePageV2.reportFail("Live Now tab not displayed");
			}

			if (Utilities.explicitWaitVisible(driver, linearpage.linearcontent, 20)) {
				linearpage.linearcontent.click();
				homepagev2.verifyAndProgressBar();
				driver.navigate().back();

				Utilities.saveandcloseCharles(testName);

				CharlesPageV2.bitratescapture();

				livelowBitrate = CharlesPageV2.bitrates.get(0);
				test.log(LogStatus.INFO, "Bitrate displayed for Live content Low quality was: " + livelowBitrate);

			} else {
				liveflag = false;
				Utilities.saveandcloseCharles(testName);
				test.log(LogStatus.WARNING, "Live Content not available");
			}

		} else
			test.log(LogStatus.WARNING, "Live Content not available");

		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		if (VodAutoBitrate <= VodhighBitrate) {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " --> " + testId + " is Pass");
			if (!Utilities.setResultsKids(testId, "Pass"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " --> " + testId + " is Fail");
			if (!Utilities.setResultsKids(testId, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		if (VodlowBitrate < VodmediumBitrate) {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " --> " + testId1 + " is Pass");
			if (!Utilities.setResultsKids(testId1, "Pass"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId1, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " --> " + testId1 + " is Fail");
			if (!Utilities.setResultsKids(testId1, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId1, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		if (VodmediumBitrate < VodhighBitrate) {
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " --> " + testId2 + " is Pass");
			if (!Utilities.setResultsKids(testId2, "Pass"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId2, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " --> " + testId2 + " is Fail");
			if (!Utilities.setResultsKids(testId2, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId2, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		if (VodhighBitrate > VodmediumBitrate && VodhighBitrate > VodlowBitrate) {
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " --> " + testId3 + " is Pass");
			if (!Utilities.setResultsKids(testId3, "Pass"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId3, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " --> " + testId3 + " is Fail");
			if (!Utilities.setResultsKids(testId3, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId3, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		if (movFlag == true) {

			if (movieAutoBitrate <= moviehighBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase4 + " --> " + testId4 + " is Pass");
				if (!Utilities.setResultsKids(testId4, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId4, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " --> " + testId4 + " is Fail");
				if (!Utilities.setResultsKids(testId4, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId4, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			if (movielowBitrate < moviemediumBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase5 + " --> " + testId5 + " is Pass");
				if (!Utilities.setResultsKids(testId5, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId5, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " --> " + testId5 + " is Fail");
				if (!Utilities.setResultsKids(testId5, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId5, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			if (moviemediumBitrate < moviehighBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase6 + " --> " + testId6 + " is Pass");
				if (!Utilities.setResultsKids(testId6, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId6, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " --> " + testId6 + " is Fail");
				if (!Utilities.setResultsKids(testId6, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId6, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			if (moviehighBitrate > moviemediumBitrate && moviehighBitrate > movielowBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase7 + " --> " + testId7 + " is Pass");
				if (!Utilities.setResultsKids(testId7, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId7, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " --> " + testId7 + " is Fail");
				if (!Utilities.setResultsKids(testId7, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId7, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} else {
			test.log(LogStatus.WARNING,
					"Test Case: " + testCase4 + " --> " + testId4 + " is Unable to test as Movie not available");
			if (!Utilities.setResultsKids(testId4, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId3, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			test.log(LogStatus.WARNING,
					"Test Case: " + testCase5 + " --> " + testId5 + " is Unable to test as Movie not available");
			if (!Utilities.setResultsKids(testId5, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId5, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			test.log(LogStatus.WARNING,
					"Test Case: " + testCase6 + " --> " + testId6 + " is Unable to test as Movie not available");
			if (!Utilities.setResultsKids(testId6, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId6, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			test.log(LogStatus.WARNING,
					"Test Case: " + testCase7 + " --> " + testId7 + " is Unable to test as Movie not available");
			if (!Utilities.setResultsKids(testId7, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId7, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		if (liveflag == true) {
			if (liveAutoBitrate <= livehighBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase8 + " --> " + testId8 + " is Pass");
				if (!Utilities.setResultsKids(testId8, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId8, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase8 + " --> " + testId8 + " is Fail");
				if (!Utilities.setResultsKids(testId8, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId8, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			if (livelowBitrate < livemediumBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase9 + " --> " + testId9 + " is Pass");
				if (!Utilities.setResultsKids(testId9, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId9, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase9 + " --> " + testId9 + " is Fail");
				if (!Utilities.setResultsKids(testId9, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId9, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			if (livemediumBitrate < livehighBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase10 + " --> " + testId10 + " is Pass");
				if (!Utilities.setResultsKids(testId10, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId10, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase10 + " --> " + testId10 + " is Fail");
				if (!Utilities.setResultsKids(testId10, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId10, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

			if (livehighBitrate > moviemediumBitrate && livehighBitrate > livelowBitrate) {
				test.log(LogStatus.PASS, "Test Case: " + testCase11 + " --> " + testId11 + " is Pass");
				if (!Utilities.setResultsKids(testId11, "Pass"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId11, "Pass");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase11 + " --> " + testId11 + " is Fail");
				if (!Utilities.setResultsKids(testId11, "Fail"))
					test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				else {
					try {
						Utilities.setResultsKids(testId11, "Fail");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} else {
			test.log(LogStatus.WARNING,
					"Test Case: " + testCase8 + " --> " + testId8 + " is Unable to test as Live not available");
			if (!Utilities.setResultsKids(testId8, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId8, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			test.log(LogStatus.WARNING,
					"Test Case: " + testCase9 + " --> " + testId9 + " is Unable to test as Live not available");
			if (!Utilities.setResultsKids(testId9, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId9, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			test.log(LogStatus.WARNING,
					"Test Case: " + testCase10 + " --> " + testId10 + " is Unable to test as Live not available");
			if (!Utilities.setResultsKids(testId10, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId10, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			test.log(LogStatus.WARNING,
					"Test Case: " + testCase11 + " --> " + testId11 + " is Unable to test as Live not available");
			if (!Utilities.setResultsKids(testId11, "Unable  to test"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			else {
				try {
					Utilities.setResultsKids(testId11, "Unable  to test");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
