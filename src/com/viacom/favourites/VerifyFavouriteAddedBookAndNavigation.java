package com.viacom.favourites;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//Author Tanisha
public class VerifyFavouriteAddedBookAndNavigation extends BaseTestV2{
	
	String testName = "VerifyFavouriteAddedBookAndNavigation";
	@Test(dataProvider = "getData")
	public void VerifyFavouriteAddedBookAndNavigation(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyFavouriteAddedBookAndNavigation");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls210 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno210=xls210.getRowCount("Smoke_Results")+1;
		xls210.setCellData("Smoke_Results", "Testcase Name",rowno210,"210: Verify the metadata displayed for the favorited Book card");

		Xls_Reader xls474 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno474=xls474.getRowCount("Smoke_Results")+1;
		xls474.setCellData("Smoke_Results", "Testcase Name",rowno474,"474: Verify the click funtionality on tapping on favourite icon in book detail page");
		
		Xls_Reader xls648 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno648=xls648.getRowCount("Smoke_Results")+1;
		xls648.setCellData("Smoke_Results", "Testcase Name",rowno648,"Verify Unfavourite icon functionality");
		
		Xls_Reader xls632 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno632=xls632.getRowCount("Smoke_Results")+1;
		xls632.setCellData("Smoke_Results", "Testcase Name",rowno632,"Verify the Close icon functionalty in Options overlay screen");

		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 //Login module to be added
		 //homepagev2.signup();
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		
		//Verification of 210
		//Click on Read tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) {
			 homepagev2.read_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on Read tab");
			 String firstBookName="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
			 String firstBookDesc="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/subtitle']";
			 String name=driver.findElementByXPath(firstBookName).getAttribute("text");
			 name=homepagev2.convertCamelCase(name);
			 String desc=driver.findElementByXPath(firstBookDesc).getAttribute("text");
			 desc=homepagev2.convertCamelCase(desc);
			 if(Utilities.explicitWaitClickable(driver, readpagev2.firstBookBelowCarousal, 5)) {
				 readpagev2.firstBookBelowCarousal.click(); 
				 test.log(LogStatus.INFO, "Clicked on the first Book below carousal: "+name+" by author: "+desc);  	
				 //Verify the presence of Favorite icon
				 if(Utilities.explicitWaitVisible(driver, readpagev2.favIconBookDetails, 10)) {
					 test.log(LogStatus.INFO,"Favourite icon is visible in the book detail page"); 
					 if(Utilities.explicitWaitClickable(driver, readpagev2.favIconBookDetails, 10)) {
						 readpagev2.favIconBookDetails.click();
						 if(readpagev2.favIconBookDetails.getAttribute("checked").equals("true")) {
							 test.log(LogStatus.INFO,"Marked as Favourite"); 
							 basepagev2.takeScreenshot();
							 //Verification of 648
							 if(Utilities.explicitWaitClickable(driver, readpagev2.favIconBookDetails, 10)) {
								 readpagev2.favIconBookDetails.click();
								 if(readpagev2.favIconBookDetails.getAttribute("checked").equals("false")) {
									 test.log(LogStatus.INFO,"Marked as Un Favourite"); 
									 basepagev2.takeScreenshot();
									 test.log(LogStatus.PASS, "Verify Unfavourite icon functionality:Tapping on unfavorite icon, should unfavorite the already favorited ebook card is PASS");
									 homepagev2.smokeresults("Verify Unfavourite icon functionality:Tapping on unfavorite icon, should unfavorite the already favorited ebook card",rowno648, "PASS");
									 if(Utilities.explicitWaitClickable(driver, readpagev2.readButton, 10)) {
									 	readpagev2.readButton.click(); 
									 	test.log(LogStatus.INFO, "Tapped on READ button");
									 } 
									 else if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10)) {
										readpagev2.previewButton.click();
										test.log(LogStatus.INFO, "Tapped on PREVIEW button");
									 }	 
									 else
										test.log(LogStatus.FAIL, "Unable to tap on Read/Preview button");
									 if(Utilities.explicitWaitVisible(driver, readpagev2.bookReader, 10)) {
										test.log(LogStatus.INFO, "eBook reader is displayed");
										Utilities.tap(driver);
										Utilities.tap(driver);
										test.log(LogStatus.INFO, "Tapped twice on reader to display reader controls");
										if(Utilities.explicitWaitClickable(driver, readpagev2.readerOptions, 10)) {
											try {
												readpagev2.readerOptions.click();
												test.log(LogStatus.INFO, "Clicked on eBook Reader options");
												basepagev2.takeScreenshot();
												if(Utilities.explicitWaitClickable(driver, readpagev2.readerOptionsClose, 10)) {
													try {
														readpagev2.readerOptionsClose.click();
														test.log(LogStatus.INFO, "Clicked on Close button");
														if(Utilities.explicitWaitVisible(driver, readpagev2.readerOptions, 10)) {
															test.log(LogStatus.INFO, "Application has navigated to eBook Reader Controls screen");
															homepagev2.smokeresults("Verify the Close icon functionalty in Options overlay screen",rowno632, "PASS");
															homepagev2.reportPass("Verify the Close icon functionalty in Options overlay screen is PASS");
														}
														else {
															test.log(LogStatus.FAIL, "Application has failed to navigate to eBook Reader Controls screen");
															homepagev2.smokeresults("Verify the Close icon functionalty in Options overlay screen",rowno632, "FAIL");
															test.log(LogStatus.FAIL, "Verify the Close icon functionalty in Options overlay screen is FAIL");
														}
													}
													catch(Exception e) {
														test.log(LogStatus.FAIL, "Failed to click on Close button in eBook Reader Options page");
													}							 
												}
												else {
													test.log(LogStatus.FAIL, "Close button in eBook Reader Options page is not clickable");
												}
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to click on eBook Reader options");
											}
										}
									} 
								 }
								 else {
									 test.log(LogStatus.INFO,"Failed to mark as Un Favourite"); 
									 test.log(LogStatus.PASS, "Verify Unfavourite icon functionality:Tapping on unfavorite icon, should unfavorite the already favorited ebook card is FAIL");
									 basepagev2.takeScreenshot();
									 homepagev2.smokeresults("Verify Unfavourite icon functionality:Tapping on unfavorite icon, should unfavorite the already favorited ebook card",rowno648, "FAIL"); 
								 }
							 }						
						 }
						 else
							 test.log(LogStatus.FAIL, "Unable to mark as Favourite");
						 //Mark Favourite
						 driver.navigate().back();
						 test.log(LogStatus.INFO, "Device navigated back to ebook Reader");
						 driver.navigate().back();
						 test.log(LogStatus.INFO, "Device navigated back to book details page");
						 if(Utilities.explicitWaitClickable(driver, readpagev2.favIconBookDetails, 10)) {
							 readpagev2.favIconBookDetails.click();
							 if(readpagev2.favIconBookDetails.getAttribute("checked").equals("true")) { 
								 test.log(LogStatus.INFO,"Marked as Favourite"); 
								 basepagev2.takeScreenshot();
							 }
							 else 
								 test.log(LogStatus.FAIL,"Failed to Mark as Favourite");  
						 }
						 else
							 test.log(LogStatus.FAIL,"Favourite icon is not clickable");  	 
						 
						 //Scroll to Favourites
						 driver.navigate().back();
						 Utilities.verticalSwipeReverse(driver);
						 homepagev2.mystuff_tab.click();
						 Thread.sleep(2000);
						 String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
						 boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
						 if(presenceTray==true) {
							test.log(LogStatus.INFO, "Favourites tray is displayed");
							//[@text=\""+bookname+"\"]"
							String uiFavBookLocator="//android.widget.TextView[@text=\""+name+"\"]";
							String uiFavDescLocator="//android.widget.TextView[@text=\""+name+"\"]/parent::android.view.ViewGroup//android.widget.TextView[contains(@text,'"+desc+"')]";
							String uiFavIconLocator="//android.widget.TextView[@text=\""+name+"\"]/parent::android.view.ViewGroup//android.widget.ImageView[@index='5']";
							for(int scroll=0;scroll<=3;scroll++) {
								try {
									driver.findElement(By.xpath(uiFavBookLocator));
									test.log(LogStatus.INFO, "Book is present in Favorites tray");
									test.log(LogStatus.PASS, "474: Verify the click funtionality on tapping on favourite icon in book detail page is PASS");
									homepagev2.smokeresults("474: Verify the click funtionality on tapping on favourite icon in book detail page",rowno474, "PASS");
									basepagev2.takeScreenshot(); 
									//Verification of 210								
									try {
										String uiBookName=driver.findElement(By.xpath(uiFavBookLocator)).getAttribute("text");
										test.log(LogStatus.INFO, "Book Title is verified "+uiBookName);
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Book Title not displayed");
										errCount++;
									}
									try {
										String uiDescName=driver.findElement(By.xpath(uiFavDescLocator)).getAttribute("text");
										test.log(LogStatus.INFO, "Book Description is verified "+uiDescName);
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Book Description not displayed");
										errCount++;
									}
									try {
										driver.findElement(By.xpath(uiFavIconLocator));
										test.log(LogStatus.INFO, "Book Icon is verified");
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Book Icon not displayed");
										errCount++;
									}
									//Click on the book
									try {
										driver.findElement(By.xpath(uiFavBookLocator)).click();
										
									}
									catch(Exception e) {
										
									}
									break;
								}
								catch(Exception e) {
									Utilities.verticalSwipe(driver);
									if(scroll==3) {
										test.log(LogStatus.INFO, "Book is not present in Favourites Tray");
										test.log(LogStatus.FAIL, "474: Verify the click funtionality on tapping on favourite icon in book detail page is FAIL");
										homepagev2.smokeresults("474: Verify the click funtionality on tapping on favourite icon in book detail page",rowno474, "FAIL");			
										errCount++;
										basepagev2.takeScreenshot(); 
									}
								}
							}
							if(errCount>0) {
								test.log(LogStatus.FAIL, "210: Verify the metadata displayed for the favorited Book card is FAIL");
								homepagev2.smokeresults("210: Verify the metadata displayed for the favorited Book card",rowno210, "FAIL");
							}
							else {
								test.log(LogStatus.PASS, "210: Verify the metadata displayed for the favorited Book card is PASS");
								homepagev2.smokeresults("210: Verify the metadata displayed for the favorited Book card",rowno210, "PASS");
							}
						}
						else {
							test.log(LogStatus.FAIL, "Favourites tray is not displayed even after marking book as Favourite");
							basepagev2.takeScreenshot();
						}	
					}
					else {
						test.log(LogStatus.FAIL,"Favourite icon is not clickable in the book detail page"); 
						basepagev2.takeScreenshot();
					}					
				 }
				 else {
					 test.log(LogStatus.FAIL,"Favourite icon is not visible in the book detail page");
					 basepagev2.takeScreenshot();
				 }	  
			}
			else {
				test.log(LogStatus.FAIL,"Unable to click on the first book from the NEW BOOKS tray"); 
				basepagev2.takeScreenshot();
			}		
		 }
		 else {
			 test.log(LogStatus.FAIL, "Unable to click on Read tab");
			 basepagev2.takeScreenshot();
		 }
		 
		}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
