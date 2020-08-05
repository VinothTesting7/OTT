package com.viacom.channels;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.or.ThreadGroupRenderer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Verify_Watch_OnNow_Channels_List extends BaseTestV2 {

	// TODO Auto-generated constructor stub

	String testName = "Verify_Watch_OnNow_Channels_List";

	/*
	 * public static int rowno=0; public static int rowno1=0; public static int
	 * rowno2=0; public static int rowno3=0; public static int rowno4=0;
	 */
	@Test(dataProvider = "getData")
	public void verify_Watch_OnNow_Channels_List(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify_Watch_OnNow_Channels_List");
		test.log(LogStatus.INFO, "Starting the test to Verify OnNow/Channels List : " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno314 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno314,
				"Verify UI of 'On Now' list view page(PageTitle,BackArrow,10 cards Should be Loaded)");
		int rowno315 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno315,
				"Verify the scroll functionality of 'On Now' tray list view page");
		int rowno316 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno316, "Verify the order of displayed cards in OnNow List");
		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3,
				"Verify UI of 'Channels' list view page(PageTitle,BackArrow,10 cards Should be Loaded)");
		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4,
				"Verify the scroll functionality of 'Channels' tray list view page");
		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5,
				"Verify the order of displayed cards in Channels List");

		launchApp();
		
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		homepagev2.login(data.get("Email"),data.get("Password"));

		String titles = " ";
		String episode = "";

		ArrayList<String> descFE = new ArrayList<String>();
		ArrayList<String> VdurationFE = new ArrayList<String>();
		ArrayList<String> EpnoFE = new ArrayList<String>();
		// api check
		// calling Config API
		String url_Config = xls.getCellData("Api", 1, 3);
		Response resp_Config = Utilities.requestUtility(url_Config);
		ArrayList<Integer> Listof_OTT = new ArrayList<Integer>();
		resp_Config.then().assertThat().statusCode(200);
		resp_Config.prettyPrint();
		Map<String, Integer> ott = resp_Config.jsonPath().get("assets.OTT");
		for (Map.Entry<String, Integer> m : ott.entrySet())
			Listof_OTT.add(m.getValue());
		// End of calling config API
		
		
		int totalitemsofapi1 = 0;
		String apiname_channels= "";
		String apiname_Channels ="";
		String url_ChannelsTab ="";
		String totalitemsofapiAccestNull = "";
		int rows1=xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows1);
		for(int rNum1=2;rNum1<=rows1;rNum1++)
		{
			String apiname1=xls.getCellData("Api", "API Name", rNum1);
			System.out.println("Geeting String from API is " + apiname1);
			if(apiname1.equals("On Now"))
			{
				 url_ChannelsTab=xls.getCellData("Api", "Url", rNum1);
				 apiname_Channels = "On Now";
				 Map map=BasePageV2.apiparams(2, xls, apiname_Channels);
			
				 Response resp_allChannels=Utilities.requestUtilitypostOld(url_ChannelsTab, map);
				
					 totalitemsofapi1=resp_allChannels.jsonPath().get("assets.totalItems");
						System.out.println("Toatl Number of titles are in on Now Tab " + totalitemsofapi1 );
						totalitemsofapiAccestNull=resp_allChannels.jsonPath().get("assets");
						test.log(LogStatus.INFO, "The API Response is : " + totalitemsofapiAccestNull);
					 System.out.println("The APi reponse from API should be Null " + totalitemsofapiAccestNull );
			}else test.log(LogStatus.FAIL, "On now section not find in watch tab");
		}

		try {
            
			Activity activity = new Activity("com.viacom18.vootkids", "com.viacom18.vootkids.ui.home.VKHomeActivity");
			driver.startActivity(activity);
			
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					System.out.println("Watch tab is visibled in tab bar");
					homepagev2.watch_tab.click();
					test.log(LogStatus.INFO, "Clicked Watch tab ");
				} else {
					homepagev2.smokeresults("", rowno314, "Fail");
					BasePageV2.reportPass(
							"Not Navigating to onNow Channels Section and  kids channel should not be displayed");
				}
					
					
					String oNowText = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='On Now']";
//					driver.findElementByAndroidUIAutomator(
//							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
//									+ oNowText + "\").instance(0))");
					
					Utilities.verticalSwipe(driver, oNowText);
					Utilities.verticalSwipe(driver);

					if(totalitemsofapiAccestNull.equals("null")) {
						BasePageV2.reportPass("'On Now' Section tray having ZERO cards 'NOTHING HERE' ");
					}else test.log(LogStatus.INFO, "On Now Section having channels cards");

					
					if (homepagev2.watchtab_text.getAttribute("selected").equals("true")) {
						try {
							
							String end = "//android.widget.Button[@text='SEE ALL']";
							Utilities.verticalSwipe(driver, end);
							
							homepagev2.seeAll.click();
						} catch (Exception e) {
						
							BasePageV2.reportFail("Not able to click 'SEE ALL' Button / 'SEE ALL' Button is not Found Becuase on NOw have <=8 ");
							
						}
						
						Thread.sleep(10000);
						driver.findElementByAndroidUIAutomator(
								"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
										+ (homepagev2.watchtab_text.getText()) + "\").instance(0))");
						
						// Fetching Titles from front end and coping to List 
						List<WebElement> titlesinsideonNowscetion = driver
								.findElementsById("com.viacom18.vootkids:id/recent_recycler_view");
						for (int j = 0; j < titlesinsideonNowscetion.size(); j++) {
							titles = titlesinsideonNowscetion.get(j).getText().trim();
							episode = titles.substring(1, 3);
							if (!descFE.contains(titles))
								descFE.add(titles);
						}	
					
					Thread.sleep(1000);
					int totalitemsofapi11 = 0;
					String apiname_channels1= "";
					String apiname_Channels1 ="";
					String url_ChannelsTab1 ="";
					int rows11=xls.getRowCount("Api");
					System.out.println("APi ROW count is " + rows11);
					for(int rNum11=2;rNum11<=rows11;rNum11++)
					{
						String apiname11=xls.getCellData("Api", "API Name", rNum11);
						System.out.println("Getting String from API is " + apiname11);
						if(apiname11.equals("On Now"))
						{
							 url_ChannelsTab1=xls.getCellData("Api", "Url", rNum11);
							 apiname_Channels1 = "On Now";
							 Map map1=BasePageV2.apiparams(2, xls, apiname_Channels1);
						
							 Response resp_allChannels1=Utilities.requestUtilitypostOld(url_ChannelsTab1, map1);
							 try {
								 totalitemsofapi11=resp_allChannels1.jsonPath().get("assets.totalItems");
									System.out.println("Toatl Number of titles are in Channels Tab " + totalitemsofapi11 );
							} catch (Exception e) {
								e.printStackTrace();
							}
								// Remaining code as required
								
							if (descFE.size() == totalitemsofapi11) {
								homepagev2.smokeresults("", rowno314, "pass");
								BasePageV2.reportPass(
										"onNow Channels Section is selected and  kids channels should be displayed");

							}
							}
						}
					}

					
				
			

			launchApp();
			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					driver.findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
									+ (homepagev2.watchTab_channels.getText()) + "\").instance(0))");
					homepagev2.watchTab_channels.click();
		
