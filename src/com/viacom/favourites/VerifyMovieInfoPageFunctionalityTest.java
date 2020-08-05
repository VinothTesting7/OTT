package com.viacom.favourites;

import java.time.Duration;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

//Author: Vinoth
public class VerifyMovieInfoPageFunctionalityTest extends BaseTestV2{
	
	//VK_2016 - Verify the navigation by tapping on Play button from Movie Info Page
	//VK_2018 - Verify Favorite icon functionality from Movie Info Page
	//VK_2021 - Verify Unfavorite icon functionality from Movie Info Page
	//vk_2197 - Verify the functionality when tapping on movie image from movie program info: 
	//VK_2314 - Verify the functionality when user taps on Favorite icon from movie programme info for the 1st time:
	//vk_2315 - Verify the functionality of OK button from Favorite pop up for a movie card:
	//VK_2316 - Verify the functionality when user taps on Favorite icon from movie programme info from 2nd time onwards:
	
	String testName = "VerifyMovieInfoPageFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{                     
	if(GlobalVariables.break_Flag)
	throw new SkipException("Skipping the test as it is No");
	
	test = rep.startTest("VerifyMovieInfoPageFunctionalityTest");
	test.log(LogStatus.INFO, "Starting the test for Verifying the  functionality in movie info page: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	launchApp();
	driver.resetApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	WatchPageV2  watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2  playerpagev2=new KidsPlayerPageV2(driver,test);
	
	String un=data.get("Email");
	String pwd=data.get("Password");
    //homepagev2.logout();
	homepagev2.login(un, pwd);
    
	//click on watch tab	
	test.log(LogStatus.INFO, "Navigating to Watch Page");
	homepagev2.tabClick("Watch");
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
	if(trayNameParameter.equals("featuredMovies")) {
	trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
	}
	}
	} 
	}	
	trayTitle=trayTitle.toUpperCase();

	//Get show name from API
	String apiName="", mediaid="",mediatype="";
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
	String allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
	//Verify if ALL CHARACTERS section is displayed
	boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
	boolean presence=false;
	 if(trayPresence==true) {
		 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
		 for(int scroll=0;scroll<=1;scroll++) {
			 try {
				 driver.findElement(By.xpath(allCharFirstItem)).click();
				 test.log(LogStatus.INFO, "Clicked on movie content");	
				 presence=true;
				 break;
			   }
			 catch(Exception e) {
				 Utilities.verticalSwipe(driver);
				 if(scroll==1)
					 test.log(LogStatus.INFO, "Failed to click on movie content");	
			 }
		 }
	

    String moviename="";

	if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageShowTitle , 30))
	{
		moviename= showspagev2.showDetailPageShowTitle.getText().trim().toLowerCase();
	}
	else
	BasePageV2.reportFail("Play Button is not displayed in Show Detail page");
	test.log(LogStatus.INFO, "Navigated to the Movie program info page-"+moviename);
	
	//cHECK FOR PLAY BUTTON
	
	if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPagePlayButton , 30))
	{
		 showspagev2.showDetailPagePlayButton.click();
	}
	else
	BasePageV2.reportFail("Play Button is not displayed in Show Detail page");
	homepagev2.verifyAndProgressBar();
	playerpagev2.pauseVideo();
	
	//cHECK FOR Show title
	if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.playerCloseButton, 30))
	{
		test.log(LogStatus.PASS,"Testcase : 'Verify the navigation by tapping on Play button from Movie Info Page' is passed");
	    if(!Utilities.setResultsKids("VK_2197", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
		playerpagev2.playerCloseButton.click();
	}
	else
	{
		  if(!Utilities.setResultsKids("VK_2197", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
		BasePageV2.reportFail("Not able to click Player close button / player is not launched");
	}

	//Check for character image
	if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageCharacterImage , 30))
	{
	 showspagev2.showDetailPageCharacterImage.click();	
	}
	else
	BasePageV2.reportFail("Character Image is not displayed in Show Detail page");
		 
	homepagev2.verifyAndProgressBar();
	playerpagev2.pauseVideo();
	
	//Check FOR Show title
	if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.playerCloseButton, 30))
	{
		test.log(LogStatus.PASS,"Testcase : 'Verify the functionality when tapping on movie image from movie progamme info:' is passed");
	    if(!Utilities.setResultsKids("VK_2016", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
		playerpagev2.playerCloseButton.click();
	}
	else
	{
		if(!Utilities.setResultsKids("VK_2016", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
		BasePageV2.reportFail("Not able to click Player close button / player is not launched");
	}
	
	
	if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconShowDetails, 30))
	{
		
		if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIcon, 30))
		{
			showspagev2.favIcon.click();
			Thread.sleep(3000);
		}
		if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
		{
			if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
				test.log(LogStatus.INFO, "Pop up message is displayed");
			else
				test.log(LogStatus.FAIL, "Pop up message is not displayed");
			if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
			{
				showspagev2.favIconPopupOkButton.click();
				Thread.sleep(3000);
				test.log(LogStatus.PASS, "Testcase : 'Verify the functionality when user taps on Favorite icon from movie programme info for the 1st time:' is Passed");
				  if(!Utilities.setResultsKids("VK_2314", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconShowDetails, 30))
					{
						test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for a movie card:' is Passed");
						if(!Utilities.setResultsKids("VK_2315", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
					}
					else
					{
						test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
						if(!Utilities.setResultsKids("VK_2315", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
					}
			}
			else
			{
				test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting movie content for first time");
				  if(!Utilities.setResultsKids("VK_2314", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
			}
			
		}
		else
		{
			  if(!Utilities.setResultsKids("VK_2314", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
			test.log(LogStatus.FAIL, "Pop up is not displayed after favoriting movie content for first time");
		}
		
	
		
		if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconChecked, 30))
		{
			
			test.log(LogStatus.INFO, "Favourite icon gets filled after favoriting");
			Thread.sleep(3000);
		}
		else
		{
			test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
		}
	}
	else
	{
		test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
	}
	
	driver.pressKeyCode(AndroidKeyCode.BACK);
	Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	homepagev2.tabClick("My Stuff");
	
	Utilities.verticalSwipe(driver, homepagev2.favouritesTray, "visible", 70);
	Utilities.verticalSwipe(driver, homepagev2.favouritesTrayFirstContentTitle, "visible", 1);
	String favTitle="";
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.favouritesTrayFirstContentTitle,30))
	{
		favTitle=homepagev2.favouritesTrayFirstContentTitle.getText().trim().toLowerCase();
		if(favTitle.equalsIgnoreCase(moviename))
		{
			test.log(LogStatus.PASS, "Testcase : 'Verify Favorite icon functionality from Movie Info Page' is Passed");
			if(!Utilities.setResultsKids("VK_2018", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
		}
		else
		{
			test.log(LogStatus.FAIL, "Verify Favorite icon functionality from Movie Info page failed");
			BasePageV2.takeScreenshot();
			if(!Utilities.setResultsKids("VK_2018", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
		}
	}
	Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	//click on watch tab	
		test.log(LogStatus.INFO, "Navigating to Watch Page");
		homepagev2.tabClick("Watch");
		Thread.sleep(2000);	

		//Get tray name from API
		 apiTrayName="";
	     uiTrayName="";
		 totalAssets=0;
		 url_kidsCharacters="";
		 api_kidsCharacters="Kids Characters Tray";
		 apiname_kidsCharacters="";
		 trayNameParameter="";
		 trayTitle="";
		 resp_kidsCharacters=null;
		 rows_kidsCharacters=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
		apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
		if(apiname_kidsCharacters.equals(api_kidsCharacters)){
		url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
		Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
		resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
		totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
		for(int i=0;i<totalAssets;i++) {
		trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
		if(trayNameParameter.equals("featuredMovies")) {
		trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
		}
		}
		} 
		}	
		trayTitle=trayTitle.toUpperCase();

		//Get show name from API
		apiName="";
		mediaid="";
		mediatype="";
		 uiName="";
		//Verify from API first item of ALL CHARACTERS
		url_allCharacters="";
		api_allCharacters="All Characters";
		apiname_allCharacters="";
		resp_allCharacters=null;
		rows_allCharacters=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rows_allCharacters;rNum++){
		apiname_allCharacters=xls.getCellData("Api", "API Name", rNum);
		if(apiname_allCharacters.equals(api_allCharacters)){
		url_allCharacters=xls.getCellData("Api", "Url", rNum);
		Map map=BasePageV2.apiparams(2, xls, api_allCharacters);
		resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
		apiName=resp_allCharacters.jsonPath().get("assets.items[0].title");

	  }
	}

		 trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
		 allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
		//Verify if ALL CHARACTERS section is displayed
		 trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
		 presence=false;
		 if(trayPresence==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
			 for(int scroll=0;scroll<=1;scroll++) {
				 try {
					 driver.findElement(By.xpath(allCharFirstItem)).click();
					 test.log(LogStatus.INFO, "Clicked on movie content");	
					 presence=true;
					 break;
				   }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 if(scroll==1)
						 test.log(LogStatus.INFO, "Failed to click on movie content");	
				 }
			 }
		
			 if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconShowDetails, 30))
				{
					if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconChecked, 30))
					{
						Thread.sleep(3000);
						showspagev2.favIconChecked.click();
						
						if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopup, 5))
						{
							test.log(LogStatus.FAIL, "Pop up for favorite displayed when user taps on Favorite icon from movie programme info  2nd time onwards");
							if(!Utilities.setResultsKids("VK_2316", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
						}
						else
						{
							test.log(LogStatus.PASS, "Testcase : 'Verify the functionality when user taps on Favorite icon from movie programme info from 2nd time onwards:' is Passed");
							if(!Utilities.setResultsKids("VK_2316", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
					}
					
				}
				else
				{
					test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
				}
			 
			  driver.pressKeyCode(AndroidKeyCode.BACK);
				Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
				homepagev2.tabClick("My Stuff");
				
				Utilities.verticalSwipe(driver, homepagev2.favouritesTray, "visible", 70);
				Utilities.verticalSwipe(driver, homepagev2.favouritesTrayFirstContentTitle, "visible", 1);
				
				if(Utilities.explicitWaitVisibleNew(driver, homepagev2.favouritesTrayFirstContentTitle,30))
				{
					favTitle=homepagev2.favouritesTrayFirstContentTitle.getText().trim().toLowerCase();
					if(favTitle.equalsIgnoreCase(moviename))
					{
						test.log(LogStatus.FAIL, "'Verify Favorite icon functionality from Movie Info Page' failed");
						BasePageV2.takeScreenshot();
						if(!Utilities.setResultsKids("VK_2021", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
					}
					else
					{
						test.log(LogStatus.PASS, "Testcase : 'Verify Favorite icon functionality from Movie Info Page' is Passed");	
						if(!Utilities.setResultsKids("VK_2021", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
					}
				}
				else
				{
					if(!Utilities.setResultsKids("VK_2021", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					test.log(LogStatus.PASS, "Testcase : 'Verify Favorite icon functionality from Movie Info Page' is Passed");
				}
			 

		 }
		}
	}
	
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
