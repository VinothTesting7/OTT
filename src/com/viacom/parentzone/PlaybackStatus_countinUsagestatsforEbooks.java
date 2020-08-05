package com.viacom.parentzone;

import java.time.Duration;
import java.util.ArrayList;
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

//Author= Bhaskar
public class PlaybackStatus_countinUsagestatsforEbooks extends BaseTestV2
{	  
     
	   //VK_1671-----Verify the playback status for partially watched Ebook content in Last viewed tray	
	  //VK_1672 ------Verify the playback status for Completely watched Ebook content in Last viewed tray		
	  // VK_1613	----Verify if E book pages data gets updated in usage stats if user reads all pages in an eBook completely		
	  // VK_1614---Verify if E book pages data gets updated in usage stats if user reads E book partially
	
	String testName = "PlaybackStatus_countinUsagestatsforEbooks";	
	@Test(dataProvider = "getData")
	public void PlaybackStatus_countinUsagestatsforEbooks(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("PlaybackStatus_countinUsagestatsforEbooks");
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
					
					  
				 
					 int c=0 , c1=0, count=0 , updatedcount1=0 ,updatedcount3=0 ,updatedcount2=0 ,readpagesofebook=0, partiallyreadpages=0,totalpagesreadofebook=0;
					 
					 int	 readpagesofebookinlastviewdtray=0 , totalpagesofebook=0 , startingtime=0 ,readpagesofebookinlast2viewdtray=0;
					 
			         String lastread="" ,  lastviewed2="" ,lastviewed3="",  lastviewedfirsttitle1="" ,audiocard="" ,audiocard1="" ,lastread2="", audiotitleindetailpage="";
			        
				     String audiocard2="",  audiocard3="" , storiescount="" , storiescount1="", storiescount2="", storiescount3="" ,bookname="" ,bookname1="";
			       
				     String bookname2="" , bookname3="" ,numberofpagesread="" ,pages="" , audioname="" ,audioname1="" ,audioname2="",audioname3="" ,lastplayed="";
			        
				     String booktitle1="" , booktitle2="", booktitle3="", booktitle4="", booktitle5="",pagescount="";
				    				    
				     boolean readerDisplayedPreview=false;  
				    
				      //login with Valid credentials
					  homepagev2.login(data.get("Email"),data.get("Password"));
					
					   //delete All Downloads					
					   downloadsv2.deleteAllDownloads();
					 
//VK_1671-----Verify the PlayBack status for partially watched Ebook content in Last viewed
						
							            
						homepagev2.tabClick("Read");
						Thread.sleep(4000);
												
						//scroll to first  reads tray and click on first reads tray			 		
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
			 			 
						 //click on card 1 first tray reads page 
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
			                     					                          
	                                //preview e-book pages partially			                     
						             for(int i=1;i<=2;i++)				    		
					    	         {										    		
							    			 Thread.sleep(3000);
								        	 Utilities.horizontalSwipe(driver);	
								        	 c=c+1;										    		 
					                 }										            
					              	System.out.println(c);
					     	        test.log(LogStatus.INFO, "partially read pages are"+c);
							     	
		     	                      		        			    	                  	
			    	                 //navigate back to homePage from book Reader page			   
			    				      driver.navigate().back();	
			    	                  Thread.sleep(2000);
			    	                  driver.navigate().back();
			    	                  Thread.sleep(2000);
			    	                  driver.navigate().back();
			    	                  Thread.sleep(2000);
		          }
				  else
				  {
						   test.log(LogStatus.FAIL, "preview button is not available in book detail page so unable to conrinue");   
						   basepagev2.takeScreenshot();
				  }
			   
			                
				//searching for my stuff tab and click on My stuff tab			 
				Utilities.verticalSwipeReverse(driver);	
				Utilities.verticalSwipeReverse(driver);	
				
				homepagev2.tabClick("Watch");
				Thread.sleep(120000);
											
				homepagev2.tabClick("My Stuff");			
				Thread.sleep(60000);
				
				//scroll to  lastViewed  tray	
				
				for(int scroll=0;scroll<=7;scroll++)
				{
							if(Utilities.explicitWaitClickable(driver, listenpageV2.lastviewedtrayb, 1))
							{							 															  
								      test.log(LogStatus.INFO, "navigated to  lastviewed tray");
								     try{
								    	   listenpageV2.lastviewedtrayb.click();
										   test.log(LogStatus.INFO, "clicked on  lastviewed tray");
									     }catch(Exception e)
								         {
									    	 test.log(LogStatus.FAIL, "  lastviewed tray is not updated after giving 3 minutes wait also");	
									    	 test.log(LogStatus.FAIL, " failed to click on  lastviewed tray");	
									    	 basepagev2.takeScreenshot();
								         }	
								     
								      break;						 
							}	  
							else
							{ 
								 Utilities.verticalSwipe(driver); 							 
							} 
				}
				 							
