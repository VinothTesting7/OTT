package com.viacom.vodplayer;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class VerifyVodPlayerFavoritesRelatedFunctionality extends BaseTestV2 {
	String testName = "VerifyVodPlayerFavoritesRelatedFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Vod Player UI Functionality for the Episode content from Favorites Tab");
	test.log(LogStatus.INFO, "Starting the test for Verifying the VOD Player functionality for the  Episode content from Favorites Tab: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrowrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uparrowrow, "Verify the Up arrow functionality during playback of favouirted episode card content");	//P2
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int navirow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",navirow, "Episode Tab - Verify card navigation");//P2
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	 String timebefPause="";

		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.logout();
		homepagev2.login("8073542250", "vinoth@123");
		

				  
	    //scroll to Favourites Tray	
		test.log(LogStatus.INFO, "Scrolling to the Favourites Tray");
		//String xpath="//android.widget.TextView[@text='FAVOURITES' and @resource-id='com.viacom18.vootkids:id/recent_title_text']";
		   Utilities.verticalSwipe(driver, homepagev2.favouritesTray,"visible",50);
		
        if(Utilities.explicitWaitVisible(driver, homepagev2.favouritesTray, 30))
        {
        	  Utilities.verticalSwipe(driver, homepagev2.favouritesTrayFirstEpisodeContent,"Clickable",10);
		   Thread.sleep(2000);	
		  // test.log(LogStatus.INFO, "Navigating to any of content under Favourites Tray");
		   if(Utilities.explicitWaitClickable(driver, homepagev2.favouritesTrayFirstEpisodeContent, 30))
		   homepagev2.favouritesTrayFirstEpisodeContent.click();
		   else
		   BasePageV2.reportFail("Not able to click content under Favourites Tray / No contents under Favourites");
	    }
        else
        	BasePageV2.reportFail("Favourites Tray is not Present in My Stuff tab");

                 //Check for progress bar
		          test.log(LogStatus.INFO, "Playing any of the favorited content from Favourites tray");
				  homepagev2.verifyAndProgressBar();
				
				  //Pause the video
				  playerpagev2.pauseVideo();
				  
                test.log(LogStatus.INFO, "Clicking on Up Arrow button");	  
							  
				  
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomUpArrowButton, 10))
				  {
					  playerpagev2.playerBottomUpArrowButton.click();						  
				  }
				  else
					  BasePageV2.reportFail("UpArrow button is not displayed");  
		System.out.println(driver.getPageSource());
				 //Verify whether favorites section only present
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playListFavoritesTab, 10))
					 {
						 test.log(LogStatus.INFO, "Favorites section is displayed");
						 
					    if(playerpagev2.playListEpisodeContents.size()>0)
					   {
						test.log(LogStatus.INFO,"Content(s) are displayed under Favourites section");						
					   }
					   else
					   {
						BasePageV2.reportFail("No contents are present under Favourites section");
					   }		
					    
					    if(playerpagev2.playlistTabs.size()==0  && playerpagev2.playListduringFavoritesTabs.size()==1)
					    	{
					        	test.log(LogStatus.INFO, "Only favorites Tab is displayed");
					        	test.log(LogStatus.PASS,"Testcase : 'Verify the Up arrow functionality during playback of favouirted episode card content' is Passed");
					    	    BasePageV2.smokeresults("", uparrowrow, "Pass");
					    	}
					    else
					    	BasePageV2.reportFail("Not only favorites Tab is displayed -"+playerpagev2.playlistTabs.size()+" is displayed");
					    String episodefirsttitle="";
					    if(Utilities.explicitWaitClickable(driver, playerpagev2.episodeVideoInPlaylist, 30))
						 {	
							   episodefirsttitle=playerpagev2.playListEpisodeTitle.getText();
							   try{
								   episodefirsttitle=episodefirsttitle.split("-")[1];
							   }
							   catch(Exception e){}
							   test.log(LogStatus.INFO, "Verifying Card Navigation from Episode Tab-"+episodefirsttitle);
						       playerpagev2.episodeVideoInPlaylist.click();
						 }
						 else
						 BasePageV2.reportFail("Not able to cLick on content in Episode section");
						
						 
					if(Utilities.explicitWaitVisible(driver, playerpagev2.videoPlayer, 20))	 
					{
					     homepagev2.verifyAndProgressBar();
						 playerpagev2.pauseVideo();
					}
					else
						BasePageV2.reportFail("Player is not launched when clicking on content under Episode Tab");
						  //Check for Title	  				
						  String titletext="", subtitletext="",subtitletext2="";
						
				
			      //Check for sub Title	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10))
				  {
					  subtitletext2=playerpagev2.playerSubTitle.getText();
					 test.log(LogStatus.INFO, "Upnext Content sub Title is displayed on the Player screen -"+subtitletext2);
					  System.out.println("Episode section Title :"+episodefirsttitle);
					  if(episodefirsttitle.contains(subtitletext2))
					  {
						  test.log(LogStatus.PASS,"Testcase : 'Episode Tab - Verify card navigation' is Passed");
						  BasePageV2.smokeresults("",navirow, "Pass");
					  }
					  else
					  {
						  test.log(LogStatus.INFO, "Title displayed in the player is -"+subtitletext2);
						  BasePageV2.reportFail("Title in player is not matching with the title of content played from Episode Tab");  
					  }
				  }
				  else
					  BasePageV2.reportFail("Content sub Title is not displayed");
					    
					    
					    
					    
			       }
			        else
			        {
				      test.log(LogStatus.FAIL, "Favorites section is not displayed");
			        }
				  
				 
				 
		 	
				  
				  // check in favorites section
				  
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
	