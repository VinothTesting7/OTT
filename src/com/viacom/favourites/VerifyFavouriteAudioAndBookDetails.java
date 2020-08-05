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
public class VerifyFavouriteAudioAndBookDetails extends BaseTestV2{
	/*
	String testName = "VerifyFavouriteAudioAndBookDetails";
	@Test(dataProvider = "getData")
	public void VerifyFavouriteAudioAndBookDetails(Hashtable<String, String> data) throws Exception 
	{		

		int errCount=0;
		test = rep.startTest("VerifyFavouriteAudioAndBookDetails");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		//Class contains test cases from Adhoc defects
		//Mark Favourite in audio player and verify audio details favorite icon is highlighted
		//Mark Favourite in book reader and verify book details favorite icon is highlighted
		//Check if pop appears on unfavouriting
		
	
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 //homepagev2.login(data.get("Email"),data.get("Password"));
		 //Login module to be added
		 //homepagev2.signup();
		 
		
		 
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 //System.out.println(driver.getPageSource());
		 }

		//Verification of 199
		//Click on Listen tab
		 test.log(LogStatus.INFO, "---------------------------------------------------------------");
		 homepagev2.tabClick("Listen");
		 Thread.sleep(2000);
		 Utilities.verticalSwipe(driver);
		 String audioTitle="", audioTitleUpper="", audioTitleCamel="";
		 String audioAuthor="", audioAuthorUpper="", audioAuthorCamel="";
		 String bookTitle="", bookTitleUpper="", bookTitleCamel="";
		 String bookAuthor="", bookAuthorUpper="", bookAuthorCamel="";
		 boolean audioPlayerDisplayed=false;
		 boolean audioPlayerFavButtonClicked=false;
		 boolean bookReaderDisplayed=false;
		 boolean bookReaderFavButtonClicked=false;
		 
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.newAudioTextOne, 5)) {
			 listenpagev2.newAudioTextOne.click(); 
			 test.log(LogStatus.INFO, "Clicked on the first Audio from tray");  	
			 //Wait for audio details page to load
			 listenpagev2.waitForAudioDetailsToLoad();
			 Utilities.verticalSwipe(driver);
			 if(Utilities.explicitWaitVisibleNew(driver, listenpagev2.nameFromDetailsPage, 10)) {
				 audioTitle=listenpagev2.nameFromDetailsPage.getAttribute("text").trim();
				 audioTitleUpper=audioTitle.toUpperCase();
				 audioTitleCamel=homepagev2.convertCamelCase(audioTitle);
				 audioAuthor=listenpagev2.authorFromDetailsPage.getAttribute("text").trim();
				 audioAuthorUpper=audioAuthor.toUpperCase();
				 audioAuthorCamel=homepagev2.convertCamelCase(audioAuthor);
				 test.log(LogStatus.INFO, "Audio clicked title: "+audioTitleCamel+", author: "+audioAuthorCamel);
			 }
			 else{
				 test.log(LogStatus.FAIL, "Audio details not fetched");
			 }
		 }
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 //Launch Audio player
		 if(Utilities.explicitWaitClickableNew(driver, listenpagev2.playFromDetailsPage, 10)){
			 listenpagev2.playFromDetailsPage.click();
			 test.log(LogStatus.INFO, "Clicked on Play from Audio Details");
			 for(int wait=0;wait<=15;wait++) {
				 if(Utilities.explicitWaitVisibleNew(driver, listenpagev2.audioPlayerSettings, 10)) {
					 test.log(LogStatus.INFO, "Audio Player is displayed");
					 Thread.sleep(3000);
					 if(Utilities.explicitWaitClickableNew(driver, listenpagev2.play_btn, 10)) {
						 try {
							 listenpagev2.play_btn.click();
							 test.log(LogStatus.INFO, "Paused the Audio");
							 audioPlayerDisplayed=true;
							 break;
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Failed to pause Audio");
						 }
					 }
				 }
				 else {
					 Thread.sleep(1000);
					 if(wait==15) {
						 test.log(LogStatus.FAIL, "Audio Player failed to display");
					 }
				 }
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "Play button is not clickable in Audio details page");
		 }
		 if(audioPlayerDisplayed==true) {
			 //Mark as Favourite
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerFavouriteButton, 10)) {
				 try {
					 listenpagev2.audioPlayerFavouriteButton.click();
					 audioPlayerFavButtonClicked=true;
					 test.log(LogStatus.INFO,"Marked as Favourite in Audio Player"); 
					 if(Utilities.explicitWaitClickableNew(driver, homepagev2.favDialogOKButton, 5)) {
						 homepagev2.favDialogOKButton.click();
						 test.log(LogStatus.INFO, "Dismissed 'Added To Favorites' pop up");
					 }
					 homepagev2.takeScreenshot();
					 //Close the player
					 if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerClose, 5)) {
						 try {
							 listenpagev2.audioPlayerClose.click();
							 test.log(LogStatus.INFO, "Closed the Audio Player");			 
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Failed to close the player");
						 }
					 }
					 else {
						 driver.navigate().back();
						 test.log(LogStatus.INFO, "Navigated back to details page");
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click Favorite button in Audio player");
				 }
			 }
		 }
		 if(audioPlayerFavButtonClicked==true) {
			 //Check if favorite button highlighted in details page.
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.favIconAudioDetails, 3)) {
				 test.log(LogStatus.INFO, "Located Favorite icon in Audio details page");
				 try {
					 if(listenpagev2.favIconAudioDetails.getAttribute("checked").equals("true")) {
						 test.log(LogStatus.PASS, "Verified Favorite icon is highlighted in Audio details after favoriting from Audio player");
					 }
					 else {
						 test.log(LogStatus.FAIL, "Verified Favorite icon is NOT highlighted in Audio details after favoriting from Audio player");
					 }
					 BasePageV2.takeScreenshot();
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to get the Favorite icon status in Audio details page");
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Favorite icon is not visible in Audio details page");
			 }
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.favIconAudioDetails, 10)) {
				 try {
					 listenpagev2.favIconAudioDetails.click();
					 test.log(LogStatus.INFO, "Unfavorited the audio");
				 }
				 catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to unfavorite audio");
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Favorite button is not clickable in audio details");
			 }
			 driver.navigate().back();
			 Utilities.verticalSwipeReverse(driver);
			 Utilities.verticalSwipeReverse(driver);
			 Thread.sleep(2000);
			 test.log(LogStatus.INFO, "---------------------------------------------------------------");
			 homepagev2.tabClick("Read");
			 
		 }
		 for(int scroll=0;scroll<3;scroll++) {
			 if(Utilities.explicitWaitClickable(driver, readpagev2.firstTrayfirstBook, 2)) {
				 try {
					 readpagev2.firstTrayfirstBook.click();
					 test.log(LogStatus.INFO, "Clicked on first book from tray");
					 //Wait for book details page to load
					 readpagev2.waitForBookDetailsToLoad();
					 Utilities.verticalSwipe(driver);
					 if(Utilities.explicitWaitVisibleNew(driver, readpagev2.nameFromDetailsPage, 10)) {
						 bookTitle=readpagev2.nameFromDetailsPage.getAttribute("text").trim();
						 bookTitleUpper=bookTitle.toUpperCase();
						 bookTitleCamel=homepagev2.convertCamelCase(bookTitle);
						 bookAuthor=readpagev2.authorFromDetailsPage.getAttribute("text").trim();
						 bookAuthorUpper=bookAuthor.toUpperCase();
						 bookAuthorCamel=homepagev2.convertCamelCase(bookAuthor);
						 test.log(LogStatus.INFO, "Book clicked title: "+bookTitleCamel+", author: "+bookAuthorCamel);
					 }
					 else{
						 test.log(LogStatus.FAIL, "Book details not fetched");
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click on first book from tray");
				 }
			 }
			 else {
				 Utilities.verticalSwipe(driver);
				 if(scroll==3) {
					 test.log(LogStatus.FAIL, "Failed to locate book under tray");
				 }
			 }
		 }
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 //Launch Book reader
		 if(Utilities.explicitWaitClickableNew(driver, readpagev2.tryPreviewReadButton, 10)){
			readpagev2.tryPreviewReadButton.click();
			test.log(LogStatus.INFO, "Clicked on Try/Read from Book Details");
			readpagev2.dismissReaderCoachCards();
			for(int wait=0;wait<=15;wait++) {
				try {
					if(readpagev2.bookReader.isDisplayed()) {
						test.log(LogStatus.INFO, "Book Reader is displayed");
						bookReaderDisplayed=true;
						break;
					}
					else if(wait==15)
						test.log(LogStatus.FAIL, "Book Reader failed to display");
				}
				catch(Exception e) {
					Thread.sleep(2000);
					if(wait==15)
						test.log(LogStatus.FAIL, "Book Reader failed to display");
				}
			}
		}
		else{
			test.log(LogStatus.FAIL, "Try/Read from Book Details is not clickable");
		}
		if(bookReaderDisplayed==true) {
			Thread.sleep(2000);
			Utilities.tap(driver);
			Utilities.tap(driver);
			test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
			if(Utilities.explicitWaitClickableNew(driver, readpagev2.bookReaderFavButton, 10)) {
				try {
					readpagev2.bookReaderFavButton.click();
					test.log(LogStatus.INFO, "Clicked on Favourite button");
					bookReaderFavButtonClicked=true;
					test.log(LogStatus.INFO,"Marked as Favourite in Book Reader"); 
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Favourite button in book reader");
				}
				BasePageV2.takeScreenshot();
			}
		}
		if(bookReaderFavButtonClicked==true) {
			driver.navigate().back();
			test.log(LogStatus.INFO, "Dismissed controls");
			driver.navigate().back();
			test.log(LogStatus.INFO, "Navigated back to book details");
			if(Utilities.explicitWaitVisibleNew(driver, readpagev2.favIconBookDetails, 5)) {
				test.log(LogStatus.INFO, "Located Favorite button in book details");
				try {
					if(readpagev2.favIconBookDetails.getAttribute("checked").equals("true")) {
						test.log(LogStatus.PASS, "Verified Favorite icon is highlighted in Book details after favoriting from Book Reader");
					}
					else {
						test.log(LogStatus.FAIL, "Verified Favorite icon is NOT highlighted in Book details after favoriting from Book Reader");
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to get the Favorite icon status in Book details page");
				}
			}
			BasePageV2.takeScreenshot();
			//Unfavorite
			if(Utilities.explicitWaitClickableNew(driver, readpagev2.favIconBookDetails, 10)) {
				try {
					readpagev2.favIconBookDetails.click();
					test.log(LogStatus.INFO, "Unfavorited the book");
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to unfavorite book");
				}
			}
			else {
				 test.log(LogStatus.FAIL, "Favorite button is not clickable in book details");
			}
		}
		test.log(LogStatus.INFO, "---------------------------------------------------------------");
		test.log(LogStatus.INFO, "Verify if cards are removed from Favourites tray");
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("My Stuff");
		Thread.sleep(2000);
		for(int scroll=0;scroll<=40;scroll++) {
			if(Utilities.explicitWaitVisibleNew(driver, homepagev2.favoriteTray, 1)) {
				test.log(LogStatus.FAIL, "Favourite tray is displayed");
				break;
			}
			else if(scroll==40) {
				test.log(LogStatus.INFO, "Favourite tray could not be located because items are marked unfavorite");
			}
		}
	}

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
	
   	*/	
	
}
