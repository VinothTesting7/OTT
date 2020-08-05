package com.viacom.registration;

import java.time.Duration;
import java.util.Hashtable;
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

//VK_2302 - Verify the functionality of YES button from Forgot PIN pop up for Fb users:
//VK_2303 - Verify user is able to reset the PIN with the received PIN from Forgot PIN flow for Fb users:
//VK_2294 - Verify the functionality of YES button from Forgot PIN pop up for Fb users:
//VK_2295 - Verify user is able to reset the PIN with the received PIN from Forgot PIN flow for Fb users:

//Author : Vinoth - 13-06-2019


public class VerifyFacebookLoginForgotPinFunctionalityTest extends BaseTestV2
{
    String testName = "VerifyFacebookLoginForgotPinFunctionalityTest";
    String FbProfileName="";
    String tradProfileName="";
	@Test(dataProvider = "getData") 
	public void VerifySocialMediaLinkFunctionality(Hashtable<String, String> data) throws Exception
	{  
	 if(GlobalVariables.break_Flag)
	 throw new SkipException("Skipping the test as it is No");
	 test = rep.startTest("VerifyFacebookLoginForgotPinFunctionalityTest");
	 test.log(LogStatus.INFO, "Starting the test for VerifyFacebookLoginForgotPinFunctionalityTest: "+VootConstants.DEVICE_NAME);
	
	 // Check run mode
	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}
	 //LaunchVootkidsapp();								 
     launchApp();				 
 	 String email=data.get("FbMail");
	 String password=data.get("FbPwd");
	  
	 HomePageV2 homepagev2=new HomePageV2(driver,test);
	 SignupandLoginPageV2  signuploginpagev2 =new SignupandLoginPageV2(driver,test);
	 LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	 SettingsPageV2 settingspagev2 = new SettingsPageV2(driver, test);

	 HomePageV2.logout();
	 if(Utilities.explicitWaitClickableNew(driver,launchpagev2.loginGateway, 10))
	 {	
		 launchpagev2.loginGateway.click();
	 }
	 else
		 BasePageV2.reportFail("Not able to click on login button in welcome screen");
 
			
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
				   signuploginpagev2.fbEmail.sendKeys(email);
				   }
				   if(Utilities.explicitWaitClickable(driver,signuploginpagev2.fbPassword, 20));
				   { signuploginpagev2.fbPassword.click();
				   signuploginpagev2.fbPassword.sendKeys(password);
				   }
				   if(Utilities.explicitWaitClickable(driver, signuploginpagev2.fbLogin, 20));
				   signuploginpagev2.fbLogin.click();
				        //test.log(LogStatus.INFO, "Logged in to facebook successfully");
				   
				//  if(driver.findElements(By.id("OK")).size()>0)
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
		   test.log(LogStatus.FAIL, "Not able to click facebook option"); 	
		   BasePageV2.takeScreenshot();			        
	 }
	 

	 if (Utilities.explicitWaitVisibleNew(driver, launchpagev2.selectProfileIcon, 10))
	 {
		 launchpagev2.selectProfileIcon.click();
		 Thread.sleep(3000);
	 }
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
				
