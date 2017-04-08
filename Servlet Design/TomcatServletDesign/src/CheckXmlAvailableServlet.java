
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;
import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class CheckXmlAvailableServlet extends MyHttpServlet{
	private String filePath;
	private DataHandler handler;
	private XmlDtoParser parser;
	@Override
	protected void initiate(ServletContext context) throws Exception {
		filePath = getServletContext().getRealPath("model\\triMago.xml");
		handler = new FileDataHandler(filePath);
		parser = new XmlDtoParser(){
			@Override
			protected Object getParsedXmlData(DocumentBuilder arg0, Document arg1) throws TransformerException {
				return null;
			}	
		};
		handler.setDtoParser(parser);
	}

	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected String getXpathFormat() throws Exception {
		return null;
	}

	@Override
	protected String executeAndGetResult(String xpath) throws Exception {
		try {
			handler.getParsedDataSet();
			result = "XML測試結果: 成功";
		} catch (Exception e) {
			result = "XML測試結果: 失敗" ;
			e.printStackTrace();
		}
		return result;
	}


}
