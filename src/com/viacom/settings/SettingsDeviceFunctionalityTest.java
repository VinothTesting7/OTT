package com.viacom.settings;

import java.time.Duration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class SettingsDeviceFunctionalityTest extends BaseTestV2{

	String testName = "SettingsDeviceFunctionalityTest";

	
	String title = "";
	String hindiString = "";
	String lowString = "";
	@Test(dataProvider = "getData")
	public void settingsDeviceFunctionalityTest(Hashtable<String, String> data) throws Exception {

		test = rep.startTest("SettingsDeviceFunctionalityTest");
		test.log(LogStatus.INFO,
				"Starting the test to Verify Settings Device Functionality Test: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		Xls_Reader xls838 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno838 = xls838.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno838, "Verify the default toggle button state for Enable Downloads options in Device settings");
		
		Xls_Reader xls837 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno837 = xls837.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno837, "Verify the functionality post changing Preferred Language options from Settings:");
		
		Xls_Reader xls841 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno841 = xls841.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno841, "Verify download button functionality when 'Enable Downloads'  button is in 'OFF' state:");
		
		Xls_Reader xls850 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno850 = xls850.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno850, "Verify the default option for Download Quality in device settings");
		
		Xls_Reader xls851 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno851 = xls851.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno851, "Verify the download initiation fuctionality when user updates quality option from default to other state:");
		
		Xls_Reader xls886 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno886 = xls886.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno886, "Verify the functionality of 'Skip for Now' button on the rate us pop-up message");
		
		int rowno869 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno869," Verify the Download Quality dropdown functionality:");
		
		int rowno871 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno871,"Verify the UI of Download disabled Pop-up");
		
		int rowno872 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno872,"Verify the functonality of OK button in Downloads disabled pop-up");
		
		
		
		// Launching the Voot-kids App
					launchApp();
					test.log(LogStatus.INFO, "Application launched successfully");
					
					
//					LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
					SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		       		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		            KidsPlayerPageV2 playerpagev2 = new KidsPlayerPageV2(driver, test);
		            ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		            WatchPageV2 watchpagev2 = new WatchPageV2(driver,test);
		            DownloadsPageV2 downloadsPageV2 = new DownloadsPageV2(driver, test);
					HashSet<String> set = new HashSet<String>();
					String end1 = "//android.widget.TextView[@text='Create New Profile']";

					// navigating to settings 'Profiles' page 

//							   HomePageV2.signup();

					
					
					homepagev2.login(data.get("Email"),data.get("Password"));
					
                     
					try {
						homepagev2.profilepic.click(); // tap on profile icon
						test.log(LogStatus.INFO, "Clicked on profile icon in home page");

				} catch (Exception e) {
					BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
				}
					
					settingsPageV2.putBackGroundApp();
					
					// Click on ParentZone Button in Switch Profile Screen parentZoneButton
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
						settingsPageV2.parentZoneButton.click();
						test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
							Thread.sleep(1000);
							settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
						}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
					}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
					

				
					 driver.runAppInBackground(Duration.ofSeconds(3));
					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					 driver.currentActivity();
					
					
		//Verify the functionality post changing Preferred Language options from Settings:
					
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
						settingsPageV2.settingsIcon.click();
						
							
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDevice, 10)) {
								settingsPageV2.settingsDevice.click();
								test.log(LogStatus.INFO, "Succussfully tapped on Settings Icon , Naviagted to Settings Page");
							}else BasePageV2.reportFail("Device option is not found in SETTINGS main Page");
							
							
//							Verify the Download Quality dropdown functionality:
							
//							//scrolling to till Download Quality option
//							try {
//								String end = "//android.widget.TextView[@text='Download Quality']";
//								Utilities.verticalSwipe(driver, end);
//								test.log(LogStatus.INFO, "Succussfully Scrolled till 'Download Quality' option in DEVICE screen");
//							} catch (Exception e) {
//								test.log(LogStatus.FAIL, "Not scrolled till 'Download Quality' / not found in DEVICE screen");
//							}
							
