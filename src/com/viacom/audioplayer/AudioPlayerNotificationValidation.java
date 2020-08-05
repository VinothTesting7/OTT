package com.viacom.audioplayer;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;


// Author - Suresh

public class AudioPlayerNotificationValidation extends BaseTestV2 {
	
	String pass="PASS";
	String fail="FAIL";	
	
	String testName = "AudioPlayerNotificationValidation";
	
	String tc1 = "VK_583";
	String tc2 = "VK_585";
	
	String	audiobook="";
	
	@Test(dataProvider = "getData")
	public void VerifyEbookDefaults(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("AudioPlayerNotificationValidation");
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
				
			// Verify that Audio player from device notification dismisses by killing the app: - VK_583	
				
				 HomePageV2.tabClick("Listen");

					int rows = xls.getRowCount("Api");
					System.out.println("APi ROW count is " + rows);
					for (int rNum = 1; rNum <= rows; rNum++) {
						String apiname = xls.getCellData("Api", "API Name", rNum);
						if (apiname.equals("AudioBookCards")) {
							String ApiTab = xls.getCellData("Api", "Url", rNum);

							Response respTab = Utilities.requestUtility(ApiTab);

							audiobook = respTab.jsonPath().get("assets.items[0].title");
							// Audiobooks.add(abooks);

						} else
							System.out.println("Unable to fetch tab from API");
					}

					try {
						String xpath = "//android.widget.TextView[contains(@text,'" + audiobook + "')]";

						Utilities.verticalSwipe(driver, xpath);
						WebElement ab = driver.findElement(By.xpath(xpath));

						if (Utilities.explicitWaitVisible(driver, ab, 10))
							ab.click();
						else
							BasePageV2.reportFail("Audio Book is not displayed");
					} catch (Exception e) {
					}

					try {
						if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 25))
							homepagev2.playAudiobookbutton.click();
						
						else
							BasePageV2.reportFail("Play button not displayed in audio detail page");

					} catch (Exception e) {
						BasePageV2.reportFail("Play button not displayed in audio detail page");
					}
	 
				 
					try {
						if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 30)) {
							test.log(LogStatus.INFO, "Tapping on audiobook navigated to audio player");
							
							
						    driver.closeApp(); // close the Application
							
						    Thread.sleep(2000);
							
							driver.openNotifications();
							
							try {
								
								
								 driver.findElement(By.xpath("xpath"));
								 
								 if(!Utilities.setResultsKids(tc1, "Fail"))
								      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 test.log(LogStatus.FAIL, "Verify that Audio player from device notification dismisses by killing the app: - VK_583");
								
							} catch (Exception e) {
								 if(!Utilities.setResultsKids(tc1, "pass"))
								      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 test.log(LogStatus.PASS, "Verify that Audio player from device notification dismisses by killing the app: - VK_583");
								
							}
							
							
							
							
						} else {
							test.log(LogStatus.FAIL, "Tapping on audiobook navigated is not to audio player");
							
						}
					} catch (Exception e) {
						BasePageV2.reportFail("Audio Player not displayed");
					}

				 
			// Verify the functionality when tapping on the audio player notification area anywhere other than Play/Pause/Volume/Close buttons: - VK_585
					
					driver.pressKeyCode(AndroidKeyCode.BACK);
						
					// relaunching the app
					driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKbetaSplashActivity"));
					
					HomePageV2.tabClick("Listen");
					
					
					
					try {
						String xpath = "//android.widget.TextView[contains(@text,'" + audiobook + "')]";

						Utilities.verticalSwipe(driver, xpath);
						WebElement ab = driver.findElement(By.xpath(xpath));

						if (Utilities.explicitWaitVisible(driver, ab, 10))
							ab.click();
						else
							BasePageV2.reportFail("Audio Book is not displayed");
					} catch (Exception e) {
					}

					try {
						if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 25))
							homepagev2.playAudiobookbutton.click();
						
						else
							BasePageV2.reportFail("Play button not displayed in audio detail page");

					} catch (Exception e) {
						BasePageV2.reportFail("Play button not displayed in audio detail page");
					}
	 
					try {
						
						driver.openNotifications();
						driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'" + audiobook + "')]")).click();
			
						Thread.sleep(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 30)) {
						test.log(LogStatus.INFO, "Tapping on audiobook navigated to audio player");
					
						if(!Utilities.setResultsKids(tc2, "pass"))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.PASS, "Verify the functionality when tapping on the audio player notification area anywhere other than Play/Pause/Volume/Close buttons: - VK_585");
						
					
					} else {
						test.log(LogStatus.FAIL, "Tapping on audiobook in notifications navigated is not to audio player");
						if(!Utilities.setResultsKids(tc2, "Fail"))
						      test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 test.log(LogStatus.FAIL, "Verify the functionality when tapping on the audio player notification area anywhere other than Play/Pause/Volume/Close buttons: - VK_585");
					     basepagev2.takeScreenshot();
						
					}
					
 

					
					
	   }
				
				 
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}		 
	

}
