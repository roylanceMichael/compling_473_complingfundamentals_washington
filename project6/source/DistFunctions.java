public class DistFunctions {
	public static float[][] EditDistance(int val1OrigSize, int val2OrigSize, ICostFunction costFunction) {
		int val1Len = val1OrigSize + 1;
		int val2Len = val2OrigSize + 1;

		if(val1Len + val2Len == 0) {
			return new float[0][0];
		}

		float[][] d = new float[val1Len][val2Len];

		for(int i = 0; i < val1Len; i++) {
			for(int j = 0; j < val2Len; j++) {
				// setup initial array
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

					// cost function
					float cost = costFunction.CostFunction(i-1, j-1);

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

		return d;
	}

	public static float EditDistanceResult(int val1OrigSize, int val2OrigSize, ICostFunction costFunction) {
		return EditDistance(val1OrigSize, val2OrigSize, costFunction)[val1OrigSize][val2OrigSize];
	}

	public static float NormalizedEditDistance(int val1Size, int val2Size, ICostFunction costFunction) {
		float result = EditDistanceResult(val1Size, val2Size, costFunction);

		if(val1Size + val2Size == 0 || result == 0) {
			return 0;
		}
		
		return result / (float)(val1Size + val2Size);
	}
}