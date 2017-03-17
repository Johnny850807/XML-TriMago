package WebParserUtility.DataParsers.DtoParsers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class XmlDtoParser<T> extends DTOParser{
	private String url;
	private String document;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private boolean parseFromUrl;
	
	public XmlDtoParser(){
		parseFromUrl = false;
	}
	
	public XmlDtoParser(String url){
		super();
		this.url = url;
		parseFromUrl = true;
	}
	@Override
	protected List<T> createParsedData(String ...document) throws SAXException, IOException, ParserConfigurationException  {
		if (document.length > 0)
			this.document = document[0];
		dbFactory = initFactory();
		dBuilder = initBuilder();
		return getParsedXmlData(dBuilder);
	}
	
	private DocumentBuilderFactory initFactory(){
		dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		return dbFactory;
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException, SAXException, IOException{
		dBuilder = dbFactory.newDocumentBuilder();
		dBuilder.setErrorHandler(new MyXmlErrorHandler());
		
		if (parseFromUrl)
			dBuilder.parse(new File(url));
		else
			dBuilder.parse(new ByteArrayInputStream(document.getBytes()));
		return dBuilder;
	}
	
	@Override
	public void close() throws IOException {}
	
	protected abstract List<T> getParsedXmlData(DocumentBuilder dBuilder);
	
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
