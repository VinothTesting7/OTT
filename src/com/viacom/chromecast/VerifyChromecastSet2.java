package com.viacom.chromecast;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyChromecastSet2 extends BaseTestV2{
	
	String testName = "VerifyChromecastSet2";
	@Test(dataProvider = "getData")
	public void VerifyChromecastSet2(Hashtable<String, String> data) throws Exception 
	{		
		int err1220=0;
		int err1247=0;
		test = rep.startTest("VerifyChromecastSet2");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls1214 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1214=xls1214.getRowCount("Smoke_Results")+1;
		xls1214.setCellData("Smoke_Results", "Testcase Name",rowno1214,"Validate chromecast icon availability on the Audio player");

		Xls_Reader xls1215 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1215=xls1215.getRowCount("Smoke_Results")+1;
		xls1215.setCellData("Smoke_Results", "Testcase Name",rowno1215,"Validate chromecast icon placement on the Audio player");
		
		Xls_Reader xls1217 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1217=xls1217.getRowCount("Smoke_Results")+1;
		xls1217.setCellData("Smoke_Results", "Testcase Name",rowno1217,"Validate the functionality by tapping on active chromecast icon");

		Xls_Reader xls1218 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1218=xls1218.getRowCount("Smoke_Results")+1;
		xls1218.setCellData("Smoke_Results", "Testcase Name",rowno1218,"Validate 'Stop Casting' button functionality from chromecast disconnection pop up");

		Xls_Reader xls1219 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1219=xls1219.getRowCount("Smoke_Results")+1;
		xls1219.setCellData("Smoke_Results", "Testcase Name",rowno1219,"Validate Close button functionality");
		
		Xls_Reader xls1220 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1220=xls1220.getRowCount("Smoke_Results")+1;
		xls1220.setCellData("Smoke_Results", "Testcase Name",rowno1220,"Validate the displayed player controls in Sender device post casting");

		Xls_Reader xls1247 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1247=xls1247.getRowCount("Smoke_Results")+1;
		xls1247.setCellData("Smoke_Results", "Testcase Name",rowno1247,"Validate the UI of cast dialog control pop up");
		
		
		test.log(LogStatus.INFO, "Starting VerifyChromecastSet2: Verify Chromecast related test cases set 2");
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 int xStart, yStart;
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 String audioName="";
		 String audioAuthor="";
		 int meta=0;
/*		 
		//Settings verification
		 try {
				if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");
				}
		 }
		 catch (Exception e) {
				BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
		 }
		 if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsIcon, 10)) {
			settingspagev2.settingsIcon.click();
			test.log(LogStatus.INFO, "Clicked on Settings icon");
			if (Utilities.explicitWaitVisible(driver, settingspagev2.parentPinContainer, 10)) {
				settingspagev2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				test.log(LogStatus.INFO, "Entered the PIN");
				if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsDevice, 10)) {
					settingspagev2.settingsDevice.click();
					test.log(LogStatus.INFO, "Clicked on Device option");
					if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsDeviceTile, 10)) {
						test.log(LogStatus.INFO, "Device screen is displayed");		
					}
					else {
						test.log(LogStatus.FAIL, "Device screen is not displayed");	
					}
				} 
				else
					test.log(LogStatus.FAIL, "Device option is not displayed");	
			} else
				test.log(LogStatus.FAIL, "PIN container is not displayed");
		}	
		else 
			test.log(LogStatus.FAIL, "Settings icon is not displayed"); 
		
		// verifying the default toggle button should 'ON' for 'Allow Casting' options in device settings
		String allowCasting = "//android.widget.TextView[@text='Allow Casting']";
		Utilities.verticalSwipe(driver, allowCasting);
		String allowcastOn = settingspagev2.deviceAllowCastingSwitch.getText().toString().trim();
		if((allowcastOn.equalsIgnoreCase("ON"))){
			test.log(LogStatus.INFO, "Setting 'Allow Casting' is ON");
			test.log(LogStatus.INFO, "Turning the Setting 'Allow Casting' to OFF");
			try {
				settingspagev2.deviceAllowCastingSwitch.click();
				test.log(LogStatus.INFO, "Turned OFF the Setting 'Allow Casting'");
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to turn OFF the Setting 'Allow Casting'");
			}	
		}
		else {
			test.log(LogStatus.INFO, "Setting 'Allow Casting' is ON");
		}
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();	
			
		}	
*/				
		//Launch player and verify the availability
		homepagev2.tabClick("Listen");
		if(Utilities.explicitWaitClickable(driver, listenpagev2.firstItemCarousal, 10)) {
			try {
				try {
					audioName=listenpagev2.firstItemCarousalName.getAttribute("text").trim();
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to fetch name of the audio");
					meta++;
				}
				try {
					audioAuthor=listenpagev2.firstItemCarousalAuthor.getAttribute("text").trim();
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to fetch author of the audio");
					meta++;
				}
				listenpagev2.firstItemCarousal.click();
				test.log(LogStatus.INFO, "Clicked on the first item in Listen Carousal");
				if(Utilities.explicitWaitClickable(driver, listenpagev2.firstItemCarousalPlay, 10)) {
					try {
						listenpagev2.firstItemCarousalPlay.click();
						test.log(LogStatus.INFO, "Clicked on PLAY button");
						for(int wait=0;wait<15;wait++) {
							Thread.sleep(2000);
							if(Utilities.explicitWaitVisible(driver, listenpagev2.firstItemCarousalPausePlay, 20)) {
								try {
									if(Utilities.explicitWaitVisible(driver, listenpagev2.playerCastIconDisconnected, 10)){
										homepagev2.smokeresults("Validate chromecast icon availability on the Audio player",rowno1214, "PASS");
										test.log(LogStatus.PASS, "Validate chromecast icon availability on the Audio player is Pass");
									}
									else {
										homepagev2.smokeresults("Validate chromecast icon availability on the Audio player",rowno1214, "FAIL");
										test.log(LogStatus.FAIL, "Validate chromecast icon availability on the Audio player is Fail");
										BasePageV2.takeScreenshot();
									}
								}
								catch(Exception e) {
									homepagev2.smokeresults("Validate chromecast icon availability on the Audio player",rowno1214, "FAIL");
									test.log(LogStatus.FAIL, "Validate chromecast icon availability on the Audio player is Fail");
									BasePageV2.takeScreenshot();
								}
								try {
									xStart=listenpagev2.playerCastIconDisconnected.getLocation().getX();
									yStart=listenpagev2.playerCastIconDisconnected.getLocation().getY();
									if(xStart>=250 && yStart<=150) {
										homepagev2.smokeresults("Validate chromecast icon placement on the Audio player",rowno1215, "PASS");
										homepagev2.reportPass("Validate chromecast icon placement on the Audio player is Pass");
									}
									else {
										homepagev2.smokeresults("Validate chromecast icon placement on the Audio player",rowno1215, "FAIL");
										test.log(LogStatus.FAIL, "Validate chromecast icon placement on the Audio player is Fail");
										BasePageV2.takeScreenshot();
									}
								}
								catch(Exception e) {
										System.out.println();
										test.log(LogStatus.FAIL, "Unable to get cordinates of the cast icon");
								}
								try {
									listenpagev2.playerCastIconDisconnected.click();
									Thread.sleep(2000);
									test.log(LogStatus.INFO, "Clicked on the Chromcast icon");
									if(listenpagev2.castTvList.size()>0) {
										 for(int count=0;count<listenpagev2.castTvList.size();count++) {
										 		test.log(LogStatus.INFO, ""+homepagev2.castTvList.get(count).getAttribute("text")+" is listed");
										 		BasePageV2.takeScreenshot();
										 }
										 //Tap on the TV
										 try {
											 	String firstTV=listenpagev2.castTvList.get(0).getAttribute("text").trim();
											 	listenpagev2.castTvList.get(0).click();
											 	test.log(LogStatus.INFO, "Connected to the first TV");
											 	Thread.sleep(5000);
											 	for(int wait2=0;wait2<=15;wait2++) {
											 		try {
											 			if(listenpagev2.playerCastIconConnected.isDisplayed()){
												 			//Validate the displayed player controls in Sender device post casting
												 			try {
												 				listenpagev2.firstItemCarousalPausePlay.isDisplayed();
												 				test.log(LogStatus.INFO, "Play/Pause is getting displayed");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Play/Pause is not getting displayed");
												 				err1220++;
												 			}
												 			try {
												 				listenpagev2.playerForward.isDisplayed();
												 				test.log(LogStatus.INFO, "Forward button is getting displayed");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Forward button is not getting displayed");
												 				err1220++;
												 			}
												 			try {
												 				listenpagev2.playerBackward.isDisplayed();
												 				test.log(LogStatus.INFO, "Backward button is getting displayed");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Backward button is not getting displayed");
												 				err1220++;
												 			}
												 			try {
												 				listenpagev2.playerDurationElapsed.isDisplayed();
												 				test.log(LogStatus.INFO, "Duration elapsed is getting displayed");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Duration elapsed is not getting displayed");
												 				err1220++;
												 			}
												 			try {
												 				listenpagev2.playerDurationTotal.isDisplayed();
												 				test.log(LogStatus.INFO, "Total Duration is getting displayed");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Total Duration is not getting displayed");
												 				err1220++;
												 			}
												 			try {
												 				listenpagev2.playerSeekBar.isDisplayed();
												 				test.log(LogStatus.INFO, "Seek Bar is getting displayed");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Seek Bar is not getting displayed");
												 				err1220++;
												 			}
												 			try {
												 				listenpagev2.playerCloseButton.isDisplayed();
												 				test.log(LogStatus.INFO, "Close button is getting displayed");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Close Button is not getting displayed");
												 				err1220++;
												 			}
												 			try {
												 				listenpagev2.playerCastIconConnected.isDisplayed();
												 				test.log(LogStatus.INFO, "Chromecast Icon is getting displayed in Connected state");
												 			}
												 			catch(Exception e) {
												 				test.log(LogStatus.FAIL, "Chromecast Icon is not getting displayed in Connected state");
												 				err1220++;
												 			}
												 			//Final verification of 1220
												 			if(err1220==0) {
												 				homepagev2.smokeresults("Validate the displayed player controls in Sender device post casting",rowno1220, "PASS");
																test.log(LogStatus.PASS, "Validate the displayed player controls in Sender device post casting is Pass");
												 			}
												 			else {
												 				homepagev2.smokeresults("Validate the displayed player controls in Sender device post casting",rowno1220, "FAIL");
																test.log(LogStatus.FAIL, "Validate the displayed player controls in Sender device post casting is Fail");
												 			}
												 			BasePageV2.takeScreenshot();
												 			break;
												 		}
											 			else {
											 				Thread.sleep(2000);
												 			if(wait2==15) {
												 				test.log(LogStatus.FAIL, "The Chromecast icon is not displayed in Connected state");
												 				BasePageV2.takeScreenshot();
												 			}
											 			}
											 		}
											 		catch(Exception e) {
											 			Thread.sleep(2000);
											 			if(wait2==15) {
											 				test.log(LogStatus.FAIL, "The Chromecast icon is not displayed in Connected state");
											 				BasePageV2.takeScreenshot();
											 			}
											 		}
											 	}
											 	try {
											 		listenpagev2.playerCastIconConnected.click();
											 		test.log(LogStatus.INFO, "Clicked on the Chromecast icon");
											 		try {
														if(listenpagev2.stopCasting.isDisplayed()){
															homepagev2.smokeresults("Validate the functionality by tapping on active chromecast icon",rowno1217, "PASS");
															homepagev2.reportPass("Validate the functionality by tapping on active chromecast icon is Pass");
															//Validate the UI of cast dialog control pop up 
															try {
																if(listenpagev2.stopCastDialogTV.getAttribute("text").trim().equalsIgnoreCase(firstTV)) 
																	test.log(LogStatus.INFO, "Chromecast device name is displayed");
																else {
																	test.log(LogStatus.FAIL, "Chromecast device name is not displayed");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Chromecast device name is not displayed");
																err1247++;
															}
															try {
																if(listenpagev2.stopCastDialogCancel.isDisplayed()) 
																	test.log(LogStatus.INFO, "Close button is displayed");
																else {
																	test.log(LogStatus.FAIL, "Close button is not displayed");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Close button is displayed");
																err1247++;
															}
															try {
																if(listenpagev2.stopCastDialogImage.isDisplayed()) 
																	test.log(LogStatus.INFO, "Image is displayed");
																else {
																	test.log(LogStatus.FAIL, "Image is not displayed");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Image is not displayed");
																err1247++;
															}
															try {
																if(listenpagev2.stopCastDialogAudioName.getAttribute("text").trim().equalsIgnoreCase(audioName)) 
																	test.log(LogStatus.INFO, "Audio name is displayed");
																else {
																	test.log(LogStatus.FAIL, "Audio name is not displayed ");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Audio name is not displayed displayed");
																err1247++;
															}
															try {
																if(listenpagev2.stopCastDialogAudioAuthor.getAttribute("text").trim().equalsIgnoreCase(audioAuthor)) 
																	test.log(LogStatus.INFO, "Audio author is displayed");
																else {
																	test.log(LogStatus.FAIL, "Audio author is not displayed displayed");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Audio author is not displayed displayed");
																err1247++;
															}
															try {
																if(listenpagev2.stopCastDialogPlayButton.isDisplayed()) 
																	test.log(LogStatus.INFO, "Play button is displayed");
																else {
																	test.log(LogStatus.FAIL, "Play button is not displayed");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Play button is not displayed");
																err1247++;
															}
															try {
																if(listenpagev2.stopCastDialogVolume.isDisplayed()) 
																	test.log(LogStatus.INFO, "Volume control is displayed");
																else {
																	test.log(LogStatus.FAIL, "Volume control is not displayed");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Volume control is not displayed");
																err1247++;
															}
															try {
																if(listenpagev2.stopCasting.isDisplayed()) 
																	test.log(LogStatus.INFO, "Stop Casting is displayed");
																else {
																	test.log(LogStatus.FAIL, "Stop Casting is not displayed");
																	err1247++;
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Stop Casting is not displayed");
																err1247++;
															}
															//Final verification of 1247
												 			if(err1247==0) {
												 				homepagev2.smokeresults("Validate the UI of cast dialog control pop up",rowno1247, "PASS");
																test.log(LogStatus.PASS, "Validate the UI of cast dialog control pop up is Pass");
												 			}
												 			else {
												 				homepagev2.smokeresults("Validate the UI of cast dialog control pop up",rowno1247, "FAIL");
																test.log(LogStatus.FAIL, "Validate the UI of cast dialog control pop up is Fail");
												 			}															
															try {
																listenpagev2.stopCastDialogCancel.click();
																test.log(LogStatus.INFO, "Tapped on Close button in the dialog");
																for(int wait1=0;wait1<15;wait1++) {
																	if(homepagev2.chromecastIconConnected.isDisplayed()){
																		test.log(LogStatus.INFO, "The Chromecast icon is displayed in Connected state after tapping on Close button");
																		homepagev2.smokeresults("Validate Close button functionality",rowno1219, "PASS");
																		homepagev2.reportPass("Validate Close button functionality is Pass");
																		break;
																	}
																	else {
																		Thread.sleep(2000);
																		if(wait1==15) {
																			test.log(LogStatus.FAIL, "The Chromecast icon is not displayed in Connected state after tapping on Close button");
																			homepagev2.smokeresults("Validate Close button functionality",rowno1219, "FAIL");
																			test.log(LogStatus.FAIL, "Validate Close button functionalityp is Fail");
																			BasePageV2.takeScreenshot();
																		}
																	}
																}	
															}
															catch(Exception e) {
																	test.log(LogStatus.FAIL, "Unable to click on the Close button in the stop casting dialog");
																	homepagev2.smokeresults("Validate Close button functionality",rowno1219, "FAIL");
																	test.log(LogStatus.FAIL, "Validate Close button functionality is Fail");
																	BasePageV2.takeScreenshot();
															}
															for(int wait2=0;wait2<=20;wait2++) {
														 		try {
														 			if(listenpagev2.playerCastIconConnected.isDisplayed()){
															 			try {
															 				listenpagev2.playerCastIconConnected.click();
															 				test.log(LogStatus.INFO, "Clicked on Chromecast icon");
															 				break;
															 			}
															 			catch(Exception e) {
															 				Thread.sleep(2000);
															 				if(wait2==20) {
																 				test.log(LogStatus.FAIL, "The Chromecast icon is not displayed in Connected state");
																 				BasePageV2.takeScreenshot();
																 				break;
																 			}
															 			}		
															 		}
														 			else {
														 				Thread.sleep(2000);
															 			if(wait2==20) {
															 				test.log(LogStatus.FAIL, "The Chromecast icon is not displayed in Connected state");
															 				BasePageV2.takeScreenshot();
															 				break;
															 			}
														 			}
														 		}
														 		catch(Exception e) {
														 			Thread.sleep(2000);
														 			if(wait2==15) {
														 				test.log(LogStatus.FAIL, "The Chromecast icon is not displayed in Connected state");
														 				BasePageV2.takeScreenshot();
														 				break;
														 			}
														 		}
														 	}
															try {
																listenpagev2.stopCasting.click();
																test.log(LogStatus.INFO, "Clicked on Stop Casting");
																for(int wait1=0;wait1<15;wait1++) {
																	if(listenpagev2.playerCastIconDisconnected.isDisplayed()){
																		test.log(LogStatus.INFO, "Chromecast icon is displayed in Disconnected state");
																		homepagev2.smokeresults("Validate 'Stop Casting' button functionality from chromecast disconnection pop up",rowno1218, "PASS");
																		homepagev2.reportPass("Validate 'Stop Casting' button functionality from chromecast disconnection pop up is Pass");
																		break;
																	}
																	else {
																		Thread.sleep(2000);
																		if(wait1==15) {
																			test.log(LogStatus.FAIL, "Chromecast icon is not displayed in Disconnected state");
																			homepagev2.smokeresults("Validate 'Stop Casting' button functionality from chromecast disconnection pop up",rowno1218, "FAIL");
																			test.log(LogStatus.FAIL, "Validate 'Stop Casting' button functionality from chromecast disconnection pop up is Fail");
																			BasePageV2.takeScreenshot();
																		}
																	}
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Unable to click on Stop Casting");
																homepagev2.smokeresults("Validate 'Stop Casting' button functionality from chromecast disconnection pop up",rowno1218, "FAIL");
																test.log(LogStatus.FAIL, "Validate 'Stop Casting' button functionality from chromecast disconnection pop up is Fail");
																BasePageV2.takeScreenshot();
															}		
														}
														else {
															test.log(LogStatus.FAIL, "Stop Casting is not displayed");	
															homepagev2.smokeresults("Validate the functionality by tapping on active chromecast icon",rowno1217, "FAIL");
															test.log(LogStatus.FAIL, "Validate the functionality by tapping on active chromecast icon is Fail");
															BasePageV2.takeScreenshot();
														}	
													}
													catch(Exception e) {
														test.log(LogStatus.FAIL, "Stop Casting is not displayed");
														BasePageV2.takeScreenshot();
													}			
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Unable to click on the Chromecast icon in the player");
												}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Unable to click the Chromecast TV listed in Player");
										}				
									 }
									 else {
										test.log(LogStatus.FAIL, "Chromecast TV not displayed");
										BasePageV2.takeScreenshot();
									 }
								break;
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Unable to click on the Chromecast icon");
									break;
								}	
							}
							else {
								Thread.sleep(3000);
								if(wait==15) {
									test.log(LogStatus.FAIL, "Audio is not getting played");
								}
							}
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to click on Play button");
					}			
				}
				else {
					test.log(LogStatus.FAIL, "PLAY button is not clickable.");
				}			
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on the first item in Listen Carousal");
			}
		}
		else{
			test.log(LogStatus.FAIL, "First item in Listen Carousal is not clickable");
		}	
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
