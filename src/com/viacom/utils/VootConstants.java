package com.viacom.utils;

import java.net.InetAddress;

/*************************************************************************************
 * Class        : GlobalVariables
 * Purpose      : This class is used for storing all the global constants
 * Remarks      : none
 * Author       : Roja KC, Ifocus
 * Modifications:
 *                28 March 2017 - First created
 
 **************************************************************************************/
public class VootConstants {
	
	//Upgrade
	public static final String APK_PATH_new=System.getProperty("user.dir")+"\\apk\\VootKids_0.1.39_23_RC_GE.apk";
	public static final String APK_PATH_old=System.getProperty("user.dir")+"\\apk\\VootKids_0.1.37_21_RC_GE.apk";
	public static final String LATEST_BUILD="0.1.39";
	public static final String OLD_BUILD="0.1.37";
	
	public static String ipAddress="";
	
	public static final String EXCEL_PATH = ".//data//Testdata.xlsx";
	public static final String EXCEL_PATHV2 = ".//data//TestDataV2.xlsx";
	
	public static final String EXCEL_PATHTREG = ".//data//Voot Kids App - Regression Checklist.xlsx";
	public static final String EXCEL_PATHTTC = ".//data//VK_S1 to S16_Test Cases.xlsx";
	public static final String sheet= "VOOT_USERS";	
	//public static final String ENVIRONMENT="PreProduction";
	public static String ENVIRONMENT="Production";
	public static final String BROWSER_NAME="Android";
	public static final String PLATFORM_NAME="Android";
	
	//Change every build
	public static final String DEVICE_TYPE="Mobile";
	public static final String DEVICE_NAME="Samsung S4";
	public static final String UDID="4d0026acf211227f";
	public static final String DEVICE_VERSION="5.0.1";
	public static final String HUB_URL="http://127.0.0.4:4724/wd/hub";
	
	
	public static final String APK_PATH=System.getProperty("user.dir")+"\\apk\\voot_1.6.114_playkit.apk";
//	public static final String APK_PATH_new=System.getProperty("user.dir")+"\\apk\\VootKids_0.1.30_14_RC_GE.apk";
//	public static final String APK_PATH_old=System.getProperty("user.dir")+"\\apk\\VootKids_0.1.29_13_RC_GE.apk";
//	public static final String LATEST_BUILD="0.1.30";
//	public static final String OLD_BUILD="0.1.29";
	
	public static final String XLS_PATH = System.getProperty("user.dir")+"//data//TestData.xlsx";
	public static String Version;
	public static final String REPORT_PATH="C:\\Voot_Kids_report\\";
	public static final String SCREENSHOT_PATH = "C:\\Voot_Kids_screenshots\\";
	public static final String REPORT_EMAIL_SUBJECT="Automation test reports-V2";
	/*public static final String REPORT_PATH=System.getProperty("user.dir")+"\\report\\";
	public static final String SCREENSHOT_PATH = System.getProperty("user.dir")+"\\screenshots\\";*/
	public static final String SIGN_IN="//android.widget.TextView[@text='Sign In']";
	public static final String CREATE_ACCOUNT="//android.widget.TextView[@text='Create a new account.']";
	public static final String CHOOSE_ACCOUNT="//android.widget.TextView[@text='Choose an account']";
	public static final String FACEBOOK_LINK="//android.view.View[@content-desc='facebook Link']";
	
	public static final String MENU="//android.widget.ImageView[@resource-id='com.viacom18.vootkids:id/hamburger_menu']";
	//public static final String url="http://dayanand8:4jjkbaxHEK9SfZwceHtz@hub-cloud.browserstack.com/wd/hub";
	public static final String tabAPI="https://api.vootkids.com/app/ui/v1/tabs.json?os=android&deviceType=phone";
	public static final String pin="1111";
	
	public static String charleslogsName ="";
	public static String charlesName ="";
	public static String ipAdress ="";
	public static InetAddress localhost; 
	public static String filePath="";
	public static String filePathxml ="";
	public static String filePathlogs="";
	//public static String charlesNameVK ="";
	//public static String charleslogsNameVK ="";
	//public static String filePathxmlVK ="";
	//public static String filePathlogsVK="";
	
	
	
	
}
