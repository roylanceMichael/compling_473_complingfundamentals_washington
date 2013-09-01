public class BuildTrie {
	private Trie rootTrie;
	private Trie iterator;
	private int maximumSize;
	private int temporarySize;

	public BuildTrie() {
		this.rootTrie = new Trie(true);
		this.maximumSize = 0;
		this.temporarySize = 0;
	}

	public Trie GetMainTrie() {
		this.rootTrie = Trie.ResetTrie(this.rootTrie);
		return this.rootTrie;
	}

	public int GetMaximumTrieSize() {
		return this.maximumSize;
	}

	public void BuildDnaTrieFromString(String dnaSequence) {
		for(int i = 0; i < dnaSequence.length(); i++) {

			char currentChar = dnaSequence.charAt(i);
			
			boolean isDna = Trie.IsDna(currentChar);
			
			if (!isDna) {
				// reset if not correct
				if (this.temporarySize > this.maximumSize) {
					this.maximumSize = this.temporarySize;
				}

				this.temporarySize = 0;

				this.rootTrie = Trie.ResetTrie(this.rootTrie);
				continue;
			}

			Trie foundTrie = this.rootTrie.GetTrie(currentChar);
			
			if (foundTrie == null) {
				this.rootTrie.SetTrie(currentChar);
			}

			this.rootTrie = this.rootTrie.GetTrie(currentChar);
			this.temporarySize = this.temporarySize + 1;
		}

		if (this.temporarySize > this.maximumSize) {
			this.maximumSize = this.temporarySize;
		}
	}
}