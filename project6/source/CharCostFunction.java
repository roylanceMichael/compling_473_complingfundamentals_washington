public class CharCostFunction implements ICostFunction {
	private String val1;
	private String val2;

	public CharCostFunction(String val1, String val2) {
		this.val1 = val1;
		this.val2 = val2;
	}

	public float CostFunction(int idx1, int idx2) {
		if(this.val1.charAt(idx1) == this.val2.charAt(idx2)) {
			return 0;
		}
		return 2;
	}
}