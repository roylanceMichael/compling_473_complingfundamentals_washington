public class App {
	public static void main(String[] args) {
		if(args != null && args.length == 0) {
			Tests tests = new Tests();
			tests.TestStringDistance();
			tests.NormalizedTestStringDistance();
		}
	}
}