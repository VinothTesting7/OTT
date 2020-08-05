package com.viacom.upgrade;

// Author - Suresh 

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import io.appium.java_client.android.AndroidKeyCode;

public class UpGradeValidation1 extends BaseTestV2{

	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "UpGradeValidation1";
	

	
	
	public static String bookName = "";
	public static String AudiocardName = "";
	public static String swicthProName = "";
	
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("UpGradeValidation1");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// Launching the Voot-kids App
		launchAppUpgrade();
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
				 KidsPlayerPageV2 kidsPlayerPageV2 =  new KidsPlayerPageV2(driver, test);

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
						}else test.log(LogStatus.FAIL, " Not able to entered Pin in parent Zone page");
					}else test.log(LogStatus.FAIL, "Not able to click Settings icon");		
							 try {
								 Utilities.verticalSwipe(driver, settingspagev2.settingsHelpSupport);
								 settingspagev2.settingsHelpSupport.click();
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Not able to click Help&support option in Settings page");
								}
							    
							    
							    if(Utilities.explicitWaitVisible(driver, settingspagev2.helpAppVersion, 10)) {
							    	
							    	test.log(LogStatus.INFO, "Navigated to Help Page");
							    	
							    	String appversion = settingspagev2.appVersion.getText().toString().trim();
							    	test.log(LogStatus.INFO, "The New Appilcation Version is : " + appversion);
							    
							    	if(appversion.equals(VootConstants.OLD_BUILD)) {
							    		test.log(LogStatus.INFO, "Installed Latest Version of '"+ appversion +"' Voot kids Application ");
							    	}
							    	
							    	driver.navigate().back();
							    	driver.navigate().back();
							    	driver.navigate().back();
							    	
							    	
							    	
							    }else {
							    	test.log(LogStatus.FAIL, "Not Navigated to Help page");
							    }
				 
							    
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
			
							    
							    driver.runAppInBackground(Duration.ofSeconds(3));
								 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
								 driver.currentActivity();
									
								 // clear the data in Downloads 
									
									downloadsv2.deleteAllDownloads();
									
									if(Utilities.explicitWaitVisible(driver, downloadsv2.downloadsTabMystuffpage, 20)) {
										
										homepagev2.mystuff_tab.click();
										
										Utilities.verticalSwipeReverse(driver);
										Utilities.verticalSwipeReverse(driver);
										Utilities.verticalSwipeReverse(driver);
										
									}else {
										driver.navigate().back();	
									}
								    
									
								 
							// clearing the favourites Section
								    
								    /*homepagev2.tabClick("My Stuff");
									 Thread.sleep(2000);*/
								 //handle in try
									
						/*			
								 String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
								 boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
								 
									//Verify Favorites tray UI
									test.log(LogStatus.INFO, "Favourites tray is displayed");
									Utilities.verticalSwipe(driver);
									if(Utilities.explicitWaitVisible(driver, homepagev2.editFavorite, 10)) {  
										test.log(LogStatus.INFO, "Edit link is displayed");
										//Click on Edit link
										
											//Verify the functionality of edit option in My Stuff - Favorites tab.
											try {
												homepagev2.editFavorite.click();
											} catch (Exception e) {
												test.log(LogStatus.FAIL, "Unable to click edit Link In Watch Page");
											}
				 
										}else BasePageV2.reportFail("Not able found Edit option in favourites page"); 	
											
									if(Utilities.explicitWaitVisible(driver, favouritesPageV2.favEditPageBooks, 20)) {
									
										favouritesPageV2.favEditPageBooks.click();
										
										for(int i = 0 ; i < 10 ; i++) {
											
											if(Utilities.explicitWaitClickable(driver, favouritesPageV2.favIConStar, 20)) {
												favouritesPageV2.favIConStar.click();
											}
										}
										if(Utilities.explicitWaitClickable(driver, favouritesPageV2.UnfavouriteBtn, 20)) {
											favouritesPageV2.UnfavouriteBtn.click();
											Thread.sleep(3000);
											driver.navigate().back();
											Utilities.verticalSwipeReverse(driver);
											Utilities.verticalSwipeReverse(driver);
											Utilities.verticalSwipeReverse(driver);
										}else {
											test.log(LogStatus.INFO, "Bok cards are not contained in Books tab");
											driver.navigate().back();
											Utilities.verticalSwipeReverse(driver);
											Utilities.verticalSwipeReverse(driver);
											Utilities.verticalSwipeReverse(driver);
										}
										
									}else {
										test.log(LogStatus.FAIL, "Not found Books tab in Favourites page");
										
									}
									
							*/		
								//download read book and favourite 
									
									driver.runAppInBackground(Duration.ofSeconds(3));
									 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									 driver.currentActivity();
									
									
									 homepagev2.tabClick("Read");
									 Thread.sleep(2000);
									 Utilities.horizontalSwipe(driver);
									 String bookNameBefore = "";
									 if(Utilities.explicitWaitVisible(driver, readpagev2.readfirstBookcarosal, 10))
									 {
										 try {
											 bookNameBefore= readpagev2.firstBookNameBelowCarousal.getAttribute("text");
											 bookNameBefore=homepagev2.convertCamelCase(bookNameBefore);
												
											readpagev2.readfirstBookcarosal.click();
													 
										} catch (Exception e) {
											// TODO: handle exception
										}
										
											test.log(LogStatus.INFO, "Clicked on the Book "+bookNameBefore+" from Read Carousal");
											if(Utilities.explicitWaitClickable(driver, favouritesPageV2.favStartToFav, 10))
											{
												
												test.log(LogStatus.INFO, "clicked on favourite icon in Read book detail page");
											   if (favouritesPageV2.favStartToFav.getAttribute("checked").equalsIgnoreCase("false")) {
													((JavascriptExecutor) driver).executeScript("arguments[0].click();", favouritesPageV2.favStartToFav);
//												   favouritesPageV2.favStartToFav.click();
											   }else test.log(LogStatus.INFO, "Book card is favourited");

												
										      }
											
											
											try {
												
												 String bookName1 = readpagev2.bookNameInDetailsPage.getText().toString().trim();
												 
												 bookName=homepagev2.convertCamelCase(bookName1);
												
												 test.log(LogStatus.INFO, "The book name is : " + bookName);
												
											} catch (Exception e) {
												// TODO: handle exception
											}
											
											Utilities.verticalSwipe(driver, downloadsv2.downloadSimbolBtn);
											
									 }else BasePageV2.reportFail("Not able to found Book carosal");
									
									if(Utilities.explicitWaitVisible(driver, downloadsv2.downloadStatusText, 20)) {
										
										downloadsv2.downloadStatusText.click();
										test.log(LogStatus.INFO, "clicked on download book ");
										
										Thread.sleep(30000);
										
										driver.navigate().back();
									}else {
										test.log(LogStatus.INFO, "Book card downloaded");
										driver.navigate().back();
									}
									
									 // click on Listen tab 
									
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
										 Thread.sleep(80000);
										 
										 if(Utilities.explicitWaitClickable(driver, kidsPlayerPageV2.audioPlayerCloseButton, 20)) {
											 kidsPlayerPageV2.audioPlayerCloseButton.click();
										 }else test.log(LogStatus.FAIL, "Not able to click on player close button / not found");
			
										 
										 driver.navigate().back();
									 }else test.log(LogStatus.FAIL, "Unable to click play button / Not found");
									 
									} catch (Exception e) {
										// TODO: handle exception
									}
									
				             // switch profile
									
									if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 10))
										homepagev2.profilepic.click();
									else BasePageV2.reportFail("Not able to click on Profile Icon in home page");
									

									if(Utilities.explicitWaitVisible(driver, launchPageV2.switchProfileScreenProfileName, 20)) {
									test.log(LogStatus.INFO, "Created kids Profile name is displayed");
									 swicthProName = launchPageV2.switchProfileScreenProfileName.getText().toString();
									
									 test.log(LogStatus.INFO, "Switch Profile Name is : " + swicthProName);
									}else
									BasePageV2.reportFail("Created Kids Profile name is not displayed");
									
									Thread.sleep(20000);
									
									driver.navigate().back();
									
									homepagev2.tabClick("My Stuff");
									 Thread.sleep(2000);
									
									 Utilities.verticalSwipe(driver, downloadsv2.downloadsTabMystuffpage);
									 
									 if (Utilities.explicitWaitVisible(driver, downloadsv2.downloadsTabMystuffpage, 5))
										{
										 downloadsv2.downloadsTabMystuffpage.click();
											
										}else {
											test.log(LogStatus.FAIL, "Not able to click on Downloads Tab /  Not Found");
										}
									 
									 Utilities.verticalSwipe(driver);
									 Utilities.verticalSwipe(driver);
									
									 if (Utilities.explicitWaitClickable(driver, downloadsv2.editDownloadsMystuff, 5))
										{
											
													downloadsv2.editDownloadsMystuff.click();
													
										}else test.log(LogStatus.INFO, "Downloads are Empty");
									 
									driver.pressKeyCode(AndroidKeyCode.HOME);
								
									
									
				 
	  }
	
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	
}
