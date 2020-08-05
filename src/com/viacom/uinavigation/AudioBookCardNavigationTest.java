package com.viacom.uinavigation;

import java.util.Hashtable;

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

public class AudioBookCardNavigationTest extends BaseTestV2 {
	
	

	String testName = "AudioBookCardNavigationTest";
	String testCase = "Verify card navigation (Related Tray)";
	String testCase1 = "Verify card navigation (Daily Pics)";
	String testCase2 = "Verify card navigation (More From Author)";
	String testCase3 = "Verify audio book card tap functionality in 'You Might Also Like' screen";
	String contentTitle = "";
	String gridTitle = "";
	String showDetailContent = "";
	String relatedtabcontent = "";
	String audiobook = "";
	Response rskidsdaily;
	Response rskidsrelated;
	String songTitle = "";
	String songTitle1 = "";

	String programinfoTitle = "";

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

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		
		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		// 2. Tap on Listen icon on top bar menu
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

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 10)) {
			homepagev2.playAudiobookbutton.click();
		} else
			BasePageV2.reportFail("Play button is not displayed in audio detail page");

		try {
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 10))
				contentTitle = homepagev2.audioTitle.getText();
			else
				BasePageV2.reportFail("Audio Title Not found");

		} catch (Exception e) {
			BasePageV2.reportFail("Audio Title Not found");
		}
		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10)) {
			test.log(LogStatus.INFO,
					" 'Audio playlist expander (up arrow)' switch button is displayed and clicking on it");
		//	BasePageV2.takeScreenshot();
			homepagev2.audioplaylistExpand.click();
		} else
			BasePageV2.reportFail(" 'Audio playlist expander (up arrow)' switch button is not displayed");

		Utilities.verticalSwipeDown(driver, homepagev2.related);
		
		if (Utilities.explicitWaitVisible(driver, homepagev2.related, 2)) {
			homepagev2.related.click();
			

			try {
				String xpath = "//*[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='1']//android.widget.TextView";

				Utilities.verticalSwipe(driver, xpath);
				WebElement ab = driver.findElement(By.xpath(xpath));

				if (Utilities.explicitWaitVisible(driver, ab, 10)) {
					relatedtabcontent=ab.getText().trim();
					ab.click();
				}
					
				else
					BasePageV2.reportFail("Audio Book is not displayed under Related tab");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else
			BasePageV2.reportFail("Related Tray Not Displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 5)) {
			programinfoTitle = homepagev2.audTitle.getText();
			if ((relatedtabcontent.trim()).equalsIgnoreCase(programinfoTitle.trim())
					&& Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 1)) {
				test.log(LogStatus.INFO, "Navigated Successfully to respective Program info page");
			//	BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
				HomePageV2.smokeresults(testCase, rowno, "Pass");
			} else
				BasePageV2.reportFail("Play Button not displayed");
		} else
			BasePageV2.reportFail("Audio Title Not Displayed in Audio detail Page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.miniplayerThumbnail, 2))
			homepagev2.miniplayerThumbnail.click();
		else
			BasePageV2.reportFail("Mini Player Not Displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10)) {
			test.log(LogStatus.INFO,
					" 'Audio playlist expander (up arrow)' switch button is displayed and clicking on it");
		//	BasePageV2.takeScreenshot();
			homepagev2.audioplaylistExpand.click();
		} else
			BasePageV2.reportFail(" 'Audio playlist expander (up arrow)' switch button is not displayed");

		
		Utilities.verticalSwipeDown(driver, homepagev2.dailypicks);
		if (Utilities.explicitWaitVisible(driver, homepagev2.dailypicks, 2)) {
			homepagev2.dailypicks.click();
//bcd
		

			try {
				String xpath = "//*[@resource-id = 'com.viacom18.vootkids:id/parent_layout'][@index='1']//android.widget.TextView";

				Utilities.verticalSwipe(driver, xpath);
				WebElement ab = driver.findElement(By.xpath(xpath));

				if (Utilities.explicitWaitVisible(driver, ab, 10)) {
					relatedtabcontent=ab.getText().trim();
					ab.click();
				}
					
				else
					BasePageV2.reportFail("Audio Book is not displayed under Related tab");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			BasePageV2.reportFail("'Daily Pics tab' Not Displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 5)) {
			String programinfoTitle = homepagev2.audTitle.getText();
			if ((relatedtabcontent.trim()).equalsIgnoreCase(programinfoTitle.trim())
					&& Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 1)) {
				test.log(LogStatus.INFO, "Navigated Successfully to respective Program info page");
			//	BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
				HomePageV2.smokeresults(testCase1, rowno1, "Pass");
			} else {
				test.log(LogStatus.INFO, "Failed to Navigate to respective Program info page");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
				HomePageV2.smokeresults(testCase1, rowno1, "Fail");
			}
		} else
			BasePageV2.reportFail("Audio Title Not Displayed in Audio detail Page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.miniplayerThumbnail, 2))
			homepagev2.miniplayerThumbnail.click();
		else
			BasePageV2.reportFail("Mini Player Not Displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 5)) {
			Utilities.scrubtillend(driver, homepagev2.audioseekBar);

		} else
			BasePageV2.reportFail("Unable to scrub the audio");

		if (Utilities.explicitWaitVisible(driver, homepagev2.listenAgainBtn, 2)) {
			test.log(LogStatus.INFO, "'You Might Also Like' page displayed");
		} else
			BasePageV2.reportFail("'You Might Also Like' page Not Displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.gridItemImage, 5)) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.gridItemTitle, 2))
				gridTitle = homepagev2.gridItemTitle.getText();
			else {
				test.log(LogStatus.INFO, "Content title not displayed");
				//BasePageV2.takeScreenshot();
			}
		//	BasePageV2.takeScreenshot();
			try {
				homepagev2.gridItemTitle.click();
			} catch (Exception e) {
				homepagev2.gridItemTitle.click();
			}
		} else
			BasePageV2.reportFail("Content not displayed in 'You May Also Like' page");

		try {
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 10))
				contentTitle = homepagev2.audioTitle.getText();
			else
				BasePageV2.reportFail("Audio Title Not found");

		} catch (Exception e) {
		}

		if (contentTitle.equalsIgnoreCase(gridTitle)) {
			test.log(LogStatus.INFO, "Content from 'You May Also Like' tray played");
		//	BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
			HomePageV2.smokeresults(testCase3, rowno3, "Pass");

		} else {
			test.log(LogStatus.INFO, "Content from 'You May Also Like' tray not played");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			HomePageV2.smokeresults(testCase3, rowno3, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
