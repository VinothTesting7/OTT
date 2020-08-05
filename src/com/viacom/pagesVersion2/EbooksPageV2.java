package com.viacom.pagesVersion2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class EbooksPageV2 extends BasePageV2
{
	public EbooksPageV2(AndroidDriver driver, ExtentTest test)
	{
		super(driver, test);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'TRY') or contains(@text,'try') or contains(@text,'Try')]")
	public WebElement previewbutton;

	@FindBy(xpath="(//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/following::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement card1underbestreadspage;
	
	@FindBy(xpath="(//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/following::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[2]")
	public WebElement card2underbestreadspage;
			
	@FindBy(xpath="(//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/following::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[3]")
	public WebElement card3underbestreadspage;
	
	@FindBy(xpath="(//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/following::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[4]")
	public WebElement card4underbestreadspage;
	
	@FindBy(xpath="(//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/following::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[5]")
	public WebElement card5underbestreadspage;
	
@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='LAST READ']")
	public WebElement lastreadtrayinreadtab;

@FindBy(id="com.viacom18.vootkids:id/ll_close")
	public WebElement ebookspageclosebuttonb;

@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/iv_close']")
	public WebElement closebuttonindownloadpage;

@FindBy(xpath= "(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'])[1]")
	public WebElement bestreadstrayb;


	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_chapter_number' and contains(@text,'Page')]")
	public WebElement chapternumberb;
	
    @FindBy(xpath="//android.widget.SeekBar[@resource-id='com.viacom18.vootkids:id/seek']")
	public WebElement bookseekbar;

	/*@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card1underbestreadspage;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card2underbestreadspage;
	
		
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='2']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card3underbestreadspage;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='3']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card4underbestreadspage;
	
	@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='4']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card5underbestreadspage;
	*/
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog']")
	public WebElement yes;
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement clearbuttonb;
	
	/*@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='1']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card2underbestreadspage;*/
	
	/*@FindBy(xpath= "(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text'])[1]/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/title_container']")
	public WebElement lastreadtrayinreadtab;*/
	
	/*@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card1underbestreadspage;*/
	

     @FindBy(xpath="(//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_image_container']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[1]")
	public WebElement card1underLastreadspage;
		
	@FindBy(xpath="(//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_image_container']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[2]")
	public WebElement card2underLastreadspage;
	
	@FindBy(xpath="(//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_image_container']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[3]")
	public WebElement card3underLastreadspage;
		
	@FindBy(xpath="(//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_image_container']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[4]")
	public WebElement card4underLastreadspage;
	
	@FindBy(xpath="(//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_image_container']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title'])[5]")
	public WebElement card5underLastreadspage;

	
	/*@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/iv_close']")
	public WebElement closebuttonindownloadpage;*/
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/rtv_download']")
	public WebElement downloadbuttonb;
	
	@FindBy(id = "com.viacom18.vootkids:id/seek")
	public WebElement ebookseekBar;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/rtv_download' and @text='CANCEL']")
	public WebElement cancelbuttonb;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_seek_progress']")
	public WebElement pagesfromplayerb;
	
	@FindBy(id = "com.viacom18.vootkids:id/checkbox_fav_selector")
	public WebElement favourite;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'READ') or contains(@text,'Read') or contains(@text,'read')]")
	public WebElement Readbutton;
	
	
	////bhaskar////
	
	/*@FindBy(id="com.viacom18.vootkids:id/ll_close")
	public WebElement ebookspageclosebuttonb;*/
	
	//@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/close' and @index='0']")
	//public WebElement ebookspageclosebuttonb;
	@FindBy(xpath="//android.widget.TextView[contains(@text,'Page')]")
	public WebElement readerCurrentChapter;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/ll_close' and @index='1']")
	public WebElement closebuttonb;
	
	@FindBy(xpath="//*[@index='0' and @resource-id='com.viacom18.vootkids:id/progressBar']")
	public WebElement downloadingprogressbarb;
	
	@FindBy(xpath="//*[@text='Downloading..' and @resouce-id='com.viacom18.vootkids:id/tv_downloading']")
	public WebElement downloadingtextb ;
			
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='EARLY READERS']")
	public WebElement earlyreadersb;
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='LAST READ']")
	public WebElement lastreadtrayb;
	
	@FindBy(xpath= "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']/descendant::android.widget.TextView[@index='2' and @resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement lastviewedinlastreadtrayb;
	
	
	/*@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='BEST READS']")
	public WebElement bestreadstrayb;*/
	
		
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and @text='Download Book']")
	public WebElement downloadbooklinkb;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/download_status_imageview']")
	public WebElement downloadarrowb;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/rtv_download']")
	public WebElement downloadButton;
	
	@FindBy(xpath="//android.widget.TextView[@text='READ']")
	public WebElement readButton;
	
	@FindBy(xpath = "//android.widget.FrameLayout[@index='0']/..//android.view.ViewGroup[@index='1']//android.widget.TextView[@text='LAST READ' or @text='Last Read' or @text='last read']")
	public WebElement lastReadAfterCarousal;
	
	@FindBy(xpath = "//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text']")
	public WebElement lastReadTitle;
	
	@FindBy(xpath = "//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement lastReadClear;
	
	
	
	
	
	
	////bhaskar//////
}
