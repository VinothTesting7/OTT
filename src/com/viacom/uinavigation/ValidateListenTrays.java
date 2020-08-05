package com.viacom.uinavigation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.glassfish.grizzly.compression.lzma.impl.Base;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

public class ValidateListenTrays extends BaseTestV2{
	
	String testName = "ValidateListenTrays";
	
	@Test(dataProvider = "getData")
	public void VerifyTraysinListentab(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("ValidateListenTrays");
		test.log(LogStatus.INFO, "Starting the test to Verify Listen Tab Trays "+VootConstants.DEVICE_NAME);
	// Check run mode
	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	    //Declarations
	    ArrayList<String> tabsfromAPI = new ArrayList<String>();
	    ArrayList<String> traysofListentab_fromAPI = new ArrayList<String>();
	    ArrayList<String> titles_fromAPI = new ArrayList<String>();
	    ArrayList<String> Author_fromAPI = new ArrayList<String>();
	    ArrayList<Integer> mediaids_fromAPI = new ArrayList<Integer>();	 
	    WebElement titleFE = null;
	    WebElement traytitlename=null;
	    int a,rowno,trays,totalItems=0;	    
	    String end,traynamefromAPI,titleTextFE="";
	    boolean temp,temp1=true;
	    launchApp();
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		HomePageV2 homepagev2=new HomePageV2(driver,test);	
		ListenPageV2 listepagev2= new ListenPageV2(driver,test);
		String testCase11,testCase22,testCase33,testCase44,testCase55,testCase66;
		//Printing TC Names in Excel
	    Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
	    String testCase1 = "'Validate media type in the tray'";
		String testCase2 = "'Validate tapping on trayname'";
		String testCase3 = "'Validate SEE ALL Visibility'";
		String testCase4 = "'Verify Metadata for the tray'";
		String testCase5 = "'Validate ShowDetailPage Navigation'";
		String testCase6="'Validate SEE ALL button Navigation'";
	   rowno = xls.getRowCount("Smoke_Results") + 1;
	   xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase1);
	   xls.setCellData("Smoke_Results", "Testcase Name", rowno+1, testCase2);
	   xls.setCellData("Smoke_Results", "Testcase Name", rowno+2, testCase3);
	   //xls.setCellData("Smoke_Results", "Testcase Name", rowno+3, testCase4);
	   xls.setCellData("Smoke_Results", "Testcase Name", rowno+4, testCase5);
	   xls.setCellData("Smoke_Results", "Testcase Name", rowno+5, testCase6);
	   homepagev2.login("rishrosh@gmail.com", "rishrosh100");
	   homepagev2.tabClick("Listen");
		 int rows=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows;rNum++)
		 {
			 String apiname=xls.getCellData("Api", "API Name", rNum);
			 if(apiname.equals("Listentab_alltrays")){
				String  url_Listentab_alltrays=xls.getCellData("Api", "Url", rNum);
				System.out.println(url_Listentab_alltrays);
				 Response resp_Listentab_alltrays=Utilities.requestUtility(url_Listentab_alltrays);
				 trays=resp_Listentab_alltrays.jsonPath().get("assets.title.size()");
				 System.out.println(trays);
				 for(int i=0;i<trays;i++)			
				 {
					String tarytitles=resp_Listentab_alltrays.jsonPath().get("assets["+i+"].title");
					 traysofListentab_fromAPI.add(tarytitles);
				 }				 
				 for(int i=1;i<trays;i++)
				 {	 
					 titles_fromAPI.clear();
					 Author_fromAPI.clear();
					 mediaids_fromAPI.clear();
				 totalItems=resp_Listentab_alltrays.jsonPath().get("assets["+i+"].totalItems");	
				 for(int j=0;j<totalItems;j++)
					{
					 String titles=resp_Listentab_alltrays.jsonPath().get("assets["+i+"].assets[0].items["+j+"].title");					 
					 titles_fromAPI.add(titles);
					 String Authors=resp_Listentab_alltrays.jsonPath().get("assets["+i+"].assets[0].items["+j+"].author");					 
					 Author_fromAPI.add(Authors);
					 int mediaids=resp_Listentab_alltrays.jsonPath().get("assets["+i+"].assets[0].items["+j+"].mediaType");
					 mediaids_fromAPI.add(mediaids);
					}	
				 System.out.println(titles_fromAPI);
				 System.out.println(Author_fromAPI);
				 System.out.println(mediaids_fromAPI);
				 //verifying media id for all the contents in the tray
				 int t=0;
				 for(int mtype : mediaids_fromAPI)
				 {
					if( mtype!=540)
					{
						test.log(LogStatus.FAIL, "Media is invalid for audio in the API data for the tray: "+traysofListentab_fromAPI.get(i-1));
					     temp=false;
					     testCase11=testCase1+" "+traysofListentab_fromAPI.get(i-1);
					     xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase11);
	       				   BasePageV2.smokeresults(testCase11, rowno, "FAIL");
					}
					if(t==mediaids_fromAPI.size()-1 && (temp=true))
					{
                       test.log(LogStatus.PASS, "Test Case: "+testCase1+" "+traysofListentab_fromAPI.get(i-1));
                       testCase11=testCase1+" "+traysofListentab_fromAPI.get(i-1);
                       xls.setCellData("Smoke_Results", "Testcase Narme", rowno, testCase11);
       				   BasePageV2.smokeresults(testCase11, rowno, "Pass");
					}
					t++;
						
				 }
							 traynamefromAPI=traysofListentab_fromAPI.get(i).toUpperCase();
							 //Swiping the current tray
							 end="//android.widget.TextView[@text='"+traynamefromAPI+"' and @resource-id='com.viacom18.vootkids:id/recent_title_text']";
							 Utilities.verticalSwipe(driver, end);
							 //Navigated to current tray
							 try
							 {
							 traytitlename=driver.findElementByXPath("//android.widget.TextView[@text='"+traynamefromAPI+"']");
							 traytitlename.click();
							 }catch(Exception e)
							 {
								 test.log(LogStatus.FAIL, "Failed to click on the tray title"+traynamefromAPI);
								 homepagev2.takeScreenshot();
							 }
							 if(totalItems<=8)
							 
								 if(!Utilities.explicitWaitClickable(driver, listepagev2.back_btn, 1))
								 {
									testCase22=testCase2+" "+traysofListentab_fromAPI.get(i-1);
								   test.log(LogStatus.INFO, "Verified Tapping on title when card size is less or equal to 8");
								   
								   xls.setCellData("Smoke_Results", "Testcase Name", rowno+1, testCase22);
			       				   homepagev2.smokeresults(testCase22, rowno+1, "Pass");
			       				   
								 }
								 else
								 {
									 test.log(LogStatus.FAIL, "Navigated to 'SEE ALL' eventhough card size is less or equal to 8");
									 launchPageV2.takeScreenshot();
									testCase22=testCase2+" "+traysofListentab_fromAPI.get(i-1);
									 xls.setCellData("Smoke_Results", "Testcase Name", rowno+1, testCase22);
				       				   homepagev2.smokeresults(testCase22, rowno+1, "Fail");
									 launchPageV2.backButton.click();
								 }
							 else
							 {
								 if(Utilities.explicitWaitClickable(driver, listepagev2.back_btn, 1))
								 {
									 test.log(LogStatus.INFO, "Verified Tapping on title when card size is > 8");
									 launchPageV2.takeScreenshot();
									 testCase22=testCase2+" "+traysofListentab_fromAPI.get(i-1);
									 xls.setCellData("Smoke_Results", "Testcase Name", rowno+1, testCase22);
				       				   homepagev2.smokeresults(testCase22, rowno+1, "Pass");
									 launchPageV2.backButton.click();
								 }
									 else
									 {
									 test.log(LogStatus.FAIL, "Not Navigated to 'SEE ALL' eventhough card size is > 8");
									 launchPageV2.takeScreenshot();
									 testCase22=testCase2+" "+traysofListentab_fromAPI.get(i-1);
									 xls.setCellData("Smoke_Results", "Testcase Name", rowno+1, testCase22);
				       				   homepagev2.smokeresults(testCase22, rowno+1, "Fail");
									 }	
							 }
							 
							 
                              // verified the tray title : need to double check 
								//test.log(LogStatus.PASS, traynamefromAPI+"= tray name is displayed");	
							 //When the items are less than or equal to 8 
							 if(totalItems<=8)
							 {		
							Utilities.verticalSwipe(driver);
							if(!Utilities.explicitWaitClickable(driver, listepagev2.seeAll, 1))
							{
								test.log(LogStatus.PASS, "See all is not visible ");
								testCase3="Validate SEE ALL is not visible for the tray: ";
								launchPageV2.takeScreenshot();
								testCase33=testCase3+" "+traysofListentab_fromAPI.get(i-1);
								xls.setCellData("Smoke_Results", "Testcase Name", rowno+2, testCase33);
			       				   homepagev2.smokeresults(testCase33, rowno+2, "PASS");	   
							}
							else
							{
								test.log(LogStatus.FAIL,"See all is visible eventhough card size is less or equal to 8");
								BasePageV2.takeScreenshot();
								testCase33=testCase3+" "+traysofListentab_fromAPI.get(i-1);
								xls.setCellData("Smoke_Results", "Testcase Name", rowno+2, testCase33);
			       				homepagev2.smokeresults(testCase33, rowno+2, "FAIL");	
							}
							if(Utilities.explicitWaitClickable(driver, traytitlename, 10))
							{
							 end="//android.widget.TextView[@text='"+traynamefromAPI+"' and @resource-id='com.viacom18.vootkids:id/recent_title_text']";
							 System.out.println(end);
							 Utilities.verticalSwipeDown(driver);
							}
					
							try
							{
							titleFE=driver.findElementByXPath("//android.widget.TextView[@text='"+traynamefromAPI+"']/../..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']");
							titleTextFE =titleFE.getText();
							}
							catch(Exception e)
							{
								test.log(LogStatus.FAIL,"Failed to get title from front end");
								BasePageV2.takeScreenshot();
							}
							for(int b=0;b<titles_fromAPI.size();b++)
							{		
								try{
								if(driver.findElementByXPath("//android.widget.TextView[@text='"+titles_fromAPI.get(b)+"']").isDisplayed())
							//if(driver.findElementByXPath("(//android.widget.TextView[@text='"+titles_fromAPI+"'])["+b+"]").isDisplayed())										
								if(driver.findElementByXPath("//android.widget.TextView[@text='"+Author_fromAPI.get(b)+"']").isDisplayed())
								{}
								
								}
								catch (Exception e) {
									testCase55=testCase5+" "+traysofListentab_fromAPI.get(i-1);
									launchPageV2.takeScreenshot();
									xls.setCellData("Smoke_Results", "Testcase Name", rowno+4, testCase55);
									test.log(LogStatus.FAIL,"Failed to verify metadata");
									homepagev2.smokeresults(testCase55, rowno+4, "FAIL");
									temp1=false;
									
								}
							}
							if(temp1)
							{
								testCase55=testCase5+" "+traysofListentab_fromAPI.get(i-1);
								xls.setCellData("Smoke_Results", "Testcase Name", rowno+4, testCase55);
								launchPageV2.takeScreenshot();
								
								homepagev2.smokeresults(testCase55, rowno+4, "PASS");
							}
						
							if(Utilities.explicitWaitClickable(driver, titleFE, 10))
							{
								titleFE.click();
								if(Utilities.explicitWaitVisible(driver, listepagev2.play_btn, 10))
								{
									testCase66=testCase6+" "+traysofListentab_fromAPI.get(i-1);
									xls.setCellData("Smoke_Results", "Testcase Name", rowno+5, testCase66);
									launchPageV2.takeScreenshot();
									test.log(LogStatus.PASS,"testCase66");
									homepagev2.smokeresults(testCase66, rowno+5, "PASS");
									try
									{
									String title_inside=listepagev2.title_inside_showdetailPage.getText().trim();
									System.out.println(title_inside);
									if(listepagev2.play_btn.isDisplayed())
									test.log(LogStatus.INFO, "Play button in displayed inside Program info page");
										
									if(titleTextFE.contains(title_inside))
									test.log(LogStatus.INFO, "Title is verified in program info page");	
								//	BasePageV2.takeScreenshot();
									testCase44=testCase4+" "+traysofListentab_fromAPI.get(i-1);
									xls.setCellData("Smoke_Results", "Testcase Name", rowno+3, testCase44);
									homepagev2.smokeresults(testCase44, rowno+3, "PASS");	
									
									}
									catch(Exception e)
									{
										testCase44=testCase4+" "+traysofListentab_fromAPI.get(i-1);
										xls.setCellData("Smoke_Results", "Testcase Name", rowno+3, testCase44);
										test.log(LogStatus.FAIL, "Failed to verify metadata in Program info page");	
										homepagev2.smokeresults(testCase44, rowno+3, "FAIL");	
									}
									if(Utilities.explicitWaitClickable(driver, listepagev2.back_btn, 5))
									listepagev2.back_btn.click();
									else
										test.log(LogStatus.FAIL, "Failed to navigate back from program info page");
								}
								else
								{
									//temporarily adding below line need to remove this later
									listepagev2.back_btn.click();
									test.log(LogStatus.FAIL, "Failed to Navigate to Program info page");
									BasePageV2.takeScreenshot();
									testCase55=testCase5+" "+traysofListentab_fromAPI.get(i-1);
									xls.setCellData("Smoke_Results", "Testcase Name", rowno+4, testCase55);
									homepagev2.smokeresults(testCase55, rowno+4, "FAIL");
								}
							}
							else
								{
								test.log(LogStatus.FAIL, "Unable to tap on tray: "+traynamefromAPI);
								BasePageV2.takeScreenshot();
								}
							 }//end of if when items is less or equal to8
							 else if(totalItems>8)
							 {
								 String end1="//android.widget.Button[@text='SEE ALL']";
								    Utilities.verticalSwipe(driver, end1);
								    if(Utilities.explicitWaitClickable(driver, listepagev2.seeAll, 10))
								    {
								    	listepagev2.seeAll.click();
								    	test.log(LogStatus.PASS,"'SEE ALL' button is visible as there are more than 8 contents");
								    	testCase3="Validate SEE ALL is visible for the tray: ";
								    	testCase33=testCase3+" "+traysofListentab_fromAPI.get(i-1);
								    	xls.setCellData("Smoke_Results", "Testcase Name", rowno+2, testCase33);
										homepagev2.smokeresults(testCase33, rowno+2, "PASS");
										
								    }
								    else
								    {
								    	BasePageV2.reportFail("'SEE ALL' button is not visible eventhough there are More than 8 comntents");
								    	testCase3="Validate SEE ALL is visible for the tray: ";
								    	testCase33=testCase3+" "+traysofListentab_fromAPI.get(i-1);
								    	xls.setCellData("Smoke_Results", "Testcase Name", rowno+2, testCase33);
										homepagev2.smokeresults(testCase33, rowno+2, "FAIL");
								    }
								    if(Utilities.explicitWaitClickable(driver, listepagev2.back_btn, 10))	
								    {
										 test.log(LogStatus.INFO, "Successfully Navigated to 'SEE ALL' list for the tray");
										 testCase66=testCase6+" "+traysofListentab_fromAPI.get(i-1);
										 xls.setCellData("Smoke_Results", "Testcase Name", rowno+5, testCase66);
											homepagev2.smokeresults(testCase66, rowno+5, "PASS");
								    }
									 else
									 {
										 test.log(LogStatus.FAIL, "Unable to  Navigate to 'SEE ALL' list for the tray");
										 BasePageV2.takeScreenshot();
										 testCase66=testCase6+" "+traysofListentab_fromAPI.get(i-1);
										 xls.setCellData("Smoke_Results", "Testcase Name", rowno+5, testCase66);
										 homepagev2.smokeresults(testCase66, rowno+5, "PASS");
									 }
								    for(int b=0;b<titles_fromAPI.size();b++)
									{		
										try{
										if(driver.findElementByXPath("//android.widget.TextView[@text='"+titles_fromAPI.get(b)+"']").isDisplayed())
									//if(driver.findElementByXPath("(//android.widget.TextView[@text='"+titles_fromAPI+"'])["+b+"]").isDisplayed())										
										if(driver.findElementByXPath("//android.widget.TextView[@text='"+Author_fromAPI.get(b)+"']").isDisplayed())	
											test.log(LogStatus.INFO, "Meta data verified in See All details page");
										
										 testCase44=testCase4+" "+traysofListentab_fromAPI.get(i-1);
										 xls.setCellData("Smoke_Results", "Testcase Name", rowno+3, testCase44);
											homepagev2.smokeresults(testCase44, rowno+3, "PASS");											
										}
										catch (Exception e)
										{
											Utilities.verticalSwipe(driver);
											try
											{
											if(driver.findElementByXPath("//android.widget.TextView[@text='"+titles_fromAPI.get(b)+"']").isDisplayed())
												//if(driver.findElementByXPath("(//android.widget.TextView[@text='"+titles_fromAPI+"'])["+b+"]").isDisplayed())										
													if(driver.findElementByXPath("//android.widget.TextView[@text='"+Author_fromAPI.get(b)+"']").isDisplayed())	
														test.log(LogStatus.INFO, "Titlte and Author is displayed");
													else
														BasePageV2.reportFail("Title is missing");
											}
											catch(Exception e1)
											{
												 testCase55=testCase5+" "+traysofListentab_fromAPI.get(i-1);
												 xls.setCellData("Smoke_Results", "Testcase Name", rowno+4, testCase55);
													homepagev2.smokeresults(testCase55, rowno+4, "FAIL");
											}
											
										}
									}							    
							 }
								 //code for greater than 8
							 rowno = xls.getRowCount("Smoke_Results") + 1;					 							 
				 }// end of tray for loop
			 }
			 }	 		
		 //End of Listen tab trays API	 
		

		
		
		// based on the api tab position click on listen tab== done
	//Get all tray detals from API==done
	//Outer for loop to traverse through each tray
	// Swipe to the particular tray
	// Get the no of content for this particular tray from API
	
	  // Do all validations
	
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}