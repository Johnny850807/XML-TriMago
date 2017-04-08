import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;
/**
 * 筆記:
 * 1. 來源檔案要記得也列上 UTF-8 
 * 2. 使用到的資源(如:檔案)要釋放掉 不然可能無法將結果輸入瀏覽器上
 */

public class SearchRestaurantByName extends HttpServlet{
	private static final String BASE_QUERY = "//Waterball:restaurant[contains(@name,'%s') ]"
			+ "/@*[name()='name' or name()='id']";
	private String findRestaurantByNameXpath;
	private String restaurantName;
	private String filePath;
	private DataHandler myHandler;
	private MyXmlDtoParser xmlParser;
	private BufferedReader resultReader;
	private BufferedWriter responseWriter;
	private String result;
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			setupConfig(request,response);
			restaurantName = request.getParameter("name");
			System.out.println("restaurantName = "+restaurantName);
			executeQueryAndGetResult();
			writeResultToResponse(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try {
				myHandler.close();
				resultReader.close();
				responseWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setupConfig(HttpServletRequest request, HttpServletResponse response) throws Exception{
		filePath = getServletContext().getRealPath("model\\triMago.xml");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	}
	
	private void executeQueryAndGetResult() throws Exception{
		findRestaurantByNameXpath = String.format(BASE_QUERY, restaurantName);
		xmlParser = new MyXmlDtoParser(findRestaurantByNameXpath);
		System.out.println(findRestaurantByNameXpath);
		doQuery();
	}
	
	private void doQuery() throws Exception{
		myHandler = new FileDataHandler(filePath);
		myHandler.setDtoParser(xmlParser);
		myHandler.getParsedDataSet();
		result = xmlParser.getQueryResult();
		System.out.println(result);
	}
	
	private void writeResultToResponse(HttpServletResponse response) throws IOException{
		resultReader = new BufferedReader(new StringReader(result));
		responseWriter = new BufferedWriter(response.getWriter());
		String input;
		while ((input = resultReader.readLine()) != null)
			responseWriter.write(input+"<br/>");
		System.out.println("response finished.");
	}
	
}
