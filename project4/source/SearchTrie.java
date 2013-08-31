import java.util.*;

// this is going to happen on a per file basis
public class SearchTrie {
	
	private HashMap<Integer, String> foundOffsets;
	private int maximumSize;
	private int offset;
	private String temporaryString;
	private Trie trie;
	private String fileName;

	public SearchTrie(int maximumSize, Trie trie, String fileName) {
		this.foundOffsets = new HashMap<Integer, String>();
		this.maximumSize = maximumSize;
		this.offset = 0;
		this.temporaryString = "";
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

		return workspace.toString();
	}

	public void SearchString(String maximumSizedString) {
		// I want to make sure that SubString always searches the maximum amount
		this.temporaryString = this.temporaryString + maximumSizedString;
		
		if(this.temporaryString.length() <= this.maximumSize) {
			return;
		}
		
		for(int i = 0; i < this.temporaryString.length() - this.maximumSize; i++) {
			String substringToTest = this.temporaryString.substring(i, this.temporaryString.length() -1);
			String result = this.SearchGivenSubstring(substringToTest);

			if(result != null) {
				this.foundOffsets.put(this.offset, result);
			}

			this.offset = this.offset + 1;
		}

		// set the temporary string to be the remainder
		this.temporaryString = this.temporaryString.substring(this.temporaryString.length() - this.maximumSize);
	}

	public void EndSearch() {
		for(int i = 0; i < this.temporaryString.length(); i++) {
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
		Trie closureTrie = trie;
		for(int i = 0; i < substringValue.length(); i++) {

			String currentChar = Character.toString(substringValue.charAt(i));

			// are we a leaf? let's report back
			if (closureTrie.IsLeaf()) {
				this.trie = Trie.ResetTrie(this.trie);
				return substringValue.substring(0, i);
			}

			Trie foundTrie = closureTrie.GetTrie(currentChar);

			if (foundTrie != null) {
				closureTrie = foundTrie;
			}
			else {
				this.trie = Trie.ResetTrie(this.trie);
				return null;
			}
		}

		// are we a leaf? let's report back
		if (closureTrie.IsLeaf()) {
			this.trie = Trie.ResetTrie(this.trie);
			return substringValue.substring(0, substringValue.length());
		}

		this.trie = Trie.ResetTrie(this.trie);
		return null;
	}
}