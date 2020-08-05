package com.viacom.mixpanel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.MPPageVK;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;


//Author - Suresh
public class VootKidsEvents_P1 extends BaseTestV2 {
	
String testName = "VootKidsEvents_P1";
 
    
	final String EventName = "Session";
	String duration = "",total_sessions = "", genEmail = "";
	 
	
	public static Map<String, String> valuesMap = new HashMap<String, String>();
	
	String event1 = "App Install";
	String event2 = "Session";
	String event3 = "Registration Attempt";
	String event4 = "Registration Field Submitted";
	String event5 = "Registration OTP Failure";
	String event6 = "Login Successful";
	String event7 = "Account Settings Changed";

	
	@Test(dataProvider = "getData")
	public void settingsDeviceCellularFunctionality(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VootKidsEvents_P1");
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

        Utilities.openCharles();
		
      VootConstants.ENVIRONMENT= "PreProduction";        
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		SignupandLoginPageV2 singnupPage = new SignupandLoginPageV2(driver, test);
		SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
		HomePageV2 homePage = new HomePageV2(driver, test);
		SearchPageV2 searchPage = new SearchPageV2(driver, test);
		
		String signMobileNum = data.get("signMobileNum") ;
		String passwordStr = data.get("passwordStr");
		String otpstr = data.get("otpstr");
		String loginMobileEdit = data.get("Email");
		String loginPassword = data.get("Password");
		String nonRigsMobileNum = data.get("nonRigsMobileNum");
		

		
		
		test.log(LogStatus.INFO, "Done");
		
		
		
		
		for (int i = 0 ; i < 2 ; i++) {
			// Clicked the 30 Day Free Trial Offer  Event
			
						 if(Utilities.explicitWaitVisible(driver,singnupPage.joinFreefor30Days, 10))
						 {					 
							      test.log(LogStatus.INFO, " Join free for 30 days Button is Displayed in Welcome Screen");				 					
							    	singnupPage.joinFreefor30Days.click();
							          test.log(LogStatus.INFO, "clicked on Join free for 30 days  button in Welcome Screen");
						           
						 }
						 else
						 {
							        test.log(LogStatus.FAIL, "Join free for 30 days Button is not Displayed in Welcome Screen"); 	
							       
						 }	
			 
						 Thread.sleep(3000);
			  driver.pressKeyCode(AndroidKeyCode.BACK);
			  Thread.sleep(3000);
			
			}
			
			
			 driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
		
			 Thread.sleep(3000);
			 
			 driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
			 
			 Thread.sleep(3000);
			 
			
			 // click on Join free For 30 days Button
				if(Utilities.explicitWaitVisible(driver,singnupPage.joinFreefor30Days, 10))
				 {					 
					      test.log(LogStatus.INFO, " Join free for 30 days Button is Displayed in Welcome Screen");				 					
					    	singnupPage.joinFreefor30Days.click();
					          test.log(LogStatus.INFO, "clicked on Join free for 30 days  button in Welcome Screen");
				           
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, "Join free for 30 days Button is not Displayed in Welcome Screen"); 	
					       
				 }	
				
				 // send mobile Number to Mobile Field 
				
