//
// Created by Burak on 11/05/2025.
//

#ifndef PUSHAGENT_HPP
#define PUSHAGENT_HPP


#include <iostream>
#include <string>

class PushAgent {
public:
    void setup();

    static void listenForMessages(const std::string& messageContent);
    static void productPushed();
};





#endif //PUSHAGENT_HPP
