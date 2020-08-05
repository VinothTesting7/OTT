package com.viacom.parentzone;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
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
import com.viacom.utils.Xls_Reader;

  //Author= Bhaskar

public class VerifyTCsAfterSignUp extends BaseTestV2

{
	         //VK_1611---Verify the Episode card usage data if user has not watched any content (or New user)
	         //VK_1615----Verify the Ebook card usage data if user has not Opened/Read any Ebook (or New user) 
	         //VK_1619----Verify the Listen card usage data if user has not Played any audio book (or New user) 
             //VK_1622---Verify the Learn card usage data if user has not attempted any questions (or New user) 
	
	String testName = "VerifyTCsAfterSignUp";
	@Test(dataProvider = "getData")
	public void VerifyTCsAfterSignUp2(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyTCsAfterSignUp");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
				
	     //Launch Voot kids app
	
		
		 launchApp();
 		 
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
		 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
		 
		  //Login with valid credentials 		 
		   homepagev2.login(data.get("Email"),data.get("Password"));
		 
		 String usageStatsEpisodeCount;
		 String usageStatsPagesCount;
		 String usageStatsStoriesCount;
		 String usageStatsQuestionsCount;
		 int err1612=0;
		 int err1616=0;
		 int err1620=0;
		 int err1623=0;
		 
/*//VK_1914 
		 
		     if (Utilities.explicitWaitVisible(driver, launchpagev2.CreateProfile, 5)) 
			{
		    	 						
							if (Utilities.explicitWaitVisible(driver, launchpagev2.kidsName, 25))
							{
								launchpagev2.kidsName.clear();
								launchpagev2.kidsName.sendKeys(Utilities.generateRandomName());
							}else
							{
							     test.log(LogStatus.FAIL, "Kids name text field not displayed");
							}
			
							if (Utilities.explicitWaitVisible(driver, launchpagev2.DOB, 5))
								launchpagev2.DOB.click();
							else
								HomePageV2.reportFail("DOB Field not displayed");
							BasePageV2.takeScreenshot();
			
							if (Utilities.explicitWaitVisible(driver, launchpagev2.DOBSelect, 5))
								launchpagev2.DOBSelect.click();
							else {
								HomePageV2.reportFail("Date selector not displayed");
								BasePageV2.takeScreenshot();
							}
			
							try {
								driver.hideKeyboard();
							} catch (Exception e) {
							}
							if (Utilities.explicitWaitVisible(driver, launchpagev2.next, 1))
								launchpagev2.next.click();
							else
								BasePageV2.reportFail("Next Button not displayed");
			
							if (Utilities.explicitWaitVisible(driver, launchpagev2.createBuddyPage, 1))
								test.log(LogStatus.INFO, "Select Profile image page is displayed");
							else
								BasePageV2.reportFail("Select Profile image page is not displayed");
			
							if (Utilities.explicitWaitVisible(driver, launchpagev2.next, 1))
								launchpagev2.next.click();
							else
								BasePageV2.reportFail("Next Button not displayed");
			
						for (int i = 1; i <= 5; i++) 
						{
							 try{
			
											WebElement element = driver.findElement(By
													.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
															+ i + "]"));
											if (i == 5) 
											{
												Utilities.verticalSwipe(driver);
												Utilities.verticalSwipe(driver);
												driver.findElement(By
														.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
														.click();
											}else if (Utilities.explicitWaitVisible(driver, element, 10)) 
											{
												element.click();
											}else 
											{
												HomePageV2.reportFail("Kids Characters not displayed");
												BasePageV2.takeScreenshot();
											}
				                   
								   }catch (Exception e) 
								   {
									  // test.log(logStatus, details);
								   }
				                              
						   }
							
						try{
								WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
								if (Utilities.explicitWaitVisible(driver, Skills1, 5))
									Skills1.click();
								else
									test.log(LogStatus.INFO, "Skills not displayed");
							 }catch (Exception e) {}
			
							
			
							if (Utilities.explicitWaitVisible(driver, launchpagev2.next, 1))
								launchpagev2.next.click();
							else
								BasePageV2.reportFail("Next Button not displayed");
			
							if (Utilities.explicitWaitVisible(driver, launchpagev2.letsGo, 60))
								launchpagev2.letsGo.click();
							else
								BasePageV2.reportFail("Let's Go Button not displayed");
			
							for (int i = 1; i <= 5; i++) {
								if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
									homepagev2.freshAppNotificationCancel.click();
								else
									break;
							}
			
							if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
								test.log(LogStatus.INFO, "Home Page is displayed");
							else
								BasePageV2.reportFail("Home page is not displayed");		 
		
		  } 
		 
		 */
		 
	         
    	 
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
			          Thread.sleep(10000);
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
				    Thread.sleep(10000);
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
				     Thread.sleep(10000);
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
					
				
				//scroll to episodes 				
				//0 Episodes
				for(int scroll=0;scroll<=4;scroll++) 
				{
							if(Utilities.explicitWaitVisible(driver, settingspagev2.usageEpisodesCount, 10)) 
							{
								usageStatsEpisodeCount=settingspagev2.usageEpisodesCount.getAttribute("text");
								if(usageStatsEpisodeCount.equalsIgnoreCase("0"))
									test.log(LogStatus.INFO, "Watch card usage data for new user: "+usageStatsEpisodeCount+" is as expected");	
								else
								{
									test.log(LogStatus.FAIL, "Watch card usage data for new user: "+usageStatsEpisodeCount+" is as not as expected");	
									 basepagev2.takeScreenshot();
									 err1612++;
								}
								break;
							}
							else
							{
								Utilities.verticalSwipe(driver);
								if(scroll==2)
								{ 
									test.log(LogStatus.FAIL, "Watch card usage data could not be located");
									 basepagev2.takeScreenshot();
									err1612++;
								}
							}						
				}
				
				//verify usage episodes count equal to ZERO or not
				//Verification
				if(err1612==0) 
				{
					
					 test.log(LogStatus.PASS, "VK_1611---Verify the Episode card usage data if user has not watched any content (or New user) is Passed"); 
					 if(!Utilities.setResultsKids("VK_1611", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
				}
				else 
				{
					 test.log(LogStatus.FAIL, "VK_1611---Verify the Episode card usage data if user has not watched any content (or New user) is Failed"); 
					 if(!Utilities.setResultsKids("VK_1611", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					 basepagev2.takeScreenshot();
				}
				
				//scroll to pages count				
				//0 Pages
				for(int scroll=0;scroll<=2;scroll++) 
				{
						if(Utilities.explicitWaitVisible(driver, settingspagev2.usagePagesCount, 10)) 
						{
								usageStatsPagesCount=settingspagev2.usagePagesCount.getAttribute("text");
								if(usageStatsPagesCount.equalsIgnoreCase("0")) 
									test.log(LogStatus.INFO, "Read card usage data for new user: "+usageStatsPagesCount+" is as expected");	
								else 
								{
									test.log(LogStatus.FAIL, "Read card usage data for new user: "+usageStatsPagesCount+" is as not as expected");	
									 err1616++;
									 basepagev2.takeScreenshot();
								}
								break;
						}
						else
						{
								Utilities.verticalSwipe(driver);
								if(scroll==2)
								{
									test.log(LogStatus.FAIL, "Read card usage data could not be located");	
									err1616++;
									 basepagev2.takeScreenshot();
								}
						}						
				}
				
				////verify usage pages count equal to ZERO or not
				if(err1616==0) 
				{
					
					 test.log(LogStatus.PASS, "VK_1615----Verify the Ebook card usage data if user has not Opened/Read any Ebook (or New user) is Pass"); 
					 if(!Utilities.setResultsKids("VK_1615", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
				}
				else
				{
					
					 test.log(LogStatus.FAIL, "VK_1615---Verify the Ebook card usage data if user has not Opened/Read any Ebook (or New user) is Fail"); 
					 if(!Utilities.setResultsKids("VK_1615", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					basepagev2.takeScreenshot();
				}
				
				Utilities.verticalSwipe(driver);
				
				//0 Stories
				//scroll to stories count				
				for(int scroll=0;scroll<=2;scroll++)
				{
						if(Utilities.explicitWaitVisible(driver, settingspagev2.usageStoriesCount, 10)) 
						{
								usageStatsStoriesCount=settingspagev2.usageStoriesCount.getAttribute("text");
								if(usageStatsStoriesCount.equalsIgnoreCase("0")) 
									test.log(LogStatus.INFO, "Listen card usage data for new user: "+usageStatsStoriesCount+" is as expected");	
								else 
								{
									test.log(LogStatus.FAIL, "Listen card usage data for new user: "+usageStatsStoriesCount+" is as not as expected");	
									basepagev2.takeScreenshot();
									err1620++;
								}
								break;
						}
						else
						{
								Utilities.verticalSwipe(driver);
								if(scroll==2) 
								{
									test.log(LogStatus.FAIL, "Listen card usage data could not be located");
									basepagev2.takeScreenshot();
									err1620++;
								}
						}						
				}
								
				//Verification of stories count equal to ZERO or not				
				if(err1620==0) 
				{
					
					 test.log(LogStatus.PASS, "VK_1619----Verify the Listen card usage data if user has not Played any audio book (or New user) is Passed"); 
					 if(!Utilities.setResultsKids("VK_1619", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
				}
				else 
				{					
					  test.log(LogStatus.FAIL, "VK_1619---Verify the Listen card usage data if user has not Played any audio book (or New user) is Failed"); 
					  if(!Utilities.setResultsKids("VK_1619", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					  basepagev2.takeScreenshot();
				}
				
				//scroll to  Questions count
				
				for(int scroll=0;scroll<=2;scroll++) 
				{
						if(Utilities.explicitWaitVisible(driver, settingspagev2.usageQuestionsCount, 10)) 
						{
							usageStatsQuestionsCount=settingspagev2.usageQuestionsCount.getAttribute("text");
							if(usageStatsQuestionsCount.equalsIgnoreCase("0")) 
								test.log(LogStatus.INFO, "Learn card usage data for new user: "+usageStatsQuestionsCount+" is as expected");	
							else
							{
								test.log(LogStatus.FAIL, "Learn card usage data for new user: "+usageStatsQuestionsCount+" is as not as expected");	
								err1623++;
								basepagev2.takeScreenshot();
							}
							break;
						}
						else 
						{
							  Utilities.verticalSwipe(driver);
							
								if(scroll==2) 
								{
									test.log(LogStatus.FAIL, "Learn card usage data could not be located");		
									err1623++;
									basepagev2.takeScreenshot();
								
								}
						}						
				}
				
				//Verification of learn card usage data equal to zero or not				
				if(err1623==0) 
				{					
					 test.log(LogStatus.PASS, "VK_1622---Verify the Learn card usage data if user has not attempted any questions (or New user) is Passed"); 
					  if(!Utilities.setResultsKids("VK_1622", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
				}
				else 
				{
					
					  test.log(LogStatus.FAIL, "VK_1622---Verify the Learn card usage data if user has not attempted any questions (or New user) is Failed"); 
					  if(!Utilities.setResultsKids("VK_1622", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					  basepagev2.takeScreenshot();
				}
				
			}
		
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	        }
}
