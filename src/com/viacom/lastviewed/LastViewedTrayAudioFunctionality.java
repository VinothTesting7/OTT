package com.viacom.lastviewed;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

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
public class LastViewedTrayAudioFunctionality extends BaseTestV2{
	

	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "LastViewedTrayAudioFunctionality";
	
	String tc1 = "VK_1663";
	String tc2 = "VK_1664";
	String tc3 = "VK_1665";
	String tc4 = "VK_1666";
	
	String audio = "";
	String AudiocardName = "";
	int currentDuration = 0 , lastViewedAudioDuration = 0;
	
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("LastViewedTrayAudioFunctionality");
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
				 
				 
				//Verify if the Audiobook cards are added to the Last viewed tray if user launches and closes the audio player - VK_1663
				 
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
					
					//click Audio tab and play Audio card
					
					
					 
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
							 Thread.sleep(2000);
							 
							 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
								 kidsPlayerPageV2.audioPlayerCloseButton.click();
								 driver.navigate().back();
								 
								 homepagev2.tabClick("My Stuff");
								 
								 Utilities.verticalSwipe(driver);
								
								 Utilities.verticalSwipe(driver);
								 
								 
								 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
									 
									 homepagev2.lastViewedTitle.click();
									 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
									 
									 try {
										  audio = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")).getText().toString().trim();
										 test.log(LogStatus.INFO, "Audio card present in Last Viewed tray is : " + audio );
										  if(AudiocardName.equals(audio)) {
											  if(!Utilities.setResultsKids(tc1, pass))
											      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											 test.log(LogStatus.PASS, "Verify if the Audiobook cards are added to the Last viewed tray if user launches and closes the audio player - VK_1663");
											 
											 driver.navigate().back();
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
											 
											 if(!Utilities.setResultsKids(tc1, fail))
											      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											 test.log(LogStatus.FAIL, "Verify if the Audiobook cards are added to the Last viewed tray if user launches and closes the audio player - VK_1663");
											 BasePageV2.takeScreenshot();
											 
											 driver.navigate().back();
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
										  
						
					               
										 } catch (Exception e) {
						       					e.printStackTrace();
						       				}
									 
									 
									 
								 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
								 
				
							 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");

							
						 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
						 
						} catch (Exception e) {
							e.printStackTrace();
							test.log(LogStatus.FAIL, e.getMessage());
						}
						
					//	Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio partially - VK_1664
						
						
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
								 Thread.sleep(40000);
								 
								 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
									 kidsPlayerPageV2.audioPlayerCloseButton.click();
									 driver.navigate().back();
									 
									 homepagev2.tabClick("My Stuff");
									 
									 Utilities.verticalSwipe(driver);
									
									 Utilities.verticalSwipe(driver);
									 
									 
									 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
										 
										 homepagev2.lastViewedTitle.click();
										 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
										 
