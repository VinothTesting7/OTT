package com.viacom.parentzone;

import java.time.Duration;
import java.util.Hashtable;

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

//author --sarvanan to bhaskar
public class VerifyParentZoneForNewUser extends BaseTestV2
{
	
//VK_1338----Verify the default state for the Email Update options	--bhaskar
//VK_1319----Verify the Default days highlighted when Bed time option is enabled for the first time
//VK_1319------Verify Days selection button functionality	
	
	String testName = "VerifyParentZoneForNewUser";
	String sheetName = "Regression Checklist";
	String pass = "PASS";
	String fail = "FAIL";

	static int errorCount_0 = 0;
	static int errorCount_1 = 0;

	String getBedTimeToggle = null;
	String getWeek = null;
	String getEmailUpdate = null;
	String getEmailToggle = null;
	String getWeekDays = null;
	String getWakeUpTime = null;
	String getSleepTime = null;
	String getFriday = null;
       String EmailUpdateToggle="";
	boolean flag;
	
	@Test(dataProvider = "getData")
	public void verifyParentZoneForNewUser(Hashtable<String, String> data) throws Exception {
		
		test = rep.startTest("VerifyParentZoneForNewUser");
		test.log(LogStatus.INFO, "Verify Parent Zone UI for new profile");
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		
             //launch APP		
		     launchApp();				
		     HomePageV2 homepage = new HomePageV2(driver, test);
		     SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		
		      //logging out		
			  //HomePageV2.logout();
					
		      //login with Valid credentials
			   homepage.login(data.get("Email"),data.get("Password"));
		     
		     
					//click on profile-icon in home page
					if (Utilities.explicitWaitVisible(driver, homepage.profilepic, 30)) 
					{
						        test.log(LogStatus.INFO, "Profile icon has  shown");			
						       try {
							         homepage.profilepic.click();
							         test.log(LogStatus.INFO, "clicked on Profile icon ");
						           }catch(Exception e)
						           {
						        	   test.log(LogStatus.FAIL, "failed to click on Profile icon "); 
						        	   BasePageV2.takeScreenshot();
						           }			     
					}
					else
					{			
						  test.log(LogStatus.FAIL, "Profile icon has not shown");
						  BasePageV2.takeScreenshot();
					}
				
					 //refreshApp
					 driver.runAppInBackground(Duration.ofSeconds(3));
					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					 driver.currentActivity();		
							
					//click on parent zone button
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 30)) 
					{
						    test.log(LogStatus.INFO, "ParentZone button is  Available ");
								try
								{
									settingsPageV2.parentZoneButton.click();
									test.log(LogStatus.INFO, " clicked on ParentZone button ");
								}catch(Exception e) 
								{
									test.log(LogStatus.FAIL, " clicked on ParentZone button ");
									BasePageV2.takeScreenshot();
								}			
								
																
					}
					else
					{
						test.log(LogStatus.FAIL, "  ParentZone button not Available ");
						BasePageV2.takeScreenshot();
					}
				
					//Enter pin to pinView
					if (Utilities.explicitWaitVisible(driver, homepage.pinView_In_ParentZone, 30)) 
					{
						homepage.pinView_In_ParentZone.click();
						homepage.pinView_In_ParentZone.sendKeys("1111");
						test.log(LogStatus.INFO, "PinView In ParentZone has shown");
					}else 
					{					
						 test.log(LogStatus.FAIL, "PinView In ParentZone has not shown");
						 BasePageV2.takeScreenshot();
					}
					
				        Thread.sleep(15000);
				        
				         //refreshApp
						 driver.runAppInBackground(Duration.ofSeconds(3));
						 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
						 driver.currentActivity();	
						 
				      //click on preference tab
				      if (Utilities.explicitWaitVisible(driver,settingsPageV2.parentZonePreferenceTab, 10)) 
					  {    	  
						      try{
						    	     settingsPageV2.parentZonePreferenceTab.click();
							         test.log(LogStatus.INFO, "clicked on parent Zone Preference  tab ");
						         }catch (Exception e)
						         {						    					        	 
						        	 test.log(LogStatus.FAIL, "failed to click on  parent Zone Preference tab ");
						        	 BasePageV2.takeScreenshot();
						         }
					  }	     
					  else 
					  {					
							 test.log(LogStatus.FAIL, " parent Zone Preference tab  is not Available");
							 BasePageV2.takeScreenshot();
					  }
				      
				
					   if(Utilities.explicitWaitVisible(driver, homepage.toggleOff_In_BedTime, 30)) 
					   {
									test.log(LogStatus.INFO, "Daily usage switch");
									getBedTimeToggle = homepage.toggleOff_In_BedTime.getText();
															
									//Scroll till parent zone preference week
									for (int scroll = 0; scroll <= 11; scroll++) 
									{
												if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceWeek, 2)) 
												{
													test.log(LogStatus.INFO, "Swiped to parent zone preference week");
													getWeek = settingsPageV2.parentZonePreferenceWeek.getText();
													test.log(LogStatus.INFO, "Week:" + getWeek);
													getEmailUpdate = settingsPageV2.emailUpdateText.getText();
													getEmailToggle = settingsPageV2.emailtoggleon.getText();
												     
													break;
												} 
												else
												{
												    Utilities.verticalSwipe(driver);
												}
									}
							
								    
									
									
									
									
							      //Couldn't fetch wake up and sleep time causes connection abort exception
							      //Couldn't fetch week days except monday
					   }	
					   
					   						
						if(getBedTimeToggle != null) 
						{
							                homepage.toggleOff_In_BedTime.click();
							
											if (Utilities.explicitWaitVisible(driver, homepage.toggleOff_In_BedTime, 30)) 
											{
												       test.log(LogStatus.INFO, "Expecting bedtime");
												       homepage.toggleOff_In_BedTime.click();
												       getWeekDays = settingsPageV2.weekDays.get(0).getAttribute("text");
													    
														if(getWeekDays != null) 
														{
																settingsPageV2.weekDays.get(0).click();
																if(Utilities.isSelected(driver, settingsPageV2.weekDays.get(0))) 
																{
																	flag = true;
																} else if (Utilities.isSelected(driver, settingsPageV2.weekDays.get(0))) 
																{
																	settingsPageV2.weekDays.get(0).click();
																	flag = true;
																} else 
																{
																	flag = false;
																}
														}
												
																								
														if(getBedTimeToggle != null && getWeekDays != null && flag == true) 
														{
																	test.log(LogStatus.PASS, "VK_1321 - has PASSED");
																	
																	if(getEmailUpdate != null && getEmailToggle != null) 
																	{
																		test.log(LogStatus.PASS, "VK_1319 - has PASSED");
																	} else 
																	{
																		test.log(LogStatus.FAIL, "VK_1319 - has FAILED");
																		BasePageV2.takeScreenshot();
																	}
																	
																   //Un-toggle bed time
																	
																	for (int i = 0; i < 2; i++) 
																	{
																		if (Utilities.explicitWaitVisible(driver, homepage.toggleOn_In_BedTime, 30)) 
																		{
																			homepage.toggleOn_In_BedTime.click();
																		} 
																		else 
																		{
																			test.log(LogStatus.INFO, "Bed time toggle ON has not visible");
																		}
																	}
														} else 
														{
															test.log(LogStatus.FAIL, "VK_1319 - has FAILED");
															test.log(LogStatus.FAIL, "VK_1321 - has FAILED");
														}																															
											 }
						
					     }
						
						
						
						int	toggle=0;
				    	int error_1338=0;
						//VK_1338----Verify the default state for the Email Update options
				    	
				    	
				    	EmailUpdateToggle=settingsPageV2.emailUpdatetoggle.getAttribute("text");
						if(EmailUpdateToggle.contains("OFF"))
						{										
							 test.log(LogStatus.INFO, "Default Toggle Option for Email update is OFF ");
							 
						}
						else
						{
							 toggle++;
							 test.log(LogStatus.FAIL, "Default Toggle Option for Email update is ON");
							 										 
							 error_1338++;									 
						}
						
						if(toggle==1)
						{
							 test.log(LogStatus.INFO, " Toggle Option for Email update is ON ");
						}
						else
						{
							 try{
								 settingsPageV2.emailUpdatetoggle.click();
							   }catch(Exception e) {}
							
							test.log(LogStatus.INFO, " Toggle Option for Email update is changing from OFF to ON ");
						}
						
						String email=settingsPageV2.emailWeek.getAttribute("enabled").toString();
						//Week is highLighted or not
						if(email.equalsIgnoreCase("true"))
						{
							test.log(LogStatus.INFO, "   Week is Default HighLighted Duration  for the users who logs in/sign up with email ID.");
						}
						else
						{									
							test.log(LogStatus.FAIL, "Week is not Default HighLighted Duration  for the users who logs in/sign up with email ID");
						     BasePageV2.takeScreenshot();
						     error_1338++;	
						}
						
						if(error_1338==0)
						{
							test.log(LogStatus.FAIL, "VK_1338----Verify the default state for the Email Update options	is passed ");									
							 if(!Utilities.setResultsKids("VK_1338", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");					
						}
						else
						{
							test.log(LogStatus.FAIL, "VK_1338----Verify the default state for the Email Update options	is failed ");									
							 if(!Utilities.setResultsKids("VK_1338", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 BasePageV2.takeScreenshot();
						}
				
		}			
		@DataProvider
		public Object[][] getData() {
			return DataUtil.getData(testName, xls);
		}

}