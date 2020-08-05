package com.viacom.pagesVersion2;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;

import io.appium.java_client.android.AndroidDriver;


public class ListenPageV2 extends BasePageV2{
	
	public ListenPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement audio1InTray;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[2]")
	public WebElement audio2InTray;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_title")
	public List<WebElement> moreFromTitleList;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_title")
	public List<WebElement> moreFromAuthorList;
	
	@FindBy(id="com.viacom18.vootkids:id/play_pause")
	public WebElement audioPauseBTN;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/button_preview_animation_view']")
	public WebElement audioPlayBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"DOWNLOAD DISABLED!\" or @resource-id='com.viacom18.vootkids:id/header_dialog']")
	public WebElement DownloadDisablesMsg;
	
		@FindBy(xpath="//android.widget.Button[@text='OK' and @resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']")
	public WebElement DownloadDisablesOKBtn;
	
		@FindBy(xpath="//android.widget.TextView[@text=\"Please go to Settings to enable downloads.\" or  @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement DownloadDisablesSubMsg;
	
		@FindBy(id="com.viacom18.vootkids:id/positive_button_container")
	public WebElement DownloadDisablesOKBtn1;
	
	/////////////end///////////
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@index='1']//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.ImageView")
	public WebElement secondTrayAudioStory;
	 
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@index='1']//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement secondTrayAudioStoryTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement secondTrayAudioStoryTitleFromDetails;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement firstTrayAudioStoryTitleFromDetails;	
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/close_button_audio_skin']")
	public WebElement audioPlayerListenAgainClose;
	
	@FindBy(xpath="//android.widget.TextView[@text='PLAY']")
	public WebElement play_btn;
		
	@FindBy(id="com.viacom18.vootkids:id/button_back")
	public WebElement back_btn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement audioDetailsAudioName;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_author_name']")
	public WebElement audioDetailsAuthorName;
	
	@FindBy(id="com.viacom18.vootkids:id/textview_title")
	public WebElement title_inside_showdetailPage;
	
	@FindBy(xpath="//android.widget.Button[@text='SEE ALL']")
	public WebElement seeAll;

	@FindBy(xpath="//android.widget.TextView[@text='NEW AUDIOSTORIES']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")
	public List<WebElement> newAudioText1;
	
	@FindBy(xpath="//android.widget.TextView[@text='NEW AUDIOSTORIES']/parent::android.view.ViewGroup[@index='0']/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='1']/android.widget.TextView")
	public WebElement secondAudio;
	
	@FindBy(xpath="//android.widget.TextView[@text='Download Audiobook']")
	public WebElement downloadAudioBookText;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement firstTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.ImageView")
	//@FindBy(xpath="//android.widget.TextView[@text='NEW AUDIOSSTORIES']/parent::android.view.ViewGroup[@index='1']//parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup[@index='1']/android.widget.TextView")
	public WebElement firstTraySecondAudioStory; 
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
//	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement firstTraySecondAudioStoryTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView")
	//@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/..//*/android.view.ViewGroup")
	public WebElement firstTrayAudioStory;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	//@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/..//*/android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement firstTrayAudioStoryTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloading')]")
	public WebElement downloadingProgress;	
	
	@FindBy(id="com.viacom18.vootkids:id/btn_download_item")
	public WebElement downloadAudioBookLink;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title' and contains(@text,'Cancel Download')]")
	public WebElement cancelDownloadOption;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_dialog' and (contains(@text,'DELETE')   or contains(@text,'Delete'))]")
	public WebElement cancelDownloadPopup;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog']")
	public WebElement cancelDownloadPopupYesButton;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog']")
	public WebElement cancelDownloadPopupNoButton;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement cancelDownloadPopuCancelDialog;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloaded')]")
	public WebElement downloadedAudioBookLink;	
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='false']")
	public WebElement audioUnFavoritedButton;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='true']")
	public WebElement audioFavoritedButton;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']")
	public WebElement firstItemCarousal;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title' and @index='1']")
	public WebElement firstItemCarousalName;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/subtitle' and @index='2']")
	public WebElement firstItemCarousalAuthor;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/button_preview' or @text='PLAY' or @text='Play' or '@text'='play']")
	public WebElement firstItemCarousalPlay;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/button_play_pause_toggle']")
	public WebElement firstItemCarousalPausePlay;
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/player_media_route_button' and @content-desc='Cast button. Disconnected']")
	public WebElement playerCastIconDisconnected;
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/player_media_route_button' and @content-desc='Cast button. Connected']")
	public WebElement playerCastIconConnected;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/mr_chooser_route_name']")
	public List<WebElement> castTvList;
	
	@FindBy(xpath="//android.widget.Button[@text='STOP CASTING' or @text='Stop Casting' or @text='Stop casting']")
	public WebElement stopCasting;
	
	@FindBy(xpath="//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/mr_close']")
	public WebElement stopCastDialogCancel;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/forward_player']")
	public WebElement playerForward;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/back_ward']")
	public WebElement playerBackward;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/duration_player']")
	public WebElement playerDurationElapsed;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/totla_duration_player']")
	public WebElement playerDurationTotal;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/coach_indicator']")
	public WebElement playerSeekBar;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/close_button_audio_skin']")
	public WebElement playerCloseButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/mr_name']")
	public WebElement stopCastDialogTV;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//parent::*//parent::*//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
	public WebElement audioFromTray;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/mr_art']")
	public WebElement stopCastDialogImage;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/mr_control_title']")
	public WebElement stopCastDialogAudioName;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/mr_control_subtitle']")
	public WebElement stopCastDialogAudioAuthor;
	
	@FindBy(xpath="//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/mr_control_playback_ctrl']")
	public WebElement stopCastDialogPlayButton;
	
	@FindBy(xpath="//android.widget.SeekBar[@resource-id='com.viacom18.vootkids:id/mr_volume_slider']")
	public WebElement stopCastDialogVolume;	

	@FindBy(xpath="//android.widget.TextView[@text='Downloading Audiobook' or @text='Downloading AudioBook']")
	public WebElement downloadingAudioText;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and @text='Downloaded']")
	public WebElement downloadedAudioText;	
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_list_container']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public List<WebElement> favMoreThan8;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector']")
	public WebElement favAudioDetails;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/audio_setting']")
	public WebElement audioPlayerSettings;	
	
	@FindBy(xpath="//android.widget.TextView[@text='OPTIONS' or @text='Options' or @text='options']")
	public WebElement audioPlayerSettingsOptions;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/player_setting_close_btn']")
	public WebElement audioPlayerSettingsClose;	
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/devider']")
	public WebElement audioPlayerSettingsDivider;		
	
	@FindBy(xpath="//android.widget.TextView[@text='Stream Quality. ']")
	public WebElement audioPlayerSettingsStreamQualityText;	
	
	@FindBy(xpath="//android.widget.TextView[@text='Auto']")
	public WebElement audioPlayerSettingsSelectQualityDefault;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/page_title']")
	public WebElement audioPlayerSettingsStreamQualityTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='Auto']")
	public WebElement audioPlayerSettingsStreamQualityAuto;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='Low']")
	public WebElement audioPlayerSettingsStreamQualityLow;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='Medium']")
	public WebElement audioPlayerSettingsStreamQualityMedium;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='High']")
	public WebElement audioPlayerSettingsStreamQualityHigh;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/player_setting_close_btn']")
	public WebElement audioPlayerSettingsStreamQualityClose;
	
	@FindBy(id = "com.viacom18.vootkids:id/btn_playlist_expand")
	public WebElement audioPlayerUpArrowExpand;

	@FindBy(id = "com.viacom18.vootkids:id/btn_playlist_collapse")
	public WebElement audioPlayerUpArrowCollapse;
	
	@FindBy(xpath="//android.widget.TextView[@text='More From Author' or @text='More from author' or @text='More from Author']")
	public WebElement audioPlayerMoreFromAuthor;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement audioPlayerMorFrmAuthrFirstAudio;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement audioPlayerMorFrmAuthrFirstAudioName;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement audioPlayerMorFrmAuthrFirstAudioAuthor;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement audioPlayerMorFrmAuthrAudioDetailsName;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_author_name']")
	public WebElement audioPlayerMorFrmAuthrAudioDetailsAuthor;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2']")
	public WebElement newAudioTextOne;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.view.ViewGroup[@index='1']/android.widget.TextView[@index='2']")
	public WebElement newAudioTextTwo;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector']")
	public WebElement favIconAudioDetails;
	
	@FindBy(id="com.viacom18.vootkids:id/favourite_button")
	public WebElement audioPlayerFavouriteButton;
	
	@FindBy(id = "com.viacom18.vootkids:id/button_preview_animation_view")
	public WebElement playButtonInBook;
	
	@FindBy(xpath = "//android.widget.FrameLayout[@index='0']/..//android.view.ViewGroup[@index='1']//android.widget.TextView[@text='LAST EXPLORED BY YOU']")
	public WebElement lastAudioAfterCarousal;
	
	@FindBy(xpath = "//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement lastAudioTitle;
	
	@FindBy(xpath = "//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement lastAudioClear;
	
	@FindBy(id="com.viacom18.vootkids:id/close_button_audio_skin")
	public WebElement audioPlayerClose;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@index='1']")
	public WebElement firstAudioInCarousalName;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@index='2']")
	public WebElement firstAudioInCarousalDesc;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='1']//android.widget.TextView[@index='1']")
	public WebElement secondAudioInCarousalName;
	
	/////bhaskar//////
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/close_button_audio_skin']")
	public WebElement closebuttoninaudioplayerpage;

	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement lastupdatedintrayb;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']")
	public WebElement audionameNameincarousel;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']")
	public WebElement audioincarousel;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='LAST VIEWED']")
	public WebElement lastviewedtrayb;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastviewedintrayb;

	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement nameFromDetailsPage;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_author_name']")
	public WebElement authorFromDetailsPage;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/button_preview' and @text='PLAY' or @text='Play' or '@text'='play']")
	public WebElement playFromDetailsPage;

	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/favourite_button' and @checked='false']")
	public WebElement audioPlayerUnFavouriteButton;
	
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/favourite_button' and @checked='true']")
	public WebElement audioPlayerFavouritedButton;
	
	
	public static void waitForAudioDetailsToLoad() throws Exception {
		ListenPageV2 listenpagev2=new ListenPageV2(driver,test);
		for(int wait=0;wait<=10;wait++) {
			if(Utilities.explicitWaitVisibleNew(driver, listenpagev2.playFromDetailsPage, 1)) {
				test.log(LogStatus.INFO, "Navigated to Audio details page");
				break;
			}
			else {
				Thread.sleep(2000);
				if(wait==10)
					test.log(LogStatus.FAIL, "Audio Details did not load even after 20 seconds");
			}
		}
	}
	
}
