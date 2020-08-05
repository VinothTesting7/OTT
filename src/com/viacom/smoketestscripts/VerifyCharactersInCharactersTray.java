package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.MobileElement;
//Author Tanisha

public class VerifyCharactersInCharactersTray extends BaseTestV2{
	
	String testName = "VerifyCharactersInCharactersTray";
	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyCharactersInCharactersTray");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// VK_275 Validate the functionality by tapping on tray name
		// VK_277 Verify the metadata for kid characters cards present under 'All Characters' tray
		// VK_279 Verify the availibility of 'See All' Button in All Characters Tray if there are > 8 characters
		// VK_280 Verify the availibility of 'See All' button in All Characters Tray if there are 8 characters
		// VK_281 Verify the availibility of 'See All' button in All Characters Tray if there are < 8 characters
		// VK_282 Verify the ''See All'' button functionality
		// VK_283 Verify the displayed cards order

		
		//Launch Voot kids app
		 launchApp();
		 test.log(LogStatus.INFO, "Application launched successfully");
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 //System.out.println(driver.getPageSource());
		 int countSwipe=0;
		 int err277=0;
		 int err291=0;
		 int err283=0;
		//Tap on Watch
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
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
		 
		 //Verify the API response for All Characters
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
					driver.findElement(By.xpath("//android.widget.TextView[@text=\""+trayTitle+"\" or @text=\""+trayTitleUpper+"\" or @text=\""+trayTitleCamel+"\"]"));
					test.log(LogStatus.INFO, trayTitle+" page is displayed");
					test.log(LogStatus.PASS, "Validate the functionality by tapping on tray name is PASS");
					if(!Utilities.setResultsKids("VK_275", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					
				}
				catch(Exception e){
					test.log(LogStatus.INFO, trayTitle+" page is not displayed");
					test.log(LogStatus.FAIL, "Validate the functionality by tapping on tray name is FAIL");
					if(!Utilities.setResultsKids("VK_275", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
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
			 for(int findseeall=0;findseeall<=1;findseeall++) {
				 if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 10)) {
					 if(totalitemsofapi<8) {
						 test.log(LogStatus.FAIL, "Verify the availibility of 'See All' button in All Characters Tray if there are < 8 characters is FAIL");
						 if(!Utilities.setResultsKids("VK_281", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else if(totalitemsofapi==8) {
						 test.log(LogStatus.FAIL, "Verify the availibility of 'See All' button in All Characters Tray if there are 8 characters is FAIL");
						 if(!Utilities.setResultsKids("VK_280", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 BasePageV2.takeScreenshot();
					 break;
				 }
				 else {
					 Utilities.verticalSwipe(driver);
					 if(findseeall==1) {
						 if(totalitemsofapi<8) {
							 test.log(LogStatus.FAIL, "Verify the availibility of 'See All' button in All Characters Tray if there are < 8 characters is PASS");
							 if(!Utilities.setResultsKids("VK_281", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
						 else if(totalitemsofapi==8) {
							 test.log(LogStatus.FAIL, "Verify the availibility of 'See All' button in All Characters Tray if there are 8 characters is PASS");
							 if(!Utilities.setResultsKids("VK_280", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
						 Utilities.verticalSwipeReverse(driver);
						 Utilities.verticalSwipeReverse(driver);
						 try {
								driver.findElement(By.xpath(trayLoc)).click();
								test.log(LogStatus.INFO, "Clicked on tray "+trayTitle);
								Thread.sleep(2000);	
								insidePage=true;
							}
						 catch(Exception e) {
								test.log(LogStatus.INFO, "Failed to click on tray "+trayTitle);
						 }
					 }
				 }
			 }		 
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
					test.log(LogStatus.PASS, "Verify the availibility of 'See All' Button in All Characters Tray if there are > 8 characters is PASS");
					if(!Utilities.setResultsKids("VK_279", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					//Click on SEE ALL
					try {
						homepagev2.seeAll.click(); 
						test.log(LogStatus.INFO, "Clicked on See All button");
						Thread.sleep(5000);
						try {
							driver.findElement(By.xpath("//android.widget.TextView[@text=\""+trayTitle+"\" or @text=\""+trayTitleUpper+"\" or @text=\""+trayTitleCamel+"\"]"));
							test.log(LogStatus.INFO, trayTitle+" page is displayed");
							test.log(LogStatus.PASS, "Verify the \"See All\" button functionality is PASS");
							if(!Utilities.setResultsKids("VK_282", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							insidePage=true;			
						}
						catch(Exception e){
							test.log(LogStatus.FAIL, trayTitle+" page is not displayed");
							test.log(LogStatus.FAIL, "Verify the \"See All\" button functionality is FAIL");
							if(!Utilities.setResultsKids("VK_282", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
						}
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to click on See All button");
					}
				}
				else {
					test.log(LogStatus.INFO, "See All button is not displayed");
					test.log(LogStatus.FAIL, "Verify the availibility of 'See All' Button in All Characters Tray if there are > 8 characters is FAIL");
					if(!Utilities.setResultsKids("VK_279", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate 6th show");
			 }
			 if(insidePage==true) {
				//Verification of lazy load
				 ArrayList firstListTitles=new ArrayList();
				 ArrayList secondListTitles=new ArrayList();
				 String uiItemTitleLocator="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
				 for(int tryfetch=0;tryfetch<=2;tryfetch++) {
					 try {
						 List<WebElement> firstList=driver.findElements(By.xpath(uiItemTitleLocator));
						 test.log(LogStatus.INFO, "First list of shows count: "+firstList.size());
						 for(int count=0;count<firstList.size();count++) {
							 try {
								 String title=firstList.get(count).getAttribute("text");
								 if(!firstListTitles.contains(title))
									 firstListTitles.add(title); 
							 }
							 catch(Exception e) {
								 test.log(LogStatus.INFO, "Failed to fetch title of show "+(count+1)+" from the first list");
							 }
							 
						 }
						 test.log(LogStatus.INFO, "First list of show titles: "+firstListTitles);
						 break;
					 }
					 catch(Exception e) {
						 Thread.sleep(2000);
						 if(tryfetch==2)
							 test.log(LogStatus.FAIL, "Failed to fetch show titles before scroll");
					 }	  
				 }
				 
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 Utilities.verticalSwipe(driver);
				 test.log(LogStatus.INFO, "Swiped vertically twice ..");
				 Thread.sleep(2000);
				 for(int tryfetch=0;tryfetch<=2;tryfetch++) {
					 try {
						 List<WebElement> secondList=driver.findElements(By.xpath(uiItemTitleLocator));
						 test.log(LogStatus.INFO, "Second list of shows count: "+secondList.size());
						 for(int count=0;count<secondList.size();count++) {
							 try {
								 String title=secondList.get(count).getAttribute("text");
								 if(!secondListTitles.contains(title))
									 secondListTitles.add(title); 
							 }
							 catch(Exception e) {
								 test.log(LogStatus.INFO, "Failed to fetch title of show "+(count+1)+" from the second list");
							 }			 
						 }
						 test.log(LogStatus.INFO, "Second list of show titles: "+secondListTitles);
						 break;
					 }
					 catch(Exception e) {
						 Thread.sleep(2000);
						 if(tryfetch==2)
							 test.log(LogStatus.FAIL, "Failed to fetch show titles before scroll");
					 } 	 
				 } 
				 if(firstListTitles.equals(secondListTitles)) {
					test.log(LogStatus.INFO, "Items before and after scroll are same ..");
					
				 }
				 else {
					test.log(LogStatus.INFO, "Items before and after scroll vary ..");
				 }	  
				 //Scroll to the top
				 Utilities.verticalSwipeReverse(driver);
				 Utilities.verticalSwipeReverse(driver);
				 Utilities.verticalSwipeReverse(driver);
				 Utilities.verticalSwipeReverse(driver);
				 Utilities.verticalSwipeReverse(driver);
				 test.log(LogStatus.INFO, "Scrolled to the top");
				 //Verify meta data and the order of items
				 ArrayList<String> titleList=new ArrayList<String>();
				 test.log(LogStatus.INFO, "Verification of meta data for each show:");
				 test.log(LogStatus.INFO, "------------------------------------------------");
				 List<String> checkBeforeSort=new LinkedList<String>(); 
				 String uiItemShowTitle="";
				 for(int count=0;count<totalitemsofapi;count++) {
					 if(count==6)
						 Utilities.verticalSwipe(driver);
					 Thread.sleep(1000);
					 String apiItemShowTitle=resp_allCharacters.jsonPath().get("assets.items["+count+"].title"); 
					 try {
						 uiItemShowTitle=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,\""+apiItemShowTitle+"\")]")).getText();
						 if(uiItemShowTitle.contains(apiItemShowTitle)) {
							 test.log(LogStatus.INFO, "Meta Data->Show title is matching in API and UI for "+uiItemShowTitle);
							 checkBeforeSort.add(uiItemShowTitle);
						 }
						 else {
							 test.log(LogStatus.INFO, "Meta Data->Show title is not matching in API and UI for "+uiItemShowTitle);
							 err277++;
							 err283++;
						 }	 
					 }
					 catch(Exception e) {
						 Utilities.verticalSwipe(driver);
						 Thread.sleep(2000);
						 try {
							 uiItemShowTitle=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,\""+apiItemShowTitle+"\")]")).getText();
							 if(uiItemShowTitle.contains(apiItemShowTitle)) { 
								 test.log(LogStatus.INFO, "Meta Data->Show title is matching in API and UI for "+uiItemShowTitle);
								 checkBeforeSort.add(uiItemShowTitle);
							 }
							 else {
							 test.log(LogStatus.INFO, "Meta Data->Show title is not matching in API and UI for "+uiItemShowTitle);
							 err277++;
							 }	
						 }
						 catch(Exception e1) {
							 test.log(LogStatus.INFO, "Unable to locate show "+count);
							 err277++;
						 }
					 }	
				 }
				 if(err277==0) {
					 test.log(LogStatus.PASS, "Verify the metadata for kid characters cards present under 'All Characters' tray is PASS");
					 if(!Utilities.setResultsKids("VK_277", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 
				 }
				 else {
					 test.log(LogStatus.FAIL, "Verify the metadata for kid characters cards present under 'All Characters' tray is FAIL");
					 if(!Utilities.setResultsKids("VK_277", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 List<String> templist=new LinkedList<String>(checkBeforeSort); 
				 test.log(LogStatus.INFO, "List in UI: "+templist);
				 Collections.sort(checkBeforeSort);
				 test.log(LogStatus.INFO, "List sorted alphabetically: "+checkBeforeSort); 
				 for(int i=0;i<checkBeforeSort.size();i++){
					if(!checkBeforeSort.get(i).equals(templist.get(i))) {
						err283++;  
					}
				 }		  
				 if(err283==0) {
					 test.log(LogStatus.INFO, "The displayed cards are sorted alpabetically");
					 test.log(LogStatus.PASS, "Verify the displayed cards order is Pass");
					 if(!Utilities.setResultsKids("VK_283", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else {
					 test.log(LogStatus.FAIL, "The displayed cards are not sorted alpabetically");
					 test.log(LogStatus.FAIL, "Verify the displayed cards order is Fail");
					 if(!Utilities.setResultsKids("VK_283", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }		 
			}	 
		}
		 
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
	
	
}
