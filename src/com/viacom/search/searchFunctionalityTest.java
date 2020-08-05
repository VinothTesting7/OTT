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
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;



public class searchFunctionalityTest extends BaseTestV2 {
	String testName = "searchFunctionalityTest";
	String testCase = "'Verify Search page UI when landing on the page for the 1st time'";
	String testCase1 = "'Verify Search page UI post performing search functionality(basically 2nd time onwards')";
	String testCase2 = "'Verify 'Surprise Me' button functionality'";
	
	String testCase4 = "'Verify the metadata of each card in recent search'";
	String testCase5 = "'Verify the sorting order for the tiles present under 'Recent Searches' section'";
	String testCase6 = "Verify the functionality by tapping on back arrow";
	String testCase7 = "Verify 'Voice' search UI screen";
	String testCase8 = "Verify 'Cancel' button functionality from Voice search screen";
	String defaulttextInsidesearchtextfield = "Start typing..";
	String searchKey = "BirthofKrishna";
	String searchKey1 = "oddbods";
	String pin = "1111";
	List<String> searchMeta = new ArrayList<String>();

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void searchFunctionality(Hashtable<String, String> data) throws Exception {

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
		SearchPageV2 searchpage = new SearchPageV2(driver, test);
		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

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

		// Validating visibility of Voice button
//		if (Utilities.explicitWaitVisible(driver, homepagev2.voiceBtn, 5)) {
//			test.log(LogStatus.INFO, "'Voice Search' Button is displayed");
//			homepagev2.voiceBtn.click();
//
//			if (Utilities.explicitWaitVisible(driver, searchpage.allowVoicePermission, 10))
//				searchpage.allowVoicePermission.click();
//			else
//				test.log(LogStatus.INFO, "Already allowed device permission for mic access");
//
//			if (Utilities.explicitWaitVisible(driver, homepagev2.voiceBtn, 20))
//				homepagev2.voiceBtn.click();
//			else
//				test.log(LogStatus.INFO, "");
//
//		} else
//			test.log(LogStatus.FAIL, "Voice button is not displayed");
//		try {
//			searchpage.speechCancel.click();
//		} catch (Exception e) {
//			test.log(LogStatus.INFO, "Cancel Button not displayed");
//		}
//
//		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 2)) {
//			test.log(LogStatus.PASS, "Test Case: " + testCase7 + " is Pass");
//			test.log(LogStatus.PASS, "Test Case: " + testCase8 + " is Pass");
//			HomePageV2.smokeresults(testCase7, rowno7, "Pass");
//			HomePageV2.smokeresults(testCase8, rowno8, "Pass");
//		} else {
//			test.log(LogStatus.INFO,
//					"Failed to navigate back to search page after tapping on cancel button in home page");
//			test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " is Fail");
//			test.log(LogStatus.FAIL, "Test Case: " + testCase8 + " is Fail");
//			HomePageV2.smokeresults(testCase7, rowno7, "Fail");
//			HomePageV2.smokeresults(testCase8, rowno8, "Fail");
//		}

		// Validating visibility of Surprise me button
		if (Utilities.explicitWaitVisible(driver, homepagev2.surprisemeBtn, 5)) {
			test.log(LogStatus.INFO, "'Surprise Me' Button is displayed");
		} else
			BasePageV2.reportFail("'Surprise Me' Button is not displayed");

		// BasePageV2.takeScreenshot();
		test.log(LogStatus.PASS, "Test Case: " + testCase + " is passed");
		HomePageV2.smokeresults(testCase, rowno, "Pass");

		// Validating 2nd Test Case
		if (Utilities.explicitWaitVisible(driver, homepagev2.listen, 3))
			homepagev2.listen.click();
		else
			BasePageV2.reportFail("'Listen Button' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.bckbtn, 3))
			homepagev2.bckbtn.click();
		else
			BasePageV2.reportFail("BackButton not displayed in search page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5)) {

			test.log(LogStatus.INFO, "Clicking on Back Button navigated to the page where search icon present");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is Pass");
			homepagev2.smokeresults(testCase6, rowno6, "Pass");

			homepagev2.search.click();
		} else
			BasePageV2.reportFail("'Search icon' not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5)) {
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.sendKeys(searchKey);
			test.log(LogStatus.INFO, "Searched for 'birth of krishna'");
			try {
				driver.hideKeyboard();
			} catch (Exception e1) {
			}

			try {
				WebElement searchcontent = driver.findElement(By.id("com.viacom18.vootkids:id/grid_title"));
				if (Utilities.explicitWaitVisible(driver, searchcontent, 5)) {
					System.out.println("Search result displayed");

				} else
					System.out.println("Search result not displayed");
			} catch (Exception e1) {

			}

			

			if (Utilities.explicitWaitVisible(driver, homepagev2.contentDuration, 2)) {
				test.log(LogStatus.INFO, "Content duration displayed: " + homepagev2.contentDuration.getText());
				searchMeta.add("Pass");
			}
			else {
				test.log(LogStatus.FAIL,"Content duration not displayed");
				searchMeta.add("Fail");
			}
				

			if (Utilities.explicitWaitVisible(driver, homepagev2.contentTitle, 2))
			{
				test.log(LogStatus.INFO, "Content Title displayed: " + homepagev2.contentTitle.getText());
				searchMeta.add("Pass");
			}
			else {
				test.log(LogStatus.FAIL,"Content Title not displayed");
				searchMeta.add("Fail");
			}
				

			if (Utilities.explicitWaitVisible(driver, homepagev2.EpnumAndDescription, 2)) {
				test.log(LogStatus.INFO, "Content Episode number and Description displayed: "
						+ homepagev2.EpnumAndDescription.getText());
				searchMeta.add("Pass");
			}
			else {
				test.log(LogStatus.FAIL,"Content Description not displayed");
				searchMeta.add("Fail");
			}
				

			if (Utilities.explicitWaitVisible(driver, homepagev2.contentitemCategory, 2)) {
				test.log(LogStatus.INFO, "Content Category displayed");
				searchMeta.add("Pass");
			}
			else{
				test.log(LogStatus.FAIL,"Content Category not displayed");
				searchMeta.add("Fail");
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.searchClear, 2)) {
				test.log(LogStatus.INFO, "Search page close button displayed");
				searchMeta.add("Pass");
			}
			else {
				test.log(LogStatus.FAIL,"Search page close button not displayed");
				searchMeta.add("Fail");
			}
				
			
			if(searchMeta.contains("Pass") && !searchMeta.contains("Fail")) {
				test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Passed");
				HomePageV2.smokeresults(testCase4, rowno4, "Pass");
			}
			else {
				test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
				BasePageV2.takeScreenshot();
				HomePageV2.smokeresults(testCase4, rowno4, "Fail");
			}

			// BasePageV2.takeScreenshot();
			
		} else
			BasePageV2.reportFail("'Search text field' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.bckbtn, 3))
			homepagev2.bckbtn.click();
		else
			BasePageV2.reportFail("BackButton not displayed in search page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5)) {
			test.log(LogStatus.INFO, "Clicking on search");
			homepagev2.search.click();
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.listen, 3))
			homepagev2.listen.click();
		else
			BasePageV2.reportFail("'Listen Button' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.bckbtn, 3))
			homepagev2.bckbtn.click();
		else
			BasePageV2.reportFail("BackButton not displayed in search page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5)) {
			test.log(LogStatus.INFO, "Clicking on search");
			homepagev2.search.click();
		} else
			BasePageV2.reportFail("'Search icon' not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5)) {
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			homepagev2.searchTextBox.clear();
			homepagev2.searchTextBox.sendKeys(searchKey1);
			test.log(LogStatus.INFO, "Searched for 'oddbods'");
			try {
				driver.hideKeyboard();
			} catch (Exception e1) {
			}
			WebElement searchcontent = driver.findElement(By.id("com.viacom18.vootkids:id/grid_title"));
			if (Utilities.explicitWaitVisible(driver, searchcontent, 5)) {
				System.out.println("Search result displayed");

			} else
				System.out.println("Search result not displayed");
			String nextsearchTitle = driver.findElement(By.id("com.viacom18.vootkids:id/grid_title")).getText();
			test.log(LogStatus.INFO, "Actual Recent search title: " + nextsearchTitle);
			test.log(LogStatus.INFO, "Recent search title: " + searchKey1);
			test.log(LogStatus.INFO, "Previous searched data: " + searchKey);
			if (nextsearchTitle.equalsIgnoreCase(searchKey)) {
				test.log(LogStatus.INFO, "Previous Search dislayed first");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
				HomePageV2.smokeresults(testCase5, rowno5, "Fail");
			} else {
				test.log(LogStatus.INFO, "Recent  Search displayed first");
				if (Utilities.explicitWaitVisible(driver, homepagev2.contentDuration, 2))
					test.log(LogStatus.INFO, "Content duration displayed: " + homepagev2.contentDuration.getText());
				else
					BasePageV2.reportFail("Content duration not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.contentTitle, 2))
					test.log(LogStatus.INFO, "Content Title displayed: " + homepagev2.contentTitle.getText());
				else
					BasePageV2.reportFail("Content Title not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.EpnumAndDescription, 2))
					test.log(LogStatus.INFO, "Content Episode number and Description displayed: "
							+ homepagev2.EpnumAndDescription.getText());
				else
					BasePageV2.reportFail("Content Description not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.contentitemCategory, 2))
					test.log(LogStatus.INFO, "Content Category displayed");
				else
					BasePageV2.reportFail("Content Category not displayed");

				if (Utilities.explicitWaitVisible(driver, homepagev2.searchClear, 2))
					test.log(LogStatus.INFO, "Search page close button displayed");
				else
					BasePageV2.reportFail("Search page close button not displayed");

				// BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Passed");
				HomePageV2.smokeresults(testCase5, rowno5, "Pass");
			}

		} else
			BasePageV2.reportFail("Search text box not displayed");

		test.log(LogStatus.INFO, "Closing search page");

		Utilities.verticalSwipeDown(driver, homepagev2.searchClear);
		if (Utilities.explicitWaitVisible(driver, homepagev2.searchClear, 2))
			homepagev2.searchClear.click();
		else
			BasePageV2.reportFail("Unable to close search page");

		Utilities.verticalSwipeDown(driver, homepagev2.bckbtn);

		if (Utilities.explicitWaitVisible(driver, homepagev2.bckbtn, 2))
			homepagev2.bckbtn.click();
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.search, 5))

		{
			test.log(LogStatus.INFO, "Clicking on search");
			homepagev2.search.click();
		} else
			BasePageV2.reportFail("Unable to click search page");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5)) {
			test.log(LogStatus.INFO, "'Search text field' is displayed");
			String defaultsearchtext = homepagev2.searchTextBox.getText();
			test.log(LogStatus.INFO, "Actual text to display: " + defaultsearchtext);
			// Validating visibility of Voice button
			if (Utilities.explicitWaitVisible(driver, homepagev2.voiceBtn, 5))
				test.log(LogStatus.INFO, "'Voice Search' Button is displayed");
			else
				BasePageV2.reportFail("'Voice Search' Button is not displayed");

			// Validating visibility of Surprise me button
			if (Utilities.explicitWaitVisible(driver, homepagev2.surprisemeBtn, 5)) {
				test.log(LogStatus.INFO, "'Surprise Me' Button is displayed");
				homepagev2.surprisemeBtn.click();

			} else
				BasePageV2.reportFail("'Surprise Me' Button is not displayed");

			if (Utilities.explicitWaitVisible(driver, homepagev2.videoPlayerTitle, 25)) {
				String title = homepagev2.videoPlayerTitle.getText();
				test.log(LogStatus.INFO, "Title of currently playing video is: " + title);
				test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is passed");
				HomePageV2.smokeresults(testCase2, rowno2, "Pass");
				driver.navigate().back();
			} else {

				test.log(LogStatus.INFO, "Title not displayed for currently playing video");
				test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
				HomePageV2.smokeresults(testCase2, rowno2, "Fail");
			}

		}
		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is passed");
		HomePageV2.smokeresults(testCase1, rowno1, "Pass");
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
