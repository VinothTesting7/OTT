package com.viacom.registration;

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

public class SignupFunctionalityTest extends BaseTestV2 {
	String testName = "SignupFunctionalityTest";
	String PageTitle = "Sign Up";
	String screenTitle = "Enter your email or mobile number";
	String emailText = "Email";
	String mobileNumberText = "Mobile No.";
	String pwdText = "Password";
	String alreadAccount = "Already have an account?";
	String invalidEmailidErrmsg = "This Email ID is not valid. Please choose another one.";
	String alreadyRegesteredEmailErrmsg = "This Email is already registered with us. Please choose another email";
	String blankPwdErrmsg = "Please enter password";
	String blankSignupErr = "Please enter Mobile and Password";
	String inavlidEmailId = "hgjhs2jhjh@";
	String validEmail = "ifocus.automation@gmail.com";
	String validMobileNumber = "9988776655";
	String password = "abcd5278";
	String invalidPwd = "12345";
	String registeredEmail = "karthi@karthi.com";
	String passwordError = "Please choose another Password.";
	String mobileNoError = "Please enter a valid Mobile No.";
	String invalidMoblile = "526389";
	String alreadyRegisteredMobileErr = "This Mobile No. is already registered with us. Please choose another Mobile No.";
	String testCase1 = "Verify Next button functionality (Invalid)";
	String blankPhoneError = "Please Enter Mobile No.";
	String invalidOtpError = "Please enter valid OTP.";
	String pin = "1111";
	String enteredPwd = "";
	String dots = "";
	String registeredMobileNumber = "";
	String invalidPwdError = "";
	String mobileTextFieldError = "";
	String mobileRegisteredErr = "";

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void signUpFunctionality(Hashtable<String, String> data) throws Exception {
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
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, "Verify Sign Up screen UI");

		Xls_Reader xls1 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno1 = xls1.getRowCount("Smoke_Results") + 1;
		xls1.setCellData("Smoke_Results", "Testcase Name", rowno1,
				"Verify the functionality of Back arrow in Sign Up screen");

		// Verify Email field data acceptance criteria in Sign Up page by giving
		// invalid
		// input

		Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno2 = xls2.getRowCount("Smoke_Results") + 1;
		xls2.setCellData("Smoke_Results", "Testcase Name", rowno2,
				"Verify Email field data acceptance criteria in Sign Up page by giving invalid input");

		Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno3 = xls3.getRowCount("Smoke_Results") + 1;
		xls3.setCellData("Smoke_Results", "Testcase Name", rowno3,
				"Verify Email field data acceptance criteria in Sign Up page by giving already registered email");

		Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno4 = xls4.getRowCount("Smoke_Results") + 1;
		xls4.setCellData("Smoke_Results", "Testcase Name", rowno4,
				"Verify Password field data acceptance criteria in Sign Up page by giving invalid input");

		// 6. Verify Password field data acceptance criteria in Sign Up page by
		// keeping
		// field empty
		Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno5 = xls5.getRowCount("Smoke_Results") + 1;
		xls5.setCellData("Smoke_Results", "Testcase Name", rowno5,
				"Verify Password field data acceptance criteria in Sign Up page by keeping field empty");

		// 7. Verify the functionality of Eye icon in Password text field
		Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno6 = xls6.getRowCount("Smoke_Results") + 1;
		xls6.setCellData("Smoke_Results", "Testcase Name", rowno6,
				"Verify the functionality of Eye icon in Password text field");

		// 8. Verify Next button functionality(Invalid)
		Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno7 = xls7.getRowCount("Smoke_Results") + 1;
		xls7.setCellData("Smoke_Results", "Testcase Name", rowno7, "Verify Next button functionality(Invalid)");

		Xls_Reader xls8 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno8 = xls8.getRowCount("Smoke_Results") + 1;
		xls8.setCellData("Smoke_Results", "Testcase Name", rowno8,
				"Verify Mobile No. field in Sign Up page by giving valid input");

		Xls_Reader xls9 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno9 = xls9.getRowCount("Smoke_Results") + 1;
		xls9.setCellData("Smoke_Results", "Testcase Name", rowno9,
				"Verify country code is autofilled as user enteres mobile no. in the text field ");

