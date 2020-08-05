package com.viacom.smoketestscripts;

import java.time.Duration;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
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
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
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

public class VerifyCoachCardsFunctionality extends BaseTestV2{
	String testName = "VerifyCoachCardsFunctionality";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Coach cards Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Coach cards functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int profilecoach=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",profilecoach, "Verify the Coach Card for profile icon in top bar when user access the app for 1st time:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int profileclosecoach=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",profileclosecoach, "Verify the Close icon functionality for the Profile - coach card");	

	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int interruptrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",interruptrow, "Verify coach card availabilty post performing any sort interruptions(Minimize and resume/Network/Call/lock and unlock)");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int accountrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",accountrow, "Verfiy the coach card displayed when user login/Sign Up with different account on the same device");	
	
	
Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
int tabcoach=xls7.getRowCount("Smoke_Results")+1;
xls7.setCellData("Smoke_Results", "Testcase Name",tabcoach, "Verify the Coach Card  when accessing any tab(default My Stuff)");	

Xls_Reader xls8 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
int tabsclosecoach=xls8.getRowCount("Smoke_Results")+1;
xls8.setCellData("Smoke_Results", "Testcase Name",tabsclosecoach, "Verify the Close icon functionality for the tabs - coach card");	
	
Xls_Reader xls9 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
int castcoach=xls9.getRowCount("Smoke_Results")+1;
xls9.setCellData("Smoke_Results", "Testcase Name",castcoach, "Verify coach card for Chromecast feature:");	
	

Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
int castcloserow=xls3.getRowCount("Smoke_Results")+1;
xls3.setCellData("Smoke_Results", "Testcase Name",castcloserow, "Verify Close icon functionality for Chromecast coach card:");	

Xls_Reader xls10 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
int seecoach=xls10.getRowCount("Smoke_Results")+1;
xls10.setCellData("Smoke_Results", "Testcase Name",seecoach, "Verify coach card for 'See All' button feature:");	
	

