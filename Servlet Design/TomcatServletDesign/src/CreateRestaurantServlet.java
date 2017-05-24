import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import API.APIFactory;
import API.ImageResponse;
import API.ImgurAPI;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

@MultipartConfig
public class CreateRestaurantServlet extends BaseXslTransformServlet{
	private Document originalDocument;
	private Element rootElement;
	private String name;
	private String latitude;
	private String longitude;
	private String typeOfMeal;
	private String price;
	private String address;
	private File picture;

	@Override
	protected void extractParameter(HttpServletRequest request) throws Exception {
		name = request.getParameter("name");
		latitude = request.getParameter("latitude");
		longitude = request.getParameter("longitude");
		typeOfMeal = request.getParameter("typeOfMeal");
		price = request.getParameter("price");
		address = request.getParameter("address");
		Part uploadImagePart = request.getPart("image");
		picture = extractFile(uploadImagePart.getInputStream());
		log(String.format("新增餐廳: 名子 %s%n 座標 (%s,%s)%n"
				+ "地址 %s%n 價格 %s%n 分類 %s%n ", name , latitude , longitude
				, address , price , typeOfMeal));
	}
	
	private File extractFile(InputStream inputStream) throws IOException{
		String fileName = String.valueOf(new Date().hashCode());
		File resultFile = new File(fileName);
		FileOutputStream outputStream =
                new FileOutputStream(resultFile);
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		return resultFile;
	}

	
	@Override
	protected boolean doXmlCrud(Transformer transformer) throws SAXException, IOException, ParserConfigurationException, TransformerException, DOMException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		originalDocument = initBuilder().parse(new File(xmlPath));
		rootElement = originalDocument.getDocumentElement();
		Element restaurantNode = originalDocument.createElementNS
	    		("http://g9.xml.csie.mcu.edu.tw","Waterball:restaurant");
		restaurantNode.setAttribute("id", createNewGuid());
		restaurantNode.setAttribute("name", name);
		restaurantNode.setAttribute("latitude", latitude);
		restaurantNode.setAttribute("longitude", longitude);
		typeOfMeal = typeOfMeal.equals("") ? "無分類" : typeOfMeal;
		restaurantNode.setAttribute("typeOfMeal", typeOfMeal);
		restaurantNode.setAttribute("price", price);
		restaurantNode.setAttribute("address", address);
		restaurantNode.setAttribute("imageUrl", uploadImageAndGetLink());
		rootElement.appendChild(restaurantNode);
		
		transformer.transform(new DOMSource(originalDocument), 
				new StreamResult(new FileOutputStream(xmlPath)));
		
		response.sendRedirect("../index");
		return true;
	}
	
	private String createNewGuid() throws TransformerException{
		String xpath = "//Waterball:restaurant[last()]";
		NodeIterator nl = XPathAPI.selectNodeIterator
	    		  (originalDocument, xpath , rootElement);
		Node lastNode = nl.nextNode();
		int lastIdNum =  Integer.valueOf(lastNode.getAttributes().getNamedItem("id")
				.getNodeValue().substring(1));
		String newId = "r" + (lastIdNum+1);
		log("New id : " + newId);
		return newId;
	}
	
	private String uploadImageAndGetLink() throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
		ImgurAPI imgurAPI = (ImgurAPI) APIFactory.createAPI(ImgurAPI.class);
		RequestBody request = RequestBody.create(MediaType.parse("image/*")
				, picture);
		Call<ImageResponse> call = imgurAPI.postImage(name, address, "file	", request);
		retrofit2.Response<ImageResponse> res = call.execute();
		if (res.isSuccessful())
		{
			if (picture.exists())
				picture.delete();
			log("圖片上傳成功 : " + res.body().data.link);
		}	
		else
			log("失敗" + res.message());
		return res.body().data.link;
	}
	
	private DocumentBuilder initBuilder() throws ParserConfigurationException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
	    return factory.newDocumentBuilder();
	}


	@Override
	protected String getXslFileName() {
		return "index.xsl";
	}

}
