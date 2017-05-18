import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class MyXmlDataParser extends XmlDtoParser<Document>{

	@Override
	protected Document getParsedXmlData(DocumentBuilder arg0, Document arg1) throws TransformerException {
		return arg1;
	}

}
