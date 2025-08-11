//
// Created by Burak on 11/05/2025.
//





#include <iostream>
#include <string>
#include "PushAgent.hpp"

using namespace std;


#include <iostream>

using namespace std;

void PushAgent::setup() {
    cout << "Agent is ready." << endl;
}

void PushAgent::listenForMessages(const string& messageContent) {
    if (!messageContent.empty() && messageContent == "push") {
        cout << "Message: " << messageContent << endl;
        productPushed();
    } else {
        cout << "Waiting for message..." << endl;
    }
}

void PushAgent::productPushed() {
    cout << "The Product is Pushed away." << endl;
}




