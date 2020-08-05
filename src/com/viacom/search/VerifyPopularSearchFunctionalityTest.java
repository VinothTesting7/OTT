package com.viacom.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyPopularSearchFunctionalityTest extends BaseTestV2 {
	String testName = "VerifyPopularSearchFunctionalityTest";

	String testCase = "'Verify the navigation from popular search in search page for the 1st time'";
	String testCase1 = "'4-8 tiles should be displayed under 'Popular Searches' section')";
	String testCase2 = "'Verify 'Popular Searches' section UI'";
	String testCase3 = "''";
	String PopularContentCard = "";
	String searchKey = "BirthofKrishna";
	String searchKey1 = "oddbods";
	String showDetailPage = "";
	int totalPopularSearches = 0;
	int mediaType = 0;
	String CotentType = "";
	String feContent = "";

	// Author: Karthik
	@Test(dataProvider = "getData")
	public void VerifyPopularSearchFunctionality(Hashtable<String, String> data) throws Exception {
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
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SearchPageV2 searchpage = new SearchPageV2(driver, test);
		KidsPlayerPageV2 kidspage = new KidsPlayerPageV2(driver, test);
		HomePageV2.login("karthiktskaaru5278@gmail.com", "karu5278");

		if (Utilities.explicitWaitVisible(driver, searchpage.Search, 5)) {
			searchpage.Search.click();
		} else
			BasePageV2.reportFail("Seach icon not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchPage, 2))
			test.log(LogStatus.INFO, "Search Page is displayed");
		else
			BasePageV2.reportFail("Search Page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.searchTextBox, 5))
			test.log(LogStatus.INFO, "'Search text field' is displayed");
		else
			BasePageV2.reportFail("Search Text box is not displayed");

		String trayName = "POPULAR SEARCH";

		String xp = "//android.widget.TextView[contains(@text,'" + trayName + "')]";
		Utilities.verticalSwipe(driver, xp);

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		Map<String, Integer> contentsPopulars = new HashMap<String, Integer>();
		List<String> titles = new ArrayList<String>();
		List<Integer> mediatypes = new ArrayList<Integer>();

		List<String> Resultsdata = new ArrayList<String>();
		for (int rNum = 1; rNum <= rows; rNum++) {

			try {
				String apiname = xls.getCellData("Api", "API Name", rNum);
				if (apiname.equals("Popular Search")) {
					String ApiTab = xls.getCellData("Api", "Url", rNum);

					System.out.println("API Displayed is: " + ApiTab);

					Response respTab = Utilities.requestUtility(ApiTab);
					totalPopularSearches = respTab.jsonPath().get("assets.totalItems");

					for (int i = 0; i < totalPopularSearches; i++) {

						try {
							PopularContentCard = respTab.jsonPath().get("assets.items[" + i + "].title");

							String[] Carddata = PopularContentCard.split(" ");
							feContent = Carddata[0].toString() + " " + Carddata[1].toString();
							titles.add(feContent);
							test.log(LogStatus.INFO, "Content displayed in API: " + PopularContentCard);
							System.out.println("Content Displayed is: " + PopularContentCard);
							String xpath1 = "//android.widget.TextView[contains(@text,'" + feContent + "')]";
							Utilities.verticalSwipe(driver, xpath1);

							try {
								WebElement popularSearch = driver.findElement(By.xpath(xpath1));
								if (Utilities.explicitWaitVisible(driver, popularSearch, 20)) {
									PopularContentCard = popularSearch.getText().trim();
									test.log(LogStatus.INFO, "Content displayed is: " + popularSearch.getText());

									popularSearch.click();

								} else
									BasePageV2.reportFail("Content not displayed in front end");

								if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 20)) {
									showDetailPage = homepagev2.audTitle.getText().trim();
									if (PopularContentCard.trim().contains(showDetailPage.trim()))
										Resultsdata.add("Pass");
									else
										Resultsdata.add("Fail");
								} else if (Utilities.explicitWaitVisible(driver, kidspage.videoPlayer, 20)) {

									kidspage.pauseVideo();

									if (Utilities.explicitWaitVisible(driver, kidspage.playerTitle, 20)) {
										showDetailPage = kidspage.playerTitle.getText().trim();
										if (PopularContentCard.trim().contains(showDetailPage.trim()))
											Resultsdata.add("Pass");
										else
											Resultsdata.add("Fail");
									} else
										BasePageV2.reportFail("Episode title is not displayed on VOD Player");

								} else if (Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 20)) {
									showDetailPage = homepagev2.audioTitle.getText().trim();
									if (PopularContentCard.trim().contains(showDetailPage.trim()))
										Resultsdata.add("Pass");
									else
										Resultsdata.add("Fail");

								}

								else
									test.log(LogStatus.INFO, "Content Title not displayed");
							} catch (Exception e) {
							}

							mediaType = respTab.jsonPath().get("assets.items[" + i + "].mediaType");
							System.out.println("Media Type Displayed is: " + mediaType);
							mediatypes.add(mediaType);
							contentsPopulars.put(PopularContentCard, mediaType);
						} catch (Exception e) {
							System.out.println("************end**************");
						}
						driver.pressKeyCode(AndroidKeyCode.BACK);
					}

				} else
					System.out.println("Unable to fetch tab from API");
			} catch (Exception e) {
			}

		}

		if (totalPopularSearches >= 4 && totalPopularSearches <= 8) {
			BasePageV2.reportPass("Test case: " + testCase1 + " is Pass");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
		}

		if (Resultsdata.contains("Pass") && !Resultsdata.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}

		System.out.println("Titles displaed: " + titles);

		Map<Integer, String> contentoftypeApi = new HashMap<Integer, String>();
		contentoftypeApi.put(415, "EPISODE_TYPE");
		contentoftypeApi.put(416, "AUDIO_TYPE");
		contentoftypeApi.put(418, "TV_SERIES_TYPE");
		contentoftypeApi.put(417, "LINEAR_TYPE");
		contentoftypeApi.put(800, "EBOOK_TYPE");

		List<String> displayed = new ArrayList<String>();
		List<String> ordertypes = new ArrayList<String>();

		ordertypes.add("418");
		ordertypes.add("415");
		ordertypes.add("800");
		ordertypes.add("416");

		System.out.println("Order Types: " + ordertypes);
		int k;
//		for (int i = 0; i < ordertypes.size(); i++) {

		for (int mediadisp : mediatypes) {
			// k = ordertypes.size();
			String orderString = ordertypes.get(0);
			// int order = Integer.parseInt(orderString);
			System.out.println("Order: " + orderString);

			String mediastring = Integer.toString(mediadisp);
			// String key = evalues.getKey();
			// System.out.println("Content displayed in Map: " + key);
			// int value = evalues.getValue();
			// int mediadisp = contentsPopulars.get(key);
			System.out.println("Displayed media type is: " + mediadisp);
			if (ordertypes.contains(mediastring)) {
				if (mediastring == orderString) {
					displayed.add("true");
					continue;
				} else {

					ordertypes.remove(orderString);

				}
			} else {
				displayed.add("false");
				break;
			}

		}

		if (displayed.contains("true") && !displayed.contains("false")) {

			BasePageV2.reportPass("Test Case: " + testCase2 + " is Pass");
			HomePageV2.smokeresults(testCase2, rowno2, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase2, rowno2, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
