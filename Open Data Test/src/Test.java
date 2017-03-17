import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ParserUtility.DataParsers.FileDataHandler;
import ParserUtility.DataParsers.WebDataHandler;
import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class Test {
	final static String LINK = "http://data.gov.tw/iisi/logaccess/46147?dataUrl=http://opendata.epa.gov.tw/ws/Data/ATM00564/?%24skip=0&%24top=1000&format=xml&ndctype=XML&ndcnid=34766";
	final static String url = "triMago.xml";
	public static void main(String[] args) {
		XmlDtoParser myParser = new XmlDtoParser(url){
			@Override
			protected List getParsedXmlData(DocumentBuilder dBuilder) {
				return null;
			}
		};
		try {
			myParser.parseData("");
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
}
