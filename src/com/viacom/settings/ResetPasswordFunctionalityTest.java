package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class ResetPasswordFunctionalityTest extends BaseTestV2 {

	String testName = "ResetPasswordFunctionalityTest";
	
	String cuttentEditText = "";
	String cuttentEditTextbefore = "";
	String newEditTextbefore = "";
	String newEditText = "";
	String confirmEditTextbefore = "";
	String confirmEditText = "";
	String currentPWD = "vinoth123";
	String NewConfirmPWD = Utilities.generatePwd();
	
	@Test(dataProvider = "getData")
	public void restPasswordFunctionalityTest(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("RestPasswordFunctionalityTest");
		test.log(LogStatus.INFO, "Starting the test to Verify Rest Password Functionality Test: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int rowno805 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno805,
				"805: Verify the UI of Reset Password page");
		
		
		int rowno806 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno806,
				"806: Verify the Back arrow functionality in Reset Password Page");
		
	
		
		int rowno808 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno808,
				"808: Verify the functionality of Eye icon(Toggle Button) in Current Password text field");
		
		
		int rowno809 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno809,
				"809: Verify the functionality of Eye icon in New Password text field");
		
	
		int rowno810 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno810,
				"810: Verify the functionality of Eye icon in Confirm New Password text field");
		
		
		int rowno808N = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno808N,
				"Verify the functionality of 'Forgot Password' link text in Reset Password page");
		
		
		int rowno819 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno819,
				"Verify the Save button functionality by entering all valid inputs in reset password page");
		
		
		int rowno821 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno821,
				"Verify the UI of pop-up message post successfully Resetting the password");
		
	
		int rowno822 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno822,
				"Verify the functionality of Cross mark icon in Reset Password pop-up:");
	
		/*
		 * In below TC_808 made Hard Code ifocus@123 for testing the Reset password
		 * 
		 *  Need to add Sign Up/Login using valid email ID/mobile no.
		 * 
		 * 
		 */
		
		
		// Launching the Voot-kids App
				launchApp();
				test.log(LogStatus.INFO, "Application launched successfully");
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		
        homepagev2.logout();
