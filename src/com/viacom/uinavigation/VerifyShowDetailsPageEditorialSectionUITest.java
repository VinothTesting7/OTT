package com.viacom.uinavigation;

import java.time.Duration;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class VerifyShowDetailsPageEditorialSectionUITest extends BaseTestV2{
	String testName = "VerifyShowDetailsPageEditorialSectionUITest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Show Detail screen Editorial Section UI");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Show Detail screen Tray UI "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int taprow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",taprow, "Verify the functionality by tapping on cards:");	
		

	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	WatchPageV2  watchpagev2=new WatchPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	homepagev2.login(un, pwd);
//click on watch tab	
	test.log(LogStatus.INFO, "Navigating to Watch Page");
	
	homepagev2.tabClick("Watch");
	
	Thread.sleep(2000);	

	//Get tray name from API
	String apiTrayName="";
	String uiTrayName="";
	int totalAssets=0;
	String url_kidsCharacters="";
	String api_kidsCharacters="Kids Characters Tray";
	String apiname_kidsCharacters="";
	String trayNameParameter="";
	String trayTitle="";
	Response resp_kidsCharacters=null;
	int rows_kidsCharacters=xls.getRowCount("Api");
	for(int rNum=1;rNum<=rows_kidsCharacters;rNum++){
	apiname_kidsCharacters=xls.getCellData("Api", "API Name", rNum);
	if(apiname_kidsCharacters.equals(api_kidsCharacters)){
	url_kidsCharacters=xls.getCellData("Api", "Url", rNum);
	Map map=BasePageV2.apiparams(2, xls, api_kidsCharacters);
	resp_kidsCharacters=Utilities.requestUtilitypostOld(url_kidsCharacters, map);
	totalAssets=resp_kidsCharacters.jsonPath().get("assets.size()");
	for(int i=0;i<totalAssets;i++) {
	trayNameParameter=resp_kidsCharacters.jsonPath().get("assets["+i+"].trayName");
	if(trayNameParameter.equals("allKidsCharacters")) {
	trayTitle=resp_kidsCharacters.jsonPath().get("assets["+i+"].title");
	}
	}
	}
	}	
	trayTitle=trayTitle.toUpperCase();

	//Get show name from API
	String apiName="", mediaid="",mediatype="";
	String uiName="";
	//Verify from API first item of ALL CHARACTERS
	String url_allCharacters="";
	String api_allCharacters="All Characters";
	String apiname_allCharacters="";
	Response resp_allCharacters=null;
	int rows_allCharacters=xls.getRowCount("Api");
	for(int rNum=1;rNum<=rows_allCharacters;rNum++){
	apiname_allCharacters=xls.getCellData("Api", "API Name", rNum);
	if(apiname_allCharacters.equals(api_allCharacters)){
	url_allCharacters=xls.getCellData("Api", "Url", rNum);
	Map map=BasePageV2.apiparams(2, xls, api_allCharacters);
	resp_allCharacters=Utilities.requestUtilitypostOld(url_allCharacters, map);
	apiName=resp_allCharacters.jsonPath().get("assets.items[0].title");

	
  }
}

	String trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
	String allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
	//Verify if ALL CHARACTERS section is displayed
	boolean trayPresence=Utilities.verticalSwipeAndFind(driver,trayLoc);
	boolean presence=false;
	 if(trayPresence==true) {
		 test.log(LogStatus.INFO, "Tray title "+trayTitle+" is displayed in the UI");
		 for(int scroll=0;scroll<=1;scroll++) {
			 try {
				 driver.findElement(By.xpath(allCharFirstItem)).click();
				 test.log(LogStatus.INFO, "Clicked on show");	
				 presence=true;
				 break;
			 }
			 catch(Exception e) {
				 Utilities.verticalSwipe(driver);
				 if(scroll==1)
					 test.log(LogStatus.INFO, "Failed to click on show");	
			 }
		 }
	 }
	
			//Scroll to  Editorial section
			String editorialsection="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='RELATED SHOWS']";
			Utilities.verticalSwipe(driver, editorialsection);
			Thread.sleep(1000);
			//check for editorial section
			if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSection , 30))
			{
				//test.log(LogStatus.INFO, "Editorial section - is displayed");
				
				//Scroll to  Editorial section
				String editorialsectionRelatedTray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED SHOWS']";
				Utilities.verticalSwipe(driver, editorialsectionRelatedTray);
				
				if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionRelatedTray , 10))
				{
					//Scroll to  editorial section content
					String editorialsectionRelatedTraycontent="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and ( @text='RELATED SHOWS'  or @text='BOOKS')]//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
					Utilities.verticalSwipe(driver, editorialsectionRelatedTraycontent);
					Thread.sleep(1000);
					String showtitle,showpageshowtitle;
					//check for editorial section content
					if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContent, 20))
					{
						test.log(LogStatus.INFO, "content(s) are displayed under Related Shows Tray");
						showtitle=showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContentTitle.getText().trim();
						//BasePageV2.takeScreenshot();
						showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContent.click();
						//cHECK FOR Show title
						test.log(LogStatus.INFO, "Clicking on the Show under Related Shows Tray");
						if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageShowTitle , 30))
						{
							
							if(showspagev2.showDetailPageShowTitle.getText().trim().equalsIgnoreCase(showtitle))
							{								
								test.log(LogStatus.INFO, "Navigated to the relevant show");
								test.log(LogStatus.PASS,"Testcase : 'Verify the functionality by tapping on cards:' is Passed");
								BasePageV2.smokeresults("", taprow, "Pass");
							}
							else
							BasePageV2.reportFail("Not navigating to the respective show Detail Page ");
						}
						else
						BasePageV2.reportFail("Show Title is not displayed in Show Detail page");
						
						
					}
				/*	else if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEditorialSectionYouMayLikeTrayFirstContent, 20))
					{
						test.log(LogStatus.INFO, "content are displayed under Editorial section ");
					}*/
					else
						BasePageV2.reportFail("No content are displayed under Related Shows Tray");
				}
				
				
				
			
			}
			else
				BasePageV2.reportFail("Editorial section is not displayed in show details page");
			
	//	test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the show detail screen:' is Passed");
	}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
