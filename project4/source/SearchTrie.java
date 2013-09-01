import java.util.*;

// this is going to happen on a per file basis
public class SearchTrie {
	
	private HashMap<Integer, String> foundOffsets;
	private int maximumSize;
	private int offset;
	private StringBuilder temporaryString;
	private Trie trie;
	private String fileName;

	public SearchTrie(int maximumSize, Trie trie, String fileName) {
		this.foundOffsets = new HashMap<Integer, String>();
		this.maximumSize = maximumSize;
		this.offset = 0;
		this.temporaryString = new StringBuilder();
		this.trie = trie;
		this.fileName = fileName;
	}

	public HashMap<Integer, String> ReportOffsets() {
		return this.foundOffsets;
	}

	public String PrintOffsets() {
		StringBuilder workspace = new StringBuilder();
		workspace.append(this.fileName);
		workspace.append("\n");
		

		for (Map.Entry<Integer, String> entry : this.foundOffsets.entrySet()) {
			workspace.append("\t" + entry.getKey() + "\t" + entry.getValue());
			workspace.append("\n");
		}

		return workspace.toString().trim();
	}

	public String GetFileName() {
		return this.fileName;
	}

	public void SearchString(char value) {
		// I want to make sure that SubString always searches the maximum amount
		this.temporaryString.append(value);
		
		if(this.temporaryString.length() <= this.maximumSize) {
			return;
		}
		
		for (int i = 0; i < this.temporaryString.length() - this.maximumSize; i++) {
			String substringToTest = this.temporaryString.substring(i, this.temporaryString.length() -1);
			
			String result = this.SearchGivenSubstring(substringToTest);

			if(result != null) {
				this.foundOffsets.put(this.offset, result);
			}

			this.offset = this.offset + 1;
		}

		// set the temporary string to be the remainder
		this.temporaryString.delete(0, this.temporaryString.length() - this.maximumSize);
	}

	public void EndSearch() {
		for (int i = 0; i < this.temporaryString.length(); i++) {
			String substringToTest = this.temporaryString.substring(i, this.temporaryString.length() - 1);
			String result = this.SearchGivenSubstring(substringToTest);

			if(result != null) {
				this.foundOffsets.put(this.offset, result);
			}

			this.offset = this.offset + 1;
		}
	}

	// will return trie found if successful, null otherwise
	public String SearchGivenSubstring(String substringValue) {
		for(int i = 0; i < substringValue.length(); i++) {

			char currentChar = substringValue.charAt(i);

			// are we a leaf? let's report back
			if (this.trie.IsLeaf()) {
				this.trie = Trie.ResetTrie(this.trie);
				return substringValue.substring(0, i).toUpperCase();
			}

			Trie foundTrie = this.trie.GetTrie(currentChar);

			if (foundTrie != null) {
				this.trie = foundTrie;
			}
			else {
				this.trie = Trie.ResetTrie(this.trie);
				return null;
			}
		}

		// are we a leaf? let's report back
		if (this.trie.IsLeaf()) {
			this.trie = Trie.ResetTrie(this.trie);
			return substringValue.substring(0, substringValue.length()).toUpperCase();
		}

		this.trie = Trie.ResetTrie(this.trie);
		return null;
	}
}