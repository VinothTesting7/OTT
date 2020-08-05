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
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class VrifySettingsAccountScreen extends BaseTestV2 {

	String testName = "VrifySettingsAccountScreen";
	
	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VrifySettingsAccountScreen");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Account Screen: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls722 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno722 = xls722.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno722,
				"722: Verify the email id present on the Account Settings main screen is same as when user entered while signing up/login ");

		Xls_Reader xls1723 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno723 = xls1723.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno723,
				"723: Verify the mobile number present on the Account Settings main screen is same as when user entered while signing up/login");
		
		

		Xls_Reader xls724 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno724 = xls724.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno724,
				"724: Verify the click functionality of Email field in Account settings page");

		Xls_Reader xls735 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno735 = xls735.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno735, "735: Verify the UI of Email Edit page");

		Xls_Reader xls726 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno726 = xls726.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno726,
				"726: Verify the Back arrow functionality in Email page");

		Xls_Reader xls727 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno727 = xls727.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno727,
				"727: Verify the click functionality of Edit option in Email page");

		Xls_Reader xls728 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno728 = xls728.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno728, "728: Verify the UI of Email edit page after tapping on 'Edit' ");


		Xls_Reader xls736 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno736 = xls736.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno736, "736: Verify the functionality of 'Save' button post entering valid data in 'Email' field");

		Xls_Reader xls731 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno731 = xls731.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno731, "731: Verify the functionality of 'Save ' button post entering invalid data in 'Add Email ID' field");

		Xls_Reader xls740 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno740 = xls740.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno740, "740: Verify the functionality of 'Save ' button by keeping the 'Add Email ID' field blank/empty");
		
		Xls_Reader xls734 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno734 = xls734.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno734, "734: Verify the click functionality of 'Add Email' in Settings page");
		
		Xls_Reader xls737 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno737 = xls737.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno737, "737: Verify the UI of pop-up message post successful adding Email ");
		
//		Xls_Reader xls738 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
//		int rowno738 = xls738.getRowCount("Smoke_Results") + 1;
//		xls.setCellData("Smoke_Results", "Testcase Name", rowno738, "738: Verify the functionality of Cross mark icon/outside the pop-up notification in Email Added pop-up");
		
		
	

		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		String newEmailid = null;
		LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
		SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		// navigating to settings 'ACCOUNT' page 

		
		  homepagev2.logout();
		  
//		  homepagev2.signinguptogetfreshLogincredentials();
		  
		  homepagev2.login(data.get("Email"),data.get("Password"));
		  
		// navigating to settings 'Profiles' page
