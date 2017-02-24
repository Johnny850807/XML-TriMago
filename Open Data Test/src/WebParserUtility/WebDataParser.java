
package WebParserUtility;

import java.awt.List;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collection;

import WebParserUtility.Request.WebRequestAction;

public abstract class WebDataParser<T> extends DataParser{
	
	public WebDataParser(String url) throws Exception{
		super(url);
	}
	
	protected HttpURLConnection getConnection(){
		return connection;
	}

	@Override
	protected void openUrl() throws Exception{
		URL obj = new URL(url);
		connection = (HttpURLConnection) obj.openConnection();
	}

	@Override
	public void close() throws Exception {
		connection.disconnect();
	}
	
	
	
}