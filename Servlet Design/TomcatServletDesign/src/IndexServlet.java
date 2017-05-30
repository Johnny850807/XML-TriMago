import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.traversal.NodeIterator;

import xml.XPathResultServlet;

public class IndexServlet extends XPathResultServlet{
	private String search;
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		search = request.getParameter("search");
		search = search == null ? "" : search;
	}

	@Override
	protected String getXpathExpression() {
		return String.format("//G12:ContactPerson[contains(G12:name,'%s')]", search );
	}

	@Override
	protected String getXmlFileName() {
		return "index.xml";
	}

	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}

	@Override
	protected String getRootNodeName() {
		return "ContactList";
	}

	@Override
	protected String getNameSpaceValue() {
		return "http://g12.xml.csie.mcu.edu.tw";
	}

	@Override
	protected String getNameSpaceName() {
		return "G12";
	}	

}
