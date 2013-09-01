public class Trie {
	private boolean root;
	private Trie parent;
	private Trie a;
	private Trie c;
	private Trie g;
	private Trie t;

	public Trie(boolean root) {
		this.root = root;
	}

	public void SetTrie(char value) {
		Trie newTrie = new Trie(false);
		newTrie.SetParent(this);

		if (value == 'A' || value == 'a') {
			this.a = newTrie;
		}
		else if (value == 'G' || value == 'g') {
			this.g = newTrie;
		}
		else if (value == 'C' || value == 'c') {
			this.c = newTrie;
		}
		else if (value == 'T' || value == 't') {
			this.t = newTrie;
		}
	}

	public Trie GetTrie(char value) {

		if (value == 'A') {
			return this.a;
		}
		else if (value == 'G') {
			return this.g;
		}
		else if (value == 'C') {
			return this.c;
		}
		else if (value == 'T') {
			return this.t;
		}

		return null;
	}

	public void SetParent(Trie parent) {
		if (this.root) {
			throw new IllegalArgumentException();
		}
		this.parent = parent;
	}

	public Trie GetParent() {
		return this.parent;
	}

	public boolean IsLeaf() { 
		return this.a == null &&
				this.c == null &&
				this.g == null &&
				this.t == null;
	}

	public static boolean IsDna(char value) {

		return value == 'A' || value == 'a' || 
				value == 'C' || value == 'c' || 
				value == 'G' || value == 'g' ||
				value == 'T' || value == 't';
	}

	public static Trie ResetTrie(Trie trie) {
		Trie closure = trie;
		while(closure.GetParent() != null) {
			closure = closure.GetParent();
		}
		return closure;
	}
}