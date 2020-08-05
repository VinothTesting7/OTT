package com.viacom.mixpanel;

import java.time.Duration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.apache.xerces.dom.PSVIDOMImplementationImpl;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.melloware.jintellitype.Main;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.MPPageVK;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import net.sf.saxon.instruct.GlobalVariable;

// Author - Suresh 

public class VKOnboardingEvents_P2 extends BaseTestV2{
	
	String testName = "VKOnboardingEvents_P2";
    String testName1 = "VootKidsEvents_P1";
	
	String duration = "",total_sessions = "";
	 
	String event1 = "Splash Screen Loaded";
	String event2 = "Introduction Screen Loaded";
	String event3 = "Clicked the 30 Day Free Trial Offer";
	String event4 = "Clicked on Login button";
	String event5 = "Login Screen Loaded";
	String event6 = "Sign-Up Screen Loaded";
	String event7 = "Create Profile Screen Loaded";
	String event8 = "Clicked on Forget Password";
	String event9 = "Clicked on Sign-Up Button";
	String event10 = "Entered New Profile Name";
	String event11 = "Entered New Profile Date of Birth";
	String event12 = "Create Your Buddy Screen Loaded";
	String event13 = "Choose Your Favorites Screen Loaded";
	String event14 = "Entered My Stuff Section";
	String event15 = "Entered Watch Section";
	String event16 = "Entered Read Section";
	String event17 = "Entered Search Section";
	String event18 = "Entered Learn Section";
	String event19 = "Resetted Password";
	
	
	public static Map<String, String> valuesMap = new HashMap<String, String>();
	
	
	
