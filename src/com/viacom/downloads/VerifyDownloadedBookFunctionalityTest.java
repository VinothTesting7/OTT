package com.viacom.downloads;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.offset.PointOption;

public class VerifyDownloadedBookFunctionalityTest extends BaseTestV2 {

	String testName = "VerifyDownloadedBookFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
	throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Downloaded Books Functionality");
	test.log(LogStatus.INFO, "Starting the test for Downloaded Books Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedmetadatarow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",downloadedmetadatarow, "Verify the metadata of completely downloaded cards under My Stuff - Downloads tab:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedPlaybackrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",downloadedPlaybackrow, "Verify that Reader player launches when tapping completely downloaded Ebook card from Downloads tab");	

	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int favrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",favrow, "Verify the availablity of favourite icon for the downloaded Ebook content");	
	

	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int settingsrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",settingsrow, "Verify the availablity of Settings icon for the downloaded Ebook content");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int scrubrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",scrubrow, "Verify the Scrub functionality in Reader player for Downloaded Ebook contents");	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrowrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",uparrowrow, "Verify the availablity of Up arrow option if there is only 1 Ebook content in downloads tab");	
	
	Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrowrow2=xls7.getRowCount("Smoke_Results")+1;
	xls7.setCellData("Smoke_Results", "Testcase Name",uparrowrow2, "Verify the availablity of Uparrow option if there are more than 2 Ebook contents in downloads tab");	
	
	Xls_Reader xls8 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int linkrow=xls8.getRowCount("Smoke_Results")+1;
	xls8.setCellData("Smoke_Results", "Testcase Name",linkrow, "Verify Download icon UI post complete download:");	
	
	
	Xls_Reader xls9 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int previewbuttonrow=xls9.getRowCount("Smoke_Results")+1;
	xls9.setCellData("Smoke_Results", "Testcase Name",previewbuttonrow, "Verify the CTA(Preview button) present on the Ebook detail screen post downloading the book");	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
     KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
 
	 downloadspagev2.setDeviceDownloadQuality("low");
	 int err_count=0;
	 //Click on Listen tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) {
	 homepagev2.read_tab.click();	
	 test.log(LogStatus.INFO, "Clicked on Read tab");
	// BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the Book Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 String ebookTitle="";
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTray, 20))
	 {
		 String ebook="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
		 Utilities.verticalSwipe(driver, ebook);
		 if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTrayEbook, 20))
		 {
			 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTrayEbookTitle, 20))
			 {
				 ebookTitle=bookspagev2.firstTrayEbookTitle.getText();
			 }
			 else
			 test.log(LogStatus.FAIL, "Book title is missing");
			 
			 bookspagev2.firstTrayEbook.click();
		 }
		 else
			 BasePageV2.reportFail("No contents in the tray / Content not clickable");
	 }
	 else
		 BasePageV2.reportFail("Failed to find the tray");
	 
	 
	 test.log(LogStatus.INFO, "Navigating to the Book Detail page - "+ebookTitle);
	 
	 
	 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	 Utilities.verticalSwipe(driver, downloadlink);
	 
	 if(!Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 5))
	 { 
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
	 {
		 Utilities.verticalSwipe(driver);
		 bookspagev2.downloadBookLink.click();
		 test.log(LogStatus.INFO, "Downloading the Book - "+ebookTitle);
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download book / Download link not available");
	
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 20))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
	 }
	 else
		 BasePageV2.reportFail("Book is not downloading after tapping download link");
	 
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 300))
	 {
		 test.log(LogStatus.INFO, "Download is completed ");
		 test.log(LogStatus.PASS, "Testcase : 'Verify Download icon UI post complete download:' is Passed");
		 BasePageV2.smokeresults("",linkrow, "Pass");
		 
		 
	 }
	 else
		 BasePageV2.reportFail("Book is still not downloaded after long time");
	 
	 Utilities.verticalSwipeDown(driver);
	 Utilities.verticalSwipeDown(driver);
	 
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookReadButton, 30))
	 {
		 test.log(LogStatus.INFO, "CTA(Preview button) changed from 'Preview' to 'Read' post downloading a book.");
		 test.log(LogStatus.PASS, "Testcase : 'Verify the CTA(Preview button) present on the Ebook detail screen post downloading the book' is Passed");
	     BasePageV2.smokeresults("", previewbuttonrow , "Pass");
	 }
	 else
		BasePageV2.reportFail("CTA(Preview button) present on the Ebook detail screen not changed from 'Preview' to 'Read' post downloading a book.");
	 
	 
	 }
	 //Goto my stuff and navigate to downloads tab
	 
	 driver.navigate().back();
	 Thread.sleep(1000);	 
	 Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 20))
	 {
		 Thread.sleep(1000);
		 test.log(LogStatus.INFO, "Navigating to My Stuff Tab");
		 homepagev2.mystuff_tab.click();
		 Thread.sleep(1000);
	 }
	 else
		 BasePageV2.reportFail("Failed to click on My Stuff Tab");
	 
	 
	 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
	 
	 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);

		if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
		homepagev2.downloadsTab.click();
		else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
		 Utilities.verticalSwipe(driver);
		test.log(LogStatus.INFO, "Checking meta data of downloaded content under Downloads Segmented Tab");
		if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 5))
		{
		  test.log(LogStatus.INFO, "Book Title is displayed");
		  if(downloadspagev2.downloadedContentTitle.getText().contains(ebookTitle))
			 test.log(LogStatus.INFO, "Book Title is verified");
		 else
		 {
			 test.log(LogStatus.FAIL, "Downloaded book title is not matching");
			 err_count++;
		 }
		  
		     if(Utilities.explicitWaitClickable(driver, downloadspagev2.downloadsImageThumbnail, 3))
		     {
		    	 test.log(LogStatus.INFO, "Downloading book Thumbnail is displayed");
		    
		     }
		     else
		     {
		    	 test.log(LogStatus.FAIL, "Downloading book Thumbnail is not displayed");
		    	 err_count++;
		     }
		     
		     if(Utilities.explicitWaitClickable(driver, downloadspagev2.downloadsImageSize, 3))
		    	{
		    	 if(!downloadspagev2.downloadsImageSize.getText().isEmpty())
		    		 test.log(LogStatus.INFO, "Downloading Book Size is displayed - "+downloadspagev2.downloadsImageSize.getText());
		    	}
		     else
		     {
		    	 test.log(LogStatus.FAIL, "Downloading Book Size is not displayed");
		    	 err_count++;
		     }
		  
	     if(Utilities.explicitWaitClickable(driver, downloadspagev2.contenttoDownloadandDownloaded, 5))
	     {
	    	 test.log(LogStatus.INFO, "Play icon is displayed");
	    	 if(err_count==0)
	    	 {
	    		 test.log(LogStatus.PASS,"Testcase : 'Verify the metadata of completely downloaded cards under My Stuff - Downloads tab:' is Passed");
	    	     BasePageV2.smokeresults("", downloadedmetadatarow, "Pass");
	    	 }
	    	test.log(LogStatus.INFO, "Playing the downloaded book");
	    	downloadspagev2.contenttoDownloadandDownloaded.click();
	    }
	     else
	        BasePageV2.reportFail("Play icon is not displayed");
	     Thread.sleep(5000);
         Set<String> CHS = driver.getContextHandles();
	     for(String ch:CHS)
	    	 {
	    	   System.out.println("Current handle is:"+ch);
	    	   if(ch.contains("WEBVIEW"))
	    	   {
	    		 
	    			   test.log(LogStatus.INFO, "Reader launches when clicking  downloaded book");
	    			   test.log(LogStatus.PASS,"Testcase : 'Verify that Reader player launches when tapping completely downloaded Ebook card from Downloads tab' is Passed");
	    		       BasePageV2.smokeresults("", downloadedPlaybackrow, "Pass");
	    		
	    	   }
	    	 }
	         
	            Utilities.tap(driver);
	            Utilities.tap(driver);
	    		 test.log(LogStatus.INFO, "Verifying the availability of Settings icon for the downloaded book content");
	    		 if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderSettingsButton, 10))
	    		 {
	    			 test.log(LogStatus.PASS,"Testcase : 'Verify the availablity of Settings icon for the downloaded Ebook content' is Passed");
		    		 BasePageV2.smokeresults("", settingsrow, "Pass");
	    		 }
	    	     else
	    	    	 BasePageV2.reportFail("Settings icon not displayed in Book Reader"); 
	    		 
	    		
	    		 
	    		 test.log(LogStatus.INFO, "Verifying availability of UpArrow ");
	    		 
	    		 
	        	 if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderUpArrowButton, 5))
		    	 {
		    		test.log(LogStatus.FAIL, "Up Arrow button present for the downloaded book content");
		    	 }
		    	 else
		    	 {	 
		    		 test.log(LogStatus.PASS,"Testcase : 'Verify the availablity of Up arrow option if there is only 1 Ebook content in downloads tab' is Passed");
		    		 BasePageV2.smokeresults("", uparrowrow, "Pass");
		    	 }
	    		 
	        	 test.log(LogStatus.INFO, "Verifying availability of Favorite button for the downloaded book content");
		    	 if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderFavButton, 5))
		    	 {
		    		test.log(LogStatus.FAIL, "Favorite button present for the downloaded book content");
		    	 }
		    	 else
		    	 {	 
		    		 test.log(LogStatus.PASS,"Testcase : 'Verify the availablity of favourite icon for the downloaded Ebook content' is Passed");
		    		 BasePageV2.smokeresults("", favrow, "Pass");
		    	 }
	        	 
	        	 test.log(LogStatus.INFO, "Verifying Scrub functionality in the Reader Player");
	    		 bookspagev2.slideHalfBookReader(driver);
	    		 test.log(LogStatus.PASS,"Testcase : 'Verify the Scrub functionality in Reader player for Downloaded Ebook contents' is Passed");
	    		 BasePageV2.smokeresults("", scrubrow, "Pass");
		    	 
	    		 if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderCloseButton, 10))
	    	     playerpagev2.bookReaderCloseButton.click();
	    	     else
	    	     BasePageV2.reportFail("Close button not displayed in Book Reader"); 
	    		 

	    	     driver.navigate().back();
	    	     
	    	     Utilities.verticalSwipeDown(driver, homepagev2.read_tab);
	    	     if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10))
	    	    	 homepagev2.read_tab.click();
		    	  else
		    	  BasePageV2.reportFail("Not able to click Read Tab"); 
	    	     
	    	     test.log(LogStatus.INFO,"Scrolling to any of the Book Tray");
	    		 tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	    		 Utilities.verticalSwipe(driver, tray);
	    		 String ebookTitle2="";
	    		 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTray, 20))
	    		 {
	    			 String ebook="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
	    			 Utilities.verticalSwipe(driver, ebook);
	    			 if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTraySecondEbook, 20))
	    			 {
	    				 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTraySecondEbookTitle, 20))
	    				 {
	    					 ebookTitle2=bookspagev2.firstTraySecondEbookTitle.getText();
	    				 }
	    				 else
	    				 {
	    					 test.log(LogStatus.FAIL, "Book title is missing");
	    					 BasePageV2.takeScreenshot();
	    				 }
	    				 
	    				 bookspagev2.firstTraySecondEbook.click();
	    			 }
	    			 else
	    				 BasePageV2.reportFail("No contents in the tray / Content not clickable");
	    		 }
	    		 else
	    			 BasePageV2.reportFail("Failed to find the tray");
	    		 
	    		 
	    		 test.log(LogStatus.INFO, "Navigating to the Book Detail page - "+ebookTitle2);
	    		 
	    		 
	    	      downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	    		 Utilities.verticalSwipe(driver, downloadlink);
	    		 
	    		 if(!Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 5))
	    		 { 
	    		 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
	    		 {
	    			 Utilities.verticalSwipe(driver);
	    			 bookspagev2.downloadBookLink.click();
	    			 test.log(LogStatus.INFO, "Downloading the second Book - "+ebookTitle2);
	    		 }
	    		 else
	    			 BasePageV2.reportFail("Not able to click on Download book / Download link not available");
	    		
	    		 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 20))
	    		 {
	    			 test.log(LogStatus.INFO, "Downloading is in Progress ");
	    		 }
	    		 else
	    			 BasePageV2.reportFail("Book is not downloading after tapping download link");
	    		 
	    		 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 300))
	    		 {
	    			 test.log(LogStatus.INFO, "Download is completed for book-"+ebookTitle2);
	    		 }
	    		 else
	    			 BasePageV2.reportFail("Book is still not downloaded after long time");
	    	     
	    	     
	    	     
	    		 }
	    		 driver.navigate().back();
	    		 Thread.sleep(1000);	 
	    		 Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	    		 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 20))
	    		 {
	    			 Thread.sleep(1000);
	    			 test.log(LogStatus.INFO, "Navigating to My Stuff Tab");
	    			 homepagev2.mystuff_tab.click();
	    			 Thread.sleep(1000);
	    		 }
	    		 else
	    			 BasePageV2.reportFail("Failed to click on My Stuff Tab");
	    		 
	    		 
	    		 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
	    		 
	    		 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);

	    			if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
	    			homepagev2.downloadsTab.click();
	    			else
	    			BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
	    	    	Utilities.verticalSwipe(driver);	 
	    		    Utilities.verticalSwipe(driver);
	    	     
	    	     
	    	     
	    			if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 5))
	    			{
	    			 
	    		    	test.log(LogStatus.INFO, "Playing the downloaded book");
	    		    	downloadspagev2.contenttoDownloadandDownloaded.click();
	    		    }
	    		     else
	    		        BasePageV2.reportFail("Play icon is not displayed");
	    		     Thread.sleep(5000);
	    	           CHS = driver.getContextHandles();
	    		     for(String ch:CHS)
	    		    	 {
	    		    	   System.out.println("Current handle is:"+ch);
	    		    	   if(ch.contains("WEBVIEW"))
	    		    	   {
	    		    		 
	    		    			   test.log(LogStatus.INFO, "Reader launches when clicking  downloaded book");
	    		    			   test.log(LogStatus.PASS,"Testcase : 'Verify that Reader player launches when tapping completely downloaded Ebook card from Downloads tab' is Passed");
	    		    		       BasePageV2.smokeresults("", downloadedPlaybackrow, "Pass");
	    		    		
	    		    	   }
	    		    	 }
	    		         
	    		            Utilities.tap(driver);
	    		            Utilities.tap(driver);
	    		    		
	    		    		 
	    		    		 test.log(LogStatus.INFO, "Verifying availability of UpArrow ");
	    		    		 
	    		    		 
	    		        	 if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderUpArrowButton, 5))
	    			    	 {
	    		        		 test.log(LogStatus.PASS,"Testcase : 'Verify the availablity of Uparrow option if there are more than 2 Ebook contents in downloads tab' is Passed");
	    			    		 BasePageV2.smokeresults("", uparrowrow2, "Pass");
	    			    		 playerpagev2.bookReaderUpArrowButton.click();
	    			    		 test.log(LogStatus.INFO, "Verifying the Up Arrow Overlay");
	    			    		 
	    			    		 
	    			    		 
	    			    	 }
	    			    	 else
	    			    	 {	 
	    			    		 test.log(LogStatus.FAIL, "Up Arrow button not present for the downloaded book content when more than 2 Ebook contents in downloads tab");
	    			    	 }
	    		    		 
	    		        	
   
	    	     
	    	     
	    	     
	    	     
	    	     
  
		}
		else
		{
			BasePageV2.reportFail("Content title which is downloaded is not displayed under Downloads-Segmented Tab in My Stuff");
		}
		

	 
	 
	
		
		

		
		
		
		
		
	}		  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}