package com.viacom.favourites;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//Author Tanisha
public class VerifyMultipleFavouritesAndMetaData extends BaseTestV2{
	
	String testName = "VerifyMultipleFavouritesAndMetaData";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedUI(Hashtable<String, String> data) throws Exception 
	{	int errCount=0;
		int errCount199=0;
		int errCount202=0;
		int errCount203=0;
		int errCount207=0;
		int a=0;
		String nameEpisode="";
		String descEpisode="";
		String firstBookName="";
		String firstBookDesc="";
		String bookname="";
		String bookdesc="";
		String firstAudioName="";
		String firstAudioAuthor="";
		String audioname="";
		String audioauthor="";
		test = rep.startTest("Verify Favourites");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls199 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno199=xls199.getRowCount("Smoke_Results")+1;
		xls199.setCellData("Smoke_Results", "Testcase Name",rowno199,"Validate Favorites tray UI");
		
		Xls_Reader xls201 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno201=xls201.getRowCount("Smoke_Results")+1;
		xls201.setCellData("Smoke_Results", "Testcase Name",rowno201,"Validate the click functionality of the 'Edit' text displayed next to the Favourites tray name");
		
		Xls_Reader xls202 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno202=xls202.getRowCount("Smoke_Results")+1;
		xls202.setCellData("Smoke_Results", "Testcase Name",rowno202,"Verify the cards/tiles to be present under Favorites section");
		
		Xls_Reader xls203 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno203=xls203.getRowCount("Smoke_Results")+1;
		xls203.setCellData("Smoke_Results", "Testcase Name",rowno203,"Verify the sequencing for the cards present under Favorites section");
		
		Xls_Reader xls207 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno207=xls207.getRowCount("Smoke_Results")+1;
		xls207.setCellData("Smoke_Results", "Testcase Name",rowno207,"Verify the navigation by Tapping on any favorited card from Favorites section");
				
		//Launch Voot kids app
		 launchApp();
		 Thread.sleep(2000);
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 HomePageV2.signup();
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 a=Utilities.tabIndex(driver, "Watch");
		 try{
			List<WebElement>  tabsfromFE=driver.findElementsByXPath("//*[contains(@class,'ActionBar')]");
			if(Utilities.explicitWaitClickable(driver, tabsfromFE.get(a), 5))
				tabsfromFE.get(a).click();
			Thread.sleep(1000);
		   }
		 catch (Exception e) {
			ListenPageV2.reportFail("Failed to click on Watch Tab");
			e.printStackTrace();
		 }
		 test.log(LogStatus.INFO, "Clicked on Watch tab");
		 //Click on the first carousal item
		 Utilities.verticalSwipe(driver);
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
		 try{
			nameEpisode=watchpagev2.firstItemInCarousalName.getAttribute("text");
			descEpisode=watchpagev2.firstItemInCarousalDesc.getAttribute("text");
			watchpagev2.firstItemInCarousal.click();
			}
		 catch(Exception e){
			test.log(LogStatus.FAIL, "Unable to click on first item in Carousal");
			BasePageV2.takeScreenshot();
		 }	
		test.log(LogStatus.INFO, "Clicked on the first item in carousal under Watch with Episode name: "+nameEpisode+" and description "+descEpisode);  
		if(Utilities.explicitWaitVisible(driver, watchpagev2.watchFirstItemPlayer, 3)) {
			test.log(LogStatus.INFO, "Player is displayed on tapping first item of Carousal in Watch tab");
			//Tap on player until controls are visible
			//homepagev2.verifyAndProgressBar();
			for(int time=0;time<=10;time++) {
				try {
					 watchpagev2.watchFirstItemPlayer.click();
					 watchpagev2.watchFirstItemPlayerFav.click();
					 test.log(LogStatus.INFO, "Favourites icon is present in player"); 
					 test.log(LogStatus.INFO, "Clicked on Favourite icon");
					 test.log(LogStatus.INFO, "Marked Episode as Favourite");
					break;
				}
				catch(Exception e) {
					 Thread.sleep(1000);
					 if(time==10) {
						 test.log(LogStatus.FAIL, "Player is not clickable"); 
						 BasePageV2.takeScreenshot();
						 errCount++;
					}			 
				}					 
			}
			driver.navigate().back();
			Utilities.verticalSwipeReverse(driver);
			//Tap on Read tab
			//tap on read index dynamically
			a=Utilities.tabIndex(driver, "Read");
			try{
				List<WebElement>  tabsfromFE=driver.findElementsByXPath("//*[contains(@class,'ActionBar')]");
				if(Utilities.explicitWaitClickable(driver, tabsfromFE.get(a), 5))
					tabsfromFE.get(a).click();
				Thread.sleep(1000);
			}
			 catch (Exception e) {
				ListenPageV2.reportFail("Failed to click on Read Tab");
				e.printStackTrace();
			}
			 test.log(LogStatus.INFO, "Clicked on Read tab");
			 Utilities.verticalSwipe(driver);
			 //put in try catch block , definevariables top
			 firstBookName="//android.widget.TextView[@text='NEW BOOKS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='2']";
			 firstBookDesc="//android.widget.TextView[@text='NEW BOOKS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='4']";
			 bookname=driver.findElementByXPath(firstBookName).getAttribute("text");
			 bookdesc=driver.findElementByXPath(firstBookDesc).getAttribute("text");
			 if(Utilities.explicitWaitClickable(driver, readpagev2.newBook1.get(0), 10)) {
				 readpagev2.newBook1.get(0).click(); 
				 test.log(LogStatus.INFO, "Clicked on the first Book from the NEW BOOKS tray: "+bookname+" with description: "+bookdesc);  
			//	 BasePageV2.takeScreenshot();	
				 //Verify the presence of Favorite icon
				 if(Utilities.explicitWaitVisible(driver, readpagev2.favIconAudioDetails, 3)) {
					 test.log(LogStatus.INFO,"Favourite icon is visible in the book detail page"); 
					 if(Utilities.explicitWaitClickable(driver, readpagev2.favIconAudioDetails, 3)) {
						 readpagev2.favIconAudioDetails.click();
						 test.log(LogStatus.INFO, "Clicked on Favourite icon");
						 test.log(LogStatus.INFO,"Marked book as Favourite"); 
						// BasePageV2.takeScreenshot();
						 driver.navigate().back();
						 Utilities.verticalSwipeReverse(driver);
						 //Launch Audio tab
						 a=Utilities.tabIndex(driver, "Listen");
						 try{
							List<WebElement>  tabsfromFE=driver.findElementsByXPath("//*[contains(@class,'ActionBar')]");
							if(Utilities.explicitWaitClickable(driver, tabsfromFE.get(a), 5))
								tabsfromFE.get(a).click();
							Thread.sleep(1000);
						 }
						 catch (Exception e) {
							ListenPageV2.reportFail("Failed to click on Listen Tab");
							e.printStackTrace();
						 }
						 Utilities.verticalSwipe(driver);
						 test.log(LogStatus.INFO, "Clicked on Listen tab");
					//	 BasePageV2.takeScreenshot();
						 firstAudioName="//android.widget.TextView[@text='NEW AUDIOSTORIES']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout[@index='1']//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='2']";
						 firstAudioAuthor="//android.widget.TextView[@text='NEW AUDIOSTORIES']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout[@index='1']//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='4']";
						 audioname=driver.findElementByXPath(firstAudioName).getAttribute("text");
						 audioauthor=driver.findElementByXPath(firstAudioAuthor).getAttribute("text");
						 if(Utilities.explicitWaitClickable(driver, readpagev2.newAudioText1.get(0), 5)) {
							 readpagev2.newAudioText1.get(0).click(); 
							 test.log(LogStatus.INFO, "Clicked on the first Audio Story from the NEW AUDIOSTORIES tray: "+audioname+" by "+audioauthor);  	
							 //Verify the presence of Favorite icon
							 if(Utilities.explicitWaitVisible(driver, readpagev2.favIconAudioDetails, 3)) {
								 test.log(LogStatus.INFO,"Favourite icon is visible in the audio book detail page"); 
								 if(Utilities.explicitWaitClickable(driver, readpagev2.favIconAudioDetails, 3)) {
									 readpagev2.favIconAudioDetails.click();
									 test.log(LogStatus.INFO,"Marked as Favourite"); 
								//	 BasePageV2.takeScreenshot();
									 //Scroll to Favourites
									 driver.navigate().back();
									 Utilities.verticalSwipeReverse(driver);
									 driver.closeApp();
									 driver.startActivity(new Activity("com.viacom18.vootkids","com.tv.vootkids.ui.home.VKHomeActivity"));
									 homepagev2.mystuff_tab.click();
									 Thread.sleep(2000);
									 //handle in try
									 String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
									 boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
									 if(presenceTray==true) {
										//Verify Favorites tray UI
										test.log(LogStatus.INFO, "Favourites tray is displayed");
										Utilities.verticalSwipe(driver);
										if(homepagev2.editFavorite.isDisplayed()) {
											test.log(LogStatus.INFO, "Edit link is displayed");
											//Click on Edit link
											try {
												Thread.sleep(2000);
												homepagev2.editFavorite.click();
												try{
													driver.findElement(By.xpath("//android.widget.TextView[@text='Cancel']"));
													test.log(LogStatus.PASS, "On tapping the Edit text, should lead to Edit Favourites page is PASS");
													HomePageV2.smokeresults("201: On tapping the Edit text, should lead to Edit Favourites page",rowno201, "PASS");	
												}
												catch(Exception e1) {
													test.log(LogStatus.FAIL, "Incorrect page is displayed on clicking Edit link");
													test.log(LogStatus.FAIL, "On tapping the Edit text, should lead to Edit Favourites page is FAIL");
													HomePageV2.smokeresults("201: On tapping the Edit text, should lead to Edit Favourites page",rowno201, "FAIL");
												}						
											}
											catch(Exception e){
												test.log(LogStatus.FAIL, "Unable to click on the Edit link");
												test.log(LogStatus.FAIL, "On tapping the Edit text, should lead to Edit Favourites page is FAIL");
												HomePageV2.smokeresults("201: On tapping the Edit text, should lead to Edit Favourites page",rowno201, "FAIL");
											}
											driver.navigate().back();
											
										}
										else {
											test.log(LogStatus.FAIL, "Edit link is not displayed");
											BasePageV2.takeScreenshot();
											errCount199++;
										}
										Utilities.verticalSwipe(driver);
										Utilities.verticalSwipe(driver);
									//	BasePageV2.takeScreenshot();
										
										
										//Verify meta data and for the Audio type and order of recency
										if(homepagev2.firstFavorite.get(0).getAttribute("text").equalsIgnoreCase(audioname)) {
												test.log(LogStatus.INFO, "Favorited Audio Name is verified");	
												test.log(LogStatus.INFO, "Order of recency is verified for Favourites for first item");	
										}
										else {
											test.log(LogStatus.FAIL, "Favorited Audio is not displayed as first item");	
											errCount203++;
											errCount199++;
										}
										Thread.sleep(1000);
										//Verify media icon type for Audio type
										if(homepagev2.firstFavoriteMediaIcon.isDisplayed())
											test.log(LogStatus.INFO, "Media icon is displayed for Favorited Audio Name");
										else
											errCount202++;		
								
										//Verify meta data and for the Read type and order of recency
										if(homepagev2.secondFavorite.get(0).getAttribute("text").equalsIgnoreCase(bookname)) {
												test.log(LogStatus.INFO, "Favorited Book Name is verified");	
												test.log(LogStatus.INFO, "Order of recency is verified for Favourites for second item");	
										}
										else {
											test.log(LogStatus.FAIL, "Favorited Book is not displayed as second item");	
											errCount199++;
											errCount203++;
										}
										Utilities.verticalSwipe(driver);
										Thread.sleep(1000);
										//Verify media icon type for Audio type
										if(homepagev2.secondFavoriteMediaIcon.isDisplayed())
											test.log(LogStatus.INFO, "Media icon is displayed for Favorited Book Name");
										else
											errCount202++;	
										
										//Verify meta data and for the Episode type and order of recency
										if(homepagev2.thirdFavorite.get(1).getAttribute("text").equalsIgnoreCase(nameEpisode)) {
												test.log(LogStatus.INFO, "Favorited Episode Name is verified");	
												test.log(LogStatus.INFO, "Order of recency is verified for Favourites for third item");			
										}
										else {
											test.log(LogStatus.FAIL, "Favorited Episode is not displayed as third item");	
											errCount199++;
											errCount203++;
										}
										Utilities.verticalSwipe(driver);
										Thread.sleep(1000);

										//Verify media icon type for Audio type
										if(homepagev2.thirdFavoriteMediaIcon.isDisplayed())
											test.log(LogStatus.INFO, "Media icon is displayed for Favorited Episode Name");
										else
											errCount202++;	
										
										//Final verification of test case 202
										if(errCount202<=0) {
											test.log(LogStatus.PASS, "Verify the cards/tiles to be present under Favorites section is PASS");
										//	BasePageV2.takeScreenshot();
											HomePageV2.smokeresults("202: Verify the cards/tiles to be present under Favorites section",rowno202, "PASS");	
										}
										else {
											test.log(LogStatus.FAIL, "Verify the cards/tiles to be present under Favorites section is FAIL");
											HomePageV2.smokeresults("202: Verify the cards/tiles to be present under Favorites section",rowno202, "FAIL");	
										}
										
										
										//Final verification of test case 203
										if(errCount203<=0) {
											test.log(LogStatus.PASS, "Verify the sequencing for the cards present under Favorites section is PASS");
											//BasePageV2.takeScreenshot();
											HomePageV2.smokeresults("202: Verify the sequencing for the cards present under Favorites section",rowno203, "PASS");	
										}
										else {
											test.log(LogStatus.FAIL, "Verify the sequencing for the cards present under Favorites section is FAIL");
											BasePageV2.takeScreenshot();
											HomePageV2.smokeresults("202: Verify the sequencing for the cards present under Favorites section",rowno203, "FAIL");	
										}
										
										//Verify See All should not be present
										
										//Clicking on each item and back to verify player
										try {
											homepagev2.firstFavorite.get(0).click();
											test.log(LogStatus.INFO, "Clicked on Audio book");
											String audio=homepagev2.firstFavorite.get(0).getAttribute("text");
											try {
												driver.findElement(By.xpath("//android.widget.TextView[@text=\""+audio+"\"]"));
												test.log(LogStatus.INFO, "Audio details page navigation from favourites is successfull");
											//	BasePageV2.takeScreenshot();
											}
											catch(Exception e1) {
												test.log(LogStatus.FAIL, "Audio details not displayed on clicking Favourited Audio");
												errCount207++;
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Unable to click on Audio book");
											errCount207++;
										}
										driver.navigate().back();
										try {
											homepagev2.secondFavorite.get(0).click();
											test.log(LogStatus.INFO, "Clicked on Read book");
											String book=homepagev2.secondFavorite.get(0).getAttribute("text");
											try {
												driver.findElement(By.xpath("//android.widget.TextView[@text=\""+book+"\"]"));
												test.log(LogStatus.INFO, "Book details page navigation from favourites is successfull");
												//BasePageV2.takeScreenshot();
											}
											catch(Exception e2) {
												test.log(LogStatus.FAIL, "Book details not displayed on clicking Favourited Book");
												errCount207++;
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Unable to click on Book");
											errCount207++;
										}
										driver.navigate().back();
										try {
											homepagev2.thirdFavorite.get(0).click();
											test.log(LogStatus.INFO, "Clicked on Video");
											 if(Utilities.explicitWaitVisible(driver, watchpagev2.watchFirstItemPlayer, 3)) {
												 test.log(LogStatus.INFO, "Player is displayed on tapping first item of Carousal in Watch tab");
												// BasePageV2.takeScreenshot();
											 }
											 else {
												 test.log(LogStatus.FAIL, "Player is not getting displayed");
												 errCount207++;
											 }

										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Unable to click on Video");
											errCount207++;
										}
										//Final verification of test case 207
										if(errCount207==0) {
											test.log(LogStatus.PASS, "Verify the sequencing for the cards present under Favorites section is PASS");
											//BasePageV2.takeScreenshot();
											HomePageV2.smokeresults("207: Verify the sequencing for the cards present under Favorites section",rowno207, "PASS");	
										}
										else {
											test.log(LogStatus.FAIL, "Verify the sequencing for the cards present under Favorites section is FAIL");
											BasePageV2.takeScreenshot();
											HomePageV2.smokeresults("207: Verify the sequencing for the cards present under Favorites section",rowno207, "FAIL");	
										}
										
										//Add more audio items
										ArrayList<String> ele8=new ArrayList<String>();
										//Utilities.verticalSwipeReverse(driver);
										homepagev2.Listen_tab.click();
										for(int count1=0;count1<5;count1++) {
											if(ele8.size()<12) {
												List<WebElement> ele8temp=listenpagev2.favMoreThan8;
												for(int count=0;count<ele8temp.size();count++) {
													if(!ele8.contains(listenpagev2.favMoreThan8.get(count).getAttribute("text"))){
														ele8.add(listenpagev2.favMoreThan8.get(count).getAttribute("text"));
														try {
															listenpagev2.favMoreThan8.get(count).click();
															if(listenpagev2.favAudioDetails.getAttribute("checked").equals("true"))
																driver.navigate().back();
															else {
																try {
																	listenpagev2.favAudioDetails.click();
																	test.log(LogStatus.INFO, "Added item to Favourites");
																	driver.navigate().back();
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Unable to add item to Favourites");
																	driver.navigate().back();
																}
															}
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Unable to click on the audio");
														}
													}
												}
												Utilities.verticalSwipe(driver);
												Thread.sleep(1000);
											}	
										}
										Utilities.verticalSwipeReverse(driver);
										homepagev2.read_tab.click();
										for(int count1=0;count1<5;count1++) {
											if(ele8.size()<12) {
												List<WebElement> ele8temp=listenpagev2.favMoreThan8;
												for(int count=0;count<ele8temp.size();count++) {
													if(!ele8.contains(listenpagev2.favMoreThan8.get(count).getAttribute("text"))){
														ele8.add(listenpagev2.favMoreThan8.get(count).getAttribute("text"));
														try {
															listenpagev2.favMoreThan8.get(count).click();
															if(listenpagev2.favAudioDetails.getAttribute("checked").equals("true"))
																driver.navigate().back();
															else {
																try {
																	listenpagev2.favAudioDetails.click();
																	test.log(LogStatus.INFO, "Added item to Favourites");
																	driver.navigate().back();
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Unable to add item to Favourites");
																	driver.navigate().back();
																}
															}
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Unable to click on the read tab");
														}
													}
												}
												Utilities.verticalSwipe(driver);
												Thread.sleep(1000);
											}	
										}
										Utilities.verticalSwipeReverse(driver);
										homepagev2.mystuff_tab.click();
										Thread.sleep(2000);
										presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
										if(presenceTray==true) {
											test.log(LogStatus.INFO, "Favourites tray is displayed");
											Utilities.verticalSwipe(driver);
											//Click on Favourites tray
											try {
												driver.findElement(By.xpath(favouritesXpath)).click();
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Unable to click on Favourites tray");
												BasePageV2.takeScreenshot();
											}
											//Verify if Favourites page has launched
											try {
												driver.findElement(By.xpath(favouritesXpath));
												//BasePageV2.takeScreenshot();
												driver.navigate().back();
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Incorrect page is displayed for Favourites");
												BasePageV2.takeScreenshot();
											}									
											//Click on See All
											String seeAll="//android.widget.Button[@text='SEE ALL']";
											for(int swipe=1;swipe<=10;swipe++) {
												Utilities.verticalSwipe(driver);
												if(driver.findElement(By.xpath(seeAll)).isDisplayed()){
													try {
														driver.findElement(By.xpath(seeAll)).click();
														test.log(LogStatus.INFO, "Clicked on See All");
														BasePageV2.takeScreenshot();
														try {
															driver.findElement(By.xpath(favouritesXpath));
															driver.navigate().back();
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Incorrect page is displayed on clicking See All for Favourites");
															BasePageV2.takeScreenshot();
														}
														break;
													}
													catch(Exception e) {
														test.log(LogStatus.FAIL, "Unable to click on See All button");
														break;													
													}
													
												}
												else if(swipe==10) {
													test.log(LogStatus.FAIL, "Unable to swipe to See All button");
													break;
												}
											}		
											
										}
										 else {
											 test.log(LogStatus.FAIL, "Favourites tray is not displayed");
											 errCount199++;
										 }
									 }
									 else {
										 test.log(LogStatus.FAIL, "Favourites icon is not clickable in the audio book detail page");
										 errCount++;
									 }
								}
								else {
									 test.log(LogStatus.FAIL, "Favourites icon is not displayed in the audio book detail page");
									 errCount++;
								}
							}
							else {
								test.log(LogStatus.FAIL, "Unable to click on the audio under NEW AUDIOSTORIES");
								errCount++;
							}						 
					 }
					 else {
						 test.log(LogStatus.FAIL, "Unable to click on Favorite icon of the book");
						 errCount++;
					 }
				 }
				 else {
					 test.log(LogStatus.FAIL, "Favorite icon is not displayed on the book");
					 errCount++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Unable to click on first book below carousal");
				 errCount++;
			 }	 
	}
	else {
		test.log(LogStatus.FAIL, "Unable to click on first item in carousal of Watch tab");
		errCount++;
		}	
	
	//Final verification of test cases
	if(errCount199>0) {
		test.log(LogStatus.FAIL, "Validate Favorites tray UI is FAIL");
		HomePageV2.smokeresults("199: Validate Favorites tray UI",rowno199, "FAIL");
	}
	else {
		test.log(LogStatus.PASS, "Validate Favorites tray UI is PASS");
		HomePageV2.smokeresults("199: Validate Favorites tray UI",rowno199, "PASS");
	}
	}}
}	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
