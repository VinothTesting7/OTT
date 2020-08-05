// Author: Karthik

// Modified: Tanisha

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

public class SearchFunctionalityTabHilightingTest extends BaseTestV2 {
	String testName = "SearchFunctionalityTabHilightingTest";
	
	// VK_457 Verify search results page as soon as user starts typing data in search box
	String testCase = "Verify search results page as soon as user starts typing data in search box";
	int err457=0;
	
	//VK_458 Verify the tabs UI post searching any show/Episode/Audio/Book
	String testCase1 = "Verify the tabs UI post searching any show/Episode/Audio/Book";
	int err458=0;
	
	//VK_459 Verify the tab fucntionality in search results 
	String testCase2 = "Verify the tab fucntionality in search results";
	int err459=0;
	
	//VK_460 Verify the tab functionality in search results screen when if any one of the tab does not have data
	String testCase3 = "Verify the tab functionality in search results screen when if any one of the tab does not have data";
	
	//VK_464 Verify the search results page UI when no results found
	String testCase4 = "Verify the search results page UI when no results found";
	
	
	String defaulttextInsidesearchtextfield = "Start typing..";

	@Test(dataProvider = "getData")
	public void SearchFunctionalityTabHilighting(Hashtable<String, String> data) throws Exception {

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		// Clicking on Search icon
		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5))
			homepagev2.search.click();
		else
			test.log(LogStatus.FAIL, "Seach icon not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.searchTextBox, 2)) {
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.sendKeys("p");
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Search Text Box Not displayed");
		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.searchClear, 5))
			test.log(LogStatus.INFO, "Search page 'Close' button is displayed");
		else
			test.log(LogStatus.FAIL, "Search Close button is not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.AllTab, 2)) {
			if (homepagev2.AllTab.isSelected())
				test.log(LogStatus.INFO, "'All Tab' displayed and selected by default");
			else {
				test.log(LogStatus.FAIL, "'All Tab' not selected by default");
				err457++;
				err458++;
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.plalistcontentTitle, 10)) {
				test.log(LogStatus.INFO, "Results started populating as soon first character entered");
			}
			else {
				test.log(LogStatus.FAIL, "Results not populated");
				err457++;
				err458++;
				BasePageV2.takeScreenshot();
			}
		} 
		else {
			test.log(LogStatus.FAIL, "'All Tab' not displayed");
			err457++;
			err458++;
		}
		List<String> results = new ArrayList<String>();
		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.watchTab, 5)) {
			test.log(LogStatus.INFO, "'Watch Tab' is displayed");
			test.log(LogStatus.INFO, "Clicking on watch tab");
			homepagev2.watchTab.click();
			try {
				if (homepagev2.watchTab.isSelected())
					test.log(LogStatus.INFO, "'Watch Tab' selected after clicking");
				else {
					test.log(LogStatus.FAIL, "'Watch Tab' not selected after clicking");
					err459++;
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "'Watch Tab' not selected after clicking");
				err459++;
			}

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.plalistcontentTitle, 10)) {
				test.log(LogStatus.INFO, "Results displayed under Watch tab");
				results.add("Displayed");
			}

			else {
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.emptyResult, 8)) {
					results.add(homepagev2.emptyResult.getText());
				} else {
					test.log(LogStatus.INFO, "Results not displayed under Watch tab");
					BasePageV2.takeScreenshot();
					results.add("Not Displayed");
				}

			}
		}
		else {
			test.log(LogStatus.FAIL, "'Watch Tab' not displayed");
			err457++;
			err458++;
			err459++;
		}

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.BookTab, 5)) {
			test.log(LogStatus.INFO, "'Book Tab' is displayed");
			test.log(LogStatus.INFO, "Clicking on book tab");
			homepagev2.BookTab.click();
			try {
				if (homepagev2.BookTab.isSelected())
					test.log(LogStatus.INFO, "'Book Tab' selected after clicking");
				else {
					test.log(LogStatus.FAIL, "'Book Tab' not selected after clicking");
					err459++;
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "'Book Tab' not selected after clicking");
				err459++;
			}

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.plalistcontentTitle, 10)) {
				test.log(LogStatus.INFO, "Results displayed under Book tab");
				results.add("Displayed");
			}

			else {

				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.emptyResult, 7)) {
					results.add(homepagev2.emptyResult.getText());
				} else {
					test.log(LogStatus.INFO, "Results not displayed");
					BasePageV2.takeScreenshot();
					results.add("Not Displayed");
				}

			}
		} 
		else {
			test.log(LogStatus.FAIL, "'Book Tab' not displayed");
			err457++;
			err458++;
			err459++;
		}

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.ListenTab, 5)) {
			test.log(LogStatus.INFO, "'Listen Tab' is displayed");
			test.log(LogStatus.INFO, "Clicking on Listen tab");
			homepagev2.ListenTab.click();
			try {
				if (homepagev2.ListenTab.isSelected())
					test.log(LogStatus.INFO, "'Listen Tab' selected after clicking");
				else {
					test.log(LogStatus.FAIL, "'Listen Tab' not selected after clicking");
					err459++;
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "'Listen Tab' not selected after clicking");
				err459++;
			}

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.plalistcontentTitle, 10)) {
				test.log(LogStatus.INFO, "Results displayed under Listen tab is selected");
				results.add("Displayed");
			}
			else {

				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.emptyResult, 7)) {
					results.add(homepagev2.emptyResult.getText());
				} else {
					test.log(LogStatus.INFO, "Results not displayed");
					BasePageV2.takeScreenshot();
					results.add("Not Displayed");
				}

			}
		} 
		else {
			test.log(LogStatus.FAIL, "'Listen Tab' not displayed");
			err457++;
			err458++;
		}
	    if(err457==0) {
	    	test.log(LogStatus.PASS, "Verify search results page as soon as user starts typing data in search box is PASS");
			if(!Utilities.setResultsKids("VK_457", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify search results page as soon as user starts typing data in search box is FAIL");
			if(!Utilities.setResultsKids("VK_457", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
	    if(err458==0) {
	    	test.log(LogStatus.PASS, "Verify the tabs UI post searching any show/Episode/Audio/Book is PASS");
			if(!Utilities.setResultsKids("VK_458", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the tabs UI post searching any show/Episode/Audio/Book is FAIL");
			if(!Utilities.setResultsKids("VK_458", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
	    if(err459==0) {
	    	test.log(LogStatus.PASS, "Verify the tab fucntionality in search results is PASS");
			if(!Utilities.setResultsKids("VK_459", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the tab fucntionality in search results is FAIL");
			if(!Utilities.setResultsKids("VK_459", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

		if (results.contains("NO RESULTS")) {
			//BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "TestCase: " + testCase3 + " is Pass");
			if(!Utilities.setResultsKids("VK_460", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		} else {
			test.log(LogStatus.WARNING, "Results got populated in all tabs, none of the tab is empty, hence TC is Unable to Test");
			test.log(LogStatus.WARNING, "TestCase: " + testCase3 + " is Unable to Test");
			if(!Utilities.setResultsKids("VK_460", "Unable to Test")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

		String ExpectedSearchError = "NO RESULTS";
		List<String> expectederror = new ArrayList<String>();
		List<String> Actualerror = new ArrayList<String>();

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.searchClear, 5))
			homepagev2.searchClear.click();
		else
			test.log(LogStatus.FAIL, "'Search Close' button not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 2)) {
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.sendKeys("&&&sd#@");
			try {
				driver.hideKeyboard();
			} catch (Exception e) {

			}
		} else
			test.log(LogStatus.FAIL, "Search Text Box not displayed");
		List<String> tabdisplay = new ArrayList<String>();
		

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.emptyResult, 5)) {
			String actualErrorMsg = homepagev2.emptyResult.getText();
			Actualerror.add(actualErrorMsg);
			expectederror.add(ExpectedSearchError);
			test.log(LogStatus.INFO, "Error Message displayed in 'Listen Tab' is: " + actualErrorMsg);
			test.log(LogStatus.INFO, "Expected Error Message is: " + ExpectedSearchError);
			if (actualErrorMsg.equalsIgnoreCase(ExpectedSearchError)) {
				test.log(LogStatus.INFO, "Actual and Expected Error messages are matching in Listen Tab");
				tabdisplay.add("Displayed");
			}
			else {
				test.log(LogStatus.FAIL, "No Results Error not displayed when searched by Invalid String");
				tabdisplay.add("Not Displayed");
			}

		} else{
			test.log(LogStatus.FAIL, "No Results Error not displayed when searched by Invalid String");
			tabdisplay.add("Not Displayed");
		}
		
		if(Utilities.explicitWaitVisibleNew(driver, homepagev2.surprisemeBtn, 15)) {
			test.log(LogStatus.INFO, "Surprise me button is displayed");
			tabdisplay.add("Displayed");
		}
		else{
			test.log(LogStatus.FAIL, "Surprise me button is not displayed");
			tabdisplay.add("Not Displayed");
		}

		String trayName = "POPULAR SEARCH";

		String xp = "//android.widget.TextView[contains(@text,'" + trayName + "')]";

		try {
			WebElement PopularSearchTray = driver.findElement(By.xpath(xp));
			if (Utilities.explicitWaitVisibleNew(driver, PopularSearchTray, 10))
			{
				test.log(LogStatus.INFO, "popular search section is displayed");
				tabdisplay.add("Displayed");
			} else {
				test.log(LogStatus.FAIL, "popular search section is not displayed");
				tabdisplay.add("Not Displayed");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (Actualerror.equals(expectederror) && !tabdisplay.contains("Not Displayed")) {
			BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Verify Search page UI post performing search functionality(basically 2nd time onwards) is Pass");
			if(!Utilities.setResultsKids("VK_445", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		} else {
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Verify Search page UI post performing search functionality(basically 2nd time onwards) is Fail");
			if(!Utilities.setResultsKids("VK_445", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