				 if(Utilities.explicitWaitVisible(driver,singnupPage.mobileNoTextField, 10))
				 {				
						      test.log(LogStatus.INFO, "  Mobile No field'  Displayed in signUPPage");				 				   								 											 
															 
									 singnupPage.mobileNoTextField.click();
									 singnupPage.mobileNoTextField.clear();
									 singnupPage.mobileNoTextField.sendKeys(signMobileNum);								 
									 test.log(LogStatus.INFO, " 'Sucessesfully entered mobile number to mobile number field ");				 				   							
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, " 'Mobile No field '  not  Displayed in signUP Page"); 	
					        BasePageV2.takeScreenshot();				       
				 }
				
				 //Verify OTP Text with * and OTP field in SignupPage			 			 
				 if( Utilities.explicitWaitVisible(driver,singnupPage.otptextField, 10))
				 {					 
					      test.log(LogStatus.INFO, "  ' OTP Text with * and OTP field in SignupPage'  Displayed in signUPPage");
					         singnupPage.otptextField.click();
					         singnupPage.otptextField.clear();
					         singnupPage.otptextField.sendKeys(otpstr);	
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, " ' OTP Text with * and OTP field in SignupPage'  not  Displayed in signUPPage"); 	
					        BasePageV2.takeScreenshot();
					        
				 }	
				 
				 
				 driver.hideKeyboard();
				 
				 //Verify Password Text with * and Password field in SignupPage			 			 
				 if(Utilities.explicitWaitVisible(driver,singnupPage.passwordtextField, 10))
				 {					 
					      test.log(LogStatus.INFO, "  ' Password Text with * and Password field in SignupPage'  Displayed in signUPPage");	
					      singnupPage.passwordtextField.click();
					      singnupPage.passwordtextField.clear();
					      singnupPage.passwordtextField.sendKeys(passwordStr);	
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, " ' Password Text with and Password field in SignupPage'  not  Displayed in signUPPage"); 	
					        BasePageV2.takeScreenshot();
					        
				 }	
				 
				 // click on Next button
				 driver.hideKeyboard();
				 
				 if(Utilities.explicitWaitClickable(driver, singnupPage.nextbuttonwithfillDatainsignUp, 10)) {
					 singnupPage.nextbuttonwithfillDatainsignUp.click();
					 test.log(LogStatus.INFO, "clicked on Next button in singup page");
					 Thread.sleep(30000);
				 }else {
					 test.log(LogStatus.FAIL, "Not Found Next Button / Not Click");
				 }
				
			
				 driver.pressKeyCode(AndroidKeyCode.BACK);
				 
				 // click on Join free For 30 days Button
					if(Utilities.explicitWaitVisible(driver,singnupPage.joinFreefor30Days, 10))
					 {					 
						      test.log(LogStatus.INFO, " Join free for 30 days Button is Displayed in Welcome Screen");				 					
						    	singnupPage.joinFreefor30Days.click();
						          test.log(LogStatus.INFO, "clicked on Join free for 30 days  button in Welcome Screen");
					           
					 }
					 else
					 {
						        test.log(LogStatus.FAIL, "Join free for 30 days Button is not Displayed in Welcome Screen"); 	
						       
					 }	
				 
		    // Enter Wrong OTP 
				 if(Utilities.explicitWaitVisible(driver,singnupPage.mobileNoTextField, 10))
				 {				
						      test.log(LogStatus.INFO, "  Mobile No field'  Displayed in signUPPage");				 				   								 											 
															 
									 singnupPage.mobileNoTextField.click();
									 singnupPage.mobileNoTextField.clear();
									 singnupPage.mobileNoTextField.sendKeys(nonRigsMobileNum);								 
									 test.log(LogStatus.INFO, " 'Sucessesfully entered mobile number to mobile number field ");				 				   							
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, " 'Mobile No field '  not  Displayed in signUP Page"); 	
					        BasePageV2.takeScreenshot();				       
				 }
				
				 // click on SendOTP Link 
				 
			
				 if(Utilities.explicitWaitClickable(driver, singnupPage.sendOtpLink, 30)) {
					 singnupPage.sendOtpLink.click();
					 Thread.sleep(10000);
					 test.log(LogStatus.INFO, "click on Send OTP link");
				 }else {
					 test.log(LogStatus.FAIL, "Failed to click on Send OTP Link in sing up screen / Not found");
					 BasePageV2.takeScreenshot();
				 }
				 
				 //Verify OTP Text with * and OTP field in SignupPage			 			 
				 if( Utilities.explicitWaitVisible(driver,singnupPage.otptextField, 10))
				 {					 
					      test.log(LogStatus.INFO, "  ' OTP Text with * and OTP field in SignupPage'  Displayed in signUPPage");
					         singnupPage.otptextField.click();
					         singnupPage.otptextField.clear();
					         singnupPage.otptextField.sendKeys(otpstr);	
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, " ' OTP Text with * and OTP field in SignupPage'  not  Displayed in signUPPage"); 	
					        BasePageV2.takeScreenshot();
					        
				 }	
				 
				 
				 driver.hideKeyboard();
				 
				 //Verify Password Text with * and Password field in SignupPage			 			 
				 if(Utilities.explicitWaitVisible(driver,singnupPage.passwordtextField, 10))
				 {					 
					      test.log(LogStatus.INFO, "  ' Password Text with * and Password field in SignupPage'  Displayed in signUPPage");	
					      singnupPage.passwordtextField.click();
					      singnupPage.passwordtextField.clear();
					      singnupPage.passwordtextField.sendKeys(passwordStr);	
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, " ' Password Text with and Password field in SignupPage'  not  Displayed in signUPPage"); 	
					        BasePageV2.takeScreenshot();
					        
				 }	
				 
				 // click on Next button
				 driver.hideKeyboard();
				 
				 if(Utilities.explicitWaitClickable(driver, singnupPage.nextbuttonwithfillDatainsignUp, 10)) {
					 singnupPage.nextbuttonwithfillDatainsignUp.click();
					 test.log(LogStatus.INFO, "clicked on Next button in singup page");
					 Thread.sleep(30000);
				 }else {
					 test.log(LogStatus.FAIL, "Not Found Next Button / Not Click");
				 }
				
				 
				 driver.pressKeyCode(AndroidKeyCode.BACK);
				 
				 
				 // click on Login link
				 
				 if(Utilities.explicitWaitClickable(driver, singnupPage.alreadyhaveanAccountandLogininWelcomScreen, 30)) {
					 singnupPage.alreadyhaveanAccountandLogininWelcomScreen.click();
					 test.log(LogStatus.INFO, "Click on 'Login' Link in Welcome screen");
				 }else {
					 test.log(LogStatus.FAIL, "Failed to click on Login Link in Welcome screen");
					 BasePageV2.takeScreenshot();
				 }
				 
				 // send to Mobile Number to Mobile Edit Text Filed
				 
				 if(Utilities.explicitWaitVisible(driver, singnupPage.mobilefieldinLoginPage, 40)) {
					 singnupPage.mobilefieldinLoginPage.click();
					 singnupPage.mobilefieldinLoginPage.clear();
					 singnupPage.mobilefieldinLoginPage.sendKeys(loginMobileEdit);
				 }else {
					 test.log(LogStatus.FAIL, "Failed to sent Mobile Number to Edit text filed in login screen");
					 BasePageV2.takeScreenshot();
				 }
				 
				// send password to password Filed 
				 
				 if(Utilities.explicitWaitVisible(driver,singnupPage.passwordtextField, 10))
				 {					 
					      singnupPage.passwordtextField.click();
					      singnupPage.passwordtextField.clear();
					      singnupPage.passwordtextField.sendKeys(loginPassword);	
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, " ' Password Text with and Password field in SignupPage'  not  Displayed in signUPPage"); 	
					        BasePageV2.takeScreenshot();
					        
				 }	
				 
			// click on Login Button
				 
				 if(Utilities.explicitWaitClickable(driver, singnupPage.loginbtn, 30)) {
					 singnupPage.loginbtn.click();
					 test.log(LogStatus.INFO, "click on Login Button");
					 Thread.sleep(10000);
					 settingsPage.putBackGroundApp();
					 Thread.sleep(10000);
				 }else {
					 test.log(LogStatus.FAIL, "failed to click on Login button in Login page / Not found");
					 BasePageV2.takeScreenshot();
				 }
				 
				 
			// select the Profile 
					
					if(Utilities.explicitWaitClickable(driver, singnupPage.proicon, 30)) {
						singnupPage.proicon.click();
						test.log(LogStatus.INFO, "click on profile icon in Select Profile page / selected the profile ");
					}else {
						test.log(LogStatus.FAIL, "Does not click on profile icon in select profile page/ Does not selected the profile");
						BasePageV2.takeScreenshot();
					}
					 Thread.sleep(10000);
					
					// click on Allow button on pop up
					if(Utilities.explicitWaitClickable(driver, homePage.homeAllowButton, 60)) {
						try {
							homePage.homeAllowButton.click();
						}
						catch(Exception e) {
							test.log(LogStatus.INFO, "Failed to click on Allow button after login");
						}
					}
					else {
						test.log(LogStatus.INFO, "Allow button is not clickable");
						BasePageV2.takeScreenshot();
					}
					
					// cancel Coach cards
					for(int coachCardCount=1;coachCardCount<=4;coachCardCount++) {
						
						Utilities.tap(driver);
						Thread.sleep(2000);
					}
					
				
				 // click on profile Icon in Home Page
					 // click on Profile icon
					  if(Utilities.explicitWaitClickable(driver, homePage.profileBtn, 60)) {
						  homePage.profileBtn.click();
						  test.log(LogStatus.INFO, "click on profile icon in Home page");
						  if(Utilities.explicitWaitClickable(driver, settingsPage.parentZoneButton, 30)) {
//							  settingsPage.parentZoneButton.click();
							  driver.findElement(By.id("com.viacom18.vootkids:id/btn_parent_zone")).click();
							  test.log(LogStatus.INFO, "click on Parent Zone button in 'Switch Profile' Screen");
							  try {
								  settingsPage.parentPinContainer.sendKeys("1111");
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Not Displayed Pin container in Parent Zone page / Not Found");
							}
							  Thread.sleep(10000);
							  settingsPage.putBackGroundApp();
							  
							  // Click on Settings Icon 
							  if(Utilities.explicitWaitClickable(driver, settingsPage.settingsIcon, 60)) {
								  settingsPage.settingsIcon.click();
								  test.log(LogStatus.INFO, "click on Settings Icon");
				 
							  }else {
								  BasePageV2.reportFail("Failed to click on Settings icon in Parent Zone page / Not Displayed");
							  }
						  }else {
							  BasePageV2.reportFail("Failed to click on Parent zone button in Switch Profile");
						  }
					  }else {
						  BasePageV2.reportFail("Failed to click on Profile icon in home page / Not Displayed ");
					  }
					  
					  // Update the Email id in Add Emial Page
					  
					  if(Utilities.explicitWaitClickable(driver, settingsPage.settingTextinParantZoneAccount, 30)) {
							settingsPage.settingTextinParantZoneAccount.click();
							test.log(LogStatus.INFO, "click on Account settings in Settings page");
							
							if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 30)) {
								 genEmail = Utilities.generateEmailid();
								 test.log(LogStatus.INFO, "The New Generated Email id is : " + genEmail);
								settingsPage.accountEmail.click();
								test.log(LogStatus.INFO, "click om Email Link in Account Settings screen");
								if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 30)) {
									settingsPage.addMailEditText.click();
									settingsPage.addMailEditText.clear();
									Thread.sleep(200);
									settingsPage.addMailEditText.sendKeys(genEmail);
									Thread.sleep(5000);
									driver.hideKeyboard();
									// click Save button
									if(Utilities.explicitWaitClickable(driver, settingsPage.updateEmailSaveBtn1, 30)) {
										settingsPage.updateEmailSaveBtn1.click();
										test.log(LogStatus.INFO, "click on Save button in Add Email screen");
										
										if(Utilities.explicitWaitClickable(driver, settingsPage.addMailSucssEmailAdd, 30)) {
											settingsPage.addMailSucssEmailAddCnlBtn.click();
											test.log(LogStatus.INFO, "click on cancel button on Email update successful pop up");
											Thread.sleep(5000);
											driver.pressKeyCode(AndroidKeyCode.BACK);
										}else {
											test.log(LogStatus.FAIL, "Failed to click on Cancel button on Email Update pop up ");
											BasePageV2.takeScreenshot();
										}
										
									}else {
										test.log(LogStatus.FAIL, "Failed to click on Save button in Add Email screen");
										BasePageV2.takeScreenshot();
									}
									
								}else {
									test.log(LogStatus.FAIL, "Failed to send the update Email to Edit text feild in Add Email screen");
									BasePageV2.takeScreenshot();
								}
								

							}else {
								test.log(LogStatus.FAIL, "Failed to click on Email Link in Account Settings screen");
								BasePageV2.takeScreenshot();
							}
							
							
					  }else {
						  test.log(LogStatus.FAIL, "Failed to click on Account link in Settings screen");
						  BasePageV2.takeScreenshot();
					  }
					  
					  
				  settingsPage.putBackGroundApp();	  
				 
				 // click on Device Link in Settings Page
					  
					  if(Utilities.explicitWaitClickable(driver, settingsPage.settingTextinParantZoneDevice, 30)) {
						  settingsPage.settingTextinParantZoneDevice.click();
						  test.log(LogStatus.INFO, "click on Device Link in Settings page");
						  
						  // check Cellular playback set ON/OFF
						  if(settingsPage.deviceCellplySwitch.getAttribute("checked").equals("true")) {
							  test.log(LogStatus.INFO, "Cellular Playback switch is in 'OFF' state");
							  try {
								  settingsPage.deviceCellplySwitch.click();
							  	test.log(LogStatus.INFO, "Clicked Cellular Playback switch for 'OFF' state in Device Screen");
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click Cellular playback switch for 'OFF' state in Device Screen");
							  }

						  }else if (settingsPage.deviceCellplySwitch.getAttribute("checked").equals("false")){
							  test.log(LogStatus.INFO, "Cellular Playback switch is in 'ON' state");
							  try {
								  settingsPage.deviceCellplySwitch.click();
							  	test.log(LogStatus.INFO, "Clicked Cellular Playback switch for 'ON' state in Device Screen");
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click Cellular playback switch for 'ON' state in Device Screen");
							  }

							  }

						  
						  // Select Default Stream Quality 
						  
						  if(Utilities.explicitWaitClickable(driver, settingsPage.defaultsremqualityText, 40)) {
							  settingsPage.defaultsremqualityText.click();
							  test.log(LogStatus.INFO, "click on 'Default Stream quality' drop down in Device setting screen");
							  try {
								  if(Utilities.explicitWaitVisible(driver, settingsPage.devicedefaultsremqualityLow, 30)) {
								  settingsPage.devicedefaultsremqualityLow.click();
								  test.log(LogStatus.INFO, "Selected Low default stream quality from drop down in Device Settings screen");
								  }else {
									  test.log(LogStatus.FAIL, "Failed to select the LOW 'Default Stream Quality' from drop down ");
									  BasePageV2.takeScreenshot();
								  }
							} catch (Exception e) {
								e.printStackTrace();
							}
							  
						  }else {
							  test.log(LogStatus.FAIL, "Failed to click on 'Default Stream quality' drop down in Device setting screen");
							  BasePageV2.takeScreenshot();
						  }
						      
						  // select Preferred Language from drop down
						  
						  // Select Default Stream Quality 
						  if(Utilities.explicitWaitClickable(driver, settingsPage.devicePrefferdLanuageSpinner, 40)) {
							  settingsPage.devicePrefferdLanuageSpinner.click();
							  test.log(LogStatus.INFO, "click on 'Preferred Language' drop down in Device setting screen");
							  try {
								  if(Utilities.explicitWaitVisible(driver, settingsPage.devicePrefferdLanuageTamil, 30)) {
								  settingsPage.devicePrefferdLanuageTamil.click();
								  test.log(LogStatus.INFO, "selcted Tamil as the Preferred Language in Device Settings Screen");
								  Thread.sleep(5000);
								  }else {
									  test.log(LogStatus.FAIL, "Failed to select the Tamil 'Preferred Language' from drop down in device settings page ");
									  BasePageV2.takeScreenshot();
								  }
							} catch (Exception e) {
								e.printStackTrace();
							}
							  
						  }else {
							  test.log(LogStatus.FAIL, "Failed to click on 'Preferred Language' drop down in Device setting screen");
							  BasePageV2.takeScreenshot();
						  }
						      
						 // select the Enable Download Switch to turn ON/OFF
						  
						  if(settingsPage.deviceEnableddownloadsSwitch.getAttribute("checked").equals("true")) {
							  test.log(LogStatus.INFO, "'Enable Downloads' switch is in 'ON' state");
							  try {
								  settingsPage.deviceEnableddownloadsSwitch.click();
							  	test.log(LogStatus.INFO, "Clicked 'Enable Downloads' switch for 'OFF' state in Device Screen");
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click 'Enable Downloads' switch for 'OFF' state in Device Screen");
							  }

						  }else if (settingsPage.deviceEnableddownloadsSwitch.getAttribute("checked").equals("false")){
							  test.log(LogStatus.INFO, "'Enable Downloads' switch is in 'OFF' state");
							  try {
								  settingsPage.deviceEnableddownloadsSwitch.click();
							  	test.log(LogStatus.INFO, "Clicked 'Enable Downloads' switch for 'ON' state in Device Screen");
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click 'Enable Downloads' switch for 'ON' state in Device Screen");
							  }

							  }
						  
						// Select Cellular Downloads Switch button to ON/OFF
						  
						  if(settingsPage.deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("true")) {
							  test.log(LogStatus.INFO, "'Cellular Downloads' switch is in 'ON' state");
							  try {
								  settingsPage.deviceCelluallerDownlodsSwitch.click();
								  Thread.sleep(5000);
							  	test.log(LogStatus.INFO, "Clicked 'Cellular Downloads' switch for 'OFF' state in Device Screen");
							  	// Scroll till end
							  	Utilities.verticalSwipe(driver);
							  	Utilities.verticalSwipe(driver);
							  	Utilities.verticalSwipe(driver);
							  	Utilities.verticalSwipe(driver);
							  	
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click 'Cellular Downloads' switch for 'OFF' state in Device Screen");
							  }

						  }else if (settingsPage.deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("false")){
							  test.log(LogStatus.INFO, "'Cellular Downloads' switch is in 'OFF' state");
							  try {
								  settingsPage.deviceCelluallerDownlodsSwitch.click();
								  Thread.sleep(5000);
							  	test.log(LogStatus.INFO, "Clicked 'Cellular Downloads' switch for 'ON' state in Device Screen");
							  	// scroll till End
							  	Utilities.verticalSwipe(driver);
							  	Utilities.verticalSwipe(driver);
							  	Utilities.verticalSwipe(driver);
							  	Utilities.verticalSwipe(driver);
							  	
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click 'Cellular Downloads' switch for 'ON' state in Device Screen");
							  }

							  }
						  
						  
						  // Select the Download Quality from Drop down box
						  
						  if(Utilities.explicitWaitClickable(driver, settingsPage.deviceDownloadsQualitySpinner, 40)) {
							  settingsPage.deviceDownloadsQualitySpinner.click();
							  test.log(LogStatus.INFO, "click on 'Download quality' drop down in Device setting screen");
							  try {
								  if(Utilities.explicitWaitVisible(driver, settingsPage.devicedefaultsremqualityLow, 30)) {
								  settingsPage.devicedefaultsremqualityLow.click();
								  test.log(LogStatus.INFO, "Selected Low Download quality from drop down in Device Settings screen");
								  }else {
									  test.log(LogStatus.FAIL, "Failed to select the LOW 'Download quality' from drop down ");
									  BasePageV2.takeScreenshot();
								  }
							} catch (Exception e) {
								e.printStackTrace();
							}
							  
						  }else {
							  test.log(LogStatus.FAIL, "Failed to click on 'Download quality' drop down in Device setting screen");
							  BasePageV2.takeScreenshot();
						  }
						  
						  
						  // Select the Audio Download quality from drop down
						  
						  if(Utilities.explicitWaitClickable(driver, settingsPage.deviceAudioDownloadQualitySpinner, 40)) {
							  settingsPage.deviceAudioDownloadQualitySpinner.click();
							  test.log(LogStatus.INFO, "click on 'Audio Download quality' drop down in Device setting screen");
							  try {
								  if(Utilities.explicitWaitVisible(driver, settingsPage.devicedefaultsremqualityLow, 30)) {
								  settingsPage.devicedefaultsremqualityLow.click();
								  test.log(LogStatus.INFO, "Selected Low Audio Download quality from drop down in Device Settings screen");
								  }else {
									  test.log(LogStatus.FAIL, "Failed to select the LOW 'Audio Download quality' from drop down ");
									  BasePageV2.takeScreenshot();
								  }
							} catch (Exception e) {
								e.printStackTrace();
							}
							  
						  }else {
							  test.log(LogStatus.FAIL, "Failed to click on 'Audio Download quality' drop down in Device setting screen");
							  BasePageV2.takeScreenshot();
						  }
						  
						  
						  // select Notifications Switch ON/OFF
						  
						  if(settingsPage.deviceNotificationsSwitch.getAttribute("checked").equals("true")) {
							  test.log(LogStatus.INFO, "'Notifications' switch is in 'ON' state");
							  try {
								  settingsPage.deviceNotificationsSwitch.click();
							  	test.log(LogStatus.INFO, "Clicked 'Notifications' switch for 'OFF' state in Device Screen");
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click 'Notifications' switch for 'OFF' state in Device Screen");
							  }

						  }else if (settingsPage.deviceNotificationsSwitch.getAttribute("checked").equals("false")){
							  test.log(LogStatus.INFO, "'Notifications' switch is in 'OFF' state");
							  try {
								  settingsPage.deviceNotificationsSwitch.click();
							  	test.log(LogStatus.INFO, "Clicked 'Notifications' switch for 'ON' state in Device Screen");
							  } catch (Exception e) {
							  	test.log(LogStatus.FAIL, "Not able to click 'Notifications' switch for 'ON' state in Device Screen");
							  }

							  }
						  
						  Thread.sleep(30000);
					      driver.pressKeyCode(AndroidKeyCode.BACK);
					      driver.pressKeyCode(AndroidKeyCode.BACK);
					      driver.pressKeyCode(AndroidKeyCode.BACK);
						  
					  }else {
						  test.log(LogStatus.FAIL, "Failed to click on Device Link in Settings Page");
						  BasePageV2.takeScreenshot();
					  }
				 
				 // Switched Profile Event 
					 
					  if(Utilities.explicitWaitClickable(driver, singnupPage.proicon1, 30)) {
							singnupPage.proicon1.click();
							test.log(LogStatus.INFO, "click on profile icon in Select Profile page / selected the profile ");
						}else {
							test.log(LogStatus.FAIL, "Does not click on profile icon in select profile page/ Does not selected the profile");
							BasePageV2.takeScreenshot();
						}
						if(Utilities.explicitWaitClickable(driver, singnupPage.proicon2, 60)) {
							singnupPage.proicon2.click();
							Thread.sleep(30000);
							test.log(LogStatus.INFO, "click on profile icon in Select Profile page / selected the profile ");
						}else {
							test.log(LogStatus.FAIL, "Does not click on profile icon in select profile page/ Does not selected the profile");
							BasePageV2.takeScreenshot();
						}
						 
					  
					  
					  
					  
					  
				 
		
		
		Utilities.saveandcloseCharles("vootKidsEvents");
		
		
		// Session Event
		
		MPPageVK.mixpnlEventsfetchLoggedInuser("Session");
		
		for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
			String key = entry.getKey();
		
			
			if (key.equals("Session Duration (seconds)")) {
				String value = MPPageVK.mixevnts.get(key);
				int sessionDuration = Integer.parseInt(value);
				test.log(LogStatus.INFO, "The session Duration is : " +  sessionDuration);
				
				if( sessionDuration > 0) {
					test.log(LogStatus.PASS, "Session Duration Found : " + sessionDuration);
				}else {
					test.log(LogStatus.FAIL, "Session Duration not Found : " + sessionDuration);
				}


			} else if (key.equals("Total Sessions")) {
				String value = MPPageVK.mixevnts.get(key);
				try {
					String [] val = value.split("\\}");
					value = val[0].toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				int totalSession = Integer.parseInt(value);
				test.log(LogStatus.INFO, "The total sessions are : " +  totalSession);
				
				if(totalSession > 0) {
					test.log(LogStatus.PASS, "The total Sessions are Found :  " + totalSession);
				}else {
					test.log(LogStatus.FAIL, "The total sessions are not found : " + totalSession);
				}
			

			}  

		}
		
		
		// App Install  Event 
		
		MPPageVK.mixpnlEventsfetchLoggedInuser("App Install");

		int appCount = 0;
		for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
			String key = entry.getKey();
			
			
			if (key.equals("media_source")) {
				String value = MPPageVK.mixevnts.get(key);
			
				if(value.equals("Organic")) {
					test.log(LogStatus.PASS, "Verifyed the Event 'App Install' property  media_source values :'"+value+"'");
					
				}else {
					test.log(LogStatus.FAIL, "NOt Verifyed the Event 'App Install' property  media_source values :'"+value+"'");
					
				}


			} else if (key.equals("campaign")) {
				
				String valueCampaign = MPPageVK.mixevnts.get(key);
				
				if(valueCampaign.equals("Organic")) {
					test.log(LogStatus.PASS, "Verifyed the Event 'App Install' property  Campaign values :'"+valueCampaign+"'");
				}else {
					test.log(LogStatus.FAIL, "Not Verifyed the Event 'App Install' property  Campaign values :'"+valueCampaign+"'");
					
				}
				
			}else {
				appCount++;
				 if(appCount==MPPageVK.mixevnts.size()-1)
				 {
					 test.log(LogStatus.FAIL, "Not Found media_source and Campaign properties from 'App Install' Event");
					 appCount=0;
				 }
				
				
			}
			

		}
		
		
		
		// Registration Field Submitted Even

		 MPPageVK.mixpnlEventsfetchLoggedInuser("Registration Field Submitted");
			
		
		 
			for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
				String key = entry.getKey();
				
				 if (key.equals("Field submitted")) {
					
					String valueFiled = MPPageVK.mixevnts.get(key);
					try {
						String [] val = valueFiled.split("\\}");
						valueFiled = val[0].toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					if(valueFiled.contains("Mobile Number") || valueFiled.contains("Password") ) {
						test.log(LogStatus.PASS, "Found 'Field submitted' value from Registration Field Submitted Event : " + valueFiled);
					}else {
						test.log(LogStatus.FAIL, "Not Found 'Field submitted' from Registration Field Submitted Event : " + valueFiled);
						
					}
					
				}

			}
		 
		 
		// Registration OTP Failure Event 
			
			 MPPageVK.mixpnlEventsfetchLoggedInuser("Registration OTP Failure");
				
				int risOTP = 0;	
				 
					for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
						String key = entry.getKey();
						
						 if (key.equals("OTP failure reason")) {
							
							String valueOTP = MPPageVK.mixevnts.get(key);
							
							try {
								String [] val = valueOTP.split("\\}");
								valueOTP = val[0].toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							test.log(LogStatus.INFO, "The OTP failure  property value is : " + valueOTP);
							
							if(valueOTP.contains("Incorrect manual entry")) {
								test.log(LogStatus.PASS, "Found 'OTP failure reason' value from 'Registration OTP Failure' Event : " + valueOTP);							
								break;
							}else {
								test.log(LogStatus.FAIL, "Not Found 'OTP failure reason' value from 'Registration OTP Failure' Event : " + valueOTP);
								
							}
							
						}else {
							risOTP++;
							 if(risOTP==MPPageVK.mixevnts.size()-1)
							 {
								 test.log(LogStatus.FAIL, "Not Found 'OTP failure reason' from 'Registration OTP Failure' Event");
								 risOTP=0;
							 }
							
						}

					}
				 
		
		 // Registration Attempt Event
					
					 MPPageVK.mixpnlEventsfetchLoggedInuser("Registration Attempt");
					 
						for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
							String key = entry.getKey();
							
							 if (key.equals("Subscriber Phone")) {
								
								String valuePhone = MPPageVK.mixevnts.get(key);
								
								
								if(valuePhone != null) {
									test.log(LogStatus.PASS, "Found 'Subscriber Phone' value from 'Registration Attempt' Event : " + valuePhone);
								}else {
									test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' value from 'Registration Attempt' Event : " + valuePhone);
									
								}
								
								
							 }else if(key.equals("Subscriber Email")){
								 String valueEmail = MPPageVK.mixevnts.get(key);
								 
								 if(!valueEmail.equals(null)) {
										test.log(LogStatus.PASS, "Found 'Subscriber Email' value from 'Registration Attempt' Event : " + valueEmail);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Subscriber Email' value from 'Registration Attempt' Event : " + valueEmail);
										
									}
								
							}

						}
		
		
		
		
						
		// Login Successful Event 
		
				 MPPageVK.mixpnlEventsfetchLoggedInuser("Login Successful");
		
						 for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
								String key = entry.getKey();
								
								 if (key.equals("Subscriber Phone")) {
									
									String valuePhone = MPPageVK.mixevnts.get(key);
									
									
									if(valuePhone != null) {
										test.log(LogStatus.PASS, "Found 'Subscriber Phone' value from 'Login Successful' Event : " + valuePhone);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' value from 'Login Successful' Event : " + valuePhone);
										
									}
									
									
								 }else if(key.equals("Subscriber Email")){
									 String valueEmail = MPPageVK.mixevnts.get(key);
									 
									 if(valueEmail.equals("null")) {
											test.log(LogStatus.PASS, "Found 'Subscriber Email' value from 'Login Successful' Event : " + valueEmail);
										}else {
											test.log(LogStatus.FAIL, "Not Found 'Subscriber Email' value from 'Login Successful' Event : " + valueEmail);
											
										}
									
								}

							}
			
		
		
			//		Settings Event (Account Settings Changed)
						 
						 MPPageVK.mixpnlEventsfetchLoggedInuser("Account Settings Changed");
						 
						 for (Map.Entry<String, String> settingsentry : MPPageVK.mixevnts.entrySet()) {
								String SettingsKey = settingsentry.getKey();
								
								 if (SettingsKey.equals("Updated Settings")) {
									
									String upSettings = MPPageVK.mixevnts.get(SettingsKey);
									
									ArrayList<String> updateSettings = new ArrayList<String>() {
										{
											add("Email");
											add("Phone");
											add("Password Reset");
											add("Stream Quality");
											add("Preferred Language");
											add("Downloads Enabled");
											add("Cellular Playback");
											add("Cellular Downloads");
											add("Download Quality");
											add("Notifications");
											add("Allow Casting");
											add("Profile PINs");
											add("Cache Clear");
											add("Share");
											
										}
									};
									
									
									if(updateSettings.contains(upSettings) ) {
										test.log(LogStatus.PASS, "Found 'Updated Settings' value from 'Account Settings Changed' Event : " + upSettings);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Updated Settings' value from 'Account Settings Changed' Event : " + upSettings);
										
									}
									
									
								 }else if(SettingsKey.equals("Subscriber Email")){
									 String valueEmail = MPPageVK.mixevnts.get(SettingsKey);
									 
									 test.log(LogStatus.INFO, "The New Email id is : " + genEmail);
									 
									 if(valueEmail.equals(genEmail)) {
											test.log(LogStatus.PASS, "Found 'Subscriber Email' value from 'Account Settings Changed' Event : " + valueEmail);
										}else {
											test.log(LogStatus.FAIL, "Not Found 'Subscriber Email' value from 'Account Settings Changed' Event : " + valueEmail);
											
										}
									
								}else if(SettingsKey.equals("Subscriber Phone")) {
									String subphone = MPPageVK.mixevnts.get(SettingsKey);
									
									if(!subphone.equals("null")) {
										test.log(LogStatus.PASS, "Found 'Subscriber Phone' value from 'Account Settings Changed' Event : " + subphone);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' value from 'Account Settings Changed' Event : " + subphone);
									}
									
								}else if(SettingsKey.equals("Cellular Playback")) {
									String cellplay = MPPageVK.mixevnts.get(SettingsKey);
									if(cellplay.equals("true") || cellplay.equals("false") && !cellplay.equals("null")) {
										test.log(LogStatus.PASS, "Found 'Cellular Playback' value from 'Account Settings Changed' Event : " + cellplay);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Cellular Playback' value from 'Account Settings Changed' Event : " + cellplay);
									}
									
								}else if(SettingsKey.equals("Default Stream Quality")) {
									String defaulStremQul = MPPageVK.mixevnts.get(SettingsKey);
									
									if(defaulStremQul.equals("Auto") || defaulStremQul.equals("High") || defaulStremQul.equals("Low") || defaulStremQul.equals("Medium")) {
										test.log(LogStatus.PASS, "Found 'Default Stream Quality' value from 'Account Settings Changed' Event : " + defaulStremQul);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Default Stream Quality' value from 'Account Settings Changed' Event : " + defaulStremQul);
									}
									
									
								}else if(SettingsKey.equals("Preferred Language")) {
									String perfreLang = MPPageVK.mixevnts.get(SettingsKey);
									
									if(perfreLang.equals("English") || perfreLang.equals("Tamil")) {
										test.log(LogStatus.PASS, "Found 'Preferred Language' value from 'Account Settings Changed' Event : " + perfreLang);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Preferred Language' value from 'Account Settings Changed' Event : " + perfreLang);
									}
								}else if(SettingsKey.equals("Downloads Enabled")) {
									String downloadEnble = MPPageVK.mixevnts.get(SettingsKey);
									if(downloadEnble.equals("true") || downloadEnble.equals("false") && !downloadEnble.equals("null")) {
										test.log(LogStatus.PASS, "Found 'Downloads Enabled' value from 'Account Settings Changed' Event : " + downloadEnble);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Downloads Enabled' value from 'Account Settings Changed' Event : " + downloadEnble);
									}
								}else if(SettingsKey.equals("Cellular Downloads")) {
									String cellularDown = MPPageVK.mixevnts.get(SettingsKey);
									if(cellularDown.equals("true") || cellularDown.equals("false") && !cellularDown.equals("null")) {
										test.log(LogStatus.PASS, "Found 'Cellular Downloads' value from 'Account Settings Changed' Event : " + cellularDown);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Cellular Downloads' value from 'Account Settings Changed' Event : " + cellularDown);
									}
								}else if(SettingsKey.equals("Download Quality")) {
									String downquality = MPPageVK.mixevnts.get(SettingsKey);
									if(downquality.equals("Auto") || downquality.equals("High") || downquality.equals("Low") || downquality.equals("Medium")) {
										test.log(LogStatus.PASS, "Found 'Download Quality' value from 'Account Settings Changed' Event : " + downquality);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Download Quality' value from 'Account Settings Changed' Event : " + downquality);
									}
								}else if(SettingsKey.equals("Notifications")) {
									String notifications = MPPageVK.mixevnts.get(SettingsKey);
									if(notifications.equals("true") || notifications.equals("false") && !notifications.equals("null")) {
										test.log(LogStatus.PASS, "Found 'Notifications' value from 'Account Settings Changed' Event : " + notifications);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Notifications' value from 'Account Settings Changed' Event : " + notifications);
									}
								}else if(SettingsKey.equals("Setting value changed")) {
									String settingsvalchang = MPPageVK.mixevnts.get(SettingsKey);
									try {
										String [] val = settingsvalchang.split("\\}");
										settingsvalchang = val[0].toString();
									} catch (Exception e) {
										// TODO: handle exception
									}
								    
								    test.log(LogStatus.INFO, "The Email id is : " + settingsvalchang);
								    
								    String emailId = "Email changed to "+genEmail ;
								    test.log(LogStatus.INFO, "The changed email id is : " + emailId);
								    
								    
									ArrayList<String> settingsValChange = new ArrayList<String>() {
										{
											
											add("Phone changed to");
											add("Password has been Reset");
											add("PIN has been Reset");
											add("Stream quality changed to Low");
											add("Stream quality changed to Auto");
											add("Preferred Language changed to Tamil");
											add("Preferred Language changed to English");
											add("Downloads Enabled setting turned OFF");
											add("Downloads Enabled setting turned ON");
											add("Cellular Playback setting turned OFF");
											add("Cellular Playback setting turned ON");
											add("Cellular Downloads setting turned OFF");
											add("Cellular Downloads setting turned ON");
											add("Download quality changed to Low");
											add("Notifications setting turned OFF/ON");
											add("Cache Cleared");
											add("Share");
											
										}
									};
									
									
									if(settingsValChange.contains(settingsvalchang) || settingsvalchang.equals(emailId)) {
										test.log(LogStatus.PASS, "Found 'Setting value changed' value from 'Account Settings Changed' Event : " + settingsvalchang);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Setting value changed' value from 'Account Settings Changed' Event : " + settingsvalchang);
										
									}
									
									
									
								}

							}
			
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
		
	
   }

@DataProvider
public Object[][] getData() {
	return DataUtil.getData(testName, xls);
}

}
