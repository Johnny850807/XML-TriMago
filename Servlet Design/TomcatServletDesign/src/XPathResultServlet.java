import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

public abstract class XPathResultServlet extends BaseXslTransformServlet{
	
	@Override
	protected Source getXmlStreamSource() throws Exception {
		String expression = getXpathExpression();
		log(expression);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    
	    Document original = builder.parse(new File(xmlPath));
	    original.getDocumentElement().normalize();

	    Document filtered = builder.newDocument();
	    
	    Node rootNode = filtered.createElement("Waterball:WebSite");
	    filtered.appendChild(rootNode);
	    
	    Element rootElement = original.getDocumentElement();
	      
	    NodeIterator nl = XPathAPI.selectNodeIterator
	    		  (original, expression , rootElement);
		Node n;
		int count = 0;
		while ((n = nl.nextNode())!= null) 
		{ 
			log("·j´Mµ²ªG"+count+": " + n.getAttributes().getNamedItem("name").getNodeValue());
			Node newNode = filtered.importNode(n, true);
		    filtered.getDocumentElement().appendChild(newNode);
		}

		return new DOMSource(filtered);
	}
	
	protected abstract String getXpathExpression();
	
	@Override
	protected void transformConfig(Transformer transformer){
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	}
}
