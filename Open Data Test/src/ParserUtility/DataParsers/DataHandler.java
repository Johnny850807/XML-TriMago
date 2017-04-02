package ParserUtility.DataParsers;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerException;

import  org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import ParserUtility.Request.RequestAction;
import WebParserUtility.DataParsers.DtoParsers.DTOParser;
import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

/*DataHandler takes all processes to retrieve data-set 
 * from resources and parsing them to a specific collection
 * , finally return the demanding data collection.
 */

public abstract class DataHandler<T> implements Closeable{
	protected String url;
	protected DTOParser<T> dtoParser;
	protected RequestAction requestAction;
	
	public DataHandler(String url) throws IOException {
		setUrl(url);
		requestAction = createRequestAction();
	}
	protected abstract RequestAction createRequestAction();
	
	public void setDtoParser(DTOParser parser){
		this.dtoParser = parser;
	}
	
	public void setUrl(String url) throws IOException {
		this.url = url;
		openUrl();
	}
	// to connect to resource by the given URL
	protected abstract void openUrl() throws MalformedURLException, IOException;
	

	
	public String getURLString(){
		return url;
	}
	
	//return a final result of demanding data collection
	public List<T> getParsedDataSet() throws Exception{
		if (dtoParser == null)
			throw new DtoNullException();
		String data = getDataString();
		return dtoParser.parseData(data);
	}
	
	public String getDataString() throws Exception {
		return requestAction.parseStringFromResource();
	}
	

}
