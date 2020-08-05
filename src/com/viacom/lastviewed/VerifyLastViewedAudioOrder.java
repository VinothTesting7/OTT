package com.viacom.lastviewed;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
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
//Author Tanisha
public class VerifyLastViewedAudioOrder extends BaseTestV2{
	
	String testName = "VerifyLastViewedAudioOrder";
	static ArrayList<String> totalAudios=new  ArrayList<String>();
	static ArrayList<String> audioAuthors=new  ArrayList<String>();
	static String audioTitle="";
	@Test(dataProvider = "getData")
	
	public void VerifyLastViewedAudioOrder(Hashtable<String, String> data) throws Exception 
	{		
		test = rep.startTest("VerifyLastViewedAudioOrder");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}		
		
		//VK_1522 = Verify the no.of cards present under 'Last Viewed' section
		//VK_1523 = Verify the cards removal order under 'Last Viewed' section when user has accessed more than 8 Audio Books
		//VK_1525 = Verify Audio card metadata for partially watched contents
		//VK_1526 = Verify Audio card metadata for completely watched contents
		//VK_1524 = Verify the type of cards available under 'Last Viewed' section in Listen Tab
		
		//Launch Voot kids app
		 launchApp();
		 HomePageV2 homepagev2=new HomePageV2(driver,test);
		 BasePageV2 basepagev2=new BasePageV2(driver,test);
		 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		 ShowsPageV2 showpagev2=new ShowsPageV2(driver,test);
		 EbooksPageV2 ebookspagev2=new EbooksPageV2(driver,test);
		 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
		 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
		 
		 
		 String audio1,audio2,audio3,audio4,audio7,audio8="";
		 String lastViewed1="";
		 String lastViewed2="";
		 String lastViewed3="";
		 String lastViewed4="";
		 String lastViewed7="";
		 String lastViewed8="";
		 int errVK_1522=0;
		 int errVK_1523=0;
		 int errVK_1525=0;
		 int errVK_1526=0;
		 int errVK_1524=0;
		 
