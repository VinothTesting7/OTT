package com.viacom.uinavigation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

//AuthorName=Bhaskar

public class VerifyEbooksCardNavigations extends BaseTestV2
{	
	
	// VK_618---Verify the card navigation when tapped on the cards present under Related tray		
	//VK_623 -------Verify the card navigation when tapped on the cards present under More from Author 
	//VK_627----Verify the card navigation when tapped on the cards present under Daily picks
	
	String	firstBookRelatedTab="";
	String testName = "VerifyEbooksCardNavigations";
	private Object bookNameBefore;
	@Test(dataProvider = "getData")
	public void VerifyEbooksCardNavigations(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("VerifyEbooksCardNavigations");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
					
		         //Launch Voot kids App();	 			
				 launchApp();
				 HomePageV2 homepagev2=new HomePageV2(driver,test);
				 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
				 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
				 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
				 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
				 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
				 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
				 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
				 BasePageV2  basepagev2=new BasePageV2(driver,test);
				 
				//login with valid credentials
				 homepagev2.login(data.get("Email"),data.get("Password"));
				 
				 //delete All Downloads
				  downloadsv2.deleteAllDownloads();
				 
				 String bookNameBefore="" ,bookNameAfter="", bookname="",bookname1="";
				 boolean downloadCompleted=false , bookDownload=false ,readerDisplayedPreview=false ,readerDisplayedRead=false;
	
						
						
//VK_623 -------Verify the card navigation when tapped on the cards present under More from Author 
				
		 
		             homepagev2.tabClick("Read");	             
		             Thread.sleep(4000);				 
		             if(Utilities.explicitWaitVisible(driver, readpagev2.firstBookNameInCarousal, 10))
					 {
							    bookname=readpagev2.booknameincarouselb.getAttribute("text");							    
							    bookname1=bookname.replaceAll("[^a-zA-Z0-9]","");
							   //bookNameBefore=homepagev2.convertCamelCase(bookNameBefore);
							    test.log(LogStatus.INFO, "sucessesfully retrieved bookname from Read Carousal        "+bookname1);
							  try{
								     readpagev2.booknameincarouselb.click();
								     test.log(LogStatus.INFO, "Clicked on the Book from Read Carousal");									     
							     }catch(Exception e)
							     {
							    	  test.log(LogStatus.FAIL, "failed to click on book in read carousel");
							    	 basepagev2.takeScreenshot();
							     }
					 }
					 else
					 {
						       test.log(LogStatus.FAIL, "failed to retrieve  bookname from Read Carousal");
						       basepagev2.takeScreenshot();
					 }
					 
																
			if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
			{
				 try {
					  readpagev2.previewButton.click();
					  test.log(LogStatus.INFO, "Clicked on PREVIEW button");
						 for(int wait=0;wait<=30;wait++) //for loop 1 starting
						  {
						     try
						      {
								 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
								 //Add 1447
								    if(wait==30)
								    {
									 
									 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Preview' button is Fail");
									 BasePageV2.takeScreenshot();
									 driver.navigate().back();
									 test.log(LogStatus.INFO, "Navigated back to book details page");
								    }
							 
						       } catch(Exception e)
						       {
								 test.log(LogStatus.INFO, "Loader is not displayed");
								 Set<String> CHS = driver.getContextHandles();								 
								 for(String ch:CHS)
								 {
									if(ch.contains("WEBVIEW"))
									{
										test.log(LogStatus.INFO, "eBook Reader is displayed");
								
										test.log(LogStatus.INFO, "Verify the behavior when tapping on 'Preview' button ");
										readerDisplayedPreview=true;
										break;
									}
								 }  
							   }	
							 if(readerDisplayedPreview==true)
								 break;
							 else
								 Thread.sleep(1000);
						 }//for loop1 ending						 
					  } catch(Exception e)
				      {					
						test.log(LogStatus.FAIL, "Unable to click on PREVIEW button");
						 BasePageV2.takeScreenshot(); 
				      }
				 				 
			   }// preview if condition is ending
			
		    	//for removing coach cards
			     readpagev2.dismissReaderCoachCards();
						
					   try {
							  // double tap  reader pages
							   Thread.sleep(2000);
							    Utilities.tap(driver);
							    Utilities.tap(driver);
								test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
			  
						    }catch(Exception e)
						    {
						    	test.log(LogStatus.FAIL, "failed to do Double tapon  screen to launch reader controls");
						    	 BasePageV2.takeScreenshot();
						    }		
									                      
					/*   // refreshApp
						Thread.sleep(2000);
						driver.runAppInBackground(Duration.ofSeconds(3));
						test.log(LogStatus.INFO, "Put app to background for 3 seconds");
						driver.currentActivity();
						*/
				           //Tap on Up Arrow				
				    if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderUpArrow, 10))
				    {
							test.log(LogStatus.INFO, "Reader controls have launched");	
							try {
									 readpagev2.bookReaderUpArrow.click();										
									 test.log(LogStatus.INFO, "Clicked on Up Arrow button");
									 
							     }catch(Exception e)
						     	{
							    	 test.log(LogStatus.FAIL, " failed to Click on Up Arrow button");
							    	 BasePageV2.takeScreenshot();
							    }
				   
				    }
				    else
				    {
				    	 test.log(LogStatus.FAIL, "Reader controls have not launched");
				    	 BasePageV2.takeScreenshot();
				    }
						
				    
				       //do vertical swipe till to get morefromAuthor tab				    
				        Utilities.verticalSwipeReverse(driver);
					    Utilities.verticalSwipeReverse(driver);

		            //more fromAuthor tab should be displayed	    
		             if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderTabMoreFromAuthor, 20))
		             {
			                     							
					            	   readpagev2.bookReaderTabMoreFromAuthor.click();
						               test.log(LogStatus.INFO, " clicked on MoreFromAuthortab ");
	                 }
		             else
				     {
		            	           test.log(LogStatus.FAIL, " failed to click on MoreFromAuthortab"); 
			                        basepagev2.takeScreenshot(); 
				     }	
	
				    if(Utilities.explicitWaitClickable(driver, readpagev2.morefromauthorrelatedTab1stItemb, 20))
				    {													      
				        	  readpagev2.morefromauthorrelatedTab1stItemb.click();						        	
				        	  test.log(LogStatus.INFO, "Clicked on first book under morefrom tab");
					          Thread.sleep(3000);		    
				    }
				    else
				    {
		    	            test.log(LogStatus.FAIL, "Failed to click on first book under mefrom tab");
				            basepagev2.takeScreenshot();
				    }
			
				   if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 20))
				   {									      
			                   test.log(LogStatus.PASS, "after Tapping on the cards from moreFrom author section then it is  navigated to respective Ebook detail screen testCase:VK_623");
		                       if(!Utilities.setResultsKids("VK_623", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");					      															
				   }						
				   else							
				   {
							  test.log(LogStatus.FAIL, "after Tapping on the cards from moreFrom author  section then it is not   not  navigated to respective Ebook detail screen	testCase:VK_623");												
							  if(!Utilities.setResultsKids("VK_623", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							   basepagev2.takeScreenshot();
				   }
				   
				
//VK_627----Verify the card navigation when tapped on the cards present under Daily picks	
					
				  if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
					{
					 try {
						  readpagev2.previewButton.click();
						  test.log(LogStatus.INFO, "Clicked on PREVIEW button");
							 for(int wait=0;wait<=30;wait++) //for loop 1 starting
							  {
							     try
							      {
									 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
									 //Add 1447
									    if(wait==30)
									    {
										 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
										
										 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Preview' button is Fail");
										 BasePageV2.takeScreenshot();
										 driver.navigate().back();
										 test.log(LogStatus.INFO, "Navigated back to book details page");
									    }
								 
							       } catch(Exception e)
							       {
									 test.log(LogStatus.INFO, "Loader is not displayed");
									 Set<String> CHS = driver.getContextHandles();								 
									 for(String ch:CHS)
									 {
										if(ch.contains("WEBVIEW"))
										{
											test.log(LogStatus.INFO, "eBook Reader is displayed");
									
											test.log(LogStatus.INFO, "Verify the behavior when tapping on 'Preview' button is Pass");
											readerDisplayedPreview=true;
											break;
										}
									 }  
								   }	
								 if(readerDisplayedPreview==true)
									 break;
								 else
									 Thread.sleep(1000);
							 }//for loop1 ending						 
						  } catch(Exception e)
					      {					
							test.log(LogStatus.FAIL, "Unable to click on PREVIEW button"); 
							 BasePageV2.takeScreenshot();
					      }
			
					 
				   }//preview if condition is ending
				 
			         
						  try {
							  // double tapping reader pages
							   Thread.sleep(2000);
							    Utilities.tap(driver);
							    Utilities.tap(driver);
								test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
			  
						        }catch(Exception e)
						        {
						    	    test.log(LogStatus.FAIL, "failed to do Double tapon  screen to launch reader controls");
						    	     BasePageV2.takeScreenshot();
						         }	
						  
						/*  // refreshApp
							Thread.sleep(2000);
							driver.runAppInBackground(Duration.ofSeconds(3));
							test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							driver.currentActivity();
							*/

						    if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderUpArrow, 10))
						    {
									test.log(LogStatus.INFO, "Reader controls have launched");	
									try {
											 readpagev2.bookReaderUpArrow.click();										
											 test.log(LogStatus.INFO, "Clicked on Up Arrow button");
											 
									     }catch(Exception e)
								     	{
									    	 test.log(LogStatus.FAIL, " failed to Click on Up Arrow button");
									    	 BasePageV2.takeScreenshot();
									    }
						   
						    }
						    else
						    {
						    	 test.log(LogStatus.FAIL, "Reader controls have not launched");
						    	 BasePageV2.takeScreenshot();
						    }
								
						    
						    //do vertical swipe till to get morefromAuthor tab
						    
						        Utilities.verticalSwipeReverse(driver);
							    Utilities.verticalSwipeReverse(driver);

			
						         //Daily picks tab should be displayed
							    							
						            if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderTabDailyPicks, 20))
						             {
						            	       readpagev2.bookReaderTabDailyPicks.click();
							                   test.log(LogStatus.INFO, " clicked on daily picks tab ");
						            }
						            else
						            {
						            	        test.log(LogStatus.FAIL, " failed to click on daily picks tab"); 
							                    basepagev2.takeScreenshot(); 
						             }
					
							
						//click on card under daily picks tab							
					    if(Utilities.explicitWaitClickable(driver, readpagev2.morefromauthorrelatedTab1stItemb, 20))
					    {														       
							        	readpagev2.morefromauthorrelatedTab1stItemb.click();							        	
							        	 test.log(LogStatus.INFO, "Clicked on first book underdaily picks tab");								          
				        }
					    else
					    {
					    	            test.log(LogStatus.FAIL, "Failed to click on first book under daily picks tab");
							            basepagev2.takeScreenshot();		
					    }
				
					   if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 20))
					   {									      
						          test.log(LogStatus.PASS, "VK_627----after Tapping on the cards from daily picks section then it is  navigated to respective Ebook detail screen ");
					              if(!Utilities.setResultsKids("VK_627", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");				      																
					   }						
					   else							
					   {
						           test.log(LogStatus.FAIL, "VK_627---after Tapping on the cards from daily picks section then it is not   not  navigated to respective Ebook detail screen	");										
						          if(!Utilities.setResultsKids("VK_627", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						          basepagev2.takeScreenshot();		
					   }
			
						
						
// VK_618---Verify the card navigation when tapped on the cards present under Related tab  tray
					   
						
						if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
						{
							 try {
								  readpagev2.previewButton.click();
								  test.log(LogStatus.INFO, "Clicked on PREVIEW button");
									 for(int wait=0;wait<=30;wait++) //for loop 1 starting
									  {
									     try
									      {
											 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
											 //Add 1447
											    if(wait==30)
											    {
												 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
												 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Preview' button is Fail");
												 BasePageV2.takeScreenshot();
												 driver.navigate().back();
												 test.log(LogStatus.INFO, "Navigated back to book details page");
											    }
										 
									       } catch(Exception e)
									       {
											 test.log(LogStatus.INFO, "Loader is not displayed");
											 Set<String> CHS = driver.getContextHandles();								 
											 for(String ch:CHS)
											 {
												if(ch.contains("WEBVIEW"))
												{
													test.log(LogStatus.INFO, "eBook Reader is displayed");
											
													test.log(LogStatus.INFO, "Verify the behavior when tapping on 'Preview' button is Pass");
													readerDisplayedPreview=true;
													break;
												}
											 }  
										   }	
										 if(readerDisplayedPreview==true)
											 break;
										 else
											 Thread.sleep(1000);
									 }//for loop1 ending						 
								  } catch(Exception e)
							      {					
									test.log(LogStatus.FAIL, "Unable to click on PREVIEW button"); 
									 basepagev2.takeScreenshot();	
							      }
							 
						   }// preview if condition is ending 
							
								 
						
						
				         
						  try {
							  // double tapping reader pages
							   Thread.sleep(3000);
							    Utilities.tap(driver);
							    Utilities.tap(driver);
								test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
						
						     }catch(Exception e)
						     {
						     	test.log(LogStatus.FAIL, "failed to do Double tapon  screen to launch reader controls");
						 	    BasePageV2.takeScreenshot();
						     }	
						  
								 /* // refreshApp
									Thread.sleep(2000);
									driver.runAppInBackground(Duration.ofSeconds(3));
									test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									driver.currentActivity();*/
									
								    if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderUpArrow, 10))
								    {
											test.log(LogStatus.INFO, "Reader controls have launched");	
											try {
													 readpagev2.bookReaderUpArrow.click();										
													 test.log(LogStatus.INFO, "Clicked on Up Arrow button");
													 
											     }catch(Exception e)
										     	{
											    	 test.log(LogStatus.FAIL, " failed to Click on Up Arrow button");						
											    	 BasePageV2.takeScreenshot();
											    }
								   
								    }
								    else
								    {
								    	 test.log(LogStatus.FAIL, "Reader controls have not launched");
								    	 BasePageV2.takeScreenshot();
								    }
								    								    
					            //click on related tab							 
					            if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderTabRelated, 10))
					             {						                   										
						                   readpagev2.bookReaderTabRelated.click();
						                   test.log(LogStatus.INFO, " clicked on Related tab ");
					            }
					            else
				            	{
					            	        test.log(LogStatus.FAIL, " failed to click on Related tab "); 
					            	        BasePageV2.takeScreenshot();
				            	}
						  
							    if(Utilities.explicitWaitClickable(driver, readpagev2.readerRelatedTab1stItemb, 10))
							    {																      
									       readpagev2.readerRelatedTab1stItemb.click();	
									        	
									        test.log(LogStatus.INFO, "Clicked on first book under Related tab");										  				    
							    }
							    else
							    {
							    	       test.log(LogStatus.FAIL, "Failed to click on first book under Related tab");
							    	       basepagev2.takeScreenshot();
							    }
						
							   if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 10))
							   {									      
								            test.log(LogStatus.PASS, "VK_618-----after Tapping on the cards from Related section then it is  navigated to respective Ebook detail screen 	");
							                if(!Utilities.setResultsKids("VK_618", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");					      															
							   }						
							    else							
							   {
								           test.log(LogStatus.FAIL, " VK_618-----after Tapping on the cards from Related section then it is not   not  navigated to respective Ebook detail screen 	");															
								            if(!Utilities.setResultsKids("VK_618", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								           basepagev2.takeScreenshot();
							    }
							   
    }	
	
	@DataProvider
	public Object[][] getData()
	{
		return DataUtil.getData(testName,xls);						
	          
	
	}
}	
