package com.viacom.smoketestscripts;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.connection.ConnectionState;
//Author Tanisha
public class VerifyNotification extends BaseTestV2{
	
	String testName = "VerifyNotification";
	@Test(dataProvider = "getData")
	public void VerifyNotification(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyNotification");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls1071 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1071=xls1071.getRowCount("Smoke_Results")+1;
		xls1071.setCellData("Smoke_Results", "Testcase Name",rowno1071,"Verify the functionality by tapping on the downloaded content in notification widnow :Audio");

		Xls_Reader xls1114 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1114=xls1114.getRowCount("Smoke_Results")+1;
		xls1114.setCellData("Smoke_Results", "Testcase Name",rowno1114,"Verify the functionality by tapping on the downloaded content in notification widnow :Book");
				
		Xls_Reader xls1113 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1113=xls1113.getRowCount("Smoke_Results")+1;
		xls1113.setCellData("Smoke_Results", "Testcase Name",rowno1113,"Verify the UI of completely downloaded contents in notification window");

		Xls_Reader xls1003 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1003=xls1003.getRowCount("Smoke_Results")+1;
		xls1003.setCellData("Smoke_Results", "Testcase Name",rowno1003,"Verify the UI of downloads dedicated screen post tapping on saved downloaded contents");
		
		Xls_Reader xls1004 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1004=xls1004.getRowCount("Smoke_Results")+1;
		xls1004.setCellData("Smoke_Results", "Testcase Name",rowno1004,"Verify the Metadata for Episode card in Dedicated downloads screen");
		
		Xls_Reader xls1005 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1005=xls1005.getRowCount("Smoke_Results")+1;
		xls1005.setCellData("Smoke_Results", "Testcase Name",rowno1005,"Verify the Metadata for Ebook card in Dedicated downloads screen");
		
		Xls_Reader xls1006 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1006=xls1006.getRowCount("Smoke_Results")+1;
		xls1006.setCellData("Smoke_Results", "Testcase Name",rowno1006,"Verify the Metadata for Audio book card in Dedicated downloads screen");
		
		Xls_Reader xls1008 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1008=xls1008.getRowCount("Smoke_Results")+1;
		xls1008.setCellData("Smoke_Results", "Testcase Name",rowno1008,"Verify the functionality by tapping on back arrow in downloads dedicated screen");

		Xls_Reader xls988 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno988=xls988.getRowCount("Smoke_Results")+1;
		xls988.setCellData("Smoke_Results", "Testcase Name",rowno988,"Verify the behaviour post completion of download completion->Episode");

		Xls_Reader xls1029 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1029=xls1029.getRowCount("Smoke_Results")+1;
		xls1029.setCellData("Smoke_Results", "Testcase Name",rowno1029,"Verify the functionality by tapping on the downloaded content in notification window->Episode");
		
		Xls_Reader xls1025 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1025=xls1025.getRowCount("Smoke_Results")+1;
		xls1025.setCellData("Smoke_Results", "Testcase Name",rowno1025,"Verify if downloading content is being displayed in device notification window->Episode");

		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver, test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver, test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 int err1113=0;
		 int err1003=0;
		 int err1004=0;
		 int err1005=0;
		 int err1006=0;
		 int err1025=0;
		 //downloadsv2.deleteAllDownloads();
//Clear all existing notifications
		 driver.openNotifications();
		 if(Utilities.explicitWaitVisible(driver, downloadsv2.notificationsClear, 5)) {
			 try {
				 downloadsv2.notificationsClear.click(); 
				 //Close the notification
				 driver.navigate().back();
			 }
			 catch(Exception e) {
				 driver.navigate().back();
			 }
		 }
		 else {
			 driver.navigate().back();
		 }
		 
//Audio in Notification
		 homepagev2.tabClick("Listen");
		 if(Utilities.explicitWaitVisible(driver, homepagev2.Listen_tab, 5)) {
			String firstItemCarousal="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container']/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
			try {
				WebElement firstItem=driver.findElement(By.xpath(firstItemCarousal));
				String audioNameBefore=firstItem.getAttribute("text");
				audioNameBefore=homepagev2.convertCamelCase(audioNameBefore);
				firstItem.click();
				test.log(LogStatus.INFO, "Clicked on the Audio "+audioNameBefore+" from Audio Carousal");
				String downloadAudioLoc="//android.widget.TextView[@text='Download Audiobook' or @text='Download AudioBook']";
				boolean downloadAudioText=Utilities.verticalSwipeAndFind(driver,downloadAudioLoc);
				if(downloadAudioText==true) {
					test.log(LogStatus.INFO, "Scrolled to \"Download Audiobook\" text in Audio details page");
					if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookText, 10)) {
						listenpagev2.downloadAudioBookText.click();
						test.log(LogStatus.INFO, "Clicked on Download Audiobook");
						for(int wait=0;wait<=15;wait++) {
							if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioText, 10)) {
								test.log(LogStatus.INFO, "Download completed ..");
								Thread.sleep(5000);
								driver.openNotifications();
								Thread.sleep(7000);
								String notificationAudioNameLoc="//android.widget.TextView[@resource-id='android:id/title']";
								String notificationAudioDownloadCompleteLoc="//android.widget.TextView[@text='Download complete' or @text='Download Complete']";
								boolean notificationFound=false;
								int count=0;
								String temp="";
								String tempLast="";
								while(notificationFound==false) {
									count++;
									List<WebElement> appNames=driver.findElements(By.xpath(notificationAudioNameLoc));
									for(int name=0;name<appNames.size();name++) {
										/*if(name==appNames.size()-1) {
											if(!tempLast.equals(appNames.get(name).getAttribute("text"))) {
												tempLast=appNames.get(name).getAttribute("text");
											}
											else 
												break;	
										}*/
										if(appNames.get(name).getAttribute("text").equalsIgnoreCase(audioNameBefore)){
											try {
												try {
													driver.findElement(By.xpath(notificationAudioDownloadCompleteLoc));
													test.log(LogStatus.INFO, "String 'Download Complete' is displayed in the notification");
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "String 'Download Complete' is not displayed in the notification");
												}
												BasePageV2.takeScreenshot();
												appNames.get(name).click();
												notificationFound=true;
												try {
													if(Utilities.explicitWaitVisible(driver, downloadsv2.downloadsPageHeader, 10)) {
														test.log(LogStatus.INFO, "Application navigated to Downloads screen on tapping the Notification");
														test.log(LogStatus.INFO, "\"Downloads\" text is verified present");
														if(Utilities.explicitWaitVisible(driver, homepagev2.notificationsDownloadsBackButton, 10)) {
															test.log(LogStatus.INFO, "Back button is verified present");
														}
														else{
															test.log(LogStatus.FAIL, "Back button is not present");
															err1003++;
														}
														boolean foundAudio=false;
														try {
															for(int titles=0;titles<downloadsv2.downloadsTitles.size();titles++) {
																if(downloadsv2.downloadsTitles.get(titles).getAttribute("text").equalsIgnoreCase(audioNameBefore)){
																	test.log(LogStatus.INFO, "The downloaded audio is found in Downloads page");
																	homepagev2.smokeresults("Verify the functionality by tapping on the downloaded content in notification windnow :Audio",rowno1071, "PASS");
																	homepagev2.reportPass("Verify the functionality by tapping on the downloaded content in notification windnow :Audio is Pass");
																	//Verify meta data
																	try {
																		driver.findElement(By.xpath("//android.widget.TextView[@text=\""+audioNameBefore+"\"]"));
																		test.log(LogStatus.INFO, "Located audio name "+audioNameBefore+"in Downloads");
																	}
																	catch(Exception e) {
																		test.log(LogStatus.INFO, "Failed to locate audio name "+audioNameBefore+" in Downloads");
																		err1006++;
																	}
																	try {
																		String size=driver.findElementByXPath("//android.widget.TextView[@text=\'"+audioNameBefore+"\']/ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']").getAttribute("text");
																		test.log(LogStatus.INFO, ""+size);
																		if(size.contains("MB")) {
																			test.log(LogStatus.INFO, "Memory Size Unit MB is displayed "+size);
																		}
																		else {
																			test.log(LogStatus.FAIL, "Memory Size Unit MB for "+audioNameBefore+" is not displayed");
																			err1006++;
																		}	
																	}
																	catch(Exception e) {
																		test.log(LogStatus.FAIL, "Failed to locate Memory Size Unit MB for "+audioNameBefore);
																		err1006++;
																	}		
																	if(err1006==0) {
																		homepagev2.smokeresults("Verify the Metadata for Audio book card in Dedicated downloads screen",rowno1006, "PASS");
																		homepagev2.reportPass("Verify the Metadata for Audio book card in Dedicated downloads screen is Pass");
																	}
																	else {
																		homepagev2.smokeresults("Verify the Metadata for Audio book card in Dedicated downloads screen",rowno1006, "FAIL");
																		test.log(LogStatus.FAIL, "Verify the Metadata for Audio book card in Dedicated downloads screen is Fail");
																		basepagev2.takeScreenshot();
																	}
																	if(Utilities.explicitWaitVisible(driver, downloadsv2.playDownloadIcon, 10)) {
																		test.log(LogStatus.INFO, "Play icon is displayed");
																	}
																	else {
																		test.log(LogStatus.INFO, "Play icon is not displayed");
																		err1003++;
																	}
																	foundAudio=true;
																	break;
																}
															}	
															if(foundAudio==false) {
																test.log(LogStatus.FAIL, "The downloaded audio is NOT found in Downloads page");
																err1003++;
																homepagev2.smokeresults("Verify the functionality by tapping on the downloaded content in notification widnow :Audio",rowno1071, "FAIL");
																test.log(LogStatus.FAIL, "Verify the functionality by tapping on the downloaded content in notification widnow :Audio is Fail");
																homepagev2.takeScreenshot();
															}
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Unable to fetch the Downloaded Audio Title from Downloads page");
														}												
													}
													else {
														test.log(LogStatus.FAIL, "Application did not navigate to Downloads screen on tapping the Notification");
														err1003++;
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Application did not navigate to Downloads screen on tapping the Notification");
												}
												break;
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Unable to click on the notification");
												notificationFound=true;
												break;
											}
										}
									}
									Utilities.verticalSwipe(driver);
									if(count==15) {
										test.log(LogStatus.FAIL, "Unable to find the notification");
										err1003++;
										break;
									}
								}
								break;
							}
							else {
								Thread.sleep(3000);
								if(wait==15) {
									test.log(LogStatus.FAIL, "The Audio did not get downloaded");
									err1003++;
								}
							}
						}				 		 		 	
					}
					else {
						test.log(LogStatus.FAIL, "\"Download Audiobook\" text in Audio Details page is not clickable");	
						err1003++;
					}
				}
				else {
					test.log(LogStatus.FAIL, "Unable to scroll to \"Download Audiobook\" text");	
					err1003++;
				}	
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to click on the first item in Carousal in Listen tab");
				err1003++;
			}
		}
		else {
			test.log(LogStatus.FAIL, "Listen tab is not visible");	
			err1003++;
		}
		if(err1003==0) {
			homepagev2.smokeresults("Verify the UI of downloads dedicated screen post tapping on saved downloaded contents",rowno1003, "PASS");
			homepagev2.reportPass("Verify the UI of downloads dedicated screen post tapping on saved downloaded contents is Pass");
		}
		else {
			homepagev2.smokeresults("Verify the UI of downloads dedicated screen post tapping on saved downloaded contents",rowno1003, "FAIL");
			test.log(LogStatus.FAIL, "Verify the UI of downloads dedicated screen post tapping on saved downloaded contents is Fail");
			basepagev2.takeScreenshot();
		}
		//Verification of Back button functionality in Downloads
		if(Utilities.explicitWaitClickable(driver, homepagev2.notificationsDownloadsBackButton, 10)) {
			try {
				homepagev2.notificationsDownloadsBackButton.click();
				test.log(LogStatus.INFO, "Clicked on Back button in Downloads screen");
				if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioText, 10)) {
					test.log(LogStatus.INFO, "Audio details page is displayed");
					homepagev2.smokeresults("Verify the functionality by tapping on back arrow in downloads dedicated screen",rowno1008, "PASS");
					homepagev2.reportPass("Verify the functionality by tapping on back arrow in downloads dedicated screen is Pass");
				}
				else {
					test.log(LogStatus.INFO, "Audio details page is not displayed");
					homepagev2.smokeresults("Verify the functionality by tapping on back arrow in downloads dedicated screen",rowno1008, "FAIL");
					test.log(LogStatus.FAIL, "Verify the functionality by tapping on back arrow in downloads dedicated screen is Fail");
					basepagev2.takeScreenshot();
					driver.navigate().back();
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on Back button in Downloads screen");
				homepagev2.smokeresults("Verify the functionality by tapping on back arrow in downloads dedicated screen",rowno1008, "FAIL");
				test.log(LogStatus.FAIL, "Verify the functionality by tapping on back arrow in downloads dedicated screen is Fail");
				driver.navigate().back();
				
			}
		}
		else{
			test.log(LogStatus.FAIL, "Back button is not present");
			err1003++;
		}
		
