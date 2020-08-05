package com.viacom.settings;

import java.time.Duration;
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
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

public class MovieCardIntegration_P1 extends BaseTestV2 {
	
String testName = "MovieCardIntegration_P1";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("MovieCardIntegration_P1");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_2037 - Verify the Download functionality by toggling OFF 'Enable Downloads' button
		//VK_2119 - Verify if watched Movie content gets updated in usage stats if user launches the player & closes 
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		WatchPageV2 watchPage = new WatchPageV2(driver, test);
		DownloadsPageV2 downloads = new DownloadsPageV2(driver, test);
		ListenPageV2 listenPage = new ListenPageV2(driver, test);
		
		String userName = data.get("Email");
		String password = data.get("Password");
		
		// Login to Voot Kids App
		homepagev2.login(userName, password);
		
		//VK_2116 - Verify if Movie card is displayed under 'Recent Activity' tray
		//VK_2119 - Verify if watched Movie content gets updated in usage stats if user launches the player & closes 
		// VK_2120 - Verify if watched content gets updated in usage stats if user watches a Movie partially
		//VK_2121 - Verify if watched content gets updated in usage stats if user watches a Movie completely
		
		// click on Watch tab
		homepagev2.tabClick("Watch");
		
		// scroll till movies tray
		
		 Utilities.verticalSwipe(driver,watchPage.watchMoviesTrayTitle,"visible",80);
		 Utilities.verticalSwipe(driver);
		 Utilities.verticalSwipe(driver);
		 
		
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
				driver.findElement(By.xpath("//android.widget.TextView[@text='"+movieCardTile+"']"));
				test.log(LogStatus.PASS, "Verify if Movie card is displayed under 'Recent Activity' tray");
				if(!Utilities.setResultsKids("VK_2116", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Verify if Movie card is displayed under 'Recent Activity' tray");
				BasePageV2.takeScreenshot();
				if(!Utilities.setResultsKids("VK_2116", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
		
		
		    //VK_2119 - Verify if watched Movie content gets updated in usage stats if user launches the player & closes 
		     
		     for(int j = 0 ; j < 30 ; j++) {
		    	 if(Utilities.explicitWaitVisible(driver, settingsPageV2.usageStatsTrayTile, 50)) {
		    		 settingsPageV2.parentZonePreferenceDayBtnClick.click();
		    		 Thread.sleep(20000);
		    		 break;
		    	 }else Utilities.verticalSwipeDown(driver);
		     }
		     
		     if(Utilities.explicitWaitVisible(driver, settingsPageV2.usageEpisodesCount, 5)) {
		    	 test.log(LogStatus.PASS, "Verify if watched Movie content gets updated in usage stats if user launches the player & closes");
					if(!Utilities.setResultsKids("VK_2119", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		     }else {
					test.log(LogStatus.FAIL, "Verify if watched Movie content gets updated in usage stats if user launches the player & closes");
					BasePageV2.takeScreenshot();
					if(!Utilities.setResultsKids("VK_2119", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} 
		     
		     
		     // VK_2120 - Verify if watched content gets updated in usage stats if user watches a Movie partially 
		     
		      // Watch Episode Stats Count 
		     
		     int watchStatusCount = 0;
		     try {
		    	 String watchStatsCount = settingsPageV2.watchStatsCount.getText().toString().trim();
			     test.log(LogStatus.INFO, "The Watch Status count is(String) : " + watchStatsCount);
			     
			     // convert String to Int 
			      watchStatusCount = Integer.parseInt(watchStatsCount);
			     test.log(LogStatus.INFO, "The Watch Status count is(Int) : " + watchStatusCount);
			     
			     driver.pressKeyCode(AndroidKeyCode.BACK);
			     driver.pressKeyCode(AndroidKeyCode.BACK);
			} catch (Exception e) {
				// TODO: handle exception
			}
		    
		    // click Watch Tab
		     
		     homepagev2.tabClick("Watch");
		     
		     try {
			    	driver.findElement(By.id("com.viacom18.vootkids:id/container_inner_carousel")).click();
			    	test.log(LogStatus.INFO, "Click on Carousal in Watch Page");
			    	homepagev2.verifyAndProgressBar();
			    	Thread.sleep(10000);
			    	driver.pressKeyCode(AndroidKeyCode.BACK);
			    	
		     }catch (Exception e) {
				// TODO: handle exception
			}
		     
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
		     
					 // scroll to Usage Stats Tray title
					 Utilities.verticalSwipe(driver,settingsPageV2.usageStatsTrayTile,"visible",80);
				
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.parentZonePreferenceDayBtnClick, 50)) {
		            	Utilities.verticalSwipe(driver);
		    		 settingsPageV2.parentZonePreferenceDayBtnClick.click();
		    		 Thread.sleep(10000);
		    	    }else {
		    	    	test.log(LogStatus.FAIL, "Failed to click on Day button under Usage stats tab");
		    	    	BasePageV2.takeScreenshot();
		    	    }
			
		            int watchStatusCount1 = 0;
		            
		            // Watch Episode Stats Count 
				     try {
				    	 String watchStatsCount1 = settingsPageV2.watchStatsCount.getText().toString().trim();
					     test.log(LogStatus.INFO, "The Watch Status count is(String) : " + watchStatsCount1);
					     
					     // convert String to Int 
					      watchStatusCount1 = Integer.parseInt(watchStatsCount1);
					     test.log(LogStatus.INFO, "The Watch Status count is(Int) : " + watchStatusCount1);
					     
					} catch (Exception e) {
						// TODO: handle exception
					}
		
		           if(watchStatusCount == watchStatusCount1 || watchStatusCount < watchStatusCount1) {
		        	   test.log(LogStatus.PASS, "Verify if watched content gets updated in usage stats if user watches a Movie partially");
						if(!Utilities.setResultsKids("VK_2120", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						test.log(LogStatus.PASS, "Verify if watched content gets updated in usage stats if user watches a Movie completely");
						if(!Utilities.setResultsKids("VK_2121", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        }else {
						test.log(LogStatus.FAIL, "Verify if watched content gets updated in usage stats if user watches a Movie partially");
						BasePageV2.takeScreenshot();
						if(!Utilities.setResultsKids("VK_2120", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						test.log(LogStatus.FAIL, "Verify if watched content gets updated in usage stats if user watches a Movie completely");
						BasePageV2.takeScreenshot();
						if(!Utilities.setResultsKids("VK_2121", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}  
		           
				     
		        
	
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	

}
