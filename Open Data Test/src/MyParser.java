import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import WebParserUtility.DTOParser;
import WebParserUtility.WebDataParser;
import WebParserUtility.Request.RequestAction;
import WebParserUtility.Request.WebRequestAction;
import WebParserUtility.XmlParser.XmlDtoParser;
import WebParserUtility.XmlParser.XmlParserBuilder;

public class MyParser extends WebDataParser {
	final static String LINK = "http://data.gov.tw/iisi/logaccess/46147?dataUrl=http://opendata.epa.gov.tw/ws/Data/ATM00564/?%24skip=0&%24top=1000&format=xml&ndctype=XML&ndcnid=34766";
	public MyParser(String url) throws Exception {
		super(url);
	}

	public static void main(String[] args) {
		WebDataParser mP = null;
		try {
			mP = new MyParser(LINK);
			ArrayList<AirQuality> airs =  (ArrayList<AirQuality>) mP.getParsedDataSet();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				mP.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	protected RequestAction createRequestAction() {
			return new WebRequestAction(getConnection()){
				@Override
				protected String getAction() {
					return WebRequestAction.GET;
				}
			};
	}

	@Override
	protected DTOParser createDTOParser() {
		XmlParserBuilder<AirQuality> builder = new XmlParserBuilder<AirQuality>();
		try {
			return  new XmlDtoParser(builder.addKey("siteId")
			.addKey("siteName")
			.addKey("itemId")
			.addKey("itemName")
			.addKey("itemEngName")
			.addKey("itemUnit")
			.classType(AirQuality.class)
			.entityName("Data")
			.build());
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return null;
		};
	}

	




