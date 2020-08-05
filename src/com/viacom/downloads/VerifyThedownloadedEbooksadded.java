package com.viacom.downloads;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;
import org.openqa.selenium.By;
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
//Author=bhaskar
public class VerifyThedownloadedEbooksadded extends BaseTestV2
{
	
	//VK_1848------Verify if the Downloaded Ebook cards are added to the Last viewed tray if user Opens and closes an Ebook
	//VK_1849----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook partially 
	//VK_1850----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook completely
	//VK_1851-----Verify the playback status for partially watched downloaded Ebook content in Last viewed tray:
	//VK_1852-----Verify the playback status for Completely watched downloaded Ebook content in Last viewed tray:
		
	String testName = "VerifyThedownloadedEbooksadded";	
	@Test(dataProvider = "getData")
	public void VerifyThedownloadedEbooksadded(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("VerifyThedownloadedEbooksadded");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		
		// Check run mode
		
		       if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		  }
            			       
		             //LaunchVootkidsapp();								 
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
					
				
				 
					 int c=0 , c1=0, count=0 , updatedcount1=0 ,updatedcount3=0 ,updatedcount2=0 ,readpagesofebook=0, partiallyreadpages=0 ,totlalpages=0;
					 
					 int	 readpagesinlastviewdtray=0 , totalpagesofebook=0 , startingtime=0 ,readpagesofebookinlast2viewdtray=0;
					 
			         String lastread="" ,  lastviewed2="" ,lastviewed3="",  lastviewedfirsttitle1="" ,audiocard="" ,audiocard1="" ,lastread2="", audiotitleindetailpage="";
			        
				     String audiocard2="",  audiocard3="" , storiescount="" , storiescount1="", storiescount2="", storiescount3="" ,bookname="" ,bookname1=""; 
			       
				     String booktitle1="" ,  booktitle2="", booktitle3="", booktitle4="", booktitle5="";
				     
				    String bookname2="" , bookname3="" ,numberofpagesread="" ,pages="" , audioname="" ,audioname1="" ,audioname2="",audioname3="" ,lastplayed="";
			        
				    String lastupdated1="", lastupdated2="", lastupdated3="",lastupdated="";
				    
				    boolean readerDisplayedPreview=false;  
				    
				    
				      //login with Valid credentials
					   homepagev2.login(data.get("Email"),data.get("Password"));
					
					   //delete All Downloads					
					   downloadsv2.deleteAllDownloads();
	   	
	       
	      
  //VK_1848------Verify if the Down-loaded E-book cards are added to the Last viewed tray if user Opens and closes an E-book				       
	       
	          homepagev2.tabClick("Read");			 
			  Thread.sleep(4000);			 						
	  
			  // scroll to first Reads tray and click on First reads tray			 		
			 for(int scroll=1;scroll<=10;scroll++)
		     {												
				      if(Utilities.explicitWaitClickable(driver, ebookspagev2.bestreadstrayb, 1))
				      {									          	
				          try {
				        	         ebookspagev2.bestreadstrayb.click();			        	      
				                     test.log(LogStatus.INFO, "clicked on first  tray");
				                     break;
				                     
				               }catch(Exception e)
				               {
				            	     test.log(LogStatus.FAIL, "failed to click on first  tray");
				            	     basepagev2.takeScreenshot();
				               }									          
					           									           
			           }else   Utilities.verticalSwipe(driver);
	          } 
 			 
