if File.exists? './ptb.rb'
	require './ptb.rb'
else
	require './source/ptb.rb'
end

def countNumX(res, x)
	sCount = 0

	if res == nil || res.length == 0
		return sCount
	end

	# res is an array of constiuents
	res.each do |constituent|
		if(constituent.pos == x)
			sCount = sCount + 1
		end

		#search children
		sCount = sCount + countNumX(constituent.children, x)
	end

	return sCount
end

# usage - ruby main.rb <location of file>
# output - a table with the information for sentence, noun phrases, verb phrases etc
if ARGV != nil && ARGV.length > 0
	path = ARGV[0]

	Dir["#{path}/*"].each do |filePath|
		fileStr = (File.open(filePath)).read
		ptb = Ptb.new
		res = ptb.process fileStr

		puts "#{filePath}	#{countNumX(res, 'S')}	#{countNumX(res, 'NP')}"
		# puts res
		# puts "Successfully ran!"
	end
else
	puts "Did not successfully run!"
end