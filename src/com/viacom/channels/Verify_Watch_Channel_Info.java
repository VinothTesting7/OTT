package com.viacom.channels;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.ChannelsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;

public class Verify_Watch_Channel_Info extends BaseTestV2 {

	String testName = "VerifyWatchChannelInfo";

	/*
	 * public static int rowno=0; public static int rowno1=0; public static int
	 * rowno2=0; public static int rowno3=0; public static int rowno4=0;
	 */
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("Verify Watch_Channel Info ");
		test.log(LogStatus.INFO, "Starting the test to Verify Watch_Channel Info: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno,
				"Verify Back arrow functionality in Channel Info page");

		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5,
				"Verify the segmented tabs available in Channel Info page below the live content section");
		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6,
				"Verfiy the UI of Characters tab when the tab has less than 8 cards:");
		int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno7,
				"Verfiy the navigation on tapping on any of the kids character.");
		int rowno8 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno8,
				"Verify the functionality on switching to Schedule tab.");
		launchApp();
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		ChannelsPageV2 channelsPageV2 = new ChannelsPageV2(driver, test);
		
		 homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		String  justforutext = " ",justforutext1=" ";
		String titleName="";
		String titles=" ";
		String episode="";
		String vduration="";
		ArrayList<String> descTilesChannels=new ArrayList<String>();

		//calling Config API
		String url_Config=xls.getCellData("Api", 1, 3);
		Response resp_Config=Utilities.requestUtility(url_Config);
		ArrayList<Integer> Listof_OTT = new ArrayList<Integer>();
		resp_Config.then().assertThat().statusCode(200);
		resp_Config.prettyPrint();
		Map<String,Integer> ott=resp_Config.jsonPath().get("assets.OTT");
		for(Map.Entry<String, Integer> m :ott.entrySet())			    
			Listof_OTT.add(m.getValue());			    
		//End of calling config API

		Thread.sleep(1000);
		int totalitemsofapi = 0;
		String apiname_channels= "";
		String apiname_Channels ="";
		String url_ChannelsTab ="";
		String statusDataNotFound = "";
		int totalTilesOfapi=0;
		int rows=xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for(int rNum=2;rNum<=rows;rNum++)
		{
			String apiname=xls.getCellData("Api", "API Name", rNum);
			System.out.println("Geeting String from API is " + apiname);
			if(apiname.equals("Channels"))
			{
				url_ChannelsTab=xls.getCellData("Api", "Url", rNum);
				apiname_Channels = "Channels";
				Map map=BasePageV2.apiparams(2, xls, apiname_Channels);

				Response resp_allChannels=Utilities.requestUtilitypostOld(url_ChannelsTab, map);

				totalitemsofapi=resp_allChannels.jsonPath().get("assets.totalItems");
				totalTilesOfapi=resp_allChannels.jsonPath().get("assets.items.size()");
				test.log(LogStatus.INFO, "Size of Channels section tray is: " + totalTilesOfapi);
				
				for(int i=0;i<totalTilesOfapi;i++)
				{
					String titlefromapi=resp_allChannels.jsonPath().get("assets.items["+i+"].channelName");
					test.log(LogStatus.INFO, "Channles cards titles from API is : " + titlefromapi);
					descTilesChannels.add(titlefromapi);
				}

			}

			if(apiname.equals("Characters")) {
				url_ChannelsTab=xls.getCellData("Api", "Url", rNum);
				apiname_Channels = "Characters";
				Map map=BasePageV2.apiparams(2, xls, apiname_Channels);

				Response resp_allChannels=Utilities.requestUtilitypostOld(url_ChannelsTab, map);
				totalTilesOfapi=resp_allChannels.jsonPath().get("assets.items.size()");
				test.log(LogStatus.INFO, "Size of Characters section tray is: " + totalTilesOfapi);
				for(int i=0;i<totalTilesOfapi;i++)
				{
					String titlefromapi=resp_allChannels.jsonPath().get("assets.items["+i+"].title");
					test.log(LogStatus.INFO, "The titles from Characters API is : " + titlefromapi);
					descTilesChannels.add(titlefromapi);
				}
				 try {
		        	 statusDataNotFound=resp_allChannels.jsonPath().get("assets");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		

	    test.log(LogStatus.INFO, "API Responce of cards not found in channels section "+ statusDataNotFound);



// From API Channels Section have Nothing cards means Zero 

		if(statusDataNotFound == null)  {
			Activity activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
			driver.startActivity(activity);
			if(Utilities.explicitWaitVisible(driver, homepagev2.onNowNothingHere, 10)) {
				test.log(LogStatus.INFO, "'NOTHING HERE' cards are not present in Channels Section");
			
			BasePageV2.reportFail("Channels Scetion does not Have cards 'NOTHING HERE'");
			homepagev2.smokeresults("", rowno, "Fail");
			homepagev2.smokeresults("", rowno5, "Fail");
			homepagev2.smokeresults("", rowno6, "Fail");
			homepagev2.smokeresults("", rowno7, "Fail");
			homepagev2.smokeresults("", rowno8, "Fail");
			}
// From API if Channels Cards Count <= 8 
		}else if (totalitemsofapi <= 8 ) {
			System.out.println("Totoal number of Tiles in Loop  <=8 " + totalitemsofapi);
			System.out.println("Array Titles Size is " +descTilesChannels.size() );

			Activity activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
			driver.startActivity(activity);
			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10))
			{
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					
					try {
						homepagev2.watch_tab.click(); // clicking on Channels tab	
					} catch (Exception e) {
						BasePageV2.reportFail("Not clicked Watch tab / not found");
					}
					
					Thread.sleep(1000);
					String text="//android.widget.TextView[@text='Channels']";
					//					driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
					Utilities.verticalSwipe(driver, text);
					if(Utilities.explicitWaitVisible(driver, homepagev2.watchTab_channels, 2)) {
					homepagev2.watchTab_channels.click();
					}else test.log(LogStatus.FAIL, "unble to find 'channels' section in Watch tab / not clicked");
					Utilities.verticalSwipe(driver);
					Thread.sleep(1000);
					test.log(LogStatus.PASS, "Characters Tab having < 8 cards Verified ");
					homepagev2.takeScreenshot();
					homepagev2.smokeresults("", rowno6, "pass");
					Utilities.verticalSwipe(driver);
					for(int i = 0 ;i < descTilesChannels.size(); i++  ) {
						String Tiltle = descTilesChannels.get(i).toString().trim();
						try {
							WebElement cardTitle = driver.findElementByXPath("//android.widget.TextView[@text='"+Tiltle+"']");
							cardTitle.click();
							test.log(LogStatus.INFO, "Scuussfully clicked on fist card of Channles section tray");
						} catch (Exception e) {
							test.log(LogStatus.FAIL, "Unable to click on First card in Channels Section tray");
							break;
						}
						
						if(Tiltle != null) {
							break;
						}

					}

					if(Utilities.explicitWaitVisible(driver, homepagev2.channels_characters, 10)) {
						
						test.log(LogStatus.PASS, "Characters Tab kid charcter card tapped");
						//BasePageV2.takeScreenshot();
						homepagev2.smokeresults("", rowno7, "pass");
					}else BasePageV2.reportFail("Channels Cards not found in Channels Section Tab");
					
					if (Utilities.explicitWaitVisible(driver, homepagev2.channels_characters, 10)) {
						if (homepagev2.channels_characters.isSelected()
								&& homepagev2.channels_schedule.isDisplayed()) {
							test.log(LogStatus.PASS," Verified Character - should be highligted by default ");
							homepagev2.smokeresults("", rowno5, "Pass");
							if(Utilities.explicitWaitClickable(driver, homepagev2.channels_schedule, 10)) {
								homepagev2.channels_schedule.click();
								test.log(LogStatus.PASS," Virified Schedule tab tapped and  hightlited ");
								homepagev2.smokeresults("", rowno8, "Pass");
							}else BasePageV2.reportFail("'schedule' button option is not found ");
						}
					}else BasePageV2.reportFail("Channels Characters Section not found in Chaneels Section");
				}
				if(Utilities.explicitWaitVisible(driver, channelsPageV2.charactersLiveCardBackBTN, 10)) {
					channelsPageV2.charactersLiveCardBackBTN.click();
					if(Utilities.explicitWaitVisible(driver, homepagev2.watchTab_channels, 20)) {
						test.log(LogStatus.PASS,"Verify Back arrow functionality in Channel Info page");
						homepagev2.smokeresults("", rowno, "pass");

					}
				}else BasePageV2.reportFail("Not able to verify Back Arrow Button functionality in Channel Info page");


			}

			

//From  APi Channels have > 8 cards 
		}else if (totalitemsofapi > 8) {
			System.out.println("Totoal number of Tiles in Loop > 8 " + totalitemsofapi);
			Activity activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
			driver.startActivity(activity);
			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10))
			{
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					
					try {
						homepagev2.watch_tab.click(); // clicking on Channels tab	
					} catch (Exception e) {
						BasePageV2.reportFail("Not clicked Watch tab / not found");
					}
					
					Thread.sleep(1000);

					String text="//android.widget.TextView[@text='Channels']";
					//							driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
					Utilities.verticalSwipe(driver, text);
					try {
						test.log(LogStatus.INFO, "Clicking Channels section tab in watch Tab");
					    homepagev2.watchTab_channels.click();
					}catch(Exception e) {
						test.log(LogStatus.FAIL, "Channels Section not found in Watch Tab");
					}
					Utilities.verticalSwipe(driver);

					if (Utilities.explicitWaitVisible(driver, homepagev2.watchTab_channels, 10)) {
						try {
							
							homepagev2.watchTab_channels.click();
							test.log(LogStatus.INFO,"Sccussfully clicked Channels section tab in Watch page" );
							Thread.sleep(10000);
						} catch (Exception e) {
                            test.log(LogStatus.INFO, "Not able to clicked Channels sction tab ");
							e.printStackTrace();
						}
						test.log(LogStatus.INFO, "Total number of crads having in channels section tray is "+ totalitemsofapi); 
						if(Utilities.explicitWaitVisible(driver, homepagev2.channels_characters, 10)) {
							homepagev2.Chennels_Back_btn.click();
						}else BasePageV2.reportFail("Channels card Main page back button is not found or else not tapped ");

						String end = "//android.widget.Button[@text='SEE ALL']";
						if (Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
							Utilities.verticalSwipe(driver, end);
							try {
								homepagev2.seeAll.click();
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Not clicked 'SEE ALL' BUtton ");
							}
							
							if (Utilities.explicitWaitClickable(driver, homepagev2.Chennels_Back_btn, 10)) {
								//BasePageV2.takeScreenshot();
								homepagev2.Chennels_Back_btn.click();
								if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
									homepagev2.smokeresults("", rowno, "Pass");
									homepagev2.takeScreenshot();
									BasePageV2
									.reportPass("Vrifyied Back arrow functionality in Channel Info page ");
								} else {
									homepagev2.smokeresults("", rowno, "Fail");
									BasePageV2.reportFail(" Back arrow is not found in Channel Info page ");
								}
							}else test.log(LogStatus.FAIL, "Back Button not clicked in Channels section page/ not found");
						}else BasePageV2.reportFail("'SEE All' Button not found to navigate to Chanells List Main Page");



					} else {
						homepagev2.smokeresults("", rowno, "Fail");
						BasePageV2.reportFail(" Channel Info page not found ");

					}
				}

			}else {

				BasePageV2.reportFail("Watch tab option is not Found for naviagting to Watch Tab Main Page");
			}





		}else {

			BasePageV2.reportFail("Channels API not REsponding or else Not feltching data");
		}


	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

}
