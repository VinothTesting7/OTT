package com.viacom.audioplayer;

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
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifyInterruptionsAudioPlayerTest extends BaseTestV2 {

	String testName = "VerifyInterruptionsAudioPlayerTest";
	String testCase = "'Verify Audio player is present on the device notification window'";
	String testCase1 = "'Verify Audio player is present on the device notification window'";
	String testCase2 = "";
	String testCase3 = "";
	String testCase4 = "";
	String testCase5 = "";
	String audiobook = "";
	String contentTitle = "";
	String contentSubTitle = "";

	// Author: Karthik T S
	@Test(dataProvider = "getData")
	public void VerifyInterruptionsAudioPlayer(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

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

		launchApp();
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

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 10))
			homepagev2.audioplayPause.click();
		else
			BasePageV2.reportFail("Audio Play pause option is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 2)) {
			contentTitle = homepagev2.audioTitle.getText();
			test.log(LogStatus.INFO, "Title of currently playing audio is: " + contentTitle);

			if (Utilities.explicitWaitVisible(driver, homepagev2.audiosubTitle, 5)) {
				contentSubTitle = homepagev2.audiosubTitle.getText();
				test.log(LogStatus.INFO, "Sub Title of currently playing Audio is " + contentSubTitle);
			} else
				BasePageV2.reportFail("Audio Player Sub Title is not displayed");
		}

		else
			BasePageV2.reportFail("Audio Title Not found");

		try {
			driver.openNotifications();
		} catch (Exception e) {
		}

		ArrayList<String> notificationUI = new ArrayList<String>();
		try {
			String xpath = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.TextView[contains(@text,'"
					+ contentTitle + "')]";
			String xpath1 = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.TextView[contains(@text,'"
					+ contentSubTitle + "')]";
			WebElement audioNotificationSubTitle = driver.findElement(By.xpath(xpath1));

			WebElement audioNotificationTitle = driver.findElement(By.xpath(xpath));
			// test.log(LogStatus.INFO, "Title displayed in notification is: " +
			// audioNotificationTitle.getText());

			if (Utilities.explicitWaitVisible(driver, audioNotificationTitle, 10)) {
				test.log(LogStatus.INFO,
						"Title displayed in notification displayed is: " + audioNotificationTitle.getText());
				BasePageV2.reportPass("Test Case: " + testCase + " is Pass");
				HomePageV2.smokeresults(testCase, rowno, "Pass");
				notificationUI.add("Pass");
			} else
				BasePageV2.reportFail("App notification not displayed");

			if (Utilities.explicitWaitVisible(driver, audioNotificationSubTitle, 10)) {
				test.log(LogStatus.INFO,
						"Sub Title displayed in notification displayed is: " + audioNotificationSubTitle.getText());
				notificationUI.add("Pass");
			} else
				BasePageV2.reportFail("App notification not displayed");

		} catch (Exception e) {
		}

		try {
			String xpath = "//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.ImageButton[@resource-id = 'android:id/action0']";
			WebElement notificationPause = driver.findElement(By.xpath(xpath));
			if (Utilities.explicitWaitVisible(driver, notificationPause, 5)) {
				test.log(LogStatus.INFO, "Pause Button is displayed in notification window");
				notificationUI.add("Pass");
			} else
				BasePageV2.reportFail("Play Pause Button is not displayed in notification window");
		} catch (Exception e) {
		}

		if (notificationUI.contains("Pass")) {
			BasePageV2.reportPass("Test Case: " + testCase1 + " is Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
