package com.viacom.downloads;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;

public class VerifyDownloadedEpisodesPlaybackTest extends BaseTestV2 {

	String testName = "VerifyDownloadedEpisodesPlaybackTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Episode Downloads playback  Functionality");
	test.log(LogStatus.INFO, "Starting the test for downloaded episode playback Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int cancelrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",cancelrow,"Verify the Cancel  button functionality in the pop-up:");	
	
	Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrowui=xls2.getRowCount("Smoke_Results")+1;
	xls2.setCellData("Smoke_Results", "Testcase Name",uparrowui, "Verify the UI of Uparrow in player for the downloaded content when playing in online mode.");
	
	Xls_Reader xls3 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int metarow=xls3.getRowCount("Smoke_Results")+1;
	xls3.setCellData("Smoke_Results", "Testcase Name",metarow, "Verify metadata for the cards present under All downloads tray:");
	

	Xls_Reader xls4 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int navirow=xls4.getRowCount("Smoke_Results")+1;
	xls4.setCellData("Smoke_Results", "Testcase Name",navirow, "Verify navigation for the cards present under 'All Downloads' content tray");
	
	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int initiaterow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",initiaterow, "Verify the functionality when user taps on 'Not Yet Initiated' status episodes in 'Download Episodes' screen:");
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedplaybackrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",downloadedplaybackrow, "Verify the functionality by tapping on 'Completely Downloaded' episode cards in 'Download Episodes' screen:");
	

	Xls_Reader xls7 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrowrow=xls7.getRowCount("Smoke_Results")+1;
	xls7.setCellData("Smoke_Results", "Testcase Name",uparrowrow, "Verify the availablity of Up arrow option if there is only 1 VOD content in downloads tab");
	
	Xls_Reader xls8 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int uparrow2=xls8.getRowCount("Smoke_Results")+1;
	xls8.setCellData("Smoke_Results", "Testcase Name",uparrow2, "Verify the availablity of Uparrow option if there are more than 2 VOD contents in downloads tab");
	
	
	Xls_Reader xls9 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadsectionrow=xls9.getRowCount("Smoke_Results")+1;
	xls9.setCellData("Smoke_Results", "Testcase Name",downloadsectionrow, "All Downloads section should have all downloaded contents which is scrollable towards left/right");
	
	Xls_Reader xls10 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int looprow=xls10.getRowCount("Smoke_Results")+1;
	xls10.setCellData("Smoke_Results", "Testcase Name",looprow, "Verify if Downloaded contents will be played in loop if there are more than 2 contents");
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);

     WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
     ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
     KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
	 
	 downloadspagev2.setDeviceDownloadQuality("Low");
	 int err_count=0;
	  int size=0;
	//click on watch tab	
		test.log(LogStatus.INFO, "Navigating to Watch Page");
		
		if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 30))
		homepagev2.watch_tab.click();
		else
		BasePageV2.reportFail("Not able to click on watch tab");
	
		String xpath="//android.widget.TextView[@text='ALL KIDS CHARACTERS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
		Utilities.verticalSwipe(driver, xpath);
		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Navigating to any of show under ALL KIDS CHARACTERS Section");
		
		if(Utilities.explicitWaitClickable(driver, watchpagev2.allCharactersFirstShow, 30))
		watchpagev2.allCharactersFirstShow.click();
		else
		BasePageV2.reportFail("Not able to click show under ALL KIDS CHARACTERS section");
		String showname="";
	    if(Utilities.explicitWaitClickable(driver, showspagev2.showDetailPageShowTitle, 20))
			 {
	    	    showname=showspagev2.showDetailPageShowTitle.getText().trim();		    	
		    	
			 }
			 else
				 BasePageV2.reportFail("Not navigated to Download Episodes Screen");
			
	    String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	    Utilities.verticalSwipe(driver, downloadlink);
	
	    if(Utilities.explicitWaitClickable(driver, watchpagev2.downloadEpisodesLink, 20))
		 {
			 Utilities.verticalSwipe(driver);
			 watchpagev2.downloadEpisodesLink.click();
			 test.log(LogStatus.INFO, "Clicking on Download Icon in show Detail page - "+showname);
		 }
		 else
			 BasePageV2.reportFail("Not able to click on Download Audio book / Download link not available for the show-"+showname);
		
		List<String> downloadTitles=new LinkedList<String>();
