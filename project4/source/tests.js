var buildTrie = require('./buildTrie.js');
var searchTrie = require('./searchTrie.js');

var correctTrieWhenGivenACGT = function() {
	// arrange
	var mainTrie = { };
	var currentNode = mainTrie;

	// act
	var newTrie = buildTrie("ACGT", currentNode, mainTrie);
	
	// assert
	var result = true;
	
	result = newTrie.mainTrie["A"]["C"]["G"]["T"] != undefined;
	console.log(result + " -> correctTrieWhenGivenACGT");
}

var correctTrieWhenGivenACNGT = function() {
	// arrange
	var mainTrie = { };
	var currentNode = mainTrie;

	// act
	var newTrie = buildTrie("ACNGT", currentNode, mainTrie);
	
	// assert
	var result = true;
	
	result = newTrie.mainTrie["A"]["C"] != undefined;
	result = newTrie.mainTrie["G"]["T"] != undefined;
	console.log(result + " -> correctTrieWhenGivenACNGT");
}

var correctTrieWhenGivenTwoBuffers = function() {
	// arrange
	var mainTrie = { };
	var currentNode = mainTrie;

	// act
	var newTrie = buildTrie("AC", currentNode, mainTrie);
	var anotherNewTrie = buildTrie("GT", newTrie.currentNode, newTrie.mainTrie);

	// assert
	var result = true;
	
	result = anotherNewTrie.mainTrie["A"]["C"]["G"]["T"] != undefined;
	console.log(result + " -> correctTrieWhenGivenTwoBuffers");
}

var correctSubsetIdentifiedWhenGivenTrie = function() {
	// arrange
	var mainTrie = { };
	var currentNode = mainTrie;
	var newTrie = buildTrie("ACGT", currentNode, mainTrie);

	// act
	var searchResult = searchTrie("ACGT", "", newTrie.mainTrie);

	// assert
	var result = true;
	
	result = searchResult.foundTrie == "ACGT" && searchResult.state == "success";
	console.log(result + " -> correctSubsetIdentifiedWhenGivenTrie");
}

var correctSubsetIdentifiedWhenGivenTrieDoesNotComplete = function() {
	// arrange
	var mainTrie = { };
	var currentNode = mainTrie;
	var newTrie = buildTrie("ACGT", currentNode, mainTrie);

	// act
	var searchResult = searchTrie("AC", "", newTrie.mainTrie);

	// assert
	var result = true;
	
	result = searchResult.foundTrieSoFar == "AC" && searchResult.state == "mada";
	console.log(result + " -> correctSubsetIdentifiedWhenGivenTrieDoesNotComplete");
}

var correctSubsetIdentifiedWhenBufferSplit = function() {
	// arrange
	var mainTrie = { };
	var currentNode = mainTrie;
	var newTrie = buildTrie("ACGT", currentNode, mainTrie);

	// act
	var searchResult = searchTrie("AC", "", newTrie.mainTrie);
	var anotherSearchResult = searchTrie("GT", searchResult.foundTrieSoFar, searchResult.currentNode);

	// assert
	var result = true;
	
	result = anotherSearchResult.foundTrie == "ACGT" && anotherSearchResult.state == "success";
	console.log(result + " -> correctSubsetIdentifiedWhenBufferSplit");
}

var failReturnedWhenBufferSplitAndDoesNotContainTrie = function() {
	// arrange
	var mainTrie = { };
	var currentNode = mainTrie;
	var newTrie = buildTrie("ACGT", currentNode, mainTrie);

	// act
	var searchResult = searchTrie("AC", "", newTrie.mainTrie);
	var anotherSearchResult = searchTrie("GG", searchResult.foundTrieSoFar, searchResult.currentNode);

	// assert
	var result = true;
	
	result = anotherSearchResult.state == "fail";
	console.log(result + " -> failReturnedWhenBufferSplitAndDoesNotContainTrie");
}



correctTrieWhenGivenACGT();
correctTrieWhenGivenACNGT();
correctTrieWhenGivenTwoBuffers();
correctSubsetIdentifiedWhenGivenTrie();
correctSubsetIdentifiedWhenGivenTrieDoesNotComplete();
correctSubsetIdentifiedWhenBufferSplit();
failReturnedWhenBufferSplitAndDoesNotContainTrie();