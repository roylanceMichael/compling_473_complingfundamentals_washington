if File.exists? './constituent.rb'
	require './constituent.rb'
else
	require './source/constituent.rb'
end

class Ptb
	def process(fileStr)
		# puts fileStr
		constituents = []
		currentConstituent = nil
		currentValue = ""

		fileStr.split("").each do |c|
			if c == "("

				# first take care of the current value
				if !currentValue.empty? && currentConstituent.pos.empty?
					currentConstituent.pos = currentValue
				end
				
				# we want to push this
				if currentConstituent == nil
					currentConstituent = Constituent.new
					constituents.push currentConstituent
				else 
					tmpConstituent = Constituent.new
					# set up parent/child relationship
					tmpConstituent.parent = currentConstituent
					currentConstituent.addChild(tmpConstituent)

					currentConstituent = tmpConstituent
				end
				
				currentValue = ""
			elsif c == ")"
				# let's take care of currentValue and set it to the currentConstituent 
				if !currentValue.empty?
					currentConstituent.value = currentValue
				end

				# we need to push back out now, either set to nil or to parent
				currentConstituent = currentConstituent.parent
				
				currentValue = ""

			elsif c.strip.empty?
				if !currentValue.empty? 
					currentConstituent.pos = currentValue
				end
				currentValue = ""
			else
				currentValue = "#{currentValue}#{c}"
			end
		end
		constituents
	end

	def pushVal(val, collection)
		if val == nil
			return
		end

		tmpVal = val.strip

		if !tmpVal.empty?
			collection.push tmpVal
		end
	end
end