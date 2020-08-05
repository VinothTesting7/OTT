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
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyAudioBooksDownloadsLockUnlockInterruptionFunctionality extends BaseTestV2 {

	String testName = "VerifyAudioBooksDownloadsLockUnlockInterruptionFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify  Audio Books Downloads Interruptions Functionality - Lock/Unlock");
	test.log(LogStatus.INFO, "Starting the test for Lock/Unlock Interruption during Audio Books Download in Progress Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	

	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int lockrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",lockrow, "Verify if downloading content is resumed post Locking and unlocking the device");		
	
	launchApp();
	
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
    ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
    DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
    
	 String timebefPause="";
	 //homepagev2.logout();
	 //homepagev2.login("audiodownloads@gmail.com", "vinoth@123");
	 //downloadspagev2.setDeviceAudioDownloadQuality("high");
	 int err_count=0;
	 // downloadspagev2.deleteDownload();
	 //Click on Listen tab
	 test.log(LogStatus.INFO, "Downloaded contents must be present in Downloads Segmented Tab");
	 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10)) 
	 homepagev2.Listen_tab.click();	
	 else
	 BasePageV2.reportFail("Not able to click on Listen Tab");
	 test.log(LogStatus.INFO, "Clicked on Listen tab");
	// BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 String audiostorytitle="";
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
	 {
		 //String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
		 //Utilities.verticalSwipe(driver, audiostory);
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 20))
		 {
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTrayAudioStoryTitle, 20))
			 {
				 audiostorytitle=listenpagev2.firstTrayAudioStoryTitle.getText();
			 }
			 else
			 test.log(LogStatus.FAIL, "Audio story title is missing");
			 
			 listenpagev2.firstTrayAudioStory.click();
		 }
		 else
			 BasePageV2.reportFail("No contents in the tray / Content not clickable");
	 }
	 else
		 BasePageV2.reportFail("Failed to find the tray");
	 
	 
	 test.log(LogStatus.INFO, "Navigating to the audio Detail page - "+audiostorytitle);
	 System.out.println("audio title is - "+audiostorytitle);
	 
	 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	 Utilities.verticalSwipe(driver, downloadlink);

	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioBookLink, 2))
	 { 
		 driver.navigate().back();
		 Thread.sleep(1000);
		 downloadspagev2.deleteDownloadTitle(audiostorytitle);
		 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10)) 
			 homepagev2.Listen_tab.click();	
		 else
			 BasePageV2.reportFail("Not able to click on Listen Tab");
			 test.log(LogStatus.INFO, "Clicked on Listen tab");
			 //BasePageV2.takeScreenshot();
			 Thread.sleep(2000);
			 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
			 tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
			 Utilities.verticalSwipe(driver, tray);
			audiostorytitle="";
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
			 {
				 String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
				 Utilities.verticalSwipe(driver, audiostory);
				 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 20))
				 {
					 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTrayAudioStoryTitle, 20))
					 {
						 audiostorytitle=listenpagev2.firstTrayAudioStoryTitle.getText();
					 }
					 else
					 test.log(LogStatus.FAIL, "Audio story title is missing");
					 
					 listenpagev2.firstTrayAudioStory.click();
				 }
				 else
					 BasePageV2.reportFail("No contents in the tray / Content not clickable");
			 }
			 else
				 BasePageV2.reportFail("Failed to find the tray");
			 
			 
			 test.log(LogStatus.INFO, "Navigating to the audio Detail page - "+audiostorytitle);
			 
			 
			 downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
			 Utilities.verticalSwipe(driver, downloadlink); 
	 }
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
	 {
		 Utilities.verticalSwipe(driver);
		 listenpagev2.downloadAudioBookLink.click();
		 test.log(LogStatus.INFO, "Downloading the Audio Book - "+audiostorytitle);
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadingProgress, 20))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after tapping download link");
	 
			 /*test.log(LogStatus.INFO, "Locking the device when downloading is in progress");
	    	 driver.lockDevice();
	    	 Thread.sleep(2000);
	    	 test.log(LogStatus.INFO,"UnLocking the device and verifying whether still download is in progress");
	         driver.unlockDevice();
	    	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadingProgress, 20))
			 {
				 test.log(LogStatus.INFO, "Downloading is in Progress after unlocking device");
			 }
			 else
				 BasePageV2.reportFail("Audio book is not downloading after unlocking device"); 
	    	 */
		 
	 
			 
	 //Goto my stuff and navigate to downloads tab
	 
	 driver.navigate().back();
	 Utilities.verticalSwipeReverse(driver);
	 Utilities.verticalSwipeReverse(driver);
	 Thread.sleep(1000);	 
	 Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 20))
	 {
		 Thread.sleep(2000);
		 test.log(LogStatus.INFO, "Navigating to My Stuff Tab");
		 homepagev2.mystuff_tab.click();
		 Thread.sleep(2000);
	 }
	 else
		 BasePageV2.reportFail("Failed to click on My Stuff Tab");
	 
	 
	 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
	 
	 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);

		if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
		homepagev2.downloadsTab.click();
		else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
		Thread.sleep(1000);
	    Utilities.verticalSwipe(driver);
		
	    	 try{
                   String xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and @text='"+audiostorytitle+"']//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'IN_PROGRESS')]";
	    		
	    		  driver.findElementByXPath(xpath);
	    		 test.log(LogStatus.INFO, "Content Download is in Progress");   
		    	 test.log(LogStatus.INFO, "locking the device when downloading is in progress");
		    	 driver.lockDevice();
		    	 //Thread.sleep(2000);
		    	 test.log(LogStatus.INFO,"Unlocking the device and verifying whether still download is in progress");
		    	 driver.unlockDevice(); 
		    	 
		    	 try{
		    		  driver.findElementByXPath(xpath);
		    		  test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Locking and unlocking the device' is Passed");
			    	  BasePageV2.smokeresults("", lockrow, "Pass");		    		  
		    		 }
		    	  catch(Exception e){
		    		  String xpath2="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and @text='"+audiostorytitle+"']//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'DOWNLOAD_COMPLETE')]";
			    	  try{
			    			 driver.findElementByXPath(xpath2);
			    		 }
			    		 catch(Exception ex){   BasePageV2.reportFail("Download is not in progress or not completed after unlocking device");}
			    		
			    	  test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Locking and unlocking the device' is Passed");
			    	  BasePageV2.smokeresults("", lockrow, "Pass");	
		    	  }
	    	 }
	    	 catch(Exception e){
	    		 String xpath3="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and @text='"+audiostorytitle+"']//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'DOWNLOAD_COMPLETE')]";
	    		 try{
	    			 driver.findElementByXPath(xpath3);
	    		      test.log(LogStatus.INFO, "Download is completed before checking scenario");
	    		 }
	    		 catch(Exception ex){  
	    			 BasePageV2.reportFail("Download is not in progress or not completed");}	
	    	 }
	    	
	    	 driver.navigate().back();
	    	 
		downloadspagev2.deleteDownloadTitle(audiostorytitle);				  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}