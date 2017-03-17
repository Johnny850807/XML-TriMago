package ParserUtility.DataParsers;

import ParserUtility.Request.FileRequestAction;
import ParserUtility.Request.RequestAction;

public class FileDataHandler extends DataHandler{
	private FileRequestAction fileAction;

	public FileDataHandler(String url) throws Exception {
		super(url);
	}

	@Override
	protected void openUrl() {}

	@Override
	protected RequestAction createRequestAction() {
		fileAction = new FileRequestAction(url);
		return fileAction;
	}
	
	@Override
	public void close() {}

	
}
