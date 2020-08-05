package com.viacom.lastviewed;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
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

// Author - Suresh 

public class ReadLastviewedWatchedEbookCardPlayback extends BaseTestV2 {
	
	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "ReadLastviewedWatchedEbookCardPlayback";
	
	String tc1 = "VK_1686";
	String tc2 = "VK_1661";
	String tc3 = "VK_1662";
	
	boolean readerDisplayedPreview=false;
	
 String bookname ="";
 int  readpagesofebookinlastviewdtray = 0;
 int LastViwedCardDuration =  0;
	
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("ReadLastviewedWatchedEbookCardPlayback");
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
				 EbooksPageV2 ebookspagev2 = new EbooksPageV2(driver, test);
				 KidsPlayerPageV2 kidsPlayerPageV2 = new KidsPlayerPageV2(driver, test);
				 
				 
		// Verify the playback status for Completely watched Ebook content in Last viewed tray: - VK_1687 
				 
				 
				 
				 
				 downloadsv2.deleteAllDownloads();
				 
				 driver.navigate().back();
				 
				 Utilities.verticalSwipeDown(driver);
				 Utilities.verticalSwipeDown(driver);
				 Utilities.verticalSwipeDown(driver);
				 
				 
					
				 homepagev2.tabClick("Read");
					Thread.sleep(1000);
				 
				 //clear Last read Tray 
				 
				  Utilities.verticalSwipe(driver);
				  
