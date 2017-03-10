package ParserUtility.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

/* RequestAction is in charge of inputting data-set
 * from specific resources.
 */

public abstract class RequestAction {
	private static BufferedReader reader;
	protected String url;
	public static final String GET = "GET";  //for web request
	public static final String POST = "POST";
	
	public RequestAction(String url){
		this.url = url;
	}
	
	public String parseStringFromResource() throws Exception{
		 prepare();
		 printLog();
		 if (reader != null)
			 reader.close();
		 readerInitiate(reader);
		 return readResponse();
	}
	
	protected abstract void prepare() throws Exception;
	protected abstract void printLog() throws Exception;
	protected abstract void readerInitiate(BufferedReader reader) throws IOException;
	
	protected String readResponse() throws IOException{
		StringBuffer response = new StringBuffer();
		while (reader.ready()) {
			response.append(reader.readLine());
		}
		return response.toString();
	}
	
	
	/* Hook Method : to Override this
	 * Choosing which type of request to send , 
	 * GET or POST (For Web Request)
	 */
	protected String getAction(){
		return GET; 
	}

}
