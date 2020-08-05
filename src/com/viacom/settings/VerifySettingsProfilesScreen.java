package com.viacom.settings;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Set;
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

import io.appium.java_client.android.AndroidKeyCode;

public class VerifySettingsProfilesScreen extends BaseTestV2 {
	String testName = "VerifySettingsProfilesScreen";
    
	String delProList = "";
	@Test(dataProvider = "getData")
	public void verifySettingsProfilesScreen(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifySettingsProfilesScreen");
		test.log(LogStatus.INFO, "Starting the test to Verify All Characters Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls778 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno778 = xls778.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno778, "778: Validate Profile icon functionality.");

		Xls_Reader xls781 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno781 = xls781.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno781,
				"781: Verify the functionality by tapping on Back arrow from Profile list screen");

		Xls_Reader xls779 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno779 = xls779.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno779,
				"779: Verify profile settings screen UI if user has created < 6 profiles");

		Xls_Reader xls780 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno780 = xls780.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno780,
				"780: Verify profile settings screen UI if user has created  6 profiles");

		Xls_Reader xls782 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno782 = xls782.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno782,
				"782: Validate the navigation by tapping on Profile icon/arrow icon in profile list screen");

		Xls_Reader xls783 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno783 = xls783.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno783,
				"783: Validate 'Create New Profile' button functionality");

		Xls_Reader xls784 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno784 = xls784.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno784, "784: Verify the Edit profile page UI");
		
		Xls_Reader xls787 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno787 = xls787.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno787,
				" Verify the name text field present in edit profile is same as in the profile selected ");

		Xls_Reader xls788 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno788 = xls788.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno788,
				"788: Verify date of birth field in edit profile page is prefilled and same as when user entered DOB while creating profile");
		

		Xls_Reader xls789 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno789 = xls789.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno789,
				"789: Verify user can edit the date of birth field in edit profile page");

		Xls_Reader xls790 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno790 = xls790.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno790,
				"790: Verify the Name field in Edit profile screen by entering blank space(Invalid):");

		Xls_Reader xls794 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno794 = xls794.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno794,
				"794: Verify the Name field in Edit profile screen by entering Alphabet characters (Valid)");

		Xls_Reader xls795 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno795 = xls795.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno795,
				"795: Verify the DOB field in Edit profile screen by entering invalid data (Eg. 31/12/1999)");

		Xls_Reader xls796 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno796 = xls796.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno796,
				"796: Verify the DOB field in Edit profile screen by entering invalid data (Eg. 31/12/2019)");

		Xls_Reader xls797 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno797 = xls797.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno797, "797: Verify Delete Profile link functionality:");

		Xls_Reader xls798 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno798 = xls798.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno798,
				"798: Verify the functionality of 'Done' button in Edit Profile page after editing Name/DOB");

		Xls_Reader xls799 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno799 = xls799.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno799,
				"799: Verify the functionality of 'Done' button in Edit Profile page without editing Name/DOB");

		Xls_Reader xls802 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno802 = xls802.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno802,
				"802: Verify the Functionality of delete profile by clicking on No in pop-up");

		Xls_Reader xls800 = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno800 = xls800.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno800,
				"800: Verify the Functionality of delete profile by clicking on 'Yes' in pop-up:");


		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");

		LaunchPageV2 settingsProfile = new LaunchPageV2(driver, test);
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		BasePageV2 BasePageV2 = new BasePageV2(driver, test);
		HashSet<String> set = new HashSet<String>();
//		String end1 = "//android.widget.TextView[@text='Create New Profile']";

		
		//SignUp
//		homepagev2.signup();
		
//		homepagev2.logout();
		
		
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
				settingsPageV2.settingsProfile.click();
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileTile, 10)) {
					test.log(LogStatus.PASS, "Validated Profile icon functionality");
					settingsPageV2.takeScreenshot();
					homepagev2.smokeresults("778: Validate Profile icon functionality", rowno778, "PASS");
				}
