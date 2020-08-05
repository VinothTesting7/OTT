package com.viacom.smoketestscripts;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class VerifyForGotPINFunctionality extends BaseTestV2{
	
String testName = "VerifyForGotPINFunctionality";
    
	@Test(dataProvider = "getData")
	public void verifyForGotPINFunctionality(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyForGotPINFunctionality");
		test.log(LogStatus.INFO, "Starting the test to verify ForGot PIN Functionality " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int rowno712 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno712, "Verify Forgot PIN link functionality:");
		
		int rowno713 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno713, "Verify Submit button functionality by entering incorrect PIN:");
		
		int rowno714 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno714, "Verify Forgot Pin button functionality by entering incorrect PIN:");
		
		int rowno715 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno715, "Verify Forgot Pin button functionality by entering incorrect PIN:");
		
		int rowno716 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno716, "Verify Forgot Pin button functionality by entering incorrect PIN:");
		
		int rowno717 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno717, "Verify Forgot Pin button functionality by entering incorrect PIN:");
		
		int rowno718 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno718, "Verify Forgot Pin button functionality by entering incorrect PIN:");
	
		int rowno719 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno719, "Verify Forgot Pin button functionality by entering incorrect PIN:");
		
		int rowno720 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno720, "Verify Forgot Pin button functionality by entering incorrect PIN:");
		
		
		
		/*
		 * Submit button need to add in this class once implmented
		 * 
		 * 
		 */
		
	
		//launching the vook kids Application
		launchApp();
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test); 
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		

		homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		
		
		try {

				homepagev2.profilepic.click(); // tap on profile icon
				test.log(LogStatus.INFO, "Clicked on profile icon in home page");

		} catch (Exception e) {
			BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
		}
		
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
			settingsPageV2.settingsIcon.click();
			test.log(LogStatus.INFO, "clicked settings Icon");
		}else BasePageV2.reportFail("Unable to Click Settings ICon / not found");
		
//	Verify Forgot PIN link functionality:
		
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.forGotPinText, 10)) {
			settingsPageV2.forGotPinText.click();
			test.log(LogStatus.INFO, "clicked Forgot PIn link");
		}else test.log(LogStatus.FAIL, "Not able to click Forgot PIN link / Not found Forgot Pin Link");
			
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.forgotPinPopUpTitle, 5)) {
			test.log(LogStatus.PASS,"Verified Forgot PIN link functionality:");
			BasePageV2.smokeresults("", rowno712, "PASS");
			
		}else {
			test.log(LogStatus.FAIL, "Not able to Verify Forgot PIN link functionality");
			BasePageV2.takeScreenshot();
			BasePageV2.smokeresults("", rowno712, "FAIL");
		}
		
		
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinNOBtn, 5)) {
			settingsPageV2.forgotPinNOBtn.click();
			test.log(LogStatus.INFO, "clicked No button on forgot pin pop up");
		}else test.log(LogStatus.FAIL, "Not able to click No button on forgot pin pop / not found");
		
		
		
		
	// 	Verify Submit button functionality by entering incorrect PIN:
		
		try {
			settingsPageV2.parentPinContainer.sendKeys("9559");   // Seding Wrong Pin in Pin container
			test.log(LogStatus.INFO, "Succussfully entered the Wrong Pin in Parent Zone Page");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.forgotPinResetPinText, 5)) {
				test.log(LogStatus.PASS,"Verify Submit button functionality by entering incorrect PIN:");
				BasePageV2.smokeresults("", rowno713, "FAIL");
				
			}else {
				test.log(LogStatus.FAIL, "Verify Submit button functionality by entering incorrect PIN:");
				BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("", rowno713, "FAIL");
			}
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Not entered the pin '9559' in Parent Zone page");
		}
		
//   Verify Forgot Pin button functionality by entering incorrect PIN: (NO Button)
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinNOBtn, 5)) {
			settingsPageV2.forgotPinNOBtn.click();
			test.log(LogStatus.INFO, "clicked No Button on Forgot pin pop up");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.forGotPinText, 10)) {
				test.log(LogStatus.PASS,"Verify Forgot Pin button functionality by entering incorrect PIN:");
				BasePageV2.smokeresults("", rowno715, "PASS");
			}else {
				test.log(LogStatus.FAIL, "Not able to navigated to PARENT ZONE page");
				BasePageV2.smokeresults("", rowno715, "FAIL");
			}
			
		}else test.log(LogStatus.FAIL, "Unable to click NO button on forgotpin Pop up");
		
	
		settingsPageV2.parentPinContainer.clear();
		
//   Verify Forgot Pin button functionality by entering incorrect PIN:
				
		try {
			settingsPageV2.parentPinContainer.sendKeys("9559");   // Seding Wrong Pin in Pin container
			test.log(LogStatus.INFO, "Succussfully entered the Wrong Pin in Parent Zone Page");
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinYESBtn, 5)) {
				settingsPageV2.forgotPinYESBtn.click();
				test.log(LogStatus.INFO, "clicked yes Button on forgotpin pop up ");
			}else test.log(LogStatus.FAIL, "Not able to click YES button on forgot pin pop up / not found");
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinNOBtn, 5)) {
				settingsPageV2.forgotPinNOBtn.click();
				test.log(LogStatus.INFO, "clicked No button on forgot pin pop up");
			}else test.log(LogStatus.FAIL, "Not able to click No button on forgot pin pop / not found");
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.forGotPinText, 10)) {
				test.log(LogStatus.PASS,"Verify Forgot Pin button functionality by entering incorrect PIN:");
				BasePageV2.smokeresults("", rowno717, "PASS");
			}else {
				test.log(LogStatus.FAIL, "Not able to navigated to PARENT ZONE page");
				BasePageV2.smokeresults("", rowno717, "FAIL");
			}
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Not entered the pin '9559' in Parent Zone page");
		}
		
		
		
		settingsPageV2.parentPinContainer.clear();
		
		
