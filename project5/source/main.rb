lmExpr = /lm$/
punctuationStripper = /.,!¡¥$£?¿;:()"'—–-\/\[\]¹²³«»/

class LanguageTuple
	attr_accessor :word, :count
end

# encoding: utf-8
# usage - ruby main.rb <location of file>
# output - a table with the information for sentence, noun phrases, verb phrases etc
if ARGV != nil && ARGV.length > 0
	path = ARGV[0]

	counts = {}

	Dir["#{path}/*"].each do |filePath|
		if (filePath =~ lmExpr) != nil
			# process this file
			fileStr = (File.open(filePath)).read
			line = fileStr.force_encoding("ISO-8859-1").encode("UTF-8").split("\n")
			
			listOfCounts = []

			line.each do |wholeLine|
				words = wholeLine.split("\t")
				tuple = LanguageTuple.new
				tuple.word = words[0]
				tuple.count = words[1]
				listOfCounts.push(tuple)
			end

			counts[fileStr] = listOfCounts
		end 
		
		counts.each do |k, v|
			puts k
			v.each do |item| 
				puts "#{item.word} - #{item.count}"
			end
		end
	end


else
	puts "Please add parameters! <folderLocation>"
end

