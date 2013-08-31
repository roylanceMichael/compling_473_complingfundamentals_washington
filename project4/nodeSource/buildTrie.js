// I want to be given a string and return a trie object
// example: ACG
// { A: { C: { G: {}}}}

// we assume that the user has set up the currentNode and mainTrie correctly here
var createTrie = function(bufferStr, currentNode, mainTrie) {
	for(var i = 0; i < bufferStr.length; i++) {

		var currentChar = bufferStr[i].toUpperCase();

		if(!containsDnaSeq(currentChar)) {
			currentNode = mainTrie;
		}
		else if(currentNode[currentChar] === undefined) {
			var newObject = { };
			currentNode[currentChar] = newObject;
			currentNode = newObject;
		}
		else {
			currentNode = currentNode[currentChar];
		}
	}

	return { "mainTrie": mainTrie, "currentNode": currentNode };
}

var containsDnaSeq = function(val){
	if( val == "A" || 
		val == "G" ||
		val == "C" || 
		val == "T") {
		return true;
	}

	return false;
};

module.exports = createTrie;