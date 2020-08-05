package com.viacom.favourites;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class Favourites_scripts extends BaseTestV2 {
	
	String testName = "Favourites_scripts";
	public static int rowno=0;
	public static int rowno1=0;
	public static int rowno2=0;

	
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{
		if(GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Verify Favourites");
		test.log(LogStatus.INFO, "Starting the test to Verify Favourites_scripts: "+VootConstants.DEVICE_NAME);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}	
		
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno , "Verify the availability of favourites tray when user is not favourited any episode/book/audiobook");	
		
		Xls_Reader xls1 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno1=xls1.getRowCount("Smoke_Results")+1;
		xls1.setCellData("Smoke_Results", "Testcase Name",rowno1 ,"Verify the availibility of favourite icon in show detail page");	
		
		Xls_Reader xls2 = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno2=xls1.getRowCount("Smoke_Results")+1;
		xls1.setCellData("Smoke_Results", "Testcase Name",rowno2 ,"Verify the availibility of favourite icon in book detail page");	
		
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		
		String Favourites=" ",popular_books=" ";
		
		if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_text, 20))
	       {
	       test.log(LogStatus.INFO, "Navigated to My stuff to verify Just for you section");
	       }
	       else
				test.log(LogStatus.FAIL, "Navigation to My stuff is failed");	  						
		
		try{
	        String text="FAVOURITESabc";
			  test.log(LogStatus.PASS, "Section title FAVOURITES is present");		
	     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	        Utilities.verticalSwipe(driver);
			 //BasePageV2.takeScreenshot();
			 Favourites= homepagev2.favourites_text.getText().trim();
	          homepagev2.smokeresults("Verify the availability of favourites tray when user is not favourited any episode/book/audiobook", rowno, "Pass");
	        }
	        catch (Exception e)
		     {
	           homepagev2.reportFail("Unable to find FAVOURITES section");
	          }
		
		for(int i=0;i<=5;i++)
		{
			if(!Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 3))
				Utilities.verticalSwipeDown(driver);
			else
				if(Utilities.explicitWaitVisible(driver, homepagev2.watch_tab, 3))
					break;
		}
		
		try{ 
	    	if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10))			    	
	    		homepagev2.watch_tab.click();	
	    	if(Utilities.explicitWaitVisible(driver, homepagev2.watchtab_text, 20))
	       {
	       test.log(LogStatus.INFO, "Navigating to (Watch) page to Verify the availibility of favourite icon in show detail page");
	       //BasePageV2.takeScreenshot();
	       }
	       else
				test.log(LogStatus.FAIL, "Navigating to (Watch) page is failed");
		     }
		catch (Exception e) {}
		
		try{
	        String text="ALL CHARACTERSabc";
			  test.log(LogStatus.INFO, "Navigated to All Charecters section");		
	     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	        Utilities.verticalSwipe(driver);
			// BasePageV2.takeScreenshot();
			 Favourites= homepagev2.allcharecters_text.getText().trim();
				if(Utilities.explicitWaitClickable(driver, homepagev2.allcharecters_firstcharecter, 5))
				{
					homepagev2.allcharecters_firstcharecter.click();
					test.log(LogStatus.INFO, "Navigated to show details Page");
					if(Utilities.explicitWaitVisible(driver, homepagev2.fav_btn, 10))
					{
						test.log(LogStatus.FAIL, "Favourite button present in show detail page");
						BasePageV2.takeScreenshot();
					}
					else
					{
						test.log(LogStatus.PASS, "Favourite button is not their in show detail page");
						//BasePageV2.takeScreenshot();
						homepagev2.smokeresults("Verify the availibility of favourite icon in show detail page", rowno1, "Pass");
					}
					if(Utilities.explicitWaitClickable(driver, homepagev2.bckbtn, 10))
						homepagev2.bckbtn.click();
					else
						test.log(LogStatus.FAIL, "Unable to find back button inside show details page");
					
					for(int i=0;i<=5;i++)
					{
						if(!Utilities.explicitWaitVisible(driver, homepagev2.read_tab, 3))
							Utilities.verticalSwipeDown(driver);
						else
							if(Utilities.explicitWaitVisible(driver, homepagev2.read_tab, 3))
							{
								homepagev2.read_tab.click();
								 String text1="MOST POPULAR EBOOKSabc";
								  test.log(LogStatus.INFO, "Navigated to Popular books to Verify the availibility of favourite icon in book detail page");		
						     driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text1+"\").instance(0))");
						     Utilities.verticalSwipe(driver);
						//	 BasePageV2.takeScreenshot();
							 popular_books= homepagev2.popular_books.getText().trim();
								if(Utilities.explicitWaitVisible(driver, homepagev2.popular_books, 5))
								break;
							}
					}
				}
				else
					test.log(LogStatus.FAIL, "Unable to find charecter in All Charecter section");
	        }
	        catch (Exception e) {
	                  test.log(LogStatus.FAIL, "Unable to find ALL CHARACTER section");}
		
		
		if(Utilities.explicitWaitVisible(driver, homepagev2.popular_books, 10))
		{
			if(Utilities.explicitWaitClickable(driver, homepagev2.popular_books_firstcharecter, 10))
			{
				homepagev2.popular_books_firstcharecter.click();
				
				if(Utilities.explicitWaitVisible(driver, homepagev2.fav_btn, 10))
				{
					test.log(LogStatus.PASS, "Favourite button present in book detail page");
					//BasePageV2.takeScreenshot();
				}
				else
				{
					test.log(LogStatus.FAIL, "Favourite button is not their in book detail page");
					BasePageV2.takeScreenshot();
					homepagev2.smokeresults("Verify the availibility of favourite icon in book detail page", rowno2, "Pass");
				}
				if(Utilities.explicitWaitClickable(driver, homepagev2.bckbtn, 10))
					homepagev2.bckbtn.click();
				else
					test.log(LogStatus.FAIL, "Unable to find back button inside book details page");
			}
			else
				test.log(LogStatus.FAIL, "Unable to find Book charecter under most popular section");
			
			
		}

			}		
				@DataProvider
				public Object[][] getData(){
					return DataUtil.getData(testName,xls);
							
				}
		}
			