	            //retrieve book title from last read page			
		        if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))
		        {		 	 
						     audiocard=listenpageV2.lastviewedintrayb.getAttribute("text").toString().trim();
						     lastread= audiocard.replaceAll("[^a-zA-Z0-9]","");
						     lastread=lastread.toLowerCase();
						     System.out.println(lastread);
						     test.log(LogStatus.INFO, "sucessesfully retrieved book title                  "+lastread);								     								     
		        }
			    else
			    {
			    	       test.log(LogStatus.FAIL,"failed to retrieve book title from last read page");	
			    	       basepagev2.takeScreenshot();
			    }	
		        
               //verifying partially previewed e-book updated or not		        
	           if(lastread.equalsIgnoreCase(booktitle1))
			   {		  	 			 
					         test.log(LogStatus.INFO, "partially  previewed ebook  is  availble in lastviewedtray");
				          try {						  				
					            listenpageV2.lastviewedintrayb.click();
					            test.log(LogStatus.INFO, "clicked on partially last previewed ebook");					  										  				
					
			                  }catch(Exception e)
				              {
			                	  test.log(LogStatus.FAIL, " failed to click on partially last previewed ebook");						       	
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
								      
											       
											       //get reader controls													    															
													Utilities.tap(driver);
													Utilities.tap(driver);
													test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");
													      													      													      
											        /* //refreshApp											         
											         driver.runAppInBackground(Duration.ofSeconds(3));
											         test.log(LogStatus.INFO, "Put app to background for 3 seconds");
											         driver.currentActivity();*/
											         													      													      													      
													if(Utilities.explicitWaitVisible(driver, ebookspagev2.readerCurrentChapter, 10))
													{
												                   test.log(LogStatus.INFO, " read pages count is  available");
															try {																								 																																		
																    pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
																    String  arr[]=pages.split(" ");
														            numberofpagesread=arr[1].toString().trim();
														            readpagesofebookinlastviewdtray=Integer.parseInt(numberofpagesread);
														            System.out.println( readpagesofebookinlastviewdtray);											 
														            test.log(LogStatus.INFO, "sucessesfully retrieved read ebook pages from last viewed tray"+ readpagesofebookinlastviewdtray);
																
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
													
													
													if(readpagesofebookinlastviewdtray==(c+1))
													{					  
															    test.log(LogStatus.PASS, "VK_1671--Verify the playback status for partially watched Ebook content in Last viewed tray ");
															    if(!Utilities.setResultsKids("VK_1671", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");																       					 
													}
													else 
													{					 							
															   test.log(LogStatus.FAIL, "VK_1671--Verify the playback status for partially watched Ebook content in Last viewed tray ");																  
															   if(!Utilities.setResultsKids("VK_1671", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");
															   BasePageV2.takeScreenshot();     
													}
													 																							
													//navigate back to last viewed page from e-book read controls  page												 
													driver.navigate().back();
													Thread.sleep(2000);
													driver.navigate().back();
													Thread.sleep(2000);
													driver.navigate().back();
													Thread.sleep(2000);																	
																								    												    	
										}
									    else
									    {
											    	test.log(LogStatus.FAIL, " preview button  is not availble");	
											    	basepagev2.takeScreenshot();											    	
									    }
									   																			
								}
								else 
								{	
									   test.log(LogStatus.FAIL, "partially previewed e-book  failed to update in lastviewed tray after giving 3 minutes wait also");						
									   test.log(LogStatus.FAIL, "VK_1671--Verify the playback status for partially watched Ebook content in Last viewed tray ");						 
									   if(!Utilities.setResultsKids("VK_1671", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									   BasePageV2.takeScreenshot(); 									   
									   
								}	
	           
			          //back to home Page from last read page
					   driver.navigate().back();
					   Thread.sleep(2000);		
						
//VK_1672 ------Verify the playback status for Completely watched Ebook content in Last viewed tray				
						
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
					            	     test.log(LogStatus.FAIL, "failed to click on card2 under first tray page ");
					            	     test.log(LogStatus.FAIL, "failed to retrieve   Booktitle               "+booktitle2);
					            	     basepagev2.takeScreenshot();
					               }
				      }else
				      {
				    	     test.log(LogStatus.FAIL, " card 2  is not Available in under first tray page ");
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
							    				    				    						    		
					    		if(Utilities.explicitWaitVisible(driver, ebookspagev2.downloadbuttonb, 5))
					    		{
					    			  test.log(LogStatus.INFO, "down load button is available  after previewing E-book  pages completely ");
					    		     								    		
					    		}
					    		
						 						    									    
	     	                   //navigate back to home page	  from down load page       
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
					test.log(LogStatus.FAIL, "preview button is not available in book detail page so unable to conrinue");   
					basepagev2.takeScreenshot();
			  }
		   
	   	         		   
	   //start verifying completely previewed e-book play back status
	   
			//searching for my stuff tab and click on My stuff tab
		 
			Utilities.verticalSwipeReverse(driver);	
			Utilities.verticalSwipeReverse(driver);	
			
			homepagev2.tabClick("Watch");
			Thread.sleep(120000);										
			homepagev2.tabClick("My Stuff");			
			Thread.sleep(60000);
			
						//scroll to  lastViewed  tray						
						for(int scroll=0;scroll<=7;scroll++)
						{
									if(Utilities.explicitWaitClickable(driver, listenpageV2.lastviewedtrayb, 1))
									{							 															  
										      test.log(LogStatus.INFO, "navigated to  lastviewed tray");
										     try{
										    	   listenpageV2.lastviewedtrayb.click();
												   test.log(LogStatus.INFO, "clicked on  lastviewed tray");
											     }catch(Exception e)
										         {
											    	 test.log(LogStatus.FAIL, " last viesed tray is not available after giving 3 minutes wait also ");
											    	 test.log(LogStatus.FAIL, " failed to click on  lastviewed tray");	
											    	 basepagev2.takeScreenshot();
										         }								   
										      break;						 
									}	  
									else
									{ 
										 Utilities.verticalSwipe(driver); 							 
									} 
						}
			
													
						//retrieve book title from last viewed page in my stuff-tab								
						if(Utilities.explicitWaitVisible(driver,listenpageV2.lastupdatedintrayb, 10))
                        {		 	 
						     audiocard=listenpageV2.lastviewedintrayb.getAttribute("text").toString().trim();
						     lastread= audiocard.replaceAll("[^a-zA-Z0-9]","");
						     lastread=lastread.toLowerCase();
						     System.out.println(lastread);
						     test.log(LogStatus.INFO, "sucessesfully retrieved book title from last viewed my stuff                             "+lastread);

						}
						else 
						{	
							    test.log(LogStatus.FAIL, "sucessesfully retrieved book title from last viewed my stuff ");
							    BasePageV2.takeScreenshot();  							   							 
						}		
						
						//verifying completely previewed e-book updated or not in last viewed page	in my stuff tab			        
						if(lastread.equalsIgnoreCase(booktitle2))
					    {		  	 			 
					               test.log(LogStatus.INFO, "completely  previewed e-book  updated in lastviewedtray");
						         try {						  				
							            listenpageV2.lastviewedintrayb.click();
							            test.log(LogStatus.INFO, "clicked on completely last previewed ebook");					  										  														
					                  }catch(Exception e)
						              {
					                	test.log(LogStatus.FAIL, " failed to click on completely last previewed ebook");						       	
					                	 BasePageV2.takeScreenshot(); 
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
									 				 
											  
												
										         try 
											    	{										    	   					    							    												    						    		
														Thread.sleep(2000);
														Utilities.tap(driver);
														Utilities.tap(driver);								
														test.log(LogStatus.INFO, "Double tapped screen to launch reader controls");	
														
											    	}catch(Exception e)
									        	    {
											    		test.log(LogStatus.FAIL, "failed to do Double tap on  screen to launch reader controls");	
											    		 basepagev2.takeScreenshot();
											    	}	
										         
											        /* //refreshApp													         
											         driver.runAppInBackground(Duration.ofSeconds(3));
											         test.log(LogStatus.INFO, "Put app to background for 3 seconds");
											         driver.currentActivity();*/
										         													      													      													      
													if(Utilities.explicitWaitVisible(driver,ebookspagev2.readerCurrentChapter, 10))
													{
														test.log(LogStatus.INFO, " total previewed pages count are  available");	  
													    
															try{																								 																																		
																	pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
																	String  arr[]=pages.split(" ");
														            numberofpagesread=arr[0].toString().trim();
														            readpagesofebookinlastviewdtray=Integer.parseInt(numberofpagesread);
														            System.out.println( readpagesofebookinlastviewdtray);											 
														            test.log(LogStatus.INFO, "sucessesfully retrieved read ebook pages from last viewed tray"+ readpagesofebookinlastviewdtray);
																
															    }catch(Exception e)
															    {
																    test.log(LogStatus.FAIL, "failed to  retrieve read ebook pages from last viewed tray");	  
																    basepagev2.takeScreenshot();
															    }												
												    }
													else
													{
														test.log(LogStatus.FAIL, " total read pages count  are not available");	  
													    basepagev2.takeScreenshot();         	
													}
																																											
													if(readpagesofebookinlastviewdtray==5)
													{					  
															    test.log(LogStatus.PASS, "VK_1672 ------Verify the playback status for Completely watched Ebook content in Last viewed tray  ");
															    if(!Utilities.setResultsKids("VK_1672", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
																       					 
													}
													else 
													{					 							
															   test.log(LogStatus.FAIL, "VK_1672 ------Verify the playback status for Completely watched Ebook content in Last viewed tray");																  
															   if(!Utilities.setResultsKids("VK_1672", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");
															   BasePageV2.takeScreenshot();     
													}
													 																										
													//navigate back to last viewed page from e-book read controls  page													
													driver.navigate().back();
													Thread.sleep(2000);
													driver.navigate().back();
													Thread.sleep(2000);
													driver.navigate().back();
													Thread.sleep(2000);																	
																									    												    	
								 }
							     else
							     {
									    	test.log(LogStatus.FAIL, " preview button  is not availble");	
									    	basepagev2.takeScreenshot();										    	
							     }													   											
					}
					else 
					{	
							   test.log(LogStatus.FAIL, "completely previewed  ebook  failed to update in lastviewed tray of my stufftab after gviving 3 minute wait also");						
							   test.log(LogStatus.FAIL, "VK_1672 ------Verify the playback status for Completely watched Ebook content in Last viewed tray");						 
							   if(!Utilities.setResultsKids("VK_1672", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							   BasePageV2.takeScreenshot();     														  
					}	
      			 
					    //back to homePage from last viewed page
					     driver.navigate().back();
					     Thread.sleep(2000);		
					   
						 
						 //do vertical swipe up to get profile icon		  
						 Utilities.verticalSwipeReverse(driver);
						 Utilities.verticalSwipeReverse(driver);		 
										             	                         
		
							
// VK_1614---Verify if E book pages data gets updated in usage stats if user reads E book partially
									 						 
						   //click on profile icon-1																 
							if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
							{
								homepagev2.profilepic.click();
								test.log(LogStatus.INFO, "Clicked on Profile pic icon");					
							}
							else 
							{
								test.log(LogStatus.FAIL, " failed to Click on Profile pic icon");
								basepagev2.takeScreenshot();
							}
							
							   //refresh app
							   driver.runAppInBackground(Duration.ofSeconds(3));
				               test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				                driver.currentActivity();
							
							//click on parent zone							
							if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10))
							{
									settingspagev2.parentZoneButton.click();
									test.log(LogStatus.INFO, "Tapped on Parent Zone button");
							}
							else
							{
								   test.log(LogStatus.FAIL, "failed to Tapped on Parent Zone button");
								   basepagev2.takeScreenshot();
							}
														
							// clearing pin container and Entering pin 						
							if (Utilities.explicitWaitVisible(driver, launchpagev2.pinContainer, 20)) 
							{
								
								     try{
									        launchpagev2.pinContainer.clear();
											launchpagev2.pinContainer.sendKeys("1111");
											test.log(LogStatus.INFO, "Entered PIN");	
										
								          }catch(Exception e)
								          {
								        	  test.log(LogStatus.FAIL, "failed to Enter PIN"); 
								        	  basepagev2.takeScreenshot();
								          }
								   	  
									       //refresh app
										   driver.runAppInBackground(Duration.ofSeconds(3));
							               test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							                driver.currentActivity();
							                
									    //click on activity tab
										if (Utilities.explicitWaitClickable(driver, settingspagev2.activityTab, 10))
										{
											settingspagev2.activityTab.click();
											test.log(LogStatus.INFO, "Activity tab is selected");
										}
										else
										{
											test.log(LogStatus.FAIL, "Activity tab is not selected");
											basepagev2.takeScreenshot();
										}
										
								
								           Utilities.verticalSwipe(driver);				     
								           Utilities.verticalSwipe(driver);
							   										   
						     	       //scroll to Horizontal tray 			     
										for(int scroll=0;scroll<=3;scroll++)
										 {
													 if(Utilities.explicitWaitVisible(driver, settingspagev2.horizantaltrayb, 1))
													 {							 															  
														   test.log(LogStatus.INFO, "horizantal tray is available");
														    break;						 
													 }
													 else
													  {
														 Utilities.verticalSwipe(driver);	
													  }						  							 						
									    }				
														
								   try{
							              Thread.sleep(60000);
						                  driver.runAppInBackground(Duration.ofSeconds(3));
							              test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							              driver.currentActivity();
								          settingspagev2.monthbuttonb.click();
								          test.log(LogStatus.INFO, "clicked on month button");								
										   
									    }catch(Exception e)
								        {
									    	test.log(LogStatus.FAIL, " failed to clicked on month button");
									    	 basepagev2.takeScreenshot();
									    }
							     
								 try{
									    Thread.sleep(120000);
								        driver.runAppInBackground(Duration.ofSeconds(3));
									    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									    driver.currentActivity();
									    settingspagev2.weekbuttonb.click();
										test.log(LogStatus.INFO, "clicked on week button");							
										  
									    }catch(Exception e)
								        {
									    	test.log(LogStatus.FAIL, " failed to clicked on week button");
									    	 basepagev2.takeScreenshot();
									    }							
							
								    try{
									    Thread.sleep(120000);
								        driver.runAppInBackground(Duration.ofSeconds(3));
									    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									    driver.currentActivity();
									    settingspagev2.daybuttonb.click();
									    test.log(LogStatus.INFO, "clicked on day button");							
									  
								       }catch(Exception e) 
								       {
								    	   test.log(LogStatus.FAIL, " failed to clicked on day button");
								    	   basepagev2.takeScreenshot();
								       }
										
										
									    //Scroll to read card						
										 for(int scroll=0;scroll<=4;scroll++)
										 {
												 if(Utilities.explicitWaitVisible(driver,  settingspagev2.usagePagesCount, 1))
												 {
													   test.log(LogStatus.INFO, " Read text is displayed");
													   break;
												 }
												 else
												 { 
													 Utilities.verticalSwipe(driver); 							 
												 } 
										 }
									 
										   //refresh app
										    driver.runAppInBackground(Duration.ofSeconds(3));
										    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
										    driver.currentActivity();										    
										    Thread.sleep(2000);										    
										   //retrieving the count 
										 if(Utilities.explicitWaitVisible(driver, settingspagev2.usagePagesCount, 10))
										 {
											  pagescount=settingspagev2.usagePagesCount.getAttribute("text");							  
											  System.out.println( pagescount);
											  test.log(LogStatus.INFO, "fetching pages count is              "+ pagescount);							  
										 }
										 else
										 {
										        test.log(LogStatus.FAIL, " failed to fetching  epagescount ");  
										        basepagev2.takeScreenshot();
										 }
					       
										 try {
										     count=Integer.parseInt( pagescount); 
										 }catch(Exception e) {}
										 
									    //navigate to HomePage  from usage stats page					 
										 driver.navigate().back();
										 Thread.sleep(2000);
										 driver.navigate().back();
										 Thread.sleep(2000);
																			
							}						
							else
							{ 
								 test.log(LogStatus.FAIL,"Pin Container not displayed");
								 basepagev2.takeScreenshot();
								//navigate to HomePage  from parent zone page					 
								 driver.navigate().back();
								 Thread.sleep(2000);
								 driver.navigate().back();
								 Thread.sleep(2000);
								
							}	
							
							
							//do vertical swipe	till to get READ TAB				
							Utilities.verticalSwipeReverse(driver);					
							Utilities.verticalSwipeReverse(driver);
														            
							homepagev2.tabClick("Read");
							Thread.sleep(4000);				 
					      		    
						 			 
					     // scroll to first Reads tray and click on first reads tray		 		
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
																			      
											if(Utilities.explicitWaitVisible(driver,ebookspagev2.readerCurrentChapter, 10))
											{
											           test.log(LogStatus.INFO, "partialy  read pages count is  available");
														try 
														{																								 																																		
															    pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
															    String  arr[]=pages.split(" ");
																numberofpagesread=arr[1].toString().trim();
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
					 						 
						//verify partially read e-book pages updated or not					 
						 Utilities.verticalSwipeReverse(driver);
						 Utilities.verticalSwipeReverse(driver);
					 
					   //click on profile icon-2																		 
						if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
						{
							homepagev2.profilepic.click();
							test.log(LogStatus.INFO, "Clicked on Profile pic icon");					
						}
						else 
						{
							test.log(LogStatus.FAIL, " failed to Click on Profile pic icon");
							basepagev2.takeScreenshot();
						}
						
						 //refresh app
					    driver.runAppInBackground(Duration.ofSeconds(3));
					    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
					    driver.currentActivity();
					    
						//click on parent zone								
						if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10))
						{
								settingspagev2.parentZoneButton.click();
								test.log(LogStatus.INFO, "Tapped on Parent Zone button");
						}
						else
						{
							test.log(LogStatus.FAIL, "failed to Tapped on Parent Zone button");
							basepagev2.takeScreenshot();
						}
						
						//clearing pin container and Entering pin 							
						if (Utilities.explicitWaitVisible(driver, launchpagev2.pinContainer, 20)) 
						{
							        try{ 
							             launchpagev2.pinContainer.clear();
									     launchpagev2.pinContainer.sendKeys("1111");
									      test.log(LogStatus.INFO, "Entered PIN");	
							           }catch(Exception e)
							           {
							        	  	  test.log(LogStatus.FAIL, "failed to Entere PIN");	
							        	  	  basepagev2.takeScreenshot();
							            }
							        							    										 
								    //refreshing the app
								    driver.runAppInBackground(Duration.ofSeconds(3));
								    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
								    driver.currentActivity();
								    
								    //click on activity tab
									if (Utilities.explicitWaitClickable(driver, settingspagev2.activityTab, 10))
									{
										settingspagev2.activityTab.click();
										test.log(LogStatus.INFO, "Activity tab is selected");
									}
									else
									{
										test.log(LogStatus.FAIL, "Activity tab is not selected");
										basepagev2.takeScreenshot();
									}
									
									
									   Utilities.verticalSwipe(driver);				     
									   Utilities.verticalSwipe(driver);
									   										   
								     	//scroll to Horizontal tray 						     
										for(int scroll=0;scroll<=3;scroll++)
										 {
													 if(Utilities.explicitWaitVisible(driver, settingspagev2.horizantaltrayb, 1))
													 {							 															  
														   test.log(LogStatus.INFO, "horizantal tray is available");
														    break;						 
													 }
													 else
													  {
														 Utilities.verticalSwipe(driver);	
													  }						  							 						
									    }				
							
											
									   try{
										       Thread.sleep(60000);
									            driver.runAppInBackground(Duration.ofSeconds(3));
										        test.log(LogStatus.INFO, "Put app to background for 3 seconds");
										        driver.currentActivity();
											    settingspagev2.monthbuttonb.click();
											    test.log(LogStatus.INFO, "clicked on month button");								
											   
										    }catch(Exception e)
									        {
										    	 test.log(LogStatus.FAIL, " failed to clicked on month button");
										    	 basepagev2.takeScreenshot();
										    }
								     
									 try{
										     Thread.sleep(120000);
									        driver.runAppInBackground(Duration.ofSeconds(3));
										    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
										    driver.currentActivity();
										    settingspagev2.weekbuttonb.click();
											test.log(LogStatus.INFO, "clicked on week button");							
											  
										    }catch(Exception e)
									        {
										    	test.log(LogStatus.FAIL, " failed to clicked on week button");
										    	 basepagev2.takeScreenshot();
										   }							
								
									    try{
										      Thread.sleep(120000);
									        driver.runAppInBackground(Duration.ofSeconds(3));
										    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
										    driver.currentActivity();
										    settingspagev2.daybuttonb.click();
										    test.log(LogStatus.INFO, "clicked on day button");							
										  
									       }catch(Exception e) 
									       {
									    	   test.log(LogStatus.FAIL, " failed to clicked on day button");
									    	   basepagev2.takeScreenshot();
									       }
							
								
									    //Scroll to read card							
										 for(int scroll=0;scroll<=4;scroll++)
										 {
												 if(Utilities.explicitWaitVisible(driver,  settingspagev2.usagePagesCount, 1))
												 {
													   test.log(LogStatus.INFO, " Read text is displayed");
													   break;
												 }
												 else
												 { 
													 Utilities.verticalSwipe(driver); 							 
												 } 
										 }
								 
									    //refreshing the app
									    driver.runAppInBackground(Duration.ofSeconds(3));
									    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									    driver.currentActivity();		
									    
									    Thread.sleep(2000);									    
									 //retrieving count
									 if(Utilities.explicitWaitVisible(driver, settingspagev2.usagePagesCount, 10))
									 {
										  pagescount=settingspagev2.usagePagesCount.getAttribute("text");								  
										  System.out.println( pagescount);
										  test.log(LogStatus.INFO, "fetching pages count is          "+ pagescount);								  
									 }
									 else
									 {
									            test.log(LogStatus.FAIL, " failed to fetching  epagescount ");  
										        basepagev2.takeScreenshot();
									 }
						   
									 try {
									      updatedcount1=Integer.parseInt( pagescount); 
									     }catch(Exception e) {}
										 
								    if(updatedcount1>=(count+partiallyreadpages))
								    {							    						    	
										    test.log(LogStatus.PASS, " Testcase: VK_1614---Verify if E book pages data gets updated in usage stats if user reads Ebook partially "+updatedcount1);								    	
								    	    if(!Utilities.setResultsKids("VK_1614", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							        }	
						    	    else
							        {
						    	    	    test.log(LogStatus.FAIL, " Testcase: VK_1614---Verify if E book pages data gets updated in usage stats if user reads Ebook partially");					    	
								    	    if(!Utilities.setResultsKids("VK_1614", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
								    	    basepagev2.takeScreenshot();
							        } 

						            //navigate back to home page form usage stats page							    
								    driver.navigate().back();
								    Thread.sleep(2000);
								    driver.navigate().back();
								    Thread.sleep(2000);
							        						        						        
						}							
						else
						{ 
							 test.log(LogStatus.FAIL,"Pin Container not displayed");
							 basepagev2.takeScreenshot();
							 
							   //navigate back to home page form parent zone page							    
							    driver.navigate().back();
							    Thread.sleep(2000);
							    driver.navigate().back();
							    Thread.sleep(2000);
							        
						}
												
						 //do vertical swipe up till to get read tab			 
						 Utilities.verticalSwipeReverse(driver);
					 Utilities.verticalSwipeReverse(driver);		 
						 
// VK_1613	----Verify if E book pages data gets updated in usage stats if user reads all pages in an eBook completely
						
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
			 	     
						 //click on card 5 under best reads page 
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
								      							      
								           /* // refreshApp													         
								            driver.runAppInBackground(Duration.ofSeconds(3));
								            test.log(LogStatus.INFO, "Put app to background for 3 seconds");
								            driver.currentActivity();*/
								            
								            //retrieving total pages of e-book  
								            if(Utilities.explicitWaitVisible(driver, ebookspagev2.readerCurrentChapter, 10))
											{
								            									          								            	
											           test.log(LogStatus.INFO, "total pages count is  available");
														try 
														{																								 																																		
															    pages=ebookspagev2.readerCurrentChapter.getAttribute("text").toString().trim();
															     String  arr[]=pages.split(" ");
																numberofpagesread=arr[1].toString().trim();
																totalpagesreadofebook=Integer.parseInt(numberofpagesread);
																System.out.println(totalpagesreadofebook);											 
																test.log(LogStatus.INFO, "sucessesfully retrieved completely  read ebook pages           "+ totalpagesreadofebook);
																
														}catch(Exception e)
														{
																test.log(LogStatus.FAIL, "failed to  retrieve completely  read ebook pages       ");	  
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
						 		
						 
						  //verify completely read e-book pages updated or not		
						  
					      //search and click on profile -icon						  
					       Utilities.verticalSwipeReverse(driver);
					       Utilities.verticalSwipeReverse(driver);
					       
						    //click on profile icon-2																				 
							if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
							{
								homepagev2.profilepic.click();
								test.log(LogStatus.INFO, "Clicked on Profile pic icon");					
							}
							else 
							{
								test.log(LogStatus.FAIL, " failed to Click on Profile pic icon");
								basepagev2.takeScreenshot();
							}
							
							 //refresh app
						    driver.runAppInBackground(Duration.ofSeconds(3));
						    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
						    driver.currentActivity();
														
							//click on parent zone									
							if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10))
							{
									settingspagev2.parentZoneButton.click();
									test.log(LogStatus.INFO, "Tapped on Parent Zone button");
							}
							else
							{
								test.log(LogStatus.FAIL, "failed to Tapped on Parent Zone button");
								basepagev2.takeScreenshot();
							}
							
							// clearing pin container and Entering pin 								
							if (Utilities.explicitWaitVisible(driver, launchpagev2.pinContainer, 20)) 
							{
								      
								     try {																	
								           launchpagev2.pinContainer.clear();
										   launchpagev2.pinContainer.sendKeys("1111");
										   test.log(LogStatus.INFO, "Entered PIN");											
								          }catch(Exception e) 
								          {
								        	  test.log(LogStatus.FAIL, "failed to Entere PIN");	
								        	  basepagev2.takeScreenshot();
								         }
										    
										      //click on activity tab
											if (Utilities.explicitWaitClickable(driver, settingspagev2.activityTab, 10))
											{
												settingspagev2.activityTab.click();
												test.log(LogStatus.INFO, "Activity tab is selected");
											}
											else
											{
												test.log(LogStatus.FAIL, "Activity tab is not selected");
												basepagev2.takeScreenshot();
											}
																			
											   Utilities.verticalSwipe(driver);				     
											   Utilities.verticalSwipe(driver);
											   										   
										     	//scroll to Horizontal tray 							     
												for(int scroll=0;scroll<=3;scroll++)
												 {
															 if(Utilities.explicitWaitVisible(driver, settingspagev2.horizantaltrayb, 1))
															 {							 															  
																   test.log(LogStatus.INFO, "horizantal tray is available");
																    break;						 
															 }
															 else
															  {
																 Utilities.verticalSwipe(driver);	
															  }						  							 						
											    }				
									
													
											   try{
												       Thread.sleep(60000);
											            driver.runAppInBackground(Duration.ofSeconds(3));
												        test.log(LogStatus.INFO, "Put app to background for 3 seconds");
												        driver.currentActivity();
													    settingspagev2.monthbuttonb.click();
													    test.log(LogStatus.INFO, "clicked on month button");								
													   
												    }catch(Exception e)
											        {
												    	 test.log(LogStatus.FAIL, " failed to clicked on month button");
												    	 basepagev2.takeScreenshot();
												    }
										     
											 try{
												    Thread.sleep(120000);
											        driver.runAppInBackground(Duration.ofSeconds(3));
												    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
												    driver.currentActivity();
												    settingspagev2.weekbuttonb.click();
													test.log(LogStatus.INFO, "clicked on week button");							
													  
												    }catch(Exception e)
											        {
												    	test.log(LogStatus.FAIL, " failed to clicked on week button");
												    	 basepagev2.takeScreenshot();
												   }							
										
											    try{
												    Thread.sleep(120000);
											        driver.runAppInBackground(Duration.ofSeconds(3));
												    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
												    driver.currentActivity();
												    settingspagev2.daybuttonb.click();
												    test.log(LogStatus.INFO, "clicked on day button");							
												  
											       }catch(Exception e) 
											       {
											    	   test.log(LogStatus.FAIL, " failed to clicked on day button");
											    	   basepagev2.takeScreenshot();
											       }
									
										
											    //Scroll to read card								
												 for(int scroll=0;scroll<=4;scroll++)
												 {
														 if(Utilities.explicitWaitVisible(driver,  settingspagev2.usagePagesCount, 1))
														 {
															   test.log(LogStatus.INFO, " Read text is displayed");
															   break;
														 }
														 else
														 { 
															 Utilities.verticalSwipe(driver); 							 
														 } 
												 }
										 
											   //refreshing								 
											    driver.runAppInBackground(Duration.ofSeconds(3));
											    test.log(LogStatus.INFO, "Put app to background for 3 seconds");
											    driver.currentActivity();
											    								    
											  Thread.sleep(2000);
											 //retrieving  count
											 if(Utilities.explicitWaitVisible(driver, settingspagev2.usagePagesCount, 10))
											 {
												  pagescount=settingspagev2.usagePagesCount.getAttribute("text");								  
												  System.out.println( pagescount);
												  test.log(LogStatus.INFO, "fetching pages count is          "+ pagescount);								  
											 }
											 else
											 {
											        test.log(LogStatus.FAIL, " failed to fetching  epagescount ");  
											        basepagev2.takeScreenshot();
											 }
						       
											 try {
										          updatedcount2=Integer.parseInt( pagescount); 
											     }catch(Exception e) {}
											 							 
										    if(updatedcount2>=(updatedcount1+ totalpagesreadofebook))
									        {							    						    	
											        test.log(LogStatus.PASS, " Testcase: VK_1613	----Verify if E book pages data gets updated in usage stats if user reads all pages in an eBook completely "+updatedcount2);								    	
										    	    if(!Utilities.setResultsKids("VK_1613", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
									        }	
								    	    else
									        {
								    	    	    test.log(LogStatus.FAIL, " Testcase: VK_1613	----Verify if E book pages data gets updated in usage stats if user reads all pages in an eBook completely");					    	
										    	    if(!Utilities.setResultsKids("VK_1613", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
										    	    basepagev2.takeScreenshot();
									        } 
							}								
							else
							{ 
								 test.log(LogStatus.FAIL,"Pin Container not displayed");
								 basepagev2.takeScreenshot();
							}						 
		
							
    }
		
		@DataProvider
		public Object[][] getData()
		{
		return DataUtil.getData(testName,xls);											          					
	    }     					 
					 
}
