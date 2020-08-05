package com.viacom.settings;

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
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

public class MovieCardIntegration_P2 extends BaseTestV2 {
	
String testName = "MovieCardIntegration_P2";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("MovieCardIntegration_P2");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_2118 - Verify the navigation by tapping on Movie cards from Recent Activity section
		// VK_2194 - Verify the functionality when tapping on show/charater image from show progarmme info:
		
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		WatchPageV2 watchPage = new WatchPageV2(driver, test);
		DownloadsPageV2 downloads = new DownloadsPageV2(driver, test);
		ListenPageV2 listenPage = new ListenPageV2(driver, test);
		KidsPlayerPageV2 player = new KidsPlayerPageV2(driver, test);
		ListenPageV2 listen = new ListenPageV2(driver, test);
		BooksPageV2 book = new BooksPageV2(driver, test);
		ReadPageV2 read = new ReadPageV2(driver, test);
		
		String userName = data.get("Email");
		String password = data.get("Password");

		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		  //VK_2118 - Verify the navigation by tapping on Movie cards from Recent Activity section
		  //VK_2194 - Verify the functionality when tapping on show/charater image from show progarmme info:
		  //VK_2195 - Verify the functionality when tapping on audio book image from audio progarmme info:
		  //VK_2196 - Verify the functionality when tapping on book image from book progarmme info: 
		
		
		
		
		// click on Watch tab
				homepagev2.tabClick("Watch");
				
				// scroll till movies tray
				try {
					 Utilities.verticalSwipe(driver,watchPage.watchMoviesTrayTitle,"visible",80);
					 Utilities.verticalSwipe(driver);
					 Utilities.verticalSwipe(driver);
				} catch (Exception e) {
					// TODO: handle exception
				}
			
				 
				
				 String  movieCardTile = "";
				 if(Utilities.explicitWaitClickable(driver, watchPage.movieFirstCard, 10)) {
					 watchPage.movieFirstCard.click();
					 
					   movieCardTile = settingsPageV2.movieTile.getText().toString().trim();
						test.log(LogStatus.INFO, "The Movie First card title is : " + movieCardTile);
					 
					 if(Utilities.explicitWaitClickable(driver, listenPage.audioPlayBtn, 50)) {
			    		 listenPage.audioPlayBtn.click();
			  
			    		 test.log(LogStatus.INFO, "Click on play button");
			    		 Thread.sleep(20000);
			    		 driver.pressKeyCode(AndroidKeyCode.BACK);
			    	 }else {
			    		 test.log(LogStatus.FAIL, "Failed to click on play button");
			    		 BasePageV2.takeScreenshot();
			    	 }
					 
					 driver.pressKeyCode(AndroidKeyCode.BACK);
				 }else {
				     BasePageV2.reportFail("Failed to click on Watch Movies tray in watch page");
				 }
				
				 for(int i = 0 ; i < 10 ; i ++) {
					 Utilities.verticalSwipeReverse(driver);
				 }
				 

				//  Navigate to Parent zone page
				 
