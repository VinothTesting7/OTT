package com.viacom.ebook;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class VerifyYouMightAlsoLikeFunctionality_P2 extends BaseTestV2 {
	
	String testName = "VerifyYouMightAlsoLikeFunctionality_P2";


  int bookFirstpageNumber = 0;
		
		@Test(dataProvider = "getData")
		public void verifyYouMightAlsoLikeFunctionality_P2(Hashtable<String, String> data) throws Exception{	
		test = rep.startTest("VerifyYouMightAlsoLikeFunctionality_P2");
		test.log(LogStatus.INFO, "Starting the test for Verifying the VerifyYouMightAlsoLikeFunctionality_P2 : "+VootConstants.DEVICE_NAME);
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
		HomePageV2 homePage = new HomePageV2(driver, test);
		SearchPageV2 searchPage = new SearchPageV2(driver, test);
			String un=data.get("Email");
			String pwd=data.get("Password");
		    String bookName = data.get("bookName");
			
		    
			HomePageV2.login(un, pwd);
	
		// VK_649 - Verify 'You might also like' overlay UI:
		// VK_1916 - Verify the cards present in Reader matches with the cards present in programme info:	
		// VK_620 - Verify the cards present under More from Author post tapping on Up arrow button
			
			
		
			// VK_649 - Verify 'You might also like' overlay UI:
			
			// clear Downloads
			try {
				downloadPage.deleteAllDownloadsAndClickMyStuffTab();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// click on Read tab in home page
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
			        	Utilities.verticalSwipe(driver);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    	// click on download link to downloads the book
			    	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
			    		watchpagev2.downloadEpisodesLink.click();
			    		test.log(LogStatus.INFO, "click on book download link in book info page");
			    		if(Utilities.explicitWaitVisible(driver, readPage.downloadedBookText, 120)) {
			    			test.log(LogStatus.INFO, "Expected book downloaded completed");
			    			// scroll and click read button in reader info page
			    			Utilities.verticalSwipeDown(driver);
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
			    	    				if(Utilities.explicitWaitClickable(driver, readPage.readAgainBtn, 4)) {
			    	    					break;
			    	    					
			    	    				}
			    	    				
			    	    				if(i == 40) {
			    	    					BasePageV2.reportFail("Not displayed Read again button in read book last page");
			    	    				}
			    	    				
			    	    			}
			    	    			
			    	    			// verifying the Ui of Read again page 
			    	    			
			    	    			int errorUi = 0;
			    	    			
			    	    			if(Utilities.explicitWaitClickable(driver, readPage.readAgainBtn, 10)) {
			    	    				test.log(LogStatus.INFO, "Displayed Read Again button ");
			    	    				if(Utilities.explicitWaitVisible(driver, readPage.readAgainImgCard, 10)) {
			    	    					test.log(LogStatus.INFO, "Displayed Content thumbnail");
			    	    					if(Utilities.explicitWaitVisible(driver, readPage.readAgainCloseBtn, 10)) {
			    	    						test.log(LogStatus.INFO, "Displayed Cloase button in read Again page");
			    	    						
			    	    						if(Utilities.explicitWaitVisible(driver, readPage.readAgainYouMightLikeText, 10)) {
			    	    							test.log(LogStatus.INFO, "Displayed You might Like text title in read agin button contain page");
			    	    							
			    	    							if(Utilities.explicitWaitVisible(driver, readPage.readAgainImgTitle, 10)) {
			    	    								test.log(LogStatus.INFO, "Displayed image card title in read agin button contain page");
			    	    							}else {
			    	    								errorUi++;
							    	    				test.log(LogStatus.FAIL, "Not displayed image card title in read again button contain page");
							    	    				BasePageV2.takeScreenshot();
			    	    							}
			    	    							
			    	    							
			    	    						}else {
			    	    							errorUi++;
						    	    				test.log(LogStatus.FAIL, "Not displayed You might Like text title in read again button contain page");
						    	    				BasePageV2.takeScreenshot();
			    	    						}
			    	    						
			    	    						
			    	    					}else {
			    	    						errorUi++;
					    	    				test.log(LogStatus.FAIL, "Not displayed close button in read again button contain page");
					    	    				BasePageV2.takeScreenshot();
			    	    					}
			    	    				}else {
			    	    					errorUi++;
				    	    				test.log(LogStatus.FAIL, "Not displayed Content Thumbnail");
				    	    				BasePageV2.takeScreenshot();
			    	    				}
			    	    					
			    	    				
			    	    			}else {
			    	    				errorUi++;
			    	    				test.log(LogStatus.FAIL, "Not displayed Read Again button");
			    	    				BasePageV2.takeScreenshot();
			    	    			}
			    	    			
			    	    			if(errorUi == 0 ) {
			    	    				test.log(LogStatus.PASS, "Verify 'You might also like' overlay UI:");
			    						if(!Utilities.setResultsKids("VK_649", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			    						
			    						driver.navigate().back();
			    						driver.navigate().back();
			    						
			    	    			}else {
			    	    				test.log(LogStatus.FAIL, "Verify 'You might also like' overlay UI:");
			    						if(!Utilities.setResultsKids("VK_649", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
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
		    	
		        
		        
			// VK_1916 - Verify the cards present in Reader matches with the cards present in programme info:
		        
		        ArrayList<String> relatedList = new ArrayList<String>();
		        ArrayList<String> UpArrowrelatedList = new ArrayList<String>();
		        
		        // scroll till Related Ebooks Tray 
		        try {
					Utilities.verticalSwipe(driver, bookPage.bookDetailPageRelatedBooksTray);
					Utilities.verticalSwipe(driver);
					String relatedBookfirst = bookPage.relatedEbookFirstCardtitle.getText().toString().trim();
					String relatedBookSecond = bookPage.relatedEbookSecondCardtitle.getText().toString().trim();
					relatedList.add(relatedBookfirst);
					relatedList.add(relatedBookSecond);
					
					// Scroll down to click Read button
					Utilities.verticalSwipeDown(driver);
					Utilities.verticalSwipeDown(driver);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
		       
		        
		        // click on Read button to read book
		        if(Utilities.explicitWaitClickable(driver, bookPage.readBtnBook, 50)) {
    				bookPage.readBtnBook.click();
    				test.log(LogStatus.INFO, "click on Read button in Read book inf page");
    				Thread.sleep(3000);
    				try {
    		    		readPage.dismissReaderCoachCards();
    		    		Thread.sleep(300);
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    	    		try {
						Utilities.tap(driver);
						Utilities.tap(driver);
						
						if(Utilities.explicitWaitClickable(driver, readPage.bookReaderUpArrow, 50)) {
							readPage.bookReaderUpArrow.click();
							test.log(LogStatus.INFO, "clicked on up Arrow button on reader page");
							Thread.sleep(5000);
							try {
								String upArrowRelatedbookFirsTitle = bookPage.upArrowrelatedEbookFirstCardtitle.getText().toString().trim();
								String upArrowRelatedbookSecondTitle = bookPage.upArrowrelatedEbookSecondCardtitle.getText().toString().trim();
								
								UpArrowrelatedList.add(upArrowRelatedbookFirsTitle);
								UpArrowrelatedList.add(upArrowRelatedbookSecondTitle);
								
								driver.navigate().back();
								driver.navigate().back();
								driver.navigate().back();
								driver.navigate().back();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							if(relatedList.equals(UpArrowrelatedList)) {
								test.log(LogStatus.FAIL, "Verify the cards present in Reader matches with the cards present in programme info:");
	    						if(!Utilities.setResultsKids("VK_1916", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	    						
							}else {
								test.log(LogStatus.PASS, "Verify the cards present in Reader matches with the cards present in programme info:");
	    						if(!Utilities.setResultsKids("VK_1916", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	    						
							}
							
							
						}else {
							BasePageV2.reportFail("Failed to click on up Arrow button on reader page");
						}
						
						
						
						
						
    	    			
					} catch (Exception e) {
						e.printStackTrace();
					}
    				
    				
    				
    				
    				
		        	
		        }else BasePageV2.reportFail("Not displayed Read button in book info page / Not click");
		        
			
		        
		     
		        
		        
		        
			// VK_620 - Verify the cards present under More from Author post tapping on Up arrow button
			
		        // click on Search icon in home page
		        if (Utilities.explicitWaitVisible(driver, searchPage.Search, 50))
		        	searchPage.Search.click();
				else
					BasePageV2.reportFail("Search tab not displayed");

				if (Utilities.explicitWaitVisible(driver, homePage.searchPage, 20))
					test.log(LogStatus.INFO, "Search Page is displayed");
				else
					BasePageV2.reportFail("Search Page is not displayed");

				if (Utilities.explicitWaitVisible(driver, searchPage.searchTextBox, 50)) {
					searchPage.searchTextBox.clear();
					searchPage.searchTextBox.sendKeys(bookName);
				}

				
				try {
					driver.hideKeyboard();
					driver.findElement(By.xpath("//android.widget.TextView[@text='"+bookName+"' and @resource-id='com.viacom18.vootkids:id/grid_title']/..")).click();
					test.log(LogStatus.INFO, "clicked on '"+bookName+"' book in search page");
					
					if(Utilities.explicitWaitClickable(driver, readPage.tryPreviewReadButton, 50)) {
						readPage.tryPreviewReadButton.click();
						test.log(LogStatus.INFO, "clicked on TRY button in book info page");
						
						try {
	    		    		readPage.dismissReaderCoachCards();
	    		    		Thread.sleep(300);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
						
						
						// double click on Book first page
						try {
							Utilities.tap(driver);
							Utilities.tap(driver);
							// click on Reader up Arrow 
							if(Utilities.explicitWaitClickable(driver, readPage.bookReaderUpArrow, 50)) {
								readPage.bookReaderUpArrow.click();
								test.log(LogStatus.INFO, "click on Up Arrow button ");
								// click on More from Author Tab
							    if(Utilities.explicitWaitClickable(driver, readPage.bookReaderTabMoreFromAuthor, 50)) {
							    	readPage.bookReaderTabMoreFromAuthor.click();
							    	test.log(LogStatus.INFO, "Clicked on More from Author tab");
							    	if(Utilities.explicitWaitVisible(driver, readPage.nothingHere, 10)) {
							    		test.log(LogStatus.PASS, "Verify the cards present under More from Author post tapping on Up arrow button");
			    						if(!Utilities.setResultsKids("VK_620", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							    	}else {
							    		test.log(LogStatus.FAIL, "Verify the cards present under More from Author post tapping on Up arrow button");
			    						if(!Utilities.setResultsKids("VK_620", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							    	}
						
							    }else {
							    	BasePageV2.reportFail("Failed to click on More from Author tab / Not displayed");
							    }
	
							}else {
								BasePageV2.reportFail("Failed to click on Reader Up arrow / Not displayed");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}else {
						BasePageV2.reportFail("Failed to click on TRY button inbook info page");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			    
			
			
			
			
			
			
			
			
			
		}
		
		
		@DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
		}

}
