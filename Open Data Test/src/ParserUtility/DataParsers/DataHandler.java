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

/*DataHandler takes all processes to retrieve data-set 
 * from resources and parsing them to a specific collection
 * , finally return the demanding data collection.
 */

public abstract class DataHandler<T> {
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
		String data = getDataOnRequest(requestAction);
		return dtoParser.parseData(data);
	}
	
	private String getDataOnRequest(RequestAction request) throws Exception {
		return request.parseStringFromResource();
	}

	public abstract void close() throws Exception;
}
