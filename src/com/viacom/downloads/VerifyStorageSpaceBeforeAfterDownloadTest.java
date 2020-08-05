package com.viacom.downloads;

import java.util.Hashtable;
import java.util.LinkedList;
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
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyStorageSpaceBeforeAfterDownloadTest extends BaseTestV2 {

	String testName = "VerifyStorageSpaceBeforeAfterDownloadTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("VerifyStorageSpaceBeforeAfterDownloadTest");
	test.log(LogStatus.INFO, "Starting the test to verify Audio content Download quality  - "+VootConstants.DEVICE_NAME);
	// Check run mode
	
	//VK_959:Verify if Remaining space gets updated accordingly when each time user downloads a content  
    //vk_960:Verify if Remaining space gets Downgraded accordingly when each time user delets a downloaded content
	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
     ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	 String timebefPause="";	 
		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
		
	 homepagev2.tabClick("Listen");
	 downloadspagev2.setDeviceAudioDownloadQuality("medium");
     double remainingDataBefDownload=0, remainingDataAftDownload=0, remainingDataAftDelete=0;
     
	     test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
		 Utilities.verticalSwipe(driver,  listenpagev2.firstTray,"visible",3);
		 String audiostorytitle="", audiostorytitle2="";
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
		 {
			 Utilities.verticalSwipe(driver,  listenpagev2.firstTray,"clickable",3);
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTrayAudioStory, 20))
			 {			 
				 listenpagev2.firstTrayAudioStory.click();
			 }
			 else
				 BasePageV2.reportFail("No contents in the tray / Content not clickable");
		 }
		 else
			 BasePageV2.reportFail("Failed to find the tray");
		 

		 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName, 20))
		 {
			 audiostorytitle=listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName.getText().toLowerCase();
		 }
		 else
		 test.log(LogStatus.FAIL, "Audio content title is missing");	
		 
	    //String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	    Utilities.verticalSwipe(driver, listenpagev2.downloadAudioBookLink,"clickable",4);
	
	    if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
		 {
			 Utilities.verticalSwipe(driver);
			 listenpagev2.downloadAudioBookLink.click();
			 test.log(LogStatus.INFO, "Clicking on Download Icon in audio Detail page - "+audiostorytitle);
		 }
		 else
			 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+audiostorytitle);
		
	    driver.navigate().back();
	    Thread.sleep(1000);
	    
	     Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
	     homepagev2.tabClick("My Stuff");
	     
		 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
		 
		 Utilities.verticalSwipe(driver, homepagev2.downloadsTab,"clickable",15);
