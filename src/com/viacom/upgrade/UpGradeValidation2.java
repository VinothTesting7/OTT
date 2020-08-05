package com.viacom.upgrade;

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

public class UpGradeValidation2 extends BaseTestV2 {
	
	
	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "UpGradeValidation2";
	
	String tc1 = "VK_1287";
	String tc2 = "VK_1288";
	String tc3 = "VK_1289";
	String tc4 = "VK_1290";
	String tc5 = "VK_1291";
	
	
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("UpGradeValidation2");
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
					 
					 // Verify list of created profiles post upgrade - VK_1290
				 
				     try {
					
						
						
						driver.findElement(By.xpath("//android.widget.TextView[@text=\""+UpGradeValidation1.swicthProName+"\"]"));
						test.log(LogStatus.INFO, "switch profile showed in Upgrade build is :  " + UpGradeValidation1.swicthProName );
				    	 
						 if(!Utilities.setResultsKids(tc4, pass))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.PASS, "Verify list of created profiles post upgrade - VK_1290");
				    	 
					} catch (Exception e) {
						
						 if(!Utilities.setResultsKids(tc4, fail))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.FAIL, "Verify list of created profiles post upgrade - VK_1290");
						 BasePageV2.takeScreenshot();
						
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
								    
								    	if(appversion.equals(VootConstants.LATEST_BUILD)) {
								    		test.log(LogStatus.INFO, "Installed Latest Version of '"+ appversion +"' Voot kids Application ");
								    		if(!Utilities.setResultsKids(tc1, pass))
											      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											 test.log(LogStatus.PASS, "Verify app launch post upgrade - VK_1287");
								    		
								    	}else {
								    		
								    		if(!Utilities.setResultsKids(tc1, fail))
											      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											 test.log(LogStatus.FAIL, "Verify app launch post upgrade - VK_1287");
											 BasePageV2.takeScreenshot();
								    	}
								    	
								    	driver.navigate().back();
								    	driver.navigate().back();
								    	driver.navigate().back();
								    	
								    	
								    	
								    }else {
								    	test.log(LogStatus.FAIL, "Not Navigated to Help page");
								    }
					 
								    
								    // clering the favourites Section
								    
								    homepagev2.tabClick("My Stuff");
									 Thread.sleep(2000);
									 
	
				 
				 
				 
				
				try {
				
					Utilities.verticalSwipe(driver, homepagev2.lastViewedTitle);
					
					homepagev2.lastViewedTitle.click();
					
					Thread.sleep(2000);
					
					String lastviewedCardsTitles = "";
					
					for(int i = 0 ; i < homepagev2.lastViewedTrayCardsTile.size() ; i++) {
						 lastviewedCardsTitles = homepagev2.lastViewedTrayCardsTile.get(i).getText().toString().trim();
						System.out.println("The Tile card Last Viewed Card is : " + lastviewedCardsTitles);
						test.log(LogStatus.INFO, "The Tile card Last Viewed Card is : " + lastviewedCardsTitles);
						if(lastviewedCardsTitles.equals(UpGradeValidation1.AudiocardName)) {
							
							if(!Utilities.setResultsKids(tc5, pass))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.PASS, "Verify 'Last Viewed' tray data post upgrade - VK_1291");
							 break;
							
						}
						else if(i==homepagev2.lastViewedTrayCardsTile.size()-1)
						{
							if(!Utilities.setResultsKids(tc5, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify 'Last Viewed' tray data post upgrade - VK_1291");
							 BasePageV2.takeScreenshot();
							 
						}
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				 
		 
				 
				 
				 try {
					
					 driver.navigate().back();
					 
					 Utilities.verticalSwipe(driver, downloadsv2.downloadsTabMystuffpage);
						
					 downloadsv2.downloadsTabMystuffpage.click();
						
						Thread.sleep(2000);
						
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						
						if(Utilities.explicitWaitClickable(driver, downloadsv2.editDownloadsMystuff, 20)) {
							downloadsv2.editDownloadsMystuff.click();
							
							 try {
									

									driver.findElement(By.xpath("//android.widget.TextView[@text=\""+UpGradeValidation1.bookName+"\"]"));
									
									test.log(LogStatus.INFO, "Found The Last Viewed card Title : " + UpGradeValidation1.bookName);
								 
								 
									if(!Utilities.setResultsKids(tc3, pass))
									      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 test.log(LogStatus.PASS, "Verify downloaded cards availability post upgrade - VK_1289");
									 
									 driver.navigate().back();
									 homepagev2.mystuff_text.click();
									 
								 
							} catch (Exception e) {  
								
								if(!Utilities.setResultsKids(tc3, fail))
								      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 test.log(LogStatus.FAIL, "Verify downloaded cards availability post upgrade - VK_1289");
								 BasePageV2.takeScreenshot();
								
							}
							
						}else {
							
							if(!Utilities.setResultsKids(tc3, fail))
							      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 test.log(LogStatus.FAIL, "Verify downloaded cards availability post upgrade - VK_1289");
							 BasePageV2.takeScreenshot();
							
							test.log(LogStatus.INFO, "Downloads are Clear Doet Not contain data");
							 
							if(Utilities.explicitWaitVisible(driver, downloadsv2.downloadsTabMystuffpage, 20)) {
								
								homepagev2.mystuff_tab.click();
								
								Utilities.verticalSwipeReverse(driver);
								Utilities.verticalSwipeReverse(driver);
								Utilities.verticalSwipeReverse(driver);
								
							}else {
								driver.navigate().back();	
							}
						}
						
					
					
					
	
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
				 


				
				 try {
					
					 
					 Utilities.verticalSwipe(driver, favouritesPageV2.favFavoritesTitle);
						
					 favouritesPageV2.favFavoritesTitle.click();
						
						Thread.sleep(2000);
					
					favouritesPageV2.favEditPageBooks.click();

					
					test.log(LogStatus.INFO, " The Favourited card is  : " + UpGradeValidation1.bookName);
					System.out.println("The Favourited card is  : " + UpGradeValidation1.bookName);
					

					
					String favCardTile = favouritesPageV2.favCardTitle.getText().trim().toString();

					favCardTile=homepagev2.convertCamelCase(favCardTile);
					test.log(LogStatus.INFO, "The Favourited card Title is  : " + favCardTile);
					System.out.println("The Favourited card Title is  : " + favCardTile);
					
					
					
					if(favCardTile.equals(UpGradeValidation1.bookName)) {
						if(!Utilities.setResultsKids(tc2, pass))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.PASS, "Verify favorited cards availability post upgrade - VK_1288");
					}else {
						
						if(!Utilities.setResultsKids(tc2, fail))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.FAIL, "Verify favorited cards availability post upgrade - VK_1288");
						 BasePageV2.takeScreenshot();
					}
					
					
					 
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				 
				 
				 
				 
				 VootConstants.ENVIRONMENT = "Production";	 
				 
				 
				 
				 
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	
	

}
