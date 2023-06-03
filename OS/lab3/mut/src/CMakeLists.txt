
set(SOURCES dinner.cpp fork.cpp philosopher.cpp)
set(HEADERS dinner.h fork.h philosopher.h)

add_library(dinner_lib STATIC ${SOURCES} ${HEADERS})