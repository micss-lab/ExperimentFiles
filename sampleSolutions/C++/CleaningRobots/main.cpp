#include <iostream>
#include <vector>
#include <thread>
#include <chrono>
#include <iomanip>
#include <cstdlib>
#include <cmath>  // For exp()
#include <algorithm>

#include <fstream>



#ifdef _WIN32
#include <windows.h>

class Grid;

void enableANSI() {
    HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);
    DWORD mode;
    GetConsoleMode(hOut, &mode);
    SetConsoleMode(hOut, mode | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
}
#else
#include <cstdlib>
#endif

using namespace std;



// BatteryModel class definition
class BatteryModel {
private:
    double capacity;  // Battery capacity in the range of 0-100
    double k;         // Sensitivity factor for action effect

public:
    BatteryModel(double initialCapacity, double k) : capacity(initialCapacity), k(k) {}

    // Simulate the battery decrease based on an action value
    bool performAction(double actionValue, double timeInterval) {
        // Calculate the battery decrease based on the exponential decay model
        double decreaseFactor = exp(-k * actionValue * timeInterval);
        capacity *= decreaseFactor;

        cout << "Battery Capacity: " << capacity << endl;
        if (capacity < 0) {
            capacity = 0;
            return false;
        }
        return true;
    }

    double getCapacity() const {
        return capacity;
    }
};

// VacuumBag class definition
class VacuumBag {
private:
    double capacity;        // Maximum capacity of the vacuum bag
    string agentName;       // Name of the agent using the vacuum bag
    double remainingCAP;    // Remaining load in the vacuum bag

public:
    VacuumBag(double d, const string& agentName) : capacity(d), agentName(agentName), remainingCAP(d) {}

    // Method to add items to the vacuum bag
    bool addItems(double amount) {
        if (remainingCAP - amount >= 0) {
            remainingCAP -= amount;
            cout << "Vacuumed " << amount << " items. Remaining load: " << remainingCAP << "." << endl;
            return true;
        } else {
            return false;
        }
    }

    // Method to get details of the vacuum bag
    string getDetails() const {
        return "Agent Name: " + agentName + ", Capacity: " + to_string(capacity) + ", Current Load: " + to_string(remainingCAP);
    }

    // Method to check if the vacuum bag is full
    bool isFull() const {
        return remainingCAP == 0;
    }

    // Method to get the remaining capacity of the vacuum bag
    double getRemainingCapacity() const {
        return remainingCAP;
    }
};

// Agent class definition
class Grid;  // Forward declaration of Grid class

class Agent {
private:
    std::chrono::steady_clock::time_point startTime;

public:
    string name;
    int x, y, width, height;
    void startTimer() {
        startTime = std::chrono::steady_clock::now();
    }







    // New attributes
    string batteryPower = "full";  // Assume initially full
    string vacuumBag = "empty";    // Assume initially empty
    string dirtIntensity="none";
    BatteryModel battery;          // Battery object
    VacuumBag vacuumBagObj;        // VacuumBag object
    Grid* grid;                    // Reference to the Grid object
    boolean continue_signal=false;

