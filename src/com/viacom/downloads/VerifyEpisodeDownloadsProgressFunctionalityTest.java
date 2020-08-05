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

public class VerifyEpisodeDownloadsProgressFunctionalityTest extends BaseTestV2 {

	String testName = "VerifyEpisodeDownloadsProgressFunctionalityTest";
	@Test(dataProvider = "getData")
	public void loginTest(Hashtable<String, String> data) throws Exception{
	if(GlobalVariables.break_Flag)
		throw new SkipException("Skipping the test as it reaches to Home page");
	test = rep.startTest("Verify Episode Downloads in Progress Functionality");
	test.log(LogStatus.INFO, "Starting the test for Episode Downloads in Progress Functionality - "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadrow=xls.getRowCount("Smoke_Results")+1;
	xls.setCellData("Smoke_Results", "Testcase Name",downloadrow, "Verify if Downloading content from 'Download Episodes' is reflected in Downloads tab");	
	

/*	Xls_Reader xls5 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedmetadatarow=xls5.getRowCount("Smoke_Results")+1;
	xls5.setCellData("Smoke_Results", "Testcase Name",downloadedmetadatarow, "Verify the metadata of completely downloaded cards under My Stuff - Downloads tab:");
	
	
	Xls_Reader xls6 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
	int downloadedplaybackrow=xls6.getRowCount("Smoke_Results")+1;
	xls6.setCellData("Smoke_Results", "Testcase Name",downloadedplaybackrow, "Verify that Audio player launches when tapping completely downloaded Audio card from Downloads tab");*/
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
     ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
     WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
     ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
     DownloadsPageV2 downloadspagev2=new DownloadsPageV2(driver,test);
	 String timebefPause="";
	 
	 downloadspagev2.setDeviceDownloadQuality("high");
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

	    if(Utilities.explicitWaitClickable(driver, showspagev2.downloadEpisodesScreenTitle, 20))
		 {
	    	test.log(LogStatus.INFO, "Navigated to Download Episodes Screen");	    	
	    	test.log(LogStatus.INFO, "Clicking on Download Icon for all the episodes of show - ");
	    	
	    	  if(Utilities.explicitWaitClickable(driver, showspagev2.downloadIconInDownloadsScreen, 20))
	    	  {
	    		   size=showspagev2.downloadIconsInDownloadsScreen.size();
	    		  for(int i=0;i<size;i++)
	       		  {
	       			  try{
	       				  String title=showspagev2.downloadsScreenEpisodeTitles.get(i).getText().trim();
	       				  downloadTitles.add(title);   				
	       				  test.log(LogStatus.INFO, title);
	       			  }
	       			  catch(Exception e){}
	       		  }
	    		  for(int i=0;i<size;i++)
	    		  {
	    			  try{
	    				  showspagev2.downloadIconInDownloadsScreen.click();
	    			  }
	    			  catch(Exception e){}
	    		  }
	    		  
	    		
	    	  }
	    	  else
	    		  BasePageV2.reportFail("Download icon not present");
	    	
	    	
	    	
		 }
		 else
			 BasePageV2.reportFail("Not navigated to Download Episodes Screen");
		
	    
	    driver.navigate().back();
	    Thread.sleep(1000);
	    driver.navigate().back();
	    Thread.sleep(1000);
	    
	    
	    Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
		 if(Utilities.explicitWaitClickable(driver, homepagev2.mystuff_tab, 20))
		 {
			 Thread.sleep(1000);
			 test.log(LogStatus.INFO, "Navigating to My Stuff Tab");
			 homepagev2.mystuff_tab.click();
			 Thread.sleep(1000);
		 }
		 else
			 BasePageV2.reportFail("Failed to click on My Stuff Tab");
		 
		 
		 test.log(LogStatus.INFO, "Scrolling to Downloads tab");
		 
		 Utilities.verticalSwipe(driver, homepagev2.downloadsTab);

			if (Utilities.explicitWaitClickable(driver, homepagev2.downloadsTab, 5))
			homepagev2.downloadsTab.click();
			else
			BasePageV2.reportFail("Failed to find Downloads Tab in My Stuff");
	
			Utilities.verticalSwipe(driver);
			    if (Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsMystuff, 5))
				downloadspagev2.editDownloadsMystuff.click();
				else
				BasePageV2.reportFail("Failed to find edit Downloads Tab in My Stuff");
		    
			    
			    if(Utilities.explicitWaitClickable(driver, downloadspagev2.editDownloadsScreen, 5))
				{
			    	List<String> titles =new LinkedList<String>();
			    	for(int i=0;i<downloadspagev2.downloadedContentTitles.size();i++)
			    	{
			    		titles.add(downloadspagev2.downloadedContentTitles.get(i).getText().trim());
			    	}
			    	for(int i=0;i<downloadTitles.size();i++)
			    	{
			    	        if(!titles.contains(downloadTitles.get(i)))
			    	        BasePageV2.reportFail("Episode Title - "+downloadTitles.get(i)+" is not found in Edit Downloads Screen");
			    	}
				}
				else
				BasePageV2.reportFail("Not navigated to Edit Downloads Screen");
		    
		test.log(LogStatus.PASS,"Testcase : 'Verify if Downloading content from 'Download Episodes' is reflected in Downloads tab' is Passed");
		BasePageV2.smokeresults("", downloadrow, "Pass"); 
	
		for(int i=0;i<size;i++)
		{
		     try{
					if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteDownload, 2))
					{
					try{
						Thread.sleep(1000);
						driver.findElementByXPath("//android.widget.CheckBox").click();
						
						if (Utilities.explicitWaitVisible(driver, downloadspagev2.deleteTitle, 2))
							downloadspagev2.deleteTitle.click();
						else test.log(LogStatus.FAIL, "'Delete CTA' not displayed");

						if (Utilities.explicitWaitVisible(driver, downloadspagev2.ConfirmDeleteDownload, 2))
							downloadspagev2.ConfirmDeleteDownload.click();
						else test.log(LogStatus.FAIL, "'Confirmation Popup' to delete download not displayed");
						
					}
					catch(Exception e){}
					
					}
		    	 
		     }
		     catch(Exception e)
		     {
		    	 
		     }
		}
 }			 

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);
				
	
	}

}