package com.viacom.chromecast;

import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//Author Tanisha
public class VerifyChromecastSet1 extends BaseTestV2{
	
	String testName = "VerifyChromecastSet1";
	@Test(dataProvider = "getData")
	public void VerifyChromecastSet1(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyChromecastSet1");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls1157 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1157=xls1157.getRowCount("Smoke_Results")+1;
		xls1157.setCellData("Smoke_Results", "Testcase Name",rowno1157,"Validate the chromecast icon availability in Top menu bar");
		
		Xls_Reader xls1160 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1160=xls1160.getRowCount("Smoke_Results")+1;
		xls1160.setCellData("Smoke_Results", "Testcase Name",rowno1160,"Validate chromecast icon availability on the player");
		
		Xls_Reader xls1161 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1161=xls1161.getRowCount("Smoke_Results")+1;
		xls1161.setCellData("Smoke_Results", "Testcase Name",rowno1161,"Validate chromecast icon placement on the player");
				
		Xls_Reader xls1163 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1163=xls1163.getRowCount("Smoke_Results")+1;
		xls1163.setCellData("Smoke_Results", "Testcase Name",rowno1163,"Validate the functionality when user taps on chromecast icon from Top menu bar");
		
		Xls_Reader xls1164 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1164=xls1164.getRowCount("Smoke_Results")+1;
		xls1164.setCellData("Smoke_Results", "Testcase Name",rowno1164,"Validate the UI of chromecast icon post connecting to chromecast device");
		
		Xls_Reader xls1165 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1165=xls1165.getRowCount("Smoke_Results")+1;
		xls1165.setCellData("Smoke_Results", "Testcase Name",rowno1165,"Validate the functionality when user taps on chromecast icon from player");
		
		Xls_Reader xls1166 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1166=xls1166.getRowCount("Smoke_Results")+1;
		xls1166.setCellData("Smoke_Results", "Testcase Name",rowno1166,"Validate the UI of chromecast icon post connecting to chromecast device");
		
		Xls_Reader xls1167 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1167=xls1167.getRowCount("Smoke_Results")+1;
		xls1167.setCellData("Smoke_Results", "Testcase Name",rowno1167,"Validate the chromecast disconnection pop up UI");
		
		Xls_Reader xls1168 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1168=xls1168.getRowCount("Smoke_Results")+1;
		xls1168.setCellData("Smoke_Results", "Testcase Name",rowno1168,"Validate Stop Casting button functionality from chromecast disconnection popup");
		
		Xls_Reader xls1169 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1169=xls1169.getRowCount("Smoke_Results")+1;
		xls1169.setCellData("Smoke_Results", "Testcase Name",rowno1169,"Validate Close button functionality");
		
		Xls_Reader xls1170 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1170=xls1170.getRowCount("Smoke_Results")+1;
		xls1170.setCellData("Smoke_Results", "Testcase Name",rowno1170,"Validate the functionality by tapping on active chromecast icon");
		
		Xls_Reader xls1171 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1171=xls1171.getRowCount("Smoke_Results")+1;
		xls1171.setCellData("Smoke_Results", "Testcase Name",rowno1171,"Validate 'Stop Casting' button functionality from chromecast disconnection pop up");
		 
		Xls_Reader xls1172 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1172=xls1172.getRowCount("Smoke_Results")+1;
		xls1172.setCellData("Smoke_Results", "Testcase Name",rowno1172,"Validate Close button functionality");

		
		test.log(LogStatus.INFO, "Starting VerifyChromecastSet1: Verify Chromecast related test cases set 1");
		//Launch Voot kids app
		 launchApp();
		 int err1167=0;
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 int xStart, yStart;
		 
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.chromecastIconDisconnected, 10)) {
			   homepagev2.smokeresults("Validate the chromecast icon availability in Top menu bar",rowno1157, "PASS");
			   homepagev2.reportPass("Validate the chromecast icon availability in Top menu bar is Pass");
		 }
		 else {
			   homepagev2.smokeresults("Validate the chromecast icon availability in Top menu bar",rowno1157, "FAIL");
			   test.log(LogStatus.FAIL, "Validate the chromecast icon availability in Top menu bar is Fail");
			   BasePageV2.takeScreenshot();
		 }
		//Click on the icon in top menu bar
		 if(Utilities.explicitWaitClickable(driver, homepagev2.chromecastIconDisconnected, 10)) {
			 try {
				 homepagev2.chromecastIconDisconnected.click();
				 test.log(LogStatus.INFO, "Clicked on the Chromcast icon");
				 try {
					 	Thread.sleep(2000);
					 	if(homepagev2.castTvList.size()>0) {
						 	for(int count=0;count<homepagev2.castTvList.size();count++) {
						 		test.log(LogStatus.INFO, ""+homepagev2.castTvList.get(count).getAttribute("text")+" is listed");
						 	}
							homepagev2.smokeresults("Validate the functionality when user taps on chromecast icon from Top menu bar",rowno1163, "PASS");
							homepagev2.reportPass("Validate the functionality when user taps on chromecast icon from Top menu bar is Pass");
							//Tap on the TV
							try {
									Thread.sleep(2000);
									homepagev2.castTvList.get(0).click();
									for(int wait=0;wait<=15;wait++) {
										if(homepagev2.chromecastIconConnected.isDisplayed()) {
											homepagev2.smokeresults("Validate the UI of chromecast icon post connecting to chromecast device",rowno1164, "PASS");
											homepagev2.reportPass("Validate the UI of chromecast icon post connecting to chromecast device is Pass");
											//Remove the selection
											try {
												homepagev2.chromecastIconConnected.click();
												test.log(LogStatus.INFO, "Clicked on the Chromcast icon");
												Thread.sleep(7000);
												//Validate the chromecast disconnection pop up UI
												try {
													if(homepagev2.noMediaSelected.isDisplayed()) 
														test.log(LogStatus.INFO, "No media selected text is displayed");
													else {
														test.log(LogStatus.FAIL, "No media selected text is not displayed");
														err1167++;
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "No media selected text is not displayed");
													err1167++;
												}
												try {
													if(homepagev2.stopCasting.isDisplayed()) 
														test.log(LogStatus.INFO, "Stop Casting text is displayed");
													else {
														test.log(LogStatus.FAIL, "Stop Casting text is not displayed");
														err1167++;
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Stop Casting text is not displayed");
													err1167++;
												}
												try {
													if(homepagev2.stopCastDialogCancel.isDisplayed()) 
														test.log(LogStatus.INFO, "Close button is displayed");
													else {
														test.log(LogStatus.FAIL, "Close button is not displayed");
														err1167++;
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Close button is not displayed");
													err1167++;
												}
												
												if(err1167==0) {
													homepagev2.smokeresults("Validate the chromecast disconnection pop up UI",rowno1167, "PASS");
													homepagev2.reportPass("Validate the chromecast disconnection pop up UI is Pass");
												}
												else {
													homepagev2.smokeresults("Validate the chromecast disconnection pop up UI",rowno1167, "FAIL");
													test.log(LogStatus.FAIL, "Validate the chromecast disconnection pop up UI is Fail");
													BasePageV2.takeScreenshot();
												}
												try {
													homepagev2.stopCastDialogCancel.click(); 
													test.log(LogStatus.INFO, "Clicked on Close button");
													if(homepagev2.chromecastIconConnected.isDisplayed()) {
														homepagev2.smokeresults("Validate Close button functionality",rowno1169, "PASS");
														homepagev2.reportPass("Validate Close button functionality is Pass");
													}
													else {
														homepagev2.smokeresults("Validate Close button functionality",rowno1169, "FAIL");
														test.log(LogStatus.FAIL, "Validate Close button functionality is Fail");
														BasePageV2.takeScreenshot();
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Unable to click on the Close button in the stop casting dialog");
													homepagev2.smokeresults("Validate Close button functionality",rowno1169, "FAIL");
													test.log(LogStatus.FAIL, "Validate Close button functionality is Fail");			
													BasePageV2.takeScreenshot();
												}
												for(int wait3=0;wait3<10;wait3++) {
													try {
														homepagev2.chromecastIconConnected.click();
														test.log(LogStatus.INFO, "Clicked on the Chromcast icon");
														break;
													}
													catch(Exception e) {
														Thread.sleep(2000);
														if(wait==10) {
															test.log(LogStatus.FAIL, "Unable to click on Chrome cast icon");
														}
													}
												}
												try {
													homepagev2.stopCasting.click();
													test.log(LogStatus.INFO, "Clicked on Stop Casting");
													for(int wait1=0;wait1<10;wait1++) {
														if(homepagev2.chromecastIconDisconnected.isDisplayed()){
															homepagev2.smokeresults("Validate Stop Casting button functionality from chromecast disconnection popup",rowno1168, "PASS");
															homepagev2.reportPass("Validate Stop Casting button functionality from chromecast disconnection popup is Pass");
															break;
														}
														else {
															Thread.sleep(2000);
															if(wait1==10) {
																homepagev2.smokeresults("Validate Stop Casting button functionality from chromecast disconnection popup",rowno1168, "FAIL");
																test.log(LogStatus.FAIL, "Validate Stop Casting button functionality from chromecast disconnection popup is Fail");
																BasePageV2.takeScreenshot();
															}
														}
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Unable to stop casting");
												}
												break;
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Unable to click on the Chromcast icon to Disconnect");
												break;
											}
										}	
										else {
											Thread.sleep(2000);
											if(wait==15) {
												homepagev2.smokeresults("Validate the UI of chromecast icon post connecting to chromecast device",rowno1164, "FAIL");
												test.log(LogStatus.FAIL, "Validate the UI of chromecast icon post connecting to chromecast device is Fail");
												BasePageV2.takeScreenshot();
											}
										}
											
									}										
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Unable to select the first TV listed");
							}
							
					 	}
					 	else {
					 		homepagev2.smokeresults("Validate the functionality when user taps on chromecast icon from Top menu bar",rowno1163, "FAIL");
							test.log(LogStatus.FAIL, "Validate the functionality when user taps on chromecast icon from Top menu bar is Fail");
							BasePageV2.takeScreenshot();
					 	}

					 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Unable to fetch the list of TVs connected to Chromecast network");
				 }
				 
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on Chromecast icon");
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "Chromecast icon is not clickable"); 
		 }
		 
		//Launch player and verify the availability
		homepagev2.tabClick("Watch");
		if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 10)) {
			watchpagev2.firstItemInCarousal.click();
			test.log(LogStatus.INFO, "Clicked on the first item in Watch Carousal");
			//System.out.println(driver.getPageSource());
			for(int wait=0;wait<=15;wait++) {
				try {
					watchpagev2.watchFirstItemPlayer.click();
					try {
						Thread.sleep(1000);
						watchpagev2.watchFirstItemPlayerPause.click();
						if(Utilities.explicitWaitVisible(driver, watchpagev2.playerCastIconDisconnected, 10)) {
							homepagev2.smokeresults("Validate chromecast icon availability on the player",rowno1160, "PASS");
							homepagev2.reportPass("Validate chromecast icon availability on the player is Pass");
							//Verify the position of the cast icon
							try {
									xStart=watchpagev2.playerCastIconDisconnected.getLocation().getX();
									yStart=watchpagev2.playerCastIconDisconnected.getLocation().getY();
									if(xStart>=700 && yStart<=150) {
										homepagev2.smokeresults("Validate chromecast icon placement on the player",rowno1161, "PASS");
										homepagev2.reportPass("Validate chromecast icon placement on the player is Pass");
									}
									else {
										homepagev2.smokeresults("Validate chromecast icon placement on the player",rowno1161, "FAIL");
										test.log(LogStatus.FAIL, "Validate chromecast icon placement on the player is Fail");
										BasePageV2.takeScreenshot();
									}
							}
							catch(Exception e) {
								System.out.println();
								test.log(LogStatus.FAIL, "Unable to get cordinates of the cast icon");
							}
							try {
								watchpagev2.playerCastIconDisconnected.click();
								Thread.sleep(2000);
								test.log(LogStatus.INFO, "Clicked on the Chromcast icon");
								try {
								 	if(homepagev2.castTvList.size()>0) {
									 	for(int count=0;count<homepagev2.castTvList.size();count++) {
									 		test.log(LogStatus.INFO, ""+homepagev2.castTvList.get(count).getAttribute("text")+" is listed");
									 	}
										homepagev2.smokeresults("Validate the functionality when user taps on chromecast icon from player",rowno1165, "PASS");
										homepagev2.reportPass("Validate the functionality when user taps on chromecast icon from player is Pass");
										//Tap on the TV
										try {
												homepagev2.castTvList.get(0).click();
												Thread.sleep(5000);
												try {
														for(int wait2=0;wait2<=15;wait2++) {
															if(watchpagev2.playerCastIconConnected.isDisplayed()){
																homepagev2.smokeresults("Validate the UI of chromecast icon post connecting to chromecast device",rowno1166, "PASS");
																homepagev2.reportPass("Validate the UI of chromecast icon post connecting to chromecast device is Pass");
																break;
															}
															else {
																Thread.sleep(2000);
																if(wait2==15) {
																	homepagev2.smokeresults("Validate the UI of chromecast icon post connecting to chromecast device",rowno1166, "FAIL");
																	test.log(LogStatus.FAIL, "Validate the UI of chromecast icon post connecting to chromecast device is Fail");
																	BasePageV2.takeScreenshot();
																}											
															}
														}
														try {
															watchpagev2.playerCastIconConnected.click();
															try {
																if(homepagev2.stopCasting.isDisplayed()) {
																	homepagev2.smokeresults("Validate the functionality by tapping on active chromecast icon",rowno1170, "PASS");
																	homepagev2.reportPass("Validate the functionality by tapping on active chromecast icon is Pass");
																}
																else {
																	homepagev2.smokeresults("Validate the functionality by tapping on active chromecast icon",rowno1170, "FAIL");
																	test.log(LogStatus.FAIL, "Validate the functionality by tapping on active chromecast icon is Fail");
																	BasePageV2.takeScreenshot();
																}
																try {
																	homepagev2.stopCastDialogCancel.click();
																	for(int wait1=0;wait1<15;wait1++) {
																		if(homepagev2.chromecastIconConnected.isDisplayed()){
																			homepagev2.smokeresults("Validate Close button functionality",rowno1172, "PASS");
																			homepagev2.reportPass("Validate Close button functionality is Pass");
																			break;
																		}
																		else {
																			Thread.sleep(2000);
																			if(wait1==15) {
																				homepagev2.smokeresults("Validate Close button functionality",rowno1172, "FAIL");
																				test.log(LogStatus.FAIL, "Validate Close button functionalityp is Fail");
																				BasePageV2.takeScreenshot();
																			}
																		}
																	}	
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Unable to click on the Close button in the stop casting dialog");
																	homepagev2.smokeresults("Validate Close button functionality",rowno1172, "FAIL");
																	test.log(LogStatus.FAIL, "Validate Close button functionality is Fail");
																	BasePageV2.takeScreenshot();
																}
																Thread.sleep(2000);
																try {
																	watchpagev2.playerCastIconConnected.click();
																	Thread.sleep(2000);
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Unable to click on the Chromecast icon");
																}
																try {
																	homepagev2.stopCasting.click();
																	for(int wait1=0;wait1<15;wait1++) {
																		if(homepagev2.chromecastIconDisconnected.isDisplayed()){
																			homepagev2.smokeresults("Validate 'Stop Casting' button functionality from chromecast disconnection pop up",rowno1171, "PASS");
																			homepagev2.reportPass("Validate 'Stop Casting' button functionality from chromecast disconnection pop up is Pass");
																			break;
																		}
																		else {
																			Thread.sleep(2000);
																			if(wait1==15) {
																				homepagev2.smokeresults("Validate 'Stop Casting' button functionality from chromecast disconnection pop up",rowno1171, "FAIL");
																				test.log(LogStatus.FAIL, "Validate 'Stop Casting' button functionality from chromecast disconnection pop up is Fail");
																				BasePageV2.takeScreenshot();
																			}
																		}
																	}
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Unable to click on Stop Casting");
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Stop Casting is not displayed");
																homepagev2.smokeresults("Validate the functionality by tapping on active chromecast icon",rowno1170, "FAIL");
																test.log(LogStatus.FAIL, "Validate the functionality by tapping on active chromecast icon is Fail");
																BasePageV2.takeScreenshot();
															}
															
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Unable to click on the Chromecast icon in the player");
														}
												}
												catch(Exception e) {
													homepagev2.smokeresults("Validate the UI of chromecast icon post connecting to chromecast device",rowno1166, "FAIL");
													test.log(LogStatus.FAIL, "Validate the UI of chromecast icon post connecting to chromecast device is Fail");
												}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Unable to select the Chromecast TV listed in Player");
										}
										
								 	}
								 	else {
								 		homepagev2.smokeresults("Validate the functionality when user taps on chromecast icon from player",rowno1165, "FAIL");
										test.log(LogStatus.FAIL, "Validate the functionality when user taps on chromecast icon from player is Fail");
								 	}
								}
								catch(Exception e) {
									homepagev2.smokeresults("Validate the functionality when user taps on chromecast icon from player",rowno1165, "FAIL");
									test.log(LogStatus.FAIL, "Validate the functionality when user taps on chromecast icon from player is Fail");
								}
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Unable to click on the Chromecast icon");
							}
							break;
						}
						else {
							homepagev2.smokeresults("Validate chromecast icon availability on the player",rowno1160, "FAIL");
							test.log(LogStatus.FAIL, "Validate chromecast icon availability on the player is Fail");
							BasePageV2.takeScreenshot();
							break;
						}
						
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Unable to click on the Pause button of the player");
						break;
					}
				}
				catch(Exception e) {
					Thread.sleep(3000);
				}
			}
		}
		else {
			test.log(LogStatus.FAIL, "Unable to click on the first item in Watch Carousal");
		}
		 



	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
