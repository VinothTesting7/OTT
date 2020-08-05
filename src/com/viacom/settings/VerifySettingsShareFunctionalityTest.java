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

public class VerifySettingsShareFunctionalityTest extends BaseTestV2{
	
String testName = "VerifySettingsShareFunctionalityTest";
    
	
	@Test(dataProvider = "getData")
	public void verifySettingsProfilesScreen(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifySettingsShareFunctionalityTest");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Share Functionality Test " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int rowno902 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno902, "Validate the deeplink functionality from the shared app when Voot Kids app is not installed in device:");
		
		int rowno903 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno903, "Validate the deeplink functionality from the shared app when Voot Kids app is installed in device:");
		
		/*
		 * Preconditon : Voot Application should installed in Device for Share Feature
		 */
		

		          
		          
//		          driver.installApp("\\apk\\voot_1.6.114_playkit.apk");
		
//		         installVootApp();    // installing the Voot App 
		
		// Launching the Voot-kids App
		
				launchApp();
				
				test.log(LogStatus.INFO, "Application launched successfully");

				LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
				SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
				HomePageV2 homepagev2 = new HomePageV2(driver, test);
				BasePageV2 BasePageV2 = new BasePageV2(driver, test);
				
				

				 homepagev2.login(data.get("Email"),data.get("Password"));
				
		
		        Thread.sleep(1000);
		        if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "clicked on profile pic icon in home page");

				}else BasePageV2.reportFail("Not able to clicked Profile Icon / Not found");
		        
		
		     // Click on ParentZone Button in Switch Profile Screen parentZoneButton
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
					settingsPageV2.parentZoneButton.click();
					test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
						Thread.sleep(1000);
						settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
				}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		        
				// putting App in background
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();
				
				
		        if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
					settingsPageV2.settingsIcon.click();
					test.log(LogStatus.INFO, "clicked on Settings ICon");
		        }else BasePageV2.reportFail("Not able to click Settings Icon / not found ");
		        
		        if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsShare, 5)) {
		        	settingsPageV2.settingsShare.click();
		        }else BasePageV2.reportFail("Not able to found Share option in Settings Page");
		        
		        
		        if(Utilities.explicitWaitClickable(driver, settingsPageV2.shareTweetBtn, 10)) {
		        	settingsPageV2.shareTweetBtn.click();
		        	Thread.sleep(1000);
		        	test.log(LogStatus.INFO, "Clicked Tweet Button in Share Page");
		        }else BasePageV2.reportFail("Not able to Click Tweet button in Share page/ not found");
		        
		         		        
		        
		        try{
					driver.startActivity(new Activity("com.twitter.android", "com.twitter.android.StartActivity"));
				 
			 }
			 catch(Exception e){
//				 test.log(LogStatus.FAIL, "unable to switch to 'TWITTER' App to click the Voot share Link");
				 e.printStackTrace();
			 }
		        
		        
		        try {
		        	Utilities.verticalSwipeDown(driver, settingsPageV2.vootTW);
			        settingsPageV2.vootTW.click();
			        test.log(LogStatus.INFO, "clicked Voot share Link in Tiwtter App");
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Unable to click VOOT share Link in twitter app");
				}
		       
		        if (Utilities.explicitWaitVisible(driver, settingsPageV2.homeVootapp, 80)) {
					test.log(LogStatus.PASS,"Validate the deeplink functionality from the shared app when Voot Kids app is not installed in device:");
					BasePageV2.smokeresults("", rowno903, "PASS");
				}else {
					test.log(LogStatus.FAIL, "Not able to Navigated to Voot App from Twitter App");
					BasePageV2.smokeresults("", rowno903, "FAIL");
				}
		        
		        
		        driver.closeApp();
		        
		        
		        
//		  Validate the deeplink functionality from the shared app when Voot Kids app is not installed in device:      
		        
		        
		        try{
					driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
				  System.out.println("found");
				  test.log(LogStatus.INFO, "Launched Voot Kids App");
			 }
			 catch(Exception e){
				 test.log(LogStatus.FAIL, "unable to switch to Voot Kids App to click the Voot share Link");
			 }
		        
		        
		        if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Succusfully entered to Switch profile page");

				}else BasePageV2.reportFail("Not able to clicked Profile Icon / Not found");
		        
		
		        // Click on ParentZone Button in Switch Profile Screen parentZoneButton
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
					settingsPageV2.parentZoneButton.click();
					test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
						Thread.sleep(1000);
						settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
				}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		        
		        
				// putting App in background
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();
				
		        if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
					settingsPageV2.settingsIcon.click();
					test.log(LogStatus.INFO, "Clicked on Settings Icon");
		        }else BasePageV2.reportFail("Not able to click Settings Icon / not found ");
		        
		        if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsShare, 5)) {
		        	settingsPageV2.settingsShare.click();
		        }else BasePageV2.reportFail("Not able to found Share option in Settings Page");
		        
		        
		        if(Utilities.explicitWaitClickable(driver, settingsPageV2.shareTweetBtn, 10)) {
		        	settingsPageV2.shareTweetBtn.click();
		        	Thread.sleep(1000);
		        	test.log(LogStatus.INFO, "Clicked Tweet Button in Share Page");
		        }else BasePageV2.reportFail("Not able to found Share option in Settings Page");
		        
		        driver.pressKeyCode(AndroidKeyCode.BACK);
		        driver.pressKeyCode(AndroidKeyCode.BACK);

		         try {
		        	 driver.removeApp("com.tv.v18.viola");
		        	 test.log(LogStatus.INFO, "Unistalled the VOOT application");
		        	 Thread.sleep(1000);
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Unable to unistalled the VOOT application");
				}
		        
		         

			        try{
						driver.startActivity(new Activity("com.twitter.android", "com.twitter.android.StartActivity"));
					  
				 }
				 catch(Exception e){
					 test.log(LogStatus.FAIL, "unable to switch to 'TWITTER' App to click the Voot share Link");
				 }
			        
			        
			        try {
			        	Utilities.verticalSwipeDown(driver, settingsPageV2.vootTW);
				        settingsPageV2.vootTW.click();
				        test.log(LogStatus.INFO, "clicked Voot share Link in Tiwtter App");
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Unable to click VOOT share Link in twitter app");
					}
			       
			        if (Utilities.explicitWaitVisible(driver, settingsPageV2.homeVootapp, 80)) {
						test.log(LogStatus.PASS,"Validate the deeplink functionality from the shared app when Voot Kids app is not installed in device:");
						BasePageV2.smokeresults("", rowno902, "PASS");
					}else {
						test.log(LogStatus.FAIL, "Not able to Navigated to Voot App from Twitter App");
						BasePageV2.smokeresults("", rowno902, "FAIL");
					}
		        
		        	
		
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
