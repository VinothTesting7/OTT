package com.viacom.chromecast;

import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.MoviesPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//Author Tanisha
public class VerifyChromecastMovie extends BaseTestV2{
	
	String testName = "VerifyChromecastMovie";
	@Test(dataProvider = "getData")
	public void VerifyChromecastMovie(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyChromecastMovie");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// P1 VK_2053 Verify Chromecast icon availability for Movie Player
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 MoviesPageV2 moviespagev2=new MoviesPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);


		 String trayName="";
		 String trayNameUpper="";
		 String trayNameCamel="";
		 String movieTitle1="";
		 
		 boolean locatedTray=false;
		 boolean movieFirstItemClicked=false;
		 boolean playButtonClickedMovie1=false;
		 
		 
		 if(Utilities.explicitWaitVisibleNew(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 
		//Hit Watch API and find the name of the Movies tray
		RestAssured.baseURI="https://api.vootkids.com/app/ui/v1/tabs/watch.json?limit=10&offSet=0";
		Response watchTabResponse =(Response) RestAssured.given().relaxedHTTPSValidation().get().body();
		int responseItems=watchTabResponse.jsonPath().get("assets.size()");
		boolean foundMoviesTrayFromAPI=false;
		for(int traverseResponse=0;traverseResponse<responseItems;traverseResponse++) {
			if(watchTabResponse.jsonPath().get("assets["+traverseResponse+"].trayName").equals("featuredMovies")) {
				trayName=watchTabResponse.jsonPath().get("assets["+traverseResponse+"].title");
				test.log(LogStatus.INFO, "Movie tray name fetched from Watch API is: \""+trayName+"\"");
				foundMoviesTrayFromAPI=true;
				break;
			}		
		}
		if(foundMoviesTrayFromAPI==false) {
			test.log(LogStatus.INFO, "Failed to fetch Movie tray name from Watch API");
		}
			
		if(foundMoviesTrayFromAPI==true) {
			homepagev2.tabClick("Watch");
			trayNameUpper=trayName.toUpperCase();
			trayNameCamel=homepagev2.convertCamelCase(trayName);
			String tray="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']";
			for(int scroll=0;scroll<=50;scroll++) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(tray));
					test.log(LogStatus.INFO, "Scrolled to tray: "+trayName);
					locatedTray=true;
					break;
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					if(scroll==50) {
						test.log(LogStatus.FAIL, "Failed to locate tray: "+trayName);
						homepagev2.takeScreenshot();
					}
				}
			}
			if(locatedTray==true) {
				Utilities.verticalSwipe(driver);
				String movieFirstItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='0']";
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(movieFirstItem)).click();
					test.log(LogStatus.INFO, "Clicked on first movie");
					movieFirstItemClicked=true;
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to click on first movie");
					
				}
			}
			if(movieFirstItemClicked==true) {
				//Get the name of the movie
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieTitle, 10)) {
					movieTitle1= moviespagev2.movieDetailsPageMovieTitle.getAttribute("text");
					test.log(LogStatus.INFO, "First Movie title: \""+movieTitle1+"\"");	
					movieTitle1=homepagev2.convertCamelCase(movieTitle1);
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate First Movie title"); 
				}
				Utilities.verticalSwipeReverse(driver);
				//Tap on Play button
				if(Utilities.explicitWaitClickableNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
					try {
						moviespagev2.movieDetailsPagePlayButton.click();
						test.log(LogStatus.INFO, "Tapped on PLAY button");
						playButtonClickedMovie1=true;
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to tap on PLAY button");
					}
				}
			}		
			if(playButtonClickedMovie1==true) {
				try {
					homepagev2.verifyAndProgressBar();
					test.log(LogStatus.INFO, "First Movie started to play");
					kidsplayerv2.pauseVideo();
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "First Movie play failed");
				}
				if(Utilities.explicitWaitVisibleNew(driver, homepagev2.chromecastIconDisconnected, 10)){
					test.log(LogStatus.INFO, "Located Chromecast icon in Movie Player");
					test.log(LogStatus.PASS, "Verify Chromecast icon availability for Movie Player is Pass");
					if(!Utilities.setResultsKids("VK_2053", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				else {
					test.log(LogStatus.INFO, "Failed to locate Chromecast icon in Movie Player");
					test.log(LogStatus.FAIL, "Verify Chromecast icon availability for Movie Player is Fail");
					if(!Utilities.setResultsKids("VK_2053", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
				}
			}
		}
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