		// 11. Verify Mobile No. field in Sign up page by giving invalid input
		Xls_Reader xls10 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno10 = xls10.getRowCount("Smoke_Results") + 1;
		xls10.setCellData("Smoke_Results", "Testcase Name", rowno10,
				"Verify Mobile No. field in Sign up page by giving invalid input ");

		// Verify Password field data acceptance criteria in Sign Up page by
		// giving
		// valid input
		Xls_Reader xls11 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno11 = xls11.getRowCount("Smoke_Results") + 1;
		xls11.setCellData("Smoke_Results", "Testcase Name", rowno11,
				"Verify Mobile No. field in Sign up page by giving invalid input ");

		// Verify Next button functionality (Valid)
		Xls_Reader xls12 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno12 = xls12.getRowCount("Smoke_Results") + 1;
		xls11.setCellData("Smoke_Results", "Testcase Name", rowno12, "Verify Next button functionality (Valid) ");

		// Verify Mobile No. field in Sign Up page by entering already
		// registered Mobile
		// No.
		Xls_Reader xls13 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno13 = xls13.getRowCount("Smoke_Results") + 1;
		xls13.setCellData("Smoke_Results", "Testcase Name", rowno13,
				"Verify Mobile No. field in Sign Up page by entering already registered Mobile No. ");

		int rowno14 = xls13.getRowCount("Smoke_Results") + 1;
		xls13.setCellData("Smoke_Results", "Testcase Name", rowno14, testCase1);

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		// Clicking on Sign Up
		HomePageV2.signup();

