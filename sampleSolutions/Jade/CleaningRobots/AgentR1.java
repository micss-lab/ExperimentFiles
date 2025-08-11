package JadeCleaningRobots;


import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;


public class AgentR1 extends Agent {

    static public boolean continueSignal = false;
    private String batteryPower = "full";  // Assume initially full
    private String vacuumBag = "empty";  // Assume initially empty
    private int x , y ;  // Initial position of the agent

    public static long startTimeR1;

    AgentFeatures agent1 = new AgentFeatures("r1",containerMain.env.model);

    @Override
    protected void setup() {
        // Add the behavior directly in the setup method
        addBehaviour(new TickerBehaviour(this,1) {
            @Override
            public void onTick() {
                try {

                    startTimeR1 = System.nanoTime();
                    x=agent1.x;
                    y=agent1.y;

                    // Check agent status


                    // Check if the agent needs to continue or take action
                    if (((x != 6 || y != 7) || AgentR3.continueSignal) && !batteryPower.equals("depleted") && !vacuumBag.equals("full")) {
                        agent1.checkStatus();
                        agent1.arrangeVacuumPower15_27("Ag1");
                        try {
                            agent1.nextSlot();
                        } catch (Exception e) {
                        //    throw new RuntimeException(e);
                        }
                    } else if (batteryPower.equals("depleted") || vacuumBag.equals("full")) {
                        System.out.println("Agent 1 - Finished 1");
                        continueSignal = true;
                    }

                    if (x == 6 && y == 7 && !batteryPower.equals("depleted") && !vacuumBag.equals("full")) {
                        System.out.println("Agent 1 - Finished 2 - Devam edebilir - AG 3 Signal");
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