import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;
import WebParserUtility.DataParsers.DtoParsers.XmlDtoParser;

public class Test {

	public static void main(String[] argv){
		final String xmlPath = "triMago.xml";
	
		try (DataHandler myHandler = new FileDataHandler(xmlPath)){
			//System.out.println(myHandler.getDataString());
			myHandler.setDtoParser(new MyXmlDtoParser());
			myHandler.getParsedDataSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static class MyXmlDtoParser extends XmlDtoParser{
		@Override
		protected List getParsedXmlData(DocumentBuilder dBuilder ,Document xmlDocument) throws TransformerException {
			String findRestaurant = "//Waterball:restaurant[contains(@typeOfMeal,'早餐')]" ;  // to find all restaurants that are serving for breakfast
			String findLowRateComment = "//Waterball:comment[@rate < 2]";  // to find all comments which giving a low rate
			
			runXpath(xmlDocument,findRestaurant);
			runXpath(xmlDocument,findLowRateComment);
			return null;
		}
	
		
		private void runXpath(Document xmlDocument,String xpath) throws TransformerException{
			boolean hasResult = false;
			NodeIterator iterator = XPathAPI.selectNodeIterator(xmlDocument, xpath);
		    Node node;
		    
		    while ( (node = iterator.nextNode()) !=  null) 
		    { 
		    	hasResult = true;
		    	System.out.println("================================");
		    	
		    	NamedNodeMap attributes =  node.getAttributes();
		    	for ( int i = 0 ; i < attributes.getLength() ; i ++ )
		    		System.out.printf(" %s %n",attributes.item(i));
		    	
		    	NodeList childs = node.getChildNodes();
		    	for ( int i = 0 ; i < childs.getLength() ; i ++ )
		    		System.out.println(childs.item(i).getTextContent().trim());
		    	
		    	System.out.println("================================");
		    	
		    	System.out.println();
		    }
		    
		    if (!hasResult)
		    	System.out.println("找無結果。");
		}
	}
}
