//
// Created by Burak on 11/05/2025.
//


#ifndef AgentPLFeatures_HPP
#define AgentPLFeatures_HPP
using namespace std;
class SampleData {
public:
    int id;
    float r, g, b;
    int number;
    std::string color;

    SampleData(int id, float r, float g, float b, int number, const std::string &color)
        : id(id), r(r), g(g), b(b), number(number), color(color) {}

    void print() const {
        std::cout << "SampleData{id=" << id << ", r=" << r << ", g=" << g << ", b=" << b
                  << ", number=" << number << ", color='" << color << "'}\n";
    }
};

class AgentPLFeatures {
public:

    static  int productCounter;
    static std::string redVal;
    static std::string greenVal;
    static std::string blueVal;
    static std::vector<SampleData> dataList;

        static void boolColourSensor();
        static void parseDataFile();
        static void boolGroup(float red, float green, float blue);
};








#endif //AGENTPLFEATURES_HPP
