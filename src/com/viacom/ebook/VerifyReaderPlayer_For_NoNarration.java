package com.viacom.ebook;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.BooksPageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.EbooksPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SearchPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.ShowsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

public class VerifyReaderPlayer_For_NoNarration extends  BaseTestV2
{

//VK_630----Verify the availablity of Auto-page Turn option if the book has no narration option	 	
//VK_1912-----Verify the UI of Reader player control screen if the book has no narration option	
//VK_1913-----Verify the availablity of Auto Zoom option if the book has no narration option
	 	
	String testName = "VerifyReaderPlayer_For_NoNarration";		
	@Test(dataProvider = "getData")
	public void VerifyReaderPlayer_For_NoNarration(Hashtable<String, String> data) throws Exception 
	{				
		test = rep.startTest("VerifyReaderPlayer_For_NoNarration");
		test.log(LogStatus.INFO, "Starting the test on: "+VootConstants.DEVICE_NAME);
		// Check run mode
		       if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		  }           		
		       //Launch VootkidsApp();				
				 launchApp();
				 
				 HomePageV2 homepagev2=new HomePageV2(driver,test);
				 WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
				 ShowsPageV2 showpageV2=new ShowsPageV2(driver,test);
				
				 ListenPageV2 listenpageV2=new ListenPageV2(driver,test);
				 DownloadsPageV2 downloadsv2=new DownloadsPageV2(driver,test);
				 ReadPageV2 readpagev2=new ReadPageV2(driver,test);
				 SettingsPageV2 settingspagev2=new SettingsPageV2(driver,test);
				 BooksPageV2 bookspagev2=new BooksPageV2(driver,test);
				 BasePageV2  basepagev2=new BasePageV2(driver,test);
				 LaunchPageV2 launchpagev2=new  LaunchPageV2(driver,test);
				
				 KidsPlayerPageV2 kidsplayerv2=new KidsPlayerPageV2(driver,test);
				 EbooksPageV2 ebookspagev2= new EbooksPageV2(driver,test);
				 SearchPageV2 searchpagev2=new SearchPageV2(driver,test);
				 
				 
				//login with valid credentials
				 homepagev2.login(data.get("Email"),data.get("Password"));
				 
				 //delete All Downloads
				  downloadsv2.deleteAllDownloads();
				 
				
				 String ebookname=data.get("booktitle");
				 
				 
				 int c=0 ,count=0  ,updatedcount1=0;   String  lastread=""  ,lastviewed2="" ,lastviewed3="", lastviewedfirsttitle1="";
                
				 String audiocard="",  title="", audiocard1="", audiocard2="" , audiocard3="" ,storiescount3="",bookname3="",bookname=""; 
               
				 int updatedcount2=0 , updatedcount3=0,totalpagesreadofebook=0;  String storiescount="",  storiescount1="",  storiescount2="",	bookname1="";
                
				  boolean readerDisplayedPreview=false;   String bookname2="", totalpagesread="", pages="";    WebElement booktitle=null;  
	

//VK_630----Verify the Availablity of Auto-page Turn option if the book has no narration option
			
				    if(Utilities.explicitWaitClickable(driver, searchpagev2.Search, 10))
					{
				    	     searchpagev2.Search.click();
				    	     test.log(LogStatus.INFO, " clicked on serch button");
					}
				    else
			    	{
			             	test.log(LogStatus.FAIL, "failed to click onserch button");
			             	basepagev2.takeScreenshot();
			    	}
			  
				    //click and send data to the search box in search page
				    
				    if(Utilities.explicitWaitClickable(driver, searchpagev2.searchTextBox, 10))
					{								          
				        	    searchpagev2.searchTextBox.click();	
				        	    test.log(LogStatus.INFO, "clicked on search box");	
				        	    searchpagev2.searchTextBox.sendKeys(""+ebookname+"");
				        	    test.log(LogStatus.INFO, "sucesses fully data sent to the serchbox");				        	 
				    }
				    else
			    	{				    	
			    	       test.log(LogStatus.FAIL, "failed to click on search box");
			    	       test.log(LogStatus.FAIL, "failed to sent  data sent to the serchbox");
			    	       basepagev2.takeScreenshot();
			    	}
				    
