package com.viacom.relatedShowsDM;

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
import com.viacom.pagesVersion2.DMPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class VerifyEpisodesUIFunctionalityTest extends BaseTestV2 {
	String showname = "";
	String testName = "VerifyEpisodesUIFunctionalityTest";

	String testCase3 = "'Verify the metadata displayed for each of the cards'";
	String testID3 = "VK_414";
	String testCase4 = "'Verify the functionality by tapping on cards'";
	String testID4 = "VK_415";
	String testCase5 = "'Verify the type of cards displayed under 'Related Shows' tray";
	String testID5 = "VK_413";
	Response resp;
	String watchPageAPI = "https://api.vootkids.com/app/curated/v1/kidsCharacters.json?sortBy=mostPopular&limit=8&offSet=0";

	String showTitle = "";
	String cardTitle = "";
	String mediaID = "";
	String xpath = "";
	ArrayList<String> naviResult = new ArrayList<String>();
	ArrayList<String> metaResult = new ArrayList<String>();

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void EpisodesUIFunctionality(Hashtable<String, String> data) throws Exception {

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
		String email = data.get("Email");
		String pwd = data.get("Password");
		HomePageV2 homepage = new HomePageV2(driver, test);
		HomePageV2.login(email, pwd);
		Utilities.saveCharles(testName);
		
		

		HomePageV2.tabClick("Watch");
		
		try {
			resp = Utilities.requestUtility(watchPageAPI);
			showTitle = resp.jsonPath().getString("assets.items[0].title");
			
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Episode Response from API");
		}


		test.log(LogStatus.INFO, "Navigating to show: " + showTitle);
		xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
				+ showTitle + "']";
		Utilities.verticalSwipe(driver, xpath);
		try {
			WebElement show = driver.findElement(By.xpath(xpath));
			show.click();
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
		}
		
		String end = "//android.widget.TextView[@text='RELATED SHOWS']";

		Utilities.verticalSwipe(driver, end, "visible", 20);
		Utilities.saveCharles(testName);
		DMPageV2.fetchShowsandMediaTypeRelatedShows();

		if (DMPageV2.mediaTypeFlag == true) {
			
			test.log(LogStatus.PASS, "Test Case --> " + testID5 + " --> " + testCase5 + " was Pass");
			try {
				try {
					Utilities.setResultsKids(testID5, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			for (String show : DMPageV2.showsList) {
				
				try {
					xpath = "//android.widget.TextView[contains(@text,'" + show + "')]";
					Utilities.verticalSwipe(driver, xpath);
					try {
						WebElement showCard = driver.findElement(By.xpath(xpath));
						metaResult.add("Pass");
						test.log(LogStatus.INFO, "Verified Meta data of show: "+show);
						showCard.click();
						try {
							driver.findElement(By.xpath(xpath));
							if (Utilities.explicitWaitVisible(driver, homepage.playAudiobookbutton, 20)) {
								test.log(LogStatus.INFO, "Verified Navigation of show: "+show);
								naviResult.add("Pass");
								driver.navigate().back();
							} else {
								naviResult.add("Fail");
								test.log(LogStatus.FAIL, "Failed to Navigate to show: " + show);
								break;
							}
						} catch (Exception e) {
							naviResult.add("Fail");
							test.log(LogStatus.FAIL, "Failed to Navigate to show: " + show);
							break;
						}

					} catch (Exception e) {
						metaResult.add("Fail");
						test.log(LogStatus.FAIL, "Show: " + show + " was not displayed");
						break;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			test.log(LogStatus.FAIL, "Test Case --> " + testID5 + " --> " + testCase5 + " was Fail");
			try {
				try {
					Utilities.setResultsKids(testID5, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			test.log(LogStatus.FAIL, "Test Case --> " + testID3 + " --> " + testCase3 + " was Fail");
			try {
				try {
					Utilities.setResultsKids(testID3, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			test.log(LogStatus.FAIL, "Test Case --> " + testID4 + " --> " + testCase4 + " was Fail");
			try {
				try {
					Utilities.setResultsKids(testID4, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		if (metaResult.contains("Pass") && !metaResult.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case --> " + testID3 + " --> " + testCase3 + " was Pass");
			try {
				try {
					Utilities.setResultsKids(testID3, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			test.log(LogStatus.FAIL, "Test Case --> " + testID3 + " --> " + testCase3 + " was Fail");
			try {
				try {
					Utilities.setResultsKids(testID3, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		if (naviResult.contains("Pass") && !naviResult.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case --> " + testID4 + " --> " + testCase4 + " was Pass");
			try {
				try {
					Utilities.setResultsKids(testID4, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			test.log(LogStatus.FAIL, "Test Case --> " + testID4 + " --> " + testCase4 + " was Fail");
			try {
				try {
					Utilities.setResultsKids(testID4, "Fail");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				
			}
		}
		
		Utilities.saveandcloseCharles(testName);

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
