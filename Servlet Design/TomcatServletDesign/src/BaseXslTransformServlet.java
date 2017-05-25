import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;

public abstract class BaseXslTransformServlet extends MyHttpServlet{
	protected DataHandler<Document> xmlDataHandler;
	protected String xslPath;
	protected String xmlPath;
	@Override
	protected void initiate(ServletContext context) throws Exception {
		xmlPath = context.getRealPath(XmlContext.XML_NAME);
		xslPath = context.getRealPath(getXslFileName());
	}
	
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
		xmlDataHandler = new FileDataHandler(xmlPath);
		StringReader reader = new StringReader(xmlDataHandler.getDataString());
		return new StreamSource(reader);
	}

}
