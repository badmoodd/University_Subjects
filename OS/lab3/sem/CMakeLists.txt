cmake_minimum_required(VERSION 3.22)

project(dining_philosophers)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)


add_executable(${PROJECT_NAME} main.cpp)

add_subdirectory(src)
include_directories(src)

target_link_libraries (${PROJECT_NAME} dinner_lib)
