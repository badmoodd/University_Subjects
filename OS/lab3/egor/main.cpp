#include <unistd.h>

#include <iostream>
#include <mutex>
#include <queue>
#include <thread>
#include <vector>

#include "functions.h"
#include "logger.h"
#include "logtime.h"
#include "point.h"
#include "signal.h"
#include "solver.h"

static std::queue<std::unique_ptr<Point> > queue;
static std::queue<std::pair<Signal, double> > signals;

static std::mutex s, q;

int main() {


  std::thread t1([]() { Solver solver(-5, 5, funct5, queue, q, s, signals); });
  std::thread t2([]() { Logger logger(queue, q, s, signals); });
  std::thread t3([]() { LogTime time(signals, s); });

  t1.join();
  // дописать синхр конца программы 
  t2.detach();
  t3.detach();
}