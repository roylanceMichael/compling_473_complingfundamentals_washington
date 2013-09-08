import java.util.*;

public class BayesianReporter {
	private static final String DelimitCharStr = ".,!¡¥$£?¿;:()\"'—–-/[]¹²³«»";
	private HashMap<String, HashMap<String, Integer>> masterLanguageList;
	private HashMap<String, Integer> totalLanguageWordCount;
	private HashSet<Character> delimiterChars;
	
	public BayesianReporter(HashMap<String, HashMap<String, Integer>> masterLanguageList) {
		this.masterLanguageList = masterLanguageList;
		this.totalLanguageWordCount = new HashMap<String, Integer>();

		// update the total list per type
		for (Map.Entry<String, HashMap<String, Integer>> entry : this.masterLanguageList.entrySet()) {
			int totalWordCount = 0;
			for (Map.Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
				// add in 1 for each smoothing
				totalWordCount = totalWordCount + subEntry.getValue() + 1;
			}
			this.totalLanguageWordCount.put(entry.getKey(), totalWordCount);
		}

		this.delimiterChars = new HashSet<Character>();
		
		for(int i = 0; i < DelimitCharStr.length(); i++) {
			char currentChar = DelimitCharStr.charAt(i);
			this.delimiterChars.add(currentChar);
		}
	}

	private String CleanseSentence(String sentence) {
		StringBuilder workSpace = new StringBuilder();

		for(int i = 0; i < sentence.length(); i++) {
			char currentChar = sentence.charAt(i);
			if(!this.delimiterChars.contains(currentChar)) {
				workSpace.append(currentChar);
			}
		}
		return workSpace.toString();
	}

	private double ComputeProbabilityOfSentenceInLanguage(String languageKey, String[] sentenceWords) {
		HashMap<String, Integer> languageProbability = this.masterLanguageList.get(languageKey);
		
		double probabilityThisLanguage = (double)0;

		// get the total number of words including smoothing
		int totalCount = this.totalLanguageWordCount.get(languageKey);

		// precompute the amount to add if no words exist
		double noWordExistsProb = Math.log((double)1 / (double)totalCount);

		// automatically add 1 to the probability, for smoothing
		for(int i = 0; i < sentenceWords.length; i++) {
			if(languageProbability.containsKey(sentenceWords[i])) {
				
				double prob = Math.log(
					// add in smoothing
					((double)languageProbability.get(sentenceWords[i]) + 1) / 
					// divide by total with smoothing baked in
					(double)totalCount);

				probabilityThisLanguage = probabilityThisLanguage + prob;
			}
			else {
				probabilityThisLanguage = probabilityThisLanguage + noWordExistsProb;
			}
		}

		return probabilityThisLanguage;
	}

	public String ConsumeAndReportSentence(String sentence) {
		// we always get a number, then a tab, then the sentence
		// we always look for the tab first
		int sentenceFromTab = sentence.indexOf('\t');
		StringBuilder workSpace = new StringBuilder();

		if(sentenceFromTab > 0) {
			String fullSentence = this.CleanseSentence(sentence.substring(sentenceFromTab));
			// split by \\s+
			String[] words = fullSentence.split("\\s+");

			// report the first sentence
			workSpace.append(sentence);
			workSpace.append("\n");

			double lowestProbability = (double) -100000;
			String winningLanguage = "";

			// this is where I need to check, per language, the probability of each pass
			for (Map.Entry<String, HashMap<String, Integer>> entry : this.masterLanguageList.entrySet()) {
				String currentLang = entry.getKey();
				double probabilityThisLanguage = ComputeProbabilityOfSentenceInLanguage(currentLang, words);
				workSpace.append(currentLang);
				workSpace.append("\t");
				workSpace.append(probabilityThisLanguage);
				workSpace.append("\n");

				if(probabilityThisLanguage > lowestProbability) {
					lowestProbability = probabilityThisLanguage;
					winningLanguage = currentLang;
				}
			}

			workSpace.append("result");
			workSpace.append("\t");
			workSpace.append(winningLanguage);
			workSpace.append("\n");

		}

		return workSpace.toString().trim();
	}
}