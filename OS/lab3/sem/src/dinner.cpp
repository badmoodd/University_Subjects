#include "dinner.h"

static std::vector<Philosopher> philosophers;

void addPhilosopher(const std::string &name) {
  std::cout << "\e[1;32mAdding " << name << "\e[0m\n";
  philosophers.emplace_back(name);
}

void startMeal(Philosopher *ph, sem_t *forks) {
  int count = 0;
  while (count < 10) {
    ph->say("I want to eat");
    ph->takeForks(forks);
    ph->say("I take two forks and start eating!");
    ph->eat();
    ph->putForks(forks);
    ph->say("I put the forks back down and start thinking");
    ph->think();

    count++;
  }
  exit(0);
}

void initPhilosophers() {
  srand(time(nullptr));
  pid_t pid;

  sem_t *forks = sem_open("/forkstest", O_CREAT, 0600, 5);

  for (auto &ph : philosophers) {
    pid = fork();

    forks = sem_open("/forkstest", O_CREAT);

    if (pid == 0) {
      startMeal(&ph, forks);
    }
		

    int time = rand() % 3;
    sleep(time);
  }
  waitpid(pid, nullptr, 0);
}

void startDinner() {
  initPhilosophers();
}
