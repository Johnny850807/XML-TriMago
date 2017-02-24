package WebParserUtility;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public abstract class DTOParser<T> implements Closeable{
	public Collection parseData(String document) throws Exception{
		Collection parsedData = createParsedData(document);
		close();
		return parsedData;
	}
	
	/* Override this method to create own tailored object collection 
	 * via a data string corresponding the way you define in the method.
	 */
	protected abstract List<T> createParsedData(String document) throws Exception;
}
