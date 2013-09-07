var fs = require('fs');

var lmFile = /lm$/;

var folderLocation = "../content/"

// get all the files to process
fs.readdir(folderLocation, function(err, files) {
	for(var i = 0; i < files.length; i++){
		if(files[i].match(lmFile)) {
			console.log(files[i]);
		}
	}
});