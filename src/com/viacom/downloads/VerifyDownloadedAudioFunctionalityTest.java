package com.viacom.downloads;

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

public class VerifyDownloadedAudioFunctionalityTest extends BaseTestV2 {

	String testName = "VerifyDownloadedAudioFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
	throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Downloaded Audio Books Functionality");
	test.log(LogStatus.INFO, "Starting the test for Downloaded Audio Books Functionality - "+VootConstants.DEVICE_NAME);
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
	xls2.setCellData("Smoke_Results", "Testcase Name",downloadedPlaybackrow, "Verify that Audio player launches when tapping completely downloaded Audio card from Downloads tab");	
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedlinkrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",downloadedlinkrow, "Verify Download icon UI post complete download:");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrowrowonecontent=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",uparrowrowonecontent, "Verify the availablity of Uparrow option if there is only 1 Audio book content in downloads tab");	

	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
	 
	 //downloadspagev2.setDeviceAudioDownloadQuality("low");
	 int err_count=0;
	 //Click on Listen tab
	 homepagev2.tabClick("Listen");
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 String audiostorytitle="";
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20)){
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTrayAudioStory, 20)){
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTrayAudioStoryTitle, 20)){
				 audiostorytitle=listenpagev2.firstTrayAudioStoryTitle.getText();
				 test.log(LogStatus.INFO, "Audio title: "+audiostorytitle);
			 }
			 else
			 test.log(LogStatus.FAIL, "Audio story title is missing"); 
			 try{
				listenpagev2.firstTrayAudioStory.click();
				test.log(LogStatus.INFO, "Clicked on first audio story");
			 }
			 catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on first audio story");
			 }
		 }
		 else
			 BasePageV2.reportFail("No contents in the tray / Content not clickable");
	 }
	 else
		 BasePageV2.reportFail("Failed to find the tray");

	 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	 Utilities.verticalSwipe(driver, downloadlink);
	  
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
	 {
		 Utilities.verticalSwipe(driver);
		 listenpagev2.downloadAudioBookLink.click();
		 test.log(LogStatus.INFO, "Downloading the Audio Book - "+audiostorytitle);
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 	 
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioBookLink, 500))
	 {
		 test.log(LogStatus.INFO, "Download is completed ");
		 test.log(LogStatus.PASS, "Testcase : 'Verify Download icon UI post complete download:' is Passed");
		 BasePageV2.smokeresults("", downloadedlinkrow, "Pass");
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after tapping download link");
	 //Goto my stuff and navigate to downloads tab
	 
	 driver.navigate().back();
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
	 Utilities.verticalSwipe(driver);

		if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
			homepagev2.downloadsTab.click();
		else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
		Utilities.verticalSwipe(driver);
		test.log(LogStatus.INFO, "Checking meta data of downloaded content under Downloads Segmented Tab");
		if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 5)){
			test.log(LogStatus.INFO, "Audio Book Title is displayed");
			String title=downloadspagev2.downloadedContentTitle.getText();
			test.log(LogStatus.INFO, "title "+title);
			test.log(LogStatus.INFO, "audiostorytitle "+audiostorytitle);
			if(title.equalsIgnoreCase(audiostorytitle))
				test.log(LogStatus.INFO, "Audio Book Title is verified");
			else{
				test.log(LogStatus.FAIL, "Downloaded Audio title is not matching");
				err_count++;
			}
		    if(Utilities.explicitWaitClickable(driver, downloadspagev2.downloadsImageThumbnail, 3)){
		    	test.log(LogStatus.INFO, "Downloading Audio Thumbnail is displayed");
		    }
		    else{
		    	test.log(LogStatus.FAIL, "Downloading Audio Thumbnail is not displayed");
		    	err_count++;
		    }
		    if(Utilities.explicitWaitClickable(driver, downloadspagev2.downloadsImageSize, 3)){
		    	if(!downloadspagev2.downloadsImageSize.getText().isEmpty())
		    		 test.log(LogStatus.INFO, "Downloading Audio Size is displayed - "+downloadspagev2.downloadsImageSize.getText());
		    }
		    else{
		    	 test.log(LogStatus.FAIL, "Downloading Audio Size is not displayed");
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
	    	test.log(LogStatus.INFO, "Playing the downloaded Audio");
	    	downloadspagev2.contenttoDownloadandDownloaded.click();
	    }
	    else {
	    	test.log(LogStatus.INFO, "Play icon is not displayed");
	    	BasePageV2.takeScreenshot();
	    }
	    	
	     if(Utilities.explicitWaitVisible(driver, playerpagev2.audioPlayerTitle, 60)){
	    	 test.log(LogStatus.INFO, "Audio title is displayed in the audio player");
	    	 if(playerpagev2.audioPlayerTitle.getText().equalsIgnoreCase(audiostorytitle)){
	    		 test.log(LogStatus.PASS,"Testcase : 'Verify that Audio player launches when tapping completely downloaded Audio card from Downloads tab' is displayed");
	    		 BasePageV2.smokeresults("", downloadedPlaybackrow, "Pass");
	    	 }
	    	 else {
	    		 test.log(LogStatus.INFO, "Audio player title in the Player not matching with downloaded audio title");
	    	 	 BasePageV2.takeScreenshot();
	    	 }
	    		 
	     }
	     else
	       test.log(LogStatus.FAIL, "Audio title is not displayed in the player");
	     
	     //Check for up arrow
   	  	if(Utilities.explicitWaitVisible(driver, playerpagev2.playerBottomUpArrowButton, 60)){
   	  		test.log(LogStatus.FAIL, "Uparrow button is displayed even though there is only 1 Audio book content in downloads tab");
   	  		BasePageV2.takeScreenshot();
   	  	}
   	  	else
   	  	{	 	
 	    	test.log(LogStatus.INFO, "Uparrow button is not displayed when there is only 1 Audio book content in downloads tab");
 	    	test.log(LogStatus.PASS,"Testcase : 'Verify the availablity of Uparrow option if there is only 1 Audio book content in downloads tab' is Passed");
 	    	BasePageV2.smokeresults("", uparrowrowonecontent, "Pass");
   	  	}
	}
				  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}