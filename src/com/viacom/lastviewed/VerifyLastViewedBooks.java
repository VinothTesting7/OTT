package com.viacom.lastviewed;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author Tanisha
public class VerifyLastViewedBooks extends BaseTestV2{
	
	String testName = "VerifyLastViewedBooks";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedBooks(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedBooks");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}		
		
		//VK_1508 = Verify availability of 'Last Viewed' section
		//VK_1683 = Verify if the Ebook cards are added to the Last viewed tray if user Opens and closes an Ebook
		//VK_1684 = Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook partially
		//VK_1685 = Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook Completely


		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 boolean locatedBookTray=false;
		 boolean firstBookClicked=false;
		 boolean secondBookClicked=false;
		 boolean thirdBookClicked=false;
		 boolean readerDisplayedPreview=false;
		 boolean locatedLVTray=false;
		 String bookTitle1="";
		 String bookTitle2="";
		 String bookTitle3="";
		 String lastViewed1,lastViewed2,lastViewed3="";
		 int errVK_1510=0;
			//Login module 
			//homepagev2.logout();
/*		 homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }*/
		 
		//Click on Read tab
		homepagev2.tabClick("Read");
		homepagev2.tabClick("Read");
		Utilities.verticalSwipe(driver); 
		String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
		for(int scroll=0;scroll<=3;scroll++) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(tray));
				test.log(LogStatus.INFO, "Located Books tray");
				locatedBookTray=true;
				break;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				if(scroll==3) {
					test.log(LogStatus.FAIL, "Failed to locate Books tray");
				}
			}
		}
		if(locatedBookTray==true) {
			String firstBookLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='0']";
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(firstBookLoc)).click();
				test.log(LogStatus.INFO, "Clicked on first book");
				firstBookClicked=true;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(firstBookLoc)).click();
					test.log(LogStatus.INFO, "Clicked on first book");
					firstBookClicked=true;
				}
				catch(Exception e1) {
					test.log(LogStatus.FAIL, "Failed to click on first book");
				}
			}
			//First book to open and close
			test.log(LogStatus.INFO, "----------------Launch and close book------------------");
			if(firstBookClicked==true) {
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisibleNew(driver, bookspagev2.bookDetailsPageBookTitle, 10)) {
					bookTitle1= bookspagev2.bookDetailsPageBookTitle.getAttribute("text");
					test.log(LogStatus.INFO, "First Book title: \""+bookTitle1+"\"");	
					bookTitle1=homepagev2.convertCamelCase(bookTitle1);
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate First Book title"); 
				}
				Utilities.verticalSwipeReverse(driver);
				//Tap on Try button
				if(Utilities.explicitWaitClickableNew(driver, bookspagev2.tryButton, 10)) {
					try {
						bookspagev2.tryButton.click();
						test.log(LogStatus.INFO, "Tapped on Try button");
						for(int wait=0;wait<=30;wait++) {
							 try {
								 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
								 //Add 1447
								 if(wait==30) {
									 test.log(LogStatus.FAIL, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
									 BasePageV2.takeScreenshot();
									 driver.navigate().back();
									 test.log(LogStatus.INFO, "Navigated back to book details page");
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.INFO, "Loader is not displayed");
								 Set<String> CHS = driver.getContextHandles();
								 for(String ch:CHS){
									if(ch.contains("WEBVIEW")){
										test.log(LogStatus.INFO, "eBook Reader is displayed");
										readerDisplayedPreview=true;
										break;
									}
								 }  
							 }	
							 if(readerDisplayedPreview==true)
								 break;
							 else
								 Thread.sleep(1000);
						 }				
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to tap on on Try button");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Try button is not clickable");
				}
				if(readerDisplayedPreview==true) {
					//TC to launch and close the book
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to close the book");
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to tray");	
				}
			}
			test.log(LogStatus.INFO, "----------------Launch and read book partially------------------");
			//Open second book and read partially
			readerDisplayedPreview=false;
			String secondBookLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='1']";
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(secondBookLoc)).click();
				test.log(LogStatus.INFO, "Clicked on second book");
				secondBookClicked=true;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(secondBookLoc)).click();
					test.log(LogStatus.INFO, "Clicked on second book");
					secondBookClicked=true;
				}
				catch(Exception e1) {
					test.log(LogStatus.FAIL, "Failed to click on second book");
				}
			}
			if(secondBookClicked==true) {
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisibleNew(driver, bookspagev2.bookDetailsPageBookTitle, 10)) {
					bookTitle2= bookspagev2.bookDetailsPageBookTitle.getAttribute("text");
					test.log(LogStatus.INFO, "Second Book title: \""+bookTitle2+"\"");	
					bookTitle2=homepagev2.convertCamelCase(bookTitle2);
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate Second Book title"); 
				}
				Utilities.verticalSwipeReverse(driver);
				//Tap on Try button
				if(Utilities.explicitWaitClickableNew(driver, bookspagev2.tryButton, 10)) {
					try {
						bookspagev2.tryButton.click();
						test.log(LogStatus.INFO, "Tapped on Try button");
						for(int wait=0;wait<=30;wait++) {
							 try {
								 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
								 //Add 1447
								 if(wait==30) {
									 test.log(LogStatus.FAIL, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
									 BasePageV2.takeScreenshot();
									 driver.navigate().back();
									 test.log(LogStatus.INFO, "Navigated back to book details page");
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.INFO, "Loader is not displayed");
								 Set<String> CHS = driver.getContextHandles();
								 for(String ch:CHS){
									if(ch.contains("WEBVIEW")){
										test.log(LogStatus.INFO, "eBook Reader is displayed");
										readerDisplayedPreview=true;
										break;
									}
								 }  
							 }	
							 if(readerDisplayedPreview==true)
								 break;
							 else
								 Thread.sleep(1000);
						 }				
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to tap on on Try button");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Try button is not clickable");
				}
				if(readerDisplayedPreview==true) {
					//TC to launch and read book partially
					Utilities.horizontalSwipe(driver);
					Thread.sleep(2000);
					test.log(LogStatus.INFO, "Swiped horizontally to page 1");
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to close the book");
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to tray");	
				}
			}
			test.log(LogStatus.INFO, "----------------Launch and complete book preview------------------");
			//Open third book and read completely
			readerDisplayedPreview=false;
			String thirdBookLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::*/parent::*//android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='2']";
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(thirdBookLoc)).click();
				test.log(LogStatus.INFO, "Clicked on third book");
				thirdBookClicked=true;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				try {
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					driver.findElement(By.xpath(thirdBookLoc)).click();
					test.log(LogStatus.INFO, "Clicked on third book");
					thirdBookClicked=true;
				}
				catch(Exception e1) {
					test.log(LogStatus.FAIL, "Failed to click on third book");
				}
			}
			if(thirdBookClicked==true) {
				Utilities.verticalSwipe(driver);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisibleNew(driver, bookspagev2.bookDetailsPageBookTitle, 10)) {
					bookTitle3= bookspagev2.bookDetailsPageBookTitle.getAttribute("text");
					test.log(LogStatus.INFO, "Third Book title: \""+bookTitle3+"\"");	
					bookTitle3=homepagev2.convertCamelCase(bookTitle3);
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate Third Book title"); 
				}
				Utilities.verticalSwipeReverse(driver);
				//Tap on Try button
				if(Utilities.explicitWaitClickableNew(driver, bookspagev2.tryButton, 10)) {
					try {
						bookspagev2.tryButton.click();
						test.log(LogStatus.INFO, "Tapped on Try button");
						for(int wait=0;wait<=30;wait++) {
							 try {
								 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
								 //Add 1447
								 if(wait==30) {
									 test.log(LogStatus.FAIL, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
									 BasePageV2.takeScreenshot();
									 driver.navigate().back();
									 test.log(LogStatus.INFO, "Navigated back to book details page");
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.INFO, "Loader is not displayed");
								 Set<String> CHS = driver.getContextHandles();
								 for(String ch:CHS){
									if(ch.contains("WEBVIEW")){
										test.log(LogStatus.INFO, "eBook Reader is displayed");
										readerDisplayedPreview=true;
										break;
									}
								 }  
							 }	
							 if(readerDisplayedPreview==true)
								 break;
							 else
								 Thread.sleep(1000);
						 }				
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to tap on on Try button");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Try button is not clickable");
				}
				if(readerDisplayedPreview==true) {
					//TC to launch and read book completely
					for(int scroll=0;scroll<=10;scroll++) {
						Utilities.horizontalSwipe(driver);
						Thread.sleep(2000);
					}
					if(Utilities.explicitWaitVisibleNew(driver, readpagev2.downloadInPopup, 10)) {
						test.log(LogStatus.INFO,"Completed book preview");
						driver.navigate().back();
						test.log(LogStatus.INFO, "Navigated back to dismiss download popup");
						
					}
					else {
						test.log(LogStatus.FAIL,"Failed to complete book preview");
					}
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to close the book");
					driver.navigate().back();
					test.log(LogStatus.INFO, "Navigated back to tray");	
				}
			}
		}	
		//Verify last viewed in Read tab
		test.log(LogStatus.INFO, "----------------Verify last viewed in Read tab------------------");
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
		homepagev2.tabClick("Read");
		homepagev2.tabClick("Read");

		//Scroll till Last Viewed
		String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		for(int i=0;i<50;i++) {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath(recentViewedClear));
				test.log(LogStatus.INFO, "Located Last Viewed tray");
				locatedLVTray=true;
				test.log(LogStatus.PASS, "Verify availability of 'Last Viewed' section is PASS");
				if(!Utilities.setResultsKids("VK_1508", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				break;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				if(i==50) {
					test.log(LogStatus.INFO, "Failed to locate Last Viewed tray");
					test.log(LogStatus.FAIL, "Verify availability of 'Last Viewed' section is FAIL");
					if(!Utilities.setResultsKids("VK_1508", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
			}
		}
		if(locatedLVTray==true) {
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
		    if(Utilities.explicitWaitVisibleNew(driver, bookspagev2.lvBookFirstItemTitle, 10)) {
		    	lastViewed1=bookspagev2.lvBookFirstItemTitle.getAttribute("text");
		    	if(lastViewed1.contains(bookTitle3)) {
		    		test.log(LogStatus.INFO, lastViewed1+" is the first latest book under last viewed in Read tab");
		    		test.log(LogStatus.PASS, "Verify if the Ebook cards are added to the Last viewed tray if user Opens and closes an Ebook is PASS");
		    		if(!Utilities.setResultsKids("VK_1683", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last viewed in Read tab instead of "+bookTitle3);
		    		test.log(LogStatus.FAIL, "Verify if the Ebook cards are added to the Last viewed tray if user Opens and closes an Ebook is FAIL");
		    		if(!Utilities.setResultsKids("VK_1683", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed Book 1 is not visible under last viewed in Read tab");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, bookspagev2.lvBookSecondItemTitle, 10)) {
		    	lastViewed2=bookspagev2.lvBookSecondItemTitle.getAttribute("text");
		    	if(lastViewed2.contains(bookTitle2)) {
		    		test.log(LogStatus.INFO, lastViewed2+" is the second latest book under last viewed in Read tab");	
		    		test.log(LogStatus.PASS, "Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook partially is PASS");
		    		if(!Utilities.setResultsKids("VK_1684", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last viewed in Read tab instead of "+bookTitle2);
		    		test.log(LogStatus.FAIL, "Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook partially is FAIL");
		    		if(!Utilities.setResultsKids("VK_1684", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed Book 2 is not visible under last viewed in Read tab");
		    }
		    if(Utilities.explicitWaitVisibleNew(driver, bookspagev2.lvBookThirdItemTitle, 10)) {
		    	lastViewed3=bookspagev2.lvBookThirdItemTitle.getAttribute("text");
		    	if(lastViewed3.contains(bookTitle1)) {
		    		test.log(LogStatus.INFO, lastViewed3+" is the third latest book under last viewed in Read tab");
		    		test.log(LogStatus.PASS, "Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook Completely is PASS");
		    		if(!Utilities.setResultsKids("VK_1685", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	else {
		    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest book under last viewed in Read tab instead of "+bookTitle1);
		    		test.log(LogStatus.FAIL, "Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook Completely is FAIL");
		    		if(!Utilities.setResultsKids("VK_1685", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    }
		    else {
		    	test.log(LogStatus.FAIL, "Last Viewed Book 3 is not visible under last viewed in Read tab");
		    }		
		}
		
	    basepagev2.takeScreenshot();

		homepagev2.mystuff_tab=null;
		homepagev2.lastViewedFirstItemTitle=null;
		homepagev2.lastViewedSecondItemTitle=null;
		homepagev2.lastViewedThirdItemTitle=null;
		homepagev2.recentViewedClear=null;
		homepagev2.recentViewedClearMessage=null;
		homepagev2.recentViewedClearYes=null;
		homepagev2.recentViewedClearNo=null;
		
		

	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
