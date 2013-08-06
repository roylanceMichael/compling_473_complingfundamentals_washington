if File.exists? './ptb.rb'
	require './ptb.rb'
else
	require './source/ptb.rb'
end

# usage - ruby main.rb <location of file>
# output - a table with the information for sentence, noun phrases, verb phrases etc
if ARGV != nil && ARGV.length > 0
	fileStr = (File.open(ARGV[0])).read
	ptb = Ptb.new
	ptb.process fileStr
end