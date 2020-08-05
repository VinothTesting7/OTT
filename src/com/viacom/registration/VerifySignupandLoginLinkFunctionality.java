package com.viacom.registration;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author =bhaskar
public class VerifySignupandLoginLinkFunctionality extends BaseTestV2
{
/*	
	 1) Page Title - Sign Up
	 2) Progress Path(1 Sign up- 2 Choose plan- 3 Explore)
	 3) Text - Enter your details
	 4) 3 mandatory fields - Mobile No, OTP & Create Password(* symbol should be there for all 3 fields)
	 5) Next button - in grey color(button should be in curved shape)
	 6) Text - Already have an account? Login
	 7) Privacy Policy link
	 8) Back arrow
	9. Login with Facebook and Google options*/
	 
	
	//VK_03---Verify the Welcome screen animation
	//VK_1933---Verify Sign Up screen UI:		
	//VK_1948---Verify Mobile No. field in Sign Up page by giving valid input:
	//VK_1950---Verify Mobile No. field in Sign Up page by entering already registered Mobile No.
	//VK_1953---Verify 'Login' link functionality from Sign Up screen:
		
    String testName = "VerifySignupandLoginLinkFunctionality";
	@Test(dataProvider = "getData")
	public void VerifySignupandLoginLinkFunctionality(Hashtable<String, String> data) throws Exception
	{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("VerifySignupandLoginLinkFunctionality");
	test.log(LogStatus.INFO, "Starting the test for VerifySignupandLoginLinkFunctionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
		
	
	 //LaunchVootkidsapp();								 
     launchApp();				 
	 HomePageV2 homepagev2=new HomePageV2(driver,test);
	 SignupandLoginPageV2  signuploginpagev2 =new SignupandLoginPageV2(driver,test);
	 
     //logout 
	 homepagev2.logout();
	 			 
        //VK_1933---Verify Sign Up screen UI:		    
     test.log(LogStatus.INFO, "  TestCase:VK_1933---Verify Sign Up screen UI:");

			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.joinFreefor30Days, 10))
			 {					 
				    test.log(LogStatus.INFO, " Join free for 30 days Button is Displayed in Welcome Screen");				 
				    try{							
				          signuploginpagev2.joinFreefor30Days.click();
				          test.log(LogStatus.INFO, "clicked on Join free for 30 days  button in Welcome Screen");
			            }
				    catch(Exception e)
			            {
			            	  test.log(LogStatus.FAIL, "failed to click on Join free for 30 days  button in Welcome Screen"); 	
						        BasePageV2.takeScreenshot();
			            }
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "Join free for 30 days Button is not Displayed in Welcome Screen"); 	
				        BasePageV2.takeScreenshot();
			 }	 
			 		
			 int controls_err_count=0;
			 
			 //Verify PageTitle SignUp in signUPPage			 			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.pagetitlesignUp, 10))
			 {					 
				      test.log(LogStatus.INFO, "  PageTitle 'SignUp' Displayed  in SignUP Page");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "PageTitle 'SignUp' not  Displayed  in SignUP Page"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	 
			 // verify signup, choose plan and explore , progresspath avaialble
			 test.log(LogStatus.INFO, "Verifying Progress path display");
			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.progressPathSignup, 10))
			 {					 
				      test.log(LogStatus.INFO, "  Progress path 'SignUp' Displayed  in SignUP Page");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "Progress path 'SignUp' not  Displayed  in SignUP Page"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	 
			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.progressPathChoosePlan, 10))
			 {					 
				      test.log(LogStatus.INFO, "  Progress path 'Choose Plan' Displayed  in SignUP Page");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "Progress path 'Choose Plan' not  Displayed  in SignUP Page"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	 
			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.progressPathExplore, 10))
			 {					 
				      test.log(LogStatus.INFO, "  Progress path 'Explore' Displayed  in SignUP Page");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "Progress path 'Explore' not  Displayed  in SignUP Page"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	 
			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.progressPathline, 10))
			 {					 
				      test.log(LogStatus.INFO, "  Progress paths Line Displayed  in SignUP Page");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "Progress paths Line not  Displayed  in SignUP Page"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	 
			 
			//Verify Enter your Details Subtitle  in signUPPage			 			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.enterYourDetails, 10))
			 {					 
				      test.log(LogStatus.INFO, "  'Enter your Details' Subtitle Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " 'Enter your Details' Subtitle not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	 
			 
			 			 
			 //Verify MobileNo Text with * and MobileNumber field in SignupPage			 			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.mobileNoText, 10) && Utilities.explicitWaitVisible(driver,signuploginpagev2.mobileNoTextField, 10))
			 {					 
				      test.log(LogStatus.INFO, "  'MobileNo Text with * and MobileNumber filed in SignupPage'  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " 'MobileNo Text with * and MobileNumber filed in SignupPage'  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	

			 
			 //Verify Password Text with * and Password field in SignupPage			 			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.passwordtext, 10) && Utilities.explicitWaitVisible(driver,signuploginpagev2.passwordtextField, 10))
			 {					 
				      test.log(LogStatus.INFO, "  ' Password Text with * and Password field in SignupPage'  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Password Text with * and Password field in SignupPage'  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	
			 
			 //Verify OTP Text with * and OTP field in SignupPage			 			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.otptext, 10) && Utilities.explicitWaitVisible(driver,signuploginpagev2.otptextField, 10))
			 {					 
				      test.log(LogStatus.INFO, "  ' OTP Text with * and OTP field in SignupPage'  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' OTP Text with * and OTP field in SignupPage'  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	
			 
			 
			 //Verify NEXT Button in grey color in SignUpPage
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.nextbuttonwithoutfillDatainsignUp, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' NEXT Button in grey color'  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' NEXT Button in grey color'  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	 
		  
			//Verify Back Button  in SignUpPage
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.BackButtoninSignupPage, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' Back Button'  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Back Button '  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	
			 
			  
		   //Verify  Send OTP link next to Mobile No field in SignUpPage
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.sendOtpLink, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' Send OTP link next to Mobile No field'  Displayed in signUPPage");
			 }        
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Send OTP link next to Mobile No field '  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }

			 
			 Utilities.verticalSwipe(driver,signuploginpagev2.alreadyhaveanAccountText,"visible",3);
			 			 
			 //Verify 'Already have an account? Login' text
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.alreadyhaveanAccountText, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' Already have an account?' Text  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Already have an account? ' Text  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.alreadyhaveanAccountLoginLink, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' Already have an account? Login' link  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Already have an account? Login ' link  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }		
			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.privacyPolicyLink, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' Privacy policy ' link  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Privacy policy ' link  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	
			 
			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.facebookButton, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' Facebook ' button  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Facebook  ' button  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	
			 
			 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.googleButton, 10))
			 {					 
				        test.log(LogStatus.INFO, "  ' Google' button  Displayed in signUPPage");				 				   
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " ' Google ' button  not  Displayed in signUPPage"); 	
				        BasePageV2.takeScreenshot();
				        controls_err_count++;
			 }	
			 
			 
			 if(controls_err_count==0)
			 {				   
				   test.log(LogStatus.PASS, "Testcase : TestCase:VK_1933---Verify Sign Up screen UI: is passed");
				   if(!Utilities.setResultsKids("VK_1933", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");				   
			 }
			 else
			 {
				   test.log(LogStatus.FAIL, "Testcase : TestCase:VK_1933---Verify Sign Up screen UI: is failed");
				   if(!Utilities.setResultsKids("VK_1933", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				   BasePageV2.takeScreenshot();
			 }
	

			//VK_1948---Verify Mobile No. field in Sign Up page by giving valid input:	 
						 
						 test.log(LogStatus.INFO, " TestCase: VK_1948---Verify Mobile No. field in Sign Up page by giving valid input:"); 
						 			 
						 String x=signuploginpagev2.generateMobileNumber();
						 
						 test.log(LogStatus.INFO, "Automatically Generated mobile number is"+x); 
						 	
						 System.out.println(x);
						 
						 
						 //Enter Valid Mobile Number  to Mobile No field in SignUpPage
						 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.mobileNoTextField, 10))
						 {				
								      test.log(LogStatus.INFO, "  Mobile No field'  Displayed in signUPPage");				 				   								 											 
										 try
										 {								 
											 signuploginpagev2.mobileNoTextField.click();
											 signuploginpagev2.mobileNoTextField.clear();
											 signuploginpagev2.mobileNoTextField.sendKeys(x);								 
											 test.log(LogStatus.INFO, " 'Sucessesfully valid number Enter into mobile No text Field' in signUP Page");				 				   							
										 }catch(Exception e)
										 {
											 test.log(LogStatus.FAIL, " 'Failed to Enter  valid number Enter into mobile No text Field' in signUP Page");				 				   
											 BasePageV2.takeScreenshot();
										 }
						 }
						 else
						 {
							        test.log(LogStatus.FAIL, " 'Mobile No field '  not  Displayed in signUP Page"); 	
							        BasePageV2.takeScreenshot();				       
						 }
						 
						 //hide The keyboard			   
						 try { 
								 driver.hideKeyboard();
								 test.log(LogStatus.INFO, "keyboard hided Sucesseesfuly");	
							 
						     }catch(Exception e) 
						     {
						    	 test.log(LogStatus.FAIL, " failed to hide The keyboard ");
						    	 BasePageV2.takeScreenshot();
						     }
						 
						 //Click on  Send OTP link next to Mobile No field in SignUpPage
						 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.sendOtpLink, 10))
						 {					 
							           test.log(LogStatus.INFO, "  ' Send OTP link next to Mobile No field'  Displayed in signUPPage");						              
							        try{
						            	 signuploginpagev2.sendOtpLink.click();			            	 
						            	 test.log(LogStatus.INFO, "  'clicked on send OTP Link' in signUPPage");			     			    		            	 
						                }catch(Exception e)
						                {
						                	 test.log(LogStatus.FAIL, "  failed to 'click on send OTP Link' in signUPPage");
						                	 BasePageV2.takeScreenshot();
						                }			 
						 }        
						 else
						 {
							        test.log(LogStatus.FAIL, " ' Send OTP link next to Mobile No field '  not  Displayed in signUPPage"); 	
							        BasePageV2.takeScreenshot();				       
						 }
						 		
						 //Error Message should not Display  for valid Mobile Number 			 
						 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.error_message_phone_number, 10))
						 {					 
								      test.log(LogStatus.FAIL, "Error Message Displayed for Entering Valid Mobile Number in signUPPage ");						              				       		 
								      test.log(LogStatus.FAIL, " TestCase: VK_1948---Verify Mobile No. field in Sign Up page by giving valid input: is Failed"); 
								      if(!Utilities.setResultsKids("VK_1948", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									  BasePageV2.takeScreenshot(); 			 
						 }        
						 else
						 {
							           test.log(LogStatus.INFO, " Error Message  not  Displayed for Entering Mobile Number in signUPPage "); 	
							           test.log(LogStatus.PASS, "TestCase: VK_1948---Verify Mobile No. field in Sign Up page by giving valid input: Passed");
									   if(!Utilities.setResultsKids("VK_1948", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									   				       
						 }
						 
			 //VK_1950---Verify Mobile No. field in Sign Up page by entering already registered Mobile No.			 			 
					 test.log(LogStatus.INFO, "TestCase: VK_1950---Verify Mobile No. field in Sign Up page by entering already registered Mobile No."); 
						 	 
					 //Enter Registered mobile number to Mobile No field in SignUpPage
					 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.mobileNoTextField, 10))
					 {				
							      test.log(LogStatus.INFO, "  Mobile No field'  Displayed in signUPPage");				 				   								 											 
									 try
									 {								 
										 signuploginpagev2.mobileNoTextField.click();
										 signuploginpagev2.mobileNoTextField.clear();
										 signuploginpagev2.mobileNoTextField.sendKeys("9741039718");								 
										 test.log(LogStatus.INFO, " 'Sucessesfully Registered number Enter into mobile No text Field' in signUP Page");				 				   							
									 }catch(Exception e)
									 {
										 test.log(LogStatus.FAIL, " 'Failed to Enter  Registired number Enter into mobile No text Field' in signUP Page");				 				   
										 BasePageV2.takeScreenshot();
									 }
					 }
					 else
					 {
						        test.log(LogStatus.FAIL, " 'Mobile No field '  not  Displayed in signUP Page"); 	
						        BasePageV2.takeScreenshot();				       
					 }
					 		 
					 // hide keyboard
					   
					 try { 
						 driver.hideKeyboard();
						 test.log(LogStatus.INFO, "keyboard hided Sucesseesfuly");	
						 
					     }catch(Exception e) 
					     {
					    	 test.log(LogStatus.FAIL, " failed to hide The keyboard ");
					    	 BasePageV2.takeScreenshot();
					     }
					    
					 //Click on  Send OTP link next to Mobile No field in SignUpPage
					 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.sendOtpLink, 10))
					 {					 
						           test.log(LogStatus.INFO, "  ' Send OTP link next to Mobile No field'  Displayed in signUPPage");						              
						        try{
					            	 signuploginpagev2.sendOtpLink.click();
					            	 
					            	 test.log(LogStatus.INFO, "  'clicked on send OTP Link' in signUPPage");			     			    		            	 
					                }catch(Exception e)
					                {
					                	 test.log(LogStatus.FAIL, "  failed to 'click on send OTP Link' in signUPPage");
					                	 BasePageV2.takeScreenshot();
					                }			 
					 }        
					 else
					 {
						        test.log(LogStatus.FAIL, " ' Send OTP link next to Mobile No field '  not  Displayed in signUPPage"); 	
						        BasePageV2.takeScreenshot();				       
					 }
					 	
					 //This mobile number Already Registered with us Message should  Display  for Entering Registered  Mobile Number 			 
					 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.error_message_phone_number, 10))
					 {					 
							      test.log(LogStatus.INFO, "  ' This mobile number Already Registerd with us'   message Displayed in Signup page");						              				       		 
							      test.log(LogStatus.PASS, " VK_1950---Verify Mobile No. field in Sign Up page by entering already registered Mobile No. is Passed"); 
							      if(!Utilities.setResultsKids("VK_1950", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 			 
					 }        
					 else
					 {
						           test.log(LogStatus.FAIL, " 'This mobile number Alredy Registered with us'  message not  Displayed in signUP Page "); 	
						           test.log(LogStatus.FAIL, "TestCase: VK_1950---Verify Mobile No. field in Sign Up page by entering already registered Mobile No. is Failed");
								   if(!Utilities.setResultsKids("VK_1950", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								   BasePageV2.takeScreenshot();				       
					 }
					
					 
			//VK_1953---Verify 'Login' link functionality from Sign Up screen:		 
					 					 
						 test.log(LogStatus.INFO, "TestCase:  VK_1953---Verify 'Login' link functionality from Sign Up screen:");	
					
						 //do vertical swipe down till to get Login link		 
						 Utilities.verticalSwipe(driver);
						 Utilities.verticalSwipe(driver);
					 
						 //Click on Login Link  in SignUpPage
						 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.loginLinkinSignupPage, 10))
						 {					 
							           test.log(LogStatus.INFO, "  ' LoginLink'  Displayed in signUPPage");						              
							        try{		            	
							        	  signuploginpagev2.loginLinkinSignupPage.click();		            	 
						            	  test.log(LogStatus.INFO, " clicked on ' LoginLink' in signUPPage");			            	  
						                }catch(Exception e)
						                {
						                	 test.log(LogStatus.FAIL, "  failed to click on  ' LoginLink' in signUPPage");
						                	 BasePageV2.takeScreenshot();
						                }			 
						 }        
						 else
						 {
							        test.log(LogStatus.FAIL, "  ' LoginLink' not  Displayed in signUPPage");			
							        BasePageV2.takeScreenshot();				       
						 }
					 		
						 			 
						 //verify User navigated to login page or not after clicking on login link in Sign up page
						 if(Utilities.explicitWaitVisible(driver,signuploginpagev2.forgotpasswordinLoginPage, 10))
						 {					 
								      test.log(LogStatus.INFO, " User navigated  to Login Page");						              				       		 
								      test.log(LogStatus.PASS, "VK_1953---Verify 'Login' link functionality from Sign Up screen: is Passed"); 
								      if(!Utilities.setResultsKids("VK_1953", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									  			 
						 }        
						 else
						 {
							           test.log(LogStatus.FAIL, " User  failed to navigate To Login page"); 	
							           test.log(LogStatus.FAIL, "TestCase: VK_1953---Verify 'Login' link functionality from Sign Up screen: is Failed");
									   if(!Utilities.setResultsKids("VK_1953", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									   BasePageV2.takeScreenshot();				       
						 }		
						 			 
				}
			    
					@DataProvider
					public Object[][] getData(){
						return DataUtil.getData(testName,xls);
								
					
					}
			}
