package com.viacom.downloads;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;
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

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class VerifyEBooksDownloadsKillAppInterruptionFunctionality extends BaseTestV2 {

	String testName = "VerifyEBooksDownloadsKillAppInterruptionFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Books Downloads  Interruptions Functionality - Kill/Open App");
	test.log(LogStatus.INFO, "Starting the test for Kill/Open App during Books Download in Progress Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	

	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int killrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",killrow, "Verify if downloading content is resumed post Killing & Opening the app");	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
	// homepagev2.logout();
	// homepagev2.login("audiodownloads@gmail.com", "vinoth@123");
	 downloadspagev2.setDeviceDownloadQuality("high");
	 int err_count=0;
	 
	// downloadspagev2.deleteDownload();
	 //Click on Listen tab
	 test.log(LogStatus.INFO, "Downloaded contents must be present in Downloads Segmented Tab");
	 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) 
	 homepagev2.read_tab.click();	
	 else
	 BasePageV2.reportFail("Not able to click on Read Tab");
	 test.log(LogStatus.INFO, "Clicked on Read tab");
	 //BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 
	 test.log(LogStatus.INFO,"Scrolling to any of the Books Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 String ebookTitle="";
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTray, 20))
	 {
		 String book="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
		 Utilities.verticalSwipe(driver, book);
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

	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 2))
	 { 
		 driver.navigate().back();
		 Thread.sleep(1000);
		 downloadspagev2.deleteDownloadTitle(ebookTitle);
		 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) 
			 homepagev2.read_tab.click();	
		 else
			 BasePageV2.reportFail("Not able to click on Read Tab");
			 test.log(LogStatus.INFO, "Clicked on Read tab");
			// BasePageV2.takeScreenshot();
			 Thread.sleep(2000);
			 test.log(LogStatus.INFO,"Scrolling to any of the Books Tray");
			 tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
			 Utilities.verticalSwipe(driver, tray);
			ebookTitle="";
			 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTray, 20))
			 {
				 String book="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
				 Utilities.verticalSwipe(driver, book);
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
			 
			 
			 test.log(LogStatus.INFO, "Navigating to the book Detail page - "+ebookTitle);
			 
			 
			 downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
			 Utilities.verticalSwipe(driver, downloadlink);  
		 
	 }
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
	 {
		 Utilities.verticalSwipe(driver);
		 bookspagev2.downloadBookLink.click();
		 test.log(LogStatus.INFO, "Downloading the Book - "+ebookTitle);
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download  book / Download link not available");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 40))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
	 }
	 else
		 BasePageV2.reportFail("Book is not downloading after tapping download link");
	 
test.log(LogStatus.INFO, "Killing the App when downloading is in progress");

	 
	 try
		{
		 driver.closeApp();
		} catch (Exception e)
		{		
			
		}
		try{
			test.log(LogStatus.INFO,"launching the app again");
			driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		}
		catch(Exception e){
			BasePageV2.reportFail("Failed to launch the App");
		}
	 //Goto my stuff and navigate to downloads tab
	 Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);

	 
	 test.log(LogStatus.INFO, "Scrolling to Downloads tab and verifying whether download is in progress");
	 
	 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);

		if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
		homepagev2.downloadsTab.click();
		else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
		
		Thread.sleep(1000);
		
	    Utilities.verticalSwipe(driver);
	    Utilities.verticalSwipe(driver);
	    	 try{
                   String xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and contains(@text,'"+ebookTitle+"')]//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_download_item' and contains(@content-desc,'IN_PROGRESS')]";
	    		
	    		 driver.findElementByXPath(xpath);
	    		 test.log(LogStatus.INFO, "Content Download is in Progress");   
		    	
	
		    		  test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Killing & Opening the app' is Passed");
			    	  BasePageV2.smokeresults("", killrow, "Pass");		    		  
		    		
	    	 }
	    	 catch(Exception e){
	    		 String xpath3="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and contains(@text,'"+ebookTitle+"')]/ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_download_item' and contains(@content-desc,'DOWNLOAD_COMPLETE')]";
	    		 try{
	    			 driver.findElementByXPath(xpath3);
	    			 test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Killing & Opening the app' is Passed");
			    	  BasePageV2.smokeresults("", killrow, "Pass");	
	    		 }
	    		 catch(Exception ex){   BasePageV2.reportFail("Download is not in progress or not completed");}	
	    	 }
	    	
	    	 
	    	 
		downloadspagev2.deleteDownloadTitle(ebookTitle);

	
	 		
		
		
		
		
		  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}