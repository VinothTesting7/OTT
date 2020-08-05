//Author Tanisha
package com.viacom.audioplayer;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;

import io.appium.java_client.android.AndroidKeyCode;

@SuppressWarnings("deprecation")
public class VerifyPlayback extends BaseTestV2 {
	public static String testName = "VerifyStatsInParentZone";
	public static String sheetName = "Regression Checklist";
	public static String pass = "PASS";
	public static String fail = "FAIL";

	public static int errorCount_0 = 0;
	public static int errorCount_1 = 0;
	public static int errorCount_2 = 0;
	
	String currentTimeBefore = null;
	String currentTimeAfter = null;

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

	@SuppressWarnings("deprecation")
	@Test(dataProvider = "getData")
	public void VerifyPlayback(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyPlayback");
		test.log(LogStatus.INFO, "Verify playback resume from last watched time");

		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		launchApp();

		HomePageV2 homepage = new HomePageV2(driver, test);
		WatchPageV2 watchPage = new WatchPageV2(driver, test);
		KidsPlayerPageV2 kidsPlayerPage = new KidsPlayerPageV2(driver, test);
		ListenPageV2 listenPageV2 = new ListenPageV2(driver, test);
		BooksPageV2 booksPageV2 = new BooksPageV2(driver, test);
		EbooksPageV2 ebooksPageV2 = new EbooksPageV2(driver, test);
		ReadPageV2 readpage =new ReadPageV2(driver, test);
		EbooksPageV2 ebookspagev2= new EbooksPageV2(driver,test);
		
		try {
			homepage.login(data.get("Email"),data.get("Password"));
		} catch (Exception e) {
			// TODO: handle exception
			BasePageV2.reportFail("Login credential couldn't fetch from data sheet");
		}
		String getVideoDurationString1="";
		int getVideoDurationInt1=0;
		String getVideoDurationString2="";
		int getVideoDurationInt2=0;
		String getAudioDurationString1="";
		int getAudioDurationInt1=0;
		String getAudioDurationString2="";
		int getAudioDurationInt2=0;
		

		
		// VK_1292 Verify the playback status for partially watched episode content
		// VK_1293 Verify the audio playback status for partially watched audio content
		// VK_1294 Verify the Reader playback status for partially watched book content
		


		String episode="";
		
		 //Get tray name from API
		 int totalAssets=0;
		 String url_kidsCharacters="";
		 String api_kidsCharacters="Kids Characters Tray";
		 String apiname_kidsCharacters="";
		 String trayNameParameter="";
		 String trayTitle="";
		 Response resp_kidsCharacters=null;
		 int rows_kidsCharacters=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
			apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
			if(apiname_kidsCharacters.equals(api_kidsCharacters)){
				url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
				resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
				totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
				for(int i=0;i<totalAssets;i++) {
					trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
					if(trayNameParameter.equals("allKidsCharacters")) {
						trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
					}
				}
			 }
		 }	
		 trayTitle=trayTitle.toUpperCase();
			 
