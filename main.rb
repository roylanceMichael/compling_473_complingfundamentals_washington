require 'pennTreeBank.rb'
# usage - ruby main.rb <location of file>
# output - a table with the information for sentence, noun phrases, verb phrases etc
if ARGV != nil && ARGV.length > 0
	fileStr = (File.open(ARGV[0])).read
	ptb = PennTreeBank.new
end


def printVal(val, currentSent)
	tmpVal = val.strip
	if(!tmpVal.empty?)
		#puts "-#{tmpVal}"
		currentSent.push(val)
	end
end