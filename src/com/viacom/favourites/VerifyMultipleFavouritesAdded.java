package com.viacom.favourites;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
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
//Author Tanisha
public class VerifyMultipleFavouritesAdded extends BaseTestV2{
	
	String testName = "VerifyMultipleFavouritesAdded";
	@Test(dataProvider = "getData")
	public void VerifyMultipleFavouritesAdded(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyMultipleFavouritesAdded");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls199 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno199=xls199.getRowCount("Smoke_Results")+1;
		xls199.setCellData("Smoke_Results", "Testcase Name",rowno199,"Verify the click funtionality on tapping on favourite icon in audio book detail page");
				
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerpagev2=new KidsPlayerPageV2(driver,test);
		 
		 int err1750=0;
		 homepagev2.login(data.get("Email"),data.get("Password"));
		 //Login module to be added
		 //homepagev2.signup();
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
			 //System.out.println(driver.getPageSource());
		 }
		
		//Verification of 199
		//Click on Listen tab
		 homepagev2.tabClick("Listen");
		 test.log(LogStatus.INFO, "Clicked on Listen tab");
		 Thread.sleep(2000);
		 Utilities.verticalSwipe(driver);
		 String firstBookName="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
		 String firstBookAuthor="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";
		 String secondBookName="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@index='1']//android.view.ViewGroup[@index='1']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']";
		 String secondBookAuthor="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@index='1']//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']";		 
		 boolean bookPresence=Utilities.verticalSwipeAndFind(driver,firstBookName);
		 String firstbookname="";
		 String firstbookauthor="";
		 String secondbookname="";
		 String secondbookauthor="";
		 try {
			 firstbookname=driver.findElementByXPath(firstBookName).getAttribute("text");
		 }catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to get name of the first book");
		 }
		 try {
			 firstbookauthor=driver.findElementByXPath(firstBookAuthor).getAttribute("text");
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to get author of the first book");
		 }
		 try {
			 secondbookname=driver.findElementByXPath(secondBookName).getAttribute("text");
		 }catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to get name of the second book");
		 }
		 try {
			 secondbookauthor=driver.findElementByXPath(secondBookAuthor).getAttribute("text");
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to get author of the second book");
		 }
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.newAudioTextOne, 5)) {
			 listenpagev2.newAudioTextOne.click(); 
			 test.log(LogStatus.INFO, "Clicked on the first Audio Story from the NEW AUDIO STORIES tray: "+firstbookname+" by "+firstbookauthor);  	
			 //Verify the presence of Favorite icon
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.favIconAudioDetails, 3)) {
				 test.log(LogStatus.INFO,"Favourite icon is visible in the audio book detail page"); 
				 if(Utilities.explicitWaitClickable(driver, listenpagev2.favIconAudioDetails, 3)) {
					 listenpagev2.favIconAudioDetails.click();
					 test.log(LogStatus.INFO,"Marked as Favourite");
				 }
				 else {
					 test.log(LogStatus.FAIL,"Favourite icon is not clickable in the audio book detail page"); 
					 err1750++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL,"Favourite icon is not visible in the audio book detail page");
				 basepagev2.takeScreenshot();
				 err1750++;
			 }	  
		}
		else {
			test.log(LogStatus.FAIL,"Unable to click on the first Audio Story from the NEW AUDIOSTORIES tray"); 
			err1750++;
		}
		driver.navigate().back();
		 if(Utilities.explicitWaitClickable(driver, listenpagev2.newAudioTextTwo, 5)) {
			 listenpagev2.newAudioTextTwo.click(); 
			 test.log(LogStatus.INFO, "Clicked on the second Audio Story from the NEW AUDIO STORIES tray: "+secondbookname+" by "+secondbookauthor);  	
			 //Verify the presence of Favorite icon
			 if(Utilities.explicitWaitVisible(driver, listenpagev2.favIconAudioDetails, 3)) {
				 test.log(LogStatus.INFO,"Favourite icon is visible in the audio book detail page"); 
				 if(Utilities.explicitWaitClickable(driver, listenpagev2.favIconAudioDetails, 3)) {
					 listenpagev2.favIconAudioDetails.click();
					 test.log(LogStatus.INFO,"Marked as Favourite");
				 }
				 else {
					 test.log(LogStatus.FAIL,"Favourite icon is not clickable in the audio book detail page"); 
					 err1750++;
				 }
			 }
			 else {
				 test.log(LogStatus.FAIL,"Favourite icon is not visible in the audio book detail page");
				 basepagev2.takeScreenshot();
				 err1750++;
			 }	  
		}
		else {
			test.log(LogStatus.FAIL,"Unable to click on the first Audio Story from the NEW AUDIOSTORIES tray"); 
			err1750++;
		}	
		//Add two episodes
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("Watch");
		//Click on the first carousal item
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
			 String nameEpisode=watchpagev2.firstItemInCarousalName.getAttribute("text");
			 String descEpisode=watchpagev2.firstItemInCarousalDesc.getAttribute("text");
			 watchpagev2.firstItemInCarousal.click();
			 if(Utilities.explicitWaitVisible(driver, watchpagev2.watchFirstItemPlayer, 3)) {
				 test.log(LogStatus.INFO, "Player is displayed on tapping first item of Carousal in Watch tab");
				 //Tap on player until controls are visible
				 for(int time=0;time<=10;time++) {
					 try {
						 watchpagev2.watchFirstItemPlayer.click();
						 Thread.sleep(2000);
						 homepagev2.verifyAndProgressBar();
						 kidsplayerpagev2.pauseVideo();
						 if(Utilities.explicitWaitClickable(driver,watchpagev2.watchFirstItemPlayerFav,3)) {
							 test.log(LogStatus.INFO, "Favourites icon is present in player");
							 watchpagev2.watchFirstItemPlayerFav.click();
							 test.log(LogStatus.INFO, "Clicked on Favourite icon");
							 break;
						 }
						 else
							 Thread.sleep(3000);
					 }
					 catch(Exception e) {
						 Thread.sleep(3000);
						 if(time==10) {
							 test.log(LogStatus.FAIL, "Player is not clickable"); 
							 errCount++;
						 }			 
					 }					 
				 }
			 }
		 }
		
		 
