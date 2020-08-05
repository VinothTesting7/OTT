package com.viacom.settings;

import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.connection.ConnectionState;

public class SettingsDeviceCellularFunctionality extends BaseTestV2{
	
	String testName = "SettingsDeviceCellularFunctionality";
    String ebooktitle = "";
	@Test(dataProvider = "getData")
	public void settingsDeviceCellularFunctionality(Hashtable<String, String> data) throws Exception {
		test = rep.startTest("SettingsDeviceCellularFunctionality");
		test.log(LogStatus.INFO, "Starting the test to Settings Device Cellular Functionality : " + VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}


		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		
		int VK_850 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_850,"Verify the functionality when Cellular Playback button in OFF state:");

		int VK_851 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_851,"Verify the functionality when Cellular Playback button in ON state:");

		int VK_869 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_869,"Verify download button functionality when 'Cellular Downloads'  button is in 'ON' state:");

		int VK_879 = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", VK_879,"Verify the Audio Download Quality dropdown functionality:");
		
		
		// Launching the Voot-kids App
		launchApp();
		test.log(LogStatus.INFO, "Application launched successfully");
        SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
        HomePageV2 homepagev2 = new HomePageV2(driver, test);
        WatchPageV2 watchpagev2 = new WatchPageV2(driver, test);
        KidsPlayerPageV2 playerpagev2 = new KidsPlayerPageV2(driver, test);
        BooksPageV2 bookspagev2 = new BooksPageV2(driver, test);
        DownloadsPageV2 downloadsPageV2 = new DownloadsPageV2(driver, test);

        
//        try {
//			 HomePageV2.login("cellpbs@gmail.com", "Ifocus@122");  // 8904583619 mobile number
//	        }catch(Exception e) {
//	        	test.log(LogStatus.FAIL, "login Failed");
//	        }
        
        homepagev2.login(data.get("Email"),data.get("Password"));
        

        
//		 //Scroll to Downloads for Clearing the Downloads to Empty
		 String downloadTabLocator="//android.widget.TextView[@text='Downloads']";
		 boolean tabPresence=Utilities.verticalSwipeAndFind(driver,downloadTabLocator);
		 if(tabPresence==true) {
			 if(homepagev2.downloadsTab.getAttribute("selected").equals("false")) {
						 homepagev2.downloadsTab.click();
						 test.log(LogStatus.INFO,"Clicked on Downloads tab");
				
			 }else if (homepagev2.downloadsTab.getAttribute("selected").equals("true")) {
				 test.log(LogStatus.INFO, "'Download' tab is Enabled ");
			 }
		 }
        

      //deleting the cards from dowloads
		 
		 String downloadTabLocator1="//android.widget.TextView[@text='Downloads']";
		 Utilities.verticalSwipeAndFind(driver,downloadTabLocator1);
		 
		 Utilities.verticalSwipe(driver);
		
		if(Utilities.explicitWaitClickable(driver, downloadsPageV2.editDownloadsMystuff, 5)) {
			downloadsPageV2.editDownloadsMystuff.click();
			test.log(LogStatus.INFO, "Clicked 'Edit Downloads' button in downloads ");
			
			if(Utilities.explicitWaitVisible(driver, downloadsPageV2.editdownloadTitle, 5)) {
				test.log(LogStatus.INFO, "Navigated to EDIT DOWNLOADS page");
				String crossBtn ="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item']";
				 for (int i = 1 ; i < 6;i++) {
					 if(Utilities.explicitWaitClickable(driver, downloadsPageV2.deleteDownload, 5)) {
						 downloadsPageV2.deleteDownload.click();
						 if(Utilities.explicitWaitClickable(driver, downloadsPageV2.deletebutton, 10)) {
							 downloadsPageV2.deletebutton.click();
							 test.log(LogStatus.INFO, "clicked delete button in EDIT DOWNLOADS page");
						 }else test.log(LogStatus.FAIL, "Not able to click Dlete Button in EDIT DOWNLOADS page/ Not found");
						 if(Utilities.explicitWaitClickable(driver, downloadsPageV2.downloadDeleteYesBtn, 5)) {
							 downloadsPageV2.downloadDeleteYesBtn.click();
							 test.log(LogStatus.INFO, "Clicked 'YES' button on DELETE pop up");
						 }else test.log(LogStatus.FAIL, "Not able to click 'YES' Button on DELETE Pop up/ Not found");
					 }else if (!Utilities.explicitWaitClickable(driver, downloadsPageV2.deleteDownload, 5)) {
						 test.log(LogStatus.INFO, "Downloads Sections is EMPTY");
						 driver.pressKeyCode(AndroidKeyCode.BACK);
						 break;
					 }else test.log(LogStatus.FAIL, "Cards not found to delete");
				 }
				 
				
			}else test.log(LogStatus.FAIL, "Not able to Navigated to EDIT DOWNLOADS page");
			
			
		}else test.log(LogStatus.FAIL, "Not able to click 'Edit downloads' Button / not found in downloads ");
        
        
         Utilities.verticalSwipeDown(driver);
         Utilities.verticalSwipeDown(driver);
         Utilities.verticalSwipeDown(driver);
        

        
        
        
