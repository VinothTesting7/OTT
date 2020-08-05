package com.viacom.settings;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
//Author Suresh
public class SettingsParentZonePageFunctionality_1 extends BaseTestV2{

String testName = "SettingsParentZonePageFunctionality_1";
    
	
	@Test(dataProvider = "getData")
	public void settingsParentZonePageFunctionality(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsParentZonePageFunctionality_1");
		test.log(LogStatus.INFO, "Starting the test to Settings Parent Zone Page Functionality: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int VK_1306 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1306, "Verify the UI of Preferences segmented tab");
		
		int VK_1307 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1307, "Verify the default state for the Daily Usage option");
		
		int VK_1308 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1308, "Verify 'Daily Usage' toggle button functionality");
		
		int VK_1309 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1309, "Verify the UI of 'Daily Usage' section when button is ON state:");
		
		int VK_1310 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1310, "Verify the Seek functionality for 'Daily Usage' time limit option");
		
		int VK_1311 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1311, "Verify usage time limit increments as user scrubs the seekbar forward");
		
		int VK_1312 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1312, "Verify usage time limit decrements as user scrubs the seekbar backward");
		
		int VK_1316 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1316, "Verify the default state for the Bed Time option");
		
		int VK_1317 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1317, "Verify 'Bed Time' toggle button functionality");
		
		int VK_1318 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1318, "Verify the UI of 'Bed Time' when it is ON state");
		
		int VK_1319 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_1319, "Verify the Default days highlighted when Bed time option is enabled for the first time");
		
		// Launching the Voot-kids App
				launchApp();
				test.log(LogStatus.INFO, "Application launched successfully");

				
				SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
				HomePageV2 homepagev2 = new HomePageV2(driver, test);
				BasePageV2 BasePageV2 = new BasePageV2(driver, test);
				LaunchPageV2 launchpagev2 = new LaunchPageV2(driver, test);
				
				
				// As per TC' Sign Up is Required 
				
