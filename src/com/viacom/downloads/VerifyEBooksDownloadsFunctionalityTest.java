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

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyEBooksDownloadsFunctionalityTest extends BaseTestV2 {

	String testName = "VerifyEBooksDownloadsFunctionalityTest";
	@Test(dataProvider = "getData")
	public void downloadTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify E-Books Downloads Functionality");
	test.log(LogStatus.INFO, "Starting the test for E-Books Downloads Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",cancelrow, "Verify the functionality when user taps on the Ebook card while download is in progress:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelDownloadrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",cancelDownloadrow, "Verify 'Cancel Download' option functionality from the download cancellation pop up:");	
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelbuttonrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",cancelbuttonrow, "Verify 'Cancel' option functionality from the download cancellation pop up:");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",downloadedrow, "Verify 'Download Ebook' UI post completion of downloads:");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int mystuffdownloadsrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",mystuffdownloadsrow, "Verify E-book content is displayed under downloads tabs post Download initiation");	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int ctarow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",ctarow, "Verify the CTA(Preview button) present on the Ebook detail screen post downloading the book");	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
   
     BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
    // homepagev2.logout();
	// homepagev2.login("booksdownloads@gmail.com", "vinoth@123");
	 downloadspagev2.setDeviceDownloadQuality("high");
	 //Click on Listen tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) 
	 homepagev2.read_tab.click();	
	 else
		 BasePageV2.reportFail("Not able to click on Read Tab");
	 test.log(LogStatus.INFO, "Clicked on Read tab");
	// BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the E-books Tray");
	 
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 String ebooktitle="";
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTray, 20))
	 {
		 String ebook="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
		 Utilities.verticalSwipe(driver, ebook);
		 if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTrayEbook, 20))
		 {
			 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTrayEbookTitle, 20))
			 {
				 ebooktitle=bookspagev2.firstTrayEbookTitle.getText();
			 }
			 else
			 test.log(LogStatus.FAIL, "E-book title is missing");
			 
			 bookspagev2.firstTrayEbook.click();
		 }
		 else
			 BasePageV2.reportFail("No contents in the tray / Content not clickable");
	 }
	 else
		 BasePageV2.reportFail("Failed to find the tray");
	 
	 
	 test.log(LogStatus.INFO, "Navigating to the Book Detail page - "+ebooktitle);
	 
	 
	 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	 Utilities.verticalSwipe(driver, downloadlink);
	 
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
	 {
		 Utilities.verticalSwipe(driver);
		 bookspagev2.downloadBookLink.click();
		 test.log(LogStatus.INFO, "Downloading the Book - "+ebooktitle);
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 20))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
		 
		 test.log(LogStatus.PASS, "Testcase : 'Verify Download Book link functionality' is passed");
		 
		 Thread.sleep(3000);
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after tapping download link");
	 
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
	 {
		 bookspagev2.downloadBookLink.click();
		 test.log(LogStatus.INFO, "Tapping on  Download link again when download is in progress");
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 Thread.sleep(3000);
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.cancelDownloadOption, 20))
	 {
		 test.log(LogStatus.INFO, "Cancel Download Option is displayed");
		 test.log(LogStatus.PASS,"Testcase : 'Verify the functionality when user taps on the Ebook card while download is in progress:' is Passed");
		 BasePageV2.smokeresults("", cancelrow, "Pass");
		 test.log(LogStatus.INFO, "Tapping on  Cancel Download Option");
		 bookspagev2.cancelDownloadOption.click();
		 if(Utilities.explicitWaitVisible(driver, bookspagev2.cancelDownloadPopup, 20))
		 {
			 test.log(LogStatus.INFO, "Cancel/delete Pop up is displayed");
			
			// if(Utilities.explicitWaitClickable(driver, bookspagev2.cancelDownloadPopupNoButton, 5))
			 if(Utilities.explicitWaitClickable(driver, bookspagev2.cancelDownloadPopupYesButton, 5))
			 {
				 
				 test.log(LogStatus.INFO, "Tapping on  Yes/Cancel Download button");
				// BasePageV2.takeScreenshot();
				 bookspagev2.cancelDownloadPopupYesButton.click();
			 }
			 else
			 BasePageV2.reportFail("Yes/Cancel Download button is not displayed in popup");
			 
			 
			 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadBookText, 20))
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
	 
	 
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
	 {
		 Thread.sleep(3000);
		 bookspagev2.downloadBookLink.click();
		 test.log(LogStatus.INFO, "Tapping on  Download link again to download the audio book");
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 20))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
		 Thread.sleep(3000);
	 }
	 else
		 BasePageV2.reportFail("Audio book is not downloading after tapping download link");
	 
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
	 {
		 bookspagev2.downloadBookLink.click();
		 test.log(LogStatus.INFO, "Tapping on  Download link again when download is in progress");
	 }
	 else
		 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available");
	 
	 Thread.sleep(3000);
	 if(Utilities.explicitWaitClickable(driver, bookspagev2.cancelDownloadOption, 20))
	 {
		 test.log(LogStatus.INFO, "Cancel Download Option is displayed");		 
		 test.log(LogStatus.INFO, "Tapping on  Cancel Download Option");
		 bookspagev2.cancelDownloadOption.click();
		 if(Utilities.explicitWaitVisible(driver, bookspagev2.cancelDownloadPopup, 20))
		 {
			 test.log(LogStatus.INFO, "Cancel/delete Pop up is displayed");			
			
			if(Utilities.explicitWaitClickable(driver, bookspagev2.cancelDownloadPopupNoButton, 5))
			 //if(Utilities.explicitWaitClickable(driver, bookspagev2.cancelDownloadPopuCancelDialog, 5))
			 {
				 test.log(LogStatus.INFO, "Tapping on  No/Cancel button");
				// BasePageV2.takeScreenshot();
				 bookspagev2.cancelDownloadPopupNoButton.click();
			 }
			 else
			 BasePageV2.reportFail("Cancel/no button is not displayed in popup");
			 
			 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 20))
			 {
				 test.log(LogStatus.INFO, "Downloading is in Progress after tapping Cnacel/No button in popup ");
				 test.log(LogStatus.PASS,"Testcase : 'Verify 'Cancel' option functionality from the download cancellation pop up:' is Passed");
			     BasePageV2.smokeresults("", cancelbuttonrow, "Pass");
			 }
			 else if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 20))
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
	 
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 300))
	 {
		 test.log(LogStatus.INFO, "Download is complete");
		 test.log(LogStatus.PASS,"Testcase : 'Verify 'Download Ebook' UI post completion of downloads:' is Passed");
	     BasePageV2.smokeresults("", downloadedrow, "Pass");
	     
	     Utilities.verticalSwipeDown(driver, bookspagev2.bookDetailPageBookCoverImage);
		 test.log(LogStatus.INFO, "Verifying whether 'Preview' is changed to 'Read' after book is downloaded");
		 if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookReadButton, 20))
		 {
			 test.log(LogStatus.INFO, "'Preview' is changed to 'Read' after book is downloaded");
			 test.log(LogStatus.PASS,"Testcase : 'Verify the CTA(Preview button) present on the Ebook detail screen post downloading the book' is Passed");
		     BasePageV2.smokeresults("", ctarow, "Pass");
		 }
		 else if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookPreviewButton, 20))
			 BasePageV2.reportFail("'Preview' is not changed to 'Read' after book is downloaded");
		 else
			 BasePageV2.reportFail("Preview/Read Button is not displayed");
	 }
	 else
		 BasePageV2.reportFail("Downloading is not in Progress after tapping Cancel/No button in popup");
	 
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
	    Utilities.verticalSwipe(driver, downloadspagev2.editDownloadsMystuff);	 
	    
		if(Utilities.explicitWaitVisible(driver, downloadspagev2.editDownloadsMystuff, 5))
		downloadspagev2.editDownloadsMystuff.click();
		else
		BasePageV2.reportFail("Edit Download Button not displayed");
	 
		if(Utilities.explicitWaitVisible(driver, downloadspagev2.editDownloadsScreen, 5))
			{
			  test.log(LogStatus.INFO, "Verifying whether Downloaded Audio book is displayed In Downloads Screen");
			  if(Utilities.explicitWaitVisible(driver, downloadspagev2.downloadedContentTitle, 5))
			  {
				for(int i=0;i<downloadspagev2.downloadedContentTitles.size();i++)
				{
					try{
						if(downloadspagev2.downloadedContentTitles.get(i).getText().contains(ebooktitle))
					    test.log(LogStatus.PASS,"Testcase : 'Verify E-book content is displayed under downloads tabs post Download initiation' is Passed");
						BasePageV2.smokeresults("", mystuffdownloadsrow, "Pass");
						break;
					 }
					catch(Exception e){
					BasePageV2.reportFail("Downloaded Audio book is not displayed in Downloads screen");
				}
				
			    if(Utilities.explicitWaitClickable(driver, downloadspagev2.deleteDownloadButton, 5))
				 {
					 downloadspagev2.deleteDownload.click();
					 if(Utilities.explicitWaitClickable(driver, downloadspagev2.deletebutton, 5))
						 downloadspagev2.deletebutton.click();
					 else
						 test.log(LogStatus.FAIL, "Delete Button not displayed after clicking delete Download");
					 
					 if(Utilities.explicitWaitClickable(driver, downloadspagev2.ConfirmDeleteDownload, 5))
						 downloadspagev2.ConfirmDeleteDownload.click();
					 else
						 test.log(LogStatus.FAIL, "Delete Pop up not displayed after clicking delete Download");
				 }
				 else
				 test.log(LogStatus.FAIL, "Delete Button not displayed for the downloaded content");
				
			  
			  
			 
			  
			  
			}
		
			}
			}
		else
			BasePageV2.reportFail("Not navigated to Edit Downloads screen");
	    driver.navigate().back();
	     
	
	 		  
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}