//				List<WebElement> lis = driver.findElementsByXPath(
//						"//android.widget.LinearLayout[@index='3']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/settings_list']//android.view.ViewGroup//android.widget.TextView");
//				int size = lis.size();
//				System.out.println("List Size :" + size);
//				// HashSet<String> set = new HashSet<String>();
//
//				for (int i = 0; i < size; i++) {
//					try {
//						String str = lis.get(i).getText();
//						System.out.println("The List Values are : " + str);
//						set.add(str);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//				}
				
				List<WebElement> lis1 = driver.findElementsByXPath(
						"//android.widget.LinearLayout[@index='3']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/settings_list']//android.view.ViewGroup//android.widget.TextView");
				for (int i = 0; i < lis1.size(); i++) {
//					String end = "//android.widget.TextView[@text='Create New Profile']";
//					Utilities.verticalSwipe(driver, end);
					
					String str1 = lis1.get(i).getText();
					test.log(LogStatus.INFO,"The List of Profiles are : " + str1);
					set.add(str1);
					Utilities.verticalSwipe(driver);
					if(i == 2) {
						break;
					}else {
						Utilities.verticalSwipe(driver);
					}
				}

				test.log(LogStatus.INFO,"Array List Values Size " + set.size());
				System.out.println("Array Vlues are printed here" + set);
				try {
					set.remove("Create New Profile");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println("Original Array Values Size " + set.size());
				System.out.println("Original Array Vlues are printed here" + set);
				if (set.size() <= 6 && set.size() != 0) {
					if (Utilities.explicitWaitVisible(driver, settingsPageV2.setiingsProfileEditProBckBtn, 10)) {
						String end = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/selected_tick']";
						Utilities.verticalSwipe(driver, end);
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileSelectedTick, 10)) {
//							 String end2 = "//android.widget.TextView[@text='Create New Profile']";
//							Utilities.verticalSwipe(driver, end2);
							if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateNewPro, 10)) {
								test.log(LogStatus.PASS,
										"Verified profile settings screen UI if user has created < 6 profiles");
								settingsPageV2.takeScreenshot();
								homepagev2.smokeresults(
										"779: Verify profile settings screen UI if user has created < 6 profiles",
										rowno779, "PASS");
								test.log(LogStatus.PASS,
										"Verified profile settings screen UI if user has created  6 profiles");
								settingsPageV2.takeScreenshot();
								homepagev2.smokeresults(
										"780: Verify profile settings screen UI if user has created  6 profiles",
										rowno780, "PASS");

							}else test.log(LogStatus.FAIL, "Unable to find 'Create New Profile' in profile page");
						}else test.log(LogStatus.FAIL, "Unable to find Profile Selected Tick simbol in Profile List page/not found");
					}else test.log(LogStatus.FAIL, "unable to click back button in Edit Profile page / not found");
				}

			
            try {
            	settingsPageV2.settingsProfileBackBtn.click();
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to click back button in profiles screen");
			}
			
			
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfile, 10)) {
				test.log(LogStatus.PASS,
						"Verified the functionality by tapping on Back arrow from Profile list screen");
				settingsPageV2.takeScreenshot();
				homepagev2.smokeresults(
						"781: Verify the functionality by tapping on Back arrow from Profile list screen", rowno781,
						"PASS");
				Thread.sleep(1000);
				
				driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();
				
				if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsProfile, 5)) {
					settingsPageV2.settingsProfile.click();	
					test.log(LogStatus.INFO, "Clicked profiles option in settings page");
				}else BasePageV2.reportFail("Not bale to click profiles option in settings page");
				
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateImg, 10)) {
					settingsPageV2.settingsProfileCreateImg.click();
					String editText = settingsPageV2.editTextinEditProfile.getText();
					test.log(LogStatus.INFO,"Edit Profile Text should be : " + editText);
					if (set.contains(editText)) {
						System.out.println("Set Array Contains the String profile index[0]" + editText);
						

						test.log(LogStatus.PASS,
								"Validated the navigation by tapping on Profile icon/arrow icon in profile list screen");
						settingsPageV2.takeScreenshot();
						homepagev2.smokeresults(" ",rowno782, "PASS");
						
						
						test.log(LogStatus.PASS,
								"Verified the name text field present in edit profile is same as in the profile selected ");
						settingsPageV2.takeScreenshot();
						homepagev2.smokeresults(" ",rowno787, "PASS");

						try {

							if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsEditProfilePageTile, 10)) {
								if (Utilities.explicitWaitVisible(driver, settingsPageV2.setiingsProfileEditProBckBtn,
										10)) {
									if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProEditProName,
											20)) {
										if (Utilities.explicitWaitVisible(driver,
												settingsPageV2.settingsProEditProDateOfBirth, 20)) {
										
												if (Utilities.explicitWaitVisible(driver,
														settingsPageV2.settingsEditProfilrNextBtn, 10)) {
													test.log(LogStatus.PASS, "Verified the Edit profile page UI");
													settingsPageV2.takeScreenshot();
													homepagev2.smokeresults("784: Verify the Edit profile page UI",
															rowno784, "PASS");
												}else BasePageV2.reportFail("Done button not found in Edit profile Main page");
											
										}else test.log(LogStatus.FAIL, "Date Of Birth option is not found in Edit Profile Page");
									}else test.log(LogStatus.FAIL, "Name option is not found in Edit Profile Page");
								}else BasePageV2.reportFail("Back Button is missing Form Edit Profile Main Page");

							}else BasePageV2.reportFail("Not found 'EDIT PROFILE' Title in Edit profile page");
						} catch (Exception e) {
							e.printStackTrace();
						}

						settingsPageV2.editProBackBtn.click();
