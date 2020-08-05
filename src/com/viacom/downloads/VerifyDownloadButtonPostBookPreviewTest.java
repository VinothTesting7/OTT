package com.viacom.downloads;

import java.time.Duration;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

//Author:vinoth
public class VerifyDownloadButtonPostBookPreviewTest extends BaseTestV2{
	String testName = "VerifyDownloadButtonPostBookPreviewTest";
	
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("VerifyDownloadButtonPostBookPreviewTest");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Download Button Post Book Preview Test: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	System.out.println(driver.getPageSource());
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);	
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	BooksPageV2  bookspagev2=new BooksPageV2(driver,test);
	ReadPageV2 readpagev2=new ReadPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	homepagev2.login(un, pwd);
	
//click on watch tab	
	test.log(LogStatus.INFO, "Navigating to Read Tab");
	Thread.sleep(1000);
    HomePageV2.tabClick("Read");
	Thread.sleep(1000);
	String xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
	Utilities.verticalSwipe(driver, xpath);
	Thread.sleep(2000);	
	test.log(LogStatus.INFO, "Navigating to any of book under New Books Section");
	
	if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTrayFirstBook, 30))
	bookspagev2.firstTrayFirstBook.click();
	else
	BasePageV2.reportFail("Not able to click book under New Books section");
	
	
	//Click on preview button

	if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 30))
	bookspagev2.bookDetailPageBookPreviewButton.click();
	else
	BasePageV2.reportFail("Not able to click on preview button");
	
	bookspagev2.verifyReaderProgressBar();
	Thread.sleep(5000);
    Set<String> CHS = driver.getContextHandles();

    
	test.log(LogStatus.INFO, "flipping the page post naration and verifying whether download button displayed");
	for(int i=1;i<=6;i++)
	{
		Utilities.horizontalSwipe(driver);
		Thread.sleep(2000);
		if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadButtonPostFlipping, 3))
		{
			test.log(LogStatus.INFO, "Download CTA button displayed post flipping the pages");
			if(Utilities.explicitWaitVisible(driver, bookspagev2.continueReadingMessage, 5))
			{
				test.log(LogStatus.INFO, "Download this e-Book to continue reading messageis displayed on download overlay screen");
			    test.log(LogStatus.PASS, "Testcase : 'Verify if Download overlay screen is displayed post completion of 5 pages' is Passed");
			     if(!Utilities.setResultsKids("VK_424", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
			}
			else
			{
				test.log(LogStatus.FAIL, "Download this e-Book to continue reading message not displayed on download overlay screen");
			    BasePageV2.takeScreenshot();
			     if(!Utilities.setResultsKids("VK_424", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");						 
			}
			
			
			if(Utilities.explicitWaitVisible(driver, bookspagev2.closeButtonOnDownloadOverlay, 10))
			{
				test.log(LogStatus.INFO, " Cross mark(x) button is displayed on download overlay screen");
				bookspagev2.closeButtonOnDownloadOverlay.click();
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookPreviewButton, 5))
				{
					test.log(LogStatus.FAIL, "Tapping on 'Cross Mark' not landing on previous page");
					BasePageV2.takeScreenshot();				    
					if(!Utilities.setResultsKids("VK_1826", "Fail")) 
				    test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
								 
				}
				else
				{
					test.log(LogStatus.INFO, "Tapping on 'Cross Mark' button closes the overlay pop-up and land on previous page");
				    test.log(LogStatus.PASS, "Testcase : 'Verify the Functionality of 'Cross Mark' button in post preview overlay' is Passed");
				    if(!Utilities.setResultsKids("VK_1826", "Pass")) 
				    test.log(LogStatus.WARNING, "TC ID not found in the tc document");				 
				}
			   
			}
			else
			{
				test.log(LogStatus.FAIL, " Cross mark(x) button is not displayed on download overlay screen on download overlay screen");
				BasePageV2.takeScreenshot();
			}
			
			break;
		}
		else if(i==6)
		{
			BasePageV2.reportFail("Download CTA button not displayed post flipping more than 5 pages");
		}
	}
	
		  
	}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
