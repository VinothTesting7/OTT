package com.viacom.vodplayer;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
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

public class VerifyVodPlayerSettingsFunctionalityTest extends BaseTestV2{
	String testName = "VerifyVodPlayerSettingsFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Player Settings screen UI Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Settings Screen UI functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int langrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",langrow, "Verify the language selection  functionality");//P1	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int qualityrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",qualityrow, "Verify the quality settings by default in the settings screen:");//P1	
		
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of the Settings overlay:");//P2	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int qualityuirow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",qualityuirow, "Verify the UI of the Stream Quality in the settings");	//P1
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int closerow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",closerow, "Verify Close button functionality:"); //P3
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int postlangrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",postlangrow, "Verify the pop-up message is displayed for 2nd time post accessing the language selection");// P1
	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	
	String un=data.get("Email");
	String pwd=data.get("Password");
	homepagev2.login(un, pwd);
	 String timebefPause="";
	 //Click on Watch tab
	test.log(LogStatus.INFO, "Navigating to Watch Page");
	Thread.sleep(2000);
				homepagev2.tabClick("Watch");
				 String apiTrayName="";
				 String uiTrayName="";
				 int totalAssets=0;
				 String url_kidsCharacters="";
				 String api_kidsCharacters="Kids Characters Tray";
				 String apiname_kidsCharacters="";
				 String trayNameParameter="";
				 String trayTitle="";
				 Response resp_kidsCharacters=null;
				 int rows_kidsCharacters=xls.getRowCount("Api");
				 for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
					apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
					if(apiname_kidsCharacters.equals(api_kidsCharacters)){
						url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
						Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
						resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
						totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
						for(int i=0;i<totalAssets;i++) {
							trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
							if(trayNameParameter.equals("allKidsCharacters")) {
								trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
							}
						}
					 }
				 }	
				 trayTitle=trayTitle.toUpperCase();
					 
				 //Get show name from API
				 String apiName="";
				 String uiName="";
				 //Verify from API first item of ALL CHARACTERS
				 String url_allCharacters="";
				 String api_allCharacters="All Characters";
				 String apiname_allCharacters="";
				 Response resp_allCharacters=null;
				 int rows_allCharacters=xls.getRowCount("Api");
				 for(int rNum=1;rNum<=rows_allCharacters;rNum++){
					apiname_allCharacters=xls.getCellData("Api", "API Name", rNum);
					if(apiname_allCharacters.equals(api_allCharacters)){
						url_allCharacters=xls.getCellData("Api", "Url", rNum);
						Map map=BasePageV2.apiparams(2, xls, api_allCharacters);
						resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
						apiName=resp_allCharacters.jsonPath().get("assets.items[0].title");
					 }
				 }
				 
				 String trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
				 String allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
				 //Verify if ALL CHARACTERS section is displayed
				 boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
				 boolean presence=false;
				 if(trayPresence==true) {
					 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
					 for(int scroll=0;scroll<=1;scroll++) {
						 try {
							 driver.findElement(By.xpath(allCharFirstItem)).click();
							 test.log(LogStatus.INFO, "Clicked on show");	
							 presence=true;
							 break;
						 }
						 catch(Exception e) {
							 Utilities.verticalSwipe(driver);
							 if(scroll==1)
								 test.log(LogStatus.INFO, "Failed to click on show");	
						 }
					 }
					 if (presence==true) {
						 try {
							 //Scroll till Episodes tray
							 for(int scroll=0;scroll<=3;scroll++) {
								 if(Utilities.explicitWaitVisible(driver, watchpagev2.episodesTray, 10)) {
									 test.log(LogStatus.INFO, "Scrolled to Episodes tray");
									 Utilities.verticalSwipe(driver);
									 Utilities.verticalSwipe(driver);
									 Thread.sleep(2000);
									 break;
								 }
								 else {
									 Utilities.verticalSwipe(driver);
									 if(scroll==3)
										 test.log(LogStatus.FAIL, "Failed to scroll to EPISODES tray");
								 }
							 }
							 if(Utilities.explicitWaitVisible(driver, watchpagev2.episodesItemOneInEpisodeTray, 10)) {
								 String recentActivityFirstItemData = watchpagev2.episodesItemOneInEpisodeTray.getAttribute("text");
								 test.log(LogStatus.INFO, "First Episode is -> "+recentActivityFirstItemData);
								 if(Utilities.explicitWaitClickable(driver, watchpagev2.episodesItemOneInEpisodeTray, 10)) {
									 watchpagev2.episodesItemOneInEpisodeTray.click();
									 test.log(LogStatus.INFO, "Clicked on episode");

				
								 }
							 }
						 }
						 catch(Exception e){}
					 }
				
						  //Check for progress bar
						  homepagev2.verifyAndProgressBar();
						
						  //Pause the video
						  playerpagev2.pauseVideo();
					  
					//Check for settings icon	  				  
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerSettings, 10))
				  {
					  test.log(LogStatus.INFO, "Player Settings is displayed on the Player screen");
					  test.log(LogStatus.INFO, "Verifying UI  of the Settings overlay");
					  playerpagev2.playerSettings.click();
					//  System.out.println(driver.getPageSource());
					  
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguageList, 10))
					  {
						  String lang="";
						  test.log(LogStatus.INFO, "Select Language List button is displayed");
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedLanguage, 10))
						  {
							 lang=playerpagev2.defaultSelectedLanguage.getText();
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
							  for(int i=0;i<playerpagev2.playerSettingsSelectLanguages.size();i++)
							  {
								  selectedlang=playerpagev2.playerSettingsSelectLanguages.get(i).getText();
						          if(!selectedlang.equalsIgnoreCase(lang))						
						    	  {
						    	      test.log(LogStatus.INFO, "Selecting the language - "+selectedlang);
						    	      Thread.sleep(3000);
						    	      playerpagev2.playerSettingsSelectLanguages.get(i).click();
						    	      Thread.sleep(2000);
						    	      break;
						    	  }
						      }						  
							  
						  }
						  else if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsSelectSingleLanguage, 10))
						  {
							  for(int i=0;i<playerpagev2.playerSettingsSelectLanguages.size();i++)
							  {
								  selectedlang=playerpagev2.playerSettingsSelectLanguages.get(i).getText();
					    	      playerpagev2.playerSettingsSelectLanguages.get(i).click();
						         
						    	      break;
						    	  
						  }
						  }
						  else
							  BasePageV2.reportFail("Not abe to select language");
						  driver.runAppInBackground(Duration.ofSeconds(5));
							 driver.currentActivity();
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguagePopUp, 4))
						  {
						      test.log(LogStatus.FAIL, "Overlay message appears post changing the language once");
						      BasePageV2.takeScreenshot();
						  }
						  else
						  {
							  test.log(LogStatus.INFO, "Overlay message not appears post changing the language once");
							  test.log(LogStatus.PASS,"Testcase : 'Verify the pop-up message is displayed for 2nd time post accessing the language selection' is Passed");
							  BasePageV2.smokeresults("", postlangrow, "Pass");
							
						  }
					
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguageList, 10))
						  {
							  test.log(LogStatus.INFO, "Select Language List button is displayed");
							  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedLanguage, 10))
							  {
								   lang=playerpagev2.defaultSelectedLanguage.getText();
								  if(lang!=null && !lang.equals("")  && lang.equals(selectedlang))
								  {
									  test.log(LogStatus.INFO, "Selected Language is Displayed : "+lang);
									  test.log(LogStatus.PASS,"Testcase : 'Verify the language selection  functionality:' is Passed");
								  }
								  else
									test.log(LogStatus.FAIL, "Selected Language is not Displayed & Language displayed is - "+lang);
							  }
							  else
								  test.log(LogStatus.FAIL, "Selected language is not displayed ");
							  
						  }
						  else
							  test.log(LogStatus.FAIL, "Select Language List button is not displayed after selecting language");
						  
						  
					  }
					  else
						  test.log(LogStatus.FAIL, "Select Language List button is not displayed");
					  
				
					  BasePageV2.smokeresults("", langrow, "Pass");
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectQualityList, 10))
					  {
						  test.log(LogStatus.INFO, "Select Quality List button is displayed");
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedQuality, 10))
						  {
							  String quality=playerpagev2.defaultSelectedQuality.getText();
							  if(quality!=null && !quality.equals(""))
							  {
								  if(quality.equalsIgnoreCase("auto"))
								  {
									  test.log(LogStatus.INFO, "Default selected Quality is : "+quality);
									  test.log(LogStatus.PASS,"Testcase : 'Verify the quality settings by default in the settings screen:' is Passed");
									  BasePageV2.smokeresults("", qualityrow, "Pass");
								  }
								  else
								  {
									  test.log(LogStatus.FAIL, "Default selected Quality is  not auto: ");
								  }
							  }
							  else
								test.log(LogStatus.FAIL, "No default quality is selected ");
						  }
						  else
							  test.log(LogStatus.FAIL, "Default selected Quality is not displayed ");
						  
					  }
					  else
						  test.log(LogStatus.FAIL, "Select quality List button is not displayed");
					  
					  playerpagev2.selectQualityList.click();
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsAutoQuality, 20))
						  test.log(LogStatus.INFO, "Default Auto quality setting is displayed");
					  else
						  test.log(LogStatus.FAIL, "Auto is not displayed / Auto is not default");
					  
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsLowQuality, 20))
						  test.log(LogStatus.INFO, "Low quality setting is displayed");
					  else
						  test.log(LogStatus.FAIL, "Low quality is not displayed / Low is default");
					  
					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsHighQuality, 20))
						  test.log(LogStatus.INFO, "High quality setting is displayed");
					  else
						  test.log(LogStatus.FAIL, "High quality is not displayed / High is default");
					  
				  }
				  else
					  BasePageV2.reportFail("Player Settings is not displayed");
				  Thread.sleep(3000);
				     driver.runAppInBackground(Duration.ofSeconds(5));
					 driver.currentActivity();
			          if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsCloseButton, 10)) 
					  playerpagev2.playerSettingsCloseButton.click();
				      else
					  test.log(LogStatus.FAIL, "Close Button is not displayed in Select Quality Screen");
				  
				  driver.runAppInBackground(Duration.ofSeconds(5));
					 driver.currentActivity();
				  Thread.sleep(3000);
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsCloseButton, 10)) 
					  playerpagev2.playerSettingsCloseButton.click();
				  else
					  test.log(LogStatus.FAIL, "Close Button is not displayed in Settings screen");  
				  
				 test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the Stream Quality in the settings screen:' is Passed");
				 BasePageV2.smokeresults("", qualityuirow, "Pass");
				 test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the Settings overlay:' is Passed");
				 BasePageV2.smokeresults("", uirow, "Pass");
				 {
					 if(Utilities.explicitWaitClickable(driver, playerpagev2.playerSettings, 10))
					  {
					 test.log(LogStatus.PASS,"Testcase : 'Verify Close button functionality:' is Passed");
					 BasePageV2.smokeresults("", closerow, "Pass");
					  }
					 else
						 BasePageV2.reportFail("Clicking on close button not closing the settings screen / Player is not in paused state");
				 }
       }
	  else
		  BasePageV2.reportFail("Content is not played");
	
}

	
	
		  @DataProvider
			public Object[][] getData(){
				return DataUtil.getData(testName,xls);
						
			
			
		  }
		}