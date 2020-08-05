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
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;

public class EbooksPreviewandDownloadFunctionalityTest extends BaseTestV2 {
	String testName = "EbooksPreviewandDownloadFunctionalityTest";
	String testCase = "'Verify Download icon UI while downloading a content'";
	String testCase1 = "'Verify Download icon UI post complete download'";
	String testCase2 = "'Verify the favourites functionality when user deselects the favourite button (Already Favourited)'";
	String testCase3 = "'Verify the type of cards to be displayed under any configurable tray(ex: Related Shows)'";
	String testCase4 = "'Verify Download icon UI when there are in-progress contents'";
	String testCase5 = "'	'";

	String tabName = "";
	int totalNumCards;
	String relatedTray = "";
	Response EbookTitle;
	String EbookMainTitle = "";

	// Author: Karthik

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "getData")
	public void EbooksPreviewandDownloadFunctionality(Hashtable<String, String> data) throws Exception {

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

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		EbooksPageV2 ebookspage = new EbooksPageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);
		try {
			HomePageV2.tabClick("Read");
		} catch (Exception e) {
			BasePageV2.reportFail("'Read' tab not displayed");
		}

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("EbooksTab")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);
				// String ApiName = "EbooksTab";
				Response respTab = Utilities.requestUtility(ApiTab);
				String tabNameApi = respTab.jsonPath().get("assets[1].title");
				tabName = tabNameApi.toUpperCase();
				totalNumCards = respTab.jsonPath().get("assets[1].totalItems");
			} else
				System.out.println("Unable to fetch tab from API");
		}
		System.out.println(tabName);
		String tabend = "//android.widget.TextView[@text='" + tabName + "']";

		try {
			Utilities.verticalSwipe(driver, tabend);
		} catch (Exception e) {
		}

		int rows1 = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows1);
		// List<String> cardTitles = new ArrayList<String>();

		for (int rNum = 1; rNum <= rows1; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("NewBooks")) {
				String ApiEbook = xls.getCellData("Api", "Url", rNum);

				Response EbookTitle = Utilities.requestUtility(ApiEbook);
				String cardTitle = EbookTitle.jsonPath().get("assets.items[1].title");
				System.out.println("Displayed Card " + cardTitle);
				String cardend = "//android.widget.TextView[contains(@text,'" + cardTitle + "')]";
				try {
					Utilities.verticalSwipe(driver, cardend);
				} catch (Exception e) {
				}

				try {
					WebElement card = driver.findElement(By.xpath(cardend));
					if (Utilities.explicitWaitVisible(driver, card, 2))
						card.click();
					else
						BasePageV2.reportFail("Ebook not displayed");
				} catch (Exception e) {
					Utilities.verticalSwipe(driver, cardend);
					try {
						WebElement card = driver.findElement(By.xpath(cardend));
						if (Utilities.explicitWaitVisible(driver, card, 2))
							card.click();
						else
							BasePageV2.reportFail("Ebook not displayed");
					} catch (Exception e1) {
						BasePageV2.reportFail("Ebook not displayed");
					}
				}
			} else
				System.out.println("Unable to fetch tab from API");
		}

		if (Utilities.explicitWaitVisible(driver, ebookspage.previewbutton, 25)) {
			test.log(LogStatus.INFO, "Preview button is displayed before downloading a content");
		} else
			test.log(LogStatus.INFO, "Preview button is not displayed in Ebook detai page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 1)) {
			EbookMainTitle = homepagev2.audTitle.getText();
			test.log(LogStatus.INFO, "Navigated to detail page of book: " + EbookMainTitle);
		} else
			BasePageV2.reportFail("Ebook Title not displayed");

		Utilities.verticalSwipe(driver, downloadpagev2.downloadIcon);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadIcon, 1))
			downloadpagev2.downloadIcon.click();
		else
			BasePageV2.reportFail("Download Icon is not displayed");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadingEbook, 1)) {
			test.log(LogStatus.INFO, "Ebook download is in Progress text displayed");
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is pass");
			HomePageV2.smokeresults(testCase4, rowno4, "Pass");

		} else {
			test.log(LogStatus.INFO, "Ebook download is in Progress is not displayed");
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}

		next: for (int i = 1; i <= 100; i++) {
			Thread.sleep(1500);
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.DownloadedStatus, 5)) {
				test.log(LogStatus.INFO, "Content downloaded successfully");
				test.log(LogStatus.INFO, "Download Status displayed as: " + downloadpagev2.DownloadedStatus.getText());
				// BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is passed");
				HomePageV2.smokeresults(testCase1, rowno1, "Pass");
				break;
			} else
				continue next;
		}

		Utilities.verticalSwipeDown(driver, homepagev2.playAudiobookbutton);

		if (Utilities.explicitWaitVisible(driver, ebookspage.Readbutton, 5)) {
			test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Pass");
			HomePageV2.smokeresults(testCase5, rowno5, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase5, rowno5, "Fail");
		}

		Utilities.verticalSwipe(driver, downloadpagev2.DownloadedStatus);

		for (int rNum = 1; rNum <= rows1; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("RelatedEbookTray")) {
				String ApiRelatedTray = xls.getCellData("Api", "Url", rNum);
				Response EbookTitle = Utilities.requestUtility(ApiRelatedTray);
				String realted = EbookTitle.jsonPath().get("assets[1].title");
				relatedTray = realted.toUpperCase();
			} else
				System.out.println("API URL not yet found");
		}

		String relatedEnd = "//android.widget.TextView[contains(@text,'" + relatedTray + "')]";
		Utilities.verticalSwipe(driver, relatedEnd);

		List<String> ebooks = new ArrayList<String>();
		for (int rNum = 1; rNum <= rows1; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("RelatedEbookTrayContent")) {
				String ApiRelatedTray = xls.getCellData("Api", "Url", rNum);
				EbookTitle = Utilities.requestUtility(ApiRelatedTray);
				totalNumCards = EbookTitle.jsonPath().get("assets.totalItems");
				test.log(LogStatus.INFO,
						"Total Number of contents displayed under tray " + relatedTray + " is: " + totalNumCards);
			}

			else
				System.out.println("API yest to display");
		}

		for (int i = 0; i < totalNumCards; i++) {
			String content = EbookTitle.jsonPath().get("assets.items[" + i + "].title");
			int mediaType = EbookTitle.jsonPath().get("assets.items[" + i + "].mediaType");

			String relateEnd = "//android.widget.TextView[contains(@text,'" + content + "')]";

			try {
				Utilities.verticalSwipe(driver, relateEnd);
			} catch (Exception e) {
			}

			try {
				WebElement contentrelated = driver
						.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + content + "')]"));
				String contentName = contentrelated.getText();
				test.log(LogStatus.INFO,
						"Content " + (i + 1) + " displayed under " + relatedTray + " tray is: " + contentName);
				if (mediaType == 900) {
					test.log(LogStatus.INFO,
							"Content " + (i + 1) + " displayed under " + relatedTray + " is of type: Ebook");
					ebooks.add("Ebook");
				} else {
					test.log(LogStatus.INFO,
							"Content " + (i + 1) + " displayed under " + relatedTray + " is not of type: Ebook");
					ebooks.add("Not Ebook");
				}
			} catch (Exception e) {
			}
		}

		if (ebooks.contains("Ebook") && !ebooks.contains("Not Ebook")) {
			test.log(LogStatus.INFO, "Contents displayed under " + relatedTray + " is of type Ebook");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
			HomePageV2.smokeresults(testCase3, rowno3, "Pass");
		} else {
			test.log(LogStatus.INFO, "Contents displayed under " + relatedTray + " is not of type Ebook");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			HomePageV2.smokeresults(testCase3, rowno3, "Fail");
		}

		Utilities.verticalSwipeDown(driver, ebookspage.favourite);

		if (Utilities.explicitWaitVisible(driver, homepagev2.notfavouritedetailpage, 2)) {
			homepagev2.notfavouritedetailpage.click();
			if (Utilities.explicitWaitVisible(driver, homepagev2.favouritedetailpage, 2))
				test.log(LogStatus.INFO, "Added to favourite");
			else
				BasePageV2.reportFail("Failed to add to favourites");
		} else if (Utilities.explicitWaitVisible(driver, homepagev2.favouritedetailpage, 2))
			test.log(LogStatus.INFO, "Already added to favourite");
		else
			BasePageV2.reportFail("Unable to perform favourite functionality");

		test.log(LogStatus.INFO, "Killing the Application to validate: " + testCase2);
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		HomePageV2.tabClick("My Stuff");

		String end = "//android.widget.TextView[contains(@text,'FAVOURITES')]";
		try {
			Utilities.verticalSwipe(driver, end);
		} catch (Exception e) {
		}

		String trayname = "FAVOURITES";
		try {

			String xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and contains(@text,'"
					+ trayname
					+ "')]//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";

			Utilities.verticalSwipe(driver, xpath);
			List<WebElement> titles = driver.findElements(By.xpath(xpath));
			int size = titles.size();
			if (size > 0) {
				// Utilities.verticalSwipe(driver,titles.get(size-1));
				titles.get(size - 1).click();
			} else
				BasePageV2.reportFail("No contents under the tray - " + trayname);

		} catch (Exception e) {
			BasePageV2.reportFail("Content not displayed under favourites");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.favouritedetailpage, 2)) {
			homepagev2.favouritedetailpage.click();
			if (Utilities.explicitWaitVisible(driver, homepagev2.notfavouritedetailpage, 2))
				test.log(LogStatus.INFO, "Removed from favourite");
			else
				BasePageV2.reportFail("Failed to Remove from favourites");
		} else if (Utilities.explicitWaitVisible(driver, homepagev2.notfavouritedetailpage, 2))
			BasePageV2.reportFail("Favourite Icon not highlighted even though content displayed in favourite section");
		else
			BasePageV2.reportFail("Unable to perform favourite functionality");

		test.log(LogStatus.INFO, "Killing the Application to validate: " + testCase2);
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		HomePageV2.tabClick("My Stuff");

		String end1 = "//android.widget.TextView[contains(@text,'FAVOURITES')]";
		try {
			Utilities.verticalSwipe(driver, end1);
		} catch (Exception e) {
		}

		// String favend1 = "//android.widget.TextView[contains(@text,'" +
		// EbookMainTitle + "')]";
		for (int i = 0; i < 2; i++) {
			Utilities.verticalSwipe(driver);
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.emptyResult, 1)) {

			test.log(LogStatus.INFO,
					"Favourited content not displayed under favourite section after removed from Favourites");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
			// favcontent.click();
		} else {
			test.log(LogStatus.INFO,
					"Favourited content displayed under favourite section after removed from Favourites");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			HomePageV2.smokeresults(testCase2, rowno2, "Fail");
		}

		downloadpagev2.deleteDownload();

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
