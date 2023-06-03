#include "fork.h"

void Fork::unlock() { m.unlock(); }

bool Fork::try_lock() { return m.try_lock(); }
