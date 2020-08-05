package com.viacom.settings;

import java.time.Duration;
import java.util.HashSet;
import java.util.Hashtable;

import org.glassfish.grizzly.compression.lzma.impl.Base;
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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class VerifySettingsRateUsFeature extends BaseTestV2 {

	String testName = "VerifySettingsRateUsFeature";

	
	
	String rateUsText = "";
	
	
	@Test(dataProvider = "getData")
	public void verifySettingsRateUsFeature(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifySettingsRateUsFeature");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings RateUs Feature: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls879 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno879 = xls879.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno879,
				"879: Verify the functionality of 'Skip For Now' button / outside the pop-up message");
		
		Xls_Reader xls882 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno882 = xls882.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno882,
				"882: Verify the functionality by tapping on 'Rate us ' button in the pop-up");
		
		
		Xls_Reader xls887 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno887 = xls887.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno887,
				"887: Verify the functionality of 'Skip for Now' button / outside the rate us pop-up message");
		
		Xls_Reader xls890 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno890 = xls890.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno890,
				"890: Verify the functionality of 'Skip for Now' button /outside the feedback pop-up message");
		
		Xls_Reader xls870 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno870 = xls870.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno870,
				"870: Validate Help & Support link functionality");
		
		Xls_Reader xls872 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno872 = xls872.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno872,
				"872: Verify the UI of Help & Support page");     
		
		Xls_Reader xls874 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno874 = xls874.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno874,
				"874: Validate the functionality for FAQ's link");  
		
		Xls_Reader xls875 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno875 = xls875.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno875,
				"875: Validate the functionality for Terms & Conditions link");  
		
		Xls_Reader xls876 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno876 = xls876.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno876,
				"876: Validate the functionality for Privacy Policy link");  
		
		
		Xls_Reader xls894 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno894 = xls894.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno894,
				"894: Verify the functionality of support contact number button");  
		
		Xls_Reader xls895 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno895 = xls895.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno895,
				"895: Verify the functionality of support email id button");  
		
		Xls_Reader xls896 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno896 = xls896.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno896,
				"896: Validate the prefilled data post tapping on support email id button");  
		
		
		

		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		HashSet<String> set = new HashSet<String>();
		String end1 = "//android.widget.TextView[@text='Create New Profile']";

		// navigating to settings 'Profiles' page 

//				   HomePageV2.signup();

		

