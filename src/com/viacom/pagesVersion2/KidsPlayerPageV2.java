package com.viacom.pagesVersion2;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class KidsPlayerPageV2 extends BasePageV2{
	public KidsPlayerPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	/// checking whether present again after pulling  TRY TRY TRY 
	//committing	
	
	@FindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/player_download_ripple']")
	public WebElement episodePlayerDownloadIcon;
	
	@FindBy(xpath="//android.view.ViewGroup[@index='1']/android.widget.TextView[@text='Low']")
	public WebElement playerSettingsLowQuality1;
	
	@FindBy(xpath="//android.view.ViewGroup[@index='1']//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/media_option_list_item']")
	public WebElement selectQualityList1;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/setting_item_list']//android.view.View[@resource-id='com.viacom18.vootkids:id/devider' and @index='2']/..//android.widget.TextView")
	public WebElement playerSettingsSelectSingleLanguage;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='0']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement MagnifySubTitle;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='1']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement DictionarySubTitle;
	
	@FindBy(xpath="//android.widget.LinearLayout[@index='2']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement AutoPageTurnSubTitle;
	
	@FindBy(id="com.viacom18.vootkids:id/textview_title")
	public WebElement AudioDetailPageAudioTitle;
	
	@FindBy(id="com.viacom18.vootkids:id/player_close_btn")
	public WebElement playerCloseButton;
	
	@FindBy(id="com.viacom18.vootkids:id/player_title")
	public WebElement playerTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED AUDIO']")
	public WebElement AudioDetailPageRelatedAudioTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public List<WebElement> playListEpisodeTitles;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_all_downloads' and (@text='ALL DOWNLOADS' or @text='All Downloads')]")
	public WebElement playListAllDownloadsTab; 

	@FindBy(xpath="//android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title']")
	public List<WebElement> playerSettingsSelectLanguages;
	
	@FindBy(id="com.viacom18.vootkids:id/close_button_audio_skin")
	public WebElement audioPlayerCloseButton;
	
	@FindBy(id="com.viacom18.vootkids:id/audio_skin_title")
	public WebElement audioPlayerTitle;
	
	@FindBy(id="com.viacom18.vootkids:id/player_subtitle")  
	public WebElement playerSubTitle;	
	
	@FindBy(id="com.viacom18.vootkids:id/player_setting")
	public WebElement playerSettings;
	
//	@FindBy(id="com.viacom18.vootkids:id/favourite_button")
	@FindBy(id="com.viacom18.vootkids:id/favourite_button_container")
	public WebElement playerFavoriteButton;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/favourite_button' and @checked='true']")
	public WebElement playerFavoritedButton;
	
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/favourite_button' and @checked='false']")
	public WebElement playerUnFavoritedButton;

	@FindBy(id="com.viacom18.vootkids:id/back_ward")
	public WebElement playerBackwardButton;
	
	@FindBy(id="com.viacom18.vootkids:id/button_play_pause_toggle")
	public WebElement playerPlayPauseButton;
	
	
	@FindBy(id="com.viacom18.vootkids:id/btn_playlist_expand")
	public WebElement playerBottomUpArrowButton;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_playlist_collapse")
	public WebElement playerBottomDownArrowButton;
	
	@FindBy(id="com.viacom18.vootkids:id/forward_player")
	public WebElement playerForwardButton;
	
	@FindBy(id="com.viacom18.vootkids:id/back_ward")
	public WebElement playerRewindButton;
	
	@FindBy(id="com.viacom18.vootkids:id/seek_bar") 
	public WebElement playerSeekBar;	
	
	@FindBy(id="com.viacom18.vootkids:id/player_seek_container") 
	public WebElement playerSeekBarContainer;	
	
	@FindBy(id="com.viacom18.vootkids:id/duration_player")
	public WebElement playerCurrentDuration;
	
	@FindBy(id="com.viacom18.vootkids:id/totla_duration_player")
	public WebElement playerTotalDuration;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/skin_overlay_controls_layout']")
	public WebElement videoPlayer; 
	
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Episodes']")
	public WebElement playListEpisodeTab; 
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_all_downloads' and (contains(@text,'FAVOURITE') or contains(@text,'Favourite'))]")
	public WebElement playListFavoritesTab; 
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_all_downloads']")
	public List<WebElement> playListduringFavoritesTabs; 
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title']")
	public List<WebElement> playlistTabs;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/linear_recycler_view']//android.view.ViewGroup")
	public List<WebElement> playListEpisodeContents; 
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/linear_recycler_view']//android.view.ViewGroup")
	public WebElement episodeVideoInPlaylist; 
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/linear_recycler_view']//android.view.ViewGroup[@index='2']")
	public WebElement episodeSecondVideoInPlaylist; 

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title'  and @text='Related']")
	public WebElement playListRelatedTab; 
	
	//Settings Screen
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement playListEpisodeTitle; 
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/linear_recycler_view']//android.view.ViewGroup[@index='2']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public WebElement playListSecondEpisodeTitle; 
	
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/media_option_list_item']//android.widget.TextView[contains(@text,'Select Language')]")
	public WebElement selectLanguageList;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/media_option_list_item']//android.widget.TextView[contains(@text,'Select Language')]/..//android.widget.TextView[@index='1']")
	public WebElement defaultSelectedLanguage;
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/media_option_list_item']//android.widget.TextView[contains(@text,'Stream Quality')]")
	public WebElement selectQualityList;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/media_option_list_item']//android.widget.TextView[contains(@text,'Stream Quality')]/..//android.widget.TextView[@index='1']")
	public WebElement defaultSelectedQuality;
	
	@FindBy(id="com.viacom18.vootkids:id/player_setting_close_btn")  
	public WebElement playerSettingsCloseButton;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/setting_item_list']//android.view.View[@resource-id='com.viacom18.vootkids:id/devider' and @index='1']/..//android.widget.TextView")
	public WebElement playerSettingsSelectLanguage;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'Go to Settings to set')  and @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement selectLanguagePopUp;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement selectLanguagePopUpCloseDialog;
	
	@FindBy(xpath="//*[@text='Ok' or @text='OK']")
	public WebElement selectLanguagePopUpOkButton;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/quality_item_list']//android.view.View[@resource-id='com.viacom18.vootkids:id/devider' and @index='1']/..//android.widget.TextView")
	public WebElement playerSettingsSelectQuality;
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/devider' and @index='1']/..//android.widget.TextView[@text='Low']")
	public WebElement playerSettingsLowQuality;
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/devider' and @index='1']/..//android.widget.TextView[@text='High']")
	public WebElement playerSettingsHighQuality;
	
	@FindBy(xpath="//android.view.View[@resource-id='com.viacom18.vootkids:id/devider' and @index='2']/..//android.widget.TextView[@text='Auto']")
	public WebElement playerSettingsAutoQuality;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_video_duration")  
	public WebElement playerGridDuration;
	
	@FindBy(xpath="//android.widget.FrameLayout//android.widget.ImageView")  
	public WebElement upNextImage;
	
	/*@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'  and @text='EPISODES']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement showDetailPageEpisodesSectionFirstVideo;*/
	
	@FindBy(id="com.viacom18.vootkids:id/cancel_button")  
	public WebElement cancelVideoButton;
	
	@FindBy(id="com.viacom18.vootkids:id/cancel_button")  
	public WebElement replayVideoButton;
	@FindBy(xpath="//android.widget.TextView[@text='Up Next']")  
	public WebElement upNextTextOnOverlay;
	
	@FindBy(id="com.viacom18.vootkids:id/grid_title") 	
	public WebElement videoTitleonUpNextOverlay;
	
