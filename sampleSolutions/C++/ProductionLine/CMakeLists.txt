cmake_minimum_required(VERSION 3.30)
project(ProductionLine)

set(CMAKE_CXX_STANDARD 20)

add_executable(ProductionLine
        SortAgent.c++
        BuildAgent.c++
        PushAgent.c++
        AgentPLFeatures.hpp
        AgentPLFeatures.c++
        BuildAgent.hpp
        PushAgent.hpp

)
