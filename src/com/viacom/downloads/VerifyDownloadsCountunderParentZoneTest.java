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
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifyDownloadsCountunderParentZoneTest extends BaseTestV2 {

	String testName = "VerifyDownloadsCountunderParentZoneTest";

	String testCase = "Verify the UI of downloads section when there are no contents/Books/Audio books downloaded";
	String testCase1 = "Verify the UI of downloads section when there are contents/Books/Audio books downloaded";
	String testCase2 = "Verify the functionality by tapping on 'Manage' link text in downloads section";
	String testCase3 = "Verify the downloads data is updated accordingly post downloading content";

	String testCase4 = "Verify the downloads data is updated accordingly post deleting downloaded content ";
	String testCase5 = "Verify the  UI of Email Update option";
	String testCase6 = "Verify the default state for the Email Update options";
	String testCase7 = "Verify 'Email Update' toggle button functionality";
	String testCase8 = "Verify the duration buttons click functionality";
	String testCase9 = "Verify if user can select multiple buttons under Email update duration buttons";
	List<String> emaiupdateResult = new ArrayList<String>();
	List<String> defaultstateresult = new ArrayList<String>();
	List<String> profiledeselected = new ArrayList<String>();
	List<String> profileselected = new ArrayList<String>();
	List<String> tc1Result = new ArrayList<String>();
	int totalDownloads;
	String audiopageAPI = "";
	Response respAud;
	String abook = "";
	String xpath = "";

	@Test(dataProvider = "getData")
	public void VerifyDownloadsCountunderParentZone(Hashtable<String, String> data) throws Exception {
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

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		SearchPageV2 searchpage = new SearchPageV2(driver, test);
		SettingsPageV2 settings = new SettingsPageV2(driver, test);

		HomePageV2.login("9988776655", "secret");

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {
			homepagev2.profilepic.click();

			if (Utilities.explicitWaitVisible(driver, settings.parentZoneButton, 10)) {
				settings.parentZoneButton.click();

				if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 5)) {
					launchPageV2.pinContainer.clear();
					launchPageV2.pinContainer.sendKeys("1111");
				} else
					BasePageV2.reportFail("Pin Container not displayed");

				if (Utilities.explicitWaitVisible(driver, settings.parentZonePage, 20)) {
					test.log(LogStatus.INFO, "Parent zone Page is displayed");

					if (Utilities.explicitWaitVisible(driver, settings.preferencesTab, 20)) {
						settings.preferencesTab.click();
						try {
							Utilities.verticalSwipe(driver, settings.NoofDownloads);
						} catch (Exception e) {
						}

						if (Utilities.explicitWaitVisible(driver, settings.NoofDownloads, 20)) {
							// abcd
							String down = settings.NoofDownloads.getText();
							String[] downs = down.split(" ");
							totalDownloads = Integer.parseInt(downs[0].toString());

							if (totalDownloads == 0) {
								test.log(LogStatus.PASS, "Test case: " + testCase + " is Pass");
								HomePageV2.smokeresults(testCase, rowno, "Pass");
							} else {
								test.log(LogStatus.FAIL, "Test case: " + testCase + " is Fail");
								BasePageV2.takeScreenshot();
								HomePageV2.smokeresults(testCase, rowno, "Fail");
							}

						} else
							BasePageV2.reportFail("Downloads count section is not displayed under 'Preferences tab'");

					} else
						BasePageV2.reportFail("Preferences tab is not displayed under 'Parent Zone' page");

				} else
					BasePageV2.reportFail("Parent Zone page is not displayed");

			} else
				BasePageV2.reportFail("Parent zone button is not displayed");
		}

		else
			BasePageV2.reportFail("Profile pic is not displayed in home page");

		driver.navigate().back();
		driver.navigate().back();

		HomePageV2.tabClick("Listen");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("AudioBookCards")) {
				audiopageAPI = xls.getCellData("Api", "Url", rNum);
				System.out.println("Displayed API is: " + audiopageAPI);
				break;
			} else
				System.out.println("Unable to fetch API");
		}

		try {
			respAud = Utilities.requestUtility(audiopageAPI);
			abook = respAud.jsonPath().get("assets.items[0].title");

		} catch (Exception e) {
		}

		try {
			xpath = "//android.widget.TextView[contains(@text,'" + abook + "')]";

			Utilities.verticalSwipe(driver, xpath);

		} catch (Exception e) {
		}

		try {
			WebElement audioCard = driver.findElement(By.xpath(xpath));

			if (Utilities.explicitWaitVisible(driver, audioCard, 20))
				audioCard.click();
			else
				BasePageV2.reportFail("Audio Card is not displayed");
		} catch (Exception e) {
		}

		//Utilities.verticalSwipe(driver, homepagev2.DownloadStatusIcon);

		Utilities.verticalSwipe(driver, homepagev2.downloadItem);
		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadItem, 10)) {
			homepagev2.downloadItem.click();
			for (int j = 1; j <= 100; j++) {
				Thread.sleep(1500);
				if (Utilities.explicitWaitVisible(driver, homepagev2.DownloadedStatus, 5)) {
					test.log(LogStatus.INFO, "Content downloaded successfully");
					test.log(LogStatus.INFO, "Download Status displayed as: " + homepagev2.DownloadedStatus.getText());
					break;
				} else
					continue;
			}
		} else
			BasePageV2.reportFail("Download icon is not displayed");

		driver.navigate().back();

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(2000);

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {
			homepagev2.profilepic.click();

			if (Utilities.explicitWaitVisible(driver, settings.parentZoneButton, 10)) {
				settings.parentZoneButton.click();

				if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 5)) {
					launchPageV2.pinContainer.clear();
					launchPageV2.pinContainer.sendKeys("1111");
				} else
					BasePageV2.reportFail("Pin Container not displayed");

				if (Utilities.explicitWaitVisible(driver, settings.parentZonePage, 20)) {
					test.log(LogStatus.INFO, "Parent zone Page is displayed");

					if (Utilities.explicitWaitVisible(driver, settings.preferencesTab, 20)) {
						settings.preferencesTab.click();
						try {
							Utilities.verticalSwipe(driver, settings.NoofDownloads);
						} catch (Exception e) {
						}
						Utilities.verticalSwipe(driver);
						if (Utilities.explicitWaitVisible(driver, settings.NoofDownloads, 20)) {
							// abcd
							String down = settings.NoofDownloads.getText();
							String[] downs = down.split(" ");
							totalDownloads = Integer.parseInt(downs[0].toString());

							if (totalDownloads == 1)
								tc1Result.add("true");
							else
								tc1Result.add("false");

							if (Utilities.explicitWaitVisible(driver, settings.downloadSize, 20))
								tc1Result.add("true");
							else
								tc1Result.add("false");

							if (Utilities.explicitWaitVisible(driver, settings.manageDownloads, 20)) {
								tc1Result.add("true");
								settings.manageDownloads.click();

								if (Utilities.explicitWaitVisible(driver, settings.editDownloadsPage, 20)) {
									test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Pass");
									HomePageV2.smokeresults(testCase2, rowno2, "Pass");

									if (Utilities.explicitWaitVisible(driver, settings.deleteIcon, 20)) {
										settings.deleteIcon.click();

										if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteTitle, 2)) {
											downloadpagev2.deleteTitle.click();
											if (Utilities.explicitWaitVisible(driver,
													downloadpagev2.ConfirmDeleteDownload, 2)) {
												downloadpagev2.ConfirmDeleteDownload.click();
												Thread.sleep(3000);
												driver.navigate().back();

												Utilities.verticalSwipe(driver);
												if (Utilities.explicitWaitVisible(driver, settings.NoofDownloads, 20)) {
													// abcd
													String down1 = settings.NoofDownloads.getText();
													String[] downs1 = down1.split(" ");
													totalDownloads = Integer.parseInt(downs1[0].toString());

													if (totalDownloads == 0) {
														test.log(LogStatus.PASS,
																"Test case: " + testCase4 + " is Pass");
														HomePageV2.smokeresults(testCase4, rowno4, "Pass");

														Utilities.verticalSwipe(driver, settings.monthTab);

														if (Utilities.explicitWaitVisible(driver,
																settings.emailUpdateText, 20)) {
															String email = settings.emailUpdateText.getText();

															if (email.equalsIgnoreCase("EMAIL UPDATE")) {
																emaiupdateResult.add("Pass");
															} else {
																emaiupdateResult.add("Fail");
																test.log(LogStatus.FAIL,
																		"Email text is not displayed according to design");
																BasePageV2.takeScreenshot();
															}
															if (Utilities.explicitWaitVisible(driver,
																	settings.emailtoggleon, 20)) {
																emaiupdateResult.add("Pass");
																defaultstateresult.add("Pass");
																settings.emailtoggleon.click();
																if (Utilities.explicitWaitVisible(driver,
																		settings.emailtoggleoff, 20)) {
																	settings.emailtoggleoff.click();
																	Utilities.verticalSwipe(driver);
																	if (Utilities.explicitWaitVisible(driver,
																			settings.emailtoggleon, 20)) {
																		test.log(LogStatus.PASS,
																				"Test case: " + testCase7 + " is Pass");
																		HomePageV2.smokeresults(testCase7, rowno7,
																				"Pass");
																	} else
																		BasePageV2.reportFail(
																				"Unable to Turn on back email update toggle from off state");
																	// toggleofffff
																} else {
																	// defaultstateresult.add("Pass");
																	test.log(LogStatus.FAIL,
																			"Unable to Turn off email update toggle");
																	BasePageV2.takeScreenshot();

																}

															} else {
																test.log(LogStatus.FAIL,
																		"Email update toggle button is not displayed");
																BasePageV2.takeScreenshot();
																emaiupdateResult.add("Fail");
																defaultstateresult.add("Fail");
															}

														} else {
															emaiupdateResult.add("Fail");
															test.log(LogStatus.FAIL, "Email text is not displayed");
															BasePageV2.takeScreenshot();
														}

														if (Utilities.explicitWaitVisible(driver, settings.weektab,
																10)) {
															emaiupdateResult.add("Pass");
															if (settings.weektab.isSelected()) {
																defaultstateresult.add("Pass");
															} else {
																defaultstateresult.add("Fail");
																test.log(LogStatus.FAIL,
																		"Week tab is not selected by default");
																BasePageV2.takeScreenshot();
															}
														} else {
															emaiupdateResult.add("Fail");
															test.log(LogStatus.FAIL, "Week Tab is not displayed");
															BasePageV2.takeScreenshot();
														}

														if (Utilities.explicitWaitVisible(driver, settings.dayTab,
																10)) {
															emaiupdateResult.add("Pass");
															settings.dayTab.click();
															if (settings.dayTab.isSelected()) {
																// 111111
																profileselected.add("Pass");
																if (settings.weektab.isSelected())
																	profiledeselected.add("Fail");
																else
																	profiledeselected.add("Pass");
															} else {
																test.log(LogStatus.FAIL, "Not Able to select Day tab");
																profileselected.add("Fail");
																BasePageV2.takeScreenshot();
															}
														} else {
															emaiupdateResult.add("Fail");
															test.log(LogStatus.FAIL, "Day Tab is not displayed");
															BasePageV2.takeScreenshot();
														}

														if (Utilities.explicitWaitVisible(driver, settings.monthTab,
																10)) {
															emaiupdateResult.add("Pass");
															settings.monthTab.click();
															if (settings.monthTab.isSelected()) {
																// 111111
																profileselected.add("Pass");
																if (settings.dayTab.isSelected())
																	profiledeselected.add("Fail");
																else
																	profiledeselected.add("Pass");
															} else {
																test.log(LogStatus.FAIL,
																		"Not Able to select Month tab");
																profileselected.add("Fail");
																BasePageV2.takeScreenshot();
															}
															// BasePageV2.takeScreenshot();

														} else {
															emaiupdateResult.add("Fail");
															test.log(LogStatus.FAIL, "Month Tab is not displayed");
															BasePageV2.takeScreenshot();
														}

													} else {
														test.log(LogStatus.FAIL,
																"Test case: " + testCase4 + " is Fail");
														BasePageV2.takeScreenshot();
														HomePageV2.smokeresults(testCase4, rowno4, "Fail");
													}

												} else
													BasePageV2.reportFail(
															"Downloads count section is not displayed under 'Preferences tab'");

											} else
												test.log(LogStatus.INFO,
														"'Confirmation Popup' to delete download not displayed");
										} else
											test.log(LogStatus.INFO, "'Delete CTA' not displayed");
									} else
										BasePageV2.reportFail(
												"Delete download icon is not displayed in 'Edit Downloads Page'");

								} else {
									test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
									BasePageV2.takeScreenshot();
									HomePageV2.smokeresults(testCase2, rowno2, "Fail");
									BasePageV2.reportFail(
											"Edit Downloads Page is not displyed when taping on manage link under preferences");
								}

							} else
								tc1Result.add("false");

							if (tc1Result.contains("true") && !tc1Result.contains("false")) {
								test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
								HomePageV2.smokeresults(testCase1, rowno1, "Pass");

								test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Pass");
								HomePageV2.smokeresults(testCase3, rowno3, "Pass");
							} else {
								test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
								BasePageV2.takeScreenshot();
								HomePageV2.smokeresults(testCase3, rowno3, "Fail");
							}

						} else
							BasePageV2.reportFail("Downloads count section is not displayed under 'Preferences tab'");

					} else
						BasePageV2.reportFail("Preferences tab is not displayed under 'Parent Zone' page");

				} else
					BasePageV2.reportFail("Parent Zone page is not displayed");

			} else
				BasePageV2.reportFail("Parent zone button is not displayed");
		}

		else
			BasePageV2.reportFail("Profile pic is not displayed in home page");

		if (emaiupdateResult.contains("Pass") && !emaiupdateResult.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is Pass");
			HomePageV2.smokeresults(testCase5, rowno5, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase5, rowno5, "Fail");
		}

		if (defaultstateresult.contains("Pass") && !defaultstateresult.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is Pass");
			HomePageV2.smokeresults(testCase6, rowno6, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase6, rowno6, "Fail");
		}

		if (profileselected.contains("Pass") && !profileselected.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase8 + " is Pass");
			HomePageV2.smokeresults(testCase8, rowno8, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase8 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase8, rowno8, "Fail");
		}

		if (profiledeselected.contains("Pass") && !profiledeselected.contains("Fail")) {
			test.log(LogStatus.PASS, "Test Case: " + testCase9 + " is Pass");
			HomePageV2.smokeresults(testCase9, rowno9, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case: " + testCase9 + " is Fail");
			BasePageV2.takeScreenshot();
			HomePageV2.smokeresults(testCase9, rowno9, "Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
