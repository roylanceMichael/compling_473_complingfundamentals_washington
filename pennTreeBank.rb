require 'constituent.rb'

class PennTreeBank
	def process(fileStr)
		# puts fileStr
		constituents = []
		currentConstituent = []
		currentValue = ""

		fileStr.split("").each do |c|
			if c == "("
				pushVal(currentValue, currentConstituent)
				
				if(currentConstituent.length == 1)
					constituents.push currentConstituent
					currentConstituent = []
				end
				
				currentValue = ""
			elsif c == ")"
				pushVal(currentValue, currentConstituent)
				
				if currentConstituent.length > 0
					constituents.push(currentConstituent)
					currentConstituent = []
				end
				
				currentValue = ""

			elsif c.strip.empty?
				pushVal(currentValue, currentConstituent)
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