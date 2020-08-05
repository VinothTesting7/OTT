package com.viacom.profile;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class VootKidsCreateProfileScreenFunctionalityTest extends BaseTestV2{
	String testName = "VootKidsCreateProfileScreenFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Voot Kids Create Profile screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Create Profile Screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int fivecharrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",fivecharrow , "Verify the favourites selection functionality in the favourites/Preferences screen  by selecting 5 favourites characters/preferences:(Valid)");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int fourcharrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",fourcharrow , "Verify the favourites selection functionality in the favourites/Preferences screen  by selecting less than or equal to 4 favourites characters/preferences:(invalid)");	
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int sixcharrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",sixcharrow, "Verify the favourites selection functionality in the favourites/Preferences screen by selecting 6 favourites characters/preferences:(Valid)");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int backrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",backrow, "Verify the back button in the favourites/Preferences screen:");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int switchprofilerow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",switchprofilerow , "Verify the back arrow functionality in Create Profile 1st screen:");	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int blanknamerow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",blanknamerow , "Verify the Name field in Create Profile screen by entering blank space(Invalid):");	
	
	
	Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int kidsnamevalidrow=xls7.getRowCount("Smoke_Results")+1;
	xls7.setCellData("Smoke_Results", "Testcase Name",kidsnamevalidrow , "Verify the Name field in Create Profile screen by entering more than or equal to 2 characters:(Valid)");	
	
	Xls_Reader xls8 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int kidsnamespacerow=xls8.getRowCount("Smoke_Results")+1;
	xls8.setCellData("Smoke_Results", "Testcase Name",kidsnamespacerow , "Verify the Name field in Create Profile screen by entering spaces between each characters(Valid):");	
	
	Xls_Reader xls9 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int kidsnamespaceInFnLnrow=xls9.getRowCount("Smoke_Results")+1;
	xls9.setCellData("Smoke_Results", "Testcase Name",kidsnamespaceInFnLnrow , "Verify the Name field in Create Profile screen by entering spaces between first name and last name(Valid):");	
	
	
	Xls_Reader xls10 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int dobrow=xls10.getRowCount("Smoke_Results")+1;
	xls10.setCellData("Smoke_Results", "Testcase Name",dobrow , "Verify the click functionality on the DOB field:");	
	

	
	Xls_Reader xls12 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int invaliddobrow=xls12.getRowCount("Smoke_Results")+1;
	xls12.setCellData("Smoke_Results", "Testcase Name",invaliddobrow , "Verify the DOB field in Create Profile screen by selecting the current/today's date(Valid):");	
	
	Xls_Reader xls13 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int nextbuttonrow=xls13.getRowCount("Smoke_Results")+1;
	xls13.setCellData("Smoke_Results", "Testcase Name",nextbuttonrow , "Verify the Next button functionality without filling the mandatory details in to the Create Profile screen:(Invalid)");	
	
	Xls_Reader xls14 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int validnextbuttonrow=xls14.getRowCount("Smoke_Results")+1;
	xls14.setCellData("Smoke_Results", "Testcase Name",validnextbuttonrow , "Verify the Next button functionality after filling all the mandatory fields:");	
	
	Xls_Reader xls15 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int firstscreenrow=xls15.getRowCount("Smoke_Results")+1;
	xls15.setCellData("Smoke_Results", "Testcase Name",firstscreenrow , "Verify the back button in the  2nd screen of Create profile (Avatar selection screen):");	
	
	
		Xls_Reader xls16 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int avatarscreenrow=xls16.getRowCount("Smoke_Results")+1;
	xls16.setCellData("Smoke_Results", "Testcase Name",avatarscreenrow , "Verify the Next button functionality in the avatar selection screen:");	
	

	Xls_Reader xls17 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
    int letsgorow=xls17.getRowCount("Smoke_Results")+1;
    xls17.setCellData("Smoke_Results", "Testcase Name",letsgorow , "Verify the Let`s Go button functionality in Final(last) screen:");	
	
	Xls_Reader xls18 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
    int hyperlinkrow=xls18.getRowCount("Smoke_Results")+1;
    xls18.setCellData("Smoke_Results", "Testcase Name",hyperlinkrow , "Verify the functionality of Create another hyperlink in Final(last) screen:");	
    
	Xls_Reader xls19 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
    int notdeleterow=xls19.getRowCount("Smoke_Results")+1;
    xls19.setCellData("Smoke_Results", "Testcase Name",notdeleterow , "Verify the Functionality of delete profile by clicking on No in pop-up");	
    
    Xls_Reader xls20 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
    int yesdeleterow=xls20.getRowCount("Smoke_Results")+1;
    xls20.setCellData("Smoke_Results", "Testcase Name",yesdeleterow , "Verify the Functionality of delete profile by clicking on 'Yes' in pop-up:");	
   
    Xls_Reader xls21 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
    int specialcharrow=xls21.getRowCount("Smoke_Results")+1;
    xls21.setCellData("Smoke_Results", "Testcase Name",specialcharrow , "Verify the Functionality of delete profile by clicking on 'Yes' in pop-up:");	
   
    Xls_Reader xls22 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
    int numberrow=xls22.getRowCount("Smoke_Results")+1;
    xls22.setCellData("Smoke_Results", "Testcase Name",numberrow , "Verify the Name field in Create Profile screen by entering Numbers(Invalid):");	
   
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	
	
	String un=data.get("Email");
	String pwd=data.get("Password");
	
