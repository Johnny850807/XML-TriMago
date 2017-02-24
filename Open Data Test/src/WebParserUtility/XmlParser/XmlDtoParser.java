package WebParserUtility.XmlParser;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import WebParserUtility.DTOParser;
import WebParserUtility.XmlParser.XmlParserBuilder.MyXmlHandler;

public class XmlDtoParser<T> extends DTOParser{
	private SAXParserFactory saxfactory;
	private SAXParser saxParser; 
	private Stack<String> tagStack = new Stack();
	private MyXmlHandler xmlHandler;

	public XmlDtoParser(MyXmlHandler xmlHandler) throws ParserConfigurationException, SAXException{
		this.xmlHandler = xmlHandler;
		saxfactory = SAXParserFactory.newInstance(); 
		saxParser = saxfactory.newSAXParser();
	}
	
	@Override
	public void close() throws IOException {}

	@Override
	protected List<T> createParsedData(String document) throws SAXException, IOException  {
		saxParser.parse( document, xmlHandler); 
		return xmlHandler.getEntityList();
	}

}
