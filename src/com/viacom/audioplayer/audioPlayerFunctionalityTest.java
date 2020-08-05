package com.viacom.audioplayer;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;
//author=bhaskar
public class audioPlayerFunctionalityTest extends BaseTestV2 
{
	String testName = "audioPlayerFunctionalityTest";
	
	//VK_536---Verify the navigation of Audio player when tapped on Play button of any Audio book card from programme info page:---
	//VK_537---'Verify UI of audio player'";-------
	//VK_538---Verify UI of audio player controls after tapping on the player screen'";---	        
	//VK_546----Verify Favourite icon functionality:
	//VK_548---Verify the functionality of close button in audio player'";
	//VK_549---Verify the functionality of device back button in audio player'";----
	//VK_560----To verify the UI of inline audio player'";-------
	//VK_540----Verify the Pause functionality for audio player";-------
	//VK_541---Verify the Play functionality for audio player";------------------
	//VK_559----To verify the functionality of drop down icon in audio player";-----
	//VK_563---Verify the functionality when tapping on inline audio player area anywhere other than Play/Pause buttons";---	      
	//VK_239--Verify 'Unfavorite' button functionality:";----
	
	String DurationAfterpausingsometime = "";	
	String audiobook = "";

	//Author: Karthik
	@Test(dataProvider = "getData")
	public void audioPlayerFunctionality(Hashtable<String, String> data) throws Exception 
	{

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		//launching the App
		
		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
		 KidsPlayerPageV2 kidspagev2=new KidsPlayerPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 
		    //login with Valid credentials
	         homepagev2.login(data.get("Email"),data.get("Password"));
		 
		/*if(driver.isDeviceLocked())
		{
			driver.unlockDevice();
		}			
		else
		{
			test.log(LogStatus.FAIL, "failed to unlock device");
			BasePageV2.takeScreenshot();
		}
		
		System.out.println("Device already unlocked");

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);*/

		String audioname1="";
		
//VK_536Verify the navigation of Audio player when tapped on Play button of any Audio book card from Programme info page:		
		
		
		/*
		//click on listen tab in home page
		HomePageV2.tabClick("Listen");
      
		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		
		for (int rNum = 1; rNum <= rows; rNum++) 
		{
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("AudioBookCards"))
			{
				String ApiTab = xls.getCellData("Api", "Url", rNum);
				Response respTab = Utilities.requestUtility(ApiTab);
				audiobook = respTab.jsonPath().get("assets.items[0].title");
				// Audiobooks.add(abooks);
			}
			else
			{
				test.log(LogStatus.FAIL, "Unable to fetch tab from API");
				BasePageV2.takeScreenshot();
				System.out.println("Unable to fetch tab from API");
			}
		}

		try {
			    String xpath = "//android.widget.TextView[contains(@text,'" + audiobook + "')]";
			    Utilities.verticalSwipe(driver, xpath);
			    WebElement ab = driver.findElement(By.xpath(xpath));
				if (Utilities.explicitWaitVisible(driver, ab, 10))
				{
					ab.click();
					
				}
				else
				{					
					test.log(LogStatus.FAIL, "Audio Book is not displayed");
					BasePageV2.takeScreenshot();
				}
				
		   } catch (Exception e)
		   {
		   }*/

				    homepagev2.tabClick("Listen");							
				   Thread.sleep(4000);
				   Utilities.horizontalSwipe(driver);
				   Thread.sleep(2000);
				 							                                       
			       //Audio card is started  to play				 
				  if(Utilities.explicitWaitVisible(driver,listenpageV2.audioincarousel, 10))
			      {		
								  test.log(LogStatus.INFO, "audio card is available in carousel");
								try 
								 {										
									audioname1=listenpageV2.audionameNameincarousel.getAttribute("text");							
									audioname1=audioname1.replaceAll("[^a-zA-Z0-9]","");
									audioname1=audioname1.toLowerCase();
									System.out.println(audioname1);
									listenpageV2.audionameNameincarousel.click();
									test.log(LogStatus.INFO, "clicked on  audio card is  "+audioname1);
								 }catch(Exception e)
								 {
									 test.log(LogStatus.FAIL, " failed to click on audio card");
									BasePageV2.takeScreenshot();
								 }											
			 	 }
			     else
			     {
			                 test.log(LogStatus.FAIL, "audio card is not availble in carousel");
			                 BasePageV2.takeScreenshot();
			     }
		
			    //click on play button in audio book detail page		
				if (Utilities.explicitWaitVisible(driver, listenpageV2.play_btn, 25))
				{
					     test.log(LogStatus.INFO," Play button is  available  in audio detail page");					
						 try{
							  listenpageV2.play_btn.click();
							  homepagev2.verifyAndProgressBar();
							  test.log(LogStatus.INFO," clicked on Play button in audio detail page");
						    }catch (Exception e)
					        {
						    	test.log(LogStatus.FAIL,"failed to click on Play button in audio detail page");
								BasePageV2.takeScreenshot();
						    }
				}
				else
				{
					test.log(LogStatus.FAIL," Play button is not available  in audio detail page");
					BasePageV2.takeScreenshot();
				}
				
				//click on pause button in audio player
				if (Utilities.explicitWaitVisible(driver, kidsplayerv2.playerPlayPauseButton, 10)) 
				{						
						    test.log(LogStatus.INFO,"Title of currently playing audio book is: " + homepagev2.audioTitle.getText().toString());
						 
						     //click on pause button in audio player page
							 try{							     
						            homepagev2.verifyAndProgressBar();							      						     
					                kidsplayerv2.playerPlayPauseButton.click();						     		          
			                         test.log(LogStatus.INFO, "clicked on pause button Audio play back page ");						          
				               }catch(Exception e)
				               {
					    	        test.log(LogStatus.FAIL, " failed to click on pause button audio play back page");
					    	        BasePageV2.takeScreenshot();
				                }
				} 
				else
				{
						test.log(LogStatus.FAIL,"audio play pause button is not available");
						BasePageV2.takeScreenshot();
				}
				
//VK_536-----Verify the navigation of Audio player when tapped on Play button of any Audio book card from programme info page:
			
				test.log(LogStatus.INFO, "TestCase:VK_536---Verify the navigation of Audio player when tapped on Play button of any Audio book card from programme info page:");
				
				//verifying the Tapping on audio book navigated to audio player or not
				if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10)) 
				{
					test.log(LogStatus.INFO, "Tapping on audiobook navigated to audio player");
					test.log(LogStatus.PASS, " 'TestCase:VK_536---Verify the navigation of Audio player when tapped on Play button of any Audio book card from programme info page:' is passed");
					// driver.pressKeyCode(AndroidKeyCode.BACK);
					if(!Utilities.setResultsKids("VK_536", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 					         									
				}
				else 
				{
					test.log(LogStatus.FAIL, "Tapping on audiobook navigated is not to audio player");
					test.log(LogStatus.FAIL, "TestCase:VK_536---Verify the navigation of Audio player when tapped on Play button of any Audio book card from programme info page: is Failed");
					if(!Utilities.setResultsKids("VK_536", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");  
					BasePageV2.takeScreenshot();					
				}
			
//VK_537------'Verify UI of audio player
//VK_538-------'Verify UI of audio player controls after tapping on the player screen'	
				
				
				test.log(LogStatus.INFO, "TestCase:VK_537---'Verify UI of audio player'");
				test.log(LogStatus.INFO, "TestCase:VK_538---'Verify UI of audio player controls after tapping on the player screen'");				

				
		         //verify audio play pause button available or not in audio player
				if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 10))
				{
					   test.log(LogStatus.INFO, "Audio 'Play/Pause toggle' button is displayed");
				}
				else
				{
					   test.log(LogStatus.FAIL,"Audio 'Play/Pause toggle' button is not displayed");
					   BasePageV2.takeScreenshot();
				}

			    //verify Audio back bard button in audio player 
				if(Utilities.explicitWaitVisible(driver, homepagev2.audiobackward, 10))			
				{
					   test.log(LogStatus.INFO, "Audio 'Rewind' button is displayed");
				}
				else
				{
					   test.log(LogStatus.FAIL,"Audio 'Rewind' button is not displayed");
					   BasePageV2.takeScreenshot();
				}
	
			    //verify Audio forward button in audio player
			    if(Utilities.explicitWaitVisible(driver, homepagev2.audioForward, 10))
				{
				   test.log(LogStatus.INFO, "Audio 'Forward' button is displayed");
				}
			   else
				{
				   test.log(LogStatus.FAIL,"Audio 'Forward' button is not displayed");
				   BasePageV2.takeScreenshot();
				}

			   //verify audio favorite-icon in audio player
			    if(Utilities.explicitWaitVisible(driver, homepagev2.audiofavouriteIcon, 10))
				{
				      test.log(LogStatus.INFO, "Audio 'Favourite icon' button is displayed");
				}
			    else
				{
			    	 test.log(LogStatus.FAIL,"Audio 'Favourite icon' button is not displayed");
			    	 BasePageV2.takeScreenshot();
				}

			   //Verify Audio SeekBar Available in Audio player
			   if(Utilities.explicitWaitVisible(driver, homepagev2.audioseekBar, 10))
				{
				   test.log(LogStatus.INFO, "Audio 'Seek bar' button is displayed");
				}
			   else
				{
				   test.log(LogStatus.FAIL,"Audio 'Seek bar' button is not displayed");
				   BasePageV2.takeScreenshot();
				}

			   //Verify AudioPlayer CloseButton available in AudioPlayer
			    if(Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 10))
				{
				    test.log(LogStatus.INFO, "Audio 'Player close' button is displayed");
				}
			    else
				{
			    	 test.log(LogStatus.FAIL,"Audio 'Player close' button is not displayed");
			    	 BasePageV2.takeScreenshot();
				}

			   //Verify AudioTitle in AudioPlayer
			    if(Utilities.explicitWaitVisible(driver, homepagev2.audioTitle, 10))
				{
				   test.log(LogStatus.INFO, "Audio 'Title' is displayed: " + homepagev2.audioTitle.getText().toString());
				}
			    else
				{
			    	 test.log(LogStatus.FAIL,"Audio 'Title' is not displayed");
			    	 BasePageV2.takeScreenshot();
				}
		  
			    //Verify AudioPlayListExpander in Audio Player
			    if(Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10))
				{
				     test.log(LogStatus.INFO, "Audio 'Play list expander' button is displayed");
				}
			   else
				{
				     test.log(LogStatus.FAIL,"Audio 'Play list expander' button is not displayed");
				      BasePageV2.takeScreenshot();
				}

 //VK_540-------"Verify the Pause functionality for audio player";	
		    
		    test.log(LogStatus.INFO, "TestCase:VK_540-----Verify the Pause functionality for audio player");
		    
		    
		    //checking pause functionality
			if(Utilities.explicitWaitVisible(driver, homepagev2.audiorunningDuration, 10)) 
			{
						test.log(LogStatus.INFO, "Audio 'Running duration' is displayed");
						String initialDuration = homepagev2.audiorunningDuration.getText();
						Thread.sleep(3000);
						DurationAfterpausingsometime = homepagev2.audiorunningDuration.getText();
			
						test.log(LogStatus.INFO, "Initial pause duration is: " + initialDuration);
						test.log(LogStatus.INFO, "Duration After Pausing sometime is:      " + DurationAfterpausingsometime);
					
						//comparing IntialDuratio and Duration AfterPlaySomeTime						
						if (initialDuration.equals(DurationAfterpausingsometime))
						{
							test.log(LogStatus.PASS, "TestCase:VK_540----Verify the Pause functionality for audio player  is Passed");
							if(!Utilities.setResultsKids("VK_540", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 						
						}
						else
						{
							test.log(LogStatus.FAIL, "TestCase:VK_540---Verify the Pause functionality for audio player is Failed");
							if(!Utilities.setResultsKids("VK_540", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							BasePageV2.takeScreenshot();
						}
			}
			else
			{
				test.log(LogStatus.FAIL,"Audio 'Running duration' is not displayed");
				BasePageV2.takeScreenshot();
			}
			
			
//VK_541-----"Verify the Play functionality for audio player";
			
			test.log(LogStatus.INFO, "TestCase:VK_541----Verify the Play functionality for audio player");
			 			 
			//play the audioContent
			if(Utilities.explicitWaitVisible(driver, kidsplayerv2.playerPlayPauseButton, 10)) 
			{			
				        test.log(LogStatus.INFO,"audio play pause button is  available");						
				     
				        //click on pause button in audio player page
						 try{							     
					            homepagev2.verifyAndProgressBar();							      						     
				                kidsplayerv2.playerPlayPauseButton.click();						     		          
		                         test.log(LogStatus.INFO, "clicked on pause button Audio play back page ");						          
			               }catch(Exception e)
			               {
				    	        test.log(LogStatus.FAIL, " failed to click on pause button audio play back page");
				    	        BasePageV2.takeScreenshot();
			                }				  				   
			} 
			else
			{
				  test.log(LogStatus.FAIL,"audio play pass button is not available");
				  BasePageV2.takeScreenshot();
			}
			
			
			//pause the audioContent
			if(Utilities.explicitWaitVisible(driver, kidsplayerv2.playerPlayPauseButton, 10)) 
			{			
				        test.log(LogStatus.INFO,"audio play pause button is  available");						
				     
				        //click on pause button in audio player page
						 try{							     
					            homepagev2.verifyAndProgressBar();							      						     
				                kidsplayerv2.playerPlayPauseButton.click();						     		          
		                         test.log(LogStatus.INFO, "clicked on pause button Audio play back page ");						          
			               }catch(Exception e)
			               {
				    	        test.log(LogStatus.FAIL, " failed to click on pause button audio play back page");
				    	        BasePageV2.takeScreenshot();
			                }				  				   
			} 
			else
			{
				       test.log(LogStatus.FAIL,"audio play pass button is not available");
				       BasePageV2.takeScreenshot();
			}
			
		     //checking the play functionality				
			if (Utilities.explicitWaitVisible(driver, homepagev2.audiorunningDuration, 10))
			{
						String DurationafterPlayforSomeTime = homepagev2.audiorunningDuration.getText();		
						test.log(LogStatus.INFO, "Duration Before Playing is: " + DurationAfterpausingsometime);
						test.log(LogStatus.INFO, "Duration after playing for some time is: " + DurationafterPlayforSomeTime);
						
						//comparing initialDuration and AfterPlayforSomeTime in Audio player
						if(!DurationafterPlayforSomeTime.equals(DurationAfterpausingsometime))
						{
							test.log(LogStatus.PASS, "TestCase:VK_541----Verify the Play functionality for audio player' is Passed");
							if(!Utilities.setResultsKids("VK_541", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
						} 
						else
						{
							test.log(LogStatus.FAIL, "TestCase:VK_541----Verify the Play functionality for audio player' is Failed");
							if(!Utilities.setResultsKids("VK_541", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							BasePageV2.takeScreenshot();
						}
			}
			else
			{
				       test.log(LogStatus.FAIL,"Unable to Capture Timer");
				       BasePageV2.takeScreenshot();
			}
			
		    //Verifying Audio Total duration is Displayed or not in audio player
		    if(Utilities.explicitWaitVisible(driver, homepagev2.audiototalDuration, 10))
			{
			      test.log(LogStatus.INFO, "Audio 'Total duration' is displayed: " + homepagev2.audiototalDuration.getText());
			}
		    else
			{
		    	  test.log(LogStatus.FAIL,"Audio 'Total duration' is not displayed");
		    	  BasePageV2.takeScreenshot();
			}
			
		    
//VK_537---'Verify UI of audio player
//VK_538-------'Verify UI of audio player controls after tapping on the player screen'
		    		    
			if (Utilities.explicitWaitVisible(driver, homepagev2.audiominiplayerSwitch, 10)) 
			{
					test.log(LogStatus.INFO, "Audio 'mini player switch' button is displayed");
					test.log(LogStatus.INFO, "Tapping on audio book navigated to Full screen audio player");
					test.log(LogStatus.PASS, "Test case:'VK_537---'Verify UI of audio player' is passed");
					test.log(LogStatus.PASS, "Test Case: VK_538----'Verify UI of audio player controls after tapping on the player screen' is passed");				
					if(!Utilities.setResultsKids("VK_537", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					if(!Utilities.setResultsKids("VK_538", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
			} 
			else
			{
				    test.log(LogStatus.FAIL, "Audio 'mini player switch' button is not displayed");
				    test.log(LogStatus.FAIL,"Tapping on audio book is not navigated to Full screen audio player");
				    test.log(LogStatus.FAIL, "Test case:'VK_537---'Verify UI of audio player' is failed");
					test.log(LogStatus.FAIL, "Test Case: VK_538----'Verify UI of audio player controls after tapping on the player screen' is failed");								
					if(!Utilities.setResultsKids("VK_537", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					if(!Utilities.setResultsKids("VK_538", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
				    BasePageV2.takeScreenshot();
			}

//VK_559----To verify the functionality of drop down icon in audio player";
//VK_563----Verify the functionality when tapping on inline audio player area anywhere other than Play/Pause buttons";		

		test.log(LogStatus.INFO, "TestCase:VK_559---To verify the functionality of drop down icon in audio player");
		test.log(LogStatus.INFO, "TestCase:VK_563--Verify the functionality when tapping on inline audio player area anywhere other than Play/Pause buttons");
		
		
		if (Utilities.explicitWaitVisible(driver, homepagev2.audiominiplayerSwitch, 10)) 
		{
						test.log(LogStatus.INFO, "Audio player displayed in full screen");
						
						//click on minimize button  audio player
						try 
						{
							homepagev2.audiominiplayerSwitch.click();
							test.log(LogStatus.INFO, "clicked on minimize button in audio player");
							
						} catch (Exception e)
						{
							  test.log(LogStatus.FAIL, "failed to click on minimize button in audio player");
							  BasePageV2.takeScreenshot();
						}
			
						//Verify  TheMiniPlayer and playButton in AudioDetailPage Displayed or not
						if(Utilities.explicitWaitVisible(driver, homepagev2.miniplayer, 10) && Utilities.explicitWaitVisible(driver, listenpageV2.play_btn, 10))					
						{
							test.log(LogStatus.INFO, "After Clicking on 'Audio Player Drop Down' switched to mini player");						
							test.log(LogStatus.PASS, "VK_559----To verify the functionality of drop down icon in audio player is Passed");
							if(!Utilities.setResultsKids("VK_559", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 				 
							/*Thread.sleep(4000);
							homepagev2.miniplayer.click();*/
						} 
						else 
						{
							test.log(LogStatus.INFO, "After Clicking on 'Audio Player Drop Down' unable to switch to mini player");						
							test.log(LogStatus.FAIL, "Test Case:VK_559----To verify the functionality of drop down icon in audio player is Failed");
							if(!Utilities.setResultsKids("VK_559", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
						    BasePageV2.takeScreenshot();
						}
						
					     //verify miniplayerThumbnail 
					    if(Utilities.explicitWaitVisible(driver, homepagev2.miniplayerThumbnail, 10))
						{				    	
					    	 test.log(LogStatus.INFO,"Mini Player Thumbnail is displayed");
					    	try {
					    		
					    		 homepagev2.miniplayerThumbnail.click();
					    		 test.log(LogStatus.INFO,"clicked on Mini Player Thumbnail ");
					    		 
					    	    }catch(Exception e) 
					    	    {
					    	    	 test.log(LogStatus.FAIL," failed to click on Mini Player Thumbnail ");
					    	    	 BasePageV2.takeScreenshot();
					    	    }					    					     					       
						}
					   else
						{
						    test.log(LogStatus.FAIL,"Mini Player Thumbnail is not displayed");
						    BasePageV2.takeScreenshot();
						}
					
					     //Verify The Availability of MiniplayerSwitch
						if (Utilities.explicitWaitVisible(driver, homepagev2.audiominiplayerSwitch, 10))
						{
							test.log(LogStatus.INFO, "Clicking on mini player navigated to Full Screen player");
							test.log(LogStatus.PASS, "Test Case:VK_563----Verify the functionality when tapping on inline audio player area anywhere other than Play/Pause buttons'is Passed");
							if(!Utilities.setResultsKids("VK_563", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 				
								
								//click on dropDownbutton in audio player
								try{					    		
							         homepagev2.audiominiplayerSwitch.click();
							         test.log(LogStatus.INFO,"clicked on Mini Player Switch in audio player ");					    		 
						           }catch(Exception e) 
						           {
						    	     test.log(LogStatus.FAIL," failed to click on Mini Player switch in AudioPlayer ");
						    	     BasePageV2.takeScreenshot();
						           }
						} 
						else 
						{
							test.log(LogStatus.FAIL, "Clicking on mini player unable to navigate to Full Screen player");
							test.log(LogStatus.FAIL, "Test Case: VK_563----Verify the functionality when tapping on inline audio player area anywhere other than Play/Pause buttons' is Failed");
							if(!Utilities.setResultsKids("VK_563", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
						    BasePageV2.takeScreenshot();
						}
					   
		} 
		else
		{
			 test.log(LogStatus.FAIL," 'Mini player' switch button is not displayed");
			 BasePageV2.takeScreenshot();
		}
		
			
//VK_549---Verify the functionality of device back button in audio player			
										
				    test.log(LogStatus.INFO,"TestCase:VK_549--'Verify the functionality of device back button in audio player'");  
				   /* 		     
				      //navigate back to audio detail page
				       Thread.sleep(3000);	
				       driver.navigate().back();
				       Thread.sleep(3000);	*/
					    					
				    //verify the  play button in book detail page
					if(Utilities.explicitWaitVisible(driver, listenpageV2.play_btn, 10)) 
					{				
							   test.log(LogStatus.INFO,"play button is available in audio detail page");
							   test.log(LogStatus.PASS, "Test Case:VK_549----'Verify the functionality of device back button in audio player' is passed");
							   if(!Utilities.setResultsKids("VK_549", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 				    
							
					} 
					else
					{
						       test.log(LogStatus.FAIL,"Play button is not displayed in audio detail page");
						       test.log(LogStatus.FAIL, "Test Case:VK_549----'Verify the functionality of device back button in audio player' is failed");
							   if(!Utilities.setResultsKids("VK_549", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							   BasePageV2.takeScreenshot();
					}
					
		
//VK_560-----To verify the UI of inLine audio player
					
					 test.log(LogStatus.INFO,"TestCase:VK_560-----To verify the UI of inLine audio player");
					 
					//verify inLine player book title 
				    if(Utilities.explicitWaitVisible(driver, homepagev2.inlinePlayerBookTitle, 25))			
					{
					    test.log(LogStatus.INFO,"Book title displayed on inline player: " + homepagev2.inlinePlayerBookTitle.getText().toString());
					}
				    else
					{
					   test.log(LogStatus.FAIL,"Book Title not displayed on inline player");
					   BasePageV2.takeScreenshot();
					}
			
				   //verify inLine player playPausebutton
				    if(Utilities.explicitWaitVisible(driver, homepagev2.inlinePlayerPlaypause, 10))
					{
					    test.log(LogStatus.INFO, "'Play/pause toggle' displayed on inline player");
					}
			   	    else
					{
			   		   test.log(LogStatus.FAIL,"'Play/pause toggle' not displayed on inline player");
			   		   BasePageV2.takeScreenshot();
					}
			
				    //verify inLineplayer seekBar
				    if(Utilities.explicitWaitVisible(driver, homepagev2.inlinePlayerSeekbar, 10))
					{
					      test.log(LogStatus.INFO, "'Inline player Seek bar' displayed on inline player");
					      test.log(LogStatus.PASS, "TestCase:VK_560-----To verify the UI of inLine audio player' is passed");
					      if(!Utilities.setResultsKids("VK_560", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 				 
					}
				    else
					{
					      test.log(LogStatus.FAIL,"'Inline player Seek bar' not displayed on inline player");
					      test.log(LogStatus.FAIL, "TestCase:VK_560-----To verify the UI of inLine audio player' is passed");
					      if(!Utilities.setResultsKids("VK_560", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
						    BasePageV2.takeScreenshot();			    
					}	
					
//VK_548----'Verify the functionality of close button in audio player'";	
				    				    
				    test.log(LogStatus.INFO," TestCase:VK_548----'Verify the functionality of close button in audio player'"); 
				    
				    //click on  miniplayerThumbnail 
				    if(Utilities.explicitWaitVisible(driver, homepagev2.miniplayerThumbnail, 10))
					{				    	
					    	   test.log(LogStatus.INFO,"Mini Player Thumbnail is displayed");
					    	try {				    		
					    		   homepagev2.miniplayerThumbnail.click();
					    		   test.log(LogStatus.INFO,"clicked on Mini Player Thumbnail ");				    		 
					    	    }catch(Exception e) 
					    	    {
					    	    	 test.log(LogStatus.FAIL," failed to click on Mini Player Thumbnail ");
					    	    	 BasePageV2.takeScreenshot();
					    	    }					    					     					       
					}
				   else
					{
					    test.log(LogStatus.FAIL,"Mini Player Thumbnail is not displayed");
					    BasePageV2.takeScreenshot();
					}
									    
					//click on close button  in audio player
					if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 10)) 
					{
						     test.log(LogStatus.INFO," Audio player close  button is  displayed");  					 
						 try{				    		
							      homepagev2.audioplayerClose.click();
				    		      test.log(LogStatus.INFO,"clicked on close button in audio player ");				    		 
				    	    }catch(Exception e) 
				    	    {
				    	    	 test.log(LogStatus.FAIL," failed to click on close button in audio player ");
				    	    	 BasePageV2.takeScreenshot();
				    	    }													 						 
					} 
					else
					{
						 test.log(LogStatus.FAIL," Audio player close  button is not displayed");
						 BasePageV2.takeScreenshot();
					}	
		    		   					
					//verify the  play button in book detail page
					if(Utilities.explicitWaitVisible(driver, listenpageV2.play_btn, 10)) 
					{				
							   test.log(LogStatus.INFO,"play button is available in audio detail page");
							   test.log(LogStatus.PASS, "TestCase:VK_548----'Verify the functionality of close button in audio player' is passed");
							   if(!Utilities.setResultsKids("VK_548", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 				    
							
					} 
					else
					{
						       test.log(LogStatus.FAIL,"Play button is not displayed in audio detail page");
						       test.log(LogStatus.FAIL, "TestCase:VK_548----'Verify the functionality of close button in audio player' is failed");
							   if(!Utilities.setResultsKids("VK_548", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							   BasePageV2.takeScreenshot();
					}
										
//VK_239-------"Verify Unfavorite icon functionality";

          /*  test.log(LogStatus.INFO,"TestCase:VK_239---Verify Unfavorite icon functionality");
                                   
		    if(Utilities.explicitWaitVisible(driver, listenpageV2.play_btn, 1))
			{
			      listenpageV2.play_btn.click();
			}
		    else
			{
			     BasePageV2.reportFail("Play button is not displayed in audio detail page");
			}

		if (Utilities.explicitWaitVisible(driver, homepagev2.notfavourite, 1)) 
		{
				test.log(LogStatus.INFO, "Not Favourited Adding to favourite and then unfavouriting");
				try
				{
					homepagev2.notfavourite.click();
				} catch (Exception e) 
				{
					homepagev2.notfavourite.click();
				}

				if (Utilities.explicitWaitVisible(driver, homepagev2.favourite, 1))
				{
	
						test.log(LogStatus.INFO, "Favourite Icon got highlighted after adding to favourite");
						// BasePageV2.takeScreenshot();
						try 
						{
							homepagev2.favourite.click();
						} catch (Exception e) 
						{
							homepagev2.favourite.click();
						}
					
				}else 
				{
					test.log(LogStatus.INFO, "Not Able to 'Unfavourite Audio'");
					// BasePageV2.takeScreenshot();
	
				}

				if (Utilities.explicitWaitVisible(driver, homepagev2.notfavourite, 1)) 
				{
					test.log(LogStatus.INFO, "Audio Unfavourited Successfully");
					test.log(LogStatus.PASS, "Test Case: " + testCase11 + " is Pass");
					homepagev2.smokeresults(testCase11, rowno11, "Pass");
				} else 
				{
					test.log(LogStatus.INFO, "Unable to Un Favourite Audio");
					test.log(LogStatus.FAIL, "Test Case: " + testCase11 + " is Fail");
					homepagev2.smokeresults(testCase11, rowno11, "Fail");
				}
		} 
		else if (Utilities.explicitWaitVisible(driver, homepagev2.favourite, 1)) 
		{
			test.log(LogStatus.INFO, "Already Favourited so Unfavouriting");
			try {
				homepagev2.favourite.click();
			   } catch (Exception e) 
			   {
				homepagev2.favourite.click();
			   }
				if (Utilities.explicitWaitVisible(driver, homepagev2.notfavourite, 1)) 
				{
					test.log(LogStatus.INFO, "Audio Unfavourited Successfully");
					test.log(LogStatus.PASS, "Test Case: " + testCase11 + " is Pass");
					homepagev2.smokeresults(testCase11, rowno11, "Pass");
				}else 
				{
					test.log(LogStatus.INFO, "Unable to Un Favourite Audio");
					test.log(LogStatus.FAIL, "Test Case: " + testCase11 + " is Fail");
					homepagev2.smokeresults(testCase11, rowno11, "Fail");
				}
		}
	    else
		{
		BasePageV2.reportFail("Favourite icon is non functional");
		}

		
	   if(Utilities.explicitWaitVisible(driver, homepagev2.notfavourite, 2))
		{
		      homepagev2.notfavourite.click();
		}
	    else
		{
		     test.log(LogStatus.FAIL, "Unable to Add to Favourites");
		}
	   
		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayerClose, 10))
		{
			test.log(LogStatus.INFO, "Clicking on close button");
			// BasePageV2.takeScreenshot();
			homepagev2.audioplayerClose.click();
		} 
		else
		{
		     BasePageV2.reportFail("Audio 'Player close' button is not displayed");
		}

		
		if (Utilities.explicitWaitVisible(driver, listenpageV2.play_btn, 1)) 
		{			
			test.log(LogStatus.INFO,"Navigated to page from where audio was played while clicking on Audio player close button");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase4 + " is passed");
			HomePageV2.smokeresults(testCase4, rowno4, "Pass");
		}
		else
		{

			
		}
*/
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
