#ifndef DINNER_H
#define DINNER_H

#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>

#include <iostream>
#include <string>
#include <vector>

#include "philosopher.h"

void addPhilosopher(const std::string& name);

void startDinner();

#endif
