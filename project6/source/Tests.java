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

		// wiki article
		NormalizedStringDistanceTest("Lady GaGa", "Lady GaGa", 0);

			
		NormalizedStringDistanceTest("Joanne Stefani Germanotta (born March 20, 1986),", 
						 "Stefani Joanne Angelina Germanotta (born March", (float)0.32);

		NormalizedStringDistanceTest("best known by her stage name Lady GaGa,", 
						 "28, 1986), known by her stage name Lady Gaga,", (float)0.19);

		NormalizedStringDistanceTest("", 
						 "an imprint of Interscope Records. During", (float)1.00);
				
		NormalizedStringDistanceTest("is an Italian-American singer-songwriter and",
						 "is an American recording artist. She began", (float).4);
	}

	public void TestLineDistance() {
		
		LineDistanceTest("I walk a lonely road".split("\\s"), "I walk a lonely road".split("\\s"), 0);
		
		this.TestTextNormalizedDistance2();

		this.TestTextNormalizedDistance3();

		this.TestAlignmentSmallDifference();

		this.TestAlignmentSame();

		this.LadyGaGaTest();
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

    public void TestAlignmentSmallDifference() {
    	String[] s = new String[] { "a", "", "b" };
    	String[] t = new String[] { "", "a", "b" };

    	LineDistanceTest(s, t, (float)2.0 / (float)6.0);
    }

    public void TestAlignmentSame() {
    	String[] s = new String[] { "a", "b", "c" };
    	String[] t = new String[] { "a", "b", "c" };

    	LineDistanceTest(s, t, 0);
    }

    public void LadyGaGaTest() {
    	String[] s = new String[] { 
    		"Lady GaGa", 
    		"Joanne Stefani Germanotta (born March 20, 1986),", 
    		"best known by her stage name Lady GaGa," };
    	
    	String[] t = new String[] { 
    		"Lady GaGa", 
    		"Stefani Joanne Angelina Germanotta (born March", 
    		"28, 1986), known by her stage name Lady Gaga," };

    	ICostFunction stringsCostFunction = new StringsCostFunction(s, t);

        float[][] d = DistFunctions.EditDistance(s.length, t.length, stringsCostFunction);

        //PrintOutDistanceArray(d, s, t);

        String response = DistFunctions.PrintOutAlignment(s, t);

        String answer = "<Lady GaGa> 0.0 <Lady GaGa>\n<Joanne Stefani Germanotta (born March 20, 1986),> 0.32 <Stefani Joanne Angelina Germanotta (born March>\n<best known by her stage name Lady GaGa,> 0.19 <28, 1986), known by her stage name Lady Gaga,>";

        if(!response.equals(answer)) {
			System.out.println("received: " + response + " was expecting " + answer);
		}
    }

    public void LadyGaGaTest1() {
    	String[] s = new String[] { "x", "ab", "xyz" };
    	
    	String[] t = new String[] { "ab", "bc", "xyz" };

    	ICostFunction stringsCostFunction = new StringsCostFunction(s, t);

        float[][] d = DistFunctions.EditDistance(s.length, t.length, stringsCostFunction);

        //PrintOutDistanceArray(d, s, t);

        String response = DistFunctions.PrintOutAlignment(s, t);

        String answer = "<x> 1.0 <>\n<ab> 0.0 <ab>\n<> 1.0 <bc>\n<xyz> 0.0 <xyz>";
		if(!response.equals(answer)) {
			System.out.println("received: " + response + " was expecting " + answer);
		}
    }

	// TEST HELPER FUNCTIONS
	private void StringDistanceTest(String s1, String s2, float e) {
		ICostFunction charCostFunction = new CharCostFunction(s1, s2);
		float result = DistFunctions.EditDistanceResult(s1.length(), s2.length(), charCostFunction);
		if(result != e) {
			ReportToConsole(s1 + " <> " + s2 + " got: " + Float.toString(result) + " expected: " + e);
		}
	}

	private void NormalizedStringDistanceTest(String s1, String s2, float e) {
		ICostFunction charCostFunction = new CharCostFunction(s1, s2);
		float result = DistFunctions.NormalizedEditDistance(s1.length(), s2.length(), charCostFunction);
		if(result != e) {
			ReportToConsole(s1 + " <> " + s2 + " got: " + Float.toString(result) + " expected: " + e);
		}
	}

	private void LineDistanceTest(String[] s1, String[] s2, float e) {
		ICostFunction stringsCostFunction = new StringsCostFunction(s1, s2);
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

	private static void PrintOutDistanceArray(float[][] d, String[] words1, String[] words2) {
		int sizeWords1 = d.length;
		int sizeWords2 = d[0].length;

		System.out.println("Matrix for (i)" + JoinStringWithSpace(words1) + " <-> (j)" + JoinStringWithSpace(words2));

		StringBuilder workSpace = new StringBuilder();
		for(int i = 0; i < sizeWords1; i++) {
			for(int j = 0; j < sizeWords2; j++) {
				workSpace.append(Float.toString(d[i][j]) + " ");
			}
			workSpace.append("\n");
		}

		System.out.println(workSpace.toString());
	}
}