package com.viacom.pagesVersion2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class DownloadsPageV2 extends BasePageV2 {
	public DownloadsPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	public static List<String> profiles = new ArrayList<String>();
	
	@FindBy(xpath="//android.widget.TextView[@text='Edit Downloads' or @text='EDIT DOWNLOADS' and @resource-id='com.viacom18.vootkids:id/header_text']/../../..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/profile_name']")
	public List<WebElement> profilesInEditpage;
	
	@FindBy(xpath = "//android.widget.TextView[@resuorce-id='com.viacom18.vootkids:id/profile_name']")
	public List<WebElement> downloadProfiles;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/profile_name']")
	public WebElement downloadedProfile;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'My Stuff')]")
	public WebElement myStuffTabMystuffpage;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_download_item")
	public WebElement downloadSimbolBtn;
	
	@FindBy(id = "com.viacom18.vootkids:id/imageview_download_item")
	public WebElement downloadThumbnail;

	@FindBy(id = "com.viacom18.vootkids:id/textview_download_item_title")
	public WebElement downloadEpisodeTitle;
	
	@FindBy(xpath="//android.widget.ProgressBar")
	public WebElement progressBarNotification;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/header_text' and @text='DOWNLOADS' or @text='Downloads' or @text='downloads' ]")
	public WebElement downloadsPageTitle;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")
	public WebElement downloadedMovieSize;
	
	@FindBy(id = "com.viacom18.vootkids:id/textview_available_storage_msg")
	public WebElement availableStorage;

	@FindBy(id = "com.viacom18.vootkids:id/imageview_media_state_button")
	public WebElement downloadStateIcon;
	
	@FindBy(xpath="//*[contains(@resource-id,'com.android.systemui:id/clear_all_button')]")
	public WebElement notificationsClear;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @index='0']//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item']")
	public WebElement playDownloadIcon;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']")
	public WebElement contenttoDownload;

	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE']")
	public WebElement contentdownloaded;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'All Downloads') or contains(@text,'ALL DOWNLOADS') or contains(@text,'all downloads')]")
	public WebElement allDownloads;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_empty_view' or @text='NOTHING HERE']")
	public WebElement downloadsNothingHere;
	
	@FindBy(xpath = "//android.widget.FrameLayout[contains(@content-desc,'DOWNLOAD_COMPLETE')]")
	public WebElement contenttoDownloadandDownloaded;
	
	@FindBy(xpath = "//android.widget.FrameLayout[contains(@content-desc,'IN_PROGRESS')]")
	public WebElement inprogress;
	
	@FindBy(id = "com.viacom18.vootkids:id/textview_media_item_size")
	public WebElement downloadsImageSize;

	@FindBy(id = "com.viacom18.vootkids:id/imageview_download_item")
	public WebElement downloadsImageThumbnail;
	@FindBy(id = "com.viacom18.vootkids:id/header_text")
	public WebElement downloadPageHeader;

	@FindBy(id = "com.viacom18.vootkids:id/btn_back")
	public WebElement BackButtonDownloadPage;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'YES') or contains(@text,'Yes') or contains(@text,'yes')]")
	public WebElement ConfirmDeleteDownload;

	@FindBy(id = "com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement CloseDeleteDownloadpopup;


	@FindBy(xpath = "//android.widget.Button[contains(@text,'NO') or contains(@text,'No') or contains(@text,'no')]")
	public WebElement ConfirmNottoDeleteDownload;

	@FindBy(id = "com.viacom18.vootkids:id/download_status_imageview")
	public WebElement downloadIconEpisodes;

	@FindBy(id = "com.viacom18.vootkids:id/textview_edit_downloads")
	public WebElement editDownloadsMystuff;

	
	@FindBy(id = "com.viacom18.vootkids:id/checkbox_delete_download_item")
	public WebElement deleteDownload;

	@FindBy(id = "com.viacom18.vootkids:id/delete_button")
	public WebElement deletebutton;
	
	@FindBy(xpath="//android.widget.Button[@text='YES' or @text='Yes' or @text='yes' or @resource-id='com.viacom18.vootkids:id/positive_btn_dialog']")
	public WebElement downloadDeleteYesBtn;
	
	@FindBy(id = "com.viacom18.vootkids:id/textview_download_item_title")
	public WebElement downloadedContentTitle;
	
	@FindBy(id = "com.viacom18.vootkids:id/textview_download_item_title")
	public List<WebElement> downloadedContentTitles;
	
	@FindBy(id = "com.viacom18.vootkids:id/download_status_textview")
	public WebElement downloadStatusText;

	@FindBy(xpath = "//*[contains(@class,'ViewGroup')][@index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement show1;

	@FindBy(xpath = "//*[contains(@class,'ViewGroup')][@index='3']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement show2;

	@FindBy(id = "com.viacom18.vootkids:id/radio_btn_low")
	public WebElement lowqualityDownload;

	@FindBy(id = "com.viacom18.vootkids:id/radio_btn_medium")
	public WebElement midqualityDownload;

	@FindBy(id = "com.viacom18.vootkids:id/radio_btn_high")
	public WebElement highqualityDownload;

	@FindBy(id = "com.viacom18.vootkids:id/positive_btn")
	public WebElement allowDownload;

	@FindBy(id = "com.viacom18.vootkids:id/negative_btn")
	public WebElement cancelDownload;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='1']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_media_state_button']")
	public WebElement contenttocheckdownloaded;

	@FindBy(id = "com.viacom18.vootkids:id/progress_drawable_middle")
	public WebElement contentDownloadedqueue;

	@FindBy(id = "com.viacom18.vootkids:id/progress_drawable_middle")
	public WebElement contentDownloadedinprogress;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Downloading AudioBook')]")
	public WebElement downloadingAudio;
	
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Downloads')]")
	public WebElement downloadsTabMystuffpage;
	
	@FindBy(id = "com.viacom18.vootkids:id/textview_delete_title")
	public WebElement deleteTitle;
	
	
	@FindBy(xpath = "//*[contains(@text,'Cancel Download') or contains(@text,'cancel download') or contains(@text,'CANCEL DOWNLOAD')]")
	public WebElement canceldownloadingThing;

	@FindBy(id = "com.viacom18.vootkids:id/header_text")
	public WebElement editDownloadPageheader;

	@FindBy(id = "com.viacom18.vootkids:id/checkbox_delete_download_item")
	public WebElement deleteDownloadButton;

	@FindBy(xpath = "//android.widget.FrameLayout[@index='0']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_download_item']")
	public WebElement editdownloadThumbnail;

//	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='1']//*[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
//	public WebElement editdownloadTitle;

	@FindBy(xpath = "//android.widget.TextView[@text='EDIT DOWNLOADS' and @resource-id='com.viacom18.vootkids:id/header_text']")
	public WebElement editdownloadTitle;
	
	@FindBy(id = "com.viacom18.vootkids:id/download_status_imageview")
	public WebElement downloadIcon;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Downloading')]")
	public WebElement downloadingEbook;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Downloaded')]")
	public WebElement DownloadedStatus;
	
	@FindBy(xpath="//android.widget.TextView[@text='Downloads' or @text='DOWNLOADS']")
	public WebElement downloadsPageHeader;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public List<WebElement> downloadsTitles;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item']")
	public List<WebElement> cancelDownloadIcon;

	@FindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/delete_button']")
	public WebElement deleteButton;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/positive_btn_dialog']")
	public WebElement confirmDeleteButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog' and @text='Are you sure you want to delete this item?']")
	public WebElement deleteBodyDialogSingle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog' and @text='Are you sure you want to delete all selected items?']")
	public WebElement deleteBodyDialogMultiple;
	
	@FindBy(xpath = "//android.widget.FrameLayout[@index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public WebElement editdownloadDescription;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text' and @text='EDIT DOWNLOADS' or @text='Edit Downloads' or @text='edit downloads']")
	public WebElement editDownloadsScreen;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Download complete')]")
	public WebElement notificationDownloadComplete;
	
	@FindBy(xpath = "//android.widget.Button[contains(@text,'SEE ALL') or contains(@text,'See All') or contains(@class,'see all')]")
	public WebElement seeall;

	@FindBy(xpath = "//android.widget.TextView[@text='DOWNLOADS' and @resource-id='com.viacom18.vootkids:id/header_text']")
	public WebElement DownloadsScreen;

	@FindBy(id = "com.android.systemui:id/pinEntry")
	public WebElement devicepinentry;

	@FindBy(id = "com.viacom18.vootkids:id/readerWebView")
	public WebElement readerView;

	// com.viacom18.vootkids:id/checkbox_delete_download_item

	// com.viacom18.vootkids:id/header_text

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item'][@index='0']//android.widget.FrameLayout[contains(@class,'FrameLayout')]//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.view.View[@resource-id='com.viacom18.vootkids:id/squareProgressBar']")
	public WebElement downloadCheck;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_delete_title' and @text='DELETE ( 1 )']")
	public WebElement delete1item;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_delete_title' and @text='DELETE ( 2 )']")
	public WebElement delete2item;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog' and @text='YES' or @text='Yes' or @text='yes']")
	public WebElement deleteYes;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog' and @text='NO' or @text='No' or @text='no']")
	public WebElement deleteNo;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/btn_close_dialog']")
	public WebElement deleteClose;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item' and @checked='false']")
	public List<WebElement> cancelDownloadIconUnchecked;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item' and @checked='true']")
	public List<WebElement> cancelDownloadIconChecked;
	
	@FindBy(xpath="//android.widget.TextView[@text='LISTEN AGAIN' or @text='Listen Again' or @text='listen again']")
	public WebElement listenAgain;	
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE']")
	public List<WebElement> downloadCompletedEpisodes;	
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']")
	public List<WebElement> yetToDownloadEpisodes;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public List<WebElement> yetToDownloadEpisodesTitle;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")
	public List<WebElement> yetToDownloadEpisodesSeason;
	
	
	
	public void setDeviceAudioDownloadQuality(String quality) throws Exception
	{
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SettingsPageV2 settingspagev2 = new SettingsPageV2(driver, test);
		LaunchPageV2 launchpagev2 = new LaunchPageV2(driver, test);
		Utilities.verticalSwipeDown(driver);
		Thread.sleep(1000);
		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 30))
		{
			homepagev2.profilepic.click();
			Thread.sleep(1000);
		}
		else
         BasePageV2.reportFail("User Profile icon is not displayed");
     	
		if (Utilities.explicitWaitClickable(driver, settingspagev2.parentZoneButton, 10))
		{
				settingspagev2.parentZoneButton.click();
				test.log(LogStatus.INFO, "Tapped on Parent Zone button");
		}
		else
		{
			test.log(LogStatus.FAIL, "failed to Tapped on Parent Zone button");
			BasePageV2.takeScreenshot();
		}
		if (Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 10))
		{
			launchpagev2.parentPinContainer.clear();	
			launchpagev2.parentPinContainer.sendKeys("1111");
			Thread.sleep(1000);
			try{
				driver.hideKeyboard();
				launchpagev2.parentPinContainer.sendKeys("1111");
			}
			catch(Exception e){ e.printStackTrace();}
		}
		 driver.runAppInBackground(Duration.ofSeconds(3));
		 driver.currentActivity();
		 Thread.sleep(10000);
		if (Utilities.explicitWaitClickable(driver, homepagev2.settings, 10))
		{
			homepagev2.settings.click();
		}
		else
         BasePageV2.reportFail("Not able to click Settings");
		
		/*if (Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 10))
		{
			launchpagev2.parentPinContainer.clear();	
			launchpagev2.parentPinContainer.sendKeys("1111");
			Thread.sleep(1000);
			try{
				driver.hideKeyboard();
				launchpagev2.parentPinContainer.sendKeys("1111");
			}
			catch(Exception e){ e.printStackTrace();}
		}*/
		
		if(Utilities.explicitWaitClickable(driver,launchpagev2.deviceSectionInSettingsScreen , 5))
		{
			launchpagev2.deviceSectionInSettingsScreen.click();
		}
		else
         BasePageV2.reportFail("Not able to click on Device option in Settings screen");
		
		Utilities.verticalSwipe(driver, settingspagev2.audioDownloadQualitySelectInDeviceScreen);
		if(Utilities.explicitWaitClickable(driver,launchpagev2.deviceSectionInSettingsScreen , 5))
		{
			settingspagev2.audioDownloadQualitySelectInDeviceScreen.click();
		}
		else
         BasePageV2.reportFail("Not able to click on Device option in Settings screen");
		
		if(Utilities.explicitWaitClickable(driver,settingspagev2.LowDownloadQualitySelectInDeviceScreen , 5))
		{
			
				try{
					if(quality.equalsIgnoreCase("high"))
					settingspagev2.HighDownloadQualitySelectInDeviceScreen.click();
					else if (quality.equalsIgnoreCase("medium"))
					settingspagev2.MediumDownloadQualitySelectInDeviceScreen.click();
					else
					settingspagev2.LowDownloadQualitySelectInDeviceScreen.click();	
				}
				catch(Exception e){
					BasePageV2.reportFail("Failed to select the quality -"+quality);
				}
	     }
		else
			BasePageV2.reportFail("Quality selection not found");
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		
		
	}
	
	
	
	public void setDeviceDownloadQuality(String quality) throws Exception
	{
		
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SettingsPageV2 settingspagev2 = new SettingsPageV2(driver, test);
		LaunchPageV2 launchpagev2 = new LaunchPageV2(driver, test);
		Utilities.verticalSwipeDown(driver);
		Thread.sleep(1000);
		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 30))
		{
			homepagev2.profilepic.click();
			Thread.sleep(1000);
		}
		else
         BasePageV2.reportFail("User Profile icon is not displayed");
       
		if (Utilities.explicitWaitClickable(driver, settingspagev2.parentZoneButton, 10))
		{
				settingspagev2.parentZoneButton.click();
				Thread.sleep(3000);
				test.log(LogStatus.INFO, "Tapped on Parent Zone button");
		}
		else
		{
			test.log(LogStatus.FAIL, "failed to Tapped on Parent Zone button");
			BasePageV2.takeScreenshot();
		}
		if (Utilities.explicitWaitVisible(driver, launchpagev2.parentPinContainer, 10))
		{
			launchpagev2.parentPinContainer.clear();	
			launchpagev2.parentPinContainer.sendKeys("1111");
			Thread.sleep(1000);
			try{
				driver.hideKeyboard();
				launchpagev2.parentPinContainer.sendKeys("1111");
			}
			catch(Exception e){ e.printStackTrace();}
		}
		driver.runAppInBackground(Duration.ofSeconds(3));
		 driver.currentActivity();
		 Thread.sleep(10000);
		if (Utilities.explicitWaitClickable(driver, homepagev2.settings, 10))
		{
			homepagev2.settings.click();
		}
		else
         BasePageV2.reportFail("Not able to click Settings");
		
		if(Utilities.explicitWaitClickable(driver,launchpagev2.deviceSectionInSettingsScreen , 15))
		{
			launchpagev2.deviceSectionInSettingsScreen.click();
		}
		else
         BasePageV2.reportFail("Not able to click on Device option in Settings screen");
		
		Utilities.verticalSwipe(driver, settingspagev2.DownloadQualitySelectInDeviceScreen);
		if(Utilities.explicitWaitClickable(driver,launchpagev2.deviceSectionInSettingsScreen , 5))
		{
			settingspagev2.DownloadQualitySelectInDeviceScreen.click();
		}
		else
         BasePageV2.reportFail("Not able to click on Device option in Settings screen");
		
		if(Utilities.explicitWaitClickable(driver,settingspagev2.LowDownloadQualitySelectInDeviceScreen , 5))
		{
			
				try{
					if(quality.equalsIgnoreCase("high"))
					settingspagev2.HighDownloadQualitySelectInDeviceScreen.click();
					else if (quality.equalsIgnoreCase("medium"))
					settingspagev2.MediumDownloadQualitySelectInDeviceScreen.click();
					else
					settingspagev2.LowDownloadQualitySelectInDeviceScreen.click();	
				}
				catch(Exception e){
					BasePageV2.reportFail("Failed to select the quality -"+quality);
				}
	     }
		else
			BasePageV2.reportFail("Quality selection not found");
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		Thread.sleep(1000);
		
		
	}
	
	public void deleteDownload() throws Exception
	{
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);

		for (int i = 0; i <= 10; i++)
		{
			if (Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 1))
			{
				homepagev2.mystuff_tab.click();
				break;
			}
			else Utilities.verticalSwipeDown(driver);

		}

		for (int i = 0; i <= 20; i++)
		{
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 1))
			{
				downloadpagev2.downloadsTabMystuffpage.click();
				break;
			}
			else Utilities.verticalSwipe(driver);
		}

		for (int i = 0; i < 2; i++)
		{
			Utilities.verticalSwipe(driver);
		}

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editDownloadsMystuff, 5))
		{
			downloadpagev2.editDownloadsMystuff.click();
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteDownload, 2))
			{
				downloadpagev2.deleteDownload.click();
				if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteTitle, 2))
					downloadpagev2.deleteTitle.click();
				else test.log(LogStatus.INFO, "'Delete CTA' not displayed");

				if (Utilities.explicitWaitVisible(driver, downloadpagev2.ConfirmDeleteDownload, 2))
					downloadpagev2.ConfirmDeleteDownload.click();
				else test.log(LogStatus.INFO, "'Confirmation Popup' to delete download not displayed");
			}
			else test.log(LogStatus.INFO, "Downloads is empty / Delete icon is not present");

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.BackButtonDownloadPage, 2))
			{
				test.log(LogStatus.INFO, "Clicking on 'Back Button' in 'Edit Downloads' page to check navigation");
				downloadpagev2.BackButtonDownloadPage.click();
			}
			else BasePageV2.reportFail("Back button not displayed in 'Edit Downloads' page");

			if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 2))
			{
				test.log(LogStatus.INFO,
						"Tapping on Back button in 'Edit Downloads' is navigated to Downloads tab in Mystuff page");

			}
			else
			{
				test.log(LogStatus.INFO,
						"Tapping on Back button in 'Edit Downloads' is navigated to Downloads tab in Mystuff page");
				//BasePageV2.takeScreenshot();

			}
			Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
		}
		else test.log(LogStatus.INFO, "Downloads Not Available");

	}
	public void deleteAllDownloads() throws Exception{
		//The method will delete all downloads and then click on My Stuff tab after completion
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		DownloadsPageV2 downloadpagev2=new DownloadsPageV2(driver,test);
		String cancelLocator="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item']";
		String deleteLocator="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/delete_button']";
		String confirmDelete="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']";
		homepagev2.tabClick("My Stuff");
		for (int i = 0; i <= 20; i++)
		{
			if (Utilities.explicitWaitVisibleNew(driver, downloadpagev2.downloadsTabMystuffpage, 1))
			{
				downloadpagev2.downloadsTabMystuffpage.click();
				break;
			}
			else Utilities.verticalSwipe(driver);
		}
		Utilities.verticalSwipe(driver);
		if(Utilities.explicitWaitVisibleNew(driver, downloadpagev2.downloadsNothingHere, 5)) {
			for(int j=0;j<=10;j++) {
				Utilities.verticalSwipeReverse(driver);
			}
			for(int i=0;i<30;i++) {
				if(Utilities.explicitWaitVisibleNew(driver, homepagev2.carousalCard, 1)) {
					Utilities.verticalSwipeReverse(driver);
					break;
				}
				else {
					Utilities.verticalSwipeReverse(driver);
				}
			}			
		}
		else if (Utilities.explicitWaitVisibleNew(driver, downloadpagev2.editDownloadsMystuff, 5)) {		
			if (Utilities.explicitWaitClickable(driver, downloadpagev2.editDownloadsMystuff, 5))
			{
				try {
						downloadpagev2.editDownloadsMystuff.click();
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
							driver.navigate().back();
							for(int j=0;j<=10;j++) {
								Utilities.verticalSwipeReverse(driver);
							}
							for(int i=0;i<30;i++) {
								if(Utilities.explicitWaitVisible(driver, homepagev2.carousalCard, 1)) {
									Utilities.verticalSwipeReverse(driver);
									break;
								}
								else {
									Utilities.verticalSwipeReverse(driver);
								}
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
		}
		else {
			test.log(LogStatus.INFO, "There are no downloads present");
		}
	}

	public void deleteDownloadTitle(String title) throws Exception
	{
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		DownloadsPageV2 downloadpagev2 = new DownloadsPageV2(driver, test);

		
		for (int i = 0; i <= 10; i++)
		{
			if (Utilities.explicitWaitVisible(driver, homepagev2.mystuff_tab, 1))
			{
				homepagev2.mystuff_tab.click();
				break;
			}
			else Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);

		}

		for (int i = 0; i <= 20; i++)
		{
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.downloadsTabMystuffpage, 1))
			{
				downloadpagev2.downloadsTabMystuffpage.click();
				break;
			}
			else Utilities.verticalSwipe(driver);
		}

	
			Utilities.verticalSwipe(driver);
		

		if (Utilities.explicitWaitVisible(driver, downloadpagev2.editDownloadsMystuff, 5))
			{
			downloadpagev2.editDownloadsMystuff.click();
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteDownload, 2))
			{
			try{
				Thread.sleep(1000);
				driver.findElementByXPath("//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title' and contains(@text,'"+title+"')]/ancestor::android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']//android.widget.CheckBox").click();
				
				if (Utilities.explicitWaitVisible(driver, downloadpagev2.deleteTitle, 2))
					downloadpagev2.deleteTitle.click();
				else test.log(LogStatus.INFO, "'Delete CTA' not displayed");

				if (Utilities.explicitWaitVisible(driver, downloadpagev2.ConfirmDeleteDownload, 2))
					downloadpagev2.ConfirmDeleteDownload.click();
				else test.log(LogStatus.INFO, "'Confirmation Popup' to delete download not displayed");
				
			}
			catch(Exception e){}
			
			}
			
			else test.log(LogStatus.INFO, "Downloads is empty / Delete icon is not present");
			
			if (Utilities.explicitWaitVisible(driver, downloadpagev2.BackButtonDownloadPage, 2))
			{
				//test.log(LogStatus.INFO, "Clicking on 'Back Button' in 'Edit Downloads' page to check navigation");
				downloadpagev2.BackButtonDownloadPage.click();
			}
			else BasePageV2.reportFail("Back button not displayed in 'Edit Downloads' page");

		Thread.sleep(1000);
			Utilities.verticalSwipeDown(driver, homepagev2.mystuff_tab);
			Utilities.verticalSwipeDown(driver);
			
			}
	
		else 
			test.log(LogStatus.INFO, "No Videos/Audios are in downloads Tab");
	


	}
	
	
	public void setDeviceDownloadQuality1(String quality) throws Exception
	{
		
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SettingsPageV2 settingspagev2 = new SettingsPageV2(driver, test);
		LaunchPageV2 launchpagev2 = new LaunchPageV2(driver, test);
		
		
		Utilities.verticalSwipe(driver, settingspagev2.DownloadQualitySelectInDeviceScreen);
		if(Utilities.explicitWaitClickable(driver,launchpagev2.deviceSectionInSettingsScreen , 5))
		{
			settingspagev2.DownloadQualitySelectInDeviceScreen.click();
		}
		else
         BasePageV2.reportFail("Not able to click on Device option in Settings screen");
		
		if(Utilities.explicitWaitClickable(driver,settingspagev2.LowDownloadQualitySelectInDeviceScreen , 5))
		{
			
				try{
					if(quality.equalsIgnoreCase("high"))
					settingspagev2.HighDownloadQualitySelectInDeviceScreen.click();
					else if (quality.equalsIgnoreCase("medium"))
					settingspagev2.MediumDownloadQualitySelectInDeviceScreen.click();
					else
					settingspagev2.LowDownloadQualitySelectInDeviceScreen.click();	
				}
				catch(Exception e){
					BasePageV2.reportFail("Failed to select the quality -"+quality);
				}
	     }
		else
			BasePageV2.reportFail("Quality selection not found");

		
	}
	
	public void deleteAllDownloadsFromEditDownloads() throws Exception{
		//The method will delete all downloads and then click on My Stuff tab after completion
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		DownloadsPageV2 downloadpagev2=new DownloadsPageV2(driver,test);
		String cancelLocator="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item']";
		String deleteLocator="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/delete_button']";
		String confirmDelete="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']";
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
				test.log(LogStatus.FAIL, "Unable to click on Delete button");
			}
			try {
				driver.findElement(By.xpath(confirmDelete)).click();
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to Confirm Delete button");
			}
			cancelButtons=driver.findElements(By.xpath(cancelLocator));
		}
		if(cancelButtons.size()==0) {
			test.log(LogStatus.INFO, "Deleted all Downloads");	
		}
		else {
			test.log(LogStatus.FAIL, "Failed to delete all Downloads");
		}
	}
	
	// Download Movie card from movie tray 
	
		public void movieDownload() throws Exception {
			String trayName="";
			String trayNameUpper="";
			String trayNameCamel="";
			String movieTitle="" , episodefirsttitle = "",subtitletext2="";
			HomePageV2 homepagev2 = new HomePageV2(driver, test);
			//Hit Watch API and find the name of the Movies tray
			RestAssured.baseURI="https://api.vootkids.com/app/ui/v1/tabs/watch.json?limit=10&offSet=0";
			Response watchTabResponse =(Response) RestAssured.given().relaxedHTTPSValidation().get().body();
			int responseItems=watchTabResponse.jsonPath().get("assets.size()");
			boolean foundMoviesTrayFromAPI=false;
			for(int traverseResponse=0;traverseResponse<responseItems;traverseResponse++) {
				if(watchTabResponse.jsonPath().get("assets["+traverseResponse+"].trayName").equals("featuredMovies")) {
					trayName=watchTabResponse.jsonPath().get("assets["+traverseResponse+"].title");
					test.log(LogStatus.INFO, "Movie tray name fetched from Watch API is: \""+trayName+"\"");
					foundMoviesTrayFromAPI=true;
					break;
				}		
			}
			if(foundMoviesTrayFromAPI==false) {
				test.log(LogStatus.INFO, "Failed to fetch Movie tray name from Watch API");
			}
			
			if(foundMoviesTrayFromAPI==true) {
				homepagev2.tabClick("Watch");
				trayNameUpper=trayName.toUpperCase();
				trayNameCamel=homepagev2.convertCamelCase(trayName);
				String movieFirstItem="//android.widget.TextView[@text='"+trayName+"' or @text='"+trayNameUpper+"' or @text='"+trayNameCamel+"' and @resource-id='com.viacom18.vootkids:id/recent_title_text']";
			
				for(int scroll=0;scroll<=50;scroll++) {
					try {
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						driver.findElement(By.xpath(movieFirstItem)).click();
						break;
					}
					catch(Exception e) {
						Utilities.verticalSwipe(driver);
						if(scroll==50) {
							test.log(LogStatus.FAIL, "Failed to click on tray \""+trayName+"\"");
							homepagev2.takeScreenshot();
						}
					}
				}
			
		     }
			
			//  click on card and check the downloads option is there r not and download
			int lisize= 0;

				
				List<WebElement> li = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']"));

				 lisize = li.size();
				 test.log(LogStatus.INFO, "The size of the list is : " + lisize );

				 WatchPageV2 watchpagev2 = new WatchPageV2(driver, test);
			for (int i = 0 ; i < lisize ; i++ ) {
				try {
					li.get(i).click();
					// Click on play button in movie info page
					  if(Utilities.explicitWaitClickable(driver, watchpagev2.playBtn, 60)) {
						  Utilities.verticalSwipe(driver);
						  Utilities.verticalSwipe(driver);
						  if(Utilities.explicitWaitClickable(driver, downloadSimbolBtn, 10)) {
							  downloadSimbolBtn.click();
							  Thread.sleep(50000);
							  break;
						  }else {
							  driver.pressKeyCode(AndroidKeyCode.BACK);
						  }
						
					  }else {
						  BasePageV2.reportFail("Not able to navigated to movie info page");
					  }
				} catch (Exception e) {
					// TODO: handle exception
				}
			
				if(i == 10) {
					test.log(LogStatus.FAIL, "download option movie not found in movie page");
					BasePageV2.takeScreenshot();
				}
				
				
				
			}
			
			driver.pressKeyCode(AndroidKeyCode.BACK);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			
			for(int j = 0 ; j < 15 ; j++) {
				Utilities.verticalSwipeReverse(driver);
			}
			
			
			
		}
	
	
		public void deleteAllDownloadsAndClickMyStuffTab() throws Exception{
			//The method will delete all downloads and then click on My Stuff tab after completion
			HomePageV2 homepagev2=new HomePageV2(driver,test);
			DownloadsPageV2 downloadpagev2=new DownloadsPageV2(driver,test);
			String cancelLocator="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_delete_download_item']";
			String deleteLocator="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/delete_button']";
			String confirmDelete="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']";
			homepagev2.tabClick("My Stuff");
			for (int i = 0; i <= 20; i++)
			{
				if (Utilities.explicitWaitVisibleNew(driver, downloadpagev2.downloadsTabMystuffpage, 1))
				{
					downloadpagev2.downloadsTabMystuffpage.click();
					break;
				}
				else Utilities.verticalSwipe(driver);
			}
			Utilities.verticalSwipe(driver);
			if(Utilities.explicitWaitVisibleNew(driver, downloadpagev2.downloadsNothingHere, 5)) {
				//clicj My stuff
				if(Utilities.explicitWaitClickable(driver, myStuffTabMystuffpage, 50)) {
					myStuffTabMystuffpage.click();
					for(int i = 0 ; i < 15 ; i ++) {
						Utilities.verticalSwipeReverse(driver);
					}
				}else {
					test.log(LogStatus.FAIL, "Not able to click on my stuff button in My stuff page");
				}
							
			}
			else if (Utilities.explicitWaitVisibleNew(driver, downloadpagev2.editDownloadsMystuff, 5)) {		
				if (Utilities.explicitWaitClickable(driver, downloadpagev2.editDownloadsMystuff, 5))
				{
					try {
							downloadpagev2.editDownloadsMystuff.click();
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
								driver.navigate().back();
								if(Utilities.explicitWaitClickable(driver, myStuffTabMystuffpage, 50)) {
									myStuffTabMystuffpage.click();
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
			}
			else {
				test.log(LogStatus.INFO, "There are no downloads present");
			}
		}
		
		
		//Method to switch profile based on index
		public static boolean switchProfile(int profileNumber) throws Exception {
			HomePageV2 homePage = new HomePageV2(driver, test);
			boolean switchFlag = false;
			if (Utilities.explicitWaitVisibleNew(driver, homePage.profilepic, 10)) {
				homePage.profilepic.click();
				Thread.sleep(3000);
				try {
					String xpath = "//*[@resource-id='com.viacom18.vootkids:id/profile_list']//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='" + profileNumber+"']";

					String profiletitle = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='" + profileNumber+"']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text']";
					profiletitle = driver.findElement(By.xpath(profiletitle)).getText();
					profiles.add(profiletitle);
					WebElement element = driver.findElement(By.xpath(xpath));
					try {
						element.click();
					} catch (Exception e) {
						// TODO: handle exception
					}
					switchFlag = true;
					Thread.sleep(10000);
					test.log(LogStatus.INFO, "Switched to profile " + profileNumber);
					driver.navigate().back();
					/*if (element.isSelected()) {
						
						
					}*/

				} catch (Exception e) {
					BasePageV2.reportFail("Profiles were not displayed");
				}

			} else {
				BasePageV2.reportFail("Profile Icon was not displayed");
			}

			if (switchFlag == true)
				return true;
			else
				return false;
		}
		
//	//////////End////////////////////
	
	
	
	
	
	
}
