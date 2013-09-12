public class DistFunctions {
	public static float EditDistance(String value1, String value2) {

		int val1Len = value1.length();
		int val2Len = value2.length();

		if(val1Len + val2Len == 0) {
			return 0;
		}

		float[][] d = new float[val1Len][];
		
		// initialize all of the array as well
		for(int i = 0; i < val1Len; i++) {
			d[i] = new float[val2Len];
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
					float firstTemp = d[i-1][j] + 1;
					float secondTemp = d[i][j-1] + 1;
					float cost = value1.charAt(i-1) == value2.charAt(j-1) ? 0 : 2;

					float thirdTemp = d[i-1][j-1] + cost;

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

	public static float NormalizedEditDistance(String value1, String value2) {
		float result = EditDistance(value1, value2);
		int val1Len = value1.length();
		int val2Len = value2.length();
		if(val1Len + val2Len == 0 || result == 0) {
			return 0;
		}
		return result / (val1Len + val2Len);
	}
}