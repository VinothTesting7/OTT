package com.viacom.pagesVersion2;

import java.util.List;

//import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class ShowsPageV2 extends BasePageV2{
	public ShowsPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title'  and @text='Live Now']")
	public WebElement liveNowTab;
	
	@FindBy(name= "All Shows")
	public WebElement allShowsTab;
	
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup")
	//@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_live_logo']//ancestor::android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup")
	public WebElement liveTabFirstVideoTitle;
	
	@FindBy(id="com.viacom18.vootkids:id/textview_download_item_title")
	public WebElement episodeTitleInDownloadEpisodesScreen;	
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.viacom18.v18.viola:id/banner_image_hero_tray']")
	public WebElement allShowsFeaturevideo;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup")
	//@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_live_logo']//ancestor::android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup")
	public WebElement liveTabFirstVideo;
	
	
	
	@FindBy(xpath= "//android.widget.RadioButton[@text='Originals']")
	public WebElement originalsTab;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public List<WebElement> notAddedEpisodes;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public List<WebElement> downloadedEpisodes;	
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.v18.viola:id/tv_series_title']")
	public List<WebElement> allEpisodesList;
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.viacom18.v18.viola:id/banner_image_hero_tray']")
	public WebElement originalsFeaturevideo;
	
	@FindBy(id="com.viacom18.vootkids:id/imageview_media_state_button")
	public WebElement downloadIconInDownloadsScreen;
	
	
	@FindBy(id="com.viacom18.vootkids:id/imageview_media_state_button")
	public List<WebElement> downloadIconsInDownloadsScreen;
	
	@FindBy(id="com.viacom18.vootkids:id/textview_download_item_title")
	public List<WebElement> downloadsScreenEpisodeTitles;
	
	@FindBy(id="com.viacom18.vootkids:id/imageview_media_state_button")
	public List<WebElement> downloadedEpisodeIcons;
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text']")
	public WebElement episodesPageTitle;
	
	@FindBy(xpath= "//android.widget.TextView[@text='EPISODES']")
	public WebElement episodesTray;
	
	//@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2' and @resource-id='com.viacom18.vootkids:id/grid_description']")
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public List<WebElement> showDetailsEpisodes;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement showDetailsEpisode1;	
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement showDetailsEpisode1Title;	
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']")
	public WebElement showDetailsEpisode2;	
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement showDetailsEpisode2Title;	
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']")
	public WebElement showDetailsEpisode3;	
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement showDetailsEpisode3Title
	
	;	
	//to be changed
	@FindBy(id="com.viacom18.vootkids:id/imageview_media_state_button")
	public WebElement downloadedEpisodeIcon;
	
	@FindBy(name ="Most Popular")
	public WebElement mostPopularTab;
	
	@FindBy(xpath ="//android.widget.TextView[contains(@text,'Voot Shorts')]")
	public WebElement vootShortsTab;
	
	@FindBy(xpath ="//android.widget.TextView[@resource-id='com.viacom18.v18.viola:id/txt_related_title']")
	public List<WebElement> vootShortsRelatedTitle;
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title' and contains(@text,'Cancel Download')]")
	public WebElement cancelDownloadOption;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_dialog' and (contains(@text,'DELETE') or contains(@text,'Delete'))]")
	public WebElement cancelDownloadPopup;
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog' and (contains(@text,'YES') or contains(@text,'Yes'))]")
	public WebElement cancelDownloadPopupYesButton;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog' and (contains(@text,'NO') or contains(@text,'No') or contains(@text,'Cancel'))]")
	public WebElement cancelDownloadPopupNoButton;
	
	//sHOW DETAIL TESTCASE 
	
	
	@FindBy(id="com.viacom18.vootkids:id/button_back")
	public WebElement showDetailPageBackButton;
	
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_media_image']")
	public WebElement showDetailPageCharacterImage;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/button_preview' and @text='PLAY']")
	public WebElement showDetailPagePlayButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement showDetailPageShowTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_description']")
	public WebElement showDetailPageShowInfo;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text' and (@text='DOWNLOAD EPISODES' or @text='Download Episodes')]")
	public WebElement downloadEpisodesScreenTitle;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_available_langauges']")
	public WebElement showDetailPageLanguagesAvailableInfo;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']")
	public WebElement showDetailPageDownlaodEpisodesButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']")
	public WebElement showDetailPageEpisodesSection;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement showDetailPageEpisodesSectionFirstVideo;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public List<WebElement> showDetailPageEpisodesSectionVideos;

      @FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public List<WebElement> showDetailPageEpisodesSectionTitles;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public List<WebElement> showDetailPageEpisodesSectionTitlesWay2;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and ( @text='RELATED SHOWS'  or @text='You May Also Like')]")
	public WebElement showDetailPageEditorialSection;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED SHOWS']")
	public WebElement showDetailPageEditorialSectionRelatedTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  ( @text='YOU MAY ALSO LIKE'  or @text='You May Also Like')")
	public WebElement showDetailPageEditorialSectionYouMayLikeTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED SHOWS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement showDetailPageEditorialSectionRelatedTrayFirstContent;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED SHOWS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement showDetailPageEditorialSectionRelatedTrayFirstContentTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='You May Also Like']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement showDetailPageEditorialSectionYouMayLikeTrayFirstContent;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @index='4']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public WebElement notAddedEpisode4;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @index='4' and @content-desc='DOWNLOAD_COMPLETE']")
	public WebElement downloadedEpisode4;
	
	
	@FindBy(name ="A - Z")
	public WebElement atozTab;
	
	@FindBy(id ="com.viacom18.v18.viola:id/btn_filter_shows")
	public WebElement filterTab;

	 @FindBy(id="com.viacom18.v18.viola:id/title_toolbar")
	 public WebElement toolBarTitle;
	 
	 @FindBy(id="com.viacom18.v18.viola:id/title")
	 public WebElement playingVideoTitle;

	 
	 @FindBy(id="com.viacom18.v18.viola:id/show_description")
	 public WebElement descriptionDetailText;
	 
	 @FindBy(id="com.viacom18.v18.viola:id/more_btn")
	 public WebElement moreChevronBtn;
	 
	 @FindBy(id="com.viacom18.v18.viola:id/meta_data")
	 public WebElement showMetaData;
	 
	 @FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector']")
	 public WebElement favIconShowDetails;
	 
		
	 @FindBy(xpath ="//android.widget.TextView[@text='Download Episodes']")
	 public WebElement downloadEpisodesText;
		
	@FindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/parent_download_item']/android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='1']")
	public List<WebElement> toDownloadEpisodes;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/positive_btn']")
	public WebElement downloadButton;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @index='0']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public WebElement notAddedEpisode;	
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_download_item_title']")
	public WebElement notAddedEpisode2name;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_media_item_size']")
	public WebElement notAddedEpisode2episode;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @index='1' and @content-desc='DOWNLOAD_COMPLETE']")
	public WebElement downloadedEpisode2;
	
	@FindBy(xpath="//android.widget.TextView[@text='SEE ALL' or @text='see all' or @text='See All' or @text='See all']")
	public WebElement episodesSeeAll;	
	
	
	 @FindBy(xpath="//android.widget.TextView[@text='ADDED TO FAVORITES']")
	 public WebElement favIconPopup;
	 
	 @FindBy(xpath="//android.widget.TextView[contains(@text,'All your favorites contents are available under My Stuff section')]")
	 public WebElement favIconPopupMessage;
	 
	 @FindBy(id="com.viacom18.vootkids:id/positive_single_btn_dialog")
	 public WebElement favIconPopupOkButton;
	 
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.view.ViewGroup[@index='1']//android.widget.ImageView")
		public WebElement showDetailPageEpisodesSectionSecondVideo;
	 @FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='true']")
	 public WebElement favIconChecked;
	 
	 
	 @FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='false']")
	 public WebElement favIcon;

	 
	 
}

	
	

