package com.viacom.lastviewed;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.Activity;
//Author Tanisha
public class VerifyLastViewedBooksOrder extends BaseTestV2{
	
	String testName = "VerifyLastViewedBooksOrder";
	static ArrayList<String> totalBooks=new  ArrayList<String>();
	static ArrayList<String> bookAuthors=new  ArrayList<String>();
	static String bookTitle="";
	@Test(dataProvider = "getData")
	
	public void VerifyLastViewedBooksOrder(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedBooksOrder");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}		
		
		//VK_1511 = Verify the no.of cards present under 'Last Viewed' section
		//VK_1512 = Verify the cards removal order under 'Last Viewed' section when user has accessed more than 8 Ebooks
		//VK_1514 = Verify Book card metadata for partially read book
		//VK_1515 = Verify Book card metadata for completely read book:
		//VK_1513 = Verify the type of cards available under 'Last Viewed' section in Read Tab
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 EbooksPageV2 ebookspagev2=new EbooksPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 
		 
		 String book1,book2,book3,book4,book7,book8="";
		 String lastViewed1="";
		 String lastViewed2="";
		 String lastViewed3="";
		 String lastViewed4="";
		 String lastViewed7="";
		 String lastViewed8="";
		 int errVK_1511=0;
		 int errVK_1512=0;
		 int errVK_1514=0;
		 int errVK_1515=0;
		 int errVK_1513=0;
		 
		 String temp="";
		 String tempCC="";
		 String bookAuthor="";
		 String tempAuthor="";
			//Login module 
			//homepagev2.logout();
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 
		//Click on Read tab
		 homepagev2.tabClick("Read");
		 homepagev2.tabClick("Read");
		 boolean stopAddingBooks=false;
		 boolean gotName=false;
		
		for(int scroll=0;scroll<=5;scroll++) {
			 Utilities.verticalSwipe(driver); 
		 }
		 while(totalBooks.size()<=9) {
			 String bookLocs="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
			 List<WebElement> tempBooks=driver.findElementsByXPath(bookLocs);
			 for(int i=0;i<tempBooks.size();i++) {
				 try {
					 temp=tempBooks.get(i).getAttribute("text");
					 gotName=true;
				 }
				 catch(Exception e) {
					 gotName=false;
				 }
				 if(gotName==true) {
					 test.log(LogStatus.INFO, "--------------------------------------------");
					 test.log(LogStatus.INFO, "Book: "+temp);
					 try {
						 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+temp+"\"]")).click();
						 Thread.sleep(2000);
						 Utilities.verticalSwipe(driver);
						 try {
							 temp=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")).getAttribute("text");
							 bookTitle=homepagev2.convertCamelCase(temp);
						 }
						 catch(Exception e){test.log(LogStatus.FAIL, "Failed to fetch Book Name from book details page");}
						 try {
							 bookAuthor=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_author_name']")).getAttribute("text");
						 }
						 catch(Exception e){test.log(LogStatus.FAIL, "Failed to fetch Book Author from book details page");}
						 Thread.sleep(2000);
						 if(!totalBooks.contains(bookTitle)) {
							 test.log(LogStatus.INFO, "Sending "+bookTitle+":"+bookAuthor);
							 openReader(bookTitle,bookAuthor); 
							 driver.navigate().back();
						 }
						 else {
							 test.log(LogStatus.INFO, "Book already added to list.. Hence navigating back");
							 driver.navigate().back();
						 }
						 if(totalBooks.size()>=9) {
							 stopAddingBooks=true;
							 break;
						 }
					 }
					 catch(Exception e) {
						 test.log(LogStatus.FAIL, "Failed to click on Book :"+bookTitle);
					 }		 
				 }
				 gotName=false;
			 }
			 if(stopAddingBooks==true)
				 break;
			 Utilities.verticalSwipe(driver);
			 Utilities.verticalSwipe(driver);
		 }
		 
		test.log(LogStatus.INFO, "Books for which eBook reader opened are: \n"+totalBooks);
		test.log(LogStatus.INFO, "Books for which eBook reader opened are: \n"+bookAuthors);

		for(int wait1=0;wait1<=3;wait1++) {
			 for(int wait=0;wait<=60;wait++) {
				 Thread.sleep(1000);
			 }
		}
		test.log(LogStatus.INFO, "Waited for 3 minutes");
		try {
			driver.closeApp();
		} 
		catch (Exception e) {}
		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKbetaSplashActivity"));
		test.log(LogStatus.INFO, "Relaunched app");
		
		 homepagev2.tabClick("My Stuff");
		 homepagev2.tabClick("Read");
		 //Scroll to Last Viewed
		 String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		 Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		 test.log(LogStatus.INFO, "Swiped till Last Read tray");
		 Utilities.verticalSwipe(driver);
		 Utilities.verticalSwipe(driver);
		 //Check book 1
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
	    	if(lastViewed1.equals(totalBooks.get(8))) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest book under last read in Read tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedFirstBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(bookAuthors.get(8))){
	    				test.log(LogStatus.INFO, "Author "+bookAuthors.get(8)+" matches with author displayed in book details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last read is "+tempAuthor+", Author displayed in book details is "+bookAuthors.get(8));
	    				errVK_1515++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1515++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1515++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last viewed in Read tab instead of "+totalBooks.get(8));
	    		errVK_1512++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed book 1 is not visible under last viewed in Read tab");
	    	errVK_1511++;
	     }
	     //Check book 2
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
	    	if(lastViewed2.equals(totalBooks.get(7))) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest book under last viewed in Read tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedSecondBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(bookAuthors.get(7))){
	    				test.log(LogStatus.INFO, "Author "+bookAuthors.get(7)+" matches with author displayed in book details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last read is "+tempAuthor+", Author displayed in book details is "+bookAuthors.get(7));
	    				errVK_1514++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1514++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1514++;
	    		}
	    		
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last viewed in Read tab instead of "+totalBooks.get(7));
	    		errVK_1512++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed book 2 is not visible under last viewed in Read tab");
	    	errVK_1511++;
	     }
	     //Check book 3
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdBookTitle, 10)) {
	    	lastViewed3=homepagev2.lastViewedThirdBookTitle.getAttribute("text");
	    	if(lastViewed3.equals(totalBooks.get(6))) {
	    		test.log(LogStatus.INFO, lastViewed3+" is the third latest book under last viewed in Read tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedThirdBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(bookAuthors.get(6))){
	    				test.log(LogStatus.INFO, "Author "+bookAuthors.get(6)+" matches with author displayed in book details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last read is "+tempAuthor+", Author displayed in book details is "+bookAuthors.get(6));
	    				errVK_1514++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1514++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1514++;
	    		}
	    	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest book under last viewed in Read tab instead of "+totalBooks.get(6));
	    		errVK_1512++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed book 3 is not visible under last viewed in Read tab");
	    	errVK_1511++;
	     }
	     //Check book 4
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthBookTitle, 10)) {
	    	lastViewed4=homepagev2.lastViewedFourthBookTitle.getAttribute("text");
	    	if(lastViewed4.equals(totalBooks.get(5))) {
	    		test.log(LogStatus.INFO, lastViewed4+" is the fourth latest book under last viewed in Read tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedFourthBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(bookAuthors.get(5))){
	    				test.log(LogStatus.INFO, "Author "+bookAuthors.get(5)+" matches with author displayed in book details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last read is "+tempAuthor+", Author displayed in book details is "+bookAuthors.get(5));
	    				errVK_1514++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1514++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1514++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed4+" is the fourth latest book under last viewed in Read tab instead of "+totalBooks.get(6));
	    		errVK_1512++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed book 4 is not visible under last viewed in Read tab");
	    	errVK_1511++;
	     }
	     Utilities.verticalSwipe(driver);
	     Utilities.verticalSwipe(driver);
	     //Check book 7
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhBookTitle, 10)) {
	    	lastViewed7=homepagev2.lastViewedSeventhBookTitle.getAttribute("text");
	    	if(lastViewed7.equals(totalBooks.get(2))) {
	    		test.log(LogStatus.INFO, lastViewed7+" is the seventh latest book under last viewed in Read tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedSeventhBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(bookAuthors.get(2))){
	    				test.log(LogStatus.INFO, "Author "+bookAuthors.get(2)+" matches with author displayed in book details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last read is "+tempAuthor+", Author displayed in book details is "+bookAuthors.get(2));
	    				errVK_1514++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1514++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1514++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed7+" is the seventh latest book under last viewed in Read tab instead of "+totalBooks.get(2));
	    		errVK_1512++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed book 7 is not visible under last viewed in Read tab");
	    	errVK_1511++;
	     }
	     //Check book 8
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthBookTitle, 10)) {
	    	lastViewed8=homepagev2.lastViewedEighthBookTitle.getAttribute("text");
	    	if(lastViewed8.equals(totalBooks.get(1))) {
	    		test.log(LogStatus.INFO, lastViewed8+" is the eighth latest book under last viewed in Read tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedEighthBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(bookAuthors.get(1))){
	    				test.log(LogStatus.INFO, "Author "+bookAuthors.get(1)+" matches with author displayed in book details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last read is "+tempAuthor+", Author displayed in book details is "+bookAuthors.get(1));
	    				errVK_1514++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1514++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1514++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed8+" is the eighth latest book under last viewed in Read tab instead of "+totalBooks.get(1));
	    		errVK_1512++;
	    	}
		    //Verify that the first watched is deleted from last viewed
		    if(lastViewed8.equals(totalBooks.get(0))) {
		    	test.log(LogStatus.FAIL, totalBooks.get(0)+" is not cleared from last read tray, FIFO order not followed");			
		    	errVK_1512++;
		    }
		    else {
		    	test.log(LogStatus.INFO, totalBooks.get(0)+" is cleared from last read tray, FIFO order is followed");
		    }
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed book 8 is not visible under last viewed in Read tab");
	    	errVK_1511++;
	     }
	    //Final verification of VK_1511
	    if(errVK_1511==0) {
	    	test.log(LogStatus.PASS, "Verify the no.of cards present under 'Last Viewed' section is Pass");
	    	if(!Utilities.setResultsKids("VK_1511", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify the no.of cards present under 'Last Viewed' section is Pass");
	    	if(!Utilities.setResultsKids("VK_1511", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    if(errVK_1512==0) {
	    	test.log(LogStatus.PASS, "Verify the cards removal order under 'Last Viewed' section when user has accessed more than 8 Ebooks is Pass");
	    	if(!Utilities.setResultsKids("VK_1511", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify the cards removal order under 'Last Viewed' section when user has accessed more than 8 Ebooks is Fail");
	    	if(!Utilities.setResultsKids("VK_1511", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    if(errVK_1514==0) {
	    	test.log(LogStatus.PASS, "Verify Book card metadata for partially read book is Pass");
	    	if(!Utilities.setResultsKids("VK_1514", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify Book card metadata for partially read book is Fail");
	    	if(!Utilities.setResultsKids("VK_1514", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    if(errVK_1515==0) {
	    	test.log(LogStatus.PASS, "Verify Book card metadata for completely read book is Pass");
	    	if(!Utilities.setResultsKids("VK_1515", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify Book card metadata for completely read book is Fail");
	    	if(!Utilities.setResultsKids("VK_1515", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    
	    //Verify the Audio icon type from API (robotani04@gmail.com, profile Jane)
	     String url_config="";
		 String apiname_config="";
		 String api_config="Config";
		 String url_LV_read=""; 
		 String api_LV_read="LV Read";
		 String apiname_LV_read="";
		 int api_totalItems_LV_read=0;
		 ArrayList<Integer> Listof_Read=new ArrayList<Integer>();	 
		 int rows=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows;rNum++){
			 apiname_config=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_config.equals(api_config)){
				 url_config=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_config);
				 Response resp_config=Utilities.requestUtilitypostOld(url_config, map);
				 resp_config.then().assertThat().statusCode(200);
				 //resp_config.prettyPrint();
				 Map<String,Integer> ott=resp_config.jsonPath().get("assets.OTT");
				 for(Map.Entry<String, Integer> m :ott.entrySet()) {	
					 if(m.getKey().equals("EBOOK_TYPE"))
						 Listof_Read.add(m.getValue());	
				 }
			 }		 
		 }
		 for(int rNum=1;rNum<=rows;rNum++){
			 apiname_LV_read=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_LV_read.equals(api_LV_read)){
				 url_LV_read=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_LV_read);
				 Response resp_LV_read=Utilities.requestUtilitypostOld(url_LV_read, map);
				 api_totalItems_LV_read=resp_LV_read.jsonPath().get("assets.items.size()");
				 for(int item=0; item<api_totalItems_LV_read; item++) {
					 int mediatype=resp_LV_read.jsonPath().get("assets.items["+item+"].mediaType");
					 String title=resp_LV_read.jsonPath().get("assets.items["+item+"].title");
					 if(Listof_Read.contains(mediatype)) 
						test.log(LogStatus.INFO, "The mediatype of "+title+ " belongs to Read type" ); 
					 else {
						test.log(LogStatus.FAIL, "The mediatype of "+title+ " does not belongs to Read" );
						errVK_1513++;
					 }
				 }
			 }
		 } 
		//Final verification of VK_1524
		if(errVK_1513==0) {
			test.log(LogStatus.PASS, "Verify the type of cards available under 'Last Viewed' section in Read Tab is Pass");
			if(!Utilities.setResultsKids("VK_1513", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available under 'Last Viewed' section in Read Tab is Fail");
			if(!Utilities.setResultsKids("VK_1513", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
	    
		homepagev2.mystuff_tab=null;
		homepagev2.lastViewedFirstBookTitle=null;
		homepagev2.lastViewedFirstBookAuthor=null;
		homepagev2.lastViewedFirstBookProgressBar=null;
		homepagev2.lastViewedSecondBookTitle=null;
		homepagev2.lastViewedSecondBookAuthor=null;
		homepagev2.lastViewedSecondBookProgressBar=null;
		homepagev2.lastViewedThirdBookTitle=null;
		homepagev2.lastViewedThirdBookAuthor=null;
		homepagev2.lastViewedThirdBookProgressBar=null;
		homepagev2.lastViewedFourthBookTitle=null;
		homepagev2.lastViewedFourthBookAuthor=null;
		homepagev2.lastViewedFourthBookProgressBar=null;
		homepagev2.lastViewedSeventhBookTitle=null;
		homepagev2.lastViewedSeventhBookAuthor=null;
		homepagev2.lastViewedSeventhBookProgressBar=null;
		homepagev2.lastViewedEighthBookTitle=null;
		homepagev2.lastViewedEighthBookAuthor=null;
		homepagev2.lastViewedEighthBookProgressBar=null;
		homepagev2.recentViewedClear=null;
		homepagev2.recentViewedClearMessage=null;
		homepagev2.recentViewedClearYes=null;
		homepagev2.recentViewedClearNo=null;
		
		

	}
	public void openReader(String book,String author) {
		 EbooksPageV2 ebookspagev21=new EbooksPageV2(driver,test);
		 boolean readerDisplayedPreview=false;
		 try {
			 if(Utilities.explicitWaitVisible(driver, ebookspagev21.previewbutton, 60)) {
				 try {
					 ebookspagev21.previewbutton.click();
					 test.log(LogStatus.INFO, "Clicked on PREVIEW button of book");
					 for(int wait=0;wait<=30;wait++) {
						 try {
							 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
							 //Add 1447
							 if(wait==30) {
								 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
								 driver.navigate().back();
								 test.log(LogStatus.INFO, "Navigated back to book details page");
							 }
						 }
						 catch(Exception e) {
							 Set<String> CHS = driver.getContextHandles();
							 for(String ch:CHS){
								if(ch.contains("WEBVIEW")){
									test.log(LogStatus.INFO, "eBook Reader is displayed for Book: "+book);
/*									if(Utilities.explicitWaitVisible(driver, ebookspagev21.downloadButton, 1)) {
										test.log(LogStatus.INFO, "Located Download button, preview is complete");
										driver.navigate().back();
									}*/
									totalBooks.add(book.trim());
									bookAuthors.add(author.trim());
									if(totalBooks.size()==9) {
										//read the last book completely
										for(int i=0;i<=7;i++) {
											Utilities.horizontalSwipe(driver);
											Thread.sleep(2000);
										}
										if(Utilities.explicitWaitVisible(driver, ebookspagev21.downloadButton, 1)) {
											test.log(LogStatus.INFO, "Located Download button, preview is complete");
											driver.navigate().back();
										}
									}
									driver.navigate().back();
									test.log(LogStatus.INFO, "Navigated back to book details page");
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
				 }
				 catch(Exception e) {
					test.log(LogStatus.INFO, "Unable to click on PREVIEW button of book: "+book);
				 }
			 }	 
			 else {
				 test.log(LogStatus.INFO, "PREVIEW button is not clickable for the book: "+book); 
			 }
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Book 1");
		 }
		 ebookspagev21.previewbutton=null;
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