//		homepagev2.login(data.get("Email"),data.get("Password"));
		
			try {
				if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

				}
			} catch (Exception e) {
				BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
			}
			
			// putting App in background
			 driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
			
			
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
				settingsPageV2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
					Thread.sleep(1000);
					settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
			
			
			
			// putting App in background
			 driver.runAppInBackground(Duration.ofSeconds(5));
			 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
			 driver.currentActivity();
			
	// Navigate to RATE US Feature to tap Skip For Now 	
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
			settingsPageV2.settingsIcon.click();
		     test.log(LogStatus.INFO, "clicked on Settings Icon");
				if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZone, 10)) {
				    rateUsText = "//android.widget.TextView[@text='Rate Us']";
					Utilities.verticalSwipe(driver, rateUsText);
					Utilities.verticalSwipe(driver);
					
					settingsPageV2.putBackGroundApp3();
					
					try {
						settingsPageV2.settingsRateUS.click();
					} catch (Exception e) {
						BasePageV2.reportFail("Not able to click 'RATE US' option in Settings Page");
					}
					
					Thread.sleep(1000);
					try {
						settingsPageV2.rateUsPopSkipForNow.click();
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Unable to click 'Skip For Now' on Rate us pop up / not found");
					}
					
					
					// putting app to background for 3 seconds
					driver.runAppInBackground(Duration.ofSeconds(3));
					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					 driver.currentActivity();
						 
						
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZone, 10)) 
					{
						
						test.log(LogStatus.PASS,"Verified the functionality of 'Skip For Now' button / outside the pop-up message");
						BasePageV2.smokeresults("879: Verify the functionality of 'Skip For Now' button / outside the pop-up message",
								rowno879, "PASS");
					} else {
						test.log(LogStatus.FAIL,"functionality of 'Skip For Now' button / outside the pop-up message Not Working");
						BasePageV2.takeScreenshot();
					}
				}else BasePageV2.reportFail("Not able to navigate to 'SETTINGS' Page ");
					
			
		
			//Tapping 'Yes'button next screen tap Skip For Now 
			
			
		    rateUsText = "//android.widget.TextView[@text='Rate Us']";
			Utilities.verticalSwipe(driver, rateUsText);
             
			driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
			 Utilities.verticalSwipe(driver);
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsRateUS, 2)) {
			settingsPageV2.settingsRateUS.click();
			}else test.log(LogStatus.FAIL, "unable to click rate us Option in SETTINGS Main page / not found");
			Thread.sleep(1000);
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsPopYesText, 4)) {
			settingsPageV2.rateUsPopYesText.click();
			}else test.log(LogStatus.FAIL, "Not able to click on 'YES' button on Rate us pop up / not found");
			BasePageV2.takeScreenshot();
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsPopSkipForNow, 4)) {
				settingsPageV2.rateUsPopSkipForNow.click();
			}else test.log(LogStatus.FAIL, "Not able to click on 'SKIP FOR NOW' button on Rate us pop up / not found");
			
			
			Utilities.verticalSwipeDown(driver);
			Utilities.verticalSwipe(driver);
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
				
				test.log(LogStatus.PASS,"Verified the functionality of 'Skip for Now' button / outside the rate us pop-up message");
				BasePageV2.smokeresults("887: Verify the functionality of 'Skip for Now' button / outside the rate us pop-up message",
						rowno887, "PASS");
			}else BasePageV2.reportFail("Not worked functionality of 'Skip for Now' button / outside the rate us pop-up message");
			
			//Tapping 'No'button next screen tap Skip For Now 

		    rateUsText = "//android.widget.TextView[@text='Rate Us']";
			Utilities.verticalSwipe(driver, rateUsText);
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsRateUS, 2)) {
				settingsPageV2.settingsRateUS.click();
			}else test.log(LogStatus.FAIL, "unable to click rate us Option in SETTINGS Main page / not found");
			
			Thread.sleep(1000);
			
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsPopNoText, 4)) {
				settingsPageV2.rateUsPopNoText.click();
			}else test.log(LogStatus.FAIL, "Not able to click on 'YES' button on Rate us pop up / not found");
			
			
			BasePageV2.takeScreenshot();
			
			try {
				settingsPageV2.rateUsPopSkipForNow.click();
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Unable to click 'Skip For Now' on Rate us pop up / not found");
			}
		
		     //sending app to backgrouns and get back	
			settingsPageV2.putBackGroundApp();
			
			if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 10)) {
				test.log(LogStatus.PASS,
						"Verify the functionality of 'Skip for Now' button /outside the feedback pop-up message");
				//BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("890: Verify the functionality of 'Skip for Now' button /outside the feedback pop-up message",
						rowno890, "PASS");
			}else BasePageV2.reportFail("Not worked the functionality of 'Skip for Now' button /outside the feedback pop-up message");
			
			
			 rateUsText = "//android.widget.TextView[@text='Rate Us']";
				Utilities.verticalSwipe(driver, rateUsText);
			
			//Tapping 'Yes' Button on RateUS Pop
			   
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsRateUS, 2)) {
				settingsPageV2.settingsRateUS.click();
			}else test.log(LogStatus.FAIL, "unable to click rate us Option in SETTINGS Main page / not found");
			
			if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsPopYesText, 4)) {
				settingsPageV2.rateUsPopYesText.click();
			}else test.log(LogStatus.FAIL, "Not able to click on 'YES' button on Rate us pop up / not found");
			 
			    Thread.sleep(1000);
			    try {
			    	 settingsPageV2.rateUsRATEUS.click();
				} catch (Exception e) {
					BasePageV2.reportFail("'Rate Us' Button is not found on Thats great pop up");
				}
			   
			    
			    if(Utilities.explicitWaitVisible(driver, settingsPageV2.playSing, 10)) {
			    	test.log(LogStatus.PASS,
							"Verified the functionality by tapping on 'Rate us ' button in the pop-up");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("882: Verify the functionality by tapping on 'Rate us ' button in the pop-up",
							rowno882, "PASS");
			    }else BasePageV2.reportFail("Not able to Navigate to Play Store Page");		
			    
			    ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
			    
			    
			//Help & Support Feature Functionality
			    
			    settingsPageV2.putBackGroundApp();   // put the app to background and get back
			    
			    try {
			    	settingsPageV2.settingsHelpSupport.click();
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Not able to click Help&support option in Settings page");
				}
			    
			    
			    if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
			    	
			    	test.log(LogStatus.PASS,
							"Validated Help & Support link functionality:");
					//BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("870: Validate Help & Support link functionality:",
							rowno870, "PASS");
					
					
			    }else BasePageV2.reportFail("Not able to Navigate to Help & Support Page");
			    
			  // Verify UI of Help & Support Page 
			    
			    settingsPageV2.putBackGroundApp();  // put background app and get back
			    
			    if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
			    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpContactTile, 10)) {
			    					if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpFAQs, 10)) {
			    							if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpTemsConditions, 10)) {
			    								if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpPrivacyPolicy, 10)) {
			    									Utilities.verticalSwipe(driver);
			    									if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpAppVersion, 10)) {
			    										test.log(LogStatus.PASS,
			    												"Verified the UI of Help & Support page");
			    										//BasePageV2.takeScreenshot();
			    										BasePageV2.smokeresults("872: Verify the UI of Help & Support page",
			    												rowno872, "PASS");
			    									}else BasePageV2.reportFail("'App version' option is not Found in Help main Page");
			    								}else BasePageV2.reportFail("'Privacy Policy' option is not Found in Help main Page");
			    							}else BasePageV2.reportFail("'Terms and Conditions' option is not Found in Help main Page");
			    					}else BasePageV2.reportFail("'FAQs' option is not Found in Help main Page");
			    	
			    	}else BasePageV2.reportFail("'Contact main text' is not Found in Help main Page");
			    }else BasePageV2.reportFail("Not able to navigate Help & Support Screen");
			    
			    
	//Verifying the FAQ's feature Functionality
			    
			    	
			    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpFAQs, 10)) {
			    		settingsPageV2.helpFAQs.click();
			    		if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpFAQs, 20)) {
			    			
							test.log(LogStatus.PASS,"Validate the functionality for FAQ's link");
							
							BasePageV2.smokeresults("874: Validate the functionality for FAQ's link",
									rowno874, "PASS");
			    		}
			    		if(Utilities.explicitWaitClickable(driver, settingsPageV2.helpContactFAQbackBtn, 10)) {
			    		settingsPageV2.helpContactFAQbackBtn.click();
			    		}else BasePageV2.reportFail("FAQs back Button not Found or else Functionality not validated");
			    	}else BasePageV2.reportFail("Not able to navigate to FAQ's Page ");
			    	
			    	
			    
	//Validate Terms & conditions Page 
			    
			    settingsPageV2.putBackGroundApp();
			    	
			    if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpTemsConditions, 10)) {
			    	settingsPageV2.helpTemsConditions.click();
			    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpTermsandcondTile, 10)) {
			    		test.log(LogStatus.PASS,
								"Validated the functionality for Terms & Conditions link");
						//BasePageV2.takeScreenshot();
						BasePageV2.smokeresults("875: Validate the functionality for Terms & Conditions link",
								rowno875, "PASS");
			      }else BasePageV2.reportFail("Not able to navigate to terms & Conditions Page");
			    }else BasePageV2.reportFail("Not able to found Terms and Conditions option in Help&Support main page");
			    
			    
			((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
           
			    
//	Navigate to Privacy Policy link
	           settingsPageV2.putBackGroundApp();
			
	             if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpPrivacyPolicy, 10)) {
	            	 settingsPageV2.helpPrivacyPolicy.click();
	            	 if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpPrivacyPolicyTile, 10)) {
	            		 test.log(LogStatus.PASS,
									"Validated the functionality for Privacy Policy link");
						//	BasePageV2.takeScreenshot();
							BasePageV2.smokeresults("876: Validate the functionality for Privacy Policy link",
									rowno876, "PASS");
	            	 }else BasePageV2.reportFail("Not able to Navigate to Privacy Palicy Page");
	             }else BasePageV2.reportFail("Not able to Found Privacy Policy Link In Help & Support Page ");
			    
	            