//				homepagev2.logout();
//				homepagev2.signup();
				
				homepagev2.login(data.get("Email"),data.get("Password"));
				
				
				
				try {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

			        } catch (Exception e) {
				      BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
			           }
				
			
				
		           //  Verify 'Parent Zone' button functionality:
				
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.parentZoneBtn, 5)) {
					settingsPageV2.parentZoneBtn.click();
					test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");
					
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.ParentZoneTile, 5)) {
						test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
					    try {
							   settingsPageV2.parentPinContainer.sendKeys("1111");
							   test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
						} catch (Exception e) {
							   test.log(LogStatus.FAIL, "Not able to entered PIn '1111' in parent zone page / not found pin container");
						}
					    
					}else {
						BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
					}

				}else BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");
		
	
				Thread.sleep(5000);
		         //  Verify the UI of Preferences segmented tab    Tc-1307
				 settingsPageV2.putBackGroundApp3();
			    if(Utilities.explicitWaitClickable(driver, settingsPageV2.parentZonePreferenceTab, 10)) {
			    	settingsPageV2.parentZonePreferenceTab.click();
			    	test.log(LogStatus.INFO, "Clicked Preference tab in Parent Zone Page");
			    	if(settingsPageV2.parentZonePreferenceTab.getAttribute("selected").equals("true")) {
			    		
			    		try {
			    			Utilities.verticalSwipe(driver, settingsPageV2.parentZonePreferenceDailyUsageSwitch);
			    			if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("true")) {
			    				settingsPageV2.parentZonePreferenceDailyUsageSwitch.click();
			    			}
			    			Utilities.verticalSwipe(driver, settingsPageV2.parentZonePreferenceDailyUsageSwitch);
			    			if(settingsPageV2.parentZonePreferenceBedTimeSwitch.getAttribute("checked").equals("true")) {
			    				settingsPageV2.parentZonePreferenceBedTimeSwitch.click();
			    			}
						} catch (Exception e) {
							// TODO: handle exception
						}
			    		
			    		
			    		
			    		
			    		if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceSetTimeLimits, 10)) {
			    			test.log(LogStatus.INFO, "Found 'Set time Limits' option in Preference Segment section");
			    			if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceDailyUsage, 10)) {
			    				test.log(LogStatus.INFO, "Found 'Daily Usage' option in Preference Segment section");
			    				
			    				 if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("false")) {
			    					 test.log(LogStatus.PASS, "Verify the default state for the Daily Usage option");
			    					 BasePageV2.smokeresults("", VK_1307, "PASS");
			    				 }else {
			    					 test.log(LogStatus.FAIL, "Verify the default state for the Daily Usage option");
			    					 BasePageV2.takeScreenshot();
			    					 BasePageV2.smokeresults("", VK_1307, "FAIL");
			    				 }
			    				  
			    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceDailyUsageSwitch, 10)) {
				    				test.log(LogStatus.INFO, "Found 'Daily Usage Toggle Switch' option in Preference Segment section");
				    				Utilities.verticalSwipe(driver);
			    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceBedTime, 10)) {
				    				test.log(LogStatus.INFO, "Found 'Bed time' option in Preference Segment section");
				    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceBedTimeSwitch, 10)) {
					    				test.log(LogStatus.INFO, "Found 'Bed time Toggle Switch' option in Preference Segment section");
					    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceDownloads, 10)) {
						    				test.log(LogStatus.INFO, "Found 'Downloads' option in Preference Segment section");
						    				Utilities.verticalSwipe(driver);
						    				
							    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceEmailupdate, 10)) {
								    				test.log(LogStatus.INFO, "Found 'Email update' option in Preference Segment section");
								    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceEmailUpdateSwitch, 10)) {
									    				test.log(LogStatus.INFO, "Found 'Email update Toggle Switch' option in Preference Segment section");
									    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceDayBtn, 10)) {
										    				test.log(LogStatus.INFO, "Found 'Day Button' option in Preference Segment section");
										    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceMonth, 10)) {
											    				test.log(LogStatus.INFO, "Found 'Month Button' option in Preference Segment section");
											    				if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceWeek, 10)) {
												    				test.log(LogStatus.INFO, "Found 'Week Button' option in Preference Segment section");
												    				
												    				test.log(LogStatus.PASS, "Verify the UI of Preferences segmented tab");
												    				BasePageV2.smokeresults("", VK_1306, "PASS");
												    				
												    				 //scroll down till Activity
												    			    Utilities.verticalSwipeDown(driver, settingsPageV2.parentZoneActivityTab);
												    			    Utilities.verticalSwipeDown(driver);
												    				
												    			}else {
												    				test.log(LogStatus.FAIL, "Not Found 'Week Button' option in Preference Segment section");
												    				BasePageV2.takeScreenshot();
												    			}
											    				
											    			}else {
											    				test.log(LogStatus.FAIL, "Not Found 'Month Button' option in Preference Segment section");
											    				BasePageV2.takeScreenshot();
											    			}
										    				
										    			}else {
										    				test.log(LogStatus.FAIL, "Not Found 'Day Button' option in Preference Segment section");
										    				BasePageV2.takeScreenshot();
										    			}
									    				
									    			}else {
									    				test.log(LogStatus.FAIL, "Not Found 'Email Update Switch' option in Preference Segment section");
									    				BasePageV2.takeScreenshot();
									    			}
								    				
								    			}else {
								    				test.log(LogStatus.FAIL, "Not Found 'Email update' option in Preference Segment section");
								    				BasePageV2.takeScreenshot();
								    			}
							    				
						    	
						    			}else {
						    				test.log(LogStatus.FAIL, "Not Found 'Downloads' option in Preference Segment section");
						    				BasePageV2.takeScreenshot();
						    			}
					    				
					    			}else {
					    				test.log(LogStatus.FAIL, "Not Found 'Bed Time Switch' option in Preference Segment section");
					    				BasePageV2.takeScreenshot();
					    			}
				    				
				    			}else {
				    				test.log(LogStatus.FAIL, "Not Found 'Bed Time' option in Preference Segment section");
				    				BasePageV2.takeScreenshot();
				    			}
			    				
			    			   }else {
			    				   test.log(LogStatus.FAIL, "Not Found 'Daily Usage Toggle switch' option in Preference Segment section");
			    				   BasePageV2.takeScreenshot();
			    			   }
			    			}else {
			    				test.log(LogStatus.FAIL, "Not Found 'Daily Usage' option in Preference Segment section");
			    				BasePageV2.takeScreenshot();
			    			}
			   
			    		}else {
			    			test.log(LogStatus.FAIL, "Not Found 'Set time Limits' option in Preference Segment section");
			    			BasePageV2.takeScreenshot();
			    		}
			    		
			    	}else {
			    		test.log(LogStatus.FAIL, "Preferences segment not Hightlighted after Click on Preference Segment tab");
			    		BasePageV2.takeScreenshot();
			    	}
			    
			    }else {
			    	test.log(LogStatus.FAIL, "Unable to Click on preference Tab in parent Zone page");
			    	BasePageV2.takeScreenshot();
			    }
			    
		      
		       // Verify 'Daily Usage' toggle button functionality  TC-1309
		
			    Utilities.verticalSwipeDown(driver, settingsPageV2.parentZonePreferenceDailyUsage);
			    
			    if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("false")){
			    	settingsPageV2.parentZonePreferenceDailyUsageSwitch.click();
			    	   
			    	try {
			    		Utilities.verticalSwipe(driver);
			    		//Verify the Seek functionality for 'Daily Usage' time limit option
				    	settingsPageV2.slide(driver);
				    	test.log(LogStatus.PASS, "Verify the Seek functionality for 'Daily Usage' time limit option");
				    	BasePageV2.smokeresults("", VK_1310, "Pass");
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verify the Seek functionality for 'Daily Usage' time limit option");
				    	BasePageV2.smokeresults("", VK_1310, "FAIL");
					}
			    	
			    
			    	test.log(LogStatus.INFO, "Clicked daily Usage toggle Button turn to ON state");
			    	
			    	if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("true")){
				    	settingsPageV2.parentZonePreferenceDailyUsageSwitch.click();
				    	test.log(LogStatus.INFO, "Clicked 'Daily Usage' toggle Button turn to OFF state");
				    	
				        test.log(LogStatus.PASS, "Verify 'Daily Usage' toggle button functionality");
				        BasePageV2.smokeresults("", VK_1308, "PASS");
				    	
				    }else test.log(LogStatus.FAIL, "Preference Section Daily Usage Toggle button not found1");
			    }else {
			    	if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("true")){
			    		
			    	 	try {
				    		//Verify the Seek functionality for 'Daily Usage' time limit option
					    	settingsPageV2.slide(driver);
					    	test.log(LogStatus.PASS, "Verify the Seek functionality for 'Daily Usage' time limit option");
					    	BasePageV2.smokeresults("", VK_1310, "Pass");
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Verify the Seek functionality for 'Daily Usage' time limit option");
					    	BasePageV2.smokeresults("", VK_1310, "FAIL");
					    	BasePageV2.takeScreenshot();
						}
				    	
				    	
				    	
			    		settingsPageV2.parentZonePreferenceDailyUsageSwitch.click();
				    	test.log(LogStatus.INFO, "Clicked daily Usage toggle Button");
				    	test.log(LogStatus.PASS, "Verify 'Daily Usage' toggle button functionality");
				        BasePageV2.smokeresults("", VK_1308, "PASS");
			    	}else test.log(LogStatus.FAIL, "Preference Section Daily Usage Toggle button not found2");
			    }
		
	
		        // Verify the UI of 'Daily Usage' section when button is ON state:  TC-1310
			    
			    if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("false")){
			    	settingsPageV2.parentZonePreferenceDailyUsageSwitch.click();
			    	test.log(LogStatus.INFO, "Clicked daily Usage toggle Button");
			    	if(Utilities.explicitWaitVisible(driver, settingsPageV2.ParentZonePreferenceSeekbar, 10)){
				    	test.log(LogStatus.INFO, "Seek Bar Diplayed under Daily Usage option");
				    	
				    }else test.log(LogStatus.FAIL, "Seek bar does not Displayed under Daily Usage");
			    }else {
			    	if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("true")){
			    		if(Utilities.explicitWaitVisible(driver, settingsPageV2.ParentZonePreferenceSeekbar, 10)){
					    	test.log(LogStatus.INFO, "Seek Bar Diplayed under Daily Usage option");
					    	
					    }else test.log(LogStatus.FAIL, "Seek bar does not Displayed under Daily Usage");
				  
			    	}else test.log(LogStatus.FAIL, "Preference Section Daily Usage Toggle button not found3");
			    }
			    
			    //Verify usage time limit increments as user scrubs the seekbar forward  TC-1312
			       
			           try {
			        	   settingsPageV2.slideForward(driver);
					          test.log(LogStatus.PASS, "Verify usage time limit increments as user scrubs the seekbar forward");
					          BasePageV2.smokeresults("", VK_1311, "PASS");
					} catch (Exception e) {
						 test.log(LogStatus.FAIL, "Verify usage time limit increments as user scrubs the seekbar forward");
				          BasePageV2.smokeresults("", VK_1311, "FAIL");
				          BasePageV2.takeScreenshot();
					}
			           
			    //  Verify usage time limit decrements as user scrubs the seekbar backward   Tc-1313
			           
			           try {
			        	   settingsPageV2.slideBackWard(driver);
					          test.log(LogStatus.PASS, "Verify usage time limit decrements as user scrubs the seekbar backward");
					          BasePageV2.smokeresults("", VK_1312, "PASS");
					} catch (Exception e) {
						 test.log(LogStatus.FAIL, "Verify usage time limit decrements as user scrubs the seekbar backward");
				          BasePageV2.smokeresults("", VK_1312, "FAIL");
					}
			       
			           
			          // put the Switch OFF state
			
					    if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("false")){
					    }else {
					    	if(settingsPageV2.parentZonePreferenceDailyUsageSwitch.getAttribute("checked").equals("true")){
					    		settingsPageV2.parentZonePreferenceDailyUsageSwitch.click();
					    		test.log(LogStatus.INFO, "Clicked daily Usage toggle Button");
					    	}else test.log(LogStatus.FAIL, "Preference Section Daily Usage Toggle button not found3");
					    }

			      //  Verify the default state for the Bed Time option  TC-1317
			           
			           Utilities.verticalSwipe(driver);
			           
			           if(Utilities.explicitWaitClickable(driver, settingsPageV2.parentZonePreferenceBedTimeSwitch, 10)) {
			        	   if(settingsPageV2.parentZonePreferenceBedTimeSwitch.getAttribute("checked").equals("false")) {
			        		   test.log(LogStatus.PASS, "Verify the default state for the Bed Time option");
						          BasePageV2.smokeresults("", VK_1316, "PASS");
			        		   
			        	   }else {
			        		   test.log(LogStatus.FAIL, "Verify the default state for the 'Bed Time' option Toggle state should be OFF");
			        		   BasePageV2.smokeresults("", VK_1316, "FAIL");
			        	   }
			        	   
			           }else {
			        	   BasePageV2.reportFail("Not found 'Bed time' Option toggle Button in preference Page");
			           }
			    
			         //   Verify 'Bed Time' toggle button functionality     TC-1318
			   		
					    Utilities.verticalSwipe(driver);
					    if(settingsPageV2.parentZonePreferenceBedTimeSwitch.getAttribute("checked").equals("false")){
					    	settingsPageV2.parentZonePreferenceBedTimeSwitch.click();
					    	test.log(LogStatus.INFO, "Clicked 'Bed Time' option toggle Button turn to 'ON' state");
			
					    	if(settingsPageV2.parentZonePreferenceBedTimeSwitch.getAttribute("checked").equals("true")){
						    	
						        
						        
						        // Verify the UI of 'Bed Time' when it is ON state TC-1319
						        List<WebElement> weekdays = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title']"));
						        for(int i = 0 ; i < weekdays.size(); i++) {
						        	 String dayText = weekdays.get(i).getText().toString().trim();
						        	 test.log(LogStatus.INFO, "The daytext string is : " + dayText);
						        	if(dayText.length() != 0 ) {
						        		BasePageV2.reportFail("Mentioned day Text Having more than one caharcter : " + dayText);
						        	}else  test.log(LogStatus.INFO, "mentioned day Name having sigle characer : " + dayText);
						        
						        }
						        
						        //checking Format of Wakeup/sleep times
						        String Wakeuptime = settingsPageV2.parentZonePreferenceBedTimeWakeUpTime.getText().toString().trim();
						        String sleeptime = settingsPageV2.parentZonePreferenceBedTimeSleepTime.getText().toString().trim();
						        if((Wakeuptime.contains("AM")) || (Wakeuptime.contains("PM"))) {
						        	test.log(LogStatus.INFO, "wake Up option Time having Format AM/PM");
						        }else BasePageV2.reportFail("Wake Up option Time not contain time Format / not found");
						        
						        if((sleeptime.contains("AM")) || (sleeptime.contains("PM"))) {
						        	test.log(LogStatus.INFO, "Sleep Time option Time having Format AM/PM");
						        }else BasePageV2.reportFail("Sleep time option Time not contain time Format / not found");
						        
						        test.log(LogStatus.PASS, "Verify the UI of 'Bed Time' when it is ON state");
						          BasePageV2.smokeresults("", VK_1318, "PASS");
						          
						          
						          settingsPageV2.parentZonePreferenceBedTimeSwitch.click();
							    	test.log(LogStatus.INFO, "Clicked 'Bed Time' option toggle Button turn to 'OFF' state");
							    	
							        test.log(LogStatus.PASS, "Verify 'Bed Time' Option toggle button functionality");
							        BasePageV2.smokeresults("", VK_1317, "PASS");
						        
						        
						    }else test.log(LogStatus.FAIL, "Preference Section 'Bed Time' option Toggle button not found");
					    }else {
					    	if(settingsPageV2.parentZonePreferenceBedTimeSwitch.getAttribute("checked").equals("true")){
					    		test.log(LogStatus.INFO, "'Bed Time' optiontoggle Button is ON state");
					    		
						        // Verify the UI of 'Bed Time' when it is ON state TC-1319
						        List<WebElement> weekdays = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title']"));
						        for(int i = 0 ; i < weekdays.size(); i++) {
						        	 String dayText = weekdays.get(i).getText().toString().trim();
						        	 test.log(LogStatus.INFO, "The Day text String is : " + dayText);
						        	if(dayText.length() > 1) {
						        		BasePageV2.reportFail("Mentioned day Text Having more than one caharcter : " + dayText);
						        	}else  test.log(LogStatus.INFO, "mentioned day Name having sigle characer : " + dayText);
						        
						        }
						        
						        //checking Format of Wakeup/sleep times
						        String Wakeuptime = settingsPageV2.parentZonePreferenceBedTimeWakeUpTime.getText().toString().trim();
						        String sleeptime = settingsPageV2.parentZonePreferenceBedTimeSleepTime.getText().toString().trim();
						        if((Wakeuptime.contains("AM")) || (Wakeuptime.contains("PM"))) {
						        	test.log(LogStatus.INFO, "wake Up option Time having Format AM/PM");
						        }else BasePageV2.reportFail("Wake Up option Time not contain time Format / not found");
						        
						        if((sleeptime.contains("AM")) || (sleeptime.contains("PM"))) {
						        	test.log(LogStatus.INFO, "Sleep Time option Time having Format AM/PM");
						        }else BasePageV2.reportFail("Sleep time option Time not contain time Format / not found");
						        
						        test.log(LogStatus.PASS, "Verify the UI of 'Bed Time' when it is ON state");
						          BasePageV2.smokeresults("", VK_1318, "PASS");
					    		
					    		
					    		settingsPageV2.parentZonePreferenceBedTimeSwitch.click();
						    	test.log(LogStatus.INFO, "Clicked 'Bed Time' option toggle Button to turn OFF state");
						    	 test.log(LogStatus.PASS, "Verify 'Bed Time' option toggle button functionality");
							        BasePageV2.smokeresults("", VK_1317, "PASS");
					    	}else test.log(LogStatus.FAIL, "Preference Section 'Bed Time' option Toggle button not found");
					    }
				
		
		
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}