//      Verify the functionality when Cellular Playback button in OFF state:      
		
            settingsPageV2.cellularPlayDownloadSwichOFF();   // keep the Cellular Playback Switch OFF state
		
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			
        //switching from Wifi to celluler data 
        driver.setConnection(new ConnectionState(0));
        Utilities.verticalSwipeDown(driver);
        
        try {
        	 //click Watch tab
            homepagev2.tabClick("Watch");
            test.log(LogStatus.INFO, "Clicked Watch tab in Tab bar");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click Watch tab ");
		}
       
        
   	 if(Utilities.explicitWaitVisibleNew(driver, watchpagev2.firstItemCarousal, 3)){
		 try{
			watchpagev2.firstItemCarousal.click();
			test.log(LogStatus.INFO, "Clicked Watch Page First Item In Carosal");
			}catch(Exception e){
			test.log(LogStatus.FAIL, "Unable to click on first item in Carousal of Watch Page");
		 }	
         
   	 }else test.log(LogStatus.FAIL, "Not found First Item in carousal of Watch Tab");
        
   	 
   	 if(Utilities.explicitWaitVisible(driver, settingsPageV2.cellularplybackOFFMsg, 5)) {
   		 test.log(LogStatus.INFO, "Found 'CELLULAR PLAYBACK OFF!' msg on pop up");
   		if(Utilities.explicitWaitVisible(driver, settingsPageV2.cellularplybackOFFSUBMsg, 5)) {
   		 test.log(LogStatus.INFO, "Found 'Cellular playback is switched off. Go to settings to edit preferences' Sub msg on pop up");
   			if(Utilities.explicitWaitVisible(driver, settingsPageV2.cellularplybackOFFMsgOkBtn, 5)) {
   				test.log(LogStatus.INFO, "Found 'OK' button on Cellular Playback OFF pop up");
   		            test.log(LogStatus.INFO, "Found pop up 'CELLULAR PLAYBACK OFF!'");
   	                test.log(LogStatus.PASS,"Verify the functionality when Cellular Playback button in OFF state:");
   	                BasePageV2.smokeresults("", VK_850, "PASS");
   			}else  test.log(LogStatus.FAIL, "'Ok' Button not found on Cellular play back OFF pop up");
   		}else test.log(LogStatus.FAIL, "'Cellular playback is switched off. Go to settings to edit preferences' Sun msg on Cellular play back OFF pop up");
   	 }else test.log(LogStatus.FAIL, "'CELLULAR PLAYBACK OFF!' Header title not found on Cellular play back OFF pop up");
   	     
       // click on Ok Button on 'CELLULAR PLAYBACK OFF! pop up
      	 
      if(Utilities.explicitWaitVisible(driver, settingsPageV2.cellularplybackOFFMsgOkBtn, 5)) {
    	  settingsPageV2.cellularplybackOFFMsgOkBtn.click();
    	  test.log(LogStatus.INFO, "Clicked Ok Button on 'CELLULAR PLAYBACK OFF!' Pop up ");
      }else test.log(LogStatus.FAIL, "Not able to click 'OK' button on 'CELLULAR PLAYBACK OFF!' pop up");
        
        
      
