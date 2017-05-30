import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;

public class IndexXslServlet extends MyXPathResultServlet{
	protected String searchInput;
	protected String sort;
	protected String typeOfMeal;
	
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
		typeOfMeal = typeOfMeal == null || typeOfMeal.endsWith("分類")? "" : typeOfMeal;
		log("分類: " + typeOfMeal );
		sort = request.getParameter("sort");
		sort = sort == null || sort.equals("無排序") ? "" : sort;
		log("排序: " + sort);
	}
	

	@Override
	protected Transformer transformConfig(Transformer transformer) {
		log("設定排序"+":"+sort);
		transformer = super.transformConfig(transformer);
		transformer.setParameter("sort", sort);
		
		return transformer;
	}

	@Override
	protected String getXpathExpression() {
		return String.format
				("//Waterball:restaurant[contains(@name,'%s') and contains(@typeOfMeal,'%s')]"
				, searchInput , typeOfMeal);
	}

	@Override
	protected String getXmlFileName() {
		return XmlContext.XML_NAME;
	}


	

}
