#include <iostream>
#include <vector>
#include <fstream>
#include <sstream>
#include <string>
#include <chrono>
#include <map>

using namespace  std;

class NetworkScaler {
public:
    int numberOfMachines;
    const int initialMachines = 100;
    const double machineCost = 1.0;

    NetworkScaler() {
        numberOfMachines = initialMachines;
    }

    double calculateResponseTime(double workload) {
        if (numberOfMachines == 0) {
            return std::numeric_limits<double>::max();
        }
        return workload / numberOfMachines;
    }

    void consumeWorkLoad(int& workLoad) {
        workLoad -= numberOfMachines;
        if (workLoad < 0) {
            workLoad = 0;
        }
    }

    void setNumberOfMachines(int scaleFactor) {
        numberOfMachines += scaleFactor;
        if (numberOfMachines <= 50) {
            numberOfMachines = 50;
        }
    }

    double getTotalCost() {
        return numberOfMachines * machineCost;
    }
};




class ScalerAgentEnvironment {
public:
    std::vector<std::pair<std::string, long>> agentTimeList;
    std::vector<int> values;
    std::vector<int> arrivals;
    std::vector<long> executionTimes;
    int workLoad = 0;
    int arrivalTurns = 0;
    int totalMach = 0;
    int workloadTurnCounter = 0;
    double responseTime = 0.0;
    int currentIndex = -1;
    static int sumWorkload;
    NetworkScaler netsc;
    string workLoadStatus="none";
    string responseStatus="none";

    void loadValuesFromFile(const std::string& filePath) {
        std::ifstream file(filePath);
        if (!file) {
            std::cerr << "Error opening file: " << filePath << std::endl;
            return;
        }

        std::string line;
        while (std::getline(file, line)) {
            std::istringstream iss(line);
            int val1, val2;
            char comma;
            if (iss >> val1 >> comma >> val2) {
                values.push_back(val1);
                arrivals.push_back(val2);
            }
        }
    }

    std::pair<int, int> getNextValue() {
        currentIndex++;
        if (currentIndex < values.size()) {
            return {values[currentIndex], arrivals[currentIndex]};
        } else {
            std::exit(0);
            return {-1, -1};
        }
    }

    void getWorkLoadBool() {
        std::pair<int, int> arrivedData = getNextValue();
        int arrivedWorkload = arrivedData.first;
        int arrivedTurn = arrivedData.second;

        std::cout << "Arrived Workload: " << arrivedWorkload << endl;
        workLoad += arrivedWorkload;
        arrivalTurns = arrivedTurn;

        std::cout <<" Current Workload: " << workLoad << " Arrival Turns: " << arrivalTurns << std::endl;
    }

    void checkWorkLoadBool() {
        responseTime = netsc.calculateResponseTime(workLoad);

        if (workLoad <= 100) {
            workLoadStatus = "low";
        } else if (workLoad <= 400) {
            workLoadStatus = "medium";
        } else {
            workLoadStatus = "high";
        }


        if (responseTime <= 5.0) {
            responseStatus = "good";
        } else if (responseTime <= 10.0) {
            responseStatus = "ok";
        } else {
            responseStatus = "bad";
        }

        std::cout <<" Current Workload: " << workLoad << " Arrival Turns: " << arrivalTurns << std::endl;

        std::cout << "Workload: " << workLoadStatus << " Response Time: " << responseStatus << std::endl;
    }

    void consumeWorkLoad() {
        netsc.consumeWorkLoad(workLoad);
        workloadTurnCounter++;
        totalMach += netsc.numberOfMachines;
    }

    void scaleFactor(int scaleFactorParam) {
        std::cout << "Scale Factor: " << scaleFactorParam << std::endl;
        netsc.setNumberOfMachines(scaleFactorParam);
    }

    void init() {
        std::string filePath = "C:\\Users\\Burak\\CLionProjects\\NetworkScaler\\Workload_250-500_Iteration1.txt";
        loadValuesFromFile(filePath);

        // Calculate the sum of values
       // sumWorkload = 0;
        for (int value : values) {
            sumWorkload += value;
        }

        std::cout << "Environment initialized. Total workload sum: " << sumWorkload << std::endl;
    }





};




