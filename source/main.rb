if File.exists? './ptb.rb'
	require './ptb.rb'
	require './countPtb.rb'
else
	require './source/ptb.rb'
	require './source/countPtb.rb'
end

# constants used
sentence = "S"
vp = "VP"
np = "NP"

# usage - ruby main.rb <location of file>
# output - a table with the information for sentence, noun phrases, verb phrases etc
if ARGV != nil && ARGV.length > 0
	path = ARGV[0]

	ptb = Ptb.new
	countPtb = CountPtb.new

	Dir["#{path}/*"].each do |filePath|
		fileStr = (File.open(filePath)).read		
		res = ptb.process fileStr
		indivReport = countPtb.runTotal(res, filePath)
		puts indivReport
		# puts res
		# puts "Successfully ran!"
	end

	puts countPtb.reportSumTotal(path)
else
	puts "Did not successfully run!"
end