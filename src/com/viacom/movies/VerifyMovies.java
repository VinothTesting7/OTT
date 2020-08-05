package com.viacom.movies;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.MoviesPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author Tanisha
public class VerifyMovies extends BaseTestV2{
	
	String testName = "VerifyMovies";
	@Test(dataProvider = "getData")
	public void VerifyMovies(Hashtable<String, String> data) throws Exception 
	{		

		test = rep.startTest("VerifyMovies");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// VK_2009 : Verify Movie Info Page UI 
		// VK_2023 : Verify the behavior by unfavoriting Movie card from Edit favourites screen 
		// VK_2024 : Verify the navigation by tapping on Movie card under 'All Favourites' tray 
		// VK_2025 : Verify Download functionality for Movies 
		// VK_2026 : Verify Cancel Download functionality for Movies when download is in-progress 
		// VK_2030 : Verify in-progress downloads movie cards in device notification drawer
		// VK_2031 : Verify download completed movie cards in device notification drawer
		// VK_2032 : Verify the navigation by tapping on downloaded Movie content notification from device notification drawer
		// VK_2033 : Verify the navigation of Downloaded cards from Downloads dedicated screen
		
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 SearchPageV2 searchpagev2=new SearchPageV2(driver,test);
		 MoviesPageV2 moviespagev2=new MoviesPageV2(driver,test);
		 DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 //homepagev2.login(data.get("Email"),data.get("Password"));
		 
		 //Declare error integers
		 int err2009=0;
		 int err2023=0;
		 int err2025=0;
		 int err2030=0;
		 int err2031=0;
		 int err2032=0;
		 int err2033=0;

		 //Declare boolean checks
		 boolean searchDone=false;
		 boolean favouriteIconClicked=false;
		 boolean downloadButtonClicked=false;
		 boolean positionFavouritesTray=false;
		 

		 //From API
		 boolean movieDownloadEnabled=true;
		 String movieTitleAPI="Ice Ice Mighty";
		 String movieTitleAPIUpper=movieTitleAPI.toUpperCase();
		 String movieTitleAPICamel=homepagev2.convertCamelCase(movieTitleAPI);
		 String movieYearAPI="2017";
		 String movieLangsAPI="Tamil,Hindi,Telugu";
		 String movieDescAPI="When the prince is attacked by the residents of Vedas city, he seeks revenge. He asks the residents to leave the city immediately. When Shiva arrives at the palace to plead to the prince, he smells a conspiracy when he sees one of the attackers in the palace. Can Shiva get to the bottom of this and help the citizens of Vedas City?";
		 String uiFavMovieTitleLocator="";
		 
		 if(Utilities.explicitWaitVisibleNew(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }	
	
		 searchDone=searchpagev2.performSearchAndClickOnFirstSearchResult("Ice Ice Mighty");
		 if(searchDone==true) {
			 test.log(LogStatus.INFO, "********** Verifying Movie Info Page UI **********");
			 //1. Back button on the top left.
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageBackButton, 10)) {
				 test.log(LogStatus.INFO, "Located Back button");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Back button"); 
				 err2009++;
			 }
			 //2. Movie image on center(vertical image)
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieImage, 10)) {
				 test.log(LogStatus.INFO, "Located Movie Image");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Movie Image"); 
				 err2009++;
			 }
			 //3. Favourite icon on top right
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageFavouriteIcon, 10)) {
				 test.log(LogStatus.INFO, "Located Favourite icon");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Favourite icon"); 
				 err2009++;
			 }
			 //4. Play button below the Movie image
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
				 test.log(LogStatus.INFO, "Located Play button");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Play button"); 
				 err2009++;
			 }
			 //5. Movie title below Play button
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieTitle, 10)) {
				 test.log(LogStatus.INFO, "Located Movie title");
				 if(moviespagev2.movieDetailsPageMovieTitle.getAttribute("text").toLowerCase().contains(movieTitleAPI.toLowerCase())) {
					 test.log(LogStatus.INFO, "Movie title displayed: "+moviespagev2.movieDetailsPageMovieTitle.getAttribute("text")); 
				 }
				 else {
					 test.log(LogStatus.FAIL, "Movie title displayed in UI: "+moviespagev2.movieDetailsPageMovieTitle.getAttribute("text")+", Movie title from API: "+movieTitleAPI); 
					 err2009++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Movie title"); 
				 err2009++;
			 }
			 Utilities.verticalSwipe(driver);
			 //6. Year below Movie title
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieYear, 10)) {
				 test.log(LogStatus.INFO, "Located Movie Year");
				 String temp1=moviespagev2.movieDetailsPageMovieYear.getAttribute("text").toLowerCase();
				 String temp2=movieYearAPI.toLowerCase();
				 if(temp1.contains(temp2)) {
					 test.log(LogStatus.INFO, "Movie Year displayed: "+moviespagev2.movieDetailsPageMovieYear.getAttribute("text")); 
				 }
				 else {
					 test.log(LogStatus.FAIL, "Movie Year displayed in UI: "+moviespagev2.movieDetailsPageMovieYear.getAttribute("text")+", Movie Year from API: "+movieYearAPI); 
					 err2009++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Movie Year"); 
				 err2009++;
			 }
			 //7. Languages available
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieLangs, 10)) {
				 test.log(LogStatus.INFO, "Located Movie Languages");
				 String temp1=moviespagev2.movieDetailsPageMovieLangs.getAttribute("text").toLowerCase();
				 String temp2=movieLangsAPI.toLowerCase();
				 if(temp1.contains(temp2)) {
					 test.log(LogStatus.INFO, "Movie Languages displayed: "+moviespagev2.movieDetailsPageMovieLangs.getAttribute("text")); 
				 }
				 else {
					 test.log(LogStatus.FAIL, "Movie Languages displayed in UI: "+moviespagev2.movieDetailsPageMovieLangs.getAttribute("text")+", Movie Languages from API: "+movieLangsAPI); 
					 err2009++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Movie Languages"); 
				 err2009++;
			 }
			 //8. Movie description in grey colour
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageMovieDescription, 10)) {
				 test.log(LogStatus.INFO, "Located Movie description");
				 if(movieDescAPI.toLowerCase().contains(moviespagev2.movieDetailsPageMovieDescription.getAttribute("text").toLowerCase())) {
					 test.log(LogStatus.INFO, "Movie Description displayed: "+moviespagev2.movieDetailsPageMovieDescription.getAttribute("text")); 
				 }
				 else {
					 test.log(LogStatus.FAIL, "Movie Decription displayed in UI: "+moviespagev2.movieDetailsPageMovieDescription.getAttribute("text")+"\n Movie Description from API: "+movieDescAPI); 
					 err2009++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Movie description"); 
				 err2009++;
			 }
			 Utilities.verticalSwipe(driver);
			 //9. 'Download' button
			 if(movieDownloadEnabled==true) {
				 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageDownloadButton, 10)) {
					 test.log(LogStatus.INFO, "Located Download button");
				 }
				 else {
					 test.log(LogStatus.FAIL, "Failed to locate Download button"); 
					 err2009++;
				 }
			 }
			 else {
				 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageDownloadButton, 10)) {
					 test.log(LogStatus.FAIL, "Download button is present even though not returned from API");
					 err2009++;
				 }
				 else {
					 test.log(LogStatus.INFO, "Download button is not displayed since Download is disabled from API"); 
				 }
			 }
			 Utilities.verticalSwipe(driver);
			 //10. 'Related Movies' tray section
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageRelatedMoviesTrayTitle, 10)) {
				 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageRelatedMoviesTrayFirstMovie, 10)) {
					 test.log(LogStatus.INFO, "Located Related Movies Tray title and first movie card in the tray");
				 }
				 else {
					 test.log(LogStatus.FAIL, "Located Related Movies Tray title but no movie card in the tray"); 
					 err2009++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Related Movies tray title"); 
				 err2009++;
			 }
		 }

	    if(err2009==0) {
	    	test.log(LogStatus.PASS, "Verify Movie Info Page UI is PASS");
			if(!Utilities.setResultsKids("VK_2009", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify Movie Info Page UI is FAIL");
			if(!Utilities.setResultsKids("VK_2009", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		}
	    
    	try {
    		 Thread.sleep(2000);
    	     Utilities.verticalSwipeReverse(driver);
			 moviespagev2.movieDetailsPageDownloadButton.click();
			 test.log(LogStatus.INFO, "Clicked on Download button");
			 if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageDownloading, 10)) {
				 test.log(LogStatus.INFO, "Downloading link is displayed");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Downloading link is not displayed");
				 err2025++;
			 }
			 try {
				 moviespagev2.movieDetailsPageDownloadButton.click();
				 test.log(LogStatus.INFO, "Clicked on Downloading link");
				 try {
					 moviespagev2.movieDetailsPageCancelDownload.click();
					 test.log(LogStatus.INFO, "Clicked on Cancel Download"); 
					 try {
						 moviespagev2.movieDetailsPageCancelDownloadYes.click();
						 test.log(LogStatus.INFO, "Clicked on YES to Cancel Download"); 
						 if(Utilities.explicitWaitClickable(driver, moviespagev2.movieDetailsPageDownloadButton, 10)) {
							test.log(LogStatus.INFO, "Downloading has changed to Download button"); 
							test.log(LogStatus.PASS, "Verify Cancel Download functionality for Movies when download is in-progress is PASS");
							if(!Utilities.setResultsKids("VK_2026", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 }
						 else {
							test.log(LogStatus.INFO, "Downloading has not changed to Download button"); 
							test.log(LogStatus.FAIL, "Verify Cancel Download functionality for Movies when download is in-progress is FAIL");
							if(!Utilities.setResultsKids("VK_2026", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
						 }
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to click on YES to Cancel Download");
						 BasePageV2.takeScreenshot();
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click on Cancel Download");
					 BasePageV2.takeScreenshot();
				 }
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on Downloading link");
				 BasePageV2.takeScreenshot();
			 }
			 if(Utilities.explicitWaitClickable(driver, moviespagev2.movieDetailsPageDownloadButton, 10)) {
		    	try {
					 moviespagev2.movieDetailsPageDownloadButton.click();
					 test.log(LogStatus.INFO, "Clicked on Download button again");
					 downloadButtonClicked=true;
					 //Verify Notification
					 driver.openNotifications();
					 BasePageV2.takeScreenshot();
					 try {
						 uiFavMovieTitleLocator="//android.widget.TextView[@text=\""+movieTitleAPI.split("'")[0]+"\" or @text=\""+movieTitleAPIUpper+"\" or @text=\""+movieTitleAPICamel+"\"]";
						 driver.findElement(By.xpath(uiFavMovieTitleLocator));
						 test.log(LogStatus.INFO, "Located Movie Title in Notifications"); 
					 }
					 catch(Exception e) {
						 err2030++;
						 test.log(LogStatus.FAIL, "Failed to locate Movie with download in progress state in Notifications");
						 
					 }
					 if(Utilities.explicitWaitVisibleNew(driver, downloadspagev2.progressBarNotification, 10)) {
						 test.log(LogStatus.INFO, "Located progress bar in Notifications");
					 }
					 else{
						 test.log(LogStatus.FAIL, "Failed to locate progress bar in Notifications");
						 err2030++;
					 }
					 if(err2030==0) {
						 test.log(LogStatus.PASS, "Verify in-progress downloads movie cards in device notification drawer is PASS");
						 if(!Utilities.setResultsKids("VK_2030", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else {
						 test.log(LogStatus.FAIL, "Verify in-progress downloads movie cards in device notification drawer is FAIL");
						 if(!Utilities.setResultsKids("VK_2030", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 //dismiss the notification drawer
					 try {
						 Utilities.verticalSwipeReverseGmail(driver);
						 test.log(LogStatus.INFO, "Swiped upwards from bottom of the screen to dismiss the notifications");
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to swipe upwards from bottom of the screen to dismiss the notifications"); 
					 }					 
		    	}
		    	catch(Exception e) {
		    		test.log(LogStatus.FAIL, "Failed to click on Download button");
		    		BasePageV2.takeScreenshot();
		    	}
			 }
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Download button");
			 BasePageV2.takeScreenshot();
			 err2025++;
		 }
	    
	    Utilities.verticalSwipeReverse(driver);
	    Utilities.verticalSwipeReverse(driver);
	    Utilities.verticalSwipeReverse(driver);
	    //Mark as Favourite
	    if(Utilities.explicitWaitClickable(driver, moviespagev2.movieDetailsPageFavouriteIcon, 10)) {
			 try {
				 moviespagev2.movieDetailsPageFavouriteIcon.click();
				 test.log(LogStatus.INFO, "Clicked on Favourite icon");
				 if(Utilities.explicitWaitClickableNew(driver, moviespagev2.addedToFavouritesOK, 10)) {
					 try {
						 moviespagev2.addedToFavouritesOK.click();
						 test.log(LogStatus.INFO, "Clicked on OK button in 'ADDED TO FAVOURITES' popup"); 
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to click on OK button in 'ADDED TO FAVOURITES' popup"); 
					 }
				 }
				 favouriteIconClicked=true;
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on Favourite icon");
				 BasePageV2.takeScreenshot();
			 }
		}
		else {
			 test.log(LogStatus.FAIL, "Favourite icon is not clickable"); 
			 BasePageV2.takeScreenshot();
		}
	    //Go to Home
	    driver.navigate().back();
	    Thread.sleep(1000);
	    driver.navigate().back();
	    Thread.sleep(1000);
	    test.log(LogStatus.INFO, "Navigated back to My Stuff");
	    if(favouriteIconClicked==true) {
	    	//Search for Favourites
			String favouritesXpath="//android.widget.TextView[@text='FAVOURITES' or @text='Favourites' or @text='favourites']";
			boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
			if(presenceTray==true) {
				test.log(LogStatus.INFO, "Favourites tray is displayed");	
				positionFavouritesTray=true;
				Utilities.verticalSwipe(driver);
				uiFavMovieTitleLocator="//android.widget.TextView[@text=\""+movieTitleAPI.split("'")[0]+"\" or @text=\""+movieTitleAPIUpper+"\" or @text=\""+movieTitleAPICamel+"\"]";
				try {
					driver.findElement(By.xpath(uiFavMovieTitleLocator)).click();
					test.log(LogStatus.INFO, "Located and clicked on Movie under Favourites tray");
					if(Utilities.explicitWaitVisibleNew(driver, moviespagev2.movieDetailsPageFavouriteIcon, 10)) {
						if(moviespagev2.movieDetailsPageFavouriteIcon.getAttribute("checked").equals("true")) {
							test.log(LogStatus.INFO, "Favourite icon is highlighted");
							test.log(LogStatus.PASS, "Verify the navigation by tapping on Movie card under 'All Favourites' tray is PASS");
							if(!Utilities.setResultsKids("VK_2024", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else {
							test.log(LogStatus.FAIL, "Favourite icon is not highlighted");
							test.log(LogStatus.FAIL, "Verify the navigation by tapping on Movie card under 'All Favourites' tray is FAIL");
							if(!Utilities.setResultsKids("VK_2024", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
						}
					}
					else {
						test.log(LogStatus.FAIL, "Failed to locate Favourite icon");
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to locate and click on Movie under Favourites tray");
					BasePageV2.takeScreenshot();
				}
				driver.navigate().back();
				if(Utilities.explicitWaitClickable(driver, homepagev2.editFavorite, 10)){
					try {
						homepagev2.editFavorite.click();
						test.log(LogStatus.INFO, "Tapped on Edit of Favourites tray");
						try {
							homepagev2.editFavoriteMoviesTab.click();
							test.log(LogStatus.INFO, "Tapped on Movies tab");
							try {
								driver.findElement(By.xpath(uiFavMovieTitleLocator));
								test.log(LogStatus.INFO, "Located the Movie under Movies tab");
								try {
									String favLoc="//android.widget.TextView[@text=\""+movieTitleAPI.split("'")[0]+"\" or @text=\""+movieTitleAPIUpper+"\" or @text=\""+movieTitleAPICamel+"\"]//parent::*/..//*[@resource-id='com.viacom18.vootkids:id/checkbox_un_fav_selector']";
									driver.findElement(By.xpath(favLoc)).click();
									test.log(LogStatus.INFO, "Tapped on Favourite icon to Unfavourite Movie");
									if(Utilities.explicitWaitClickable(driver, homepagev2.editFavoriteUnfavouriteButton, 10)) {
										try {
											homepagev2.editFavoriteUnfavouriteButton.click();
											test.log(LogStatus.INFO, "Tapped on Unfavourite button");
											for(int wait=0;wait<=10;wait++) {
												Thread.sleep(2000);
											}
											try {
												driver.findElement(By.xpath(uiFavMovieTitleLocator));
												test.log(LogStatus.FAIL, "Located the Movie under Movies tab after unfavouriting");
												BasePageV2.takeScreenshot();
												err2023++;
												driver.navigate().back();
												Utilities.verticalSwipe(driver);
												test.log(LogStatus.INFO, "Navigated back");
												Thread.sleep(3000);
												try {
													driver.findElement(By.xpath(uiFavMovieTitleLocator));
													test.log(LogStatus.FAIL, "Located the Movie under Favourites tray");
													err2023++;
												}
												catch(Exception e) {
													test.log(LogStatus.INFO, "Movie is not present in Favourites tray after unfavouriting");
												}	
												//Final verification of 2023
												 if(err2023==0) {
												    test.log(LogStatus.PASS, "Verify the behavior by unfavoriting Movie card from Edit favourites screen is PASS");
													if(!Utilities.setResultsKids("VK_2023", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												 }
												 else {
													test.log(LogStatus.FAIL, "Verify the behavior by unfavoriting Movie card from Edit favourites screen is FAIL");
													if(!Utilities.setResultsKids("VK_2023", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
													BasePageV2.takeScreenshot();
												 }				
											}
											catch(Exception e) {
												test.log(LogStatus.INFO, "Movie is not present under Movies tab after unfavouriting");
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to tap on Unfavourite button");
										}
									}
									else {
										test.log(LogStatus.FAIL, "Unfavourite button is not clickable");
									}
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to Unfavourite the movie");
								}
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to locate the Movie under Movies tab");
							}
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to click on Movies tab");
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to click on Edit of Favourites tray");
					}
				}
			}		
	    }
	    //driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
	    for (int i = 0; i <= 20; i++) {
				Utilities.verticalSwipeReverse(driver);
		}
	    for (int i = 0; i <= 20; i++)
		{
			if (Utilities.explicitWaitVisibleNew(driver, downloadspagev2.downloadsTabMystuffpage, 1))
			{
				test.log(LogStatus.INFO, "Swiped till Downloads tab");
				downloadspagev2.downloadsTabMystuffpage.click();
				test.log(LogStatus.INFO, "Clicked on Downloads tab");
				break;
			}
			else 
				Utilities.verticalSwipe(driver);
		}
	    Utilities.verticalSwipe(driver);
	    Utilities.verticalSwipe(driver);
	    try {
			driver.findElement(By.xpath(uiFavMovieTitleLocator));
			test.log(LogStatus.INFO, "Located the Movie under Downloads tab");
			String downloadProgress="//android.widget.TextView[@text=\""+movieTitleAPI.split("'")[0]+"\" or @text=\""+movieTitleAPIUpper+"\" or @text=\""+movieTitleAPICamel+"\"]//parent::*/..//*[@resource-id='com.viacom18.vootkids:id/squareProgressBar']";
			for(int wait=0;wait<=20;wait++) {
				try {
					driver.findElement(By.xpath(downloadProgress));
					test.log(LogStatus.INFO, "Movie download in progress ...");
					Thread.sleep(2000);
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Could not locate the download progress indicator for movie");
					break;
				}
			}
			String downloadCompleted="//android.widget.TextView[@text=\""+movieTitleAPI.split("'")[0]+"\" or @text=\""+movieTitleAPIUpper+"\" or @text=\""+movieTitleAPICamel+"\"]//parent::*/..//*[@resource-id='com.viacom18.vootkids:id/animation_play_button']";
			try {
				driver.findElement(By.xpath(downloadCompleted));
				test.log(LogStatus.INFO, "Play button located, Movie download COMPLETED ...");
				Thread.sleep(2000);
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Could not locate Play button for Movie downloaded");
			}
	    }
	    catch(Exception e) {
	    	test.log(LogStatus.INFO, "Failed to locate the Movie under Downloads tab");
	    	err2025++;
	    }
	  //Final verification of 2023
		 if(err2025==0) {
		    test.log(LogStatus.PASS, "Verify Download functionality for Movies is PASS");
			if(!Utilities.setResultsKids("VK_2025", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
		 else {
			test.log(LogStatus.FAIL, "Verify Download functionality for Movies is FAIL");
			if(!Utilities.setResultsKids("VK_2025", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			BasePageV2.takeScreenshot();
		 }
		 try {
			 driver.openNotifications();
			 BasePageV2.takeScreenshot();
			 uiFavMovieTitleLocator="//android.widget.TextView[@text=\""+movieTitleAPI.split("'")[0]+"\" or @text=\""+movieTitleAPIUpper+"\" or @text=\""+movieTitleAPICamel+"\"]//parent::*//..//android.widget.TextView[@text='Download complete' or @text='DOWNLOAD COMPLETE' or @text='download complete' or @text='Download Complate']";
			 driver.findElement(By.xpath(uiFavMovieTitleLocator));
			 test.log(LogStatus.INFO, "Located Movie title in Notifications");
			 if(Utilities.explicitWaitVisibleNew(driver, downloadspagev2.notificationDownloadComplete, 60)) {
				 test.log(LogStatus.INFO, "Located 'Download Complete' in Notifications");
			 }
			 else {
				 test.log(LogStatus.INFO, "Failed to locate 'Download Complete' in Notifications");
				 err2031++;
			 }
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to locate Movie title in Notifications");
			 err2031++;
			 
		 }
		 if(err2031==0) {
			 test.log(LogStatus.PASS, "Verify download completed movie cards in device notification drawer is PASS");
			 if(!Utilities.setResultsKids("VK_2031", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
		 }
		 else {
			 test.log(LogStatus.FAIL, "Verify download completed movie cards in device notification drawer is FAIL");
			 if(!Utilities.setResultsKids("VK_2031", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
		 }
		 try {
			 driver.findElement(By.xpath(uiFavMovieTitleLocator)).click();
			 test.log(LogStatus.INFO, "Clicked on Movie from Notifications");
			 if(Utilities.explicitWaitVisibleNew(driver, downloadspagev2.downloadsPageTitle, 20)) {
				 test.log(LogStatus.INFO, "Downloads page is displayed tapping on Notification");
				 uiFavMovieTitleLocator="//android.widget.TextView[@text=\""+movieTitleAPI.split("'")[0]+"\" or @text=\""+movieTitleAPIUpper+"\" or @text=\""+movieTitleAPICamel+"\"]";
				 try {
					 driver.findElement(By.xpath(uiFavMovieTitleLocator));
					 test.log(LogStatus.INFO, "Located Movie Title in dedicated Downloads page");
					 try {
						 driver.findElement(By.xpath(uiFavMovieTitleLocator)).click();
						 test.log(LogStatus.INFO, "Clicked on Movie from Dedicated Downloads page");
						 try {
							 homepagev2.verifyAndProgressBar();
							 kidsplayerv2.pauseVideo(); 	 
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Failed to pause video");	
						 }
						 if(Utilities.explicitWaitVisible(driver, kidsplayerv2.videoPlayer, 10)) {
							 test.log(LogStatus.INFO, "Player is displayed");	 
						 }
						 else {
							 test.log(LogStatus.FAIL, "Player failed to display");
							 BasePageV2.takeScreenshot();
							 err2033++;
						 } 
					 }
					 catch(Exception e) {
						 test.log(LogStatus.INFO, "Failed to click on movie from Dedicated Downloads page");
						 err2033++;
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to locate Movie Title in dedicated Downloads page");
					 err2032++;
				 }
				 if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedMovieSize, 10)) {
					test.log(LogStatus.INFO, "Located Movie Size in dedicated Downloads page: "+downloadspagev2.downloadedMovieSize.getAttribute("text"));
				 }
				 else {
					test.log(LogStatus.FAIL, "Failed to locate Movie size");
					err2032++;
				 } 
				 if(err2032==0) {
					test.log(LogStatus.PASS, "Verify the navigation by tapping on downloaded Movie content notification from device notification drawer is PASS");
					if(!Utilities.setResultsKids("VK_2032", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else {
					test.log(LogStatus.FAIL, "Verify the navigation by tapping on downloaded Movie content notification from device notification drawer is FAIL");
					if(!Utilities.setResultsKids("VK_2032", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 if(err2033==0) {
					test.log(LogStatus.PASS, "Verify the navigation of Downloaded cards from Downloads dedicated screen is PASS");
					if(!Utilities.setResultsKids("VK_2033", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else {
					test.log(LogStatus.FAIL, "Verify the navigation of Downloaded cards from Downloads dedicated screen is FAIL");
					if(!Utilities.setResultsKids("VK_2033", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Downloads page failed to display tapping on Notification ");
			 }
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Movie from Notifications");
		 }	 

	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
