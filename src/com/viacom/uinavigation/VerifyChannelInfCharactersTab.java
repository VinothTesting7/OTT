package com.viacom.uinavigation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Base;
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
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

public class VerifyChannelInfCharactersTab extends BaseTestV2{
String testName = "VerifyChannelInfCharactersTab";
    
	String channelFirstCard = "",charactersFirstCard = "";
	@Test(dataProvider = "getData")
	public void verifyChannelInfCharactersTab(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyChannelInfCharactersTab");
		test.log(LogStatus.INFO, "Starting the test to Verify Channel Inf in CharactersTab " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int rowno314 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno314, "Verfiy the UI of Characters tab when the tab has more than 8 cards:");
		
		int rowno315 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno315, "Verfiy the UI of Characters tab when the tab has exactly 8 cards:");
		
		int rowno316 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno316, "Verfiy the UI of Characters tab when the tab has less than 8 cards:");
		
		int rowno317 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno317, "Verify the functionality of See All button in Characters tab.");
		
		int rowno318 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno318, "Verfiy the navigation on tapping on any of the kids character.");
		
		int rowno319 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno319, "Verify the functionality on switching to Schedule tab.");
		
		int rowno320 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno320, "Verfiy the Schedule tab UI");
		
		int rowno321 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno321, "Verify the functionality on tapping the Live running show/List of cards present under Schedule segmented tab:");
		
		int rowno322 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno322, "Verify the charcater Info page UI when there are no live contents:");
		
		int rowno323 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno323, "Verify the listed characters sorting order:");
		
		int rowno324 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno324, "Verfiy the functionality on tapping any of the character image:");
		
		
		//launching the vook kids Application
		launchApp();
		
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		ChannelsPageV2 channelsPageV2 = new ChannelsPageV2(driver, test);
		

		 homepagev2.login(data.get("Email"),data.get("Password"));
		
		
		
		
	
		ArrayList<String> descTilesChannels =new ArrayList<String>();
		ArrayList<String> charctersTiles =new ArrayList<String>();

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
		int totalitemsofapi = 0,totalCharactersTilesOfapi=0;
		String apiname_channels= "",apiname_Channels ="",url_ChannelsTab ="",statusDataNotFound = "";

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

				Response resp_allChannels=Utilities.requestUtilitypost(url_ChannelsTab, map);

				totalitemsofapi=resp_allChannels.jsonPath().get("assets.totalItems");
