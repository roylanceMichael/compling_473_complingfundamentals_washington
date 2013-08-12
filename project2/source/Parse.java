import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parse {
	private String fileLocation;
	private Hashtable<String, Integer> fileContents;

	public Parse(String fileLocation) {
		this.fileLocation = fileLocation;
		this.fileContents = new Hashtable<String, Integer>();
	}

	public void BuildHash() throws IOException {
		FileReader inputStream = null;

        try {
            inputStream = new FileReader(this.fileLocation);

            int c;
            StringBuilder word = new StringBuilder();
            String state = "";
            while ((c = inputStream.read()) != -1) {
            	
            	if (c == 32) {
            		this.HandleWordWhenTerminalFound(word);
            		word = new StringBuilder();
            	}
            	else if (c == 60) { // handle <
            		this.HandleWordWhenTerminalFound(word);
            		word = new StringBuilder();
            		state = "<";
            	} else if(c == 62) { // handle >
            		word = new StringBuilder();
            		state = "";
            	} else if (state.isEmpty()) {
            		if (this.IsValidAscii(c)) {
            			char charToProcess = (char) c;
            			word.append(charToProcess);
            		}
            	}

            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	}

	public String ReportHash() {
		StringBuilder sb = new StringBuilder();

		Enumeration<String> enumKey = this.fileContents.keys();
		while(enumKey.hasMoreElements()) {
			String key = enumKey.nextElement();
			Integer val = this.fileContents.get(key);
			sb.append(String.format("%s\t%s\n", key, val));
		}
		return sb.toString();
	}

	private Boolean IsValidAscii(Integer val) {
		if (val >= 65 && val >= 90) {
			return true;
		} else if (val >= 97 && val <= 122) {
			return true;
		} else if (val == 39) {
			return true;
		}
		return false;
	}

	private void HandleWordWhenTerminalFound(StringBuilder sbWord) {
		String word = sbWord.toString();
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
}