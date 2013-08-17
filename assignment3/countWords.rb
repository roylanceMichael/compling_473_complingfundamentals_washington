class Bigram
	attr_accessor :pos, :word

	def initialize(pos, word)
		@pos = pos
		@word = word
	end
end

bigrams = []

oldMan = File.new("old-man.txt", "r")
while (line = oldMan.gets)
	res = line.split(/\s+/)

	bigrams.push(Bigram.new(res[0], res[1]))
end
oldMan.close

bigramCount = bigrams.length - 1

puts "total bigrams are: #{bigramCount}"

posHash = { }

for i in 1...(bigrams.length)
	uniqueStr = "#{bigrams[i-1].pos}~#{bigrams[i].pos}"

	if(posHash.has_key?(uniqueStr))
		posHash[uniqueStr] = posHash[uniqueStr] + 1
	else
		posHash[uniqueStr] = 1
	end
end

puts "total bigrams on pos are #{posHash.length}"

wordHash = { }

for i in 1...(bigrams.length)
	uniqueStr = "#{bigrams[i-1].word}~#{bigrams[i].word}"

	if(wordHash.has_key?(uniqueStr))
		wordHash[uniqueStr] = wordHash[uniqueStr] + 1
	else
		wordHash[uniqueStr] = 1
	end
end

puts "total bigrams on word are #{wordHash.length}"

nnCount = 0
bigrams.each do |bigram|
	if(bigram.pos == "NN")
		nnCount = nnCount + 1
	end
end

p_nn = posHash["NN~."]
puts "P(.|NN) = #{p_nn} / #{nnCount}"

puts "P(DT JJ) = #{posHash['DT~JJ']} / #{bigramCount}"

trigramHash = { }

for i in 2...(bigrams.length)
	uniqueStr = "#{bigrams[i-2].pos}~#{bigrams[i-1].pos}~#{bigrams[i].pos}"

	if(trigramHash.has_key?(uniqueStr))
		trigramHash[uniqueStr] = trigramHash[uniqueStr] + 1
	else
		trigramHash[uniqueStr] = 1
	end
end

puts "total trigrams on word are #{trigramHash.length}"

p_nn_dt_jj = trigramHash["DT~JJ~NN"]

p_dt_jj = 0

trigramHash.each do |k,v|
	strSplit = k.split("~")
	if(strSplit[0] == "DT" && strSplit[1] == "JJ")
		p_dt_jj = p_dt_jj + v
	end
end

puts "p_nn_dt_jj = #{p_nn_dt_jj} and total is #{p_dt_jj}"

# find probability of nn
nnTotal = 0
dt_jj_given_nn = 0
nn_given_dt_jj = 0

trigramHash.each do |k,v|
	strSplit = k.split("~")
	if(strSplit[2] == "NN")
		#puts "#{k} = #{v}"
		nnTotal = nnTotal + v
	end

	if(strSplit[0] == "NN")
		puts "#{k} = #{v}"
	end

	if(strSplit[0] == "DT" && strSplit[1] == "JJ" && strSplit[2] == "NN")
		dt_jj_given_nn = dt_jj_given_nn + 1
	end

	if(strSplit[0] == "NN" && strSplit[1] == "DT" && strSplit[2] == "JJ")
		nn_given_dt_jj = nn_given_dt_jj + 1
	end
end

puts "total NN found in trigram: #{nnTotal}, dt_jj_given_nn #{dt_jj_given_nn} nn_given_dt_jj #{nn_given_dt_jj}"


