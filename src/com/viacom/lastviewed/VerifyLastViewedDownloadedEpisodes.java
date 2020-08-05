package com.viacom.lastviewed;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyLastViewedDownloadedEpisodes extends BaseTestV2{
	
	String testName = "VerifyLastViewedDownloadedEpisodes";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedDownloadedEpisodes(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedDownloadedEpisodes");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls_VK_1836 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1836=xls_VK_1836.getRowCount("Smoke_Results")+1;
		xls_VK_1836.setCellData("Smoke_Results", "Testcase Name",row_VK_1836,"Verify if the Downloaded Episode cards are added to the Last viewed tray if user launches the player & closes");
		
		Xls_Reader xls_VK_1837 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1837=xls_VK_1837.getRowCount("Smoke_Results")+1;
		xls_VK_1837.setCellData("Smoke_Results", "Testcase Name",row_VK_1837,"Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video partially");

		Xls_Reader xls_VK_1838 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1838=xls_VK_1838.getRowCount("Smoke_Results")+1;
		xls_VK_1838.setCellData("Smoke_Results", "Testcase Name",row_VK_1838,"Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video completely");

		Xls_Reader xls_VK_1841 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1841=xls_VK_1841.getRowCount("Smoke_Results")+1;
		xls_VK_1841.setCellData("Smoke_Results", "Testcase Name",row_VK_1841,"Verify if downloaded Episodes are added to Last Viewed Tray without watching");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 
		 boolean clickedOnShow=false;
		 String episode[]=new String[3];
		 String title[]=new String[3];
		 String season[]=new String[3];
		 String lastViewed1,lastViewed2,lastViewed3="";

		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 
		//Login module 
		//homepagev2.logout();
		//homepagev2.login(data.get("Email"),data.get("Password"));
		 
/*		 downloadsv2.deleteAllDownloads();
		 driver.navigate().back();
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);*/

		 
		//Click on Watch tab
		 homepagev2.tabClick("Watch");
		//Scroll to shows tray
		 for(int scroll=0;scroll<=4;scroll++) {
			 if(Utilities.explicitWaitVisible(driver, watchpagev2.allKidsCharacters2, 5)) 
				 break;
			 else
				 Utilities.verticalSwipe(driver); 
		 }
		 
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.allKidsCharacters2, 10)) {
			 try {
				 watchpagev2.allKidsCharacters2.click();
				 test.log(LogStatus.INFO, "Clicked on 2nd character from kids characters tray");
				 clickedOnShow=true;
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Failed to click on 2nd character from kids characters tray");
			 }
		 }
		 else {
			 test.log(LogStatus.FAIL, "2nd character from kids characters tray is not clickable");
		 }
				 
		 if(clickedOnShow==true) {
			 String downloadEpLoc="//android.widget.TextView[@text='Download Episodes']";
			 boolean downloadEpText=Utilities.verticalSwipeAndFind(driver,downloadEpLoc);
			 if(downloadEpText==true) {
				 test.log(LogStatus.INFO, "Scrolled to \"Download Episodes\" text in Show details page");
				 if(Utilities.explicitWaitClickable(driver, showpagev2.downloadEpisodesText, 3)) {
					 showpagev2.downloadEpisodesText.click();
					 test.log(LogStatus.INFO, "Clicked on \"Download Episodes\" link");
					 //Get the count of already downloaded items
					 for(int i=0;i<=3;i++) {
						 try {
							 title[i]=downloadsv2.yetToDownloadEpisodesTitle.get(i).getAttribute("text");
							 season[i]=downloadsv2.yetToDownloadEpisodesSeason.get(i).getAttribute("text");
							 episode[i]=season[i]+"-"+title[i];
							 driver.findElement(By.xpath("//android.widget.TextView[@text=\""+title[i]+"\"]")).click();
							 test.log(LogStatus.INFO, "Tapped Episode to start download: "+episode[i]);
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Some error occured while initiating download for episode "+i);
						 }
					 }
 
				 }
				 else {
					 test.log(LogStatus.FAIL, "Failed to click on \"Download Episodes\" link");
				 }
			 }
		 }
		 
		 //Click on the Downloaded show
		 for(int i=0;i<=2;i++) {	
			 String downloadedpath="//android.widget.TextView[@text=\""+title[i]+"\"]//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE']";
			 test.log(LogStatus.INFO, "-------------------------------------"); 
			 for(int wait=0;wait<=30;wait++) {
				 try {
					 driver.findElement(By.xpath(downloadedpath)).click();
					 test.log(LogStatus.INFO, "Tapped on downloaded Episode: "+episode[i]);	
					 if(i==0) {
						//TC to Immediately close the player
						try {
							homepagev2.verifyAndProgressBar();
							test.log(LogStatus.INFO, "Episode started to play");
							driver.navigate().back();
							test.log(LogStatus.INFO, "Navigated back to close the player"); 
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Some issue when playing the first episode: "+episode[i]);
						}
						
					 }
					 else if(i==1) {
						//TC to Watch partially and close the player 
						try {
							homepagev2.verifyAndProgressBar();
							test.log(LogStatus.INFO, "Episode started to play");
							kidsplayerv2.pauseVideo();
							Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);
							kidsplayerv2.playVideo();
							driver.navigate().back();
							test.log(LogStatus.INFO, "Navigated back to close the player");	 
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Some issue when playing the second episode: "+episode[i]);
						}
				 
					 }
					 else if(i==2) {
						//TC to Watch completely and close the player  
						try {
							homepagev2.verifyAndProgressBar();
							test.log(LogStatus.INFO, "Episode started to play");
							kidsplayerv2.pauseVideo();
							Utilities.scrubtillend(driver, homepagev2.audioseekBar);
							driver.navigate().back();
							test.log(LogStatus.INFO, "Navigated back to close the player");	 
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Some issue when playing the third episode: "+episode[i]);
						}
					 }
					 break;//very important
				 }
				 catch(Exception e) {
					 Thread.sleep(1000);
					 if(wait==30) {
						 test.log(LogStatus.FAIL, "Download failed for Episode: "+episode[i]);
					 } 
				 } 
			 }
		 }
		//TC to not watch the downloaded episode
		String downloadedpath="//android.widget.TextView[@text=\""+title[3]+"\"]//ancestor::*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE']";
		test.log(LogStatus.INFO, "-------------------------------------"); 
		try {
			 driver.findElement(By.xpath(downloadedpath));
			 test.log(LogStatus.INFO, "Download completed for Episode: "+episode[3]);	
			 test.log(LogStatus.INFO, "Will not launch player for "+episode[3]);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Download failed for Episode: "+episode[3]);
		}
		 
		//Go to Last Viewed to verify
		driver.navigate().back();
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("My Stuff");
		//Scroll till Last Viewed
		String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
	    	if(lastViewed1.equals(episode[2])) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest episode under last viewed");
	    		homepagev2.smokeresults("Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video completely",row_VK_1838, "PASS");
				test.log(LogStatus.PASS, "Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video completely is PASS"); 			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed instead of "+title[2]);
	    		homepagev2.smokeresults("Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video completely",row_VK_1838, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video completely is FAIL"); 		
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 1 is not visible");
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
	    	if(lastViewed2.equals(episode[1])) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed");
	    		homepagev2.smokeresults("Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video partially",row_VK_1837, "PASS");
				test.log(LogStatus.PASS, "Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video partially is PASS");
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed instead of "+title[1]);
	    		homepagev2.smokeresults("Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video partially",row_VK_1837, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Downloaded Episode cards are added to the Last viewed tray if user watches a video partially is FAIL");
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 2 is not visible");
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdItemTitle, 10)) {
	    	lastViewed3=homepagev2.lastViewedThirdItemTitle.getAttribute("text");
	    	if(lastViewed3.equals(episode[0])) {
	    		test.log(LogStatus.INFO, lastViewed3+" is the third latest episode under last viewed");
	    		homepagev2.smokeresults("Verify if the Downloaded Episode cards are added to the Last viewed tray if user launches the player & closes",row_VK_1836, "PASS");
				test.log(LogStatus.PASS, "Verify if the Downloaded Episode cards are added to the Last viewed tray if user launches the player & closes is PASS"); 			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest episode under last viewed instead of "+title[0]);
	    		homepagev2.smokeresults("Verify if the Downloaded Episode cards are added to the Last viewed tray if user launches the player & closes",row_VK_1836, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Downloaded Episode cards are added to the Last viewed tray if user launches the player & closes is FAIL"); 		
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 3 is not visible");
	    }
	    int countOfLastViewed=homepagev2.lastViewedItemTitles.size();
	    test.log(LogStatus.INFO, "List of Last Viewed items: ");
	    test.log(LogStatus.INFO, ""+homepagev2.lastViewedItemTitles);
	    for(int i=0;i<countOfLastViewed;i++) {
	    	if(homepagev2.lastViewedItemTitles.get(i).equals(episode[3])) {
	    		test.log(LogStatus.FAIL, "Downloaded episode "+episode[3]+" is present in Last Viewed even though not watched");
	    		homepagev2.smokeresults("Verify if downloaded Episodes are added to Last Viewed Tray without watching",row_VK_1841, "FAIL");
				test.log(LogStatus.FAIL, "Verify if downloaded Episodes are added to Last Viewed Tray without watching is FAIL"); 		
				break;
	    	}
	    	else if(i==(countOfLastViewed-1)) {
	    		test.log(LogStatus.INFO, "Downloaded episode "+episode[3]+" is not present in Last Viewed because it is not watched");
	    		homepagev2.smokeresults("Verify if downloaded Episodes are added to Last Viewed Tray without watching",row_VK_1841, "PASS");
				test.log(LogStatus.PASS, "Verify if downloaded Episodes are added to Last Viewed Tray without watching is PASS"); 		
	    	}
	    }
	   
	    basepagev2.takeScreenshot();
	    //Clear Last Viewed
	    homepagev2.clearLastViewed();
	    
		
		watchpagev2.allKidsCharacters2=null;
		showpagev2.episodesTray=null;
		showpagev2.showDetailsEpisode1=null;
		showpagev2.showDetailsEpisode1Title=null;
		showpagev2.showDetailsEpisode2=null;
		showpagev2.showDetailsEpisode2Title=null;
		showpagev2.showDetailsEpisode3=null;
		showpagev2.showDetailsEpisode3Title=null;
		showpagev2.downloadEpisodesText=null;
		homepagev2.lastViewedFirstItemTitle=null;
		homepagev2.lastViewedSecondItemTitle=null;
		homepagev2.lastViewedThirdItemTitle=null;
		homepagev2.mystuff_tab=null;
		downloadsv2.yetToDownloadEpisodesTitle=null;
		downloadsv2.yetToDownloadEpisodesSeason=null;
		downloadsv2.yetToDownloadEpisodes=null;
		
		
		

	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
