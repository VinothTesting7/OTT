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

public class VerifyShowDetailsPageFunctionalityTest extends BaseTestV2{
	String testName = "VerifyShowDetailsPageFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it is No");
	test = rep.startTest("Verify Show Detail screen Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Show Detail screen functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of the show detail screen:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",downloadrow, "Verify 'Download Episodes' button functionality");	
		
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int backrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",backrow, "Verify the back button functionality:");	
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int seeallrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",seeallrow, "Verify the Episodes section UI whe the tray has <=8 card:");	
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int sortrow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",sortrow, "Verify the sorting order of the episodes by default:");	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	WatchPageV2  watchpagev2=new WatchPageV2(driver,test);
	String un=data.get("Email"); 
	String pwd=data.get("Password");
    homepagev2.logout();
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
	
	test.log(LogStatus.INFO, "Verifying show details page UI");
	
	//Check fOR back button	
	if(Utilities.explicitWaitClickable(driver, showspagev2.showDetailPageBackButton, 30))
	{
		test.log(LogStatus.INFO, "Back button is displayed");
		test.log(LogStatus.INFO, "Verifying back button functionality");
		//BasePageV2.takeScreenshot();
		test.log(LogStatus.INFO, "Clicking on Back button");
		showspagev2.showDetailPageBackButton.click();
		
	
/*		String xpath2="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
		Utilities.verticalSwipe(driver, xpath2);*/
		Thread.sleep(2000);	
		//test.log(LogStatus.INFO, "Navigating to any of show under ALL KIDS CHARACTERS Section");
		 trayLoc="//android.widget.TextView[@text='"+trayTitle+"']";
		 allCharFirstItem="//android.widget.TextView[@text='"+trayTitle+"']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']";
		//Verify if ALL CHARACTERS section is displayed
		 presence=false;
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
	else
	BasePageV2.reportFail("Back button is not displayed in Show Detail page");
	
	test.log(LogStatus.INFO, "Navigating to the Show Detail page again");
	// Check for character image
	
	if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageCharacterImage , 30))
	test.log(LogStatus.INFO, "Character Image is displayed");
	else
	BasePageV2.reportFail("Character Image is not displayed in Show Detail page");
	
	//cHECK FOR PLAY BUTTON
	
	if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPagePlayButton , 30))
	{
		
		test.log(LogStatus.INFO, "Play Button is displayed");
	}
	else
	BasePageV2.reportFail("Play Button is not displayed in Show Detail page");
	
	
	//cHECK FOR Show title
	
		if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageShowTitle , 30))
		test.log(LogStatus.INFO, "Show Title is displayed");
		else
		BasePageV2.reportFail("Show Title is not displayed in Show Detail page");
		
		Utilities.verticalSwipe(driver, showspagev2.showDetailPageShowInfo, "visible", 1);
		
		//cHECK FOR Show Info
		if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageShowInfo , 30))
		test.log(LogStatus.INFO, "Show Info is displayed");
		else
		BasePageV2.reportFail("Show Info is not displayed in Show Detail page");
		
		Thread.sleep(1000);
		   //Check for Language available section
		Utilities.verticalSwipe(driver, showspagev2.showDetailPageLanguagesAvailableInfo, "visible", 1);
		
		    if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageLanguagesAvailableInfo , 30))
			test.log(LogStatus.INFO, "Languages Available Info is displayed");
			else
			BasePageV2.reportFail("Show Info is not displayed in Show Detail page");
		
			Utilities.verticalSwipe(driver,showspagev2.showDetailPageDownlaodEpisodesButton, "clickable", 1);
			//Scroll to Download episodes button
		    if(!Utilities.explicitWaitClickable(driver, showspagev2.showDetailPageDownlaodEpisodesButton , 5))
			{
		    	String download="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
				Utilities.verticalSwipe(driver, download);
				Thread.sleep(1000);
			}
			//Check for Episodes Download button
		    if(Utilities.explicitWaitClickable(driver, showspagev2.showDetailPageDownlaodEpisodesButton , 30))
			{
		    	test.log(LogStatus.INFO, "Download Episodes Button is displayed");
		    	test.log(LogStatus.INFO, "Verifying Download Episodes Button functionality-");
		    	
		    	//Click dOWNLOAD episodes button
		    	showspagev2.showDetailPageDownlaodEpisodesButton.click();
		        if(Utilities.explicitWaitVisibleNew(driver, showspagev2.downloadEpisodesScreenTitle , 30))
		        {
		        	test.log(LogStatus.INFO, "Clicking on Download Episodes button navigated to Download Episodes screen");
		        	test.log(LogStatus.PASS,"Testcase : 'Verify 'Download Episodes' button functionality' is Passed");
		            BasePageV2.smokeresults("", downloadrow, "Pass");
		        }
			}
			else
			BasePageV2.reportFail("Download Episodes Button is not displayed in Show Detail page");
		    
		    
		    //Clicking back button in EDownload Episodes screen
		    if(Utilities.explicitWaitClickable(driver, launchpagev2.backButton , 30))
					{
		    	        test.log(LogStatus.INFO, "Clicking on back button in Download Episodes screen");
		            	launchpagev2.backButton.click();
					}
		    else
		    	BasePageV2.reportFail("Back button is not present in Download Episodes screen");
		    
			
			//Scroll to  episodes section
		//	String episode="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']";
		    Utilities.verticalSwipe(driver,showspagev2.showDetailPageEpisodesSection, "visible", 2);
			Thread.sleep(1000);
			 //Check for Episodes section
			if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageEpisodesSection , 30))
			{
				test.log(LogStatus.INFO, "Episodes section is Present");
				//Scroll to  episodes section video
				String episodevideo="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
				Utilities.verticalSwipe(driver,episodevideo, "visible", 1);
				Thread.sleep(1000);
				if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageEpisodesSectionFirstVideo , 30))
				{		
					test.log(LogStatus.INFO, "Videos are displayed under Episodes section");
			   int count=0, no=0;
			   String title="", way="";
			//Utilities.verticalSwipe(driver);

			    List<String> videotitles=new LinkedList<String>();
			   //Check number of videos under Episodes section (loop)

			    
		        count=showspagev2.showDetailPageEpisodesSectionTitles.size();
	
				 for(int i=0;i<count;i++)
			     {
			    	title=showspagev2.showDetailPageEpisodesSectionTitles.get(i).getText();
			    	if(!videotitles.contains(title))
			    	videotitles.add(title);
			     }
				List<String> videotitlesbeforesort=new LinkedList<String>(videotitles);
				System.out.println("Before sorting :"+videotitlesbeforesort);
			    Collections.sort(videotitles);
			    System.out.println("After sorting :"+videotitles);
			    
			    //Verify the sorting order
			    test.log(LogStatus.INFO, "Verifying the Sorting order of all Episodes");
			    for(int i=0;i<count;i++)
			     {
			    	    String bef=videotitlesbeforesort.get(i);
				        String aft=videotitles.get(i);
				        System.out.println(bef);
				        System.out.println(aft);
				    	if(!bef.equals(aft))
			    		BasePageV2.reportFail("Episode - "+bef+": is not in sorted order");
			     }
			    test.log(LogStatus.PASS,"Testcase : 'Verify the sorting order of the episodes by default:' is Passed");
		        BasePageV2.smokeresults("", sortrow, "Pass");
		        
		        if(count!=0 && count>=6)
			  	{
		        	
			        test.log(LogStatus.INFO, "size is :"+count);
			        Utilities.verticalSwipe(driver);
					if(Utilities.explicitWaitVisibleNew(driver, homepagev2.seeAll, 10))
					{
						test.log(LogStatus.INFO, "See All buttton is displayed for more than 8 videos under Episodes Section");
					}
					else
						{
						    BasePageV2.reportFail("See All Button not displayed for more than 8 videos");
						}
			  	 }
				else if(Utilities.explicitWaitVisibleNew(driver, homepagev2.seeAll, 10))
				{
					//BasePageV2.reportFail("See All button is displayed for less than 8 videos under Episodes Section");
				}
				
					{
					test.log(LogStatus.PASS,"Testcase : 'Verify the Episodes section UI whe the tray has <=8 card:' is Passed");
					BasePageV2.smokeresults("", seeallrow, "Pass");
					}
		        
				}
				else
					BasePageV2.reportFail("No videos are displayed under Episodes section");
				
			}
			else
			BasePageV2.reportFail("Episodes section is not displayed in Show Detail page");
			

			
			//Scroll to  Editorial section
			String editorialsectionrelatedTray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='RELATED SHOWS']";
			Utilities.verticalSwipe(driver, editorialsectionrelatedTray,"visible",3);
			Thread.sleep(1000);
			//check for editorial section
			if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageEditorialSection , 30))
			{
				test.log(LogStatus.INFO, "Editorial section is displayed");
				
				//Scroll to  editorial section content
				String editorialsectioncontent="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and ( @text='RELATED SHOWS'  or @text='BOOKS')]//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
				Utilities.verticalSwipe(driver, editorialsectioncontent,"visible",3);
				Thread.sleep(1000);
				//check for editorial section content
				if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageEditorialSectionRelatedTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "content are displayed under Editorial section ");
				}
				else if(Utilities.explicitWaitVisibleNew(driver, showspagev2.showDetailPageEditorialSectionYouMayLikeTrayFirstContent, 20))
				{
					test.log(LogStatus.INFO, "content are displayed under Editorial section ");
				}
				else
					BasePageV2.reportFail("No content are displayed under Editorial section");
			}
			else
				BasePageV2.reportFail("Editorial section is not displayed in show details page");
			
		test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the show detail screen:' is Passed");
		BasePageV2.smokeresults("", uirow, "Pass");
	}
	 }}
		    

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}
