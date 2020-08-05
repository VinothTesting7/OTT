package com.viacom.vodplayer;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class VerifyVodPlayerFunctionalityTest extends BaseTestV2 {
	String testName = "VerifyVodPlayerFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Player screen UI Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Screen UI functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uirow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",uirow, "Verify the UI of the VOD Player");	//P2
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int pauserow=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",pauserow, "Verify the Pause button functionality:");	//P1
		
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int playrow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",playrow, "Verify the Play button functionality");	//P1
	
	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downrow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",downrow, "Verify the down arrow functionality:");//	P2
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uprow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",uprow, "Verify the Up arrow functionality:");	//P1
		
		Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int seekrow=xls6.getRowCount("Smoke_Results")+1;
		xls6.setCellData("Smoke_Results", "Testcase Name",seekrow, "Verify the seek functionality of the player:");	//P1
		
		Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int closerow=xls7.getRowCount("Smoke_Results")+1;
		xls7.setCellData("Smoke_Results", "Testcase Name",closerow, "Verify the Close button in the player screen:");	//P2
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	String un=data.get("Email");
	String pwd=data.get("Password");
	homepagev2.login(un, pwd);
	
	String timebefPause="";
	 //Click on Watch tab
	 if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10)) {
			homepagev2.tabClick("Watch");
	 test.log(LogStatus.INFO, "Clicked on Watch tab");
	 //BasePageV2.takeScreenshot();
	//Click on the first carousal item
	 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
	 watchpagev2.firstItemInCarousal.click();
	 
	 test.log(LogStatus.INFO, "Playing any of the content from carousal");
		  if(Utilities.explicitWaitVisible(driver,playerpagev2.videoPlayer, 3)) {
				  
				  //Check for progress bar
				  homepagev2.verifyAndProgressBar();
				
				  
				  //Pause the video
				  
				  playerpagev2.pauseVideo();
				  test.log(LogStatus.INFO, "Verifying the UI of the VOD Player");	  
			
				  //Check for close button	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCloseButton, 10))
				  {
					  test.log(LogStatus.INFO, "Close button is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Close button is  not displayed ");
				  
				  //Check for Title	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerTitle, 10))
				  {
					  test.log(LogStatus.INFO, "Content Title is displyed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Content Title is not displayed");
				  
				  //Check for sub Title	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10))
				  {
					  test.log(LogStatus.INFO, "Content sub Title is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Content sub Title is not displayed");
				  
				  
				//Check for settings icon	  				  
				  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerSettings, 10))
				  {
					  test.log(LogStatus.INFO, "Player Settings is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Player Settings is not displayed");
			
				  //Check for favorite button	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoriteButton, 10))
				  {
					  //playerpagev2.playerFavoriteButton.click();
					  test.log(LogStatus.INFO, "Favorite button is displayed on the Player screen");
					  
					  if(Utilities.explicitWaitClickableNew(driver, showspagev2.favIconPopupOkButton, 5))
						{
							showspagev2.favIconPopupOkButton.click();
						}
					  
				  }
				  else
					  BasePageV2.reportFail("Favorite button is not displayed");
				  
				  //Check for play/pause button	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerPlayPauseButton, 10))
				  {
					  test.log(LogStatus.INFO, "Play/Pause button is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Play/Pause  button is not displayed");
				  
				  
				  //Check for Rewind button	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerBackwardButton, 10))
				  {
					  test.log(LogStatus.INFO, "Rewind button is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Rewind  button is not displayed");
				  
				  
				  //Check for forward button	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerForwardButton, 10))
				  {
					  test.log(LogStatus.INFO, "Forward button is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Forward  button is not displayed");	  
				  
				  
				  
				  //Check for seek bar button	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSeekBar, 10))
				  {
					  test.log(LogStatus.INFO, "Seek bar is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Seek Bar is not displayed");	   
				 
				  //Check for Current duration	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 10))
				  {
					  timebefPause=playerpagev2.playerCurrentDuration.getText();
					  test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen - "+timebefPause);
				  }
				  else
					  BasePageV2.reportFail("Duration watched is not displayed");	
				  
				  
				  //Check for Total duration	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerTotalDuration, 10))
				  {
					  test.log(LogStatus.INFO, "Total Duration is displayed on the Player screen");
				  }
				  else
					  BasePageV2.reportFail("Total Duration  is not displayed");	
				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerBottomUpArrowButton, 10))
				  test.log(LogStatus.INFO, "Up Arrow button is displayed  in bottom of  the Player screen");
				  else
				  BasePageV2.reportFail("Up Arrow button is not displayed ");
				  
				test.log(LogStatus.PASS,"Testcase : 'Verify the UI of the VOD Player :' is Passed");
				BasePageV2.smokeresults("", uirow, "Pass");
				  
			 }
			 else {
				 test.log(LogStatus.FAIL, "Player is not displayed on tapping first item of Carousal in Watch tab");
				 BasePageV2.takeScreenshot();
			
			 }			 
		 }
		 else {
			 test.log(LogStatus.FAIL, "Unable to click on first item of Carousal in Watch tab");
			 BasePageV2.takeScreenshot();
		
		 }	 			 
	 }
	 else {
		 test.log(LogStatus.FAIL, "Unable to click on Watch tab");
		 BasePageV2.takeScreenshot();
		
	 }
   //Verifying Pause Functionality
	 Thread.sleep(5000);
	 String timeaftPause="",timeaftplay="";
	 if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 10))
	  {
		  timeaftPause=playerpagev2.playerCurrentDuration.getText();
		  test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen After Pausing video- "+timeaftPause);
		  if(timeaftPause.equals(timebefPause))
		  {
			  test.log(LogStatus.PASS,"Testcase : 'Verify the Pause button functionality:' is Passed");
			  BasePageV2.smokeresults("", pauserow, "Pass");
		  }
		  else
			 BasePageV2.reportFail("Failed to pause the video");
	  }
	  else
		  BasePageV2.reportFail("Duration watched is not displayed");	
	 
	 //Verifying Play Functionality
	 if(Utilities.explicitWaitVisible(driver, playerpagev2.playerPlayPauseButton, 10))
	 {
		 test.log(LogStatus.INFO, "Verifying play button functionality - Clicking on play button");
		 playerpagev2.playerPlayPauseButton.click();
		 Thread.sleep(5000);
		 playerpagev2.pauseVideo();
		 if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 10))
		  {
			 timeaftplay=playerpagev2.playerCurrentDuration.getText();
			  test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen After Playing the video- "+timeaftplay);
			  if(!timeaftPause.equals(timeaftplay))
				{
				  test.log(LogStatus.PASS,"Testcase : 'Verify the Play button functionality:' is Passed");
				  BasePageV2.smokeresults("", playrow, "Pass");
				}
			  else
				 BasePageV2.reportFail("Failed to play the video");
		  }
		  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
		 
	
	 }
	 else
		 BasePageV2.reportFail("Failed to Pause the video");
	 
	 //Verifying Up Arrow button functionality
	 test.log(LogStatus.INFO, "Verifying Up Arrow Button Functionality");
	 if(Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomUpArrowButton, 10))
	 {
		 test.log(LogStatus.INFO, "Clicking on Up Arrow");
		 playerpagev2.playerBottomUpArrowButton.click();
		 if(Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomDownArrowButton, 10))
		 {
			 if(Utilities.explicitWaitVisible(driver, playerpagev2.playListEpisodeTab, 10))
			 {
				 test.log(LogStatus.INFO, "Episodes section is displayed");
			 }
			 else
			 {
				 test.log(LogStatus.FAIL, "Episodes section is displayed");
			 }
			 if(Utilities.explicitWaitVisible(driver, playerpagev2.playListRelatedTab, 10))
			 {
				 test.log(LogStatus.INFO, "Related section is displayed");
			 }
			 else
				 test.log(LogStatus.FAIL, "Related section is displayed");
			 playerpagev2.playerBottomDownArrowButton.click();
			 test.log(LogStatus.INFO, "Clicking on Down Arrow Button");
			 if(Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomUpArrowButton, 10))
				 {
				 test.log(LogStatus.INFO, "Down Arrow ( Collapse ) button functionality working fine");
				 test.log(LogStatus.PASS,"Testcase : 'Verify the down arrow functionality:' is Passed");
				 BasePageV2.smokeresults("", downrow, "Pass");
				 }
			 else
				 BasePageV2.reportFail("Down Arrow ( Collapse ) button functionality not working fine");
		 }
		 else
		  BasePageV2.reportFail("PlayList (Collapse) Down Arrow button is not displayed");
		 test.log(LogStatus.PASS,"Testcase : 'Verify the Up arrow functionality:' is Passed");
		 BasePageV2.smokeresults("", uprow, "Pass");
	 }
	 else
		 BasePageV2.reportFail("Up arrow functionality failed");

	  //Verifying scrub functionality
	   playerpagev2.slidehalf(driver);
	   
	   test.log(LogStatus.PASS,"Testcase : 'Verify the seek functionality of the player:' is Passed");
	   BasePageV2.smokeresults("", seekrow, "Pass");
	   //Verifying rewind button functionality
	   
		  //Verifying close button	
	   test.log(LogStatus.INFO, "Verifying close button button");
		  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerCloseButton, 10))
		  {
			  playerpagev2.playerCloseButton.click();
		  }
		  else
			  {
			     playerpagev2.pauseVideo();
			     if(Utilities.explicitWaitClickable(driver, playerpagev2.playerCloseButton, 10))
			     playerpagev2.playerCloseButton.click();
				 else
			     BasePageV2.reportFail("Close button is  not displayed ");		
			  }
	   if(!Utilities.explicitWaitVisible(driver, playerpagev2.videoPlayer, 5))
       test.log(LogStatus.PASS,"Testcase : 'Verify the Close button in the player screen:' is Passed");
	   else
	   BasePageV2.reportFail("Player is not closed when tapping on close button");
	   BasePageV2.smokeresults("", closerow, "Pass");
	   
	 
	 
Thread.sleep(3000);
}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}