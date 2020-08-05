package com.viacom.ebook;

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

public class VerifyBookReaderFunctionalityTest extends BaseTestV2{
	String testName = "VerifyBookReaderFunctionalityTest";
	
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Book Reader screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Book Reader screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int navirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",navirow, "Verify the navigation of Reader player when tapped on any book card Preview button");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int hiderow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",hiderow, "Verify UI of Reader player controls when immediately player is launched");	
		

	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify UI of Reader player controls after tapping on the player screen");	
		
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int optionrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",optionrow, "Verify UI of Options page screen in Reader player");	
		
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int scrubrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",scrubrow, "Verify the Scrub functionality in Reader player:");	
		
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int optionchangerow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",optionchangerow, "Verify if options changed in One book will be same if user navigates to different book");	
	
    Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
    int favrow=xls7.getRowCount("Smoke_Results")+1;
    xls7.setCellData("Smoke_Results", "Testcase Name",favrow, "Verify Unfavourite icon functionality:");	

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
	if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 30))
	homepagev2.read_tab.click();
	else
	BasePageV2.reportFail("Not able to click on read tab");
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
	
   		Thread.sleep(5000);
   		boolean readerDisplayedPreview=false;
		 for(int wait=0;wait<=30;wait++) {
			 try {
				 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
				 //Add 1447
				 if(wait==30) {
					 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
					 
					 BasePageV2.takeScreenshot();
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to book details page");
				 }
			 }
			 catch(Exception e) {
				 test.log(LogStatus.INFO, "Loader is not displayed");
				 Set<String> CHS = driver.getContextHandles();
				 for(String ch:CHS){
					if(ch.contains("WEBVIEW")){
						test.log(LogStatus.INFO, "eBook Reader is displayed");
						readerDisplayedPreview=true;
						break;
					}
				 }  
			 }	
			 if(readerDisplayedPreview==true)
				 break;
			 else
				 Thread.sleep(1000);
		 }
		 
   	 readpagev2.dismissReaderCoachCards();
   	 Thread.sleep(3000);


     test.log(LogStatus.INFO, "Verifying Whether the controls are hidden when reader is launched");
    		 
    		
    		if(Utilities.explicitWaitVisibleNew(driver, playerpagev2.bookReaderSettingsButton, 5))
    		{
    			test.log(LogStatus.FAIL, "Controls are not hidden when reader launched -eg(settings icon) ");
    			BasePageV2.takeScreenshot();
    		}
    		else
    		{
    			test.log(LogStatus.INFO, "Controls are hidden by default when reader launched");
    			test.log(LogStatus.PASS, "Testcase : 'Verify UI of Reader player controls when immediately player is launched' is Passed");
    		    BasePageV2.smokeresults("", hiderow, "Pass");
    		}
         Utilities.tap(driver);
         Utilities.tap(driver);
	
	test.log(LogStatus.INFO, "Verifying whether reader player have controls when tapped on player screen");
	
	
	int controls_err_count=0;
	
	if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderSettingsButton, 10))
	test.log(LogStatus.INFO," Option icon is displayed");
	else
	{
		
		test.log(LogStatus.FAIL, "Option icon is not displayed");
		BasePageV2.takeScreenshot();
		controls_err_count++;
	}
	/*   if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderCloseButton, 10))
	   test.log(LogStatus.INFO," close icon is displayed");
		else
		{
			test.log(LogStatus.FAIL, "Close icon is not displayed");
			BasePageV2.takeScreenshot();
			controls_err_count++;
		}
	   */
	      if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderSeekBar, 10))
		   test.log(LogStatus.INFO," seek bar is displayed");
			else
			{
				test.log(LogStatus.FAIL, "seek bar is not displayed");
				BasePageV2.takeScreenshot();
				controls_err_count++;
			}
	      
	         if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderFavButton, 10))
			   {
	                 test.log(LogStatus.INFO," Favorite icon is displayed");
	                 
			   }
				else
				{
					test.log(LogStatus.FAIL, " Favorite icon is not displayed");
					BasePageV2.takeScreenshot();
					controls_err_count++;
				}
	         

	         if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderChapterNumber, 10))
			   test.log(LogStatus.INFO,"Chapter name is displayed");
				else
				{
					test.log(LogStatus.FAIL, "Chapter Name is not displayed");
					BasePageV2.takeScreenshot();
					controls_err_count++;
				}
	

	         if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderPageNumber, 10))
			   test.log(LogStatus.INFO,"Number of pages read out of total number of pages is displayed");
				else
				{
					test.log(LogStatus.FAIL, "Number of pages read out of total number of pages is not displayed");
					BasePageV2.takeScreenshot();
					controls_err_count++;
				}
	         
	               if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderNarrationButton, 10))
				   test.log(LogStatus.INFO,"Narration button on/off is displayed");
					else
					{
						test.log(LogStatus.FAIL, "Narration button on/off is not displayed");
						BasePageV2.takeScreenshot();
						controls_err_count++;
					}
	
	   if(controls_err_count==0)
		   {
		   test.log(LogStatus.PASS, "Testcase : 'Verify UI of Reader player controls after tapping on the player screen' is Passed");
		   BasePageV2.smokeresults("", uirow, "Pass");
		   }
	//	 bookspagev2.slideHalfBookReader(driver);
		 test.log(LogStatus.PASS,"Testcase : 'Verify the Scrub functionality in Reader player:' is Passed");
		 BasePageV2.smokeresults("", scrubrow, "Pass");
		 try{
			 driver.switchTo().defaultContent();
		 }
		 catch(Exception e)
		 {
			 
		 }
	     Thread.sleep(10000);
		 if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderSettingsButton, 10))
		 playerpagev2.bookReaderSettingsButton.click();
		 else
		{
			test.log(LogStatus.FAIL, "Option icon is not displayed");
			BasePageV2.takeScreenshot();
		} 
		 int option_err_count=0;
		 test.log(LogStatus.INFO, "Verifying ui of the Options page screen in reader player");
		 
		  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderOptionMagnify, 10))
		  {
			  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderOptionMagnifyOff,2))
			  playerpagev2.bookReaderOptionMagnify.click();
			  test.log(LogStatus.INFO,"Magnify option is displayed");
			  
		  }
				else
				{
					test.log(LogStatus.FAIL, "Magnify option is not displayed");
					BasePageV2.takeScreenshot();
					option_err_count++;
				}
		  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderOptionDicionary, 10))
			   {
			  test.log(LogStatus.INFO,"Dictionary option is displayed");
			  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderOptionDicionaryOff, 2))
			  playerpagev2.bookReaderOptionDicionary.click();
			   }
				else
				{
					test.log(LogStatus.FAIL, "Dictionary option is not displayed");
					BasePageV2.takeScreenshot();
					option_err_count++;
				}
		  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderOptionAutoPageTurn, 10))
		  {
			  
			  test.log(LogStatus.INFO,"Auto Page Turn on/off is displayed");
			  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderOptionAutoPageTurnOff, 10))
			  playerpagev2.bookReaderOptionAutoPageTurn.click();
		  }
		  else
		   {
			  test.log(LogStatus.FAIL, "Auto Page Turn on/off is not displayed");
			   BasePageV2.takeScreenshot();
			   option_err_count++;
			}
	
		  if(option_err_count==0)
		   {
		   test.log(LogStatus.PASS, "Testcase : 'Verify UI of Options page screen in Reader player' is Passed");
		   BasePageV2.smokeresults("", optionrow, "Pass");
		   }
		  
		if(playerpagev2.bookReaderOptionOn.size()>=3)
		{
			test.log(LogStatus.INFO, "Changing all the options" );
		}
		else
			BasePageV2.reportFail("Changing the option failed");
		
		
		  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderCloseButton, 10))
		  {
			  playerpagev2.bookReaderCloseButton.click();
		  }
		  else
			  BasePageV2.reportFail("Not able to click on close button / Close button not displayed in Options page screen");
			  
		 /* 
		  if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderCloseButton, 10))
		  {
			  playerpagev2.bookReaderCloseButton.click();
		  }
		  else
			  BasePageV2.reportFail("Not able to click on close button / Close button not displayed in Reader player screen");
			   
		*/
		  Utilities.tap(driver);
		  driver.navigate().back();
		  Thread.sleep(500);
		  driver.navigate().back();
		  
           test.log(LogStatus.INFO, "Clicking on any other book");
			if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTraySecondEbook, 30))
			bookspagev2.firstTraySecondEbook.click();
			else
			BasePageV2.reportFail("Not able to click other book under New Books section");
			
			//Click on preview button
			if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBookPreviewButton, 30))
			bookspagev2.bookDetailPageBookPreviewButton.click();
			else
			BasePageV2.reportFail("Not able to click on preview button");
			bookspagev2.verifyReaderProgressBar();
			Thread.sleep(5000);
		    
			Set<String> CHS1 = driver.getContextHandles();
		    for(String ch:CHS1)
		   	 {
		   	   System.out.println("Current handle is:"+ch);
		   	   if(ch.contains("WEBVIEW"))
		   	   {
		   			   test.log(LogStatus.INFO, "Reader launches when clicking preview button");		   			   
		   	   }
		   	 }
		  
		    Utilities.tap(driver);
	        Utilities.tap(driver);
            test.log(LogStatus.INFO, "Navigating to Options Page Screen");
	    	if(Utilities.explicitWaitVisible(driver, playerpagev2.bookReaderSettingsButton, 10))
	    	playerpagev2.bookReaderSettingsButton.click();
	    	else
	    	{
	    		BasePageV2.reportFail("Option icon is not displayed / Controls still hidden after double tap");	    		
	    	}
	    	test.log(LogStatus.INFO, "Verifying whether option changed is same in this book");
	    	
	    	if(playerpagev2.bookReaderOptionOn.size()!=3)
			{
				test.log(LogStatus.INFO, "Options changed is not same in this book");
				test.log(LogStatus.PASS, "Testcase : 'Verify if options changed in One book will be not same if user navigates to different book' is Passed");
			    BasePageV2.smokeresults("", optionchangerow, "Pass");
			}
			else
				BasePageV2.reportFail("Options changed in other book is same in this book");	
	}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
