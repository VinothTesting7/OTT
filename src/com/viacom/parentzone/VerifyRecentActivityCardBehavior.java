//Author Tanisha

package com.viacom.parentzone;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.CharacterDetailsV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;


@SuppressWarnings("deprecation")
public class VerifyRecentActivityCardBehavior extends BaseTestV2 {
	String testName = "VerifyRecentActivityCardBehavior";
	String sheetName = "Regression Checklist";
	String pass = "PASS";
	String fail = "FAIL";

	static int errorCount_0 = 0;
	static int errorCount_1 = 0;
	static int errorCount_2 = 0;
	static int errorCount_3 = 0;
	static int errorCount_4 = 0;
	static int errorCount_5 = 0;

	boolean firstActivityFlag;
	boolean secondActivityFlag;
	boolean thirdActivityFlag;
	boolean fourthActivityFlag;
	boolean fifthActivityFlag;

	@SuppressWarnings({ "unused", "static-access" })
	@Test(dataProvider = "getData")
	public void VerifyRecentActivityCardBehavior(Hashtable<String, String> data) throws Exception {
		String email = data.get("Email");
		String password = data.get("Password");
		System.out.println(email);
		System.out.println(password);

		String VK_1547 = "VK_1547";
		//Verify the Last seen data is updated accordingly as the time moves forward in Recent Activity section
		String VK_1542 = "VK_1542";
		//Verify the UI of 'Recent Activity' section in Activity Segmented tab
		String VK_1543 = "VK_1543";
		//Verify the no.of cards present under 'Recent Activity' section
		String VK_1544 = "VK_1544";
		//Verify the cards removal order under 'Recent Activity' section when user watches more than 5 cards
		String VK_1545 = "VK_1545";
		//Verify the type of cards available under 'Recent Activity' section
		String VK_1548 = "VK_1548";
		//Verify the navigation by tapping on any of the cards from Recent Activity section

		String recentActivityTitle = null;
		String recentActivityFirstItemData = null;
		String recentActivitySecondItemData = null;
		String recentActivityThirdItemData = null;
		String recentActivityFourthItemData = null;
		String recentActivityFifthItemData = null;
		String recentActivitySixthItemData = null;
		String lastUpdatedTime = null;
		String lastUpdatedTime_2 = null;
		String lastUpdatedTime_3 = null;
		String lastUpdatedTime_4 = null;
		String lastUpdatedTime_5 = null;

		String firstItemInCaros = null;
		
		int err1547=0;
		int err1542=0;
		int err1543=0;
		int err1544=0;
		int err1545=0;
		int err1548=0;
		
		test = rep.startTest("VerifyRecentActivityCardBehavior");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		try {
			launchApp();
		} 
		catch (Exception e) {
			// TODO: handle exception
			BasePageV2.takeScreenshot();
			BasePageV2.reportFail("Launch app has fail");
		}
		
		LaunchPageV2 launchpagev2 = new LaunchPageV2(driver, test);
		HomePageV2 homepage = new HomePageV2(driver, test);
		SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		WatchPageV2 watchPage = new WatchPageV2(driver, test);
		BooksPageV2 booksPage = new BooksPageV2(driver, test);
		ListenPageV2 listenPage = new ListenPageV2(driver, test);
		CharacterDetailsV2 chardetailsv2=new CharacterDetailsV2(driver,test);
		KidsPlayerPageV2 kidspage=new KidsPlayerPageV2(driver,test);
		ReadPageV2 readpage=new ReadPageV2(driver,test);
		/*HomePageV2.logout();
		try {
			homepage.login(email, password);
		} catch (Exception e) {
			// TODO: handle exception
			BasePageV2.takeScreenshot();
			BasePageV2.reportFail("Login credential couldn't fetch from data sheet");
		}*/

		//Click on Watch tab
		homepage.tabClick("Watch");
		Thread.sleep(2000);

		 //Get tray name from API
		 String apiTrayName="";
		 String uiTrayName="";
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
					 if(Utilities.explicitWaitVisible(driver, watchPage.episodesItemOneInEpisodeTray, 10)) {
						 recentActivityFirstItemData=watchPage.episodesItemOneInEpisodeTray.getAttribute("text");
						 test.log(LogStatus.INFO, "First Episode is -> "+recentActivityFirstItemData);
						 if(Utilities.explicitWaitClickable(driver, watchPage.episodesItemOneInEpisodeTray, 10)) {
							 watchPage.episodesItemOneInEpisodeTray.click();
							 test.log(LogStatus.INFO, "Clicked on episode");
							 homepage.verifyAndProgressBar();
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to show details page");
						 }
						 else {
							 test.log(LogStatus.FAIL, "First episode is not clickable");	 
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "First episode is not visible");	 
					 }
					 if(Utilities.explicitWaitVisible(driver, watchPage.episodesItemTwoInEpisodeTray, 10)) {
						 recentActivitySecondItemData=watchPage.episodesItemTwoInEpisodeTray.getAttribute("text");
						 test.log(LogStatus.INFO, "Second Episode is -> "+recentActivitySecondItemData);
						 if(Utilities.explicitWaitClickable(driver, watchPage.episodesItemTwoInEpisodeTray, 10)) {
							 watchPage.episodesItemTwoInEpisodeTray.click();
							 test.log(LogStatus.INFO, "Clicked on episode");
							 homepage.verifyAndProgressBar();
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to show details page");
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to Watch tab");
							 Utilities.verticalSwipeReverse(driver);
							 Utilities.verticalSwipeReverse(driver);
							 Utilities.verticalSwipeReverse(driver);
						 }
						 else {
							 test.log(LogStatus.FAIL, "Second episode is not clickable");	 
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Second episode is not visible");	 
					 }
				 }
				 catch(Exception e){
						test.log(LogStatus.FAIL, "Failed to click on first show under "+trayTitle+" tray");
				 }
			 }
			 else {
				 test.log(LogStatus.INFO, "Tray title "+trayTitle+"is not displayed in the UI");
			 }
		 }
		 //Click on Read tab
		 homepage.tabClick("Read");
		 String bookNameBefore="";
		 boolean readerDisplayedPreview=false;
		 Thread.sleep(2000);
		 Utilities.verticalSwipe(driver);
		 Utilities.verticalSwipe(driver);
		 if(Utilities.explicitWaitClickable(driver, readpage.book1InTray, 10)) {
			readpage.book1InTray.click();
			try {
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				bookNameBefore=readpage.nameFromDetailsPage.getAttribute("text");
				recentActivityThirdItemData=homepage.convertCamelCase(bookNameBefore);
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
									driver.navigate().back();
									test.log(LogStatus.INFO, "Navigated back to Book details");
									Thread.sleep(1000);
									driver.navigate().back();
									test.log(LogStatus.INFO, "Navigated back to Read tab");
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
		readerDisplayedPreview=false;
		if(Utilities.explicitWaitClickable(driver, readpage.book2InTray, 10)) {
			readpage.book2InTray.click();
			try {
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				bookNameBefore=readpage.nameFromDetailsPage.getAttribute("text");
				recentActivityFourthItemData=homepage.convertCamelCase(bookNameBefore);
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
									driver.navigate().back();
									test.log(LogStatus.INFO, "Navigated back to Book details");
									driver.navigate().back();
									test.log(LogStatus.INFO, "Navigated back to Read tab");
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
			test.log(LogStatus.FAIL, "Second book below carousal is not clickable"); 
		}
		
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		//Click on Audio tab
		HomePageV2.tabClick("Listen");
		Utilities.verticalSwipe(driver);
		String audioNameBefore="";
		boolean playerDisplayed=false;
		Thread.sleep(2000);
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
		if(Utilities.explicitWaitClickable(driver, listenPage.audio1InTray, 10)) {
			listenPage.audio1InTray.click();
			try {
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				audioNameBefore=listenPage.nameFromDetailsPage.getAttribute("text");
				recentActivityFifthItemData=homepage.convertCamelCase(audioNameBefore);
				test.log(LogStatus.INFO, "Clicked on the Audio -> "+audioNameBefore);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to fetch Audio name");
			}
			if(Utilities.explicitWaitClickable(driver, listenPage.play_btn, 30)) {
				 try {
					 listenPage.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button");
					 for(int wait=0;wait<=30;wait++) {
						 homepage.verifyAndProgressBar();
						 try {
							 if(Utilities.explicitWaitVisible(driver, listenPage.audioPlayerSettings, 10))
							 test.log(LogStatus.INFO, "Audio Player is displayed");
							 playerDisplayed=true;
							 try {
								 listenPage.playerCloseButton.click();
								 test.log(LogStatus.INFO, "Closed player");
							 }
							 catch(Exception e) {
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
							 }
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio tab");
						 }
						 catch(Exception e) {
							 if(wait==30) {
								 test.log(LogStatus.FAIL, "Player failed to display");
								 break;
							 }
								 
						 }	
						 if(playerDisplayed==true)
							 break;
						 else
							 Thread.sleep(2000);
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
		readerDisplayedPreview=false;
		if(Utilities.explicitWaitClickable(driver, listenPage.audio2InTray, 10)) {
			listenPage.audio2InTray.click();
			try {
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				audioNameBefore=listenPage.nameFromDetailsPage.getAttribute("text");
				recentActivitySixthItemData=homepage.convertCamelCase(audioNameBefore);
				test.log(LogStatus.INFO, "Clicked on the Audio -> "+audioNameBefore);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to fetch Audio name");
			}
			if(Utilities.explicitWaitClickable(driver, listenPage.play_btn, 30)) {
				 try {
					 listenPage.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button");
					 for(int wait=0;wait<=30;wait++) {
						 homepage.verifyAndProgressBar();
						 try {
							 if(Utilities.explicitWaitVisible(driver, listenPage.audioPlayerSettings, 10))
							 test.log(LogStatus.INFO, "Audio Player is displayed");
							 playerDisplayed=true;
							 try {
								 listenPage.playerCloseButton.click();
								 test.log(LogStatus.INFO, "Closed player");
							 }
							 catch(Exception e) {
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
							 }
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to audio tab");
						 }
						 catch(Exception e) {
							 if(wait==30) {
								 test.log(LogStatus.FAIL, "Player failed to display");
								 break;
							 }
								 
						 }	
						 if(playerDisplayed==true)
							 break;
						 else
							 Thread.sleep(2000);
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

		//Wait for 2 minutes
		for(int wait=0;wait<=30;wait++) {
			try {
				driver.wait(1000);
			}
			catch(Exception e) {}
		}	
		
		
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		
		//Navigate to Recent Activity
		if(Utilities.explicitWaitClickable(driver, homepage.profilepic, 10)) {
			try {
				homepage.profilepic.click(); // tap on profile icon
				test.log(LogStatus.INFO, "Clicked on profile icon in home page");
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on profile icon in home page");
			}
		}
		else {
			test.log(LogStatus.INFO, "Profile icon in home page is not clickable");
			BasePageV2.takeScreenshot();
		}	
		if(Utilities.explicitWaitClickable(driver, settingspagev2.parentZoneButton, 20)) {
			try {
				settingspagev2.parentZoneButton.click(); // tap on parent zone button
				test.log(LogStatus.INFO, "Clicked on Parent Zone button");
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on Parent Zone button");
			}
		}
		else {
			test.log(LogStatus.INFO, "Parent Zone button is not clickable");
			BasePageV2.takeScreenshot();
		}		
		try {
			settingspagev2.parentPinContainer.sendKeys("1111");
			test.log(LogStatus.INFO, "Entered PIN 1111");
		} 
		catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to enter 1111");
		}	
		boolean foundRecentActivity=false;
		for (int scroll = 0; scroll <= 15; scroll++) {
			if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneRecentActivity, 5)) {
				foundRecentActivity=true;
				break;
			} 
			else {
				Utilities.verticalSwipe(driver);
				if(scroll==15) {
					test.log(LogStatus.FAIL, "Failed to locate Recent Activity");
					err1542++;
				}
			}
		}
		if(foundRecentActivity==true) {
			String[] items=new String[6];
			items[0]=recentActivitySixthItemData;
			items[1]=recentActivityFifthItemData;
			items[2]=recentActivityFourthItemData;
			items[3]=recentActivityThirdItemData;
			items[4]=recentActivitySecondItemData;
			items[5]=recentActivityFirstItemData;
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
			for(int i=0;i<=4;i++) {
				test.log(LogStatus.INFO, " ************************************************* ");
				String path="//android.widget.FrameLayout[@index=\""+i+"\" and @resource-id='com.viacom18.vootkids:id/parent_for_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_activity_item_title']";
				String time="//android.widget.FrameLayout[@index=\""+i+"\" and @resource-id='com.viacom18.vootkids:id/parent_for_download_item']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_activity_item_time_textview']";
				try {
					String nameUI=driver.findElement(By.xpath(path)).getAttribute("text");
					String timeUI=driver.findElement(By.xpath(time)).getAttribute("text");
					if(nameUI.equalsIgnoreCase(items[i])) {
						test.log(LogStatus.INFO, "Recent Activity item "+(i+1)+" is correct: "+nameUI);
					}
					else {
						test.log(LogStatus.FAIL, "Recent Activity item "+(i+1)+" should be: "+items[i]+", but UI displays: "+nameUI);
						err1542++;
						err1545++;
					}
					if(timeUI.contains("minutes ago") || timeUI.contains("minute ago") || timeUI.contains("hour ago") || timeUI.contains("hours ago") || timeUI.contains("second ago") || timeUI.contains("seconds ago")) {
						test.log(LogStatus.INFO, "Last seen data is displayed");
					}
					else {
						test.log(LogStatus.FAIL , "Last seen data is not displayed for activity "+i);
						err1547++;
					}
					try {
						driver.findElement(By.xpath(path)).click();
						test.log(LogStatus.INFO, "Clicked on the item");
						try {
							driver.findElement(By.xpath(path));
							test.log(LogStatus.INFO, "No response on clicking the item -> Expected behavior");
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Clicking on item gave some reponse");
							BasePageV2.takeScreenshot();
							err1548++;
						}
					}
					catch(Exception e1) {
						test.log(LogStatus.INFO, "Failed to click on the item");
						err1543++;
					}
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					try {
						String nameUI=driver.findElement(By.xpath(path)).getAttribute("text");
						String timeUI=driver.findElement(By.xpath(time)).getAttribute("text");
						if(nameUI.equalsIgnoreCase(items[i])) {
							test.log(LogStatus.INFO, "Recent Activity item "+(i+1)+" is correct: "+nameUI);
						}
						else {
							test.log(LogStatus.FAIL, "Recent Activity item "+(i+1)+ "should be: "+items[i]+", but UI displays: "+nameUI);
							err1542++;
							err1545++;
						}
						if(timeUI.contains("minutes ago") || timeUI.contains("minute ago") || timeUI.contains("hour ago") || timeUI.contains("hours ago") || timeUI.contains("second ago") || timeUI.contains("seconds ago")) {
							test.log(LogStatus.INFO, "Last seen data is displayed");
						}
						else {
							test.log(LogStatus.FAIL , "Last seen data is not displayed for activity "+i);
							err1547++;
						}
						try {
							driver.findElement(By.xpath(path)).click();
							test.log(LogStatus.INFO, "Clicked on the item");
							try {
								driver.findElement(By.xpath(path));
								test.log(LogStatus.INFO, "No response on clicking the item -> Expected behavior");
							}
							catch(Exception e1) {
								test.log(LogStatus.FAIL, "Clicking on item gave some reponse");
								BasePageV2.takeScreenshot();
								err1548++;
							}
						}
						catch(Exception e1) {
							test.log(LogStatus.INFO, "Failed to click on the item");
						}
					}
					catch(Exception e1) {
						test.log(LogStatus.FAIL, "Failed to locate Recent Activity item "+i);
						err1543++;
					}
				}
			}
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
			try {
				String path="//android.widget.FrameLayout[@index='5']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_activity_item_title']";
				String nameUI=driver.findElement(By.xpath(path)).getAttribute("text");
				test.log(LogStatus.FAIL, "Located Recent Activity item 6");
				err1542++;
				err1543++;
				err1544++;
			}
			catch(Exception e1) {
				test.log(LogStatus.INFO, "Recent Activity item 6 is not present, expected behavior");
			}
		}
		BasePageV2.takeScreenshot();
		if(err1547==0) {
			test.log(LogStatus.PASS, "Verify the Last seen data is updated accordingly as the time moves forward in Recent Activity section is PASS");
			if(!Utilities.setResultsKids("VK_1547", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the Last seen data is updated accordingly as the time moves forward in Recent Activity section is FAIL");
			if(!Utilities.setResultsKids("VK_1547", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		if(err1542==0) {
			test.log(LogStatus.PASS, "Verify the UI of 'Recent Activity' section in Activity Segmented tab is PASS");
			if(!Utilities.setResultsKids("VK_1542", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the UI of 'Recent Activity' section in Activity Segmented tab is FAIL");
			if(!Utilities.setResultsKids("VK_1542", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		if(err1543==0) {
			test.log(LogStatus.PASS, "Verify the no.of cards present under 'Recent Activity' section is PASS");
			if(!Utilities.setResultsKids("VK_1543", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the no.of cards present under 'Recent Activity' section is FAIL");
			if(!Utilities.setResultsKids("VK_1543", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		if(err1544==0) {
			test.log(LogStatus.PASS, "Verify the cards removal order under 'Recent Activity' section when user watches more than 5 cards is PASS");
			if(!Utilities.setResultsKids("VK_1544", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the cards removal order under 'Recent Activity' section when user watches more than 5 cards is FAIL");
			if(!Utilities.setResultsKids("VK_1544", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		if(err1545==0) {
			test.log(LogStatus.PASS, "Verify the type of cards available under 'Recent Activity' section is PASS");
			if(!Utilities.setResultsKids("VK_1545", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available under 'Recent Activity' section is FAIL");
			if(!Utilities.setResultsKids("VK_1545", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		if(err1548==0) {
			test.log(LogStatus.PASS, "Verify the navigation by tapping on any of the cards from Recent Activity section is PASS");
			if(!Utilities.setResultsKids("VK_1548", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the navigation by tapping on any of the cards from Recent Activity section is FAIL");
			if(!Utilities.setResultsKids("VK_1548", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
	
}
