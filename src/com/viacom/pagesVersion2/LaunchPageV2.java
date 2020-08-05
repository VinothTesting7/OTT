package com.viacom.pagesVersion2;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
/*************************************************************************************
 * Class        : LaunchPage
 * Purpose      : This class is used for selecting on various voot account signin and new voot registrations
 * Remarks      : none
 * Author       : Roja KC, Ifocus
 * Modifications:
 *                26 May 2017 - First created
 
 **************************************************************************************/
public class LaunchPageV2 extends BasePageV2{
		
	public LaunchPageV2(AndroidDriver driver,ExtentTest test){
		super(driver,test);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_sub_title' and (contains(@text,'EDIT YOUR BUDDY') or contains(@text,'Edit your buddy'))]")
	public WebElement editProfileBuddyScreenToolBarTitle;
	
	@FindBy(id = "com.viacom18.vootkids:id/input_pinView") // Pin container
	public WebElement pinContainer;

@FindBy(xpath = "//android.widget.TextView[contains(@text,'Forgot Password')]") //Forgot Password Page
	public WebElement forgotPasswordPage;

@FindBy(xpath = "//android.widget.TextView[contains(@text,'Choose your buddy') or contains(@text,'choose your buddy') or contains(@text,'Choose Your Buddy') or contains(@text,'CHOOSE YOUR BUDDY')]") // Password text above password text field
public WebElement chooseBuddyPage;

@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_sub_title' and (contains(@text,'EDIT YOUR BUDDY') or contains(@text,'Edit your buddy'))]")
public WebElement editProfileBuddyScreenToolBarSubTitle;
      @FindBy(xpath= "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/profile_list']//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout']")
      public List<WebElement> profileLists;
	@FindBy(name= "Sign in with Facebook")
	public WebElement signinFacebook;
	
	@FindBy(name= "Sign in with Google")
	public WebElement signinGoogle;
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_sub_title' and @text='Edit Buddy']")
	public WebElement editProfileBuddyScreenSubTitle;
	
	

	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Reset Password')]")
	public WebElement resetPasswordInAccountSettingsScreen;
	
	@FindBy(xpath="//android.widget.TextView[@text='Reset Parent Zone Pin' and @resource-id='com.viacom18.vootkids:id/item_head']")
	public WebElement resetParentZonePinInAccountSettingsScreen;
	

	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Account')]")
	public WebElement accountSectionInSettingsScreen;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'You cannot reset your password')]")
	public WebElement cannotResetPasswordMessage;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'OK')]")
	public WebElement cannotResetPasswordPopUpOkButton;
	/*@FindBy(xpath = "//android.widget.TextView[contains(@text,'Forgot Password') or contains(@text,'Forgot password') or contains(@text,'forgot password') or contains(@text,'FORGOT PASSWORD')]") //Forgot Password Page
	public WebElement forgotPasswordPage;*/
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'sign up') or contains(@text,'Sign Up') or contains(@text,'Sign up') or contains(@text,'SIGN UP')]") //Sign up Page
	public WebElement signUpPage;

   @FindBy(id = "com.viacom18.vootkids:id/btn_new_member_sign_up") // Signup link login page
	public WebElement signuplinkLoginPage;

    @FindBy(id = "com.viacom18.vootkids:id/input_pinView")
	public WebElement parentalPin;
	
	
	// aDDING ONE COMMENT
	
	@FindBy(id= "com.viacom18.v18.viola:id/email")
	public WebElement email;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Device')]")
	public WebElement deviceSectionInSettingsScreen;
	@FindBy(id= "com.viacom18.v18.viola:id/password")
	public WebElement password;

	
	@FindBy(id = "com.viacom18.v18.viola:id/login_btn") //login button
	public WebElement loginBtn;
	
	
	
	@FindBy(id = "com.viacom18.v18.viola:id/txt_register")
	public WebElement register;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Please enter at least 2 characters')]")
	public WebElement enterAtleastTwoCharactersErrorMessage;
	@FindBy(id= "com.viacom18.v18.viola:id/forgot_password_txt")
	public WebElement forgotPassword;
	
	@FindBy(id = "com.viacom18.v18.viola:id/hamburger_menu")
	public WebElement MenuTab;
	
	@FindBy(id= "com.viacom18.v18.viola:id/skip_txt")
	public WebElement skipExplore;
	
	@FindBy(id = "com.viacom18.v18.viola:id/circles_page_indicator")
	public WebElement pageIndicator;
	
	//@FindBy(id="com.viacom18.v18.viola:id/lyt_voot")
	@FindBy(id="com.viacom18.v18.viola:id/gateway_btn_view")
	public WebElement selectSection;
	
	@FindBy(id = "com.viacom18.v18.viola:id/root_lyt")
	public WebElement root;
	
	@FindBy(id = "com.viacom18.v18.viola:id/password_error")
	public WebElement passwordError;
	
	@FindBy(id = "com.viacom18.v18.viola:id/email_error")
	public WebElement emailError;
	
	@FindBy(id = "com.viacom18.v18.viola:id/gateway_btn_view")
	public WebElement gatewayBtn;
	
	@FindBy(xpath = "//android.widget.TextView[@text='login' or @text='Login' or @text='LOGIN']") //Login Gateway button
	public WebElement loginGateway;
	
	@FindBy(id = "com.viacom18.vootkids:id/edit_text_email_id") // Email text field
	public WebElement emailText;
	
	@FindBy(id = "com.viacom18.vootkids:id/edit_text_phone_number") // Email text field
	public WebElement mobilenumberText;
	
	@FindBy(id = "com.viacom18.vootkids:id/password_edit_text") //Password text field
	public WebElement pwdText;
	
	@FindBy(id = "com.viacom18.vootkids:id/text_input_password_toggle")//Show password icon
	public WebElement showPwd;
		
	@FindBy(id = "com.viacom18.vootkids:id/header_text") // Login header text login page
	public WebElement headerText;
	
	@FindBy(id = "com.viacom18.vootkids:id/btn_back") // Login Page Back Button
	public WebElement backButton;
	
	@FindBy(id = "com.viacom18.vootkids:id/header_subtitle") // Enter your details text field Login Page
	public WebElement headerSubtitle;
	
	@FindBy(id = "com.viacom18.vootkids:id/text_email") // Mobile No./ Email text above email text field
	public WebElement textAboveEmailTextField;
	
	@FindBy(id = "com.viacom18.vootkids:id/label_phone_number") // Mobile No text above email text field
	public WebElement textAboveMobilenumberTextField;
	

	/*@FindBy(id = "com.viacom18.vootkids:id/input_pinView") // Pin container
	public WebElement pinContainer;
	*/
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Create your buddy') or contains(@text,'create your buddy') or contains(@text,'Create Your Buddy') or contains(@text,'CREATE YOUR BUDDY')]") // Password text above password text field
	public WebElement createBuddyPage;
	
	@FindBy(id = "com.viacom18.vootkids:id/text_password") // Password text above password text field
	public WebElement textAbovepwdTextField;
	
	@FindBy(id = "com.viacom18.vootkids:id/email_sign_up") // Next Button
	public WebElement nextButton;

	
	@FindBy(id = "com.viacom18.vootkids:id/btn_create_pin") // Next Button
	public WebElement buttonCreatePin;
	
	@FindBy(id = "com.viacom18.vootkids:id/name_edit_text") // Enter Data in kids name
	public WebElement kidsName;
	
	@FindBy(id = "com.viacom18.vootkids:id/title")
	public WebElement screenTitle;
	
	@FindBy(id = "com.viacom18.vootkids:id/dob_date_et") // DOB birth od 
	public WebElement DOB;
	
	@FindBy(id = "android:id/button1") // Date pick
	public WebElement DOBSelect;
	
	@FindBy(id = "com.viacom18.vootkids:id/next_btn") // Next Button
	public WebElement next;
	
	@FindBy(id = "com.viacom18.vootkids:id/lets_go_btn") // let's go button
	public WebElement letsGo;
	
	@FindBy(id = "com.viacom18.vootkids:id/label_forgot_password") // forgot password
	public WebElement forgotPwd;

	
	@FindBy(id = "com.viacom18.vootkids:id/btn_login") // Login Button
	public WebElement loginButton;
	
	@FindBy(id = "com.viacom18.vootkids:id/divider_login_new_member") // Or divider below forgot password
	public WebElement OrDivider;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Not a member?')]") //Not a member? text beside signup link text
	public WebElement notaMemberText;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Already have an account?')]") //Already have account text beside login link text
	public WebElement alreadyHaveAccount;
	
	@FindBy(linkText = "//android.widget.TextView[@text='sign up']") //sign up button
	public WebElement signUp;
	
	@FindBy(linkText = "//android.widget.TextView[@text='Login']") //Login button
	public WebElement Login;
	
	 @FindBy(id = "com.viacom18.vootkids:id/btn_sign_up_into_screen") //Join Free for 30 Days Button
	 public WebElement SignUpfromWelcomePage;


