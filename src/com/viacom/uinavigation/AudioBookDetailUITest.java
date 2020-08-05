package com.viacom.uinavigation;

import java.util.Hashtable;
import java.util.List;

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
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class AudioBookDetailUITest extends BaseTestV2 {
	String testName = "AudioBookDetailUITest";
	String testCase = "'Verify the navigation when tapping on cards'";
	String testCase1 = "'Verify the UI of Audio book detail page UI'";
	String testCase2 = "'Verify Play button functionality'";
	String testCase3 = "'Verify Download icon UI post complete download'";
	String testCase4 = "'Verify the type of cards to be displayed under 'Related/You May Also Like' tray'";
	String testCase5 = "Verify UI of Favorite icon post favoriting a audio book from detail page";
	String testCase6 = "Verify Download icon UI while downloading a content";
	String testCase7 = "'Verify 'Download Audiobook' link functionality'";
	String testCase8 = "'Verify the availablity of Uparrow option if there is only 1 Audio book content in downloads tab'";
	String audiobook = "";

	// Author: Karthik

	@Test(dataProvider = "getData")
	public void audioBookUI(Hashtable<String, String> data) throws Exception {

		if (GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest(testName);
		test.log(LogStatus.INFO, "Starting the test for Validating " + testName + " : " + VootConstants.DEVICE_NAME);

		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);

		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase);

		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1, testCase1);

		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2, testCase2);

		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3, testCase3);

		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4, testCase4);

		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5, testCase5);

		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6, testCase6);

		int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno7, testCase7);

		int rowno8 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno8, testCase8);

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);
		// Validating Test Case (testCase)

		String email= data.get("Email");
		String pwd = data.get("Password");
		HomePageV2.login(email, pwd);

		HomePageV2.tabClick("Listen");

		int rows = xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for (int rNum = 1; rNum <= rows; rNum++) {
			String apiname = xls.getCellData("Api", "API Name", rNum);
			if (apiname.equals("AudioBookCards")) {
				String ApiTab = xls.getCellData("Api", "Url", rNum);

				Response respTab = Utilities.requestUtility(ApiTab);

				audiobook = respTab.jsonPath().get("assets.items[0].title");
				// Audiobooks.add(abooks);

			} else
				System.out.println("Unable to fetch tab from API");
		}

		try {
			String xpath = "//android.widget.TextView[contains(@text,'" + audiobook + "')]";

			Utilities.verticalSwipe(driver, xpath);
			WebElement ab = driver.findElement(By.xpath(xpath));

			if (Utilities.explicitWaitVisible(driver, ab, 10))
				ab.click();
			else
				BasePageV2.reportFail("Audio Book is not displayed");
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 5)) {
			test.log(LogStatus.INFO, "Audio Book Detail Page is displayed");
			//BasePageV2.takeScreenshot();
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is Pass");
			homepagev2.smokeresults(testCase, rowno, "Pass");

			try {
				homepagev2.playAudiobookbutton.click();
				if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 10)) {
					test.log(LogStatus.INFO, "Audio Player is displayed");
				//	BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase2 + " is pass");
					homepagev2.smokeresults(testCase2, rowno2, "Pass");
					driver.pressKeyCode(AndroidKeyCode.BACK);
				} else
					BasePageV2.reportFail("Audio Player not displayed");

			} catch (Exception e) {
				BasePageV2.reportFail("Play Audio Button in audio book detail page is not displayed");
			}

		} else
			BasePageV2.reportFail("Audio Book detail page is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.backButton, 3))
			test.log(LogStatus.INFO, "Back button displayed in Audio Book detail page");
		else
			BasePageV2.reportFail("Back button is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audTitle, 3))
			test.log(LogStatus.INFO,
					"'Audio Book Title' is displayed in Audio Book detail page" + homepagev2.audTitle.getText());
		else
			BasePageV2.reportFail("'Audio Book Title' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.favIcon, 3)) {

			test.log(LogStatus.INFO, "'Favourite icon' is displayed in Audio Book detail page");
			if (Utilities.explicitWaitVisible(driver, homepagev2.notfavouritedetailpage, 2)) {
				test.log(LogStatus.INFO, "Not added to favourite, Adding to Favourite");
				homepagev2.notfavouritedetailpage.click();
				if (Utilities.explicitWaitClickable(driver, homepagev2.favouritedetailpage, 2)) {
					test.log(LogStatus.INFO, "Added to favourite successfull Icon got highlighted");
					test.log(LogStatus.PASS, "Test case: " + testCase5 + " is Pass");
				//	BasePageV2.takeScreenshot();
					homepagev2.smokeresults(testCase5, rowno5, "Pass");
				} else {
					test.log(LogStatus.INFO, "Unable to interact with favourite Icon");
					test.log(LogStatus.FAIL, "Test case: " + testCase5 + " is Fail");
					BasePageV2.takeScreenshot();
					homepagev2.smokeresults(testCase5, rowno5, "Fail");
				}
			} else if (Utilities.explicitWaitVisible(driver, homepagev2.favouritedetailpage, 2)) {
				test.log(LogStatus.INFO, "Already in favourite (Icon is highlighted), Removing from favourite");
				homepagev2.favouritedetailpage.click();
				if (Utilities.explicitWaitClickable(driver, homepagev2.notfavouritedetailpage, 2)) {
					test.log(LogStatus.INFO, "Removing from favourite successfull Icon dehighlighted");
					test.log(LogStatus.PASS, "Test case: " + testCase5 + " is Pass");
					//BasePageV2.takeScreenshot();
					homepagev2.smokeresults(testCase5, rowno5, "Pass");
				} else {
					test.log(LogStatus.INFO, "Unable to interact with favourite Icon");
					test.log(LogStatus.FAIL, "Test case: " + testCase5 + " is Fail");
					BasePageV2.takeScreenshot();
					homepagev2.smokeresults(testCase5, rowno5, "Fail");
				}
			} else {
				test.log(LogStatus.INFO, "Unable to interact with favourite Icon");
				test.log(LogStatus.FAIL, "Test case: " + testCase5 + " is Fail");
				BasePageV2.takeScreenshot();
				homepagev2.smokeresults(testCase5, rowno5, "Fail");
			}

		}

		else
			BasePageV2.reportFail("'Favourite icon' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.playAudiobookbutton, 3))
			test.log(LogStatus.INFO, "'Play Audio' button is displayed in Audio Book detail page");
		else
			BasePageV2.reportFail("'Play Audio' button is not displayed");

		Utilities.verticalSwipe(driver, homepagev2.naratorname);

		if (Utilities.explicitWaitVisible(driver, homepagev2.audauthorContainer, 3))
			test.log(LogStatus.INFO, "'Author container' displayed in Audio Book detail page");
		else
			BasePageV2.reportFail("'Author container' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.audauthorname, 3))
			test.log(LogStatus.INFO,
					"'Author name' displayed in Audio Book detail page is: " + homepagev2.audauthorname.getText());
		else
			BasePageV2.reportFail("'Author name' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.naratorContainer, 3))
			test.log(LogStatus.INFO, "'Narrator container' displayed in Audio Book detail page");
		else
			BasePageV2.reportFail("'Narrator container' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.naratorname, 3))
			test.log(LogStatus.INFO,
					"'Narrated by' displayed in Audio Book detail page is: " + homepagev2.naratorname.getText());
		else
			BasePageV2.reportFail("'Narrated by' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.description, 3))
			test.log(LogStatus.INFO, "'Audio Book description' is displayed in Audio Book detail page is: "
					+ homepagev2.description.getText());
		else
			BasePageV2.reportFail("'Audio Book description' is not displayed");

		Utilities.verticalSwipe(driver, homepagev2.downloadIcon);

		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadAudioBookText, 3))
			test.log(LogStatus.INFO,
					"Download Audio Book Text is displayed: " + homepagev2.downloadAudioBookText.getText());
		else
			BasePageV2.reportFail("'Download Audio Book' text is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.duration, 3))
			test.log(LogStatus.INFO, "Audio duration displayed is " + homepagev2.duration.getText());
		else
			BasePageV2.reportFail("'Audio duration' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadIcon, 3))
			test.log(LogStatus.INFO, "'Download icon' is displayed in Audio Book detail page");
		else
			BasePageV2.reportFail("'Download icon' is not displayed");

		if (Utilities.explicitWaitVisible(driver, homepagev2.downloadIcon, 3))
			homepagev2.downloadIcon.click();
		else
			BasePageV2.reportFail("Download Icon is not displayed");

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadingAudio, 2)) {
			test.log(LogStatus.INFO,
					"Audio download is in Progress text displayed as: " + downloadpagev2.downloadingAudio.getText());
			test.log(LogStatus.PASS, "Test Case: " + testCase6 + " is pass");
			test.log(LogStatus.PASS, "Test Case: " + testCase7 + " is Pass");
			HomePageV2.smokeresults(testCase6, rowno6, "Pass");
			HomePageV2.smokeresults(testCase7, rowno7, "Pass");
		} else {
			test.log(LogStatus.INFO, "Audio download is in Progress is not displayed");
			test.log(LogStatus.FAIL, "Test Case: " + testCase6 + " is Fail");
			test.log(LogStatus.FAIL, "Test Case: " + testCase7 + " is Fail");
			HomePageV2.smokeresults(testCase6, rowno6, "Fail");
			HomePageV2.smokeresults(testCase7, rowno7, "Fail");
		}

		try {
			next: for (int i = 1; i <= 100; i++) {
				Thread.sleep(1500);
				if (Utilities.explicitWaitVisible(driver, homepagev2.DownloadedStatus, 5)) {
					test.log(LogStatus.INFO, "Content downloaded successfully");
					test.log(LogStatus.INFO, "Download Status displayed as: " + homepagev2.DownloadedStatus.getText());
				//	BasePageV2.takeScreenshot();
					test.log(LogStatus.PASS, "Test Case: " + testCase3 + " is passed");
					homepagev2.smokeresults(testCase3, rowno3, "Pass");
					break;
				} else
					continue next;
			}

		} catch (Exception e) {
			BasePageV2.reportFail("Failed to download Audio Book");
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.recentTypeTab, 5)) {
			test.log(LogStatus.INFO, "Displayed tab is: " + homepagev2.recentTypeTab.getText());
			for (int i = 0; i <= 2; i++) {
				Utilities.verticalSwipe(driver);
			}
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.recentTypeTab, 3))
			test.log(LogStatus.INFO, "Recent types tab displayed is: " + homepagev2.recentTypeTab.getText());
		else
			BasePageV2.reportFail("'Recent types tab' is not displayed");

	//	BasePageV2.takeScreenshot();
		test.log(LogStatus.PASS, "Test Case: " + testCase1 + " is Pass");
		homepagev2.smokeresults(testCase1, rowno1, "Pass");

		// Validating Test Case testCase4
		String url = "https://api.vootkids.com/app/recommendation/v1/getRecommendations.json?useCaseId=UCAPPEAUDIO&method=audioRelated&uId=471a76d3bf1241b787895f0828da7269&profileId=5b581c196d8d1912c0c719be&limit=10&offSet=0";
		Response rskids = Utilities.requestUtility(url);
		int totalcards = rskids.jsonPath().get("assets.totalItems");
		List<WebElement> audioContents = driver.findElements(By.id("com.viacom18.vootkids:id/recent_recycler_view"));
		int numberofAudioCards = audioContents.size();
		test.log(LogStatus.INFO, "Total Number of contents displayed under tray: " + homepagev2.recentTypeTab.getText()
				+ " is " + totalcards);
		for (int i = 0; i < totalcards; i++) {
			try {
				WebElement element = driver.findElement(By.xpath("//android.view.View[@index='" + i + "']"));

				if (Utilities.explicitWaitVisible(driver, element, 3)) {

					String contentType = rskids.jsonPath().get("assets.items[" + i + "].contentType");
					test.log(LogStatus.INFO, "Content type of Content: " + (i + 1) + " is: " + contentType);
					if (contentType.contains("Audio")) {
						test.log(LogStatus.INFO, "Card displayed is related to audio");
					} else
						BasePageV2.reportFail("Audio Card is not displayed under: " + homepagev2.recentTypeTab.getText()
								+ " tab in audio book detail page");
				} else
					BasePageV2.reportFail("Audio Card: " + (i + 1) + " is not displayed");

			} catch (Exception e) {
			}

		}

		test.log(LogStatus.PASS,"Test Case: " + testCase4 + " is Pass");
		homepagev2.smokeresults(testCase4, rowno4, "Pass");

		// Validating Test Case testCase5
		driver.pressKeyCode(AndroidKeyCode.BACK);//this line failed to navigate back need to check..
		Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
		if (Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			homepagev2.mystuff_tab.click();
			
		} else
			BasePageV2.reportFail("My Stuff Tab is not displayed");

		try {
			HomePageV2.tabClick("My Stuff");
		} catch (Exception e) {

		}

		Utilities.verticalSwipe(driver, downloadpagev2.downloadsTabMystuffpage);

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 5))
			downloadpagev2.downloadsTabMystuffpage.click();
		else
			BasePageV2.reportFail("Downloads Tab is not displayed");

		for (int i = 0; i < 2; i++) {
			Utilities.verticalSwipe(driver);
		}

		try {
			String xpath = "//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='0']";
			WebElement downloadstuffs = driver.findElement(By.xpath(xpath));
			if (Utilities.explicitWaitVisible(driver, downloadstuffs, 5))
				downloadstuffs.click();
			else
				BasePageV2.reportFail("Down load contents not displayed");
		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.audioplayPause, 30)) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.audioplaylistExpand, 5)) {
				test.log(LogStatus.INFO,
						"Up Arrow button is displayed when played downloaded content in downloads stuff when having only one content in downloads tab");
				test.log(LogStatus.FAIL, "Test Case: " + testCase8 + " is Fail");
				HomePageV2.smokeresults(testCase8, rowno8, "FAIL");
			} else {
				test.log(LogStatus.INFO,
						"Up Arrow button is displayed when played downloaded content in downloads stuff when having only one content in downloads tab");
				test.log(LogStatus.PASS, "Test Case: " + testCase8 + " is Pass");
				HomePageV2.smokeresults(testCase8, rowno8, "Pass");
			}
			driver.pressKeyCode(AndroidKeyCode.BACK);
		} else
			test.log(LogStatus.FAIL, "Play/Pause Button is not displayed on audio player");

		driver.pressKeyCode(AndroidKeyCode.BACK);
		downloadpagev2.deleteDownload();

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}
}
