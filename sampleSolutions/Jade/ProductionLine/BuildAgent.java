package JadeProductionLine;


import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class BuildAgent extends Agent {
    private int buildStatus = 0;

    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            public void action() {


                addBehaviour(new CyclicBehaviour() {
                    public void action() {
                        ACLMessage msg = receive(); // Receive a message
                        if (msg != null) {

                            System.out.println(" Message:"+msg.getContent());

                            if ("build".equalsIgnoreCase(String.valueOf(msg.getContent()))) {

                                System.out.println("M= " + buildStatus);
                                buildStatus++;
                                System.out.println("K= " + buildStatus);
                                checkState();
                            }
                        } else {
                            block(); // Block until a new message arrives
                        }
                    }
                });

            }
        });
    }

    private void checkState() {
        if (buildStatus == 1) {
            System.out.println("First Red Press " + buildStatus);
        } else if (buildStatus == 2) {
            System.out.println("Second Red-Red Press " + buildStatus);
            eject();
        }
    }

    private void eject() {
        System.out.println("Eject");
        buildStatus = 0;
    }
}