										 try {
											  audio = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")).getText().toString().trim();
											 test.log(LogStatus.INFO, "Audio card present in Last Viewed tray is : " + audio );
											  if(AudiocardName.equals(audio)) {
												  if(!Utilities.setResultsKids(tc2, pass))
												      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												 test.log(LogStatus.PASS, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio partially - VK_1664");
												 
												 driver.navigate().back();
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
												 
												 if(!Utilities.setResultsKids(tc2, fail))
												      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												 test.log(LogStatus.FAIL, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio partially - VK_1664");
												 BasePageV2.takeScreenshot();
												 
												 driver.navigate().back();
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
											  
							
						               
											 } catch (Exception e) {
							       					e.printStackTrace();
							       				}
										 
										 
										 
									 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
									 
					
								 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");

								
							 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
							 
							} catch (Exception e) {
								e.printStackTrace();
								test.log(LogStatus.FAIL, e.getMessage());
							}
					
					
					//Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio Completely - VK_1665
							
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
								 
								 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
								 
								 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
									 kidsPlayerPageV2.audioPlayerCloseButton.click();
									 driver.navigate().back();
									 
									 homepagev2.tabClick("My Stuff");
									 
									 Utilities.verticalSwipe(driver);
									
									 Utilities.verticalSwipe(driver);
									 
									 
									 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
										 
										 homepagev2.lastViewedTitle.click();
										 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
										 
										 try {
											  audio = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")).getText().toString().trim();
											 test.log(LogStatus.INFO, "Audio card present in Last Viewed tray is : " + audio );
											  if(AudiocardName.equals(audio)) {
												  if(!Utilities.setResultsKids(tc3, pass))
												      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												 test.log(LogStatus.PASS, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio Completely - VK_1665");
												 
												 driver.navigate().back();
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
												 
												 if(!Utilities.setResultsKids(tc3, fail))
												      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												 test.log(LogStatus.FAIL, "Verify if the Audiobook cards are added to the Last viewed tray if user Plays an audio Completely - VK_1665");
												 BasePageV2.takeScreenshot();
												 
												 driver.navigate().back();
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
											  
							
						               
											 } catch (Exception e) {
							       					e.printStackTrace();
							       				}
										 
										 
										 
									 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
									 
					
								 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");

								
							 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
							 
							} catch (Exception e) {
								e.printStackTrace();
								test.log(LogStatus.FAIL, e.getMessage());
							}
					
					
					 // Verify the playback status for partially watched Audiobook content in Last viewed tray: - VK_1666
							
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
											 
											 Thread.sleep(60000);
											 
											if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.playerPlayPauseButton, 20)) {
												kidsPlayerPageV2.playerPlayPauseButton.click();
											
											    String audioPlayerDuraion = kidsPlayerPageV2.playerCurrentDuration.getText().toString().trim();
											    
											    
											    String arr[] = audioPlayerDuraion.split(":");
											     currentDuration = Integer.parseInt(arr[0]);
											    
											    test.log(LogStatus.INFO, "The Audio Player Current Duration is :" + currentDuration);
											    
											 
											 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
												 kidsPlayerPageV2.audioPlayerCloseButton.click();
												 driver.navigate().back();
												 
												
												 
												 homepagev2.tabClick("Read");
												 
												 homepagev2.tabClick("My Stuff");
												 Thread.sleep(2000);
												 
												 Utilities.verticalSwipe(driver);

												 Utilities.verticalSwipe(driver);
												 
												 
												 if(Utilities.explicitWaitClickable(driver, homepagev2.lastViewedTitle, 20)) {
													 
													 homepagev2.lastViewedTitle.click();
													 test.log(LogStatus.INFO, "clicked on Last viewed tray Title ");
													 
													 
													 try {
														 driver.runAppInBackground(Duration.ofSeconds(3));
														 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
														 driver.currentActivity();
														 
														 Thread.sleep(5000);
														 
														 WebElement element = driver.findElement(By.xpath("//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/rewards_tray_parent_container']"));
														 element.click();
														 
														 driver.runAppInBackground(Duration.ofSeconds(3));
														 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
														 driver.currentActivity();
														 
														 if(Utilities.explicitWaitClickable(driver,listenpageV2.audioPlayBtn , 30)) {
															 listenpageV2.audioPlayBtn.click();
															 test.log(LogStatus.INFO, "clicked on play button");
														 }else test.log(LogStatus.INFO, "not click Play button");
														 
														 Thread.sleep(20000);
														 
														 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.playerPlayPauseButton, 20)) {
																kidsPlayerPageV2.playerPlayPauseButton.click();
												

														  String LastviewdaudioPlayerDuraion = kidsPlayerPageV2.playerCurrentDuration.getText().toString().trim();
														    
														    
														    String lastViwed[] = LastviewdaudioPlayerDuraion.split(":");
														    
														     lastViewedAudioDuration = Integer.parseInt(lastViwed[0]);
														    
														    test.log(LogStatus.INFO, "The Audio Player lastviewed tary card Duration is :" + lastViewedAudioDuration);
														  
														 
														 
														  if(currentDuration == lastViewedAudioDuration || lastViewedAudioDuration > currentDuration) {
															  if(!Utilities.setResultsKids(tc4, pass))
															      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
															 test.log(LogStatus.PASS, "Verify the playback status for partially watched Audiobook content in Last viewed tray: - VK_1666");
															 
															 
														 }else {
															 
															 if(!Utilities.setResultsKids(tc4, fail))
															      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
															 test.log(LogStatus.FAIL, "Verify the playback status for partially watched Audiobook content in Last viewed tray: - VK_1666");
															 BasePageV2.takeScreenshot();
														 }
												
														 }else test.log(LogStatus.FAIL, "Not click on pause button on Audio player ");
														  
														  
														 } catch (Exception e) {
										       					e.printStackTrace();
										       				}
													 
													 
													 
												 }else test.log(LogStatus.FAIL, "Not able to Click on Last Viewed tray Title");
												 
								
											 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");

											
											}else test.log(LogStatus.FAIL, "Not click on pause button on Audio player ");
										 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
									 	 
														    
										} catch (Exception e) {
											e.printStackTrace();
											test.log(LogStatus.FAIL, e.getMessage());
										}
							
												 			 
												 
					
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

				 

}
