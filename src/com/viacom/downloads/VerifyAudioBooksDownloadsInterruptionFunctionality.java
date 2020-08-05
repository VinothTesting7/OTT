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

public class VerifyAudioBooksDownloadsInterruptionFunctionality extends BaseTestV2 {

	String testName = "VerifyAudioBooksDownloadsInterruptionFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify  Audio Books Downloads Interruptions Functionality - Minimize/Resume");
	test.log(LogStatus.INFO, "Starting the test for Minimize/Resume App Interruption during Audio Books Download in Progress Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",downloadrow, "Verify if the Downloads appear in Downloads tab when user puts the app to the background and again brings back to the foreground:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int minrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",minrow, "Verify if downloading content is resumed post Minimising & Resuming the app");	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
	 
	 //homepagev2.logout();
	 //homepagev2.login("tanisha.cutinha@gmail.com", "tanisha19");
	 if(Utilities.explicitWaitVisible(driver, launchpagev2.selectFirstProfile, 30)) 
	 launchpagev2.selectFirstProfile.click();	
	 //downloadspagev2.setDeviceAudioDownloadQuality("high");
	 int err_count=0;

	 //downloadspagev2.deleteDownload();
	 //Click on Listen tab
	 test.log(LogStatus.INFO, "Downloaded contents must be present in Downloads Segmented Tab");
	 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 30)) 
	 homepagev2.Listen_tab.click();	
	 else
	 BasePageV2.reportFail("Not able to click on Listen Tab");
	 test.log(LogStatus.INFO, "Clicked on Listen tab");
	 //BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 Utilities.verticalSwipe(driver);
	 String audiostorytitle="";
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 30))
	 {
		 //String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
		 //Utilities.verticalSwipe(driver, audiostory);
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 30))
		 {
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTrayAudioStoryTitle, 30))
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
	 
	 
	 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	 Utilities.verticalSwipe(driver, downloadlink);

	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioBookLink, 30))
	 {
		 driver.navigate().back();
		 Thread.sleep(1000);
		 downloadspagev2.deleteDownloadTitle(audiostorytitle);
		 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 30)) 
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
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 30))
			 {
				 String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
				 Utilities.verticalSwipe(driver, audiostory);
				 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 30))
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
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 30))
	 {
		 Utilities.verticalSwipe(driver);
		 listenpagev2.downloadAudioBookLink.click();
		 test.log(LogStatus.INFO, "Downloading the Audio Book - "+audiostorytitle);
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadingProgress, 30))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after tapping download link");
	 
	 
	 
	 test.log(LogStatus.INFO, "Minimizing the App when downloading is in progress");
	 driver.runAppInBackground(Duration.ofSeconds(3));
	 test.log(LogStatus.INFO,"Resuming the App and verifying whether still download is in progress");
	 
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadingProgress, 30))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after resuming app");
	 
	 //Goto my stuff and navigate to downloads tab
	 
	 driver.navigate().back();
	 Utilities.verticalSwipeReverse(driver);
	 Utilities.verticalSwipeReverse(driver);
	 Thread.sleep(1000);	 
	 Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 30))
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

		if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 30))
		homepagev2.downloadsTab.click();
		else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
		Thread.sleep(1000);
	    Utilities.verticalSwipe(driver);
		
	    	 try{
                   String xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and @text='"+audiostorytitle+"']//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'IN_PROGRESS')]";
	    		
	    		  driver.findElementByXPath(xpath);
	    		 test.log(LogStatus.INFO, "Content Download is in Progress");   
	    		 test.log(LogStatus.PASS, "Verify if the Downloads appear in Downloads tab when user puts the app to the background and again brings back to the foreground:");
	    		 BasePageV2.smokeresults("", downloadrow, "Pass");
	    		 
		    	 test.log(LogStatus.INFO, "Minimizing the App when downloading is in progress");
		    	 driver.runAppInBackground(Duration.ofSeconds(3));
		    	 test.log(LogStatus.INFO,"Resuming the App and verifying whether still download is in progress");
		    	  try{
		    		  driver.findElementByXPath(xpath);
		    		  test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Minimising & Resuming the app' is Passed");
			    	  BasePageV2.smokeresults("", minrow, "Pass");		    		  
		    		 }
		    	  catch(Exception e){
		    		  String xpath2="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and @text='"+audiostorytitle+"']//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'DOWNLOAD_COMPLETE')]";
			    	  try{
			    			 driver.findElementByXPath(xpath2);
			    			
			    		 }
			    		 catch(Exception ex){   BasePageV2.reportFail("Download is not in progress or not completed");}
			    		
			    	  test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Minimising & Resuming the app' is Passed");
			    	  BasePageV2.smokeresults("", minrow, "Pass");	
		    	  }
	    	 }
	    	 catch(Exception e){
	    		 String xpath3="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and @text='"+audiostorytitle+"']//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'DOWNLOAD_COMPLETE')]";
	    		 for(int wait=0;wait<=10;wait++) {
	    			 try{
		    			 driver.findElementByXPath(xpath3);
			    		 test.log(LogStatus.PASS, "Verify if the Downloads appear in Downloads tab when user puts the app to the background and again brings back to the foreground:");
			    		 BasePageV2.smokeresults("", downloadrow, "Pass");
		    			 test.log(LogStatus.INFO, "Download completed before checking scenario");
		    			 break;
		    		    
		    		 }
		    		 catch(Exception ex){   
		    			 Thread.sleep(2000);
		    			 if(wait==10)
		    				 BasePageV2.reportFail("Download is not in progress or not completed");
		    		 }	
	    		 }
	    		 
	    	 }
		downloadspagev2.deleteDownloadTitle(audiostorytitle);
	
		
		  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}