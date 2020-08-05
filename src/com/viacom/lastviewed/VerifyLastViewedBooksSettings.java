package com.viacom.lastviewed;

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
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//Author Tanisha
public class VerifyLastViewedBooksSettings extends BaseTestV2{
	
	String testName = "VerifyLastViewedBooksSettings";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedBooksSettings(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedBooksSettings");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}		
		
		//VK_1887 = Verify if Last viewed assets are specific to individual profile
		//VK_1889 = Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device
		//VK_1891 = Verify if Cards under Last viewed tray are retained when user clears device app data and logs in
		//VK_1516 = Verify 'Clear' link functionality
		//VK_1517 = Verify the functionality of 'Yes' option in clear link confirmation message
		//VK_1518 = Verify the functionality of 'No' option in clear link confirmation message
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 EbooksPageV2 ebookspagev2=new EbooksPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 
		 String book1="";
		 String book2="";
		 String lastViewed1,lastViewed2="";
		 int errVK_1887=0;
		 int errVK_1889=0;
		 int errVK_1891=0;
		 int errVK_1516=0;
		 
			//Login module 
			//homepagev2.logout();
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }

		 
		//Click on Read tab
		 homepagev2.tabClick("Read");
		//Scroll to any books 
		 for(int scroll=0;scroll<=6;scroll++) {
			 Utilities.verticalSwipe(driver); 
		 }
		 Thread.sleep(2000);
		 String bookLocs="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
		 List<WebElement> books=driver.findElementsByXPath(bookLocs);
		 try {
			 book1=books.get(0).getAttribute("text");
			 book2=books.get(1).getAttribute("text"); 
			 test.log(LogStatus.INFO, book1+":"+book2);
		 }
		 catch(Exception e) {
			test.log(LogStatus.INFO, "Failed to fetch book names"); 
		 }
		 //Launch and close book 1
		 boolean readerDisplayedPreview=false;
		 try {
			 driver.findElement(By.xpath("//android.widget.TextView[@text='"+book1+"']")).click();
			 if(Utilities.explicitWaitClickable(driver, ebookspagev2.previewbutton, 30)) {
				 try {
					 ebookspagev2.previewbutton.click();
					 test.log(LogStatus.INFO, "Clicked on PREVIEW button of book 1: "+book1);
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
							 test.log(LogStatus.INFO, "Loader is not displayed");
							 Set<String> CHS = driver.getContextHandles();
							 for(String ch:CHS){
								if(ch.contains("WEBVIEW")){
									test.log(LogStatus.INFO, "eBook Reader is displayed for Book 1");
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
					test.log(LogStatus.FAIL, "Unable to click on PREVIEW button of book 2: "+book2);
				 }
			 }	 
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Book 1");
		 }
		 //Launch and close book 2
		 driver.navigate().back();
		 test.log(LogStatus.INFO, "Navigated back to Read tab");
		 readerDisplayedPreview=false;
		 try {
			 driver.findElement(By.xpath("//android.widget.TextView[@text='"+book2+"']")).click();
			 if(Utilities.explicitWaitClickable(driver, ebookspagev2.previewbutton, 30)) {
				 try {
					 ebookspagev2.previewbutton.click();
					 test.log(LogStatus.INFO, "Clicked on PREVIEW button of book 2: "+book2);
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
							 test.log(LogStatus.INFO, "Loader is not displayed");
							 Set<String> CHS = driver.getContextHandles();
							 for(String ch:CHS){
								if(ch.contains("WEBVIEW")){
									test.log(LogStatus.INFO, "eBook Reader is displayed for Book 2");
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
					test.log(LogStatus.FAIL, "Unable to click on PREVIEW button of book 2: "+book2); 
				 }
			 }	
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Book 2");
		 }
		 
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		//Logout and Login
		homepagev2.logout();
		homepagev2.loginAfterReset(data.get("Email"),data.get("Password"));
		homepagev2.selectProfile("Jane");
		Thread.sleep(3000);
		homepagev2.tabClick("Read");
		String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
	    	if(lastViewed1.equals(book2)) {
	    		test.log(LogStatus.INFO, lastViewed1+" first latest book under last read in Read tab is retained after re-login");	    	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last viewed in Read tab instead of "+book2);	
	    		errVK_1889++;
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Read book 1 is not visible under last read in Read tab");
	    	errVK_1889++;
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
	    	if(lastViewed2.equals(book1)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest book under last viewed in Read tab is retained after re-login");
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last viewed in My Stuff tab instead of "+book1);	    	
	    		errVK_1889++;
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Read book 2 is not visible under last viewed in Watch tab");
	    	errVK_1889++;
	    }
		if(errVK_1889==0) {
			test.log(LogStatus.INFO, "Books under Last Read are retained after re-login");
			test.log(LogStatus.PASS, "Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device is PASS"); 
			if(!Utilities.setResultsKids("VK_1889", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
		}
		else {
			test.log(LogStatus.FAIL, "Books under Last Read are not retained after re-login");
			test.log(LogStatus.FAIL, "Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device is FAIL"); 
			if(!Utilities.setResultsKids("VK_1889", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
		}
		//Switch the profile
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.selectProfile("Chris");
		Thread.sleep(3000);
		homepagev2.tabClick("Read");
		//Scroll till Last Viewed
		recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
	    	if(lastViewed1.equals(book2)) {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last read in Read tab, same as previous profile");	 
	    		errVK_1887++;
	    	}
	    	else {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest book under last read in Read tab instead of "+book2);	    	
	    	}
	    }
	    else {
	    	test.log(LogStatus.INFO, "Last Read book 1 is not visible under last read in Read tab");
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
	    	if(lastViewed2.equals(book1)) {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last read in Read tab, same as previous profile");	   
	    		errVK_1887++;
	    	}
	    	else {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest book under last read in Read tab instead of "+book1);	    	
	    	}
	    }
	    else {
	    	test.log(LogStatus.INFO, "Last Read book 2 is not visible under last read in Read tab");
	    }
	    basepagev2.takeScreenshot();
	  //Final verification of VK_1887
		if(errVK_1887==0) {
			test.log(LogStatus.PASS, "Books under Last Read are different for the two profiles");
			test.log(LogStatus.PASS, "Verify if Last viewed assets are specific to individual profile is PASS"); 
			if(!Utilities.setResultsKids("VK_1887", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
		}
		else {
			test.log(LogStatus.FAIL, "Books under Last Read are same for the two profiles");
			test.log(LogStatus.FAIL, "Verify if Last viewed assets are specific to individual profile is FAIL"); 
			if(!Utilities.setResultsKids("VK_1887", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
		}

	//Clear app data
	driver.resetApp();
	test.log(LogStatus.INFO, "Performed App Reset");
	homepagev2.loginAfterReset(data.get("Email"),data.get("Password"));
	homepagev2.clearCoachCards();
	homepagev2.tabClick("My Stuff");
	homepagev2.selectProfile("Jane");
	Thread.sleep(3000);
	homepagev2.tabClick("Read");

	//Scroll till Last Viewed
	recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
	Utilities.verticalSwipeAndFind(driver, recentViewedClear);
	test.log(LogStatus.INFO, "Swiped till Last viewed tray");
	Utilities.verticalSwipe(driver);
    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
    	if(lastViewed1.equals(book2)) {
    		test.log(LogStatus.INFO, lastViewed1+" is the first latest book under under last read in Read tab, same as before clearing app data");	 
    	}
    	else {
    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest book under last read in Read tab instead of "+book2);	    	
    		errVK_1891++;
    	}
    }
    else {
    	test.log(LogStatus.FAIL, "Last read book 1 is not visible under last read in Read tab");
    	errVK_1891++;
    }
    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
    	if(lastViewed2.equals(book1)) {
    		test.log(LogStatus.INFO, lastViewed2+" is the second latest book under last viewed in Read tab, same as before clearing app data");	   
    	}
    	else {
    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest book under last viewed in Read tab instead of "+book1);	    	
    		errVK_1891++;
    	}
    }
    else {
    	test.log(LogStatus.FAIL, "Last Viewed book 2 is not visible under last viewed in Read tab");
    	errVK_1891++;
    }
    basepagev2.takeScreenshot();
  //Final verification of VK_1891
	if(errVK_1891==0) {
		test.log(LogStatus.PASS, "Books under Last Read are same after clearing app data");
		test.log(LogStatus.PASS, "Verify if Cards under Last viewed tray are retained when user clears device app data and logs in is PASS"); 
		if(!Utilities.setResultsKids("VK_1891", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	}
	else {
		test.log(LogStatus.FAIL, "Books under Last Read are not same after clearing app data");
		test.log(LogStatus.FAIL, "Verify if Cards under Last viewed tray are retained when user clears device app data and logs in is FAIL"); 		
		if(!Utilities.setResultsKids("VK_1891", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	}
			
	//Clear Last Viewed
	//homepagev2.clearLastViewed();
	
	
    //TCS relating Clear link in Watch tab
    if(Utilities.explicitWaitClickable(driver, homepagev2.recentViewedClear, 10)) {
		try {
			homepagev2.recentViewedClear.click();
			test.log(LogStatus.INFO, "Clicked on Clear link of Last Read in Read tab");
			//Verify message
			try {
				String messageUI=homepagev2.recentViewedClearMessage.getAttribute("text");
				String messageExpected="Are you sure you want to delete all last viewed items?";
				if(messageUI.equals(messageExpected)) {
					test.log(LogStatus.INFO, "Are you sure you want to delete all last viewed items? -> Is displayed");
				}
				else {
					test.log(LogStatus.FAIL, "Message displayed is-> "+messageUI);
					test.log(LogStatus.FAIL, "Expected Message is-> "+messageExpected);
					errVK_1516++;
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to verify confirmation message in popup of Last Viewed in Watch tab");
			}
			//Verify presence of YES button
			if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClearYes, 5)) {
				test.log(LogStatus.INFO, "Located YES button");
			}
			else {
				test.log(LogStatus.FAIL, "Failed to locate YES button");
				errVK_1516++;				
			}
			//Verify presence of No button
			if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClearNo, 5)) {
				test.log(LogStatus.INFO, "Located NO button");
			}
			else {
				test.log(LogStatus.FAIL, "Failed to locate NO button");
				errVK_1516++;				
			}
			//Verification of VK_1505
			if(errVK_1516==0) {
				test.log(LogStatus.PASS, "Verify 'Clear' link functionality is PASS");
				if(!Utilities.setResultsKids("VK_1516", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			else {
				test.log(LogStatus.FAIL, "Verify 'Clear' link functionality is FAIL");
				if(!Utilities.setResultsKids("VK_1516", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			//Verify No button functionality
			try {
				homepagev2.recentViewedClearNo.click();
				test.log(LogStatus.INFO, "Clicked on No button in the popup");
				if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
					test.log(LogStatus.INFO, "Verified first book is still displayed");
					test.log(LogStatus.PASS, "Verify the functionality of 'No' option in clear link confirmation message is PASS");
					if(!Utilities.setResultsKids("VK_1518", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					try {
						homepagev2.recentViewedClear.click();
						test.log(LogStatus.INFO, "Clicked on Clear link again to verify YES button functionality");
					}
					catch(Exception e) {
						test.log(LogStatus.INFO, "Failed to click on Clear link to verify YES button functionality");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Unable to locate first book under Last Read");
					test.log(LogStatus.FAIL, "Verify the functionality of 'No' option in clear link confirmation message is FAIL");	
					if(!Utilities.setResultsKids("VK_1518", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on No in the popup of Last Viewed in Watch tab");
			}
			//Verify Yes button functionality
			try {
				homepagev2.recentViewedClearYes.click();
				test.log(LogStatus.INFO, "Clicked on Yes button in the popup");
				if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClear, 10)) {
					test.log(LogStatus.FAIL, "Clear button is displayed, tray is not cleared");
					test.log(LogStatus.FAIL, "Verify the functionality of 'Yes' option in clear link confirmation message is FAIL");
					if(!Utilities.setResultsKids("VK_1517", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					BasePageV2.takeScreenshot();
				}
				else {
					test.log(LogStatus.INFO, "Clear button is not displayed, tray has been cleared");
					test.log(LogStatus.PASS, "Verify the functionality of 'Yes' option in clear link confirmation message is PASS");	
					if(!Utilities.setResultsKids("VK_1517", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}	
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on Yes in the popup of Last Viewed in Watch tab");
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Failed to click on Clear link in Last Viewed in Watch tab");
		}
	}
	else {
		test.log(LogStatus.FAIL, "Clear link is not clickable in Last Viewed in Watch tab");
	}
	    
		homepagev2.mystuff_tab=null;
		homepagev2.lastViewedFirstBookTitle=null;
		homepagev2.lastViewedSecondBookTitle=null;
		homepagev2.recentViewedClear=null;
		homepagev2.recentViewedClearMessage=null;
		homepagev2.recentViewedClearYes=null;
		homepagev2.recentViewedClearNo=null;
		
		

	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
