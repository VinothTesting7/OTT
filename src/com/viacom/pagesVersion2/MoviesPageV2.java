package com.viacom.pagesVersion2;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;
import io.appium.java_client.android.AndroidDriver;

public class MoviesPageV2 extends BasePageV2{
	public MoviesPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement relatedcardTitle;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='true']")
	public WebElement movieDetailsPageFavouriteIconSelected;
	
	@FindBy(xpath="//*[@text='Movies' or @text='MOVIES' and @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement favMovieTab;
	
	@FindBy(xpath="//*[@text='Books' or @text='BOOKS' and @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement favBooksTab;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_edit")
	public WebElement editLinkFav;
	
	@FindBy(id="com.viacom18.vootkids:id/checkbox_un_fav_selector")
	public WebElement unfavIcon;
	
	@FindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/positive_button_container']")
	public WebElement okBtn;

	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/button_back']")
	public WebElement movieDetailsPageBackButton;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/imageview_media_image']")
	public WebElement movieDetailsPageMovieImage;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector']")
	public WebElement movieDetailsPageFavouriteIcon;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/button_preview' and @text='PLAY' or @text='Play' or @text='play']")
	public WebElement movieDetailsPagePlayButton;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement movieDetailsPageMovieTitle;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_author_name']")
	public WebElement movieDetailsPageMovieYear;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_author_name']")
	public WebElement movieDetailsPageMovieLangs;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_description']")
	public WebElement movieDetailsPageMovieDescription;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/download_status_textview' and @text='Download Movie' or @text='download movie' or @text='DOWNLOAD MOVIE']")
	public WebElement movieDetailsPageDownloadButton;
	
	@FindBy(xpath="//*[contains(@text,'Downloading') or contains(@text,'downloading') or contains(@text,'DOWNLOADING')]")
	public WebElement movieDetailsPageDownloading;
	
	@FindBy(xpath="//*[contains(@text,'Cancel Download') or contains(@text,'cancel download') or contains(@text,'CANCEL DOWNLOAD')]")
	public WebElement movieDetailsPageCancelDownload;
	
	@FindBy(xpath="//*[@text='YES' or @text='Yes' or @text='yes']")
	public WebElement movieDetailsPageCancelDownloadYes;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='RELATED MOVIES' or @text='Related Movies' or @text='related movies']")
	public WebElement movieDetailsPageRelatedMoviesTrayTitle;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/parent_layout']//*[@resource-id='com.viacom18.vootkids:id/grid_image_container']")
	public WebElement movieDetailsPageRelatedMoviesTrayFirstMovie;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']")
	public WebElement addedToFavouritesOK;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/duration_player']")
	public WebElement playerCurrentDuration;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/button_play_pause_toggle']")
	public WebElement playPauseButton;	
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/setting_item_list']//android.widget.ImageView//parent::android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title']")
	public WebElement defaultSelectedLanguage;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'Select Language') or contains(@text,'SELECT LANGUAGE') or contains(@text,'select language') or contains(@text,'Select language')]//following-sibling::android.widget.TextView")
	public WebElement optionsPageLanguage;
	
	@FindBy(xpath="//android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title']")
	public List<WebElement> availableLanguages;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/player_setting_close_btn']")
	public WebElement optionsPageClose;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/page_title' and @text='SELECT LANGUAGE' or @text='Select Language' or @text='Select language']")
	public WebElement selectLangPageTitle;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title' and @text='OPTIONS' or @text='Options' or @text='options']")
	public WebElement optionsPageTitle;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'Stream Quality') or contains(@text,'STREAM QUALITY') or contains(@text,'stream quality')]")
	public WebElement moviePlayerSettingsStreamQualityText;	
	
	@FindBy(xpath="//android.widget.TextView[@text='Auto']")
	public WebElement moviePlayerSettingsSelectQualityDefault;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/page_title' and contains(@text,'Stream Quality') or contains(@text,'STREAM QUALITY') or contains(@text,'stream quality')]")
	public WebElement moviePlayerSettingsStreamQualityTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='Auto']")
	public WebElement moviePlayerSettingsStreamQualityAuto;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='Low']")
	public WebElement moviePlayerSettingsStreamQualityLow;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='Medium']")
	public WebElement moviePlayerSettingsStreamQualityMedium;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='High']")
	public WebElement moviePlayerSettingsStreamQualityHigh;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/player_setting_close_btn']")
	public WebElement moviePlayerSettingsStreamQualityClose;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/media_option_list_item']//android.widget.TextView[contains(@text,'Select Language')]")
	public WebElement selectLanguageDropdown;

	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvFirstMovieTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvSecondMovieTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvThirdMovieTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lvFirstMovieYear;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	public WebElement lvFirstMovieDuration;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lvFirstMovieProgressBar;
	
	
	
	
	
}