package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.remote.BaseAugmenter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sun.mail.imap.protocol.UID;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import groovy.json.internal.BaseJsonParser;
import io.appium.java_client.android.AndroidKeyCode;

public class SettingsHelpAndSupportFeature_P2 extends BaseTestV2{
	
	String testName = "SettingsHelpAndSupportFeature_P2";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsHelpAndSupportFeature_P2");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_2158 - Verify the functionality of Contact Us option preesent in Help screen:
		//VK_2160 - Verify default state of the fields present in Contact Us page:
		// VK_418 -Verify the UI of the book detail screen if book has no narration option:
		
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");

		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BooksPageV2 bookPage = new BooksPageV2(driver, test);
		
		
		String userName = data.get("Email");
		String password = data.get("Password");
		
		// Login to Voot Kids App
		homepagev2.login(userName, password);

		
		
		//  click on Profile icon in Home page
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			homepagev2.profilepic.click();
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 30)) {
				settingsPageV2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 30)) {
					Thread.sleep(1000);
					settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(30000);
					// putting App in background
					 settingsPageV2.putBackGroundApp3();
					// click on Settings Icon in Parent Zone page
					 if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 80)) {
							settingsPageV2.settingsIcon.click();
						 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
					 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
				}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
			
		}else {
			BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");
		}
		
		settingsPageV2.putBackGroundApp3();
		Utilities.verticalSwipe(driver);
		
		// click on Help and Support option in Settings Main screen
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsHelpSupport, 50)) {
			settingsPageV2.settingsHelpSupport.click();
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpAndSupportPageTile, 50)) {
				test.log(LogStatus.INFO, "Successfully Naviagted to Help&Support screen");
			}else BasePageV2.reportFail("Does not navigated to Help&Support screen");
		}else BasePageV2.reportFail("Not displayed Help&Support option in settings screen / Not click");
		
		
		// VK_2158 - Verify the functionality of Contact Us option preesent in Help screen:
		settingsPageV2.putBackGroundApp3();
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.contactUs, 50)) {
			settingsPageV2.contactUs.click();
			test.log(LogStatus.INFO, "clicked on 'Contact Us' option in Help&Support screen");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.contectUsTitle, 40)) {
				test.log(LogStatus.PASS, "Verify the functionality of Contact Us option preesent in Help screen:");
				if(!Utilities.setResultsKids("VK_2158", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}else {
				test.log(LogStatus.FAIL, "Verify the functionality of Contact Us option preesent in Help screen:");
				if(!Utilities.setResultsKids("VK_2158", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			
		}else {
			test.log(LogStatus.FAIL, "Not displayed Contact Us option in Help&Support screen / Not click");
			BasePageV2.takeScreenshot();
		}
		
		
		//VK_2160 - Verify default state of the fields present in Contact Us page:
		
		int contct = 0;
		// Name Edit Text box in Contact Us screen
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.nameContactUs, 40)) {
			if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.editTextName, 40)) {
				String editText = settingsPageV2.editTextName.getText();
				System.out.println("The edit text field text is : " + editText );
				test.log(LogStatus.INFO, "The edit text field text is : " + editText);
				if(editText.isEmpty()) {
					test.log(LogStatus.INFO, "Name Edit text field is empty as expected");
				}else {
					test.log(LogStatus.FAIL, "Name Edit text field is not empty");
					BasePageV2.takeScreenshot();
					contct++;
				}
			}
			
		}else {
			test.log(LogStatus.FAIL, "Not displayed label 'Name' in Contect Us screen");
			BasePageV2.takeScreenshot();
		}
		
		
		// Edit Address field
		
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.emailAddress, 40)) {
			test.log(LogStatus.INFO, "Displayed Email Address label on the Edit text field");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.editAddressbox, 40)) {
				test.log(LogStatus.INFO, "Displayed Email Address Edit text box");
			}else {
				test.log(LogStatus.FAIL, "Not displayed Email Address Lable on Email Address Edit text box");
				BasePageV2.takeScreenshot();
				contct++;
			}
			
		}else {
			test.log(LogStatus.FAIL, "Not displayed Email Address Edit text box in contact us screen");
			BasePageV2.takeScreenshot();
			contct++;
		}
		
		// category of Issue fields
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.categoryofIssue, 40)) {
			test.log(LogStatus.INFO, "Displayed 'category of issue' label in contact us screen");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.selectIssueDropDown , 40)) {
				test.log(LogStatus.INFO, "Displayed select issue drop down in Contact us screen");
			}else {
				test.log(LogStatus.FAIL, "Not displayed select issue drop down in contact us screen");
				BasePageV2.takeScreenshot();
				contct++;
			}
		}else {
			test.log(LogStatus.FAIL, "Not displaye 'category of issue' label in contact us screen");
			BasePageV2.takeScreenshot();
			contct++;
		}
		
		// Actual issue
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.actualissueText, 40)) {
			test.log(LogStatus.INFO, "Displayed to Actual issue label in contact us screen");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.actaulIssueEditTextbox, 40)) {
				test.log(LogStatus.INFO, "Displayed actual issue Edit text box in cotact us screen");
			}else {
				test.log(LogStatus.FAIL, "Not displayed actual issue Edit text box in contact us screen");
				BasePageV2.takeScreenshot();
				contct++;
			}
		}else {
			test.log(LogStatus.FAIL, "Not displayed actual issue Label in contact us screen");
			BasePageV2.takeScreenshot();
			contct++;
		}
		
		
		if(contct == 0) {
			test.log(LogStatus.PASS, "Verify default state of the fields present in Contact Us page:");
			if(!Utilities.setResultsKids("VK_2160", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}else {
			test.log(LogStatus.FAIL, "Verify default state of the fields present in Contact Us page:");
			if(!Utilities.setResultsKids("VK_2160", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		
		
		// VK_418 -Verify the UI of the book detail screen if book has no narration option:
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		
		// click on Read Tab
		int bookDetials = 0;
		homepagev2.tabClick("Read");
		if(Utilities.explicitWaitVisible(driver, bookPage.bookCarousal, 50)) {
			bookPage.bookCarousal.click();
			Thread.sleep(5000);
			test.log(LogStatus.INFO, "click on carousal in Read screen");
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
		}else {
			test.log(LogStatus.FAIL, "Failed to click on carousal in Read page");
			BasePageV2.takeScreenshot();
		}

		String levelStr = "";
		try {
			if(Utilities.explicitWaitVisible(driver, bookPage.bookDetailPageBookLevelCount, 50)) {
				 levelStr = bookPage.bookDetailPageBookLevelCount.getText().toString().trim();
				test.log(LogStatus.INFO, "The Level of the book is :  "+  levelStr);
				
			}else {
				test.log(LogStatus.FAIL, "Not displayed Level of the book in book detail page ");
				BasePageV2.takeScreenshot();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String readTimeStr = "";
		try {
			if(Utilities.explicitWaitVisible(driver, bookPage.bookDetailPageReadTime1, 50)) {
				 readTimeStr = bookPage.bookDetailPageReadTime1.getText().toString().trim();
				test.log(LogStatus.INFO, "The Read time of the book is :  "+  readTimeStr);
				
			}else {
				test.log(LogStatus.FAIL, "Not displayed Read Level of the book in book detail page ");
				BasePageV2.takeScreenshot();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(!levelStr.equals(null) && !readTimeStr.equals(null)) {
			test.log(LogStatus.PASS, "Verify the UI of the book detail screen if book has no narration option:");
			if(!Utilities.setResultsKids("VK_418", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}else {
			test.log(LogStatus.FAIL, "Verify the UI of the book detail screen if book has no narration option:");
			if(!Utilities.setResultsKids("VK_418", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		
		

		
		
		
		
		
		
	}
		
		@DataProvider
		public Object[][] getData() {
			return DataUtil.getData(testName, xls);
		}	

}