homepagev2.signup();
     //launchpagev2.loginToVoot(un,pwd);
	//launchpagev2.registerWithoutMobileNumberSubscription();
	//driver.startActivity(new Activity("com.tv.vootkids.ui.home", "VKHomeActivity"));
	//clicking on user profile
	
	if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 10))
		homepagev2.profilepic.click();

	test.log(LogStatus.INFO, "Navigating to Create New Profile screen");
	
	//Click on Create new profile icon at Switch profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createNewIcon, 20))
		launchpagev2.createNewIcon.click();
	else
		BasePageV2.reportFail("Not able to click on Create new Link");
	

	//Enter Pin
	
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 20))
	{
		launchpagev2.parentPinContainer.clear();
		Thread.sleep(2000);
		launchpagev2.parentPinContainer.sendKeys("1111");
		try{
			driver.hideKeyboard();
			launchpagev2.parentPinContainer.sendKeys("1111");
		}
		catch(Exception e){}
	}
	
	//take snap to check navigated to next screen
	//BasePageV2.takeScreenshot();
	test.log(LogStatus.INFO, "Clicking on back button to navigate back to Switch Profile Screen");
	
	//Click back and check whether navigating to Switch Profile screen
	if(Utilities.explicitWaitClickable(driver, launchpagev2.createProfileBackButton, 20))
		launchpagev2.createProfileBackButton.click();
	else
		BasePageV2.reportFail("Not able to click back button in Create Profile first Screen");
	
	//Switch profile heading visible or not
	if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenToolBarTitle, 20))
		{
		test.log(LogStatus.PASS,"Testcase : 'Verify the back arrow functionality in Create Profile 1st screen' is passed");
		BasePageV2.smokeresults("", switchprofilerow, "Pass");
		}
	else
		BasePageV2.reportFail("Not navigated back to Switch Profile Screen");
	
	//Click on Create new profile icon at Switch profile screen
		if(Utilities.explicitWaitVisible(driver, launchpagev2.createNewIcon, 20))
			launchpagev2.createNewIcon.click();
		else
			BasePageV2.reportFail("Not able to click on Create new Link");
	
		
		
		
		
		//Enter Pin
		
		
		if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 20))
			{
			launchpagev2.parentPinContainer.clear();
			Thread.sleep(2000);
			launchpagev2.parentPinContainer.sendKeys("1111");
			try{
				driver.hideKeyboard();
				launchpagev2.parentPinContainer.sendKeys("1111");
			}
			catch(Exception e){}
			}
		/*else
			BasePageV2.reportFail("Parent Pin screen is not displayed");*/
		
		
		//Blank name and dob
		test.log(LogStatus.INFO,"Tapping on Next button without entering in Kids name and Dob field");
		
		if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
			launchpagev2.createProfileScreenNextButton.click();
		else
			BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
		
		
		//Check error msg and next button functionality
		
		test.log(LogStatus.INFO,"Tapping on Next button without entering in Kids name and Dob field");
		
		if(Utilities.explicitWaitVisible(driver, launchpagev2.enterNameErrorMessage, 20))
		{
		test.log(LogStatus.PASS,"Testcase : 'Verify the Next button functionality without filling the mandatory details in to the Create Profile screen:(Invalid)' is passed");
		BasePageV2.smokeresults("", nextbuttonrow, "Pass");
		}
	else
		BasePageV2.reportFail("'Please enter Name' error message is not displayed when tapping next button without entering In kids name field and Dob field");
	
		
		
		//blank name testcase
		
		test.log(LogStatus.INFO, "Leaving kids name field as blank (Invalid)");
		//Select Dob	valid
		if(Utilities.explicitWaitVisible(driver, launchpagev2.dobProfileField, 20))
			launchpagev2.dobProfileField.click();
		else
			BasePageV2.reportFail("Not able to select Date of birth in Create Profile screen");
		
		//Invalid date - Today date selection
		
