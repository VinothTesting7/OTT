package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

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

import io.appium.java_client.android.AndroidKeyCode;

public class VrifySettingsAccountMobileNum extends BaseTestV2 {
	String testName = "VrifySettingsAccountMobileNum";

	@Test(dataProvider = "getData")
	public void vrifySettingsAccountMobileNum(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Settings ACCOUNT Mobile Number Page ");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings ACCOUNT Mobile Number Page : " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		
		int rowno741 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno741,
				"741: Verify the click functionality of Mobile No. field in Account settings page");

		
		int rowno742 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno742,
				"742: Verify the UI of Mobile No. page ");

	
		int rowno743 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno743,
				"743: Verify the click functionality of Back arrow in Mobile No. Page");

		
		int rowno744 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno744, "744: Verify the click functionality of Edit option in Mobile No. Page");

		
		int rowno745 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno745, "745: Verify the UI of Mobile No. edit page");
		
	
		int rowno751 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno751,
				"751: Verify country code is autofilled as user starts typing mobile no. in the text field");

		
		int VK_757 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_757,"Verify the click functionality of 'Enter Code Manually' link text");
		
		int VK_758 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_758,"Verify the UI of Manual verification code page");
		
		int VK_759 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_759,"Verify the functionality of back arrow in Manual Verification screen:");

		
		/*
		 * Need to add test step in this class below in between test cases 
		 * 2. SignUp/Login using valid email ID/mobile no.
		 * 
		 * And
		 * 
		 * Hard coded pin "1234" in below code using next line code
		 * 
		 * if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer,
		 * 20)) launchpagev2.parentPinContainer.sendKeys("1111"); Set the pin here
		 * Default "1111" Later in this Class
		 * 
		 * Passing in Email Edit  " suresh.k@ifocussystec.com"   /// Hard coded in Email in this step
		 * Passing Mobile Number : "8904584821"                   /// Hard coded number in this step
		 * 
		 * Hard Coded Emai"suresh.kutagula@gmail.com" in below test steps nned to Modify
		 * 
		 */

		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");

		LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
		SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		// navigating to settings 'ACCOUNT' page 
		
		
		 homepagev2.login(data.get("Email"),data.get("Password"));
		
	
		  for(int i = 1; i < 4 ; i++ ) {
			  driver.pressKeyCode(AndroidKeyCode.BACK);
			  if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
				  break;
		  }
		  
		
		

			try {
				if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

				}
			} catch (Exception e) {
				BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
			}
		
			 // Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, settingsPage.parentZoneButton, 10)) {
				settingsPage.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settingsPage.parentPinContainer, 10)) {
					Thread.sleep(1000);
					settingsPage.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		
			
			// putting App in background
			 driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
			
			if (Utilities.explicitWaitVisible(driver, settingsPage.settingsIcon, 10)) {
				settingsPage.settingsIcon.click();
				test.log(LogStatus.INFO, "Succussfully clicked Settings Icon");
			}else SettingsPageV2.reportFail("Not Found Settings Icon to naviagte to Settings page");
			
					
					if(Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZoneAccount, 10)) {;
					settingsPage.settingTextinParantZoneAccount.click();
					}else BasePageV2.reportFail("unable to navigate to Account Page");
					
					if(Utilities.explicitWaitVisible(driver, settingsPage.accountMobile, 10)) {
					settingsPage.accountMobile.click();
				    }else BasePageV2.reportFail("Mobile option is not found in Account Mian page ");
					
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumTile, 10)) {
						test.log(LogStatus.PASS,
								"Verify the click functionality of Mobile No. field in Account settings page");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("741: Verify the click functionality of Mobile No. field in Account settings page",
								rowno741, "PASS");
					}else test.log(LogStatus.FAIL,"Add Mobile Number Title not found in Settings Mobile Main Screen");
					     BasePageV2.takeScreenshot();
					
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumEditText, 10)) {
							if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumSaveBtn, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumCuntryCodeText, 10)) {
									test.log(LogStatus.PASS,
											"Verified the UI of Mobile No. page");
									settingsPage.takeScreenshot();
									homepagev2.smokeresults("741: Verify the UI of Mobile No. page",
											rowno742, "PASS");
								
							}else BasePageV2.reportFail("Mobile Number Country code is not found");
						}else {
							  if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumSaveBtn1, 10)) {
								  test.log(LogStatus.INFO, "Found 'SAVE' Button");
							  }else {
								  BasePageV2.reportFail("SAVE button is missing in Add Mobile screen");
							  }
							
							
						}
					}else BasePageV2.reportFail("Mobile Edit Text box is not found in Add Mobiel main screen");
					
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumEditText, 10)) {
						settingsPage.addMobileNumEditText.click();
						test.log(LogStatus.PASS,
								"Verified the click functionality of Edit option in Mobile No. Page");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("744: Verify the click functionality of Edit option in Mobile No. Page",
								rowno744, "PASS");
					}
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumCuntryCodeText, 10)) {
						
						test.log(LogStatus.PASS,
								"Verified country code is autofilled as user starts typing mobile no. in the text field");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("751: Verify country code is autofilled as user starts typing mobile no. in the text field",
								rowno751, "PASS");
					}
					if(Utilities.explicitWaitVisible(driver, settingsPage.addMobileNumTile, 10)) {
						if(Utilities.explicitWaitClickable(driver, settingsPage.addMobileNumBackBtn, 2)) {
					        settingsPage.addMobileNumBackBtn.click();
						}else test.log(LogStatus.FAIL, "Back Arrow button Functionality Fialed in Update mobile Number Page");
						   
					 if(Utilities.explicitWaitVisible(driver, settingsPage.settingsAccount, 10)) {
						 test.log(LogStatus.PASS,
									"Verified the click functionality of Back arrow in Mobile No. Page.");
							settingsPage.takeScreenshot();
							homepagev2.smokeresults("743: Verify the click functionality of Back arrow in Mobile No. Page",
									rowno743, "PASS");
					 }else test.log(LogStatus.FAIL, "unble to verified Click back arrow functionality ");
					}else test.log(LogStatus.FAIL, "Not able to find the Mobile main page Back Arrow button / not working ");
					
					
