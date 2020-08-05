package com.viacom.movies;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
import com.viacom.pagesVersion2.MoviesPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionState;

// Author - Suresh

public class MoviesCardNavigation_P1 extends BaseTestV2{
	
String testName = "MoviesCardNavigation_P1";
	
	String trayName="";
	String trayNameUpper="";
	String trayNameCamel="";
	String movieTitle="" , episodefirsttitle = "",subtitletext2="";
	@Test(dataProvider = "getData")
	public void moviesCardNavigation_P1(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("MoviesCardNavigation_P1");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Forward/Rewind button functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	MoviesPageV2 moviespagev2 = new MoviesPageV2(driver, test);
	FavouritesPageV2 favPage = new FavouritesPageV2(driver, test);
	DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
	SearchPageV2 search = new SearchPageV2(driver, test);
	
	
	 String timebefPause="";

		String un=data.get("Email");
		String pwd=data.get("Password");
		String movieName = data.get("movieName");
		String movieTray = data.get("trayName");
		homepagev2.login(un, pwd);
	// VK_113 - Verify the tabs present under Tab Bar Menu: - P1	
    // VK_2014 - Verify the navigation by tapping on Movie card from Last Viewed tray:  - P1
	// VK_2016  - Verify the navigation by tapping on Play button from Movie Info Page  - P1
    // VK_2018 - Verify Favorite icon functionality from Movie Info Page  - P1
	// VK_2019 - Verify Favorite icon availability in VIdeo Player - Movie - P1
    // VK_2020 - Verify Favorite icon functionality from Video Player - Movie - P1
	// VK_2021 - Verify Unfavorite icon functionality from Movie Info Page  - P1
    // VK_2012 - Verify the navigation by tapping on Movie card from Curated trays - P1
	// VK_2013 - Verify the navigation by tapping on Movie card from Last Viewed trays - P1
	// VK_2027 - Verify if user is able to delete downloaded Movie content from Edit downlaods screen  - P1
	// VK_2028 - Verify the navigation when tapping on downloaded Movie cards in online mode
	// VK_2029 - Verify the navigation when tapping on downloaded Movie cards in offline mode
    // VK_2046 - Verify the Movie search functionality by giving valid search string
	// VK_2049 - Verify if Movie cards are displayed under 'Recent Searches' tray post accessing searched contents
    // VK_2051 - Verify the search results navigation for Movie card
	// VK_2052 - Verify Movie cards are removed from Recent Searches tray by tapping on 'Clear' link text
		
		
		// VK_113 - Verify the tabs present under Tab Bar Menu: - P1
		int error = 0;
		if(Utilities.explicitWaitVisible(driver, homepagev2.myStuffTexticon, 50)) {
			test.log(LogStatus.INFO, "displayed My Stuff text and icon in home page");
			if(Utilities.explicitWaitVisible(driver, homepagev2.watchTexticon, 50)) {
				test.log(LogStatus.INFO, "displayed Watch text and icon in home page");
				if(Utilities.explicitWaitVisible(driver, homepagev2.ReadTexticon, 50)) {
					test.log(LogStatus.INFO, "displayed Read text and icon in home page");
					if(Utilities.explicitWaitVisible(driver, homepagev2.listenTexticon, 50)) {
						test.log(LogStatus.INFO, "displayed Listen text and icon in home page");
						if(Utilities.explicitWaitVisible(driver, homepagev2.learnTexticon, 50)) {
							test.log(LogStatus.INFO, "displayed Learn text and icon in home page");
							
							if(error == 0) {
								test.log(LogStatus.PASS, "Verify the tabs present under Tab Bar Menu:");
								if(!Utilities.setResultsKids("VK_113", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}else {
								test.log(LogStatus.FAIL, "Verify the tabs present under Tab Bar Menu:");
								if(!Utilities.setResultsKids("VK_113", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
		
						}else {
							error++;
						    test.log(LogStatus.FAIL, "Not displayed in Learn text and icon in home page");
						    BasePageV2.takeScreenshot();
						}
						
					}else {
						error++;
					    test.log(LogStatus.FAIL, "Not displayed in Listen text and icon in home page");
					    BasePageV2.takeScreenshot();
					}
					
				}else {
					error++;
				    test.log(LogStatus.FAIL, "Not displayed in Read text and icon in home page");
				    BasePageV2.takeScreenshot();
				}
				
			}else {
				error++;
			    test.log(LogStatus.FAIL, "Not displayed in Watch text and icon in home page");
			    BasePageV2.takeScreenshot();
			}
			
		}else {
			error++;
		    test.log(LogStatus.FAIL, "Not displayed in Mt Stuff text and icon in home page");
		    BasePageV2.takeScreenshot();
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
			String movieFirstItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup";
			boolean movieFirstItemClicked=false;
			for(int scroll=0;scroll<=50;scroll++) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(movieFirstItem)).click();
					test.log(LogStatus.INFO, "Clicked on first item from \""+trayName+"\" tray");
					movieFirstItemClicked=true;
					break;
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					if(scroll==50) {
						test.log(LogStatus.FAIL, "Failed to click on first item from \""+trayName+"\" tray");
						homepagev2.takeScreenshot();
					}
				}
			}
			if(movieFirstItemClicked==true) {
				Utilities.verticalSwipe(driver);
				if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieTitle, 10)) {
					movieTitle= moviespagev2.movieDetailsPageMovieTitle.getAttribute("text");
					movieTitle = homepagev2.convertCamelCase(movieTitle);
					test.log(LogStatus.INFO, "Movie title: \""+movieTitle+"\"");
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate Movie title"); 
				}			
			}
		}				



	

		//  VK_2012 - Verify the navigation by tapping on Movie card from Curated trays
		
         try {
			
			driver.findElement(By.xpath("//android.widget.TextView[@text='"+movieTitle+"']"));
			test.log(LogStatus.PASS, "Verify the navigation by tapping on Movie card from Curated trays");
			if(!Utilities.setResultsKids("VK_2012", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Verify the navigation by tapping on Movie card from Curated trays");
			if(!Utilities.setResultsKids("VK_2012", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		}
		
		
		
		
     		
		
		
		
		// VK_2016  - Verify the navigation by tapping on Play button from Movie Info Page  - P1 
		
		// Click on play button in movie info page
		  if(Utilities.explicitWaitClickable(driver, watchpagev2.playBtn, 50)) {
			  watchpagev2.playBtn.click();
			  test.log(LogStatus.INFO, "click on play button in Movie info page");
			  Thread.sleep(5000);
		  }else {
			  BasePageV2.reportFail("Not displayed play button in  movie info page / Not click");
		  }

		  int movieError = 0;

		  try {
			  //Check for progress bar
			  homepagev2.verifyAndProgressBar();
			  Thread.sleep(5000);
			   
		} catch (Exception e) {
			movieError++;
		}
		
		  String playerMovietitle = "";
		  int firstDuration = 0;
		  try {
			  playerpagev2.pauseVideo();
			   playerMovietitle = playerpagev2.playerSubTitle.getText().toString().trim();
			   test.log(LogStatus.INFO, "player movie title is :  " + playerMovietitle);
			   
			   String CurrentDuration = playerpagev2.playerCurrentDuration.getText().toString().trim();
				  test.log(LogStatus.INFO, "The current duration of the Movie is  : " + CurrentDuration);
				  String[] arr = CurrentDuration.split(":");
				  int time1 = Integer.parseInt(arr[0]);
				  int time2 = Integer.parseInt(arr[1]);
				   firstDuration = time1+time2;
				   test.log(LogStatus.INFO, "The Current Duration of movie is : " + firstDuration);
				   Thread.sleep(10000);
		} catch (Exception e) {
			BasePageV2.reportFail("Failed to get title from Player");
		}
		  
		
		  
		if(movieTitle.equals(playerMovietitle)) {
		}else {
			movieError++;
		}
		
		if(movieError == 0) {
			test.log(LogStatus.PASS, "Verify the navigation by tapping on Play button from Movie Info Page");
			if(!Utilities.setResultsKids("VK_2016", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			Thread.sleep(10000);
		}else {

			test.log(LogStatus.FAIL, "Verify the navigation by tapping on Play button from Movie Info Page");
			if(!Utilities.setResultsKids("VK_2016", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
			BasePageV2.takeScreenshot();
		}
		
	    driver.pressKeyCode(AndroidKeyCode.BACK);
	    driver.pressKeyCode(AndroidKeyCode.BACK); 
		  
	   
	    // VK_2014 - Verify the navigation by tapping on Movie card from Last Viewed tray:  - P1
	    
	    for(int i = 0 ; i < 30 ; i++) {
	    	if(Utilities.explicitWaitClickable(driver, watchpagev2.lastviewedTray, 10)) {
	    		watchpagev2.lastviewedTray.click();
	    		test.log(LogStatus.INFO, "click on Last viewed tray in watch screen");
	    		// click movie in Last viewed tray 
	    		if(Utilities.explicitWaitClickable(driver, watchpagev2.lastviewedFirstcard, 50)) {
	    			watchpagev2.lastviewedFirstcard.click();
	    			test.log(LogStatus.INFO, "click on first Movie card in Last viewed tray");
	    			
	    			// VK_2013 - Verify the navigation by tapping on Movie card from Last Viewed trays
	    			 try {
	 					
	 					driver.findElement(By.xpath("//android.widget.TextView[@text='"+movieTitle+"']"));
	 					test.log(LogStatus.PASS, "Verify the navigation by tapping on Movie card from Last Viewed trays");
	 					if(!Utilities.setResultsKids("VK_2013", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	 					
	 				} catch (Exception e) {
	 					test.log(LogStatus.FAIL, "Verify the navigation by tapping on Movie card from Last Viewed trays");
	 					if(!Utilities.setResultsKids("VK_2013", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	 					BasePageV2.takeScreenshot();
	 				}
	    			
	    			
	    			
	    			// click on play button
	    			  if(Utilities.explicitWaitClickable(driver, watchpagev2.playBtn, 50)) {
						  watchpagev2.playBtn.click();
						  test.log(LogStatus.INFO, "click on play button in Movie info page");
						  Thread.sleep(10000);
					  }else {
						  BasePageV2.reportFail("Not displayed play button in  movie info page / Not click");
					  }
	    		
	    			  
	    			  try {
						 
					  playerpagev2.pauseVideo();
					} catch (Exception e) {
						
					} 
	    	  
	    			  
	    		}
	    		
	    		break;
	    	}else {
	    		Utilities.verticalSwipe(driver);
	    	}
	    	if(i == 30) {
	    		BasePageV2.reportFail("Not Displayed Last viewed tray in Watch screen / Not click");
	    	}
	    	
	    }
	    
	    int SecondDuration = 0;
	    try {
	    	String CurrentDuration = playerpagev2.playerCurrentDuration.getText().toString().trim();
			  test.log(LogStatus.INFO, "The current duration of the Movie is  : " + CurrentDuration);
			  String[] arr = CurrentDuration.split(":");
			  
			  int time1 = Integer.parseInt(arr[0]);
			  if(time1 == 01) {
				  time1 = 01*60;
			  }
			  int time2 = Integer.parseInt(arr[1]);
			   SecondDuration = time1+time2;   // adding 10 seconds
			   test.log(LogStatus.INFO, "The second Duration of movie is : " + SecondDuration);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    
	    if(SecondDuration > firstDuration ) {
	    	test.log(LogStatus.PASS, "Verify the navigation by tapping on Movie card from Last Viewed tray:");
			if(!Utilities.setResultsKids("VK_2014", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}else {

			test.log(LogStatus.FAIL, "Verify the navigation by tapping on Movie card from Last Viewed tray:");
			if(!Utilities.setResultsKids("VK_2014", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
			BasePageV2.takeScreenshot();
		}
	    
	    driver.pressKeyCode(AndroidKeyCode.BACK);
	    driver.pressKeyCode(AndroidKeyCode.BACK);
	    driver.pressKeyCode(AndroidKeyCode.BACK);
	    
	    for(int r = 0 ; r < 20 ; r++) {
	    	Utilities.verticalSwipeReverse(driver);
	    }
	    
	    // click on read Tab 
	    homepagev2.tabClick("Read");

	    // click on Movie First card
	    homepagev2.tabClick("Watch");
		trayNameUpper=trayName.toUpperCase();
		trayNameCamel=homepagev2.convertCamelCase(trayName);
		String movieFirstItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup";
		boolean movieFirstItemClicked=false;
		for(int scroll=0;scroll<=50;scroll++) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(movieFirstItem)).click();
				test.log(LogStatus.INFO, "Clicked on first item from \""+trayName+"\" tray");
				movieFirstItemClicked=true;
				break;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				if(scroll==50) {
					test.log(LogStatus.FAIL, "Failed to click on first item from \""+trayName+"\" tray");
					homepagev2.takeScreenshot();
				}
			}
		}
		if(movieFirstItemClicked==true) {
			Utilities.verticalSwipe(driver);
			if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieTitle, 10)) {
				movieTitle= moviespagev2.movieDetailsPageMovieTitle.getAttribute("text");
				movieTitle = homepagev2.convertCamelCase(movieTitle);
				test.log(LogStatus.INFO, "Movie title: \""+movieTitle+"\"");
			}
			else {
				test.log(LogStatus.FAIL, "Failed to locate Movie title"); 
			}			
		}

		
		 // VK_2018 - Verify Favorite icon functionality from Movie Info Page  

		// click on favourite icon 
		
		if(moviespagev2.movieDetailsPageFavouriteIcon.getAttribute("checked").equals("true")) {
			test.log(LogStatus.INFO, "selected movie favourite icon");
		}else if(moviespagev2.movieDetailsPageFavouriteIcon.getAttribute("checked").equals("false")) {
			moviespagev2.movieDetailsPageFavouriteIcon.click();
			test.log(LogStatus.INFO, "click on Favourite icon in movie info page");
		}
		
		
		// VK_2019 - Verify Favorite icon availability in VIdeo Player - Movie - P1
		// click on play button
		    if(Utilities.explicitWaitClickable(driver, watchpagev2.playBtn, 50)) {
			  watchpagev2.playBtn.click();
			  test.log(LogStatus.INFO, "click on play button in Movie info page");
			  Thread.sleep(10000);
		  }else {
			  BasePageV2.reportFail("Not displayed play button in  movie info page / Not click");
		  }
		
		try {
		    homepagev2.verifyAndProgressBar();
			playerpagev2.pauseVideo();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoriteButton, 30)) {
			test.log(LogStatus.PASS, "Verify Favorite icon availability in VIdeo Player - Movie");
			if(!Utilities.setResultsKids("VK_2019", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
		} else {
			test.log(LogStatus.FAIL, "Verify Favorite icon availability in VIdeo Player - Movie");
			if(!Utilities.setResultsKids("VK_2019", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		}
		
		// VK_2020 - Verify Favorite icon functionality from Video Player - Movie 

		if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoriteButton, 30)) {
			test.log(LogStatus.PASS, "Verify Favorite icon functionality from Video Player");
			if(!Utilities.setResultsKids("VK_2020", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
		} else {
			test.log(LogStatus.FAIL, "Verify Favorite icon functionality from Video Player");
			if(!Utilities.setResultsKids("VK_2020", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		}
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		
		// scroll to top of the Tabs in home page
		for(int i = 0 ; i < 15 ; i ++) {
			Utilities.verticalSwipeReverse(driver);
		}
		
		// click on My-stuff tab
		homepagev2.tabClick("My Stuff");
		
		try {
			String xpath = "//android.widget.TextView[@text='FAVOURITES' or @text='Favourities' and @resource-id='com.viacom18.vootkids:id/recent_title_text']";
			Utilities.verticalSwipe(driver, xpath);
			if(Utilities.explicitWaitClickable(driver, favPage.favFavoritesTitle1, 60)) {
				favPage.favFavoritesTitle1.click();
				// click on Movie tab in favorite page
				if(Utilities.explicitWaitClickable(driver, moviespagev2.favMovieTab, 60)) {
					moviespagev2.favMovieTab.click();
					test.log(LogStatus.INFO, "Click on Movie Tab in favourite page");
				}else BasePageV2.reportFail("Not displayed Movie tab in favourite screen / Not click ");
				
			}else {
				BasePageV2.reportFail("Failed to click on favourite tray tile");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			
			driver.findElement(By.xpath("//android.widget.TextView[@text='"+movieTitle+"']"));
			test.log(LogStatus.PASS, "Verify Favorite icon functionality from Movie Info Page");
			if(!Utilities.setResultsKids("VK_2018", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Verify Favorite icon functionality from Movie Info Page");
			if(!Utilities.setResultsKids("VK_2018", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		}
		
	// VK_2021 - Verify Unfavorite icon functionality from Movie Info Page
		
		// click on Edit Link in Favourite screen

		if(Utilities.explicitWaitClickable(driver, moviespagev2.editLinkFav, 60)) {
			moviespagev2.editLinkFav.click();
			test.log(LogStatus.INFO, "click on Edit link in favourite page");
		}else BasePageV2.reportFail("Not displayed edit link in favourite page");
		
		for(int i = 0 ; i < 10 ; i++) {
			if(Utilities.explicitWaitClickable(driver, moviespagev2.unfavIcon, 20)) {
				moviespagev2.unfavIcon.click();
				// click on Un favourite button
				if(Utilities.explicitWaitClickable(driver, favPage.unfavbtn, 20)) {
					favPage.unfavbtn.click();
					test.log(LogStatus.INFO, "click on un favourite button ");
					Thread.sleep(10000);
				}
				
				try {
					moviespagev2.favBooksTab.click();
					Thread.sleep(200);
					moviespagev2.favMovieTab.click();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}else {
				break;
			}
			
		}
		
		
       try {
			
			driver.findElement(By.xpath("//android.widget.TextView[@text='"+movieTitle+"']"));
			test.log(LogStatus.FAIL, "Verify Favorite icon functionality from Movie Info Page");
			if(!Utilities.setResultsKids("VK_2021", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		} catch (Exception e) {
			test.log(LogStatus.PASS, "Verify Favorite icon functionality from Movie Info Page");
			if(!Utilities.setResultsKids("VK_2021", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			
		}
		
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		
		// scroll to top of the Tabs in home page
		for(int i = 0 ; i < 15 ; i ++) {
			Utilities.verticalSwipeReverse(driver);
		}
	
		
// VK_2027 - Verify if user is able to delete downloaded Movie content from Edit downlaods screen
		
		// download The Movie card
		downloadsPage.movieDownload();
		
		try {
			
			downloadsPage.deleteAllDownloadsAndClickMyStuffTab();
			test.log(LogStatus.INFO, "deleted complete downloads content");
			if(Utilities.explicitWaitVisible(driver, homepagev2.carousalCard, 20)) {
				test.log(LogStatus.PASS, "Verify if user is able to delete downloaded Movie content from Edit downlaods screen");
				if(!Utilities.setResultsKids("VK_2027", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			} else {
				test.log(LogStatus.FAIL, "Verify if user is able to delete downloaded Movie content from Edit downlaods screen");
				if(!Utilities.setResultsKids("VK_2027", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
		
// VK_2028 - Verify the navigation when tapping on downloaded Movie cards in online mode		
		
		// download The Movie card
		downloadsPage.movieDownload();
		
		try {
			// click on My Stuff tab in home page
			homepagev2.tabClick("My Stuff");
			Thread.sleep(20000);
			// scroll till downloads section
			String downloads = "//android.widget.TextView[contains(@text,'Downloads')]";
			Utilities.verticalSwipe(driver, downloads);
			if(Utilities.explicitWaitClickable(driver, downloadsPage.downloadsTabMystuffpage, 50)) {
				downloadsPage.downloadsTabMystuffpage.click();
				Thread.sleep(10000);
				Utilities.verticalSwipe(driver);
				Utilities.verticalSwipe(driver);
				if(Utilities.explicitWaitClickable(driver, downloadsPage.contentdownloaded, 50)) {
					downloadsPage.contentdownloaded.click();
					test.log(LogStatus.INFO, "click on downloaded content in Downloads Tab");
					try {
						homepagev2.verifyAndProgressBar();
						driver.pressKeyCode(AndroidKeyCode.BACK);
						test.log(LogStatus.PASS, "Verify the navigation when tapping on downloaded Movie cards in online mode");
						if(!Utilities.setResultsKids("VK_2028", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Verify the navigation when tapping on downloaded Movie cards in online mode");
						if(!Utilities.setResultsKids("VK_2028", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						
					}
					
				}
				
				
				
			}else {
				BasePageV2.reportFail("Failed to click on downloads tab in My Stuff page");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// VK_2029 - Verify the navigation when tapping on downloaded Movie cards in offline mode
		
		
		// Turn Off the Wifi 
		 driver.setConnection(new ConnectionState(0));
		
		 Thread.sleep(5000);
		 
		if(Utilities.explicitWaitClickable(driver, downloadsPage.contentdownloaded, 50)) {
			downloadsPage.contentdownloaded.click();
			test.log(LogStatus.INFO, "click on downloaded content in Downloads Tab");
			try {
				homepagev2.verifyAndProgressBar();
				driver.pressKeyCode(AndroidKeyCode.BACK);
				
				test.log(LogStatus.PASS, "Verify the navigation when tapping on downloaded Movie cards in offline mode");
				if(!Utilities.setResultsKids("VK_2029", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				// click on My Stuff Button in My Stuff page
				if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
					downloadsPage.myStuffTabMystuffpage.click();
					for(int i = 0 ; i < 15 ; i ++) {
						Utilities.verticalSwipeReverse(driver);
					}
				}else {
					test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
				}
				
		
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Verify the navigation when tapping on downloaded Movie cards in offline mode");
				if(!Utilities.setResultsKids("VK_2029", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				// click on My Stuff Button in My Stuff page
				if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
					downloadsPage.myStuffTabMystuffpage.click();
					for(int i = 0 ; i < 15 ; i ++) {
						Utilities.verticalSwipeReverse(driver);
					}
				}else {
					test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
				}
			}
			
		}
		
		// Turn on the Wifi 
		 driver.setConnection(new ConnectionState(6));
		 Thread.sleep(5000);
		
		
		// clear Downlods 
		downloadsPage.deleteAllDownloadsAndClickMyStuffTab();
		
		
		// VK_2046 - Verify the Movie search functionality by giving valid search string	

// click on Serach icon in home page
if(Utilities.explicitWaitClickable(driver, search.Search, 50)) {
	search.Search.click();
	test.log(LogStatus.INFO, "click on Search icon in home page");
	if(Utilities.explicitWaitVisible(driver, search.searchTextBox, 50)) {
		search.searchTextBox.click();
		search.searchTextBox.sendKeys(movieName);
		Thread.sleep(200);
		driver.hideKeyboard();
	}else {
		BasePageV2.reportFail("Not displayed search Edit text box in search page / Not click");
	}
}else {
	BasePageV2.reportFail("Not able to send Movie Title to search Edit text box / Not found");
}

try {
	driver.findElement(By.xpath("//android.widget.TextView[@text='"+movieName+"']"));
	
	test.log(LogStatus.PASS, "Verify the Movie search functionality by giving valid search string");
	if(!Utilities.setResultsKids("VK_2046", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	
} catch (Exception e) {
	test.log(LogStatus.FAIL, "Verify the Movie search functionality by giving valid search string");
	if(!Utilities.setResultsKids("VK_2046", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	
}

// VK_2049 - Verify if Movie cards are displayed under 'Recent Searches' tray post accessing searched contents 

if(Utilities.explicitWaitClickable(driver, search.searchMovieCard, 50)) {
	search.searchMovieCard.click();
	test.log(LogStatus.INFO, "click on search movie card in search page");
	// click on play button
	  if(Utilities.explicitWaitClickable(driver, watchpagev2.playBtn, 50)) {
		  watchpagev2.playBtn.click();
		  test.log(LogStatus.INFO, "click on play button in Movie info page");
		  Thread.sleep(10000);
		  //VK_2051 - Verify the search results navigation for Movie card
		    test.log(LogStatus.PASS, "Verify the search results navigation for Movie card");
			if(!Utilities.setResultsKids("VK_2051", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	  }else {
		    test.log(LogStatus.FAIL, "Verify the search results navigation for Movie card");
			if(!Utilities.setResultsKids("VK_2051", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		  BasePageV2.reportFail("Not displayed play button in  movie info page / Not click");
	  }

	try {
		homepagev2.verifyAndProgressBar();
		Thread.sleep(5000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(5000);
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	// click on search icon 
	String recentTrayTile = "";
	if(Utilities.explicitWaitClickable(driver, search.Search, 50)) {
		search.Search.click();
		test.log(LogStatus.INFO, "click on Search icon in home page");
		Thread.sleep(5000);
		String trayMovieUpper=movieTray.toUpperCase();
		String trayNameCamel=homepagev2.convertCamelCase(movieTray);
		try {
			recentTrayTile="//android.widget.TextView[@text='"+movieTray+"' or @text='"+trayMovieUpper+"' or @text='"+trayNameCamel+"' and @resource-id='com.viacom18.vootkids:id/recent_title_text']";
			 Utilities.verticalSwipe(driver, recentTrayTile);
			 driver.findElement(By.xpath(recentTrayTile)).click();
				Thread.sleep(200);
				test.log(LogStatus.INFO, "click on Recent Search tray in search page");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
			try {
			
				List<WebElement> MovieLi = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']"));
				String movieTitle = MovieLi.get(0).getText().toString().trim();
				test.log(LogStatus.INFO, "Recent search Tray movie card title  : " + movieTitle);
				if(movieTitle.contains("Chhota Bheem")) {
					test.log(LogStatus.PASS, "Verify if Movie cards are displayed under 'Recent Searches' tray post accessing searched contents");
					if(!Utilities.setResultsKids("VK_2049", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					
				} else {
					test.log(LogStatus.FAIL, "Verify if Movie cards are displayed under 'Recent Searches' tray post accessing searched contents");
					if(!Utilities.setResultsKids("VK_2049", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
				}
				
				driver.pressKeyCode(AndroidKeyCode.BACK);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
	
		
	}else {
		BasePageV2.reportFail("Not able to send Movie Title to search Edit text box / Not found");
	}
	
	
}else {
	BasePageV2.reportFail("Not displayed search movie card in serach screen / Not click");
}
		
	
 // VK_2052 - Verify Movie cards are removed from Recent Searches tray by tapping on 'Clear' link text

  if(Utilities.explicitWaitClickable(driver, search.recentSearchClearBtn, 50)) {
	  search.recentSearchClearBtn.click();
	  test.log(LogStatus.INFO, "click on Recent Search tray clear Link");
	  Thread.sleep(5000);
	  if(Utilities.explicitWaitClickable(driver, search.recentSearchClearBtn, 50)) {
		    test.log(LogStatus.FAIL, "Verify Movie cards are removed from Recent Searches tray by tapping on 'Clear' link text");
			if(!Utilities.setResultsKids("VK_2052", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	  }else {
		    test.log(LogStatus.PASS, "Verify Movie cards are removed from Recent Searches tray by tapping on 'Clear' link text");
			if(!Utilities.setResultsKids("VK_2052", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	  }
	  
  }else {
	  BasePageV2.reportFail("Not displayed Recent Search Tray clear Link in search page");
  }







    
	  }
	  @DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					

		}		  
				  

}
