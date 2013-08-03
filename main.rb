# usage - ruby main.rb <location of file>
# output - a table with the information for sentence, noun phrases, verb phrases etc
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
			puts currentValue
			currentValue = ""
		elsif c == ")"
			puts currentValue
			currentValue = ""
		elsif rightAfterOpenParen
			currentValue = "#{currentValue}#{c}"
		elsif c == " "
			puts currentValue
			currentValue = ""
		else
			currentValue = "#{currentValue}#{c}"
		end
	end
end