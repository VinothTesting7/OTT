package com.viacom.pagesVersion2;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class BooksPageV2 extends BasePageV2{

	public BooksPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/button_preview' and @text='READ']")
	public WebElement bookDetailPageBookReadButton;
	
	@FindBy(id="com.viacom18.vootkids:id/button_preview")
	public WebElement bookTryButton;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement upArrowrelatedEbookFirstCardtitle;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[2]")
	public WebElement upArrowrelatedEbookSecondCardtitle;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/button_preview_animation_view']")
	public WebElement readBtnBook;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement relatedEbookFirstCardtitle;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[2]")
	public WebElement relatedEbookSecondCardtitle;
	
	@FindBy(xpath="//android.widget.TextView[@text='Read Time']/preceding-sibling::android.widget.TextView")
	public WebElement bookDetailPageReadTime1;
	 
	@FindBy(id="com.viacom18.vootkids:id/container_inner_carousel")
	public WebElement bookCarousal;
	
	@FindBy(id="com.viacom18.vootkids:id/iv_close")
	public WebElement closeButtonOnDownloadOverlay;
	
	@FindBy(id="com.viacom18.vootkids:id/rtv_download")
	public WebElement downloadButtonPostFlipping;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'to continue reading')]")
	public WebElement continueReadingMessage;
	
	@FindBy(xpath="//android.widget.TextView[@text='NEW BOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement newBooksFirstBook;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']")
  	public WebElement bookDetailPageDownloadBookButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/button_preview' and @text='TRY']")
	public WebElement bookDetailPageBookPreviewButton;
	
	@FindBy(id="com.viacom18.vootkids:id/textview_title")
