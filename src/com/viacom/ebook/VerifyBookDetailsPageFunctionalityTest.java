package com.viacom.ebook;

import java.time.Duration;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

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
import com.viacom.pagesVersion2.LaunchPageV2;
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

public class VerifyBookDetailsPageFunctionalityTest extends BaseTestV2{
	String testName = "VerifyBookDetailsPageFunctionalityTest";
	
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Book Detail screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Book Detail screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of the book detail screen:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int metadatarow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",metadatarow, "Verify the metadata of  the book:");	
		
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	WatchPageV2  watchpagev2=new WatchPageV2(driver,test);
	BooksPageV2  bookspagev2=new BooksPageV2(driver,test);
	
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
	
	
	test.log(LogStatus.INFO, "Verifying Books details page UI");
	
	//Check fOR back button	
	if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageBackButton, 30))
	{
		test.log(LogStatus.INFO, "Back button is displayed");
		test.log(LogStatus.INFO, "Verifying Back icon functionality in Book Detail screen:");
		//BasePageV2.takeScreenshot();
		bookspagev2.bookDetailPageBackButton.click();
		if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTrayFirstBook, 30))
		{
			test.log(LogStatus.PASS,"Testcase : 'Verify the click funtionality on tapping on back icon in book detail page' is Passed");
			bookspagev2.firstTrayFirstBook.click();
		}
			else
			BasePageV2.reportFail("Not able to click book under New Books section");
	}
	else
	BasePageV2.reportFail("Back button is not displayed in Book Detail screen");
	
	//cHECK FOR Favorite button
	
	if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageFavoriteButton , 30))
	test.log(LogStatus.INFO, "Favoriting Button is displayed");
	else
	BasePageV2.reportFail("Favorite Button is not displayed in Book Detail screen");
	
	
	//cHECK FOR bOOK title
	
		if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookTitle , 30))
		{
			if(!bookspagev2.bookDetailPageBookTitle.getText().isEmpty() && bookspagev2.bookDetailPageBookTitle.getText()!=null)
			test.log(LogStatus.INFO, "Book Title is displayed");
			else
		    test.log(LogStatus.FAIL, "Book Title is missing");
		}
		else
		BasePageV2.reportFail("Book Title is not displayed in Show Detail page");
		
		
		//cHECK FOR bOOK Author name
		
		if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookAuthorName , 30))
		{
			if(!bookspagev2.bookDetailPageBookAuthorName.getText().isEmpty() && bookspagev2.bookDetailPageBookAuthorName.getText()!=null)
			test.log(LogStatus.INFO, "Book Author name is displayed");
			else
			test.log(LogStatus.FAIL, "Book Author name is missing");
		}
		else
		BasePageV2.reportFail("Book Author Name is not displayed");
		
		

          Utilities.verticalSwipe(driver, bookspagev2.bookDetailPageBookDescription , "visible", 1);
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookDescription , 30))
				{
					if(!bookspagev2.bookDetailPageBookDescription.getText().isEmpty() && bookspagev2.bookDetailPageBookDescription.getText()!=null)
					test.log(LogStatus.INFO, "Description about Book is displayed");
					else
					test.log(LogStatus.FAIL, "Description about Book is missing");
				}
				else
				BasePageV2.reportFail("Description about Book is not displayed in Book Detail screen");
				
				
				test.log(LogStatus.PASS,"Testcase : 'Verify the metadata of  the book:' is Passed");	
		//scroll to book level 
				xpath="//android.widget.TextView[@text='Level']";
				//Utilities.verticalSwipe(driver, xpath);
				   Utilities.verticalSwipe(driver,xpath , "visible", 1);
		//Check For Book Level
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookLevelCount , 30))
				{
					if(!bookspagev2.bookDetailPageBookLevelCount.getText().isEmpty() && bookspagev2.bookDetailPageBookLevelCount.getText()!=null)
						test.log(LogStatus.INFO, "Book Level is displayed");
						else
						test.log(LogStatus.FAIL, "Book Level is missing");
					
				}
				else
				BasePageV2.reportFail("Book Level  is not displayed in Book Detail screen");

		
				//Check For Book Read Time
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageReadTime , 30))
				{
					if(!bookspagev2.bookDetailPageReadTime.getText().isEmpty() && bookspagev2.bookDetailPageReadTime.getText()!=null)
						test.log(LogStatus.INFO, "Book Read Time is displayed");
						else
						test.log(LogStatus.FAIL, "Book Read Time is missing");
					
				}
				else
				BasePageV2.reportFail("Book Read Time is not displayed in Book Detail screen");
				
				
				//Check For Book Read Time
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageNarrationIcon , 30))
				{
					    test.log(LogStatus.INFO, "Book Narration icon is displayed");
				}
				else
				BasePageV2.reportFail("Book Narration icon is not displayed");
				
				   Utilities.verticalSwipe(driver,bookspagev2.bookDetailPageDownloadBookButton, "visible", 1);
				
			//Scroll to Download episodes button
				 if(!Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageDownloadBookButton, 5))
					{
				    	test.log(LogStatus.INFO, "Download Book Button is displayed");

					}
				 else
				 {
			       String download="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
			       Utilities.verticalSwipe(driver, download);
				 }
			Thread.sleep(1000);
			
			//Check for Episodes Download button
		    if(Utilities.explicitWaitClickable(driver, bookspagev2.bookDetailPageDownloadBookButton, 30))
			{
		    	test.log(LogStatus.INFO, "Download Book Button is displayed");

			}
			else
			BasePageV2.reportFail("Download Book Button is not displayed in book Detail page");
	
  
		    for(int i=1;i<=6;i++)
		    {
		    	Utilities.horizontalSwipe(driver);
		    }
		    
		    
	//Scroll to  Editorial section
		String editorialsectionrelatedTray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='RELATED EBOOKS']";
			Utilities.verticalSwipe(driver, bookspagev2.bookDetailPageRelatedBooksTray,"visible",5);
			Thread.sleep(1000);
			//check for editorial section
			if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageRelatedBooksTray , 30))
			{
				test.log(LogStatus.INFO, "Related EBooks Tray is displayed");
				//Scroll to  related tray content
				//String relatedbooktraycontent="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
				//Utilities.verticalSwipe(driver, relatedbooktraycontent);
				Thread.sleep(1000);
				String bookTitle = "";
				//check for editorial section content
				if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageRelatedTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "Book(s) are displayed under Related EBooks");
					//Check for icon, title , description , 
					try{
						bookTitle=bookspagev2.bookDetailPageEditorialSectionRelatedTrayFirstContentTitle.getText();
					}
					catch(Exception e){}
					//BasePageV2.takeScreenshot();
					test.log(LogStatus.INFO, "Clicking on the book under Related Shows Tray-"+bookTitle);
					bookspagev2.bookDetailPageRelatedTrayFirstContent.click();
					//cHECK FOR book title
					if(Utilities.explicitWaitVisible(driver, bookspagev2.relatedBookDetailPageBookTitle , 30))
					{
						String title=bookspagev2.relatedBookDetailPageBookTitle.getText();
						if(StringUtils.containsIgnoreCase(title, bookTitle))
						{								
							test.log(LogStatus.INFO, "Navigated to the relevant book");
							test.log(LogStatus.PASS,"Testcase : 'Verify the functionality by tapping on a Book card present under any configurable tray(Ex: Related/You May Also Like)' is Passed");
						}
						else
						BasePageV2.reportFail("Not navigating to the respective Book Detail Page -"+title);
					}
					else
					BasePageV2.reportFail("Book Title is not displayed in Show Detail page");
					
				}
				/*else if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionYouMayLikeTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "content are displayed under Editorial section ");
				}*/
				else
					BasePageV2.reportFail("No books are displayed under Related Ebooks Tray");
			}
			else
				BasePageV2.reportFail("Related ebooks Tray is not displayed in Book details page");
		test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the Book detail screen:' is Passed");
	}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
