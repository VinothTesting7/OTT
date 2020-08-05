package com.viacom.uinavigation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.android.library.AndroidAlert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.FavouritesPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.MoviesPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyNavigation_P2 extends BaseTestV2  {
	
String testName = "VerifyNavigation_P2";
	
	@Test(dataProvider = "getData")
	public void verifyNavigation_P2(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("VerifyNavigation_P2");
	test.log(LogStatus.INFO, "Starting the test for VerifyNavigation_P2 : "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
	ListenPageV2 listenPage = new ListenPageV2(driver, test);
	

		String un=data.get("Email");
		String pwd=data.get("Password");
	    String mostPopularApi = data.get("mostPopularTray");
	    
		homepagev2.login(un, pwd);
		
		
		// VK_258 - Verify the functionality when tapping on cards present under 'Most Popular Shows' tray:
		// VK_262 - Verify the click functionality on tapping on 'See All' button
		// VK_255 - Verify the functionality by tapping on tray name:
	    // VK_2194 - Verify the functionality when tapping on show/charater image from show progarmme info:
	    // VK_2195 - Verify the functionality when tapping on audio book image from audio progarmme info:
	    // VK_2196  - Verify the functionality when tapping on book image from book progarmme info:
		
		
		

		
		// VK_258 - Verify the functionality when tapping on cards present under 'Most Popular Shows' tray:
		
		// click on Watch Tab in home page
		if(Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 50)) {
			try {
				homepagev2.tabClick("Watch");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			BasePageV2.reportFail("Failed to click on Watch Tab in home page / not displayed");
		}

		// scroll till Top TooNs tray in watch page
		Utilities.verticalSwipe(driver, watchpagev2.topToonsTray);
		String showCardTitle= "",showTileinfo=""; 
		if(Utilities.explicitWaitVisibleNew(driver, watchpagev2.topToonsTrayFirstCardd, 20)) {
			showCardTitle = watchpagev2.topToonsTrayFirstCardd.getText().toString().trim();
			test.log(LogStatus.INFO, "Character card title from tray :" + showCardTitle);
		}else {
			test.log(LogStatus.FAIL, "Not found show first card");
			BasePageV2.takeScreenshot();
		}
		
		if(Utilities.explicitWaitClickable(driver, watchpagev2.topToonsTrayFirstCard, 50)) {
			watchpagev2.topToonsTrayFirstCard.click();
			test.log(LogStatus.INFO, "click on show tray first card in watch page");
			try {
				 showTileinfo = watchpagev2.showNameInShowDetails.getText().toString().trim();
				 test.log(LogStatus.INFO, "character card title from card info page : " + showTileinfo);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			test.log(LogStatus.FAIL, "Not found show tray first card in watch page");
		}
		
		if(showCardTitle.equals(showTileinfo)) {
			test.log(LogStatus.PASS, "Verify the functionality when tapping on cards present under 'Most Popular Shows' tray:");
			if(!Utilities.setResultsKids("VK_258", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}else {
			test.log(LogStatus.FAIL, "Verify the functionality when tapping on cards present under 'Most Popular Shows' tray:");
			if(!Utilities.setResultsKids("VK_258", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		for(int i = 0 ; i < 5; i++) {
			Utilities.verticalSwipeReverse(driver);
		}
		
		// VK_262 - Verify the click functionality on tapping on 'See All' button
		
		// APi validation
	    if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			 homepagev2.watch_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Watch tab");
		 }
		 else {
			 BasePageV2.reportFail("Unable to click on Watch tab");
		 }
		 //Get tray name from API
		 int totalAssets=0;
		 String url_kidsCharacters="";
		 String api_kidsCharacters="Kids Characters Tray";
		 String apiname_kidsCharacters="";
		 String trayNameParameter="";
		 String trayTitle="";
		 String trayTitleCamel="";
		 String trayTitleUpper="";
		 Response resp_kidsCharacters=null;
		 int rows_kidsCharacters=xls.getRowCount("Api");
		 boolean insidePage=false;
		 for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
			apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
			if(apiname_kidsCharacters.equals(api_kidsCharacters)){
				url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
				resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
				totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
				test.log(LogStatus.INFO, "asset size of All character api : " + totalAssets);
				for(int i=0;i<totalAssets;i++) {
					trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
					if(trayNameParameter.equals("allKidsCharacters")) {
						trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
						trayTitleCamel=homepagev2.convertCamelCase(trayTitle);
						trayTitleUpper=trayTitle.toUpperCase();
					}
				}
			 }
		 }	
		 
		 String trayLoc="//android.widget.TextView[@text=\""+trayTitle+"\" or @text=\""+trayTitleUpper+"\" or @text=\""+trayTitleCamel+"\"]";
		 boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
		 if(trayPresence==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Tray title "+trayTitle+" is not displayed in the UI"); 
		 }
		
		//Verify the API response for Top Toons
		 //Calling kidsCharacters.json API
		 int totalitemsofapi=0;
		 int size=0;
		 String url_allCharacters="";
		 String apiname_allCharacters="";
		 ArrayList<String> characterNamesFromAPI=new ArrayList<String>();
		 ArrayList<String> characterNamesFromUI=new ArrayList<String>();
		 int characterTextsCount=0;
		 Response resp_allCharacters=null;
		 int rows=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows;rNum++){
			 String apiname=xls.getCellData("Api", "API Name", rNum);
			 if(apiname.equals("All Characters")){
				 url_allCharacters=xls.getCellData("Api", "Url", rNum);
				 apiname_allCharacters="All Characters";
				 Map map=BasePageV2.apiparams(2, xls, apiname_allCharacters);
				 resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
				 //resp_allCharacters.prettyPrint();
				 totalitemsofapi=resp_allCharacters.jsonPath().get("assets.items.size()");
				 test.log(LogStatus.INFO, "Total shows returned from API is "+totalitemsofapi);
				 if(totalitemsofapi>12)
					 totalitemsofapi=12; 
				 //Get the items from the API
				 for(int count1=0;count1<totalitemsofapi;count1++) {
					   String characterName=resp_allCharacters.jsonPath().get("assets.items["+count1+"].title"); 
					   characterNamesFromAPI.add(characterName);
				 }
			 }
		 }
		
		 
		 if(totalitemsofapi<1) {
			   test.log(LogStatus.INFO, "API returns empty list for All Kids Characters");
		 }
		 else {
			//Verify if tray is clickable and tap on back button
			try {
				driver.findElement(By.xpath(trayLoc)).click();
				test.log(LogStatus.INFO, "Clicked on tray "+trayTitle);
				Thread.sleep(2000);
				try {
					driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text' and @text=\""+trayTitle+"\" or @text=\""+trayTitleUpper+"\" or @text=\""+trayTitleCamel+"\"]"));
					test.log(LogStatus.INFO, trayTitle+" page is displayed");
					test.log(LogStatus.PASS, "Verify the functionality by tapping on tray name:");
					if(!Utilities.setResultsKids("VK_255", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					
				}
				catch(Exception e){
					test.log(LogStatus.INFO, trayTitle+" page is not displayed");
					test.log(LogStatus.FAIL, "Verify the functionality by tapping on tray name:");
					if(!Utilities.setResultsKids("VK_255", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
				}			
				if(Utilities.explicitWaitVisible(driver, homepagev2.backButton, 20)) {
					test.log(LogStatus.INFO, "Back button is displayed");
					try {
						homepagev2.backButton.click();
						test.log(LogStatus.INFO, "Clicked on Back button");
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to click on Back button");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Back button is not displayed");
				}		
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on tray "+trayTitle);
			}
		 }
		 
		 
		 //Verify See All presence
		 if(totalitemsofapi<=8) {
			 //Scroll twice and search for SEE ALL
			 test.log(LogStatus.FAIL, "Api contains <= 8 cards in Top Toons tray ");
		 }
		 else {
			//Scroll to the 6th item and search for see all
			//Scroll to the sixth card using description
			 String apiItem6=resp_allCharacters.jsonPath().get("assets.items[5].title");
			 String apiItem6Camel=homepagev2.convertCamelCase(apiItem6);
			 String apiItem6Upper=apiItem6.toUpperCase();
			 String uiItemDescLocator6="//android.widget.TextView[contains(@text,\""+apiItem6+"\") or contains(@text,\""+apiItem6Upper+"\") or contains(@text,\""+apiItem6Camel+"\")]";
			 boolean item6Presence=false;
			 for(int scroll=0;scroll<5;scroll++) {
				 item6Presence=Utilities.verticalSwipeAndFind(driver,uiItemDescLocator6);
				 if(item6Presence==true) 
					break; 		 
			 }
			 if(item6Presence==true) {
				Utilities.verticalSwipe(driver);
				test.log(LogStatus.INFO, "Scrolled and located sixth item");
				//Check for availability of See All
				if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 3)){
					test.log(LogStatus.INFO, "Located See All button");
					//Click on SEE ALL
					try {
						homepagev2.seeAll.click(); 
						test.log(LogStatus.INFO, "Clicked on See All button");
						Thread.sleep(5000);
						try {
							driver.findElement(By.xpath("//android.widget.TextView[@text=\""+trayTitle+"\" or @text=\""+trayTitleUpper+"\" or @text=\""+trayTitleCamel+"\"]"));
							test.log(LogStatus.INFO, trayTitle+" page is displayed");
							test.log(LogStatus.PASS, "Verify the click functionality on tapping on 'See All' button");
							if(!Utilities.setResultsKids("VK_262", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							insidePage=true;	
							
							driver.pressKeyCode(AndroidKeyCode.BACK);
							for(int i = 0 ; i < 5 ; i++) {
								Utilities.verticalSwipeReverse(driver);
							}
							
							
						}
						catch(Exception e){
							test.log(LogStatus.FAIL, trayTitle+" page is not displayed");
							test.log(LogStatus.FAIL, "Verify the click functionality on tapping on 'See All' button");
							if(!Utilities.setResultsKids("VK_262", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
							driver.pressKeyCode(AndroidKeyCode.BACK);
							for(int i = 0 ; i < 5 ; i++) {
								Utilities.verticalSwipeReverse(driver);
							}
						}
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to click on See All button");
					}
				}
				else {
					test.log(LogStatus.FAIL, "See All button is not displayed");
					
				}
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate 6th show");
			 }	 
		}
		
		// VK_2194 - Verify the functionality when tapping on show/charater image from show progarmme info:
		
		if(Utilities.explicitWaitClickable(driver, watchpagev2.topToonsTrayFirstCard, 50)) {
			watchpagev2.topToonsTrayFirstCard.click();
			test.log(LogStatus.INFO, "click on Character first card in Character tray in watch page");
		    if(Utilities.explicitWaitClickable(driver, watchpagev2.characterImg, 50)) {
		    	watchpagev2.characterImg.click();
		    	test.log(LogStatus.INFO, "click on character img in character info page");
		    	try {
					homepagev2.verifyAndProgressBar();
					test.log(LogStatus.PASS, "Verify the functionality when tapping on show/charater image from show progarmme info:");
					if(!Utilities.setResultsKids("VK_2194", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					driver.pressKeyCode(AndroidKeyCode.BACK);
					driver.pressKeyCode(AndroidKeyCode.BACK);
					for(int i = 0 ; i < 5 ; i++) {
						Utilities.verticalSwipeReverse(driver);
					}
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Verify the functionality when tapping on show/charater image from show progarmme info:");
					if(!Utilities.setResultsKids("VK_2194", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
					driver.pressKeyCode(AndroidKeyCode.BACK);
					driver.pressKeyCode(AndroidKeyCode.BACK);
					for(int i = 0 ; i < 5 ; i++) {
						Utilities.verticalSwipeReverse(driver);
					}
				}
		    	
		    	
		    	
		    }else {
		    	test.log(LogStatus.FAIL, "Failed to click on character img in character img info page");
		    	BasePageV2.takeScreenshot();
		    }
		}else {
			test.log(LogStatus.FAIL, "Not displayed the character card in character tray in watch page");
			BasePageV2.takeScreenshot();
		}
		
		
		// VK_2196  - Verify the functionality when tapping on book image from book progarmme info:
		
		// API validation 
	    if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) {
			 homepagev2.read_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Read tab");
		 }
		 else {
			 BasePageV2.reportFail("Unable to click on Read tab");
		 }
		 
		//Get tray name from API
		 int totalAssets2=0;
		 String url_kidsCharacters2="";
		 String api_kidsCharacters2="Listentab_alltrays";
		 String apiname_kidsCharacters2="";
		 String trayNameParameter2="";
		 String trayTitle2="";
		 String trayTitleCamel2="";
		 String trayTitleUpper2="";
		 String characterCardTitle1="";
		 Response resp_kidsCharacters2=null;
		 int rows_kidsCharacters2=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows_kidsCharacters2;rNum++){
			apiname_kidsCharacters2=xls.getCellData("Api", "API Name", rNum);
			if(apiname_kidsCharacters2.equals(api_kidsCharacters2)){
				url_kidsCharacters2=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters2);
				resp_kidsCharacters2=Utilities.requestUtilitypostOld(url_kidsCharacters2, map);
				totalAssets2=resp_kidsCharacters2.jsonPath().get("assets.size()");
				test.log(LogStatus.INFO, "asset size of All character api : " + totalAssets);
				for(int i=0;i<totalAssets2;i++) {
					trayNameParameter2=resp_kidsCharacters2.jsonPath().get("assets["+i+"].trayName");
					if(trayNameParameter2.equals("newAudios")) {
						trayTitle2=resp_kidsCharacters2.jsonPath().get("assets["+i+"].title");
						trayTitleCamel2=homepagev2.convertCamelCase(trayTitle2);
						trayTitleUpper2=trayTitle2.toUpperCase();
					}
				}
			 }
		 }	
		 
		 String trayLoc2="//android.widget.TextView[@text=\""+trayTitle2+"\" or @text=\""+trayTitleUpper2+"\" or @text=\""+trayTitleCamel2+"\"]";
		 boolean trayPresence2=Utilities.verticalSwipeAndFind(driver,trayLoc2);
		 if(trayPresence2==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle2+" is displayed in the UI");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Tray title "+trayTitle2+" is not displayed in the UI"); 
		 }
		 
		 
		 // get the First Audio card title 
		 
		 try {
			 // getting First card title from Audio page first tray
			  characterCardTitle1=resp_kidsCharacters2.jsonPath().get("assets[1].assets[0].items[0].title");
			  test.log(LogStatus.INFO, "Book card title from api : " + characterCardTitle1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		  
		 for(int i = 0 ; i < 20 ; i++) {
			 try {
				 driver.findElement(By.xpath("//android.widget.TextView[@text='"+characterCardTitle1+"' and @resource-id='com.viacom18.vootkids:id/grid_title']")).click();
				 test.log(LogStatus.INFO, "click on First card in Read page first tray ");
				 if(Utilities.explicitWaitClickable(driver, watchpagev2.characterImg, 60)) {
					 watchpagev2.characterImg.click();
					 test.log(LogStatus.INFO, "click on Read character icon in Read info page");
					 
					 try {
						 driver.findElement(By.xpath("//android.widget.TextView[@text='"+characterCardTitle1+"' and @resource-id='com.viacom18.vootkids:id/grid_title']"));
						 test.log(LogStatus.FAIL, "Verify the functionality when tapping on book image from book progarmme info:");
						 if(!Utilities.setResultsKids("VK_2196", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
						 BasePageV2.takeScreenshot();
						 
						 // going back to Home page
						 driver.pressKeyCode(AndroidKeyCode.BACK);
						 driver.pressKeyCode(AndroidKeyCode.BACK);
						 for(int j = 0 ; j < 5 ; j++) {
							 Utilities.verticalSwipeReverse(driver);
						 }
						 
					} catch (Exception e) {
						test.log(LogStatus.PASS, "Verify the functionality when tapping on book image from book progarmme info:");
						if(!Utilities.setResultsKids("VK_2196", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					 
					 
				 }else {
					 test.log(LogStatus.INFO, "Not displayed Audio character img in Audio card info page / Not click");
				 }
				 break;
			} catch (Exception e) {
				Utilities.verticalSwipe(driver);
			}
			 
		 }
		 

		
    // VK_2195 - Verify the functionality when tapping on audio book image from audio progarmme info: 
		
		
		// API validation 
	    if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10)) {
			 homepagev2.Listen_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Listen tab");
		 }
		 else {
			 BasePageV2.reportFail("Unable to click on Listen tab");
		 }
		 
		//Get tray name from API
		 int totalAssets1=0;
		 String url_kidsCharacters1="";
		 String api_kidsCharacters1="Listentab_alltrays";
		 String apiname_kidsCharacters1="";
		 String trayNameParameter1="";
		 String trayTitle1="";
		 String trayTitleCamel1="";
		 String trayTitleUpper1="";
		 String characterCardTitle="";
		 Response resp_kidsCharacters1=null;
		 int rows_kidsCharacters1=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows_kidsCharacters1;rNum++){
			apiname_kidsCharacters1=xls.getCellData("Api", "API Name", rNum);
			if(apiname_kidsCharacters1.equals(api_kidsCharacters1)){
				url_kidsCharacters1=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters1);
				resp_kidsCharacters1=Utilities.requestUtilitypostOld(url_kidsCharacters1, map);
				totalAssets1=resp_kidsCharacters1.jsonPath().get("assets.size()");
				test.log(LogStatus.INFO, "asset size of All character api : " + totalAssets);
				for(int i=0;i<totalAssets1;i++) {
					trayNameParameter1=resp_kidsCharacters1.jsonPath().get("assets["+i+"].trayName");
					if(trayNameParameter1.equals("newAudios")) {
						trayTitle1=resp_kidsCharacters1.jsonPath().get("assets["+i+"].title");
						trayTitleCamel1=homepagev2.convertCamelCase(trayTitle1);
						trayTitleUpper1=trayTitle1.toUpperCase();
					}
				}
			 }
		 }	
		 
		 String trayLoc1="//android.widget.TextView[@text=\""+trayTitle1+"\" or @text=\""+trayTitleUpper1+"\" or @text=\""+trayTitleCamel1+"\"]";
		 boolean trayPresence1=Utilities.verticalSwipeAndFind(driver,trayLoc1);
		 if(trayPresence1==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle1+" is displayed in the UI");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Tray title "+trayTitle1+" is not displayed in the UI"); 
		 }
		 
		 
		 // get the First Audio card title 
		 
		 try {
			 // getting First card title from Audio page first tray
			  characterCardTitle=resp_kidsCharacters1.jsonPath().get("assets[1].assets[0].items[0].title");
			  test.log(LogStatus.INFO, "Audio card title from api : " + characterCardTitle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		  
		 for(int i = 0 ; i < 20 ; i++) {
			 try {
				 driver.findElement(By.xpath("//android.widget.TextView[@text='"+characterCardTitle+"' and @resource-id='com.viacom18.vootkids:id/grid_title']")).click();
				 test.log(LogStatus.INFO, "click on First card in Audio page first tray ");
				 if(Utilities.explicitWaitClickable(driver, watchpagev2.characterImg, 60)) {
					 watchpagev2.characterImg.click();
					 test.log(LogStatus.INFO, "click on Audio character icon in audio info page");
					 if(Utilities.explicitWaitClickable(driver, listenPage.audioPlayerSettings, 60)) {
						    test.log(LogStatus.PASS, "Verify the functionality when tapping on audio book image from audio progarmme info:");
							if(!Utilities.setResultsKids("VK_2195", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 }else {
						    test.log(LogStatus.FAIL, "Verify the functionality when tapping on audio book image from audio progarmme info:");
							if(!Utilities.setResultsKids("VK_2195", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 
				 }else {
					 test.log(LogStatus.INFO, "Not displayed Audio character img in Audio card info page / Not click");
				 }
				 break;
			} catch (Exception e) {
				Utilities.verticalSwipe(driver);
			}
			 
		 }
		 
		 
		   
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				

	}	
	
	

}
