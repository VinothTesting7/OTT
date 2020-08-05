package com.viacom.vodplayer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//Author Tanisha
public class VerifyVODContentConfigurableTrays extends BaseTestV2{
	
	String testName = "VerifyVODContentConfigurableTrays";
	@Test(dataProvider = "getData")
	public void VerifyVODContentConfigurableTrays(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyVODContentConfigurableTrays");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls270 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno270=xls270.getRowCount("Smoke_Results")+1;
		xls270.setCellData("Smoke_Results", "Testcase Name",rowno270,"VOD Content: Verify the type of cards present under Configurable trays");//P2
		
		Xls_Reader xls271 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno271=xls271.getRowCount("Smoke_Results")+1;
		xls271.setCellData("Smoke_Results", "Testcase Name",rowno271,"VOD Content: Verify the functionality by tapping tray name");//P2
		
		Xls_Reader xls272 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno272=xls272.getRowCount("Smoke_Results")+1;
		xls272.setCellData("Smoke_Results", "Testcase Name",rowno272,"VOD Content: Verify the card metadata");//P2
		
		Xls_Reader xls273 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno273=xls273.getRowCount("Smoke_Results")+1;
		xls273.setCellData("Smoke_Results", "Testcase Name",rowno273,"VOD Content: Verify the availibility of 'See All' button in configurable tray if there are 8 cards");//P3
		
		Xls_Reader xls274 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno274=xls274.getRowCount("Smoke_Results")+1;
		xls274.setCellData("Smoke_Results", "Testcase Name",rowno274,"VOD Content: Verify the availibility of 'See All' button in configurable tray if there are less than 8 cards");//P3
		
		Xls_Reader xls275 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno275=xls275.getRowCount("Smoke_Results")+1;
		xls275.setCellData("Smoke_Results", "Testcase Name",rowno275,"VOD Content: Verify the availibility of 'See All' button in configurable tray if there are more than 8 cards");//P3
		
		Xls_Reader xls276 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno276=xls276.getRowCount("Smoke_Results")+1;
		xls276.setCellData("Smoke_Results", "Testcase Name",rowno276,"VOD Content: Verify 'See All' button functionality");//P2
		
		Xls_Reader xls277 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno277=xls277.getRowCount("Smoke_Results")+1;
		xls277.setCellData("Smoke_Results", "Testcase Name",rowno277,"VOD Content: Verify the navigation when tapping on cards");//P2
		
		Xls_Reader xls278 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno278=xls278.getRowCount("Smoke_Results")+1;
		xls278.setCellData("Smoke_Results", "Testcase Name",rowno278,"VOD Content: Verify UI of 'VOD Content - Configurable' tray list view page");//P2
		
		Xls_Reader xls279 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno279=xls279.getRowCount("Smoke_Results")+1;
		xls279.setCellData("Smoke_Results", "Testcase Name",rowno279,"VOD Content: Verify the scroll functionality of 'VOD Content - Configurable' tray list view page");//P3
		
		
		test.log(LogStatus.INFO, "Starting VerifyVODContentConfigurableTrays: Verify VOD Content Configurable Trays");
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 int err270=0;
		 int err271=0;
		 int err272=0;
		 int err278=0;
		//Click on Watch tab
		//Scroll to tray and check if it is All Characters
		//If all Characters ignore and get the next tray
		
		 
		
		//Call Config API and get the media type for show and episode
		 String url_config="";
		 String api_config="Config";
		 String apiname_config="";
		 ArrayList<Integer> Listof_Episode=new ArrayList<Integer>();
		 ArrayList<Integer> Listof_Show=new ArrayList<Integer>();
		 Hashtable<String,Integer> table=null;
		 int rowsConfig=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rowsConfig;rNum++){
			 apiname_config=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_config.equals(api_config)){
				 url_config=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_config);
				 Response resp_config=Utilities.requestUtilitypostOld(url_config, map);
				 resp_config.then().assertThat().statusCode(200);
				 //resp_config.prettyPrint();
				 Map<String,Integer> ott=resp_config.jsonPath().get("assets.OTT");
				 for(Map.Entry<String, Integer> m :ott.entrySet()) {
					 if(m.getKey().equals("EPISODE_TYPE"))
						Listof_Episode.add(m.getValue());	
					 if(m.getKey().equals("TV_SERIES_TYPE"))
						Listof_Show.add(m.getValue());
				 }
			 }		 
		 }
		 
		 //Call Watch API and get the media type of the configurable tray
		 int api_totalItems_watch=0;
		 String url_watch="";
		 String api_watch="Watch Json";
		 String apiname_watch="";
		 ArrayList<String> watchJsonAPI=new ArrayList<String>();
		 int rowsWatchJson=xls.getRowCount("Api");
		 int configurableItemNumber=0;
		 String apiTrayName="";
		 int apiTrayTotalItems;
		 Response resp_watch=null;
		 int apiTrayMediaType=0;
		 for(int rNum=1;rNum<=rowsWatchJson;rNum++){
			 apiname_watch=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_watch.equals(api_watch)){
				 url_watch=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_watch);
				 resp_watch=Utilities.requestUtilitypostOld(url_watch, map);
				 api_totalItems_watch=resp_watch.jsonPath().get("assets.size()");
				 for(int count=0; count<api_totalItems_watch; count++) {
					 String trayType=resp_watch.jsonPath().get("assets["+count+"].trayContentType");
					 if(trayType.equalsIgnoreCase("inline")) {
						 configurableItemNumber=count;
						 break;
					 }
				 }
				 
			 }
		 }
		 
		 //Call the Configurable tray API
		 int api_totalItems_vodconf=0;
		 String url_vodconf="";
		 String api_vodconf="VOD Configurable";
		 String apiname_vodconf="";
		 ArrayList<String> vodconfAPI=new ArrayList<String>();
		 int rowsvodconfJson=xls.getRowCount("Api");
		 int configurableItemNo=0;
		 Response resp_vodconf=null;
		 for(int rNum=1;rNum<=rowsvodconfJson;rNum++){
			 apiname_vodconf=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_vodconf.equals(api_vodconf)){
				 url_vodconf=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_vodconf);
				 resp_vodconf=Utilities.requestUtilitypostOld(url_vodconf, map);
				 		 
			 }
		 }	 
		 //Get the media type of the configurable tray
		 String uiTrayName="";
		 int apiTraymediaTypeForEachItem=0;
		 apiTrayName=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].title");
		 apiTrayTotalItems=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].totalItems");
		 //Find if the configurable tray is episode tray or show tray
		 apiTrayMediaType=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].assets[0].items[0].mediaType");	
		 int apiTrayTotalItemsChanged=0;
		 //Click on Watch tab
		 homepagev2.tabClick("Watch");
	     //CODE FOR EPISODE CARDS
		 if(Listof_Episode.contains(apiTrayMediaType)) {
			 test.log(LogStatus.INFO, "As per API the Configurable tray consists of Episode cards");
			 if(apiTrayTotalItems>10) {
				 apiTrayTotalItemsChanged=10;
			 }
			 //Verify the type of cards present under Configurable trays using VOD api
			 for(int count=0;count<apiTrayTotalItemsChanged;count++) {			 
				 apiTraymediaTypeForEachItem=resp_vodconf.jsonPath().get("assets.items["+count+"].mediaType");
				 if(!Listof_Episode.contains(apiTraymediaTypeForEachItem)){
					 test.log(LogStatus.FAIL, "Item "+(count+1)+" is not of Episode type");
					 err270++;
				 }
				 else {
					 test.log(LogStatus.INFO, "Item "+(count+1)+" is of Episode type");
				 }
			 }
		 }
			 //Final verification of test case 270
			 if(err270<=0) {
				 homepagev2.smokeresults("VOD Content: Verify the type of cards present under Configurable trays",rowno270, "PASS");
				 homepagev2.reportPass("VOD Content: Verify the type of cards present under Configurable trays is PASS"); 
			 }
			 else {
				 homepagev2.smokeresults("VOD Content: Verify the type of cards present under Configurable trays",rowno270, "FAIL");
				 test.log(LogStatus.FAIL, "VOD Content: Verify the type of cards present under Configurable trays is FAIL"); 
				 BasePageV2.takeScreenshot();
			 }
			 //Scroll for 30 times until we find tray name
			 for(int scrollCount=0;scrollCount<=30;scrollCount++) {
				 try {
					 uiTrayName=watchpagev2.trayTitle.getText().trim();
					 if(uiTrayName.equalsIgnoreCase(apiTrayName)) {
						 test.log(LogStatus.INFO, "Scrolled and found the Configurable tray in UI"); 
						 break;
					 }
					 else if(scrollCount==30) 
						 homepagev2.reportFail("Unable to find the Configurable tray in the UI"); 
					 
					 else 
						 Utilities.verticalSwipe(driver); 
					 
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
				 }
					 
			 }
			 //Code if total items lesser than or equal to 8
			 if(apiTrayTotalItems<=8) {
				 //Tapping on tray name should launch list page
				 try {
					  String trayNameLocator="//android.widget.TextView[@text='"+uiTrayName+"']";
					  driver.findElement(By.xpath(trayNameLocator)).click();
					  if(Utilities.explicitWaitVisible(driver, homepagev2.backButton, 5)) {
						  test.log(LogStatus.INFO, "Configurable tray launched list page when items are below or equal to 8");
						  driver.navigate().back();
					  }
					  else {
						  test.log(LogStatus.FAIL, "Configurable tray is not clickable when items are below or equal to 8");
						  
					  }
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Unable to click on the Configurable tray title");
				 }
				 //Verification of metadata for the cards
				 
				 for(int count=0;count<apiTrayTotalItems;count++) {
					 Thread.sleep(1000);
					 String apiItemTitle=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].assets[0].items["+count+"].title");
					 int apiItemDuration=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].assets[0].items["+count+"].duration");
					 int apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toMinutes(apiItemDuration);					 
					 if(apiItemDurationInt<=1) 
						 apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toSeconds(apiItemDuration);
					 String apiItemDurationString=Integer.toString((int) apiItemDurationInt);
					 String apiItemEpisodeNumber=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].assets[0].items["+count+"].episodeNo");
					 String apiItemDesc=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].assets[0].items["+count+"].desc");
					 String uiItemTitleLocator="//android.widget.TextView[contains(@text,\""+apiItemTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
					 String uiItemDurationLocator="//android.widget.TextView[contains(@text,\""+apiItemTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']";
					 String uiItemDescLocator="//android.widget.TextView[contains(@text,\""+apiItemTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
					 String uiItemImageLocator="//android.widget.TextView[contains(@text,\""+apiItemTitle+"\")]/parent::android.view.ViewGroup/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/grid_item_category']";
					 try {
						 String uiItemTitle=driver.findElement(By.xpath(uiItemTitleLocator)).getText();
						 if(uiItemTitle.contains(apiItemTitle)) 
							 test.log(LogStatus.INFO, "Meta Data->Title is matching in API and UI for "+uiItemTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Title is NOT matching in API and UI for "+uiItemTitle);
							 err272++;
						 }	 
						 String uiItemDuration=driver.findElement(By.xpath(uiItemDurationLocator)).getText();
						 if(uiItemDuration.contains(apiItemDurationString)) 
							 test.log(LogStatus.INFO, "Meta Data->Duration: "+uiItemDuration+" is matching in API and UI for "+uiItemTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Duration: "+uiItemDuration+" is NOT matching in API and UI for "+uiItemTitle);
							 err272++;
						 }
						 String uiItemDesc=driver.findElement(By.xpath(uiItemDescLocator)).getText();
						 if(uiItemDesc.contains(apiItemDesc)) 
							 test.log(LogStatus.INFO, "Meta Data->Description: "+apiItemDesc+" is matching in API and UI for "+uiItemTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Description: "+apiItemDesc+" is NOT matching in API and UI for "+uiItemTitle);
							 err272++;
						 }
						 if(uiItemDesc.contains(apiItemEpisodeNumber)) 
							 test.log(LogStatus.INFO, "Meta Data->Episode: E"+apiItemEpisodeNumber+" is matching in API and UI for "+uiItemTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Episode: E"+apiItemEpisodeNumber+" is NOT matching in API and UI for "+uiItemTitle);
							 err272++;
						 }
						 try{
							 driver.findElement(By.xpath(uiItemImageLocator));
							 test.log(LogStatus.INFO, "Media icon is verified present in UI for "+uiItemTitle);
						 }
						 catch(Exception e2) {
							 test.log(LogStatus.FAIL, "Media icon is missing in UI for "+uiItemTitle);
							 err272++;
						 }
					 }
					 catch(Exception e) {
						 Utilities.verticalSwipe(driver);
						 try {
							 String uiItemTitle=driver.findElement(By.xpath(uiItemTitleLocator)).getText();
							 if(uiItemTitle.contains(apiItemTitle)) 
								 test.log(LogStatus.INFO, "Meta Data->Title is matching in API and UI for "+uiItemTitle);
							 else {
								 test.log(LogStatus.FAIL, "Meta Data->Title is NOT matching in API and UI for "+uiItemTitle);
								 err272++;
							 }	 
							 String uiItemDuration=driver.findElement(By.xpath(uiItemDurationLocator)).getText();
							 if(uiItemDuration.contains(apiItemDurationString)) 
								 test.log(LogStatus.INFO, "Meta Data->Duration: "+uiItemDuration+" is matching in API and UI for "+uiItemTitle);
							 else {
								 test.log(LogStatus.FAIL, "Meta Data->Duration: "+uiItemDuration+" is NOT matching in API and UI for "+uiItemTitle);
								 err272++;
							 }
							 String uiItemDesc=driver.findElement(By.xpath(uiItemDescLocator)).getText();
							 if(uiItemDesc.contains(apiItemDesc)) 
								 test.log(LogStatus.INFO, "Meta Data->Description: "+apiItemDesc+" is matching in API and UI for "+uiItemTitle);
							 else {
								 test.log(LogStatus.FAIL, "Meta Data->Description: "+apiItemDesc+" is NOT matching in API and UI for "+uiItemTitle);
								 err272++;
							 }
							 if(uiItemDesc.contains(apiItemEpisodeNumber)) 
								 test.log(LogStatus.INFO, "Meta Data->Episode: E"+apiItemEpisodeNumber+" is matching in API and UI for "+uiItemTitle);
							 else {
								 test.log(LogStatus.FAIL, "Meta Data->Episode: E"+apiItemEpisodeNumber+" is NOT matching in API and UI for "+uiItemTitle);
								 err272++;
							 }
							 try{
								 driver.findElement(By.xpath(uiItemImageLocator));
								 test.log(LogStatus.INFO, "Media icon is verified present in UI for "+uiItemTitle);
							 }
							 catch(Exception e2) {
								 test.log(LogStatus.FAIL, "Media icon is missing in UI for "+uiItemTitle);
								 err272++;
							 }
						 }
						 catch(Exception e1) {
							 test.log(LogStatus.INFO, "Unable to locate item"+count);
						 }
					 }				 
				}
				 //Final verification of test case 272
				 if(err272<=0) {
					 homepagev2.smokeresults("VOD Content: Verify the card metadata",rowno272, "PASS");
					 homepagev2.reportPass("VOD Content: Verify the card metadata is PASS"); 
				 }
				 else {
					 homepagev2.smokeresults("VOD Content: Verify the card metadata",rowno272, "FAIL");
					 test.log(LogStatus.FAIL, "VOD Content: Verify the card metadata is FAIL"); 
				 }
				//Verify See All button availability if there are 8 cards
				if(apiTrayTotalItems==8) {
					if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 3)){
						homepagev2.smokeresults("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are 8 cards",rowno273, "FAIL");
						test.log(LogStatus.FAIL,"VOD Content: Verify the availibility of 'See All' button in configurable tray if there are 8 cards is FAIL"); 	 		 
						BasePageV2.takeScreenshot();
					}
					else {
						homepagev2.smokeresults("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are 8 cards",rowno273, "PASS");
					    homepagev2.reportPass("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are 8 cards is PASS"); 
					}
				}
				//Verify the availibility of 'See All' button in configurable tray if there are less than 8 cards
				if(apiTrayTotalItems<8) {
					if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 3)){
						 homepagev2.smokeresults("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are less than 8 cards",rowno274, "FAIL");
						 test.log(LogStatus.FAIL,"VOD Content: Verify the availibility of 'See All' button in configurable tray if there are less than 8 cards is FAIL"); 
						 BasePageV2.takeScreenshot();		 
					}
					else {
						 homepagev2.smokeresults("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are less than 8 cards",rowno274, "PASS");
						 homepagev2.reportPass("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are less than 8 cards is PASS"); 
					}
				}
				//VOD Content: Verify the navigation when tapping on cards
				//Tapping on the last card displayed
				String apiItemDescLast=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].assets[0].items[5].desc");
				String uiItemDescLocatorLast="//android.widget.TextView[contains(@text,\""+apiItemDescLast+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
				try {
					driver.findElement(By.xpath(uiItemDescLocatorLast)).click();
					test.log(LogStatus.INFO, "Clicked on the last item in the configurable tray");
					Thread.sleep(10000);
					//homepagev2.verifyAndProgressBar();
					if(Utilities.explicitWaitVisible(driver, watchpagev2.watchFirstItemPlayer, 3)) {
						homepagev2.smokeresults("VOD Content: Verify the navigation when tapping on cards",rowno277, "PASS");
						homepagev2.reportPass("VOD Content: Verify the navigation when tapping on cards is PASS"); 
					}
					else {
						homepagev2.smokeresults("VOD Content: Verify the navigation when tapping on cards",rowno277, "FAIL");
						test.log(LogStatus.FAIL,"VOD Content: Verify the navigation when tapping on cards is FAIL");
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to click on the last item");
				}
				
	 
		}
			 
			 
			 //Code if total items greater than 8
			// 271 Verify the functionality by tapping tray name->Done
			// 272 Verify the card metadata
			// 275 Verify the availibility of 'See All' button in configurable tray if there are more than 8 cards->Done
			// 276 Verify 'See All' button functionality->Done
			// 277 Verify the navigation when tapping on cards
			// 278 Verify UI of 'VOD Content - Configurable' tray list view page
			// 279 Verify the scroll functionality of 'VOD Content - Configurable' tray list view page->Done
 
		else {
	   //Verify the functionality by tapping tray name
			try {
				String trayNameLocator="//android.widget.TextView[@text='"+uiTrayName+"']";
				driver.findElement(By.xpath(trayNameLocator)).click();
				if(Utilities.explicitWaitVisible(driver, homepagev2.backButton, 10)) {
					test.log(LogStatus.INFO, "Verified that Configurable tray is clickable and launches list page when items are greater than 8"); 
					homepagev2.smokeresults("VOD Content: Verify the functionality by tapping tray name",rowno271, "PASS");
					homepagev2.reportPass("VOD Content: Verify the functionality by tapping tray name is PASS"); 
				}
				else {
						  test.log(LogStatus.FAIL, "Back button is not visible");
						  homepagev2.smokeresults("VOD Content: Verify the functionality by tapping tray name",rowno271, "FAIL");
						  test.log(LogStatus.FAIL,"VOD Content: Verify the functionality by tapping tray name is FAIL");
						  basepagev2.takeScreenshot();  
				 }
			}
			catch(Exception e) {
					 test.log(LogStatus.FAIL, "Unable to click on the Configurable tray title");
					 homepagev2.smokeresults("VOD Content: Verify the functionality by tapping tray name",rowno271, "FAIL");
					 test.log(LogStatus.FAIL,"VOD Content: Verify the functionality by tapping tray name is FAIL");
					 basepagev2.takeScreenshot();
			}
		//Verify meta data
			 if(apiTrayTotalItems>10) {
				 apiTrayTotalItems=10;
			 }
			 ArrayList<String> descList=new ArrayList<String>();
			 test.log(LogStatus.INFO, "Verification of meta data for each item:");
			 test.log(LogStatus.INFO, "------------------------------------------------");
			 for(int count=0;count<apiTrayTotalItems;count++) {
				 Thread.sleep(1000);
				 String apiItemShowTitle=resp_vodconf.jsonPath().get("assets.items["+count+"].refSeriesTitle");
				 String apiItemEpisodeTitle=resp_vodconf.jsonPath().get("assets.items["+count+"].title");
				 int apiItemDuration=resp_vodconf.jsonPath().get("assets.items["+count+"].duration");
				 int apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toMinutes(apiItemDuration);					 
				 if(apiItemDurationInt<=1) 
					 apiItemDurationInt=(int) TimeUnit.MILLISECONDS.toSeconds(apiItemDuration);
				 String apiItemDurationString=Integer.toString((int) apiItemDurationInt);
				 String apiItemEpisodeNumber=resp_vodconf.jsonPath().get("assets.items["+count+"].episodeNo");
				 String apiItemDesc=resp_vodconf.jsonPath().get("assets.items["+count+"].desc");
				 String uiItemShowTitleLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
				 String uiItemDurationLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']";
				 String uiItemDescLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
				 String uiItemImageLocator="//android.widget.TextView[contains(@text,\""+apiItemEpisodeTitle+"\")]/parent::android.view.ViewGroup/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/grid_item_category']";
				 try {
					 String uiItemShowTitle=driver.findElement(By.xpath(uiItemShowTitleLocator)).getText();
					 if(uiItemShowTitle.contains(apiItemShowTitle)) 
						 test.log(LogStatus.INFO, "Meta Data->Show title is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
					 else {
						 test.log(LogStatus.FAIL, "Meta Data->Show title is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 err272++;
					 }	 
					 String uiItemDuration=driver.findElement(By.xpath(uiItemDurationLocator)).getText();
					 if(uiItemDuration.contains(apiItemDurationString)) 
						 test.log(LogStatus.INFO, "Meta Data->Duration: "+uiItemDuration+" is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
					 else {
						 test.log(LogStatus.FAIL, "Meta Data->Duration: "+uiItemDuration+" is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 err272++;
					 }
					 String uiItemEpisodeDesc=driver.findElement(By.xpath(uiItemDescLocator)).getText();
					 descList.add(uiItemEpisodeDesc);
					 if(uiItemEpisodeDesc.contains(apiItemEpisodeTitle)) 
						 test.log(LogStatus.INFO, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
					 else {
						 test.log(LogStatus.FAIL, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 err272++;
					 }
					 if(uiItemEpisodeDesc.contains(apiItemEpisodeNumber)) 
						 test.log(LogStatus.INFO, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
					 else {
						 test.log(LogStatus.FAIL, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 err272++;
					 }
					 try{
						 driver.findElement(By.xpath(uiItemImageLocator));
						 test.log(LogStatus.INFO, "Media icon is verified present in UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
					 }
					 catch(Exception e2) {
						 test.log(LogStatus.FAIL, "Media icon is missing in UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 err272++;
					 }
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 try {
						 String uiItemShowTitle=driver.findElement(By.xpath(uiItemShowTitleLocator)).getText();
						 if(uiItemShowTitle.contains(apiItemShowTitle)) 
							 test.log(LogStatus.INFO, "Meta Data->Show title is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Show title is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
							 err272++;
						 }	 
						 String uiItemDuration=driver.findElement(By.xpath(uiItemDurationLocator)).getText();
						 if(uiItemDuration.contains(apiItemDurationString)) 
							 test.log(LogStatus.INFO, "Meta Data->Duration: "+uiItemDuration+" is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Duration: "+uiItemDuration+" is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
							 err272++;
						 }
						 String uiItemEpisodeDesc=driver.findElement(By.xpath(uiItemDescLocator)).getText();
						 descList.add(uiItemEpisodeDesc);
						 if(uiItemEpisodeDesc.contains(apiItemEpisodeTitle)) 
							 test.log(LogStatus.INFO, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Episode Title: "+apiItemEpisodeTitle+" is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
							 err272++;
						 }
						 if(uiItemEpisodeDesc.contains(apiItemEpisodeNumber)) 
							 test.log(LogStatus.INFO, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 else {
							 test.log(LogStatus.FAIL, "Meta Data->Episode Number: "+apiItemEpisodeNumber+" is NOT matching in API and UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
							 err272++;
						 }
						 try{
							 driver.findElement(By.xpath(uiItemImageLocator));
							 test.log(LogStatus.INFO, "Media icon is verified present in UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
						 }
						 catch(Exception e2) {
							 test.log(LogStatus.FAIL, "Media icon is missing in UI for "+uiItemShowTitle+":"+apiItemEpisodeTitle);
							 err272++;
						 }
					 }
					 catch(Exception e1) {
						 test.log(LogStatus.INFO, "Unable to locate item"+count);
					 }
				 }	
				 test.log(LogStatus.INFO, "------------------------------------------------");
			}	
		//Scroll up to locate back button
			Utilities.verticalSwipeReverse(driver);
			Utilities.verticalSwipeReverse(driver);
		//Verify the availability of 'See All' button in configurable tray if there are more than 8 cards
				 //Navigate back
			 if(Utilities.explicitWaitClickable(driver, homepagev2.backButton, 10)) {
				 try {
					 homepagev2.backButton.click(); 
					 test.log(LogStatus.INFO, "Clicked on Back button at the top left");
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click on Back button at the top left");
					 driver.navigate().back();
					 test.log(LogStatus.INFO, "Clicked on Device back button");
				 }			 
			 }
			 else {
				 test.log(LogStatus.FAIL, "Back button at the top left is not clickable"); 
				 driver.navigate().back();
				 test.log(LogStatus.INFO, "Clicked on Device back button");
			 }
			//Scroll to the sixth card using description
			 String apiItem6=resp_watch.jsonPath().get("assets["+configurableItemNumber+"].assets[0].items[5].title");
			 String uiItemDescLocator6="//android.widget.TextView[contains(@text,\""+apiItem6+"\")]/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
			 boolean item6Presence=false;
			 for(int scroll=0;scroll<5;scroll++) {
				 item6Presence=Utilities.verticalSwipeAndFind(driver,uiItemDescLocator6);
				 if(item6Presence==true) 
					break; 		 
			 }
			 if(item6Presence==true) {
				Utilities.verticalSwipe(driver);
				test.log(LogStatus.INFO, "Scrolled and located sixth item");
				//Check for availability of See All
				if(Utilities.explicitWaitVisible(driver, homepagev2.seeAll, 3)){
					homepagev2.smokeresults("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are more than 8 cards",rowno275, "PASS");
					homepagev2.reportPass("VOD Content: Verify the availibility of 'See All' button in configurable tray if there are more than 8 cards is PASS"); 	
					//Click on SEE ALL
					try {
						homepagev2.seeAll.click(); 
						test.log(LogStatus.INFO, "Clicked on See All button");
						if(Utilities.explicitWaitVisible(driver, homepagev2.backButton, 10)) {
							  test.log(LogStatus.INFO, "Located Back button");
							  homepagev2.smokeresults("VOD Content: Verify 'See All' button functionality",rowno276, "PASS");
							  homepagev2.reportPass("VOD Content: Verify 'See All' button functionality is PASS"); 	
							  //Code for Page title verification
							  if(Utilities.explicitWaitVisible(driver, homepagev2.pageTitle, 10)) {
								  String pageTitle=homepagev2.pageTitle.getAttribute("text");
								  if(pageTitle.equalsIgnoreCase(uiTrayName)) {
									  test.log(LogStatus.INFO, "VOD tray UI : Page title is verified");  
								  }
								  else {
									  test.log(LogStatus.FAIL, "Failed to verify VOD tray UI : Page title");
								  }
							  }
							  else {
								  test.log(LogStatus.FAIL, "Failed to verify VOD tray UI : Page title");
							  }
							//Verification of lazy load
							 Thread.sleep(3000);
							 ArrayList firstListTitles=new ArrayList();
							 ArrayList secondListTitles=new ArrayList();
							 String uiItemTitleLocator="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
							 try {
								 List<WebElement> firstList=driver.findElements(By.xpath(uiItemTitleLocator));
								 test.log(LogStatus.INFO, "First list of episode count: "+firstList.size());
								 for(int count=0;count<firstList.size();count++) {
									 try {
										 String title=firstList.get(count).getAttribute("text");
										 if(!firstListTitles.contains(title))
											 firstListTitles.add(title); 
									 }
									 catch(Exception e) {
										 test.log(LogStatus.INFO, "Failed to fetch description of episode"+(count+1)+" from the first list");
									 }
									 
								 }
								 test.log(LogStatus.INFO, "First list of item descriptions: "+firstListTitles);
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "Failed to fetch description of episodes before scroll");
							 }	 
							 Utilities.verticalSwipe(driver);
							 Utilities.verticalSwipe(driver);
							 test.log(LogStatus.INFO, "Swiped vertically twice ..");
							 Thread.sleep(2000);
							 try {
								 List<WebElement> secondList=driver.findElements(By.xpath(uiItemTitleLocator));
								 test.log(LogStatus.INFO, "Second list of episode count: "+secondList.size());
								 for(int count=0;count<secondList.size();count++) {
									 try {
										 String title=secondList.get(count).getAttribute("text");
										 if(!secondListTitles.contains(title))
											 secondListTitles.add(title); 
									 }
									 catch(Exception e) {
										 test.log(LogStatus.INFO, "Failed to fetch description of episode"+(count+1)+" from the second list");
									 }
									 
								 }
								 test.log(LogStatus.INFO, "Second list of item descriptions: "+secondListTitles);
							 }
							 catch(Exception e) {
								 test.log(LogStatus.FAIL, "Failed to fetch description of episodes after scroll");
							 } 
							 
							 if(firstListTitles.equals(secondListTitles)) {
								test.log(LogStatus.INFO, "Items before and after scroll are same ..");
								homepagev2.smokeresults("VOD Content: Verify the scroll functionality of 'VOD Content - Configurable' tray list view page",rowno279, "FAIL");
								test.log(LogStatus.FAIL,"VOD Content: Verify the scroll functionality of 'VOD Content - Configurable' tray list view page is FAIL"); 
							 }
							 else {
								test.log(LogStatus.INFO, "Items before and after scroll vary ..");
								homepagev2.smokeresults("VOD Content: Verify the scroll functionality of 'VOD Content - Configurable' tray list view page",rowno279, "PASS");
								homepagev2.reportPass("VOD Content: Verify the scroll functionality of 'VOD Content - Configurable' tray list view page is PASS"); 
							 }	  
						}
						else {
							test.log(LogStatus.FAIL, "Back button is not visible");  
						}
					}
					catch(Exception e) {
					    test.log(LogStatus.FAIL, "Failed to click on See All button");	
					}
				}
				else {
					test.log(LogStatus.FAIL, "See All button is not visible");
					basepagev2.takeScreenshot();
				}
			}
			else {
				test.log(LogStatus.FAIL, "Unable to scroll to See All");
			}
		}
	}	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
