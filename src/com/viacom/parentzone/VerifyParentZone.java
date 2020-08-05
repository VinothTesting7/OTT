package com.viacom.parentzone;

import java.time.Duration;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.viacom.utils.Utilities;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.pagesVersion2.BasePageV2;
import com.viacom.pagesVersion2.HomePageV2;
import com.viacom.pagesVersion2.SettingsPageV2;
import com.viacom.smoketestscripts.BaseTestV2;
import com.viacom.utils.DataUtil;

/********************************************************************************************************************************
 * Class : VerifyStatsInParentZone Purpose : This class is used to verify sleep and wake time behavior in parent zone across the
 * framework Author : Saravanan R
 ********************************************************************************************************************************/
public class VerifyParentZone extends BaseTestV2 {
	String testName = "VerifyParentZone";
	String sheetName = "Regression Checklist";
	String pass = "PASS";
	String fail = "FAIL";

	static int errorCount_0 = 0;
	static int errorCount_1 = 0;
	static int errorCount_2 = 0;
	static int errorCount_3 = 0;
	static int errorCount_4 = 0;

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(testName, xls);
	}

	@Test(dataProvider = "getData")
	public void verifyParentZonePreferenceTab(Hashtable<String, String> data) throws Exception {
		String VK_1327 = "VK_1327";
		String VK_1328 = "VK_1328";

		test = rep.startTest("VerifyParentZone");
		test.log(LogStatus.INFO, "Verify Parent Zone - Preference tab");

		// Check run mode
		if (!DataUtil.isExecutable(xls, testName) || data.get("Runmode").equals("N")) {
			test.log(LogStatus.SKIP, "Skipping the test as Run Mode was: NO");
			throw new SkipException("Skipping the test as Run Mode was: NO");
		}
		
		String emailLabel = null;

		launchApp();
		HomePageV2 homepage = new HomePageV2(driver, test);
		SettingsPageV2 settingsPageV2 = new SettingsPageV2(driver, test);
		try {
			homepage.login(data.get("Email"),data.get("Password"));
		} catch (Exception e) {
			// TODO: handle exception
			BasePageV2.reportFail("Login credential couldn't fetch from data sheet");
		}
		if (Utilities.explicitWaitVisible(driver, homepage.profilepic, 10)) {
			homepage.profilepic.click();
		} else {
			test.log(LogStatus.INFO, "Profile icon has not shown");
		}
		if (Utilities.explicitWaitVisible(driver, settingsPageV2.settingsIcon, 30)) {
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneButton, 30)) {
				settingsPageV2.parentZoneButton.click();
			} else {
				test.log(LogStatus.INFO, "Parent zone button has not shown");
			}
		} else {
			test.log(LogStatus.INFO, "Settings screen has not shown");
			test.log(LogStatus.FAIL, "Settings screen has not shown");
		}
		if (Utilities.explicitWaitVisible(driver, homepage.pinView_In_ParentZone, 30)) {
			homepage.pinView_In_ParentZone.click();
			homepage.pinView_In_ParentZone.sendKeys("1111");
		} else {
			test.log(LogStatus.INFO, "PinView In ParentZone has not shown");
			test.log(LogStatus.FAIL, "PinView In ParentZone has not shown");
		}
		Thread.sleep(10000);
