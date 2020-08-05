package com.viacom.smoketestscripts;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.LaunchPageV2;
import com.viacom.utils.ExtentManager;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTestV2 {
	public AndroidDriver driver;

	public ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	protected Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);
	public int networkCount = 0;
	public int sessionCount = 0;
  @BeforeSuite
  public void suite()
  {
	  
  }

	@BeforeMethod
	public void setup() throws InterruptedException {
		System.out.println("BeforeMethod()...");
	 	com.viacom.utils.Utilities.startAppiumServer();
	}


	@AfterMethod
	public void tearDown() throws Exception {
		System.out.println("AfterMethod()");

		if (rep != null) {
			rep.endTest(test);
			System.out.println("rep...");
			rep.flush();
		}

		try {
			if (driver != null)
				/*driver.resetApp();
			System.out.println("Reseted..");*/
				driver.quit();

		} finally {

			com.viacom.utils.Utilities.stopAppiumServer();
			System.out.println("server stopped");
		}		

	}
	
	

	
	public void launchApp() throws Exception
	{
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setCapability("BROWSER_NAME", VootConstants.BROWSER_NAME);
		 capabilities.setCapability("VERSION", VootConstants.DEVICE_VERSION);
	     capabilities.setCapability("deviceName", VootConstants.DEVICE_NAME);
	   // capabilities.setCapability("automationName", "uiautomator2");
		 capabilities.setCapability("platformName", VootConstants.PLATFORM_NAME);
			//capabilities.setCapability("udid","4200a46cb468a267" );//Samsung On5 6.0.1
			//capabilities.setCapability("udid","9201108548bd43fd" );//Samsung Galaxy J2 5.1.1
		    //capabilities.setCapability("udid","3300236b162fa2f1" );//Samsung Galaxy J7 6.0.1
		    //capabilities.setCapability("udid","32081ef3775181bf" );//Samsung Galaxy S5 (6.0.1)
		   //capabilities.setCapability("udid","421042efc4159421" );//Samsung J5 Prime(8.0.0)
			//capabilities.setCapability("udid","52005532ec35357b" );//Samsung Galaxy J6(8.0.0)
			//capabilities.setCapability("udid","bd12d3c7" );//Vivo 1724 8.1.0
		    //capabilities.setCapability("udid","372beb74" );//Vivo 1606(6.0.1)
			//capabilities.setCapability("udid","105144ff" );//vivo
//			capabilities.setCapability("udid","5790dc2d" );//vivo 5.1.1		
//			capabilities.setCapability("udid","d45f2cd8");//One Plus A0001 6.0.1
			//capabilities.setCapability("udid","ZY322CF54R");//Moto G5 S 7.1.1
			//capabilities.setCapability("udid","ZY32279CMM");//Moto G5 S Plus 7.1.1	
		    //capabilities.setCapability("udid","ZW2225DTSJ");//Moto E4 7.1.1
			//capabilities.setCapability("udid","EBAZCY00R491" );//Asus(5.0)
		    //capabilities.setCapability("udid","H5AXJA006633D2P" );//Asus Zenfone Max (6.0.1)
			//capabilities.setCapability("udid","b953954" );//Oppo A3S (8.1.0)
		    //capabilities.setCapability("udid","YTT4KZGQJF8SB6V8" );//Oppo F5 7.1.1 
		    capabilities.setCapability("udid","4d0026acf211227f" );//Oppo F5 7.1.1
//		    capabilities.setCapability("udid","03496e5f438064ba" );//Nexus 5 (6.0.1)
//		   capabilities.setCapability("udid","ENU7N15A17013920" );//Nexus  6P (8.1.0) 
		   //capabilities.setCapability("udid","878ff410" );//lenovo tab
//		    capabilities.setCapability("udid","DRGID18101405207" );//Nokia  6.1 Plus (9.0.0)
		    //capabilities.setCapability("udid","NB1GAD1810601997" );//Nokia 8 (8.1.0)
		    //capabilities.setCapability("udid","E6HM7SBI6LAUVCEU" );//Lenovo K3 Note 6.0
		    //capabilities.setCapability("udid","4SR8Z5Y5JBEA9HLV" );//Lenovo K4 Note 5.1
		 //capabilities.setCapability("udid","KZDEAEDY99999999" );//GIONEE 5.1.0
		   //capabilities.setCapability("udid","YT9114QHMR" );//Sony Xperia C3 5.1.1
		 //capabilities.setCapability("udid","a651f2ed" );//Lenovo A6010 5.0.2
//		 capabilities.setCapability("udid","d173bebb" );//One Plus 5.1.1
		    //capabilities.setCapability("udid","4d0026acf211227f" );//Samsung Galaxy S4 (5.0.1)
		    //capabilities.setCapability("udid","LGH818d416e397" );//G4 LG 6.0
		    //capabilities.setCapability("udid","d173bebb" );//One A2 5.1.1
		
		  //Tablets
		    //capabilities.setCapability("udid","878ff410" );//Lenovo TB-X304L (7.1.1) 
		    //capabilities.setCapability("udid","dff35a8a");//Samsung SM-P605V (5.1.1) 
		    //capabilities.setCapability("udid","HA0W3P9F");//Lenovo TB-X103F (6.0.1) 

		capabilities.setCapability("unlockType", "pin");
		capabilities.setCapability("unlockKey", "1111");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		capabilities.setCapability("newCommandTimeout", 60 * 6);
		
		if (VootConstants.ENVIRONMENT.equalsIgnoreCase("PreProduction")) {
			File app = new File(VootConstants.APK_PATH);
			capabilities.setCapability("app", app.getAbsolutePath());
		} else if (VootConstants.ENVIRONMENT.equalsIgnoreCase("Production")) {
			capabilities.setCapability("appPackage", "com.viacom18.vootkids");
		    capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKHomeActivity");
			//capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKbetaSplashActivity");
			System.out.println("Production,...");
			capabilities.setCapability("autoDismissAlerts", true);	
			capabilities.setCapability("noReset","true");
			  capabilities.setCapability("fullReset", "false");
		}
		try {
			
			driver = new AndroidDriver(new URL(VootConstants.HUB_URL), capabilities);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e.getMessage());
			BasePageV2.reportFail("Application did not launch as Appium session not created");

		}
			/*try {

				if (driver.findElementsByName("in-app message image").size() > 0) {
					driver.findElementByName("in-app message close").click();
				}
				if(driver==null)
					BasePageV2.reportFail("Application did not launch unexpectedly");
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();

			} catch (Exception e) {}*/
