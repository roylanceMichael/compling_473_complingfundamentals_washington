import java.math.*;
import java.util.ArrayList;
import java.util.List;

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

	public static String PrintOutAlignment(String[] lines1, String[] lines2) {
		ICostFunction costFunction = new StringsCostFunction(lines1, lines2);
		float[][] distanceArray = EditDistance(lines1.length, lines2.length, costFunction);

		int i = distanceArray.length - 1;
		int j = distanceArray[0].length - 1;

		List<Tuple> listOfIdxTuples = new ArrayList<Tuple>();

		while(i > 0 && j > 0) {
			// diagnal means we print both out
			float diagVal = distanceArray[i-1][j-1];
			float leftVal = distanceArray[i][j-1];
			float bottomVal = distanceArray[i-1][j];

			if(diagVal < leftVal && diagVal < bottomVal) {
				listOfIdxTuples.add(new Tuple(i--, j--));
			}
			else if(leftVal <= bottomVal) {
				listOfIdxTuples.add(new Tuple(0, j--));
			}
			else {
				listOfIdxTuples.add(new Tuple(i--, 0));
			}
		}

		while (i > 0) {
				listOfIdxTuples.add(new Tuple(i--, 0));
		}

		while (j > 0) {
				listOfIdxTuples.add(new Tuple(0, j--));			
		}

		StringBuilder workSpace = new StringBuilder();

		for(int k = listOfIdxTuples.size()-1; k >= 0; k--) {
			Tuple t = listOfIdxTuples.get(k);

			String val1 = t.x == 0 ? "" : lines1[t.x-1];
			String val2 = t.y == 0 ? "" : lines2[t.y-1];

			ICostFunction tmpCostFunction = new CharCostFunction(val1, val2);

			workSpace.append("<" + val1 + "> " + 
				Float.toString(NormalizedEditDistance(val1.length(), val2.length(), tmpCostFunction)) + 
				" <" + val2 + ">");
			workSpace.append("\n");
		}

		return workSpace.toString().trim();
	}

	public static float EditDistanceResult(int val1OrigSize, int val2OrigSize, ICostFunction costFunction) {
		float value = EditDistance(val1OrigSize, val2OrigSize, costFunction)[val1OrigSize][val2OrigSize];
		return Round(value, 2);
	}

	public static float NormalizedEditDistance(int val1Size, int val2Size, ICostFunction costFunction) {
		float result = EditDistanceResult(val1Size, val2Size, costFunction);

		if(val1Size + val2Size == 0 || result == 0) {
			return 0;
		}
		
		return Round(result / (float)(val1Size + val2Size), 2);
	}

	// http://stackoverflow.com/questions/8911356/whats-the-best-practice-to-round-a-float-to-2-decimals - Thank you!
    public static float Round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}