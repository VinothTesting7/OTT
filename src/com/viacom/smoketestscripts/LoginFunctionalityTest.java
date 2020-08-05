package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author= Bhaskar
public class LoginFunctionalityTest extends BaseTestV2 
{
	
	String ScreenTitle = "Login";
	String headerText = "Enter your details";
	String emailHeader = "Mobile No.";
	String pwdHeader = "Password";
	String dotText = "•••••••";
	String deviderText = "----Or-----";
	String newMember = "Not a member?";
//	String email = "karthiktskaaru5278@gmail.com";
	String invalidEmail = "dayaabc@daya";
	String nonExixtingUser = "dayaabc@daya.in";
	String validPwd = "bhaskar";
	String invalidPwd = "123456";
	String ErrorMessagePwd = "That’s an incorrect password. Please try again.";
	String invalidEmailandPwd = "User does not exist.";
	String blankPwd = "Please enter a valid password";
	String ErrorMessageEmail = "mobile length must be 10 digits long.";
	String passwordErrormsg = "Invalid user ID and/or password";
	String invalidEmailIDError = "Please enter valid phone number";
	String loginBlank = "Please enter mobile and password";
	String validMobileNumber = "9741039718";
	String validMobilePassword = "bhaskar";
	String invalidMobileNumber = "5432105698";
	
	String testName = "LoginFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception 
	{
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, testName + " : " + VootConstants.DEVICE_NAME);
		
		//Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
	
		//Utilities.setResultsKids(TCID, Status)
		
		//VK_07---Verify the click functionality of 'Login' link in Welcome screen	
		//VK_86-----"Verify the UI for login screen";		
		//VK_87-----"Verify the functionality of back arrow button in login screen";				
		//VK_93---"Verify the Password eye functionality by tapping on eye icon in Password field";						
		//VK_97"Verify the click functionality of 'Forgot Password?' link";		
		//VK_101"Verify the click functionality of 'Sign Up' link";				
		//VK_96---"Verify country code is autofilled as user enteres mobile no. in the text field";		
		//VK_94"-----Verify the the 'Login' button functionality by giving valid Mobile number in login screen";				
							
//launch APP		
		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SignupandLoginPageV2  signuploginpagev2 =new SignupandLoginPageV2(driver,test);
		
		
//VK_07---"Verify the click functionality of 'Login' link in Welcome screen";		
			
		  //logging out		
		   HomePageV2.logout();
				
	     		
		test.log(LogStatus.INFO, "TestCase:VK_07---Verify the click functionality of 'Login' link in Welcome screen");    
	
