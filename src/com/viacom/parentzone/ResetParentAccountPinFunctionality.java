package com.viacom.parentzone;

import java.time.Duration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;

import org.openqa.selenium.WebElement;
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

public class ResetParentAccountPinFunctionality extends BaseTestV2 {

	String testName = "ResetParentAccountPinFunctionality";

	@Test(dataProvider = "getData")
	public void restParentAccountPinFunctionality(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("RestParentAccountPinFunctionality"); 
		test.log(LogStatus.INFO, "Starting the test to Verify Rest Parent Account Pin Functionality: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls774 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno774 = xls774.getRowCount("Smoke_Results") + 1;
		System.out.println("Row number is "+ rowno774);
		xls.setCellData("Smoke_Results", "Testcase Name", rowno774,
				"774: Verify the functionality of resetting parent Pin when incorrect PIN is entered in current PIN field (Invalid)");
	
		Xls_Reader xls776 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno776 = xls776.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno776,
				"776: Verify the functionality of resetting parent PIN when user enters different set of PIN's in both New PIN & Confirm New PIN fields (Invalid)");
	
		Xls_Reader xls769 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno769 = xls769.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno769,
				"769: Verify the functionality of Next button in 'Enter Current PIN' screen by entering correct PIN (Valid)");
		
		Xls_Reader xls770 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno770 = xls770.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno770,
				"770: Verify the functionality of Next button in 'Enter Current PIN' screen by entering Incorrect PIN (Invalid)");	
		
		Xls_Reader xls771 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno771 = xls771.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno771,
				"771: Verify the functionality of Next button in 'Enter Current PIN' screen by keeping fields blank (Invalid)");
		
		
		Xls_Reader xls772 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno772 = xls772.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno772,
				"772: Verify the functionality of Next button in 'Enter new PIN' screen by entering Valid 4 digit PIN (Valid)");
		
		Xls_Reader xls773 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno773 = xls773.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno773,
				"773: Verify the functionality of Next button in 'Enter new PIN' screen by keeping fields blank (Invalid)");
		
		Xls_Reader xls775 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno775 = xls775.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno775,
				"775: Verify the functionality of Save button in 'Confirm new PIN' screen by entering Valid 4 digit PIN (Valid)");	
		
		
		Xls_Reader VK776 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int VK_776 = VK776.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_776,
				"776: Verify the UI of pop-up message post successfully Resetting the PIN");	
		
		Xls_Reader xls777 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno777 = xls777.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno777,
				"777: Verify the functionality of close icon in Pop-up message");
		
		
		Xls_Reader xls779 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno779 = xls779.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno779,
				"779: Verify the functionality of Next button in 'Confirm new PIN' screen by Keeping the fields blank (Invalid)");	
		
		
		Xls_Reader xls778 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno778 = xls778.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno778,
				"778: Verify the functionality of Save button in 'Confirm new PIN' screen by entering Incorrectt PIN (Invalid)");	
		
		

		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");

		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		
//		  try {
//		       homepagev2.login("resetpapf@gmail.com", "Ifocus@122");// mobile Number for this account 8904584999
//			   }catch(Exception e) {
//				   test.log(LogStatus.FAIL, "Login is failed");
//			   }
		
		
		homepagev2.logout();
//		homepagev2.signup();
		  
		homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		

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
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneAccount, 3)) {
				settingsPageV2.settingTextinParantZoneAccount.click(); // Navigating to Account Page
				}else BasePageV2.reportFail("Not able to Find 'Account' option in SETTINGS Page/ not able to click");
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.accountResetparZonePin, 10)) {
				settingsPageV2.accountResetparZonePin.click(); // Navigating to the Reset Parent Zone Pin
				}else BasePageV2.reportFail("Not found the 'Reset Parent Zone Pin Option' in Account Screen Page");
				