/*	@FindBy(id = "com.viacom18.vootkids:id/btn_sign_up_into_screen") //Join Free for 30 Days Button
	public WebElement SignUpfromWelcomePage;
*/
	@FindBy(id = "com.viacom18.vootkids:id/error_message_password") // Password error message
	public WebElement pwdErrorMsg;
	
	@FindBy(id = "com.viacom18.vootkids:id/common_error_message") // common errors (EX: User does not exist error message)
	public WebElement commonErrors;
	
	@FindBy(id = "com.viacom18.vootkids:id/error_message_email_id") // Invalid email id error
	public WebElement emailidError;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'NEW PROFILE') or contains(@text,'New Profile') or contains(@text,'new profile')]") // Create Profile
	public WebElement CreateProfile;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Please enter email ID or mobile and password')]") //Please enter email ID or mobile and password common errors
	public WebElement commonError;
	
	@FindBy(xpath="//android.widget.TextView[contains(@text,'••••')]") // Dotted text inside password text field
	public WebElement dottedText;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'JOIN FREE FOR 30 DAYS')]") //welcomeScreen
	public WebElement welcomeScreen;
	
	
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'SELECT PROFILE') or contains(@text,'Select Profile')]") //Select Profile Page
	public WebElement selectProfilePage;
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']")
	public WebElement selectFirstProfile;
	
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='1']") // Select Profile Icon from multiple page
	public WebElement selectProfileIcon;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/toolbar_title']") // Select Profile Title
	public WebElement selectProfileTitle;
	
	@FindBy(xpath = "//android.widget.Button[contains(@text,'Continue')]") //Select Profile Icon from multiple page
	public WebElement continueButton;
	
	@FindBy(id = "com.viacom18.vootkids:id/text_country_code") //Select Profile Icon from multiple page
	public WebElement countryCode;
	
	@FindBy(id = "com.viacom18.vootkids:id/list_selected_icon") //Select Icon from multiple page (Right tick icon)
	public WebElement rightTickIcon;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Create new')]/..") //Create new link (+ icon)
	public WebElement createNewLink;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'NEW PROFILE')]") //Create Profile Page
	public WebElement createProfilePage;
	
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Create new')]/..//android.widget.ImageView")
	public WebElement createNewIcon;
	

	
	@FindBy(xpath= "(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text'])[1]")
	public WebElement switchProfileScreenProfileName;
	
	
	@FindBy(xpath= "//android.view.ViewGroup//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']")
	public WebElement switchProfileScreenProfileAvatar;
	
	@FindBy(xpath= "//android.view.ViewGroup//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_selected_icon']")
	public WebElement switchProfileScreenSelectedProfile;
	

	@FindBy(xpath= "//android.view.ViewGroup//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon' and @index='0']")
	public WebElement unselectedProfileAvatar;
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon' and @index='0']//following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text']")
	public WebElement unselectedProfileName;
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_selected_icon' and @index='0']//following-sibling::android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text']")
	public WebElement selectedProfileName;
	
	
	@FindBy(id= "android:id/date_picker_header_year")
	public WebElement calendarYearPick;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'2018')]")
	public WebElement currentYear;
	
	@FindBy(xpath= "(//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/list_text'])[2]")
	public WebElement switchProfileScreenCreatedProfileName;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'1999')]")
	public WebElement year1999;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'2019')]")
	public WebElement year2020;
	@FindBy(id= "com.viacom18.vootkids:id/name_edit_text")
	public WebElement kidsNameField;
	
	@FindBy(xpath= "//android.widget.EditText[@text=\"Kid's name\"]")
	public WebElement kidsNameFieldText;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Create your buddy') and @resource-id='com.viacom18.vootkids:id/toolbar_sub_title']")
	public WebElement createYourBuddyScreen;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'ALL SET') and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement createProfileFinalScreenSubTitle;
	
	@FindBy(xpath= "//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/main_buddy_icon']")
	public WebElement createProfileFinalScreenMainBuddyIcon;
	
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_name']")
	public WebElement createProfileFinalScreenKidsProfileName;
	
	
	@FindBy(xpath= "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tv_age']")
	public WebElement createProfileFinalScreenKidsAge;
		
	
	@FindBy(xpath= "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/buddy_list']//android.widget.LinearLayout//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/buddy_icon']")
	public List<WebElement> avatarImagesAtCreateYourBuddyScreen;
	
	@FindBy(xpath= "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/buddy_color_list']//android.view.ViewGroup")
	public List<WebElement> coloursList;
	
	@FindBy(xpath= "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/buddy_color_list']//android.view.ViewGroup")
	public WebElement colourInBuddyScreen;
	
	
	
	@FindBy(id= "com.viacom18.vootkids:id/btn_edit_profile")
	public WebElement editProfileBuddyButton;
	
	@FindBy(id= "com.viacom18.vootkids:id/btn_parent_zone")
	public WebElement parentZoneButton;
	
	@FindBy(id= "com.viacom18.vootkids:id/btn_cancel")
	public WebElement switchProfileCancelButton;
	
	
	
	@FindBy(xpath= "//*[@resource-id='com.viacom18.vootkids:id/next_btn' or @text='DONE']")
	public WebElement editProfileBuddyScreenDoneButton;
	
	
	
	

	@FindBy(xpath= "//android.support.v7.widget.RecyclerView[@resource-id='com.viacom18.vootkids:id/buddy_list']//android.widget.LinearLayout//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/buddy_icon']")
	public WebElement avatarImageAtCreateYourBuddyScreen;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'CREATE PROFILE') and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement createProfileSecondScreenToolBarTitle;
	
	

	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'about your kid')]")
	public WebElement createProfileTellUsFirstScreenAboutKid;
	

	
	@FindBy(xpath= "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/indicator_layout']/android.widget.ImageView")
	public List<WebElement> threepaginationindicator;
	
	@FindBy(xpath= "//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/indicator_layout']/android.widget.ImageView")
	public WebElement paginationindicator;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Please enter Name')]")
	public WebElement enterNameErrorMessage;
	
	@FindBy(id= "com.viacom18.vootkids:id/dob_date_et")
	public WebElement dobProfileField;
	
	@FindBy(id= "com.viacom18.vootkids:id/dob_date_et")
	public WebElement dobProfileMonthField;
	
	
	@FindBy(xpath= "//android.widget.Button[@text='OK']")
	public WebElement calendarOkButton;
	
	@FindBy(xpath= "//android.widget.TextView[@text='Age should be above zero']")
	public WebElement ageAboveZeroErrorMessage;
	

	
	@FindBy(id= "com.viacom18.vootkids:id/next_btn")
	public WebElement createProfileScreenNextButton;
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'NEW PROFILE')]")
	public WebElement createProfileTellUsFirstScreen;

	@FindBy(id = "com.viacom18.vootkids:id/input_pinView") // Parent Pin container
	public WebElement parentPinContainer;
	@FindBy(id= "com.viacom18.vootkids:id/next_btn")
	public WebElement signUpScreenNextButton;
	
	@FindBy(id= "com.viacom18.vootkids:id/text_create")
	public WebElement createAnotherProfileLink;
	
	@FindBy(id= "com.viacom18.vootkids:id/character_icon")
	public WebElement characterSetIcon;
	
	@FindBy(id= "com.viacom18.vootkids:id/character_icon")
	public List<WebElement> characterSetIcons;
	
	@FindBy(id= "com.viacom18.vootkids:id/skill_set_icon")
	public WebElement skillSetIcon;
	
	@FindBy(id= "com.viacom18.vootkids:id/btn_settings_menu")
	public WebElement createProfileBackButton;
	
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Switch profile') or contains(@text,'SWITCH PROFILE')]")
	public WebElement switchProfileScreenToolBarTitle;
	
	
	@FindBy(id= "com.viacom18.vootkids:id/lets_go_btn")
	public WebElement profileCompleteLetsGoButton;
	
	@FindBy(id= "com.viacom18.vootkids:id/btn_settings_menu")
	public WebElement settingsInSwitchProfileScreen;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Create your buddy')]")
	public WebElement createProfileSelectAvatarScreen;
	
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Profiles')]")
	public WebElement profilesSectionInSettingsScreen;
	
	@FindBy(xpath= "//android.support.v7.widget.RecyclerView[@index='1']//android.view.ViewGroup[@index='0']")
	public WebElement firstProfileInProfilesScreen;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Edit profile') or contains(@text,'EDIT PROFILE')]")
	public WebElement editProfileScreen;
	
	//@FindBy(id= "com.viacom18.vootkids:id/text_delete")
	@FindBy(id= "com.viacom18.vootkids:id/delete_profile")
	public WebElement deleteProfileLink;
	
	@FindBy(id= "com.viacom18.vootkids:id/positive_right_btn_dialog")
	public WebElement deleteProfilePopUpYesButton;
	
	@FindBy(id= "com.viacom18.vootkids:id/negative_left_btn_dialog")
	public WebElement deleteProfilePopUpNoButton;
	
	
	
	@FindBy(id= "com.viacom18.vootkids:id/name_edit_text")
	public WebElement nameEditTextField;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'favourites')]")
	public WebElement createProfileSelectFavoritesPreferencesScreen;

	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'PROFILES') and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
	public WebElement profilesScreen;


	@FindBy(id= "com.viacom18.vootkids:id/btn_code_manually")
	public WebElement entercodeManuallyLink;
	
	@FindBy(id= "com.viacom18.vootkids:id/btn_switch_to_auto")
	public WebElement autoCodedetectBtn;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Please enter the OTP sent to')]")
	public WebElement otpPageHeaderSubtitle;
	
	@FindBy(id= "com.viacom18.vootkids:id/header_text")
	public WebElement otpPageHeader;
	
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Not Received your OTP?')]")
	public WebElement notRecievedOtp;
	
	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Resend OTP')]")
	public WebElement resendOTP;
	
	@FindBy(id = "com.viacom18.vootkids:id/container_otp_parent")
	public WebElement otppininner;
	
	@FindBy(id = "com.viacom18.vootkids:id/container_otp") // OTP Text Field
	public WebElement otpcontainer;
	