//		
		try {
			settingsPageV2.parentZoneActivityTab.click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			settingsPageV2.parentZonePreferenceTab.click();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		Scroll till email update
		for (int scroll = 0; scroll <= 8; scroll++) {
			if (Utilities.explicitWaitVisible(driver, settingsPageV2.emailUpdateInParentZone,
					2)) {
				test.log(LogStatus.INFO, "Swiped to RECENT ACTIVITY");
				emailLabel = settingsPageV2.emailUpdateInParentZone.getText();
				test.log(LogStatus.INFO, "EmailLabel:" + emailLabel);
				break;
			} else
				Utilities.verticalSwipe(driver);
		}
		if (Utilities.explicitWaitVisible(driver, homepage.toggleOff_In_BedTime, 30)) {
			homepage.toggleOff_In_BedTime.click();
		} else {
			test.log(LogStatus.INFO, "ToggleOff_In_BedTime has not shown");
			test.log(LogStatus.FAIL, "ToggleOff_In_BedTime has not shown");
		}
		if (Utilities.explicitWaitVisible(driver, homepage.toggleOn_In_BedTime, 30)) {
			if (Utilities.explicitWaitVisible(driver, homepage.sleep_Time, 30)) {
				homepage.sleep_Time.click();
//					Get list
				List<WebElement> calendarPickerList = driver
						.findElements(By.className("android.widget.RadialTimePickerView$RadialPickerTouchHelper"));
				calendarPickerList.get(8).click();
				if (Utilities.explicitWaitVisible(driver, homepage.Ok_Button_In_Calendar, 30)) {
					homepage.Ok_Button_In_Calendar.click();
				} else {
					test.log(LogStatus.INFO, "Ok Button In Calendar has not shown");
				}
			} else {
				test.log(LogStatus.INFO, "Sleep Time has not shown");
			}
			if (Utilities.explicitWaitVisible(driver, homepage.wakeUp_Time, 30)) {
				String wakeTime = homepage.wakeUp_Time.getText();
				test.log(LogStatus.INFO, "wakeTime:" + wakeTime);
				homepage.wakeUp_Time.click();
				if (Utilities.explicitWaitVisible(driver, settingsPageV2.hoursInCalendar, 30)) {
					List<WebElement> wakeUpPickerList = driver
							.findElements(By.className("android.widget.RadialTimePickerView$RadialPickerTouchHelper"));
					wakeUpPickerList.get(9).click();
					if (Utilities.explicitWaitClickable(driver, homepage.Ok_Button_In_Calendar, 30)) {
						test.log(LogStatus.INFO, "Ok button in calendar has enabled");
						test.log(LogStatus.FAIL,
								"Test case:' VK_1327 - User should not be able to select time difference between Sleep and Wakeup with less than 1 hour' has FAILED");
						if (!Utilities.setResultsKids("VK_1327", "FAIL")) {
						}
						errorCount_0++;
					} else {
						test.log(LogStatus.INFO, "Ok button in calendar has disabled");
						test.log(LogStatus.PASS,
								"Test case:' VK_1327 - User should not be able to select time difference between Sleep and Wakeup with less than 1 hour' has PASSED");
					}
//					VK_1328
					if (Utilities.explicitWaitClickable(driver, settingsPageV2.calendercancelBtn, 30)) {
						settingsPageV2.calendercancelBtn.click();
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.ResetParentResetPageBackBtn, 30)) {
							settingsPageV2.ResetParentResetPageBackBtn.click();
						} else {
							test.log(LogStatus.INFO, "Back button in parent zone has not visible");
						}
					} else {
						test.log(LogStatus.INFO, "Calendar cancel button has not clickable");
					}
					if (Utilities.explicitWaitVisible(driver, homepage.bedTimeTitle, 30)
							|| Utilities.explicitWaitVisible(driver, homepage.bedTimeSubTitle, 30)) {
						test.log(LogStatus.INFO, "Bed time reminder screen has shown");
						test.log(LogStatus.PASS,
								"Test case:'VK_1328 - If User access the app in between wakeup and sleep time, a seperate screen showing bed time reminder' has PASSED");
						test.log(LogStatus.PASS, "Test case: VK_1473 - has PASSED");
					} else {
						test.log(LogStatus.INFO, "Bed time reminder screen has not shown");
						test.log(LogStatus.FAIL,
								"Test case:'VK_1328 - If User access the app in between wakeup and sleep time, a seperate screen showing bed time reminder' has FAILED");
						test.log(LogStatus.FAIL, "Test case: VK_1473 - has FAILED");
						Utilities.captureScreenshot(driver);
						if (!Utilities.setResultsKids("VK_1328", "FAIL")) {
						}
						errorCount_1++;
						if (!Utilities.setResultsKids("VK_1473", "FAIL")) {
						}
						errorCount_4++;
					}
//					VK_1474
					if (Utilities.explicitWaitVisible(driver, homepage.parentZoneButton, 30)) {
						homepage.parentZoneButton.click();
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.parentZoneEnterPinToacess, 30)
								&& Utilities.explicitWaitVisible(driver, homepage.pinView_In_ParentZone, 30)) {
							test.log(LogStatus.INFO, "Navigation successful to parent zone enter pin to access");
							test.log(LogStatus.PASS,
									"Test case: 'VK_1474 - Tapping on Parent Zone button should be navigated to parent zone PIN screen' has PASSED");
						} else {
							test.log(LogStatus.INFO, "Navigation not successful to parent zone enter pin to access");
							test.log(LogStatus.FAIL,
									"Test case: 'VK_1474 - Tapping on Parent Zone button should be navigated to parent zone PIN screen' has FAILED");
						}
					} else {
						test.log(LogStatus.INFO, "Parent zone button on sleep reminder has not visible");
						test.log(LogStatus.FAIL,
								"Test case: 'VK_1474 - Tapping on Parent Zone button should be navigated to parent zone PIN screen' has not Executed");
						Utilities.captureScreenshot(driver);
						if (!Utilities.setResultsKids("VK_1474", "FAIL")) {
						}
						errorCount_2++;
					}
//					VK_1475
					if (Utilities.explicitWaitVisible(driver, homepage.pinView_In_ParentZone, 30)) {
						homepage.pinView_In_ParentZone.click();
						homepage.pinView_In_ParentZone.sendKeys("1111");
						if (Utilities.explicitWaitVisible(driver, settingsPageV2.ParentZonePageTile, 30)) {
							test.log(LogStatus.INFO, "After pin input navigation success to parent zone screen");
							test.log(LogStatus.PASS,
									"Test case:'VK_1475 - Post entering valid PIN user should be navigated to preferences section in parent zone to change the bed time option' has partial PASSED");
						} else {
							test.log(LogStatus.INFO, "After pin input navigation failed to parent zone screen");
							test.log(LogStatus.FAIL,
									"Test case:'VK_1475 - Post entering valid PIN user should be navigated to preferences section in parent zone to change the bed time option' has FAILED");
						}
					} else {
						test.log(LogStatus.INFO, "Input pin field has not visible");
						test.log(LogStatus.FAIL,
								"Test case:'VK_1475 - Post entering valid PIN user should be navigated to preferences section in parent zone to change the bed time option' has not EXECUTED");
						Utilities.captureScreenshot(driver);
						if (!Utilities.setResultsKids("VK_1475", "FAIL")) {
						}
						errorCount_3++;
					}
				} else {
					test.log(LogStatus.INFO, "Ok Button In Calendar has not shown");
					test.log(LogStatus.FAIL, "Ok Button In Calendar has not shown");
				}
			} else {
				test.log(LogStatus.INFO, "WakeUp Time has not shown");
				test.log(LogStatus.FAIL, "WakeUp Time has not shown");
			}
		} else {
			test.log(LogStatus.INFO, "Toggle ON in bedtime has not shown");
			test.log(LogStatus.FAIL, "Toggle ON in bedtime has not shown");
		}
	}
}
