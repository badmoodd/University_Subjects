
set(SOURCES dinner.cpp philosopher.cpp)
set(HEADERS dinner.h philosopher.h)

add_library(dinner_lib STATIC ${SOURCES} ${HEADERS})