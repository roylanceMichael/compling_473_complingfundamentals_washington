import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.Charset;

public class Main {
	private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	private static final String StripCharacters = ".,!¡¥$£?¿;:()\"'—–-/[]¹²³«»";

	private static String DecodeUTF8(byte[] bytes) {
	    return new String(bytes, UTF8_CHARSET);
	}

	public static void main(String args[]) throws IOException {
		// location of all the files to search through
		File folder = new File(args[0]);
		File[] fileList = folder.listFiles();
		Pattern commonLangFile = Pattern.compile("lm$");

		for (int i = 0; i < fileList.length; i++) {
			// find dna files
			Matcher matcher = commonLangFile.matcher(fileList[i].toString());
			
			if(matcher.find()) {
				File file = new File(fileList[i].toString());
				String fileBytes = ReadFile(file);
				//	System.out.println(fileBytes);

				BufferedWriter writer = null;

				try {
					writer = new BufferedWriter(new FileWriter("extra-credit.txt"));
					writer.write(fileBytes);
				} 
				finally {
					if(writer != null) {
						writer.close();
					}
				}
			}
		}
	}


	private static String ReadFile(File file) throws IOException
    {
    	StringBuffer buffer = new StringBuffer();
	    try {
	        FileInputStream fis = new FileInputStream(file.getPath());
	        InputStreamReader isr = new InputStreamReader(fis, "UTF8");
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
	        return null;
	    }
    }
}