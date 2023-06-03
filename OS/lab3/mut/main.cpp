#include <iostream>

#include "dinner.h"

int main() {
  std::cout << "\e[1;32mAdding philosophers:\e[0m" << std::endl;

  addPhilosopher("Socrates");
  addPhilosopher("Plato");
  addPhilosopher("Aristotle");
  addPhilosopher("Nietzsche");
  addPhilosopher("Confucius");

  std::cout << "\e[1;32mPhilosophers added. Starting dinner:\e[0m" << std::endl;

  startDinner(20, 5);

  std::cout << "\e[1;32mDinner finished.\e[0m" << std::endl;

  return 0;
}