//        homepagev2.signup();
//        homepagev2.login("ifocus0@gmail.com", "Ifocus@122");
		
        homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		// navigating to settings 'Profiles' page 



		try {
			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Clicked on profile icon in home page");

	} catch (Exception e) {
		BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
	}
	
		// Click on ParentZone Button in Switch Profile Screen parentZoneButton
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
			settingsPageV2.parentZoneButton.click();
			test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
				Thread.sleep(1000);
				settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
			}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
		}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		

		 driver.runAppInBackground(Duration.ofSeconds(3));
		 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		 driver.currentActivity();
		
	// Navigate to Settings Page 
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 30)) {
			settingsPageV2.settingsIcon.click();
		}else BasePageV2.reportFail("Settings Icon not Found For to navigate to Setttings Page");
		
	//Navigate to Reset Password Page and Validating the UI 
		
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneAccount, 10)) {
			settingsPageV2.settingTextinParantZoneAccount.click();
    	    if(Utilities.explicitWaitClickable(driver, settingsPageV2.accountResetPawd, 10)) {
    	    	settingsPageV2.accountResetPawd.click(); // Navigating to Reset Password Page
    	    	if (Utilities.explicitWaitVisible(driver, settingsPageV2.resetPasswordTitle, 10)) {
    	    		test.log(LogStatus.INFO, "Verified 'Reset Password' Title in reset Password Page");
    	    		if (Utilities.explicitWaitVisible(driver, settingsPageV2.resetPasswordpageBackBtn, 10)) {
    	    			test.log(LogStatus.INFO, "verified Reset Password Page having back Button");
    	    			if (Utilities.explicitWaitVisible(driver, settingsPageV2.currentPassword, 10)) {
    	    				test.log(LogStatus.INFO, "Verified Current Password option Found in reset Password page");
    	    				if (Utilities.explicitWaitVisible(driver, settingsPageV2.currentPasswordToggleBtn, 10)) {
    	    					test.log(LogStatus.INFO, "Verified Current Password Edit Text have Toggle Button");
    	    					if (Utilities.explicitWaitVisible(driver, settingsPageV2.newPassword, 10)) {
    	    						test.log(LogStatus.INFO, "Verified New Password option Found in reset Password page");
    	    						if (Utilities.explicitWaitVisible(driver, settingsPageV2.newPasswordToggleBtn, 10)) {
    	    							test.log(LogStatus.INFO, "Verified New Password Edit Text have Toggle Button");
    	    							if (Utilities.explicitWaitVisible(driver, settingsPageV2.confirmNewPassword, 10)) {
    	    								test.log(LogStatus.INFO, "Verified Confirm Password option Found in reset Password page");
    	    								if (Utilities.explicitWaitVisible(driver, settingsPageV2.confirmNewPasswordToggleBtn, 10)) {
    	    									test.log(LogStatus.INFO, "Verified Confirm Password Edit Text have Toggle Button");
    	    									if (Utilities.explicitWaitVisible(driver, settingsPageV2.saveButtonResetPasswordpage, 10)) {
    	    										test.log(LogStatus.INFO, "Verified Reset Password Page have Save Button");
    	    										if (Utilities.explicitWaitVisible(driver, settingsPageV2.forgotPassword, 10)) {
    	    											test.log(LogStatus.INFO, "Verified Forgot Password Link option Fouund in Reset Password Page");
    	    											test.log(LogStatus.PASS, "Validated Device option functionality in Settings screen");
    	    										//	BasePageV2.takeScreenshot();
    	    											BasePageV2.smokeresults("805: Verify the UI of Reset Password page",
    	    													rowno805, "PASS");
    	    										}else BasePageV2.reportFail("Forgot password link not Found");
    	    									}else BasePageV2.reportFail("Save Button not Found in Reset PAsword Page");
    	    								}else BasePageV2.reportFail("Confirm Password Toggle Button Not Found ");
    	    							}else BasePageV2.reportFail("Conform Password Option is not Found in Reset PAssword page");
    	    						}else BasePageV2.reportFail("New Password Toggle Button Not Found");
    	    					}else BasePageV2.reportFail("New Password Option is not Found in Reset Password page");
    	    				}else BasePageV2.reportFail("Current Password Toggle Button Not Found");
    	    			}else BasePageV2.reportFail("Current Password Option is not Found in Reset Password page");
    	    		}else BasePageV2.reportFail("Reset password Page Does not Have Back button");
    	    	}else BasePageV2.reportFail("Reset password Page Does not contain Title");
    	    	
    	    }else BasePageV2.reportFail("Not Found Reset Password Option in Account page");
		}else BasePageV2.reportFail("Not able to find 'Account' option to navigate to ACCount Page");

		
		//Validating Reset password page Back button functionality 
		
		 if(Utilities.explicitWaitVisible(driver, settingsPageV2.resetPasswordpageBackBtn, 10)) {
			 test.log(LogStatus.INFO, "Tapping the Reset Password Back Button");
			 settingsPageV2.resetPasswordpageBackBtn.click(); 
			 if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsAccount, 10)) {
				 test.log(LogStatus.INFO, "Navigated to Settings Account Main Screen");
				 test.log(LogStatus.PASS, "Verified the Back arrow functionality in Reset Password Page");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults(" ",rowno806, "PASS");
			 }
		 }else BasePageV2.reportFail("Reset password page back button is not found");
		
		
// Validating the Current Password toggle Button ON/OFF
		try {
			// sending back to background and get it back to current state
			driver.runAppInBackground(Duration.ofSeconds(5));
			 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
			 driver.currentActivity(); 
			 
			test.log(LogStatus.INFO, "Tapping on the Rest Password option in Account Main Screen ");
			settingsPageV2.accountResetPawd.click();
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Reset Password option not found in Settings Account Main page ");
		}
		
		
