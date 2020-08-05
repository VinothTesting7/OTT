package com.viacom.lastviewed;

import java.time.Duration;
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

import io.appium.java_client.remote.MobileCapabilityType;
//Author Tanisha //P1
// kidsplayerpage playerCurrentDuration
//kidsplayerpage pauseVideo, playVideo methods add visible new
// capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300); to basetest
public class VerifyLastViewedMoviesResumeMyStuff extends BaseTestV2{
	
	String testName = "VerifyLastViewedMoviesResumeMyStuff";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedMoviesResumeMyStuff(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedMoviesResumeMyStuff");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// P1 VK_2064 Verify the playback status for partially watched Movie content in Last viewed tray (LV in my stuff tab)
		// P1 VK_2065 Verify the playback status for Completetly watched Movie content in Last viewed tray (LV in my stuff tab)
		// P1 VK_2066 Verify Last viewed Movie cards are removed from My stuff - Last view tray by tapping on 'Clear' link text (LV in my stuff tab)
		

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
		 boolean locatedTray=false;
		 boolean movieFirstItemClicked=false;
		 boolean movieSecondItemClicked=false;
		 boolean movieThirdItemClicked=false;
		 boolean playButtonClickedMovie1=false;
		 boolean playButtonClickedMovie2=false;
		 boolean playButtonClickedMovie3=false;
		 boolean playButtonClickedlv1=false;
		 boolean playButtonClickedlv2=false;
		 String durationPlayed="";
		 int durationPlayedInt=0;
		 int completelyWatchedTime=0;
		 int partiallyWatchedFirstTime=0;
		 int partiallyWatchedSecondTime=0;
		 //Login module 
		 //homepagev2.logout ();
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
			String movieSecondItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='1']";		
			try {
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
				Utilities.verticalSwipeReverse(driver);
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
					 kidsplayerv2.pauseVideo();
					 if(Utilities.explicitWaitVisibleNew(driver, kidsplayerv2.playerCurrentDuration, 20)) {
						 try {
							 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
							 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
							 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed);
							 partiallyWatchedFirstTime=durationPlayedInt;
							 test.log(LogStatus.INFO, "Duration played in Seconds: "+partiallyWatchedFirstTime);
						 }
						 catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to fetch player current duration for second movie"); 
						 } 
					 }
					 else {
						 test.log(LogStatus.FAIL, "Player current duration is not displayed"); 
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.INFO, "Second Movie play failed");
				 }
				 kidsplayerv2.playVideo();
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Navigated back to close the player");
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Navigated back to tray");
			}	
			
			//Third Movie
			String movieThirdItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='2']";		
			try {
				driver.findElement(By.xpath(movieThirdItem)).click();
				test.log(LogStatus.INFO, "Clicked on third movie");
				movieThirdItemClicked=true;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				try {
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
				Utilities.verticalSwipeReverse(driver);
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
					 //kidsplayerv2.playVideo();
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
			Utilities.verticalSwipeReverse(driver);
			Utilities.verticalSwipeReverse(driver);
			driver.runAppInBackground(Duration.ofSeconds(3));
			test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			driver.currentActivity();
			if(Utilities.explicitWaitClickableNew(driver, homepagev2.mystuff_tab, 5)) {
				try {
					homepagev2.mystuff_tab.click();
					test.log(LogStatus.INFO, "Tapped on My Stuff tab");
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to tap on My Stuff tab");
				} 
			 }
			//Scroll till Last Viewed
			String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
			for(int i=0;i<50;i++) {
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(recentViewedClear));
					test.log(LogStatus.INFO, "Located Last Viewed tray");
					break;
				}
				catch(Exception e) {
					Utilities.verticalSwipe(driver);
					if(i==50) {
						test.log(LogStatus.INFO, "Failed to locate Last Viewed tray");
					}
				}
			}
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
		    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lvMovieFirstItemTitle, 10)) {
		    	lastViewed1=homepagev2.lvMovieFirstItemTitle.getAttribute("text");
		    	if(lastViewed1.contains(movieTitle3)) {
		    		test.log(LogStatus.INFO, lastViewed1+" is the first latest movie under last viewed in My Stuff tab");
		    		//Verify complete resume
		    		try {
		    			homepagev2.lvMovieFirstItemTitle.click();
		    			test.log(LogStatus.INFO, "Tapped on first movie from Last Viewed");
		    		}
		    		catch(Exception e) {
		    			test.log(LogStatus.FAIL, "Failed to tap on first movie from Last Viewed");
		    		}
		    		//Tap on Play button
					if(Utilities.explicitWaitClickableNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
						try {
							moviespagev2.movieDetailsPagePlayButton.click();
							test.log(LogStatus.INFO, "Tapped on PLAY button");
							playButtonClickedlv1=true;
						}
						catch(Exception e) {
							test.log(LogStatus.INFO, "Failed to tap on PLAY button");
						}
					}
					else {
						test.log(LogStatus.FAIL, "Play button is not clickable");
					}
					if(playButtonClickedlv1==true) {
						homepagev2.waitForVideoToLoad();
						kidsplayerv2.pauseVideo();
						if(Utilities.explicitWaitVisibleNew(driver, kidsplayerv2.playerCurrentDuration, 10)) {
							try {
								 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
								 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
								 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed); 
								 completelyWatchedTime=durationPlayedInt;
								 test.log(LogStatus.INFO, "Duration played in Seconds: "+completelyWatchedTime);
								 if(completelyWatchedTime<=30) {
									 test.log(LogStatus.INFO, "The completely watched movie is assumed to have begun from beginning since captured time is below 30 seconds");
									 test.log(LogStatus.PASS, "Verify the playback status for Completetly watched Movie content in Last viewed tray is Pass");
									 if(!Utilities.setResultsKids("VK_2065", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 else {
									 test.log(LogStatus.FAIL, "The completely watched movie is assumed to NOT have begun from beginning since captured time is above 30 seconds");
									 test.log(LogStatus.FAIL, "Verify the playback status for Completetly watched Movie content in Last viewed tray is Fail");
									 if(!Utilities.setResultsKids("VK_2065", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Player current duration is not displayed"); 
							}
						}
						else {
							test.log(LogStatus.FAIL, "Player current duration is not visible"); 
						}
						driver.navigate().back();
						test.log(LogStatus.INFO, "Navigated back to close the player");	
						driver.navigate().back();
						test.log(LogStatus.INFO, "Navigated back to tray");	
					}		
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest movie under last viewed in My Stuff tab instead of "+movieTitle3);
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed movie 1 is not visible under last viewed in My Stuff tab");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lvMovieSecondItemTitle, 10)) {
		    	lastViewed2=homepagev2.lvMovieSecondItemTitle.getAttribute("text");
		    	if(lastViewed2.contains(movieTitle2)) {
		    		test.log(LogStatus.INFO, lastViewed2+" is the second latest movie under last viewed in My Stuff tab");
		    		//Verify partial resume
		    		try {
		    			homepagev2.lastViewedSecondItemTitle.click();
		    			test.log(LogStatus.INFO, "Tapped on second movie from Last Viewed");
		    		}
		    		catch(Exception e) {
		    			test.log(LogStatus.FAIL, "Failed to tap on second movie from Last Viewed");
		    		}
		    		//Tap on Play button
					if(Utilities.explicitWaitClickableNew(driver, moviespagev2.movieDetailsPagePlayButton, 10)) {
						try {
							moviespagev2.movieDetailsPagePlayButton.click();
							test.log(LogStatus.INFO, "Tapped on PLAY button");
							playButtonClickedlv2=true;
						}
						catch(Exception e) {
							test.log(LogStatus.INFO, "Failed to tap on PLAY button");
						}
					}
					if(playButtonClickedlv2==true) {
						homepagev2.waitForVideoToLoad();
						test.log(LogStatus.INFO, "Played movie that was played partially");
						kidsplayerv2.pauseVideo();
						if(Utilities.explicitWaitVisibleNew(driver, kidsplayerv2.playerCurrentDuration, 10)) {
							try {
								 durationPlayed=kidsplayerv2.playerCurrentDuration.getAttribute("text");
								 test.log(LogStatus.INFO, "Played until duration: "+durationPlayed);
								 durationPlayedInt=kidsplayerv2.convertStringDurationTOIntegerSeconds(driver, durationPlayed);
								 partiallyWatchedSecondTime=durationPlayedInt;
								 test.log(LogStatus.INFO, "Duration played in Seconds, currently: "+partiallyWatchedSecondTime);
								 test.log(LogStatus.INFO, "Duration played in Seconds, previously: "+partiallyWatchedFirstTime);
								 if(partiallyWatchedSecondTime>partiallyWatchedFirstTime) {
									 test.log(LogStatus.INFO, "The partially watched movie is assumed to have resumed from where it was paused");
									 test.log(LogStatus.PASS, "Verify the playback status for partially watched Movie content in Last viewed tray is Pass");
									 if(!Utilities.setResultsKids("VK_2064", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 else {
									 test.log(LogStatus.FAIL, "The partially watched episode is assumed to not have resumed from where it was paused");
									 test.log(LogStatus.FAIL, "Verify the playback status for partially watched Movie content in Last viewed tray is Fail");
									 if(!Utilities.setResultsKids("VK_2064", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Player current duration is not displayed"); 
							}	
						}
						else {
							test.log(LogStatus.FAIL, "Player current duration is not visible"); 
						}
						driver.navigate().back();
						test.log(LogStatus.INFO, "Navigated back to close the player");		
						driver.navigate().back();
						test.log(LogStatus.INFO, "Navigated back to tray");
		    		}
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest movie under last viewed in My Stuff tab instead of "+movieTitle2);
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episide 2 is not visible under last viewed in My Stuff tab");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lvMovieThirdItemTitle, 10)) {
		    	lastViewed3=homepagev2.lvMovieThirdItemTitle.getAttribute("text");
		    	if(lastViewed3.contains(movieTitle1)) {
		    		test.log(LogStatus.INFO, lastViewed3+" is the third latest movie under last viewed in My Stuff tab");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest movie under last viewed in My Stuff tab instead of "+movieTitle1);
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed movie 3 is not visible under last viewed in My Stuff tab");
		    }
		    if(Utilities.explicitWaitClickableNew(driver, homepagev2.watchTabLVClear, 10)) {
				try {
					homepagev2.watchTabLVClear.click();
					test.log(LogStatus.INFO, "Clicked on Clear link");
					//Verify Yes button functionality
					try {
						homepagev2.recentViewedClearYes.click();
						test.log(LogStatus.INFO, "Clicked on Yes button in the popup");
						Thread.sleep(3000);
						Thread.sleep(3000);
						if(Utilities.explicitWaitVisibleNew(driver, homepagev2.watchTabLVClear, 10)) {
							test.log(LogStatus.FAIL, "Clear button is displayed, tray is not cleared");
							test.log(LogStatus.FAIL, "Verify Last viewed Movie cards are removed from My stuff - Last view tray by tapping on 'Clear' link text is Fail");	
							if(!Utilities.setResultsKids("VK_2066", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							BasePageV2.takeScreenshot();
						}
						else {
							test.log(LogStatus.INFO, "Clear button is not displayed, tray has been cleared");
							test.log(LogStatus.PASS, "Verify Last viewed Movie cards are removed from My stuff - Last view tray by tapping on 'Clear' link text is Pass");	
							if(!Utilities.setResultsKids("VK_2066", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}	
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to click on Yes in the popup of Last Viewed in My Stuff tab");
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Clear link in Last Viewed in My Stuff tab");
				}
			}
			else {
				test.log(LogStatus.FAIL, "Clear link is not clickable in Last Viewed in My Stuff tab");
			}
		}		
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
