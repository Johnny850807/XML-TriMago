package ParserUtility.DataParsers;

import java.io.Closeable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import ParserUtility.Request.RequestAction;
import WebParserUtility.DataParsers.DtoParsers.DTOParser;

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
