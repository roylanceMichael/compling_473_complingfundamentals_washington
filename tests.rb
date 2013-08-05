require './ptb.rb'

class PennBankParseTests
	def run
		reports_single_constituent
		reports_multiple_constituents
		reports_child_constituents
	end

	def reports_single_constituent
		# arrange
		testStr = "(NP something)"
		ptb = Ptb.new

		# act
		res = ptb.process(testStr)

		# assert
		raise "ERROR - #{res[0].pos} #{res[0].value}" unless res[0].pos == "NP" && res[0].value == "something"
	end

	def reports_multiple_constituents
		# arrange
		testStr = "(NP something) (VP yell)"
		ptb = Ptb.new

		# act
		res = ptb.process(testStr)

		# assert
		raise "#{res[1].pos} #{res[1].value}" unless res[1].pos == "VP" && res[1].value == "yell"
	end

	def reports_child_constituents
		# arrange
		testStr = "(S (NP something))"
		ptb = Ptb.new

		# act
		res = ptb.process(testStr)
		child = res[0].children[0]
		# assert
		rails "#{child.pos} #{child.value}" unless child.pos == "NP" && child.value == "something"
	end
end