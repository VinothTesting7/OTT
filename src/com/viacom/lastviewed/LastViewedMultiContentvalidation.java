package com.viacom.lastviewed;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import com.viacom.pagesVersion2.KidsPlayerPageV2;
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
//Author - Suresh
public class LastViewedMultiContentvalidation extends BaseTestV2 {
	
	
	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "LastViewedMultiContentvalidation";
	
	String tc1 = "VK_162";
	String tc2 = "VK_163";
	String tc3 = "VK_171";
	String tc4 = "VK_172";
	String tc5 = "VK_174";
	String tc6 = "VK_167";
	String tc7 = "VK_168";
	
	String bookName = "";
	
	 
	int VK_167 = 0 , VK_168 = 0;
	
	 Set<String> bookTitles = new TreeSet<String>();
	 Set<String> lastViewedtray = new TreeSet<String>();
	 Set<String> LatestlastViewedtray = new TreeSet<String>();
	 
	
	 
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("LastViewedMultiContentvalidation");
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
				 KidsPlayerPageV2 kidsPlayerPageV2 = new KidsPlayerPageV2(driver, test);
				 
				 
				 
				 homepagev2.login(data.get("Email"),data.get("Password"));
				 
				 
			    // Verify the no.of cards present under 'Last Viewed' section: - VK_162 
			 
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
				 
					
					
					homepagev2.tabClick("Read");
					 Thread.sleep(2000);
					
