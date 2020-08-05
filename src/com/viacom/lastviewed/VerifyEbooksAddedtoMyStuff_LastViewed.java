package com.viacom.lastviewed;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

//AuthorName=Bhaskar
public class VerifyEbooksAddedtoMyStuff_LastViewed extends BaseTestV2
{
	// VK_1668-----Verify if the E book cards are added to the Last viewed tray if user Opens and closes an Ebook	
    //VK_1669-----Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook partially 
	//VK_1670-----Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook Completely	
	String testName = "VerifyEbooksAddedtoMyStuff_LastViewed";	
	private Object bookNameBefore;
	@Test(dataProvider = "getData")
	public void VerifyEbooksAddedtoMyStuff_LastViewed(Hashtable<String, String> data) throws Exception 
	{		
		
		   test = rep.startTest("VerifyEbooksAddedtoMyStuff_LastViewed");
		   test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		    // Check run mode
		       if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			   test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			   throw new SkipException("Skipping the test as Run Mode was: NO");
		  }
            		
		       //Launch Voot kids app();			       
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
				 LaunchPageV2 launchpagev2=new  LaunchPageV2(driver,test);
				 KidsPlayerPageV2 kidspagev2=new KidsPlayerPageV2(driver,test);
				 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
				 EbooksPageV2 ebookspagev2= new EbooksPageV2(driver,test);
				
				
				 
				     int c=0;  int count=0, updatedcount1=0, updatedcount2=0, updatedcount3=0,  totalpagesreadofebook=0;
				 
				     String  lastread="", lastviewed2="", lastviewed3="", lastviewedfirsttitle1="",bookname="",bookname1=""; 
                
				     String audiocard="", audiocard1="", audiocard2="",audiocard3=""; 
               
				     String storiescount="",storiescount1="",storiescount2="",storiescount3="";
                
				    boolean readerDisplayedPreview=false;    	     
	
				    String 	bookname2="", bookname3="", totalpagesread="",pages="";  
				    
				    //login with some valid credentials
					 homepagev2.login(data.get("Email"),data.get("Password"));
					
				 	//delete All Downloads
					 downloadsv2.deleteAllDownloads();			    
				    
				    //deleting all books under down loads				    
				      downloadsv2.deleteAllDownloads();	 
			    
				    
//TestCase:VK_1668----Verify if the Ebook cards are added to the Last viewed tray if user Opens and closes an Ebook 
	
			 homepagev2.tabClick("Read");			 
			 Thread.sleep(4000);			 
								 
			 //click on book under read Carousel
			 if(Utilities.explicitWaitVisible(driver, readpagev2.firstBookNameInCarousal, 10))
			 {
					 	  bookname=readpagev2.booknameincarouselb.getAttribute("text").toString().trim();
					 	  bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
						  bookname1=bookname.toLowerCase();
						   test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle from Read Carousal          "+bookname1);
						   System.out.print(bookname1);
						try {
							    readpagev2.booknameincarouselb.click();
							    test.log(LogStatus.INFO, "Clicked on the Book from Read Carousal");
						    }catch(Exception e)
						    {
						        test.log(LogStatus.FAIL, " failed to Clicked on the Book from Read Carousal");
						        basepagev2.takeScreenshot();						     
						    }					      
	    	 }
			 else
			 {
				        test.log(LogStatus.FAIL, "failed to retrieve  Book title  from Read Carousal");
				        basepagev2.takeScreenshot();
			 }
				
