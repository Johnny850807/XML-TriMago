import xml.XPathResultServlet;

public abstract class MyXPathResultServlet extends XPathResultServlet{

	@Override
	protected String getRootNodeName() {
		return "WebSite";
	}

	@Override
	protected String getNameSpaceValue() {
		return "http://g9.xml.csie.mcu.edu.tw";
	}

	@Override
	protected String getNameSpaceName() {
		return "Waterball";
	}

}
