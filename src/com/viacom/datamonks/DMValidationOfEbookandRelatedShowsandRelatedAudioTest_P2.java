package com.viacom.datamonks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DMPageV2;
import com.viacom.pagesVersion2.DataMonkPage;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.Activity;

public class DMValidationOfEbookandRelatedShowsandRelatedAudioTest_P2 extends BaseTestV2 {
	String testName = "DMValidationOfEbookandRelatedShowsandRelatedAudioTest_P2";

	String testCase1 = "Verify the cards present under More from Author post tapping on Up arrow button";
	String testId1 = "VK_619";

	String testCase2 = "Verify the type of cards displayed under 'Related Shows' tray";
	String testId2 = "VK_413";
	
	String testCase3 = "Verify the cards present in Audio Player matches with the cards present in programme info";
	String testId3 = "VK_1915";

	String ReadPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/ebook.json";
	String watchPageAPI = "https://api.vootkids.com/app/curated/v1/kidsCharacters.json?sortBy=mostPopular&limit=8&offSet=0";
	String listenPageAPI = "https://api.vootkids.com/app/ui/v1/tabs/audio.json";
	
	String ebookTitle = "";
	String xpath = "";
	int pagenumber1 = 0;
	int pagenumber2 = 0;
	int totalPages = 0;
	String showTitle = "";
	String contentTitle="";
	String audiobook="";

	List<String> ebookMfromTitleList = new ArrayList<String>();
	List<String> Result = new ArrayList<String>();
	Set<String> ebookMfromAuthorSet = new HashSet<String>();

