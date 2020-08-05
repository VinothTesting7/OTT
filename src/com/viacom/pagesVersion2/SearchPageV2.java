package com.viacom.pagesVersion2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;

import io.appium.java_client.android.AndroidDriver;

public class SearchPageV2 extends BasePageV2
{

	public SearchPageV2(AndroidDriver driver, ExtentTest test)
	{
		super(driver, test);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='RECENT SEARCHES' or @text='Recent Searches' and @resource-id='com.viacom18.vootkids:id/recent_title_text']/following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_clear_txt']")
	public WebElement recentSearchClearBtn;
	
	@FindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/search_recylerview']/android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
	public WebElement searchMovieCard;

	@FindBy(id = "com.viacom18.vootkids:id/search_icon")
	public WebElement Search;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_title_text' and @text='POPULAR SEARCH']/ancestor::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.FrameLayout[@resource-id='com.viacom18.vootkids:id/grid_list_container']//android.widget.FrameLayout[@resource-id='']//android.widget.ImageView")
	public WebElement popularSearchShow;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'VOICE') or contains(@text,'Voice') or contains(@text,'voice')]")
	public WebElement voiceButton;

	@FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	public WebElement allowVoicePermission;

	@FindBy(id = "com.viacom18.vootkids:id/btn_speech_cancel")
	public WebElement speechCancel;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'LISTENING') or contains(@text,'Listening') or contains(@text,'listening')]")
	public WebElement listeningText;

	@FindBy(id = "com.viacom18.vootkids:id/search_edittext")
	public WebElement searchTextBox;	
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement searchFirstItem;	

	@FindBy(id = "com.viacom18.vootkids:id/button_surprise_me")
	public WebElement surprisemeBtn;

	@FindBy(id = "com.viacom18.vootkids:id/clear_icon")
	public WebElement searchClear;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'RECENT SEARCHES')]")
	public WebElement RecentsearchContents;

	@FindBy(id = "com.viacom18.vootkids:id/recent_clear_txt")
	public WebElement RecentsearchClear;

	@FindBy(id = "com.viacom18.vootkids:id/empty_view")
	public WebElement searchEmpty;

	@FindBy(xpath = "//*[contains(@class,'ActionBar')][@index='0']//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/parent_layout']//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/tab_image']")
	public WebElement AllTab;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='0']//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/grid_title')]")
	public WebElement searchContent;
	
	////bhaskar//////////
	
	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index='0']")
	public WebElement card1inserachpage;
	

	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']/descendant::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_title']")
	public WebElement card1title;
	
	public boolean performSearchAndClickOnFirstSearchResult(String searchKey) {
		HomePageV2 homepagev2 = new HomePageV2(driver, test);
		if (Utilities.explicitWaitClickable(driver, homepagev2.search, 20)) {
			try {
				homepagev2.search.click();
				test.log(LogStatus.INFO, "Tapped on Search icon");
				if (Utilities.explicitWaitVisible(driver, searchTextBox, 20)) {
					try {
						searchTextBox.sendKeys(searchKey);
						test.log(LogStatus.INFO, "Entered key word in Search edit field");
						driver.navigate().back();
						if (Utilities.explicitWaitClickable(driver, searchFirstItem, 20)) {
							try {
								searchFirstItem.click();
								return true;
							} catch (Exception e) {
								test.log(LogStatus.FAIL, "Failed to click on first item from search results");
							}
						} else {
							test.log(LogStatus.FAIL, "First item in search results is not clickable");
						}
					} catch (Exception e) {
						test.log(LogStatus.FAIL, "Failed to send key word in Search edit field");
					}
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Failed to click on Search icon in My Stuff tab");
			}
		} else {
			test.log(LogStatus.FAIL, "Search icon in My Stuff tab is not clickable");
		}
		return false;
	}

	
	////bhaskar////////
	
	

}