		 //Get show name from API
		 String apiName="";
		 String uiName="";
		 //Verify from API first item of ALL CHARACTERS
		 String url_allCharacters="";
		 String api_allCharacters="All Characters";
		 String apiname_allCharacters="";
		 Response resp_allCharacters=null;
		 int rows_allCharacters=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows_allCharacters;rNum++){
			apiname_allCharacters=xls.getCellData("Api", "API Name", rNum);
			if(apiname_allCharacters.equals(api_allCharacters)){
				url_allCharacters=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(2, xls, api_allCharacters);
				resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
				apiName=resp_allCharacters.jsonPath().get("assets.items[0].title");
			 }
		 }
		homepage.tabClick("Watch");
		Thread.sleep(2000);
		 String trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
		 String allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
		 //Verify if ALL CHARACTERS section is displayed
		 boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
		 boolean presence=false;
		 if(trayPresence==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
			 for(int scroll=0;scroll<=1;scroll++) {
				 try {
					 driver.findElement(By.xpath(allCharFirstItem)).click();
					 test.log(LogStatus.INFO, "Clicked on show");	
					 presence=true;
					 break;
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 if(scroll==1)
						 test.log(LogStatus.INFO, "Failed to click on show");	
				 }
			 }
			 if (presence==true) {
				 try {
					 //Scroll till Episodes tray
					 for(int scroll=0;scroll<=3;scroll++) {
						 if(Utilities.explicitWaitVisible(driver, watchPage.episodesTray, 10)) {
							 test.log(LogStatus.INFO, "Scrolled to Episodes tray");
							 Utilities.verticalSwipe(driver);
							 Utilities.verticalSwipe(driver);
							 Thread.sleep(2000);
							 break;
						 }
						 else {
							 Utilities.verticalSwipe(driver);
							 if(scroll==3)
								 test.log(LogStatus.FAIL, "Failed to scroll to EPISODES tray");
						 }
					 }
					 boolean oneIteration=false;
					 if(Utilities.explicitWaitVisible(driver, watchPage.episodesItemOneInEpisodeTray, 10)) {
						 episode=watchPage.episodesItemOneInEpisodeTray.getAttribute("text");
						 if(Utilities.explicitWaitClickable(driver, watchPage.episodesItemOneInEpisodeTray, 10)) {
							 watchPage.episodesItemOneInEpisodeTray.click();
							 test.log(LogStatus.INFO, "Clicked on episode -> "+episode);
							 homepage.verifyAndProgressBar();
							 kidsPlayerPage.pauseVideo();
							 getVideoDurationString1 = kidsPlayerPage.playerCurrentDuration.getText().trim();
							 String minute=getVideoDurationString1.split(":")[0];
							 String seconds=getVideoDurationString1.split(":")[1];
							 int minuteInt=Integer.parseInt(minute);
							 int secondsInt=Integer.parseInt(seconds);
							 getVideoDurationInt1=minuteInt*60+secondsInt;
							 test.log(LogStatus.INFO, "Video played until: "+getVideoDurationString1+", i.e. "+getVideoDurationInt1+" seconds");
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to show details page");
							 oneIteration=true;
						 }
						 else {
							 test.log(LogStatus.FAIL, "First episode is not clickable");	 
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "First episode is not visible");	 
					 }
					 if(oneIteration==true) {
						 if(Utilities.explicitWaitVisible(driver, watchPage.episodesItemOneInEpisodeTray, 10)) {
							 episode=watchPage.episodesItemOneInEpisodeTray.getAttribute("text");
							 if(Utilities.explicitWaitClickable(driver, watchPage.episodesItemOneInEpisodeTray, 10)) {
								 watchPage.episodesItemOneInEpisodeTray.click();
								 test.log(LogStatus.INFO, "Clicked again on episode -> "+episode);
								 homepage.verifyAndProgressBar();
								 kidsPlayerPage.pauseVideo();
								 getVideoDurationString2 = kidsPlayerPage.playerCurrentDuration.getText().trim();
								 String minute=getVideoDurationString2.split(":")[0];
								 String seconds=getVideoDurationString2.split(":")[1];
								 int minuteInt=Integer.parseInt(minute);
								 int secondsInt=Integer.parseInt(seconds);
								 getVideoDurationInt2=minuteInt*60+secondsInt;
								 test.log(LogStatus.INFO, "Video paused at: "+getVideoDurationString2+", i.e. "+getVideoDurationInt2+" seconds");
								 if(getVideoDurationInt2<getVideoDurationInt1+60) {
									 test.log(LogStatus.FAIL, "Video has not resumed from last played time");
									 test.log(LogStatus.FAIL, "Verify the playback status for partially watched episode content is FAIL");
									 if(!Utilities.setResultsKids("VK_1292", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 else {
									 test.log(LogStatus.INFO, "Video has resumed from last played time");
									 test.log(LogStatus.PASS, "Verify the playback status for partially watched episode content is PASS");
									 if(!Utilities.setResultsKids("VK_1292", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to show details page");
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to Watch tab");
							 }
							 else {
								 test.log(LogStatus.FAIL, "First episode is not clickable");	 
							 }
						 }
						 else {
							 test.log(LogStatus.FAIL, "First episode is not visible");	 
						 }
					 }
				 }
				 catch(Exception e) {
					 
				 }
			 }
		 }
		test.log(LogStatus.INFO, " ******************************************** ");
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		//Click on Audio tab
		HomePageV2.tabClick("Listen");
		Utilities.verticalSwipe(driver);
		String audioNameBefore="";
		boolean playerDisplayed=false;
		boolean firstiteration=false;
		Thread.sleep(2000);
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
		if(Utilities.explicitWaitClickable(driver, listenPageV2.audio1InTray, 10)) {
			listenPageV2.audio1InTray.click();
			try {
				Utilities.verticalSwipe(driver);
				audioNameBefore=listenPageV2.nameFromDetailsPage.getAttribute("text");
				audioNameBefore=homepage.convertCamelCase(audioNameBefore);
				test.log(LogStatus.INFO, "Clicked on the Audio -> "+audioNameBefore);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to fetch Audio name");
			}
			
			if(Utilities.explicitWaitClickable(driver, listenPageV2.play_btn, 30)) {
				 try {
					 listenPageV2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button");	
					 for(int wait=0;wait<=30;wait++) {
						 if(Utilities.explicitWaitVisibleNew(driver, listenPageV2.audioPlayerSettings, 3)) {
							 try {
								 try {
									 driver.wait(100000);
								 }
								 catch(Exception e){}
								 listenPageV2.audioPauseBTN.click();
								 test.log(LogStatus.INFO, "Paused Audio player");
								 Thread.sleep(3000);
								 try {
									 getAudioDurationString1 = listenPageV2.playerDurationElapsed.getText().trim();
									 String minute=getAudioDurationString1.split(":")[0];
									 String seconds=getAudioDurationString1.split(":")[1];
									 int minuteInt=Integer.parseInt(minute);
									 int secondsInt=Integer.parseInt(seconds);
									 getAudioDurationInt1=minuteInt*60+secondsInt;
									 test.log(LogStatus.INFO, "Audio paused at: "+getAudioDurationString1+", i.e. "+getAudioDurationInt1+" seconds");
									 try {
										 Thread.sleep(2000);
										 listenPageV2.playerCloseButton.click();
										 test.log(LogStatus.INFO, "Closed Audio Player");
									 }
									 catch(Exception e) {
										 test.log(LogStatus.INFO, "Failed to click on Close of Audio Player");
										 driver.navigate().back();
										 test.log(LogStatus.INFO, "Navigated back to Audio details");
									 }			
									 firstiteration=true;
								 }
								 catch(Exception e) {
									 test.log(LogStatus.FAIL, "Failed to fetch audio time duration played"); 
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "Failed to Pause Audio player");
							 }
							 break;
						 }
						 else {
							 driver.wait(2000);
						 }
					 }
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Play button of the Audio");
				 } 	 
			}
			else {
				test.log(LogStatus.FAIL, "Play button of the Audio is not clickable"); 
			}
		}
		else {
			test.log(LogStatus.FAIL, "Audio card is not clickable"); 
		}
		if(firstiteration==true) {
			if(Utilities.explicitWaitClickable(driver, listenPageV2.play_btn, 30)) {
				 try {
					 listenPageV2.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button");	
					 for(int wait=0;wait<=30;wait++) {
						 if(Utilities.explicitWaitVisibleNew(driver, listenPageV2.audioPlayerSettings, 3)) {
							 try {
								 try {
									 driver.wait(100000);
								 }
								 catch(Exception e){}
								 listenPageV2.audioPauseBTN.click();
								 test.log(LogStatus.INFO, "Paused Audio player");
								 Thread.sleep(3000);
								 try {
									 getAudioDurationString2 = listenPageV2.playerDurationElapsed.getText().trim();
									 String minute=getAudioDurationString2.split(":")[0];
									 String seconds=getAudioDurationString2.split(":")[1];
									 int minuteInt=Integer.parseInt(minute);
									 int secondsInt=Integer.parseInt(seconds);
									 getAudioDurationInt2=minuteInt*60+secondsInt;
									 test.log(LogStatus.INFO, "Audio paused at: "+getAudioDurationString2+", i.e. "+getAudioDurationInt2+" seconds");
									 try {
										 Thread.sleep(2000);
										 listenPageV2.playerCloseButton.click();
										 test.log(LogStatus.INFO, "Closed Audio Player");
										 driver.navigate().back();
										 test.log(LogStatus.INFO, "Navigated back to Audio tab");
									 }
									 catch(Exception e) {
										 test.log(LogStatus.INFO, "Failed to click on Close of Audio Player");
										 driver.navigate().back();
										 test.log(LogStatus.INFO, "Navigated back to Audio details");
										 driver.navigate().back();
										 test.log(LogStatus.INFO, "Navigated back to Audio tab");
										 Thread.sleep(2000);
									 }
									 if(getAudioDurationInt2<getAudioDurationInt1+5) {
										 test.log(LogStatus.FAIL, "Audio has not resumed from last played time");
										 test.log(LogStatus.FAIL, "Verify the audio playback status for partially watched audio content is FAIL");
										 if(!Utilities.setResultsKids("VK_1293", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 }
									 else {
										 test.log(LogStatus.INFO, "Audio has resumed from last played time");
										 test.log(LogStatus.PASS, "Verify the audio playback status for partially watched audio content is PASS");
										 if(!Utilities.setResultsKids("VK_1293", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 }
								 }
								 catch(Exception e) {
									 test.log(LogStatus.FAIL, "Failed to fetch audio time duration played"); 
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "Failed to Pause Audio player");
							 }
							 break;
						 }
						 else {
							 driver.wait(2000);
						 }
					 }
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Play button of the Audio");
				 } 	 
			}
			else {
				test.log(LogStatus.FAIL, "Play button of the Audio is not clickable"); 
			}
		}
		test.log(LogStatus.INFO, " ******************************************** ");
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
//		VK_1294
		if (Utilities.explicitWaitVisible(driver, homepage.profilepic, 30)) {
			test.log(LogStatus.INFO, "Profile icon is visible in home screen");
			try {
				homepage.tabClick("Read");
				test.log(LogStatus.INFO, "Read tab has clicked succesful");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				test.log(LogStatus.FAIL, "Read tab has not clicked succesful");
			}
		}	
		 String bookNameBefore="";
		 boolean readerDisplayedPreview=false;
		 Thread.sleep(2000);
		 Utilities.verticalSwipe(driver);
		 Utilities.verticalSwipe(driver);
		 if(Utilities.explicitWaitClickable(driver, readpage.book1InTray, 10)) {
			 readpage.book1InTray.click();
			try {
				Utilities.verticalSwipe(driver);
				bookNameBefore=readpage.nameFromDetailsPage.getAttribute("text");
				bookNameBefore=homepage.convertCamelCase(bookNameBefore);
				test.log(LogStatus.INFO, "Clicked on the Book -> "+bookNameBefore);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to fetch Book name");
			}
			if(Utilities.explicitWaitClickable(driver, readpage.tryPreviewReadButton, 10)) {
				try {
					readpage.tryPreviewReadButton.click();
					test.log(LogStatus.INFO, "Clicked on PREVIEW/TRY/READ button");
					 for(int wait=0;wait<=30;wait++) {
						 try {
							 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
							 //Add 1447
							 if(wait==30) {
								 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
								 BasePageV2.takeScreenshot();
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to book details page");
							 }
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Loader is not displayed");
							 Set<String> CHS = driver.getContextHandles();
							 for(String ch:CHS){
								if(ch.contains("WEBVIEW")){
									test.log(LogStatus.INFO, "eBook Reader is displayed");
									Thread.sleep(1000);
									readerDisplayedPreview=true;
									break;
								}
							 }  
						 }	
						 if(readerDisplayedPreview==true)
							 break;
						 else
							 Thread.sleep(1000);
					 }						 
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to click on PREVIEW/TRY/READ button"); 
				 }
			}
			else {
				test.log(LogStatus.FAIL, "PREVIEW/TRY/READ button is not clickable"); 
			}
		}
		else {
			test.log(LogStatus.FAIL, "First book below carousal is not clickable"); 
		}
		boolean firstroundverification=false;
		String pages="";
		String numberofpagesread="";
		int numberofpagesreadInt=0;
		if(readerDisplayedPreview==true) {
			Utilities.horizontalSwipe(driver);
			//get reader controls
			Thread.sleep(2000);
			Utilities.tap(driver);
			Utilities.tap(driver);
			test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
			readerDisplayedPreview=false;
			if(Utilities.explicitWaitVisible(driver, ebookspagev2.readerCurrentChapter, 10)){
				try {																								 																																		
					pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
					String  arr[]=pages.split(" ");
				    numberofpagesread=arr[1].toString().trim();
				    numberofpagesreadInt=Integer.parseInt(numberofpagesread);										 
				    test.log(LogStatus.INFO, "Pages Read: "+numberofpagesreadInt);
				    driver.navigate().back();
				    test.log(LogStatus.INFO, "Navigated back to dismiss reader controls"); 
				    driver.navigate().back();
				    test.log(LogStatus.INFO, "Navigated back to book details"); 
				    firstroundverification=true;
				}
				catch(Exception e){
					test.log(LogStatus.FAIL, "Failed to retrieve pages read");	  	   
				}												
		    }
			else{
				test.log(LogStatus.FAIL, "Read page count is not visible in eBook reader");	  
			}	
		}
		readerDisplayedPreview=false;
		if(firstroundverification==true) {
			if(Utilities.explicitWaitClickable(driver, readpage.tryPreviewReadButton, 10)) {
				try {
					readpage.tryPreviewReadButton.click();
					test.log(LogStatus.INFO, "Clicked on PREVIEW/TRY/READ button");
					 for(int wait=0;wait<=30;wait++) {
						 try {
							 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
							 //Add 1447
							 if(wait==30) {
								 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
								 BasePageV2.takeScreenshot();
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to book details page");
							 }
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Loader is not displayed");
							 Set<String> CHS = driver.getContextHandles();
							 for(String ch:CHS){
								if(ch.contains("WEBVIEW")){
									test.log(LogStatus.INFO, "eBook Reader is displayed");
									Thread.sleep(1000);
									readerDisplayedPreview=true;
									break;
								}
							 }  
						 }	
						 if(readerDisplayedPreview==true)
							 break;
						 else
							 Thread.sleep(1000);
					 }						 
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to click on PREVIEW/TRY/READ button"); 
				 }
			}
			else {
				test.log(LogStatus.FAIL, "PREVIEW/TRY/READ button is not clickable"); 
			}	
		}
		String Spages="";
		String Snumberofpagesread="";
		int SnumberofpagesreadInt;
		if(readerDisplayedPreview==true) {
			Utilities.horizontalSwipe(driver);
			//get reader controls
			Thread.sleep(2000);
			Utilities.tap(driver);
			Utilities.tap(driver);
			test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
			if(Utilities.explicitWaitVisible(driver, ebookspagev2.readerCurrentChapter, 10)){
				try {																								 																																		
					Spages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
					String  Sarr[]=Spages.split(" ");
				    Snumberofpagesread=Sarr[1].toString().trim();
				    SnumberofpagesreadInt=Integer.parseInt(numberofpagesread);										 
				    if(SnumberofpagesreadInt==numberofpagesreadInt) {
				    	test.log(LogStatus.INFO, "Page last read previously:"+numberofpagesreadInt+", page opened currently: "+SnumberofpagesreadInt);
				    	test.log(LogStatus.PASS, "Verify the Reader playback status for partially watched book content is PASS");
				    	if(!Utilities.setResultsKids("VK_1294", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				    }
				    else {
				    	test.log(LogStatus.FAIL, "Page last read previously:"+numberofpagesreadInt+", page opened currently: "+SnumberofpagesreadInt);
				    	test.log(LogStatus.FAIL, "Verify the Reader playback status for partially watched book content is FAIL");
				    	if(!Utilities.setResultsKids("VK_1294", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				    }
				    driver.navigate().back();
				    test.log(LogStatus.INFO, "Navigated back to book details"); 
				    firstroundverification=true;
				}
				catch(Exception e){
					test.log(LogStatus.FAIL, "Failed to retrieve pages read");	  	   
				}												
		    }
			else{
				test.log(LogStatus.FAIL, "Read page count is not visible in eBook reader");	  
			}	
		}
	}
}