//	@FindBy(xpath="//android.widget.Button[@text='CANCEL']")  
  @FindBy(id="com.viacom18.vootkids:id/cancel_button") 	
	public WebElement upnextOverlayCancelButton;
	

	
  @FindBy(id="com.viacom18.vootkids:id/fav")
	public WebElement bookReaderFavButton;
    @FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/fav' and @checked='true']")
	public WebElement bookReaderBookFavorited;
    
    @FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/fav' and @checked='false']")
	public WebElement bookReaderBookUnFavorited;

	@FindBy(xpath="//android.webkit.WebView[@resource-id='com.viacom18.vootkids:id/readerWebView']")
	public WebElement bookReaderWebView;
	
	@FindBy(id="com.viacom18.vootkids:id/reader_option")
	public WebElement bookReaderSettingsButton;
	
	@FindBy(id="com.viacom18.vootkids:id/book_close")
	public WebElement bookReaderBackButton;
	
	@FindBy(id="com.viacom18.vootkids:id/up_button")
	public WebElement bookReaderUpArrowButton;
	
	
	@FindBy(id="com.viacom18.vootkids:id/close")
	public WebElement bookReaderCloseButton;
  
	
	@FindBy(id="com.viacom18.vootkids:id/iv_narration")
	public WebElement bookReaderNarrationButton;
  
	
	@FindBy(id="com.viacom18.vootkids:id/tv_book_desc")
	public WebElement bookReaderBookDescription;
	
	@FindBy(id="com.viacom18.vootkids:id/tv_book_title")
	public WebElement bookReaderBookTitle;
	
	@FindBy(id="com.viacom18.vootkids:id/tv_chapter_number")
	public WebElement bookReaderChapterNumber;
	
	@FindBy(id="com.viacom18.vootkids:id/tv_seek_progress")
	public WebElement bookReaderPageNumber;
	
	@FindBy(id="com.viacom18.vootkids:id/seek")
	public WebElement bookReaderSeekBar;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Magnify')]")
	public WebElement bookReaderOptionMagnify;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Magnify')]/..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title' and contains(@text,'OFF')]")
	public WebElement bookReaderOptionMagnifyOff;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Dictionary')]")
	public WebElement bookReaderOptionDicionary;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Dictionary')]/..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title' and contains(@text,'OFF')]")
	public WebElement bookReaderOptionDicionaryOff;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Auto Page Turn')]")
	public WebElement bookReaderOptionAutoPageTurn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Auto Page Turn')]/..//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title' and contains(@text,'OFF')]")
	public WebElement bookReaderOptionAutoPageTurnOff;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Animation')]")
	public WebElement bookReaderOptionAnimation;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and contains(@text,'Text Size')]")
	public WebElement bookReaderOptionTextSize;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title' and contains(@text,'OFF')]")
	public List<WebElement> bookReaderOptionOff;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title' and contains(@text,'ON')]")
	public List<WebElement> bookReaderOptionOn;
	
	///bhaskar//
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/up_button']")
	public WebElement bookReaderuparrow;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and @text='Auto Zoom.']")
	public WebElement bookReaderOptionAutozoomb;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and @text='Auto Zoom.']")
	public WebElement bookReaderOptionAutozoomTurnOffb;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_title' and @text='Auto Zoom.']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/option_sub_title']")
	public WebElement bookReaderOptionAutozoomTurnOnb;
	
	
	
	@FindBy(xpath="//android.widget.SeekBar[@resource-id='com.viacom18.vootkids:id/seek']")
	public WebElement bookReaderseekbar;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_chapter_number']")
	public WebElement bookReaderchapternumber;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_seek_progress']")
	public WebElement bookReadertotalpages;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/iv_narration' and @text='Narration Not Available']")
	public WebElement bookReadernarrationnotavailable;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_book_title']")
	public WebElement bookReaderbookname;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/fav' and @index='2']")
	public WebElement bookReaderfavouriteicon;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_book_author']")
	public WebElement bookReaderauthorname;
	

	@FindBy(id="com.viacom18.vootkids:id/reader_option")
	public WebElement bookReaderoptionsButton;
	
	///bhasakr//////
	
	public void playVideo() throws Exception{
		TouchAction action = new TouchAction(driver);
	    Thread.sleep(4000);
	    int vx=videoPlayer.getSize().getWidth();
	    int vy=videoPlayer.getSize().getHeight();
	    int startx=vx/2;
	    int starty=vy/2;
		  if(Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 3))
		  {
			  playerPlayPauseButton.click();
			  test.log(LogStatus.INFO, "Tapped on Play button");
		  }
		  else
		  {
			  try{
				  videoPlayer.click();	
			       action.press(PointOption.point(startx,starty)).release().perform();
			       
			       test.log(LogStatus.INFO, "Tapped on Play button");
			  }
			  catch(Exception e){   }
		  }

		
	}
	
	public void pauseVideo() throws Exception
	{
	TouchAction action = new TouchAction(driver);
    Thread.sleep(4000);
    int vx=videoPlayer.getSize().getWidth();
    int vy=videoPlayer.getSize().getHeight();
    int startx=vx/2;
    int starty=vy/2;
	
		  if(Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 3))
		  {
			  playerPlayPauseButton.click();			  
			  Thread.sleep(4000);
		  }
		  else
		  {
			  try{
			      // videoPlayer.click();	
			  /*     action.tap(PointOption.point(startx,starty)).perform();
			       action.tap(PointOption.point(startx,starty)).perform();*/
				  action.press(PointOption.point(startx,starty)).release().perform().waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).press(PointOption.point(startx,starty)).release().perform();
				  
			     }
			  catch(Exception e){   e.printStackTrace();}
			  			  
			  //Check Whether paused
			  Thread.sleep(5000);
			  if(Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 3))
			  {
				  test.log(LogStatus.INFO, "Tapped on Pause button");
			  }
			  else
			  {
				  try{
				      // videoPlayer.click();	
				      /* action.tap(PointOption.point(startx,starty)).perform();
				       action.tap(PointOption.point(startx,starty)).perform();*/
					  
					  action.press(PointOption.point(startx,starty)).release().perform().waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).press(PointOption.point(startx,starty)).release().perform();
				     }
				  catch(Exception e){   e.printStackTrace();}
				  if(Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 3))
					  test.log(LogStatus.INFO, "Tapped on Pause button"); 
				  else if(Utilities.explicitWaitVisible(driver, replayVideoButton,3))
				  test.log(LogStatus.INFO, "Not able to pause as content playback is over");
				  else
				  BasePageV2.reportFail("Failed to pause");
			  }
		  }
	}
	
	private TapOptions PointOptions(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	public void slideFull(AndroidDriver driver) throws Exception
	{
		HomePageV2 homePage = new HomePageV2(driver, test);
	    int startX = 0;
	    String timebefscrub="", timeaftscrub="" , timeaftscrubback="";
	    //Get vertical location of seekbar.
	    int startY = 0;
	    int endX =0;
	    if(Utilities.explicitWaitVisible(driver,playerCurrentDuration, 10))
		  {
			 timebefscrub=playerCurrentDuration.getText();
			 test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen before scrubbing the video- "+timebefscrub);
		  }
		  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
	if(Utilities.explicitWaitClickable(driver, playerSeekBar, 20))
		{
		// Get x and y coordinates of webelement// 
	     startX = playerSeekBar.getLocation().getX();       
	    //Get vertical location of seekbar.
	     startY = playerSeekBar.getLocation().getY();
	     endX =playerSeekBar.getSize().getWidth();
	  }
	else
		reportFail("Seek bar is not visible");
	
	//Verifying fwd scrub
	    TouchAction action1 = new TouchAction((MobileDriver)driver);
	  try{
		  action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX+startX, startY)).release().perform();
	  }
	  catch(Exception e){BasePageV2.reportFail("Failed to scrub the video");}
	  
	  //  homePage.verifyAndProgressBar();
	}
	//scrub-half functionality
	public void slidehalf(AndroidDriver driver) throws Exception
	{
		HomePageV2 homePage = new HomePageV2(driver, test);
	    int startX = 0;
	    String timebefscrub="", timeaftscrub="" , timeaftscrubback="";
	    //Get vertical location of seekbar.
	    int startY = 0;
	    int endX =0;
	    if(Utilities.explicitWaitVisible(driver,playerCurrentDuration, 10))
		  {
			 timebefscrub=playerCurrentDuration.getText();
			 test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen before scrubbing the video- "+timebefscrub);
		  }
		  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
	if(Utilities.explicitWaitClickable(driver, playerSeekBar, 20))
		{
		// Get x and y coordinates of webelement// 
	     startX = playerSeekBar.getLocation().getX();       
	    //Get vertical location of seekbar.
	     startY = playerSeekBar.getLocation().getY();
	     endX = 400 + startX;
	  }
	else
		reportFail("Seek bar is not visible");
	
	//Verifying fwd scrub
	    TouchAction action1 = new TouchAction((MobileDriver)driver);
	  try{
		  action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release().perform();
	  }
	  catch(Exception e){BasePageV2.reportFail("Failed to scrub the video");}
	  Thread.sleep(4000);
	  if(!Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 4))
	  {    
		homePage.verifyAndProgressBar();
	    pauseVideo();
	  }
	    /*try{
	    	for(int i=1;i<5;i++)
	    	   if(!Utilities.explicitWaitVisible(driver,playerCurrentDuration, 5))
	    	   {
	    		   videoPlayer.click();
	    		   
	    		   playerPlayPauseButton.click();
	    		   Thread.sleep(3000);
	    	   }
	    	   else
	    		   break;
	    }catch(Exception e)
	    {
	    	reportFail("Failed to pause the video after scrubbing  video forward");
	    }*/
	    
	    if(Utilities.explicitWaitVisible(driver,playerCurrentDuration, 10))
		  {
			 timeaftscrub=playerCurrentDuration.getText();
			 test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen after scrubbing the video forward- "+timeaftscrub);
			 if(!timeaftscrub.equals(timebefscrub))
				  test.log(LogStatus.PASS, "Scrubber has moved forward succesfully");
			 else
				  test.log(LogStatus.INFO, "Failed to scrub the video forward");
		  }
		  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
	    
	  //Verifying backward scrub 
	    
	    try{
			  action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX-300, startY)).release().perform();
		  }
	      catch(Exception e){BasePageV2.reportFail("Failed to scrub the video backward");}
	    Thread.sleep(4000);
		    
			  if(!Utilities.explicitWaitVisible(driver, playerPlayPauseButton, 4))
			  {    
				homePage.verifyAndProgressBar();
			    pauseVideo();
			  }
		    /*try{
			    try{
			    	for(int i=1;i<5;i++)
			    	   if(!Utilities.explicitWaitVisible(driver,playerCurrentDuration, 5))
			    	   {
			    		   videoPlayer.click();
			    		   playerPlayPauseButton.click();
			    		   Thread.sleep(3000);
			    	   }
			    	   else
			    		   break;
			    }
			    catch(Exception e)
			    {
			    	reportFail("Failed to pause the video after scrubbing  video forward");
			    }
		    }
		    catch(Exception e)
		    {
		    	reportFail("Failed to pause the video after scrubbing  video backward");
		    }*/
		    
		    if(Utilities.explicitWaitVisible(driver,playerCurrentDuration, 5))
			  {
				 timeaftscrubback=playerCurrentDuration.getText();
				 test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen after scrubbing the video backward- "+timeaftscrubback);
				 if(!timeaftscrub.equals(timeaftscrubback))
				 test.log(LogStatus.INFO, "Scrubber has moved backward succesfully");
				 else
				 test.log(LogStatus.INFO, "Failed to scrub the video backward");
			  }
			  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
		    
		    
		    
		    
		    
	        
	}
	
	public int convertStringDurationTOIntegerSeconds(AndroidDriver driver, String duration) throws Exception{
		String stringMin="";
		String stringSec="";
		int intMin=0;
		int intSec=0;
		int totalDuration=0;
		String[] list=new String[2];
		list=duration.split(":");
		stringMin=list[0];
		stringSec=list[1];
		intMin=Integer.parseInt(stringMin);
		intSec=Integer.parseInt(stringSec);
		totalDuration=(intMin*60)+intSec;
		return totalDuration;
	}
	
	public void pauseAudio() throws Exception
	{
		  if(Utilities.explicitWaitClickable(driver, playerPlayPauseButton, 15))
		  {
			  playerPlayPauseButton.click();	
			  test.log(LogStatus.INFO, "Paused audio");
			  Thread.sleep(2000);
		  }
		  else {
			  test.log(LogStatus.FAIL, "Failed to pause audio");
		  }
	}
	
	public boolean slidehalfInPlayer(AndroidDriver driver) throws Exception
	{
		HomePageV2 homePage = new HomePageV2(driver, test);
	    int startX = 0;
	    String timebefscrub="", timeaftscrub="" , timeaftscrubback="";
	    //Get vertical location of seekbar.
	    int startY = 0;
	    int endX =0;
	    if(Utilities.explicitWaitVisibleNew(driver,playerCurrentDuration, 10)){
	    	timebefscrub=playerCurrentDuration.getText();
			test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen before scrubbing the video- "+timebefscrub);
		}
		else {
			test.log(LogStatus.FAIL, "Duration watched is not displayed");	
			return false;
		}
	    if(Utilities.explicitWaitClickableNew(driver, playerSeekBar, 20)){
		    // Get x and y coordinates of webelement// 
	        startX = playerSeekBar.getLocation().getX();       
	        //Get vertical location of seekbar.
	        startY = playerSeekBar.getLocation().getY();
	        endX = 400 + startX;
	    }
	    else {
	    	test.log(LogStatus.FAIL, "Seek bar is not visible");
	    	return false;
	    }
	    //Verifying fwd scrub
	    TouchAction action1 = new TouchAction((MobileDriver)driver);
	    try{
		  action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release().perform();
		  test.log(LogStatus.INFO, "Scrubbed forward");
		  try {
			  driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			  playerPlayPauseButton.click();
		  }
		  catch(Exception e) {}
		  
	    }
	    catch(Exception e)
	    {
	    	test.log(LogStatus.FAIL, "Failed to scrub the video");
	    	return false;
	    }
	    Thread.sleep(4000);
	    if(!Utilities.explicitWaitVisibleNew(driver, playerPlayPauseButton, 4)){    
		    homePage.verifyAndProgressBar();
			pauseVideo();
	    }
	    if(Utilities.explicitWaitVisibleNew(driver,playerCurrentDuration, 10)){
			timeaftscrub=playerCurrentDuration.getText();
			test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen after scrubbing the video forward- "+timeaftscrub);
			if(!timeaftscrub.equals(timebefscrub))
				test.log(LogStatus.INFO, "Scrubber has moved forward succesfully");
			else {
				test.log(LogStatus.FAIL, "Failed to scrub forward");
				return false;
			}
		}
		else {
			test.log(LogStatus.FAIL, "Duration watched is not displayed");	
			return false;
		}
	    //Verifying backward scrub 
	    try {
			action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX-300, startY)).release().perform();
			test.log(LogStatus.INFO, "Scrubbed backward");
	    }
	    catch(Exception e){
	    	test.log(LogStatus.FAIL, "Failed to scrub backward");
	    }
	    Thread.sleep(4000);
		if(!Utilities.explicitWaitVisibleNew(driver, playerPlayPauseButton, 4)){    
			homePage.verifyAndProgressBar();
			pauseVideo();
		}
		if(Utilities.explicitWaitVisibleNew(driver,playerCurrentDuration, 5))
		{
			timeaftscrubback=playerCurrentDuration.getText();
			test.log(LogStatus.INFO, "Duration watched is displayed on the Player screen after scrubbing the video backward- "+timeaftscrubback);
			if(!timeaftscrub.equals(timeaftscrubback)) {
				test.log(LogStatus.INFO, "Scrubber has moved backward succesfully");
				return true;
			}
			else {
				test.log(LogStatus.FAIL, "Failed to scrub the video backward");
				return false;
			}
		}
		else {
			test.log(LogStatus.FAIL, "Duration watched is not displayed");	
			return false;
		}
	}
}
