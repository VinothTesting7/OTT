package com.viacom.downloads;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;
//Author Tanisha
public class VerifyDownloads extends BaseTestV2{
	
	String testName = "VerifyDownloads";
	@Test(dataProvider = "getData")
	public void VerifyDownloads(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyDownloads");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}

		String episodeNameBefore="";
		String audioNameBefore="";
		String bookNameBefore="";
		
		// VK_944 Verify the UI of Downloads tab when there are available contents
		// VK_951 Verify the meta data for episode cards
		// VK_952 Verify the meta data for Ebooks
		// VK_953 Verify the metadata displayed for Audio Books
		// VK_1018 Verify if the count on the delete button increments on selecting more than one content to delete	
		// VK_1019 Verify if the count on the delete button decrements on selecting more than one content to delete
		// VK_1020 Verify if the delete download pop-up appears on tapping the delete button
		// VK_1022 Verify the delete functionality on selecting yes in the delete downloads pop-up
		
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
		homepagev2.login(data.get("Email"),data.get("Password"));
		downloadsv2.deleteAllDownloads();
		int err944=0;
		int err1018=0;
		int err1019=0;
		int err1020=0;
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);	
		//Click on Watch tab
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
		 boolean trayPresence=false;
		 for(int scroll=0;scroll<=3;scroll++) {
			 try {
				 driver.findElement(By.xpath(trayLoc));
				 trayPresence=true;
				 break;
			 }
			 catch(Exception e) {
				 Utilities.verticalSwipe(driver);
				 if(scroll==3) {
					 test.log(LogStatus.FAIL, "Failed to scroll to "+trayTitle+" in UI");
					 homepagev2.takeScreenshot();
				 }
			 }
		 }
		 boolean presence=false;
		 if(trayPresence==true) {
			 test.log(LogStatus.INFO, "Scrolled to character show card under tray");
			 for(int scroll=0;scroll<=1;scroll++) {
				 try {
					 driver.findElement(By.xpath(allCharFirstItem));
					 presence=true;
					 break;
				 }
				 catch(Exception e) {
					 Utilities.verticalSwipe(driver);
					 if(scroll==1) {
						 test.log(LogStatus.FAIL, "Failed to scroll to character show card under tray");
						 homepagev2.takeScreenshot();
					 }
				 } 
			 }
		 }
		 boolean downloadLink=false;
		 if (presence==true) {
			 try {
				 test.log(LogStatus.INFO,"First Show is displayed");
				 driver.findElement(By.xpath(allCharFirstItem)).click(); 
				 test.log(LogStatus.INFO, "Clicked on first show");
				 String downloadEpLoc="//android.widget.TextView[@text='Download Episodes']";
				 for(int scroll=0;scroll<=3;scroll++) {
					 try {
						 driver.findElement(By.xpath(downloadEpLoc));
						 downloadLink=true;
						 break;
					 }
					 catch(Exception e) {
						 Utilities.verticalSwipe(driver);
						 if(scroll==3) {
							 test.log(LogStatus.FAIL, "Failed to scroll to link Download Episodes");
							 homepagev2.takeScreenshot();
						 }
					 } 
				 }
			 }
			 catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on Download Episodes link"); 
			 }
		 }
		 if(downloadLink==true) {
			 test.log(LogStatus.INFO, "Scrolled to \"Download Episodes\" text in Show details page");
			 if(Utilities.explicitWaitClickable(driver, showpageV2.downloadEpisodesText, 3)) {
				 showpageV2.downloadEpisodesText.click();
				 test.log(LogStatus.INFO,"Clicked on \"Download Episodes\" text in Show Details page");
				 if(Utilities.explicitWaitVisibleNew(driver, showpageV2.notAddedEpisode, 10)) {
					 episodeNameBefore=showpageV2.notAddedEpisode.getAttribute("text");
					 if(Utilities.explicitWaitClickable(driver, showpageV2.notAddedEpisode, 10)) {
						 showpageV2.notAddedEpisode.click();
						 test.log(LogStatus.INFO,"Clicked on first episode to download: "+episodeNameBefore);
						 if(Utilities.explicitWaitClickable(driver, homepagev2.backButton, 10)) {
							 homepagev2.backButton.click();
							 test.log(LogStatus.INFO, "Clicked on Software Back button to navigate to Show details page");
						 }
						 else {
							 test.log(LogStatus.FAIL, "Software Back button is not clickable");
							 driver.navigate().back();
						 }
						 
					 }
					 else 
						test.log(LogStatus.FAIL, "First item is not clickable");	
				 }
				 else 
					test.log(LogStatus.FAIL, "Unable to fetch the name of the first item");	
			 }
			 else
				 test.log(LogStatus.FAIL,"Failed to click on \"Download Episodes\" text in Show details page"); 
		 }
		 else
			 test.log(LogStatus.FAIL,"Failed to locate \"Download Episodes\" text in Show details page");

