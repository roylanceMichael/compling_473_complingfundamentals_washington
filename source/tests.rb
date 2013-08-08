if File.exists? './ptb.rb'
	require './ptb.rb'
else
	require './source/ptb.rb'
end

class PennBankParseTests
	def run
		reports_single_constituent
		reports_multiple_constituents
		reports_child_constituents
		reports_child_constituents_no_space
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
		raise "ERROR #{res[1].pos} #{res[1].value}" unless res[1].pos == "VP" && res[1].value == "yell"
	end

	def reports_child_constituents
		# arrange
		testStr = "(S (NP something))"
		ptb = Ptb.new

		# act
		res = ptb.process(testStr)
		child = res[0].children[0]

		# assert
		raise "ERROR - #{child.pos} #{child.value}" unless child.pos == "NP" && child.value == "something"
	end

	def reports_child_constituents_no_space
		# arrange
		testStr = "(S(NP something))"
		ptb = Ptb.new

		# act
		res = ptb.process(testStr)
		child = res[0]
		
		# assert
		raise "ERROR - #{child.pos} #{child.value}" unless child.pos == "S" && child.value == nil
	end
end