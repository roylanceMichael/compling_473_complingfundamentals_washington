using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

// to compile on patas/dryas, type
//
// $ gmcs project3.cs
//
// to run on patas/dryas, then type
//
// $ mono project3.exe
//
// view output on: http://uakari.ling.washington.edu/473/example.html

namespace Ling473_Proj3
{
	public class project3
	{
		static void Main(string[] args)
		{
			FSM fsm = new FSM();

			Console.Out.WriteLine("<html><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><body>");
			
			string line;
			while ((line = Console.In.ReadLine()) != null) {
				Console.Out.Write(fsm.Process(line));
				Console.Out.WriteLine("<br/>");
			}
						
			Console.Out.WriteLine("</body></html>");
		}
	}


	public class FSM
	{
		HashSet<Char> V1 = new HashSet<Char>("เแโใไ");
		HashSet<Char> C1 = new HashSet<Char>("กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮ");
		HashSet<Char> C2 = new HashSet<Char>("รลวนม");
		HashSet<Char> V2 = new HashSet<Char>("ิีึืุูั็");
		HashSet<Char> T = new HashSet<Char> { '\u0E48', '\u0E49', '\u0E4A', '\u0E4B' };
		HashSet<Char> V3 = new HashSet<Char>("าอยว");
		HashSet<Char> C3 = new HashSet<Char>("งนมดบกยว");

		public string Process(string s_in)
		{

			// some kind of Finite State Machine goes here
			// let's start here!
			foreach (var s in s_in) 
			{
				Console.Out.WriteLine(s);
			}

			return s_in;
		}
	}
}
