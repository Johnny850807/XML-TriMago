import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

public class CreateRestaurantServlet extends BaseXslTransformServlet{
	private Document originalDocument;
	private Element rootElement;
	private String name;
	private String latitude;
	private String longitude;
	private String typeOfMeal;
	private String price;
	private String address;
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		name = request.getParameter("name");
		latitude = request.getParameter("latitude");
		longitude = request.getParameter("longitude");
		typeOfMeal = request.getParameter("typeOfMeal");
		price = request.getParameter("price");
		address = request.getParameter("address");
		log(String.format("新增餐廳: 名子 %s%n 座標 (%s,%s)%n"
				+ "地址 %s%n 價格 %s%n 分類 %s%n", name , latitude , longitude
				, address , price , typeOfMeal));
	}
	
	@Override
	protected void doXmlCrud(Transformer transformer) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		originalDocument = initBuilder().parse(new File(xmlPath));
		rootElement = originalDocument.getDocumentElement();
		Element restaurantNode = originalDocument.createElementNS
	    		("http://g9.xml.csie.mcu.edu.tw","Waterball:restaurant");
		restaurantNode.setAttribute("id", createNewGuid());
		restaurantNode.setAttribute("name", name);
		restaurantNode.setAttribute("latitude", latitude);
		restaurantNode.setAttribute("longitude", longitude);
		restaurantNode.setAttribute("typeOfMeal", typeOfMeal);
		restaurantNode.setAttribute("price", price);
		restaurantNode.setAttribute("address", address);
		rootElement.appendChild(restaurantNode);
		
		transformer.transform(new DOMSource(originalDocument), 
				new StreamResult(new FileOutputStream(xmlPath)));
	}
	
	private String createNewGuid() throws TransformerException{
		String xpath = "//Waterball:restaurant[last()]";
		NodeIterator nl = XPathAPI.selectNodeIterator
	    		  (originalDocument, xpath , rootElement);
		Node lastNode = nl.nextNode();
		int lastIdNum =  Integer.valueOf(lastNode.getAttributes().getNamedItem("id")
				.getNodeValue().substring(1));
		String newId = "r" + (lastIdNum+1);
		log("New id : " + newId);
		return newId;
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
	    return factory.newDocumentBuilder();
	}


	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}

}