//							Verify the Download Quality dropdown functionality:
							
							
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadQulMedium, 5) || (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityLow144, 5))) {
								 Utilities.verticalSwipe(driver);
								
								
								try {
									downloadsPageV2.setDeviceDownloadQuality1("High");
									
									test.log(LogStatus.INFO, "selcted 'High-720' in Download Quality drop down");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Not able to select 'High-720' Download Quality");
								}
								
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityhigh, 5)) {
									 test.log(LogStatus.PASS,"Verified the Download Quality dropdown functionality");
									 BasePageV2.smokeresults(" ",rowno869, "PASS");
								}else {
									 test.log(LogStatus.FAIL, "Unable to select 'High-720' in Download Quality drop down / Not found ");
									 BasePageV2.takeScreenshot();
								}
								
								
							}else if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityLow144, 5) ||Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityhigh, 5)) {
								
								   Utilities.verticalSwipe(driver);
								if(Utilities.explicitWaitClickable(driver, settingsPageV2.deviceDownloadsQualitySpinner, 4)) {
									settingsPageV2.deviceDownloadsQualitySpinner.click();
									test.log(LogStatus.INFO, "Succussfully Clicked on 'Download Quality' Drop down");
								}else test.log(LogStatus.FAIL, "Not able to clicked 'Download Quality' drop down/not found");
								
								try {
						
									settingsPageV2.MediumDownloadQualitySelectInDeviceScreen.click();
									test.log(LogStatus.INFO, "selcted 'Medium-320' in Download Quality drop down");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Not able to select 'Medium-320' Download Quality");
								}
								
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityMedium, 5)) {
									 test.log(LogStatus.PASS,"Verified the Download Quality dropdown functionality");
									 BasePageV2.smokeresults(" ",rowno869, "PASS");
								}else {
									 test.log(LogStatus.FAIL, "Unable to select 'Medium-320' in Download Quality drop down / Not found ");
									 BasePageV2.takeScreenshot();
								}
								
								
							}else if (Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityMedium, 5) || Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityhigh, 5)){
								 Utilities.verticalSwipe(driver);
								if(Utilities.explicitWaitClickable(driver, settingsPageV2.deviceDownloadsQualitySpinner, 4)) {
									settingsPageV2.deviceDownloadsQualitySpinner.click();
									test.log(LogStatus.INFO, "Succussfully Clicked on 'Download Quality' Drop down");
								}else test.log(LogStatus.FAIL, "Not able to clicked 'Download Quality' drop down/not found");
								
								try {
									settingsPageV2.LowDownloadQualitySelectInDeviceScreen.click();
									test.log(LogStatus.INFO, "selcted 'Low-144' in Download Quality drop down");
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Not able to select 'Low-144' Download Quality");
								}
								
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityLow144, 5)) {
									 test.log(LogStatus.PASS,"Verified the Download Quality dropdown functionality");
									 BasePageV2.smokeresults(" ",rowno869, "PASS");
								}else {
									 test.log(LogStatus.FAIL, "Unable to select 'Low-144' in Download Quality drop down / Not found ");
									 BasePageV2.takeScreenshot();
								}
								
							}else {
								
								test.log(LogStatus.INFO, "Not found 'Download Quality' drop down[Low,Medium,high]");
							}
							

							
					//Verify the default toggle button state for Enable Downloads options in Device settings
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceEnableddownloadsSwitch, 10)) {
								if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceEnableddownloadsSwitch, 10)) {
									if(settingsPageV2.deviceEnableddownloadsSwitch.isEnabled()) {
										test.log(LogStatus.PASS,"Verify the default toggle button state for Enable Downloads options in Device settings");
										BasePageV2.smokeresults(" ",rowno838, "PASS");
									}else {
										test.log(LogStatus.FAIL, "Verified the default toggle button state for Cellular Downloads options is OFF State in Device settings");
									}
								}else test.log(LogStatus.FAIL, "'Enable Download Switch button is not found");
							}else BasePageV2.reportFail("'Enable Downloads' option is not found in Device Mian Screen");
							
					//Verify the download initiation functionality when user updates quality option from default to other state:	
							
							WebElement dropDownQualityList = driver
									.findElementByXPath("//android.widget.Spinner");
							dropDownQualityList.click();
							WebElement dropDownQualityListTilesLow = driver.findElementByXPath("//android.widget.ListView//android.widget.TextView[@text='Low' and @resource-id='com.viacom18.vootkids:id/drop_down_text']");
							dropDownQualityListTilesLow.click();
							test.log(LogStatus.INFO, "Default Stream Quality Drop down selected Low option");
							
							
							
							
						//Verify the default option for Download Quality in device settings
							      String dq ="//android.widget.TextView[@text='Download Quality' and @resource-id='com.viacom18.vootkids:id/item_tittle']";
							      Utilities.verticalSwipe(driver, dq);
							      test.log(LogStatus.INFO, "swipped till 'Download qauality' option in Device Screen");
							      if(Utilities.explicitWaitVisible(driver, settingsPageV2.deviceDownloadsQualityMedium, 10)) {
							    	 test.log(LogStatus.PASS,"Verified the default option for Download Quality in device settings");
									 BasePageV2.smokeresults(" ",rowno850, "PASS");
							      }else BasePageV2.reportFail("Unable to Find Default download quality 'Medium-320' in Device Settings Page");
						// Disable the 'Enable Downloads' Switch 
							      if(Utilities.explicitWaitClickable(driver, settingsPageV2.deviceEnableddownloadsSwitch, 5)) {
							    	  String swiichState= settingsPageV2.deviceEnableddownloadsSwitch.getText().toString().trim();
							    	  if(swiichState.equalsIgnoreCase("ON")) {
							    		  settingsPageV2.deviceEnableddownloadsSwitch.click();
							    	  }else test.log(LogStatus.INFO, "'Enable Downloads' Switch has OFF state");
							      }else BasePageV2.reportFail("unble to find the Enable Downloads Option in Device Settings Page");
							      
							      Utilities.verticalSwipeDown(driver);
							      Utilities.verticalSwipeDown(driver);
							 // getting the Default Stream Quality Selected Quality text 'Low' Quality 
							      if(Utilities.explicitWaitVisible(driver, settingsPageV2.devicedefaultsremquality, 10)) {
										 lowString = settingsPageV2.defaultsremqualityText.getText();  
										System.out.println("The Stream quality Low is selected " +lowString );
									}else BasePageV2.reportFail("not able to copied the Hindi text from Drop Down");
							      
							      
							      
						//	Verify the functionality of 'Skip for Now' button on the rate us pop-up message
							      
							      driver.pressKeyCode(AndroidKeyCode.BACK);
							      String rateUsText = "//android.widget.TextView[@text='Rate Us' and @resource-id='com.viacom18.vootkids:id/item_head']";
								  Utilities.verticalSwipe(driver, rateUsText);
								 test.log(LogStatus.INFO, "Tapping on the RateUS");
								 if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsRateUS, 2))
								  settingsPageV2.settingsRateUS.click();
								 else BasePageV2.reportFail("unable to find RateUs option in Settings Page");
								 test.log(LogStatus.INFO, "tapping 'yes' Button on RateUs pop Up");
								 if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsPopYesText, 2))
								  settingsPageV2.rateUsPopYesText.click();
								 else BasePageV2.reportFail("oN pop up 'Yes' Button is not found");
								 test.log(LogStatus.INFO, "Tapping SKIP FOR NOW on rateUS pop up");
								 if(Utilities.explicitWaitClickable(driver, settingsPageV2.rateUsPopSkipForNow, 2)) {
									settingsPageV2.rateUsPopSkipForNow.click();
									test.log(LogStatus.PASS, "Tapped SKIP FOR NOW optionsuccussfully on RateUS pop Up ");	
								// Putting the App to background and bring to up for Acivate	
									 driver.runAppInBackground(Duration.ofSeconds(5));
									 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
									 driver.currentActivity();
								
								    if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDevice, 5)) {
								    	 test.log(LogStatus.PASS,"Verified the functionality of 'Skip for Now' button on the rate us pop-up message");
										 BasePageV2.smokeresults(" ",rowno886, "PASS");
								    }else test.log(LogStatus.FAIL, "Not able to redirected to Settings Page from RateUS pop up 'SKIP FOR NOW' click");
								 }else BasePageV2.reportFail("Skip For Now option is not found in rateUS Pop up");
							      
							   