//		Verify the click functionality of 'Enter Code Manually' link text
					
					
					 driver.runAppInBackground(Duration.ofSeconds(3));
					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					 driver.currentActivity();
					
					
					 if(Utilities.explicitWaitVisible(driver, settingsPage.accountMobile, 10)) {
						 settingsPage.accountMobile.click();
						 test.log(LogStatus.INFO, "Clicked Mobile option in Settings ACCOUNT page");
					 }else {
						 test.log(LogStatus.FAIL, "Not able to click Mobile Option in Aettings ACCOUNT page / not found");
					 }
					 
					 test.log(LogStatus.INFO,"Mobile Number is : " + Utilities.generateMobileNumber());
					 
					 //sending new valied Mobile Number
					 settingsPage.addMobileNumEditText.clear();
					 settingsPage.addMobileNumEditText.sendKeys(Utilities.generateMobileNumber());
					 String str = settingsPage.addMobileNumEditText.getText().toString().trim();
					 test.log(LogStatus.INFO, "The Mobile Number from Edit Text : " + str);
					try {
						driver.hideKeyboard();
					} catch (Exception e) {
						e.printStackTrace();
					}
					 
					 
					 Thread.sleep(1000);
					 
					 if(Utilities.explicitWaitClickable(driver, settingsPage.addMobileNumSaveBtn, 10)) {
						 settingsPage.addMobileNumSaveBtn.click();
						 test.log(LogStatus.INFO, "Clicked on Save button in 'UPDATE MOBILE NUMBER' page");
					 }else {
						 
						 if(Utilities.explicitWaitClickable(driver, settingsPage.addMobileNumSaveBtn1, 10)) {
							 settingsPage.addMobileNumSaveBtn1.click();
							 test.log(LogStatus.INFO, "Clicked on Save button in 'UPDATE MOBILE NUMBER' page");
						 }else BasePageV2.reportFail("Not able to Clicked Save Button / not found in Upadte Mobile Number page");
					 }
					 
					 if(Utilities.explicitWaitClickable(driver, settingsPage.enterCodeManuallyMobile, 10)) {
						 settingsPage.enterCodeManuallyMobile.click();
						 test.log(LogStatus.INFO, " Clicked the 'ENTER CODE MANUALLY' Link / Not found ");
					 }else BasePageV2.reportFail("Not able to Clicked the 'ENETER CODE MANUALLY' link / Not found");
					
					 if(Utilities.explicitWaitClickable(driver, settingsPage.autodetectCodeBtn, 10)) {
						 test.log(LogStatus.PASS,"Verify the click functionality of 'Enter Code Manually' link text");
						 BasePageV2.smokeresults("", VK_757, "PASS");
					 }else {
						 test.log(LogStatus.FAIL, "Not able to redirected to 'ENTER CODE MANUALLY' Page");
						 BasePageV2.takeScreenshot();
						 BasePageV2.smokeresults("", VK_757, "FAIL");
					 }
					 
					 
