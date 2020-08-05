package com.viacom.pagesVersion2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.jayway.restassured.path.json.JsonPath;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.GlobalVariables;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidDriver;

public class DataMonkPage extends BasePageV2 {
	public DataMonkPage(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	public static HashMap<String, String> dcseMap = new HashMap<String, String>();
	public static HashMap<String, String> uneMap = new HashMap<String, String>();
	public static Element cElement;
	public static String strResponse = "";
	public static boolean ResultDM = false;
	public static List<String> playerWidgetTypes = new ArrayList<String>();
	public static boolean uneFlag = false;
	public static boolean dcseFlag = false;
	public static boolean pagetypeFlag = false;
	public static String uneRequest = "";
	public static String dcseRequest = "";
	public static boolean profileSwitch=false;
	public static List<String> audioList = new ArrayList<String>();
	
	public static Set<String> authorSet = new HashSet<String>();
	public static List<String> titleList = new ArrayList<String>();
	public static String trayType = "";
	public static boolean authorFlag = false;
	public static List<String> showsList = new ArrayList<String>();

	// Method to fetch PLAYER_EPISODE Request Properties

	public static void playerEpisodeRequestfetch() throws ParserConfigurationException, SAXException, IOException {

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
		dcseFlag = false;
		uneFlag = false;
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if (eElement.getAttribute("path").equals("/service/recommendation/widgets")
						&& eElement.getAttribute("host").equals("dm-prod.vootkids.com")) {

					cElement = (Element) eElement.getElementsByTagName("body").item(0);
					strResponse = cElement.getTextContent();

					if (uneFlag == true && dcseFlag == true)
						break;

					if (dcseFlag == false) {
						if (strResponse.contains("\"pageType\":\"PLAYER_EPISODE\"")
								&& strResponse.contains("DIFFERENT_CATEGORY_SIMILAR_EPISODE")) {
							// test.log(LogStatus.INFO, "Click event response was: " + strResponse);
							dcseFlag = true;
							test.log(LogStatus.INFO,
									"DIFFERENT_CATEGORY_SIMILAR_EPISODE widget type displayed was:\n" + strResponse);
							
							dcseRequest = strResponse;

						}
					}

					if (uneFlag == false) {
						if (strResponse.contains("\"pageType\":\"PLAYER_EPISODE\"")
								&& strResponse.contains("UP_NEXT_EPISODE")) {
							uneFlag = true;
							test.log(LogStatus.INFO, "UP_NEXT_EPISODE widget type displayed was:\n" + strResponse);
							
							
							uneRequest = strResponse;
						}
					}

				}

			}

			// break;
		}

		if (dcseFlag == true) {
			dcseMap.clear();
			String[] keys = dcseRequest.split(",\"");
			for (String mapvals : keys) {
				try {
					String mapSplits[] = mapvals.split("\\:");
					dcseMap.put("" + mapSplits[0].toString().trim().replaceAll("\"", ""),
							"" + mapSplits[1].toString().trim().replaceAll("\"", ""));
				} catch (Exception e) {
					
				}

			}

			System.out.println("Validating 'Different Category Values'");
			for (Map.Entry<String, String> diffMap : dcseMap.entrySet()) {
				String key = diffMap.getKey();
				String value = dcseMap.get(key);
				test.log(LogStatus.INFO, "Value for Key -----> '" + key + "' displayed was: " + value);
			}
		} else {
			test.log(LogStatus.FAIL, "'DIFFERENT_CATEGORY_SIMILAR_EPISODE' Widget Type was not displayed");
		}

