package com.viacom.parentzone;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sun.mail.handlers.text_html;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class ResetParentAccountPinFunctionality_1 extends BaseTestV2 {

	String testName = "ResetParentAccountPinFunctionality_1";

	@Test(dataProvider = "getData")
	public void resetParentAccountPinFunctionality_1(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Settings Reset Parent Account Pin Page_1 ");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls763 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno763 = xls763.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno763,
				"763: Verify the click functionality of Reset Parent Zone PIN field in Account settings page");	
		
		Xls_Reader xls764 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno764 = xls764.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno764,
				"764: Verify the UI of Reset Parent Zone PIN");	
		
		Xls_Reader xls765 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno765 = xls765.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno765,
				"765: Verify the Back arrow functionality in Reset Parent Zone PIN Page");	
		
		
	
		// Launching the Voot-kids App
				launchApp();
				test.log(LogStatus.INFO, "Application launched successfully");

				SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
				HomePageV2 homepagev2 = new HomePageV2(driver, test);
				
				
				try {
					String email= data.get("Email");
					String pwd = data.get("Password");
					HomePageV2.login(email, pwd);
		        }catch(Exception e) {
		        	test.log(LogStatus.FAIL, "login Failed");
		        }
				
				

				// navigating to settings 'Account-reset Parent Zone Pin' page

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
				
				
				//Navigating to Reset Pin page to set InCorrect Pin in Current Pin Filed
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
					settingsPageV2.settingsIcon.click();
					test.log(LogStatus.INFO, "clicked on Settings Icon");
						settingsPageV2.settingTextinParantZoneAccount.click(); // Navigating to Account Page
						if(Utilities.explicitWaitClickable(driver, settingsPageV2.accountResetparZonePin, 10)) {
						settingsPageV2.accountResetparZonePin.click(); // Navigating to the Reset Parent Zone Pin
						}else BasePageV2.reportFail("Not found the 'Reset Parent Zone Pin Option' in Account Screen Page");
	//	Verify the click functionality of Reset Parent Zone PIN field in Account settings page	
						
		 		  if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetParentResetTitle, 10)) {
							test.log(LogStatus.PASS, "Verified the click functionality of Reset Parent Zone PIN field in Account settings page");
						//	BasePageV2.takeScreenshot();
							BasePageV2.smokeresults(" " , rowno763, "PASS");
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetParentSubTitle, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetParentResetPageBackBtn, 10)) {
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetParentResetNextBtn, 10)) {
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetPinforgotPin, 10)) {
											if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetPinDigit1, 10)) {
												if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetPinDigit2, 10)) {
													if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetPinDigit3, 10)) {
														if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetPinDigit4, 10)) {
															test.log(LogStatus.PASS, "Verified the UI of Reset Parent Zone PIN");
															//BasePageV2.takeScreenshot();
															BasePageV2.smokeresults(" " , rowno764, "PASS");	
														}else BasePageV2.reportFail("Reset pin Edit Text box 4 not found");
													}else BasePageV2.reportFail("Reset pin Edit Text box 3 not found");
												}else BasePageV2.reportFail("Reset pin Edit Text box 2 not found");
											}else BasePageV2.reportFail("Reset pin Edit Text box 1 not found");
										}else BasePageV2.reportFail("Reset pin 'Forgot Pin' option is not found");
									}else BasePageV2.reportFail("In Reset pin page 'NEXT' Button is not found");
								}else BasePageV2.reportFail("'Back button' is not found in Reset Pin Mina Page");
							}else test.log(LogStatus.FAIL, "Unable to found Reset Pin Mina page Sub title");
							
						}else BasePageV2.reportFail("unable to navigate to seperate dedicated page to reset the current PIN ");
						
	//   Verify the Back arrow functionality in Reset Parent Zone PIN Page
					  
					if(Utilities.explicitWaitClickable(driver, settingsPageV2.ResetParentResetPageBackBtn, 10)) {
							settingsPageV2.ResetParentResetPageBackBtn.click();
							test.log(LogStatus.INFO, "Tapped Reset Pin main page Back Button");
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipe(driver);
							if (Utilities.explicitWaitVisible(driver, settingsPageV2.accountResetparZonePin, 10)) {
								test.log(LogStatus.INFO, "Redirected to Account Reset Parent Zone Pin Main Page ");
								test.log(LogStatus.PASS, "Verified the Back arrow functionality in Reset Parent Zone PIN Page");
								//BasePageV2.takeScreenshot();
								BasePageV2.smokeresults(" " , rowno765, "PASS");	
							}else BasePageV2.reportFail("unable to Found Reset Parent Zone Pin in Account Main Page");
						}else test.log(LogStatus.FAIL, "Back Button is missed from Reset  Pin Mian Page");
						
						
//	
						
						
						
					
				}else BasePageV2.reportFail("Not Found Settings Icon for navigate to Settings Page ");
					
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
