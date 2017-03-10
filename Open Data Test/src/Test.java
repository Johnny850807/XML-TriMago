import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class Test {

	public static void main(String[] args) {
		XmlDtoParser<AirQuality> xmlDtoParser = new XmlDtoParser<AirQuality>(){
			@Override
			protected List<AirQuality> getParsedXmlData(DocumentBuilder dBuilder) {
				return null;
			}
		};
	}

}
