package JadeProductionLine;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class PushAgent extends Agent {
    protected void setup() {
        System.out.println("Agent " + getLocalName() + " is ready.");

        // Listening for messages
        addBehaviour(new CyclicBehaviour() {
            public void action() {
                ACLMessage msg = receive(); // Receive a message
                if (msg != null) {

                        System.out.println(" Message:"+msg.getContent());

                        if ("push".equalsIgnoreCase(String.valueOf(msg.getContent()))) {
                            addBehaviour(new OneShotBehaviour() {
                                public void action() {
                                    System.out.println("The Product is Pushed away.");
                                }
                            });
                        }




                } else {
                    block(); // Block until a new message arrives
                }
            }
        });
    }
}