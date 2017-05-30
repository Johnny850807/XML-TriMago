import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public abstract class BaseXslTransformServlet extends MyHttpServlet{
	protected String xslPath;
	protected String xmlPath;
	@Override
	protected void initiate(ServletContext context) throws Exception {
		xmlPath = context.getRealPath(getXmlFileName());
		xslPath = context.getRealPath(getXslFileName());
	}
	
	protected abstract String getXmlFileName();
	
	protected abstract String getXslFileName();
	
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		// hook method
	}


	@Override
	protected String executeAndGetResult() throws Exception {
		Transformer transformer = transformConfig(
				TransformerFactory.newInstance().newTransformer());
		boolean redirect = doXmlCrud(transformer);
		if (redirect)
			return "Redirecting...";
		return transformXmlToHtml();
	}
	
	protected boolean doXmlCrud(Transformer transformer) throws Exception{
		//hook method
		return false;
	}
	
	protected String transformXmlToHtml() throws Exception{
		TransformerFactory tranFactory = TransformerFactory.newInstance();
		Transformer transformer = tranFactory.newTransformer
				( new StreamSource(xslPath));
		transformConfig(transformer);
	    StringWriter writer = new StringWriter();
		Source src = getXmlStreamSource();
		Result dest = new StreamResult(writer);
		transformer.transform(src, dest);
		String result = writer.toString();
		writer.close();
		return writer.toString();
	}
	
	protected Transformer transformConfig(Transformer transformer){
		//hook method
		transformer.setOutputProperty(OutputKeys.ENCODING, getEncoding());
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		return transformer;
	}
	
	protected Source getXmlStreamSource() throws Exception{
		//hook method
		return new StreamSource(new InputStreamReader(
	            new FileInputStream(xmlPath),"UTF-8"));
	}

}
