import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.Charset;

public class App {
	private static final String ISO88591 = "ISO-8859-1";
	private static final Charset ISO88591_CHARSET = Charset.forName(ISO88591);
	private static final String StripCharacters = ".,!¡¥$£?¿;:()\"'—–-/[]¹²³«»";

	private static String DecodeUTF8(byte[] bytes) {
	    return new String(bytes, ISO88591_CHARSET);
	}

	public static void main(String args[]) throws IOException {
		if(args == null || args.length == 0) {
			Tests tests = new Tests();
			tests.RunTests();
			return;
		}

		// location of all the files to search through
		File folder = new File(args[0]);
		File[] fileList = folder.listFiles();
		Pattern commonLangFile = Pattern.compile("lm$");
		// build up extra credit dictionary
		HashMap<String, HashMap<String, Integer>> masterLanguageList = new HashMap<String, HashMap<String, Integer>>();

		for (int i = 0; i < fileList.length; i++) {
			// find dna files
			Matcher matcher = commonLangFile.matcher(fileList[i].toString());
			
			if(matcher.find()) {
				File file = new File(fileList[i].toString());
				HashMap<String, Integer> languageHashMap = new HashMap<String, Integer>();
				masterLanguageList.put(CleanseFileName(file.getName()), languageHashMap);

				String fileString = ReadFileToString(file);
				String[] splitStrings = fileString.split("\\s+");
				for(int j = 0; j < splitStrings.length; j+=2) {
					languageHashMap.put(
						// word
						splitStrings[j],
						// count
						Integer.parseInt(splitStrings[j+1])
						);
				}
			}
		}

		// at this point I should have all my languages loaded into this HashMap structure
		BayesianReporter reporter = new BayesianReporter(masterLanguageList);

		// normal assignment 
		File file = new File(args[1]);
		String fileString = ReadFileToString(file);
		// split by newline
		String[] lines = fileString.split("\n");
		for(int j = 0; j < lines.length; j++) {
			String reportSentence = reporter.ConsumeAndReportSentence(lines[j]);
			System.out.println(reportSentence);
		}

		// extra credit
	}

	private static String CleanseFileName(String fileName) {
		int indexOfFirstPeriod = fileName.indexOf(".");
		return fileName.substring(0, indexOfFirstPeriod);
	}

	private static String ReadFileToString(File file) throws IOException
    {
    	StringBuffer buffer = new StringBuffer();
	    try {
	        FileInputStream fis = new FileInputStream(file.getPath());
	        InputStreamReader isr = new InputStreamReader(fis, "ISO-8859-1");
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