cmake_minimum_required(VERSION 3.30)
project(NetworkScaler)

set(CMAKE_CXX_STANDARD 20)

add_executable(NetworkScaler main.cpp
        ScalerAgentEnvironment.c++
        ScalerAgentEnvironment.hpp)