	@Test(dataProvider = "getData")
	public void settingsDeviceCellularFunctionality(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VKOnboardingEvents_P2");
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
		 
		String signMobileNum = data.get("signMobileNum");
		String passwordStr = data.get("passwordStr");
		String otpstr = data.get("otpstr");
		String loginMobileEdit = data.get("Email");
		String loginPassword = data.get("Password");
		String VkEventsClass = data.get("VkEventsClass");
		
		
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
			
			 
			 
			 //put app in background
			 settingsPage.putBackGroundApp();
			 
			 driver.hideKeyboard();
		// click Login Link
			 if(Utilities.explicitWaitClickable(driver, singnupPage.loginLinkinSignupPage, 10)) {
				 singnupPage.loginLinkinSignupPage.click();
				 test.log(LogStatus.INFO, "clicked on Login Link in SignUp screen");
				 Thread.sleep(30000);
			 }
			 
			
			 
			 
		// Click on Forgot Password Link
		for(int f = 0 ; f<2;f++) {	 
			if(Utilities.explicitWaitClickable(driver, singnupPage.forgotpasswordinLoginPage, 10)) {
				singnupPage.forgotpasswordinLoginPage.click();
				Thread.sleep(20000);
				driver.pressKeyCode(AndroidKeyCode.BACK);
			}
		}	 
			 
		// click on SignUp Link
		
		if(Utilities.explicitWaitClickable(driver, singnupPage.signuplinkLoginPage, 10)) {
			singnupPage.signuplinkLoginPage.click();
			Thread.sleep(30000);
		}
		
		
		settingsPage.putBackGroundApp();
		
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// login page 
		
		 if(Utilities.explicitWaitClickable(driver, singnupPage.loginLinkinSignupPage, 60)) {
			 singnupPage.loginLinkinSignupPage.click();
			 test.log(LogStatus.INFO, "clicked on Login Link in SignUp screen");

		 }
		 
		 
		// sending Mobile Number to Edit Text Mobile Filed
			if(Utilities.explicitWaitVisible(driver, singnupPage.mobilefieldinLoginPage, 10)) {
				singnupPage.mobilefieldinLoginPage.click();
				singnupPage.mobilefieldinLoginPage.clear();
				Thread.sleep(200);
				singnupPage.mobilefieldinLoginPage.sendKeys(loginMobileEdit);
			}else {
				test.log(LogStatus.FAIL, "Not able to find Mobile Edit text filed in login page / Not able to send Mobile Number to Mobile Number filed");
				BasePageV2.takeScreenshot();
			}
		
			
			 
			// sending password to password Edit text filed
			if(Utilities.explicitWaitVisible(driver, singnupPage.passwordfiledinLoginPage, 10)) {
         		singnupPage.passwordfiledinLoginPage.click();
				singnupPage.passwordfiledinLoginPage.clear();
				singnupPage.passwordfiledinLoginPage.sendKeys(loginPassword);
			}else {
				test.log(LogStatus.FAIL, "Not displayed Login password Edit Test filed in Login page ");
				BasePageV2.takeScreenshot();
			}
		 
		 
		
		// click on Login button
		
		if(Utilities.explicitWaitClickable(driver, singnupPage.loginbtn, 10)) {    
			singnupPage.loginbtn.click();
			Thread.sleep(40000);
		}else {
			test.log(LogStatus.FAIL, "Not Displayed Login Button in login page / Not able to click on Login button in login page");
		}
		
		
		// click on create Button in create profile screen
		if(Utilities.explicitWaitClickable(driver, singnupPage.createProBtn, 60)) {
			singnupPage.createProBtn.click();
			test.log(LogStatus.INFO, "Clicked on create profile button in select profile screen");
		}else {
			test.log(LogStatus.FAIL, "Not able to click on create profile button in select profile screen / not found");
			BasePageV2.takeScreenshot();
		}
		
		
		// fill the profile Name and date of birth fields 
		
		if(Utilities.explicitWaitVisible(driver, settingsPage.editProNameEditText, 20)) {
			settingsPage.editProNameEditText.click();
			settingsPage.editProNameEditText.clear();
			settingsPage.editProNameEditText.sendKeys("DoneProfileNameOfVootKidsForAndroid");
		}else {
			test.log(LogStatus.FAIL, "Not Displayed profile Edit Text in New Profile Screen");
			BasePageV2.takeScreenshot();
		}
		
		// fill the Date of Birth in New Profile screen
		if(Utilities.explicitWaitVisible(driver, settingsPage.settingsProfileEditProDateEdit, 20)) {
			settingsPage.settingsProfileEditProDateEdit.click(); 
			settingsPage.calenderOkBtn.click();
			test.log(LogStatus.INFO, "Click on 'Ok' button in calender screen");
	
		}else {
			test.log(LogStatus.FAIL, "Not Displayed profile Edit Text in New Profile Screen");
			BasePageV2.takeScreenshot();
		}
		
		// click on Next button 
		if(Utilities.explicitWaitClickable(driver, settingsPage.settingsEditProfilrNextBtn, 30)) {
			settingsPage.settingsEditProfilrNextBtn.click();
			test.log(LogStatus.INFO, "Click on Next button in New profile screen ");
			Thread.sleep(10000);
		}else {
			test.log(LogStatus.FAIL, "Not Displayed Next button in New Proile screen /Not able to click");
			BasePageV2.takeScreenshot();
		}
		
		for(int b = 0 ; b < 2 ; b++) {
			driver.pressKeyCode(AndroidKeyCode.BACK);
			// click on Next button 
			if(Utilities.explicitWaitClickable(driver, settingsPage.settingsEditProfilrNextBtn, 30)) {
				settingsPage.settingsEditProfilrNextBtn.click();
				test.log(LogStatus.INFO, "Click on Next button in New profile screen ");
			}else {
				test.log(LogStatus.FAIL, "Not Displayed Next button in New Proile screen /Not able to click");
				BasePageV2.takeScreenshot();
			}
			Thread.sleep(10000);
			
		}
		
		for(int f = 0 ; f < 2 ; f++) {
			
			// click on Next button 
			if(Utilities.explicitWaitClickable(driver, settingsPage.settingsEditProfilrNextBtn, 30)) {
				settingsPage.settingsEditProfilrNextBtn.click();
				test.log(LogStatus.INFO, "Click on Next button in New profile screen ");
				Thread.sleep(10000);
				if(Utilities.explicitWaitClickable(driver, settingsPage.settingsEditProfilrNextBtn, 30)) {
					settingsPage.settingsEditProfilrNextBtn.click();
					Thread.sleep(10000);
				}else {
					test.log(LogStatus.FAIL, "Not Displayed Next button in Favourite buddy screen /Not able to click");
					BasePageV2.takeScreenshot();
				}
				
				settingsPage.putBackGroundApp();
			}else {
				test.log(LogStatus.FAIL, "Not Displayed Next button in New Proile screen /Not able to click");
				BasePageV2.takeScreenshot();
			}
			Thread.sleep(10000);
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
			
		}

		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		
		// select profile
		
		if(Utilities.explicitWaitClickable(driver, singnupPage.proicon, 30)) {
			singnupPage.proicon.click();
			test.log(LogStatus.INFO, "click on profile icon in Select Profile page / selected the profile ");
		}else {
			test.log(LogStatus.FAIL, "Does not click on profile icon in select profile page/ Does not selected the profile");
			BasePageV2.takeScreenshot();
		}
		
		// click on Allow button on pop up
		if(Utilities.explicitWaitClickable(driver, homePage.homeAllowButton, 10)) {
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
		
		for(int w = 0 ; w < 3 ; w++) {

		try {
			
			homePage.tabClick("Watch");
			Thread.sleep(5000);
			homePage.tabClick("Read");
			Thread.sleep(5000);
			homePage.tabClick("Listen");
			Thread.sleep(5000);
			homePage.tabClick("Learn");
			settingsPage.putBackGroundApp();
			Thread.sleep(10000);
			if(Utilities.explicitWaitClickable(driver, homePage.learnGotitBtn, 10)) {
				homePage.learnGotitBtn.click();
			}
			
			
			homePage.tabClick("My Stuff");
			Thread.sleep(5000);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		}
		
		// click on Search Icon
		if(Utilities.explicitWaitClickable(driver, searchPage.Search, 30)) {
			searchPage.Search.click();
			Thread.sleep(10000);
		}else {
			test.log(LogStatus.FAIL, "Not displayed Search icon in home page / Not able to click on search icon");
			BasePageV2.takeScreenshot();
		}
	
		settingsPage.putBackGroundApp();
		Thread.sleep(5000);
		
		// create password Random 
		  String strRandom = "";
		  String strNumbers = "abcdefghijklmnopqrstuvwxyz0123456789";
		  Random rnd = new Random();
		  StringBuilder strRandomNumber = new StringBuilder(9);
		  for (int i = 0; i<6; i++)
		   {strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));}
		  strRandom = strRandomNumber.toString();  
			String name = strRandom;
		  System.out.println("The New Password created is  :" + name );
		  test.log(LogStatus.INFO, "The New Password created is  :" + name);
		
		  driver.pressKeyCode(AndroidKeyCode.BACK);
		  
		  settingsPage.putBackGroundApp();
	
		  // click on Profile icon
		  if(Utilities.explicitWaitClickable(driver, homePage.profileBtn, 50)) {
			  homePage.profileBtn.click();
			  test.log(LogStatus.INFO, "click on profile icon in Home page");
			  if(Utilities.explicitWaitClickable(driver, settingsPage.parentZoneButton, 30)) {
				  settingsPage.parentZoneButton.click();
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
					  
					  // click on Account Settings Link
					  if(Utilities.explicitWaitClickable(driver, settingsPage.settingTextinParantZoneAccount, 30)) {
							settingsPage.settingTextinParantZoneAccount.click();
							test.log(LogStatus.INFO, "click on Account settings in Settings page");
							// click on reset Password Link in Account page
							if(Utilities.explicitWaitClickable(driver, settingsPage.accountResetPawd, 30)) {
								settingsPage.accountResetPawd.click();
								test.log(LogStatus.INFO, "click on Account Reset Password link ");
								
								// send Password to Current password Filed 
								if(Utilities.explicitWaitVisible(driver, settingsPage.currentPasswordEditText, 30)) {
									settingsPage.currentPasswordEditText.click();
									settingsPage.currentPasswordEditText.clear();
									settingsPage.currentPasswordEditText.sendKeys(loginPassword);    // sending Current password 
									test.log(LogStatus.INFO, "Current Password is : " + loginPassword);
									System.out.println("Current Password is : " + loginPassword);
								}else {
									BasePageV2.reportFail("Not Diaplyed Current password field in Reset passowrd page");
								}
								
								//Sending New Password to New Password field
								if(Utilities.explicitWaitVisible(driver, settingsPage.newPasswordEditText, 30)) {
									settingsPage.newPasswordEditText.click();
									settingsPage.newPasswordEditText.clear();
									settingsPage.newPasswordEditText.sendKeys(name);    // sending New password
									test.log(LogStatus.INFO, "Sending the New Password to New Password feild  : " + name);
									System.out.println("Sending the New Password to New Password feild  : " + name);
								}else {
									BasePageV2.reportFail("Not Diaplyed Current password field in Reset passowrd page");
								}
								
								// Sending New Password to Confirm New password field
								
								driver.hideKeyboard();
								
								if(Utilities.explicitWaitVisible(driver, settingsPage.confirmNewPasswordEditText, 30)) {
									settingsPage.confirmNewPasswordEditText.click();
									settingsPage.confirmNewPasswordEditText.clear();
									settingsPage.confirmNewPasswordEditText.sendKeys(name);    // sending New password
									test.log(LogStatus.INFO, "Sending the New Password to Confirm New Password feild  : " + name);
									System.out.println("Sending the New Password to Confirm New Password feild  : " + name);
									driver.hideKeyboard();
									
								}else {
									BasePageV2.reportFail("Not Diaplyed Current password field in Reset passowrd page");
								}
								
								
								
								// click on Save Button
								
								if(Utilities.explicitWaitClickable(driver, settingsPage.saveButtonResetPassword, 30)) {
									settingsPage.saveButtonResetPassword.click();
									
									if(Utilities.explicitWaitVisible(driver, settingsPage.passwordResetSuccussMsg, 50)) {
										test.log(LogStatus.INFO, "Password Resetted Successfully");
										Thread.sleep(30000);
										
										settingsPage.putBackGroundApp();
										Thread.sleep(2000);
										settingsPage.putBackGroundApp();
										Thread.sleep(20000);
										
										settingsPage.changeCellData(testName,name,2);  //sending password to overwrite in Excel Sheet
										
										settingsPage.changeCellData(testName1,name,2);  // sending the Password to overwrite the Excel Sheet
										
										if(Utilities.explicitWaitClickable(driver, settingsPage.pinResetSuccessCancelBtn, 30)) {
											settingsPage.pinResetSuccessCancelBtn.click();
											Thread.sleep(10000);
										}else {
											BasePageV2.reportFail("Failed to click on Cancel button on Password Reset pop up");
										}
										
										
								    }else {
								    	BasePageV2.reportFail("Not Resetted Password");
								    }
									
								}else {
									BasePageV2.reportFail("Not Displayed Save button in Reset password page / Not click");
								}
								
								
							}else {
								BasePageV2.reportFail("Not Displayed Reset Password Link in Account Settings page");
							}
							
							
					  }else {
						  BasePageV2.reportFail("Not Displayed Account Settings in Settings Page / Not Click ");
					  }
				
					  
				  }else {
					 BasePageV2.reportFail("NOt Displayed settings icon in parent zone page / Not click");
				  }
		
				  
			  }else {
				  test.log(LogStatus.FAIL, "Not Displayed Parent Zone button in Switch Profile page / Not Click ");
				  BasePageV2.takeScreenshot();
			  }
	
		  }else {
			  test.log(LogStatus.FAIL, "Failed to click on profile icon in home page / Not Displayed in home page");
			  BasePageV2.takeScreenshot();
		  }
		
		
		
		
		
		
		
		
		
		
		
