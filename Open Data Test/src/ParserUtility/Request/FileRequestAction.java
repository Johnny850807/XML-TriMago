package ParserUtility.Request;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileRequestAction extends RequestAction{
	private File file;
	public FileRequestAction(String url) {
		super(url);
	}
	
	@Override
	protected void prepare() throws IOException {
		file = new File(url);
	}

	@Override
	protected void readerInitiate(BufferedReader reader) throws IOException {
		reader = new BufferedReader(
		        new FileReader(file));
	}

	@Override
	protected void printLog() throws Exception {}

	


}