Thread.sleep(2000);
			if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
			homepagev2.downloadsTab.click();
			else
			BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
			Thread.sleep(2000);
			Utilities.verticalSwipe(driver, downloadspagev2.availableStorage,"visible",5);

			
		    if (Utilities.explicitWaitVisible(driver, downloadspagev2.availableStorage, 5))
		    {
		    	try{
		        String storage=downloadspagev2.availableStorage.getText();
		    	test.log(LogStatus.INFO, "Remaining space available before downloading is-"+(storage.split(" "))[0]);		  
		    	test.log(LogStatus.INFO, "Total space available is-"+(storage.split(" "))[2]);
		    	storage=(storage.split(" "))[0];
		    	remainingDataBefDownload=Double.parseDouble(storage.replace("GB", ""));
		    }
		    	catch(Exception e)
		    	{
		    		test.log(LogStatus.FAIL, "Something went wrong when getting storage space");
		    		BasePageV2.takeScreenshot();
		    	}
		    }
			else
			{
			  test.log(LogStatus.FAIL, "Available storage space is not displayed");
			}
			
		
	Utilities.verticalSwipeDown(driver, homepagev2.Listen_tab,"Clickable",20);
	Thread.sleep(2000);
	// medium size
	homepagev2.tabClick("Listen");

		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Navigating to any other audio content under tray");
		 Utilities.verticalSwipe(driver,  listenpagev2.firstTray,"visible",3);
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
		 {
			 Utilities.verticalSwipe(driver,  listenpagev2.firstTraySecondAudioStory,"clickable",2); 
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.firstTraySecondAudioStory, 20))
			 {
				 listenpagev2.firstTraySecondAudioStory.click();
			 }
			 else
				 BasePageV2.reportFail("No contents in the tray / Content not clickable");
		 }
		 else
			 BasePageV2.reportFail("Failed to find the tray");
		
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName, 20))
		 {
			 audiostorytitle2=listenpagev2.audioPlayerMorFrmAuthrAudioDetailsName.getText().toLowerCase();
		 }
		 else
		 test.log(LogStatus.FAIL, "Audio content title is missing");
		 
       
	    Utilities.verticalSwipe(driver, listenpagev2.downloadAudioBookLink,"clickable",5);

	    if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
		 {
			 listenpagev2.downloadAudioBookLink.click();
			 test.log(LogStatus.INFO, "Clicking on Download Icon in audio Detail page - "+audiostorytitle2);
		 }
		 else
			 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+audiostorytitle);
		
		

	    driver.navigate().back();
	    Thread.sleep(1000);
	     Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab,"clickable",20);
	     
	     homepagev2.tabClick("My Stuff");
	     Thread.sleep(2000);
		 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
		 
		 Utilities.verticalSwipe(driver, homepagev2.downloadsTab,"clickable",15);
           Thread.sleep(2000);
			if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
			homepagev2.downloadsTab.click();
			else
			BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
			 Thread.sleep(4000);
			 Utilities.verticalSwipe(driver,  downloadspagev2.availableStorage,"visible",15);

			Thread.sleep(30000);
			  if (Utilities.explicitWaitVisible(driver, downloadspagev2.availableStorage, 5))
			    {
			    	
			    	try{
			        String storage=downloadspagev2.availableStorage.getText();
			      
			    	test.log(LogStatus.INFO, "Remaining space available after download is-"+(storage.split(" "))[0]);		  
			    	test.log(LogStatus.INFO, "Total space available is-"+(storage.split(" "))[2]);
			    	  storage=(storage.split(" "))[0];
			        remainingDataAftDownload=Double.parseDouble(storage.replace("GB", ""));
			        if(remainingDataAftDownload<remainingDataBefDownload)
			        {
			        	test.log(LogStatus.INFO, "Remaining space gets updated accordingly after content downloaded");
			        	test.log(LogStatus.PASS, "Test case : 'Verify if Remaining space gets updated accordingly when each time user downloads a content' is Passed");
			        	if(!Utilities.setResultsKids("VK_959", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");



			        }
			        else
			        	{
			           	if(!Utilities.setResultsKids("VK_959", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        	test.log(LogStatus.FAIL, "Remaining space gets not updated accordingly after content downloaded");
			        	  BasePageV2.takeScreenshot();
			        	}
			    }
			    	catch(Exception e)
			    	{
			    		test.log(LogStatus.FAIL, "Something went wrong when getting storage space");
			    		BasePageV2.takeScreenshot();
			    	}
			    	
			    }
				else
				{
				  test.log(LogStatus.FAIL, "Available storage space is not displayed");
				}
			
			    if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
				downloadspagev2.editDownloadsMystuff.click();
				else
				BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
		    
			    test.log(LogStatus.INFO, "Deleting any of the downloaded content ");
				if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteDownload, 2))
				{
					downloadspagev2.deleteDownload.click();
					if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteTitle, 2))
						downloadspagev2.deleteTitle.click();
					else test.log(LogStatus.INFO, "'Delete CTA' not displayed");

					if (Utilities.explicitWaitVisible(driver, downloadspagev2.ConfirmDeleteDownload, 2))
						downloadspagev2.ConfirmDeleteDownload.click();
					else test.log(LogStatus.INFO, "'Confirmation Popup' to delete download not displayed");
				}
				else test.log(LogStatus.INFO, "Downloads is empty / Delete icon is not present");

				Thread.sleep(20000);
			    driver.navigate().back();
			    
				Thread.sleep(10000);
				 Utilities.verticalSwipe(driver,  downloadspagev2.availableStorage,"visible",15);
			    if (Utilities.explicitWaitVisible(driver, downloadspagev2.availableStorage, 5))
			    {
			    	
			    	try{
			        String storage=downloadspagev2.availableStorage.getText();			
			    	test.log(LogStatus.INFO, "Remaining space available after deleting downloaded content is-"+storage);		  
			    	test.log(LogStatus.INFO, "Total space available is-"+(storage.split(" "))[2]);
			        storage=(storage.split(" "))[0];
			        remainingDataAftDelete=Double.parseDouble(storage.replace("GB", ""));
			        if(remainingDataAftDelete>remainingDataAftDownload)
			        {
			        	test.log(LogStatus.INFO, "Remaining space gets updated accordingly after deleting downloaded content");
			        	test.log(LogStatus.PASS, "Test case : 'Verify if Remaining space gets Downgraded accordingly when each time user delets a downloaded content' is Passed");
			        	if(!Utilities.setResultsKids("VK_960", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        }
			        else
			        	{
			           	if(!Utilities.setResultsKids("VK_960", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			        	  test.log(LogStatus.FAIL, "Remaining space gets not updated accordingly after deleting downloaded content");
			        	  BasePageV2.takeScreenshot();
			        	}
			    }
			    	catch(Exception e)
			    	{
			    		test.log(LogStatus.FAIL, "Something went wrong when getting storage space");
			    		BasePageV2.takeScreenshot();
			    	}
			    	
			    	
			    }
				else
				{
				  test.log(LogStatus.FAIL, "Available storage space is not displayed");
				}
			    
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}