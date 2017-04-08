import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import ParserUtility.DataParsers.DataHandler;
import ParserUtility.DataParsers.FileDataHandler;

/**
 * ���O:
 * 1. �ӷ��ɮ׭n�O�o�]�C�W UTF-8 
 * 2. �ϥΨ쪺�귽(�p:�ɮ�)�n���� ���M�i��L�k�N���G��J�s�����W
 */

public class SearchLowRateCommentServlet extends XmlQueryServlet{
	private static final String BASE_QUERY = 
			"//Waterball:comment[@rate < 3]/child::*";
	
	@Override
	protected void extractParameter(HttpServletRequest request) {}
	
	@Override
	protected String getXpathFormat() {
		return BASE_QUERY;
	}
}


