import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Tests {
	public void RunTests() throws IOException {
		this.OneWordReturnedWhenXmlCharsAreAdded();
	}

	private InputStreamReader CreateStreamFromString(String value) {
		// convert String into InputStream
		InputStream is = new ByteArrayInputStream(value.getBytes());
		return new InputStreamReader(is);
	}

	private void WriteToConsoleIfFalse(Boolean value, String contextualErrorInformation) {
		if (!value) {
			System.out.println(contextualErrorInformation);
		}
	}

	public void OneWordReturnedWhenXmlCharsAreAdded() throws IOException  {
		// arrange
		InputStreamReader is = CreateStreamFromString("<ignore>test</what>");

		Parse p = new Parse();
		// act 
		p.BuildHash(is);
		
		// assert
		HashMap<String, Integer> results = p.ReportHash();
		this.WriteToConsoleIfFalse(results.get("test") == 1, "expected 1 count for test for WriteToConsoleIfFalse");
		this.WriteToConsoleIfFalse(results.size() == 1, "expected size of 1 for WriteToConsoleIfFalse");
	}

	public void TwoWordReturnedWhenBadCharsAreInsideWord() throws IOException  {
		// arrange
		InputStreamReader is = CreateStreamFromString("this_is");

		Parse p = new Parse();
		// act 
		p.BuildHash(is);
		
		// assert
		HashMap<String, Integer> results = p.ReportHash();
		this.WriteToConsoleIfFalse(results.get("this") == 1, "expected 1 count for test for TwoWordReturnedWhenBadCharsAreInsideWord");
		this.WriteToConsoleIfFalse(results.get("is") == 1, "expected count of 1 for TwoWordReturnedWhenBadCharsAreInsideWord");
		this.WriteToConsoleIfFalse(results.size() == 2, "expected size of 2 for TwoWordReturnedWhenBadCharsAreInsideWord");
	}

	public void NoSingleAposAtEndWhenManyExistOnFrontAndBack() throws IOException  {
		// arrange
		InputStreamReader is = CreateStreamFromString("'''what'''");

		Parse p = new Parse();
		// act 
		p.BuildHash(is);
		
		// assert
		HashMap<String, Integer> results = p.ReportHash();
		this.WriteToConsoleIfFalse(results.get("what") == 1, "expected 1 count for test for NoSingleAposAtEndWhenManyExistOnFrontAndBack");
		this.WriteToConsoleIfFalse(results.size() == 1, "expected size of 2 for NoSingleAposAtEndWhenManyExistOnFrontAndBack");
	}

	public void SingleAposInMiddleWhenOneExistsInMiddle() throws IOException  {
		// arrange
		InputStreamReader is = CreateStreamFromString("'''wh'at'''");

		Parse p = new Parse();
		// act 
		p.BuildHash(is);
		
		// assert
		HashMap<String, Integer> results = p.ReportHash();
		this.WriteToConsoleIfFalse(results.get("wh'at") == 1, "expected 1 count for test for SingleAposInMiddleWhenOneExistsInMiddle");
		this.WriteToConsoleIfFalse(results.size() == 1, "expected size of 2 for SingleAposInMiddleWhenOneExistsInMiddle");
	}
}