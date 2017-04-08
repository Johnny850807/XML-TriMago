import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyHttpServlet extends HttpServlet{
	protected String xpath;
	protected String result;
	protected BufferedReader resultReader;
	protected BufferedWriter responseWriter;
	
	@Override
	public void init() throws ServletException {
		super.init();
		initiate(getServletContext());
	}
	
	protected abstract void initiate(ServletContext context);  // to read files or to instantiate variables.

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			log("�]�m�B�J");
			setupConfig(request,response);
			initResponseBufferedWriter(response.getWriter());

			log("�Ѽƴ����B�J");
			extractParameter(request);
			
			log("����P�o�쵲�G�B�J");
			result = executeAndGetResult(getXpathFormat());
			resultReader = new BufferedReader(new StringReader(result));
			
			log("���G�� : \n" + result);
			log("�g�J���A����X�B�J");
			writeResultToResponse(result,responseWriter,resultReader);
			
			log("�����C");
			
		} catch (Exception e) {
			log(e.getMessage(),e);
		} finally{
			try {
				log("����귽�C");
				close(responseWriter,resultReader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void initResponseBufferedWriter(PrintWriter responsePrintWriter){
		responseWriter = new BufferedWriter(responsePrintWriter);
	}
	
	protected void setupConfig(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String contentType = getContentType();
		String encoding  = getEncoding();
		request.setCharacterEncoding(encoding);
		response.setContentType(contentType + ";charset=" + encoding);
	}
	
	protected String getContentType(){
		return "text/html";
	}
	
	protected String getEncoding() {
		return "UTF-8";
	}

	
	protected abstract void extractParameter(HttpServletRequest request) throws Exception;
	
	protected abstract String getXpathFormat() throws Exception;
	
	protected abstract String executeAndGetResult(String xpathFormat) throws Exception;
	
	protected void writeResultToResponse(String result ,BufferedWriter responseWriter,BufferedReader resultReader) throws IOException{
		String input;
		while ((input = resultReader.readLine()) != null)
			responseWriter.write(input+"<br/>");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		log("���A������");
	}
	
	protected void close(BufferedWriter responseWriter,BufferedReader resultReader) throws Exception{
		responseWriter.close();
		resultReader.close();
	}

	@Override
	public void log(String msg) {
		super.log(msg);
		System.out.println("�t�ΧְT: " + msg);
	}
	
	
	
}
