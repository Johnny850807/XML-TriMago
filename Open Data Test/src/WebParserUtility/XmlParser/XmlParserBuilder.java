package WebParserUtility.XmlParser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.jar.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParserBuilder<T> {
	private MyXmlHandler<T> myHandler;
	public XmlParserBuilder(){
		myHandler = new MyXmlHandler<T>();
	}
	
	public XmlParserBuilder<T> addKey(String tag){
		myHandler.addTag(tag);
		return this;
	}
	
	public XmlParserBuilder<T> entityName(String name){
		myHandler.setEntityName(name);
		return this;
	}
	
	public XmlParserBuilder<T> classType(Class<T> classType){
		myHandler.setClass(classType);
		return this;
	}
	
	public MyXmlHandler<T> build(){
		return myHandler;
	}
	
	
	class MyXmlHandler<T> extends DefaultHandler{
	    private List<String> tags = Collections.checkedList(new ArrayList<String>(), String.class);;
	    private String entityName;
	    private T entity;
	    private Class<T> classType;
	    private String temp;
	    private ArrayList<T> entityList = new ArrayList<T>();
	    
	    public void setEntityName(String name){
	    	entityName = name;
	    }
	    
	    public void addTag(String tag){
	    	tags.add(tag);
	    }
	    
	    public void setClass(Class<T> classType){
	    	this.classType = classType;
	    }
	
	    @Override
	    public void characters(char[] buffer, int start, int length) {
	    	temp = new String(buffer, start, length);
	    }
	    
		@Override
		public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes)
				throws SAXException {
			temp = "";
	         if (qName.equalsIgnoreCase(entityName)) {
	        	 try {
					entity = classType.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
	         }
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
				for ( String tag : tags )
					handlerTag(tag);
		}	
		
		public void handlerTag(String tag){
			try{
				if (tag.equalsIgnoreCase(entityName)) 
		        	 entityList.add(entity);
				else
				{
					tag = Character.toUpperCase(tag.charAt(0))+tag.substring(1);
					Method method = classType.getMethod("set"+tag, null);
					method.invoke(temp);
				}
			}catch(Exception err){
				err.printStackTrace();
			}
		}

		public List<T> getEntityList(){
			return entityList;
		}

	}
}
