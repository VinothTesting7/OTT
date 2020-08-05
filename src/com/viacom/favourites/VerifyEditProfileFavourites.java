package com.viacom.favourites;

import java.time.Duration;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import bsh.util.Util;

public class VerifyEditProfileFavourites extends BaseTestV2{

	String testName = "VerifyEditProfileFavourites";
	
	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyEditProfileFavourites");
		test.log(LogStatus.INFO, "Starting the test to Verify Verify Edit Profile Favourites: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno809 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno809,"809: Verify the functionality of Edit Favourites link: ");
		
		int rowno810 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno810,"810: Verify the UI of Favourites screen : ");
		
		int rowno815 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno815,"815: Verify the back button in the Favourites screen: ");
		
		int rowno812 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno812,"812: Verify the favourites selection functionality in the Favourites screen  by selecting less than or equal to 4 favourites characters(invalid)");
		
		int rowno813 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno813,"813: Verify the favourites selection functionality in the Favourites screen  by selecting 5 favourites characters(Valid) ");
		
		int rowno814 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno814,"814: Verify the favourites selection functionality in the Favourites screen  by selecting 6 favourites characters(Valid) ");
		
	
		
		
		// Launching the Voot-kids App
				launchApp();
				test.log(LogStatus.INFO, "Application launched successfully");

				LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
				SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
				HomePageV2 homepagev2 = new HomePageV2(driver, test);
				BasePageV2 BasePageV2 = new BasePageV2(driver, test);
				// navigating to settings page and verifying pin screen
				
