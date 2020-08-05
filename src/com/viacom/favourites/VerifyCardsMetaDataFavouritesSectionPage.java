package com.viacom.favourites;

import java.time.Duration;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.FavouritesPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;

//Author: Suresh
public class VerifyCardsMetaDataFavouritesSectionPage extends BaseTestV2 {
	
	String testName = "VerifyCardsMetaDataFavouritesSectionPage";
	
	String FirstEpisode = "" ,SecondEpisode = "",end="",favouritesXpath="",firstBookTile="",secondBookTile="";
	boolean presenceTray;
	Activity activity;
	@Test(dataProvider = "getData")
	public void verifyCardsMetaDataFavouritesSectionPage(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("VerifyCardsMetaDataFavouritesSectionPage");
		test.log(LogStatus.INFO, "Starting the test to Favourites Section Page: " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int VK_226 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_226,"Verify Episode card metadata:");
		
		int VK_227 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_227,"Verify Audio Book Card metadata:");
		
		int VK_228 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_228,"Verify Book Card metadata:");
		
		int VK_231 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_231,"Verify the Audio book card functionality when tapping on the audio book card.");
		
		int VK_232 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_232,"Verify the book card functionality when tapping on the book card:");
		
		int VK_234 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_234,"Verify the functionality of edit option in Favorites landing page:");
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
   
        SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
        HomePageV2 homepagev2 = new HomePageV2(driver, test);
        WatchPageV2 watchpagev2 = new WatchPageV2(driver, test);
        KidsPlayerPageV2 playerpagev2 = new KidsPlayerPageV2(driver, test);
        BooksPageV2 bookspagev2 = new BooksPageV2(driver, test);
        DownloadsPageV2 downloadsPageV2 = new DownloadsPageV2(driver, test);
        ListenPageV2 listenPageV2 = new ListenPageV2(driver, test);
        WatchPageV2 watchpageV2 = new WatchPageV2(driver, test);
        ShowsPageV2 showspageV2 = new ShowsPageV2(driver, test);
        FavouritesPageV2 favouritesPageV2 = new FavouritesPageV2(driver, test);
    	Thread.sleep(10000);   
    	
    	
    	homepagev2.login(data.get("Email"),data.get("Password"));
  
    	String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
		
		Thread.sleep(2000);
		boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
		if(presenceTray==true) {
			test.log(LogStatus.INFO, "Favourites tray is displayed");
			Utilities.verticalSwipe(driver);
			//Click on Favourites tray
			try {
				driver.findElement(By.xpath(favouritesXpath)).click();
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to click on Favourites tray");
				BasePageV2.takeScreenshot();
			}
    	
    	if(Utilities.explicitWaitClickable(driver, favouritesPageV2.editLinkinEditpage, 10)) {
    		favouritesPageV2.editLinkinEditpage.click();
    	}else test.log(LogStatus.FAIL, "not able to found EDIT link in FAVOURITES Main page");
    	
		}
    	
    	try {
    		homepagev2.tabClick("watch");
    		test.log(LogStatus.INFO, "Clicked on Watch tab Successfully");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Not able to clicked on Watch tab");
		}

    		String xpath2="//android.widget.TextView[@text='ALL KIDS CHARACTERS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
    		Utilities.verticalSwipe(driver, xpath2);
      		test.log(LogStatus.INFO, "Navigating to any of show under ALL KIDS CHARACTERS Section");
    		if(Utilities.explicitWaitClickable(driver, watchpagev2.allCharactersFirstShow, 30))
    		watchpagev2.allCharactersFirstShow.click();
    		else
    		BasePageV2.reportFail("Not able to click show under ALL KIDS CHARACTERS section");
    		
    		//Scroll to  episodes section
    		String episode="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']";
    		Utilities.verticalSwipe(driver, episode);
    		Thread.sleep(1000);
    		 //Check for Episodes section
    		if(Utilities.explicitWaitVisible(driver, showspageV2.showDetailPageEpisodesSection , 30))
    		{
    			test.log(LogStatus.INFO, "Episodes section is Present");
    			//Scroll to  episodes section video
    			String episodevideo="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
    			Utilities.verticalSwipe(driver, episodevideo);
    			Thread.sleep(1000);
    			if(Utilities.explicitWaitVisible(driver, showspageV2.showDetailPageEpisodesSectionFirstVideo , 30))
    			{		
    				test.log(LogStatus.INFO, "Videos are displayed under Episodes section");
    				showspageV2.showDetailPageEpisodesSectionFirstVideo.click();
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
    				  //Click on Favorites
    				  
    				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10)) {
    					  FirstEpisode = playerpagev2.playerSubTitle.getText().toString().trim();
    					  test.log(LogStatus.INFO, "First Episode Sub Title is : " + FirstEpisode);
    				  }else test.log(LogStatus.FAIL, "Not able to find the Sub title of First Episode");
    				  
    				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoriteButton , 20))
    					{
    					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerUnFavoritedButton , 5))
    						{
    						  test.log(LogStatus.INFO, "Tapping on Favorite Icon");
    						  playerpagev2.playerUnFavoritedButton.click();
    						  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoritedButton , 5))
    						  {
    							  test.log(LogStatus.INFO, "Icon got filled");
    							
    						  }
    						  else
    							  test.log(LogStatus.FAIL,"Icon not getting filled after favoriting it");
    						  
    						}
    					    else if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoritedButton , 5))
    					    {
    						  test.log(LogStatus.INFO, "Content is already favorited and icon is filled");						
    					    }
    						
    						
    					}
    					else
    						test.log(LogStatus.FAIL, "Favorite button is not present in the player"); 
    	 
    	              driver.pressKeyCode(AndroidKeyCode.BACK);
    	 
           // Episodes Second Video favouriting
    	              