    void writeExecutionLog(int planNumber, auto endTime, const Agent& ag) {
        //   std::this_thread::sleep_for(std::chrono::milliseconds(1));




        std::cout << ag.name << std::endl;

        if (ag.name == "R1" || ag.name == "R3") {
            auto end = std::chrono::steady_clock::now();
            auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - ag.startTime);


            std::string path = "C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\"
                               "WCET\\Yeni Olcumler\\CleaningRobots\\NoGUI\\PC\\Cpp\\output" + ag.name + ".txt";

            std::ofstream writer(path, std::ios::app);
            if (writer.is_open()) {
                writer << "PLANN " << planNumber << " | Execution time (ms): " << elapsed << "\n";
                writer.close();
            } else {
                std::cerr << "Unable to open file: " << path << std::endl;
            }
        }}


    Agent(string name, int x, int y, int width, int height, double batteryCapacity, double batterySensitivity, double vacuumCapacity, Grid* grid)
       : name(name), x(x), y(y), width(width), height(height), battery(batteryCapacity, batterySensitivity), vacuumBagObj(vacuumCapacity, name), grid(grid) {}
    void nextSlot() {
        x++;
        if (x == width) {
            x = 0;
            y++;
        }
    }

    void nextSlotRev() {
        x--;
        if (x == -1) {
            x = width - 1;
            y--;
        }
    }
    float returnIntensity(int x, int y);

    void setIntensity(int x, int y, float value);


    void displayPosition() const {
        cout << name << " is at (" << x << ", " << y << ")\n";
    }



    // Method to arrange the vacuum power

    void arrangeVacuumPower27(Agent agName) {

        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();



        cout << "VACUUUUUUM POWER" << endl;

        cout << " Battery Power: "<<this->batteryPower <<" Vacuum Bag: " << vacuumBag<< " Dirt Intensity:" << dirtIntensity << endl;

        if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
    }

     void arrangeVacuumPower81(Agent agName) {

        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();



        cout << "VACUUUUUUM POWER" << endl;

        cout << " Battery Power: "<<this->batteryPower <<" Vacuum Bag: " << vacuumBag<< " Dirt Intensity:" << dirtIntensity << endl;

       if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }  else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }  else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
    }

     void arrangeVacuumPower270(Agent agName) {

        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();



        cout << "VACUUUUUUM POWER" << endl;

        cout << " Battery Power: "<<this->batteryPower <<" Vacuum Bag: " << vacuumBag<< " Dirt Intensity:" << dirtIntensity << endl;

       if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }  else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }  else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
         else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
         else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
         else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
         else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
         else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
         else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
         else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid"  && false) {
            cout << "RULE 2" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max"  && false) {
            cout << "RULE 3" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 4" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 5" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 6" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 7" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 8" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 9" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 10" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 11" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 12" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 13" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 14" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 15" << endl;
            burnGarb(90, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 19" << endl;
            burnGarb(50, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
            cout << "RULE 20" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
            cout << "RULE 21" << endl;
            burnGarb(90, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
            cout << "RULE 22" << endl;
            burnGarb(50, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
            cout << "RULE 23" << endl;
            burnGarb(100, 1);


            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
            cout << "RULE 24" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 25" << endl;
            burnGarb(70, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 26" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
            cout << "RULE 27" << endl;
            burnGarb(100, 1);

            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        }
    }


    void arrangeVacuumPower1_27(Agent agName) {
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();


        cout << "VACUUUUUUM POWER" << endl;

        if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" || true) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);
            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

            if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid" || true) {
                cout << "RULE 2" << endl;
                burnGarb(70, 1);
                planCounter++;
                end = std::chrono::steady_clock::now();
                writeExecutionLog(planCounter, end, agName);

                if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max" || true) {
                    cout << "RULE 3" << endl;
                    burnGarb(90, 1);
                    planCounter++;
                    end = std::chrono::steady_clock::now();
                    writeExecutionLog(planCounter, end, agName);

                    if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" || true) {
                        cout << "RULE 4" << endl;
                        burnGarb(50, 1);
                        planCounter++;
                        end = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter, end, agName);

                        if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" || true) {
                            cout << "RULE 5" << endl;
                            burnGarb(70, 1);
                            planCounter++;
                            end = std::chrono::steady_clock::now();
                            writeExecutionLog(planCounter, end, agName);

                            if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" || true) {
                                cout << "RULE 6" << endl;
                                burnGarb(90, 1);
                                planCounter++;
                                end = std::chrono::steady_clock::now();
                                writeExecutionLog(planCounter, end, agName);

                                if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" || true) {
                                    cout << "RULE 7" << endl;
                                    burnGarb(50, 1);
                                    planCounter++;
                                    end = std::chrono::steady_clock::now();
                                    writeExecutionLog(planCounter, end, agName);

                                    if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" || true) {
                                        cout << "RULE 8" << endl;
                                        burnGarb(70, 1);
                                        planCounter++;
                                        end = std::chrono::steady_clock::now();
                                        writeExecutionLog(planCounter, end, agName);

                                        if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" || true) {
                                            cout << "RULE 9" << endl;
                                            burnGarb(90, 1);
                                            planCounter++;
                                            end = std::chrono::steady_clock::now();
                                            writeExecutionLog(planCounter, end, agName);

                                            if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" || true) {
                                                cout << "RULE 10" << endl;
                                                burnGarb(50, 1);
                                                planCounter++;
                                                end = std::chrono::steady_clock::now();
                                                writeExecutionLog(planCounter, end, agName);

                                                if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" || true) {
                                                    cout << "RULE 11" << endl;
                                                    burnGarb(70, 1);
                                                    planCounter++;
                                                    end = std::chrono::steady_clock::now();
                                                    writeExecutionLog(planCounter, end, agName);

                                                    if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" || true) {
                                                        cout << "RULE 12" << endl;
                                                        burnGarb(90, 1);
                                                        planCounter++;
                                                        end = std::chrono::steady_clock::now();
                                                        writeExecutionLog(planCounter, end, agName);

                                                        if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" || true) {
                                                            cout << "RULE 13" << endl;
                                                            burnGarb(50, 1);
                                                            planCounter++;
                                                            end = std::chrono::steady_clock::now();
                                                            writeExecutionLog(planCounter, end, agName);

                                                            if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" || true) {
                                                                cout << "RULE 14" << endl;
                                                                burnGarb(70, 1);
                                                                planCounter++;
                                                                end = std::chrono::steady_clock::now();
                                                                writeExecutionLog(planCounter, end, agName);

                                                                if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" || true) {
                                                                    cout << "RULE 15" << endl;
                                                                    burnGarb(90, 1);
                                                                    planCounter++;
                                                                    end = std::chrono::steady_clock::now();
                                                                    writeExecutionLog(planCounter, end, agName);

                                                                    if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" || true) {
                                                                        cout << "RULE 16" << endl;
                                                                        burnGarb(50, 1);
                                                                        planCounter++;
                                                                        end = std::chrono::steady_clock::now();
                                                                        writeExecutionLog(planCounter, end, agName);

                                                                        if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" || true) {
                                                                            cout << "RULE 17" << endl;
                                                                            burnGarb(70, 1);
                                                                            planCounter++;
                                                                            end = std::chrono::steady_clock::now();
                                                                            writeExecutionLog(planCounter, end, agName);

                                                                            if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" || true) {
                                                                                cout << "RULE 18" << endl;
                                                                                burnGarb(90, 1);
                                                                                planCounter++;
                                                                                end = std::chrono::steady_clock::now();
                                                                                writeExecutionLog(planCounter, end, agName);

                                                                                if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" || true) {
                                                                                    cout << "RULE 19" << endl;
                                                                                    burnGarb(50, 1);
                                                                                    planCounter++;
                                                                                    end = std::chrono::steady_clock::now();
                                                                                    writeExecutionLog(planCounter, end, agName);

                                                                                    if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" || true) {
                                                                                        cout << "RULE 20" << endl;
                                                                                        burnGarb(70, 1);
                                                                                        planCounter++;
                                                                                        end = std::chrono::steady_clock::now();
                                                                                        writeExecutionLog(planCounter, end, agName);

                                                                                        if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" || true) {
                                                                                            cout << "RULE 21" << endl;
                                                                                            burnGarb(90, 1);
                                                                                            planCounter++;
                                                                                            end = std::chrono::steady_clock::now();
                                                                                            writeExecutionLog(planCounter, end, agName);

                                                                                            if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" || true) {
                                                                                                cout << "RULE 22" << endl;
                                                                                                burnGarb(50, 1);
                                                                                                planCounter++;
                                                                                                end = std::chrono::steady_clock::now();
                                                                                                writeExecutionLog(planCounter, end, agName);

                                                                                                if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" || true) {
                                                                                                    cout << "RULE 23" << endl;
                                                                                                    burnGarb(100, 1);
                                                                                                    planCounter++;
                                                                                                    end = std::chrono::steady_clock::now();
                                                                                                    writeExecutionLog(planCounter, end, agName);

                                                                                                    if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" || true) {
                                                                                                        cout << "RULE 24" << endl;
                                                                                                        burnGarb(100, 1);
                                                                                                        planCounter++;
                                                                                                        end = std::chrono::steady_clock::now();
                                                                                                        writeExecutionLog(planCounter, end, agName);

                                                                                                        if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" || true) {
                                                                                                            cout << "RULE 25" << endl;
                                                                                                            burnGarb(70, 1);
                                                                                                            planCounter++;
                                                                                                            end = std::chrono::steady_clock::now();
                                                                                                            writeExecutionLog(planCounter, end, agName);

                                                                                                            if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" || true) {
                                                                                                                cout << "RULE 26" << endl;
                                                                                                                burnGarb(100, 1);
                                                                                                                planCounter++;
                                                                                                                end = std::chrono::steady_clock::now();
                                                                                                                writeExecutionLog(planCounter, end, agName);

                                                                                                                if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
                                                                                                                    cout << "RULE 27" << endl;
                                                                                                                    burnGarb(100, 1);
                                                                                                                    planCounter++;
                                                                                                                    end = std::chrono::steady_clock::now();
                                                                                                                    writeExecutionLog(planCounter, end, agName);
                                                                                                                }}}}}}}}}}}}}}}}}}}}}}}}}}
        }
    }

