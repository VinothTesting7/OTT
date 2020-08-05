package com.viacom.downloads;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

//AuthorName=Bhaskar
public class DownloadFunctionalityandLastreadUpdation extends BaseTestV2
{
		//VK_428---------Verify the Functionality of 'Download' button in Download overlay				 				
		//VK_429---------Verify the behaviour post downloading a content from Download overlay flow			
		//VK_1686--------Verify the playback status for partially watched Ebook content in Last viewed tray:			
		//VK_1906--------Verify if the Ebook cards are added to the 1st position Last viewed tray if user plays the same content again from LV		 
			
		String testName = "DownloadFunctionalityandLastreadUpdation";		
		private Object bookNameBefore;
		@Test(dataProvider = "getData")
		public void DownloadFunctionalityandLastreadUpdation(Hashtable<String, String> data) throws Exception 
		{				
			test = rep.startTest("DownloadFunctionalityandLastreadUpdation");
			test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
			  
			      // Check run mode
			       if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N"))
			       {
				    test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
				    throw new SkipException("Skipping the test as Run Mode was: NO");
			        } 
			       
			       //Launch VootkidsApp();				
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
					
					 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
					 EbooksPageV2 ebookspagev2= new EbooksPageV2(driver,test);
					SearchPageV2 searchpagev2=new SearchPageV2(driver,test);
					 
					 	
					   String  lastupdatedtitle1="",lastupdatedtitle2="",  lastupdatedtitle3="",lastupdated4="", lastupdatedtitle4="", lastupdatedtitle5="";
					  
					   String  lastread=""  ,lastviewed1="",lastviewed2="" ,lastviewed3="", lastviewedfirsttitle1="", storiescount="",  storiescount1="",  storiescount2="",	bookname1="";
	                
					   String audiocard="",  title="", audiocard1="",booktitle1="",booktitle2="", audiocard2="" , audiocard3="" ,storiescount3="",bookname3="",bookname=""; 
	               
					   int updatedcount2=0 , 	readpagesofebookinlastviewdtray=0, updatedcount3=0,totalpagesreadofebook=0,readpagesofebook=0, c=0 ,count=0  ,updatedcount1=0;   
	                
					  String bookname2="",numberofpagesread="", totalpagesread="", pages="" , booktitlename="",booktitle3="",booktitle4="",booktitle5=""; 
					  
					  WebElement booktitle=null;  
					
					  boolean readerDisplayedPreview=false; 
					  
					    //login with Valid credentials					  
					    homepagev2.login(data.get("Email"),data.get("Password"));
					  
					   //deleting all downloads under downLoads Tab				    
				        downloadsv2.deleteAllDownloads();	 
				       
//VK_428----Verify the Functionality of 'Download' button in Download overlay
					  		
				        Utilities.verticalSwipeReverse(driver);					
						Utilities.verticalSwipeReverse(driver);
													            
						homepagev2.tabClick("Read");
						Thread.sleep(4000);
						
						// scroll to first  reads tray and click on first reads tray		 		
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
			 			 
						 //click on card 1 under first tray page 
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
					    	     test.log(LogStatus.FAIL, " card 1  is not Available  under first tray page ");
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
										     test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Preview' button is Fail because App stucked");
										     BasePageV2.takeScreenshot();
										      driver.navigate().back();
										      Thread.sleep(2000);
										      driver.navigate().back();
										      Thread.sleep(2000);
										      driver.navigate().back();
										      test.log(LogStatus.INFO, "Navigated back to last viewed page");
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
					 				 						 
				                    boolean download=false;    
				                     
		                           //preview e-book pages completely		                     
				                    for(int i=1;i<=30;i++)				    		
							    	{	
								    	  Thread.sleep(3000);
					                      Utilities.horizontalSwipe(driver);
							    	}
								    				    				    						    		
						    		if(Utilities.explicitWaitVisible(driver, ebookspagev2.downloadbuttonb, 10))
						    		{
						    			  test.log(LogStatus.INFO, "down load button is available  after previewing E-book  pages completely ");
						    			  
						    			  try {
						    				  ebookspagev2.downloadbuttonb.click();
						    				  test.log(LogStatus.INFO, "clicked on download button in download page,  after previewing E-book  pages completely ");
						    			      }catch(Exception e)
						    			     {
						    			    	  test.log(LogStatus.FAIL, "clicked on download button in download page,  after previewing E-book  pages completely ");
						    			    	  basepagev2.takeScreenshot();
						    			     }
						    		     								    		
						    		}
						    		else
						    		{
						    			   test.log(LogStatus.FAIL, "down load button is not available  after previewing E-book  pages completely ");
						    			   basepagev2.takeScreenshot();
						    		}
								       
							        //verify the availability of cancelButton,downloading text and progressbar   				       					       
							        if(Utilities.explicitWaitVisible(driver, ebookspagev2.cancelbuttonb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingprogressbarb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingtextb, 10) ) 											   
								    {			   
								    	    test.log(LogStatus.PASS, "VK_428----Verify the Functionality of 'Download' button in Download overlay");
										    if(!Utilities.setResultsKids("VK_428", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 	
										    
										    //navigate back to reader page from download page							    
										     driver.navigate().back();
										     Thread.sleep(3000);								    								    
								    }
							        else
							        {							        	
							        	    test.log(LogStatus.WARNING, "unable to verify because downlaoding gets completed with in fraction of milliseconds");
							        	    test.log(LogStatus.WARNING, "VK_428----Verify the Functionality of 'Download' button in Download overlay");
										    if(!Utilities.setResultsKids("VK_428", "WARNING")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
										    basepagev2.takeScreenshot();	
							        }
							        
							       //navigate back to FirstTrayReads page	 from Reader page       
			    	                driver.navigate().back();
			    	                Thread.sleep(2000);
				                     driver.navigate().back();	
				                    Thread.sleep(2000);					                  				                   							          					           
		   }				
		  else
		   {
			    test.log(LogStatus.FAIL, "unable to verify because preview button is not available");
      	        test.log(LogStatus.FAIL, "VK_428----Verify the Functionality of 'Download' button in Download overlay");
			    if(!Utilities.setResultsKids("VK_428", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");    
			    BasePageV2.takeScreenshot();	
		   }
			
		         
		         
//VK_429-----Verify the behaviour post downloading a content from Download overlay flow		
								
											      
			     //click on card 2 under first tray page 
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
				            	     test.log(LogStatus.FAIL, "failed to click on card 2 under first tray page ");
				            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle2);
				            	     basepagev2.takeScreenshot();
				               }
			      }else
			      {
			    	     test.log(LogStatus.FAIL, "card 2  is not Available  under first tray page ");
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
														               
										   //preview e-book pages completely		                     
						                    for(int i=1;i<=30;i++)				    		
									    	{	
										    	  Thread.sleep(3000);
							                      Utilities.horizontalSwipe(driver);
									    	}
										    				    				    						    		
								    		if(Utilities.explicitWaitVisible(driver, ebookspagev2.downloadbuttonb, 10))
								    		{
								    			  test.log(LogStatus.INFO, "down load button is available  after previewing E-book  pages completely ");
								    			  
								    			  try {
								    				  ebookspagev2.downloadbuttonb.click();
								    				  test.log(LogStatus.INFO, "clicked on download button in download page,  after previewing E-book  pages completely ");
								    			      }catch(Exception e)
								    			     {
								    			    	  test.log(LogStatus.FAIL, "clicked on download button in download page,  after previewing E-book  pages completely ");
								    			    	  basepagev2.takeScreenshot();
								    			     }
								    		     								    		
								    		}
								    		else
								    		{
								    			   test.log(LogStatus.FAIL, "down load button is not available  after previewing E-book  pages completely ");
								    			   basepagev2.takeScreenshot();
								    		}						       
						      			
									       //close button in reader page									       
									       if(Utilities.explicitWaitClickable(driver, ebookspagev2.ebookspageclosebuttonb,180))
									       {
									    	      
									    	      test.log(LogStatus.INFO, "after complete downloading scucessesfully landed on reader page(which is still unread)");
									    	      test.log(LogStatus.PASS, "VK_429-----Verify the behaviour post downloading a content from Download overlay flow	");
												  if(!Utilities.setResultsKids("VK_429", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 									      									    	     
									             
												  //navigate back to book detail page from reader page
												  driver.navigate().back();
												  Thread.sleep(2000);
												  
									       }									       
									       else if(Utilities.explicitWaitVisible(driver, ebookspagev2.closebuttonindownloadpage,20)) 
								    	   {
									    	     test.log(LogStatus.FAIL, "failed to complete down laod because  downlaoding gets stucked in down load page after clicking on download button");								    	   
								        	     test.log(LogStatus.FAIL, "VK_429-----Verify the behaviour post downloading a content from Download overlay flow	");
											     if(!Utilities.setResultsKids("VK_429", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
								                 basepagev2.takeScreenshot();
								                 
								                 //navigate back to book detail page from down load page
								                  driver.navigate().back();
								                  Thread.sleep(2000);
								                  driver.navigate().back();
												  Thread.sleep(2000);
								                
								    	   }
									       else
									       {
									    	     test.log(LogStatus.FAIL, "close button is not available in reader page");
									    	     test.log(LogStatus.FAIL, "VK_429-----Verify the behaviour post downloading a content from Download overlay flow	");
											     if(!Utilities.setResultsKids("VK_429", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
								                 basepagev2.takeScreenshot(); 
								                 
								                 //navigate back to book detail page from reader page
												  driver.navigate().back();
												  Thread.sleep(2000);
									       }	
									       									      
						}   
						else
						{
							    test.log(LogStatus.FAIL, " preview button is not available");
					    	   basepagev2.takeScreenshot();		
						}
						
				    	//navigate back to First Tray Reads page from book detail page          						
						 driver.navigate().back();
				         Thread.sleep(2000);
				        
//VK_1686---Verify the play back status for partially watched Ebook content in Last viewed tray:		         
				         
				         
				         //click on card 3 under first tray page 
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
						            	     test.log(LogStatus.FAIL, "failed to click on card 3 under first tray page ");
						            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle3);
						            	     basepagev2.takeScreenshot();
						               }
					      }else
					      {
					    	     test.log(LogStatus.FAIL, "card 3  is not Available  under first tray page ");
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
												
														test.log(LogStatus.INFO, "Verifyed the behavior when tapping on 'Preview' button ");
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
								 
                                                   //swipe e-book pages partially
								 
												       for(int i=1;i<=2;i++)
										               {				
												    	            Thread.sleep(3000);
																	Utilities.horizontalSwipe(driver); 		
																	c=c+1;
										               }	
												       
												       //navigate back to book detail page from reader page												       
												        driver.navigate().back();
												        Thread.sleep(2000);	         
				         
							}
							else
							{
								test.log(LogStatus.FAIL, "preview button is not available"); 
								basepagev2.takeScreenshot();
							}
							
						      //navigate back to home page from book detail page							
							   driver.navigate().back();
						       Thread.sleep(2000);
						       driver.navigate().back();
						       Thread.sleep(2000);
													       						       
						        Utilities.verticalSwipeReverse(driver);
						        Utilities.verticalSwipeReverse(driver);
						         						        
	       //verify play back status of partially previewed e-book in last read tray		        
						         //do vertical swipe till to read tab						        
						         homepagev2.tabClick("Watch");
						         Thread.sleep(180000);
						         homepagev2.tabClick("Read");
						         Thread.sleep(120000);
						         						            
							        //click on LAST READ TRAY UNDER READ TAB						         						         
						             for(int i=1;i<=20;i++)
							         {
									        	 if(Utilities.explicitWaitClickable(driver, ebookspagev2.lastreadtrayinreadtab, 2))
											     {										        		 
									        		       test.log(LogStatus.INFO, "last read tray available in  read tab");
										        		   try{
											        	        ebookspagev2.lastreadtrayinreadtab.click();
											        	        test.log(LogStatus.INFO, "clicked on last read tray in read tab");
										        		      }catch(Exception e)
										        		      {
										        		    	  test.log(LogStatus.FAIL, " failed to click on last read tray in read tab");
										        		    	  basepagev2.takeScreenshot();
										        		      }
										        		   break;
											     }
										         else
										         {
										        	      Utilities.verticalSwipe(driver);				        	 
										         }
						          }
						         								
					              //click on  card1 in last read page from  read tab  						         
			   			         if(Utilities.explicitWaitClickable(driver, ebookspagev2.card1underLastreadspage, 10))
			   				     {				       			   			        	 
			   			        	           ebookspagev2.card1underLastreadspage.click();
			   					        	
			   					        	   test.log(LogStatus.INFO, "clicked on last updated book in last read page");
			   				     }
			   			         else
			   			         {			        	
			   			        	          test.log(LogStatus.FAIL, "failed to click on last updated book in last read page"); 
			   			        	          basepagev2.takeScreenshot();
			   			         } 
									
			   			          			   			         
			   			         //retrieve book title from book detail page								 
								 if(Utilities.explicitWaitVisible(driver,bookspagev2.bookDetailPageBookTitle, 20))
							     {	
									     lastupdatedtitle1=bookspagev2.bookDetailPageBookTitle.getAttribute("text").toString().trim();
									     lastupdatedtitle1=lastupdatedtitle1.replaceAll("[^a-zA-Z0-9]", "");
									     lastupdatedtitle1=lastupdatedtitle1.toLowerCase();
						        	     test.log(LogStatus.INFO, "sucessesfully retrieved book title from book detail page  after clicking on book in last read page   "+lastupdatedtitle1);
							     }
						         else
						         {
						        	      test.log(LogStatus.FAIL, "failed to retrieve book title from book detail page after clicking on book in last read page ");
						        	      basepagev2.takeScreenshot();				        	 
						         }
								 			   			         
			   			         //compare same book updated or not in last read page 									
			   			         if( lastupdatedtitle1.contains(booktitle3))
			   				     {				            			   					        	 
			   					                test.log(LogStatus.INFO, "same book updated in last read page");
			   					        	   
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
			   														
			   																test.log(LogStatus.INFO, "Verifyed the behavior when tapping on 'Preview' button ");
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
			   									     			   									 			   					              
				   									     //double tapping to get reader controls			   										 
				   									    try {
				   									    	    Utilities.tap(driver);
				   									    	    Utilities.tap(driver);
				   									    	    test.log(LogStatus.INFO,  " sucessesfully  double tapped ");
				   									    	
				   									        }catch(Exception e)		   									        
				   									        {
				   									        	 test.log(LogStatus.FAIL, "failed to do double tapping");
				   									        	 basepagev2.takeScreenshot();
				   									        }
				   					         
					   									   if(Utilities.explicitWaitVisible(driver, ebookspagev2.readerCurrentChapter, 10))
															{
														                   test.log(LogStatus.INFO, " read pages count is  available");
																	try {																								 																																		
																		    pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
																		    String  arr[]=pages.split(" ");
																		    totalpagesread=arr[1].toString().trim();
																		    totalpagesreadofebook=Integer.parseInt(totalpagesread);
																		    System.out.println(totalpagesreadofebook);											 
																		    test.log(LogStatus.INFO, "totalpages of readbook is   "+totalpagesreadofebook);
																		    		
																	    }catch(Exception e)
																	    {
																		    test.log(LogStatus.FAIL, "failed to  retrieve read ebook pages from last viewed tray");	  
																		    basepagev2.takeScreenshot();
																	    }												
														    }
															else
															{
																test.log(LogStatus.FAIL, "  read pages count are not available");	  
															    basepagev2.takeScreenshot();           	
															}
																	                                                   
					   										//verifying play back status for partially previewed book				   										
					   									    if(totalpagesreadofebook==c+1)
					   										{
					   									    	  test.log(LogStatus.PASS, "VK_1686---Verify the playback status for partially watched Ebook content in Last viewed tray:");
						   									      if(!Utilities.setResultsKids("VK_1686", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");    	
						   										
					   										}
					   										else
					   										{
					   											 test.log(LogStatus.FAIL, "VK_1686----Verify the playback status for partially watched Ebook content in Last viewed tray:");
						   									     if(!Utilities.setResultsKids("VK_1686", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");    	
						   										 basepagev2.takeScreenshot();
					   										}
	                                                       
					   									    //navigate back to last read page from readerControls page				   									    				   									    
					   									     driver.navigate().back();
					   									     Thread.sleep(2000);
					   									     driver.navigate().back();
					   									     Thread.sleep(2000);
					   									     driver.navigate().back();
					   									     Thread.sleep(2000);
			   								 }
			   								else
			   							     {
			   										test.log(LogStatus.FAIL, "preview button is not available"); 
	   												basepagev2.takeScreenshot();
			   								 }
			   									
			   													   						   	
			  				     }
			   			         else
			   			         {			        	
			   			        	    test.log(LogStatus.FAIL, "failed to update last read book after giving 5 minutes wait also"); 
			   			        	    basepagev2.takeScreenshot();
			   			        	    
			   			        	    //navigate back to last read page from book detail page
			   			        	     driver.navigate().back();
			   			        	     Thread.sleep(2000);
			   			        	    
			   			         } 
				       				       				     									
//VK_1906-------- Verify if the Ebook cards are added to the 1st position Last viewed tray if user plays the same content again from LV		 
			              				
				                              			   			         
              		  //retrieve  card1 title from last read page in read tab 				        
			         if(Utilities.explicitWaitVisible(driver, ebookspagev2.card1underbestreadspage, 10))
				     {				            
					        	   lastupdatedtitle1=ebookspagev2.card1underbestreadspage.getAttribute("text").toString().trim();
					        	   lastupdatedtitle1=lastupdatedtitle1.replaceAll("[^a-zA-Z0-9]", "");
					        	   lastupdatedtitle1=lastupdatedtitle1.toLowerCase();
					        	   test.log(LogStatus.INFO, "sucessesfully retrieved book  lastupdatedtitle1 in last read tray"    +lastupdatedtitle1);
				     }
			         else
			         {			        	
			        	          test.log(LogStatus.FAIL, "failed to retrieve lastupdatedtitle1"); 
			        	          basepagev2.takeScreenshot();
			         } 
                        
			         //retrieve  card2 title from last read page in read tab 				        
			         if(Utilities.explicitWaitVisible(driver, ebookspagev2.card2underbestreadspage, 10))
				     {				            
					        	   lastupdatedtitle2=ebookspagev2.card2underbestreadspage.getAttribute("text").toString().trim();
					        	   lastupdatedtitle2=lastupdatedtitle2.replaceAll("[^a-zA-Z0-9]", "");
					        	   lastupdatedtitle2=lastupdatedtitle2.toLowerCase();
					        	   test.log(LogStatus.INFO, "sucessesfully retrieved book  lastupdatedtitle2 in last read page"         +lastupdatedtitle2);
				     }
			         else
			         {			        	
			        	        test.log(LogStatus.FAIL, "failed to retrieve lastupdatedtitle2"); 
			        	        basepagev2.takeScreenshot();
			         }   
                       			         
			         //retrieve card3 title from last read page in read tab 				        
			         if(Utilities.explicitWaitVisible(driver, ebookspagev2.card3underbestreadspage, 10))
				     {	
			            
					        	   lastupdatedtitle3=ebookspagev2.card3underbestreadspage.getAttribute("text").toString().trim();
					        	   lastupdatedtitle3=lastupdatedtitle3.replaceAll("[^a-zA-Z0-9]", "");
					        	   lastupdatedtitle3=lastupdatedtitle3.toLowerCase();
					        	   test.log(LogStatus.INFO, "sucessesfully retrieved book  lastupdatedtitle3 in last read page           "+lastupdatedtitle3);
				     }
			         else
			         {			        	
			        	        test.log(LogStatus.FAIL, "failed to retrieve lastupdatedtitle3"); 
			        	        basepagev2.takeScreenshot();
			         }
        			        			         
			         //retrieve card4 title from last read page in read tab 				        
			         if(Utilities.explicitWaitVisible(driver, ebookspagev2.card4underLastreadspage, 10))
				     {	
			            
					        	   lastupdatedtitle4=ebookspagev2.card4underLastreadspage.getAttribute("text").toString().trim();
					        	   lastupdatedtitle4=lastupdatedtitle4.replaceAll("[^a-zA-Z0-9]", "");
					        	   lastupdatedtitle4=lastupdatedtitle4.toLowerCase();
					      	       test.log(LogStatus.INFO, "sucessesfully retrieved book  lastupdatedtitle4 in last read page       "+lastupdatedtitle4);
				     }
			         else
			         {			        	
			        	        test.log(LogStatus.FAIL, "failed to retrieve lastupdatedtitle4"); 
			        	        basepagev2.takeScreenshot();
			         }
			         
			         //retrieve card5 title from last read page in read tab 				        
			         if(Utilities.explicitWaitVisible(driver, ebookspagev2.card5underLastreadspage, 10))
				     {				            
					        	   lastupdatedtitle5=ebookspagev2.card5underLastreadspage.getAttribute("text").toString().trim();
					        	   lastupdatedtitle5=lastupdatedtitle5.replaceAll("[^a-zA-Z0-9]", "");
					        	   lastupdatedtitle5=lastupdatedtitle5.toLowerCase();
					      	       test.log(LogStatus.INFO, "sucessesfully retrieved book  lastupdatedtitle4 in last read page       "+lastupdatedtitle5);
				     }
			         else
			         {			        	
			        	        test.log(LogStatus.FAIL, "failed to retrieve lastupdatedtitle5"); 
			        	        basepagev2.takeScreenshot();
			         }
			         
			         			         			    			         
			         //comparing the book titles under last read page  with which we read first in  First reads page			         
			         if(lastupdatedtitle1.contains(booktitle1))
				     {
			        	   ebookspagev2.card1underLastreadspage.click(); 
			        	   test.log(LogStatus.INFO,"last updated matched  with first played book in best reads page");
			        	 
				     }
			         else if(lastupdatedtitle2.contains(booktitle1))
				     {
			        	    ebookspagev2.card2underLastreadspage.click(); ;
			        	    test.log(LogStatus.INFO,"last second updated matched  with first played book in best reads page");
				     }
			         else if(lastupdatedtitle3.contains(booktitle1))
			         {
			        	    ebookspagev2.card3underLastreadspage.click();  
			        	    test.log(LogStatus.INFO,"last third updated matched  with first played book in best reads page");
			         }
			         else if(lastupdatedtitle4.contains(booktitle1))
			         {			        	 
			        	   ebookspagev2.card4underLastreadspage.click();   
			        	   test.log(LogStatus.INFO,"last third updated matched  with first played book in best reads page"); 			        	 			        	
			         }
			         else if(lastupdatedtitle5.contains(booktitle1))
			         {			        	 
			        	     ebookspagev2.card4underLastreadspage.click();   
			        	     test.log(LogStatus.INFO,"last third updated matched  with first played book in best reads page"); 			        	 
			        	   
			         }else
			         {
			        	    test.log(LogStatus.FAIL," first watched book failed to update in lastRead page after giving 5 minutes wait also");
			        	    test.log(LogStatus.FAIL,"no book  matched  with first watched book in best reads page");
			        	   basepagev2.takeScreenshot(); 
			         }
			         			         
			           //started reading  the same E-book which we read first in best reads page 		            
						if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 20))
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
						     
							 
	                                  try{
								              Thread.sleep(3000);
				                               Utilities.horizontalSwipe(driver);	
				                               Thread.sleep(3000);
				                               Utilities.horizontalSwipe(driver);
				                               test.log(LogStatus.INFO, "swiped ebook pages partially"); 
				                               
	                                     }catch(Exception e)
	                                     {
	                                    	     test.log(LogStatus.FAIL, "failed to swipe ebook pages "); 	
	                                     	      basepagev2.takeScreenshot();
	                                     }
	                           
				                         //navigate back to book detail page  
	                                      driver.navigate().back();
	                                      Thread.sleep(2000);	                                      
				     }else
				     {
				    	 
				     }
					 
								      
					   //navigate back to home page from book detail page 						
                        driver.navigate().back();
                        Thread.sleep(2000);
                        driver.navigate().back();
                        Thread.sleep(2000);
                        
                     //do vertical swipe up                       
                     Utilities.verticalSwipeReverse(driver);
                     Utilities.verticalSwipeReverse(driver);
                     Utilities.verticalSwipeReverse(driver);
                     
                     //do vertical swipe till to read tab						        
			         homepagev2.tabClick("Watch");
			         Thread.sleep(120000);
			         homepagev2.tabClick("Read");
			         Thread.sleep(120000);
			         						            
			        //click on LAST READ TRAY UNDER READ TAB						         						         
		             for(int i=1;i<=20;i++)
			         {
					        	 if(Utilities.explicitWaitClickable(driver, ebookspagev2.lastreadtrayinreadtab, 2))
							     {										        		 
					        		       test.log(LogStatus.INFO, "last read tray available in  read tab");
						        		   try{
							        	        ebookspagev2.lastreadtrayinreadtab.click();
							        	        test.log(LogStatus.INFO, "clicked on last read tray in read tab");
						        		      }catch(Exception e)
						        		      {
						        		    	  test.log(LogStatus.FAIL, " failed to click on last read tray in read tab");
						        		    	  basepagev2.takeScreenshot();
						        		      }
						        		   break;
							     }
						         else
						         {
						        	      Utilities.verticalSwipe(driver);				        	 
						         }
		          }
			         								
		             //click on  card1 in last read page  						         
   			         if(Utilities.explicitWaitClickable(driver, ebookspagev2.card1underLastreadspage, 10))
   				     {				       			   			        	 
   			        	           ebookspagev2.card1underLastreadspage.click();  					        	
   					        	   test.log(LogStatus.INFO, "clicked on last updated book in last read page");
   				     }
   			         else
   			         {			        	
   			        	          test.log(LogStatus.FAIL, "failed to click on last updated book in last read page"); 
   			        	          basepagev2.takeScreenshot();
   			         } 
						   			          			   			         
   			         //retrieve book title from book detail page								 
					 if(Utilities.explicitWaitVisible(driver,bookspagev2.bookDetailPageBookTitle, 10))
				     {	
						     lastupdatedtitle1=bookspagev2.bookDetailPageBookTitle.getAttribute("text").toString().trim();
						     lastupdatedtitle1=lastupdatedtitle1.replaceAll("[^a-zA-Z0-9]", "");
						     lastupdatedtitle1=lastupdatedtitle1.toLowerCase();
			        	     test.log(LogStatus.INFO, "sucessesfully retrieved book title from book detail page  after clicking on book in last read page   "+lastupdatedtitle1);
				     }
			         else
			         {
			        	      test.log(LogStatus.FAIL, "failed to retrieve book title from book detail page after clicking on book in last read page ");
			        	      basepagev2.takeScreenshot();				        	 
			         }
					 			   			            			        						
	   			       //verification last watched from last read page  updated in first position or not in Last-Read page  			         
	   			       if(lastupdatedtitle1.contains(booktitle1))
					   {
	   			    	      test.log(LogStatus.INFO, "VK_1906-------- Verify if the Ebook cards are added to the 1st position Last viewed tray if user plays the same content again from LV"); 
	   			    	      if(!Utilities.setResultsKids("VK_1906", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					   }
	   			       else
	   			       {
	   			    	        test.log(LogStatus.FAIL, "last watched from last read page failed to update in first position  in Last-Read page after givining 5 minutes wait also ");
	   			    	       test.log(LogStatus.FAIL, "VK_1906-------- Verify if the Ebook cards are added to the 1st position Last viewed tray if user plays the same content again from LV");  
	   			    	       if(!Utilities.setResultsKids("VK_1906", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
	   			               basepagev2.takeScreenshot();
	   			      }
   			        								         
		}
	
	   @DataProvider
	   public Object[][] getData()
	   {
	   return DataUtil.getData(testName,xls);											          					
       }  		
}
