package com.viacom.pagesVersion2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class SettingsPageV2 extends BasePageV2 {
	static String newpwdgmail="";
	public SettingsPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(xpath = "//android.widget.ImageView[@text='NO' or @text='No' or @text='no' or @resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog']")
	public WebElement forgotPinNOBtn;

	@FindBy(xpath = "//android.widget.ImageView[@text='YES' or @text='Yes' or @text='yes' or @resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog']")
	public WebElement forgotPinYESBtn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog_text' or @text='NO' or @text='no' or @text='No']")
	public WebElement clearCachepopupNObtn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog_text' or @text='YES' or @text='yes' or @text='Yes']")
	public WebElement clearCachepopupYESbtn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog' and contains(@text,'Are you sure you want to clear cached data')]")
	public WebElement clearcacheBodyMsg;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Device' or @text='device' and @resource-id='com.viacom18.vootkids:id/item_head']/..")
	public WebElement settingsDevice;
	
	@FindBy(xpath = "//android.widget.TextView[@text='SET TIME LIMITS' or @text='Set Time Limits' or @text='Set Time limits' and @resource-id='com.viacom18.vootkids:id/title_daily_limit']")
	public WebElement parentZonePreferenceSetTimeLimits;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_back']")
	public WebElement bckBtnSwitchProfile;
	
	@FindBy(xpath="//android.widget.TextView[@text='HELP' or @text='Help' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement helpAndSupportPageTile;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/back_btn_settings']")
	public WebElement settingsHelpSupportBackbtn;
	
	@FindBy(xpath="//android.widget.TextView[@text='Contact us' or @text='Contact Us' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement contactUs;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"FAQ’s\" and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement faqTitle;
	
	@FindBy(xpath="//android.view.View[@text='Category of issue']")
	public WebElement categoryofIssue;
	
	@FindBy(xpath="//android.widget.Spinner[@text='Select issue' and @resource-id='topic']")
	public WebElement selectIssueDropDown;
	
	@FindBy(xpath="//android.widget.CheckedTextView[@resource-id='android:id/text1']")
	public WebElement categoryIssue;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='uName']")
	public WebElement editTextName;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='Body']")
	public WebElement actaulIssueEditTextbox;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='submit']")
	public WebElement sendBtncontactUs;
	
	@FindBy(xpath="//android.view.View[@text='We will reach out to you shortly via email']")
	public WebElement contactUsSccussMsg;
	
	@FindBy(xpath="//android.widget.Button[@resource-id='closebutton']")
	public WebElement contactUsSccussPopCloseBtn;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement emailAddedSuccessMsg;
	
	@FindBy(xpath="//android.widget.TextView[@text='CONTACT US' or @text='Contact Us' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement contectUsTitle;
	
	@FindBy(xpath="//android.view.View[@text='Name']")
	public WebElement nameContactUs;
	
	@FindBy(xpath="//android.view.View[@text='Email Address']")
	public WebElement emailAddress;
	
	@FindBy(xpath="//android.widget.EditText[@resource-id='uEmail']")
	public WebElement editAddressbox;
	
	@FindBy(xpath="//android.view.View[@text='Actual Issue']")
	public WebElement actualissueText;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_download_item")
	public WebElement downloadMovie;
	
	@FindBy(xpath="//android.widget.TextView[@text='Download Disabled!']")
	public WebElement downloadDisablePopUp;
	
	@FindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.viacom18.vootkids:id/positive_button_container']")
	public WebElement okBtn;
	
	@FindBy(xpath = "//android.widget.TextView[@text='RECENT ACTIVITY' or @text='Recent Activity']")
	public WebElement parentZoneRecentActivity1;
	
	@FindBy(xpath="//android.widget.TextView[@text='Usage Stats' or @text='USAGE STATS']")
	public WebElement usageStatsTrayTile;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Day' or @text='DAY' and @resource-id='com.viacom18.vootkids:id/tab_title']/../..")
	public WebElement parentZonePreferenceDayBtnClick;
	
	@FindBy(xpath="//android.widget.TextView[@text='WATCH' or @text='Watch' and @resource-id='com.viacom18.vootkids:id/usage_stat_item_title']/parent::android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_hour']")
	public WebElement watchStatsCount;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/textview_title']")
	public WebElement movieTile;
	
	@FindBy(xpath="//android.widget.ImageView[@text='NO' or @resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog' or @text='No' ]")
	public WebElement prodelPopNoBtn;

@FindBy(id="com.viacom18.vootkids:id/forgot_pin")
public WebElement forgotPinInParentzoneScreen;
	@FindBy(xpath="//android.widget.TextView[@text='High']")
	public WebElement deviceStremQulHigh;

	@FindBy(xpath="//android.widget.TextView[@text='Low']")
	public WebElement deviceStremQullow;
	
	@FindBy(id="com.viacom18.vootkids:id/edit_my_buddy")
	public WebElement editbuddy;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/skip_for_now' and @text='SKIP FOR NOW' or @text='Skip For Now' or @text='skip for now' or @text='Skip for now']")
	public WebElement rateUsPopSkipForNow;

	@FindBy(xpath = "//*[@content-desc='Google Play']")
	public WebElement rateUsBtnTapplayStore;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tray_title_textview' and @text='REWARDS' or @text='Rewards' or @text='rewards']")
	public WebElement rewardsTrayInParentsZone;

	@FindBy(xpath = "//*[@text='Default Stream Quality']//parent::android.view.ViewGroup//android.widget.Spinner[@resource-id='com.viacom18.vootkids:id/item_spinner']")
	public WebElement streamQualityDrpDown;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Terms & Conditions\"]//parent::*//*[@resource-id='com.viacom18.vootkids:id/arrow_indicator']")
	public WebElement helpTermsConditions;

	@FindBy(xpath = "//android.widget.TextView[@text='Privacy Policy']//parent::*//*[@resource-id='com.viacom18.vootkids:id/arrow_indicator']")
	public WebElement helpPrivacyPolicy;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tray_title_textview' and @text='REWARDS' or @text='Rewards' or @text='rewards']//parent::android.view.ViewGroup//android.view.ViewGroup")
	public WebElement stickerBelowRewardsInParentZone;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/grid_more_button_text' and @text='SEE ALL' or @text='See All' or @text='see all' or @text='See all']")
	public WebElement rewardsSeeAllButtonInParentsZone;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/button_got_it']")
	public WebElement rewardsScreenGotIt;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_title' and @text='REWARDS' or @text='Rewards' or @text='rewards']")
	public WebElement rewardsScreenTitle;

	@FindBy(xpath = "//android.widget.ImageView[@index='8']")
	public WebElement rewardsScreenSticker;

	 @FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/learn_stat_main_header']")
	 public WebElement learnStatsTrayName;
	 
	 @FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/learn_stat_main_header']//parent::*//android.view.ViewGroup[@index='0']//*[@resource-id='com.viacom18.vootkids:id/header_learn_item']")
	 public WebElement logicSmartBelowLearnStats;	 
	 
	 @FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/learn_stat_main_header']//parent::*//android.view.ViewGroup[@index='1']//*[@resource-id='com.viacom18.vootkids:id/header_learn_item']")
	 public WebElement discoverSmartBelowLearnStats;	 
	 
	 @FindBy(xpath = "//android.widget.TextView[@text='KNOWLEDGE' or @text='Knowledge' or @text='knowledge']//parent::*//following-sibling::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/header_learn_item']")
	 public WebElement liveSmartBelowDiscoverSmart;	
	 
	 @FindBy(xpath = "//android.widget.TextView[@text='LIFE' or @text='Life' or @text='life']//parent::*//following-sibling::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/header_learn_item']")
	 public WebElement artSmartBelowLiveSmart;	
	 
	 @FindBy(xpath = "//android.widget.TextView[@text='ART' or @text='Art' or @text='art']//parent::*//following-sibling::android.view.ViewGroup//*[@resource-id='com.viacom18.vootkids:id/header_learn_item']")
	 public WebElement speakSmartBelowArtSmart;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_title' and @text='Bed time' or @text='bed time' or @text='BED TIME' or @text='Bed Time']")
	public WebElement bedTime;

	@FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/layout_week_time']//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title']//parent::android.widget.LinearLayout")
	public List<WebElement> bedTimeDays;

	@FindBy(xpath = "//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_email_update_switch']")
	public WebElement emailUpdatetoggle;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Week']")
	public WebElement emailWeek;

	@FindBy(id = "com.viacom18.vootkids:id/btn_parent_zone")
	public WebElement parentZoneButton;

	@FindBy(id = "com.viacom18.vootkids:id/download_item_file_item_textview")
	public WebElement NoofDownloads;

	@FindBy(id = "com.viacom18.vootkids:id/download_item_file_size_textview")
	public WebElement downloadSize;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Activity') or contains(@text,'activity') or contains(@text,'ACTIVITY')]")
	public WebElement activityTab;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Preferences') or contains(@text,'preferences') or contains(@text,'PREFERENCES')]")
	public WebElement preferencesTab;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'PARENT ZONE') or contains(@text,'Parent Zone') or contains(@text,'parent zone')]")
	public WebElement parentZonePage;

	@FindBy(id = "com.viacom18.vootkids:id/button_manage_downloads")
	public WebElement manageDownloads;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'EDIT DOWNLOADS') or contains(@text,'Edit Downloads') or contains(@text,'edit downloads')]")
	public WebElement editDownloadsPage;

	@FindBy(id = "com.viacom18.vootkids:id/checkbox_delete_download_item")
	public WebElement deleteIcon;

	@FindBy(id = "com.viacom18.vootkids:id/title_email_update")
	public WebElement emailUpdateText;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Week') or contains(@text,'week') or contains(@text,'WEEK')]")
	public WebElement weektab;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Day') or contains(@text,'day') or contains(@text,'DAY')]")
	public WebElement dayTab;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Month') or contains(@text,'month') or contains(@text,'MONTH')]")
	public WebElement monthTab;

	@FindBy(xpath = "//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_email_update_switch' and @text='ON']")
	public WebElement emailtoggleon;

	@FindBy(xpath = "//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_email_update_switch' and @text='OFF']")
	public WebElement emailtoggleoff;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/learn_stat_main_header']")
	public WebElement learnStatsTray;

	@FindBy(xpath = "//android.widget.TextView[@text='Month' or @text='MONTH']")
	public WebElement parentZoneActivityUsageStatsMonth;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title' and @text='Day']")
	public WebElement dayButton;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title' and @text='Week']")
	public WebElement weekButton;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title' and @text='Month']")
	public WebElement monthButton;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_usage_stat_detail']")
	public WebElement usageWeek;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_title' and @text='WATCH' or @text='Watch']/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_hour']")
	public WebElement usageEpisodesCount;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_title' and @text='READ' or @text='Read']/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_hour']")
	public WebElement usagePagesCount;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_title' and @text='LISTEN' or @text='Listen']/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_hour']")
	public WebElement usageStoriesCount;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_title' and @text='LEARN' or @text='Learn']/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/usage_stat_item_hour']")
	public WebElement usageQuestionsCount;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/total_hour_title']")
	public WebElement usageTotalHours;

	@FindBy(xpath = "//android.view.ViewGroup[@index='0']/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_learn_item']")
	public WebElement learnStatsFirstBlock;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title_total_answer_correct']")
	public List<WebElement> learnStatsCorrect;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_cancel']")
	public WebElement switchProfileCancel;

	@FindBy(xpath = "//android.widget.TextView[@text='RECENT ACTIVITY' or @text='Recent Activity' or @text='recent activity']")
	public WebElement parentZoneRecentActivity;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_activity_item_title']")
	public List<WebElement> parentZoneRecentActivityItemNames;

	@FindBy(xpath = "//android.widget.ListView//android.view.ViewGroup//android.widget.TextView[@text='Kannada']")
	public WebElement devicePrefrredLanuageKannada;

	// Saravanan - Added on 1st JAN
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_activity_item_time_textview']")
	public WebElement lastUpdatedTime_In_parentZoneRecentActivity;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/recent_activity_item_time_textview']")
	public List<WebElement> lastUpdatedTime_In_parentZoneRecentActivity_As_List;

	@FindBy(xpath = "//android.widget.TextView[@text='EMAIL UPDATE']")
	public WebElement emailUpdateInParentZone;

	// Learn stats
	@FindBy(xpath = "//android.widget.TextView[@text='LEARN STATS']")
	public WebElement learnStats;

	@FindBy(xpath = "//android.widget.TextView[@text='LOGIC SMART']")
	public WebElement logicSmart;

	@FindBy(xpath = "//android.widget.TextView[@text='DISCOVER SMART']")
	public WebElement discoverSmart;

	@FindBy(xpath = "//android.widget.TextView[@text='LIVE SMART']")
	public WebElement liveSmart;

	@FindBy(xpath = "//android.widget.TextView[@text='ART SMART']")
	public WebElement artSmart;

	@FindBy(xpath = "//android.widget.TextView[@text='SPEAK SMART']")
	public WebElement speakSmart;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/learn_item_trophy_view']")
	public WebElement blankTrophy;

	@FindBy(xpath = "//android.widget.LinearLayout[@index='3']")
	public WebElement unlockedTrophy;

	@FindBy(id = "com.viacom18.vootkids:id/title_total_answer")
	public WebElement totalQuestionAttempt;

	@FindBy(id = "com.viacom18.vootkids:id/trophy_level_view")
	public WebElement trophyLevelName;

	@FindBy(id = "com.viacom18.vootkids:id/sub_header_learn_item")
	public WebElement earnedSticker;

	@FindBy(id = "com.viacom18.vootkids:id/tab_title")
	public WebElement preferenceTab;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='android:id/hours']")
	public WebElement hoursInCalendar;

	@FindBy(xpath = "//android.widget.TextView[@text='M']")
	public List<WebElement> weekDays;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tray_tab']")
	public WebElement horizantaltrayb;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title' and @index='0' and @text='Week']")
	public WebElement weekbuttonb2;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title' and @index='0' and @text='Day']")
	public WebElement daybuttonb2;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title' and @index='0' and @text='Month']")
	public WebElement monthbuttonb2;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Bed time' or @text='BED TIME' or @text='Bed Time']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch' and @text='ON' or @text='On' or @text='on']")
	public WebElement parentZonePreferenceBedTimeSwitchON;

	@FindBy(xpath = "//android.widget.TextView[@text='Bed time' or @text='BED TIME' or @text='Bed Time']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch' and @text='OFF' or @text='Off' or @text='off']")
	public WebElement parentZonePreferenceBedTimeSwitchOFF;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_sleep']//following-sibling::*[@resource-id='com.viacom18.vootkids:id/text_sleep_time']")
	public WebElement sleepTime;	
	
	@FindBy(xpath = "//*[@resource-id='android:id/ampm_layout']//*[@resource-id='android:id/pm_label']")
	public WebElement clockPM;
	
	@FindBy(xpath = "//*[@resource-id='android:id/ampm_layout']//*[@resource-id='android:id/am_label']")
	public WebElement clockAM;
	
	@FindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	public WebElement clockOK;
	
	
	

	public void putBackGroundApp() {
		driver.runAppInBackground(Duration.ofSeconds(5));
		test.log(LogStatus.INFO, "Put app to background for 5 seconds");
		driver.currentActivity();
	}

	// Enable Download Switch to Make ON state

	HomePageV2 homepagev2 = new HomePageV2(driver, test);

	// keep Cellular Playback OFF state

	public void cellularPlayDownloadSwichOFF() throws Exception {

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 80)) {

			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Clicked on Profile Pic Icon in home page");

		} else
			BasePageV2.reportFail("Not able to click Profile Pic Icon in Home page / not found ");

		if (Utilities.explicitWaitClickable(driver, parentZoneBtn, 50)) {
			parentZoneBtn.click();
			test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");

			if (Utilities.explicitWaitVisible(driver, ParentZoneTile, 50)) {
				test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
				try {
					parentPinContainer.sendKeys("1111");
					Thread.sleep(30000);
					test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
				} catch (Exception e) {
					test.log(LogStatus.FAIL,
							"Not able to entered PIn '1111' in parent zone page / not found pin container");
				}

			} else {
				BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
			}

		} else
			BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");

		driver.runAppInBackground(Duration.ofSeconds(3));
		test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		driver.currentActivity();

		if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
			settingsIcon.click();
		} else
			BasePageV2.reportFail("Unable to click 'Settings Icon' / Not Found to navigate to Parent Zone page ");

		if (Utilities.explicitWaitVisible(driver, settingsDevice, 10)) {
			settingsDevice.click();
		} else
			BasePageV2.reportFail("Not able click 'Device' link in Settings Page / Not found");

		if (Utilities.explicitWaitClickable(driver, deviceCellplySwitch, 5)) {
			test.log(LogStatus.INFO, "Found Cellular playback Switch In Device screen");
		} else
			test.log(LogStatus.FAIL, "Not found Cellular Playback Switch");

		if (deviceCellplySwitch.getAttribute("checked").equals("false")) {
			test.log(LogStatus.INFO, "Cellular Playback switch is in 'OFF' state");

		} else if (deviceCellplySwitch.getAttribute("checked").equals("true")) {
			test.log(LogStatus.INFO, "Cellular Playback switch is in 'ON' state");
			try {
				deviceCellplySwitch.click();
				test.log(LogStatus.INFO, "Clicked Cellular Playback switch for 'OFF' state in Device Screen");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to click Cellular playback switch for 'OFF' state in Device Screen");
			}

		}

		String end = "//android.widget.TextView[@text='Cellular Downloads']";
		Utilities.verticalSwipe(driver, end);

		if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("false")) {
			test.log(LogStatus.INFO, "Cellular Download switch is in 'OFF' state");

		} else if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("true")) {
			test.log(LogStatus.INFO, "Cellular Download switch is in 'ON' state");
			try {
				deviceCelluallerDownlodsSwitch.click();
				test.log(LogStatus.INFO, "Clicked Cellular Download switch for 'OFF' state in Device Screen");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to click Cellular Download switch for 'OFF' state in Device Screen");
			}

		}

	} // End CellularplaybackSwicthOFF

	// Cellular playback ON state

	public void cellularPlayDownloadSwichON() throws Exception {

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 80)) {

			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Clicked on Profile Pic Icon in home page");

		} else
			BasePageV2.reportFail("Not able to click Profile Pic Icon in Home page / not found ");

		if (Utilities.explicitWaitClickable(driver, parentZoneBtn, 50)) {
			parentZoneBtn.click();
			test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");

			if (Utilities.explicitWaitVisible(driver, ParentZoneTile, 50)) {
				test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
				try {
					parentPinContainer.sendKeys("1111");
					Thread.sleep(30000);
					test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
				} catch (Exception e) {
					test.log(LogStatus.FAIL,
							"Not able to entered PIn '1111' in parent zone page / not found pin container");
				}

			} else {
				BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
			}

		} else
			BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");

		driver.runAppInBackground(Duration.ofSeconds(3));
		test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		driver.currentActivity();

		if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
			settingsIcon.click();
		} else
			BasePageV2.reportFail("Unable to click 'Settings Icon' / Not Found to navigate to Parent Zone page ");

		if (Utilities.explicitWaitVisible(driver, settingsDevice, 10)) {
			settingsDevice.click();
		} else
			BasePageV2.reportFail("Not able click 'Device' link in Settings Page / Not found");

		if (Utilities.explicitWaitClickable(driver, deviceCellplySwitch, 5)) {
			test.log(LogStatus.INFO, "Found Cellular playback Switch In Device screen");
		} else
			test.log(LogStatus.FAIL, "Not found Cellular Playback Switch");

		if (deviceCellplySwitch.getAttribute("checked").equals("false")) {
			test.log(LogStatus.INFO, "Cellular Playback switch is in 'OFF' state");
			try {
				deviceCellplySwitch.click();
				test.log(LogStatus.INFO, "Clicked Cellular Playback switch for 'ON' state in Device Screen");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to click Cellular playback switch for 'ON' state in Device Screen");
			}

		} else if (deviceCellplySwitch.getAttribute("checked").equals("true")) {
			test.log(LogStatus.INFO, "Cellular Playback switch is in 'ON' state");

		}

		String end = "//android.widget.TextView[@text='Cellular Downloads']";
		Utilities.verticalSwipe(driver, end);

		if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("false")) {
			test.log(LogStatus.INFO, "Cellular Playback switch is in 'OFF' state");
			try {
				deviceCelluallerDownlodsSwitch.click();
				test.log(LogStatus.INFO, "Clicked Cellular Playback switch for 'ON' state in Device Screen");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to click Cellular playback switch for 'ON' state in Device Screen");
			}

		} else if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("true")) {
			test.log(LogStatus.INFO, "Cellular Playback switch is in 'ON' state");

		}

	}// End Cellular playback ON state

	// Cellular Downloads

	public void cellularDownloadsSwichON() throws Exception {

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 80)) {

			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Clicked on Profile Pic Icon in home page");

		} else
			BasePageV2.reportFail("Not able to click Profile Pic Icon in Home page / not found ");

		if (Utilities.explicitWaitClickable(driver, parentZoneBtn, 50)) {
			parentZoneBtn.click();
			test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");

			if (Utilities.explicitWaitVisible(driver, ParentZoneTile, 50)) {
				test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
				try {
					parentPinContainer.sendKeys("1111");
					Thread.sleep(30000);
					test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
				} catch (Exception e) {
					test.log(LogStatus.FAIL,
							"Not able to entered PIn '1111' in parent zone page / not found pin container");
				}

			} else {
				BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
			}

		} else
			BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");

		driver.runAppInBackground(Duration.ofSeconds(3));
		test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		driver.currentActivity();

		if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
			settingsIcon.click();
		} else
			BasePageV2.reportFail("Unable to click 'Settings Icon' / Not Found to navigate to Parent Zone page ");

		if (Utilities.explicitWaitVisible(driver, settingsDevice, 10)) {
			settingsDevice.click();
		} else
			BasePageV2.reportFail("Not able click 'Device' link in Settings Page / Not found");

		// scroll to Cellular Downloads
		// String end = "//android.widget.TextView[@text='Cellular Downloads']";
		// Utilities.verticalSwipe(driver, end);

		Utilities.verticalSwipe(driver, deviceCellularDownloadsWithToggleButton);

		if (Utilities.explicitWaitClickable(driver, deviceCelluallerDownlodsSwitch, 5)) {
			test.log(LogStatus.INFO, "Found Cellular Downloads Switch In Device screen");
		} else
			test.log(LogStatus.FAIL, "Not found Cellular Downloads Switch in Device Screen");

		if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("false")) {
			test.log(LogStatus.INFO, "Cellular Downloads switch is in 'OFF' state");
			try {
				deviceCelluallerDownlodsSwitch.click();
				test.log(LogStatus.INFO, "Clicked Cellular Downloads switch for 'ON' state in Device Screen");
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Not able to click Cellular Downloads switch for 'ON' state in Device Screen");
			}

		} else if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("true")) {
			test.log(LogStatus.INFO, "Cellular Downloads switch is in 'ON' state");

		}
	}// End Cellular Downloads Switch ON

	// cellular Downloads OFF sate

	// Cellular Downloads

	public void cellularDownloadsSwichOFF() throws Exception {

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 80)) {

			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Clicked on Profile Pic Icon in home page");

		} else
			BasePageV2.reportFail("Not able to click Profile Pic Icon in Home page / not found ");

		if (Utilities.explicitWaitClickable(driver, parentZoneBtn, 50)) {
			parentZoneBtn.click();
			test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");

			if (Utilities.explicitWaitVisible(driver, ParentZoneTile, 50)) {
				test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
				try {
					parentPinContainer.sendKeys("1111");
					Thread.sleep(30000);
					test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
				} catch (Exception e) {
					test.log(LogStatus.FAIL,
							"Not able to entered PIn '1111' in parent zone page / not found pin container");
				}

			} else {
				BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
			}

		} else
			BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");

		driver.runAppInBackground(Duration.ofSeconds(3));
		test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		driver.currentActivity();

		if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
			settingsIcon.click();
		} else
			BasePageV2.reportFail("Unable to click 'Settings Icon' / Not Found to navigate to Parent Zone page ");

		if (Utilities.explicitWaitVisible(driver, settingsDevice, 10)) {
			settingsDevice.click();
		} else
			BasePageV2.reportFail("Not able click 'Device' link in Settings Page / Not found");

		// scroll to Cellular Downloads
		String end = "//android.widget.TextView[@text='Cellular Downloads']";
		Utilities.verticalSwipe(driver, end);

		// Utilities.verticalSwipe(driver, deviceCelluallerDownlods);

		if (Utilities.explicitWaitClickable(driver, deviceCelluallerDownlodsSwitch, 5)) {
			test.log(LogStatus.INFO, "Found Cellular Downloads Switch In Device screen");
		} else
			test.log(LogStatus.FAIL, "Not found Cellular Downloads Switch in Device Screen");

		if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("false")) {
			test.log(LogStatus.INFO, "Cellular Downloads switch is in 'OFF' state");

		} else if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("true")) {
			test.log(LogStatus.INFO, "Cellular Downloads switch is in 'ON' state");
			try {
				deviceCelluallerDownlodsSwitch.click();
				test.log(LogStatus.INFO, "Clicked Cellular Downloads switch for 'OFF' state in Device Screen");
			} catch (Exception e) {
				test.log(LogStatus.FAIL,
						"Not able to click Cellular Downloads switch for 'OFF' state in Device Screen");
			}

		}
	}// End Cellular Downloads Switch OFF

	public void enableDownloadsSwichON() throws Exception {

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 80)) {

			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Succusfully entered to Switch profile page");

		} else
			BasePageV2.reportFail("Profile Icon Not Fould to navigate to Switch profile ");

		if (Utilities.explicitWaitClickable(driver, parentZoneBtn, 50)) {
			parentZoneBtn.click();
			test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");

			if (Utilities.explicitWaitVisible(driver, ParentZoneTile, 50)) {
				test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
				try {
					parentPinContainer.sendKeys("1111");
					Thread.sleep(30000);
					test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
				} catch (Exception e) {
					test.log(LogStatus.FAIL,
							"Not able to entered PIn '1111' in parent zone page / not found pin container");
				}

			} else {
				BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
			}

		} else
			BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");

		driver.runAppInBackground(Duration.ofSeconds(3));
		test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		driver.currentActivity();

		if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
			settingsIcon.click();
		} else
			BasePageV2.reportFail("Unable to click 'Settings Icon' / Not Found to navigate to Parent Zone page ");

		if (Utilities.explicitWaitVisible(driver, settingsDevice, 10)) {
			settingsDevice.click();
		} else
			BasePageV2.reportFail("Not able click 'Device' link in Settings Page / Not found");

		String end = "//android.widget.TextView[@text='Enable Downloads']";
		try {
			Utilities.verticalSwipe(driver, end);
			test.log(LogStatus.INFO, "Swiped till 'Enable Downloads'");
		} catch (Exception e) {
			BasePageV2.reportFail("Unable to swiped till 'Enable Downloads' in Device page");
		}

		try {
			if (deviceEnableddownloadsSwitch.getAttribute("checked").equalsIgnoreCase("true")) {
				test.log(LogStatus.INFO, "'Enable Download' Swicth is ON state");
			} else {
				deviceEnableddownloadsSwitch.click();
				test.log(LogStatus.INFO, "Succussfully clicked 'Enable Download' Switch to turned it to ON state");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			BasePageV2.reportFail("Unable to Found 'Enable Download' Swicth / not able to click");
		}

		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);

	}

	// Enable Download Switch to Make OFF state

	public void enableDownloadsSwichOFF() throws Exception {

		if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 80)) {

			homepagev2.profilepic.click(); // tap on profile icon
			test.log(LogStatus.INFO, "Succusfully entered to Switch profile page");

		} else
			BasePageV2.reportFail("Profile Icon Not Fould to navigate to Switch profile ");

		if (Utilities.explicitWaitClickable(driver, parentZoneBtn, 50)) {
			parentZoneBtn.click();
			test.log(LogStatus.INFO, "clicked parent Zone Button in Switch Profile page");

			if (Utilities.explicitWaitVisible(driver, ParentZoneTile, 50)) {
				test.log(LogStatus.INFO, "Navigated to PARENT ZONE page");
				try {
					parentPinContainer.sendKeys("1111");
					Thread.sleep(30000);
					test.log(LogStatus.INFO, "Entered Pin '1111' in Four white Blocks of Parent Zone page ");
				} catch (Exception e) {
					test.log(LogStatus.FAIL,
							"Not able to entered PIn '1111' in parent zone page / not found pin container");
				}

			} else {
				BasePageV2.reportFail("Not able to navigated to PARENT ZONE page");
			}

		} else
			BasePageV2.reportFail("Not able to click PARENT ZONE button / not found");

		driver.runAppInBackground(Duration.ofSeconds(3));
		test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		driver.currentActivity();

		if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
			settingsIcon.click();
		} else
			BasePageV2.reportFail("Unable to click 'Settings Icon' / Not Found to navigate to Parent Zone page ");

		if (Utilities.explicitWaitVisible(driver, settingsDevice, 10)) {
			settingsDevice.click();
		} else
			BasePageV2.reportFail("Not able click 'Device' link in Settings Page / Not found");

		String end = "//android.widget.TextView[@text='Enable Downloads']";
		try {
			Utilities.verticalSwipe(driver, end);
			test.log(LogStatus.INFO, "Swiped till 'Enable Downloads'");
		} catch (Exception e) {
			BasePageV2.reportFail("Unable to swiped till 'Enable Downloads' in Device page");
		}

		try {
			if (deviceEnableddownloadsSwitch.getAttribute("checked").equalsIgnoreCase("false")) {
				test.log(LogStatus.INFO, "'Enable Download' Swicth is in OFF state");
			} else {
				deviceEnableddownloadsSwitch.click();
				test.log(LogStatus.INFO, "Succussfully clicked 'Enable Download' Switch to turned it to OFF state");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			BasePageV2.reportFail("Unable to Found 'Enable Download' Swicth / not able to click");
		}

		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.pressKeyCode(AndroidKeyCode.BACK);

	}

	/////////////// Adding Setting Elements Properties //////////////

	@FindBy(id = "com.viacom18.vootkids:id/btn_save_password")
	public WebElement saveButtonResetPassword;

	

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog' or @text='If you have forgotten your PIN, we can send you an email or sms to reset your PIN. Request reset PIN email or reset PIN sms?']")
	public WebElement forgotPinMsgBody;

	@FindBy(id = "com.viacom18.vootkids:id/app_version")
	public WebElement appVersion;

	@FindBy(xpath = "//android.widget.TextView[@text='RECENT ACTIVITY' and @resource-id='com.viacom18.vootkids:id/tray_title_textview']")
	public WebElement RecentActivityTitle;


	@FindBy(id="com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement pinResetSuccessMsgPopupCloseButton;


	@FindBy(xpath = "//android.widget.TextView[@text=\"CELLULAR PLAYBACK OFF!\" or @text=\"Cellular Playback Off!\" or @resource-id='com.viacom18.vootkids:id/header_dialog']")
	public WebElement cellularplybackOFFMsg;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Cellular playback is switched off. Go to settings to edit preferences\" or @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement cellularplybackOFFSUBMsg;

	@FindBy(xpath = "//android.widget.Button[@text='OK' or @text='ok' or @text='Ok' or @resource-id='com.viacom18.vootkids:id/positive_btn_dialog']")
	public WebElement cellularplybackOFFMsgOkBtn;

	@FindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Voot')]")
	public WebElement vootTW;

	@FindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Voot kids Support')]")
	public WebElement vootKidsForPaWD;

	@FindBy(xpath = "//android.view.View[@text='Home']")
	public WebElement homeVootapp;

	@FindBy(xpath = "//android.widget.EditText[@text=\"ifocus@123\"]")
	public WebElement ifocusTextForResetPawd; // Here ifocus@123 hardcoded For
												// TC-808 because testing the
												// Reset Password

	@FindBy(xpath = "//android.widget.EditText[@text=\"••••••••••\"]")
	public WebElement dotText; // Here ifocus@123 hardcoded For TC-808 because
								// testing the Reset Password

	@FindBy(id = "com.viacom18.vootkids:id/btn_settings_menu")
	public WebElement settingsIcon;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_title' and @text='SETTINGS' or @text='Settings' or @text='settings']")
	public WebElement settingsPageTitle;

	@FindBy(xpath = "//android.widget.TextView[@text='SWITCH PROFILE' or @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement switchProfile;

	@FindBy(xpath = "//android.widget.Button[@text='Parent Zone' or @text='parent zone' or @resource-id='com.viacom18.vootkids:id/btn_parent_zone']")
	public WebElement parentZoneBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='PARENT ZONE' or @text='Parent Zone' or @text='parent zone' or @resource-id='com.viacom18.vootkids:id/header_text']")
	public WebElement ParentZoneTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Preferences' and @resource-id='com.viacom18.vootkids:id/tab_title' or @text='PREFERENCES']")
	public WebElement parentZonePreferenceTab;


	@FindBy(xpath = "//android.widget.TextView[@text='Daily Usage' and @resource-id='com.viacom18.vootkids:id/item_title' or @text='DAILY USAGE']")
	public WebElement parentZonePreferenceDailyUsage;

	@FindBy(xpath = "//android.widget.TextView[@text='Bed time' and @resource-id='com.viacom18.vootkids:id/item_title' or @text='BED TIME']")
	public WebElement parentZonePreferenceBedTime;

	@FindBy(xpath = "//android.widget.TextView[@text='Daily Usage']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement parentZonePreferenceDailyUsageSwitch;

	@FindBy(xpath = "//android.widget.SeekBar[@resource-id='com.viacom18.vootkids:id/seek_bar']")
	public WebElement ParentZonePreferenceSeekbar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Bed time' or @text='BED TIME' or @text='Bed Time']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement parentZonePreferenceBedTimeSwitch;

	@FindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.viacom18.vootkids:id/tray_tab']")
	public WebElement parentZonePreferenceBedTimeWeekDays;

	@FindBy(id = "com.viacom18.vootkids:id/text_wake_up")
	public WebElement parentZonePreferenceBedTimeWakeUp;

	@FindBy(id = "com.viacom18.vootkids:id/text_wake_up_time")
	public WebElement parentZonePreferenceBedTimeWakeUpTime;

	@FindBy(id = "com.viacom18.vootkids:id/text_sleep_time")
	public WebElement parentZonePreferenceBedTimeSleepTime;

	@FindBy(id = "com.viacom18.vootkids:id/text_sleep")
	public WebElement parentZonePreferenceBedTimeSleep;

	@FindBy(xpath = "//android.widget.TextView[@text='DOWNLOADS' and @resource-id='com.viacom18.vootkids:id/title_daily_limit' or @text='Downloads']")
	public WebElement parentZonePreferenceDownloads;

	@FindBy(id = "com.viacom18.vootkids:id/button_manage_downloads")
	public WebElement parentZonePreferenceManageBtn;

	@FindBy(id = "com.viacom18.vootkids:id/title_email_update")
	public WebElement parentZonePreferenceEmailupdate;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/title_email_update']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_email_update_switch']")
	public WebElement parentZonePreferenceEmailUpdateSwitch;

	@FindBy(xpath = "//android.widget.TextView[@text='Day' and @resource-id='com.viacom18.vootkids:id/tab_title' or @text='DAY']")
	public WebElement parentZonePreferenceDayBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Week' and @resource-id='com.viacom18.vootkids:id/tab_title' or @text='WEEK']")
	public WebElement parentZonePreferenceWeek;

	@FindBy(xpath = "//android.widget.TextView[@text='Month' and @resource-id='com.viacom18.vootkids:id/tab_title' or @text='MONTH']")
	public WebElement parentZonePreferenceMonth;

	@FindBy(xpath = "//android.widget.TextView[@text='Activity' and @resource-id='com.viacom18.vootkids:id/tab_title' or @text='ACTIVITY']")
	public WebElement parentZoneActivityTab;

	@FindBy(xpath = "//android.widget.TextView[@text='PARENT ZONE' or @text='Parent Zone' or @text='parent zone' or @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement ParentZonePageTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Enter PIN to access']")
	public WebElement enterPinToacess;

	@FindBy(xpath = "//android.widget.TextView[@text='Enter PIN to access' or @resource-id='com.viacom18.vootkids:id/header_subtitle']")
	public WebElement parentZoneEnterPinToacess;

	@FindBy(xpath = "//android.widget.TextView[@text='com.viacom18.vootkids:id/btn_back']")
	public WebElement submitbtnParentZone;

	@FindBy(id = "com.viacom18.vootkids:id/forgot_pin")
	public WebElement forGotPinText;

	@FindBy(id = "com.viacom18.vootkids:id/body_dialog")
	public WebElement forgotpinPopUpBdyText;

	@FindBy(xpath = "//android.widget.TextView[@text='FORGOT PIN' or @resource-id='com.viacom18.vootkids:id/header_dialog']")
	public WebElement forgotPinPopUpTitle;

	@FindBy(id = "com.viacom18.vootkids:id/body_dialog")
	public WebElement forGotpinPopUpBodyText;

	@FindBy(xpath = "//android.widget.TextView[@text=\"You have entered a wrong PIN. Do you want to reset your PIN?\" or @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement forgotPinResetPinText;

	@FindBy(xpath = "//android.widget.TextView[@text=\"If you have forgotten your PIN, we can send you an email to reset your PIN. Request reset PIN email?\" or @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement fogottenBodyText;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_back']")
	public WebElement bckBtnParentZone;

	@FindBy(id = "com.viacom18.vootkids:id/input_pinView") // Parent Pin
															// container
	public WebElement parentPinContainer;

	@FindBy(id = "com.viacom18.vootkids:id/container_pin") // Parent Pin
															// container
	public WebElement reSetPinContainer;

	@FindBy(xpath = "//android.widget.TextView[@text='NEXT' and @resource-id='com.viacom18.vootkids:id/settings_log_btn' or @text='Next']")
	public WebElement resetPinNextBtn;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/back_btn_settings']")
	public WebElement ResetParentResetPageBackBtn;

	@FindBy(id = "com.viacom18.vootkids:id/forgot_pin_link")
	public WebElement ResetPinforgotPin;

	@FindBy(id = "com.viacom18.vootkids:id/pin_digit_1")
	public WebElement ResetPinDigit1;

	@FindBy(id = "com.viacom18.vootkids:id/pin_digit_2")
	public WebElement ResetPinDigit2;

	@FindBy(id = "com.viacom18.vootkids:id/pin_digit_3")
	public WebElement ResetPinDigit3;

	@FindBy(id = "com.viacom18.vootkids:id/pin_digit_4")
	public WebElement ResetPinDigit4;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_title' and @text='RESET PIN']")
	public WebElement ResetParentResetTitle;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/settings_log_btn' and @text='NEXT']")
	public WebElement ResetParentResetNextBtn;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/reset_pin_subTitle' and @text='Enter current PIN']")
	public WebElement ResetParentSubTitle;

	@FindBy(xpath = "//android.widget.TextView[@text=\"PIN entered does not match. Please try again.\"]")
	public WebElement ResetParentWrongPin;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/pin_digit_1']")
	public WebElement resetParentPinContainer;

	@FindBy(xpath = "//android.widget.TextView[@text='SETTINGS' or @text='Settings']") // Setting
																						// Page
																						// after
																						// entering
																						// the
																						// Pin
	public WebElement settingTextinParantZone;

	@FindBy(xpath = "//android.widget.TextView[@text='Account' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement settingTextinParantZoneAccount;

	@FindBy(xpath = "//android.widget.TextView[@text='Profiles']")
	public WebElement settingTextinParantZoneProfiles;

	@FindBy(xpath = "//android.widget.TextView[@text='Device']")
	public WebElement settingTextinParantZoneDevice;

	@FindBy(xpath = "//android.widget.TextView[@text='Share']")
	public WebElement settingTextinParantZoneShare;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Help & Support\"]")
	public WebElement settingTextinParantZoneHelpSupport;

	@FindBy(xpath = "//android.widget.TextView[@text='Rate Us']")
	public WebElement settingTextinParantZoneRateUs;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/back_btn_settings']")
	public WebElement settingTextinParantZonebackbtn;

	@FindBy(id = "com.viacom18.vootkids:id/settings_log_btn")
	public WebElement settingsLogOut;

	@FindBy(id = "com.viacom18.vootkids:id/positive_right_btn_dialog")
	public WebElement settingsLogOutYesBtn;

	@FindBy(xpath = "//android.widget.Button[@text='JOIN FREE FOR 30 DAYS' or @resource-id='com.viacom18.vootkids:id/btn_sign_up_into_screen']")
	public WebElement settingsLogOutWelcomeBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='ACCOUNT' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement settingsAccount;

	@FindBy(xpath = "//android.widget.TextView[@text='Email' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement accountEmail;

	@FindBy(xpath = "//android.widget.TextView[@text='Mobile'  and  @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement accountMobile;

	@FindBy(xpath = "//android.widget.TextView[@text='Reset Password' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement accountResetPawd;

	@FindBy(xpath = "//android.widget.TextView[@text='Reset Parent Zone Pin' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement accountResetparZonePin;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid Pin.\" and @resource-id='com.viacom18.vootkids:id/error_message_pin']")
	public WebElement ResetparZonePinContainerErorMSg;

	@FindBy(xpath = "//android.widget.TextView[@text='Enter new PIN' and @resource-id='com.viacom18.vootkids:id/reset_pin_subTitle']")
	public WebElement newPinResetParent;

	@FindBy(xpath = "//android.widget.TextView[@text='Confirm New PIN' and @resource-id='com.viacom18.vootkids:id/reset_pin_subTitle']")
	public WebElement confirmNewPinResetParent;

	@FindBy(xpath = "//android.widget.TextView[@text='SAVE' and @resource-id='com.viacom18.vootkids:id/settings_log_btn' or @text='Save']")
	public WebElement confimNewPinResetSaveBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='PIN RESET' and @resource-id='com.viacom18.vootkids:id/header_dialog']")
	public WebElement pinResetSuccessMsg;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_close_dialog']")
	public WebElement pinResetSuccessCancelBtn;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Your PIN has been reset.\" and @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement pinResetSuccessBodyMsg;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement accountBackBtn;

	// Passing the Email ID hard coded here
	@FindBy(xpath = "//android.widget.TextView[@text='Email']/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_desc']")
	public WebElement accountEmailId;

	// Passing the Mobile number hard coded here
	@FindBy(xpath = "//android.widget.TextView[@text='Mobile']/parent::android.view.ViewGroup/android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_desc']")
	public WebElement accountMobileNum;

	@FindBy(xpath = "//android.widget.TextView[@text ='UPDATE EMAIL']")
	public WebElement addEmail;

	// ACCOUNT 'Email page'
	@FindBy(id = "com.viacom18.vootkids:id/back_btn_settings")
	public WebElement addMailBackbtn;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/edit_text_email_id']")
	public WebElement addMailEditText;

	@FindBy(xpath = "//android.widget.TextView[@text='Email']")
	public WebElement addMailuserTile;

	@FindBy(xpath = "//android.widget.TextView[@text='SAVE' and @resource-id='com.viacom18.vootkids:id/settings_log_btn' or @text='Save']")
	public WebElement addMailSaveBtn;

	// Pop up Email change success massage elements parameters

	@FindBy(xpath = "//android.widget.TextView[@text='Save' or @text='SAVE']")
	public WebElement updateEmailSaveBtn1;

	@FindBy(id = "com.viacom18.vootkids:id/header_dialog")
	public WebElement addMailSucssEmailAdd;

	@FindBy(id = "com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement addMailSucssEmailAddCnlBtn;

	@FindBy(xpath = "//android.widget.TextView[@text=\"This Email ID is not valid. Please choose another one.\"]")
	public WebElement addMailWrongMsg;

	// Setting Page Account "Mobile Number" Screen

	@FindBy(xpath = "//android.widget.TextView[@text='UPDATE MOBILE NUMBER'  and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement addMobileNumTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Save' and @resource-id='com.viacom18.vootkids:id/settings_log_btn']")
	public WebElement addMobileNumSaveBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='SAVE' and @resource-id='com.viacom18.vootkids:id/settings_log_btn']")
	public WebElement addMobileNumSaveBtn1;

	@FindBy(xpath = "//android.widget.TextView[@text=\"The Mobile is already registered with us.\"]")
	public WebElement addMobileNumInvalisErrorMsg;

	@FindBy(id = "com.viacom18.vootkids:id/edit_text_phone_number")
	public WebElement addMobileNumEditText;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid phone number.\" and @resource-id = 'com.viacom18.vootkids:id/error_message_phone_number']")
	public WebElement addMobileNumEditEmptyErrorMsg;

	@FindBy(xpath = "//android.widget.ImageView[@text='MOBILE NUMBER ADDED'] ")
	public WebElement addMobileNumSuccesmsg;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Old mobile number & new mobile number should not be the same.\" and @resource-id='com.viacom18.vootkids:id/error_message_phone_number']")
	public WebElement errorMsgRegistedMobilenum;

	@FindBy(id = "com.viacom18.vootkids:id/button_cancel")
	public WebElement addMobileNumSuccesCrossBtn;

	@FindBy(xpath = "//android.widget.ImageView[@text='Your mobile number has been updated successfully'] ")
	public WebElement addMobileNumSuccesSubmsg;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement addMobileNumBackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='com.viacom18.vootkids:id/text_country_code']")
	public WebElement addMobileNumCountryCode;

	@FindBy(xpath = "//android.widget.TextView[@text='IN +91']")
	public WebElement addMobileNumCuntryCodeText;

	@FindBy(xpath = "//android.widget.TextView[@text='Enter Code Manually' and @resource-id='com.viacom18.vootkids:id/btn_code_manually']")
	public WebElement enterCodeManuallyMobile;

	@FindBy(xpath = "//android.widget.Button[@text='AUTODETECT CODE' or @resource-id='com.viacom18.vootkids:id/btn_switch_to_auto' or @text='Autodetect Code']")
	public WebElement autodetectCodeBtn;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement enterManuallyPageBackBtn;

	@FindBy(id = "com.viacom18.vootkids:id/container_otp_parent")
	public WebElement pinContainerEnterCodeManually;

	// @FindBy(id="com.viacom18.vootkids:id/header_subtitle")
	// public WebElement subTextEnterManuallyPage;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Please enter the OTP sent to') and @resource-id='com.viacom18.vootkids:id/header_subtitle']")
	public WebElement subTextEnterManuallyPage;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Not Received your OTP?\"]")
	public WebElement notReciveOTPEnterManuallyPage;

	// @FindBy(xpath="//android.widget.TextView[contains(@text,'Not Received
	// your OTP')]")
	// public WebElement notReciveOTPEnterManuallyPage;

	@FindBy(xpath = "//android.widget.TextView[@text='Resend OTP' or @text='RESEND otp' ]")
	public WebElement ReSendOTPEnterManuallyPage;

	// Settings Profiles Screen verify
	@FindBy(xpath = "//android.widget.TextView[@text='Profiles']")
	public WebElement settingsProfile;

	@FindBy(xpath = "//android.widget.TextView[@text='PROFILES' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement settingsProfileTile;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement settingsProfileBackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Create New Profile']")
	public WebElement settingsProfileCreateNewPro;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/profile_image']")
	public WebElement settingsProfileCreateImg;

	// Edit Profile Page validating

	@FindBy(xpath = "//android.widget.TextView[@text='Delete Profile' or @resource-id='com.viacom18.vootkids:id/delete_profile']")
	public WebElement deleteProfileLink;

	@FindBy(xpath = "//android.widget.TextView[@text='Edit your buddy' or @resource-id='com.viacom18.vootkids:id/edit_my_buddy']")
	public WebElement editYourBuddy;

	@FindBy(xpath = "//android.widget.TextView[@text='EDIT PROFILE BUDDY' or @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement editProBuddyTitle;

	@FindBy(xpath = "//android.widget.TextView[@text='Edit your buddy' or @resource-id='com.viacom18.vootkids:id/toolbar_sub_title']")
	public WebElement editYourBuddySubTile;

	@FindBy(id = "com.viacom18.vootkids:id/buddy_icon")
	public WebElement buddyIcon;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/next_btn_animated_view']")
	public WebElement DoneBtnEditBuddy;

	@FindBy(id = "com.viacom18.vootkids:id/btn_settings_menu")
	public WebElement editbuddyBackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Edit Favourites' and @resource-id='com.viacom18.vootkids:id/edit_your_favorites']")
	public WebElement editYourfavourites;
	
	@FindBy(xpath="//android.widget.TextView[@text='Pick your favorites' and @resource-id='com.viacom18.vootkids:id/toolbar_sub_title']")
	public WebElement subTitleFav;
	
	@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement editAvatorTitle;
	
	@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog']")
	public WebElement noBtnforgotPin;

	@FindBy(xpath = "//android.widget.TextView[@text='Select at least 5 favourites' and @resource-id='com.viacom18.vootkids:id/toolbar_sub_title']")
	public WebElement favourites5SelectText;

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement favourites5SelectTextBackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='EDIT PROFILE' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement favouritesPageTile;

	@FindBy(id = "com.viacom18.vootkids:id/next_btn")
	public WebElement doneBtnFavAvatarPage;

	@FindBy(id = "com.viacom18.vootkids:id/character_icon")
	public WebElement charIconFavAvatar;

	@FindBy(id = "com.viacom18.vootkids:id/skill_set_icon")
	public WebElement skillSetIconFavAvatar;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/skill_list']//android.view.ViewGroup[@index='0']")
	public WebElement firstSkillSetICon;

	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/skill_list']//android.view.ViewGroup[@index='1']")
	public WebElement SecondSkillSetICon;

	@FindBy(xpath = "//android.widget.TextView[@text='Please select at least 5 favourites' and @resource-id='com.viacom18.vootkids:id/snackbar_text']")
	public WebElement errorMsg5FavAtLst;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement setiingsProfileEditProBckBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='EDIT PROFILE' or @resource-id ='com.viacom18.vootkids:id/toolbar_title' or @text='Edit Profile']")
	public WebElement settingsEditProfilePageTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Delete profile' and @resource-id='com.viacom18.vootkids:id/delete_profile']")
	public WebElement settingsEditProfileDelProBtn;

	@FindBy(id = "com.viacom18.vootkids:id/next_btn")
	public WebElement settingsEditProfilrNextBtn;

	@FindBy(id = "com.viacom18.vootkids:id/lets_go_btn") // let's go button
	public WebElement letsGo;

	@FindBy(id = "com.viacom18.vootkids:id/skill_set_icon")
	public WebElement skillSetIcon;

	@FindBy(id = "com.viacom18.vootkids:id/character_icon")
	public List<WebElement> characterSetIcons;

	@FindBy(id = "com.viacom18.vootkids:id/character_icon")
	public WebElement characterSetIcon;

	@FindBy(xpath = "//android.widget.TextView[@text='Name']")
	public WebElement settingsProEditProName;

	@FindBy(xpath = "//android.widget.TextView[@text='Date of Birth']")
	public WebElement settingsProEditProDateOfBirth;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/name_edit_text']")
	public WebElement editProNameEditText;

	@FindBy(xpath = "//android.widget.TextView[@text='Please enter Name']")
	public WebElement editProErrorMsgForName;

	@FindBy(xpath = "//android.widget.Button[@text='DONE']")
	public WebElement editproDoneBtn;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/selected_tick']")
	public WebElement settingsProfileSelectedTick;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/name_edit_text']")
	public WebElement editTextinEditProfile;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'CREATE PROFILE') or contains(@text,'Create Profile') or contains(@text,'create profile') or @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement settingsProfileCreatProfileTile;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/dob_date_et']")
	public WebElement settingsProfileEditProDateEdit;

	@FindBy(id = "com.viacom18.vootkids:id/dob_month_et")
	public WebElement settingsProfileEditProMonthEdit;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/dob_year_et']")
	public WebElement settingsProfileEditProYearEdit;

	// Date Of The Birth fileds
	@FindBy(id = "com.viacom18.vootkids:id/dob_year_et")
	public WebElement settingsDateOfYear;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'2019')]")
	public WebElement Year2019;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'2014')]")
	public WebElement year2014;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'1999')]")
	public WebElement year1999;

	@FindBy(id = "android:id/date_picker_header_year")
	public WebElement calendarYearPick;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/back_btn_settings']")
	public WebElement editProBackBtn;

	@FindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1' and @text='OK']")
	public WebElement calenderOkBtn;

	@FindBy(id = "com.viacom18.vootkids:id/btn_settings_menu")
	public WebElement editProfileBackBtn;

	@FindBy(id = "android:id/button2")
	public WebElement calendercancelBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='DELETE' or @resource-id='com.viacom18.vootkids:id/header_dialog' or @text='Delete']")
	public WebElement delPopDELETETile;

	@FindBy(xpath = "//android.widget.ImageView[@text='YES' or @resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog' or @text='Yes']")
	public WebElement prodelPopYesBtn;


	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_close_dialog']")
	public WebElement delPopCrossBtn;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Are you sure you want to delete this profile? Once deleted, profile can’t be recovered.\"  or @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement delPopSubText;

	// Settings Device Page Properties

	@FindBy(id = "com.viacom18.vootkids:id/btn_cancel")
	public WebElement settingscancelBtn;

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement deviceBackBtn;



	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_title' and @text='DEVICE' or @text='Device' or @text='device']")
	public WebElement settingsDeviceTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Cellular Playback']")
	public WebElement devicecellerPlyback;

	@FindBy(xpath = "//android.widget.TextView[@text='Cellular Playback' or @text='cellular playback' or @text='Cellular playback' or @text='CELLULAR PLAYBACK']//parent::*//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceCellularPlybackWithToggleButton;

	@FindBy(xpath = "//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceCellplySwitch;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_tittle' and @text='Default Stream Quality' or @text='Default stream quality' or @text='default stream quality']//parent::*//*[@resource-id='com.viacom18.vootkids:id/drop_down_text']")
	public WebElement devicedefaultsremquality;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text' and @text='Low' or @text='LOW' or @text='low']")
	public WebElement streamQualityLow;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text' and @text='Medium' or @text='MEDIUM' or @text='medium']")
	public WebElement streamQualityMedium;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text' and @text='High' or @text='HIGH' or @text='high']")
	public WebElement streamQualityHigh;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text' and @text='Auto' or @text='AUTO' or @text='auto']")
	public WebElement streamQualityAuto;

	@FindBy(xpath = "//android.widget.TextView[@text='Default Stream Quality']//parent::android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text']")
	public WebElement defaultsremqualityText;

	@FindBy(xpath = "//android.widget.TextView[@text='Low']")
	public WebElement devicedefaultsremqualityLow;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_tittle' and @text='Download Quality' or @text='download quality' or @text='Download quality' or @text='DOWNLOAD QUALITY']//parent::android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text']")
	public WebElement deviceVideoDownloadQualityWithDropdown;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_tittle' and @text='Audio Download Quality' or @text='audio download quality' or @text='Audio download quality' or @text='AUDIO DOWNLOAD QUALITY']//parent::android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text']")
	public WebElement deviceAudioDownloadQualityWithDropdown;

	@FindBy(xpath = "//android.widget.TextView[@text='Preferred Language' or @text='PREFERRED LANGUAGE' or @text='preferred language' or @text='Preferred language']//parent::*//*[@resource-id='com.viacom18.vootkids:id/drop_down_text']")
	public WebElement devicePreferredLanuagewithDropdown;

	@FindBy(xpath = "//android.widget.ListView//android.view.ViewGroup//android.widget.TextView[@text='Hindi']")
	public WebElement devicePrefferdLanuageHindi;

	@FindBy(xpath = "//android.widget.ListView//android.view.ViewGroup[@index='1']//android.widget.TextView[@text='English']")
	public WebElement devicePrefferdLanuageEnglish;

	@FindBy(xpath = "//android.widget.TextView[@text='Preferred Language']//parent::android.view.ViewGroup//android.widget.Spinner[@resource-id='com.viacom18.vootkids:id/item_spinner']")
	public WebElement devicePrefferdLanuageSpinner;

	@FindBy(xpath = "//android.widget.TextView[@text='Tamil']")
	public WebElement devicePrefferdLanuageTamil;

	@FindBy(xpath = "//android.widget.TextView[@text='Preferred Language']//parent::android.view.ViewGroup//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text']")
	public WebElement devicePrefferdLanuageDropDownText;

	@FindBy(xpath = "//android.widget.TextView[@text='Enable Downloads' or @text='enable downloads' or @text='Enable downloads' or @text='ENABLE DOWNLOADS']")
	public WebElement deviceEnableDownloadsWithToggleButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Enable Downloads']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceEnableddownloadsSwitch;

	@FindBy(xpath = "//android.widget.TextView[@text='Cellular Downloads' or @text='cellular downloads' or @text='Cellular downloads' or @text='CELLULAR DOWNLOADS']//parent::*//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceCellularDownloadsWithToggleButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Cellular Downloads']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceCelluallerDownlodsSwitch;

	@FindBy(xpath = "//android.widget.TextView[@text=\"DOWNLOAD DISABLED!\" or @resource-id ='com.viacom18.vootkids:id/header_dialog']")
	public WebElement CelluallerDisabledPopUpTile;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Cellular download is switched off. Go to settings to edit preferences\" or @resource-id ='com.viacom18.vootkids:id/body_dialog']")
	public WebElement CelluallerDisabledPopUpSubText;

	@FindBy(xpath = "//android.widget.Button[@text='OK' or @resource-id ='com.viacom18.vootkids:id/positive_single_btn_dialog' or @text='Ok']")
	public WebElement CelluallerDisabledPopUpOkBtn;

	@FindBy(id = "com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement CelluallerDisabledPopUpCancelBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Download Quality' and @resource-id='com.viacom18.vootkids:id/item_tittle']")
	public WebElement deviceDownloadsQuality;

	@FindBy(xpath = "//android.widget.TextView[@text='Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner//android.widget.TextView[@text=\"Medium - 320\" or @text='Medium' or @text='medium']")
	public WebElement deviceDownloadsQualityMedium;

	@FindBy(xpath = "//android.widget.TextView[@text='Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner//android.widget.TextView[@text=\"Low - 144\" or @text='Low']")
	public WebElement deviceDownloadsQualityLow144;

	@FindBy(xpath = "//android.widget.TextView[@text='Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner//android.widget.TextView[@text=\"High - 720\" or @text='High']")
	public WebElement deviceDownloadsQualityhigh;

	@FindBy(xpath = "//android.widget.TextView[@text='Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner[@resource-id='com.viacom18.vootkids:id/item_spinner']")
	public WebElement deviceDownloadsQualitySpinner;

	@FindBy(xpath = "//android.widget.TextView[@text='Audio Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner//android.widget.TextView[@text=\"Medium - 320\" or @text='Medium']")
	public WebElement deviceAudiodownloadQualityMedium;

	@FindBy(xpath = "//android.widget.TextView[@text='Audio Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner//android.widget.TextView[@text=\"Low - 144\" or @text='Low']")
	public WebElement deviceAudiodownloadQualityLow;

	@FindBy(xpath = "//android.widget.TextView[@text='Audio Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner//android.widget.TextView[@text=\"High - 720\" or @text='High']")
	public WebElement deviceAudiodownloadQualityHigh;

	@FindBy(xpath = "//android.widget.TextView[@text='Audio Download Quality']//parent::android.view.ViewGroup//android.widget.Spinner[@resource-id='com.viacom18.vootkids:id/item_spinner']")
	public WebElement deviceAudioDownloadQualitySpinner;

	@FindBy(xpath = "//android.widget.TextView[@text='Notifications' or @text='notifications' or @text='NOTIFICATIONS']//parent::*//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceNotificationsWithToggleButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Background Music' or @text='background music' or @text='BACKGROUND MUSIC']//parent::*//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceBckMusicWithToggleButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Clear Cache' or @text='clear cache' or @text='Clear cache']//parent::*//*[@resource-id='com.viacom18.vootkids:id/arrow_indicator']")
	public WebElement deviceClearCacheWithToggleButton;

	@FindBy(xpath = "//android.widget.TextView[@text='Notifications']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceNotificationsSwitch;

	@FindBy(xpath = "//android.widget.TextView[@text='Allow Casting']")
	public WebElement deviceAllowCasting;

	@FindBy(xpath = "//android.widget.TextView[@text='Allow Casting']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceAllowCastingSwitch;

	@FindBy(xpath = "//android.widget.TextView[@text='Profile PINs']")
	public WebElement deviceProfilesPINS;
	@FindBy(xpath = "//android.widget.TextView[@text='Clear Cache']")
	public WebElement deviceClearCache;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/item_tittle' and @text='Default Stream Quality' or @text='Default stream quality' or @text='default stream quality']//parent::*//android.widget.TextView[@text='Auto' or @text='AUTO' or @text='auto']")
	public WebElement deviceStremQulAuto;

	@FindBy(xpath = "//android.widget.TextView[@text='Preferred Language']")
	public WebElement devicePrefferdLanuage;

	/*
	 * @FindBy(id="com.viacom18.vootkids:id/item_spinner") public WebElement
	 * spiner;
	 */

	@FindBy(className = "android.widget.Spinner")
	public WebElement spiner;

	@FindBy(xpath = "//android.widget.TextView[@text='Medium']")
	public WebElement deviceStremQulMedium;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/action_button' and @text='RATE US' or @text='Rate Us' or @text='Rate us' or @text='rate us']")
	public WebElement thatsGreatRateUs;

	@FindBy(xpath = "//android.widget.TextView[@text='Contact us']//parent::*//*[@resource-id='com.viacom18.vootkids:id/arrow_indicator']")
	public WebElement helpContactTile;

	@FindBy(xpath = "//android.widget.TextView[@text='English']")
	public WebElement deviceLanubgeEnglish;

	@FindBy(xpath = "//android.widget.TextView[@text='Enable Downloads']")
	public WebElement deviceEnableddownloads;

	@FindBy(xpath = "//android.widget.TextView[@text='Cellular Downloads' and @resource-id='com.viacom18.vootkids:id/item_tittle']")
	public WebElement deviceCelluallerDownlods;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Medium - 320\" or @text='Medium' or @text='medium']")
	public WebElement deviceDownloadQulMedium;

	@FindBy(xpath = "//android.widget.TextView[@text='Notifications']")
	public WebElement deviceNotifications;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text']")
	public WebElement devicedefaultsremqualityDropDownText;

	// Help & Support page

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement helpPagebackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text= \"Help & Support\"]")
	public WebElement settingsHelpSupport;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_title' and @text='HELP' or @text='Help' or @text='help']")
	public WebElement helpHELPtile;

	@FindBy(xpath = "//android.widget.TextView[@text=\"FAQ’s\"]//parent::*//*[@resource-id='com.viacom18.vootkids:id/arrow_indicator']")
	public WebElement helpFAQs;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Google Play']")
	public WebElement rateUsplayStore;

	@FindBy(xpath = "//android.widget.Button[@resource-id = 'com.viacom18.vootkids:id/contact_no']")
	public WebElement helpContactNumBTN;

	@FindBy(id = "com.viacom18.vootkids:id/contact_email")
	public WebElement helpEmailContactBtn;

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement helpTemsConditionsBackBtn;

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement helpPrivacyPolicyBackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='App Version']")
	public WebElement helpAppVersion;

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement helpContactFAQbackBtn;

	@FindBy(xpath = "//android.widget.ImageView[resource-id='com.viacom18.vootkids:id/arrow_indicator']")
	public WebElement helpPageArrow;

	@FindBy(id = "com.viacom18.vootkids:id/contact_desc")
	public WebElement helpContactSubText;

	@FindBy(id = "com.viacom18.vootkids:id/btn_profile_menu")
	public WebElement faqsPageBackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text=\"T&C\"]")
	public WebElement helpTermsandcondTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Privacy Policy' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement helpPrivacyPolicyTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Gmail']")
	public WebElement helpSupportGmailText;

	@FindBy(xpath = "//android.view.View[@resource-id='androidreauthNext']")
	public WebElement GmailNextBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Email']")
	public WebElement helpSupportEmailText;

	@FindBy(xpath = "//android.widget.TextView[@text='ADD NEW ACCOUNT']")
	public WebElement addNewActForEmail;

	@FindBy(xpath = "//android.widget.TextView[@text='Phone']")
	public WebElement helpSupportPhoneSubTile;

	@FindBy(xpath = "//android.widget.TextView[@text='Skype']")
	public WebElement helpSupportSkypeSubtile;

	@FindBy(xpath = "//android.widget.EditText[@text='02230303030']")
	public WebElement helpSupportContactNumber;

	@FindBy(xpath = "//android.widget.TextView[@text='ADD NEW ACCOUNT']")
	public WebElement helpSupportEmailAddNewAct;

	@FindBy(xpath = "//android.widget.TextView[@text='From' and @resource-id='com.google.android.gm:id/from_label']")
	public WebElement helpGmailSupportFROM;

	@FindBy(xpath = "//android.widget.TextView[@text='To' and @resource-id='com.google.android.gm:id/to_heading']")
	public WebElement helpGmailSupportTO;

	@FindBy(xpath = "//android.widget.EditText[@text='Subject' and @resource-id='com.google.android.gm:id/subject']")
	public WebElement helpGmailSupportSubject;

	// Share Feature Functionality

	@FindBy(xpath = "//android.widget.TextView[@text='Share']")
	public WebElement settingsShare;

	@FindBy(id = "com.twitter.android:id/button_tweet")
	public WebElement shareTweetBtn;

	@FindBy(xpath = "//android.widget.TextView[@content-desc='Facebook' or @text='Facebook']")
	public WebElement sharefacebook;

	@FindBy(xpath = "//android.widget.TextView[@content-desc='Gmail' or @text='Gmail']")
	public WebElement shareGmail;

	@FindBy(xpath = "//android.widget.TextView[@text='Compose']")
	public WebElement shareGmailCompose;

	// Rate Us Page Properties

	@FindBy(xpath = "//android.widget.TextView[@text='Rate Us' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement settingsRateUS;

	@FindBy(xpath = "//android.widget.TextView[@text='Yes']")
	public WebElement rateUsPopYesText;

	@FindBy(xpath = "//android.widget.TextView[@text='No']")
	public WebElement rateUsPopNoText;

	@FindBy(xpath = "//android.widget.TextView[@text=\"THAT’S GREAT!\" or @resource-id='com.viacom18.vootkids:id/feed_back_title' or @text=\"That’s Great!\"]")
	public WebElement rateusThatsGreat;

	// @FindBy(xpath="//android.widget.TextView[@text=\"ARE YOU HAPPY WITH VOOT
	// KIDS EXPERIENCE?\" and
	// @resource-id='com.viacom18.vootkids:id/feed_back_title']")
	// public WebElement rateUsSubhead;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/feed_back_title']")
	public WebElement rateUsSubhead;

	@FindBy(id = "com.viacom18.vootkids:id/action_sub_head")
	public WebElement ThatGreatSunHead;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/action_sub_head' and @text='How about rating us on the Play Store then?' or @text='How about rating us on the play store then?']")
	public WebElement thatsGreatSubHead;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/action_button' and @text='SEND FEEDBACK' or @text='Send Feedback' or @text='Send feedback']")
	public WebElement rateUsSendfeedBack;

	@FindBy(xpath = "//android.widget.TextView[@text='RATE US' or @resource-id='com.viacom18.vootkids:id/action_button']")
	public WebElement rateUsRATEUS;
	
	@FindBy(id="com.android.vending:id/unauth_home_sign_in_button")
	public WebElement playSing;
	
	@FindBy(xpath="//android.widget.TextView[@text=\"Terms & Conditions\"]")
	public WebElement helpTemsConditions;

	@FindBy(xpath = "//android.widget.TextView[@text='Send Feedback']")
	public WebElement rateUsSendfeedBackPage;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/feed_back_editext']")
	public WebElement rateUsSendFeedBackEditText;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_close_dialog']")
	public WebElement rateUsSendFeedBackCanlBtn;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/dialog_sub_title' or @text='Feedback submitted successfully.']")
	public WebElement rateUsSendFeedBackSuccussMsg;

	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/close_btn']")
	public WebElement sendFeedbackCancel;

	@FindBy(xpath = "//android.widget.ScrollView[@resource-id='android:id/contentPanel']")
	public WebElement shareList;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title' and @text='Share with Gmail']")
	public WebElement shareWithGmail;

	@FindBy(xpath = "//android.widget.Button[@text='JUST ONCE' or @text='Just Once' or @resource-id='android:id/button_once']")
	public WebElement justOnceBtn;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/send_btn']")
	public WebElement rateUsSendFeedBackSendBtn;

	// Reset password Page

	@FindBy(xpath = "//android.widget.TextView[@text='Current Password']")
	public WebElement currentPassword;

	@FindBy(xpath = "//android.widget.TextView[@text='RESET PASSWORD' or @resource-id='com.viacom18.vootkids:id/toolbar_title' or @text='Reset Password']")
	public WebElement resetPasswordTitle;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_profile_menu']")
	public WebElement resetPasswordpageBackBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='New Password']")
	public WebElement newPassword;

	@FindBy(xpath = "//android.widget.TextView[@text='Confirm New Password']")
	public WebElement confirmNewPassword;

	@FindBy(xpath = "//android.widget.TextView[@text='SAVE' and @resource-id='com.viacom18.vootkids:id/settings_log_btn' or @text='Save']")
	public WebElement saveButtonResetPasswordpage;

	@FindBy(xpath = "//android.widget.TextView[@text='PASSWORD RESET' or @resource-id='com.viacom18.vootkids:id/header_dialog' or @text='Password Reset']")
	public WebElement passwordResetSuccussMsg;

	@FindBy(id = "com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement passwordResetSuccussCrossBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Your password has been reset' and @resource-id='com.viacom18.vootkids:id/body_dialog']")
	public WebElement passwordResetSuccussSubMsg;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Forgot Password?\" or @resource-id='com.viacom18.vootkids:id/btn_forgot_password']")
	public WebElement forgotPassword;

	@FindBy(xpath = "//android.widget.TextView[@text='FORGOT PASSWORD' and @resource-id='com.viacom18.vootkids:id/header_text']")
	public WebElement forgotPasswordPageTile;

	@FindBy(xpath = "//android.widget.Button[@text='Send new Password' or @text='SEND NEW PASSWORD' or @resource-id='com.viacom18.vootkids:id/btn_forgot_password']")
	public WebElement forgotPAWDSendNewPAWD;

	@FindBy(xpath = "//android.widget.TextView[@text='New Password']/..//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/new_pwd_edit_text']")
	public WebElement newPasswordEditText;

	@FindBy(xpath = "//android.widget.TextView[@text='New Password']/..//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/text_input_password_toggle']")
	public WebElement newPasswordToggleBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Current Password']/..//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/currnt_pwd_edit_text']")
	public WebElement currentPasswordEditText;

	@FindBy(xpath = "//android.widget.TextView[@text='Current Password']/..//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/text_input_password_toggle']")
	public WebElement currentPasswordToggleBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Confirm New Password']/..//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/confirm_pwd_edit_text']")
	public WebElement confirmNewPasswordEditText;

	@FindBy(xpath = "//android.widget.TextView[@text='Confirm New Password']/..//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/text_input_password_toggle']")
	public WebElement confirmNewPasswordToggleBtn;

	// VOD player Properties

	@FindBy(xpath = "//android.widget.TextView[@text='Please check your internet connectivity' and @resource-id='com.viacom18.vootkids:id/snackbar_text']")
	public WebElement VODNetWorkErrorMSG;

	// Seek Bar

	@FindBy(xpath = "//android.widget.SeekBar[@resource-id='com.viacom18.vootkids:id/seek_bar']")
	public WebElement seekBar;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_hour']")
	public WebElement seekBarClockDuration;

	///////////////////// bhaskar/////////////////

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title'  and @text='Day']")
	public WebElement daybuttonb;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title'  and @text='Week']")
	public WebElement weekbuttonb;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_item_title' and @text='Month']")
	public WebElement monthbuttonb;

	/////////////////////// bhaskar////////////

	// Sliding functionality
	public void slide(AndroidDriver driver) throws Exception {
		HomePageV2 homePage = new HomePageV2(driver, test);
		int startX = 0;
		String timebefscrub = "", timeaftscrub = "", timeaftscrubback = "";
		// Get vertical location of seekbar.
		int startY = 0;
		int endX = 0;
		if (Utilities.explicitWaitVisible(driver, seekBarClockDuration, 10)) {
			timebefscrub = seekBarClockDuration.getText();

			test.log(LogStatus.INFO,
					"Duration is displayed on the Preference screen before sliding the seek bar - " + timebefscrub);
			String[] intFirstDur = timebefscrub.split(" ");

			// String str = intFirstDur
			double doubleval = Double.parseDouble(intFirstDur[0].toString());

			System.out.println("Before Sliding Duration : " + doubleval);
			test.log(LogStatus.INFO, "Before Sliding Duration : " + doubleval);

		} else
			BasePageV2.reportFail("Duration  is not displayed");
		if (Utilities.explicitWaitClickable(driver, seekBar, 20)) {
			// Get x and y coordinates of webelement//
			startX = seekBar.getLocation().getX();
			// Get vertical location of seekbar.
			startY = seekBar.getLocation().getY();
			endX = 400 + startX;
		} else
			reportFail("Seek bar is not visible");

		// Verifying fwd scrub
		TouchAction action1 = new TouchAction((MobileDriver) driver);
		try {
			action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
					.perform();
		} catch (Exception e) {
			BasePageV2.reportFail("Failed to slide the Seek Bar");
		}

		if (Utilities.explicitWaitVisible(driver, seekBarClockDuration, 10)) {
			timeaftscrub = seekBarClockDuration.getText();
			test.log(LogStatus.INFO,
					"Duration is displayed below the Seek bar after sliding to  forward- " + timeaftscrub);

			String[] intFirstDur = timeaftscrub.split(" ");
			double doubleval = Double.parseDouble(intFirstDur[0].toString());

			System.out.println("After Forward sliding Duration : " + doubleval);
			test.log(LogStatus.INFO, "After Forward sliding Duration : " + doubleval);

			if (!timeaftscrub.equals(timebefscrub))
				test.log(LogStatus.INFO, "sliding to the forward succesfully");
			else
				test.log(LogStatus.INFO, "Failed to sliding the Seek bar to forward");
		} else
			BasePageV2.reportFail("Duration is not displayed");

		// Verifying backward scrub

		try {
			action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX - 300, startY)).release()
					.perform();
		} catch (Exception e) {
			BasePageV2.reportFail("Failed to sliding the Seek Bato backward");
		}

		if (Utilities.explicitWaitVisible(driver, seekBarClockDuration, 5)) {
			timeaftscrubback = seekBarClockDuration.getText();
			test.log(LogStatus.INFO, "Duration is displayed Below the Seek bar after sliding the Seek bar to backward- "
					+ timeaftscrubback);
			String[] intFirstDur = timebefscrub.split(" ");
			double doubleval = Double.parseDouble(intFirstDur[0].toString());

			System.out.println("After Backward Sliding Duration : " + doubleval);
			test.log(LogStatus.INFO, "After Backward Sliding Duration : " + doubleval);

			if (!timeaftscrub.equals(timeaftscrubback))
				test.log(LogStatus.INFO, "Sliding seek bar to backward succesfully");
			else
				test.log(LogStatus.INFO, "Failed to Slide the Seek bar to backward");
		} else
			BasePageV2.reportFail("Duration is not displayed");

	}

	// Slide forward functionality
	double doublevalbeforefarward = 0;

	public void slideForward(AndroidDriver driver) throws Exception {
		HomePageV2 homePage = new HomePageV2(driver, test);
		int startX = 0;
		String timebefscrub = "", timeaftscrub = "", timeaftscrubback = "";
		// Get vertical location of seekbar.
		int startY = 0;
		int endX = 0;
		if (Utilities.explicitWaitVisible(driver, seekBarClockDuration, 10)) {
			timebefscrub = seekBarClockDuration.getText();

			test.log(LogStatus.INFO, "Duration is displayed on the Preference screen before sliding - " + timebefscrub);
			String[] intFirstDur = timebefscrub.split(" ");

			// String str = intFirstDur
			doublevalbeforefarward = Double.parseDouble(intFirstDur[0].toString());

			System.out.println("Before Sliding Duration : " + doublevalbeforefarward);
			test.log(LogStatus.INFO, "Before Sliding Duration : " + doublevalbeforefarward);

		} else
			BasePageV2.reportFail("Duration  is not displayed");
		if (Utilities.explicitWaitClickable(driver, seekBar, 20)) {
			// Get x and y coordinates of webelement//
			startX = seekBar.getLocation().getX();
			// Get vertical location of seekbar.
			startY = seekBar.getLocation().getY();
			endX = 400 + startX;
		} else
			reportFail("Seek bar is not visible");

		// Verifying fwd scrub
		TouchAction action1 = new TouchAction((MobileDriver) driver);
		try {
			action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, startY)).release()
					.perform();
		} catch (Exception e) {
			BasePageV2.reportFail("Failed to scrub the Seek Bar");
		}

		if (Utilities.explicitWaitVisible(driver, seekBarClockDuration, 10)) {
			timeaftscrub = seekBarClockDuration.getText();
			test.log(LogStatus.INFO,
					"Duration  is displayed below the Seek Bar after Sliding the Seek bar forward- " + timeaftscrub);

			String[] intFirstDur = timeaftscrub.split(" ");
			double doublevalAfterForward = Double.parseDouble(intFirstDur[0].toString());

			System.out.println("After Forward sliding Duration : " + doublevalAfterForward);
			test.log(LogStatus.INFO, "After Forward sliding Duration : " + doublevalAfterForward);

			if (doublevalbeforefarward < doublevalAfterForward) {
				test.log(LogStatus.INFO, "Scrubber has moved forward succesfully");
				test.log(LogStatus.INFO, "the seekbar forwarded Successfully, usage time limit should incremented");

			} else
				BasePageV2.reportFail("Failed to Slide the Seek Bar to forward");
		} else
			BasePageV2.reportFail("Duration is not displayed");

	}

	// Slide backward functionality
	double doublevalbeforeBackward = 0;

	public void slideBackWard(AndroidDriver driver) throws Exception {
		HomePageV2 homePage = new HomePageV2(driver, test);
		int startX = 0;
		String timebefscrub = "", timeaftscrub = "", timeaftscrubback = "";
		// Get vertical location of seekbar.
		int startY = 0;
		int endX = 0;
		if (Utilities.explicitWaitVisible(driver, seekBarClockDuration, 10)) {
			timebefscrub = seekBarClockDuration.getText();

			test.log(LogStatus.INFO, "Duration is displayed on the Preference screen before sliding - " + timebefscrub);
			String[] intFirstDur = timebefscrub.split(" ");

			// String str = intFirstDur
			doublevalbeforeBackward = Double.parseDouble(intFirstDur[0].toString());

			System.out.println("Before Sliding Duration : " + doublevalbeforeBackward);
			test.log(LogStatus.INFO, "Before Sliding Duration : " + doublevalbeforeBackward);

		} else
			BasePageV2.reportFail("Duration is not displayed");
		if (Utilities.explicitWaitClickable(driver, seekBar, 20)) {
			// Get x and y coordinates of webelement//
			startX = seekBar.getLocation().getX();
			// Get vertical location of seekbar.
			startY = seekBar.getLocation().getY();
			endX = 400 + startX;
		} else
			reportFail("Seek bar is not visible");

		// Verifying fwd scrub
		TouchAction action1 = new TouchAction((MobileDriver) driver);
		try {
			action1.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX - 300, startY)).release()
					.perform();
		} catch (Exception e) {
			BasePageV2.reportFail("Failed to scrub the video backward");
		}

		if (Utilities.explicitWaitVisible(driver, seekBarClockDuration, 10)) {
			timeaftscrub = seekBarClockDuration.getText();
			test.log(LogStatus.INFO,
					"Duration  is displayed below the Seek Bar after Sliding the Seek bar Backward- " + timeaftscrub);

			String[] intFirstDur = timeaftscrub.split(" ");
			double doublevalAfterBackward = Double.parseDouble(intFirstDur[0].toString());

			System.out.println("After Forward sliding Duration : " + doublevalAfterBackward);
			test.log(LogStatus.INFO, "After Bacward sliding Duration : " + doublevalAfterBackward);

			if (doublevalbeforeBackward > doublevalAfterBackward) {
				test.log(LogStatus.INFO, "Scrubber has moved forward succesfully");
				test.log(LogStatus.INFO, "the seekbar Bacwarded Successfully, usage time limit should Decremented");

			} else
				BasePageV2.reportFail("Failed to Slide the Seek Bar to Backward");
		} else
			BasePageV2.reportFail("Duration is not displayed");

	}

	/////////////// Ending Setting Elements Properties //////////////

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text' and contains(@text,'Low')]")
	public WebElement LowDownloadQualitySelectInDeviceScreen;

	@FindBy(xpath = "//android.widget.TextView[@text='Download Quality']/following-sibling::android.widget.Spinner")
	public WebElement DownloadQualitySelectInDeviceScreen;
	@FindBy(xpath = "//android.widget.TextView[@text='Audio Download Quality']/following-sibling::android.widget.Spinner")
	public WebElement audioDownloadQualitySelectInDeviceScreen;
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text' and contains(@text,'Medium')]")
	public WebElement MediumDownloadQualitySelectInDeviceScreen;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/drop_down_text' and contains(@text,'High')]")
	public WebElement HighDownloadQualitySelectInDeviceScreen;

	@FindBy(id = "com.viacom18.v18.viola:id/edit_profile_txt")
	public WebElement editProfile;

	@FindBy(id = "com.viacom18.v18.viola:id/change_password_txt")
	public WebElement profileChangepassword;

	@FindBy(id = "com.viacom18.v18.viola:id/kids_zone_switch")
	public WebElement kidsZoneSwitch;

	@FindBy(id = "com.viacom18.v18.viola:id/change_pin_txt")
	public WebElement parentalPasswordChange;

	@FindBy(id = "com.viacom18.v18.viola:id/download_on_wifi_switch")
	public WebElement downloadOnWifiSwitch;

	@FindBy(id = "com.viacom18.v18.viola:id/language_preferences_txt")
	public WebElement languagePreferencesOption;

	@FindBy(id = "com.viacom18.v18.viola:id/clear_record_lyt")
	public WebElement clearHistoryOption;

	@FindBy(xpath = "//android.widget.TextView[@text='Background Music']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceBackgroundMusicSwitch;

	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/item_head' and contains(@text,'Device')]")
	public WebElement deviceOptionSettings;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Device') or contains(@text,'DEVICE') or contains(@text,'device')]")
	public WebElement deviceOptionpage;

	@FindBy(xpath = "//*[contains(@text,'Audio Download Quality')]/following-sibling::android.widget.Spinner//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/drop_down_text')]")
	public WebElement audioDefaultQuality;

	@FindBy(xpath = "//android.widget.TextView[@text='Cellular Downloads']")
	public WebElement deviceCellularDownloads;

	@FindBy(xpath = "//android.widget.TextView[@text='Cellular Downloads']/..//android.widget.Switch[@resource-id='com.viacom18.vootkids:id/item_switch']")
	public WebElement deviceCellularDownloadsSwitch;

	

@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog']")
public WebElement ForgotPinPopUpNOBtn;

@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/negative_left_btn_dialog_text' or @text='NO']")
public WebElement forgotPinpopupNObtn;

@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/positive_right_btn_dialog_text' or @text='YES']")
public WebElement forgotPinpopupYESbtn;
	public void changeCellData(String testcaseName, String word, int coulmNUm) throws IOException // send
																									// 2
																									// here
	{
		int testCaseRowNum = 0;
		FileInputStream fis = new FileInputStream(VootConstants.EXCEL_PATHV2);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet1 = workbook.getSheet("Data");
		for (int i = 0; i < sheet1.getLastRowNum(); i++) {
			Row row = sheet1.getRow(i);
			try {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					try {
						String text = row.getCell(j).getStringCellValue();
						if (text.equalsIgnoreCase(testcaseName)) {
							testCaseRowNum = i;
							break;
						}

					} catch (Exception e) {

					}
				}

			} catch (Exception ex) {

			}
		}
		System.out.println(testCaseRowNum);
		workbook.getSheet("Data").getRow(testCaseRowNum + 2).getCell(coulmNUm).setCellValue(word);
		FileOutputStream fileOut = new FileOutputStream(VootConstants.EXCEL_PATHV2);
		workbook.write(fileOut);
		fileOut.close();

	}
	
	
	
	public void putBackGroundApp3() {
		driver.runAppInBackground(Duration.ofSeconds(3));
		test.log(LogStatus.INFO, "Put app to background for 3 seconds");
		driver.currentActivity();
		}
	public static String logintogmailandfetchnewPwd(String email, String Pwd) throws Exception {
		String testCase2 = "Verify if forgot PIN request is sent to user's email ID when logged in via Email	";
		String testId2 = "VK_1696";
		boolean flag = false;
		String gurl = "https://accounts.google.com/signin/v2/identifier?service=mail";
	
		System.setProperty("webdriver.chrome.driver", "./webdriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
	
		options.addArguments("--disable-notifications");
		
		WebDriver driver1 = new ChromeDriver(options);
		driver1.manage().window().maximize();
		driver1.get(gurl);

		for (int i = 0; i < 50; i++) {
			try {
				String xpath = "//input[@type='email']";
				driver1.findElement(By.xpath(xpath)).clear();
				driver1.findElement(By.xpath(xpath)).sendKeys(email);
				flag = true;
				break;

			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}

		if (flag == false)
			BasePageV2.reportFail("Gamil Text field not displayed");
		else {

		}

		flag = false;

		for (int i = 0; i < 50; i++) {
			try {
				String xpath = "//span[contains(.,'Next')]";
				driver1.findElement(By.xpath(xpath)).click();
				flag = true;
				break;

			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}

		if (flag == false)
			BasePageV2.reportFail("Next button not displayed");
		else {

		}
		flag = false;

		for (int i = 0; i < 50; i++) {
			try {
				String xpath = "//input[@type='password']";
				driver1.findElement(By.xpath(xpath)).clear();
				driver1.findElement(By.xpath(xpath)).sendKeys(Pwd);
				flag = true;
				break;

			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}

		if (flag == false)
			BasePageV2.reportFail("Gmail pwd text field not displayed");
		else {

		}
		flag = false;

		for (int i = 0; i < 50; i++) {
			try {
				String xpath = "//span[contains(.,'Next')]";
				driver1.findElement(By.xpath(xpath)).click();
				flag = true;
				break;

			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		if (flag == false)
			BasePageV2.reportFail("Next button not displayed");
		else {

		}
		flag = false;

		

		for (int i = 0; i < 200; i++) {
			try {
				String xpath = "(//td[@class='yX xY ' and contains(.,'unread')]/..//span[contains(.,'Vootkids support')])[3]";
				driver1.findElement(By.xpath(xpath)).click();
				flag=true;
				break;

			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		
		if(flag==false)
			BasePageV2.reportFail("Voot kids support gmail not recieved");
		else {
			
		}
		flag=false;

		for (int i = 0; i < 50; i++) {
			try {
				 newpwdgmail = "";
				String xpath = "//div[@class='aHl']/..//div//div[contains(.,'is your OTP to reset your parent PIN')]";
				WebElement ForgotPin= driver1.findElement(By.xpath(xpath));
				newpwdgmail = ForgotPin.getText();
				String[] splitforPWD = newpwdgmail.split(" ");
				newpwdgmail = splitforPWD[0].toString();
				test.log(LogStatus.INFO, "New password recieved from email is: " + newpwdgmail);
				
				test.log(LogStatus.PASS, "Test Case: "+testCase2+" is Pass");
				try {
					Utilities.setResultsKids(testId2, "Pass");
				} catch (Exception e) {
					
				}
				flag=true;
				
				
				break;

			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		///
		if (flag == false)
			BasePageV2.reportFail("Unable to get password from gmail");
		else {

		}
		flag = false;

		for (int i = 0; i < 50; i++) {
			try {
				String xpath = "(//div[@gh='tm']//div[@class='asa'])[4]";
				driver1.findElement(By.xpath(xpath)).click();
				flag = true;
				break;

			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		if (flag == false)
			BasePageV2.reportFail("Unable to delete gmail");
		else {

		}
		
		driver1.close();
		
		return newpwdgmail;
	}
	
	
	public void cellularDownloadsSwichOFFNew() throws Exception {

		  // click on profile pic icon
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			homepagev2.profilepic.click();
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, parentZoneButton, 30)) {
				parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, parentPinContainer, 30)) {
					Thread.sleep(30000);
					parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(10000);
					// putting App in background
					 putBackGroundApp3();
					// click on Settings Icon in Parent Zone page
					 if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
							settingsIcon.click();
						 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
						 
						 // click on Device Settings Option
						 if(Utilities.explicitWaitClickable(driver, settingsDevice, 50)) {
							 settingsDevice.click();
							 test.log(LogStatus.INFO, "click on settings Device option");
							 
							//scroll to Cellular Downloads
							 String end = "//android.widget.TextView[@text='Cellular Downloads']";
							 Utilities.verticalSwipe(driver, end);

							 //Utilities.verticalSwipe(driver, deviceCelluallerDownlods);

							 if(Utilities.explicitWaitClickable(driver, deviceCelluallerDownlodsSwitch, 5)) {
							 	test.log(LogStatus.INFO, "Found Cellular Downloads Switch In Device screen");
							 }else test.log(LogStatus.FAIL, "Not found Cellular Downloads Switch in Device Screen");

							 if(deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("false")) {
							 	test.log(LogStatus.INFO, "Cellular Downloads switch is in 'OFF' state");
							 	
							 	
							 }else if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("true")){
							 	test.log(LogStatus.INFO, "Cellular Downloads switch is in 'ON' state");
							 	try {
							 		deviceCelluallerDownlodsSwitch.click();
							 		Thread.sleep(3000);
							 		test.log(LogStatus.INFO, "Clicked Cellular Downloads switch for 'OFF' state in Device Screen");
							 	} catch (Exception e) {
							 		test.log(LogStatus.FAIL, "Not able to click Cellular Downloads switch for 'OFF' state in Device Screen");
							 	}

							 }
							 
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							 
					
						 }else BasePageV2.reportFail("Failed to click on settings Device option in settings main page");
					 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
				}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		}else BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");

	}
	
	public void cellularDownloadsSwichONNew() throws Exception {

		  // click on profile pic icon
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 50)) {
			homepagev2.profilepic.click();
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, parentZoneButton, 30)) {
				parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, parentPinContainer, 30)) {
					Thread.sleep(1000);
					parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(30000);
					// putting App in background
					 putBackGroundApp3();
					// click on Settings Icon in Parent Zone page
					 if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
							settingsIcon.click();
						 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
						 
						 // click on Device Settings Option
						 if(Utilities.explicitWaitClickable(driver, settingsDevice, 50)) {
							 settingsDevice.click();
							 test.log(LogStatus.INFO, "click on settings Device option");
							 
							//scroll to Cellular Downloads
							 String end = "//android.widget.TextView[@text='Cellular Downloads']";
							 Utilities.verticalSwipe(driver, end);

							 //Utilities.verticalSwipe(driver, deviceCelluallerDownlods);

							 if(Utilities.explicitWaitClickable(driver, deviceCelluallerDownlodsSwitch, 5)) {
							 	test.log(LogStatus.INFO, "Found Cellular Downloads Switch In Device screen");
							 }else test.log(LogStatus.FAIL, "Not found Cellular Downloads Switch in Device Screen");

							 if(deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("true")) {
							 	test.log(LogStatus.INFO, "Cellular Downloads switch is in 'ON' state");
							 	
							 	
							 }else if (deviceCelluallerDownlodsSwitch.getAttribute("checked").equals("false")){
							 	test.log(LogStatus.INFO, "Cellular Downloads switch is in 'OFF' state");
							 	try {
							 		deviceCelluallerDownlodsSwitch.click();
							 		Thread.sleep(3000);
							 		test.log(LogStatus.INFO, "Clicked Cellular Downloads switch for 'ON' state in Device Screen");
							 	} catch (Exception e) {
							 		e.printStackTrace();
							 	}

							 }
							 
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							 
					
						 }else BasePageV2.reportFail("Failed to click on settings Device option in settings main page");
					 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
				}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		}else BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");

	}

	// Cellular play back switch OFF 
	
	public void cellularPlayBackSwichOFF() throws Exception {

		  // click on profile pic icon
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 80)) {
			homepagev2.profilepic.click();
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, parentZoneButton, 30)) {
				parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, parentPinContainer, 30)) {
					Thread.sleep(1000);
					parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(30000);
					// putting App in background
					 putBackGroundApp3();
					// click on Settings Icon in Parent Zone page
					 if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
							settingsIcon.click();
						 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
						 
						 // click on Device Settings Option
						 if(Utilities.explicitWaitClickable(driver, settingsDevice, 50)) {
							 settingsDevice.click();
							 test.log(LogStatus.INFO, "click on settings Device option");
							 
						
							 if(Utilities.explicitWaitClickable(driver, deviceCellplySwitch, 5)) {
							 	test.log(LogStatus.INFO, "Found Cellular Playback Switch In Device screen");
							 }else test.log(LogStatus.FAIL, "Not found Cellular Playback Switch in Device Screen");

								if (deviceCellplySwitch.getAttribute("checked").equals("false")) {
									test.log(LogStatus.INFO, "Cellular Playback switch is in 'OFF' state");
									

								} else if (deviceCellplySwitch.getAttribute("checked").equals("true")) {
									test.log(LogStatus.INFO, "Cellular Playback switch is in 'ON' state");
									try {
										deviceCellplySwitch.click();
										test.log(LogStatus.INFO, "Clicked Cellular Playback switch for 'OFF' state in Device Screen");
									} catch (Exception e) {
										test.log(LogStatus.FAIL, "Not able to click Cellular playback switch for 'OFF' state in Device Screen");
									}
   
								}
							 
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							 
					
						 }else BasePageV2.reportFail("Failed to click on settings Device option in settings main page");
					 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
				}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		}else BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");

	}
	
	// Cellular play back Switch ON
	public void cellularPlayBackSwichON() throws Exception {

		  // click on profile pic icon
		if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 80)) {
			homepagev2.profilepic.click();
			// Click on ParentZone Button in Switch Profile Screen parentZoneButton
			if (Utilities.explicitWaitVisible(driver, parentZoneButton, 30)) {
				parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, parentPinContainer, 30)) {
					Thread.sleep(1000);
					parentPinContainer.sendKeys("1111"); // set the pin "1111" default
					Thread.sleep(30000);
					// putting App in background
					 putBackGroundApp3();
					// click on Settings Icon in Parent Zone page
					 if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
							settingsIcon.click();
						 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
						 
						 // click on Device Settings Option
						 if(Utilities.explicitWaitClickable(driver, settingsDevice, 50)) {
							 settingsDevice.click();
							 test.log(LogStatus.INFO, "click on settings Device option");
							 
							 if(Utilities.explicitWaitClickable(driver, deviceCellplySwitch, 5)) {
								 	test.log(LogStatus.INFO, "Found Cellular Playback Switch In Device screen");
								 }else test.log(LogStatus.FAIL, "Not found Cellular Playback Switch in Device Screen");

									if (deviceCellplySwitch.getAttribute("checked").equals("false")) {
										test.log(LogStatus.INFO, "Cellular Playback switch is in 'OFF' state");
										try {
											deviceCellplySwitch.click();
											test.log(LogStatus.INFO, "Clicked Cellular Playback switch for 'ON' state in Device Screen");
										} catch (Exception e) {
											test.log(LogStatus.FAIL, "Not able to click Cellular playback switch for 'ON' state in Device Screen");
										}

									} else if (deviceCellplySwitch.getAttribute("checked").equals("true")) {
										test.log(LogStatus.INFO, "Cellular Playback switch is in 'ON' state");
										
	   
									}
							 
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							    driver.pressKeyCode(AndroidKeyCode.BACK);
							 
					
						 }else BasePageV2.reportFail("Failed to click on settings Device option in settings main page");
					 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
				}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
			}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
		}else BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");

	} // Cellular play Back Switch ON End
	
	
	// clear Cache Functionality
	public void clearCacheDeviceSettings() throws Exception {
		  // click on profile pic icon
			if(Utilities.explicitWaitClickable(driver, homepagev2.profilepic, 80)) {
				homepagev2.profilepic.click();
				// Click on ParentZone Button in Switch Profile Screen parentZoneButton
				if (Utilities.explicitWaitVisible(driver, parentZoneButton, 30)) {
					parentZoneButton.click();
					test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
					if (Utilities.explicitWaitVisible(driver, parentPinContainer, 30)) {
						Thread.sleep(1000);
						parentPinContainer.sendKeys("1111"); // set the pin "1111" default
						Thread.sleep(30000);
						// putting App in background
						 putBackGroundApp3();
						// click on Settings Icon in Parent Zone page
						 if (Utilities.explicitWaitVisible(driver, settingsIcon, 80)) {
								settingsIcon.click();
							 test.log(LogStatus.INFO, "click on Settings icon in Parent Zone Page");
							 
							 // click on Device Settings Option
							 if(Utilities.explicitWaitClickable(driver, settingsDevice, 50)) {
								 settingsDevice.click();
								 test.log(LogStatus.INFO, "click on settings Device option");
								 try {
									//scroll to Clear Cache
									 String end = "//android.widget.TextView[@text='Clear Cache' or @text='clear cache' or @text='CLEAR CACHE']";
									 Utilities.verticalSwipe(driver, end);
	                                 Utilities.verticalSwipe(driver);
								} catch (Exception e) {
									// TODO: handle exception
								}
								

								 if(Utilities.explicitWaitClickable(driver, deviceClearCacheWithToggleButton, 50)) {
									 deviceClearCacheWithToggleButton.click();
								 	test.log(LogStatus.INFO, "Found Clear Cache option In Device screen ");
								 	if(Utilities.explicitWaitClickable(driver,prodelPopYesBtn, 60)) {
								 		prodelPopYesBtn.click();
								 		test.log(LogStatus.INFO, "clicked on Device Clear Cache option pop up Yes button");
								 		Thread.sleep(15000);
								 		putBackGroundApp3();
								 		if(Utilities.explicitWaitVisible(driver, settingsDeviceTile, 60)) {
								 			test.log(LogStatus.INFO, "Naviagted to device settings screen");
								 		}else {
								 			BasePageV2.reportFail("Not able Validate clear cache option functionality ");
								 		}
								 		
								 	}else BasePageV2.reportFail("Not displayed delete pop up yes button");
								 	
								 	
								 }else test.log(LogStatus.FAIL, "Not found Clear Cache option in Device Screen");

							
								 
								    driver.pressKeyCode(AndroidKeyCode.BACK);
								    driver.pressKeyCode(AndroidKeyCode.BACK);
								    driver.pressKeyCode(AndroidKeyCode.BACK);
								    driver.pressKeyCode(AndroidKeyCode.BACK);
								 
						
							 }else BasePageV2.reportFail("Failed to click on settings Device option in settings main page");
						 }else BasePageV2.reportFail("Not displayed settings icon in parent zone screen / not click");
					}else BasePageV2.reportFail("PIN CONTAINER Not found in Parent Zone Page");
				}else BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");
			}else BasePageV2.reportFail("Not displayed profile pic icon in home page / not able to clicks ");
		
		
		
	}
	
}
