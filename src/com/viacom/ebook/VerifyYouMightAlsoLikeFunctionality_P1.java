package com.viacom.ebook;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;



public class VerifyYouMightAlsoLikeFunctionality_P1 extends BaseTestV2 {
	
String testName = "VerifyYouMightAlsoLikeFunctionality_P1";


int bookFirstpageNumber = 0 , readAgainFirstPage = 0;
	
	@Test(dataProvider = "getData")
	public void VerifyYouMightAlsoLikeFunctionality(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("VerifyYouMightAlsoLikeFunctionality_P1");
	test.log(LogStatus.INFO, "Starting the test for Verifying the VerifyYouMightAlsoLikeFunctionality_P1 : "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	WatchPageV2 watchpagev2 = new WatchPageV2(driver, test);
	ReadPageV2 readPage = new ReadPageV2(driver, test);
	BooksPageV2 bookPage = new BooksPageV2(driver, test);
	DownloadsPageV2 downloadPage = new DownloadsPageV2(driver, test);
		String un=data.get("Email");
		String pwd=data.get("Password");
	  
	    
		HomePageV2.login(un, pwd);
		
		// VK_648 - Verify the functionality of 'You might also like':
		// VK_651 - Verify Ebook card tap functionality in 'You Might Also Like' screen:
		// VK_652 - Verify the functionality of Read Again button in Reader player - You might also like overlay:
		
	
		
		

		
		
		
		// clear Downloads
		try {
			downloadPage.deleteAllDownloadsAndClickMyStuffTab();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// click on Read tab to download book
    	try {
    		HomePageV2.tabClick("Read");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
	    	watchpagev2.cardinwatchcarousel.click();
	    	test.log(LogStatus.INFO, "click on carousal in Read page");

            // click on Try Button in book reader page
	    	if(Utilities.explicitWaitClickableNew(driver, readPage.previewButton, 60)) {
	    		readPage.previewButton.click();
	    		test.log(LogStatus.INFO, "click on Try button in read page");
	    		Thread.sleep(10000);
	    		try {
		    		readPage.dismissReaderCoachCards();
		    		Thread.sleep(300);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		
	    		
	    		try {
	    			
	    			Utilities.tap(driver);
	    			Utilities.tap(driver);
	    			Thread.sleep(300);
	    			String booksize = bookPage.readerPageNumber.getText().toString().trim();
	    			test.log(LogStatus.INFO, "Book page count : " + booksize);
	    			String[] bookNum = booksize.split("of");
	    			String bookcoun = bookNum[1].toString().trim();
	    			int NumberOFPages = Integer.parseInt(bookcoun);
	    			test.log(LogStatus.INFO, "Number of Pages Contains in the book is : " + NumberOFPages);
	    		
	    			if(NumberOFPages > 1) {
	    				test.log(LogStatus.SKIP, "Skipped more than 1 ");
	    				throw new SkipException("Skipping the test because Book contains more Than 40 pages");
	    				
	    			}
	    			else {
	    				test.log(LogStatus.INFO, "Else Block");
	    				driver.navigate().back();
	    			}
	    			
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		
	    		
	    		
	    		for(int i = 0 ; i <= 80 ; i++ ) {
	    			if(Utilities.explicitWaitVisible(driver, readPage.downloadInPopup, 10)) {
	    				if(Utilities.explicitWaitVisible(driver, readPage.downloadInPopupClose, 10)) {
	    					test.log(LogStatus.PASS, "Verify the functionality of 'You might also like':");
							if(!Utilities.setResultsKids("VK_648", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							
							driver.navigate().back();
							driver.navigate().back();
							driver.navigate().back();
	    				}else {
	    					test.log(LogStatus.PASS, "Verify the functionality of 'You might also like':");
							if(!Utilities.setResultsKids("VK_648", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							driver.navigate().back();
							driver.navigate().back();
							driver.navigate().back();
	    				}
	    				break;
	    			}
	    			Utilities.horizontalSwipe(driver);
	    			
	    		}

	    		
	    	}else {
	    		BasePageV2.reportFail("Not displayed TRY button in read page / Not click");
	    	}

	    	
	    	
        }else {
        	test.log(LogStatus.FAIL, "Not displayed book carousal in Book page / Not click");
        	BasePageV2.takeScreenshot();
        }
        
        
     
    	
		// VK_652 - Verify the functionality of Read Again button in Reader player - You might also like overlay:
		
        try {
    		HomePageV2.tabClick("Read");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
	    	watchpagev2.cardinwatchcarousel.click();
	    	test.log(LogStatus.INFO, "click on carousal in Read page");
	    	Thread.sleep(5000);
	        try {
				
	        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
	        	
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	// click on download link to downloads the book
	    	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
	    		watchpagev2.downloadEpisodesLink.click();
	    		test.log(LogStatus.INFO, "click on book download link in book info page");
	    		if(Utilities.explicitWaitVisible(driver, readPage.downloadedBookText, 120)) {
	    			test.log(LogStatus.INFO, "Expected book downloaded completed");
	    			// scroll and click read button in reader info page
	    			Utilities.verticalSwipeDown(driver);
	    			if(Utilities.explicitWaitClickable(driver, bookPage.readBtnBook, 50)) {
	    				bookPage.readBtnBook.click();
	    				test.log(LogStatus.INFO, "click on Read button in Read book inf page");
	    				
	    				try {
	    		    		readPage.dismissReaderCoachCards();
	    		    		Thread.sleep(300);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    	    		
	    	    		
	    	    		try {
	    	    			
	    	    			Utilities.tap(driver);
	    	    			Utilities.tap(driver);
	    	    			Thread.sleep(300);
	    	    			String booksize = bookPage.readerPageNumber.getText().toString().trim();
	    	    			test.log(LogStatus.INFO, "Book page count : " + booksize);
	    	    			String[] bookNum = booksize.split("of");
	    	    			String bookPage1 = bookNum[0].toString().trim();
	    	    			String bookcoun = bookNum[1].toString().trim();
	    	    			 bookFirstpageNumber = Integer.parseInt(bookPage1);
	    	    			int NumberOFPages = Integer.parseInt(bookcoun);
	    	    			test.log(LogStatus.INFO, "Number of Pages Contains in the book is : " + NumberOFPages);
	    	    		
	    	    			if(NumberOFPages >= 40) {
	    	    				
	    	    				throw new SkipException("Skipping the test because Book contains more Than 40 pages");
	    	    				
	    	    			}
	    	    			else {
	    	    				
	    	    				driver.navigate().back();
	    	    			}
	    	    			
	    	    			// Swipe the Pages till end 
	    	    			for(int i = 0 ; i < 40 ; i++) {
	    	    				Utilities.horizontalSwipe(driver);
	    	    				if(Utilities.explicitWaitClickable(driver, readPage.readAgainBtn, 10)) {
	    	    					readPage.readAgainBtn.click();
	    	    					test.log(LogStatus.INFO, "Clicked on Read Again button");
	    	    					Thread.sleep(5000);
	    	    					
	    	    					try {
	    	    						Utilities.tap(driver);
	    		    	    			Utilities.tap(driver);
	    		    	    			Thread.sleep(300);
	    		    	    			String booksize1 = bookPage.readerPageNumber.getText().toString().trim();
	    		    	    			test.log(LogStatus.INFO, "Book page count : " + booksize1);
	    		    	    			String[] bookFirst = booksize1.split("of");
	    		    	    			String againPage = bookFirst[0].toString().trim();
	    		    	    			 readAgainFirstPage = Integer.parseInt(againPage); 
	    		    	    			 if(readAgainFirstPage == 1) {
	 	    	    						test.log(LogStatus.PASS, "Verify the functionality of 'You might also like':");
	 	    								if(!Utilities.setResultsKids("VK_652", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	 	    	    					}else {
	 	    	    						test.log(LogStatus.FAIL, "Verify the functionality of 'You might also like':");
	 	    								if(!Utilities.setResultsKids("VK_652", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	 	    	    					}
	    		    	    			 
									} catch (Exception e) {
										e.printStackTrace();
									}
	    	    					
	    	    					break;
	    	    				}
	    	    				
	    	    				if(i == 40) {
	    	    					BasePageV2.reportFail("Not displayed Read again button in read book last page");
	    	    				}
	    	    				
	    	    			}
	    	    			
	    	    			
	    	    			
	    	    			
	    	    		} catch (Exception e) {
	    	    			e.printStackTrace();
	    	    		}
	    	    		
	    	    		
	    				
	    				
	    			}else BasePageV2.reportFail("Not displayed Read button in book info page");

	    		}else {
	    			BasePageV2.reportFail("Expected book not download completely");
	    		}
	    		
	    	}else {
	    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
	    		BasePageV2.takeScreenshot();
	    	}
	    	
	    	
        }else {
        	test.log(LogStatus.FAIL, "Not displayed book carousal in Book page / Not click");
        	BasePageV2.takeScreenshot();
        }
    	
    	
        
     // VK_651 - Verify Ebook card tap functionality in 'You Might Also Like' screen: 
        
        for(int i = 0 ; i < 40 ; i++) {
			Utilities.horizontalSwipe(driver);
			if(Utilities.explicitWaitClickable(driver, readPage.readAgainImgCard, 10)) {
				readPage.readAgainImgCard.click();
				test.log(LogStatus.INFO, "Clicked on Read Again page img");
				Thread.sleep(5000);
			
				if(Utilities.explicitWaitVisible(driver, readPage.previewButton, 30)) {
					test.log(LogStatus.PASS, "Verify Ebook card tap functionality in 'You Might Also Like' screen:");
					if(!Utilities.setResultsKids("VK_651", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}else {
					test.log(LogStatus.FAIL, "Verify Ebook card tap functionality in 'You Might Also Like' screen:");
					if(!Utilities.setResultsKids("VK_651", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}
				
				
				break;
			}
			
			if(i == 40) {
				BasePageV2.reportFail("Not displayed Read again button in read book last page");
			}
			
		}
		

        
        // click on read book 
        
       // clear downloads 

        driver.navigate().back();
        
        downloadPage.deleteAllDownloadsAndClickMyStuffTab();
        
        
        
        
		
		
		
		
	}
	
	 @DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					

		}	
	

}