		Utilities.saveandcloseCharles("onBoardingEvents");
		
		
	
	// Splash Screen Loaded Event
	
	
	MPPageVK.mixpnlEventsfetchLoggedInuser("Splash Screen Loaded");
	
	
int count=0;
	for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
		String key = entry.getKey();
		
		 if (key.equals("GAID")) {
			
			String valueGAID = MPPageVK.mixevnts.get(key);
			
			if(valueGAID != null) {
				test.log(LogStatus.PASS, "Found GAID from 'Splash Screen Loaded' Event : " + valueGAID);
			}else {
				test.log(LogStatus.FAIL, "Not Found GAID from 'Splash Screen Loaded' Event : " + valueGAID);
				
			}
			
		}else if(key.equals("distinct_id")) {
			String valuedistin = MPPageVK.mixevnts.get(key);
			
			try {
				String [] val = valuedistin.split("\\}");
				valuedistin = val[0].toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(!valuedistin.equals("null")) {
				test.log(LogStatus.INFO, "Found 'distinct_id' from 'Splash Screen Loaded' Event : " + valuedistin);
			}else {
				test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Splash Screen Loaded' Event : " + valuedistin);
				
			}

			
		}else
		 {
			 count++;
			 if(count==MPPageVK.mixevnts.size()-1)
			 {
				 test.log(LogStatus.FAIL, "Not Found GAID from 'Splash Screen Loaded' Event");
				 count=0;
			 }
		 }

	}
	
	

	
       
	
	
	// Introduction Screen Loaded  Event 
	
	MPPageVK.mixpnlEventsfetchLoggedInuser("Introduction Screen Loaded");
	

	
	
	for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
		String key = entry.getKey();
		
