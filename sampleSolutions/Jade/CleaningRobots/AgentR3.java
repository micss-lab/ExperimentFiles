package JadeCleaningRobots;


import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class AgentR3 extends Agent {

    static public boolean continueSignal = false;
    private String batteryPower = "full";  // Assume initially full
    private String vacuumBag = "empty";  // Assume initially empty
    private int x, y ;  // Initial position of the agent


    public static long startTimeR3;

    AgentFeatures agent3 = new AgentFeatures("r3",containerMain.env.model);


    @Override
    protected void setup() {
        // Add the behavior directly in the setup method
        addBehaviour(new TickerBehaviour(this,1) {
            @Override
            public void onTick() {
                try {

                    startTimeR3 = System.nanoTime();

                    x=agent3.x;
                    y=agent3.y;




                    // Check if the agent needs to continue or take action
                    if (( (x != 8 || y != 7) || AgentR1.continueSignal) && !(batteryPower.equals("depleted")) && !(vacuumBag.equals("full")) ) {
                        agent3.checkStatus();
                        agent3.arrangeVacuumPower15_27("Ag3");
                        try {
                            agent3.nextSlotRev();
                        } catch (Exception e) {
                        //    throw new RuntimeException(e);
                        }
                    } else if (batteryPower.equals("depleted") || vacuumBag.equals("full")) {
                        System.out.println("Agent 3 - Finished 1");
                        continueSignal = true;
                    }

                    if (x == 8 && y == 7 && !batteryPower.equals("depleted") && !vacuumBag.equals("full")) {
                        System.out.println("Agent 3 - Finished 2 - Devam edebilir");
                        continueSignal = true;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        });
    }


}

