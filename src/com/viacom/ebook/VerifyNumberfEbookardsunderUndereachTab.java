package com.viacom.ebook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
//aurhorName=Bhaskar
public class VerifyNumberfEbookardsunderUndereachTab extends BaseTestV2
{	
	// VK_624----Verify Daily Picks under books are same as in the carousel in the read section 	
	// VK_628----Verify the number of cards present under Relatd/morefromauthor/dailypickstab 	
	
	String	firstBookRelatedTab="";
	private Object bookNameBefore;
	
	String testName = "VerifyNumberfEbookardsunderUndereachTab";	
	@Test(dataProvider = "getData")
	public void VerifyNumberfEbookardsunderUndereachTab(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("VerifyNumberfEbookardsunderUndereachTab");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		       if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		  }
            	
		//Launch Voot kids App();
		       
				 launchApp();				 
				 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
				 HomePageV2 homepagev2=new HomePageV2(driver,test);
				 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
				 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
				 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
				 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
				 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
				 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
				 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
				 BasePageV2  basepagev2=new BasePageV2(driver,test);
				
				 homepagev2.login(data.get("Email"),data.get("Password"));
				 downloadsv2.deleteAllDownloads();
				 
				 boolean downloadCompleted=false , bookDownload=false ,readerDisplayedPreview=false ,readerDisplayedRead=false;				
				 
				 String bookNameAfter="" ,cardname=null,bookNameBefore="";
				 
				 WebElement bookname=null ,booknamed=null ,booknamem=null ,bookName=null,authorName=null;
				 
				 int c=0,n=0;
				
					
// VK_624----Verify Daily Picks under books are same as in the carousel in the read section  	
				
				 
				 
				    HomePageV2.tabClick("Read");
				    Thread.sleep(4000);
			    
					
					   ArrayList<String> bookNames=new ArrayList<String>();	
										
					     for(int count=0;count<=4;count++)
					     {									
						        try {
										    Thread.sleep(1000);
										    bookName=driver.findElementByXPath("//android.widget.LinearLayout[@index='"+count+"']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']//android.widget.RelativeLayout[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']");
									     	bookNames.add(bookName.getAttribute("text"));
										    test.log(LogStatus.INFO, "Book name fetched from UI is "+bookName.getAttribute("text"));					
								    }catch(Exception e) {}
								  
									
						             c=c+1;
						             
									if(c==5)
									{
									   break;	
									}
									else
									{
									    Utilities.horizontalSwipe(driver);
									     Thread.sleep(1000);}
								    }
																                
								Utilities.horizontalSwipeReverseSlow(driver);
								Utilities.horizontalSwipeReverseSlow(driver);
								Utilities.horizontalSwipeReverseSlow(driver);
					     
						 if(Utilities.explicitWaitVisible(driver, readpagev2.firstBookNameInCarousal, 10))
					     {
								    bookNameBefore= readpagev2.firstBookNameInCarousal.getAttribute("text");
								    test.log(LogStatus.INFO, "book  is availble in carousel");
								  try{
									      readpagev2.firstBookNameInCarousal.click();
									       test.log(LogStatus.INFO, "Clicked on the Book "+bookNameBefore+" from Read Carousal");
								     }catch(Exception e)
								     {
								    	    test.log(LogStatus.FAIL,"Clicked on the Book "+bookNameBefore+" from Read Carousal");
								    	    basepagev2.takeScreenshot();	
								     }
								  
					     }
						 else
						 {
						     test.log(LogStatus.FAIL, "book  is not availble in carousel");
						      basepagev2.takeScreenshot();	
						 }
						 
