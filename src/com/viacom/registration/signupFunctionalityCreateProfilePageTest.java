package com.viacom.registration;

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
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class signupFunctionalityCreateProfilePageTest extends BaseTestV2 {
	String pin = "1111";
	String testName = "signupFunctionalityCreateProfilePageTest";
	String email = "ifocus.automation@gmail.com";
	String mobileNumber = "9988776655";
	String password = "daya123";
	String testCase = "Verify Next button functionality (Invalid - trying to sign up by keeping mobile number empty) ";
	String testCase1 = "Verify Next button functionality (By Entering Valid Mobile number and Password)";
	String testCase2 = "'Verify the navigation post sign up & relaunching the app'";
	String testCase3 = "Verify if user is navigated to PIN creation page post entering details and exiting & relaunching the app";
	String testCase4 = "'Verify the functionality of 'Enter Code Manually' link text";
	String testCase5 = "'Verify Next button functionality' (OTP Landing page should display)";
	String testCase6 = "'Verify the UI of Manual verification code page'";
	String phonenumberInvalidError = "Please Enter Mobile No.";
	String headertextManualCodePage = "Sign Up";
	String headerSubtitleOTP = "Please enter the OTP sent to";
	String notrecievedotp = "Not Received your OTP?";
	String resendotp = "Resend OTP";
	String invalidOtpError = "Please enter valid OTP.";
	String testCase7 = "'Verify the OTP field by entering incorrect OTP number'";
	String headersubtitle = "";
	String notrecievedotptext = "";
	String resend = "";

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void signUpFunctionalityCreateProfilePage(Hashtable<String, String> data) throws Exception {

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

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		for (int i = 1; i <= 5; i++) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 1))

				homepagev2.freshAppNotificationCancel.click();
			else
				break;

		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5))
			HomePageV2.logout();
		else
			System.out.println("Already loged in");

		HomePageV2.signUpPagefromWelcomeScreen();
		HomePageV2.signUpwithoutMobile(email, password);
		if (Utilities.explicitWaitVisible(driver, homepagev2.phoneNumberError, 25)) {
			String errorPhoneNumber = homepagev2.phoneNumberError.getText().toString();
			test.log(LogStatus.INFO, "Actual Phone number error message: " + errorPhoneNumber);
			test.log(LogStatus.INFO, "Expected Phone number error message: " + phonenumberInvalidError);
			if (errorPhoneNumber.equalsIgnoreCase(phonenumberInvalidError)) {
				test.log(LogStatus.INFO, "Invalid Phone Number Error message is displayed");
				HomePageV2.smokeresults(testCase, rowno, "Pass");
				test.log(LogStatus.PASS, "Test Case: " + testCase + " is Passed");
			//	BasePageV2.takeScreenshot();
			} else {
				test.log(LogStatus.INFO,
						"Invalid Phone number Error message is not matching with expected error message");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
				HomePageV2.smokeresults(testCase, rowno, "Fail");
			}

		} else {
			test.log(LogStatus.INFO, "Invalid Phone Number Error message is not displayed");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		}

		try {
			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(2000);
			HomePageV2.signUpPagefromWelcomeScreen();
		} catch (Exception e) {
		}
		HomePageV2.signUpwithoutEmail(mobileNumber, password);

		if (Utilities.explicitWaitVisible(driver, launchPageV2.entercodeManuallyLink, 10)) {
			test.log(LogStatus.INFO, "OTP Loading Page is displayed");
			//BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase5 + " is passed");
			launchPageV2.entercodeManuallyLink.click();
		} else {
			test.log(LogStatus.INFO, "OTP Loading Page is not displayed");
			test.log(LogStatus.INFO, "Unable to click on enter code Manually link");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
			BasePageV2.smokeresults(testCase4, rowno4, "Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
			HomePageV2.smokeresults(testCase5, rowno5, "Fail");
		}

		List<String> uiverify = new ArrayList<String>();
		if (Utilities.explicitWaitVisible(driver, launchPageV2.autoCodedetectBtn, 10)) {
			test.log(LogStatus.INFO, "Manual Verification code page is displayed");
			//BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case:" + testCase4 + " is Passed");

			// 4 OTP text field verification
			// int i = 1;
			// int count=0;
			for (int i = 1; i <= 5; i++) {
				// count=i;
				if (i > 0 && i < 5) {
					try {
						WebElement element = driver.findElement(By.id("com.viacom18.vootkids:id/otp_pin_" + i));
						if (Utilities.explicitWaitVisible(driver, element, 2)) {
							test.log(LogStatus.INFO, "OTP Text field" + i + " is displayed");
							i++;
							uiverify.add("Displayed");
						} else {
							test.log(LogStatus.INFO, "OTP Text field" + i + " is not displayed");
							// BasePageV2.takeScreenshot();
							uiverify.add("Not Displayed");
							// homepagev2.smokeresults(testCase6, rowno6, "Fail");
							break;
						}
					} catch (Exception e) {
					}

				} else {
					try {
						WebElement element = driver.findElement(By.id("com.viacom18.vootkids:id/otp_pin_" + i));
						if (Utilities.explicitWaitVisible(driver, element, 2)) {
							// test.log(LogStatus.INFO, "Total number of OTP Text fields displayed: " +
							// (i));
							BasePageV2.reportFail("Total number of OTP text field displayed is >5: " + i);
						} else {
							test.log(LogStatus.INFO, "Total number of OTP Text fields displayed: " + (i - 1));
							//BasePageV2.takeScreenshot();
							test.log(LogStatus.PASS, "TestCase: " + testCase6 + " is Passed");
							uiverify.add("Displayed");
						}

					} catch (Exception e) {
						break;
					}
				}

			}

			// Verify the OTP field by entering incorrect OTP number

			if (Utilities.explicitWaitVisible(driver, launchPageV2.otpcontainer, 3)) {
				launchPageV2.otpcontainer.clear();
				launchPageV2.otpcontainer.sendKeys(pin);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Pin Container not Displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.otpPageHeader, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.otpPageHeaderSubtitle, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.notRecievedOtp, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.autoCodedetectBtn, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.resendOTP, 1)) {
				String otpError = launchPageV2.commonErrors.getText();
				test.log(LogStatus.INFO, "Actual invalid OTP Error displayed is: " + otpError);
				test.log(LogStatus.INFO, "Actual invalid OTP Error displayed is: " + invalidOtpError);
				if (otpError.equalsIgnoreCase(invalidOtpError)) {
					test.log(LogStatus.INFO, "Actual Error Message is matching with Expected Error Message");
				//	BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase7 + " is Pass");
					HomePageV2.smokeresults(testCase7, rowno7, "Pass");
				} else {
					test.log(LogStatus.INFO, "Actual Error Message is not matching with Expected Error Message");
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " is Fail");
					HomePageV2.smokeresults(testCase7, rowno7, "Fail");
				}
			} else {
				test.log(LogStatus.INFO, "OTP Error Message is not displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " is Fail");
				HomePageV2.smokeresults(testCase7, rowno7, "Fail");
			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.otpPageHeader, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.otpPageHeaderSubtitle, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.notRecievedOtp, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.autoCodedetectBtn, 1)
					&& Utilities.explicitWaitVisible(driver, launchPageV2.resendOTP, 1)) {
				// Verifying header text
				test.log(LogStatus.INFO, "Back Button is displayed in 'Manual Verification code page'");
				test.log(LogStatus.INFO, "Auto detect code button is displayed in 'Manual Verification code page'");
				String otppageheader = launchPageV2.otpPageHeader.getText().toString();
				test.log(LogStatus.INFO, "Actual header text: " + otppageheader);
				test.log(LogStatus.INFO, "Expected header text: " + headertextManualCodePage);

				if (otppageheader.equalsIgnoreCase(headertextManualCodePage)) {
					test.log(LogStatus.INFO, "Manual Verification Header text is displayed as: " + otppageheader);
				} else {
					test.log(LogStatus.INFO,
							"Header Title of manual Verification code page Header text is not matching with Expected text: "
									+ otppageheader);
					uiverify.add("Not Displayed");
				}

				if (Utilities.explicitWaitVisible(driver, launchPageV2.otpPageHeaderSubtitle, 1))
					headersubtitle = launchPageV2.otpPageHeaderSubtitle.getText().toString();
				else
					test.log(LogStatus.FAIL, "Header Subtitle not displayed");

				test.log(LogStatus.INFO, "Actual header Subtitle: " + headersubtitle);
				test.log(LogStatus.INFO, "Expected header Subtitle: " + headerSubtitleOTP);

				if (headersubtitle.contains(headerSubtitleOTP))
					test.log(LogStatus.INFO, "Manual Verification Header Subtitle is displayed as: " + headersubtitle);
				else {
					test.log(LogStatus.INFO,
							"Header Sub Title of manual Verification code page Header text is not matching with Expected text: "
									+ otppageheader);
					uiverify.add("Not Displayed");
				}
				// Verifying not recieved otp text
				// changeeed
				if (Utilities.explicitWaitVisible(driver, launchPageV2.notRecievedOtp, 1))
					notrecievedotptext = launchPageV2.notRecievedOtp.getText().toString();
				else
					test.log(LogStatus.FAIL, "Not Recieved OTP Text Field not displayed");

				test.log(LogStatus.INFO, "Actual Not Recieved OTP Text: " + notrecievedotptext);
				test.log(LogStatus.INFO, "Expected Not Recieved OTP Text: " + notrecievedotp);
				if (notrecievedotptext.equalsIgnoreCase(notrecievedotp)) {
					test.log(LogStatus.INFO,
							"Manual Verification 'Not Recieved OTP text' is displayed as: " + notrecievedotptext);
				} else {
					test.log(LogStatus.INFO,
							"Header Title of manual Verification code page 'Not Recieved OTP text' is not matching with Expected text: "
									+ otppageheader);
					uiverify.add("Not Displayed");
				}

				// Verifying resend otp link
				// changeee
				if (Utilities.explicitWaitVisible(driver, launchPageV2.resendOTP, 1))
					resend = launchPageV2.resendOTP.getText().toString();
				else
					test.log(LogStatus.INFO, "Resend OTP Link not displyed");

				test.log(LogStatus.INFO, "Actual Resend OTP Text: " + resend);
				test.log(LogStatus.INFO, "Expected Resend OTP Text: " + resendotp);
				if (resend.contains(resendotp)) {
					test.log(LogStatus.INFO, "Manual Verification 'Resend OTP' is displayed as: " + resend);
				} else {
					test.log(LogStatus.INFO,
							"Header Title of manual Verification code page 'Resend OTP' is not matching with Expected text: "
									+ otppageheader);
					uiverify.add("Not Displayed");
				}
				test.log(LogStatus.INFO,
						"Header Title of manual Verification code page 'UI' is displayed according to the design");
				//BasePageV2.takeScreenshot();
				uiverify.add("Displayed");

			} else {
				test.log(LogStatus.INFO,
						"Header Title of manual Verification code page 'UI' is not according to the design");
			//	BasePageV2.takeScreenshot();
				uiverify.add("Not Displayed");
			}
			if (uiverify.contains("Displayed") && !uiverify.contains("Not Displayed")) {
				test.log(LogStatus.INFO, "Manual Verification code displayed according to the design");
			//	BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is Pass");
				homepagev2.smokeresults(testCase6, rowno6, "Pass");
			} else {
				test.log(LogStatus.INFO, "Manual Verification code is not displayed according to the design");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
				homepagev2.smokeresults(testCase6, rowno6, "Fail");
			}

			try {
				launchPageV2.backButton.click();
			} catch (Exception e) {
				homepagev2.reportFail("Back button not displayed");
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Test Case: " + testCase4 + " is Fail");
				test.log(LogStatus.FAIL, "Test Case: " + testCase5 + " is Fail");
				homepagev2.smokeresults(testCase4, rowno4, "Fail");
				homepagev2.smokeresults(testCase5, rowno5, "Fail");
			}

		} else {
			test.log(LogStatus.INFO, "Manual Verification code page is not displayed");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case:" + testCase4 + " is Fail");
			launchPageV2.backButton.click();
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 25)) {

			test.log(LogStatus.INFO, "Create Pin Page before Killing the App");
		//	BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Passed");
			HomePageV2.smokeresults(testCase1, rowno1, "Pass");
		} else {
			test.log(LogStatus.INFO, "Create Pin Page not displayed before Killing the App");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase1 + " is Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			HomePageV2.smokeresults(testCase1, rowno1, "Fail");
			HomePageV2.smokeresults(testCase2, rowno2, "Fail");
		}

		test.log(LogStatus.INFO, "Killing the Application to validate: " + testCase2);
		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 120)) {

			test.log(LogStatus.INFO, "Create Pin Page After Relaunching the App");
			//BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is Passed");
			homepagev2.smokeresults(testCase2, rowno2, "Pass");
		} else {
			test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase2 + " is Fail");
			homepagev2.smokeresults(testCase2, rowno2, "Fail");
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {

			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys("1111");
		} else {
			test.log(LogStatus.INFO, "Pin container not displayed");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is fail");
			homepagev2.smokeresults(testCase3, rowno3, "Fail");

		}

		try {
			driver.hideKeyboard();
		} catch (Exception e) {

		}

		test.log(LogStatus.INFO, "Killing the application to validate test case: " + testCase3);

		try {
			driver.closeApp();
		} catch (Exception e) {

		}
		test.log(LogStatus.INFO, "Relaunching the application");
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 120)) {
			test.log(LogStatus.INFO, "Create Pin Page After Relaunching the App");
			//BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is Passed");
			homepagev2.smokeresults(testCase2, rowno3, "Pass");
		} else {
			test.log(LogStatus.INFO, "Create Pin Page not displayed After Relaunching the App");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase3 + " is Fail");
			homepagev2.smokeresults(testCase3, rowno3, "Fail");
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
			launchPageV2.buttonCreatePin.click();
		else
			BasePageV2.reportFail("Create Pin Button not displayed");

		// Confirm pin

		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
			launchPageV2.buttonCreatePin.click();
		else
			BasePageV2.reportFail("Create Pin Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
			launchPageV2.kidsName.clear();
			launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
		} else {
			HomePageV2.reportFail("Kids name text field not displayed");
			//BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
			launchPageV2.DOB.click();
		else
			HomePageV2.reportFail("DOB Field not displayed");
		//BasePageV2.takeScreenshot();

		if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
			launchPageV2.DOBSelect.click();
		else {
			HomePageV2.reportFail("Date selector not displayed");
		//	BasePageV2.takeScreenshot();
		}

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
			test.log(LogStatus.INFO, "Select Profile image page is displayed");
		else
			BasePageV2.reportFail("Select Profile image page is not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		for (int i = 1; i <= 5; i++) {
			try {

				WebElement element = driver.findElement(
						By.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
								+ i + "]"));
				if (i == 5) {
					Utilities.verticalSwipe(driver);
					Utilities.verticalSwipe(driver);
					driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
							.click();
				} else if (Utilities.explicitWaitVisible(driver, element, 10)) {
					element.click();
				} else {
					HomePageV2.reportFail("Kids Characters not displayed");
					//BasePageV2.takeScreenshot();
				}

			} catch (Exception e) {
				// test.log(logStatus, details);
			}

		}
		try {
			WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
			if (Utilities.explicitWaitVisible(driver, Skills1, 5))
				Skills1.click();
			else
				test.log(LogStatus.INFO, "Skills not displayed");
		} catch (Exception e) {

		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 25))
			launchPageV2.letsGo.click();
		else
			BasePageV2.reportFail("Let's Go Button not displayed");

		for (int i = 1; i <= 5; i++) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
				homepagev2.freshAppNotificationCancel.click();
			else
				break;
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
			test.log(LogStatus.INFO, "Sign up Successful");
		else
			BasePageV2.reportFail("Unable to Signup");

		HomePageV2.logout();
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