				    //hide The keyboard
					   
					 try { 
						 driver.hideKeyboard();
						 test.log(LogStatus.INFO, "keyboard hided Sucesseesfuly");	
						 
					     }catch(Exception e) 
					     {
					    	 test.log(LogStatus.FAIL, " failed to hide The keyboard ");
					    	 basepagev2.takeScreenshot();
					     }
				    
						    //refresh app				    
						    driver.runAppInBackground(Duration.ofSeconds(3));
							 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
							 driver.currentActivity();
					
					// searchpagev2.card1inserachpage
				    if(Utilities.explicitWaitClickable(driver,  ebookspagev2.card1underbestreadspage, 10))
					{				
				          				        	   
				        	   // title=searchpagev2.card1title.getAttribute("text").toString().trim();
				        	    title=ebookspagev2.card1underbestreadspage.getAttribute("text").toString().trim();	
				        	    test.log(LogStatus.INFO, "sucessesfully retrieved book tilte                   "+title);
				        	    try{
				        	    	    ebookspagev2.card1underbestreadspage.click();				        	    				        	    
						        	    test.log(LogStatus.INFO, "clicked on book card having no narration option ");
				        	 
				                    }catch(Exception e)
				        	        {
				                    	  test.log(LogStatus.FAIL, "failed to click  on bookcard having no narration option ");	
				                    	  basepagev2.takeScreenshot();
				        	        }
				           				           
					}
				    else
			    	{
			    	               test.log(LogStatus.FAIL, " book card is not available in search page");
			    	               basepagev2.takeScreenshot();		    	
			        }
				    
				    
			if(Utilities.explicitWaitClickable(driver, readpagev2.previewButton, 10))
			{
					try {
						  readpagev2.previewButton.click();
						  test.log(LogStatus.INFO, "Clicked on PREVIEW button");
							 for(int wait=0;wait<=30;wait++) //for loop 1 starting
							  {
							     try
							      {
									 driver.findElement(By.id("com.viacom18.vootkids:id/animation_view"));
									 //Add 1447
									    if(wait==30)
									    {
										 test.log(LogStatus.INFO, "Loader is continuously displayed for 30 seconds but eBook Reader did not launch...");
										
										 test.log(LogStatus.FAIL, "Verify the behavior when tapping on 'Preview' button is Fail");
										 BasePageV2.takeScreenshot();
										 driver.navigate().back();
										 test.log(LogStatus.INFO, "Navigated back to book details page");
									    }
								 
							       } catch(Exception e)
							       {
									 test.log(LogStatus.INFO, "Loader is not displayed");
									 Set<String> CHS = driver.getContextHandles();								 
									 for(String ch:CHS)
									 {
										if(ch.contains("WEBVIEW"))
										{
											test.log(LogStatus.INFO, "eBook Reader is displayed");
											
											test.log(LogStatus.INFO, "Verify the behavior when tapping on 'Preview' button is Pass");
											readerDisplayedPreview=true;
											break;
										}
									 }  
								   }	
								 if(readerDisplayedPreview==true)
									 break;
								 else
									 Thread.sleep(1000);
							 }//for loop1 ending						 
						  } catch(Exception e)
					     {					
							test.log(LogStatus.FAIL, "Unable to click on PREVIEW button"); 
							basepagev2.takeScreenshot();
					     }
									
					
					         //for removing coach cards
				             readpagev2.dismissReaderCoachCards();
					
					
					               try{
							              // double tapping
											Thread.sleep(2000);											
											Utilities.tap(driver);
		             						Utilities.tap(driver);
											test.log(LogStatus.INFO, "Double tapped on screen to launch reader controls");
					                 }catch(Exception e)
					                 {
					                	 test.log(LogStatus.FAIL, "failed to do Double tapping on  screen to launch reader controls");
					                	 basepagev2.takeScreenshot();
					                 }
											 
											 
											 //click on settings button in book reader controls page											 
											 if(Utilities.explicitWaitClickable(driver,kidsplayerv2.bookReaderSettingsButton, 10))
									         {    
									        	 							        		 
									        		   kidsplayerv2.bookReaderSettingsButton.click();
									        		   test.log(LogStatus.INFO, "clicked on setting button in reader controls page page");
								        	       
										    }
											else
											{
											            test.log(LogStatus.FAIL, "failed to click on sttings button in reader controls  page");
											             basepagev2.takeScreenshot();
											}
														
											 //verify Autopage option should be disabled or not  for Ebooks which has no Narration option											 
									    	 if(kidsplayerv2.bookReaderOptionAutoPageTurnOff.getAttribute("enabled").equalsIgnoreCase("true"))
									    	 {									    		
									    		    test.log(LogStatus.FAIL,"VK_630----Verify the Availablity of Auto-page Turn option if the book has no narration option");
									                if(!Utilities.setResultsKids("VK_630", "FAIl")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 								     		      	
									    	 }
									    	 else				    		
									    	 {
									    		     test.log(LogStatus.PASS,"VK_630----Verify the Availablity of Auto-page Turn option if the book has no narration option");
									                if(!Utilities.setResultsKids("VK_630", "PASS"))test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 									     		       		
									                basepagev2.takeScreenshot();
									    	 }
									    					    				    				           		
           }//preview if condition ending			          
           else
    	   {
    	         test.log(LogStatus.FAIL, " preview button  is not available ");
    	          basepagev2.takeScreenshot();
    	   }
        	   
//VK_1913-----Verify the availablity of Auto Zoom option if the book has no narration option       	   
				
			if(Utilities.explicitWaitVisible(driver, kidsplayerv2.bookReaderOptionAutozoomb, 10))
			{				
			        	     test.log(LogStatus.INFO, "AutoZoom button is available ");
			        	     
			        	    String  mode1=kidsplayerv2.bookReaderOptionAutozoomb.getAttribute("enabled").toString().trim();
			        	    kidsplayerv2.bookReaderOptionAutozoomb.click();
			        	    String  mode2=kidsplayerv2.bookReaderOptionAutozoomb.getAttribute("enabled").toString().trim();
			        	     
			        	    if(mode1.equalsIgnoreCase(mode2))
			        	    {
			        	    	   test.log(LogStatus.PASS,"VK_1913-----Verify the availablity of Auto Zoom option if the book has no narration option");
					                if(!Utilities.setResultsKids("VK_1913", "PASS")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
					     		       	
			        	    }
			        	    else			        	    
			        	    {
			        	    	 test.log(LogStatus.FAIL,"VK_1913-----Verify the availablity of Auto Zoom option if the book has no narration option ");
				                  if(!Utilities.setResultsKids("VK_1913", "FAIL")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				                  basepagev2.takeScreenshot();   
			        	    }			        	    					           					      	 
								     
		   }			
		   else
		   {
		         test.log(LogStatus.FAIL, "AutoZoom button is not available "); 
		          basepagev2.takeScreenshot();
		   }
				  
		  //navigate back to e-book reader controls  page from options page
			
		          driver.navigate().back();
				  Thread.sleep(2000);
				  
//VK_1912-----Verify the UI of Reader player control screen if the book has no narration option			  
								     					 
					int	UI_error=0;
					
				    //verify seek bar present on reader controls page					 
					if(Utilities.explicitWaitVisible(driver,ebookspagev2.bookseekbar, 10))
			        {    			        	
						     test.log(LogStatus.INFO, "bookreadr seekbar is  available");						
			        }
					else
					{
					           test.log(LogStatus.FAIL, "bookreadr seekbar is  not available");
					          basepagev2.takeScreenshot();
					        UI_error++;
					}

					//verify Chapter Name present or not					
					if(Utilities.explicitWaitVisible(driver,ebookspagev2.chapternumberb, 10)) 
					{    			        	
						test.log(LogStatus.INFO, "bookreadr chapternumber is  is displayed");							
			        }
					else
					{
					     test.log(LogStatus.FAIL, "bookreadrchapter number is  not displayed");
					      basepagev2.takeScreenshot();
					      UI_error++;
					}
												         
		             //verify Total Number of pages present on right side of the seekbar		    
					 if(Utilities.explicitWaitVisible(driver,ebookspagev2.pagesfromplayerb, 10))
				     {    			        	
							test.log(LogStatus.INFO, "bookreadr  total pages count is displayed");							
				     }
					 else
					 {
					        test.log(LogStatus.FAIL, "bookreadr  total pages count is   not displayed"); 
					        basepagev2.takeScreenshot();	
					        UI_error++;
					 }
																	
					//verify Narration not availableText present or not					
					if(Utilities.explicitWaitVisible(driver,kidsplayerv2.bookReadernarrationnotavailable, 10)) 
					{    			        	
						test.log(LogStatus.INFO, "bookreadr narration not availble text is displayed");							
			        }
					else
					{
					    test.log(LogStatus.FAIL, "bookreadr narration not availble textis not displayed");
					    basepagev2.takeScreenshot();
					    UI_error++;
					}
			
					
					//verify favorite icon present or not					
					if(Utilities.explicitWaitVisible(driver,kidsplayerv2.bookReaderfavouriteicon, 10)) 
			        {    
			        	
						test.log(LogStatus.INFO, "favourite icon is displayed");							
			        }
					else
					{
					    test.log(LogStatus.FAIL, "favourite icon is  not displayed");
					    basepagev2.takeScreenshot();
					    UI_error++;
					}
				
					//verify book reader options button  present or not					
					if(Utilities.explicitWaitVisible(driver,kidsplayerv2.bookReaderoptionsButton, 10)) 
			        {    
			        	
						test.log(LogStatus.INFO, "bookReaderoptionsButton is displayed");	
						
			        }
					else
					{
					   test.log(LogStatus.FAIL, "bookReaderoptionsButton is  not displayed");
					    basepagev2.takeScreenshot();
					    UI_error++;
					}
					
										
				    // verify book reader author name presnt or not					
					if(Utilities.explicitWaitVisible(driver,kidsplayerv2.bookReaderauthorname, 10)) 
			        {    			        	
						test.log(LogStatus.INFO, "bookReaderauthorname is displayed ");							
			        }
					else
					{
					    test.log(LogStatus.FAIL, "bookReaderauthorname  is  not displayed");
					    basepagev2.takeScreenshot();
					    UI_error++;
					}
				
					// verify book reader up arrow button present or not					
					if(Utilities.explicitWaitVisible(driver,kidsplayerv2.bookReaderuparrow,10)) 
			        {    
			        	
						  test.log(LogStatus.INFO, "bookReaderuparrow is  is displayed ");							
			        }
					else
					{ 
					      test.log(LogStatus.FAIL, "bookReaderuparrow   is  not displayed");
					      basepagev2.takeScreenshot();
					      UI_error++;					      
					}
				
					//verify book title present or not							
					if(Utilities.explicitWaitVisible(driver,kidsplayerv2.bookReaderbookname, 10))   
			        {    			        	
						test.log(LogStatus.INFO, "book title is displayed");							 				
			        }
					else
					{
						      test.log(LogStatus.FAIL,"book title is not displayed");
						      basepagev2.takeScreenshot();
			                 UI_error++;
				    } 
					
					if (UI_error==0)
					{
						 test.log(LogStatus.PASS,"VK_1912-----Verify the UI of Reader player control screen if the book has no narration option");
			                if(!Utilities.setResultsKids("VK_1912", "PASS"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 			     		      
					}
					else
					{
						test.log(LogStatus.FAIL,"VK_1912-----Verify the UI of Reader player control screen if the book has no narration option");
		                 if(!Utilities.setResultsKids("VK_1912", "FAIL"))  test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 	
		                 basepagev2.takeScreenshot();
					}

	   	}		
		@DataProvider
		public Object[][] getData()
		{
		return DataUtil.getData(testName,xls);											          					
	    }  	
	
}
