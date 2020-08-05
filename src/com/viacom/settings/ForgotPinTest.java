package com.viacom.settings;

import java.util.Hashtable;
import java.util.Random;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.ForgotPinPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;

public class ForgotPinTest extends BaseTestV2{
	String testName = "ForgotPinTest";
	
	String testCase = "Verify Forgot Pin button functionality by tapping on 'Yes' option in native message";
	String testId = "VK_776";


	String testCase1 = "Verify the reset PIN functionality post performing 'Forgot PIN' flow";
	String testId1 = "VK_1485";

	String testCase2 = "Verify if forgot PIN request is sent to user's email ID when logged in via Email	";
	String testId2 = "VK_1696";
	
	String randomWord="";
	
	@Test(dataProvider = "getData")
	public void OTPLimitExceed(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Verify " + testName);
		test.log(LogStatus.INFO, "Starting the test " + testName);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		ForgotPinPageV2 forgotpin = new ForgotPinPageV2(driver, test);
		
		driver.resetApp();
		Thread.sleep(5000);
		
		String email = data.get("Email");
		String pwd = data.get("Password");
		String pin = data.get("Pin");

		HomePageV2.login(email, pwd);
		
		if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20))
			homepagev2.profilepic.click();
		else BasePageV2.reportFail("Profile Icon not displayed");
		
		if(Utilities.explicitWaitVisible(driver, homepagev2.settings, 20))
			homepagev2.settings.click();
		else BasePageV2.reportFail("Settings icon was not displayed");
		
		if(Utilities.explicitWaitVisible(driver, forgotpin.forgotpinLink, 20))
			forgotpin.forgotpinLink.click();
		else BasePageV2.reportFail("Forgot pin link was not displayed");
		
		if(Utilities.explicitWaitVisible(driver, homepagev2.confirmLogout, 20))
			homepagev2.confirmLogout.click();
		else BasePageV2.reportFail("Yes/No option was not displayed");
		
		ForgotPinPageV2.logintogmailandfetchnewPwd("forgotpwdvoot@gmail.com", "ForgotPWD123");
		
		pin = ForgotPinPageV2.newpwdgmail;
		
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		

		
		
		try {
			String strNumbers = "0123456789";
			Random random = new Random();
			StringBuilder strRandomNumber = new StringBuilder(9);
			for (int i = 0; i < 4; i++) {
				strRandomNumber.append(strNumbers.charAt(random.nextInt(strNumbers.length())));
			}
			randomWord = strRandomNumber.toString();
			System.out.println("4 digit pin = "+randomWord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		
		if (Utilities.explicitWaitVisible(driver, forgotpin.resetPinPage, 25)) {
			
			test.log(LogStatus.INFO, "Reset Pinn page was not displayed");
			test.log(LogStatus.PASS, "Test Case: "+testCase+" is Pass");
			try {
				Utilities.setResultsKids(testId, "Pass");
			} catch (Exception e) {
				
			}
		}
			
		 else
			BasePageV2.reportFail("Reset pin page was not displayed");
			
		
		//Resetting pin 1st time
		
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(randomWord);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		
		if (Utilities.explicitWaitVisible(driver, forgotpin.savepwdButton, 25)) 
			forgotpin.savepwdButton.click();
		 else
			BasePageV2.reportFail("Reset pin page was not displayed");
	
		//Confirming pin 
		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(randomWord);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		
		if (Utilities.explicitWaitVisible(driver, forgotpin.savepwdButton, 25)) 
			forgotpin.savepwdButton.click();
		 else
			BasePageV2.reportFail("Reset pin page was not displayed");
		
		
		if (Utilities.explicitWaitVisible(driver, forgotpin.pinresetConfirmPopup, 25)) {
			test.log(LogStatus.INFO, "Pin Reset confirm popup displayed");
			test.log(LogStatus.PASS, "Test Case: "+testCase1+" is Pass");
			try {
				Utilities.setResultsKids(testId1, "Pass");
			} catch (Exception e) {
				
			}
		}
			
		 else
			BasePageV2.reportFail("Pin Reset confirm popup not displayed");
		
		if (Utilities.explicitWaitVisible(driver, forgotpin.pinresetsuccessMsg, 25)) {
			test.log(LogStatus.INFO, "Successfully pin reset done");
		}
			
		 else
			BasePageV2.reportFail("Pin reset fail");
		
	//	xls.changeCellData(testName, randomWord);
		
		if (Utilities.explicitWaitVisible(driver, forgotpin.popupClose, 25)) 
			forgotpin.popupClose.click();
		 else
			BasePageV2.reportFail("Close popup (X) icon not displayed");
		
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
		driver.navigate().back();
		
		driver.resetApp();
		Thread.sleep(5000);
		//Utilities.generateMobileNumber();
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