		//click on login link in welcome screen
		if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25)) 
		{
				test.log(LogStatus.INFO, "Welcome screen displayed");
				try
				{
					launchPageV2.loginGateway.click();
					test.log(LogStatus.INFO, "Clicked  on login button");
				}catch(Exception e)
				{
					test.log(LogStatus.FAIL, " failed to Click  on login button");
					BooksPageV2.takeScreenshot();
				}				
		}
	   else
		{
		   test.log(LogStatus.FAIL, "Login link not displayed in welecome Screen");
		   BooksPageV2.takeScreenshot();
		}	
			
		//navigating to login screen or not
		if(Utilities.explicitWaitVisible(driver, signuploginpagev2.PagetitlelogininLoginPage, 25))
		{
				  test.log(LogStatus.PASS, "Test Case: VK_07---'Verify the click functionality of 'Login' link in Welcome screen' is passed");
				  if(!Utilities.setResultsKids("VK_07", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			  			
				    if (Utilities.explicitWaitVisible(driver, homepagev2.allowpermissionbtn, 5))
				    {
					   homepagev2.allowpermissionbtn.click();
					   test.log(LogStatus.INFO, "Device permission allow popup is not displayed");
				    }
				    
				
		} 
		else 
		{
			   test.log(LogStatus.FAIL, "Test Case:VK_07---'Verify the click functionality of 'Login' link in Welcome screen' is failed");
			   if(!Utilities.setResultsKids("VK_07", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			   BooksPageV2.takeScreenshot();			
		}
					
		
//VK_86-----"Verify the UI for login screen"	
		
		List<String> loginUI = new ArrayList<String>();
		// 1. Verify the UI for login screen
		test.log(LogStatus.INFO, "TestCase:VK_86-----Verify the UI for login screen");
		
			
		  //Verifying Screen title - "Login" at the top of screen
		    if(Utilities.explicitWaitVisible(driver, launchPageV2.headerText, 25))
			{
			    test.log(LogStatus.INFO, "Login Screen title is displayed");
			}
			else
			{
				test.log(LogStatus.FAIL, "Login Screen title not Displayed");
				BasePageV2.takeScreenshot();
			}

			String loginHeader = launchPageV2.headerText.getText().toString();
			if (loginHeader.equalsIgnoreCase(ScreenTitle)) 
			{				
				test.log(LogStatus.INFO,"Verified Screen title - Login at the top of screen is displayed:    " + loginHeader);
				loginUI.add("Pass");
			}				
			else
			{								
				test.log(LogStatus.FAIL,"Verified 'Screen title - Login at the top of screen' is not displayed: " + loginHeader);
				loginUI.add("Fail");
				BasePageV2.takeScreenshot();
			}
			
			// Verifying "Enter your details" written at the bottom of "login" text
			if (Utilities.explicitWaitVisible(driver, launchPageV2.headerSubtitle, 5)) 
			{
				        test.log(LogStatus.INFO, "Login header found");
						
						String textHeader = launchPageV2.headerSubtitle.getText().toString();
						if (textHeader.equalsIgnoreCase(headerText)) 
						{				
							test.log(LogStatus.INFO,"Verified 'Enter your details' written at the bottom of login text is displayed: "+ textHeader);
							loginUI.add("Pass");
						}
						else 
						{										
							test.log(LogStatus.FAIL,"Verified 'Enter your details' written at the bottom of login text is not displayed");
							loginUI.add("Fail");
							BasePageV2.takeScreenshot();			
						}
			}
			else
			{
					test.log(LogStatus.FAIL, "Login Page Header  not displayed");
					loginUI.add("Fail");
			}
			
			// Back button the top left
			if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 10)) 
			{
				            test.log(LogStatus.INFO, "Back button");							
				           if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 10)) 
							{
								loginUI.add("Pass");
								test.log(LogStatus.INFO, "'Back button the top left' is displayed: ");
							}					
							else 
							{								
								test.log(LogStatus.FAIL, "'Back button the top left' is not displayed");
								loginUI.add("Fail");
								BasePageV2.takeScreenshot();
							}
			}
			else 
			{
						test.log(LogStatus.FAIL, "Back button was not displayed");
						loginUI.add("Fail");
			}
			
			// "Mobile" field
			if (Utilities.explicitWaitVisible(driver, signuploginpagev2.mobilefieldinLoginPage, 5)) 
			{
				test.log(LogStatus.INFO, "Mobile  Text field displayed");	    
			}
			else 
			{
				test.log(LogStatus.FAIL, "Mobile  Text field  not displayed");
				loginUI.add("Fail");
			}
				
			// Password field
			if (Utilities.explicitWaitVisible(driver, signuploginpagev2.passwordfiledinLoginPage, 5))
			{
				test.log(LogStatus.INFO, "Password fiels displayed");						
			}
			else
			{
				test.log(LogStatus.FAIL, "Password field not displayed");
				loginUI.add("Fail");
			}
		
			// "Password" field with "eye icon" button
			if (Utilities.explicitWaitVisible(driver, signuploginpagev2.eyeinpasswordfieldinLoginPage, 5)) 
			{
				loginUI.add("Pass");	
				test.log(LogStatus.INFO, "'Password field with eye icon' button is displayed ");
			}
			else 
			{				
				test.log(LogStatus.FAIL, "'Password field with eye icon button' is not displayed");
				loginUI.add("Fail");	
				BasePageV2.takeScreenshot();
			}

			// Login button
			if (Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5)) 
			{
				loginUI.add("Pass");	
				test.log(LogStatus.INFO, "'Login button' is displayed ");
			}
			else 
			{
				
				test.log(LogStatus.FAIL, "'Login button' is not displayed ");
				loginUI.add("Fail");	
				BasePageV2.takeScreenshot();
			}
			
			// Forgot Password link
			if (Utilities.explicitWaitVisible(driver, launchPageV2.forgotPwd, 5)) 
			{
				loginUI.add("Pass");	
				test.log(LogStatus.INFO, "'Forgot Password link' is displayed ");
			}
			else 
			{				
				test.log(LogStatus.FAIL, "'Forgot Password link' is not displayed ");
				loginUI.add("Fail");
				BasePageV2.takeScreenshot();
			}

			//   "----Or-----" text below Forgot Password link underlined
			if (Utilities.explicitWaitVisible(driver, launchPageV2.OrDivider, 5)) 
			{				
				test.log(LogStatus.INFO,"'----Or----- 'text below Forgot Password link underlined is displayed is displayed ");
				loginUI.add("Pass");
			}		
			else 
			{
				
				test.log(LogStatus.FAIL,"'----Or-----' text below Forgot Password link underlined is displayed is not displayed ");
				loginUI.add("Fail");				
				BasePageV2.takeScreenshot();
			}

			// Not a member ? 
			if (Utilities.explicitWaitVisible(driver, launchPageV2.notaMemberText, 5)) 
			{
						test.log(LogStatus.INFO, "New member link verification");
						String notMember = launchPageV2.notaMemberText.getText().toString();
						if (notMember.equalsIgnoreCase(newMember)) 
						{
							test.log(LogStatus.INFO, "'Not a member ?' is displayed: " + notMember);
							loginUI.add("Pass");
						}
							
						else 
						{
							test.log(LogStatus.FAIL, "'Not a member ?' is not displayed: " + notMember);
							loginUI.add("Fail");
							BasePageV2.takeScreenshot();
						}
			}
			else 
			{
					test.log(LogStatus.FAIL, "Not a member link was not displayed");
					loginUI.add("Fail");
			}
			
			//Sign Up (link)
			if(Utilities.explicitWaitVisible(driver, signuploginpagev2.signuplinkLoginPage, 20)) 
			{
					
						test.log(LogStatus.INFO, "'Sign Up (link)'  displayed ");
						loginUI.add("Pass");
					
			}
			else 
			{						
				test.log(LogStatus.FAIL, "'Sign Up (link)'  not displayed ");
				loginUI.add("Fail");
				BasePageV2.takeScreenshot();
			}
			
		
			if(loginUI.contains("Pass") && !loginUI.contains("Fail")) 
			{
					test.log(LogStatus.PASS, "Test Case:VK_86-----Verify the UI for login screen: is passed");				
			}
			else
			{
					test.log(LogStatus.FAIL, "Test Case: VK_86-----Verify the UI for login screen: is failed");
					BasePageV2.takeScreenshot();
			}
			
