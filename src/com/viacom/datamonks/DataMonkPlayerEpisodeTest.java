package com.viacom.datamonks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DataMonkPage;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class DataMonkPlayerEpisodeTest extends BaseTestV2 {

	String testName = "DataMonkPlayerEpisodeTest";
	String apiURL = "";
	String ks = "", userid = "", profileid = "";
	String carouselEpisodeContent = "";
	boolean watchFlag = false;
	boolean watchClickFlag = false;
	String mediaId = "";
	int mediaType = 0;
	Map<String, String> valuesMap = new HashMap<String, String>();
	ArrayList<String> Result = new ArrayList<String>();

	@Test(dataProvider = "getData")
	public void DataMonkPlayerEpisode(Hashtable<String, String> data) throws Exception {
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		test = rep.startTest("DM-PLAYER_EPISODES Event check");
		test.log(LogStatus.INFO,
				"Starting the test to verify all parameters (Data Monks) -" + VootConstants.DEVICE_NAME);
		// Open charles application

		// Launch App

		launchApp();
		String email = data.get("Email");
		String password = data.get("Password");
		// driver.resetApp();
		HomePageV2.logout();

		Utilities.saveCharles(testName);

		HomePageV2.login(email, password);
		Utilities.saveCharles(testName);
		DataMonkPage.captureUidandPidandKs();
		ks = GlobalVariables.ksValue;
		userid = GlobalVariables.userId;
		profileid = GlobalVariables.profileId;

		apiURL = "https://api.vootkids.com/app/user/v1/getPersonalizedCarousel.json?tabId=home&uId=" + userid
				+ "&profileId=" + profileid + "&ks=" + ks + "&moduleId=23";

		Response resp = Utilities.requestUtility(apiURL);
		String countstring = resp.jsonPath().getString("assets.sliderCount");
		int count = Integer.parseInt(countstring);

		watchFlag = false;
		for (int i = 0; i < count; i++) {
			String contentType = resp.jsonPath().getString("assets.items[" + i + "].contentType");
			if (contentType.contains("Episode")) {
				carouselEpisodeContent = resp.jsonPath().getString("assets.items[" + i + "].title");
				mediaId = resp.jsonPath().getString("assets.items[" + i + "].mId");
				mediaType = resp.jsonPath().get("assets.items[" + i + "].mediaType");
				test.log(LogStatus.INFO,
						"Episode content displayed from Home Page carousel under API was: " + carouselEpisodeContent);
				watchFlag = true;
				break;
			}
		}

		if (watchFlag == true) {
			String xpath = "//*[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']//*[@text='"
					+ carouselEpisodeContent + "']";
			watchClickFlag = false;
			for (int i = 0; i < 25; i++) {
				try {
					driver.findElement(By.xpath(xpath)).click();
					watchClickFlag = true;
					test.log(LogStatus.INFO,
							"Episode content played from carousel in home page was: " + carouselEpisodeContent);
					Thread.sleep(3000);
					Utilities.saveCharles(testName);
					break;
				} catch (Exception e) {

				}
			}

			if (watchClickFlag == true) {

				DataMonkPage.playerEpisodeRequestfetch();

				if (DataMonkPage.dcseFlag == true) {
					valuesMap.clear();
					Result.clear();
					valuesMap.put("limit", "8");
					valuesMap.put("pageType", "PLAYER_EPISODE");
					valuesMap.put("userId", userid);
					valuesMap.put("userIdType", "GUID");
					valuesMap.put("catalogType", "EPISODE");
					valuesMap.put("profileId", profileid);
					valuesMap.put("total", "random");
					valuesMap.put("widgetTypes", "DIFFERENT_CATEGORY_SIMILAR_EPISODE");
					valuesMap.put("deviceType", "APP");
					valuesMap.put("deviceOsType", "ANDROID");
					valuesMap.put("offset", "0");
					valuesMap.put("followupId", "random");

					for (Map.Entry<String, String> playDM : valuesMap.entrySet()) {
						String key = playDM.getKey();
						key = key.replaceAll("\\{", "");
						key = key.replaceAll("\\}", "");
						key = key.replaceAll("\"", "");
						System.out.println("Displayed Key was: " + key);
						String dmVal = "";
						String expVal = "";
						try {
							dmVal = DataMonkPage.dcseMap.get(key);
							expVal = valuesMap.get(key);
							if (key.equals("total") || key.equals("followupId")) {
								if (dmVal.length() > 1) {
									test.log(LogStatus.INFO, "Verified '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE' and value displayed was: "
											+ dmVal);
									Result.add("Pass");
								} else {
									test.log(LogStatus.INFO, "Incorrect value displayed for '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE': " + dmVal);
									Result.add("Fail");
								}
							} else {
								if (dmVal.contains(expVal)) {
									test.log(LogStatus.INFO, "Verified '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE' and value displayed was: "
											+ dmVal);
									Result.add("Pass");
								} else {
									test.log(LogStatus.INFO, "Incorrect value displayed for '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE': " + dmVal);
									Result.add("Fail");
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} else {
					test.log(LogStatus.FAIL,
							"'DIFFERENT_CATEGORY_SIMILAR_EPISODE' widgetType response was not displayed for 'PLAYER_EPISODE'");
				}

				if (Result.contains("Pass") && !Result.contains("Fail")) {
					test.log(LogStatus.PASS,
							"Verified 'DM' Request for 'PLAYER_EPISODE' for Episode content under home page carousel");
				} else {
					test.log(LogStatus.FAIL,
							" Verification of 'DM' Request for 'PLAYER_EPISODE' for Episode content under home page carousel is Fail");
				}

				if (DataMonkPage.uneFlag == true) {
					Result.clear();
					valuesMap.clear();
					valuesMap.put("limit", "8");
					valuesMap.put("pageType", "PLAYER_EPISODE");
					valuesMap.put("userId", userid);
					valuesMap.put("userIdType", "GUID");
					valuesMap.put("itemId", mediaId);
					valuesMap.put("catalogType", "EPISODE");
					valuesMap.put("profileId", profileid);
					valuesMap.put("total", "random");
					valuesMap.put("widgetTypes", "UP_NEXT_EPISODE");
					valuesMap.put("deviceType", "APP");
					valuesMap.put("deviceOsType", "ANDROID");
					valuesMap.put("offset", "0");
					valuesMap.put("followupId", "random");

					for (Map.Entry<String, String> playDM : valuesMap.entrySet()) {
						String key = playDM.getKey();
						key = key.replaceAll("\\{", "");
						key = key.replaceAll("\\}", "");
						key = key.replaceAll("\"", "");

						System.out.println("Key was: " + key);
						String dmVal = "";
						String expVal = "";
						try {
							dmVal = DataMonkPage.dcseMap.get(key);
							expVal = valuesMap.get(key);
							if (key.equals("total") || key.equals("followupId")) {
								if (dmVal.length() > 1) {
									test.log(LogStatus.INFO, "Verified '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE' and value displayed was: "
											+ dmVal);
									Result.add("Pass");
								} else {
									test.log(LogStatus.INFO, "Incorrect value displayed for '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE': " + dmVal);
									Result.add("Fail");
								}
							} else {
								if (dmVal.contains(expVal)) {
									test.log(LogStatus.INFO, "Verified '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE' and value displayed was: "
											+ dmVal);
									Result.add("Pass");
								} else {
									test.log(LogStatus.INFO, "Incorrect value displayed for '" + key
											+ "' for widgetType 'DIFFERENT_CATEGORY_SIMILAR_EPISODE': " + dmVal);
									Result.add("Fail");
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				} else
					test.log(LogStatus.FAIL,
							"'UP_NEXT_EPISODE' widgetType response was not displayed for 'PLAYER_EPISODE'");

			} else {
				test.log(LogStatus.FAIL, "Unable to play Episode content from the carousel");
				BasePageV2.takeScreenshot();
				Utilities.saveCharles("Failure");
			}

		} else {
			test.log(LogStatus.WARNING, "Episode content was not displayed uder API Response");
			Utilities.saveCharles(testName);
		}

		if (Result.contains("Pass") && !Result.contains("Fail")) {
			test.log(LogStatus.PASS,
					"Verified 'DM' Request for 'PLAYER_EPISODE' for Episode content under home page carousel");
		} else {
			test.log(LogStatus.FAIL,
					" Verification of 'DM' Request for 'PLAYER_EPISODE' for Episode content under home page carousel is Fail");
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
