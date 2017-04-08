import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;
import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class MyXmlDtoParser<T> extends XmlDtoParser<T>{
	private String xpath;
	private DocumentBuilder dBuilder;
	private Document xmlDocument;
	
	public MyXmlDtoParser(String xpath){
		this.xpath = xpath;
	}
	
	@Override  // 當父類別讀完也驗證完xml檔案之後會呼叫這個函數，傳入需要用到的xml工具
	protected List<T> getParsedXmlData(DocumentBuilder dBuilder ,Document xmlDocument) throws TransformerException {
		this.dBuilder = dBuilder;
		this.xmlDocument = xmlDocument;
		return null; //作業只要求印出結果，所以目前都不需要製作物件導向的List QQ Null就好
	}
	
	public String getQueryResult() throws TransformerException{
		StringBuilder result = new StringBuilder();
		
		boolean hasResult = false;
		NodeIterator iterator = XPathAPI.selectNodeIterator(xmlDocument, xpath);
		Node node;
		    
		while ( (node = iterator.nextNode()) !=  null) 
		{ 
			hasResult = true;
		   	result.append(node.getNodeName() + ": " + node.getTextContent().trim()+"\n");
		}
		    
		if (!hasResult)
			result.append("找無結果。\n");

		return result.toString();
	}
	
	
	public static void main(String[] argv){
		final String restaurantName = "時間";
		final String xpath =  String.format("//Waterball:restaurant[contains(@name,'%s') ]/@*[name()='name' or name()='id']", restaurantName);
		final MyXmlDtoParser xmlParser = new MyXmlDtoParser(xpath);
		
		try(DataHandler handler = new FileDataHandler("triMago.xml")){
			handler.setDtoParser(xmlParser);
			handler.getParsedDataSet();
			System.out.println(xmlParser.getQueryResult());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
		
}