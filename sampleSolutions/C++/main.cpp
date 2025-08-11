#include <iostream>
#include <thread>
#include <string>

#include "ScalerAgentEnvironment.c++" // Assuming this class exists in C++
using namespace std;

class ScalerAgent {
public:
    static int currentWorkLoad;
    static int arrivedTurn;
    static string responseTime;
    static string workLoad;
    static int arrivedWorkload;
    std::chrono::steady_clock::time_point startTime;  // sadece declaration



    void writeExecutionLog(int planNumber, auto endTime) {


        auto ms = std::chrono::duration_cast<std::chrono::milliseconds>(startTime.time_since_epoch()).count();
        std::cout << "Start time (ms since steady clock epoch): " << ms << std::endl;

        auto end = std::chrono::steady_clock::now();
        auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(end - startTime);


        std::string filePath = "C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\Publications\\TSF"
                               "\\Case Studies\\Production Line\\OutputFiles\\WCET\\Yeni Olcumler\\NetworkScaler\\Cpp\\output.txt";


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

    void arrangeResourceScale(ScalerAgentEnvironment &sae) {
        string rule = "CCC";
        int factor = 0;
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();

        if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();


            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        cout << rule << endl;
        sae.scaleFactor(factor);
        sae.consumeWorkLoad();
    }

    // //

    void arrangeResourceScale27(ScalerAgentEnvironment &sae) {
        string rule = "CCC";
        int factor = 0;
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();


        if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        cout << rule << endl;
        sae.scaleFactor(factor);
        sae.consumeWorkLoad();
    }

    void arrangeResourceScale90(ScalerAgentEnvironment &sae) {
        string rule = "CCC";
        int factor = 0;
        int planCounter = 0;

        auto end = std::chrono::steady_clock::now();

        if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        else if (sae.workLoadStatus == "low" && sae.responseStatus == "good") {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "ok") {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "low" && sae.responseStatus == "bad") {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "good") {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "ok") {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "medium" && sae.responseStatus == "bad") {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "good") {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "ok") {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if (sae.workLoadStatus == "high" && sae.responseStatus == "bad") {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        cout << rule << endl;
        sae.scaleFactor(factor);
        sae.consumeWorkLoad();
    }

    void arrangeResourceScale1_5(ScalerAgentEnvironment &sae) {
        string rule = "CCC";
        int factor = 0;
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();

        if ((sae.workLoadStatus == "low" && sae.responseStatus == "good") || true) {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

         if ((sae.workLoadStatus == "low" && sae.responseStatus == "ok")|| true) {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();


            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


         if ((sae.workLoadStatus == "low" && sae.responseStatus == "bad")|| true) {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

         if ((sae.workLoadStatus == "medium" && sae.responseStatus == "good")|| true) {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);



          if ((sae.workLoadStatus == "medium" && sae.responseStatus == "ok") || true ) {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }}}}}


            if ((sae.workLoadStatus == "medium" && sae.responseStatus == "bad")&& false) {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if ((sae.workLoadStatus == "high" && sae.responseStatus == "good")&& false) {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


        } else if ((sae.workLoadStatus == "high" && sae.responseStatus == "ok")&& false) {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        } else if ((sae.workLoadStatus == "high" && sae.responseStatus == "bad") || true) {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        cout << rule << endl;
        sae.scaleFactor(factor);
        sae.consumeWorkLoad();
    }

    void arrangeResourceScale1_9(ScalerAgentEnvironment &sae) {
        string rule = "CCC";
        int factor = 0;
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();

        if ((sae.workLoadStatus == "low" && sae.responseStatus == "good") || true) {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

         if ((sae.workLoadStatus == "low" && sae.responseStatus == "ok")|| true) {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();


            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


         if ((sae.workLoadStatus == "low" && sae.responseStatus == "bad")|| true) {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

         if ((sae.workLoadStatus == "medium" && sae.responseStatus == "good")|| true) {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);



          if ((sae.workLoadStatus == "medium" && sae.responseStatus == "ok") || true ) {
            rule = "RULE 5";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 5" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


            if ((sae.workLoadStatus == "medium" && sae.responseStatus == "bad") || true) {
            rule = "RULE 6";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 6" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

            if ((sae.workLoadStatus == "high" && sae.responseStatus == "good") || true) {
            rule = "RULE 7";
            factor = 0;

            planCounter++;
            std::cout << "PLAN 7" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);


          if ((sae.workLoadStatus == "high" && sae.responseStatus == "ok") || true) {
            rule = "RULE 8";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 8" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

          if ((sae.workLoadStatus == "high" && sae.responseStatus == "bad") || true) {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }}}}}}}}}

        cout << rule << endl;
        sae.scaleFactor(factor);
        sae.consumeWorkLoad();
    }

    void arrangeResourceScale5_9(ScalerAgentEnvironment &sae) {

        string rule = "CCC";
        int factor = 0;
        int planCounter = 0;
        auto end = std::chrono::steady_clock::now();

        if ((sae.workLoadStatus == "low" && sae.responseStatus == "good")  && false ) {
            rule = "RULE 1";
            factor = -15;

            planCounter++;
            std::cout << "PLAN 1" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);
        } else if ((sae.workLoadStatus == "low" && sae.responseStatus == "ok") && false ) {
            rule = "RULE 2";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 2" << std::endl;
            end = std::chrono::steady_clock::now();


            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);
        } else if ((sae.workLoadStatus == "low" && sae.responseStatus == "bad") && false ) {
            rule = "RULE 3";
            factor = 10;

            planCounter++;
            std::cout << "PLAN 3" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }else if ((sae.workLoadStatus == "medium" && sae.responseStatus == "good")|| true) {
            rule = "RULE 4";
            factor = -10;

            planCounter++;
            std::cout << "PLAN 4" << std::endl;
            end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);
        }


