package com.viacom.lastviewed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
//Author Tanisha
//Make sure to login with robotani Jane profile
public class VerifyLastViewedEpisodesOrder extends BaseTestV2{
	
	String testName = "VerifyLastViewedEpisodesOrder";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedEpisodes(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedEpisodesOrder");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls_VK_1501 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1501=xls_VK_1501.getRowCount("Smoke_Results")+1;
		xls_VK_1501.setCellData("Smoke_Results", "Testcase Name",row_VK_1501,"Verify the cards removal order under 'Last Viewed' section when user watches more than 8 VOD contents");
		
		Xls_Reader xls_VK_1502 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1502=xls_VK_1502.getRowCount("Smoke_Results")+1;
		xls_VK_1502.setCellData("Smoke_Results", "Testcase Name",row_VK_1502,"Verify the type of cards available under 'Last Viewed' section");
	
		Xls_Reader xls_VK_1497 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1497=xls_VK_1497.getRowCount("Smoke_Results")+1;
		xls_VK_1497.setCellData("Smoke_Results", "Testcase Name",row_VK_1497,"Verify availability of 'Last Viewed' section.");

		Xls_Reader xls_VK_1503 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1503=xls_VK_1503.getRowCount("Smoke_Results")+1;
		xls_VK_1503.setCellData("Smoke_Results", "Testcase Name",row_VK_1503,"Verify episode card metadata for partially watched contents");

		Xls_Reader xls_VK_1504 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1504=xls_VK_1504.getRowCount("Smoke_Results")+1;
		xls_VK_1504.setCellData("Smoke_Results", "Testcase Name",row_VK_1504,"Verify episode card metadata for completely watched contents");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 //Login module 
		 //homepagev2.logout();
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 boolean clickedOnShow=true;
		 boolean clickedEpisodesTray=true;
		 boolean verifylastViewed=true;
		 ArrayList<String> episodes=new ArrayList<String>();
		 ArrayList<String> episodesDuration=new ArrayList<String>();
		 ArrayList<String> episodesLastViewed=new ArrayList<String>();
		 int countEpisodes=0;
		 int scrollEpisodes=0;
		 int fetchedCount=0;
		 String durationUI="";
		 String durationAPI="";
		 String UIlocation="";
		 String lastViewed1="";
		 String lastViewed2="";
		 String lastViewed3="";
		 String lastViewed4="";
		 String lastViewed7="";
		 String lastViewed8="";
		 int errVK_1501=0;
		 int errVK_1502=0;
		 int errVK_1503=0;
		 int errVK_1504=0;
		 int errVK_1877=0;

		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }

		 
		 //Fetch data from API
		 String api_AllCharacters="All Characters";
		 int totalitemsofapiAllCharacters=0;
		 String url_AllCharacters="";
		 ArrayList<String> carousalCardsFromAPIAllCharacters=new ArrayList<String>();
		 String carousalCardsInListAllCharacters="";
		 String secondMediaID="";
		 int rowsAllCharacters=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsAllCharacters;rNum++){
			 String apiname=xls.getCellData("Api", "API Name", rNum);
			 if(apiname.equals(api_AllCharacters)){
				 url_AllCharacters=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(2, xls, api_AllCharacters);
				 Response resp_AllCharacters=Utilities.requestUtilitypostOld(url_AllCharacters, map);
				 //resp_allCharacters.prettyPrint();
				 totalitemsofapiAllCharacters=resp_AllCharacters.jsonPath().get("assets.items.size()");
				 //Get the second item media id from the API
				 secondMediaID=resp_AllCharacters.jsonPath().get("assets.items[1].mId");
			 }
		 }
		 
		 //Call the API to get list of Episodes for the fetched media ID 
		 String url_APIepisodeList="https://api.vootkids.com/app/ks/v1/allKidsEpisodes.json?pageSize=1&pageIndex=&refSeriesId=1&uId=1&profileId=1&ks=1&mediaId="+secondMediaID+"&pageSize=20&pageIndex=0&refSeriesId="+secondMediaID;
		 //ArrayList<String> itemsFromAPIEpisodes=new ArrayList<String>();
		 HashMap<String,String> map=new HashMap<String,String>();//empty map
		 Response resp_EpisodeList=Utilities.requestUtilitypostOld(url_APIepisodeList, map);
		 int totalitemsofAPIEpisodeList=resp_EpisodeList.jsonPath().get("assets.items.size()");
		 //String test1=resp_EpisodeList.jsonPath().get("assets.items[19].mId");
		 
		 //Place 9 titles in an array list
		 for(int i=0;i<9;i++) {
			 String apiItemEpisodeTitle=resp_EpisodeList.jsonPath().get("assets.items["+i+"].title");
			 String apiItemEpisodeNumber=resp_EpisodeList.jsonPath().get("assets.items["+i+"].episodeNo");
			 int apiItemSeasonInt=resp_EpisodeList.jsonPath().get("assets.items["+i+"].season");
			 String apiItemSeason=Integer.toString(apiItemSeasonInt);
			 String epi=apiItemEpisodeTitle;
			 episodes.add(epi);	
			 int apiItemDuration=resp_EpisodeList.jsonPath().get("assets.items["+i+"].duration");
			 int apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toMinutes(apiItemDuration);					 
			 if(apiItemDurationInt<=1) 
				 apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toSeconds(apiItemDuration);
			 String apiItemDurationString=Integer.toString((int) apiItemDurationInt);
			 episodesDuration.add(apiItemDurationString);
		 }
		 test.log(LogStatus.INFO, "9 episodes fetched from API: "+episodes);
		 //Call Config API to get the media type of Episodes
		 ArrayList<Integer> Listof_Watch=new ArrayList<Integer>();	
		 String url_config="";
		 String api_config="Config";
		 String apiname_config="";
		 Hashtable<String,Integer> table=null;
		 int rowsConfig=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsConfig;rNum++){
			 apiname_config=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_config.equals(api_config)){
				 url_config=xls.getCellData("Api", "Url", rNum);
				 Map map_config=BasePageV2.apiparams(0, xls, api_config);
				 Response resp_config=Utilities.requestUtilitypostOld(url_config, map_config);
				 resp_config.then().assertThat().statusCode(200);
				 //resp_config.prettyPrint();
				 Map<String,Integer> ott=resp_config.jsonPath().get("assets.OTT");
				 for(Map.Entry<String, Integer> m :ott.entrySet()) {
					 if(m.getKey().equals("EPISODE_TYPE"))
						Listof_Watch.add(m.getValue());	
				 }
			 }		 
		 }	  
	 
		//Click on Watch tab
		 homepagev2.tabClick("Watch");
		 Utilities.verticalSwipe(driver); 
		//Scroll to shows tray
		 for(int scroll=0;scroll<=4;scroll++) {
			 if(Utilities.explicitWaitClickable(driver, watchpagev2.allKidsCharacters2, 10)) {
				 try {
					 watchpagev2.allKidsCharacters2.click();
					 test.log(LogStatus.INFO, "Clicked on 2nd character from kids characters tray");
					 clickedOnShow=true;
					 break;
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click on 2nd character from kids characters tray");
				 }
				 break;
			 }
			 else {
				 Utilities.verticalSwipe(driver); 
				 if(scroll==4) {
					 test.log(LogStatus.FAIL, "2nd character from kids characters tray is not clickable");
				 }
			 }
		 }
		 
		 if(clickedOnShow==true) {
			 Utilities.verticalSwipe(driver);
			 for(int findtray=0;findtray<=3;findtray++) {
				 if(Utilities.explicitWaitClickable(driver, showpagev2.episodesTray, 5)) {
					 try {
						 showpagev2.episodesTray.click();
						 test.log(LogStatus.INFO, "Clicked on Episodes tray");
						 clickedEpisodesTray=true;
					 }
					 catch (Exception e){
						 test.log(LogStatus.INFO, "Failed to click on Episodes tray");
					 }
					 break;
				 }
				 else {
					 Utilities.verticalSwipe(driver);
					 if(findtray==3) {
						 test.log(LogStatus.INFO, "Episodes tray is not clickable");  
					 }
				 }
			 }
		 }
		 
		 if(clickedEpisodesTray==true) {
			 //Find items and click
			 for(int count=0;count<episodes.size();count++) {
				 String episodeLocation="//android.widget.TextView[contains(@text,\""+episodes.get(count)+"\")]";
				 try {
					 driver.findElement(By.xpath(episodeLocation)).click();
					 test.log(LogStatus.INFO, "Tapped on episode "+(count+1)+" : "+episodes.get(count));
					 homepagev2.verifyAndProgressBar();
					 test.log(LogStatus.INFO, "Episode started to play");
					 if(count==episodes.size()-1) {
						 kidsplayerv2.pauseVideo();
						 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
					 }
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to close the player");	 
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 Utilities.verticalSwipe(driver);
					 try {
						 driver.findElement(By.xpath(episodeLocation)).click();
						 test.log(LogStatus.INFO, "Tapped on episode "+(count+1)+" : "+episodes.get(count));
						 homepagev2.verifyAndProgressBar();
						 test.log(LogStatus.INFO, "Episode started to play");
						 if(count==episodes.size()-1) {
							 kidsplayerv2.pauseVideo();
							 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
						 }
						 driver.navigate().back();
						 test.log(LogStatus.INFO, "Navigated back to close the player");
					 }
					 catch(Exception e1) {
						 test.log(LogStatus.INFO, "Some error occured with launching player for episode: "+episodes.get(count));
					 }			 
				 }
			 }
			 verifylastViewed=true;
		 }
		for(int wait1=0;wait1<=3;wait1++) {
			 for(int wait=0;wait<=60;wait++) {
				 Thread.sleep(1000);
			 }
		}
		test.log(LogStatus.INFO, "Waited for 3 minutes");
		try {
			driver.closeApp();
		} 
		catch (Exception e) {}
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKbetaSplashActivity"));
		 
		 if(verifylastViewed==true) {
			 //Call Last Viewed for Watch API
			 String api_LastViewedWatch="LV Watch";
			 int totalitemsofapiLastViewedWatch=0;
			 String url_LastViewedWatch="";
			 String mediaID="";
			 String lastViewedWatch="";
			 int rowsLastViewedWatch=xls.getRowCount("Api");
			 for(int rNum=1;rNum<=rowsLastViewedWatch;rNum++){
				 String apiname=xls.getCellData("Api", "API Name", rNum);
				 if(apiname.equals(api_LastViewedWatch)){
					 url_LastViewedWatch=xls.getCellData("Api", "Url", rNum);
					 Map map_LastViewedWatch=BasePageV2.apiparams(2, xls, api_LastViewedWatch);
					 Response resp_LastViewedWatch=Utilities.requestUtilitypostOld(url_LastViewedWatch, map_LastViewedWatch);
					 totalitemsofapiLastViewedWatch=resp_LastViewedWatch.jsonPath().get("assets.items.size()");
					 //Check media ID for 9 items
					 for(int i=0;i<totalitemsofapiLastViewedWatch;i++) {
						 String apiLastViewedTitle=resp_LastViewedWatch.jsonPath().get("assets.items["+i+"].title");
						 String apiLastViewedNumber=resp_LastViewedWatch.jsonPath().get("assets.items["+i+"].episodeNo");
						 String apiLastViewedSeasonString=resp_LastViewedWatch.jsonPath().get("assets.items["+i+"].season");
						 //String apiLastViewedSeason=Integer.toString(apiLastViewedSeasonInt);
						 int mediaType=resp_LastViewedWatch.jsonPath().get("assets.items["+i+"].mediaType"); 
						 String epi="E"+apiLastViewedNumber+" S"+apiLastViewedSeasonString+" - "+apiLastViewedTitle;
						 if(Listof_Watch.contains(mediaType))
							 test.log(LogStatus.INFO, "Episode from Last Viewed API "+epi+" is of Episode type");
						 else {
							 test.log(LogStatus.FAIL, "Episode from Last Viewed API "+epi+" is of not of Episode type");
							 errVK_1502++;
						 }
						 episodesLastViewed.add(epi);		
					 }	
					 if(errVK_1502==0) {
						 test.log(LogStatus.PASS, "All Last Viewed cards in Watch tab are of Episodes type as per API");
						 homepagev2.smokeresults("Verify the type of cards available under 'Last Viewed' section",row_VK_1502, "PASS");
						 test.log(LogStatus.PASS, "Verify the type of cards available under 'Last Viewed' section is PASS"); 			
					 }
					 else {
						 test.log(LogStatus.FAIL, "Not all Last Viewed cards in Watch tab are of Episodes type as per API"); 
						 homepagev2.smokeresults("Verify the type of cards available under 'Last Viewed' section",row_VK_1502, "FAIL");
						 test.log(LogStatus.FAIL, "Verify the type of cards available under 'Last Viewed' section is FAIL"); 		
					 }
				 }
			 }
			 
			 driver.navigate().back();
			 driver.navigate().back();
			 Utilities.verticalSwipeReverse(driver);
			 Utilities.verticalSwipeReverse(driver);
			 homepagev2.tabClick("My Stuff");
			 homepagev2.tabClick("Watch");
			 //Scroll to Last Viewed
			 String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
			 Utilities.verticalSwipeAndFind(driver, recentViewedClear);
			 test.log(LogStatus.INFO, "Swiped till Last viewed tray");
			 
			//Scroll till Last Viewed
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
			//Check for 1st latest
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
		    	test.log(LogStatus.INFO, "Verified Last Viewed tray is present with one card");
		    	homepagev2.smokeresults("Verify availability of 'Last Viewed' section",row_VK_1497, "PASS");
				test.log(LogStatus.PASS, "Verify availability of 'Last Viewed' section is PASS"); 
				test.log(LogStatus.INFO, "-------------------------------------------");
		    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
		    	if(episodes.get(8).contains(lastViewed1)) {
		    		test.log(LogStatus.INFO, lastViewed1+" is the first latest episode under last viewed in Watch tab");
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemDuration, 5)) {
		    			test.log(LogStatus.INFO, "Located duration for first episode");
		    			durationUI=homepagev2.lastViewedFirstItemDuration.getAttribute("text");
		    			durationAPI=episodesDuration.get(8);
		    			if(durationUI.contains(durationAPI)){
		    				test.log(LogStatus.INFO, "Duration from Last Viewed API "+durationUI+" matches with duration displayed in UI for "+lastViewed1);
		    			}
		    			else {
		    				test.log(LogStatus.FAIL, "Duration from Last Viewed API "+durationUI+" does not matches with duration displayed in UI for "+lastViewed1);
		    				errVK_1504++;
		    			}
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate duration for first episode");
		    			errVK_1504++;
		    		}
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemProgressBar, 5)) {
		    			test.log(LogStatus.INFO, "Located progress bar for first episode "+lastViewed1);
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate progress bar for first episode "+lastViewed1);
		    			errVK_1504++;
		    		}	
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in Watch tab instead of "+episodes.get(8));
		    		errVK_1501++;
		    		errVK_1504++;
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episode 1 is not visible under last viewed in Watch tab");
		    	homepagev2.smokeresults("Verify availability of 'Last Viewed' section",row_VK_1497, "FAIL");
				test.log(LogStatus.FAIL, "Verify availability of 'Last Viewed' section is FAIL");
				errVK_1501++;
				errVK_1504++;
		    }
		    //Check for 2nd latest
		    test.log(LogStatus.INFO, "-------------------------------------------");
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
		    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
		    	if(episodes.get(7).contains(lastViewed2)) {
		    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed in Watch tab");		    	
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemDuration, 5)) {
		    			test.log(LogStatus.INFO, "Located duration for second episode");
		    			durationUI=homepagev2.lastViewedSecondItemDuration.getAttribute("text");
		    			durationAPI=episodesDuration.get(7);
		    			if(durationUI.contains(durationAPI)){
		    				test.log(LogStatus.INFO, "Duration from Last Viewed API "+durationUI+" matches with duration displayed in UI for "+lastViewed2);
		    			}
		    			else {
		    				test.log(LogStatus.FAIL, "Duration from Last Viewed API "+durationUI+" does not matches with duration displayed in UI for "+lastViewed2);
		    				errVK_1503++;
		    			}
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate duration for second episode");
		    			errVK_1503++;
		    		}
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemProgressBar, 5)) {
		    			test.log(LogStatus.INFO, "Located progress bar for second episode "+lastViewed2);
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate progress bar for second episode "+lastViewed2);
		    			errVK_1503++;
		    		}	
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed in My Stuff tab instead of "+episodes.get(7));	
		    		errVK_1501++;
		    		errVK_1503++;
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episode 2 is not visible under last viewed in Watch tab");
		    	errVK_1501++;
		    	errVK_1503++;
		    }
		    //Check for 3rd latest
		    test.log(LogStatus.INFO, "-------------------------------------------");
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdItemTitle, 10)) {
		    	lastViewed3=homepagev2.lastViewedThirdItemTitle.getAttribute("text");
		    	if(episodes.get(6).contains(lastViewed3)) {
		    		test.log(LogStatus.INFO, lastViewed3+" is the third latest episode under last viewed in Watch tab");			
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdItemDuration, 5)) {
		    			test.log(LogStatus.INFO, "Located duration for third episode");
		    			durationUI=homepagev2.lastViewedThirdItemDuration.getAttribute("text");
		    			durationAPI=episodesDuration.get(6);
		    			if(durationUI.contains(durationAPI)){
		    				test.log(LogStatus.INFO, "Duration from Last Viewed API "+durationUI+" matches with duration displayed in UI for "+lastViewed3);
		    			}
		    			else {
		    				test.log(LogStatus.FAIL, "Duration from Last Viewed API "+durationUI+" does not matches with duration displayed in UI for "+lastViewed3);
		    				errVK_1503++;
		    			}
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate duration for third episode");
		    			errVK_1503++;
		    		}
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdItemProgressBar, 5)) {
		    			test.log(LogStatus.INFO, "Located progress bar for third episode "+lastViewed3);
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate progress bar for third episode "+lastViewed3);
		    			errVK_1503++;
		    		}	
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest episode under last viewed in Watch tab instead of "+episodes.get(6)); 		
		    		errVK_1501++;
		    		errVK_1503++;
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episode 3 is not visible under last viewed in Watch tab");
		    	errVK_1501++;
		    	errVK_1503++;
		    }
		    //Check for 4th latest
		    test.log(LogStatus.INFO, "-------------------------------------------");
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthItemTitle, 10)) {
		    	lastViewed4=homepagev2.lastViewedFourthItemTitle.getAttribute("text");
		    	if(episodes.get(5).contains(lastViewed4)) {
		    		test.log(LogStatus.INFO, lastViewed4+" is the fourth latest episode under last viewed in Watch tab");			
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthItemDuration, 5)) {
		    			test.log(LogStatus.INFO, "Located duration for fourth episode");
		    			durationUI=homepagev2.lastViewedFourthItemDuration.getAttribute("text");
		    			durationAPI=episodesDuration.get(5);
		    			if(durationUI.contains(durationAPI)){
		    				test.log(LogStatus.INFO, "Duration from Last Viewed API "+durationUI+" matches with duration displayed in UI for "+lastViewed4);
		    			}
		    			else {
		    				test.log(LogStatus.FAIL, "Duration from Last Viewed API "+durationUI+" does not matches with duration displayed in UI for "+lastViewed4);
		    				errVK_1503++;
		    			}
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate duration for fourth episode");
		    			errVK_1503++;
		    		}
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthItemProgressBar, 5)) {
		    			test.log(LogStatus.INFO, "Located progress bar for fourth episode "+lastViewed4);
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate progress bar for first episode "+lastViewed4);
		    			errVK_1503++;
		    		}	
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed4+" is the fourth latest episode under last viewed in Watch tab instead of "+episodes.get(5)); 		
		    		errVK_1501++;
		    		errVK_1503++;
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episode 4 is not visible under last viewed in Watch tab");
		    	errVK_1501++;
		    	errVK_1503++;
		    }
		    //Verify for 7th latest
		    test.log(LogStatus.INFO, "-------------------------------------------");
		    Utilities.verticalSwipe(driver);
		    Utilities.verticalSwipe(driver);
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhItemTitle, 10)) {
		    	lastViewed7=homepagev2.lastViewedSeventhItemTitle.getAttribute("text");
		    	if(episodes.get(2).contains(lastViewed7)) {
		    		test.log(LogStatus.INFO, lastViewed7+" is the seventh latest episode under last viewed in Watch tab");	
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhItemDuration, 5)) {
		    			test.log(LogStatus.INFO, "Located duration for seventh episode");
		    			durationUI=homepagev2.lastViewedSeventhItemDuration.getAttribute("text");
		    			durationAPI=episodesDuration.get(2);
		    			if(durationUI.contains(durationAPI)){
		    				test.log(LogStatus.INFO, "Duration from Last Viewed API "+durationUI+" matches with duration displayed in UI for "+lastViewed7);
		    			}
		    			else {
		    				test.log(LogStatus.FAIL, "Duration from Last Viewed API "+durationUI+" does not matches with duration displayed in UI for "+lastViewed7);
		    				errVK_1503++;
		    			}
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate duration for seventh episode");
		    			errVK_1503++;
		    		}
		    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhItemProgressBar, 5)) {
		    			test.log(LogStatus.INFO, "Located progress bar for seventh episode "+lastViewed7);
		    		}
		    		else {
		    			test.log(LogStatus.FAIL, "Failed to locate progress bar for seventh episode "+lastViewed7);
		    			errVK_1503++;
		    		}	
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed7+" is the seventh latest episode under last viewed in Watch tab instead of "+episodes.get(2)); 		
		    		errVK_1501++;
		    		errVK_1503++;
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed episode 7 is not visible under last viewed in Watch tab");
		    	errVK_1501++;
		    	errVK_1503++;
		    }
		    //Verify for 8th latest
		    test.log(LogStatus.INFO, "-------------------------------------------");
		    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthItemTitle, 10)) {
		    	lastViewed8=homepagev2.lastViewedEighthItemTitle.getAttribute("text");
		    	if(episodes.get(1).contains(lastViewed8)) {
			    	test.log(LogStatus.INFO, lastViewed8+" is the eighth latest episode under last viewed in Watch tab");	
			    	if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthItemDuration, 5)) {
			    		test.log(LogStatus.INFO, "Located duration for eighth episode");
			    		durationUI=homepagev2.lastViewedEighthItemDuration.getAttribute("text");
			    		durationAPI=episodesDuration.get(1);
			    		if(durationUI.contains(durationAPI)){
			    			test.log(LogStatus.INFO, "Duration from Last Viewed API "+durationUI+" matches with duration displayed in UI for "+lastViewed8);
			    		}
			    		else {
			    			test.log(LogStatus.FAIL, "Duration from Last Viewed API "+durationUI+" does not matches with duration displayed in UI for "+lastViewed8);
			    			errVK_1503++;
			    		}
			    	}
			    	else {
			    		test.log(LogStatus.FAIL, "Failed to locate duration for eighth episode");
			    		errVK_1503++;
			    	}
			    	if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthItemProgressBar, 5)) {
			    		test.log(LogStatus.INFO, "Located progress bar for eighth episode "+lastViewed8);
			    	}
			    	else {
			    		test.log(LogStatus.FAIL, "Failed to locate progress bar for eighth episode "+lastViewed8);
			    		errVK_1503++;
			    	}	
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, lastViewed8+" is the eighth latest episode under last viewed in Watch tab instead of "+episodes.get(1)); 		
		    	errVK_1501++;
		    	errVK_1503++;
		    }
		    //Verify that the first watched is deleted from last viewed
		    if(episodes.get(0).contains(lastViewed8)) {
		    	test.log(LogStatus.FAIL, episodes.get(0)+" is not cleared from last viewed tray, FIFO order not followed");			
		    	errVK_1501++;
		    }
		    else {
		    	test.log(LogStatus.INFO, episodes.get(0)+" is cleared from last viewed tray, FIFO order is followed");
		    }
		}
		if(errVK_1501==0) {
			homepagev2.smokeresults("Verify the cards removal order under 'Last Viewed' section when user watches more than 8 VOD contents",row_VK_1501, "PASS");
			test.log(LogStatus.PASS, "Verify the cards removal order under 'Last Viewed' section when user watches more than 8 VOD contents  is PASS");  
		}
		else {
			 homepagev2.smokeresults("Verify the cards removal order under 'Last Viewed' section when user watches more than 8 VOD contents",row_VK_1501, "FAIL");
			 test.log(LogStatus.FAIL, "Verify the cards removal order under 'Last Viewed' section when user watches more than 8 VOD contents is FAIL");  
		}
		if(errVK_1503==0) {
			 homepagev2.smokeresults("Verify episode card metadata for partially watched contents",row_VK_1503, "PASS");
			 test.log(LogStatus.PASS, "Verify episode card metadata for partially watched contents is PASS"); 
		}
		else {
			 homepagev2.smokeresults("Verify episode card metadata for partially watched contents",row_VK_1503, "FAIL");
			 test.log(LogStatus.FAIL, "Verify episode card metadata for partially watched contents is FAIL"); 
		}
		if(errVK_1504==0) {
			 homepagev2.smokeresults("Verify episode card metadata for completely watched content",row_VK_1503, "PASS");
			 test.log(LogStatus.PASS, "Verify episode card metadata for completely watched content is PASS"); 
		}
		else {
			 homepagev2.smokeresults("Verify episode card metadata for completely watched content",row_VK_1503, "FAIL");
			 test.log(LogStatus.FAIL, "Verify episode card metadata for completely watched content is FAIL"); 
		}
		
		
		homepagev2.mystuff_tab=null;
		watchpagev2.allKidsCharacters2=null;
		showpagev2.episodesTray=null;
		showpagev2.showDetailsEpisode1=null;
		showpagev2.showDetailsEpisode1Title=null;
		showpagev2.showDetailsEpisode2=null;
		showpagev2.showDetailsEpisode2Title=null;
		showpagev2.showDetailsEpisode3=null;
		showpagev2.showDetailsEpisode3Title=null;
		homepagev2.lastViewedFirstItemTitle=null;
		homepagev2.lastViewedFirstItemDuration=null;
		homepagev2.lastViewedFirstItemProgressBar=null;
		homepagev2.lastViewedSecondItemTitle=null;
		homepagev2.lastViewedSecondItemDuration=null;
		homepagev2.lastViewedSecondItemProgressBar=null;
		homepagev2.lastViewedThirdItemTitle=null;
		homepagev2.lastViewedThirdItemDuration=null;
		homepagev2.lastViewedThirdItemProgressBar=null;
		homepagev2.recentViewedClear=null;
		homepagev2.recentViewedClearMessage=null;
		homepagev2.recentViewedClearYes=null;
		homepagev2.recentViewedClearNo=null;
		homepagev2.lastViewedFourthItemTitle=null;
		homepagev2.lastViewedSeventhItemTitle=null;
		homepagev2.lastViewedEighthItemTitle=null;
		settingspagev2.switchProfileCancel=null;
		homepagev2.profileIcon=null;
		
		
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
