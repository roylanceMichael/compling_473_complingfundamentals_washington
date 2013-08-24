namespace Ling473_Proj3
{
	using System;
	using System.IO;
	using System.Collections.Generic;
	using System.Linq;
	using System.Text;
	using System.Text.RegularExpressions;

	public class App
	{
		public static void Main(string[] args)
		{
			Console.OutputEncoding = System.Text.Encoding.UTF8;
			// run tests if anything here
			if(args != null && args.Length > 0 && args[0] == "test")
			{
				var tests = new Tests();
				tests.RunTests();
			}
			else
			{
				var fsm = new Fsm();
				Console.Out.WriteLine("<html><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><body>");
				
				string line;
				while ((line = Console.In.ReadLine()) != null) {
					Console.Out.Write(fsm.Process(line));
					Console.Out.WriteLine("<br/>");
				}
							
				Console.Out.WriteLine("</body></html>");
			}
		}
	}
}