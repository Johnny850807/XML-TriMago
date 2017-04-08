import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;

/**
 * 筆記:
 * 1. 來源檔案要記得也列上 UTF-8 
 * 2. 使用到的資源(如:檔案)要釋放掉 不然可能無法將結果輸入瀏覽器上
 */

public class SearchRestaurantsByNameServlet extends XmlQueryServlet{
	private static final String BASE_QUERY = "//Waterball:restaurant[contains(@name,'%s') ]"
			+ "/@*";
	
	private String restaurantName;

	@Override
	protected void extractParameter(HttpServletRequest request) {
		restaurantName = request.getParameter("name");
	}
	
	@Override
	protected String getXpathFormat() {
		return String.format(BASE_QUERY, restaurantName);
	}
}
