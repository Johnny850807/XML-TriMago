package xml;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public abstract class XPathResultServlet extends BaseXslTransformServlet{
	private DocumentBuilder builder;
	private Document original;
	private Document resultDocument;
	private Element rootNode;
	@Override
	protected Source getXmlStreamSource() throws Exception {
		initBuilder();
	    initDocuments();
	    initRootNode();
	    String expression = getXpathExpression();
	    log(expression);
	    Element rootElement = original.getDocumentElement();
	    log("root element getting completed");
	    buildUpXpathResultDocument( resultDocument,XPathAPI.selectNodeIterator
	    		  (original, expression , rootElement));
		Source result = new DOMSource(resultDocument);
		return result;
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		//factory.setValidating(true);
		builder = factory.newDocumentBuilder();
		//builder.setErrorHandler(new MyXmlErrorHandler());
	    return builder;
	}
	
	private void initDocuments() throws SAXException, IOException{
		original = builder.parse(new File(xmlPath));
	    original.getDocumentElement().normalize();
	    resultDocument = builder.newDocument();
	}
	
	private void initRootNode(){
		String nameSpace = getNameSpaceName();
		String nameValue = getNameSpaceValue();
		String rootNodeName = getRootNodeName();
		rootNode = resultDocument.createElementNS
	    		(nameValue,nameSpace+":"+rootNodeName);
		rootNode.setAttributeNS("http://www.w3.org/2000/xmlns/",
	    		"xmlns:"+nameSpace, 
	    		nameValue);
		log("Root: "+nameSpace+":"+rootNodeName+", result name : " + rootNode.getNodeName());
		log("NameSpace: "+"xmlns:"+nameSpace+"  "+nameValue);
	    resultDocument.appendChild(rootNode);
	    log("append end");
	}
	
	protected abstract String getRootNodeName();
	protected abstract String getNameSpaceValue();
	protected abstract String getNameSpaceName();
	
	protected void buildUpXpathResultDocument(Document resultDocument,NodeIterator resultNodes) throws TransformerException{
		//hook method , create your own xml logics
		Node n;
		int count = 0;
		log("開始增加節點");
		while ((n = resultNodes.nextNode())!= null) 
		{ 
			count ++;
			Node newNode = resultDocument.importNode(n, true);
		    resultDocument.getDocumentElement().appendChild(newNode);
		}
		log("搜尋結果:"+count+"筆");
	}

	protected abstract String getXpathExpression();
	
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
