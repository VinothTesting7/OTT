package com.viacom.lastviewed;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
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
public class VerifyLastViewedEpisodes extends BaseTestV2{
	
	String testName = "VerifyLastViewedEpisodes";
	@Test(dataProvider = "getData")
	public void VerifyLastViewedEpisodes(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedEpisodes");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls_VK_1658 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1658=xls_VK_1658.getRowCount("Smoke_Results")+1;
		xls_VK_1658.setCellData("Smoke_Results", "Testcase Name",row_VK_1658,"Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes");
		
		Xls_Reader xls_VK_1659 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1659=xls_VK_1659.getRowCount("Smoke_Results")+1;
		xls_VK_1659.setCellData("Smoke_Results", "Testcase Name",row_VK_1659,"Verify if the Episode cards are added to the Last viewed tray if user watches a video partially");

		Xls_Reader xls_VK_1660 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1660=xls_VK_1660.getRowCount("Smoke_Results")+1;
		xls_VK_1660.setCellData("Smoke_Results", "Testcase Name",row_VK_1660,"Verify if the Episode cards are added to the Last viewed tray if user watches a video completely");

		Xls_Reader xls_VK_1673 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1673=xls_VK_1673.getRowCount("Smoke_Results")+1;
		xls_VK_1673.setCellData("Smoke_Results", "Testcase Name",row_VK_1673,"Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes");
		
		Xls_Reader xls_VK_1674 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1674=xls_VK_1674.getRowCount("Smoke_Results")+1;
		xls_VK_1674.setCellData("Smoke_Results", "Testcase Name",row_VK_1674,"Verify if the Episode cards are added to the Last viewed tray if user watch content partially");

		Xls_Reader xls_VK_1675 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1675=xls_VK_1675.getRowCount("Smoke_Results")+1;
		xls_VK_1675.setCellData("Smoke_Results", "Testcase Name",row_VK_1675,"Verify if the Episode cards are added to the Last viewed tray if user watches a video completely");
		
		Xls_Reader xls_VK_1505 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1505=xls_VK_1505.getRowCount("Smoke_Results")+1;
		xls_VK_1505.setCellData("Smoke_Results", "Testcase Name",row_VK_1505,"Verify 'Clear' link functionality");
		
		Xls_Reader xls_VK_1506 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1506=xls_VK_1506.getRowCount("Smoke_Results")+1;
		xls_VK_1506.setCellData("Smoke_Results", "Testcase Name",row_VK_1506,"Verify the functionality of 'Yes' option in clear link confirmation message");

		Xls_Reader xls_VK_1507 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int row_VK_1507=xls_VK_1507.getRowCount("Smoke_Results")+1;
		xls_VK_1507.setCellData("Smoke_Results", "Testcase Name",row_VK_1507,"Verify the functionality of 'No' option in clear link confirmation message");
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 
		 boolean clickedOnShow=false;
		 String episode1="";
		 String episode2="";
		 String episode3="";
		 String lastViewed1,lastViewed2,lastViewed3="";
		 int errVK_1505=0;
			//Login module 
			//homepagev2.logout();
			homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisibleNew(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }
		 
		//Click on Watch tab
		 homepagev2.tabClick("Watch");
		//Scroll to shows tray
		 for(int scroll=0;scroll<=4;scroll++) {
			 if(Utilities.explicitWaitVisibleNew(driver, watchpagev2.allKidsCharacters2, 5)) 
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
			 for(int findtray=0;findtray<=3;findtray++) {
				 if(Utilities.explicitWaitClickable(driver, showpagev2.episodesTray, 5)) {
					 test.log(LogStatus.INFO, "Located Episodes tray"); 
					 Utilities.verticalSwipe(driver);
					 Utilities.verticalSwipe(driver);
					 Thread.sleep(2000);
					 if(Utilities.explicitWaitVisibleNew(driver, showpagev2.showDetailsEpisode1Title, 10)){
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
					 if(Utilities.explicitWaitVisibleNew(driver, showpagev2.showDetailsEpisode2Title, 10)){
						 episode2=showpagev2.showDetailsEpisode2Title.getAttribute("text");
						 try {
							 showpagev2.showDetailsEpisode2Title.click();
							 test.log(LogStatus.INFO, "Clicked on second episode: "+episode2);
							 homepagev2.verifyAndProgressBar();
							 test.log(LogStatus.INFO, "Second Episode started to play");
							 kidsplayerv2.pauseVideo();
							 Utilities.scrubtillhalf(driver, homepagev2.audioseekBar);
							 kidsplayerv2.playVideo();
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
					 if(Utilities.explicitWaitVisibleNew(driver, showpagev2.showDetailsEpisode3Title, 10)){
						 episode3=showpagev2.showDetailsEpisode3Title.getAttribute("text");
						 try {
							 showpagev2.showDetailsEpisode3Title.click();
							 test.log(LogStatus.INFO, "Clicked on third episode: "+episode3);
							 homepagev2.verifyAndProgressBar();
							 test.log(LogStatus.INFO, "Third Episode started to play");
							 kidsplayerv2.pauseVideo();
							 Utilities.scrubtillend(driver, homepagev2.audioseekBar);
							 driver.navigate().back();
							 test.log(LogStatus.INFO, "Navigated back to close the player");
						 }
						 catch(Exception e) {
							 test.log(LogStatus.INFO, "Failed to click on third episode");
						 }
					 }
					 else {
						 test.log(LogStatus.FAIL, "Third episode is not visible, hence failed to fetch name");
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
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("My Stuff");
		homepagev2.tabClick("My Stuff");
		//Scroll till Last Viewed
		String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipe(driver,recentViewedClear,"clickable",60);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
	    	if(lastViewed1.contains(episode3)) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest episode under last viewed in My Stuff tab");
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video completely",row_VK_1660, "PASS");
				test.log(LogStatus.PASS, "Verify if the Episode cards are added to the Last viewed tray if user watches a video completely is PASS"); 			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in My Stuff tab instead of "+episode3);
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video completely",row_VK_1660, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the Last viewed tray if user watches a video completely is FAIL"); 		
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episode 1 is not visible under last viewed in My Stuff tab");
	    }
	    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
	    	if(lastViewed2.contains(episode2)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed in My Stuff tab");
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video partially",row_VK_1659, "PASS");
				test.log(LogStatus.PASS, "Verify if the Episode cards are added to the Last viewed tray if user watches a video partially is PASS");
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed in My Stuff tab instead of "+episode2);
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video partially",row_VK_1659, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the Last viewed tray if user watches a video partially is FAIL");
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episide 2 is not visible under last viewed in My Stuff tab");
	    }
	    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lastViewedThirdItemTitle, 10)) {
	    	lastViewed3=homepagev2.lastViewedThirdItemTitle.getAttribute("text");
	    	if(lastViewed3.contains(episode1)) {
	    		test.log(LogStatus.INFO, lastViewed3+" is the third latest episode under last viewed in My Stuff tab");
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes",row_VK_1658, "PASS");
				test.log(LogStatus.PASS, "Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes is PASS"); 			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest episode under last viewed in My Stuff tab instead of "+episode1);
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes",row_VK_1658, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes is FAIL"); 		
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episide 3 is not visible under last viewed in My Stuff tab");
	    }
	    basepagev2.takeScreenshot();
	    
		Utilities.verticalSwipeReverse(driver);
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("Watch");
		//Scroll till Last Viewed
		recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipe(driver,recentViewedClear,"clickable",60);
		test.log(LogStatus.INFO, "Swiped till Last viewed tray");
		Utilities.verticalSwipe(driver);
		Utilities.verticalSwipe(driver);
	    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstItemTitle.getAttribute("text");
	    	if(lastViewed1.contains(episode3)) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest episode under last viewed in Watch tab");
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video completely",row_VK_1675, "PASS");
				test.log(LogStatus.PASS, "Verify if the Episode cards are added to the Last viewed tray if user watches a video completely is PASS"); 			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest episode under last viewed in Watch tab instead of "+episode3);
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video completely",row_VK_1675, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the Last viewed tray if user watches a video completely is FAIL"); 		
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episide 1 is not visible under last viewed in Watch tab");
	    }
	    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lastViewedSecondItemTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondItemTitle.getAttribute("text");
	    	if(lastViewed2.contains(episode2)) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest episode under last viewed in Watch tab");
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video partially",row_VK_1674, "PASS");
				test.log(LogStatus.PASS, "Verify if the Episode cards are added to the Last viewed tray if user watches a video partially is PASS");
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest episode under last viewed in Watch tab instead of "+episode2);
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user watches a video partially",row_VK_1674, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the Last viewed tray if user watches a video partially is FAIL");
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episide 2 is not visible under last viewed in Watch tab");
	    }
	    if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lastViewedThirdItemTitle, 10)) {
	    	lastViewed3=homepagev2.lastViewedThirdItemTitle.getAttribute("text");
	    	if(lastViewed3.contains(episode1)) {
	    		test.log(LogStatus.INFO, lastViewed3+" is the third latest episode under last viewed in Watch tab");
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes",row_VK_1673, "PASS");
				test.log(LogStatus.PASS, "Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes is PASS"); 			
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest episode under last viewed in Watch tab instead of "+episode1);
	    		homepagev2.smokeresults("Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes",row_VK_1673, "FAIL");
				test.log(LogStatus.FAIL, "Verify if the Episode cards are added to the Last viewed tray if user launches the player & closes is FAIL"); 		
	    	}
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Last Viewed episide 3 is not visible under last viewed in Watch tab");
	    }
	    basepagev2.takeScreenshot();

	    //Clear Last Viewed
	    //homepagev2.clearLastViewed();
	    
	    //TCS relating Clear link in Watch tab
	    if(Utilities.explicitWaitClickable(driver, homepagev2.watchTabLVClear, 10)) {
			try {
				homepagev2.watchTabLVClear.click();
				test.log(LogStatus.INFO, "Clicked on Clear link of Last Viewed popup in Watch tab");
				//Verify message
				try {
					String messageUI=homepagev2.recentViewedClearMessage.getAttribute("text");
					String messageExpected="Are you sure you want to delete all last viewed items?";
					if(messageUI.equals(messageExpected)) {
						test.log(LogStatus.INFO, "Are you sure you want to delete all last viewed items? -> Is displayed");
					}
					else {
						test.log(LogStatus.FAIL, "Message displayed is-> "+messageUI);
						test.log(LogStatus.FAIL, "Expected Message is-> "+messageExpected);
						errVK_1505++;
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to verify confirmation message in popup of Last Viewed in Watch tab");
				}
				//Verify presence of YES button
				if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClearYes, 5)) {
					test.log(LogStatus.INFO, "Located YES button");
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate YES button");
					errVK_1505++;				
				}
				//Verify presence of No button
				if(Utilities.explicitWaitVisible(driver, homepagev2.recentViewedClearNo, 5)) {
					test.log(LogStatus.INFO, "Located NO button");
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate NO button");
					errVK_1505++;				
				}
				//Verification of VK_1505
				if(errVK_1505==0) {
		    		homepagev2.smokeresults("Verify 'Clear' link functionality",row_VK_1505, "PASS");
					test.log(LogStatus.PASS, "Verify 'Clear' link functionality is PASS");
				}
				else {
					homepagev2.smokeresults("Verify 'Clear' link functionality",row_VK_1505, "FAIL");
					test.log(LogStatus.FAIL, "Verify 'Clear' link functionality is FAIL");
				}
				//Verify No button functionality
				try {
					homepagev2.recentViewedClearNo.click();
					test.log(LogStatus.INFO, "Clicked on No button in the popup");
					if(Utilities.explicitWaitVisibleNew(driver, homepagev2.lastViewedFirstItemTitle, 10)) {
						test.log(LogStatus.INFO, "Verified first episode is still displayed");
			    		homepagev2.smokeresults("Verify the functionality of 'No' option in clear link confirmation message",row_VK_1507, "PASS");
						test.log(LogStatus.PASS, "Verify the functionality of 'No' option in clear link confirmation message is PASS");
						try {
							homepagev2.recentViewedClear.click();
							test.log(LogStatus.INFO, "Clicked on Clear link again to verify YES button functionality");
						}
						catch(Exception e) {
							test.log(LogStatus.INFO, "Failed to click on Clear link to verify YES button functionality");
						}
					}
					else {
						test.log(LogStatus.FAIL, "Unable to locate first episode");
						homepagev2.smokeresults("Verify the functionality of 'No' option in clear link confirmation message",row_VK_1507, "FAIL");
						test.log(LogStatus.FAIL, "Verify the functionality of 'No' option in clear link confirmation message is FAIL");	
					}
					
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on No in the popup of Last Viewed in Watch tab");
				}
				//Verify Yes button functionality
				try {
					homepagev2.recentViewedClearYes.click();
					test.log(LogStatus.INFO, "Clicked on Yes button in the popup");
					Thread.sleep(3000);
					Thread.sleep(3000);
					if(Utilities.explicitWaitVisibleNew(driver, homepagev2.watchTabLVClear, 10)) {
						test.log(LogStatus.FAIL, "Clear button is displayed, tray is not cleared");
			    		homepagev2.smokeresults("Verify the functionality of 'Yes' option in clear link confirmation message",row_VK_1506, "FAIL");
						test.log(LogStatus.FAIL, "Verify the functionality of 'Yes' option in clear link confirmation message is FAIL");	
						BasePageV2.takeScreenshot();
					}
					else {
						test.log(LogStatus.INFO, "Clear button is not displayed, tray has been cleared");
						homepagev2.smokeresults("Verify the functionality of 'Yes' option in clear link confirmation message",row_VK_1506, "PASS");
						test.log(LogStatus.PASS, "Verify the functionality of 'Yes' option in clear link confirmation message is PASS");	
					}	
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Yes in the popup of Last Viewed in Watch tab");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on Clear link in Last Viewed in Watch tab");
			}
		}
		else {
			test.log(LogStatus.FAIL, "Clear link is not clickable in Last Viewed in Watch tab");
		}
	    
	    
	    
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
