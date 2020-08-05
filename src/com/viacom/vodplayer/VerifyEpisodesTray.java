package com.viacom.vodplayer;

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

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.CharacterDetailsV2;
import com.viacom.pagesVersion2.HomePageV2;
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
public class VerifyEpisodesTray extends BaseTestV2{
	
	String testName = "VerifyEpisodesTray";
	@Test(dataProvider = "getData")
	public void VerifyEpisodesTray(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyEpisodesTray");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// VK_389 Verify the UI of Episodes section , P2
		// VK_390 Verify the Episodes section UI when the tray has <=8 card , P2
		// VK_391 Verify the See All button functionality, P2
		// VK_392 Verify the functionality by tapping tray title, P2
		// VK_393 Verify the sorting order of the episodes by default, P2
		// VK_397 Verify the sorting order of the episodes by default for a show in All  Episodes screen , P1
		// VK_398 Verify the Scrolling functionality in All Episodes screen, P3
		// VK_401 Verify the episode card metadata, P2
	
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 CharacterDetailsV2 chardetailsv2=new CharacterDetailsV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		//Login module 
		//homepagev2.logout();
		//homepagev2.login(data.get("Email"),data.get("Password"));
		 
		 int err389=0;
		 int err401=0;
		 int err397=0;
		 
		 //Fetch data from API
		 String api_AllCharacters="All Characters";
		 int totalitemsofapiAllCharacters=0;
		 String url_AllCharacters="";
		 ArrayList<String> carousalCardsFromAPIAllCharacters=new ArrayList<String>();
		 String carousalCardsInListAllCharacters="";
		 String firstMediaID="";
		 int rowsAllCharacters=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsAllCharacters;rNum++){
			 String apiname=xls.getCellData("Api", "API Name", rNum);
			 if(apiname.equals(api_AllCharacters)){
				 url_AllCharacters=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(2, xls, api_AllCharacters);
				 Response resp_AllCharacters=Utilities.requestUtilitypostOld(url_AllCharacters, map);
				 //resp_allCharacters.prettyPrint();
				 totalitemsofapiAllCharacters=resp_AllCharacters.jsonPath().get("assets.items.size()");
				 //Get the first item media id from the API
				 firstMediaID=resp_AllCharacters.jsonPath().get("assets.items[1].mId");
				 if(firstMediaID.equals("") || firstMediaID==null) {
					 test.log(LogStatus.INFO, "Second Character media ID is blank from API");
				 }
			 }
		 }
		 
		 //Call the API to get list of Episodes for the fetched media ID 
		 // https://api.vootkids.com/app/ks/v1/allKidsEpisodes.json?pageSize=1&pageIndex=&refSeriesId=1&uId=1&profileId=1&ks=1&mediaId=622094&pageSize=20&pageIndex=0&refSeriesId=622094
		 String url_APIepisodeList="https://api.vootkids.com/app/ks/v1/allKidsEpisodes.json?pageSize=1&pageIndex=&refSeriesId=1&uId=1&profileId=1&ks=1&mediaId="+firstMediaID+"&pageSize=20&pageIndex=0&refSeriesId="+firstMediaID;
		 //ArrayList<String> itemsFromAPIEpisodes=new ArrayList<String>();
		 HashMap<String,String> map=new HashMap<String,String>();//empty map
		 Response resp_EpisodeList=Utilities.requestUtilitypostOld(url_APIepisodeList, map);
		 int totalitemsofAPIEpisodeList=resp_EpisodeList.jsonPath().get("assets.items.size()");
		 //String test1=resp_EpisodeList.jsonPath().get("assets.items[19].mId");

