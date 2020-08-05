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

	@FindBy(xpath="//android.widget.TextView[@text='ALL CHARACTERS']")
	public WebElement allCharacterText;
	
	@FindBy(xpath="//android.widget.TextView[@text='ALL CHARACTERS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")
	public List<WebElement> watchCharactersText1;
	
	@FindBy(xpath="//android.widget.TextView[@text='ALL CHARACTERS']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView")
	public List<WebElement> watchCharactersText2;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.tv.vootkids:id/recyclerview_detail_screen']/android.view.ViewGroup[@index='0']//android.widget.TextView[@resource-id='com.tv.vootkids:id/textview_title']")
	public WebElement characterDetailsTitle;
	
	@FindBy(xpath="")
	public WebElement seeAll;
	
	@FindBy(xpath="")
	public WebElement allCharacters;
	
	@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/parent_for_carousal']//android.widget.LinearLayout[@resource-id='com.tv.vootkids:id/ln_item_container']")
	public WebElement firstItemInCarousal;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='com.tv.vootkids:id/skin_overlay_controls_layout']")
	public WebElement watchFirstItemPlayer;

	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.tv.vootkids:id/button_play_pause_toggle']")
	public WebElement watchFirstItemPlayerPause;
			
	@FindBy(xpath="//android.widget.CheckBox[@resource-id='com.tv.vootkids:id/favourite_button']")
	public WebElement watchFirstItemPlayerFav;


}