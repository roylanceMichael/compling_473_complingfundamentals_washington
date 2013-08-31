var fs = require('fs');
var buildTrie = require('./buildTrie.js');
var searchTrie = require('./searchTrie.js');

/* MAIN FUNCTIONS */
if(process.argv == undefined || process.argv.length < 3) {
	console.log("please include the location of the directory to test");
	return;
}

var print = function(val) {
	if(debug) {
		console.log(val);
	}
}

// local variables
var debug = false;
var folderLocation = process.argv[2];
var filesToProcess = [];
var targetsFile = "../content/seven-targets.txt";
var trieStream = fs.createReadStream(targetsFile);
var dnaFile = /\.dna$/;
var rootTrie = {};
var currentNode = rootTrie;
// this will tell us how far in advance we need to search
var maximumTrieSize = 0;

// get all the files to process
fs.readdir(folderLocation, function(err, files) {
		for(var i = 0; i < files.length; i++){
			if(files[i].match(dnaFile)) {
				filesToProcess.push(folderLocation + files[i]);
			}
		}
});

// load up the trie
trieStream.on('data', function(buffer) {
	var bufferStr = buffer.toString();
	
	// get the maximum size allowable
	maximumTrieSize = maximumTrieSize + bufferStr.length;

	var buildResult = buildTrie(bufferStr, currentNode, rootTrie);

	// update values
	rootTrie = buildResult.mainTrie;
	currentNode = buildResult.currentNode;
});

// after the trie has been loaded, process each file
trieStream.on('end', function() {
	// we have our main trie now
	print("trie built, now searching " + filesToProcess.length + " file(s) with a maxium trie size of " + maximumTrieSize);

	for(var i = 0; i < filesToProcess.length; i++) {
		processFile(filesToProcess[i]);
	}
});

var processFile = function(fileName) {

	var fileStream = fs.createReadStream(fileName);
	var offset = 0;

	var currentWorkSpace = "";
	var successfulSearches = [];

	fileStream.on('data', function(buffer) {

		print("working on offset# " + offset);

		var remainderBuffer = buffer.toString();
		var bufferStr = currentWorkSpace + remainderBuffer.substring(0, remainderBuffer.length - maximumTrieSize);

		// I want to get a substring at each iteration
		for(var j = 0; j < bufferStr.length; j++) {
			var bufferSubstr = bufferStr.substring(j);

			var searchResult = searchTrie(bufferSubstr, "", rootTrie, offset);
			
			if(searchResult.state == "success") {
				print(searchResult);
				successfulSearches.push([searchResult.offset, searchResult.foundTrie]);
			}

			offset = offset + 1;
		}

		currentWorkSpace = remainderBuffer.substring(remainderBuffer.length - maximumTrieSize);
	});

	fileStream.on('end', function() {
		
		for(var i = 0; i < currentWorkSpace.length; i++) {
			var subStr = currentWorkSpace.substring(i);
			var searchResult = searchTrie(subStr, "", rootTrie, offset);
			if(searchResult.state == "success") {
				print(searchResult);
				successfulSearches.push([searchResult.offset, searchResult.foundTrie]);
			}
		}

		print("found the end with " + successfulSearches.length + " successful searches");

		console.log(fileName);
		for(var i = 0; i < successfulSearches.length; i++) {
			console.log("\t" + successfulSearches[i][0] + "\t" + successfulSearches[i][1]);
		}
	});
}