package com.viacom.profile;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
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

public class EditProfileBuddyFunctionality extends BaseTestV2 {
	
	String testName = "EditProfileBuddyFunctionality";
	
	@Test(dataProvider = "getData")
	public void VerifyCharactersInCharactersTray(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("EditProfileBuddyFunctionality");
		test.log(LogStatus.INFO, "Starting the test to Edit Profile Buddy Functionality: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int rowno800 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno800,"800: Verify the functionality of Edit Buddy link:");
		
		int rowno801 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno801,"801: Verify the functionality of Done button in Edit Buddy screen: ");
		
		int rowno802 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno802,"802: Verify the UI of Avatar selection/Create your buddy screen: ");
		
		int rowno807 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno807,"807: Verify the back button in the Edit profile (Avatar selection screen):");
		
		int rowno818 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno818,"818: Verify the Functionality of delete profile by clicking on 'No' in pop-up:");
		
		

		// Launching the Voot-kids App
				launchApp();
				test.log(LogStatus.INFO, "Application launched successfully");

				LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
				SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
				HomePageV2 homepagev2 = new HomePageV2(driver, test);
				BasePageV2 BasePageV2 = new BasePageV2(driver, test);
				
				

//				try {
//					homepagev2.login("editbuddy@gmail.com", "Ifocus@122");
//			        }catch(Exception e) {
//			        	test.log(LogStatus.FAIL, "login Failed");
//			        }
				
				

				homepagev2.login(data.get("Email"),data.get("Password"));
				
				
				try {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

			} catch (Exception e) {
				BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
			}
				
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();
				

		// Click on ParentZone Button in Switch Profile Screen parentZoneButton
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 10)) {
							settingsPageV2.parentZoneButton.click();
							test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
							if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentPinContainer, 10)) {
								Thread.sleep(1000);
								settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
							}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
						}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		
						
						// putting App in background
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
		
		            
		            
		            
//		            Verify the functionality of Edit Buddy link:
		            
		            if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourBuddy, 2)) {
		            	settingsPageV2.editYourBuddy.click();
		            	test.log(LogStatus.INFO, "Succussfully Clicked the 'Edit your buddy' Link in Edit Profile Page");
		            	if(Utilities.explicitWaitVisible(driver, settingsPageV2.editYourBuddy, 2)) {
		            		test.log(LogStatus.PASS,"Verified Edit buddy Functionality");
		            		homepagev2.smokeresults(" ",rowno800, "PASS"); 
		            	}else {
		            		test.log(LogStatus.FAIL, "Not able to Navigate to 'Edit Profile Buddy' Page");
		            		homepagev2.smokeresults(" ",rowno800, "FAIL"); 
		            	}
		            }else {
		            	BasePageV2.reportFail("Unable to click on the 'Edit your buddy' Link in Edit Profile Page");
		            	homepagev2.smokeresults(" ",rowno800, "FAIL"); 
		            }
//		
	//         Verify the UI of Avatar selection/Create your buddy screen:
		
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.editProBuddyTitle, 2)) {
		            	if(Utilities.explicitWaitVisible(driver, settingsPageV2.editYourBuddySubTile, 2)) {
		            		if(Utilities.explicitWaitVisible(driver, settingsPageV2.editbuddyBackBtn, 2)) {
		            			if(Utilities.explicitWaitVisible(driver, settingsPageV2.buddyIcon, 2)) {
		            				if(Utilities.explicitWaitVisible(driver, settingsPageV2.DoneBtnEditBuddy, 2)) {
		            					test.log(LogStatus.PASS,"Verified UI of Avatar create Your Buddy Screen");
		    		            		homepagev2.smokeresults(" ",rowno802, "PASS"); 
		            			    }else test.log(LogStatus.FAIL, "DONE button not found in Edit Profile buddy Page");
		            		      }else test.log(LogStatus.FAIL, "Buddy Icon not found in Edit Profile buddy page");
		            	      }else test.log(LogStatus.FAIL, "Back Button not found in Edit Profile buddy page");
		            	  }else test.log(LogStatus.FAIL, "'Edit your Buddy' Sub title not found in Edit Profile buddy page");
		              }else test.log(LogStatus.FAIL, "'EDIT PROFILE BUDDY'Title not found in Edit Profile buddy page");
    		