//scripting progress
		if(Utilities.explicitWaitVisible(driver, launchpagev2.calendarYearPick, 20))
		{
			launchpagev2.calendarYearPick.click();
			try{
				String text="//android.widget.TextView[contains(@text,'2018')]";
				Utilities.verticalSwipe(driver, text);
		//		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
			}
			catch(Exception e){}
			if(Utilities.explicitWaitVisible(driver, launchpagev2.currentYear, 5))
				launchpagev2.currentYear.click();
		}
		else
			BasePageV2.reportFail("Not able to select Date of birth in Create Profile screen");
		
				
		//Click ok in calendar pop up
		if(Utilities.explicitWaitClickable(driver, launchpagev2.calendarOkButton, 20))
			{
			 test.log(LogStatus.PASS,"Testcase : 'Verify the click functionality on the DOB field:' is passed");
			 BasePageV2.smokeresults("", dobrow, "Pass");
			 launchpagev2.calendarOkButton.click();
			}
		else
			BasePageV2.reportFail("Not able to click Ok button on calendar pop up");
		
		//Check error message displayed in date field
		if(!Utilities.explicitWaitVisible(driver, launchpagev2.ageAboveZeroErrorMessage, 5))
		{
		
		 test.log(LogStatus.PASS,"Testcase : 'Verify the DOB field in Create Profile screen by selecting the current/today's date(Valid):' is passed");
		 BasePageV2.smokeresults("", invaliddobrow, "Pass");
		
		}
	/*else
		BasePageV2.reportFail("Error message is displayed");	*/
		
		
		//Select Dob	valid
		if(Utilities.explicitWaitVisible(driver, launchpagev2.dobProfileField, 20))
			launchpagev2.dobProfileField.click();
		else
			BasePageV2.reportFail("Not able to select Date of birth in Create Profile screen");
		
		if(Utilities.explicitWaitVisible(driver, launchpagev2.calendarOkButton, 20))
		{
		 test.log(LogStatus.PASS,"Testcase : 'Verify the click functionality on the DOB field:' is passed");
		 BasePageV2.smokeresults("", dobrow, "Pass");
		 launchpagev2.calendarOkButton.click();
		}
	else
		BasePageV2.reportFail("Not able to click Ok button on calendar pop up");
		Thread.sleep(1000);
	//	driver.pressKeyCode(AndroidKeyCode.BACK);		
		//Special Characters testcase 		
		//Click on next button in create profile screen
		
		
		if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
			launchpagev2.createProfileScreenNextButton.click();
		else
			BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
		

		// Check whether Please enter name error message displayed
		if(Utilities.explicitWaitVisible(driver, launchpagev2.enterNameErrorMessage, 20))
			{
			test.log(LogStatus.PASS,"Testcase : 'Verify the Name field in Create Profile screen by entering blank space(Invalid):' is passed");
			BasePageV2.smokeresults("", blanknamerow, "Pass");
			}
		else
			BasePageV2.reportFail("'Please enter Name' error message is not displayed when leaving Kids name field blank");
		
		
		
		
		
		
		
		
		//Enter invalid name - spl characters & numbers
		
				if(Utilities.explicitWaitVisible(driver, launchpagev2.kidsNameField, 20))
				{
					launchpagev2.kidsNameField.sendKeys("Vinoth@123");
					if(launchpagev2.kidsNameField.getText().contains("@"))
					{
						test.log(LogStatus.FAIL, "Able to send special charcters in Kids name text field");
					}
					else
					{
						test.log(LogStatus.PASS, "Testcase : 'Verify the Name field in Create Profile screen by entering special characters(Invalid):' is Passed");
					    BasePageV2.smokeresults("", specialcharrow, "Pass");
					}
						
					if(launchpagev2.kidsNameField.getText().contains("123"))
					{
						test.log(LogStatus.FAIL, "Able to send numbers in Kids name text field");
					}
					else
					{
						test.log(LogStatus.PASS, "Testcase : 'Verify the Name field in Create Profile screen by entering Numbers(Invalid):' is Passed");
					    BasePageV2.smokeresults("", numberrow, "Pass");
					}
					
					
					
				}
				else
					BasePageV2.reportFail("Kids name field is not displayed in Create Profile screen");
				
				
				
			// Enter Kids name in Kids name field
			if(Utilities.explicitWaitVisible(driver, launchpagev2.kidsNameField, 20))
				{
				launchpagev2.kidsNameField.clear();
				launchpagev2.kidsNameField.sendKeys("VinothTest");
				}
			else
				BasePageV2.reportFail("Kids name field is not displayed in Create Profile screen");
	driver.pressKeyCode(AndroidKeyCode.BACK);
	
	test.log(LogStatus.INFO, "Entering all valid data and navigating to next screen");
	//BasePageV2.takeScreenshot();
	//Click on next button in create profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		launchpagev2.createProfileScreenNextButton.click();
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	
	//Check whether navigated to create ur buddy screen
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createYourBuddyScreen, 20))
	{
		test.log(LogStatus.PASS,"Verify the Next button functionality after filling all the mandatory fields:");
		BasePageV2.smokeresults("", validnextbuttonrow, "Pass");
	}
	else
		BasePageV2.reportFail("Not Navigated to Create Your Buddy after filling all mandatory fields and clicking on next button");
	

	
	//Click on back button and verify navigated to first screen
	if(Utilities.explicitWaitClickable(driver, launchpagev2.createProfileBackButton, 20))
		launchpagev2.createProfileBackButton.click();
	else
		BasePageV2.reportFail("Not able to click back button in Create Profile -second Screen - create ur buddy screen");
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileTellUsFirstScreen, 20))
	{
		test.log(LogStatus.PASS,"Testcase : 'Verify the back button in the  2nd screen of Create profile (Avatar selection screen):' is passed");
		BasePageV2.smokeresults("", firstscreenrow, "Pass");
	}
	else
		BasePageV2.reportFail("Not Navigated back to Create Profile First screen after clicking back button from second Screen - create ur buddy screen");
	
	
	////Click on next button(1) in create profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
	{
		launchpagev2.createProfileScreenNextButton.click();
	}
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	
	////Click on next button(2) in create profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
	{
		launchpagev2.createProfileScreenNextButton.click();
	}
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	test.log(LogStatus.PASS,"Testcase : 'Verify the Name field in Create Profile screen by entering more than or equal to 2 characters:(Valid)' is passed");
	BasePageV2.smokeresults("", kidsnamevalidrow, "Pass");
	//Click on back button and check whether navigated back
	
	if(Utilities.explicitWaitClickable(driver, launchpagev2.createProfileBackButton, 20))
		launchpagev2.createProfileBackButton.click();
	else
		BasePageV2.reportFail("Not able to click back button in Favorites/preferences  Screen");
	
	//check whether create your buddy text is present or not after navigating back
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileSelectAvatarScreen, 20))
	{
		test.log(LogStatus.PASS,"Testcase : 'Verify the back button in the favourites/Preferences screen' is passed ");
		BasePageV2.smokeresults("", backrow, "Pass");
	}
	else
		BasePageV2.reportFail("Its not navigated back to select Avatar screen after clicking back from favorites/preferences screen");
	
	//Click on next button to proceed
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		launchpagev2.createProfileScreenNextButton.click();
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	

	
	//Check whether navigated to next screen favorites selection screen 
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileSelectFavoritesPreferencesScreen, 20))
		{
		test.log(LogStatus.PASS,"Testcase : 'Verify the Next button functionality in the avatar selection screen:' is passed");
		BasePageV2.smokeresults("", avatarscreenrow, "Pass");
		}
	else
		BasePageV2.reportFail("Not navigated to Favorites/Preferences screen after selecting avatar and clicking next button");
	
	
	//Select any skill  (Story skill)
	/*if(Utilities.explicitWaitVisible(driver, launchpagev2.skillSetIcon, 20))
		launchpagev2.skillSetIcon.click();
	else
		BasePageV2.reportFail("Skills are not displayed in Create Profile Screen");*/
	
	List<WebElement> icons = launchpagev2.characterSetIcons;
	//selecting 4 character icons
	if(Utilities.explicitWaitVisible(driver, launchpagev2.characterSetIcon, 20))
	{
		
		for(int i=0;i<=3;i++)
		{
			try{
				icons.get(i).click();
			}
			catch(Exception e)
			{
				BasePageV2.reportFail("Not able to click Character Icon - "+(i+1));
			}
		}
	}
	else
		BasePageV2.reportFail("Characters set are not displayed in Create Profile Screen");
	
	test.log(LogStatus.INFO, "Selecting 4 favorite characters and click on Next button");
	//BasePageV2.takeScreenshot();
	
