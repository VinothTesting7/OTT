package com.viacom.pagesVersion2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.viacom.mail.FileFilterDateIntervalUtils;

import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;
import com.viacom.utils.Xls_Reader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePageV2 {
	 public static  AndroidDriver driver;
	 public static  ExtentTest test;
	 public static TopMenuPageV2 menu;
	 
		@FindBy(xpath ="//android.widget.Button[@Contentdesc='Skip Ad']")
		public WebElement skipAd;
	public BasePageV2(AndroidDriver driver,ExtentTest test)
	{
		menu=new TopMenuPageV2(driver,test);
		PageFactory.initElements(new  AppiumFieldDecorator(driver),menu);
		this.driver=driver;
		this.test=test;
	}
	
	public static void reportPass(String passMsg){
		 test.log(LogStatus.PASS, passMsg);
		 takeScreenshot();
	}
	
	public static void reportFail(String failureMsg) throws Exception{
		
		test.log(LogStatus.FAIL, failureMsg);
		takeScreenshot();
		Assert.fail(failureMsg);
		 		 
	}
	
	
	public TopMenuPageV2 getMenu(){
		return menu;
		
	}
	
	public static void takeScreenshot(){
		// decide the file name
		Date d = new Date();		
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		String path=VootConstants.SCREENSHOT_PATH+screenshotFile;
		try {
		// take screenshot
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add screenshot to report
				test.log(LogStatus.INFO,"Snapshot below: "+test.addScreenCapture(addScreenshot()));
			
	}
	
	
	@SuppressWarnings("resource")
	 public static String addScreenshot() {
	     String encodedBase64 = null;
	     FileInputStream fileInputStreamReader = null;
	     try {
		     File scrFile = ((TakesScreenshot) BasePageV2.driver).getScreenshotAs(OutputType.FILE);
	         fileInputStreamReader = new FileInputStream(scrFile);
	         byte[] bytes = new byte[(int)scrFile.length()];
	         fileInputStreamReader.read(bytes);
	         encodedBase64 = new String(Base64.encodeBase64(bytes));
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return "data:image/png;base64,"+encodedBase64;
	 }
	public static void takeScreenshotvPlayback(){
		// decide the file name
		Date d = new Date();
		
		String screenshotFile=d.toString().replace(":", "_").replace(" ","_")+".png";
		String path=VootConstants.SCREENSHOT_PATH+screenshotFile;
		// take screenshot
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*//add screenshot to report
		test.log(LogStatus.INFO,"Snapshot below: ("+screenshotFile+")"+
				 test.addScreenCapture(path));*/
	}
	
	public static void logScreenshot(){
		
		String screenshotFolder=VootConstants.SCREENSHOT_PATH;
   	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        FileFilterDateIntervalUtils filter =
            new FileFilterDateIntervalUtils("2017-04-04", "2050-01-20");
        File folder =  new File(screenshotFolder);
        File files[] = folder.listFiles(filter);
       //date
      
        String fileName=files[files.length-1].getName();
        
        String filename=screenshotFolder+fileName;
		//add screenshot to report
		test.log(LogStatus.INFO,"Snapshot below: (fileName)"+
				 test.addScreenCapture(filename));
	}
	
	public static boolean chkForAd(WebElement videoViewer,WebElement skipAd ) throws InterruptedException
	{
		boolean Ad=false;
		Thread.sleep(5000);
		   for(int j=0;j<15;j++)
		     {
			   if(driver.findElements(By.name("Visit Advertiser")).size()>0 || (driver.findElements(By.id("com.tv.v18.viola:id/txt_ad")).size()>0))
				   Ad=true;
			   else if(j>1)
				   break; 
			   /*if(driver.findElementsById("com.tv.v18.viola:id/drag_view").size()>0)
				      videoViewer.click();*/	      
		      try{skipAd.click();}
		      catch(Exception e){}
			   
		     }
		   return Ad;
	}
	public static boolean imageCompare()
	{
		
		
		return true;
	}
	public static void smokeresults(String testcasename,int x,String status)
	{
		  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  Date date = new Date();
		  String x1=date.toString();
		Xls_Reader xls = new Xls_Reader(VootConstants.EXCEL_PATHV2);	
		// int x=xls.getRowCount("Smoke_Results");
		//  xls.setCellData("Smoke_Results", "Testcase Name", x, testcasename);
		  xls.setCellData("Smoke_Results", "Status", x, status);
		  xls.setCellData("Smoke_Results", "Time Stamp", x, x1);
		
	}
	public static void setResult(String testName,int videosCount,int adCount,int failureCount) throws EncryptedDocumentException, InvalidFormatException, IOException
	{		  	     
		Xls_Reader xl=new Xls_Reader(VootConstants.EXCEL_PATHV2);
	     int rowno=xl.getRowCount("Videoplayback")+1;
	     xl.setCellData("Videoplayback", "SectionName", rowno,testName);
	     xl.setCellData("Videoplayback", "Number of Videos Played", rowno,String.valueOf(videosCount));
	     xl.setCellData("Videoplayback", "Number of Ads Played", rowno,String.valueOf(adCount));
	     xl.setCellData("Videoplayback", "Number of Failures", rowno,String.valueOf(failureCount));
	     DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	     xl.setCellData("Videoplayback", "Date", rowno,String.valueOf(format.format(new Date())));		  
	}

	public static Map apiparams(int noparms,Xls_Reader xls,String apiname) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
	    //FileInputStream fis=new FileInputStream(VootConstants.EXCEL_PATHV2);
		 //Workbook wb=WorkbookFactory.create(fis);
		 //Sheet sheet = wb.getSheet("Api");   
		int rows=xls.getRowCount("Api");
		HashMap<String,String> map=new HashMap<String,String>();
		for(int rNum=2;rNum<=rows;rNum++)
		{
			String tcid=xls.getCellData("Api", "API Name", rNum);
			if(tcid.equals(apiname))
			{
				for(int i=rNum;i<noparms+rNum;i++)
				{
				String apikey=xls.getCellData("Api", "Key", i);
				String apivalue=xls.getCellData("Api", "Value", i);
		        map.put(apikey, apivalue);	
				}
			}			
		}
		///HashMap<String,String> map=new HashMap<String,String>();
		/*for(int i=1;i<=noparms;i++)
		 {		
	        String apikey = sheet.getRow(i).getCell(2).getStringCellValue();
	        String apivalue=sheet.getRow(i).getCell(3).getStringCellValue();	
	        map.put(apikey, apivalue);
		}*/
		return map;		
	}
	
	public static void swipetokidslogo(AndroidDriver driver) throws InterruptedException 
    {try{
   HomePageV2 homepagev2=new HomePageV2(driver,test);
     for(int i=0;i<=10;i++)
   {
    if(!Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 3))
		BasePageV2.swipetokidslogo(driver);			    		
    else
     if(Utilities.explicitWaitVisible(driver, homepagev2.kidslogoicon, 3))
      break;
   }
        }
         catch (Exception e) {
                e.printStackTrace();     
                }    
    }

}