				if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

				}else BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
			
					
					settingsPageV2.putBackGroundApp();
					
					// Click on ParentZone Button in Switch Profile Screen parentZoneButton
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 50)) {
						settingsPageV2.parentZoneButton.click();
						test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 50)) {
							Thread.sleep(1000);
							settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
							Thread.sleep(20000);
						}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
					}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");

					 driver.runAppInBackground(Duration.ofSeconds(3));
					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					 driver.currentActivity();
					
				  // Scroll to recent Activity Tab under Parent zone screen
					 Utilities.verticalSwipe(driver,settingsPageV2.parentZoneRecentActivity1,"visible",80);
				     Utilities.verticalSwipe(driver);
		
				     try {
							driver.findElement(By.xpath("//android.widget.TextView[@text='"+movieCardTile+"']")).click();
						} catch (Exception e) {
							e.printStackTrace();
						}
		
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneRecentActivity1, 50)) {
		            	test.log(LogStatus.PASS, "Verify the navigation by tapping on Movie cards from Recent Activity section");
						if(!Utilities.setResultsKids("VK_2118", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					} else {
						test.log(LogStatus.FAIL, "Verify the navigation by tapping on Movie cards from Recent Activity section");
						BasePageV2.takeScreenshot();
						if(!Utilities.setResultsKids("VK_2118", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
		            
		
		            
		            driver.pressKeyCode(AndroidKeyCode.BACK);
		            driver.pressKeyCode(AndroidKeyCode.BACK);
		            
		            
		
	// VK_2194 - Verify the functionality when tapping on show/charater image from show progarmme info:
		            String showtile = "", playerVideoTitle = "";
		            homepagev2.tabClick("Watch");
		            Utilities.verticalSwipe(driver, watchPage.topToonsTray, "visible", 30);
		            Utilities.verticalSwipe(driver);
		            if(Utilities.explicitWaitClickable(driver, watchPage.topToonsTrayFirstCard, 50)) {
		            	watchPage.topToonsTrayFirstCard.click();
		            	test.log(LogStatus.INFO, "Click on show tray first card");
		            	Utilities.verticalSwipe(driver, watchPage.episodesTrayFirstCard, "visible", 30);
			            Utilities.verticalSwipe(driver);
			            try {
			            	 showtile =  watchPage.episodesTrayFirstCardTitle.getText().toString().trim();
				             test.log(LogStatus.INFO, "The Episode First card title is : " + showtile);
				             Utilities.verticalSwipeReverse(driver);
				             Utilities.verticalSwipeReverse(driver);
				             Utilities.verticalSwipeReverse(driver);
				             
						} catch (Exception e) {
							e.printStackTrace();
						}
			            
			           
			            // click on Play Button
			            if(Utilities.explicitWaitClickable(driver, watchPage.playBtn, 20)) {
			            	watchPage.playBtn.click();
			            	test.log(LogStatus.INFO, "click on Paly button");
			            	Thread.sleep(20000);
			            	try {
			            		player.pauseVideo();
			            		playerVideoTitle = player.playerSubTitle.getText().toString().trim();
			            		test.log(LogStatus.INFO, "Episode card Tile from Video player : " +playerVideoTitle);
			            		
			            		 if(showtile.equals(playerVideoTitle)) {
			 		            	test.log(LogStatus.PASS, "Verify the functionality when tapping on show/charater image from show progarmme info:");
			 						if(!Utilities.setResultsKids("VK_2194", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 					} else {
			 						test.log(LogStatus.FAIL, "Verify the functionality when tapping on show/charater image from show progarmme info:");
			 						BasePageV2.takeScreenshot();
			 						if(!Utilities.setResultsKids("VK_2194", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 					}
			            		
			            		driver.pressKeyCode(AndroidKeyCode.BACK);
			            		driver.pressKeyCode(AndroidKeyCode.BACK);
			            		
							} catch (Exception e) {
								// TODO: handle exception
							}
			            	
			            }else {
			            	test.log(LogStatus.FAIL, "Failed to click on Play Button / Not displayed");
			            	BasePageV2.takeScreenshot();
			            	driver.pressKeyCode(AndroidKeyCode.BACK);
			            }
			             
			            
		            }else {
		            	test.log(LogStatus.FAIL, "Failed to click on show first card ");
		            	BasePageV2.takeScreenshot();
		            }
		            
		           
		            
		            
		            // VK_2195 - Verify the functionality when tapping on audio book image from audio progarmme info:
                    String audioCardTile = "",audioPlayertile="";
                    Utilities.verticalSwipeDown(driver);
		            homepagev2.tabClick("Listen");
		            try {
		            	//click on carousal card in Listen Screen
			            driver.findElement(By.id("com.viacom18.vootkids:id/container_inner_carousel")).click();
				    	test.log(LogStatus.INFO, "Click on Carousal in Listen Page");
				    	
				    	audioCardTile = listen.audioDetailsAudioName.getText().toString().trim();
				    	test.log(LogStatus.INFO, "Audio card title : " + audioCardTile);
				    	if(Utilities.explicitWaitClickable(driver, watchPage.playBtn, 30)) {
				    		watchPage.playBtn.click();
				    		test.log(LogStatus.INFO, "click on play button in Audio card");
				    		Thread.sleep(10000);
				    		try {
				    			audioPlayertile = player.audioPlayerTitle.getText().toString().trim();
				    			test.log(LogStatus.INFO, "The Audio title from player : " + audioPlayertile);
				    			
				    	         driver.pressKeyCode(AndroidKeyCode.BACK);
				    	         driver.pressKeyCode(AndroidKeyCode.BACK);
				    	
				    			if(audioCardTile.equals(audioPlayertile)) {
				    				test.log(LogStatus.PASS, "Verify the functionality when tapping on audio book image from audio progarmme info:");
			 						if(!Utilities.setResultsKids("VK_2195", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 					} else {
			 						test.log(LogStatus.FAIL, "Verify the functionality when tapping on audio book image from audio progarmme info:");
			 						BasePageV2.takeScreenshot();
			 						if(!Utilities.setResultsKids("VK_2195", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 					}
				    
							} catch (Exception e) {
								// TODO: handle exception
							}
				   
				    	}else {
				    		test.log(LogStatus.FAIL, "Failed to click on play button in Audio card");
				    		BasePageV2.takeScreenshot();
				    	}
				    	
					} catch (Exception e) {
						e.printStackTrace();
					}
		            
		            
		           // VK_2196 - Verify the functionality when tapping on book image from book progarmme info: 
		
		           // click on read tab
		            String booktitle = "",readerBookTitle="";
		            homepagev2.tabClick("Read");
		            try {
						
		            	//click on carousal card in Listen Screen
			            driver.findElement(By.id("com.viacom18.vootkids:id/container_inner_carousel")).click();
				    	test.log(LogStatus.INFO, "Click on Carousal in Listen Page");
				    	Thread.sleep(10000);
				    	booktitle = book.bookDetailPageBookTitle.getText().toString().trim();
				    	test.log(LogStatus.INFO, "the Book tile is : " + booktitle);
		            	if(Utilities.explicitWaitClickable(driver, book.bookTryButton, 30)) {
		            		book.bookTryButton.click();
		            		test.log(LogStatus.INFO, "click on book Try button in book details page");
		            		Thread.sleep(20000);
		            		try {
		            			read.dismissReaderCoachCards();
							} catch (Exception e) {
								
							}
		            		
		            		Utilities.tap(driver);
	            			Utilities.tap(driver);
	            			
		            		try {
								
		            			 readerBookTitle =player.bookReaderBookTitle.getText().toString().trim();
		            			test.log(LogStatus.INFO, "book reader title is : " + readerBookTitle);
		            			
		            			driver.pressKeyCode(AndroidKeyCode.BACK);
		            			driver.pressKeyCode(AndroidKeyCode.BACK);
		            			driver.pressKeyCode(AndroidKeyCode.BACK);
		            			
		            			if(booktitle.equals(readerBookTitle)) {
		            				test.log(LogStatus.PASS, "Verify the functionality when tapping on book image from book progarmme info:");
			 						if(!Utilities.setResultsKids("VK_2196", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 					} else {
			 						test.log(LogStatus.FAIL, "Verify the functionality when tapping on book image from book progarmme info:");
			 						BasePageV2.takeScreenshot();
			 						if(!Utilities.setResultsKids("VK_2196", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 					}
		            			
		            			
							} catch (Exception e) {
								// TODO: handle exception
							}
		            		
		            	}else {
		            		test.log(LogStatus.FAIL, "Failed to click on book Try button / Not displayed");
		            		BasePageV2.takeScreenshot();
		            	}
		            	
					} catch (Exception e) {
						// TODO: handle exception
					}
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		
	
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	

}
