package com.viacom.vodplayer;

import java.util.Hashtable;

import org.openqa.selenium.By;
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

public class VerifyUpNextOverlayFunctionalityTest extends BaseTestV2 {
	String testName = "VerifyUpNextOverlayFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	//Testcase : 'Verify the functionality of character image in Upnext overlay' is Passed");//P1
	test = rep.startTest("Verify Player Upnext Overlay UI Functionality");
	test.log(LogStatus.INFO, "Starting the test for Verifying the Player Screen Upnext overlay UI functionality: "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	 String timebefPause="";

		String un=data.get("Email");
		String pwd=data.get("Password");
		
	//click on watch tab	
		test.log(LogStatus.INFO, "Navigating to Watch Page");
		
		if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 30))
		homepagev2.watch_tab.click();
		else
		BasePageV2.reportFail("Not able to click on watch tab");
		
		String xpath="//android.widget.TextView[@text='ALL CHARACTERS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
		Utilities.verticalSwipe(driver, xpath);
		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Navigating to any of show under All Characters Section");
		if(Utilities.explicitWaitClickable(driver, watchpagev2.allCharactersFirstShow, 30))
		watchpagev2.allCharactersFirstShow.click();
		else
		BasePageV2.reportFail("Not able to click show under All Characters section");
		

		//Scroll to  episodes section
		String episode="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']";
		Utilities.verticalSwipe(driver, episode);
		Thread.sleep(1000);
		 //Check for Episodes section
		if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEpisodesSection , 30))
		{
			test.log(LogStatus.INFO, "Episodes section is Present");
			//Scroll to  episodes section video
			String episodevideo="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
			Utilities.verticalSwipe(driver, episodevideo);
			Thread.sleep(1000);
			if(Utilities.explicitWaitVisible(driver, showspagev2.showDetailPageEpisodesSectionFirstVideo , 30))
			{		
				test.log(LogStatus.INFO, "Videos are displayed under Episodes section");
				showspagev2.showDetailPageEpisodesSectionFirstVideo.click();
			}
			else
				BasePageV2.reportFail("Not able to play content under Episodes section");
		}
		else
			BasePageV2.reportFail("Episodes section is not present");
				  //Check for progress bar
				  homepagev2.verifyAndProgressBar();
				
				  //Pause the video
				  playerpagev2.pauseVideo();
				 
							  
				  //Check for Title	  				
				  String titletext="", subtitletext="",subtitletext2="";
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerTitle, 10))
				  {
					  titletext=playerpagev2.playerTitle.getText();
					  test.log(LogStatus.INFO, "Content Title is displyed on the Player screen - "+titletext);
				  }
				  else
					  BasePageV2.reportFail("Content Title is not displayed");
				  
				  //Check for sub Title	  				  
				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10))
				  {
					  subtitletext=playerpagev2.playerSubTitle.getText();
					  test.log(LogStatus.INFO, "Content sub Title is displayed on the Player screen -"+subtitletext);
				  }
				  else
					  BasePageV2.reportFail("Content sub Title is not displayed");
				  	
	  //Doing full scrub 
	   playerpagev2.slideFull(driver);
	   System.out.println(driver.getPageSource());
	   System.out.println(driver.getPageSource());
	  /* if(Utilities.explicitWaitClickable(driver, playerpagev2.upNextImage, 10))
		  {
		      playerpagev2.upNextImage.click();
		      homepagev2.verifyAndProgressBar();
			
		  }*/
		/*  else
			  BasePageV2.reportFail("Upnext image is not displayed");*/
	   
	   //Pause the video
		 // playerpagev2.pauseVideo();
	   
	   
	   
	   //Verify cancel functionality
	    driver.findElement(By.id("com.viacom18.vootkids:id/cancel_button")).click();
	   if(Utilities.explicitWaitVisible(driver, playerpagev2.upnextOverlayCancelButton, 60))
		  {
		      playerpagev2.upnextOverlayCancelButton.click();
		      System.out.println(driver.getPageSource());
		      if(Utilities.explicitWaitClickable(driver, playerpagev2.replayVideoButton, 10))
		    	  playerpagev2.replayVideoButton.click();
		    /*  else
		    	  BasePageV2.reportFail("Replay button is not displayed after clicking cancel on Upnext overlay");*/
		  }
	   else
	   {
		   BasePageV2.reportFail("Not able to click on cancel button / Cancel button is not present on Upnext overlay");
	   }
		  
	   homepagev2.verifyAndProgressBar();
	   //Pause the video
		  playerpagev2.pauseVideo();
		  
	   //Check for sub Title	  				  
		  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10))
		  {
			  subtitletext2=playerpagev2.playerSubTitle.getText();
			  test.log(LogStatus.INFO, "Upnext Content sub Title is displayed on the Player screen -"+subtitletext2);
			  if(subtitletext.equalsIgnoreCase(subtitletext2))
				  test.log(LogStatus.PASS,"Testcase : 'Verify the functionality of character image in Upnext overlay' is Passed");//P1
			  else
				  BasePageV2.reportFail("Failed to load next video after clicking Character image on Upnext overlay");
				  
		  }
		  else
			  BasePageV2.reportFail("Content sub Title is not displayed");
	 
Thread.sleep(3000);
}

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}