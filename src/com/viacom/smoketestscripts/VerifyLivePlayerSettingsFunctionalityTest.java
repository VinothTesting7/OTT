package com.viacom.smoketestscripts;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

//Author : Vinoth
public class VerifyLivePlayerSettingsFunctionalityTest extends BaseTestV2{
	String testName = "VerifyLivePlayerSettingsFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Live Player Settings screen UI Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Live Player Settings Screen UI functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode
//VK_528 : Verify the UI of the Stream Quality in the settings 
//vk_522 : Verify the UI of the Settings overlay
//vk_514: Verify the slider button on the player
//vk_520 : Verify Down arrow icon functionality.
//vk_523: Verify the language selection  functionality:
//vk_529 : Verify the quality settings by default in the settings screen:
	
	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	 String timebefPause="";
	 //Click on Watch tab
		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
		Thread.sleep(2000);
		
		 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
				homepagev2.tabClick("Watch");
				test.log(LogStatus.INFO, "Scroll down to Live Tab");
				homepagev2.tabClick("Watch");
				Utilities.verticalSwipe(driver, showspagev2.liveNowTab, "clickable", 100);		
				Thread.sleep(1000);
				 //Check for Episodes section
				if(Utilities.explicitWaitVisible(driver, showspagev2.liveNowTab , 30))
				{
					test.log(LogStatus.INFO, "Live Now tab is Present");
					Utilities.verticalSwipe(driver);					
					Thread.sleep(1000);
					if(Utilities.explicitWaitVisible(driver, showspagev2.liveTabFirstVideoTitle , 30))
					{		
						test.log(LogStatus.INFO, "Live Videos are displayed under Live now tab");
					}
					else
					{
				      test.log(LogStatus.SKIP, "Skipping live player related testcases as content is not present under live tab");
				    }
					
					if(Utilities.explicitWaitVisible(driver, showspagev2.liveTabFirstVideo , 30))
					{		
						showspagev2.liveTabFirstVideo.click();
					}
					else
						{
						   BasePageV2.reportFail("Not able to play content under Live now tab / No content available");
						}
				}
				else
					BasePageV2.reportFail("Live now Tab is not present");
						  //Check for progress bar"
				
				test.log(LogStatus.INFO, "Playing any of live video content");
				  homepagev2.verifyAndProgressBar();
						
