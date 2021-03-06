import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Parse {
	private HashMap<String, Integer> fileContents;

	public Parse() {
		this.fileContents = new HashMap<String, Integer>();
	}

	private Boolean IsValidAscii(Integer val) {
		if (val >= 65 && val <= 90) {
			return true;
		} else if (val >= 97 && val <= 122) {
			return true;
		} else if (val == 39) {
			return true;
		}
		return false;
	}
	
	/*
		a. Trim any occurrence(s) of the straight apostrophe from the beginning and end of the 
		word. Note that this preserves internal occurrences of this character, as in the words 
		“Marvin's” or “O'Conner.”
		b. Convert every word to lower-case the word
	*/
	private String CleanseWord(String word) {
		if (word == null || word.isEmpty()) {
			return word;
		}
		
		String returnWord = word;

		// first part of word
		char firstChar = returnWord.charAt(0);
		while (firstChar == '\'' && !returnWord.isEmpty()) {
			returnWord = returnWord.substring(1, returnWord.length());
			
			if (returnWord.length() > 0) {
				firstChar = returnWord.charAt(0);
			}
		}

		if (returnWord.isEmpty()) {
			return returnWord;
		}

		char lastChar = returnWord.charAt(returnWord.length() - 1);
		
		while (lastChar == '\'' && !returnWord.isEmpty()) {
			returnWord = returnWord.substring(0, returnWord.length() - 1);

			if (returnWord.length() > 0) {
				lastChar = returnWord.charAt(returnWord.length() - 1);
			}
		}

		if (returnWord.isEmpty()) {
			return returnWord;
		}

		returnWord = returnWord.toLowerCase();

		return returnWord;
	}

	private void HandleWordWhenTerminalFound(StringBuilder sbWord) {
		String word = CleanseWord(sbWord.toString());

		if (word.isEmpty()) {
			return;
		}

		if (this.fileContents.containsKey(word)) {
			Integer oldVal = this.fileContents.get(word);
			this.fileContents.put(word, oldVal + 1);
		} else {
			this.fileContents.put(word, 1);
		}
	}

	public void BuildHash(InputStreamReader inputStream) throws IOException {
            int c;
            StringBuilder word = new StringBuilder();
            Boolean insideSmglTag = false;

            while ((c = inputStream.read()) != -1) {
            	
            	if (c == 60) { // handle <
            		this.HandleWordWhenTerminalFound(word);
            		word = new StringBuilder();
            		insideSmglTag = true;
            	} else if(c == 62) { // handle >
            		word = new StringBuilder();
            		insideSmglTag = false;
            	} else if (!this.IsValidAscii(c)) {
            		this.HandleWordWhenTerminalFound(word);
            		word = new StringBuilder();
            	}
            	else if (!insideSmglTag) {
        			char charToProcess = (char) c;
        			word.append(charToProcess);
            	}
            }
	}

	public HashMap<String, Integer> ReportHash() {
		return this.fileContents;
	}
}