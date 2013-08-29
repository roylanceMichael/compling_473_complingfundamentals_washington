var fs = require('fs');

/* CUSTOM FUNCTIONS */
var processFile = function(fileLocation){
	// get file stream
	var fileStream = fs.createReadStream(fileLocation);

	var currentTrieNode = rootTrie;

	var offsetOfCurrentDnaSequence = 0;
	var totalFileIdx = 0;

	fileStream.on('data', function(buffer){
		var bufferStr = buffer.toString();

		console.log("looking for " + bufferStr);

		for(var i = 0; i < bufferStr.length; i++){
			var currentChar = bufferStr[i];

			if(currentTrieNode[currentChar] != undefined){
				console.log("found " + currentChar + ", moving on");
				currentTrieNode = currentTrieNode[currentChar];
				totalFileIdx = totalFileIdx + 1;
			}
			else {

				// report if found a trie
				if(currentTrieNode["A"] == undefined &&
					currentTrieNode["G"] == undefined &&
					currentTrieNode["C"] == undefined &&
					currentTrieNode["T"] == undefined){
					console.log("found trie at " + offsetOfCurrentDnaSequence);
				}
				else {
					console.log("didn't find a match");
				}

				totalFileIdx = totalFileIdx + 1;
				offsetOfCurrentDnaSequence = totalFileIdx;
				currentTrieNode = rootTrie;
			}
		}


	});

};

// create root trie
var createRootTrie = function(bufferStr, mainTrie) {
	var currentNode = mainTrie;

	for(var i = 0; i < bufferStr.length; i++){
		var currentChar = bufferStr[i].toUpperCase();

		if(!containsDnaSeq(currentChar)) {
			// console.log("replacing root trie, found " + currentChar);
			currentNode = mainTrie;
		}
		else if(currentNode[currentChar] === undefined){
			// console.log("did not find " + currentChar + " in object");
			var newObject = { };
			currentNode[currentChar] = newObject;
			currentNode = newObject;
		} else {
			// console.log("found char, placing as current iteration");
			currentNode = currentNode[currentChar];
		}
	}
}

// utilities
var containsDnaSeq = function(val){
	if( val == "A" || 
		val == "G" ||
		val == "C" || 
		val == "T") {
		return true;
	}

	return false;
};

/* END CUSTOM FUNCTIONS */

/* MAIN FUNCTIONS */
if(process.argv == undefined || process.argv.length < 3) {
	console.log("please include either 'test' to run tests or the location of the directory to test");
	return;
}

var argument = process.argv[2];

if(argument.toLowerCase() === "test"){
	console.log("processing tests...");
	
	var anotherRootTrie = {};
	createRootTrie("CCNCT", anotherRootTrie);
	console.log(JSON.stringify(anotherRootTrie, null, "\t"));

}
else {
	var rootTrie = { };
	var folderLocation = process.argv[2];
	var sevenTargetsFile = "../content/seven-targets.txt";
	var sevenTargets = fs.createReadStream(sevenTargetsFile);
	var dnaFile = /\.dna/;

	// load up the trie
	sevenTargets.on('data', function(buffer){
		var bufferStr = buffer.toString();
		createRootTrie(bufferStr, rootTrie);

		fs.readdir(folderLocation, function(err, files){
					for(var i = 0; i < files.length; i++){
						if(files[i].match(dnaFile)) {
							processFile(folderLocation + files[i]);
						}
					}
			});
	});
}

/* END MAIN FUNCTIONS */