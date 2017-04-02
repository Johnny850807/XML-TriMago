package ParserUtility.Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileRequestAction extends RequestAction{
	private File file;
	private BufferedReader reader;
	public FileRequestAction(String url) {
		super(url);
	}
	
	@Override
	protected void prepare() throws IOException {
		file = new File(url);
	}

	@Override
	protected BufferedReader readerInitiate() throws IOException {
		return new BufferedReader(new InputStreamReader(
	            new FileInputStream(file),"UTF-8"));
	}

	@Override
	protected void printLog() throws Exception {
		System.out.println("Resource : " + file.getPath());
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

}