//Click on next button(3) in create profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		launchpagev2.createProfileScreenNextButton.click();
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	
	test.log(LogStatus.INFO, "Clicking on Next button and verifying whether not able to proceed to Next screen");
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.profileCompleteLetsGoButton, 5))
	{
		BasePageV2.reportFail("Error message not displayed when clicking next without selecting 5 characters");
	}
	else
	{
		test.log(LogStatus.PASS,"Testcase : 'Verify the favourites selection functionality in the favourites/Preferences screen  by selecting less than or equal to 4 favourites characters/preferences:(invalid)' is passed");
	    BasePageV2.smokeresults("", fourcharrow, "Pass");
	}
 
	Utilities.verticalSwipe(driver);
	Utilities.verticalSwipe(driver);
	//select 4th character
	
	try{
	icons.get(2).click();
	}
	catch(Exception e)
	{
		BasePageV2.reportFail("Not able to select character");
	}
	
	//click next button
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		launchpagev2.createProfileScreenNextButton.click();
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	
	//checking for Lets go button
	if(Utilities.explicitWaitVisible(driver, launchpagev2.profileCompleteLetsGoButton, 20))
	{
		test.log(LogStatus.PASS,"Testcase : 'Verify the favourites selection functionality in the favourites/Preferences screen  by selecting 5 favourites characters/preferences:(Valid)' is passed");
	    BasePageV2.smokeresults("", fivecharrow, "Pass");
	}
	else
		BasePageV2.reportFail("Error message is displayed / Not able to create Profile when clicking next button after selecting 5 characters");
	
	
	
	test.log(LogStatus.INFO,"Clicking on Create Another hyperlink");
	//Clicking create Another  link
	if(Utilities.explicitWaitClickable(driver, launchpagev2.createAnotherProfileLink, 20))
	{
		launchpagev2.createAnotherProfileLink.click();
	}
	else
		BasePageV2.reportFail("Error message is displayed / Not able to create Profile  when clicking next button after selecting 5 characters");
	
	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 20))
		{
		launchpagev2.parentPinContainer.clear();	
		launchpagev2.parentPinContainer.sendKeys("1111");
		Thread.sleep(1000);
		try{
			driver.hideKeyboard();
			launchpagev2.parentPinContainer.sendKeys("1111");
		}
		catch(Exception e){}
		}
	//cHECKING FUNCTIONALITY of Create Another Hyperlink whether navigating to first screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileTellUsFirstScreen, 20))
	{
	 	 test.log(LogStatus.PASS,"Testcase : 'Verify the functionality of Create another hyperlink in Final(last) screen:' is passed");
	 	 BasePageV2.smokeresults("", hyperlinkrow, "Pass");
	}
	else
		BasePageV2.reportFail("Not navigated to Create Profile First Screen");
	
	//

	if(Utilities.explicitWaitVisible(driver, launchpagev2.kidsNameField, 20))
	{
	    test.log(LogStatus.INFO, "Entering the space between each characters in the Kids name field");
		launchpagev2.kidsNameField.clear();
		launchpagev2.kidsNameField.sendKeys("v i n o t h");
	}
	else
		BasePageV2.reportFail("Kids name field is not displayed in Create Profile screen");
	
	//Select Dob	
	if(Utilities.explicitWaitVisible(driver, launchpagev2.dobProfileField, 20))
		launchpagev2.dobProfileField.click();
	else
		BasePageV2.reportFail("Not able to select Date of birth in Create Profile screen");
	
	//Click ok in calendar pop up
	if(Utilities.explicitWaitVisible(driver, launchpagev2.calendarOkButton, 20))
		launchpagev2.calendarOkButton.click();
	else
		BasePageV2.reportFail("Not able to click Ok button on calendar pop up");
	
	Thread.sleep(1000);
	//driver.pressKeyCode(AndroidKeyCode.BACK);
	test.log(LogStatus.INFO, "Entering all valid data and navigating to next screen");
	//BasePageV2.takeScreenshot();
	//Click on next button in create profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		launchpagev2.createProfileScreenNextButton.click();
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	
	////Click on next button(2) in create profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		launchpagev2.createProfileScreenNextButton.click();
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileSelectFavoritesPreferencesScreen, 10))
	{	
		test.log(LogStatus.PASS,"Testcase : 'Verify the Name field in Create Profile screen by entering spaces between each characters(Valid):' is passed");
	    BasePageV2.smokeresults("", kidsnamespacerow, "Pass");
	}
	else
		BasePageV2.reportFail("Not navigated to Favorites/Preference screen");
	
	    //click back button in favorites/preferences screen
		if(Utilities.explicitWaitClickable(driver, launchpagev2.createProfileBackButton, 20))
			launchpagev2.createProfileBackButton.click();
		else
			BasePageV2.reportFail("Not able to click back button in Favorites/preferences  Screen");
	    
	    //Click back in create ur buddy screen 
		if(Utilities.explicitWaitClickable(driver, launchpagev2.createProfileBackButton, 20))
			launchpagev2.createProfileBackButton.click();
		else
			BasePageV2.reportFail("Not able to click back button in Create your Buddy  Screen");

	    
	    if(Utilities.explicitWaitVisible(driver, launchpagev2.kidsNameField, 20))
			{
	    	  test.log(LogStatus.INFO, "Entering the space between firstname and lastname in the Kids name field");
	    	  launchpagev2.kidsNameField.clear();
	    	  launchpagev2.kidsNameField.sendKeys("VinothTest Test");
			}
		else
			BasePageV2.reportFail("Kids name field is not displayed in Create Profile screen");
		driver.pressKeyCode(AndroidKeyCode.BACK);
		//Click on next button in create profile screen
		if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
			launchpagev2.createProfileScreenNextButton.click();
		else
			BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
		
		////Click on next button(2) in create profile screen
		if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
			launchpagev2.createProfileScreenNextButton.click();
		else
			BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	    
		test.log(LogStatus.PASS,"Testcase : 'Verify the Name field in Create Profile screen by entering spaces between first name and last name(Valid):' is passed");
		BasePageV2.smokeresults("", kidsnamespaceInFnLnrow, "Pass");
		//Select any skill  (Story skill)
	/*if(Utilities.explicitWaitVisible(driver, launchpagev2.skillSetIcon, 20))
		{
		   launchpagev2.skillSetIcon.click();
		}
	else
		BasePageV2.reportFail("Skills are not displayed in Create Profile Screen");*/
	test.log(LogStatus.INFO, "Creating profile by selecting 6 characters");
	List<WebElement> icons2 = launchpagev2.characterSetIcons;
	//selecting 5 character icons
	if(Utilities.explicitWaitVisible(driver, launchpagev2.characterSetIcon, 20))
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
	Utilities.verticalSwipe(driver);
	Utilities.verticalSwipe(driver);
	for(int i=2;i<=3;i++)
	{
		try{
			icons2.get(i).click();
		}
		catch(Exception e)
		{
			BasePageV2.reportFail("Not able to click Character Icon - "+(i+1));
		}
	}
	test.log(LogStatus.INFO, "Selecting 6 favorite characters and click on Next button");
