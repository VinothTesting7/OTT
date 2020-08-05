package com.viacom.downloads;

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
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class DownloadCancelFeatureTest extends BaseTestV2 {
	String testName = "DownloadCancelFeatureTest";
	String showname = "";
	String testCase = "Verify the 'Cancel Download' button functionality in the pop-up";
	String testCase1 = "Verify the functionality by tapping on 'In Progress' status episodes in 'Download Episodes' screen";
	String testCase2 = "Verify the functionality by tapping on 'Completely Downloaded' episode cards in 'Download Episodes' screen";
	String testCase3 = "Edit Downloads screen should have below UI";
	String testCase4 = "Verify the Metadata for Episode card in Edit downloads screen";
	String testCase5 = "'Verify Delete CTA button is displayed at bottom of the screen post tapping on Minimum 1 card'";
	String testCase6 = "Verify if the delete button pops up on selecting any one of the contents by tapping on the cross (X) button";
	String headerEditDownloads = "Edit Downloads";
	String testCase7 = "Verify Delete CTA button is Auto hidden post deselecting all cards";
	String testCase8 = "Verify if user is navigated to Downloads tab when tapped on back arrow in Edit downloads screen";
	String EpisodeCard = "";
	// Author: Karthik

	@Test(dataProvider = "getData")
	public void DownloadCancelFeature(Hashtable<String, String> data) throws Exception {
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

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		HomePageV2.tabClick("Watch");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);

		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("WatchCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);

				Response respTab = Utilities.requestUtility(ApiTab);

				EpisodeCard = respTab.jsonPath().get("assets.items[2].title");
				// Audiobooks.add(abooks);

			} else
				System.out.println("Unable to fetch tab from API");
		}

		try {
			String xpath = "//android.widget.TextView[contains(@text,'" + EpisodeCard + "')]";

			Utilities.verticalSwipe(driver, xpath);
			WebElement ab = driver.findElement(By.xpath(xpath));

			if (Utilities.explicitWaitVisible(driver, ab, 10))
				ab.click();
			else
				BasePageV2.reportFail("Episode card is not displayed");
		} catch (Exception e) {
		}

		Utilities.verticalSwipe(driver, downloadpagev2.downloadIconEpisodes);
		Thread.sleep(10000);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadIconEpisodes, 2))
			downloadpagev2.downloadIconEpisodes.click();
		else
			BasePageV2.reportFail("Download icon is not displayed in show detail page of the show: " + EpisodeCard);

		try {
			WebElement downloadTitle = driver.findElement(By.xpath(
					"//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='1']//*[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']"));

			WebElement downloadStatusIcon = driver.findElement(
					By.xpath("//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='1']"));
			test.log(LogStatus.INFO, "Download Icon displayed for the content: " + downloadTitle.getText());
			if (Utilities.explicitWaitVisible(driver, downloadStatusIcon, 20)) {
				downloadStatusIcon.click();
				Thread.sleep(2000);
				// Verify cancel feature
				downloadStatusIcon.click();

				if (Utilities.explicitWaitVisible(driver, downloadpagev2.canceldownloadingThing, 3)) {
					test.log(LogStatus.INFO, "Download Cancel popup displayed");

					test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is pass");
					homepagev2.smokeresults(testCase1, rowno1, "Pass");
					downloadpagev2.canceldownloadingThing.click();
				}

				else
					BasePageV2.reportFail("Cancel Download popup not displayed");

			} else
				BasePageV2.reportFail("Download icon is not displayed");

		} catch (Exception e) {
			test.log(LogStatus.INFO, "Unable to click on download icon");
			// BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.ConfirmDeleteDownload, 5))
			downloadpagev2.ConfirmDeleteDownload.click();
		else
			BasePageV2.reportFail("Delete confirmation popup not displayed");

		try {
			WebElement downloadStatusIcon = driver.findElement(
					By.xpath("//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index=' 1']"));

			if (Utilities.explicitWaitVisible(driver, downloadStatusIcon, 20)) {

				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
				homepagev2.smokeresults(testCase, rowno, "Pass");
				downloadStatusIcon.click();
				Thread.sleep(2000);

			} else {

				test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
				homepagev2.smokeresults(testCase, rowno, "Fail");
			}

		} catch (Exception e) {
			BasePageV2.reportFail("Unable to cancel download");
		}

		for (int i = 0; i < 2; i++) {
			driver.pressKeyCode(AndroidKeyCode.BACK);
		}

		Utilities.verticalSwipe(driver, homepagev2.profilepic);
		Thread.sleep(5000);
		HomePageV2.tabClick("My Stuff");

		Utilities.verticalSwipe(driver, downloadpagev2.downloadsTabMystuffpage);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 20))
			downloadpagev2.downloadsTabMystuffpage.click();
		else
			BasePageV2.reportFail("Downloads tab is not displayed");

		for (int i = 0; i < 2; i++) {
			Utilities.verticalSwipe(driver);
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editDownloadsMystuff, 5))
			downloadpagev2.editDownloadsMystuff.click();
		else
			BasePageV2.reportFail("Edit Download Button not displayed");

		List<String> editDownloadUI = new ArrayList<String>();
		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editDownloadPageheader, 5)) {

			test.log(LogStatus.INFO, "Header text matching with expected result");
			editDownloadUI.add("Displayed");
			editDownloadUI.add("Match");

		} else
			BasePageV2.reportFail("Edit Downloads page is not displayed");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editdownloadTitle, 2)) {
			test.log(LogStatus.INFO, "Title not displayed for the content in Edit download page is: "
					+ downloadpagev2.editdownloadTitle.getText());
			editDownloadUI.add("Displayed");
		} else
			BasePageV2.reportFail("Title is not displayed in Edit downloads page");
		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editdownloadDescription, 5)) {
			test.log(LogStatus.INFO, "Episode Title and Size displayed for the content in Edit download page is: "
					+ downloadpagev2.editdownloadDescription.getText());
			editDownloadUI.add("Displayed");
		} else
			BasePageV2.reportFail("Episode details is not displayed");

		if (editDownloadUI.contains("Displayed") && !editDownloadUI.contains("Not Displayed")) {
			test.log(LogStatus.INFO,
					"Meta data displayed properly for the Episode card displayed under 'Download Episodes Page'");
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is pass");
			homepagev2.smokeresults(testCase4, rowno4, "Pass");
		} else {
			test.log(LogStatus.INFO,
					"Meta data is not displayed properly for the Episode card displayed under 'Download Episodes Page'");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
			homepagev2.smokeresults(testCase4, rowno4, "Fail");
		}
		if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteDownload, 5)) {
			test.log(LogStatus.INFO, "Delete Download Button is displayed");
			editDownloadUI.add("Displayed");
			downloadpagev2.deleteDownload.click();
		} else {
			test.log(LogStatus.INFO, "Delete Download Button is not displayed");
			editDownloadUI.add("Not Displayed");
			// BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteTitle, 5)) {
			String deleteText = downloadpagev2.deleteTitle.getText();
			if (deleteText.contains("(") && deleteText.contains(")")) {
				test.log(LogStatus.INFO, "Content selected for delete is: " + deleteText);
				test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Pass");
				homepagev2.smokeresults(testCase5, rowno5, "Pass");

				test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is Pass");
				homepagev2.smokeresults(testCase6, rowno6, "Pass");
				editDownloadUI.add("Displayed");

			} else {
				test.log(LogStatus.INFO, "Content selected for delete is not displayed: ");
				test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
				homepagev2.smokeresults(testCase5, rowno5, "Pass");

				test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
				homepagev2.smokeresults(testCase6, rowno6, "Pass");
				BasePageV2.takeScreenshot();
				editDownloadUI.add("Not Displayed");
			}
		} else {
			test.log(LogStatus.INFO, "Content selected for delete is not displayed: ");
			editDownloadUI.add("Not Displayed");
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteDownload, 2)) {
			test.log(LogStatus.INFO, "Clicking on (X) icon to check auto hidden functionality of 'Delete CTA' Button");
			downloadpagev2.deleteDownload.click();
		} else
			BasePageV2.reportFail("Unable to Click on (X) icon");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteTitle, 2)) {
			test.log(LogStatus.INFO, "Delete button not hided Automatically afer clicking on (X) icon");
			test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " is Fail");
			homepagev2.smokeresults(testCase7, rowno7, "Fail");
		} else {
			test.log(LogStatus.INFO, "Delete button hided Automatically afer clicking on (X) icon");
			test.log(LogStatus.PASS, "Test Case: " + testCase7 + " is Pass");
			homepagev2.smokeresults(testCase7, rowno7, "Pass");
		}

		if (editDownloadUI.contains("Displayed") && editDownloadUI.contains("Match")
				&& !editDownloadUI.contains("Not Displayed") && !editDownloadUI.contains("Not Match")) {
			test.log(LogStatus.INFO, "'Edit Downloads' page is displayed according to the design");
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
			homepagev2.smokeresults(testCase3, rowno3, "Pass");
		} else {
			test.log(LogStatus.INFO, "'Edit Downloads' page is not displayed according to the design");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			homepagev2.smokeresults(testCase3, rowno3, "Fail");
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteDownload, 2))
			downloadpagev2.deleteDownload.click();
		else
			test.log(LogStatus.INFO, "'Delete Download icon (X)' not displayed");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteTitle, 2))
			downloadpagev2.deleteTitle.click();
		else
			test.log(LogStatus.INFO, "'Delete CTA' not displayed");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.ConfirmDeleteDownload, 2))
			downloadpagev2.ConfirmDeleteDownload.click();
		else
			test.log(LogStatus.INFO, "'Confirmation Popup' to delete download not displayed");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.BackButtonDownloadPage, 2)) {
			test.log(LogStatus.INFO, "Clicking on 'Back Button' in 'Edit Downloads' page to check navigation");
			downloadpagev2.BackButtonDownloadPage.click();
		} else
			BasePageV2.reportFail("Back button not displayed in 'Edit Downloads' page");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 2)) {
			test.log(LogStatus.INFO,
					"Tapping on Back button in 'Edit Downloads' is navigated to Downloads tab in Mystuff page");
			test.log(LogStatus.PASS, "Test Case: " + testCase8 + " is Pass");
			homepagev2.smokeresults(testCase8, rowno8, "Pass");
		} else {
			test.log(LogStatus.INFO,
					"Tapping on Back button in 'Edit Downloads' is navigated to Downloads tab in Mystuff page");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase8 + " is Fail");
			homepagev2.smokeresults(testCase8, rowno8, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
