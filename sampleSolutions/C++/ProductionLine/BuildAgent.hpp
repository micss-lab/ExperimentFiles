#ifndef BUILDAGENT_HPP
#define BUILDAGENT_HPP

#include <iostream>
#include <string>
using namespace std;
class BuildAgent {


public:

     static int buildStatus;
        BuildAgent();

        void setup();

      static void onMessageReceived(const string& messageContent);
      static void checkState();
       static void eject();

       int getBuildStatus() const;
       void setBuildStatus(int status);
};

#endif // BUILDAGENT_HPP
