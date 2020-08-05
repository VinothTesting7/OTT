package com.viacom.pagesVersion2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
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

import io.appium.java_client.android.AndroidDriver;

public class MixPanelPageVK extends BasePageV2 {

	public static ArrayList<String> decodedResponseloggedIn = new ArrayList<String>();
	public static Map<String, String> mixevntsClicktoDownload = new HashMap<String, String>();
	public static Map<String, String> mixevntsCompleteDownload = new HashMap<String, String>();
	public static Map<String, String> mixevntsCancelDownload = new HashMap<String, String>();
	public static Map<String, String> mixevntsDeleteDownload = new HashMap<String, String>();
	public static ArrayList<String> base64encodedString = new ArrayList<String>();
	public static Element cElement;
	public static String strResponse = "";
	public static String decodedString = "";
	public static String EventName = "Download";
	public static boolean clickDownload = false;
	public static boolean cancelDownload = false;
	public static boolean completeDownload = false;
	public static boolean deleteDownload = false;
	public static String decodedStringClick = "";
	public static String decodedStringCancel = "";
	public static String decodedStringComplete = "";
	
	public static boolean rewind = false;
	public static boolean forward = false;
	public static boolean pause = false;
	public static boolean play = false;
	public static boolean resume = false;
	public static boolean minimize = false;
	public static boolean changequality = false;
	public static boolean changelanguage = false;
	public static boolean favourite = false;
	public static boolean unfavourite = false;
	public static boolean upArrow = false;
	
	public static Map<String, String> mixevntsrewind = new HashMap<String, String>();
	public static Map<String, String> mixevntsforward = new HashMap<String, String>();
	public static Map<String, String> mixevntspause = new HashMap<String, String>();
	public static Map<String, String> mixevntsresume = new HashMap<String, String>();
	public static Map<String, String> mixevntsminimize = new HashMap<String, String>();
	public static Map<String, String> mixevntschangequality = new HashMap<String, String>();
	public static Map<String, String> mixevntschangelanguage = new HashMap<String, String>();
	public static Map<String, String> mixevntsfavourite = new HashMap<String, String>();
	public static Map<String, String> mixevntsunfavourite = new HashMap<String, String>();
	public static Map<String, String> mixevntsupArrow = new HashMap<String, String>();