			 //click on card 1 under best reads page 
			 if(Utilities.explicitWaitClickable(driver, ebookspagev2.card1underbestreadspage, 10))
		     {							 				 
			          try {		        	  
			        	    bookname=ebookspagev2.card1underbestreadspage.getAttribute("text").toString().trim();
				 	        bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
					        booktitle1=bookname.toLowerCase();
					        test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle               "+booktitle1);
					        System.out.print(booktitle1);
			        	     ebookspagev2.card1underbestreadspage.click();			        	      
			                 test.log(LogStatus.INFO, "clicked on  card 1 under first tray page ");		                    		                     
			               }catch(Exception e)
			               {
			            	     test.log(LogStatus.FAIL, "failed to click on card1 under first tray page ");
			            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle1);
			            	     basepagev2.takeScreenshot();
			               }
		      }else
		      {
		    	     test.log(LogStatus.FAIL, " card1  is not Available in under first tray page ");
         	         basepagev2.takeScreenshot(); 
		      }
			 
			 
			 //doing vertical swipe down till to  download-book link in book detail page			 
			 if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
			 {								 
							 for(int scroll=1;scroll<=4;scroll++)
							 {											
									 if(Utilities.explicitWaitVisible(driver,ebookspagev2.downloadbooklinkb , 2))
									 {													 
										 test.log(LogStatus.INFO, "download book link is available"); 
										 break;
									 }
									 else
									 {
										Utilities.verticalSwipe(driver);
									 }											 											 
							 }
			 }			 
			 else
		     {
			         test.log(LogStatus.FAIL, "preview  button is not available in book detail page");
			         basepagev2.takeScreenshot();
		     }
		 	
			 //click on download-book link in book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,ebookspagev2.downloadbooklinkb , 10))
			 {		try {							
					   ebookspagev2.downloadbooklinkb.click();
				       test.log(LogStatus.INFO, "clicked on download book link in book detail page");
			            }catch(Exception e) {}
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "failed to click on download book link in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
			 //do vertical swipe up till to  read or preview button in book detail page			 
			 for(int scroll=1;scroll<=4;scroll++)
			 {											
					 if(Utilities.explicitWaitVisible(driver,  readpagev2.previewButton  , 2) || Utilities.explicitWaitVisible(driver,  readpagev2.readButton  , 2) )
					 {													 
						   test.log(LogStatus.INFO, "read button or preview button is available"); 
						   break;
					 }
					 else
					 {
						  Utilities.verticalSwipeReverse(driver);
					 }											 											 
			 }
				 			 
            //click on read button in  book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,readpagev2.readButton, 600))
			 {									
				    test.log(LogStatus.INFO, " read button is available in book detail page");
						 try {
							 readpagev2.readButton.click();
							  test.log(LogStatus.INFO, "Clicked on READ button");
								 for(int wait=0;wait<=30;wait++) //for loop 1 starting
								  {
								     try
								      {
										 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
										
										    if(wait==30)
										    {
											 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
											 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Read' button is Fail");
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
										
												test.log(LogStatus.INFO, "Verified the behavior when tapping on 'Read' button ");
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
								test.log(LogStatus.FAIL, "Unable to click on read button"); 
								basepagev2.takeScreenshot();
						      }	
						 
									    //read only one page
										 try {
										        Thread.sleep(2000);
										         Utilities.horizontalSwipe(driver);
										        Thread.sleep(3000);
										        test.log(LogStatus.INFO, "sucesses fully swiped ebook pages");
										    }catch(Exception e)
										    {
										    	test.log(LogStatus.FAIL, "Unable to swipe ebook pages");
										    	basepagev2.takeScreenshot();
										    }   
										 
										//navigate back to  home page from e-book pages										
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);							
										driver.navigate().back();
										Thread.sleep(2000);			 						 
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " read button is not available in  in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 	
		      // searching for my stuff tab and  click on mystuffTab 			 
			   Utilities.verticalSwipeReverse(driver);
			   Utilities.verticalSwipeReverse(driver);						  
			 			   			   
			      //click on MyStuff tab
				  homepagev2.tabClick("Watch");
				  Thread.sleep(120000);
				  homepagev2.tabClick("My Stuff");
				  Thread.sleep(60000);
			  
		     //do vertical swipe till to last viewed tray in my stuff		  
			 for(int scroll=0;scroll<=4;scroll++)
			 {
						 if(Utilities.explicitWaitVisible(driver, listenpageV2.lastviewedtrayb, 2))
						 {							 															  
							       test.log(LogStatus.INFO, "  lastviewed tray is available");												     							   
							       break;						 
						 }	  
						 else
						 { 
							 Utilities.verticalSwipe(driver); 							 
						 } 
			 }
		  			 
		      //click on lastViewed tray in MyStuff tab			 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastviewedtrayb, 10))
			 {	 
				        test.log(LogStatus.INFO, "  lastviewed tray is available");
				    try{
				    	  listenpageV2.lastviewedtrayb.click();
				    	  test.log(LogStatus.INFO, " Clicked on Lastviewed Tray in Mystuff tab");
				       }catch(Exception e)
				       {
				    	   test.log(LogStatus.FAIL, "failed to  Click on Lastviewed Tray in Mystuff tab");
				           basepagev2.takeScreenshot();
				       }				 
			 }
			 else
			 {
				         test.log(LogStatus.FAIL," Lastviewed tray is not available after giving 3 minutes wait also");	
				         basepagev2.takeScreenshot();
			 }
							 
		     //retrieve title of last updated( just open and closed-book)  e-book 	 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))				 
			 {		
				  test.log(LogStatus.INFO, "last updated book is  available");				 
				 try {
					    audiocard=listenpageV2.lastviewedintrayb.getAttribute("text").toString().trim();
			            audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
			            lastupdated=audiocard.toLowerCase();
			            System.out.println( lastupdated);
			            test.log(LogStatus.INFO, "sucessesfully retrieved last updated1 content title       "+lastupdated);
				      }catch(Exception e)
				     {
				    	  test.log(LogStatus.FAIL, "failed to retrieve  last updated1  content title        "+lastupdated);
				            basepagev2.takeScreenshot();
				     }
			 }
			 else
			 {
			            test.log(LogStatus.FAIL, "last updated book is not  available             "+lastupdated);
			            basepagev2.takeScreenshot();
			 }
						 
			 //verifying the  just open and closed-book updated or not under last viewed my stuff tab		 
			 if(lastupdated.contains(booktitle1))
			 {
				    test.log(LogStatus.INFO, " open and closed-book updated  under last viewed my stuff tab");
				    test.log(LogStatus.PASS, " Testcase:VK_1848------Verify if the Down-loaded E-book cards are added to the Last viewed tray if user Opens and closes an E-book' is passed");					    	
		    	    if(!Utilities.setResultsKids("VK_1850", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    				    	  
			 }
			 else
			 {
				    test.log(LogStatus.FAIL, "open and closed-book failed to update  under last viewed my stuff tab after giving 3 minutes wait also");
				    test.log(LogStatus.FAIL, " Testcase: VK_1848------Verify if the Down-loaded E-book cards are added to the Last viewed tray if user Opens and closes an E-book' is failed");					    	
		    	    if(!Utilities.setResultsKids("VK_1850", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
		    	    basepagev2.takeScreenshot();
			 }
			 
			 //navigate back to home page from last viewed page			 
			   driver.navigate().back();
			   Thread.sleep(2000);
			 
			 //do vertical swipe up to get read tab			 
			 Utilities.verticalSwipeReverse(driver);
			 Utilities.verticalSwipeReverse(driver);

			 
//VK_1849----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook partially 			 
			 
			 homepagev2.tabClick("Read");				 
			 Thread.sleep(4000);			 												 						
			

			 //scroll to first Reads tray and click on First reads tray			 		
			 for(int scroll=1;scroll<=10;scroll++)
		     {												
				      if(Utilities.explicitWaitClickable(driver, ebookspagev2.bestreadstrayb, 1))
				      {									          	
				          try {
				        	         ebookspagev2.bestreadstrayb.click();			        	      
				                     test.log(LogStatus.INFO, "clicked on best first  tray");
				                     break;
				                     
				               }catch(Exception e)
				               {
				            	     test.log(LogStatus.FAIL, "failed to click on first  tray");
				            	     basepagev2.takeScreenshot();
				               }									          
					           									           
			           }else   Utilities.verticalSwipe(driver);
	          } 
 	     
			 //click on card 2 under best reads page 
			 if(Utilities.explicitWaitClickable(driver, ebookspagev2.card2underbestreadspage, 10))
		     {							 				 
			          try {		        	  
			        	    bookname=ebookspagev2.card2underbestreadspage.getAttribute("text").toString().trim();
				 	        bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
					        booktitle2=bookname.toLowerCase();
					        test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle               "+booktitle2);
					        System.out.print(booktitle2);
			        	     ebookspagev2.card2underbestreadspage.click();			        	      
			                 test.log(LogStatus.INFO, "clicked on  card 2 under first tray page ");		                    		                     
			               }catch(Exception e)
			               {
			            	     test.log(LogStatus.FAIL, "failed to click on card2 under first tray page ");
			            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle2);
			            	     basepagev2.takeScreenshot();
			               }
		      }else
		      {
		    	     test.log(LogStatus.FAIL, " card 2  is not Available  under first tray page ");
         	         basepagev2.takeScreenshot(); 
		      }
			
			 
			//doing vertical swipe down till to  download-book link in book detail page			 
			 if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
			 {								 
							 for(int scroll=1;scroll<=4;scroll++)
							 {											
									 if(Utilities.explicitWaitVisible(driver,ebookspagev2.downloadbooklinkb , 2))
									 {													 
										 test.log(LogStatus.INFO, "download book link is available"); 
										 break;
									 }
									 else
									 {
									     Utilities.verticalSwipe(driver);
									 }											 											 
							 }
			 }			 
			 else
		     {
			         test.log(LogStatus.FAIL, "preview  button is not available in book detail page");
			         basepagev2.takeScreenshot();
		     }
		 	
			 //click on download-book link in book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,ebookspagev2.downloadbooklinkb , 10))
			 {		
				 try {
					     ebookspagev2.downloadbooklinkb.click();
				         test.log(LogStatus.INFO, "clicked on download book link in book detail page"); 
				     }catch(Exception e) {}						      								  
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "failed to click on download book link in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
			 //doing vertical swipe up till to  read or preview button in book detail page			 
			 for(int scroll=1;scroll<=4;scroll++)
			 {											
					 if(Utilities.explicitWaitVisible(driver,  readpagev2.previewButton  , 2) || Utilities.explicitWaitVisible(driver,  readpagev2.readButton  , 2) )
					 {													 
						   test.log(LogStatus.INFO, "read button or preview button is  available"); 
						   break;
					 }
					 else
					 {
						  Utilities.verticalSwipeReverse(driver);
					 }											 											 
			 }
			 
            //click on read button in  book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,readpagev2.readButton, 600))
			 {									
				    test.log(LogStatus.INFO, " read button is available in book detail page");
						 try {
							 readpagev2.readButton.click();
							  test.log(LogStatus.INFO, "Clicked on READ button");
								 for(int wait=0;wait<=30;wait++) //for loop 1 starting
								  {
								     try
								      {
										 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
										
										    if(wait==30)
										    {
											 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
											 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Read' button is Fail");
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
										
												test.log(LogStatus.INFO, "Verified the behavior when tapping on 'Read' button ");
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
								test.log(LogStatus.FAIL, "Unable to click on read button"); 
								basepagev2.takeScreenshot();
						      }	
						 						 
					       //double click on book pages to get reader controls						 
						 try{									    	   					    							    												    						    		
								Thread.sleep(3000);
								Utilities.tap(driver);
								Utilities.tap(driver);								
								test.log(LogStatus.INFO, "Double tapped on screen to launch reader controls");	
								
					    	}catch(Exception e)
				 	        {
					    		 test.log(LogStatus.FAIL, "failed to do Double tap on screen to launch reader controls");	
					    		 basepagev2.takeScreenshot();
					    	}	
					      							      
					           /* // refreshApp													         
					            driver.runAppInBackground(Duration.ofSeconds(3));
					            test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					            driver.currentActivity();*/
					         										
				                //read e-book partially					       				         
								if(Utilities.explicitWaitVisible(driver, ebookspagev2.ebookseekBar, 10))
								{
									     test.log(LogStatus.INFO, "ebook seek bar is available");	
									     Utilities.scrubtillhalf(driver, ebookspagev2.ebookseekBar);							    
									     Thread.sleep(2000);
						        }
							    else
							    {
							            test.log(LogStatus.FAIL, "ebookseekbar is not availble");
							            basepagev2.takeScreenshot();
						        }   
								   
							
							//navigate back to  home page from reader controls page							
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " read button is not available in  in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
									
		  // searching for my stuff tab and  click on mystuffTab 			 
			   Utilities.verticalSwipeReverse(driver);
			   Utilities.verticalSwipeReverse(driver);						  
			 			   
			   //click on MyStuff tab
				  homepagev2.tabClick("Watch");
				  Thread.sleep(1200000);
				  homepagev2.tabClick("My Stuff");
				  Thread.sleep(60000);;
			  
		    //do vertical swipe till to last viewed tray in my stuff		  
			 for(int scroll=0;scroll<=4;scroll++)
			 {
						 if(Utilities.explicitWaitVisible(driver, listenpageV2.lastviewedtrayb, 2))
						 {							 															  
							       test.log(LogStatus.INFO, "  lastviewed tray is available");												     							   
							       break;						 
						 }	  
						 else
						 { 
							 Utilities.verticalSwipe(driver); 							 
						 } 
			 }
		  			 
		     //click on lastViewed tray in MyStuff tab			 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastviewedtrayb, 10))
			 {	 
				        test.log(LogStatus.INFO, "  lastviewed tray is available");
				    try{
				    	  listenpageV2.lastviewedtrayb.click();
				    	  test.log(LogStatus.INFO, " Clicked on Lastviewed Tray in Mystuff tab");
				       }catch(Exception e)
				       {
				    	   test.log(LogStatus.FAIL, "failed to  Click on Lastviewed Tray in Mystuff tab");
				           basepagev2.takeScreenshot();
				       }				 
			 }
			 else
			 {
				         test.log(LogStatus.FAIL," Lastviewed tray is not available after giving 3 minutes wait also");	
				         basepagev2.takeScreenshot();
			 }
							 
		    	 
			 //retrieve title of last updated(partially watched e-book)  e-book 	 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))				 
			 {		
				  test.log(LogStatus.INFO, "last updated book is  available");				 
				 try {
					    audiocard=listenpageV2.lastviewedintrayb.getAttribute("text").toString().trim();
			            audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
			            lastupdated=audiocard.toLowerCase();
			            System.out.println( lastupdated);
			            test.log(LogStatus.INFO, "sucessesfully retrieved last updated1 content title       "+lastupdated);
				      }catch(Exception e)
				     {
				    	  test.log(LogStatus.FAIL, "failed to retrieve  last updated1  content title        "+lastupdated);
				            basepagev2.takeScreenshot();
				     }
			 }
			 else
			 {
			            test.log(LogStatus.FAIL, "last updated book is not  available             "+lastupdated);
			            basepagev2.takeScreenshot();
			 }
			 
			 //verifying partially read e-book updated or not under last viewed my Stuff tab 
			 if(lastupdated.contains(booktitle2))
			 {
				    test.log(LogStatus.INFO, "partially watched E-book updated under last viewed MyStuff tab");
				    test.log(LogStatus.PASS, " Testcase:VK_1849----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook partially' is passed");					    	
		    	    if(!Utilities.setResultsKids("VK_1849", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    				    	  
			 }
			 else
			 {
				    test.log(LogStatus.FAIL, "partially watched E-book faield to  update under last viewed MyStuff tab after giving 3 minutes wait also");
				    test.log(LogStatus.FAIL, " Testcase:VK_1849----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook partially' is failed");					    	
		    	    if(!Utilities.setResultsKids("VK_1849", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
		    	    basepagev2.takeScreenshot();
			 }
			 
			 //navigate back to home page from last viewed page			 
			   driver.navigate().back();
			   Thread.sleep(2000);
			 
			 //do vertical swipe up to get read tab			 
			 Utilities.verticalSwipeReverse(driver);
			 Utilities.verticalSwipeReverse(driver);
		 
//VK_1850----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook completely			 
			 
			 homepagev2.tabClick("Read");				 
			 Thread.sleep(4000);			 						

			 //scroll to first Reads tray and click on First reads tray			 		
			 for(int scroll=1;scroll<=10;scroll++)
		     {												
				      if(Utilities.explicitWaitClickable(driver, ebookspagev2.bestreadstrayb, 1))
				      {									          	
				          try {
				        	         ebookspagev2.bestreadstrayb.click();			        	      
				                     test.log(LogStatus.INFO, "clicked on first tray");
				                     break;
				                     
				               }catch(Exception e)
				               {
				            	     test.log(LogStatus.FAIL, "failed to click on first tray");
				            	     basepagev2.takeScreenshot();
				               }									          
					           									           
			           }else   Utilities.verticalSwipe(driver);
	          } 
 	     
			 //click on card 3 under best reads page 
			 if(Utilities.explicitWaitClickable(driver, ebookspagev2.card3underbestreadspage, 10))
		     {							 				 
			          try {		        	  
			        	    bookname=ebookspagev2.card3underbestreadspage.getAttribute("text").toString().trim();
				 	        bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
					        booktitle3=bookname.toLowerCase();
					        test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle               "+booktitle3);
					        System.out.print(booktitle3);
			        	     ebookspagev2.card3underbestreadspage.click();			        	      
			                 test.log(LogStatus.INFO, "clicked on  card 3 under first tray page ");		                    		                     
			               }catch(Exception e)
			               {
			            	     test.log(LogStatus.FAIL, "failed to click on card3 under first tray page ");
			            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle3);
			            	     basepagev2.takeScreenshot();
			               }
		      }else
		      {
		    	     test.log(LogStatus.FAIL, " card3  is not Available in under first tray page ");
         	         basepagev2.takeScreenshot(); 
		      }
							
			//doing vertical swipe down till to  download-book link in book detail page			 
			 if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
			 {								 
							 for(int scroll=1;scroll<=4;scroll++)
							 {											
									 if(Utilities.explicitWaitVisible(driver,ebookspagev2.downloadbooklinkb , 2))
									 {													 
										 test.log(LogStatus.INFO, "download book link is available"); 
										 break;
									 }
									 else
									 {
										Utilities.verticalSwipe(driver);
									 }											 											 
							 }
			 }			 
			 else
		     {
			         test.log(LogStatus.FAIL, "preview  button is not available in book detail page");
			         basepagev2.takeScreenshot();
		     }
		 	
			 //click on download-book link in book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,ebookspagev2.downloadbooklinkb , 10))
			 {									
					   ebookspagev2.downloadbooklinkb.click();
				       test.log(LogStatus.INFO, "clicked on download book link in book detail page"); 							      								  
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "failed to click on download book link in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
			 //doing vertical swipe up till to  read or preview button in book detail page			 
			 for(int scroll=1;scroll<=4;scroll++)
			 {											
					 if(Utilities.explicitWaitVisible(driver,  readpagev2.previewButton  , 2) || Utilities.explicitWaitVisible(driver,  readpagev2.readButton  , 2) )
					 {													 
						   test.log(LogStatus.INFO, "read button or preview button is  available"); 
						   break;
					 }
					 else
					 {
						  Utilities.verticalSwipeReverse(driver);
					 }											 											 
			 }
			 
            //click on read button in  book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,readpagev2.readButton, 600))
			 {									
				    test.log(LogStatus.INFO, " read button is available in book detail page");
						 try {
							 readpagev2.readButton.click();
							  test.log(LogStatus.INFO, "Clicked on READ button");
								 for(int wait=0;wait<=30;wait++) //for loop 1 starting
								  {
								     try
								      {
										 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
										
										    if(wait==30)
										    {
											 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
											 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Read' button is Fail");
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
										
												test.log(LogStatus.INFO, "Verified the behavior when tapping on 'Read' button ");
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
								test.log(LogStatus.FAIL, "Unable to click on read button"); 
								basepagev2.takeScreenshot();
						      }	
						 						 
					       //double click on book pages to get reader controls	  			 
						 try{									    	   					    							    												    						    		
								Thread.sleep(3000);
								Utilities.tap(driver);
								Utilities.tap(driver);								
								test.log(LogStatus.INFO, "Double tapped on screen to launch reader controls");	
								
					    	}catch(Exception e)
				 	        {
					    		 test.log(LogStatus.FAIL, "failed to do Double tap on screen to launch reader controls");	
					    		 basepagev2.takeScreenshot();
					    	}	
					      							      
					       /* //refreshApp													         
					         driver.runAppInBackground(Duration.ofSeconds(3));
					         test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					         driver.currentActivity();*/
					         										
				            //read e book completely				         
							if(Utilities.explicitWaitVisible(driver, ebookspagev2.ebookseekBar, 10))
							{
								     test.log(LogStatus.INFO, "ebook seek bar is available");	
								     Utilities.scrubtillend(driver, ebookspagev2.ebookseekBar);							    
								     Thread.sleep(2000);
					        }
						    else
						    {
						            test.log(LogStatus.FAIL, "ebookseekbar is not availble");
						            basepagev2.takeScreenshot();
					        }   
							 							
							//navigate back to  home page from 'read-again' page							
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);	
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();
							Thread.sleep(2000);
							
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " read button is not available in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			
		  //searching for my stuff tab and  click on mystuffTab 			 
			   Utilities.verticalSwipeReverse(driver);
			   Utilities.verticalSwipeReverse(driver);						  
						   
			   //click on MyStuff tab
				  homepagev2.tabClick("Watch");
				  Thread.sleep(1200000);
				  homepagev2.tabClick("My Stuff");
				  Thread.sleep(60000);
			  
		   //do vertical swipe till to last viewed tray in my stuff		  
			 for(int scroll=0;scroll<=4;scroll++)
			 {
						 if(Utilities.explicitWaitVisible(driver, listenpageV2.lastviewedtrayb, 2))
						 {							 															  
							       test.log(LogStatus.INFO, "Lastviewed tray is available");												     							   
							       break;						 
						 }	  
						 else
						 { 
							     Utilities.verticalSwipe(driver); 							 
						 } 
			 }
		  
			 
		    //click on lastViewed tray in MyStuff tab				 
			  if(Utilities.explicitWaitVisible(driver,listenpageV2.lastviewedtrayb, 10))
			 {	 
				        test.log(LogStatus.INFO, "  lastviewed tray is available");
				    try{
				    	  listenpageV2.lastviewedtrayb.click();
				    	  test.log(LogStatus.INFO, " Clicked on Lastviewed Tray in Mystuff tab");
				       }catch(Exception e)
				       {
				    	   test.log(LogStatus.FAIL, "failed to  Click on Lastviewed Tray in Mystuff tab");
				           basepagev2.takeScreenshot();
				       }				 
			 }
			 else
			 {
				         test.log(LogStatus.FAIL," Lastviewed tray is not available after giving 3 minutes wait also");	
				         basepagev2.takeScreenshot();
			 }
							 		     
			  //retrieve title of last updated(completely watched e-book)  e-book 	 
				 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))				 
				 {		
					  test.log(LogStatus.INFO, "last updated book is  available");				 
					 try {
						    audiocard=listenpageV2.lastviewedintrayb.getAttribute("text").toString().trim();
				            audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
				            lastupdated=audiocard.toLowerCase();
				            System.out.println( lastupdated);
				            test.log(LogStatus.INFO, "sucessesfully retrieved last updated1 content title       "+lastupdated);
					      }catch(Exception e)
					     {
					    	  test.log(LogStatus.FAIL, "failed to retrieve  last updated1  content title        "+lastupdated);
					            basepagev2.takeScreenshot();
					     }
				 }
				 else
				 {
				            test.log(LogStatus.FAIL, "last updated book is not  available             "+lastupdated);
				            basepagev2.takeScreenshot();
				 }
						 
			 //verifying completely watched e-book updated or not			 
			 if(lastupdated.contains(booktitle3))
			 {
				    test.log(LogStatus.INFO, "completely watched E-book updated under last viewed MyStuff tab");
				    test.log(LogStatus.PASS, " Testcase: VK_1850----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook completely' is passed");					    	
		    	    if(!Utilities.setResultsKids("VK_1850", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    				    	  
			 }
			 else
			 {
				    test.log(LogStatus.FAIL, "completely watched E-book faield to  update under last viewed MyStuff tab after giving 3 minutes wait also");
				    test.log(LogStatus.FAIL, " Testcase: VK_1850----Verify if the Ebook cards are added to the Last viewed tray if user Reads Downloaded Ebook completely' is failed");					    	
		    	    if(!Utilities.setResultsKids("VK_1850", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
		    	    basepagev2.takeScreenshot();
			 }
			 
			 //navigate back to home page from last viewed page			 
			   driver.navigate().back();
			   Thread.sleep(2000);
			 
			 //do vertical swipe up to get read tab			  
			 Utilities.verticalSwipeReverse(driver);
			 Utilities.verticalSwipeReverse(driver);		 
							             	                         
//VK_1851-----Verify the playback status for partially watched downloaded Ebook content in Last viewed tray:			 
			 
			 
		     homepagev2.tabClick("Read");	
		     Thread.sleep(4000);		    
			 			 
		     // scroll to first Reads tray and click on First reads tray	 		
			 for(int scroll=1;scroll<=10;scroll++)
		     {												
				      if(Utilities.explicitWaitClickable(driver, ebookspagev2.bestreadstrayb, 1))
				      {									          	
				          try {
				        	         ebookspagev2.bestreadstrayb.click();			        	      
				                     test.log(LogStatus.INFO, "clicked on first  tray");
				                     break;
				                     
				               }catch(Exception e)
				               {
				            	     test.log(LogStatus.FAIL, "failed to click on best reades tray");
				            	     basepagev2.takeScreenshot();
				               }									          
					           									           
			           }else   Utilities.verticalSwipe(driver);
	          } 
 	     
			 //click on card 4 under best reads page 
			 if(Utilities.explicitWaitClickable(driver, ebookspagev2.card4underbestreadspage, 10))
		     {							 				 
			          try {		        	  
			        	    bookname=ebookspagev2.card4underbestreadspage.getAttribute("text").toString().trim();
				 	        bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
					        booktitle4=bookname.toLowerCase();
					        test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle               "+booktitle4);
					        System.out.print(booktitle4);
			        	     ebookspagev2.card4underbestreadspage.click();			        	      
			                 test.log(LogStatus.INFO, "clicked on  card 4 under  first tray page");		                    		                     
			               }catch(Exception e)
			               {
			            	     test.log(LogStatus.FAIL, "failed to click on card4 under  first tray page");
			            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle4);
			            	     basepagev2.takeScreenshot();
			               }
		      }else
		      {
		    	         test.log(LogStatus.FAIL, " card4  is not Available in under first tray page");
         	             basepagev2.takeScreenshot(); 
		      }
			 
			//doing vertical swipe down till to  download-book link in book detail page			 
			 if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
			 {								 
							 for(int scroll=1;scroll<=4;scroll++)
							 {											
									 if(Utilities.explicitWaitVisible(driver,ebookspagev2.downloadbooklinkb , 2))
									 {													 
										 test.log(LogStatus.INFO, "download book link is available"); 
										 break;
									 }
									 else
									 {
									     Utilities.verticalSwipe(driver);
									 }											 											 
							 }
			 }			 
			 else
		     {
			         test.log(LogStatus.FAIL, "preview  button is not available in book detail page");
			         basepagev2.takeScreenshot();
		     }
		 	
			 //click on download-book link in book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,ebookspagev2.downloadbooklinkb , 10))
			 {		
				 try {
					     ebookspagev2.downloadbooklinkb.click();
				         test.log(LogStatus.INFO, "clicked on download book link in book detail page"); 
				     }catch(Exception e) {}						      								  
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "failed to click on download book link in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
			 //doing vertical swipe up till to  read or preview button in book detail page			 
			 for(int scroll=1;scroll<=4;scroll++)
			 {											
					 if(Utilities.explicitWaitVisible(driver,  readpagev2.previewButton  , 2) || Utilities.explicitWaitVisible(driver,  readpagev2.readButton  , 2) )
					 {													 
						   test.log(LogStatus.INFO, "read button or preview button is  available"); 
						   break;
					 }
					 else
					 {
						  Utilities.verticalSwipeReverse(driver);
					 }											 											 
			 }
			 
            //click on read button in  book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,readpagev2.readButton, 600))
			 {									
				    test.log(LogStatus.INFO, " read button is available in book detail page");
						 try {
							 readpagev2.readButton.click();
							  test.log(LogStatus.INFO, "Clicked on READ button");
								 for(int wait=0;wait<=30;wait++) //for loop 1 starting
								  {
								     try
								      {
										 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
										
										    if(wait==30)
										    {
											 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
											 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Read' button is Fail");
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
										
												test.log(LogStatus.INFO, "Verified the behavior when tapping on 'Read' button ");
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
								test.log(LogStatus.FAIL, "Unable to click on read button"); 
								basepagev2.takeScreenshot();
						      }	
						 						 
					       //double click on book pages to get reader controls						 
						 try{									    	   					    							    												    						    		
								Thread.sleep(3000);
								Utilities.tap(driver);
								Utilities.tap(driver);								
								test.log(LogStatus.INFO, "Double tapped on screen to launch reader controls");	
								
					    	}catch(Exception e)
				 	        {
					    		 test.log(LogStatus.FAIL, "failed to do Double tap on screen to launch reader controls");	
					    		 basepagev2.takeScreenshot();
					    	}	
					      							      
					           /* // refreshApp													         
					            driver.runAppInBackground(Duration.ofSeconds(3));
					            test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					            driver.currentActivity();*/
					         										
				                //read e-book partially					       				         
								if(Utilities.explicitWaitVisible(driver, ebookspagev2.ebookseekBar, 10))
								{
									     test.log(LogStatus.INFO, "ebook seek bar is available");	
									     Utilities.scrubtillhalf(driver, ebookspagev2.ebookseekBar);							    
									     Thread.sleep(2000);
						        }
							    else
							    {
							            test.log(LogStatus.FAIL, "ebookseekbar is not availble");
							            basepagev2.takeScreenshot();
						        }   
																      
								if(Utilities.explicitWaitVisible(driver, ebookspagev2.pagesfromplayerb, 10))
								{
								           test.log(LogStatus.INFO, "partialy  read pages count is  available");
											try 
											{																								 																																		
												 pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
												  String  arr[]=pages.split(" ");

													numberofpagesread=arr[0].toString().trim();
													partiallyreadpages=Integer.parseInt(numberofpagesread);
													System.out.println(partiallyreadpages);											 
													test.log(LogStatus.INFO, "sucessesfully retrieved partially read ebook pages           "+ partiallyreadpages);
													
											}catch(Exception e)
											{
													test.log(LogStatus.FAIL, "failed to  retrieve partially read ebook pages       ");	  
													basepagev2.takeScreenshot();
											}												
								}
								else
								{
											test.log(LogStatus.FAIL, " partially  read pages count are not available");	  
											basepagev2.takeScreenshot();           	
								}
																
								//navigate back to  home page from reader controls page							
								driver.navigate().back();
								Thread.sleep(2000);
								driver.navigate().back();
								Thread.sleep(2000);
								driver.navigate().back();
								Thread.sleep(2000);		
								driver.navigate().back();
								Thread.sleep(2000);	
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, " read button is not available  in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
									
		  //searching for my stuff tab and  click on mystuffTab 			 
			   Utilities.verticalSwipeReverse(driver);
			   Utilities.verticalSwipeReverse(driver);						  
			 			     			   
			   //click on MyStuff tab
				  homepagev2.tabClick("Watch");
				  Thread.sleep(1200000);
				  homepagev2.tabClick("My Stuff");
				  Thread.sleep(60000);
			  
		  //do vertical swipe till to last viewed tray in my stuff		  
			 for(int scroll=0;scroll<=4;scroll++)
			 {
						 if(Utilities.explicitWaitVisible(driver, listenpageV2.lastviewedtrayb, 2))
						 {							 															  
							       test.log(LogStatus.INFO, "  lastviewed tray is available");												     							   
							       break;						 
						 }	  
						 else
						 { 
							 Utilities.verticalSwipe(driver); 							 
						 } 
			 }
		  			 
		     //click on lastViewed tray in MyStuff tab			 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastviewedtrayb, 10))
			 {	 
				        test.log(LogStatus.INFO, "  lastviewed tray is available");
				    try{
				    	  listenpageV2.lastviewedtrayb.click();
				    	  test.log(LogStatus.INFO, " Clicked on Lastviewed Tray in Mystuff tab");
				       }catch(Exception e)
				       {
				    	   test.log(LogStatus.FAIL, "failed to  Click on Lastviewed Tray in Mystuff tab");
				           basepagev2.takeScreenshot();
				       }				 
			 }
			 else
			 {
				         test.log(LogStatus.FAIL," Lastviewed tray is not available after giving 3 minutes wait also");	
				         basepagev2.takeScreenshot();
			 }
							 
		    	 
			 //retrieve title of last updated(partially watched e-book)  e-book 	 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))				 
			 {		
				  test.log(LogStatus.INFO, "last updated book is  available");				 
				 try {
					    audiocard=listenpageV2.lastviewedintrayb.getAttribute("text").toString().trim();
			            audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
			            lastupdated=audiocard.toLowerCase();
			            System.out.println( lastupdated);
			            test.log(LogStatus.INFO, "sucessesfully retrieved last updated1 content title       "+lastupdated);
				      }catch(Exception e)
				     {
				    	  test.log(LogStatus.FAIL, "failed to retrieve  last updated1  content title ");
				            basepagev2.takeScreenshot();
				     }
			 }
			 else
			 {
			            test.log(LogStatus.FAIL, "last updated book is not  available ");
			            basepagev2.takeScreenshot();
			 }
			 
			 //verifying partially read e-book updated or not under last viewed my Stuff tab 
			 if(lastupdated.contains(booktitle4))
			 {
				      test.log(LogStatus.INFO, "partially watched E-book updated under last viewed MyStuff tab");	
				    
				        //click on last updated book			 
						 if(Utilities.explicitWaitClickable(driver,listenpageV2.lastviewedintrayb, 10))
						 {	 				        
							    try{
							    	listenpageV2.lastviewedintrayb.click();
							    	  test.log(LogStatus.INFO, " Clicked on last updated book ");
							    	  
							       }catch(Exception e)
							       {
							    	   test.log(LogStatus.FAIL, "failed to  Click on last updated book");
							           basepagev2.takeScreenshot();				           
							       }				 
						 }
						 
				    
				    
				    //click on read button in  book detail page 						 
					 if(Utilities.explicitWaitClickable(driver,readpagev2.readButton, 20))
					 {									
						    test.log(LogStatus.INFO, " read button is available in book detail page");
						    
								 try {
									 readpagev2.readButton.click();
									 
									  test.log(LogStatus.INFO, "Clicked on READ button");
										 for(int wait=0;wait<=30;wait++) //for loop 1 starting
										  {
										     try
										      {
												 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
												
												    if(wait==30)
												    {
													 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
													 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Read' button is Fail");
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
												
														test.log(LogStatus.INFO, "Verified the behavior when tapping on 'Read' button ");
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
										test.log(LogStatus.FAIL, "Unable to click on read button"); 
										basepagev2.takeScreenshot();
								      }	
								 						 
							       //double click on book pages to get reader controls						 
								 try{									    	   					    							    												    						    		
										Thread.sleep(3000);
										Utilities.tap(driver);
										Utilities.tap(driver);								
										test.log(LogStatus.INFO, "Double tapped on screen to launch reader controls");	
										
							    	}catch(Exception e)
						 	        {
							    		 test.log(LogStatus.FAIL, "failed to do Double tap on screen to launch reader controls");	
							    		 basepagev2.takeScreenshot();
							    	}	
							      							      
							          /*  // refreshApp													         
							            driver.runAppInBackground(Duration.ofSeconds(3));
							            test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							            driver.currentActivity();*/
							         														               									
							           if(Utilities.explicitWaitVisible(driver, ebookspagev2.readerCurrentChapter, 10))
										{
										           test.log(LogStatus.INFO, "partialy  read pages count is  available");
													try 
													{																							 																																		
															pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
															String  arr[]=pages.split(" ");
															numberofpagesread=arr[1].toString().trim();
															readpagesinlastviewdtray=Integer.parseInt(numberofpagesread);
															System.out.println(readpagesinlastviewdtray);											 
															test.log(LogStatus.INFO, "sucessesfully retrieved partially read ebook pages           "+ readpagesinlastviewdtray);
															
													}catch(Exception e)
													{
															test.log(LogStatus.FAIL, "failed to  retrieve partially read ebook pages       ");	  
															basepagev2.takeScreenshot();
													}												
										}
										else
										{
													test.log(LogStatus.FAIL, " partially  read pages count are not available");	  
													basepagev2.takeScreenshot();           	
										}
										
										//verifying play-back status for partially watched e-book in last viewed tray in my Stuff tab								
										 if(partiallyreadpages==readpagesinlastviewdtray)
										 {
											    test.log(LogStatus.INFO, "its resumed from last wtched page");
											    test.log(LogStatus.PASS, " Testcase:VK_1851-----Verify the playback status for partially watched downloaded Ebook content in Last viewed tray:' is passed");					    	
									    	    if(!Utilities.setResultsKids("VK_1851", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    				    	  
										 }
										 else
										 {
											 test.log(LogStatus.FAIL, "its failed to resume from last watched page");
										     test.log(LogStatus.FAIL, " Testcase:VK_1851-----Verify the playback status for partially watched downloaded Ebook content in Last viewed tray:' is failed");					    	
									    	    if(!Utilities.setResultsKids("VK_1851", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
									    	    basepagev2.takeScreenshot();
										 }								
										
										//navigate back to  last viewed page from reader controls page							
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
										
					 }
					 else
					 {
						        test.log(LogStatus.FAIL, " read button is not available  in book detail page"); 	
						        basepagev2.takeScreenshot();
					 }	
				    
				    
		 }
		 else
		 {
			    test.log(LogStatus.FAIL, "partially watched E-book faield to  update under last viewed MyStuff tab after givung 3 minute wait also");				     					    		
			    test.log(LogStatus.FAIL, " Testcase:VK_1851-----Verify the playback status for partially watched downloaded Ebook content in Last viewed tray:' is failed");					    	
	    	    if(!Utilities.setResultsKids("VK_1851", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
	    	    basepagev2.takeScreenshot();
		 }
			 			 
			   //navigate back to  home page from lastviewed page			 
			    driver.navigate().back();
				Thread.sleep(2000);	
							 
			 //do vertical swipe up till to get read tab			 
			 Utilities.verticalSwipeReverse(driver);
			 Utilities.verticalSwipeReverse(driver);		 
			 
//VK_1852-----Verify the play-back status for Completely watched down loaded Ebook content in Last viewed tray:
			
			 homepagev2.tabClick("Read");				 					 		    	
		     Thread.sleep(4000);		  
	
		     
		  // scroll to first Reads tray and click on First reads tray	 		
			 for(int scroll=1;scroll<=10;scroll++)
		     {												
				      if(Utilities.explicitWaitClickable(driver, ebookspagev2.bestreadstrayb, 1))
				      {									          	
				          try {
				        	         ebookspagev2.bestreadstrayb.click();			        	      
				                     test.log(LogStatus.INFO, "clicked on first tray");
				                     break;
				                     
				               }catch(Exception e)
				               {
				            	     test.log(LogStatus.FAIL, "failed to click on first tray");
				            	     basepagev2.takeScreenshot();
				               }									          
					           									           
			           }else   Utilities.verticalSwipe(driver);
	          } 
 	     
			 //click on card 3 under best reads page 
			 if(Utilities.explicitWaitClickable(driver, ebookspagev2.card5underbestreadspage, 10))
		     {							 				 
			          try {		        	  
			        	    bookname=ebookspagev2.card5underbestreadspage.getAttribute("text").toString().trim();
				 	        bookname=bookname.replaceAll("[^a-zA-Z0-9]", "");
					        booktitle5=bookname.toLowerCase();
					        test.log(LogStatus.INFO, "sucessesfully retrieved  Booktitle               "+booktitle5);
					        System.out.print(booktitle5);
			        	     ebookspagev2.card5underbestreadspage.click();			        	      
			                 test.log(LogStatus.INFO, "clicked on  card 5 under first tray page ");		                    		                     
			               }catch(Exception e)
			               {
			            	     test.log(LogStatus.FAIL, "failed to click on card5 under  first tray page");
			            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle5);
			            	     basepagev2.takeScreenshot();
			               }
		      }else
		      {
		    	         test.log(LogStatus.FAIL, " card5  is not Available in under  first tray page");
         	             basepagev2.takeScreenshot(); 
		      }
			 
			//doing vertical swipe down till to  download-book link in book detail page			 
			 if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
			 {								 
							 for(int scroll=1;scroll<=4;scroll++)
							 {											
									 if(Utilities.explicitWaitVisible(driver,ebookspagev2.downloadbooklinkb , 2))
									 {													 
										 test.log(LogStatus.INFO, "download book link is available"); 
										 break;
									 }
									 else
									 {
									     Utilities.verticalSwipe(driver);
									 }											 											 
							 }
			 }			 
			 else
		     {
			         test.log(LogStatus.FAIL, "preview  button is not available in book detail page");
			         basepagev2.takeScreenshot();
		     }
		 	
			 //click on download-book link in book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,ebookspagev2.downloadbooklinkb , 10))
			 {		
				   try{
					     ebookspagev2.downloadbooklinkb.click();
				         test.log(LogStatus.INFO, "clicked on download book link in book detail page"); 
				       }catch(Exception e) {}						      								  
			 }
			 else
			 {
				        test.log(LogStatus.FAIL, "failed to click on download book link in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
			 //doing vertical swipe up till to  read or preview button in book detail page			 
			 for(int scroll=1;scroll<=4;scroll++)
			 {											
					 if(Utilities.explicitWaitVisible(driver,  readpagev2.previewButton  , 2) || Utilities.explicitWaitVisible(driver,  readpagev2.readButton  , 2) )
					 {													 
						   test.log(LogStatus.INFO, "read button or preview button is  available"); 
						   break;
					 }
					 else
					 {
						  Utilities.verticalSwipeReverse(driver);
					 }											 											 
			 }
			 
            //click on read button in  book detail page 			 
			 if(Utilities.explicitWaitClickable(driver,readpagev2.readButton, 600))
			 {									
				    test.log(LogStatus.INFO, " read button is available in book detail page");
						 try {
							 readpagev2.readButton.click();
							  test.log(LogStatus.INFO, "Clicked on READ button");
								 for(int wait=0;wait<=30;wait++) //for loop 1 starting
								  {
								     try
								      {
										 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
										
										    if(wait==30)
										    {
											 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
											 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Read' button is Fail");
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
										
												test.log(LogStatus.INFO, "Verified the behavior when tapping on 'Read' button ");
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
								test.log(LogStatus.FAIL, "Unable to click on read button"); 
								basepagev2.takeScreenshot();
						      }	
						 						 
					       //double click on book pages to get reader controls
						 
						 try{									    	   					    							    												    						    		
								Thread.sleep(3000);
								Utilities.tap(driver);
								Utilities.tap(driver);								
								test.log(LogStatus.INFO, "Double tapped on screen to launch reader controls");	
								
					    	}catch(Exception e)
				 	        {
					    		 test.log(LogStatus.FAIL, "failed to do Double tap on screen to launch reader controls");	
					    		 basepagev2.takeScreenshot();
					    	}	
					      		/*					      
					            // refreshApp													         
					            driver.runAppInBackground(Duration.ofSeconds(3));
					            test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					            driver.currentActivity();*/
					            
					            //retrieving total pages of e-book  
					            if(Utilities.explicitWaitVisible(driver,ebookspagev2.readerCurrentChapter, 10))
								{
								           test.log(LogStatus.INFO, "total pages count is  available");
											try 
											{																								 																																		
												    pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
											        String  arr[]=pages.split(" ");
													numberofpagesread=arr[1].toString().trim();
													totlalpages=Integer.parseInt(numberofpagesread);
													System.out.println(totlalpages);											 
													test.log(LogStatus.INFO, "sucessesfully retrieved completely  read ebook pages           "+ totlalpages);
													
											}catch(Exception e)
											{
													test.log(LogStatus.FAIL, "failed to  retrieve completely  read ebook pages       "+totlalpages);	  
													basepagev2.takeScreenshot();
											}												
								}
								else
								{
											test.log(LogStatus.FAIL, " total pages count is not available");	  
											basepagev2.takeScreenshot();           	
								}	
					            
				                //read e-book completely				       				         
								if(Utilities.explicitWaitVisible(driver, ebookspagev2.ebookseekBar, 10))
								{
									     test.log(LogStatus.INFO, "ebook seek bar is available");	
									     Utilities.scrubtillend(driver, ebookspagev2.ebookseekBar);							    
									     Thread.sleep(3000);
						        }
							    else
							    {
							            test.log(LogStatus.FAIL, "ebookseekbar is not availble");
							            basepagev2.takeScreenshot();
						        }   
																      																								
								//navigate back to  home page from read-again page						
								driver.navigate().back();
								Thread.sleep(2000);
								driver.navigate().back();
								Thread.sleep(2000);
								driver.navigate().back();
								Thread.sleep(2000);	
								driver.navigate().back();
								Thread.sleep(2000);	
								driver.navigate().back();
								Thread.sleep(2000);	
								driver.navigate().back();
								Thread.sleep(2000);
			 }
			 else				 
			 {
				        test.log(LogStatus.FAIL, " read button is not available  in book detail page"); 	
				        basepagev2.takeScreenshot();
			 }	 
			 
									
		     //searching for my stuff tab and  click on mystuffTab 			 
			   Utilities.verticalSwipeReverse(driver);
			   Utilities.verticalSwipeReverse(driver);						  
			 
			      //click on MyStuff tab
				  homepagev2.tabClick("Watch");
				  Thread.sleep(120000);
				  homepagev2.tabClick("My Stuff");
				  Thread.sleep(60000);
			  
		     //do vertical swipe till to last viewed tray in my stuff		  
			 for(int scroll=0;scroll<=4;scroll++)
			 {
						 if(Utilities.explicitWaitVisible(driver, listenpageV2.lastviewedtrayb, 2))
						 {							 															  
							       test.log(LogStatus.INFO, "  lastviewed tray is available");												     							   
							       break;						 
						 }	  
						 else
						 { 
							 Utilities.verticalSwipe(driver); 							 
						 } 
			 }
		  			 
		     //click on lastViewed tray in MyStuff tab			 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastviewedtrayb, 10))
			 {	 
				        test.log(LogStatus.INFO, "  lastviewed tray is available");
				    try{
				    	  listenpageV2.lastviewedtrayb.click();
				    	  test.log(LogStatus.INFO, " Clicked on Lastviewed Tray in Mystuff tab");
				       }catch(Exception e)
				       {
				    	   test.log(LogStatus.FAIL, "failed to  Click on Lastviewed Tray in Mystuff tab");
				           basepagev2.takeScreenshot();
				       }				 
			 }
			 else
			 {
				         test.log(LogStatus.FAIL," Lastviewed tray is not available after giving 3 minutes wait also");	
				         basepagev2.takeScreenshot();
			 }
							 		    	 
			 //retrieve title of last updated(completely watched e-book)  e-book 	 
			 if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))				 
			 {		
				     test.log(LogStatus.INFO, "last updated book is  available");				 
				 try {
					    audiocard=listenpageV2.lastviewedintrayb.getAttribute("text").toString().trim();
			            audiocard= audiocard.replaceAll("[^a-zA-Z0-9]","");
			            lastupdated=audiocard.toLowerCase();
			            System.out.println( lastupdated);
			            test.log(LogStatus.INFO, "sucessesfully retrieved last updated content title       "+lastupdated);
				      }catch(Exception e)
				     {
				    	  test.log(LogStatus.FAIL, "failed to retrieve  last updated  content title   ");
				            basepagev2.takeScreenshot();
				     }
			 }
			 else
			 {
			            test.log(LogStatus.FAIL, "last updated book is not  available  ");
			            basepagev2.takeScreenshot();
			 }
			 
			 //verifying completely read e-book updated or not under last viewed my Stuff tab 
			 if(lastupdated.contains(booktitle5))
			 {
				    test.log(LogStatus.INFO, "completely watched E-book updated under last viewed MyStuff tab");	
				    
				    //click on last updated book			 
					 if(Utilities.explicitWaitClickable(driver,listenpageV2.lastviewedintrayb, 10))
					 {	 				        
						    try{
						    	listenpageV2.lastviewedintrayb.click();
						    	  test.log(LogStatus.INFO, " Clicked on last updated book ");
						    	  
						       }catch(Exception e)
						       {
						    	   test.log(LogStatus.FAIL, "failed to  Click on last updated book");
						           basepagev2.takeScreenshot();				           
						       }				 
					 }
					 
					  //click on read button in  book detail page 			 
					 if(Utilities.explicitWaitClickable(driver,readpagev2.readButton, 20))
					 {									
						    test.log(LogStatus.INFO, " read button is available in book detail page");
								 try {
									 readpagev2.readButton.click();
									  test.log(LogStatus.INFO, "Clicked on READ button");
										 for(int wait=0;wait<=30;wait++) //for loop 1 starting
										  {
										     try
										      {
												 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
												
												    if(wait==30)
												    {
													 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");										
													 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Read' button is Fail");
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
												
														test.log(LogStatus.INFO, "Verified the behavior when tapping on 'Read' button ");
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
										test.log(LogStatus.FAIL, "Unable to click on read button"); 
										basepagev2.takeScreenshot();
								      }	
								 						 
							       //double click on book pages to get reader controls								 
								 try{									    	   					    							    												    						    		
										Thread.sleep(3000);
										Utilities.tap(driver);
										Utilities.tap(driver);								
										test.log(LogStatus.INFO, "Double tapped on screen to launch reader controls");	
										
							    	}catch(Exception e)
						 	        {
							    		 test.log(LogStatus.FAIL, "failed to do Double tap on screen to launch reader controls");	
							    		 basepagev2.takeScreenshot();
							    	}	
							      							      
							           /* // refreshApp													         
							            driver.runAppInBackground(Duration.ofSeconds(3));
							            test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							            driver.currentActivity();
							         								*/		
						               							      
								       if(Utilities.explicitWaitVisible(driver, ebookspagev2.readerCurrentChapter, 10))
										{
										           test.log(LogStatus.INFO, "total pages count is  available");
													try 
													{																							 																																		
															pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
															String  arr[]=pages.split(" ");
															numberofpagesread=arr[1].toString().trim();
															readpagesinlastviewdtray=Integer.parseInt(numberofpagesread);
															System.out.println(readpagesinlastviewdtray);											 
															test.log(LogStatus.INFO, "sucessesfully retrieved completely read ebook pages from last viewed tray           "+ readpagesinlastviewdtray);
															
													}catch(Exception e)
													{
															test.log(LogStatus.FAIL, "failed to  retrieve completely read ebook pages from last viewed tray ");	  
															basepagev2.takeScreenshot();
													}												
										}
										else
										{
													test.log(LogStatus.FAIL, " total  pages count are not available");	  
													basepagev2.takeScreenshot();           	
										}
												
										//verifying play-back status for completely watched e-book in last viewed tray in my Stuff tab										
										 if(totlalpages==readpagesinlastviewdtray)
										 {
											    test.log(LogStatus.INFO, "its resumed from last wtched page");
											    test.log(LogStatus.PASS, " Testcase:VK_1852-----Verify the play-back status for Completely watched down loaded Ebook content in Last viewed tray:' is passed");					    	
									    	    if(!Utilities.setResultsKids("VK_1852", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    				    	  
										 }
										 else
										 {
											 test.log(LogStatus.FAIL, "its failed to resume from last watched page");
										       test.log(LogStatus.FAIL, " Testcase:VK_1852-----Verify the play-back status for Completely watched down loaded Ebook content in Last viewed tray:' is failed");					    	
									    	    if(!Utilities.setResultsKids("VK_1852", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
									    	    basepagev2.takeScreenshot();
										 }								
										
										//navigate back to  home page from reader again page							
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);	
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);	
										driver.navigate().back();
										Thread.sleep(2000);
					 }
					 else
					 {
						        test.log(LogStatus.FAIL, " read button is not available  in book detail page"); 	
						        basepagev2.takeScreenshot();
					 }	
				    
				    
		 }
		 else
		 {
			    test.log(LogStatus.FAIL, "completely watched E-book faield to  update under last viewed MyStuff tab after giving 3 inutes wait also");				     					    		
			    test.log(LogStatus.FAIL, " Testcase:VK_1852-----Verify the play-back status for Completely watched down loaded Ebook content in Last viewed tray:' is failed");					    	
	    	    if(!Utilities.setResultsKids("VK_1852", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
	    	    basepagev2.takeScreenshot();
		 }
			 
			 
			
}

		@DataProvider
		public Object[][] getData()
		{
		return DataUtil.getData(testName,xls);											          					
	    }  
}