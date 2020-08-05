package com.viacom.favourites;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyFavouriteAddedAudioAndEpisode extends BaseTestV2{
	
	String testName = "VerifyFavouriteAddedAudioAndEpisode";
	@Test(dataProvider = "getData")
	public void VerifyFavouriteAddedAudioAndEpisode(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyFavouriteAddedAudioAndEpisode");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		//VK_195 Verify the click funtionality on tapping on favourite icon in audio book detail page
		//VK_196 Verify the click funtionality on tapping on favourite icon from player
		//VK_204 Verify the metadata displayed for the favorited episode card
		//VK_205 Verify the metadata displayed for the favorited Audio book card
		//VK_1748 Verify the availablity of Up arrow option if there is only 1 Audiobook in Favourites screen
		//VK_1746 Verify the availablity of Up arrow option if there is only 1 favourited episode content
			
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerpagev2=new KidsPlayerPageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 //Login module to be added
		 //homepagev2.signup();
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 //System.out.println(driver.getPageSource());
		 }
		
		//Verification of 199
		//Click on Listen tab
		 homepagev2.tabClick("Listen");
		 test.log(LogStatus.INFO, "Clicked on Listen tab");
		 Thread.sleep(2000);
		 Utilities.verticalSwipe(driver);
		 String audioTitle="";
		 String audioAuthor="";
		 boolean favAudioFound=false;
		 boolean clickedFav=false;
		 String uiFavBookLocator="";
		 String uiFavAuthorLocator="";
		 String uiFavIconLocator="";
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.newAudioTextOne, 5)) {
			 listenpagev2.newAudioTextOne.click(); 
			 test.log(LogStatus.INFO, "Clicked on the first Audio from tray");  	
			 //Wait for audio details page to load
			 listenpagev2.waitForAudioDetailsToLoad();
			 Utilities.verticalSwipe(driver);
			 if(Utilities.explicitWaitVisibleNew(driver, listenpagev2.nameFromDetailsPage, 10)) {
				 audioTitle=listenpagev2.nameFromDetailsPage.getAttribute("text").trim();
				 audioAuthor=listenpagev2.authorFromDetailsPage.getAttribute("text").trim();
				 test.log(LogStatus.INFO, "Audio clicked title: "+audioTitle+", author: "+audioAuthor);
			 }
			 else{
				 test.log(LogStatus.INFO, "Audio details not fetched");
			 }
			 Utilities.verticalSwipeReverse(driver);
			 Utilities.verticalSwipeReverse(driver);
			 //Verify the presence of Favorite icon
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.favIconAudioDetails, 3)) {
				 test.log(LogStatus.INFO,"Favourite icon is visible in the audio book detail page"); 
				 if(Utilities.explicitWaitClickable(driver, listenpagev2.favIconAudioDetails, 3)) {
					 listenpagev2.favIconAudioDetails.click();
					 test.log(LogStatus.INFO,"Marked as Favourite"); 
					 if(Utilities.explicitWaitClickableNew(driver, homepagev2.favDialogOKButton, 5)) {
						 homepagev2.favDialogOKButton.click();
						 test.log(LogStatus.INFO, "Dismissed 'Added To Favorites' pop up");
					 }
					 clickedFav=true;
				 }
				 else {
						test.log(LogStatus.FAIL,"Favourite icon is not clickable in the audio book detail page"); 
						basepagev2.takeScreenshot();
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL,"Favourite icon is not visible in the audio book detail page");
				 basepagev2.takeScreenshot();
			 }
			 if(clickedFav==true) {
				//Scroll to Favourites
				 driver.navigate().back();
				 Utilities.verticalSwipeReverse(driver);
				 homepagev2.mystuff_tab.click();
				 Thread.sleep(2000);
				 String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
				 boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
				 if(presenceTray==true) {
					test.log(LogStatus.INFO, "Favourites tray is displayed");	
					uiFavBookLocator="//android.widget.TextView[@text='"+audioTitle+"']";
					uiFavAuthorLocator="//android.widget.TextView[@text='"+audioTitle+"']/parent::android.view.ViewGroup//android.widget.TextView[contains(@text,'"+audioAuthor+"')]";
					uiFavIconLocator="//android.widget.TextView[@text='"+audioTitle+"']/parent::android.view.ViewGroup//android.widget.ImageView[@index='5']";
					for(int scroll=0;scroll<=3;scroll++) {
						try {
							driver.findElement(By.xpath(uiFavBookLocator));
							test.log(LogStatus.PASS, "Verify the click funtionality on tapping on favourite icon in audio book detail page is PASS");
							if(!Utilities.setResultsKids("VK_195", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							favAudioFound=true;
							break;
						}
						catch(Exception e) {
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipe(driver);
							if(scroll==3) {
								test.log(LogStatus.FAIL, "Audio is not present in Favourites Tray");
								test.log(LogStatus.FAIL, "Verify the click funtionality on tapping on favourite icon in audio book detail page is FAIL");
								if(!Utilities.setResultsKids("VK_195", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}
						}
					}
				 }
				 else {
					test.log(LogStatus.FAIL, "Favourites tray is not displayed even after marking audio book as Favourite");
					basepagev2.takeScreenshot();
				 }
			 } 
			 if(favAudioFound==true) {
				 try {
					String uiBookName=driver.findElement(By.xpath(uiFavBookLocator)).getAttribute("text");
					test.log(LogStatus.INFO, "Audio Book Title is verified "+uiBookName);
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Audio Book Title not displayed");
					errCount++;
				 }
				 try {
					String uiAuthorName=driver.findElement(By.xpath(uiFavAuthorLocator)).getAttribute("text");
					test.log(LogStatus.INFO, "Audio Book Author is verified "+uiAuthorName);
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Audio Book Author not displayed");
					errCount++;
				 }
				 try {
					driver.findElement(By.xpath(uiFavIconLocator));
					test.log(LogStatus.INFO, "Audio Book Icon is verified");
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Audio Book Icon not displayed");
					errCount++;
				 }
				 if(errCount==0) {
					test.log(LogStatus.PASS, "Verify the metadata displayed for the favorited Audio book card is PASS");
					if(!Utilities.setResultsKids("VK_205", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 if(errCount>0) {
					test.log(LogStatus.FAIL, "Verify the metadata displayed for the favorited Audio book card is FAIL");
					if(!Utilities.setResultsKids("VK_205", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
			 }
			 if(favAudioFound==true) {
				//Click on the audio to verify Up arrow
				try {
					driver.findElement(By.xpath(uiFavBookLocator)).click();
					test.log(LogStatus.INFO, "Clicked on the favourited Audio");
					//Tap on Play
					if(Utilities.explicitWaitVisible(driver, listenpagev2.play_btn, 10)) {
						listenpagev2.play_btn.click();
						test.log(LogStatus.INFO, "Clicked on Play button");
						if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerUpArrowExpand, 10)) {
							test.log(LogStatus.INFO, "Up Arrow is displayed,even though there is only one favourited card");
							test.log(LogStatus.FAIL, "Verify the availablity of Up arrow option if there is only 1 Audiobook in Favourites screen is FAIL");
							if(!Utilities.setResultsKids("VK_1748", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else {
							test.log(LogStatus.INFO, "Up Arrow is not displayed because there is only one favourited card");
							test.log(LogStatus.PASS, "Verify the availablity of Up arrow option if there is only 1 Audiobook in Favourites screen is PASS");
							if(!Utilities.setResultsKids("VK_1748", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");											
						}
						//mark unfavourite
						if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerFavouriteButton, 10)) {
							listenpagev2.audioPlayerFavouriteButton.click();
							test.log(LogStatus.INFO, "Marked as unfavourite");
						}
						else {
							test.log(LogStatus.INFO, "Failed to mark as unfavourite");
						}
						Thread.sleep(2000);
						//Close the player
						try {
							listenpagev2.playerCloseButton.click();
							test.log(LogStatus.INFO, "Closed the audio player");
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to close the audio player");
						}
						driver.navigate().back();
						test.log(LogStatus.INFO, "Navigated back to Home page");
					}
					else {
						test.log(LogStatus.FAIL, "Failed to click on Play button");
					}	
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to click on the favourited Audio");
				}
			 }	 	  
		}
		else {
			test.log(LogStatus.FAIL,"Unable to click on the first Audio Story from tray"); 
			basepagev2.takeScreenshot();
		}		  
		//Verification of 200
		 errCount=0;
		//Scroll upwards 
		Utilities.verticalSwipeReverse(driver);
		Thread.sleep(1000);
		Utilities.verticalSwipeReverse(driver);
		//Click on Watch tab
		 homepagev2.tabClick("Watch");
		 Thread.sleep(2000);
		 //Click on the first carousal item
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
			 String showName="";
			 String episodeName="";
			 WebElement favCard=null;
			 boolean clickedCard=false;
			 boolean markedFav=false;
			 boolean presenceTray=false;
			 boolean favCardLocated=false;
			 boolean videoPaused=false;
			 
			 try {
				 watchpagev2.firstItemInCarousal.click();
				 test.log(LogStatus.INFO, "Clicked on carousal card");
				 clickedCard=true;
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on Carousal card");
			 }
			 if(clickedCard==true) {
				 homepagev2.verifyAndProgressBar();
				 kidsplayerpagev2.pauseVideo();
				 if(Utilities.explicitWaitVisibleNew(driver, watchpagev2.vodPlayerShowTitle, 5)) {
					 try {
						 showName=watchpagev2.vodPlayerShowTitle.getAttribute("text");
						 test.log(LogStatus.INFO, "Show Name: "+showName);
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to fetch Show name");
					 }
				 }
				 else {
					 test.log(LogStatus.FAIL, "Show name is not visible");
				 }
				 if(Utilities.explicitWaitVisibleNew(driver, watchpagev2.vodPlayerVodTitle, 5)) {
					 try {
						 episodeName=watchpagev2.vodPlayerVodTitle.getAttribute("text");
						 test.log(LogStatus.INFO, "Episode Name: "+episodeName);
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to fetch Episode name");
					 }
				 }
				 else {
					 test.log(LogStatus.FAIL, "Episode name is not visible");
				 }
				 if(Utilities.explicitWaitClickableNew(driver,watchpagev2.watchFirstItemPlayerFav,10)) {
					 watchpagev2.watchFirstItemPlayerFav.click();
					 test.log(LogStatus.INFO, "Clicked on Favourite icon");
					 if(Utilities.explicitWaitClickableNew(driver, homepagev2.favDialogOKButton, 3)) {
						 homepagev2.favDialogOKButton.click();
						 test.log(LogStatus.INFO, "Dismissed 'Added To Favorites' pop up");
					 }
					 markedFav=true;
				 }
				 else {
					 test.log(LogStatus.INFO, "Favourite icon is not clickable");
				 }
				 
			 }
			 if(markedFav==true) {
				 driver.navigate().back();
				 Utilities.verticalSwipeReverse(driver);
				 homepagev2.mystuff_tab.click();
				 Thread.sleep(2000);
				 String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
				 presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
				 if(presenceTray==true)
					 test.log(LogStatus.INFO, "Favourites tray is displayed"); 
				 else
					 test.log(LogStatus.FAIL, "Favourites tray is not displayed"); 
				 Utilities.verticalSwipe(driver);
			 }
			 if(presenceTray==true) {
				 String uiFavEpisodeLocator="//*[@resource-id='com.viacom18.vootkids:id/recent_title_text' and (@text='FAVOURITES' or @text='Favourites')]//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//*[@resource-id='com.viacom18.vootkids:id/grid_title' and contains(@text,\""+showName+"\")]/following-sibling::*[contains(@text,\""+episodeName+"\")]";				 
				 try {
					 favCard=driver.findElement(By.xpath(uiFavEpisodeLocator));
					 favCardLocated=true;
					 test.log(LogStatus.INFO, "Located Show title and Episode name under Favourites");
					 test.log(LogStatus.PASS, "Verify the click funtionality on tapping on favourite icon from player is PASS");
					 if(!Utilities.setResultsKids("VK_196", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.PASS, "Verify the metadata displayed for the favorited episode card is PASS");
					 if(!Utilities.setResultsKids("VK_204", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 
				 }
				 catch(Exception e) {
					 test.log(LogStatus.INFO, "Failed to locate Show title and Episode name under Favourites");
					 test.log(LogStatus.FAIL, "Verify the click funtionality on tapping on favourite icon from player is Fail");
					 if(!Utilities.setResultsKids("VK_196", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 test.log(LogStatus.FAIL, "Verify the metadata displayed for the favorited episode card is Fail");
					 if(!Utilities.setResultsKids("VK_204", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
			 }
			 if(favCardLocated==true) {
				 try {
					 favCard.click();
					 test.log(LogStatus.INFO, "Clicked on the favorited episode");
					 homepagev2.verifyAndProgressBar();
					 kidsplayerpagev2.pauseVideo();
					 test.log(LogStatus.INFO, "Paused the video");
					 videoPaused=true;
				 }
				 catch(Exception e) {
					 test.log(LogStatus.INFO, "Failed to click on the favorited episode");
				 }
			 }
			 if(videoPaused==true) {
				 if(Utilities.explicitWaitVisibleNew(driver, watchpagev2.playerExpand, 10)) {
					test.log(LogStatus.INFO, "Up Arrow is displayed, even though there is only one favourited card");
					test.log(LogStatus.FAIL, "Verify the availablity of Up arrow option if there is only 1 favourited episode content is FAIL");
					if(!Utilities.setResultsKids("VK_1748", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
				 else {
					test.log(LogStatus.INFO, "Up Arrow is not displayed because there is only one favourited card");
					test.log(LogStatus.PASS, "Verify the availablity of Up arrow option if there is only 1 favourited episode content is PASS");
					if(!Utilities.setResultsKids("VK_1748", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 }
			 }
			 if(Utilities.explicitWaitClickableNew(driver,watchpagev2.watchFirstItemPlayerFav,5)) {
				 watchpagev2.watchFirstItemPlayerFav.click();
				 if(Utilities.explicitWaitClickableNew(driver, homepagev2.favDialogOKButton, 3)) {
					 homepagev2.favDialogOKButton.click();
				 }
			 }
		 }	 			 	 
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
