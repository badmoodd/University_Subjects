#pragma once

#ifndef FORK_H
#define FORK_H

#include <mutex>

class Fork {
 public:
  void unlock();
  bool try_lock();
  std::string belong;

 private:
  std::mutex m;
};

#endif