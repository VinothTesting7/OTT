package com.viacom.movies;
import java.time.Duration;
//Author Tanisha
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.MoviesPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifyMoviePlayerFunctionalityTest extends BaseTestV2 {
	String testName = "VerifyMoviePlayerFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("VerifyMoviePlayerFunctionalityTest");
	test = rep.startTest("VerifyMoviePlayerFunctionalityTest");

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	//VK_2075 Verify Pause button functionality
	//VK_2076 Verify Play button functionality
	//VK_2079 Verify Seekbar functionality
	//VK_2084 Verify the language selection functionality
	//VK_2085 Verify the functionality post selecting any language
	//VK_2095 Verify Close icon functionality
	//VK_2082 Verify Settings/Options button functionality
	//VK_2083 Verify Settings - Close button functionality
	//VK_2086 Verify the UI of the Stream Quality in the settings screen
	//VK_2087 Verify the quality settings by default in the settings screen

	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	MoviesPageV2 moviespagev2=new MoviesPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	//homepagev2.login(un, pwd);
	
	String trayName="";
	String trayNameUpper="";
	String trayNameCamel="";
	String movieTitle="";
	boolean playButtonClicked=false;
	String pausedTime1="",pausedTime2="";
	String playTime1="", playTime2="";
	boolean playButtonTapped=false;
	boolean slidehalfInPlayer=false;
	String selectedlang="";
	String lang="";
	int languageCount=0;
	boolean singleLang=false;
	boolean manyLang=false;
	String optionsLangBefore="";
	String optionsLangAfter="";
	boolean clickedStreamQuality=false;
	int err2086=0;
	
	//Hit Watch API and find the name of the Movies tray
	RestAssured.baseURI="https://api.vootkids.com/app/ui/v1/tabs/watch.json?limit=10&offSet=0";
	Response watchTabResponse =(Response) RestAssured.given().relaxedHTTPSValidation().get().body();
	int responseItems=watchTabResponse.jsonPath().get("assets.size()");
	boolean foundMoviesTrayFromAPI=false;
	for(int traverseResponse=0;traverseResponse<responseItems;traverseResponse++) {
		if(watchTabResponse.jsonPath().get("assets["+traverseResponse+"].trayName").equals("featuredMovies")) {
			trayName=watchTabResponse.jsonPath().get("assets["+traverseResponse+"].title");
			test.log(LogStatus.INFO, "Movie tray name fetched from Watch API is: \""+trayName+"\"");
			foundMoviesTrayFromAPI=true;
			break;
		}		
	}
	if(foundMoviesTrayFromAPI==false) {
		test.log(LogStatus.INFO, "Failed to fetch Movie tray name from Watch API");
	}
	
	if(foundMoviesTrayFromAPI==true) {
		homepagev2.tabClick("Watch");
		trayNameUpper=trayName.toUpperCase();
		trayNameCamel=homepagev2.convertCamelCase(trayName);
		String movieFirstItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup";
		boolean movieFirstItemClicked=false;
		for(int scroll=0;scroll<=50;scroll++) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(movieFirstItem)).click();
				test.log(LogStatus.INFO, "Clicked on first item from \""+trayName+"\" tray");
				movieFirstItemClicked=true;
				break;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				if(scroll==50) {
					test.log(LogStatus.FAIL, "Failed to click on first item from \""+trayName+"\" tray");
					homepagev2.takeScreenshot();
				}
			}
		}
		if(movieFirstItemClicked==true) {
			//Get the name of the movie
			Utilities.verticalSwipe(driver);
			Thread.sleep(1000);
			if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieTitle, 10)) {
				movieTitle= moviespagev2.movieDetailsPageMovieTitle.getAttribute("text");
				test.log(LogStatus.INFO, "Movie title: \""+movieTitle+"\"");
				
			}
			else {
				test.log(LogStatus.FAIL, "Failed to locate Movie title"); 
			}
			//Tap on Play button
			if(Utilities.explicitWaitClickableNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
				try {
					moviespagev2.movieDetailsPagePlayButton.click();
					test.log(LogStatus.INFO, "Tapped on PLAY button");
					playButtonClicked=true;
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to tap on PLAY button");
				}
			}
			if(playButtonClicked==true) {
				//Check for progress bar
				homepagev2.verifyAndProgressBar();
				//Pause the movie
				playerpagev2.pauseVideo();
				//Verifying Pause Functionality
				if(Utilities.explicitWaitVisible(driver, moviespagev2.playerCurrentDuration, 10)){
					pausedTime1=moviespagev2.playerCurrentDuration.getText();
					test.log(LogStatus.INFO, "Duration watched displayed on the Player screen after Pausing movie: "+pausedTime1);
					Thread.sleep(3000);
					test.log(LogStatus.INFO, "Waited for 3 seconds");
					pausedTime2=moviespagev2.playerCurrentDuration.getText();
					test.log(LogStatus.INFO, "Duration watched displayed on the Player screen after waiting for 3 seconds: "+pausedTime2);
					if(pausedTime1.equals(pausedTime2)){
						test.log(LogStatus.PASS,"Verify Pause button functionality is Pass");
						if(!Utilities.setResultsKids("VK_2075", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					else {
						test.log(LogStatus.FAIL,"Verify Pause button functionality is Fail");
						if(!Utilities.setResultsKids("VK_2075", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					//Verifying Play Functionality
					playTime1=pausedTime2;
					test.log(LogStatus.INFO, "Duration watched displayed on the Player screen before playing movie: "+playTime1);
					//Play the movie
					if(Utilities.explicitWaitClickableNew(driver, moviespagev2.playPauseButton, 10)) {
						try {
							moviespagev2.playPauseButton.click();
							test.log(LogStatus.INFO, "Tapped on Play button of the player");
							playButtonTapped=true;
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to tap on Play button of the player");
						}
					}
					else {
						test.log(LogStatus.FAIL, "Play button on player is not clickable");
					}
					if(playButtonTapped==true) {
						homepagev2.verifyAndProgressBar();
						//Pause the video
						playerpagev2.pauseVideo();
						if(Utilities.explicitWaitVisible(driver, moviespagev2.playerCurrentDuration, 10)){
							playTime2=moviespagev2.playerCurrentDuration.getText();
							test.log(LogStatus.INFO, "Duration watched displayed on the Player screen after playing for 3 seconds: "+playTime2);
							if(playTime1.equals(playTime2)){
								test.log(LogStatus.FAIL,"Verify Play button functionality is Fail");
								if(!Utilities.setResultsKids("VK_2076", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							else {
								test.log(LogStatus.PASS,"Verify Play button functionality is Pass");
								if(!Utilities.setResultsKids("VK_2076", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
						}
						else
							test.log(LogStatus.FAIL, "Duration watched is not displayed");		
					}
				}
				else
					test.log(LogStatus.FAIL, "Duration watched is not displayed");	
				//Scrub functionality
				slidehalfInPlayer=playerpagev2.slidehalfInPlayer(driver);
				if(slidehalfInPlayer==true) {
					test.log(LogStatus.PASS,"Verify Seekbar functionality is Pass");
					if(!Utilities.setResultsKids("VK_2079", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL,"Verify Seekbar functionality is Fail");
					if(!Utilities.setResultsKids("VK_2079", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				//Language dropdown
				if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerSettings, 10)){
					playerpagev2.playerSettings.click();
					if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageTitle, 10)) {
						test.log(LogStatus.INFO, "Options page is displayed");
						test.log(LogStatus.PASS, "Verify Settings/Options button functionality is Pass");	
						if(!Utilities.setResultsKids("VK_2082", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					else {
						test.log(LogStatus.INFO, "Failed to display Options page");
						test.log(LogStatus.PASS, "Verify Settings/Options button functionality is Fail");	
						if(!Utilities.setResultsKids("VK_2082", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageLanguage, 10)) {
						try {
							optionsLangBefore=moviespagev2.optionsPageLanguage.getAttribute("text");
							test.log(LogStatus.INFO, "Language displayed in 'Options' page: "+optionsLangBefore);
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to fetch the language in 'Options' page");
						}
					}
					else {
						test.log(LogStatus.FAIL, "Language is not displayed in Options page");
					}
					if(Utilities.explicitWaitClickableNew(driver, moviespagev2.selectLanguageDropdown, 10)){
						moviespagev2.selectLanguageDropdown.click();
						test.log(LogStatus.INFO, "Clicked on Select Language dropdown");
						if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.selectLangPageTitle, 10)) {
							test.log(LogStatus.INFO, "Navigated to Select Language page");
							test.log(LogStatus.PASS, "Verify the language selection functionality is Pass");	
							if(!Utilities.setResultsKids("VK_2084", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else {
							test.log(LogStatus.INFO, "Failed to navigated to Select Language page");
							test.log(LogStatus.PASS, "Verify the language selection functionality is Fail");	
							if(!Utilities.setResultsKids("VK_2084", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.defaultSelectedLanguage, 10)) {
							selectedlang=moviespagev2.defaultSelectedLanguage.getText();
							test.log(LogStatus.INFO, "Language selected in 'Select Language' page: "+selectedlang);
						}
						else {
							test.log(LogStatus.INFO, "Selected Language is not displayed in 'Select Language' page");
						}
						try {
							languageCount=moviespagev2.availableLanguages.size();
							if(languageCount>1) {
								test.log(LogStatus.INFO, "Languages present are: ");
								for(int i=0;i<languageCount;i++) {
									test.log(LogStatus.INFO, ""+moviespagev2.availableLanguages.get(i).getAttribute("text"));
								}
								manyLang=true;
							}
						    if(languageCount==1) {
							    test.log(LogStatus.INFO, "Movie available in only one language: "+moviespagev2.availableLanguages.get(0).getAttribute("text"));
							    singleLang=true;
						    }
						    else {
							  test.log(LogStatus.FAIL, "Some error in fetching language list");
						    }
						}
						catch(Exception e) {
							  test.log(LogStatus.FAIL, "Failed to fetch language list");
						}
						if(manyLang==true) {
						   if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.playerSettingsSelectLanguage, 10)){
							  for(int i=0;i<playerpagev2.playerSettingsSelectLanguages.size();i++){
								  selectedlang=playerpagev2.playerSettingsSelectLanguages.get(i).getText();
						          if(!selectedlang.equalsIgnoreCase(lang)){
						    	      test.log(LogStatus.INFO, "Selecting the language - "+selectedlang);
						    	      Thread.sleep(3000);
						    	      playerpagev2.playerSettingsSelectLanguages.get(i).click();
						    	      optionsLangAfter=selectedlang;
						    	      Thread.sleep(2000);
						    	      break;
						    	  }
							  }  
						   } 
						   if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.selectLanguagePopUp, 30)){
								test.log(LogStatus.INFO, "'Go to Settings to set "+selectedlang+" as your preferred language' message is displayed");
								test.log(LogStatus.PASS, "Verify the functionality post selecting any language is Pass");	
								if(!Utilities.setResultsKids("VK_2085", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.selectLanguagePopUpCloseDialog, 30)){
									test.log(LogStatus.INFO, "Close dialog button is displayed");
								}
								else
									test.log(LogStatus.FAIL,"Close dialog button is not displayed in pop up");
								if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.selectLanguagePopUpOkButton, 30)){
									try{
								    	playerpagev2.selectLanguagePopUpOkButton.click();
								    	test.log(LogStatus.INFO, "Clicked on OK button of Pop up");
								    	driver.runAppInBackground(Duration.ofSeconds(5));
										driver.currentActivity();
								    	if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageTitle, 10)) {
								    		test.log(LogStatus.INFO, "OPTIONS page is displayed, OK button functionality is fine");
								    	}
								    	else {
								    		test.log(LogStatus.FAIL, "OPTIONS page is not displayed, OK button functionality fails");
								    	}
								    } 
								    catch(Exception e){
								    	test.log(LogStatus.FAIL, "Failed to click on OK button of Pop up");
								    }
								}
								else
									test.log(LogStatus.FAIL, "Ok button is not displayed in Language selection popup");
						   }
						   else {
							   test.log(LogStatus.WARNING, "'Go to Settings to set "+lang+" as your preferred language' message did not get displayed");
							   test.log(LogStatus.WARNING, "Verify the functionality post selecting any language is Unable to Test because the pop up might have displayed in previous execution run");
							   if(!Utilities.setResultsKids("VK_2085", "Unable to Test")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						   }
						   if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageTitle, 10)){
								 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageLanguage, 10)) {
									try {
										optionsLangAfter=moviespagev2.optionsPageLanguage.getAttribute("text");
										test.log(LogStatus.INFO, "Language displayed in Options page after selection: "+optionsLangAfter);
										if(optionsLangBefore.equals(optionsLangAfter)) {
											test.log(LogStatus.INFO, "Previous displayed language is displayed after selecting same language from Language selection list");
											test.log(LogStatus.INFO, "Language selection verified for Single language only");
										}
										else {
											test.log(LogStatus.FAIL, "Language before and after language selection is different even though there is only one language");
										}
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to fetch the language displayed in Options page");
									}
								 }
								 else {
										test.log(LogStatus.FAIL, "Language is not displayed in Options page");
								 }
							}
							else {
								 test.log(LogStatus.FAIL, "Language selection functionality could not be verified");
							}
						}
						if(singleLang==true) {
							 try {
								 moviespagev2.defaultSelectedLanguage.click();
								 test.log(LogStatus.INFO, "Tapped on the same language: "+selectedlang);
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "Failed to tap on the same language: "+selectedlang);
							 }
							 if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.selectLanguagePopUp, 30)){
									test.log(LogStatus.INFO, "'Go to Settings to set "+selectedlang+" as your preferred language' message is displayed");
									test.log(LogStatus.PASS, "Verify the functionality post selecting any language is Pass");	
									if(!Utilities.setResultsKids("VK_2085", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.selectLanguagePopUpCloseDialog, 30)){
										test.log(LogStatus.INFO, "Close dialog button is displayed");
									}
									else
										test.log(LogStatus.FAIL,"Close dialog button is not displayed in pop up");
									if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.selectLanguagePopUpOkButton, 30)){
										try{
									    	playerpagev2.selectLanguagePopUpOkButton.click();
									    	test.log(LogStatus.INFO, "Clicked on OK button of Pop up");
									    	driver.runAppInBackground(Duration.ofSeconds(5));
											driver.currentActivity();
									    	if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageTitle, 10)) {
									    		test.log(LogStatus.INFO, "OPTIONS page is displayed, OK button functionality is fine");
									    	}
									    	else {
									    		test.log(LogStatus.FAIL, "OPTIONS page is not displayed, OK button functionality fails");
									    	}
									    } 
									    catch(Exception e){
									    	test.log(LogStatus.FAIL, "Failed to click on OK button of Pop up");
									    }
									}
									else
										test.log(LogStatus.FAIL, "Ok button is not displayed in Language selection popup");
							  }
							  else {
								   test.log(LogStatus.WARNING, "'Go to Settings to set "+lang+" as your preferred language' message did not get displayed");
								   test.log(LogStatus.WARNING, "Verify the functionality post selecting any language is Unable to Test because the pop up might have displayed in previous execution run");
								   if(!Utilities.setResultsKids("VK_2085", "Unable to Test")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							  }
							  if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageTitle, 10)){
								 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.optionsPageLanguage, 10)) {
									try {
										optionsLangAfter=moviespagev2.optionsPageLanguage.getAttribute("text");
										test.log(LogStatus.INFO, "Language displayed in Options page after selection: "+optionsLangAfter);
										if(optionsLangBefore.equals(optionsLangAfter)) {
											test.log(LogStatus.INFO, "Previous displayed language is displayed after selecting same language from Language selection list");
											test.log(LogStatus.INFO, "Language selection verified for Single language only");
										}
										else {
											test.log(LogStatus.FAIL, "Language before and after language selection is different even though there is only one language");
										}
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to fetch the language displayed in Options page");
									}
								 }
								 else {
										test.log(LogStatus.FAIL, "Language is not displayed in Options page");
								 }
							  }
							  else {
								 test.log(LogStatus.FAIL, "Language selection functionality could not be verified");
							  }
						 }  
					}
					//Verify stream quality options
					 if(Utilities.explicitWaitVisible(driver, moviespagev2.moviePlayerSettingsStreamQualityText, 10)) {
						 test.log(LogStatus.INFO, "Stream Quality. text is displayed");
						 if(Utilities.explicitWaitVisible(driver, moviespagev2.moviePlayerSettingsSelectQualityDefault, 10)) {
							 test.log(LogStatus.INFO, "Auto as default selection is displayed");
							 test.log(LogStatus.PASS, "Verify the quality settings by default in the settings screen is Pass"); 
							 if(!Utilities.setResultsKids("VK_2087", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
						 else {
							 test.log(LogStatus.FAIL, "Auto as default selection is NOT displayed");
							 test.log(LogStatus.PASS, "Verify the quality settings by default in the settings screen is Fail"); 
							 if(!Utilities.setResultsKids("VK_2087", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Stream Quality. text is not displayed");
					 }
					 if(Utilities.explicitWaitClickable(driver, moviespagev2.moviePlayerSettingsStreamQualityText, 5)) {
						 try {
							 moviespagev2.moviePlayerSettingsStreamQualityText.click();
							 test.log(LogStatus.INFO, "Clicked on Stream Quality button");
							 clickedStreamQuality=true;
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Failed to click on Stream Quality button");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Stream Quality button is not clickable");
					 }
					 if(clickedStreamQuality==true) {
						 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.moviePlayerSettingsStreamQualityTitle, 10)) {	
							 test.log(LogStatus.INFO, "Stream Quality page is displayed");
							 if(Utilities.explicitWaitVisible(driver, moviespagev2.moviePlayerSettingsStreamQualityAuto, 10)) {
									test.log(LogStatus.INFO, "Auto option is displayed");
								}
								else {
									test.log(LogStatus.FAIL, "Auto option is NOT displayed");
									err2086++;
								}
								if(Utilities.explicitWaitVisible(driver, moviespagev2.moviePlayerSettingsStreamQualityLow, 10)) {
									test.log(LogStatus.INFO, "Low option is displayed");
								}
								else {
									test.log(LogStatus.FAIL, "Low option is NOT displayed");
									err2086++;
								}
								if(Utilities.explicitWaitVisible(driver, moviespagev2.moviePlayerSettingsStreamQualityMedium, 10)) {
									test.log(LogStatus.INFO, "Medium option is displayed");
								}
								else {
									test.log(LogStatus.FAIL, "Medium option is NOT displayed");
									err2086++;
								}
								if(Utilities.explicitWaitVisible(driver, moviespagev2.moviePlayerSettingsStreamQualityHigh, 10)) {
									test.log(LogStatus.INFO, "High option is displayed");
								}
								else {
									test.log(LogStatus.FAIL, "High option is NOT displayed");
									err2086++;
								}
								if(err2086==0) {
									test.log(LogStatus.PASS, "Verify the UI of the Stream Quality in the settings screen is Pass"); 
									if(!Utilities.setResultsKids("VK_2086", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								}
								else{
									test.log(LogStatus.FAIL, "Verify the UI of the Stream Quality in the settings screen is Fail"); 
									if(!Utilities.setResultsKids("VK_2086", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								}
								//Click on Close button of Stream Quality page
								if(Utilities.explicitWaitClickable(driver, moviespagev2.moviePlayerSettingsStreamQualityClose, 10)) {
									try {
										moviespagev2.moviePlayerSettingsStreamQualityClose.click();
										test.log(LogStatus.INFO, "Clicked on Close button of STREAM QUALITY page");
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to click on Close button of STREAM QUALITY page");
									}
								}
								else {
									test.log(LogStatus.FAIL, "Close button of STREAM QUALITY page is not clickable");
								}
						 }
						 else {
							 test.log(LogStatus.FAIL, "Stream Quality page is not displayed");
						 }
					}
					if(Utilities.explicitWaitClickableNew(driver, moviespagev2.optionsPageClose, 10)) {
						try {
							moviespagev2.optionsPageClose.click();
							test.log(LogStatus.INFO, "Clicked on Close button of Options page");
							if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.videoPlayer, 10)) {
								test.log(LogStatus.INFO, "Movie player is displayed");
						    	test.log(LogStatus.PASS, "Verify Settings - Close button functionality is Pass");
						    	if(!Utilities.setResultsKids("VK_2083", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						    }
						    else {
						    	test.log(LogStatus.INFO, "Movie player is not displayed");
						    	test.log(LogStatus.FAIL, "Verify Settings - Close button functionality is Fail");
						    	if(!Utilities.setResultsKids("VK_2083", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						    }
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to click on Close button of Options page");
						}
					}
					else {
						test.log(LogStatus.INFO, "Close button of Options page is not clickable");
						test.log(LogStatus.FAIL, "Verify Settings - Close button functionality is Fail");
					    if(!Utilities.setResultsKids("VK_2083", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					//Verifying Close button	
					if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerCloseButton, 10)){
						playerpagev2.playerCloseButton.click();
						test.log(LogStatus.INFO, "Clicked on Close button of player");
					}
					else{
						try {
							playerpagev2.pauseVideo();
							test.log(LogStatus.INFO, "Paused the video");
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to pause movie");
						}
						if(Utilities.explicitWaitClickableNew(driver, playerpagev2.playerCloseButton, 10)) {
							playerpagev2.playerCloseButton.click();
							test.log(LogStatus.INFO, "Clicked on Close button of player");
						}
						else {
							test.log(LogStatus.FAIL, "Close button is not displayed in Player");
						}
					}
				    if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.videoPlayer, 10)) {
				    	test.log(LogStatus.INFO, "Movie player is displayed");
				    	test.log(LogStatus.FAIL, "Verify Close icon functionality is FAIL");
				    	if(!Utilities.setResultsKids("VK_2095", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				    }
				    else {
				    	test.log(LogStatus.INFO, "Movie player is not displayed");
				    	test.log(LogStatus.PASS, "Verify Close icon functionality is Pass");
				    	if(!Utilities.setResultsKids("VK_2095", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				    }
				}
			}
		}
	}
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}