import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class MyXmlDtoParser extends XmlDtoParser<String>{
	private String xpath;
	private DocumentBuilder dBuilder;
	private Document xmlDocument;
	
	public MyXmlDtoParser(){}
	
	public MyXmlDtoParser(String xpath){
		this.xpath = xpath;
	}
	
	public void setXpath(String xpath){
		this.xpath = xpath;
	}
	

	@Override  // 當父類別讀完也驗證完xml檔案之後會呼叫這個函數，傳入需要用到的xml工具
	protected String getParsedXmlData(DocumentBuilder dBuilder ,Document xmlDocument) throws TransformerException {
		this.dBuilder = dBuilder;
		this.xmlDocument = xmlDocument;
		return getQueryResult(); //作業只要求印出結果，所以目前都不需要製作物件導向的List QQ Null就好
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

}
	
