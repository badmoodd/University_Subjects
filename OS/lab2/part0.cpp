#include <iostream>
#include <unistd.h>

using namespace std;

int main() {

 cout << "parent pid: " << getpid() << endl;

 for (int i=0; i<2; i++) {
  
  pid_t pid = fork();
  sleep(1);
  if (pid == 0) {
   cout << "child pid: " << getpid() << endl;
   system("time");
   cout << endl;
   exit(0);
  }
 }

 return 0;
}