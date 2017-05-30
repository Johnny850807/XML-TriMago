import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

public class CreateCommentServlet extends BaseXslTransformServlet{
	private String id;
	private String date;
	private String name;
	private String content;
	private String rate;
	private Document originalDocument;
	private Element rootElement;
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		date = nowdateToString();
		id = request.getParameter("id");
		name = request.getParameter("name");
		content = request.getParameter("content");
		rate = request.getParameter("rate");
		rate = rate == null || rate.equals("")  ? "0" : rate;
		log(String.format("id: %s%n , name: %s%n , content: %s%n , date: %s%n"
				+ "rate: %s%n", id,name,content,date,rate));
	}
	
	private String nowdateToString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd - hh:mm:ss");
		return format.format(new Date());
	}

	@Override
	protected String getXslFileName() {
		return "detail.xsl";
	}

	@Override
	protected boolean doXmlCrud(Transformer transformer) throws Exception {
		originalDocument = initBuilder().parse(new File(xmlPath));
		rootElement = originalDocument.getDocumentElement();
		Element commentNode = originalDocument.createElementNS
	    		("http://g9.xml.csie.mcu.edu.tw","Waterball:comment");
		commentNode.setAttribute("rate", rate);
		Element commentDateNode = originalDocument.createElementNS
	    		("http://g9.xml.csie.mcu.edu.tw","Waterball:date");
		commentDateNode.setTextContent(date);
		Element commentNameNode = originalDocument.createElementNS
	    		("http://g9.xml.csie.mcu.edu.tw","Waterball:name"); 
		commentNameNode.setTextContent(name);
		Element commentContentNode = originalDocument.createElementNS
	    		("http://g9.xml.csie.mcu.edu.tw","Waterball:content"); 
		commentContentNode.setTextContent(content);
		commentNode.appendChild(commentDateNode);
		commentNode.appendChild(commentNameNode);
		commentNode.appendChild(commentContentNode);
		NodeIterator nl = XPathAPI.selectNodeIterator
	    		  (originalDocument, String.format("//Waterball:restaurant[@id='%s']", id) , rootElement);
		Node restaurantNode = nl.nextNode();
		restaurantNode.appendChild(commentNode);
		transformer.transform(new DOMSource(originalDocument), 
				new StreamResult(new FileOutputStream(xmlPath)));
		response.sendRedirect("../detail?id="+id);
		return true;
	}
	
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
	    return factory.newDocumentBuilder();
	}

	@Override
	protected String getXmlFileName() {
		return XmlContext.XML_NAME;
	}
}
