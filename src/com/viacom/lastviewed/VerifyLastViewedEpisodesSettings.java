package com.viacom.lastviewed;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyLastViewedEpisodesSettings extends BaseTestV2{
	
	String testName = "VerifyLastViewedEpisodesSettings";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedEpisodesSettings(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedEpisodesSettings");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls_VK_1877 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1877=xls_VK_1877.getRowCount("Smoke_Results")+1;
		xls_VK_1877.setCellData("Smoke_Results", "Testcase Name",row_VK_1877,"Verify if Last viewed assets are specific to individual profile");

		Xls_Reader xls_VK_1878 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1878=xls_VK_1878.getRowCount("Smoke_Results")+1;
		xls_VK_1878.setCellData("Smoke_Results", "Testcase Name",row_VK_1878,"Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device");
		
		Xls_Reader xls_VK_1880 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1880=xls_VK_1880.getRowCount("Smoke_Results")+1;
		xls_VK_1880.setCellData("Smoke_Results", "Testcase Name",row_VK_1880,"Verify if Cards under Last viewed tray are retained when user clears device app data and logs in");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
		 
		 boolean clickedOnShow=false;
		 String episode1="";
		 String episode2="";
		 String lastViewed1,lastViewed2="";
		 String recentViewedClear="";
		 int errVK_1877=0;
		 int errVK_1878=0;
		 int errVK_1880=0;
		//Login module 
		//homepagev2.logout();
		homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }

		 
		//Click on Watch tab
		 homepagev2.tabClick("Watch");
		//Scroll to shows tray
		 for(int scroll=0;scroll<=4;scroll++) {
			 if(Utilities.explicitWaitClickable(driver, watchpagev2.allKidsCharacters2, 10)) {
				 try {
					 watchpagev2.allKidsCharacters2.click();
					 test.log(LogStatus.INFO, "Clicked on 2nd character from kids characters tray");
					 clickedOnShow=true;
					 break;
				 }
				 catch(Exception e) {
					 test.log(LogStatus.FAIL, "Failed to click on 2nd character from kids characters tray");
				 }
				 break;
			 }
			 else {
				 Utilities.verticalSwipe(driver); 
				 if(scroll==4) {
					 test.log(LogStatus.FAIL, "2nd character from kids characters tray is not clickable");
				 }
			 }
		 }
				 
		 if(clickedOnShow==true) {
			 for(int findtray=0;findtray<=3;findtray++) {
				 if(Utilities.explicitWaitClickable(driver, showpagev2.episodesTray, 5)) {
					 test.log(LogStatus.INFO, "Located Episodes tray"); 
					 Utilities.verticalSwipe(driver);
					 Utilities.verticalSwipe(driver);
					 Thread.sleep(2000);
					 if(Utilities.explicitWaitVisible(driver, showpagev2.showDetailsEpisode1, 10)){
						 episode1=showpagev2.showDetailsEpisode1Title.getAttribute("text");
						 try {
							 showpagev2.showDetailsEpisode1Title.click();
							 test.log(LogStatus.INFO, "Clicked on first episode: "+episode1);
							 homepagev2.verifyAndProgressBar();
							 test.log(LogStatus.INFO, "First Episode started to play");
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to close the player");
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Failed to click on first episode");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "First episode is not visible, hence failed to fetch name");
					 }
					 if(Utilities.explicitWaitVisible(driver, showpagev2.showDetailsEpisode2, 10)){
						 episode2=showpagev2.showDetailsEpisode2Title.getAttribute("text");
						 try {
							 showpagev2.showDetailsEpisode2Title.click();
							 test.log(LogStatus.INFO, "Clicked on second episode: "+episode2);
							 homepagev2.verifyAndProgressBar();
							 test.log(LogStatus.INFO, "Second Episode started to play");
							 //kidsplayerv2.pauseVideo();
							 //Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);
							 //kidsplayerv2.playVideo();
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to close the player");
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Failed to click on second episode");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Second episode is not visible, hence failed to fetch name");
					 }
					 break;
				 }
				 else {
					 Thread.sleep(1000);
					 Utilities.verticalSwipe(driver);
					 if(findtray==3) {
						 test.log(LogStatus.FAIL, "Could not locate Episodes tray");
					 }
				 }
			 }
		}
		
		driver.navigate().back();
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		//Logout and Login
		homepagev2.logout();
		homepagev2.login(data.get("Email"),data.get("Password"));
		//Select profile Jane
		if(Utilities.explicitWaitClickable(driver, homepagev2.profileBtn, 30)) {
			try {
				homepagev2.profileBtn.click();
				test.log(LogStatus.INFO, "Clicked on Profile icon");
				try {
					driver.findElement(By.xpath("//android.widget.TextView[@text='Jane']//parent::android.view.ViewGroup[@instance='20']//android.widget.ImageView")).click();
					test.log(LogStatus.INFO, "Switched to first profile");
					if(Utilities.explicitWaitClickable(driver, settingspagev2.switchProfileCancel, 60)) {
						try {
							settingspagev2.switchProfileCancel.click();
						}
						catch(Exception e2) {
							test.log(LogStatus.FAIL, "Failed to to close switch profile screen");
						}
					}
				}
				catch(Exception e1) {
					test.log(LogStatus.FAIL, "Failed to switch profile");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to clicked on Profile icon");
			}
		}
		//Check last Viewed
		//Scroll till Last Viewed
		homepagev2.tabClick("Read");
		homepagev2.tabClick("Read");
		recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
	    	if(lastViewed1.equals(episode2)) {
	    		test.log(LogStatus.INFO, lastViewed1+" first latest episode under last viewed in Watch tab is retained after re-login");	    	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in Watch tab instead of "+episode2);	
	    		errVK_1878++;
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 1 is not visible under last viewed in Watch tab");
	    	errVK_1878++;
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
	    	if(lastViewed2.equals(episode1)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed in Watch tab is retained after re-login");
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed in My Stuff tab instead of "+episode1);	    	
	    		errVK_1878++;
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 2 is not visible under last viewed in Watch tab");
	    	errVK_1878++;
	    }
		if(errVK_1878==0) {
			test.log(LogStatus.INFO, "Episodes under Watch tab are retained after re-login");
			homepagev2.smokeresults("Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device",row_VK_1878, "PASS");
			test.log(LogStatus.PASS, "Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device is PASS"); 
		}
		else {
			test.log(LogStatus.FAIL, "Episodes under Watch tab are not retained after re-login");
			homepagev2.smokeresults("Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device",row_VK_1878, "FAIL");
			test.log(LogStatus.PASS, "Verify if Cards under Last viewed tray are retained when user Logs out and Logs in from same device is FAIL"); 
		}
		//Switch the profile
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		if(Utilities.explicitWaitClickable(driver, homepagev2.profileBtn, 30)) {
			try {
				homepagev2.profileBtn.click();
				test.log(LogStatus.INFO, "Clicked on Profile icon");
				try {
					driver.findElement(By.xpath("//android.widget.TextView[@text='Chris']//parent::android.view.ViewGroup[@instance='19']//android.widget.ImageView")).click();
					test.log(LogStatus.INFO, "Switched to second profile");
					if(Utilities.explicitWaitClickable(driver, settingspagev2.switchProfileCancel, 60)) {
						try {
							settingspagev2.switchProfileCancel.click();
						}
						catch(Exception e2) {
							test.log(LogStatus.FAIL, "Failed to to close switch profile screen");
						}
					}
				}
				catch(Exception e1) {
					test.log(LogStatus.FAIL, "Failed to switch profile");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to clicked on Profile icon");
			}
		}

		homepagev2.tabClick("Read");
		homepagev2.tabClick("Read");
		//Scroll till Last Viewed
		recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
	    	if(lastViewed1.equals(episode2)) {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in Watch tab, same as previous profile");	 
	    		errVK_1877++;
	    	}
	    	else {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest episode under last viewed in Watch tab instead of "+episode2);	    	
	    	}
	    }
	    else {
	    	test.log(LogStatus.INFO, "Last Viewed episode 1 is not visible under last viewed in Watch tab");
	    }
	    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
	    	if(lastViewed2.equals(episode1)) {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed in Watch tab, same as previous profile");	   
	    		errVK_1877++;
	    	}
	    	else {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed in Watch tab instead of "+episode1);	    	
	    	}
	    }
	    else {
	    	test.log(LogStatus.INFO, "Last Viewed episide 2 is not visible under last viewed in Watch tab");
	    }
	    basepagev2.takeScreenshot();
	  //Final verification of VK_1877
		if(errVK_1877==0) {
			test.log(LogStatus.PASS, "Episodes under Watch tab are different for the two profiles");
			homepagev2.smokeresults("Verify if Last viewed assets are specific to individual profile",row_VK_1877, "PASS");
			test.log(LogStatus.PASS, "Verify if Last viewed assets are specific to individual profile is PASS"); 
		}
		else {
			test.log(LogStatus.FAIL, "Episodes under Watch tab are same for the two profiles");
			homepagev2.smokeresults("Verify if Last viewed assets are specific to individual profile",row_VK_1877, "FAIL");
			test.log(LogStatus.FAIL, "Verify if Last viewed assets are specific to individual profile is FAIL"); 		
		}

	//Clear app data
	driver.resetApp();
	homepagev2.login(data.get("Email"),data.get("Password"));
	
	homepagev2.tabClick("Watch");
	homepagev2.tabClick("Watch");
	//Select first profile
	
	//Scroll till Last Viewed
	recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
	Utilities.verticalSwipeAndFind(driver, recentViewedClear);
	test.log(LogStatus.INFO, "Swiped till Last viewed tray");
	Utilities.verticalSwipe(driver);
	Utilities.verticalSwipe(driver);
    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
    	if(lastViewed1.equals(episode2)) {
    		test.log(LogStatus.INFO, lastViewed1+" is the first latest episode under last viewed in Watch tab, same as before clearing app data");	 
    	}
    	else {
    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in Watch tab instead of "+episode2);	    	
    		errVK_1880++;
    	}
    }
    else {
    	test.log(LogStatus.INFO, "Last Viewed episode 1 is not visible under last viewed in Watch tab");
    }
    if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
    	if(lastViewed2.equals(episode1)) {
    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed in Watch tab, same as before clearing app data");	   
    		errVK_1880++;
    	}
    	else {
    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed in Watch tab instead of "+episode1);	    	
    	}
    }
    else {
    	test.log(LogStatus.INFO, "Last Viewed episide 2 is not visible under last viewed in Watch tab");
    }
    basepagev2.takeScreenshot();
  //Final verification of VK_1880
	if(errVK_1880==0) {
		test.log(LogStatus.PASS, "Episodes under Watch tab are same after clearing app data");
		homepagev2.smokeresults("Verify if Cards under Last viewed tray are retained when user clears device app data and logs in",row_VK_1880, "PASS");
		test.log(LogStatus.PASS, "Verify if Cards under Last viewed tray are retained when user clears device app data and logs in is PASS"); 
	}
	else {
		test.log(LogStatus.FAIL, "Episodes under Watch tab are not same after clearing app data");
		homepagev2.smokeresults("Verify if Cards under Last viewed tray are retained when user clears device app data and logs in",row_VK_1880, "FAIL");
		test.log(LogStatus.FAIL, "Verify if Cards under Last viewed tray are retained when user clears device app data and logs in is FAIL"); 		
	}
	
	
		
		
	//Clear Last Viewed
	//homepagev2.clearLastViewed();
	    
   
		homepagev2.mystuff_tab=null;
		watchpagev2.allKidsCharacters2=null;
		showpagev2.episodesTray=null;
		showpagev2.showDetailsEpisode1=null;
		showpagev2.showDetailsEpisode1Title=null;
		showpagev2.showDetailsEpisode2=null;
		showpagev2.showDetailsEpisode2Title=null;
		showpagev2.showDetailsEpisode3=null;
		showpagev2.showDetailsEpisode3Title=null;
		homepagev2.lastViewedFirstItemTitle=null;
		homepagev2.lastViewedSecondItemTitle=null;
		homepagev2.lastViewedThirdItemTitle=null;
		homepagev2.recentViewedClear=null;
		homepagev2.recentViewedClearMessage=null;
		homepagev2.recentViewedClearYes=null;
		homepagev2.recentViewedClearNo=null;
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
