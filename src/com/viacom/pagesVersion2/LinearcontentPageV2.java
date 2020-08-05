package com.viacom.pagesVersion2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class LinearcontentPageV2 extends BasePageV2
{
	public LinearcontentPageV2(AndroidDriver driver, ExtentTest test)
	{
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/rc_segment_tray']//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='0']//android.widget.ImageView")
	public WebElement linearcontent;

	@FindBy(id = "com.viacom18.vootkids:id/player_close_btn")
	public WebElement linearPlayerClose;

	@FindBy(id = "com.viacom18.vootkids:id/player_title")
	public WebElement linearPlayerTitle;

	@FindBy(id = "com.viacom18.vootkids:id/player_setting")
	public WebElement linearplayerSettings;

	@FindBy(id = "com.viacom18.vootkids:id/player_subtitle")
	public WebElement linearplayerSubTitle;

	@FindBy(id = "com.viacom18.vootkids:id/back_ward")
	public WebElement linearplayerBackwardButton;

	@FindBy(id = "com.viacom18.vootkids:id/button_play_pause_toggle")
	public WebElement linearplayerPlayPauseButton;
	
	@FindBy(id = "com.viacom18.vootkids:id/btn_playlist_expand")
	public WebElement linearplayerBottomUpArrowButton;

	@FindBy(id = "com.viacom18.vootkids:id/btn_playlist_collapse")
	public WebElement linearplayerBottomDownArrowButton;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/skin_overlay_controls_layout']")
	public WebElement linearvideoPlayer;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'LIVE') or contains(@text,'Live') or contains(@text,'live')]")
	public WebElement LiveIcon;

}
