package com.viacom.datamonks;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
//Author =Vinoth
public class DMRecommendationCallEventsTrack extends BaseTestV2
{		 
	
	    static String userid="" , profileid="";
		String testName = "DMRecommendationCallEventsTrack";
		@Test(dataProvider = "getData")
		public void loginTest(Hashtable<String, String> data) throws Exception
		{
		  // Check run mode
	     if(!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		       test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		       throw new SkipException("Skipping the test as Run Mode was: NO");
	    }	
	     test = rep.startTest("DM-RecommendationCallEventsTrack");
	 	test.log(LogStatus.INFO, "Starting the test to verify Call Events track (Data Monks) -"+VootConstants.DEVICE_NAME);
	    // Open charles application
	 
	    //Launch App
		launchApp();
		HomePageV2 homepagev2=new HomePageV2(driver, test);
		ShowsPageV2 showspagev2=new ShowsPageV2(driver,test);
		WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
		SearchPageV2 searchpagev2=new SearchPageV2(driver,test);
		LaunchPageV2 launchpagev2=new LaunchPageV2(driver,test);
		KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
		String un=data.get("Email");
		String pwd=data.get("Password");
		//homepagev2.logout();
		//homepagev2.login(un,pwd);
		
		Utilities.getProfileIdAndUserId(driver);

		// fetch all nodelist from charles 
		NodeList nodeList =Utilities.nodeList;
		String strRequest="", strResponse="", pagetype="";
		for (int i = 0; i < nodeList.getLength(); i++) {

			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) (nodeList.item(i));
				//request
				if (element.getAttribute("path").equals("/service/recommendation/widgets")
						&& element.getAttribute("host").equals("dm-prod.vootkids.com")) {		
				Element elementRequest = (Element) element.getElementsByTagName("body").item(0);
				//response
				Element elementResponse = (Element) element.getElementsByTagName("body").item(1);
				//request in String
				strRequest = elementRequest.getTextContent();
				//response in String
				strResponse = elementResponse.getTextContent();		
				//current page type which we are going to verify
			    
		        if (strRequest.contains("\"pageType\":\"MYSTUFF\"")){	
		        	pagetype="MYSTUFF";
					checkParameters(strRequest,strResponse,pagetype);
				}
		    	else if(strRequest.contains("\"pageType\":\"WATCH\""))
		        {
		        	    pagetype="WATCH";
						checkParameters(strRequest,strResponse,pagetype);
		        }
		       else if(strRequest.contains("\"pageType\":\"READ\""))
		        {
		        	    pagetype="READ";
						checkParameters(strRequest,strResponse,pagetype);
		        }
		       else if(strRequest.contains("\"pageType\":\"LISTEN\""))
		        {
		        	    pagetype="LISTEN";
						checkParameters(strRequest,strResponse,pagetype);
		        }
		        else 
				{
		        	test.log(LogStatus.FAIL, "None of the page type found or pagetype missing in request");
				}
		    }  
		  }
      }	
	  
	/*	if (Utilities.explicitWaitClickable(driver, homepagev2.backButton, 10))
		{
			homepagev2.backButton.click();				
		}
		else 
		{
			test.log(LogStatus.FAIL, "Not able to click back button");
			BasePageV2.takeScreenshot();
		}*/
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(3000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(3000);
	/*	if (Utilities.explicitWaitClickable(driver, homepagev2.backButton, 10))
		{
			homepagev2.backButton.click();				
		}
		else 
		{
			test.log(LogStatus.FAIL, "Not able to click back button");
			BasePageV2.takeScreenshot();
		}*/
		test.log(LogStatus.INFO, "Navigating to Watch Page");
		Thread.sleep(5000);
		
		homepagev2.tabClick("Watch");
		Thread.sleep(5000);
		String xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView";
		Utilities.verticalSwipe(driver, xpath);
		
		Thread.sleep(2000);	
		test.log(LogStatus.INFO, "Navigating to any of show under any Section");
		if(Utilities.explicitWaitClickable(driver, watchpagev2.allCharactersFirstShow, 30))
		watchpagev2.allCharactersFirstShow.click();
		else
		BasePageV2.reportFail("Not able to click show under first section");
		Thread.sleep(10000);
		nodeList =Utilities.getNodeList("ShowDetailsFromTray");
		int nodelistsize=nodeList.getLength();
		
		for (int i = 0; i < nodelistsize; i++) {

			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) (nodeList.item(i));
				//request
				if (element.getAttribute("path").equals("/service/recommendation/widgets")
						&& element.getAttribute("host").equals("dm-prod.vootkids.com")) {		
				Element elementRequest = (Element) element.getElementsByTagName("body").item(0);
				//response
				Element elementResponse = (Element) element.getElementsByTagName("body").item(1);
				//request in String
				strRequest = elementRequest.getTextContent();
				//response in String
				strResponse = elementResponse.getTextContent();		
				//current page type which we are going to verify
			    
		        if (strRequest.contains("\"pageType\":\"SHOW_DETAILS\"")){	
		        	pagetype="SHOW_DETAILS";
					checkParameters(strRequest,strResponse,pagetype);
				}
		        else if(i==nodelistsize-1)
				{
		        	test.log(LogStatus.FAIL, " page type - SHOW_DETAILS not found or pagetype missing in request");
				}
		       
		    }  
				//else
				//test.log(LogStatus.FAIL, "expected api not triggered ");
		  }
      }	
		
	/*	if (Utilities.explicitWaitClickable(driver, homepagev2.backButton, 10))
		{
			homepagev2.backButton.click();				
		}
		else 
		{
			test.log(LogStatus.FAIL, "Not able to click back button");
			BasePageV2.takeScreenshot();
		}
		*/
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(3000);
		test.log(LogStatus.INFO, "Navigating to any tray page by tapping tray title");
		if (Utilities.explicitWaitClickable(driver, watchpagev2.trayTitle, 10))
		{
			watchpagev2.trayTitle.click();				
		}
		else 
		{
			test.log(LogStatus.FAIL, "Not able to click tray title");
			BasePageV2.takeScreenshot();
		}
		test.log(LogStatus.INFO, "Navigating to any show from tray detail page");
		if (Utilities.explicitWaitClickable(driver, watchpagev2.showsTrayFirstShow, 10))
		{
			watchpagev2.showsTrayFirstShow.click();		
			Thread.sleep(5000);
		}
		else 
		{
			test.log(LogStatus.FAIL, "Not able to click show in tray detail page");
			BasePageV2.takeScreenshot();
		}
		Thread.sleep(5000);
		nodeList =Utilities.getNodeList("ShowDetailsFromTraytitle");
		nodelistsize=nodeList.getLength();
		
		for (int i = 0; i < nodelistsize; i++) {

			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) (nodeList.item(i));
				//request
				if (element.getAttribute("path").equals("/service/recommendation/widgets")
						&& element.getAttribute("host").equals("dm-prod.vootkids.com")) {		
				Element elementRequest = (Element) element.getElementsByTagName("body").item(0);
				//response
				Element elementResponse = (Element) element.getElementsByTagName("body").item(1);
				//request in String
				strRequest = elementRequest.getTextContent();
				//response in String
				strResponse = elementResponse.getTextContent();		
				//current page type which we are going to verify
			    
		        if (strRequest.contains("\"pageType\":\"SHOW_DETAILS\"")){	
		        	pagetype="SHOW_DETAILS";
					checkParameters(strRequest,strResponse,pagetype);
				}
		        else if(i==nodelistsize-1)
				{
		        	test.log(LogStatus.FAIL, " page type - SHOW_DETAILS not found or pagetype missing in request");
				}
		    }  
				//else
				//	test.log(LogStatus.FAIL, "expected api not triggered ");
		  }
      }	
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		
		test.log(LogStatus.INFO, "Navigating to any of the show under channels tab");
		Utilities.verticalSwipe(driver, watchpagev2.channelsTab,80);
		for(int i=1;i<10;i++)
		{
		 if(!Utilities.explicitWaitClickable(driver, watchpagev2.channelsTab, 1))
		 Utilities.verticalSwipe(driver);		
		}
		if(Utilities.explicitWaitClickable(driver, watchpagev2.channelsTab, 20))
		{
			 Utilities.verticalSwipe(driver);
			watchpagev2.channelsTab.click();
			if(Utilities.explicitWaitClickable(driver, watchpagev2.channelTabFirstShow, 20))
			{
				watchpagev2.channelTabFirstShow.click();
				Thread.sleep(2000);
				if(Utilities.explicitWaitClickable(driver, watchpagev2.characterstab, 20))
				{
					watchpagev2.characterstab.click();
				}
				else
					test.log(LogStatus.FAIL, "Not able to click characters Tab");
				
				if(Utilities.explicitWaitClickable(driver, watchpagev2.charactersTabFirstShow, 20))
				{
					watchpagev2.charactersTabFirstShow.click();
					Thread.sleep(5000);
				}
				else
					test.log(LogStatus.FAIL, "Not able to click show under characters Tab");
				
				 nodeList =Utilities.getNodeList("ShowDetailsFromTraytitle");
				nodelistsize=nodeList.getLength();
				
				for (int i = 0; i < nodelistsize; i++) {

					if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) (nodeList.item(i));
						//request
						if (element.getAttribute("path").equals("/service/recommendation/widgets")
								&& element.getAttribute("host").equals("dm-prod.vootkids.com")) {		
						Element elementRequest = (Element) element.getElementsByTagName("body").item(0);
						//response
						Element elementResponse = (Element) element.getElementsByTagName("body").item(1);
						//request in String
						 strRequest = elementRequest.getTextContent();
						//response in String
						 strResponse = elementResponse.getTextContent();		
						//current page type which we are going to verify
					    
				        if (strRequest.contains("\"pageType\":\"SHOW_DETAILS\"")){	
				        	pagetype="SHOW_DETAILS";
							checkParameters(strRequest,strResponse,pagetype);
						}
				        else if(i==nodelistsize-1)
						{
				        	test.log(LogStatus.FAIL, " page type - SHOW_DETAILS not found or pagetype missing in request");
						}
				    }  
						//else
						//	test.log(LogStatus.FAIL, "expected api not triggered ");
				  }
			   }
			}
			else
				test.log(LogStatus.FAIL, "show under Channels tab not found");	
		}
		else
			test.log(LogStatus.FAIL, "Channels tab not found");	
		driver.pressKeyCode(AndroidKeyCode.BACK);
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Utilities.verticalSwipeDown(driver);
		test.log(LogStatus.INFO, "Navigating to search page");
		if (Utilities.explicitWaitClickable(driver, homepagev2.search, 10))
		{
			homepagev2.search.click();			
		}
		else 
		{
			test.log(LogStatus.FAIL, "Not able to click search icon");
			BasePageV2.takeScreenshot();
		}
		
		Utilities.verticalSwipe(driver, searchpagev2.popularSearchShow);
		if (Utilities.explicitWaitClickable(driver, searchpagev2.popularSearchShow, 10))
		{
			Thread.sleep(2000);
			searchpagev2.popularSearchShow.click();		
			test.log(LogStatus.INFO, "Clicked on popular search show");
			Thread.sleep(10000);
			
		 nodeList =Utilities.getNodeList("ShowDetailsFromTray");
			 nodelistsize=nodeList.getLength();
			
			for (int i = 0; i < nodelistsize; i++) {

				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) (nodeList.item(i));
					//request
					if (element.getAttribute("path").equals("/service/recommendation/widgets")
							&& element.getAttribute("host").equals("dm-prod.vootkids.com")) {		
					Element elementRequest = (Element) element.getElementsByTagName("body").item(0);
					//response
					Element elementResponse = (Element) element.getElementsByTagName("body").item(1);
					//request in String
					 strRequest = elementRequest.getTextContent();
					//response in String
				 strResponse = elementResponse.getTextContent();		
					//current page type which we are going to verify
				    
			        if (strRequest.contains("\"pageType\":\"SHOW_DETAILS\"")){	
			        	 pagetype="SHOW_DETAILS";
						checkParameters(strRequest,strResponse,pagetype);
					}
			        else if(i==nodelistsize-1)
					{
			        	test.log(LogStatus.FAIL, " page type - SHOW_DETAILS not found or pagetype missing in request");
					}
			       
			    }  
					//else
					//test.log(LogStatus.FAIL, "expected api not triggered ");
			  }
	      }	
			
			/*// related shows below player
			if (Utilities.explicitWaitClickable(driver, showspagev2.showDetailPagePlayButton, 10))
			{
				showspagev2.showDetailPagePlayButton.click();		
				Thread.sleep(1000);
				homepagev2.verifyAndProgressBar();
				playerpagev2.pauseVideo();
			}
			else 
			{
				test.log(LogStatus.FAIL, "Not able to click play button in show detail page");
				BasePageV2.takeScreenshot();
			}
			if (Utilities.explicitWaitClickable(driver, playerpagev2.playerBottomUpArrowButton, 10))
			{
				playerpagev2.playerBottomUpArrowButton.click();		
			}
			else 
			{
				test.log(LogStatus.FAIL, "Not able to click up arrow button in player");
				BasePageV2.takeScreenshot();
			}*/
			
			

		}
		else 
		{
			test.log(LogStatus.FAIL, "Not able to click show in most poular search");
			BasePageV2.takeScreenshot();
		}
		
		
}
public static void checkParameters(String strRequest,String strResponse,String pagetype)
    {
			test.log(LogStatus.INFO, "Verifying all parameters in request as Expected for pageType -"+pagetype);		
			//Actual map
			Map<String,String> actualRequestMap=new HashMap<String,String>();
			//ExpectedMap
			LinkedHashMap<String, String> expectedRequestMap=new LinkedHashMap<String,String>(){
				{
					put("limit","8");
					put("pageType",pagetype);
					put("userId",GlobalVariables.userId);
					put("userIdType","GUID");
					put("itemId","");
					put("catalogType","SERIES");
					put("profileId",GlobalVariables.profileId);
					if(pagetype.equals("SHOW_DETAILS"))
					put("total","8");
					else
					put("total","20");
					
					if(pagetype.equals("MYSTUFF"))
				      	put("widgetTypes","[RECS]");
					else if(pagetype.equals("WATCH"))
						put("widgetTypes","[RECS_EPISODE,MOST_POPULAR_EPISODE]");
					else if(pagetype.equals("LISTEN"))
						put("widgetTypes","[RECS_AUDIO_STORIES,MOST_POPULAR_AUDIO_STORIES]");
					else if(pagetype.equals("READ"))
						put("widgetTypes","[RECS_EBOOK,MOST_POPULAR_EBOOK]");
					else if(pagetype.equals("SHOW_DETAILS"))
						put("widgetTypes","[SAME_CATEGORY_SIMILAR_SERIES]");
					
					
					put("deviceType","APP");
					put("deviceOsType","ANDROID");
					put("offset","0");
					put("followupId","");
				}
			};
		            strRequest= strRequest.replace("\"", "");
					strRequest=strRequest.replace("{", "");
					strRequest=strRequest.replace("}", "");
					String[] actualRequest=	strRequest.split(",");
					String multipleWidget="";
			for(int i=0;i<actualRequest.length;i++)
			{
				try{
					actualRequestMap.put(actualRequest[i].split(":")[0],actualRequest[i].split(":")[1]);
					if(i==actualRequest.length-2)
					multipleWidget=actualRequest[i].split(":")[1];
					else
					test.log(LogStatus.INFO, "Key-"+actualRequest[i].split(":")[0]+" Value-"+actualRequest[i].split(":")[1]);
				}
				catch(Exception e){
					
					if(i==actualRequest.length-1)
					{
						
						multipleWidget=multipleWidget+","+actualRequest[i].split(":")[0];
						actualRequestMap.put("widgetTypes",multipleWidget);
						test.log(LogStatus.INFO, "Key-widgetTypes Value-"+multipleWidget);
					}
				}
			}
			
		for(Map.Entry<String, String> m :expectedRequestMap.entrySet())
		{
		    String expectedKey=m.getKey();
			try{
				String actualValue=actualRequestMap.get(expectedKey);
				if(m.getValue().contains(actualValue))
				{
					test.log(LogStatus.INFO, expectedKey+" is verified-"+actualValue);
				}
				else
				{
					if(!pagetype.equals("SHOW_DETAILS"))
					test.log(LogStatus.FAIL, expectedKey+" is incorrect-"+actualValue);
					else
						test.log(LogStatus.INFO, expectedKey+" is present-"+actualValue);
				}
			}
			catch(Exception e)
			{
				if(expectedKey.equals("itemId") || expectedKey.equals("catalogType"))
				{
					if(!pagetype.equals("SHOW_DETAILS"))
					test.log(LogStatus.INFO, expectedKey+" is not present ");	
					else
					{
						test.log(LogStatus.FAIL, expectedKey+" is missing-");
					}
						
				}
				else if(expectedKey.equals("followupId"))
				{
					String offset=actualRequestMap.get("offset");
					if(offset.equals("0"))
					{
						//test.log(LogStatus.INFO, "Follow up id is not in request but offset is 0");
					}
					else
					{
						//test.log(LogStatus.FAIL, "Follow up id is not in request ");
					}
				}
				else
				test.log(LogStatus.FAIL, expectedKey+" is missing");
			}
		}
		
		//Verifying tray type
  if(pagetype.equals("MYSTUFF"))
  {
	  String strResponseNew=strResponse.replace("\"", "");
	  String traylabel="Just for you";
	  int index=strResponseNew.indexOf(traylabel);
	  String traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
	  
		if(traytype.equals("jfu"))
		{
			test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
		}
		else
		{
			test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
		}
 }
  else if(pagetype.equals("WATCH"))
  {
	  String strResponseNew=strResponse.replace("\"", "");
	  String traylabel="Most popular episodes";
	  int index=strResponseNew.indexOf(traylabel);
	  String traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
		if(traytype.equals("mpe"))
		{
			test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
		}
		else
		{
			test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
		}
		 traylabel="Episodes just for you";
		 index=strResponseNew.indexOf(traylabel);
		 traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
		 if(traytype.equals("jfue"))
			{
				test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
			}
			else
			{
				test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
			}
  }
  else if(pagetype.equals("LISTEN"))
  {
	  String strResponseNew = strResponse.replace("\"", "");
	  String traylabel="Most popular audio stories";
	  int index=strResponseNew.indexOf(traylabel);
	  String traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
	  
		if(traytype.equals("mpas"))
		{
			test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
		}
		else
		{
			test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
		}
		 traylabel="Audio stories just for you";
		 index=strResponseNew.indexOf(traylabel);
		 traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
		 if(traytype.equals("jfuas"))
			{
				test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
			}
			else
			{
				test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
			}
  }
  else if(pagetype.equals("READ"))
  {
   String strResponseNew = strResponse.replace("\"", "");
   String traylabel="Most popular ebooks";
   int index=strResponseNew.indexOf(traylabel);
   String traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
  
	if(traytype.equals("mpeb"))
	{
		test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
	}
	else
	{
		test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
	}
	 traylabel="Ebooks just for you";
	 index=strResponseNew.indexOf(traylabel);
	 traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
	 if(traytype.equals("jfueb"))
		{
			test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
		}
		else
		{
			test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
		}
  }
  else if(pagetype.equals("SHOW_DETAILS"))
  {
   String strResponseNew = strResponse.replace("\"", "");
   String traylabel="Similar shows";
   int index=strResponseNew.indexOf(traylabel);
   String traytype=strResponseNew.substring(index).split(":")[1].split(",")[0];
  
	if(traytype.equals("scss"))
	{
		test.log(LogStatus.INFO, "Tray type is verified in response-"+traytype);
	}
	else
	{
		test.log(LogStatus.FAIL, "Tray type is incorrect in response-"+traytype);
	}
	
  }
  else
	  test.log(LogStatus.FAIL, "None of the page type found or pagetype missing in request");
  
	//Verifying pagetype
	if(strResponse.contains("\"pageType\":"))
	{
		String strResponseNew = strResponse.replace("\"", "");
	    int index=strResponseNew.indexOf("pageType");
		String page=strResponseNew.substring(index).split(":")[1].split(",")[0];
		if(page.equals(pagetype.toLowerCase()))
		test.log(LogStatus.INFO, "pageType is verified in response-"+page);
		else
		test.log(LogStatus.INFO, "pageType is incorrect in response-"+page);
	}
	else
	{
		test.log(LogStatus.FAIL, "pageType is missing in response");
	}
  
	//  make sure follow up id is not empty or null 
	
	if(strResponse.contains("\"followupId\":\""))
	{
		String strResponseNew = strResponse.replace("\"", "");
	    int index=strResponseNew.indexOf("followupId");
		String followupid=strResponseNew.substring(index).split(":")[1].split(",")[0];
		test.log(LogStatus.INFO, "followupId is present in response-"+followupid);
		if(followupid.equals("") || followupid.equals(null))
		test.log(LogStatus.FAIL, "FollowupId is empty/null");	
	}
	else
	{
		test.log(LogStatus.FAIL, "FollowupId is missing in response");
	}
	expectedRequestMap.clear();
}
@DataProvider
public Object[][] getData(){
	return DataUtil.getData(testName,xls);
				
  }

}
