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

public class VerifyEditProfileFunctionalitytest extends BaseTestV2{
	String testName = "VootKidsEditProfileFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Edit Profile screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Edit Profile screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of Edit Profile screen:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int backrow1=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",backrow1 , "Verify the back arrow functionality in Edit Profile screen:");	
	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	
	 homepagev2.login(data.get("Email"),data.get("Password"));
	
	
	String un=data.get("Email");
	String pwd=data.get("Password");
	
	//click on profile icon
	if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 10))
		homepagev2.profilepic.click();
	
	test.log(LogStatus.INFO, "Navigating to Edit Profile screen");
	
	//Click on Edit  profile button at Switch profile screen
	if(Utilities.explicitWaitVisible(driver, launchpagev2.editProfileBuddyButton, 20))
		launchpagev2.editProfileBuddyButton.click();
	else
		BasePageV2.reportFail("Not able to click on Create new Link");
	
	
	//Verify Edit Profile buddy screen
		if(Utilities.explicitWaitVisible(driver, launchpagev2.editProfileBuddyScreenToolBarTitle, 20))
        test.log(LogStatus.INFO,"EDIT PROFILE BUDDY text is displayed");
		else
	    BasePageV2.reportFail("Not Navigated to Edit Profile Buddy screen / EDIT PROFILE BUDDY Text not present");
		
		
		if(Utilities.explicitWaitVisible(driver, launchpagev2.editProfileBuddyScreenSubTitle, 20))
	        test.log(LogStatus.INFO,"Edit your buddy text is displayed below the EDIT PROFILE BUDDY text");
			else
		    BasePageV2.reportFail("Edit your buddy Text not present");
		
		//Checking buddy icon present or not
		
		if(Utilities.explicitWaitVisible(driver, launchpagev2.avatarImageAtCreateYourBuddyScreen, 20))
		{
			if(launchpagev2.avatarImagesAtCreateYourBuddyScreen.size()>2)
				test.log(LogStatus.INFO, "More than two buddy icons are displayed");
			else
				test.log(LogStatus.INFO, "No buddy icons or only less than two icons are displayed");
		}
			else
		    BasePageV2.reportFail("Avatar icons are not present");
		
		
	//Checking whether 8 colours present
		if(Utilities.explicitWaitVisible(driver, launchpagev2.colourInBuddyScreen, 20))
		{
			if(launchpagev2.coloursList.size()==8)
				test.log(LogStatus.INFO, "Total of 8 different colours are displayed");
			else
				test.log(LogStatus.INFO, "Total of 8 different colours are not displayed");
		}
		else
			BasePageV2.reportFail("No colours are present in Create your buddy screen");
		
		//Check whether done button displayed or not
		if(Utilities.explicitWaitClickable(driver, launchpagev2.editProfileBuddyScreenDoneButton, 20))
		{
			test.log(LogStatus.INFO, "Done button is present in Edit Profile Buddy screen");
		}
		else
			BasePageV2.reportFail("Done button is not present / Not able to click Done button");
	
		
		//Check Back button displayed or not 
		if(Utilities.explicitWaitVisible(driver, launchpagev2.createProfileBackButton, 20))
	    test.log(LogStatus.INFO,"Back arrow is displayed in Edit Profile Buddy screen");
	    else
		BasePageV2.reportFail("Back arrow is displayed in Edit Profile Buddy screen");
		
		
	test.log(LogStatus.PASS,"Testcase : 'Verify the UI of Edit Profile screen' is passed");
	BasePageV2.smokeresults("", uirow, "Pass");
		
		launchpagev2.createProfileBackButton.click();

	
		//Verify back button functionality
		if(Utilities.explicitWaitVisible(driver, launchpagev2.switchProfileScreenToolBarTitle, 20))
		{
		 test.log(LogStatus.INFO,"Clicking on Back Arrow navigated to Switch Profile Screen");
		}
		else
			BasePageV2.reportFail("Clicking on Back Arrow not navigated to Switch Profile Screen");	
		
		//Edit Profile Button displayed or not
		if(Utilities.explicitWaitClickable(driver, launchpagev2.editProfileBuddyButton, 20))
		{
		 test.log(LogStatus.INFO,"Edit Profile Buddy button is displayed in Switch Profile screen");
		}
		else
		BasePageV2.reportFail("Create New Icon is not displayed in Switch Profile screen");	

		test.log(LogStatus.PASS,"Testcase : 'Verify the back arrow functionality in Edit Profile screen:' is passed");
		BasePageV2.smokeresults("", backrow1, "Pass");
			
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