//	BasePageV2.takeScreenshot();
	
	//click next button
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		launchpagev2.createProfileScreenNextButton.click();
	else
		BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
	
	//checking for Lets go button
	if(Utilities.explicitWaitVisible(driver, launchpagev2.profileCompleteLetsGoButton, 20))
	{
		test.log(LogStatus.PASS,"Testcase : 'Verify the favourites selection functionality in the favourites/Preferences screen  by selecting 6 favourites characters/preferences:(Valid)' is passed");
	    BasePageV2.smokeresults("", sixcharrow, "Pass");
	 // Lets go button functionality
	       
	    launchpagev2.profileCompleteLetsGoButton.click();
	    if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20))
	    {
	    	test.log(LogStatus.INFO,"Navigated to the My Stuff Tab screen");
	    	test.log(LogStatus.PASS,"Testcase : 'Verify  Let`s Go button functionality in Final(last) screen:' is passed");
	    	BasePageV2.smokeresults("", letsgorow, "Pass");
	    	
	    	// Delete Profile Functionality
	    	
	    	homepagev2.profilepic.click();
	    	
	    }
	    else
	    	BasePageV2.reportFail("Not taken to My Stuff tab on tapping the let`s go button");
	    
	   	}
	else
		BasePageV2.reportFail("Error message is displayed / Not able to create Profile when clicking next button after selecting 6 characters in Favorites/Preferences screen");
	
   	test.log(LogStatus.INFO,"Navigating to the Settings screen through Switch Profile screen");
	 if(Utilities.explicitWaitVisible(driver, launchpagev2.settingsInSwitchProfileScreen, 20))
	  {
		  launchpagev2.settingsInSwitchProfileScreen.click();
	  }
	 else
		 BasePageV2.reportFail("Settings button icon is not present in Switch Profile Screen");
	 
	 
	 if(Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 20))
			{
			launchpagev2.parentPinContainer.clear();	
			launchpagev2.parentPinContainer.sendKeys("1111");
			Thread.sleep(1000);
			try{
				driver.hideKeyboard();
				launchpagev2.parentPinContainer.sendKeys("1111");
			}
			catch(Exception e){}
			}
	 
	 if(Utilities.explicitWaitVisible(driver, launchpagev2.profilesSectionInSettingsScreen, 20))
	  {
		 launchpagev2.profilesSectionInSettingsScreen.click();
	  }
	 else
		 BasePageV2.reportFail("Profiles Section is not present in Settings Screen");
	 
	 if(Utilities.explicitWaitVisible(driver, launchpagev2.profilesScreen, 20))
	  {
		 test.log(LogStatus.INFO, "Navigated to the Profiles Screen");
	  }
	 else
		 BasePageV2.reportFail(" Not Navigated to Profiles screen after selecting Profiles Section");
	 
	 test.log(LogStatus.INFO, "Navigating to any of Profile edit Screen");
	 if(Utilities.explicitWaitVisible(driver, launchpagev2.firstProfileInProfilesScreen, 20))
	  {
		 launchpagev2.firstProfileInProfilesScreen.click();
	  }
	 else
		 BasePageV2.reportFail("Profiles Section is not present in Settings Screen");