//			Verify the functionality of Next button in 'Enter Current PIN' screen by keeping fields blank (Invalid)	
				
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.resetPinNextBtn, 10)) {
					settingsPageV2.resetPinNextBtn.click();
					test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");
					
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetparZonePinContainerErorMSg, 10)) {
						test.log(LogStatus.PASS,"Verify the functionality of Next button in 'Enter Current PIN' screen by keeping fields blank (Invalid)");
						BasePageV2.smokeresults("", rowno771, "PASS");
					}else {
						test.log(LogStatus.FAIL, "Verify the functionality of Next button in 'Enter Current PIN' screen by keeping fields blank (Invalid)");
						BasePageV2.smokeresults("", rowno771, "FAIL");
					}
			
				}else {
					test.log(LogStatus.FAIL, "Not able to clicked 'NEXT' button in Reset Pin page / not found");
					
				}
				
				
				
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.reSetPinContainer, 10)) {
					System.out.println("Enter the Pin in Current Pin ");
					settingsPageV2.reSetPinContainer.sendKeys("5628");
					Thread.sleep(1000);
					
					if(Utilities.explicitWaitClickable(driver, settingsPageV2.resetPinNextBtn, 10)) {
						settingsPageV2.resetPinNextBtn.click();
						test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");
						test.log(LogStatus.PASS,"Verify the functionality of Next button in 'Enter Current PIN' screen by entering Incorrect PIN (Invalid)");
						BasePageV2.smokeresults("", rowno770, "PASS");
						
						
					}else {
						test.log(LogStatus.FAIL, "Not able to clicked 'NEXT' button in Reset Pin page / not found");
						test.log(LogStatus.FAIL, "Verify the functionality of Next button in 'Enter Current PIN' screen by entering Incorrect PIN (Invalid)");
						BasePageV2.smokeresults("", rowno770, "FAIL");
					}
		
					
				}else test.log(LogStatus.FAIL, "Pin Container not found for set the Pin ");
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetParentWrongPin, 10)) {
					test.log(LogStatus.PASS, "Verified the functionality of resetting parent Pin when incorrect PIN is entered in current PIN field (Invalid)");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("774: Verify the functionality of resetting parent Pin when incorrect PIN is entered in current PIN field (Invalid)",
							rowno774, "PASS");
				}else BasePageV2.reportFail("Not Displayed InCorrect Pin Error Massage");
				
			
		}else BasePageV2.reportFail("'Settings Icon' not found for tapping on it to naviagte to Parent Zone");
		
		
		//Navigating to Reset Pin page to set InCorrect Pin in 'Confirm New Pin' Filed	
//		   for(int i=0; i < 3; i++) {
//			    driver.navigate().back();
//			   if(Utilities.explicitWaitVisible(driver, settingsPageV2.accountBackBtn, 10)) {
//				   break;
//			   }
//			   
//		   }
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		  
//set of incorrect  PIN's in both New PIN & Confirm New PIN fields (Invalid)
	 // set the pin "1111" default
		
//		******************
		    //put App in background and bring back
		driver.runAppInBackground(Duration.ofSeconds(3));
		 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		 driver.currentActivity();
		
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.accountResetparZonePin, 10)) {
			settingsPageV2.accountResetparZonePin.click(); // Navigating to the Reset Parent Zone Pin
			}else BasePageV2.reportFail("Not found the 'Reset Parent Zone Pin Option' in Account Screen Page");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.reSetPinContainer, 10)) {
				System.out.println("Enter the Pin in Current Pin filed");
//				*********************************
		
				
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.reSetPinContainer, 10)) {
					System.out.println("Enter the Pin in Current Pin ");
					settingsPageV2.reSetPinContainer.sendKeys("1111");
					Thread.sleep(1000);
					
					if(Utilities.explicitWaitClickable(driver, settingsPageV2.resetPinNextBtn, 10)) {
						settingsPageV2.resetPinNextBtn.click();
						test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");
						test.log(LogStatus.PASS,"Verify the functionality of Next button in 'Enter Current PIN' screen by entering correct PIN (Valid)");
						BasePageV2.smokeresults("",rowno769, "PASS");
						
					}else {
						test.log(LogStatus.FAIL, "Not able to clicked 'NEXT' button in Reset Pin page / not found");
						test.log(LogStatus.FAIL, "Verify the functionality of Next button in 'Enter Current PIN' screen by entering correct PIN (Valid)");
						BasePageV2.smokeresults("",rowno769, "FAIL");
					}
					
					
				}else if (true){
				
				for(int i=1 ; i < 4; i++) {
					WebElement pinContain = driver.findElementByXPath("//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/pin_digit_"+ i +"']");
					System.out.println("Finding the Pin Container in Reset PIn");
					System.out.println(pinContain);
					pinContain.clear();
					pinContain.click();
					pinContain.sendKeys("1");
				  }
				
					
				
			    }else BasePageV2.reportFail("Reset Container pin failed/ not able to entered pin ");
				
			
				
