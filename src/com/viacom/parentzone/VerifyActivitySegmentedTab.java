package com.viacom.parentzone;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyActivitySegmentedTab extends BaseTestV2{
	
	String testName = "VerifyActivitySegmentedTab";
	@Test(dataProvider = "getData")
	public void VerifyActivitySegmentedTab(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyActivitySegmentedTab");
		test.log(LogStatus.INFO, "Verify Activity Segmented Tab");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		// VK_1565 Verify the UI of Learn stats tray under Activity tab in Parent Zone for a new user	
		// VK_1562 Verify the App usage data under Activity data if user has accessed < 1 hour since installing first time:

		//Launch Voot kids app
		 launchApp();
		 test.log(LogStatus.INFO, "Launched application");
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
		 //homepagev2.login(data.get("Email"),data.get("Password"));
		 //Login module to be added
		 //homepagev2.signup();
	 
		// Front end
		homepagev2.createProfileAndSelectTheProfile();
		int err1566=0;
		int index=0;
		boolean locatedLearnStats=false;
		String firstSkillName="NUMBERS";
		String secondSkillName="KNOWLEDGE";
		String thirdSkillName="LIFE";
		String fourthSkillName="ART";
		String fifthSkillName="WORDS";
		ArrayList<Integer> indexList=new ArrayList<Integer>();
		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 10)) {
			homepagev2.profilepic.click();
			test.log(LogStatus.INFO, "Clicked on Profile pic icon");
			if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZoneButton, 10)) {
				settingspagev2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Tapped on Parent Zone button");
				if (Utilities.explicitWaitVisible(driver, launchpagev2.pinContainer, 5)) {
					launchpagev2.pinContainer.clear();
					launchpagev2.pinContainer.sendKeys("1111");
					test.log(LogStatus.INFO, "Entered PIN");
				} 
				else
					BasePageV2.reportFail("Pin Container not displayed");
				if (Utilities.explicitWaitVisible(driver, settingspagev2.parentZonePage, 20)) {
					test.log(LogStatus.INFO, "Parent zone Page is displayed");
					if (Utilities.explicitWaitVisible(driver, settingspagev2.activityTab, 20)) {
						settingspagev2.activityTab.click();
						test.log(LogStatus.INFO, "Activity tab is selected");
						//Total hours
						for(int scroll=0;scroll<=7;scroll++) {
							if(Utilities.explicitWaitVisibleNew(driver, homepagev2.activityTabTotalHours, 10)) {
								try {
									String totalHours=homepagev2.activityTabTotalHours.getAttribute("text");
									if(totalHours.equals("0 Hours")) {
										test.log(LogStatus.INFO, "'"+totalHours+"' is displayed in UI as expected");
										test.log(LogStatus.PASS, "Verify the App usage data under Activity data if user has accessed < 1 hour since installing first time is PASS");
										if(!Utilities.setResultsKids("VK_1562", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									}
									else {
										test.log(LogStatus.INFO, ""+totalHours+" is displayed in UI instead of '0 Hours'");
										test.log(LogStatus.FAIL, "Verify the App usage data under Activity data if user has accessed < 1 hour since installing first time is FAIL");
										if(!Utilities.setResultsKids("VK_1562", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
									}
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to fetch total hours");
									test.log(LogStatus.FAIL, "Verify the App usage data under Activity data if user has accessed < 1 hour since installing first time is FAIL");
									if(!Utilities.setResultsKids("VK_1562", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								}
							}
							else {
								Utilities.verticalSwipe(driver);
								if(scroll==7) {
									test.log(LogStatus.FAIL, "Failed to locate total hours");
									test.log(LogStatus.FAIL, "Verify the App usage data under Activity data if user has accessed < 1 hour since installing first time is FAIL");
									if(!Utilities.setResultsKids("VK_1562", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								}
							}
						}
						
						//Learn Stats
						for(int scroll=0;scroll<=7;scroll++) {
							if(Utilities.explicitWaitVisibleNew(driver, settingspagev2.learnStatsTray, 5)) {
								test.log(LogStatus.INFO, "Located LEARN STATS tray in Parent Zone page");
								Utilities.verticalSwipe(driver);
								Utilities.verticalSwipe(driver);
								locatedLearnStats=true;
								break;
							}
							else {
								Utilities.verticalSwipe(driver);
							}
						}
						if(locatedLearnStats==true) {
							test.log(LogStatus.INFO, "--------------------------------------------------");
							try {
								String firstSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+firstSkillName+"']";
								driver.findElement(By.xpath(firstSkillLoc));
								test.log(LogStatus.INFO, "Located first skill: "+firstSkillName);
								String correctSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+firstSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer_correct' and @text='0']";
								String totalSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+firstSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer' and @text='0']";
								String skillLevelLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+firstSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/trophy_level_view' and @text='Level 0']";
								try {
									driver.findElement(By.xpath(correctSkillLoc));
									test.log(LogStatus.INFO, "Located correct skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate correct skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(totalSkillLoc));
									test.log(LogStatus.INFO, "Located total skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate total skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(skillLevelLoc));
									test.log(LogStatus.INFO, "Located skill Level 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate skill Level 0");
									err1566++;
								}
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to locate first skill");
								err1566++;
							}
							test.log(LogStatus.INFO, "--------------------------------------------------");
							try {
								String secondSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+secondSkillName+"']";
								driver.findElement(By.xpath(secondSkillLoc));
								test.log(LogStatus.INFO, "Located second skill: "+secondSkillName);
								String correctSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+secondSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer_correct' and @text='0']";
								String totalSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+secondSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer' and @text='0']";
								String skillLevelLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+secondSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/trophy_level_view' and @text='Level 0']";
								try {
									driver.findElement(By.xpath(correctSkillLoc));
									test.log(LogStatus.INFO, "Located correct skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate correct skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(totalSkillLoc));
									test.log(LogStatus.INFO, "Located total skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate total skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(skillLevelLoc));
									test.log(LogStatus.INFO, "Located skill Level 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate skill Level 0");
									err1566++;
								}
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to locate second skill");
								err1566++;
							}
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipe(driver);
							test.log(LogStatus.INFO, "--------------------------------------------------");
							try {
								String thirdSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+thirdSkillName+"']";
								driver.findElement(By.xpath(thirdSkillLoc));
								test.log(LogStatus.INFO, "Located third skill: "+thirdSkillName);
								String correctSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+thirdSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer_correct' and @text='0']";
								String totalSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+thirdSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer' and @text='0']";
								String skillLevelLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+thirdSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/trophy_level_view' and @text='Level 0']";
								try {
									driver.findElement(By.xpath(correctSkillLoc));
									test.log(LogStatus.INFO, "Located correct skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate correct skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(totalSkillLoc));
									test.log(LogStatus.INFO, "Located total skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate total skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(skillLevelLoc));
									test.log(LogStatus.INFO, "Located skill Level 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate skill Level 0");
									err1566++;
								}
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to locate third skill");
								err1566++;
							}
							test.log(LogStatus.INFO, "--------------------------------------------------");
							try {
								String fourthSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fourthSkillName+"']";
								driver.findElement(By.xpath(fourthSkillLoc));
								test.log(LogStatus.INFO, "Located fourth skill: "+fourthSkillName);
								String correctSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fourthSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer_correct' and @text='0']";
								String totalSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fourthSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer' and @text='0']";
								String skillLevelLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fourthSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/trophy_level_view' and @text='Level 0']";
								try {
									driver.findElement(By.xpath(correctSkillLoc));
									test.log(LogStatus.INFO, "Located correct skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate correct skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(totalSkillLoc));
									test.log(LogStatus.INFO, "Located total skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate total skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(skillLevelLoc));
									test.log(LogStatus.INFO, "Located skill Level 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate skill Level 0");
									err1566++;
								}
							}
							catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to locate fourth skill");
							err1566++;
							}
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipe(driver);
							test.log(LogStatus.INFO, "--------------------------------------------------");
							try {
								String fifthSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fifthSkillName+"']";
								driver.findElement(By.xpath(fifthSkillLoc));
								test.log(LogStatus.INFO, "Located fifth skill: "+fifthSkillName);
								String correctSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fifthSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer_correct' and @text='0']";
								String totalSkillLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fifthSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/learn_answer_layout']//*[@resource-id='com.viacom18.vootkids:id/title_total_answer' and @text='0']";
								String skillLevelLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item' and @text='"+fifthSkillName+"']//parent::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/trophy_level_view' and @text='Level 0']";
								try {
									driver.findElement(By.xpath(correctSkillLoc));
									test.log(LogStatus.INFO, "Located correct skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate correct skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(totalSkillLoc));
									test.log(LogStatus.INFO, "Located total skill score 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate total skill score 0");
									err1566++;
								}
								try {
									driver.findElement(By.xpath(skillLevelLoc));
									test.log(LogStatus.INFO, "Located skill Level 0");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to locate skill Level 0");
									err1566++;
								}
							}
							catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to locate fifth skill");
							err1566++;
							}
						}
						if(err1566==0) {
							test.log(LogStatus.PASS, "Verify the type of cards available in masthead carousel for My Stuff tab is PASS");
							if(!Utilities.setResultsKids("VK_1566", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
						else {
							test.log(LogStatus.FAIL, "Verify the type of cards available in masthead carousel for My Stuff tab is FAIL");
							if(!Utilities.setResultsKids("VK_1566", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						}
					}
				}
			}
		}
		 
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
