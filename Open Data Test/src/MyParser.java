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

import ParserUtility.DataParsers.WebDataHandler;
import ParserUtility.Request.RequestAction;
import ParserUtility.Request.WebRequestAction;
import WebParserUtility.DataParsers.DtoParsers.DTOParser;
import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class MyParser  {
	final static String LINK = "http://data.gov.tw/iisi/logaccess/46147?dataUrl=http://opendata.epa.gov.tw/ws/Data/ATM00564/?%24skip=0&%24top=1000&format=xml&ndctype=XML&ndcnid=34766";
	

	public static void main(String[] args) {
		
	}
	
}

	