//				******************************************
//			settingsPageV2.reSetPinContainer.sendKeys("1111"); // sending the Incorrect Pin 'Hard coded'
			Thread.sleep(1000);
			}else test.log(LogStatus.FAIL, "Pin Container not found for set the Pin ");
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.newPinResetParent, 10)) {
				
				
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.resetPinNextBtn, 10)) {
					settingsPageV2.resetPinNextBtn.click();
					test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");
					
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetparZonePinContainerErorMSg, 10)) {
						test.log(LogStatus.PASS,"Verify the functionality of Next button in 'Enter new PIN' screen by keeping fields blank (Invalid)");
						BasePageV2.smokeresults("", rowno773, "PASS");
					}else {
						test.log(LogStatus.FAIL, "Verify the functionality of Next button in 'Enter new PIN' screen by keeping fields blank (Invalid)");
						BasePageV2.smokeresults("",rowno773,"FAIL");
					}
					
					
				}else {
					test.log(LogStatus.FAIL, "Not able to clicked 'NEXT' button in Reset Pin page / not found");
				}
				
				
				
				settingsPageV2.reSetPinContainer.sendKeys("1234"); // passing the New Pin number in New Pin Fields
				Thread.sleep(1000);
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.resetPinNextBtn, 10)) {
					settingsPageV2.resetPinNextBtn.click();
					test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");
				}else {
					test.log(LogStatus.FAIL, "Not able to clicked 'NEXT' button in Reset Pin page / not found");
				}
				
			}else BasePageV2.reportFail("New Pin Filed not found in reset Pin page / not able to navigate to New Pin reset Page");
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.confirmNewPinResetParent, 10)) {
				test.log(LogStatus.PASS,"Verify the functionality of Next button in 'Enter new PIN' screen by entering Valid 4 digit PIN (Valid)");
				BasePageV2.smokeresults("", rowno772, "PASS");
		
				settingsPageV2.reSetPinContainer.sendKeys("9652"); //sending Incorrect number for checking the Error Massage
				Thread.sleep(1000);
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.confimNewPinResetSaveBtn, 10)) {
					settingsPageV2.confimNewPinResetSaveBtn.click();
					test.log(LogStatus.INFO, "Clicked SAVE button in Confirm Reset Pin page");
		
				}else {
					test.log(LogStatus.FAIL, "Not able to clicked 'SAVE' button in Confirm Reset Pin page / not found");
				}
				
			}else {
				test.log(LogStatus.FAIL,"Verify the functionality of Next button in 'Enter new PIN' screen by entering Valid 4 digit PIN (Valid)");
				BasePageV2.smokeresults("", rowno772, "FAIL");
				BasePageV2.reportFail("Confirm New pin page not Found");
			}
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetParentWrongPin, 10)) {
				test.log(LogStatus.PASS, "Verified the functionality of resetting parent PIN when user enters different set of PIN's in both New PIN & Confirm New PIN fields (Invalid)");
			//	BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("776: Verify the functionality of resetting parent PIN when user enters different set of PIN's in both New PIN & Confirm New PIN fields (Invalid)",
						rowno776, "PASS");
			
				test.log(LogStatus.PASS,"Verify the functionality of Save button in 'Confirm new PIN' screen by entering Incorrectt PIN (Invalid)");
				BasePageV2.smokeresults("", rowno778, "PASS");
		
				
			}else BasePageV2.reportFail("Not Displayed InCorrect Pin Error");
			
			  
			
			
