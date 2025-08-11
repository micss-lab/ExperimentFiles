#include "BuildAgent.hpp"

//int BuildAgent::buildStatus = 0;

using namespace std;


int BuildAgent::buildStatus = 0;

BuildAgent::BuildAgent() {
    setup();

}

void BuildAgent::setup() {
    cout << "BuildAgent setup complete." << endl;


}

void BuildAgent::onMessageReceived(const string& messageContent) {
    if (!messageContent.empty()) {
        cout << "Message: " << messageContent << endl;

        if (messageContent == "build") {
            buildStatus++;
            cout << "K= " << buildStatus << endl;
            checkState();
        }
    } else {
        cout << "No message received." << endl;
    }
}

void BuildAgent::checkState() {
    if (buildStatus == 1) {
        cout << "First Red Press " << buildStatus << endl;
    } else if (buildStatus == 2) {
        cout << "Second Red-Red Press " << buildStatus << endl;
        eject();
    }
}

void BuildAgent::eject() {
    cout << "Eject" << endl;
    buildStatus = 0;
}

int BuildAgent::getBuildStatus() const {
    return buildStatus;
}

void BuildAgent::setBuildStatus(int status) {
    buildStatus = status;
}