//    	              if(Utilities.explicitWaitVisible(driver, showspageV2.showDetailPageEpisodesSectionSecondVideo , 30))
//    	    			{		
//    	    				test.log(LogStatus.INFO, "Videos are displayed under Episodes section");
//    	    				showspageV2.showDetailPageEpisodesSectionSecondVideo.click();
//    	    			}
//    	    			else
//    	    				BasePageV2.reportFail("Not able to play content under Episodes section");
    	              
    	              try {
    	            	  showspageV2.showDetailPageEpisodesSectionVideos.get(4).click();
    	            	  test.log(LogStatus.INFO, "Clicked Second Episodes Section Show");
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Not able to clicked Second card in Episodes Section");
					}
    	              
    	              
    	        	  //Check for progress bar
    				  homepagev2.verifyAndProgressBar();
    				
    				  //Pause the video
    				  playerpagev2.pauseVideo();
    				  
    				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerSubTitle, 10)) {
    					  SecondEpisode = playerpagev2.playerSubTitle.getText().toString().trim();
    					  test.log(LogStatus.INFO, "Second Episode Sub Title is : " + SecondEpisode);
    				  }else test.log(LogStatus.FAIL, "Not able to find the Sub title of Second Episode");
    				  
    				  
    				  //Click on Favorites
    				  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoriteButton , 20))
    					{
    					  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerUnFavoritedButton , 5))
    						{
    						  test.log(LogStatus.INFO, "Tapping on Favorite Icon");
    						  playerpagev2.playerUnFavoritedButton.click();
    						  if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoritedButton , 5))
    						  {
    							  test.log(LogStatus.INFO, "Icon got filled");
    							
    						  }
    						  else
    							  test.log(LogStatus.FAIL,"Icon not getting filled after favoriting it");
    						  
    						}
    					    else if(Utilities.explicitWaitVisible(driver, playerpagev2.playerFavoritedButton , 5))
    					    {
    						  test.log(LogStatus.INFO, "Content is already favorited and icon is filled");						
    					    }
    						
    						
    					}
    					else
    						test.log(LogStatus.FAIL, "Favorite button is not present in the player"); 
    	              
    	 
    	              //putting the App in background
    				  driver.runAppInBackground(Duration.ofSeconds(3));
    					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
    					 driver.currentActivity();
    					 
    				 // closing App 	 
    					driver.closeApp();
    				  
    				 //Launch App 
    					Activity activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
    					driver.startActivity(activity);
    					
    					
    			  //Verify Episode card metadata:
    			    	 favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
    					
    					Thread.sleep(2000);
    					 presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
    					if(presenceTray==true) {
    						test.log(LogStatus.INFO, "Favourites tray is displayed");
    						Utilities.verticalSwipe(driver);
    						
    						//Click on Favourites tray
    						try {
    							driver.findElement(By.xpath(favouritesXpath)).click();
    						}
    						catch(Exception e) {
    							test.log(LogStatus.FAIL, "Unable to click on Favourites tray");
    							BasePageV2.takeScreenshot();
    						}
    					}
    					
    					try {
    						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ FirstEpisode +"']"));
    						test.log(LogStatus.INFO, "Found the First Episode show in favourites Section Main page");
    						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ SecondEpisode +"']"));
    						test.log(LogStatus.INFO, "Found the Second Episode show in favourites Section Main page");
    						test.log(LogStatus.PASS,"Verify Episode card metadata:");
        					BasePageV2.smokeresults("", VK_226, "PASS");
						} catch (Exception e) {
							test.log(LogStatus.FAIL,"Verify Episode card metadata:");
							BasePageV2.smokeresults("", VK_226, "FAIL");
						}
    					
    					driver.pressKeyCode(AndroidKeyCode.BACK);
    					
    					 end = "//android.widget.TextView[@text='JUST FOR YOU']";
    					Utilities.verticalSwipeDown(driver, end);
    					
    	//Verify Audio Book Card metadata:
    					
    					try {
    			    		homepagev2.tabClick("Listen");
    			    		test.log(LogStatus.INFO, "Clicked on Watch tab Successfully");
    					} catch (Exception e) {
    						test.log(LogStatus.FAIL, "Not able to clicked on Watch tab");
    					}

    					 test.log(LogStatus.INFO,"Scrolling to any of the Audio Tray");
    					 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
    					 Utilities.verticalSwipe(driver, tray);
    					
    					 String audiostorytitle="";
    					 if(Utilities.explicitWaitVisible(driver, listenPageV2.firstTray, 20))
    					 {

    						 if(Utilities.explicitWaitClickable(driver, listenPageV2.firstTrayAudioStory, 20))
    						 {
    							 if(Utilities.explicitWaitVisible(driver, listenPageV2.firstTrayAudioStoryTitle, 20))
    							 {
    								 audiostorytitle=listenPageV2.firstTrayAudioStoryTitle.getText();
    							 }
    							 else
    							 test.log(LogStatus.FAIL, "Audio story title is missing");
    							 
    							 listenPageV2.firstTrayAudioStory.click();
    						 }
    						 else
    							 BasePageV2.reportFail("No contents in the tray / Content not clickable");
    					 }
    					 else
    						 BasePageV2.reportFail("Failed to find the tray");
    					 
    					 
    					 test.log(LogStatus.INFO, "Navigating to the First audio card Detail page - "+audiostorytitle);
    					 
    					 //favouriting the audio card
    					  if(Utilities.explicitWaitVisible(driver, listenPageV2.favAudioDetails , 10))
      					{
      					  if(Utilities.explicitWaitVisible(driver, listenPageV2.audioUnFavoritedButton , 5))
      						{
      						  test.log(LogStatus.INFO, "Tapping on Favorite Icon");
      						   listenPageV2.audioUnFavoritedButton.click();
      						  if(Utilities.explicitWaitVisible(driver, listenPageV2.audioFavoritedButton , 5))
      						  {
      							  test.log(LogStatus.INFO, "Icon got filled");
      							
      						  }
      						  else
      							  test.log(LogStatus.FAIL,"Icon not getting filled after favoriting it");
      						  
      						}
      					    else if(Utilities.explicitWaitVisible(driver, listenPageV2.audioFavoritedButton , 5))
      					    {
      						  test.log(LogStatus.INFO, "Content is already favorited and icon is filled");						
      					    }
      						
      						
      					}
      					else
      						test.log(LogStatus.FAIL, "Favorite button is not present in the Audio card"); 
    					  
    					
    					 driver.pressKeyCode(AndroidKeyCode.BACK);
    					 
    					 String audiostorytitle1="";
    					 if(Utilities.explicitWaitVisible(driver, listenPageV2.firstTray, 20))
    					 {
    						 String audiostory="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView";
    						 Utilities.verticalSwipe(driver, audiostory);
    						 if(Utilities.explicitWaitClickable(driver, listenPageV2.firstTraySecondAudioStory, 20))
    						 {
    							 if(Utilities.explicitWaitVisible(driver, listenPageV2.firstTraySecondAudioStoryTitle, 20))
    							 {
    								 audiostorytitle1=listenPageV2.firstTraySecondAudioStoryTitle.getText();
    							 }
    							 else
    							 test.log(LogStatus.FAIL, "Audio story title is missing");
    							 
    							 listenPageV2.firstTraySecondAudioStory.click();
    						 }
    						 else
    							 BasePageV2.reportFail("No contents in the tray / Content not clickable");
    					 }
    					 else
    						 BasePageV2.reportFail("Failed to find the tray");
    					 
    					 
    					 test.log(LogStatus.INFO, "Navigating to the Second audio card Detail page - "+audiostorytitle1);
    					 
    					 //favouriting the Second Card
    					 
    					 if(Utilities.explicitWaitVisible(driver, listenPageV2.favAudioDetails , 10))
       					{
       					  if(Utilities.explicitWaitVisible(driver, listenPageV2.audioUnFavoritedButton , 5))
       						{
       						  test.log(LogStatus.INFO, "Tapping on Favorite Icon");
       						listenPageV2.audioUnFavoritedButton.click();
       						  if(Utilities.explicitWaitVisible(driver, listenPageV2.audioFavoritedButton , 5))
       						  {
       							  test.log(LogStatus.INFO, "Icon got filled");
       							
       						  }
       						  else
       							  test.log(LogStatus.FAIL,"Icon not getting filled after favoriting it");
       						  
       						}
       					    else if(Utilities.explicitWaitVisible(driver, listenPageV2.audioFavoritedButton , 5))
       					    {
       						  test.log(LogStatus.INFO, "Content is already favorited and icon is filled");						
       					    }
       						
       						
       					}
       					else
       						test.log(LogStatus.FAIL, "Favorite button is not present in the Audio card"); 
    					 
    					 driver.pressKeyCode(AndroidKeyCode.BACK);
    					 
    					 Utilities.verticalSwipeDown(driver);
    					 

       	              //putting the App in background
       				  driver.runAppInBackground(Duration.ofSeconds(3));
       					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
       					 driver.currentActivity();
       					 
       				 // closing App 	 
       					driver.closeApp();
       				  
       				 //Launch App 
       					 activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
       					driver.startActivity(activity);
       					
    					 
    					 
    					 favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
     					
     					Thread.sleep(2000);
     					 presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
     					if(presenceTray==true) {
     						test.log(LogStatus.INFO, "Favourites tray is displayed");
     						Utilities.verticalSwipe(driver);
     						
     						//Click on Favourites tray
     						try {
     							driver.findElement(By.xpath(favouritesXpath)).click();
     						}
     						catch(Exception e) {
     							test.log(LogStatus.FAIL, "Unable to click on Favourites tray");
     							BasePageV2.takeScreenshot();
     						}
     					}
     					
     					
     					//click Audiobooks Tab in favourites Main page
     					if(Utilities.explicitWaitVisible(driver, favouritesPageV2.favEditPageAudiobooks, 10)) {
     						favouritesPageV2.favEditPageAudiobooks.click();
     						test.log(LogStatus.INFO, "Clicked Audiobooks tab in favourites main page");
     					}else test.log(LogStatus.FAIL, "Unable to Click Audiobooks tab in favourites Main page");
     					
     					
     					try {
//     						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ audiostorytitle +"']"));
     						test.log(LogStatus.INFO, "Found the First Episode show in favourites Section Main page");
     						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ audiostorytitle1 +"']"));
     						test.log(LogStatus.INFO, "Found the Second Episode show in favourites Section Main page");
     						test.log(LogStatus.PASS,"Verify Episode card metadata:");
         					BasePageV2.smokeresults("", VK_227, "PASS");
 						} catch (Exception e) {
 							test.log(LogStatus.FAIL,"Verify Episode card metadata:");
 							BasePageV2.smokeresults("", VK_227, "FAIL");
 						}
     					
     					driver.pressKeyCode(AndroidKeyCode.BACK);
     					
     					 end = "//android.widget.TextView[@text='JUST FOR YOU']";
     					Utilities.verticalSwipeDown(driver, end);
    					 
    					 
    			//Verify Book Card metadata:
     					
     					try {
     			    		homepagev2.tabClick("Read");
     			    		test.log(LogStatus.INFO, "Clicked on Read tab Successfully");
     					} catch (Exception e) {
     						test.log(LogStatus.FAIL, "Not able to clicked on Read tab");
     					}
     					
     					
     					String xpath="//android.widget.TextView[@text='NEW BOOKS']";
