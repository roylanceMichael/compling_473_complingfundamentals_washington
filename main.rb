# usage - ruby main.rb <location of file>
# output - a table with the information for sentence, noun phrases, verb phrases etc

def printVal(val)
	tmpVal = val.strip
	if(!tmpVal.empty?)
		puts "-#{tmpVal}"
	end
end

if ARGV != nil && ARGV.length > 0
	puts ARGV[0]
	# read file
	fileStr = (File.open(ARGV[0])).read
	# puts fileStr
	puts fileStr.length

	rightAfterOpenParen = false
	currentValue = ""

	fileStr.split("").each do |c|
		if c == "("
			rightAfterOpenParen = true
			printVal(currentValue)
			currentValue = ""
		elsif c == ")"
			printVal(currentValue)
			currentValue = ""
		elsif c.strip.empty?
			if(rightAfterOpenParen)
				
			end
			rightAfterOpenParen = false
			printVal(currentValue)
			currentValue = ""
		else
			currentValue = "#{currentValue}#{c}"
		end
	end
end