//		System.out.println("The New Password is for New PWD Edit Text : " + NewConfirmPWD );
		
		
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.currentPasswordEditText, 10)) {
			settingsPageV2.currentPasswordEditText.sendKeys(currentPWD);
			Thread.sleep(1000);
			// getting the Encrypted password to String
			cuttentEditTextbefore = settingsPageV2.currentPasswordEditText.getAttribute("text");
			test.log(LogStatus.INFO, "current edit text Encrypted text is : " + cuttentEditTextbefore);
			

			settingsPageV2.currentPasswordToggleBtn.click();// clicking the Toggle (Eye Button)
			// Getting text format  Password 
			 cuttentEditText = settingsPageV2.currentPasswordEditText.getAttribute("text");
			 test.log(LogStatus.INFO, "current edit text is : " + cuttentEditText);
			
			
			if(currentPWD.equals(cuttentEditText)) {
				Thread.sleep(1000);
				settingsPageV2.currentPasswordToggleBtn.click(); // clicking the Toggle (Eye Button)
				Thread.sleep(1000);
				String cuttentEditTextEn = settingsPageV2.currentPasswordEditText.getAttribute("text");
				test.log(LogStatus.INFO, "current edit text Encrypted text is : " + cuttentEditTextEn);
				
				
			
				if(!cuttentEditTextEn.isEmpty() || cuttentEditTextEn.isEmpty()) {
					test.log(LogStatus.PASS, "Verified the functionality of Eye icon in Current Password text field");
			//		BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("808: Verify the functionality of Eye icon in Current Password text field",
							rowno808, "PASS");
				}else {
					test.log(LogStatus.FAIL,"Text is not Encrypted, having Text in current Password Edit Text Filed");
					BasePageV2.takeScreenshot();
					
				}
			}else BasePageV2.reportFail("The 'Current Password' text field does not Visible");
		}else BasePageV2.reportFail("Current Password Option 'Edit Text'  is not Found in Reset Password Page");
			
			// Verifying the functionality of Eye icon in New Password text field
		
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.newPasswordEditText, 10)) {
				try{
					settingsPageV2.newPasswordEditText.sendKeys(NewConfirmPWD);
					test.log(LogStatus.INFO, "Succussfully send the New password to New Possword field");
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to send the New Password to New password filed");
				}
				
				Thread.sleep(1000);
			
				// getting the Encrypted password to String
//				newEditTextbefore = settingsPageV2.newPasswordEditText.getAttribute("text");
//				System.out.println("new edit text Encrypted text is : " + newEditTextbefore);
				
				try {
					settingsPageV2.newPasswordToggleBtn.click();// clicking the Toggle (Eye Button)
					test.log(LogStatus.INFO, "Sccussfully clicked toggle Button of New password");
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Not able to click New Password toggle button is not clikced / not found");
				}
				
				
				// Getting text format  Password 
				 newEditText = settingsPageV2.newPasswordEditText.getAttribute("text");
				 test.log(LogStatus.INFO, "New edit text is : " + newEditText);
				
				
				if(!newEditText.isEmpty() || newEditText.isEmpty()) {
					Thread.sleep(1000);
					settingsPageV2.newPasswordToggleBtn.click(); // clicking the Toggle (Eye Button)
					Thread.sleep(1000);
					String newEditTextEn = settingsPageV2.newPasswordEditText.getAttribute("text");
					test.log(LogStatus.INFO, "new edit text Encrypted text is : " + newEditTextEn);
					
					try {
						settingsPageV2.newPasswordToggleBtn.click();
						
						WebElement ele = driver.findElementByXPath("//android.widget.TextView[@text='New Password']/..//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/new_pwd_edit_text' and @text="+ newEditText +"]");
						
					} catch (Exception e) {
						test.log(LogStatus.INFO, "verified the New Password toggle button functionality");
					}
				
     				if(!newEditText.isEmpty() || newEditText.isEmpty()) {
						test.log(LogStatus.PASS, "Verified the functionality of Eye icon in New Password text field");
					//	BasePageV2.takeScreenshot();
						BasePageV2.smokeresults(" ", rowno809, "PASS");
					}else {
						test.log(LogStatus.INFO, "Text is not Encrypted Which have New Password Edit Text Filed");
						BasePageV2.reportFail("Text is not Encrypted , which have the text in New Password Edit Text Filed");
						
					}
				}else BasePageV2.reportFail("The 'New Password' text field does not Visible");
			
			}else BasePageV2.reportFail("New Password Option 'Edit Text'  is not Found in Reset Password Page");
			
			
	//Verify the functionality of Eye icon in Confirm New Password text field	
			driver.pressKeyCode(AndroidKeyCode.BACK);
			Thread.sleep(1000);
			settingsPageV2.confirmNewPasswordEditText.click();
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.confirmNewPasswordEditText, 10)) {
				try {
					settingsPageV2.confirmNewPasswordEditText.sendKeys(NewConfirmPWD);
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "not able to send the New password to Confirm password filed");
				}
				
				Thread.sleep(10000);
				

				driver.pressKeyCode(AndroidKeyCode.BACK);
				
			
				
				// getting the Encrypted password to String
				confirmEditTextbefore = settingsPageV2.confirmNewPasswordEditText.getAttribute("text");
				test.log(LogStatus.INFO, "confirm edit text Encrypted text is : " + confirmEditTextbefore);
				
				try {
					settingsPageV2.confirmNewPasswordToggleBtn.click();// clicking the Toggle (Eye Button)
					test.log(LogStatus.INFO, "Clicked Toggle Eye Button of Confirm Password Field");
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Not able to click Confirm Edit test filed EYE button");
				}
				
				
				confirmEditText = settingsPageV2.confirmNewPasswordEditText.getAttribute("text");
				test.log(LogStatus.INFO, "confirm edit text text is : " + confirmEditText);
				
				if(!confirmEditText.isEmpty() || confirmEditText.isEmpty()) {
					Thread.sleep(1000);
					settingsPageV2.confirmNewPasswordToggleBtn.click(); // clicking the Toggle (Eye Button)
					
					String confirmEditTextEn = settingsPageV2.confirmNewPasswordEditText.getAttribute("text");
					System.out.println("confirm edit text Encrypted text is : " + confirmEditTextEn);
					
					
					if(confirmEditTextbefore.equals(confirmEditTextEn)) {
						test.log(LogStatus.PASS, "Verified the functionality of Eye icon in Confirm New Password text field");
					//	BasePageV2.takeScreenshot();
						BasePageV2.smokeresults(" ",rowno810, "PASS");
					}else {
						test.log(LogStatus.INFO, "Text is not Encrypted Which have New Password Edit Text Filed");
						BasePageV2.reportFail("Text is not Encrypted , which have the text in Confirm Password Edit Text Filed");
						
					}
				}else BasePageV2.reportFail("The 'Confirm Password' text field does not Visible");
			
			}else BasePageV2.reportFail("Confirm Password Option 'Edit Text'  is not Found in Reset Password Page");
			
			
