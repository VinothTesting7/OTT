package com.viacom.pagesVersion2;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class ChannelsPageV2 extends BasePageV2 {
	public ChannelsPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	

	//////// added properties
	@FindBy(id ="com.viacom18.vootkids:id/btn_back")
	public WebElement charactersLiveCardBackBTN;
	
	@FindBy(xpath="//android.widget.TextView[@text='Characters' or @text='characters' or @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement charactersTab;
	
	@FindBy(xpath="//android.widget.TextView[@text='Schedule' or @text='schedule' or @resource-id='com.viacom18.vootkids:id/tab_title']")
	public WebElement scheduleTab;
	
	@FindBy(xpath="//android.widget.Button[@text='SEE ALL' or @text='See All' or @text='see all' or @resource-id='com.viacom18.vootkids:id/grid_more_button']")
	public WebElement seeAllButton;
	
	@FindBy(xpath="//android.widget.TextView[@text='CHARACTERS' or text='Characters' or @text='characters' or @resource-id='com.viacom18.vootkids:id/header_text']")
	public WebElement charactersTitle;
	
	/////// End Properties///////

}
