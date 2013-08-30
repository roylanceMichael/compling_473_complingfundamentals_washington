var fs = require('fs');

var rootTrie = { };
var debug = true;

/* CUSTOM FUNCTIONS */
var log = function(str){
	if(debug) {
		console.log(str);
	}
}

var doesATrieExistAtThisIndex(mainTrie, )

var processFile = function(fileLocation){
	// get file stream
	var fileStream = fs.createReadStream(fileLocation);

	var currentTrieNode = rootTrie;

	// log(JSON.stringify(rootTrie, null, "\t"));

	var offsetsFound = [];
	var foundSequence = "";
	var offsetOfCurrentDnaSequence = 0;
	var totalFileIdx = 0;

	fileStream.on('data', function(buffer){
		var bufferStr = buffer.toString();

		log("looking for " + bufferStr);

		for(var i = 0; i < bufferStr.length; i++){
			var currentChar = bufferStr[i];

			if(currentTrieNode[currentChar] != undefined) {
				
				log("found " + currentChar + ", moving on");
				foundSequence = foundSequence + currentChar;
				currentTrieNode = currentTrieNode[currentChar];
				totalFileIdx = totalFileIdx + 1;
			
			}
			else {
				log(currentChar + "-- A: " + 
					currentTrieNode["A"] + " - G: " + 
					currentTrieNode["G"] + " - C: " + 
					currentTrieNode["C"] + " - T: " + 
					currentTrieNode["T"]);
				
				// report if found a trie
				if(currentTrieNode["A"] == undefined &&
					currentTrieNode["G"] == undefined &&
					currentTrieNode["C"] == undefined &&
					currentTrieNode["T"] == undefined){

					log("found trie at " + offsetOfCurrentDnaSequence);
					offsetsFound.push({ "offset": offsetOfCurrentDnaSequence, "sequence": foundSequence });
				
				}
				else {
				
					log("didn't find a match");
				
				}

				totalFileIdx = totalFileIdx + 1;
				offsetOfCurrentDnaSequence = totalFileIdx;
				foundSequence = "";
				currentTrieNode = rootTrie;
			}
		}

		// report if found a trie
		if(currentTrieNode["A"] == undefined &&
			currentTrieNode["G"] == undefined &&
			currentTrieNode["C"] == undefined &&
			currentTrieNode["T"] == undefined){

			log("found trie at " + offsetOfCurrentDnaSequence);
			offsetsFound.push({ "offset": offsetOfCurrentDnaSequence, "sequence": foundSequence });
		
		}

	});

	fileStream.on('end', function(){
		for(var i = 0; i < offsetsFound.length; i++) {
			console.log("found at " + offsetsFound[i].offset + " : " + offsetsFound[i].sequence);
		}
		console.log("found " + offsetsFound.length + " total");
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

/* BEGIN TESTS */

/* END TESTS */

/* MAIN FUNCTIONS */
if(process.argv == undefined || process.argv.length < 3) {
	console.log("please include either 'test' to run tests or the location of the directory to test");
	return;
}

var argument = process.argv[2];

if(argument.toLowerCase() === "test"){
	console.log("processing tests...");
	debug = true;

	var anotherRootTrie = {};
	createRootTrie("CCNCT", anotherRootTrie);
	console.log(JSON.stringify(anotherRootTrie, null, "\t"));

}
else {
	var folderLocation = process.argv[2];
	var sevenTargetsFile = "../content/seven-targets.txt";
	var sevenTargets = fs.createReadStream(sevenTargetsFile);
	var dnaFile = /\.dna$/;

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