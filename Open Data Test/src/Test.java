import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class Test {

	public static void main(String[] args) {
		String url = "..//XMLs//triMago.xml";
		XmlDtoParser<AirQuality> xmlDtoParser = new XmlDtoParser<AirQuality>(url){
			@Override
			protected List<AirQuality> getParsedXmlData(DocumentBuilder dBuilder) {
				return null;
			}
		};
		
		try {
			xmlDtoParser.parseData();
		} catch (IOException | SAXException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
