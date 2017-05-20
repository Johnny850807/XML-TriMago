import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

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
	    buildUpXpathResultDocument();
 
		Source result = new DOMSource(resultDocument);
		outputResultForTesting(result);
		return result;
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		builder = factory.newDocumentBuilder();
	    return builder;
	}
	
	private void initDocuments() throws SAXException, IOException{
		original = builder.parse(new File(xmlPath));
	    original.getDocumentElement().normalize();
	    resultDocument = builder.newDocument();
	}
	
	private void initRootNode(){
		rootNode = resultDocument.createElementNS
	    		("http://g9.xml.csie.mcu.edu.tw","Waterball:WebSite");
	    rootNode.setAttributeNS("http://www.w3.org/2000/xmlns/",
	    		"xmlns:Waterball" , 
	    		"http://g9.xml.csie.mcu.edu.tw");
	}
	
	private void buildUpXpathResultDocument() throws TransformerException{
		String expression = getXpathExpression();
		log(expression);
		resultDocument.appendChild(rootNode);
	    Element rootElement = original.getDocumentElement();
	    NodeIterator nl = XPathAPI.selectNodeIterator
	    		  (original, expression , rootElement);
	    appendResultNodes(nl);
	}
	
	private void appendResultNodes(NodeIterator nl){
		Node n;
		int count = 0;
		while ((n = nl.nextNode())!= null) 
		{ 
			log("·j´Mµ²ªG"+count+": " + n.getAttributes().getNamedItem("name").getNodeValue());
			Node newNode = resultDocument.importNode(n, true);
		    resultDocument.getDocumentElement().appendChild(newNode);
		}
	}
	
	private void outputResultForTesting(Source result) throws TransformerException, FileNotFoundException{
		TransformerFactory tranFactory = TransformerFactory.newInstance();
		Transformer transformer = tranFactory.newTransformer(
				new StreamSource(xslPath));
		Source src = result;
		Result dest = new StreamResult(new FileOutputStream(getServletContext().getRealPath("TestingResult.html")));
		
		transformer.transform(src, dest);
	}
	
	protected abstract String getXpathExpression();
	

}
