package JadeNetworkScaler;


import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;


public class ScalerAgent extends Agent {

    ScalerAgentEnvironment sae = new ScalerAgentEnvironment();

    static int currentWorkLoad = 0;
    static int arrivedTurn = 0;
    static String responseTime="none";
    static String workLoad="none";
    static int arrivedWorkload;

    public long startTime;




    @Override
    protected void setup() {

        try {
            sae.init();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Add the behavior directly in the setup method
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                try {

                    startTime = System.nanoTime();

                    if ((currentWorkLoad==0)||arrivedTurn==0) {

                            sae.getWorkLoadBool();
                            block(1);
                            sae.checkWorkLoadBool();
                            block(1);
                            this.arrangeResourceScale5_9();

                    } else if (currentWorkLoad!=0) {

                        sae.checkWorkLoadBool();
                        this.arrangeResourceScale5_9();

                    }





                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }

            private void writeExecutionLog(int planNumber, long endTime) {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                long end = System.nanoTime();
                long elapsed = (end - startTime) / 1_000_000; // convert nanoseconds to milliseconds



                try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\WCET\\Yeni Olcumler\\NetworkScaler\\Jade\\output.txt", true))) {
                    //  try (BufferedWriter writer = new BufferedWriter(new FileWriter("/home/robot/productionLineEmpirical/src/java/output.txt", true))) {
                    writer.write("PLANN " + planNumber + " | Execution time (ms): " + elapsed +  "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void arrangeResourceScale() {

                String rule = "";
                int factor = 0;

                int planCounter = 0;
                long endTime;


                if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }



                System.out.println(rule);
                sae.scaleFactor(factor);
                sae.consumeWorkLoad();
            }


            public void arrangeResourceScale27() {

                String rule = "";
                int factor = 0;

                int planCounter = 0;
                long endTime;


                if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }



                System.out.println(rule);
                sae.scaleFactor(factor);
                sae.consumeWorkLoad();
            }

            public void arrangeResourceScale90() {

                String rule = "";
                int factor = 0;

                int planCounter = 0;
                long endTime;


                if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);




                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 5";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
                    rule = "RULE 7";
                    factor = 0;


                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }



                System.out.println(rule);
                sae.scaleFactor(factor);
                sae.consumeWorkLoad();
            }

            public  void arrangeResourceScale1_5(){


                String rule = "";
                int factor = 0;

                int planCounter = 0;
                long endTime;


                if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) || true) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);


                    if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok"))|| true) {
                        rule = "RULE 2";
                        factor = -10;

                        planCounter++;
                        System.out.println("PLAN 2");
                        endTime = System.nanoTime();
                        writeExecutionLog(planCounter, endTime);


                        if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad"))|| true) {
                            rule = "RULE 3";
                            factor = 10;

                            planCounter++;
                            System.out.println("PLAN 3");
                            endTime = System.nanoTime();
                            writeExecutionLog(planCounter, endTime);


                            if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) || true ) {
                                rule = "RULE 4";
                                factor = -10;

                                planCounter++;
                                System.out.println("PLAN 4");
                                endTime = System.nanoTime();
                                writeExecutionLog(planCounter, endTime);


                                if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok"))|| true) {
                                    rule = "RULE 5";
                                    factor = 0;

                                    planCounter++;
                                    System.out.println("PLAN 5");
                                    endTime = System.nanoTime();
                                    writeExecutionLog(planCounter, endTime);
                                }
                            }
                        }
                    }
                }

                if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) && false)  {
                    rule = "RULE 6";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) && false) {
                    rule = "RULE 7";
                    factor = 0;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) && false) {
                    rule = "RULE 8";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                } else if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) || true) {
                    rule = "RULE 9";
                    factor = 15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                System.out.println(rule);
                sae.scaleFactor(factor);
                sae.consumeWorkLoad();


            }


            public void arrangeResourceScale1_9() {

                String rule = "";
                int factor = 0;

                int planCounter = 0;
                long endTime;

                if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) || true) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                    if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) || true) {
                        rule = "RULE 2";
                        factor = -10;

                        planCounter++;
                        System.out.println("PLAN 2");
                        endTime = System.nanoTime();
                        writeExecutionLog(planCounter, endTime);

                        if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) || true) {
                            rule = "RULE 3";
                            factor = 10;

                            planCounter++;
                            System.out.println("PLAN 3");
                            endTime = System.nanoTime();
                            writeExecutionLog(planCounter, endTime);

                            if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) || true) {
                                rule = "RULE 4";
                                factor = -10;

                                planCounter++;
                                System.out.println("PLAN 4");
                                endTime = System.nanoTime();
                                writeExecutionLog(planCounter, endTime);

                                if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) || true) {
                                    rule = "RULE 5";
                                    factor = 0;

                                    planCounter++;
                                    System.out.println("PLAN 5");
                                    endTime = System.nanoTime();
                                    writeExecutionLog(planCounter, endTime);

                                    if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) || true) {
                                        rule = "RULE 6";
                                        factor = 10;

                                        planCounter++;
                                        System.out.println("PLAN 6");
                                        endTime = System.nanoTime();
                                        writeExecutionLog(planCounter, endTime);

                                        if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) || true) {
                                            rule = "RULE 7";
                                            factor = 0;

                                            planCounter++;
                                            System.out.println("PLAN 7");
                                            endTime = System.nanoTime();
                                            writeExecutionLog(planCounter, endTime);

                                            if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) || true) {
                                                rule = "RULE 8";
                                                factor = 10;

                                                planCounter++;
                                                System.out.println("PLAN 8");
                                                endTime = System.nanoTime();
                                                writeExecutionLog(planCounter, endTime);

                                                if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) || true) {
                                                    rule = "RULE 9";
                                                    factor = 15;

                                                    planCounter++;
                                                    System.out.println("PLAN 9");
                                                    endTime = System.nanoTime();
                                                    writeExecutionLog(planCounter, endTime);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                System.out.println(rule);
                sae.scaleFactor(factor);
                sae.consumeWorkLoad();
            }

            public void arrangeResourceScale5_9() {

                String rule = "";
                int factor = 0;

                int planCounter = 0;
                long endTime;

                // 1-4 arası normal, iç içe olmayan koşullar
                if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good") && false )) {
                    rule = "RULE 1";
                    factor = -15;

                    planCounter++;
                    System.out.println("PLAN 1");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

               else if ((ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok"))&& false  ){
                    rule = "RULE 2";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 2");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else if( (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) && false ) {
                    rule = "RULE 3";
                    factor = 10;

                    planCounter++;
                    System.out.println("PLAN 3");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                else  if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) || true ) {
                    rule = "RULE 4";
                    factor = -10;

                    planCounter++;
                    System.out.println("PLAN 4");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);
                }

                // 5-9 arası iç içe if blokları
                if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) || true) {
                    rule = "RULE 5";
                    factor = 0;

                    planCounter++;
                    System.out.println("PLAN 5");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                    if ((ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) || true) {
                        rule = "RULE 6";
                        factor = 10;

                        planCounter++;
                        System.out.println("PLAN 6");
                        endTime = System.nanoTime();
                        writeExecutionLog(planCounter, endTime);

                        if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) || true) {
                            rule = "RULE 7";
                            factor = 0;

                            planCounter++;
                            System.out.println("PLAN 7");
                            endTime = System.nanoTime();
                            writeExecutionLog(planCounter, endTime);

                            if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) || true) {
                                rule = "RULE 8";
                                factor = 10;

                                planCounter++;
                                System.out.println("PLAN 8");
                                endTime = System.nanoTime();
                                writeExecutionLog(planCounter, endTime);

                                if ((ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) || true) {
                                    rule = "RULE 9";
                                    factor = 15;

                                    planCounter++;
                                    System.out.println("PLAN 9");
                                    endTime = System.nanoTime();
                                    writeExecutionLog(planCounter, endTime);
                                }
                            }
                        }
                    }
                }

                System.out.println(rule);
                sae.scaleFactor(factor);
                sae.consumeWorkLoad();
            }






        });
    }






}