			 //click on preview button
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
							basepagev2.takeScreenshot();
					      }	
					 				 			
							 //for removing coach cards
						     readpagev2.dismissReaderCoachCards();
					 
					 
				                        //reading only one page					 
								          try{
								                   Thread.sleep(2000);
					                               Utilities.horizontalSwipe(driver);
					                               Thread.sleep(2000);
					                               test.log(LogStatus.INFO, "sucessesfully swiped one page");   
								             }catch(Exception e)
								             {
								            	 test.log(LogStatus.FAIL, "failed to  swipe one page");
								            	 basepagev2.takeScreenshot();
								             }
						          
								         //navigate back to book detail page from reader page								          
							    	     driver.navigate().back();							    	
							    	     Thread.sleep(1000);										 						    	
			 }			 
			 else
		     {
			         test.log(LogStatus.FAIL, "preview  button is not available so unable to continue");
			         basepagev2.takeScreenshot();
		     }
		 			 
			 
			 //navigate back to home page from book detail  page	          
		    	driver.navigate().back();		    	
		    	Thread.sleep(2000);	
			 			 
		  // searching for my stuff tab and  click on mystuffTab 			 
			   Utilities.verticalSwipeReverse(driver);
			   Utilities.verticalSwipeReverse(driver);
			  
			 //click on MyStuff tab
			  homepagev2.tabClick("Watch");
			  Thread.sleep(50000);
			  homepagev2.tabClick("My Stuff");
			  Thread.sleep(10000);
			  
			  //scroll to last ViewedTray				  
					 for(int scroll=0;scroll<=4;scroll++)
					 {
								 if(Utilities.explicitWaitClickable(driver, listenpageV2.lastviewedtrayb, 1))
								 {							 															  
									       test.log(LogStatus.INFO, "  lastviewed tray is available");
									    try {
									    	   listenpageV2.lastviewedtrayb.click();
											   test.log(LogStatus.INFO, "clicked on  lastviewed tray");
										     }catch(Exception e)
									         {
										    	 test.log(LogStatus.FAIL, "failed to click on  lastviewed tray");
										    	 basepagev2.takeScreenshot();
									        }								   
									      break;						 
								 }	  
								 else
								 { 
									 Utilities.verticalSwipe(driver); 							 
								 } 
					 }
			  
					 //retrieve title of last updated  card					 
					 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))
					 {		 	 
					           audiocard=listenpageV2.lastviewedintrayb.getAttribute("text");
					           audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
					           lastread=audiocard.toLowerCase();
					           System.out.println(lastread);
					           test.log(LogStatus.INFO, "sucessesfully retrieved last updated content title      "+lastread);
				           
					 }
					 else
					 {
					           test.log(LogStatus.FAIL, "failed to retrieve  last updated  content title ");
					            basepagev2.takeScreenshot();
					 }
					 
					 if(lastread.contains(bookname1))
					 {		  	 			 
						      test.log(LogStatus.INFO, "Ebookail added under last viewed tray  ");
				             test.log(LogStatus.PASS,"TestCase:VK_1668----Verify if the Ebook cards are added to the Last viewed tray if user Opens and closes an Ebook ");
				             if(!Utilities.setResultsKids("VK_1668", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 						       
					 }
					 else
					 {
						     test.log(LogStatus.FAIL, "Ebook failed to update under last viewed tray After giving 1 minute wait also  ");
							 test.log(LogStatus.FAIL, "TestCase:VK_1668----Verify if the Ebook cards are added to the Last viewed tray if user Opens and closes an Ebook ");
							 if(!Utilities.setResultsKids("VK_1668", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							 basepagev2.takeScreenshot();    
					 }
			 				 
			       // navigated back to homePage from last viewed page
		 
			           driver.navigate().back();
			           
			           Thread.sleep(2000);
			        
			 
				Utilities.verticalSwipeReverse(driver);
				Utilities.verticalSwipeReverse(driver);
			
// VK_1669---Verify if the E book cards are added to the Last viewed tray if user Previews an E book partially	
				
			              homepagev2.tabClick("Read");			 
						 Thread.sleep(4000);			 
						 Utilities.horizontalSwipe(driver);
						 Thread.sleep(3000);			 
						 
						 if(Utilities.explicitWaitVisible(driver, readpagev2.firstBookNameInCarousal, 10))
						 {
								 	  bookname=readpagev2.booknameincarouselb.getAttribute("text");
								 	  bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
									  bookname2=bookname.toLowerCase();
									   test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle from Read Carousal      "+bookname2);
									   System.out.print(bookname2);
									try {
										   readpagev2.booknameincarouselb.click();
										    test.log(LogStatus.INFO, "Clicked on the Book from Read Carousal");
									    }catch(Exception e)
									    {
									         test.log(LogStatus.FAIL, " failed to Clicked on the Book from Read Carousal");
									         basepagev2.takeScreenshot();
									     
									    }					      
				    	 }
						 else
						 {
							      test.log(LogStatus.FAIL, "failed to retrieve  Book title  from Read Carousal");
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
										basepagev2.takeScreenshot();
								      }	
			 				 					 					 
					                         //reading e-book pages partially								 
									          try{
									                   Thread.sleep(2000);
						                               Utilities.horizontalSwipe(driver);
						                               Thread.sleep(3000);
						                               Utilities.horizontalSwipe(driver);
						                               test.log(LogStatus.INFO, "sucessesfully previewed pages partially");   
									             }catch(Exception e)
									             {
									            	 test.log(LogStatus.FAIL, "failed to  swipe pages partially");
									            	 basepagev2.takeScreenshot();
									             }
							          
									           //navigate back to book detail page from reader page								          
								    	        driver.navigate().back();							    	
								    	        Thread.sleep(2000);										 						    	
						 }			 
						 else
					     {
						         test.log(LogStatus.FAIL, "preview  button is not available so unable to continue");
						         basepagev2.takeScreenshot();
					     }
					 			 
						 
						 //navigate back to home page from book detail  page				          
					    	driver.navigate().back();					    	
					    	Thread.sleep(2000);	
						 
						 
					  // searching for my stuff tab and  click on mystuffTab 						 
						   Utilities.verticalSwipeReverse(driver);
						   Utilities.verticalSwipeReverse(driver);
						  						 
						  homepagev2.tabClick("Watch");
						  Thread.sleep(50000);
						  homepagev2.tabClick("My Stuff");
						  Thread.sleep(10000);
						  
						  //scroll to last ViewedTray	
						  
								 for(int scroll=0;scroll<=4;scroll++)
								 {
											 if(Utilities.explicitWaitClickable(driver, listenpageV2.lastviewedtrayb, 1))
											 {							 															  
												       test.log(LogStatus.INFO, "  lastviewed tray is available");
												    try {
												    	   listenpageV2.lastviewedtrayb.click();
														   test.log(LogStatus.INFO, "clicked on  lastviewed tray");
													     }catch(Exception e)
												         {
													    	 test.log(LogStatus.FAIL, "failed to click on  lastviewed tray");
													    	 basepagev2.takeScreenshot();
												        }								   
												      break;						 
											 }	  
											 else
											 { 
												 Utilities.verticalSwipe(driver); 							 
											 } 
								 }
						  
								 //retrieve title of last updated  card								 
								 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))
								 {		 	 
								           audiocard=listenpageV2.lastviewedintrayb.getAttribute("text");
								           audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
								           lastread=audiocard.toLowerCase();
								           System.out.println(lastread);
								           test.log(LogStatus.INFO, "sucessesfully retrieved last updated content title     "+lastread);						           
								 }
								 else
								 {
								           test.log(LogStatus.FAIL, "failed to retrieve  last updated  content title ");
								            basepagev2.takeScreenshot();
								 }
			 			 
					     		 if(lastread.contains(bookname2))
					     		 {		  	 		
					     			         test.log(LogStatus.INFO, "Ebook  to added under last viewed tray  ");
					                          test.log(LogStatus.PASS,"TestCase:VK_1669  when user previewns an Ebook partially , same card is added to lastviewed tray in my stuff");
					                          if(!Utilities.setResultsKids("VK_1669", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 			     		      
					     		 }
					     		 else
					     		 {
					     			          test.log(LogStatus.FAIL, "Ebook failed to update under last viewed tray After giving 1 minute wait also  ");
					     			         test.log(LogStatus.FAIL, "TestCase:VK_1669  when user previewns an Ebook for partially, same card is not added to lastviewed tray in my stuff");     			
					     			        if(!Utilities.setResultsKids("VK_1669", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					     			         basepagev2.takeScreenshot();       
					     		 }	     			 	     				 
			     			
			     		       //navigated back to homePage from last viewed page		     		 
			     			   driver.navigate().back();
			     			   Thread.sleep(2000);
     			 
//TestCase:VK_1670--------Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook Completely 
	     			   
			     			   Utilities.verticalSwipeReverse(driver);			  				
			     			    Utilities.verticalSwipeReverse(driver);
                                 //click on read tab			  				
			     			     homepagev2.tabClick("Read");			 
								 Thread.sleep(4000);			 
								 Utilities.horizontalSwipe(driver);
								 Thread.sleep(2000);			 
								 Utilities.horizontalSwipe(driver);										 
								 Thread.sleep(2000);			 
																 
								 if(Utilities.explicitWaitVisible(driver, readpagev2.firstBookNameInCarousal, 10))
								 {
										 	  bookname=readpagev2.booknameincarouselb.getAttribute("text");
										 	  bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
											  bookname3=bookname.toLowerCase();
											   test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle from Read Carousal          "+bookname3);
											   System.out.print(bookname3);
											try {
												readpagev2.booknameincarouselb.click();
												test.log(LogStatus.INFO, "Clicked on the Book from Read Carousal");
											    }catch(Exception e)
											    {
											     test.log(LogStatus.FAIL, " failed to Clicked on the Book from Read Carousal");
											     basepagev2.takeScreenshot();
											     
											    }					      
						    	 }
								 else
								 {
									 test.log(LogStatus.FAIL, "failed to retrieve  Book title  from Read Carousal");
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
												   basepagev2.takeScreenshot();
										      }	
										 				 	
													 				
												   //preview an e-book completely	  
												     for(int i=1;i<=5;i++)
											         {
										                   Utilities.horizontalSwipe(driver);
										                   Thread.sleep(3000);
											         } 
												    											     							                               
													   //navigate back from download page	to book detail page													   
													     driver.navigate().back();
													      Thread.sleep(2000);
													      driver.navigate().back();
													      Thread.sleep(2000);
												   
								 }			 
								 else
							     {
								         test.log(LogStatus.FAIL, "preview  button is not available so unable to continue");
								         basepagev2.takeScreenshot();
							     }
							 			 							 
									
								   //navigate back to home page from book detail  page						          
							    	driver.navigate().back();							    	
							    	Thread.sleep(2000);	
								 								 
							  //searching for my stuff tab and  click on mystuffTab 								 
								   Utilities.verticalSwipeReverse(driver);
								   Utilities.verticalSwipeReverse(driver);
								  								 
								  homepagev2.tabClick("Watch");
								  Thread.sleep(50000);
								  homepagev2.tabClick("My Stuff");
								  Thread.sleep(10000);
								  
						        //scroll to last ViewedTray									  
								 for(int scroll=0;scroll<=4;scroll++)
								 {
											 if(Utilities.explicitWaitClickable(driver, listenpageV2.lastviewedtrayb, 1))
											 {							 															  
												       test.log(LogStatus.INFO, "  lastviewed tray is available");
												    try {
												    	   listenpageV2.lastviewedtrayb.click();
														   test.log(LogStatus.INFO, "clicked on  lastviewed tray");
													     }catch(Exception e)
												         {
													    	 test.log(LogStatus.FAIL, "failed to click on  lastviewed tray");
													    	 basepagev2.takeScreenshot();
												        }								   
												      break;						 
											 }	  
											 else
											 { 
												 Utilities.verticalSwipe(driver); 							 
											 } 
								 }
						  
								 //retrieve title of last updated  card									 
								 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))
								 {		 	 
								           audiocard=listenpageV2.lastviewedintrayb.getAttribute("text");
								           audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
								           lastread=audiocard.toLowerCase();
								           System.out.println(lastread);
								           test.log(LogStatus.INFO, "sucessesfully retrieved last updated content title          "+lastread);
							           
								 }
								 else
								 {
								           test.log(LogStatus.FAIL, "failed to retrieve  last updated  content title ");
								            basepagev2.takeScreenshot();
								 }
								 
					     		 if(lastread.contains(bookname3))
					     		 {		  
					     			    test.log(LogStatus.INFO, "Ebook  updated under last viewed tray  ");
						                test.log(LogStatus.PASS,"TestCase:VK_1670--------Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook Completely ");
						                if(!Utilities.setResultsKids("VK_1670", "PASS"))test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
						     		       
					     		 }
					     		 else
					     		 {
					     			     test.log(LogStatus.FAIL, "Ebook failed to update under last viewed tray After giving 1 minute wait also  ");
						     			 test.log(LogStatus.FAIL, "TestCase:VK_1670--------Verify if the Ebook cards are added to the Last viewed tray if user Previews an Ebook Completely ");					     		
						     			 if(!Utilities.setResultsKids("VK_1670", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
						     			 basepagev2.takeScreenshot();      
					     		 }	     			 	     				 
		     				 	 
	}
	
	@DataProvider
	public Object[][] getData()
	{
	return DataUtil.getData(testName,xls);											          					
    }     
}