	public MixPanelPageVK(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	// Xpaths:
	@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']")
	public WebElement episodeDownloadIcon;

	@FindBy(id = "com.viacom18.vootkids:id/textview_download_item_title")
	public WebElement downLoadItemTitle;
	
	// Xpaths:
		/*@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='NOT_ADDED']")
		public WebElement episodeDownloadIcon;*/
		
		//com.viacom18.vootkids:id/audio_setting
		
		@FindBy(id = "com.viacom18.vootkids:id/audio_setting")
		public WebElement audioSettingsIcon;

		/*@FindBy(id = "com.viacom18.vootkids:id/textview_download_item_title")
		public WebElement downLoadItemTitle;*/

		@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE']")
		public WebElement downloadedEpisode;

		@FindBy(xpath = "//android.widget.TextView[@text='Cancel Download']")
		public WebElement cancelDownloadLink;

		@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Download')]")
		public WebElement DownloadLink;

		@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloading')]")
		public WebElement DownloadingLink;

		@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloaded')]")
		public WebElement DownloadedLink;

		@FindBy(xpath = "//android.widget.TextView[@text='CANCEL' or @text='Cancel' or @text='cancel']")
		public WebElement CancelBtn;

		@FindBy(xpath = "//android.widget.TextView[@text='Download' or @text='DOWNLOAD' or @text='download']")
		public WebElement DownloadBtn;

		@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Preferences']")
		public WebElement preferencesTab;

		@FindBy(id = "com.viacom18.vootkids:id/button_manage_downloads")
		public WebElement manageDownloadsLink;

		@FindBy(id = "com.viacom18.vootkids:id/checkbox_delete_download_item")
		public WebElement deleteIcon;

		@FindBy(id = "com.viacom18.vootkids:id/delete_button")
		public WebElement deleteDownloadButton;
		
		
		//android.widget.TextView[@text='Hindi']
		
		
		@FindBy(xpath = "//android.widget.TextView[@text='Select Language. ']")
		public WebElement selectLanguage;
		
		@FindBy(xpath = "//android.widget.TextView[@text='Stream Quality. ']")
		public WebElement streamQuality;
		
		@FindBy(xpath = "//android.widget.TextView[@text='Hindi' or @text='HINDI' or @text='hindi']")
		public WebElement hindiLanguage;
		
		@FindBy(xpath = "//android.widget.TextView[@text='OK' or @text='Ok' or @text='ok']")
		public WebElement okButton;

	/*@FindBy(xpath = "//*[@resource-id='com.viacom18.vootkids:id/parent_for_download_item' and @content-desc='DOWNLOAD_COMPLETE']")
	public WebElement downloadedEpisode;*/

	/*@FindBy(xpath = "//android.widget.TextView[@text='Cancel Download']")
	public WebElement cancelDownloadLink;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Download')]")
	public WebElement DownloadLink;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloading')]")
	public WebElement DownloadingLink;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/download_status_textview' and contains(@text,'Downloaded')]")
	public WebElement DownloadedLink;
	
	@FindBy(xpath = "//android.widget.TextView[@text='CANCEL' or @text='Cancel' or @text='cancel']")
	public WebElement CancelBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Download' or @text='DOWNLOAD' or @text='download']")
	public WebElement DownloadBtn;

	@FindBy(xpath = "//android.widget.TextView[@resource-id='com.viacom18.vootkids:id/tab_title' and @text='Preferences']")
	public WebElement preferencesTab;
	
	@FindBy(id = "com.viacom18.vootkids:id/button_manage_downloads")
	public WebElement manageDownloadsLink;
	
	@FindBy(id = "com.viacom18.vootkids:id/checkbox_delete_download_item")
	public WebElement deleteIcon;
	
	@FindBy(id = "com.viacom18.vootkids:id/delete_button")
	public WebElement deleteDownloadButton;*/
	
		
		// Method to capture Audio MP Events
		public static void mixpnlEventsfetchLoggedInuserplayerActionsEvent(String mobileNumber, String EventName) throws SAXException, IOException, ParserConfigurationException {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setValidating(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			EntityResolver resolver = new EntityResolver() {
				public InputSource resolveEntity(String publicId, String systemId) {
					String ety = "";
					ByteArrayInputStream bais = new ByteArrayInputStream(ety.getBytes());
					System.out.println("Charles Xml documet session:" + systemId);
					return new InputSource(bais);
				}
			};

			base64encodedString.clear();
			decodedResponseloggedIn.clear();
			rewind = false;
			forward = false;
			pause = false;
			play = false;
			minimize=false;
			resume = false;
			changequality = false;
			changelanguage = false;
			favourite = false;
			unfavourite = false;
			upArrow = false;

			dBuilder.setEntityResolver(resolver);
			Document doc = dBuilder.parse(VootConstants.filePathxml);

			doc.getDocumentElement().normalize();

			System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("transaction");

//			VootConstants.MPProperties.clear();

//			UtilitiesWeb.fetchProperties(VootConstantsWeb.environment, EventName);

			// System.out.println( "Fetching base64 encoding value and converting all
			// hex codes in a String");
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) (nodeList.item(i));

					if (eElement.getAttribute("path").contains("/track")
							&& eElement.getAttribute("host").equals("api.mixpanel.com")) {

						cElement = (Element) eElement.getElementsByTagName("body").item(0);

						try {
							strResponse = cElement.getTextContent();
							String split[] = strResponse.split("data=");
							strResponse = split[1].toString().trim();
							System.out.println(
									"base64 encoded String before replacing the hexcode values with respective charaters: "
											+ strResponse);
							strResponse = Utilities.replacehexadeciValuetoUnichar(strResponse);
							base64encodedString.add(strResponse);
							System.out.println(
									"base64 encoded String afetr replacing the hexcode values with respective charaters: "
											+ strResponse);
						} catch (Exception e) {
							System.out.println("Not able to decode String");
						}

					} else {

					}

				}

				// break;
			}

