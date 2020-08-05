package com.viacom.uinavigation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyMetaDataForTraysInBookDetailsPage extends BaseTestV2{
	
	String testName = "VerifyMetaDataForTraysInBookDetailsPage";
	@Test(dataProvider = "getData")
	public void VerifyMetaDataForTraysInBookDetailsPage(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("Verify MetaData For Trays In Book Details Page");
		test.log(LogStatus.INFO, "Starting the test for 485: Verify the book card metadata present for the cards in configurable tray in Book details page "+VootConstants.DEVICE_NAME);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		Xls_Reader xls485 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno485=xls485.getRowCount("Smoke_Results")+1;
		xls485.setCellData("Smoke_Results", "Testcase Name",rowno485,"485: Verify the book card metadata present for the cards in configurable tray in Book details page");	
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BooksPageV2  bookspagev2=new BooksPageV2(driver,test);
		 
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched and logged in successfully");
			// BasePageV2.takeScreenshot();
			 //System.out.println(driver.getPageSource());
		 }
		  
		//Login module to be added
		 
		//Get from API
		//API for New Books
		 int apiFirstNewBookMID;
		 String url_NewBooks="";
		 String api_NewBooks="New Books";
		 String apiname_NewBooks="";
		 Response resp_NewBooks=null;
		 int rows_NewBooks=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows_NewBooks;rNum++){
			apiname_NewBooks=xls.getCellData("Api", "API Name", rNum);
			if(apiname_NewBooks.equals(api_NewBooks)){
				url_NewBooks=xls.getCellData("Api", "Url", rNum);
				Map map=BasePageV2.apiparams(2, xls, api_NewBooks);
				resp_NewBooks=Utilities.requestUtilitypostOld(url_NewBooks, map);
				apiFirstNewBookMID=resp_NewBooks.jsonPath().get("assets.items[0].mId");
			 }
		 }
		
		//Verification of 485: Verify the book card metadata present for the cards in configurable tray in Book details page
		test.log(LogStatus.INFO, "Starting test for 485: Verify the book card metadata present for the cards in configurable tray in Book details page");
		//Click on My Profile tab
		 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
			 homepagev2.mystuff_tab.click();	
			 test.log(LogStatus.INFO, "Clicked on My Stuff tab");
			// BasePageV2.takeScreenshot();
				//Click on Read tab	
				test.log(LogStatus.INFO, "Navigating to Read Tab");
				Thread.sleep(1000);
				 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) {
					homepagev2.read_tab.click();	
					test.log(LogStatus.INFO, "Clicked on Read tab");
				//	BasePageV2.takeScreenshot();
					String xpath="//android.widget.TextView[@text='NEW BOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
					Utilities.verticalSwipe(driver, xpath);
					Thread.sleep(2000);	
					test.log(LogStatus.INFO, "Navigating to any of book under New Books Section");
					
					if(Utilities.explicitWaitClickable(driver, bookspagev2.newBooksFirstBook, 30))
					bookspagev2.newBooksFirstBook.click();
					else {
						BasePageV2.reportFail("Not able to click book under New Books section");
						homepagev2.smokeresults("Testcase 485: Verify the book card metadata present for the cards in configurable tray in Book details page",rowno485, "FAIL");
					}
					//Scroll to  RELATED EBOOKS tray section
					String relatedTray="//android.widget.TextView[@text='RELATED EBOOKS']";
					Utilities.verticalSwipe(driver, relatedTray);
					Thread.sleep(1000);
					//check for Related Tray section
					if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageRelatedBooksTray , 30)){
						test.log(LogStatus.INFO, "Related EBooks Tray is displayed");	
						//BasePageV2.takeScreenshot();
						
						
					}
					else {
						homepagev2.smokeresults("Testcase 485: Verify the book card metadata present for the cards in configurable tray in Book details page",rowno485, "FAIL");
						BasePageV2.reportFail("Related ebooks Tray is not displayed in Book details page");
					}
				}
				else {
					homepagev2.smokeresults("Testcase 485: Verify the book card metadata present for the cards in configurable tray in Book details page",rowno485, "FAIL");
					BasePageV2.reportFail("Not able to click on read tab");			
				}
		 }
		 else {
			 homepagev2.smokeresults("Testcase 485: Verify the book card metadata present for the cards in configurable tray in Book details page",rowno485, "FAIL");
			 BasePageV2.reportFail("Unable to click on My Stuff tab");
		 }
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
