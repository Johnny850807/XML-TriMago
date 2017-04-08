import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;

public abstract class XmlQueryServlet extends MyHttpServlet{
	protected String filePath;
	protected DataHandler<String> myHandler;
	protected MyXmlDtoParser xmlParser;
	
	@Override
	protected void initiate(ServletContext context) throws Exception {
		filePath = context.getRealPath("model\\triMago.xml");
		myHandler = new FileDataHandler(filePath);
		xmlParser = new MyXmlDtoParser();
	}

	@Override
	protected String executeAndGetResult(String xpath) throws Exception {
		xmlParser.setXpath(xpath);
		myHandler.setDtoParser(xmlParser);
		return "查詢結果為: \n" + myHandler.getParsedDataSet();
	}

}
