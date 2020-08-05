package com.viacom.carousal;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.MobileDriver;
//Author Tanisha
public class VerifyCarouselMetadataAndSwipeFeature extends BaseTestV2{
	
	String testName = "VerifyCarouselMetadataAndSwipeFeature";
	@Test(dataProvider = "getData")
	public void VerifyCarouselMetadataAndSwipeFeature(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		int errCount389=0;
		test = rep.startTest("VerifyCarouselMetadataAndSwipeFeature");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// VK_148: Verify the type of cards available in masthead carousel for My Stuff tab DONE
		// VK_245: Verify the type of cards available in masthead carousel for Watch tab DONE
		// VK_328: Verify the type of cards available in masthead carousel for Read tab DONE
		// VK_351: Verify the type of cards available in masthead carousel for Listen tab DONE
		// VK_139: Validate the Maximum count of assets in the Masthead Carousel section under all tabs. DONE
		// VK_146: Verify the order of displaying cards in Carousal section of My Stuff DONE
		// VK_243: Verify the order of displaying cards in Carousel section of Watch tab DONE
		// VK_326: Verify the order of displaying cards in Carousel section of Read tab DONE
		// VK_349: Verify the order of displaying cards in Carousel section of Listen tab DONE
		// VK_152: Verify user is able swipe horizontal when 5 cards available in carousel in My Stuff tab DONE
		// VK_247: Verify user is able swipe horizontal  in watch tab when 5 cards available DONE
		// VK_330: Verify user is able swipe horizontal  in Read tab when 5 cards available DONE
		// VK_354: Verify user is able swipe horizontal  in Listen tab when 5 cards available DONE
		// VK_149: Verify episode card metadata for the card present in carousel DONE
		// VK_150: Verify book card metadata for the card present in carousel DONE
		// VK_151: Verify audio book card metadata for the card present in carousel
		
		// TCS related to Minimize app and verify, Carousel section UI with 1 card fully visible and 2nd partially visible is NA, hence removed
		//Verify the Carousel section UI in My Stuff tab
		//Verify the Carousel section UI in Watch tab
		//Verify the Carousel section UI in Watch tab
		//Verify the Carousel section UI in Listen tab
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 int errVK_148=0;
		 int errVK_245=0;
		 int errVK_328=0;
		 int errVK_351=0;
		 int errVK_139=0;
		 int errVK_146=0;
		 int errVK_243=0;
		 int errVK_326=0;
		 int errVK_349=0;
		 int errVK_152=0;
		 int errVK_247=0;
		 int errVK_330=0;
		 int errVK_354=0;
		 int errVK_149=0;
		 int errVK_150=0;
		 int errVK_151=0;
		 
		 boolean myStuffAutoScroll=true;
		 boolean watchAutoScroll=false;
		 boolean readAutoScroll=false;
		 boolean audioAutoScroll=false;
		 
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }	
		 		 
