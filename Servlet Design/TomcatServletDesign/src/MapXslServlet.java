
public class MapXslServlet extends BaseXslTransformServlet{

	@Override
	protected String getXslFileName() {
		return "map.xsl";
	}
	
	@Override
	protected String getXmlFileName() {
		return XmlContext.XML_NAME;
	}

}
