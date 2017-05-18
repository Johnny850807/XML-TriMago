import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;

public class SearchRestaurantServlet extends XPathResultServlet{
	private String searchInput;
	private String typeOfMeal;
	
	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}
	
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		searchInput = request.getParameter("searchInput");
		typeOfMeal = request.getParameter("typeOfMeal");
	}

	@Override
	protected String getXpathExpression() {
		return String.format
				("//Waterball:restaurant[contains(@name,'%s') and contains(@typeOfMeal,'%s')]"
				, searchInput , typeOfMeal);
	}

	
	
	

}
