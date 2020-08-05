package com.viacom.profile;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifyDeleteProfileFunctionality extends BaseTestV2 {
	
String testName = "VerifyDeleteProfileFunctionality";
	
	
	String newEmail = "";
    String oldEmail="";
    String oldMobileNum = "";
	@Test(dataProvider = "getData")
	public void verifyDeleteProfileFunctionality(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyDeleteProfileFunctionality");
		test.log(LogStatus.INFO, "Starting the test to Verify Delete Profile Functionality: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		
		Xls_Reader xls802 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno802 = xls802.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno802,
				"802: Verify the Functionality of delete profile by clicking on 'Yes' in pop-up:");
		
		Xls_Reader xls804 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno804 = xls804.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno804,
				"804: Verify edited fields are not saved when clicked on back arrow in edit profile page without clicking on done");
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
		
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		//Login if not logged in to Voot-Kids Application in the device
		
//		 HomePageV2.login("ifocus1@gmail.com", "Ifocus@122");    // Mobile Number for this Account is 8919179844
		
		homepagev2.login(data.get("Email"),data.get("Password"));
		
		
			try {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");

			} catch (Exception e) {
				BasePageV2.reportFail("Not able to click on Profile Icon in home page / not found");
			}
		


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
		
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
			settingsPageV2.settingsIcon.click();
		    test.log(LogStatus.INFO, "clicked on Settings Icon");
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneProfiles, 10)) {
					settingsPageV2.settingTextinParantZoneProfiles.click();
	// Verify edited fields are not saved when clicked on back arrow in edit profile page without clicking on done
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateImg, 10))
						settingsPageV2.settingsProfileCreateImg.click();
						else BasePageV2.reportFail("Unable to navigated to Edit profile page");
					    //send the profile name to name Edit text box
					    if(Utilities.explicitWaitVisible(driver, settingsPageV2.editProNameEditText, 10))
					    	settingsPageV2.editProNameEditText.sendKeys("ifocus");
					    else BasePageV2.reportFail("unable to found Name Edit Prfile text box in Edit profile page ");
					    //selecting the date and year     
					    if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsDateOfYear, 10))
					    	settingsPageV2.settingsDateOfYear.click();
					    else BasePageV2.reportFail("unable to found date of Year Edit text box in Edit Profile page");
					
					    if(Utilities.explicitWaitClickable(driver, settingsPageV2.calenderOkBtn, 10))
					    	settingsPageV2.calenderOkBtn.click();
					    else BasePageV2.reportFail("unable to found date of ok buttonon Calender pop up page");
					    
					    if(Utilities.explicitWaitClickable(driver, settingsPageV2.editProfileBackBtn, 10))
					    	settingsPageV2.editProfileBackBtn.click();
					    else BasePageV2.reportFail("unable to found Edit prfile back Button");
					    
					    if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsProfileTile, 10)) {
					    	List<WebElement> lispro = driver.findElementsByXPath(
									"//android.widget.LinearLayout[@index='3']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/settings_list']//android.view.ViewGroup//android.widget.TextView");
							int size = lispro.size();
							System.out.println("Profile List Size :" + size);
					    	for(int i = 1; i < size;i++) {
					    		String protile = lispro.get(i).getText().toString().trim();
					    		if(!protile.equalsIgnoreCase("ifocus")) {
					    			test.log(LogStatus.INFO, "Expected profile not found in profile list: passed");
					    			test.log(LogStatus.PASS, "Verified edited fields are not saved when clicked on back arrow in edit profile page without clicking on done");
									//BasePageV2.takeScreenshot();
									BasePageV2.smokeresults(" ",rowno804, "PASS");
					    		}else test.log(LogStatus.FAIL, "Prfile found in the profile list : failed");
					    	}
					    	
					    }else BasePageV2.reportFail("Not able to navigated to Profile Mian page");
					
					
					test.log(LogStatus.INFO, "Checking the Total  list of Profiles present in the Prfiles Page");
					List<WebElement> lis = driver.findElementsByXPath(
							"//android.widget.LinearLayout[@index='3']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/settings_list']//android.view.ViewGroup//android.widget.TextView");
					int size = lis.size();
					System.out.println("List Size :" + size);
					test.log(LogStatus.INFO, "got size of List of Profiles");
					
					if(size < 2) {
					 test.log(LogStatus.INFO, "Creating the Profile if less than 1 profile present");
					 if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateNewPro, 10)) {
					 settingsPageV2.settingsProfileCreateNewPro.click();
					 test.log(LogStatus.INFO, "Tapped on Create New profile option in Profile page");
					}else BasePageV2.reportFail("Create New Profile option could not Found in profile page");
					 
					 if(Utilities.explicitWaitVisible(driver, settingsPageV2.editTextinEditProfile, 10)) {
						 settingsPageV2.editTextinEditProfile.sendKeys("ABCD");
						 test.log(LogStatus.INFO, "Entered the Profile name in Edit text Box filed ");
					 }else BasePageV2.reportFail("Edit Text Box not found in Create Profile page");
					 
					 if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsProfileEditProYearEdit, 10)) {
						 settingsPageV2.settingsProfileEditProYearEdit.click();
						 test.log(LogStatus.INFO, "Selected Date in  Date Filed ");
					 }else BasePageV2.reportFail("unale to find the Calender year and date Edit Text box Fileds");
						 
					 if(Utilities.explicitWaitClickable(driver, settingsPageV2.calenderOkBtn, 10)) {
						 settingsPageV2.calenderOkBtn.click();
					 }else BasePageV2.reportFail("unale to Found Ok Button in Calender page to select Date");
					 
					 if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsEditProfilrNextBtn, 10)) {
						 settingsPageV2.settingsEditProfilrNextBtn.click();
						 test.log(LogStatus.INFO, "Clicked Next button in Create Profile page");
						 settingsPageV2.settingsEditProfilrNextBtn.click();
					}else BasePageV2.reportFail("Next Button is not Found in create profile page to create Prfile");
					 
						
						//Select any skill  (Story skill)
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.skillSetIcon, 20))
							{
							settingsPageV2.skillSetIcon.click();
							}
						else
							BasePageV2.reportFail("Skills are not displayed in Create Profile Screen");
						test.log(LogStatus.INFO, "Creating profile by selecting 6 characters");
						List<WebElement> icons2 = settingsPageV2.characterSetIcons;
						//selecting 5 character icons
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.characterSetIcon, 20))
						{
							
							for(int i=0;i<=3;i++)
							{
								try{
									icons2.get(i).click();
								}
								catch(Exception e)
								{
									BasePageV2.reportFail("Not able to click Character Icon - "+(i+1));
								}
							}
						}
						else
							BasePageV2.reportFail("Characters set are not displayed in Create Profile Screen");
						
						test.log(LogStatus.INFO, "Selecting 6 favorite characters and click on Next button");
					 
						//click next button
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsEditProfilrNextBtn, 20))
						    {
							test.log(LogStatus.INFO,"Next Button is displayed");
							settingsPageV2.settingsEditProfilrNextBtn.click();
						    }
						    else
							BasePageV2.reportFail("Next button is not displayed");
						
					// Clicking the Let'sGo button	
						if(Utilities.explicitWaitVisible(driver, settingsPageV2.letsGo, 20))
						{
							settingsPageV2.letsGo.click();
							test.log(LogStatus.INFO, "Lets Go button is displayed in the screen");
						}
						else
							test.log(LogStatus.FAIL,"Lets Go button is not displayed in the final screen of Create Profile");
						
						
						
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
							}else BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
						}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
					
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 10)) {
							settingsPageV2.settingsIcon.click();
						     test.log(LogStatus.INFO, "clicked on Settings Icon");
								if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingTextinParantZoneProfiles, 10)) {
									settingsPageV2.settingTextinParantZoneProfiles.click();
								}else BasePageV2.reportFail("Profiles option is not Found in Settings Page to naviagte to Account main Page");
							
						} else BasePageV2.reportFail("Settings Icon Not Found ");
						
					 
					} else test.log(LogStatus.FAIL, "Number of Profile's are less than one");
					
		//Verify the Functionality of delete profile by clicking on 'Yes' in pop-up:
					
					if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateImg, 10))
						settingsPageV2.settingsProfileCreateImg.click();
						else BasePageV2.reportFail("Unable to navigated to Edit profile page");
						if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsEditProfileDelProBtn, 10)) {
							settingsPageV2.settingsEditProfileDelProBtn.click();
							test.log(LogStatus.INFO, "Tapped on Delete Prfile button");
						}else BasePageV2.reportFail("Unale to Found Delete Button in Edit Profile page");
						
						test.log(LogStatus.INFO, "Clicking on yes Button to Delete Prfile");
						if(Utilities.explicitWaitClickable(driver, settingsPageV2.prodelPopYesBtn, 10))
							settingsPageV2.prodelPopYesBtn.click();
						else BasePageV2.reportFail("Unable to found the yes button on Delete pop up");
					    if(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileTile, 10)) {
					    	test.log(LogStatus.PASS, "Verified the Functionality of delete profile by clicking on 'Yes' in pop-up:");
							//BasePageV2.takeScreenshot();
							BasePageV2.smokeresults(" ",rowno802, "PASS");
					    	
					    }else test.log(LogStatus.FAIL, "unable to navigate to Profiles page");
					
					
				
				}else BasePageV2.reportFail("Unable to Found Profiles Option in Settings Main Page");
			
		}else BasePageV2.reportFail("Settings Icon is not Found to navigate to Settings page");
		
		
		
		
		
		
		
		
		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}	

}
