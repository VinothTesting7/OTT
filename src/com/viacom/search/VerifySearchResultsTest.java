package com.viacom.search;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

@SuppressWarnings("deprecation")
public class VerifySearchResultsTest extends BaseTestV2 {
	int k;
	String defaulttextInsidesearchtextfield = "Start typing..";
	String testName = "VerifySearchResultsTest";
	String testCase = "Verify the number of results to be shown in search result page";
	String testCase1 = "Verfiy the availibility of See All button post searching any character/Episode/Audio/Book";
	String testCase2 = "Verify the lazy load fucntionality when there are badges of 10  post searching any content in search page UI";
	String testCase3 = "Verify the virtual keypad dismissal";

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void searchResults(Hashtable<String, String> data) throws Exception {

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
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
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5)) {
			System.out.println("Already signed in");
		} else
			HomePageV2.login("9483099120", "karu5278");

		// Clicking on Search icon
		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5)) {
			homepagev2.search.click();
		} else
			BasePageV2.reportFail("Seach icon not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5)) {
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			String defaultsearchtext = homepagev2.searchTextBox.getText();

			if (defaultsearchtext.equalsIgnoreCase(defaulttextInsidesearchtextfield)) {
				test.log(LogStatus.INFO, "Default Search text displayed is: " + defaultsearchtext);
				test.log(LogStatus.INFO, "Actual text to display: " + defaulttextInsidesearchtextfield);
			} else
				test.log(LogStatus.INFO, "Default search text not matched with Actual Search text");

		} else
			BasePageV2.reportFail("'Search text field' is not displayed");

		// Virtual Keyboard visibility and dismissal
		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 2)) {
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.click();

			if (driver.isKeyboardShown()) {
				test.log(LogStatus.INFO, "Virtual Keyboard displayed on clicking on search field");
				// BasePageV2.takeScreenshot();
			} else
				BasePageV2.reportFail("Virtual Keyboard not displayed on clicking on search field");

			test.log(LogStatus.INFO, "Tapping on Enter key");
			driver.pressKeyCode(AndroidKeyCode.ENTER);
			// homepagev2.searchTextBox.sendKeys(Keys.ENTER);

			if (driver.isKeyboardShown()) {
				test.log(LogStatus.INFO, "Virtual Keyboard failed to dismiss after tapping enter key");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
				HomePageV2.smokeresults(testCase3, rowno3, "Fail");
			} else {
				test.log(LogStatus.INFO, "Virtual Keyboard dismissed after tapping enter key");
				// BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is passed");
				HomePageV2.smokeresults(testCase3, rowno3, "Pass");
			}

		} else
			BasePageV2.reportFail("Search Tesxt Field not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 2)) {
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.sendKeys("Birth Of Krishna");
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Search Tesxt Field not displayed");

		List<String> seallVerify = new ArrayList<String>();

		if (Utilities.explicitWaitVisible(driver, homepagev2.AllTab, 2)) {
			homepagev2.AllTab.click();
			if (Utilities.explicitWaitVisible(driver, homepagev2.traySeeAll, 5)) {
				test.log(LogStatus.INFO, "'See All' button displayed for search results in All tab");
				// BasePageV2.takeScreenshot();
				seallVerify.add("Displayed");
			} else {
				test.log(LogStatus.INFO, "'See All' button not displayed for search results in All tab");
				// BasePageV2.takeScreenshot();
				seallVerify.add("Not Displayed");
			}
		} else
			BasePageV2.reportFail("All Tab not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.watchTab, 2)) {
			homepagev2.watchTab.click();
			if (Utilities.explicitWaitVisible(driver, homepagev2.traySeeAll, 5)) {
				test.log(LogStatus.INFO, "'See All' button displayed for search results in watch tab");
				// BasePageV2.takeScreenshot();
				seallVerify.add("Displayed");
			} else {
				test.log(LogStatus.INFO, "'See All' button not displayed for search results in watch tab");
				/// BasePageV2.takeScreenshot();
				seallVerify.add("Not Displayed");
			}
		} else
			BasePageV2.reportFail("Watch Tab not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.BookTab, 2)) {
			homepagev2.BookTab.click();
			if (Utilities.explicitWaitVisible(driver, homepagev2.traySeeAll, 5)) {
				test.log(LogStatus.INFO, "'See All' button displayed for search results in Book tab");
				// BasePageV2.takeScreenshot();
				seallVerify.add("Displayed");

			} else {
				test.log(LogStatus.INFO, "'See All' button not displayed for search results in Book tab");
				// BasePageV2.takeScreenshot();
				seallVerify.add("Not Displayed");
			}
		} else
			BasePageV2.reportFail("Book Tab not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.ListenTab, 2)) {
			homepagev2.ListenTab.click();
			if (Utilities.explicitWaitVisible(driver, homepagev2.traySeeAll, 5)) {
				test.log(LogStatus.INFO, "'See All' button displayed for search results in Listen tab");
				// BasePageV2.takeScreenshot();
				seallVerify.add("Displayed");

			} else {
				test.log(LogStatus.INFO, "'See All' button not displayed for search results in Listen tab");
				/// BasePageV2.takeScreenshot();
				seallVerify.add("Not Displayed");
			}
		} else
			BasePageV2.reportFail("Listen Tab not displayed");

		if (seallVerify.contains("Displayed") && !seallVerify.contains("Not Displayed")) {
			test.log(LogStatus.FAIL, "'See All' button displayed for search results");
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
		} else {
			test.log(LogStatus.INFO, "'See All' button not displayed for search results");
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.AllTab, 2))
			homepagev2.AllTab.click();
		else
			BasePageV2.reportFail("All Tab Not Displayed");

		Utilities.verticalSwipe(driver);
		List<String> LazyLoader = new ArrayList<String>();
		List<String> searchelement = new ArrayList<String>();
		int j = 0;
		int count;
		for (int i = 0; i <= 10; i++) {
			k = 1;
			count = i + 1;
			try {
				WebElement searchresult = driver.findElement(By.xpath("//*[contains(@class,'ViewGroup')][@index='" + i
						+ "']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']"));
				if (i == j + 2) {
					Utilities.verticalSwipe(driver);
				}
				if (Utilities.explicitWaitVisible(driver, searchresult, 1)) {
					String contentdisplayed = searchresult.getText();
					if (!searchelement.contains(contentdisplayed)) {
						searchelement.add(contentdisplayed);

						k++;
					} else {
						System.out.println("Content: " + contentdisplayed + " already verified");
						k++;
					}
					count++;
				} else {
					Utilities.verticalSwipe(driver);
				}
				if (k == 10) {
					if (Utilities.explicitWaitVisible(driver, homepagev2.loadingAnimator, 5)) {
						test.log(LogStatus.INFO, "Lazy loader displayed after: " + k + " after 10 badges");
						LazyLoader.add("Displayed");
						// BasePageV2.takeScreenshot();
					} else {
						test.log(LogStatus.INFO, "Lazy loader not displayed after: " + k + " after 10 badges");
						LazyLoader.add("Not Displayed");
						// BasePageV2.takeScreenshot();
					}

				}

			} catch (Exception e) {
				// int count1 = 0;
				if (j == j + 1) {
					j = 0;
					i = 0;
					Utilities.verticalSwipe(driver);
				} else {
					test.log(LogStatus.INFO, "End of content");
					test.log(LogStatus.INFO, "Number of contents displayed is: " + count);
					test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
					break;
				}
			}
		}

		if (LazyLoader.contains("Displayed")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
		} else if (k < 10) {
			test.log(LogStatus.INFO,
					"Number of contents displayed in search results is less than 10, lazy loader not displayed hence passing the test");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			HomePageV2.smokeresults(testCase2, rowno2, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