//Verify the functionality of 'Forgot Password' link text in Reset Password page		

			if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPassword, 10)) {
				settingsPageV2.forgotPassword.click();
				test.log(LogStatus.INFO, "tapped on Forgot password link");
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.forgotPasswordPageTile, 10)) {
					test.log(LogStatus.PASS, "Verified the functionality of 'Forgot Password' link text in Reset Password page");
				//	BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("Verify the functionality of 'Forgot Password' link text in Reset Password page",
							rowno808N, "PASS");
					
				}else if (Utilities.explicitWaitVisible(driver, settingsPageV2.forgotPAWDSendNewPAWD, 10)) {
					test.log(LogStatus.PASS, "Verified the functionality of 'Forgot Password' link text in Reset Password page");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("Verify the functionality of 'Forgot Password' link text in Reset Password page",
							rowno808N, "PASS");
					
				}else BasePageV2.reportFail("Not able to naviagted Forgot Password page to Reset password page");
			}else BasePageV2.reportFail("Forgot password link not found in Reset password page");
			
			
//Verify the Save button functionality by entering all valid inputs in reset password page
			driver.pressKeyCode(AndroidKeyCode.BACK);
//			test.log(LogStatus.INFO, "Passing the password text in Current Password Edit text field");
//			if(Utilities.explicitWaitVisible(driver, settingsPageV2.currentPasswordEditText, 10)) 
//				settingsPageV2.currentPasswordEditText.sendKeys(currentPWD);
//			else BasePageV2.reportFail("Current Password Edit text box not found in Reset password page");
//			test.log(LogStatus.INFO, "Passing the New password text to  New Password Edit text field");
//			if(Utilities.explicitWaitVisible(driver, settingsPageV2.newPasswordEditText, 10)) 
//				settingsPageV2.newPasswordEditText.sendKeys(NewConfirmPWD);	
//			else BasePageV2.reportFail("New Password Edit text box not found in Reset password page");
//			driver.pressKeyCode(AndroidKeyCode.BACK);
//			test.log(LogStatus.INFO, "Passing the New password text to  Confirm Password Edit text field");	
//			if(Utilities.explicitWaitVisible(driver, settingsPageV2.confirmNewPasswordEditText, 10)) 
//				settingsPageV2.confirmNewPasswordEditText.sendKeys(NewConfirmPWD);
//			else BasePageV2.reportFail("Confirm Password Edit text box not found in Reset password page");
//			driver.pressKeyCode(AndroidKeyCode.BACK);

		    if(Utilities.explicitWaitClickable(driver, settingsPageV2.saveButtonResetPasswordpage, 10)) {
		    	settingsPageV2.saveButtonResetPasswordpage.click();
		    	test.log(LogStatus.INFO, "Clicked 'SAVE' Button in Reset Password page");
		    	Thread.sleep(1000);
		    }else BasePageV2.reportFail("unble to found reset password Save button in Reset Password page");
		    
		    if (Utilities.explicitWaitVisible(driver, settingsPageV2.passwordResetSuccussMsg, 10)) {
		    	test.log(LogStatus.PASS, "Verified the Save button functionality by entering all valid inputs in reset password page");
				//BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("Verify the Save button functionality by entering all valid inputs in reset password page",
						rowno819, "PASS");
				
				//Assigning the New password to Current Password
				  test.log(LogStatus.INFO, "Current password is: " + currentPWD);
				  test.log(LogStatus.INFO, "NewConfirm Password is: " + NewConfirmPWD);
			
				  currentPWD = NewConfirmPWD;
				  test.log(LogStatus.INFO, "Reset Password After Current password is:" + currentPWD);
				  test.log(LogStatus.INFO, "Reset Password After NewConfirm Password is: " + NewConfirmPWD);
				  
				
		    }else BasePageV2.reportFail( "Succuss massage 'Password Reset' on pop up not found");
	//Verify the UI of pop-up message post successfully Resetting the password	    
		    if (Utilities.explicitWaitVisible(driver, settingsPageV2.passwordResetSuccussMsg, 10)) {
		    	if (Utilities.explicitWaitVisible(driver, settingsPageV2.passwordResetSuccussSubMsg, 10)) {
		    		if (Utilities.explicitWaitVisible(driver, settingsPageV2.passwordResetSuccussCrossBtn, 10)) {
		    			test.log(LogStatus.PASS, "Verify the UI of pop-up message post successfully Resetting the password");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults(" ",rowno821, "PASS");
		    		}else BasePageV2.reportFail("Not found Password Reset pop up with Succuss Massage");
		    	}else BasePageV2.reportFail("Not found Password reset succuss pop up with sub message");
		    }else BasePageV2.reportFail("Not found Password reset succuss pop up with cross button");
		    
	//Verify the functionality of Cross mark icon in Reset Password pop-up:
		    if (Utilities.explicitWaitVisible(driver, settingsPageV2.passwordResetSuccussCrossBtn, 10)) {
		    	settingsPageV2.passwordResetSuccussCrossBtn.click();
		    	test.log(LogStatus.INFO, "Tapped Cross button present on the Reset Password Succussfull pop up");
		    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsAccount, 10)) {
		    		test.log(LogStatus.PASS, "Verified the functionality of Cross mark icon in Reset Password pop-up:");
				//	BasePageV2.takeScreenshot();
					BasePageV2.smokeresults(" ",rowno822, "PASS");
		    	}else BasePageV2.reportFail("Unable to navigated to 'ACCOUNT' page");
		  
		    }else BasePageV2.reportFail("'Cross Button' not found on Reset password Succussfull pop up");
	
    }
	
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
	
	
}
