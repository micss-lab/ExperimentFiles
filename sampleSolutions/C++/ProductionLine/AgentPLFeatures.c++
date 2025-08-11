
#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <sstream>

#include "AgentPLFeatures.hpp"







// int AgentPLFeatures::productCounter = 0;
//
//
//

int AgentPLFeatures::productCounter = 0; // Başlangıç değeri
std::string AgentPLFeatures::redVal = "none";  // Başlangıç değeri
std::string AgentPLFeatures::greenVal = "none";  // Başlangıç değeri
std::string AgentPLFeatures::blueVal = "none";  // Başlangıç değeri
std::vector<SampleData> AgentPLFeatures::dataList;  // Başlangıç değeri (boş vektör)



 void AgentPLFeatures::boolColourSensor() {
    if (productCounter >= dataList.size()) {
        std::cerr << "Error: Product counter exceeds data list size.\n";
        return;
    }

    const SampleData &data = dataList[productCounter];
    float red = data.r;
    float green = data.g;
    float blue = data.b;

    productCounter++;

    std::cout << "RED: " << red << " Green: " << green << " Blue: " << blue << '\n';
    boolGroup(red, green, blue);
}


void AgentPLFeatures::parseDataFile() {







       std::cout<<"Parsing" << '\n';

    std::string inputFilePath = "C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\"
                                "Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\Randoms\\SampleInput1.txt";

    std::ifstream file(inputFilePath);
    if (!file.is_open()) {
        std::cerr << "Error opening file: " << inputFilePath << '\n';
        return;
    }

    std::string line;
    while (std::getline(file, line)) {
        if (line.empty()) continue;

        std::istringstream stream(line);
        std::string part;
        std::vector<std::string> parts;

        while (stream >> part) {
            parts.push_back(part);
        }

        int id = std::stoi(parts[0].substr(2)); // Extract ID from "N=xxx"
        float r = std::stof(parts[1]);
        float g = std::stof(parts[2]);
        float b = std::stof(parts[3]);
        int number = std::stoi(parts[4]);
        std::string color = parts.size() > 5 ? parts[5] : ""; // Assumes color name starts from the 5th part

        for (size_t i = 6; i < parts.size(); ++i) {
            color += " " + parts[i];
        }

        dataList.push_back(SampleData(id, r, g, b, number, color));
    }

    // Display the parsed data
    std::cout << "Parsed Data:\n";
    for (const auto &data : dataList) {
        data.print();
    }
}

void AgentPLFeatures::boolGroup(float red, float green, float blue) {
    // Red value assignment based on thresholds
    if (red <= 60.0f) {
        redVal = "low";
    } else if (red > 60.0f && red <= 115.0f) {
        redVal = "medium";
    } else {
        redVal = "high";
    }

    // Green value assignment based on thresholds
    if (green <= 20.0f) {
        greenVal = "low";
    } else if (green > 20.0f && green <= 30.0f) {
        greenVal = "medium";
    } else if (green > 30.0f && green <= 50.0f) {
        greenVal = "high";
    } else if (green > 50.0f && green <= 80.0f) {
        greenVal = "veryhigh";
    } else if (green > 80.0f && green <= 110.0f) {
        greenVal = "ultralow";
    } else if (green > 110.0f && green <= 145.0f) {
        greenVal = "ultramedium";
    } else {
        greenVal = "ultrahigh";
    }

    // Blue value assignment based on thresholds
    if (blue <= 10.5f) {
        blueVal = "low";
    } else if (blue > 10.5f && blue <= 19.5f) {
        blueVal = "medium";
    } else {
        blueVal = "high";
    }

    // Placeholder for handling the output with SortAgent
    // You might want to add appropriate functionality here for setting the static properties

}
