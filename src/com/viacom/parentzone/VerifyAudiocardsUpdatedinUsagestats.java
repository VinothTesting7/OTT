package com.viacom.parentzone;

import java.time.Duration;
import java.util.Hashtable;

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
//Author=Bhaskar

public class VerifyAudiocardsUpdatedinUsagestats extends BaseTestV2
{
		
	//VK_1616----Verify if watched content gets updated in usage stats if user launches and closes the audio player 	
	//VK_1617-----Verify if watched content gets updated in usage stats if user Plays an audio partial	
    //VK_1618------Verify if watched content gets updated in usage stats  if user Plays an audio Completely		     
		
	    String testName = "VerifyAudiocardsUpdatedinUsagestats";	
		@Test(dataProvider = "getData")		
		public void  VerifyAudiocardsUpdatedinUsagestats(Hashtable<String, String> data) throws Exception 
		{				
			  test = rep.startTest("VerifyAudiocardsUpdatedinUsagestats");
			  test.log(LogStatus.INFO, "Starting the test VerifyAudiocardsUpdatedinUsagestats: on  "+VootConstants.DEVICE_NAME);
			   //Check run mode
			       if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
				test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
				throw new SkipException("Skipping the test as Run Mode was: NO");
			  }
	            		
			     //Launch Voot kids App();
			       
					 launchApp();
					 HomePageV2 homepagev2=new HomePageV2(driver,test);
					 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
					 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
					 EbooksPageV2 ebookspagev2= new EbooksPageV2(driver,test);
					 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
					 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
					 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
					 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
					 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
					 BasePageV2  basepagev2=new BasePageV2(driver,test);
					 LaunchPageV2 launchpagev2=new  LaunchPageV2(driver,test);
					 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
					
					  
					   String  lastviewed1="",  lastviewed2="", lastviewed3="", lastviewedfirsttitle1="",audioname="", audioname1=""; 
							  	                				 	               
					 int updatedcount1=0, updatedcount=0, c=0,count=0, updatedcount2=0, updatedcount3=0;
					   
					 String storiescount="", storiescount1="", storiescount2="",audioname2="", audioname3="",storiescount3="";
	                
					    boolean readerDisplayedPreview=false;
	                 
					  
					    
