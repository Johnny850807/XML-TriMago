package WebParserUtility.DataParsers.DtoParsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class XmlDtoParser<T> extends DTOParser{
	private String filePath;
	private String document;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document xmlDocument;
	private boolean parseFromFilePath;
	
	public XmlDtoParser(){
		parseFromFilePath = false;
	}
	
	public XmlDtoParser(String filePath){
		super();
		this.filePath = filePath;
		parseFromFilePath = true;
	}
	@Override
	protected List<T> createParsedData(String ...document) throws SAXException, IOException, ParserConfigurationException, TransformerException  {
		if (document.length > 0)
			this.document = document[0];
		dbFactory = initFactory();
		dBuilder = initBuilder();
		xmlDocument = parseXmlDocument();
		return getParsedXmlData(dBuilder,xmlDocument);
	}
	
	private DocumentBuilderFactory initFactory(){
		dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		dbFactory.setNamespaceAware(true);
		return dbFactory;
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException, SAXException, IOException{
		dBuilder = dbFactory.newDocumentBuilder();
		dBuilder.setErrorHandler(new MyXmlErrorHandler());
		return dBuilder;
	}
	
	private Document parseXmlDocument() throws SAXException, IOException{
		if (parseFromFilePath)
			return dBuilder.parse(filePath);
		else
		{
			return dBuilder.parse(new ByteArrayInputStream(document.getBytes("UTF-8")));
		}
			
	}
	
	protected abstract List<T> getParsedXmlData(DocumentBuilder dBuilder , Document xmlDocument) throws TransformerException;
	
	@Override
	public void close() throws IOException {}
	
	
	class MyXmlErrorHandler implements ErrorHandler{
		@Override
		public void warning(SAXParseException exception) throws SAXException {
			System.out.println("Warning : " +exception.getMessage() );
		}
		@Override
		public void error(SAXParseException exception) throws SAXException {
			throw exception;
		}
		@Override
		public void fatalError(SAXParseException exception) throws SAXException {
			throw exception;
		}
	}
	
	
	
}
