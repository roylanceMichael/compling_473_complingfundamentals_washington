class Parser
	@fileLocation
	@fileContents

	def initialize(fileLocation)
		@fileLocation = fileLocation
		@fileContents = Hash.new
	end

	def parseFile
		# open up the file
		fileToStream = File.new(@fileLocation)

		# intialize current value
		word = ""
		state = ""

		# acceptance regex
		# A-Z, a-z and '
		acceptanceRegex = /[A-Za-z']/

		# cycle through each character
		fileToStream.each_byte do |c|
			val = c.chr
			
			# 4 states
			# < state means we ignore
			# > state means we accept again
			# (space) state mans we add our current value into our hash
			# normal char means we are building up our current word

			if word == " "
				handleCurrentWordGivenSpace(word)
				word = ""
			elsif word == "<"
				state = "<"
				handleCurrentWordGivenSpace(word)
				word = ""
			elsif word == ">"
				state = ""
				# reset, altho it should be empty already
				word = ""
			elsif state != "<"
				isMatch = val =~ acceptanceRegex

				if isMatch
					word = "#{word}#{val}"
				end
			end

			if @fileContents.length % 50 == 0
				puts "rise and shine #{@fileContents.length} #{val}"
			end
		end
	end

	def handleCurrentWordGivenSpace(word)
		# return if word is empty
		if word == nil || word.empty?
			return
		end

		if @fileContents.has_key(word)
			@fileContents[word] = @fileContents[word] + 1
		else
			@fileContents[word] = 1
		end
	end
end