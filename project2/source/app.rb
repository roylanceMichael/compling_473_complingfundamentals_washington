if File.exists? './parser.rb'
	require './parser.rb'
else
	require './source/parser.rb'
end

class App
	@dirPath

	def initialize(dirPath)
		@dirPath = dirPath
	end

	def run

	end
end

# make sure we have 
if ARGV != nil && ARGV.length > 0
	path = ARGV[0]

	app = App.new(path)
	app.run
end