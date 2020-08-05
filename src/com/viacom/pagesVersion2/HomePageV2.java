package com.viacom.pagesVersion2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.View;

import org.apache.log4j.BasicConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/*************************************************************************************
 * Class : HomePage Purpose : This class is used for selecting various sections
 * of voot Remarks : none Author : Roja KC, Ifocus Modifications: 26 May 2017-
 * First created Modified by entire team
 **************************************************************************************/
public class HomePageV2 extends BasePageV2 {
	static String pawd = "";

	public HomePageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static String pin = "1111";
	
	@FindBy(xpath="(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text'])[1]")
	public WebElement firstProfileTile;
	
	
	
	@FindBy(xpath="(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']/..)[1]")
	public WebElement firstProfile;
	
	@FindBy(xpath="(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text'])[2]")
	public WebElement secondProfileTile;
	
	@FindBy(xpath="(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']/..)[2]")
	public WebElement secondProfile;
	
	@FindBy(id = "com.viacom18.vootkids:id/input_pinView") // Parent Pin// container
    public WebElement parentPinContainer;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/tab_image_selected']/following-sibling::android.widget.TextView[@text='My Stuff' and @resource-id='com.viacom18.vootkids:id/tab_image_selected_txt']")
	public WebElement myStuffTexticon;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/tab_image']/following-sibling::android.widget.TextView[@text='Watch' and @resource-id='com.viacom18.vootkids:id/tab_image_txt']")
	public WebElement watchTexticon;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/tab_image']/following-sibling::android.widget.TextView[@text='Read' and @resource-id='com.viacom18.vootkids:id/tab_image_txt']")
	public WebElement ReadTexticon;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/tab_image']/following-sibling::android.widget.TextView[@text='Listen' and @resource-id='com.viacom18.vootkids:id/tab_image_txt']")
	public WebElement listenTexticon;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/tab_image']/following-sibling::android.widget.TextView[@text='Learn' and @resource-id='com.viacom18.vootkids:id/tab_image_txt']")
	public WebElement learnTexticon;
	
	
	
	// added by me new
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']")
	public WebElement popUpOKButton;
	
	@FindBy(id="com.viacom18.vootkids:id/text_info_coach")
	public WebElement coachCard;
	
	@FindBy(id="com.viacom18.vootkids:id/button_got_it_animated_view")
    public WebElement learnGotitBtn;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_title")
	public List<WebElement> lastViewedTrayCardsTile;

