package com.viacom.audioplayer;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
//Author Tanisha
public class VerifyAudioPlayerSettings extends BaseTestV2{
	
	String testName = "VerifyAudioPlayerSettings";
	@Test(dataProvider = "getData")
	public void VerifyAudioPlayerSettings(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyAudioPlayerSettings");
		test.log(LogStatus.INFO, "Verify Audio Player Settings");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		// VK_550 Verify the UI of the Settings overlay
		// VK_551 Verify the Navigation by tapping on stream quality option
		// VK_552 Verify the UI of the Stream Quality in the settings screen
		// VK_553 Verify the default quality settings in the settings screen
		// VK_558 Verify Close button functionality
		// VK_570 Verify the Audio book title & Author name of the playing content is being displayed in audio player in locked screen of device
		// VK_592 Verify card navigation

		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 int err550=0;
		 int err552=0;
		 int err570=0;
		 int err592=0;
		 String firstItemNameUI="";
		 String firstItemNameUICamel="";
		 String firstItemNameUIUpper="";
		 String firstItemAuthorUI="";
		 String firstItemAuthorUICamel="";
		 String firstItemAuthorUIUpper="";
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 //System.out.println(driver.getPageSource());
		 }
		
		//Verification of 551
		//Click on Listen tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10)) {
			 homepagev2.Listen_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Listen tab");
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstItemCarousal, 10)) {
				 try {
					 listenpagev2.firstItemCarousal.click();
					 test.log(LogStatus.INFO, "Clicked on first audio in Carousal");
					 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioDetailsAudioName, 10)) {
						 firstItemNameUI=listenpagev2.audioDetailsAudioName.getAttribute("text");
						 firstItemNameUICamel=homepagev2.convertCamelCase(firstItemNameUI);
						 firstItemNameUIUpper=firstItemNameUI.toUpperCase();
						 test.log(LogStatus.INFO, "Audio name fetched from Audio Details page: "+firstItemNameUI);
					 }
					 else {
						 test.log(LogStatus.FAIL, "Unable to fetch Audio name from Audio Details page");
					 }
					 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioDetailsAuthorName, 10)) {
						 firstItemAuthorUI=listenpagev2.audioDetailsAuthorName.getAttribute("text");
						 firstItemAuthorUICamel=homepagev2.convertCamelCase(firstItemAuthorUI);
						 firstItemAuthorUIUpper=firstItemAuthorUI.toUpperCase();
						 test.log(LogStatus.INFO, "Author name fetched from Audio Details page: "+firstItemAuthorUI);
					 }
					 else {
						 test.log(LogStatus.FAIL, "Unable to fetch Author name from Audio Details page");
					 }
					 if(Utilities.explicitWaitClickable(driver, listenpagev2.play_btn, 10)) {
						 try {
							 listenpagev2.play_btn.click();
							 test.log(LogStatus.INFO, "Clicked on PLAY button");
							 Thread.sleep(3000);
							 
							 if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerSettings, 10)) {
								 try {
									 listenpagev2.audioPlayerSettings.click();
									 test.log(LogStatus.INFO, "Clicked on Settings button in Audio player");
									 basepagev2.takeScreenshot();
									 //Click on Close button
									 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsClose, 10)) {
										 try {
												driver.findElement(By.id("com.viacom18.vootkids:id/player_setting_close_btn")).click();
												test.log(LogStatus.INFO, "Clicked on Close button of OPTIONS page");
												if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettings, 10)) {
													test.log(LogStatus.INFO, "Audio Player is displayed");
													test.log(LogStatus.PASS, "Audio Player Settings: Verify Close button functionality is PASS"); 
													if(!Utilities.setResultsKids("VK_558", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												}
												else {
													test.log(LogStatus.FAIL, "Close button of OPTIONS page is not clickable");
													driver.navigate().back();
													test.log(LogStatus.INFO, "Navigated back");
												}	 
												 if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerSettings, 10)) {
													 try {
														 listenpagev2.audioPlayerSettings.click();
														 test.log(LogStatus.INFO, "Clicked on Settings button in Audio player");
													 }
													 catch(Exception e) {
														 test.log(LogStatus.INFO, "Failed to click on Settings button in Audio player");
													 }
												 }
												
												
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to click on Close button of OPTIONS page");
											}
									 }
									 else {
											test.log(LogStatus.FAIL, "Audio Player Settings: Verify Close button functionality is FAIL"); 
											if(!Utilities.setResultsKids("VK_558", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											basepagev2.takeScreenshot();
									 }	
									 
									 //Verify the UI
									 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsOptions, 10))
										 test.log(LogStatus.INFO, "OPTIONS text is verified present");
									 else {
										 test.log(LogStatus.FAIL, "OPTIONS text is NOT present");
										 err550++;
									 }
									 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsClose, 10))
										 test.log(LogStatus.INFO, "Close icon is verified present");
									 else {
										 test.log(LogStatus.FAIL, "Close icon is NOT present");
										 err550++;
									 }
									 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsDivider, 10))
										 test.log(LogStatus.INFO, "Dotted lines is verified present");
									 else {
										 test.log(LogStatus.FAIL, "Dotted lines is NOT present");
										 err550++;
									 }
									 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsStreamQualityText, 10))
										 test.log(LogStatus.INFO, "Stream Quality. text is verified present");
									 else {
										 test.log(LogStatus.FAIL, "Stream Quality. text is NOT present");
										 err550++;
									 }
									 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsSelectQualityDefault, 10)) {
										 test.log(LogStatus.INFO, "Auto as default selection is verified present");
										 test.log(LogStatus.PASS, "Audio Player Settings: Verify the default quality settings in the settings screen is PASS"); 
										 if(!Utilities.setResultsKids("VK_553", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 }
									 else {
										 test.log(LogStatus.FAIL, "Auto as default selection is NOT present");
										 err550++;
										 test.log(LogStatus.FAIL, "Audio Player Settings: Verify the default quality settings in the settings screen is FAIL"); 
										 if(!Utilities.setResultsKids("VK_553", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");

									 }
									 if(err550==0) {
										test.log(LogStatus.PASS,"Audio Player Settings: Verify the UI of the Settings overlay is PASS"); 
										if(!Utilities.setResultsKids("VK_550", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										
									 }
									 else {
										test.log(LogStatus.FAIL, "Audio Player Settings: Verify the UI of the Settings overlay is FAIL"); 
										if(!Utilities.setResultsKids("VK_550", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 }
									 if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerSettingsStreamQualityText, 5)) {
										 try {
											 listenpagev2.audioPlayerSettingsStreamQualityText.click();
											 test.log(LogStatus.INFO, "Clicked on Stream Quality button");
											 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsStreamQualityTitle, 10)) {	
												if(listenpagev2.audioPlayerSettingsStreamQualityTitle.getAttribute("text").equalsIgnoreCase("STREAM QUALITY")) {
													test.log(LogStatus.INFO, "Page title is verified to be STREAM QUALITY");
													test.log(LogStatus.PASS, "Audio Player Settings: Verify the Navigation by tapping on stream quality option is PASS"); 
													if(!Utilities.setResultsKids("VK_551", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
													if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsStreamQualityAuto, 10)) {
														test.log(LogStatus.INFO, "Auto option is displayed");
													}
													else {
														test.log(LogStatus.FAIL, "Auto option is NOT displayed");
														err552++;
													}
													if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsStreamQualityLow, 10)) {
														test.log(LogStatus.INFO, "Low option is displayed");
													}
													else {
														test.log(LogStatus.FAIL, "Low option is NOT displayed");
														err552++;
													}
													if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsStreamQualityMedium, 10)) {
														test.log(LogStatus.INFO, "Medium option is displayed");
													}
													else {
														test.log(LogStatus.FAIL, "Medium option is NOT displayed");
														err552++;
													}
													if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsStreamQualityHigh, 10)) {
														test.log(LogStatus.INFO, "High option is displayed");
													}
													else {
														test.log(LogStatus.FAIL, "High option is NOT displayed");
														err552++;
													}
													if(err552==0) {
														test.log(LogStatus.PASS, "Audio Player Settings: Verify the UI of the Stream Quality in the settings screen is PASS"); 
														if(!Utilities.setResultsKids("VK_552", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
													}
													else{
														test.log(LogStatus.FAIL, "Audio Player Settings: Verify the UI of the Stream Quality in the settings screen is FAIL"); 
														if(!Utilities.setResultsKids("VK_552", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
													}
													//Click on Close button of Stream Quality page
													if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerSettingsStreamQualityClose, 10)) {
														try {
															listenpagev2.audioPlayerSettingsStreamQualityClose.click();
															test.log(LogStatus.INFO, "Clicked on Close button of STREAM QUALITY page");
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Failed to click on Close button of STREAM QUALITY page");
														}
													}
													else {
														test.log(LogStatus.FAIL, "Close button of STREAM QUALITY page is not clickable");
													}
													if(!Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerSettingsOptions, 10)) {
														test.log(LogStatus.FAIL, "Failed to click on Close button of STREAM QUALITY page, hence navigating back");
														driver.navigate().back();
													}
													//Click on Close button of Options page
													if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerSettingsClose, 10)) {
														try {
															listenpagev2.audioPlayerSettingsClose.click();
															test.log(LogStatus.INFO, "Clicked on Close button of OPTIONS page");
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Failed to click on Close button of OPTIONS page");
														}
													}
													else {
														test.log(LogStatus.FAIL, "Close button of OPTIONS page is not clickable");
													}
													if(!Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerUpArrowExpand, 10)) {
														test.log(LogStatus.FAIL, "Failed to click on Close button of OPTIONS page, hence navigating back");
														driver.navigate().back();
													}
													//Verify 593
													String moreFromAuthorItemNameUI="";
													String moreFromAuthorItemAuthorUI="";
													if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerUpArrowExpand, 20)) {
														try {
															listenpagev2.audioPlayerUpArrowExpand.click();
															test.log(LogStatus.INFO, "Clicked on the Up arrow in audio player");
															if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerMoreFromAuthor, 10)) {
																try {
																	listenpagev2.audioPlayerMoreFromAuthor.click();
																	test.log(LogStatus.INFO, "Clicked on More From Author");
																	if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerMorFrmAuthrFirstAudioName, 10)) {
																		moreFromAuthorItemNameUI=listenpagev2.audioPlayerMorFrmAuthrFirstAudioName.getAttribute("text");
																		test.log(LogStatus.INFO, "First Audio file Name under 'More From Author' is: "+moreFromAuthorItemNameUI);
																		moreFromAuthorItemAuthorUI=listenpagev2.audioPlayerMorFrmAuthrFirstAudioAuthor.getAttribute("text");
																		test.log(LogStatus.INFO, "First Audio file Author under 'More From Author' is: "+moreFromAuthorItemAuthorUI);
																		try {
																			listenpagev2.audioPlayerMorFrmAuthrFirstAudioName.click();
																			test.log(LogStatus.INFO, "Clicked on first audio under 'More From Author' tab "+moreFromAuthorItemNameUI);
																			Utilities.verticalSwipe(driver);
																		}
																		catch(Exception e) {
																			test.log(LogStatus.FAIL, "Failed to click on first audio under 'More From Author' tab");
																		}
																			
																		if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName, 10)) {
																			test.log(LogStatus.INFO, "Audio details screen is displayed");
																			if(listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName.getAttribute("text").equalsIgnoreCase(moreFromAuthorItemNameUI)) {
																				test.log(LogStatus.INFO, "Correct Audio file name is displayed in Audio details screen");
																			}
																			else{
																				test.log(LogStatus.FAIL, "Incorrect Audio file name is displayed in Audio details screen");
																				err592++;
																			}
																			if(listenpagev2.audioPlayerMorFrmAuthrAudioDetailsAuthor.getAttribute("text").equalsIgnoreCase(moreFromAuthorItemAuthorUI)) {
																				test.log(LogStatus.INFO, "Correct Audio file author is displayed in Audio details screen");
																			}
																			else{
																				test.log(LogStatus.FAIL, "Incorrect Audio file author is displayed in Audio details screen");
																				err592++;
																			}
																		}
																		else {
																			test.log(LogStatus.FAIL, "Audio details screen is not displayed");
																			homepagev2.takeScreenshot();
																		}
																		if(err592==0) {
																			test.log(LogStatus.PASS, "Verify card navigation is PASS");
																			if(!Utilities.setResultsKids("VK_592", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
																		}
																		else{
																			test.log(LogStatus.FAIL, "Verify card navigation is FAIL");
																			if(!Utilities.setResultsKids("VK_592", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
																		}	
																	}
																	else {
																		test.log(LogStatus.FAIL, "Unable to fetch the details of first audio from UI");
																	}
																	
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Failed to click on More From Author");
																}
															}
															else 
																test.log(LogStatus.FAIL, "More From Author is not clickable");
					
														}
														catch(Exception e) {
																test.log(LogStatus.FAIL, "Failed to click on the Up arrow in audio player");
														}
													}
													else 
														test.log(LogStatus.FAIL, "Up arrow in the audio player is not clickable");
																
																
													//Verification of 571
													driver.lockDevice();
													test.log(LogStatus.INFO, "Lock the Device -> Executed");
													driver.pressKeyCode(26);
													test.log(LogStatus.INFO, "Wake the Device -> Executed");
													try {
														driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text=\""+firstItemNameUI+"\" or @text=\""+firstItemNameUICamel+"\" or @text=\""+firstItemNameUIUpper+"\"]"));
														test.log(LogStatus.INFO, "Located Audio file Name in Lock screen after waking device: "+firstItemNameUI);
													}
													catch(Exception e) {
														test.log(LogStatus.FAIL, "Unable to locate Audio file Name in Lock screen after waking device");
														err570++;
													}
													try {
														driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text=\""+firstItemAuthorUI+"\" or @text=\""+firstItemAuthorUICamel+"\" or @text=\""+firstItemAuthorUIUpper+"\"]"));
														test.log(LogStatus.INFO, "Located Audio file Author in Lock screen after waking device: "+firstItemAuthorUI);
													}
													catch(Exception e) {
														test.log(LogStatus.FAIL, "Unable to locate Audio file Author in Lock screen after waking device");
														err570++;
													}
													if(err570==0) {
												    	test.log(LogStatus.PASS, "Verify the Audio book title & Author name of the playing content is being displayed in audio player in locked screen of device is PASS");
														if(!Utilities.setResultsKids("VK_570", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
													}
													else {
														test.log(LogStatus.FAIL, "Verify the Audio book title & Author name of the playing content is being displayed in audio player in locked screen of device is FAIL");
														if(!Utilities.setResultsKids("VK_570", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
													}
													//Unlock the device
														
												
												}
												else { 
													test.log(LogStatus.FAIL, "Page title is not STREAM QUALITY");
													test.log(LogStatus.FAIL, "Audio Player Settings: Verify the Navigation by tapping on stream quality option is FAIL"); 		
													if(!Utilities.setResultsKids("VK_551", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
													BasePageV2.takeScreenshot();
												}
											 }
											 else {
												test.log(LogStatus.FAIL, "Audio Player Settings: Verify the Navigation by tapping on stream quality option is FAIL"); 
												if(!Utilities.setResultsKids("VK_551", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												BasePageV2.takeScreenshot();
											 }
										 }
										 catch(Exception e) {
											 test.log(LogStatus.FAIL, "Unable to click on Stream Quality button");
										 }
									 }
								 }
								 catch(Exception e) {
									 test.log(LogStatus.FAIL, "Failed to click on Settings button in Audio player");
								 }
							 }
							 else {
								 test.log(LogStatus.FAIL, "Settings button in Audio player is not clickable");
							 }

						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Failed to click on PLAY button");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "PLAY button is not clickable");
					 }
					 
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click on first item in Carousal");
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "First item in Carousal is not clickable");
			 }

		 }
		 else {
			 test.log(LogStatus.FAIL, "Unable to click on Watch tab");
			 basepagev2.takeScreenshot();
		 }
		 
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
