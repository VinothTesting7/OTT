package com.viacom.registration;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.SignupandLoginPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author =Vinoth

import io.appium.java_client.android.AndroidKeyCode;

//VK_2280 - Verify the functionality of Facebook Login:
//VK_2285 - Verify the redirection when user sign in with Google account by using the same email which was used for FB sign in:
//VK_2282 - Verify the redirection when user sign in with FB account by using the same Mobile Number which was used for Traditional sign in:
//VK_2283 - Verify the redirection when user sign in with FB account by using the same Email ID which was used for Traditional sign in:
//VK_2286 - Verify the redirection when user sign in with Google account by using the same Email ID which was used for Traditional sign in:
//VK_2288 - Verify if user has an option to reset the password post FB sign in:
//VK_2290 - Verify Reset PIN functionality for FB users:
//VK_2292 - Verify Forgot PIN functionality for FB users:
//VK_2300 - Verify Forgot PIN functionality for FB users:

//Author : Vinoth - 13-06-2019
public class VerifyFacebookLoginOptionFunctionalityTest extends BaseTestV2
{
    String testName = "VerifyFacebookLoginOptionFunctionalityTest";
    String facebookProfileName="";
    String tradProfileName="";
	@Test(dataProvider = "getData")
	public void VerifySocialMediaLinkFunctionality(Hashtable<String, String> data) throws Exception
	{
	 if(GlobalVariables.break_Flag)
	 throw new SkipException("Skipping the test as it is No");
	 test = rep.startTest("VerifyFacebookLoginOptionFunctionalityTest");
	 test.log(LogStatus.INFO, "Starting the test for VerifyFacebookLoginOptionFunctionalityTest: "+VootConstants.DEVICE_NAME);
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
	 SettingsPageV2 settingspagev2 = new SettingsPageV2(driver, test);

	Set<String> contexts = driver.getContextHandles();
	 
       System.out.println(contexts.size());
       for(String context:contexts)
       {
    	   System.out.println(context);
    	   if(context.contains("hrome"))
    		   driver.context(context);
       }
       

       driver.manage().deleteAllCookies();
	 HomePageV2.logout();
	 if(Utilities.explicitWaitClickableNew(driver,launchpagev2.loginGateway, 10))
	 {	
		 launchpagev2.loginGateway.click();
	 }
	 else
		 BasePageV2.reportFail("Not able to click on login button in welcome screen");
	 
			 //Verify PageTitle SignUp in signUPPage			 			 
			 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.facebookButton, 10))
			 {				
					String email=data.get("FbMail");
					String password=data.get("FbPwd");	
				   signuploginpagev2.facebookButton.click();	
				   test.log(LogStatus.INFO, "Verifying facebook login functionality"); 
					fbcheck:{
					if(Utilities.explicitWaitClickableNew(driver,homepagev2.profilepic, 10))
					{
						break fbcheck;
					    
					}
					else if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.loginToAnotherAccount, 10)){
						   signuploginpagev2.loginToAnotherAccount.click();
					
						   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbEmail, 20));
						   { signuploginpagev2.fbEmail. clear();
						   signuploginpagev2.fbEmail.sendKeys(email);
						   }
						   if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
						   { signuploginpagev2.fbPassword.click();
						   signuploginpagev2.fbPassword.sendKeys(password);
						   }
						   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20));
						   signuploginpagev2.fbLogin.click();
						        //test.log(LogStatus.INFO, "Logged in to facebook successfully");
						   
					}
					else if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbEmail, 20))
					  { 
						    signuploginpagev2.fbEmail. clear();
						    signuploginpagev2.fbEmail.sendKeys(email);
						    if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
						   {
							  signuploginpagev2.fbPassword.click();
						      signuploginpagev2.fbPassword.sendKeys(password);
						   }
						   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20))
						   signuploginpagev2.fbLogin.click();
					  }
					else
					  {
						        test.log(LogStatus.FAIL, "fb login failed"); 	
						        BasePageV2.takeScreenshot();  
					  }
		}
	}
			 else
			   BasePageV2.reportFail("Not able to click on fb button");
			
			 
			 if(Utilities.explicitWaitClickableNew(driver,homepagev2.profilepic, 30))
			 {		
				 homepagev2.profilepic.click();
				 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
				 {
					 facebookProfileName=launchpagev2.switchProfileScreenProfileName.getText().trim();
                     test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of Facebook login:' is Passed");
					 if(!Utilities.setResultsKids("VK_2280", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					        
				}
				 else
				 {
					 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
				 }
				 driver.pressKeyCode(AndroidKeyCode.BACK);
			 }
			 else if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectedProfileName, 10))
			 {
				   facebookProfileName=launchpagev2.selectedProfileName.getText().trim();
				   test.log(LogStatus.INFO, "Testcase : 'Verify the functionality of Facebook login:' is Passed");
				   if(!Utilities.setResultsKids("VK_2280", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					if (Utilities.explicitWaitVisibleNew(driver, launchpagev2.selectProfileIcon, 5)) {
						test.log(LogStatus.INFO, "Multi user Profile, selecting a profile to login");
						launchpagev2.selectProfileIcon.click();
						Thread.sleep(4000);
					}
				   
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Facebook Sign in failed"); 
			        if(!Utilities.setResultsKids("VK_2280", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        BasePageV2.takeScreenshot();
		     } 
			 
			 // VK_VK_2288  - Verify if user has an option to reset the password post FB sign in:
			 
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 30))
				{
					homepagev2.profilepic.click();
					Thread.sleep(1000);
				}
				else
		         BasePageV2.reportFail("User Profile icon is not displayed");
		       
				if (Utilities.explicitWaitClickableNew(driver, settingspagev2.parentZoneButton, 10))
				{
						settingspagev2.parentZoneButton.click();
						Thread.sleep(3000);
						test.log(LogStatus.INFO, "Tapped on Parent Zone button");
				}
				else
				{
					test.log(LogStatus.FAIL, "failed to Tapped on Parent Zone button");
					BasePageV2.takeScreenshot();
				}
				
				// VK_2300 - Verify Forgot PIN functionality for Fb users:
					
				if(Utilities.explicitWaitClickableNew(driver,settingspagev2.forgotPinInParentzoneScreen , 15))
				{
					settingspagev2.forgotPinInParentzoneScreen.click();
				}
				else
		        test.log(LogStatus.FAIL, "Not able to click on forgot pin link in reset Parent zone screen");
				
				if(Utilities.explicitWaitVisibleNew(driver,settingspagev2.forgotPinMsgBody , 5))
				{
					test.log(LogStatus.INFO, "Text - 'If you have forgotten your PIN. We can send you an SMS or email to reset your PIN' is displayed");
					if(Utilities.explicitWaitVisibleNew(driver,settingspagev2.forgotPinpopupYESbtn , 5))
					{
						test.log(LogStatus.INFO, "yes button is displayed on popup ");
						if(Utilities.explicitWaitVisibleNew(driver,settingspagev2.forgotPinpopupNObtn , 5))
						{
							test.log(LogStatus.INFO, "No button is displayed on popup ");
							settingspagev2.forgotPinpopupNObtn.click();
							test.log(LogStatus.PASS, "Testcase : 'Verify Forgot PIN functionality for Fb users:' is Passed");
							 if(!Utilities.setResultsKids("VK_2300", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else
							{
							 BasePageV2.takeScreenshot();
							 if(!Utilities.setResultsKids("VK_2300", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							  test.log(LogStatus.FAIL,"No button is not displayed on popup");
							}
					}
					else
						{
						BasePageV2.takeScreenshot();
						if(!Utilities.setResultsKids("VK_2300", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						test.log(LogStatus.FAIL,"yes button is not displayed on popup");
						}
			 }
				else
					{
					  BasePageV2.takeScreenshot();
					  if(!Utilities.setResultsKids("VK_2300", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					  test.log(LogStatus.FAIL,"Forgot  pin pop up not displayed");
					}
				
				
				
				if (Utilities.explicitWaitVisibleNew(driver, launchpagev2.parentPinContainer, 10))
				{
					launchpagev2.parentPinContainer.clear();	
					launchpagev2.parentPinContainer.sendKeys("1111");
					Thread.sleep(1000);
					try{
						driver.hideKeyboard();
						launchpagev2.parentPinContainer.sendKeys("1111");
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				driver.runAppInBackground(Duration.ofSeconds(3));
				 driver.currentActivity();
				 Thread.sleep(5000);
				if (Utilities.explicitWaitClickableNew(driver, homepagev2.settings, 20))
				{
					homepagev2.settings.click();
				}
				else
		         BasePageV2.reportFail("Not able to click Settings");
				
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.accountSectionInSettingsScreen , 5))
				{
					launchpagev2.accountSectionInSettingsScreen.click();
				}
				else
		         BasePageV2.reportFail("Not able to click on Device option in Settings screen");
			 
			 
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.resetPasswordInAccountSettingsScreen , 5))
				{
					launchpagev2.resetPasswordInAccountSettingsScreen.click();
				}
				else
		        BasePageV2.reportFail("Not able to click on reset password in Account Settings screen");
			 
			 
				if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.cannotResetPasswordMessage , 5))
				{
					test.log(LogStatus.INFO, "You cannot reset your password message is displayed");
					if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.cannotResetPasswordPopUpOkButton , 5))
					{
						launchpagev2.cannotResetPasswordPopUpOkButton.click();
						if(Utilities.explicitWaitClickableNew(driver,launchpagev2.resetPasswordInAccountSettingsScreen , 5))
						{
							test.log(LogStatus.INFO, "Pop up dismissed after tapping Ok button");
							test.log(LogStatus.PASS, "Testcase : 'Verify if user has an option to reset the password post FB sign in:' is Passed");
							 if(!Utilities.setResultsKids("VK_2288", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else
						{
							 if(!Utilities.setResultsKids("VK_2288", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							test.log(LogStatus.FAIL, "Pop up not dismissed after tapping Ok button");
						}				
					}
					else
					{
						 if(!Utilities.setResultsKids("VK_2288", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						test.log(LogStatus.FAIL, "Pop up Ok button is not displayed");
					}
				}
				else
		        test.log(LogStatus.FAIL,"You cannot reset your password message is not displayed");
			 
			   
			    //VK_2290 Verify Reset PIN functionality for FB users:		
				
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.resetParentZonePinInAccountSettingsScreen , 5))
				{
					launchpagev2.resetParentZonePinInAccountSettingsScreen.click();
				}
				else
		        BasePageV2.reportFail("Not able to click on reset Parent zone pin in Account Settings screen");
				
				
				if(Utilities.explicitWaitClickableNew(driver,settingspagev2.reSetPinContainer, 5))
				{
					test.log(LogStatus.INFO, "Entering current pin");
					settingspagev2.reSetPinContainer.sendKeys("1111");
					Thread.sleep(1000);
				}
				else if (true){
					
					for(int i=1 ; i < 4; i++) {
						WebElement pinContain = driver.findElementByXPath("//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/pin_digit_"+ i +"']");
						System.out.println("Finding the Pin Container in Reset PIn");
						System.out.println(pinContain);
						pinContain.clear();
						pinContain.click();
						pinContain.sendKeys("1");
					  }
				}
		
				
				
				if(Utilities.explicitWaitClickableNew(driver, settingspagev2.resetPinNextBtn, 10)) {
					   settingspagev2.resetPinNextBtn.click();
					   test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");		
				}
				else {
					   test.log(LogStatus.FAIL, "Not able to click 'NEXT' button in Reset Pin page / not found");
				    }
				

				if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.newPinResetParent, 10)) {
		         
					test.log(LogStatus.INFO,"Entering new pin");
					settingspagev2.reSetPinContainer.sendKeys("1234"); 
					Thread.sleep(1000);
					
					if(Utilities.explicitWaitClickableNew(driver, settingspagev2.resetPinNextBtn, 10)) {
						   settingspagev2.resetPinNextBtn.click();
						   test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");		
					}
					else {
						   test.log(LogStatus.FAIL, "Not able to click 'NEXT' button in Reset Pin page / not found");
					    }
					
					
				}else {
					BasePageV2.reportFail("Enter New pin container not Found");
				}
				
				
				if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.confirmNewPinResetParent, 10)) {
		         
					test.log(LogStatus.INFO,"Entering confirm pin");
					if (Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 10))
					{
						launchpagev2.parentPinContainer.clear();	
						launchpagev2.parentPinContainer.sendKeys("1234");
						Thread.sleep(1000);
					/*	try{
							driver.hideKeyboard();
							launchpagev2.parentPinContainer.sendKeys("1234");
						}
						catch(Exception e){ e.printStackTrace();}*/
					}
					Thread.sleep(1000);
					if(Utilities.explicitWaitClickableNew(driver, settingspagev2.confimNewPinResetSaveBtn, 10)) {
						settingspagev2.confimNewPinResetSaveBtn.click();
						test.log(LogStatus.INFO, "Clicked SAVE button in Confirm Reset Pin page");
			
					}else {
						test.log(LogStatus.FAIL, "Not able to clicked 'SAVE' button in Confirm Reset Pin page / not found");
					}
					
				}else {
					BasePageV2.reportFail("Confirm New pin container not Found");
				}
				
				if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.pinResetSuccessMsg, 10)) {
				  test.log(LogStatus.INFO, "Pin reset success message is displayed");
				  test.log(LogStatus.PASS, "Testcase : 'Verify Reset PIN functionality for FB users:' is Passed");
				  if(!Utilities.setResultsKids("VK_2290", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				  settingspagev2. pinResetSuccessMsgPopupCloseButton.click();
				}
				else
				{	
				  test.log(LogStatus.FAIL, "Pin reset message is not displayed");
				  if(!Utilities.setResultsKids("VK_2290", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				  BasePageV2.takeScreenshot();
				}
				
				// change back to default
				
				
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.resetParentZonePinInAccountSettingsScreen , 5))
				{
					launchpagev2.resetParentZonePinInAccountSettingsScreen.click();
				}
				else
		        BasePageV2.reportFail("Not able to click on reset Parent zone pin in Account Settings screen");
				
				
				if(Utilities.explicitWaitClickableNew(driver,settingspagev2.reSetPinContainer, 5))
				{
					test.log(LogStatus.INFO, "Entering current pin");
					settingspagev2.reSetPinContainer.sendKeys("1234");
					Thread.sleep(1000);
				}
				else if (true){
					
					for(int i=1 ; i <= 4; i++) {
						WebElement pinContain = driver.findElementByXPath("//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/pin_digit_"+ i +"']");
						System.out.println("Finding the Pin Container in Reset PIn");
						System.out.println(pinContain);
						pinContain.clear();
						pinContain.click();
						pinContain.sendKeys(String.valueOf(i));
					  }
				}
				
				if(Utilities.explicitWaitClickableNew(driver, settingspagev2.resetPinNextBtn, 10)) {
					   settingspagev2.resetPinNextBtn.click();
					   test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");		
				}
				else {
					   test.log(LogStatus.FAIL, "Not able to click 'NEXT' button in Reset Pin page / not found");
				    }
				

				if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.newPinResetParent, 10)) {
		         
					test.log(LogStatus.INFO,"Entering new pin");
					settingspagev2.reSetPinContainer.sendKeys("1111"); 
					Thread.sleep(1000);
					
					if(Utilities.explicitWaitClickableNew(driver, settingspagev2.resetPinNextBtn, 10)) {
						   settingspagev2.resetPinNextBtn.click();
						   test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");		
					}
					else {
						   test.log(LogStatus.FAIL, "Not able to click 'NEXT' button in Reset Pin page / not found");
					    }
					
					
				}else {
					BasePageV2.reportFail("Enter New pin container not Found");
				}
				
				
				if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.confirmNewPinResetParent, 10)) {
		         
					test.log(LogStatus.INFO,"Entering confirm pin");
					if (Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 10))
					{
						launchpagev2.parentPinContainer.clear();	
						launchpagev2.parentPinContainer.sendKeys("1111");
						Thread.sleep(1000);
					/*	try{
							driver.hideKeyboard();
							launchpagev2.parentPinContainer.sendKeys("1234");
						}
						catch(Exception e){ e.printStackTrace();}*/
					}
					Thread.sleep(1000);
					if(Utilities.explicitWaitClickableNew(driver, settingspagev2.confimNewPinResetSaveBtn, 10)) {
						settingspagev2.confimNewPinResetSaveBtn.click();
						test.log(LogStatus.INFO, "Clicked SAVE button in Confirm Reset Pin page");
			
					}else {
						test.log(LogStatus.FAIL, "Not able to clicked 'SAVE' button in Confirm Reset Pin page / not found");
					}
					
				}else {
					BasePageV2.reportFail("Confirm New pin container not Found");
				}
				
				if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.pinResetSuccessMsg, 10)) {
					  settingspagev2. pinResetSuccessMsgPopupCloseButton.click();
				  test.log(LogStatus.INFO, "Pin reset success message is displayed");
				}
				else
				{	
				  test.log(LogStatus.FAIL, "Pin reset message is not displayed");
				  BasePageV2.takeScreenshot();
				}
				
				//VK_2292 - Verify Forgot PIN functionality for Fb users:
				
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.resetParentZonePinInAccountSettingsScreen , 5))
				{
					launchpagev2.resetParentZonePinInAccountSettingsScreen.click();
				}
				else
		       test.log(LogStatus.FAIL, "Not able to click on reset Parent zone pin in Account Settings screen");
				
				if(Utilities.explicitWaitClickableNew(driver,settingspagev2.ResetPinforgotPin , 5))
				{
					settingspagev2.ResetPinforgotPin.click();
				}
				else
		       test.log(LogStatus.FAIL, "Not able to click on forgot pin link in reset Parent zone screen");
				
				
			     	
				if(Utilities.explicitWaitVisibleNew(driver,settingspagev2.forgotPinMsgBody , 5))
				{
					test.log(LogStatus.INFO, "Text - 'If you have forgotten your PIN. We can send you an SMS or email to reset your PIN' is displayed");
					if(Utilities.explicitWaitVisibleNew(driver,settingspagev2.forgotPinpopupYESbtn , 5))
					{
						test.log(LogStatus.INFO, "yes button is displayed on popup ");
						if(Utilities.explicitWaitVisibleNew(driver,settingspagev2.forgotPinpopupNObtn , 5))
						{
							test.log(LogStatus.INFO, "No button is displayed on popup ");
							settingspagev2.forgotPinpopupNObtn.click();
							test.log(LogStatus.PASS, "Testcase : 'Verify Forgot PIN functionality for Fb users:' is Passed");
							 if(!Utilities.setResultsKids("VK_2292", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else
							{
							 BasePageV2.takeScreenshot();
							 if(!Utilities.setResultsKids("VK_2292", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							  test.log(LogStatus.FAIL,"No button is not displayed on popup");
							}
					}
					else
						{
						BasePageV2.takeScreenshot();
						 if(!Utilities.setResultsKids("VK_2292", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						test.log(LogStatus.FAIL,"yes button is not displayed on popup");
						}
			 }
				else
					{
					BasePageV2.takeScreenshot();
					 if(!Utilities.setResultsKids("VK_2292", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					test.log(LogStatus.FAIL,"Forgot  pin pop up not displayed");
					}
				
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			 HomePageV2.logout();
			 // VK_2285 - Verify the redirection when user sign in with Google account by using the same email which was used for FB sign in:
			 if(Utilities.explicitWaitClickableNew(driver,launchpagev2.loginGateway, 10))
			 {	
				 launchpagev2.loginGateway.click();
			 }
			 else
				 BasePageV2.reportFail("Not able to click on login button in welcome screen");
			 
			 Utilities.verticalSwipe(driver, signuploginpagev2.googleButton, "clickable", 2); 
			  input:{
				 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.googleButton, 10))
				 {		
						boolean flag = false;
						String email=data.get("FbMail");
						String password=data.get("FbPwd");
					    signuploginpagev2.googleButton.click();
					    
						 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
						 break input;
					    
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
										{
											signuploginpagev2.addAccountRadioButton.click();
										    if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.okButton, 10))
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
										else if (Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectProfileIcon, 10))
										{
											test.log(LogStatus.INFO,"Logged in successfully through google");
										}
										else
										test.log(LogStatus.FAIL, "Add account radio button is not available to click");
												
									
					
				 }
				 else
					 BasePageV2.reportFail("Not able to click on google button");
		   }
			 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
			 {		
				 homepagev2.profilepic.click();
				 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
				 {
					 String profname=launchpagev2.switchProfileScreenProfileName.getText().trim();
					 if(profname.equalsIgnoreCase(facebookProfileName))
					 {
					   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign in with Google account by using the same email which was used for FB sign in:' is Passed");
					   if(!Utilities.setResultsKids("VK_2285", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else
					 {
						 test.log(LogStatus.FAIL, "sign up with google  account by using the same email which was used for fb sign in functionality failed");
						  if(!Utilities.setResultsKids("VK_2285", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
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
				 if(profname.equalsIgnoreCase(facebookProfileName))
				 {
				   test.log(LogStatus.PASS, "Testcase : 'Verify the redirection when user sign in with Google account by using the same email which was used for FB sign in:' is Passed");
				   if(!Utilities.setResultsKids("VK_2285", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else
				 {
					 test.log(LogStatus.FAIL, "sign in with google account by using the same email which was used for Facebook sign in functionality failed");
					  if(!Utilities.setResultsKids("VK_2285", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 driver.pressKeyCode(AndroidKeyCode.BACK);
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Google sign in failed using mail which used for fb login"); 
			        if(!Utilities.setResultsKids("VK_2285", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        BasePageV2.takeScreenshot();
		     } 
			 
			 HomePageV2.logout();	 
			 
	       // Testcase : VK_2282 - Verify the redirection when user sign in with FB account by using the same Mobile Number which was used for Traditional sign in:		 
			 HomePageV2.login("8073542250", "vinoth@123");
			 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
			 {		
				 homepagev2.profilepic.click();
				 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
				 {
					 tradProfileName=launchpagev2.switchProfileScreenProfileName.getText().trim();
                     test.log(LogStatus.INFO, "Traditional login using mobile number profile name is -"+tradProfileName);
				}
				 else
				 {
					 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
				 }
			 }
			 else if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectedProfileName, 10))
			 {
				 tradProfileName=launchpagev2.selectedProfileName.getText().trim();
				 test.log(LogStatus.INFO, "Traditional login using mobile number profile name is -"+tradProfileName);
			 }
			 else
			 {
			        test.log(LogStatus.FAIL, "Traditional Sign in failed"); 
			        BasePageV2.takeScreenshot();
		     } 
			 HomePageV2.logout();	 
		// VK_2282	 - Verify the redirection when user sign in with FB account by using the same Mobile Number which was used for Traditional sign in:
			 
			 if(Utilities.explicitWaitClickableNew(driver,launchpagev2.loginGateway, 10))
			 {	
				 launchpagev2.loginGateway.click();
			 }
			 else
				 BasePageV2.reportFail("Not able to click on login button in welcome screen");
			 
					 //Verify PageTitle SignUp in signUPPage			 			 
					 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.facebookButton, 10))
					 {					 
						   signuploginpagev2.facebookButton.click();	
						   test.log(LogStatus.INFO, "Verifying facebook login functionality"); 
							fbcheck:{
							if(Utilities.explicitWaitClickableNew(driver,homepagev2.profilepic, 10))
							{
								break fbcheck;
							    
							}
							else if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.loginToAnotherAccount, 10)){
								   signuploginpagev2.loginToAnotherAccount.click();
								
								   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbEmail, 20));
								   { signuploginpagev2.fbEmail. clear();
								   signuploginpagev2.fbEmail.sendKeys("8073542250");
								   }
								   if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
								   { signuploginpagev2.fbPassword.click();
								   signuploginpagev2.fbPassword.sendKeys("");
								   }
								   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20));
								   signuploginpagev2.fbLogin.click();
								   //test.log(LogStatus.INFO, "Logged in to facebook successfully");   
								   // if(driver.findElements(By.id("OK")).size()>0)
								  // driver.findElementByName("OK").click();
								  // if(driver.findElementsByXPath("//android.widget.Button[@text='Dismiss']").size()>0)
								  // driver.findElementByXPath("//android.widget.Button[@text='Dismiss']").click();
							}
							else
							 {
								        test.log(LogStatus.FAIL, "fb login failed"); 	
								        BasePageV2.takeScreenshot();  
							 }
				}
					
				}
					 else
					 {
					        test.log(LogStatus.FAIL, "Facebook Sign in failed"); 
					        if(!Utilities.setResultsKids("VK_2282", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					        BasePageV2.takeScreenshot();
				     } 
					 HomePageV2.logout();
			 
					// VK_2283	 - Verify the redirection when user sign in with FB account by using the same Email ID which was used for Traditional sign in:
			 
					 if(Utilities.explicitWaitClickableNew(driver,launchpagev2.loginGateway, 10))
					 {	
						 launchpagev2.loginGateway.click();
					 }
					 else
						 BasePageV2.reportFail("Not able to click on login button in welcome screen");
					 
							 //Verify PageTitle SignUp in signUPPage			 			 
							 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.facebookButton, 10))
							 {					 
								   signuploginpagev2.facebookButton.click();	
								   test.log(LogStatus.INFO, "Verifying facebook login functionality"); 
								   if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.loginToAnotherAccount, 10))
								   signuploginpagev2.loginToAnotherAccount.click();
								
								   if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.fbEmail, 20));
								   { signuploginpagev2.fbEmail. clear();
								   signuploginpagev2.fbEmail.sendKeys("vinothtest1189@gmail.com");}
								   if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.fbPassword, 20));
								   { signuploginpagev2.fbPassword.click();
								   signuploginpagev2.fbPassword.sendKeys("vinoth@123");}
								   if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.fbLogin, 20));
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
							 
							 if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
							 {		
								 homepagev2.profilepic.click();
								 if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.switchProfileScreenProfileName, 10))
								 {
									facebookProfileName=launchpagev2.switchProfileScreenProfileName.getText().trim();
				                    if(facebookProfileName.equalsIgnoreCase(tradProfileName))
				                    		{
				                    	     test.log(LogStatus.PASS, "Testcase : 'Verify the redirection when user sign in with FB account by using the same Email ID which was used for Traditional sign in:' is Passed");
				                    	     if(!Utilities.setResultsKids("VK_2283", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				                    		}
				                    else
				                    	 test.log(LogStatus.FAIL, "Sign in fb account failed when using mail id which used for Traditional login");
									
								}
								 else
								 {
									 test.log(LogStatus.FAIL, "Profile name not found in Switch profile screen");
								 }
							 }
							 else if(Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectedProfileName, 10))
							 {
								   facebookProfileName=launchpagev2.selectedProfileName.getText().trim();
								   if(facebookProfileName.equalsIgnoreCase(tradProfileName))
		                   		{
		                   	      test.log(LogStatus.PASS, "Testcase : 'Verify the redirection when user sign in with FB account by using the same Email ID which was used for Traditional sign in:' is Passed");
		                   	      if(!Utilities.setResultsKids("VK_2283", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		                   		}
		                   else
		                   {
		                	   if(!Utilities.setResultsKids("VK_2283", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		                	   test.log(LogStatus.FAIL, "Sign in fb account failed when using mail id which used for Traditional login");
		                   }
							
						}
							 else
							 {
							        test.log(LogStatus.FAIL, "Facebook Sign in failed"); 
							        if(!Utilities.setResultsKids("VK_2283", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							        BasePageV2.takeScreenshot();
						     } 	 
             HomePageV2.logout();	 
             
			 //VK_2286 - Verify the redirection when user sign in with Google account by using the same Email ID which was used for Traditional sign in:
							 
							 if(Utilities.explicitWaitClickableNew(driver,launchpagev2.loginGateway, 10))
							 {	
								 launchpagev2.loginGateway.click();
							 }
							 else
								 BasePageV2.reportFail("Not able to click on login button in welcome screen");
							 
							 Utilities.verticalSwipe(driver, signuploginpagev2.googleButton, "clickable", 2); 
							 if(Utilities.explicitWaitClickableNew(driver,signuploginpagev2.googleButton, 10))
							 {		
								boolean flag = false;
								String email=data.get("TradEmail");
								String password=data.get("TradPwd");	
								signuploginpagev2.googleButton.click();
								test.log(LogStatus.INFO, "Verifying google option functionality"); 

						         input:{
								 if(Utilities.explicitWaitVisibleNew(driver,signuploginpagev2.googleButton, 10))
								 {
								
									    signuploginpagev2.googleButton.click();
									    
										if(Utilities.explicitWaitVisibleNew(driver,homepagev2.profilepic, 10))
										break input;
									    
									    if(!Utilities.explicitWaitVisibleNew(driver, signuploginpagev2.chooseanAccount, 20))
										test.log(LogStatus.INFO, "Choose an account Popup page didnt get loaded");		
									    
									   
									    int count = signuploginpagev2.googleAccounts.size();
												try {	
													   for(int i=0;i<count;i++)
													  {
														String actualGoogleemail = (String)signuploginpagev2.googleAccounts.get(i).getText();
														if(email.equals(actualGoogleemail))
														{
															signuploginpagev2.googleAccounts.get(i).click();
															try{
																signuploginpagev2.okButton.click();
															}
															catch(Exception e) {}
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
														{
															signuploginpagev2.addAccountRadioButton.click();
														    if(Utilities.explicitWaitClickableNew(driver, signuploginpagev2.okButton, 10))
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
														else if (Utilities.explicitWaitVisibleNew(driver,launchpagev2.selectProfileIcon, 10))
														{
															test.log(LogStatus.INFO,"Logged in successfully through google");
														}
														else
														test.log(LogStatus.FAIL, "Add account radio button is not available to click");
															
								 }
								 else
									 BasePageV2.reportFail("Not able to click on google button");
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
									 if(profname.equalsIgnoreCase(facebookProfileName))
									 {
									   test.log(LogStatus.INFO, "Testcase : 'Verify the redirection when user sign in with Google account by using the same Email ID which was used for Traditional sign in:' is Passed");
									   if(!Utilities.setResultsKids("VK_2286", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 }
									 else
									 {
										 test.log(LogStatus.FAIL, "sign up with google  account by using the same email which was used for traditional sign in functionality failed");
										  if(!Utilities.setResultsKids("VK_2286", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
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
								   test.log(LogStatus.PASS, "Testcase : 'Verify the redirection when user sign in with Google account by using the same Email ID which was used for Traditional sign in:' is Passed");
								   if(!Utilities.setResultsKids("VK_2286", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 else
								 {
									 test.log(LogStatus.FAIL, "sign in with google account by using the same email which was used for traditional sign in functionality failed");
									  if(!Utilities.setResultsKids("VK_2286", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 driver.pressKeyCode(AndroidKeyCode.BACK);
							 }
							 else
							 {
							        test.log(LogStatus.FAIL, "Google sign in failed using mail which used for Traditional login"); 
							        if(!Utilities.setResultsKids("VK_2286", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							        BasePageV2.takeScreenshot();
						     } 
							 
							 HomePageV2.logout();	 
							 
							 
			 
	}
			    
	@DataProvider
    public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
					
					
					
			}
