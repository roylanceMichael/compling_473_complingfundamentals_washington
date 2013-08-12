#include <iostream>
#include <fstream>
#include <string>

using namespace std;

class Parse {
	string fileLocation;
	public:
	 Parse (string);
	 
	 void BuildHash();
};

Parse::Parse(string fLoc) {
	fileLocation = fLoc;
}

void Parse::BuildHash() {
	string line;

	ifstream myfile ("./content/20000924_NYT");

	if (myfile.is_open()) {
		while (myfile.good()){
			getline (myfile, line);
			cout << line << endl;
		}
		myfile.close();
	}
	else {
		cout << "Unable to open file";
	}
}

