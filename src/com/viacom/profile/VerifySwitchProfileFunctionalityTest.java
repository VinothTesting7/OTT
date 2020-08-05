package com.viacom.profile;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;
//AUTHORNAME =Vinoth
public class VerifySwitchProfileFunctionalityTest extends BaseTestV2
{		
		//VK_654--Verify Switch Profile page UI	
		//VK_655--Verify the click functionality of 'Edit Profile Buddy' button	
		//VK_663---Validate Close icon functionality----invalid
		//VK_664----Validate Settings button functionality----invalid
		//VK_665---Validate 'Create new' button functionality
		//VK_666---Verify the functionality of switch profile icons
	    //VK_667---Verify the number of profiles associated with each account:
	
		
		String testName = "VerifySwitchProfileFunctionalityTest";
		@Test(dataProvider = "getData")
		public void loginTest(Hashtable<String, String> data) throws Exception
		{
		      if(GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it is No");
		    test = rep.startTest("VerifySwitchProfileFunctionalityTest");
		     test.log(LogStatus.INFO, "Starting the test for Verifying the Switch Profile screen functionality: "+VootConstants.DEVICE_NAME);
	
		// Check run mode
	    if(!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	    }	
		
		launchApp();
		HomePageV2 homepagev2=new HomePageV2(driver, test);
		LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
		
		           //login with Valid credentials
	                  homepagev2.login(data.get("Email"),data.get("Password"));
		
					//VK_654--Verify Switch Profile page UI		
			
		            
					test.log(LogStatus.INFO, "Navigating to Switch Profile screen");
					
					//click on profile icon
					if(Utilities.explicitWaitClickableNew(driver, homepagev2.profilepic, 10))
					{
						homepagev2.profilepic.click();
						test.log(LogStatus.INFO, "clicked on profile icon in home page");
					}
					else
					{
						test.log(LogStatus.FAIL, "failed to click on profile icon in home page");
						BasePageV2.takeScreenshot();
						
					}	
			
				   int	error_654=0;
				   
				  //Check whether navigated to Switch Profile screen		
				  if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.switchProfileScreenToolBarTitle, 10))
				  {
				        test.log(LogStatus.INFO,"Navigated to Switch Profile Screen successfully");
				  }
				  else
				  {
					  test.log(LogStatus.FAIL,"after Clicking on profile icon not navigated to Switch Profile Screen");
					  BasePageV2.takeScreenshot();
					  error_654++;
				  }
		
