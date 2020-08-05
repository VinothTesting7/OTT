package com.viacom.smoketestscripts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyRewards extends BaseTestV2{
	
	String testName = "VerifyRewards";
	@Test(dataProvider = "getData")
	public void VerifyRewards(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyRewards");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		//VK_1451 Verify the availability of Rewards tray in My Stuff below carousel section
		//VK_1452 Verify the UI of Rewards tray below carousel for a New User
		
		int errCount=0;
		//Launch Voot kids app
		launchApp();
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		//homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 //System.out.println(driver.getPageSource());
		 }
		 int err1451=0;
		 int err1452=0;
		 Utilities.verticalSwipe(driver);
		 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsTrayTitle, 10)) {
			 test.log(LogStatus.INFO, "REWARDS tray is displayed");
		 }
		 else {
			 test.log(LogStatus.FAIL, "REWARDS tray is not displayed");
			 err1451++;
			 err1452++;
		 }
		 Utilities.verticalSwipe(driver);
		 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsPlayButton, 10)) {
			 test.log(LogStatus.INFO, "PLAY button is displayed");
		 }
		 else {
			 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsSeeAllButton, 10)) {
				 test.log(LogStatus.INFO, "SEE ALL button is displayed");
			 }
			 else {
				 test.log(LogStatus.FAIL, "PLAY/SEE ALL button is not displayed");
				 err1451++;
				 err1452++; 
			 }
		 }
		 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsSticker1, 10)) {
			 test.log(LogStatus.INFO, "Located Sticker 1 under Rewards tray");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Failed to locate Sticker 1 under Rewards tray");
			 err1452++;
		 }
		 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsSticker2, 10)) {
			 test.log(LogStatus.INFO, "Located Sticker 2 under Rewards tray");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Failed to locate Sticker 2 under Rewards tray");
			 err1452++;
		 }
		 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsSticker3, 10)) {
			 test.log(LogStatus.INFO, "Located Sticker 3 under Rewards tray");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Failed to locate Sticker 3 under Rewards tray");
			 err1452++;
		 }
		 if(VootConstants.DEVICE_TYPE.equals("Tablet")) {
			 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsSticker4, 10)) {
				 test.log(LogStatus.INFO, "Located Sticker 4 under Rewards tray");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Sticker 4 under Rewards tray");
				 err1452++;
			 }
			 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsSticker5, 10)) {
				 test.log(LogStatus.INFO, "Located Sticker 5 under Rewards tray");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Sticker 5 under Rewards tray");
				 err1452++;
			 }
			 if(Utilities.explicitWaitVisible(driver, homepagev2.rewardsSticker6, 10)) {
				 test.log(LogStatus.INFO, "Located Sticker 6 under Rewards tray");
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate Sticker 6 under Rewards tray");
				 err1452++;
			 }
		 }
		 if(err1451==0) {
		     test.log(LogStatus.PASS, "Verify the availability of Rewards tray in My Stuff below carousel section is PASS");
			if(!Utilities.setResultsKids("VK_1451", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Verify the availability of Rewards tray in My Stuff below carousel section is FAIL");
			 if(!Utilities.setResultsKids("VK_1451", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
		 if(err1452==0) {
		     test.log(LogStatus.PASS, "Verify the UI of Rewards tray below carousel for a New User is PASS");
			if(!Utilities.setResultsKids("VK_1452", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }
		 else {
			 test.log(LogStatus.FAIL, "Verify the UI of Rewards tray below carousel for a New User is FAIL");
			 if(!Utilities.setResultsKids("VK_1452", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }	 
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
