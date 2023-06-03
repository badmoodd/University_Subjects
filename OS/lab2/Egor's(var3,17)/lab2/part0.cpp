#include <unistd.h>

#include <iostream>
#include <string>

using namespace std;

int main() {
  cout << "parent pid: " << getpid() << endl;

  for (int i = 0; i < 2; i++) {
    pid_t pid = fork();

    if (pid == 0) {
      std::cout << "\t id: " << getpid() << "\t"
                << "Parent: " << getppid() << std::endl;
      system("date +\"%T\"");
      cout << endl;
      sleep(2);
      exit(0);
    }
  }
  

  system("ps -x");

  cout << "Parent id:" << endl;

  string a = "ps -x | grep " + to_string(getpid()) + " | grep -v grep";
  system(a.c_str());

  return 0;
}