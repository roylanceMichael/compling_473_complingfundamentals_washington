public class Tests {
	public void TestStringDistance() { 
		// equal
		StringDistanceTest("what", "what", 0);
		// one insertion
		StringDistanceTest("what", "wha", 1);
		// one update
		StringDistanceTest("chat", "what", 2);
	}

	public void NormalizedTestStringDistance() {
		// equal
		NormalizedStringDistanceTest("what", "what", 0);
		// one insertion
		NormalizedStringDistanceTest("what", "wha", (1/(double)7));
		// one update
		NormalizedStringDistanceTest("chat", "what", 2/(double)8);
	}

	private static void StringDistanceTest(String s1, String s2, double e) {
		float result = DistFunctions.EditDistance(s1, s2);
		if((double)result != e) {
			System.out.println(s1 + "<>" + s2 + " got:" + Float.toString(result) + " expected: " + e);
		}
	}

	private static void NormalizedStringDistanceTest(String s1, String s2, double e) {
		float result = DistFunctions.NormalizedEditDistance(s1, s2);
		if((double)result != e) {
			System.out.println(s1 + "<>" + s2 + " got:" + Float.toString(result) + " expected: " + e);
		}
	}
}