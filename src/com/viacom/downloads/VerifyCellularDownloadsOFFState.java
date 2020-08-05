package com.viacom.downloads;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyCellularDownloadsOFFState extends BaseTestV2{
	
	String testName = "VerifyCellularDownloadsOFFState";
	@Test(dataProvider = "getData")
	public void VerifyCellularDownloadsOFFState(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyCellularDownloadsOFFState");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		int err872=0;
		
		Xls_Reader xls438 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno438=xls438.getRowCount("Smoke_Results")+1;
		xls438.setCellData("Smoke_Results", "Testcase Name",rowno438,"Verify the navigation tapping on 'Read' button post downloading a book");
			
		Xls_Reader xls871 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno871=xls871.getRowCount("Smoke_Results")+1;
		xls871.setCellData("Smoke_Results", "Testcase Name",rowno871,"Verify download button functionality when 'Cellular Downloads' button is in 'OFF' state");

		Xls_Reader xls872 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno872=xls872.getRowCount("Smoke_Results")+1;
		xls872.setCellData("Smoke_Results", "Testcase Name",rowno872,"Verify the UI of Download disabled Pop-up");
		
		Xls_Reader xls873 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno873=xls873.getRowCount("Smoke_Results")+1;
		xls873.setCellData("Smoke_Results", "Testcase Name",rowno873,"Verify the functonality of OK button in Downloads disabled pop-up");

		Xls_Reader xls426 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno426=xls426.getRowCount("Smoke_Results")+1;
		xls426.setCellData("Smoke_Results", "Testcase Name",rowno426,"Overlay of Ebook");
		
		Xls_Reader xls428 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno428=xls428.getRowCount("Smoke_Results")+1;
		xls428.setCellData("Smoke_Results", "Testcase Name",rowno428,"Verify the UI of Post preview overlay");
		
		
		//Launch Voot kids app
		//Login module to be added
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
		 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 downloadsv2.deleteAllDownloads();
		 int err426=0;
		 int err428=0;
		 String bookNameFromHomePage="";
		 String bookAuthorFromHomePage="";
		 
	//Verification of 426
	if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 5)) {
		homepagev2.read_tab.click();
		test.log(LogStatus.INFO, "Clicked on Read tab");
		if(Utilities.explicitWaitClickable(driver, readpagev2.firstBookBelowCarousal, 10)) {
			for(int trybookname=0;trybookname<=3;trybookname++) {
				try {
					bookNameFromHomePage=readpagev2.firstBookNameBelowCarousal.getAttribute("text");
					test.log(LogStatus.INFO, "Fetched book name "+bookNameFromHomePage);
					break;
				}
				catch(Exception e) {
					Thread.sleep(1000);
					if(trybookname==3)
						test.log(LogStatus.FAIL, "Unable to fetch book name from Carosual in Read tab");
				}
			}
			for(int trybookauthor=0;trybookauthor<=3;trybookauthor++) {
				try {
					bookAuthorFromHomePage=readpagev2.firstBookAuthorBelowCarousal.getAttribute("text");
					test.log(LogStatus.INFO, "Fetched book author "+bookAuthorFromHomePage);
					break;
				}
				catch(Exception e) {
					Thread.sleep(1000);
					if(trybookauthor==3)
						test.log(LogStatus.FAIL, "Unable to fetch book author from Carosual in Read tab");
				}
			}
			try {
				readpagev2.firstBookBelowCarousal.click();
				test.log(LogStatus.INFO, "Clicked on first book under Read Carousal");
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on first book under Read Carousal");
			}
			BasePageV2.takeScreenshot();
			//Check if Preview button is clickable
			if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10)) {
				readpagev2.previewButton.click();
				test.log(LogStatus.INFO, "Clicked on PREVIEW button");
				for(int wait=0;wait<15;wait++) {
					try {
						readpagev2.animationView.isDisplayed();
					}
					catch(Exception e){
						break;
					}
				}
				if(Utilities.explicitWaitVisible(driver, readpagev2.eBookReader, 5)) {
					BasePageV2.takeScreenshot();
					boolean foundLastPage=false;
					int wait=0;
					while(foundLastPage==false) {
						try {
							driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/rtv_download']"));
							foundLastPage=true;
							break;
						}
						catch(Exception e) {
							Utilities.horizontalSwipe(driver);
							wait++;
							if(wait==25) {
								test.log(LogStatus.FAIL, "Horizontal swipe to find the last page of the book has failed");
								break;
							}
						}
					}
				}
				if(Utilities.explicitWaitVisible(driver, readpagev2.downloadInPopup, 10)) {
					test.log(LogStatus.INFO, "Completed swiping to the end of the page");
					
					if(Utilities.explicitWaitVisible(driver, readpagev2.downloadInPopupEbookImage, 10)){
						test.log(LogStatus.INFO, "Ebook Image is displayed");
					}
					else {
						test.log(LogStatus.FAIL, "Ebook Image is NOT displayed");
						err428++;
					}	
					
					if(Utilities.explicitWaitVisible(driver, readpagev2.downloadInPopupEbookName, 10)){
						if(readpagev2.downloadInPopupEbookName.getAttribute("text").equalsIgnoreCase(bookNameFromHomePage)){
							test.log(LogStatus.INFO, "Correct Ebook Name is displayed in the download pop up");
						}
						else {
							test.log(LogStatus.INFO, "Incorrect Ebook Name is displayed in the download pop up");
							err428++;
						}		
					}
					else {
						test.log(LogStatus.FAIL, "Ebook Name is NOT displayed");
						err428++;
						
					}	
					
					if(Utilities.explicitWaitVisible(driver, readpagev2.downloadInPopupEbookAuthor, 10)){
						test.log(LogStatus.INFO, "Ebook Author is displayed");
						if(readpagev2.downloadInPopupEbookAuthor.getAttribute("text").equalsIgnoreCase(bookAuthorFromHomePage)){
							test.log(LogStatus.INFO, "Correct Ebook Author is displayed in the download pop up");
						}
						else {
							test.log(LogStatus.INFO, "Incorrect Ebook Author is displayed in the download pop up");
							err428++;
						}		
					}
					else {
						test.log(LogStatus.FAIL, "Ebook Name is NOT displayed");
						err428++;
					}	
					
					if(Utilities.explicitWaitVisible(driver, readpagev2.downloadingText, 10)) {
						if(readpagev2.downloadingText.getAttribute("text").equals("Download this e-Book to continue reading")) {
							test.log(LogStatus.INFO, "An overlay of Ebook with message 'Download this e-Book to continue reading' is displayed");
						}
						else {
							test.log(LogStatus.FAIL, "An overlay of Ebook with message 'Download this e-Book to continue reading' is NOT displayed");
							err426++;
						}
					}
					else {
						test.log(LogStatus.FAIL, "Downloading text is not displayed in the pop up");	
					}
					
					if(readpagev2.downloadInPopup.getAttribute("text").equals("DOWNLOAD")) {
						test.log(LogStatus.INFO, "An overlay of Ebook with message 'DOWNLOAD' is displayed");
					}
					else {
						test.log(LogStatus.FAIL, "An overlay of Ebook with message 'DOWNLOAD' is NOT displayed");
						err426++;
					}					
									
				}
				else {
					test.log(LogStatus.FAIL, "Download pop up is not getting displayed");
					err426++;
				}
				//Final verification of 426
				if(err426==0) {
					homepagev2.smokeresults("Overlay of Ebook",rowno426, "PASS");
					test.log(LogStatus.PASS, "Overlay of Ebook is PASS");
				}
				else {
					homepagev2.smokeresults("Overlay of Ebook",rowno426, "FAIL");
					test.log(LogStatus.FAIL, "Overlay of Ebook is FAIL");
				}
				
				if(err428==0) {
					homepagev2.smokeresults("Verify the UI of Post preview overlay",rowno428, "PASS");
					test.log(LogStatus.PASS,"Verify the UI of Post preview overlay is PASS");
				}
				else {
					homepagev2.smokeresults("Verify the UI of Post preview overlay",rowno428, "FAIL");
					test.log(LogStatus.FAIL, "Verify the UI of Post preview overlay is FAIL");
				}
				BasePageV2.takeScreenshot();
				if(Utilities.explicitWaitClickable(driver, readpagev2.downloadInPopupClose, 10)) {
					readpagev2.downloadInPopupClose.click();
					test.log(LogStatus.INFO, "Closed the download pop up");
				}
				else {
					test.log(LogStatus.FAIL, "Unable to click on Close of the download pop up");
				}
				
			}
			else 
				test.log(LogStatus.FAIL, "PREVIEW button is not clickable");
			
		}
		else
			test.log(LogStatus.FAIL, "First book in Carousal is not clickable");
		
	}
	else
		test.log(LogStatus.FAIL, "Read tab is not clickable");
		 
		 		 	
	//Verification of 438
	String downloadBookLoc="//android.widget.TextView[@text='Download Book' or @text='Download book']";
	boolean downloadBookText=Utilities.verticalSwipeAndFind(driver,downloadBookLoc);
	if(downloadBookText==true) {
		test.log(LogStatus.INFO, "Scrolled to \"Download Book\" text in Book details page");
		basepagev2.takeScreenshot();
		if(Utilities.explicitWaitClickable(driver, readpagev2.downloadBookText, 10)) {
			readpagev2.downloadBookText.click();
			test.log(LogStatus.INFO, "Clicked on Download Book");
			boolean mainfor=false;
			for(int waittime=0;waittime<=15;waittime++) {
				if(Utilities.explicitWaitVisible(driver, readpagev2.downloadedBookText, 10)) {
					test.log(LogStatus.INFO, "Book downloaded successfully");
					Utilities.verticalSwipeReverse(driver);
					if(Utilities.explicitWaitClickable(driver, readpagev2.downloadedBookRead, 10)) {
						readpagev2.downloadedBookRead.click();
						for(int readerwait=0;readerwait<=10;readerwait++) {
							if(Utilities.explicitWaitClickable(driver, readpagev2.eBookReader, 10)) {
								homepagev2.smokeresults("Verify the navigation tapping on 'Read' button post downloading a book",rowno438, "PASS");
								homepagev2.reportPass("Verify the navigation tapping on 'Read' button post downloading a book is PASS");
								driver.navigate().back();
								driver.navigate().back();
								Utilities.verticalSwipeReverse(driver);
								Utilities.verticalSwipeReverse(driver);
								mainfor=true;
								break;
							}
							else {
								Thread.sleep(1000);
								if(readerwait==10) {
									homepagev2.smokeresults("Verify the navigation tapping on 'Read' button post downloading a book",rowno438, "FAIL");
									test.log(LogStatus.FAIL, "Verify the navigation tapping on 'Read' button post downloading a book is FAIL");
									BasePageV2.takeScreenshot();
									mainfor=true;
									driver.navigate().back();
									driver.navigate().back();
									Utilities.verticalSwipeReverse(driver);
									Utilities.verticalSwipeReverse(driver);
								}							
							}
						}
					}
				}
				if(mainfor==true) {
						break;
				}
			}
		}
		else 
			test.log(LogStatus.FAIL, "Download Book link is not clickable");
	}
	else 
		test.log(LogStatus.FAIL, "Unable to find Download book link");	
	
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
					if(Utilities.explicitWaitVisible(driver, settingspagev2.deviceCellularDownloads, 10)) {
						test.log(LogStatus.INFO, "Celluar Downloads option found");
						if(Utilities.explicitWaitVisible(driver, settingspagev2.deviceCellularDownloadsSwitch, 10)) {
							String state=settingspagev2.deviceCellularDownloadsSwitch.getAttribute("text");
							if(state.equals("ON")) {
								if(Utilities.explicitWaitClickable(driver, settingspagev2.deviceCellularDownloadsSwitch, 10)) {
									settingspagev2.deviceCellularDownloadsSwitch.click();
									Thread.sleep(3000);
									test.log(LogStatus.INFO, "Turned OFF Cellular Downloads setting");
									BasePageV2.takeScreenshot();
									
								}
								else {
									test.log(LogStatus.FAIL, "Unable to turn OFF Cellular Downloads setting");
								}
							}
							else {
								test.log(LogStatus.INFO, "Cellular Downloads setting is OFF");
							}
							driver.navigate().back();
							driver.navigate().back();
							driver.navigate().back();
						}
					}
				}
				else 
					test.log(LogStatus.FAIL, "Device screen is not displayed");			
			} 
			else
				test.log(LogStatus.FAIL, "Device option is not displayed");	
		} 
		else
			test.log(LogStatus.FAIL, "PIN container is not displayed");
	}	
	else 
		test.log(LogStatus.FAIL, "Settings icon is not displayed");	 

	//Try to download any content
	if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 5)) {
		homepagev2.read_tab.click();
		test.log(LogStatus.INFO, "Clicked on Read tab");
		Utilities.horizontalSwipe(driver);
		Utilities.horizontalSwipe(driver);
		test.log(LogStatus.INFO, "Swiped horizontally in the carousal");
		if(Utilities.explicitWaitVisible(driver, readpagev2.thirdBookBelowCarousal, 10)) {
			readpagev2.thirdBookBelowCarousal.click();
			test.log(LogStatus.INFO, "Clicked on a book in the carousal");
			downloadBookLoc="//android.widget.TextView[@text='Download Book' or @text='Download book']";
			downloadBookText=Utilities.verticalSwipeAndFind(driver,downloadBookLoc);
			if(downloadBookText==true) {
				test.log(LogStatus.INFO, "Scrolled to \"Download Book\" text in Book details page");
				basepagev2.takeScreenshot();
				if(Utilities.explicitWaitClickable(driver, readpagev2.downloadBookText, 10)) {
					readpagev2.downloadBookText.click();
					test.log(LogStatus.INFO, "Clicked on Download Book");
					if(Utilities.explicitWaitVisible(driver, readpagev2.downloadDisabledHeader, 10)) {
						homepagev2.smokeresults("Verify download button functionality when 'Cellular Downloads' button is in 'OFF' state",rowno871, "PASS");
						homepagev2.reportPass("Verify download button functionality when 'Cellular Downloads' button is in 'OFF' state is PASS");						
						if(readpagev2.downloadDisabledHeader.getAttribute("text").equalsIgnoreCase("DOWNLOAD DISABLED!")) 			
							test.log(LogStatus.INFO, "Popup UI: DOWNLOAD DISABLED! is displayed");	
						else {
							test.log(LogStatus.FAIL, "Popup UI: DOWNLOAD DISABLED! is NOT displayed");
							err872++;
						}
						if(readpagev2.downloadDisabledText.getAttribute("text").equals("Please go to Settings to enable downloads.")) 
							test.log(LogStatus.INFO, "Popup UI: 'Please go to Settings to enable downloads.' is displayed");
						else {
							test.log(LogStatus.FAIL, "Popup UI: 'Please go to Settings to enable downloads.' is NOT displayed");
							err872++;
						}
						if(readpagev2.downloadDisabledOK.getAttribute("text").equals("OK")) {
							test.log(LogStatus.INFO, "Popup UI: 'OK' button is displayed");
							if(err872==0) {
								homepagev2.smokeresults("Verify the UI of Download disabled Pop-up",rowno872, "PASS");
								homepagev2.reportPass("Verify the UI of Download disabled Pop-up is PASS");
							}
							if(Utilities.explicitWaitClickable(driver, readpagev2.downloadDisabledOK, 10)) {
								readpagev2.downloadDisabledOK.click();
								test.log(LogStatus.INFO, "Tapped on OK button");
								if(Utilities.explicitWaitVisible(driver, readpagev2.downloadBookText, 10)) {			
									homepagev2.smokeresults("Verify the functonality of OK button in Downloads disabled pop-up",rowno873, "PASS");
									homepagev2.reportPass("Verify the functonality of OK button in Downloads disabled pop-up is PASS");
									driver.navigate().back();
									Utilities.verticalSwipeReverse(driver);
									Utilities.verticalSwipeReverse(driver);
									//Revert Settings verification
									test.log(LogStatus.INFO, "Reverting Cellular Downloads settings");
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
													if(Utilities.explicitWaitVisible(driver, settingspagev2.deviceCellularDownloads, 10)) {
														test.log(LogStatus.INFO, "Celluar Downloads option found");
														if(Utilities.explicitWaitVisible(driver, settingspagev2.deviceCellularDownloadsSwitch, 10)) {
															String state=settingspagev2.deviceCellularDownloadsSwitch.getAttribute("text");
															if(state.equals("OFF")) {
																if(Utilities.explicitWaitClickable(driver, settingspagev2.deviceCellularDownloadsSwitch, 10)) {
																	settingspagev2.deviceCellularDownloadsSwitch.click();
																	Thread.sleep(3000);
																	test.log(LogStatus.INFO, "Turned ON Cellular Downloads setting");
																	BasePageV2.takeScreenshot();
																	
																}
																else {
																	test.log(LogStatus.FAIL, "Unable to turn ON Cellular Downloads setting");
																}
															}
															else {
																test.log(LogStatus.INFO, "Cellular Downloads setting is OFF");
															}
															driver.navigate().back();
															driver.navigate().back();
															driver.navigate().back();
														}
													}
												}
												else 
													test.log(LogStatus.FAIL, "Device screen is not displayed");			
											} 
											else
												test.log(LogStatus.FAIL, "Device option is not displayed");	
										} 
										else
											test.log(LogStatus.FAIL, "PIN container is not displayed");
									}	
									else 
										test.log(LogStatus.FAIL, "Settings icon is not displayed");

									
								}
								else {
									homepagev2.smokeresults("Verify the functonality of OK button in Downloads disabled pop-up",rowno873, "FAIL");
									test.log(LogStatus.FAIL, "Verify the functonality of OK button in Downloads disabled pop-up is FAIL");
									BasePageV2.takeScreenshot();
								}
							}
							else {
								test.log(LogStatus.FAIL, "OK button is not clickable");
								homepagev2.smokeresults("Verify the functonality of OK button in Downloads disabled pop-up",rowno873, "FAIL");
								test.log(LogStatus.FAIL, "Verify the functonality of OK button in Downloads disabled pop-up is FAIL");
								BasePageV2.takeScreenshot();
							}
						}
						else {
							test.log(LogStatus.FAIL, "Popup UI: 'OK' button is NOT displayed");
							err872++;
						}
						if(err872>0) {
							homepagev2.smokeresults("Verify the UI of Download disabled Pop-up",rowno872, "FAIL");
							test.log(LogStatus.FAIL, "Verify the UI of Download disabled Pop-up is FAIL");
							BasePageV2.takeScreenshot();
						}
					}
					else {
						homepagev2.smokeresults("Verify download button functionality when 'Cellular Downloads' button is in 'OFF' state",rowno871, "FAIL");
						test.log(LogStatus.FAIL, "Verify download button functionality when 'Cellular Downloads' button is in 'OFF' state is FAIL");
						BasePageV2.takeScreenshot();
					}
				}
				else 
					test.log(LogStatus.FAIL, "Book is not clickable");
			}
			else 
				test.log(LogStatus.FAIL, "Unable to find Download book link");
		}
		else
			test.log(LogStatus.FAIL, "Unable to locate third item in Carousal");	
	}
	
}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