void arrangeVacuumPower15_27(Agent agName) {
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();


        cout << "VACUUUUUUM POWER" << endl;

        if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min" && false) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);
            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        } else if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
                cout << "RULE 2" << endl;
                burnGarb(70, 1);
                planCounter++;
                end = std::chrono::steady_clock::now();
                writeExecutionLog(planCounter, end, agName);

        } else   if (batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max" && false) {
                    cout << "RULE 3" << endl;
                    burnGarb(90, 1);
                    planCounter++;
                    end = std::chrono::steady_clock::now();
                    writeExecutionLog(planCounter, end, agName);

        } else  if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
                        cout << "RULE 4" << endl;
                        burnGarb(50, 1);
                        planCounter++;
                        end = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
                            cout << "RULE 5" << endl;
                            burnGarb(70, 1);
                            planCounter++;
                            end = std::chrono::steady_clock::now();
                            writeExecutionLog(planCounter, end, agName);

        } else   if (batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
                      cout << "RULE 6" << endl;
                      burnGarb(90, 1);
                      planCounter++;
                      end = std::chrono::steady_clock::now();
                      writeExecutionLog(planCounter, end, agName);

        } else  if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min" && false) {
                          cout << "RULE 7" << endl;
                          burnGarb(50, 1);
                          planCounter++;
                          end = std::chrono::steady_clock::now();
                          writeExecutionLog(planCounter, end, agName);

        } else if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
                        cout << "RULE 8" << endl;
                        burnGarb(70, 1);
                        planCounter++;
                      end = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter, end, agName);

        } else  if (batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max" && false) {
                          cout << "RULE 9" << endl;
                          burnGarb(90, 1);
                          planCounter++;
                          end = std::chrono::steady_clock::now();
                          writeExecutionLog(planCounter, end, agName);

        } else  if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min" && false) {
                      cout << "RULE 10" << endl;
                      burnGarb(50, 1);
                      planCounter++;
                     end = std::chrono::steady_clock::now();
                            writeExecutionLog(planCounter, end, agName);

        } else  if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
                      cout << "RULE 11" << endl;
                      burnGarb(70, 1);
                      planCounter++;
                      end = std::chrono::steady_clock::now();
                      writeExecutionLog(planCounter, end, agName);

        } else   if (batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max" && false) {
                     cout << "RULE 12" << endl;
                     burnGarb(90, 1);
                     planCounter++;
                     end = std::chrono::steady_clock::now();
                     writeExecutionLog(planCounter, end, agName);

        } else  if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
                    cout << "RULE 13" << endl;
                         burnGarb(50, 1);
                         planCounter++;
                         end = std::chrono::steady_clock::now();
                         writeExecutionLog(planCounter, end, agName);

        } else  if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid" || true) {
                        cout << "RULE 14" << endl;
                        burnGarb(70, 1);
                        planCounter++;
                        end = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter, end, agName);

        }
        if (batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max" || true) {
              cout << "RULE 15" << endl;
              burnGarb(90, 1);
              planCounter++;
              end = std::chrono::steady_clock::now();
              writeExecutionLog(planCounter, end, agName);

              if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" || true) {
                  cout << "RULE 16" << endl;
                  burnGarb(50, 1);
                  planCounter++;
                  end = std::chrono::steady_clock::now();
                  writeExecutionLog(planCounter, end, agName);

                  if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" || true) {
                      cout << "RULE 17" << endl;
                      burnGarb(70, 1);
                      planCounter++;
                      end = std::chrono::steady_clock::now();
                      writeExecutionLog(planCounter, end, agName);

                      if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" || true) {
                          cout << "RULE 18" << endl;
                          burnGarb(90, 1);
                          planCounter++;
                          end = std::chrono::steady_clock::now();
                          writeExecutionLog(planCounter, end, agName);

                          if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" || true) {
                              cout << "RULE 19" << endl;
                              burnGarb(50, 1);
                              planCounter++;
                              end = std::chrono::steady_clock::now();
                              writeExecutionLog(planCounter, end, agName);

                              if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" || true) {
                                  cout << "RULE 20" << endl;
                                  burnGarb(70, 1);
                                  planCounter++;
                                  end = std::chrono::steady_clock::now();
                                  writeExecutionLog(planCounter, end, agName);

                                  if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" || true) {
                                      cout << "RULE 21" << endl;
                                      burnGarb(90, 1);
                                      planCounter++;
                                      end = std::chrono::steady_clock::now();
                                      writeExecutionLog(planCounter, end, agName);

                                      if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" || true) {
                                          cout << "RULE 22" << endl;
                                          burnGarb(50, 1);
                                          planCounter++;
                                          end = std::chrono::steady_clock::now();
                                          writeExecutionLog(planCounter, end, agName);

                                          if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" || true) {
                                              cout << "RULE 23" << endl;
                                              burnGarb(100, 1);
                                              planCounter++;
                                              end = std::chrono::steady_clock::now();
                                              writeExecutionLog(planCounter, end, agName);

                                              if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" || true) {
                                                  cout << "RULE 24" << endl;
                                                  burnGarb(100, 1);
                                                  planCounter++;
                                                  end = std::chrono::steady_clock::now();
                                                  writeExecutionLog(planCounter, end, agName);

                                                  if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" || true) {
                                                    cout << "RULE 25" << endl;
                                                    burnGarb(70, 1);
                                                    planCounter++;
                                                    end = std::chrono::steady_clock::now();
                                                    writeExecutionLog(planCounter, end, agName);

                                                    if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" || true) {
                                                      cout << "RULE 26" << endl;
                                                       burnGarb(100, 1);
                                                       planCounter++;
                                                       end = std::chrono::steady_clock::now();
                                                       writeExecutionLog(planCounter, end, agName);

                                                       if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
                                                        cout << "RULE 27" << endl;
                                                         burnGarb(100, 1);
                                                         planCounter++;
                                                         end = std::chrono::steady_clock::now();
                                                         writeExecutionLog(planCounter, end, agName);
                                                          }}}}}}}}}}}}}
    }










    void arrangeVacuumPower1_15(Agent agName) {
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();

        cout << "VACUUUUUUM POWER" << endl;

        if ((batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "min") || true) {
            cout << "RULE 1" << endl;
            burnGarb(50, 1);
            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);

            if ((batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "mid") || true) {
                cout << "RULE 2" << endl;
                burnGarb(70, 1);
                planCounter++;
                end = std::chrono::steady_clock::now();
                writeExecutionLog(planCounter, end, agName);

                if ((batteryPower == "min" && vacuumBag == "min" && dirtIntensity == "max") || true) {
                    cout << "RULE 3" << endl;
                    burnGarb(90, 1);
                    planCounter++;
                    end = std::chrono::steady_clock::now();
                    writeExecutionLog(planCounter, end, agName);

                    if ((batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "min") || true) {
                        cout << "RULE 4" << endl;
                        burnGarb(50, 1);
                        planCounter++;
                        end = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter, end, agName);

                        if ((batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "mid") || true) {
                            cout << "RULE 5" << endl;
                            burnGarb(70, 1);
                            planCounter++;
                            end = std::chrono::steady_clock::now();
                            writeExecutionLog(planCounter, end, agName);

                            if ((batteryPower == "min" && vacuumBag == "mid" && dirtIntensity == "max") || true) {
                                cout << "RULE 6" << endl;
                                burnGarb(90, 1);
                                planCounter++;
                                end = std::chrono::steady_clock::now();
                                writeExecutionLog(planCounter, end, agName);

                                if ((batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "min") || true) {
                                    cout << "RULE 7" << endl;
                                    burnGarb(50, 1);
                                    planCounter++;
                                    end = std::chrono::steady_clock::now();
                                    writeExecutionLog(planCounter, end, agName);

                                    if ((batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "mid") || true) {
                                        cout << "RULE 8" << endl;
                                        burnGarb(70, 1);
                                        planCounter++;
                                        end = std::chrono::steady_clock::now();
                                        writeExecutionLog(planCounter, end, agName);

                                        if ((batteryPower == "min" && vacuumBag == "max" && dirtIntensity == "max") || true) {
                                            cout << "RULE 9" << endl;
                                            burnGarb(90, 1);
                                            planCounter++;
                                            end = std::chrono::steady_clock::now();
                                            writeExecutionLog(planCounter, end, agName);

                                            if ((batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "min") || true) {
                                                cout << "RULE 10" << endl;
                                                burnGarb(50, 1);
                                                planCounter++;
                                                end = std::chrono::steady_clock::now();
                                                writeExecutionLog(planCounter, end, agName);

                                                if ((batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "mid") || true) {
                                                    cout << "RULE 11" << endl;
                                                    burnGarb(70, 1);
                                                    planCounter++;
                                                    end = std::chrono::steady_clock::now();
                                                    writeExecutionLog(planCounter, end, agName);

                                                    if ((batteryPower == "mid" && vacuumBag == "min" && dirtIntensity == "max") || true) {
                                                        cout << "RULE 12" << endl;
                                                        burnGarb(90, 1);
                                                        planCounter++;
                                                        end = std::chrono::steady_clock::now();
                                                        writeExecutionLog(planCounter, end, agName);

                                                        if ((batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "min") || true) {
                                                            cout << "RULE 13" << endl;
                                                            burnGarb(50, 1);
                                                            planCounter++;
                                                            end = std::chrono::steady_clock::now();
                                                            writeExecutionLog(planCounter, end, agName);

                                                            if ((batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "mid") || true) {
                                                                cout << "RULE 14" << endl;
                                                                burnGarb(70, 1);
                                                                planCounter++;
                                                                end = std::chrono::steady_clock::now();
                                                                writeExecutionLog(planCounter, end, agName);

                                                                if ((batteryPower == "mid" && vacuumBag == "mid" && dirtIntensity == "max") || true) {
                                                                    cout << "RULE 15" << endl;
                                                                    burnGarb(90, 1);
                                                                    planCounter++;
                                                                    end = std::chrono::steady_clock::now();
                                                                    writeExecutionLog(planCounter, end, agName);
                                                                }}}}}}}}}}}}}} }


        if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "min" && false) {
            cout << "RULE 16" << endl;
            burnGarb(50, 1);
            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
            cout << "RULE 17" << endl;
            burnGarb(70, 1);
            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);
        } else if (batteryPower == "mid" && vacuumBag == "max" && dirtIntensity == "max" && false) {
            cout << "RULE 18" << endl;
            burnGarb(90, 1);
            planCounter++;
            end = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, end, agName);


    } else  if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "min" && false) {
        cout << "RULE 19" << endl;
        burnGarb(50, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else  if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "mid" && false) {
        cout << "RULE 20" << endl;
        burnGarb(70, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else  if (batteryPower == "max" && vacuumBag == "min" && dirtIntensity == "max" && false) {
        cout << "RULE 21" << endl;
        burnGarb(90, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else   if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "min" && false) {
        cout << "RULE 22" << endl;
        burnGarb(50, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else   if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "mid" && false) {
        cout << "RULE 23" << endl;
        burnGarb(100, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else  if (batteryPower == "max" && vacuumBag == "mid" && dirtIntensity == "max" && false) {
        cout << "RULE 24" << endl;
        burnGarb(100, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else  if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "min" && false) {
        cout << "RULE 25" << endl;
        burnGarb(70, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else  if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "mid" && false) {
        cout << "RULE 26" << endl;
        burnGarb(100, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);

    } else  if (batteryPower == "max" && vacuumBag == "max" && dirtIntensity == "max" || true) {
        cout << "RULE 27" << endl;
        burnGarb(100, 1);
        planCounter++;
        end = std::chrono::steady_clock::now();
        writeExecutionLog(planCounter, end, agName);


    }
}




    void burnGarb(int vacuumPowerLimit, int vacuumPowerConstant) {
        // Implement the method as needed
    //    float updatedval = (this->returnIntensity(x,y))-((power*flag));
     //   this->setIntensity(x,y,updatedval);
        // Calculate the updated intensity value and ensure it doesn't go below 0
        float timeInterval = 1.0;

        float returnedGarbateIntensity = returnIntensity(x, y);

        float updatedVal = max(0.0f, returnedGarbateIntensity - (vacuumPowerLimit * vacuumPowerConstant));

        float vacuumedgarbageIntensity = returnedGarbateIntensity - updatedVal;

        // Update the intensity
        setIntensity(x, y, updatedVal);
        cout << "Burning garbage with vacuumPowerLimit: " << vacuumPowerLimit*vacuumPowerConstant << " and updatedval: " << updatedVal << endl;



        bool batteryResult = battery.performAction((vacuumPowerLimit * vacuumPowerConstant) / 100.0, timeInterval);

        bool addResult = vacuumBagObj.addItems(vacuumedgarbageIntensity);

        cout << "REACH burnGarb CAPS: BatteryResult: " << battery.getCapacity()
                  << " addResult: " << vacuumBagObj.getRemainingCapacity() << endl;

        if (!batteryResult) {
            this->batteryPower = "depleted";
            cout << "AGENT BATTERY IS DEPLETED" << endl;
        }

        if (!addResult) {
            this->vacuumBag = "full";
            cout << "AGENT BAG IS FULL" << endl;
        }



    }



    // Method to check battery, vacuum bag, and dirt intensity status
    void checkStatusBool() {

        cout << " Agent Name"<<this->name <<" Battery Capacity " << battery.getCapacity() <<"Vacuum Capacity " << vacuumBagObj.getRemainingCapacity() << endl;
        cout << " Battery Power "<<this->batteryPower <<" Vacuum Bag " << vacuumBag<< endl;

        // Battery status check
        if (battery.getCapacity() <= 5.0) {
            batteryPower = "min";
        }
        else if (battery.getCapacity() > 5.0 && battery.getCapacity() <= 10.0) {
            batteryPower = "mid";
        }
        else if (battery.getCapacity() > 10.0) {
            batteryPower = "max";
        }

        // Vacuum bag status check

        if (vacuumBagObj.getRemainingCapacity() <= 500) {
            vacuumBag = "min";
        }
        else if (vacuumBagObj.getRemainingCapacity() > 500 && vacuumBagObj.getRemainingCapacity() <= 750) {
            vacuumBag = "mid";
        }
        else if (vacuumBagObj.getRemainingCapacity() > 750) {
            vacuumBag = "max";
        }

        // Dirt intensity logic
        float intensityValue = returnIntensity(x, y);  // Calculate intensity based on position


        if (intensityValue > 30.0 && intensityValue <= 50.0) {
            dirtIntensity = "min";
          //  cout << "Dirt Intensity: min" << endl;
        }
        if (intensityValue > 50.0 && intensityValue <= 70.0) {
          //  cout << "Dirt Intensity: mid" << endl;
            dirtIntensity = "mid";
        }
        if (intensityValue > 70.0) {
        //    cout << "Dirt Intensity: max" << endl;
            dirtIntensity = "max";
        }
    }


};

// Grid class definition
class Grid {
private:
    int width = 14;
    int height = 14;
    vector<vector<float>> values;  // Store the full floating-point values

public:
    Grid() {
        values = vector<vector<float>>(height, vector<float>(width, 0.0));

        // Generate random floating numbers between 0.0 and 100.0
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                // Generates a value between 0.00 and 100.00
                values[i][j] = static_cast<float>(rand() % 10001) / 100.0;  //100.0
            }
        }
    }



    // Function to get the intensity value at specific (x, y) coordinates
    float getValue(int x, int y) const {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return values[y][x];  // Return the value at the (x, y) position
        }
        return 0.0f;  // Return 0.0 if coordinates are out of bounds
    }

    void setValue(int x, int y, float value) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            values[y][x] = value;  // Set the value at the (x, y) position
        }
        // You can also add an else statement to handle out-of-bounds cases if needed
    }


    void displayGrid(const Agent& r1, const Agent& r3) const {
        cout << "**************************************************************\n";

        // Muted blue for floating point numbers
        string floatColorCode = "\033[38;2;100;120;160m";  // Muted blue color for floating point numbers

        // Less bright red for agents
        string agentColor = "\033[38;2;180;0;0m";  // Muted red for R1, R2, and R3

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i == 7 && j == 7) {
                    cout << agentColor << " R2" << "\033[0m "; // Muted Red R2
                } else if (i == r1.y && j == r1.x) {
                    cout << agentColor << " R1" << "\033[0m "; // Muted Red R1
                } else if (i == r3.y && j == r3.x) {
                    cout << agentColor << " R3" << "\033[0m "; // Muted Red R3
                } else {
                    // Extract only the integer part of the floating-point number for display
                    int intPart = static_cast<int>(values[i][j]);  // Extract the integer part
                    cout << floatColorCode << setw(3) << intPart << "\033[0m "; // Apply muted blue color and display only the integer part
                }
            }
            cout << endl;
        }
    }
};

