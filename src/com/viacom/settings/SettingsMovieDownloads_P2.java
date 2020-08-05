package com.viacom.settings;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionState;

public class SettingsMovieDownloads_P2 extends BaseTestV2 {
	
String testName = "SettingsMovieDownloads_P2";
	
	@Test(dataProvider = "getData")
	public void settingsMoblieNumberValidation(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsMovieDownloads_P2");
		test.log(LogStatus.INFO, "Starting the test to Verify Settings Moblie Number Validation: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_2037 - Verify the Download functionality by toggling OFF 'Enable Downloads' button
		//VK_2038 - Verify download functionality when 'Cellular Downloads' option is in OFF state and user is accessing app in cellular network.
		// VK_2039 - Verify download functionality when 'Cellular Downloads' option is in ON state and user is accessing app in cellular network.
		
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		WatchPageV2 watchPage = new WatchPageV2(driver, test);
		DownloadsPageV2 downloads = new DownloadsPageV2(driver, test);
		
		String userName = data.get("Email");
		String password = data.get("Password");
		
		// Login to Voot Kids App
		homepagev2.login(userName, password);
	
		downloads.deleteAllDownloads();
	
		 test.log(LogStatus.INFO, "Done");
		
	    // Disable Enable Downloads in Device Settings 
		
		try {
			settingsPageV2.enableDownloadsSwichOFF();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// click on Watch tab
		homepagev2.tabClick("Watch");
		
		// scroll till movies tray
		
		 Utilities.verticalSwipe(driver,watchPage.watchMoviesTrayTitle,"visible",80);
		 Utilities.verticalSwipe(driver);
		 Utilities.verticalSwipe(driver);
     if(Utilities.explicitWaitClickable(driver, watchPage.watchMoviesTrayTitle, 10)) {
        	watchPage.watchMoviesTrayTitle.click();
     }else {
    	 test.log(LogStatus.FAIL, "Failed to click on Watch Movies tray in watch page");
     }
 
     List<WebElement> moviesCrads = driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']//android.view.ViewGroup");
          for(int i = 1 ; i < 6; i++ ) {
        	  try {
        		  moviesCrads.get(i).click();
        		  Thread.sleep(5000);
            	  Utilities.verticalSwipe(driver);
            	  Utilities.verticalSwipe(driver);
                   settingsPageV2.putBackGroundApp3();
            	  if(Utilities.explicitWaitClickable(driver, settingsPageV2.downloadMovie, 10)) {
            		  settingsPageV2.downloadMovie.click();
            		  if(Utilities.explicitWaitVisible(driver, settingsPageV2.downloadDisablePopUp, 10)) {
            			  test.log(LogStatus.PASS, "Verify the Download functionality by toggling OFF 'Enable Downloads' button");
            				if(!Utilities.setResultsKids("VK_2037", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
            				if(Utilities.explicitWaitClickable(driver, settingsPageV2.okBtn, 10)) 
            					settingsPageV2.okBtn.click();
            				Thread.sleep(5000);
            				    driver.pressKeyCode(AndroidKeyCode.BACK);
            				    driver.pressKeyCode(AndroidKeyCode.BACK);
            				    for(int j = 0 ; j < 15 ;j++ ) {
            				    Utilities.verticalSwipeReverse(driver);
            				    }
            				   
            			  break;
            		  }
            	  }else {
            		  driver.pressKeyCode(AndroidKeyCode.BACK);
            	  }
			} catch (Exception e) {
				// TODO: handle exception
			}
        	  
        	  if(i == 5) {
      			test.log(LogStatus.FAIL, "Verify the Download functionality by toggling OFF 'Enable Downloads' button");
      			if(!Utilities.setResultsKids("VK_2037", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
      		}
        	  
        	  
          }
         
			 
			
		
		
		// Enable the Enable Downloads switch in  Device Settings
		 
		 settingsPageV2.enableDownloadsSwichON();
		
		 
		 //VK_2038 - Verify download functionality when 'Cellular Downloads' option is in OFF state and user is accessing app in cellular network.
		 
		 
		 // disable the Cellular Downloads option in Device Settings
		 
		 settingsPageV2.cellularDownloadsSwichOFFNew();
		 
		 
		 
		// click on Watch tab
			homepagev2.tabClick("Watch");
			
			Thread.sleep(3000);
			 driver.setConnection(new ConnectionState(0));
			 Thread.sleep(3000);
			
			// scroll till movies tray
			
			 Utilities.verticalSwipe(driver,watchPage.watchMoviesTrayTitle,"visible",80);
			 Utilities.verticalSwipe(driver);
			 Utilities.verticalSwipe(driver);
	     if(Utilities.explicitWaitClickable(driver, watchPage.watchMoviesTrayTitle, 10)) {
	        	watchPage.watchMoviesTrayTitle.click();
	     }else {
	    	 test.log(LogStatus.FAIL, "Failed to click on Watch Movies tray in watch page");
	     }
	
	 List<WebElement> moviesCradscellularOff = driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']//android.view.ViewGroup");
	          for(int i = 1 ; i < 6; i++ ) {
	        	  try {
	        		  moviesCradscellularOff.get(i).click();
	        		  Thread.sleep(5000);
	            	  Utilities.verticalSwipe(driver);
	            	  Utilities.verticalSwipe(driver);
	                   settingsPageV2.putBackGroundApp3();
	            	  if(Utilities.explicitWaitClickable(driver, settingsPageV2.downloadMovie, 10)) {
	            		  settingsPageV2.downloadMovie.click();
	            		  if(Utilities.explicitWaitVisible(driver, settingsPageV2.downloadDisablePopUp, 10)) {
	            			  test.log(LogStatus.PASS, "Verify download functionality when 'Cellular Downloads' option is in OFF state and user is accessing app in cellular network.");
	            				if(!Utilities.setResultsKids("VK_2038", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	            				if(Utilities.explicitWaitClickable(driver, settingsPageV2.okBtn, 10)) 
	            					settingsPageV2.okBtn.click();
	            				Thread.sleep(5000);
	            				    driver.pressKeyCode(AndroidKeyCode.BACK);
	            				    driver.pressKeyCode(AndroidKeyCode.BACK);
	            				    for(int j = 0 ; j < 15 ;j++ ) {
	            				    Utilities.verticalSwipeReverse(driver);
	            				    }
	            				   
	            			  break;
	            		  }
	            	  }else {
	            		  driver.pressKeyCode(AndroidKeyCode.BACK);
	            	  }
				} catch (Exception e) {
					// TODO: handle exception
				}
	        	  
	        	  if(i == 5) {
	      			test.log(LogStatus.FAIL, "Verify download functionality when 'Cellular Downloads' option is in OFF state and user is accessing app in cellular network.");
	      			if(!Utilities.setResultsKids("VK_2038", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	      			 driver.pressKeyCode(AndroidKeyCode.BACK);
 				    driver.pressKeyCode(AndroidKeyCode.BACK);
 				    for(int j = 0 ; j < 15 ;j++ ) {
 				    Utilities.verticalSwipeReverse(driver);
 				    }
	      		}
	        	  
	        	  
	          }
		
	          
	          // VK_2039 - Verify download functionality when 'Cellular Downloads' option is in ON state and user is accessing app in cellular network.

	          // Enable the Cellular Downloads to On state
	          settingsPageV2.cellularDownloadsSwichONNew();
	          
	       // click on Watch tab
				homepagev2.tabClick("Watch");
		
				// scroll till movies tray
				
				 Utilities.verticalSwipe(driver,watchPage.watchMoviesTrayTitle,"visible",80);
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
		     if(Utilities.explicitWaitClickable(driver, watchPage.watchMoviesTrayTitle, 10)) {
		        	watchPage.watchMoviesTrayTitle.click();
		     }else {
		    	 test.log(LogStatus.FAIL, "Failed to click on Watch Movies tray in watch page");
		     }
		 
		     List<WebElement> moviesCradscellularON = driver.findElementsByXPath("//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']//android.view.ViewGroup");
		          for(int i = 1 ; i < 6; i++ ) {
		        	  try {
		        		  moviesCradscellularON.get(i).click();
		        		  Thread.sleep(5000);
		            	  Utilities.verticalSwipe(driver);
		            	  Utilities.verticalSwipe(driver);
		                   settingsPageV2.putBackGroundApp3();
		            	  if(Utilities.explicitWaitClickable(driver, settingsPageV2.downloadMovie, 10)) {
		            		  settingsPageV2.downloadMovie.click();
		            		  Thread.sleep(10000);
		            		  if(!Utilities.explicitWaitVisible(driver, settingsPageV2.downloadDisablePopUp, 50)) {
		            			  test.log(LogStatus.PASS, "Verify download functionality when 'Cellular Downloads' option is in ON state and user is accessing app in cellular network.");
		            				if(!Utilities.setResultsKids("VK_2039", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		            				if(Utilities.explicitWaitClickable(driver, settingsPageV2.okBtn, 10)) 
		            					settingsPageV2.okBtn.click();
		            				Thread.sleep(5000);
		            				    driver.pressKeyCode(AndroidKeyCode.BACK);
		            				    driver.pressKeyCode(AndroidKeyCode.BACK);
		            				    for(int j = 0 ; j < 15 ;j++ ) {
		            				    Utilities.verticalSwipeReverse(driver);
		            				    }
		            				   
		            			  break;
		            		  }
		            	  }else {
		            		  driver.pressKeyCode(AndroidKeyCode.BACK);
		            	  }
					} catch (Exception e) {
						// TODO: handle exception
					}
		        	  
		        	  if(i == 5) {
		      			test.log(LogStatus.FAIL, "Verify download functionality when 'Cellular Downloads' option is in ON state and user is accessing app in cellular network.");
		      			if(!Utilities.setResultsKids("VK_2039", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		      		}
		        	  
		        	  
		          }
	          

	          
	          // Turn On the WiFI
		
			 driver.setConnection(new ConnectionState(2));
		
			 
			 
		    // clear Downloads
			 
			 
			 downloads.deleteAllDownloads();
		    
		
		
		
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	

}
