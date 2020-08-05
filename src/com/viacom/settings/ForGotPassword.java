package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class ForGotPassword extends BaseTestV2{
String testName = "ForGotPassword";
String str ="",Newpassword="";
String loginEmail = "tuesdayvk001@gmail.com";
String NewPWD = Utilities.generatePwd();  // generate random password 

	@Test(dataProvider = "getData")
	public void forGotPassword(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("ForGotPassword");
		test.log(LogStatus.INFO, "Starting the test to ForGot Password: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int VK_98 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_98,"Verify the password received in email post performing 'Forgot Password':");
		
		int VK_100 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_100,"Verify the flow post performing 'Forgot Password' from Login screen:");
		
		int VK_101 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_101,"Verify the reset password functionality post performing 'Forgot Password' from Login screen:");
		
		/*
		 * 
		 * GMAIL ACCOUNT -- automationvk1@gmail.com ----   Password : Ifocus123  ---- 8904584339
		 * 
		 * 
		 */
		
		
		
		
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		
//		login if not login
//		try {
//			homepagev2.login("forgotpwd@gmail.com", "Ifocus@122"); 
//	        }catch(Exception e) {
//	        	test.log(LogStatus.FAIL, "login Failed");
//	        }   // Mobile Number for this Account is 8904584251
		
		homepagev2.login(data.get("Email"),data.get("Password"));

	
		
		
//		homepagev2.logout();
		
		//click login Link
		if(Utilities.explicitWaitVisible(driver,launchPageV2.Login , 10)) {
			launchPageV2.Login.click();
			test.log(LogStatus.INFO, "clicked Login Link");
		}else BasePageV2.reportFail("Login Link not found in Welcome screen");
		
		//click Forgot Password
		 if(Utilities.explicitWaitClickable(driver, launchPageV2.forgotPwd, 10)) {
			 launchPageV2.forgotPwd.click();
			 test.log(LogStatus.INFO, "Clicked Forgot password Link");
		 }else BasePageV2.reportFail("Not able to click 'Forgot Password' Link in Home page/ Not found");
		 
		 //sending the Gmail id 
		 if(Utilities.explicitWaitVisible(driver, homepagev2.editTextForGotpassword, 5)) {
			 homepagev2.editTextForGotpassword.sendKeys(loginEmail);
			 test.log(LogStatus.INFO, "sent the Gmail to Forgot Password Edit Text Box");
		 }else test.log(LogStatus.FAIL, "Unable to send the Gmail id to Forgot Password Edit Text box");
		 
		 //click Send NEw Password Button
		 if(Utilities.explicitWaitClickable(driver, homepagev2.sendNewPasswordBtn, 10)) {
			 homepagev2.sendNewPasswordBtn.click();
			 test.log(LogStatus.INFO, "Clikced the Send New Password Button in Forgot Password page");
		 }else BasePageV2.reportFail("Not able to click the 'SEND NEW PASSWORD' Button in Forgot password page / not found");
		
		
		 
		 if(Utilities.explicitWaitVisible(driver, homepagev2.resetRequestFGPWD, 10)) {
			 test.log(LogStatus.INFO, "Successfully sent the New Reset Password to Gmail Account");
			 //click Back To Login Button

			 if(Utilities.explicitWaitClickable(driver, homepagev2.backToLoginBtn, 10)) {
				 homepagev2.backToLoginBtn.click();
				 test.log(LogStatus.INFO, "Clicked 'BACK TO LOGIN' Button on Reset Request pop up");
				 Thread.sleep(1000);
			 }else test.log(LogStatus.FAIL, "Not able to click the 'BACK TO LOGIN' button on Reset Request Pop up / not found");
			 
		 }else test.log(LogStatus.FAIL, "Not able to send New Reset Request Password to Gmail Account");
		 
        Thread.sleep(200000); // sleep for sending password to gmail Account
		
		   try{
				driver.startActivity(new Activity("com.google.android.gm", "com.google.android.gm.ConversationListActivityGmail"));
			    test.log(LogStatus.INFO, "Switched to Gamil to get Reset Password");
		 }
		 catch(Exception e){
			 test.log(LogStatus.FAIL, "unable to switch to 'GMAIL' App to get New Reset Password");
		 }
		
		Thread.sleep(200000);
		

		try {
			//getting Password from Gmail
			str = driver.findElementByXPath("//android.view.View[contains(@content-desc,\"Voot kids Support, VootKids :: Forgot password\")]").getAttribute("name");
			test.log(LogStatus.INFO, str);
			System.out.println("The Gmail password string is " + str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// From str extracting the String Password 
		String[] substr = str.split(" ");
		System.out.println(substr[16]);
		String[] substr1=substr[16].split(",");
		Newpassword = substr1[0];
			test.log(LogStatus.INFO, "The Password from Gmail is : " + Newpassword );
			System.out.println("The Password from Gmail is : " + Newpassword);
		
		if(substr1.length != 0) {
			
			test.log(LogStatus.PASS,"Verify the password received in email post performing 'Forgot Password':");
			BasePageV2.smokeresults("", VK_98, "PASS");
			
		}else {
			test.log(LogStatus.FAIL, "New Password is not found / not got from Gmail");
			BasePageV2.takeScreenshot();
			BasePageV2.smokeresults("", VK_98, "FAIL");
		}
		
		

		   try{
					driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
				    test.log(LogStatus.INFO, "Switched to Vook kids App to Login");
			 }
			 catch(Exception e){
				 test.log(LogStatus.FAIL, "unable to switch to 'VOOt Kids' App to Login");
			 }
	  	
		
			//click login Link
			if(Utilities.explicitWaitVisible(driver,launchPageV2.Login , 10)) {
				launchPageV2.Login.click();
				test.log(LogStatus.INFO, "clicked Login Link");
			}else BasePageV2.reportFail("Login Link not found in Welcome screen"); 
		
			if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25))
			{
				launchPageV2.emailText.click();
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(loginEmail);
				test.log(LogStatus.INFO, "Sent the Email id to Login Email Edit text box");
			}else BasePageV2.reportFail("unable to sent the Email id to Login page Email id Edit text box");
		
		 //send New Password 
			if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5))
			{
				launchPageV2.pwdText.click();
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(Newpassword);
                test.log(LogStatus.INFO, "Sent New password to Password Edit text field in Login page");
			}else BasePageV2.reportFail("Not able to sent the New password to Password edit text box in Login page");
			
        //click Login button		
			if (Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5))
			{
				launchPageV2.loginButton.click();
				test.log(LogStatus.INFO, "clicked Login Button");
			}
			else
			{
				BasePageV2.reportFail("Login Button not found");
			}
		
	//navigate to Reset Password Page
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.resetPasswordTitle, 10)) {
				test.log(LogStatus.PASS,"Verify the flow post performing 'Forgot Password' from Login screen:");
				BasePageV2.smokeresults("", VK_100, "PASS");
				
			}else {
				BasePageV2.smokeresults("", VK_100, "FAIL");
				BasePageV2.reportFail("Not able Navigated to 'RESET PASSWORD' Page");
			}
		
		
