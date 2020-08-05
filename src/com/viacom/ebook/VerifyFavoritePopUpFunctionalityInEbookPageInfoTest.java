package com.viacom.ebook;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

//Author : Vinoth

//priority - P1

//VK_2312 - Verify the functionality of OK button from Favorite pop up for an ebook card:


public class VerifyFavoritePopUpFunctionalityInEbookPageInfoTest extends BaseTestV2 {
	String testName = "VerifyFavoritePopUpFunctionalityInEbookPageInfoTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("VerifyFavoritePopUpFunctionalityInEbookPageInfoTest");
	test.log(LogStatus.INFO, "Starting the test for Verifying the favorite pop up from ebook program Info page functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	launchApp();
	driver.resetApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
    ReadPageV2 readpagev2=new ReadPageV2(driver,test);
  
	String un=data.get("Email");
	String pwd=data.get("Password");
	homepagev2.login(un, pwd);
	
	 if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10)) {
			homepagev2.tabClick("Read");
	 test.log(LogStatus.INFO, "Clicked on Read tab");
	 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
	 watchpagev2.firstItemInCarousal.click();
			//cHECK FOR PLAY BUTTON
			
	test.log(LogStatus.INFO, "Verifying the Favorite icon functionality of book progrom info page");	  
			
	if(Utilities.explicitWaitClickableNew(driver,readpagev2.favIconBookDetails, 30))
		{
		  readpagev2.favIconBookDetails.click();
		  Thread.sleep(3000);
	    }
	else
		BasePageV2.reportFail("Favorite button not displayed in ebook program info page");

			           if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
						{
							if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
								test.log(LogStatus.INFO, "Pop up message is displayed");
							else
								test.log(LogStatus.FAIL, "Pop up message is not displayed");
							if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
							{
								showspagev2.favIconPopupOkButton.click();
								if(Utilities.explicitWaitClickableNew(driver,readpagev2.favIconBookDetails, 30))
								{
									test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for an ebook card:' is Passed");
									if(!Utilities.setResultsKids("VK_2312", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
								}
								else
								{
									test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
									if(!Utilities.setResultsKids("VK_2312", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
								}
								
							}
							else
							{
								test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting book content in reader for first time");
								 
							}
							
						}
			           else if(Utilities.explicitWaitClickableNew(driver,readpagev2.favIconBookDetails, 30))
			   		{
			     		  readpagev2.favIconBookDetails.click();
			     		  Thread.sleep(3000);
			     		 if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
							{
								if(Utilities.explicitWaitVisibleNew(driver, showspagev2.favIconPopup, 30))
									test.log(LogStatus.INFO, "Pop up message is displayed");
								else
									test.log(LogStatus.FAIL, "Pop up message is not displayed");
								if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 30))
								{
									showspagev2.favIconPopupOkButton.click();
									if(Utilities.explicitWaitClickableNew(driver,readpagev2.favIconBookDetails, 30))
									{
										test.log(LogStatus.PASS, "Testcase : 'Verify the functionality of OK button from Favorite pop up for an ebook card:' is Passed");
										if(!Utilities.setResultsKids("VK_2312", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
									else
									{
										test.log(LogStatus.FAIL, "Pop up ok button functionality failed");
										if(!Utilities.setResultsKids("VK_2312", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
									}
									
								}
								else
								{
									test.log(LogStatus.FAIL, "Pop up ok button is not displayed after favoriting book content in reader for first time");
									 
								}
								
							}
			     	    }
			           
						else
						{
							 if(!Utilities.setResultsKids("VK_2311", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
							 BasePageV2.reportFail("pop up not displayed when favorite the ebook content for first time in ebook program info page");	   
						}
						
					if(Utilities.explicitWaitVisibleNew(driver, readpagev2.favIconBookDetails, 30))
						{	
							test.log(LogStatus.INFO, "Favourite icon gets filled after favoriting");
							readpagev2.favIconBookDetails.click();
							Thread.sleep(3000);
						}
						else
						{
							test.log(LogStatus.FAIL, "Favourite icon not filled after favoriting book in book reader");
						}
					}
	            }
	}

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	}

}