	@FindBy(id="com.viacom18.vootkids:id/grid_progress_bar")
	public WebElement BookProgressbar;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_description")
	public WebElement BookDiscription;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_title")
	public WebElement BookTitle;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']")
	public WebElement favDialogOKButton;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_video_duration")
	public WebElement episodeDuration;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Movies' or @text='movies' or @text='MOVIES']")
	public WebElement editFavoriteMoviesTab;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'UNFAVOURITE') or contains(@text,'Unfavourite') or contains(@text,'unfavourite')]")
	public WebElement editFavoriteUnfavouriteButton;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_progress_bar")
	public WebElement EpisodeprogressBar;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_description")
	public WebElement EpisodeDiscription;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']")
	public WebElement bookFirstcarosal;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']/android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement lastupdatedintrayb;
	
	@FindBy(id="com.viacom18.vootkids:id/recent_title_text")
	public WebElement lastViewedTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='0']/android.widget.TextView")
	public List<WebElement> firstFavorite;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='1']/android.widget.TextView")
	public List<WebElement> thirdFavorite;
	
	@FindBy(id = "com.viacom18.vootkids:id/close_button")
	public WebElement closePopup;
	
	@FindBy(xpath = "//android.widget.TextView[@text='EDIT DOWNLOADS' or @text='Edit Downloads' or @text='edit downloads']")
	public WebElement editDownloadsButton;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='2']/android.widget.TextView")
	public List<WebElement> secondFavorite;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='0']/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/category_icon']")
	public WebElement firstFavoriteMediaIcon;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='1']/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/category_icon']")
	public WebElement secondFavoriteMediaIcon;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='2']/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/category_icon']")
	public WebElement thirdFavoriteMediaIcon;

	@FindBy(xpath = "//android.widget.TextView[@text='Edit' and @resource-id='com.viacom18.vootkids:id/favourite_edit_txt']")
	public WebElement editFavorite;

	@FindBy(id = "com.viacom18.vootkids:id/edit_text_forgot_password")
	public WebElement editTextForGotpassword;

	@FindBy(id = "com.viacom18.vootkids:id/btn_forgot_password")
	public WebElement sendNewPasswordBtn;

	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/item_head' and contains(@text,'Profiles') or contains(@text,'profiles') or contains(@text,'PROFILES')]")
	public WebElement profilestab;

	@FindBy(xpath = "//android.widget.TextView[@text='RESET REQUEST' or resource-id='com.viacom18.vootkids:id/header_dialog' or @text='Reset Request']")
	public WebElement resetRequestFGPWD;

	@FindBy(xpath = "//android.widget.Button[@text='BACK TO LOGIN' or resource-id='com.viacom18.vootkids:id/positive_btn_dialog' or @text='Back To Login']")
	public WebElement backToLoginBtn;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement profileBtn;

	// End new property

	@FindBy(id = "com.viacom18.vootkids:id/audio_skin_subtitle")
	public WebElement audiosubTitle;

	// Added by me parameters below once

	@FindBy(xpath = "//*[contains(@id='com.viacom18.vootkids:id/empty_view')]")
	public WebElement onNowNothingHere;

	@FindBy(className = "android.widget.ProgressBar")
	public List<WebElement> progressBar;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='Channels']")
	public WebElement watchTab_channels;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='On Now']")
	public WebElement watchTab_onNOwTab;

	@FindBy(xpath = "//android.widget.ImageView[@id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement profileIcon;

	@FindBy(xpath = "//android.view.ViewGroup[@index='0']")
	public WebElement onNow_tray_ViewGroup0;

	@FindBy(xpath = ("//android.widget.ImageView[@resource-id = 'com.viacom18.vootkids:id/btn_back']"))
	public WebElement Chennels_Back_btn;
	
	@FindBy(xpath = "//android.widget.Button[@text='ALLOW' or @text='Allow' or @text='allow']")
	public WebElement homeAllowButton;	
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/btn_info_cancel']//parent::android.view.ViewGroup")
	public WebElement coachCardCancel;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Create New Profile') or contains(@text,'create new profile') or contains(@text,'CREATE NEW PROFILE')]") // Create
	// pin
	public WebElement createnewprofilebtn;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='Characters']")
	public WebElement channels_characters;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab']//android.widget.TextView[@text='Schedule']")
	public WebElement channels_schedule;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement channels_characters_kids_card;

	@FindBy(xpath = "//android.widget.TextView[@text='FAVOURITES'  and @resource-id='com.viacom18.vootkids:id/recent_title_text']") // Parent																																																								// container
	public WebElement favouritesTray;

	@FindBy(xpath = "//android.widget.TextView[@text='SETTINGS' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement settingsScreen;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastViewedItem;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='FAVOURITES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']/..")
	public WebElement favouritesTrayFirstEpisodeContent;

	@FindBy(xpath = "//android.widget.TextView[@text='CHANNELS']")
	public WebElement channels_characters_CHANNELS_Text;

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement profilepic;

	@FindBy(linkText = "BLAZE OF GLORY16")
	public WebElement element;

	@FindBy(id = "com.viacom18.vootkids:id/home_icon")
	public WebElement kidslogoicon;

	@FindBy(id = "com.viacom18.vootkids:id/btn_cast_menu")
	public WebElement casticon;

	@FindBy(id = "com.viacom18.vootkids:id/main.collapsing")
	public WebElement maintab;

	@FindBy(id = "com.viacom18.vootkids:id/tabs")
	public WebElement actiontab;

	@FindBy(className = "android.widget.ProgressBar")
	public WebElement progressBarLoader;

	@FindBy(id = "com.viacom18.vootkids:id/parent_for_carousal")
	public WebElement homecarousal_layout;

	@FindBy(id = "com.viacom18.vootkids:id/segmented_tray_tab")
	public WebElement swipeto_tabs;

	@FindBy(xpath = "//android.support.v7.app.ActionBar$Tab[@selected='true']")
	public WebElement highlighted_icon;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']")
	public WebElement current_img;

	@FindBy(id = "com.viacom18.vootkids:id/btn_download_item")
	public WebElement downloadItem;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='0']")
	// @FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/tab_image'])[0]"))
	public WebElement mystuff_tab;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='1']")
	// @FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/tab_image'])[1]"))
	public WebElement watch_tab;

	// new xpath
	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='2']")
	// @FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/tab_image'])[2]"))
	public WebElement read_tab;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='3']")
	// @FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/tab_image'])[3]"))
	public WebElement Listen_tab;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='4']")
	// @FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/tab_image'])[4]"))
	public WebElement search_tab;

	@FindBy(xpath = "//android.widget.TextView[@text='Switch profile' or @text='SWITCH PROFILE' or @text='Switch Profile']")
	public WebElement switch_prof;

	@FindBy(id = "com.viacom18.vootkids:id/btn_cancel")
	public WebElement btn_cancel_fromprofile;

	@FindBy(id = "com.viacom18.vootkids:id/btn_edit_profile")
	public WebElement btn_editprofile;

	@FindBy(id = "com.viacom18.vootkids:id/btn_parent_zone")
	public WebElement btn_parentzone;

	@FindBy(xpath = "//android.widget.TextView[@text='My Stuff']")
	public WebElement mystuff_text;

	@FindBy(id = "com.viacom18.vootkids:id/parent_delete_btn")
	public WebElement deleteProfilebutton;

	@FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	public WebElement allowpermissionbtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Live Now']")
	public WebElement watchtab_text;

	@FindBy(xpath = "//android.widget.TextView[@text='MOST POPULAR EBOOKS']")
	public WebElement readtab_text;

	@FindBy(xpath = "//android.widget.TextView[@text='MOST POPULAR AUDIOS']")
	public WebElement Listentab_text;

	@FindBy(xpath = "//android.widget.EditText[@text='Search']")
	public WebElement searchtab_text;

	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container']")
	public WebElement mast_carousel;

	@FindBy(xpath = "//android.widget.TextView[@text='JUST FOR YOUabc']//ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup")
	public List<WebElement> justforu_size;

	@FindBy(xpath = "//android.widget.TextView[@text='Just For You']")
	public WebElement justforu_text_inside;

	@FindBy(xpath = "//android.widget.TextView[@text='JUST FOR YOU' or @text='Just For You' or @text='just for you']")
	public WebElement justforu_text;

	@FindBy(id = "com.viacom18.vootkids:id/btn_back")
	public WebElement justforu_bckbtn;

	// @FindBy(xpath="//android.widget.TextView[@text='JUST FOR
	// YOUabc']//ancestor::android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup//android.widget.TextView[resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	@FindBy(id = "com.viacom18.vootkids:id/grid_video_duration")
	public WebElement justforu_video_duration;

	@FindBy(id = "com.viacom18.vootkids:id/grid_title")
	public WebElement justforu_Grid_title;

	@FindBy(xpath = "//android.widget.TextView[@text='JUST FOR YOU']/../..//android.view.ViewGroup")
	public List<WebElement> titles;

	@FindBy(xpath = "//android.widget.TextView[@text='FAVOURITESabc']")
	public WebElement favourites_text;

	@FindBy(xpath = "//android.widget.TextView[@text='FAVOURITES']")
	public WebElement favourites_text_insidefavourite;

	@FindBy(id = "com.viacom18.vootkids:id/btn_edit")
	public WebElement edit_favourites;

	@FindBy(xpath = "//android.widget.TextView[@text='ALL CHARACTERSabc']")
	public WebElement allcharecters_text;

	@FindBy(xpath = "//android.widget.TextView[@text='ALL CHARACTERSabc']/..//android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup")
	public WebElement allcharecters_firstcharecter;

	@FindBy(id = "com.viacom18.vootkids:id/button_fav")
	public WebElement fav_btn;

	@FindBy(id = "com.viacom18.vootkids:id/btn_back")
	public WebElement bckbtn;

	@FindBy(xpath = "//android.widget.TextView[@text='MOST POPULAR EBOOKSabc']")
	public WebElement popular_books;

	@FindBy(xpath = "//android.widget.TextView[@text='MOST POPULAR EBOOKSabc']/..//android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup")
	public WebElement popular_books_firstcharecter;

	@FindBy(id = "com.viacom18.vootkids:id/btn_settings_menu") // Settings icon // in profile 	// pic page
	public WebElement settings;

	@FindBy(id = "com.viacom18.vootkids:id/settings_log_btn") // Logout
	public WebElement logout;

	@FindBy(xpath = "//*[contains(@text,'yes') or contains(@text,'YES')]") // Logout																							// Button
	public WebElement confirmLogout;
	
	@FindBy(xpath="//android.widget.TextView[@text='SEE ALL' or @text='See All' or @text='see all']")
	public WebElement seeAll;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text']")
	public WebElement pageTitle;	

	@FindBy(id = "com.viacom18.vootkids:id/btn_info_cancel") // Cancel all
																// notification
																// after signing
																// up for
																// freshly
																// installed app
	public WebElement freshAppNotificationCancel;

	@FindBy(id = "com.viacom18.vootkids:id/input_pinView")
	public WebElement parentalPin;
	
	

	@FindBy(id = "com.viacom18.vootkids:id/error_message_phone_number")
	public WebElement phoneNumberError;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'CREATE PIN') or contains(@text,'Create Pin') or contains(@text,'create pin')]") // Create
																																				// pin
	public WebElement createPinPage;

	@FindBy(xpath = "//android.widget.FrameLayout[@index='1']//android.widget.TextView")
	public WebElement audioBook;

	@FindBy(id = "com.viacom18.vootkids:id/button_preview")
	public WebElement playAudiobookbutton;

	@FindBy(id = "com.viacom18.vootkids:id/audio_skin_title")
	public WebElement audioTitle;

	@FindBy(id = "com.viacom18.vootkids:id/button_play_pause_toggle")
	public WebElement audioplayPause;

	@FindBy(id = "com.viacom18.vootkids:id/forward_player")
	public WebElement audioForward;

	@FindBy(id = "com.viacom18.vootkids:id/back_ward")
	public WebElement audiobackward;

	@FindBy(id = "com.viacom18.vootkids:id/down_button_audio_skin")
	public WebElement audiominiplayerSwitch;

	@FindBy(id = "com.viacom18.vootkids:id/close_button_audio_skin")
	public WebElement audioplayerClose;

	@FindBy(id = "com.viacom18.vootkids:id/seek_bar")
	public WebElement audioseekBar;

	@FindBy(id = "com.viacom18.vootkids:id/favourite_button")
	public WebElement audiofavouriteIcon;

	@FindBy(id = "com.viacom18.vootkids:id/fragment_audio_playback_controls")
	public WebElement miniplayer;

	@FindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/fragment_audio_playback_controls']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/album_art']")
	public WebElement miniplayerThumbnail;

	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/favourite_button' and @checked='true']")
	public WebElement favourite;

	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/favourite_button' and @checked='false']")
	public WebElement notfavourite;

	@FindBy(id = "com.viacom18.vootkids:id/duration_player")
	public WebElement audiorunningDuration;

	@FindBy(id = "com.viacom18.vootkids:id/totla_duration_player")
	public WebElement audiototalDuration;

	@FindBy(id = "com.viacom18.vootkids:id/btn_playlist_expand")
	public WebElement audioplaylistExpand;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='3']")
	public WebElement listen;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='0']")
	public WebElement hometab;

	@FindBy(id = "com.viacom18.vootkids:id/btn_playlist_collapse")
	public WebElement audioplaylistCollapse;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Related')]")
	public WebElement related;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'More From Author')]")
	public WebElement morefromauthor;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Daily Picks')]")
	public WebElement dailypicks;

	@FindBy(id = "com.viacom18.vootkids:id/seek_bar")
	public WebElement inlinePlayerSeekbar;

	@FindBy(id = "com.viacom18.vootkids:id/title")
	public WebElement inlinePlayerBookTitle;

	@FindBy(id = "com.viacom18.vootkids:id/play_pause")
	public WebElement inlinePlayerPlaypause;

	@FindBy(id = "com.viacom18.vootkids:id/grid_title")
	public WebElement plalistcontentTitle;

	@FindBy(id = "com.viacom18.vootkids:id/grid_description")
	public WebElement plalistcontentAuthor;

	@FindBy(id = "com.viacom18.vootkids:id/listen_again")
	public WebElement listenAgainBtn;

	@FindBy(id = "com.viacom18.vootkids:id/next_image")
	public WebElement AudioOverlay;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'You might also like...')]")
	public WebElement youmayalsoLike;

	@FindBy(xpath = "//android.widget.ImageView[@index='2']")
	public WebElement close;

	@FindBy(id = "com.viacom18.vootkids:id/category_icon")
	public WebElement categoryicon;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@index='0']/android.widget.FrameLayout[@index='0']//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container']/android.widget.TextView")
	public List<WebElement> carousalNames;

	@FindBy(id = "com.viacom18.vootkids:id/player_title")
	public WebElement videoPlayerTitle;

	@FindBy(id = "com.viacom18.vootkids:id/player_title")
	public WebElement videoPlayersettings;

	@FindBy(id = "com.viacom18.vootkids:id/favourite_button")
	public WebElement videoPlayerfavouriteBtn;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Create new') or contains(@text,'Create New') or contains(@text,'create new') or contains(@text,'CREATE NEW')]/..")
	public WebElement createNew;

	@FindBy(id = "com.viacom18.vootkids:id/favourite_button")
	public WebElement videoPlayerclose;

	@FindBy(id = "com.viacom18.vootkids:id/player_center_control")
	public WebElement playercenterControl;

	@FindBy(id = "com.viacom18.vootkids:id/player_skin_container")
	public WebElement playerskin;

	@FindBy(id = "com.viacom18.vootkids:id/grid_description")
	public WebElement EpnumAndDescription;

	@FindBy(id = "com.viacom18.vootkids:id/grid_title")
	public WebElement contentTitle;

	@FindBy(id = "com.viacom18.vootkids:id/grid_item_category")
	public WebElement contentitemCategory;

	@FindBy(id = "com.viacom18.vootkids:id/grid_video_duration")
	public WebElement contentDuration;

	@FindBy(id = "com.viacom18.vootkids:id/search_icon")
	public WebElement search;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'SEARCH') or contains(@text,'Search') or contains(@text,'search')]")
	public WebElement searchPage;

	@FindBy(id = "com.viacom18.vootkids:id/search_edittext")
	public WebElement searchTextBox;

	@FindBy(id = "com.viacom18.vootkids:id/voice_button")
	public WebElement voiceBtn;

	@FindBy(id = "com.viacom18.vootkids:id/button_surprise_me")
	public WebElement surprisemeBtn;

	@FindBy(id = "com.viacom18.vootkids:id/clear_icon")
	public WebElement searchClear;

	@FindBy(id = "com.viacom18.vootkids:id/btn_back")
	public WebElement backButton;

	@FindBy(id = "com.viacom18.vootkids:id/checkbox_fav_selector")
	public WebElement favIcon;

	@FindBy(id = "com.viacom18.vootkids:id/textview_title")
	public WebElement audTitle;

	@FindBy(id = "com.viacom18.vootkids:id/author_container")
	public WebElement audauthorContainer;

	@FindBy(id = "com.viacom18.vootkids:id/textview_author_name")
	public WebElement audauthorname;

	@FindBy(id = "com.viacom18.vootkids:id/narrated_by_container")
	public WebElement naratorContainer;

	@FindBy(id = "com.viacom18.vootkids:id/textview_narrated_by_name")
	public WebElement naratorname;

	@FindBy(id = "com.viacom18.vootkids:id/textview_description")
	public WebElement description;

	@FindBy(xpath = "//android.widget.TextView[@text='Duration']/..")
	public WebElement duration;

	@FindBy(id = "com.viacom18.vootkids:id/download_status_imageview")
	public WebElement downloadIcon;

	@FindBy(id = "com.viacom18.vootkids:id/download_status_textview")
	public WebElement downloadAudioBookText;

	@FindBy(id = "com.viacom18.vootkids:id/recent_title_text")
	public WebElement recentTypeTab;

	@FindBy(id = "com.viacom18.vootkids:id/btn_download_item")
	public WebElement downloadedItem;

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

	@FindBy(id = "com.viacom18.vootkids:id/download_status_textview")
	public WebElement DownloadStatus;

	@FindBy(xpath = "//android.widget.Button[@text='SEE ALL']")
	public WebElement traySeeAll;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement recentViewedClear;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']//parent::android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement recentViewedTray;	
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement recentViewedClearMessage;		
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog']")
	public WebElement recentViewedClearYes;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog']")
	public WebElement recentViewedClearNo;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog_text']")
	public WebElement deleteProfileYes;

	// Episodes related properties

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='1']")
	public WebElement watchtab;

	@FindBy(xpath = "//android.widget.FrameLayout[@index='1']//android.widget.TextView[contains(@text,'Motu Patlu')]")
	public WebElement show;

	@FindBy(xpath = "//android.view.ViewGroup[@id='com.viacom18.vootkids:id/title_container']//android.widget.TextView[contains(@text,'EPISODES')]")
	public WebElement episodesTray;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']") // Parent
																																																					// Pin
																																																					// container
	public List<WebElement> gridTitleDescriptions;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']") // Parent
																											// Pin
																											// container
	public WebElement trayName;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'')]//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']") // Parent
																																																																																						// Pin
																																																																																						// container
	public WebElement trayLayout;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']") // Parent
																																																			// Pin
																																																			// container
	public List<WebElement> gridTitles;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab']")
	public WebElement trayLayoutContainer;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']") // Parent
																																																					// Pin
																																																					// container
	public WebElement gridTitleDescription;

	@FindBy(xpath = "//android.widget.TextView[@text='RELATED SHOWS']")
	public WebElement relatedTray;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Downloaded')]")
	public WebElement DownloadedStatus;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab'][@index='0']//*[contains(@class,'ActionBar')][@index='0']")
	public WebElement AllTab;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab'][@index='0']//*[contains(@class,'ActionBar')][@index='1']")
	public WebElement watchTab;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab'][@index='0']//*[contains(@class,'ActionBar')][@index='2']")
	public WebElement BookTab;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/segmented_tray_tab'][@index='0']//*[contains(@class,'ActionBar')][@index='3']")
	public WebElement ListenTab;

	@FindBy(id = "com.viacom18.vootkids:id/empty_view")
	public WebElement emptyResult;

	@FindBy(xpath = "//android.widget.TextView[@text='EPISODES']")
	public WebElement EpisodesTab;

	@FindBy(id = "com.viacom18.vootkids:id/progress_bar")
	public WebElement loadingAnimator;

	@FindBy(xpath = "//android.widget.TextView[@text='Downloads']")
	public WebElement downloadsTab;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/rc_segment_tray']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='1']")
	public WebElement downloadsNothingHere;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/rc_segment_tray']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='2']")
	public WebElement downloadsSupportText;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/rc_segment_tray']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='0']")
	public WebElement editDownload;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView")
	public List<WebElement> downloadedBooks;

	@FindBy(xpath = "//android.widget.TextView[@text='AVAILABLE SPACE']")
	public WebElement availableSpace;

	@FindBy(xpath = "//android.widget.SeekBar[@resource-id='com.viacom18.vootkids:id/available_storage_space_seek_bar']")
	public WebElement availableSpaceBar;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_available_storage_msg']")
	public WebElement availableSpaceText;

	@FindBy(xpath = "//*[contains(@resource-id,'id/recent_recycler_view')]//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='0']//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/grid_title')]")
	public WebElement Content;

	@FindBy(id = "com.viacom18.vootkids:id/grid_item_image")
	public WebElement gridItemImage;

	@FindBy(id = "com.viacom18.vootkids:id/grid_title")
	public WebElement gridItemTitle;

	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='true']")
	public WebElement favouritedetailpage;

	@FindBy(xpath = "//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='false']")
	public WebElement notfavouritedetailpage;

	@FindBy(id = "com.viacom18.vootkids:id/coach_indicator")
	public WebElement Paginator;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Go here to change your avatars, profile or settings') and @resource-id='com.viacom18.vootkids:id/text_info_coach']")
	public WebElement profileCoachCard;

	@FindBy(id = "com.viacom18.vootkids:id/buddy_icon")
	public WebElement profileCoachCardBuddyIcon;

	@FindBy(id = "com.viacom18.vootkids:id/btn_info_cancel")
	public WebElement profileCoachCardCloseButton;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Switch between videos, books and audiobooks here') and @resource-id='com.viacom18.vootkids:id/text_info_coach']")
	public WebElement tabsCoachCard;

	@FindBy(id = "com.viacom18.vootkids:id/buddy_icon")
	public WebElement tabsCoachCardBuddyIcon;

	@FindBy(id = "com.viacom18.vootkids:id/btn_info_cancel")
	public WebElement tabsCoachCardCloseButton;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Tap the orange Buttons to see more shows') and @resource-id='com.viacom18.vootkids:id/text_info_coach']")
	public WebElement seeAllCoachCard;

	@FindBy(id = "com.viacom18.vootkids:id/buddy_icon")
	public WebElement seeAllCoachCardBuddyIcon;

	@FindBy(id = "com.viacom18.vootkids:id/btn_info_cancel")
	public WebElement seeAllCoachCardCloseButton;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Click here to cast on your TV') and @resource-id='com.viacom18.vootkids:id/text_info_coach']")
	public WebElement tvCastCoachCard;

	@FindBy(id = "com.viacom18.vootkids:id/buddy_icon")
	public WebElement tvCastCoachCardBuddyIcon;

	@FindBy(id = "com.viacom18.vootkids:id/btn_info_cancel")
	public WebElement tvCastCoachCardCloseButton;

	@FindBy(xpath = "//android.view.View[@resource-id='com.viacom18.vootkids:id/btn_cast_menu' and @content-desc='Cast button. Disconnected']")
	public WebElement chromecastIconDisconnected;

	@FindBy(xpath = "//android.view.View[@resource-id='com.viacom18.vootkids:id/btn_cast_menu' and @content-desc='Cast button. Connected']")
	public WebElement chromecastIconConnected;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='LAST VIEWED' or @text='Last viewed']//following-sibling::*[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement watchTabLVClear;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/mr_chooser_route_name']")
	public List<WebElement> castTvList;

	@FindBy(xpath = "//android.widget.Button[@text='STOP CASTING' or @text='Stop Casting' or @text='Stop casting']")
	public WebElement stopCasting;

	@FindBy(xpath = "//android.widget.TextView[@text='No media selected' or @text='No Media Selected']")
	public WebElement noMediaSelected;

	@FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/mr_close']")
	public WebElement stopCastDialogCancel;
	
	@FindBy(id = "com.viacom18.vootkids:id/btn_back")
	public WebElement notificationsDownloadsBackButton;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tray_title_textview' and @text='REWARDS' or @text='Rewards' or @text='rewards']")
	public WebElement rewardsTrayTitle;
	
	@FindBy(xpath = "//*[@text='PLAY' or @text='Play' or @text='play']")
	public WebElement rewardsPlayButton;	
	
	@FindBy(xpath = "//*[@text='See All' or @text='SEE ALL' or @text='see all']")
	public WebElement rewardsSeeAllButton;	
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/linear_layout_rewards_item_container']//android.view.ViewGroup[@index='0']//android.widget.ImageView")
	public WebElement rewardsSticker1;	
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/linear_layout_rewards_item_container']//android.view.ViewGroup[@index='1']//android.widget.ImageView")
	public WebElement rewardsSticker2;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/linear_layout_rewards_item_container']//android.view.ViewGroup[@index='2']//android.widget.ImageView")
	public WebElement rewardsSticker3;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/linear_layout_rewards_item_container']//android.view.ViewGroup[@index='3']//android.widget.ImageView")
	public WebElement rewardsSticker4;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/linear_layout_rewards_item_container']//android.view.ViewGroup[@index='4']//android.widget.ImageView")
	public WebElement rewardsSticker5;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/linear_layout_rewards_item_container']//android.view.ViewGroup[@index='5']//android.widget.ImageView")
	public WebElement rewardsSticker6;
	
	@FindBy(xpath="//*[contains(@class,'ActionBar')][@index='3']")
	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/tab_image'])[3]"))
	public WebElement listen_tab;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedFirstItemTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	public WebElement lastViewedFirstItemDuration;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedFirstItemProgressBar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedSecondItemTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	public WebElement lastViewedSecondItemDuration;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedSecondItemProgressBar;	
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedThirdItemTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	public WebElement lastViewedThirdItemDuration;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedThirdItemProgressBar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='3']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedFourthItemTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='3']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	public WebElement lastViewedFourthItemDuration;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='3']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedFourthItemProgressBar;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='6']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedSeventhItemTitle;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='6']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	public WebElement lastViewedSeventhItemDuration;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='6']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedSeventhItemProgressBar;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='7']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedEighthItemTitle;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='7']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_video_duration']")
	public WebElement lastViewedEighthItemDuration;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='7']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedEighthItemProgressBar;
	
	@FindBy(xpath = "//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public List<WebElement> lastViewedItemTitles;	
	
	@FindBy(xpath = "//android.widget.TextView[@index='0' and @resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement lastViewedTrayTitle;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']")
	public WebElement carousalCard;
	
//	Saravanan - Property added on 1st JAN
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement profileIconNew;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/rewards_item_imageview']")
	public WebElement parentZone_In_SwitchProfile;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/input_pinView']")
	public WebElement pinView_In_ParentZone;
	
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Preferences']")
	public WebElement preferenceTab_In_ParentZone;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title_daily_limit']")
	public WebElement setTimeLimit_In_ParentZone;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Bed time']")
	public WebElement bedTime_In_ParentZone;
	
	@FindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public WebElement toggleOff_In_BedTime;
	
	@FindBy(xpath = "//android.widget.Switch[@text='ON']")
	public WebElement toggleOn_In_BedTime;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_wake_up_time']")
	public WebElement wakeUp_Time;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_sleep_time']")
	public WebElement sleep_Time;
	
	@FindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	public WebElement Ok_Button_In_Calendar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='It’s bed time' or @text='IT’S BED TIME']")
	public WebElement bedTimeTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Reach out to your parent to change bed time from settings']")
	public WebElement bedTimeSubTitle;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/positive_btn_dialog']")
	public WebElement parentZoneButton;
	
	@FindBy(xpath="//android.widget.TextView[@text='MOTU PATLU']")
	public WebElement trayShow;
	
	@FindBy(xpath="//android.widget.TextView[@text='MOTU PATLU']")
	public List<WebElement> trayShowAsList;
	
	@FindBy(xpath="//android.widget.TextView[@text='Ninja Hattori']")
	public WebElement NinjaInGrid;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Ninja']")
	public WebElement NinjaInRecentActivity;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Ninja Hattori']")
	public WebElement NinjaInListviewed;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/rewards_item_imageview']")
	public WebElement rewardsInHomeScreen_With_Clickable;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/rewards_item_imageview']")
	public List<WebElement> rewardsInHomeScreen_With_Clickable_As_List;
	
	@FindBy (xpath = "//android.widget.TextView[@text='SEE ALL']")
	public WebElement seeAllButton;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastViewedFirstBookTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedFirstBookAuthor;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedFirstBookProgressBar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastViewedSecondBookTitle;	
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedSecondBookAuthor;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedSecondBookProgressBar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastViewedThirdBookTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedThirdBookAuthor;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedThirdBookProgressBar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='3']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastViewedFourthBookTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='3']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedFourthBookAuthor;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clear' or @text='clear' or @text='CLEAR']//ancestor::android.view.ViewGroup//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='3']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedFourthBookProgressBar;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='6']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastViewedSeventhBookTitle;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='6']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedSeventhBookAuthor;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='6']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedSeventhBookProgressBar;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='7']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastViewedEighthBookTitle;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='7']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement lastViewedEighthBookAuthor;
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='7']//android.widget.ProgressBar[@resource-id='com.viacom18.vootkids:id/grid_progress_bar']")
	public WebElement lastViewedEighthBookProgressBar;
	

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='FAVOURITES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
    public WebElement favouritesTrayFirstContentTitle;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/profile_list']//android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text']")
	public List<WebElement> profileListNames;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/profile_list']//android.view.ViewGroup//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']")
	public List<WebElement> profileListImages;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/name_edit_text']")
	public WebElement newProfileNameEditBox;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/dob_date_et']")
	public WebElement newProfileDateBox;
	
	@FindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	public WebElement calendarOK;	
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/next_btn']")
	public WebElement nextButton;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout_skill_image']")
	public List<WebElement> favoriteImages;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/lets_go_btn']")
	public WebElement letsGoButton;
	
	@FindBy(id = "com.viacom18.vootkids:id/input_pinView")
	public WebElement pinContainer;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Profiles' or @text='PROFILES' or @text='profiles']")
	public WebElement profilesOptionInSettingsPage;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/selected_tick']")
	public WebElement selectedProfileTick;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/delete_profile']")
	public WebElement deleteProfile;	
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']//following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text' and @text='Create new']")
	public WebElement createNewProfile;	
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/total_hour_title']")
	public WebElement activityTabTotalHours;	
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Preferences' or @text='preferences' or @text='PREFERENCES']")
	public WebElement preferencesTab;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvMovieFirstItemTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvMovieSecondItemTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvMovieThirdItemTitle;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/player_close_btn_ripple']")
	public WebElement loaderRipple;

	static String uid = "qyFuou8JF6N7hA7TcYE1YiHpCVu1";
	static Response resp1;

	static String profilename = "";

	static List<String> profilesList = new ArrayList<String>();

	public static void loginWithoutCoachCancel(String UN, String Pwd) throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		test.log(LogStatus.INFO, "Logging In with valid credentials");
		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5))
			test.log(LogStatus.INFO, "Already in home page");
		else if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25)) {
			test.log(LogStatus.INFO, "Logging in");
			try {
				launchPageV2.loginGateway.click();
			} catch (Exception e) {
				BasePageV2.reportFail("Login Page Gateway button not displayed");
			}
			if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(UN);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Email text field not displayed");
			if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(Pwd);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Password text field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5))
				launchPageV2.loginButton.click();
			else
				BasePageV2.reportFail("Login Button not found");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.selectProfilePage, 5))
				{
				test.log(LogStatus.INFO, "Navigated to select profile page");
				   launchPageV2.selectProfileIcon.click();
				}
			else
				test.log(LogStatus.INFO, "Not navigated to Select Profile Page");

		} else
			BasePageV2.reportFail("Failed to launch Welcome Screen");
		
		if (Utilities.explicitWaitVisible(driver, homepagev2.allowpermissionbtn, 5))
			homepagev2.allowpermissionbtn.click();
		else
			System.out.println("Device permission allow popup is not displayed");
	}

	public static void signinguptogetfreshLogincredentials() throws Exception {

		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		String userName = "ifocus.automation@gmail.com";
		GlobalVariables.userName = userName;
		pawd = "vinoth123";
		// String pin="1234";
		String mobileNumber = "9988776655";

		GlobalVariables.mobilenumber = mobileNumber;

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5)) {
			test.log(LogStatus.INFO, "Logging out from home page");
			HomePageV2.logout();
		} else
			test.log(LogStatus.INFO, "Signing up with Fresh credentials");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 5)) {
			launchPageV2.SignUpfromWelcomePage.click();
		} else
			BasePageV2.reportFail("Signup Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 5)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(userName);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Email Text Field not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 5)) {
			Thread.sleep(500);
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(mobileNumber);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Mobile Number Text Field not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
			Thread.sleep(500);
			launchPageV2.pwdText.clear();
			launchPageV2.pwdText.sendKeys(pawd);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Password Text Field not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.nextButton, 1))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.commonErrors, 2)) {

			if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 5)) {
				Thread.sleep(500);
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(Utilities.generateEmailid());
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Email Text Field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 5)) {
				Thread.sleep(500);
				launchPageV2.mobilenumberText.clear();
				launchPageV2.mobilenumberText.sendKeys(Utilities.generateMobileNumber());
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Mobile Number Text Field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
				Thread.sleep(500);
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(pawd);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Password text Field not displayed");

			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.nextButton, 1))
				launchPageV2.nextButton.click();
			else
				BasePageV2.reportFail("Next Button not displayed");
		} else
			test.log(LogStatus.INFO, "Signing up with Fresh credentials");

		// Create pin

		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
			launchPageV2.buttonCreatePin.click();
		else
			BasePageV2.reportFail("Create Pin Button not displayed");

		// Confirm pin

		if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
			launchPageV2.buttonCreatePin.click();
		else
			BasePageV2.reportFail("Create Pin Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
			launchPageV2.kidsName.clear();
			launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
		} else {
			HomePageV2.reportFail("Kids name text field not displayed");
			// BasePageV2.takeScreenshot();
		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
			launchPageV2.DOB.click();
		else
			HomePageV2.reportFail("DOB Field not displayed");
		// BasePageV2.takeScreenshot();

		if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
			launchPageV2.DOBSelect.click();
		else {
			HomePageV2.reportFail("Date selector not displayed");
			// BasePageV2.takeScreenshot();
		}

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
			test.log(LogStatus.INFO, "Select Profile image page is displayed");
		else
			BasePageV2.reportFail("Select Profile image page is not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		for (int i = 1; i <= 5; i++) {
			try {

				WebElement element = driver.findElement(
						By.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
								+ i + "]"));
				if (i == 5) {
					Utilities.verticalSwipe(driver);
					Utilities.verticalSwipe(driver);
					driver.findElement(By
							.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
							.click();
				} else if (Utilities.explicitWaitVisible(driver, element, 10)) {
					element.click();
				} else {
					HomePageV2.reportFail("Kids Characters not displayed");
					BasePageV2.takeScreenshot();
				}

			} catch (Exception e) {
				// test.log(logStatus, details);
			}

		}
		try {
			WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
			if (Utilities.explicitWaitVisible(driver, Skills1, 5))
				Skills1.click();
			else {
				HomePageV2.reportFail("Skills not displayed");
				// BasePageV2.takeScreenshot();
			}
		} catch (Exception e) {

		}

		if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 25))
			launchPageV2.letsGo.click();
		else
			BasePageV2.reportFail("Let's Go Button not displayed");

		for (int i = 1; i <= 5; i++) {
			if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
				homepagev2.freshAppNotificationCancel.click();
			else
				break;
		}

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
			test.log(LogStatus.INFO, "Sign up Successful");
		else
			BasePageV2.reportFail("Unable to Signup");
	}

	// Click on any tab
	public static void tabClick(String tabName) throws Exception {
		int a = Utilities.tabIndex(driver, tabName);

		try {
			List<WebElement> tabsfromFE = driver.findElementsByXPath("//*[contains(@class,'ActionBar')]");
			if (Utilities.explicitWaitClickable(driver, tabsfromFE.get(a), 5))
				tabsfromFE.get(a).click();
			test.log(LogStatus.INFO, "Clicked on the tab: " + tabName);
		} catch (Exception e) {
			reportFail("Failed to click on Tab: " + tabName);
			e.printStackTrace();
		}
	}

	// Login
	public static void login(String UN, String Pwd) throws Exception {
		test.log(LogStatus.INFO, "Logging In with valid credentials");
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.loginGateway, 20)) {
			test.log(LogStatus.INFO, "Logging in");
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.loginGateway, 20))
				launchPageV2.loginGateway.click();
			else
				BasePageV2.reportFail("Login Page Gateway button not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.allowpermissionbtn, 10))
				homepagev2.allowpermissionbtn.click();
			else
				System.out.println("Device permission allow popup is not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(UN);

				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Email text field not displayed");
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pwdText, 5)) {
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(Pwd);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Password text field not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.loginButton, 30))
				launchPageV2.loginButton.click();
			else
				BasePageV2.reportFail("Login Button not found");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.selectProfilePage, 20))
				test.log(LogStatus.INFO, "Select Profile Page displayed");
			else
				test.log(LogStatus.INFO, "Select Profile Page not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.selectProfileIcon, 5)) {
				test.log(LogStatus.INFO, "Multi user Profile, selecting a profile to login");
				launchPageV2.selectProfileIcon.click();
				Thread.sleep(3000);
			}

			else
				test.log(LogStatus.INFO, "Single User Profile");

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.allowpermissionbtn, 5))
				homepagev2.allowpermissionbtn.click();
			else
				System.out.println("Device permission allow popup is not displayed");
			driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			 driver.currentActivity();
			Thread.sleep(3000);
			for (int i = 1; i <= 5; i++) {
				
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else
					{
					break;
					}
			}
			driver.runAppInBackground(Duration.ofSeconds(3));
			 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.allowpermissionbtn, 5))
				homepagev2.allowpermissionbtn.click();
			else
				System.out.println("Device permission allow popup is not displayed");
			
               for (int i = 1; i <= 5; i++) {
				
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else
					{
					break;
					}
			}
			// abcd
			
			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.closePopup, 3)) {
				test.log(LogStatus.INFO, "closing popup");
				homepagev2.closePopup.click();
			}
				
			else
				{
				
				}

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 5))
				test.log(LogStatus.INFO, "Logged in Successfully");
			else
				BasePageV2.reportFail("Login Failed");
		}
		else if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 5))
			test.log(LogStatus.INFO, "Already in home page");
		else if (Utilities.explicitWaitVisibleNew(driver, homepagev2.createPinPage, 5)) {
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pinContainer, 5)) {
				launchPageV2.pinContainer.clear();
				launchPageV2.pinContainer.sendKeys(pin);
			} else
				BasePageV2.reportFail("Pin Container not displayed");
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.buttonCreatePin, 3))
				launchPageV2.buttonCreatePin.click();
			else
				BasePageV2.reportFail("Create Pin Button not displayed");

			// Confirm pin

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pinContainer, 25)) {
				launchPageV2.pinContainer.clear();
				launchPageV2.pinContainer.sendKeys(pin);
			} else
				BasePageV2.reportFail("Pin Container not displayed");
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.buttonCreatePin, 3))
				launchPageV2.buttonCreatePin.click();
			else
				BasePageV2.reportFail("Create Pin Button not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.kidsName, 25)) {
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
			} else
				reportFail("Kids name text field not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOB, 5))
				launchPageV2.DOB.click();
			else
				HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOBSelect, 5))
				launchPageV2.DOBSelect.click();
			else {
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.createBuddyPage, 1))
				test.log(LogStatus.INFO, "Select Profile image page is displayed");
			else
				BasePageV2.reportFail("Select Profile image page is not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			for (int i = 1; i <= 5; i++) {
				try {

					WebElement element = driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[" + i
									+ "]"));
					if (i == 5) {
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						driver.findElement(By.xpath(
								"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
								.click();
					} else if (Utilities.explicitWaitVisibleNew(driver, element, 10)) {
						element.click();
					} else {
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}

				} catch (Exception e) {
					// test.log(logStatus, details);
				}

			}
			try {
				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisibleNew(driver, Skills1, 5))
					Skills1.click();
				else
					test.log(LogStatus.INFO, "Skills not displayed");
			} catch (Exception e) {

			}

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.letsGo, 60))
				launchPageV2.letsGo.click();
			else
				BasePageV2.reportFail("Let's Go Button not displayed");

			for (int i = 1; i <= 5; i++) {
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else
					break;
			}

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 25))
				test.log(LogStatus.INFO, "Home Page is displayed");
			else
				BasePageV2.reportFail("Home page is not displayed");
			login(UN, Pwd);
		} 
		else if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.CreateProfile, 5)) {
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.kidsName, 25)) {
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
			} else
				reportFail("Kids name text field not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOB, 5))
				launchPageV2.DOB.click();
			else
				HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOBSelect, 5))
				launchPageV2.DOBSelect.click();
			else {
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 30))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.createBuddyPage, 30))
				test.log(LogStatus.INFO, "Select Profile image page is displayed");
			else
				BasePageV2.reportFail("Select Profile image page is not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 30))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			for (int i = 1; i <= 5; i++) {
				try {

					WebElement element = driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[" + i
									+ "]"));
					if (i == 5) {
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						driver.findElement(By.xpath(
								"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
								.click();
					} else if (Utilities.explicitWaitVisibleNew(driver, element, 10)) {
						element.click();
					} else {
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}

				} catch (Exception e) {
					// test.log(logStatus, details);
				}

			}
			try {
				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisibleNew(driver, Skills1, 5))
					Skills1.click();
				else
					test.log(LogStatus.INFO, "Skills not displayed");
			} catch (Exception e) {

			}

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.letsGo, 60))
				launchPageV2.letsGo.click();
			else
				BasePageV2.reportFail("Let's Go Button not displayed");

			for (int i = 1; i <= 5; i++) {
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else
					break;
			}

			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 25))
				test.log(LogStatus.INFO, "Home Page is displayed");
			else
				BasePageV2.reportFail("Home page is not displayed");
			login(UN, Pwd);
		}
		else if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.selectProfilePage, 5)) {
			// add code tp select profile

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.selectProfileIcon, 5)) {
				test.log(LogStatus.INFO, "Multi user Profile, selecting a profile to login");
				launchPageV2.selectProfileIcon.click();
			} else
				reportFail("Profile Icon is not displayed under Select profile page");
			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 5))
				login(UN, Pwd);
			else
				reportFail("Home Page is not displayed after selecting profile");

		}
		// break;
		else if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.loginGateway, 2)) {
			test.log(LogStatus.INFO, "Logging in");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.loginGateway, 25))
				launchPageV2.loginGateway.click();
			else
				BasePageV2.reportFail("Login Page Gateway button not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 25)) {
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(UN);

				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Email text field not displayed");
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pwdText, 5)) {
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(Pwd);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Password text field not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.loginButton, 5))
				launchPageV2.loginButton.click();
			else
				BasePageV2.reportFail("Login Button not found");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.selectProfilePage, 5))
				test.log(LogStatus.INFO, "Select Profile Page displayed");
			else
				test.log(LogStatus.INFO, "Select Profile Page not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.selectProfileIcon, 5)) {
				test.log(LogStatus.INFO, "Multi user Profile, selecting a profile to login");
				launchPageV2.selectProfileIcon.click();
			}

			else
				test.log(LogStatus.INFO, "Single User Profile");
			
			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.allowpermissionbtn, 5))
				homepagev2.allowpermissionbtn.click();
			else
				System.out.println("Device permission allow popup is not displayed");

			for (int i = 1; i <= 5; i++) {
				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 2))
					homepagev2.freshAppNotificationCancel.click();
				else
					break;
			}
			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.allowpermissionbtn, 5))
				homepagev2.allowpermissionbtn.click();
			else
				System.out.println("Device permission allow popup is not displayed");
			
			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 60))
				test.log(LogStatus.INFO, "Logged in Successfully");
			else
				BasePageV2.reportFail("Login Failed");
		} 
		else
			BasePageV2.reportFail("Login Failed");
	}

	// Logout
	public static void logout() {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		test.log(LogStatus.INFO, "Logging out");
		try {
			if (Utilities.explicitWaitClickableNew(driver, homepagev2.profilepic, 20)) {
				//BasePageV2.takeScreenshot();
				homepagev2.profilepic.click();

				// Thread.sleep(10000);

				if (Utilities.explicitWaitClickable(driver, settingsPageV2.parentZoneButton, 20)) {
					settingsPageV2.parentZoneButton.click();
					test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
					if (Utilities.explicitWaitVisibleNew(driver, settingsPageV2.parentPinContainer, 20)) {
						Thread.sleep(1000);
						settingsPageV2.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					} else
						BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
				} else
					BasePageV2.reportFail("PARENT ZONE button not found in Switch Profile Screen");
				
				Thread.sleep(15000);
//abcd
				 driver.runAppInBackground(Duration.ofSeconds(3));
				 test.log(LogStatus.INFO, "Put app to background for 3 seconds");
				 driver.currentActivity();

				if (Utilities.explicitWaitClickable(driver, settingsPageV2.settingsIcon, 30)) {
					settingsPageV2.settingsIcon.click();
				} else
					BasePageV2.reportFail("Settings Icon not Found For to navigate to Setttings Page");
				
				try {
					if (Utilities.explicitWaitVisibleNew(driver, homepagev2.logout, 20)) {
						homepagev2.logout.click();

					} else {
						BasePageV2.reportFail("Logout button not displayed");
					}
				} catch (Exception e) {
				}
				try {
					if (Utilities.explicitWaitVisibleNew(driver, homepagev2.confirmLogout, 20)) {
						homepagev2.confirmLogout.click();
					} else {
						BasePageV2.reportFail("Confirm Logout Popup not displayed");
					}
					Thread.sleep(2000);

				} catch (Exception e) {
				}
				try {
					if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.SignUpfromWelcomePage, 20)) {

						test.log(LogStatus.INFO, "Logged out successfully");
					} else {
						BasePageV2.reportFail("Failed to Logout");
					}

				} catch (Exception e) {
				}

			} else if (Utilities.explicitWaitVisibleNew(driver, homepagev2.createPinPage, 10)) {
				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pinContainer, 10)) {
					launchPageV2.pinContainer.clear();
					launchPageV2.pinContainer.sendKeys(pin);
				} else
					BasePageV2.reportFail("Pin Container not displayed");
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.buttonCreatePin, 10))
					launchPageV2.buttonCreatePin.click();
				else
					BasePageV2.reportFail("Create Pin Button not displayed");

				// Confirm pin

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pinContainer, 25)) {
					launchPageV2.pinContainer.clear();
					launchPageV2.pinContainer.sendKeys(pin);
				} else
					BasePageV2.reportFail("Pin Container not displayed");
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.buttonCreatePin, 10))
					launchPageV2.buttonCreatePin.click();
				else
					BasePageV2.reportFail("Create Pin Button not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.kidsName, 25)) {
					launchPageV2.kidsName.clear();
					launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
				} else
					reportFail("Kids name text field not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOB, 15))
					launchPageV2.DOB.click();
				else
					HomePageV2.reportFail("DOB Field not displayed");
				BasePageV2.takeScreenshot();

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOBSelect, 15))
					launchPageV2.DOBSelect.click();
				else {
					HomePageV2.reportFail("Date selector not displayed");
					BasePageV2.takeScreenshot();
				}

				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
					launchPageV2.next.click();
				else
					BasePageV2.reportFail("Next Button not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.createBuddyPage, 10))
					test.log(LogStatus.INFO, "Select Profile image page is displayed");
				else
					BasePageV2.reportFail("Select Profile image page is not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
					launchPageV2.next.click();
				else
					BasePageV2.reportFail("Next Button not displayed");

				for (int i = 1; i <= 5; i++) {
					try {

						WebElement element = driver.findElement(By.xpath(
								"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
										+ i + "]"));
						if (i == 5) {
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipe(driver);
							driver.findElement(By.xpath(
									"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
									.click();
						} else if (Utilities.explicitWaitVisibleNew(driver, element, 10)) {
							element.click();
						} else {
							HomePageV2.reportFail("Kids Characters not displayed");
							BasePageV2.takeScreenshot();
						}

					} catch (Exception e) {
						// test.log(logStatus, details);
					}
				}
				try {
					WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
					if (Utilities.explicitWaitVisibleNew(driver, Skills1, 15))
						Skills1.click();
					else
						test.log(LogStatus.INFO, "Skills not displayed");
				} catch (Exception e) {

				}

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
					launchPageV2.next.click();
				else
					BasePageV2.reportFail("Next Button not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.letsGo, 60))
					launchPageV2.letsGo.click();
				else
					BasePageV2.reportFail("Let's Go Button not displayed");

				for (int i = 1; i <= 5; i++) {
					if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 5))
						homepagev2.freshAppNotificationCancel.click();
					else
						break;
				}

				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 15))
					test.log(LogStatus.INFO, "Home Page is displayed");
				else
					BasePageV2.reportFail("Home page is not displayed");
				logout();
			} else if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.CreateProfile, 15)) {
				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.kidsName, 25)) {
					launchPageV2.kidsName.clear();
					launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
				} else
					reportFail("Kids name text field not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOB, 15))
					launchPageV2.DOB.click();
				else
					HomePageV2.reportFail("DOB Field not displayed");
				BasePageV2.takeScreenshot();

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOBSelect, 15))
					launchPageV2.DOBSelect.click();
				else {
					HomePageV2.reportFail("Date selector not displayed");
					BasePageV2.takeScreenshot();
				}

				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
					launchPageV2.next.click();
				else
					BasePageV2.reportFail("Next Button not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.createBuddyPage, 10))
					test.log(LogStatus.INFO, "Select Profile image page is displayed");
				else
					BasePageV2.reportFail("Select Profile image page is not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
					launchPageV2.next.click();
				else
					BasePageV2.reportFail("Next Button not displayed");

				for (int i = 1; i <= 5; i++) {
					try {

						WebElement element = driver.findElement(By.xpath(
								"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
										+ i + "]"));
						if (i == 5) {
							Utilities.verticalSwipe(driver);
							Utilities.verticalSwipe(driver);
							driver.findElement(By.xpath(
									"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
									.click();
						} else if (Utilities.explicitWaitVisibleNew(driver, element, 10)) {
							element.click();
						} else {
							HomePageV2.reportFail("Kids Characters not displayed");
							BasePageV2.takeScreenshot();
						}

					} catch (Exception e) {
						// test.log(logStatus, details);
					}
				}
				try {
					WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
					if (Utilities.explicitWaitVisibleNew(driver, Skills1, 15))
						Skills1.click();
					else
						test.log(LogStatus.INFO, "Skills not displayed");
				} catch (Exception e) {

				}

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
					launchPageV2.next.click();
				else
					BasePageV2.reportFail("Next Button not displayed");

				if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.letsGo, 60))
					launchPageV2.letsGo.click();
				else
					BasePageV2.reportFail("Let's Go Button not displayed");

				for (int i = 1; i <= 5; i++) {
					if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 15))
						homepagev2.freshAppNotificationCancel.click();
					else
						break;
				}

				if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 25))
					test.log(LogStatus.INFO, "Home Page is displayed");
				else
					BasePageV2.reportFail("Home page is not displayed");
				logout();
			} else if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.SignUpfromWelcomePage, 20)) {
				System.out.println("Already Logged out");
			} else
				BasePageV2.reportFail("Failed to logout");
		} catch (Exception e) {
		}

	}

	// Method created for some specific validation of sign up
	// created by Karthik
	public static void signUpPagefromWelcomeScreen() throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		try {

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.SignUpfromWelcomePage, 25)) {
				test.log(LogStatus.INFO, "Clicking on 'JOIN FREE FOR 30 DAYS' Button");
				BasePageV2.takeScreenshot();
				launchPageV2.SignUpfromWelcomePage.click();
			}
		} catch (Exception e) {
			BasePageV2.takeScreenshot();
		}
	}

	// sign up

	public static void signUp(String email, String mobileNumber, String pwd) throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 15)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Email Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.mobilenumberText, 15)) {
			Thread.sleep(500);
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(mobileNumber);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Mobile Number Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pwdText, 15)) {
			Thread.sleep(500);
			launchPageV2.pwdText.clear();
			launchPageV2.pwdText.sendKeys(pwd);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Password Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

	}

	public static void blankSignup() throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.backButton, 15))
			launchPageV2.backButton.click();
		else
			BasePageV2.takeScreenshot();
		HomePageV2.signUpPagefromWelcomeScreen();

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

	}

	public static void signUpwithonlyEmail(String email) throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 25)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);
			Thread.sleep(500);
		} else
			reportFail("Email Text field not found");

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

	}

	public static void signUpwithoutEmail(String MobileNo, String Pwd) throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.mobilenumberText, 15)) {
			Thread.sleep(500);
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(MobileNo);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Mobile Number Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pwdText, 15)) {
			Thread.sleep(500);
			launchPageV2.pwdText.clear();
			launchPageV2.pwdText.sendKeys(Pwd);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Password Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

	}

	public static void signUpwithoutMobile(String email, String pwd) throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 15)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Email Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pwdText, 15)) {
			Thread.sleep(500);
			launchPageV2.pwdText.clear();
			launchPageV2.pwdText.sendKeys(pwd);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Password Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

	}

	public static void signUpwithoutPwd(String email, String Mobile) throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 15)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Email Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.mobilenumberText, 15)) {
			Thread.sleep(500);
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(Mobile);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Mobile Number Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

	}

	public static void signuptestCaseIncluded() throws Exception {

		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		String testCase = "'Verify Next button functionality by entering both Email and Mobile No'";

		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
		int rowno = xls.getRowCount("Smoke_Results") + 1;
		xls.setCellData("Smoke_Results", "Testcase Name", rowno, testCase);

		String email = "ifocus.automation@gmail.com";
		String pwd = "secret";
		// String pin="1234";
		String mobileNumber = "9988776655";

		for (int i = 0; i < 7; i++) {
			try {
				profilename = resp1.jsonPath().get("profiles[" + i + "].name");
				profilesList.add(profilename);
			} catch (Exception e) {
				break;
			}

		}

		// resp1.jsonPath().getInt("");

		// String email = "ifocus.automation@gmail.com";

		// String pin="1234";
		// String mobileNumber = "9988776655";

		if (profilesList.size() > 3)
			deleteprofiles();
		else
			test.log(LogStatus.INFO, "Number of profiles displayed is less than 3");

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 15)) {
			test.log(LogStatus.INFO, "Logging out from home page");
			HomePageV2.logout();
		} else
			test.log(LogStatus.INFO, "Signing up with Fresh credentials");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.SignUpfromWelcomePage, 15))
			launchPageV2.SignUpfromWelcomePage.click();
		else
			BasePageV2.reportFail("Signup Button not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 15)) {
			Thread.sleep(500);
			launchPageV2.emailText.clear();
			launchPageV2.emailText.sendKeys(email);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Email Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.mobilenumberText, 15)) {
			Thread.sleep(500);
			launchPageV2.mobilenumberText.clear();
			launchPageV2.mobilenumberText.sendKeys(mobileNumber);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Mobile Number Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pwdText, 15)) {
			Thread.sleep(500);
			launchPageV2.pwdText.clear();
			launchPageV2.pwdText.sendKeys(pwd);
			Thread.sleep(500);
			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
		} else
			BasePageV2.reportFail("Password Text Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
			launchPageV2.nextButton.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.commonErrors, 10)) {

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.emailText, 15)) {
				Thread.sleep(500);
				launchPageV2.emailText.clear();
				launchPageV2.emailText.sendKeys(Utilities.generateEmailid());
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Email Text Field not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.mobilenumberText, 15)) {
				Thread.sleep(500);
				launchPageV2.mobilenumberText.clear();
				launchPageV2.mobilenumberText.sendKeys(Utilities.generateMobileNumber());
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Mobile Number Text Field not displayed");

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pwdText, 15)) {
				Thread.sleep(500);
				launchPageV2.pwdText.clear();
				launchPageV2.pwdText.sendKeys(pwd);
				Thread.sleep(500);
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
				}
			} else
				BasePageV2.reportFail("Password text Field not displayed");

			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}

			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.nextButton, 10))
				launchPageV2.nextButton.click();
			else
				BasePageV2.reportFail("Next Button not displayed");
		} else
			test.log(LogStatus.INFO, "Signing up with Fresh credentials");

		// Create pin

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pinContainer, 25)) {
			homepagev2.smokeresults(testCase, rowno, "Pass");
			test.log(LogStatus.PASS, "Test Case: " + testCase + " is passed");
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.buttonCreatePin, 10))
			launchPageV2.buttonCreatePin.click();
		else
			BasePageV2.reportFail("Create Pin Button not displayed");

		// Confirm pin

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.pinContainer, 25)) {
			launchPageV2.pinContainer.clear();
			launchPageV2.pinContainer.sendKeys(pin);
		} else
			BasePageV2.reportFail("Pin Container not displayed");
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 10))
			launchPageV2.buttonCreatePin.click();
		else
			BasePageV2.reportFail("Create Pin Button not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.kidsName, 25)) {
			launchPageV2.kidsName.clear();
			launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
		} else
			reportFail("Kids name text field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOB, 15))
			launchPageV2.DOB.click();
		else
			HomePageV2.reportFail("DOB Field not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.DOBSelect, 15))
			launchPageV2.DOBSelect.click();
		else {
			HomePageV2.reportFail("Date selector not displayed");
			BasePageV2.takeScreenshot();
		}

		try {
			driver.hideKeyboard();
		} catch (Exception e) {
		}
		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.createBuddyPage, 10))
			test.log(LogStatus.INFO, "Select Profile image page is displayed");
		else
			BasePageV2.reportFail("Select Profile image page is not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		for (int i = 1; i <= 5; i++) {
			try {

				WebElement element = driver.findElement(
						By.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
								+ i + "]"));
				if (i == 5) {
					Utilities.verticalSwipe(driver);
					Utilities.verticalSwipe(driver);
					driver.findElement(By
							.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
							.click();
				} else if (Utilities.explicitWaitVisibleNew(driver, element, 10)) {
					element.click();
				} else {
					HomePageV2.reportFail("Kids Characters not displayed");
					BasePageV2.takeScreenshot();
				}

			} catch (Exception e) {
				// test.log(logStatus, details);
			}
		}
		try {
			WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
			if (Utilities.explicitWaitVisibleNew(driver, Skills1, 15))
				Skills1.click();
			else
				test.log(LogStatus.INFO, "Skills not displayed");
		} catch (Exception e) {

		}

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.next, 10))
			launchPageV2.next.click();
		else
			BasePageV2.reportFail("Next Button not displayed");

		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.letsGo, 25))
			launchPageV2.letsGo.click();
		else
			BasePageV2.reportFail("Let's Go Button not displayed");

		for (int i = 1; i <= 5; i++) {
			if (Utilities.explicitWaitVisibleNew(driver, homepagev2.freshAppNotificationCancel, 15))
				homepagev2.freshAppNotificationCancel.click();
			else
				break;
		}

		if (Utilities.explicitWaitVisibleNew(driver, homepagev2.profilepic, 25))
			test.log(LogStatus.INFO, "Sign up Successful");
		else
			BasePageV2.reportFail("Unable to Signup");
	}

	public static Response requestresp() throws Exception {
		String url = "https://asia-northeast1-vootkids-216805.cloudfunctions.net/users/v1/profiles.json";
		RestAssured.config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		JSONObject requestParam = new JSONObject();

		requestParam.put("uId", uid);

		System.out.println(requestParam.toString());

		BasicConfigurator.configure();
		resp1 = RestAssured.given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body(requestParam.toString()).contentType(ContentType.JSON).accept(ContentType.JSON).when().post(url);
		return resp1;

	}

	public static void deleteprofiles() throws Exception {

		// boolean flag = false;

		logout();
		login("9988776655", "secret");

		HomePageV2 homepage = new HomePageV2(driver, test);
		if (Utilities.explicitWaitVisibleNew(driver, homepage.profilepic, 20)) {
			// Clicking on profile pic
			homepage.profilepic.click();

			// Clicking on settings
			if (Utilities.explicitWaitVisibleNew(driver, homepage.settings, 15)) {
				homepage.settings.click();

				// parental pin
				if (Utilities.explicitWaitVisibleNew(driver, homepage.parentalPin, 15)) {
					homepage.parentalPin.clear();
					homepage.parentalPin.sendKeys(pin);
					try {
						driver.hideKeyboard();
					} catch (Exception e) {

					}

					// clicking on profile tab
					if (Utilities.explicitWaitVisibleNew(driver, homepage.profilestab, 15)) {
						homepage.profilestab.click();

						// taking list of profiles
						// mnop

						int numberofProfiles = profilesList.size() - 1;
						System.out.println("Number of profiles displayed is: " + numberofProfiles);

						next: for (int i = 0; i < numberofProfiles; i++) {

							String profiledisplayed = profilesList.get(i).toString();

							try {
								Utilities.verticalSwipe(driver, profiledisplayed);
								// abcdefghi
								WebElement profile = driver.findElement(
										By.xpath("//android.widget.TextView[@text='" + profiledisplayed + "']"));
								if (i >= (numberofProfiles - 1))
									break next;
								else {

									if (Utilities.explicitWaitVisibleNew(driver, profile, 10)) {

										profile.click();
										Thread.sleep(60000);
										if (Utilities.explicitWaitVisibleNew(driver, homepage.deleteProfilebutton, 20)) {

											homepage.deleteProfilebutton.click();

											Thread.sleep(5000);

											if (Utilities.explicitWaitVisibleNew(driver, homepage.confirmLogout, 20))
												homepage.confirmLogout.click();
											else if (Utilities.explicitWaitVisibleNew(driver, homepage.deleteProfilebutton,20)) {
												driver.navigate().back();
												break next;

											} else
												reportFail("Confirmation popup is not displayed to delete the profile");

											// driver.navigate().back();

										} else
											reportFail("Delete profile button is not displayed");
									}

									else
										reportFail("Profile not displayed");

								}
							} catch (Exception e) {

							}

						}

						try {
							driver.navigate().back();
							Thread.sleep(2000);
							driver.navigate().back();

							// abcdefg

							if (Utilities.explicitWaitVisibleNew(driver, homepage.btn_cancel_fromprofile, 20))
								homepage.btn_cancel_fromprofile.click();
							else {
								driver.navigate().back();
								if (Utilities.explicitWaitVisibleNew(driver, homepage.btn_cancel_fromprofile, 20))
									homepage.btn_cancel_fromprofile.click();
								else
									reportFail("Close button is not displayed in settings page");
							}

							logout();
						} catch (Exception e) {
							// TODO: handle exception
						}

					} else
						reportFail("Profiles tab not displayed in settings page");
				} else
					reportFail("Parental pin text field not displayed");

			} else
				reportFail("Setting button not displayed");

		} else

			reportFail("Profile pic is not displayed in 'My Stuff' page");

	}

	public static void signup() throws Exception {
//
//		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
//		HomePageV2 homepagev2 = new HomePageV2(driver, test);
//
//		requestresp();
//
//		for (int i = 0; i < 7; i++) {
//			try {
//				profilename = resp1.jsonPath().get("profiles[" + i + "].name");
//				profilesList.add(profilename);
//			} catch (Exception e) {
//				break;
//			}
//
//		}
//
//		// resp1.jsonPath().getInt("");
//
//		String email = "ifocus.automation@gmail.com";
//		String pwd = "secret";
//		// String pin="1234";
//		String mobileNumber = "9988776655";
//
//		if (profilesList.size() > 3)
//			deleteprofiles();
//		else
//			test.log(LogStatus.INFO, "Number of profiles displayed is less than 3");
//
//		logout();
//		// if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5))
//		// {
//		// //
//		// test.log(LogStatus.INFO, "Logging out from home page");
//		// logout();
//		// } else
//		// test.log(LogStatus.INFO, "Signing up with Fresh credentials");
//
//		if (Utilities.explicitWaitVisible(driver, launchPageV2.SignUpfromWelcomePage, 5)) {
//			launchPageV2.SignUpfromWelcomePage.click();
//
//			if (Utilities.explicitWaitVisible(driver, homepagev2.allowpermissionbtn, 5))
//				homepagev2.allowpermissionbtn.click();
//			else
//				System.out.println("Device permission allow popup is not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 5)) {
//				Thread.sleep(500);
//				launchPageV2.emailText.clear();
//				launchPageV2.emailText.sendKeys(email);
//				Thread.sleep(500);
//				try {
//					driver.hideKeyboard();
//				} catch (Exception e) {
//				}
//			} else
//				BasePageV2.reportFail("Email Text Field not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.mobilenumberText, 5)) {
//				Thread.sleep(500);
//				launchPageV2.mobilenumberText.clear();
//				launchPageV2.mobilenumberText.sendKeys(mobileNumber);
//				Thread.sleep(500);
//				try {
//					driver.hideKeyboard();
//				} catch (Exception e) {
//				}
//			} else
//				BasePageV2.reportFail("Mobile Number Text Field not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
//				Thread.sleep(500);
//				launchPageV2.pwdText.clear();
//				launchPageV2.pwdText.sendKeys(pwd);
//				Thread.sleep(500);
//				try {
//					driver.hideKeyboard();
//				} catch (Exception e) {
//				}
//			} else
//				BasePageV2.reportFail("Password Text Field not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.nextButton, 1))
//				launchPageV2.nextButton.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			// if (Utilities.explicitWaitVisible(driver,
//			// launchPageV2.commonErrors, 2)) {
//			//
//			// if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText,
//			// 5)) {
//			// Thread.sleep(500);
//			// launchPageV2.emailText.clear();
//			// launchPageV2.emailText.sendKeys("ifocus.automation@gmail.com");
//			// Thread.sleep(500);
//			// try {
//			// driver.hideKeyboard();
//			// } catch (Exception e) {
//			// }
//			// } else
//			// BasePageV2.reportFail("Email Text Field not displayed");
//			//
//			// if (Utilities.explicitWaitVisible(driver,
//			// launchPageV2.mobilenumberText, 5)) {
//			// Thread.sleep(500);
//			// launchPageV2.mobilenumberText.clear();
//			// launchPageV2.mobilenumberText.sendKeys(Utilities.generateMobileNumber());
//			// Thread.sleep(500);
//			// try {
//			// driver.hideKeyboard();
//			// } catch (Exception e) {
//			// }
//			// } else
//			// BasePageV2.reportFail("Mobile Number Text Field not displayed");
//			//
//			// if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText,
//			// 5)) {
//			// Thread.sleep(500);
//			// launchPageV2.pwdText.clear();
//			// launchPageV2.pwdText.sendKeys(pwd);
//			// Thread.sleep(500);
//			// try {
//			// driver.hideKeyboard();
//			// } catch (Exception e) {
//			// }
//			// } else
//			// BasePageV2.reportFail("Password text Field not displayed");
//			//
//			// try {
//			// driver.hideKeyboard();
//			// } catch (Exception e) {
//			// }
//			//
//			// if (Utilities.explicitWaitVisible(driver,
//			// launchPageV2.nextButton, 1))
//			// launchPageV2.nextButton.click();
//			// else
//			// BasePageV2.reportFail("Next Button not displayed");
//			// } else
//			// test.log(LogStatus.INFO, "Signing up with Fresh credentials");
//
//			// Create pin
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.entercodeManuallyLink, 20)) {
//				test.log(LogStatus.INFO, "Entering OTP: 1234");
//				launchPageV2.entercodeManuallyLink.click();
//
//			} else
//				System.out.println("Manually link not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.resendOTP, 20)) {
//				if (Utilities.explicitWaitVisible(driver, launchPageV2.otppininner, 5)) {
//					launchPageV2.otppininner.clear();
//					launchPageV2.otppininner.sendKeys("1234");
//					try {
//						driver.hideKeyboard();
//					} catch (Exception e) {
//					}
//
//				} else
//					BasePageV2.reportFail("Pin Container not displayed");
//			} else
//				reportFail("OTP page is not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 25))
//				test.log(LogStatus.INFO, "Crreate pin page is displayed");
//			else
//				reportFail("Create Pin Page is not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 5)) {
//				launchPageV2.pinContainer.clear();
//				launchPageV2.pinContainer.sendKeys(pin);
//			} else
//				BasePageV2.reportFail("Pin Container not displayed");
//			try {
//				driver.hideKeyboard();
//			} catch (Exception e) {
//			}
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
//				launchPageV2.buttonCreatePin.click();
//			else
//				BasePageV2.reportFail("Create Pin Button not displayed");
//
//			// Confirm pin
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
//				launchPageV2.pinContainer.clear();
//				launchPageV2.pinContainer.sendKeys(pin);
//			} else
//				BasePageV2.reportFail("Pin Container not displayed");
//			try {
//				driver.hideKeyboard();
//			} catch (Exception e) {
//			}
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
//				launchPageV2.buttonCreatePin.click();
//			else
//				BasePageV2.reportFail("Create Pin Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
//				launchPageV2.kidsName.clear();
//				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
//				try {
//					driver.hideKeyboard();
//				} catch (Exception e) {
//				}
//			} else
//				reportFail("Kids name text field not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
//				launchPageV2.DOB.click();
//			else
//				HomePageV2.reportFail("DOB Field not displayed");
//			BasePageV2.takeScreenshot();
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
//				launchPageV2.DOBSelect.click();
//			else {
//				HomePageV2.reportFail("Date selector not displayed");
//				BasePageV2.takeScreenshot();
//			}
//
//			try {
//				driver.hideKeyboard();
//			} catch (Exception e) {
//			}
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 20))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 20))
//				test.log(LogStatus.INFO, "Select Profile image page is displayed");
//			else
//				BasePageV2.reportFail("Select Profile image page is not displayed");
//
//			if (Utilities.explicitWaitClickable(driver, launchPageV2.next, 20))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			for (int i = 1; i <= 5; i++) {
//				try {
//
//					WebElement element = driver.findElement(By
//							.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
//									+ i + "]"));
//					if (i == 5) {
//						Utilities.verticalSwipe(driver);
//						Utilities.verticalSwipe(driver);
//						driver.findElement(By
//								.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
//								.click();
//					} else if (Utilities.explicitWaitVisible(driver, element, 10)) {
//						element.click();
//					} else {
//						HomePageV2.reportFail("Kids Characters not displayed");
//						BasePageV2.takeScreenshot();
//					}
//
//				} catch (Exception e) {
//					// test.log(logStatus, details);
//				}
//			}
//			try {
//				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
//				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
//					Skills1.click();
//				else
//					test.log(LogStatus.INFO, "Skills not displayed");
//			} catch (Exception e) {
//
//			}
//
//			if (Utilities.explicitWaitClickable(driver, launchPageV2.next, 20))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 60))
//				launchPageV2.letsGo.click();
//			else
//				BasePageV2.reportFail("Let's Go Button not displayed");
//
//			for (int i = 1; i <= 5; i++) {
//				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
//					homepagev2.freshAppNotificationCancel.click();
//				else
//					break;
//			}
//
//			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
//				test.log(LogStatus.INFO, "Sign up Successful");
//			else
//				BasePageV2.reportFail("Unable to Signup");
//		}
//
//		else if (Utilities.explicitWaitVisible(driver, homepagev2.createPinPage, 5)) {
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 5)) {
//				launchPageV2.pinContainer.clear();
//				launchPageV2.pinContainer.sendKeys(pin);
//			} else
//				BasePageV2.reportFail("Pin Container not displayed");
//			try {
//				driver.hideKeyboard();
//			} catch (Exception e) {
//			}
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
//				launchPageV2.buttonCreatePin.click();
//			else
//				BasePageV2.reportFail("Create Pin Button not displayed");
//
//			// Confirm pin
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.pinContainer, 25)) {
//				launchPageV2.pinContainer.clear();
//				launchPageV2.pinContainer.sendKeys(pin);
//			} else
//				BasePageV2.reportFail("Pin Container not displayed");
//			try {
//				driver.hideKeyboard();
//			} catch (Exception e) {
//			}
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.buttonCreatePin, 1))
//				launchPageV2.buttonCreatePin.click();
//			else
//				BasePageV2.reportFail("Create Pin Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
//				launchPageV2.kidsName.clear();
//				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
//			} else
//				reportFail("Kids name text field not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
//				launchPageV2.DOB.click();
//			else
//				HomePageV2.reportFail("DOB Field not displayed");
//			BasePageV2.takeScreenshot();
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
//				launchPageV2.DOBSelect.click();
//			else {
//				HomePageV2.reportFail("Date selector not displayed");
//				BasePageV2.takeScreenshot();
//			}
//
//			try {
//				driver.hideKeyboard();
//			} catch (Exception e) {
//			}
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
//				test.log(LogStatus.INFO, "Select Profile image page is displayed");
//			else
//				BasePageV2.reportFail("Select Profile image page is not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			for (int i = 1; i <= 5; i++) {
//				try {
//
//					WebElement element = driver.findElement(By
//							.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
//									+ i + "]"));
//					if (i == 5) {
//						Utilities.verticalSwipe(driver);
//						Utilities.verticalSwipe(driver);
//						driver.findElement(By
//								.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
//								.click();
//					} else if (Utilities.explicitWaitVisible(driver, element, 10)) {
//						element.click();
//					} else {
//						HomePageV2.reportFail("Kids Characters not displayed");
//						BasePageV2.takeScreenshot();
//					}
//
//				} catch (Exception e) {
//					// test.log(logStatus, details);
//				}
//
//			}
//			try {
//				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
//				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
//					Skills1.click();
//				else
//					test.log(LogStatus.INFO, "Skills not displayed");
//			} catch (Exception e) {
//
//			}
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 60))
//				launchPageV2.letsGo.click();
//			else
//				BasePageV2.reportFail("Let's Go Button not displayed");
//
//			for (int i = 1; i <= 5; i++) {
//				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
//					homepagev2.freshAppNotificationCancel.click();
//				else
//					break;
//			}
//
//			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 5))
//				test.log(LogStatus.INFO, "Home Page is displayed");
//			else
//				BasePageV2.reportFail("Home page is not displayed");
//			// signup();
//		} else if (Utilities.explicitWaitVisible(driver, launchPageV2.CreateProfile, 5)) {
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
//				launchPageV2.kidsName.clear();
//				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
//			} else
//				reportFail("Kids name text field not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
//				launchPageV2.DOB.click();
//			else
//				HomePageV2.reportFail("DOB Field not displayed");
//			BasePageV2.takeScreenshot();
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
//				launchPageV2.DOBSelect.click();
//			else {
//				HomePageV2.reportFail("Date selector not displayed");
//				BasePageV2.takeScreenshot();
//			}
//
//			try {
//				driver.hideKeyboard();
//			} catch (Exception e) {
//			}
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
//				test.log(LogStatus.INFO, "Select Profile image page is displayed");
//			else
//				BasePageV2.reportFail("Select Profile image page is not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			for (int i = 1; i <= 5; i++) {
//				try {
//
//					WebElement element = driver.findElement(By
//							.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])["
//									+ i + "]"));
//					if (i == 5) {
//						Utilities.verticalSwipe(driver);
//						Utilities.verticalSwipe(driver);
//						driver.findElement(By
//								.xpath("(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
//								.click();
//					} else if (Utilities.explicitWaitVisible(driver, element, 10)) {
//						element.click();
//					} else {
//						HomePageV2.reportFail("Kids Characters not displayed");
//						BasePageV2.takeScreenshot();
//					}
//
//				} catch (Exception e) {
//					// test.log(logStatus, details);
//				}
//
//			}
//			try {
//				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
//				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
//					Skills1.click();
//				else
//					test.log(LogStatus.INFO, "Skills not displayed");
//			} catch (Exception e) {
//
//			}
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
//				launchPageV2.next.click();
//			else
//				BasePageV2.reportFail("Next Button not displayed");
//
//			if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 60))
//				launchPageV2.letsGo.click();
//			else
//				BasePageV2.reportFail("Let's Go Button not displayed");
//
//			for (int i = 1; i <= 5; i++) {
//				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
//					homepagev2.freshAppNotificationCancel.click();
//				else
//					break;
//			}
//
//			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
//				test.log(LogStatus.INFO, "Home Page is displayed");
//			else
//				BasePageV2.reportFail("Home page is not displayed");
//			// signup();
//		} else
//			reportFail("Unable to signup");
//
//	
	}

	public String convertCamelCase(String toConvert) throws Exception {
		if (toConvert == "") {
			return "";
		} else {
			String finalString = "";
			String[] array = toConvert.toLowerCase().split(" ");
			for (int i = 0; i < array.length; i++) {
				char firstCharBefore = array[i].charAt(0);
				char firstCharAfter = Character.toUpperCase(firstCharBefore);
				String newWord = array[i].substring(1);
				newWord = firstCharAfter + newWord;
				System.out.println("////////////////////////" + newWord);
				array[i] = newWord;
				if (i == array.length - 1)
					finalString = finalString.concat(array[i]);
				else
					finalString = finalString.concat(array[i] + " ");
			}
			return finalString;
		}
	}

	public void verifyAndProgressBar() throws Exception {
		for (int k = 0; k < 25; k++) {
			if (!Utilities.explicitWaitVisible(driver, progressBarLoader, 3))
				break;
			Thread.sleep(3000);
		}
		if (Utilities.explicitWaitVisible(driver, progressBarLoader, 3)) {
			for (int k = 0; k < 10; k++) {
				if (progressBar.size() == 0)
					break;
				else
					Thread.sleep(5000);
			}
			if (progressBar.size() > 0)
				reportFail("Video not loaded within 45 seconds");
		}
	}// End of progress bar
	
	// Login after Reset
	public static void loginAfterReset(String UN, String Pwd) throws Exception {
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		test.log(LogStatus.INFO, "Logging In with valid credentials");
		try {
			if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 2)) {
				if (Utilities.explicitWaitVisible(driver, launchPageV2.loginGateway, 25))
					launchPageV2.loginGateway.click();
				else
					BasePageV2.reportFail("Login Page Gateway button not displayed");
				if (Utilities.explicitWaitVisible(driver, launchPageV2.emailText, 25)) {
					launchPageV2.emailText.clear();
					launchPageV2.emailText.sendKeys(UN);
					try {
						driver.hideKeyboard();
					} catch (Exception e) {}
				} 
				else
					BasePageV2.reportFail("Email text field not displayed");
				if (Utilities.explicitWaitVisible(driver, launchPageV2.pwdText, 5)) {
					launchPageV2.pwdText.clear();
					launchPageV2.pwdText.sendKeys(Pwd);
					try {
						driver.hideKeyboard();
					} catch (Exception e) {}
				} 
				else
					BasePageV2.reportFail("Password text field not displayed");

				if (Utilities.explicitWaitVisible(driver, launchPageV2.loginButton, 5))
					launchPageV2.loginButton.click();
				else
					BasePageV2.reportFail("Login Button not found");

				if (Utilities.explicitWaitVisible(driver, launchPageV2.selectProfilePage, 5))
					test.log(LogStatus.INFO, "Select Profile Page displayed");
				else
					test.log(LogStatus.INFO, "Select Profile Page not displayed");

				if (Utilities.explicitWaitVisible(driver, launchPageV2.selectProfileIcon, 5)) {
					test.log(LogStatus.INFO, "Multi user Profile, selecting a profile to login");
					launchPageV2.selectProfileIcon.click();
				}
				else
					test.log(LogStatus.INFO, "Single User Profile");
			}
		}
		catch(Exception e) {
			BasePageV2.reportFail("Login Failed");
		}
	}
	
	//Select Profile
	public void selectProfile(String profileName) {
		SettingsPageV2 settingspagev2 = new SettingsPageV2(driver, test);
		if(Utilities.explicitWaitClickable(driver, profileBtn, 30)) {
			try {
				profileBtn.click();
				test.log(LogStatus.INFO, "Clicked on Profile icon");
				try {
					driver.findElement(By.xpath("//android.widget.TextView[@text=\""+profileName+"\"]//parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView")).click();
					test.log(LogStatus.INFO, "Switched to profile "+profileName);
					Thread.sleep(3000);
					if(Utilities.explicitWaitClickable(driver, settingspagev2.switchProfileCancel, 60)) {
						try {
							settingspagev2.switchProfileCancel.click();
						}
						catch(Exception e2) {
							test.log(LogStatus.FAIL, "Failed to to close switch profile screen");
						}
					}
				}
				catch(Exception e1) {
					test.log(LogStatus.FAIL, "Failed to switch to profile "+profileName);
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to clicked on Profile icon "+profileName);
			}
		}
	}
	
	public void clearCoachCards() throws Exception {
		if(Utilities.explicitWaitClickable(driver, homeAllowButton, 10)) {
			try {
				homeAllowButton.click();
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on Allow button after login");
			}
		}
		else {
			test.log(LogStatus.INFO, "Allow button is not clickable");
		}
		for(int coachCardCount=1;coachCardCount<=4;coachCardCount++) {
			/*if(Utilities.explicitWaitClickable(driver, coachCardCancel, 10)) {
				try {
					coachCardCancel.click();
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to click on Cancel button of the coach card");
				}
			}
			else {
				test.log(LogStatus.INFO, "Cancel button of the coach card is not clickable");
			}*/
			Utilities.tap(driver);
			Thread.sleep(1000);
		}
	}
	
	public void clearLastViewed() throws Exception {
		if(Utilities.explicitWaitClickable(driver, recentViewedClear, 10)) {
			try {
				recentViewedClear.click();
				try {
					recentViewedClearYes.click();
					test.log(LogStatus.INFO, "Clearing last viewed tray");
				}
				catch(Exception e) {
					test.log(LogStatus.INFO, "Failed to Clear");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.INFO, "Failed to click on Clear");
			}
		}
		else {
			test.log(LogStatus.INFO, "Clear link is not clickables");
		}
	}
	public static void createProfile(String profName) throws Exception
	{
		LaunchPageV2 launchPageV2 = new LaunchPageV2(driver, test);
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		if (Utilities.explicitWaitVisible(driver, launchPageV2.CreateProfile, 5)) {
			  if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25)) {
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(profName);
			} else
				reportFail("Kids name text field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
				launchPageV2.DOB.click();
			else
				HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
				launchPageV2.DOBSelect.click();
			else {
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try {
				driver.hideKeyboard();
			} catch (Exception e) {
			}
			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 30))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.chooseBuddyPage, 30))
				test.log(LogStatus.INFO, "Select Profile image page is displayed");
			else
				BasePageV2.reportFail("Select Profile image page is not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 30))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			for (int i = 1; i <= 5; i++) {
				try {

					WebElement element = driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[" + i
									+ "]"));
				
				/*	if (i == 5) {
						Utilities.verticalSwipe(driver);
						Utilities.verticalSwipe(driver);
						driver.findElement(By.xpath(
								"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[3]"))
								.click();
					} else*/
					if (Utilities.explicitWaitClickableNew(driver, element, 5)) {
						try{
							element.click();
						}
						catch(Exception e){
							Utilities.verticalSwipe(driver);
							try{
								element.click();
							}
							catch(Exception ex){}
						}
					} else {
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}

				} catch (Exception e) {
					// test.log(logStatus, details);
				}

			}
			try {
				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
					Skills1.click();
				else
					test.log(LogStatus.INFO, "Skills not displayed");
			} catch (Exception e) {

			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else
				BasePageV2.reportFail("Next Button not displayed");

			
			if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.letsGo, 20))
			 {
				    if(GlobalVariables.profileCount>5)
				    BasePageV2.reportFail("User are allowed to create more");
				    launchPageV2.letsGo.click();
			 }
			else if(GlobalVariables.profileCount>=5)
			{
				driver.pressKeyCode(AndroidKeyCode.BACK);
				driver.pressKeyCode(AndroidKeyCode.BACK);
				driver.pressKeyCode(AndroidKeyCode.BACK);
				driver.pressKeyCode(AndroidKeyCode.BACK);
				
				test.log(LogStatus.INFO, "User are not allowed to create more than 4 profile");
				test.log(LogStatus.PASS, "Testcase : 'Verify the number of profiles associated with each account:' is Passed");
				driver.pressKeyCode(AndroidKeyCode.BACK);
			}
			else
				{
				 BasePageV2.reportFail("Let's Go Button not displayed / Not able to create profile ");
				driver.pressKeyCode(AndroidKeyCode.BACK);
				driver.pressKeyCode(AndroidKeyCode.BACK);
				driver.pressKeyCode(AndroidKeyCode.BACK);
				driver.pressKeyCode(AndroidKeyCode.BACK);
				 driver.pressKeyCode(AndroidKeyCode.BACK);
				}

			for (int i = 1; i <= 5; i++) {
				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else
					break;
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
				test.log(LogStatus.INFO, "Home Page is displayed");
			else
				BasePageV2.reportFail("Home page is not displayed");
			
		}
	}
	
	
	public void createProfileIfOnlyOnePresentAndSelectFirstProfile() {
		int profileCount=0;
		if(Utilities.explicitWaitClickableNew(driver, profileBtn, 5)) {
			try {
				profileBtn.click();
				try {
					profileCount=profileListNames.size();
					if(profileCount>2) {
						test.log(LogStatus.INFO, "User has more than 1 profile");
						for(int i=0;i<(profileCount-1);i++) {
							test.log(LogStatus.INFO, "Profile name: "+profileListNames.get(i).getAttribute("text"));
						}
						try {
							profileListImages.get(0).click();
							test.log(LogStatus.INFO, "Tapped on first profile");
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to tap first profile");
						}
						Thread.sleep(5000);
						driver.navigate().back();
					}
					else {
						test.log(LogStatus.INFO, "User has only one profile");
						//Create profile
						String newName=createProfileName();
						try {
							profileListImages.get(profileCount-1).click();
							test.log(LogStatus.INFO, "Tapped on Create New button");
							if(Utilities.explicitWaitClickableNew(driver, newProfileNameEditBox, 10)) {
								newProfileNameEditBox.sendKeys(newName);
								if(Utilities.explicitWaitClickableNew(driver, newProfileDateBox, 10)) {
									try {
										newProfileDateBox.click();
										Thread.sleep(1000);
										if(Utilities.explicitWaitClickableNew(driver, calendarOK, 10)) {
											try {
												calendarOK.click();
												if(Utilities.explicitWaitClickableNew(driver, nextButton, 10)) {
													try {
														nextButton.click();
														if(Utilities.explicitWaitClickableNew(driver, nextButton, 10)) {
															try {
																nextButton.click();
																try {
																	for(int i=0;i<5;i++) {
																		favoriteImages.get(i).click();
																	}
																	if(Utilities.explicitWaitClickableNew(driver, nextButton, 10)) {
																		try {
																			nextButton.click();
																			if(Utilities.explicitWaitClickableNew(driver, letsGoButton, 10)) {
																				letsGoButton.click();
																				test.log(LogStatus.INFO, "Created new profile: "+newName);
																			}
																		}
																		catch(Exception e) {
																			test.log(LogStatus.FAIL, "Failed to tap Lets Go button, profile creation failed");
																		}
																	}
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Failed to select favorites");
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Failed to tap Next button");
															}
														}
													}
													catch(Exception e) {
														test.log(LogStatus.FAIL, "Failed to tap Next button");
													}
												}
											}	
											catch(Exception e) {
												test.log(LogStatus.FAIL, "Failed to enter date");
											}
										}				
									}
									catch(Exception e) {
										test.log(LogStatus.FAIL, "Failed to enter date");
									}
								}
							}
							else {
								test.log(LogStatus.FAIL, "Failed to enter name");
							}
						}
						catch(Exception e) {
							test.log(LogStatus.FAIL, "Failed to tap on Create New button");
						}
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to get number of profiles");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to tap on Profile button");
			}
		}
	}
	
	public void selectSecondProfile() {
		String secondPofile="";
		if(Utilities.explicitWaitClickableNew(driver, profileBtn, 5)) {
			try {
				profileBtn.click();
				test.log(LogStatus.INFO, "Tapped on Profile button");
				if(Utilities.explicitWaitClickableNew(driver, profileListImages.get(1), 10)) {
					try {
						profileListImages.get(1).click();
						test.log(LogStatus.INFO, "Tapped on second profile: "+profileListNames.get(1).getAttribute("text"));
						Thread.sleep(2000);
						driver.navigate().back();
					}
					catch(Exception e) {}
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate Second Profile");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to tap on Profile button");
			}
		}
	}
	
	
	public void selectFirstProfile() {
		String firstPofile="";
		if(Utilities.explicitWaitClickableNew(driver, profileBtn, 5)) {
			try {
				profileBtn.click();
				test.log(LogStatus.INFO, "Tapped on Profile button");
				if(Utilities.explicitWaitClickableNew(driver, profileListImages.get(0), 10)) {
					try {
						profileListImages.get(0).click();
						test.log(LogStatus.INFO, "Tapped on first profile: "+profileListNames.get(0).getAttribute("text"));
						Thread.sleep(2000);
						driver.navigate().back();
					}
					catch(Exception e) {}
				}
				else {
					test.log(LogStatus.FAIL, "Failed to locate First Profile");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to tap on Profile button");
			}
		}
	}
	
	public String createProfileName() {
		String alphabetString = "abcdefghijklmnopqrstuvxyz";
		StringBuilder profileName = new StringBuilder(5); 
		for (int i = 0; i < 5; i++) {
			int index = (int)(alphabetString.length()*Math.random()); 
			profileName.append(alphabetString.charAt(index)); 
		} 
		return profileName.toString(); 	
	}
	
	public void createProfileAndSelectTheProfile() {
		int profileCount=0;
		if(Utilities.explicitWaitClickableNew(driver, profileBtn, 5)) {
			try {
				profileBtn.click();
				try {
					profileCount=profileListNames.size();
					if(profileCount==4) {//one count for create new
						test.log(LogStatus.INFO, "User already has 3 profiles, hence need to delete and create a profile");
						//delete the profile
						if(Utilities.explicitWaitClickableNew(driver, btn_parentzone, 20)) {
							try {
								btn_parentzone.click();
								if (Utilities.explicitWaitVisibleNew(driver, pinContainer, 20)) {
									pinContainer.clear();
									pinContainer.sendKeys("1111");
									if(Utilities.explicitWaitClickableNew(driver, settings, 50)) {
										try {
											driver.runAppInBackground(Duration.ofSeconds(3));
											driver.currentActivity();
											settings.click();
											if(Utilities.explicitWaitClickableNew(driver, profilesOptionInSettingsPage, 10)) {
												try {
													profilesOptionInSettingsPage.click();
													if(Utilities.explicitWaitClickableNew(driver, selectedProfileTick, 10)) {
														try{
															selectedProfileTick.click();
															if(Utilities.explicitWaitClickableNew(driver, deleteProfile, 10)) {
																try {
																	deleteProfile.click();
																	if(Utilities.explicitWaitClickableNew(driver, deleteProfileYes, 10)) {
																		try {
																			deleteProfileYes.click();
																			test.log(LogStatus.INFO, "Deleted Profile");
																			for(int i=0;i<5;i++) {
																				driver.navigate().back();
																				Thread.sleep(1000);
																			}
																		}
																		catch(Exception e) {
																			test.log(LogStatus.FAIL, "Failed to click on Yes in Delete confirmation dialog");
																		}
																	}
																	else {
																		test.log(LogStatus.FAIL, "Yes in Delete confirmation dialog is not clickable");
																	}
																}
																catch(Exception e) {
																	test.log(LogStatus.FAIL, "Failed to delete profile");
																}
															}
															else {
																test.log(LogStatus.FAIL, "Delete profile link is not clickable");
															}
														}
														catch(Exception e) {
															test.log(LogStatus.INFO, "Failed to click on Selected Profile");
														}
													}
													else {
														test.log(LogStatus.FAIL, "Selected Profile is not clickable");
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Failed to click on Profiles option in Settings");
												}
											}
										}
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to click on Settings");
										}
									}
									else {
										test.log(LogStatus.FAIL, "Settings button is not clickable");
									}
								} 
								else
									test.log(LogStatus.FAIL, "Failed to enter PIN");
							}
							catch(Exception e) {
								test.log(LogStatus.FAIL, "Failed to click Parent Zone button");
							}
						}
						else {
							test.log(LogStatus.INFO, "User has more than 1 profile");
						}
					}
					//Create profile
					String newName=createProfileName();
					try {
						int createNewCount=profileListImages.size()-1;
						profileListImages.get(createNewCount).click();
						test.log(LogStatus.INFO, "Tapped on Create New button");
						if(Utilities.explicitWaitClickableNew(driver, newProfileNameEditBox, 10)) {
							newProfileNameEditBox.sendKeys(newName);
							if(Utilities.explicitWaitClickableNew(driver, newProfileDateBox, 10)) {
								try {
									newProfileDateBox.click();
									Thread.sleep(1000);
									if(Utilities.explicitWaitClickableNew(driver, calendarOK, 10)) {
										try {
											calendarOK.click();
											if(Utilities.explicitWaitClickableNew(driver, nextButton, 10)) {
												try {
													nextButton.click();
													if(Utilities.explicitWaitClickableNew(driver, nextButton, 10)) {
														try {
															nextButton.click();
															try {
																for(int i=0;i<5;i++) {
																	favoriteImages.get(i).click();
																}
																if(Utilities.explicitWaitClickableNew(driver, nextButton, 10)) {
																	try {
																		nextButton.click();
																		if(Utilities.explicitWaitClickableNew(driver, letsGoButton, 10)) {
																			letsGoButton.click();
																			test.log(LogStatus.INFO, "Created new profile: "+newName);
																		}
																	}
																	catch(Exception e) {
																		test.log(LogStatus.FAIL, "Failed to tap Lets Go button, profile creation failed");
																	}
																}
															}
															catch(Exception e) {
																test.log(LogStatus.FAIL, "Failed to select favorites");
															}
														}
														catch(Exception e) {
															test.log(LogStatus.FAIL, "Failed to tap Next button");
														}
													}
												}
												catch(Exception e) {
													test.log(LogStatus.FAIL, "Failed to tap Next button");
												}
											}
										}	
										catch(Exception e) {
											test.log(LogStatus.FAIL, "Failed to enter date");
										}
									}				
								}
								catch(Exception e) {
									test.log(LogStatus.FAIL, "Failed to enter date");
								}
							}
						}
						else {
							test.log(LogStatus.FAIL, "Failed to enter name");
						}
					}
					catch(Exception e) {
						test.log(LogStatus.FAIL, "Failed to tap on Create New button");
					}
				}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Failed to get number of profiles");
				}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Failed to tap on Profile button");
			}
		}
	}
	
	public String[] getTimeToSelect(String systemTime) {
		String[] timeDetails=new String[2];
		systemTime=systemTime.split(":")[0];
		String[] sysTimeTemp=systemTime.split(" ");
		systemTime=sysTimeTemp[sysTimeTemp.length-1];
		int systemTimeInt=Integer.parseInt(systemTime);
		if(systemTimeInt>12) {
			systemTimeInt=systemTimeInt-12;
			systemTimeInt=systemTimeInt+1;//Adding 1 to pass the time one hour more than current time
			systemTime=String.valueOf(systemTimeInt);
			timeDetails[0]=systemTime;
			timeDetails[1]="PM";
		}
		else if(systemTimeInt==12) {
			systemTimeInt=systemTimeInt-12;
			systemTimeInt=systemTimeInt+1;//Adding 1 to pass the time one hour more than current time
			systemTime=String.valueOf(systemTimeInt);
			timeDetails[0]=systemTime;
			timeDetails[1]="PM";
		}
		else {
			systemTimeInt=systemTimeInt+1;//Adding 1 to pass the time one hour more than current time
			systemTime=String.valueOf(systemTimeInt);
			timeDetails[0]=systemTime;
			timeDetails[1]="AM";
		}
		return timeDetails;
	}
	
	
	public void waitForVideoToLoad() throws Exception {
		for (int k = 0; k < 25; k++) {
			if (!Utilities.explicitWaitVisibleNew(driver, loaderRipple, 3))
				break;
			Thread.sleep(3000);
		}
		if (Utilities.explicitWaitVisibleNew(driver, loaderRipple, 3)) {
			for (int k = 0; k < 10; k++) {
				if (Utilities.explicitWaitVisibleNew(driver, loaderRipple, 3))
					break;
				else
					Thread.sleep(5000);
			}
			if (Utilities.explicitWaitVisibleNew(driver, loaderRipple, 3)) {
				test.log(LogStatus.FAIL,"Video not loaded within 45 seconds");
			}
		}
	}
	
	
	public void deleteProfile(String ProfileName) throws Exception {

		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		SettingsPageV2 settingsPage = new SettingsPageV2(driver, test);
		if (Utilities.explicitWaitClickableNew(driver, homepagev2.profilepic, 80)) {
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
						if (Utilities.explicitWaitClickableNew(driver, settingsPage.settingsProfile, 60)) {
							settingsPage.settingsProfile.click();
							test.log(LogStatus.INFO, "Click on Profile link in settings page");

							// delete Profile
							try {

								driver.findElement(By.xpath(
										"//android.widget.TextView[@text='PROFILES' or @text='Profiles' and @resource-id='com.viacom18.vootkids:id/toolbar_title']/../../../..//android.widget.TextView[@text='"
												+ ProfileName + "']"))
										.click();

								if (Utilities.explicitWaitClickableNew(driver, settingsPage.deleteProfileLink, 60)) {
									settingsPage.deleteProfileLink.click();
									test.log(LogStatus.INFO, "click on delete profile link");
									if (Utilities.explicitWaitClickableNew(driver, settingsPage.prodelPopYesBtn, 60)) {
										settingsPage.prodelPopYesBtn.click();
										test.log(LogStatus.INFO, "click on yes button on delete pop up");

										// navigate to home page

										for (int i = 0; i <= 3; i++) {
											driver.navigate().back();
										}

										// Navigate to downloads page in My Stuff screen
										try {
											HomePageV2.tabClick("My Stuff");
										} catch (Exception e) {
											// TODO: handle exception
										}

										DownloadsPageV2 downloadsPage = new DownloadsPageV2(driver, test);
										for (int i = 0; i <= 20; i++) {
											if (Utilities.explicitWaitVisibleNew(driver,
													downloadsPage.downloadsTabMystuffpage, 1)) {
												downloadsPage.downloadsTabMystuffpage.click();
												break;
											} else
												Utilities.verticalSwipe(driver);
										}

										// click on Esit downloads button in Downloads Page
										Utilities.verticalSwipe(driver);
										if (Utilities.explicitWaitClickableNew(driver,
												downloadsPage.editDownloadsMystuff, 5)) {

											downloadsPage.editDownloadsMystuff.click();

										} else {
											test.log(LogStatus.FAIL, "EDIT DOWNLOADS button is not clickable");
										}

									} else
										BasePageV2.reportFail(
												"Failed to click on yes button on delete pop up / Not displayed");
								} else {
									BasePageV2.reportFail(
											"Failed click on delete link in Edit Profile page / Not displayed");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							BasePageV2.reportFail("Failed to click on profile link in settings page");
							BasePageV2.takeScreenshot();
						}

					} else {
						test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
						BasePageV2.takeScreenshot();
					}
				} else {
					test.log(LogStatus.FAIL, "Failed to set the Pin in parent zone page / Not displayed");
				}

			} else
				BasePageV2.reportFail("Not displayed Parent Zone button in switch profile screen/ Not click");

		} else
			BasePageV2.reportFail("Not displayed Profile pic icon in home page / not click");
	}
	
	//Method to clear last viewed secton
	public void clearLastViewedContents() throws Exception {
		HomePageV2.tabClick("My Stuff");
		Thread.sleep(2000);
		HomePageV2 homepage = new HomePageV2(driver, test);
		String xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']";
		Utilities.verticalSwipe(driver, xpath, "visible", 6);
		if(Utilities.explicitWaitVisibleNew(driver, recentViewedClear, 3)) {
			recentViewedClear.click();
			if(Utilities.explicitWaitVisibleNew(driver, homepage.confirmLogout, 10)) {
				homepage.confirmLogout.click();
				Thread.sleep(5000);
			}
			else BasePageV2.reportFail("Unable to clear the Last Viewed contents");
		}
		else {
			
		}
			
	}
	
	
}
