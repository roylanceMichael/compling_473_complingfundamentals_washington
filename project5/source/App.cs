using System.IO;
using System;

public class App
{
	public static void Main(string[] args) 
	{
		Console.OutputEncoding = System.Text.Encoding.UTF8;

	    using (StreamReader reader = new StreamReader("../content/fin.unigram-lm",System.Text.Encoding.UTF8))
	    {
	        string line;

	        while ((line = reader.ReadLine()) != null)
	        {
	            Console.WriteLine(line);
	        }

	    }
	}
}