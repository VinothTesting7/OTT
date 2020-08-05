package com.viacom.profile;

import java.util.Hashtable;

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

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionState;

//Author: Karthik

@SuppressWarnings("deprecation")
public class MultipleProfileFunctionalityTest extends BaseTestV2 {
	String email = "7019898470";
	String validPwd = "karu5278";
	String testName = "MultipleProfileFunctionalityTest";

	@Test(dataProvider = "getData")
	public void multiProfileFunctionalityTest(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		// Verify profile selection in 'Select Profile' screen
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, "Verify profile selection in 'Select Profile' screen");

		// Verify 'Create new' button functionality in 'Select Profile' screen

		Xls_Reader xls1 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno1 = xls1.getRowCount("Smoke_Results") + 1;
		xls1.setCellData("Smoke_Results", "Testcase Name", rowno1,
				"Verify 'Create new' button functionality in 'Select Profile' screen");

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		HomePageV2.logout();

		try {
			test.log(LogStatus.INFO, "Turning off wifi");
			driver.setConnection(new ConnectionState(0));

		} catch (Exception e) {

		}

		try {
			test.log(LogStatus.INFO, "Turning on wifi");
			driver.setConnection(new ConnectionState(2));
		} catch (Exception e) {

		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 60)) {
			launchPageV2.loginGateway.click();
		} else
			BasePageV2.reportFail("Welcome Screen not displayed after logout");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);

			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Email text field not displayed");
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
			launchPageV2.pwdText.clear();
			launchPageV2.pwdText.sendKeys(validPwd);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Password text field not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5))
			launchPageV2.loginButton.click();
		else
			BasePageV2.reportFail("Login Button not found");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.selectProfilePage, 60)) {
			if (launchPageV2.selectProfilePage.isDisplayed()) {
				test.log(LogStatus.INFO, "Select Profile page is displayed");
				test.log(LogStatus.INFO, "Login for multiple user profile is taking to select profile page");
				HomePageV2.smokeresults("Verify profile selection in 'Select Profile' screen", rowno, "Pass");
				// BasePageV2.takeScreenshot();
			} else {
				test.log(LogStatus.FAIL, "Select Profile page page is not displayed for multi-profile user");
				HomePageV2.smokeresults("Verify profile selection in 'Select Profile' screen", rowno, "Fail");
			}
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.createNew, 20)) {

			homepagev2.createNew.click();

			if (Utilities.explicitWaitVisible(driver, homepagev2.parentalPin, 20)) {
				homepagev2.parentalPin.clear();
				homepagev2.parentalPin.sendKeys("1111");

				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("parental pin page is not displayed");
		} else
			BasePageV2.reportFail("create new Icon is not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.CreateProfile, 20)) {

			test.log(LogStatus.INFO, "Create Profile Page is displayed");
			test.log(LogStatus.PASS,
					"Test Case Verify 'Create new' button functionality in 'Select Profile' screen is Passed");
			HomePageV2.smokeresults("Verify 'Create new' button functionality in 'Select Profile' screen", rowno1,
					"Pass");

		} else {
			HomePageV2.smokeresults("Verify 'Create new' button functionality in 'Select Profile' screen", rowno1,
					"Fail");
			BasePageV2.reportFail(
					"Test Case: 'Verify 'Create new' button functionality in 'Select Profile' screen' is Fail");
		}

		driver.navigate().back();

		if (Utilities.explicitWaitVisible(driver, launchPageV2.selectProfileIcon, 5)) {
			test.log(LogStatus.INFO, "Multi user Profile, selecting a profile to login");
			launchPageV2.selectProfileIcon.click();
		}

		else
			BasePageV2.reportFail("Failed to select profile");

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
			test.log(LogStatus.INFO, "Navigated to home page");
			HomePageV2.logout();
		} else
			HomePageV2.login("karthiktskaaru5278@gmail.com", "karu5278");

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
