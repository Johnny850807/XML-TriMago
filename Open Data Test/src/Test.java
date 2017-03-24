import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class Test {
	final static String LINK = "C:\\Users\\user\\Desktop\\apache-tomcat-6.0.41\\webapps\\TriMago\\WEB-INF\\classes\\model\\triMago.xml";
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
