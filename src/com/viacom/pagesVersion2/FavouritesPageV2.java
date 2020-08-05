package com.viacom.pagesVersion2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class FavouritesPageV2 extends BasePageV2{
	
	public FavouritesPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	
	//////////////Added by me///////////////
	
	
	@FindBy(xpath="//android.widget.TextView[@text='Movies' and @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement favouriteMoviesTab;
	
	
	@FindBy(xpath = "//android.widget.TextView[@text='FAVOURITES' or @text='Favourites' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement favFavoritesTitle1;
	
	@FindBy(id="com.viacom18.vootkids:id/frame_layout_unfavourite_container")
	public WebElement unfavbtn;

	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_fav_selector']")
	public WebElement favStartToFav;
	
	@FindBy(id="com.viacom18.vootkids:id/textview_fav_item_title")
	public WebElement favCardTitle;
	
	@FindBy(xpath="//*[contains(@class,'ActionBar')][@index='0']")
	//@FindBy(xpath=("(//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tabs']//android.widget.ImageView[@resource-id='com.tv.vootkids:id/tab_image'])[0]"))
	public WebElement mystuff_tab;
	
	
	@FindBy(xpath="//android.widget.TextView[@text='Edit' and @resource-id='com.viacom18.vootkids:id/btn_edit']")
	public WebElement editLinkinEditpage;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_back")
	public WebElement favBackBtn;
	
	@FindBy(xpath = "//android.widget.TextView[@text='FAVOURITES' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement favFavoritesTitle;
	
	@FindBy(xpath="//android.widget.TextView[@text='Cancel' and @resource-id='com.viacom18.vootkids:id/btn_edit']")
	public WebElement favEditPageCancelLink;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_back")
	public WebElement favEditpageBackBtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='Episodes' and @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement favEditPageEpisodes;
	
	@FindBy(xpath="//android.widget.TextView[@text='Books' and @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement favEditPageBooks;
	
	@FindBy(xpath="//android.widget.TextView[@text='Audiobooks' and @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement favEditPageAudiobooks;
	
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.viacom18.vootkids:id/checkbox_un_fav_selector']")
	public WebElement favIConStar;
	
	@FindBy(xpath="//android.widget.TextView[@text = \" UNFAVOURITE ( 1 ) \" or @resource-id='com.viacom18.vootkids:id/textview_unfav_count']")
	public WebElement unfavTextViewEditPage;
	
	//////////////End by me ////////////////////
	
	
}