//Download Episode
		 String episodeNameBefore="";
		 String episodeDetailsBefore="";
		 
		 driver.navigate().back();
		 Utilities.verticalSwipeReverse(driver);
		 Thread.sleep(2000);
		 try {
			 homepagev2.watch_tab.click();
		 }
		 catch(Exception e){
			 test.log(LogStatus.FAIL, "Unable to click on Watch tab");
		 }
		 test.log(LogStatus.INFO, "Clicked on Watch tab");
			//Scroll to ALL CHARACTERS and click on the first character
			 String allCharacters="//android.widget.TextView[@text='ALL KIDS CHARACTERS']";
			 boolean allCharactersPresence=Utilities.verticalSwipeAndFind(driver,allCharacters);
			 if(allCharactersPresence==true) {
				 test.log(LogStatus.INFO, "Scrolled to ALL KIDS CHARACTERS tray");
				 //Click on the first item
				 try {
					 String firstCharacterLoc="//android.widget.TextView[@text='ALL KIDS CHARACTERS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView";
					 Utilities.verticalSwipeAndFind(driver,firstCharacterLoc);
					 watchpagev2.allCharactersFirstItem.click();
					 test.log(LogStatus.INFO, "Clicked on first item of ALL KIDS CHARACTERS tray");
					 String downloadEpLoc="//android.widget.TextView[@text='Download Episodes']";
					 boolean downloadEpText=Utilities.verticalSwipeAndFind(driver,downloadEpLoc);
					 if(downloadEpText==true) {
						 test.log(LogStatus.INFO, "Scrolled to \"Download Episodes\" text in Show details page");
						 if(Utilities.explicitWaitClickable(driver, showpagev2.downloadEpisodesText, 3)) {
							 showpagev2.downloadEpisodesText.click();
							 test.log(LogStatus.INFO,"Clicked on \"Download Episodes\" text in Show Details page");
							 if(Utilities.explicitWaitVisible(driver, showpagev2.notAddedEpisode4, 10)) {
								 episodeNameBefore=showpagev2.notAddedEpisode4.getAttribute("text");
								 if(Utilities.explicitWaitVisible(driver, showpagev2.notAddedEpisode4, 10)) {
									 try {
										episodeDetailsBefore=showpagev2.notAddedEpisode4.getAttribute("text");
									 }
									 catch(Exception e) {
										 test.log(LogStatus.FAIL, "Unable to fetch Episode details");
									 }
								 }
								 if(Utilities.explicitWaitClickable(driver, showpagev2.notAddedEpisode4, 10)) {
									 showpagev2.notAddedEpisode4.click();
									 test.log(LogStatus.INFO,"Clicked on episode to download: "+episodeNameBefore);
									 String notificationEpisodeNameLoc="//android.widget.TextView[@resource-id='android:id/title']";
									//Verify notifications when download is in progress
									 driver.openNotifications();
									 basepagev2.takeScreenshot();
									 try {
										 driver.findElement(By.xpath("//android.widget.TextView[@text=\'"+episodeNameBefore+"\']"));
										 test.log(LogStatus.INFO, "Located episode in Notifications: "+episodeNameBefore);
									 }
									 catch(Exception e) {
										 test.log(LogStatus.FAIL, "Failed to find episode name in Notifications");
										 err1025++;
									 }
									 try {
										 String episodeSize=driver.findElement(By.xpath("//android.widget.TextView[@text=\'"+episodeNameBefore+"\']/..//android.widget.TextView[@resource-id='android:id/text_line_1']")).getText();
										 test.log(LogStatus.INFO, "Located episode size in Notifications: "+episodeSize);
										 if(episodeSize.matches("\\d\\d.\\d\\d MB / \\d\\d.\\d\\d MB") || episodeSize.matches("\\d.\\d\\d MB / \\d\\d.\\d\\d MB") ) {
											 test.log(LogStatus.INFO, "Episode size is displayed in required format");
										 }
										 else {
											 test.log(LogStatus.INFO, "Episode size is not displayed in required format");
											 err1025++;
										 }
										 
									 }
									 catch(Exception e) {
										 test.log(LogStatus.FAIL, "Failed to find episode size in Notifications");
										 err1025++;
									 }
									 try {
										 driver.findElement(By.xpath("//android.widget.ProgressBar"));
										 test.log(LogStatus.INFO, "Located Progress Bar in Notifications");
									 }
									 catch(Exception e) {
										 test.log(LogStatus.FAIL, "Failed to locate Progress Bar in Notifications");
										 err1025++;
									 }
									 try {
										 driver.findElement(By.xpath("//android.widget.Button[@text='CANCEL']"));
										 test.log(LogStatus.INFO, "Located Cancel button in Notifications");
									 }
									 catch(Exception e) {
										 test.log(LogStatus.FAIL, "Failed to locate Cancel button in Notifications");
										 err1025++;
									 }
									 //verification
									 if(err1025==0) {
										 homepagev2.smokeresults("Verify if downloading content is being displayed in device notification window->Episode",rowno1025, "PASS");
										 test.log(LogStatus.PASS, "Verify if downloading content is being displayed in device notification window->Episode is Pass"); 
									 }
									 else {
										 homepagev2.smokeresults("Verify if downloading content is being displayed in device notification window->Episode",rowno1025, "FAIL");
										 test.log(LogStatus.FAIL, "Verify if downloading content is being displayed in device notification window->Episode is Fail");
									 }
									 driver.navigate().back();
									 BasePageV2.takeScreenshot();
									 //Wait for Download completion
									 for(int wait=0;wait<=15;wait++) {
										 if(Utilities.explicitWaitVisible(driver, showpagev2.downloadedEpisode4, 2)) {
											 test.log(LogStatus.INFO, "Episode is completely downloaded");
											 //Open notifications
											 Thread.sleep(7000);
											 driver.openNotifications();
											 basepagev2.takeScreenshot();
											 Thread.sleep(3000);
											 String notificationEpisodeDownloadCompleteLoc="//android.widget.TextView[@text='Download complete' or @text='Download Complete']";
											 boolean notificationFound=false;
											 int count=0;
											 String temp="";
											 String tempLast="";
											 while(notificationFound==false) {
												count++;
												List<WebElement> appNames=driver.findElements(By.xpath(notificationEpisodeNameLoc));
												for(int name=0;name<appNames.size();name++) {
													/*if(name==appNames.size()-1) {
														if(!tempLast.equals(appNames.get(name).getAttribute("text"))) {
															tempLast=appNames.get(name).getAttribute("text");
														}
														else 
															break;	
													}*/
													if(appNames.get(name).getAttribute("text").equalsIgnoreCase(episodeNameBefore)){
														try {
															try {
																driver.findElement(By.xpath(notificationEpisodeDownloadCompleteLoc));
																test.log(LogStatus.INFO, "String 'Download Complete' is displayed in the notification");
																homepagev2.smokeresults("Verify the behaviour post completion of download completion->Episode",rowno988, "PASS");
																test.log(LogStatus.PASS, "Verify the behaviour post completion of download completion->Episode is Pass");
											
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "String 'Download Complete' is not displayed in the notification");
																homepagev2.smokeresults("Verify the behaviour post completion of download completion->Episode",rowno988, "FAIL");
																test.log(LogStatus.FAIL, "Verify the behaviour post completion of download completion->Episode is Fail");
																BasePageV2.takeScreenshot();
															}
															
															appNames.get(name).click();
															notificationFound=true;
															try {
																if(Utilities.explicitWaitVisible(driver, downloadsv2.downloadsPageHeader, 10)) {
																	test.log(LogStatus.INFO, "Application navigated to Downloads screen on tapping the Notification");
																	homepagev2.smokeresults("Verify the functionality by tapping on the downloaded content in notification window->Episode",rowno1029, "PASS");
																	test.log(LogStatus.PASS, "Verify the functionality by tapping on the downloaded content in notification window->Episode is Pass");
																	boolean foundEpisode=false;
																	try {
																		/*for(int titles=0;titles<downloadsv2.downloadsTitles.size();titles++) {*/
																			/*if(downloadsv2.downloadsTitles.get(titles).getAttribute("text").equalsIgnoreCase(episodeNameBefore)){*/
																				test.log(LogStatus.INFO, "The downloaded episode is found in Downloads page");
																				//Verify meta data
																				try {
																					driver.findElement(By.xpath("//android.widget.TextView[@text=\""+episodeNameBefore+"\"]"));
																					test.log(LogStatus.INFO, "Located episode name "+episodeNameBefore+" in Downloads");
																				}
																				catch(Exception e) {
																					test.log(LogStatus.INFO, "Failed to locate episode name "+episodeNameBefore+" in Downloads");
																					err1004++;
																				}
																				try {
																					List<WebElement> sizes=driver.findElementsByXPath("//android.widget.TextView[@text=\'"+episodeNameBefore+"\']/ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']");
																					String size=sizes.get(1).getAttribute("text");
																					if(size.contains("MB")) {
																						test.log(LogStatus.INFO, "Memory Size Unit MB is displayed "+size);
																					}
																					else {
																						test.log(LogStatus.FAIL, "Memory Size Unit MB for "+episodeNameBefore+" is not displayed");
																						err1004++;
																					}	
																				}
																				catch(Exception e) {
																					test.log(LogStatus.FAIL, "Failed to locate Memory Size Unit MB for "+episodeNameBefore);
																					err1004++;
																				}		
																				if(err1004==0) {
																					homepagev2.smokeresults("Verify the Metadata for Episode card in Dedicated downloads screen",rowno1004, "PASS");
																					homepagev2.reportPass("Verify the Metadata for Episode card in Dedicated downloads screen is Pass");
																				}
																				else {
																					homepagev2.smokeresults("Verify the Metadata for Episode card in Dedicated downloads screen",rowno1004, "FAIL");
																					test.log(LogStatus.FAIL, "Verify the Metadata for Episode card in Dedicated downloads screen is Fail");
																					basepagev2.takeScreenshot();
																				}
																				foundEpisode=true;
																				break;
																		/*	}
																			
																		}	
																		if(foundEpisode==false) {
																			test.log(LogStatus.FAIL, "The downloaded episode is NOT found in Downloads page");
																		}*/
																	}
																	catch(Exception e) {
																		test.log(LogStatus.FAIL, "Unable to fetch the Downloaded Episode from Downloads page");
																	}	
																
																}
																else {
																	test.log(LogStatus.FAIL, "Application did not navigate to Downloads screen on tapping the Notification");
																	homepagev2.smokeresults("Verify the functionality by tapping on the downloaded content in notification window->Episode",rowno1029, "FAIL");
																	test.log(LogStatus.FAIL, "Verify the functionality by tapping on the downloaded content in notification window->Episode is Fail");
																	BasePageV2.takeScreenshot();
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Application did not navigate to Downloads screen on tapping the Notification");
															}
															break;
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Unable to click on the notification");
															notificationFound=true;
															break;
														}
													}
												}
												Utilities.verticalSwipe(driver);
												if(count==15) {
													test.log(LogStatus.FAIL, "Unable to find the notification");
													driver.navigate().back();
													break;
												}
											}
											break;	 
										 }
										 else{
											Thread.sleep(2000); 
											if(wait==15) {
												test.log(LogStatus.FAIL, "Episode did not complete download");
											}
										 }
									 }								 								 
								 }
								 else 
									test.log(LogStatus.FAIL, "Episode is not clickable");	
							 }
							 else 
								test.log(LogStatus.FAIL, "Episode is not visible");	
						 }
						 else
							 test.log(LogStatus.FAIL,"Failed to click on \"Download Episodes\" text in Show details page"); 
					 }
					 else
						 test.log(LogStatus.FAIL,"Failed to locate \"Download Episodes\" text in Show details page"); 
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL,"Failed to click on first item under ALL CHARACTERS tray"); 
				 }			 
			 }
			 else
				 test.log(LogStatus.FAIL,"Unable to scroll to ALL KIDS CHARACTERS tray");  
		 

		 driver.navigate().back();
		 driver.navigate().back();
		 driver.navigate().back();
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 try {
			 homepagev2.read_tab.click();
		 }
		 catch(Exception e){
			 test.log(LogStatus.FAIL, "Unable to click on Read tab");
		 }
		 if(Utilities.explicitWaitVisible(driver, homepagev2.read_tab, 5)) {
			 String firstItemCarousalRead="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container']/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
			 try {
				WebElement firstItemRead=driver.findElement(By.xpath(firstItemCarousalRead));
				String bookNameBefore=firstItemRead.getAttribute("text");
				bookNameBefore=homepagev2.convertCamelCase(bookNameBefore);
				firstItemRead.click();
				test.log(LogStatus.INFO, "Clicked on the Book "+bookNameBefore+" from Read Carousal");
				String downloadBookLoc="//android.widget.TextView[@text='Download Book' or @text='Download book']";
				boolean downloadBookText=Utilities.verticalSwipeAndFind(driver,downloadBookLoc);
				if(downloadBookText==true) {
					test.log(LogStatus.INFO, "Scrolled to \"Download Book\" text in Book details page");
					basepagev2.takeScreenshot();
					if(Utilities.explicitWaitClickable(driver, readpagev2.downloadBookText, 10)) {
						readpagev2.downloadBookText.click();
						test.log(LogStatus.INFO, "Clicked on Download Book");
						for(int wait=0;wait<=15;wait++) {
							if(Utilities.explicitWaitVisible(driver, readpagev2.downloadedBookText, 5)) {
								test.log(LogStatus.INFO, "Download completed ..");
								Thread.sleep(7000);
								driver.openNotifications();
								basepagev2.takeScreenshot();
								Thread.sleep(3000);
								String notificationBookNameLoc="//android.widget.TextView[@resource-id='android:id/title']";
								String notificationBookDownloadCompleteLoc="//android.widget.TextView[@text='Download complete' or @text='Download Complete']";
								
								boolean notificationFound=false;
								int count=0;
								String tempLast="";
								while(notificationFound==false) {
									count++;
									List<WebElement> appNames=driver.findElements(By.xpath(notificationBookNameLoc));
									for(int name=0;name<appNames.size();name++) {
										/*if(name==appNames.size()-1) {
											if(!tempLast.equals(appNames.get(name).getAttribute("text"))) {
												tempLast=appNames.get(name).getAttribute("text");
											}
											else 
												break;	
										}*/
										if(appNames.get(name).getAttribute("text").equalsIgnoreCase(bookNameBefore)){
											try {
												try {
													driver.findElement(By.xpath(notificationBookDownloadCompleteLoc));		
													test.log(LogStatus.INFO, "String 'Download Complete' is displayed in the notification");	
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "String 'Download Complete' is not displayed in the notification");
													err1113++;
												}
												appNames.get(name).click();
												notificationFound=true;
												try {
													if(Utilities.explicitWaitVisible(driver, downloadsv2.downloadsPageHeader, 10)) {
														test.log(LogStatus.INFO, "Application navigated to Downloads screen on tapping the Notification");
														boolean foundBook=false;
														try {
															for(int titles=0;titles<downloadsv2.downloadsTitles.size();titles++) {
																if(downloadsv2.downloadsTitles.get(titles).getAttribute("text").equalsIgnoreCase(bookNameBefore)){
																	test.log(LogStatus.INFO, "The downloaded book is found in Downloads page");
																	homepagev2.smokeresults("Verify the functionality by tapping on the downloaded content in notification window :Book",rowno1114, "PASS");
																	homepagev2.reportPass("Verify the functionality by tapping on the downloaded content in notification window :Book is Pass");
																	foundBook=true;

																	//Verify meta data
																	try {
																		driver.findElement(By.xpath("//android.widget.TextView[@text=\""+bookNameBefore+"\"]"));
																		test.log(LogStatus.INFO, "Located book name "+bookNameBefore+"in Downloads");
																	}
																	catch(Exception e) {
																		test.log(LogStatus.INFO, "Failed to locate book name "+bookNameBefore+" in Downloads");
																		err1005++;
																	}
																	try {
																		String size=driver.findElementByXPath("//android.widget.TextView[@text=\'"+bookNameBefore+"\']/ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']").getAttribute("text");
																		test.log(LogStatus.INFO, ""+size);
																		if(size.contains("MB")) {
																			test.log(LogStatus.INFO, "Memory Size Unit MB is displayed "+size);
																		}
																		else {
																			test.log(LogStatus.FAIL, "Memory Size Unit MB for "+bookNameBefore+" is not displayed");
																			err1005++;
																		}	
																	}
																	catch(Exception e) {
																		test.log(LogStatus.FAIL, "Failed to locate Memory Size Unit MB for "+bookNameBefore);
																		err1005++;
																	}		
																	if(err1005==0) {
																		homepagev2.smokeresults("Verify the Metadata for Ebook card in Dedicated downloads screen",rowno1005, "PASS");
																		homepagev2.reportPass("Verify the Metadata for Ebook card in Dedicated downloads screen is Pass");
																	}
																	else {
																		homepagev2.smokeresults("Verify the Metadata for Ebook card in Dedicated downloads screen",rowno1005, "FAIL");
																		test.log(LogStatus.FAIL, "Verify the Metadata for Ebook card in Dedicated downloads screen screen is Fail");
																		basepagev2.takeScreenshot();
																	}
																	break;
																}
															}	
															if(foundBook==false) {
																test.log(LogStatus.FAIL, "The downloaded book is NOT found in Downloads page");
																homepagev2.smokeresults("Verify the functionality by tapping on the downloaded content in notification widnow :Book",rowno1114, "FAIL");
																test.log(LogStatus.FAIL, "Verify the functionality by tapping on the downloaded content in notification widnow :Book is Fail");
																BasePageV2.takeScreenshot();
															}
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Unable to fetch the Downloaded Book Title from Downloads page");
														}														
													}
													else {
														test.log(LogStatus.FAIL, "Application did not navigate to Downloads screen on tapping the Notification");
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Application did not navigate to Downloads screen on tapping the Notification");
												}
												break;
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Unable to click on the notification");
												notificationFound=true;
												break;
											}
										}
										else {
											test.log(LogStatus.FAIL, "Book title does in notification does not match with downloaded book");
											err1113++;
										}
									}
									Utilities.verticalSwipe(driver);
									if(count==15) {
										test.log(LogStatus.FAIL, "Unable to find the notification");
										break;
									}
								}
								break;
							}
							else {
								Thread.sleep(3000);
								if(wait==15) {
									test.log(LogStatus.FAIL, "The Book did not get downloaded");
									err1113++;
								}
							}
						}
						//final verification of 1113
						if(err1113==0) {
							homepagev2.smokeresults("Verify the UI of completely downloaded contents in notification window",rowno1113, "PASS");
							homepagev2.reportPass("Verify the UI of completely downloaded contents in notification window is PASS");
						}
						else {
							homepagev2.smokeresults("Verify the UI of completely downloaded contents in notification window",rowno1113, "FAIL");
							test.log(LogStatus.FAIL, "Verify the UI of completely downloaded contents in notification window is FAIL");
							basepagev2.takeScreenshot();
						}
					}
					else {
						test.log(LogStatus.FAIL, "\"Download Book\" text in Book Details page is not clickable");	
					}
				}
				else {
					test.log(LogStatus.FAIL, "Unable to scroll to \"Download Book\" text");	
				}	
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to click on the first item in Carousal in Read tab");
			}
		}
		else {
			test.log(LogStatus.FAIL, "Read tab is not visible");	
		}			
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
