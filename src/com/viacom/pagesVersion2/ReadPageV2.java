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

public class ReadPageV2 extends BasePageV2{
	public ReadPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement readAgainImgTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/you_might_like_text']")
	public WebElement readAgainYouMightLikeText;
	
	@FindBy(xpath="//android.widget.TextView[@text='NOTHING HERE' or @text='Nothing Here' and @resource-id='com.viacom18.vootkids:id/empty_view']")
	public WebElement nothingHere;
	
	@FindBy(xpath="//*[@text='READ AGAIN' or @text='READ Again' and @resource-id='com.viacom18.vootkids:id/read_again']")
	public WebElement readAgainBtn;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/grid_background']")
	public WebElement readAgainImgCard;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/up_next_close_button']")
	public WebElement readAgainCloseBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='PREVIEW' or @text='Preview' or @text='TRY' or @text='Try']")
	public WebElement previewButton;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement book1InTray;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[2]")
	public WebElement book2InTray;
	
	@FindBy(xpath="//android.widget.TextView[@text='PREVIEW' or @text='TRY' or @text='READ' or @text='Preview' or @text='Read' or @text='Try']")
	public WebElement tryPreviewReadButton;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement nameFromDetailsPage;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']")
	public WebElement booknameincarouselb;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='LAST READ']")
	public WebElement LastReadTitle;
	
	@FindBy(xpath="//android.view.ViewGroup[@index='0']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement LastreadFirstcard;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2']")
	public WebElement firstTrayfirstBook;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt' and @text='Clear']")
	public WebElement LastReadClearBtn;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='0']/android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/reader_option']")
	public WebElement readerBookSettingsICon;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']")
	public WebElement readfirstBookcarosal;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2']")
	public List<WebElement> newAudioText1;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2']")
	public WebElement newAudioText1e;
		
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2']")
	public WebElement newAudioTextOne;	
	
	@FindBy(xpath="//android.widget.TextView[@text='NEW BOOKS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")
	public List<WebElement> newBook1;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector']")
	public WebElement favIconAudioDetails;
	
	@FindBy(xpath="//android.widget.TextView[@text='NEW BOOKS']")
	public WebElement newBooksTray;
	
	@FindBy(xpath="//android.widget.Button[@text='SEE ALL']")
	public WebElement seeAll;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']")
	public WebElement firstBookBelowCarousal;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='1']")
	public WebElement secondBookBelowCarousal;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']")
	public WebElement thirdBookBelowCarousal;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']")
	public WebElement firstBookNameBelowCarousal;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/subtitle']")
	public WebElement firstBookAuthorBelowCarousal;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']")
	public WebElement secondBookNameBelowCarousal;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement firstBookDetailsName;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_author_name']")
	public WebElement firstBookDetailsDesc;
	
	@FindBy(xpath="//android.widget.TextView[@text='READ']")
	public WebElement readButton;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/animation_view']")
	public WebElement animationView;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/rtv_download']")
	public WebElement downloadInPopup;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/iv_close']")
	public WebElement downloadInPopupClose;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/cover_page']")
	public WebElement downloadInPopupEbookImage;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_book_title']")
	public WebElement downloadInPopupEbookName;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_author_name']")
	public WebElement downloadInPopupEbookAuthor;
	
	@FindBy(xpath="//android.webkit.WebView")
	public WebElement eBookReader;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_book_downloading_text']")
	public WebElement downloadingText;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_dialog']")
	public WebElement downloadDisabledHeader;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement downloadDisabledText;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/positive_single_btn_dialog']")
	public WebElement downloadDisabledOK;
	
	@FindBy(xpath="//android.widget.TextView[@text='READ' or @text='Read']")
	public WebElement downloadedBookRead;	

	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/button_back']")
	public WebElement backAudioDetails;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/imageview_media_image']")
	public WebElement bookCoverImageAudioDetails;
	
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement firstItemInTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/iv_narration']")
	public WebElement narration;
	
	@FindBy(xpath="//android.widget.TextView[@text='NARRATION OFF']")
	public WebElement narrationOn;
	
	@FindBy(xpath="//android.view.View[@index='1']")
	public WebElement bookReader;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/close' or @resource-id='com.viacom18.vootkids:id/ll_close']")
	public WebElement bookReaderClose;
	
	@FindBy(xpath="//android.widget.TextView[@text='Download Book' or @text='Download book']")
	public WebElement downloadBookText;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and @text='Downloaded Book' or @text='Downloaded book']")
	public WebElement downloadedBookText;

	@FindBy(id="com.viacom18.vootkids:id/reader_option")
	public WebElement readerOptions;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/close']")
	public WebElement readerOptionsClose;
	
	@FindBy(xpath="//android.widget.TextView[@text='OPTIONS' or @text='Options']")
	public WebElement readerOptionsPageTitle;

	@FindBy(xpath="//android.widget.LinearLayout[@index='0']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title']")
	public WebElement defaultMagnify;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='0']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement defaultMagnifyON;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='1']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title']")
	public WebElement defaultDictionary;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='1']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement defaultDictionaryON;	
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='2']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title']")
	public WebElement defaultAutoPageTurn;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='2']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement defaultAutoPageTurnOFF;	
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='3']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title']")
	public WebElement defaultAutoZoom;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='3']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement defaultAutoZoomON;	
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/up_button']")
	public WebElement bookReaderUpArrow;	
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_playlist_collapse']")
	public WebElement bookReaderDownArrow;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
	public WebElement readerRelatedTab1stItem;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/android.widget.TextView[@index='0']")
	public WebElement readerRelatedTab1stItemName;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement bookNameInDetailsPage;
	
	@FindBy(xpath="//android.view.ViewGroup[@index='7']")
	public WebElement readerRelatedTab7thItem;	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Related']")
	public WebElement bookReaderTabRelated;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='More From Author']")
	public WebElement bookReaderTabMoreFromAuthor;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Daily Picks']")
	public WebElement bookReaderTabDailyPicks;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title']")
	public List<WebElement> bookReaderTabs;
	
	@FindBy(xpath="//android.widget.TextView[@text='NARRATION ON']")
	public WebElement narrationOff;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public WebElement defaultAllow;	
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector']")
	public WebElement favIconBookDetails;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@index='2']")
	public WebElement newBookText1;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@index='1']")
	public WebElement firstBookInCarousalName;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='1']//android.widget.TextView[@index='1']")
	public WebElement secondBookInCarousalName;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ln_item_container' and @index='0']//android.widget.TextView[@index='2']")
	public WebElement firstBookInCarousalDesc;
	
	///////bhaskar///
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/up_button']")
	public WebElement bookReaderuparrow;
	
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title']//ancestor::android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/container_inner_carousel']")
	public WebElement firstBookNameInCarousal;
		
	
	
	@FindBy(xpath="//android.widget.ImageView[@index='3' and @resource-id='com.viacom18.vootkids:id/grid_item_image']")
	public WebElement readerRelatedTab1stItemb;
	
	@FindBy(xpath="//android.widget.ImageView[@index='3' and @resource-id='com.viacom18.vootkids:id/grid_item_image']")
	public WebElement morefromauthorrelatedTab1stItemb;
	
	
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'Double')]")
	public WebElement readerFirstCoachCard;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'Swipe')]")
	public WebElement readerSecondCoachCard;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'Long')]")
	public WebElement readerThirdCoachCard;
	

	  @FindBy(id="com.viacom18.vootkids:id/fav")
		public WebElement bookReaderFavButton;
	  
	  @FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/fav' and @checked='false']")
			public WebElement bookReaderUnFavoriteButton;
	  
	  @FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/fav' and @checked='true']")
			public WebElement bookReaderFavoritedButton;
	
	
	
	
	public void dismissReaderCoachCards() throws Exception {
		Thread.sleep(2000);
		if(Utilities.explicitWaitClickableNew(driver, readerFirstCoachCard, 20)) {
			try {
				readerFirstCoachCard.click();
			}
			catch(Exception e) {}
		}
		if(Utilities.explicitWaitClickableNew(driver, readerSecondCoachCard, 2)) {
			try {
				readerSecondCoachCard.click();
			}
			catch(Exception e) {}
		}
		if(Utilities.explicitWaitClickableNew(driver, readerThirdCoachCard, 2)) {
			try {
				readerThirdCoachCard.click();
			}
			catch(Exception e) {}
		}		
	}
	


}