//					List<WebElement> titlesinsideonNowscetion = driver
//							.findElementsById("com.viacom18.vootkids:id/recent_recycler_view");
					List<WebElement> titlesinsideonchannelsscetion = driver
							.findElementsById("com.viacom18.vootkids:id/recent_recycler_view");
					for (int j = 0; j < titlesinsideonchannelsscetion.size(); j++) {
						titles = titlesinsideonchannelsscetion.get(j).getText().trim();
						episode = titles.substring(1, 3);
						if (!descFE.contains(titles))
							descFE.add(titles);
					}
				
					
							// Remaining code as required
						
//						if (descFE.size() == totalitemsofapi2) {
//							driver.findElementByAndroidUIAutomator(
//									"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
//											+ (homepagev2.seeAll.getText()) + "\").instance(0))");
//							if (Utilities.explicitWaitVisible(driver, homepagev2.Chennels_Back_btn, 5)) {
//								if (Utilities.explicitWaitClickable(driver, homepagev2.Chennels_Back_btn, 5)) {
//									BasePageV2.takeScreenshot();
//									homepagev2.smokeresults("", rowno3, "pass");
//									BasePageV2.reportPass(
//											"Channels Section is selected and  kids channel should be displayed");
//								}
//							}
//
//						}

				
				}

			} else {

				BasePageV2.takeScreenshot();
				homepagev2.smokeresults("", rowno3, "Fail");
				BasePageV2.reportPass("Channels Section is not navigated  and  kids channel should not be displayed");

			}

		launchApp();

			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					homepagev2.watch_tab.click();
//					driver.findElementByAndroidUIAutomator(
//							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
//									+ (homepagev2.watchTab_channels.getText()) + "\").instance(0))");
					
					String text="Channels";
//					driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
					Utilities.verticalSwipe(driver, text);
					Utilities.verticalSwipe(driver);
					try {
						Thread.sleep(1000);
						homepagev2.watchTab_channels.click();
						Thread.sleep(1000);
						Utilities.verticalSwipe(driver);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
			
							if (totalitemsofapi1 > 8) {
								driver.findElementByAndroidUIAutomator(
										"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
												+ (homepagev2.seeAll.getText()) + "\").instance(0))");
								homepagev2.seeAll.click();
								Utilities.verticalSwipeDown(driver);
								Utilities.verticalSwipeDown(driver);
								if (Utilities.explicitWaitVisible(driver, homepagev2.channels_characters_CHANNELS_Text,
										10)) {
									test.log(LogStatus.PASS, "Verified CHANNELS Page having channels list ");
									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno4, "Pass");
									BasePageV2.reportPass("Channels Section is not navigated to CHANNELS List page");

								}
								ArrayList<String> charTitle = new ArrayList<String>();
								for (int i = 1; i < 10; i++) {
									try {

										WebElement cc = driver.findElement(By.xpath(
												"(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])["
														+ i + "]"));
										titles = cc.getText().trim();
										episode = titles.substring(1, 3);
										if (!charTitle.contains(titles))
											charTitle.add(titles);

									} catch (Exception e) {
										e.printStackTrace();
									}

								}

								if (charTitle.size() == totalitemsofapi1) {
									test.log(LogStatus.PASS, "Verified CHANNELS Page cards displayied order ");
									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno5, "Pass");

								} else {

									BasePageV2.takeScreenshot();
									homepagev2.smokeresults("", rowno5, "Fail");
									BasePageV2.reportFail("CHANNELS cards not found");
								}
							}else {
								BasePageV2.takeScreenshot();
								BasePageV2.reportFail("'SELL Button' Not Found becuase Number of Channels are < 8  ");
							}
						
					
				} else {
					homepagev2.smokeresults("", rowno4, "Fail");
					BasePageV2.reportFail("CHANNELS List page not found as well Watch Tab also");
				}

			}
				
					
		} catch (Exception e) {
			homepagev2.smokeresults("", rowno314, "Fail");
			homepagev2.smokeresults("", rowno3, "Fail");
			homepagev2.smokeresults("", rowno4, "Fail");
			homepagev2.smokeresults("", rowno5, "Fail");
			BasePageV2.reportFail("Watch tab icon not found in home page");
		
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);

	}

}
