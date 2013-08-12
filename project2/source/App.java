import java.io.IOException;

public class App {
	public static void main(String args[]) throws IOException {
		if(args == null || args.length == 0){
			System.out.println("No input file added");
			return;
		}

		Parse p = new Parse(args[0]);
		p.BuildHash();
		String results = p.ReportHash();

		System.out.println(results);
	}
}