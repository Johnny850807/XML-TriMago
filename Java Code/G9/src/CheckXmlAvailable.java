import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class CheckXmlAvailable extends HttpServlet{
	final static String LINK = "http://data.gov.tw/iisi/logaccess/46147?dataUrl=http://opendata.epa.gov.tw/ws/Data/ATM00564/?%24skip=0&%24top=1000&format=xml&ndctype=XML&ndcnid=34766";
	
	protected void service(HttpServletRequest req, HttpServletResponse res ) {
		try {
			String filePath = getServletContext().getRealPath("model\\triMago.xml");
			String result ;
			req.setCharacterEncoding("UTF-8");
			String userName = (String) req.getParameter("name");
			XmlDtoParser myParser = new XmlDtoParser(filePath){
				@Override
				protected List getParsedXmlData(DocumentBuilder dBuilder) {
					return null;
				}
			};
			try {
				myParser.parseData("");
				result = "XML測試結果: 成功";
			} catch (IOException | SAXException | ParserConfigurationException e) {
				result = "XML測試結果: 失敗" + e.getMessage();
			}
			
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			BufferedReader input = new BufferedReader(new StringReader(result));
			BufferedWriter output = new BufferedWriter( out);
			int ch;
			while ((ch = input.read()) != -1)
				output.write(ch);
			input.close();
			output.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
