package com.viacom.ebook;

import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
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

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.touch.offset.PointOption;
//Author Tanisha
public class VerifyEbookDefaults extends BaseTestV2{
	
	String testName = "VerifyEbookDefaults";
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyEbookDefaults");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_1442 Verify the default state of 'Narration' option Done
		// VK_1443 Verify the default state of 'Dictionary' option Done
		// VK_1444 Verify the default state of 'Magnify' option Done
		// VK_1445 Verify the default state of 'Auto Page Turn' option Done
		// VK_1446 Verify the behavior when tapping on 'Preview' button	Done
		// VK_1449 Verify the availability of Back arrow Done
		// VK_1284 Verify ebook playback state post killing & relaunching the application(when narration is ON) Done
		// VK_1540 Verify the Auto Zoom feature state with respect to Narration option 
		// VK_1480 Verify control player screen is dismissed post tapping on any of the empty screen Done
		// VK_613 Verify the Up arrow functionality on the reader player Done
		// VK_614 Verify the Down arrow functionality on the reader player Done
		// VK_617 Verify the scroll functionality for Ebook cards present under Related tray 
		// VK_618 Verify the card navigation when tapped on the cards present under Related tray Done
		
				
		Xls_Reader xls1538 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1538=xls1538.getRowCount("Smoke_Results")+1;
		xls1538.setCellData("Smoke_Results", "Testcase Name",rowno1538,"Verify the default state of 'Auto Zoom' option");
								
		//Launch Voot kids app
		//Login module to be added
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
		 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
		 
		
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 downloadsv2.deleteAllDownloads();
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 String bookNameBefore="";
		 boolean downloadCompleted=false;
		 boolean bookDownload=false;
		 boolean readerDisplayedPreview=false;
		 boolean readerDisplayedRead=false;
		 int err1541=0;
		 int err613=0;
		 int err614=0;
		 int err617=0;
		 int err618=0;
		 