		HomePageV2.logout();
		if (Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 25)) {
			test.log(LogStatus.INFO, "Clicking on 'JOIN FREE FOR 30 DAYS' Button");
			launchPageV2.SignUpfromWelcomePage.click();
		} else
			BasePageV2.reportFail("Not able to click on Signup button");

		// 1. Verify Sign Up screen UI

		if (Utilities.explicitWaitVisible(driver, launchPageV2.headerText, 25)) {
			String textHeader = launchPageV2.headerText.getText().toString();
			if (textHeader.equalsIgnoreCase(PageTitle)) {
				test.log(LogStatus.INFO, "Page Title found: " + textHeader);
				// BasePageV2.takeScreenshot();
			} else {
				test.log(LogStatus.INFO, "Page Title not found: " + textHeader);
				homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.headerSubtitle, 5)) {
			String subTitle = launchPageV2.headerSubtitle.getText().toString();
			if (subTitle.equalsIgnoreCase(screenTitle)) {
				test.log(LogStatus.INFO, "Screen Title found: " + subTitle);
				// BasePageV2.takeScreenshot();
			} else {
				test.log(LogStatus.INFO, "Screen Title not found: " + subTitle);
				homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.textAbovepwdTextField, 5)) {
			String textPwd = launchPageV2.textAbovepwdTextField.getText().toString();
			if (textPwd.equalsIgnoreCase(pwdText)) {
				test.log(LogStatus.INFO, "Password Text found: " + textPwd);
				// BasePageV2.takeScreenshot();
			} else {
				test.log(LogStatus.FAIL, "Password Text not found: " + textPwd);
				homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.textAboveEmailTextField, 5)) {
			String textEmail = launchPageV2.textAboveEmailTextField.getText().toString();
			if (textEmail.equalsIgnoreCase(emailText)) {
				test.log(LogStatus.INFO, "Email Text found: " + textEmail);
				// BasePageV2.takeScreenshot();
			} else {
				test.log(LogStatus.FAIL, "Email Text not found: " + textEmail);
				homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.textAboveMobilenumberTextField, 5)) {
			String textMob = launchPageV2.textAboveMobilenumberTextField.getText().toString();
			if (textMob.contains(mobileNumberText)) {
				test.log(LogStatus.INFO, "Mobile number Text found: " + textMob);
				// BasePageV2.takeScreenshot();
			} else {
				test.log(LogStatus.FAIL, "Mobile number not found: " + textMob);
				homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
				BasePageV2.takeScreenshot();
			}
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.nextButton, 5)) {
			test.log(LogStatus.INFO, "Next Button found ");
		} else {
			test.log(LogStatus.INFO, "Next Button found ");
			homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
			BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.Login, 5))

			test.log(LogStatus.INFO, "Login link found ");
		// BasePageV2.takeScreenshot();
		else {
			test.log(LogStatus.INFO, "Login link not found ");
			homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
			BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.alreadyHaveAccount, 5)) {
			String already = launchPageV2.alreadyHaveAccount.getText().toString();
			if (already.equalsIgnoreCase(alreadAccount)) {
				test.log(LogStatus.INFO, "Already Have Account? Text found: " + already);
			} else {
				test.log(LogStatus.FAIL, "Already Have Account? Text not found: " + already);
				homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
			}
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.showPwd, 5)) {
			test.log(LogStatus.INFO, "Eye icon found ");
			// BasePageV2.takeScreenshot();
		} else {
			test.log(LogStatus.INFO, "Eye icon not found ");
			homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "Fail");
			BasePageV2.takeScreenshot();
		}

		test.log(LogStatus.PASS, "Test Case 'Verify Sign Up screen UI' is passed");
		homepagev2.smokeresults("Verify Sign Up screen UI", rowno, "PASS");

		// Verify the functionality of Back arrow in Sign Up screen
		test.log(LogStatus.INFO, "Verify the functionality of Back arrow in Sign Up screen");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5)) {
			test.log(LogStatus.INFO, "Back button is visible");
			launchPageV2.backButton.click();
		} else {
			test.log(LogStatus.INFO, "Failled to click on back button");
			homepagev2.smokeresults("Verify the functionality of Back arrow in Sign Up screen", rowno1, "Fail");
			BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 25)) {

			test.log(LogStatus.INFO, "Welcome Page is displayed");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case 'Verify the functionality of Back arrow in Sign Up screen'is Passed");
			homepagev2.smokeresults("Verify the functionality of Back arrow in Sign Up screen", rowno1, "Pass");
		} else {
			test.log(LogStatus.INFO, "Welcome Page is not displayed");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case 'Verify the functionality of Back arrow in Sign Up screen'is Fail");
			homepagev2.smokeresults("Verify the functionality of Back arrow in Sign Up screen", rowno1, "Fail");
		}

		// Verify Email field data acceptance criteria in Sign Up page by giving
		// invalid
		// input

		HomePageV2.signUpPagefromWelcomeScreen();
		HomePageV2.signUp(inavlidEmailId, validMobileNumber, password);
		String emailError = launchPageV2.emailidError.getText().toString();
		test.log(LogStatus.INFO, "Actual Email Error: " + emailError);
		test.log(LogStatus.INFO, "Expected Email Error: " + invalidEmailidErrmsg);
		if (emailError.equalsIgnoreCase(invalidEmailidErrmsg)) {
			test.log(LogStatus.PASS,
					"Test Case 'Verify Email field data acceptance criteria in Sign Up page by giving invalid input' is passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Email field data acceptance criteria in Sign Up page by giving invalid input", rowno2,
					"Pass");
		} else {
			test.log(LogStatus.FAIL,
					"Test Case 'Verify Email field data acceptance criteria in Sign Up page by giving invalid input' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Email field data acceptance criteria in Sign Up page by giving invalid input", rowno2,
					"Fail");
		}

		// Verify Email field data acceptance criteria in Sign Up page by giving
		// already
		// registered email
		HomePageV2.signUp(registeredEmail, validMobileNumber, password);
		String registeredEmailError = launchPageV2.commonErrors.getText().toString();
		test.log(LogStatus.INFO, "Actual Registered Email Id Error: " + registeredEmailError);
		test.log(LogStatus.INFO, "Expected Registered Email Id Error: " + alreadyRegesteredEmailErrmsg);
		if (registeredEmailError.equalsIgnoreCase(alreadyRegesteredEmailErrmsg)) {
			test.log(LogStatus.PASS,
					"Test Case 'Verify Email field data acceptance criteria in Sign Up page by giving already registered email' is passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Email field data acceptance criteria in Sign Up page by giving already registered email",
					rowno3, "Pass");
		} else {
			test.log(LogStatus.FAIL,
					"Test Case 'Verify Email field data acceptance criteria in Sign Up page by giving already registered email' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Email field data acceptance criteria in Sign Up page by giving already registered email",
					rowno3, "Fail");
		}
		// Changee
		if (Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 5))
			registeredMobileNumber = launchPageV2.mobilenumberText.getText().toString();
		else
			test.log(LogStatus.FAIL, "Mobile Number Text Field not displayed");
		System.out.println(registeredMobileNumber);

		// 5. Verify Password field data acceptance criteria in Sign Up page by
		// giving
		// invalid input
		HomePageV2.signUp(validEmail, validMobileNumber, invalidPwd);
		// changeee
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdErrorMsg, 2))
			invalidPwdError = launchPageV2.pwdErrorMsg.getText().toString();
		else
			test.log(LogStatus.FAIL, "Password Error message not displayed");

		test.log(LogStatus.INFO, "Actual Password Error: " + invalidPwdError);
		test.log(LogStatus.INFO, "Expected Password Error: " + passwordError);

		if (invalidPwdError.equalsIgnoreCase(passwordError)) {
			test.log(LogStatus.PASS,
					"Test Case 'Verify Password field data acceptance criteria in Sign Up page by giving invalid input' is passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Password field data acceptance criteria in Sign Up page by giving invalid input", rowno4,
					"Pass");

		} else {
			test.log(LogStatus.FAIL,
					"Test Case 'Verify Password field data acceptance criteria in Sign Up page by giving invalid input' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Password field data acceptance criteria in Sign Up page by giving invalid input", rowno4,
					"Fail");
		}

		// Please Enter Mobile No.

		if (Utilities.explicitWaitVisible(driver, homepagev2.bckbtn, 5))
			homepagev2.bckbtn.click();
		else
			BasePageV2.reportFail("Back button not displayed");

		HomePageV2.signUpPagefromWelcomeScreen();
		homepagev2.signUpwithoutMobile(validEmail, invalidPwd);
		if (Utilities.explicitWaitVisible(driver, homepagev2.phoneNumberError, 3)) {
			String phoneError = homepagev2.phoneNumberError.getText().toString();
			test.log(LogStatus.INFO, "Actual Phone Error: " + phoneError);
			test.log(LogStatus.INFO, "Expected Phone Error: " + blankPhoneError);
			if (phoneError.equalsIgnoreCase(blankPhoneError)) {
				HomePageV2.smokeresults(testCase1, rowno14, "Pass");
				test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is passed");
				// BasePageV2.takeScreenshot();
			} else {
				HomePageV2.smokeresults(testCase1, rowno14, "Fail");
				test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
				BasePageV2.takeScreenshot();
			}
		} else
			test.log(LogStatus.INFO, "Phone Number Error Not displayed");

		// 7. Verify the functionality of Eye icon in Password text field
		if (Utilities.explicitWaitVisible(driver, launchPageV2.showPwd, 1))
			launchPageV2.showPwd.click();
		else
			test.log(LogStatus.FAIL, "Eye Icon Not Displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 2))
			enteredPwd = launchPageV2.pwdText.getText().toString();
		else
			test.log(LogStatus.FAIL, "Password Text Field Not Displayed");

		if (enteredPwd.contains(invalidPwd)) {
			test.log(LogStatus.INFO, "Password visible after clicking on eye icon " + enteredPwd);
		} else {
			test.log(LogStatus.FAIL, "Test Case 'Verify the functionality of Eye icon in Password text field' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify the functionality of Eye icon in Password text field", rowno6, "Fail");
		}
		Thread.sleep(3000);
		if (Utilities.explicitWaitVisible(driver, launchPageV2.showPwd, 1))
			launchPageV2.showPwd.click();
		else
			test.log(LogStatus.FAIL, "Dotted Text not displayed");
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 1))
			dots = launchPageV2.pwdText.getText().toString();
		else
			test.log(LogStatus.FAIL, "Password Text Field Not displayed");

		if (!(dots.equalsIgnoreCase(invalidPwd))) {
			test.log(LogStatus.INFO, "Password not visible after clicking on eye icon " + dots);
			test.log(LogStatus.PASS,
					"Test Case 'Verify the functionality of Eye icon in Password text field' is passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify the functionality of Eye icon in Password text field", rowno6, "Pass");
		} else {
			test.log(LogStatus.FAIL, "Test Case 'Verify the functionality of Eye icon in Password text field' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify the functionality of Eye icon in Password text field", rowno6, "Fail");
		}

		// 6. Verify Password field data acceptance criteria in Sign Up page by
		// keeping
		// field empty

		HomePageV2.signUpwithoutPwd(validEmail, validMobileNumber);
		String blankPwdError = launchPageV2.pwdErrorMsg.getText().toString();
		test.log(LogStatus.INFO, "Actual Password Error: " + blankPwdError);
		test.log(LogStatus.INFO, "Expected Password Error: " + blankPwdErrmsg);
		if (blankPwdError.equalsIgnoreCase(blankPwdErrmsg)) {
			test.log(LogStatus.PASS,
					"Test Case 'Verify Password field data acceptance criteria in Sign Up page by keeping field empty' is passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Password field data acceptance criteria in Sign Up page by keeping field empty", rowno4,
					"Fail");
			Thread.sleep(5000);
		} else {
			test.log(LogStatus.FAIL,
					"Test Case 'Verify Password field data acceptance criteria in Sign Up page by keeping field empty' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults(
					"Verify Password field data acceptance criteria in Sign Up page by keeping field empty", rowno4,
					"Fail");
		}

		// 8. Verify Next button functionality(Invalid)
		HomePageV2.blankSignup();

		String blanksignup = launchPageV2.commonErrors.getText().toString();
		test.log(LogStatus.INFO, "Actual Blank Signup Error: " + blanksignup);
		test.log(LogStatus.INFO, "Expected Blank Signup Error: " + blankSignupErr);
		if (blanksignup.equalsIgnoreCase(blankSignupErr)) {
			test.log(LogStatus.PASS, "Test Case 'Verify Next button functionality(Invalid)' is passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Next button functionality(Invalid)", rowno7, "Pass");

		} else {
			test.log(LogStatus.FAIL, "Test Case 'Verify Next button functionality(Invalid)' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Next button functionality(Invalid)", rowno7, "Fail");
		}

		// 9. Verify Mobile No. field in Sign Up page by giving valid input

		if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5)) {
			launchPageV2.backButton.click();
		} else {
			// BasePageV2.takeScreenshot();
		}
		try {
			HomePageV2.signUpPagefromWelcomeScreen();
		} catch (Exception e) {

		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 10)) {
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(validMobileNumber);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}

			try {
				launchPageV2.nextButton.click();
			} catch (Exception e) {
			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.commonErrors, 2)) {
				test.log(LogStatus.FAIL,
						"Test Case 'Verify Mobile No. field in Sign Up page by giving valid input' is fail");
				BasePageV2.takeScreenshot();
				homepagev2.smokeresults("Verify Mobile No. field in Sign Up page by giving valid input", rowno8,
						"Fail");
			} else {
				test.log(LogStatus.PASS,
						"Test Case 'Verify Mobile No. field in Sign Up page by giving valid input' is Passed");
				// BasePageV2.takeScreenshot();
				homepagev2.smokeresults("Verify Mobile No. field in Sign Up page by giving valid input", rowno8,
						"Pass");
			}

			// 10. Verify country code is autofilled as user enteres mobile no.
			// in the text
			// field

			if (Utilities.explicitWaitVisible(driver, launchPageV2.countryCode, 25)) {
				String code = launchPageV2.countryCode.getText().toString();
				test.log(LogStatus.INFO,
						"Country code Autofilled when user enters a mobile number in mobile number text field: "
								+ code);
				test.log(LogStatus.PASS,
						"Test case Verify country code is autofilled as user enteres mobile no. in the text field is passed");
				homepagev2.smokeresults(
						"Verify country code is autofilled as user enteres mobile no. in the text field", rowno9,
						"Pass");

			} else {
				test.log(LogStatus.FAIL,
						"Test case Verify country code is autofilled as user enteres mobile no. in the text field is fail");
				homepagev2.smokeresults(
						"Verify country code is autofilled as user enteres mobile no. in the text field", rowno9,
						"Fail");
				BasePageV2.takeScreenshot();

			}

		}

		// 10. Verify Mobile No. field in Sign up page by giving invalid input

		if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5))

			launchPageV2.backButton.click();
		else
			BasePageV2.takeScreenshot();

		HomePageV2.signUpPagefromWelcomeScreen();

		if (Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 10)) {
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(invalidMoblile);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}

			try {
				launchPageV2.nextButton.click();
			} catch (Exception e) {
			}
		} else {
			System.out.println();
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.commonErrors, 5)) {
			System.out.println();
		}

		// changee mobile
		if (Utilities.explicitWaitVisible(driver, launchPageV2.commonErrors, 5))
			mobileTextFieldError = launchPageV2.commonErrors.getText().toString();
		else
			test.log(LogStatus.FAIL, "Mobile Text Field error not displayed");

		test.log(LogStatus.INFO, "Actual Mobile Number Error: " + mobileTextFieldError);
		test.log(LogStatus.INFO, "Expected Mobile Number Error: " + mobileNoError);
		if (mobileTextFieldError.equalsIgnoreCase(mobileNoError)) {
			test.log(LogStatus.PASS,
					"Test Case 'Verify Mobile No. field in Sign up page by giving invalid input' is passed");
			homepagev2.smokeresults("Verify Mobile No. field in Sign up page by giving invalid input", rowno10, "Pass");
			// BasePageV2.takeScreenshot();
		} else {
			test.log(LogStatus.FAIL,
					"Test Case 'Verify Mobile No. field in Sign up page by giving invalid input' is fail");
			homepagev2.smokeresults("Verify Mobile No. field in Sign up page by giving invalid input", rowno10, "Fail");
			BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5))
			launchPageV2.backButton.click();
		else
			BasePageV2.reportFail("Back Button Not Displayed");

		HomePageV2.signUpPagefromWelcomeScreen();

		HomePageV2.signUpwithonlyEmail(validEmail);

		if (Utilities.explicitWaitVisible(driver, launchPageV2.emailidError, 1)) {
			test.log(LogStatus.FAIL,
					"Test Case 'Verify Email field data acceptance criteria in Sign Up page by giving valid input' is fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Email field data acceptance criteria in Sign Up page by giving valid input",
					rowno11, "Fail");

		} else {
			test.log(LogStatus.PASS,
					"Test Case 'Verify Email field data acceptance criteria in Sign Up page by giving valid input' is passed");
			/// BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Email field data acceptance criteria in Sign Up page by giving valid input",
					rowno11, "Pass");
		}

		// Verify Mobile No. field in Sign Up page by entering already
		// registered Mobile
		// No.
		if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5))
			launchPageV2.backButton.click();
		else
			BasePageV2.reportFail("Back button not displayed");

		HomePageV2.signUpPagefromWelcomeScreen();

		HomePageV2.signUp(validEmail, "9898478987", "karu5278");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.commonErrors, 1))
			mobileRegisteredErr = launchPageV2.commonErrors.getText().toString();
		else
			test.log(LogStatus.FAIL, "Already Registered Error message not displayed");

		test.log(LogStatus.INFO, "Actual already Registered Mobile Number Error: " + mobileRegisteredErr);
		test.log(LogStatus.INFO, "Actual already Registered Mobile Number Error: " + alreadyRegisteredMobileErr);

		if (mobileRegisteredErr.equalsIgnoreCase(alreadyRegisteredMobileErr)) {
			test.log(LogStatus.PASS,
					"Test Case 'Verify Mobile No. field in Sign Up page by entering already registered Mobile No.' is Passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Mobile No. field in Sign Up page by entering already registered Mobile No.",
					rowno13, "Pass");
		} else {
			test.log(LogStatus.FAIL,
					"Test Case 'Verify Mobile No. field in Sign Up page by entering already registered Mobile No.' is Fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Mobile No. field in Sign Up page by entering already registered Mobile No.",
					rowno13, "Fail");
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 5))
			launchPageV2.backButton.click();
		else
			BasePageV2.reportFail("Back Button not displayed");

		HomePageV2.signuptestCaseIncluded();

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25)) {

			test.log(LogStatus.PASS, "Test Case 'Verify Next button functionality (Valid)' is passed");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Next button functionality (Valid)", rowno12, "Pass");
		}

		else {
			test.log(LogStatus.FAIL, "Test Case 'Verify Next button functionality (Valid)' is Fail");
			BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify Next button functionality (Valid)", rowno12, "Fail");
		}

		HomePageV2.logout();
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