/*		 
			for(int i=0;i<10;i++)
			{
				try{
					driver.findElementById(".v18.viola:id/gateway_btn_view").click();
					break;
				}catch(Exception e)
				{
					Thread.sleep(2000);
				}
			}*/

		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		
		if (Utilities.explicitWaitVisibleNew(driver, launchPageV2.CreateProfile, 20))
		{
			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25))
			{
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
			}
			else 
				HomePageV2.reportFail("Kids name Text field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
				launchPageV2.DOB.click();
			else HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
				launchPageV2.DOBSelect.click();
			else
			{
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try
			{
				driver.hideKeyboard();
			} catch (Exception e)
			{
			}
			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
				test.log(LogStatus.INFO, "Select Profile image page is displayed");
			else BasePageV2.reportFail("Select Profile image page is not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				try
				{
					WebElement element = driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[" + i
									+ "]"));
					if (Utilities.explicitWaitVisible(driver, element, 10))
					{
						element.click();
					}
					else
					{
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}
				} catch (Exception e)
				{
					//test.log(logStatus, details);
				}

			}
			try
			{
				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
					Skills1.click();
				else
				{
					HomePageV2.reportFail("Skills not displayed");
					BasePageV2.takeScreenshot();
				}
			} catch (Exception e)
			{

			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 60))
				launchPageV2.letsGo.click();
			else BasePageV2.reportFail("Let's Go Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else break;
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
				test.log(LogStatus.INFO, "Home Page is displayed");
			else BasePageV2.reportFail("Home page is not displayed");
		}
			//signup();
		}
	
	
	
	
	
	public void launchAppUpgrade() throws Exception
	{
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setCapability("BROWSER_NAME", VootConstants.BROWSER_NAME);
		 capabilities.setCapability("VERSION", VootConstants.DEVICE_VERSION);
	     capabilities.setCapability("deviceName", VootConstants.DEVICE_NAME);
	   // capabilities.setCapability("automationName", "uiautomator2");
		 capabilities.setCapability("platformName", VootConstants.PLATFORM_NAME);
		 //capabilities.setCapability("udid","ZY322CF54R" );//moto g5
         capabilities.setCapability("udid","ZY32279CMM");//moto e4
		 //capabilities.setCapability("udid","03496e5f438064ba" );//Nexus_5(6.0.1)
		 //capabilities.setCapability("udid","32081ef3775181bf" );//Samsung Galaxy S5 (6.0.1)
		 //capabilities.setCapability("udid","YTT4KZGQJF8SB6V8" );//oppo F5 7.1.1
		 //capabilities.setCapability("udid","b953954" );//Oppo A3S 8.1.0
		 //capabilities.setCapability("udid","3300236b162fa2f1" );//Samsung J7 (6.0.1)
		 //capabilities.setCapability("udid","ZY32279CMM" );//Moto G5S Plus (7.1.1)
		 //capabilities.setCapability("udid","ZY322CF54R" );//Moto G5S (7.1.1)
		 //capabilities.setCapability("udid","DRGID18101405207" );//Nokia 6.1 Plus (9.0)
	     //capabilities.setCapability("udid","4200a46cb468a267" );//Samsung Galaxy On5 (6.0.1)
		 //capabilities.setCapability("udid","H5AXJA006633D2P" );//ASUS_Z010D (6.0.1)
		 //capabilities.setCapability("udid","E6HM7SBI6LAUVCEU" );//Lenovo K3 Note (6.0.0)
		 //capabilities.setCapability("udid","878ff410" );//Lenovo TB-X304L (7.1.1) 
		 
		 
		 
			capabilities.setCapability("unlockType", "pin");
			capabilities.setCapability("unlockKey", "1111");
		 capabilities.setCapability("newCommandTimeout", 60 * 5);
		 VootConstants.ENVIRONMENT="PreProduction";
		if (VootConstants.ENVIRONMENT.equalsIgnoreCase("PreProduction")) {
			File app = new File(VootConstants.APK_PATH_old);
			capabilities.setCapability("app", app.getAbsolutePath());
		} else if (VootConstants.ENVIRONMENT.equalsIgnoreCase("Production")) {
			capabilities.setCapability("appPackage", "com.viacom18.vootkids");
		//	capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKHomeActivity");
			capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKbetaSplashActivity");
			System.out.println("Production,...");
			capabilities.setCapability("autoDismissAlerts", true);	
			capabilities.setCapability("noReset","true");
			  capabilities.setCapability("fullReset", "false");
		}
		try {
			
			driver = new AndroidDriver(new URL(VootConstants.HUB_URL), capabilities);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e.getMessage());
			BasePageV2.reportFail("Application did not launch as Appium session not created");

		}
			try {

				/*if (driver.findElementsByName("in-app message image").size() > 0) {
					driver.findElementByName("in-app message close").click();
				}*/
				if(driver==null)
					BasePageV2.reportFail("Application did not launch unexpectedly");
				/*driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();*/

			} catch (Exception e) {}
		 
			/*for(int i=0;i<10;i++)
			{
				try{
					driver.findElementById(".v18.viola:id/gateway_btn_view").click();
					break;
				}catch(Exception e)
				{
					Thread.sleep(2000);
				}
			}
*/
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		/*HomePageV2 homepagev2=new HomePageV2(driver,test);
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		
		if (Utilities.explicitWaitVisible(driver, launchPageV2.CreateProfile, 2))
		{
			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25))
			{
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
			}
			else 
				HomePageV2.reportFail("Kids name Text field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
				launchPageV2.DOB.click();
			else HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
				launchPageV2.DOBSelect.click();
			else
			{
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try
			{
				driver.hideKeyboard();
			} catch (Exception e)
			{
			}
			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
				test.log(LogStatus.INFO, "Select Profile image page is displayed");
			else BasePageV2.reportFail("Select Profile image page is not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				try
				{
					WebElement element = driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[" + i
									+ "]"));
					if (Utilities.explicitWaitVisible(driver, element, 10))
					{
						element.click();
					}
					else
					{
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}
				} catch (Exception e)
				{
					//test.log(logStatus, details);
				}

			}
			try
			{
				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
					Skills1.click();
				else
				{
					HomePageV2.reportFail("Skills not displayed");
					BasePageV2.takeScreenshot();
				}
			} catch (Exception e)
			{

			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 60))
				launchPageV2.letsGo.click();
			else BasePageV2.reportFail("Let's Go Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else break;
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
				test.log(LogStatus.INFO, "Home Page is displayed");
			else BasePageV2.reportFail("Home page is not displayed");
			//signup();
		}*/
		
	

}
	
	
	
	
	