//	Verify the reset password functionality post performing 'Forgot Password' from Login screen:
			
			//current Password Edit Text
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.currentPasswordEditText, 10)) {
				settingsPageV2.currentPasswordEditText.sendKeys(Newpassword);  // sending the New Password
				test.log(LogStatus.INFO, "Sent 'Current Password' to Current password Edit Text Box in Reset Password page");
			}else BasePageV2.reportFail("Current Password Option 'Edit Text' box  is not Found in Reset Password Page");
			
			//New Password Edit Text
			
				try{
					settingsPageV2.newPasswordEditText.sendKeys(NewPWD);
					test.log(LogStatus.INFO, "Successfully send the New password to New Possword field " + NewPWD);
					driver.pressKeyCode(AndroidKeyCode.BACK);
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to send the New Password to New password filed");
				}
		
			    
			//confirm password
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.confirmNewPasswordEditText, 10)) {
				settingsPageV2.confirmNewPasswordEditText.sendKeys(NewPWD);
				test.log(LogStatus.INFO, "Successfully send the New password to New Possword field " + NewPWD );
				driver.pressKeyCode(AndroidKeyCode.BACK);
			}else BasePageV2.reportFail("New Password Option 'Edit Text'  is not Found in Reset Password Page");
			
			//click save button
			
		    if(Utilities.explicitWaitClickable(driver, settingsPageV2.saveButtonResetPasswordpage, 10)) {
		    	settingsPageV2.saveButtonResetPasswordpage.click();
		    	Thread.sleep(1000);
		    }else BasePageV2.reportFail("Not able to Click SAVE button in Reset Password page / not found");
			
			//Reset Password Successfull Massage
		    if (Utilities.explicitWaitVisible(driver, settingsPageV2.passwordResetSuccussMsg, 10)) {
		    	test.log(LogStatus.PASS, "Successfully reset The Password");
			    test.log(LogStatus.PASS,"Verify the reset password functionality post performing 'Forgot Password' from Login screen:");
			    BasePageV2.smokeresults("",VK_101, "PASS");
				  
				
		    }else {
		    	BasePageV2.smokeresults("",VK_101, "FAIL");
		    	BasePageV2.reportFail( "Succuss massage 'Password Reset' on pop up not found");
		    }
			
		    //click Cross button on reset Password Successful Massage Pop up
		    if (Utilities.explicitWaitVisible(driver, settingsPageV2.passwordResetSuccussCrossBtn, 10)) {
		    	settingsPageV2.passwordResetSuccussCrossBtn.click();
		    }else BasePageV2.reportFail("'Cross Button' not found on Reset password Successfull pop up");
			
			
			
			
			
			
		
		
		
		
	}
	

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