		//Calling config.json to get 
		 int api_totalItems_watch=0;
		 int api_totalItems_myStuff=0;
		 String url_watch="";
		 String url_myStuff="";
		 String url_config="";
		 String api_watch="Watch Card Count";
		 String api_myStuff="My Stuff Card Count";
		 String api_config="Config";
		 String apiname_watch="";
		 String apiname_myStuff="";
		 String apiname_config="";
		 ArrayList<Integer> Listof_MyStuff=new ArrayList<Integer>();
		 ArrayList<Integer> Listof_Watch=new ArrayList<Integer>();		 
		 ArrayList<Integer> Listof_Read=new ArrayList<Integer>();
		 ArrayList<Integer> Listof_Audio=new ArrayList<Integer>();
		 Hashtable<String,Integer> table=null;
		 int rowsConfig=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsConfig;rNum++){
			 apiname_config=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_config.equals(api_config)){
				 url_config=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_config);
				 Response resp_config=Utilities.requestUtilitypostOld(url_config, map);
				 resp_config.then().assertThat().statusCode(200);
				 //resp_config.prettyPrint();
				 Map<String,Integer> ott=resp_config.jsonPath().get("assets.OTT");
				 for(Map.Entry<String, Integer> m :ott.entrySet()) {
					 if(m.getKey().equals("EPISODE_TYPE"))
						Listof_Watch.add(m.getValue());	
					 if(m.getKey().equals("EBOOK_TYPE"))
						Listof_Read.add(m.getValue());	
					 if(m.getKey().equals("AUDIO_TYPE"))
						Listof_Audio.add(m.getValue());	
				 }
			 }		 
		 }
		 test.log(LogStatus.INFO, "*********************** Media icon verification for Carousel items in My Stuff tab API ***********************");
		 //Calling getPersonalizedCarousel.json API for My Stuff tab to verify media type for all items			
		 ArrayList<String> myStuffNamesAPI=new ArrayList<String>();
		 ArrayList<String> myStuffDescAPI=new ArrayList<String>();
		 int rowsMyStuff=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsMyStuff;rNum++){
			 apiname_myStuff=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_myStuff.equals(api_myStuff)){
				 url_myStuff=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_myStuff);
				 Response resp_myStuff=Utilities.requestUtilitypostOld(url_myStuff, map);
				 api_totalItems_myStuff=resp_myStuff.jsonPath().get("assets.items.size()");
				 if(api_totalItems_myStuff!=5) { 
					test.log(LogStatus.FAIL, "Get Personalized Carosual for My Stuff tab API has returned "+api_totalItems_myStuff+" items instead of 5");
					errVK_139++;
				 }
				 else 
					test.log(LogStatus.INFO, "Get Personalized Carosual for My Stuff tab API has returned 5 items");
				 
				 for(int item=0; item<api_totalItems_myStuff; item++) {
					 int mediatype=resp_myStuff.jsonPath().get("assets.items["+item+"].mediaType");
					 String title=resp_myStuff.jsonPath().get("assets.items["+item+"].title");
					 String refSeriesTitle=resp_myStuff.jsonPath().get("assets.items["+item+"].refSeriesTitle");
					 String author=resp_myStuff.jsonPath().get("assets.items["+item+"].author");
					 if(Listof_Watch.contains(mediatype)) {
						 test.log(LogStatus.INFO, "Episode media type icon is returned by API for "+title); 
						 myStuffNamesAPI.add(refSeriesTitle);
						 myStuffDescAPI.add(title);
					 }
					 else if(Listof_Read.contains(mediatype)) {
						test.log(LogStatus.INFO, "Book media type icon is returned by API for "+title); 
						myStuffNamesAPI.add(title);
						myStuffDescAPI.add(author);
					 }
					 else if(Listof_Audio.contains(mediatype)) {
						test.log(LogStatus.INFO, "Audio media type icon is returned by API for "+title); 
						myStuffNamesAPI.add(title);
						myStuffDescAPI.add(author);
					 }
					 else {
						test.log(LogStatus.FAIL, "Incorrect Media type icon is returned by API for "+title);
						errVK_148++;
					 }
				 }
			 }
		 }	
	    if(errVK_148==0) {
	    	test.log(LogStatus.PASS, "Verify the type of cards available in masthead carousel for My Stuff tab is PASS");
			if(!Utilities.setResultsKids("VK_148", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available in masthead carousel for My Stuff tab is FAIL");
			if(!Utilities.setResultsKids("VK_148", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		test.log(LogStatus.INFO, "*********************** Media icon verification for Carousel items in Watch tab API ***********************"); 
		//Calling getPersonalizedCarousel.json API for Watch tab to verify media type for Episodes			
		 ArrayList<String> episodeNamesAPI=new ArrayList<String>();
		 ArrayList<String> episodeDescAPI=new ArrayList<String>();
		 int rowsWatch=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsWatch;rNum++){
			 apiname_watch=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_watch.equals(api_watch)){
				 url_watch=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_watch);
				 Response resp_watch=Utilities.requestUtilitypostOld(url_watch, map);
				 api_totalItems_watch=resp_watch.jsonPath().get("assets.items.size()");
				 if(api_totalItems_watch!=5) { 
					test.log(LogStatus.FAIL, "Get Personalized Carosual for Watch tab API has returned "+api_totalItems_watch+" items instead of 5");
					errVK_139++;
				 }
				 else 
					test.log(LogStatus.INFO, "Get Personalized Carosual for Watch tab API has returned 5 items");
				 for(int item=0; item<api_totalItems_watch; item++) {
					 int mediatype=resp_watch.jsonPath().get("assets.items["+item+"].mediaType");
					 String episodeTitle=resp_watch.jsonPath().get("assets.items["+item+"].refSeriesTitle");
					 String refSeriesTitle=resp_watch.jsonPath().get("assets.items["+item+"].title");
					 episodeNamesAPI.add(episodeTitle);
					 episodeDescAPI.add(refSeriesTitle);
					 if(!Listof_Watch.contains(mediatype)) {
						test.log(LogStatus.FAIL, "Episode media type icon is not returned by API for "+episodeTitle); 
						errVK_245++;
					 }
					 else {
						test.log(LogStatus.INFO, "Episode media type icon is returned by API for "+episodeTitle); 
					 }
				 }
			 }
		 }
	    if(errVK_245==0) {
	    	test.log(LogStatus.PASS, "Verify the type of cards available in masthead carousel for Watch tab is PASS");
			if(!Utilities.setResultsKids("VK_245", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available in masthead carousel for Watch tab is FAIL");
			if(!Utilities.setResultsKids("VK_245", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		
		test.log(LogStatus.INFO, "*********************** Media icon verification for Carousel items in Read tab API ***********************");
		//Calling getPersonalizedCarousel.json API for Read tab to verify media type for Books
		ArrayList<String> bookNamesAPI=new ArrayList<String>();
		ArrayList<String> bookDescAPI=new ArrayList<String>();
		int api_totalItems_read=0;
		String url_read="";
		String api_read="Read Card Count";
		String apiname_read="";
		int rowsRead=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rowsRead;rNum++){
			apiname_read=xls.getCellData("Api", "API Name", rNum);
			if(apiname_read.equals(api_read)){
				url_read=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(0, xls, api_read);
				Response resp_read=Utilities.requestUtilitypostOld(url_read, map);
				api_totalItems_read=resp_read.jsonPath().get("assets.items.size()");
				if(api_totalItems_read!=5) {
					test.log(LogStatus.FAIL, "Get Personalized Carosual for Read tab API has returned "+api_totalItems_read+" items instead of 5");
					errVK_139++;
				}
				else 
					test.log(LogStatus.INFO, "Get Personalized Carosual for Read tab API has returned 5 items");
				for(int item=0; item<api_totalItems_read; item++) {
					int mediatype=resp_read.jsonPath().get("assets.items["+item+"].mediaType");
					String bookTitle=resp_read.jsonPath().get("assets.items["+item+"].title");
					String author=resp_read.jsonPath().get("assets.items["+item+"].author");
					bookNamesAPI.add(bookTitle);
					bookDescAPI.add(author);
					if(!Listof_Read.contains(mediatype)) {
						test.log(LogStatus.FAIL, "Book media type icon is not returned by API for "+bookTitle);
						errVK_328++;
					}
					else 
						test.log(LogStatus.INFO, "Book media type icon is returned by API for "+bookTitle); 
				}
			}
		}
	    if(errVK_328==0) {
	    	test.log(LogStatus.PASS, "Verify the type of cards available in masthead carousel for Read tab is PASS");
			if(!Utilities.setResultsKids("VK_328", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available in masthead carousel for Read tab is FAIL");
			if(!Utilities.setResultsKids("VK_328", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		test.log(LogStatus.INFO, "*********************** Media icon verification for Carousel items in Audio tab API ***********************");
		//Calling getPersonalizedCarousel.json API for Audio tab to verify media type for Book name, author and icon
		int api_totalItems_audio=0;
		String url_audio="";
		String api_audio="Audio Card Count";
		String apiname_audio="";
		String apiAudioString="";
		String apiAudioTitle="";
		String apiAudioAuthor="";
		ArrayList<String> audioNamesAPI=new ArrayList<String>();
		ArrayList<String> audioDescAPI=new ArrayList<String>();
		int rowsAudio=xls.getRowCount("Api");
		for(int rNum=1;rNum<=rowsAudio;rNum++){
			apiname_audio=xls.getCellData("Api", "API Name", rNum);
			if(apiname_audio.equals(api_audio)){
				url_audio=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(0, xls, api_audio);
				Response resp_audio=Utilities.requestUtilitypostOld(url_audio, map);
				api_totalItems_audio=resp_audio.jsonPath().get("assets.items.size()");
				if(api_totalItems_audio!=5) {
					test.log(LogStatus.FAIL, "Get Personalized Carosual for Audio tab API has returned "+api_totalItems_audio+" items instead of 5");
					errVK_139++;
				}
				else 
					test.log(LogStatus.INFO, "Get Personalized Carosual for Audio tab API has returned 5 items");
				for(int item=0; item<api_totalItems_audio; item++) {
					int mediatype=resp_audio.jsonPath().get("assets.items["+item+"].mediaType");
					String audioTitle=resp_audio.jsonPath().get("assets.items["+item+"].title");
					String author=resp_audio.jsonPath().get("assets.items["+item+"].author");
					audioNamesAPI.add(audioTitle);
					audioDescAPI.add(author);
					if(!Listof_Audio.contains(mediatype)) {
						test.log(LogStatus.FAIL, "Audio media type icon is not returned by API for "+audioTitle);
						errVK_351++;
					}
					else
						test.log(LogStatus.INFO, "Audio media type icon is returned by API for "+audioTitle);  
				}
			}
		}	
	    if(errVK_351==0) {
	    	test.log(LogStatus.PASS, "Verify the type of cards available in masthead carousel for Listen tab is PASS");
			if(!Utilities.setResultsKids("VK_351", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available in masthead carousel for Listen tab is FAIL");
			if(!Utilities.setResultsKids("VK_351", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
	   if(errVK_139==0) {
	    	test.log(LogStatus.PASS, "Validate the Maximum count of assets in the Masthead Carousel section under all tabs is PASS");
			if(!Utilities.setResultsKids("VK_139", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   else {
			test.log(LogStatus.FAIL, "Validate the Maximum count of assets in the Masthead Carousel section under all tabs is FAIL");
			if(!Utilities.setResultsKids("VK_139", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   test.log(LogStatus.INFO, "*********************** Verify meta data in My Stuff Carousel and compare with API ***********************");	 
	   String cardNameUpper="";
	   String cardName="";
	   String cardDescUpper="";
	   String cardDesc="";
	   if(myStuffAutoScroll==true) {
		   for(int cardNo=0;cardNo<api_totalItems_myStuff;cardNo++) {
			   cardName=myStuffNamesAPI.get(cardNo);
			   cardNameUpper=myStuffNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=myStuffDescAPI.get(cardNo);
			   cardDescUpper=myStuffDescAPI.get(cardNo).toUpperCase();
			   for(int count=0;count<=10;count++) {
				   try {
					   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located in My Stuff Carousal -> "+cardName+" :  "+cardDesc+" with media icon");
					   break;
				   }
				   catch(Exception e) {
					   if(count==10) {
						   test.log(LogStatus.FAIL, "Failed to locate in My Stuff Carousal -> "+cardName+" : "+cardDesc+" with media icon");
						   errVK_146++;
						   errVK_152++;
					   }
				   }
			   }
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in My Stuff Carousel***********************");
		   //first find the 5th card in the auto scrolling carousel
		   boolean foundFifthCard=false;
		   for(int count=0;count<=10;count++) {
			   cardName=myStuffNamesAPI.get((api_totalItems_myStuff-1));
			   cardNameUpper=myStuffNamesAPI.get((api_totalItems_myStuff-1)).toUpperCase();
			   cardDesc=myStuffDescAPI.get((api_totalItems_myStuff-1));
			   cardDescUpper=myStuffDescAPI.get((api_totalItems_myStuff-1)).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+api_totalItems_myStuff+" in My Stuff Carousal -> "+cardName);
				   foundFifthCard=true;
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
				   break;
			   }
			   catch(Exception e) {
				   if(count==10) {
					   test.log(LogStatus.FAIL, "Failed to locate card "+api_totalItems_myStuff+" in My Stuff Carousal -> "+cardName);
					   errVK_146++;
					   errVK_152++;
				   }
			   }
			   
		   }
		   if(foundFifthCard==true) {
			   for(int cardNo=(api_totalItems_myStuff-2);cardNo>=0;cardNo--) {
				   cardName=myStuffNamesAPI.get(cardNo);
				   cardNameUpper=myStuffNamesAPI.get(cardNo).toUpperCase();
				   cardDesc=myStuffDescAPI.get(cardNo);
				   cardDescUpper=myStuffDescAPI.get(cardNo).toUpperCase();
				   try {
					   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in My Stuff Carousal-> "+cardName);
				   }
				   catch(Exception e) {
					   test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in My Stuff Carousal-> "+cardName);
					   errVK_146++;
					   errVK_152++;
				   }   
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
			   }
		   }  
	   }
	   else {
		   for(int cardNo=0;cardNo<api_totalItems_myStuff;cardNo++) {
			   cardName=myStuffNamesAPI.get(cardNo);
			   cardNameUpper=myStuffNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=myStuffDescAPI.get(cardNo);
			   cardDescUpper=myStuffDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located in My Stuff Carousal -> "+cardName+" :  "+cardDesc+" with media icon");
				   break;
			   }
			   catch(Exception e) {
				   test.log(LogStatus.FAIL, "Failed to locate in My Stuff Carousal -> "+cardName+" : "+cardDesc+" with media icon");
				   errVK_146++;
				   errVK_152++;
			   }
			   if(cardNo<api_totalItems_audio-1)
				   //Utilities.horizontalSwipe(driver);
				   Utilities.horizontalSwipeCarousalSlow(driver);
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in My Stuff Carousel***********************");
		   for(int cardNo=(api_totalItems_myStuff-1);cardNo>=0;cardNo--) {
			   cardName=myStuffNamesAPI.get(cardNo);
			   cardNameUpper=myStuffNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=myStuffDescAPI.get(cardNo);
			   cardDescUpper=myStuffDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in My Stuff Carousal-> "+cardName);
			   }
			   catch(Exception e) {
				   test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in My Stuff Carousal-> "+cardName);
				   errVK_146++;
				   errVK_152++;
			   }   
			   Utilities.horizontalSwipeReverseFast(driver);
			   test.log(LogStatus.INFO, "Swiped right");
		   }
	   }
	   if(errVK_146==0) {
	        test.log(LogStatus.PASS, "Verify the order of displaying cards in Carousal section of My Stuff is PASS");
			if(!Utilities.setResultsKids("VK_146", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify the order of displaying cards in Carousal section of My Stuff is FAIL");
			if(!Utilities.setResultsKids("VK_146", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   if(errVK_152==0) {
	        test.log(LogStatus.PASS, "Verify user is able swipe horizontal when 5 cards available in carousel in My Stuff tab is PASS");
			if(!Utilities.setResultsKids("VK_152", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify user is able swipe horizontal when 5 cards available in carousel in My Stuff tab My Stuff is FAIL");
			if(!Utilities.setResultsKids("VK_152", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   test.log(LogStatus.INFO, "*********************** Verify meta data in Watch Carousel and compare with API ***********************");	 
	   homepagev2.tabClick("Watch");
	   if(watchAutoScroll==true) {
		   for(int cardNo=0;cardNo<api_totalItems_watch;cardNo++) {
			   cardName=episodeNamesAPI.get(cardNo);
			   cardNameUpper=episodeNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=episodeDescAPI.get(cardNo);
			   cardDescUpper=episodeDescAPI.get(cardNo).toUpperCase();
			   for(int count=0;count<=10;count++) {
				   try {
					   //driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]"));
					   driver.findElement(By.xpath("//*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located in Watch Carousal -> "+cardName+" : "+cardDesc+" with media icon");
					   break;
				   }
				   catch(Exception e) {
					   if(count==10) {
						   test.log(LogStatus.FAIL, "Failed to locate in Watch Carousal -> "+cardName+" : "+cardDesc+" with media icon");
						   errVK_243++;
						   errVK_247++;
						   errVK_149++;
					   }
				   }
			   }
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in Watch Carousel***********************");
		   //first find the 5th card in the auto scrolling carousel
		   boolean foundFifthCard=false;
		   for(int count=0;count<=10;count++) {
			   cardName=episodeNamesAPI.get((api_totalItems_watch-1));
			   cardNameUpper=episodeNamesAPI.get((api_totalItems_watch-1)).toUpperCase();
			   cardDesc=episodeDescAPI.get((api_totalItems_watch-1));
			   cardDescUpper=episodeDescAPI.get((api_totalItems_watch-1)).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+api_totalItems_watch+" in Watch Carousal -> "+cardName);
				   foundFifthCard=true;
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
				   break;
			   }
			   catch(Exception e) {
				   if(count==10) {
					   test.log(LogStatus.FAIL, "Failed to locate card "+api_totalItems_watch+" in Watch Carousal -> "+cardName);
					   errVK_243++;
					   errVK_247++;
				   }
			   }
			   
		   }
		   if(foundFifthCard==true) {
			   for(int cardNo=(api_totalItems_watch-2);cardNo>=0;cardNo--) {
				   cardName=episodeNamesAPI.get(cardNo);
				   cardNameUpper=episodeNamesAPI.get(cardNo).toUpperCase();
				   cardDesc=episodeDescAPI.get(cardNo);
				   cardDescUpper=episodeDescAPI.get(cardNo).toUpperCase();
				   try {
					   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in Watch Carousal-> "+cardName);
				   }
				   catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in Watch Carousal-> "+cardName);
						errVK_243++;
						errVK_247++;
				   }   
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
			   }
		   }  
	   }
	   else {
		   for(int cardNo=0;cardNo<api_totalItems_watch;cardNo++) {
			   cardName=episodeNamesAPI.get(cardNo);
			   cardNameUpper=episodeNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=episodeDescAPI.get(cardNo);
			   cardDescUpper=episodeDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located in Watch Carousal -> "+cardName+" : "+cardDesc+" with media icon");
			   }
			   catch(Exception e) {
			       test.log(LogStatus.FAIL, "Failed to locate in Watch Carousal -> "+cardName+" : "+cardDesc+" with media icon");
			       errVK_243++;
			       errVK_247++;
			       errVK_149++;
			   }
			   if(cardNo<api_totalItems_audio-1)
				   //Utilities.horizontalSwipe(driver);
				   Utilities.horizontalSwipeCarousalSlow(driver);
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in Watch Carousel***********************");
		   for(int cardNo=(api_totalItems_watch-1);cardNo>=0;cardNo--) {
			   cardName=episodeNamesAPI.get(cardNo);
			   cardNameUpper=episodeNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=episodeDescAPI.get(cardNo);
			   cardDescUpper=episodeDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//*[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::*[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in Watch Carousal-> "+cardName);
			   }
			   catch(Exception e) {
				   test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in Watch Carousal-> "+cardName);
				   errVK_243++;
				   errVK_247++;
			   }   
			   Utilities.horizontalSwipeReverseFast(driver);
			   test.log(LogStatus.INFO, "Swiped right");
		   }
	   }
	   if(errVK_149==0) {
	        test.log(LogStatus.PASS, "Verify episode card metadata for the card present in carousel is PASS");
			if(!Utilities.setResultsKids("VK_149", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify episode card metadata for the card present in carousel is FAIL");
			if(!Utilities.setResultsKids("VK_149", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   if(errVK_243==0) {
	        test.log(LogStatus.PASS, "Verify the order of displaying cards in Carousel section of Watch tab is PASS");
			if(!Utilities.setResultsKids("VK_243", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify the order of displaying cards in Carousel section of Watch tab is FAIL");
			if(!Utilities.setResultsKids("VK_243", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   if(errVK_247==0) {
	        test.log(LogStatus.PASS, "Verify user is able swipe horizontal  in watch tab when 5 cards available is PASS");
			if(!Utilities.setResultsKids("VK_247", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify user is able swipe horizontal  in watch tab when 5 cards available is FAIL");
			if(!Utilities.setResultsKids("VK_247", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   test.log(LogStatus.INFO, "*********************** Verify meta data in Read Carousel and compare with API ***********************");	 
	   homepagev2.tabClick("Read");
	   if(readAutoScroll==true) {
		   for(int cardNo=0;cardNo<api_totalItems_read;cardNo++) {
			   cardName=bookNamesAPI.get(cardNo);
			   cardNameUpper=bookNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=bookDescAPI.get(cardNo);
			   cardDescUpper=bookDescAPI.get(cardNo).toUpperCase();
			   for(int count=0;count<=10;count++) {
				   try {
					   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located in Read Carousal -> "+cardName+" : "+cardDesc+" with media icon");
					   break;
				   }
				   catch(Exception e) {
					   if(count==10) {
						   test.log(LogStatus.FAIL, "Failed to locate-> "+cardName+" : "+cardDesc+" with media icon");
						   errVK_326++;
						   errVK_330++;
						   errVK_150++;
					   }
				   }
			   }
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in Read Carousel***********************");
		   //first find the 5th card in the auto scrolling carousel
		   boolean foundFifthCard=false;
		   for(int count=0;count<=10;count++) {
			   cardName=bookNamesAPI.get((api_totalItems_read-1));
			   cardNameUpper=bookNamesAPI.get((api_totalItems_read-1)).toUpperCase();
			   cardDesc=bookDescAPI.get((api_totalItems_read-1));
			   cardDescUpper=bookDescAPI.get((api_totalItems_read-1)).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+api_totalItems_read+" in Read Carousal -> "+cardName);
				   foundFifthCard=true;
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
				   break;
			   }
			   catch(Exception e) {
				   if(count==10) {
					   test.log(LogStatus.FAIL, "Failed to locate card "+api_totalItems_read+" in Read Carousal -> "+cardName);
					   errVK_326++;
					   errVK_330++;
				   }
			   }
			   
		   }
		   if(foundFifthCard==true) {
			   for(int cardNo=(api_totalItems_watch-2);cardNo>=0;cardNo--) {
				   cardName=bookNamesAPI.get(cardNo);
				   cardNameUpper=bookNamesAPI.get(cardNo).toUpperCase();
				   cardDesc=bookDescAPI.get(cardNo);
				   cardDescUpper=bookDescAPI.get(cardNo).toUpperCase();
				   try {
					   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in Read Carousal-> "+cardName);
				   }
				   catch(Exception e) {
					   test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in Read Carousal-> "+cardName);
					   errVK_326++;
					   errVK_330++;
				   }   
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
			   }
		   } 
	   }
	   else {
		   for(int cardNo=0;cardNo<api_totalItems_read;cardNo++) {
			   cardName=bookNamesAPI.get(cardNo);
			   cardNameUpper=bookNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=bookDescAPI.get(cardNo);
			   cardDescUpper=bookDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located in Read Carousal -> "+cardName+" : "+cardDesc+" with media icon");
			   }
			   catch(Exception e) {
				   test.log(LogStatus.FAIL, "Failed to locate-> "+cardName+" : "+cardDesc+" with media icon");
				   errVK_326++;
				   errVK_330++;
				   errVK_150++;
			   }
			   if(cardNo<api_totalItems_audio-1)
				   //Utilities.horizontalSwipe(driver);
				   Utilities.horizontalSwipeCarousalSlow(driver);
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in Read Carousel***********************");
		   for(int cardNo=(api_totalItems_read-1);cardNo>=0;cardNo--) {
			   cardName=bookNamesAPI.get(cardNo);
			   cardNameUpper=bookNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=bookDescAPI.get(cardNo);
			   cardDescUpper=bookDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in Read Carousal-> "+cardName);
			   }
			   catch(Exception e) {
				   test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in Read Carousal-> "+cardName);
				   errVK_326++;
				   errVK_330++;
			   }   
			   Utilities.horizontalSwipeReverseFast(driver);
			   test.log(LogStatus.INFO, "Swiped right");
		   }
	   }
	   if(errVK_150==0) {
	        test.log(LogStatus.PASS, "Verify book card metadata for the card present in carousel is PASS");
			if(!Utilities.setResultsKids("VK_150", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   else {
			test.log(LogStatus.FAIL, "Verify book card metadata for the card present in carousel is FAIL");
			if(!Utilities.setResultsKids("VK_150", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   if(errVK_326==0) {
	        test.log(LogStatus.PASS, "Verify the order of displaying cards in Carousel section of Read tab is PASS");
			if(!Utilities.setResultsKids("VK_326", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	   else {
			test.log(LogStatus.FAIL, "Verify the order of displaying cards in Carousel section of Read tab is FAIL");
			if(!Utilities.setResultsKids("VK_326", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }  
	   if(errVK_330==0) {
	        test.log(LogStatus.PASS, "Verify user is able swipe horizontal  in Read tab when 5 cards available is PASS");
			if(!Utilities.setResultsKids("VK_330", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify user is able swipe horizontal  in Read tab when 5 cards available is FAIL");
			if(!Utilities.setResultsKids("VK_330", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   } 
	   test.log(LogStatus.INFO, "*********************** Verify meta data in Audio Carousel and compare with API ***********************");	 
	   homepagev2.tabClick("Listen");
	   if(audioAutoScroll==true) {
		   for(int cardNo=0;cardNo<api_totalItems_audio;cardNo++) {
			   cardName=audioNamesAPI.get(cardNo);
			   cardNameUpper=audioNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=audioDescAPI.get(cardNo);
			   cardDescUpper=audioDescAPI.get(cardNo).toUpperCase();
			   for(int count=0;count<=10;count++) {
				   try {
					   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located in Audio Carousal -> "+cardName+" : "+cardDesc+" with media icon");
					   break;
				   }
				   catch(Exception e) {
					   if(count==10) {
						   test.log(LogStatus.FAIL, "Failed to locate in Audio Carousal -> "+cardName+" : "+cardDesc+" with media icon");
						   errVK_349++;
						   errVK_354++;
						   errVK_151++;
					   }
				   }
			   }
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in Audio Carousel***********************");
		   //first find the 5th card in the auto scrolling carousel
		   boolean foundFifthCard=false;
		   for(int count=0;count<=10;count++) {
			   cardName=audioNamesAPI.get((api_totalItems_audio-1));
			   cardNameUpper=audioNamesAPI.get((api_totalItems_audio-1)).toUpperCase();
			   cardDesc=audioDescAPI.get((api_totalItems_audio-1));
			   cardDescUpper=audioDescAPI.get((api_totalItems_audio-1)).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+api_totalItems_audio+" in Audio Carousal -> "+cardName);
				   foundFifthCard=true;
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
				   break;
			   }
			   catch(Exception e) {
				   if(count==10) {
					   test.log(LogStatus.FAIL, "Failed to locate card "+api_totalItems_audio+" in Audio Carousal -> "+cardName);
					   errVK_349++;
					   errVK_354++;
				   }
			   }
			   
		   }
		   if(foundFifthCard==true) {
			   for(int cardNo=(api_totalItems_watch-2);cardNo>=0;cardNo--) {
				   cardName=audioNamesAPI.get(cardNo);
				   cardNameUpper=audioNamesAPI.get(cardNo).toUpperCase();
				   cardDesc=audioDescAPI.get(cardNo);
				   cardDescUpper=audioDescAPI.get(cardNo).toUpperCase();
				   try {
					   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
					   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in Audio Carousal-> "+cardName);
				   }
				   catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in Audio Carousal-> "+cardName);
						errVK_349++;
						errVK_354++;
				   }   
				   Utilities.horizontalSwipeReverseFast(driver);
				   test.log(LogStatus.INFO, "Swiped right");
			   }
		   }   
	   }	
	   else {
		   for(int cardNo=0;cardNo<api_totalItems_audio;cardNo++) {
			   cardName=audioNamesAPI.get(cardNo);
			   cardNameUpper=audioNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=audioDescAPI.get(cardNo);
			   cardDescUpper=audioDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located in Audio Carousal -> "+cardName+" : "+cardDesc+" with media icon");
			   }
			   catch(Exception e) {
				   test.log(LogStatus.FAIL, "Failed to locate in Audio Carousal -> "+cardName+" : "+cardDesc+" with media icon");
				   errVK_349++;
				   errVK_354++;
				   errVK_151++;
			   }
			   if(cardNo<api_totalItems_audio-1)
				   //Utilities.horizontalSwipe(driver);
			       Utilities.horizontalSwipeCarousalSlow(driver);
		   }
		   test.log(LogStatus.INFO, "*********************** Verify Left Swipe of cards in Audio Carousel***********************");
		   for(int cardNo=(api_totalItems_read-1);cardNo>=0;cardNo--) {
			   cardName=audioNamesAPI.get(cardNo);
			   cardNameUpper=audioNamesAPI.get(cardNo).toUpperCase();
			   cardDesc=audioDescAPI.get(cardNo);
			   cardDescUpper=audioDescAPI.get(cardNo).toUpperCase();
			   try {
				   driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/book_icon_img']//following-sibling::android.widget.TextView[@text=\""+cardName+"\" or @text=\""+cardNameUpper+"\"]//following-sibling::android.widget.TextView[@text=\""+cardDesc+"\" or @text=\""+cardDescUpper+"\"]"));
				   test.log(LogStatus.INFO, "Located card "+(cardNo+1)+" in Audio Carousal-> "+cardName);
			   }
			   catch(Exception e) {
				    test.log(LogStatus.FAIL, "Failed to locate card "+(cardNo+1)+" in Audio Carousal-> "+cardName);
				    errVK_349++;
				    errVK_354++;
			   }   
			   Utilities.horizontalSwipeReverseFast(driver);
			   test.log(LogStatus.INFO, "Swiped right");
		   }
	   }
	   if(errVK_151==0) {
	        test.log(LogStatus.PASS, "Verify audio book card metadata for the card present in carousel is PASS");
			if(!Utilities.setResultsKids("VK_151", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify audio book card metadata for the card present in carousel is FAIL");
			if(!Utilities.setResultsKids("VK_151", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   } 
	   if(errVK_349==0) {
	        test.log(LogStatus.PASS, "Verify the order of displaying cards in Carousel section of Listen tab is PASS");
			if(!Utilities.setResultsKids("VK_349", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify the order of displaying cards in Carousel section of Listen tab is FAIL");
			if(!Utilities.setResultsKids("VK_349", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   } 
	   if(errVK_354==0) {
	        test.log(LogStatus.PASS, "Verify user is able swipe horizontal  in Listen tab when 5 cards available is PASS");
			if(!Utilities.setResultsKids("VK_354", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
		else {
			test.log(LogStatus.FAIL, "Verify user is able swipe horizontal  in Listen tab when 5 cards available FAIL");
			if(!Utilities.setResultsKids("VK_354", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   }
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
