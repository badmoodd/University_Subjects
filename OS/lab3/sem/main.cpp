#include <iostream>

#include "dinner.h"

int main() {
  std::cout << "\e[1;32mAdding philosophers:\e[0m" << std::endl;

  addPhilosopher("Saruman");
  addPhilosopher("Gandalf");
  addPhilosopher("Radagast");
  addPhilosopher("Alatar");
  addPhilosopher("Pallando");

  std::cout << "\e[1;32mPhilosophers added. Starting dinner:\e[0m" << std::endl;

  startDinner();

  std::cout << "\e[1;32mDinner finished.\e[0m" << std::endl;
}