// This function is now valid because Grid is fully defined
float Agent::returnIntensity(int x, int y) {
    return grid->getValue(x, y);  // Access grid's getValue() method
}
void Agent::setIntensity(int x, int y, float value) {
    return grid->setValue(x, y,value);  // Access grid's getValue() method
}

int main() {
#ifdef _WIN32
    enableANSI();  // Enable ANSI colors on Windows
#endif

    Grid grid;
    Agent R1("R1", 0, 0, 14, 14, 5.0, 0.02, 6000.0,&grid);
    Agent R3("R3", 13, 13, 14, 14, 5.0, 0.02, 6000.0,&grid);

    cout << " R1 Initial " << endl;
    R1.displayPosition();
    cout << " R3 Initial " << endl;
    R3.displayPosition();

    // Simulate actions and battery consumption
    R1.checkStatusBool();  // Update R1's status based on battery, vacuum, and dirt intensity
    R3.checkStatusBool();  // Update R1's status based on battery, vacuum, and dirt intensity





  //  ****************************************************************

    while (true) {
   //   grid.displayGrid(R1, R3);
       // this_thread::sleep_for(chrono::milliseconds(1));
        if (((R1.x != 6 || R1.y != 7) || R3.continue_signal) &&  R1.batteryPower != "depleted" && R1.vacuumBag != "full") {
            R1.startTimer();
            R1.checkStatusBool();
         //   std::this_thread::sleep_for(std::chrono::milliseconds(1));
            R1.arrangeVacuumPower1_27(R1);
            R1.nextSlot();
            } else if (R1.batteryPower == "depleted" || R1.vacuumBag == "full") {
                std::cout << "Agent 1 - Finished 1\n";
                R1.continue_signal = true;
            }

        if ((R1.x == 6 && R1.y == 7) && R1.batteryPower != "depleted" && R1.vacuumBag != "full") {
            std::cout << "Agent 1 - Finished 2 - Can Continue - AG 3 Signal: " << R3.continue_signal << "\n";
            R1.continue_signal = true;
        }



        if (((R3.x != 8 || R3.y != 7) || R1.continue_signal) &&
            R3.batteryPower != "depleted" && R3.vacuumBag != "full") {

            R3.startTimer();


            R3.checkStatusBool();
          //  std::this_thread::sleep_for(std::chrono::milliseconds(1));
            R3.arrangeVacuumPower1_27(R3);
            R3.nextSlotRev();
            } else if (R3.batteryPower == "depleted" || R3.vacuumBag == "full") {
                std::cout << "Agent 3 - Finished 1\n";
                R3.continue_signal = true;
            }

        if ((R3.x == 8 && R3.y == 7) && R3.batteryPower != "depleted" && R3.vacuumBag != "full") {
            std::cout << "Agent 3 - Finished 2 - Can Continue\n";
            R3.continue_signal = true;
        }

        // Break the loop if both agents have reached their goals
        if ((R1.x >= 13 && R1.y >= 13) && (R3.x <= 0 && R3.y <= 0)) {
            break;
        }

    }





    return 0;
}