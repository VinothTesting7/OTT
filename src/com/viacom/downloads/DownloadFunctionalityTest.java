package com.viacom.downloads;

import java.util.Hashtable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.MixPanelPageVK;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class DownloadFunctionalityTest extends BaseTestV2 {
	String testName = "DownloadFunctionalityTest";
	String testCase = "Verify the Metadata for Audio Book card in Edit downloads screen";
	String testID="VK_1012";
	String testCase1 = "Verify the Metadata for Ebook card in Edit downloads screen";
	String testID1="VK_1011";
	//String showTitle="";
	String xpath="";
	Response resp;
	String audioTitle="";
	String ebookTitle="";
	boolean audioDownloadflag=false;
	boolean ebookDownloadFlag=false;
	String listenPageAPI="https://api.vootkids.com/app/ui/v1/tabs/audio.json";
	String ReadPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/ebook.json";
	// Author: Karthik

	@Test(dataProvider = "getData")
	public void DownloadFunctionality(Hashtable<String, String> data) throws Exception {
		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		MixPanelPageVK mppage=new MixPanelPageVK(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		
		

		HomePageV2.logout();
		Thread.sleep(5000);
		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);
		
		downloadpagev2.deleteAllDownloads();

		HomePageV2.tabClick("Listen");
		try {
			resp = Utilities.requestUtility(listenPageAPI);
			audioTitle = resp.jsonPath().getString("assets[1].assets[0].items[0].title");

			
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch response Audio book from API");
		}

		
		test.log(LogStatus.INFO, "Navigating to show: " + audioTitle);
		xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
				+ audioTitle + "']";
		Utilities.verticalSwipe(driver, xpath);

		try {
			WebElement show = driver.findElement(By.xpath(xpath));
			show.click();
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to navigate to show: " + audioTitle);
		}

		Utilities.verticalSwipe(driver, mppage.DownloadLink);
		// mppage.DownloadLink.click();
		

		test.log(LogStatus.INFO, "Clicking on download Icon");
		audioDownloadflag=false;
		if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadLink, 20)) {
			test.log(LogStatus.INFO, "Adding content '" + audioTitle + "' for Download");
			mppage.DownloadLink.click();
			if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadedLink, 120)) {
				test.log(LogStatus.INFO, "Audio Content Downloaded successfully");
				audioDownloadflag=true;
				driver.navigate().back();
			}
			else {
				test.log(LogStatus.INFO, "Failed to download Audio book Content: "+audioTitle);
				BasePageV2.takeScreenshot();
				driver.navigate().back();
			}
			
		}
		else {
			test.log(LogStatus.FAIL, "Failed to Download Audio book content");
			BasePageV2.takeScreenshot();
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		Thread.sleep(5000);

		HomePageV2.tabClick("Read");
		try {
			resp = Utilities.requestUtility(ReadPageAPI);
			ebookTitle = resp.jsonPath().getString("assets[1].assets[0].items[1].title");
			
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Ebook Response from API");
		}

		
		test.log(LogStatus.INFO, "Navigating to show: " + ebookTitle);
		xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
				+ ebookTitle + "']";
		Utilities.verticalSwipe(driver, xpath);

		try {
			WebElement show = driver.findElement(By.xpath(xpath));
			show.click();
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to navigate to show: " + ebookTitle);
		}
		
		Utilities.verticalSwipe(driver, mppage.DownloadLink);
		// mppage.DownloadLink.click();
		

		test.log(LogStatus.INFO, "Clicking on download Icon");
		ebookDownloadFlag=false;
		if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadLink, 20)) {
			test.log(LogStatus.INFO, "Adding content '" + ebookTitle + "' for Download");
			mppage.DownloadLink.click();
			if (Utilities.explicitWaitVisibleNew(driver, mppage.DownloadedLink, 120)) {
				test.log(LogStatus.INFO, "Ebook Content Downloaded successfully");
				ebookDownloadFlag=true;
				driver.navigate().back();
			}
			else {
				test.log(LogStatus.INFO, "Failed to download Ebook Content: "+ebookTitle);
				BasePageV2.takeScreenshot();
				driver.navigate().back();
			}
			
		}
		else {
			test.log(LogStatus.FAIL, "Failed to Download Ebook content");
			BasePageV2.takeScreenshot();
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		
		HomePageV2.tabClick("My Stuff");
		for (int i = 0; i <= 20; i++) {
			if (Utilities.explicitWaitVisibleNew(driver, downloadpagev2.downloadsTabMystuffpage, 1)) {
				downloadpagev2.downloadsTabMystuffpage.click();
				break;
			} else
				Utilities.verticalSwipe(driver);
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editDownloadsMystuff, 5))
			downloadpagev2.editDownloadsMystuff.click();
		else
			BasePageV2.reportFail("Edit Download Button not displayed");
		
		
		if(audioDownloadflag==true) {
			try {
				xpath = "//android.widget.TextView[@text='"+audioTitle+"']";
				driver.findElement(By.xpath(xpath));
				test.log(LogStatus.PASS, "Verified : "+testCase+" ---> "+testID);
				try {
					Utilities.setResultsKids(testID, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, testCase+" ---> "+testID+" is Fail");
				try {
					Utilities.setResultsKids(testID, "Fail");
				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		}
		else {
			test.log(LogStatus.FAIL, testCase+" ---> "+testID+" is Fail");
			try {
				Utilities.setResultsKids(testID, "Fail");
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		
		if(ebookDownloadFlag==true) {
			try {
				xpath = "//android.widget.TextView[@text='"+ebookTitle+"']";
				driver.findElement(By.xpath(xpath));
				test.log(LogStatus.PASS, "Verified : "+testCase1+" ---> "+testID);
				try {
					Utilities.setResultsKids(testID1, "Pass");
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, testCase1+" ---> "+testID1+" is Fail");
				try {
					Utilities.setResultsKids(testID1, "Fail");
				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		}
		else {
			test.log(LogStatus.FAIL, testCase1+" ---> "+testID1+" is Fail");
			try {
				Utilities.setResultsKids(testID1, "Fail");
			} catch (Exception e1) {
				// TODO: handle exception
			}
		}
		

		driver.navigate().back();
		 
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		downloadpagev2.deleteAllDownloads();
		
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
