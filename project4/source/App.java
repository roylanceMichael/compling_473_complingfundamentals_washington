import java.io.*;
import java.util.*;
import java.util.regex.*;

public class App {
	public static void main(String args[]) throws IOException {
		
		// load up trie
		if (args.length == 0) {
			Tests tests = new Tests();
			tests.Run();
			return;
		}

		BuildTrie buildTrie = LoadTrieFromFile(args[0]);

		// location of all the files to search through
		File folder = new File(args[1]);
		File[] fileList = folder.listFiles();
		Pattern dnaPattern = Pattern.compile("dna$");

		List<SearchTrie> searchTries = new ArrayList<SearchTrie>();

		for (int i = 0; i < fileList.length; i++) {
			// find dna files
			Matcher matcher = dnaPattern.matcher(fileList[i].toString());
			
			if(matcher.find()) {
				SearchTrie st = CreateSearchTrie(fileList[i].toString(), buildTrie.GetMaximumTrieSize(), buildTrie.GetMainTrie());
				System.out.println(st.PrintOffsets());
				searchTries.add(st);
			}
		}

		// build up extra credit dictionary
		HashMap<String, List<String>> sequenceFile = new HashMap<String, List<String>>();

		for(int i = 0; i < searchTries.size(); i++) {
			SearchTrie st = searchTries.get(i);

			for (Map.Entry<Integer, String> entry : st.ReportOffsets().entrySet()) {
				String sequence = entry.getValue();

				if(sequenceFile.containsKey(sequence)) {
					List<String> reports = sequenceFile.get(sequence);
					reports.add("\t" + Integer.toString(entry.getKey()) + "\t" + st.GetFileName());
				}
				else {
					List<String> reports = new ArrayList<String>();
					reports.add("\t" + Integer.toString(entry.getKey()) + "\t" + st.GetFileName());
					sequenceFile.put(sequence, reports);
				}
			}
		}

		// EXTRA CREDIT portion here:

		StringBuilder extraCreditFile = new StringBuilder();

		for (Map.Entry<String, List<String>> entry : sequenceFile.entrySet()) {
			extraCreditFile.append(entry.getKey());
			extraCreditFile.append("\n");

			List<String> fileOffsets = entry.getValue();
			
			for (int i = 0; i < fileOffsets.size(); i++) {
				extraCreditFile.append(fileOffsets.get(i));
				extraCreditFile.append("\n");
			}
		}

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter("extra-credit.txt"));
			writer.write(extraCreditFile.toString().trim());
		} 
		finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

	private static SearchTrie CreateSearchTrie(String fileName, int maximumTrieSize, Trie mainTrie) throws IOException {
		// location of the dna file
		// we want to process the trie here...
		File file = new File(fileName);
		
		SearchTrie searchTrie = new SearchTrie(maximumTrieSize, mainTrie, file.getName());

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