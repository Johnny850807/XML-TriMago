package ParserUtility.DataParsers;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import ParserUtility.Request.RequestAction;
import ParserUtility.Request.WebRequestAction;

public abstract class WebDataHandler<T> extends DataHandler{
	protected HttpURLConnection connection;
	
	public WebDataHandler(String url) throws Exception{
		super(url);
	}

	@Override
	protected void openUrl() throws IOException {
		URL url = new URL(super.url);
		connection = (HttpURLConnection) url.openConnection();
	}

	@Override
	public void close() throws Exception {
		connection.disconnect();
	}

	@Override
	protected RequestAction createRequestAction() {
		return new WebRequestAction(connection);
	}
	

}