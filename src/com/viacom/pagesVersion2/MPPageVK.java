package com.viacom.pagesVersion2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.viacom.utils.Utilities;
import com.viacom.utils.VootConstants;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jayway.restassured.response.Response;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class MPPageVK extends BasePageV2 {

	public MPPageVK(AndroidDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	public static String strResponse = "";

	public static Element cElement;

	public static String decodedString = "";
	
	public static boolean respFlag=false;

	public static String[] charlesValues;
	public static ArrayList<String> Result = new ArrayList<String>();
	public static boolean actionEndFlag = false;
	public static boolean actionStartFlag = false;
	public static boolean actionVisitAdFlag = false;
	public static boolean quitBeforePlaybackFlag = false;

	// Triggered from flags
	public static boolean homeFlag = false;
	public static boolean kidsFlag = false;
	public static boolean showsFlag = false;
	public static boolean newsFlag = false;
	public static boolean channelsFlag = false;
	public static boolean moviesFlag = false;
	static boolean requestFlag = false;

	static boolean impressionFlag = false;
	public static boolean sponsorAdValidationHome = false;
	public static boolean sponsorAdValidationShow = false;
	static boolean impressionValidationFlag = false;
	static boolean requestValidationFlag = false;

	static ArrayList<String> TriggeredFromList = new ArrayList<String>();

	public static Map<String, String> mixevnts = new HashMap<String, String>();

	@FindBy(xpath = "//div[contains(@class,'adbannerWrapper')]")
	public WebElement imageadBanner;

	@FindBy(xpath = "//div[contains(@class,'sponsorAd')]//span[contains(.,'Digital Sponsor')]")
	public WebElement sponserAdtext;

	@FindBy(xpath = "//div[contains(@class,'sponsorAd')]//span[contains(.,'Digital Sponsor')]/../img")
	public WebElement sponsors;

	@FindBy(xpath = "//div[contains(@class,'VSingletrayTitle') and contains(.,'Continue Watching')]/..//a[contains(.,'More')]")
	public WebElement cwMorebtn;

	@FindBy(xpath = "(//div[contains(@class,'VtitleContainer') and contains(.,'All Episodes')]/..//div[contains(@class,'continueWatchActionBtn')])[1]")
	public WebElement cwAllEpisodesActionbutton;

	@FindBy(xpath = "(//div[contains(@class,'VtitleContainer') and contains(.,'All Episodes')]/..//div[contains(@class,'removeCardTooltip')])[1]")
	public WebElement cwEpisodesRemoveBtn;

	@FindBy(xpath = "(//div[contains(@class,'VtitleContainer') and contains(.,'All Episodes')]/..//div[contains(@class,'cWatchepisodeCards')]//a)[1]")
	public WebElement cwEpisodeshref;

	@FindBy(xpath = "(//div[contains(@class,'VtitleContainer') and contains(.,'Movies')]/..//div[contains(@class,'continueWatchActionBtn')])[1]")
	public WebElement cwMoviesActionbutton;

	@FindBy(xpath = "(//div[contains(@class,'VtitleContainer') and contains(.,'Movies')]/..//div[contains(@class,'removeCardTooltip')])[1]")
	public WebElement cwMoviesRemoveBtn;

	@FindBy(xpath = "(//div[contains(@class,'VtitleContainer') and contains(.,'Movies')]/..//div[contains(@class,'cWatchMovieCards')]//a)[1]")
	public WebElement cwMovieshref;

	@FindBy(xpath = "(//div[contains(@class,'sponsorship')])[1]/./../../../..//div[contains(@class,'sponsorship')]")
	public WebElement sponserAd;

	@FindBy(xpath = "(//div[contains(@class,'sponsorship')])[1]/./../../../..//div[contains(@class,'sponsorship')]//iframe")
	public WebElement sponserAdFrame;

	@FindBy(xpath = "(//h2[contains(@class,'VSingletrayTitle') and contains(.,'Up Next')]/../..//div[contains(@class,'mediaSmallwithMetaCard')])[2]")
	public WebElement playlistContent;

	@FindBy(xpath = "//div[@class='lrsharecontainer']//a[@class='facebook']")
	public WebElement fbShareLink;

	@FindBy(xpath = "//span[.='Post to Facebook']")
	public WebElement fbLinkPost;

	@FindBy(xpath = "//div[@class='lrsharecontainer']//a[@class='googleplus']")
	public WebElement gplusShareLink;

	@FindBy(xpath = "//div[@class='lrsharecontainer']//a[@class='twitter']")
	public WebElement twitterShareLink;

	@FindBy(xpath = "//input[@id='username_or_email']")
	public WebElement twitterEmailTextField;

	@FindBy(xpath = "//input[@type='password']")
	public WebElement twitterPwdTextField;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement twitterLoginPost;

	@FindBy(xpath = "//fieldset//input[@type='submit']")
	public WebElement tweetbutton;

	// Masthead Ad Banner controls
	@FindBy(xpath = "//div[contains(@class,'ad-video')]//img[contains(@src,'play.png')]")
	public WebElement mastheadADPlaybtn;

	@FindBy(xpath = "//div[contains(@class,'ad-video')]//img[contains(@src,'volume')]")
	public WebElement mastheadADvolumebtn;

	@FindBy(xpath = "//div[contains(@class,'ad-video')]//img[contains(@src,'replay')]")
	public WebElement mastheadADrePlaybtn;

	@FindBy(xpath = "//div[contains(@class,'ad-video')]//img[contains(@src,'pause')]")
	public WebElement mastheadAdpausebtn;

	@FindBy(xpath = "//button[.='Close']")
	public WebElement kidsPagePopupClose;

	@FindBy(xpath = "//div[contains(@class,'playkit-dropdown-button')]//span")
	public WebElement videoqualitydefaultbtn;

	@FindBy(xpath = "//span[.='High']")
	public WebElement videoqualityhigh;

	@FindBy(xpath = "//span[.='Low']")
	public WebElement videoqualityLow;

	@FindBy(xpath = "//span[.='Medium']")
	public WebElement videoqualityMedium;

	@FindBy(xpath = "//span[.='Auto']")
	public WebElement videoqualityAuto;

	@FindBy(xpath = "//button[contains(@aria-label,'Play')]")
	public WebElement playBtn;

	@FindBy(xpath = "//div[@class='details']//small[not(contains(.,'Form'))]/../..//div[last()][not(@class='play')]/..//img")
	public WebElement carouselHavingShowContent;

	@FindBy(xpath = "//div[@class='details']//small[not(contains(.,'Form'))]/../..//div[last()][(@class='play')]/..//img")
	public WebElement carouselHavingEpisodeContent;

	@FindBy(xpath = "//div[@class='details']//small[not(contains(.,'Form'))]/../..//div[last()][(@class='play')]/..//img//ancestor::div[@data-index]")
	public List<WebElement> carouselEpisodes;

	@FindBy(xpath = "//ancestor::div[@class='kids_slider_container']//div[(@class='play')]//ancestor::div[@data-index]")
	public List<WebElement> kidsCarouselEpisodes;

	@FindBy(xpath = "//div[@class='details']//small[not(contains(.,'Form'))]/../..//div[last()][not(@class='play')]/..//img//ancestor::div[@data-index]")
	public WebElement carouselAssetNumber;

	@FindBy(xpath = "//div[@class='details']//small[not(contains(.,'Form'))]/../..//div[last()][not(@class='play')]/..//img//ancestor::div[@data-index='2']")
	public WebElement carouselAssetNumber2number;

	@FindBy(xpath = "//div[@class='details']//small[not(contains(.,'Form'))]/../..//div[last()][not(@class='play')]/..//img//ancestor::div[@data-index='2']//a")
	public WebElement carouselAssetNumber2name;

	@FindBy(xpath = "//div[@class='play']//ancestor::div[@class='mdl-grid']//div[@data-index]//a")
	public List<WebElement> trayEpisodes;

	@FindBy(xpath = "//div[@class='VSBannerContainer']//img")
	public WebElement heroAsset;

	@FindBy(xpath = "//div[@class='VSTitle']//h1")
	public WebElement showName;

	@FindBy(xpath = "//div[@class='episodeTitle']//h1")
	public WebElement newsChannelName;

	@FindBy(xpath = "(//div[@class='VSTags']/h2)")
	public WebElement kidsShowDetails;

	@FindBy(xpath = "(//div[@class='VSTags']/h2/a)")
	public List<WebElement> showDetails;

	@FindBy(xpath = "(//div[@class='VSTags']/h2/a)[1]")
	public WebElement channelName;

	@FindBy(xpath = "(//div[@class='VSTags']/h2/a)[2]")
	public WebElement Genre;

	@FindBy(xpath = "(//div[@class='VSTags']/h2/a)[3]")
	public WebElement Language;

	@FindBy(xpath = "//div[@class='adDetails']")
	public WebElement adBanner;

	@FindBy(xpath = "//div[@class='mainCaroselContainer']")
	public WebElement carousal;

	@FindBy(xpath = "//div[@class='VSBannerContainer']")
	public WebElement contentBanner;

	@FindBy(xpath = "//div[@class='slider_wrap_kids']")
	public WebElement kidsCarousal;

	@FindBy(xpath = "//div[@class='VtitleContainer']//h2[@class='title' or @class='VSingletrayTitle']")
	public List<WebElement> trayTitles;

	@FindBy(xpath = "//*[@class='VSearchHomeTitle']")
	public List<WebElement> trayTitlesInSearch;

	@FindBy(xpath = "//div[@class='tabsContainer']//h2")
	public WebElement trayTitleAllEpisodes;

	@FindBy(xpath = "//div[contains(@class,'playkit-overlay-action')]")
	public WebElement playerContainer;

	@FindBy(xpath = "//i[contains(@class,'playkit-icon-maximize')]")
	public WebElement playerMaximize;

	@FindBy(xpath = "//i[contains(@class,'playkit-icon-minimize')]")
	public WebElement playerMinimize;

	@FindBy(xpath = "//i[contains(@class,'playkit-icon-settings')]")
	public WebElement playerSettings;

	@FindBy(xpath = "//a[contains(@class,'playkit-scrubber')]")
	public WebElement playerScrubber;

	@FindBy(xpath = "//i[contains(@class,'playkit-icon-language')]")
	public WebElement playerMTASettings;

	@FindBy(xpath = "//i[contains(@class,'playkit-icon-next')]")
	public WebElement playerNext;

	@FindBy(xpath = "//i[contains(@class,'playkit-icon-captions')]")
	public WebElement playerCaptions;

	@FindBy(xpath = "//div[contains(@class,'playkit-time-display')]//span")
	public WebElement playerTimeDetails;

	@FindBy(xpath = "//div[@class='captions-li']//p[not(contains(@class,'Off'))]")
	public List<WebElement> subtitleOptions;

	@FindBy(xpath = "//div[@class='captions-li']//p[contains(@class,'Off')]")
	public WebElement subtitleOff;

	@FindBy(xpath = "//div[contains(@class,'settings-li')]//div//p[not(contains(@class,'a-selected'))]")
	public List<WebElement> playerNonSelectedQualityOptions;

	@FindBy(xpath = "//div[@class='language-li']//p[not(contains(@class,'a-selected'))]")
	public List<WebElement> playerNonSelectedLanguages;

	@FindBy(xpath = "//div[contains(@class,'language-li')]//p[(contains(@class,'a-selected'))]")
	public List<WebElement> playerSelectedLanguage;

	@FindBy(xpath = "//div[@class='language-li default']//p")
	public List<WebElement> playerDefaultLanguage;

	@FindBy(xpath = "//a[@title='Colors Hindi']")
	public WebElement channelColorsHindi;

	@FindBy(xpath = "//div[contains (@class,'allEpisodes')]/../..//div[@class='mdl-grid']//a")
	public WebElement allEpisodesTabFirstEpisode;

	@FindBy(xpath = "(//div[contains (@class,'allEpisodes')]/../..//div[@class='mdl-grid']//a)[2]")
	public WebElement allEpisodesTabSecondEpisode;

	@FindBy(xpath = "(//div[contains (@class,'allEpisodes')]/../..//div[@class='mdl-grid']//a)[3]")
	public WebElement allEpisodesTabThirdEpisode;

	@FindBy(xpath = "//h2[text()='All Episodes']//ancestor::div[contains (@class,'VtitleContainer')]/../..//div[@class='mdl-grid']//a")
	public WebElement allEpisodesTabKidsFirstEpisode;

	@FindBy(xpath = "//i[contains(@class,'playkit-icon-next')]")
	public WebElement vodPlayerNextButton;

	@FindBy(xpath = "//div[@class='tabsContainer']//div[contains(@class,'allShows tabs')]")
	public WebElement languagesShowsTab;

	@FindBy(xpath = "(//div[contains(@class,'VSingletray')]//div[@class='mdl-grid']//div//a)[2]")
	public WebElement languagesShowsTabSecondShow;

	@FindBy(xpath = "(//div[@class='sponsorship']/../../../..//h2)[1]")
	public WebElement sponsorAdTrayTitle;

	@FindBy(xpath = "(//div[@class='sponsorship']/../../../..//h2)[1]/../../..")
	public WebElement sponsorAdTrayNumber;

	@FindBy(xpath = "//div[@class='sponsorship']")
	public WebElement sponsorAd;

	@FindBy(xpath = "//div[@class='searchTabsResultsContainer']//div[@class='play']")
	public WebElement allSearchResultsFirstEpisode;

	@FindBy(xpath = "//div[@class='searchTabsResultsContainer']//div[@class='MSWMWrap']")
	public WebElement searchResultsShowsTabFirstShow;

	@FindBy(xpath = "//div[@class='searchTabsResultsContainer']//div[@class='MSWMWrap']//div[@class='play']")
	public WebElement searchResultsKidsTabFirstEpisode;

	@FindBy(xpath = "//*[@class='VSearchHomeSearchIcon']")
	public WebElement searchPageSearchIcon;

	@FindBy(xpath = "//a[@id='movies_dropbtn']//parent::div[contains(@class,'megaMenu')]//div//a[@class='mdl-layout__tab']")
	public List<WebElement> moviesMegaMenu;

	@FindBy(xpath = "//a[@id='shows_dropbtn']//parent::div[contains(@class,'megaMenu')]//div//a[@class='mdl-layout__tab']")
	public List<WebElement> showsMegaMenu;

	@FindBy(xpath = "//a[@id='shows_dropbtn']/..//div[@id='Featured Shows']//a")
	public List<WebElement> featuredShowsList;

	@FindBy(xpath = "//a[@id='news_dropbtn']//parent::div[contains(@class,'megaMenu')]//div//a[@class='mdl-layout__tab']//img")
	public List<WebElement> newsMegaMenu;

	@FindBy(xpath = "//div//ul//li[@class='replay']")
	public WebElement vodReplayButton;

	@FindBy(xpath = "//div[@class='rewind-hover-layer']")
	public WebElement vodAutoPlaybackReplayButton;

	@FindBy(xpath = "//div[@class='homeSearchtabsContainer']//div[contains(@class,'tabsContainer')]//*[@id='Shows']")
	public WebElement searchResultsShowsTab;

	@FindBy(xpath = "//div[@class='homeSearchtabsContainer']//div[contains(@class,'tabsContainer')]//*[@id='Kids']")
	public WebElement searchResultsKidsTab;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'Kannada Shows')]/..//div[@class='MLWMLWrap'])[3]")
	public WebElement topKannadaShowsTrayContent;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'News Channels')]/..//div[contains(@class,'news-card')])[1]")
	public WebElement liveNewsCardTopNewsChannel;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'News: ')]/..//div[@class='MSWMWrap'])[1]")
	public WebElement vodNewsCardPopularNewsTray;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'Similar')]/..//div[@class='MSWMWrap'])[1]")
	public WebElement similarShowsTrayDetailPage;

	@FindBy(xpath = "//div[@class='channels_name' and .='Colors Hindi']/../../..")
	public WebElement colorsHindiChannelChannelPage;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'Popular Kids')]/..//div[@class='VPlaceholderImg'])[1]")
	public WebElement popularKidsShow;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'All Kids Shows')]/..//div[@class='VPlaceholderImg'])[1]")
	public WebElement allKidsShowsDetailPage;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'News Shows')]/..//div[@class='MSWMWrap'])[1]")
	public WebElement popularNewsShowNewsPage;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'Live News:')]/..//div[@class='play'])[1]")
	public WebElement liveNewsShowNewsPage;

	@FindBy(xpath = "(//div[@class='VtitleContainer' and contains(.,'News Channels')]/..//div[contains(@class,'news-card')])[1]")
	public WebElement liveChannelCardPlaybackPageNewsLive;

	@FindBy(xpath = "//div[@class='mdl-tabs__panel is-active']//span[@class='mdl-chip']")
	public List<WebElement> filterOptions;

	static ArrayList<String> base64encodedString = new ArrayList<String>();
	static ArrayList<String> decodedResponseloggedIn = new ArrayList<String>();

	public static void mixpnlEventsfetchLoggedInuser(String EventName)
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

		mixevnts.clear();
		base64encodedString.clear();
		decodedResponseloggedIn.clear();

		dBuilder.setEntityResolver(resolver);

		Document doc = dBuilder.parse(VootConstants.filePathxml);

		doc.getDocumentElement().normalize();

		System.out.println("Root element of session xml:" + doc.getDocumentElement().getNodeName());

		NodeList nodeList = doc.getElementsByTagName("transaction");

		// test.log(LogStatus.INFO, "Fetching base64 encoding value and converting all
		// hex codes in a String");
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) (nodeList.item(i));

				if ((eElement.getAttribute("path").contains("/track")
						|| eElement.getAttribute("path").contains("/engage"))
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
		
		respFlag=false;
		for (String base64String : base64encodedString) {
			decodedString="";
			byte[] byteArray = Base64.decodeBase64(base64String.getBytes());
			// System.out.println(Arrays.toString(byteArray));
			decodedString = new String(byteArray).toString().trim();

			
			System.out.println("Decoded String was: " + decodedString);
			
		
			
			if(respFlag==false) {
				if (decodedString.contains(EventName)) {
//					test.log(LogStatus.PASS, "Event '"+EventName+"' was triggered" );
					// decodedResponseloggedIn.add(decodedString);
					
					int index=decodedString.indexOf(EventName);
					String newString=decodedString.substring(index);
					decodedString = newString.split("event")[0];
					
					test.log(LogStatus.INFO, "Mix Panel '" + EventName + "' response was: " + decodedString);
					
					test.log(LogStatus.PASS, "Event '"+EventName+"' was triggered" );

					String[] mixsplit = decodedString.split(",\"");

					for (String mixevents : mixsplit) {
						System.out.println("Mix Panel Events are: " + mixevents);
					}

					// System.out.println("MP Properties Size: " +
					// VootConstants.MPProperties.size());
					System.out.println("Charles Properties size: " + mixsplit.length);

					for (String mixevents : mixsplit) {

						try {

							String mixeventskeyVal[] = mixevents.split("\\:");

							String firstpart = mixeventskeyVal[0].toString().trim();
							String secondpart = mixeventskeyVal[1].toString();

							if (firstpart.contains("\"")) {
								firstpart = firstpart.replaceAll("\"", "");
							}
							if (secondpart.contains("\"")) {
								secondpart = secondpart.replaceAll("\"", "");
							}

							mixevnts.put(firstpart, secondpart);
							respFlag=true;
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					break;
				} else {
					System.out.println("Fetching decoded string was fail");
					
				}
			}
			
		}
		
		

		System.out.println("Map of fetched events" + mixevnts);

		for (Map.Entry<String, String> entry1 : mixevnts.entrySet()) {
			System.out.println("Entry Key: " + entry1.getKey());
			System.out.println("Entry value: " + mixevnts.get(entry1.getKey()));
		}

		
			
		if(respFlag==false) {
			test.log(LogStatus.FAIL, "Response not found From Charls Event : " + EventName);
		}
		else {
			
		}
		
	}
	
	

}
