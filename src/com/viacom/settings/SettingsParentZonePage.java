package com.viacom.settings;

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
import io.appium.java_client.android.connection.ConnectionState;

public class SettingsParentZonePage extends BaseTestV2 {
	
String testName = "SettingsParentZonePage";
    
	String delProList = "";
	@Test(dataProvider = "getData")
	public void settingsParentZonePage(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsParentZonePage");
		test.log(LogStatus.INFO, "Starting the test to Settings Parent Zone Page: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int rowno657 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno657, "Verify the click functionality of Parent Zone button:");
	
		int rowno658 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno658, "Verify the UI of Parent Zone PIN verification for settings Page");
		
		int rowno660 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno660, "Verify the functionality by tapping on Forgot PIN link:");
		
		int VK_660 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_660, "Verify the Submit button functionality by entering incorrect PIN:");
		
		
		int rowno663 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno663, "Verify Submit button functionality for valid data:");
		
		/*
		 * 
		 * have to add 'Submit' Button in below Test cases
		 * 
		 */
		
		
		
		
		// Launching the Voot-kids App
				launchApp();
				test.log(LogStatus.INFO, "Application launched successfully");

				
				SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
				HomePageV2 homepagev2 = new HomePageV2(driver, test);
				BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		
		
				// navigating to settings 'Profiles' page
//		        try {
//				 HomePageV2.login("parentz@gmail.com", "Ifocus@122");
//		        }catch(Exception e) {
//		        	test.log(LogStatus.FAIL, "login Failed");
//		        }
//		        
		         
				 homepagev2.login(data.get("Email"),data.get("Password"));
				
				

				try {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

			} catch (Exception e) {
				BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
			}
				
				
//				Verify the click functionality of Parent Zone button:
				
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.parentZoneBtn, 5)) {
					settingsPageV2.parentZoneBtn.click();
					test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");
					
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.ParentZoneTile, 5)) {
						test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
						test.log(LogStatus.PASS,"Verify the click functionality of Parent Zone button:");
						BasePageV2.smokeresults("", rowno657, "PASS");
					}else {
						BasePageV2.smokeresults("", rowno657, "FAIL");
						BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
					}

				}else BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");
				
				
				
//	    Verify the UI of Parent Zone PIN verification for settings Page		
				
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.ParentZoneTile, 5)) {
					if(Utilities.explicitWaitClickable(driver, settingsPageV2.bckBtnParentZone, 5)) {
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneEnterPinToacess, 5)) {
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.forGotPinText, 5)) {
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 5)) {
									test.log(LogStatus.PASS,"Verify the UI of Parent Zone PIN verification for settings Page	");
									BasePageV2.smokeresults("", rowno658, "PASS");
								}else test.log(LogStatus.FAIL, "'Pin Container' is not found in Parent Zone page");
							}else test.log(LogStatus.FAIL, "'Forgot Pin' Link is not found in Parent Zone page");
						}else test.log(LogStatus.FAIL, "'Sub Title' is not found in Parent Zone page");
					}else test.log(LogStatus.FAIL, "'Back Button' is not found in Parent Zone page");
				}else test.log(LogStatus.FAIL, "'ParentZone Title' is not found in Parent Zone page");
				
				
		    	   
//		Verify the functionality by tapping on Forgot PIN link:       
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.forGotPinText, 5)) {
					settingsPageV2.forGotPinText.click();
					test.log(LogStatus.INFO, "Clicked Forgot Pin Link in Parent Zone page");
					
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.forgotPinPopUpTitle, 5)) {
						  test.log(LogStatus.PASS,"Verify the functionality by tapping on Forgot PIN link:");
						  BasePageV2.smokeresults("", rowno660, "PASS");
					}else {
						test.log(LogStatus.FAIL, "Not able to found Forgot pin pop up(Page)");
						BasePageV2.smokeresults("", rowno660, "FAIL");
					}
					
				}else test.log(LogStatus.FAIL, "'Forgot Pin' Link is not found in Parent Zone page");
				
				//click on No button in forgotPIn pop up
		          try {
					settingsPageV2.noBtnforgotPin.click();
					test.log(LogStatus.INFO, "clicked 'NO' Button on Forgot pin POP up page");
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Not able to click 'NO' Button on Forgot Pin pop up page");
				}
		          
//		       Verify Submit button functionality for valid data:
		          
		          try {
					   settingsPageV2.parentPinContainer.sendKeys("1111");
					   test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
				} catch (Exception e) {
					   test.log(LogStatus.FAIL, "Not able to entered PIn '1111' in parent zone page / not found pin container");
				}
		          
		        if(Utilities.explicitWaitVisible(driver, settingsPageV2.ParentZonePageTile, 10)) {
		        	test.log(LogStatus.PASS,"Verify Submit button functionality for valid data:");
					BasePageV2.smokeresults("", rowno663, "PASS");
		        }else {
		        	test.log(LogStatus.FAIL, "Not able to navigated to PARENT ZONE Main page");
		        	BasePageV2.smokeresults("", rowno663, "FAIL");
		        }
		        
		        driver.pressKeyCode(AndroidKeyCode.BACK);
		        if(Utilities.explicitWaitClickable(driver, settingsPageV2.parentZoneBtn, 5)) {
					settingsPageV2.parentZoneBtn.click();
					test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");
		        }else BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");
		        
		        
	
	//  Verify the Submit button functionality by entering incorrect PIN:
		        
			        try {
	                      //Entered Wrong Pin		        	
						   settingsPageV2.parentPinContainer.sendKeys("8960");
						   test.log(LogStatus.INFO, "Entered Wrong Pin '8960' in Four white Blocks of Parent Zone page ");
					} catch (Exception e) {
						   test.log(LogStatus.FAIL, "Not able to entered Wrong PIn '8960' in parent zone page / not found pin container");
					}
			        
			        if(Utilities.explicitWaitVisible(driver, settingsPageV2.forgotPinPopUpTitle, 10)) {
			        	test.log(LogStatus.PASS, "Verify the Submit button functionality by entering incorrect PIN:");
			        	BasePageV2.smokeresults("", VK_660, "PASS");
			        }else {
			        	test.log(LogStatus.FAIL, "Verify the Submit button functionality by entering incorrect PIN:");
			        	BasePageV2.takeScreenshot();
			        	BasePageV2.smokeresults("", VK_660, "FAIL");
			        }
			        
			        try {
			        	 settingsPageV2.noBtnforgotPin.click();
			        	 test.log(LogStatus.INFO, "Clikced cancel button");
					} catch (Exception e) {
						 BasePageV2.reportFail("Not able to Click cancel button on Forgot Pin pop up");
					}
			       
		        
		        
		        
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
