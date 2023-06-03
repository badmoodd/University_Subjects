#ifndef PHILOSOPHER_H
#define PHILOSOPHER_H

#include <stdlib.h>
#include <unistd.h>

#include <ctime>
#include <iostream>
#include <mutex>
#include <string>
#include <thread>
#include <vector>

#include "fork.h"

static std::mutex log_mtx;
static std::chrono::milliseconds interval(100);

class Philosopher {
 public:
  Philosopher(std::string name);

  std::string getName();

  void say(std::string message);
  void eat();
  void think();

  void acquireForks(std::vector<Fork*> forks);
  void releaseForks();

 private:
  std::string name;

  Fork* leftFork;
  Fork* rightFork;
};

#endif