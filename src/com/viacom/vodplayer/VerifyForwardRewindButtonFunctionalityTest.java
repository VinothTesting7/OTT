package com.viacom.vodplayer;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class VerifyForwardRewindButtonFunctionalityTest extends BaseTestV2{
	String testName = "VerifyForwardRewindButtonFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
	throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Forward/Rewind Button Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Forward/Rewind button functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int fwdrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",fwdrow, "Verify the forward by 10 secs button functionality:");//P1	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int backwardrow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",backwardrow, "Verify the Rewind by 10 secs button functionality:");	//P1
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	 String timebefPause="";

		String un=data.get("Email");
		String pwd=data.get("Password");
		homepagev2.login(un, pwd);
	     //click on watch tab	
		test.log(LogStatus.INFO, "Navigating to Watch Page");
		homepagev2.tabClick("Watch");
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
		 String apiName="";
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
			 if (presence==true) {
				 try {
					 //Scroll till Episodes tray
					 for(int scroll=0;scroll<=3;scroll++) {
						 if(Utilities.explicitWaitVisible(driver, watchpagev2.episodesTray, 10)) {
							 test.log(LogStatus.INFO, "Scrolled to Episodes tray");
							 Utilities.verticalSwipe(driver);
							 Utilities.verticalSwipe(driver);
							 Thread.sleep(2000);
							 break;
						 }
						 else {
							 Utilities.verticalSwipe(driver);
							 if(scroll==3)
								 test.log(LogStatus.FAIL, "Failed to scroll to EPISODES tray");
						 }
					 }
					 if(Utilities.explicitWaitVisible(driver, watchpagev2.episodesItemOneInEpisodeTray, 10)) {
						 String recentActivityFirstItemData = watchpagev2.episodesItemOneInEpisodeTray.getAttribute("text");
						 test.log(LogStatus.INFO, "First Episode is -> "+recentActivityFirstItemData);
						 if(Utilities.explicitWaitClickable(driver, watchpagev2.episodesItemOneInEpisodeTray, 10)) {
							 watchpagev2.episodesItemOneInEpisodeTray.click();
							 test.log(LogStatus.INFO, "Clicked on episode");

		
						 }
					 }
				 }
				 catch(Exception e){}
			 }
		
		
				  //Check for progress bar
				  homepagev2.verifyAndProgressBar();
				
				  //Pause the video
				  playerpagev2.pauseVideo();
				  String currentPlayingTime="";
				  int currentTimeBefFwd=0,currentTimeAftFwd=0,currentTimeBefRwd=0,currentTimeAftRwd=0;
				  //Calculate the current Time in Seconds
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 30))
				  {
					  currentPlayingTime=playerpagev2.playerCurrentDuration.getText();
					  test.log(LogStatus.INFO, "Current Duration  displayed on the Player screen - "+currentPlayingTime); 
				  }
				  else
					  BasePageV2.reportFail("Current Duration is not displayed on the player screen");
				  
				  String[] time = currentPlayingTime.split(":");
				  currentTimeBefFwd=(Integer.parseInt(time[0]))*60+Integer.parseInt(time[1]);
				  
				  test.log(LogStatus.INFO, "Verifying Forward Button functionality");
				  
				  //Click Forward button
				 
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerForwardButton, 20))
				  {
					  test.log(LogStatus.INFO, "Clicking on Forward Button");
					  playerpagev2.playerForwardButton.click();
					//  Thread.sleep(4000);
				  }
				  else
					  BasePageV2.reportFail("Forward button is not present on the player / not clickable");
				  
				  //Current Time Validation
				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 30))
				  {
					  currentPlayingTime=playerpagev2.playerCurrentDuration.getText();
					  test.log(LogStatus.INFO, "Current Duration displayed on the Player screen after clicking forward button - "+currentPlayingTime); 
				  }
				  else
					  BasePageV2.reportFail("Current Duration is not displayed on the player screen");
				  
				   time = currentPlayingTime.split(":");
				  currentTimeAftFwd=(Integer.parseInt(time[0]))*60+Integer.parseInt(time[1]);
				  
				  if((currentTimeAftFwd-currentTimeBefFwd)==10)
					  {
					  test.log(LogStatus.PASS,"Testcase : 'Verify the forward by 10 secs button functionality:' is Passed");
					  BasePageV2.smokeresults("", fwdrow, "Pass");
					  }
				  else
					  BasePageV2.reportFail("Current playing time not forwarded by 10 sec");
				 
				  //Play the video 
				  playerpagev2.playVideo();
				  
				  //Pause the video
				  playerpagev2.pauseVideo();
				  
				//Current Time Validation				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 30))
				  {
					  currentPlayingTime=playerpagev2.playerCurrentDuration.getText();
					  test.log(LogStatus.INFO, "Current Duration displayed on the Player screen before clicking rewind button - "+currentPlayingTime); 
				  }
				  else
					  BasePageV2.reportFail("Current Duration is not displayed on the player screen"); 
				  
				  time = currentPlayingTime.split(":");
				  currentTimeBefRwd=(Integer.parseInt(time[0]))*60+Integer.parseInt(time[1]);				 
				  
				  test.log(LogStatus.INFO, "Verifying rewind Functionality");
				  
				  
				
				//Click Rewind button
					 
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerRewindButton, 20))
				  {
					  test.log(LogStatus.INFO, "Clicking on Rewind Button");
					  playerpagev2.playerRewindButton.click();
					 // Thread.sleep(4000);
				  }
				  else
					  BasePageV2.reportFail("Rewind button is not present on the player / not clickable");
            //Current Time Validation				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 30))
				  {
					  currentPlayingTime=playerpagev2.playerCurrentDuration.getText();
					  test.log(LogStatus.INFO, "Current Duration displayed on the Player screen after clicking rewind button - "+currentPlayingTime); 
				  }
				  else
					  BasePageV2.reportFail("Current Duration is not displayed on the player screen"); 
				  
				time = currentPlayingTime.split(":");
				currentTimeAftRwd=(Integer.parseInt(time[0]))*60+Integer.parseInt(time[1]);  
					  
		  if((currentTimeBefRwd-currentTimeAftRwd)==10)
		  {
			  test.log(LogStatus.PASS,"Testcase : 'Verify the Rewind by 10 secs button functionality:' is Passed");
			  BasePageV2.smokeresults("", backwardrow, "Pass");
		  }
		  else
		  BasePageV2.reportFail("Current playing time not backward by 10 sec");
		 }
		  
					  
}
@DataProvider
public Object[][] getData(){
	return DataUtil.getData(testName,xls);
			

}

}