Xls_Reader xls11 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
int seecloserow=xls11.getRowCount("Smoke_Results")+1;
xls11.setCellData("Smoke_Results", "Testcase Name",seecloserow, "Verify Close icon functionality for See All button coach card:");	

Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
int cleardatarow=xls6.getRowCount("Smoke_Results")+1;
xls6.setCellData("Smoke_Results", "Testcase Name",cleardatarow, "Verify the coach card availability post clearing app data from device settings");	

	launchApp();

	System.out.println(driver.getPageSource());
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	WatchPageV2  watchpagev2=new WatchPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	int prof_Err=0, tab_Err=0, see_Err=0, cast_Err=0;
    //click on watch tab	
	test.log(LogStatus.INFO, "Clearing App data of Voot Kids ");
	driver.resetApp();

	test.log(LogStatus.INFO, "Login with valid credentials");
	homepagev2.loginWithoutCoachCancel(un, pwd);
	
	test.log(LogStatus.INFO, "Verifying whether coach cards are displayed");
	
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.profileCoachCard, 30))
	{
		test.log(LogStatus.INFO, "Coach Card for profile icon is displayed");		
	   test.log(LogStatus.PASS, "Testcase : 'Verify the Coach Card for profile icon in top bar when user access the app for 1st time:' is Passed");
	   BasePageV2.smokeresults("", profilecoach, "Pass");
		if(Utilities.explicitWaitVisibleNew(driver, homepagev2.profileCoachCardBuddyIcon, 5))
		test.log(LogStatus.INFO, "Avatar icon is displayed on coach card");
		else
		{
			test.log(LogStatus.FAIL, "Avatar icon is not displayed on coach card");
			prof_Err++;
		}
		if(Utilities.explicitWaitClickableNew(driver, homepagev2.profileCoachCardCloseButton, 5))
		{
			test.log(LogStatus.INFO, "close icon is displayed on coach card");
			homepagev2.profileCoachCardCloseButton.click();
			if(Utilities.explicitWaitVisibleNew(driver, homepagev2.profileCoachCard, 5))
			BasePageV2.reportFail("Coach card not dismissed by tapping on close icon");	
			else
			test.log(LogStatus.PASS, "Testcase : 'Verify the Close icon functionality for the Profile - coach card' is passed ");
			BasePageV2.smokeresults("", profileclosecoach, "Pass");
		}
		else
		{
			test.log(LogStatus.FAIL, "close icon is not displayed on coach card");
			prof_Err++;
		}
		
	}
	else
		BasePageV2.reportFail(" Coach Card for profile icon not displayed when user access the app for 1st time:");
	
	
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.tvCastCoachCard, 30))
	{
		test.log(LogStatus.INFO, "Coach Card for Tv cast is displayed");		
		
		   test.log(LogStatus.PASS, "Testcase : 'Verify coach card for Chromecast feature:' is Passed");
		   BasePageV2.smokeresults("", castcoach, "Pass");
		if(Utilities.explicitWaitVisibleNew(driver, homepagev2.tvCastCoachCardBuddyIcon, 5))
		test.log(LogStatus.INFO, "Avatar icon is displayed on coach card");
		else
		{
			test.log(LogStatus.FAIL, "Avatar icon is not displayed on coach card");
			cast_Err++;
		}
		if(Utilities.explicitWaitClickableNew(driver, homepagev2.tvCastCoachCardCloseButton, 5))
		{
			test.log(LogStatus.INFO, "close icon is displayed on coach card");
			homepagev2.tvCastCoachCardCloseButton.click();
			if(Utilities.explicitWaitVisibleNew(driver, homepagev2.tvCastCoachCard, 5))
			BasePageV2.reportFail("Coach card not dismissed by tapping on close icon");	
			else
				test.log(LogStatus.PASS, "Testcase : 'Verify Close icon functionality for Chromecast coach card:' is Passed ");
				BasePageV2.smokeresults("", castcloserow, "Pass");
		}
		else
		{
			test.log(LogStatus.FAIL, "close icon is not displayed on coach card");
			cast_Err++;
		}
		
	}
	else
		BasePageV2.reportFail(" Coach Card for Tv Cast not displayed when user access the app for 1st time:");
	
	
	
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.tabsCoachCard, 30))
	{
		test.log(LogStatus.INFO, "Coach Card for Tab is displayed");	
		test.log(LogStatus.PASS, "Testcase : 'Verify the Coach Card  when accessing any tab(default My Stuff)' is Passed");
		   BasePageV2.smokeresults("", tabcoach, "Pass");
		if(Utilities.explicitWaitVisibleNew(driver, homepagev2.tabsCoachCardBuddyIcon, 5))
		test.log(LogStatus.INFO, "Avatar icon is displayed on coach card");
		else
		{
			test.log(LogStatus.FAIL, "Avatar icon is not displayed on coach card");
			tab_Err++;
		}
		if(Utilities.explicitWaitClickableNew(driver, homepagev2.tabsCoachCardCloseButton, 5))
		{
			test.log(LogStatus.INFO, "close icon is displayed on coach card");
			homepagev2.tabsCoachCardCloseButton.click();
			if(Utilities.explicitWaitVisibleNew(driver, homepagev2.tabsCoachCard, 5))
			BasePageV2.reportFail("Coach card not dismissed by tapping on close icon");	
			else
				test.log(LogStatus.PASS, "Testcase : 'Verify the Close icon functionality for the tabs - coach card' is Passed ");
				BasePageV2.smokeresults("", tabsclosecoach, "Pass");
		}
		else
		{
			test.log(LogStatus.FAIL, "close icon is not displayed on coach card");
			tab_Err++;
		}
		
	}
	else
		BasePageV2.reportFail(" Coach Card for Tabs not displayed when user access the app for 1st time:");
	
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.seeAllCoachCard, 30))
	{
		test.log(LogStatus.INFO, "Coach Card for See more button  is displayed");		
		  test.log(LogStatus.PASS, "Testcase : 'Verify coach card for 'See All' button feature:' is Passed");
		   BasePageV2.smokeresults("", seecoach, "Pass");
		if(Utilities.explicitWaitVisibleNew(driver, homepagev2.seeAllCoachCardBuddyIcon, 5))
		test.log(LogStatus.INFO, "Avatar icon is displayed on coach card");
		else
		{
			test.log(LogStatus.FAIL, "Avatar icon is not displayed on coach card");
			see_Err++;
		}
		if(Utilities.explicitWaitClickableNew(driver, homepagev2.seeAllCoachCardCloseButton, 5))
		{
			test.log(LogStatus.INFO, "close icon is displayed on coach card");
			homepagev2.seeAllCoachCardCloseButton.click();
			if(Utilities.explicitWaitVisibleNew(driver, homepagev2.seeAllCoachCard, 5))
			BasePageV2.reportFail("Coach card not dismissed by tapping on close icon");	
			else
				test.log(LogStatus.PASS, "Testcase : 'Verify Close icon functionality for See All coach card:' is Passed ");
				BasePageV2.smokeresults("", seecloserow, "Pass");
		}
		else
		{
			test.log(LogStatus.FAIL, "close icon is not displayed on coach card");
			see_Err++;
		}
		
	}
	else
		BasePageV2.reportFail(" Coach Card for See more buttton not displayed when user access the app for 1st time:");
	
	test.log(LogStatus.PASS, "Testcase : 'Verify the coach card availability post clearing app data from device settings' is Passed");
	homepagev2.logout();
	homepagev2.loginWithoutCoachCancel("8073542250", "vinoth@123");
    test.log(LogStatus.INFO, "Verifying whether coach cards are displayed after login with different account");
	
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.coachCard, 20))
	{
		BasePageV2.reportFail("coach cards are displayed after login with different account");
	}
	else
	{
		test.log(LogStatus.PASS, "Testcase: 'Verfiy the coach card displayed when user login/Sign Up with different account on the same device' is Passed");
	}
	test.log(LogStatus.INFO, "Navigate to any activity screen in My Stuff and verify whether GOT IT cta button displayed");
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.rewardsTrayTitle, 20))
	{
		homepagev2.rewardsTrayTitle.click();
		if(Utilities.explicitWaitVisibleNew(driver, homepagev2.learnGotitBtn, 10))
		{
			homepagev2.learnGotitBtn.click();
			test.log(LogStatus.INFO, "GOT it button displayed");
			test.log(LogStatus.INFO, "Verifying Got it button functionality");
			if(!Utilities.explicitWaitVisibleNew(driver, homepagev2.learnGotitBtn, 5))
			{
				test.log(LogStatus.INFO, "Activity coach card  dismissed after tapping Got it button");
				homepagev2.tabClick("Learn");
				test.log(LogStatus.INFO, "Tapping learn tab and verifying whether coach card displayed or not");
				if(Utilities.explicitWaitVisibleNew(driver, homepagev2.learnGotitBtn, 10))
				{
					homepagev2.learnGotitBtn.click();
					if(!Utilities.explicitWaitVisibleNew(driver, homepagev2.learnGotitBtn, 5))
					{
					test.log(LogStatus.PASS, "Testcase:'Verify the functionality of 'Got it' CTA button in coach card overlay' is Passed");
					test.log(LogStatus.PASS, "Testcase:'Verify 'Got It' button functionality from activity coach card:' is Passed");
					}
					else
					{
						test.log(LogStatus.FAIL, "coach card not dismissing after tapping Got it button in Learn Tab");
					}
				}
				
				
			}
			else
			test.log(LogStatus.FAIL, "Activity coach card not dismissing after tapping Got it button");
			
		}
		else
		{
			test.log(LogStatus.PASS, "Testcase: 'Verfiy the coach card displayed when user login/Sign Up with different account on the same device' is Passed");
		}
	}
	else
	{
		test.log(LogStatus.PASS, "Testcase: 'Verfiy the coach card displayed when user login/Sign Up with different account on the same device' is Passed");
	}
	
	driver.resetApp();
    homepagev2.loginWithoutCoachCancel(un, pwd);
	
	
	
	if(Utilities.explicitWaitVisibleNew(driver, homepagev2.profileCoachCard, 30))
	{
		// Interruption minmise/resume
					test.log(LogStatus.INFO, "Minimising the app when coach card is displayed");
					driver.runAppInBackground(Duration.ofSeconds(3));
					test.log(LogStatus.INFO, "Resuming the App and verifying whether coach card is displayed");
					if(Utilities.explicitWaitVisibleNew(driver, homepagev2.profileCoachCard, 30))
					{
						test.log(LogStatus.INFO, "Coach Card is displayed");
					}
					else
						BasePageV2.reportFail("Coach Card  not displayed when user puts app to background and come back foreground");
					
					
					test.log(LogStatus.INFO, "Locking the device when coach card is displayed");
					driver.lockDevice();
					
					test.log(LogStatus.INFO, "Unlocking the device and verifying whether coach card is displayed");
					driver.unlockDevice();
					
					if(Utilities.explicitWaitVisibleNew(driver, homepagev2.profileCoachCard, 30))
					{
						test.log(LogStatus.INFO, "Coach Card is displayed");
					}
					else
						BasePageV2.reportFail("Coach Card  not displayed after lock/unlock interruption");
					test.log(LogStatus.PASS, "Testcase : 'Verify coach card availabilty post performing any sort interruptions(Minimize and resume/lock and unlock)' is Passed");
					BasePageV2.smokeresults("", interruptrow, "Pass");
				
					for (int i = 1; i <= 5; i++) {
						
						if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 2))
							homepagev2.freshAppNotificationCancel.click();
						else
							{
							
							}
					}
	}
}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
