package WebParserUtility.DataParsers.DtoParsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
		dbFactory.setNamespaceAware(true);
		return dbFactory;
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException, SAXException, IOException{
		dBuilder = dbFactory.newDocumentBuilder();
		dBuilder.setErrorHandler(new MyXmlErrorHandler());
		
		if (parseFromUrl)
		{
			Document document = dBuilder.parse(url);
			 NodeList nodeList = document.getElementsByTagName("Waterball:restaurant");
			System.out.println("¤¸¯À¼Æ¶q¡G " +nodeList.getLength() );
			for ( int i = 0 ; i < nodeList.getLength() ; i ++ )
				System.out.println(nodeList.item(i).getNodeName() + " ");
		}
		else
			dBuilder.parse(new ByteArrayInputStream(document.getBytes()));
		return dBuilder;
	}
	
	protected abstract List<T> getParsedXmlData(DocumentBuilder dBuilder);
	
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
