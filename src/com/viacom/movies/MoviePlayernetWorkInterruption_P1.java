package com.viacom.movies;

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
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.MoviesPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.connection.ConnectionState;

public class MoviePlayernetWorkInterruption_P1 extends BaseTestV2{
	
String testName = "MoviePlayernetWorkInterruption_P1";
	
	String trayName="";
	String trayNameUpper="";
	String trayNameCamel="";
	String movieTitle="" , episodefirsttitle = "",subtitletext2="";
	@Test(dataProvider = "getData")
	public void moviePlayernetWorkInterruption_P1(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("MoviePlayernetWorkInterruption_P1");
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
	 String timebefPause="";

		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
		

	// VK_2109   - Verify Movie playback state post network interruption
	
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
							test.log(LogStatus.INFO, "Movie title: \""+movieTitle+"\"");
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate Movie title"); 
						}			
					}
				}				

				// Click on play button in movie info page
				  if(Utilities.explicitWaitClickable(driver, watchpagev2.playBtn, 50)) {
					  watchpagev2.playBtn.click();
					  test.log(LogStatus.INFO, "click on play button in Movie info page");
					  Thread.sleep(5000);
				  }else {
					  BasePageV2.reportFail("Not displayed play button in  movie info page / Not click");
				  }
				  
				  
				  try {
					  //Check for progress bar
					  homepagev2.verifyAndProgressBar();
					  Thread.sleep(5000);
					 
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				  
				  
				 // Disconnect the Net work 
				  
				  driver.setConnection(new ConnectionState(0));
				  Thread.sleep(10000);
				  
				  // checking ProgressBar is Displayed are not 
				  if(Utilities.explicitWaitVisible(driver, homepagev2.progressBarLoader, 20)) {
					  test.log(LogStatus.FAIL, "Verify Movie playback state post network interruption");
						if(!Utilities.setResultsKids("VK_2109", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
					}else {

						test.log(LogStatus.PASS, "Verify Movie playback state post network interruption");
						if(!Utilities.setResultsKids("VK_2109", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
					}
				  
				  
				  
				  // Connect the Net work (Turn it On the Wifi)
				  driver.setConnection(new ConnectionState(6));
				  Thread.sleep(10000);
				  
				  
				  
				  
				  
	  }
	  @DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					

		}		  
				  

}
