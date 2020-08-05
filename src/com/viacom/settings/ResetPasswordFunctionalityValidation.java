package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

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

public class ResetPasswordFunctionalityValidation extends BaseTestV2 {
	String testName = "ResetPasswordFunctionalityTest";

	@Test(dataProvider = "getData")
	public void restPasswordFunctionalityTest(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Settings Reset Password Page ");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
	
		 int rowno=xls.getRowCount("Smoke_Results")+1;
		 xls.setCellData("Smoke_Results", "Testcase Name",rowno,"Verify the Save button functionality by entering all valid inputs in reset password page)");	
		
		 int rowno1=xls.getRowCount("Smoke_Results")+1;
		 xls.setCellData("Smoke_Results", "Testcase Name",rowno1,"Verify the UI of pop-up message post successfully Resetting the password)");	
		
		 int rowno2=xls.getRowCount("Smoke_Results")+1;
		 xls.setCellData("Smoke_Results", "Testcase Name",rowno2,"Verify the functionality of Cross mark icon in Reset Password pop-up:)");	
		
		
		
		   
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
        SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
        HomePageV2 homepagev2 = new HomePageV2(driver, test);

//       homepagev2.logout();
//       homepagev2.login("ifocus0@gmail.com", "Ifocus@122");


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

       //Navigating to Password Reset Main Page
        if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneAccount, 10)) {
			settingsPageV2.settingTextinParantZoneAccount.click();
          }else BasePageV2.reportFail("'Account' option is not found in Settings Main Page");
    	    if(Utilities.explicitWaitClickable(driver, settingsPageV2.accountResetPawd, 10)) {
    	    	settingsPageV2.accountResetPawd.click(); 
    	    }else BasePageV2.reportFail("'Reset Password' Option not found in ACCOUNT Page / Unable to Clikable");
    	    //Verify the Save button functionality by entering all valid inputs in reset password page
    	    	if (Utilities.explicitWaitVisible(driver, settingsPageV2.resetPasswordTitle, 10)) {
    	    		test.log(LogStatus.INFO, "Verified 'RESET PASSWORD' Title in Reset Password Page");
    	    	}else test.log(LogStatus.FAIL, "unable to Navigate to Reset Password Settings Page");
    	    	
    	    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.currentPasswordEditText, 10)) {
    				settingsPageV2.currentPasswordEditText.sendKeys("Ifocus@122");
    	    	}else BasePageV2.reportFail("Unable to find 'Current Password' Edit Text box");
    	    	
    	    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.newPasswordEditText, 10)) {
    				settingsPageV2.newPasswordEditText.sendKeys("Ifocus@1234");
    	    	}else BasePageV2.reportFail("Unable to Found 'New Password' Edit text box ");
    	    	
    	    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.confirmNewPasswordEditText, 10)) {
    				settingsPageV2.confirmNewPasswordEditText.sendKeys("Ifocus@1234");
    	    	}else BasePageV2.reportFail("Unable to find the 'Confirm New password' Edit text box");
    	    	
    	    	if(Utilities.explicitWaitClickable(driver, settingsPageV2.saveButtonResetPasswordpage, 10)) {
    			    	settingsPageV2.saveButtonResetPasswordpage.click();
    			    	Thread.sleep(1000);
    			}else BasePageV2.reportFail("unble to found reset password Save button in Reset Password page");
    			    
		
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