//	        try {
//	        	 email=data.get("Email");
//	        	 pwd=data.get("Password");
//			 HomePageV2.login(email, pwd);   //8904584888 for this Account Mobile number
//	        }catch(Exception e) {
//	        	test.log(LogStatus.FAIL, "login Failed");
//	        }
		
		
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
	         test.log(LogStatus.INFO, "clicked on Settings Icon");
				
				if(Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZoneAccount, 10)) {
				      settingsPage.settingTextinParantZoneAccount.click();
				}else test.log(LogStatus.FAIL, "Not able to naviagte to ACCOUNT Screen/ not able to click Account option in Settings page");
				
				// Passing the Email id :suresh.k@ifocussystec.in" with hard coded mail id
				String emailId = GlobalVariables.userName;
				String verifystr = settingsPage.accountEmailId.getText();
				if(emailId.equalsIgnoreCase(verifystr)) {
					test.log(LogStatus.PASS,
							"Verifies the given Mail ID is present on the Account main screen");
					settingsPage.takeScreenshot();
					homepagev2.smokeresults("722: Verify the email id present on the Account Settings main screen is same as when user entered while signing up/login",
							rowno722, "PASS");

				}else test.log(LogStatus.FAIL, "Logged Email id is not matched with Account Email Page Email Id");
				   BasePageV2.takeScreenshot();
				
				if(Utilities.explicitWaitVisible(driver, settingsPage.accountMobile, 10)) {
					String mobileNum = "+91 "+ GlobalVariables.mobilenumber;  
					test.log(LogStatus.INFO, "Logged in Account Mobile number is  "+ mobileNum);
					String verifystrMobileNum = settingsPage.accountMobileNum.getText();
					System.out.println("The Mobile is : " + verifystrMobileNum );
					if(mobileNum.equalsIgnoreCase(verifystrMobileNum)) {
						test.log(LogStatus.PASS,
								"Verified the Mobile Number present on the Account Main Screen");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("723: Verify the mobile number present on the Account Settings main screen is same as when user entered while signing up/login",
								rowno723, "PASS");
					}else {
						test.log(LogStatus.FAIL, "Account Mobile Page Mobile Number is not matched with Logged Mobile Number");
						settingsPage.takeScreenshot();
					}
					  
				}else test.log(LogStatus.FAIL, "Mobile option is not found in Account Screen/ unable to click mobile option");

				Utilities.verticalSwipeDown(driver);
				Utilities.verticalSwipe(driver);
				
				// Checking the From ACCOUNT Email Navigating to Email Page		     
                  
				if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
					try {
						settingsPage.accountEmail.click();
					} catch (Exception e) {
						e.printStackTrace();
						test.log(LogStatus.FAIL, "Not able to tap Email Option in Account Page/unable to navigate Update Email page");
					}
					
					if(Utilities.explicitWaitVisible(driver, settingsPage.addEmail, 10)) {
						test.log(LogStatus.PASS,
								"Verified the Click Functionality on Email filed in Account Settings Page");
						settingsPage.takeScreenshot();
						homepagev2.smokeresults("724: Verify the click functionality of Email field in Account settings page",
								rowno724, "PASS");
						// validating Email screen having expected List Options							
						if(Utilities.explicitWaitVisible(driver, settingsPage.settingTextinParantZonebackbtn, 10)) {
							if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPage.addMailuserTile, 10)) {
									if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSaveBtn, 10)) {
										test.log(LogStatus.PASS,
												"verified the UI of 'Add Email' page UI");
										settingsPage.takeScreenshot();
										homepagev2.smokeresults("735: Verify the UI of 'Add Email' page UI",
												rowno735, "PASS");
									}
									if(Utilities.explicitWaitClickable(driver, settingsPage.addMailEditText, 10)) {
										if(settingsPage.addMailEditText.isEnabled()) {
											test.log(LogStatus.PASS,
													"Click functionality work fine in Edit page Email edit");
											settingsPage.takeScreenshot();
											homepagev2.smokeresults("727: Verify the click functionality of Edit option in Email page",
													rowno727, "PASS");
										}
										if(Utilities.explicitWaitClickable(driver, settingsPage.settingTextinParantZonebackbtn, 10)) {
											settingsPage.settingTextinParantZonebackbtn.click();
											test.log(LogStatus.PASS,
													"verified the Ui of Email page mail Edit is edited succussfully");
											settingsPage.takeScreenshot();
											homepagev2.smokeresults("726: Verify the Back arrow functionality in Email page",
													rowno726, "PASS");		
										}
	                   
										if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
											settingsPage.accountEmail.click();
											if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
												settingsPage.addMailEditText.clear();
												
												// Generating the new Email Id 
												newEmailid = Utilities.generateEmailid();
												settingsPage.addMailEditText.clear();            
												settingsPage.addMailEditText.sendKeys(newEmailid);
												if(Utilities.explicitWaitClickable(driver, settingsPage.addMailSaveBtn, 5)) {
												settingsPage.addMailSaveBtn.click();
												test.log(LogStatus.INFO, "Clikced on Save Button");
												}else BasePageV2.reportFail("'SAVE' Button is not found in Email Page");
												
									
												
												if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSucssEmailAdd, 10)) {
													if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSucssEmailAddCnlBtn, 10)) {
														test.log(LogStatus.PASS,
																"Verified the UI of pop-up message post successful adding Email");
														         
														
												//		xls.changeCellData(testName, newEmailid,1);
														
														settingsPage.takeScreenshot();
														homepagev2.smokeresults("737: Verify the UI of pop-up message post successful adding Email",
																rowno737, "PASS");	
	
													}else test.log(LogStatus.FAIL, "'cancel' button is not found on Emial Add Sccuss Pop up");
												}else BasePageV2.reportFail("'Email Added' Text not found on Emial Add Sccuss Pop up");
												
												
												
												if(Utilities.explicitWaitVisible(driver, settingsPage.addMailSucssEmailAdd, 10)) {
													test.log(LogStatus.PASS,
															"Verify the functionality of 'Save' button post entering valid data in 'Email' field");
													settingsPage.takeScreenshot();
													settingsPage.addMailSucssEmailAddCnlBtn.click();
													test.log(LogStatus.PASS,
															"Verify the functionality of Cross mark icon/outside the pop-up notification in Email Added pop-up");
													
//													homepagev2.smokeresults("738: Verify the functionality of 'Save' button post entering valid data in 'Email' field",
//															rowno738, "PASS");
													
													homepagev2.smokeresults("736: Verify the functionality of 'Save' button post entering valid data in 'Email' field",
															rowno736, "PASS");	
													test.log(LogStatus.PASS,
															"Verified the UI of Email edit page after tapping on 'Edit'");
													settingsPage.takeScreenshot();
													homepagev2.smokeresults("728: Verify the UI of Email edit page after tapping on 'Edit'",
															rowno728, "PASS");	
												}else test.log(LogStatus.FAIL, "Email Added  Succuss pop up not found");

											}else BasePageV2.reportFail("Email Ediy Text Box not found in Email Page");
										}else BasePageV2.reportFail("'Email' option not found in Account page");

