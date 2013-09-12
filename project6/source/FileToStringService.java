import java.io.*;
import java.util.*;

public class FileToStringService {
	public static String ConvertFileToString(String fileName) {
		StringBuffer buffer = new StringBuffer();
	    try {
	    	File file = new File(fileName);
	        FileInputStream fis = new FileInputStream(file.getPath());
	        InputStreamReader isr = new InputStreamReader(fis);
	        Reader in = new BufferedReader(isr);
	        int ch;
	        while ((ch = in.read()) > -1) {
	            buffer.append((char)ch);
	        }
	        in.close();
	        return buffer.toString();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	        return "";
	    }
	}
}