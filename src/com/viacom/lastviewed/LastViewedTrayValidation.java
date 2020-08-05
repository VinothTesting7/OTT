package com.viacom.lastviewed;
import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.FavouritesPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;

import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;


// Author - Suresh 

public class LastViewedTrayValidation extends BaseTestV2 {
	
	
	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "LastViewedTrayValidation";
	
	String tc1 = "VK_165";
	String tc2 = "VK_169";
	String tc3 = "VK_170";
	String tc4 = "VK_418";
	String tc5 = "VK_775";
	String tc6 = "VK_166";
	
	String episodeTitle = "";
	
	int VK_165 = 0 , VK_169 = 0,VK_170 = 0,VK_418 = 0 , VK_775 = 0 ,VK_166 = 0 ;
	
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("LastViewedTrayValidation");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		
		
		if (driver.isDeviceLocked())
			driver.unlockDevice();
		else
			System.out.println("Device already unlocked");
					
		   HomePageV2.login(data.get("Email"),data.get("Password"));
		
				 HomePageV2 homepagev2=new HomePageV2(driver,test);
				 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
				 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
				 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
				 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
				 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
				 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
				 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
				 BasePageV2  basepagev2=new BasePageV2(driver,test);
				 FavouritesPageV2 favouritesPageV2 = new FavouritesPageV2(driver, test);
				 LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
				 
				 homepagev2.login(data.get("Email"),data.get("Password"));
				 
				// Verify episode card metadata for partially watched contents: -  VK_165
				 
	
				     Utilities.verticalSwipe(driver);
				     Utilities.verticalSwipe(driver);
				 