	@SuppressWarnings("unlikely-arg-type")
	@Test(dataProvider = "getData")
	public void EbooksLastViewedTest(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyLastViewedBooks");
		test.log(LogStatus.INFO, "Starting the test on: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
		EbooksPageV2 ebookPage = new EbooksPageV2(driver, test);
		ListenPageV2 listenPage = new ListenPageV2(driver, test);

		String email = data.get("Email");
		String pwd = data.get("Password");

		HomePageV2.login(email, pwd);

		downloadsPage.deleteAllDownloads();

		driver.navigate().back();

		try {
			Response resp = Utilities.requestUtility(ReadPageAPI);
			ebookTitle = resp.jsonPath().getString("assets[1].assets[0].items[1].title");
			System.out.println("Displayed Ebook was: " + ebookTitle);

		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Ebook Response from API");
		}

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		homepagev2.clearLastViewedContents();

		Utilities.verticalSwipeDown(driver, homepagev2.profilepic);

		HomePageV2.tabClick("Read");
		xpath = "//android.widget.TextView[contains(@text,'" + ebookTitle + "')]";

		Utilities.verticalSwipe(driver, xpath);
		try {
			driver.findElement(By.xpath(xpath)).click();
			if (Utilities.explicitWaitClickableNew(driver, homepagev2.playAudiobookbutton, 10))
				test.log(LogStatus.INFO, "Navigated to Audio Playbook button");
			else
				BasePageV2.reportFail("Failed to navigate to detail page of book: " + ebookTitle);

			Utilities.verticalSwipe(driver, downloadsPage.downloadIcon);
			if (Utilities.explicitWaitClickableNew(driver, downloadsPage.downloadIcon, 20)) {
				downloadsPage.downloadIcon.click();
				if (Utilities.explicitWaitClickableNew(driver, downloadsPage.DownloadedStatus, 120))
					test.log(LogStatus.INFO, "Ebook content '" + ebookTitle + "' downloaded successfully");
				else
					BasePageV2.reportFail("Unable to downloaad ebook: " + ebookTitle);

				Utilities.verticalSwipeDown(driver, ebookPage.readButton);
				if (Utilities.explicitWaitVisibleNew(driver, ebookPage.readButton, 10)) {
					ebookPage.readButton.click();
					homepagev2.verifyAndProgressBar();
					Utilities.tap(driver);
					Utilities.tap(driver);

					if (Utilities.explicitWaitVisibleNew(driver, listenPage.audioPlayerUpArrowExpand, 20)) {
						listenPage.audioPlayerUpArrowExpand.click();

						if (Utilities.explicitWaitVisibleNew(driver, listenPage.audioPlayerMoreFromAuthor, 10)) {
							listenPage.audioPlayerMoreFromAuthor.click();

							for (WebElement titles : listenPage.moreFromTitleList) {
								ebookMfromTitleList.add(titles.getText());
							}

							for (WebElement authors : listenPage.moreFromTitleList) {
								ebookMfromAuthorSet.add(authors.getText());
							}

							Utilities.saveandcloseCharles(testName);
							DataMonkPage.moreFromAuthorEbboks();

							if (DataMonkPage.authorFlag == true) {
								if (DataMonkPage.titleList.contains(ebookMfromTitleList)
										&& ebookMfromAuthorSet.size() == 1 && DataMonkPage.authorSet.size() == 1
										&& ebookMfromAuthorSet.equals(DataMonkPage.authorSet)) {
									test.log(LogStatus.PASS, "Verified Test Case: " + testCase1 + " -----> " + testId1);
									if (!Utilities.setResultsKids(testId1, "Pass"))
										test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								} else {
									test.log(LogStatus.FAIL,
											"Failed to Verify Test Case: " + testCase1 + " -----> " + testId1);
									if (!Utilities.setResultsKids(testId1, "Fail"))
										test.log(LogStatus.WARNING, "TC ID not found in the tc document");
								}
							} else {
								test.log(LogStatus.FAIL, "Ebooks were not dislayed under more from author tray");
								test.log(LogStatus.FAIL,
										"Failed to Verify Test Case: " + testCase1 + " -----> " + testId1);
								if (!Utilities.setResultsKids(testId1, "Fail"))
									test.log(LogStatus.WARNING, "TC ID not found in the tc document");
							}

						}

						else {
							BasePageV2.reportFail("More From Author tab was not displayed");
						}
					} else {
						BasePageV2.reportFail("Up arrow button was not displayed post double tapping on Reader screen");
					}

				} else
					BasePageV2.reportFail("Ebook was not displayed");

			} else
				BasePageV2.reportFail("Download Icon was not displayed");
		} catch (Exception e) {
			BasePageV2.reportFail("Unable to Navigate to: " + ebookTitle + " detail page");
		}

		// Validating Related Shows Test Case
		try {
			driver.closeApp();
		} catch (Exception e) {

		}

		Utilities.openCharles();

		driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

		Thread.sleep(10000);

		HomePageV2.tabClick("Watch");
		try {
			Response resp = Utilities.requestUtility(watchPageAPI);
			showTitle = resp.jsonPath().getString("assets.items[0].title");

		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to fetch Episode Response from API");
		}

		test.log(LogStatus.INFO, "Navigating to show: " + showTitle);
		xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@text='"
				+ showTitle + "']";
		Utilities.verticalSwipe(driver, xpath);
		try {
			WebElement show = driver.findElement(By.xpath(xpath));
			show.click();
		} catch (Exception e) {
			Utilities.saveandcloseCharles(testName);
			BasePageV2.reportFail("Failed to navigate to show: " + showTitle);
		}

		String end = "//android.widget.TextView[@text='RELATED SHOWS']";

		Utilities.verticalSwipe(driver, end, "visible", 20);
		Utilities.saveCharles(testName);
		DMPageV2.fetchShowsandMediaTypeRelatedShows();

		DataMonkPage.relatedShowsFetch();

		Result.clear();
		if (DataMonkPage.showsList.size() > 1 && DMPageV2.mediaTypeFlag == true) {
			test.log(LogStatus.INFO, "Related Shows tary contains only 'Shows'");

			for (String show : DataMonkPage.showsList) {
				String xpath = "//android.widget.TextView[@text='" + show + "']";
				try {
					driver.findElement(By.xpath(xpath));
					test.log(LogStatus.INFO, "Verified Show: " + show);
					Result.add("Pass");
				} catch (Exception e) {
					// String xpath = "//android.widget.TextView[@text='"+show+"']";
					try {
						Utilities.verticalSwipe(driver);
						driver.findElement(By.xpath(xpath));
						test.log(LogStatus.INFO, "Verified Show: " + show);
						Result.add("Pass");
					} catch (Exception e1) {
						test.log(LogStatus.FAIL, "Unable to find Show: " + show
								+ " Fetched from Recommendation tray under related Tray");
						Result.add("Fail");
					}
				}
			}

		} else {
			test.log(LogStatus.FAIL, "Related Shows was not found under Recommendations API");
			if (!Utilities.setResultsKids(testId2, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

		if (Result.contains("Pass") && !Result.contains("Fail")) {
			test.log(LogStatus.PASS, "Verified Test Case: " + testCase2 + " -----> " + testId2);
			if (!Utilities.setResultsKids(testId2, "Pass"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		} else {
			test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase2 + " -----> " + testId2);
			if (!Utilities.setResultsKids(testId2, "Fail"))
				test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		
		
		
		// Validating Related Audio Test Case
				try {
					driver.closeApp();
				} catch (Exception e) {

				}

				Utilities.openCharles();

				driver.startActivity(new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity"));

				Thread.sleep(10000);

				HomePageV2.tabClick("Listen");
				
				int rows = xls.getRowCount("Api");
				System.out.println("APi ROW count is " + rows);
				for (int rNum = 1; rNum <= rows; rNum++) {
					String apiname = xls.getCellData("Api", "API Name", rNum);
					if (apiname.equals("AudioBookCards")) {
						String ApiTab = xls.getCellData("Api", "Url", rNum);

						Response respTab = Utilities.requestUtility(ApiTab);

						audiobook = respTab.jsonPath().get("assets[1].assets[0].items[0].title");
						// Audiobooks.add(abooks);

					} else
						System.out.println("Unable to fetch tab from API");
				}

				try {
					String xpath = "//android.widget.TextView[contains(@text,'" + audiobook + "')]";

					Utilities.verticalSwipe(driver, xpath);
					WebElement ab = driver.findElement(By.xpath(xpath));

					if (Utilities.explicitWaitVisibleNew(driver, ab, 10))
						ab.click();
					else
						BasePageV2.reportFail("Audio Book is not displayed");
				} catch (Exception e) {
				}
				
				homepagev2.verifyAndProgressBar();

				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.playAudiobookbutton, 2)) {
					homepagev2.playAudiobookbutton.click();
				} else
					BasePageV2.reportFail("Play button is not displayed in audio detail page");

				try {
					if (Utilities.explicitWaitVisibleNew(driver, homepagev2.audioTitle, 2))
						contentTitle = homepagev2.audioTitle.getText();
					else
						BasePageV2.reportFail("Audio Title Not found");

				} catch (Exception e) {
				}

				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.audioplaylistExpand, 2)) {
					test.log(LogStatus.INFO,
							" 'Audio playlist expander (up arrow)' switch button is displayed and clicking on it");
					homepagev2.audioplaylistExpand.click();
					if(Utilities.explicitWaitVisibleNew(driver, homepagev2.related, 10))
						 homepagev2.related.click();
					else {
						BasePageV2.reportFail("Related Section was not displayed");
					}
				} else
					BasePageV2.reportFail(" 'Audio playlist expander (up arrow)' switch button is not displayed");
				
				Utilities.saveCharles(testName);

				DataMonkPage.relatedAudioStories();

				Result.clear();
				if (DataMonkPage.audioList.size() > 1) {
					//test.log(LogStatus.INFO, "Related Shows tary contains only 'Shows'");

					for (String show : DataMonkPage.audioList) {
						String xpath = "//android.widget.TextView[@text='" + show + "']";
						try {
							driver.findElement(By.xpath(xpath));
							test.log(LogStatus.INFO, "Verified Show: " + show);
							Result.add("Pass");
						} catch (Exception e) {
							// String xpath = "//android.widget.TextView[@text='"+show+"']";
							try {
								Utilities.verticalSwipe(driver);
								driver.findElement(By.xpath(xpath));
								test.log(LogStatus.INFO, "Verified Show: " + show);
								Result.add("Pass");
							} catch (Exception e1) {
								test.log(LogStatus.FAIL, "Unable to find Show: " + show
										+ " Fetched from Recommendation tray under related Tray");
								Result.add("Fail");
							}
						}
					}

				} else {
					test.log(LogStatus.FAIL, "Related Shows was not found under Recommendations API");
					test.log(LogStatus.FAIL, "Failed to Verify Test Case: " + testCase3 + " -----> " + testId3);
					if (!Utilities.setResultsKids(testId3, "Fail"))
						test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				}

				if (Result.contains("Pass") && !Result.contains("Fail")) {
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
