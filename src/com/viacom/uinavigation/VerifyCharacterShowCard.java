package com.viacom.uinavigation;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.CharacterDetailsV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha

public class VerifyCharacterShowCard extends BaseTestV2{
	
	String testName = "VerifyCharacterShowCard";
	@Test(dataProvider = "getData")
	public void VerifyCharacterShowCard(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyCharacterShowCard");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}		
		
		// VK_276 Verify the characters present in characters tray
		// VK_278 Verify the navigation on tapping on any of the kid character
			
		int err278=0;
		//Launch Voot kids app
		 launchApp();
		 test.log(LogStatus.INFO, "Application launched successfully");
		 //System.out.println(driver.getPageSource());
		 
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 CharacterDetailsV2 chardetailsv2=new CharacterDetailsV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 //Tap on Watch
		 
		 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			 homepagev2.watch_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Watch tab");
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
			 boolean trayPresence=false;
			 for(int scroll=0;scroll<=3;scroll++) {
				 try {
					 driver.findElement(By.xpath(trayLoc));
					 trayPresence=true;
					 break;
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 if(scroll==3) {
						 test.log(LogStatus.FAIL, "Failed to scroll to "+trayTitle+" in UI");
						 homepagev2.takeScreenshot();
					 }
				 }
			 }
			 boolean presence=false;
			 if(trayPresence==true) {
				 test.log(LogStatus.INFO, "Scrolled to character show card under tray");
				 for(int scroll=0;scroll<=1;scroll++) {
					 try {
						 driver.findElement(By.xpath(allCharFirstItem));
						 presence=true;
						 break;
					 }
					 catch(Exception e) {
						 Utilities.verticalSwipe(driver);
						 if(scroll==3) {
							 test.log(LogStatus.FAIL, "Failed to scroll to character show card under tray");
							 homepagev2.takeScreenshot();
						 }
					 } 
				 }
				 if (presence==true) {
					 try {
						 test.log(LogStatus.INFO,"First Show is displayed");
						 test.log(LogStatus.PASS, "Verify the characters present in characters tray is PASS");
						 if(!Utilities.setResultsKids("VK_276", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 driver.findElement(By.xpath(allCharFirstItem)).click(); 
						 test.log(LogStatus.INFO, "Clicked on first show");
						 if(Utilities.explicitWaitVisible(driver, chardetailsv2.characterName, 20)) {
					 		 try {
					 			 uiName=chardetailsv2.characterName.getAttribute("text").trim();
					 		 }catch(Exception e){
					 			 test.log(LogStatus.FAIL, "Unable to fetch the Show name");
					 		 }
					 		test.log(LogStatus.INFO,"Name returned by API is "+apiName+" and name displayed in UI is "+uiName);
					 		 if(apiName.equalsIgnoreCase(uiName)) {
					 			 test.log(LogStatus.INFO, "Name returned by API matches with UI name");
					 		 }
					 		 else {
					 			test.log(LogStatus.INFO, "Name returned by API does not match with UI name");
					 			 err278++;
					 		 }	
					 		 if(Utilities.explicitWaitVisible(driver, showpagev2.showDetailPageBackButton, 10)) {
					 			test.log(LogStatus.INFO, "Back button is displayed");
					 			try {
						 			 showpagev2.showDetailPageBackButton.click();
						 			 test.log(LogStatus.INFO, "Clicked on Back button");
						 		 }
						 		 catch(Exception e) {
						 			 test.log(LogStatus.FAIL, "Unable to click on Back");
						 		 }
					 		 }
					 		 else {
					 			test.log(LogStatus.FAIL, "Back button is not displayed"); 
					 			err278++;
					 		 }
					 	 }	
					 	 else { 
					 		test.log(LogStatus.FAIL, "Character name is not displayed in Show details page");
					 		err278++;
					 		BasePageV2.takeScreenshot();
					 	 }
					 }
					 catch(Exception e){
						test.log(LogStatus.FAIL, "Failed to click on first show under "+trayTitle+" tray");
						err278++;
					 }
				 }
				 else { 
					 test.log(LogStatus.FAIL, "Unable to swiple to first show under "+trayTitle);
					 test.log(LogStatus.FAIL, "Verify the characters present in characters tray is FAIL");
					 if(!Utilities.setResultsKids("VK_276", "Fail")) test.log(LogStatus.WARNING, "Verify the characters present in characters tray");
					 err278++;
					 BasePageV2.takeScreenshot();
				 }
			 }
			 else {
				 test.log(LogStatus.INFO, "Tray title "+trayTitle+"is not displayed in the UI");
				 err278++;
			 }
			 
		 }
		 else {
			 test.log(LogStatus.FAIL, "Unable to click on Watch tab");
			 err278++;
		 }
 		 if(err278==0) {
 			test.log(LogStatus.PASS, "Verify the navigation on tapping on any of the kid character is Pass");
 			if(!Utilities.setResultsKids("VK_278", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
 		 }
 		 else {
 			test.log(LogStatus.FAIL, "Verify the navigation on tapping on any of the kid character is Fail");
 			if(!Utilities.setResultsKids("VK_278", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot(); 
 		 }
		   	   
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
	
	
	
}

