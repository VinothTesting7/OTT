package com.viacom.settings;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

public class SignUpPageUI_P1 extends BaseTestV2 {
	
	
String testName = "SignUpPageUI_P1";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SignUpPageUI_P1");
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
		
		String userName = data.get("Email");
		String password = data.get("Password");

		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		// Log out from the App
		
		homepagev2.logout();
		
		
		// VK_2185 - Verify the functionality of 'Privacy Policy' link present in sign up screen:
		
		// click on Sign Up button
		if(Utilities.explicitWaitClickable(driver, signUp.joinFreefor30Days, 30)) {
			signUp.joinFreefor30Days.click();
			test.log(LogStatus.INFO, "click on SignUp button");
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
			if(Utilities.explicitWaitClickable(driver, signUp.signUpPrivacyPolicy, 30)) {
				signUp.signUpPrivacyPolicy.click();
				test.log(LogStatus.INFO, "click on Privacy Policy link in SignUp page");
				if(Utilities.explicitWaitVisible(driver, signUp.signUpPrivacyPolicyTitle, 30)) {
					test.log(LogStatus.PASS, "Verify the functionality of 'Privacy Policy' link present in sign up screen:");
					if(!Utilities.setResultsKids("VK_2185", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					driver.pressKeyCode(AndroidKeyCode.BACK);
					driver.pressKeyCode(AndroidKeyCode.BACK);
				} else {
					test.log(LogStatus.FAIL, "Verify the functionality of 'Privacy Policy' link present in sign up screen:");
					BasePageV2.takeScreenshot();
					if(!Utilities.setResultsKids("VK_2185", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					driver.pressKeyCode(AndroidKeyCode.BACK);
					driver.pressKeyCode(AndroidKeyCode.BACK);
				}
				
				
				
			}else {
				test.log(LogStatus.FAIL, "Failed to click on Privacy Policy link in Sign up screen/Not displayed");
				BasePageV2.takeScreenshot();
				driver.pressKeyCode(AndroidKeyCode.BACK);
			}
			
		}else {
			test.log(LogStatus.FAIL, "Failed to click on SignUp button / Not displayed");
		}
		
		
		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		
		
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	

}
