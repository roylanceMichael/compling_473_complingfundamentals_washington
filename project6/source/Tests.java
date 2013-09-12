import java.io.*;
import java.lang.*;
public class Tests {
	public void TestStringDistance() { 
		// equal
		StringDistanceTest("what", "what", 0);
		// equal
		StringDistanceTest("abcde", "bd", 3);
		// equal
		StringDistanceTest("intention", "execution", 8);
		// one insertion
		StringDistanceTest("what", "wha", 1);
		// one update
		StringDistanceTest("chat", "what", 2);
	}

	public void NormalizedTestStringDistance() {
		// equal
		NormalizedStringDistanceTest("what", "what", 0);
		// equal
		NormalizedStringDistanceTest("intention", "execution", (8/(double)("intention".length() + "execution".length())));
		// equal
		NormalizedStringDistanceTest("abcde", "bd", 0.42857142857142855);
		// one insertion
		NormalizedStringDistanceTest("what", "wha", (1/(double)7));
		// one update
		NormalizedStringDistanceTest("chat", "what", 2/(double)8);
	}

	public void TestLineDistance() {
		LineDistanceTest("I walk a lonely road", "I walk a lonely road", 0);
		
		// wiki article
		LineDistanceTest("Lady GaGa", "Lady GaGa", 0);
		
		LineDistanceTest("Joanne Stefani Germanotta (born March 20, 1986),", 
						 "Stefani Joanne Angelina Germanotta (born March", 0.32);
		
		LineDistanceTest("best known by her stage name Lady GaGa,", 
						 "28, 1986), known by her stage name Lady Gaga,", 0.19);
		
		LineDistanceTest("", 
						 "an imprint of Interscope Records. During", 1.00);

		LineDistanceTest("is an Italian-American singer-songwriter and",
						 "is an American recording artist. She began", .4);
	}

	public void TestLadyGagaGagaFile() {

	}

	private static void StringDistanceTest(String s1, String s2, double e) {
		double result = DistFunctions.EditDistance(s1, s2);
		if((double)result != e) {
			ReportToConsole(s1 + "<>" + s2 + " got:" + Double.toString(result) + " expected: " + e);
		}
	}

	private static void NormalizedStringDistanceTest(String s1, String s2, double e) {
		double result = DistFunctions.NormalizedEditDistance(s1, s2);
		if((double)result != e) {
			ReportToConsole(s1 + "<>" + s2 + " got:" + Double.toString(result) + " expected: " + e);
		}
	}

	private static void LineDistanceTest(String s1, String s2, double e) {
		double result = DistFunctions.EditDistanceLine(s1, s2);
		if(result != e) {
			ReportToConsole(s1 + "<>" + s2 + " got:" + Double.toString(result) + " expected: " + e);
		}
	}

	private static void ReportToConsole(String message) {
		System.out.println(message);
	}
}