//	             ((AndroidDriver)driver).pressKeyCode(AndroidKeyCode.BACK);
	
// Validate functionality of support contact number button 	
	            /*
	             * Here while Tapping Contact and Email Support Buttons Behavior is Different based on Mobile device so i make below 
	             * way BCz once type contact Button again Sub Menu is display from footer 
	             */
	           /* if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpContactNumBTN, 10)) {
	            	settingsPageV2.helpContactNumBTN.click();
	            	Thread.sleep(1000);
//	            	driver.currentActivity();
//	            
//	            	
//	            	WebElement ele = driver.findElementByXPath("//android.widget.EditText[@resource='com.samsung.android.contacts:id/digits']");
//	            	if (ele.isDisplayed()) {
//	            		System.out.println("Identified The Mobile number");
//	            	}
//	            	
	            	
	            	
	            	if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpSupportPhoneSubTile, 10)) {
	            		settingsPageV2.helpSupportPhoneSubTile.click();
	            		
	            		if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpSupportContactNumber, 20)) {
	            		Thread.sleep(1000);
	            		 test.log(LogStatus.PASS,
									"Verify the functionality of support contact number button");
							//BasePageV2.takeScreenshot();
							BasePageV2.smokeresults("894: Verify the functionality of support contact number button",
									rowno894, "PASS");
							  for(int i =1 ; i<5;i++) {
									driver.navigate().back();
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
										break;
									}
								}
	            		}else {
	            			if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpSupportPhoneSubTile, 10)){
	            				settingsPageV2.helpSupportPhoneSubTile.click();
	            				test.log(LogStatus.INFO, "Clicked on phone option");
	            				if(Utilities.explicitWaitClickable(driver, settingsPageV2.justOnceBtn, 10)){
	            					settingsPageV2.justOnceBtn.click();
	            				}else{
	            					BasePageV2.reportFail("Just Once Button Not found For Navigating to contact Support Number Screen");
	            				}
	            			}else{
	            			BasePageV2.reportFail("Contact Support Number is not Found");
	            			}
	            		}
	            		
	         
	            		
	            	}else {
	            		Thread.sleep(1000);
	            		
	            		 test.log(LogStatus.PASS,
									"Verify the functionality of support contact number button");
							//BasePageV2.takeScreenshot();
							BasePageV2.smokeresults("894: Verify the functionality of support contact number button",
									rowno894, "PASS");
							  for(int i =1 ; i<5;i++) {
									driver.navigate().back();
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
										break;
									}
								}
	            		
	            	}
	            }else BasePageV2.reportFail("Not able to Found Help Contact Number Button");*/
	            
	            
