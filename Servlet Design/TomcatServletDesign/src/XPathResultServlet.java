import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XPathResultServlet extends BaseXslTransformServlet{
	
	@Override
	protected Source getXmlStreamSource() throws Exception {
		String expression = getXpathExpression();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document original = builder.parse(xmlPath);

	    original.getDocumentElement().normalize();

	    Document filtered = builder.newDocument();
	    Node root = filtered.createElement("Waterball:WebSite");
	    filtered.appendChild(root);

	    XPath xPath = XPathFactory.newInstance().newXPath();
	    Object result = xPath.compile(expression).evaluate(original, XPathConstants.NODESET);

	    NodeList nodes = (NodeList) result;
	    log("·j´Mµ²ªG:" + nodes.getLength() + "µ§");
	    for (int i = 0; i < nodes.getLength(); i++) {
	        Node node = nodes.item(i);
	        filtered.adoptNode(node);
	        root.appendChild(node);
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
