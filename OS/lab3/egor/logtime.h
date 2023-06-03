#include <queue>
#include <iostream>
#include <ctime>
#include <fstream>
#include <string>

#include "signal.h"

class LogTime {
 public:
  LogTime(std::queue<std::pair<Signal, double> > &signals, std::mutex &s) : signals(signals), s(s) { startWrite(); }

 private:
  void startWrite() {
    std::ofstream f("/Users/egorvasilkov/storage/OS/lab3/egor/logtime", std::ios::app);
    while (true) { // true !signals.empty()
      if (!signals.empty()) {
        s.lock();
   
        f << "[logger time]  [" << (signals.back().first == Signal::RECEIVED ? "RECIEVED" : "RECORDED") << "] -> " << signals.back().second << " |  time:" << std::time(nullptr) << std::endl;
        signals.pop();
        s.unlock();
      }
    }
    f.close();
  }
  
  std::mutex &s;
  std::queue<std::pair<Signal, double> > &signals;
};
