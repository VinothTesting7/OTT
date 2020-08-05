package com.viacom.lastviewed;
import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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


public class LastViewedTrayContentStreamQualityValidation extends BaseTestV2 {
	
	
	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "LastViewedTrayContentStreamQualityValidation";
	
	String tc1 = "VK_1895";
	String tc2 = "VK_1897";
	String tc3 = "VK_1898";
	String tc4 = "VK_1901";
	String tc5 = "VK_1902";
	String tc6 = "VK_1903";
	String tc7 = "VK_1899";
	
	 int VK_1895 = 0 ,VK_1899 = 0 ;
	 
	 
	 String defaultmagnify = "",defaultDictionary = "",defaultAutoPageturn="",LastviewdTraydefaultmagnify = "",LastviewdTraydefaultDictionary="",LastviewdTraydefaultAutoPageturn = "";
	 
	
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("LastViewedTrayContentStreamQualityValidation");
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
				 
				 
				 
				 // Verify the playback status for Downloaded Ebook card post playing an Ebook and downloading the same - VK_1895
				 
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 
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
						
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						
						
					}
					else {
						test.log(LogStatus.INFO, "Clear link is not clickable");
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
					}
				 
				 
				 // clear Downloads 
				 
				 downloadsv2.deleteAllDownloads();
				 driver.navigate().back();
				 
				 Utilities.verticalSwipeDown(driver);
				 Utilities.verticalSwipeDown(driver);
				 Utilities.verticalSwipeDown(driver);
				 
				 
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
							Thread.sleep(20000);
							
							for(int i = 0 ; i < 8 ; i++) {
								
								Utilities.horizontalSwipe(driver);
								Thread.sleep(6000);
								
							}
							
							
							
							driver.navigate().back();
							Thread.sleep(40000);
							driver.navigate().back();
							
							Utilities.verticalSwipe(driver, downloadsv2.downloadSimbolBtn);
							
							if(Utilities.explicitWaitVisible(driver, downloadsv2.downloadStatusText, 20)) {
								downloadsv2.downloadSimbolBtn.click();
								test.log(LogStatus.INFO, "clicked on Download Book ");
								Thread.sleep(10000);
								driver.navigate().back();
								Utilities.verticalSwipeDown(driver);
								
							}else {
								test.log(LogStatus.INFO, "Book downloaded ");
								driver.navigate().back();
								Utilities.verticalSwipeDown(driver);
							}
			
						
						}else {
							test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
							
						}
						
						
								 
					} catch (Exception e) {
						// TODO: handle exception
					}
					
						
						
				 }else test.log(LogStatus.FAIL, "Not able to found Book carosal"); 
				 
				 
				 // click on My suffs
				 
				 homepagev2.tabClick("Read");
				 
				 homepagev2.tabClick("My Stuff");
				 Thread.sleep(2000);
				 
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 
		 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
					 
					 homepagev2.lastViewedTitle.click();
					 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
					 
					 try {
						
						   driver.findElement(By.xpath("//android.widget.TextView[@text=\""+bookName+"\"]"));
							test.log(LogStatus.INFO, "watched book Found :  " + bookName );
							driver.navigate().back();
						 
					} catch (Exception e) {
						VK_1895++;
						driver.navigate().back();
						test.log(LogStatus.FAIL, "book card not Available in Last viewed Tray");
					}
					 
					 
				 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
				 
				 Utilities.verticalSwipe(driver, downloadsv2.downloadsTabMystuffpage);
				 
				 if(Utilities.explicitWaitClickable(driver, downloadsv2.downloadsTabMystuffpage, 20)) {
					 downloadsv2.downloadsTabMystuffpage.click();
					 test.log(LogStatus.INFO, "clicked on Downloads tab");
					 Utilities.verticalSwipe(driver);
					 
					 
					 if(Utilities.explicitWaitClickable(driver, downloadsv2.editDownloadsMystuff, 20)) {
						 
						 Utilities.verticalSwipe(driver);
						 
						 try {
								
							 driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item']//android.widget.TextView[@text='"+bookName+"']")).click();
							 
								 test.log(LogStatus.INFO, "Ebook playback started");
								 driver.navigate().back();
								 Utilities.verticalSwipeDown(driver);
								 Utilities.verticalSwipeDown(driver);
								 Utilities.verticalSwipeDown(driver);
							
			 
							 
						} catch (Exception e) {
							
							VK_1895++;
							 driver.navigate().back();
							 Utilities.verticalSwipeDown(driver);
							 Utilities.verticalSwipeDown(driver);
							 Utilities.verticalSwipeDown(driver);
							System.out.print(e);
							e.printStackTrace();
							e.printStackTrace(System.out);
						}
						 
						 
						 
					 }else test.log(LogStatus.FAIL, "Book is nOt downloaded / Not found");
					 
					
					 
				 }else {
					 test.log(LogStatus.FAIL, "Not clicked on Downloads tab button");
				 }
				 
				 
				 
				 if(VK_1895 == 0) {
				 
			
						 if(!Utilities.setResultsKids(tc1, pass))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.PASS, "Verify the playback status for Downloaded Ebook card post playing an Ebook and downloading the same - VK_1895");
						 
						 driver.navigate().back();
						 Utilities.verticalSwipeDown(driver);
						 Utilities.verticalSwipeDown(driver);
						 Utilities.verticalSwipeDown(driver);
						 
					 }else {
						 
						 if(!Utilities.setResultsKids(tc1, fail))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.FAIL, "Verify the playback status for Downloaded Ebook card post playing an Ebook and downloading the same - VK_1895");
						 BasePageV2.takeScreenshot();
						 
						 driver.navigate().back();
						 Utilities.verticalSwipeDown(driver);
						 Utilities.verticalSwipeDown(driver);
						 Utilities.verticalSwipeDown(driver);
						 
					 }
				 
			 
				 
				 // Verify the stream quality for Last viewed assets post changing stream quality from player screen - VK_1897
				 
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
			Thread.sleep(6000);
			
			 //Pause the video
			  kidsPlayerPageV2.pauseVideo();
			  
			  if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.playerSettings, 10))
			  {
				  test.log(LogStatus.INFO, "Player Settings is displayed on the Player screen");
				  test.log(LogStatus.INFO, "Verifying UI  of the Settings overlay");
				  kidsPlayerPageV2.playerSettings.click();
			  
				  if(Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.selectQualityList, 10))
				  {
					  test.log(LogStatus.INFO, "Select Quality List button is displayed");
					  if(Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.defaultSelectedQuality, 10))
					  {
						  String quality=kidsPlayerPageV2.defaultSelectedQuality.getText();
						  if(quality!=null && !quality.equals(""))
						  {
							  if(quality.equals("auto") || quality.equals("Auto") )
							  {
								  test.log(LogStatus.INFO, "Default selected Quality is : "+ quality);
								 
							  }else test.log(LogStatus.FAIL, "Default selected Quality is  not auto: ");
							  
						  }
						  else
							test.log(LogStatus.FAIL, "No default quality is selected ");
					  }
					  else
						  test.log(LogStatus.FAIL, "Default selected Quality is not displayed ");
					  
				  }
				  else
					  test.log(LogStatus.FAIL, "Select quality List button is not displayed");
				  
				  
				  
				  
				  kidsPlayerPageV2.selectQualityList1.click();
				  
				  
				  
				  
				  Thread.sleep(5000);
				     driver.runAppInBackground(Duration.ofSeconds(3));
					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					 driver.currentActivity();
				  
				  if(Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.playerSettingsLowQuality1, 20)) {
					  test.log(LogStatus.INFO, "Low quality setting is displayed");
				  kidsPlayerPageV2.playerSettingsLowQuality1.click();
				  test.log(LogStatus.INFO, "Changed Default Stream Quality to 'Low'");
				  test.log(LogStatus.INFO, "Selected Stream Quality as Low");
				  
				  } else
					  test.log(LogStatus.FAIL, "Low quality is not displayed / Low is default");
	
				     driver.navigate().back();
				     driver.navigate().back();
					 
					  
					//Click on Watch tab 						 
						 homepagev2.tabClick("Watch");	
						 Thread.sleep(10000);
						 
						 homepagev2.tabClick("My Stuff");
						 Thread.sleep(2000);
						 
						 Utilities.verticalSwipe(driver);
						 Utilities.verticalSwipe(driver);
						 
				 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
							 
							 homepagev2.lastViewedTitle.click();
							 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
							 
							 try {
							
								    Thread.sleep(5000);
								    
								     driver.runAppInBackground(Duration.ofSeconds(3));
									 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									 driver.currentActivity();
									 
								    homepagev2.lastupdatedintrayb.click();
					
							
									homepagev2.verifyAndProgressBar();
									Thread.sleep(6000);
									
									 //Pause the video
									  kidsPlayerPageV2.pauseVideo();
									  
									  if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.playerSettings, 10))
									  {
										  test.log(LogStatus.INFO, "Player Settings is displayed on the Player screen");
										  test.log(LogStatus.INFO, "Verifying UI  of the Settings overlay");
										  kidsPlayerPageV2.playerSettings.click();
									  
										  if(Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.selectQualityList, 10))
										  {
											  test.log(LogStatus.INFO, "Select Quality List button is displayed");
											  if(Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.defaultSelectedQuality, 10))
											  {
												  String quality=kidsPlayerPageV2.defaultSelectedQuality.getText();
												  if(quality!=null && !quality.equals(""))
												  {
													  if(quality.equals("auto") || quality.equals("Auto") )
													  {
														  test.log(LogStatus.INFO, "Default selected Quality is : "+ quality);
														  
														  if(!Utilities.setResultsKids(tc2, pass))
														      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
														 test.log(LogStatus.PASS, "Verify the stream quality for Last viewed assets post changing stream quality from player screen - VK_1897");
														 
														 driver.navigate().back();
														 driver.navigate().back();
														 driver.navigate().back();
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 
													 }else {
														 
														 if(!Utilities.setResultsKids(tc2, fail))
														      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
														 test.log(LogStatus.FAIL, "Verify the stream quality for Last viewed assets post changing stream quality from player screen - VK_1897");
														 BasePageV2.takeScreenshot();
														 
														 driver.navigate().back();
														 driver.navigate().back();
														 driver.navigate().back();
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 
													 }
													 
													  
												  }
												  else
													test.log(LogStatus.FAIL, "No default quality is selected ");
											  }
											  else
												  test.log(LogStatus.FAIL, "Default selected Quality is not displayed ");
											  
										  }
										  else
											  test.log(LogStatus.FAIL, "Select quality List button is not displayed");
										  
										  
									  }else test.log(LogStatus.FAIL, "player Settings Icon not found to click");
									
									
									
									
									
								 
							} catch (Exception e) {
								
								driver.navigate().back();
								test.log(LogStatus.FAIL, "Episode card not Available in Last viewed Tray");
								Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
							}
							 
							 
						 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
						 
					  
					  
					  
					  
			  }else test.log(LogStatus.FAIL, "Not found Settings Icon on Player");
			  

			}catch(Exception e)
			{
				
			}

			 
	        }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
		
				 
			// Verify the stream quality for Last viewed assets post changing stream quality from player screen - VK_1898
			
			
			Utilities.verticalSwipe(driver);
			 Utilities.verticalSwipe(driver);
			 
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
					
					Utilities.verticalSwipeDown(driver);
					Utilities.verticalSwipeDown(driver);
					Utilities.verticalSwipeDown(driver);
					
					
				}
				else {
					test.log(LogStatus.INFO, "Clear link is not clickable");
					Utilities.verticalSwipeDown(driver);
					Utilities.verticalSwipeDown(driver);
					Utilities.verticalSwipeDown(driver);
				}
			 
			 
			 // click Listen tab
			 
			 String AudiocardName = "";
			 
			 HomePageV2.tabClick("Listen");
				
				try {
					
				
				String carousalAudioCardTitle="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
				 
				String temp=driver.findElement(By.xpath(carousalAudioCardTitle)).getAttribute("text");
				 AudiocardName=homepagev2.convertCamelCase(temp);
				 
				 test.log(LogStatus.INFO, "The Audio card Title is  : " + AudiocardName);
				 
				 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
				 
				 Utilities.verticalSwipeDown(driver);
				 
				 if(Utilities.explicitWaitClickable(driver,listenpageV2.play_btn , 30)) {
					 listenpageV2.play_btn.click();
					 test.log(LogStatus.INFO, "clicked on play button");
					 Thread.sleep(20000);
			 
					if(Utilities.explicitWaitClickable(driver, listenpageV2.firstItemCarousalPausePlay, 20)) {
						listenpageV2.firstItemCarousalPausePlay.click();
						if(Utilities.explicitWaitClickable(driver, listenpageV2.audioPlayerSettings, 20)) {
							listenpageV2.audioPlayerSettings.click();
							if(Utilities.explicitWaitClickable(driver, listenpageV2.audioPlayerSettingsSelectQualityDefault, 20)) {
								listenpageV2.audioPlayerSettingsSelectQualityDefault.click();
							
									Thread.sleep(5000);
									driver.navigate().back();
									driver.navigate().back();
									listenpageV2.playerCloseButton.click();
									driver.navigate().back();
								
							}else test.log(LogStatus.FAIL, "Not click on sream Quality settings drop down");
						}else test.log(LogStatus.FAIL, "Not clicked on Audio Settings Icon in a Audio player");
						
					}else test.log(LogStatus.FAIL, "Not click on pause button on player / Not Found");
	
				 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
				 
				} catch (Exception e) {
					e.printStackTrace();
					test.log(LogStatus.FAIL, e.getMessage());
				}
				 
				HomePageV2.tabClick("Read");
				
				homepagev2.tabClick("My Stuff");
				 Thread.sleep(2000);
				 
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 
		 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
					 
					 homepagev2.lastViewedTitle.click();
					 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
					 
					 try {
					
						    Thread.sleep(5000);
						    
						     driver.runAppInBackground(Duration.ofSeconds(3));
							 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							 driver.currentActivity();
							 
						    homepagev2.lastupdatedintrayb.click();
			
					
							  
							  if(Utilities.explicitWaitClickable(driver, listenpageV2.audioPlayBtn, 10))
							  {
								  listenpageV2.audioPlayBtn.click();
								  test.log(LogStatus.INFO, "clicked on play button");
								  
								  
								 
								  
								  if(Utilities.explicitWaitClickable(driver, listenpageV2.audioPauseBTN, 20)) {
										listenpageV2.audioPauseBTN.click();
										
										if(Utilities.explicitWaitClickable(driver, listenpageV2.audioPlayerSettings, 20)) {
											listenpageV2.audioPlayerSettings.click();
											if(Utilities.explicitWaitClickable(driver, listenpageV2.audioPlayerSettingsSelectQualityDefault, 20)) {
												
												  String quality=listenpageV2.audioPlayerSettingsSelectQualityDefault.getText();
												  if(quality!=null && !quality.equals(""))
												  {
													  if(quality.equals("auto") || quality.equals("Auto") )
													  {
														  test.log(LogStatus.INFO, "Default selected Quality is : "+ quality);
														  
														  if(!Utilities.setResultsKids(tc3, pass))
														      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
														 test.log(LogStatus.PASS, "Verify the stream quality for Last viewed assets post changing stream quality from player screen - VK_1898");
														 
														 driver.navigate().back();
														 listenpageV2.playerCloseButton.click();
														 driver.navigate().back();
														 driver.navigate().back();
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 
													 }else {
														 
														 if(!Utilities.setResultsKids(tc3, fail))
														      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
														 test.log(LogStatus.FAIL, "Verify the stream quality for Last viewed assets post changing stream quality from player screen - VK_1898");
														 BasePageV2.takeScreenshot();
														 
														 driver.navigate().back();
														 driver.navigate().back();
														 driver.navigate().back();
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 Utilities.verticalSwipeDown(driver);
														 
													 }
													 
													  
												  
											  
										  }
										  else
											  test.log(LogStatus.FAIL, "Select quality List button is not displayed");
												
												
											}else test.log(LogStatus.FAIL, "Not click on sream Quality settings drop down");
										}else test.log(LogStatus.FAIL, "Not clicked on Audio Settings Icon in a Audio player");
										
									}else test.log(LogStatus.FAIL, "Not click on pause button on player / Not Found");
					
				
							  }else test.log(LogStatus.FAIL, "player Settings Icon not found to click");
							
							
							
							
							
						 
					} catch (Exception e) {
						
						driver.navigate().back();
						test.log(LogStatus.FAIL, "Episode card not Available in Last viewed Tray");
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
					}
					 
					 
				 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
				 
			 
				 
			// 	 Verify if the Episode cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1901
		 
		 
		 
		 Utilities.verticalSwipe(driver);
		 Utilities.verticalSwipe(driver);
		 
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
				
				Utilities.verticalSwipeDown(driver);
				Utilities.verticalSwipeDown(driver);
				Utilities.verticalSwipeDown(driver);
				
				
			}
			else {
				test.log(LogStatus.INFO, "Clear link is not clickable");
				Utilities.verticalSwipeDown(driver);
				Utilities.verticalSwipeDown(driver);
				Utilities.verticalSwipeDown(driver);
			}
		 
		 
		//Click on Watch tab 						 
		 homepagev2.tabClick("Watch");	
		 Thread.sleep(5000);

			  //click on card in watch carousel 1
			
				String episodeTitle1 = "";	 
					 
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
				driver.navigate().back();
		
				
				}catch(Exception e)
				{
					
				}
				 
			    }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
							 
				//Click on Read tab 						 
				 homepagev2.tabClick("Read");	
				 Thread.sleep(2000);
				 
				//Click on Watch tab 
				 
				 
				 homepagev2.tabClick("Watch");
				 Thread.sleep(4000);
				 Utilities.horizontalSwipe(driver);
				 
				 Thread.sleep(2000);
				 
				 if (Utilities.explicitWaitVisible(driver, watchpagev2.cardSecodinwatchcarousel, 10))
					{
				
					try 
					{
						 episodeTitle1=watchpagev2.cardSecodinwatchcarousel.getAttribute("text");	
						episodeTitle1=homepagev2.convertCamelCase(episodeTitle1);
						test.log(LogStatus.INFO, "clicked on first episode"+episodeTitle1);
					System.out.println(episodeTitle1);
					watchpagev2.cardSecodinwatchcarousel.click();
					
					homepagev2.verifyAndProgressBar();
					Thread.sleep(20000);
					driver.navigate().back();
			
					
					}catch(Exception e)
					{
						
					}
					 
				    }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
				 
				 
				// play same Episode again 
				
				//Click on Watch tab 						 
				 homepagev2.tabClick("Watch");	
				 Thread.sleep(5000);

					  //click on card in watch carousel 1
					
							 
							 
						if (Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
						{
					
						try 
						{
						
						watchpagev2.cardinwatchcarousel.click();
						
						homepagev2.verifyAndProgressBar();
						Thread.sleep(10000);
						driver.navigate().back();
				
						
						}catch(Exception e)
						{
							
						}
						 
					    }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
				
				    // click on Watch tab 
						
						
						homepagev2.tabClick("Watch");
						Thread.sleep(2000);
						
						 homepagev2.tabClick("My Stuff");
						 Thread.sleep(2000);
						 
						 Utilities.verticalSwipe(driver);
						 Utilities.verticalSwipe(driver);
				
						 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
							 
							 homepagev2.lastViewedTitle.click();
							 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
						 }else test.log(LogStatus.FAIL, "Not click on Last Viewed Tary Title / not found");
				
						 String lastviewedCardTitle = "";
						 
				         try {
				        	 lastviewedCardTitle =  homepagev2.Content.getText().toString().trim();
				        	 lastviewedCardTitle=homepagev2.convertCamelCase(lastviewedCardTitle);
				        	 
				        	 test.log(LogStatus.INFO, "The Lastviewed content card title is :" + lastviewedCardTitle);
				        	 
				        	 
						} catch (Exception e) {
							// TODO: handle exception
						}
				
				        if(episodeTitle.equals(lastviewedCardTitle)) {
				        	
				        	if(!Utilities.setResultsKids(tc4, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify if the Episode cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1901");
							 
							 driver.navigate().back();
							 
				        	
				        }else {
				        	
				        	 if(!Utilities.setResultsKids(tc4, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1901");
							 BasePageV2.takeScreenshot();
							 driver.navigate().back();
							 
				        }
				 
				        //clear Last viewed Tray
				        
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
							
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
							
							
						}
						else {
							test.log(LogStatus.INFO, "Clear link is not clickable");
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
							Utilities.verticalSwipeDown(driver);
						}
				 
				
				 //  Verify if the Audio cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1902
				   	 
				   	 
				   	 
				  
					 
					 HomePageV2.tabClick("Listen");
						
						try {
							
						
						String carousalAudioCardTitle="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
						 
						String temp=driver.findElement(By.xpath(carousalAudioCardTitle)).getAttribute("text");
						 AudiocardName=homepagev2.convertCamelCase(temp);
						 
						 test.log(LogStatus.INFO, "The Audio card Title is  : " + AudiocardName);
						 
						 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
						 
						 Utilities.verticalSwipeDown(driver);
						 
						 if(Utilities.explicitWaitClickable(driver,listenpageV2.play_btn , 30)) {
							 listenpageV2.play_btn.click();
							 test.log(LogStatus.INFO, "clicked on play button");
							 Thread.sleep(20000);
					 
							if(Utilities.explicitWaitClickable(driver, listenpageV2.playerCloseButton, 20)) {
								listenpageV2.playerCloseButton.click();
								driver.navigate().back();
								
							}else test.log(LogStatus.FAIL, "Not click on Close button in player / Not Found");
			
						 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
						 
						} catch (Exception e) {
							e.printStackTrace();
							test.log(LogStatus.FAIL, e.getMessage());
						}
				   	 
						String AudiocardName1 = "";
						 
						 HomePageV2.tabClick("Listen");
						 Thread.sleep(2000);
						 Utilities.horizontalSwipe(driver);
							
							try {
								
							
							String carousalAudioCardTitle="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
							 
							String temp=driver.findElement(By.xpath(carousalAudioCardTitle)).getAttribute("text");
							AudiocardName1=homepagev2.convertCamelCase(temp);
							 
							 test.log(LogStatus.INFO, "The Audio card Title is  : " + AudiocardName1);
							 
							 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
							 
							 Utilities.verticalSwipeDown(driver);
							 
							 if(Utilities.explicitWaitClickable(driver,listenpageV2.play_btn , 30)) {
								 listenpageV2.play_btn.click();
								 test.log(LogStatus.INFO, "clicked on play button");
								 Thread.sleep(20000);
						 
								if(Utilities.explicitWaitClickable(driver, listenpageV2.playerCloseButton, 20)) {
									listenpageV2.playerCloseButton.click();
									driver.navigate().back();
									
								}else test.log(LogStatus.FAIL, "Not click on Close button in player / Not Found");
				
							 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
							 
							} catch (Exception e) {
								e.printStackTrace();
								test.log(LogStatus.FAIL, e.getMessage());
							}
				   	 
				   	  // play again Same previous Content
							HomePageV2.tabClick("Read");
							Thread.sleep(2000);
							HomePageV2.tabClick("Listen");
							
							try {
								
							
							String carousalAudioCardTitle="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
							 
							String temp=driver.findElement(By.xpath(carousalAudioCardTitle)).getAttribute("text");
							 AudiocardName=homepagev2.convertCamelCase(temp);
							 
							 test.log(LogStatus.INFO, "The Audio card Title is  : " + AudiocardName);
							 
							 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
							 
							 Utilities.verticalSwipeDown(driver);
							 
							 if(Utilities.explicitWaitClickable(driver,listenpageV2.play_btn , 30)) {
								 listenpageV2.play_btn.click();
								 test.log(LogStatus.INFO, "clicked on play button");
								 Thread.sleep(20000);
						 
								if(Utilities.explicitWaitClickable(driver, listenpageV2.playerCloseButton, 20)) {
									listenpageV2.playerCloseButton.click();
									driver.navigate().back();
									
								}else test.log(LogStatus.FAIL, "Not click on Close button in player / Not Found");
				
							 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
							 
							} catch (Exception e) {
								e.printStackTrace();
								test.log(LogStatus.FAIL, e.getMessage());
							}
				   	 
				   	    //  navigate to LastViewed Tray
							
							HomePageV2.tabClick("Listen");
							
							HomePageV2.tabClick("My Stuff");
				   	        Thread.sleep(2000);
				   	 
				   	        Utilities.verticalSwipe(driver);
				   	        Utilities.verticalSwipe(driver);
				   	        
		                if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
							 
							 homepagev2.lastViewedTitle.click();
							 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
						 }else test.log(LogStatus.FAIL, "Not click on Last Viewed Tary Title / not found");
				
						 String lastviewedAudioCardTitle = "";
						 
				         try {
				        	 lastviewedAudioCardTitle =  homepagev2.Content.getText().toString().trim();
				        	 lastviewedAudioCardTitle=homepagev2.convertCamelCase(lastviewedAudioCardTitle);
				        	 
				        	 test.log(LogStatus.INFO, "The Lastviewed content card title is :" + lastviewedAudioCardTitle);
				        	 
				        	 
						} catch (Exception e) {
							// TODO: handle exception
						}
				
				        if(AudiocardName.equals(lastviewedAudioCardTitle)) {
				        	
				        	if(!Utilities.setResultsKids(tc5, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify if the Audio cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1902");
							 
							 driver.navigate().back();
							 
				        	 Utilities.verticalSwipeDown(driver);
				        	 Utilities.verticalSwipeDown(driver);
				        	 Utilities.verticalSwipeDown(driver);
				        }else {
				        	
				        	 if(!Utilities.setResultsKids(tc5, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify if the Audio cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1902");
							 BasePageV2.takeScreenshot();
							 driver.navigate().back();
							 
							 Utilities.verticalSwipeDown(driver);
							 Utilities.verticalSwipeDown(driver);
							 Utilities.verticalSwipeDown(driver);
				        }
				   	 
				   	 
				   	 // Verify if the Ebook cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1903
				 
				    
				 homepagev2.tabClick("Read");
				 Thread.sleep(2000);
				 
				
				 if(Utilities.explicitWaitVisible(driver, homepagev2.bookFirstcarosal, 10))
				 {
					 try {
						 bookName= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
						 bookName=homepagev2.convertCamelCase(bookName);
							
						homepagev2.bookFirstcarosal.click();
						
						test.log(LogStatus.INFO, "Clicked on the Book " + bookName + " from Read Carousal");
						
						if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
							
							bookspagev2.bookDetailPageBookPreviewButton.click();
							Thread.sleep(10000);
				
							driver.navigate().back();
							
							driver.navigate().back();
				
							
						}else {
							test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
							
						}
						
		 
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				 }else test.log(LogStatus.FAIL, "Not click on First book on Read book carosal / Not found");		 
				   	 
				   	 
				  // click on Read Tab carosal Second card
				 
				 homepagev2.tabClick("Read");
				 Thread.sleep(2000);
				 
				 Utilities.horizontalSwipe(driver);
				 
				WebElement  cardName=driver.findElementByXPath("//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']");
				String secondBookCard = cardName.getText().toString().trim();
				System.out.println("the Second book is :" + secondBookCard);
				cardName.click();
				 
				if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
					
					bookspagev2.bookDetailPageBookPreviewButton.click();
					Thread.sleep(10000);
		
					driver.navigate().back();
					
					driver.navigate().back();
		
					
				}else {
					test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
					
				}
				 
				// play again previous same content again 
				 
				homepagev2.tabClick("Listen");
				Thread.sleep(4000);
				
				homepagev2.tabClick("Read");
				Thread.sleep(2000);
				
				 if(Utilities.explicitWaitVisible(driver, homepagev2.bookFirstcarosal, 10))
				 {
					 try {
						 bookName= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
						 bookName=homepagev2.convertCamelCase(bookName);
							
						homepagev2.bookFirstcarosal.click();
						
						test.log(LogStatus.INFO, "Clicked on the Book " + bookName + " from Read Carousal");
						
						if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
							
							bookspagev2.bookDetailPageBookPreviewButton.click();
							Thread.sleep(10000);
				
							driver.navigate().back();
							
							driver.navigate().back();
				
							
						}else {
							test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
							
						}
						
		 
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				 }else test.log(LogStatus.FAIL, "Not click on First book on Read book carosal / Not found");	
				
				
				 homepagev2.tabClick("Read");
					Thread.sleep(2000);
				
					HomePageV2.tabClick("My Stuff");
		   	        Thread.sleep(2000);
		   	 
		   	        Utilities.verticalSwipe(driver);
		   	        Utilities.verticalSwipe(driver);
		   	        
                if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
					 
					 homepagev2.lastViewedTitle.click();
					 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
				 }else test.log(LogStatus.FAIL, "Not click on Last Viewed Tary Title / not found");
		
				 String lastviewedBookCardTitle = "";
				 
		         try {
		        	 lastviewedBookCardTitle =  homepagev2.Content.getText().toString().trim();
		        	 lastviewedBookCardTitle=homepagev2.convertCamelCase(lastviewedBookCardTitle);
		        	 
		        	 test.log(LogStatus.INFO, "The Lastviewed content card title is :" + lastviewedBookCardTitle);
		        	 
		        	 
				} catch (Exception e) {
					// TODO: handle exception
				}
		
		        if(bookName.equals(lastviewedBookCardTitle)) {
		        	
		        	if(!Utilities.setResultsKids(tc6, pass))
					      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.PASS, "Verify if the Ebook cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1903");
					 
					 driver.navigate().back();
					 
		        	 Utilities.verticalSwipeDown(driver);
		        	 
		        }else {
		        	
		        	 if(!Utilities.setResultsKids(tc6, fail))
					      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.FAIL, "Verify if the Ebook cards are added to the 1st position Last viewed tray if user plays the same content again from LV - VK_1903");
					 BasePageV2.takeScreenshot();
					 driver.navigate().back();
					 
					 Utilities.verticalSwipeDown(driver);
					 
		        }
				
			
		      
		        
				 // clear Last viewed tray
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
						
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						
						
					}
					else {
						test.log(LogStatus.INFO, "Clear link is not clickable");
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
						Utilities.verticalSwipeDown(driver);
					}
				 
				 
			  	 
		        
		//Verify the settings changed are retained for Last viewed Ebook card post changing the settings to other than default - VK_1899
		        
		        
				 homepagev2.tabClick("Read");
				 Thread.sleep(2000);
				 
				 
				 if(Utilities.explicitWaitVisible(driver, homepagev2.bookFirstcarosal, 10))
				 {
					 try {
						 bookName= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
						 bookName=homepagev2.convertCamelCase(bookName);
							
						homepagev2.bookFirstcarosal.click();
						
						test.log(LogStatus.INFO, "Clicked on the Book " + bookName + " from Read Carousal");
						
						if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
							
							bookspagev2.bookDetailPageBookPreviewButton.click();
							Thread.sleep(15000);
							
							Utilities.tap(driver);
							Utilities.tap(driver);
							
							
							Thread.sleep(2000);
							
							driver.runAppInBackground(Duration.ofSeconds(3));
							 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							 driver.currentActivity();
						
							if(Utilities.explicitWaitClickable(driver, readpagev2.readerBookSettingsICon, 20)) {
								readpagev2.readerBookSettingsICon.click();
								if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.bookReaderOptionMagnify, 10)) {
									 defaultmagnify =  kidsPlayerPageV2.MagnifySubTitle.getText().toString().trim();
									System.out.println("The Default Magnify is : " + defaultmagnify);
									kidsPlayerPageV2.bookReaderOptionMagnify.click();
									test.log(LogStatus.INFO, "clicked on book Option Magnify ");
									
									if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.bookReaderOptionDicionary, 10)) {
										 defaultDictionary = kidsPlayerPageV2.DictionarySubTitle.getText().toString().trim();
										System.out.println("The default Dictionary is : " + defaultDictionary);
										kidsPlayerPageV2.bookReaderOptionDicionary.click();
										test.log(LogStatus.INFO, "clicked on book Option Dictionary ");
									
										if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.bookReaderOptionAutoPageTurn, 10)) {
											 defaultAutoPageturn = kidsPlayerPageV2.AutoPageTurnSubTitle.getText().toString().trim();
											System.out.println("The Default Auto page turn is  : " + defaultAutoPageturn);
											kidsPlayerPageV2.bookReaderOptionAutoPageTurn.click();
											test.log(LogStatus.INFO, "clicked on book Option Auto Page turn ");
											
											if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.bookReaderCloseButton, 10)) {
												kidsPlayerPageV2.bookReaderCloseButton.click();
												
												driver.navigate().back();
												driver.navigate().back();
												driver.navigate().back();
												
												homepagev2.tabClick("Read");
												
												 homepagev2.tabClick("My Stuff");
												 Thread.sleep(2000);
												 
												 Utilities.verticalSwipe(driver);
												 Utilities.verticalSwipe(driver);
												 
										 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
													 
													 homepagev2.lastViewedTitle.click();
													 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
													 homepagev2.lastupdatedintrayb.click();
		 
													 if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 20)) {
															
															bookspagev2.bookDetailPageBookPreviewButton.click();
															Thread.sleep(15000);
															
															Utilities.tap(driver);
															Utilities.tap(driver);
															
															Thread.sleep(5000);
															
															 driver.runAppInBackground(Duration.ofSeconds(3));
															 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
															 driver.currentActivity();
															
															if(Utilities.explicitWaitClickable(driver, readpagev2.readerBookSettingsICon, 20)) {
																readpagev2.readerBookSettingsICon.click();
																
																LastviewdTraydefaultmagnify =  kidsPlayerPageV2.MagnifySubTitle.getText().toString().trim();
																System.out.println("The Default Magnify is in Last Viewed Tray : " + LastviewdTraydefaultmagnify);
																
																test.log(LogStatus.INFO, "The Default Magnify is in Last Viewed Tray : " + LastviewdTraydefaultmagnify);
																
																LastviewdTraydefaultDictionary = kidsPlayerPageV2.DictionarySubTitle.getText().toString().trim();
																 System.out.println("The default Dictionary is in LastViewed tary : " + LastviewdTraydefaultDictionary);
																 test.log(LogStatus.INFO, "The default Dictionary is in LastViewed tary : " + LastviewdTraydefaultDictionary);
																
																 LastviewdTraydefaultAutoPageturn = kidsPlayerPageV2.AutoPageTurnSubTitle.getText().toString().trim();
																 System.out.println("The Default Auto page turn is  : " + LastviewdTraydefaultAutoPageturn);
																 test.log(LogStatus.INFO, "The Default Auto page turn is  : " + LastviewdTraydefaultAutoPageturn);
																 
																 if(defaultmagnify.equals(LastviewdTraydefaultDictionary)) {
																	 if(defaultDictionary.equals(LastviewdTraydefaultDictionary)) {
																		 if(defaultAutoPageturn.equals(LastviewdTraydefaultAutoPageturn)) {
															
																		 }else {
																			 VK_1899++;
																			 test.log(LogStatus.FAIL, "Failed default Auto Page Turn option " + LastviewdTraydefaultAutoPageturn);
																		 }
																		 
																	 }else {
																		 VK_1899++;
																		 test.log(LogStatus.FAIL, "Failed default Dictionary option " + LastviewdTraydefaultDictionary);
																	 }
																	 
																 }else {
																	 VK_1899++;
																	 test.log(LogStatus.FAIL, "Failed default Magnify option " + LastviewdTraydefaultmagnify);
																 }
																 
																 
																 if(VK_1899 == 0) {
																	 
																	 if(!Utilities.setResultsKids(tc7, pass))
																	      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
																	 test.log(LogStatus.PASS, "Verify the settings changed are retained for Last viewed Ebook card post changing the settings to other than default - VK_1899");
																	 
																 }else {
																	 if(!Utilities.setResultsKids(tc7, fail))
																	      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
																	 test.log(LogStatus.FAIL, "Verify the settings changed are retained for Last viewed Ebook card post changing the settings to other than default - VK_1899");
																	 BasePageV2.takeScreenshot();
																	 
																 }
																 
												
																 
																 
															}else test.log(LogStatus.FAIL, "Not able to click on Book reader Settings Icon");
															

													 
													 }else test.log(LogStatus.FAIL, "Not clicked on Preview button");
											
													 
													 
												 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
								
												
											}else test.log(LogStatus.FAIL, "not click on close button in book reader screen");
									
									}else BasePageV2.reportFail("Not able to click on book Dictionary option");
								}else BasePageV2.reportFail("Not able to click on book magnify option");
								
								
							}else test.log(LogStatus.FAIL, "Not able to click on Book reader settings button");
							
							
						}else test.log(LogStatus.FAIL, "Not click on Book Reader Settings Button");
			
						
						}else {
							test.log(LogStatus.FAIL, "Not able to click on Preview button / not found");
							
						}
						
						
								 
					} catch (Exception e) {
						// TODO: handle exception
					}
				 
				 }else test.log(LogStatus.FAIL, "Not able to click on first book in carosal ");
				 
				 
				 
				 
				 
				 
		        
		        
		        
		        
				
				   	 
				 
				 
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