/*	public void launchApp() throws Exception    // For Upgrade TC's This Method
	{
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setCapability("BROWSER_NAME", VootConstants.BROWSER_NAME);
		 capabilities.setCapability("VERSION", VootConstants.DEVICE_VERSION);
	     capabilities.setCapability("deviceName", VootConstants.DEVICE_NAME);
	   // capabilities.setCapability("automationName", "uiautomator2");
		 capabilities.setCapability("platformName", VootConstants.PLATFORM_NAME);
		 //capabilities.setCapability("udid","ZY322CF54R" );//moto g5
         capabilities.setCapability("udid","ZY32279CMM");//moto e4
		 //capabilities.setCapability("udid","03496e5f438064ba" );//Nexus_5(6.0.1)
		 //capabilities.setCapability("udid","32081ef3775181bf" );//Samsung Galaxy S5 (6.0.1)
		 //capabilities.setCapability("udid","YTT4KZGQJF8SB6V8" );//oppo F5 7.1.1
		 //capabilities.setCapability("udid","b953954" );//Oppo A3S 8.1.0
		 //capabilities.setCapability("udid","3300236b162fa2f1" );//Samsung J7 (6.0.1)
//		 capabilities.setCapability("udid","ZY32279CMM" );//Moto G5S Plus (7.1.1)
		 //capabilities.setCapability("udid","ZY322CF54R" );//Moto G5S (7.1.1)
		 //capabilities.setCapability("udid","DRGID18101405207" );//Nokia 6.1 Plus (9.0)
	     //capabilities.setCapability("udid","4200a46cb468a267" );//Samsung Galaxy On5 (6.0.1)
		 //capabilities.setCapability("udid","H5AXJA006633D2P" );//ASUS_Z010D (6.0.1)
		 //capabilities.setCapability("udid","E6HM7SBI6LAUVCEU" );//Lenovo K3 Note (6.0.0)
		 //capabilities.setCapability("udid","878ff410" );//Lenovo TB-X304L (7.1.1) 
		 
		 
		 
			capabilities.setCapability("unlockType", "pin");
			capabilities.setCapability("unlockKey", "1111");
		 capabilities.setCapability("newCommandTimeout", 60 * 5);

		if (VootConstants.ENVIRONMENT.equalsIgnoreCase("PreProduction")) {
			File app = new File(VootConstants.APK_PATH_new);
			capabilities.setCapability("app", app.getAbsolutePath());
		} else if (VootConstants.ENVIRONMENT.equalsIgnoreCase("Production")) {
			capabilities.setCapability("appPackage", "com.viacom18.vootkids");
		//	capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKHomeActivity");
			capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKbetaSplashActivity");
			System.out.println("Production,...");
			capabilities.setCapability("autoDismissAlerts", true);	
			capabilities.setCapability("noReset","true");
			  capabilities.setCapability("fullReset", "false");
		}
		try {
			
			driver = new AndroidDriver(new URL(VootConstants.HUB_URL), capabilities);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e.getMessage());
			BasePageV2.reportFail("Application did not launch as Appium session not created");

		}
			try {

				if (driver.findElementsByName("in-app message image").size() > 0) {
					driver.findElementByName("in-app message close").click();
				}
				if(driver==null)
					BasePageV2.reportFail("Application did not launch unexpectedly");
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();

			} catch (Exception e) {}
		 
			for(int i=0;i<10;i++)
			{
				try{
					driver.findElementById(".v18.viola:id/gateway_btn_view").click();
					break;
				}catch(Exception e)
				{
					Thread.sleep(2000);
				}
			}
       
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		
		if (Utilities.explicitWaitVisible(driver, launchPageV2.CreateProfile, 15))
		{
			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25))
			{
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
			}
			else 
				HomePageV2.reportFail("Kids name Text field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
				launchPageV2.DOB.click();
			else HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
				launchPageV2.DOBSelect.click();
			else
			{
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try
			{
				driver.hideKeyboard();
			} catch (Exception e)
			{
			}
			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
				test.log(LogStatus.INFO, "Select Profile image page is displayed");
			else BasePageV2.reportFail("Select Profile image page is not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				try
				{
					WebElement element = driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[" + i
									+ "]"));
					if (Utilities.explicitWaitVisible(driver, element, 10))
					{
						element.click();
					}
					else
					{
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}
				} catch (Exception e)
				{
					//test.log(logStatus, details);
				}

			}
			try
			{
				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
					Skills1.click();
				else
				{
					HomePageV2.reportFail("Skills not displayed");
					BasePageV2.takeScreenshot();
				}
			} catch (Exception e)
			{

			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 60))
				launchPageV2.letsGo.click();
			else BasePageV2.reportFail("Let's Go Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else break;
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
				test.log(LogStatus.INFO, "Home Page is displayed");
			else BasePageV2.reportFail("Home page is not displayed");
		}
			//signup();
		}*/
	
	
	
	
