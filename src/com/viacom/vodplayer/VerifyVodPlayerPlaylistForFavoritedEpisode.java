package com.viacom.vodplayer;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
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

import io.appium.java_client.MobileDriver;

public class VerifyVodPlayerPlaylistForFavoritedEpisode extends BaseTestV2 {
	String testName = "VerifyVodPlayerPlaylistForFavoritedEpisode";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Player playlist UI Functionality for the favorited Episode content");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Screen Playlist UI functionality for the favorited Episode content :"+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int scrollrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",scrollrow , "Verify scroll functionality for the cards displayed under 'Episodes' content tray:(Precondition - Yes)");	//P3
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int navirow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",navirow , "Verify navigation for the cards present under 'Episodes' content tray:(Precondition - Yes)");	//P2
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	 String timebefPause="";
		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
		
		test.log(LogStatus.INFO, " PRE-CONDITION - Episode type of card to be Favorited");
	     //click on watch tab	
		test.log(LogStatus.INFO, "Navigating to Watch Page");
		
		homepagev2.tabClick("Watch");
		
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
					if(trayNameParameter.equals("allKidsCharacters")) {
						trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
					}
				}
			 }
		 }	
		 trayTitle=trayTitle.toUpperCase();
			 
		 //Get show name from API
		 String apiName="";
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
		 String allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
		 //Verify if ALL CHARACTERS section is displayed
		 boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
		 boolean presence=false;
		 if(trayPresence==true) {
			 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
			 for(int scroll=0;scroll<=1;scroll++) {
				 try {
					 driver.findElement(By.xpath(allCharFirstItem)).click();
					 test.log(LogStatus.INFO, "Clicked on show");	
					 presence=true;
					 break;
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 if(scroll==1)
						 test.log(LogStatus.INFO, "Failed to click on show");	
				 }
			 }
			 if (presence==true) {
				 try {
					 //Scroll till Episodes tray
					 for(int scroll=0;scroll<=3;scroll++) {
						 if(Utilities.explicitWaitVisible(driver, watchpagev2.episodesTray, 10)) {
							 test.log(LogStatus.INFO, "Scrolled to Episodes tray");
							 Utilities.verticalSwipe(driver);
							 Utilities.verticalSwipe(driver);
							 Thread.sleep(2000);
							 break;
						 }
						 else {
							 Utilities.verticalSwipe(driver);
							 if(scroll==3)
								 test.log(LogStatus.FAIL, "Failed to scroll to EPISODES tray");
						 }
					 }
					 if(Utilities.explicitWaitVisible(driver, watchpagev2.episodesItemOneInEpisodeTray, 10)) {
						 String recentActivityFirstItemData = watchpagev2.episodesItemOneInEpisodeTray.getAttribute("text");
						 test.log(LogStatus.INFO, "First Episode is -> "+recentActivityFirstItemData);
						 if(Utilities.explicitWaitClickable(driver, watchpagev2.episodesItemOneInEpisodeTray, 10)) {
							 watchpagev2.episodesItemOneInEpisodeTray.click();
							 test.log(LogStatus.INFO, "Clicked on episode");

		
						 }
					 }
				 }
				 catch(Exception e){}
			 }
		 
				  //Check for progress bar
				  homepagev2.verifyAndProgressBar();
				
				  //Pause the video
				  playerpagev2.pauseVideo();
				  //Click on Favorites
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoriteButton , 20))
					{
					
					  if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 5))
						{
							showspagev2.favIconPopupOkButton.click();
						}
						  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoritedButton , 5))
						  {
							  test.log(LogStatus.INFO, "Icon got filled for the favorited episode content playback");
							 
						  }
						  else
							  test.log(LogStatus.FAIL,"Favorite Icon not  filled for the favorited episode content playback");
					
					}
					else
						test.log(LogStatus.FAIL, "Favorite button is not present in the player"); 
				  
				  
				  
				  test.log(LogStatus.INFO, "Clicking on Up Arrow button");	  
							  
				  
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomUpArrowButton, 10))
				  {
					  playerpagev2.playerBottomUpArrowButton.click();						  
				  }
				  else
					  BasePageV2.reportFail("Content sub Title is not displayed");  
				  
		//	verifying UI of the playlist under episode section
				  List<String> episodeTitles=new LinkedList<String>();
					String lastTitleOfloop="", firsttitle="",currentLastTitle="",title="", episodefirsttitle="", episoderelatedtitle="";
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playListEpisodeTab, 10))
					 {
						 test.log(LogStatus.INFO, "Episodes section is displayed");
						 test.log(LogStatus.INFO, "Verifying the UI of Episodes Section ");
						 
						 System.out.println(driver.getPageSource());
						 //Verifying UI of the metadata
						
					if(playerpagev2.playListEpisodeContents.size()>0)
					{
						boolean check=true;
						while(check==true)
						{
			
						for(int i=0;i<playerpagev2.playListEpisodeContents.size();i++)
						{
							//Verifying whether all contents all meta data
							String episodexpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/linear_recycler_view']//android.view.ViewGroup[@index='"+i+"']";
							
							//Checking show Title
							try{
								driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Show Title is not present");
							}
							
							//Checking episode Title
							try{
							  WebElement episodetitle=	driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']");
							  title=episodetitle.getText();
							  if(episodeTitles.contains(title))
							  episodeTitles.add(title);
							  if(i==playerpagev2.playListEpisodeContents.size()-1)
							  {
								  currentLastTitle=episodetitle.getText();
							  }
							  if(i==0)
								  firsttitle=episodetitle.getText();
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL, "Episode Title is not present");
							}
							
							//Checking Duration
							
							try{
								driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']");
							}
							catch(Exception e)
							{
								if(i<3)
								test.log(LogStatus.FAIL, "Duration is not present for the content");
							}
							
							//checking watch icon
							
							try{
								driver.findElementByXPath(episodexpath+"//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/grid_item_category']");
							}
							catch(Exception e)
							{
								if(i<3)
								test.log(LogStatus.FAIL, "Watch icon is not present for the content");
							}
							
							
						}
						   if(currentLastTitle.equals(lastTitleOfloop))
						   check=false;
						   else
						   lastTitleOfloop=currentLastTitle;						   
						   Utilities.horizontalSwipeForPlaylistTray(driver);
						}
						
					}
					else
					{
						test.log(LogStatus.FAIL, "No contents are present under Episodes section");
					}
					
					
					
							 
			}
					 else
					 {
						 test.log(LogStatus.FAIL, "Episodes section is displayed");
					 }
				  
				  test.log(LogStatus.INFO, "Meta data is displayed for all the contents in Episodes Section");
				
				 
  
			//Navigating to Related Tab
				if(Utilities.explicitWaitClickable(driver, playerpagev2.playListRelatedTab, 30))
				 {
				  playerpagev2.playListRelatedTab.click();
				  
					 test.log(LogStatus.INFO, "Related section is displayed");
					
					 
					 System.out.println(driver.getPageSource());
					 //Verifying UI of the metadata
					
				if(playerpagev2.playListEpisodeContents.size()>0)
				{
					boolean check=true;
					while(check==true)
					{
		
					for(int i=0;i<playerpagev2.playListEpisodeContents.size();i++)
					{
						//Verifying whether all contents all meta data
						String episodexpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/linear_recycler_view']//android.view.ViewGroup[@index='"+i+"']";
						
						//Checking show Title
						try{
							driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
						}
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Show Title is not present");
						}
						
						//Checking episode Title
						try{
						  WebElement episodetitle=	driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']");
						  title=episodetitle.getText();
						  if(episodeTitles.contains(title))
						  episodeTitles.add(title);
						  if(i==playerpagev2.playListEpisodeContents.size()-1)
						  {
							  currentLastTitle=episodetitle.getText();
							  
						  }
						  if(i==0)
							  firsttitle=episodetitle.getText();
						}
						catch(Exception e)
						{
							test.log(LogStatus.FAIL, "Episode Title is not present");
						}
						
						//Checking Duration
						
						try{
							driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']");
						}
						catch(Exception e)
						{
							if(i<3)
							test.log(LogStatus.FAIL, "Duration is not present for the content");
						}
						
						//checking watch icon
						
						try{
							driver.findElementByXPath(episodexpath+"//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/grid_item_category']");
						}
						catch(Exception e)
						{
							if(i<3)
							test.log(LogStatus.FAIL, "Watch icon is not present for the content");
						}
						
						
					}
					   if(currentLastTitle.equals(lastTitleOfloop))
					   check=false;
					   else
					   lastTitleOfloop=currentLastTitle;						   
					   Utilities.horizontalSwipeForPlaylistTray(driver);
					}
					
				}
				else
				{
					BasePageV2.reportFail("No contents are present in Related Tab");
				}
				  
				 }
				 else
				 BasePageV2.reportFail("Not able to click on Related Tab / Related Tab is not present");
				
				
				 test.log(LogStatus.INFO, "Meta data is displayed for all the contents in Related Section");
				 
				 test.log(LogStatus.PASS,"Testcase : 'Verify scroll functionality for the cards displayed under 'Episodes' content tray: Pre-condition:Episodes type of cards to be favorited' is Passed");
				  BasePageV2.smokeresults("", scrollrow, "Pass");
				 if(Utilities.explicitWaitClickable(driver, playerpagev2.playListEpisodeTab, 30))
				 {
					 
					 playerpagev2.playListEpisodeTab.click();
				 }
				 else
				 BasePageV2.reportFail("Not able to play content from episode section");
				
				test.log(LogStatus.INFO, "Playing any content from Episode section");
				
				 if(Utilities.explicitWaitClickable(driver, playerpagev2.episodeSecondVideoInPlaylist, 30))
				 {	
					   episodefirsttitle=playerpagev2.playListSecondEpisodeTitle.getText();
					   try{
						   episodefirsttitle=episodefirsttitle.split("-")[1];
					   }
					   catch(Exception e){}
					   test.log(LogStatus.INFO, "Verifying Card Navigation from Episode Tab-"+episodefirsttitle);
				       playerpagev2.episodeSecondVideoInPlaylist.click();
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
				  test.log(LogStatus.PASS,"Testcase : 'Verify navigation for the cards present under 'Episodes' content tray , Pre-condition:Episodes type of cards to be favorited' is Passed");
			      BasePageV2.smokeresults("", navirow, "Pass");
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
           //Click on Down Arrow
		  
		  /*	  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomUpArrowButton, 20))
			  playerpagev2.playerBottomUpArrowButton.click();
		  else
			  BasePageV2.reportFail("Up Arrow button is not displayed on at bottom when played content from Episode Tab");
		  
		//clicking on related tab
		  if(Utilities.explicitWaitClickable(driver, playerpagev2.playListRelatedTab, 30))
			 {
			  playerpagev2.playListRelatedTab.click();
			 }
		  else
          BasePageV2.reportFail("Related Tab is not present");	
          
          if(Utilities.explicitWaitClickable(driver, playerpagev2.episodeVideoInPlaylist, 30))
			 {	

				   episodefirsttitle=playerpagev2.playListEpisodeTitle.getText();
					  test.log(LogStatus.INFO, "Verifying Card Navigation from Related Tab - "+episodefirsttitle);
				   
				   try{
					   episodefirsttitle=episodefirsttitle.split("-")[1];
				   }
				   catch(Exception e){}
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
 				BasePageV2.reportFail("Player is not launched when clicking on content under Related Tab");

	      //Check for sub Title	  				  
		  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10))
		  {
			  subtitletext2=playerpagev2.playerSubTitle.getText();
			  System.out.println("Related section Title :"+episodefirsttitle);
			 test.log(LogStatus.INFO, "Upnext Content sub Title is displayed on the Player screen -"+subtitletext2);
			  if(episodefirsttitle.contains(subtitletext2))
				  test.log(LogStatus.PASS,"Testcase : 'Related Tab - Verify card navigation' is Passed");
			  else
			  {
				  test.log(LogStatus.INFO, "Title displayed in the player is -"+subtitletext2);
				  BasePageV2.reportFail(" Title in player is not matching with the title of content played from Related Tab");  
			  }
		  }
		  else
			  BasePageV2.reportFail("Content sub Title is not displayed");*/
            
}

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}