package com.viacom.vodplayer;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

//Author : Vinoth

//priority - P1

//VK_2324 - Verify the functionality of OK button from Favorite pop up for an Audio content:
//VK_2327 - Verify the functionality of OK button from Favorite pop up for an Ebook:


public class VerifyFavoritePopUpFunctionalityInAudioAndEbookTest extends BaseTestV2 {
	String testName = "VerifyFavoritePopUpFunctionalityInAudioAndEbookTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("VerifyFavoritePopUpFunctionalityInAudioAndEbookTest");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Screen UI functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();

	driver.resetApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
    BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
    ReadPageV2 readpagev2=new ReadPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	homepagev2.login(un, pwd);
	
	String timebefPause="";
	 //Click on Watch tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.listen_tab, 10)) {
			homepagev2.tabClick("Listen");
	
	 //BasePageV2.takeScreenshot();
	//Click on the first carousal item
	 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 10)){
	 watchpagev2.firstItemInCarousal.click();
	 
	 test.log(LogStatus.INFO, "Playing any of the audio content from carousal");
	 

		if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPagePlayButton , 30))
		{
			 showspagev2.showDetailPagePlayButton.click();
		}
		else
		BasePageV2.reportFail("Play Button is not displayed in Show Detail page");
	 
		  if(Utilities.explicitWaitClickableNew(driver,listenpagev2.audioPlayerClose, 60)) {
				  
				  test.log(LogStatus.INFO, "Verifying the Favorite icon functionality of audio Player");	  
			
					if(Utilities.explicitWaitClickableNew(driver, listenpagev2.audioPlayerFavouriteButton, 30))
					{
						
						if(Utilities.explicitWaitClickableNew(driver, listenpagev2.audioPlayerFavouriteButton, 30))
						{
							listenpagev2.audioPlayerFavouriteButton.click();
							Thread.sleep(3000);
						}
						else
							test.log(LogStatus.FAIL, "Could not favorite the content");
						if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
						{
							if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopupMessage, 30))
								test.log(LogStatus.INFO, "Pop up message is displayed");
							else
								test.log(LogStatus.FAIL, "Pop up message is not displayed");
							if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
							{
								showspagev2.favIconPopupOkButton.click();
								Thread.sleep(3000);
								
								
									if(Utilities.explicitWaitClickableNew(driver, listenpagev2.audioPlayerFavouriteButton, 30))
									{
										test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for an Audio content:' is Passed");
										if(!Utilities.setResultsKids("VK_2324", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
									else
									{
										test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
										if(!Utilities.setResultsKids("VK_2324", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
							}
							else
							{
								test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting Episode content in player for first time");
								  if(!Utilities.setResultsKids("VK_2324", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							}
							
						}
						else if(Utilities.explicitWaitClickableNew(driver, listenpagev2.audioPlayerFavouriteButton, 30))
						{
							listenpagev2.audioPlayerFavouriteButton.click();
							if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
							{
								if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopupMessage, 30))
									test.log(LogStatus.INFO, "Pop up message is displayed");
								else
									test.log(LogStatus.FAIL, "Pop up message is not displayed");
								if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
								{
									showspagev2.favIconPopupOkButton.click();
									Thread.sleep(3000);
									
									
										if(Utilities.explicitWaitClickableNew(driver, listenpagev2.audioPlayerFavouriteButton, 30))
										{
											test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for an Audio content:' is Passed");
											if(!Utilities.setResultsKids("VK_2324", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
										}
										else
										{
											test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
											if(!Utilities.setResultsKids("VK_2324", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
										}
								}
								else
								{
									test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting Episode content in player for first time");
									  if(!Utilities.setResultsKids("VK_2324", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
								}
								
							}
						}
						else
						{
							if(!Utilities.setResultsKids("VK_2324", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							test.log(LogStatus.FAIL, "Pop up is not displayed after favoriting episode content in player for first time");
						}
						
					
						
					if(Utilities.explicitWaitVisibleNew(driver, listenpagev2.audioPlayerFavouriteButton, 30))
						{	
						    test.log(LogStatus.INFO, "Favourite icon gets filled after favoriting");
							listenpagev2.audioPlayerFavouriteButton.click();
							Thread.sleep(3000);
						}
						else
						{
							test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
						}
					}
					else
					{
						test.log(LogStatus.FAIL, "Favourite icon not found in audio player");
					}
					
					if(Utilities.explicitWaitClickableNew(driver, listenpagev2.audioPlayerClose, 30))
					{
						listenpagev2.audioPlayerClose.click();
					}
					else
						{
						test.log(LogStatus.FAIL, "Not able to click on audio player close button");
						BasePageV2.takeScreenshot();
						}
	   
		  }
		  else
			  test.log(LogStatus.FAIL, "Audio content failed to play");
	    }
	 else
		 test.log(LogStatus.FAIL, "Not able to click on carousal in watvh tab");
	 

	 driver.resetApp();
	 homepagev2.login(un, pwd);
	 
	 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) {
			homepagev2.tabClick("Read");

			if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 10)){
				 watchpagev2.firstItemInCarousal.click();
				 
				 test.log(LogStatus.INFO, "Reading any of the book content from carousal");
			//cHECK FOR TRY BUTTON
			
				 if (Utilities.explicitWaitVisibleNew(driver, homepagev2.allowpermissionbtn, 5))
						homepagev2.allowpermissionbtn.click();
				 
				 if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 60))
						bookspagev2.bookDetailPageBookPreviewButton.click();
						else
						BasePageV2.reportFail("Not able to click on preview button");
						
					   		Thread.sleep(5000);
					   		boolean readerDisplayedPreview=false;
							 for(int wait=0;wait<=30;wait++) {
								 try {
									 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
									 //Add 1447
									 if(wait==30) {
										 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
										 
										 BasePageV2.takeScreenshot();
										 driver.navigate().back();
										 test.log(LogStatus.INFO, "Navigated back to book details page");
									 }
								 }
								 catch(Exception e) {
									/// test.log(LogStatus.INFO, "Loader is not displayed");
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
							 
					   	 readpagev2.dismissReaderCoachCards();
					   	 Thread.sleep(3000);

					         Utilities.tap(driver);
					         Utilities.tap(driver);
		
		        test.log(LogStatus.INFO, "Verifying the Favorite icon functionality of ebook reader");	  
			
					if(Utilities.explicitWaitClickableNew(driver, readpagev2.bookReaderFavButton, 30))
					{
						
						if(Utilities.explicitWaitClickableNew(driver,readpagev2.bookReaderFavButton, 30))
						{
							readpagev2.bookReaderFavButton.click();
							Thread.sleep(3000);
						}
						else
							test.log(LogStatus.FAIL, "Not able to favorite book content in reader");
						
						if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
						{
							if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
								test.log(LogStatus.INFO, "Pop up message is displayed");
							else
								test.log(LogStatus.FAIL, "Pop up message is not displayed");
							if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
							{
								showspagev2.favIconPopupOkButton.click();
								Thread.sleep(3000);
									if(Utilities.explicitWaitClickableNew(driver, readpagev2.bookReaderFavButton, 30))
									{
										
										test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for an movie content:' is Passed");
										if(!Utilities.setResultsKids("VK_2327", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
									else
									{
										test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
										if(!Utilities.setResultsKids("VK_2327", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
							}
							else
							{
								test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting Episode content in player for first time");
								  if(!Utilities.setResultsKids("VK_2327", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							}
							
						}
						else if(Utilities.explicitWaitClickableNew(driver,readpagev2.bookReaderFavButton, 30))
						{
							readpagev2.bookReaderFavButton.click();
							Thread.sleep(3000);
							if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
							{
								if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
									test.log(LogStatus.INFO, "Pop up message is displayed");
								else
									test.log(LogStatus.FAIL, "Pop up message is not displayed");
								if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
								{
									showspagev2.favIconPopupOkButton.click();
									Thread.sleep(3000);
										if(Utilities.explicitWaitClickableNew(driver, readpagev2.bookReaderFavButton, 30))
										{
											
											test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for an movie content:' is Passed");
											if(!Utilities.setResultsKids("VK_2327", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
										}
										else
										{
											test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
											if(!Utilities.setResultsKids("VK_2327", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
										}
								}
								else
								{
									test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting Episode content in player for first time");
									  if(!Utilities.setResultsKids("VK_2327", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
								}
						  }
						}
						else
						{
							  if(!Utilities.setResultsKids("VK_2327", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							test.log(LogStatus.FAIL, "Pop up is not displayed after favoriting movie content in player for first time");
						}
						
					    	driver.runAppInBackground(Duration.ofSeconds(3));
						 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
						 driver.currentActivity();
					if(Utilities.explicitWaitVisibleNew(driver, readpagev2.bookReaderFavButton, 30))
						{	
							
						readpagev2.bookReaderFavButton.click();
							Thread.sleep(3000);
						}
						else
						{
							test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting");
						}
					}
					else
					{
						BasePageV2.reportFail("Favourite icon not found in reader");
					}
	 }
	}
   }
	 
		  
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}