		if (uneFlag == true) {
			uneMap.clear();
			String[] keys = uneRequest.split(",\"");
			for (String mapvals : keys) {
				try {
					String mapSplits[] = mapvals.split("\\:");
					uneMap.put("" + mapSplits[0].toString().trim().replaceAll("\"", ""),
							"" + mapSplits[1].toString().trim().replaceAll("\"", ""));
				} catch (Exception e) {
					
				}

			}
			System.out.println();
			System.out.println("Validating 'Up Next Values'");
			for (Map.Entry<String, String> upNextMap : uneMap.entrySet()) {
				String key = upNextMap.getKey();
				String value = uneMap.get(key);
				test.log(LogStatus.INFO, "Value for Key -----> '" + key + "' displayed was: " + value);
			}
		} else {
			test.log(LogStatus.FAIL, "\"'UP_NEXT_EPISODE' Widget Type was not displayed\"");
		}

	}

	// Method to capture User ID
	public static void captureUidandPidandKs() throws SAXException, IOException, ParserConfigurationException {
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
		// VootConstants.filePathxml = "C:\\Users\\ifocus\\Desktop\\login.xml";
		Document doc = dBuilder.parse(VootConstants.filePathxml);

		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");
		profileSwitch=false;
		outer: for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if ((eElement.getAttribute("path").equals("/users/v1/switch-profile.json")
						&& eElement.getAttribute("host").contains("asia-northeast") )) {
					profileSwitch=true;
					cElement = (Element) eElement.getElementsByTagName("body").item(0);
					strResponse = cElement.getTextContent();
					
					
					
					try {
						strResponse = strResponse.replaceAll("\\{", "");
						strResponse = strResponse.replaceAll("\\}", "");
						strResponse = strResponse.replaceAll("\"", "");

						String[] splitsProfile = strResponse.split(",\"");
						
						for(String splits : splitsProfile) {
							strResponse = splits.replaceAll("\"", "");
							if(splits.contains("uId")) {
								String[] uidSplit = splits.split("\\:");
								GlobalVariables.userId = "" + uidSplit[1].toString().trim().replaceAll("\"", "");;
								System.out.println("User Id displayed was: " + GlobalVariables.userId);
								break;
							}
						}
						
						
						//Capturing Profile Id
						cElement = (Element) eElement.getElementsByTagName("body").item(1);
						strResponse = cElement.getTextContent();
						
						
						try {
							strResponse=strResponse.replaceAll("\\{", "");
							strResponse=strResponse.replaceAll("\\}", "");
							//strResponse=strResponse.replaceAll("\"", "");
							
							String[] splitsProfile1 = strResponse.split(",\"");
							
							for(String splits: splitsProfile1) {
								if(splits.contains("id\":")) {
									//strResponse=strResponse.replaceAll("\"", "");
									String pidSplit[] = splits.split("\\:");
									GlobalVariables.profileId=""+pidSplit[1].toString().trim().replaceAll("\"", "");
									System.out.println("Displayed Profile ID: was: "+GlobalVariables.profileId);
									break outer;
								}
								
							}
							
						} catch (Exception e) {
							
						}
						
						
						//break;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				
				
			}
			
			

		}
		outer1: for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));
				if (eElement.getAttribute("path").equals("/users/v1/refresh-ks.json")
						&& eElement.getAttribute("host").contains("asia-northeast")) {
					cElement = (Element) eElement.getElementsByTagName("body").item(1);
					strResponse = cElement.getTextContent();

					try {
						strResponse = strResponse.replaceAll("\\{", "");
						strResponse = strResponse.replaceAll("\\}", "");
						// strResponse=strResponse.replaceAll("\"", "");

						String[] splitsProfile = strResponse.split(",\"");

						for (String splits : splitsProfile) {
							if (splits.contains("ks\":")) {
								// strResponse=strResponse.replaceAll("\"", "");
								String pidSplit[] = splits.split("\\:");
								GlobalVariables.ksValue = "" + pidSplit[1].toString().trim().replaceAll("\"", "");
								System.out.println("Displayed ks value was: " + GlobalVariables.ksValue);
								break outer1;
							}

						}

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
		
		if(profileSwitch==false) {
			outer2: for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) (nodeList.item(i));

					if ((eElement.getAttribute("path").equals("/users/v1/profiles.json")
							&& eElement.getAttribute("host").contains("asia-northeast") )) {
						profileSwitch=true;
						cElement = (Element) eElement.getElementsByTagName("body").item(0);
						strResponse = cElement.getTextContent();
						
						try {
							strResponse = strResponse.replaceAll("\\{", "");
							strResponse = strResponse.replaceAll("\\}", "");
							strResponse = strResponse.replaceAll("\"", "");

							String[] splitsProfile = strResponse.split(":");
							GlobalVariables.userId = "" + splitsProfile[1].toString().trim();
							System.out.println("User Id displayed was: " + GlobalVariables.userId);
							
							//Capturing Profile Id
							cElement = (Element) eElement.getElementsByTagName("body").item(1);
							strResponse = cElement.getTextContent();
							
							
							try {
								strResponse=strResponse.replaceAll("\\{", "");
								strResponse=strResponse.replaceAll("\\}", "");
								//strResponse=strResponse.replaceAll("\"", "");
								
								String[] splitsProfile1 = strResponse.split(",\"");
								
								for(String splits: splitsProfile1) {
									if(splits.contains("id\":")) {
										//strResponse=strResponse.replaceAll("\"", "");
										String pidSplit[] = splits.split("\\:");
										GlobalVariables.profileId=""+pidSplit[1].toString().trim().replaceAll("\"", "");
										System.out.println("Displayed Profile ID: was: "+GlobalVariables.profileId);
										break outer2;
									}
									
								}
								
							} catch (Exception e) {
								
							}
							
							
							//break;
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}
					
					
				}
				
				

			}
		}
		
	}
	
	//More from Author Ebooks
	
	public static void moreFromAuthorEbboks() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		int k = 0;

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				System.out.println("Charles Xml document session:" + systemId);
				return new InputSource(bais);
			}
		};

		dBuilder.setEntityResolver(resolver);

