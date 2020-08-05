package com.viacom.pagesVersion2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidDriver;

public class DMPageV2 extends BasePageV2 {
	public DMPageV2(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	public static boolean mediaTypeFlag = false;
	public static String strResponse = "";
	public static String show = "";
	public static Element cElement;
	public static ArrayList<String> showsList = new ArrayList<String>();
	public static ArrayList<String> mediaTypeList = new ArrayList<String>();

	public static void fetchShowsandMediaTypeRelatedShows()
			throws SAXException, IOException, ParserConfigurationException {
		showsList.clear();
		mediaTypeList.clear();
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

		// VootConstants.filePathxml = "C:\\Users\\ifocus\\Desktop\\Related shows.xml";
		Document doc = dBuilder.parse(VootConstants.filePathxml);

		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) (nodeList.item(i));

				if (eElement.getAttribute("path").equals("/service/recommendation/widgets")
						&& eElement.getAttribute("host").equals("dm-prod.vootkids.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);
					strResponse = cElement.getTextContent();
					if (strResponse.contains("\"pageType\":\"SHOW_DETAILS\"")
							&& strResponse.contains("\"widgetTypes\":[\"SAME_CATEGORY_SIMILAR_SERIES\"]")) {
						cElement = (Element) eElement.getElementsByTagName("body").item(1);
						strResponse = cElement.getTextContent();
						// strResponse.replaceAll("\"", "");
						String splitResp[] = strResponse.split(",\"");
						for (String refSer : splitResp) {

							try {
								if (refSer.contains("refSeriesTitle")) {
									refSer.replaceAll("\"", "");
									String shows[] = refSer.split("\\:");
									show = shows[1].toString().replaceAll("\"", "");
									System.out.println("Displayed Show was: " + show);
									showsList.add(show);
								}

								if (refSer.contains("mediaType")) {

									String shows[] = refSer.split("\\:");
									String mediaType = shows[1].toString();
									try {
										mediaType.replaceAll("\"", "").replaceAll("\"", "");
									} catch (Exception e) {
										// TODO: handle exception
									}

									mediaTypeFlag = false;
									int medType = Integer.parseInt(mediaType);
									if (medType == 418)
										mediaTypeFlag = true;
									
									if (mediaTypeFlag == false) {
										test.log(LogStatus.FAIL, "Incorrect Card Type displayed for content: " + show);
										break;
									} else {
										test.log(LogStatus.INFO, "Verified Type of card displayed for content: "+show);
									}

									System.out.println("Displayed Media Type was: " + mediaType);
									mediaTypeList.add(mediaType);

								}

							} catch (Exception e) {
								// TODO: handle exception
							}
						}

						break;
					}
				}
			}
		}
	}
}
