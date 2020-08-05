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

public class VerifyAudioDownloadsProgressFunctionalityTest extends BaseTestV2 {

	String testName = "VerifyAudioDownloadsProgressFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Audio Books Downloads in Progress Functionality");
	test.log(LogStatus.INFO, "Starting the test for Audio Books Download in Progress Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",cancelrow, "Verify the functionality by tapping 'In Progress' status audio books under My Stuff - Downloads tab:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelDownloadrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",cancelDownloadrow, "Verify cancel Download button functionality in the pop-up:");	
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelbuttonrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",cancelbuttonrow, "Verify Cancel button functionality in the pop-up:");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int progressmetadatarow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",progressmetadatarow, "Verify the metadata of in-progress state cards under My Stuff - Downloads tab:");	
	
/*	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedmetadatarow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",downloadedmetadatarow, "Verify the metadata of completely downloaded cards under My Stuff - Downloads tab:");
	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedplaybackrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",downloadedplaybackrow, "Verify that Audio player launches when tapping completely downloaded Audio card from Downloads tab");*/
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
	 
	 downloadspagev2.setDeviceDownloadQuality("high");
	 int err_count=0;
	 
	// downloadspagev2.deleteDownload();
	 //Click on Listen tab
	 
	 if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10)) 
	 homepagev2.Listen_tab.click();	
	 else
	 BasePageV2.reportFail("Not able to click on Listen Tab");
	 test.log(LogStatus.INFO, "Clicked on Listen tab");
	 //BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 String audiostorytitle="";
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
	 
	 
	 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	 Utilities.verticalSwipe(driver, downloadlink);

	 if(!Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioBookLink, 5))
	 { 
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
	 
	 }
	 else
	 {
		 driver.navigate().back();
		 Thread.sleep(1000);
		 downloadspagev2.deleteDownload();
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
		 
	 }
			 
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

		if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
		homepagev2.downloadsTab.click();
		else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
		Thread.sleep(1000);

		test.log(LogStatus.INFO, "Checking meta data of downloading content under Downloads Segmented Tab");
	    Utilities.verticalSwipe(driver);
		if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 5))
		{
		  test.log(LogStatus.INFO, "Audio Book Title is displayed");
		  if(downloadspagev2.downloadedContentTitle.getText().equalsIgnoreCase(audiostorytitle))
			 test.log(LogStatus.INFO, "Audio Book Title is verified");
		 else
		 {
			 test.log(LogStatus.FAIL, "Downloading Audio title is not matching");
			 err_count++;
		 }
		  
		     if(Utilities.explicitWaitClickable(driver, downloadspagev2.downloadsImageThumbnail, 3))
		     {
		    	 test.log(LogStatus.INFO, "Downloading Audio Thumbnail is displayed");
		    
		     }
		     else
		     {
		    	 test.log(LogStatus.FAIL, "Downloading Audio Thumbnail is not displayed");
		    	 err_count++;
		     }
		     
		     if(Utilities.explicitWaitClickable(driver, downloadspagev2.downloadsImageSize, 3))
		    	{
		    	 if(!downloadspagev2.downloadsImageSize.getText().isEmpty())
		    		 test.log(LogStatus.INFO, "Downloading Audio Size is displayed - "+downloadspagev2.downloadsImageSize.getText());
		    	}
		     else
		     {
		    	 test.log(LogStatus.FAIL, "Downloading Audio Size is not displayed");
		    	 err_count++;
		     }
		  
	     if(Utilities.explicitWaitClickable(driver, downloadspagev2.inprogress, 5))
	     {
	    	 test.log(LogStatus.INFO, "Progress bar filling outside the Square is displayed");
	    	 if(err_count==0)
	    	 {
	    		 test.log(LogStatus.PASS,"Testcase : 'Verify the metadata of in-progress state cards under My Stuff - Downloads tab:' is Passed");
	    	     BasePageV2.smokeresults("", progressmetadatarow, "Pass");
	    	 }
	    	 downloadspagev2.inprogress.click();
	     }
	    else if(Utilities.explicitWaitClickable(driver, downloadspagev2.contenttoDownloadandDownloaded, 5))
	    	{
	    	   test.log(LogStatus.INFO, "Download is complete before checking Scenario");
	    	   BasePageV2.reportFail(" Download is complete - Temporary failure ");
	    	}
	    else
	        BasePageV2.reportFail("Progress bar filling outside the Square is not displayed");
	     
           
	     
	     
		}
		else
		{
			BasePageV2.reportFail("Content title which is downloading is not displayed under Downloads-Segmented Tab in My Stuff");
		}
		

	 
	 
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadOption, 20))
		 {
			 test.log(LogStatus.INFO, "Cancel Download Option is displayed");		 
			 test.log(LogStatus.PASS,"Testcase : 'Verify the functionality by tapping 'In Progress' status audio books under My Stuff - Downloads tab:' is Passed");
			 BasePageV2.smokeresults("", cancelrow, "Pass");
			 test.log(LogStatus.INFO, "Tapping on  Cancel Download Option");
			 try{
				 listenpagev2.cancelDownloadOption.click();
			 }
			 catch(Exception e){
				 BasePageV2.reportFail("Dowbload is completed before checking scenario");
			 }
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.cancelDownloadPopup, 20))
			 {
				 test.log(LogStatus.INFO, "Cancel/delete Pop up is displayed");			
				
				 if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadPopupNoButton, 5))
				 {
					 test.log(LogStatus.INFO, "Tapping on  No/Cancel button");
					// BasePageV2.takeScreenshot();
					 listenpagev2.cancelDownloadPopupNoButton.click();
				 }
				 else
				 BasePageV2.reportFail("Cancel/no button is not displayed in popup");
				 
				       if(Utilities.explicitWaitClickable(driver, downloadspagev2.inprogress, 5))
				       {
					  	  test.log(LogStatus.PASS,"Testcase : 'Verify Cancel button functionality in the pop-up:' is Passed");
					  	  BasePageV2.smokeresults("", cancelbuttonrow, "Pass");
					  	  try{
					  		  downloadspagev2.inprogress.click();
					  	  }
					  	  catch(Exception e)
					  	  {
					  		  BasePageV2.reportFail("Audio is Downloaded Before checking Scenario");
					  		  
					  		  //check downloaded metadata
					  		  
					  		  
					  		  
					  	  }
				        }
					    else if(Utilities.explicitWaitClickable(driver, downloadspagev2.contenttoDownloadandDownloaded, 5))
					    	{
					    	    test.log(LogStatus.INFO, "Download is complete");
							  	test.log(LogStatus.PASS,"Testcase : 'Verify Cancel button functionality in the pop-up:' is Passed");
					    	}
					    else
					        BasePageV2.reportFail("Not able to find Content downloading in Progress state");
				
			 }
			 else
				 BasePageV2.reportFail("Cancel/delete Popup is not displayed");
			 
			 
		 }
		    else if(Utilities.explicitWaitClickable(driver, downloadspagev2.contenttoDownloadandDownloaded, 5))
	    	{
	    	    test.log(LogStatus.INFO, "Download is complete");
			  	BasePageV2.reportFail("Download is completed before checking scenario");
	    	}
		 else
			 BasePageV2.reportFail("Not able to tap on Cancel Download option  / Cancel Download option not available");
		 
		if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadOption, 20))
		 {
			 test.log(LogStatus.INFO, "Cancel Download Option is displayed");
			
			 test.log(LogStatus.INFO, "Tapping on  Cancel Download Option");
			 listenpagev2.cancelDownloadOption.click();
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.cancelDownloadPopup, 20))
			 {
				 test.log(LogStatus.INFO, "Cancel/delete Pop up is displayed");
				
				
				 if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadPopupYesButton, 5))
				 {
					 test.log(LogStatus.INFO, "Tapping on  Yes/Cancel Download button");
					// BasePageV2.takeScreenshot();
					 listenpagev2.cancelDownloadPopupYesButton.click();
				 }
				 else
				 BasePageV2.reportFail("Yes/Cancel Download button is not displayed in popup");
				 
				 
				 if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 5))
				 {
					 if(!downloadspagev2.downloadedContentTitle.getText().equals(audiostorytitle))
					 {
						 test.log(LogStatus.INFO, "Downloading is cancelled and pop up is closed ");
						 test.log(LogStatus.PASS,"Testcase : 'Verify Cancel Download button functionality in the pop-up:' is Passed");
				         BasePageV2.smokeresults("", cancelDownloadrow, "Pass");
					 }
					 else if(Utilities.explicitWaitVisible(driver, downloadspagev2.contenttoDownloadandDownloaded, 5))
					 {
						 test.log(LogStatus.INFO, "Content is downloaded before checking the Cancel Download button Functionality");
						 
						 // Downoloaded content metadata check 
						 
						 
						 test.log(LogStatus.FAIL," Download is complete - Temporary failure ");
						 
					}
					 else
						 BasePageV2.reportFail("Downloading is not cancelled after tapping Yes/cancelDownload button in popup");	 
				 }
				 else
				 {
					 test.log(LogStatus.INFO, "Downloading is cancelled and pop up is closed");
					 test.log(LogStatus.PASS,"Testcase : 'Verify Cancel Download button functionality in the pop-up:' is Passed");
			         BasePageV2.smokeresults("", cancelDownloadrow, "Pass");	 
				 }
				 
				  
			 }
			 else
				 BasePageV2.reportFail("Cancel/delete Popup is not displayed");
			 
			 
		 }
		 else if(Utilities.explicitWaitVisible(driver, downloadspagev2.contenttoDownloadandDownloaded, 5))
		 {
			 test.log(LogStatus.INFO, "Content is downloaded before checking the Cancel Download button Functionality");
			 
			 // Downoloaded content metadata check 
			 
			 
			 test.log(LogStatus.FAIL," Download is complete - Temporary failure ");
			 
		}
		 else
			 test.log(LogStatus.FAIL,"Not able to tap on Cancel Download option  / Cancel Download option not available");
	 
		
		downloadspagev2.deleteDownloadTitle(audiostorytitle);

		
		
		
		
		
		  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}