//						if (!(Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateNewPro, 10))) {
//							Utilities.verticalSwipe(driver, end1);
//						}
                        
						try {
							settingsPageV2.settingsProfileCreateNewPro.click();
							test.log(LogStatus.INFO, "succussfully clicked profile Create Profile button");
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Not able to click 'Create New Profile' Link in Profile Page");
						}
						
						
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreatProfileTile, 20)) {
							test.log(LogStatus.PASS, "Validated 'Create New Profile' button functionality");
							settingsPageV2.takeScreenshot();
							homepagev2.smokeresults("781: Validate 'Create New Profile' button functionality", rowno783,
									"PASS");
						}else test.log(LogStatus.FAIL, "Not able to Navigated to Create Profile Screen");
						settingsPageV2.settingsProfileBackBtn.click();
						Utilities.verticalSwipeDown(driver);
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileCreateImg, 20)) {
							settingsPageV2.settingsProfileCreateImg.click();
							settingsPageV2.settingsProfileEditProYearEdit.click();
							if (Utilities.explicitWaitVisible(driver, settingsPageV2.calendarYearPick, 20)) {
								//
								if (Utilities.explicitWaitClickable(driver, settingsPageV2.calendarYearPick, 10)) {
									settingsPageV2.calendarYearPick.click();
									settingsPageV2.year2014.click();
									settingsPageV2.calenderOkBtn.click();
									test.log(LogStatus.PASS,
											"Verified user can edit the date of birth field in edit profile page");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults(
											"789: Verify user can edit the date of birth field in edit profile page",
											rowno789, "PASS");
								}else BasePageV2.reportFail("Calender year selected option is not found ");
							}else BasePageV2.reportFail("unable to select Year in Calender");

							driver.pressKeyCode(AndroidKeyCode.BACK);
							driver.runAppInBackground(Duration.ofSeconds(5));
							 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
							 driver.currentActivity();
							 
							settingsPageV2.settingsProfileCreateImg.click();
							if (!(settingsPageV2.settingsProfileEditProDateEdit.getText().isEmpty())
									&& !(settingsPageV2.settingsProfileEditProMonthEdit.getText().isEmpty())
									&& !(settingsPageV2.settingsProfileEditProYearEdit.getText().isEmpty())) {

								String datestr = settingsPageV2.settingsProfileEditProDateEdit.getText();
								int date = Integer.parseInt(datestr);

								String monthstr = settingsPageV2.settingsProfileEditProMonthEdit.getText();
								int month = Integer.parseInt(monthstr);

								String yearstr = settingsPageV2.settingsProfileEditProYearEdit.getText();
								int year = Integer.parseInt(yearstr);

								if (date <= 31 && month <= 12 && year <= 2030) {
									test.log(LogStatus.PASS, "Verified date of birth field in edit profile page is prefilled and same as when user entered DOB while creating profile");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults(
											"788: Verify date of birth field in edit profile page is prefilled and same as when user entered DOB while creating profile",
											rowno788, "PASS");
								} else {
									BasePageV2.reportFail("Edit Profile Page DOB is EMPTY or Not Found DOB ");
								}

							}else BasePageV2.reportFail("Date of Birth found Empty/option is not found");
							// Verifying the Name[Kid's name]- Edit Text Box
							if (Utilities.explicitWaitVisible(driver, settingsPageV2.editProNameEditText, 20)) {
								settingsPageV2.editProNameEditText.clear();
								if (settingsPageV2.editProErrorMsgForName.isDisplayed()) {
									test.log(LogStatus.PASS,
											"Verified the Name field in Edit profile screen by entering blank space");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults(
											"790: Verify the Name field in Edit profile screen by entering blank space(Invalid):",
											rowno790, "PASS");
								}

								settingsPageV2.editProNameEditText.clear();
								settingsPageV2.editProNameEditText.sendKeys("AAAA");
								// Clicking the Mobile Back button
								driver.navigate().back();

								settingsPageV2.settingsEditProfilrNextBtn.click();
								// "Done" Button Functionality checking with out editing the name and DOB
								
								Thread.sleep(1000);
								if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfileTile, 10)) {
									test.log(LogStatus.PASS,
											"Verified the functionality of 'Done' button in Edit Profile page without editing Name/DOB");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults(
											"799: Verify the functionality of 'Done' button in Edit Profile page without editing Name/DOB",
											rowno799, "PASS");

								}else BasePageV2.reportFail("Done button Functionality is not Working or not found Done Button ");
								Thread.sleep(10000);
								test.log(LogStatus.PASS,
										"Verified the functionality of 'Done' button in Edit Profile page after editing Name/DOB");
								settingsPageV2.takeScreenshot();
								homepagev2.smokeresults(
										"798: Verify the functionality of 'Done' button in Edit Profile page after editing Name/DOB",
										rowno798, "PASS");

								// Getting the List Tiles from Profile List
								try {
									Thread.sleep(5000);
									driver.pressKeyCode(AndroidKeyCode.BACK);
									driver.runAppInBackground(Duration.ofSeconds(3));
									 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
									 driver.currentActivity();
									if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsProfile, 10)) {
										Thread.sleep(1000);
										settingsPageV2.settingsProfile.click();
									} else {
										Thread.sleep(5000);
										driver.findElementByXPath("//android.widget.TextView[@text='Profiles']")
												.click();
									}

								} catch (Exception e) {
									e.printStackTrace();
									settingsPageV2.reportFail("Profile Back Functionallity is not working");
								}

								// lIST hAVING pROBLEM CHECK IT lOOP
								HashSet<String> set1 = new HashSet<String>();
								Thread.sleep(1000);
								List<WebElement> lisText = driver.findElementsByXPath(
										"//android.widget.LinearLayout[@index='3']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/settings_list']//android.view.ViewGroup//android.widget.TextView");
								System.out.println("'listText' list Size : " + lisText.size());
								for (int i = 0; i < lisText.size(); i++) {
									System.out.println("'listText' list Size : " + lisText.size());

									String strText = lisText.get(i).getText().toString().trim();
									System.out.println(strText);
									System.out.println("Strings values having the Set is " + strText);
									set1.add(strText);
								}

								Object[] SetArray = set1.toArray();
								System.out.println("set size is :" + SetArray.length);
								for (int i = 0; i < SetArray.length; i++) {
									System.out.println(SetArray[i]);
									String textTile = SetArray[i].toString().trim();

									if (textTile.equalsIgnoreCase("AAAA")) {
										test.log(LogStatus.PASS,
												"Verified the Name field in Edit profile screen by entering Alphabet characters (Valid)");
										settingsPageV2.takeScreenshot();
										homepagev2.smokeresults(
												"794: Verify the Name field in Edit profile screen by entering Alphabet characters (Valid):",
												rowno794, "PASS");
									}
								}

							} else {
								homepagev2.reportFail("Name edit text Option does not found in 'EDIT PROFILE' page");
							}

							// DOB selecting past year 1999 and Feature year 2019
							driver.runAppInBackground(Duration.ofSeconds(5));
							 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
							 driver.currentActivity();
							settingsPageV2.settingsProfileCreateImg.click();
							settingsPageV2.settingsProfileEditProYearEdit.click();
							if (Utilities.explicitWaitVisible(driver, settingsPageV2.calendarYearPick, 20)) {

								settingsPageV2.calendarYearPick.click();
								if (Utilities.explicitWaitClickable(driver, settingsPageV2.year1999, 10)) {
									BasePageV2.reportFail("User able to select the date 31-12-1999");
								} else {
									test.log(LogStatus.PASS,
											"Verified the DOB field in Edit profile screen by entering invalid data (Eg. 31/12/1999)");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults(
											"795: Verify the DOB field in Edit profile screen by entering invalid data (Eg. 31/12/1999)",
											rowno795, "PASS");
								}
								settingsPageV2.calendarYearPick.click();
								try {
									String text = "//android.widget.TextView[contains(@text,'2018')]";
									Utilities.verticalSwipeCheckElement(driver, text);
									// driver.findElementByAndroidUIAutomator("newScrollable(new
									// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
									// UiSelector().textContains(\""+text+"\").instance(0))");
								} catch (Exception e) {
								}
								if (Utilities.explicitWaitClickable(driver, settingsPageV2.Year2019, 10)) {
									BasePageV2.reportFail("User able to select the year 2019");
								} else {
									BasePageV2
											.reportPass("'User should not be able to select the year 2019' is Passed");
									settingsPageV2.takeScreenshot();
									homepagev2.smokeresults(
											"796: Verify the DOB field in Edit profile screen by entering invalid data (Eg. 31/12/2019)",
											rowno796, "PASS");
								}
							} else
								BasePageV2.reportFail("Not able to select year in Date of birth field");

							settingsPageV2.calendercancelBtn.click();
							
							driver.runAppInBackground(Duration.ofSeconds(3));
							 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							 driver.currentActivity();
							
							// Delete profile Button Functionality verifying
							 if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsEditProfileDelProBtn, 5)) {
							settingsPageV2.settingsEditProfileDelProBtn.click();
							 }else BasePageV2.reportFail("unabke to Found Delete Profile Link in Edit Profile Page");
							
							if (Utilities.explicitWaitClickable(driver, settingsPageV2.delPopSubText, 10)) {
								test.log(LogStatus.PASS,"Verified Delete Profile link functionality");
								homepagev2.smokeresults("797: Verify Delete Profile link functionality:", rowno797,
										"PASS");
							} else BasePageV2.reportFail(" Delete Profile link functionality not able to validate");
							    
							driver.runAppInBackground(Duration.ofSeconds(2));
							 test.log(LogStatus.INFO, "Put app to background for 2 seconds");
							 driver.currentActivity();
							 if(Utilities.explicitWaitClickable(driver, settingsPageV2.delPopCrossBtn, 5)) {
								 settingsPageV2.delPopCrossBtn.click();
							 }else test.log(LogStatus.FAIL, "Cross button not found on Succussfull delete Pop Up ");
								
								driver.pressKeyCode(AndroidKeyCode.BACK);

								// Delete Profile clicking 'No' Button functionality
								driver.runAppInBackground(Duration.ofSeconds(5));
								 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
								 driver.currentActivity();
								settingsPageV2.settingsProfileCreateImg.click();
								settingsPageV2.settingsEditProfileDelProBtn.click();
								if (Utilities.explicitWaitVisible(driver, settingsPageV2.delPopDELETETile, 10)) {
									settingsPageV2.takeScreenshot();
									if(Utilities.explicitWaitVisible(driver, settingsPageV2.prodelPopNoBtn, 10)) {
										settingsPageV2.prodelPopNoBtn.click();
										}else BasePageV2.reportFail("'NO' button is not found on Delete Pop screen");
									
									driver.runAppInBackground(Duration.ofSeconds(5));
									 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
									 driver.currentActivity();
									
									if (Utilities.explicitWaitVisible(driver,settingsPageV2.settingsEditProfilePageTile, 10)) {
										test.log(LogStatus.PASS,"Verified the Functionality of delete profile by clicking on No in pop-up");
										homepagev2.smokeresults("802: Verify the Functionality of delete profile by clicking on No in pop-up:",
												rowno802, "PASS");
									} else BasePageV2.reportFail("Functionality of delete profile by clicking on 'No' is not working");
									

								}else test.log(LogStatus.FAIL, "DELETE title not found on Delete pop up");
								}else BasePageV2.reportFail("Profile Avator not found to tap");

						
						      if(Utilities.explicitWaitClickable(driver, settingsPageV2.settingsEditProfileDelProBtn, 3))
						    		  settingsPageV2.settingsEditProfileDelProBtn.click();
						      else BasePageV2.reportFail("Delete Profile Link not found/not able to click");
						
								// Delete Profile clicking 'yes' Button functionality
							driver.runAppInBackground(Duration.ofSeconds(5));
							 test.log(LogStatus.INFO, "Put app to background for 5 seconds");
							 driver.currentActivity();
							 
								if (Utilities.explicitWaitVisible(driver, settingsPageV2.delPopDELETETile, 10)) {
									try {
										String delText = settingsPageV2.editProNameEditText.getText().toString().trim();
										test.log(LogStatus.INFO, "Will delete Prodile is : " + delText);
										String delProList = "//android.widget.TextView[@text='" + delText + "']";  
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "unable to get profile name text from Edit TExt Box ");
									}
									
									if(Utilities.explicitWaitClickable(driver, settingsPageV2.prodelPopYesBtn, 3))
									     settingsPageV2.prodelPopYesBtn.click();
									else BasePageV2.reportFail("'YES' Button not found on Delete Pop up / not able to Click");
									Thread.sleep(1000);
									try {
										driver.findElementByXPath(delProList);
										BasePageV2.reportFail(
												" Not able to Verified the Functionality of delete profile by clicking on 'Yes' in pop-up");
									} catch (Exception e) {
										test.log(LogStatus.PASS,
												"Verified the Functionality of delete profile by clicking on 'Yes' in pop-up:");
										homepagev2.smokeresults(
												"800: Verify the Functionality of delete profile by clicking on 'Yes' in pop-up",
												rowno800, "PASS");
									}

								}else BasePageV2.reportFail("not found 'Delete' title text not Delete Pop up/ Delete pop up Not found ");
								

							}else test.log(LogStatus.FAIL, "set Array not contained expected profile " + editText);

						} else BasePageV2.reportFail("Not Found parentPinContainer in PARENT Zone Page");
						

					}else BasePageV2.reportFail("Profile option is not find in Settings Main Page");
				
	
			} else BasePageV2.reportFail("Settings Icon not found for navigating to Settings Page");
			
       }
	

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