//				totalCharactersTilesOfapi=resp_allChannels.jsonPath().get("assets.items.size()");
				test.log(LogStatus.INFO, "Size of Channels section tray is: " + totalitemsofapi);
				
				for(int i=0;i<totalitemsofapi;i++)
				{
					String titlefromapi=resp_allChannels.jsonPath().get("assets.items["+i+"].channelName");
					test.log(LogStatus.INFO, "Channles cards titles from API is : " + titlefromapi);
					descTilesChannels.add(titlefromapi);
				}
				
				try {
		        	 statusDataNotFound=resp_allChannels.jsonPath().get("assets");
		        	 test.log(LogStatus.INFO, "Channel tray have 'ZERO' crdas / Nothing here");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			if(apiname.equals("Characters")) {
				url_ChannelsTab=xls.getCellData("Api", "Url", rNum);
				apiname_Channels = "Characters";
				Map map=BasePageV2.apiparams(2, xls, apiname_Channels);

				Response resp_allChannels=Utilities.requestUtilitypost(url_ChannelsTab, map);
				totalCharactersTilesOfapi=resp_allChannels.jsonPath().get("assets.totalItems");
				test.log(LogStatus.INFO, "Size of Characters section tray is: " + totalCharactersTilesOfapi);
				for(int i=0;i<totalCharactersTilesOfapi;i++)
				{   
					
				    String titlefromapi=resp_allChannels.jsonPath().get("assets.items["+i+"].title");
					
					test.log(LogStatus.INFO, "The titles from Characters API is : " + titlefromapi);
					charctersTiles.add(titlefromapi);
				}
				 try {
		        	 statusDataNotFound=resp_allChannels.jsonPath().get("assets");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		// navigating to Watch tab 
		
		homepagev2.tabClick("Watch");
		test.log(LogStatus.INFO, "clicked on the Watch Tab");
		
		try {
			Utilities.verticalSwipe(driver, homepagev2.watchTab_channels);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Not able to Swiped till channel section tab in Watch Page");
		}
		
		
		if(Utilities.explicitWaitClickable(driver, homepagev2.watchTab_channels, 10)) {
			homepagev2.watchTab_channels.click();
			Utilities.verticalSwipe(driver);
		}else BasePageV2.reportFail("Not able to Click 'Channel' section in Watch Tab page");
		 
		Utilities.verticalSwipe(driver);
		
		if(statusDataNotFound == null) {
			if(Utilities.explicitWaitVisible(driver, homepagev2.onNowNothingHere, 10)) {
			test.log(LogStatus.INFO, "'Nothing Here' channels section does not have crads ");
			BasePageV2.reportFail("Channel section Having 'Zero' cards ");
			}else test.log(LogStatus.FAIL, "Not able to found in 'Channel'Section have Nothing Here");
			
		}
		
		if (totalitemsofapi >= 1){   // clicking card in channel section to navigate to characters Tab
			String channelFirstCard = descTilesChannels.get(0).toString().trim();
			test.log(LogStatus.INFO, "The Array String is : " + channelFirstCard);
			try {
			   driver.findElementByXPath("//android.widget.TextView[@text='"+channelFirstCard+"']").click();
			   test.log(LogStatus.INFO, "Clciked the First card of Channel Section");
			} catch (Exception e) {
			     BasePageV2.reportFail("Not able to click 'First card' in Channel section");
			}
//	Verfiy the functionality on tapping any of the character image:		
			
			if(charctersTiles != null) {
				String charactersFirstCard = charctersTiles.get(0).toString().trim();
				test.log(LogStatus.INFO, "The charcters Aarry First Kid card is  : " + charactersFirstCard);
				try {
					   driver.findElementByXPath("//android.widget.TextView[@text='"+charactersFirstCard+"']").click();
					   test.log(LogStatus.INFO, "Clciked the First card of Characters Section");
					   test.log(LogStatus.PASS,"Verfiy the functionality on tapping any of the character image:");
					   BasePageV2.smokeresults("", rowno324, "PASS");
					} catch (Exception e) {
					     test.log(LogStatus.FAIL,"Not able to click 'First card' in Characters section");
					     BasePageV2.takeScreenshot();
					     BasePageV2.smokeresults("", rowno324, "FAIL");
					}
				
			}
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
			
			
//		Verify the functionality on switching to Schedule tab.	
			
		if(channelsPageV2.scheduleTab.getAttribute("selected").equals("false")) {
			channelsPageV2.scheduleTab.click();
			test.log(LogStatus.INFO, "clikced Schedule tab");
			test.log(LogStatus.PASS,"Verify the functionality on switching to Schedule tab");
			BasePageV2.smokeresults("", rowno319, "PASS");
		}else if(channelsPageV2.scheduleTab.getAttribute("selected").equals("true")){
			test.log(LogStatus.PASS,"Verify the functionality on switching to Schedule tab");
			BasePageV2.smokeresults("", rowno319, "PASS");
		}else {
			test.log(LogStatus.FAIL, "Not able to click Schedule tab");
			BasePageV2.takeScreenshot();
			BasePageV2.smokeresults("", rowno319, "FAIL");
		}
			
			
		}else BasePageV2.reportFail("'Nothing Here' Cards are Zero in channel ");
		
		
//	Verify the listed characters sorting order:
		
			if(channelsPageV2.charactersTab.getAttribute("selected") == "true") {
				List<String> unsortedList=new LinkedList<String>(charctersTiles); 
				Collections.sort(charctersTiles);
		   
				for(int i=0;i<charctersTiles.size();i++)
				{
				if(!charctersTiles.get(i).equals(unsortedList.get(i))) {
				test.log(LogStatus.FAIL, "Episode title is not in sorted order -"+charctersTiles.get(i));
				test.log(LogStatus.FAIL,"Verify the listed characters sorting order:");
				BasePageV2.smokeresults("", rowno323, "FAIL");
				}else if(i==charctersTiles.size()-1){
                  test.log(LogStatus.PASS,"Verify the listed characters sorting order:");
                  BasePageV2.smokeresults("", rowno323, "PASS");

				}
				
			}
				
			}else {
				channelsPageV2.charactersTab.click();
				List<String> unsortedList=new LinkedList<String>(charctersTiles); 
				Collections.sort(charctersTiles);
		   
				for(int i=0;i<charctersTiles.size();i++)
				{
				if(!charctersTiles.get(i).equals(unsortedList.get(i))) {
				test.log(LogStatus.FAIL, "Episode title is not in sorted order -"+charctersTiles.get(i));
				test.log(LogStatus.FAIL,"Verify the listed characters sorting order:");
				BasePageV2.smokeresults("", rowno323, "FAIL");
				}else if(i==charctersTiles.size()-1){
                  test.log(LogStatus.PASS,"Verify the listed characters sorting order:");
                  BasePageV2.smokeresults("", rowno323, "PASS");

				}
				
			}
		
		}
		 
		
		
		
		//characters section have cards == 8 	
		
		if (totalCharactersTilesOfapi == 8) {
			channelFirstCard = descTilesChannels.get(0).toString().trim();
			test.log(LogStatus.INFO, "The Array String is : " + channelFirstCard);
			//check redirected of channel card
			try {
				WebElement channelsFirstCardNavigate = driver.findElementByXPath("//android.widget.TextView[@text='"+channelFirstCard+"']");
				test.log(LogStatus.INFO, "Redirected to channel first card");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to redirected to first channel card ");
			}
			
//		Verfiy the navigation on tapping on any of the kids character.	
			charactersFirstCard = charctersTiles.get(0).toString().trim();
			test.log(LogStatus.INFO, "The Characters Array First String is : " + charactersFirstCard);
			try {
				WebElement channelsFirstCardNavigate = driver.findElementByXPath("//android.widget.TextView[@text='"+charactersFirstCard+"']");
				test.log(LogStatus.INFO, "Redirected to Kids Character first card");
				test.log(LogStatus.PASS,"Verfied the navigation on tapping on any of the kids character.");
				BasePageV2.smokeresults("", rowno318, "PASS");
				driver.pressKeyCode(AndroidKeyCode.BACK);
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to redirected to Kids Character first channel card ");
				BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("", rowno318, "FAIL");
			}
			

			
		//check Characters Tab Highlited 
			if(channelsPageV2.charactersTab.getAttribute("selected") == "true") {
				test.log(LogStatus.INFO, "default characters tab is selected");
			
			
//  Verfiy the UI of Characters tab when the tab has exactly 8 cards:
			
			// check cards presence in characters section
			       
						for (int i = 0; i < charctersTiles.size() ; i++ ) {
							String charCard = charctersTiles.get(i).toString().trim();
							  if (i == 7) {
								  Utilities.verticalSwipe(driver);
							  }
							
							try {
								driver.findElementByXPath("//android.widget.TextView[@text="+charCard+"]");
								
							} catch (Exception e) {
								Utilities.verticalSwipe(driver);
							}
							test.log(LogStatus.INFO, "1Card present in charcters section : " + charCard);
							if(Utilities.explicitWaitClickable(driver, channelsPageV2.seeAllButton, 10)) {
							   
								BasePageV2.reportFail("Verfiy the UI of Characters tab when the tab has exactly 8 cards:");
								BasePageV2.smokeresults("", rowno315, "FAIL");
								
							}else if(i==7) {
								test.log(LogStatus.PASS,"Verfiy the UI of Characters tab when the tab has exactly 8 cards:");
								BasePageV2.smokeresults("", rowno315, "PASS");
								break;
							}
							
						}
			}else {
				channelsPageV2.charactersTab.click();
				test.log(LogStatus.INFO, "clicked on characters tab");
				// check cards presence in characters section
			       
				for (int i = 0; i < charctersTiles.size() ; i++ ) {
					String charCard = charctersTiles.get(i).toString().trim();
					if (i == 7) {
						  Utilities.verticalSwipe(driver);
					  }
					
					try {
						driver.findElementByXPath("//android.widget.TextView[@text="+charCard+"]");
						
					} catch (Exception e) {
						Utilities.verticalSwipe(driver);
					}
					test.log(LogStatus.INFO, "1Card present in charcters section : " + charCard);
					if(Utilities.explicitWaitClickable(driver, channelsPageV2.seeAllButton, 10)) {
					   
						BasePageV2.reportFail("Verfiy the UI of Characters tab when the tab has exactly 8 cards:");
						BasePageV2.smokeresults("", rowno314, "FAIL");
						
					}
					else if(i==7)
					{
						test.log(LogStatus.PASS,"Verfiy the UI of Characters tab when the tab has exactly 8 cards:");
						BasePageV2.smokeresults("", rowno314, "PASS");
						break;
					}
					
				}
			
			}
			
	
			
			
			
			
			
			
			
		//characters section have cards < 8	
			
		}else if (totalCharactersTilesOfapi <  8) {
			channelFirstCard = descTilesChannels.get(0).toString().trim();
			test.log(LogStatus.INFO, "The Array String is : " + channelFirstCard);
			
			//check redirected of channel card
			try {
				WebElement channelsFirstCardNavigate = driver.findElementByXPath("//android.widget.TextView[@text='"+channelFirstCard+"']");
				test.log(LogStatus.INFO, "Redirected to channel first card");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to redirected to first channel card ");
			}
	
//			Verfiy the navigation on tapping on any of the kids character.  
			
			
			charactersFirstCard = charctersTiles.get(0).toString().trim();
			test.log(LogStatus.INFO, "The Characters Array First String is : " + charactersFirstCard);
			try {
				WebElement channelsFirstCardNavigate = driver.findElementByXPath("//android.widget.TextView[@text='"+charactersFirstCard+"']");
				test.log(LogStatus.INFO, "Redirected to Kids Character first card");
				test.log(LogStatus.PASS,"Verfied the navigation on tapping on any of the kids character.");
				BasePageV2.smokeresults("", rowno318, "PASS");
				driver.pressKeyCode(AndroidKeyCode.BACK);
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to redirected to Kids Character first channel card ");
				BasePageV2.takeScreenshot();
				BasePageV2.smokeresults("", rowno318, "FAIL");
			}
			
			
			
//			Verfiy the UI of Characters tab when the tab has less than 8 cards:
			
			
			//check Characters Tab Highlited 
			if(channelsPageV2.charactersTab.getAttribute("selected") == "true") {
				test.log(LogStatus.INFO, "default characters tab is selected");
			
			
//  Verfiy the UI of Characters tab when the tab has less than 8 cards:
			
			// check cards presence in characters section
			       
						for (int i = 0; i < charctersTiles.size() ; i++ ) {
							String charCard = charctersTiles.get(i).toString().trim();
							 if((i == 4)||(i == 5)) {
								 Utilities.verticalSwipe(driver);
							 }
							
							try {
								driver.findElementByXPath("//android.widget.TextView[@text="+charCard+"]");
								
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Not found Title in characters section " + charCard);
							}
							test.log(LogStatus.INFO, "1Card present in charcters section : " + charCard);
							if(Utilities.explicitWaitClickable(driver, channelsPageV2.seeAllButton, 10)) {
							   
								BasePageV2.reportFail("Verfiy the UI of Characters tab when the tab has less than 8 cards:");
								BasePageV2.smokeresults("", rowno316, "FAIL");
								
							}else if((i == charctersTiles.size())) {
								test.log(LogStatus.PASS,"Verfiy the UI of Characters tab when the tab has less than 8 cards:");
								BasePageV2.smokeresults("", rowno316, "PASS");
								
								
							}
							
						}
			}else {
				channelsPageV2.charactersTab.click();
				test.log(LogStatus.INFO, "clicked on characters tab");
				// check cards presence in characters section
			       
				for (int i = 0; i < charctersTiles.size() ; i++ ) {
					String charCard = charctersTiles.get(i).toString().trim();
					 if((i == 4)||(i == 5)) {
						 Utilities.verticalSwipe(driver);
					 }
					
					try {
						driver.findElementByXPath("//android.widget.TextView[@text="+charCard+"]");
						
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Not found Title in characters section " + charCard);
					}
					test.log(LogStatus.INFO, "1Card present in charcters section : " + charCard);
					if(Utilities.explicitWaitClickable(driver, channelsPageV2.seeAllButton, 10)) {
					   
						BasePageV2.reportFail("Verfiy the UI of Characters tab when the tab has less than 8 cards:");
						BasePageV2.smokeresults("", rowno316, "FAIL");
						
					}else if((i == charctersTiles.size())) {
						test.log(LogStatus.PASS,"Verfiy the UI of Characters tab when the tab has less than 8 cards:");
						BasePageV2.smokeresults("", rowno316, "PASS");
						
						
					}
					
				}
				
			}
			
			

			
			
			
	    // characters section have cards > 8 		
		}else if (totalCharactersTilesOfapi >  8) {
			channelFirstCard = descTilesChannels.get(0).toString().trim();
			test.log(LogStatus.INFO, "The Array String is : " + channelFirstCard);
			
			//check redirected of channel card
			try {
				WebElement channelsFirstCardNavigate = driver.findElementByXPath("//android.widget.TextView[@text='"+channelFirstCard+"']");
				test.log(LogStatus.INFO, "Redirected to channel first card");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to redirected to first channel card ");
			}
			
			
//			Verfiy the navigation on tapping on any of the kids character.	
				charactersFirstCard = charctersTiles.get(0).toString().trim();
				test.log(LogStatus.INFO, "The Characters Array First String is : " + charactersFirstCard);
				try {
					WebElement channelsFirstCardNavigate = driver.findElementByXPath("//android.widget.TextView[@text='"+charactersFirstCard+"']");
					test.log(LogStatus.INFO, "Redirected to Kids Character first card");
					test.log(LogStatus.PASS,"Verfied the navigation on tapping on any of the kids character.");
					BasePageV2.smokeresults("", rowno318, "PASS");
//					driver.pressKeyCode(AndroidKeyCode.BACK);
				} catch (Exception e) {
					test.log(LogStatus.FAIL, "Not able to redirected to Kids Character first channel card ");
					BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("", rowno318, "FAIL");
				}
				
			
			
			
			
			//check Characters Tab Highlited 
			
			
			if(channelsPageV2.charactersTab.getAttribute("selected").equals("true")) {
				test.log(LogStatus.INFO, "default characters tab is selected");

//  Verfiy the UI of Characters tab when the tab has more than 8 cards:
			
			// check cards presence in characters section
			       
							for (int i = 0; i < charctersTiles.size() ; i++ ) {
								String charCard = charctersTiles.get(i).toString().trim();
								
								    if(i == 3) {
								    	Utilities.verticalSwipe(driver);
								    }
								    
								    if (i == 6) {
										 Utilities.verticalSwipe(driver);
										 if(Utilities.explicitWaitClickable(driver, channelsPageV2.seeAllButton, 10)) {
												test.log(LogStatus.PASS,"Verfiy the UI of Characters tab when the tab has more than 8 cards:");
												BasePageV2.smokeresults("", rowno314, "PASS");
												break;
										}else {
										        BasePageV2.reportFail("Verfiy the UI of Characters tab when the tab has more than 8 cards:");
										        BasePageV2.smokeresults("", rowno314, "FAIL");
										        
											}
									}
								    
								
								try {
									driver.findElementByXPath("//android.widget.TextView[@text='"+charCard+"']");
									
								} catch (Exception e) {
									test.log(LogStatus.FAIL, "Not found Title in characters section " + charCard);
								}
								    test.log(LogStatus.INFO, "Card present in charcters section : " + charCard);
								
								
							}
						
			}else {
				channelsPageV2.charactersTab.click();
				// check cards presence in characters section
				test.log(LogStatus.INFO, "clicked on characters tab");
				for (int i = 0; i < charctersTiles.size() ; i++ ) {
					String charCard = charctersTiles.get(i).toString().trim();
					
					    if(i == 3) {
					    	Utilities.verticalSwipe(driver);
					    }
					    
					    if (i == 6) {
							 Utilities.verticalSwipe(driver);
							 if(Utilities.explicitWaitClickable(driver, channelsPageV2.seeAllButton, 10)) {
									test.log(LogStatus.PASS,"Verfiy the UI of Characters tab when the tab has more than 8 cards:");
									BasePageV2.smokeresults("", rowno314, "PASS");
									break;
							}else {
							        BasePageV2.reportFail("Verfiy the UI of Characters tab when the tab has more than 8 cards:");
							        BasePageV2.smokeresults("", rowno314, "FAIL");
							        
								}
						}
					    
					
					try {
						driver.findElementByXPath("//android.widget.TextView[@text='"+charCard+"']");
						
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Not found Title in characters section " + charCard);
					}
					    test.log(LogStatus.INFO, "Card present in charcters section : " + charCard);
					
					
				}
			}
		
			//check SEE ALL button functionality
			try {
			   Utilities.verticalSwipe(driver, channelsPageV2.seeAllButton);
			  if(Utilities.explicitWaitClickable(driver, channelsPageV2.seeAllButton, 10)) {
				channelsPageV2.seeAllButton.click();
				test.log(LogStatus.INFO, "Clicked SEE ALL button in characters section");
				if(Utilities.explicitWaitVisible(driver, channelsPageV2.charactersTitle, 10)) {
					test.log(LogStatus.PASS,"Verified the functionality of See All button in Characters tab.");
					BasePageV2.smokeresults("", rowno317, "PASS");
				}else {
					test.log(LogStatus.FAIL, "Verify the functionality of See All button in Characters tab.");
					BasePageV2.takeScreenshot();
					BasePageV2.smokeresults("", rowno317, "FAIL");
				}
				
			  }else BasePageV2.reportFail("Not able to Click 'SEE ALL' Button / Not found ");
			}catch (Exception e) {
				test.log(LogStatus.FAIL, "Unable to swiped till to SEE ALL Button");
			}
			

			
		
		// End > 8 cards

		
		}	


		
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}
		
}