String episode1="",episode2="";
	    if(Utilities.explicitWaitClickable(driver, showspagev2.downloadEpisodesScreenTitle, 20))
		 {
	    	test.log(LogStatus.INFO, "Navigated to Download Episodes Screen");	
	    	if(showspagev2.downloadsScreenEpisodeTitles.size()>0)
	           {
	        	   episode1=showspagev2.downloadsScreenEpisodeTitles.get(0).getText();
	           }
	    	else
	    		test.log(LogStatus.FAIL, "Episode title is missing in Downloads screen");
	    
	    	  test.log(LogStatus.INFO, "Clicking on Download Icon for  episode  "+episode1);	    	
	    	  if(Utilities.explicitWaitClickable(driver, showspagev2.downloadIconInDownloadsScreen, 20))
	    	  {
		    		  showspagev2.downloadIconInDownloadsScreen.click();
	    	  }
	    	  else
	    		  BasePageV2.reportFail("Download icon not present");
	    	
		 }
		 else
			 BasePageV2.reportFail("Not navigated to Download Episodes Screen");
		
	
		
  	  if(Utilities.explicitWaitClickable(driver, downloadspagev2.inprogress, 30))
  	  {
  		  test.log(LogStatus.INFO, "Download is initiated");
  		  test.log(LogStatus.PASS, "Testcase : 'Verify the functionality when user taps on 'Not Yet Initiated' status episodes in 'Download Episodes' screen:' is Passed");
  		  BasePageV2.smokeresults("", initiaterow, "Pass");
  		  downloadspagev2.inprogress.click();
  	  }
  	  else
  		  BasePageV2.reportFail("Download is not initiated after tapping download icon");
  	if(Utilities.explicitWaitClickable(driver, showspagev2.cancelDownloadOption, 30))
	 {
		 test.log(LogStatus.INFO, "Cancel Download Option is displayed");		 
		 test.log(LogStatus.INFO, "Tapping on  Cancel Download Option");
		 try{
			 showspagev2.cancelDownloadOption.click();
		 }
		 catch(Exception e){
			 BasePageV2.reportFail("Dowbload is completed before checking scenario");
		 }
	 }
  	else
  		BasePageV2.reportFail("Cancel download option not displayed when tapping on downloading episode");
		 if(Utilities.explicitWaitVisible(driver, showspagev2.cancelDownloadPopup, 30))
		 {
			 test.log(LogStatus.INFO, "Cancel/delete Pop up is displayed");			
			
			 if(Utilities.explicitWaitClickable(driver, showspagev2.cancelDownloadPopupNoButton, 5))
			 {
				 test.log(LogStatus.INFO, "Tapping on  No/Cancel button");
				// BasePageV2.takeScreenshot();
				 showspagev2.cancelDownloadPopupNoButton.click();
			 }
			 else
			 BasePageV2.reportFail("Cancel/no button is not displayed in popup");
			 
			       if(Utilities.explicitWaitClickable(driver, downloadspagev2.inprogress, 5))
			       {
				  	  test.log(LogStatus.PASS,"Testcase : 'Verify Cancel button functionality in the pop-up:' is Passed");
				  	  BasePageV2.smokeresults("", cancelrow, "Pass");
				  	
			        }
				    else if(Utilities.explicitWaitClickable(driver, showspagev2.downloadedEpisodeIcon, 5))
				    	{
				    	    test.log(LogStatus.INFO, "Download is complete");
						  	test.log(LogStatus.PASS,"Testcase : 'Verify Cancel button functionality in the pop-up:' is Passed");
				    	}
				    else
				        BasePageV2.reportFail("Not able to find Content downloading in Progress state");
			
		 }
		 else
			 BasePageV2.reportFail("Cancel/delete Popup is not displayed");
		 
		 if(Utilities.explicitWaitClickable(driver,showspagev2.downloadedEpisodeIcon, 400))
	  	  {
	  		  test.log(LogStatus.INFO, "Download is completed");
	  		  test.log(LogStatus.INFO, "Tapping on completely Downloaded episode -"+episode1);
	  		  showspagev2.downloadedEpisodeIcon.click();
	  		
	  		 if(Utilities.explicitWaitClickable(driver,playerpagev2.videoPlayer, 20))  
	  		 {
	  			 homepagev2.verifyAndProgressBar();
	  			 playerpagev2.pauseVideo();
	  			if(Utilities.explicitWaitVisible(driver,playerpagev2.playerSubTitle, 20))  
	  			{
	  				if(playerpagev2.playerSubTitle.getText().equalsIgnoreCase(episode1))
	  				{
	  				   test.log(LogStatus.PASS, "Testcase: 'Verify the functionality by tapping on 'Completely Downloaded' episode cards in 'Download Episodes' screen:' is Passed");
	  				   BasePageV2.smokeresults("", downloadedplaybackrow, "Pass");
	  				}
	  				else
	  					test.log(LogStatus.FAIL, "Player launched but title is not matching");
	  					
	  			}
	  			else
	  				test.log(LogStatus.FAIL, "Title is missing in the player");
	  			
	  			test.log(LogStatus.INFO, "verifying whether Up-Arrow button displayed when there is only one downloaded episode content");
	  			
	  			if(Utilities.explicitWaitClickable(driver,playerpagev2.playerBottomUpArrowButton, 5))  
	  			{
	  				test.log(LogStatus.FAIL, "Up Arrow button is displayed when there is only one downloaded episode content");
	  			    BasePageV2.takeScreenshot();
	  			}
	  			else
	  				{
	  				test.log(LogStatus.PASS, "Testcase : 'Verify the availablity of Up arrow option if there is only 1 VOD content in downloads tab' is Passed");
	  				BasePageV2.smokeresults("", uparrowrow, "Pass");
	  				}
	  			
	  			if(Utilities.explicitWaitVisible(driver,playerpagev2.playerCloseButton, 20))  
	  			{
	  				playerpagev2.playerCloseButton.click();
	  			}
	  			else
	  				test.log(LogStatus.FAIL, "Close button missing in the player");
	  		 }
	  		 else
	  			 BasePageV2.reportFail("Player not launched when tapping downnloaded episode content");
	  	
	  		   
	  	  }
	  	  else
	  		  BasePageV2.reportFail("Download icon not present");
		 if(showspagev2.downloadsScreenEpisodeTitles.size()>0)
         {
      	   episode2=showspagev2.downloadsScreenEpisodeTitles.get(1).getText();
         }
  	else
  		test.log(LogStatus.FAIL, "Episode title is missing in Downloads screen");
  
  	test.log(LogStatus.INFO, "Clicking on Download Icon for  episode  "+episode2);
  	
  	  if(Utilities.explicitWaitClickable(driver, showspagev2.downloadIconInDownloadsScreen, 20))
  	  {
	    showspagev2.downloadIconInDownloadsScreen.click();
  	  }
  	  else
  		  BasePageV2.reportFail("Download icon not present");
		
  	 if(Utilities.explicitWaitClickable(driver,showspagev2.downloadedEpisodeIcons.get(1), 400))
 	  {
 		  test.log(LogStatus.INFO, "Download is completed");
 		  showspagev2.downloadedEpisodeIcons.get(0).click();
 		  test.log(LogStatus.INFO, "Tapping on completely downloaded episode -"+episode1);
 		
 		  		
 	  }
  	 else
  		 BasePageV2.reportFail("Download not completed after long time for episode-"+episode2);
  	  
  	 
  	 if(Utilities.explicitWaitClickable(driver,playerpagev2.videoPlayer, 20)) 
	  {
		 homepagev2.verifyAndProgressBar();
		 playerpagev2.pauseVideo();
	  }
	 else
	 BasePageV2.reportFail("Player did not lauch when tapping downloaded content -"+episode1);
  	 
  	test.log(LogStatus.INFO, "verifying whether Up-Arrow button displayed when there are more than one downloaded episode content");
		
		if(Utilities.explicitWaitClickable(driver,playerpagev2.playerBottomUpArrowButton, 5))  
		{
			test.log(LogStatus.PASS, "Testcase : 'Verify the availablity of Uparrow option if there are more than 2 VOD contents in downloads tab' is Passed");
			BasePageV2.smokeresults("", uparrow2, "Pass");
			playerpagev2.playerBottomUpArrowButton.click();
			
		}
		else
			{
			BasePageV2.reportFail("Up Arrow button is not displayed when there are more than one downloaded episode contents");
			}
		
		
		test.log(LogStatus.INFO, "Verify the UI of Uparrow in player for the downloaded content");
		
		if(Utilities.explicitWaitVisible(driver,playerpagev2.playListAllDownloadsTab, 5)) 
		{
			test.log(LogStatus.INFO,"All Downloads Tray is present");
			test.log(LogStatus.PASS, "Testcase : 'Verify the UI of Uparrow in player for the downloaded content when playing in online mode' is Passed");
			BasePageV2.smokeresults("",uparrowui, "Pass");
		}
		else
		{
			BasePageV2.reportFail("Overlay/All Downloads Tray not present");
		}
		
		String playlisttitle1 = null,playlisttitle="";
		if(Utilities.explicitWaitVisible(driver,playerpagev2.playListEpisodeTitle, 5)) 
		{
			 playlisttitle1=playerpagev2.playListEpisodeTitle.getText();
			test.log(LogStatus.INFO,"Verifying whether all downloaded contents present under All Downloads Tray");
			for(int i=0;i<playerpagev2.playListEpisodeTitles.size();i++)
			{
				playlisttitle=playerpagev2.playListEpisodeTitles.get(i).getText();
				if(playlisttitle.contains(episode1) || playlisttitle.contains(episode2))
				{
					BasePageV2.reportFail("Downloaded content not displayed under All Downloads tray");
				}
			}
			
		}
		else
		{
			BasePageV2.reportFail("Episode Title is missing for downloaded episode under All Downloads Tray");
		}
  	 
		test.log(LogStatus.PASS, "Testcase : 'All Downloads section should have all downloaded contents which is scrollable towards left/right' is Passed");
		BasePageV2.smokeresults("", downloadsectionrow, "Pass");
		
		
		if(playerpagev2.playListEpisodeContents.size()>0)
		{
		
			for(int i=0;i<playerpagev2.playListEpisodeContents.size();i++)
			{
				//Verifying whether all contents all meta data
				String episodexpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/linear_recycler_view']//android.view.ViewGroup[@index='"+i+"']";
				
				//Checking show Title
				try{
					driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Show Title is not present");
				}
				
				//Checking episode Title
				try{
				   driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']");
				 
				}
				catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Episode Title is not present");
				}
				
				//Checking Duration
				
				try{
					driver.findElementByXPath(episodexpath+"//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']");
				}
				catch(Exception e)
				{
					if(i!=3)
					test.log(LogStatus.FAIL, "Duration is not present for the content");
				}
				
				//checking watch icon
				
				try{
					driver.findElementByXPath(episodexpath+"//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/grid_item_category']");
				}
				catch(Exception e)
				{
					if(i!=3)
					test.log(LogStatus.FAIL, "Watch icon is not present for the content");
				}
			}
		
		}
		else
			BasePageV2.reportFail("No Downloaded episodes present under All Downloads Tray");
		
		
		        if(Utilities.explicitWaitVisible(driver,playerpagev2.episodeVideoInPlaylist, 5)) 
				playerpagev2.episodeVideoInPlaylist.click();
				else
				BasePageV2.reportFail("No Downloaded episodes present under All Downloads Tray");
		        
		        if(Utilities.explicitWaitClickable(driver,playerpagev2.videoPlayer, 20)) 
		  	  {
		  		 homepagev2.verifyAndProgressBar();
		  		 playerpagev2.pauseVideo();
		  	  }
		  	 else
		  	 BasePageV2.reportFail("Player did not lauch when tapping downloaded content from  All Downloads playlist Tray-"+playlisttitle1);
		    	
		        
		        if(Utilities.explicitWaitVisible(driver,playerpagev2.playerSubTitle, 20))  
	  			{
	  				if(playerpagev2.playerSubTitle.getText().equalsIgnoreCase(playlisttitle))
	  				{
	  				   test.log(LogStatus.PASS, "Testcase: 'Verify navigation for the cards present under 'All Downloads' content tray' is Passed");
	  				   BasePageV2.smokeresults("", navirow, "Pass");
	  				}
	  				else
	  					test.log(LogStatus.FAIL, "Player launched when playing downloaded episode content from All Downloads tray but title is not matching");
	  					
	  			}
	  			else
	  				test.log(LogStatus.FAIL, "Title is missing in the player");   
		        
		        playerpagev2.slideFull(driver);
		        homepagev2.verifyAndProgressBar();
		        playerpagev2.pauseVideo();
		        
                test.log(LogStatus.INFO, "Verifying whether all downloaded content playing in loop");
		        if(Utilities.explicitWaitVisible(driver,playerpagev2.playerSubTitle, 20))  
	  			{
	  				if(playerpagev2.playerSubTitle.getText().equalsIgnoreCase(playlisttitle))
	  				{
	  				   test.log(LogStatus.PASS, "Testcase: 'Verify if Downloaded contents will be played in loop if there are more than 2 contents' is Passed");
	  				   BasePageV2.smokeresults("", looprow, "Pass");
	  				}
	  				else
	  				{
	  					test.log(LogStatus.FAIL, "Failed to play next video");
	  					BasePageV2.takeScreenshot();
	  				}
	  					
	  			}
	  			else
	  				test.log(LogStatus.FAIL, "Title is missing in the player");         
		        
		        
		        
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}