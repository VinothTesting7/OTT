package com.viacom.channels;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;

public class Verify_Watch_ChannelsTab_Section extends BaseTestV2 {

	

	String testName = "VerifyWatchChannelsTabSection";
	
	
	String title = "" ;
	
	@Test(dataProvider = "getData")
	public void verifyWatchChannelsTabSection(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyWatchChannelsTabSection");
		test.log(LogStatus.INFO, "Starting the test to Verify Channels Section: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno,"Verify the UI of Channels tab");	
		int rowno1=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno1,"Verify the availibility of 'See All' button in Channels tab if there are > 8 channels");	
		int rowno2=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno2,"Verify the 'See All' button should not displayed in Channels tab  if there are < 8 channels");	
		int rowno3=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno3,"'See All' button should NOT be displayed in Channels tab if the tab contains exact 8 channels.");	
		int rowno4=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno4,"Verify the 'See All' button functionality");	
		int rowno5=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno5,"Verify the navigation on tapping on to any of the channels:");

		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);		
		
		

		 homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		String  justforutext = " ",justforutext1=" ";
		String titleName="";
		String titles=" ";
		String episode="";
		String vduration="";
		ArrayList<String> descTilesChannels=new ArrayList<String>();		
		//api check		
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

		//calling Channels API 
		Thread.sleep(1000);
		int totalitemsofapi = 0;
		String apiname_channels= "";
		String apiname_Channels ="";
		String url_ChannelsTab ="";
		int totalTilesOfapi = 0;
		String statusDataNotFound = "";
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
					 test.log(LogStatus.INFO, "Toatl Number of titles are in Channels Tab : " + totalitemsofapi);
						
						 totalTilesOfapi=resp_allChannels.jsonPath().get("assets.items.size()");
						 System.out.println(totalTilesOfapi);
						    for(int i=0;i<totalTilesOfapi;i++)
					      {
						    	String titlefromapi=resp_allChannels.jsonPath().get("assets.items["+i+"].channelName");
						    	test.log(LogStatus.INFO, "Channles cards titles from API is : " + titlefromapi);
						         descTilesChannels.add(titlefromapi);
					      }
						    
						    try {
			                	 statusDataNotFound=resp_allChannels.jsonPath().get("assets");
							} catch (Exception e) {
								e.printStackTrace();
							}
				
						    test.log(LogStatus.INFO, "API Responce of cards not found in channels section "+ statusDataNotFound);
			}
			
		}
			
        // ENd Channels API

		
			Activity activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
			driver.startActivity(activity);
			if (Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 10)) {
				if (Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
					System.out.println("Watch tab is visibled in tab bar");
					try {
						homepagev2.watch_tab.click(); // clicking on Channels tab	
					} catch (Exception e) {
						BasePageV2.reportFail("Not clicked Watch tab / not found");
					}
					
					System.out.println("Clicked Watch tab");
					String text="//android.widget.TextView[@text='Channels' or @resource-id='com.viacom18.vootkids:id/tab_title']";
//					driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
					Utilities.verticalSwipe(driver, text);
					Utilities.verticalSwipe(driver);
					
					
					
					try {
						Thread.sleep(2000);
						homepagev2.watchTab_channels.click();
						test.log(LogStatus.INFO, "Succussfully clicked Channels section tab");
						Thread.sleep(2000);
						Utilities.verticalSwipe(driver);
					} catch (Exception e) {
						
						test.log(LogStatus.FAIL, "Not clicked Channels Section / not found");
					}
					
					test.log(LogStatus.PASS,"Channels Section is selected and  kids channel should be displayed");
					homepagev2.smokeresults("", rowno, "pass");
					
				}else {
					
					BasePageV2.reportFail("Channels Section does not selected and kids channels not displayed");
					
				}

				
							
					// From API channels have Zero crads means 'Nothing Displayed'			
				if(homepagev2.watchTab_channels.getAttribute("selected").equals("true") ) {
									if(statusDataNotFound == null) {
										Utilities.verticalSwipe(driver);
										test.log(LogStatus.INFO, "'Nothing here is Displayed' , No Channels contained in Channels Main Section");	
										homepagev2.takeScreenshot();
										
			// From APi channels have >8 cards 							
									}else if (totalitemsofapi > 8) {
										
										String end="//android.widget.Button[@text='SEE ALL']";
										Utilities.verticalSwipe(driver, end);
											
											if(totalitemsofapi > 8)
											{
												
												test.log(LogStatus.PASS,"Verify the availibility of 'See All' button in 'Channels' as the tray has More than 8 channels");
												homepagev2.smokeresults("",rowno1, "pass");

												if(Utilities.explicitWaitClickable(driver, homepagev2.seeAll, 10))
												{
													test.log(LogStatus.PASS,"Verify the availibility of 'See All' button in 'Channels' as the tray has More than 8 channels");
													homepagev2.smokeresults("",rowno1, "pass");
													
													try {
														homepagev2.seeAll.click();
													} catch (Exception e) {
														test.log(LogStatus.FAIL, "Unable to click SEE ALL button / not found");
													}
													
													Thread.sleep(10000);
													test.log(LogStatus.PASS,"Verify 'See All' button is tapped in 'Channels' as the tray has More than 8 channels");
													homepagev2.smokeresults("",rowno4, "pass");
													
													for (int i = 0; i <=10 ; i++) {
														WebElement channelTap=driver.findElement(By.xpath("(//android.view.ViewGroup//android.widget.TextView[@resource-id='com.tv.vootkids:id/grid_title'])["+i+"]")); 
														channelTap.click();
														Thread.sleep(10000);
														test.log(LogStatus.PASS,"Verifyied the navigation on tapping on to any of the channels");
														//BasePageV2.takeScreenshot();
														homepagev2.smokeresults("",rowno5, "pass");
														break;
													}

												}else test.log(LogStatus.FAIL, "'SEE ALL' button not found in Channels section tray");


											}else test.log(LogStatus.FAIL, "Totol titles not found > 8  in Channels page ");

										

   // From API channels section have <= 8 cards 
									  }else if (totalitemsofapi <= 8) 
									{
										
										if(Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10))
										{	String end="//android.widget.Button[@text='SEE ALL']";
										    Utilities.verticalSwipe(driver, end);	
										    
										    	//  Not Found 'SEE ALL' Button need to get Fail this Condition 

										}
										if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 10)) {
										    BasePageV2.reportFail("SEE ALL Button Found when Channels have <= 8 ");
										
									    }else {
											
											test.log(LogStatus.PASS,"Verify the availibility of 'See All' button should not displayed in 'Channels' as the tray has < 8 channels");
											homepagev2.smokeresults("",rowno2, "pass");
											test.log(LogStatus.PASS,"Verify the availibility of 'See All' button should not displayed in 'Channels' as the tray has  8 channels");
											homepagev2.smokeresults("",rowno3, "pass");
											test.log(LogStatus.PASS,"Virifyied 'Sell All' is not there in Channels Section");
										}
										
										
										
									       for(int i = 0 ;i < descTilesChannels.size(); i++  ) {
						                    	 title = descTilesChannels.get(i).toString().trim();
						                    	 
						                    	 try {
						                    		 WebElement cardTitle = driver.findElementByXPath("//android.widget.TextView[@text='"+title+"']");
								                     cardTitle.click();
						 							test.log(LogStatus.INFO, "Scuussfully clicked on fist card of Channles section tray");
						 						} catch (Exception e) {
						 							test.log(LogStatus.FAIL, "Unable to click on First crad in Channels Section tray");
						 							break;
						 						}
						                    	 
						                    	if(title != null) {
						                    		break;
						                    	}
						                    	
						                    }
				//Navigating to Card Redirected Page 							
									       
									       WebElement cardTitle = driver.findElementByXPath("//android.widget.TextView[@text='"+ title +"']");
									       String Redirchannel = cardTitle.getText().toString().trim();
									       
									 if(title.equalsIgnoreCase(Redirchannel)) {
											test.log(LogStatus.PASS,"Verified the navigation on tapping on to any of the channels");
											homepagev2.smokeresults("", rowno5, "pass");
									 }else BasePageV2.reportFail("Channels Cards not found in Channels Section Tab");
										
				
			
									}else {

										homepagev2.smokeresults("",rowno1, "Faill");
										homepagev2.smokeresults("",rowno2, "Faill");
										homepagev2.smokeresults("",rowno3, "Faill");
										homepagev2.smokeresults("",rowno4, "Faill");
										homepagev2.smokeresults("",rowno5, "Faill");
										BasePageV2.reportFail("Channels Section not clicksble or API data is empty");
									}

								}else test.log(LogStatus.FAIL, "Channels section is not selected or not found in Watch page");
                                      BasePageV2.takeScreenshot();
			
			
		}

	}	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}

}