		if (key.equals("GAID")) {
			
			String valueGAID = MPPageVK.mixevnts.get(key);
			
			if(valueGAID != null) {
				test.log(LogStatus.INFO, "Found GAID from 'Introduction Screen Loaded' Event : " + valueGAID);
			}else {
				test.log(LogStatus.FAIL, "Not Found GAID from 'Introduction Screen Loaded' Event : " + valueGAID);
			
			}
			
		}else if(key.equals("distinct_id")) {
			String valuedistin = MPPageVK.mixevnts.get(key);
			
			try {
				String [] val = valuedistin.split("\\}");
				valuedistin = val[0].toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(valuedistin != null) {
				test.log(LogStatus.INFO, "Found 'distinct_id' from 'Introduction Screen Loaded' Event : " + valuedistin);
			}else {
				test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Introduction Screen Loaded' Event : " + valuedistin);
				
			}
		}

	}

	
	
	
	
    // Clicked the 30 Day Free Trial Offer Even 
	
	MPPageVK.mixpnlEventsfetchLoggedInuser("Clicked the 30 Day Free Trial Offer");
	
	
	
	for (Map.Entry<String, String> entry : MPPageVK.mixevnts.entrySet()) {
		String key = entry.getKey();
	    
		
		 if (key.equals("GAID")) {
			
			String valueGAID = MPPageVK.mixevnts.get(key);
			
			if(valueGAID != null) {
				test.log(LogStatus.INFO, "Found GAID from 'Clicked the 30 Day Free Trial Offer' Event : " + valueGAID);
			}else {
				test.log(LogStatus.FAIL, "Not Found GAID from 'Clicked the 30 Day Free Trial Offer' Event : " + valueGAID);
				
			}
			
		}else if(key.equals("distinct_id")) {
			String valuedistin = MPPageVK.mixevnts.get(key);
			
			try {
				String [] val = valuedistin.split("\\}");
				valuedistin = val[0].toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(!valuedistin.equals("null")) {
				test.log(LogStatus.INFO, "Found 'distinct_id' from 'Clicked the 30 Day Free Trial Offer' Event : " + valuedistin);
			}else {
				test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Clicked the 30 Day Free Trial Offer' Event : " + valuedistin);
				
			}
	}else {
	}

	}
	
	
	
	
	// Sign-Up Screen Loaded Even
	
MPPageVK.mixpnlEventsfetchLoggedInuser("Sign-Up Screen Loaded");
	

	for (Map.Entry<String, String> entry1 : MPPageVK.mixevnts.entrySet()) {
		String key1 = entry1.getKey();
	    
		
		 if (key1.equals("GAID")) {
			
			String valueGAID = MPPageVK.mixevnts.get(key1);
			
			if(valueGAID != null) {
				test.log(LogStatus.INFO, "Found GAID from 'Sign-Up Screen Loaded' Event : " + valueGAID);
			}else {
				test.log(LogStatus.FAIL, "Not Found GAID from 'Sign-Up Screen Loaded' Event : " + valueGAID);
				
			}
			
		}else if(key1.equals("distinct_id")) {
			String valuedistin = MPPageVK.mixevnts.get(key1);
			
			try {
				String [] val = valuedistin.split("\\}");
				valuedistin = val[0].toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(!valuedistin.equals("null")) {
				test.log(LogStatus.INFO, "Found 'distinct_id' from 'Sign-Up Screen Loaded' Event : " + valuedistin);
			}else {
				test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Sign-Up Screen Loaded' Event : " + valuedistin);
				
			}
		 
	}else {
		
	}

	}
	

		
		// Login Screen Loaded Even
		
		MPPageVK.mixpnlEventsfetchLoggedInuser("Login Screen Loaded");
			
		
			
			for (Map.Entry<String, String> entry2 : MPPageVK.mixevnts.entrySet()) {
				String key2 = entry2.getKey();
			    
				if (key2.equals("GAID")) {
					
					String valueGAID = MPPageVK.mixevnts.get(key2);
					
					if(valueGAID != null) {
						test.log(LogStatus.INFO, "Found GAID from 'Login Screen Loaded' Event : " + valueGAID);
					}else {
						test.log(LogStatus.FAIL, "Not Found GAID from 'Login Screen Loaded' Event : " + valueGAID);
						
					}
					
				}else if(key2.equals("distinct_id")) {
					String valuedistin = MPPageVK.mixevnts.get(key2);
					
					try {
						String [] val = valuedistin.split("\\}");
						valuedistin = val[0].toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
					if(!valuedistin.equals("null")) {
						test.log(LogStatus.INFO, "Found 'distinct_id' from 'Sign-Up Screen Loaded' Event : " + valuedistin);
					}else {
						test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Sign-Up Screen Loaded' Event : " + valuedistin);
						
					}
				
				
			}else {
				
			}

			}
			
			
	
	 
			// Clicked on Login button Even
			
			
          MPPageVK.mixpnlEventsfetchLoggedInuser("Clicked on Login button");
			
			
			
			for (Map.Entry<String, String> entry1 : MPPageVK.mixevnts.entrySet()) {
				String key1 = entry1.getKey();
			    
				 if (key1.equals("GAID")) {
					
					String valuelogiBtn = MPPageVK.mixevnts.get(key1);
					
					if(valuelogiBtn != null) {
						test.log(LogStatus.INFO, "Found GAID from 'Clicked on Login button' Event : " + valuelogiBtn);
					}else {
						test.log(LogStatus.FAIL, "Not Found GAID from 'Clicked on Login button' Event : " + valuelogiBtn);
						
					}
					
				}else if(key1.equals("distinct_id")) {
					String valuedistin = MPPageVK.mixevnts.get(key1);
					
					try {
						String [] val = valuedistin.split("\\}");
						valuedistin = val[0].toString();
					} catch (Exception e) {
						// TODO: handle exception
					}
					if(!valuedistin.equals("null")) {
						test.log(LogStatus.INFO, "Found 'distinct_id' from 'Sign-Up Screen Loaded' Event : " + valuedistin);
					}else {
						test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Sign-Up Screen Loaded' Event : " + valuedistin);
						
					}
				 
			}else {
				
			}

			}
			
		
		
	 //Clicked on Forget Password Even
			
			
			 MPPageVK.mixpnlEventsfetchLoggedInuser("Clicked on Forget Password");
				
				
				
				for (Map.Entry<String, String> entry3 : MPPageVK.mixevnts.entrySet()) {
					String key3 = entry3.getKey();
				    
					 if (key3.equals("GAID")) {
						
						String valueForgotLink = MPPageVK.mixevnts.get(key3);
						
						if(valueForgotLink != null) {
							test.log(LogStatus.INFO, "Found GAID from 'Clicked on Forget Password' Event : " + valueForgotLink);
						}else {
							test.log(LogStatus.FAIL, "Not Found GAID from 'Clicked on Forget Password' Event : " + valueForgotLink);
							
						}
						
					}else if(key3.equals("distinct_id")) {
						String valuedistin = MPPageVK.mixevnts.get(key3);
						
						try {
							String [] val = valuedistin.split("\\}");
							valuedistin = val[0].toString();
						} catch (Exception e) {
							// TODO: handle exception
						}
						if(valuedistin != null) {
							test.log(LogStatus.INFO, "Found 'distinct_id' from 'Clicked on Forget Password' Event : " + valuedistin);
						}else {
							test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Clicked on Forget Password' Event : " + valuedistin);
							
						}
						
					}else if(key3.equals("Subscriber Phone")) {
						
						String valuesub = MPPageVK.mixevnts.get(key3);
						if(valuesub != null) {
							test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Clicked on Forget Password' Event : " + valuesub);
						}else {
							test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Clicked on Forget Password' Event : " + valuesub);
							
						}
					}
				}
				
				
		
	  // Clicked on Sign-Up Button Event
	 
				 MPPageVK.mixpnlEventsfetchLoggedInuser("Clicked on Sign-Up Button");
					
					
					
					for (Map.Entry<String, String> entry4 : MPPageVK.mixevnts.entrySet()) {
						String key4 = entry4.getKey();
					    
						 if (key4.equals("GAID")) {
							
							String signupLink = MPPageVK.mixevnts.get(key4);
							
							if(signupLink != null) {
								test.log(LogStatus.INFO, "Found GAID from 'Clicked on Sign-Up Button' Event : " + signupLink);
							}else {
								test.log(LogStatus.FAIL, "Not Found GAID from 'Clicked on Sign-Up Button' Event : " + signupLink);
								
							}
							
						}else if(key4.equals("distinct_id")) {
							String signupdisLink = MPPageVK.mixevnts.get(key4);
							try {
								String [] val = signupdisLink.split("\\}");
								signupdisLink = val[0].toString();
							} catch (Exception e) {
								// TODO: handle exception
							}
							
							if(signupdisLink != null) {
								test.log(LogStatus.INFO, "Found 'distinct_id' from 'Clicked on Sign-Up Button' Event : " + signupdisLink);
							}else {
								test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Clicked on Sign-Up Button' Event : " + signupdisLink);
								
							}
							
						}else if(key4.equals("Subscriber Phone")) {
							
							String valuesub = MPPageVK.mixevnts.get(key4);
							if(valuesub != null) {
								test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Clicked on Sign-Up Button' Event : " + valuesub);
							}else {
								test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Clicked on Sign-Up Button' Event : " + valuesub);
								
							}
						}
					}
					
					
	 
	 
	  // Create Profile Screen Loaded  Event 
					
					 MPPageVK.mixpnlEventsfetchLoggedInuser("Create Profile Screen Loaded");
						
						int creatPro = 0;
						
						for (Map.Entry<String, String> entry5 : MPPageVK.mixevnts.entrySet()) {
							String key5 = entry5.getKey();
						    
							 if (key5.equals("GAID")) {
								
								String creatGaid = MPPageVK.mixevnts.get(key5);
								
								if(creatGaid != null) {
									test.log(LogStatus.INFO, "Found GAID from 'Create Profile Screen Loaded' Event : " + creatGaid);
								}else {
									test.log(LogStatus.FAIL, "Not Found GAID from 'Create Profile Screen Loaded' Event : " + creatGaid);
									creatPro++;
								}
								
							}else if(key5.equals("distinct_id")) {
								String creatdisLink = MPPageVK.mixevnts.get(key5);
								try {
									String [] val = creatdisLink.split("\\}");
									creatdisLink = val[0].toString();
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								if(creatdisLink != null) {
									test.log(LogStatus.INFO, "Found 'distinct_id' from 'Create Profile Screen Loaded' Event : " + creatdisLink);
								}else {
									test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Create Profile Screen Loaded' Event : " + creatdisLink);
									creatPro++;
								}
								
							}else if(key5.equals("Subscriber Phone")) {
								
								String valuesub = MPPageVK.mixevnts.get(key5);
								if(valuesub != null) {
									test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Create Profile Screen Loaded' Event : " + valuesub);
								}else {
									test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Create Profile Screen Loaded' Event : " + valuesub);
									creatPro++;
								}
							}
						}
						
						if(creatPro == 0) {
							test.log(LogStatus.PASS, "Verified 'Create Profile Screen Loaded' Event ");
						}else {
							test.log(LogStatus.FAIL, "Not able to verified 'Create Profile Screen Loaded' Event / charales Response not found ");
						}
		 
		 
	   // Entered New Profile Name  Event 
						
						 MPPageVK.mixpnlEventsfetchLoggedInuser("Entered New Profile Name");
							
							int newProName = 0;
							
							for (Map.Entry<String, String> entry6 : MPPageVK.mixevnts.entrySet()) {
								String key6 = entry6.getKey();
							    
								 if (key6.equals("GAID")) {
									
									String nameGaid = MPPageVK.mixevnts.get(key6);
									
									if(nameGaid != null) {
										test.log(LogStatus.INFO, "Found GAID from 'Entered New Profile Name' Event : " + nameGaid);
									}else {
										test.log(LogStatus.FAIL, "Not Found GAID from 'Entered New Profile Name' Event : " + nameGaid);
										newProName++;
									}
									
								}else if(key6.equals("distinct_id")) {
									String namedisLink = MPPageVK.mixevnts.get(key6);
									try {
										String [] val = namedisLink.split("\\}");
										namedisLink = val[0].toString();
									} catch (Exception e) {
										// TODO: handle exception
									}
									
									if(namedisLink != null) {
										test.log(LogStatus.INFO, "Found 'distinct_id' from 'Entered New Profile Name' Event : " + namedisLink);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Entered New Profile Name' Event : " + namedisLink);
										newProName++;
									}
									
								}else if(key6.equals("Subscriber Phone")) {
									
									String valuesub = MPPageVK.mixevnts.get(key6);
									if(valuesub != null) {
										test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Entered New Profile Name' Event : " + valuesub);
									}else {
										test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Entered New Profile Name' Event : " + valuesub);
										newProName++;
									}
								}
							}
							
							if(newProName == 0) {
								test.log(LogStatus.PASS, "Verified 'Entered New Profile Name' Event ");
							}else {
								test.log(LogStatus.FAIL, "Not able to verified 'Entered New Profile Name' Event / charales Response not found ");
							}
			 
			   // Entered New Profile Date of Birth Event 
							
							 MPPageVK.mixpnlEventsfetchLoggedInuser("Entered New Profile Date of Birth");
								
								int dobPro = 0;
								
								for (Map.Entry<String, String> entry7 : MPPageVK.mixevnts.entrySet()) {
									String key7 = entry7.getKey();
								    
									 if (key7.equals("GAID")) {
										
										String dobGaid = MPPageVK.mixevnts.get(key7);
										
										if(dobGaid != null) {
											test.log(LogStatus.INFO, "Found GAID from 'Entered New Profile Date of Birth' Event : " + dobGaid);
										}else {
											test.log(LogStatus.FAIL, "Not Found GAID from 'Entered New Profile Date of Birth' Event : " + dobGaid);
											dobPro++;
										}
										
									}else if(key7.equals("distinct_id")) {
										String dobdisLink = MPPageVK.mixevnts.get(key7);
										try {
											String [] val = dobdisLink.split("\\}");
											dobdisLink = val[0].toString();
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										if(dobdisLink != null) {
											test.log(LogStatus.INFO, "Found 'distinct_id' from 'Entered New Profile Date of Birth' Event : " + dobdisLink);
										}else {
											test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Entered New Profile Date of Birth' Event : " + dobdisLink);
											dobPro++;
										}
										
									}else if(key7.equals("Subscriber Phone")) {
										
										String valuesub = MPPageVK.mixevnts.get(key7);
										if(valuesub != null) {
											test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Entered New Profile Date of Birth' Event : " + valuesub);
										}else {
											test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Entered New Profile Date of Birth' Event : " + valuesub);
											dobPro++;
										}
									}
								}
								
								if(dobPro == 0) {
									test.log(LogStatus.PASS, "Verified 'Entered New Profile Date of Birth' Event ");
								}else {
									test.log(LogStatus.FAIL, "Not able to verified 'Entered New Profile Date of Birth' Event / charales Response not found ");
								}
						
						
			// Create Your Buddy Screen Loaded	Event
						
								 MPPageVK.mixpnlEventsfetchLoggedInuser("Create Your Buddy Screen Loaded");
									
									int creatbuddyPro = 0;
									
									for (Map.Entry<String, String> entry8 : MPPageVK.mixevnts.entrySet()) {
										String key8 = entry8.getKey();
									    
										 if (key8.equals("GAID")) {
											
											String creatbuddyGaid = MPPageVK.mixevnts.get(key8);
											
											if(creatbuddyGaid != null) {
												test.log(LogStatus.INFO, "Found GAID from 'Create Your Buddy Screen Loaded' Event : " + creatbuddyGaid);
											}else {
												test.log(LogStatus.FAIL, "Not Found GAID from 'Create Your Buddy Screen Loaded' Event : " + creatbuddyGaid);
												creatbuddyPro++;
											}
											
										}else if(key8.equals("distinct_id")) {
											String creatBuddydisLink = MPPageVK.mixevnts.get(key8);
											try {
												String [] val = creatBuddydisLink.split("\\}");
												creatBuddydisLink = val[0].toString();
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											if(creatBuddydisLink != null) {
												test.log(LogStatus.INFO, "Found 'distinct_id' from 'Create Your Buddy Screen Loaded' Event : " + creatBuddydisLink);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Create Your Buddy Screen Loaded' Event : " + creatBuddydisLink);
												creatbuddyPro++;
											}
											
										}else if(key8.equals("Subscriber Phone")) {
											
											String valuesub = MPPageVK.mixevnts.get(key8);
											if(valuesub != null) {
												test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Create Your Buddy Screen Loaded' Event : " + valuesub);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Create Your Buddy Screen Loaded' Event : " + valuesub);
												creatbuddyPro++;
											}
										}
									}
									
									if(creatbuddyPro == 0) {
										test.log(LogStatus.PASS, "Verified 'Create Your Buddy Screen Loaded' Event ");
									}else {
										test.log(LogStatus.FAIL, "Not able to verified 'Create Your Buddy Screen Loaded' Event / charales Response not found ");
									}
							
						
						
	        // Choose Your Favorites Screen Loaded Event
									
						MPPageVK.mixpnlEventsfetchLoggedInuser("Choose Your Favorites Screen Loaded");		
									
                             int selectfav = 0;
									
								for (Map.Entry<String, String> entry9 : MPPageVK.mixevnts.entrySet()) {
									String key9 = entry9.getKey();
								    
									 if (key9.equals("GAID")) {
										
										String selectFavGaid = MPPageVK.mixevnts.get(key9);
										
										if(selectFavGaid != null) {
											test.log(LogStatus.INFO, "Found GAID from 'Choose Your Favorites Screen Loaded' Event : " + selectFavGaid);
										}else {
											test.log(LogStatus.FAIL, "Not Found GAID from 'Choose Your Favorites Screen Loaded' Event : " + selectFavGaid);
											selectfav++;
										}
										
									}else if(key9.equals("distinct_id")) {
										String selectFavdisLink = MPPageVK.mixevnts.get(key9);
										try {
											String [] val = selectFavdisLink.split("\\}");
											selectFavdisLink = val[0].toString();
										} catch (Exception e) {
											// TODO: handle exception
										}
										
										if(selectFavdisLink != null) {
											test.log(LogStatus.INFO, "Found 'distinct_id' from 'Choose Your Favorites Screen Loaded' Event : " + selectFavdisLink);
										}else {
											test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Choose Your Favorites Screen Loaded' Event : " + selectFavdisLink);
											selectfav++;
										}
										
									}else if(key9.equals("Subscriber Phone")) {
										
										String valuesub = MPPageVK.mixevnts.get(key9);
										if(valuesub != null) {
											test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Choose Your Favorites Screen Loaded' Event : " + valuesub);
										}else {
											test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Choose Your Favorites Screen Loaded' Event : " + valuesub);
											selectfav++;
										}
									}
								}
								
								if(selectfav == 0) {
									test.log(LogStatus.PASS, "Verified 'Choose Your Favorites Screen Loaded' Event ");
								}else {
									test.log(LogStatus.FAIL, "Not able to verified 'Choose Your Favorites Screen Loaded' Event / charales Response not found ");
								}
						
								
					//	Entered My Stuff Section Even
									
								MPPageVK.mixpnlEventsfetchLoggedInuser("Entered My Stuff Section");	
								
								int mtStuff = 0;
								try {
									
									for (Map.Entry<String, String> entry10 : MPPageVK.mixevnts.entrySet()) {
										String key10 = entry10.getKey();
									    
										 if (key10.equals("GAID")) {
											
											String myStuffGaid = MPPageVK.mixevnts.get(key10);
											
											if(myStuffGaid != null) {
												test.log(LogStatus.INFO, "Found GAID from 'Entered My Stuff Section' Event : " + myStuffGaid);
											}else {
												test.log(LogStatus.FAIL, "Not Found GAID from 'Entered My Stuff Section' Event : " + myStuffGaid);
												mtStuff++;
											}
											
										}else if(key10.equals("distinct_id")) {
											String myStuffdisLink = MPPageVK.mixevnts.get(key10);
											try {
												String [] val = myStuffdisLink.split("\\}");
												myStuffdisLink = val[0].toString();
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											if(myStuffdisLink != null) {
												test.log(LogStatus.INFO, "Found 'distinct_id' from 'Entered My Stuff Section' Event : " + myStuffdisLink);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Entered My Stuff Section' Event : " + myStuffdisLink);
												mtStuff++;
											}
											
										}else if(key10.equals("Subscriber Phone")) {
											
											String valuesub = MPPageVK.mixevnts.get(key10);
											if(valuesub != null) {
												test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Entered My Stuff Section' Event : " + valuesub);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Entered My Stuff Section' Event : " + valuesub);
												mtStuff++;
											}
										}
									}
									
					
								} catch (Exception e) {
									mtStuff++;
								}
								
								if(mtStuff == 0) {
									test.log(LogStatus.PASS, "Verified 'Entered My Stuff Section' Event ");
								}else {
									test.log(LogStatus.FAIL, "Not able to verified 'Entered My Stuff Section' Event / charales Response not found ");
								}
								
								
								// Entered Watch Section Event 
								
								
								
								MPPageVK.mixpnlEventsfetchLoggedInuser("Entered Watch Section");
								
								int watch = 0;
								try {
									
									for (Map.Entry<String, String> entry11 : MPPageVK.mixevnts.entrySet()) {
										String key11 = entry11.getKey();
									    
										 if (key11.equals("GAID")) {
											
											String watchGaid = MPPageVK.mixevnts.get(key11);
											
											if(watchGaid != null) {
												test.log(LogStatus.INFO, "Found GAID from 'Entered Watch Section' Event : " + watchGaid);
											}else {
												test.log(LogStatus.FAIL, "Not Found GAID from 'Entered Watch Section' Event : " + watchGaid);
												watch++;
											}
											
										}else if(key11.equals("distinct_id")) {
											String watchdisLink = MPPageVK.mixevnts.get(key11);
											try {
												String [] val = watchdisLink.split("\\}");
												watchdisLink = val[0].toString();
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											if(watchdisLink != null) {
												test.log(LogStatus.INFO, "Found 'distinct_id' from 'Entered Watch Section' Event : " + watchdisLink);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Entered Watch Section' Event : " + watchdisLink);
												watch++;
											}
											
										}else if(key11.equals("Subscriber Phone")) {
											
											String valuesub = MPPageVK.mixevnts.get(key11);
											if(valuesub != null) {
												test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Entered Watch Section' Event : " + valuesub);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Entered Watch Section' Event : " + valuesub);
												watch++;
											}
										}
									}
									
					
								} catch (Exception e) {
									watch++;
								}
								
								if(watch == 0) {
									test.log(LogStatus.PASS, "Verified 'Entered Watch Section' Event ");
								}else {
									test.log(LogStatus.FAIL, "Not able to verified 'Entered Watch Section' Event / charales Response not found ");
								}
								
								
								
								//Entered Read Section Event 
								
								MPPageVK.mixpnlEventsfetchLoggedInuser("Entered Read Section");	
								
								
								try {
									
									for (Map.Entry<String, String> entry12 : MPPageVK.mixevnts.entrySet()) {
										String key12 = entry12.getKey();
									    
										 if (key12.equals("GAID")) {
											
											String readGaid = MPPageVK.mixevnts.get(key12);
											
											if(readGaid != null) {
												test.log(LogStatus.INFO, "Found GAID from 'Entered Read Section' Event : " + readGaid);
											}else {
												test.log(LogStatus.FAIL, "Not Found GAID from 'Entered Read Section' Event : " + readGaid);
												
											}
											
										}else if(key12.equals("distinct_id")) {
											String readdisLink = MPPageVK.mixevnts.get(key12);
											try {
												String [] val = readdisLink.split("\\}");
												readdisLink = val[0].toString();
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											if(readdisLink != null) {
												test.log(LogStatus.INFO, "Found 'distinct_id' from 'Entered Read Section' Event : " + readdisLink);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Entered Read Section' Event : " + readdisLink);
												
											}
											
										}else if(key12.equals("Subscriber Phone")) {
											
											String valuesub = MPPageVK.mixevnts.get(key12);
											if(valuesub != null) {
												test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Entered Read Section' Event : " + valuesub);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Entered Read Section' Event : " + valuesub);
												
											}
										}
									}
									
					
								} catch (Exception e) {
									
								}
								
								
								
								
								//Entered Learn Section Even
								
								
								MPPageVK.mixpnlEventsfetchLoggedInuser("Entered Learn Section");
								
								
							
									
									for (Map.Entry<String, String> entry13 : MPPageVK.mixevnts.entrySet()) {
										String key13 = entry13.getKey();
									    
										 if (key13.equals("GAID")) {
											
											String learnGaid = MPPageVK.mixevnts.get(key13);
											
											if(learnGaid != null) {
												test.log(LogStatus.INFO, "Found GAID from 'Entered Learn Section' Event : " + learnGaid);
											}else {
												test.log(LogStatus.FAIL, "Not Found GAID from 'Entered Learn Section' Event : " + learnGaid);
												
											}
											
										}else if(key13.equals("distinct_id")) {
											String learndisLink = MPPageVK.mixevnts.get(key13);
											try {
												String [] val = learndisLink.split("\\}");
												learndisLink = val[0].toString();
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											if(!learndisLink.equals("null")) {
												test.log(LogStatus.INFO, "Found 'distinct_id' from 'Entered Learn Section' Event : " + learndisLink);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Entered Learn Section' Event : " + learndisLink);
												
											}
											
										}else if(key13.equals("Subscriber Phone")) {
											
											String valuesub = MPPageVK.mixevnts.get(key13);
											if(valuesub != null) {
												test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Entered Learn Section' Event : " + valuesub);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Entered Learn Section' Event : " + valuesub);
												
											}
										}
									}
									
					
								
								
								
								
								// Entered Search Section Event
								
								MPPageVK.mixpnlEventsfetchLoggedInuser("Entered Search Section");
	
	
								int search = 0;
								try {
									
									for (Map.Entry<String, String> entry14 : MPPageVK.mixevnts.entrySet()) {
										String key14 = entry14.getKey();
									    
										 if (key14.equals("GAID")) {
											
											String searchGaid = MPPageVK.mixevnts.get(key14);
											
											if(searchGaid != null) {
												test.log(LogStatus.INFO, "Found GAID from 'Entered Search Section' Event : " + searchGaid);
											}else {
												test.log(LogStatus.FAIL, "Not Found GAID from 'Entered Search Section' Event : " + searchGaid);
												search++;
											}
											
										}else if(key14.equals("distinct_id")) {
											String searchdisLink = MPPageVK.mixevnts.get(key14);
											try {
												String [] val = searchdisLink.split("\\}");
												searchdisLink = val[0].toString();
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											if(searchdisLink != null) {
												test.log(LogStatus.INFO, "Found 'distinct_id' from 'Entered Search Section' Event : " + searchdisLink);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Entered Search Section' Event : " + searchdisLink);
												search++;
											}
											
										}else if(key14.equals("Subscriber Phone")) {
											
											String valuesub = MPPageVK.mixevnts.get(key14);
											if(valuesub != null) {
												test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Entered Search Section' Event : " + valuesub);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Entered Search Section' Event : " + valuesub);
												search++;
											}
										}
									}
									
					
								} catch (Exception e) {
									search++;
								}
								
								if(search == 0) {
									test.log(LogStatus.PASS, "Verified 'Entered Search Section' Event ");
								}else {
									test.log(LogStatus.FAIL, "Not able to verified 'Entered Search Section' Event / charales Response not found ");
								}
	
	
					// Resetted Password Event 
								
								MPPageVK.mixpnlEventsfetchLoggedInuser("Resetted Password");	
								
								
								try {
									
									for (Map.Entry<String, String> entry15 : MPPageVK.mixevnts.entrySet()) {
										String key15 = entry15.getKey();
									    
										 if (key15.equals("GAID")) {
											
											String resetPawdGaid = MPPageVK.mixevnts.get(key15);
											
											if(resetPawdGaid != null) {
												test.log(LogStatus.INFO, "Found GAID from 'Resetted Password' Event : " + resetPawdGaid);
											}else {
												test.log(LogStatus.FAIL, "Not Found GAID from 'Resetted Password' Event : " + resetPawdGaid);
												
											}
											
										}else if(key15.equals("distinct_id")) {
											String resetPawddisLink = MPPageVK.mixevnts.get(key15);
											try {
												String [] val = resetPawddisLink.split("\\}");
												resetPawddisLink = val[0].toString();
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											if(resetPawddisLink != null) {
												test.log(LogStatus.INFO, "Found 'distinct_id' from 'Resetted Password' Event : " + resetPawddisLink);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'distinct_id' from 'Resetted Password' Event : " + resetPawddisLink);
												
											}
											
										}else if(key15.equals("Subscriber Phone")) {
											
											String valuesub = MPPageVK.mixevnts.get(key15);
											if(valuesub != null) {
												test.log(LogStatus.INFO, "Found 'Subscriber Phone' from 'Resetted Password' Event : " + valuesub);
											}else {
												test.log(LogStatus.FAIL, "Not Found 'Subscriber Phone' from 'Resetted Password' Event : " + valuesub);
												
											}
										}
									}
									
					
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								
								
								
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
	
	
}
