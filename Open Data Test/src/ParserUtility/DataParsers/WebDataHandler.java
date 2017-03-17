package ParserUtility.DataParsers;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import ParserUtility.Request.RequestAction;
import ParserUtility.Request.WebRequestAction;

public class WebDataHandler<T> extends DataHandler{
	protected HttpURLConnection connection;
	private RequestAction request;
	
	public WebDataHandler(String url) throws Exception{
		super(url);
	}

	@Override
	protected void openUrl() throws IOException {
		URL url = new URL(super.url);
		connection = (HttpURLConnection) url.openConnection();
	}

	@Override
	public void close() {
		connection.disconnect();
	}

	@Override
	protected RequestAction createRequestAction() {
		this.request = new WebRequestAction(connection);
		return request;
	}
	

}