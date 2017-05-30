
public class AboutXslServlet extends BaseXslTransformServlet{
	@Override
	protected String getXslFileName() {
		return "about.xsl";
	}

	@Override
	protected String getXmlFileName() {
		return XmlContext.XML_NAME;
	}
}

