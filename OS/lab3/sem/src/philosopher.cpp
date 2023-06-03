#include "philosopher.h"

void Philosopher::say(std::string message) {
  std::cout << this->name << ": " << message << std::endl;
}

void Philosopher::eat() {
  int eating_time = 2 + rand() % 5;
  sleep(eating_time);
}

void Philosopher::think() {
  int thinking_time = 2 + rand() % 5;
  sleep(thinking_time);
}

void Philosopher::takeForks(sem_t *forks) {
  if (sem_wait(forks) < 0)
    std::cout << "takeForks  : [sem_wait] Failed" << std::endl;
  if (sem_wait(forks) < 0)
    std::cout << "takeForks  : [sem_wait] Failed" << std::endl;
}

void Philosopher::putForks(sem_t *forks) {
  if (sem_post(forks) < 0)
    std::cout << "putForks  : [sem_wait] Failed" << std::endl;
  if (sem_post(forks) < 0)
    std::cout << "putForks  : [sem_wait] Failed" << std::endl;
}