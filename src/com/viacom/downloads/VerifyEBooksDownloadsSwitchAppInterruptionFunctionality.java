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

public class VerifyEBooksDownloadsSwitchAppInterruptionFunctionality extends BaseTestV2 {

	String testName = "VerifyEBooksDownloadsSwitchAppInterruptionFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify  EBooks Downloads  Interruptions Functionality - Switch App");
	test.log(LogStatus.INFO, "Starting the test for Switching to other App during EBooks Download in Progress Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	

	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int switchrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",switchrow, "Verify if downloading content is not interrupted post switching to different app");	
	
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
	// BasePageV2.takeScreenshot();
	 Thread.sleep(2000);
	 test.log(LogStatus.INFO,"Scrolling to any of the Books Tray");
	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	 Utilities.verticalSwipe(driver, tray);
	 String ebookTitle="";
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
			 test.log(LogStatus.FAIL, "Audio story title is missing");
			 
			 bookspagev2.firstTrayEbook.click();
		 }
		 else
			 BasePageV2.reportFail("No contents in the tray / Content not clickable");
	 }
	 else
		 BasePageV2.reportFail("Failed to find the tray");
	 
	 
	 test.log(LogStatus.INFO, "Navigating to the audio Detail page - "+ebookTitle);
	 
	 
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
		//	 BasePageV2.takeScreenshot();
			 Thread.sleep(2000);
			 test.log(LogStatus.INFO,"Scrolling to any of the Books Tray");
			 tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
			 Utilities.verticalSwipe(driver, tray);
			ebookTitle="";
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
		 BasePageV2.reportFail("Not able to click on Download book / Download link not available");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 40))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
	 }
	 else
		 BasePageV2.reportFail("Book is not downloading after tapping download link");
	 
	 
	 
   test.log(LogStatus.INFO, "Switching to any of other App when downloading is in progress");
	 
	 try{
			driver.startActivity(new Activity("com.android.settings", "com.android.settings.Settings"));
		  driver.findElement(By.id(""));
		  System.out.println("found");
	 }
	 catch(Exception e){
		 
	 }
    test.log(LogStatus.INFO, "Switching back to Voot Kids App and verify whether download is in Progress");
 	 
    driver.pressKeyCode(AndroidKeyCode.HOME);
 	// KEYCODE_APP_SWITCH
 	driver.pressKeyCode(0x000000bb);
 	
 	List<WebElement> elem = driver.findElements(By.id("com.android.systemui:id/title"));
 	for (int i=0;i<elem.size()-1;i++){
 		System.out.println("elem.get(i).getAttribute(name) :: " + elem.get(i).getAttribute("text")) ;
 		if (elem.get(i).getAttribute("text").equals("VootKids")){
 			//System.out.println("Tapping :: " + elem.get(i).getAttribute("name")) ;
 			 elem.get(i).click();
 		
 			break;
 		}

 	}			
	 
 	if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 20))
	 {
		 test.log(LogStatus.INFO, "Downloading is in Progress ");
		 test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is not interrupted post switching to different app' is passed");
		 BasePageV2.smokeresults("", switchrow, "Pass");
		 
	 }
	 else if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 20))
	 {
		 test.log(LogStatus.INFO, "Download is completed");
		 test.log(LogStatus.PASS,"Testcase : 'Verify if downloading content is not interrupted post switching to different app' is passed");
		 BasePageV2.smokeresults("", switchrow, "Pass");
	 }
	 else
     {
		 BasePageV2.reportFail("Book is not downloading after switching to other App");
	 }
	 driver.navigate().back();
	downloadspagev2.deleteDownloadTitle(ebookTitle);
	
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}