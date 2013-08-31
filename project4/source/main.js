var fs = require('fs');
var buildTrie = require('./buildTrie.js');
var searchTrie = require('./searchTrie.js');

/* MAIN FUNCTIONS */
if(process.argv == undefined || process.argv.length < 3) {
	console.log("please include the location of the directory to test");
	return;
}

var folderLocation = process.argv[2];
var filesToProcess = [];
var sevenTargetsFile = "../content/seven-targets.txt";
var sevenTargets = fs.createReadStream(sevenTargetsFile);
var dnaFile = /\.dna$/;

var rootTrie = {};
var currentNode = rootTrie;

// get all the files to process
fs.readdir(folderLocation, function(err, files) {
		for(var i = 0; i < files.length; i++){
			if(files[i].match(dnaFile)) {
				filesToProcess.push(folderLocation + "\\" + files[i]);
			}
		}
});

// load up the trie
sevenTargets.on('data', function(buffer) {
	var bufferStr = buffer.toString();
	var buildResult = buildTrie(bufferStr, currentNode, rootTrie);

	// update values
	rootTrie = buildResult.mainTrie;
	currentNode = buildResult.currentNode;
});

sevenTargets.on('end', function() {
	// we have our main trie now
	console.log("trie built, now searching " + filesToProcess.length + " file");

	for(var i = 0; i < filesToProcess.length; i++) {

		var fileStream = fs.createReadStream(filesToProcess[i]);
		var offset = 0;

		// will contain structure like this { "state":"mada", "currentNode": currentNode, "foundTrieSoFar": foundTrieSoFar }
		var inProgressSearches = [];
		var successfulSearches = [];

		fileStream.on('data', function(buffer) {

			var bufferStr = buffer.toString();

			// we need to process each inProgress
			var tmpArray = [];
			for(var j = 0; j < inProgressSearches.length; j++) {
				var searchResult = inProgressSearches[i];
				if(searchResult.state == "mada") {

					var newSearchResult = searchTrie(bufferStr, searchResult.foundTrieSoFar, searchResult.currentNode, searchResult.offset);

					if(newSearchResult.state == "mada") {
						tmpArray.push(newSearchResult);
					} 
					else if(newSearchResult.state == "success") {
						successfulSearches.push(newSearchResult);
					}
					// throw away failure
				}
			}

			inProgressSearches = [];

			// I want to get a substring at each iteration
			for(var j = 0; j < bufferStr.length; j++) {
				var bufferSubstr = bufferStr.substring(j);

				var searchResult = searchTrie(bufferSubstr, "", rootTrie, offset);

				if(searchResult.state == "success") {
					successfulSearches.push(searchResult);
				}
				else if(searchResult.state == "mada") {
					inProgressSearches.push(searchResult);
				}

				offset = offset + 1;
			}

		});

		fileStream.on('end', function() {
		console.log("found the end with " + successfulSearches.length + " succeses");
			for(var i = 0; i < successfulSearches.length; i++) {
				console.log(successfulSearches[i].foundTrie + "\t" + successfulSearches[i].offset);
			}
		});
	}
});