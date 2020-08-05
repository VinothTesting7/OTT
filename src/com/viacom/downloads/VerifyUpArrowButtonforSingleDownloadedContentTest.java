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
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyUpArrowButtonforSingleDownloadedContentTest extends BaseTestV2 {

	String testName = "VerifyUpArrowButtonforSingleDownloadedContentTest";
	String EpisodeCard = "";
	String testCase = "Verify the availablity of Uparrow option if there is only 1 VOD content in downloads tab";

	@Test(dataProvider = "getData")
	public void DownloadedUparrowFunctionality(Hashtable<String, String> data) throws Exception {

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Validating " + testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		// Verify profile selection in 'Select Profile' screen
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase);

		// Verify 'Create new' button functionality in 'Select Profile' screen

		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		SettingsPageV2 settings = new SettingsPageV2(driver, test);

		KidsPlayerPageV2 kidspage = new KidsPlayerPageV2(driver, test);

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		HomePageV2.tabClick("Watch");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);

		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("WatchCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);

				Response respTab = Utilities.requestUtility(ApiTab);

				EpisodeCard = respTab.jsonPath().get("assets.items[2].title");
				// Audiobooks.add(abooks);

			} else
				System.out.println("Unable to fetch tab from API");
		}

		try {
			String xpath = "//android.widget.TextView[contains(@text,'" + EpisodeCard + "')]";

			Utilities.verticalSwipe(driver, xpath);
			WebElement ab = driver.findElement(By.xpath(xpath));

			if (Utilities.explicitWaitVisible(driver, ab, 10))
				ab.click();
			else
				BasePageV2.reportFail("Episode card is not displayed");
		} catch (Exception e) {
		}

		Utilities.verticalSwipe(driver, downloadpagev2.downloadIconEpisodes);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadIconEpisodes, 20))
			downloadpagev2.downloadIconEpisodes.click();
		else
			BasePageV2.reportFail("Download icon is not displayed in Episode info Page");
		Thread.sleep(10000);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.contenttoDownload, 20))
			downloadpagev2.contenttoDownload.click();
		else
			BasePageV2.reportFail("Content not displayed in 'Download Episodes' screen");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.contenttoDownload, 120))
			test.log(LogStatus.INFO, " Content downloaded successfully");
		else
			BasePageV2.reportFail("Content downloaded successfully");

		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(2000);
		driver.pressKeyCode(AndroidKeyCode.BACK);

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);
		Thread.sleep(5000);

		HomePageV2.tabClick("My Stuff");

		Utilities.verticalSwipe(driver, downloadpagev2.downloadsTabMystuffpage);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 20))
			downloadpagev2.downloadsTabMystuffpage.click();
		else
			BasePageV2.reportFail("Downloads tab is not displayed under 'My Stuff' tab");

		Utilities.verticalSwipe(driver, downloadpagev2.contentdownloaded);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.contentdownloaded, 20))
			downloadpagev2.contentdownloaded.click();
		else
			BasePageV2.reportFail("Downloaded content is not displayed in 'Downloads tab'");

		if (Utilities.explicitWaitVisible(driver, kidspage.videoPlayer, 20)) {
			kidspage.pauseVideo();
			System.out.println("Page Source of Video Player");

		} else
			BasePageV2.reportFail("Video Player fails to trigger on playing downloaded content");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 10)) {
			test.log(LogStatus.INFO,
					"Up Arrow button is displayed on playing content from downloads tab having only 1 content");
			test.log(LogStatus.FAIL, "Test Case: " + testCase + " is Fail");
			HomePageV2.smokeresults(testCase, rowno, "Fail");
		} else {
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			HomePageV2.smokeresults(testCase, rowno, "Pass");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