//		VootConstants.filePathxml = "C:\\Users\\ifocus\\Desktop\\ebooks and Episode DM.xml";
		Document doc = dBuilder.parse(VootConstants.filePathxml);
		doc.getDocumentElement().normalize();
		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		for (int i = 0; i < nodeList.getLength(); i++) {

			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if (eElement.getAttribute("path").equals("/service/recommendation/widgets")
						&& eElement.getAttribute("host").equals("dm-prod.vootkids.com")) {

					Element cElement = (Element) eElement.getElementsByTagName("body").item(0);
					strResponse = cElement.getTextContent();
					if (strResponse.contains("SAME_CATEGORY_SIMILAR_EBOOK")) {
						cElement = (Element) eElement.getElementsByTagName("body").item(1);
						strResponse = cElement.getTextContent();
						System.out.println("Response was: " + strResponse);

						try {
							JsonPath response = new JsonPath(strResponse);

							for (int j = 0; j < 5; j++) {
								try {
									trayType = response.getString("assets[" + j + "].trayType");
									if (trayType.equalsIgnoreCase("scseb")) {
										k = j;
										break;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							try {
								int totalItems = response.getInt("assets[" + k + "].totalItems");
								if (totalItems > 0 && totalItems == 1) {
									try {
										authorSet.add(response
												.getString("assets[" + k + "].items[" + (totalItems - 1) + "].author"));
										titleList.add(response
												.getString("assets[" + k + "].items[" + (totalItems - 1) + "].title"));
										authorFlag=true;
										break;
									} catch (Exception e) {
										// TODO: handle exception
									}

								} else if (totalItems > 1) {
									for (int j = 0; j < totalItems; j++) {
										try {
											authorSet.add(
													response.getString("assets[" + k + "].items[" + j + "].author"));
											titleList.add(
													response.getString("assets[" + k + "].items[" + j + "].title"));
											
										} catch (Exception e) {
											// TODO: handle exception
										}

									}
									
									authorFlag=true;
									break;
								} else {
									System.out.println("Total Items displayed under 'scseb' tryaType was empty");
									break;
								}
							} catch (Exception e) {
								// TODO: handle exception
							}

						} catch (Exception e) {
							// TODO: handle exception
						}

						break;

					} else {
						
					}

				}
			}
		}
		System.out.println("Number of Autor displayed was: "+authorSet.size());
		
		System.out.println("Author\tTitle\n"+authorSet+"\t:\t"+titleList);
		
	}
	
	//Data Monk to capture shows List
	public static void relatedShowsFetch() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		showsList.clear();
		int k = 0;

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				System.out.println("Charles Xml document session:" + systemId);
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

				if (eElement.getAttribute("path").equals("/service/recommendation/widgets")
						&& eElement.getAttribute("host").equals("dm-prod.vootkids.com")) {

					Element cElement = (Element) eElement.getElementsByTagName("body").item(0);
					strResponse = cElement.getTextContent();
					if (strResponse.contains("SAME_CATEGORY_SIMILAR_SERIES")) {
						cElement = (Element) eElement.getElementsByTagName("body").item(1);
						strResponse = cElement.getTextContent();
						System.out.println("Response was: " + strResponse);

						try {
							JsonPath response = new JsonPath(strResponse);

							for (int j = 0; j < 5; j++) {
								try {
									trayType = response.getString("assets[" + j + "].trayType");
									if (trayType.equalsIgnoreCase("scss")) {
										k = j;
										break;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							try {
								int totalItems = response.getInt("assets[" + k + "].totalItems");
								if (totalItems > 0 && totalItems == 1) {
									try {
										
										showsList.add(response
												.getString("assets[" + k + "].items[" + (totalItems - 1) + "].refSeriesTitle"));
										break;
									} catch (Exception e) {
										// TODO: handle exception
									}

								} else if (totalItems > 1) {
									for (int j = 0; j < totalItems; j++) {
										try {
											showsList.add(response
													.getString("assets[" + k + "].items[" +j+"].refSeriesTitle"));
											
										} catch (Exception e) {
											// TODO: handle exception
										}

									}
									
									//authorFlag=true;
									break;
								} else {
									System.out.println("Total Items displayed under 'scseb' tryaType was empty");
									break;
								}
							} catch (Exception e) {
								// TODO: handle exception
							}

						} catch (Exception e) {
							// TODO: handle exception
						}

						break;

					} else {

					}

				}
			}
		}
		System.out.println("Number of Shows displayed was: "+showsList.size());
		
		System.out.println("Shows List\t:"+showsList);
		//System.out.println("Number of Autor displayed was: "+titleList);
	}
	
	public static void relatedAudioStories() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		int k = 0;

		EntityResolver resolver = new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) {
				String empty = "";
				ByteArrayInputStream bais = new ByteArrayInputStream(empty.getBytes());
				System.out.println("Charles Xml document session:" + systemId);
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

				if (eElement.getAttribute("path").equals("/service/recommendation/widgets")
						&& eElement.getAttribute("host").equals("dm-prod.vootkids.com")) {

					Element cElement = (Element) eElement.getElementsByTagName("body").item(0);
					strResponse = cElement.getTextContent();
					if (strResponse.contains("SAME_CATEGORY_SIMILAR_AUDIO_STORY") && strResponse.contains("DIFFERENT_CATEGORY_SIMILAR_AUDIO_STORY")) {
						cElement = (Element) eElement.getElementsByTagName("body").item(1);
						strResponse = cElement.getTextContent();
						System.out.println("Response was: " + strResponse);

						try {
							JsonPath response = new JsonPath(strResponse);

							for (int j = 0; j < 5; j++) {
								try {
									trayType = response.getString("assets[" + j + "].trayType");
									if (trayType.equalsIgnoreCase("dcsas")) {
										k = j;
										break;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}

							try {
								int totalItems = response.getInt("assets[" + k + "].totalItems");
								if (totalItems > 0 && totalItems == 1) {
									try {
										
										audioList.add(response
												.getString("assets[" + k + "].items[" + (totalItems - 1) + "].refSeriesTitle"));
										break;
									} catch (Exception e) {
										// TODO: handle exception
									}

								} else if (totalItems > 1) {
									for (int j = 0; j < totalItems; j++) {
										try {
											audioList.add(response
													.getString("assets[" + k + "].items[" +j+"].refSeriesTitle"));
											
										} catch (Exception e) {
											// TODO: handle exception
										}

									}
									
									//authorFlag=true;
									break;
								} else {
									System.out.println("Total Items displayed under 'dcsas' tryaType was empty");
									break;
								}
							} catch (Exception e) {
								// TODO: handle exception
							}

						} catch (Exception e) {
							// TODO: handle exception
						}

						break;

					} else {

					}

				}
			}
		}
		System.out.println("Number of Shows displayed was: "+audioList.size());
		
		System.out.println("Shows List\t:"+audioList);
		//System.out.println("Number of Autor displayed was: "+titleList);
	}
	
}
