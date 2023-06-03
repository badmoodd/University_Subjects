#include <iostream>
#include <sstream>
#include <fstream>

using namespace std;

int main(){
  string argv; 
  getline(cin, argv);

  stringstream s(argv);
  string temp;
  ofstream file("4Output");
  while(s >> temp){
	file << temp <<endl;
	cout<<temp<<endl;
  }
  return 0;
}

