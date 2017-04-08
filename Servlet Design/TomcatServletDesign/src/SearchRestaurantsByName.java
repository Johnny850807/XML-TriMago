import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;

/**
 * ���O:
 * 1. �ӷ��ɮ׭n�O�o�]�C�W UTF-8 
 * 2. �ϥΨ쪺�귽(�p:�ɮ�)�n���� ���M�i��L�k�N���G��J�s�����W
 */

public class SearchRestaurantsByName extends MyHttpServlet{
	private static final String BASE_QUERY = "//Waterball:restaurant[contains(@name,'%s') ]"
			+ "/@*[name()='name' or name()='id']";
	
	private String restaurantName;
	private String filePath;
	private DataHandler myHandler;
	private MyXmlDtoParser xmlParser;

	@Override
	protected void initiate(ServletContext context) {
		filePath = context.getRealPath("model\\triMago.xml");
		xmlParser = new MyXmlDtoParser();
	}

	@Override
	protected void extractParameter(HttpServletRequest request) {
		restaurantName = request.getParameter("name");
	}
	
	@Override
	protected String getXpathFormat() {
		return String.format(BASE_QUERY, restaurantName);
	}


	@Override
	protected String executeAndGetResult(String xpath) throws Exception{
		myHandler = new FileDataHandler(filePath);
		xmlParser.setXpath(xpath);
		myHandler.setDtoParser(xmlParser);
		myHandler.getParsedDataSet();
		return xmlParser.getQueryResult();
	}

}
