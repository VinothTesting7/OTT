package com.viacom.downloads;

import java.time.Duration;
import java.util.ArrayList;
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
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyNoDownloads extends BaseTestV2{
	
	String testName = "VerifyNoDownloads";
	@Test(dataProvider = "getData")
	public void VerifyNoDownloads(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyNoDownloads");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		// VK_943 Verify the UI of Downloads tab when there are no downloaded assets
		
		//Launch Voot kids app
		//Login module to be added
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 DownloadsPageV2 downloadpagev2=new DownloadsPageV2(driver,test);
		//Login module 
		//homepagev2.logout();
		homepagev2.login(data.get("Email"),data.get("Password"));
		downloadpagev2.deleteAllDownloads();
		 int errCount943=0;
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched and logged in successfully");
			 //System.out.println(driver.getPageSource());
			 //Verification of 905
			 test.log(LogStatus.INFO, "Starting test for 905: Verify the UI of Downloads tab when there are no downloaded assets");
			 //Click on My Profile tab
			 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
				 homepagev2.mystuff_tab.click();	
				 test.log(LogStatus.INFO, "Clicked on My Stuff tab");
				 //Scroll to Downloads tab
				 String downloadTabLocator="//android.widget.TextView[@text='Downloads']";
				 boolean tabPresence=Utilities.verticalSwipeAndFind(driver,downloadTabLocator);
				 if(tabPresence==true) {
					 if(Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5)) {
						 try {
							 homepagev2.downloadsTab.click();
							 Utilities.verticalSwipe(driver);
							 //Verify if Downloads is enabled
							 try {
								 boolean selected=homepagev2.downloadsTab.getAttribute("selected").equals("true");
								 if(selected==true) {
									 test.log(LogStatus.PASS, "Downloads tab is highlighted");
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "Downloads tab is not highlighted");
								 errCount943++;
							 }
							 Utilities.verticalSwipe(driver);
							 try {
								 boolean nothingHere=homepagev2.downloadsNothingHere.getAttribute("text").equals("NOTHING HERE");
								 if(nothingHere=true)
									 test.log(LogStatus.PASS, "NOTHING HERE is displayed since there are no downloads"); 
								 else {
									 test.log(LogStatus.FAIL, "NOTHING HERE is not displayed for no downloads");
									 errCount943++; 
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "NOTHING HERE is not displayed for no downloads");
								 errCount943++;
							 }
							 Utilities.verticalSwipe(driver);
							 try {
								 boolean supportText=homepagev2.downloadsSupportText.getAttribute("text").equals("Start browsing and download your favourite books, episodes, audiobooks for an offline experience.");
								 if(supportText==true)
									 test.log(LogStatus.PASS, "\"Start browsing and download your favourite books, episodes, audiobooks for an offline experience.\" is displayed since there are no downloads"); 
								 else {
									 test.log(LogStatus.FAIL, "\"Start browsing and download your favourite books, episodes, audiobooks for an offline experience.\" is not displayed for no downloads");
									 errCount943++; 
								 }
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "\"Start browsing and download your favourite books, episodes, audiobooks for an offline experience.\" is not displayed for no downloads");
								 errCount943++;
							 }
							 if(errCount943>0) { 
								 test.log(LogStatus.FAIL, "Verify the UI of Downloads tab when there are no downloaded assets is FAIL");
								 if(!Utilities.setResultsKids("VK_943", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								 BasePageV2.takeScreenshot();
							 }
							 else {
								 test.log(LogStatus.PASS, "Verify the UI of Downloads tab when there are no downloaded assets is PASS");	
								 if(!Utilities.setResultsKids("VK_943", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			 
							 }
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Unable to click on Downloads tab");
						 }
					 }
					 else 
						 test.log(LogStatus.FAIL,"Downloads tab is not clickable");   
				 }	 
				 else
					 test.log(LogStatus.FAIL,"Download tab is not visisble");  
			}
			else
				test.log(LogStatus.FAIL,"Unable to click on My Stuff tab");  
		}
		else
			test.log(LogStatus.FAIL, "Failed to log in to the application");
	
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
