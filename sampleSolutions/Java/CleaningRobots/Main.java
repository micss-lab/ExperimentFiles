package JavaCleaningRobots;

public class Main {


    public static void main(String[] args) throws Exception {

        System.out.println("Starting....");

        MarsEnv env = new MarsEnv();
        env.init(new String[]{"10","5","5300","boolean"}); // 7 5 4700  // 10 5 4700 boolean - R3 exactly finishes at 7 6
        Agent agent1 = new Agent("r1",env.model);
        Agent agent3 = new Agent("r3",env.model);

        boolean stop_signal_agent1 = false;
        boolean stop_signal_agent3 = false;






        //   while ((agent1.vacuumBag.equals("full")||agent1.batteryPower.equals("depleted"))) {

        agent1.checkStatus();
        Thread.sleep(1);
        agent3.checkStatus();
        Thread.sleep(1);

        for (int i = 0; i < 196; i++) {






            ////////////////////////////////////// AGENT 1 /////////////////////////////////////////////////////////////////////////////////////////

            if ( ( ((agent1.x != 6 || agent1.y != 7) || agent3.continue_signal) && !(agent1.batteryPower.equals("depleted")) && !(agent1.vacuumBag.equals("full")) ) )
            {
                agent1.startTime = System.nanoTime();
                agent1.checkStatus();
                Thread.sleep(1);
                agent1.arrangeVacuumPower1_15(agent1);
                agent1.nextSlot();


            }

            else if ((agent1.batteryPower.equals("depleted")) || ((agent1.vacuumBag.equals("full"))))
            {
                System.out.println("Agent 1 - Finished 1");

                agent1.continue_signal = true;
            }

            if ((agent1.x == 6 && agent1.y == 7) && !(agent1.batteryPower.equals("depleted")) && !((agent1.vacuumBag.equals("full"))))
            {
                System.out.println("Agent 1 - Finished 2 - Devam edebilir - AG 3 Signal" +agent3.continue_signal);

                agent1.continue_signal = true;
            }


           /* else if ((!(agent1.batteryPower.equals("depleted"))) && (!(agent1.vacuumBag.equals("full")))
                    && agent3.continue_signal )
            {
                agent1.checkStatus();
                Thread.sleep(100);
                agent1.arrangeVacuumPower();
                agent1.nextSlot();

              //  agent1.continue_signal = true;
            }*/



     ////////////////////////////////////// AGENT 3 /////////////////////////////////////////////////////////////////////////////////////////
// (! ((agent3.x == 8 && agent3.y == 7) || (agent3.batteryPower.equals("depleted")) || (agent3.vacuumBag.equals("full"))) )
            if ( ( ((agent3.x != 8 || agent3.y != 7) || agent1.continue_signal) && !(agent3.batteryPower.equals("depleted")) && !(agent3.vacuumBag.equals("full")) ) )
            {
                agent3.startTime = System.nanoTime();

                agent3.checkStatus();
                Thread.sleep(1);
                agent3.arrangeVacuumPower1_15(agent3);
                agent3.nextSlotRev();
            }

            else if ((agent3.batteryPower.equals("depleted")) || ((agent3.vacuumBag.equals("full"))))
            {
                System.out.println("Agent 3 - Finished 1");

                agent3.continue_signal = true;
            }

            if ((agent3.x == 8 && agent3.y == 7) && !(agent3.batteryPower.equals("depleted")) && !((agent3.vacuumBag.equals("full"))))
            {
                System.out.println("Agent 3 - Finished 2 - Devam edebilir");

                agent3.continue_signal = true;
            }


            /*else if ((!(agent3.batteryPower.equals("depleted"))) && (!(agent3.vacuumBag.equals("full")))
                    && (agent1.continue_signal) )
            {
                agent3.checkStatus();
                Thread.sleep(100);
                agent3.arrangeVacuumPower();
                agent3.nextSlotRev();
                //   agent3.continue_signal = true;

            }*/




        Thread.sleep(100);
}


       // env.model.setAgPos(0, 4, 4);

    }
}
