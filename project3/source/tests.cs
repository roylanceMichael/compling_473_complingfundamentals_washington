namespace Ling473_Proj3
{
	using System;
	
	public class Tests 
	{
		private const char V1Char = 'เ';
		private const char C1Char = 'ษ';
		private const char C2Char = 'ร';
		private const char V2Char = 'ิ';
		private const char TChar = '\u0E48';
		private const char V3Char = 'า';
		private const char C3Char = 'ม';

		public void RunTests()
		{
			this.InitialState();
			this.InputLine2();
			this.InputLine46();
			this.CheckIfState0ReturnsState1WhenV1Char();
			this.CheckIfState0ReturnsState2WhenC1Char();
			this.CheckIfState1ReturnsState2WhenC1Char();
			this.CheckIfState2ReturnsState3WhenC2Char();
			this.CheckIfState2ReturnsState4WhenV2Char();
			this.CheckIfState2ReturnsState5WhenTChar();
			this.CheckIfState2ReturnsState6WhenV3Char();
			this.CheckIfState2ReturnsState7WhenV1Char();
			this.CheckIfState2ReturnsState8WhenC1Char();
			this.CheckIfState3ReturnsState4WhenV2Char();
			this.CheckIfState3ReturnsState5WhenTChar();
			this.CheckIfState3ReturnsState6WhenV3Char();
			this.CheckIfState3ReturnsState9WhenC3Char();
			this.CheckIfState4ReturnsState5WhenTChar();
			this.CheckIfState4ReturnsState6WhenV3Char();
			this.CheckIfState4ReturnsState9WhenC3Char();
			this.CheckIfState4ReturnsState7WhenV1Char();
			this.CheckIfState4ReturnsState8WhenC1Char();
		}

		public void InitialState()
		{
			// arrange
			const string input = "คู่แข่งขันต่างก็คุมเชิงกัน";
			const string expectedOutput = "คู่ แข่ง ขัน ต่าง ก็ คุม เชิง กัน";
			var fsm = new Fsm(true);

			// act
			var res = fsm.Process(input);

			// assert
			Console.Out.WriteLine(expectedOutput);
			Console.Out.WriteLine((res == expectedOutput) + " -> InitialState");
		}

		public void InputLine2()
		{
			// arrange
			const string input = "เขาเงียบไปครู่หนึ่งแล้วพูดขึ้น";
			const string expectedOutput = "เขา เงียบ ไป ครู่ หนึ่ง แล้ว พูด ขึ้น";
			var fsm = new Fsm(true);

			// act
			var res = fsm.Process(input);

			// assert
			Console.Out.WriteLine(expectedOutput);
			Console.Out.WriteLine((res == expectedOutput) + " -> InputLine2");
		}

		public void InputLine46()
		{
			// ช่วยย้ายกล่องนี้ไปไว้ที่ห้องนั้นหน่อย
			// arrange
			const string input = "ช่วยย้ายกล่องนี้ไปไว้ที่ห้องนั้นหน่อย";
			const string expectedOutput = "ช่วย ย้าย กล่อง นี้ ไป ไว้ ที่ ห้อง นั้น หน่อย";
			var fsm = new Fsm(true);

			// act
			var res = fsm.Process(input);

			// assert
			Console.Out.WriteLine(expectedOutput);
			Console.Out.WriteLine((res == expectedOutput) + " -> InputLine2");
		}

		public void CheckIfState0ReturnsState1WhenV1Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState0(V1Char);

			// assert
			Console.Out.WriteLine((res == 1) + " -> result of CheckIfState0ReturnsState1WhenV1Char");
		}

		public void CheckIfState0ReturnsState2WhenC1Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState0(C1Char);

			// assert
			Console.Out.WriteLine((res == 2) + " -> result of CheckIfState0ReturnsState2WhenC1Char");
		}

		public void CheckIfState1ReturnsState2WhenC1Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState1(C1Char);

			// assert
			Console.Out.WriteLine((res == 2) + " -> result of CheckIfState1ReturnsState2WhenC1Char");
		}

		public void CheckIfState2ReturnsState3WhenC2Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState2(C2Char);

			// assert
			Console.Out.WriteLine((res == 3) + " -> result of CheckIfState2ReturnsState3WhenC2Char");
		}	

		public void CheckIfState2ReturnsState4WhenV2Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState2(V2Char);

			// assert
			Console.Out.WriteLine((res == 4) + " -> result of CheckIfState2ReturnsState4WhenV2Char");
		}	

		public void CheckIfState2ReturnsState5WhenTChar()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState2(TChar);

			// assert
			Console.Out.WriteLine((res == 5) + " -> result of CheckIfState2ReturnsState5WhenTChar");
		}	

		public void CheckIfState2ReturnsState6WhenV3Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState2(V3Char);

			// assert
			Console.Out.WriteLine((res == 6) + " -> result of CheckIfState2ReturnsState6WhenV3Char");
		}

		public void CheckIfState2ReturnsState7WhenV1Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState2(V1Char);

			// assert
			Console.Out.WriteLine((res == 7) + " -> result of CheckIfState2ReturnsState7WhenV1Char");
		}

		public void CheckIfState2ReturnsState8WhenC1Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState2(C1Char);

			// assert
			Console.Out.WriteLine((res == 8) + " -> result of CheckIfState2ReturnsState8WhenC1Char");
		}

		public void CheckIfState3ReturnsState4WhenV2Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState3(V2Char);

			// assert
			Console.Out.WriteLine((res == 4) + " -> result of CheckIfState3ReturnsState4WhenV2Char");
		}

		public void CheckIfState3ReturnsState5WhenTChar()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState3(TChar);

			// assert
			Console.Out.WriteLine((res == 5) + " -> result of CheckIfState3ReturnsState5WhenTChar");
		}
		
		public void CheckIfState3ReturnsState6WhenV3Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState3(V3Char);

			// assert
			Console.Out.WriteLine((res == 6) + " -> result of CheckIfState3ReturnsState6WhenV3Char");
		}

		public void CheckIfState3ReturnsState9WhenC3Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState3(C3Char);

			// assert
			Console.Out.WriteLine((res == 9) + " -> result of CheckIfState3ReturnsState9WhenC3Char");
		}

		public void CheckIfState4ReturnsState5WhenTChar()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState4(TChar);

			// assert
			Console.Out.WriteLine((res == 5) + " -> result of CheckIfState4ReturnsState5WhenTChar");
		}

		public void CheckIfState4ReturnsState6WhenV3Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState4(V3Char);

			// assert
			Console.Out.WriteLine((res == 6) + " -> result of CheckIfState4ReturnsState6WhenV3Char");
		}

		public void CheckIfState4ReturnsState9WhenC3Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState4(C3Char);

			// assert
			Console.Out.WriteLine((res == 9) + " -> result of CheckIfState4ReturnsState9WhenC3Char");
		}

		public void CheckIfState4ReturnsState7WhenV1Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState4(V1Char);

			// assert
			Console.Out.WriteLine((res == 7) + " -> result of CheckIfState4ReturnsState7WhenV1Char");
		}

		public void CheckIfState4ReturnsState8WhenC1Char()
		{
			// arrange
			var fsm = new Fsm(true);

			// act
			var res = fsm.CheckIfState4(C1Char);

			// assert
			Console.Out.WriteLine((res == 8) + " -> result of CheckIfState4ReturnsState8WhenC1Char");
		}
	}
}