					    //login with Valid credentials
					     homepagev2.login(data.get("Email"),data.get("Password"));
					    
					
					    
//VK_1616----Verify if watched content gets updated in usage stats if user launches and closes the audio player 
				
			             
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
							        launchpagev2.pinContainer.clear();
									launchpagev2.pinContainer.sendKeys("1111");
									test.log(LogStatus.INFO, "Entered PIN");					
						}
						
						else
						{ 
							 test.log(LogStatus.FAIL,"Pin Container not displayed");
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
								
						
				
				//Scroll to stories count			
				 for(int scroll=0;scroll<=4;scroll++)
				 {
						 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 1))
						 {							 								
							    test.log(LogStatus.INFO, " Stories text is displayed");
							    break;
						 }
						 else
						 { 
							 Utilities.verticalSwipe(driver); 							 
						 } 
				 }
			 						  
				 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 10))
				 {
					  storiescount=settingspagev2.usageStoriesCount.getAttribute("text");							  
					  System.out.println(storiescount);
					  test.log(LogStatus.INFO, "fetching  episodescount is"+storiescount);
					  
				 }
				 else 
				 {
					 test.log(LogStatus.FAIL, " failed to fetching  episodescount ");  
					 basepagev2.takeScreenshot();
				 }
   
				 count=Integer.parseInt(storiescount); 
				 
				   //navigated to HomePage  from parent zone page
				 
					 driver.navigate().back();
					 Thread.sleep(2000);
					 driver.navigate().back();
					 Thread.sleep(2000);
					 									 						
                 //searching for listen tab and clicking on listen tab
					 
					 Utilities.verticalSwipeReverse(driver);
					 Utilities.verticalSwipeReverse(driver);
					 
					    homepagev2.tabClick("Listen");	
					    Thread.sleep(4000);
								 
					 //started to play audio card 1
					 
					   if(Utilities.explicitWaitVisible(driver,listenpageV2.audioincarousel, 10))
					   {																											
									audioname=listenpageV2.audionameNameincarousel.getAttribute("text").toString().trim();
									audioname1=audioname.replaceAll("[^a-zA-Z0-9]","");
									System.out.println(audioname1);
									
								    test.log(LogStatus.INFO, "sucessesfully  retrieved audio contetnt name from carousel" +audioname1);
																	    
								     try{
											listenpageV2.audionameNameincarousel.click();														
											test.log(LogStatus.INFO, "clicked on  audio card1  ");
											
										}catch(Exception e)
										
										{
											test.log(LogStatus.FAIL, "failed to click on  audio card1");
											  basepagev2.takeScreenshot();
										}
					 }
					 else
					 {
					 test.log(LogStatus.FAIL, "failed to retrieve audio contetnt name from carousel");
					 basepagev2.takeScreenshot();
					 }
					 
					 //click on play button in audio detail page
					 
					 if(Utilities.explicitWaitVisible(driver,listenpageV2.play_btn, 20))
					 {
						         listenpageV2.play_btn.click();														
						         test.log(LogStatus.INFO, "clicked on  playbutton");												
					 }
					 else
					 {
						        test.log(LogStatus.FAIL, "failed to click to playbutton");	
						        basepagev2.takeScreenshot();
					 }		         
						         
					  //verify audio seek bar is available
					 
					 if(Utilities.explicitWaitVisible(driver,homepagev2.audioseekBar, 10))
					 {							 
						             test.log(LogStatus.INFO, "audio seek bar is available");						 					 
							       try{									            
								            homepagev2.verifyAndProgressBar();															
										    test.log(LogStatus.INFO, "Second audio started  to play");	
											Thread.sleep(2000);																																															
								      }catch(Exception e)
							 	      {
											  test.log(LogStatus.FAIL, "Second audio failed   to play");	 											  
											  basepagev2.takeScreenshot();
							          }		
					 }
					 else
					 {
						     test.log(LogStatus.FAIL, "audio seek bar is not available");
						     basepagev2.takeScreenshot();
					 }
												 
		         //click on pause button in audio player page
				 try{							     
			            homepagev2.verifyAndProgressBar();							      						     
		                kidsplayerv2.playerPlayPauseButton.click();						     		          
                         test.log(LogStatus.INFO, "clicked on pause button Audio play back page ");						          
	               }catch(Exception e)
	               {
		    	        test.log(LogStatus.FAIL, " failed to click on pause button audio play back page");
		    	        basepagev2.takeScreenshot();
	                }
			
			    //closing the audio player					  
				 if(Utilities.explicitWaitClickable(driver, listenpageV2.closebuttoninaudioplayerpage, 10))
				 {
					    listenpageV2.closebuttoninaudioplayerpage.click();					 
					     test.log(LogStatus.INFO, " clicked on close button in audio player page ");
					    Thread.sleep(1000);															 																													
				 }
				 else 
				 {
				     test.log(LogStatus.FAIL, "failed to click on close button in audio player page ");	
				     basepagev2.takeScreenshot();
				 }
				 
		         //navigate back  to home page	 from audio book detail page
		 
		          driver.navigate().back();	
		     
		        //do vertical swipe reverse till to get profile-icon-2
             
                Utilities.verticalSwipeReverse(driver);
                Utilities.verticalSwipeReverse(driver);
                
		      //verify audio card1 updated or not
			 
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
					        launchpagev2.pinContainer.clear();
							launchpagev2.pinContainer.sendKeys("1111");
							test.log(LogStatus.INFO, "Entered PIN");					
				}
				
				else
				{ 
					 test.log(LogStatus.FAIL,"Pin Container not displayed");
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
														
				//Scroll to stories count
				
				 for(int scroll=0;scroll<=4;scroll++)
				 {
						 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 1))
						 {							 								
							    test.log(LogStatus.INFO, " Stories text is displayed");
							    break;
						 }
						 else
						 { 
							 Utilities.verticalSwipe(driver); 							 
						 } 
				 }
			 						  
				 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 10))
				 {
					  storiescount=settingspagev2.usageStoriesCount.getAttribute("text").toLowerCase().trim();							  
					  System.out.println(storiescount);
					  test.log(LogStatus.INFO, "fetching  episodescount is  "+storiescount);
					  
				 }
				 else 
				 {
					 test.log(LogStatus.FAIL, " failed to fetching  episodescount ");  
					 basepagev2.takeScreenshot();
				 }
		
				updatedcount1=Integer.parseInt(storiescount); 
				 
			    if(updatedcount1>=(count+1))
		        {							    						    	
				        test.log(LogStatus.PASS, " Testcase: VK_1616----Verify if watched content gets updated in usage stats if user launches and closes the audio player  "+updatedcount1);								    	
			    	    if(!Utilities.setResultsKids("VK_1616", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
		        }	
	    	    else
		        {
	    	    	    test.log(LogStatus.FAIL, " Testcase: VK_1616----Verify if watched content gets updated in usage stats if user launches and closes the audio player ");					    	
			    	    if(!Utilities.setResultsKids("VK_1616", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
			    	    basepagev2.takeScreenshot();
		        }  
					    
				//navigate to home page   from parent zone page 	    
					    
					 driver.navigate().back();   
					 Thread.sleep(2000);					   
					 driver.navigate().back();   
					 Thread.sleep(2000);	
					 
// VK_1617-----Verify if watched content gets updated in usage stats if user Plays an audio partially 									 
				 
               // searching and clicking on listen tab
		 
				 Utilities.verticalSwipeReverse(driver);
				 Utilities.verticalSwipeReverse(driver);
				 
				 Thread.sleep(2000);									 
				 homepagev2.tabClick("Read");							
				 Thread.sleep(2000);
				 homepagev2.tabClick("Listen");							
				 Thread.sleep(4000);
				 Utilities.horizontalSwipe(driver);
				 Thread.sleep(2000);
				 Utilities.horizontalSwipe(driver);
							
				 //started to play audio card 2
				 
				 if(Utilities.explicitWaitVisible(driver,listenpageV2.audioincarousel, 10))
				{																											
								audioname=listenpageV2.audionameNameincarousel.getAttribute("text").toString().trim();
								audioname2=audioname.replaceAll("[^a-zA-Z0-9]","");
								System.out.println(audioname2);
							    test.log(LogStatus.INFO, "sucessesfully  retrieved audio contetnt name from carousel" +audioname2);
									
							    
							     try{
										listenpageV2.audionameNameincarousel.click();														
										test.log(LogStatus.INFO, "clicked on second audio card  " );
										
									}catch(Exception e)
									
									{
										test.log(LogStatus.FAIL, "failed to click on second audio card");
										  basepagev2.takeScreenshot();
									}
				 }
				 else
				 {
				 test.log(LogStatus.FAIL, "failed to retrieve audio contetnt name from carousel");
				 basepagev2.takeScreenshot();
				 }
				 
				 //click on play button in audio detail page
				 
				 if(Utilities.explicitWaitVisible(driver,listenpageV2.play_btn, 10))
				 {
					         listenpageV2.play_btn.click();														
					         test.log(LogStatus.INFO, "clicked on  playbutton");												
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, "failed to click to playbutton");	
					        basepagev2.takeScreenshot();
				 }		         
					         
				  //verify audio seek bar is available
				 
				 if(Utilities.explicitWaitVisible(driver,homepagev2.audioseekBar, 10))
				 {							 
					           test.log(LogStatus.INFO, "audio seek bar is available");						 					 
						       try{									            
							            homepagev2.verifyAndProgressBar();															
									    test.log(LogStatus.INFO, "Second audio started  to play");	
										
										Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);									
										
										
							      }catch(Exception e)
						 	      {
										  test.log(LogStatus.FAIL, "Second audio failed   to play");	 
										
										  basepagev2.takeScreenshot();
						          }		
				 }
				 else
				 {
					     test.log(LogStatus.FAIL, "audio seek bar is not available");
					     basepagev2.takeScreenshot();
				 }
					
					//click on pause button in audio player page
				 try{							     
			            homepagev2.verifyAndProgressBar();							      						     
		                kidsplayerv2.playerPlayPauseButton.click();						     		          
                         test.log(LogStatus.INFO, "clicked on pause button Audio play back page ");						          
	               }catch(Exception e)
	               {
		    	        test.log(LogStatus.FAIL, " failed to click on pause button audio play back page");
		    	        basepagev2.takeScreenshot();
	                }			
								
								
								
			     //closing the audio player					  
				 if(Utilities.explicitWaitClickable(driver, listenpageV2.closebuttoninaudioplayerpage, 10))
				 {
					 listenpageV2.closebuttoninaudioplayerpage.click();
					 
					     test.log(LogStatus.INFO, " clicked on close button in audio player page ");
					    Thread.sleep(1000);															 																													
				 }
				 else 
				 {
				     test.log(LogStatus.FAIL, "failed to click on close button in audio player page ");	
				     basepagev2.takeScreenshot();
				 }
				  
				  //navigated back  to home page	from audio book detail page
				 
				      driver.navigate().back();											 
				 
				  //verify partially played content updated or not under usage stats
				 
				 
				    //do vertical swipe reverse till to get profile-icon-3
		             
	                Utilities.verticalSwipeReverse(driver);
	                Utilities.verticalSwipeReverse(driver);
	                
			      //verify audio card1 updated or not
				 
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
						        launchpagev2.pinContainer.clear();
								launchpagev2.pinContainer.sendKeys("1111");
								test.log(LogStatus.INFO, "Entered PIN");					
					}
					
					else
					{ 
								 test.log(LogStatus.FAIL,"Pin Container not displayed");
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
							
										
					//Scroll to stories count					
					 for(int scroll=0;scroll<=3;scroll++)
					 {
							 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 1))
							 {							 								
								    test.log(LogStatus.INFO, " Stories text is displayed");
								    break;
							 }
							 else
							 { 
								 Utilities.verticalSwipe(driver); 							 
							 } 
					 }
				 						  
					 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 10))
					 {
						  storiescount=settingspagev2.usageStoriesCount.getAttribute("text").toString().trim();							  
						  System.out.println(storiescount);
						  test.log(LogStatus.INFO, "fetching  episodescount is  "+storiescount);						  
					 }
					 else 
					 {
						 test.log(LogStatus.FAIL, " failed to fetching  episodescount ");  
						 basepagev2.takeScreenshot();
					 }
			
					updatedcount2=Integer.parseInt(storiescount); 
					
				       if(updatedcount2>=(updatedcount1+1))
			           {							    						    	
					        test.log(LogStatus.PASS, " Testcase: VK_1617-----Verify if watched content gets updated in usage stats if user Plays an audio partially "+updatedcount2);								    	
				    	    if(!Utilities.setResultsKids("VK_1617", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
			          }	
		    	      else
			          {
		    	    	    test.log(LogStatus.FAIL, " Testcase:VK_1617-----Verify if watched content gets updated in usage stats if user Plays an audio partially ");					    	
				    	    if(!Utilities.setResultsKids("VK_1617", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
				    	    basepagev2.takeScreenshot();
			          } 
				       
				     //navigate to home page from parent zone page
				       
				       driver.navigate().back();
				       Thread.sleep(2000);
				       driver.navigate().back();
				       Thread.sleep(2000);
				       
				       
//  VK_1618------Verify if watched content gets updated in usage stats  if user Plays an audio Completely
             
		      //searching for listen tab and clicking on listen tab
		 
				 Utilities.verticalSwipeReverse(driver);
				 Utilities.verticalSwipeReverse(driver);
				 
				 Thread.sleep(2000);
				 homepagev2.tabClick("Read");							
				 Thread.sleep(2000);
				 homepagev2.tabClick("Listen");							
				 Thread.sleep(4000);											 
				 Utilities.horizontalSwipe(driver);                                       
												 
				 //audio content   3 started   playing	
				 
				 if(Utilities.explicitWaitVisible(driver,listenpageV2.audioincarousel, 10))
				 {																																		
								   audioname=listenpageV2.audionameNameincarousel.getAttribute("text").toString().trim();													   
								   audioname3=audioname.replaceAll("[^a-zA-Z0-9]","");
								   System.out.println(audioname3);			
								   test.log(LogStatus.INFO, "failed to retrieve audio contetnt name from carousel"+audioname3);
						    try{
									listenpageV2.audioincarousel.click();
									test.log(LogStatus.INFO, "clicked on audiocard  ");
									
						       }catch(Exception e)
						       {
						    	   test.log(LogStatus.FAIL, " failed to click on audiocard3 ");
						    	   basepagev2.takeScreenshot();
						       }
				 }
			     else 
			     {
			    	       test.log(LogStatus.FAIL, "failed to retrieve audio contetnt name from carousel");
			               basepagev2.takeScreenshot();
			     }       
                    
				 //click on play button in audio detail page
				 
				 if(Utilities.explicitWaitVisible(driver,listenpageV2.play_btn, 10))
				 {
					         listenpageV2.play_btn.click();														
					         test.log(LogStatus.INFO, "clicked on  playbutton");												
				 }
				 else
				 {
					        test.log(LogStatus.FAIL, "failed to click to playbutton");	
					        basepagev2.takeScreenshot();
				 }		         
					
				 
				  //verify audio seek bar is available
				 
				 if(Utilities.explicitWaitVisible(driver,homepagev2.audioseekBar, 10))
				 {							 
					              test.log(LogStatus.INFO, "audio seek bar is available");						           
						       try{									            
							            homepagev2.verifyAndProgressBar();															
									    test.log(LogStatus.INFO, "third audio content  started  to play");											
										Utilities.scrubtillend(driver, homepagev2.audioseekBar);									
										
										
							      }catch(Exception e)
						 	      {
										  test.log(LogStatus.FAIL, "Third  audio content  failed   to play");	 
										
										  basepagev2.takeScreenshot();
						          }		
				 }
				 else
				 {
					     test.log(LogStatus.FAIL, "audio seek bar is not available");
					     basepagev2.takeScreenshot();
				 }
								
				     
				   //click on close button in  "listen again" page				  
				 if(Utilities.explicitWaitClickable(driver, listenpageV2.closebuttoninaudioplayerpage, 30))
				 {
					 listenpageV2.closebuttoninaudioplayerpage.click();
					 
					 test.log(LogStatus.INFO, "clicked on close button in listen-again page");		
					    Thread.sleep(1000);															 																													
				 }
				 else 
				 {
					 test.log(LogStatus.FAIL, " failed to click on close button in listen-again page");
				     basepagev2.takeScreenshot();
				 }
				  
			   //closing the audio player					  
				 if(Utilities.explicitWaitClickable(driver, listenpageV2.closebuttoninaudioplayerpage, 10))
				 {
					 listenpageV2.closebuttoninaudioplayerpage.click();
					 
					     test.log(LogStatus.INFO, " clicked on close button in audio player page ");
					    Thread.sleep(1000);															 																													
				 }
				 else 
				 {
				     test.log(LogStatus.FAIL, "failed to click on close button in audio player page ");	
				     basepagev2.takeScreenshot();
				 }
				 	

				    //navigated back  to home page	 from audio detail page
				 
				     driver.navigate().back();											 
				 			 
				    //do vertical swipe reverse till to get profile-icon-4
		             
	                Utilities.verticalSwipeReverse(driver);
	                Utilities.verticalSwipeReverse(driver);
	                
			      //verify audio card3 updated or not
				 
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
						        launchpagev2.pinContainer.clear();
								launchpagev2.pinContainer.sendKeys("1111");
								test.log(LogStatus.INFO, "Entered PIN");					
					}					
					else
					{ 
								 test.log(LogStatus.FAIL,"Pin Container not displayed");
								 basepagev2.takeScreenshot();
					}
					
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
																	
					//Scroll to stories count
					
					 for(int scroll=0;scroll<=3;scroll++)
					 {
							 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 1))
							 {							 								
								    test.log(LogStatus.INFO, " Stories text is displayed");
								    break;
							 }
							 else
							 { 
								 Utilities.verticalSwipe(driver); 							 
							 } 
					 }
				 						  
					 if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 10))
					 {
						  storiescount=settingspagev2.usageStoriesCount.getAttribute("text").toString().trim();							  
						  System.out.println(storiescount);
						  test.log(LogStatus.INFO, "fetching  episodescount is  "+storiescount);
						  
					 }
					 else 
					 {
						 test.log(LogStatus.FAIL, " failed to fetching  episodescount ");  
						 basepagev2.takeScreenshot();
					 }
			
					   updatedcount3=Integer.parseInt(storiescount); 
					   
				       if(updatedcount3==(updatedcount2+1))
			           {							    						    	
					        test.log(LogStatus.PASS, " Testcase: VK_1618------Verify if watched content gets updated in usage stats  if user Plays an audio Completely "+updatedcount3);								    	
				    	    if(!Utilities.setResultsKids("VK_1618", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
			           }	
		    	       else
			           {
		    	    	    test.log(LogStatus.FAIL, " Testcase:VK_1618------Verify if watched content gets updated in usage stats  if user Plays an audio Completely ");					    	
				    	    if(!Utilities.setResultsKids("VK_1618", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					    		
				    	    basepagev2.takeScreenshot();
			           } 
				 									 
       }

				@DataProvider
				public Object[][] getData()
				{
				 return DataUtil.getData(testName,xls);											          					
			    }    	
}
