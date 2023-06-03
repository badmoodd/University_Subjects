#include "philosopher.h"

Philosopher::Philosopher(std::string name) : name(std::move(name)) {
  srand(time(nullptr));
}

void Philosopher::say(std::string message) {
  log_mtx.lock();
  std::cout << name << ": " << message << std::endl;
  log_mtx.unlock();
}

void Philosopher::think() { sleep(3 + rand() % 5); }

void Philosopher::eat() { sleep(3 + rand() % 5); }

std::string Philosopher::getName() { return name; }

void Philosopher::acquireForks(std::vector<Fork*> forks) {
  int count = 0;
  while (true) {
    for (auto& fork : forks) {
      if (fork->try_lock()) count++;

      if (count == 1)
        leftFork = fork;
      else if (count == 2) {
        rightFork = fork;
        return;
      }
    }
    std::this_thread::sleep_for(interval);
  }
}

void Philosopher::releaseForks() {
  leftFork->unlock();
  rightFork->unlock();

  leftFork = nullptr;
  rightFork = nullptr;
}