//    Verify the functionality when Cellular Playback button in ON state:     
		
      settingsPageV2.cellularPlayDownloadSwichON();  // keep the Cellular Playback Switch OFF state 
      
      driver.pressKeyCode(AndroidKeyCode.BACK);
	  driver.pressKeyCode(AndroidKeyCode.BACK);
	  driver.pressKeyCode(AndroidKeyCode.BACK);
	  
	  Utilities.verticalSwipeDown(driver);
      
	//click Watch tab
      homepagev2.tabClick("Watch");
      test.log(LogStatus.INFO, "Clicked Watch tab in Tab bar");
      
 	 if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 3)){
		 try{
			watchpagev2.firstItemInCarousal.click();
			test.log(LogStatus.INFO, "Clicked Watch Page First Item In Carosal");
			}catch(Exception e){
			test.log(LogStatus.FAIL, "Unable to click on first item in Carousal of Watch Page");
		 }	
       
 	 }else test.log(LogStatus.FAIL, "Not found First Item in carousal of Watch Tab");
      
        
 	       
		  if(Utilities.explicitWaitVisible(driver,playerpagev2.videoPlayer, 20)) {
					  
					  //Check for progress bar
					  homepagev2.verifyAndProgressBar();		
					  
					  //Pause the video				  
					  playerpagev2.pauseVideo();
						//Check for settings icon	  				  
					  if(Utilities.explicitWaitClickable(driver, playerpagev2.playerSettings, 10)) {
						   
						    test.log(LogStatus.PASS,"Verify the functionality when Cellular Playback button in ON state:");
		   	                BasePageV2.smokeresults("", VK_851, "PASS");
					  }else {
						  
					      test.log(LogStatus.FAIL, "unable to play the video when Cellular Playback button in ON state:");
						  BasePageV2.smokeresults("", VK_851, "FAIL");
					  }
		  }
		  else
			  BasePageV2.reportFail("Progress barc not found on Video player "); 
        
          driver.pressKeyCode(AndroidKeyCode.BACK);
		  Utilities.verticalSwipeDown(driver);
		  
          //Verify download button functionality when 'Cellular Downloads'  button is in 'ON' state:
        
        
		  settingsPageV2.cellularDownloadsSwichON();
		  
