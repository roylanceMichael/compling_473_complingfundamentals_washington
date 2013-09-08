import java.util.*;

public class Tests {

	public void RunTests() {
		this.ReturnsProperUnigramEnglish();
		this.ReturnsProperUnigramJapanese();
	}

	private static BayesianReporter SetupReporter() {
		HashMap<String, HashMap<String, Integer>> masterList = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> englishWords = new HashMap<String, Integer>();
		englishWords.put("I", 1);
		englishWords.put("am", 2);
		englishWords.put("still", 1);
		englishWords.put("holding", 1);
		englishWords.put("on", 3);
		englishWords.put("yo", 1);
		masterList.put("English", englishWords);
		HashMap<String, Integer> japaneseWords = new HashMap<String, Integer>();
		japaneseWords.put("boku", 1);
		japaneseWords.put("wa", 2);
		japaneseWords.put("mada", 1);
		japaneseWords.put("koko", 1);
		japaneseWords.put("da", 3);
		japaneseWords.put("yo", 3);
		masterList.put("Japanese", japaneseWords);
		BayesianReporter reporter = new BayesianReporter(masterList);
		return reporter;
	}

	private void ReportIfNotTrue(Boolean result, String message) {
		if(!result) {
			System.out.println(message);
		}
	}

	public void ReturnsProperUnigramEnglish() {
		// arrange
		BayesianReporter reporter = SetupReporter();

		// act
		String report = reporter.ConsumeAndReportSentence("1\tI yo");

		// assert
		this.ReportIfNotTrue(report.contains("result\tEnglish"), "Result was not English for I\tyo");
	}

	public void ReturnsProperUnigramJapanese() {
		// arrange
		BayesianReporter reporter = SetupReporter();

		// act
		String report = reporter.ConsumeAndReportSentence("1\tboku yo");

		// assert
		this.ReportIfNotTrue(report.contains("result\tJapanese"), "Result was not Japanese for boku\tyo");
	}
}