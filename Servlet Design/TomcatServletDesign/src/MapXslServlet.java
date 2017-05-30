import javax.servlet.http.HttpServletRequest;

public class MapXslServlet extends IndexXslServlet{

	@Override
	protected String getXslFileName() {
		return "map.xsl";
	}
	
	@Override
	protected String getXmlFileName() {
		return XmlContext.XML_NAME;
	}

}
