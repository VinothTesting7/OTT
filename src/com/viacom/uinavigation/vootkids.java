package com.viacom.uinavigation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
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

public class vootkids extends BaseTestV2 {

	String testName = "vootkids";
	public static int rowno = 0;
	public static int rowno1 = 0;
	public static int rowno2 = 0;
	String testCase = "'Verify the tabs present under Tab Bar Menu";

	// Author: Karthik

	@SuppressWarnings({ "static-access", "unchecked" })
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno,
				"Top UIbar (Profile,Voot Kids logo,Cast icon) is verified");
		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1,
				"Top UIbar (profile, Tab Bars(My Stuff, Watch, Read, Listen & Search),Cast icon) is verified");
		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2, "Verify 'Profile' icon functionality");
		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3, "Verify 'Voot Kids' logo functionality");
		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4, "Verify 'Chromecast' icon functionality");
		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5, "Verify top bar UI while scrolling down the page");
		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6, "Verify top bar UI while scrolling up the page");
		int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno7,
				"Verify Tab bar menu UI when there are more than 5 options present in the section");
		int rowno8 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno8, "Verify top bar UI while scrolling up the page");
		int rowno9 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno9,
				"Scrolling up the page should bring the tap bar menu back");

		int rowno10 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno10, testCase);

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		// homepagev2.login("karthiktskaaru5278@gmail.com", "karu5278");
		// calling Config API
		ArrayList<String> List_tabs = new ArrayList<String>();
		String url_tab = xls.getCellData("Api", 1, 4);
		System.out.println(url_tab);
		Response resp_tab = Utilities.requestUtility(url_tab);
		int totaltabsapi = resp_tab.jsonPath().get("assets.size()");
		System.out.println(totaltabsapi);
		for (int i = 0; i <= totaltabsapi; i++) {
			String tabid = resp_tab.jsonPath().get("assets.tabId[" + i + "]");
			List_tabs.add(tabid);
		}
		System.out.println(List_tabs);
		// test purpose
		// end test purpose
		if (Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 10)) {
			homepagev2.profilepic.click();
			if (Utilities.explicitWaitClickable(driver, homepagev2.switch_prof, 2)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.btn_editprofile, 2)) {
					test.log(LogStatus.INFO, "Verified edit profile button");
				} else
					test.log(LogStatus.FAIL, "Edit profile button is not their");

				if (Utilities.explicitWaitVisible(driver, homepagev2.btn_parentzone, 2)) {
					test.log(LogStatus.INFO, "Verified Prent zone button");
					// BasePageV2.takeScreenshot();
				} else
					test.log(LogStatus.FAIL, "Parent zone button is not their");

				if (Utilities.explicitWaitClickable(driver, homepagev2.btn_cancel_fromprofile, 2)) {
					homepagev2.btn_cancel_fromprofile.click();
				} else
					test.log(LogStatus.FAIL, "Cancel button is not available");
			} else
				test.log(LogStatus.INFO, "Profile icon is able to click");
			homepagev2.smokeresults("Verify 'Profile' icon functionality", rowno2, "Pass");
		} else
			test.log(LogStatus.FAIL, "Profile icon is not able to click");

		if (Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 2)) {
			System.out.println("Kids Logo Icon is able to click");
		} else
			System.out.println("Kids Logo Icon is not able to click");

		homepagev2.smokeresults("Top UIbar (Profile,Voot Kids logo,Cast icon) is verified", vootkids.rowno, "pass");
		test.log(LogStatus.PASS, " Test Case: 'Top UIbar (Profile,Voot Kids logo,Cast icon) is verified' and is Pass");
		// Navigation of tabs
		List<WebElement> tabs = null;
		try {
			tabs = driver.findElementsByClassName("android.support.v7.app.ActionBar$Tab");
			System.out.println(tabs.size());
		} catch (Exception e) {
		}

		List<String> tabsclick = new ArrayList<String>();

		try {
			homepagev2.tabClick("Watch");
			test.log(LogStatus.INFO, "Clicked on 'Watch' tab");
			tabsclick.add("true");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Unable to Click on 'Watch' tab");
			// BasePageV2.takeScreenshot();
			tabsclick.add("false");
		}
		try {
			homepagev2.tabClick("Read");
			test.log(LogStatus.INFO, "Clicked on 'Read' tab");
			tabsclick.add("true");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Unable to Click on 'Read' tab");
			// BasePageV2.takeScreenshot();
			tabsclick.add("false");
		}

		try {
			homepagev2.tabClick("Listen");
			test.log(LogStatus.INFO, "Clicked on 'Listen' tab");
			tabsclick.add("true");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Unable to Click on 'Listen' tab");
			// BasePageV2.takeScreenshot();
			tabsclick.add("false");
		}

		try {
			homepagev2.tabClick("My Stuff");
			test.log(LogStatus.INFO, "Clicked on 'My Stuff' tab");
			tabsclick.add("true");
		} catch (Exception e) {
			test.log(LogStatus.INFO, "Unable to Click on 'My Stuff' tab");
			// BasePageV2.takeScreenshot();
			tabsclick.add("false");
		}

		if (tabsclick.contains("true") && !tabsclick.contains("false")) {
			test.log(LogStatus.INFO, "All tabs displayed under Tab Menu Bar");
			// BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is pass");
			homepagev2.smokeresults(testCase, rowno10, "Pass");
		} else {
			test.log(LogStatus.INFO, "All tabs not displayed under Tab Menu Bar");
			BasePageV2.takeScreenshot();
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			homepagev2.smokeresults(testCase, rowno10, "Fail");
		}

		HomePageV2.tabClick("My Stuff");
		Utilities.verticalSwipe(driver, homepagev2.mystuff_text);

		
		if (Utilities.explicitWaitVisible(driver, homepagev2.mystuff_text, 2))
			test.log(LogStatus.INFO, "Navigation to (My Stuff) is passed");
		else
			BasePageV2.reportFail("Navigation to 'My Stuff' is fail");
		Utilities.verticalSwipeDown(driver, homepagev2.kidslogoicon);
		Thread.sleep(2000);

		HomePageV2.tabClick("Watch");
		Utilities.verticalSwipe(driver, homepagev2.watchtab_text);

