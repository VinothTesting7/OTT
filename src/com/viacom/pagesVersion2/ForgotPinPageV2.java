package com.viacom.pagesVersion2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ForgotPinPageV2 extends BasePageV2 {
	public ForgotPinPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static String newpwdgmail = "";

	@FindBy(xpath = "//div[@class='aHl']/..//div//div[contains(.,'is your OTP to reset your parent PIN')]")
	public WebElement forgotPinText;

	@FindBy(xpath = "//input[@type='email']")
	public WebElement gmailtextField;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement gmailPwdtextField;

	@FindBy(xpath = "(//td[@class='yX xY ' and contains(.,'unread')]/..//span[contains(.,'Vootkids support')])[3]")
	public WebElement fgtpwdEmail;

	@FindBy(xpath = "//button[@id='proceedBtn' and contains(.,'Email')]")
	public WebElement emailpwdBtn;

	@FindBy(xpath = "//button[@id='backToSignin']")
	public WebElement backtoSigninBtn;

	@FindBy(xpath = "//span[contains(.,'Next')]")
	public WebElement nextBtnEmail;

	@FindBy(xpath = "//h3[contains(.,'Create New Password')]")
	public WebElement createnewpwdpage;

	@FindBy(xpath = "//input[@id='oldPwd']")
	public WebElement oldpwdfield;

	@FindBy(xpath = "//input[@id='newPwd']")
	public WebElement newpwdfield;

	@FindBy(xpath = "//input[@id='confirmPwd']")
	public WebElement confirmpwdfield;

	@FindBy(xpath = "//button[@id='updatePwd']")
	public WebElement savepwdbtn;

	@FindBy(xpath = "(//div[@gh='tm']//div[@class='asa'])[4]")
	public WebElement deleteMail;

	@FindBy(xpath = "//button[contains(@class,'mostPopular')]")
	public WebElement mostPopularBtn;

	@FindBy(xpath = "//button[contains(@class,'mostPopular ')]/../..//a[@title='Bajirao Mastani']")
	public WebElement movieTitle;

	@FindBy(id = "com.viacom18.vootkids:id/forgot_pin")
	public WebElement forgotpinLink;
	
	@FindBy(xpath = "//android.widget.TextView[@text='RESET PIN' or @text='Reset Pin' or @text='reset pin']")
	public WebElement resetPinPage;
	
	@FindBy(id = "com.viacom18.vootkids:id/btn_save_password")
	public WebElement savepwdButton;
	
	@FindBy(xpath = "//android.widget.TextView[@text='PIN Reset' or @text='Pin Reset' or @text='pin reset']")
	public WebElement pinresetConfirmPopup;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/body_dialog' and @text='Your PIN has been reset.']")
	public WebElement pinresetsuccessMsg;
	
	@FindBy(id = "com.viacom18.vootkids:id/btn_close_dialog")
	public WebElement popupClose;
	
	
	
	

	
	
	// button[contains(@class,'mtextostPopular')]

	// input[@id='oldPwd']

	// input[@id='newPwd']

	// input[@id='confirmPwd']

	// button[@id='updatePwd']

	public static void logintogmailandfetchnewPwd(String email, String Pwd) throws Exception {
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
	}
}