/*	@FindBy(xpath= "//android.widget.TextView[contains(@text,'Create your buddy')]")
	public WebElement createAnotherProfileLink;
	*/

	@FindBy(id= "com.viacom18.vootkids:id/error_message_phone_number")
	public WebElement mobileTextFieldError;
	
	void pulltest()
	{
	 System.out.println("Vinoth");
	}//commit happened 
	
	
	public void registerWithoutMobileNumberSubscription() throws Exception
	{
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		if(Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 20)) {
			homepagev2.logout();
		}
		if(Utilities.explicitWaitVisible(driver, SignUpfromWelcomePage, 25)) 
		SignUpfromWelcomePage.click();
		else
		BasePageV2.reportFail("Welcome screen is not loaded");
		
		String email=Utilities.generateEmailid();
		String pwd="vinoth123";
		
		if(Utilities.explicitWaitVisible(driver, emailText, 25)) {
			emailText.clear();
			emailText.sendKeys(email);			
		}
		else
			BasePageV2.reportFail("Sign up screen is not loaded");
	    driver.pressKeyCode(AndroidKeyCode.BACK);
	    
	    
	    
	    
		if(Utilities.explicitWaitVisible(driver,pwdText, 25)) {		
			pwdText.clear();
			pwdText.sendKeys(pwd);
		}
		else
			BasePageV2.reportFail("Password Text field not found");
		Thread.sleep(1000);
		    try{
		    	driver.hideKeyboard();
		    }
		    catch(Exception e){e.printStackTrace();}
			Thread.sleep(1000);  
			if(Utilities.explicitWaitVisible(driver, nextButton, 20))
			nextButton.click();
			else
			BasePageV2.reportFail("Not able to click Next button in Sign up Screen");
			
			
			if(Utilities.explicitWaitVisible(driver, pinContainer, 20))
				pinContainer.sendKeys("1111");
			else
				BasePageV2.reportFail("Create Pin screen is not loaded");
		    driver.pressKeyCode(AndroidKeyCode.BACK);
		    
		    
			if(Utilities.explicitWaitVisible(driver, buttonCreatePin, 20))
				buttonCreatePin.click();
			else
				BasePageV2.reportFail(" Not able to click Next button in Create Pin screen");
			
			if(Utilities.explicitWaitVisible(driver, pinContainer, 20))
				pinContainer.sendKeys("1111");
			else
				BasePageV2.reportFail("Create Pin screen - 'Again to Confirm' is not loaded");
			  driver.pressKeyCode(AndroidKeyCode.BACK);
			if(Utilities.explicitWaitVisible(driver, buttonCreatePin, 20))
				buttonCreatePin.click();
			else
				BasePageV2.reportFail(" Not able to click Next button in Create Pin screen - 'Again to Confirm'");
		    
			
			if(Utilities.explicitWaitVisible(driver, createProfileTellUsFirstScreen, 20))
			{
			}
			else
				BasePageV2.reportFail("Not navigated to Create Profile First Screen");

			if(Utilities.explicitWaitVisible(driver, kidsNameField, 20))
			{
		        kidsNameField.clear();
				kidsNameField.sendKeys("vinothifocus");
			}
			else
				BasePageV2.reportFail("Kids name field is not displayed in Create Profile screen");
			
			//Select Dob	
			if(Utilities.explicitWaitVisible(driver, dobProfileField, 20))
				dobProfileField.click();
			else
				BasePageV2.reportFail("Not able to select Date of birth in Create Profile screen");
			
			//Click ok in calendar pop up
			if(Utilities.explicitWaitVisible(driver, calendarOkButton, 20))
				calendarOkButton.click();
			else
				BasePageV2.reportFail("Not able to click Ok button on calendar pop up");
			
			Thread.sleep(1000);
			driver.pressKeyCode(AndroidKeyCode.BACK);
			//Click on next button in create profile screen
			if(Utilities.explicitWaitVisible(driver, createProfileScreenNextButton, 20))
				createProfileScreenNextButton.click();
			else
				BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
			
			////Click on next button(2) in create profile screen
			if(Utilities.explicitWaitVisible(driver, createProfileScreenNextButton, 20))
				createProfileScreenNextButton.click();
			else
				BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
			if(Utilities.explicitWaitVisible(driver, createProfileSelectFavoritesPreferencesScreen, 10))
			{	
				if(Utilities.explicitWaitVisible(driver, skillSetIcon, 20))
				{
				  skillSetIcon.click();
				}
			else
				BasePageV2.reportFail("Skills are not displayed in Create Profile Screen");
			test.log(LogStatus.INFO, "Creating profile by selecting 6 characters");
			List<WebElement> icons2 = characterSetIcons;
			//selecting 5 character icons
			if(Utilities.explicitWaitVisible(driver, characterSetIcon, 20))
			{
				
				for(int i=0;i<=3;i++)
				{
					try{
						icons2.get(i).click();
					}
					catch(Exception e)
					{
						BasePageV2.reportFail("Not able to click Character Icon - "+(i+1));
					}
				}
			}
			else
				BasePageV2.reportFail("Characters set are not displayed in Create Profile Screen");
			}
			else
				BasePageV2.reportFail("Not navigated to Favorites/Preference screen");
			
			
			if(Utilities.explicitWaitVisible(driver, createProfileScreenNextButton, 20))
				createProfileScreenNextButton.click();
			else
				BasePageV2.reportFail("Not able to click Next button in Create Profile Screen");
			
			//checking for Lets go button
			if(Utilities.explicitWaitVisible(driver, profileCompleteLetsGoButton, 20))
			{
			
			}
			else
				BasePageV2.reportFail("Error message is displayed / Not able to create Profile when clicking next button after selecting 6 characters in Favorites/Preferences screen");
			
	}
	
	
	
	
	

	



	

}