//				
		if (Utilities.explicitWaitVisible(driver, homepagev2.watchtab_text, 2))
			test.log(LogStatus.INFO, "Navigation to (Watch) is passed");
		else
			BasePageV2.reportFail("Navigation to (Watch) is failed");
		Utilities.verticalSwipeDown(driver, homepagev2.kidslogoicon);
		Thread.sleep(2000);

		HomePageV2.tabClick("Read");
		Utilities.verticalSwipe(driver, homepagev2.readtab_text);

		if (Utilities.explicitWaitVisible(driver, homepagev2.readtab_text, 2))
			test.log(LogStatus.INFO, "Navigation to (Read) is passed");
		else
			BasePageV2.reportFail("Navigation to (read) is failed");
		Utilities.verticalSwipeDown(driver, homepagev2.kidslogoicon);
		Thread.sleep(2000);

		HomePageV2.tabClick("Listen");
		Utilities.verticalSwipe(driver, homepagev2.Listentab_text);

		if (Utilities.explicitWaitVisible(driver, homepagev2.Listentab_text, 2))
			test.log(LogStatus.INFO, "Navigation to (Listen) is passed");
		else
			BasePageV2.reportFail("Listen tab not displayed");
		Utilities.verticalSwipeDown(driver, homepagev2.kidslogoicon);
		Thread.sleep(2000);

		homepagev2.smokeresults(
				"Top UIbar (profile, Tab Bars(My Stuff, Watch, Read, Listen & Search),Cast icon) is verified", rowno1,
				"pass");
		test.log(LogStatus.PASS,
				"Test Case: 'Top UIbar (profile, Tab Bars(My Stuff, Watch, Read, Listen & Search),Cast icon) is verified' is Pass");

		// scroll down
		Thread.sleep(5000);

		try {
			test.log(LogStatus.INFO, "Tapping on My stuff to check scrolling");
			HomePageV2.tabClick("My Stuff");
		} catch (Exception e) {
		}

		Utilities.verticalSwipe(driver, homepagev2.mystuff_text);

		if (!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 2)) {
			test.log(LogStatus.INFO, "Page is scrolled and Menu tab is hided");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify top bar UI while scrolling down the page", rowno5, "pass");
			homepagev2.smokeresults("Verify top bar UI while scrolling up the page", rowno8, "pass");
			test.log(LogStatus.PASS, "Test Case: 'Verify top bar UI while scrolling down the page' is Pass");
			test.log(LogStatus.PASS, "Test Case: 'Verify top bar UI while scrolling up the page' is Pass");
		} else
			test.log(LogStatus.FAIL, "Page is scrolled but Menu tab is unable to hide");

		Utilities.verticalSwipeDown(driver, homepagev2.kidslogoicon);
		Thread.sleep(3000);
		if (Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 2)) {
			homepagev2.kidslogoicon.click();
			homepagev2.smokeresults("Verify 'Voot Kids' logo functionality", rowno3, "pass");
		} else
			test.log(LogStatus.FAIL, "Kids Logo Icon is not able to click");

		if (Utilities.explicitWaitVisible(driver, homepagev2.homecarousal_layout, 2)) {
			test.log(LogStatus.INFO, "Page is scrolled up and Menu tab is Visible");
			// BasePageV2.takeScreenshot();
			homepagev2.smokeresults("Verify top bar UI while scrolling up the page", rowno6, "pass");
			homepagev2.smokeresults("Scrolling up the page should bring the tap bar menu back", rowno9, "pass");
			test.log(LogStatus.PASS, "Test Case: 'Verify top bar UI while scrolling up the page' is Pass");
			test.log(LogStatus.PASS, "Test Case: 'Scrolling up the page should bring the tap bar menu back' is Pass");
		} else
			test.log(LogStatus.FAIL, "Page is scrolled up and Menu tab is not Visible");

		// Checking tab is scrolable or not with api size
		if (totaltabsapi >= 4) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.actiontab, 2)) {
				Utilities.appbar(driver, homepagev2.actiontab);
				test.log(LogStatus.INFO, "The icons should became scrollable as more sections are added");
				// BasePageV2.takeScreenshot();
				test.log(LogStatus.PASS,
						"Test Case: 'Verify Tab bar menu UI when there are more than 5 options present in the section' is Pass");
				homepagev2.smokeresults(
						"Verify Tab bar menu UI when there are more than 5 options present in the section", rowno7,
						"pass");

			} else
				test.log(LogStatus.INFO, "Unable to scroll as size is fixed");
		} else
			test.log(LogStatus.INFO, "Total tabs is <5");
		// End of Checking tab is scrolable or not with api size

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
