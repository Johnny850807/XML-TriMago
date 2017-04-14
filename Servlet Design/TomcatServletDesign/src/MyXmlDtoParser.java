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
	

	@Override  // ������OŪ���]���ҧ�xml�ɮפ���|�I�s�o�Ө�ơA�ǤJ�ݭn�Ψ쪺xml�u��
	protected String getParsedXmlData(DocumentBuilder dBuilder ,Document xmlDocument) throws TransformerException {
		this.dBuilder = dBuilder;
		this.xmlDocument = xmlDocument;
		return getQueryResult(); //�@�~�u�n�D�L�X���G�A�ҥH�ثe�����ݭn�s�@����ɦV��List QQ Null�N�n
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

}
	