/*	public void launchAppUpgrade() throws Exception
	{
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 capabilities.setCapability("BROWSER_NAME", VootConstants.BROWSER_NAME);
		 capabilities.setCapability("VERSION", VootConstants.DEVICE_VERSION);
	     capabilities.setCapability("deviceName", VootConstants.DEVICE_NAME);
	   // capabilities.setCapability("automationName", "uiautomator2");
		 capabilities.setCapability("platformName", VootConstants.PLATFORM_NAME);
		 //capabilities.setCapability("udid","ZY322CF54R" );//moto g5
         capabilities.setCapability("udid","ZY32279CMM");//moto e4
		 //capabilities.setCapability("udid","03496e5f438064ba" );//Nexus_5(6.0.1)
		 //capabilities.setCapability("udid","32081ef3775181bf" );//Samsung Galaxy S5 (6.0.1)
		 //capabilities.setCapability("udid","YTT4KZGQJF8SB6V8" );//oppo F5 7.1.1
		 //capabilities.setCapability("udid","b953954" );//Oppo A3S 8.1.0
		 //capabilities.setCapability("udid","3300236b162fa2f1" );//Samsung J7 (6.0.1)
		 //capabilities.setCapability("udid","ZY32279CMM" );//Moto G5S Plus (7.1.1)
		 //capabilities.setCapability("udid","ZY322CF54R" );//Moto G5S (7.1.1)
		 //capabilities.setCapability("udid","DRGID18101405207" );//Nokia 6.1 Plus (9.0)
	     //capabilities.setCapability("udid","4200a46cb468a267" );//Samsung Galaxy On5 (6.0.1)
		 //capabilities.setCapability("udid","H5AXJA006633D2P" );//ASUS_Z010D (6.0.1)
		 //capabilities.setCapability("udid","E6HM7SBI6LAUVCEU" );//Lenovo K3 Note (6.0.0)
		 //capabilities.setCapability("udid","878ff410" );//Lenovo TB-X304L (7.1.1) 
		 
		 
		 
			capabilities.setCapability("unlockType", "pin");
			capabilities.setCapability("unlockKey", "1111");
		 capabilities.setCapability("newCommandTimeout", 60 * 5);
		 VootConstants.ENVIRONMENT="PreProduction";
		if (VootConstants.ENVIRONMENT.equalsIgnoreCase("PreProduction")) {
			File app = new File(VootConstants.APK_PATH_old);
			capabilities.setCapability("app", app.getAbsolutePath());
		} else if (VootConstants.ENVIRONMENT.equalsIgnoreCase("Production")) {
			capabilities.setCapability("appPackage", "com.viacom18.vootkids");
		//	capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKHomeActivity");
			capabilities.setCapability("appActivity", "com.tv.vootkids.ui.home.VKbetaSplashActivity");
			System.out.println("Production,...");
			capabilities.setCapability("autoDismissAlerts", true);	
			capabilities.setCapability("noReset","true");
			  capabilities.setCapability("fullReset", "false");
		}
		try {
			
			driver = new AndroidDriver(new URL(VootConstants.HUB_URL), capabilities);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, e.getMessage());
			BasePageV2.reportFail("Application did not launch as Appium session not created");

		}
			try {

				if (driver.findElementsByName("in-app message image").size() > 0) {
					driver.findElementByName("in-app message close").click();
				}
				if(driver==null)
					BasePageV2.reportFail("Application did not launch unexpectedly");
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();
				driver.findElementById("com.android.packageinstaller:id/permission_deny_button").click();

			} catch (Exception e) {}
		 
			for(int i=0;i<10;i++)
			{
				try{
					driver.findElementById(".v18.viola:id/gateway_btn_view").click();
					break;
				}catch(Exception e)
				{
					Thread.sleep(2000);
				}
			}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		HomePageV2 homepagev2=new HomePageV2(driver,test);
		LaunchPageV2 launchPageV2=new LaunchPageV2(driver,test);
		
		if (Utilities.explicitWaitVisible(driver, launchPageV2.CreateProfile, 2))
		{
			if (Utilities.explicitWaitVisible(driver, launchPageV2.kidsName, 25))
			{
				launchPageV2.kidsName.clear();
				launchPageV2.kidsName.sendKeys(Utilities.generateRandomName());
			}
			else 
				HomePageV2.reportFail("Kids name Text field not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOB, 5))
				launchPageV2.DOB.click();
			else HomePageV2.reportFail("DOB Field not displayed");
			BasePageV2.takeScreenshot();

			if (Utilities.explicitWaitVisible(driver, launchPageV2.DOBSelect, 5))
				launchPageV2.DOBSelect.click();
			else
			{
				HomePageV2.reportFail("Date selector not displayed");
				BasePageV2.takeScreenshot();
			}

			try
			{
				driver.hideKeyboard();
			} catch (Exception e)
			{
			}
			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.createBuddyPage, 1))
				test.log(LogStatus.INFO, "Select Profile image page is displayed");
			else BasePageV2.reportFail("Select Profile image page is not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				try
				{
					WebElement element = driver.findElement(By.xpath(
							"(//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/character_icon'])[" + i
									+ "]"));
					if (Utilities.explicitWaitVisible(driver, element, 10))
					{
						element.click();
					}
					else
					{
						HomePageV2.reportFail("Kids Characters not displayed");
						BasePageV2.takeScreenshot();
					}
				} catch (Exception e)
				{
					//test.log(logStatus, details);
				}

			}
			try
			{
				WebElement Skills1 = driver.findElement(By.xpath("//android.widget.TextView[@text='Story']/.."));
				if (Utilities.explicitWaitVisible(driver, Skills1, 5))
					Skills1.click();
				else
				{
					HomePageV2.reportFail("Skills not displayed");
					BasePageV2.takeScreenshot();
				}
			} catch (Exception e)
			{

			}

			if (Utilities.explicitWaitVisible(driver, launchPageV2.next, 1))
				launchPageV2.next.click();
			else BasePageV2.reportFail("Next Button not displayed");

			if (Utilities.explicitWaitVisible(driver, launchPageV2.letsGo, 60))
				launchPageV2.letsGo.click();
			else BasePageV2.reportFail("Let's Go Button not displayed");

			for (int i = 1; i <= 5; i++)
			{
				if (Utilities.explicitWaitVisible(driver, homepagev2.freshAppNotificationCancel, 5))
					homepagev2.freshAppNotificationCancel.click();
				else break;
			}

			if (Utilities.explicitWaitVisible(driver, homepagev2.profilepic, 25))
				test.log(LogStatus.INFO, "Home Page is displayed");
			else BasePageV2.reportFail("Home page is not displayed");
			//signup();
		}
		
	

}*/
	
	
	@AfterSuite
	public void close() throws Exception {
		// SendMail.main(null);
		System.out.println("after suite");
		

	}

}
