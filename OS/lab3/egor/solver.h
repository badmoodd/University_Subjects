#include <queue>
#include "signal.h"
#include <thread>



class Solver {
 public:
  Solver(double start, double end, double (*func)(double x),
         std::queue<std::unique_ptr<Point> > &queue, std::mutex &q, std::mutex &s, std::queue<std::pair<Signal, double> > &signals)
      : start_X(start), end_X(end), func(func), queue(queue), q(q), s(s), signals(signals) {
    startSolve();
  }

 private:
  void startSolve() {
    for (int x = start_X; x <= end_X; x++) {
      
      double y = func(x);
      sleep(1);
      

      q.lock();
      queue.push(std::make_unique<Point>(Point(x, y)));
      q.unlock();

      s.lock();
      signals.push(std::make_pair(Signal::RECEIVED, y));
      s.unlock();
    }
  }

  std::queue<std::pair<Signal, double> > &signals;
  std::mutex &q;
  std::mutex &s;
  std::queue<std::unique_ptr<Point> > &queue;
  double start_X;
  double end_X;
  double (*func)(double x);
};