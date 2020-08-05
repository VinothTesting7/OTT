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

public class WatchPageV2 extends BasePageV2{
	public WatchPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog']")
	public WebElement lastViewedYesBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='LAST VIEWED' or @text='Last Viewed' or @text='last viewed' and @resource-id='com.viacom18.vootkids:id/recent_title_text']/following-sibling::android.widget.TextView[@text='Clear' or @text='CLEAR' and @resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement lastViewedTrayClearLink;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement clearBtnLastviewed;
	
	@FindBy(xpath="(//android.widget.TextView[@text='TOP TOONS' and @resource-id='com.viacom18.vootkids:id/recent_title_text']/../..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement topToonsTrayFirstCardd;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_media_image']")
	public WebElement characterImg;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_listing_items']//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement lastviewedFirstcard;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"Cellular playback off!\" or @text=\"Cellular Playback Off!\" and @resource-id='com.viacom18.vootkids:id/header_dialog']") 
	public WebElement cellularplaybackOffMsg;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_button_container']")
	public WebElement okBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='EPISODES' and @resource-id='com.viacom18.vootkids:id/recent_title_text']/../..//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement episodesTrayFirstCardTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='MUST WATCH MOVIES' or @text='Must Watch Movies']")
	public WebElement watchMoviesTrayTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='MUST WATCH MOVIES' or @text='Must Watch Movies']//../..//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
	public WebElement movieFirstCard;
	
	@FindBy(xpath="//android.widget.TextView[@text='TOP TOONS' and @resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement topToonsTray;
	
	@FindBy(xpath="//android.widget.TextView[@text='TOP TOONS' and @resource-id='com.viacom18.vootkids:id/recent_title_text']/../..//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
	public WebElement topToonsTrayFirstCard;
	
	@FindBy(xpath="//android.widget.TextView[@text='EPISODES' and @resource-id='com.viacom18.vootkids:id/recent_title_text']/../..//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
	public WebElement episodesTrayFirstCard;
	
	@FindBy(id="com.viacom18.vootkids:id/button_preview")
	public WebElement playBtn;
	
	
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='Episodes' or @text='EPISODES' or @text='episodes']")
	public WebElement episodesTray;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement showNameInShowDetails;
	
	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='Episodes' or @text='EPISODES' or @text='episodes']//parent::*//parent::*//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_list_container']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement episodesItemOneInEpisodeTray;	
	
	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='Episodes' or @text='EPISODES' or @text='episodes']//parent::*//parent::*//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_list_container']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[2]")
	public WebElement episodesItemTwoInEpisodeTray;	
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='1']")
	public WebElement cardSecodinwatchcarousel;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']")
	public WebElement EpisodecardTitle;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/favourite_button']")
	public WebElement watchFirstItemPlayerFav;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and (@text='Channels' or @text='CHANNELS')]/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/root_view_segmented_tray']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup")
	public WebElement channelTabFirstShow;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and (@text='Characters' or @text='CHARACTERS')]/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/root_view_segmented_tray']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup")
	public WebElement charactersTabFirstShow;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Channels' or @text='CHANNELS']")
	public WebElement channelsTab;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and (@text='Characters' or @text='Characters')]")
	public WebElement characterstab;
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']")
	public WebElement cardinwatchcarousel;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement allCharacterText;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")
	public List<WebElement> watchCharactersText1;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")
	public List<WebElement> watchCharactersText2;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup[@index='4']//android.widget.ImageView")
	public WebElement allCharactersFourthShow;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement trayTitle;

	@FindBy(id="com.viacom18.vootkids:id/btn_download_item")
	public WebElement downloadEpisodesLink;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recyclerview_detail_screen']/android.view.ViewGroup[@index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement characterDetailsTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/title_container']/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/android.widget.FrameLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.view.ViewGroup")
	public WebElement allCharactersFirstItem;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@index='1']")
	public WebElement firstEpisodeInCarousalName;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='1']//android.widget.TextView[@index='1']")
	public WebElement secondEpisodeInCarousalName;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement allCharactersFirstShow;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/favourite_button']")
	public WebElement vodPlayerFavourite;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container']//android.widget.FrameLayout//android.widget.LinearLayout//android.widget.RelativeLayout//android.widget.TextView[@index='1']")
	public WebElement firstItemInCarousal;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']")
	public WebElement firstItemCarousal;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container']//android.widget.FrameLayout//android.widget.LinearLayout//android.widget.RelativeLayout//android.widget.TextView[@index='1']")
	public WebElement firstItemInCarousalName;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container']//android.widget.FrameLayout//android.widget.LinearLayout//android.widget.RelativeLayout//android.widget.TextView[@index='2']")
	public WebElement firstItemInCarousalDesc;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/skin_overlay_controls_layout']")
	public WebElement watchFirstItemPlayer;

	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/button_play_pause_toggle']")
	public WebElement watchFirstItemPlayerPause;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/parent::android.view.ViewGroup/parent::android.view.ViewGroup//android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[@index='3']")
	public WebElement watchCharactersFirstItem;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_back']")
	public WebElement backButton;
	
	@FindBy(xpath = "//android.widget.Button[@text='SEE ALL']")
	public WebElement seeAll;
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/player_media_route_button' and @content-desc='Cast button. Disconnected']")
	public WebElement playerCastIconDisconnected;
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/player_media_route_button' and @content-desc='Cast button. Connected']")
	public WebElement playerCastIconConnected;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Live Now' or @text='LIVE NOW']")
	public WebElement livenowTab;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='LAST VIEWED' or @text='Last Viewed' or @text='last viewed']")
	public WebElement lastviewedTray;
	
	@FindBy(xpath="//android.view.ViewGroup[@index='0']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement allKidsCharacters1;
	
	@FindBy(xpath="//android.view.ViewGroup[@index='1']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement allKidsCharacters2;

	@FindBy(id="com.viacom18.vootkids:id/btn_playlist_expand")
	public WebElement playerExpand;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement showsTray;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement showsTrayFirstShow;
	
// Added by Saravanan on 1st JAN
	@FindBy(xpath = "//android.widget.TextView[@text='Akbar & Birbal']")
	public WebElement akbarShow_In_TopToons;

	@FindBy(xpath = "//android.widget.TextView[@text='PLAY']")
	public WebElement akbarShow_PlayButton_In_TopToons;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/button_back']")
	public WebElement backButton_OnShow;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement firstShow_In_TopToons;
	
	@FindBy(xpath = "//android.widget.TextView[@text='PLAY']")
	public WebElement firstShow_PlayButton_In_TopToons;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@index='2']")
	public WebElement firstEpisodeInCarousalDesc;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/subtitle']")
	public WebElement cardnameinwatchcarousel;
	
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/player_download' and @content-desc='NEW']")
	public WebElement vodPlayerDownloadButton;	
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/header_dialog' and @text='Download' or @text='DOWNLOAD' or @text='download']")
	public WebElement vodPlayerDownloadPopUpTitle;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/body_dialog']")	
	public WebElement vodPlayerDownloadPopUpMessage;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']")
	public WebElement vodPlayerDownloadPopUpOKButton;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/player_download' and @content-desc='IN_PROGRESS']")
	public WebElement vodPlayerDownloadInProgressButton;


	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement vodPlayerDownloadInProgressPopUpMessage;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog_text']")
	public WebElement vodPlayerDownloadInProgressPopUpYes;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog_text']")
	public WebElement vodPlayerDownloadInProgressPopUpNo;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/player_download' and @content-desc='DOWNLOAD_COMPLETE']")
	public WebElement vodPlayerDownloadCompletedButton;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/header_dialog' and @text='Video Downloaded' or @text='VIDEO DOWNLOADED' or @text='Video downloaded']")
	public WebElement vodPlayerDownloadCompletedPopUpTitle;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement vodPlayerDownloadCompletedPopUpMessage;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']")
	public WebElement vodPlayerDownloadCompletedPopUpOK;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/header_dialog' and @text='Download in progress' or @text='DOWNLOAD IN PROGRESS' or @text='Download In Progress']")
	public WebElement vodPlayerDownloadInProgressPopUpTitle;
	
	
	
	
	
	
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/skin_overlay_controls_layout']//*[@resource-id='com.viacom18.vootkids:id/player_title']")
	public WebElement vodPlayerShowTitle;	
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/skin_overlay_controls_layout']//*[@resource-id='com.viacom18.vootkids:id/player_subtitle']")
	public WebElement vodPlayerVodTitle;

}