					 String bookNameBefore = "";
					 if(Utilities.explicitWaitVisible(driver, readpagev2.readfirstBookcarosal, 10))
					 {
						 try {
							 bookNameBefore= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
							 bookNameBefore=homepagev2.convertCamelCase(bookNameBefore);
								
							readpagev2.readfirstBookcarosal.click();
							
							test.log(LogStatus.INFO, "Clicked on the Book "+bookNameBefore+" from Read Carousal");
							bookTitles.add(bookNameBefore);
									 
						} catch (Exception e) {
							// TODO: handle exception
						}
						
							
							if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
								
								bookspagev2.bookDetailPageBookPreviewButton.click();
								Thread.sleep(20000);
								
								driver.navigate().back();
								
								
								
							}else {
								test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
								
							}
							
							
					 }else BasePageV2.reportFail("Not able to found Book carosal");
					 
					 
				 
				 Utilities.verticalSwipe(driver,bookspagev2.bookDetailPageRelatedBooksTray);
				 
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 
				 
				 for(int i = 0 ; i <=3 ; i ++) {
					 
					 
					 driver.findElement(By.xpath("//android.view.ViewGroup[@index='"+i+"']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")).click();
					 Thread.sleep(6000);
	
                     String book = bookspagev2.bookDetailPageBookTitle.getText().toString().trim();
					 
					 String bookTitle=homepagev2.convertCamelCase(book);
					 
					 System.out.println("The Book Title is : " + bookTitle);
					 
					 bookTitles.add(bookTitle);
					 
					 if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
							
							bookspagev2.bookDetailPageBookPreviewButton.click();
							Thread.sleep(10000);
							
							driver.navigate().back();
							driver.navigate().back();
							
							
						}else {
							test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
							
						}
				 
					
	 
				 }
				 
				 
				 driver.navigate().back();
				 
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();
				 
				 
				 
				 // click on Listen Tab
				 
				 String AudiocardName = "";
				 
				 HomePageV2.tabClick("Listen");
					
					try {
						
					
					String carousalBooksLocTitle="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
					 
					String temp=driver.findElement(By.xpath(carousalBooksLocTitle)).getAttribute("text");
					 AudiocardName=homepagev2.convertCamelCase(temp);
					 
					 test.log(LogStatus.INFO, "The Audio card Title is  : " + AudiocardName);
					 
					 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
					 
					 if(Utilities.explicitWaitClickable(driver,listenpageV2.play_btn , 30)) {
						 listenpageV2.play_btn.click();
						 test.log(LogStatus.INFO, "clicked on play button");
						 Thread.sleep(20000);
						 
						 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
							 kidsPlayerPageV2.audioPlayerCloseButton.click();
						 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");

						 Utilities.verticalSwipe(driver, kidsPlayerPageV2.AudioDetailPageRelatedAudioTray);
						 Utilities.verticalSwipe(driver);
						 Utilities.verticalSwipe(driver);
						 Utilities.verticalSwipe(driver);
					 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
					 
					} catch (Exception e) {
						// TODO: handle exception
					}
				 
					 for(int i = 0 ; i <=3 ; i ++) {
						 
						 
						 driver.findElement(By.xpath("//android.view.ViewGroup[@index='"+i+"']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")).click();
						 Thread.sleep(6000);
		
	                     String audio = kidsPlayerPageV2.AudioDetailPageAudioTitle.getText().toString().trim();
						 
						 String audioTitle=homepagev2.convertCamelCase(audio);
						 
						 System.out.println("The Book Title is : " + audioTitle);
						 
						 bookTitles.add(audioTitle);
						 
						 if(Utilities.explicitWaitClickable(driver, listenpageV2.play_btn, 20)) {
								
							 listenpageV2.play_btn.click();
							 
								Thread.sleep(30000);
								
								if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
									 kidsPlayerPageV2.audioPlayerCloseButton.click();
								 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");
								
								driver.navigate().back();
								driver.navigate().back();
								
								
							}else {
								test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
								
							}
					 
						
		 
					 }
				 
		
				 homepagev2.tabClick("My Stuff");
				 Thread.sleep(20000);
				 
				 Utilities.verticalSwipe(driver);
				 Thread.sleep(10000);
				 Utilities.verticalSwipe(driver);
				 Thread.sleep(10000);
				 
				 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
					 
					 homepagev2.lastViewedTitle.click();
					 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
					 
				 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
				 
				 for(int i = 0 ; i < bookTitles.size(); i++) {
					 
					 try {
					 String audio = driver.findElement(By.xpath("//android.view.ViewGroup[@index='"+i+"']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")).getText().toString().trim();
					 Thread.sleep(2000);
	
                     lastViewedtray.add(audio);
                     if(i == 3 || i == 5) {
                    	 Utilities.verticalSwipe(driver);
                     }
					 } catch (Exception e) {
	       					e.printStackTrace();
	       				}
					 
				 }
				
				 if(bookTitles.size() == lastViewedtray.size()) {
					 
					 if(!Utilities.setResultsKids(tc1, pass))
					      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.PASS, "Verify the no.of cards present under 'Last Viewed' section: - VK_162");
					 
					 driver.navigate().back();
					 
					 
				 }else {
					 
					 if(!Utilities.setResultsKids(tc1, fail))
					      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.FAIL, "Verify the no.of cards present under 'Last Viewed' section: - VK_162");
					 BasePageV2.takeScreenshot();
					 
					 driver.navigate().back();
					 
				 }
				
				 
				 
				 
				 // Verify the cards removal order under 'Last Viewed' section when user watches more than 8 cards: - VK_163
				 
				
				//Click on Watch tab 						 
				 homepagev2.tabClick("Watch");	
				 Thread.sleep(10000);
		
          //click on card in watch carousel 1
	  
			String episodeTitle = "";	 
				 
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
			
			
			
			}catch(Exception e)
			{
				
			}

			 
	        }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
		
					
			homepagev2.tabClick("My Stuff");
			 Thread.sleep(10000);
			 
			 Utilities.verticalSwipe(driver);
			 
			 Utilities.verticalSwipe(driver);
			 Thread.sleep(10000);
			 
			 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
				 
				 homepagev2.lastViewedTitle.click();
				 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
				 
			 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
			 
			 for(int i = 0 ; i < 10; i++) {
				 
				 try {
				 String audio = driver.findElement(By.xpath("//android.view.ViewGroup[@index='"+i+"']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")).getText().toString().trim();
				 Thread.sleep(2000);

                LatestlastViewedtray.add(audio);
                if(i == 3 || i == 5) {
               	 Utilities.verticalSwipe(driver);
                }
     
				} catch (Exception e) {
					// TODO: handle exception
				}
				 
              }
				 
			 if (!lastViewedtray.containsAll(LatestlastViewedtray)) {
				 
				 if(!Utilities.setResultsKids(tc2, pass))
				      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 test.log(LogStatus.PASS, "Verify the cards removal order under 'Last Viewed' section when user watches more than 8 cards: - VK_163");
				 
				 driver.navigate().back();
				 
				 
			 }else {
				 
				 if(!Utilities.setResultsKids(tc2, fail))
				      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 test.log(LogStatus.FAIL, "Verify the cards removal order under 'Last Viewed' section when user watches more than 8 cards: - VK_163");
				 BasePageV2.takeScreenshot();
				 
				 driver.navigate().back();

			 }
				  
				 
			//	 Verify episode card metadata for partially watched contents: - VK_171
				 
				if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClear, 10)) {
					try {
						homepagev2.recentViewedClear.click();
						
						if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClearMessage, 10)) {
							if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClearYes, 10)) {
								if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClearNo, 10)) {
									if(!Utilities.setResultsKids(tc3, pass))
									      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 test.log(LogStatus.PASS, "Verify 'Clear' link functionality: - VK_171");
									
								}else {
									test.log(LogStatus.FAIL, "NO button not found on Lastviwed clear pop up");
									 if(!Utilities.setResultsKids(tc3, fail))
									      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 test.log(LogStatus.FAIL, "Verify 'Clear' link functionality: - VK_171");
									 BasePageV2.takeScreenshot();
								}
								
							}else {
								test.log(LogStatus.FAIL, "YES button not found on Lastviwed clear pop up");
								 if(!Utilities.setResultsKids(tc3, fail))
								      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 test.log(LogStatus.FAIL, "Verify 'Clear' link functionality: - VK_171");
								 BasePageV2.takeScreenshot();
								
							}
							
							
						}else {
							test.log(LogStatus.FAIL, "Body Message not found on Lastviwed clear pop up");
							 if(!Utilities.setResultsKids(tc3, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify 'Clear' link functionality: - VK_171");
							 BasePageV2.takeScreenshot();
						}
						
						if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClearYes, 10)) {
							
							homepagev2.recentViewedClearYes.click();
							Thread.sleep(6000);
						}else {
							test.log(LogStatus.FAIL, "Not clicked YES button on Last viewed clear pop up");
						}
					
						if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
							test.log(LogStatus.FAIL, "Last Viewed tray Displayed");
							 if(!Utilities.setResultsKids(tc4, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify the functionality of 'Yes' option in clear link confirmation message: - VK_172");
							 BasePageV2.takeScreenshot();
							    Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
							
						}else {
							if(!Utilities.setResultsKids(tc4, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify the functionality of 'Yes' option in clear link confirmation message: - VK_172");
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
				
				 
				 // Verify Audio card metadata for partially watched contents: - VK_167
				
				   homepagev2.tabClick("Listen");
				
                  try {
						
					
					String carousalBooksLocTitle="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
					 
					String temp=driver.findElement(By.xpath(carousalBooksLocTitle)).getAttribute("text");
					 AudiocardName=homepagev2.convertCamelCase(temp);
					 
					 test.log(LogStatus.INFO, "The Audio card Title is  : " + AudiocardName);
					 
					 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
					 
					 if(Utilities.explicitWaitClickable(driver,listenpageV2.play_btn , 30)) {
						 listenpageV2.play_btn.click();
						 test.log(LogStatus.INFO, "clicked on play button");
						 Thread.sleep(20000);
						
						 
						 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
							 kidsPlayerPageV2.audioPlayerCloseButton.click();
							 driver.navigate().back();
						 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");

						 
					 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
					 
					} catch (Exception e) {
						// TODO: handle exception
					}
				
				     homepagev2.tabClick("Read");
				     
				     
				     homepagev2.tabClick("My Stuff");
				     Thread.sleep(2000);
				     
				     Utilities.verticalSwipe(driver);
				     Utilities.verticalSwipe(driver);
				     
				     
				     if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
						 
						 homepagev2.lastViewedTitle.click();
						 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
						 
						 if(Utilities.explicitWaitVisible(driver, listenpageV2.audioPlayerMorFrmAuthrFirstAudioName, 10)) {
							 if(Utilities.explicitWaitVisible(driver, listenpageV2.audioPlayerMorFrmAuthrFirstAudioAuthor, 10)) {
	
							 }else {
								 VK_167++;
								 test.log(LogStatus.FAIL, "Audio card Discription not found on Audio car in Last Viewed Tray");
							 }
							 
						 }else {
							 VK_167++;
							 test.log(LogStatus.FAIL, "Audio card title not found on Audio car in Last Viewed Tray");
						 }

						 
					 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
				     
				     
				     if(VK_167 == 0) {
				     
				    	 if(!Utilities.setResultsKids(tc6, pass))
					      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					      test.log(LogStatus.PASS, "Verify Audio card metadata for partially watched contents: - VK_167");
				    	 
					      driver.navigate().back();
		
					           //  Clear Last viewed data
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
					              
					     
				     }else {
				    	 if(!Utilities.setResultsKids(tc6, fail))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.FAIL, "Verify Audio card metadata for partially watched contents: - VK_167");
						 BasePageV2.takeScreenshot();
						 driver.navigate().back();
					     
						 // clear last viewed data 
						 
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
				     }
				     
				    
				    
				    //Verify Audio card metadata for completey watched contents: - VK_168
				    
				     homepagev2.tabClick("Listen");
				      
				    try {
						
						
						String carousalBooksLocTitle="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
						 
						String temp=driver.findElement(By.xpath(carousalBooksLocTitle)).getAttribute("text");
						 AudiocardName=homepagev2.convertCamelCase(temp);
						 
						 test.log(LogStatus.INFO, "The Audio card Title is  : " + AudiocardName);
						 
						 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
						 
						 if(Utilities.explicitWaitClickable(driver,listenpageV2.play_btn , 30)) {
							 listenpageV2.play_btn.click();
							 test.log(LogStatus.INFO, "clicked on play button");
							 Thread.sleep(10000);
							
							 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
							 
							 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
								 kidsPlayerPageV2.audioPlayerCloseButton.click();
								 driver.navigate().back();
							 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");

							 
						 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
						 
						} catch (Exception e) {
							// TODO: handle exception
						}
					
					     homepagev2.tabClick("Read");
					     
					     
					     homepagev2.tabClick("My Stuff");
					     Thread.sleep(2000);
					     
					     Utilities.verticalSwipe(driver);
					     Utilities.verticalSwipe(driver);
					     
					     
					     if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
							 
							 homepagev2.lastViewedTitle.click();
							 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
							 
							 if(Utilities.explicitWaitVisible(driver, listenpageV2.audioPlayerMorFrmAuthrFirstAudioName, 10)) {
								 if(Utilities.explicitWaitVisible(driver, listenpageV2.audioPlayerMorFrmAuthrFirstAudioAuthor, 10)) {
		
								 }else {
									 VK_168++;
									 test.log(LogStatus.FAIL, "Audio card Discription not found on Audio car in Last Viewed Tray");
								 }
								 
							 }else {
								 VK_168++;
								 test.log(LogStatus.FAIL, "Audio card title not found on Audio car in Last Viewed Tray");
							 }

							 
						 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
					     
					     
					     if(VK_168 == 0) {
					     
					    	 if(!Utilities.setResultsKids(tc7, pass))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						      test.log(LogStatus.PASS, "Verify Audio card metadata for completey watched contents: - VK_168");
					    	 
						      driver.navigate().back();
			
						           //  Clear Last viewed data
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
						              
						     
					     }else {
					    	 if(!Utilities.setResultsKids(tc7, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify Audio card metadata for completey watched contents: - VK_168");
							 BasePageV2.takeScreenshot();
							 driver.navigate().back();
						     
							 // clear last viewed data 
							 
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
					     }
					     
		
		
				 
				 // Verify the last viewed assets are retained in parent zone after clearing from Last viewed section: - VK_174
				
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
				 
				    if(Utilities.explicitWaitClickable(driver, settingspagev2.parentZoneBtn, 20)) {
				    	settingspagev2.parentZoneBtn.click();
				    	
				    	if (Utilities.explicitWaitVisible(driver, settingspagev2.settingsIcon, 10)) {
							settingspagev2.settingsIcon.click();
							if (Utilities.explicitWaitVisible(driver, settingspagev2.parentPinContainer, 10)) {
								settingspagev2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
							}else test.log(LogStatus.FAIL, " Not able to entered Pin in parent Zone page");
						}else test.log(LogStatus.FAIL, "Not able to click Settings icon");	
				    	
				    }else test.log(LogStatus.FAIL, "Not click on Parent Zone button / not found in Switch profile page");
				 
				    Utilities.verticalSwipe(driver, settingspagev2.RecentActivityTitle);
				 
				    Utilities.verticalSwipe(driver);
				    Utilities.verticalSwipe(driver);
				    
				    for(String contentTitle : LatestlastViewedtray) {
							
				    		if(settingspagev2.parentZoneRecentActivityItemNames.contains(contentTitle)) {
									if(!Utilities.setResultsKids(tc5, pass))
									      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 test.log(LogStatus.PASS, "Verify the last viewed assets are retained in parent zone after clearing from Last viewed section: - VK_174");
				    			break;
				    		}else {
				    			if(!Utilities.setResultsKids(tc5, fail))
								      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 test.log(LogStatus.FAIL, "Verify the last viewed assets are retained in parent zone after clearing from Last viewed section: - VK_174");
								 BasePageV2.takeScreenshot();
				    		}
			
				    	
				    }
				 
				 
				    
				 
				 
	 }
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

				 

}
