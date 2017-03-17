package WebParserUtility.DataParsers.DtoParsers;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/* DTOParser is a process of parsing Data String from
 * the specific data-type given from concrete DataParser.
 */

public abstract class DTOParser<T> implements Closeable{
	public List<T> parseData(String ...document) throws IOException, SAXException, ParserConfigurationException {
		List<T> parsedData = createParsedData(document);
		close();
		return parsedData;
	}
	
	/* Override this method to create own tailored object collection 
	 * via a data string corresponding the way you define in the method.
	 */
	protected abstract List<T> createParsedData(String ...document) throws SAXException, IOException, ParserConfigurationException ;
}
