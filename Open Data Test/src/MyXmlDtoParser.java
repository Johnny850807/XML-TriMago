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
	
	@Override  // ������OŪ���]���ҧ�xml�ɮפ���|�I�s�o�Ө�ơA�ǤJ�ݭn�Ψ쪺xml�u��
	protected List<T> getParsedXmlData(DocumentBuilder dBuilder ,Document xmlDocument) throws TransformerException {
		this.dBuilder = dBuilder;
		this.xmlDocument = xmlDocument;
		return null; //�@�~�u�n�D�L�X���G�A�ҥH�ثe�����ݭn�s�@����ɦV��List QQ Null�N�n
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
			result.append("��L���G�C\n");

		return result.toString();
	}
	
	
	public static void main(String[] argv){
		final String restaurantName = "�ɶ�";
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