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
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author Tanisha
public class VerifyLastViewedMoviesMetaData extends BaseTestV2{
	
	String testName = "VerifyLastViewedMoviesMetaData";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedMoviesMetaData(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedMoviesMetaData");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// P2 VK_2006 Verify Movie card metadata in My stuff - Last viewed tray

		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 MoviesPageV2 moviespagev2=new MoviesPageV2(driver,test);
		 SearchPageV2 searchpagev2=new SearchPageV2(driver,test);
		 boolean moviePlayClicked=false;
		 boolean verifyLV=false;
		 boolean searchDone=false;
		 int err2006=0;
		 //Login module 
		 //homepagev2.logout();
		 //homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisibleNew(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 //Call API
		 // Mighty Raju- ice ice mightly 764386
		 // Dus Pe Dus 
		 //Hit Movie Programe Info API fr movie Mighty Raju- ice ice mightly
		 String url="https://api.vootkids.com/app/programInfo/v1/details.json?mediaTypeId=414&mediaId=764386";
		 //Response movieInfoResponse = RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).headers("Content-Type", "application/json").get(url); //This works
		 Response movieInfoResponse = RestAssured.given().relaxedHTTPSValidation().get(url); //This works too
		 
		 
		 test.log(LogStatus.INFO, "-------------- Fetching details from API ----------------------");
		 String movieTitleAPI=movieInfoResponse.jsonPath().get("assets[0].assets[0].items[0].title");
		 test.log(LogStatus.INFO, "Movie Title from API: "+movieTitleAPI);
		 String movieYearAPI=movieInfoResponse.jsonPath().get("assets[0].assets[0].items[0].yearofRelease");
		 test.log(LogStatus.INFO, "Movie Year of Release from API: "+movieYearAPI);
		 int movieDurationAPImilli=movieInfoResponse.jsonPath().get("assets[0].assets[0].items[0].duration");
		 int movieDurationAPIint=(int) TimeUnit.MILLISECONDS.toMinutes(movieDurationAPImilli);
		 int hrs=0;
		 int mins=0;
		 String movieDurationAPIString="";
		 if(movieDurationAPIint<60 && movieDurationAPIint>=1) {
			 movieDurationAPIString=movieDurationAPIint+" min";
		 }
		 else if(movieDurationAPIint<1) {
			 movieDurationAPIint=(int) TimeUnit.MILLISECONDS.toSeconds(movieDurationAPImilli);
			 movieDurationAPIString=movieDurationAPIint+" sec";
		 }
		 else if(movieDurationAPIint>=60) {
			 hrs=movieDurationAPIint/60;
			 mins=Math.floorMod(movieDurationAPIint, 60);
			 movieDurationAPIString=hrs+" hr"+mins+" min";
		 }
		 test.log(LogStatus.INFO, "Movie Duration from API: "+movieDurationAPIString);


		 test.log(LogStatus.INFO, "---------------------------------------------------------------");
		 String movieTitleAPIUpper=movieTitleAPI.toUpperCase();
		 String movieTitleAPICamel=homepagev2.convertCamelCase(movieTitleAPI);
		 String uiFavMovieTitleLocator="";

		 if(Utilities.explicitWaitVisibleNew(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }	
		 searchDone=searchpagev2.performSearchAndClickOnFirstSearchResult("Ice Ice Mighty");
		 if(searchDone==true) {
			//Click on Play of Movie
			 if(Utilities.explicitWaitClickableNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
				 moviespagev2.movieDetailsPagePlayButton.click();
				 test.log(LogStatus.INFO, "Tapped on Play button");
				 moviePlayClicked=true;
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to tap on Play button"); 
			 }
		 }
		 if(moviePlayClicked==true) {
			 try {
				 homepagev2.verifyAndProgressBar();
				 test.log(LogStatus.INFO, "Movie started to play");
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Navigated back to close the player");
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Navigated back to movie details page");
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Navigated back to Search page");
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Navigated back to Home page");
				 verifyLV=true;
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "First Movie play failed");
			 }
		 }	 
		 if(verifyLV==true) {
			//Wait for 3 minutes
			for(int min=1;min<=3;min++) {
				for(int sec=0;sec<=60;sec++) {
					Thread.sleep(1000);
				}
				test.log(LogStatus.INFO, "Waited for "+min+" minute");
			}
			Thread.sleep(2000);
			//To prevent driver breakdown---start code
			try {
				driver.findElement(By.xpath("//*[contains(@class,'ActionBar')][@index='0'])"));
			}
			catch(Exception e) {}
			//To prevent driver breakdown---end code
			Utilities.verticalSwipeReverse(driver);
			Utilities.verticalSwipeReverse(driver);
			homepagev2.tabClick("My Stuff");
			homepagev2.tabClick("My Stuff");
			//Scroll till Last Viewed
			String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
			for(int scroll=0;scroll<60;scroll++) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(recentViewedClear));
					test.log(LogStatus.INFO, "Swiped till Last viewed tray in Watch tab");
					Utilities.verticalSwipe(driver);
					Utilities.verticalSwipe(driver);
					break;
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					if(scroll==60) {
						test.log(LogStatus.FAIL, "Failed to locate Last viewed tray in Watch tab");
					}
				}
			}
			Thread.sleep(2000);
			if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvFirstMovieTitle, 10)) {
		    	//Title
				test.log(LogStatus.INFO, "Located Movie title");
	    		try {
	    			String titleUI=moviespagev2.lvFirstMovieTitle.getAttribute("text");
		    		if(titleUI.equals(movieTitleAPICamel)) {
		    			test.log(LogStatus.INFO, "Movie title matching in UI and API: "+movieTitleAPICamel);
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Movie title in UI is: "+titleUI+" and API is: "+movieTitleAPICamel);
		    			err2006++;
		    		}
	    		}
	    		catch(Exception e) {
	    			test.log(LogStatus.FAIL, "Failed to fetch Movie title");
	    			err2006++;
	    		}
	    		//Year
	    		if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvFirstMovieYear, 10)) {
	    			test.log(LogStatus.INFO, "Located Movie Year");
	    			try {
	    				String yearUI=moviespagev2.lvFirstMovieYear.getAttribute("text");
		    			if(yearUI.equals(movieYearAPI)) {
		    				test.log(LogStatus.INFO, "Movie year of release matching in UI and API: "+movieYearAPI);
		    			}
		    			else {
		    				test.log(LogStatus.FAIL, "Movie year of release in UI is: "+yearUI+" and API is: "+movieYearAPI);
		    				err2006++;
		    			}
	    			}
	    			catch(Exception e) {
	    				test.log(LogStatus.FAIL, "Failed to fetch Movie year");
	    				err2006++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate Movie year");
	    			err2006++;
	    		}
	    		//Duration
	    		if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvFirstMovieDuration, 10)) {
	    			test.log(LogStatus.INFO, "Located Movie Duration");
	    			try {
	    				String durationUI=moviespagev2.lvFirstMovieDuration.getAttribute("text");
		    			if(durationUI.equals(movieDurationAPIString)) {
		    				test.log(LogStatus.INFO, "Movie duration matching in UI and API: "+movieDurationAPIString);
		    			}
		    			else {
		    				test.log(LogStatus.FAIL, "Movie duration in UI is: "+durationUI+" and API is: "+movieDurationAPIString);
		    				err2006++;
		    			}
	    			}
	    			catch(Exception e) {
	    				test.log(LogStatus.FAIL, "Failed to fetch Movie year");
	    				err2006++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate Movie year");
	    			err2006++;
	    		}
	    		//Progress bar
	    		if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.lvFirstMovieProgressBar, 10)) {
	    			test.log(LogStatus.INFO, "Located Movie Progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate Movie year");
	    			err2006++;
	    		}	
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed movie is not visible under last viewed in Watch tab");
		    	test.log(LogStatus.FAIL, "Verify Movie card metadata in My stuff - Last viewed tray is Fail");
	    		if(!Utilities.setResultsKids("VK_2006", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	    		basepagev2.takeScreenshot();
		    }
		    
		    if(err2006==0) {
		    	test.log(LogStatus.PASS, "Verify Movie card metadata in My stuff - Last viewed tray is Pass");
		    	if(!Utilities.setResultsKids("VK_2006", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Verify Movie card metadata in My stuff - Last viewed tray is Fail");
		    	if(!Utilities.setResultsKids("VK_2006", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	basepagev2.takeScreenshot();
		    }
		}
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
