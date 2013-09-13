public class StringsCostFunction implements ICostFunction {
	private String[] lines1;
	private String[] lines2;

	public StringsCostFunction(String[] lines1, String[] lines2) {
		this.lines1 = lines1;
		this.lines2 = lines2;
	}

	public float CostFunction(int idx1, int idx2) {
		String val1 = this.lines1[idx1];
		String val2 = this.lines2[idx2];

		if(val1.equals(val2)) {
			return 0;
		}

		CharCostFunction charCostFunction = new CharCostFunction(val1, val2);
		float result = DistFunctions.NormalizedEditDistance(val1.length(), val2.length(), charCostFunction);

		return (float)0.5 + result;
	}
}