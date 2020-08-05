package com.viacom.favourites;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyFavouritesTrayAndIcon extends BaseTestV2{
	
	String testName = "VerifyFavouritesTrayAndIcon";
	@Test(dataProvider = "getData")
	public void VerifyFavouritesTrayAndIcon(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyFavouritesTrayAndIcon");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// VK_191 Verify the availability of favourites tray when user is not favourited any episode/book/audiobook
		// VK_192 Verify the availibility of favourite icon in show detail page
		// VK_193 Verify the availibility of favourite icon in audio book detail page
		// VK_194 Verify the availibility of favourite icon in episode player
						
	 //Launch Voot kids app
	 launchApp();
	 HomePageV2 homepagev2=new HomePageV2(driver,test);
	 BasePageV2 basepagev2=new BasePageV2(driver,test);
	 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
	 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
	 KidsPlayerPageV2 kidsplayerpagev2=new KidsPlayerPageV2(driver,test);
	 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
	 
	 homepagev2.login(data.get("Email"),data.get("Password"));
	 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
		 test.log(LogStatus.INFO, "Application launched successfully");
		 //System.out.println(driver.getPageSource());
	 }
	boolean favTrayPresent=false;
	//Verify Favourites tray availablity
	String favouritesXpath="//android.widget.TextView[@text='FAVOURITES' or @text='Favourites' or @text='favourites']";
	for(int scroll=0;scroll<=15;scroll++) {
		try {
			driver.findElement(By.xpath(favouritesXpath));
			favTrayPresent=true;
			break;
		}
		catch(Exception e) {
			Utilities.verticalSwipe(driver);
			if(scroll==15) {
				test.log(LogStatus.INFO, "Unable to locate Favourites tab since no contents are favourited");
			}
		}
	}
	if(favTrayPresent==true) {
		 test.log(LogStatus.INFO, "FAVOURITES tray is found");
		 test.log(LogStatus.FAIL, "Verify the availability of favourites tray when user is not favourited any episode/book/audiobook is FAIL");
		 if(!Utilities.setResultsKids("VK_191", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 BasePageV2.takeScreenshot();
	}
	else {
		 test.log(LogStatus.INFO, "FAVOURITES tray is not found");
		 test.log(LogStatus.PASS, "Verify the availability of favourites tray when user is not favourited any episode/book/audiobook is PASS");
		 if(!Utilities.setResultsKids("VK_191", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	}

	Utilities.verticalSwipeReverse(driver);
	Utilities.verticalSwipeReverse(driver);
	//Click on Watch tab
	homepagev2.tabClick("Watch");	 
	//Show details
	String firstCharLocator="//android.widget.TextView[@text='TOP TOONS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView";
	boolean charPresence=Utilities.verticalSwipeAndFind(driver,firstCharLocator);
	if(Utilities.explicitWaitClickable(driver, watchpagev2.watchCharactersText1.get(0), 5)) {
		 watchpagev2.watchCharactersText1.get(0).click(); 
		 test.log(LogStatus.INFO, "Clicked on the first Character from Characters tray");  
		 //Verify the presence of Favorite icon
		 if(Utilities.explicitWaitVisible(driver, showpagev2.favIconShowDetails, 3)) {
			 test.log(LogStatus.INFO, "Favourites icon is displayed in Show Details page");
			 test.log(LogStatus.FAIL, "Verify the availibility of favourite icon in show detail page is FAIL");
			 if(!Utilities.setResultsKids("VK_192", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 BasePageV2.takeScreenshot();
		}
		else {
			 test.log(LogStatus.INFO, "Favourites icon is not displayed in Show Details page");
			 test.log(LogStatus.PASS, "Verify the availibility of favourite icon in show detail page is PASS");	
			 if(!Utilities.setResultsKids("VK_192", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

	}
	else {
		test.log(LogStatus.FAIL,"Unable to click on the first Character from the ALL CHARACTERS tray");  
	}
		 

	driver.navigate().back();
	Utilities.verticalSwipeReverse(driver);
	Utilities.verticalSwipeReverse(driver);
	//Click on Audio tab
	homepagev2.tabClick("Listen");
	boolean audiodetails=false;
	for(int i=0;i<=5;i++) {
		if(Utilities.explicitWaitClickable(driver, listenpagev2.audioFromTray, 1)) {
			try {
				listenpagev2.audioFromTray.click();
				test.log(LogStatus.INFO, "Clicked on an Audio from Listen tab");
				audiodetails=true;
				break;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				if(i==5) {
					test.log(LogStatus.FAIL, "Failed to click on an Audio from Listen tab");
				}
			}
		}
	}
	if(audiodetails==true) {
		//Verify the presence of Favorite icon
		 if(!Utilities.explicitWaitVisible(driver, readpagev2.favIconBookDetails, 3)) {
			 test.log(LogStatus.INFO, "Favourites icon is not displayed in Audio details page");
			 test.log(LogStatus.FAIL, "Verify the availibility of favourite icon in audio book detail page is FAIL");
			 if(!Utilities.setResultsKids("VK_193", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 BasePageV2.takeScreenshot();
		}
		else {
			test.log(LogStatus.INFO, "Favourites icon is displayed in Audio details page");
			test.log(LogStatus.PASS, "Verify the availibility of favourite icon in audio book detail page is PASS");
			if(!Utilities.setResultsKids("VK_193", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
	}
	driver.navigate().back();
	homepagev2.tabClick("Watch");
	//Click on the first carousal item
	if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
		watchpagev2.firstItemInCarousal.click();
		test.log(LogStatus.INFO, "Clicked on first item in Watch Carousal");
		homepagev2.verifyAndProgressBar();
		try {
			kidsplayerpagev2.pauseVideo();
			if(Utilities.explicitWaitVisible(driver,watchpagev2.vodPlayerFavourite,20)) {
				 test.log(LogStatus.INFO, "Located Favourites icon in VOD player");
				 test.log(LogStatus.PASS, "Verify the availibility of favourite icon in episode player is PASS");
				 if(!Utilities.setResultsKids("VK_194", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 }
			 else {
				test.log(LogStatus.FAIL, "Failed to locate Favourite icon in VOD Player"); 
				test.log(LogStatus.FAIL, "Verify the availibility of favourite icon in episode player is FAIL");
				if(!Utilities.setResultsKids("VK_194", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				BasePageV2.takeScreenshot();
			 }
		}
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to pause video");
		}
	}
			 
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