//VK_87-----"Verify the functionality of back arrow button in login screen";		
					
		    test.log(LogStatus.INFO, " TestCase:VK_87-----Verify the functionality of back arrow button in login screen");
		
			if (Utilities.explicitWaitVisible(driver, launchPageV2.backButton, 25)) 
			{
				    test.log(LogStatus.INFO, "Back button is visible");												
					  try
					  {
						  launchPageV2.backButton.click();
						  test.log(LogStatus.INFO, "clicked on Back button");	
					  }catch(Exception e)
					  {
						  test.log(LogStatus.FAIL, " failed to click on Back button");	
						  BasePageV2.takeScreenshot();
					  }
			} else 
			{
				test.log(LogStatus.FAIL, "Back button is  not visible");			
				BasePageV2.takeScreenshot();
			}

	     //verify navigating back toWelcome screen or not
		 try{
						if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25)) 
						{
							test.log(LogStatus.INFO, "Welcome screen is visible");
							test.log(LogStatus.PASS, " TestCase:VK_87-----Verify the functionality of back arrow button in login screen");							
						} 
						else 
						{
							test.log(LogStatus.FAIL, "Welcome screen is not visible");
							test.log(LogStatus.FAIL, " TestCase:VK_87-----Verify the functionality of back arrow button in login screen");							
							BasePageV2.takeScreenshot();
					   }
									
						//click on login link in welcome Screen
					     launchPageV2.loginGateway.click();
					     test.log(LogStatus.INFO, "Clicked on Login Gateway button");

			}catch (Exception e) 
			{
				test.log(LogStatus.FAIL,"Unable to click on Login Gateway button");
				BasePageV2.takeScreenshot();
			}
	
