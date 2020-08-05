package com.viacom.settings;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BaseAugmenter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sun.mail.imap.protocol.UID;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import groovy.json.internal.BaseJsonParser;
import io.appium.java_client.android.AndroidKeyCode;

// Author - Suresh

public class SettingsHelpAndSupportFeature_P1 extends BaseTestV2{
	
	String testName = "SettingsHelpAndSupportFeature_P1";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsHelpAndSupportFeature_P1");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_2161 - Verify the list of issues present in 'Category of Issue' drop down:
		// VK_2173 - Verify the redirection when user taps on 'FAQ' option:
		// VK_2164 - Verify functionality of Send button present in 'Contact Us' screen:
		// VK_739 - Verify the functionality of 'Save' button post entering valid data in 'Email' field:
		
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		
		String userName = data.get("Email");
		String password = data.get("Password");
		
		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		List actualList =  new ArrayList();
		List ExpectedList =  new ArrayList();
		
		
		
	
		
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
					Thread.sleep(20000);
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
		
		
		// VK_2173 - Verify the redirection when user taps on 'FAQ' option:
		settingsPageV2.putBackGroundApp3();
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.helpFAQs, 50)) {
			settingsPageV2.helpFAQs.click();
			test.log(LogStatus.INFO, "click on FAQ's option in HELP screen");
			Thread.sleep(5000);
			settingsPageV2.putBackGroundApp3();
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.faqTitle, 50)) {
				driver.pressKeyCode(AndroidKeyCode.BACK);
				test.log(LogStatus.PASS, "Verify the redirection when user taps on 'FAQ' option:");
				if(!Utilities.setResultsKids("VK_2173", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}else {
				test.log(LogStatus.FAIL, "Verify the redirection when user taps on 'FAQ' option:");
				if(!Utilities.setResultsKids("VK_2173", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				driver.pressKeyCode(AndroidKeyCode.BACK);
			}
			
		}else {
			test.log(LogStatus.FAIL, "Not displayed FAQ's option in HELP screen / Not click");
			BasePageV2.takeScreenshot();
		}
		
		
	
		settingsPageV2.putBackGroundApp3();
		if(Utilities.explicitWaitClickable(driver, settingsPageV2.contactUs, 50)) {
			settingsPageV2.contactUs.click();
			test.log(LogStatus.INFO, "clicked on 'Contact Us' option in Help&Support screen");
			
		}else {
			test.log(LogStatus.FAIL, "Not displayed Contact Us option in Help&Support screen / Not click");
			BasePageV2.takeScreenshot();
		}
		
		
		// category of Issue fields
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.categoryofIssue, 40)) {
			test.log(LogStatus.INFO, "Displayed 'category of issue' label in contact us screen");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.selectIssueDropDown , 40)) {
				test.log(LogStatus.INFO, "Displayed select issue drop down in Contact us screen");
				try {
					settingsPageV2.selectIssueDropDown.click();
					test.log(LogStatus.INFO, "Click on select issue drop down");
		
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Not click on select issue drop down in contact us screen");
					BasePageV2.takeScreenshot();
				}
	
				
				
				try {
					
					List<WebElement> list = driver.findElements(By.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1']"));
					for (WebElement ele : list ) {
						String stringTitle = ele.getText().toString().trim();
						actualList.add(stringTitle);
					}
					
					// printing actual list 
					test.log(LogStatus.INFO, "The actual List is : " + actualList);
					
					
					
					// expected List 
					
					ExpectedList.add("Registration");
					ExpectedList.add("Login");
					ExpectedList.add("Video Content");
					ExpectedList.add("Audio Content");
					ExpectedList.add("Ebooks");
					ExpectedList.add("Profile Settings");
					ExpectedList.add("Parent Dashboard");
					ExpectedList.add("Other");
		
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}else {
				test.log(LogStatus.FAIL, "Not displayed select issue drop down in contact us screen");
				BasePageV2.takeScreenshot();
				
			}
		}else {
			test.log(LogStatus.FAIL, "Not displaye 'category of issue' label in contact us screen");
			BasePageV2.takeScreenshot();
			
		}
		
		
	
		if(ExpectedList.equals(actualList)) {
			test.log(LogStatus.PASS, "Verify the list of issues present in 'Category of Issue' drop down:");
			if(!Utilities.setResultsKids("VK_2160", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}else {
			test.log(LogStatus.FAIL, "Verify the list of issues present in 'Category of Issue' drop down:");
			if(!Utilities.setResultsKids("VK_2160", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		
		
		// VK_2164 - Verify functionality of Send button present in 'Contact Us' screen:
		
		if(Utilities.explicitWaitVisible(driver, settingsPageV2.categoryIssue, 50)) {
			settingsPageV2.categoryIssue.click();
			test.log(LogStatus.INFO , "selected category of issue in drop down in Contact us screen");
			//send the Name to Name field
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.editTextName, 50)) {
				settingsPageV2.editTextName.clear();
				settingsPageV2.editTextName.click();
				settingsPageV2.editTextName.sendKeys("Dhoni");
				
				driver.hideKeyboard();
				
				// send text to actual issue filed
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.actaulIssueEditTextbox, 50)) {
					settingsPageV2.actaulIssueEditTextbox.clear();
					settingsPageV2.actaulIssueEditTextbox.click();
					settingsPageV2.actaulIssueEditTextbox.sendKeys("Thank You");
					
					driver.hideKeyboard();
					
					// click on Send Button
					if(Utilities.explicitWaitClickable(driver, settingsPageV2.sendBtncontactUs, 50) ) {
						settingsPageV2.sendBtncontactUs.click();
						test.log(LogStatus.INFO, "click on send button in contact us screen");
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.contactUsSccussMsg, 50)) {
							
							test.log(LogStatus.PASS, "Verify functionality of Send button present in 'Contact Us' screen:");
							if(!Utilities.setResultsKids("VK_2164", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							
							// click on Cross Btn on Pop up
							try {
								settingsPageV2.contactUsSccussPopCloseBtn.click();
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							
						}else {
							test.log(LogStatus.FAIL, "Verify functionality of Send button present in 'Contact Us' screen:");
							if(!Utilities.setResultsKids("VK_2164", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						
						
					}else {
						test.log(LogStatus.FAIL, "Failed to click on Send button in contact us screen");
						BasePageV2.takeScreenshot();
					}
					
					
					
				}else {
					test.log(LogStatus.FAIL, "failed to find the Actual issue edit text field in Contact us screen");
					BasePageV2.takeScreenshot();
				}
				
				
			}else {
				test.log(LogStatus.FAIL, "Not displayed name edit text box in contact us screen");
				BasePageV2.takeScreenshot();
			}
			
			
		}else {
			test.log(LogStatus.FAIL, "Failed to click category issue in drop down in Contact us screen");
			BasePageV2.takeScreenshot();
		}
		
		
		// VK_739 - Verify the functionality of 'Save' button post entering valid data in 'Email' field:
		
		 driver.pressKeyCode(AndroidKeyCode.BACK);
		 Utilities.verticalSwipeDown(driver);
		 
		 // click on settings Account option
		 String EmailId = "";
		 try {
			driver.hideKeyboard();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		 if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneAccount, 50)) {
			 settingsPageV2.settingTextinParantZoneAccount.click();
			 test.log(LogStatus.INFO, "click on Account option in Settings screen");
			 if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountEmail, 50)) {
				 settingsPageV2.accountEmail.click();
				 test.log(LogStatus.INFO, "click on Account Email option");
				 try {
					 EmailId  = settingsPageV2.addMailEditText.getText().toString().trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
				  
				  // send the Email id 
				 if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.addMailEditText, 30)) {
					 settingsPageV2.addMailEditText.clear();
					 settingsPageV2.addMailEditText.click();
					 settingsPageV2.addMailEditText.sendKeys(EmailId+"abc");
					 // click on Save Button**
					 settingsPageV2.putBackGroundApp3();
					 if(Utilities.explicitWaitClickable(driver, settingsPageV2.updateEmailSaveBtn1, 60)) {
						 settingsPageV2.updateEmailSaveBtn1.click();
						 test.log(LogStatus.INFO, "click on Save button in Edit Email screen");
						 if(Utilities.explicitWaitVisible(driver, settingsPageV2.emailAddedSuccessMsg, 60)) {
							 test.log(LogStatus.PASS, "Verify the functionality of 'Save' button post entering valid data in 'Email' field:");
								if(!Utilities.setResultsKids("VK_739", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								
								if(Utilities.explicitWaitClickable(driver, settingsPageV2.pinResetSuccessCancelBtn, 50)) {
									settingsPageV2.pinResetSuccessCancelBtn.click();
								}
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountEmail, 50)) {
									 settingsPageV2.accountEmail.click();
								}
								 if(Utilities.explicitWaitVisibleNew(driver, settingsPageV2.addMailEditText, 30)) {
									settingsPageV2.addMailEditText.clear();
									 settingsPageV2.addMailEditText.click();
									 settingsPageV2.addMailEditText.sendKeys(EmailId);
									 Thread.sleep(5000);
									 try {
										 settingsPageV2.updateEmailSaveBtn1.click();
										 if(Utilities.explicitWaitClickable(driver, settingsPageV2.pinResetSuccessCancelBtn, 50)) {
												settingsPageV2.pinResetSuccessCancelBtn.click();
											}
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
							}else {
								test.log(LogStatus.FAIL, "Verify the functionality of 'Save' button post entering valid data in 'Email' field:");
								if(!Utilities.setResultsKids("VK_739", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								BasePageV2.takeScreenshot();
							} 
						 
						 
						 
					 }else {
						 test.log(LogStatus.FAIL, "Failed to click on Save button in Email edit screen / not found");
						 BasePageV2.takeScreenshot();
					 }

				 }else {
					 test.log(LogStatus.FAIL, "Not displayed add mail edit text / unable to send Email to Edit text box ");
					 BasePageV2.takeScreenshot();
				 }
				 
				 
			 }else {
				 test.log(LogStatus.FAIL, "Failed to click on Account Email / Not displayed");
				 BasePageV2.takeScreenshot();
			 }
		 }else {
			 test.log(LogStatus.FAIL, "Failed to click on Account option in settings screen / Not found");
			 BasePageV2.takeScreenshot();
		 }
		

		
	}
		
		@DataProvider
		public Object[][] getData() {
			return DataUtil.getData(testName, xls);
		}	

}
