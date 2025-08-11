



#include <iostream>
#include <thread>
#include <chrono>
#include <fstream>
#include <string>


#include "AgentPLFeatures.hpp"
#include "BuildAgent.hpp"
#include "PushAgent.hpp"

using namespace std;

class SortAgent {
public:
    bool dropped = false;
    bool shredEndSent = false;
    int count_time = 0;
    bool reverse_triggered = false;
    bool colorDecided = false;

    static string isRed;
    static string isGreen;
    static string isBlue;
    std::chrono::steady_clock::time_point startTime;  // sadece declaration


    SortAgent() {
        cout << "SortAgent started." << endl;
        setup();
    }

    void setup() {
        cout << "SortAgent initialized." << endl;
     //   nextColor();
    }

    void nextColor() {
        try {

        //    std::this_thread::sleep_for(std::chrono::milliseconds(1)); // aynı şekilde 1 saniye

            AgentPLFeatures::boolColourSensor();

            isRed = AgentPLFeatures::redVal;
            isGreen = AgentPLFeatures::greenVal;
            isBlue = AgentPLFeatures::blueVal;



           decide360();
        //   std::this_thread::sleep_for(std::chrono::milliseconds(1)); // aynı şekilde 1 saniye



        } catch (...) {
            cerr << "Exception in nextColor" << endl;
        }
    }

    // Simulating product counter check, stopping when it reaches 64
      bool limitProductCounter() {
        if (AgentPLFeatures::productCounter >= 64) {
            return true;
            // Logic to stop or finalize processing can be added here
        }
        return  false;
    }





    void writeExecutionLog(int planNumber, auto endTime) {


        auto ms = std::chrono::duration_cast<std::chrono::milliseconds>(startTime.time_since_epoch()).count();
        std::cout << "Start time (ms since steady clock epoch): " << ms << std::endl;

        auto end = std::chrono::steady_clock::now();
        auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - startTime);

        std::string filePath = "C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen"
                               "\\Bureaublad\\Activities\\Publications\\TSF\\Case Studies\\Production Line"
                               "\\OutputFiles\\WCET\\Yeni Olcumler\\NetworkScaler\\Cpp\\output.txt";

        // Alternative for Linux:
        // std::string filePath = "/home/robot/productionLineEmpirical/src/java/output.txt";

        try {
            std::ofstream writer(filePath, std::ios::app);
            if (!writer) {
                throw std::ios_base::failure("Failed to open file.");
            }

            writer << "PLANN " << planNumber << " | Execution time (ms): " << elapsed.count() << "\n";
            writer.close();
        } catch (const std::exception& e) {
            std::cerr << "IO Exception: " << e.what() << std::endl;
        }
    }


