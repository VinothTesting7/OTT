package com.viacom.pagesVersion2;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class SignupandLoginPageV2 extends BasePageV2 {
	public SignupandLoginPageV2(AndroidDriver driver,ExtentTest test)
	{
		super(driver,test);
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='Next' or @text='NEXT']")
    public WebElement signUpNextBtn;
	
    @FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/privacyPolicy']")
    public WebElement signUpPrivacyPolicy;
    
    @FindBy(xpath="//android.widget.TextView[@text='Privacy Policy' and @resource-id='com.viacom18.vootkids:id/toolbar_title']")
    public WebElement signUpPrivacyPolicyTitle;
	
	@FindBy(id="com.viacom18.vootkids:id/btn_fb_login")
	public WebElement facebookButton;
	    @FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index=1]")
	    public WebElement proicon1;
	    
	    @FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']/parent::android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/parent_layout' and @index=0]")
	    public WebElement proicon2;
		@FindBy(id="com.google.android.gms:id/container")
		//	@FindBy(xpath = "//android.widget.CheckedTextView[@text='Add account']")
			public WebElement addAccountRadioButton;
	   @FindBy(xpath="(//android.widget.TextView[@text='Login']/..)/child::android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/animated_background']")
	    public WebElement loginbtn;
	
	    @FindBy(xpath="(//android.widget.TextView[@text='Create new']/..)/child::android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']")
	    public WebElement createProBtn;

	    @FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/list_icon']")
	    public WebElement proicon;
	
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/join_for_30_days_to_handle_click']/preceding-sibling::android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/animated_background']")
		public WebElement joinFreefor30Days;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_otp']")
		public WebElement otptext;
	
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_password']")
		public WebElement passwordtext;
		
		@FindBy(xpath="//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/edit_text_otp']")
		public WebElement otptextField;
		
		@FindBy(xpath="//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/password_edit_text']")
		public WebElement passwordtextField;
		@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/subscription_header']//android.widget.TextView[@text='Explore']")
		public WebElement progressPathExplore;
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/btn_login_existing_user' and @text='Login']")
		public WebElement alreadyhaveanAccountLoginLink;
		@FindBy(xpath="//android.widget.TextView[contains(@text,'Already have an account')]")
		public WebElement alreadyhaveanAccountText;

		@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/subscription_header']//android.widget.TextView[@text='Sign up']")
		public WebElement progressPathSignup;
		
		@FindBy(id="com.viacom18.vootkids:id/privacyPolicy")
		public WebElement privacyPolicyLink;
		@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/subscription_header']//android.widget.TextView[@text='Choose Plan']")
		public WebElement progressPathChoosePlan;
		
		@FindBy(xpath="//android.view.ViewGroup[@resource-id='com.viacom18.vootkids:id/subscription_header']//android.view.View[contains(@resource-id,'com.viacom18.vootkids:id/view')]")
		public WebElement progressPathline;
		@FindBy(xpath="//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/text_input_password_toggle' and @content-desc='* Password']")
		public WebElement eyeinPasswordtextField;
		
		@FindBy(xpath="//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/edit_text_phone_number']")
		public WebElement mobileNoTextField;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/label_phone_number']")
		public WebElement mobileNoText;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/send_otp']")
		public WebElement sendOtpLink;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/text_enter_details']")
		public WebElement enterYourDetails;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text']")
		public WebElement pagetitlesignUp;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/btn_login_existing_user']")
		public WebElement loginLinkinSignupPage;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/btn_login_existing_user']/..")
		public WebElement alreadyhaveanAccountandlogin;
		
		@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/btn_google_login']")
		//@FindBy(id="com.viacom18.vootkids:id/btn_google_login")
		public WebElement googleButton;
		
		@FindBy(id= "com.google.android.gms:id/main_title")
		public WebElement chooseanAccount;
		
		@FindBy(id= "com.google.android.gms:id/account_name")
		public List<WebElement> googleAccounts;
		
		@FindBy(xpath="//android.widget.Button[@text='OK']")
		public WebElement okButton;
		
	
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text']")
		public WebElement PagetitlelogininLoginPage;
		
		@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/button_disabled']")
		public WebElement nextbuttonwithoutfillDatainsignUp;
		
		@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/animated_background']")
		public WebElement nextbuttonwithfillDatainsignUp;
		
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/header_text']")
		public WebElement BackButtoninSignupPage;
		
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/error_message_phone_number']")
		public WebElement error_message_phone_number;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/btn_new_member_sign_up']")
		public WebElement  signuplinkLoginPage;
		
		@FindBy(xpath="//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/edit_text_email_id']")
		public WebElement  mobilefieldinLoginPage;
		
		@FindBy(xpath="//android.widget.EditText[@resource-id='com.viacom18.vootkids:id/password_edit_text']")
		public WebElement passwordfiledinLoginPage;
		
		@FindBy(xpath="//android.widget.ImageButton[@resource-id='com.viacom18.vootkids:id/text_input_password_toggle']")
		public WebElement eyeinpasswordfieldinLoginPage;
		
		@FindBy(xpath="//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/label_forgot_password']")
		public WebElement forgotpasswordinLoginPage;
				
		@FindBy(xpath="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/logo_imageview']")
		public WebElement vootkidsLogoinWelcomescreen;
				
		@FindBy(xpath="//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_login_existing_user']")
		public WebElement alreadyhaveanAccountandLogininWelcomScreen;
				
		@FindBy(xpath="//android.widget.LinearLayout[@resource-id='((//android.widget.LinearLayout[@resource-id='com.viacom18.vootkids:id/btn_login_existing_user'])//android.widget.TextView)[2]']")
		public WebElement logininLinkinWelcomScreen;
		@FindBy(name = "Email or phone")
		public WebElement addAccountEmail;
		
		@FindBy(xpath="//android.widget.TextView[@text='Email or phone']")
		public WebElement emailField;
		
		@FindBy(xpath="//android.widget.TextView[@text='Password']")
		public WebElement passwordField;
		
		@FindBy(name = "Password")
		public WebElement addAccountPassword;
		
		//@FindBy(id = "identifierNext")
		@FindBy(xpath="//android.widget.Button[@text='NEXT']")
		public WebElement emailFieldNextButton;
		
		@FindBy(xpath="//android.widget.Button[@text='NEXT']")
		public WebElement passwordFieldNextButton;
		
		@FindBy(name = "NEXT")
		public WebElement emailNextButton;
		
		@FindBy(name = "NEXT")
		public WebElement passwordNextButton;
		
		@FindBy(name = "ACCEPT")
		public WebElement acceptButton;
		
		@FindBy(id = "com.google.android.gms:id/accept_button")
		public WebElement allowBtn;
		
		@FindBy(name = "Finish Registration")
		public WebElement finishRegistration;
		

		@FindBy(xpath= "//android.widget.EditText[@content-desc='Username']")
		public WebElement fbEmail;
		 
		@FindBy(xpath= "//android.widget.EditText[@content-desc='Password']")
		public WebElement fbPassword;
		  
		@FindBy(xpath= "//android.view.ViewGroup[@content-desc='Login click']")
		public WebElement fbLogin;
		  
		  @FindBy(name= "OK ")
		  public WebElement confirmLogin;

		  @FindBy(xpath = "//android.widget.Button[@resource-id='com.facebook.katana:id/(name removed)' and contains(@text,'Another Account')]")
		  public WebElement loginToAnotherAccount;
		//generate mobile number
		public  String generateMobileNumber() 
	     {
			   String strRandom = "";
			   String strNumbers = "0123456789";
			   Random rnd = new Random();
			   StringBuilder strRandomNumber = new StringBuilder(9);
			    for (int i = 0; i<9; i++)
			   {
			    strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));}
			    strRandom = strRandomNumber.toString();  
				String mobileNumber = "9"+strRandom;
				return mobileNumber;
				
		 }
		
		
		
}	
	