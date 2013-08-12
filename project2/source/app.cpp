#include <iostream>
#include <fstream>
#include <string>
#include "parse.cpp"

using namespace std;

int main() {
	Parse * parse = new Parse("./content/20000924_NYT");

	delete parse;

	return 0;
}