//	 Verify the functionality post changing Preferred Language options from Settings:	
								 
						   if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDevice, 10)) {
										settingsPageV2.settingsDevice.click();
										test.log(LogStatus.INFO, "Succussfully tapped on Settings Icon , Naviagted to Settings Page");
						   }else BasePageV2.reportFail("Device option is not found in SETTINGS Mina Page");
									 
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.devicePrefferdLanuageSpinner, 10)) {
							settingsPageV2.devicePrefferdLanuageSpinner.click();
							test.log(LogStatus.INFO, "Tapped on Spinner");
							}else BasePageV2.reportFail("Unable to Tapped the Preferred Language Drop down");
						// selecting the Hindi Language in Preferred Language 
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.devicePrefferdLanuageHindi, 2)) {
							settingsPageV2.devicePrefferdLanuageHindi.click();
							Thread.sleep(1000);
							// Putting the App to background and bring to up for Activate	
							 driver.runAppInBackground(Duration.ofSeconds(5));
							 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
							 driver.currentActivity();
							
							if(Utilities.explicitWaitVisible(driver, settingsPageV2.devicePrefferdLanuageDropDownText, 10)) {
								 hindiString = settingsPageV2.devicePrefferdLanuageDropDownText.getText();
								System.out.println("The Hindi Lang selected " + hindiString );
								test.log(LogStatus.INFO, "The Lang selected in Settings Device is : "  + hindiString);
							}else test.log(LogStatus.FAIL, "not able to copied the Hindi text from Drop Down"); 
							
							
							
							test.log(LogStatus.INFO, "Slected Hindi Language in 'Preferred Language' Drop Down ");
							}else BasePageV2.reportFail("Unable to found Hindi Language in the Preferred Language");
						
						// Clicking on back button to home page
							    driver.pressKeyCode(AndroidKeyCode.BACK);
								driver.pressKeyCode(AndroidKeyCode.BACK);	
								driver.pressKeyCode(AndroidKeyCode.BACK);
								driver.pressKeyCode(AndroidKeyCode.BACK);
			
								
								 //Click on Listen tab
								 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10)) {
								 homepagev2.Listen_tab.click();	
								 test.log(LogStatus.INFO, "Clicked on Listen tab");
								// BasePageV2.takeScreenshot();
								 Thread.sleep(2000);
								 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
								 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
								 Utilities.verticalSwipe(driver, tray);
								 String audiostorytitle="";
								 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
								 {
									 String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
									 Utilities.verticalSwipe(driver, audiostory);
									 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 20))
									 {
										 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTrayAudioStoryTitle, 20))
										 {
											 audiostorytitle=listenpagev2.firstTrayAudioStoryTitle.getText();
										 }
										 else
										 test.log(LogStatus.FAIL, "Audio story title is missing");
										 
										 listenpagev2.firstTrayAudioStory.click();
									 }
									 else
										 BasePageV2.reportFail("No contents in the tray / Content not clickable");
								 }
								 else
									 BasePageV2.reportFail("Failed to find the tray");
								 
								 
								 test.log(LogStatus.INFO, "Navigating to the audio Detail page - "+audiostorytitle);
								 
								 
								 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
								 Utilities.verticalSwipe(driver, downloadlink);
								 
								 if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
								 {
									 Utilities.verticalSwipe(driver);
									 listenpagev2.downloadAudioBookLink.click();
									 test.log(LogStatus.INFO, "Clicked Download Link in Listen Audio Book");

									 if(Utilities.explicitWaitVisible(driver, listenpagev2.DownloadDisablesMsg, 5)) {
										 if(Utilities.explicitWaitVisible(driver, listenpagev2.DownloadDisablesSubMsg, 5)) {
										    if(Utilities.explicitWaitClickable(driver, listenpagev2.DownloadDisablesOKBtn, 2)) {
										    	test.log(LogStatus.PASS,"Verify the UI of Download disabled Pop-up");
												 BasePageV2.smokeresults(" ", rowno871, "PASS");
							
										    }else {
										    	test.log(LogStatus.FAIL, "Not found OK button on 'Download Disabled!'  pop up");
										    	BasePageV2.takeScreenshot();
										    }
										 }else {
											 test.log(LogStatus.FAIL, "Not found Sub title of 'Download Disabled!'  on pop up");
											 BasePageV2.takeScreenshot();
										 }
									 }else {
										 test.log(LogStatus.FAIL, "Not found 'Download Disabled!' title on pop up");
										 BasePageV2.takeScreenshot();
									 }
									 
									 
									 
									 
									 if(Utilities.explicitWaitVisible(driver, listenpagev2.DownloadDisablesMsg, 5)) {
										 test.log(LogStatus.PASS,"Verify download button functionality when 'Enable Downloads'  button is in 'OFF' state:");
										 BasePageV2.smokeresults(" ", rowno841, "PASS");
									 }else BasePageV2.reportFail("Unable to find the Download Link in Lisen page / unable to Click on Download Link");
									 
									 if(Utilities.explicitWaitClickable(driver, listenpagev2.DownloadDisablesOKBtn1, 2)) {
										 listenpagev2.DownloadDisablesOKBtn1.click();
										 test.log(LogStatus.INFO, "Tapped 'Ok' Button on Downloads Disabled Pop up");
										 test.log(LogStatus.PASS,"Verify the functonality of OK button in Downloads disabled pop-up");
										 BasePageV2.smokeresults(" ", rowno872, "PASS");
							
									 }else BasePageV2.reportFail("Unable to Click Ok Button / Not find the Ok button in Downloads Disabled pop up");
									 
									 driver.pressKeyCode(AndroidKeyCode.BACK);
									 Utilities.verticalSwipeDown(driver);
									 Utilities.verticalSwipeDown(driver);
									 
									
									 test.log(LogStatus.INFO, "Downloading the Audio Book - "+audiostorytitle);
								 }
								 else BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
								 
								 }else BasePageV2.reportFail("Unable to find the Listen tab");
								
	// Checking the Preferred Launguage is chnaged according fron device settings checking in Watch tab play Video 