		 homepagev2.tabClick("Read");
		 Thread.sleep(2000);
		 Utilities.horizontalSwipe(driver);
		 if(Utilities.explicitWaitVisible(driver, readpagev2.firstBookNameBelowCarousal, 10)) {
			bookNameBefore=readpagev2.firstBookNameBelowCarousal.getAttribute("text");
			bookNameBefore=homepagev2.convertCamelCase(bookNameBefore);
			if(Utilities.explicitWaitVisible(driver, readpagev2.firstBookNameBelowCarousal, 10)) {
				readpagev2.firstBookBelowCarousal.click();
				test.log(LogStatus.INFO, "Clicked on the Book "+bookNameBefore+" from Read Carousal");
				if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10)) {
					try {
						 readpagev2.previewButton.click();
						 test.log(LogStatus.INFO, "Clicked on PREVIEW button");
						 for(int wait=0;wait<=30;wait++) {
							 try {
								 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
								 //Add 1447
								 if(wait==30) {
									 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
									 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Preview' button is Fail");
									 if(!Utilities.setResultsKids("VK_1446", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 BasePageV2.takeScreenshot();
									 driver.navigate().back();
									 test.log(LogStatus.INFO, "Navigated back to book details page");
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.INFO, "Loader is not displayed");
								 Set<String> CHS = driver.getContextHandles();
								 for(String ch:CHS){
									if(ch.contains("WEBVIEW")){
										test.log(LogStatus.INFO, "eBook Reader is displayed");
										test.log(LogStatus.PASS, "Verify the behavior when tapping on 'Preview' button is Pass");
										 if(!Utilities.setResultsKids("VK_1446", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										readerDisplayedPreview=true;
										break;
									}
								 }  
							 }	
							 if(readerDisplayedPreview==true)
								 break;
							 else
								 Thread.sleep(1000);
						 }						 
					 }
					 catch(Exception e) {
						test.log(LogStatus.FAIL, "Unable to click on PREVIEW button"); 
					 }
				}
										
				//get reader controls
				Thread.sleep(2000);
				Utilities.tap(driver);
				Utilities.tap(driver);
				test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
				//Tap on Up Arrow
				if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderUpArrow, 10)) {
					test.log(LogStatus.INFO, "Reader controls have launched");	
					try {
						readpagev2.bookReaderUpArrow.click();										
						test.log(LogStatus.INFO, "Clicked on Up Arrow button");
						Utilities.verticalSwipeReverse(driver);
						Utilities.verticalSwipeReverse(driver);
						//Up arrow should turn to down arrow
						if(Utilities.explicitWaitVisible(driver, readpagev2.bookReaderDownArrow, 10)) {
							test.log(LogStatus.INFO, "Down Arrow button is displayed");
						}
						else {
							test.log(LogStatus.FAIL, "Down Arrow button is not displayed");
							err613++;
						}
						//Related, More from Author, Daily picks should be displayed
						if(Utilities.explicitWaitVisible(driver, readpagev2.bookReaderTabRelated, 10)) {
							test.log(LogStatus.INFO, "Related tab is displayed");
							int totalRelatedCardsAPI=10;
							if(totalRelatedCardsAPI>8) {
								if(Utilities.explicitWaitVisible(driver, readpagev2.readerRelatedTab7thItem, 5)) {
									test.log(LogStatus.FAIL, "Found item 7 in Related tab even before scroll");
									err617++;
								}
								else {
									Utilities.verticalSwipe(driver);
									Utilities.verticalSwipe(driver);
									if(Utilities.explicitWaitVisible(driver, readpagev2.readerRelatedTab7thItem, 5)) {
										test.log(LogStatus.INFO, "Found item 7 in Related tab after swiping vertically down");
									}
									else {
										test.log(LogStatus.FAIL, "Failed to find item 7 in Related tab after swiping vertically down");
										err617++;
									}
								}
								//Verification
								if(err617==0) {
									test.log(LogStatus.PASS, "Verify the scroll functionality for Ebook cards present under Related tray is Pass");
									if(!Utilities.setResultsKids("VK_617", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									BasePageV2.takeScreenshot();
								}
								else {
									test.log(LogStatus.FAIL, "Verify the scroll functionality for Ebook cards present under Related tray is Fail");
									if(!Utilities.setResultsKids("VK_617", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									BasePageV2.takeScreenshot();
								}
								Utilities.verticalSwipeReverse(driver);
								Utilities.verticalSwipeReverse(driver);
							}
						}
						else {
							test.log(LogStatus.FAIL, "Related tab is not displayed");
							err613++;
						}
						if(Utilities.explicitWaitVisible(driver, readpagev2.bookReaderTabMoreFromAuthor, 10)) {
							test.log(LogStatus.INFO, "More From Author tab is displayed");
						}
						else {
							test.log(LogStatus.FAIL, "More From Author tab is not displayed");
							err613++;
						}
						if(Utilities.explicitWaitVisible(driver, readpagev2.bookReaderTabDailyPicks, 10)) {
							test.log(LogStatus.INFO, "Daily Picks tab is displayed");
						}
						else {
							test.log(LogStatus.FAIL, "Daily Picks tab is not displayed");
							err613++;
						}
						//Verification
						if(err613==0) {
							test.log(LogStatus.PASS, "Verify the Up arrow functionality on the reader player is PASS"); 
							if(!Utilities.setResultsKids("VK_613", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else {
							test.log(LogStatus.FAIL, "Verify the Up arrow functionality on the reader player is FAIL");
							if(!Utilities.setResultsKids("VK_613", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
						}
						//Click on Down arrow
						if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderDownArrow, 10)) {
							try {
								readpagev2.bookReaderDownArrow.click();
								test.log(LogStatus.INFO, "Clicked on Down Arrow button");
								if(Utilities.explicitWaitVisible(driver, readpagev2.bookReaderUpArrow, 10)) {
									test.log(LogStatus.INFO, "Up Arrow button is displayed after clicking on Down Arrow button");
								}
								else {
									test.log(LogStatus.FAIL, "Up Arrow button is not displayed after clicking on Down Arrow button");
									err614++;
								}
								if(readpagev2.bookReaderTabs.size()==0) {
									test.log(LogStatus.INFO, "No tabs are displayed");
								}
								else {
									test.log(LogStatus.FAIL, "Tabs are displayed");
									err614++;
								}
							}
							catch(Exception e2) {
								test.log(LogStatus.INFO, "Failed to click on Down Arrow button");
								err614++;
							}
						}
						else {
							test.log(LogStatus.FAIL, "Down Arrow button is not clickable");
							err614++;
						}
						//Verification
						if(err614==0) {
							test.log(LogStatus.PASS, "Verify the Down arrow functionality on the reader player is PASS"); 
							if(!Utilities.setResultsKids("VK_614", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else {
							test.log(LogStatus.FAIL, "Verify the Down arrow functionality on the reader player is FAIL");
							if(!Utilities.setResultsKids("VK_614", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
						}
												
					}
					catch(Exception e1) {
						test.log(LogStatus.INFO, "Failed to click on Up Arrow button");
					}
				}
				Thread.sleep(2000);
				try {
					int width  =driver.manage().window().getSize().getWidth();  
					int height = driver.manage().window().getSize().getHeight();               
					int x = width/2;												
					int y =  height/height +10;
					try{
						TouchAction act=new TouchAction(driver);
						(new TouchAction(driver))
						.tap(PointOption.point(x, y))           
						.perform();  
						test.log(LogStatus.INFO, "Tapped on an empty area");
						if(Utilities.explicitWaitVisible(driver, readpagev2.narrationOn, 10)) {
							test.log(LogStatus.FAIL, "Reader controls are displayed even after tapping on empty area");
							test.log(LogStatus.FAIL, "Verify control player screen is dismissed post tapping on any of the empty screen is Fail");
							if(!Utilities.setResultsKids("VK_1480", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
						}
						else {
							test.log(LogStatus.INFO, "Reader controls are not displayed after tapping on empty area"); 
							test.log(LogStatus.PASS, "Verify control player screen is dismissed post tapping on any of the empty screen is Pass");
							if(!Utilities.setResultsKids("VK_1480", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						
					}	
					catch(Exception e1)
					{}
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}	
				//Again launch reader controls
				String firstBookRelatedTab="";
				Thread.sleep(2000);
				Utilities.tap(driver);
				Utilities.tap(driver);
				test.log(LogStatus.INFO, "Double tapped screen to launch reader controls again");
				//Tap on Up Arrow
				if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderUpArrow, 10)) {
					test.log(LogStatus.INFO, "Reader controls have launched");	
					try {
						readpagev2.bookReaderUpArrow.click();										
						test.log(LogStatus.INFO, "Clicked on Up Arrow button");
						Utilities.verticalSwipeReverse(driver);
						Utilities.verticalSwipeReverse(driver);
						Thread.sleep(2000);
						if(Utilities.explicitWaitClickable(driver, readpagev2.readerRelatedTab1stItemName, 10)) {
							try {
								firstBookRelatedTab=readpagev2.readerRelatedTab1stItemName.getAttribute("text");
								test.log(LogStatus.INFO, "First book related tab: "+firstBookRelatedTab);
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to fetch name of the first book under Related tab");
							}
							try {
								readpagev2.readerRelatedTab1stItem.click();
								test.log(LogStatus.INFO, "Clicked on first book under Related tab");
								
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to click on first book under Related tab");
							}
							try {
								if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 10)) {
									try {
										String RelatedTabFirstBookDetails=readpagev2.bookNameInDetailsPage.getAttribute("text");
										test.log(LogStatus.INFO, "From details "+RelatedTabFirstBookDetails);
										if(RelatedTabFirstBookDetails.equalsIgnoreCase(firstBookRelatedTab)) {
											test.log(LogStatus.INFO, "Launched book details page for book "+firstBookRelatedTab);
										}
										else {
											test.log(LogStatus.INFO, "Failed to launch book details page for book "+firstBookRelatedTab);
											err618++;
										}
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to verify Book details");
										err618++;
									}
								}
								else if(Utilities.explicitWaitVisible(driver, readpagev2.readButton, 10)) {
									try {
										if(readpagev2.bookNameInDetailsPage.getAttribute("text").equalsIgnoreCase(firstBookRelatedTab)) {
											test.log(LogStatus.INFO, "Launched book details page for book "+firstBookRelatedTab);
										}
										else {
											test.log(LogStatus.INFO, "Failed to launch book details page for book "+firstBookRelatedTab);
											err618++;
										}
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to verify Book details");
										err618++;
									}
								}
								else {
									test.log(LogStatus.FAIL, "Failed to launch Book details page");
									err618++;
								}
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to verify Book details");
								err618++;
							}
						}
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to click on Up Arrow");
					}
				}
				else {
					test.log(LogStatus.INFO, "Up Arrow is not clickable");
				}
				if(err618==0) {
					test.log(LogStatus.PASS, "Verify the card navigation when tapped on the cards present under Related tray is Pass");
					if(!Utilities.setResultsKids("VK_618", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.FAIL, "Verify the card navigation when tapped on the cards present under Related tray is Fail");
					if(!Utilities.setResultsKids("VK_618", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				driver.navigate().back();
				String downloadBookLoc="//android.widget.TextView[@text='Download Book' or @text='Download book']";
				for(int count=0;count<=3;count++) {
					try {
						driver.findElement(By.xpath(downloadBookLoc)).click();
						test.log(LogStatus.INFO, "Clicked on Download Book");
						bookDownload=true;
						break;
					}
					catch(Exception e) {
						Utilities.verticalSwipe(driver);
						if(count==3) {
							test.log(LogStatus.FAIL, "Unable to find Download book link");
						}
					}
				}
				if(bookDownload==true) {
					for(int wait=0;wait<=15;wait++) {
						if(readerDisplayedRead==true)
							break;
						else if(Utilities.explicitWaitVisible(driver, readpagev2.downloadedBookText, 5)) {
							test.log(LogStatus.INFO, "Download completed ..");
							downloadCompleted=true;
							if(Utilities.explicitWaitClickable(driver, readpagev2.readButton, 10)) {
								readpagev2.readButton.click();
								readerDisplayedRead=true;
								bookspagev2.verifyReaderProgressBar();
								Set<String> CHS = driver.getContextHandles();
								for(String ch:CHS){
									
									if(ch.contains("WEBVIEW")){
										test.log(LogStatus.INFO, "eBook Reader is displayed");	
										readerDisplayedRead=true;	
										//Verify that back button is not bresent
										if(Utilities.explicitWaitVisible(driver, homepagev2.backButton, 10)) {
											test.log(LogStatus.FAIL, "Software Back button is visible in eBook Reader");
											test.log(LogStatus.FAIL, "Verify the availability of Back arrow is Fail");
											if(!Utilities.setResultsKids("VK_1449", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											BasePageV2.takeScreenshot();
										}
										else {
											test.log(LogStatus.INFO, "Software Back button is not visible in eBook Reader"); 
											test.log(LogStatus.PASS, "Verify the availability of Back arrow is Pass");
											if(!Utilities.setResultsKids("VK_1449", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										}
									}
								}  
							}
							else 
								test.log(LogStatus.FAIL, "Read button is not clickable");
						}
						else 
							Thread.sleep(1000);
					}	
				}
			}
		}
		else {
			test.log(LogStatus.INFO, "Unable to fetch name of the second item from Carousal");
		}
		if(readerDisplayedRead==true) {
			Utilities.tap(driver);
			Utilities.tap(driver);
			test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");	
			String narration="";
			Thread.sleep(2000);
			try {
				narration=readpagev2.narrationOn.getAttribute("text");	
			}
			catch(Exception e) {	
				test.log(LogStatus.FAIL, "Failed to locate Narration button");	
			}

			if(narration.equalsIgnoreCase("NARRATION OFF")) {
				test.log(LogStatus.INFO, "NARRATION OFF is displayed");
				test.log(LogStatus.PASS, "Verify the default state of 'Narration' option is Pass");
				if(!Utilities.setResultsKids("VK_1442", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			else {
				test.log(LogStatus.INFO, "NARRATION OFF is not displayed");
				test.log(LogStatus.FAIL, "Verify the default state of 'Narration' option is Fail");
				if(!Utilities.setResultsKids("VK_1442", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				BasePageV2.takeScreenshot();	
			}
			
			//Tap on Settings
			if(Utilities.explicitWaitVisible(driver, readpagev2.readerOptions, 10)) {
				test.log(LogStatus.INFO, "eBook Reader Options icon is visible");
				readpagev2.readerOptions.click();
				test.log(LogStatus.INFO, "Clicked on eBook Reader Options icon");
				if(Utilities.explicitWaitVisible(driver, readpagev2.readerOptionsPageTitle, 10)) {
					test.log(LogStatus.INFO, "OPTIONS page is launched");
					//Verify default Magnify
					if(Utilities.explicitWaitVisible(driver, readpagev2.defaultMagnify, 10)) {
						if(readpagev2.defaultMagnify.getAttribute("text").equalsIgnoreCase("Magnify.")) {
							test.log(LogStatus.INFO, "Located 'Magnify.' option");
							if(readpagev2.defaultMagnifyON.getAttribute("text").equalsIgnoreCase("ON")) {
								test.log(LogStatus.INFO, "Magnify. option is ON by default");
								test.log(LogStatus.PASS, "Verify the default state of 'Magnify' option is Pass");
								if(!Utilities.setResultsKids("VK_1445", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							else {
								test.log(LogStatus.FAIL, "Magnify. option is not ON by default");
								test.log(LogStatus.FAIL, "Verify the default state of 'Magnify' option is Fail");
								if(!Utilities.setResultsKids("VK_1444", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								homepagev2.takeScreenshot();
							}
						}
						else {
							test.log(LogStatus.FAIL, "Magnify. option is not displayed");
							test.log(LogStatus.FAIL, "Verify the default state of 'Magnify' option is Fail");
							if(!Utilities.setResultsKids("VK_1444", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							homepagev2.takeScreenshot();
						}
						
					}
					else {
						test.log(LogStatus.FAIL, "Magnify. option is not displayed");
						test.log(LogStatus.FAIL, "Verify the default state of 'Magnify' option is Fail");
						if(!Utilities.setResultsKids("VK_1444", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						homepagev2.takeScreenshot();
					}
					//Verify default Dictionary
					if(Utilities.explicitWaitVisible(driver, readpagev2.defaultDictionary, 10)) {
						if(readpagev2.defaultDictionary.getAttribute("text").equalsIgnoreCase("Dictionary.")) {
							test.log(LogStatus.INFO, "Located 'Dictionary.' option");
							if(readpagev2.defaultDictionaryON.getAttribute("text").equalsIgnoreCase("ON")) {
								test.log(LogStatus.INFO, "Dictionary. option is ON by default");
								test.log(LogStatus.PASS, "Verify the default state of 'Dictionary' option is Pass");
								if(!Utilities.setResultsKids("VK_1443", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							else {
								test.log(LogStatus.FAIL, "Dictionary. option is not ON by default");
								test.log(LogStatus.FAIL, "Verify the default state of 'Dictionary' option is Fail");
								if(!Utilities.setResultsKids("VK_1443", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								homepagev2.takeScreenshot();
							}
						}
						else {
							test.log(LogStatus.FAIL, "Dictionary. option is not displayed");
							test.log(LogStatus.FAIL, "Verify the default state of 'Dictionary' option is Fail");
							if(!Utilities.setResultsKids("VK_1443", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							homepagev2.takeScreenshot();
						}
						
					}
					else {
						test.log(LogStatus.FAIL, "Dictionary. option is not displayed");
						test.log(LogStatus.FAIL, "Verify the default state of 'Dictionary' option is Fail");
						if(!Utilities.setResultsKids("VK_1443", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						homepagev2.takeScreenshot();
					}
					//Verify default Auto Page Turn
					if(Utilities.explicitWaitVisible(driver, readpagev2.defaultAutoPageTurn, 10)) {
						if(readpagev2.defaultAutoPageTurn.getAttribute("text").equalsIgnoreCase("Auto Page Turn.")) {
							test.log(LogStatus.INFO, "Located 'Auto Page Turn.' option");
							if(readpagev2.defaultAutoPageTurnOFF.getAttribute("text").equalsIgnoreCase("OFF")) {
								test.log(LogStatus.INFO, "Auto Page Turn. option is OFF by default");
								test.log(LogStatus.PASS, "Verify the default state of 'Auto Page Turn' option is Pass");
								if(!Utilities.setResultsKids("VK_1445", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
							else {
								test.log(LogStatus.FAIL, "Auto Page Turn. option is not OFF by default");
								test.log(LogStatus.FAIL, "Verify the default state of 'Auto Page Turn' option is Fail");
								if(!Utilities.setResultsKids("VK_1445", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								homepagev2.takeScreenshot();
							}
						}
						else {
							test.log(LogStatus.FAIL, "Auto Page Turn. option is not displayed");
							test.log(LogStatus.FAIL, "Verify the default state of 'Auto Page Turn' option is Fail");
							if(!Utilities.setResultsKids("VK_1445", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							homepagev2.takeScreenshot();
						}
						
					}
					else {
						test.log(LogStatus.FAIL, "Auto Page Turn. option is not displayed");
						test.log(LogStatus.FAIL, "Verify the default state of 'Auto Page Turn' option is Fail");
						if(!Utilities.setResultsKids("VK_1445", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						homepagev2.takeScreenshot();
					}
					//Verify default Auto Zoom
					if(Utilities.explicitWaitVisible(driver, readpagev2.defaultAutoZoom, 10)) {
						if(readpagev2.defaultAutoZoom.getAttribute("text").equalsIgnoreCase("Auto Zoom.")) {
							test.log(LogStatus.INFO, "Located 'Auto Zoom.' option");
							if(readpagev2.defaultAutoZoomON.getAttribute("text").equalsIgnoreCase("ON")) {
								test.log(LogStatus.INFO, "Auto Zoom. option is ON by default");
								homepagev2.reportPass("Default state of 'Auto Zoom' option is Pass");
							}
							else {
								test.log(LogStatus.FAIL, "Auto Zoom. option is not ON by default");
								err1541++;
								homepagev2.smokeresults("Verify the default state of 'Auto Zoom' option",rowno1538, "FAIL");
								test.log(LogStatus.FAIL, "Verify the default state of 'Auto Zoom' option is Fail");
								BasePageV2.takeScreenshot();
							}
						}
						else {
							test.log(LogStatus.FAIL, "Auto Zoom. option is not displayed");
							err1541++;
							homepagev2.smokeresults("Verify the default state of 'Auto Zoom' option",rowno1538, "FAIL");
							test.log(LogStatus.FAIL, "Verify the default state of 'Auto Zoom' option is Fail");
							BasePageV2.takeScreenshot();
						}
						
					}
					else {
						test.log(LogStatus.FAIL, "Auto Zoom. option is not displayed");
						err1541++;
						homepagev2.smokeresults("Verify the default state of 'Auto Zoom' option",rowno1538, "FAIL");
						test.log(LogStatus.FAIL, "Verify the default state of 'Auto Zoom' option is Fail");
						BasePageV2.takeScreenshot();
					}
				}
				else {
					test.log(LogStatus.FAIL, "OPTIONS page is not launched");
				}
			}
			else {
				test.log(LogStatus.FAIL, "Reader options is not clickable");
			}	
			if(Utilities.explicitWaitClickable(driver, readpagev2.readerOptionsClose, 10)) {
				readpagev2.readerOptionsClose.click();
				test.log(LogStatus.INFO, "Closed Reader Options");
			}
			else {
				driver.navigate().back();
				test.log(LogStatus.INFO, "Navigated back to eBook Reader");	
			}
			//Verification of 1541
			//Tap on NARRATION OFF button
			if(Utilities.explicitWaitClickable(driver, readpagev2.narrationOn, 10)) {
				readpagev2.narrationOn.click();
				try {
					narration=readpagev2.narrationOff.getAttribute("text");	
				}
				catch(Exception e) {	
					test.log(LogStatus.FAIL, "Failed to locate Narration button");	
				}
				test.log(LogStatus.INFO, "Narration button is: "+narration);
				if(narration.equalsIgnoreCase("NARRATION ON")) {
					test.log(LogStatus.INFO, "NARRATION ON button is displayed->Narration is in OFF state");
					//Verify the auto zoom option
					if(Utilities.explicitWaitVisible(driver, readpagev2.readerOptions, 10)) {
						readpagev2.readerOptions.click();
						test.log(LogStatus.INFO, "Clicked on eBook Reader Options icon");
						if(Utilities.explicitWaitVisible(driver, readpagev2.readerOptionsPageTitle, 10)) {
							test.log(LogStatus.INFO, "OPTIONS page is launched");
							if(Utilities.explicitWaitVisible(driver, readpagev2.defaultAutoZoom, 10)) {
								if(readpagev2.defaultAutoZoom.getAttribute("text").equalsIgnoreCase("Auto Zoom.")) {
									test.log(LogStatus.INFO, "Located 'Auto Zoom.' option");
									if(readpagev2.defaultAutoZoomON.getAttribute("text").equalsIgnoreCase("OFF")) {
										test.log(LogStatus.INFO, "Auto Zoom. option is OFF when Narration is OFF");
									}
									else {
										test.log(LogStatus.FAIL, "Auto Zoom. option is ON when Narration is OFF");
										err1541++;
									}
								}
								else {
									test.log(LogStatus.FAIL, "Auto Zoom. option is not displayed");
									err1541++;
								}
								
							}
							else {
								test.log(LogStatus.FAIL, "Auto Zoom. option is not displayed");
								err1541++;
							}
							
						}
						else {
							test.log(LogStatus.FAIL, "OPTIONS page is not launched");
						}
					}
					else {
						test.log(LogStatus.FAIL, "eBook Reader Options is not displayed");
					}
				}
				else {
					test.log(LogStatus.INFO, "NARRATION ON button is not displayed");
				}
				
			}
			//Verifiaction of 1541
			if(err1541==0) {
				 test.log(LogStatus.PASS, "Verify the Auto Zoom feature state with respect to Narration option is PASS"); 
				 if(!Utilities.setResultsKids("VK_1540", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Verify the Auto Zoom feature state with respect to Narration option is FAIL"); 
				 if(!Utilities.setResultsKids("VK_1540", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 BasePageV2.takeScreenshot();
			 }
			//Kill app
			try{
				driver.closeApp();
				test.log(LogStatus.INFO, "Killed app...");
			} 
			catch (Exception e)
			{}
			try{
				test.log(LogStatus.INFO,"Launching app again");
				launchApp();
				Thread.sleep(3000);
				if(Utilities.explicitWaitVisible(driver, homepagev2.profileIcon, 10)) {
					test.log(LogStatus.INFO, "Home page is displayed");
					test.log(LogStatus.PASS, "Verify ebook playback state post killing & relaunching the application(when narration is ON) is Pass");
					if(!Utilities.setResultsKids("VK_1284", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.INFO, "Home page is not displayed");
					test.log(LogStatus.FAIL, "Verify ebook playback state post killing & relaunching the application(when narration is ON) is Fail");
					if(!Utilities.setResultsKids("VK_1284", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
				}
						
			}
			catch(Exception e){
					BasePageV2.reportFail("Failed to launch app");
			}
		}

}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}