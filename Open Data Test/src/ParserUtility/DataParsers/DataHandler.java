package ParserUtility.DataParsers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import ParserUtility.Request.RequestAction;
import ParserUtility.Request.WebRequestAction;
import WebParserUtility.DataParsers.DtoParsers.DTOParser;

/*DataHandler take all processes about retrieving data-set 
 * from resources and parsing them to a specific collection
 * , finally return the demanding data collection.
 */

public abstract class DataHandler<T> {
	protected String url;
	protected HttpURLConnection connection;
	protected DTOParser<T> dtoParser;
	protected RequestAction requestAction;
	
	public DataHandler(String url) throws Exception{
		setUrl(url);
		requestAction = createRequestAction();
		dtoParser = createDTOParser();
	}
	
	public void setUrl(String url) throws Exception{
		this.url = url;
		openUrl();
	}
	
	// to connect to resource by the given URL
	protected abstract void openUrl() throws Exception;
	
	protected abstract RequestAction createRequestAction();
	protected abstract DTOParser<T> createDTOParser();
	
	protected HttpURLConnection getConnection(){
		return connection;
	}
	
	public String getURLString(){
		return url;
	}
	
	//return a final result of demanding data collection
	public List<T> getParsedDataSet() throws Exception{
		String data = getDataOnRequest(requestAction);
		return dtoParser.parseData(data);
	}
	
	private String getDataOnRequest(RequestAction request) throws Exception{
		return request.parseString();
	}
	
	

	public abstract void close() throws Exception;
}