				      //click on preview buttonin book detail page						 
				     if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 20))
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
										  }catch(Exception e)
										  {					
											test.log(LogStatus.FAIL, "Unable to click on PREVIEW button"); 
										  }
										   
							}//preview if condition is ending	
										
				         //for removing coach cards
				         readpagev2.dismissReaderCoachCards();
				     
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
										
											
						        /* // refreshApp
										Thread.sleep(2000);
										driver.runAppInBackground(Duration.ofSeconds(3));
										test.log(LogStatus.INFO, "Put app to background for 3 seconds");
										driver.currentActivity();*/
										
							 	
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
							
						    
						    //do vertical swipe till to get daily picks tab						    
						    
					        Utilities.verticalSwipeReverse(driver);
						    Utilities.verticalSwipeReverse(driver);
						    
						    // click on daily picks tab
						    
						if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderTabDailyPicks, 20))
						{					
							
							try {
							     readpagev2.bookReaderTabDailyPicks.click();
							      test.log(LogStatus.INFO, " clicked on Daily Picks tab ");
							    }catch(Exception e)					     	
							    {
							    	test.log(LogStatus.FAIL, " failed to clicke on Daily Picks tab ");
							    	 basepagev2.takeScreenshot();
							    }
						
						}
						else
						{
						test.log(LogStatus.FAIL, " failed to clicke on Daily Picks tab ");
						 basepagev2.takeScreenshot();
						}
						
						
						Utilities.verticalSwipe(driver);				
						Utilities.verticalSwipe(driver);
						 					
					    ArrayList<String> booknamesindailypickstab1=new ArrayList<String>();
					
						for(int count=0;count<=4;count++)
						{
									
							       try {
										     Thread.sleep(1000);
										     booknamed=driver.findElementByXPath("//android.view.ViewGroup[@index='"+count+"']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
						      	             booknamesindailypickstab1.add(booknamed.getAttribute("text"));
							  	             test.log(LogStatus.INFO, "Book name fetched from UI is "+booknamed.getAttribute("text"));							
								        }
								         catch(Exception e)
								        {
								    	 test.log(LogStatus.FAIL, "Book name is NOT displayed for book card ");
								    	 basepagev2.takeScreenshot();
									    }											
						}					
                   
					  test.log(LogStatus.INFO, ""+booknamesindailypickstab1);	
							
					  if(bookNames.size()==booknamesindailypickstab1.size() )
					   {									      
						   test.log(LogStatus.INFO, "books under daily picks  are same as in the carousel in read section  ");			   																
					   }						
					   else							
					   {
							 test.log(LogStatus.FAIL, "books under daily picks are not  same as in the carousel in read section ");
							 basepagev2.takeScreenshot();											
					   }
			        
						 for(int i=0;i<=4;i++)
				         {
					                if(bookNames.get(i).equalsIgnoreCase(booknamesindailypickstab1.get(i)))
					                {
					                	   test.log(LogStatus.INFO, ""+bookNames.get(i),booknamesindailypickstab1.get(i));	
					                	    n=n+1;
					                }				  
				         }						 
						 if(n==5)
						 {
									test.log(LogStatus.PASS, "VK_624--books under daily picks are  same as books under readcarousel ");			
									 if(!Utilities.setResultsKids("VK_624", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");									      
						 }
						 else							
					     {
									 test.log(LogStatus.FAIL, "VK_624--- books under daily picks are same not as books under readcarousel ");									
									 if(!Utilities.setResultsKids("VK_624", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									 basepagev2.takeScreenshot();
					     }		 				 
									     
 				           //navigated to book detail page  from daily picks  tab	page						 
						 
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
										driver.navigate().back();
										Thread.sleep(2000);
						 
// VK_628----Verify the number of cards present under Relatd/morefromauthor/dailypickstab 
				 						
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
						
						 /*
									
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
								
							    
							    //do vertical swipe till to get related tab						    
							    
						        Utilities.verticalSwipeReverse(driver);
							    Utilities.verticalSwipeReverse(driver);
						
							    //Related tab should be displayed							    					           
									  if(Utilities.explicitWaitClickable(driver, readpagev2.bookReaderTabRelated, 20))
						              {							      
								                   readpagev2.bookReaderTabRelated.click();
								                   test.log(LogStatus.INFO, " clicked on Related tab ");
					                   }
									  else
									   {
										      test.log(LogStatus.FAIL, " failed to click on Related tab "); 
								             basepagev2.takeScreenshot(); 						                 
									   }
							     							
                          //checking number of cards under related tab								
								ArrayList<String> booknamesinrelatedtab=new ArrayList<String>();	
								for(int count=0;count<=3;count++)
								{
									 try
									   {
										Thread.sleep(1000);
						      	                   bookname=driver.findElementByXPath("//android.view.ViewGroup[@index='"+count+"']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
						        	               booknamesinrelatedtab.add(bookname.getAttribute("text"));
							  	                   test.log(LogStatus.INFO, "Book name fetched from UI is "+bookname.getAttribute("text"));							
								        }
								         catch(Exception e)
								        {
								    	         test.log(LogStatus.FAIL, "Book name is NOT displayed under related tab "+count);								    	 
								                 basepagev2.takeScreenshot(); 	
									    }																	
								}
								
                                Utilities.verticalSwipe(driver);	
                                Thread.sleep(1000);
                                Utilities.verticalSwipe(driver); 
                                Thread.sleep(1000);
                                
								for(int count=2;count<=7;count++)
								{
									     try
									     {
									         	Thread.sleep(1000);
									           	bookname=driver.findElementByXPath("//android.view.ViewGroup[@index='"+count+"']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
						        	             //String x=bookname.getAttribute("text");
						      	                 if(booknamesinrelatedtab.contains(bookname.getAttribute("text")))
						      	                  {
						      		                    test.log(LogStatus.INFO, "already available in list"+bookname.getAttribute("text"));	  
						      	                  }
						      	                 else
						      	                  {
						      		                   booknamesinrelatedtab.add(bookname.getAttribute("text"));  
						      		                   test.log(LogStatus.INFO, "not available in list adding to a list"+bookname.getAttribute("text"));	
						      		                    		
						      	                  }
									    }catch(Exception e)	{}	   							
								  }
								
								test.log(LogStatus.INFO, ""+ booknamesinrelatedtab);	
																
								int c1=booknamesinrelatedtab.size();
								
			
						   if(booknamesinrelatedtab.size()<=8)
						   {									      
							test.log(LogStatus.INFO, "numberofcards present under related tab is verified "+c1);
						   																
						   }						
						   else							
						   {
							test.log(LogStatus.FAIL, "numberofcards present under related tab is failed to  verify	");
							 basepagev2.takeScreenshot();													
						   }
	
						   // do vertical swipe reverse till to get more fromAuthor tab
						   
					     	Utilities.verticalSwipeReverse(driver);   					     	
					     	Utilities.verticalSwipeReverse(driver); 
				 
				 
                            //checking number of cards under moreFrom author tab
					
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
					  
						    ArrayList<String> booknamesinmorefromauthortab=new ArrayList<String>();	
						    
						    for(int count=0;count<=3;count++)
							{
								 try
								   {
										Thread.sleep(1000);							
										 booknamem=driver.findElementByXPath("//android.view.ViewGroup[@index='"+count+"']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
						      	         booknamesinmorefromauthortab.add(booknamem.getAttribute("text"));
							  	         test.log(LogStatus.INFO, "Book name fetched from UI is "+booknamem.getAttribute("text"));							
							        }
							         catch(Exception e)
							        {
								    	 test.log(LogStatus.FAIL, "Book name is NOT displayed for under more fromauthor tab "+count);
								    	 basepagev2.takeScreenshot(); 
								    }														
							}
						    
                            Utilities.verticalSwipe(driver);                           
                            Utilities.verticalSwipe(driver);
                          
                            
							for(int count=2;count<=7;count++)
							{
								    try
								    {
								         	Thread.sleep(1000);
								         	 booknamem=driver.findElementByXPath("//android.view.ViewGroup[@index='"+count+"']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
					        	            // String y=booknamem.getAttribute("text");
					      	                 if(booknamesinmorefromauthortab.contains(booknamem.getAttribute("text")))
					      	                  {
					      		              test.log(LogStatus.INFO, "already available in list"+booknamem.getAttribute("text"));	  
					      	                  }
					      	                 else
					      	                  {
					      	                	booknamesinmorefromauthortab.add(booknamem.getAttribute("text"));   
					      	                   test.log(LogStatus.INFO, "not available in list adding to a list"+booknamem.getAttribute("text"));
					      	                  }
								   }catch(Exception e)	{}	   
								    
							}
							
							test.log(LogStatus.INFO, ""+booknamesinmorefromauthortab);	
											
							int c2=booknamesinmorefromauthortab.size();
							
		
					   if(booknamesinmorefromauthortab.size()<=8)
					   {									      
						test.log(LogStatus.INFO, "numberofcards present under morefromauthor tab is verified "+c2);
					     																
					   }						
					   else							
					   {
						test.log(LogStatus.FAIL, "numberofcards present under morefromauthor tab is failed to verify");
						 basepagev2.takeScreenshot();							
						 
					    }
					  
					   // do swipe till to get daily picks tab
					   
				      Utilities.verticalSwipeReverse(driver);
				      Thread.sleep(1000);
				      Utilities.verticalSwipeReverse(driver);
				      Thread.sleep(1000);

				      
					 //checking number of cards under daily picks tab	
				      
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
				      
				        Utilities.verticalSwipe(driver);
				        Thread.sleep(1000);
				        Utilities.verticalSwipe(driver);
				        Thread.sleep(1000);
				        
						ArrayList<String> booknamesindailypickstab=new ArrayList<String>();	
						
						for(int count=0;count<=4;count++)
						{
								 try
								   {
									Thread.sleep(1000);
					      	         booknamed=driver.findElementByXPath("//android.view.ViewGroup[@index='"+count+"']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
					      	         booknamesindailypickstab.add(booknamed.getAttribute("text"));
						  	         test.log(LogStatus.INFO, "Book name fetched from UI is "+booknamed.getAttribute("text"));							
							        }
							         catch(Exception e)
							        {
							    	 test.log(LogStatus.FAIL, "Book name is NOT displayed under daily picks tab ");
							    	 basepagev2.takeScreenshot(); 
								    }													
						}
                     									                     
						test.log(LogStatus.INFO, ""+booknamesindailypickstab);	
						
				        int c3=booknamesindailypickstab.size();	
				   
						   if(booknamesindailypickstab.size()<=5)
						   {									      
							test.log(LogStatus.INFO, "numberofcards present under dailypicks tab is verified "+c3);			   																
						   }						
						   else							
						   {
							test.log(LogStatus.FAIL, " numberofcards present under dailypicks tab is  failed to verify 	");
							 basepagev2.takeScreenshot();												
						   }
				   					   			 
		            // VK_628----Verify the number of cards present under Relatd/morefromauthor/dailypickstab 	
				   
						   if(booknamesinrelatedtab.size()<=8 && booknamesinmorefromauthortab.size()<=8 && booknamesindailypickstab.size()<=5  )
						   {									      
							      test.log(LogStatus.PASS, "VK_628--numberofcards present under related/morefromauthor/dailypicks tabs are verified ");
						           if(!Utilities.setResultsKids("VK_628", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					       																
						   }						
						   else							
						   {
							     test.log(LogStatus.FAIL, " VK_628----numberofcards present under related/morefromauthor/dailypicks tabs are failed to verify ");												
							       if(!Utilities.setResultsKids("VK_628", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							         basepagev2.takeScreenshot();	 
						   }
							   
	}	
			@DataProvider
			public Object[][] getData()
			{
				return DataUtil.getData(testName,xls);						
			          
			
			}
	
}	

