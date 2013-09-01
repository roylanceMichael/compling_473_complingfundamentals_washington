import java.util.*;

public class Tests {
	public void Run() {
		this.CorrectTrieWhenGivenACGT();
		this.CorrectTrieWhenGivenACNGT();
		this.CorrectTrieWhenGivenTwoBuffers();
		this.CorrectTrieWhenGivenTwoBuffers2();
		this.CorrectSubsetIdentifiedWhenGivenTrie();
		this.CorrectSubsetIdentifiedWhenGivenComplexString();
		this.CorrectSearchIdentifiedWhenGivenString();
		this.CorrectSearchIdentifiedWhenGivenStreamOfStrings();
		this.CorrectSearchIdentifiedWhenGivenStreamOfStringsMoreComplex();
		this.CorrectSearchIdentifiedWhenHandledBadChars();
	}

	private void CorrectTrieWhenGivenACGT() {
		// arrange
		BuildTrie bt = new BuildTrie();

		// act
		bt.BuildDnaTrieFromString("ACGT");

		// assert
		Trie rootTrie = bt.GetMainTrie();

		boolean result = true;
		// this will throw an exception if bad
		result = rootTrie.GetTrie('A').GetTrie('C').GetTrie('G').GetTrie('T') != null;
		System.out.println(result + " -> CorrectTrieWhenGivenACGT");
	}

	private void CorrectTrieWhenGivenACNGT() {
		// arrange
		BuildTrie bt = new BuildTrie();

		// act
		bt.BuildDnaTrieFromString("ACNGT");

		// assert
		Trie rootTrie = bt.GetMainTrie();

		boolean result = true;
		// this will throw an exception if bad
		result = rootTrie.GetTrie('A').GetTrie('C').IsLeaf() && rootTrie.GetTrie('G').GetTrie('T').IsLeaf();
		System.out.println(result + " -> CorrectTrieWhenGivenACNGT");
	}

	private void CorrectTrieWhenGivenTwoBuffers() {
		// arrange
		BuildTrie bt = new BuildTrie();

		// act
		bt.BuildDnaTrieFromString("ACN");
		bt.BuildDnaTrieFromString("GT");

		// assert
		Trie rootTrie = bt.GetMainTrie();

		boolean result = true;
		// this will throw an exception if bad
		result = rootTrie.GetTrie('A').GetTrie('C').IsLeaf() && rootTrie.GetTrie('G').GetTrie('T').IsLeaf();
		System.out.println(result + " -> CorrectTrieWhenGivenTwoBuffers");
	}

	private void CorrectTrieWhenGivenTwoBuffers2() {
		// arrange
		BuildTrie bt = new BuildTrie();

		// act
		bt.BuildDnaTrieFromString("AC");
		bt.BuildDnaTrieFromString("NGT");

		// assert
		Trie rootTrie = bt.GetMainTrie();

		boolean result = true;
		// this will throw an exception if bad
		result = rootTrie.GetTrie('A').GetTrie('C').IsLeaf() && rootTrie.GetTrie('G').GetTrie('T').IsLeaf();
		System.out.println(result + " -> CorrectTrieWhenGivenTwoBuffers2");
	}

	private void CorrectSubsetIdentifiedWhenGivenTrie() {
		// arrange
		String sequence = "ACGT";
		BuildTrie bt = new BuildTrie();
		bt.BuildDnaTrieFromString(sequence);
		Trie rootTrie = bt.GetMainTrie();
		SearchTrie st = new SearchTrie(bt.GetMaximumTrieSize(), rootTrie, "test");

		// act
		String sRes = st.SearchGivenSubstring(sequence);

		// assert
		boolean result = true;
		// this will throw an exception if bad
		result = sRes.equals(sequence);
		System.out.println(result + " -> CorrectSubsetIdentifiedWhenGivenTrie");
	}

