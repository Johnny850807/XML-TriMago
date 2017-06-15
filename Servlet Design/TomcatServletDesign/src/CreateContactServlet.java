import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xml.BaseXslTransformServlet;
@MultipartConfig
public class CreateContactServlet extends BaseXslTransformServlet
{
	private Document originalDocument;
	private Element rootElement;
	private String name;
	private String nickName;
	private String phone;
	private String address;
	private String imageUrl;
	private String group;
	
	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		group = request.getParameter("group");
		name = request.getParameter("name");
		nickName = request.getParameter("nickName");
		phone = request.getParameter("phone");
		address = request.getParameter("address");
		Part imagePart = request.getPart("image");
		saveImageAndsetLink(imagePart.getInputStream());
	}
	
	private void saveImageAndsetLink(InputStream inputStream) throws IOException{
		//use a latest date as a name of file
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
		String dateText = formatter.format(new Date());
		String contexPath = getServletContext().getRealPath("pics");
		imageUrl = contexPath+"\\"+dateText+".png";
		log("save url path : " + imageUrl);
		File saveFile = new File(imageUrl);

		FileOutputStream outputStream =
                new FileOutputStream(saveFile);  //save the image
	
		//doing I/O
		int read = 0;
		byte[] bytes = new byte[1024];
		
		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		
		imageUrl = imageUrl.substring(imageUrl.lastIndexOf("pics"));
	}

	@Override
	protected boolean doXmlCrud(Transformer transformer) throws Exception {
		originalDocument = initBuilder().parse(new File(xmlPath));
		rootElement = originalDocument.getDocumentElement();
		Element contactNode = createContactPersonNode();
		rootElement.appendChild(contactNode);
		
		transformer.transform(new DOMSource(originalDocument), 
				new StreamResult(new FileOutputStream(xmlPath)));
		response.sendRedirect("../index");
		return true;
	}
	
	private Element createContactPersonNode(){
		Element contactNode = originalDocument.createElementNS
	    		("http://g12.xml.csie.mcu.edu.tw","G12:ContactPerson");
		if (group != null)
			contactNode.setAttribute("group", group);
		
		Element nameNode = originalDocument.createElementNS
	    		("http://g12.xml.csie.mcu.edu.tw","G12:name");
		nameNode.setTextContent(name);
		
		Element nickNameNode = originalDocument.createElementNS
	    		("http://g12.xml.csie.mcu.edu.tw","G12:nickname");
		nickNameNode.setTextContent(nickName);
		
		Element phoneNode = originalDocument.createElementNS
	    		("http://g12.xml.csie.mcu.edu.tw","G12:phone");
		phoneNode.setTextContent(phone);
		
		Element addressNode = originalDocument.createElementNS
	    		("http://g12.xml.csie.mcu.edu.tw","G12:address");
		addressNode.setTextContent(address);
		
		Element imgUrlNode = originalDocument.createElementNS
	    		("http://g12.xml.csie.mcu.edu.tw","G12:imageUrl");
		imgUrlNode.setTextContent(imageUrl);
		
		contactNode.appendChild(nameNode);
		contactNode.appendChild(nickNameNode);
		contactNode.appendChild(phoneNode);
		contactNode.appendChild(addressNode);
		contactNode.appendChild(imgUrlNode);
		
		return contactNode;
	}

	@Override
	protected String getXmlFileName() {
		return "index.xml";
	}

	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}
    
}