String profname=null;
	 if(Utilities.explicitWaitVisible(driver, launchpagev2.editProfileScreen, 20)) 
	{
    	test.log(LogStatus.INFO, "Getting the profile name and deleting the profile");
     	//BasePageV2.takeScreenshot();
    	if(Utilities.explicitWaitVisible(driver, launchpagev2.nameEditTextField, 20))
    	{
    		profname=launchpagev2.nameEditTextField.getText();
    	}
    	else
    		BasePageV2.reportFail("Not able to get the Profile name");
    
	}
	else
		BasePageV2.reportFail("Not navigated to the Edit Profile Screen");
	 
	  if(Utilities.explicitWaitVisible(driver, launchpagev2.deleteProfileLink, 20))
	  {
		 launchpagev2.deleteProfileLink.click();
	  }
	 else
		 BasePageV2.reportFail("Delete Profile Link  is not present in Edit profile Screen");

	  if(Utilities.explicitWaitVisible(driver, launchpagev2.deleteProfilePopUpNoButton, 20))
		{
		  launchpagev2.deleteProfilePopUpNoButton.click();
		}
		else
			BasePageV2.reportFail("Delete Pop up not displayed after clicking Delete profile Link ");
	
	  if(Utilities.explicitWaitVisible(driver, launchpagev2.editProfileScreen, 20)) 
		{
		  test.log(LogStatus.PASS,"Testcase : 'Verify the Functionality of delete profile by clicking on No in pop-up' is passed");
		  BasePageV2.smokeresults("", notdeleterow, "Pass");
		}
		else
			BasePageV2.reportFail("Not landed on same Edit Profile Screen");
	  if(Utilities.explicitWaitVisible(driver, launchpagev2.deleteProfileLink, 20))
	  {
		 launchpagev2.deleteProfileLink.click();
	  }
	 else
		 BasePageV2.reportFail("Delete Profile Link  is not present in Settings Screen");
	  
	  if(Utilities.explicitWaitVisible(driver, launchpagev2.deleteProfilePopUpYesButton, 20))
		{
		  launchpagev2.deleteProfilePopUpYesButton.click();
		}
		else
			BasePageV2.reportFail("Delete Pop up not displayed after clicking Delete profile Link ");
	  
		
	  if(Utilities.explicitWaitVisible(driver, launchpagev2.profilesScreen, 20))
	  {
		 test.log(LogStatus.INFO, "Verifying whether Profile is deleted or not in Profiles screen");
		try{
			driver.findElementByXPath("//android.widget.TextView[contains(@text,'"+profname+"')]");
		    BasePageV2.reportFail("Deleted profile is still listed in Profiles screen");
		}
		catch(Exception e)
			  {
			   test.log(LogStatus.PASS,"Testcase : 'Verify the Functionality of delete profile by clicking on 'Yes' in pop-up:' is passed");
			   BasePageV2.smokeresults("", yesdeleterow, "Pass");
			  }
	  }
	 else
		 BasePageV2.reportFail("Not Navigated to List of Profiles screen after deleting a profile");
	
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
