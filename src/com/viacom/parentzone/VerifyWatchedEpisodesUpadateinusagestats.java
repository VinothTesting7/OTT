package com.viacom.parentzone;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
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
// AuthorName=Bhaskar
public class VerifyWatchedEpisodesUpadateinusagestats extends BaseTestV2
{
	// VK_1608----Verify if watched content gets updated in usage stats  if user launches the player & closes ");
	//  VK_1609----Verify if watched content gets updated in usage stats if user watches a video partially ");
	//  VK_1610----Verify if watched content gets updated in usage stats  if user watches a video completely");
	
	String	firstBookRelatedTab=""; 
	String testName = "VerifyWatchedEpisodesUpadateinusagestats";
	
	private Object bookNameBefore;
	@Test(dataProvider = "getData")
	public void VerifyWatchedEpisodesUpadateinusagestats(Hashtable<String, String> data) throws Exception 
	{		
		
		test = rep.startTest("VerifyWatchedEpisodesUpadateinusagestats");
		test.log(LogStatus.INFO, "Starting the test VerifyWatchedEpisodesUpadateinusagestats on: "+VootConstants.DEVICE_NAME);
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
				 KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
				 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
				 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
				 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
				 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
				 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
				 BasePageV2  basepagev2=new BasePageV2(driver,test);
				 LaunchPageV2 launchpagev2=new  LaunchPageV2(driver,test);
				 KidsPlayerPageV2 kidspagev2=new KidsPlayerPageV2(driver,test);
				 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
				 
				 //login with valid credentials
				 
				 homepagev2.login(data.get("Email"),data.get("Password"));
				
				 
				 
				 
				 
		          boolean downloadCompleted=false, bookDownload=false, readerDisplayedPreview=false,readerDisplayedRead=false;

		          int count=0, updatedcount1=0, updatedcount2=0, updatedcount3=0, updatedcount=0;
				 
				 String episode="", bookNameBefore="", episode1="",  episode2="", episode3="", episodescount="",episodescount1="", episodescount3="", episodescount2=""; 
				  			 				 
				 String lastviewedfirsttitle="",lastviewedfirsttitle1="", lastviewedsecondtitle="", lastviewedthirdtitle="";
				
												 
//Verify if watched content gets updated in usage stats  if user launches the player & closes testCase:VK_1608				 	

				 
          //click on profile icon			 
			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
			{
				homepagev2.profilepic.click();
				test.log(LogStatus.INFO, "Clicked on Profile pic icon");					
			}
			else 
			{
				test.log(LogStatus.FAIL, " failed to Clicked on Profile pic icon");
				basepagev2.takeScreenshot();
			}
			
			 //refresh the app
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
			if (Utilities.explicitWaitVisible(driver, launchpagev2.pinContainer, 10)) 
			{
				        launchpagev2.pinContainer.clear();
						launchpagev2.pinContainer.sendKeys("1111");
						test.log(LogStatus.INFO, "Entered PIN");	
						

					    if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZonePage, 10))
					    {
						   test.log(LogStatus.INFO, "Parent zone Page is displayed");
						   
					    }
					    else
					    {
					    	test.log(LogStatus.FAIL, "Parent zone Page is not displayed");  
					    	basepagev2.takeScreenshot();
					    }
					    
					    
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
				