//     					String xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
     					Utilities.verticalSwipe(driver, xpath);
     					Utilities.verticalSwipe(driver);
     					Thread.sleep(2000);	
     					test.log(LogStatus.INFO, "Navigating to any of book under New Books Section");
     					
     					
     					
     					
     					
     					if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTrayFirstBook, 30)) {
     					bookspagev2.firstTrayFirstBook.click();
     					test.log(LogStatus.INFO, "Clicked on First Tray First Book card");
     					}else
     					BasePageV2.reportFail("Not able to click book under New Books section");
     					
     			        Thread.sleep(10000);
     					//cHECK FOR bOOK title
     			        
     			        WebElement text = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']"));
     			        String textBook = text.getText();
     			        test.log(LogStatus.INFO, "The First Book Text is : " + textBook);
     					
     						if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookTitle , 10))
     						{
     							if(!bookspagev2.bookDetailPageBookTitle.getText().isEmpty() && bookspagev2.bookDetailPageBookTitle.getText()!=null) {
     							test.log(LogStatus.INFO, "Book Title is displayed");
     							firstBookTile = bookspagev2.bookDetailPageBookTitle.getText().toString().trim();
     							test.log(LogStatus.INFO, "First Card Book is : " + firstBookTile);
     							}else test.log(LogStatus.FAIL, "First Book Title is missing");
     						}
     						else
     						BasePageV2.reportFail("Book Title is not displayed in Show Detail page");
     					
     					
     					
     					  //Click on Favorites
      				  if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageFavoriteButton , 20))
      					{
      					  if(Utilities.explicitWaitVisible(driver, bookspagev2.bookUnFavoritedButton , 5))
      						{
      						  test.log(LogStatus.INFO, "Tapping on Favorite Icon");
      						bookspagev2.bookUnFavoritedButton.click();
      						  if(Utilities.explicitWaitVisible(driver, bookspagev2.bookFavoritedButton , 5))
      						  {
      							  test.log(LogStatus.INFO, "Icon got filled");
      							
      						  }
      						  else
      							  test.log(LogStatus.FAIL,"Icon not getting filled after favoriting it");
      						  
      						}
      					    else if(Utilities.explicitWaitVisible(driver, bookspagev2.bookFavoritedButton , 5))
      					    {
      						  test.log(LogStatus.INFO, "Content is already favorited and icon is filled");						
      					    }
      						
      						
      					}
      					else
      						test.log(LogStatus.FAIL, "Favorite button is not present in the Book card"); 
     					
     					
      				  driver.pressKeyCode(AndroidKeyCode.BACK);
      				  
      				  
      				  //check on second book favourites 
     			               
      				  
      				if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTraySecondBook, 30)) {
     					bookspagev2.firstTraySecondBook.click();
      				    test.log(LogStatus.INFO, "Clicked on First Tray Second Book card");
      				}else
     					BasePageV2.reportFail("Not able to click book under New Books section");
      				
      				Thread.sleep(10000);
      				  
      			//cHECK FOR bOOK title
      				   
      		      WebElement Secondtext = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']"));
			        String textBookSecond = Secondtext.getText();
			        test.log(LogStatus.INFO, "The Second Book Text is : " + textBookSecond);
      				
      				
 					
						if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageBookTitle , 30))
						{
							if(!bookspagev2.bookDetailPageBookTitle.getText().isEmpty() && bookspagev2.bookDetailPageBookTitle.getText()!=null) {
							test.log(LogStatus.INFO, "Book Title is displayed");
							secondBookTile = bookspagev2.bookDetailPageBookTitle.getText().toString().trim();
							test.log(LogStatus.INFO, "Second Card Book is : " + secondBookTile);
							}else test.log(LogStatus.FAIL, "Second Book Title is missing");
						}
						else
						BasePageV2.reportFail("Book Title is not displayed in Show Detail page");
     					
     					
						 //Click on Favorites
	      				  if(Utilities.explicitWaitVisible(driver, bookspagev2.bookDetailPageFavoriteButton , 20))
	      					{
	      					  if(Utilities.explicitWaitVisible(driver, bookspagev2.bookUnFavoritedButton , 5))
	      						{
	      						  test.log(LogStatus.INFO, "Tapping on Favorite Icon");
	      						bookspagev2.bookUnFavoritedButton.click();
	      						  if(Utilities.explicitWaitVisible(driver, bookspagev2.bookFavoritedButton , 5))
	      						  {
	      							  test.log(LogStatus.INFO, "Icon got filled");
	      							
	      						  }
	      						  else
	      							  test.log(LogStatus.FAIL,"Icon not getting filled after favoriting it");
	      						  
	      						}
	      					    else if(Utilities.explicitWaitVisible(driver, bookspagev2.bookFavoritedButton , 5))
	      					    {
	      						  test.log(LogStatus.INFO, "Content is already favorited and icon is filled");						
	      					    }
	      						
	      						
	      					}
	      					else
	      						test.log(LogStatus.FAIL, "Favorite button is not present in the Book card");
     					
     					
	      				  driver.pressKeyCode(AndroidKeyCode.BACK);
     					

	       	              //putting the App in background
	       				  driver.runAppInBackground(Duration.ofSeconds(3));
	       					 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
	       					 driver.currentActivity();
	       					 
	       				 // closing App 	 
	       					driver.closeApp();
	       				  
	       				 //Launch App 
	       					 activity = new Activity("com.viacom18.vootkids", "com.tv.vootkids.ui.home.VKHomeActivity");
	       					driver.startActivity(activity);
	       					
     					
	       					favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
	     					
	     					Thread.sleep(2000);
	     					 presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
	     					if(presenceTray==true) {
	     						test.log(LogStatus.INFO, "Favourites tray is displayed");
	     						Utilities.verticalSwipe(driver);
	     						
	     						//Click on Favourites tray
	     						try {
	     							driver.findElement(By.xpath(favouritesXpath)).click();
	     						}
	     						catch(Exception e) {
	     							test.log(LogStatus.FAIL, "Unable to click on Favourites tray");
	     							BasePageV2.takeScreenshot();
	     						}
	     					}
	     					
	     					//click Book Tab in favourites Main page
	     					if(Utilities.explicitWaitVisible(driver, favouritesPageV2.favEditPageBooks, 10)) {
	     						favouritesPageV2.favEditPageBooks.click();
	     						test.log(LogStatus.INFO, "Clicked book tab in favourites main page");
	     					}else test.log(LogStatus.FAIL, "Unable to Click Book tab in favourites Main page");
	     					
	     					
	     					try {
	     						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ firstBookTile +"']"));
	     						test.log(LogStatus.INFO, "Found the First Episode show in favourites Section Main page");
	     						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ secondBookTile +"']"));
	     						test.log(LogStatus.INFO, "Found the Second Episode show in favourites Section Main page");
	     						test.log(LogStatus.PASS,"Verify Book Card metadata:");
	         					BasePageV2.smokeresults("", VK_228, "PASS");
	 						} catch (Exception e) {
	 							test.log(LogStatus.FAIL,"Verify Book Card metadata:");
	 							BasePageV2.smokeresults("", VK_228, "FAIL");
	 						}
	     					
	     					
     					
                  //  	Verify the book card functionality when tapping on the book card:
     					
     					try {
     						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ firstBookTile +"']")).click();
						} catch (Exception e) {
							BasePageV2.reportFail("not able to Click Book card in favourites Section Page");
						}
     					
     					
     					if(Utilities.explicitWaitVisible(driver, listenPageV2.favAudioDetails, 10)) {
     					 test.log(LogStatus.PASS,"Verify the book card functionality when tapping on the book card:");
     					 BasePageV2.smokeresults("", VK_232, "PASS");
     					}else {
     						test.log(LogStatus.FAIL,"Verify the book card functionality when tapping on the book card:");
        					 BasePageV2.smokeresults("", VK_232, "FAIL");
     					}
     					
     					
     				driver.pressKeyCode(AndroidKeyCode.BACK);
     					
     					
                   //   Verify the Audio book card functionality when tapping on the audio book card. --			
     					
     					
     				//click Audiobooks Tab in favourites Main page
 					if(Utilities.explicitWaitVisible(driver, favouritesPageV2.favEditPageAudiobooks, 10)) {
 						favouritesPageV2.favEditPageAudiobooks.click();
 						test.log(LogStatus.INFO, "Clicked Audiobooks tab in favourites main page");
 					}else test.log(LogStatus.FAIL, "Unable to Click Audiobooks tab in favourites Main page");
 					
 					
 					try {
 						driver.findElement(By.xpath("//android.widget.TextView[@text='"+ audiostorytitle +"']")).click();
 						test.log(LogStatus.INFO, "Found the First Audio card in favourites Section Main page and clikced");
     					
 					}catch (Exception e) {
						BasePageV2.reportFail("Not able to clikced Audio card in favourites Section page");
					}
     					
 					if(Utilities.explicitWaitVisible(driver, listenPageV2.favAudioDetails, 10)) {
    					 test.log(LogStatus.PASS,"Verify the Audio book card functionality when tapping on the audio book card.");
    					 BasePageV2.smokeresults("", VK_231, "PASS");
    					}else {
    						test.log(LogStatus.FAIL,"Verify the Audio book card functionality when tapping on the audio book card.");
       					 BasePageV2.smokeresults("", VK_231, "FAIL");
    					}
     					
 					driver.pressKeyCode(AndroidKeyCode.BACK);
 					
                  // 	Verify the functionality of edit option in Favorites landing page:
 					
 					   
 					
 					
 					if(Utilities.explicitWaitClickable(driver, favouritesPageV2.editLinkinEditpage, 10)) {
 						favouritesPageV2.editLinkinEditpage.click();
 						test.log(LogStatus.INFO, "Clikced EDIT link in Favourites section page");
 					}else {
 						test.log(LogStatus.FAIL, "Not able to click 'EDIT' Link in favourites page");
 					}
     					
 					if(Utilities.explicitWaitVisible(driver, favouritesPageV2.favIConStar, 10)) {
 						test.log(LogStatus.PASS, "Verify the functionality of edit option in Favorites landing page:");
 						BasePageV2.smokeresults("", VK_234, "PASS");
 					}else {
 						test.log(LogStatus.FAIL, "Verify the functionality of edit option in Favorites landing page:");
 						BasePageV2.smokeresults("", VK_234, "PASS");
 						BasePageV2.takeScreenshot();
 					}
     				
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}



}