//		Verify Forgot Pin button functionality by entering incorrect PIN:
		
		try {
			settingsPageV2.parentPinContainer.sendKeys("9559");   // Seding Wrong Pin in Pin container
			test.log(LogStatus.INFO, "Succussfully entered the Wrong Pin in Parent Zone Page");
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinYESBtn, 5)) {
				settingsPageV2.forgotPinYESBtn.click();
				test.log(LogStatus.INFO, "clicked yes Button on forgotpin pop up ");
			}else test.log(LogStatus.FAIL, "Not able to click YES button on forgot pin pop up / not found");
			
//			Verify Forgot Pin button functionality by entering incorrect PIN:
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.fogottenBodyText, 10)) {
				test.log(LogStatus.PASS,"Verify Forgot Pin button functionality by entering incorrect PIN:");
				BasePageV2.smokeresults("", rowno714, "PASS");
			}else {
				test.log(LogStatus.FAIL, "Not found Forgot pin body text on forgotpin pop up");
				BasePageV2.smokeresults("", rowno714, "FAIL");
			}
			
			
			
			
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinYESBtn, 10)) {
				settingsPageV2.forgotPinYESBtn.click();
				test.log(LogStatus.INFO, "clicked YES button on forgot pin pop up");
			}else test.log(LogStatus.FAIL, "Not able to click YES button on forgot pin pop / not found");
			
			Thread.sleep(2000);  // loading while sending to forgot pin to user email
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.forGotPinText, 10)) {
				test.log(LogStatus.PASS,"Verify Forgot Pin button functionality by entering incorrect PIN:");
				BasePageV2.smokeresults("", rowno716, "PASS");
			}else {
				test.log(LogStatus.FAIL, "Not able to navigated to PARENT ZONE page");
				BasePageV2.smokeresults("", rowno716, "FAIL");
			}
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Not entered the pin '9559' in Parent Zone page");
		}
		
		
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
			settingsPageV2.settingsIcon.click();
			test.log(LogStatus.INFO, "clicked settings Icon");
		}else BasePageV2.reportFail("Unable to Click Settings ICon / not found");
		
		
		
//		Verify Forgot Pin button functionality by entering incorrect PIN:
		
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.forGotPinText, 10)) {
			settingsPageV2.forGotPinText.click();
			test.log(LogStatus.INFO, "clicked Forgot PIn link");
		}else test.log(LogStatus.FAIL, "Not able to click Forgot PIN link / Not found Forgot Pin Link");
		
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.forgotpinPopUpBdyText, 5)) {
			test.log(LogStatus.PASS,"Verify Forgot Pin button functionality by entering incorrect PIN:");
			BasePageV2.smokeresults("", rowno718, "PASS");
		}else {
			test.log(LogStatus.FAIL, "Verify Forgot Pin button functionality by entering incorrect PIN:");
			BasePageV2.takeScreenshot();
			BasePageV2.smokeresults("", rowno718, "FAIL");
		}
		
//		Verify Forgot Pin button functionality by entering incorrect PIN:
		
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinYESBtn, 5)) {
			settingsPageV2.forgotPinYESBtn.click();
			test.log(LogStatus.INFO, "clicked yes Button on forgotpin pop up ");
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.forGotPinText, 10)) {
				test.log(LogStatus.PASS,"Verify Forgot Pin button functionality by entering incorrect PIN:");
				BasePageV2.smokeresults("", rowno719, "PASS");
			}else {
				test.log(LogStatus.FAIL, "Not able Naviagted tp PARENT ZONE page");
				BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("", rowno719, "FAIL");
			}
		}else test.log(LogStatus.FAIL, "Not able to click YES button on forgot pin pop up / not found");
	
		
		
//		Verify Forgot Pin button functionality by entering incorrect PIN:
		
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.forGotPinText, 10)) {
			settingsPageV2.forGotPinText.click();
			test.log(LogStatus.INFO, "clicked Forgot PIn link");
			
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.forgotPinNOBtn, 5)) {
				settingsPageV2.forgotPinNOBtn.click();
				test.log(LogStatus.INFO, "clicked No button on forgot pin pop up");
				
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.forGotPinText, 10)) {
					test.log(LogStatus.PASS,"Verify Forgot Pin button functionality by entering incorrect PIN:");
					BasePageV2.smokeresults("", rowno720, "PASS");
				}else {
					test.log(LogStatus.FAIL, "Not able Naviagted tp PARENT ZONE page");
					BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("", rowno720, "FAIL");
				}
				
			}else test.log(LogStatus.FAIL, "Not able to click No button on forgot pin pop / not found");
			
		}else test.log(LogStatus.FAIL, "Not able to click Forgot PIN link / Not found Forgot Pin Link");
		


	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
