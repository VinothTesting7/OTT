package com.viacom.downloads;

import java.util.ArrayList;
import java.util.Hashtable;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Ordering;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class Downloads_Sorting_Profiles_Test_P1 extends BaseTestV2 {
	String testName = "Downloads_Sorting_Profiles_Test_P1";

	// Test Cases to cover
	String testCase = "Verify the sorting order of profiles in Edit Downloads tab";
	String testId = "VK_995";

	String testCase1 = "Verify the sorting order of profiles in Edit Downloads tab post switching a profile";
	String testId1 = "VK_996";

	String testCase2 = "Verify if Downloaded contents are available in Edit downlaods tab post creating profile and downloading";
	String testId2 = "VK_997";

	String testCase3 = "Verify if contents available in Edit downlaods tab post deleting profile";
	String testId3 = "VK_998";

	// Pre requisites of Test Cases
	String ReadPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/ebook.json";
	ArrayList<String> Ebooks = new ArrayList<String>();
	Response resp;
	String firstProfile = "";
	String secondProfile = "";
	String thirdProfile = "";
	String fourthProfile = "";
	ArrayList<String> otherProfilesList = new ArrayList<String>();

	@Test(dataProvider = "getData")
	public void BitrateCheckLiveandVod(Hashtable<String, String> data) throws Exception {
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test to verify " + testName + VootConstants.DEVICE_NAME);
		// Open charles application
		test.log(LogStatus.INFO, "Validating Bitrate Validation Test cases for Episode VOD, Movies and Live content");

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
		String email = data.get("Email");
		String pwd = data.get("Password");

		HomePageV2.login(email, pwd);

		downloadsPage.deleteAllDownloads();

		driver.navigate().back();

		try {
			resp = Utilities.requestUtility(ReadPageAPI);
			for (int i = 1; i <= 4; i++) {
				
				String books = resp.jsonPath().getString("assets[1].assets[0].items[" + i + "].title");
				System.out.println("Displayed Ebook was: " + books);
				Ebooks.add(books);
			}
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Ebook Response from API");
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		if (DownloadsPageV2.switchProfile(0)) {
			HomePageV2.tabClick("Read");

			try {
				String xpath = "//android.widget.TextView[contains(@text,'" + Ebooks.get(0) + "')]";

				Utilities.verticalSwipe(driver, xpath);
				try {
					driver.findElement(By.xpath(xpath)).click();
					if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 10))
						test.log(LogStatus.INFO, "Navigated to Audio Playbook button");
					else
						BasePageV2.reportFail("Failed to navigate to detail page of book: " + Ebooks.get(0));

					Utilities.verticalSwipe(driver, downloadsPage.downloadIcon);
					if (Utilities.explicitWaitClickableNew(driver, downloadsPage.downloadIcon, 20))
						downloadsPage.downloadIcon.click();
					else
						BasePageV2.reportFail("Download Icon was not displayed");
					driver.navigate().back();

					Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
				} catch (Exception e1) {
					BasePageV2.reportFail("Unable to click on book: " + Ebooks.get(0));
				}

			} catch (Exception e1) {
				BasePageV2.reportFail("Unable to select eBook: " + Ebooks.get(0));
			}
		} else {
			BasePageV2.reportFail("Unable to select profile " + 1);
		}

		// Switching to Profile 2
		if (DownloadsPageV2.switchProfile(1)) {
			HomePageV2.tabClick("Read");
			try {
				String xpath = "//android.widget.TextView[contains(@text,'" + Ebooks.get(1) + "')]";

				Utilities.verticalSwipe(driver, xpath);
				try {
					driver.findElement(By.xpath(xpath)).click();
					if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 10))
						test.log(LogStatus.INFO, "Navigated to Audio Playbook button");
					else
						BasePageV2.reportFail("Failed to navigate to detail page of book: " + Ebooks.get(1));

					Utilities.verticalSwipe(driver, downloadsPage.downloadIcon);
					if (Utilities.explicitWaitClickableNew(driver, downloadsPage.downloadIcon, 20))
						downloadsPage.downloadIcon.click();
					else
						BasePageV2.reportFail("Download Icon was not displayed");
					driver.navigate().back();
					Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

				} catch (Exception e1) {
					BasePageV2.reportFail("Unable to click on book: " + Ebooks.get(1));
				}

			} catch (Exception e1) {
				BasePageV2.reportFail("Unable to select eBook: " + Ebooks.get(1));
			}

		} else {
			BasePageV2.reportFail("Unable to select profile " + 2);
		}

		// Switching to Profile 3
		if (DownloadsPageV2.switchProfile(2)) {

			HomePageV2.tabClick("Read");

			try {
				String xpath = "//android.widget.TextView[contains(@text,'" + Ebooks.get(2) + "')]";

				Utilities.verticalSwipe(driver, xpath);
				try {
					driver.findElement(By.xpath(xpath)).click();
					if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 10))
						test.log(LogStatus.INFO, "Navigated to Audio Playbook button");
					else
						BasePageV2.reportFail("Failed to navigate to detail page of book: " + Ebooks.get(2));

					Utilities.verticalSwipe(driver, downloadsPage.downloadIcon);
					if (Utilities.explicitWaitClickableNew(driver, downloadsPage.downloadIcon, 20))
						downloadsPage.downloadIcon.click();
					else
						BasePageV2.reportFail("Download Icon was not displayed");
					driver.navigate().back();

					Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
				} catch (Exception e1) {
					BasePageV2.reportFail("Unable to click on book: " + Ebooks.get(2));
				}

			} catch (Exception e1) {
				BasePageV2.reportFail("Unable to select eBook: " + Ebooks.get(2));
			}

		} else {
			BasePageV2.reportFail("Unable to select profile " + 3);
		}

		// Switching to Second profile to verify Sorting order
		try {

		} catch (Exception e) {

		}
		firstProfile = DownloadsPageV2.profiles.get(0);
		secondProfile = DownloadsPageV2.profiles.get(1);
		thirdProfile = DownloadsPageV2.profiles.get(2);
		otherProfilesList.clear();
		if (DownloadsPageV2.switchProfile(0)) {
			HomePageV2.tabClick("My Stuff");
			for (int i = 0; i <= 20; i++) {
				if (Utilities.explicitWaitVisible(driver, downloadsPage.downloadsTabMystuffpage, 1)) {
					downloadsPage.downloadsTabMystuffpage.click();
					break;
				} else
					Utilities.verticalSwipe(driver);
			}

			Utilities.verticalSwipe(driver);
			if (Utilities.explicitWaitVisible(driver, downloadsPage.editDownloadsMystuff, 5)) {
				downloadsPage.editDownloadsMystuff.click();
				String profile1 = downloadsPage.downloadProfiles.get(0).getText();

				test.log(LogStatus.INFO, "Profile 1 under Edit download Screen displayed was: " + profile1);
				boolean defaultProfFlag = false;

				if (profile1.equalsIgnoreCase("My Downloads"))
					defaultProfFlag = true;

				
				for (int i = 1; i <= 2; i++) {
					try {
						otherProfilesList.add(downloadsPage.downloadProfiles.get(i).getText());
						test.log(LogStatus.INFO, "Profile " + (i + 1) + " displayed was: "
								+ downloadsPage.downloadProfiles.get(i).getText());
					} catch (Exception e) {

					}
				}

				if (defaultProfFlag == true && Ordering.natural().isOrdered(otherProfilesList)
						&& !otherProfilesList.contains(firstProfile)) {
					test.log(LogStatus.PASS, "Verified Test Case: " + testCase + " -----> " + testId);
					if (!Utilities.setResultsKids(testId, "Pass"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} else {
					test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase + " -----> " + testId);
					if (!Utilities.setResultsKids(testId, "Fail"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}

			}
		} else {
			BasePageV2.reportFail("Unable to switch to Profile " + 1);
		}

		// Switching to Profile 2 to Validate the sorting
		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		otherProfilesList.clear();
		if (DownloadsPageV2.switchProfile(1)) {
			HomePageV2.tabClick("My Stuff");
			for (int i = 0; i <= 20; i++) {
				if (Utilities.explicitWaitVisible(driver, downloadsPage.downloadsTabMystuffpage, 1)) {
					downloadsPage.downloadsTabMystuffpage.click();
					break;
				} else
					Utilities.verticalSwipe(driver);
			}

			Utilities.verticalSwipe(driver);
			if (Utilities.explicitWaitVisible(driver, downloadsPage.editDownloadsMystuff, 5)) {
				downloadsPage.editDownloadsMystuff.click();
				String profile1 = downloadsPage.downloadProfiles.get(0).getText();

				test.log(LogStatus.INFO, "Profile 1 under Edit download Screen displayed was: " + profile1);
				boolean defaultProfFlag = false;

				if (profile1.equalsIgnoreCase("My Downloads"))
					defaultProfFlag = true;

				//otherProfilesList.clear();
				for (int i = 1; i <= 2; i++) {
					try {
						otherProfilesList.add(downloadsPage.downloadProfiles.get(i).getText());
						test.log(LogStatus.INFO, "Profile " + (i + 1) + " displayed was: "
								+ downloadsPage.downloadProfiles.get(i).getText());
					} catch (Exception e) {

					}
				}

				if (defaultProfFlag == true && Ordering.natural().isOrdered(otherProfilesList)
						&& !otherProfilesList.contains(secondProfile)) {
					test.log(LogStatus.PASS, "Verified Test Case: " + testCase1 + " -----> " + testId1);
					if (!Utilities.setResultsKids(testId1, "Pass"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} else {
					test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase1 + " -----> " + testId1);
					if (!Utilities.setResultsKids(testId1, "Fail"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}

			}
		} else {
			BasePageV2.reportFail("Unable to switch to Profile " + 1);
		}
		
		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		// Creating profile to check new downloads Screen
		homepagev2.createProfileAndSelectTheProfile();
		DownloadsPageV2.profiles.clear();
		if (DownloadsPageV2.switchProfile(3)) {

			HomePageV2.tabClick("Read");

			try {
				String xpath = "//android.widget.TextView[contains(@text,'" + Ebooks.get(3) + "')]";

				Utilities.verticalSwipe(driver, xpath);
				try {
					driver.findElement(By.xpath(xpath)).click();
					if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 10))
						test.log(LogStatus.INFO, "Navigated to Audio Playbook button");
					else
						BasePageV2.reportFail("Failed to navigate to detail page of book: " + Ebooks.get(3));

					Utilities.verticalSwipe(driver, downloadsPage.downloadIcon);
					if (Utilities.explicitWaitClickableNew(driver, downloadsPage.downloadIcon, 20))
						downloadsPage.downloadIcon.click();
					else
						BasePageV2.reportFail("Download Icon was not displayed");
					driver.navigate().back();

					Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
				} catch (Exception e1) {
					BasePageV2.reportFail("Unable to click on book: " + Ebooks.get(3));
				}

			} catch (Exception e1) {
				BasePageV2.reportFail("Unable to select eBook: " + Ebooks.get(3));
			}

		} else {
			BasePageV2.reportFail("Unable to select profile " + 3);
		}

		
		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		fourthProfile = DownloadsPageV2.profiles.get(0);

		// Switching to Profile 4 to Validate the sorting
		driver.navigate().back();
		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		otherProfilesList.clear();
		if (DownloadsPageV2.switchProfile(1)) {
			HomePageV2.tabClick("My Stuff");
			for (int i = 0; i <= 20; i++) {
				if (Utilities.explicitWaitVisible(driver, downloadsPage.downloadsTabMystuffpage, 1)) {
					downloadsPage.downloadsTabMystuffpage.click();
					break;
				} else
					Utilities.verticalSwipe(driver);
			}

			Utilities.verticalSwipe(driver);
			if (Utilities.explicitWaitVisible(driver, downloadsPage.editDownloadsMystuff, 5)) {
				downloadsPage.editDownloadsMystuff.click();
				String profile1 = downloadsPage.downloadProfiles.get(0).getText();

				test.log(LogStatus.INFO, "Profile 1 under Edit download Screen displayed was: " + profile1);
				boolean defaultProfFlag = false;

				if (profile1.equalsIgnoreCase("My Downloads"))
					defaultProfFlag = true;

				
				for (int i = 1; i <= 3; i++) {
					try {
						otherProfilesList.add(downloadsPage.downloadProfiles.get(i).getText());
						test.log(LogStatus.INFO, "Profile " + (i + 1) + " displayed was: "
								+ downloadsPage.downloadProfiles.get(i).getText());
					} catch (Exception e) {

					}
				}

				if (defaultProfFlag == true && Ordering.natural().isOrdered(otherProfilesList)
						&& !otherProfilesList.contains(fourthProfile)) {
					test.log(LogStatus.PASS, "Verified Test Case: " + testCase2 + " -----> " + testId2);
					if (!Utilities.setResultsKids(testId2, "Pass"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				} else {
					test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase2 + " -----> " + testId2);
					if (!Utilities.setResultsKids(testId2, "Fail"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}

			}
		} else {
			BasePageV2.reportFail("Unable to switch to Profile " + 1);
		}
		
		//Validating Test Case 4
		homepagev2.deleteProfile(fourthProfile);

		downloadsPage.editDownloadsMystuff.click();
		String profile1 = downloadsPage.downloadProfiles.get(0).getText();

		test.log(LogStatus.INFO, "Profile 1 under Edit download Screen displayed was: " + profile1);
		boolean defaultProfFlag = false;

		if (profile1.equalsIgnoreCase("My Downloads"))
			defaultProfFlag = true;

		otherProfilesList.clear();
		for (int i = 1; i <= 3; i++) {
			try {
				otherProfilesList.add(downloadsPage.downloadProfiles.get(i).getText());
				test.log(LogStatus.INFO, "Profile " + (i + 1) + " displayed was: "
						+ downloadsPage.downloadProfiles.get(i).getText());
			} catch (Exception e) {

			}
		}

		if (defaultProfFlag == true && Ordering.natural().isOrdered(otherProfilesList)
				&& !otherProfilesList.contains(fourthProfile)) {
			test.log(LogStatus.PASS, "Verified Test Case: " + testCase3 + " -----> " + testId3);
			if (!Utilities.setResultsKids(testId3, "Pass"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		} else {
			test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase3 + " -----> " + testId3);
			if (!Utilities.setResultsKids(testId3, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