//Add a book

	driver.navigate().back();
	test.log(LogStatus.INFO, "Navigated back to Watch page");
	Utilities.verticalSwipeReverse(driver);
	test.log(LogStatus.INFO, "Performed vertical swipe up");
	Thread.sleep(1000);
	Utilities.horizontalSwipe(driver);
	test.log(LogStatus.INFO, "Performed horizontal swipe to navigate to Read tab");
	//workaround
	try {
		homepagev2.read_tab.click();
	}
	catch(Exception e) {}
	if(Utilities.explicitWaitClickable(driver, readpagev2.secondBookBelowCarousal, 10)) {
		readpagev2.secondBookBelowCarousal.click();
		test.log(LogStatus.INFO, "Clicked on the Book from Read Carousal");
		Utilities.verticalSwipe(driver);
		try {
			bookNameBefore=readpagev2.bookNameInDetailsPage.getAttribute("text");
			bookNameBefore=homepagev2.convertCamelCase(bookNameBefore);
			test.log(LogStatus.INFO, "Book name is "+bookNameBefore);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Failed to fetch book name");
			homepagev2.takeScreenshot();
		}	
		String downloadBookLoc="//android.widget.TextView[@text='Download Book' or @text='Download book']";
		Utilities.verticalSwipe(driver);
		for(int count=0;count<=3;count++) {
			try {
				driver.findElement(By.xpath(downloadBookLoc)).click();
				test.log(LogStatus.INFO, "Clicked on Download Book");
				break;
			}
			catch(Exception e) {
				Utilities.verticalSwipe(driver);
				if(count==3) {
					test.log(LogStatus.FAIL, "Unable to find Download book link");
				}
			}
		}
	}
	else {
		test.log(LogStatus.INFO, "Unable to fetch name of the second item from Carousal");
	}

//Add audio
		 driver.navigate().back();
		 test.log(LogStatus.INFO, "Navigated back to Read Tab");
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 test.log(LogStatus.INFO, "Performed vertical swipe up");
		 driver.navigate().back();
		 if(Utilities.explicitWaitClickable(driver, homepagev2.listen_tab, 10)) {
			 homepagev2.listen_tab.click();
			 test.log(LogStatus.INFO, "Clicked on Listen tab");
			 String firstItemCarousalAudio="//android.widget.LinearLayout[@index='0' and @resource-id='com.viacom18.vootkids:id/ln_item_container']/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']";
			 String firstItemCarousalAudioClickLoc="//android.widget.LinearLayout[@index='0' and @resource-id='com.viacom18.vootkids:id/ln_item_container']";
			 try {
				WebElement firstItemAudio=driver.findElement(By.xpath(firstItemCarousalAudio));
				WebElement firstItemCarousalAudioClick=driver.findElement(By.xpath(firstItemCarousalAudioClickLoc));
				audioNameBefore=firstItemAudio.getAttribute("text");
				audioNameBefore=homepagev2.convertCamelCase(audioNameBefore);
				firstItemCarousalAudioClick.click();
				test.log(LogStatus.INFO, "Clicked on the Audio "+audioNameBefore+" from Listen Carousal");
				String downloadAudioLoc="//android.widget.TextView[@text='Download Audiobook']";
				boolean downloadAudioText=Utilities.verticalSwipeAndFind(driver,downloadAudioLoc);
				if(downloadAudioText==true) {
					test.log(LogStatus.INFO, "Scrolled to \"Download Audiobook\" text in Audio details page");
					if(Utilities.explicitWaitClickable(driver, listenpageV2.downloadAudioBookText, 3)) {
						listenpageV2.downloadAudioBookText.click();
						test.log(LogStatus.INFO,"Clicked on \"Download Audiobook\" text in Audio Details page");
					}
					else
						test.log(LogStatus.FAIL,"Failed to click on \"Download Audiobook\" text in Show details page"); 
				}
				else
						test.log(LogStatus.FAIL,"Failed to locate \"Download Audiobook\" text in Show details page"); 
				}
			 catch(Exception e) {
				test.log(LogStatus.FAIL,"Failed to click on first item under NEW AUDIOSTORIES tray"); 
			 }		
		 }
		 else {
			 test.log(LogStatus.FAIL, "Unable to click on Listen tab");
		 }
		 
