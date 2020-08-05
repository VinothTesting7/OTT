package com.viacom.pagesVersion2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CharlesPageV2 extends BasePageV2 {
	@SuppressWarnings("rawtypes")
	public CharlesPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//android.support.v4.view.ViewPager//*[@class='android.widget.TextView']")
	public WebElement mastheadContent;

	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_layout'][@index='1']//android.widget.TextView[contains(@resource-id,'com.viacom18.vootkids:id/grid_title')]")
	public WebElement Contentcard;

	public static Element cElement;

	public static ArrayList<String> showTitles = new ArrayList<String>();
	public static ArrayList<Integer> mediaTypes = new ArrayList<Integer>();
	public static ArrayList<String> ebookAuthors = new ArrayList<String>();

	public static String strResponse = "";
	public static String custParamString = "";
	public static List<Integer> bitrates = new ArrayList<Integer>();

	public static void bitratescapture() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				System.out.println("Charles Xml documet session:" + systemId);
				return new InputSource(bais);
			}
		};

		dBuilder.setEntityResolver(resolver);

		Document doc = dBuilder.parse(VootConstants.filePathxml);

		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				List<String> paramsvlaues = new ArrayList<String>();

				if (eElement.getAttribute("path").equals("/ping")
						&& eElement.getAttribute("host").contains("youbora")) {

					cElement = (Element) eElement.getElementsByTagName("first-line").item(0);

					strResponse = cElement.getTextContent();
					// if(strResponse.contains("ping"))
					paramsvlaues.add(strResponse);
					System.out.println("displayed xml String from pub ad response is: " + strResponse);

					String[] pubadsvalues = strResponse.split("&");

					for (String pubad : pubadsvalues) {
						if (pubad.contains("bitrate")) {
							custParamString = pubad;
							String[] custvals = custParamString.split("=");
							int bit = Integer.parseInt(custvals[1].toString());
							bitrates.add(bit);
						} else {
						}
					}
				} else {

				}

			} else {
			}

		}
		int i = 0;
		for (int bitrate : bitrates) {
			int j = ++i;
			System.out.println("Values of Bit-rate " + j + " displayed is: " + bitrate);
		}

	}

	public static void clickClose(WebElement close) {

		// driver.c
		TouchAction action = new TouchAction(driver);
		int x = close.getSize().getWidth() / 2;

		int y = close.getSize().getHeight() / 2;

		try {

			action.press(PointOption.point(x, y)).release().perform()
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).press(PointOption.point(x, y))
					.release().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void titlesFetchVOD() throws SAXException, IOException, ParserConfigurationException, Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				System.out.println("Charles Xml documet session:" + systemId);
				return new InputSource(bais);
			}
		};

		dBuilder.setEntityResolver(resolver);

		Document doc = dBuilder.parse(VootConstants.filePathxml);

		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if (eElement.getAttribute("path").contains("/service/recommendation/widgets")
						&& eElement.getAttribute("host").equals("dm-prod.vootkids.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);

					try {
						strResponse = cElement.getTextContent();
						System.out.println("Response was: " + strResponse);
					} catch (Exception e) {

					}

					if (strResponse.contains("DIFFERENT_CATEGORY_SIMILAR_EPISODE")) {

						Element rElement = (Element) eElement.getElementsByTagName("body").item(1);
						strResponse = rElement.getTextContent();
						System.out.println("Captured Response was: " + strResponse);

						try {
							String relatedEpisodes[] = strResponse.split("\"totalItems\":20},");
							strResponse = relatedEpisodes[1].toString();
						} catch (Exception e) {
							BasePageV2.reportFail("Related Tray contents were not displayed");

						}

						if (strResponse.contains("Similar episodes from different series")) {
							String[] splitResp = strResponse.split(",");
							for (String title : splitResp) {
								if (title.contains("refSeriesTitle")) {

									// System.out.println("Reference Series Title displayed was: "+title);
									String[] splitsTitle = title.split("\\:");

									showTitles.add(splitsTitle[1].toString().replaceAll("\"", ""));
								}
							}

							for (String title : splitResp) {
								if (title.contains("mediaType")) {

									// System.out.println("Reference Series Title displayed was: "+title);
									String[] splitsTitle = title.split("\\:");
									// title=title.replaceAll("\"", "").trim();
									mediaTypes.add(Integer.parseInt(splitsTitle[1].toString().replaceAll("\"", "")));
								}
							}

							for (String title : splitResp) {
								if (title.contains("title")) {

									// System.out.println("Reference Series Title displayed was: "+title);
									String[] splitsTitle = title.split("\\:");

									try {
										ebookAuthors.add(splitsTitle[1].toString().replaceAll("\"", ""));
									} catch (Exception e) {
										ebookAuthors.add("Fail");
									}

								}

							}
							break;
						} else
							BasePageV2.reportFail("Related Tray contents were not displayed");
						// break;
					} else {

					}

				} else {

				}

			}

			// break;
		}

		// String relatedShowTitle="";
		for (String relatedShowTitle : showTitles) {
			System.out.println("Show title displayed under Related shows Trays under DM was: " + relatedShowTitle);
		}

		for (int Media : mediaTypes) {
			System.out.println("Media Type displayed: " + Media);
		}

		for (String author : ebookAuthors) {
			System.out.println("Author displayed: " + author);
		}

	}

	public static void qualitySettings(WebElement qualityprop, String quality) throws Exception {
		HomePageV2 homePage = new HomePageV2(driver, test);
		SettingsPageV2 settings = new SettingsPageV2(driver, test);
		if (Utilities.explicitWaitVisible(driver, homePage.profilepic, 15)) {
			BasePageV2.takeScreenshot();
			homePage.profilepic.click();

			// Thread.sleep(10000);

			if (Utilities.explicitWaitVisible(driver, settings.parentZoneButton, 10)) {
				settings.parentZoneButton.click();
				test.log(LogStatus.INFO, "Clicked on ParentZone Button in Switch Profile Screen");
				if (Utilities.explicitWaitVisible(driver, settings.parentPinContainer, 10)) {
					Thread.sleep(1000);
					settings.parentPinContainer.sendKeys("1111"); // set the pin "1111" default
				} else
					BasePageV2.reportFail("PIN CONTAINER not found in Parent Zone page ");
			} else
				BasePageV2.reportFail("RARENT ZONE button not found in Switch Profile Screen");

			Thread.sleep(15000);
//abcd
			driver.runAppInBackground(Duration.ofSeconds(3));
			test.log(LogStatus.INFO, "Put app to background for 3 seconds");
			driver.currentActivity();

			if (Utilities.explicitWaitClickable(driver, settings.settingsIcon, 30)) {
				settings.settingsIcon.click();
			} else
				BasePageV2.reportFail("Settings Icon not Found For to navigate to Setttings Page");

			if (Utilities.explicitWaitVisible(driver, settings.settingsDevice, 10))
				settings.settingsDevice.click();
			else
				BasePageV2.reportFail("Device settings not displayed");

			if (Utilities.explicitWaitVisible(driver, settings.streamQualityDrpDown, 10))
				settings.streamQualityDrpDown.click();
			else
				BasePageV2.reportFail("Device quality drop down was not displayed");

			if (Utilities.explicitWaitVisible(driver, qualityprop, 10))
				qualityprop.click();
			else
				BasePageV2.reportFail(quality + " option was not displayed");

			driver.navigate().back();
			Thread.sleep(2000);

			driver.navigate().back();
			Thread.sleep(2000);

			driver.navigate().back();
			Thread.sleep(2000);

			driver.navigate().back();
			Thread.sleep(2000);

		} else
			BasePageV2.reportFail("Profile Icon was not displayed");
	}

	public static void relatedEbooksfetch() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				System.out.println("Charles Xml documet session:" + systemId);
				return new InputSource(bais);
			}
		};

		dBuilder.setEntityResolver(resolver);

		Document doc = dBuilder.parse(VootConstants.filePathxml);

		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if (eElement.getAttribute("path").contains("/service/recommendation/widgets")
						&& eElement.getAttribute("host").equals("dm-prod.vootkids.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);

					try {
						strResponse = cElement.getTextContent();
						System.out.println("Response was: " + strResponse);
					} catch (Exception e) {

					}

					if (strResponse.contains("DIFFERENT_CATEGORY_SIMILAR_EBOOK")) {

						Element rElement = (Element) eElement.getElementsByTagName("body").item(1);
						strResponse = rElement.getTextContent();
						System.out.println("Captured Response was: " + strResponse);

						String[] splitResp = strResponse.split(",");
						for (String title : splitResp) {
							if (title.contains("title")) {
								title = title.replaceAll("\"", "").trim();
								// System.out.println("Reference Series Title displayed was: "+title);
								String[] splitsTitle = title.split("\\:");
								showTitles.add(splitsTitle[1].toString());
							}
						}

						for (String title : splitResp) {
							if (title.contains("mediaType")) {
								title = title.replaceAll("\"", "").trim();
								// System.out.println("Reference Series Title displayed was: "+title);
								String[] splitsTitle = title.split("\\:");
								mediaTypes.add(Integer.parseInt(splitsTitle[1].toString()));
							}
						}

						for (String title : splitResp) {
							if (title.contains("author")) {
								title = title.replaceAll("\"", "").trim();
								// System.out.println("Reference Series Title displayed was: "+title);
								String[] splitsTitle = title.split("\\:");
								try {
									ebookAuthors.add(splitsTitle[1].toString());
								} catch (Exception e) {
									ebookAuthors.add("FAIL");
								}

							}
						}

						break;
					} else {

					}

				} else {

				}

			}

			// break;
		}

		// String relatedShowTitle="";
		for (String relatedShowTitle : showTitles) {
			System.out.println("Show title displayed under Related shows Trays under DM was: " + relatedShowTitle);
		}

		for (int Media : mediaTypes) {
			System.out.println("Media Type displayed: " + Media);
		}

	}

}
