class CountPtb




	def initialize
		@sentenceTotal = 0
		@npTotal = 0
		@vpTotal = 0
		@vpDitran = 0
		@vpIntran = 0

		# constants used
		@sentence = "S"
		@vp = "VP"
		@np = "NP"
	end

	def runTotal(res, filePath)
		tmpSentenceTotal = countNumX(res, @sentence)
		tmpNpTotal = countNumX(res, @np)
		tmpVpTotal = countNumX(res, @vp)
		tmpVpDitran = countNumDitran(res)
		tmpVpIntran = countNumIntran(res)

		@sentenceTotal = @sentenceTotal + tmpSentenceTotal
		@npTotal = @npTotal + tmpNpTotal
		@vpTotal = @vpTotal + tmpVpTotal
		@vpDitran = @vpDitran + tmpVpDitran
		@vpIntran = @vpIntran + tmpVpIntran

		"#{filePath}	#{tmpSentenceTotal}	#{tmpNpTotal}	#{tmpVpTotal}	#{tmpVpDitran}	#{tmpVpIntran}"
	end

	def reportSumTotal(dirPath)
		"#{dirPath}			#{@sentenceTotal}	#{@npTotal}	#{@vpTotal}	#{@vpDitran}	#{@vpIntran}"
	end

	# used for (S ...) (NP ...) and (VP ...)
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

	# Ditransitive Verb Phrase
	# exactly two immediate consituents of type NP
	# Do not count NPs that are marked as, for example, NP-SBJ. Dealing with nesting and making sure
	# that you only consider the immediate consituents will be tricky, especially if you are using RegEx
	def countNumDitran(res)
		dCount = 0

		if res == nil || res.length == 0
			return dCount
		end

		# make sure to not count, for example, NP-SBJ

		res.each do |constituent|
			# verify Ditransitive Verb Phrase
			# looking for exactly two immediate constituents of type NP
			if(constituent.has_children? && constituent.children.length == 2 && constituent.pos == @vp)
				if(constituent.children[0].pos == @np && constituent.children[1].pos == @np)
					dCount = dCount + 1
				end
			end

			dCount = dCount + countNumDitran(constituent.children)
		end

		return dCount
	end

	# Intransitive Verb Phrase
	# top-level constituents include no NPs (or no imeediate children at all)
	def countNumIntran(res)
		iCount = 0

		if res == nil || res.length == 0
			return iCount
		end

		res.each do |constituent|
			# verify Intransitive Verb Phrase
			if(constituent.pos == @vp)
				# (or no immediate children at all)
				if(constituent.children == nil || !constituent.has_children?)
					iCount = iCount + 1
				else
					# whose immediate (top-level) constituents include no NPs
					# containsNps = false
					# constituent.children.each do |child|
					# 	if(child.pos == @np)
					# 		containsNps = true
					# 		break
					# 	end
					# end

					# # no top-level constituents found
					# if(!containsNps)
					# 	iCount = iCount + 1
					# end
				end
			end

			iCount = iCount + countNumIntran(constituent.children)
		end
		return iCount
	end
end