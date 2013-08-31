// given a string and a trie, I want to make sure that 
// the trie exists within the string

var searchTrie = function(stringToSearch, foundTrieSoFar, currentNode, offset) {
	// we know we've reached a terminal node if 
	// the object has no children

	for(var i = 0; i < stringToSearch.length; i++) {
		var currentChar = stringToSearch[i].toUpperCase();
		
		// terminal state?
		if(isTerminalState(currentNode)) {
			return { "state":"success", "foundTrie": foundTrieSoFar, "offset": offset };
		}

		// we do not have a match
		if(currentNode[currentChar] === undefined) {
			return { "state":"fail" };
		}
		// we have a match
		else {
			foundTrieSoFar = foundTrieSoFar + currentChar;	
			currentNode = currentNode[currentChar];
		}
	}

	if(isTerminalState(currentNode)) {
		return { "state":"success", "foundTrie": foundTrieSoFar, "offset": offset };
	}

	return { "state":"mada", "currentNode": currentNode, "foundTrieSoFar": foundTrieSoFar, "offset": offset };
}

var isTerminalState = function(currentNode) {
		if(currentNode["A"] == undefined &&
			currentNode["G"] == undefined &&
			currentNode["C"] == undefined &&
			currentNode["T"] == undefined) {

			return true;
		}
		return false;
}

module.exports = searchTrie;