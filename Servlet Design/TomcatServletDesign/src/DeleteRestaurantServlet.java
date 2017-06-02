import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

import xml.BaseXslTransformServlet;

public class DeleteRestaurantServlet  extends BaseXslTransformServlet{
	String delete_id;
	String password;
	private final String PASSWORD_ADMINISTRATOR = "waterball850807";
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		delete_id = request.getParameter("delete_id");
		password = request.getParameter("password");
		log("±ý§R°£Id : " + delete_id);
	}
	
	@Override
	protected boolean doXmlCrud(Transformer transformer) throws Exception {
		if (!password.equals(PASSWORD_ADMINISTRATOR))
		{
			response.sendRedirect("password_wrong.html");
			return true;
		}
		DocumentBuilder builder = initBuilder();
		Document document = builder.parse(new File(xmlPath));
		Element rootElement = document.getDocumentElement();
		String expression = String.format("//Waterball:restaurant[@id='%s']", delete_id);
		NodeIterator resultNodes = XPathAPI.selectNodeIterator(document, expression , rootElement);
		Node node = resultNodes.nextNode();
		node.getParentNode().removeChild(node);
		
        transformer.transform(new DOMSource(document), 
				new StreamResult(new FileOutputStream(xmlPath)));
		response.sendRedirect("index");
		return true;
	}
	
	@Override
	protected String getXmlFileName() {
		return XmlContext.XML_NAME;
	}

	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}

}