			if (rewind == false) {
				mixevntsrewind.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Rewind 10s")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Rewind 10s")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Rewind 10s' response was: " + decodedString);
									rewind = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Rewind 10s' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsrewind.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}
			if (forward == false) {
				mixevntsforward.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Forward 10s")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Forward 10s")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Forward 10s' response was: " + decodedString);
									forward = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Forward 10s' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsforward.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}
			if (pause == false) {
				mixevntspause.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("playbackPaused")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("playbackPaused")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'playbackPaused' response was: " + decodedString);
									pause = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'playbackPaused' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntspause.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (resume == false) {
				mixevntsresume.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("playbackResumed")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("playbackResumed")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'playbackResumed' response was: " + decodedString);
									resume = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'playbackResumed' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsresume.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}
			
			if (minimize == false) {
				mixevntsminimize.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Minimise")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Minimise")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Minimise' response was: " + decodedString);
									minimize = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Minimise' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsminimize.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (changequality == false) {
				mixevntschangequality.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Audio Quality Changed")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Audio Quality Changed")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Audio Quality Changed' response was: " + decodedString);
									changequality = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Audio Quality Changed' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntschangequality.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (favourite == false) {
				mixevntsfavourite.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Favorited")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Favorited")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Favorited' response was: " + decodedString);
									favourite = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Favorited' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsfavourite.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (unfavourite == false) {
				mixevntsunfavourite.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Unfavorited")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Unfavorited")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Unfavorited' response was: " + decodedString);
									unfavourite = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Unfavorited' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsunfavourite.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (upArrow == false) {
				mixevntsupArrow.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Below Arrow Clicked")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Below Arrow Clicked")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Below Arrow Clicked' response was: " + decodedString);
									upArrow = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Below Arrow Clicked' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsupArrow.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}

				}
			}

			System.out.println("Rewind Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsrewind.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Forward Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsforward.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Change quality");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntschangequality.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Playback pause");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntspause.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Playback Resume Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsresume.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("minimize Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsminimize.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Favourite Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsfavourite.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("unfavourite Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsunfavourite.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Up arrow");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsupArrow.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

		}
		
		public static void mixpnlEventsfetchLoggedInuserVideoplayerActionsEvent(String mobileNumber, String EventName) throws SAXException, IOException, ParserConfigurationException {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setValidating(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			EntityResolver resolver = new EntityResolver() {
				public InputSource resolveEntity(String publicId, String systemId) {
					String ety = "";
					ByteArrayInputStream bais = new ByteArrayInputStream(ety.getBytes());
					System.out.println("Charles Xml documet session:" + systemId);
					return new InputSource(bais);
				}
			};

			base64encodedString.clear();
			decodedResponseloggedIn.clear();
			decodedString="";
			
			rewind = false;
			forward = false;
			pause = false;
			play = false;
			resume = false;
			changequality = false;
			changelanguage = false;
			favourite = false;
			unfavourite = false;
			upArrow = false;

			dBuilder.setEntityResolver(resolver);
			Document doc = dBuilder.parse(VootConstants.filePathxml);

			doc.getDocumentElement().normalize();

			System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("transaction");

//			VootConstants.MPProperties.clear();

//			UtilitiesWeb.fetchProperties(VootConstantsWeb.environment, EventName);

			// System.out.println( "Fetching base64 encoding value and converting all
			// hex codes in a String");
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) (nodeList.item(i));

					if (eElement.getAttribute("path").contains("/track")
							&& eElement.getAttribute("host").equals("api.mixpanel.com")) {

						cElement = (Element) eElement.getElementsByTagName("body").item(0);

						try {
							strResponse = cElement.getTextContent();
							String split[] = strResponse.split("data=");
							strResponse = split[1].toString().trim();
							System.out.println(
									"base64 encoded String before replacing the hexcode values with respective charaters: "
											+ strResponse);
							strResponse = Utilities.replacehexadeciValuetoUnichar(strResponse);
							base64encodedString.add(strResponse);
							System.out.println(
									"base64 encoded String afetr replacing the hexcode values with respective charaters: "
											+ strResponse);
						} catch (Exception e) {
							System.out.println("Not able to decode String");
						}

					} else {

					}

				}

				// break;
			}

			if (rewind == false) {
				mixevntsrewind.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Rewind 10s")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Rewind 10s")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Rewind 10s' response was: " + decodedString);
									rewind = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Rewind 10s' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsrewind.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}
			if (forward == false) {
				mixevntsforward.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Forward 10s")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Forward 10s")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Forward 10s' response was: " + decodedString);
									forward = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Forward 10s' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsforward.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}
			if (pause == false) {
				mixevntspause.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("playbackPaused")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("playbackPaused")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'playbackPaused' response was: " + decodedString);
									pause = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'playbackPaused' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntspause.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (resume == false) {
				mixevntsresume.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("playbackResumed")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("playbackResumed")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'playbackResumed' response was: " + decodedString);
									resume = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'playbackResumed' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsresume.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}


			if (changequality == false) {
				mixevntschangequality.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Video Quality Changed")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Video Quality Changed")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Video Quality Changed' response was: " + decodedString);
									changequality = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Video Quality Changed' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntschangequality.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}
			
			if (changelanguage == false) {
				mixevntschangelanguage.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Language Changed")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Language Changed")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Language Changed' response was: " + decodedString);
									changelanguage = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Language Changed' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntschangelanguage.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (favourite == false) {
				mixevntsfavourite.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Favorited")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Favorited")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Favorited' response was: " + decodedString);
									favourite = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Favorited' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsfavourite.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (unfavourite == false) {
				mixevntsunfavourite.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Unfavorited")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Unfavorited")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Unfavorited' response was: " + decodedString);
									unfavourite = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Unfavorited' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsunfavourite.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}
				}
			}

			if (upArrow == false) {
				mixevntsupArrow.clear();
				for (String encodeString : base64encodedString) {
					byte[] byteArray = Base64.decodeBase64(encodeString.getBytes());
					decodedString = new String(byteArray).toString().trim();
					if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
							&& decodedString.contains("Below Arrow Clicked")) {

						try {
							String[] multiRespSplit = decodedString.split("\\}\\}\\,");
							for (String singleSplit : multiRespSplit) {

								if (singleSplit.contains("Below Arrow Clicked")) {
									decodedString = singleSplit;
									test.log(LogStatus.INFO, "Player Action 'Below Arrow Clicked' response was: " + decodedString);
									upArrow = true;
									break;
								}
							}
						} catch (Exception e) {

						}
						System.out.println();
						System.out.println("Player Action 'Below Arrow Clicked' response was: " + decodedString);

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsupArrow.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					}

				}
			}

			System.out.println("Rewind Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsrewind.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Forward Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsforward.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Change quality");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntschangequality.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Playback pause");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntspause.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Playback Resume Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsresume.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("minimize Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsminimize.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Favourite Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsfavourite.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("unfavourite Events");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsunfavourite.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

			System.out.println("Up arrow");
			System.out.println("------------------------------------------------------------------");
			for (Map.Entry<String, String> entryMap : mixevntsupArrow.entrySet()) {
				String key = entryMap.getKey();
				String value = entryMap.getValue();
				System.out.println("Displayed Key was: " + key);
				System.out.println("Displayed value was: " + value);
			}

		}
	

	// Method to validate

	// Method to Change download quality
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

	// Mix Panel Download Event property fetch Method
	public static void mixpnlEventsfetchLoggedInuserDownloadEvents(String mobileNumber, String EventName)
			throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String ety = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(ety.getBytes());
				System.out.println("Charles Xml documet session:" + systemId);
				return new InputSource(bais);
			}
		};

		mixevntsClicktoDownload.clear();
		base64encodedString.clear();
		decodedResponseloggedIn.clear();

		dBuilder.setEntityResolver(resolver);
		Document doc = dBuilder.parse(VootConstants.filePathxml);

		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if (eElement.getAttribute("path").contains("/track")
						&& eElement.getAttribute("host").equals("api.mixpanel.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);

					try {
						strResponse = cElement.getTextContent();
						String split[] = strResponse.split("data=");
						strResponse = split[1].toString().trim();
						System.out.println(
								"base64 encoded String before replacing the hexcode values with respective charaters: "
										+ strResponse);
						strResponse = Utilities.replacehexadeciValuetoUnichar(strResponse);
						base64encodedString.add(strResponse);
						System.out.println(
								"base64 encoded String afetr replacing the hexcode values with respective charaters: "
										+ strResponse);
					} catch (Exception e) {
						System.out.println("Not able to decode String");
					}

				} else {

				}

			}

		}

		// Clearing downloads
		mixevntsClicktoDownload.clear();
		mixevntsCancelDownload.clear();
		mixevntsCompleteDownload.clear();

		// Type of downloads flag
		clickDownload = false;
		cancelDownload = false;
		for (String base64String : base64encodedString) {

			if (cancelDownload == true && completeDownload == true && clickDownload == true) {
				System.out.println("Completed the validation of all 'Cancel, Click and Complete' download option");
				break;
			}

			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			decodedString = new String(byteArray).toString().trim();

			try {
				String[] multiRespSplit = decodedString.split("\\}\\}\\,");
				for (String singleSplit : multiRespSplit) {
					if (singleSplit.contains("Click to Download")) {
						decodedStringClick = singleSplit;
					}
					if (singleSplit.contains("Cancel Download")) {
						decodedStringCancel = singleSplit;
					}
					if (singleSplit.contains("Complete Download")) {
						decodedStringComplete = singleSplit;
					}
				}
			} catch (Exception e) {

			}

			System.out.println("Decoded String was: " + decodedString);
			if (clickDownload == false) {
				if (decodedStringClick.contains(mobileNumber) && decodedStringClick.contains(EventName)
						&& decodedStringClick.contains("Click to Download")) {

					test.log(LogStatus.INFO, "Download Action 'Click to Download' response was: " + decodedStringClick);
					clickDownload = true;

					String[] mixsplit = decodedStringClick.split(",\"");

					// System.out.println("MP Properties Size: " +
					// VootConstantsWeb.MPProperties.size());
					System.out.println("Charles Properties size: " + mixsplit.length);

					for (int i = 0; i < mixsplit.length; i++) {
						try {
							String mixevents = mixsplit[i].toString().trim();
							String mixeventskeyVal[] = mixevents.split("\\:");
							mixevntsClicktoDownload.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
									"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				}

				// break;
			}

			if (cancelDownload == false) {
				if (decodedStringCancel.contains(mobileNumber) && decodedStringCancel.contains(EventName)
						&& decodedStringCancel.contains("Cancel Download")) {

					// decodedResponseloggedIn.add(decodedString);

					test.log(LogStatus.INFO, "Download Action 'Cancel Download' response was: " + decodedStringCancel);
					cancelDownload = true;

					String[] mixsplit = decodedStringCancel.split(",\"");

					// System.out.println("MP Properties Size: " +
					// VootConstantsWeb.MPProperties.size());
					System.out.println("Charles Properties size: " + mixsplit.length);

					for (int i = 0; i < mixsplit.length; i++) {
						try {
							String mixevents = mixsplit[i].toString().trim();
							String mixeventskeyVal[] = mixevents.split("\\:");
							mixevntsCancelDownload.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
									"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
						} catch (Exception e) {
							// TODO: handle exception
						}

					}

					// break;

				} else {

				}
			}

		}
		completeDownload = false;
		mixevntsCompleteDownload.clear();
		for (String base64String : base64encodedString) {

			if (completeDownload == true) {
				System.out.println("Completed the validation of all 'Cancel, Click and Complete' download option");
				break;
			}

			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			decodedString = new String(byteArray).toString().trim();

			try {
				String[] multiRespSplit = decodedString.split("\\}\\}\\,");
				for (String singleSplit : multiRespSplit) {

					if (singleSplit.contains("Complete Download")) {
						decodedString = singleSplit;
						break;
					}
				}
			} catch (Exception e) {

			}

			System.out.println("Decoded String was: " + decodedString);
			if (completeDownload == false) {
				if (decodedString.contains(mobileNumber) && decodedStringClick.contains(EventName)
						&& decodedStringClick.contains("Complete Download")) {

					test.log(LogStatus.INFO, "Download Action 'Click to Download' response was: " + decodedString);
					completeDownload = true;

					String[] mixsplit = decodedStringClick.split(",\"");

					// System.out.println("MP Properties Size: " +
					// VootConstantsWeb.MPProperties.size());
					System.out.println("Charles Properties size: " + mixsplit.length);

					for (int i = 0; i < mixsplit.length; i++) {
						try {
							String mixevents = mixsplit[i].toString().trim();
							String mixeventskeyVal[] = mixevents.split("\\:");
							mixevntsCompleteDownload.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
									"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				}

				// break;
			}

			if (completeDownload == false) {
				if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
						&& decodedString.contains("Complete Download")) {

					// decodedResponseloggedIn.add(decodedString);

					test.log(LogStatus.INFO, "Download Action 'Complete Download' response was: " + decodedString);
					completeDownload = true;

					String[] mixsplit = decodedString.split(",\"");

					// System.out.println("MP Properties Size: " +
					// VootConstantsWeb.MPProperties.size());
					System.out.println("Charles Properties size: " + mixsplit.length);

					for (int i = 0; i < mixsplit.length; i++) {
						try {
							String mixevents = mixsplit[i].toString().trim();
							String mixeventskeyVal[] = mixevents.split("\\:");
							mixevntsCompleteDownload.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
									"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
						} catch (Exception e) {
							// TODO: handle exception
						}

					}

					break;
				} else {

				}
			}
		}

		System.out.println("Click to download event validation");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<String, String> entry1 : mixevntsClicktoDownload.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevntsClicktoDownload.get(entry1.getKey()));
		}

		System.out.println("Cancel download event validation");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<String, String> entry1 : mixevntsCancelDownload.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevntsCancelDownload.get(entry1.getKey()));
		}

		System.out.println("Complete download event validation");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<String, String> entry1 : mixevntsCompleteDownload.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevntsCompleteDownload.get(entry1.getKey()));
		}

		for (String decodedlogin : decodedResponseloggedIn) {
			System.out.println("Logged in decoded response was: " + decodedlogin);

		}

	}
	
	
	public static void mixpnlEventsfetchLoggedInuserDownloadEvents(String mobileNumber, String EventName,
			String Mediaid) throws SAXException, IOException, ParserConfigurationException {
		mixevntsClicktoDownload.clear();
		mixevntsCancelDownload.clear();
		mixevntsCompleteDownload.clear();
		base64encodedString.clear();
		decodedResponseloggedIn.clear();
		clickDownload = false;
		cancelDownload = false;
		completeDownload = false;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String ety = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(ety.getBytes());
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

				if (eElement.getAttribute("path").contains("/track")
						&& eElement.getAttribute("host").equals("api.mixpanel.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);

					try {
						strResponse = cElement.getTextContent();
						String split[] = strResponse.split("data=");
						strResponse = split[1].toString().trim();
						System.out.println(
								"base64 encoded String before replacing the hexcode values with respective charaters: "
										+ strResponse);
						strResponse = Utilities.replacehexadeciValuetoUnichar(strResponse);
						base64encodedString.add(strResponse);
						System.out.println(
								"base64 encoded String afetr replacing the hexcode values with respective charaters: "
										+ strResponse);
					} catch (Exception e) {
						System.out.println("Not able to decode String");
					}

				} else {

				}

			}

		}

		// Click to Download events fetch
		clickDownload = false;
		for (String base64String : base64encodedString) {

			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			decodedString = new String(byteArray).toString().trim();

			System.out.println("Decoded String was: " + decodedString);

			if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
					&& decodedString.contains("Click to Download") && decodedString.contains(Mediaid)) {

				try {
					String[] multiRespSplit = decodedString.split("\\}\\}\\,");
					for (String singleSplit : multiRespSplit) {

						if (singleSplit.contains("Click to Download")) {
							decodedString = singleSplit;
							clickDownload = true;
							break;
						}
					}
				} catch (Exception e) {

				}

				test.log(LogStatus.INFO, "Download Action 'Click to Download' response was: " + decodedString);
				clickDownload = true;

				String[] mixsplit = decodedString.split(",\"");

				// System.out.println("MP Properties Size: " +
				// VootConstantsWeb.MPProperties.size());
				System.out.println("Charles Properties size: " + mixsplit.length);

				for (int i = 0; i < mixsplit.length; i++) {
					try {
						String mixevents = mixsplit[i].toString().trim();
						String mixeventskeyVal[] = mixevents.split("\\:");
						mixevntsClicktoDownload.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
								"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
				break;
			}

			// break;

		}

		// Cancel Download Event
		cancelDownload = false;
		for (String base64String : base64encodedString) {

			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			decodedString = new String(byteArray).toString().trim();

			System.out.println("Decoded String was: " + decodedString);

			if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
					&& decodedString.contains("Cancel Download") && decodedString.contains(Mediaid)) {

				try {
					String[] multiRespSplit = decodedString.split("\\}\\}\\,");
					for (String singleSplit : multiRespSplit) {

						if (singleSplit.contains("Cancel Download")) {
							decodedString = singleSplit;
							cancelDownload = true;
							break;
						}
					}
				} catch (Exception e) {

				}

				test.log(LogStatus.INFO, "Download Action 'Cancel Download' response was: " + decodedString);

				String[] mixsplit = decodedString.split(",\"");

				// System.out.println("MP Properties Size: " +
				// VootConstantsWeb.MPProperties.size());
				System.out.println("Charles Properties size: " + mixsplit.length);

				for (int i = 0; i < mixsplit.length; i++) {
					try {
						String mixevents = mixsplit[i].toString().trim();
						String mixeventskeyVal[] = mixevents.split("\\:");
						mixevntsCancelDownload.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
								"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

				break;
			}

			// break;

		}

		// Complete Download Event

		completeDownload = false;
		for (String base64String : base64encodedString) {

			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			decodedString = new String(byteArray).toString().trim();

			System.out.println("Decoded String was: " + decodedString);

			if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
					&& decodedString.contains("Complete Download") && decodedString.contains(Mediaid)) {

				try {
					String[] multiRespSplit = decodedString.split("\\}\\}\\,");
					for (String singleSplit : multiRespSplit) {

						if (singleSplit.contains("Complete Download")) {
							decodedString = singleSplit;
							completeDownload = true;
							break;
						}
					}
				} catch (Exception e) {

				}

				test.log(LogStatus.INFO, "Download Action 'Complete Download' response was: " + decodedString);

				String[] mixsplit = decodedString.split(",\"");

				// System.out.println("MP Properties Size: " +
				// VootConstantsWeb.MPProperties.size());
				System.out.println("Charles Properties size: " + mixsplit.length);

				for (int i = 0; i < mixsplit.length; i++) {
					try {
						String mixevents = mixsplit[i].toString().trim();
						String mixeventskeyVal[] = mixevents.split("\\:");
						mixevntsCompleteDownload.put("" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
								"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

				break;
			}

			// break;

		}

		System.out.println("Click to download event validation");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<String, String> entry1 : mixevntsClicktoDownload.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevntsClicktoDownload.get(entry1.getKey()));
		}

		System.out.println("Cancel download event validation");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<String, String> entry1 : mixevntsCancelDownload.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevntsCancelDownload.get(entry1.getKey()));
		}

		System.out.println("Complete download event validation");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<String, String> entry1 : mixevntsCompleteDownload.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevntsCompleteDownload.get(entry1.getKey()));
		}

		for (String decodedlogin : decodedResponseloggedIn) {
			System.out.println("Logged in decoded response was: " + decodedlogin);

		}

	}

	// Method for Delete download events Validation
	public static void mixpnlEventsfetchLoggedInuserDeleteDownloadEvents(String mobileNumber, String EventName)
			throws SAXException, IOException, ParserConfigurationException {
		mixevntsDeleteDownload.clear();
		base64encodedString.clear();
		deleteDownload = false;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String ety = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(ety.getBytes());
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

				if (eElement.getAttribute("path").contains("/track")
						&& eElement.getAttribute("host").equals("api.mixpanel.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);

					try {
						strResponse = cElement.getTextContent();
						String split[] = strResponse.split("data=");
						strResponse = split[1].toString().trim();
						System.out.println(
								"base64 encoded String before replacing the hexcode values with respective charaters: "
										+ strResponse);
						strResponse = Utilities.replacehexadeciValuetoUnichar(strResponse);
						base64encodedString.add(strResponse);
						System.out.println(
								"base64 encoded String afetr replacing the hexcode values with respective charaters: "
										+ strResponse);
					} catch (Exception e) {
						System.out.println("Not able to decode String");
					}

				} else {

				}

			}

		}

		// Delete Download events fetch
		deleteDownload = false;
		for (String base64String : base64encodedString) {

			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			decodedString = new String(byteArray).toString().trim();

			System.out.println("Decoded String was: " + decodedString);

			if (decodedString.contains(mobileNumber) && decodedString.contains(EventName)
					&& decodedString.contains("Delete Downloaded")) {

				try {
					String[] multiRespSplit = decodedString.split("\\}\\}\\,");
					for (String singleSplit : multiRespSplit) {

						if (singleSplit.contains("Delete Downloaded")) {
							decodedString = singleSplit;
							deleteDownload = true;
							break;
						}
					}

					if (deleteDownload == true) {
						test.log(LogStatus.INFO, "Download Action 'Delete Downloaded' response was: " + decodedString);
						// deleteDownload = true;

						String[] mixsplit = decodedString.split(",\"");

						// System.out.println("MP Properties Size: " +
						// VootConstantsWeb.MPProperties.size());
						System.out.println("Charles Properties size: " + mixsplit.length);

						for (int i = 0; i < mixsplit.length; i++) {
							try {
								String mixevents = mixsplit[i].toString().trim();
								String mixeventskeyVal[] = mixevents.split("\\:");
								mixevntsDeleteDownload.put(
										"" + mixeventskeyVal[0].toString().trim().replaceAll("\"", ""),
										"" + mixeventskeyVal[1].toString().trim().replaceAll("\"", ""));
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
						break;
					} else {
						test.log(LogStatus.FAIL, "");
					}
				} catch (Exception e) {

				}

			}

			// break;

		}

		System.out.println("Delete download event validation");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<String, String> entry1 : mixevntsDeleteDownload.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevntsDeleteDownload.get(entry1.getKey()));
		}

		for (String decodedlogin : decodedResponseloggedIn) {
			System.out.println("Logged in decoded response was: " + decodedlogin);

		}

	}

}