////Verify the functionality of Save button in 'Confirm new PIN' screen by entering Valid 4 digit PIN (Valid)")
			   
			settingsPageV2.reSetPinContainer.clear();
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.confimNewPinResetSaveBtn, 10)) {
				settingsPageV2.confimNewPinResetSaveBtn.click();
				test.log(LogStatus.INFO, "Clicked SAVE button in Confirm Reset Pin page");
	
			}else {
				test.log(LogStatus.FAIL, "Not able to clicked 'SAVE' button in Confirm Reset Pin page / not found");
			}
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.ResetparZonePinContainerErorMSg, 10)) {
				test.log(LogStatus.PASS,"Verify the functionality of Next button in 'Confirm new PIN' screen by Keeping the fields blank (Invalid)");
				BasePageV2.smokeresults("", rowno779, "PASS");
			}else {
				test.log(LogStatus.FAIL, "Verify the functionality of Next button in 'Confirm new PIN' screen by Keeping the fields blank (Invalid)");
			}
			

			   settingsPageV2.reSetPinContainer.sendKeys("1234");
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.confimNewPinResetSaveBtn, 10)) {
					settingsPageV2.confimNewPinResetSaveBtn.click();
					test.log(LogStatus.INFO, "Clicked SAVE button in Confirm Reset Pin page");
		
				}else {
					test.log(LogStatus.FAIL, "Not able to clicked 'SAVE' button in Confirm Reset Pin page / not found");
				}
			   
			   if(Utilities.explicitWaitVisible(driver, settingsPageV2.pinResetSuccessMsg, 10)) {
					test.log(LogStatus.PASS,"Verify the functionality of Save button in 'Confirm new PIN' screen by entering Valid 4 digit PIN (Valid)");
					BasePageV2.smokeresults("", rowno775, "PASS");
				}else {
					test.log(LogStatus.FAIL,"Verify the functionality of Save button in 'Confirm new PIN' screen by entering Valid 4 digit PIN (Valid)");
					BasePageV2.smokeresults("", rowno775, "FAIL");
				}   
			   
			   if(Utilities.explicitWaitVisible(driver, settingsPageV2.pinResetSuccessMsg, 10)) {
				   if(Utilities.explicitWaitVisible(driver, settingsPageV2.pinResetSuccessBodyMsg, 10)) {
					   if(Utilities.explicitWaitVisible(driver, settingsPageV2.pinResetSuccessCancelBtn, 10)) {
						     
						   test.log(LogStatus.PASS,"Verify the UI of pop-up message post successfully Resetting the PIN");
						   BasePageV2.smokeresults("", VK_776, "PASS");
						   if(Utilities.explicitWaitClickable(driver, settingsPageV2.pinResetSuccessCancelBtn, 10)) {
							   settingsPageV2.pinResetSuccessCancelBtn.click();
							   test.log(LogStatus.INFO, "Clicked Cancel button on Reset Pin success pop up");
							   test.log(LogStatus.PASS,"Verify the functionality of close icon in Pop-up message");
							   BasePageV2.smokeresults("", VK_776, "PASS");
							   
							   //Log out 
							   driver.pressKeyCode(AndroidKeyCode.BACK);
                               try {
								settingsPageV2.settingsLogOut.click();
								settingsPageV2.settingsLogOutYesBtn.click();
							} catch (Exception e) {
								e.printStackTrace();
							}
							   
						   }else {
							   test.log(LogStatus.PASS,"Verify the functionality of close icon in Pop-up message");
							   BasePageV2.smokeresults("", VK_776, "FAIL");
						   }
						   
						   
					   }else test.log(LogStatus.FAIL, "Not Found 'PIN RESET' Title on Pin Reset Success POp up'");
				   }else test.log(LogStatus.FAIL, "Not Found 'PIN RESET' Body Message on Pin Reset Success POp up'");
			   }else test.log(LogStatus.FAIL, "Not Found 'PIN RESET' Cancel button on Pin Reset Success POp up'");
			   
			
			   
			  
             
	
	
  }
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	
	
}
