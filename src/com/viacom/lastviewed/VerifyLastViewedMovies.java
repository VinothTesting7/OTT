package com.viacom.lastviewed;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author Tanisha
public class VerifyLastViewedMovies extends BaseTestV2{
	
	String testName = "VerifyLastViewedMovies";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedMovies(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedMovies");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// P1 VK_2060 Verify 'Last Viewed' section in My stuff by playing any Movie content
		// P1 VK_2061 Verify if the Movie cards are added to the My stuff - Last viewed tray if user Opens and closes the player
		// P1 VK_2062 Verify if the Movie cards are added to the My stuff - Last viewed tray if user Watches a Movie partially
		// P1 VK_2063 Verify if the Movie cards are added to the My stuff - Last viewed tray if user Watches a Movie Completely
		// P1 VK_2067 Verify 'Last Viewed' section in watch tab by playing any Movie content
		// P1 VK_2068 Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Opens and closes the player
		// P1 VK_2069 Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Watches a Movie partially
		// P1 VK_2070 Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Watches a Movie Completely

		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 MoviesPageV2 moviespagev2=new MoviesPageV2(driver,test);
		 
		 String trayName="";
		 String trayNameUpper="";
		 String trayNameCamel="";
		 String movieTitle1="";
		 String movieTitle2="";
		 String movieTitle3="";
		 String lastViewed1,lastViewed2,lastViewed3="";
		 boolean movieFirstItemClicked=false;
		 boolean movieSecondItemClicked=false;
		 boolean movieThirdItemClicked=false;
		 boolean playButtonClickedMovie1=false;
		 boolean playButtonClickedMovie2=false;
		 boolean playButtonClickedMovie3=false;
		 //Login module 
		 //homepagev2.logout();
		 //homepagev2.login(data.get("Email"),data.get("Password"));
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
			String movieFirstItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView//android.view.ViewGroup[@index='0']";
			for(int scroll=0;scroll<=50;scroll++) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(movieFirstItem)).click();
					test.log(LogStatus.INFO, "Clicked on first movie");
					movieFirstItemClicked=true;
					break;
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					if(scroll==50) {
						test.log(LogStatus.FAIL, "Failed to click on first movie");
						homepagev2.takeScreenshot();
					}
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
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to close the player");
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to tray");
				 }
				 catch(Exception e) {
					 test.log(LogStatus.INFO, "First Movie play failed");
				 }
			}
			//Second Movie
			String movieSecondItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView//android.view.ViewGroup[@index='1']";		
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(movieSecondItem)).click();
				test.log(LogStatus.INFO, "Clicked on second movie");
				movieSecondItemClicked=true;
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on second movie");
				homepagev2.takeScreenshot();
			}		
			if(movieSecondItemClicked==true) {
				//Get the name of the movie
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieTitle, 10)) {
					movieTitle2= moviespagev2.movieDetailsPageMovieTitle.getAttribute("text");
					test.log(LogStatus.INFO, "Second Movie title: \""+movieTitle2+"\"");
					movieTitle2=homepagev2.convertCamelCase(movieTitle2);
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate Second Movie title"); 
				}
				//Tap on Play button
				if(Utilities.explicitWaitClickableNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
					try {
						moviespagev2.movieDetailsPagePlayButton.click();
						test.log(LogStatus.INFO, "Tapped on PLAY button");
						playButtonClickedMovie2=true;
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to tap on PLAY button");
					}
				}
			}		
			if(playButtonClickedMovie2==true) {
				 try {
					 homepagev2.verifyAndProgressBar();
					 test.log(LogStatus.INFO, "Second Movie started to play");
					 kidsplayerv2.pauseVideo();
					 Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);
					 kidsplayerv2.playVideo();
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to close the player");
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to tray");
				 }
				 catch(Exception e) {
					 test.log(LogStatus.INFO, "Second Movie play failed");
				 }
			}	
			//Third Movie
			String movieThirdItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView//android.view.ViewGroup[@index='2']";		
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(movieThirdItem)).click();
				test.log(LogStatus.INFO, "Clicked on third movie");
				movieThirdItemClicked=true;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(movieThirdItem)).click();
					test.log(LogStatus.INFO, "Clicked on third movie");
					movieThirdItemClicked=true;
				}
				catch(Exception e1) {
					test.log(LogStatus.FAIL, "Failed to click on third movie");
					homepagev2.takeScreenshot();
				}
			}		
			if(movieThirdItemClicked==true) {
				//Get the name of the movie
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieTitle, 10)) {
					movieTitle3= moviespagev2.movieDetailsPageMovieTitle.getAttribute("text");
					test.log(LogStatus.INFO, "Third Movie title: \""+movieTitle3+"\"");	
					movieTitle3=homepagev2.convertCamelCase(movieTitle3);
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate Third Movie title"); 
				}
				//Tap on Play button
				if(Utilities.explicitWaitClickableNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
					try {
						moviespagev2.movieDetailsPagePlayButton.click();
						test.log(LogStatus.INFO, "Tapped on PLAY button");
						playButtonClickedMovie3=true;
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to tap on PLAY button");
					}
				}
			}		
			if(playButtonClickedMovie3==true) {
				 try {
					 homepagev2.verifyAndProgressBar();
					 test.log(LogStatus.INFO, "Third Movie started to play");
					 kidsplayerv2.pauseVideo();
					 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
					 kidsplayerv2.playVideo();
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to close the player");
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to tray");
				 }
				 catch(Exception e) {
					 test.log(LogStatus.INFO, "Third Movie play failed");
				 }
			}	
			//Wait for 3 minutes
			/*for(int min=1;min<=3;min++) {
				for(int sec=0;sec<=60;sec++) {
					Thread.sleep(1000);
				}
				test.log(LogStatus.INFO, "Waited for "+min+" minute");
			}*/
			Utilities.verticalSwipeReverse(driver);
			Utilities.verticalSwipeReverse(driver);
			homepagev2.tabClick("Watch");
			homepagev2.tabClick("Watch");
			//Scroll till Last Viewed
			String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
			for(int scroll=0;scroll<60;scroll++) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(recentViewedClear));
					test.log(LogStatus.INFO, "Swiped till Last viewed tray in Watch tab");
					Utilities.verticalSwipe(driver);
					Utilities.verticalSwipe(driver);
					test.log(LogStatus.PASS, "Verify 'Last Viewed' section in watch tab by playing any Movie content is Pass");
					if(!Utilities.setResultsKids("VK_2067", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					break;
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					if(scroll==60) {
						test.log(LogStatus.FAIL, "Failed to locate Last viewed tray in Watch tab");
						test.log(LogStatus.FAIL, "Verify 'Last Viewed' section in watch tab by playing any Movie content is Fail");
						if(!Utilities.setResultsKids("VK_2067", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
				}
			}
		    if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvFirstMovieTitle, 10)) {
		    	lastViewed1=moviespagev2.lvFirstMovieTitle.getAttribute("text");
		    	if(lastViewed1.contains(movieTitle3)) {
		    		test.log(LogStatus.INFO, lastViewed1+" is the first latest movie under last viewed in Watch tab");
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Watches a Movie Completely is Pass");
		    		if(!Utilities.setResultsKids("VK_2070", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest movie under last viewed in Watch tab instead of "+movieTitle3);
		    		test.log(LogStatus.FAIL, "Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Watches a Movie Completely is Fail");
		    		if(!Utilities.setResultsKids("VK_2070", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed movie 1 is not visible under last viewed in Watch tab");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvSecondMovieTitle, 10)) {
		    	lastViewed2=moviespagev2.lvSecondMovieTitle.getAttribute("text");
		    	if(lastViewed2.contains(movieTitle2)) {
		    		test.log(LogStatus.INFO, lastViewed2+" is the second latest movie under last viewed in Watch tab");
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Watches a Movie partially is Pass");
		    		if(!Utilities.setResultsKids("VK_2069", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest movie under last viewed in Watch instead of "+movieTitle2);
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Watches a Movie partially is Fail");
		    		if(!Utilities.setResultsKids("VK_2069", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episide 2 is not visible under last viewed in Watch tab");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvThirdMovieTitle, 10)) {
		    	lastViewed3=moviespagev2.lvThirdMovieTitle.getAttribute("text");
		    	if(lastViewed3.contains(movieTitle1)) {
		    		test.log(LogStatus.INFO, lastViewed3+" is the third latest movie under last viewed in Watch tab");
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Opens and closes the player is Pass");
		    		if(!Utilities.setResultsKids("VK_2068", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest movie under last viewed in Watch tab instead of "+movieTitle1);
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the Watch Tab - Last viewed tray if user Opens and closes the player is Fail");
		    		if(!Utilities.setResultsKids("VK_2068", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed movie 3 is not visible under last viewed in Watch tab");
		    }
		    basepagev2.takeScreenshot();
		    //Verify in My Stuff Last Viewed
		    Utilities.verticalSwipeReverse(driver);
		    Utilities.verticalSwipeReverse(driver);
		    homepagev2.tabClick("My Stuff");
		    homepagev2.tabClick("My Stuff");
		    //Scroll till Last Viewed
			recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
			for(int scroll=0;scroll<60;scroll++) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(recentViewedClear));
					test.log(LogStatus.INFO, "Swiped till Last viewed tray in My Stuff tab");
					Utilities.verticalSwipe(driver);
					Utilities.verticalSwipe(driver);
					test.log(LogStatus.PASS, "Verify 'Last Viewed' section in My stuff by playing any Movie content is Pass");
					if(!Utilities.setResultsKids("VK_2060", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					break;
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					if(scroll==60) {
						test.log(LogStatus.FAIL, "Failed to locate Last viewed tray in My Stuff tab");
						test.log(LogStatus.FAIL, "Verify 'Last Viewed' section in My stuff by playing any Movie content is Fail");
						if(!Utilities.setResultsKids("VK_2060", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
				}
			}
		    if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvFirstMovieTitle, 10)) {
		    	lastViewed1=moviespagev2.lvFirstMovieTitle.getAttribute("text");
		    	if(lastViewed1.contains(movieTitle3)) {
		    		test.log(LogStatus.INFO, lastViewed1+" is the first latest movie under last viewed in My Stuff tab");
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the My stuff - Last viewed tray if user Watches a Movie Completely is Pass");
		    		if(!Utilities.setResultsKids("VK_2063", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest movie under last viewed in My Stuff instead of "+movieTitle3);
		    		test.log(LogStatus.FAIL, "Verify if the Movie cards are added to the My stuff - Last viewed tray if user Watches a Movie Completely is Fail");
		    		if(!Utilities.setResultsKids("VK_2063", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed movie 1 is not visible under last viewed in My Stuff");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvSecondMovieTitle, 10)) {
		    	lastViewed2=moviespagev2.lvSecondMovieTitle.getAttribute("text");
		    	if(lastViewed2.contains(movieTitle2)) {
		    		test.log(LogStatus.INFO, lastViewed2+" is the second latest movie under last viewed in My Stuff tab");
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the My stuff - Last viewed tray if user Watches a Movie partially is Pass");
		    		if(!Utilities.setResultsKids("VK_2062", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest movie under last viewed in My Stuff instead of "+movieTitle2);
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the My stuff - Last viewed tray if user Watches a Movie partially is Fail");
		    		if(!Utilities.setResultsKids("VK_2062", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episide 2 is not visible under last viewed in My Stuff tab");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvThirdMovieTitle, 10)) {
		    	lastViewed3=moviespagev2.lvThirdMovieTitle.getAttribute("text");
		    	if(lastViewed3.contains(movieTitle1)) {
		    		test.log(LogStatus.INFO, lastViewed3+" is the third latest movie under last viewed in My Stuff tab");
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the My stuff - Last viewed tray if user Opens and closes the player is Pass");
		    		if(!Utilities.setResultsKids("VK_2061", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest movie under last viewed in My Stuff tab instead of "+movieTitle1);
		    		test.log(LogStatus.PASS, "Verify if the Movie cards are added to the My stuff - Last viewed tray if user Opens and closes the player is Fail");
		    		if(!Utilities.setResultsKids("VK_2061", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed movie 3 is not visible under last viewed in My Stuff tab");
		    }
		    basepagev2.takeScreenshot();
		}			
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
