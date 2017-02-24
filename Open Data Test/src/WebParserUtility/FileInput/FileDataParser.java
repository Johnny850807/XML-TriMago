package WebParserUtility.FileInput;

import WebParserUtility.DTOParser;
import WebParserUtility.DataParser;
import WebParserUtility.Request.WebRequestAction;

public abstract class FileDataParser extends DataParser{

	public FileDataParser(String url) throws Exception {
		super(url);
	}

	@Override
	protected void openUrl() throws Exception {
		
	}

	@Override
	protected WebRequestAction createRequestAction() {
		return null;
	}


	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