/*					 //Scroll to Favourites
					 driver.navigate().back();
					 Utilities.verticalSwipeReverse(driver);
					 homepagev2.mystuff_tab.click();
					 Thread.sleep(2000);
					 String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
					 boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
					 if(presenceTray==true) {
						test.log(LogStatus.INFO, "Favourites tray is displayed");	
						String uiFavBookLocator="//android.widget.TextView[@text='"+name+"']";
						String uiFavAuthorLocator="//android.widget.TextView[@text='"+name+"']/parent::android.view.ViewGroup//android.widget.TextView[contains(@text,'"+author+"')]";
						String uiFavIconLocator="//android.widget.TextView[@text='"+name+"']/parent::android.view.ViewGroup//android.widget.ImageView[@index='5']";
						for(int scroll=0;scroll<=3;scroll++) {
							try {
								driver.findElement(By.xpath(uiFavBookLocator));
								test.log(LogStatus.PASS, "199: Verify the click funtionality on tapping on favourite icon in audio book detail page is PASS");
								homepagev2.smokeresults("199: Verify the click funtionality on tapping on favourite icon in audio book detail page",rowno199, "PASS");
								//Verification of 209
								test.log(LogStatus.INFO, "Starting 209: Verify the metadata displayed for the favorited Audio book card");		
								try {
									String uiBookName=driver.findElement(By.xpath(uiFavBookLocator)).getAttribute("text");
									test.log(LogStatus.INFO, "Audio Book Title is verified "+uiBookName);
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Audio Book Title not displayed");
									errCount++;
								}
								try {
									String uiAuthorName=driver.findElement(By.xpath(uiFavAuthorLocator)).getAttribute("text");
									test.log(LogStatus.INFO, "Audio Book Author is verified "+uiAuthorName);
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Audio Book Author not displayed");
									errCount++;
								}
								try {
									driver.findElement(By.xpath(uiFavIconLocator));
									test.log(LogStatus.INFO, "Audio Book Icon is verified");
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Audio Book Icon not displayed");
									errCount++;
								}
								if(errCount==0) {
									test.log(LogStatus.PASS, "209: Verify the metadata displayed for the favorited Audio book card is PASS");
									homepagev2.smokeresults("209: Verify the metadata displayed for the favorited Audio book card",rowno209, "PASS");
								}
								//Click on the audio to verify Up arrow
								try {
									driver.findElement(By.xpath(uiFavBookLocator)).click();
									test.log(LogStatus.INFO, "Clicked on the favourited Audio");
									//Tap on Play
									if(Utilities.explicitWaitVisible(driver, listenpagev2.play_btn, 10)) {
										listenpagev2.play_btn.click();
										test.log(LogStatus.INFO, "Clicked on Play button");
										if(Utilities.explicitWaitVisible(driver, listenpagev2.audioPlayerUpArrowExpand, 10)) {
											test.log(LogStatus.INFO, "Up Arrow is displayed");
											test.log(LogStatus.FAIL, "Verify the availablity of Up arrow option if there is only 1 Audiobook in Favourites screen is FAIL");
											homepagev2.smokeresults("Verify the availablity of Up arrow option if there is only 1 Audiobook in Favourites screen",rowno1749, "FAIL");
										}
										else {
											test.log(LogStatus.INFO, "Up Arrow is not displayed");
											test.log(LogStatus.PASS, "Verify the availablity of Up arrow option if there is only 1 Audiobook in Favourites screen is PASS");
											homepagev2.smokeresults("Verify the availablity of Up arrow option if there is only 1 Audiobook in Favourites screen",rowno1749, "PASS");											
										}
										//mark unfavourite
										if(Utilities.explicitWaitClickable(driver, listenpagev2.audioPlayerFavouriteButton, 10)) {
											listenpagev2.audioPlayerFavouriteButton.click();
											test.log(LogStatus.INFO, "Marked as unfavourite");
										}
										else {
											test.log(LogStatus.INFO, "Failed to mark as unfavourite");
										}
										//Close the player
										try {
											listenpagev2.playerCloseButton.click();
											test.log(LogStatus.INFO, "Closed the audio player");
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to close the audio player");
										}
										driver.navigate().back();
										test.log(LogStatus.INFO, "Navigated back to Home page");
									}
									else {
										test.log(LogStatus.FAIL, "Failed to click on Play button");
									}	
								}
								catch(Exception e) {
									test.log(LogStatus.INFO, "Failed to click on the favourited Audio");
								}
								break;
							}
							catch(Exception e) {
								Utilities.verticalSwipe(driver);
								Utilities.verticalSwipe(driver);
								if(scroll==3) {
									test.log(LogStatus.INFO, "Audio is not present in Favourites Tray");
									errCount++;
									test.log(LogStatus.FAIL, "199: Verify the click funtionality on tapping on favourite icon in audio book detail page");
									homepagev2.smokeresults("199: Verify the click funtionality on tapping on favourite icon in audio book detail page",rowno199, "FAIL");
								}
							}
						}
						if(errCount>0) {
							test.log(LogStatus.FAIL, "209: Verify the metadata displayed for the favorited Audio book card is FAIL");
							homepagev2.smokeresults("209: Verify the metadata displayed for the favorited Audio book card",rowno209, "FAIL");
						}
					}
					else {
						test.log(LogStatus.FAIL, "Favourites tray is not displayed even after marking audio book as Favourite");
						basepagev2.takeScreenshot();
					}	
				}
				else {
					test.log(LogStatus.FAIL,"Favourite icon is not clickable in the audio book detail page"); 
					basepagev2.takeScreenshot();
				}					
			 	
			  
		//Verification of 200
		 errCount=0;
		//Scroll upwards 
		Utilities.verticalSwipeReverse(driver);
		Thread.sleep(1000);
		Utilities.verticalSwipeReverse(driver);
		//Click on Watch tab
		 homepagev2.tabClick("Watch");
		 test.log(LogStatus.INFO, "Clicked on Watch tab");
		 Thread.sleep(2000);
		 //Click on the first carousal item
		 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
			 String nameEpisode=watchpagev2.firstItemInCarousalName.getAttribute("text");
			 String descEpisode=watchpagev2.firstItemInCarousalDesc.getAttribute("text");
			 watchpagev2.firstItemInCarousal.click();
			 if(Utilities.explicitWaitVisible(driver, watchpagev2.watchFirstItemPlayer, 3)) {
				 test.log(LogStatus.INFO, "Player is displayed on tapping first item of Carousal in Watch tab");
				 //Tap on player until controls are visible
				 for(int time=0;time<=10;time++) {
					 try {
						 watchpagev2.watchFirstItemPlayer.click();
						 Thread.sleep(2000);
						 homepagev2.verifyAndProgressBar();
						 kidsplayerpagev2.pauseVideo();
						 if(Utilities.explicitWaitClickable(driver,watchpagev2.watchFirstItemPlayerFav,3)) {
							 test.log(LogStatus.INFO, "Favourites icon is present in player");
							 watchpagev2.watchFirstItemPlayerFav.click();
							 test.log(LogStatus.INFO, "Clicked on Favourite icon");
							 break;
						 }
						 else
							 Thread.sleep(3000);
					 }
					 catch(Exception e) {
						 Thread.sleep(3000);
						 if(time==10) {
							 test.log(LogStatus.FAIL, "Player is not clickable"); 
							 errCount++;
						 }			 
					 }					 
				 }
				 //Verify if present in Favourites
				 //Scroll to Favourites
				 driver.navigate().back();
				 Utilities.verticalSwipeReverse(driver);
				 homepagev2.mystuff_tab.click();
				 Thread.sleep(2000);
				 String favouritesXpath="//android.widget.TextView[@text='FAVOURITES']";
				 boolean presenceTray=Utilities.verticalSwipeAndFind(driver,favouritesXpath);
				 if(presenceTray==true) {
					test.log(LogStatus.INFO, "Favourites tray is displayed"); 
					String uiFavEpDescLocator="//android.widget.TextView[contains(@text,'"+descEpisode+"')]";
					String uiFavEpNameLocator="//android.widget.TextView[contains(@text,'"+descEpisode+"')]/parent::android.view.ViewGroup//android.widget.TextView[@index='3']";
					String uiFavEpIconLocator="//android.widget.TextView[contains(@text,'"+descEpisode+"')]/parent::android.view.ViewGroup//android.widget.ImageView[@index='5']";
					for(int scroll=0;scroll<=3;scroll++) {
						try {
							driver.findElement(By.xpath(uiFavEpDescLocator));
							Thread.sleep(2000);
							test.log(LogStatus.PASS, "200: Verify the click funtionality on tapping on favourite icon from player is PASS");
							homepagev2.smokeresults("200: Verify the click funtionality on tapping on favourite icon from player",rowno200, "PASS");
							//Verification of 208
							test.log(LogStatus.INFO, "Starting 208: Verify the metadata displayed for the favorited episode card");
							try {
								String uiShowName=driver.findElement(By.xpath(uiFavEpNameLocator)).getAttribute("text");
								test.log(LogStatus.INFO, "Show Title is verified: "+uiShowName);
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Episode title is not displayed");
								errCount++;
							}
							try {
								String uiEpDescName=driver.findElement(By.xpath(uiFavEpDescLocator)).getAttribute("text");
								test.log(LogStatus.INFO, "Episode description is verified "+uiEpDescName);
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Episode description is not displayed");
								errCount++;
							}
							try {
								driver.findElement(By.xpath(uiFavEpIconLocator));
								test.log(LogStatus.INFO, "Episode Icon is verified");
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Episode Icon not displayed");
								errCount++;
							}
							if(errCount==0) {
								test.log(LogStatus.PASS, "208: Verify the metadata displayed for the favorited episode card is PASS");
								homepagev2.smokeresults("208: Verify the metadata displayed for the favorited episode card",rowno208, "PASS");
							}
							//Click on the Video to verify Up arrow
							try {
								driver.findElement(By.xpath(uiFavEpDescLocator)).click();
								test.log(LogStatus.INFO, "Clicked on the favourited episode");
								try {
									kidsplayerpagev2.pauseVideo();
									test.log(LogStatus.INFO, "Paused the video");
									//Verify up arrow
									if(Utilities.explicitWaitVisible(driver, watchpagev2.playerExpand, 10)) {
										test.log(LogStatus.INFO, "Up Arrow is displayed");
										test.log(LogStatus.FAIL, "Verify the availablity of Up arrow option if there is only 1 favourited episode content is FAIL");
										homepagev2.smokeresults("Verify the availablity of Up arrow option if there is only 1 favourited episode content",rowno1747, "FAIL");
									}
									else {
										test.log(LogStatus.INFO, "Up Arrow is not displayed");
										test.log(LogStatus.PASS, "Verify the availablity of Up arrow option if there is only 1 favourited episode content is PASS");
										homepagev2.smokeresults("Verify the availablity of Up arrow option if there is only 1 favourited episode content",rowno1747, "PASS");											
									}
								}
								catch(Exception e) {
									test.log(LogStatus.INFO, "Paused the video");
								}
								 
							}
							catch(Exception e) {
								test.log(LogStatus.INFO, "Clicked on the favourited episode");
							}
							break;	
						}
						catch(Exception e) {
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipe(driver);
							if(scroll==3) {
								errCount++;
								test.log(LogStatus.INFO, "Episode is not present in Favourites Tray");
								test.log(LogStatus.FAIL, "200: Verify the click funtionality on tapping on favourite icon from player");
								basepagev2.takeScreenshot();
								homepagev2.smokeresults("200: Verify the click funtionality on tapping on favourite icon from player",rowno200, "FAIL");						
							}
							
						}
					}
					if(errCount>0) {
						test.log(LogStatus.FAIL, "208: Verify the metadata displayed for the favorited episode card is FAIL");
						homepagev2.smokeresults("208: Verify the metadata displayed for the favorited episode card",rowno208, "FAIL");
					}
						
				}
				else {
					test.log(LogStatus.FAIL, "Favourites tray is not displayed even after marking episode as Favourite");
					basepagev2.takeScreenshot();
				}	 	 
			 }
			 else {
				 test.log(LogStatus.FAIL, "Player is not displayed on tapping first item of Carousal in Watch tab");
				 basepagev2.takeScreenshot();
			 }			 
		 }
		 else {
			 test.log(LogStatus.FAIL, "Unable to click on first item of Carousal in Watch tab");
			 basepagev2.takeScreenshot();
		 }	*/ 			 
		 
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