				//Checking edit profile Buddy button displayed or not		   	   
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.editProfileBuddyButton, 10)) 
				{
			        test.log(LogStatus.INFO, "Edit Profile Buddy button is displayed");        
				}
				else		
				{
					   test.log(LogStatus.FAIL,"Edit Profile Buddy button is not displayed");
					   BasePageV2.takeScreenshot();
					   error_654++;
				}
				
				//Checking Parent Zone button displayed or not					
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.parentZoneButton, 10))
				{
				      test.log(LogStatus.INFO, "Parent zone button is displayed");
				}
				else
				{
					 test.log(LogStatus.FAIL,"Parent zone button is not displayed");
					 BasePageV2.takeScreenshot();
					 error_654++;
				}
				
				/*//Check Settings button dispalyed or not				
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.settingsInSwitchProfileScreen, 20))
				{
				   test.log(LogStatus.INFO, "Settings button is displayed");
				}
				else
				{
					 test.log(LogStatus.FAIL,"Settings button is not displayed");
				      BasePageV2.takeScreenshot();
				}
				
				
				//Check close button displayed or not				
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.switchProfileCancelButton, 20))
				{
					  test.log(LogStatus.INFO, "close button is displayed");
				}
				else
				{
					    test.log(LogStatus.FAIL,"close button is not displayed");
					    BasePageV2.takeScreenshot();
				}*/
					
				
				//CERATED Profile name displayed or not				
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.switchProfileScreenProfileName, 10))
				{
					 test.log(LogStatus.INFO, "Created kids Profile name is displayed");
				}
				else
				{
					 test.log(LogStatus.FAIL,"Created Kids Profile name is not displayed");
					 BasePageV2.takeScreenshot();
					 error_654++;
				}
						
				//Current Profile ticked or not				
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.switchProfileScreenSelectedProfile, 10))
				{
					test.log(LogStatus.INFO, "Tick icon present on the current profile");
				}
				else
				{
					test.log(LogStatus.FAIL,"Tick icon not present on the current profile");
					 BasePageV2.takeScreenshot();
					 error_654++;
				}
				
				
				//checking switch profile screen avatar
			    if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.switchProfileScreenProfileAvatar, 10))
				{
				     test.log(LogStatus.INFO, "Profile avatar is displayed");
				}
				else
				{
					 test.log(LogStatus.FAIL,"Profile avatar is not displayed");
					 BasePageV2.takeScreenshot();
					 error_654++;
				}
			
			    //checking create new icon
			    if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.createNewIcon, 10))
			 	{
				     test.log(LogStatus.INFO, "Create New Profile Icon is displayed");
				 	             
			 	}
				else
				{
					  test.log(LogStatus.FAIL,"Create New Profile icon is not displayed");
					  BasePageV2.takeScreenshot();
					  error_654++;
				}
			
			    
			    if(error_654==0)
			    {
			    	test.log(LogStatus.PASS,"Testcase : 'VK_654--Verify Switch Profile page UI:' is Passed");
		             if(!Utilities.setResultsKids("VK_654", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 	
			    }
			    else
			    {
			    	 test.log(LogStatus.FAIL, "VK_654--Verify Switch Profile page UI:' is failed");
		              if(!Utilities.setResultsKids("VK_654", "FAIL"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              
					  BasePageV2.takeScreenshot();
			    }
			    
			    
			    
 //VK_655--Verify the click functionality of 'Edit Profile Buddy' button
			    
			    test.log(LogStatus.INFO,"Testcase : 'VK_655--Verify the click functionality of 'Edit Profile Buddy' button:'");
			  
			    if(Utilities.explicitWaitClickableNew(driver, launchpagev2.editProfileBuddyButton, 10))	
				{
				       launchpagev2.editProfileBuddyButton.click();
				       test.log(LogStatus.INFO," clicked on Edit Profile Buddy button");
				}
			    else
			    {
				     test.log(LogStatus.FAIL,"Not able to click on Edit Profile Buddy button");
					  BasePageV2.takeScreenshot();
			    }
			
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.editProfileBuddyScreenToolBarSubTitle, 10))
				{
					  test.log(LogStatus.INFO, "Navigated to Edit Profile Buddy screen");
					  test.log(LogStatus.PASS,"Testcase : 'VK_655--Verify the click functionality of 'Edit Profile Buddy' button:' is passed");
					  if(!Utilities.setResultsKids("VK_655", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");   
					 
				}
				else
				{
					  test.log(LogStatus.FAIL, "Not navigated to  Edit Profile Buddy screen");
					  test.log(LogStatus.FAIL,"Testcase : 'VK_655--Verify the click functionality of 'Edit Profile Buddy' button:' is failed");
					  if(!Utilities.setResultsKids("VK_655", "FAIL"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              
					  BasePageV2.takeScreenshot();			
				}
				  driver.navigate().back();
			   
			
			   /* if(Utilities.explicitWaitClickableNew(driver, launchpagev2.parentZoneButton, 20))
				launchpagev2.parentZoneButton.click();
				else
				BasePageV2.reportFail("Not able to click on Parent zone button");*/
	
				/*//VK_663---Validate Close icon functionality
				
				test.log(LogStatus.INFO, "VK_663---Validate Close icon functionality");
				
				if(Utilities.explicitWaitClickableNew(driver, launchpagev2.switchProfileCancelButton, 20))		
				{
					launchpagev2.switchProfileCancelButton.click();
					test.log(LogStatus.INFO," clicked on  Cancel button in switch profile screen");
				}
				else
				{
					test.log(LogStatus.FAIL,"Not able to click Cancel button switch profile screen");
					 BasePageV2.takeScreenshot();
				}
				
				 if(Utilities.explicitWaitClickableNew(driver, homepagev2.profilepic, 20))
			 	 {
					       test.log(LogStatus.INFO, "profile-pic icon is  available in home page ");
					 
						try{
									      if(homepagev2.mystuff_tab.getAttribute("selected").equals("true")) 
										  {
											 test.log(LogStatus.PASS,"Testcase : 'VK_663---Validate Close icon functionality:' is Passed");											 
											 if(!Utilities.setResultsKids("VK_663", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              											  
										  }
										  else
										  {						         
									          test.log(LogStatus.FAIL, "Testcase : VK_663---Validate Close icon functionality:' is faield ");
									          if(!Utilities.setResultsKids("VK_663", "FAIL"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              
											  BasePageV2.takeScreenshot();
										  }
									      
							              homepagev2.profilepic.click();
							              test.log(LogStatus.INFO," clicked on  profile pic-icon in home page");
							       
							 }catch(Exception e)
							 {
								 test.log(LogStatus.FAIL,"failed to  click on  profile pic-icon in home page");
								 BasePageV2.takeScreenshot();
							 }
				  }
				 else
				  {
					 test.log(LogStatus.FAIL, "profile-pic icon is not available in home page ");
					 BasePageV2.takeScreenshot();
				  }
				
				//VK_664----Validate Settings button functionality	
				 
			    test.log(LogStatus.INFO, "Validating Settings button functionality");
				
				if(Utilities.explicitWaitClickableNew(driver, homepagev2.settings, 20))
				{
					     homepagev2.settings.click();
					     test.log(LogStatus.INFO, " clicked on Settings icon in Switch profile screen");
				}
				else
				{
				        test.log(LogStatus.FAIL, "Not able to click Settings icon in Switch profile screen");
				        BasePageV2.takeScreenshot();
				}
				
				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.parentPinContainer, 20))
				{
					 test.log(LogStatus.INFO, "navigated to PIN - enter screen");
					 test.log(LogStatus.PASS,"Testcase : 'VK_664----Validate Settings button functionality' is Passed");				    
					 if(!Utilities.setResultsKids("VK_664", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              					 
				}
				else
				{				
					  test.log(LogStatus.FAIL, "Not navigated to PIN - enter screen");
					  test.log(LogStatus.FAIL,"Testcase : 'VK_664----Validate Settings button functionality' is failed");				    
					  if(!Utilities.setResultsKids("VK_664", "FAIL"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              
					  BasePageV2.takeScreenshot();
				}
				
				if(Utilities.explicitWaitClickableNew(driver, launchpagev2.backButton, 20))
				{
					launchpagev2.backButton.click();
					test.log(LogStatus.INFO, "clicked on back button");
				}
				else
				{
				   test.log(LogStatus.FAIL,"Not able to click back button / Back button is not present");
				   BasePageV2.takeScreenshot();
				}
				*/
			   
					

				
 //VK_665---Validate 'Create new' button functionality
				
				test.log(LogStatus.INFO, "VK_665---Validate 'Create new' button functionality");
								
				if(Utilities.explicitWaitClickableNew(driver, launchpagev2.createNewIcon, 10))
				{
					launchpagev2.createNewIcon.click();
					test.log(LogStatus.INFO, "clicked on create New-icon");
				}
				else
				{
					test.log(LogStatus.FAIL,"Not able to click Create New Profile button / button is not present");
					 BasePageV2.takeScreenshot();
				}
				
				if(Utilities.explicitWaitClickableNew(driver, launchpagev2.parentPinContainer, 3))
				{
							launchpagev2.parentPinContainer.clear();	
							launchpagev2.parentPinContainer.sendKeys("1111");
							Thread.sleep(1000);
					     	try{
							    driver.hideKeyboard();
							    launchpagev2.parentPinContainer.sendKeys("1111");
						       }catch(Exception e){}					      
				}
				else
				{
					// test.log(LogStatus.FAIL,"parent pin container is not present");
					 BasePageV2.takeScreenshot();
				}
				

				if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.createProfileTellUsFirstScreen, 10))
				{					
					  test.log(LogStatus.PASS,"Testcase : 'VK_665---Validate 'Create new' button functionality' is Passed");
					  if(!Utilities.setResultsKids("VK_665", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");              
					  BasePageV2.takeScreenshot();
				}
				else
				{				     
				     test.log(LogStatus.FAIL,"Testcase : 'VK_665---Validate 'Create new' button functionality' is failed");
				     if(!Utilities.setResultsKids("VK_665", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");              
					 BasePageV2.takeScreenshot();
				}
				
					driver.pressKeyCode(AndroidKeyCode.BACK);
					driver.pressKeyCode(AndroidKeyCode.BACK);
					
				  for(int i=1;i<=4;i++){
					  
					  int profileListSize=launchpagev2.profileLists.size();
					  GlobalVariables.profileCount=profileListSize;
					  if(profileListSize<=5)
					  {
							if(Utilities.explicitWaitClickableNew(driver, homepagev2.profilepic, 10))
							{
								homepagev2.profilepic.click();
							}
						
						  
							if(Utilities.explicitWaitClickableNew(driver, launchpagev2.createNewIcon, 10))
							{
								launchpagev2.createNewIcon.click();
								test.log(LogStatus.INFO, "clicked on create New-icon");
							}
							else
							{
								test.log(LogStatus.FAIL,"Not able to click Create New Profile button / button is not present");
								BasePageV2.takeScreenshot();
							}
						  
					      homepagev2.createProfile("newProf"+i);
					  }
					  
				  }
				  
				   
					
				//VK_666---Verify the functionality of switch profile icons		
								
								test.log(LogStatus.INFO, "VK_666---Verify the functionality of switch profile icons");
								
								if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.switchProfileScreenProfileAvatar, 10))
								{				
									    test.log(LogStatus.INFO,"Avatar is available for the profile");
										try{
											  test.log(LogStatus.INFO, "Before Switching , Selected Profile name is : "+launchpagev2.selectedProfileName.getText());
										   }
										   catch(Exception e)
										   {
											  test.log(LogStatus.FAIL,"Profile Name is not displayed for the selected profile");
											  BasePageV2.takeScreenshot();
										   }					
								}
								else
								{
								          test.log(LogStatus.FAIL,"Avatar is missing for the profile");
								          BasePageV2.takeScreenshot();
								}				
								
								String profname="";
								if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.unselectedProfileAvatar, 10))
								{
									try{
									         profname=launchpagev2.unselectedProfileName.getText().toString().trim();	
										     test.log(LogStatus.INFO, "Now , Switching to Profile Name : "+profname);
									   }catch(Exception e)					
									   {
										     test.log(LogStatus.FAIL,"Profile Name is not displayed for one of the profile");
										     BasePageV2.takeScreenshot();
									   }					
									    launchpagev2.unselectedProfileAvatar.click();
									    test.log(LogStatus.INFO,"clicked on  the profile which is not selected");
								}
								else
								{
									   test.log(LogStatus.FAIL,"Not able to click the profile which is not selected");
									   BasePageV2.takeScreenshot();
								}
															
										
							    Thread.sleep(15000);	
							    
							    	
							     if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.parentPinContainer, 5))
								{
							    	    test.log(LogStatus.INFO, "parent pin container is available");
										launchpagev2.parentPinContainer.clear();	
										launchpagev2.parentPinContainer.sendKeys("1111");
										Thread.sleep(1000);
										try{
											  driver.hideKeyboard();
											  launchpagev2.parentPinContainer.sendKeys("1111");
										   }catch(Exception e){}				
				               }
				             
							    
								if(Utilities.explicitWaitVisibleNew(driver, launchpagev2.selectedProfileName, 10))
								{					
										   test.log(LogStatus.INFO,"Profile name is available for the selected profile"+launchpagev2.selectedProfileName.getText());
										   
										   if(launchpagev2.selectedProfileName.getText().toString().trim().equalsIgnoreCase(profname))							  
											{
										    	 test.log(LogStatus.INFO," switched to the selected profile");
											     test.log(LogStatus.PASS,"Testcase : 'VK_666---Verify the functionality of switch profile icons' is Passed");
											     if(!Utilities.setResultsKids("VK_666", "PASS"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              								 
											}
										    else
											{
										    	  test.log(LogStatus.FAIL,"Not switched to the selected profile");
										    	  test.log(LogStatus.FAIL,"Testcase : 'VK_666---Verify the functionality of switch profile icons' is failed ");						    	  
										    	  if(!Utilities.setResultsKids("VK_666", "FAIL"))   test.log(LogStatus.WARNING, "TC ID not found in the tc document");              
												  BasePageV2.takeScreenshot();
											}
								}
								else
								{
									         test.log(LogStatus.FAIL,"Profile name is missing for the selected profile");
									         BasePageV2.takeScreenshot();
								} 
					
					
					
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