//    Verify the Audio Download Quality dropdown functionality:
		  
	        String audioDQ="//android.widget.TextView[@text='Audio Download Quality']";
			  Utilities.verticalSwipeAndFind(driver,audioDQ);
			   
			  if(settingsPageV2.deviceAudiodownloadQualityMedium.getAttribute("enabled").equals("true")) {
				  settingsPageV2.deviceAudioDownloadQualitySpinner.click();
				  WebElement deviceAudiodownloadQualityLow = driver.findElementByXPath("//android.widget.ListView//android.widget.TextView[@text='Low' and @resource-id='com.viacom18.vootkids:id/drop_down_text']");
				  deviceAudiodownloadQualityLow.click();
				  test.log(LogStatus.INFO, "Selected 'Low' Audio download quality in Audio Download Quality Drop down");
				  test.log(LogStatus.PASS,"Verify the Audio Download Quality dropdown functionality:");
				  BasePageV2.smokeresults("", VK_879, "PASS");
				  
			  }else if(settingsPageV2.deviceAudiodownloadQualityHigh.getAttribute("enabled").equals("true")) {
				  settingsPageV2.deviceAudioDownloadQualitySpinner.click();
				  WebElement deviceAudiodownloadQualityMedium = driver.findElementByXPath("//android.widget.ListView//android.widget.TextView[@text='Medium' and @resource-id='com.viacom18.vootkids:id/drop_down_text']");
				  deviceAudiodownloadQualityMedium.click();
				  test.log(LogStatus.INFO, "Selected 'Medium' Audio download quality in Audio Download Quality Drop down");
				  test.log(LogStatus.PASS,"Verify the Audio Download Quality dropdown functionality:");
				  BasePageV2.smokeresults("", VK_879, "PASS");
			  }else if(settingsPageV2.deviceAudiodownloadQualityLow.getAttribute("enabled").equals("true")) {
				  settingsPageV2.deviceAudioDownloadQualitySpinner.click();
				  WebElement deviceAudiodownloadQualityHigh = driver.findElementByXPath("//android.widget.ListView//android.widget.TextView[@text='High' and @resource-id='com.viacom18.vootkids:id/drop_down_text']");
				  deviceAudiodownloadQualityHigh.click();
				  test.log(LogStatus.INFO, "Selected 'High' Audio download quality in Audio Download Quality Drop down");
				  test.log(LogStatus.PASS,"Verify the Audio Download Quality dropdown functionality:");
				  BasePageV2.smokeresults("", VK_879, "PASS");
			  }else {
				  test.log(LogStatus.FAIL, "Not found 'Audio Download Quality' drop down in Device screen");
				  BasePageV2.smokeresults("", VK_879, "FAIL");
			  }
	        
		  //
		  
		  
	      driver.pressKeyCode(AndroidKeyCode.BACK);
		  driver.pressKeyCode(AndroidKeyCode.BACK);
		  driver.pressKeyCode(AndroidKeyCode.BACK);
		  
		  Utilities.verticalSwipeDown(driver);
		  
		  //navigate to Read tab
		  
	       	 homepagev2.tabClick("Read");

	       	 test.log(LogStatus.INFO, "Clicked on Read tab");
	       	 test.log(LogStatus.INFO,"Scrolling to any of the Books Tray");
	       	 String tray="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
	       	 Utilities.verticalSwipe(driver, tray);
	       	
	       	 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTray, 20))
	       	 {

	       		 if(Utilities.explicitWaitClickable(driver, bookspagev2.firstTrayEbook, 20))
	       		 {
	       			 if(Utilities.explicitWaitVisible(driver, bookspagev2.firstTrayEbookTitle, 20))
	       			 {
	       				 ebooktitle = bookspagev2.firstTrayEbookTitle.getText();
	       			 }
	       			 else
	       			 test.log(LogStatus.FAIL, "Book story title is missing");
	       			 
	       			 bookspagev2.firstTrayEbook.click();
	       		 }
	       		 else
	       			 BasePageV2.reportFail("No contents in the tray / Content not clickable");
	       	 }
	       	 else
	       		 BasePageV2.reportFail("Failed to find the tray");
	       	 
	       	 
	       	 test.log(LogStatus.INFO, "Navigating to the Book Detail page -  "+ ebooktitle);
	       	 
	       	 
	       	 String downloadlink="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']";
	       	 Utilities.verticalSwipe(driver, downloadlink);
		  
	      	 if(!Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 5))
		       	 { 
		       	 if(Utilities.explicitWaitClickable(driver, bookspagev2.downloadBookLink, 20))
		       	 {
		       		 Utilities.verticalSwipe(driver);
		       		 bookspagev2.downloadBookLink.click();
		       		 test.log(LogStatus.INFO, "Downloading the Book - "+ebooktitle);
		       	 }
		       	 else
		       		 BasePageV2.reportFail("Not able to click on Download book / Download link not available");
		       	 
		       	 
		       	 if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadingProgress, 20))
		       	 {
		       		 test.log(LogStatus.INFO, "Downloading is in Progress ");
		       	 }
		       	 else
		       		 BasePageV2.reportFail("Book is not downloading after tapping download link");
		       	 
		       	 }
		  
	      	if(Utilities.explicitWaitVisible(driver, bookspagev2.downloadedBookLink, 600))
				 {
					 test.log(LogStatus.INFO, "Succssfully downloaded Read card ");
					 
					 test.log(LogStatus.PASS,"Verify download button functionality when 'Cellular Downloads'  button is in 'ON' state:");
					 BasePageV2.smokeresults("", VK_869, "PASS");

				 }else {
					 test.log(LogStatus.FAIL,"Verify download button functionality when 'Cellular Downloads'  button is in 'ON' state:");
					 BasePageV2.smokeresults("", VK_869, "FAIL");
				 }
		  
		     driver.pressKeyCode(AndroidKeyCode.BACK);
		     Utilities.verticalSwipeDown(driver);
		     Utilities.verticalSwipeDown(driver);
		  
// Turn On the From cellular to WIFI
        driver.setConnection(new ConnectionState(2));
       
        
	}
	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

		
}
