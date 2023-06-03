#include "dinner.h"

static std::vector<Fork *> forks;
static std::vector<std::thread> threads;
static std::vector<Philosopher> philosophers;


void initForks(int forksCount);
void initPhilosophers();
void deinitPhilosophers();
void startMeal(Philosopher *philosopher);
void addPhilosopher(const std::string &name);
void waitUntilDinnerEnd(time_t dinningTime);


void startDinner(time_t dinningTime, int forksCount) {
  initForks(forksCount);
  initPhilosophers();

  waitUntilDinnerEnd(dinningTime);
  sleep(dinningTime);

  deinitPhilosophers();
}

void initForks(int forksCount) {
  for (int i = 0; i < forksCount; i++) {
    Fork *fork = new Fork();
    fork->belong = "init";
    forks.push_back(fork);
  }
}

void initPhilosophers() {
  for (auto &ph : philosophers) {
    threads.push_back(std::thread(startMeal, &ph));
  }
}

void deinitPhilosophers() {
  for (std::thread &th : threads) {
    th.detach();
  }
}

void startMeal(Philosopher *philosopher) {
  while (1) {
    philosopher->think();
    philosopher->say("I want to eat");
    philosopher->acquireForks(forks);
    philosopher->say("I take two forks and start eating!");
    philosopher->eat();
    philosopher->releaseForks();
    philosopher->say("I put the forks back down and start thinking");
  }
}

void addPhilosopher(const std::string &name) {
  std::cout << "\e[1;32mAdding " << name << "\e[0m\n";
  philosophers.emplace_back(name);
}

void waitUntilDinnerEnd(time_t dinningTime) { sleep(dinningTime); }