package com.viacom.registration;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author =Vinoth

import io.appium.java_client.android.AndroidKeyCode;

//VK_2267 - Verify the functionality of Facebook Option:
//VK_2268 - Verify the redirection when user sign up with FB account by using the same email which was used for Google sign up:
//VK_2269 - Verify the redirection when user sign up with FB account by using the same Mobile Number which was used for Traditional sign up:
//VK_2274 - Verify the redirection when user sign up with Google account by using the same email which was used for FB sign up:
//VK_2271 - Verify the functionality of Google Option:
//VK_2270 - Verify the redirection when user sign up with FB account by using the same Email ID which was used for Traditional sign up:
//VK_2275 - Verify the redirection when user sign up with Google account by using the same Email ID which was used for Traditional sign up:

//Author : Vinoth - 13-06-2019
public class VerifyFacebookAndGoogleSignupOptionFunctionalityTest extends BaseTestV2
{
    String testName = "VerifyFacebookAndGoogleSignupOptionFunctionalityTest";
    String googleProfileName="" , facebookProfileName="";
    String tradProfileName="";
	@Test(dataProvider = "getData", groups={"P1"})
	public void VerifySocialMediaLinkFunctionality(Hashtable<String, String> data) throws Exception
	{
	 if(GlobalVariables.break_Flag)
	 throw new SkipException("Skipping the test as it is No");
	 test = rep.startTest("VerifyFacebookAndGoogleSignupOptionFunctionalityTest");
	 test.log(LogStatus.INFO, "Starting the test for VerifyFacebookAndGoogleSignupOptionFunctionalityTest: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}
	 //LaunchVootkidsapp();								 
     launchApp();				 

	 HomePageV2 homepagev2=new HomePageV2(driver,test);
	 SignupandLoginPageV2  signuploginpagev2 =new SignupandLoginPageV2(driver,test);
	 LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	 String UN=data.get("Email");
	 String Pwd=data.get("Password");
	 HomePageV2.login(UN, Pwd);
	 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
	 {		
		 homepagev2.profilepic.click();
		 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
		 {
		     tradProfileName=launchpagev2.switchProfileScreenProfileName.getText().trim();
			 driver.pressKeyCode(AndroidKeyCode.BACK);
		}
		 else
		 {
			 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
		 }
	 }
	 
     //logout 
	 HomePageV2.logout();

	 // Testcase : VK_2267 - Verify the functionality of Facebook Option:
	 
	 
			 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.joinFreefor30Days, 10))
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
			
