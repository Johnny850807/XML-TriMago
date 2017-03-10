package WebParserUtility.DataParsers.DtoParsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class XmlDtoParser<T> extends DTOParser{
	
	@Override
	public void close() throws IOException {}

	@Override
	protected List<T> createParsedData(String document) throws SAXException, IOException, ParserConfigurationException  {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		dBuilder.parse(new ByteArrayInputStream(document.getBytes()));
		return getParsedXmlData(dBuilder);
	}
	
	protected abstract List<T> getParsedXmlData(DocumentBuilder dBuilder);

}
