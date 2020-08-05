package com.viacom.downloads;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.DownloadsPageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.KidsPlayerPageV2;
import com.viacom.pagesVersion2.ListenPageV2;
import com.viacom.pagesVersion2.ReadPageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.pagesVersion2.WatchPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidKeyCode;

// Author - Suresh

public class DownloadsValidation_P1 extends BaseTestV2 {

String testName = "DownloadsValidation_P1";
	
	@Test(dataProvider = "getData")
	public void verifyNavidation_P2(Hashtable<String, String> data) throws Exception{	
	test = rep.startTest("DownloadsValidation_P1");
	test.log(LogStatus.INFO, "Starting the test for Verifying the DownloadsValidation_P1 : "+VootConstants.DEVICE_NAME);
	// Check run mode

	if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
		test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
		throw new SkipException("Skipping the test as Run Mode was: NO");
	}	
	
	launchApp();
	HomePageV2 homepagev2=new HomePageV2(driver, test);
	WatchPageV2 watchpagev2=new WatchPageV2(driver,test);
	KidsPlayerPageV2 playerpagev2=new KidsPlayerPageV2(driver,test);
	DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
	ListenPageV2 listenPage = new ListenPageV2(driver, test);
	ReadPageV2 readPage = new ReadPageV2(driver, test);
	SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);

		String un=data.get("Email");
		String pwd=data.get("Password");
	    String mostPopularApi = data.get("mostPopularTray");
	    
		homepagev2.login(un, pwd);
		
		
		// VK_1023 - Verify the contents under profile gets deleted and profile gets hidden post deleting contents under a profile
	    //VK_948 - Verify if Downloaded contents are available in downloads tab post creating profile and downloading
	    // VK_945 - Verify if downloaded/downloading contents are present under respective profile names in Downloads tab 
	    // VK_946 - Verify the sorting order of profiles in Downloads tab
	    //VK_947 - Verify the sorting order of profiles in Downloads tab post switching a profile
	    // VK_949 - Verify if contents available in downloads tab post deleting profile
	    
   
	    // clear Downloads
	    try {
			
	    	downloadsPage.deleteAllDownloadsAndClickMyStuffTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
	    
	    // download the Episode in watch page
	    // click on Watch page
	    if(Utilities.explicitWaitClickable(driver, homepagev2.watch_tab, 60)) {
	    
	    try {
			homepagev2.tabClick("Watch");
	    
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	    }else BasePageV2.reportFail("Failed to click on Watch Tab in Home page / Not click "); 
	    
	    
	    // click on Watch page carousal to download
	    if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
	    	watchpagev2.cardinwatchcarousel.click();
	    	test.log(LogStatus.INFO, "click on carousal in Watch page");
	    	try {
				homepagev2.verifyAndProgressBar();
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	try {
				playerpagev2.pauseVideo();
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	
	    	if(Utilities.explicitWaitClickable(driver, playerpagev2.episodePlayerDownloadIcon, 50)) {
	    		playerpagev2.episodePlayerDownloadIcon.click();
	    		test.log(LogStatus.INFO, "click on download icon in episode player");
	    		if(Utilities.explicitWaitClickable(driver, watchpagev2.okBtn, 20)) {
	    			watchpagev2.okBtn.click();
	    		}
	    		driver.pressKeyCode(AndroidKeyCode.BACK);
	    		Utilities.verticalSwipeReverse(driver);
	    	}else {
	    		test.log(LogStatus.FAIL, "Failed to click on download icon in episode player / Not displayed");
	    	}
	    		
	    	
	    }else {
	    	BasePageV2.reportFail("NOt able to download the carousal card in Watch page");
	    }
	    
	    
	    	// click on Read tab to download book
	    	try {
	    		homepagev2.tabClick("Read");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
		    	watchpagev2.cardinwatchcarousel.click();
		    	test.log(LogStatus.INFO, "click on carousal in Read page");
		    	Thread.sleep(5000);
		        try {
					
		        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
		        	
				} catch (Exception e) {
					// TODO: handle exception
				}
		    	// click on download link to downloads the book
		    	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
		    		watchpagev2.downloadEpisodesLink.click();
		    		test.log(LogStatus.INFO, "click on book download link in book info page");
		    		driver.pressKeyCode(AndroidKeyCode.BACK);
		    		Utilities.verticalSwipeReverse(driver);
		    		
		    	}else {
		    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
		    		BasePageV2.takeScreenshot();
		    	}
		    	
		    	
	        }else {
	        	test.log(LogStatus.FAIL, "Not displayed book carousal in Book page / Not click");
	        	BasePageV2.takeScreenshot();
	        }
	    	
	    	
	    	// download Audio card 
	        try {
				homepagev2.tabClick("Listen");
			} catch (Exception e) {
				// TODO: handle exception
			}
	      // click on carousal in Audio page
	        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
		    	watchpagev2.cardinwatchcarousel.click();
		    	test.log(LogStatus.INFO, "click on carousal in Read page");
		    	Thread.sleep(5000);
	        }else {
	        	test.log(LogStatus.FAIL, "Failed to click on carousal in Audio page / not displayed");
	        }
	    
	        try {
				
	        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
	        	
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	      // click on Audio download link in Audio info page
	      	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
	    		watchpagev2.downloadEpisodesLink.click();
	    		test.log(LogStatus.INFO, "click on Audio download link in Audio info page");
	    		driver.pressKeyCode(AndroidKeyCode.BACK);
	    		Utilities.verticalSwipeReverse(driver);
	    		
	    	}else {
	    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
	    		BasePageV2.takeScreenshot();
	    	}
	    
	    // Navigating to Downloads page
	      	// click on My-Stuff tab in home page
	      	try {
				homepagev2.tabClick("My Stuff");
			} catch (Exception e) {
				// TODO: handle exception
			}
	      	
	    	for (int i = 0; i <= 20; i++)
			{
				if (Utilities.explicitWaitVisibleNew(driver, downloadsPage.downloadsTabMystuffpage, 1))
				{
					downloadsPage.downloadsTabMystuffpage.click();
					test.log(LogStatus.INFO, "clicked on downloads tab in My Stuff page");
					Utilities.verticalSwipe(driver, downloadsPage.editDownloadsMystuff);
					break;
				}
				else Utilities.verticalSwipe(driver);
			}
	    	
	    	String cancelLocator="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item']";
			String deleteLocator="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/delete_button']";
			String confirmDelete="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']";
	    	
	    	if (Utilities.explicitWaitClickable(driver, downloadsPage.editDownloadsMystuff, 50))
			{
				try {
					   downloadsPage.editDownloadsMystuff.click();
						List<WebElement> cancelButtons=driver.findElements(By.xpath(cancelLocator));
						while(cancelButtons.size()>0) {
							for(int count=0;count<cancelButtons.size();count++) {
								try {
									cancelButtons.get(count).click();
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Unable to click on Cancel Download of item "+count);
								}
								
							}
							try {
								driver.findElement(By.xpath(deleteLocator)).click();
							}
							catch(Exception e) {
								//test.log(LogStatus.FAIL, "Unable to click on Delete button");
							}
							try {
								driver.findElement(By.xpath(confirmDelete)).click();
							}
							catch(Exception e) {
								//test.log(LogStatus.FAIL, "Failed to Confirm Delete button");
							}
							cancelButtons=driver.findElements(By.xpath(cancelLocator));
						}
						if(cancelButtons.size()==0) {
							test.log(LogStatus.INFO, "Deleted all Downloads");	
							
							if(Utilities.explicitWaitVisible(driver, downloadsPage.downloadedProfile, 10)) {
								test.log(LogStatus.FAIL, "Verify the contents under profile gets deleted and profile gets hidden post deleting contents under a profile");
								if(!Utilities.setResultsKids("VK_1023", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							}else {
								test.log(LogStatus.PASS, "Verify the contents under profile gets deleted and profile gets hidden post deleting contents under a profile");
								if(!Utilities.setResultsKids("VK_1023", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
							}
							
							driver.navigate().back();
							if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
								downloadsPage.myStuffTabMystuffpage.click();
								for(int i = 0 ; i < 15 ; i ++) {
									Utilities.verticalSwipeReverse(driver);
								}
							}else {
								test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
							}
						}
						else {
							test.log(LogStatus.FAIL, "Failed to delete all Downloads");
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Unable to click on EDIT DOWNLOADS button");
					}
			}
			else {
				test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not clickable");
			}
	      	
	    	
	    	
	    	
	    	
	      //VK_948 - Verify if Downloaded contents are available in downloads tab post creating profile and downloading
	    	
	    	// create profile and switch 
	      	try {
				
	      		homepagev2.createProfileAndSelectTheProfile();
	      		test.log(LogStatus.INFO, "Created New Profile and switched ");
			} catch (Exception e) {
				// TODO: handle exception
			}
	      	
	     // click on Read tab to download book
	    	try {
	    		homepagev2.tabClick("Read");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	String bookCardTitle = "";
	    	
	        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
		    	watchpagev2.cardinwatchcarousel.click();
		    	test.log(LogStatus.INFO, "click on carousal in Read page");
		    	Thread.sleep(5000);
		        try {
		        	bookCardTitle = readPage.nameFromDetailsPage.getText().toString().trim();
		        	bookCardTitle = homepagev2.convertCamelCase(bookCardTitle);
		        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
		        	
				} catch (Exception e) {
					// TODO: handle exception
				}
		    	// click on download link to downloads the book
		    	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
		    		watchpagev2.downloadEpisodesLink.click();
		    		test.log(LogStatus.INFO, "click on book download link in book info page");
		    		driver.pressKeyCode(AndroidKeyCode.BACK);
		    		Utilities.verticalSwipeReverse(driver);
		    		
		    	}else {
		    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
		    		BasePageV2.takeScreenshot();
		    	}
		    	
		    	
	        }else {
	        	test.log(LogStatus.FAIL, "Not displayed book carousal in Book page / Not click");
	        	BasePageV2.takeScreenshot();
	        }
	    		      	
	      	
	      	// click on My Stuff 
	      	try {
					homepagev2.tabClick("My Stuff");
				} catch (Exception e) {
					// TODO: handle exception
				}
		      	
		    	for (int i = 0; i <= 20; i++)
				{
					if (Utilities.explicitWaitVisibleNew(driver, downloadsPage.downloadsTabMystuffpage, 1))
					{
						downloadsPage.downloadsTabMystuffpage.click();
						Utilities.verticalSwipe(driver, downloadsPage.editDownloadsMystuff);
						break;
					}
					else Utilities.verticalSwipe(driver);
				}
		    	
		    	int profileError =0;
		        // click on Esit downloads button in Downloads Page 	
		    	if (Utilities.explicitWaitClickable(driver, downloadsPage.editDownloadsMystuff, 5))
				{
					try {
						   downloadsPage.editDownloadsMystuff.click();
						   
							if(Utilities.explicitWaitVisible(driver, downloadsPage.downloadedProfile, 10)) {
								test.log(LogStatus.INFO, "Displayed downloads profile");
								try {
									driver.findElement(By.xpath("//android.widget.TextView[@text='"+bookCardTitle+"' and @resource-id='com.viacom18.vootkids:id/textview_download_item_title']"));
									test.log(LogStatus.INFO, "Found downloaded content in downloads ");
								} catch (Exception e) {
									profileError++;
									test.log(LogStatus.FAIL, "Not displyed downloaded book '"+bookCardTitle+"' in Edit downloads page");
									BasePageV2.takeScreenshot();
								}
							}else {
								profileError++;
								test.log(LogStatus.FAIL, "Not displyed downloads profile in Edit downloads page");
								BasePageV2.takeScreenshot();
							}
							
							driver.pressKeyCode(AndroidKeyCode.BACK);
							if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
								downloadsPage.myStuffTabMystuffpage.click();
								for(int i = 0 ; i < 15 ; i ++) {
									Utilities.verticalSwipeReverse(driver);
								}
							}else {
								test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
							}
							
							
						
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Unable to click on EDIT DOWNLOADS button");
						}
				}
				else {
					test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not clickable");
				}
	      	
	      	
	      	 if(profileError == 0) {
	      		test.log(LogStatus.PASS, "Verify if Downloaded contents are available in downloads tab post creating profile and downloading");
				if(!Utilities.setResultsKids("VK_948", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
	      	 }else {
	      		test.log(LogStatus.FAIL, "Verify if Downloaded contents are available in downloads tab post creating profile and downloading");
				if(!Utilities.setResultsKids("VK_948", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document"); 
	      	 }
	    
	        // clear downloads
	      	 downloadsPage.deleteAllDownloadsAndClickMyStuffTab();
	    
	    //  VK_945 - Verify if downloaded/downloading contents are present under respective profile names in Downloads tab 
	      	 
	      	 // click on Read Tab 
	      	 try {
				homepagev2.tabClick("Read");
	      		 test.log(LogStatus.INFO, "click on Read tab");
			} catch (Exception e) {
				// TODO: handle exception
			}
	      	 
	      	// Download the book from carousal
	         if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
			    	watchpagev2.cardinwatchcarousel.click();
			    	test.log(LogStatus.INFO, "click on carousal in Read page");
			    	Thread.sleep(5000);
			        try {
			        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
			        	
					} catch (Exception e) {
						// TODO: handle exception
					}
			    	// click on download link to downloads the book
			    	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
			    		watchpagev2.downloadEpisodesLink.click();
			    		test.log(LogStatus.INFO, "click on book download link in book info page");
			    		driver.pressKeyCode(AndroidKeyCode.BACK);
			    		Utilities.verticalSwipeReverse(driver);
			    		
			    	}else {
			    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
			    		BasePageV2.takeScreenshot();
			    	}
			    	
			    	
		        }else {
		        	test.log(LogStatus.FAIL, "Not displayed book carousal in Book page / Not click");
		        	BasePageV2.takeScreenshot();
		        }
	      	 
	      	    // click on profile Icon in home page
	         if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
	        	 homepagev2.profilepic.click();
	        	 test.log(LogStatus.INFO, "Click on Profile Pic icon in home page ");
	        	 if(Utilities.explicitWaitClickable(driver, homepagev2.secondProfile, 20)) {
	        		 homepagev2.secondProfile.click();
	        		 test.log(LogStatus.INFO, "switched profile");
	        		 Thread.sleep(10000);
	        		 driver.pressKeyCode(AndroidKeyCode.BACK);
	        	 }else {
	        		 test.log(LogStatus.FAIL, "Failed to switch the profile");
	        		 BasePageV2.takeScreenshot();
	        	 }
	         }else {
	        	 test.log(LogStatus.FAIL, "failed to click on Profile Pic in home page");
	        	 BasePageV2.takeScreenshot();
	         }
	      	 
	      	 // download Audio card 
	         try {
					homepagev2.tabClick("Listen");
				} catch (Exception e) {
					// TODO: handle exception
				}
		      // click on carousal in Audio page
		        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
			    	watchpagev2.cardinwatchcarousel.click();
			    	test.log(LogStatus.INFO, "click on carousal in Read page");
			    	Thread.sleep(5000);
		        }else {
		        	test.log(LogStatus.FAIL, "Failed to click on carousal in Audio page / not displayed");
		        }
		    
		        try {
					
		        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
		        	
				} catch (Exception e) {
					// TODO: handle exception
				}
		        
		      // click on Audio download link in Audio info page
		      	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
		    		watchpagev2.downloadEpisodesLink.click();
		    		test.log(LogStatus.INFO, "click on Audio download link in Audio info page");
		    		driver.pressKeyCode(AndroidKeyCode.BACK);
		    		Utilities.verticalSwipeReverse(driver);
		    		
		    	}else {
		    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
		    		BasePageV2.takeScreenshot();
		    	}
	   
	      	 // navigate to Downloads Tab in My Stuff Page
		      	try {
					homepagev2.tabClick("My Stuff");
				} catch (Exception e) {
					// TODO: handle exception
				}
		      	
		    	for (int i = 0; i <= 20; i++)
				{
					if (Utilities.explicitWaitVisibleNew(driver, downloadsPage.downloadsTabMystuffpage, 1))
					{
						downloadsPage.downloadsTabMystuffpage.click();
						test.log(LogStatus.INFO, "clicked on downloads tab in My Stuff page");
						Utilities.verticalSwipe(driver, downloadsPage.editDownloadsMystuff);
						break;
					}
					else Utilities.verticalSwipe(driver);
				}
		    	
		    	// click on Edit downloads button 
		    	
		    	if (Utilities.explicitWaitClickable(driver, downloadsPage.editDownloadsMystuff, 50))
				{
					
						   downloadsPage.editDownloadsMystuff.click();
						   test.log(LogStatus.INFO, "click on Edit downloads button in downloads page");
		      	
				}else {
					test.log(LogStatus.FAIL, "Failed to click on Edit downloads button in downloads page / Not displayed ");
				}
	      	 
		    	// get the count of profiles present in Edit Downloads page
		    	int profilesSize = 0;
		    	try {
					 profilesSize = downloadsPage.profilesInEditpage.size();
					test.log(LogStatus.INFO, "The total profiles present in Edit downloads page is : " + profilesSize);
				} catch (Exception e) {
					// TODO: handle exception
				}
		    	
		    	if(profilesSize == 2) {
		    		test.log(LogStatus.PASS, "Verify if downloaded/downloading contents are present under respective profile names in Downloads tab");
					if(!Utilities.setResultsKids("VK_945", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
				
		    	}else {
		    		test.log(LogStatus.FAIL, "Verify if downloaded/downloading contents are present under respective profile names in Downloads tab");
					if(!Utilities.setResultsKids("VK_945", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
		    	}
		    	
		    	driver.pressKeyCode(AndroidKeyCode.BACK);
				if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
					downloadsPage.myStuffTabMystuffpage.click();
					for(int i = 0 ; i < 15 ; i ++) {
						Utilities.verticalSwipeReverse(driver);
					}
				}else {
					test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
				}
		   
		    	// VK_946 - Verify the sorting order of profiles in Downloads tab
		    	
		    	// clear downloads
				downloadsPage.deleteAllDownloadsAndClickMyStuffTab();
				
				// switch profile 
				String secondProTitle = "";
			      if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			        	 homepagev2.profilepic.click();
			        	 test.log(LogStatus.INFO, "Click on Profile Pic icon in home page ");
			        	 if(Utilities.explicitWaitClickable(driver, homepagev2.secondProfile, 20)) {
			        		 homepagev2.secondProfile.click();
			        		 test.log(LogStatus.INFO, "switched profile");
			        		
			        		 if(Utilities.explicitWaitVisible(driver, homepagev2.secondProfileTile , 20)) {
			        			  secondProTitle = homepagev2.secondProfileTile.getText().trim();
			        		 }else {
			        			 test.log(LogStatus.FAIL, "Failed to get the second profile title in switch profile");
			        			 BasePageV2.takeScreenshot();
			        		 }
			        		 
			        		 
			        		 Thread.sleep(10000);
			        		 driver.pressKeyCode(AndroidKeyCode.BACK);
			        	 }else {
			        		 test.log(LogStatus.FAIL, "Failed to switch the profile");
			        		 BasePageV2.takeScreenshot();
			        	 }
			         }else {
			        	 test.log(LogStatus.FAIL, "failed to click on Profile Pic in home page");
			        	 BasePageV2.takeScreenshot();
			         }
		    	
			       // download book card 
			      
			      // click on Read Tab 
			    	try {
			    		homepagev2.tabClick("Read");
			    		homepagev2.tabClick("Read");
					} catch (Exception e) {
						e.printStackTrace();
					}
			    	
			        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
				    	watchpagev2.cardinwatchcarousel.click();
				    	test.log(LogStatus.INFO, "click on carousal in Read page");
				    	Thread.sleep(5000);
				        try {
							
				        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
				        	
						} catch (Exception e) {
							// TODO: handle exception
						}
				    	// click on download link to downloads the book
				    	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
				    		watchpagev2.downloadEpisodesLink.click();
				    		test.log(LogStatus.INFO, "click on book download link in book info page");
				    		driver.pressKeyCode(AndroidKeyCode.BACK);
				    		Utilities.verticalSwipeReverse(driver);
				    		
				    	}else {
				    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
				    		BasePageV2.takeScreenshot();
				    	}
				    	
				    	
			        }else {
			        	test.log(LogStatus.FAIL, "Not displayed book carousal in Book page / Not click");
			        	BasePageV2.takeScreenshot();
			        }
			      
			      // switch Profile to first 
			        String profileTitleFirst ="";
			        if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			        	 homepagev2.profilepic.click();
			        	 test.log(LogStatus.INFO, "Click on Profile Pic icon in home page ");
			        	 if(Utilities.explicitWaitClickable(driver, homepagev2.firstProfile, 20)) {
			        		 homepagev2.firstProfile.click();
			        		 test.log(LogStatus.INFO, "switched profile");
			        		 Thread.sleep(10000);
			        		 
			        		  if(Utilities.explicitWaitVisible(driver, homepagev2.firstProfileTile, 10)) {
						    	     profileTitleFirst = homepagev2.firstProfileTile.getText().toString().trim();
						      }else {
						    	    	test.log(LogStatus.FAIL, "Failed to get the first profile title from switch profile screen/Not displayed ");
						    	    	BasePageV2.takeScreenshot();
						    	    }
			        
			        		 driver.pressKeyCode(AndroidKeyCode.BACK);
			        	 }else {
			        		 test.log(LogStatus.FAIL, "Failed to switch the profile");
			        		 BasePageV2.takeScreenshot();
			        	 }
			         }else {
			        	 test.log(LogStatus.FAIL, "failed to click on Profile Pic in home page");
			        	 BasePageV2.takeScreenshot();
			         }
			      
			      // download Audio card
			        try {
						homepagev2.tabClick("Listen");
					} catch (Exception e) {
						// TODO: handle exception
					}
			      // click on carousal in Audio page
			        if(Utilities.explicitWaitClickable(driver, watchpagev2.cardinwatchcarousel, 60)) {
				    	watchpagev2.cardinwatchcarousel.click();
				    	test.log(LogStatus.INFO, "click on carousal in Read page");
				    	Thread.sleep(5000);
			        }else {
			        	test.log(LogStatus.FAIL, "Failed to click on carousal in Audio page / not displayed");
			        }
			    
			        try {
						
			        	Utilities.verticalSwipe(driver, watchpagev2.downloadEpisodesLink);
			        	
					} catch (Exception e) {
						// TODO: handle exception
					}
			        
			      // click on Audio download link in Audio info page
			      	if(Utilities.explicitWaitClickable(driver,watchpagev2.downloadEpisodesLink , 50)) {
			    		watchpagev2.downloadEpisodesLink.click();
			    		test.log(LogStatus.INFO, "click on Audio download link in Audio info page");
			    		driver.pressKeyCode(AndroidKeyCode.BACK);
			    		Utilities.verticalSwipeReverse(driver);
			    		
			    	}else {
			    		test.log(LogStatus.FAIL, "Failed to click on download link in read info page / not found");
			    		BasePageV2.takeScreenshot();
			    	}
			        
		
			      	// Navigate to Downloads page
			      	//click on My Stuff Tab in home page
			      	try {
						homepagev2.tabClick("My Stuff");
					} catch (Exception e) {
						// TODO: handle exception
					}
			      	
			    	for (int i = 0; i <= 20; i++)
					{
						if (Utilities.explicitWaitVisibleNew(driver, downloadsPage.downloadsTabMystuffpage, 1))
						{
							downloadsPage.downloadsTabMystuffpage.click();
							Utilities.verticalSwipe(driver, downloadsPage.editDownloadsMystuff);
							break;
						}
						else Utilities.verticalSwipe(driver);
					}
			    	
			    	
			        // click on Esit downloads button in Downloads Page 	
			    	if (Utilities.explicitWaitClickable(driver, downloadsPage.editDownloadsMystuff, 5))
					{
						try {
							   downloadsPage.editDownloadsMystuff.click();
							   
								if(Utilities.explicitWaitVisible(driver, downloadsPage.downloadedProfile, 10)) {
									test.log(LogStatus.INFO, "Displayed downloads profile");
									try {
										driver.findElement(By.xpath("//android.widget.TextView[@text='"+profileTitleFirst+"' and @resource-id='com.viacom18.vootkids:id/profile_name']"));
										test.log(LogStatus.FAIL, "Verify the sorting order of profiles in Downloads tab post switching a profile");
										if(!Utilities.setResultsKids("VK_947", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										BasePageV2.takeScreenshot();
									} catch (Exception e) {
										test.log(LogStatus.PASS, "Verify the sorting order of profiles in Downloads tab post switching a profile");
										if(!Utilities.setResultsKids("VK_947", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
										
									}
								}else {
									profileError++;
									test.log(LogStatus.FAIL, "Not displyed downloads profile in Edit downloads page");
									BasePageV2.takeScreenshot();
								}
								
								driver.pressKeyCode(AndroidKeyCode.BACK);
								if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
									downloadsPage.myStuffTabMystuffpage.click();
									for(int i = 0 ; i < 15 ; i ++) {
										Utilities.verticalSwipeReverse(driver);
									}
								}else {
									test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
								}
								
								
							
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Unable to click on EDIT DOWNLOADS button");
							}
					}
					else {
						test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not clickable");
					}
			        
			        
			   // VK_946 - Verify the sorting order of profiles in Downloads tab  
			      
			    	
			    	
			    	try {
						homepagev2.tabClick("My Stuff");
					} catch (Exception e) {
						// TODO: handle exception
					}
			      	
			    	for (int i = 0; i <= 20; i++)
					{
						if (Utilities.explicitWaitVisibleNew(driver, downloadsPage.downloadsTabMystuffpage, 1))
						{
							downloadsPage.downloadsTabMystuffpage.click();
							Utilities.verticalSwipe(driver, downloadsPage.editDownloadsMystuff);
							break;
						}
						else Utilities.verticalSwipe(driver);
					}
			    	
			    	
			    	String editPageProfl="";
			        // click on Esit downloads button in Downloads Page 	
			    	if (Utilities.explicitWaitClickable(driver, downloadsPage.editDownloadsMystuff, 5))
					{
						try {
							   downloadsPage.editDownloadsMystuff.click();
						       test.log(LogStatus.INFO, "click on Edit downloads button in downloads page");
						       Thread.sleep(10000);
						       try {
						    	    editPageProfl = downloadsPage.profilesInEditpage.get(1).getText().trim().toString();
						    	    test.log(LogStatus.FAIL, "Failed to get the profile name from edit downloads page");
								} catch (Exception e) {
									e.printStackTrace();
								}
							  
							   
								driver.pressKeyCode(AndroidKeyCode.BACK);
								if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
									downloadsPage.myStuffTabMystuffpage.click();
									for(int i = 0 ; i < 15 ; i ++) {
										Utilities.verticalSwipeReverse(driver);
									}
								}else {
									test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
								}
								
								
							
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Unable to click on EDIT DOWNLOADS button");
							}
					}
					else {
						test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not clickable");
					}
			    	
			    	if(secondProTitle.equals(editPageProfl)) {
			    		test.log(LogStatus.PASS, "Verify the sorting order of profiles in Downloads tab");
						if(!Utilities.setResultsKids("VK_946", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			    	}else {
			    		test.log(LogStatus.FAIL, "Verify the sorting order of profiles in Downloads tab");
						if(!Utilities.setResultsKids("VK_946", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
			    	}
			    	
			    // VK_949 - Verify if contents available in downloads tab post deleting profile	
			    	
			    	//click on Profile pic in home page
			   
			    	if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 80)) {
						homepagev2.profilepic.click();
						// Click on ParentZone Button in Switch Profile Screen parentZoneButton
						if (Utilities.explicitWaitVisible(driver, settingsPage.parentZoneButton, 30)) {
							settingsPage.parentZoneButton.click();
							test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
							if (Utilities.explicitWaitVisible(driver, settingsPage.parentPinContainer, 30)) {
								Thread.sleep(1000);
								settingsPage.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
								Thread.sleep(30000);
								// putting App in background
								 settingsPage.putBackGroundApp3();
								// click on Settings Icon in Parent Zone page
								 if (Utilities.explicitWaitVisible(driver, settingsPage.settingsIcon, 80)) {
										settingsPage.settingsIcon.click();
										// click on profile Link in settings page
										 if(Utilities.explicitWaitClickable(driver, settingsPage.settingsProfile,60)){
											 settingsPage.settingsProfile.click();
											 test.log(LogStatus.INFO, "Click on Profile link in settings page");
											 
											 // delete Profile 
											 try {
												
												 driver.findElement(By.xpath("//android.widget.TextView[@text='PROFILES' or @text='Profiles' and @resource-id='com.viacom18.vootkids:id/toolbar_title']/../../../..//android.widget.TextView[@text='"+secondProTitle+"']")).click();
												 
												 if(Utilities.explicitWaitClickable(driver, settingsPage.deleteProfileLink, 60)) {
													 settingsPage.deleteProfileLink.click();
													 test.log(LogStatus.INFO, "click on delete profile link");
													 if(Utilities.explicitWaitClickable(driver, settingsPage.prodelPopYesBtn, 60)) {
														 settingsPage.prodelPopYesBtn.click();
														 test.log(LogStatus.INFO, "click on yes button on delete pop up");
														 
														 // navigate to home page
														 driver.pressKeyCode(AndroidKeyCode.BACK);
														 driver.pressKeyCode(AndroidKeyCode.BACK);
														 driver.pressKeyCode(AndroidKeyCode.BACK);
														 driver.pressKeyCode(AndroidKeyCode.BACK);
														 
														 // Navigate to downloads page in My Stuff screen
													    	try {
																homepagev2.tabClick("My Stuff");
															} catch (Exception e) {
																// TODO: handle exception
															}
													      	
													    	for (int i = 0; i <= 20; i++)
															{
																if (Utilities.explicitWaitVisibleNew(driver, downloadsPage.downloadsTabMystuffpage, 1))
																{
																	downloadsPage.downloadsTabMystuffpage.click();
																	Utilities.verticalSwipe(driver, downloadsPage.editDownloadsMystuff);
																	break;
																}
																else Utilities.verticalSwipe(driver);
															}
													    	
													    	
													        // click on Esit downloads button in Downloads Page 	
													    	if (Utilities.explicitWaitClickable(driver, downloadsPage.editDownloadsMystuff, 5))
															{
																try {
																	   downloadsPage.editDownloadsMystuff.click();
																	   
																		if(Utilities.explicitWaitVisible(driver, downloadsPage.downloadedProfile, 10)) {
																			test.log(LogStatus.INFO, "Displayed downloads profile");
																			try {
																				driver.findElement(By.xpath("//android.widget.TextView[@text='"+secondProTitle+"' and @resource-id='com.viacom18.vootkids:id/profile_name']"));
																				test.log(LogStatus.FAIL, "Verify if contents available in downloads tab post deleting profile");
																				if(!Utilities.setResultsKids("VK_949", "Fail")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
																				BasePageV2.takeScreenshot();
																			} catch (Exception e) {
																				test.log(LogStatus.PASS, "Verify if contents available in downloads tab post deleting profile");
																				if(!Utilities.setResultsKids("VK_949", "Pass")) test.log(LogStatus.WARNING, "TC ID not found in the tc document");
																		
																			}
																		}else {
																			profileError++;
																			test.log(LogStatus.FAIL, "Not displyed downloads profile in Edit downloads page");
																			BasePageV2.takeScreenshot();
																		}
																		
																		driver.pressKeyCode(AndroidKeyCode.BACK);
																		if(Utilities.explicitWaitClickable(driver, downloadsPage.myStuffTabMystuffpage, 50)) {
																			downloadsPage.myStuffTabMystuffpage.click();
																			for(int i = 0 ; i < 15 ; i ++) {
																				Utilities.verticalSwipeReverse(driver);
																			}
																		}else {
																			test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
																		}
																		
																		
																	
																	}
																	catch(Exception e) {
																		test.log(LogStatus.FAIL, "Unable to click on EDIT DOWNLOADS button");
																	}
															}
															else {
																test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not clickable");
															}
														 
														 
														 
														 
														 
														 
														 
													 }else BasePageV2.reportFail("Failed to click on yes button on delete pop up / Not displayed");
												 }else {
													 BasePageV2.reportFail("Failed click on delete link in Edit Profile page / Not displayed");
												 }
												 
												 
											} catch (Exception e) {
												e.printStackTrace();
											}
											 
											 
											 
											 
										 }else {
											 BasePageV2.reportFail("Failed to click on profile link in settings page");
											 BasePageV2.takeScreenshot();
										 }
			
								 }else { 
									 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
									 BasePageV2.takeScreenshot();
								 }
							}else {
								test.log(LogStatus.FAIL, "Failed to set the Pin in parent zone page  / Not displayed");
							}
								 
						}else BasePageV2.reportFail("Not displayed Parent Zone button in switch profile screen/ Not click");
			    	
			    	}else BasePageV2.reportFail("Not displayed Profile pic icon in home page / not click");
		    	
		    	
		
		
	}
	
	    @DataProvider
		public Object[][] getData(){
			return DataUtil.getData(testName,xls);
					

		}	
	
}
