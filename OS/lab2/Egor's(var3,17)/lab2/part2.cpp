#include <sys/wait.h>
#include <unistd.h>

#include <iostream>

const int WAITTIME = 20;

int main() {
  int pid;

  std::cout << "id: " << getpid() << "\t"
            << "Parent: " << getppid() << std::endl;

  for (int num = 1; num <= 2; num++) {
    pid = fork();

    if (pid == 0) {
      std::cout << "\t id: " << getpid() << "\t"
                << "Parent: " << getppid() << std::endl;

      if (num == 1) {
        for (int j = 0; j < 2; j++) {
          pid = fork();

          if (pid == 0) {
            std::cout << "\t\tid: " << getpid() << "\t"
                      << "Parent: " << getppid() << std::endl;

            sleep(WAITTIME);
            exit(0);
          }
        }
        sleep(WAITTIME);
        exit(0);
      }

      if (num == 2) {
       
        system("ps");

        for (int j = 0; j < 2; j++) {
          pid = fork();
          if (pid == 0) {
            std::cout << "\t\tid: " << getpid() << "\t"
                      << "Parent: " << getppid() << std::endl;
            sleep(WAITTIME);
            exit(0);
          }
        }
        sleep(WAITTIME);
        exit(0);
      }
    }
  }

  sleep(WAITTIME);
  return 0;
}