//					 Verify the UI of Manual verification code page
					 
					 if(Utilities.explicitWaitVisible(driver, settingsPage.subTextEnterManuallyPage, 10)) {
						 if(Utilities.explicitWaitVisible(driver, settingsPage.enterManuallyPageBackBtn, 10)) {
							 if(Utilities.explicitWaitVisible(driver, settingsPage.pinContainerEnterCodeManually, 10)) {
								 if(Utilities.explicitWaitVisible(driver, settingsPage.autodetectCodeBtn, 10)) {
									 if(Utilities.explicitWaitVisible(driver, settingsPage.notReciveOTPEnterManuallyPage, 10)) {
										 if(Utilities.explicitWaitVisible(driver, settingsPage.ReSendOTPEnterManuallyPage, 10)) {
											 
											 test.log(LogStatus.PASS,"Verify the UI of Manual verification code page");
											 BasePageV2.smokeresults("", VK_758, "PASS");
											 
										 }else {
											 test.log(LogStatus.FAIL, "'Resend OTP Link' not found in 'ENTER CODE MANUALLY' Page ");
											 BasePageV2.takeScreenshot();
										 }
									 }else {
										 test.log(LogStatus.FAIL, "'Not Recevied your OTP' Text not found in 'ENTER CODE MANUALLY' Page ");
										 BasePageV2.takeScreenshot();
									 }
								 }else {
									 test.log(LogStatus.FAIL, "'AUTODETECT CODE' not found in 'ENTER CODE MANUALLY' Page ");
									 BasePageV2.takeScreenshot();
								 }
							 }else {
								 test.log(LogStatus.FAIL, "'Pin Container(4 White Boxes)' not found in 'ENTER CODE MANUALLY' Page ");
								 BasePageV2.takeScreenshot();
							 }
						 }else {
							 test.log(LogStatus.FAIL, "'Back Button' not found in 'ENTER CODE MANUALLY' Page ");
							 BasePageV2.takeScreenshot();
						 }
						 
					 }else {
						 test.log(LogStatus.FAIL, "'Please Enter The OTP Sent to' Text not found in 'ENTER CODE MANUALLY' Page ");
						 BasePageV2.takeScreenshot();
					 }
					 
//				Verify the functionality of back arrow in Manual Verification screen:	 
					 
					 if(Utilities.explicitWaitVisible(driver, settingsPage.enterManuallyPageBackBtn, 10)) {
						 settingsPage.enterManuallyPageBackBtn.click();
						 test.log(LogStatus.INFO, "Clicked Back Button in 'ENTER CODE MANUALLY' Page");
					 }else {
						 test.log(LogStatus.FAIL, "Not able Click Back Button of 'ENTER CODE MANUALLY' Page ");
						 BasePageV2.takeScreenshot();
					 }
					 
					 if(Utilities.explicitWaitClickable(driver, settingsPage.enterCodeManuallyMobile, 10)) {
						 test.log(LogStatus.PASS,"Verify the functionality of back arrow in Manual Verification screen:");
						 BasePageV2.smokeresults("", VK_759, "PASS");
					 }else {
						 BasePageV2.reportFail("Verify the functionality of back arrow in Manual Verification screen:");
						 BasePageV2.smokeresults("", VK_759, "FAIL");
					 }
					 
				
				
			
			
			
		
		
		
	
	}
	
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
	
}


	