		 String temp="";
		 String tempCC="";
		 String audioAuthor="";
		 String tempAuthor="";
			//Login module 
			//homepagev2.logout();
			homepagev2.login(data.get("Email"),data.get("Password"));
		 if(Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 5)) {
			 test.log(LogStatus.INFO, "Application launched successfully");
		 }

		 
		//Click on Listen tab
		 homepagev2.tabClick("Listen");
		 homepagev2.tabClick("Listen");
		 boolean stopAddingBooks=false;
		 boolean gotName=false;
		
		//Click on Famous Story tellers
		String trayLoc="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']";
		for(int scroll=0;scroll<=2;scroll++) {
			driver.findElement(By.xpath(trayLoc)).click();
		}
		
		while(totalAudios.size()<=9) {
			 String audioLocs="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2']";
			 List<WebElement> tempAudios=driver.findElementsByXPath(audioLocs);
			 for(int i=0;i<tempAudios.size();i++) {
				if(i==5 || i==7 || i==9) {
					Utilities.verticalSwipe(driver);
				}
				try {
					tempAudios.get(i).click();
					Thread.sleep(2000);
					Utilities.verticalSwipe(driver);
					try {
						temp=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")).getAttribute("text");
						audioTitle=homepagev2.convertCamelCase(temp);
					}
					catch(Exception e){
							 test.log(LogStatus.FAIL, "Failed to fetch Audio Name from Audio details page");
					}
					try {
							 audioAuthor=driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_author_name']")).getAttribute("text");
					}
					catch(Exception e){
							 test.log(LogStatus.FAIL, "Failed to fetch Audio Author from Audio details page");
					}
					Thread.sleep(2000);
					if(!totalAudios.contains(audioTitle)) {
						 test.log(LogStatus.INFO, "-------------------------------------");
						 test.log(LogStatus.INFO, "Audio: "+audioTitle);
						 test.log(LogStatus.INFO, "Author: "+audioAuthor);
						 openPlayer(audioTitle,audioAuthor); 
						 driver.navigate().back();
					}
					else {
						 //test.log(LogStatus.INFO, "Book already added to list.. Hence navigating back");
						 driver.navigate().back();
					}
					if(totalAudios.size()>=9) {
						stopAddingBooks=true;
						break;
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Book :"+audioTitle);
				}		 
			}
			if(stopAddingBooks==true)
				 break;
			Utilities.verticalSwipe(driver);
			Utilities.verticalSwipe(driver);
		 }
		 
		test.log(LogStatus.INFO, "Audios for which player opened are: \n"+totalAudios);
		test.log(LogStatus.INFO, "List of authors: \n"+audioAuthors);

		//Verify Last Audio 
		 driver.navigate().back();
		 Utilities.verticalSwipeReverse(driver);
		 Utilities.verticalSwipeReverse(driver);
		 homepagev2.tabClick("My Stuff");
		 homepagev2.tabClick("Listen");
		 //Scroll to Last Viewed
		 String recentViewedClear="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		 Utilities.verticalSwipeAndFind(driver, recentViewedClear);
		 test.log(LogStatus.INFO, "Swiped till Last Viewed tray");
		 Utilities.verticalSwipe(driver);
		 Utilities.verticalSwipe(driver);
		 //Check book 1
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookTitle, 10)) {
	    	lastViewed1=homepagev2.lastViewedFirstBookTitle.getAttribute("text");
	    	lastViewed1=homepagev2.convertCamelCase(lastViewed1);
	    	if(lastViewed1.trim().equals(totalAudios.get(8).trim())) {
	    		test.log(LogStatus.INFO, lastViewed1+" is the first latest audio under last read in Listen tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedFirstBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(audioAuthors.get(8))){
	    				test.log(LogStatus.INFO, "Author "+audioAuthors.get(8)+" matches with author displayed in audio book details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last viewed is "+tempAuthor+", Author displayed in audio book details is "+audioAuthors.get(8));
	    				errVK_1526++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1526++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1526++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed1+" is the first latest audio under last viewed in Audio tab instead of "+totalAudios.get(8));
	    		errVK_1523++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 1 is not visible under last viewed in Audio tab");
	    	errVK_1522++;
	     }
	     //Check audio 2
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookTitle, 10)) {
	    	lastViewed2=homepagev2.lastViewedSecondBookTitle.getAttribute("text");
	    	lastViewed2=homepagev2.convertCamelCase(lastViewed2);
	    	if(lastViewed2.trim().equals(totalAudios.get(7).trim())) {
	    		test.log(LogStatus.INFO, lastViewed2+" is the second latest audio under last viewed in Audio tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFirstBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedSecondBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(audioAuthors.get(7))){
	    				test.log(LogStatus.INFO, "Author "+audioAuthors.get(7)+" matches with author displayed in audio details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last viewed is "+tempAuthor+", Author displayed in audio details is "+audioAuthors.get(7));
	    				errVK_1525++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1525++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSecondBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1525++;
	    		}
	    		
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed2+" is the second latest audio under last viewed in Audio tab instead of "+totalAudios.get(7));
	    		errVK_1523++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 2 is not visible under last viewed in Audio tab");
	    	errVK_1522++;
	     }
	     //Check audio 3
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdBookTitle, 10)) {
	    	lastViewed3=homepagev2.lastViewedThirdBookTitle.getAttribute("text");
	    	lastViewed3=homepagev2.convertCamelCase(lastViewed3);
	    	if(lastViewed3.trim().equals(totalAudios.get(6).trim())) {
	    		test.log(LogStatus.INFO, lastViewed3+" is the third latest audio under last viewed in Audio tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedThirdBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(audioAuthors.get(6))){
	    				test.log(LogStatus.INFO, "Author "+audioAuthors.get(6)+" matches with author displayed in audio details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last viewed is "+tempAuthor+", Author displayed in audio details is "+audioAuthors.get(6));
	    				errVK_1525++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1525++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedThirdBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1525++;
	    		}
	    	
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed3+" is the third latest audio under last viewed in Audio tab instead of "+totalAudios.get(6));
	    		errVK_1523++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 3 is not visible under last viewed in Audio tab");
	    	errVK_1522++;
	     }
	     //Check audio 4
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthBookTitle, 10)) {
	    	lastViewed4=homepagev2.lastViewedFourthBookTitle.getAttribute("text");
	    	lastViewed4=homepagev2.convertCamelCase(lastViewed4);
	    	if(lastViewed4.trim().equals(totalAudios.get(5).trim())) {
	    		test.log(LogStatus.INFO, lastViewed4+" is the fourth latest audio under last viewed in Audio tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedFourthBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(audioAuthors.get(5))){
	    				test.log(LogStatus.INFO, "Author "+audioAuthors.get(5)+" matches with author displayed in audio details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last read is "+tempAuthor+", Author displayed in audio details is "+audioAuthors.get(5));
	    				errVK_1525++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1525++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedFourthBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1525++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed4+" is the fourth latest audio under last viewed in Audio tab instead of "+totalAudios.get(6));
	    		errVK_1523++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 4 is not visible under last viewed in Audio tab");
	    	errVK_1522++;
	     }
	     Utilities.verticalSwipe(driver);
	     Utilities.verticalSwipe(driver);
	     //Check audio 7
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhBookTitle, 10)) {
	    	lastViewed7=homepagev2.lastViewedSeventhBookTitle.getAttribute("text");
	    	lastViewed7=homepagev2.convertCamelCase(lastViewed7);
	    	if(lastViewed7.trim().equals(totalAudios.get(2).trim())) {
	    		test.log(LogStatus.INFO, lastViewed7+" is the seventh latest audio under last viewed in Audio tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedSeventhBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(audioAuthors.get(2))){
	    				test.log(LogStatus.INFO, "Author "+audioAuthors.get(2)+" matches with author displayed in audio details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last viewed is "+tempAuthor+", Author displayed in audio details is "+audioAuthors.get(2));
	    				errVK_1525++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1525++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedSeventhBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1525++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed7+" is the seventh latest audio under last viewed in Audio tab instead of "+totalAudios.get(2));
	    		errVK_1523++;
	    	}
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 7 is not visible under last viewed in Audio tab");
	    	errVK_1522++;
	     }
	     //Check audio 8
	     if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthBookTitle, 10)) {
	    	lastViewed8=homepagev2.lastViewedEighthBookTitle.getAttribute("text");
	    	lastViewed8=homepagev2.convertCamelCase(lastViewed8);
	    	if(lastViewed8.trim().equals(totalAudios.get(1).trim())) {
	    		test.log(LogStatus.INFO, lastViewed8+" is the eighth latest audio under last viewed in Audio tab");
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthBookAuthor, 5)) {
	    			tempAuthor=homepagev2.lastViewedEighthBookAuthor.getAttribute("text");
	    			if(tempAuthor.equalsIgnoreCase(audioAuthors.get(1))){
	    				test.log(LogStatus.INFO, "Author "+audioAuthors.get(1)+" matches with author displayed in audio details");
	    			}
	    			else {
	    				test.log(LogStatus.FAIL, "Author in last viewed is "+tempAuthor+", Author displayed in audio details is "+audioAuthors.get(1));
	    				errVK_1525++;
	    			}
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate author");
	    			errVK_1525++;
	    		}
	    		if(Utilities.explicitWaitVisible(driver, homepagev2.lastViewedEighthBookProgressBar, 5)) {
	    			test.log(LogStatus.INFO, "Located progress bar");
	    		}
	    		else {
	    			test.log(LogStatus.FAIL, "Failed to locate progress bar");
	    			errVK_1525++;
	    		}
	    	}
	    	else {
	    		test.log(LogStatus.FAIL, lastViewed8+" is the eighth latest audio under last viewed in Audio tab instead of "+totalAudios.get(1));
	    		errVK_1523++;
	    	}
		    //Verify that the first watched is deleted from last viewed
		    if(lastViewed8.equals(totalAudios.get(0))) {
		    	test.log(LogStatus.FAIL, totalAudios.get(0)+" is not cleared from last viewed tray, FIFO order not followed");			
		    	errVK_1523++;
		    }
		    else {
		    	test.log(LogStatus.INFO, totalAudios.get(0)+" is cleared from last viewed tray, FIFO order is followed");
		    }
	     }
	     else {
	    	test.log(LogStatus.FAIL, "Last Viewed audio 8 is not visible under last viewed in Audio tab");
	    	errVK_1522++;
	     }
	    //Final verification of VK_1522
	    if(errVK_1522==0) {
	    	test.log(LogStatus.PASS, "Verify the no.of cards present under 'Last Viewed' section is Pass");
	    	if(!Utilities.setResultsKids("VK_1522", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify the no.of cards present under 'Last Viewed' section is Pass");
	    	if(!Utilities.setResultsKids("VK_1522", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    if(errVK_1523==0) {
	    	test.log(LogStatus.PASS, "Verify the cards removal order under 'Last Viewed' section when user has accessed more than 8 Audio Books is Pass");
	    	if(!Utilities.setResultsKids("VK_1523", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify the cards removal order under 'Last Viewed' section when user has accessed more than 8 Audio Books is Fail");
	    	if(!Utilities.setResultsKids("VK_1523", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    if(errVK_1525==0) {
	    	test.log(LogStatus.PASS, "Verify Audio card metadata for partially played audio is Pass");
	    	if(!Utilities.setResultsKids("VK_1525", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify Audio card metadata for partially played audio is Fail");
	    	if(!Utilities.setResultsKids("VK_1525", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    if(errVK_1526==0) {
	    	test.log(LogStatus.PASS, "Verify Audio card metadata for completely played audio is Pass");
	    	if(!Utilities.setResultsKids("VK_1526", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");			
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Verify Audio card metadata for completely played audio is Fail");
	    	if(!Utilities.setResultsKids("VK_1526", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");	
	    }
	    
	    //Verify the Audio icon type from API (robotani04@gmail.com, profile Jane)
	     String url_config="";
		 String apiname_config="";
		 String api_config="Config";
		 String url_LV_listen=""; 
		 String api_LV_listen="LV Audio";
		 String apiname_LV_listen="";
		 int api_totalItems_LV_listen=0;
		 ArrayList<Integer> Listof_Audio=new ArrayList<Integer>();	 
		 int rows=xls.getRowCount("Api");
		 for(int rNum=1;rNum<=rows;rNum++){
			 apiname_config=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_config.equals(api_config)){
				 url_config=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_config);
				 Response resp_config=Utilities.requestUtilitypostOld(url_config, map);
				 resp_config.then().assertThat().statusCode(200);
				 //resp_config.prettyPrint();
				 Map<String,Integer> ott=resp_config.jsonPath().get("assets.OTT");
				 for(Map.Entry<String, Integer> m :ott.entrySet()) {	
					 if(m.getKey().equals("AUDIO_TYPE"))
						Listof_Audio.add(m.getValue());	
				 }
			 }		 
		 }
		 for(int rNum=1;rNum<=rows;rNum++){
			 apiname_LV_listen=xls.getCellData("Api", "API Name", rNum);
			 if(apiname_LV_listen.equals(api_LV_listen)){
				 url_LV_listen=xls.getCellData("Api", "Url", rNum);
				 Map map=BasePageV2.apiparams(0, xls, api_LV_listen);
				 Response resp_LV_listen=Utilities.requestUtilitypostOld(url_LV_listen, map);
				 api_totalItems_LV_listen=resp_LV_listen.jsonPath().get("assets.items.size()");
				 for(int item=0; item<api_totalItems_LV_listen; item++) {
					 int mediatype=resp_LV_listen.jsonPath().get("assets.items["+item+"].mediaType");
					 String title=resp_LV_listen.jsonPath().get("assets.items["+item+"].title");
					 if(Listof_Audio.contains(mediatype)) 
						test.log(LogStatus.PASS, "The mediatype of "+title+ " belongs to Audio type" ); 
					 else {
						test.log(LogStatus.FAIL, "The mediatype of "+title+ " does not belongs to either Audio" );
						errVK_1524++;
					 }
				 }
			 }
		 } 
		//Final verification of VK_1524
		if(errVK_1524==0) {
			test.log(LogStatus.PASS, "Verify the type of cards available under 'Last Viewed' section in Listen Tab is Pass");
			if(!Utilities.setResultsKids("VK_1524", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}
		else {
			test.log(LogStatus.FAIL, "Verify the type of cards available under 'Last Viewed' section in Listen Tab is Fail");
			if(!Utilities.setResultsKids("VK_1524", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		}

		homepagev2.mystuff_tab=null;
		homepagev2.lastViewedFirstBookTitle=null;
		homepagev2.lastViewedFirstBookAuthor=null;
		homepagev2.lastViewedFirstBookProgressBar=null;
		homepagev2.lastViewedSecondBookTitle=null;
		homepagev2.lastViewedSecondBookAuthor=null;
		homepagev2.lastViewedSecondBookProgressBar=null;
		homepagev2.lastViewedThirdBookTitle=null;
		homepagev2.lastViewedThirdBookAuthor=null;
		homepagev2.lastViewedThirdBookProgressBar=null;
		homepagev2.lastViewedFourthBookTitle=null;
		homepagev2.lastViewedFourthBookAuthor=null;
		homepagev2.lastViewedFourthBookProgressBar=null;
		homepagev2.lastViewedSeventhBookTitle=null;
		homepagev2.lastViewedSeventhBookAuthor=null;
		homepagev2.lastViewedSeventhBookProgressBar=null;
		homepagev2.lastViewedEighthBookTitle=null;
		homepagev2.lastViewedEighthBookAuthor=null;
		homepagev2.lastViewedEighthBookProgressBar=null;
		homepagev2.recentViewedClear=null;
		homepagev2.recentViewedClearMessage=null;
		homepagev2.recentViewedClearYes=null;
		homepagev2.recentViewedClearNo=null;
		
		

	}
	public void openPlayer(String audio,String author) {
		 ListenPageV2 listenpagev21=new ListenPageV2(driver,test);
		 HomePageV2 homepagev21=new HomePageV2(driver,test);
		 boolean playerDisplayed=false;
		 try {
			 if(Utilities.explicitWaitVisible(driver, listenpagev21.play_btn, 60)) {
				 try {
					 listenpagev21.play_btn.click();
					 test.log(LogStatus.INFO, "Clicked on PLAY button of audio: "+audio);
					 for(int wait=0;wait<=30;wait++) {
						 try {
							 homepagev21.verifyAndProgressBar();
							 if(Utilities.explicitWaitVisible(driver, listenpagev21.audioPlayerSettings, 60)) {
								 test.log(LogStatus.INFO, "Audio Player is displayed");
								 totalAudios.add(audio.trim());
								 audioAuthors.add(author.trim());
								 playerDisplayed=true;
								 /*if(totalAudios.size()==9) {
								 	//play the last audio completely
								 	Utilities.scrubtillend(driver, homepagev21.audioseekBar);
								 	driver.navigate().back();	
							 	}*/
								 try {
									 listenpagev21.audioPlayerClose.click();
									 test.log(LogStatus.INFO, "Closed player");
								 }
								 catch(Exception e) {
									 driver.navigate().back();
									 test.log(LogStatus.INFO, "Navigated back to audio details page"); 
								 }
								 
								 
						 	}
						 }
						 catch(Exception e) {
							 Thread.sleep(2000);
							 if(wait==30) {
								 test.log(LogStatus.FAIL, "Player failed to display");
								 break;
							 }
								 
						 }	
						 if(playerDisplayed==true)
							 break;
						 else
							 Thread.sleep(2000);
					 }						 
				 }
				 catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to click on Play button of the Audio");
				 }
			 }	 
			 else {
				 test.log(LogStatus.INFO, "Play button is not clickable for the Audio: "+audio); 
			 }
		 }
		 catch(Exception e) {
			 test.log(LogStatus.FAIL, "Failed to click on Audio");
		 }
		 listenpagev21.play_btn=null;
		 listenpagev21.audioPlayerClose=null;
		 
	}
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(testName,xls);						
	}
}