				  //check whether slider n seekbar present or not when playing 
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSeekBar, 1))
				  {
					  test.log(LogStatus.FAIL, "Seek bar is displayed on the Player screen during live playback");
				  }
				  else
					  test.log(LogStatus.INFO, "Seek bar is hidden  on the Player screen during live content playback"); 
				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerBottomUpArrowButton, 1))
					  test.log(LogStatus.FAIL, "slider button is displayed on the Player screen during live content playback");
					  else
						  test.log(LogStatus.INFO, "slider is hidden  on the Player screen during live content playback"); 
						  //Pause the video
				  playerpagev2.pauseVideo();
				  
				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerBottomUpArrowButton, 10))
				  {	
					  test.log(LogStatus.INFO, "slider button is displayed on the Player screen after tapping on player during live content playback");
					  test.log(LogStatus.PASS, "Testcase : 'Verify the slider button on the player' is Passed");
					  if(!Utilities.setResultsKids("VK_514", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
					  playerpagev2.playerBottomUpArrowButton.click();
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerBottomDownArrowButton, 10))
					  {	
						  test.log(LogStatus.INFO, "slider button pointed downwards after tapping slider button");
					     // test.log(LogStatus.PASS, "Testcase : 'Verify the slider button functionality on the player ' is passed");
					      playerpagev2.playerBottomDownArrowButton.click();
					  }
					  else
						  test.log(LogStatus.FAIL, "slider button not pointed downwards after tapping slider button");
					  
				  }
			      else
				    {
					  test.log(LogStatus.WARNING, "slider is not present on the Player screen after tapping on player during live content playback"); 
				    }
					int streamErr=0;
					  int settingErr=0;
					//Check for settings icon	  				  
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerSettings, 10))
				  {
					  test.log(LogStatus.PASS,"Testcase : 'Verify Down arrow icon functionality.' is Passed");
					  
					  if(!Utilities.setResultsKids("VK_520", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
					  test.log(LogStatus.INFO, "Player Settings is displayed on the Player screen");
					  test.log(LogStatus.INFO, "Verifying UI of the Settings overlay");
					  playerpagev2.playerSettings.click();
					//  System.out.println(driver.getPageSource());
					  
					
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguageList, 10))
					  {
						  test.log(LogStatus.INFO, "Select Language List button is displayed");
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedLanguage, 10))
						  {
							  String lang=playerpagev2.defaultSelectedLanguage.getText();
							  if(lang!=null && !lang.equals(""))
							  {
								  test.log(LogStatus.INFO, "Default selected Language is : "+lang);
							  }
							  else
								test.log(LogStatus.FAIL, "No default language is selected ");
						
						  }
						  else
							  test.log(LogStatus.FAIL, "default selected language is not displayed ");
						  
						  playerpagev2.selectLanguageList.click();
						  System.out.println(driver.getPageSource());
						
						 //System.out.println(driver.getPageSource());
						  String selectedlang=""; 
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsSelectLanguage, 10))
						  {
							  selectedlang=playerpagev2.playerSettingsSelectLanguage.getText();
							  test.log(LogStatus.INFO, "Selecting the language - "+selectedlang);
							  playerpagev2.playerSettingsSelectLanguage.click();							  
							  
						  }
						  
					
						  System.out.println(driver.getPageSource());
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguageList, 10))
						  {
							  test.log(LogStatus.INFO, "Select Language List button is displayed");
							  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedLanguage, 10))
							  {
								  String lang=playerpagev2.defaultSelectedLanguage.getText();
								  if(lang!=null && !lang.equals("")  && lang.equals(selectedlang))
								  {
									  test.log(LogStatus.INFO, "Selected Language is Displayed : "+lang);
									  test.log(LogStatus.PASS,"Testcase : 'Verify the language selection  functionality:' is Passed");
									  if(!Utilities.setResultsKids("VK_523", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
								  
								  }
								  else
									{
									  if(!Utilities.setResultsKids("VK_523", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
									  test.log(LogStatus.FAIL, "Selected Language is not Displayed & Language displayed is - "+lang);
									}
							  }
							  else
								  test.log(LogStatus.FAIL, "Selected language is not displayed ");
							  
						  }
						  else
							  test.log(LogStatus.FAIL, "Select Language List button is not displayed after selecting language");
						  
						  
					  }
					  else
						  {
						  test.log(LogStatus.FAIL, "Select Language List button is not displayed");
						  settingErr++;
						  }
					  

					  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectQualityList, 10))
					  {
						  test.log(LogStatus.INFO, "Select Quality List button is displayed");
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedQuality, 10))
						  {
							  String quality=playerpagev2.defaultSelectedQuality.getText();
							  if(quality!=null && !quality.equals(""))
							  {
								  if(quality.equalsIgnoreCase("Auto"))
								  {
									  test.log(LogStatus.INFO, "Default selected Quality is : "+quality);
									  test.log(LogStatus.PASS,"Testcase : 'Verify the quality settings by default in the settings screen:' is Passed");
									  if(!Utilities.setResultsKids("VK_529", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
								  }
								  else
								  {
									  if(!Utilities.setResultsKids("VK_529", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
									  test.log(LogStatus.FAIL, "Default selected Quality is  not auto: ");
								  }
							  }
							  else
								test.log(LogStatus.FAIL, "No default quality is selected ");
						  }
						  else
							  test.log(LogStatus.FAIL, "Default selected Quality is not displayed ");
						  playerpagev2.selectQualityList.click();
					  }
					  else
					  {
						  settingErr++;
						  test.log(LogStatus.FAIL, "Select quality List button is not displayed");
					  }
					  
		
					
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsAutoQuality, 20))
					  {
						  test.log(LogStatus.INFO, "Default Auto quality setting is displayed");
					  }
					  else
					  {
						  test.log(LogStatus.FAIL, "Auto is not displayed / Auto is not default");
						  streamErr++; 
					  }
					  
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsLowQuality, 20))
						  test.log(LogStatus.INFO, "Low quality setting is displayed");
					  else
					  {
						  test.log(LogStatus.FAIL, "Low quality is not displayed / Low is default");
						  streamErr++;
					  }
					  
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsHighQuality, 20))
						  test.log(LogStatus.INFO, "High quality setting is displayed");
					  else
						  {
						    test.log(LogStatus.FAIL, "High quality is not displayed / High is default");
						    streamErr++;
						  }
				  
				  }
				  else
				  {
					  if(!Utilities.setResultsKids("VK_520", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
					  BasePageV2.reportFail("Player Settings is not displayed");
				  }
				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsCloseButton, 10)) 
					  playerpagev2.playerSettingsCloseButton.click();
				  else
				  {
					  test.log(LogStatus.FAIL, "Close Button is not displayed in Select Quality Screen");
				  }
				  
					 if(settingErr==0)
					 {
						 test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the Settings overlay:' is Passed");
						 if(!Utilities.setResultsKids("VK_522", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
					 }
					 else
					 {
						  if(!Utilities.setResultsKids("VK_522", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
					 }
					
					 if(!Utilities.setResultsKids("VK_488", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 if(streamErr==0)
						 {
							 test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the Stream Quality in the settings screen:' is Passed");
						     if(!Utilities.setResultsKids("VK_528", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
						 }
						 else
						 {
							  if(!Utilities.setResultsKids("VK_528", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
						 }
				
					
                    
		                  
		 
		 }
	  else
		  BasePageV2.reportFail("Not able to click watch tab");
	
 
}
	
	
		  @DataProvider
			public Object[][] getData(){
				return DataUtil.getData(testName,xls);
						
			
			
		  }
		}