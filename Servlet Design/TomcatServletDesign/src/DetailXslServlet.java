import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;

public class DetailXslServlet extends MyXPathResultServlet{
	private String id;
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		id = request.getParameter("id");
	}

	@Override
	protected String getXpathExpression() {
		return String.format("//Waterball:restaurant[@id='%s']", id);
	}

	@Override
	protected String getXslFileName() {
		return "detail.xsl";
	}
	
	@Override
	protected String getXmlFileName() {
		return XmlContext.XML_NAME;
	}

}

