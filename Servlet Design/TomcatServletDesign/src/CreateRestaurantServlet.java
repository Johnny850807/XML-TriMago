import javax.servlet.http.HttpServletRequest;

public class CreateRestaurantServlet extends BaseXslTransformServlet{

	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		super.extractParameter(request);
	}
	
	@Override
	protected void doXmlCrud() {
		// TODO Auto-generated method stub
		super.doXmlCrud();
	}

	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}

}