//VK_93---"Verify the Password eye functionality by tapping on eye icon in Password field";
			
		   test.log(LogStatus.INFO, "VK_93---Verify the Password eye functionality by tapping on eye icon in Password field");
			 
		    if(Utilities.explicitWaitVisible(driver, signuploginpagev2.mobilefieldinLoginPage, 25))
			{
						signuploginpagev2.mobilefieldinLoginPage.click();
						signuploginpagev2.mobilefieldinLoginPage.clear();
						signuploginpagev2.mobilefieldinLoginPage.sendKeys(validMobileNumber);
						test.log(LogStatus.INFO, "sucessesfully sent valid mobile number to mobile field ");
					
					   //hiding the Keyboard						 
					 try{
							driver.hideKeyboard();
						}catch (Exception e)
						{
							
						}					
			}
			else
			{
					test.log(LogStatus.FAIL, "Unable to perform actions on mobile number field");
					BasePageV2.takeScreenshot();																				  					
			}
				
			//Enter password to password field 
			if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25)) 
			{
					launchPageV2.pwdText.click();
					launchPageV2.pwdText.clear();
					launchPageV2.pwdText.sendKeys(validMobilePassword);
					test.log(LogStatus.INFO, "Sucessesfully Entered  password to password filed");
					try {
						 driver.hideKeyboard();
					    }catch (Exception e)
					   {}
				
			} 
			else 
			{					
					test.log(LogStatus.FAIL, "failed to enter  passowrd to Password Field");
					BasePageV2.takeScreenshot();
			}
		       
				//click on Eye icon in passwordField
				if (Utilities.explicitWaitClickable(driver, signuploginpagev2.eyeinpasswordfieldinLoginPage, 25))
				{		
					     test.log(LogStatus.INFO, "eye icon is dispalyed in passowrd field in login page");
					   try {
						  
						     signuploginpagev2.eyeinpasswordfieldinLoginPage.click();
						     test.log(LogStatus.INFO, "clicked on eye icon  in passowrd field in login page");
					      
					        }catch(Exception e)
					       {
					    	   test.log(LogStatus.FAIL, "failed to click on eye icon  in passowrd field in login page"); 
					    	   BasePageV2.takeScreenshot();
					       }
				}
				else
				{
						test.log(LogStatus.FAIL, "eye icon not  dispalyed in passowrd field in login page");
						BasePageV2.takeScreenshot();					
				}
						   
		        test.log(LogStatus.INFO, "Checking if Normal text is visible after clicking on eye icon");
				
		        //verifying functionality of eye icon				
				  String numbers = launchPageV2.pwdText.getText().toString();
				 test.log(LogStatus.INFO, "value in password field after clicking on eye icon               "+ numbers);

				if(numbers.contains(validMobilePassword)) 
				{
							 test.log(LogStatus.PASS, "Test Case: VK_93---Verify the Password eye functionality by tapping on eye icon in Password field");
							 if(!Utilities.setResultsKids("VK_93", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");																
				} 
				else 
				{
						   test.log(LogStatus.FAIL, "Test Case: VK_93---Verify the Password eye functionality by tapping on eye icon in Password field");
						   if(!Utilities.setResultsKids("VK_93", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");										
						    BasePageV2.takeScreenshot();
				}
				
		        //hiding the keyboard
		
					try {
						 driver.hideKeyboard();
					    } catch (Exception e) {
					    }
					
											
//VK_97-----"Verify the click functionality of 'Forgot Password?' link";
			
			test.log(LogStatus.INFO, "Test Case:VK_97----Verify the click functionality of 'Forgot Password?' link");
				
			//click on forgotPassword Link
			if (Utilities.explicitWaitVisible(driver, launchPageV2.forgotPwd, 5)) 
			{
				 test.log(LogStatus.INFO, " forgot password link  displayed");
				
				 try{
					  launchPageV2.forgotPwd.click();
					   test.log(LogStatus.INFO, "Clicked on forgot password link");
					}catch(Exception e)
				    {
						test.log(LogStatus.FAIL, " failed to Click on forgot password link");	
						BasePageV2.takeScreenshot();
				    }
			} 
			else
			{
				  test.log(LogStatus.FAIL, " forgot password link not displayed"); 
				  BasePageV2.takeScreenshot();
			}

			
			if (Utilities.explicitWaitVisible(driver, launchPageV2.forgotPasswordPage, 25)) 
			{
				test.log(LogStatus.INFO, "Forgot Password page is displayed");				
				test.log(LogStatus.PASS, "Test Case:VK_97-----Verify the click functionality of 'Forgot Password?' link");
				if(!Utilities.setResultsKids("VK_97", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");							
			 } 
			else 
			{
				 test.log(LogStatus.FAIL, "Test Case: VK_97-----Verify the click functionality of 'Forgot Password?' link");
				 if(!Utilities.setResultsKids("VK_97", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
				 BasePageV2.takeScreenshot();
			}
		
			//navigate back to Login page from forgotPassword page
			
			driver.navigate().back();
			Thread.sleep(2000);
			
//VK_101----"Verify the click functionality of 'Sign Up' link";	
			
			
	    test.log(LogStatus.INFO, "TestCase:VK_101----Verify the click functionality of 'Sign Up' link");
			
	    //click on signupLink in login Page
		if(Utilities.explicitWaitVisible(driver, signuploginpagev2.signuplinkLoginPage, 10)) 
		{				
				try {						 
						  signuploginpagev2.signuplinkLoginPage.click();
						  test.log(LogStatus.INFO, "Clicked on Sign Up link in Login page");
						  Thread.sleep(2000);					 
					}catch(Exception e)
				    {								    
						   test.log(LogStatus.FAIL, "failed to click on  'Sign Up' link in login page");								
						   BasePageV2.takeScreenshot();
					}
		}
		else
		{
			     test.log(LogStatus.FAIL, "Signup Link  not displayed in login page");
			     BasePageV2.takeScreenshot();
		}
		
		//verify navigated to SignUpPage or not
		if (Utilities.explicitWaitVisible(driver, signuploginpagev2.pagetitlesignUp, 20))
		{			  
			    test.log(LogStatus.PASS, "TestCase:VK_101----Verify the click functionality of 'Sign Up' link is Passed");
			    if(!Utilities.setResultsKids("VK_101", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");							  		    			  
		} 
		else 
		{
			    test.log(LogStatus.FAIL, "TestCase:VK_101----Verify the click functionality of 'Sign Up' link is failed ");
			    if(!Utilities.setResultsKids("VK_101", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
			     BasePageV2.takeScreenshot();
		}
		
		
//VK_96---"Verify country code is autofilled as user enteres mobile no. in the text field";				
			
				//navigate back to welcome Screen from signUp page
				 driver.navigate().back();
				 Thread.sleep(2000);
				 driver.navigate().back();
				 Thread.sleep(2000);
				
				if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25)) 
				{
					test.log(LogStatus.INFO, "Welcome screen displayed");				
					test.log(LogStatus.INFO, "Clicking on login button");
					launchPageV2.loginGateway.click();
				}
				else
				{
					test.log(LogStatus.FAIL,"Login link not displayed in welcome screen");
					BasePageV2.takeScreenshot();
				}

				 if(Utilities.explicitWaitVisible(driver, signuploginpagev2.mobilefieldinLoginPage, 25))
				{
								signuploginpagev2.mobilefieldinLoginPage.click();
								signuploginpagev2.mobilefieldinLoginPage.clear();
								signuploginpagev2.mobilefieldinLoginPage.sendKeys(validMobileNumber);
							
						    //hide the keyboard	
							try 
							{
								driver.hideKeyboard();
							} catch (Exception e) 
							{
							}
				
							if(Utilities.explicitWaitVisible(driver, launchPageV2.countryCode, 25)) 
							{	
									String code = launchPageV2.countryCode.getText().toString();								
									test.log(LogStatus.INFO,"Country code Autofilled when user enters a mobile number in mobile number text field:     "+ code);												
									test.log(LogStatus.PASS, "Test Case: VK_96---Verify country code is autofilled as user enteres mobile no. in the text field is passed");
									if(!Utilities.setResultsKids("VK_96", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");					
							} 
							else 
							{
									test.log(LogStatus.FAIL, "Test Case: VK_96---Verify country code is autofilled as user enteres mobile no. in the text field is failed");
									if(!Utilities.setResultsKids("VK_96", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									BooksPageV2.takeScreenshot();	
							}
				} 
				else 
				{
					test.log(LogStatus.FAIL,"Mobile number Text Field not found");
					BooksPageV2.takeScreenshot();
				}
				
					
//VK_94"-----Verify the the 'Login' button functionality by giving valid Mobile number in login screen";
		
				   //Validating single-profile user login
				   test.log(LogStatus.INFO, "Verifying single-profile user login");
			       test.log(LogStatus.INFO, " TestCase:VK_94-----Verify the the 'Login' button functionality by giving valid Mobile number in login screen");

				    if(Utilities.explicitWaitVisible(driver, signuploginpagev2.mobilefieldinLoginPage, 25))
					{
								signuploginpagev2.mobilefieldinLoginPage.click();
								signuploginpagev2.mobilefieldinLoginPage.clear();
								signuploginpagev2.mobilefieldinLoginPage.sendKeys(validMobileNumber);
								test.log(LogStatus.INFO, "sucessesfully sent valid mobile number to mobile field ");
							
							   //hiding the Keyboard						 
							 try{
									driver.hideKeyboard();
								}catch (Exception e)
								{
									
								}					
					}
					else
					{
							test.log(LogStatus.FAIL, "Unable to perform actions on mobile number field");
							BasePageV2.takeScreenshot();																				  					
					}

				    
					//Enter password to password field 
					if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 25)) 
					{
							launchPageV2.pwdText.click();
							launchPageV2.pwdText.clear();
							launchPageV2.pwdText.sendKeys(validMobilePassword);
							test.log(LogStatus.INFO, "Sucessesfully Entered  password to password filed");
							try {
								 driver.hideKeyboard();
							    }catch (Exception e)
							   {}
						
					} 
					else 
					{					
							test.log(LogStatus.FAIL, "failed to enter  passowrd to Password Field");
							BasePageV2.takeScreenshot();
					}

							
				//click on login button in login page							
				test.log(LogStatus.INFO, "Clicking on login button");
		
				if(Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 10))
				{
					    test.log(LogStatus.INFO, " login button Displayed");
					  try{
					      launchPageV2.loginButton.click();
					      test.log(LogStatus.INFO, "Clicked  on login button");
					     }catch(Exception e)
					     {
					    	 test.log(LogStatus.INFO, "failed to Click on login button"); 
					    	 BasePageV2.takeScreenshot();
					     }
				} 
				else
				{
						test.log(LogStatus.FAIL,"Login button not displayed");
						BasePageV2.takeScreenshot();
				}
				
				
				//verify navigating to select profile screen or MyStuff-Screen 
				if(Utilities.explicitWaitVisible(driver, launchPageV2.selectProfileTitle, 10))
				{
					test.log(LogStatus.INFO, "naviagted to select profile Screen");
					test.log(LogStatus.PASS, "TestCase:VK_94-----Verify the the 'Login' button functionality by giving valid Mobile number in login screen is passed ");
				    if(!Utilities.setResultsKids("VK_94", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
				    
				}
				else if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
				{								
					test.log(LogStatus.INFO, "naviagted to MyStuff Screen");
					test.log(LogStatus.PASS, "TestCase:VK_94-----Verify the the 'Login' button functionality by giving valid Mobile number in login screen is passed ");
				    if(!Utilities.setResultsKids("VK_94", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
				   
				}
				else
				{				
					test.log(LogStatus.FAIL, " not navigated to switchProfileScreen or  MyStuff Screen");
					test.log(LogStatus.FAIL, "TestCase:VK_94-----Verify the the 'Login' button functionality by giving valid Mobile number in login screen is failed ");
				    if(!Utilities.setResultsKids("VK_94", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
				     BasePageV2.takeScreenshot();
				}
				
				
		    //verify navigating to select profile screen or MyStuff-Screen 
		    if(Utilities.explicitWaitVisible(driver, launchPageV2.selectProfileTitle, 30))
		    {			  
				         test.log(LogStatus.INFO, "navigated to select profile Screen");
					   try{
					                   launchPageV2.selectProfileIcon.click();
					                  test.log(LogStatus.INFO, "clicked on profile icon in switch profile screen");
							         
							         //click on Allow permission button 
									   if(Utilities.explicitWaitVisible(driver, homepagev2.allowpermissionbtn, 30))
										{
										       test.log(LogStatus.INFO, " Allow permisssion button  dispalyed");
									          try{
										    	   homepagev2.allowpermissionbtn.click();
										    	   test.log(LogStatus.INFO, "clicked on Allow permisssion button ");
										          }catch(Exception e) 
									              {
										        	  
									              }
										}
									   else
										{
										System.out.println("Device permission allow popup is not displayed");
										}
								       
									   //Cancel fresh App notifications
										for (int i = 1; i <= 5; i++) 
										{
												   if(Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
													{
													   
													    try{
													       homepagev2.freshAppNotificationCancel.click();
													       }catch(Exception e) {}
													}
												   else
													{
													break;
													}
										}
								
					         
					      }catch(Exception e)
					      {
					    	  test.log(LogStatus.FAIL, "failed to click on profile icon in switch profile screen");
					    	  BasePageV2.takeScreenshot();
					      }
					   
			}
		    else if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 30))
			{
		    	 test.log(LogStatus.INFO, "naviagted to MyStuff Screen");
			     BasePageV2.takeScreenshot();
			}
		    else
		    {
		    	test.log(LogStatus.FAIL, " not navigated to switchProfileScreen or  MyStuff Screen");
		    	BasePageV2.takeScreenshot();
		    }
		
		    
		//logging out		    
		test.log(LogStatus.INFO, "Logging out");
		HomePageV2.logout();
		test.log(LogStatus.INFO, "Logged out successfully Single profile user");
		    
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