// Validate the functionality of support Email id button 	
	    /*       
	          if(Utilities.explicitWaitClickable(driver, settingsPageV2.helpEmailContactBtn, 10)) {
	        	  settingsPageV2.helpEmailContactBtn.click();
	        	  if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpSupportEmailText, 20)) {
	        		   settingsPageV2.helpSupportEmailText.click();
	        		   Thread.sleep(1000);
	        		   if(Utilities.explicitWaitVisible(driver, settingsPageV2.addNewActForEmail, 20)) {
	            		Thread.sleep(1000);
	            		 test.log(LogStatus.PASS,
									"Verified the functionality of support email id button");
							//BasePageV2.takeScreenshot();
							BasePageV2.smokeresults("895: Verify the functionality of support email id button",
									rowno895, "PASS");
							  for(int i =1 ; i<5;i++) {
									
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
										break;
									}
									driver.pressKeyCode(AndroidKeyCode.BACK);
							     }
								}else test.log(LogStatus.FAIL, "not able to naviagted to 'Email' page");
	            		}else {
	            			Thread.sleep(1000);
		            		if(Utilities.explicitWaitVisible(driver, settingsPageV2.addNewActForEmail, 10)) {
		            		 test.log(LogStatus.PASS,
										"Verified the functionality of support email id button");
							//	BasePageV2.takeScreenshot();
								BasePageV2.smokeresults("895: Verify the functionality of support email id button",
										rowno895, "PASS");
								  for(int i =1 ; i<5;i++) {
									  driver.pressKeyCode(AndroidKeyCode.BACK);
										if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
											break;
										}
									}
		            		}else {
		            			  if(Utilities.explicitWaitClickable(driver, settingsPageV2.GmailNextBtn, 4) || Utilities.explicitWaitVisible(driver, settingsPageV2.shareGmailCompose, 4)) {
		            				  test.log(LogStatus.PASS,
												"Verified the functionality of support email id button");
									//	BasePageV2.takeScreenshot();
										BasePageV2.smokeresults("895: Verify the functionality of support email id button",
												rowno895, "PASS");
		            			  }else {
		            				  test.log(LogStatus.FAIL, "Not able to found compose or Next Button ");
		            				  BasePageV2.takeScreenshot();
		            			  }
		            			
		            			
		            		}
		            	}	
	          }else BasePageV2.reportFail("Not able to Click Email Support Button or Not Found Email Support button");
	          
	          
	          */
	          
	            
	      // Navigating to Email Support Compose Screen
	           
	          