				  if(Utilities.explicitWaitClickable(driver, readpagev2.LastReadClearBtn, 20)) {
					  
					  readpagev2.LastReadClearBtn.click();
					  test.log(LogStatus.INFO, "clicked on Last read Clear Link");
					  if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClearYes, 10)) {
						  
						  homepagev2.recentViewedClearYes.click();
						  Thread.sleep(10000);
						  Utilities.verticalSwipeReverse(driver);
					  }else {
						  test.log(LogStatus.FAIL, "Failed to click on Last read clear pop up Yes Button / Not found");
						  Utilities.verticalSwipeReverse(driver);
					  }

					  
					  
				  }else {
					  
					  Utilities.verticalSwipeReverse(driver);
				  }
				 
				 
				 
				 
				 
				 
				 
				 
					// started  to read ebook completely		
					

					homepagev2.tabClick("Read");
					Thread.sleep(1000);
					Utilities.horizontalSwipe(driver);
					Thread.sleep(1000);
					Utilities.horizontalSwipe(driver);
														   				
					
					// started reading of   Ebook from carousel
					
					if(Utilities.explicitWaitVisible(driver, readpagev2.booknameincarouselb, 10))
					{
						bookname=readpagev2.booknameincarouselb.getAttribute("text");
						bookname=bookname.replaceAll("[^a-zA-Z0-9]","");
						
						
						//click book
						try {
							readpagev2.booknameincarouselb.click();
							test.log(LogStatus.INFO, "Clicked on the Book "+bookname+" from Read Carousal");
						    }catch(Exception e){}	
						
					}else 	test.log(LogStatus.FAIL, "failed to click on e book from carousel");
												
														
								
						
						if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
						{
							 try {
								  readpagev2.previewButton.click();
								  test.log(LogStatus.INFO, "Clicked on PREVIEW button");
									 for(int wait=0;wait<=30;wait++) //for loop 1 starting
									  {
									     try
									      {
											 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
											 //Add 1447
											    if(wait==30)
											    {
												 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
												 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Preview' button is Fail");
												 BasePageV2.takeScreenshot();
												 driver.navigate().back();
												 test.log(LogStatus.INFO, "Navigated back to book details page");
											    }
										 
									       } catch(Exception e)
									       {
											 test.log(LogStatus.INFO, "Loader is not displayed");
											 Set<String> CHS = driver.getContextHandles();								 
											 for(String ch:CHS)
											 {
												if(ch.contains("WEBVIEW"))
												{
													test.log(LogStatus.INFO, "eBook Reader is displayed");
											
													test.log(LogStatus.INFO, "Verify the behavior when tapping on 'Preview' button is Pass");
													readerDisplayedPreview=true;
													break;
												}
											 }  
										   }	
										 if(readerDisplayedPreview==true)
											 break;
										 else
											 Thread.sleep(1000);
									 }//for loop1 ending						 
								  } catch(Exception e)
							      {					
									test.log(LogStatus.FAIL, "Unable to click on PREVIEW button"); 
							      }	
							 
						}//preview if condition is ending						
						
						
						                    Thread.sleep(2000);
						                    Utilities.horizontalSwipe(driver);
						
									      for(int i=1;i<=7;i++)
							              {																 
													 if(Utilities.explicitWaitVisible(driver, ebookspagev2.downloadbuttonb,3))
													 {																											 
																 try {
																	       ebookspagev2.downloadbuttonb.click();
																	       Thread.sleep(60000);
																	       test.log(LogStatus.INFO, "clicked on download button");														   													       							  
																      }catch(Exception e) { test.log(LogStatus.FAIL, " failed to click on downloadbutton");	}												 
														             break;													
													 }
													 else
													 {
														 Utilities.horizontalSwipe(driver);
													     Thread.sleep(3000);															      																      
												     }  												 
							               }	  					  					    
									      
									      try {
						               WebDriverWait w = new WebDriverWait(driver, 300);
										   w.until(ExpectedConditions.invisibilityOf( ebookspagev2.cancelbuttonb));
										   test.log(LogStatus.INFO, "download completed");
										   
					                    }catch(Exception e){test.log(LogStatus.FAIL, " failed to download completetely");}
					                 
					                    
									      
									      driver.runAppInBackground(Duration.ofSeconds(3));
									 	  test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									 	  driver.currentActivity();
									      
					                    //double tapping on reader page
								        Thread.sleep(2000);
									    
									    
					                    Utilities.tap(driver);
					                    Utilities.tap(driver);
					                     
					                   test.log(LogStatus.INFO, "doubled tapped on reader page");
						
					
					                   driver.runAppInBackground(Duration.ofSeconds(3));
									 	  test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									 	  driver.currentActivity();
					                   
							      if(Utilities.explicitWaitVisible(driver, ebookspagev2.ebookseekBar, 10))
								  {
								    	test.log(LogStatus.INFO, "Found book reader Seekbar");
									 try {																																														 											
											String pages=ebookspagev2.pagesfromplayerb.getAttribute("text");
									    	 String arr[]=pages.split(" ");
										   String  numberofpagesread=arr[2].toString().trim();
										   int totalpagesofebook=Integer.parseInt(numberofpagesread);
										     System.out.println(totalpagesofebook);											 
										     test.log(LogStatus.INFO, "totalpages of readbook is"+totalpagesofebook);
										     Utilities.scrubtillend(driver,  ebookspagev2.ebookseekBar);
										     test.log(LogStatus.INFO, "Seekbar Full Scrub completed");
										     Thread.sleep(5000);
												
										   }catch(Exception e)
										   {
											   test.log(LogStatus.INFO, "failed to read ebook completely");	  
										   }												
								 }else BasePageV2.reportFail("Seek bar Not found");										
							  
							       //navigate back to homePage
							      
						    	    driver.navigate().back();
						    	    Thread.sleep(1000);
							        driver.navigate().back();	
							        Thread.sleep(1000);
							        driver.navigate().back();
							        Thread.sleep(1000);												    										
					
							
				// Verifying the Last completely read Ebook Updated r not	under Last read tray
								
					
				
					
					//searching for Read tab
					
						Utilities.verticalSwipeReverse(driver);	
						Utilities.verticalSwipeReverse(driver);	
					
					homepagev2.tabClick("Watch");
					Thread.sleep(1000);
					homepagev2.tabClick("My Stuff"); 	
					
					Thread.sleep(5000);	
					homepagev2.tabClick("Read"); 
					//scroll to  last Read tray	
					
					for(int scroll=0;scroll<=4;scroll++)
					{
					if(Utilities.explicitWaitClickable(driver, readpagev2.LastReadTitle, 1))  
					{							 															  
						      test.log(LogStatus.INFO, "navigated to  lastviewed tray");
						    try {
						    	readpagev2.LastReadTitle.click();  
								   test.log(LogStatus.INFO, "clicked on  lastviewed tray");
							     }catch(Exception e) {}								   
						      break;						 
					}	  
					else
					{ 
						 Utilities.verticalSwipe(driver); 							 
					} 
					}
					
			
						
					//verifying  Completely read ebook updated in Last viewed tray
					
					
					if(Utilities.explicitWaitVisible(driver,homepagev2.lastupdatedintrayb, 10))
					{		 	 
					    String  LastreadTaryBook=readpagev2.LastreadFirstcard.getAttribute("text");
					    LastreadTaryBook= LastreadTaryBook.replaceAll("[^a-zA-Z0-9]","");
					     System.out.println(LastreadTaryBook);
					     test.log(LogStatus.INFO, "lastread completely read ebook  is updated"+ LastreadTaryBook);
					
					     if(LastreadTaryBook.equalsIgnoreCase(bookname))
							 {		  	 			 
					          test.log(LogStatus.INFO, "last completely read ebook is available in  lastviewedtray");
								try {
									
									homepagev2.lastupdatedintrayb.click();
									test.log(LogStatus.INFO, "clicked on last viewed ebook");
									
							        }catch(Exception e)
								    {
							        	test.log(LogStatus.FAIL, " failed to click on last viewed e book");
							        } 
								
								
						    }else	test.log(LogStatus.FAIL, "last completely read ebook os  is not availble in lastviewedtray");								 
						  
						   
					 	
						    if(Utilities.explicitWaitVisible(driver, readpagev2.readButton, 10))
						    {									    	   
						    	   test.log(LogStatus.INFO, "  read button is avaible");
						    	  try 
						    	   {
						    	     readpagev2.readButton.click();
						    		 test.log(LogStatus.INFO, "clicked on read button");
						    		 
						    		//get reader controls	    		
									  Thread.sleep(3000);
									  Utilities.tap(driver);
									  Utilities.tap(driver);
									  
									 test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
									
						    	    }catch(Exception e){test.log(LogStatus.FAIL, " failed to click on read button");}
						    	
						      }else test.log(LogStatus.FAIL, "  read button is not avaible");	
						    
						      driver.runAppInBackground(Duration.ofSeconds(3));
						 	  test.log(LogStatus.INFO, "Put app to background for 3 seconds");
						 	  driver.currentActivity();
						    
						    									
							  if(Utilities.explicitWaitVisible(driver, readpagev2.bookReaderUpArrow, 10))
							  {
									test.log(LogStatus.INFO, "book reader up arrow is  available");
									
									try{																		 																																		
								          String  pages=ebookspagev2.pagesfromplayerb.getAttribute("text");
							               String  arr[]=pages.split(" ");
								           String numberofpagesread=arr[1].toString().trim();
								           readpagesofebookinlastviewdtray=Integer.parseInt(numberofpagesread);
								           System.out.println(" count  of last viewed ebook in last viewed tray is"+readpagesofebookinlastviewdtray);											 
								        						 	
									    }catch(Exception e){}
									    																				
							   }else test.log(LogStatus.FAIL, "book reader up arrow is not available");																			  
					 			    		   		   
					 	   if(readpagesofebookinlastviewdtray==1)
					 	   {					  
								    
								    if(!Utilities.setResultsKids(tc1, "PASS"))
									       test.log(LogStatus.WARNING, "TC ID not found in the tc document");		
								    test.log(LogStatus.PASS, "Verify the playback status for Completely watched Ebook content in Last viewed tray: -VK_1687");
								    driver.navigate().back();
							   }
					 	   else
							   {					 				
								   
								   if(!Utilities.setResultsKids(tc1, "FAIL"))
								       test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								   test.log(LogStatus.FAIL, "Verify the playback status for Completely watched Ebook content in Last viewed tray: -VK_1687");
								   BasePageV2.takeScreenshot();
								   driver.navigate().back();
							   }                             
					
					}else test.log(LogStatus.FAIL, "last completely read read ebook is not updated ");

						 
	   // Verify the playback status for partially watched Ebook content in Last viewed tray: - VK_1686
					
					  Utilities.verticalSwipeReverse(driver);
					  Utilities.verticalSwipeReverse(driver);
					  
					  homepagev2.tabClick("Read");
					  Thread.sleep(2000);
					  
					  homepagev2.tabClick("My Stuff");
					  Thread.sleep(2000);
					
					  Utilities.verticalSwipe(driver);
					  Utilities.verticalSwipe(driver);
					
					  if(Utilities.explicitWaitClickable(driver, readpagev2.LastReadClearBtn, 20)) {
						  
						  readpagev2.LastReadClearBtn.click();
						  test.log(LogStatus.INFO, "clicked on Last read Clear Link");
						  if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClearYes, 10)) {
							  
							  homepagev2.recentViewedClearYes.click();
							  Thread.sleep(10000);
							  Utilities.verticalSwipeReverse(driver);
						  }else {
							  test.log(LogStatus.FAIL, "Failed to click on Last read clear pop up Yes Button / Not found");
							  Utilities.verticalSwipeReverse(driver);
						  }

						  
						  
					  }else {
						  
						  Utilities.verticalSwipeReverse(driver);
						  Utilities.verticalSwipeReverse(driver);
					  }
					  
					  
					// click on read tab 
					  
					// started  to read ebook completely		
						

						homepagev2.tabClick("Watch");
						Thread.sleep(1000);
					
						String episodeTitle = "";	 
						 
						if (Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
						{
					
						try 
						{
							episodeTitle=watchpagev2.EpisodecardTitle.getAttribute("text");	
							episodeTitle=homepagev2.convertCamelCase(episodeTitle);
							test.log(LogStatus.INFO, "clicked on first episode : "+episodeTitle);
							
						System.out.println(episodeTitle);
						watchpagev2.cardinwatchcarousel.click();
						
						homepagev2.verifyAndProgressBar();
						Thread.sleep(10000);
				      
						
						}catch(Exception e)
						{
							
						}
						 
					    }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
						
					  
						 kidsPlayerPageV2.pauseVideo();
						  test.log(LogStatus.INFO, "Verifying the UI of the VOD Player");

						
						  
				  
					  int pauseaft =  0 , totalEpisodeDuration = 0;
					  
						if (Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.videoPlayer, 60))

						{

							if (Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.playerCurrentDuration, 10)) {
								try {
									String timeaftPause = kidsPlayerPageV2.playerCurrentDuration.getText();
									test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen - " + timeaftPause);
									String initpause[] = timeaftPause.split(":");
									String durationpause = initpause[0].toString() + initpause[1].toString();
									pauseaft = Integer.parseInt(durationpause);
									String totalDuration = kidsPlayerPageV2.playerTotalDuration.getText().toString().trim();
									String totalDuration1[] = totalDuration.split(":");
									String totalEpisodeDuratio = totalDuration1[0].toString() + totalDuration1[1].toString();
									test.log(LogStatus.INFO, "After Split the duration is : "+ totalEpisodeDuratio );
									System.out.println("Duration merge: " + totalEpisodeDuratio);
									int totalEpisodeDuration1 = Integer.parseInt(totalEpisodeDuratio);
									totalEpisodeDuration = totalEpisodeDuration1/2;
						
									test.log(LogStatus.INFO, "Total Episode Duration : " + totalEpisodeDuration);
									
									driver.navigate().back();
									Thread.sleep(5000);
									
								} catch (Exception e) {
									// TODO: handle exception
								}

							} else
								BasePageV2.reportFail("Current duration not displayed");
						}
				 
						
						
				        homepagev2.tabClick("Watch");
				        Thread.sleep(5000);
				        homepagev2.tabClick("My Stuff");
				        
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
					
								    driver.runAppInBackground(Duration.ofSeconds(3));
									 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									 driver.currentActivity();
							

									
									 //Pause the video
									  kidsPlayerPageV2.pauseVideo();
									  
									  
									  
									  
										if (Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.videoPlayer, 60))

										{

											if (Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.playerCurrentDuration, 10)) {
												try {
													String timeaftPause = kidsPlayerPageV2.playerCurrentDuration.getText();
													test.log(LogStatus.INFO, "In LastViewed tray Episode card Duration watched is displayed on the Player screen - " + timeaftPause);
													String initpause[] = timeaftPause.split(":");
													String durationpause = initpause[0].toString() + initpause[1].toString();
													LastViwedCardDuration = Integer.parseInt(durationpause);
													
													System.out.println("Duration merge: " + LastViwedCardDuration);
													test.log(LogStatus.INFO, "In LastViewed tray Episode card After Split the duration is : "+ LastViwedCardDuration );
													
													driver.navigate().back();
													Thread.sleep(5000);
													
												} catch (Exception e) {
													// TODO: handle exception
												}

											} else
												BasePageV2.reportFail("Current duration not displayed");
										}
									  
									  
									  
									  
							 }catch (Exception e) {
								// TODO: handle exception
							}
						
				        }else test.log(LogStatus.FAIL, "Last Viewed Title Not found / Not able to click");
						
	
				        if((pauseaft < LastViwedCardDuration) && (LastViwedCardDuration < totalEpisodeDuration)) {
				        	
				        	if(!Utilities.setResultsKids(tc2, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify the playback status for partially watched episode content in Last viewed tray: - VK_1661");
					
							 driver.navigate().back();
							 driver.navigate().back();
							 
							 
						 }else {
							 
							 if(!Utilities.setResultsKids(tc2, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify the playback status for partially watched episode content in Last viewed tray: - VK_1661");
							 BasePageV2.takeScreenshot();
							 
							 driver.navigate().back();
							 driver.navigate().back();
							 
						 }
				        
				        
				        // Verify the playback status for Completely watched episode content: - VK_1662
				        
				        Utilities.verticalSwipe(driver);
				        if(Utilities.explicitWaitClickable(driver, readpagev2.LastReadClearBtn, 20)) {
							  
							  readpagev2.LastReadClearBtn.click();
							  test.log(LogStatus.INFO, "clicked on Last read Clear Link");
							  if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClearYes, 10)) {
								  
								  homepagev2.recentViewedClearYes.click();
								  Thread.sleep(10000);
								  Utilities.verticalSwipeReverse(driver);
								  Utilities.verticalSwipeReverse(driver);
								  Utilities.verticalSwipeReverse(driver);
							  }else {
								  test.log(LogStatus.FAIL, "Failed to click on Last read clear pop up Yes Button / Not found");
								  Utilities.verticalSwipeReverse(driver);
								  Utilities.verticalSwipeReverse(driver);
								  Utilities.verticalSwipeReverse(driver);
							  }

							  
						  }else {
							  
							  Utilities.verticalSwipeReverse(driver);
							  Utilities.verticalSwipeReverse(driver);
							  Utilities.verticalSwipeReverse(driver);
						  }
				        
				        
				     
				        
				        homepagev2.tabClick("Watch");
						Thread.sleep(1000);
					
						String episodeTitle1 = "";	 
						 
						if (Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
						{
					
						try 
						{
							episodeTitle1=watchpagev2.EpisodecardTitle.getAttribute("text");	
							episodeTitle1=homepagev2.convertCamelCase(episodeTitle1);
							test.log(LogStatus.INFO, "clicked on first episode : "+episodeTitle1);
							
						System.out.println(episodeTitle1);
						watchpagev2.cardinwatchcarousel.click();
						
						homepagev2.verifyAndProgressBar();
						Thread.sleep(10000);
				      
						
						}catch(Exception e)
						{
							
						}
						 
					    }else test.log(LogStatus.FAIL, "failed to clicked on card in watch carousel");
						
					  
						 kidsPlayerPageV2.pauseVideo();
						  test.log(LogStatus.INFO, "Verifying the UI of the VOD Player");
						  
						  
						 
						   
                          if(Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 10)) {
                        	  
                        	  Utilities.scrubtillend(driver, homepagev2.audioseekBar);
                        	  
                        	  driver.navigate().back();
                        	  driver.navigate().back();
                        	  
                          }else test.log(LogStatus.FAIL, "Not found Episode SeekBar on player");
						  
				
				        homepagev2.tabClick("Watch");
				        Thread.sleep(5000);
				        homepagev2.tabClick("My Stuff");
				        
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
					
								    driver.runAppInBackground(Duration.ofSeconds(3));
									 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									 driver.currentActivity();
							

									
									 //Pause the video
									  kidsPlayerPageV2.pauseVideo();
									  
									  
									  
									  
										if (Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.videoPlayer, 60))

										{

											if (Utilities.explicitWaitVisible(driver, kidsPlayerPageV2.playerCurrentDuration, 10)) {
												try {
													String timeaftPause = kidsPlayerPageV2.playerCurrentDuration.getText();
													test.log(LogStatus.INFO, "In LastViewed tray Episode card Duration watched is displayed on the Player screen - " + timeaftPause);
													String initpause[] = timeaftPause.split(":");
													String durationpause = initpause[0].toString() + initpause[1].toString();
													LastViwedCardDuration = Integer.parseInt(durationpause);
													
													System.out.println("Duration merge: " + LastViwedCardDuration);
													test.log(LogStatus.INFO, "In LastViewed tray Episode card After Split the duration is : "+ LastViwedCardDuration );
													
													driver.navigate().back();
													Thread.sleep(5000);
													
												} catch (Exception e) {
													// TODO: handle exception
												}

											} else
												BasePageV2.reportFail("Current duration not displayed");
										}
									  
									  
									  
									  
							 }catch (Exception e) {
								// TODO: handle exception
							}
						
				        }else test.log(LogStatus.FAIL, "Last Viewed Title Not found / Not able to click");
						
	
				        if((LastViwedCardDuration < 90) ) {  // Two minutes Lessthan 
				        	
				        	if(!Utilities.setResultsKids(tc3, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify the playback status for Completely watched episode content: - VK_1662");
					
							 driver.navigate().back();
							 driver.navigate().back();
							 
							 
						 }else {
							 
							 if(!Utilities.setResultsKids(tc3, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify the playback status for Completely watched episode content: - VK_1662");
							 BasePageV2.takeScreenshot();
							 
							 driver.navigate().back();
							 driver.navigate().back();
							 
						 }
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				        
				 
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
