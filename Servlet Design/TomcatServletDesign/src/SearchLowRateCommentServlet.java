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

public class SearchLowRateCommentServlet extends XmlQueryServlet{
	private static final String BASE_QUERY = 
			"//Waterball:comment[@rate < 3]/child::*";
	
	@Override
	protected void extractParameter(HttpServletRequest request) {}
	
	@Override
	protected String getXpathFormat() {
		return BASE_QUERY;
	}
}


