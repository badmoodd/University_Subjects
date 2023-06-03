#include <unistd.h>
#include <sys/wait.h>
#include <iostream>
int main (){
  int pid;
  int status;
  std::cout<<"Child: "<< getpid()<<"\t"<<"Parent: "<< getppid()<<std::endl;
  for (int i = 0; i < 3; ++i) {
    pid = fork();
    if (pid == 0){
      std::cout<<"Child: "<< getpid()<<"\t"<<"Parent: "<< getppid()<<std::endl;
      if(i == 0){
        int fifth = fork();
        if(fifth == 0) {
          std::cout << "\tChild: " << getpid() << "\t" << "Parent: " << getppid() << std::endl;
          int sixth = fork();
          if(sixth == 0){
            std::cout << "\t\tChild: " << getpid() << "\t" << "Parent: " << getppid() << std::endl;
            exit(1);
          }
          waitpid(sixth, &status, 0);
          exit(1);
        }
        waitpid(fifth, &status, 0);
      }
      if ( i == 1){
        int seventh = fork();
        if(seventh == 0){
          std::cout << "\tChild: " << getpid() << "\t" << "Parent: " << getppid() << std::endl;
          exit(1);
        }
        waitpid(seventh, &status, 0);
      }

      if ( i == 2){
        system("whoami");
      }
      exit(1);
    }
    waitpid(pid, &status, 0);
  }
  return 0;
}