//				homepagev2.logout();
				


				homepagev2.login(data.get("Email"),data.get("Password"));
				
				try {
					if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {

						homepagev2.profilepic.click(); // tap on profile icon
						test.log(LogStatus.INFO, "Succusfully entered to Switch profile page");

					}
				} catch (Exception e) {
					BasePageV2.reportFail("Profile Icon Not Fould to navigate to Switch profile ");
				}

				// Click on ParentZone Button in Switch Profile Screen parentZoneButton
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
					settingsPageV2.parentZoneButton.click();
					test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
						Thread.sleep(1000);
						settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
						Thread.sleep(10000);
					}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
				}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");

				
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();
				
				 
					try {
						settingsPageV2.settingsIcon.click(); // tapping setting icon
						test.log(LogStatus.INFO, "Succusfully entered to parent Zone");
					} catch (Exception e) {
						e.printStackTrace();
						BasePageV2.reportFail("Not able to click Settings Icon in Switch Profile page");
					}
				
				
					
					
					if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsProfile, 2)) {
						settingsPageV2.settingsProfile.click();
						test.log(LogStatus.INFO, "Succussfully tapped the Profile option in Settings page to navigate to Profile list Screen");
					}else BasePageV2.reportFail("Unable to click on Profiles option in Settings Page to navigate to Profiles Screen");
		
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateImg, 2)) {
		            	settingsPageV2.settingsProfileCreateImg.click();
		            	test.log(LogStatus.INFO, "Succussfully tapped the user profile in profile List Screen");
		            }else BasePageV2.reportFail("Unable to click on the user profile in profile Screen");
		
		            //Verify the functionality of Edit Favourites link: 
		            
		            if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourfavourites, 2)) {
		            	settingsPageV2.editYourfavourites.click();
		            	test.log(LogStatus.INFO, "Succussfully tapped the Edit favourites Link");
		            }else BasePageV2.reportFail("Unable verify the Edit Favourites Link Functionality");
		            
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.subTitleFav, 4)) {
		            	BasePageV2.reportPass("Verified The Functionality of Edit Favourites Link");
		            	homepagev2.smokeresults(" ",rowno809, "PASS"); 
		            	
		            }else {
		            	test.log(LogStatus.FAIL, "Unable to Navigate to Edit Favourites Avatars Page ");
		            	BasePageV2.takeScreenshot();
		            	homepagev2.smokeresults(" ",rowno809, "FAIL"); 
		            }
		            
		            // Verify the UI of Favourites screen :
		            
		          if(Utilities.explicitWaitVisible(driver, settingsPageV2.editAvatorTitle, 2)) {
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.subTitleFav, 2)) {	
		                if(Utilities.explicitWaitVisible(driver, settingsPageV2.doneBtnFavAvatarPage, 2)) {
		                     if(Utilities.explicitWaitVisible(driver, settingsPageV2.charIconFavAvatar, 2)) {
//		            		     if(Utilities.explicitWaitVisible(driver, settingsPageV2.skillSetIconFavAvatar, 2)) {
		            		    	 if(Utilities.explicitWaitVisible(driver, settingsPageV2.favourites5SelectTextBackBtn, 2)) {
		            		    		 
		            		    		 test.log(LogStatus.INFO, "Succussfully Verified UI of Favourites Screen");
		            		    		 homepagev2.smokeresults(" ",rowno810, "PASS"); 
		            		    		 
		            		    	 }else test.log(LogStatus.FAIL, "Back button not found in favourites Avatar page");
//		            		     }else test.log(LogStatus.FAIL, "Skill set ICon(Avatar) not found in favourites Avatar page");
		                     }else test.log(LogStatus.FAIL, "Character ICon(Avatar) not found in favourites Avatar page");
		                }else test.log(LogStatus.FAIL, "Done Button not found in favourites Avatar page");
		            }else test.log(LogStatus.FAIL, "'Pick your favorites' sub text not found in favourites Avatar page");
		          }else test.log(LogStatus.FAIL, "unable to find the favourites Avatar page Title");
		          
		        //Verify the back button in the Favourites screen:
		          if(Utilities.explicitWaitVisible(driver, settingsPageV2.favourites5SelectTextBackBtn, 2)) {
		        	  settingsPageV2.favourites5SelectTextBackBtn.click();
		        	  test.log(LogStatus.INFO, "Succussfully clicked Back Button in favourites Avatar page");
		        	  if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourfavourites, 4)) {
		        		  BasePageV2.reportPass("Verified the Back Arrow button Functionality");
		        	  homepagev2.smokeresults(" ",rowno815, "PASS"); 
		        	  }else test.log(LogStatus.FAIL, "Not able to Navigated to Edit Profile Page");
		          }else {
		        	  test.log(LogStatus.FAIL, "unable to clicked Back Button in favourites Avatar page ");
		        	  BasePageV2.takeScreenshot();
		        	  homepagev2.smokeresults(" ",rowno815, "FAIL"); 
		          }
		          
		         //Verify the favourites selection functionality in the Favourites screen  by selecting less than or equal to 4 favourites characters(invalid) 		     
		            	
		          //putting app Background bring back
		             driver.runAppInBackground(Duration.ofSeconds(3));
		     	     test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		     	     driver.currentActivity();
		     	     
		     	    if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateImg, 2)) {
		            	settingsPageV2.settingsProfileCreateImg.click();
		            	test.log(LogStatus.INFO, "Succussfully tapped the user profile in profile List Screen");
		            }else BasePageV2.reportFail("Unable to click on the user profile in profile Screen");
		          
		          if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourfavourites, 4)) {
		        	  settingsPageV2.editYourfavourites.click();
		        	  test.log(LogStatus.INFO, "Succussfully tapped 'Edit Your Favourites Link'");
		          }else BasePageV2.reportFail("Not able to Click 'Edit Your favourites' Link / Not Found");
		          
		        
		        	  if(Utilities.explicitWaitVisible(driver, settingsPageV2.doneBtnFavAvatarPage, 2))  {
		        		  settingsPageV2.doneBtnFavAvatarPage.click();
		        		  test.log(LogStatus.INFO, "Succussfully Clicked Done Button in favourites Edit Profile Page");
		        		  if(!Utilities.explicitWaitClickable(driver, settingsPageV2.editYourfavourites, 4)) {
		        			  BasePageV2.reportPass("Succussfully Not able to navigated to Edit profile page ");
		        			  homepagev2.smokeresults(" ",rowno812, "PASS"); 
		        		  }else {
		        			  test.log(LogStatus.INFO, "Navigated to Edit profile page ");
		        			  homepagev2.smokeresults(" ",rowno812, "FAIL"); 
		        		  }
		        	  }else test.log(LogStatus.INFO, "Unable to click Done button in Favourites Edit Profile page");
		        	  
		        	  
		         
		            
		        //Verify the favourites selection functionality in the Favourites screen  by selecting 5 favourites characters(Valid)    
		         
		        	  if(Utilities.explicitWaitVisible(driver, settingsPageV2.doneBtnFavAvatarPage, 2))  {
		        		  settingsPageV2.doneBtnFavAvatarPage.click();
		        		  test.log(LogStatus.INFO, "Succussfully Clicked Done Button in favourites Edit Profile Page");
		        		  if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourfavourites, 4)) {
		        			  BasePageV2.reportPass("Succussfully navigated to Edit profile page ");
		        			  homepagev2.smokeresults(" ",rowno813, "PASS"); 
		        		  }else {
		        			  test.log(LogStatus.INFO, "Not able to navigated to Edit profile page ");
		        			  homepagev2.smokeresults(" ",rowno813, "FAIL"); 
		        		  }
		        	  }else test.log(LogStatus.INFO, "Unable to click Done button in Favourites Edit Profile page");
		        	  
		        
		        
//              Verify the favourites selection functionality in the Favourites screen  by selecting 6 favourites characters(Valid)
		            
		          if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourfavourites, 4)) {
		        	  settingsPageV2.editYourfavourites.click();
		        	  test.log(LogStatus.INFO, "Succussfully tapped 'Edit Your Favourites Link'");
		          }else BasePageV2.reportFail("Not able to Click 'Edit Your favourites' Link / Not Found");
		          
		          
		        	  if(Utilities.explicitWaitVisible(driver, settingsPageV2.doneBtnFavAvatarPage, 2))  {
		        		  settingsPageV2.doneBtnFavAvatarPage.click();
		        		  test.log(LogStatus.INFO, "Succussfully Clicked Done Button in favourites Edit Profile Page");
		        		  if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourfavourites, 4)) {
		        			  BasePageV2.reportPass("Succussfully navigated to Edit profile page ");
		        			  homepagev2.smokeresults(" ",rowno814, "PASS"); 
		        		  }else {
		        			  test.log(LogStatus.INFO, "Not able to navigated to Edit profile page ");
		        			  homepagev2.smokeresults(" ",rowno814, "FAIL"); 
		        		  }
		        	  }else test.log(LogStatus.INFO, "Unable to click Done button in Favourites Edit Profile page/not found ");
		        
		            
		          
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
	
	
}
