package com.viacom.ebook;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.connection.ConnectionState;
//Author Tanisha
public class VerifyReaderPreviewAndRead extends BaseTestV2{
	
	String testName = "VerifyReaderPreviewAndRead";
	@Test(dataProvider = "getData")
	public void VerifyReaderPreviewAndRead(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyReaderPreviewAndRead");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		// VK_1136 Verify the Playback for the downloaded Ebook content in Offfline mode
		// VK_423 Verify the Preview button functionality in Book detail page 
		// VK_645 Verify the functionality of Close(X) icon in Reader player
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver, test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver, test);
		 DownloadsPageV2 downloadpagev2=new DownloadsPageV2(driver,test);
		//Login module 
		//homepagev2.logout();
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 int err1113=0;
		 int err1003=0;
		 int err1004=0;
		 int err1005=0;
		 int err1006=0;
		 downloadsv2.deleteAllDownloads();
		 String bookNameBefore="";
//Verification of 424
     	 homepagev2.tabClick("Read");
		 Thread.sleep(2000);
		 if(Utilities.explicitWaitClickable(driver, readpagev2.firstBookInCarousalDesc, 10)) {
			 try {
				 bookNameBefore=readpagev2.firstBookInCarousalDesc.getAttribute("text");
				 readpagev2.firstBookInCarousalDesc.click();
				 test.log(LogStatus.INFO, "Clicked on first book in Read Carousal "+bookNameBefore);
			 }
			 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Unable to click on the first item in Tray");
			 }	
		 }
		 for(int wait=0; wait<=10;wait++) {
			 try {
				 readpagev2.previewButton.click();
				 test.log(LogStatus.INFO, "Clicked on PREVIEW button");
				 readpagev2.dismissReaderCoachCards();
				 break;
			 }
			 catch(Exception e) {
				 Thread.sleep(2000);
				 if(wait==10) {
					 test.log(LogStatus.FAIL, "Unable to click on PREVIEW button");
				 }
			 }
		 }
		 for(int i=0;i<=1;i++) {
			 try {
					 if(readpagev2.bookReader.isDisplayed()) {
						 test.log(LogStatus.INFO, "Book Reader is displayed Page 1");
						 homepagev2.takeScreenshot();
						 Utilities.horizontalSwipe(driver);
						 test.log(LogStatus.INFO, "Book Reader is displayed Page 2");
						 homepagev2.takeScreenshot();
						 test.log(LogStatus.PASS,"Verify the Preview button functionality in Book detail page is Pass");
						 if(!Utilities.setResultsKids("VK_423", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 driver.navigate().back();
						 test.log(LogStatus.INFO, "Navigated back to Book details page");
						 break; 
					 }		
					 else {
						 Thread.sleep(3000);
						 if(i==1) {
							 test.log(LogStatus.FAIL, "Book Reader is not displayed");
							 test.log(LogStatus.FAIL, "Verify the Preview button functionality in Book detail page is Fail");
							 if(!Utilities.setResultsKids("VK_423", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 homepagev2.takeScreenshot();
						 }
					 }
			 }
			 catch(Exception e) {
					 Thread.sleep(3000);
					 if(i==1) {
						 test.log(LogStatus.FAIL, "Book Reader is not displayed");
						 test.log(LogStatus.FAIL, "Verify the Preview button functionality in Book detail page is Fail");
						 if(!Utilities.setResultsKids("VK_423", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 homepagev2.takeScreenshot();
					 }
			 }
		 }
		 boolean downloadComplete=false;
		 String downloadBookLoc="//android.widget.TextView[@text='Download Book' or @text='Download book']";
		 boolean downloadBookText=false;
		 for(int scroll=0;scroll<=3;scroll++) {
			 try {
				 driver.findElement(By.xpath(downloadBookLoc));
				 downloadBookText=true;
			 }
			 catch(Exception e) {
				 Utilities.verticalSwipe(driver);
				 if(scroll==3) {
					 test.log(LogStatus.FAIL, "Failed to locate Download book link");
					 homepagev2.takeScreenshot();
				 }
			 }
		 }
		 if(downloadBookText==true) {
			test.log(LogStatus.INFO, "Scrolled to \"Download Book\" text in Book details page");
			if(Utilities.explicitWaitClickable(driver, readpagev2.downloadBookText, 10)) {
				readpagev2.downloadBookText.click();
				test.log(LogStatus.INFO, "Clicked on Download Book");
				for(int wait=0;wait<=20;wait++) {
					if(Utilities.explicitWaitVisible(driver, readpagev2.downloadedBookText, 5)) {
						test.log(LogStatus.INFO, "Download completed ..");
						downloadComplete=true;
						if(Utilities.explicitWaitClickable(driver, readpagev2.readButton, 10)) {
							readpagev2.readButton.click();
							test.log(LogStatus.INFO, "Tapped on READ button in Book Details page");
							//Set<String> CHS = driver.getContextHandles();
							//for(String ch:CHS){								
								//if(ch.contains("WEBVIEW")){
									test.log(LogStatus.INFO, "eBook Reader is displayed");	
									if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderClose, 10)) {
										try {	
											readpagev2.bookReaderClose.click();
											test.log(LogStatus.INFO, "Tapped on Close button of eBook Reader");
											if(Utilities.explicitWaitVisible(driver, readpagev2.readButton, 10)) {
												test.log(LogStatus.INFO, "Read button of Book Details page is displayed, Book Reader has Closed");
												test.log(LogStatus.PASS, "Verify the functionality of Close(X) icon in Reader player is Pass");	
												if(!Utilities.setResultsKids("VK_646", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											}
											else {
												test.log(LogStatus.INFO, "Read button of Book Details page is not displayed, Book Reader has not Closed");
												test.log(LogStatus.FAIL,"Verify the functionality of Close(X) icon in Reader player is Fail");
												if(!Utilities.setResultsKids("VK_646", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
												homepagev2.takeScreenshot();
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Unable to click on Close hutton");
											test.log(LogStatus.FAIL,"Verify the functionality of Close(X) icon in Reader player is Fail");
											if(!Utilities.setResultsKids("VK_646", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											homepagev2.takeScreenshot();
										}
									}
									else {
										test.log(LogStatus.FAIL,"Close button is not clickable");
										test.log(LogStatus.FAIL,"Verify the functionality of Close(X) icon in Reader player is Fail");
										if(!Utilities.setResultsKids("VK_646", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										homepagev2.takeScreenshot();
										Thread.sleep(3000);
										driver.navigate().back();
									}
									break;
								//}
							//}  
						}
						else 
							test.log(LogStatus.FAIL, "Read button is not clickable");
						break;
					}
					else {
						Thread.sleep(3000);
						if(wait==15) {
							test.log(LogStatus.FAIL, "Book failed to download completely");	 
						}
					}			
				}
				
				if(downloadComplete==true) {
					//Go to My Stuff tab
					driver.navigate().back();
					Thread.sleep(2000);
					test.log(LogStatus.INFO, "Navigated back to Read tab");
					homepagev2.tabClick("My Stuff");
					for(int i=0;i<=10;i++) {
						Utilities.verticalSwipeReverse(driver);
					}
					//Switch off the network
					try {
						driver.setConnection(new ConnectionState(0));
						test.log(LogStatus.INFO, "Turned OFF Wi-Fi connection");
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Unable to turn OFF Wi-Fi connection");
					}
					//Go to Downloads tab
					boolean tabPresence=false;
					for (int i = 0; i <= 20; i++)
					{
						if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 1))
						{
							downloadpagev2.downloadsTabMystuffpage.click();
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipe(driver);
							tabPresence=true;
							break;
						}
						else 
							Utilities.verticalSwipe(driver);
					}
					if(tabPresence==true) {
						 Utilities.verticalSwipe(driver);
						 //Verify if the book is present based on the name
						 bookNameBefore=bookNameBefore.split("'")[0];
						 String bookNameAfterLoc="//android.widget.TextView[contains(@text,\""+bookNameBefore+"\")]//ancestor::android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']";
						 for(int tryfinding=0;tryfinding<=2;tryfinding++) {
							try {
								driver.findElement(By.xpath(bookNameAfterLoc)).click();
								test.log(LogStatus.INFO, "Clicked on the book in Downloads tab");	
								Set<String> CHS = driver.getContextHandles();
								for(String ch:CHS){								
									if(ch.contains("WEBVIEW")){
										if(Utilities.explicitWaitVisible(driver, readpagev2.bookReaderClose, 10)) {
											test.log(LogStatus.INFO, "eBook Reader is displayed with Close button");
											test.log(LogStatus.PASS, "Verify the Playback for the downloaded Ebook content in Offline mode is Pass");
											if(!Utilities.setResultsKids("VK_1136", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										}
										else {
											test.log(LogStatus.FAIL, "Verify the Playback for the downloaded Ebook content in Offline mode is Fail");
											if(!Utilities.setResultsKids("VK_1136", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
											homepagev2.takeScreenshot();
										}
									}
								}
								break;
							}
							catch(Exception e) {
								if(tryfinding==2) {
									test.log(LogStatus.FAIL, "Unable to find the Book in Downloads tab");
									break;
								}
							}	
						}
					}
					else 
						test.log(LogStatus.FAIL, "Failed to locate Downloads tab");	
				}
			}
			else 
				test.log(LogStatus.FAIL, "\"Download Book\" text in Book Details page is not clickable");				
		 }
		 else 
			 test.log(LogStatus.FAIL, "Unable to locate \"Download Book\" text");	 
				
		//Turn on Wi-fi again
		try {
				driver.setConnection(new ConnectionState(2));
				test.log(LogStatus.INFO, "Turned ON Wi-Fi connection");
		}
		catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to turn ON Wi-Fi connection");
		} 			
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
