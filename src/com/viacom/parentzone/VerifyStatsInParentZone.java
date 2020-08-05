//Author Tanisha
package com.viacom.parentzone;

import java.util.Hashtable;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.PlayPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;


@SuppressWarnings("deprecation")
public class VerifyStatsInParentZone extends BaseTestV2 {
	public static String testName = "VerifyStatsInParentZone";
	public static String sheetName = "Regression Checklist";
	public static String pass = "PASS";
	public static String fail = "FAIL";
	
	//VK_1743 Verify the functionality when tapped on Locked/Unlocked stickers present under Rewards tray
	//VK_1744 Verify the functionality when tapped on Locked/Unlocked stickers present under Rewards List view screen
	//VK_1554 Verify the available CTA button in Rewards tray if user has unlocked or locked stickers
	//VK_1560 Verify the functionality of See All button in Rewards tray
	//VK_1623 Verify the Sequencing of skills present under Learn stats
	//VK_1628 Verify the trophy icon colour post earning a trophy in a skill

	int err1623=0;
	boolean learnStats=false;
	String firstSmart="";
	String secondSmart="";
	String thirdSmart="";
	String fourthSmart="";
	String fifthSmart="";
	 
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

	@SuppressWarnings("deprecation")
	@Test(dataProvider = "getData")
	public void verifyStatsInParentZone(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyStatsInParentZone");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);

		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		launchApp();

		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SettingsPageV2 settingspagev2 = new SettingsPageV2(driver, test);
		PlayPageV2 playPage = new PlayPageV2(driver, test);
		
		try {
			homepagev2.login(data.get("Email"),data.get("Password"));
		} catch (Exception e) {
			// TODO: handle exception
			BasePageV2.reportFail("Login credential couldn't fetch from data sheet");
		}

		 if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 10)) {
				try {
					homepagev2.profilepic.click(); // tap on profile icon
					test.log(LogStatus.INFO, "Clicked on profile icon in home page");
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to click on profile icon in home page");
				}
		 }
		 else {
			test.log(LogStatus.INFO, "Profile icon in home page is not clickable");
			BasePageV2.takeScreenshot();
		 }	
		 if(Utilities.explicitWaitClickable(driver, settingspagev2.parentZoneButton, 20)) {
			try {
				settingspagev2.parentZoneButton.click(); // tap on parent zone button
				test.log(LogStatus.INFO, "Clicked on Parent Zone button");
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on Parent Zone button");
			}
		 }
		 else {
			test.log(LogStatus.INFO, "Parent Zone button is not clickable");
			BasePageV2.takeScreenshot();
		 }		
		 try {
				settingspagev2.parentPinContainer.sendKeys("1111");
				test.log(LogStatus.INFO, "Entered PIN 1111");
		 } 
		 catch (Exception e) {
				test.log(LogStatus.FAIL, "Failed to enter 1111");
		 }	
		 for(int i=0;i<=3;i++) {
			 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.rewardsTrayInParentsZone, 2)) {
				 test.log(LogStatus.INFO, "Located Rewards tray");
				 break;
			 }
			 else
				 Thread.sleep(3000);
		 }
		 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.stickerBelowRewardsInParentZone, 2)) {
			 test.log(LogStatus.INFO, "Located first sticker under Rewards tray");
			 if(Utilities.explicitWaitClickable(driver, settingspagev2.stickerBelowRewardsInParentZone, 2)) {
				 try {
					 settingspagev2.stickerBelowRewardsInParentZone.click();
					 test.log(LogStatus.INFO, "Clicked on sticker below Rewards tray");
					 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.stickerBelowRewardsInParentZone, 2)) {
						 test.log(LogStatus.INFO, "Even after clicking sticker, it is still visible, hence no response was observed.");
						 test.log(LogStatus.PASS, "Verify the functionality when tapped on Locked/Unlocked stickers present under Rewards tray is Pass");
						 if(!Utilities.setResultsKids("VK_1743", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else {
						 test.log(LogStatus.FAIL, "Sticker could not be located after clicking sticker, hence there was a response observed.");
						 test.log(LogStatus.FAIL, "Verify the functionality when tapped on Locked/Unlocked stickers present under Rewards tray is Fail");
						 if(!Utilities.setResultsKids("VK_1743", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.INFO, "Failed to click on sticker below Rewards tray");
				 }
			 }
		 }
		 else {
			 test.log(LogStatus.INFO, "First sticker under Rewards tray is not visible");
		 }
		 for(int scroll=0;scroll<=1;scroll++) {
			 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.rewardsSeeAllButtonInParentsZone, 7)) {
				 Utilities.verticalSwipe(driver);
				 break;
			 } 
			 else {
				 Utilities.verticalSwipe(driver);
				 if(scroll==1) {
					 test.log(LogStatus.FAIL, "Failed to locate SEE ALL button"); 
				 }
			 }
		 }
		 boolean inParentZone=false;
		 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.rewardsSeeAllButtonInParentsZone, 2)) {
			 test.log(LogStatus.INFO, "Located SEE ALL button"); 
			 test.log(LogStatus.PASS, "Verify the available CTA button in Rewards tray if user has unlocked or locked stickers is Pass");
			 if(!Utilities.setResultsKids("VK_1554", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 if(Utilities.explicitWaitClickable(driver, settingspagev2.rewardsSeeAllButtonInParentsZone, 2)) {
				 try {
					 settingspagev2.rewardsSeeAllButtonInParentsZone.click();
					 test.log(LogStatus.INFO, "Clicked on SEE ALL button");
					 if(Utilities.explicitWaitClickable(driver, settingspagev2.rewardsScreenGotIt, 5)) {
						 try {
							 settingspagev2.rewardsScreenGotIt.click();
							 test.log(LogStatus.INFO, "Clicked on Got it button");
						 }
						 catch(Exception e) {
							 
						 }
					 }
					 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.rewardsScreenTitle, 10)) {
						 test.log(LogStatus.INFO, "Rewards screen is displayed"); 
						 test.log(LogStatus.PASS, "Verify the functionality of See All button in Rewards tray is Pass");
						 if(!Utilities.setResultsKids("VK_1560", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 else {
						 test.log(LogStatus.INFO, "Rewards screen is not displayed");  
						 test.log(LogStatus.FAIL, "Verify the functionality of See All button in Rewards tray is Fail");
						 if(!Utilities.setResultsKids("VK_1560", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 }
					 Utilities.verticalSwipe(driver);
					 Utilities.verticalSwipe(driver);
					 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.rewardsScreenSticker, 20)) {
						 test.log(LogStatus.INFO, "Located a sticker in Rewards screen"); 
						 try {
							 settingspagev2.rewardsScreenSticker.click();
							 test.log(LogStatus.INFO, "Clicked on sticker");
							 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.rewardsScreenSticker, 5)) {
								 test.log(LogStatus.INFO, "Even after clicking sticker, it is still visible, hence no response was observed.");
								 test.log(LogStatus.PASS, "Verify the functionality when tapped on Locked/Unlocked stickers present under Rewards List view screen is Pass");
								 if(!Utilities.setResultsKids("VK_1744", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 }
							 else {
								 test.log(LogStatus.FAIL, "Sticker could not be located after clicking sticker, hence there was a response observed.");
								 test.log(LogStatus.FAIL, "Verify the functionality when tapped on Locked/Unlocked stickers present under Rewards List view screen is Fail");
								 if(!Utilities.setResultsKids("VK_1744", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							 }
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Failed to click on sticker"); 
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Failed to locate a sticker in Rewards screen"); 
					 }
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Navigated back to Parent Zone page");
					 inParentZone=true;
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click on SEE ALL button"); 
				 }
				 
			 } 
			 else {
					 test.log(LogStatus.FAIL, "SEE ALL button is not clickable"); 
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "Failed to locate SEE ALL button");
			 test.log(LogStatus.FAIL, "Verify the available CTA button in Rewards tray if user has unlocked or locked stickers is Fail");
			 if(!Utilities.setResultsKids("VK_1554", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		 }

		 if(inParentZone==true) {
			for(int scroll=0;scroll<=6;scroll++) {
				if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.learnStatsTrayName, 2)) {
					test.log(LogStatus.INFO, "Located Learn Stats tray");
					Utilities.verticalSwipe(driver);
					learnStats=true;
					break;
				}
				else {
					Utilities.verticalSwipe(driver);
					if(scroll==6)
						test.log(LogStatus.FAIL, "Failed to locate Learn Stats tray");
				}
			}
		 }
		 if(learnStats==true) {
			 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.logicSmartBelowLearnStats, 5)) {
				 try {
					 firstSmart=settingspagev2.logicSmartBelowLearnStats.getAttribute("text");
					 if(firstSmart.equalsIgnoreCase("Numbers")) {
						test.log(LogStatus.INFO, "Located 'Numbers' as first skill"); 
					 }
					 else {
						test.log(LogStatus.FAIL, "UI displays "+firstSmart+" as first skill instead of 'Numbers'"); 
						err1623++;
						
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to fetch first skill name");
					 err1623++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate first skill");
			 }
			 
			 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.discoverSmartBelowLearnStats, 5)) {
				 try {
					 secondSmart=settingspagev2.discoverSmartBelowLearnStats.getAttribute("text");
					 if(secondSmart.equalsIgnoreCase("Knowledge")) {
						test.log(LogStatus.INFO, "Located 'Knowledge' as second skill"); 
					 }
					 else {
						test.log(LogStatus.FAIL, "UI displays "+secondSmart+" as second skill instead of 'Knowledge'"); 
						err1623++;
						
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to fetch second skill name");
					 err1623++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate second skill");
			 }
			 
			 Utilities.verticalSwipe(driver);
			 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.liveSmartBelowDiscoverSmart, 5)) {
				 try {
					 thirdSmart=settingspagev2.liveSmartBelowDiscoverSmart.getAttribute("text");
					 if(thirdSmart.equalsIgnoreCase("Life")) {
						test.log(LogStatus.INFO, "Located 'Life' as third skill"); 
					 }
					 else {
						test.log(LogStatus.FAIL, "UI displays "+thirdSmart+" as third skill instead of 'Life'"); 
						err1623++;
						
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to fetch third skill name");
					 err1623++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate third skill");
			 }
			 
			 Utilities.verticalSwipe(driver);
			 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.artSmartBelowLiveSmart, 5)) {
				 try {
					 fourthSmart=settingspagev2.artSmartBelowLiveSmart.getAttribute("text");
					 if(fourthSmart.equalsIgnoreCase("Art")) {
						test.log(LogStatus.INFO, "Located 'Art' as fourth skill"); 
					 }
					 else {
						test.log(LogStatus.FAIL, "UI displays "+fourthSmart+" as fourth skill instead of 'Art'"); 
						err1623++;
						
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to fetch fourth skill name");
					 err1623++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate fourth skill");
			 }
			 
			 Utilities.verticalSwipe(driver);
			 if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.speakSmartBelowArtSmart, 5)) {
				 try {
					 fifthSmart=settingspagev2.speakSmartBelowArtSmart.getAttribute("text");
					 if(fifthSmart.equalsIgnoreCase("Words")) {
						test.log(LogStatus.INFO, "Located 'Words' as fifth skill"); 
					 }
					 else {
						test.log(LogStatus.FAIL, "UI displays "+fifthSmart+" as fifth skill instead of 'Words'"); 
						err1623++;
						
					 }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to fetch fifth skill name");
					 err1623++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL, "Failed to locate fifth skill");
			 }
			 
			 if(err1623==0) {
				 test.log(LogStatus.PASS, "Verify the Sequencing of skills present under Learn stats is Pass");
				 if(!Utilities.setResultsKids("VK_1623", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
			 }
			 else {
				 test.log(LogStatus.FAIL, "Verify the Sequencing of skills present under Learn stats is Fail");
				 if(!Utilities.setResultsKids("VK_1623", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
			 }
		 }
		 BasePageV2.takeScreenshot();
	}
	
}
