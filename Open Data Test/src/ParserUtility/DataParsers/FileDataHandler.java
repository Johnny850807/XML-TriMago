package ParserUtility.DataParsers;

import ParserUtility.Request.FileRequestAction;
import ParserUtility.Request.RequestAction;
import ParserUtility.Request.WebRequestAction;
import WebParserUtility.DataParsers.DtoParsers.DTOParser;

public abstract class FileDataHandler extends DataHandler{

	public FileDataHandler(String url) throws Exception {
		super(url);
	}

	@Override
	protected void openUrl() throws Exception {}

	@Override
	protected RequestAction createRequestAction() {
		return new FileRequestAction(url);
	}


	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
