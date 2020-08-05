package com.viacom.settings;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
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

import bsh.util.Util;
import io.appium.java_client.android.Activity;
import okhttp3.internal.http2.Settings;

public class SettingsMoblieNumberValidation extends BaseTestV2{
	
	String testName = "SettingsMoblieNumberValidation";
	
	
	String newEmail = "";
    String oldEmail="";
    String oldMobileNum = "";
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsMoblieNumberValidation");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		
		Xls_Reader xls724 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno724 = xls724.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno724,
				"724: Verify the functionality of SAVE button in Edit email page ");
		
		Xls_Reader xls725 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno725 = xls725.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno725,
				"725: Verify the functionality of SAVE button in Edit email page is user enters invalid email id ");
		
		Xls_Reader xls726 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno726 = xls726.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno726,
				"726: Verify the functionality of SAVE button in Edit email page without entering email id ");
		
		Xls_Reader xls727 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno727 = xls727.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno727,
				"727: Verify the functionality of SAVE button in Edit email page if user enters same email id ");
		
		Xls_Reader xls732 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno732 = xls732.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno732,
				"732: Verify the functionality of Cross mark icon the pop-up notification in Email Added pop-up ");
		
		Xls_Reader xls740 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno740 = xls740.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno740,
				"740: Verify the functionality of SAVE button in Edit Mobile No. page if user enters the already registered Mobile No. ");
		
		Xls_Reader xls741 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno741 = xls741.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno741,
				"741: Verify the functionality of SAVE button in Edit Mobile No. page if user enters invalid Mobile No. ");
		
		Xls_Reader xls742 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno742 = xls742.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno742,
				"742: Verify the functionality of SAVE button in Edit Mobile No. page without entering Mobile No. ");
		/*
		 * Here Pin container is 1111
		 */
		
	
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		//Login if not logged in to Voot-Kids Application in the device
		