	private void CorrectSubsetIdentifiedWhenGivenComplexString() {
		// arrange
		String sequence = "ACGT";
		BuildTrie bt = new BuildTrie();
		bt.BuildDnaTrieFromString(sequence);
		Trie rootTrie = bt.GetMainTrie();
		SearchTrie st = new SearchTrie(bt.GetMaximumTrieSize(), rootTrie, "test");

		// act
		String sRes = st.SearchGivenSubstring("ACGTTTT");

		// assert
		boolean result = true;
		// this will throw an exception if bad
		result = sRes.equals(sequence);
		System.out.println(result + " -> CorrectSubsetIdentifiedWhenGivenComplexString");
	}

	private void CorrectSearchIdentifiedWhenGivenString() {
		// arrange
		String sequence = "ACGT";
		BuildTrie bt = new BuildTrie();
		bt.BuildDnaTrieFromString(sequence);
		Trie rootTrie = bt.GetMainTrie();
		SearchTrie st = new SearchTrie(bt.GetMaximumTrieSize(), rootTrie, "test");

		// act
		String compareSeq = "TACGTTTT";
		for (int i = 0; i < compareSeq.length(); i++) {
			st.SearchString(compareSeq.charAt(i));
		}
		st.EndSearch();

		// assert
		HashMap<Integer, String> offsetResult = st.ReportOffsets();
		boolean result = true;
		result = offsetResult.get(1).equals(sequence);
		// this will throw an exception if bad
		System.out.println(result + " -> CorrectSearchIdentifiedWhenGivenString");
	}

	private void CorrectSearchIdentifiedWhenGivenStreamOfStrings() {
		// arrange
		String sequence = "ACGT";
		BuildTrie bt = new BuildTrie();
		bt.BuildDnaTrieFromString(sequence);
		Trie rootTrie = bt.GetMainTrie();
		SearchTrie st = new SearchTrie(bt.GetMaximumTrieSize(), rootTrie, "test");

		// act
		String testString = "TACGTTTT";
		for (int i = 0; i < testString.length(); i++) {
			st.SearchString(testString.charAt(i));
		}
		st.EndSearch();

		// assert
		HashMap<Integer, String> offsetResult = st.ReportOffsets();
		boolean result = true;

		result = offsetResult.get(1).equals(sequence);
		// this will throw an exception if bad
		System.out.println(result + " -> CorrectSearchIdentifiedWhenGivenStreamOfStrings");
	}

	private void CorrectSearchIdentifiedWhenGivenStreamOfStringsMoreComplex() {
		// arrange
		String sequence = "ACGT";
		BuildTrie bt = new BuildTrie();
		bt.BuildDnaTrieFromString(sequence);
		Trie rootTrie = bt.GetMainTrie();
		SearchTrie st = new SearchTrie(bt.GetMaximumTrieSize(), rootTrie, "test");

		// act
		String testString = "TTTGTACGTTTT";
		for (int i = 0; i < testString.length(); i++) {
			st.SearchString(testString.charAt(i));
		}
		st.EndSearch();

		// assert
		HashMap<Integer, String> offsetResult = st.ReportOffsets();
		boolean result = true;

		result = offsetResult.get(5).equals(sequence);
		// this will throw an exception if bad
		System.out.println(result + " -> CorrectSearchIdentifiedWhenGivenStreamOfStringsMoreComplex");
	}

	private void CorrectSearchIdentifiedWhenHandledBadChars() {
		// arrange
		String sequence = "ACGT";
		BuildTrie bt = new BuildTrie();
		bt.BuildDnaTrieFromString(sequence);
		Trie rootTrie = bt.GetMainTrie();
		SearchTrie st = new SearchTrie(bt.GetMaximumTrieSize(), rootTrie, "test");

		// act
		String testString = "TTNGTACGTRTT";
		for (int i = 0; i < testString.length(); i++) {
			st.SearchString(testString.charAt(i));
		}
		st.EndSearch();

		// assert
		HashMap<Integer, String> offsetResult = st.ReportOffsets();
		boolean result = true;

		result = offsetResult.get(5).equals(sequence);
		// this will throw an exception if bad
		System.out.println(result + " -> CorrectSearchIdentifiedWhenHandledBadChars");
	}
}