//Verify the UI of Downloads
	driver.navigate().back();
	test.log(LogStatus.INFO, "Navigated back to Listen Tab");
	Utilities.verticalSwipeReverse(driver);
	Utilities.verticalSwipeReverse(driver);
	homepagev2.tabClick("My Stuff");
	if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 10)) {
		homepagev2.mystuff_tab.click();
		boolean tabPresence=false;
		for(int scroll=0;scroll<=30;scroll++){
			try{
				driver.findElement(By.xpath("//android.widget.TextView[@text='Downloads' or @text='DOWNLOADS' or @text='downloads']"));
				test.log(LogStatus.INFO, "Found Downloads tab"); 
				tabPresence=true;
				break;
			}
			catch(Exception e){
				Utilities.verticalSwipe(driver);
				if(scroll==30){
					test.log(LogStatus.FAIL, "Failed to find Downloads tab"); 
				}	
			}
		}
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
					 test.log(LogStatus.PASS, "Downloads tab is highlighted"); 
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Downloads tab is not highlighted");
				 err944++;
			 }
			 //Verify EDIT DOWNLOADS is displayed
			 Utilities.verticalSwipe(driver);
			 if(Utilities.explicitWaitVisible(driver, homepagev2.editDownloadsButton, 10)) {
				 test.log(LogStatus.PASS, "EDIT DOWNLOADS button is displayed"); 
			 }
			 else {
				 test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not displayed");
				 err944++;
			 }
			 //Verify if downloaded items are displayed
			 if(homepagev2.downloadedBooks.size()>0)
				 test.log(LogStatus.PASS, "Downloaded books are displayed"); 
			 else {
				 test.log(LogStatus.FAIL, "Downloaded books are displayed");
				 err944++;
			 }
			 //Scroll to find AVAILABLE SPACE text 
			 String availableSpaceLoc="//android.widget.TextView[@text='AVAILABLE SPACE' or @text='Available Space' or @text='available space']";
			 boolean avaliableSpace=Utilities.verticalSwipeAndFind(driver,availableSpaceLoc);
			 if(avaliableSpace==true) 
				 test.log(LogStatus.PASS, "AVAILABLE SPACE text is displayed"); 	 
			 else 
				 test.log(LogStatus.FAIL, "AVAILABLE SPACE text is not displayed");
			 //Verify if Seek bar is displayed
			 Utilities.verticalSwipe(driver);
			 try {
				 boolean bar=homepagev2.availableSpaceBar.isDisplayed();
				 if(bar==true)
					 test.log(LogStatus.PASS, "Available Space bar is displayed"); 
				 else {
					 test.log(LogStatus.FAIL, "Available Space bar is not displayed");
					 err944++;
				 }
			 }
			 catch(Exception e) {
				test.log(LogStatus.FAIL, "Available Space bar is not displayed");
				err944++;
			 }
			 //Verify if available space text is displayed
			 try {
				 boolean availableSpaceText=homepagev2.availableSpaceText.isDisplayed();
				 if(availableSpaceText==true) { 
					 test.log(LogStatus.PASS, "Text indicating space remaining is displayed"); 
				 }
				 else{
					 test.log(LogStatus.FAIL, "Text indicating space remaining is not displayed");
					 err944++;
				 }
			 }
			 catch(Exception e) {
				 test.log(LogStatus.FAIL, "Text indicating space remaining is not displayed");
				 err944++;
			 }
			 if(err944>0) {
				 test.log(LogStatus.FAIL, "Verify the UI of Downloads tab when there are available contents is FAIL");
				 if(!Utilities.setResultsKids("VK_944", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				 BasePageV2.takeScreenshot();
			 }
			 else {
				 test.log(LogStatus.PASS, "Verify the UI of Downloads tab when there are available contents is PASS");
				 if(!Utilities.setResultsKids("VK_944", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			 }	
//Verify the meta data
		   //Verify if the episode is present based on the name
			 String episodeNameAfterLoc="//android.widget.TextView[@text='"+episodeNameBefore+"']";
			 for(int tryfinding=0;tryfinding<=2;tryfinding++) {
				 try {
					 driver.findElement(By.xpath(episodeNameAfterLoc));
					 test.log(LogStatus.INFO, "The Episode is displayed under Downloads tab");
					 test.log(LogStatus.PASS, "The Meta data Episode Name is verified");
					 test.log(LogStatus.PASS, "Verify the meta data for episode cards is PASS");
					 if(!Utilities.setResultsKids("VK_951", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					 break;
				 }
				 catch(Exception e) {
					 if(tryfinding==2) {
						 test.log(LogStatus.FAIL, "The Episode is not present in Downloads tab");
						 test.log(LogStatus.PASS, "Verify the meta data for episode cards is FAIL");
						 if(!Utilities.setResultsKids("VK_951", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						 BasePageV2.takeScreenshot();
						 break;
					 }
				 } 
			 }
			  
			//Verify if the book is present based on the name
			String bookNameAfterLoc="//android.widget.TextView[@text=\""+bookNameBefore+"\"]";
			System.out.println(bookNameAfterLoc);
			 for(int tryfinding=0;tryfinding<=2;tryfinding++) {
				 try {
					 driver.findElement(By.xpath(bookNameAfterLoc));
						test.log(LogStatus.INFO, "The Book is displayed under Downloads tab");
						test.log(LogStatus.PASS, "The Meta data Book Name is verified");
						test.log(LogStatus.PASS, "Verify the meta data for Ebooks is Pass");
						if(!Utilities.setResultsKids("VK_952", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						break;
				 }
				 catch(Exception e) {
					 if(tryfinding==2) {
						test.log(LogStatus.FAIL, "The Book is not present in Downloads tab");
						test.log(LogStatus.FAIL, "Verify the meta data for Ebooks is Fail");
						if(!Utilities.setResultsKids("VK_952", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						BasePageV2.takeScreenshot();
						break;
					 }
				 } 
			 } 
			 
			//Verify if the audio is present based on the name
			String audioNameAfterLoc="//android.widget.TextView[@text='"+audioNameBefore+"']";
			 for(int tryfinding=0;tryfinding<=2;tryfinding++) {
				 try {
					 driver.findElement(By.xpath(audioNameAfterLoc));
						test.log(LogStatus.INFO, "The Audio is displayed under Downloads tab");
						test.log(LogStatus.PASS, "The Meta data Audio Name is verified");
						test.log(LogStatus.PASS, "Verify the metadata displayed for Audio Books is Pass");
						if(!Utilities.setResultsKids("VK_953", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						break;
				 }
				 catch(Exception e) {
					 if(tryfinding==2) {
						test.log(LogStatus.FAIL, "The Audio is not present in Downloads tab");
						test.log(LogStatus.FAIL, "Verify the metadata displayed for Audio Books is Fail");
						if(!Utilities.setResultsKids("VK_953", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						BasePageV2.takeScreenshot();
						break;
					 }
				 } 
			 }		 
		 }
		 else {
			test.log(LogStatus.FAIL, "Unable to locate Downloads tab");
			BasePageV2.takeScreenshot();
		 }
	}
	else {
		test.log(LogStatus.FAIL, "Unable to click on My Stuff tab");
	}
//delete downloads

	if(Utilities.explicitWaitVisible(driver, downloadsv2.editDownloadsMystuff, 5)) {		
		if (Utilities.explicitWaitClickable(driver, downloadsv2.editDownloadsMystuff, 5))
		{	
			downloadsv2.editDownloadsMystuff.click();
			if(Utilities.explicitWaitClickable(driver, downloadsv2.cancelDownloadIcon.get(0), 10)) {
				downloadsv2.cancelDownloadIcon.get(0).click();
				test.log(LogStatus.INFO, "Clicked on downloaded item 1");
				if(Utilities.explicitWaitVisible(driver, downloadsv2.delete1item, 10)) {
					test.log(LogStatus.INFO, "Count on Delete button is displayed as DELETE (1)");
				}
				else {
					test.log(LogStatus.FAIL, "Count on Delete button is NOT displayed as DELETE (1)");
					err1018++;
				}
				if(Utilities.explicitWaitClickable(driver, downloadsv2.deleteButton, 10)) {
					//Verify the Delete pop up UI
					downloadsv2.deleteButton.click();
					test.log(LogStatus.INFO, "Clicked on DELETE button after selecting single item");
					if(Utilities.explicitWaitVisible(driver, downloadsv2.deleteBodyDialogSingle, 10)) {
						test.log(LogStatus.INFO, "Correct Delete message is displayed when single item is selected");
					}
					else {
						test.log(LogStatus.FAIL, "Incorrect Delete message is displayed when single item is selected");
						err1019++;
					}	
					if(Utilities.explicitWaitClickable(driver, downloadsv2.deleteClose, 10)) {
						downloadsv2.deleteClose.click();
						test.log(LogStatus.INFO, "Clicked on Close of the dialog");
						
					}
					else {
						test.log(LogStatus.INFO, "Close button in the Delete dialog is not clickable");
					}
				}
				else {
					test.log(LogStatus.FAIL, "Unable to click on Delete button");
				}						
			}
			else 
				test.log(LogStatus.FAIL, "Unable to click on the first item");

			try {
				downloadsv2.cancelDownloadIcon.get(1).click();
				test.log(LogStatus.INFO, "Clicked on downloaded item 2");
				if(Utilities.explicitWaitVisible(driver, downloadsv2.delete2item, 10)) {
					test.log(LogStatus.INFO, "Count on Delete button is incremented to DELETE (2)");
				}
				else {
					test.log(LogStatus.FAIL, "Count on Delete button is NOT incremented to DELETE (2)");
					err1018++;
				}					
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to click on second downloaded item");
			}		
			if(err1018==0) {
				test.log(LogStatus.PASS, "Verify if the count on the delete button increments on selecting more than one content to delete is Pass");
				if(!Utilities.setResultsKids("VK_1018", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			else {
				test.log(LogStatus.FAIL, "Verify if the count on the delete button increments on selecting more than one content to delete is Fail");
				if(!Utilities.setResultsKids("VK_1018", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				BasePageV2.takeScreenshot();
			}
			//Verify 1022
			if(Utilities.explicitWaitClickable(driver, downloadsv2.deleteButton, 10)) {
				downloadsv2.deleteButton.click();
				test.log(LogStatus.INFO, "Clicked on DELETE button after selecting 2 items");
				//Click on NO
				if(Utilities.explicitWaitClickable(driver, downloadsv2.deleteClose, 10)) {
					downloadsv2.deleteClose.click();
					test.log(LogStatus.INFO, "Clicked on Cancel button");
					if(downloadsv2.cancelDownloadIcon.size()==3) {
						test.log(LogStatus.INFO, "No downloaded item is deleted. "+downloadsv2.cancelDownloadIcon.size()+" downloaded items are displayed and "+downloadsv2.cancelDownloadIconChecked.size()+" are selected");
					}
					else {
						test.log(LogStatus.FAIL, ""+downloadsv2.cancelDownloadIcon.size()+" downloaded items are displayed and "+downloadsv2.cancelDownloadIconChecked.size()+" are selected");
					}
					if(Utilities.explicitWaitVisible(driver, downloadsv2.delete2item, 10)) {
						test.log(LogStatus.INFO, "Count on Delete button is retained to DELETE (2)");						
					}
					else {
						test.log(LogStatus.FAIL, "Count on Delete button is NOT retained to DELETE (2)");
					}					
					
				}
				else {
					test.log(LogStatus.FAIL, "Cancel button is not clickable");
				}	
			}
			else {
				test.log(LogStatus.FAIL, "Unable to click on Delete button");
			}	
			try {
				downloadsv2.cancelDownloadIcon.get(1).click();
				test.log(LogStatus.INFO, "Clicked on downloaded item 2 again");
				if(Utilities.explicitWaitVisible(driver, downloadsv2.delete1item, 10)) {
					test.log(LogStatus.INFO, "Count on Delete button is decremented to DELETE (1)");
				}
				else {
					test.log(LogStatus.FAIL, "Count on Delete button is NOT decremented to DELETE (1)");
					err1019++;
				}					
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to click on second downloaded item");
			}
			try {
				downloadsv2.cancelDownloadIcon.get(0).click();
				test.log(LogStatus.INFO, "Clicked on downloaded item 1 again");
				if(Utilities.explicitWaitVisible(driver, downloadsv2.delete1item, 10)) {
					test.log(LogStatus.FAIL, "Delete button is displayed even though no items are selected");
					err1019++;
					BasePageV2.takeScreenshot();
				}
				else {
					test.log(LogStatus.INFO, "Delete button is not displayed when no items are selected");					
				}					
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to click on first downloaded item");
			}
			if(err1019==0) {		
				test.log(LogStatus.PASS, "Verify if the count on the delete button decrements on selecting more than one content to delete is Pass");
				if(!Utilities.setResultsKids("VK_1019", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			else {
				test.log(LogStatus.FAIL, "Verify if the count on the delete button decrements on selecting more than one content to delete is Fail");
				if(!Utilities.setResultsKids("VK_1019", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				BasePageV2.takeScreenshot();
			}
			
			for(int count=0;count<downloadsv2.cancelDownloadIcon.size();count++) {
				try {
					downloadsv2.cancelDownloadIcon.get(count).click();
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to click on downloaded item "+count);
				}		
			}
			
			if(Utilities.explicitWaitClickable(driver, downloadsv2.deleteButton, 10)) {
				downloadsv2.deleteButton.click();
				test.log(LogStatus.INFO, "Clicked on DELETE button after selecting all items");
				//Verify the Delete pop up UI
				if(Utilities.explicitWaitVisible(driver, downloadsv2.deleteBodyDialogMultiple, 10)) {
					test.log(LogStatus.INFO, "Correct Delete message is displayed when multiple items are selected");
				}
				else {
					test.log(LogStatus.FAIL, "Incorrect Delete message is displayed when multiple items are selected");
					err1020++;
				}
			}
			else {
				test.log(LogStatus.FAIL, "Unable to click on Delete button");
			}
			if(Utilities.explicitWaitVisible(driver, downloadsv2.deleteYes, 10)) {
				test.log(LogStatus.INFO, "YES button is displayed");
			}
			else {
				test.log(LogStatus.FAIL, "YES button is not displayed");
				err1020++;
			}
			if(Utilities.explicitWaitVisible(driver, downloadsv2.deleteClose, 10)) {
				test.log(LogStatus.INFO, "Cancel button is displayed");
			}
			else {
				test.log(LogStatus.FAIL, "Cancel button is not displayed");
				err1020++;
			}
			//Verification of 1021
			if(err1020==0) {
				test.log(LogStatus.PASS, "Verify if the delete download pop-up appears on tapping the delete button is Pass");
				if(!Utilities.setResultsKids("VK_1020", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			}
			else {
				test.log(LogStatus.FAIL, "Verify if the delete download pop-up appears on tapping the delete button is Fail");
				if(!Utilities.setResultsKids("VK_1020", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				BasePageV2.takeScreenshot();

			}
			if(Utilities.explicitWaitClickable(driver, downloadsv2.deleteYes, 10)) {
				try {
					downloadsv2.deleteYes.click();
					test.log(LogStatus.INFO, "Clicked on Yes button");
					if(downloadsv2.cancelDownloadIconUnchecked.size()==0) {
						test.log(LogStatus.INFO, "No downloaded items are present after clicking on Yes");
						test.log(LogStatus.PASS, "Verify the delete functionality on selecting yes in the delete downloads pop-up is Pass");
						if(!Utilities.setResultsKids("VK_1022", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
					}
					else {
						test.log(LogStatus.INFO, "Downloaded items are still present even after clicking on Yes");
						test.log(LogStatus.FAIL, "Verify the delete functionality on selecting yes in the delete downloads pop-up is Fail");
						if(!Utilities.setResultsKids("VK_1022", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
						BasePageV2.takeScreenshot();
					}
				}
				catch(Exception e){
					test.log(LogStatus.FAIL, "Failed to click on YES button");
				}				
			}
			else {
				test.log(LogStatus.FAIL, "YES button is not clickable");
			}
		}
		else {
			test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not clickable");
		}	
	}
	else {
		test.log(LogStatus.FAIL, "There are no downloads present");
	}
}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