          if ((sae.workLoadStatus == "medium" && sae.responseStatus == "ok") || true ) {
              rule = "RULE 5";
              factor = 0;

              planCounter++;
              std::cout << "PLAN 5" << std::endl;
              end = std::chrono::steady_clock::now();
              long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
              writeExecutionLog(planCounter, end);
          }

          if ((sae.workLoadStatus == "medium" && sae.responseStatus == "bad") || true) {
                rule = "RULE 6";
                factor = 10;

                planCounter++;
                std::cout << "PLAN 6" << std::endl;
                end = std::chrono::steady_clock::now();
                long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
                writeExecutionLog(planCounter, end);
            }
          if ((sae.workLoadStatus == "high" && sae.responseStatus == "good") || true) {
                rule = "RULE 7";
                factor = 0;

                planCounter++;
                std::cout << "PLAN 7" << std::endl;
                end = std::chrono::steady_clock::now();
                long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
                writeExecutionLog(planCounter, end);
          }

          if ((sae.workLoadStatus == "high" && sae.responseStatus == "ok") || true) {
              rule = "RULE 8";
              factor = 10;

              planCounter++;
              std::cout << "PLAN 8" << std::endl;
              end = std::chrono::steady_clock::now();
              long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
              writeExecutionLog(planCounter, end);
          }

          if ((sae.workLoadStatus == "high" && sae.responseStatus == "bad") || true) {
            rule = "RULE 9";
            factor = 15;

            planCounter++;
            std::cout << "PLAN 9" << std::endl;
             end = std::chrono::steady_clock::now();
            long long endTime = std::chrono::duration_cast<std::chrono::nanoseconds>(end.time_since_epoch()).count();
            writeExecutionLog(planCounter, end);

        }

        cout << rule << endl;
        sae.scaleFactor(factor);
        sae.consumeWorkLoad();
    }





};

int ScalerAgent::currentWorkLoad = 0;
int ScalerAgent::arrivedTurn = 0;
string ScalerAgent::responseTime = "none";
string ScalerAgent::workLoad = "none";
int ScalerAgent::arrivedWorkload;

int ScalerAgentEnvironment::sumWorkload = 0;

int main() {



    ScalerAgent sa;
    ScalerAgentEnvironment sae;
    try {


        sae.init();
    } catch (...) {
        cerr << "Initialization error" << endl;
        return 1;
    }

    while (true) {
        this_thread::sleep_for(chrono::milliseconds(1));

        sa.startTime = std::chrono::steady_clock::now();


        if (sae.workLoad == 0 || sae.arrivalTurns == 0) {
            sae.getWorkLoadBool();

            sae.checkWorkLoadBool();

            sa.arrangeResourceScale5_9(sae);
            cout<< "cont 1"<< endl;
        } else if (sae.workLoad != 0) {


            sae.checkWorkLoadBool();
            sa.arrangeResourceScale5_9(sae);
            cout<< "cont 2"<< endl;
        }
    }

    return 0;
}