		 homepagev2.tabClick("Watch");
		 for(int scroll=0;scroll<=4;scroll++) {
			 if(Utilities.explicitWaitVisible(driver, watchpagev2.allKidsCharacters2, 5)) 
				 break;
			 else
				 Utilities.verticalSwipe(driver); 
		 }
		 //Clicking on Episodes tray
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.allKidsCharacters2, 10)) {
			 try {
				 watchpagev2.allKidsCharacters2.click();
				 test.log(LogStatus.INFO, "Clicked on 2nd character from ALL KIDS CHARACTERS tray");
				 Thread.sleep(2000);
				 Utilities.verticalSwipe(driver);
				 for(int findtray=0;findtray<=3;findtray++) {
					 if(Utilities.explicitWaitClickable(driver, showpagev2.episodesTray, 10)) {
						 test.log(LogStatus.INFO, "Located Episodes tray"); 
						 try {
							 showpagev2.episodesTray.click();
							 test.log(LogStatus.INFO, "Clicked on Episodes tray"); 
							 if(Utilities.explicitWaitVisible(driver, showpagev2.episodesPageTitle, 30)) {
								 if(showpagev2.episodesPageTitle.getAttribute("text").equalsIgnoreCase("EPISODES")) {
									 test.log(LogStatus.INFO, "Tapping on Episodes tray title 'EPISODES' page is displayed");
									 test.log(LogStatus.PASS,"Verify the functionality by tapping tray title is PASS"); 
									 if(!Utilities.setResultsKids("VK_392", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 }
								 else {
									 test.log(LogStatus.FAIL, "Tapping on Episodes tray title 'EPISODES' page is not displayed"); 
									 test.log(LogStatus.FAIL,"Verify the functionality by tapping tray title is FAIL"); 
									 if(!Utilities.setResultsKids("VK_392", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 BasePageV2.takeScreenshot();
								 }
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to Show details page");

							 }
							 else {
								 test.log(LogStatus.INFO, "Episodes page did not launch"); 
							 }
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Failed to Click on Episodes tray");
						 }
						 break;
					 }
					 else {
						 Thread.sleep(1000);
						 Utilities.verticalSwipe(driver);
						 if(findtray==3) {
							 test.log(LogStatus.FAIL, "Could not locate Episodes tray");
							 err389++;
						 }
					 }
				 }
				 if(err389>0) {
					 if(!Utilities.setResultsKids("VK_389", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 if(!Utilities.setResultsKids("VK_390", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 if(!Utilities.setResultsKids("VK_391", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 if(!Utilities.setResultsKids("VK_392", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 if(!Utilities.setResultsKids("VK_393", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 if(!Utilities.setResultsKids("VK_397", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 if(!Utilities.setResultsKids("VK_398", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 if(!Utilities.setResultsKids("VK_401", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 homepagev2.takeScreenshot();
					 BasePageV2.reportFail("Terminating since could not locate Episodes tray");
					 
				 }
				 //See All should not be present if total items are below 8
				 if(totalitemsofAPIEpisodeList<=8) {
					 test.log(LogStatus.INFO, "Total episodes returned from API is "+totalitemsofAPIEpisodeList);
					 //Scroll twice and search for SEE ALL
					 for(int findseeall=0;findseeall<=1;findseeall++) {
						 if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 10)) {
							 test.log(LogStatus.FAIL, "SEE ALL button is displayed in UI even though API returned <=8 episodes");
							 test.log(LogStatus.FAIL, "Verify the Episodes section UI when the tray has <=8 card is FAIL");
							 if(!Utilities.setResultsKids("VK_390", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 break;
						 }
						 else {
							 Utilities.verticalSwipe(driver);
							 if(findseeall==1) {
								 test.log(LogStatus.INFO, "Could not locate SEE ALL button in UI because API returned <=8 episodes"); 
								 test.log(LogStatus.PASS, "Verify the Episodes section UI when the tray has <=8 card is PASS");
								 if(!Utilities.setResultsKids("VK_390", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 }
						 }
					 }
				 }
				 
				 //See All should be present if total items are above 8
				 if(totalitemsofAPIEpisodeList>8) {
					//Scroll to the sixth card using description
					 String apiItem6=resp_EpisodeList.jsonPath().get("assets.items[5].title");
					 if(apiItem6.equals("") || apiItem6==null) 
						 test.log(LogStatus.FAIL, "Title of 6th episode is blank from API");
					 String uiItemDescLocator6="//android.widget.TextView[contains(@text,\""+apiItem6+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
					 boolean item6Presence=false;
					 for(int scroll=0;scroll<5;scroll++) {
						 item6Presence=Utilities.verticalSwipeAndFind(driver,uiItemDescLocator6);
						 if(item6Presence==true) 
							break; 		 
					 }
					 if(item6Presence==true) {
						Utilities.verticalSwipe(driver);
						test.log(LogStatus.INFO, "Scrolled and located sixth item");
						//Check for availability of See All
						if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 3)){
							test.log(LogStatus.INFO, "Located See All button");
							//Click on SEE ALL
							try {
								homepagev2.seeAll.click(); 
								test.log(LogStatus.INFO, "Clicked on See All button");
								if(Utilities.explicitWaitVisible(driver, homepagev2.backButton, 10)) {
									test.log(LogStatus.INFO, "Located Back button");
									if(showpagev2.episodesPageTitle.getAttribute("text").equalsIgnoreCase("EPISODES")) {
										 test.log(LogStatus.INFO, "Tapping on SEE ALL button, 'EPISODES' page is displayed");
										 test.log(LogStatus.PASS,"Verify the See All button functionality is PASS"); 
										 if(!Utilities.setResultsKids("VK_391", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										//Verification of lazy load
										 Thread.sleep(3000);
										 ArrayList firstListTitles=new ArrayList();
										 ArrayList secondListTitles=new ArrayList();
										 String uiItemTitleLocator="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
										 try {
											 List<WebElement> firstList=driver.findElements(By.xpath(uiItemTitleLocator));
											 for(int count=0;count<firstList.size();count++) {
												 try {
													 String title=firstList.get(count).getAttribute("text");
													 if(!firstListTitles.contains(title))
														 firstListTitles.add(title); 
												 }
												 catch(Exception e) {
													 test.log(LogStatus.INFO, "Failed to fetch title of episode"+(count+1)+" from the first list");
												 }
												 
											 }
											 test.log(LogStatus.INFO, "First list of episode titles: "+firstListTitles);
										 }
										 catch(Exception e) {
											 test.log(LogStatus.FAIL, "Failed to fetch description of episodes before scroll");
										 }	 
										 Utilities.verticalSwipe(driver);
										 Utilities.verticalSwipe(driver);
										 Utilities.verticalSwipe(driver);
										 test.log(LogStatus.INFO, "Swiped vertically twice ..");
										 Thread.sleep(2000);
										 try {
											 List<WebElement> secondList=driver.findElements(By.xpath(uiItemTitleLocator));
											 for(int count=0;count<secondList.size();count++) {
												 try {
													 String title=secondList.get(count).getAttribute("text");
													 if(!secondListTitles.contains(title))
														 secondListTitles.add(title); 
												 }
												 catch(Exception e) {
													 test.log(LogStatus.INFO, "Failed to fetch title of episode"+(count+1)+" from the second list");
												 }
												 
											 }
											 test.log(LogStatus.INFO, "Second list of episode titles: "+secondListTitles);
										 }
										 catch(Exception e) {
											 test.log(LogStatus.FAIL, "Failed to fetch titles of episodes after scroll");
										 } 
										 
										 if(firstListTitles.equals(secondListTitles)) {
											test.log(LogStatus.INFO, "Episodes before and after scroll are same ..");
											test.log(LogStatus.FAIL,"Verify the Scrolling functionality in All  Episodes screen is FAIL"); 
											if(!Utilities.setResultsKids("VK_398", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											BasePageV2.takeScreenshot();
										 }
										 else {
											test.log(LogStatus.INFO, "Episodes before and after scroll vary ..");
											test.log(LogStatus.PASS,"Verify the Scrolling functionality in All  Episodes screen is PASS"); 
											if(!Utilities.setResultsKids("VK_398", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										 }	  
										 //Scroll to the top
										 Utilities.verticalSwipeReverse(driver);
										 Utilities.verticalSwipeReverse(driver);
										 Utilities.verticalSwipeReverse(driver);
										 Utilities.verticalSwipeReverse(driver);
										 Utilities.verticalSwipeReverse(driver);
										 test.log(LogStatus.INFO, "Scrolled to the top");

										 //Verify meta data and the order of items
										 if(totalitemsofAPIEpisodeList>10) {
											 totalitemsofAPIEpisodeList=10;
										 }
										 ArrayList<String> descList=new ArrayList<String>();
										 test.log(LogStatus.INFO, "Verification of meta data for each item:");
										 test.log(LogStatus.INFO, "------------------------------------------------");
										 for(int count=0;count<totalitemsofAPIEpisodeList;count++) {
											 if(count==6)
												 Utilities.verticalSwipe(driver);
											 Thread.sleep(1000);
											 //String apiItemShowTitle=resp_EpisodeList.jsonPath().get("assets.items["+count+"].refSeriesTitle");
											 String apiItemEpisodeTitle=resp_EpisodeList.jsonPath().get("assets.items["+count+"].title");
											 if(apiItemEpisodeTitle.equals("") || apiItemEpisodeTitle==null) 
												 test.log(LogStatus.FAIL, "Episode Title is blank for item "+count+" from API");
											 int apiItemDuration=resp_EpisodeList.jsonPath().get("assets.items["+count+"].duration");
											 int apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toMinutes(apiItemDuration);					 
											 if(apiItemDurationInt<=1) 
												 apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toSeconds(apiItemDuration);
											 String apiItemDurationString=Integer.toString((int) apiItemDurationInt);
											 if(apiItemDurationString.equals("") || apiItemDurationString==null) 
												 test.log(LogStatus.FAIL, "Episode Duration is blank for item "+count+" from API");
											 String apiItemEpisodeNumber=resp_EpisodeList.jsonPath().get("assets.items["+count+"].episodeNo");
											 if(apiItemEpisodeNumber.equals("") || apiItemEpisodeNumber==null) 
												 test.log(LogStatus.FAIL, "Episode Number is blank for item "+count+" from API");
											 String apiItemDesc=resp_EpisodeList.jsonPath().get("assets.items["+count+"].desc");
											 if(apiItemDesc.equals("") || apiItemDesc==null) 
												 test.log(LogStatus.FAIL, "Episode Description is blank for item "+count+" from API");
											 String uiItemShowTitleLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
											 String uiItemDurationLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']";
											 String uiItemDescLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
											 String uiItemImageLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/grid_item_category']";
											 try {	 
												 String uiItemEpisodeDesc=driver.findElement(By.xpath(uiItemDescLocator)).getText();
												 descList.add(uiItemEpisodeDesc);
												 if(uiItemDescLocator.contains(apiItemEpisodeTitle)) 
													 test.log(LogStatus.INFO, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is matching in API and UI for "+apiItemEpisodeTitle);
												 else {
													 test.log(LogStatus.FAIL, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is NOT matching in API and UI for "+apiItemEpisodeTitle);
													 err401++;
													 err397++;
												 }
												 String uiItemDuration=driver.findElement(By.xpath(uiItemDurationLocator)).getText();
												 if(uiItemDuration.contains(apiItemDurationString)) 
													 test.log(LogStatus.INFO, "Meta Data->Duration: "+uiItemDuration+" is matching in API and UI for "+apiItemEpisodeTitle);
												 else {
													 test.log(LogStatus.FAIL, "Meta Data->Duration: "+uiItemDuration+" is NOT matching in API and UI for "+apiItemEpisodeTitle);
													 err401++;
												 }
												 if(uiItemEpisodeDesc.contains(apiItemEpisodeNumber)) 
													 test.log(LogStatus.INFO, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is matching in API and UI for "+apiItemEpisodeTitle);
												 else {
													 test.log(LogStatus.FAIL, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is NOT matching in API and UI for "+apiItemEpisodeTitle);
													 err401++;
												 }
												 try{
													 driver.findElement(By.xpath(uiItemImageLocator));
													 test.log(LogStatus.INFO, "Media icon is verified present in UI for "+apiItemEpisodeTitle);
												 }
												 catch(Exception e2) {
													 test.log(LogStatus.FAIL, "Media icon is missing in UI for "+apiItemEpisodeTitle);
													 err401++;
												 }
											 }
											 catch(Exception e) {
												 Utilities.verticalSwipe(driver);
												 Thread.sleep(2000);
												 try {
													 String uiItemEpisodeDesc=driver.findElement(By.xpath(uiItemDescLocator)).getText();
													 descList.add(uiItemEpisodeDesc);
													 if(uiItemDescLocator.contains(apiItemEpisodeTitle)) 
														 test.log(LogStatus.INFO, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is matching in API and UI for "+apiItemEpisodeTitle);
													 else {
														 test.log(LogStatus.FAIL, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is NOT matching in API and UI for "+apiItemEpisodeTitle);
														 err401++;
														 err397++;
													 }
													 String uiItemDuration=driver.findElement(By.xpath(uiItemDurationLocator)).getText();
													 if(uiItemDuration.contains(apiItemDurationString)) 
														 test.log(LogStatus.INFO, "Meta Data->Duration: "+uiItemDuration+" is matching in API and UI for "+apiItemEpisodeTitle);
													 else {
														 test.log(LogStatus.FAIL, "Meta Data->Duration: "+uiItemDuration+" is NOT matching in API and UI for "+apiItemEpisodeTitle);
														 err401++;
													 }
													 if(uiItemEpisodeDesc.contains(apiItemEpisodeNumber)) 
														 test.log(LogStatus.INFO, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is matching in API and UI for "+apiItemEpisodeTitle);
													 else {
														 test.log(LogStatus.FAIL, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is NOT matching in API and UI for "+apiItemEpisodeTitle);
														 err401++;
													 }
													 try{
														 driver.findElement(By.xpath(uiItemImageLocator));
														 test.log(LogStatus.INFO, "Media icon is verified present in UI for "+apiItemEpisodeTitle);
													 }
													 catch(Exception e2) {
														 test.log(LogStatus.FAIL, "Media icon is missing in UI for "+apiItemEpisodeTitle);
														 err401++;
													 }
												 }
												 catch(Exception e1) {
													 test.log(LogStatus.INFO, "Unable to locate item"+count);
												 }
											 }	
											 test.log(LogStatus.INFO, "------------------------------------------------");
										}	
										if(err401==0) {
											test.log(LogStatus.PASS, "Verify the episode card metadata is PASS"); 
											if(!Utilities.setResultsKids("VK_401", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										}
										else {
											test.log(LogStatus.FAIL, "Verify the episode card metadata is FAIL"); 
											if(!Utilities.setResultsKids("VK_401", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											BasePageV2.takeScreenshot();
										}
										if(err397==0) {
											test.log(LogStatus.PASS, "Verify the sorting order of the episodes by default for a show in All  Episodes screen is PASS"); 
											if(!Utilities.setResultsKids("VK_397", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										}
										else {
											test.log(LogStatus.FAIL, "Verify the sorting order of the episodes by default for a show in All  Episodes screen is FAIL"); 
											if(!Utilities.setResultsKids("VK_397", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											BasePageV2.takeScreenshot();
										}
										 
									 }
									 else {
										 test.log(LogStatus.FAIL, "Tapping on SEE ALL button, 'EPISODES' page is not displayed"); 
										 test.log(LogStatus.FAIL, "Verify the See All button functionality is FAIL"); 
										 if(!Utilities.setResultsKids("VK_391", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										 BasePageV2.takeScreenshot();
									 }						
								}
								else {
									test.log(LogStatus.FAIL, "Failed to locate Back button");
								}
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to click SEE ALL button");
							}
						}
						else {
							test.log(LogStatus.FAIL, "Failed to locate See All button");
						}	
					 }
					 else {
						 test.log(LogStatus.FAIL, "Could not locate 6th episode in EPISODES tray"); 
					 }
				 }			 
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on 1st character from ALL KIDS CHARACTERS tray");
			 }
		}
		else 
			test.log(LogStatus.FAIL, "Failed to click on first show under All Kids Characters");
		

	}	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
