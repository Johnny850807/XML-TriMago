package WebParserUtility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;

import WebParserUtility.Request.RequestAction;
import WebParserUtility.Request.WebRequestAction;

public abstract class DataParser<T> {
	protected String url;
	protected HttpURLConnection connection;
	protected DTOParser<T> dtoParser;
	protected RequestAction requestAction;
	
	public DataParser(String url) throws Exception{
		this.url = url;
		openUrl();
		requestAction = createRequestAction();
		dtoParser = createDTOParser();
	}
	
	protected abstract void openUrl() throws Exception;
	protected abstract RequestAction createRequestAction();
	protected abstract DTOParser<T> createDTOParser();
	
	protected HttpURLConnection getConnection(){
		return connection;
	}
	
	public String getURLString(){
		return url;
	}

	
	public Collection getParsedDataSet() throws Exception{
		String data = getDataOnRequest(requestAction);
		return dtoParser.parseData(data);
	}
	
	private String getDataOnRequest(RequestAction request) throws Exception{
		return request.parseString();
	}
	
	public void setRequestAction(RequestAction requestAction){
		this.requestAction = requestAction;
	}
	

	public abstract void close() throws Exception;
}
