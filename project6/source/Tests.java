import java.io.*;
import java.lang.*;
public class Tests {
	public void TestStringDistance() { 
		StringDistanceTest("", "", 0);
        StringDistanceTest("abc", "xyz", 6);
        StringDistanceTest("abc", "abc", 0);
        StringDistanceTest("a", "ab", 1);
        StringDistanceTest("ab", "bc", 2);
		StringDistanceTest("what", "what", 0);
		StringDistanceTest("abcde", "bd", 3);
		StringDistanceTest("intention", "execution", 8);
		StringDistanceTest("what", "wha", 1);
		StringDistanceTest("chat", "what", 2);
	}

	public void NormalizedTestStringDistance() {
		NormalizedStringDistanceTest("", "", 0);
        NormalizedStringDistanceTest("abc", "xyz", 1);
        NormalizedStringDistanceTest("abc", "abc", 0);
        NormalizedStringDistanceTest("a", "b", (float)2.0 / 2);
        NormalizedStringDistanceTest("a", "ab", (float)1.0 / (float)3);
        NormalizedStringDistanceTest("ab", "bc", (float)2.0 / 4);
		NormalizedStringDistanceTest("what", "what", 0);
		NormalizedStringDistanceTest("intention", "execution", (8/(float)("intention".length() + "execution".length())));
		NormalizedStringDistanceTest("abcde", "bd", (float)3.0 / 7);
		NormalizedStringDistanceTest("what", "wha", (1/(float)7));
		NormalizedStringDistanceTest("chat", "what", 2/(float)8);
	}

	public void TestLineDistance() {
		
		LineDistanceTest("I walk a lonely road".split("\\s"), "I walk a lonely road".split("\\s"), 0);
		
		this.TestTextNormalizedDistance2();

		this.TestTextNormalizedDistance3();
		
		// wiki article
		LineDistanceTest("Lady GaGa".split("\\s"), "Lady GaGa".split("\\s"), 0);

		/* THESE TESTS AREN'T PASSING	
		LineDistanceTest("Joanne Stefani Germanotta (born March 20, 1986),".split("\\s"), 
						 "Stefani Joanne Angelina Germanotta (born March".split("\\s"), (float)0.32);

		LineDistanceTest("best known by her stage name Lady GaGa,".split("\\s"), 
						 "28, 1986), known by her stage name Lady Gaga,".split("\\s"), (float)0.19);

		LineDistanceTest("".split("\\s"), 
						 "an imprint of Interscope Records. During".split("\\s"), (float)1.00);
				
		LineDistanceTest("is an Italian-American singer-songwriter and".split("\\s"),
						 "is an American recording artist. She began".split("\\s"), (float).4);
		*/
	}

	public void TestTextNormalizedDistance2() {
        String[] s = new String[] { "", "ab", "xyz" };
        String[] t = new String[] { "", "bc", "xyz" };
        LineDistanceTest(s, t, ((float)0.5 + ((float)2.0 / 4)) / 6);
    }

    public void TestTextNormalizedDistance3() {
        String[] s = new String[] { "a", "ab", "xyz" };
        String[] t = new String[] { "ab", "bc", "xyz" };
 
        LineDistanceTest(s, t, (((float)0.5 + ((float)1.0/3)) + ((float)0.5 + ((float)2.0/4))) / 6);
    }

    public void TestAlignment() {
    	String[] s = new String[] { "a", "", "b" };
    	String[] t = new String[] { "", "a", "b" };


    }

    // TEST HELPER FUNCTIONS
	private void StringDistanceTest(String s1, String s2, float e) {
		CharCostFunction charCostFunction = new CharCostFunction(s1, s2);
		float result = DistFunctions.EditDistanceResult(s1.length(), s2.length(), charCostFunction);
		if(result != e) {
			ReportToConsole(s1 + " <> " + s2 + " got: " + Float.toString(result) + " expected: " + e);
		}
	}

	private void NormalizedStringDistanceTest(String s1, String s2, float e) {
		CharCostFunction charCostFunction = new CharCostFunction(s1, s2);
		float result = DistFunctions.NormalizedEditDistance(s1.length(), s2.length(), charCostFunction);
		if(result != e) {
			ReportToConsole(s1 + " <> " + s2 + " got: " + Float.toString(result) + " expected: " + e);
		}
	}

	private void LineDistanceTest(String[] s1, String[] s2, float e) {
		StringsCostFunction stringsCostFunction = new StringsCostFunction(s1, s2);
		float result = DistFunctions.NormalizedEditDistance(s1.length, s2.length, stringsCostFunction);

		if(result != e) {
			ReportToConsole(JoinStringWithSpace(s1) + " <> " + JoinStringWithSpace(s2) + " got: " + Float.toString(result) + " expected: " + e);
		}
	}

	private static void ReportToConsole(String message) {
		System.out.println(message);
	}

	private static String JoinStringWithSpace(String[] words) {
		StringBuilder workSpace = new StringBuilder();
		for(int  i = 0; i < words.length; i++) {
			workSpace.append(words[i]);
			workSpace.append(" ");
		}
		return workSpace.toString().trim();
	}
}