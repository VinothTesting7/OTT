//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

package com.viacom.utils;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
/*************************************************************************************
 * Class        : ExtentManager
 * Purpose      : This class is used for configuring Extent Reports for the framework
 * Remarks      : none
 * Author       : Roja KC, Ifocus
 * Modifications:
 *                30 March 2017 - First created
 *                
 *
 **************************************************************************************/
public class ExtentManager {
	private static ExtentReports extent;
	
	private ExtentManager(){}

	public static ExtentReports getInstance() {
		if (extent == null) {
			Date d= new Date();
			String fileName=VootConstants.DEVICE_NAME+"_"+d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(VootConstants.REPORT_PATH+fileName, true, DisplayOrder.OLDEST_FIRST);

			// optional
		//	extent.config().documentTitle("Automation Report")
		//			.reportName("Regression").reportHeadline("");
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53").addSystemInfo(
					"Environment", "PROD");
		}
		return extent;
	}
}
