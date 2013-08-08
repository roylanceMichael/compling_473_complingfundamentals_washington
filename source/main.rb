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
		puts "#{filePath}	#{countPtb.countNumX(res, sentence)}	#{countPtb.countNumX(res, np)}	#{countPtb.countNumX(res, vp)}	#{countPtb.countNumDitran(res)}	#{countPtb.countNumIntran(res)}"
		# puts res
		# puts "Successfully ran!"
	end
else
	puts "Did not successfully run!"
end