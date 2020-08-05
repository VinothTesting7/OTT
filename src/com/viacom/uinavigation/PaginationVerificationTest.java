package com.viacom.uinavigation;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class PaginationVerificationTest extends BaseTestV2 {
	String testName = "PaginationVerificationTest";
	String testCase = "Verify the availibility of pagination indicator below the carousel card in My Stuff ";
	String testCase1 = "Verify the UI of pagination indicator below the carousel card in My Stuff ";
	// String testCase2 = "Verify user remains in same card after minimize and
	// resuming the app";
	String testCase3 = "'Verify the availibility of pagination indicator below the carousel card in Watch'";
	String testCase4 = "Verify the UI of pagination indicator below the carousel card in Watch";
	String testCase5 = "Verify the availibility of pagination indicator below the carousel card in Read";
	String testCase6 = "Verify the UI of pagination indicator below the carousel card in Read";
	String testCase7 = "Verify the availibility of pagination indicator below the carousel card in Listen";
	String testCase8 = "Verify the UI of pagination indicator below the carousel card in Listen";
	String testCase9 = "Verify pagination indicators present in Welcome screen";
	String testCase10 = "Verify the Auto scroll functionality in Welcome screens";
	String testCase11 = "Verify the number of profiles associated with each account";
	String firstScreenTitle = "";
	String secondScreenTitle = "";

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void PaginationTest(Hashtable<String, String> data) throws Exception {

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

		// int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		// xls.setCellData("Smoke_Results", "Testcase Name", rowno2, testCase2);

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

		int rowno9 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno9, testCase9);

		int rowno10 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno10, testCase10);

		int rowno11 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno11, testCase11);

		launchApp();
		LaunchPageV2 launchpage = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		HomePageV2.login("pavan.kn@gmail.com", "karu5278");

		HomePageV2.tabClick("My Stuff");

		if (Utilities.explicitWaitVisible(driver, homepagev2.Paginator, 2)) {
			test.log(LogStatus.INFO, "Paginator displayed below carousel in 'My Stuff' Tab");
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			BasePageV2.smokeresults(testCase, rowno, "Pass");
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
			BasePageV2.smokeresults(testCase1, rowno1, "Pass");
		//	BasePageV2.takeScreenshot();
		} else {
			test.log(LogStatus.INFO, "Paginator is not displayed below carousel in 'My Stuff' Tab");
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			BasePageV2.smokeresults(testCase3, rowno1, "Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			BasePageV2.smokeresults(testCase1, rowno1, "Fail");
			BasePageV2.takeScreenshot();
		}
		HomePageV2.tabClick("Watch");

		if (Utilities.explicitWaitVisible(driver, homepagev2.Paginator, 2)) {
			test.log(LogStatus.INFO, "Paginator displayed below carousel in 'Watch' Tab");
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
			BasePageV2.smokeresults(testCase3, rowno3, "Pass");
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is Pass");
			BasePageV2.smokeresults(testCase4, rowno4, "Pass");
			//BasePageV2.takeScreenshot();
		} else {
			test.log(LogStatus.INFO, "Paginator is not displayed below carousel in 'Watch' Tab");
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			BasePageV2.smokeresults(testCase3, rowno3, "Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
			BasePageV2.smokeresults(testCase4, rowno4, "Fail");
			BasePageV2.takeScreenshot();
		}

		HomePageV2.tabClick("Read");

		if (Utilities.explicitWaitVisible(driver, homepagev2.Paginator, 2)) {
			test.log(LogStatus.INFO, "Paginator displayed below carousel in 'Read' Tab");
			test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Pass");
			BasePageV2.smokeresults(testCase5, rowno5, "Pass");
			test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is Pass");
			BasePageV2.smokeresults(testCase6, rowno6, "Pass");
		//	BasePageV2.takeScreenshot();
		} else {
			test.log(LogStatus.INFO, "Paginator is not displayed below carousel in 'Read' Tab");
			test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
			HomePageV2.smokeresults(testCase5, rowno5, "Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
			BasePageV2.smokeresults(testCase6, rowno6, "Fail");
			BasePageV2.takeScreenshot();
		}

		HomePageV2.tabClick("Listen");

		if (Utilities.explicitWaitVisible(driver, homepagev2.Paginator, 2)) {
			test.log(LogStatus.INFO, "Paginator displayed below carousel in 'Listen' Tab");
			test.log(LogStatus.PASS, "Test Case: " + testCase7 + " is Pass");
			HomePageV2.smokeresults(testCase7, rowno7, "Pass");
			test.log(LogStatus.PASS, "Test Case: " + testCase8 + " is Pass");
			HomePageV2.smokeresults(testCase8, rowno8, "Pass");
			///BasePageV2.takeScreenshot();
		} else {
			test.log(LogStatus.INFO, "Paginator is not displayed below carousel in 'Listen' Tab");
			test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " is Fail");
			BasePageV2.smokeresults(testCase7, rowno7, "Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase8 + " is Fail");
			HomePageV2.smokeresults(testCase8, rowno8, "Fail");
			BasePageV2.takeScreenshot();

		}

		HomePageV2.logout();

		ArrayList<String> autoScroll = new ArrayList<String>();

		if (Utilities.explicitWaitVisible(driver, launchpage.SignUpfromWelcomePage, 30))
			test.log(LogStatus.INFO, "Sign Up Page is displayed");
		else
			BasePageV2.reportFail("Sign Up Page is not displayed after logout");

		for (int i = 0; i < 5; i++) {
			try {

				String xpath = "//*[contains(@class,'android.support.v7.app.ActionBar$Tab') and @selected = 'true']['"
						+ i + "']";

				/*
				 * String xpath1 =
				 * "//*[contains(@class,'android.support.v7.app.ActionBar$Tab') and @selected = 'true']['"
				 * + (i + 1) + "']";
				 */
				WebElement paginator1 = driver.findElement(By.xpath(xpath));
				if (Utilities.explicitWaitVisible(driver, paginator1, 2))
					test.log(LogStatus.INFO, "Paginator 1 is displayed and is selected");
				else
					test.log(LogStatus.FAIL, "Paginator 1 not displayed");

				if (Utilities.explicitWaitVisible(driver, launchpage.screenTitle, 2)) {
					firstScreenTitle = launchpage.screenTitle.getText().trim();
					test.log(LogStatus.INFO, "Screen title of current screen in welcome page is: " + firstScreenTitle);
				} else
					BasePageV2.reportFail("Screen title not displayed");
			} catch (Exception e) {
			}

			Thread.sleep(10000);

			try {

				String xpath1 = "//*[contains(@class,'android.support.v7.app.ActionBar$Tab') and @selected = 'true']['"
						+ (i + 1) + "']";
				WebElement paginator2 = driver.findElement(By.xpath(xpath1));

				if (Utilities.explicitWaitVisible(driver, paginator2, 2)) {
					test.log(LogStatus.INFO, "Paginator 2 is displayed and is selected");
					autoScroll.add("Pass");
				}

				else {
					test.log(LogStatus.FAIL, "Paginator 2 not displayed");
					autoScroll.add("Fail");
				}

				if (Utilities.explicitWaitVisible(driver, launchpage.screenTitle, 2)) {
					secondScreenTitle = launchpage.screenTitle.getText().trim();
					test.log(LogStatus.INFO, "Screen title of next screen in welcome page is: " + secondScreenTitle);

				} else
					BasePageV2.reportFail("Screen title not displayed");

				if (firstScreenTitle.equals(secondScreenTitle))
					autoScroll.add("Fail");
				else
					autoScroll.add("Pass");

			} catch (Exception e) {
				test.log(LogStatus.INFO, "Welcome screen fails to auto scroll");
				autoScroll.add("Fail");
			}

			if (i == 5)
				break;
			else
				System.out.println("i<5 validating");

		}

		if (autoScroll.contains("Pass") && !autoScroll.contains("Fail")) {
			test.log(LogStatus.PASS,"Test case: " + testCase9 + " is Pass");
			HomePageV2.smokeresults(testCase9, rowno9, "Pass");
			test.log(LogStatus.PASS,"Test case: " + testCase10 + " is Pass");
			HomePageV2.smokeresults(testCase10, rowno10, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test case: " + testCase9 + " is Fail");
			test.log(LogStatus.FAIL, "Test case: " + testCase10 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase9, rowno9, "Fail");
			HomePageV2.smokeresults(testCase10, rowno10, "Fail");
		}

		if (Utilities.explicitWaitVisible(driver, launchpage.SignUpfromWelcomePage, 2))
			HomePageV2.login("7019898470","karu5278");
		else {
			HomePageV2.logout();
			HomePageV2.login("7019898470","karu5278");
		}

		ArrayList<String> profileStatus = new ArrayList<String>();
		for (int i = 2; i <= 3; i++) {
			if (i < 3 && i > 2) {
				if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
					homepagev2.profilepic.click();
					if (Utilities.explicitWaitVisible(driver, homepagev2.switch_prof, 20)) {
						test.log(LogStatus.INFO, "'Switch Profile' is displayed");
						if (Utilities.explicitWaitVisible(driver, homepagev2.createNew, 5)) {
							test.log(LogStatus.INFO,
									"Create New (+) icon displayed in switch page, and number of profiles displayed is: "
											+ i);
							profileStatus.add("true");
							homepagev2.createNew.click();
							if (Utilities.explicitWaitVisible(driver, homepagev2.parentalPin, 20)) {
								homepagev2.parentalPin.clear();
								homepagev2.parentalPin.sendKeys("1111");

								try {
									driver.hideKeyboard();
								} catch (Exception e) {
								}
								if (Utilities.explicitWaitVisible(driver, launchpage.CreateProfile, 25)) {
									test.log(LogStatus.INFO, "Crreate Profile page is displayed");
									if (Utilities.explicitWaitVisible(driver, launchpage.kidsName, 25)) {
										launchpage.kidsName.clear();
										launchpage.kidsName.sendKeys(Utilities.generateRandomName());
										try {
											driver.hideKeyboard();
										} catch (Exception e) {
										}

										if (Utilities.explicitWaitVisible(driver, launchpage.DOB, 5)) {
											launchpage.DOB.click();

											if (Utilities.explicitWaitVisible(driver, launchpage.DOBSelect, 5)) {
												launchpage.DOBSelect.click();
												try {
													driver.hideKeyboard();
												} catch (Exception e) {
												}
												if (Utilities.explicitWaitVisible(driver, launchpage.next, 20)) {
													launchpage.next.click();
													if (Utilities.explicitWaitVisible(driver,
															launchpage.createBuddyPage, 20)) {
														test.log(LogStatus.INFO,
																"Select Profile image page is displayed");
														if (Utilities.explicitWaitVisible(driver, launchpage.next,
																20)) {
															launchpage.next.click();

															for (int j = 1; j <= 5; j++) {
																try {
																	WebElement element = driver.findElement(By.xpath(
																			"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
																					+ j + "]"));
																	if (Utilities.explicitWaitVisible(driver, element,
																			10)) {
																		element.click();
																	} else {
																		HomePageV2.reportFail(
																				"Kids Characters not displayed");
																	//	BasePageV2.takeScreenshot();
																	}
																} catch (Exception e) {
																	// test.log(logStatus, details);
																}

															}
															try {
																WebElement Skills1 = driver.findElement(By.xpath(
																		"//android.widget.TextView[@text='Story']/.."));
																if (Utilities.explicitWaitVisible(driver, Skills1, 5))
																	Skills1.click();
																else {
																	HomePageV2.reportFail("Skills not displayed");
																	//BasePageV2.takeScreenshot();
																}
															} catch (Exception e) {

															}

															if (Utilities.explicitWaitVisible(driver, launchpage.next,
																	20)) {
																launchpage.next.click();

																if (Utilities.explicitWaitVisible(driver,
																		launchpage.letsGo, 60))
																	launchpage.letsGo.click();
																else
																	BasePageV2.reportFail(
																			"Let's Go Button not displayed");

															} else
																BasePageV2.reportFail("Next Button not displayed");

														} else
															BasePageV2.reportFail("Next Button not displayed");
													}

													else
														BasePageV2.reportFail(
																"Select Profile image page is not displayed");
												}

												else
													BasePageV2.reportFail("Next Button not displayed");
											}

											else
												BasePageV2.reportFail("Date selector not displayed");
										}

										else
											BasePageV2.reportFail("DOB Field not displayed");

									} else
										BasePageV2.reportFail("Kids name text field not displayed");
								}

								else
									BasePageV2.reportFail("Create Profile Page is not displayed");

							} else
								BasePageV2.reportFail("Parental pin page is not displayed");

						} else
							BasePageV2.reportFail(
									"'Create New (+)' icon not displayed when number of profile displayed ---> " + i
											+ " is less than 6");

					} else
						BasePageV2.reportFail("Switch Profile page is not displayed");

					profileStatus.add("true");

				} else
					BasePageV2.reportFail("Home Page not displayed after signup");

			} else {
				if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
					homepagev2.profilepic.click();
					if (Utilities.explicitWaitVisible(driver, homepagev2.switch_prof, 20)) {
						test.log(LogStatus.INFO, "'Switch Profile' is displayed");
						if (Utilities.explicitWaitVisible(driver, homepagev2.createNew, 5)) {
							test.log(LogStatus.INFO,
									"Create New (+) icon displayed in switch profile page, and number of profiles displayed is: "
											+ i + " is >= 6");
							//BasePageV2.takeScreenshot();
							profileStatus.add("false");

						} else {
							test.log(LogStatus.INFO,
									"Create New (+) icon is not displayed in switch profile page, and number of profiles displayed is: "
											+ i + " is <= 6");
							profileStatus.add("true");
						}
					} else
						BasePageV2.reportFail("Switch Profile page is not displayed");
				} else
					BasePageV2.reportFail("Home Page is not displayed after creating profile");
			}

		}

		if (profileStatus.contains("true") && !profileStatus.contains("false")) {
			test.log(LogStatus.PASS,"Test case: " + testCase11 + " is Pass");
			HomePageV2.smokeresults(testCase11, rowno11, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test case: " + testCase11 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase11, rowno11, "Pass");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
