package com.viacom.downloads;

import java.util.ArrayList;
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
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.connection.ConnectionState;
//Author Tanisha
public class VerifyDownloadsUpNextInOffline extends BaseTestV2{
	
	String testName = "VerifyDownloadsUpNextInOffline";
	@Test(dataProvider = "getData")
	public void VerifyDownloadsUpNextInOffline(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyDownloadsUpNextInOffline");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		//Launch Voot kids app
		//Login module to be added
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
		 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
		 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
		 //homepagev2.login(data.get("Email"),data.get("Password"));
		 //downloadsv2.deleteAllDownloads();
		 
		Xls_Reader xls1052 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1052=xls1052.getRowCount("Smoke_Results")+1;
		xls1052.setCellData("Smoke_Results", "Testcase Name",rowno1052,"Verify the UI of All Downloads section under Content Trays");
		
		Xls_Reader xls1156 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1156=xls1156.getRowCount("Smoke_Results")+1;
		xls1156.setCellData("Smoke_Results", "Testcase Name",rowno1156,"Verify resuming of downloaded playback in offline mode");
		
		Xls_Reader xls911 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno911=xls911.getRowCount("Smoke_Results")+1;
		xls911.setCellData("Smoke_Results", "Testcase Name",rowno911,"911: Verify All completely downloaded contents should reflect under My Stuff - Downloads tab.");
		
		Xls_Reader xls912 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno912=xls912.getRowCount("Smoke_Results")+1;
		xls912.setCellData("Smoke_Results", "Testcase Name",rowno912,"912: Verify Queued content from Episodes should be reflected in Downloads tab");

		int err1052=0;		 	
		int totalEpisodes=0;
		int totalDownloadedEpisodes=0;
		int waitFor4=0;
		ArrayList<String> episodeNames=new ArrayList<String>();
		String playedTillString="";
		int playedTillInt=0;
		String playedFromString="";
		int playedFromInt=0;
		homepagev2.tabClick("Watch");
		//Scroll to ALL CHARACTERS and click on the first character
		 String allCharacters="//android.widget.TextView[@text='ALL KIDS CHARACTERS']";
		 boolean allCharactersPresence=Utilities.verticalSwipeAndFind(driver,allCharacters);
		 if(allCharactersPresence==true) {
			 test.log(LogStatus.INFO, "Scrolled to ALL KIDS CHARACTERS tray");
			 //Click on the first item
			 try {
				 String firstCharacterLoc="//android.widget.TextView[@text='ALL KIDS CHARACTERS']/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/title_container']/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup";
				 Utilities.verticalSwipeAndFind(driver,firstCharacterLoc);
				 watchpagev2.allCharactersFirstItem.click();
				 test.log(LogStatus.INFO, "Clicked on first item of ALL KIDS CHARACTERS tray");
				 String downloadEpLoc="//android.widget.TextView[@text='Download Episodes']";
				 boolean downloadEpText=Utilities.verticalSwipeAndFind(driver,downloadEpLoc);
				 if(downloadEpText==true) {
					 test.log(LogStatus.INFO, "Scrolled to \"Download Episodes\" text in Show details page");
					 if(Utilities.explicitWaitClickable(driver, showpageV2.downloadEpisodesText, 3)) {
						 showpageV2.downloadEpisodesText.click();
						 test.log(LogStatus.INFO,"Clicked on \"Download Episodes\" text in Show Details page");
						 try {
							 totalEpisodes=showpageV2.notAddedEpisodes.size();
						 }
						 catch(Exception e) {
							 test.log(LogStatus.FAIL, "Unable to fetch the total count of episodes");
						 }
						 for(int i=0;i<totalEpisodes;i++) {
							 String ep=showpageV2.notAddedEpisodes.get(i).getAttribute("text");
							 episodeNames.add(ep);
						 }
						 for(int i=0;i<totalEpisodes;i++) {
							 try {
								 driver.findElement(By.xpath("//android.widget.TextView[@text='"+episodeNames.get(i)+"']")).click();
								 test.log(LogStatus.INFO, "Tapped on episode "+episodeNames.get(i));
							 }
							 catch(Exception e) {
								 try {
									 driver.findElement(By.xpath("//android.widget.TextView[@text='"+episodeNames.get(i)+"']")).click();
									 test.log(LogStatus.INFO, "Tapped on episode "+episodeNames.get(i));	
								 }
								 catch(Exception e1) {
									 test.log(LogStatus.FAIL, "Failed to locate episode"+episodeNames.get(i));	
								 }
							 }
							 
						 }
						 //Wait until 4 episodes get downloaded
						 while(waitFor4<=30) {
							try {
								totalDownloadedEpisodes=showpageV2.downloadedEpisodes.size();
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Unable to fetch the total count of downloaded episodes");
							}
						 	if(totalDownloadedEpisodes<4) {
						 		Thread.sleep(1000);
						 		waitFor4++;
						 	}
						 	else if(totalDownloadedEpisodes>=4)
						 		break;
						 	else if(waitFor4==30) {
						 		test.log(LogStatus.FAIL, "Failed to download 4 episodes in 30 seconds");
						 	}
						 }
						 //Navigate to Downloads in My Stuff
						 driver.navigate().back();
						 driver.navigate().back();
						 Utilities.verticalSwipeReverse(driver);
						 Utilities.verticalSwipeReverse(driver);
						 test.log(LogStatus.INFO, "Navigated to Home page");
						if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
							homepagev2.mystuff_tab.click();
							test.log(LogStatus.INFO, "Clicked on My Stuff tab");
							//Scroll to Downloads to verify the meta data
							 String downloadTabLocator="//android.widget.TextView[@text='Downloads']";
							 boolean tabPresence=Utilities.verticalSwipeAndFind(driver,downloadTabLocator);
							 if(tabPresence==true) {
								 Utilities.verticalSwipe(driver);
								 if(homepagev2.downloadsTab.getAttribute("selected").equals("false")) {
									 try {
										 homepagev2.downloadsTab.click();
										 Utilities.verticalSwipe(driver);
										 test.log(LogStatus.INFO, "Clicked on Downloads tab"); 	 
									 }
									 catch(Exception e) {
										 test.log(LogStatus.FAIL, "Unable to click on Downloads tab");
									 }
								 } 
								 //Verify if Downloads is enabled
								 try {
									 boolean selected=homepagev2.downloadsTab.getAttribute("selected").equals("true");
									 if(selected==true)
										 test.log(LogStatus.INFO, "Downloads tab is highlighted"); 
								 }
								 catch(Exception e) {
									 test.log(LogStatus.FAIL, "Downloads tab is not highlighted");
								 }
								//Verify the meta data
								 for(int count=0;count<episodeNames.size();count++) {
									 try {
										 driver.findElement(By.xpath("//android.widget.TextView[@text='"+episodeNames.get(count)+"']"));
										 test.log(LogStatus.INFO, "The Episode "+episodeNames.get(count)+"is displayed under Downloads tab");
									 }
									 catch(Exception e) {
										 Utilities.verticalSwipe(driver);
										 try {
											 driver.findElement(By.xpath("//android.widget.TextView[@text='"+episodeNames.get(count)+"']"));
											 test.log(LogStatus.INFO, "The Episode "+episodeNames.get(count)+" is displayed under Downloads tab");
										 }
										 catch(Exception e1) {
											 test.log(LogStatus.INFO, "The Episode "+episodeNames.get(count)+" is not displayed under Downloads tab");
											 errCount++;
										 }
										 
									 }
									 
								 }
								//Verification of meta data
								 if(errCount==0) {
									 
									 test.log(LogStatus.PASS, "The Meta data Episode Name is verified");
									 homepagev2.smokeresults("911: Verify All completely downloaded contents should reflect under My Stuff - Downloads tab is PASS",rowno911, "PASS");
									 homepagev2.reportPass("Test case 911: Verify All completely downloaded contents should reflect under My Stuff - Downloads tab is PASS");
									 homepagev2.smokeresults("912: Verify Queued content from Episodes should be reflected in Downloads tab is PASS",rowno912, "PASS");
									 homepagev2.reportPass("Test case 912: Verify Queued content from Episodes should be reflected in Downloads tab is PASS");
									 
								 }
								 else {
									 test.log(LogStatus.FAIL, "The Episode is not present in Downloads tab");
									 test.log(LogStatus.FAIL, "Test case 911: Verify All completely downloaded contents should reflect under My Stuff - Downloads tab is FAIL");
									 homepagev2.smokeresults("911: Verify All completely downloaded contents should reflect under My Stuff - Downloads tab is FAIL",rowno911, "FAIL");
									 test.log(LogStatus.FAIL, "Test case 912: Verify Queued content from Episodes should be reflected in Downloads tab is FAIL");
									 homepagev2.smokeresults("912: Verify Queued content from Episodes should be reflected in Downloads tab is FAIL",rowno912, "FAIL");
									 basepagev2.takeScreenshot();
								 }
								 
								//Play the episode
								try {
									showpageV2.downloadedEpisodes.get(0).click();
									test.log(LogStatus.INFO, "Tapped on first episode");	
									//Play for 1 minute
									for(int i=0;i<=60;i++) {
										Thread.sleep(1000);
									}
									//Tap on the player and pause video
									boolean paused=false;
									int trypause=0;
									while(paused==false){	
										trypause++;
										try {
											Utilities.tap(driver);
											Utilities.tap(driver);
											playerpagev2.playerPlayPauseButton.click();
											test.log(LogStatus.INFO, "Paused the video");
											break;
										}
										catch(Exception e) {
										}	
										if(trypause==5) {
											break;
										}
									}
									//Get the current duration
									if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 2)) {
										playedTillString=playerpagev2.playerCurrentDuration.getText();
									}
									playedTillInt=playerpagev2.convertStringDurationTOIntegerSeconds(driver,playedTillString);
									test.log(LogStatus.INFO, "Video played till "+playedTillString+" i.e. "+playedTillInt+" seconds");
									//Play the video
									try {
										playerpagev2.playerPlayPauseButton.click();
										test.log(LogStatus.INFO, "Played the video");
									}
									catch(Exception e) {
										test.log(LogStatus.INFO, "Failed to play the video");
									}								
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to play the first downloaded episode");
								}
								//Switch off Wi-Fi
								try {
									driver.setConnection(new ConnectionState(0));
									test.log(LogStatus.INFO, "Turned OFF Wi-Fi connection");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Unable to turn OFF Wi-Fi connection");
								}
								//Close the player
								driver.navigate().back();
								test.log(LogStatus.INFO, "Navigated back and closed the player");
								//Play the same content
								try {
									showpageV2.downloadedEpisodes.get(0).click();
									test.log(LogStatus.INFO, "Again tapped on first episode");	
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to tap first episode");
								}
								//Tap on the player and pause video
								boolean paused=false;
								int trypause=0;
								while(paused==false){	
									trypause++;
									try {
										Utilities.tap(driver);
										Utilities.tap(driver);
										playerpagev2.playerPlayPauseButton.click();
										test.log(LogStatus.INFO, "Paused the video");
										break;
									}
									catch(Exception e) {
									}	
									if(trypause==5) {
										break;
									}
								}
								//Verify the current resume time
								if(Utilities.explicitWaitVisible(driver, playerpagev2.playerCurrentDuration, 2)) {
									playedFromString=playerpagev2.playerCurrentDuration.getText();
								}
								playedFromInt=playerpagev2.convertStringDurationTOIntegerSeconds(driver,playedFromString);
								test.log(LogStatus.INFO, "Video play resumed from "+playedFromString+" i.e. "+playedFromInt+" seconds");
								if(playedTillInt<playedFromInt) {
									homepagev2.smokeresults("Verify resuming of downloaded playback in offline mode",rowno1156, "PASS");
									homepagev2.reportPass("Verify resuming of downloaded playback in offline mode is PASS"); 
								}
								else {
									homepagev2.smokeresults("Verify resuming of downloaded playback in offline mode",rowno1156, "FAIL");
									test.log(LogStatus.FAIL, "Verify resuming of downloaded playback in offline mode is FAIL"); 
									BasePageV2.takeScreenshot();
								}
								//Locate Up arrow
								if(Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomUpArrowButton, 10)) {
									try {
										playerpagev2.playerBottomUpArrowButton.click();
										test.log(LogStatus.INFO, "Clicked on Up Arrow button");
										//Get all the names in ALL DOWNLOADS
										String epFromList;
										String temp;
										ArrayList<String> epFromDownloads=new ArrayList<String>();
										for(int i=0;i<playerpagev2.playListEpisodeContents.size();i++) {
											try {
												temp=playerpagev2.playListEpisodeTitles.get(i).getAttribute("text");
												if(!epFromDownloads.contains(temp))
													epFromDownloads.add(temp);	
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to fetch details of Episode");
											}					
										}	
										Thread.sleep(3000);
										Utilities.horizontalSwipe(driver);
										test.log(LogStatus.INFO, "Swiped horizontally");
										for(int i=0;i<playerpagev2.playListEpisodeContents.size();i++) {
											try {
												temp=playerpagev2.playListEpisodeTitles.get(i).getAttribute("text");
												if(!epFromDownloads.contains(temp))
													epFromDownloads.add(temp);	
											}
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to fetch details of Episode");
											}
										}
										//Check if the downloaded episodes are present in ALL DOWNLOADS
										String episodeFromHome="";
										String epFromDownload="";
										for(int i=0;i<episodeNames.size();i++) {
											episodeFromHome=episodeNames.get(i);
											epFromDownload=epFromDownloads.get(i);										
											if(epFromDownload.contains(episodeFromHome)) 
												test.log(LogStatus.INFO, "ALL DOWNLOADS contains downloaded episode "+epFromDownload);											
											else { 
												test.log(LogStatus.FAIL, "ALL DOWNLOADS does not contain downloaded episode "+epFromDownload);	
												err1052++;
											}
										}										
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to click on Up Arrow button");
									}
								}
								else
									test.log(LogStatus.FAIL, "Up Arrow button is not clickable");
							 }
							 else 
								test.log(LogStatus.FAIL, "Failed to find Downloads tab"); 
						}
						else 
							test.log(LogStatus.FAIL, "My Stuff tab is not clickables"); 	 	 			 
					 }
					 else
						 test.log(LogStatus.FAIL,"Failed to click on \"Download Episodes\" text in Show details page"); 
				 }
				 else
					 test.log(LogStatus.FAIL,"Failed to locate \"Download Episodes\" text in Show details page"); 
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL,"Failed to click on first item under ALL CHARACTERS tray"); 
			 }			 
		 }
		 else
			 test.log(LogStatus.FAIL,"Unable to scroll to ALL CHARACTERS tray");  

	//Turn on Wi-fi again
	try {
		driver.setConnection(new ConnectionState(2));
		test.log(LogStatus.INFO, "Turned ON Wi-Fi connection");
	}
	catch(Exception e) {
		test.log(LogStatus.FAIL, "Unable to turn ON Wi-Fi connection");
	}
	//Final verification of 1052
	if(err1052==0) {
		 homepagev2.smokeresults("Verify the UI of All Downloads section under Content Trays",rowno1052, "PASS");
		 homepagev2.reportPass("Verify the UI of All Downloads section under Content Trays is PASS"); 
	 }
	 else {
		 homepagev2.smokeresults("Verify the UI of All Downloads section under Content Trays",rowno1052, "FAIL");
		 test.log(LogStatus.FAIL, "Verify the UI of All Downloads section under Content Trays is FAIL"); 
		 BasePageV2.takeScreenshot();
	 }
	
}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
