package ParserUtility.Request;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class WebRequestAction extends RequestAction{
	private HttpURLConnection connection;
	public static final String GET = "GET";
	public static final String POST = "POST";
	
	public WebRequestAction(HttpURLConnection connection){
		super(connection.getURL().toString());
		this.connection = connection;
	}
	
	@Override
	protected void prepare() throws ProtocolException{
		connection.setRequestMethod(getAction());
	}
	
	@Override
	protected void printLog() throws IOException{
		System.out.println("\nSending" + getAction() + 
				" request to URL : " + connection.getURL());
		System.out.println("\nResponse Code : " + connection.getResponseCode());
	}

	@Override
	protected void readerInitiate(BufferedReader reader) throws IOException {
		reader = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
	}

	
}
