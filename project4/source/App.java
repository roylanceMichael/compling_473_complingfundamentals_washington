import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Timestamp;
import java.util.Date;

public class App {
	public static void main(String args[]) throws IOException {
		
		// load up trie
		if (args.length == 0) {
			Tests tests = new Tests();
			tests.Run();
			return;
		}

		BuildTrie buildTrie = LoadTrieFromFile(args[1]);

		// location of all the files to search through
		File folder = new File(args[0]);
		File[] fileList = folder.listFiles();
		Pattern dnaPattern = Pattern.compile("dna$");

		Date date = new java.util.Date();
		System.out.println(new Timestamp(date.getTime()));

		for (int i = 0; i < fileList.length; i++) {
			// find dna files
			Matcher matcher = dnaPattern.matcher(fileList[i].toString());
			
			if(matcher.find()) {
				// we want to process the trie here...
				SearchTrie st = CreateSearchTrie(fileList[i].toString(), buildTrie.GetMaximumTrieSize(), buildTrie.GetMainTrie());
				System.out.println(st.PrintOffsets());
			}
		}
		date = new java.util.Date();
		System.out.println(new Timestamp(date.getTime()));
	}

	private static SearchTrie CreateSearchTrie(String fileName, int maximumTrieSize, Trie mainTrie) throws IOException {
		// location of the dna file
		SearchTrie searchTrie = new SearchTrie(maximumTrieSize, mainTrie, fileName);
		FileReader inputStream = null;

		try {
        		inputStream = new FileReader(fileName);
        		int c;
        		while ((c = inputStream.read()) != -1) {
        			searchTrie.SearchString((Character.toString((char) c)).toUpperCase());
        		}

		} finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        searchTrie.EndSearch();

        return searchTrie;
	}

	private static BuildTrie LoadTrieFromFile(String trieFile) throws IOException {
		// location of the target file
		FileReader inputStream = null;
		StringBuilder trieString = new StringBuilder();

		try {
        		inputStream = new FileReader(trieFile);
        		int c;
        		while ((c = inputStream.read()) != -1) {
        			trieString.append(Character.toString((char) c));
        		}

		} finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        BuildTrie bt = new BuildTrie();
        bt.BuildDnaTrieFromString(trieString.toString());
        return bt;
	}
}