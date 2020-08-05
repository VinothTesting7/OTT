package com.viacom.channels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
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

public class Verify_Watch_On_Now_Section extends BaseTestV2 {
	String testName = "Verify watch tab 'On Now' Section";

	/*
	 * public static int rowno=0; public static int rowno2=0; public static int
	 * rowno3=0; public static int rowno4=0;
	 */
	
	String title = "";
	@Test(dataProvider = "getData")
	public void verifyWatchOnNowSection(Hashtable<String, String> data) throws Exception {

		test = rep.startTest("Verify watch tab 'On Now' Section");
		test.log(LogStatus.INFO,
				"Starting the test to Verify Watch tab 'On Now' Section: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, "Verify the UI of On Now and Channels tab");
		int rowno1 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno1,
				"Verify the navigation between On Now and Channels tabs");
		int rowno2 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno2,
				"Verify the metadata for the cards present under 'On Now' tab");
		int rowno3 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno3,
				"Verify the availibility of See All button in On Now tab if there are  > 8 live contents");
		int rowno4 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno4,
				"Verify the availibility of 'See All' button in On Now tab if there are  < 8 live contents");
		int rowno5 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno5,
				"Verify the availibility of 'See All' button in On Now tab if there are exact 8 live contents");
		int rowno6 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno6,
				"Verify the functionality on tapping on any of the live content card");
		int rowno7 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno7, "Verify the 'See All' functionality");
		int rowno8 = xls.getRowCount("Smoke_Results") + 1;
		
		launchApp();
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		
		
		homepagev2.login(data.get("Email"),data.get("Password"));
		
		
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

		// start API validation
		ArrayList<String> descTilesChannels=new ArrayList<String>();
		Thread.sleep(1000);
		int totalitemsofapi = 0;
		String apiname_channels= "";
		String apiname_Channels ="";
		String url_ChannelsTab ="";
		int totalTilesOfapi=0;
		 String statusDataNotFound="";
		int rows=xls.getRowCount("Api");
		System.out.println("APi ROW count is " + rows);
		for(int rNum=2;rNum<=rows;rNum++)
		{
			String apiname=xls.getCellData("Api", "API Name", rNum);
			System.out.println("Geeting String from API is " + apiname);
			if(apiname.equals("On Now"))
			{
				url_ChannelsTab=xls.getCellData("Api", "Url", rNum);
				apiname_Channels = "On Now";
				Map map=BasePageV2.apiparams(2, xls, apiname_Channels);

				Response resp_allChannels=Utilities.requestUtilitypostOld(url_ChannelsTab, map);
                 try {
                	 totalitemsofapi=resp_allChannels.jsonPath().get("assets.totalItems");
                	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
                 try {
                	 totalTilesOfapi=resp_allChannels.jsonPath().get("assets.items.size()");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
                 try {
                	 statusDataNotFound=resp_allChannels.jsonPath().get("assets");
				} catch (Exception e) {
					e.printStackTrace();
				}
                 
			
				System.out.println("onNow is Empty no channels " + statusDataNotFound);
				System.out.println("The items size is " + totalTilesOfapi);
				for(int i=0;i<totalTilesOfapi;i++)
				{
					String titlefromapi=resp_allChannels.jsonPath().get("assets.items["+i+"].title");
					System.out.println("The titles from API is " + titlefromapi );
					descTilesChannels.add(titlefromapi);
				}

			}

		}	 

		// End API Validation
		


		try {
   
			Activity activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
			driver.startActivity(activity);
			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 20)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 20)) {
					System.out.println("Watch tab is visibled in tab bar");
					homepagev2.watch_tab.click();

					try {
							String end="//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='On Now']";
							//String str = "Channels";
//							driver.findElementByAndroidUIAutomator(
//									"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
//											+ text + "\").instance(0))");
//							String end="Channels";
							//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+str+"\").instance(0))");
							Utilities.verticalSwipe(driver, end);
							Utilities.verticalSwipe(driver);
//					        homepagev2.watchTab_onNOwTab.click();
							if(homepagev2.watchTab_onNOwTab.isEnabled()) {
								test.log(LogStatus.PASS,"On Now tab should be highlighted by default");
								BasePageV2.smokeresults("", rowno, "pass");
							}else BasePageV2.reportFail("On Now tab does not highlighted by default ");
							
							if(Utilities.explicitWaitVisible(driver, homepagev2.watchTab_channels, 10)) {
								homepagev2.watchTab_channels.click();
								Utilities.verticalSwipe(driver);
								Thread.sleep(1000);
								if(homepagev2.watchTab_channels.isEnabled()) {
									Thread.sleep(1000);
									homepagev2.watchTab_onNOwTab.click();
									Utilities.verticalSwipe(driver);
									if(homepagev2.watchTab_onNOwTab.isEnabled()) {
										test.log(LogStatus.PASS,"Verify the navigation between On Now and Channels tabs");
										BasePageV2.smokeresults("", rowno1, "pass");
									}else BasePageV2.reportFail("Not able to navigate to on Now tab");
								}else BasePageV2.reportFail("Not able to navigate to Channels tab ");
								
							}else BasePageV2.reportFail("Not able to Naviagte to Channels Section");
							
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
	
					
					
				// Validating the API data	
					
					try {
						
							if (homepagev2.watchtab_text.isSelected() == true ) {

								if (statusDataNotFound == null) {
									System.out.println("The Null is printing here of onNow Section "+ statusDataNotFound);
									if(Utilities.explicitWaitVisible(driver, homepagev2.watchTab_onNOwTab, 10)) {
									Utilities.verticalSwipe(driver);
									if(Utilities.explicitWaitVisible(driver, homepagev2.onNowNothingHere, 2)) {
										test.log(LogStatus.INFO, "Nothing here is Displayed");
										//BasePageV2.takeScreenshot();
									}else BasePageV2.reportFail("Unable to Found 'NOTHING HERE' in OnNow Section");
									
									}else BasePageV2.reportFail("on Now option is not found to navigate to onNow Section");
									
//From API if > 8 cards Validation in onNow Section

                          
								} else if (totalitemsofapi > 8) {
									String seeAll_btn = "//android.widget.Button[@text='SEE ALL']";
									Utilities.verticalSwipe(driver, seeAll_btn);
									Utilities.verticalSwipe(driver);
									if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 10)) {
									test.log(LogStatus.PASS, "'onNow Section' Sell All button availibility is Verified ");
									//BasePageV2.takeScreenshot();
									BasePageV2.smokeresults("", rowno3, "pass");
									if (homepagev2.seeAll.isDisplayed()) {
										homepagev2.seeAll.click();
										Thread.sleep(10000);
										test.log(LogStatus.PASS,"Verify the 'See All' functionality");
										//BasePageV2.takeScreenshot();
										BasePageV2.smokeresults("", rowno7, "pass");
										
									}else BasePageV2.reportFail("'SEE ALL' Button functionality is not verified ");
								}else BasePageV2.reportFail("'SEE ALL' button Not Found");

//Form API if <=8 cards validation	in onNow Section								
								} else if (totalitemsofapi <= 8) {
									
									if(Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10))
									{	String end="//android.widget.Button[@text='SEE ALL']";
									    Utilities.verticalSwipe(driver, end);	
									    
									    	//  Not Found 'SEE ALL' Button need to get Fail this Condition 

									}
									if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 10)) {
									    BasePageV2.reportFail("SEE ALL Button Found when Channels have <= 8 ");
									
								    }else {
										
										test.log(LogStatus.PASS,"Verify the availibility of 'See All' button should not displayed in 'Channels' as the tray has < 8 channels");
										BasePageV2.smokeresults("",rowno4, "pass");
										test.log(LogStatus.PASS,"Verify the availibility of 'See All' button should not displayed in 'Channels' as the tray has  8 channels");
										BasePageV2.smokeresults("",rowno5, "pass");
										///BasePageV2.takeScreenshot();
										
									}
									
									
									
								       for(int i = 0 ;i < descTilesChannels.size(); i++  ) {
					                    	 title = descTilesChannels.get(i).toString().trim();
					                    	WebElement cardTitle = driver.findElementByXPath("//android.widget.TextView[@text='"+title+"']");
					                    	cardTitle.click();
					                    	if(title != null) {
					                    		break;
					                    	}
					                    	
					                    }
			//Navigating to Card Redirected Page 							
								       
								       WebElement cardTitle = driver.findElementByXPath("//android.widget.TextView[@text='"+ title +"']");
								       String Redirchannel = cardTitle.getText().toString().trim();
								       
								 if(title.equalsIgnoreCase(Redirchannel)) {
									 Thread.sleep(1000);
										test.log(LogStatus.PASS,"Verified the navigation on tapping on to any of the channels");
										//BasePageV2.takeScreenshot();
										BasePageV2.smokeresults("", rowno6, "pass");
								 }else BasePageV2.reportFail("Channels Cards not found in Channels Section Tab");
																	
							} else {
								test.log(LogStatus.INFO, "'totalitemsofapi'count not found from API");
								BasePageV2.smokeresults("", rowno4, "Fail");
								BasePageV2.smokeresults("", rowno5, "Fail");
								BasePageV2.smokeresults("", rowno6, "Fail");
							}

						}
							
						
						
					} catch (Exception e) {

						BasePageV2.reportFail("API is not found ");

					}

				}
			}
			
		
		} catch (Exception e) {

			BasePageV2.reportFail("Watch Tab Icon Not Found to naviagte to Watch Main Page");
			BasePageV2.smokeresults("", rowno4, "Fail");
			BasePageV2.smokeresults("", rowno5, "Fail");
			BasePageV2.smokeresults("", rowno3, "Fail");
			BasePageV2.smokeresults("", rowno6, "Fail");
			BasePageV2.smokeresults("", rowno7, "pass");
		}
	
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
}

/*
 * rowno2 and rowno8 is not Applicable Test case number 301 in sheet
 */
