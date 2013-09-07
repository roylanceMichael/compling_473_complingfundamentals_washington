import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.Charset;

public class Main {
	private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

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
				byte[] fileBytes = ReadFile(file);
				System.out.println(DecodeUTF8(fileBytes));
			}
		}
	}


	private static byte[] ReadFile(File file) throws IOException
    {
        final int file_size = (int) file.length();
 
        byte[] file_buf = new byte[file_size];
 
        FileInputStream input = new FileInputStream(file);
 
        int input_len = input.read(file_buf);
 
        if (input_len != file_size) {
            System.err.println("Didn't read all the bytes of the file: "
                    + file_size + " size vs " + input_len + " read");
        }
 
        input.close();
 
        return file_buf;
    }
}