//		try {
//			 HomePageV2.login("stmnv@gmail.com", "Ifocus@122"); 
//	        }catch(Exception e) {
//	        	test.log(LogStatus.FAIL, "login Failed");
//	        }   // Mobile Number for this Account is 8904584555
//		
		homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		
		
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
					}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
				}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
				
				// putting App in background
				
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();
		
	// Verify the functionality of SAVE button in Edit email page
			
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
				settingsPageV2.settingsIcon.click();
				
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneAccount, 10)) {
						settingsPageV2.settingTextinParantZoneAccount.click();
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.accountEmail, 10)) {
        						settingsPageV2.accountEmail.click();   //clicking on Email option present in ACcount Main Page
						}else BasePageV2.reportFail("Email option is not fornd in Account Page to naviagte to Email Main Page ");
							oldEmail= settingsPageV2.addMailEditText.getText();      // copying to string old Email id 
							System.out.println("The old Email is from Edit Text " + oldEmail);    
							settingsPageV2.addMailEditText.clear();
							Thread.sleep(1000);
							
							String str = Utilities.generateEmailid();
							test.log(LogStatus.INFO, "The New Mail String is : " + str);
							
							
							// generating the new Email ID 
							String newEmailID = Utilities.generateEmailid();
							settingsPageV2.addMailEditText.sendKeys(newEmailID); // sending new Email to Email Edit Text
							
							try {
								driver.hideKeyboard();
							} catch (Exception e) {
								e.printStackTrace();
							}
						
							newEmail = settingsPageV2.addMailEditText.getText();
							test.log(LogStatus.INFO,"The New Email is from Edit Text " + newEmail);   // getting the New Email ID 
							
							  if (Utilities.explicitWaitClickable(driver, settingsPageV2.addMailSaveBtn, 5)) {
								  settingsPageV2.addMailSaveBtn.click(); // After Entering the Email Clicking the SAVE Button
									test.log(LogStatus.INFO, "Succussfully clicked Save button in Email Update page");
						     	}else test.log(LogStatus.FAIL, "Unable to click Save Button in Update Email page / not found ");
							  
		
							
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailSucssEmailAdd, 10)) {
	//Verify the functionality of Cross mark icon the pop-up notification in Email Added pop-up
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailSucssEmailAddCnlBtn, 10)) {
								test.log(LogStatus.PASS, "Verify the functionality of Cross mark icon the pop-up notification in Email Added pop-up");
							//	BasePageV2.takeScreenshot();
								if(Utilities.explicitWaitClickable(driver, settingsPageV2.addMailSucssEmailAddCnlBtn, 10))
								settingsPageV2.addMailSucssEmailAddCnlBtn.click();
								else test.log(LogStatus.FAIL, "CANCEL button is not found on Email Added Succuss Pop up");
								Utilities.verticalSwipe(driver);
								Utilities.verticalSwipeDown(driver);
								if(Utilities.explicitWaitClickable(driver, settingsPageV2.accountEmail, 10)) {
								settingsPageV2.accountEmail.click();
								}else BasePageV2.reportFail("Email text not found in Account Mian page");
								String newEmailValidate = settingsPageV2.addMailEditText.getText();
								//String newEmailValidate = driver.findElementByXPath("//android.view.ViewGroup[@index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_desc']").getText();
								System.out.println("New Mail is updated in Account Page " + newEmailValidate );
								if(newEmailValidate.equalsIgnoreCase(newEmail)) {
//								WebElement newMailVerifying = driver.findElementByXPath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_desc' and @text='sureshkk@gmail.in']"); // Verifying the New Email ID is Existing in the Account Page
								BasePageV2.smokeresults(" ",rowno732, "PASS");
								test.log(LogStatus.PASS, "Verify the functionality of SAVE button in Edit email page");
								//BasePageV2.takeScreenshot();
								BasePageV2.smokeresults(" ",rowno724, "PASS");
								}else BasePageV2.reportFail("updated new Email is not found in ACCOUNT Email id ");
								}else BasePageV2.reportFail("'Cross Button' not found on Sucuss Pop up Email ID  ");
							}else BasePageV2.reportFail("'Email Added' Succussfull Pop us Not Found ");
							
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountEmail, 10)) {
							settingsPageV2.accountEmail.click();
							}else BasePageV2.reportFail("Email Option is not Finding in Settings Account Main Screen");
							settingsPageV2.addMailEditText.clear();
							settingsPageV2.addMailEditText.sendKeys(oldEmail);
							settingsPageV2.addMailSaveBtn.click();
							settingsPageV2.addMailSucssEmailAddCnlBtn.click();
						
	// Verify the functionality of SAVE button in Edit email page is user enters invalid email id	
							
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipeDown(driver);
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountEmail, 10)) {
								settingsPageV2.accountEmail.click();
								settingsPageV2.addMailEditText.sendKeys("sureshkk@gmail");   // entering Invalid Email Id Format
								settingsPageV2.addMailSaveBtn.click();
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailWrongMsg, 10)) {
									test.log(LogStatus.PASS, "Verified the functionality of SAVE button in Edit email page is user enters invalid email id");
								//	BasePageV2.takeScreenshot();
									BasePageV2.smokeresults(" ",rowno725, "PASS");
								}else BasePageV2.reportFail("Invalid Emial ID format Error Massege does not displayed");
							}else BasePageV2.reportFail("Email Option is not found in Account Main Screen");
							
  // Verify the functionality of SAVE button in Edit email page without entering email id	
							driver.navigate().back();
							settingsPageV2.addMailBackbtn.click();    // Clicking the add Mail Back Button 
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipeDown(driver);
							settingsPageV2.accountEmail.click();
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailEditText, 10)) {
								settingsPageV2.addMailEditText.clear();    // Clear the Email id in Email Edit Text Box
								settingsPageV2.addMailSaveBtn.click();    // Click on Save button
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailWrongMsg, 10)) {
									test.log(LogStatus.PASS, "Verify the functionality of SAVE button in Edit email page without entering email id");
									//BasePageV2.takeScreenshot();
									BasePageV2.smokeresults(" ",rowno726, "PASS");
									
								}else BasePageV2.reportFail("Enter Email Blank Massege does not displayed");
							}else BasePageV2.reportFail("Email Edit text box not found in UPDATE EMAIL Page");
							
	//Verify the functionality of SAVE button in Edit email page if user enters same email id
							driver.navigate().back();
							settingsPageV2.addMailBackbtn.click();
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipeDown(driver);
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountEmail, 10)) {
								settingsPageV2.accountEmail.click();
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailEditText, 10)) {
								settingsPageV2.addMailEditText.clear();
								settingsPageV2.addMailEditText.sendKeys(newEmail);
								}else BasePageV2.reportFail("Add Email page Email Edit Text box not found ");
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailSaveBtn, 10)) {
								settingsPageV2.addMailSaveBtn.click();
								}else BasePageV2.reportFail("Save Button is not found in Add Email Page");
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMailSucssEmailAddCnlBtn, 10)) {
									settingsPageV2.addMailSucssEmailAddCnlBtn.click();
									test.log(LogStatus.PASS, "Verify the functionality of SAVE button in Edit email page if user enters same email id");
									//BasePageV2.takeScreenshot();
									BasePageV2.smokeresults(" ",rowno727, "PASS");
									
								}else BasePageV2.reportFail("Succuss Email Add Pop Cross Button is not found ");
								
							}else BasePageV2.reportFail("Account Page Email option is not Found");
							
							
							
		//Verify the functionality of SAVE button in Edit Mobile No. page if user enters the already registered Mobile No.
							
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipeDown(driver);
							
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountMobile, 10)) {
								settingsPageV2.accountMobile.click();
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMobileNumEditText, 10)) {
									 oldMobileNum = settingsPageV2.addMobileNumEditText.getText().trim();     // Copying the current Mobile Number in a String
									 settingsPageV2.addMobileNumEditText.clear();   //clearing the current Mobile Number in mobile Edit Box
									 Thread.sleep(1000);
									 settingsPageV2.addMobileNumEditText.sendKeys(oldMobileNum);
									 settingsPageV2.addMobileNumSaveBtn.click();
									 Thread.sleep(1000);
									 if(Utilities.explicitWaitVisible(driver, settingsPageV2.errorMsgRegistedMobilenum, 10)) {
										 test.log(LogStatus.PASS, "Verify the functionality of SAVE button in Edit Mobile No. page if user enters the already registered Mobile No.");
										//	BasePageV2.takeScreenshot();
											BasePageV2.smokeresults(" ",rowno740, "PASS");
											
									 }else {
										 test.log(LogStatus.FAIL,"Does not Found added mobile number succuss message");
										 settingsPageV2.addMobileNumBackBtn.click();
									 }
//									 if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMobileNumSuccesCrossBtn, 10)) {
//									 settingsPageV2.addMobileNumSuccesCrossBtn.click();
//									 }
								}else BasePageV2.reportFail("Mobile Edit Text Box is not found in UpdateMobile Main Screen");
							}else BasePageV2.reportFail("Mobile Text option is not found in Account Main Screen");
							
					// Verify the functionality of SAVE button in Edit Mobile No. page if user enters invalid Mobile No.
							
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipeDown(driver);
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountMobile, 10)) {
								settingsPageV2.accountMobile.click();
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMobileNumEditText, 10)) {
									settingsPageV2.addMobileNumEditText.clear();             //clearing the current Mobile Number in mobile Edit Box
									settingsPageV2.addMobileNumEditText.sendKeys("1234567890");      // sending invalid mobile Number to mobile Edit text box
									settingsPageV2.addMobileNumSaveBtn.click();
								    Thread.sleep(1000);
							        if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMobileNumInvalisErrorMsg, 10)) {
							        	test.log(LogStatus.PASS, "Verify the functionality of SAVE button in Edit Mobile No. page if user enters invalid Mobile No.");
									//	BasePageV2.takeScreenshot();
										BasePageV2.smokeresults(" ",rowno741, "PASS");
							        	
							        }else {
							        	test.log(LogStatus.FAIL,"Does not displayed Invalid Mobile number added Message");
							        	settingsPageV2.addMobileNumBackBtn.click();
							        }
							
							}else BasePageV2.reportFail("Mobile Edit Text Box is not found in UpdateMobile Main Screen");
							}else BasePageV2.reportFail("Mobile Text option is not found in Account Main Screen");
							
						}else BasePageV2.reportFail("Account option is not Found in Settings Page to naviagte to Account main Page");
					
				} else BasePageV2.reportFail("Setting Icon not Found in PARENT ZONE page ");
		
		//Verify the functionality of SAVE button in Edit Mobile No. page without entering Mobile No.
			
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipeDown(driver);
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountMobile, 10)) {
				settingsPageV2.accountMobile.click();
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.addMobileNumEditText, 10)) {
					settingsPageV2.addMobileNumEditText.clear();       //clearing the current Mobile Number in mobile Edit Box
					try {
						settingsPageV2.addMobileNumSaveBtn.click();
						test.log(LogStatus.INFO, "Succussfully clicked Save Button in Upadte Mobilr Num Page");
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Unable to click on save button / not found save button in Update mobile Num page");
					}
					
					
					if(Utilities.explicitWaitClickable(driver, settingsPageV2.addMobileNumEditEmptyErrorMsg, 10)) {
						test.log(LogStatus.PASS, "Verified the functionality of SAVE button in Edit Mobile No. page without entering Mobile No.");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults(" ",rowno742, "PASS");
					}else BasePageV2.reportFail("Unable to Found Mobile Edit Text box Empty Error Message");
				}else BasePageV2.reportFail("Mobile No. Edit text box not found in UPDATE MOBILE Main Page");
			}else BasePageV2.reportFail("Mobile Text option is not Found in Settings Account Main Page");
		

}
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	
	
}