					   try {
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
						
						
							//Scroll to watch/episodes							 
							 for(int scroll=0;scroll<=4;scroll++)
							 {
										 if(Utilities.explicitWaitVisible(driver,  settingspagev2.usageEpisodesCount, 1))
										 {
											   test.log(LogStatus.INFO, " watch text is displayed");
											   break;
										 }
										 else
										 { 
											 Utilities.verticalSwipe(driver); 							 
										 } 
							 }
						 
							 Thread.sleep(2000);
							 //refresh the app
							 driver.runAppInBackground(Duration.ofSeconds(3));
						     test.log(LogStatus.INFO, "Put app to background for 3 seconds");
						     driver.currentActivity();
						     
						     
							 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageEpisodesCount, 10))
							 {
								  episodescount=settingspagev2.usageEpisodesCount.getAttribute("text");
								  
								  System.out.println(episodescount);
								  test.log(LogStatus.INFO, "fetching  episodescount is"+episodescount);						 						  
							 }
							 else
							 {
								 test.log(LogStatus.FAIL, " failed to fetching  episodescount is");  
								 basepagev2.takeScreenshot();	
							 }
				     
							 try {
						        count=Integer.parseInt(episodescount); 
							    }catch(Exception e) {}
		    }			
			else
			{ 
				       test.log(LogStatus.FAIL,"Pin Container not displayed");
				       basepagev2.takeScreenshot();
				       
				       try {
				             driver.hideKeyboard();
				           }catch(Exception e){}
				       driver.navigate().back();
				       Thread.sleep(2000);
				       driver.navigate().back();
				       Thread.sleep(2000);
			}
						 
			  //navigate to HomePage  from parentZone page	
			 
				 driver.navigate().back();
				 Thread.sleep(2000);										 
				 driver.navigate().back();
				 Thread.sleep(2000);											 
				 
				 //searching and clicking on watch tab						 
				 Utilities.verticalSwipeReverse(driver);
				 Utilities.verticalSwipeReverse(driver);	
				 
				//click on watch tab						 
				 homepagev2.tabClick("Watch");							
				 Thread.sleep(4000);	
				 
	           //started to play episode 1       	  
			   if(Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
			   {												
					      test.log(LogStatus.INFO, "episode 1 is available"+episode1);					     
					    try{									
								episode=watchpagev2.cardnameinwatchcarousel.getAttribute("text");
								episode1=episode.replaceAll("[^a-zA-Z0-9]",  "");								
								System.out.println(episode1);
								watchpagev2.cardinwatchcarousel.click();
								test.log(LogStatus.INFO, "clicked on first episode  "+episode1);															
							}catch(Exception e)
							{
								 test.log(LogStatus.FAIL, "failed to click on episode 1");
								 basepagev2.takeScreenshot();	
							}							   						   						 
			  }
			  else
			  {
			              test.log(LogStatus.FAIL, "episode 1 is not availble");
			              basepagev2.takeScreenshot();	
			  }
					
			   //verify video player availability
			  if(Utilities.explicitWaitClickable(driver,playerpagev2.videoPlayer, 20)) 
			  {  			 			 
				     test.log(LogStatus.INFO, "video player is  available");
				     try{					     
					       homepagev2.verifyAndProgressBar();							      				    
				           kidsplayerv2.pauseVideo();						     
				          test.log(LogStatus.INFO, "clicked on pause button episode play back page ");				          
			           }catch(Exception e)
			           {
				    	   test.log(LogStatus.FAIL, " failed to click on pause button episode play back page");
				    	   basepagev2.takeScreenshot();
			           }		
			  }
			  else
			  {				 
				     test.log(LogStatus.FAIL, "video player is not available");					 
				     basepagev2.takeScreenshot();
			  }
					 
		    //navigated back  to home page from play back page						
		        driver.navigate().back();
		        Thread.sleep(2000);
		    
		     
		       //verifying episode 1 updated or not under usage stats
		     
		       //do vertical swipe reverse till to get profile-icon-
		     
		       Utilities.verticalSwipeReverse(driver);
		       Utilities.verticalSwipeReverse(driver);
		     
		       //click on profile icon									 
				if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
				{
					homepagev2.profilepic.click();
					test.log(LogStatus.INFO, "Clicked on Profile pic icon");					
				}
				else 
				{
					test.log(LogStatus.FAIL, " failed to Clicked on Profile pic icon");
					basepagev2.takeScreenshot();
				}
												
				 //refresh the app
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
					        launchpagev2.pinContainer.clear();
							launchpagev2.pinContainer.sendKeys("1111");
							test.log(LogStatus.INFO, "Entered PIN");		
																					
							   if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZonePage, 10))
							    {
								   test.log(LogStatus.INFO, "Parent zone Page is displayed");
								   
							    }
							    else
							    {
							    	test.log(LogStatus.FAIL, "Parent zone Page is not displayed");  
							    	basepagev2.takeScreenshot();
							    }
							   
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
										
									//Scroll to watch/episodes									 
									 for(int scroll=0;scroll<=4;scroll++)
									 {
												 if(Utilities.explicitWaitVisible(driver,  settingspagev2.usageEpisodesCount, 1))
												 {
													   test.log(LogStatus.INFO, " watch text is displayed");
													   break;
												 }
												 else
												 { 
													 Utilities.verticalSwipe(driver); 							 
												 } 
									 }
								 
									 Thread.sleep(2000);
									 //refresh the app
									 driver.runAppInBackground(Duration.ofSeconds(3));
								     test.log(LogStatus.INFO, "Put app to background for 3 seconds");
								     driver.currentActivity();
								     
								     
									 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageEpisodesCount, 10))
									 {
										  episodescount=settingspagev2.usageEpisodesCount.getAttribute("text");
										  
										  System.out.println(episodescount);
										  test.log(LogStatus.INFO, "fetching  episodescount is"+episodescount);						 						  
									 }
									 else
									 {
										 test.log(LogStatus.FAIL, " failed to fetching  episodescount is");  
										 basepagev2.takeScreenshot();	
									 }
					     
						 
								 try{
								     updatedcount1=Integer.parseInt(episodescount); 
								    }catch(Exception e) {}	
								 
								    if(updatedcount1>=(count+1))
							        {							    						    	
								    	 test.log(LogStatus.PASS, " Testcase: VK_1608---Verify if watched content gets updated in usage stats  if user launches the player & closes  ");
								    	 if(!Utilities.setResultsKids("VK_1608", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							        }	
						    	    else
							        {
								    	  test.log(LogStatus.FAIL, "Testcase: VK_1608---Verify if watched content gets updated in usage stats  if user launches the player & closes ");
								    	  if(!Utilities.setResultsKids("VK_1608", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
								    	  basepagev2.takeScreenshot();
							        }  
							               
				}				
				else
				{ 
					 test.log(LogStatus.FAIL,"Pin Container not displayed");
					 basepagev2.takeScreenshot();
					 
					 try{
			              driver.hideKeyboard();
			             }catch(Exception e){}
				         driver.navigate().back();
				         Thread.sleep(2000);
				          driver.navigate().back();
				          Thread.sleep(2000);
				}
				
			  
				 
				     //navigate to HomePage  from parent page										 
					 driver.navigate().back();
					 Thread.sleep(2000);										 
					 driver.navigate().back();
					 Thread.sleep(2000);										 
			                 						 
//Verify if watched content gets updated in usage stats if user watches a video partially TestCase:VK_1609
										 
			 //searching and click on watch tab 
		 	 Utilities.verticalSwipeReverse(driver);
		 	 Utilities.verticalSwipeReverse(driver);
		 	
		 										 	
		 	 homepagev2.tabClick("Read");	
			 Thread.sleep(2000);								 	
			 homepagev2.tabClick("Watch");	
			 Thread.sleep(4000);									 
			 Utilities.horizontalSwipe(driver);
			 Thread.sleep(2000);
	
	          //episode 2 is started playing  
		      if(Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
			  {																																
					  test.log(LogStatus.INFO, "second episode is  availble");
						try 
						{														
								 episode=watchpagev2.cardnameinwatchcarousel.getAttribute("text");	
								 episode2=episode.replaceAll("[^a-zA-Z0-9]",  "");
								 System.out.println(episode2);
								 watchpagev2.cardinwatchcarousel.click();
								 test.log(LogStatus.INFO, "Clicked on second episode: "+episode2);
								
						}catch(Exception e)
						{
							 test.log(LogStatus.FAIL, "failed to click on  second episode: ");	
							 basepagev2.takeScreenshot();
						}																						 
		     }
		     else
	    	 {
	    	       test.log(LogStatus.FAIL, "second episode is not availble");
	    	        basepagev2.takeScreenshot();
	    	 }
	  	   		   
	        //watch an episode partially   	    
		     if(Utilities.explicitWaitClickable(driver,playerpagev2.videoPlayer, 20)) 
		     {
		    	      test.log(LogStatus.INFO, "video player is  available"); 			  	    	   				    		
				      homepagev2.verifyAndProgressBar();							      
		              kidsplayerv2.pauseVideo();	
		                Thread.sleep(2000);
		    	      Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);								    	
			 }
			 else
			 {			 
				    test.log(LogStatus.FAIL,"Player did not lauch when tapping downloaded content -"+episode2);
				    BasePageV2.takeScreenshot();
			 }
	    
		     //back to home page from watch player			   
		     driver.navigate().back();
	         Thread.sleep(2000);	                      
           // start verifying second watched episode updated or not
         
           //do vertical swipe reverse till to get profile-icon
         
            Utilities.verticalSwipeReverse(driver);
            Utilities.verticalSwipeReverse(driver);
         
            //click on profile icon			 
			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10))
			{
				homepagev2.profilepic.click();
				test.log(LogStatus.INFO, "Clicked on Profile pic icon");					
			}
			else 
			{
				test.log(LogStatus.FAIL, " failed to Clicked on Profile pic icon");
				basepagev2.takeScreenshot();
			}
			
			 Thread.sleep(2000);
			 //refresh the app
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
				        launchpagev2.pinContainer.clear();
						launchpagev2.pinContainer.sendKeys("1111");
						test.log(LogStatus.INFO, "Entered PIN");	
						
						
						
					    if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZonePage, 10))
					    {
						   test.log(LogStatus.INFO, "Parent zone Page is displayed");
						   
					    }
					    else
					    {
					    	test.log(LogStatus.FAIL, "Parent zone Page is not displayed");  
					    	basepagev2.takeScreenshot();
					    }
					    
					    
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
																													
							//Scroll to watch/episodes											 
							 for(int scroll=0;scroll<=4;scroll++)
							 {
									 if(Utilities.explicitWaitVisible(driver,  settingspagev2.usageEpisodesCount, 1))
									 {
										   test.log(LogStatus.INFO, " watch text is displayed");
										   break;
									 }
									 else
									 { 
										 Utilities.verticalSwipe(driver); 							 
									 } 
							 }
						 
							 Thread.sleep(2000);
							 //refresh the app
							 driver.runAppInBackground(Duration.ofSeconds(3));
						     test.log(LogStatus.INFO, "Put app to background for 3 seconds");
						     driver.currentActivity();
							
							 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageEpisodesCount, 10))
							 {
								  episodescount=settingspagev2.usageEpisodesCount.getAttribute("text");
								  
								  System.out.println(episodescount);
								  test.log(LogStatus.INFO, "fetching  episodescount is"+episodescount);						 						  
							 }
							 else
							 {
								 test.log(LogStatus.FAIL, " failed to fetching  episodescount is");  
								 basepagev2.takeScreenshot();	
							 }
			         
							 try{
						         updatedcount2=Integer.parseInt(episodescount); 
							     }catch(Exception e) {}		
							 
						    if(updatedcount2>=(updatedcount1+1))
					        {							    						    	
						    	    test.log(LogStatus.PASS, " Testcase: VK_1609---Verify if watched content gets updated in usage stats if user watches a video partially  ");
						    	    if(!Utilities.setResultsKids("VK_1609", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					        }	
				    	   else
					        {
				    		       test.log(LogStatus.FAIL, " Testcase: VK_1609---Verify if watched content gets updated in usage stats if user watches a video partially ");
						    	  if(!Utilities.setResultsKids("VK_1609", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
						    	  basepagev2.takeScreenshot();
					        }
			}
			else
			{ 
				 test.log(LogStatus.FAIL,"Pin Container not displayed");
				 basepagev2.takeScreenshot();
				 
				 try {
		             driver.hideKeyboard();
		           }catch(Exception e){}
		           driver.navigate().back();
		           Thread.sleep(2000);
		           driver.navigate().back();
		           Thread.sleep(2000);
			}
			  
		        
	             //navigated to HomePage  from parent zone page				 
		         driver.navigate().back();
		         Thread.sleep(2000);
		         driver.navigate().back();
		         Thread.sleep(2000);
		        
										  
// Verify if watched content gets updated in usage stats  if user watches a video completely	TestCase=VK_1610;
	 
	         //searching and clicking on watch page	                 						                 
		      Utilities.verticalSwipeReverse(driver);						 
		      Utilities.verticalSwipeReverse(driver);						 
		      homepagev2.tabClick("Read");	
		      Thread.sleep(2000);						     
			  homepagev2.tabClick("Watch");							 
			  Thread.sleep(4000);							 
			  Utilities.horizontalSwipe(driver);
			  Thread.sleep(2000);
			  Utilities.horizontalSwipe(driver);
			  Thread.sleep(2000);			 	
			  			  
			    //Episode 3 started playing      	  
		       if(Utilities.explicitWaitVisible(driver, watchpagev2.cardinwatchcarousel, 10))
		    	{																					
				            test.log(LogStatus.INFO, "episode 3 is availble");
							try 
							{											 									     
							    episode=watchpagev2.cardnameinwatchcarousel.getAttribute("text");
							    episode3=episode.replaceAll("[^a-zA-Z0-9]",  "");
							    System.out.println(episode3);
							    watchpagev2.cardinwatchcarousel.click();
							    test.log(LogStatus.INFO, "Clicked on third episode:                "+episode3);
							   																				
							}catch(Exception e)
							{
								test.log(LogStatus.FAIL, " failed to Click on third episode: ");	
								 basepagev2.takeScreenshot();
							}																				 
			    }
		       else
	    	    {
		    	      test.log(LogStatus.FAIL, "episode 3 is  not availble");
		    	       basepagev2.takeScreenshot();
	    	    }
		
		         //watching a content completely		       
		         if(Utilities.explicitWaitClickable(driver,playerpagev2.videoPlayer, 20)) 
			     {
		        	       test.log(LogStatus.INFO, "video player is  available");					 
		        	       homepagev2.verifyAndProgressBar();
		        	       kidsplayerv2.pauseVideo();
					       Thread.sleep(2000);
					     							    	 
			    	       Utilities.scrubtillend(driver, homepagev2.audioseekBar);									    	
				 }
				 else
				 {
					      test.log(LogStatus.FAIL,"Player did not lauch when tapping downloaded content -");
					       BasePageV2.takeScreenshot();
				 }	
		       		       
	                //back  to home page	from watch player			               								             
				     driver.navigate().back();
	                 Thread.sleep(2000);
	                 driver.navigate().back();
	                 Thread.sleep(2000);
  	                    
	           //verification started to verify played episodes are updated or not 
	                 
	                 //do vertical swipe reverse till to get profile-icon-4
	                 
	                 Utilities.verticalSwipeReverse(driver);
	                 Utilities.verticalSwipeReverse(driver);
	                 
	                 //click on profilePic 4th  time		
		                      
	                      //click on profile icon										 
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
							
						 
						 //refresh the app
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
						        launchpagev2.pinContainer.clear();
								launchpagev2.pinContainer.sendKeys("1111");
								test.log(LogStatus.INFO, "Entered PIN");	
								
								if(Utilities.explicitWaitVisible(driver, settingspagev2.parentZonePage, 10))
							    {
								   test.log(LogStatus.INFO, "Parent zone Page is displayed");
								   
							    }
							    else
							    {
							    	test.log(LogStatus.FAIL, "Parent zone Page is not displayed");  
							    	basepagev2.takeScreenshot();
							    }
							    
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
						
							try {
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
												
									
							         //Scroll to watch/episode 												
									 for(int scroll=0;scroll<=3;scroll++)
									 {
												 if(Utilities.explicitWaitVisible(driver,  settingspagev2.usageEpisodesCount, 1))
												 {										 
													 break;										 
												 }
												 else
												 { 										 
													 Utilities.verticalSwipe(driver); 										 
												 } 
									 }
									 
									 Thread.sleep(2000);
									 //refresh the app
									 driver.runAppInBackground(Duration.ofSeconds(3));
								     test.log(LogStatus.INFO, "Put app to background for 3 seconds");
								     driver.currentActivity();
								     
									 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageEpisodesCount, 10))
									 {
										  episodescount=settingspagev2.usageEpisodesCount.getAttribute("text");
										  
										  System.out.println(episodescount);
										  test.log(LogStatus.INFO, "fetching  episodescount is"+episodescount);						 						  
									 }
									 else
									 {
										 test.log(LogStatus.FAIL, " failed to fetching  episodescount is");  
										 basepagev2.takeScreenshot();	
									 }
									 
					               try{
								       updatedcount3=Integer.parseInt(episodescount);	
					                  }catch(Exception e) {}
					              
								    if(updatedcount3>=(updatedcount2+1))
							        {							    						    	
									        test.log(LogStatus.PASS, " Testcase: VK_1610---Verify if watched content gets updated in usage stats  if user watches a video completely "+updatedcount3);								    	
								    	    if(!Utilities.setResultsKids("VK_1610", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							        }	
						    	    else
							        {
						    	    	    test.log(LogStatus.FAIL, " Testcase: VK_1610-----Verify if watched content gets updated in usage stats  if user watches a video completely");					    	
								    	    if(!Utilities.setResultsKids("VK_1610", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
									    	    basepagev2.takeScreenshot();
								    } 
									
									
						}										
						else
						{ 
							 test.log(LogStatus.FAIL,"Pin Container not displayed");
							 basepagev2.takeScreenshot();
							 
							 try {
					             driver.hideKeyboard();
					           }catch(Exception e){}
					           driver.navigate().back();
					           Thread.sleep(2000);
					           driver.navigate().back();
					           Thread.sleep(2000);
						}
										     																	    
        }
						@DataProvider
						public Object[][] getData()
						{
						   return DataUtil.getData(testName,xls);											          					
					    }                 	

  }