void decideColor() {
    cout << "REDDO " << isRed << endl;

    if (isRed == "high" && isGreen == "low" && isBlue == "medium") {
        cout << "RED 1 - MEDIUM" << endl;
        sendBuildMessage();
    } else if (isRed == "high" && isGreen == "medium" && isBlue == "medium") {
        cout << "RED 2 - MEDIUM" << endl;
        sendBuildMessage();
    } else if (isRed == "high" && isGreen == "high" && isBlue == "low") {
        cout << "RED 3 - MEDIUM" << endl;
        sendBuildMessage();
    } else if (isRed == "medium" && isGreen == "high" && isBlue == "low") {
        cout << "RED 7 - MEDIUM" << endl;
        sendBuildMessage();
    } else if (isRed == "medium" && isGreen == "medium" && isBlue == "low") {
        cout << "RED 4 - MEDIUM" << endl;
        sendBuildMessage();
    } else if (isRed == "medium" && isGreen == "veryhigh" && isBlue == "low") {
        cout << "RED 9 - MEDIUM" << endl;
        sendBuildMessage();
    } else if (isRed == "high" && isGreen == "veryhigh" && isBlue == "low") {
        cout << "RED 10 - HIGH" << endl;
        sendBuildMessage();
    } else if (isRed == "medium" && isGreen == "medium" && isBlue == "medium") {
        sendPushMessage();
        cout << "PURPLE 4 - MEDIUM" << endl;
    } else if (isRed == "medium" && isGreen == "high" && isBlue == "medium") {
        sendPushMessage();
        cout << "PURPLE 5 - MEDIUM" << endl;
    } else if (isRed == "high" && isGreen == "high" && isBlue == "medium") {
        sendPushMessage();
        cout << "PURPLE 2 - HIGH" << endl;
    } else if (isRed == "medium" && isGreen == "high" && isBlue == "high") {
        sendPushMessage();
        cout << "PURPLE 5 - MEDIUM" << endl;
    } else if (isRed == "medium" && isGreen == "medium" && isBlue == "high") {
        sendPushMessage();
        cout << "PURPLE 7 - MEDIUM" << endl;
    } else if (isRed == "high" && isGreen == "medium" && isBlue == "low") {
        sendPushMessage();
        cout << "PURPLE 3 - HIGH" << endl;
    } else if (isRed == "high" && isGreen == "high" && isBlue == "high") {
        sendPushMessage();
        cout << "PURPLE 2 - HIGH" << endl;
    } else if (isRed == "medium" && isGreen == "veryhigh" && isBlue == "medium") {
        sendPushMessage();
        cout << "PURPLE 13 - MEDIUM" << endl;
    } else if (isRed == "medium" && isGreen == "veryhigh" && isBlue == "high") {
        sendPushMessage();
        cout << "PURPLE 14 - MEDIUM" << endl;
    } else if (isRed == "high" && isGreen == "veryhigh" && isBlue == "high") {
        sendPushMessage();
        cout << "PURPLE 15 - MEDIUM" << endl;
    } else if (isRed == "medium" && isGreen == "ultramedium" && isBlue == "medium") {
        sendPushMessage();
        cout << "LIGHT GREEN 1" << endl;
    } else if (isRed == "medium" && isGreen == "ulralow" && isBlue == "medium") {
        sendPushMessage();
        cout << "LIGHT GREEN 2" << endl;
    } else if (isRed == "medium" && isGreen == "ultrahigh" && isBlue == "high") {
        sendPushMessage();
        cout << "LIGHT GREEN 6" << endl;
    } else if (isRed == "low" && isGreen == "ulralow" && isBlue == "medium") {
        sendPushMessage();
        cout << "MIDDLE GREEN 1" << endl;
    } else if (isRed == "low" && isGreen == "ultramedium" && isBlue == "low") {
        sendPushMessage();
        cout << "MIDDLE GREEN 2" << endl;
    } else if (isRed == "low" && isGreen == "ulralow" && isBlue == "low") {
        sendPushMessage();
        cout << "MIDDLE GREEN 3" << endl;
    } else if (isRed == "low" && isGreen == "ultramedium" && isBlue == "medium") {
        sendPushMessage();
        cout << "MIDDLE GREEN 4" << endl;
    } else if (isRed == "low" && isGreen == "veryhigh" && isBlue == "medium") {
        sendPushMessage();
        cout << "MIDDLE GREEN 5" << endl;
    } else if (isRed == "low" && isGreen == "veryhigh" && isBlue == "high") {
        sendPushMessage();
        cout << "MIDDLE GREEN 6" << endl;
    } else if (isRed == "low" && isGreen == "high" && isBlue == "low") {
        sendPushMessage();
        cout << "KKKKKKKKKK DARK GREEN 1 $$$$$" << endl;
    } else if (isRed == "low" && isGreen == "high" && isBlue == "medium") {
        sendPushMessage();
        cout << "KKKKKKKKKK DARK GREEN 2 $$$$$" << endl;
    } else if (isRed == "low" && isGreen == "veryhigh" && isBlue == "low") {
        sendPushMessage();
        cout << "KKKKKKKKKK DARK GREEN 3 $$$$$" << endl;
    } else {
        cout << "UNKNOWN COLOR" << endl;
    }
}

 void decideColor1_18(){


        int planCounter = 0;
        auto endTime = std::chrono::steady_clock::now();


        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) || true) {
            planCounter++;

            std::cout << "PLAN 1" << std::endl;



            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);

            if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) || true) {
                planCounter++;
                std::cout << "PLAN 2" << std::endl;
                endTime = std::chrono::steady_clock::now();
                writeExecutionLog(planCounter, endTime);

                if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) || true) {
                    planCounter++;
                    std::cout << "PLAN 3" << std::endl;
                    endTime = std::chrono::steady_clock::now();
                    writeExecutionLog(planCounter, endTime);

                    if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) || true) {
                        planCounter++;
                        std::cout << "PLAN 4" << std::endl;
                        endTime = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter, endTime);

                        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) || true) {
                            planCounter++;
                            std::cout << "PLAN 5" << std::endl;
                            endTime = std::chrono::steady_clock::now();
                            writeExecutionLog(planCounter, endTime);

                            if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                planCounter++;
                                std::cout << "PLAN 6" << std::endl;
                                endTime = std::chrono::steady_clock::now();
                                writeExecutionLog(planCounter, endTime);

                                if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                    planCounter++;
                                    std::cout << "PLAN 7" << std::endl;
                                    endTime = std::chrono::steady_clock::now();
                                    writeExecutionLog(planCounter, endTime);

                                    if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) || true) {
                                        planCounter++;
                                        std::cout << "PLAN 8" << std::endl;
                                        endTime = std::chrono::steady_clock::now();
                                        writeExecutionLog(planCounter, endTime);

                                        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) || true) {
                                            planCounter++;
                                            std::cout << "PLAN 9" << std::endl;
                                            endTime = std::chrono::steady_clock::now();
                                            writeExecutionLog(planCounter, endTime);

                                            if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) || true) {
                                                planCounter++;
                                                std::cout << "PLAN 10" << std::endl;
                                                endTime = std::chrono::steady_clock::now();
                                                writeExecutionLog(planCounter, endTime);

                                                if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) || true) {
                                                    planCounter++;
                                                    std::cout << "PLAN 11" << std::endl;
                                                    endTime = std::chrono::steady_clock::now();
                                                    writeExecutionLog(planCounter, endTime);

                                                    if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) || true) {
                                                        planCounter++;
                                                        std::cout << "PLAN 12" << std::endl;
                                                        endTime = std::chrono::steady_clock::now();
                                                        writeExecutionLog(planCounter, endTime);

                                                        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) || true) {
                                                            planCounter++;
                                                            std::cout << "PLAN 13" << std::endl;
                                                            endTime = std::chrono::steady_clock::now();
                                                            writeExecutionLog(planCounter, endTime);

                                                            if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high")) || true) {
                                                                planCounter++;
                                                                std::cout << "PLAN 14" << std::endl;
                                                                endTime = std::chrono::steady_clock::now();
                                                                writeExecutionLog(planCounter, endTime);

                                                                if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) || true) {
                                                                    planCounter++;
                                                                    std::cout << "PLAN 15" << std::endl;
                                                                    endTime = std::chrono::steady_clock::now();
                                                                    writeExecutionLog(planCounter, endTime);

                                                                    if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) || true) {
                                                                        planCounter++;
                                                                        std::cout << "PLAN 16" << std::endl;
                                                                        endTime = std::chrono::steady_clock::now();
                                                                        writeExecutionLog(planCounter, endTime);

                                                                        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) || true) {
                                                                            planCounter++;
                                                                            std::cout << "PLAN 17" << std::endl;
                                                                            endTime = std::chrono::steady_clock::now();
                                                                            writeExecutionLog(planCounter, endTime);

                                                                            if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) || true) {
                                                                                planCounter++;
                                                                                std::cout << "PLAN 18" << std::endl;
                                                                                endTime = std::chrono::steady_clock::now();
                                                                                writeExecutionLog(planCounter, endTime);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium"))  && false  ) {
            planCounter++;
            std::cout << "PLAN 19" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false  ) {
            planCounter++;
            std::cout << "PLAN 20" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false  ) {
            planCounter++;
            std::cout << "PLAN 21" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false ) {
            planCounter++;
            std::cout << "PLAN 22" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low")) && false ) {
            planCounter++;
            std::cout << "PLAN 23" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false ) {
            planCounter++;
            std::cout << "PLAN 24" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium"))  && false ) {
            planCounter++;
            std::cout << "PLAN 25" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false ) {
            planCounter++;
            std::cout << "PLAN 26" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low"))  && false ) {
            planCounter++;
            std::cout << "PLAN 27" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false ) {
            planCounter++;
            std::cout << "PLAN 28" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false ) {
            planCounter++;
            std::cout << "PLAN 29" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("low"))  && false ) {
            planCounter++;
            std::cout << "PLAN 30" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("verylow")) && false ) {
            planCounter++;
            std::cout << "PLAN 31" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false ) {
            planCounter++;
            std::cout << "PLAN 32" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("verylow") && isGreen == ("ultrahigh") && isBlue == ("medium")) || true) {
            planCounter++;
            std::cout << "PLAN 33" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("verylow") && isGreen == ("ultrahigh") && isBlue == ("ultrahigh"))&& false ) {
            planCounter++;
            std::cout << "PLAN 34" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("verylow") && isGreen == ("ultramedium") && isBlue == ("ultrahigh"))&& false ) {
            planCounter++;
            std::cout << "PLAN 35" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow"))&& false ) {
            planCounter++;
            std::cout << "PLAN 36" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

       // System.out.println("PLAN C " + planCounter);
        planCounter = 0;

    }

    void decideColor40rec() {
        int planCounter = 0;
        auto endTime = std::chrono::steady_clock::now();

        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) || true) {

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
             endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter,  endTime);


            if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) || true) {
                planCounter++;
                std::cout << "PLAN 2" << std::endl;
                 endTime = std::chrono::steady_clock::now();
                writeExecutionLog(planCounter,  endTime);


                if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) || true) {
                    planCounter++;
                    std::cout << "PLAN 3" << std::endl;
                    endTime = std::chrono::steady_clock::now();
                    writeExecutionLog(planCounter,  endTime);

                    if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) || true) {
                        planCounter++;
                        std::cout << "PLAN 4" << std::endl;
                        endTime = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter,  endTime);

                        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) || true) {
                            planCounter++;
                            std::cout << "PLAN 5" << std::endl;
                            endTime = std::chrono::steady_clock::now();
                            writeExecutionLog(planCounter,  endTime);

                            if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                planCounter++;
                                std::cout << "PLAN 6" << std::endl;
                                endTime = std::chrono::steady_clock::now();
                                writeExecutionLog(planCounter,  endTime);

                                if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                    planCounter++;
                                    std::cout << "PLAN 7" << std::endl;
                                    endTime = std::chrono::steady_clock::now();
                                    writeExecutionLog(planCounter,  endTime);

                                    if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) || true) {
                                        planCounter++;
                                        std::cout << "PLAN 8" << std::endl;
                                        endTime = std::chrono::steady_clock::now();
                                        writeExecutionLog(planCounter,  endTime);

                                        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) || true) {
                                            planCounter++;
                                            std::cout << "PLAN 9" << std::endl;
                                            endTime = std::chrono::steady_clock::now();
                                            writeExecutionLog(planCounter,  endTime);

                                            if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) || true) {
                                                planCounter++;
                                                std::cout << "PLAN 10" << std::endl;
                                                endTime = std::chrono::steady_clock::now();
                                                writeExecutionLog(planCounter,  endTime);

                                                if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) || true) {
                                                    planCounter++;
                                                    std::cout << "PLAN 11" << std::endl;
                                                    endTime = std::chrono::steady_clock::now();
                                                    writeExecutionLog(planCounter,  endTime);

                                                    if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) || true) {
                                                        planCounter++;
                                                        std::cout << "PLAN 12" << std::endl;
                                                        endTime = std::chrono::steady_clock::now();
                                                        writeExecutionLog(planCounter,  endTime);

                                                        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) || true) {
                                                            planCounter++;
                                                            std::cout << "PLAN 13" << std::endl;
                                                            endTime = std::chrono::steady_clock::now();
                                                            writeExecutionLog(planCounter,  endTime);

                                                            if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high")) || true) {
                                                                planCounter++;
                                                                std::cout << "PLAN 14" << std::endl;
                                                                endTime = std::chrono::steady_clock::now();
                                                                writeExecutionLog(planCounter,  endTime);

                                                                if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) || true) {
                                                                    planCounter++;
                                                                    std::cout << "PLAN 15" << std::endl;
                                                                    endTime = std::chrono::steady_clock::now();
                                                                    writeExecutionLog(planCounter,  endTime);

                                                                    if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) || true) {
                                                                        planCounter++;
                                                                        std::cout << "PLAN 16" << std::endl;
                                                                        endTime = std::chrono::steady_clock::now();
                                                                        writeExecutionLog(planCounter,  endTime);

                                                                        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) || true) {
                                                                            planCounter++;
                                                                            std::cout << "PLAN 17" << std::endl;
                                                                            endTime = std::chrono::steady_clock::now();
                                                                            writeExecutionLog(planCounter,  endTime);

                                                                            if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) || true) {
                                                                                planCounter++;
                                                                                std::cout << "PLAN 18" << std::endl;
                                                                                endTime = std::chrono::steady_clock::now();
                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) || true) {
                                                                                    planCounter++;
                                                                                    std::cout << "PLAN 19" << std::endl;
                                                                                    endTime = std::chrono::steady_clock::now();
                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                    if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) || true) {
                                                                                        planCounter++;
                                                                                        std::cout << "PLAN 20" << std::endl;
                                                                                        endTime = std::chrono::steady_clock::now();
                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) || true) {
                                                                                            planCounter++;
                                                                                            std::cout << "PLAN 21" << std::endl;

                                                                                            endTime = std::chrono::steady_clock::now();
                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                            if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) || true) {
                                                                                                planCounter++;
                                                                                                std::cout << "PLAN 22" << std::endl;

                                                                                                endTime = std::chrono::steady_clock::now();
                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low")) || true) {
                                                                                                    planCounter++;
                                                                                                    std::cout << "PLAN 23" << std::endl;

                                                                                                    endTime = std::chrono::steady_clock::now();
                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                    if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) || true) {
                                                                                                        planCounter++;
                                                                                                        std::cout << "PLAN 24" << std::endl;

                                                                                                        endTime = std::chrono::steady_clock::now();
                                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                                        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) || true) {
                                                                                                            planCounter++;
                                                                                                            std::cout << "PLAN 25" << std::endl;

                                                                                                            endTime = std::chrono::steady_clock::now();
                                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                                            if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) || true) {
                                                                                                                planCounter++;
                                                                                                                std::cout << "PLAN 26" << std::endl;

                                                                                                                endTime = std::chrono::steady_clock::now();
                                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                                if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) || true) {
                                                                                                                    planCounter++;
                                                                                                                    std::cout << "PLAN 27" << std::endl;

                                                                                                                    endTime = std::chrono::steady_clock::now();
                                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                                    if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) || true) {
                                                                                                                        planCounter++;
                                                                                                                        std::cout << "PLAN 28" << std::endl;

                                                                                                                        endTime = std::chrono::steady_clock::now();
                                                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                                                        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                                                                                                            planCounter++;
                                                                                                                            std::cout << "PLAN 29" << std::endl;

                                                                                                                            endTime = std::chrono::steady_clock::now();
                                                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                                                            if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                                                                                                                planCounter++;
                                                                                                                                std::cout << "PLAN 30" << std::endl;

                                                                                                                                endTime = std::chrono::steady_clock::now();
                                                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                                                if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("verylow")) || true) {
                                                                                                                                    planCounter++;
                                                                                                                                    std::cout << "PLAN 31" << std::endl;

                                                                                                                                    endTime = std::chrono::steady_clock::now();
                                                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                                                    if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) || true) {
                                                                                                                                        planCounter++;
                                                                                                                                        std::cout << "PLAN 32" << std::endl;

                                                                                                                                        endTime = std::chrono::steady_clock::now();
                                                                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                                                                        if ((isRed == ("verylow") && isGreen == ("ultrahigh") && isBlue == ("medium")) || true) {
                                                                                                                                            planCounter++;
                                                                                                                                            std::cout << "PLAN 33" << std::endl;

                                                                                                                                            endTime = std::chrono::steady_clock::now();
                                                                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                                                                            if ((isRed == ("verylow") && isGreen == ("ultrahigh") && isBlue == ("ultrahigh")) || true) {
                                                                                                                                                planCounter++;
                                                                                                                                                std::cout << "PLAN 34" << std::endl;

                                                                                                                                                endTime = std::chrono::steady_clock::now();
                                                                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                                                                if ((isRed == ("verylow") && isGreen == ("ultramedium") && isBlue == ("ultrahigh")) || true) {
                                                                                                                                                    planCounter++;
                                                                                                                                                    std::cout << "PLAN 35" << std::endl;

                                                                                                                                                    endTime = std::chrono::steady_clock::now();
                                                                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                                                                    if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) || true) {
                                                                                                                                                        planCounter++;
                                                                                                                                                        std::cout << "PLAN 36" << std::endl;

                                                                                                                                                        endTime = std::chrono::steady_clock::now();
                                                                                                                                                        writeExecutionLog(planCounter,  endTime);
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        std::cout << "Total plans executed: " << planCounter << std::endl;

    }


    void decideColor18_36 () {
        int planCounter = 0;
        auto endTime = std::chrono::steady_clock::now();


        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium"))  && false ) {
            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false ) {
            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low")) && false ) {
            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false ) {
            planCounter++;
            std::cout << "PLAN 10" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high"))&& false ) {
            planCounter++;
            std::cout << "PLAN 11" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) || true) {
            planCounter++;
            std::cout << "PLAN 12" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false ) {
            planCounter++;
            std::cout << "PLAN 13" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            std::cout << "PLAN 14" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false ) {
            planCounter++;
            std::cout << "PLAN 15" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            std::cout << "PLAN 16" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false ) {
            planCounter++;
            std::cout << "PLAN 17" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            std::cout << "PLAN 18" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }





        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) || true) {
            planCounter++;
            std::cout << "PLAN 19" << std::endl;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);

            if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) || true) {
                planCounter++;
                std::cout << "PLAN 20" << std::endl;
                endTime = std::chrono::steady_clock::now();
                writeExecutionLog(planCounter, endTime);

                if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) || true) {
                    planCounter++;
                    std::cout << "PLAN 21" << std::endl;
                    endTime = std::chrono::steady_clock::now();
                    writeExecutionLog(planCounter, endTime);

                    if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) || true) {
                        planCounter++;
                        std::cout << "PLAN 22" << std::endl;
                        endTime = std::chrono::steady_clock::now();
                        writeExecutionLog(planCounter, endTime);

                        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low")) || true) {
                            planCounter++;
                            std::cout << "PLAN 23" << std::endl;
                            endTime = std::chrono::steady_clock::now();
                            writeExecutionLog(planCounter, endTime);

                            if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) || true) {
                                planCounter++;
                                std::cout << "PLAN 24" << std::endl;
                                endTime = std::chrono::steady_clock::now();
                                writeExecutionLog(planCounter, endTime);

                                if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) || true) {
                                    planCounter++;
                                    std::cout << "PLAN 25" << std::endl;
                                    endTime = std::chrono::steady_clock::now();
                                    writeExecutionLog(planCounter, endTime);

                                    if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) || true) {
                                        planCounter++;
                                        std::cout << "PLAN 26" << std::endl;
                                        endTime = std::chrono::steady_clock::now();
                                        writeExecutionLog(planCounter, endTime);

                                        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) || true) {
                                            planCounter++;
                                            std::cout << "PLAN 27" << std::endl;
                                            endTime = std::chrono::steady_clock::now();
                                            writeExecutionLog(planCounter, endTime);

                                            if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) || true) {
                                                planCounter++;
                                                std::cout << "PLAN 28" << std::endl;
                                                endTime = std::chrono::steady_clock::now();
                                                writeExecutionLog(planCounter, endTime);

                                                if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                                    planCounter++;
                                                    std::cout << "PLAN 29" << std::endl;
                                                    endTime = std::chrono::steady_clock::now();
                                                    writeExecutionLog(planCounter, endTime);

                                                    if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("low")) || true) {
                                                        planCounter++;
                                                        std::cout << "PLAN 30" << std::endl;
                                                        endTime = std::chrono::steady_clock::now();
                                                        writeExecutionLog(planCounter, endTime);

                                                        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("verylow")) || true) {
                                                            planCounter++;
                                                            std::cout << "PLAN 31" << std::endl;
                                                            endTime = std::chrono::steady_clock::now();
                                                            writeExecutionLog(planCounter, endTime);

                                                            if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) || true) {
                                                                planCounter++;
                                                                std::cout << "PLAN 32" << std::endl;
                                                                endTime = std::chrono::steady_clock::now();
                                                                writeExecutionLog(planCounter, endTime);

                                                                if ((isRed == ("verylow") && isGreen == ("ultrahigh") && isBlue == ("medium")) || true) {
                                                                    planCounter++;
                                                                    std::cout << "PLAN 33" << std::endl;
                                                                    endTime = std::chrono::steady_clock::now();
                                                                    writeExecutionLog(planCounter, endTime);

                                                                    if ((isRed == ("verylow") && isGreen == ("ultrahigh") && isBlue == ("ultrahigh")) || true) {
                                                                        planCounter++;
                                                                        std::cout << "PLAN 34" << std::endl;
                                                                        endTime = std::chrono::steady_clock::now();
                                                                        writeExecutionLog(planCounter, endTime);

                                                                        if ((isRed == ("verylow") && isGreen == ("ultramedium") && isBlue == ("ultrahigh")) || true) {
                                                                            planCounter++;
                                                                            std::cout << "PLAN 35" << std::endl;
                                                                            endTime = std::chrono::steady_clock::now();
                                                                            writeExecutionLog(planCounter, endTime);

                                                                            if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) || true) {
                                                                                planCounter++;
                                                                                std::cout << "PLAN 36" << std::endl;
                                                                                endTime = std::chrono::steady_clock::now();
                                                                                writeExecutionLog(planCounter, endTime);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }



    }

    void decide108() {
        int planCounter = 0;
        auto endTime = std::chrono::steady_clock::now();


        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) || true) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
    }


    void decide36() {
        int planCounter = 0;
        auto endTime = std::chrono::steady_clock::now();


        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) || true) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
    }


    void decide360() {
        int planCounter = 0;
        auto endTime = std::chrono::steady_clock::now();


        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("medium") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("medium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("high") && isBlue == ("high"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("high") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("medium") && isGreen == ("ultrahigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultralow") && isBlue == ("low"))&& false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("ultramedium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("high") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("low") && isGreen == ("veryhigh") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("low") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("medium") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("low")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("high") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("medium")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("veryhigh") && isBlue == ("high")) && false) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed == ("verylow") && isGreen == ("ultralow") && isBlue == ("ultralow")) || true) {
            planCounter++;
            endTime = std::chrono::steady_clock::now();
            writeExecutionLog(planCounter, endTime);
        }

    }




    void sendBuildMessage() {
        BuildAgent::onMessageReceived("build");
    }

    void sendPushMessage() {
        PushAgent::listenForMessages("push");
    }
};



int main() {

    std::cout << "Production Line Simulation Başladı!" << std::endl;

    BuildAgent buildAgent = BuildAgent();


    AgentPLFeatures::parseDataFile();

    SortAgent sortAgent;

   while (!sortAgent.limitProductCounter()) {

        sortAgent.startTime = std::chrono::steady_clock::now();
        sortAgent.nextColor();

   }

    return 0;
}


string SortAgent::isRed = "none";
string SortAgent::isGreen = "none";
string SortAgent::isBlue = "none";




