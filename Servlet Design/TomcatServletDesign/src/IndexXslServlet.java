import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;

public class IndexXslServlet extends XPathResultServlet{
	private String searchInput;
	private String typeOfMeal;
	
	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}
	
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		searchInput = request.getParameter("searchInput");
		searchInput = searchInput == null ? "" : searchInput;
		log("搜尋: " + searchInput );
		typeOfMeal = request.getParameter("typeOfMeal");
		typeOfMeal = typeOfMeal == null || typeOfMeal.equals("選擇分類") ? "" : typeOfMeal;
		log("分類: " + typeOfMeal );
	}

	@Override
	protected String getXpathExpression() {
		return String.format
				("//Waterball:restaurant[contains(@name,'%s') and contains(@typeOfMeal,'%s')]"
				, searchInput , typeOfMeal);
	}

	
	
	

}
