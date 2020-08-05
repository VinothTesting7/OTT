package com.viacom.downloads;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class VerifyDownloadofPostPreviewingpages extends BaseTestV2
{
	//VK_1827-----Verify the Behaviour post preview when download is in progress during preview pages	
	//VK_1688-----Verify if Ebook continues to play post preview when download is completed during preview pages	
	//VK_1689-----Verify the behaviour post preview when download is in progress during preview pages	
	//VK_1690----- VK_430---Verify the behaviour tapping on 'Cancel' button in downloading overlay
	
	 String testName = "VerifyDownloadofPostPreviewingpages";		
	 @Test(dataProvider = "getData")
	public void VerifyDownloadofPostPreviewingpages(Hashtable<String, String> data) throws Exception 
    {				
		 
		test = rep.startTest("VerifyDownloadofPostPreviewingpages");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		       if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
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
				
				 
				 
				 int c=0 ,count=0  ,updatedcount1=0;   String  lastread=""  ,lastviewed2="" ,lastviewed3="", lastviewedfirsttitle1="";
                
				 String audiocard="", audiocard1="", audiocard2="" , audiocard3="" ,storiescount3="",bookname3="",bookname=""; 
               
				 int updatedcount2=0 , updatedcount3=0,totalpagesreadofebook=0;  String storiescount="",  storiescount1="",  storiescount2="",	bookname1="";
                
				  boolean readerDisplayedPreview=false;   String bookname2="", totalpagesread="", pages="";    WebElement booktitle=null;  
				 
				  String booktitle1="",booktitle2="",booktitle3="",booktitle4="";
				  
				  Actions a=new Actions(driver);
				  
				  
				     //login with valid credentials
					 homepagev2.login(data.get("Email"),data.get("Password"));
					
					 //delete All Downloads
					  downloadsv2.deleteAllDownloads();
					 
			          //Set device download quality to low				  
				     downloadsv2.setDeviceDownloadQuality("low");
		  
				
				  
//VK_1827----Verify the Behaviour post preview when download is in progress during preview pages
						
				     test.log(LogStatus.INFO, "TestCase:VK_1827----Verify the Behaviour post preview when download is in progress during preview pages");							  		
				      
				       Utilities.verticalSwipeReverse(driver);					
						Utilities.verticalSwipeReverse(driver);
				       //click on Read tab									            
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
					 				 	
				   }				
				  else
				   {
					    test.log(LogStatus.FAIL, "unable to verify because preview button is not available");
		   	            test.log(LogStatus.FAIL, "TestCase:VK_1827----Verify the Behaviour post preview when download is in progress during preview pages");
					    if(!Utilities.setResultsKids("VK_1827", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");    
					    BasePageV2.takeScreenshot();	
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
			    					//navigate back to reader page from down load page
			    				    driver.navigate().back();
						    		Thread.sleep(2000);
			    				  
			    				  
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
					     
			    		//navigate back to book detail page from  Reader page						    		
			    		 driver.navigate().back();
			    		 Thread.sleep(2000);
			    			    	  	 
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
								 				 	
								    //preview e-book pages completely		                     
				                    for(int i=1;i<=30;i++)				    		
							    	{	
								    	  Thread.sleep(3000);
					                      Utilities.horizontalSwipe(driver);
							    	}
			    		
							        //verify the availability of cancelButton,downloading text and progressbar   				       					       
							        if(Utilities.explicitWaitVisible(driver, ebookspagev2.cancelbuttonb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingprogressbarb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingtextb, 10) ) 											   
								    {			   
								    	    test.log(LogStatus.PASS, "TestCase:VK_1827----Verify the Behaviour post preview when download is in progress during preview pages");
										    if(!Utilities.setResultsKids("VK_1827", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 	
										    
										    //navigate back to reader page from download page							    
										     driver.navigate().back();
										     Thread.sleep(3000);								    								    
								    }
							        else
							        {							        	
							        	    test.log(LogStatus.WARNING, "unable to verify because downlaoding gets completed while swiping pages");
							        	    test.log(LogStatus.WARNING, "TestCase:VK_1827----Verify the Behaviour post preview when download is in progress during preview pages");
										    if(!Utilities.setResultsKids("VK_1827", "WARNING")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
										    basepagev2.takeScreenshot();	
							        }
							        
							        //navigate back to book detail page	 from Reader page       
			    	                driver.navigate().back();
			    	                Thread.sleep(2000);
			                     				                  				                   							          					           		  
					 }				
					 else
					 {
						    test.log(LogStatus.WARNING, "unable to verify because Down load gets completed so preview button is not available in book detail page");
			   	            test.log(LogStatus.WARNING, "TestCase:VK_1827----Verify the Behaviour post preview when download is in progress during preview pages");
						    if(!Utilities.setResultsKids("VK_1827", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");    
						    BasePageV2.takeScreenshot();	
					 } 
									   
		                //navigate back to first tray page from book detail page
		                 driver.navigate().back();
				         Thread.sleep(3000);
				        
	
			   		            
//VK_1689-----Verify the behaviour post preview when download is in progress during preview pages	
//VK_1688-----Verify if Ebook continues to play post preview when download is completed during preview pages			      					 
					   
				         test.log(LogStatus.INFO, "VK_1689-----Verify the behaviour post preview when download is in progress during preview pages");
				         test.log(LogStatus.INFO, "VK_1688-----Verify if Ebook continues to play post preview when download is completed during preview pages");
				         
				         
				         //click on card2 in best reads page					   
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
						 
						    //verify preview button in book detail page
						    if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
						    {
								 
							       //scroll to bookDownloadLink	and click on Download book link		     
									for(int scroll=0;scroll<=4;scroll++)
								    {												
										      if(Utilities.explicitWaitClickable(driver, ebookspagev2.downloadbooklinkb, 1))
										      {	
										             test.log(LogStatus.INFO, "downloadbook link is available");							          
										            try{							        	  
										        	      ebookspagev2.downloadbooklinkb.click();							        	  
										        	      test.log(LogStatus.INFO, "clicked on downloadbook link ");
										        	     
										               }catch(Exception e)
										               {
										            	   test.log(LogStatus.FAIL, "failed to click on downloadbook link ");
										            	   basepagev2.takeScreenshot();
										               }
										            
											          break; 
											      
									          }else   Utilities.verticalSwipe(driver);
							       } 
						    }else
						    {
						    	  test.log(LogStatus.FAIL, "preview button is not Available in book detail page");
				            	   basepagev2.takeScreenshot();
						    }
						    
						  //scroll up to  Preview button
						   for(int scroll=0;scroll<=3;scroll++)
					       {												
							      if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 1) || Utilities.explicitWaitVisible(driver, readpagev2.readButton, 1) )
							      {	
							              test.log(LogStatus.INFO, "preview button or read button  is available");							          							         
							              break; 
								      
						          }else   Utilities.verticalSwipeReverse(driver);
				           } 
				    			
						   if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
						    {
								 try{
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
												
														test.log(LogStatus.PASS, "Verify the behavior when tapping on 'Preview' button  ");
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
				    		
								            //verify the availability of cancelButton,downloading text and progressbar   				       					       
								            if(Utilities.explicitWaitVisible(driver, ebookspagev2.cancelbuttonb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingprogressbarb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingtextb, 10) ) 											   
									        {  
										           test.log(LogStatus.PASS, "VK_1689-----Verify the behaviour post preview when download is in progress during preview pages");
											       if(!Utilities.setResultsKids("VK_1689", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		       									     											    	   
						                           //navigate back to reader page from dwon load page
											       driver.navigate().back();
											       Thread.sleep(2000);
									         }
									         else
									         {
									        	   test.log(LogStatus.WARNING, "cancel,progressbar,downloadingText are not Available because downlaod gets completed while swiping pages");						 			
									        	   test.log(LogStatus.WARNING, "VK_1689-----Verify the behaviour post preview when download is in progress during preview pages");
											       if(!Utilities.setResultsKids("VK_1689", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		     
											       basepagev2.takeScreenshot();
									          }		
								            
								            //verifying landed on 6th page after completion of down load
								            if(Utilities.explicitWaitVisible(driver, ebookspagev2.cancelbuttonb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingprogressbarb, 10) && Utilities.explicitWaitVisible(driver, ebookspagev2.downloadingtextb, 10) ) 											   
									        {  
										           test.log(LogStatus.PASS, "VK_1688-----Verify if Ebook continues to play post preview when download is completed during preview pages");
							 				       if(!Utilities.setResultsKids("VK_1688", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		       									     											    	   
						                             
									        }
									        else
									        {
									        	   test.log(LogStatus.WARNING, "failed to continue after 5 th page when down load is completed during preview pages");						 			
									        	   test.log(LogStatus.WARNING, "VK_1688-----Verify if Ebook continues to play post preview when download is completed during preview pages");
											       if(!Utilities.setResultsKids("VK_1688", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		     
											       basepagev2.takeScreenshot();
									        }	
								            
								            //navigate back to book detail page from reader page
								             driver.navigate().back();
								             Thread.sleep(2000);
						     }
				             else
				             {
					            	    test.log(LogStatus.WARNING, "Unable to continue because  previewe button is changed to Rread Button  in book detail page because downlaod gets completed while swiping up to preview button after clicking on dwon load book link'");						 						 
					            	    test.log(LogStatus.WARNING, "VK_1689-----Verify the behaviour post preview when download is in progress during preview pages");
								        if(!Utilities.setResultsKids("VK_1689", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		     
								        test.log(LogStatus.WARNING, "VK_1688-----Verify if Ebook continues to play post preview when download is completed during preview pages");
									    if(!Utilities.setResultsKids("VK_1688", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		     
									    basepagev2.takeScreenshot();
				             }
						   
                             //navigate back to first Tray reads page from book detail page
						      driver.navigate().back();
				              Thread.sleep(2000);
				  
						
 //VK_1690-----Verify the behaviour tapping on 'Cancel' button in downloading overlay				
//VK_430-----Verify the behaviour tapping on 'Cancel' button in downloading overlay	
				              
		                test.log(LogStatus.INFO, "VK_1690-----Verify the behaviour tapping on 'Cancel' button in downloading overlay");
				         				         
				         //click on card3 in best reads page					   
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
					    	     test.log(LogStatus.FAIL, " card 3  is not Available  under first tray page ");
			         	         basepagev2.takeScreenshot(); 
					      }
						 
						    //verify preview button in book detail page
						    if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
						    {
								 
							       //scroll to bookDownloadLink	and click on Download book link		     
									for(int scroll=0;scroll<=4;scroll++)
								    {												
										      if(Utilities.explicitWaitClickable(driver, ebookspagev2.downloadbooklinkb, 1))
										      {	
										             test.log(LogStatus.INFO, "downloadbook link is available");							          
										            try{							        	  
										        	      ebookspagev2.downloadbooklinkb.click();							        	  
										        	      test.log(LogStatus.INFO, "clicked on downloadbook link ");
										        	     
										               }catch(Exception e)
										               {
										            	   test.log(LogStatus.FAIL, "failed to click on downloadbook link ");
										            	   basepagev2.takeScreenshot();
										               }
										            
											          break; 
											      
									          }else   Utilities.verticalSwipe(driver);
							        } 
						    }else
						    {
						    	  test.log(LogStatus.FAIL, "preview button is not Available in book detail page");
				            	   basepagev2.takeScreenshot();
						    }
						    
						  //scroll up to  Preview button
						   for(int scroll=0;scroll<=3;scroll++)
					       {												
							      if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 1) || Utilities.explicitWaitVisible(driver, readpagev2.readButton, 1) )
							      {	
							              test.log(LogStatus.INFO, "preview button or read button  is available");							          							         
							              break; 
								      
						          }else   Utilities.verticalSwipeReverse(driver);
				           } 
				    			
						   if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
						    {
								 try{
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
												
														test.log(LogStatus.PASS, "Verify the behavior when tapping on 'Preview' button  ");
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
				    		
								              //verifying the functionality of cancel button in dwonLoad page  						                      
						                      if(Utilities.explicitWaitVisible(driver, ebookspagev2.cancelbuttonb, 10)) 											   
										      { 
						                    	     ebookspagev2.cancelbuttonb.click();
						                    	 	test.log(LogStatus.INFO, "clicked on cancel button in dwonlaoding apge");					                    	 
										      }
						                      else
										      {
						                    	  test.log(LogStatus.WARNING, "failed to click on cancel button in downlaoding page because downloading page is not availble because dwon load gets completed while swiping pages");
						                    	  basepagev2.takeScreenshot();
										      }
						                      
									            if(Utilities.explicitWaitVisible(driver, readpagev2.previewButton, 20)) 											   
										        {  
											           test.log(LogStatus.PASS, "VK_1690-----Verify the behaviour tapping on 'Cancel' button in downloading overlay");
												       if(!Utilities.setResultsKids("VK_1690", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		       									     											    	   							                          
										         }
										         else
										         {
										        	   test.log(LogStatus.WARNING, "failed to land on respective prgroinfo page(book detail page) because failed to click on cancel button in down laod page because down load gets completed while swiping pages");					 			
										        	   test.log(LogStatus.WARNING, "VK_1690-----Verify the behaviour tapping on 'Cancel' button in downloading overlay");
												       if(!Utilities.setResultsKids("VK_1690", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		     
												       basepagev2.takeScreenshot();
										        }		
									         	
									           //scroll to bookDownloadLink	and click on Download book link		     
												for(int scroll=0;scroll<=3;scroll++)
											    {												
													      if(Utilities.explicitWaitClickable(driver, ebookspagev2.downloadbooklinkb, 1))
													      {	
													             test.log(LogStatus.INFO, "downloadbook link is available");							          												            													            
														          break; 
														      
												          }else   Utilities.verticalSwipe(driver);
										        } 
												
												//verify downloading stopped or not after clicking on cancel button in book detail page
												if(Utilities.explicitWaitClickable(driver, ebookspagev2.downloadbooklinkb, 1))
											     {	
											             test.log(LogStatus.INFO, "downloadbook link is available");
											             test.log(LogStatus.INFO, "downloading stopped after clicking on cancelbutton in book detail page");
											     }
												 {
													     test.log(LogStatus.WARNING, "downloadbook link is not available because failed to click on cancel button because down load gets completed while swiping pages");
										                 test.log(LogStatus.INFO, "downloading failed to stop after clicking on cancelbutton in book detail page");
										     
												 }
									            									             
						     }
				             else
				             {
				            	   test.log(LogStatus.WARNING, "unable to continue because preview button changed to read button while swiping up after clicking on dwon link in book detail page");						 			
					        	   test.log(LogStatus.WARNING, "VK_1690-----Verify the behaviour tapping on 'Cancel' button in downloading overlay");
							       if(!Utilities.setResultsKids("VK_1690", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  							  		     
							       basepagev2.takeScreenshot();
				             }
						   
                            
	}
	
	@DataProvider
	public Object[][] getData()
	{
	return DataUtil.getData(testName,xls);											          					
    }   		 
}
