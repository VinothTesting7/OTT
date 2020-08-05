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
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifyVodPlayerLanguageSelectionPopUpFunctionality extends BaseTestV2{
	String testName = "VerifyVodPlayerLanguageSelectionPopUpFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Vod Player Language Selection PopUp/Dialog Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Language Selection PopUp functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int langrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",langrow, "Testcase : 'Verify the functionality post selecting any language:");	//P1
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int okrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",okrow, "Verify the functionality by tapping on 'Ok' button in pop-up");//P2

	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int selectrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",selectrow, "Verify the functionality by selecting any language");//P1
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	 String timebefPause="";

		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
	 //sign up 
	// homepagev2.signup();
	 
	 //Click on Watch tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
		 
			homepagev2.tabClick("Watch");
			//Get tray name from API
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
				  
				  test.log(LogStatus.INFO, "Navigating to Player Settings screen");
					//Check for settings icon	  				  
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerSettings, 10))
				  {
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
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguagePopUp, 30))
						  {
							  test.log(LogStatus.INFO, "'Go to Settings to set "+selectedlang+" as your preferred language' message is displayed");
							  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguagePopUpCloseDialog, 30))
							  {
								  test.log(LogStatus.INFO, "Close dialog button is displayed");
								
							  }
							  else
								  BasePageV2.reportFail("Close dialog button is not displayed");
							  
							  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguagePopUpOkButton, 30))
							  {
								  test.log(LogStatus.INFO, "Ok button is displayed");
								  test.log(LogStatus.PASS,"Testcase : 'Verify the functionality post selecting any language:' is Passed");
							      BasePageV2.smokeresults("", langrow, "Pass");
							  }
							  else
								  BasePageV2.reportFail("Ok button is not displayed in Language selection popup");
						  }
						  else
							  BasePageV2.reportFail("'Go to Settings to set "+lang+" as your preferred language' message is not displayed");
					 
					     test.log(LogStatus.INFO, "Verify the functionality of Settings button under Languges option:");
					     try{
					    	 playerpagev2.selectLanguagePopUpOkButton.click();
					     } 
					     catch(Exception e){
					    	 test.log(LogStatus.FAIL, "Ok button is not Clickable");
					     }
					     driver.runAppInBackground(Duration.ofSeconds(5));
						 driver.currentActivity();
					     if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguageList, 10))
						  {
					    	  test.log(LogStatus.INFO, "Clicking on Ok button landed to  Select language screen");
							  test.log(LogStatus.PASS,"Testcase : 'Verify the functionality by tapping on 'Ok' button in pop-up' is Passed");
							  BasePageV2.smokeresults("", okrow, "Pass");		
						  }
					     else
							  BasePageV2.reportFail("Clicking on Ok button not landed to Select language Screen");
					 
						 
					     if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSettingsCloseButton, 10))
						  {
					    	  test.log(LogStatus.INFO, "Closing player settings");
					    	  playerpagev2.playerSettingsCloseButton.click();
							  	
						  }
					     else
							  BasePageV2.reportFail("Not able to click on Player Settings close button");
					 
					     if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCloseButton, 10))
						  {
					    	  test.log(LogStatus.INFO, "Closing the player");
					    	  playerpagev2.playerCloseButton.click();
							  	
						  }
					     else
							  BasePageV2.reportFail("Not able to click on Player Settings close button");
					  
	
					     
	 if(Utilities.explicitWaitClickable(driver, watchpagev2.episodesItemOneInEpisodeTray, 10)) {
		 watchpagev2.episodesItemOneInEpisodeTray.click();
		 test.log(LogStatus.INFO, "Clicked on episode");


	 }
					     
					     
					    	 test.log(LogStatus.INFO, "Play the same previously played content from carousal");
					    	 
					    	  if(Utilities.explicitWaitVisible(driver,playerpagev2.videoPlayer, 3)) {
					    				  
					    				  //Check for progress bar
					    				  homepagev2.verifyAndProgressBar();		
					    				  
					    				  //Pause the video				  
					    				  playerpagev2.pauseVideo();
					    				  
					    				  test.log(LogStatus.INFO, "Navigating to Player Settings screen");
					    					//Check for settings icon	  				  
					    				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerSettings, 10))
					    				  {
					    					  playerpagev2.playerSettings.click();
					    					//  System.out.println(driver.getPageSource());
					    					  
					    					  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguageList, 10))
					    					  {
					    						String lang2="";
					    						  test.log(LogStatus.INFO, "Verifying language selection functionality");
					    						  test.log(LogStatus.INFO, "Select Language List button is displayed");
					    						  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedLanguage, 10))
					    						  {
					    							  lang2=playerpagev2.defaultSelectedLanguage.getText();
					    							  if(lang2.equalsIgnoreCase(selectedlang))
					    							  {
					    								  test.log(LogStatus.INFO, " content played in selected langauage "+lang);
					    								  test.log(LogStatus.PASS,"Testcase : 'Verify the functionality by selecting any language ' is Passed");
					    							      BasePageV2.smokeresults("", selectrow, "Pass");
					    							  }
					    							  else
					    								test.log(LogStatus.FAIL, "content not played  in selected language");
					    						
					    						  }
					    						  else
					    							  test.log(LogStatus.FAIL, "default selected language is not displayed ");
					     
					     
					     
					     
					     
					     
					  }
					  
					  
				  }
				  
	          }
					  }
						 
					  }}}}
	 
	
	
	
	
	

					  @DataProvider
						public Object[][] getData(){
							return DataUtil.getData(testName,xls);
									
						
						
					  }
					}					  
					  