//	          for(int i =1 ; i<5;i++) {
//				  driver.pressKeyCode(AndroidKeyCode.BACK);
//					if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
//						break;
//					}
//				}
	           
//	          try {
//			    	settingsPageV2.settingsHelpSupport.click();
//				} catch (Exception e) {
//					test.log(LogStatus.FAIL, "Not able to click Help&support option in Settings page");
//				}
	          
	      /*    driver.pressKeyCode(AndroidKeyCode.BACK);
	          driver.pressKeyCode(AndroidKeyCode.BACK);
	          
       		if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpEmailContactBtn, 10)) {
       			try {
       				settingsPageV2.helpEmailContactBtn.click();
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Not able to clicked 'Email Contact button' in Help Page/ not found");
				}
       			
       			
       			if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpSupportGmailText, 10)) {
       				settingsPageV2.helpSupportGmailText.click();
       			if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpGmailSupportFROM, 10)) {
       				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpGmailSupportTO, 10)) {
       					if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpGmailSupportSubject, 10)) {
       		          test.log(LogStatus.PASS,"Validate the prefilled data post tapping on support email id button");
					BasePageV2.smokeresults("896: Validate the prefilled data post tapping on support email id button",
							rowno896, "PASS");
					  for(int i =1 ; i<5;i++) {
							driver.navigate().back();
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
								break;
							}
						}
       					}else test.log(LogStatus.FAIL, "'Subject' not found");
       					   BasePageV2.takeScreenshot();
       				}else test.log(LogStatus.FAIL, "'To' not found");
       				BasePageV2.takeScreenshot();
       			}else test.log(LogStatus.FAIL, "'From' not found");
       			    BasePageV2.takeScreenshot();
       		}else {
       			
       		  for(int i =1 ; i<5;i++) {
					driver.navigate().back();
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpGmailSupportFROM, 10)) {
						break;
					}
				}
       			
       			
       			if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpGmailSupportFROM, 10)) {
       				if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpGmailSupportTO, 10)) {
       					if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpGmailSupportSubject, 10)) {
       		 test.log(LogStatus.PASS,"Validate the prefilled data post tapping on support email id button");
				//	BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("896: Validate the prefilled data post tapping on support email id button",
							rowno896, "PASS");
					  for(int i =1 ; i<5;i++) {
							driver.navigate().back();
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.helpHELPtile, 10)) {
								break;
							}
						   }
       					}else test.log(LogStatus.FAIL, "'Subject' option is not found in compose mail page");
       				}else test.log(LogStatus.FAIL, "'To' option is not found in compose mail page"); 
       			}else test.log(LogStatus.FAIL, "'From' option is not found in compose mail page");	
       		}
	           
       		}else BasePageV2.reportFail("Support Email Button Not Found or not able to Tapped");  */   
	           
	      
			
		}else BasePageV2.reportFail("Settings icon Not Found to navigate to settings Page");
		

   }	
		
		
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	
}
