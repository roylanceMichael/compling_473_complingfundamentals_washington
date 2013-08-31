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

	public void SetTrie(String value) {
		String normalized = value.toUpperCase();

		Trie newTrie = new Trie(false);
		newTrie.SetParent(this);

		if (normalized.equals("A")) {
			this.a = newTrie;
		}
		else if (normalized.equals("G")) {
			this.g = newTrie;
		}
		else if (normalized.equals("C")) {
			this.c = newTrie;
		}
		else if (normalized.equals("T")) {
			this.t = newTrie;
		}
	}

	public Trie GetTrie(String value) {
		String normalized = value.toUpperCase();

		if (normalized.equals("A")) {
			return this.a;
		}
		else if (normalized.equals("G")) {
			return this.g;
		}
		else if (normalized.equals("C")) {
			return this.c;
		}
		else if (normalized.equals("T")) {
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

	public static boolean IsDna(String value) {
		String normalized = value.toUpperCase();

		return normalized.equals("A") ||
				normalized.equals("C") ||
				normalized.equals("G") ||
				normalized.equals("T");
	}

	public static Trie ResetTrie(Trie trie) {
		Trie closure = trie;
		while(closure.GetParent() != null) {
			closure = closure.GetParent();
		}
		return closure;
	}
}