//								 Click on Watch tab
								 
								 try
									{
										driver.closeApp();
									} catch (Exception e)
									{

									}
									driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
								 
								 
								 
								 
								 
								 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
								           homepagev2.watch_tab.click();	
								           test.log(LogStatus.INFO, "Clicked on Watch tab");
								 
								//Click on the first carousal item
								 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
								 watchpagev2.firstItemInCarousal.click();
								 }
								 else
									 BasePageV2.reportFail("Not able to click on Content from Carousal");
								 
								 
								   test.log(LogStatus.INFO, "Playing any of the content from carousal");
								  if(Utilities.explicitWaitVisible(driver,playerpagev2.videoPlayer, 3)) {
											  
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
									
									// Checking the Selected Language is displayed r not in Watch tan Videos Play abck settings			  
												  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectLanguageList, 10))
												  {
													  test.log(LogStatus.INFO, "Select Language List button is displayed");
													  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedLanguage, 10))
													  {
														  String lang=playerpagev2.defaultSelectedLanguage.getText();
														  if(lang!=null && !lang.equals(""))
														  {
															  test.log(LogStatus.INFO, " Selected Language is : "+lang);
															  if(!lang.equals(null)) {
																  test.log(LogStatus.INFO, "Selected Language Displayed in Player Settings Page ");
																  test.log(LogStatus.PASS,"Verify the functionality post changing Preferred Language options from Settings:");
																  BasePageV2.smokeresults(" ",rowno837, "PASS");
															  }else BasePageV2.reportFail("Selected Device Preferred Language filed not found in player Settings Language Filed ");
														
														  }
														  else
															test.log(LogStatus.FAIL, "No default language is selected ");
													
													  }
													  else
														  test.log(LogStatus.FAIL, "default selected language is not displayed ");
								
												  }else
													  test.log(LogStatus.FAIL, "Select Language List drop down  is not displayed");
								
								
											 
									// checking the Stream Quality Selected 'Low'  is displayed r not 			  
												  
												
												  if(Utilities.explicitWaitVisible(driver, playerpagev2.selectQualityList, 10))
												  {
													  test.log(LogStatus.INFO, "Select Language List button is displayed");
													  if(Utilities.explicitWaitVisible(driver, playerpagev2.defaultSelectedQuality, 10))
													  {
														  String qaulity=playerpagev2.defaultSelectedQuality.getText();
														  if(qaulity!=null && !qaulity.equals(""))
														  {
															  test.log(LogStatus.INFO, " Selected stream quality is : "+qaulity);
															  if(!qaulity.equalsIgnoreCase(null)) {
																  test.log(LogStatus.INFO, "Selected stream qaulity is diplayed in video play back settings ");
																  test.log(LogStatus.PASS,"Verify the download initiation fuctionality when user updates quality option from default to other state:");
																  BasePageV2.smokeresults(" ",rowno851, "PASS");
															  }else BasePageV2.reportFail("Selected Device Preferred Language filed not found in player Settings Language Filed ");
														
														  }
														  else
															test.log(LogStatus.FAIL, "No default stream Quality is selected ");
													
													  }
													  else
														  test.log(LogStatus.FAIL, "default selected Stream quality is not displayed ");
								
												  }else
													  test.log(LogStatus.FAIL, "Select stream quality List drop down is not displayed");
												  
									
								
											  }else BasePageV2.reportFail("ON Player Screen Settings Icon is not found ");
	                                   }else BasePageV2.reportFail("Progress barc not found on Video player ");
								 }else BasePageV2.reportFail("Unable to Found Watch Icon in home page");
							
						
						
					}else BasePageV2.reportFail("unble to found Settings ICon in Profile Page");


	
}
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
	
	
}
