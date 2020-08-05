package com.viacom.pagesVersion2;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;


public class PlayPageV2 extends BasePageV2{

	public PlayPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//android.view.ViewGroup")
	public WebElement viewGroup;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'LOGIC SMART')]")
	public WebElement logicSmartSkillTitle;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'DISCOVER SMART')]")
	public WebElement discoverSmartSkillTitle;
	
	@FindBy(xpath = "//android.widget.TextView[@text='PLAY']")
	public WebElement skillPlayButton;
	
	@FindBy(id = "com.viacom18.vootkids:id/button_close")
	public WebElement closeButton;
	
	@FindBy(xpath = "//android.widget.TextView[@text='DONE']")
	public WebElement doneButton;
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/question_background_container']")
	public WebElement questionLabel;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/answer_image']")
	public WebElement answerButton;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/answer_image']")
	public List<WebElement> answerButtonAsList;
	
//	pop-up
	@FindBy(id = "com.viacom18.vootkids:id/button_got_it_animated_view")
	public WebElement doneOnTestSkillPopUp;
	
	@FindBy(id="com.viacom18.vootkids:id/player_setting")
	public WebElement playerSettings;
	
	@FindBy(id="com.viacom18.vootkids:id/media_option_list_item")
	public WebElement QualityOptionsList;
	
	@FindBy(xpath="//*[contains(@text,'High') or contains(@text,'high') or contains(@text,'HIGH')]")
	public WebElement HighQuality;
	
	@FindBy(xpath="//*[contains(@text,'Auto') or contains(@text,'auto') or contains(@text,'AUTO')]")
	public WebElement AutoQuality;
	
	@FindBy(xpath="//*[contains(@text,'Low') or contains(@text,'low') or contains(@text,'LOW')]")
	public WebElement LowQuality;
}
