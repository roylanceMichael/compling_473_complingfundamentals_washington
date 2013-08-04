require 'pennTreeBank.rb'

class PennBankParseTests
	def run
		reports_single_constituent
		#reports_multiple_constituents
		#reports_child_constituents
	end

	def reports_single_constituent
		# arrange
		testStr = "(NP something)"
		ptb = PennTreeBank.new

		# act
		res = ptb.process(testStr)
		puts res[0]
		# assert
		raise "#{res[0][0]} #{res[0][0]}" unless res[0][0] == "NP" && res[0][0] == "something"
	end

	def reports_multiple_constituents
		# arrange
		testStr = "(NP something) (VP yell)"
		ptb = PennTreeBank.new

		# act
		res = ptb.process(testStr)

		# assert
		raise "#{res[1].pos} #{res[1].value}" unless res[1].pos == "VP" && res[1].value == "yell"
	end

	def reports_child_constituents
		# arrange
		testStr = "(S (NP something))"
		ptb = PennTreeBank.new

		# act
		res = ptb.process(testStr)

		# assert
		print res
	end
end