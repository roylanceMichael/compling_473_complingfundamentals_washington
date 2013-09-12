public class DistFunctions {
	public static double EditDistance(String value1, String value2) {

		int val1Len = value1.length() + 1;
		int val2Len = value2.length() + 1;

		if(val1Len + val2Len == 0) {
			return 0;
		}

		double[][] d = new double[val1Len][];
		
		// initialize all of the array as well
		for(int i = 0; i < val1Len; i++) {
			d[i] = new double[val2Len];
		}

		for(int i = 0; i < val1Len; i++) {
			for(int j = 0; j < val2Len; j++) {
				if(j == 0 && i == 0) {
					d[i][j] = 0;
				}
				else if(j == 0) {
					d[i][j] = i;
				}
				else if(i == 0) {
					d[i][j] = j;
				}
				else {
					double firstTemp = d[i-1][j] + 1;
					double secondTemp = d[i][j-1] + 1;
					// cost function
					double cost = value1.charAt(i-1) == value2.charAt(j-1) ? 0 : 2;

					double thirdTemp = d[i-1][j-1] + cost;

					if(firstTemp <= secondTemp && firstTemp <= thirdTemp){
	                    d[i][j] = firstTemp;
	                }
	                else if(secondTemp <= firstTemp && secondTemp <= thirdTemp){
	                    d[i][j] = secondTemp;
	                }
	                else{
	                    d[i][j] = thirdTemp;
	                }
				}
			}
		}

		return d[val1Len - 1][val2Len - 1];
	}

	public static double EditDistanceLine(String line1, String line2) {
		String[] line1Words = line1.trim().split("\\s");
		String[] line2Words = line2.trim().split("\\s");

		int size1 = line1Words.length + 1;
		int size2 = line2Words.length + 1;

		if(line1Words.length + line2Words.length == 0) {
			return 0;
		}

		double[][] d = new double[size1][];
		
		// initialize all of the array as well
		for(int i = 0; i < size1; i++) {
			d[i] = new double[size2];
		}

		for(int i = 0; i < size1; i++) {
			for(int j = 0; j < size2; j++) {
				if(j == 0 && i == 0) {
					d[i][j] = 0;
				}
				else if(j == 0) {
					d[i][j] = i;
				}
				else if(i == 0) {
					d[i][j] = j;
				}
				else {
					double firstTemp = d[i-1][j] + 1;
					double secondTemp = d[i][j-1] + 1;

					// substitution function
					// the cost is either 0 if the strings are the same
					double cost = 0;
					// or 0.5 + editdistance between the two words
					if(!line1Words[i-1].equals(line2Words[j-1])) {
						cost = (double)0.5 + NormalizedEditDistance(line1Words[i-1], line2Words[j-1]);
					}
					
					double thirdTemp = d[i-1][j-1] + cost;

					if(firstTemp <= secondTemp && firstTemp <= thirdTemp){
	                    d[i][j] = firstTemp;
	                }
	                else if(secondTemp <= firstTemp && secondTemp <= thirdTemp){
	                    d[i][j] = secondTemp;
	                }
	                else{
	                    d[i][j] = thirdTemp;
	                }
				}
			}
		}

		return d[size1-1][size2-1] / ((double)(line1Words.length + line2Words.length));
	}

	public static double NormalizedEditDistance(String value1, String value2) {
		double result = EditDistance(value1, value2);
		int val1Len = value1.length();
		int val2Len = value2.length();

		if(val1Len + val2Len == 0 || result == 0) {
			return 0;
		}
		
		return result / (val1Len + val2Len);
	}
}