//	          Verify the back button in the Edit profile (Avatar selection screen):	            	
		            if(Utilities.explicitWaitVisible(driver, settingsPageV2.editbuddyBackBtn, 2)) {
		            	settingsPageV2.editbuddyBackBtn.click();
		            	test.log(LogStatus.INFO, "Succussfully clicked Back Button in Edit Profile Buddy Page");
		            	if(Utilities.explicitWaitVisible(driver, settingsPageV2.editYourBuddy, 2)) {
		            		test.log(LogStatus.PASS,"Verified back button Functionalilty in Edit Profilr Buddy screen");
		            		homepagev2.smokeresults(" ",rowno807, "PASS"); 
		            	}else test.log(LogStatus.FAIL, "Not able to navigated to Previuos Page(Edit Profile) page");
		                
		            }else {
		            	test.log(LogStatus.FAIL, "Back Button not found in Edit Profile buddy page/ not clicked");
		            	BasePageV2.takeScreenshot();
		            	homepagev2.smokeresults(" ",rowno807, "FAIL"); 
		            }
		
//		    Verify the functionality of Done button in Edit Buddy screen:
		
		            if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourBuddy, 2)) {
		            	settingsPageV2.editYourBuddy.click();
		            	test.log(LogStatus.INFO, "succussfully clicked Edit your buddy Link in Edit profile Page");
		            	WebElement buddyColor = driver.findElementByXPath("//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/buddy_color_list']/android.view.ViewGroup[@index='5']/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/buddy_icon']");
		            	test.log(LogStatus.INFO, "Came inside and Found Colcor of Element in Edit profile Page");
		            	buddyColor.click();
		            	test.log(LogStatus.INFO, "Selected color for buddy in Edit Profile buddy page");
		            	if(Utilities.explicitWaitVisible(driver, settingsPageV2.DoneBtnEditBuddy, 2)) {
		            		settingsPageV2.DoneBtnEditBuddy.click();
		            		test.log(LogStatus.INFO, "Succussfully clicked Done Button in Edit Profile buddy page");
		            		
		            		//put App Background bring back
		            		driver.runAppInBackground(Duration.ofSeconds(3));
		            		 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		            		 driver.currentActivity();
		            		
		            		
		            		if(Utilities.explicitWaitClickable(driver, settingsPageV2.editYourBuddy, 2)) {
		            			test.log(LogStatus.PASS,"Verified DONE button Functionalilty in Edit Profile Buddy screen");
			            		homepagev2.smokeresults(" ",rowno801, "PASS"); 
		            		
		            		}else {
		            			test.log(LogStatus.FAIL, "Not able to navigated to Edit Profile page");
		            			BasePageV2.takeScreenshot();
		            		}
		            	}else {
		            		test.log(LogStatus.FAIL, "unable to Click Done Button/not found in Edit profile buddy screen");
		            		
		            	}
		            }else BasePageV2.reportFail("Unable to click on the 'Edit your buddy' Link in Edit Profile Page");
		
//		    Verify the Functionality of delete profile by clicking on 'No' in pop-up:
		            
		            if(Utilities.explicitWaitClickable(driver, settingsPageV2.deleteProfileLink, 2)) {
		            	settingsPageV2.deleteProfileLink.click();
		            	test.log(LogStatus.INFO, "succussfully clicked Delete Profile Link in Edit Profile Page");
		            	if(Utilities.explicitWaitClickable(driver, settingsPageV2.prodelPopNoBtn, 2)) {
		            		settingsPageV2.prodelPopNoBtn.click();
		            		test.log(LogStatus.INFO, "succussfully clicked No Button in Delete pop up");
		            		
		            		//put App Background bring back
		            		driver.runAppInBackground(Duration.ofSeconds(3));
		            		 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		            		 driver.currentActivity();
		            		 
		            		if(Utilities.explicitWaitClickable(driver, settingsPageV2.editbuddy, 2)) {
		            			test.log(LogStatus.PASS,"Verified DONE button Functionalilty in Edit Profilr Buddy screen");
			            		homepagev2.smokeresults(" ",rowno818, "PASS"); 
		            		
		            		}else {
		            			test.log(LogStatus.FAIL, "Not able to navigated to Edit Profile page");
		            			BasePageV2.takeScreenshot();
		            		}
		            		
		            	}else {
		            		test.log(LogStatus.FAIL, "Unable to Click 'NO' Button on delete pop up ");
		            		BasePageV2.takeScreenshot();
		            		homepagev2.smokeresults(" ",rowno818, "FAIL");
		            	}
		            }else {
		            	test.log(LogStatus.FAIL, "unable to click Delete Profile Link in Edit Profile Page ");
		            	BasePageV2.takeScreenshot();
		            }
		            
		     
		
		
	}
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