//	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title'])[2]")
	public WebElement relatedBookDetailPageBookTitle;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement profilepic;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement firstTrayFirstBook;
	
	@FindBy(id="com.viacom18.vootkids:id/checkbox_fav_selector")
	public WebElement bookDetailPageFavoriteButton;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='false']")
	public WebElement bookUnFavoritedButton;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector' and @checked='true']")
	public WebElement bookFavoritedButton;
	
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView)[4]")
	public WebElement firstTraySecondBook;
	
	@FindBy(id="com.viacom18.vootkids:id/imageview_media_image")
	public WebElement bookDetailPageBookCoverImage;
	
	@FindBy(id="com.viacom18.vootkids:id/textview_title")
	public WebElement bookDetailPageBookTitle;
	

	@FindBy(id="com.viacom18.vootkids:id/textview_author_name")
	public WebElement bookDetailPageBookAuthorName;
		
	@FindBy(id="com.viacom18.vootkids:id/textview_description")
	public WebElement bookDetailPageBookDescription;
	
    @FindBy(xpath="//android.widget.TextView[@text='Level']//ancestor::android.widget.LinearLayout//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_level_count']")
    public WebElement bookDetailPageBookLevelCount;
	
    @FindBy(xpath="//android.widget.TextView[@text='Read Time']//ancestor::android.widget.LinearLayout//android.widget.TextView")
    public WebElement bookDetailPageReadTime;

    @FindBy(xpath="//android.widget.TextView[@text='Narration']//ancestor::android.widget.LinearLayout//android.widget.ImageView")
    public WebElement bookDetailPageNarrationIcon;
	
    @FindBy(id="com.viacom18.vootkids:id/button_back")
  	public WebElement bookDetailPageBackButton;
	
  	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_download_item']")
  	public WebElement bookDetailPageDownlaodEpisodesButton;
  	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']")
	public WebElement bookDetailPageRelatedBooksTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement bookDetailPageRelatedTrayFirstContent;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.ImageView")
	public WebElement bookDetailPageRelatedTrayContents;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement bookDetailPageEditorialSectionRelatedTrayFirstContentTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public List<WebElement> bookDetailPageEditorialSectionRelatedTrayContentTitles;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']////android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/category_icon']")
	public List<WebElement> bookDetailPageEditorialSectionRelatedTrayContentIcons;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and  @text='RELATED EBOOKS']//ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/recent_recycler_view']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_description']")
	public List<WebElement> bookDetailPageEditorialSectionRelatedTrayContentBookDescriptions;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement firstTray;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView")
	public WebElement firstTrayEbook;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement firstTrayEbookTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.view.ViewGroup[@index='1']//android.widget.ImageView")
	public WebElement firstTraySecondEbook;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement firstTraySecondEbookTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloading')]")
	public WebElement downloadingProgress;	
	
	@FindBy(id="com.viacom18.vootkids:id/btn_download_item")
	public WebElement downloadBookLink;

	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title' and contains(@text,'Cancel Download')]")
	public WebElement cancelDownloadOption;
	
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_dialog' and (contains(@text,'DELETE') or contains(@text,'Delete'))]")
	public WebElement cancelDownloadPopup;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog' and (contains(@text,'YES') or contains(@text,'Yes'))]")
	public WebElement cancelDownloadPopupYesButton;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog' and (contains(@text,'NO') or contains(@text,'No') or contains(@text,'Cancel'))]")
	public WebElement cancelDownloadPopupNoButton;
	
	
	@FindBy(id="com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement cancelDownloadPopuCancelDialog;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloaded')]")
	public WebElement downloadedBookLink;	
	
	@FindBy(xpath="//android.widget.TextView[@text='Download Book']")
	public WebElement downloadBookText;
	
	
	@FindBy(id="com.viacom18.vootkids:id/seek")
	public WebElement readerSeekBar;
	
	@FindBy(id="com.viacom18.vootkids:id/tv_chapter_number")
	public WebElement readerCurrentChapter;
	
	@FindBy(id="com.viacom18.vootkids:id/tv_seek_progress")
	public WebElement readerPageNumber;
	
	
	@FindBy(id="com.viacom18.vootkids:id/animation_view")
	public WebElement readerProgressBar;
	
	@FindBy(id="com.viacom18.vootkids:id/animation_view")
	public List<WebElement> readerProgress;
	
	@FindBy(xpath = "//android.widget.TextView[@text='BEAUTY AND THE BEAST']")
	public WebElement firstBook_In_Carosel;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/close']")
	public WebElement closeButton;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Mini Marvels']")
	public WebElement miniMarvelBook;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']//parent::*//parent::*//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
	public WebElement audioFromTray;
	
	@FindBy(xpath="//android.widget.TextView[@text='PREVIEW' or @text='Preview' or @text='preview']")
	public WebElement previewButton;
	
	@FindBy(xpath="//*[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement bookDetailsPageBookTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/button_preview' and @text='TRY' or @text='Try' or @text='try']")
	public WebElement tryButton;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/button_preview' and @text='READ' or @text='Read' or @text='read']")
	public WebElement readButton;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvBookFirstItemTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvBookSecondItemTitle;
	
	@FindBy(xpath = "//*[@text='Clear' or @text='clear' or @text='CLEAR']//parent::*//parent::*//*[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lvBookThirdItemTitle;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and @text='Download Book' or @text='DOWNLOAD BOOK' or @text='Download book' or @text='download book']")
	public WebElement downloadBook;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and @text='Downloaded Book' or @text='DOWNLOADED BOOK' or @text='Downloaded book' or @text='downloaded book']")
	public WebElement downloadedBook;
	
	public void verifyReaderProgressBar() throws Exception
	{
		for(int k=0;k<25;k++) 
	       { 
			 if(!Utilities.explicitWaitVisible(driver, readerProgressBar, 3))	
	       break;
	     	Thread.sleep(3000);
	        }           
	     if(Utilities.explicitWaitVisible(driver, readerProgressBar, 3))
	          {
		          for(int k=0;k<10;k++) 
	               { 
	     		      if(readerProgress.size()==0)	
	                    break;
	     		       else
	     			    Thread.sleep(5000);
	               }      
	         if(readerProgress.size()>0)
		            reportFail("Reader not launched within 45 seconds");
	          }
	  }
	public void slideHalfBookReader(AndroidDriver driver) throws Exception
	{
		HomePageV2 homePage = new HomePageV2(driver, test);
	    int startX = 0;
	    String chapterbefscrub="", chapteraftscrub="" ;
	    //Get vertical location of seekbar.
	    int startY = 0;
	    int endX =0;
	    if(Utilities.explicitWaitVisible(driver,readerCurrentChapter, 10))
		  {
	    	chapterbefscrub=readerCurrentChapter.getText();
			 test.log(LogStatus.INFO, "Chapter is displayed on the Book Reader screen before scrubbing- "+chapterbefscrub);
		  }
		  else
			  BasePageV2.reportFail("Duration watched is not displayed");	
	if(Utilities.explicitWaitClickable(driver, readerSeekBar, 20))
		{
		// Get x and y coordinates of webelement// 
	     startX = readerSeekBar.getLocation().getX();       
	    //Get vertical location of seekbar.
	     startY = readerSeekBar.getLocation().getY();
	     endX = 400 + startX;
	  }
	else
		reportFail("Seek bar is not visible");
	
	//Verifying fwd scrub
	    TouchAction action1 = new TouchAction((MobileDriver)driver);
	  try{
		  action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY )).release().perform();
	  }
	  catch(Exception e){BasePageV2.reportFail("Failed to slide in the reader");}
	  
		  
	   if(Utilities.explicitWaitVisible(driver,readerCurrentChapter, 10))
		  {
	    	chapteraftscrub=readerCurrentChapter.getText();
			 test.log(LogStatus.INFO, "Chapter is displayed on the Book Reader screen after scrubbing- "+chapteraftscrub);
			 if(!chapteraftscrub.equals(chapterbefscrub))
				  test.log(LogStatus.INFO, "Scrubber has moved forward succesfully");
			 else
				  BasePageV2.reportFail("Failed to scrub in reader forward");
		  }
		  else
			  BasePageV2.reportFail("Chapter number is not displayed");	
	  //  homePage.verifyAndProgressBar();
	}
  	
}
