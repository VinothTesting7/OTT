package com.viacom.chromecast;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;
//Author Tanisha
public class VerifyChromecastCellular extends BaseTestV2{
	
	String testName = "VerifyChromecastCellular";
	@Test(dataProvider = "getData")
	public void VerifyChromecastCellular(Hashtable<String, String> data) throws Exception 
	{		
		int errCount=0;
		test = rep.startTest("VerifyChromecastCellular: Verify Chromecast related test cases on Cellular network");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		
		Xls_Reader xls1159 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1159=xls1159.getRowCount("Smoke_Results")+1;
		xls1159.setCellData("Smoke_Results", "Testcase Name",rowno1159,"Validate the chromecast icon availability in Top menu bar when user is in cellular nework");
		
		Xls_Reader xls1162 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1162=xls1162.getRowCount("Smoke_Results")+1;
		xls1162.setCellData("Smoke_Results", "Testcase Name",rowno1162,"Validate chromecast icon availability on the player when user is in cellular nework");
		
		Xls_Reader xls1216 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		int rowno1216=xls1216.getRowCount("Smoke_Results")+1;
		xls1216.setCellData("Smoke_Results", "Testcase Name",rowno1216,"Validate chromecast icon availability on the Audio player when user is in cellular nework");
		
		
		test.log(LogStatus.INFO, "Starting testcase Validate the chromecast icon availability in Top menu bar when user is in cellular nework");
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 homepagev2.login(data.get("Email"),data.get("Password"));
		//Verify the icon in top menu bar 
		 if(Utilities.explicitWaitVisible(driver, homepagev2.chromecastIconDisconnected, 10)) {
			 test.log(LogStatus.FAIL, "Chromecast icon is displayed for Cellular network");
			 homepagev2.smokeresults("Validate the chromecast icon availability in Top menu bar when user is in cellular nework",rowno1159, "FAIL");
			 test.log(LogStatus.FAIL, "Validate the chromecast icon availability in Top menu bar when user is in cellular nework is Fail");
			 BasePageV2.takeScreenshot();
		 }
		 else {
			 test.log(LogStatus.INFO, "Chromecast icon is displayed for Cellular network");
			 homepagev2.smokeresults("Validate the chromecast icon availability in Top menu bar when user is in cellular nework",rowno1159, "PASS");
			 homepagev2.reportPass("Validate the chromecast icon availability in Top menu bar when user is in cellular nework is Pass");
		 }
		//Launch player and verify the availability
		homepagev2.tabClick("Watch");
		if(Utilities.explicitWaitClickable(driver, watchpagev2.firstItemInCarousal, 10)) {
			watchpagev2.firstItemInCarousal.click();
			test.log(LogStatus.INFO, "Clicked on the first item in Watch Carousal");
			//System.out.println(driver.getPageSource());
			for(int wait=0;wait<=10;wait++) {
				try {
					watchpagev2.watchFirstItemPlayer.click();
					try {
						watchpagev2.watchFirstItemPlayerPause.click();
						if(Utilities.explicitWaitVisible(driver, watchpagev2.playerCastIconDisconnected, 10)) {
							test.log(LogStatus.FAIL, "Chromecast icon is displayed");
							homepagev2.smokeresults("Validate chromecast icon availability on the player when user is in cellular nework",rowno1162, "FAIL");
							test.log(LogStatus.FAIL, "Validate chromecast icon availability on the player when user is in cellular nework is Fail");
							BasePageV2.takeScreenshot();
							break;
						}
						else {
							test.log(LogStatus.INFO, "Chromecast icon is not displayed");
							homepagev2.smokeresults("Validate chromecast icon availability on the player when user is in cellular nework",rowno1162, "PASS");
							homepagev2.reportPass("Validate chromecast icon availability on the player when user is in cellular nework is Pass");
							break;
						}
						
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Unable to click on the Pause button of the player");
						break;
					}
				}
				catch(Exception e) {
					Thread.sleep(3000);
				}
			}
		}
		else {
			test.log(LogStatus.FAIL, "Unable to click on the first item in Watch Carousal");
		}
		//Icon availabilty in Audio player
		driver.navigate().back();
		Utilities.verticalSwipeReverse(driver);
		homepagev2.tabClick("Listen");
		if(Utilities.explicitWaitClickable(driver, listenpagev2.firstItemCarousal, 10)) {
			try {
				listenpagev2.firstItemCarousal.click();
				test.log(LogStatus.INFO, "Clicked on the first item in Listen Carousal");
				if(Utilities.explicitWaitClickable(driver, listenpagev2.firstItemCarousalPlay, 10)) {
					try {
						listenpagev2.firstItemCarousalPlay.click();
						test.log(LogStatus.INFO, "Clicked on PLAY button");
						for(int wait=0;wait<15;wait++) {
							Thread.sleep(2000);
							if(Utilities.explicitWaitVisible(driver, listenpagev2.firstItemCarousalPausePlay, 20)) {
								while(listenpagev2.playerDurationElapsed.getText().trim().equals("00:00")) {
									Thread.sleep(2000);
								}
								try {
									listenpagev2.firstItemCarousalPausePlay.click();
								}
								catch(Exception e) {}
								try {
									if(Utilities.explicitWaitVisible(driver, listenpagev2.playerCastIconDisconnected, 10)){
										test.log(LogStatus.FAIL, "Chromecast icon is displayed");
										homepagev2.smokeresults("Validate chromecast icon availability on the Audio player when user is in cellular nework",rowno1216, "FAIL");
										test.log(LogStatus.FAIL, "Validate chromecast icon availability on the Audio player when user is in cellular nework is Fail");
										BasePageV2.takeScreenshot();
										break;
									}
									else {
										test.log(LogStatus.INFO, "Chromecast icon is not displayed");
										homepagev2.smokeresults("Validate chromecast icon availability on the Audio player when user is in cellular nework",rowno1216, "PASS");
										test.log(LogStatus.PASS, "Validate chromecast icon availability on the Audio player when user is in cellular nework is Pass");
										BasePageV2.takeScreenshot();
										break;
									}
								}
								catch(Exception e) {
									test.log(LogStatus.INFO, "Chromecast icon is not displayed");
									homepagev2.smokeresults("Validate chromecast icon availability on the Audio player when user is in cellular nework",rowno1216, "PASS");
									test.log(LogStatus.PASS, "Validate chromecast icon availability on the Audio player when user is in cellular nework is Pass");
									BasePageV2.takeScreenshot();
									break;
								}
							}
							else {
								Thread.sleep(3000);
								if(wait==15) {
									test.log(LogStatus.FAIL, "Failed to launch the player");
									break;
								}
							}
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to click on PLAY button");	
						BasePageV2.takeScreenshot();
					}
				}	
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to click on first item in Listen Carousal");
			}
		}
		else {
			test.log(LogStatus.FAIL, "First item in carousal is not clickable");
		}
		
	}
		@DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);						
		}
	}
