package com.viacom.welcomepage;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

public class SignUpPageUi_P3 extends BaseTestV2 {
	
	
String testName = "SignUpPageUi_P3";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SignUpPageUi_P3");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
	
		
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		WatchPageV2 watchPage = new WatchPageV2(driver, test);
		DownloadsPageV2 downloads = new DownloadsPageV2(driver, test);
		ListenPageV2 listenPage = new ListenPageV2(driver, test);
		SignupandLoginPageV2 signUp = new SignupandLoginPageV2(driver, test);
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		
		
		String userName = data.get("Email");
		String password = data.get("Password");
		String otp = data.get("Otp");
		

		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		// Log out from the App
		
		homepagev2.logout();
		
		
		// VK_2198 - Verify phone number restriction in Sign up screen:
		// VK_2199 - Verify phone number restriction in Login screen:
		// VK_2201 - Verify phone number restriction in Login - Forgot Password flow:
		// VK_2204 - Verify error message when user tries to sign up without tapping on 'Send OTP' link:
		// VK_2200 - Verify phone number restriction during Update Mobile Number flow:
		// VK_2202 - Verify phone number restriction in Settings - Forgot Password flow:
		
		String wrongNumber= "98765432198654";
		int numberSize = 0;
		
		// click on Sign Up button
		if(Utilities.explicitWaitClickable(driver, signUp.joinFreefor30Days, 30)) {
			signUp.joinFreefor30Days.click();
			test.log(LogStatus.INFO, "click on SignUp button");
			if (Utilities.explicitWaitVisible(driver, signUp.mobileNoTextField, 25)) {
				signUp.mobileNoTextField.click();
				signUp.mobileNoTextField.clear();
				signUp.mobileNoTextField.sendKeys(wrongNumber);
				Thread.sleep(3000);
				try {
					numberSize = signUp.mobileNoTextField.getText().toString().trim().length();
					System.out.println("The String Length is : " + numberSize);
				} catch (Exception e) {
					// TODO: handle exception
				}
				 
			} else {
				test.log(LogStatus.FAIL, "Not displayed mobile edit text box in signUp screen");
				BasePageV2.takeScreenshot();
			}
				
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
			
			if(numberSize == 10) {
				test.log(LogStatus.PASS, "Verify phone number restriction in Sign up screen:");
				if(!Utilities.setResultsKids("VK_2198", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else {
				test.log(LogStatus.FAIL, "Verify phone number restriction in Sign up screen:");
				BasePageV2.takeScreenshot();
				if(!Utilities.setResultsKids("VK_2198", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
			}
			
		
			
		}else {
			BasePageV2.reportFail("Failed to click on SignUp button / Not displayed");
		}
		
		
		// VK_2204 - Verify error message when user tries to sign up without tapping on 'Send OTP' link:
		
		
		 // send mobile Number to Mobile Field 
		
		 if(Utilities.explicitWaitVisible(driver,signUp.mobileNoTextField, 10))
		 {				
				      test.log(LogStatus.INFO, "  Mobile No field'  Displayed in signUPPage");				 				   								 											 
													 
							 signUp.mobileNoTextField.click();
							 signUp.mobileNoTextField.clear();
							 signUp.mobileNoTextField.sendKeys(wrongNumber);								 
							 test.log(LogStatus.INFO, " 'Sucessesfully entered mobile number to mobile number field ");				 				   							
		 }
		 else
		 {
			        test.log(LogStatus.FAIL, " 'Mobile No field '  not  Displayed in signUP Page"); 	
			        BasePageV2.takeScreenshot();				       
		 }
		
		 //Verify OTP Text with * and OTP field in SignupPage			 			 
		 if( Utilities.explicitWaitVisible(driver,signUp.otptextField, 10))
		 {					 
			      test.log(LogStatus.INFO, "  ' OTP Text with * and OTP field in SignupPage'  Displayed in signUPPage");
			         signUp.otptextField.click();
			         signUp.otptextField.clear();
			         signUp.otptextField.sendKeys(otp);	
		 }
		 else
		 {
			        test.log(LogStatus.FAIL, " ' OTP Text with * and OTP field in SignupPage'  not  Displayed in signUPPage"); 	
			        BasePageV2.takeScreenshot();
			        
		 }	
		 
		 
		 driver.hideKeyboard();
		 
		 //Verify Password Text with * and Password field in SignupPage			 			 
		 if(Utilities.explicitWaitVisible(driver,signUp.passwordtextField, 30))
		 {					 
			      test.log(LogStatus.INFO, "  ' Password Text with * and Password field in SignupPage'  Displayed in signUPPage");	
			      signUp.passwordtextField.click();
			      signUp.passwordtextField.clear();
			      signUp.passwordtextField.sendKeys(password);	
		 }
		 else
		 {
			        test.log(LogStatus.FAIL, " ' Password Text with and Password field in SignupPage'  not  Displayed in signUPPage"); 	
			        BasePageV2.takeScreenshot();
			        
		 }	
		 
		 // click on Next button
		 driver.hideKeyboard();
		 
		
		 
		 if(Utilities.explicitWaitClickable(driver, signUp.signUpNextBtn, 50)) {   //nextbuttonwithfillDatainsignUp
			 signUp.signUpNextBtn.click();
			 test.log(LogStatus.INFO, "clicked on Next button in singup page");
			 Thread.sleep(10000);
		 }else {
			 test.log(LogStatus.FAIL, "Not Found Next Button / Not Click");
		 }
		
		if(Utilities.explicitWaitVisible(driver, signUp.error_message_phone_number, 20)) {
			test.log(LogStatus.PASS, "Verify error message when user tries to sign up without tapping on 'Send OTP' link:");
			if(!Utilities.setResultsKids("VK_2204", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		} else {
			test.log(LogStatus.FAIL, "Verify error message when user tries to sign up without tapping on 'Send OTP' link:");
			BasePageV2.takeScreenshot();
			if(!Utilities.setResultsKids("VK_2204", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		
		}
		
       driver.pressKeyCode(AndroidKeyCode.BACK);
		
		// VK_2199 - Verify phone number restriction in Login screen:
		
		int loginMobileNum = 0;
		if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25)) {
			launchPageV2.loginGateway.click();
		    test.log(LogStatus.INFO, "click on Login link in welcome screen");
		    if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(wrongNumber);
				Thread.sleep(3000);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
				
				try {
					loginMobileNum = launchPageV2.emailText.getText().toString().trim().length();
					test.log(LogStatus.INFO, "The Length of the string from mobile number edit text field" + loginMobileNum);
				} catch (Exception e) {
					// TODO: handle exception
				}
			
				
				
			} else
				BasePageV2.reportFail("Email text field not displayed");
		
		    if(loginMobileNum == 10) {
				test.log(LogStatus.PASS, "Verify phone number restriction in Login screen:");
				if(!Utilities.setResultsKids("VK_2199", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else {
				test.log(LogStatus.FAIL, "Verify phone number restriction in Login screen:");
				BasePageV2.takeScreenshot();
				if(!Utilities.setResultsKids("VK_2199", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
			}
			
		    
		
		}else
			BasePageV2.reportFail("Login Page Gateway button not displayed");
		
		// VK_2201 - Verify phone number restriction in Login - Forgot Password flow:
		
		int forGotpasswordMobileNum = 0;
		if(Utilities.explicitWaitClickable(driver, signUp.forgotpasswordinLoginPage, 50)) {
			signUp.forgotpasswordinLoginPage.click();
			test.log(LogStatus.INFO, "click on forgot password link in Login screen");
			if(Utilities.explicitWaitVisible(driver, homepagev2.editTextForGotpassword, 50)) {
				homepagev2.editTextForGotpassword.click();
				homepagev2.editTextForGotpassword.clear();
				homepagev2.editTextForGotpassword.sendKeys(wrongNumber);
				Thread.sleep(3000);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
				try {
					forGotpasswordMobileNum = homepagev2.editTextForGotpassword.getText().toString().trim().length();
					test.log(LogStatus.INFO, "The Length of the string from mobile number edit text field in forgot password screen" + loginMobileNum);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if(loginMobileNum == 10) {
					test.log(LogStatus.PASS, "Verify phone number restriction in Login - Forgot Password flow:");
					if(!Utilities.setResultsKids("VK_2201", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} else {
					test.log(LogStatus.FAIL, "Verify phone number restriction in Login - Forgot Password flow:");
					BasePageV2.takeScreenshot();
					if(!Utilities.setResultsKids("VK_2201", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				
				}
				
				driver.pressKeyCode(AndroidKeyCode.BACK);
				driver.pressKeyCode(AndroidKeyCode.BACK);
				
			}else {
				test.log(LogStatus.FAIL, "Not displayed forgot password mobile edit text box in Forgot password screen");
				BasePageV2.takeScreenshot();
			}
			
			
			
		}else {
			BasePageV2.reportFail("Not displyed forgot password link in Login screen / Not click");
		}
		
		
		
		
		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		
		// VK_2200 - Verify phone number restriction during Update Mobile Number flow:
		
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Clicked on profile icon in home page");

		}else BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
	
			
			settingsPageV2.putBackGroundApp();
			
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
				settingsPageV2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
					Thread.sleep(5000);
					settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(10000);
				}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		
			settingsPageV2.putBackGroundApp3();
			// click on Settings icon
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 60)) {
				settingsPageV2.settingsIcon.click();
			}else BasePageV2.reportFail("Not displayed settings icon in Parent zone screen / Not click");
		
			int mobileNum = 0;
		    // click on Account option link in Settings Main screen
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneAccount, 50)) {
				settingsPageV2.settingTextinParantZoneAccount.click();
				test.log(LogStatus.INFO, "click on account option link in settings screen");
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountMobileNum, 50)) {
					settingsPageV2.accountMobileNum.click();
					test.log(LogStatus.INFO, "click on mobile option link in Settings Account screen");
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMobileNumEditText, 50)) {
						settingsPageV2.addMobileNumEditText.click();
						settingsPageV2.addMobileNumEditText.clear();
						settingsPageV2.addMobileNumEditText.click();
						settingsPageV2.addMobileNumEditText.sendKeys(wrongNumber);
						test.log(LogStatus.INFO, "Entered the mobile number in mobile edit text box");
	
						driver.hideKeyboard();
						try {
							 mobileNum = settingsPageV2.addMobileNumEditText.getText().toString().trim().length();
							 test.log(LogStatus.INFO, "The mobile number lenght is : " + mobileNum);	
						} catch (Exception e) {
							// TODO: handle exception
						}
						 
						 if(mobileNum == 10) {
							 test.log(LogStatus.PASS, "Verify phone number restriction during Update Mobile Number flow:");
								if(!Utilities.setResultsKids("VK_2200", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							} else {
								test.log(LogStatus.FAIL, "Verify phone number restriction during Update Mobile Number flow:");
								BasePageV2.takeScreenshot();
								if(!Utilities.setResultsKids("VK_2200", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							
							}
		                 driver.pressKeyCode(AndroidKeyCode.BACK);
						
					}else {
						test.log(LogStatus.FAIL, "Not displayed mobile number Edit text box in account edit mobile number screen");
						BasePageV2.takeScreenshot();
					}
					
					
				}else {
					test.log(LogStatus.FAIL, "Not displayed mobile number option link in settings account screen / Not click");
					BasePageV2.takeScreenshot();
				}
				
			}else BasePageV2.reportFail("Not displayed account option in settings main page / Not click");
		
		
			// VK_2202 - Verify phone number restriction in Settings - Forgot Password flow:
			
		   // click on Reset Password option link in Account settings screen
			settingsPageV2.putBackGroundApp3();
			int forgotPawd = 0;
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountResetPawd, 50)) {
				settingsPageV2.accountResetPawd.click();
				test.log(LogStatus.INFO, "click on Reset password option link in Account settigs screen");
				// click on Forgot password link 
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPassword, 50)) {
					settingsPageV2.forgotPassword.click();
					test.log(LogStatus.INFO, "click on Forgot password link in Reset Password screen");
					
					if(Utilities.explicitWaitVisible(driver, homepagev2.editTextForGotpassword, 50)) {
						homepagev2.editTextForGotpassword.click();
						homepagev2.editTextForGotpassword.clear();
						homepagev2.editTextForGotpassword.sendKeys(wrongNumber);
						Thread.sleep(3000);
						try {
							driver.hideKeyboard();
						} catch (Exception e) {
						}
						try {
							forgotPawd = homepagev2.editTextForGotpassword.getText().toString().trim().length();
							test.log(LogStatus.INFO, "The Length of the string from mobile number edit text field in forgot password screen  :  " + loginMobileNum);
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						if(forgotPawd == 10) {
							test.log(LogStatus.PASS, "Verify phone number restriction in Settings - Forgot Password flow:");
							if(!Utilities.setResultsKids("VK_2202", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						} else {
							test.log(LogStatus.FAIL, "Verify phone number restriction in Settings - Forgot Password flow:");
							BasePageV2.takeScreenshot();
							if(!Utilities.setResultsKids("VK_2202", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						
						}
					
						
					}else {
						test.log(LogStatus.FAIL, "Not displayed forgot password mobile edit text box in Forgot password screen");
						BasePageV2.takeScreenshot();
					}
					
					
				}else {
					test.log(LogStatus.FAIL, "Not displayed forgot password link in Reset Password screen / Not click");
					BasePageV2.takeScreenshot();
				}
				
			}else BasePageV2.reportFail("Not displayed Reset Password option link in Account Settings screen / Not click");
		
		
		

		
		
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	

}
