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
	public class Fsm
	{
		private static HashSet<Char> V1 = new HashSet<Char>{'\u0E40', '\u0E41', '\u0E42', '\u0E43','\u0E44'};
		private static HashSet<Char> C1 = new HashSet<Char>{'\u0E01','\u0E02','\u0E03','\u0E04','\u0E05','\u0E06',
			'\u0E07','\u0E08','\u0E09','\u0E0A','\u0E0B','\u0E0C','\u0E0D','\u0E0E','\u0E0F','\u0E10',
			'\u0E11','\u0E12','\u0E13','\u0E14','\u0E15','\u0E16','\u0E17','\u0E18','\u0E19','\u0E1A',
			'\u0E1B','\u0E1C','\u0E1D','\u0E1E','\u0E1F','\u0E20','\u0E21','\u0E22','\u0E23','\u0E24',
			'\u0E25','\u0E26','\u0E27','\u0E28','\u0E29','\u0E2A','\u0E2B','\u0E2C','\u0E2D','\u0E2E'};
		private static HashSet<Char> C2 = new HashSet<Char>{'\u0E23','\u0E25','\u0E27','\u0E19','\u0E21'};
		private static HashSet<Char> V2 = new HashSet<Char>{'\u0E34','\u0E35','\u0E36','\u0E37','\u0E38','\u0E39','\u0E31','\u0E47'};
		private static HashSet<Char> T = new HashSet<Char> { '\u0E48', '\u0E49', '\u0E4A', '\u0E4B' };
		private static HashSet<Char> V3 = new HashSet<Char>{'\u0E32','\u0E2D','\u0E22','\u0E27'};
		private static HashSet<Char> C3 = new HashSet<Char>{'\u0E07','\u0E19','\u0E21','\u0E14','\u0E1A','\u0E01','\u0E22','\u0E27'};

		private Dictionary<int, Func<char, int>> Transitions;

		private bool debug;

		public Fsm(bool debug = true)
		{
			this.debug = debug;
			this.Transitions = new Dictionary<int, Func<char, int>>
			{
				{ 0, CheckIfState0 },
				{ 1, CheckIfState1 },
				{ 2, CheckIfState2 },
				{ 3, CheckIfState3 },
				{ 4, CheckIfState4 },
				{ 5, CheckIfState5 },
				{ 6, CheckIfState6 },
				{ 7, (t) => { return 1; } },
				{ 8, (t) => { return 2; } },
				{ 9, (t) => { return 0; } },
			};
		}

		private void PrintIfDebug(string message)
		{
			if(this.debug)
			{
				Console.Out.WriteLine(message);
			}
		}

		public string Process(string input)
		{
			this.PrintIfDebug("Received Input: " + input);
			this.PrintIfDebug(C3.First().ToString());
			var acceptableState = 0;
			var workSpace = new StringBuilder();

			for(var i = 0; i < input.Length; i++) 
			{
				var result = this.Transitions[acceptableState](input[i]);				

				if(result == -1)
				{
					var returnError = "BAD RESULT: " + workSpace.ToString();
					this.PrintIfDebug(returnError);
					return returnError;
				}
				else if(acceptableState == 7 || acceptableState == 8)
				{
					i--;
					this.PrintIfDebug(string.Format("Current State: [{0}] ->\t(break before '{1}') ->\t[{2}]", acceptableState, input[i].ToString(), result));
					workSpace.Insert(workSpace.Length - 1, ' ');
				}
				else if(acceptableState == 9)
				{
					i--;
					this.PrintIfDebug(string.Format("Current State: [{0}] ->\t(break after '{1}') ->\t[{2}]", acceptableState, input[i].ToString(), result));
					workSpace.Append(' ');
				}
				else
				{
					this.PrintIfDebug(string.Format("Current State: [{0}] ->\t'{1}' ->\t[{2}]", acceptableState, input[i].ToString(), result));
					workSpace.Append(input[i]);
				}

				acceptableState = result;
			}

			this.PrintIfDebug(workSpace.ToString());

			return workSpace.ToString();
		}

		public int CheckIfState0(char val)
		{
			if(V1.Contains(val))	
			{
				return 1;
			}

			if(C1.Contains(val))	
			{
				return 2;
			}

			return -1;
		}

		public int CheckIfState1(char val)
		{
			if(C1.Contains(val))
			{
				return 2;
			}
			return -1;
		}

		public int CheckIfState2(char val)
		{
			if(C2.Contains(val))
			{
				return 3;
			}

			if(V2.Contains(val))
			{
				return 4;
			}

			if(T.Contains(val))
			{
				return 5;
			}

			if(V3.Contains(val))
			{
				return 6;
			}

			if(C3.Contains(val))
			{
				return 9;
			}

			if(V1.Contains(val))
			{
				return 7;
			}
			
			if(C1.Contains(val))
			{
				return 8;
			}

			return -1;
		}

		public int CheckIfState3(char val)
		{
			if(V2.Contains(val))
			{
				return 4;
			}

			if(T.Contains(val))
			{
				return 5;
			}

			if(V3.Contains(val))
			{
				return 6;
			}

			if(C3.Contains(val))
			{
				return 9;
			}

			return -1;
		}

		public int CheckIfState4(char val)
		{
			if(T.Contains(val))
			{
				return 5;
			}

			if(V3.Contains(val))
			{
				return 6;
			}

			if(C3.Contains(val))
			{
				return 9;
			}

			if(V1.Contains(val))
			{
				return 7;
			}

			if(C1.Contains(val))
			{
				return 8;
			}

			return -1;
		}

		public int CheckIfState5(char val)
		{
			if(V3.Contains(val))
			{
				return 6;
			}
			
			if(C3.Contains(val))
			{
				return 9;
			}

			if(V1.Contains(val))
			{
				return 7;
			}

			if(C1.Contains(val))
			{
				return 8;
			}

			return -1;
		}

		public int CheckIfState6(char val)
		{
			if(C3.Contains(val))
			{
				return 9;
			}
			
			if(V1.Contains(val))
			{
				return 7;
			}

			if(C1.Contains(val))
			{
				return 8;
			}

			return -1;
		}
	}
}
