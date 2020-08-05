package com.viacom.downloads;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
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

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class VerifyAudioBooksDownloadsKillAppInterruptionFunctionality extends BaseTestV2 {

	String testName = "VerifyAudioBooksDownloadsKillAppInterruptionFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify  Audio Books Downloads  Interruptions Functionality - Kill/Open App");
	test.log(LogStatus.INFO, "Starting the test for Kill/Open App during Audio Books Download in Progress Functionality - "+VootConstants.DEVICE_NAME);
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
    ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
    DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
	// homepagev2.logout();
	// homepagev2.login("audiodownloads@gmail.com", "vinoth@123");
	// downloadspagev2.setDeviceAudioDownloadQuality("high");
	 int err_count=0;
	 
	// downloadspagev2.deleteDownload();
	//Click on Listen tab

	String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	String audiostorytitle="";
	String downloadlink="";
	
	homepagev2.tabClick("Listen");
	Thread.sleep(2000);
	test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
	tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	Utilities.verticalSwipe(driver, tray);
	if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20)){
		if(Utilities.explicitWaitVisible(driver, listenpagev2.secondTrayAudioStory, 20)){
			if(Utilities.explicitWaitVisible(driver, listenpagev2.secondTrayAudioStoryTitle, 20)){
				 audiostorytitle=listenpagev2.secondTrayAudioStoryTitle.getText();
				 test.log(LogStatus.INFO, "Audio title: "+audiostorytitle);
			}
			else
				test.log(LogStatus.FAIL, "Audio story title is missing");
			try {
				listenpagev2.secondTrayAudioStory.click();
				test.log(LogStatus.INFO, "Clicked on second audio story");
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on second audio story");
			}
		}
		else
			BasePageV2.reportFail("No contents in the tray / Content not clickable");
	}
	else
		BasePageV2.reportFail("Failed to find the tray");
			 
			 
	test.log(LogStatus.INFO, "Navigating to the audio Detail page - "+audiostorytitle);
	downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	Utilities.verticalSwipe(driver, downloadlink);  
	if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20)){
		 Utilities.verticalSwipe(driver);
		 listenpagev2.downloadAudioBookLink.click();
		 test.log(LogStatus.INFO, "Downloading the Audio Book - "+audiostorytitle);
		 Thread.sleep(3000);
	}
	else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");

	 try{
		 driver.closeApp();
		 test.log(LogStatus.INFO, "Killed the App when downloading is in progress");
	 }
	 catch (Exception e){
		 test.log(LogStatus.INFO, "Failed to kill app when downloading is in progress");
	 }
	 try{
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));
		test.log(LogStatus.INFO,"Launched app again");
	 }
	 catch(Exception e){
		BasePageV2.reportFail("Failed to launch the App");
	 }
	 //Goto my stuff and navigate to downloads tab
	 Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	 test.log(LogStatus.INFO, "Scrolling to Downloads tab and verifying whether download is in progress"); 
	 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);
	 if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5)) {
		homepagev2.downloadsTab.click();
		test.log(LogStatus.INFO, "Clicked on Downloads tab");
	 }
	 else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
	 Thread.sleep(1000);
	 Utilities.verticalSwipe(driver);
	 Utilities.verticalSwipe(driver);
	 List<WebElement> inProgressDownloads=null;
	 List<WebElement> completedDownloads=null;
	 String xpathInProgress="//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'IN_PROGRESS')]//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']";
	 String xpathDownloadComplete="//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and contains(@content-desc,'DOWNLOAD_COMPLETE')]//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']";
	 String inProgressAudio="";
	 String downloadedAudio="";
	 //Search in Progress downloads
	 inProgressDownloads=driver.findElements(By.xpath(xpathInProgress));
	 boolean found=false;
	 for(int i=0;i<inProgressDownloads.size();i++) {
		 inProgressAudio=inProgressDownloads.get(i).getAttribute("text");
		 if(inProgressAudio.contains(audiostorytitle)){
			test.log(LogStatus.INFO, "Audio fetched from Downloads tab :"+downloadedAudio);
			test.log(LogStatus.INFO, "Content Download is in Progress");   
	    	test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Killing & Opening the app' is Passed");
		    BasePageV2.smokeresults("", killrow, "Pass");	
		    found=true;
		 }	
		 if(i==inProgressDownloads.size()) {
			test.log(LogStatus.INFO, "Download is not in progress");
		 }
	 }
	 //Search in completed downloads
	 if(found==false) {
		 completedDownloads=driver.findElements(By.xpath(xpathDownloadComplete));
	     for(int j=0;j<completedDownloads.size();j++) {
	        downloadedAudio=completedDownloads.get(j).getAttribute("text");
			if(downloadedAudio.contains(audiostorytitle)){
				test.log(LogStatus.INFO, "Audio fetched from Downloads tab :"+downloadedAudio);
				test.log(LogStatus.INFO, "Content Download is Completed");   
			    test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is resumed post Killing & Opening the app' is Passed");
				   BasePageV2.smokeresults("", killrow, "Pass");	
				   break;
			}
			if(j==completedDownloads.size()) {
				test.log(LogStatus.FAIL, "Download is neither in progress nor completed");
					
			}
	     }	
	 }
	       	
	listenpagev2.firstTray=null;
	listenpagev2.secondTrayAudioStory=null;
	listenpagev2.secondTrayAudioStoryTitle=null;
	homepagev2.mystuff_tab=null;
	homepagev2.downloadsTab=null;
	listenpagev2.downloadAudioBookLink=null;
		  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}