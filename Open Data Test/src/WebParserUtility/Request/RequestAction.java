package WebParserUtility.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public abstract class RequestAction {
	private BufferedReader reader;
	protected String url;
	public static final String GET = "GET";
	public static final String POST = "POST";
	
	public RequestAction(String url){
		this.url = url;
	}
	
	public String parseString() throws Exception{
		 prepare();
		 printLog();
		 readerInitiate(reader);
		 return readResponse();
	}
	
	protected abstract void prepare() throws Exception;
	protected abstract void printLog() throws Exception;
	
	/* Choose which type of request to send , 
	 * GET or POST
	 */
	protected abstract String getAction();
	
	
	
	protected String readResponse() throws IOException{
		StringBuffer response = new StringBuffer();
		int lines = 0;
		while (reader.ready()) {
			response.append(reader.readLine());
			lines++;
		}
		System.out.println(lines + " lines totally .");
		return response.toString();
	}
	
	protected abstract void readerInitiate(BufferedReader reader) throws IOException;
	
}