//										if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
//											settingsPage.accountEmail.click();
//											if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
//												settingsPage.addMailEditText.clear();
//												settingsPage.addMailEditText.sendKeys("suresh.k@ifocussystec.in");
//												settingsPage.addMailSaveBtn.click();
//												Thread.sleep(2000);
//												settingsPage.addMailSucssEmailAddCnlBtn.click();
//
//											}
//										}	
										
										Utilities.verticalSwipeDown(driver);
										Utilities.verticalSwipe(driver);
										
										if(Utilities.explicitWaitVisible(driver, settingsPage.accountEmail, 10)) {
											
											settingsPage.accountEmail.click();									
											if(Utilities.explicitWaitVisible(driver, settingsPage.addMailEditText, 10)) {
												test.log(LogStatus.PASS,
														"Verified the click functionality of 'Add Email' in Settings page");
												settingsPage.takeScreenshot();
												homepagev2.smokeresults("734: Verify the click functionality of 'Add Email' in Settings page",
														rowno734, "PASS");
												settingsPage.addMailEditText.clear();
												settingsPage.addMailEditText.sendKeys("suresh.k@ifocussystec.");
												driver.pressKeyCode(AndroidKeyCode.BACK);
												settingsPage.addMailSaveBtn.click();
												
												if(Utilities.explicitWaitVisible(driver, settingsPage.addMailWrongMsg, 20)) {
													test.log(LogStatus.PASS,
															"Verify the functionality of 'Save ' button post entering invalid data in 'Add Email ID' field");
													settingsPage.takeScreenshot();
													homepagev2.smokeresults("731: Verify the functionality of 'Save ' button post entering invalid data in 'Add Email ID' field",
															rowno731, "PASS");  
														
												}else test.log(LogStatus.FAIL, "Invailed Email added Error Massage not found ");
							// Here Empty Edit text box empty should show "enter Email Id"	in below need to Modify				
												settingsPage.addMailEditText.clear();
												settingsPage.addMailSaveBtn.click();
												Thread.sleep(10000);
												if(settingsPage.addMailEditText.getText().isEmpty() && (settingsPage.addMailWrongMsg.isDisplayed())) {
													test.log(LogStatus.PASS,
															"Verified the functionality of 'Save ' button by keeping the 'Add Email ID' field blank/empty");
													settingsPage.takeScreenshot();
													homepagev2.smokeresults("740: Verify the functionality of 'Save ' button by keeping the 'Add Email ID' field blank/empty",
															rowno740, "PASS");  
												}else test.log(LogStatus.FAIL, "not found empty Email Edit text box Error");
												
												
										  }else BasePageV2.reportFail("Email Edit TExt Box not found in Update Email Page");
										}else BasePageV2.reportFail("'Emial' Option is not found in Account Settings Page");
										
									}else test.log(LogStatus.FAIL, "Not able to verify the Edit text functionality on Email Edit text box");
								}
							}else test.log(LogStatus.FAIL, "'Emial Edit text box' not found in Account Screen / not able to click");

						}else test.log(LogStatus.FAIL, "Back Arrow button not found in Edit Email page");	
					}else BasePageV2.reportFail("not able to navigate to Update Email page");

				}else test.log(LogStatus.FAIL, "'Emial' not found in Account Screen / not able to click"); 	     


		
		  }else BasePageV2.reportFail("Settings Icon not found/not able to click");
		
		
		for(int i = 1 ; i < 5 ; i++) {
			driver.pressKeyCode(AndroidKeyCode.BACK);
			if(Utilities.explicitWaitVisible(driver, settingsPage.switchProfile, 10)) {
				break;
			}
		}
		
		
		
		homepagev2.logout();
		homepagev2.login("9988776655", "secret");
		
		}
	
	  

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
