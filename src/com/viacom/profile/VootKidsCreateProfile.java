package com.viacom.profile;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
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

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class VootKidsCreateProfile extends BaseTestV2{
	String testName = "VootKidsCreateProfile";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Create Profile screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Create Profile screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of Create Profile screen:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int backrow1=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",backrow1 , "Back arrow should not be displayed in create profile 1st screen");	
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int row1999=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",row1999, "Verify the DOB field in Create Profile screen by selecting any past date:");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int row2020=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",row2020, "Verify the DOB field in Create Profile screen by selecting any future dates:");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int avatarrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",avatarrow , "Verify the UI of Avatar selection/Create your buddy screen");	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int favrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",favrow , "Verify the UI of Favourites/Preferences screen in the avatar selection screen");	
	
	
	Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int finalrow=xls7.getRowCount("Smoke_Results")+1;
	xls7.setCellData("Smoke_Results", "Testcase Name",finalrow , "Verify the UI of Final(last) screen:");	
	
	
	Xls_Reader xls8 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int finalbackrow=xls8.getRowCount("Smoke_Results")+1;
	xls8.setCellData("Smoke_Results", "Testcase Name",finalbackrow , "Verify the functionality of back arrow in Final(last) screen:");	
	
	
	
	
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
	
	//Parent PIN
	
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
	
	//Observing the screen UI - Create Profile Text
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfilePage, 20))
	test.log(LogStatus.INFO, "Navigated to Create Profile Screen - Create Profile Text displayed");
	else
		BasePageV2.reportFail("Not Navigated to Create Profile Screen / 'Create Profile' Text not displayed");
	
	
	//About ur kid text
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileTellUsFirstScreenAboutKid, 20))
	test.log(LogStatus.INFO, "'About your kid' text is displayed");
	else
	BasePageV2.reportFail("'About your kid' text not found");
	String kidsname="Vinoth Ifocus";
	//Kids name field
	if(Utilities.explicitWaitVisible(driver, launchpagev2.kidsNameField, 20))
	{
		
		test.log(LogStatus.INFO, "'Kids Name' field is displayed");
		if(Utilities.explicitWaitVisible(driver, launchpagev2.kidsNameFieldText, 20))
			test.log(LogStatus.INFO, "'Kid's Name' text is written in Kids Name field");
		else
			BasePageV2.reportFail("'Kids Name' Text is not written in Kids name field");
		
		launchpagev2.kidsNameField.sendKeys(kidsname);
	}
	else
	BasePageV2.reportFail("'Kids Name' field is not displayed");
	
	//Dob field
	if(Utilities.explicitWaitVisible(driver, launchpagev2.dobProfileField, 20))
	test.log(LogStatus.INFO, "'Dob ' field is displayed");
	else
	BasePageV2.reportFail("'Dob' field not found");
	
	//Next button
	if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
	test.log(LogStatus.INFO, "'Next' button is displayed");
	else
	BasePageV2.reportFail(" 'Next' button is not displayed");
	
	
	//Pagination Display
	if(Utilities.explicitWaitVisible(driver, launchpagev2.paginationindicator, 20))
	{

	/*	int count=0;
		for(int i=0;i<launchpagev2.threepaginationindicator.size();i++)
		{
			if(Utilities.explicitWaitVisible(driver, launchpagev2.threepaginationindicator.get(i), 2))
			count++;
		}
		if(count==3)
			test.log(LogStatus.INFO, "Three Pagination indicators are displayed");
		else
			{
			System.out.println("size of page indicator is:"+launchpagev2.threepaginationindicator.size());
			BasePageV2.reportFail(" Three Pagination indicators are not displayed");
			}*/
		if(launchpagev2.threepaginationindicator.size()==3)
			test.log(LogStatus.INFO, "Three Pagination indicators is displayed");
		else
			BasePageV2.reportFail(" Three Pagination indicators are not displayed");
			
	}
		else
		BasePageV2.reportFail(" Pagination indicator is not displayed");
	
	test.log(LogStatus.PASS,"Testcase : 'Verify the UI of Create Profile screen' is passed");
	BasePageV2.smokeresults("", uirow, "Pass");
	
	
	//Check Back button displayed or not 
	if(!Utilities.explicitWaitVisible(driver, launchpagev2.createProfileBackButton, 20))
    BasePageV2.reportFail("Back arrow should is displayed in create profile 1st screen");
    else
	test.log(LogStatus.PASS,"Testcase : 'Back arrow should not be displayed in create profile 1st screen' is passed");
	BasePageV2.smokeresults("", backrow1, "Pass");
		
	//Select Dob	valid
	if(Utilities.explicitWaitVisible(driver, launchpagev2.dobProfileField, 20))
		launchpagev2.dobProfileField.click();
	else
		BasePageV2.reportFail("Not able to select Date of birth in Create Profile screen");
	//Invalid date - 1999 & 2019 selection
	
	//scripting progress
			if(Utilities.explicitWaitVisible(driver, launchpagev2.calendarYearPick, 20))
			{
		        launchpagev2.calendarYearPick.click();
		        Utilities.verticalSwipeDown(driver, launchpagev2.year1999);
				if(Utilities.explicitWaitClickable(driver, launchpagev2.year1999, 5))
					test.log(LogStatus.PASS,"Testcase : 'Verify the DOB field in Create Profile screen by selecting any past date:' is Passed");
				else
					BasePageV2.reportFail("User not able to select the year 1999");
				
				BasePageV2.smokeresults("", row1999, "Pass");
				launchpagev2.calendarYearPick.click();
				try{
					String text="//android.widget.TextView[contains(@text,'2020')]";
		            Utilities.verticalSwipeCheckElement(driver,text);
			//		driver.findElementByAndroidUIAutomator("newScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
				}
				catch(Exception e){}
				if(Utilities.explicitWaitClickable(driver, launchpagev2.year2020, 5))
				BasePageV2.reportFail("User able to select the year 2020");
				else
				test.log(LogStatus.PASS,"Testcase : 'Verify the DOB field in Create Profile screen by selecting any future dates:' is Passed");
				BasePageV2.smokeresults("", row2020, "Pass");
				
			}
			else
				BasePageV2.reportFail("Not able to select year in Date of birth field");

			//Click ok in calendar pop up
			if(Utilities.explicitWaitClickable(driver, launchpagev2.calendarOkButton, 20))
			{
				 launchpagev2.calendarOkButton.click();
			}
			else
				BasePageV2.reportFail("Not able to click Ok button on calendar pop up");
			
			
			////Click on next button(1) in create profile screen
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
			{
				launchpagev2.createProfileScreenNextButton.click();
			}
			else
				BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
			
		//Create Your Buddy screen Tool bar title	
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileSecondScreenToolBarTitle, 20))
			{
				test.log(LogStatus.INFO, "Create Profile text on the top is displayed");
			}
			else
				BasePageV2.reportFail("Create Profile text on the top is displayed");
			
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createYourBuddyScreen, 20))
			{
				test.log(LogStatus.INFO, "'Create ur buddy' text is displayed below Create Profile text");
			}
			else
				BasePageV2.reportFail("'Create ur buddy' text is not displayed below Create Profile text");
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.avatarImageAtCreateYourBuddyScreen, 20))
			{
				if(launchpagev2.avatarImagesAtCreateYourBuddyScreen.size()>2)
					test.log(LogStatus.INFO, "More than two buddy icons are displayed");
				else
					test.log(LogStatus.INFO, "No buddy icons or only less than two icons are displayed");
			}
			else
				BasePageV2.reportFail("No buddy icon is present in Create your buddy screen");
			
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.colourInBuddyScreen, 20))
			{
				if(launchpagev2.coloursList.size()==8)
					test.log(LogStatus.INFO, "Total of 8 different colours are displayed");
				else
					test.log(LogStatus.INFO, "Total of 8 different colours are not displayed");
			}
			else
				BasePageV2.reportFail("No colours are present in Create your buddy screen");
				
			//Check next button displayed or not 
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
		    test.log(LogStatus.INFO,"Next Button is displayed");
		    else
			BasePageV2.reportFail("Next button is not displayed");
			
			
			//Pagination Display
			if(Utilities.explicitWaitVisible(driver, launchpagev2.paginationindicator, 20))
			{
				
				if(launchpagev2.threepaginationindicator.size()==6)
					test.log(LogStatus.INFO, "Three Pagination indicators is displayed");
				else
					{
					System.out.println("size of page indicator is:"+launchpagev2.threepaginationindicator.size());
					BasePageV2.reportFail(" Three Pagination indicators are not displayed");
					}
					
			}
				else
				BasePageV2.reportFail(" Pagination indicator is not displayed");
			
			//Check Back button displayed or not 
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileBackButton, 20))
				test.log(LogStatus.INFO,"Back arrow is displayed in create profile 2nd  screen");
		    else
			
			 BasePageV2.reportFail("Back arrow is not displayed in create profile 1st screen");			
			test.log(LogStatus.PASS,"Testcase : 'Verify the UI of Avatar selection/Create your buddy screen' is passed");
			BasePageV2.smokeresults("", avatarrow, "Pass");
			//click next button
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
				launchpagev2.createProfileScreenNextButton.click();
			    else
				BasePageV2.reportFail("Next button is not displayed");
				
			
			//Check Back button displayed or not 			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileBackButton, 20))
				test.log(LogStatus.INFO,"Back arrow is displayed in create profile 2nd  screen");
		    else
			
			 BasePageV2.reportFail("Back arrow is not displayed in create profile favourites/preferences screen");			
			
			
			//Create profile text on top check
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileSecondScreenToolBarTitle, 20))
			{
				test.log(LogStatus.INFO, "Create Profile text on the top is displayed");
			}
			else
				BasePageV2.reportFail("Create Profile text on the top is displayed");
			
			
			//select atleast 5 favourites displayed or not
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createYourBuddyScreen, 20))
			{
				test.log(LogStatus.INFO, "'Select atleast 5 favourites' text is displayed below Create Profile text");
			}
			else
				BasePageV2.reportFail("'Select atleast 5 favourites' text is not displayed below Create Profile text");
			
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.characterSetIcon, 20))
		    {
			test.log(LogStatus.INFO,"Characters set icons are displayed");
		    }
		    else
			BasePageV2.reportFail("Charcters set icons are not displayed");
			
			
			/*	if(Utilities.explicitWaitVisible(driver, launchpagev2.skillSetIcon, 20))
		    {
			test.log(LogStatus.INFO,"Skill set icons are displayed");
		    }
		    else
			BasePageV2.reportFail("Skills set icons are not displayed");*/

			
		/*	//Select any skill  (Story skill)
			if(Utilities.explicitWaitVisible(driver, launchpagev2.skillSetIcon, 20))
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
			//BasePageV2.takeScreenshot();
			
			//click next button
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileScreenNextButton, 20))
			    {
				test.log(LogStatus.INFO,"Next Button is displayed");
				launchpagev2.createProfileScreenNextButton.click();
			    }
			    else
				BasePageV2.reportFail("Next button is not displayed");
			
			
			test.log(LogStatus.PASS,"Testcase : 'Verify the UI of Favourites/Preferences screen in the avatar selection screen' is passed");
			BasePageV2.smokeresults("", favrow, "Pass");
			test.log(LogStatus.INFO, "");
			//Check whether You are all set subtitle is present or not in last screen
			//select atleast 5 favourites displayed or not
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileFinalScreenSubTitle, 20))
			{
				test.log(LogStatus.INFO, "'YOU'RE ALL SET' text is displayed");
			}
			else
				test.log(LogStatus.FAIL,"'YOU'RE ALL SET' text is not displayed");
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileFinalScreenKidsProfileName, 20))
			{
				test.log(LogStatus.INFO, "Kids Profile name is displayed");
				if(launchpagev2.createProfileFinalScreenKidsProfileName.getText().equalsIgnoreCase(kidsname))
					test.log(LogStatus.INFO, "Profile name is verified");
				else
					test.log(LogStatus.FAIL, "Profile Name is different from the Create Profile first screen");
			}
			else
				test.log(LogStatus.FAIL,"Kids Profile name is not displayed");
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileFinalScreenKidsAge, 20))
			{
				test.log(LogStatus.INFO, "Age is displayed");
			}
			else
				test.log(LogStatus.FAIL,"Age is not displayed in the final screen of Create Profile");
			
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.letsGo, 20))
			{
				test.log(LogStatus.INFO, "Lets Go button is displayed in the screen");
			}
			else
				test.log(LogStatus.FAIL,"Lets Go button is not displayed in the final screen of Create Profile");	
			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.createAnotherProfileLink, 20))
			{
				test.log(LogStatus.INFO, "Create Another Profile Link is displayed in the final screen");
			}
			else
				test.log(LogStatus.FAIL,"Create Another Profile Link is not displayed in the final screen of Create Profile");	
			
			//Check Back button displayed or not 			
			if(Utilities.explicitWaitClickable(driver, launchpagev2.createProfileBackButton, 20))
				{
				test.log(LogStatus.INFO,"Back arrow is displayed in create profile last screen");
				launchpagev2.createProfileBackButton.click();
				}
		    else
			
			 BasePageV2.reportFail("Back arrow is not displayed in create profile final screen");	
			
			
			test.log(LogStatus.PASS,"Testcase : 'Verify the UI of Final(last) screen:' is passed");
			BasePageV2.smokeresults("", finalrow, "Pass");
			//Verify back button functionality
			if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenToolBarTitle, 20))
			{
			 test.log(LogStatus.INFO,"Clicking on Back Arrow navigated to Switch Profile Screen");
			 // launchpagev2.createProfileBackButton.click();
			}
			else
				BasePageV2.reportFail("Clicking on Back Arrow not navigated to Switch Profile Screen");	
			
			//Create new icon displayed or not
			if(Utilities.explicitWaitClickable(driver, launchpagev2.createNewIcon, 20))
			{
			 test.log(LogStatus.INFO,"Create New Icon is displayed in Switch Profile screen");
			}
			else
				BasePageV2.reportFail("Create New Icon is not displayed in Switch Profile screen");	
			
			 test.log(LogStatus.INFO,"Checking whether Created profile displayed or not in Switch Profile Screen");
			 
			//Check whether created profile is displayed or not 			
			if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenCreatedProfileName, 20))
			{
				System.out.println("profile screen profile name:"+launchpagev2.switchProfileScreenCreatedProfileName.getText());
			     System.out.println("kids name:"+kidsname);
			   if(launchpagev2.switchProfileScreenCreatedProfileName.getText().equalsIgnoreCase(kidsname))
			   test.log(LogStatus.INFO, "Previously Created profile is displayed in Switch Profile Screen");
			   else
				   BasePageV2.reportFail("Previously Created Profile is not displayed in Switch profile Screen");	   
			}
			else
				BasePageV2.reportFail("Profile is not displayed in Switch profile Screen");	
			
			test.log(LogStatus.PASS,"Testcase : 'Verify the functionality of back arrow in Final(last) screen:' is passed");
			BasePageV2.smokeresults("", finalbackrow, "Pass");
			
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
