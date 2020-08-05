package com.viacom.downloads;

import java.util.Hashtable;



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

public class VerifyAudioBooksDownloadsFunctionalityTest extends BaseTestV2 {

	String testName = "VerifyAudioBooksDownloadsFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Audio Books Downloads Functionality");
	test.log(LogStatus.INFO, "Starting the test for Audio Books Downloads Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",cancelrow, "Verify the functionality when the audio book card download is in progress:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelDownloadrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",cancelDownloadrow, "Verify 'Cancel Download' option functionality from the download cancellation pop up:");	
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelbuttonrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",cancelbuttonrow, "Verify 'Cancel' option functionality from the download cancellation pop up:");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",downloadedrow, "Verify 'Download Audiobook' UI post completion of downloads:");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int mystuffdownloadsrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",mystuffdownloadsrow, "Verify Audio book content is displayed under downloads tabs post Download initiation");	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int linkrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",linkrow, "Verify Download icon functionality:");	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);
	 
	 //downloadspagev2.setDeviceAudioDownloadQuality("high");
	 //Click on Listen tab
	 HomePageV2.tabClick("Listen");
	 test.log(LogStatus.INFO, "Clicked on Listen tab");
	 //BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 Utilities.verticalSwipe(driver);
	 String audiostorytitle="";
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.firstTray, 20))
	 {
		 //String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
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
	 
	 
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadingProgress, 20))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
		 test.log(LogStatus.PASS, "Testcase : 'Verify Download icon functionality:' is Passed");
		 BasePageV2.smokeresults("", linkrow, "Pass");		 
		 Thread.sleep(3000);
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after tapping download link");
	 
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
	 {
		 listenpagev2.downloadAudioBookLink.click();
		 test.log(LogStatus.INFO, "Tapping on  Download link again when download is in progress");
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 Thread.sleep(3000);
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadOption, 20))
	 {
		 test.log(LogStatus.INFO, "Cancel Download Option is displayed");
		 test.log(LogStatus.PASS,"Testcase : 'Verify the functionality when the audio book card download is in progress:' is Passed");
		 BasePageV2.smokeresults("", cancelrow, "Pass");
		 test.log(LogStatus.INFO, "Tapping on Cancel Download Option");
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadOption, 20)) {
			 try {
				 listenpagev2.cancelDownloadOption.click(); 
				 test.log(LogStatus.INFO, "Clicked on Cancel");
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "Failed to click on Cancel");
			 }
		 }
		 
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.cancelDownloadPopup, 20))
		 {
			 test.log(LogStatus.INFO, "Delete Pop up is displayed");
			
			// if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadPopupNoButton, 5))
			 if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadPopupYesButton, 5))
			 {
				 
				 test.log(LogStatus.INFO, "Tapping on Yes button");
				// BasePageV2.takeScreenshot();
				 listenpagev2.cancelDownloadPopupYesButton.click();
			 }
			 else
			 BasePageV2.reportFail("Yes/Cancel Download button is not displayed in popup");
			 
			 
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadAudioBookText, 20))
			 {
				 test.log(LogStatus.INFO, "Downloading is cancelled and pop up is closed ");
				 test.log(LogStatus.PASS,"Testcase : 'Verify 'Cancel Download' option functionality from the download cancellation pop up:' is Passed");
			     BasePageV2.smokeresults("", cancelDownloadrow, "Pass");
			 }
			 else
				 BasePageV2.reportFail("Downloading is not cancelled after tapping Yes/cancelDownload button in popup");
			 
			 
		 }
		 else
			 BasePageV2.reportFail("Cancel/delete Popup is not displayed");
		 
		 
	 }
	 else
		 BasePageV2.reportFail("Not able to tap on Cancel Download option  / Cancel Download option not available");
	 
	 
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 30))
	 {
		 Thread.sleep(3000);
		 listenpagev2.downloadAudioBookLink.click();
		 test.log(LogStatus.INFO, "Tapping on  Download link again to download the audio book");
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadingProgress, 20))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
		 Thread.sleep(3000);
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after tapping download link");
	 
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.downloadAudioBookLink, 20))
	 {
		 listenpagev2.downloadAudioBookLink.click();
		 test.log(LogStatus.INFO, "Tapping on  Download link again when download is in progress");
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 
	 Thread.sleep(3000);
	 if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadOption, 20))
	 {
		 test.log(LogStatus.INFO, "Cancel Download Option is displayed");		 
		 test.log(LogStatus.INFO, "Tapping on  Cancel Download Option");
		 listenpagev2.cancelDownloadOption.click();
		 if(Utilities.explicitWaitVisible(driver, listenpagev2.cancelDownloadPopup, 20))
		 {
			 test.log(LogStatus.INFO, "Cancel/delete Pop up is displayed");			
			
			if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadPopupNoButton, 20))
			 //if(Utilities.explicitWaitClickable(driver, listenpagev2.cancelDownloadPopuCancelDialog, 5))
			 {
				 test.log(LogStatus.INFO, "Tapping on  No/Cancel button");
				// BasePageV2.takeScreenshot();
				 listenpagev2.cancelDownloadPopupNoButton.click();
			 }
			 else
			 BasePageV2.reportFail("Cancel/no button is not displayed in popup");
			 
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadingProgress, 20))
			 {
				 test.log(LogStatus.INFO, "Downloading is in Progress after tapping Cnacel/No button in popup ");
				 test.log(LogStatus.PASS,"Testcase : 'Verify 'Cancel' option functionality from the download cancellation pop up:' is Passed");
			     BasePageV2.smokeresults("", cancelbuttonrow, "Pass");
			 }
			 else if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioBookLink, 20))
			 {
				 test.log(LogStatus.INFO, "Download is complete");
				 test.log(LogStatus.PASS,"Testcase : 'Verify 'Cancel' option functionality from the download cancellation pop up:' is Passed");
			     BasePageV2.smokeresults("", cancelbuttonrow, "Pass");
			 }
			 else
				 BasePageV2.reportFail("Downloading is not in Progress after tapping Cnacel/No button in popup");
			
			 
		 }
		 else
			 BasePageV2.reportFail("Cancel/delete Popup is not displayed");
		 
		 
	 }
	 else
		 BasePageV2.reportFail("Not able to tap on Cancel Download option  / Cancel Download option not available");
	 
	 if(Utilities.explicitWaitVisible(driver, listenpagev2.downloadedAudioBookLink, 300))
	 {
		 test.log(LogStatus.INFO, "Download is complete");
		 test.log(LogStatus.PASS,"Testcase : 'Verify 'Download Audiobook' UI post completion of downloads:' is Passed");
	     BasePageV2.smokeresults("", downloadedrow, "Pass");
	 }
	 else
		 BasePageV2.reportFail("Downloading is not in Progress after tapping Cancel/No button in popup");
	 
	 
	 
	 //Goto my stuff and navigate to downloads tab
	 
	 driver.navigate().back();
	 Thread.sleep(1000);	 
	 Utilities.verticalSwipeReverse(driver);
	 Utilities.verticalSwipeReverse(driver);
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

		if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 30))
			homepagev2.downloadsTab.click();
		else
		BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
	    Utilities.verticalSwipe(driver, downloadspagev2.editDownloadsMystuff);	 
	    
		if(Utilities.explicitWaitVisible(driver, downloadspagev2.editDownloadsMystuff, 30))
		downloadspagev2.editDownloadsMystuff.click();
		else
		BasePageV2.reportFail("Edit Download Button not displayed");
	 
		if(Utilities.explicitWaitVisible(driver, downloadspagev2.editDownloadsScreen, 30))
			{
			  test.log(LogStatus.INFO, "Verifying whether Downloaded Audio book is displayed In Downloads Screen");
			  if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 30))
			  {
				for(int i=0;i<downloadspagev2.downloadedContentTitles.size();i++)
				{
					try{
						if(downloadspagev2.downloadedContentTitles.get(i).getText().contains(audiostorytitle))
					    test.log(LogStatus.PASS,"Testcase : 'Verify Audio book content is displayed under downloads tabs post Download initiation' is Passed");
						BasePageV2.smokeresults("", mystuffdownloadsrow, "Pass");
						break;
					 }
					catch(Exception e){}
					BasePageV2.reportFail("Downloaded Audio book is not displayed in Downloads screen");
				}
				
			    if(Utilities.explicitWaitClickable(driver, downloadspagev2.deleteDownloadButton, 30))
				 {
					 downloadspagev2.deleteDownload.click();
					 if(Utilities.explicitWaitClickable(driver, downloadspagev2.deletebutton, 30))
						 downloadspagev2.deletebutton.click();
					 else
						 test.log(LogStatus.FAIL, "Delete Button not displayed after clicking delete Download");
					 
					 if(Utilities.explicitWaitClickable(driver, downloadspagev2.ConfirmDeleteDownload, 30))
						 downloadspagev2.ConfirmDeleteDownload.click();
					 else
						 test.log(LogStatus.FAIL, "Delete Pop up not displayed after clicking delete Download");
				 }
				 else
				 test.log(LogStatus.FAIL, "Delete Button not displayed for the downloaded content");
				
			  }
	  
			}
			else
			BasePageV2.reportFail("Not naviagted to Edit Downloads screen");		  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}