//VK_2302 - Verify the functionality of YES button from Forgot PIN pop up for Fb users:
			
				if(Utilities.explicitWaitClickableNew(driver,settingspagev2.forgotPinInParentzoneScreen , 5))
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
						settingspagev2.forgotPinpopupYESbtn.click();
					}
					else
						{
						BasePageV2.takeScreenshot();
						
						test.log(LogStatus.FAIL,"yes button is not displayed on popup");
						}
				}
				else
				{
					 BasePageV2.takeScreenshot();
					 test.log(LogStatus.FAIL,"Forgot  pin pop up not displayed");
				}
		
				// Navigate to mail and take pin
				Thread.sleep(5000);
				String newpin = SettingsPageV2.logintogmailandfetchnewPwd(email, password);
				Thread.sleep(5000);
				//After resetting the pin
				
				
				if(!newpin.equals("") || !newpin.equals(null))
				{
					test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of YES button from Forgot PIN pop up for Fb users:' is Passed");
					if(!Utilities.setResultsKids("VK_2302", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");		
				}
				else
				{
					BasePageV2.takeScreenshot();
					test.log(LogStatus.FAIL, "Yes button functionality failed from forgot pin popup in parent zone screen");
					if(!Utilities.setResultsKids("VK_2302", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				if (Utilities.explicitWaitVisibleNew(driver, launchpagev2.parentPinContainer, 10))
				{
					launchpagev2.parentPinContainer.clear();	
					launchpagev2.parentPinContainer.sendKeys(newpin);
					Thread.sleep(1000);
					try{
						driver.hideKeyboard();
						launchpagev2.parentPinContainer.sendKeys(newpin);
					}
					catch(Exception e){ e.printStackTrace();}
				}
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 driver.currentActivity();
				 Thread.sleep(10000);
				if (Utilities.explicitWaitClickableNew(driver, homepagev2.settings, 10))
				{
					homepagev2.settings.click();
				}
				else
		         BasePageV2.reportFail("Not able to click Settings");
				
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.accountSectionInSettingsScreen , 5))
				{
					test.log(LogStatus.PASS, "Testcase : 'Verify user is able to reset the PIN with the received PIN from Forgot PIN flow for Fb users:' is Passed");
					if(!Utilities.setResultsKids("VK_2303", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					launchpagev2.accountSectionInSettingsScreen.click();
				}
				else
				{
					if(!Utilities.setResultsKids("VK_2303", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.reportFail("Not able to click on Device option in Settings screen");
				}
			 
				//VK_2294 - Verify the functionality of YES button from Forgot PIN pop up for Fb users:
				
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.resetParentZonePinInAccountSettingsScreen , 5))
				{
					launchpagev2.resetParentZonePinInAccountSettingsScreen.click();
				}
				else
		       test.log(LogStatus.FAIL, "Not able to click on reset Parent zone pin in Account Settings screen");
				
				
				
				
				// Reset pin to default before checking forgot pin scenario
				
		
				if(Utilities.explicitWaitClickableNew(driver,launchpagev2.resetParentZonePinInAccountSettingsScreen , 5))
				{
					launchpagev2.resetParentZonePinInAccountSettingsScreen.click();
				}
				else
		        BasePageV2.reportFail("Not able to click on reset Parent zone pin in Account Settings screen");
				
				
				if(Utilities.explicitWaitClickableNew(driver,settingspagev2.reSetPinContainer, 5))
				{
		
					if(!newpin.equals("") || !newpin.equals(null))
					{
						test.log(LogStatus.INFO, "Entering current pin");
						settingspagev2.reSetPinContainer.sendKeys(newpin);
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
					}
					else
					{
						BasePageV2.takeScreenshot();
						test.log(LogStatus.FAIL, "Forgot pin functionality failed in parent zone screen");
					}
				
				}
			
				//VK_2294 - Verify the functionality of YES button from Forgot PIN pop up for Fb users:
				
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
						settingspagev2.forgotPinpopupYESbtn.click();
					}
					else
						{
						BasePageV2.takeScreenshot();
						test.log(LogStatus.FAIL,"yes button is not displayed on popup");
						}
				}
				else
					{
					 BasePageV2.takeScreenshot();
					 test.log(LogStatus.FAIL,"Forgot  pin pop up not displayed");
					}
		
			
			// Navigate to mail and take pin
			Thread.sleep(5000);
		String	resetnewpin = SettingsPageV2.logintogmailandfetchnewPwd(email, password);
			Thread.sleep(5000);
			//After resetting the pin
			
			
			if(!resetnewpin.equals("") || !resetnewpin.equals(null))
			{
				test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of YES button from Forgot PIN pop up for Fb users:' is Passed");
				if(!Utilities.setResultsKids("VK_2294", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");		
			}
			else
			{
				BasePageV2.takeScreenshot();
				test.log(LogStatus.FAIL, "Yes button functionality failed from forgot pin popup in reset pin screen");
				if(!Utilities.setResultsKids("VK_2294", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			
			if(Utilities.explicitWaitClickableNew(driver,settingspagev2.reSetPinContainer, 5))
			{
				if(!resetnewpin.equals("") || !resetnewpin.equals(null))
				{
					test.log(LogStatus.INFO, "Entering current pin");
					settingspagev2.reSetPinContainer.sendKeys(resetnewpin);
					if(Utilities.explicitWaitClickableNew(driver, settingspagev2.resetPinNextBtn, 10)) {
						   settingspagev2.resetPinNextBtn.click();
						   test.log(LogStatus.INFO, "Clicked NEXT button in Reset Pin page");		
					}
					else {
						   test.log(LogStatus.FAIL, "Not able to click 'NEXT' button in Reset Pin page / not found");
					    }
					

					if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.newPinResetParent, 10)) {
			         
						if(!Utilities.setResultsKids("VK_2295", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						test.log(LogStatus.PASS, "Testcase : 'Verify user is able to reset the PIN with the received PIN from Forgot PIN flow for Fb users:' is Passed");
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
						test.log(LogStatus.FAIL, "Enter New pin container not Found");
						if(!Utilities.setResultsKids("VK_2295", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						test.log(LogStatus.FAIL, "Forgot pin functionality failed in reset pin screen");
						
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
				}
				else
				{
					test.log(LogStatus.FAIL, "Forgot pin functionality failed in reset Pin screen");
					BasePageV2.takeScreenshot();
				}
				
			}
				
			 driver.pressKeyCode(AndroidKeyCode.BACK);
			 driver.pressKeyCode(AndroidKeyCode.BACK);
			 driver.pressKeyCode(AndroidKeyCode.BACK);
			 driver.pressKeyCode(AndroidKeyCode.BACK);
			 HomePageV2.logout();			 
	}
			    
	@DataProvider
    public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
					
}
