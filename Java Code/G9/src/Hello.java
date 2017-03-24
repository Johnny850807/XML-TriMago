
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
public class Hello extends HttpServlet
{    
	
	protected void service(HttpServletRequest req, HttpServletResponse res ) {
		String filePath = getServletContext().getRealPath("model\\triMago.xml");
		try {
			req.setCharacterEncoding("UTF-8");
			res.setContentType("text/xml;charset=UTF-8");
			PrintWriter out = res.getWriter();
			BufferedReader input = new BufferedReader(
					   new InputStreamReader(
			                      new FileInputStream(filePath), "UTF8"));
			BufferedWriter output = new BufferedWriter( out);
			int ch;
			while ((ch = input.read()) != -1)
				output.write(ch);
			input.close();
			output.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

