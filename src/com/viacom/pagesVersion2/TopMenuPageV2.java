package com.viacom.pagesVersion2;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;
import io.appium.java_client.android.AndroidDriver;

public class TopMenuPageV2{
	
	public AndroidDriver driver;
	public ExtentTest test;
	
	public TopMenuPageV2(AndroidDriver driver,ExtentTest test)
	{
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}
	
	

	
}
