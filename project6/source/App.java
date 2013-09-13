public class App {
	public static void main(String[] args) {
		if(args != null && args.length == 0) {
			Tests tests = new Tests();
			tests.TestStringDistance();
			tests.NormalizedTestStringDistance();
			tests.TestLineDistance();
			tests.LadyGaGaTest1();
			tests.LadyGaGaTest();
			return;
		}

		String[] firstFile = FileToStringService.ConvertFileToString(args[0]).split("\\r?\\n");
		String[] secondFile = FileToStringService.ConvertFileToString(args[1]).split("\\r?\\n");

		String result = DistFunctions.PrintOutAlignment(firstFile, secondFile);
		// print out
		System.out.println(result);
	}
}