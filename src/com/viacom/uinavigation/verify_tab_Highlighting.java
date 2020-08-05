package com.viacom.uinavigation;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class verify_tab_Highlighting extends BaseTestV2 {
	
	String testName = "verify_tab_Highlighting";
	public static int rowno=0;
	public static int rowno1=0;	
	@SuppressWarnings({ "unchecked", "unchecked" })
	@Test(dataProvider = "getData")
	public void videoPlayback(Hashtable<String, String> data) throws Exception 
	{
		if(GlobalVariables.break_Flag)
			throw new SkipException("Skipping the test as it reaches to Home page");
		test = rep.startTest("Verify highlighting of tab");
		test.log(LogStatus.INFO, "Starting the test for Verify all videos: "+VootConstants.DEVICE_NAME);
		// Check run mode

		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}			
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		 int rowno=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno , "Verify the highlighting of different tabs based on the user`s selection");			
		 int rowno1=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno1 ,"Verify if two or more tabs are highlighted when user switches to different tabs");			
		int rowno2=xls.getRowCount("Smoke_Results")+1;
		xls.setCellData("Smoke_Results", "Testcase Name",rowno2,"Verify if the highlighted tab bar menu is retained when user minimizes and resumes the app");			
		launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);
			//Need to check		
		List<WebElement> tabs=null;
		try{									
			tabs=driver.findElementsByClassName("android.support.v7.app.ActionBar$Tab");
			System.out.println(tabs.size());		
		   }
		catch (Exception e) {}
		//String tabname=null;
		for(int i=0;i<tabs.size();i++)
		{			
			WebElement tab=tabs.get(i);			
			switch(i)
			{  		
			    case 0:
			    	try{ 
			    	if(!Utilities.explicitWaitVisible(driver, homepagev2.mystuff_text, 20))
			       {
			    		String end="//android.widget.TextView[@text='My Stuff']";
				    	Utilities.verticalSwipe(driver, end);
				    	 if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 5))
					       {
					    		BasePageV2.swipetokidslogo(driver);			    		
					       }
				    	 
				    	 if(homepagev2.mystuff_tab.isSelected())
				    	  {
				    		  test.log(LogStatus.INFO, "Navigation to (My Stuff) is passed");
						        //BasePageV2.takeScreenshot();
								try{driver.runAppInBackground(Duration.ofSeconds(3));
								}catch (Exception e) {}
								WebElement e1=driver.findElementByXPath("//android.view.ViewGroup");
						    	Utilities.horizontalSwipeForTray(driver, e1);
				    	  }
				    	  else test.log(LogStatus.FAIL, "Navigation to (My Stuff) is failed");
			       }
			      else{
			    	 
			    	  test.log(LogStatus.FAIL, "Navigation to (My Stuff) is failed");
			      
			      }
				    
				     }
				catch (Exception e) {test.log(LogStatus.FAIL, "Navigation to (My Stuff) is failed");}
			    case 1:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 10))
			    	{
			    	if(homepagev2.watch_tab.isSelected())
			    	test.log(LogStatus.INFO, "Navigation to (Watch) is passed");
			    	//BasePageV2.takeScreenshot();
					try{driver.runAppInBackground(Duration.ofSeconds(3));
					}catch (Exception e) {}
			    	WebElement e1=driver.findElementByXPath("//android.view.ViewGroup");
			    	Utilities.horizontalSwipeForTray(driver, e1);
			    	}
			       else
						test.log(LogStatus.FAIL, "Navigation to (Watch) is failed");
				     }
				catch (Exception e) {}
			    case 2:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.read_tab, 10))
			    	{
			    	if(homepagev2.read_tab.isSelected())
			    	test.log(LogStatus.INFO, "Navigation to (read) is passed");
			    //	BasePageV2.takeScreenshot();
					try{driver.runAppInBackground(Duration.ofSeconds(3));
					}catch (Exception e) {}
			    	WebElement e1=driver.findElementByXPath("//android.view.ViewGroup");
			    	Utilities.horizontalSwipeForTray(driver, e1);
			    	}
			       else
						test.log(LogStatus.FAIL, "Navigation to (read)tab is failed");
				     }
				catch (Exception e) {}
			    case 3:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.Listen_tab, 10))
			    	{
			    	if(homepagev2.Listen_tab.isSelected())
				    test.log(LogStatus.INFO, "Navigation to (Listen) is passed");
				  //  BasePageV2.takeScreenshot();
					try{driver.runAppInBackground(Duration.ofSeconds(3));
					}catch (Exception e) {}
			    	WebElement e1=driver.findElementByXPath("//android.view.ViewGroup");
			    	Utilities.horizontalSwipeForTray(driver, e1);
			    	}
				       else
							test.log(LogStatus.FAIL, "Navigation to (read)tab is failed");					     
				     }
				catch (Exception e) {}
			    case 4:
			    	try{ 
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.search_tab, 10))
			    	{
			    	homepagev2.search_tab.click();	
			    	if(homepagev2.search_tab.isSelected())
			    	test.log(LogStatus.INFO, "Navigation to (Search) is passed");
			    //	BasePageV2.takeScreenshot();
					try{driver.runAppInBackground(Duration.ofSeconds(3));
					}catch (Exception e) {}
			    	}
			       else
						test.log(LogStatus.FAIL, "Navigation to (Search) is failed");
				     }
				catch (Exception e) {}			
			}
			if(i<5)
   				break;
		}		
        homepagev2.smokeresults("",rowno1, "pass");		
        homepagev2.smokeresults("",rowno1, "pass");  
        homepagev2.smokeresults("",rowno2, "pass");      			

	}		
		@DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					
		}
}
	