			 Utilities.verticalSwipe(driver, signuploginpagev2.facebookButton, "clickable", 2); 
			 //Verify PageTitle SignUp in signUPPage			 			 
			 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.facebookButton, 10))
			 {					 
				   signuploginpagev2.facebookButton.click();	
				   test.log(LogStatus.INFO, "Verifying facebook option functionality"); 
			
				
				   if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.loginToAnotherAccount, 10))
				   signuploginpagev2.loginToAnotherAccount.click();
					 
				   
				   String strEmail=data.get("FbMail");
				   String strPassword=data.get("FbPwd");
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbEmail, 20));
				   { signuploginpagev2.fbEmail. clear();
				   signuploginpagev2.fbEmail.sendKeys(strEmail);}
				   if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
				   { signuploginpagev2.fbPassword.click();
				   signuploginpagev2.fbPassword.sendKeys(strPassword);}
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20));
				   signuploginpagev2.fbLogin.click();
				        //test.log(LogStatus.INFO, "Logged in to facebook successfully");
				   
				  	   if(driver.findElementsByName("OK").size()>0)
					   driver.findElementByName("OK").click();
				  	   if(driver.findElementsByXPath("//android.widget.Button[@text='Dismiss']").size()>0)
				  	   driver.findElementByXPath("//android.widget.Button[@text='Dismiss']").click();
				   }		   
			 else
			 {
				   test.log(LogStatus.FAIL, "Not able to click facebook option"); 	
				   BasePageV2.takeScreenshot();			        
			 }	 
			 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.createProfilePage, 10))
			 {		
				 facebookProfileName="AutoFbProfOne";
				 test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of Facebook Option:' is Passed");
				
				 if(!Utilities.setResultsKids("VK_2267", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 HomePageV2.createProfile(facebookProfileName);
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Not taking to create profile screen after Facebook login"); 
			        if(!Utilities.setResultsKids("VK_2267", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			      
			        BasePageV2.takeScreenshot();
		     } 
			 HomePageV2.logout();
			 
		// Testcase : VK_2271 -  Verify the functionality of Google Option:
			 
	
			 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.joinFreefor30Days, 10))
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
			 Utilities.verticalSwipe(driver, signuploginpagev2.googleButton, "clickable", 2); 
			 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.googleButton, 10))
			 {		
				boolean flag = false;
				String email=data.get("GoogleId");
				String password=data.get("GooglePwd");	
				signuploginpagev2.googleButton.click();
				test.log(LogStatus.INFO, "Verifying google option functionality"); 
				if(!Utilities.explicitWaitVisibleNew(driver, signuploginpagev2.chooseanAccount, 20))
				test.log(LogStatus.INFO, "Choose an account Popup page didnt get loaded");				 
		        int count = signuploginpagev2.googleAccounts.size();
					try {	
						   for(int i=0;i<count;i++)
						  {
							String actualGoogleemail = (String)signuploginpagev2.googleAccounts.get(i).getText();
							if(signuploginpagev2.equals(actualGoogleemail))
							{
								signuploginpagev2.googleAccounts.get(i).click();
								signuploginpagev2.okButton.click();
								flag=true;
								break;
							}
						   }
					    }
					catch(Exception e) {}
						if(!flag)
						{
							
							boolean addAcc=true;
							addAcc=Utilities.isDisplayed(driver, signuploginpagev2.addAccountRadioButton);
							if(addAcc)
							signuploginpagev2.addAccountRadioButton.click();
							else 
							test.log(LogStatus.FAIL, "Add account radio button is not available to click");
									
						    if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.okButton, 30))
							signuploginpagev2.okButton.click();
						    try{
							     if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.addAccountEmail, 20))
							     signuploginpagev2.addAccountEmail.sendKeys(email);
							               
					if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.emailNextButton, 20))
						signuploginpagev2.emailNextButton.click();
					if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.addAccountPassword, 20)) {
						signuploginpagev2.addAccountPassword.clear();
						signuploginpagev2.addAccountPassword.sendKeys(password);
					}
					if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.passwordNextButton, 20))
						signuploginpagev2.passwordNextButton.click();
					if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.acceptButton, 20))
						signuploginpagev2.acceptButton.click();
							
					if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.allowBtn, 20))
					    signuploginpagev2.allowBtn.click();	
					    	
							if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.finishRegistration, 20))
							signuploginpagev2.finishRegistration.click();
	                   }
						    catch(Exception e)
						    {
						    	test.log(LogStatus.FAIL,"Add new google account failed");
						    }
						}
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "Not able to click google option"); 	
				        BasePageV2.takeScreenshot();  
			 }
			 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.createProfilePage, 10))
			 {		
				 googleProfileName="AutoGoogleProfOne";
				 test.log(LogStatus.INFO, "Testcase : 'Verify the functionality of Google Option:' is Passed");
				 if(!Utilities.setResultsKids("VK_2271", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 HomePageV2.createProfile(googleProfileName);
			 }
			 else
			 {
			     test.log(LogStatus.FAIL, "Not taking to Create profile screen "); 
			     if(!Utilities.setResultsKids("VK_2271", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			     BasePageV2.takeScreenshot();
		     }
			 HomePageV2.logout(); 
			 
// Testcase : VK_2268 Verify the redirection when user sign up with FB account by using the same email which was used for Google sign up:
			 
			 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.joinFreefor30Days, 10))
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
			
			 Utilities.verticalSwipe(driver, signuploginpagev2.facebookButton, "clickable", 2); 
			 //Verify PageTitle SignUp in signUPPage			 			 
			 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.facebookButton, 10))
			 {					 
				   signuploginpagev2.facebookButton.click();	
				   test.log(LogStatus.INFO, "Verifying facebook option functionality"); 	
				   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				   try {  
					   if(driver.findElementsByName("Log in to another account").size()>0)
					   signuploginpagev2.loginToAnotherAccount.click();
					   }
				   catch(Exception e) {}
				   if(driver.findElementsByXPath("//android.widget.EditText[@resource-id='com.facebook.katana:id/login_username']").size()>0)
				   {
				   try
				   {
				   String strEmail=data.get("GoogleId");
				   String strPassword=data.get("GooglePwd");
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbEmail, 20));
				   { signuploginpagev2.fbEmail. clear();
				   signuploginpagev2.fbEmail.sendKeys(strEmail);}
				   if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
				   { signuploginpagev2.fbPassword.click();
				   signuploginpagev2.fbPassword.sendKeys(strPassword);}
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20));
				   signuploginpagev2.fbLogin.click();
				        //test.log(LogStatus.INFO, "Logged in to facebook successfully");
				   }
				   catch(Exception e)
				   {
					   test.log(LogStatus.FAIL, "Failed to log into Facebook"); 
					   BasePageV2.takeScreenshot();
				   }
				  	   if(driver.findElementsByName("OK").size()>0)
					   driver.findElementByName("OK").click();
				  	   if(driver.findElementsByXPath("//android.widget.Button[@text='Dismiss']").size()>0)
				  	   driver.findElementByXPath("//android.widget.Button[@text='Dismiss']").click();
				   }		   
			 }
			 else
			 {
				   test.log(LogStatus.FAIL, "Not able to click facebook option"); 	
				   BasePageV2.takeScreenshot();			        
			 }	 
			 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
			 {		
				 homepagev2.profilepic.click();
				 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
				 {
					 String profname=launchpagev2.switchProfileScreenProfileName.getText().trim();
					 if(profname.equalsIgnoreCase(googleProfileName))
					 {
					   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with FB account by using the same email which was used for Google sign up:' is Passed");
					   if(!Utilities.setResultsKids("VK_2268", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else
					 {
						 test.log(LogStatus.FAIL, "sign up with FB account by using the same email which was used for Google sign up functionality failed");
						  if(!Utilities.setResultsKids("VK_2268", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 driver.pressKeyCode(AndroidKeyCode.BACK);
				}
				 else
				 {
					 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
				 }
			 }
			 else if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectedProfileName, 10))
			 {
				 String profname=launchpagev2.selectedProfileName.getText().trim();
				 if(profname.equalsIgnoreCase(googleProfileName))
				 {
				   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with FB account by using the same email which was used for Google sign up:' is Passed");
				   if(!Utilities.setResultsKids("VK_2268", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else
				 {
					 test.log(LogStatus.FAIL, "sign up with FB account by using the same email which was used for Google sign up functionality failed");
					  if(!Utilities.setResultsKids("VK_2268", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 driver.pressKeyCode(AndroidKeyCode.BACK);
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Facebook Signup failed using mail which used for Google"); 
			        if(!Utilities.setResultsKids("VK_2268", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        BasePageV2.takeScreenshot();
		     } 
			 HomePageV2.logout();
			 
			 
//Testcase : VK_2269 Verify the redirection when user sign up with FB account by using the same Mobile Number which was used for Traditional sign up:
			 
			 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.joinFreefor30Days, 10))
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
			
			 Utilities.verticalSwipe(driver, signuploginpagev2.facebookButton, "clickable", 2); 
			 //Verify PageTitle SignUp in signUPPage			 			 
			 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.facebookButton, 10))
			 {					 
				   signuploginpagev2.facebookButton.click();	
				   test.log(LogStatus.INFO, "Verifying facebook option functionality"); 	
				   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				   try {  
					   if(driver.findElementsByName("Log in to another account").size()>0)
					   signuploginpagev2.loginToAnotherAccount.click();
					   }
				   catch(Exception e) {}
				   if(driver.findElementsByXPath("//android.widget.EditText[@resource-id='com.facebook.katana:id/login_username']").size()>0)
				   {
				   try
				   {
				   String strEmail=data.get("Email");
				   String strPassword=data.get("Password");
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbEmail, 20));
				   { signuploginpagev2.fbEmail. clear();
				   signuploginpagev2.fbEmail.sendKeys(strEmail);}
				   if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
				   { signuploginpagev2.fbPassword.click();
				   signuploginpagev2.fbPassword.sendKeys(strPassword);}
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20));
				   signuploginpagev2.fbLogin.click();
				        //test.log(LogStatus.INFO, "Logged in to facebook successfully");
				   }
				   catch(Exception e)
				   {
					   test.log(LogStatus.FAIL, "Failed to log into Facebook"); 
					   BasePageV2.takeScreenshot();
				   }
				  	   if(driver.findElementsByName("OK").size()>0)
					   driver.findElementByName("OK").click();
				  	   if(driver.findElementsByXPath("//android.widget.Button[@text='Dismiss']").size()>0)
				  	   driver.findElementByXPath("//android.widget.Button[@text='Dismiss']").click();
				   }		   
			 }
			 else
			 {
				   test.log(LogStatus.FAIL, "Not able to click facebook option"); 	
				   BasePageV2.takeScreenshot();			        
			 }	 
			 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
			 {		
				 homepagev2.profilepic.click();
				 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
				 {
					 String profname=launchpagev2.switchProfileScreenProfileName.getText().trim();
					 if(profname.equalsIgnoreCase(tradProfileName))
					 {
					   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with FB account by using the same Mobile Number which was used for Traditional sign up:' is Passed");
					   if(!Utilities.setResultsKids("VK_2269", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else
					 {
						 test.log(LogStatus.FAIL, "sign up with FB account by using the same number which was used for traditional signup functionality failed");
						  if(!Utilities.setResultsKids("VK_2269", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 driver.pressKeyCode(AndroidKeyCode.BACK);
				}
				 else
				 {
					 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
				 }
			 }
			 else if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectedProfileName, 10))
			 {
				 String profname=launchpagev2.selectedProfileName.getText().trim();
				 if(profname.equalsIgnoreCase(tradProfileName))
				 {
					 test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with FB account by using the same Mobile Number which was used for Traditional sign up:' is Passed");
					   if(!Utilities.setResultsKids("VK_2269", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else
				 {
					 test.log(LogStatus.FAIL, "sign up with FB account by using the same number which was used for traditional signup functionality failed");
					  if(!Utilities.setResultsKids("VK_2269", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			     }
				 driver.pressKeyCode(AndroidKeyCode.BACK);
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Facebook Signup failed using mail which used for traditional"); 
			        if(!Utilities.setResultsKids("VK_2269", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        BasePageV2.takeScreenshot();
		     } 
			 HomePageV2.logout(); 
			
			 
//Testcase : VK_2270 Verify the redirection when user sign up with FB account by using the same Email ID which was used for Traditional sign up:
			 
			 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.joinFreefor30Days, 10))
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
			
			 Utilities.verticalSwipe(driver, signuploginpagev2.facebookButton, "clickable", 2); 
			 //Verify PageTitle SignUp in signUPPage			 			 
			 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.facebookButton, 10))
			 {					 
				   signuploginpagev2.facebookButton.click();	
				   test.log(LogStatus.INFO, "Verifying facebook option functionality"); 	
				   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				   try {  
					   if(driver.findElementsByName("Log in to another account").size()>0)
					   signuploginpagev2.loginToAnotherAccount.click();
					   }
				   catch(Exception e) {}
				   if(driver.findElementsByXPath("//android.widget.EditText[@resource-id='com.facebook.katana:id/login_username']").size()>0)
				   {
				   try
				   {
				   String strEmail=data.get("Email");
				   String strPassword=data.get("Password");
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbEmail, 20));
				   { signuploginpagev2.fbEmail. clear();
				   signuploginpagev2.fbEmail.sendKeys("vinothtest1189@gmail.com");}
				   if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
				   { signuploginpagev2.fbPassword.click();
				   signuploginpagev2.fbPassword.sendKeys("vinoth@123");}
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20));
				   signuploginpagev2.fbLogin.click();
				        //test.log(LogStatus.INFO, "Logged in to facebook successfully");
				 }
				   catch(Exception e)
				   {
					   test.log(LogStatus.FAIL, "Failed to log into Facebook"); 
					   BasePageV2.takeScreenshot();
				   }
				  	   if(driver.findElementsByName("OK").size()>0)
					   driver.findElementByName("OK").click();
				  	   if(driver.findElementsByXPath("//android.widget.Button[@text='Dismiss']").size()>0)
				  	   driver.findElementByXPath("//android.widget.Button[@text='Dismiss']").click();
				   }		   
			 }
			 else
			 {
				   test.log(LogStatus.FAIL, "Not able to click facebook option"); 	
				   BasePageV2.takeScreenshot();			        
			 }	 
			 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
			 {		
				 homepagev2.profilepic.click();
				 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
				 {
					 String profname=launchpagev2.switchProfileScreenProfileName.getText().trim();
					 if(profname.equalsIgnoreCase(tradProfileName))
					 {
					   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with FB account by using the same Email ID which was used for Traditional sign up:' is Passed");
					   if(!Utilities.setResultsKids("VK_2270", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else
					 {
						 test.log(LogStatus.FAIL, "sign up with FB account by using the same mail id which was used for traditional signup functionality failed");
						  if(!Utilities.setResultsKids("VK_2270", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 driver.pressKeyCode(AndroidKeyCode.BACK);
				}
				 else
				 {
					 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
				 }
			 }
			 else if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectedProfileName, 10))
			 {
				 String profname=launchpagev2.selectedProfileName.getText().trim();
				 if(profname.equalsIgnoreCase(tradProfileName))
				 {
					   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with FB account by using the same Email ID which was used for Traditional sign up:' is Passed");
					   if(!Utilities.setResultsKids("VK_2270", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else
				 {
					 test.log(LogStatus.FAIL, "sign up with FB account by using the same mail id which was used for traditional signup functionality failed");
					  if(!Utilities.setResultsKids("VK_2270", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			     }
				 driver.pressKeyCode(AndroidKeyCode.BACK);
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Facebook Signup failed using mail which used for traditional"); 
			        if(!Utilities.setResultsKids("VK_2270", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        BasePageV2.takeScreenshot();
		     } 
			 HomePageV2.logout(); 
			 
			// Testcase : VK_2275 -- Verify the redirection when user sign up with Google account by using the same Email ID which was used for Traditional sign up:
			 
			 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.joinFreefor30Days, 10))
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
			
			 Utilities.verticalSwipe(driver, signuploginpagev2.googleButton, "clickable", 2); 
			 //Verify PageTitle SignUp in signUPPage			 			 
			 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.googleButton, 10))
			 {		
				    boolean flag = false;
					String email=data.get("FbMail");
					String password=data.get("FbPwd");	
					signuploginpagev2.googleButton.click();
					test.log(LogStatus.INFO, "Verifying google option functionality"); 
					if(!Utilities.explicitWaitVisibleNew(driver, signuploginpagev2.chooseanAccount, 20))
					test.log(LogStatus.INFO, "Choose an account Popup page didnt get loaded");				 
			        int count = signuploginpagev2.googleAccounts.size();
						try {	
							   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							   for(int i=0;i<count;i++)
							  {
								String actualGoogleemail = (String)signuploginpagev2.googleAccounts.get(i).getText();
								if(email.equals(actualGoogleemail))
								{
									signuploginpagev2.googleAccounts.get(i).click();
									signuploginpagev2.okButton.click();
									flag=true;
									break;
								}
							   }
						    }
						catch(Exception e) {}
							if(!flag)
							{
								
								boolean addAcc=true;
								addAcc=Utilities.isDisplayed(driver, signuploginpagev2.addAccountRadioButton);
								if(addAcc)
								signuploginpagev2.addAccountRadioButton.click();
								else 
								test.log(LogStatus.FAIL, "Add account radio button is not available to click");
										
							    if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.okButton, 30))
								signuploginpagev2.okButton.click();
							    try{
								     if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.addAccountEmail, 20))
								     signuploginpagev2.addAccountEmail.sendKeys(email);
								               
						if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.emailNextButton, 20))
							signuploginpagev2.emailNextButton.click();
						if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.addAccountPassword, 20)) {
							signuploginpagev2.addAccountPassword.clear();
							signuploginpagev2.addAccountPassword.sendKeys(password);
						}
						if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.passwordNextButton, 20))
							signuploginpagev2.passwordNextButton.click();
						if (Utilities.explicitWaitClickableNew(driver, signuploginpagev2.acceptButton, 20))
							signuploginpagev2.acceptButton.click();
								
						if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.allowBtn, 20))
						    signuploginpagev2.allowBtn.click();	
						    	
								if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.finishRegistration, 20))
								signuploginpagev2.finishRegistration.click();
		                   }
							    catch(Exception e)
							    {
							    	test.log(LogStatus.FAIL,"Add new google account failed");
							    }
							}
				   
			 }
			 else
			 {
				   test.log(LogStatus.FAIL, "Not able to click google option"); 	
				   BasePageV2.takeScreenshot();			        
			 }	 
			 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
			 {		
				 homepagev2.profilepic.click();
				 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
				 {
					 String profname=launchpagev2.switchProfileScreenProfileName.getText().trim();
					 if(profname.equalsIgnoreCase(tradProfileName))
					 {
					   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with Google account by using the same Email ID which was used for Traditional sign up:' is Passed");
					   if(!Utilities.setResultsKids("VK_2275", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else
					 {
						 test.log(LogStatus.FAIL, "sign up with google account by using the same email which was used for traditional sign up functionality failed");
						  if(!Utilities.setResultsKids("VK_2275", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 driver.pressKeyCode(AndroidKeyCode.BACK);
				}
				 else
				 {
					 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
				 }
			 }
			 else if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectedProfileName, 10))
			 {
				 String profname=launchpagev2.selectedProfileName.getText().trim();
				 if(profname.equalsIgnoreCase(tradProfileName))
				 {
				   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign up with Google account by using the same Email ID which was used for Traditional sign up:' is Passed");
				   if(!Utilities.setResultsKids("VK_2275", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else
				 {
					 test.log(LogStatus.FAIL, "sign up with google account by using the same email which was used for traditional sign up functionality failed");
					  if(!Utilities.setResultsKids("VK_2275", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 driver.pressKeyCode(AndroidKeyCode.BACK);
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Facebook Signup failed using mail which used for Google"); 
			        if(!Utilities.setResultsKids("VK_2268", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        BasePageV2.takeScreenshot();
		     } 
			 HomePageV2.logout();
			 
			 
			 
	}
			    
	@DataProvider
    public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
					
					
					
			}
