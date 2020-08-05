package com.viacom.search;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Ordering;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class RecentSearchFunctionalityTest extends BaseTestV2 {
	String testName = "RecentSearchFunctionalityTest";

	String testCase = "Verify the number of tiles to be displayed in 'Recent Searches' section";
	String testCase1 = "Verify the sorting order for the tiles present under 'Recent Searches' section";
	String testCase2 = "Verify 'Clear' link functionality";
	String testCase3 = "Verify the functionality of Cross(X) icon present in search box";
	String testCase4 = "'Verify 'Recent Searches' section'";
	// Author: Karthik

	@Test(dataProvider = "getData")
	public void RecentSearchFunctionality(Hashtable<String, String> data) throws Exception {
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
		
		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4, testCase4);



		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		SearchPageV2 searchpage = new SearchPageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		if (Utilities.explicitWaitVisible(driver, searchpage.Search, 5))
			searchpage.Search.click();
		else
			BasePageV2.reportFail("Search tab not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, searchpage.searchTextBox, 1)) {
			searchpage.searchTextBox.clear();
			searchpage.searchTextBox.sendKeys("Motu Patlu");
		}
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}

		Utilities.verticalSwipe(driver, searchpage.searchContent);
		Thread.sleep(2000);
		try {
			Thread.sleep(2000);
			searchpage.searchContent.click();
		} catch (Exception e) {
			BasePageV2.reportFail("Content not displayed");
		}
		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, searchpage.searchClear);
		Thread.sleep(2000);
		if (Utilities.explicitWaitVisible(driver, searchpage.searchClear, 2)) {
			searchpage.searchClear.click();
		} else
			BasePageV2.reportFail("Search Clear button not displayed");

		Utilities.verticalSwipe(driver, searchpage.RecentsearchContents);
		Thread.sleep(2000);
		List<String> desc = new ArrayList<String>();
		List<String> contents = new ArrayList<String>();
		next: for (int i = 0; i < 8; i++) {
			try {

				WebElement contentTitle = driver.findElement(By.xpath(
						"//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='"
								+ i
								+ "']//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/grid_title')]"));
				WebElement contentDescription = driver.findElement(By.xpath(
						"//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='"
								+ i
								+ "']//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/grid_description')]"));

				if (i >= 0 && i < 8) {
					Utilities.verticalSwipe(driver, contentDescription);
					String Title = contentTitle.getText();
					String description = contentDescription.getText();
					desc.add(description);
					contents.add("Displayed");
				} else {
					test.log(LogStatus.INFO, "Number of contents displayed under 'Recent Searches' tab is >8");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
					homepagev2.smokeresults(testCase, rowno, "Fail");
					contents.add(">8");
				}

			} catch (Exception e) {
				Utilities.verticalSwipe(driver);
				try {
					WebElement contentTitle = driver.findElement(By.xpath(
							"//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='"
									+ i
									+ "']//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/grid_title')]"));
					WebElement contentDescription = driver.findElement(By.xpath(
							"//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='"
									+ i
									+ "']//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/grid_description')]"));
					if (Utilities.explicitWaitVisible(driver, contentTitle, 1)
							&& Utilities.explicitWaitVisible(driver, contentDescription, 1)) {
						i = i - 1;
						continue next;
					} else {
						test.log(LogStatus.INFO,
								"Number of contents displayed under 'Recent Searches' tab is: " + (i + 1));
						break;
					}
				} catch (Exception e1) {
					test.log(LogStatus.INFO, "Number of contents displayed under 'Recent Searches' tab is: " + (i));
					break;
				}

			}
		}

		if (contents.contains("Displayed") && !contents.contains(">8")) {

			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		} else {
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}

		boolean sorted = Ordering.natural().isOrdered(desc);
		if (sorted) {
			test.log(LogStatus.INFO, "Contents under 'Recent Searches' tab are sorted");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");

		} else {
			test.log(LogStatus.INFO, "Contents under 'Recent Searches' tab are not sorted");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
		}

		//Utilities.verticalSwipeDown(driver, searchpage.searchClear);
		Utilities.verticalSwipeDown(driver, searchpage.RecentsearchClear);
		
		if (Utilities.explicitWaitVisible(driver, searchpage.RecentsearchClear, 2)) {
			test.log(LogStatus.INFO, "Clearing 'Recent Search Results' by clicking on 'Clear Link'");
			searchpage.RecentsearchClear.click();
		} else
			BasePageV2.reportFail("Unable to Click on 'Clear' link");

		if (Utilities.explicitWaitVisible(driver, searchpage.searchEmpty, 2)) {
			test.log(LogStatus.INFO,
					"Recent Searches got cleared and displaying error message as: " + searchpage.searchEmpty.getText());
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
			homepagev2.smokeresults(testCase2, rowno2, "Pass");
			
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Pass");
			homepagev2.smokeresults(testCase4, rowno4, "Pass");
		} else {
			test.log(LogStatus.INFO, "Recent Searches not cleared");
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			homepagev2.smokeresults(testCase2, rowno2, "Fail");
			
			test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
			homepagev2.smokeresults(testCase4, rowno4, "Fail");
		}

		Utilities.verticalSwipeDown(driver, searchpage.searchTextBox);

		if (Utilities.explicitWaitVisible(driver, searchpage.searchTextBox, 1)) {
			searchpage.searchTextBox.clear();
			searchpage.searchTextBox.sendKeys("Motu Patlu");
		}
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisible(driver, searchpage.AllTab, 2))
			test.log(LogStatus.INFO, "All tab displayed under search page");
		else
			BasePageV2.reportFail("All tab is not displayed under search page");

		if (Utilities.explicitWaitVisible(driver, searchpage.searchClear, 2))
			searchpage.searchClear.click();
		else
			test.log(LogStatus.INFO, "Search clear button not displayed");

		if (Utilities.explicitWaitVisible(driver, searchpage.voiceButton, 5)) {
			test.log(LogStatus.INFO, "Navigated back to Search Landing page");
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
			homepagev2.smokeresults(testCase3, rowno3, "Pass");
		} else {
			test.log(LogStatus.INFO, "Navigated back to Search Landing page");
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			homepagev2.smokeresults(testCase3, rowno3, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