				 	if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClear, 10)) {
						try {
							homepagev2.recentViewedClear.click();
							try {
								homepagev2.recentViewedClearYes.click();
								test.log(LogStatus.INFO, "Clearing last viewed tray");
								
								Thread.sleep(6000);
					
							}
							catch(Exception e) {
								test.log(LogStatus.INFO, "Failed to Clear");
							}
						}
						catch(Exception e) {
							test.log(LogStatus.INFO, "Failed to click on Clear");
						}
						
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
					}
					else {
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
					}
				 

				 
					//Click on Watch tab 						 
					 homepagev2.tabClick("Watch");	
					 Thread.sleep(10000);
			
              //click on card in watch carousel 1
  	  
				if (Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
				{
					

					
				try 
				{
					episodeTitle=watchpagev2.EpisodecardTitle.getAttribute("text");	
					episodeTitle=homepagev2.convertCamelCase(episodeTitle);
				System.out.println(episodeTitle);
				watchpagev2.cardinwatchcarousel.click();
				test.log(LogStatus.INFO, "clicked on first episode"+episodeTitle);
				homepagev2.verifyAndProgressBar();
				Thread.sleep(60000);
				driver.navigate().back();
				
				Thread.sleep(60000);
				
				}catch(Exception e)
				{
					
				}

				 
		        }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
			
					
				// navigate to Last viewed tray	
					
				homepagev2.tabClick("My Stuff");
				 Thread.sleep(10000);
				 
				 Utilities.verticalSwipe(driver, homepagev2.recentViewedClear);
				 
				 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
					 
					 homepagev2.lastViewedTitle.click();
					 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
					 
					 try {
						
						   driver.findElement(By.xpath("//android.widget.TextView[@text=\""+episodeTitle+"\"]"));
							test.log(LogStatus.INFO, "watched Episode Found :  " + episodeTitle );
						 
					} catch (Exception e) {
						VK_165++;
						test.log(LogStatus.FAIL, "Episode card not Available in Last viewed Tray");
					}
					 
					 if(Utilities.explicitWaitVisible(driver, homepagev2.episodeDuration, 20)) {
						 test.log(LogStatus.INFO, "Found Episode Duration");
						 
						 if(Utilities.explicitWaitVisible(driver, homepagev2.EpisodeprogressBar, 20)) {
							 test.log(LogStatus.INFO, "Found Episode Progress bar");
							 
							 if(Utilities.explicitWaitVisible(driver, homepagev2.EpisodeDiscription, 20)) {
	
								 test.log(LogStatus.INFO, "Found Episode Discription");
			
							 }else {
								 VK_165++;
									test.log(LogStatus.FAIL, "Video Duration not found on Episode card");
							 }
							 
							 
						 }else {
							 VK_165++;
								test.log(LogStatus.FAIL, "progressbar not found on Episode card");
						 }
						 
		
					 }else {
						 VK_165++;
							test.log(LogStatus.FAIL, "Video Duration not found on Episode card");
					 }
					 

					 
				 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
					
					
					
				 if(VK_165 == 0) {
					 
					 if(!Utilities.setResultsKids(tc1, pass))
					      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.PASS, "Verify episode card metadata for partially watched contents: - VK_165");
					 
					 driver.navigate().back();
					 Utilities.verticalSwipeDown(driver);
					 Utilities.verticalSwipeDown(driver);
					 Utilities.verticalSwipeDown(driver);
					 
				 }else {
					 
					 if(!Utilities.setResultsKids(tc1, fail))
					      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.FAIL, "Verify episode card metadata for partially watched contents: - VK_165");
					 BasePageV2.takeScreenshot();
					 
					 driver.navigate().back();
					 Utilities.verticalSwipeDown(driver);
					 Utilities.verticalSwipeDown(driver);
					 Utilities.verticalSwipeDown(driver);
					 
				 }
				 
				 // clear data in last viewed tray
				 
			
				// Verify Book card metadata for partially read book: - VK_169
				 
				 homepagev2.tabClick("Read");
				 Thread.sleep(2000);
				 
				 String bookName = "";
				 if(Utilities.explicitWaitVisible(driver, homepagev2.bookFirstcarosal, 10))
				 {
					 try {
						 bookName= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
						 bookName=homepagev2.convertCamelCase(bookName);
							
						homepagev2.bookFirstcarosal.click();
						
						test.log(LogStatus.INFO, "Clicked on the Book " + bookName + " from Read Carousal");
						
						if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
							
							bookspagev2.bookDetailPageBookPreviewButton.click();
							Thread.sleep(60000);
							
							driver.navigate().back();
							Thread.sleep(60000);
							driver.navigate().back();
							Thread.sleep(60000);
							
						}else {
							test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
							
						}
						
						
								 
					} catch (Exception e) {
						// TODO: handle exception
					}
					
						
						
				 }else test.log(LogStatus.FAIL, "Not able to found Book carosal"); 
				 
				 
				 //click on my-stuff
				 
				// navigate to Last viewed tray	
					
					homepagev2.tabClick("My Stuff");
					 Thread.sleep(10000);
					 
					 Utilities.verticalSwipe(driver, homepagev2.recentViewedClear);
					 
					 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
						 
						 homepagev2.lastViewedTitle.click();
						 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
						 
		
						 String readBook = "";
						 try {
							 
							 readBook = homepagev2.BookTitle.getText().toString().trim();
							 test.log(LogStatus.INFO, "Book from last viewed tray is : " + readBook);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						 
						 if(bookName.equals(readBook)) {
							 test.log(LogStatus.INFO, "Read book found which contain in Last viewed tray :  " + bookName );
						 }else {
							 VK_169++;
							 test.log(LogStatus.FAIL, "Read Book not Available in Last viewed Tray");
						 }
						 
						 
						 
						 
						 
						 if(Utilities.explicitWaitVisible(driver, homepagev2.BookDiscription, 20)) {
							 test.log(LogStatus.INFO, "Found Book Duration");
							 
							 if(Utilities.explicitWaitVisible(driver, homepagev2.BookProgressbar, 20)) {
								 test.log(LogStatus.INFO, "Found Bok Progress bar");
								 
							 }else {
								 VK_169++;
									test.log(LogStatus.FAIL, "progressbar not found on Book");
							 }
							 
			
						 }else {
							 VK_169++;
								test.log(LogStatus.FAIL, "Video Duration not found on Book");
						 }
						 

						 
					 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
						
						
					 String lastviewbookTile = "";
					 try {
						 
						  lastviewbookTile = homepagev2.BookTitle.getText().toString().trim();
						 test.log(LogStatus.INFO, "Book from last viewed tray is : " + lastviewbookTile);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					 
					 if(bookName.equals(lastviewbookTile)) {
						 test.log(LogStatus.INFO, "Found Book in under last Viewed Tray");
					 }else {
						 VK_169++;
					 }
					 
					 
						
					 if(VK_169 == 0) {
						 
						 if(!Utilities.setResultsKids(tc2, pass))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.PASS, "Verify Book card metadata for partially read book: - VK_169");
						 
						 driver.navigate().back();
						 
						 
					 }else {
						 
						 if(!Utilities.setResultsKids(tc2, fail))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.FAIL, "Verify Book card metadata for partially read book: - VK_169");
						 BasePageV2.takeScreenshot();
						 
						 driver.navigate().back();
						 
					 }
				 
				 
				 
			// Verify Book card metadata for completely read book: - VK_170
					 
					 
					 // clear data in last viewed tray
					 if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClear, 10)) {
							try {
								homepagev2.recentViewedClear.click();
								try {
									homepagev2.recentViewedClearYes.click();
									test.log(LogStatus.INFO, "Clearing last viewed tray");
									
									Thread.sleep(6000);
									
									Utilities.verticalSwipeDown(driver);
									Utilities.verticalSwipeDown(driver);
									Utilities.verticalSwipeDown(driver);
						
								}
								catch(Exception e) {
									test.log(LogStatus.INFO, "Failed to Clear");
								}
							}
							catch(Exception e) {
								test.log(LogStatus.INFO, "Failed to click on Clear");
							}
							
							
						}
						else {
							test.log(LogStatus.INFO, "Clear link is not clickable");
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
						}
					 
					 
					 
					 
					 // Verify Book card metadata for completely read book: - VK_170
					 
					 
					 homepagev2.tabClick("Read");
					 Thread.sleep(2000);
					 
					 String bookName1 = "";
					 if(Utilities.explicitWaitVisible(driver, homepagev2.bookFirstcarosal, 10))
					 {
						 try {
							 bookName1= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
							 bookName1=homepagev2.convertCamelCase(bookName1);
								
							homepagev2.bookFirstcarosal.click();
							
							test.log(LogStatus.INFO, "Clicked on the Book " + bookName1 + " from Read Carousal");
							
							if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
								
								bookspagev2.bookDetailPageBookPreviewButton.click();
								Thread.sleep(20000);
								
								for(int i = 0 ; i < 8 ; i++) {
									
									Utilities.horizontalSwipe(driver);
									Thread.sleep(5000);
									
								}
								
								
								
								driver.navigate().back();
								Thread.sleep(40000);
								driver.navigate().back();
								
								driver.navigate().back();
								Thread.sleep(40000);
								
							}else {
								test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
								
							}
							
							
									 
						} catch (Exception e) {
							// TODO: handle exception
						}
						
							
							
					 }else test.log(LogStatus.FAIL, "Not able to found Book carosal"); 
					 
					 
					   driver.runAppInBackground(Duration.ofSeconds(3));
					   test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					   driver.currentActivity();
					 
					 //click on my-stuff
					 
					// navigate to Last viewed tray	
						
						homepagev2.tabClick("My Stuff");
						Thread.sleep(20000);
						 
						 Utilities.verticalSwipe(driver, homepagev2.recentViewedClear);
						 
						 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
							 
							 homepagev2.lastViewedTitle.click();
							 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");

							 
							 
							 if(Utilities.explicitWaitVisible(driver, homepagev2.BookDiscription, 20)) {
								 test.log(LogStatus.INFO, "Found Book Duration");
								 
								 if(Utilities.explicitWaitVisible(driver, homepagev2.BookProgressbar, 20)) {
									 test.log(LogStatus.INFO, "Found Bok Progress bar");
									 
								 }else {
									 VK_170++;
										test.log(LogStatus.FAIL, "progressbar not found on Book");
								 }
								 
				
							 }else {
								 VK_170++;
									test.log(LogStatus.FAIL, "Video Duration not found on Book");
							 }
							 

							 
						 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
							
							
						 String lastviewbookTile1 = "";
						 try {
							 
							 lastviewbookTile1 = homepagev2.BookTitle.getText().toString().trim();
							 test.log(LogStatus.INFO, "Book from last viewed tray is : " + lastviewbookTile1);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						 
						 if(bookName1.equals(lastviewbookTile1)) {
							 test.log(LogStatus.INFO, "Found Book in under last Viewed Tray");
						 }else {
							 VK_170++;
						 }
						 
						 
							
						 if(VK_170 == 0) {
							 
							 if(!Utilities.setResultsKids(tc3, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify Book card metadata for completely read book: - VK_170");
							 
							 driver.navigate().back();
							    Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
							 
						 }else {
							 
							 if(!Utilities.setResultsKids(tc3, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify Book card metadata for completely read book: - VK_170");
							 BasePageV2.takeScreenshot();
							 
							 driver.navigate().back();
							    Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
							 
						 }
					 
					// Verify the UI of the book detail screen if book has no narration option: - VK_418
						 
						 homepagev2.tabClick("Read");
						 Thread.sleep(2000);
						 
						 String book = "";
						 
						 if(Utilities.explicitWaitVisible(driver, homepagev2.bookFirstcarosal, 10))
						 {
								 book= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
								 book=homepagev2.convertCamelCase(book);
									
								homepagev2.bookFirstcarosal.click();
								
								test.log(LogStatus.INFO, "Clicked on the Book " + book + " from Read Carousal");

								
								
						 }else test.log(LogStatus.FAIL, "Not able to found Book carosal"); 
					 
					 
						 Utilities.verticalSwipe(driver, downloadsv2.downloadSimbolBtn);
					 
					    if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookLevelCount, 10)) {
					    	test.log(LogStatus.INFO, "Found Level option in book detail page");
					    	if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageReadTime, 10)) {
					    		test.log(LogStatus.INFO, "Found Read time option in book detail page");
					    		if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageNarrationIcon, 10)) {
					    			test.log(LogStatus.INFO, "Found Narration option in book datail page");
					    			
					    		}else {
					    			test.log(LogStatus.FAIL, "Not found book detail narration icon ");
					    			VK_418++;
					    		}
					    	}else {
					    		test.log(LogStatus.FAIL, "Not found book detail page Read time");
					    		VK_418++;
					    	}
					    	
					    }else {
					    	test.log(LogStatus.FAIL, "Not found detail page Level option");
					    	VK_418++;
					    }
					 
					 
					 if(VK_418 == 0) {
						 if(!Utilities.setResultsKids(tc4, pass))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.PASS, "Verify the UI of the book detail screen if book has no narration option: - VK_418");
						 
						driver.navigate().back();
					 }else {
						 
						 if(!Utilities.setResultsKids(tc4, fail))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.FAIL, "Verify the UI of the book detail screen if book has no narration option: - VK_418");
						 BasePageV2.takeScreenshot();
						 
						 driver.navigate().back();
						 
					 }
					 
					
					 // Verify episode card metadata for completely watched contents: - VK_166
					 
					 homepagev2.tabClick("Read");
					 Thread.sleep(2000);
					 homepagev2.tabClick("My Stuff");
					 Thread.sleep(2000);
					
					 Utilities.verticalSwipe(driver);
					 Utilities.verticalSwipe(driver);
					 
					 if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClear, 10)) {
							try {
								homepagev2.recentViewedClear.click();
								try {
									homepagev2.recentViewedClearYes.click();
									test.log(LogStatus.INFO, "Clearing last viewed tray");
									
									Thread.sleep(6000);
						
								}
								catch(Exception e) {
									test.log(LogStatus.INFO, "Failed to Clear");
								}
							}
							catch(Exception e) {
								test.log(LogStatus.INFO, "Failed to click on Clear");
							}
							
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
						}
						else {
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
						}
					 
					 homepagev2.tabClick("Watch");
					 Thread.sleep(2000);
					 
					//click on card in watch carousel 1
						
						String episodeTitle = "";	 
							 
						if (Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
						{
					
						try 
						{
							episodeTitle=watchpagev2.EpisodecardTitle.getAttribute("text");	
							episodeTitle=homepagev2.convertCamelCase(episodeTitle);
							test.log(LogStatus.INFO, "clicked on first episode"+episodeTitle);
						System.out.println(episodeTitle);
						watchpagev2.cardinwatchcarousel.click();
						
						homepagev2.verifyAndProgressBar();
						Thread.sleep(10000);
						
						Utilities.scrubtillend(driver, homepagev2.audioseekBar);
						
						
						driver.navigate().back();
				
						
						}catch(Exception e)
						{
							
						}
						 
					    }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
					 
						
						
						homepagev2.tabClick("Read");
						Thread.sleep(2000);
						homepagev2.tabClick("My Stuff");
						 Thread.sleep(2000);
						 
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						 
						 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
							 
							 homepagev2.lastViewedTitle.click();
							 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
							 
							 try {
								
								   driver.findElement(By.xpath("//android.widget.TextView[@text=\""+episodeTitle+"\"]"));
									test.log(LogStatus.INFO, "watched Episode Found :  " + episodeTitle );
								 
							} catch (Exception e) {
								VK_166++;
								test.log(LogStatus.FAIL, "Episode card not Available in Last viewed Tray");
							}
							 
							 if(Utilities.explicitWaitVisible(driver, homepagev2.episodeDuration, 20)) {
								 test.log(LogStatus.INFO, "Found Episode Duration");
								 
								 if(Utilities.explicitWaitVisible(driver, homepagev2.EpisodeprogressBar, 20)) {
									 test.log(LogStatus.INFO, "Found Episode Progress bar");
									 
									 if(Utilities.explicitWaitVisible(driver, homepagev2.EpisodeDiscription, 20)) {
			
										 test.log(LogStatus.INFO, "Found Episode Discription");
					
									 }else {
										 VK_166++;
											test.log(LogStatus.FAIL, "Video Duration not found on Episode card");
									 }
									 
									 
								 }else {
									 VK_166++;
										test.log(LogStatus.FAIL, "progressbar not found on Episode card");
								 }
								 
				
							 }else {
								 VK_166++;
									test.log(LogStatus.FAIL, "Video Duration not found on Episode card");
							 }
							 

							 
						 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
					 
					 
						 if(VK_166 == 0) {
							 if(!Utilities.setResultsKids(tc6, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify episode card metadata for completely watched contents: - VK_166");
							 
							driver.navigate().back();
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
						 }else {
							 
							 if(!Utilities.setResultsKids(tc6, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify episode card metadata for completely watched contents: - VK_166");
							 BasePageV2.takeScreenshot();
							 
							 driver.navigate().back();
							 Utilities.verticalSwipeDown(driver);
							 Utilities.verticalSwipeDown(driver);
							 Utilities.verticalSwipeDown(driver);
							 
						 }
					 
					 
				
		 
			// Verify Forgot Pin link functionality: - VK_775
					 
					 driver.runAppInBackground(Duration.ofSeconds(3));
					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					 driver.currentActivity();
					 
					 Utilities.verticalSwipeDown(driver);
					 
					 if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 30)) {
		              	   
		              	   test.log(LogStatus.INFO, "Found Profile PIc Icon");
		              	   homepagev2.profilepic.click(); // tap on profile icon
		             	   
		                 }else {
		                	 test.log(LogStatus.FAIL, "Not able to click profile icon in home page");
		                	 
		                	  try {
		                     	   homepagev2.profilepic.click(); // tap on profile icon
		       				} catch (Exception e) {
		       					e.printStackTrace();
		       				}
		                 }
						 
				
							
				//Verify the functionality post changing Preferred Language options from Settings:
							
							if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsIcon, 10)) {
								settingspagev2.settingsIcon.click();
								if (Utilities.explicitWaitVisible(driver, settingspagev2.parentPinContainer, 10)) {
									settingspagev2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
									
									if(Utilities.explicitWaitVisible(driver, settingspagev2.settingTextinParantZoneAccount, 10)) {
									        settingspagev2.settingTextinParantZoneAccount.click();
									         driver.runAppInBackground(Duration.ofSeconds(3));
									   	     test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									   	    driver.currentActivity();
									        if (Utilities.explicitWaitVisible(driver,settingspagev2.accountResetparZonePin, 10)) {
									        	settingspagev2.accountResetparZonePin.click();
                               			}else BasePageV2.reportFail("Reset Parent Zone Pin Option is not found in Account page");    
									}else BasePageV2.reportFail("unable to navigate to Account Page");
								}else test.log(LogStatus.FAIL, " Not able to entered Pin in parent Zone page");
							}else test.log(LogStatus.FAIL, "Not able to click Settings icon");		
					 
					 
							driver.runAppInBackground(Duration.ofSeconds(3));
					   	     test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					   	    driver.currentActivity();
							
							if (Utilities.explicitWaitVisible(driver, settingspagev2.ResetPinforgotPin, 10)) {
								
								settingspagev2.ResetPinforgotPin.click();
								test.log(LogStatus.INFO, "click on Forgot Pin Link");
								
							}else BasePageV2.reportFail("'For got Pin' option is not found in Parent  Zone");
					 
					 
					        if(Utilities.explicitWaitVisible(driver, settingspagev2.forgotPinMsgBody, 10)) {
					        	if(Utilities.explicitWaitVisible(driver, settingspagev2.forgotPinpopupNObtn, 10)) {
					        		if(Utilities.explicitWaitVisible(driver, settingspagev2.forgotPinpopupYESbtn, 10)) {
					        			
					        		}else {
					        			VK_775++;
					        			test.log(LogStatus.FAIL, "On Forgot Pin pop up body message not found");
					        		}
					        		
					        	}else {
					        		VK_775++;
				        			test.log(LogStatus.FAIL, "On Forgot Pin pop up NO button not found");
					        	}
					        	
					        }else {
					        	VK_775++;
			        			test.log(LogStatus.FAIL, "On Forgot Pin pop up YES button not found");
					        }
					 
					 
					   if(VK_775 == 0) {
						   if(!Utilities.setResultsKids(tc5, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify Forgot Pin link functionality: - VK_775");
							 
							if(Utilities.explicitWaitClickable(driver, settingspagev2.ForgotPinPopUpNOBtn, 10)) {
								settingspagev2.ForgotPinPopUpNOBtn.click();
							}
						 }else {
							 
							 if(!Utilities.setResultsKids(tc5, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify Forgot Pin link functionality: - VK_775");
							 BasePageV2.takeScreenshot();
							 
							 if(Utilities.explicitWaitClickable(driver, settingspagev2.ForgotPinPopUpNOBtn, 10)) {
									settingspagev2.ForgotPinPopUpNOBtn.click();
								}
				 
						 }
		 
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
