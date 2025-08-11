package JadeProductionLine;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SortAgent extends Agent {
    private boolean dropped = false;
    private boolean shredEndSent = false;
    private int count_time = 0;
    private boolean reverse_triggered = false;
    private boolean colorDecided = false;

    public static String isRed="none";
    public static String isGreen="none";
    public static String isBlue="none";
    public long startTime;

    protected void setup() {
        System.out.println("SortAgent " + getLocalName() + " started.");

        addBehaviour(new ColorDecider());
    }

    private void writeExecutionLog(int planNumber, long endTime) {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime();
        long elapsed = (end - startTime) / 1_000_000; // convert nanoseconds to milliseconds



          try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\WCET\\Yeni Olcumler\\Jade\\PC\\output.txt", true))) {
      //  try (BufferedWriter writer = new BufferedWriter(new FileWriter("/home/robot/productionLineEmpirical/src/java/output.txt", true))) {
            writer.write("PLANN " + planNumber + " | Execution time (ms): " + elapsed +  "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ColorDecider extends Behaviour {


        public void action() {



            try {
               Thread.sleep(1);

                System.out.println("BASLAAAAA");
                startTime = System.nanoTime();

                AgentPLFeatures.boolColourSensor();
                decide360(isRed,isGreen,isBlue);



                Thread.sleep(1);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



        }

        @Override
        public boolean done() {

            return AgentPLFeatures.productCounter>=64;
        }

        public int onEnd() {
            System.out.println("Products are finished");

            return super.onEnd();
        }

    }










        // ORJ
        private void decideColor2(String red, String green, String blue) {

            if (isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) {
                System.out.println("RED 1 - MEDIUM");
                sendBuildMessage();
            } else if (isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) {
                System.out.println("RED 2 - MEDIUM");
                sendBuildMessage();
            } else if (isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) {
                System.out.println("RED 3 - MEDIUM");
                sendBuildMessage();
            } else if (isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) {
                System.out.println("RED 7 - MEDIUM");
                sendBuildMessage();
            } else if (isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) {
                System.out.println("RED 4 - MEDIUM");
                sendBuildMessage();
            } else if (isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) {
                System.out.println("RED 9 - MEDIUM");
                sendBuildMessage();
            } else if (isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low")) {
                System.out.println("RED 10 - HIGH");
                sendBuildMessage();
            } else if (isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("PURPLE 4 - MEDIUM");
            } else if (isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("PURPLE 5 - MEDIUM");
            } else if (isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("PURPLE 2 - HIGH");
            } else if (isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) {
                sendPushMessage();
                System.out.println("PURPLE 5 - MEDIUM");
            } else if (isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) {
                sendPushMessage();
                System.out.println("PURPLE 7 - MEDIUM");
            } else if (isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) {
                sendPushMessage();
                System.out.println("PURPLE 3 - HIGH");
            } else if (isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high")) {
                sendPushMessage();
                System.out.println("PURPLE 2 - HIGH");
            } else if (isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("PURPLE 13 - MEDIUM");
            } else if (isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) {
                sendPushMessage();
                System.out.println("PURPLE 14 - MEDIUM");
            } else if (isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) {
                sendPushMessage();
                System.out.println("PURPLE 15 - MEDIUM");
            } else if (isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("LIGHT GREEN 1");
            } else if (isRed.equals("medium") && isGreen.equals("ulralow") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("LIGHT GREEN 2");
            } else if (isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) {
                sendPushMessage();
                System.out.println("LIGHT GREEN 6");
            } else if (isRed.equals("low") && isGreen.equals("ulralow") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("MIDDLE GREEN 1");
            } else if (isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) {
                sendPushMessage();
                System.out.println("MIDDLE GREEN 2");
            } else if (isRed.equals("low") && isGreen.equals("ulralow") && isBlue.equals("low")) {
                sendPushMessage();
                System.out.println("MIDDLE GREEN 3");
            } else if (isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("MIDDLE GREEN 4");
            } else if (isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("MIDDLE GREEN 5");
            } else if (isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) {
                sendPushMessage();
                System.out.println("MIDDLE GREEN 6");
            } else if (isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) {
                sendPushMessage();
                System.out.println("KKKKKKKKKK DARK GREEN 1 $$$$$");
            } else if (isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) {
                sendPushMessage();
                System.out.println("KKKKKKKKKK DARK GREEN 2 $$$$$");
            } else if (isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) {
                sendPushMessage();
                System.out.println("KKKKKKKKKK DARK GREEN 3 $$$$$");
            } else {
                System.out.println("UNKNOWN COLOR");
            }
        }
    //



//////////////////////////


    private void decideColor1_18(String red, String green, String blue) {



        int planCounter = 0;
        long endTime;


        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) || true) {
            planCounter++;
            System.out.println("PLAN 1");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);

            if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) || true) {
                planCounter++;
                System.out.println("PLAN 2");
                endTime = System.nanoTime();
                writeExecutionLog(planCounter, endTime);

                if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) || true) {
                    planCounter++;
                    System.out.println("PLAN 3");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                    if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) || true) {
                        planCounter++;
                        System.out.println("PLAN 4");
                        endTime = System.nanoTime();
                        writeExecutionLog(planCounter, endTime);

                        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) || true) {
                            planCounter++;
                            System.out.println("PLAN 5");
                            endTime = System.nanoTime();
                            writeExecutionLog(planCounter, endTime);

                            if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                planCounter++;
                                System.out.println("PLAN 6");
                                endTime = System.nanoTime();
                                writeExecutionLog(planCounter, endTime);

                                if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                    planCounter++;
                                    System.out.println("PLAN 7");
                                    endTime = System.nanoTime();
                                    writeExecutionLog(planCounter, endTime);

                                    if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) || true) {
                                        planCounter++;
                                        System.out.println("PLAN 8");
                                        endTime = System.nanoTime();
                                        writeExecutionLog(planCounter, endTime);

                                        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) || true) {
                                            planCounter++;
                                            System.out.println("PLAN 9");
                                            endTime = System.nanoTime();
                                            writeExecutionLog(planCounter, endTime);

                                            if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) || true) {
                                                planCounter++;
                                                System.out.println("PLAN 10");
                                                endTime = System.nanoTime();
                                                writeExecutionLog(planCounter, endTime);

                                                if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) || true) {
                                                    planCounter++;
                                                    System.out.println("PLAN 11");
                                                    endTime = System.nanoTime();
                                                    writeExecutionLog(planCounter, endTime);

                                                    if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) || true) {
                                                        planCounter++;
                                                        System.out.println("PLAN 12");
                                                        endTime = System.nanoTime();
                                                        writeExecutionLog(planCounter, endTime);

                                                        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) || true) {
                                                            planCounter++;
                                                            System.out.println("PLAN 13");
                                                            endTime = System.nanoTime();
                                                            writeExecutionLog(planCounter, endTime);

                                                            if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high")) || true) {
                                                                planCounter++;
                                                                System.out.println("PLAN 14");
                                                                endTime = System.nanoTime();
                                                                writeExecutionLog(planCounter, endTime);

                                                                if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) || true) {
                                                                    planCounter++;
                                                                    System.out.println("PLAN 15");
                                                                    endTime = System.nanoTime();
                                                                    writeExecutionLog(planCounter, endTime);

                                                                    if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) || true) {
                                                                        planCounter++;
                                                                        System.out.println("PLAN 16");
                                                                        endTime = System.nanoTime();
                                                                        writeExecutionLog(planCounter, endTime);

                                                                        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) || true) {
                                                                            planCounter++;
                                                                            System.out.println("PLAN 17");
                                                                            endTime = System.nanoTime();
                                                                            writeExecutionLog(planCounter, endTime);

                                                                            if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) || true) {
                                                                                planCounter++;
                                                                                System.out.println("PLAN 18");
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
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium"))  && false  ) {
            planCounter++;
            System.out.println("PLAN 19");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false  ) {
            planCounter++;
            System.out.println("PLAN 20");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false  ) {
            planCounter++;
            System.out.println("PLAN 21");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false ) {
            planCounter++;
            System.out.println("PLAN 22");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low")) && false ) {
            planCounter++;
            System.out.println("PLAN 23");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false ) {
            planCounter++;
            System.out.println("PLAN 24");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium"))  && false ) {
            planCounter++;
            System.out.println("PLAN 25");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false ) {
            planCounter++;
            System.out.println("PLAN 26");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low"))  && false ) {
            planCounter++;
            System.out.println("PLAN 27");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false ) {
            planCounter++;
            System.out.println("PLAN 28");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false ) {
            planCounter++;
            System.out.println("PLAN 29");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("low"))  && false ) {
            planCounter++;
            System.out.println("PLAN 30");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("verylow")) && false ) {
            planCounter++;
            System.out.println("PLAN 31");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false ) {
            planCounter++;
            System.out.println("PLAN 32");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("verylow") && isGreen.equals("ultrahigh") && isBlue.equals("medium")) || true) {
            planCounter++;
            System.out.println("PLAN 33");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("verylow") && isGreen.equals("ultrahigh") && isBlue.equals("ultrahigh"))&& false ) {
            planCounter++;
            System.out.println("PLAN 34");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("verylow") && isGreen.equals("ultramedium") && isBlue.equals("ultrahigh"))&& false ) {
            planCounter++;
            System.out.println("PLAN 35");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow"))&& false ) {
            planCounter++;
            System.out.println("PLAN 36");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        System.out.println("PLAN C " + planCounter);
        planCounter = 0;

    }


    private void decideColor40rec(String isRed, String isGreen, String isBlue) {
        int planCounter = 0;
        long endTime;

        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) || true) {

            planCounter++;
            System.out.println("PLAN 1");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter,  endTime);


            if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) || true) {
                planCounter++;
                System.out.println("PLAN 2");
                endTime = System.nanoTime();
                writeExecutionLog(planCounter,  endTime);


                if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) || true) {
                    planCounter++;
                    System.out.println("PLAN 3");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter,  endTime);

                    if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) || true) {
                        planCounter++;
                        System.out.println("PLAN 4");
                        endTime = System.nanoTime();
                        writeExecutionLog(planCounter,  endTime);

                        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) || true) {
                            planCounter++;
                            System.out.println("PLAN 5");
                            endTime = System.nanoTime();
                            writeExecutionLog(planCounter,  endTime);

                            if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                planCounter++;
                                System.out.println("PLAN 6");
                                endTime = System.nanoTime();
                                writeExecutionLog(planCounter,  endTime);

                                if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                    planCounter++;
                                    System.out.println("PLAN 7");
                                    endTime = System.nanoTime();
                                    writeExecutionLog(planCounter,  endTime);

                                    if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) || true) {
                                        planCounter++;
                                        System.out.println("PLAN 8");
                                        endTime = System.nanoTime();
                                        writeExecutionLog(planCounter,  endTime);

                                        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) || true) {
                                            planCounter++;
                                            System.out.println("PLAN 9");
                                            endTime = System.nanoTime();
                                            writeExecutionLog(planCounter,  endTime);

                                            if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) || true) {
                                                planCounter++;
                                                System.out.println("PLAN 10");
                                                endTime = System.nanoTime();
                                                writeExecutionLog(planCounter,  endTime);

                                                if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) || true) {
                                                    planCounter++;
                                                    System.out.println("PLAN 11");
                                                    endTime = System.nanoTime();
                                                    writeExecutionLog(planCounter,  endTime);

                                                    if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) || true) {
                                                        planCounter++;
                                                        System.out.println("PLAN 12");
                                                        endTime = System.nanoTime();
                                                        writeExecutionLog(planCounter,  endTime);

                                                        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) || true) {
                                                            planCounter++;
                                                            System.out.println("PLAN 13");
                                                            endTime = System.nanoTime();
                                                            writeExecutionLog(planCounter,  endTime);

                                                            if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high")) || true) {
                                                                planCounter++;
                                                                System.out.println("PLAN 14");
                                                                endTime = System.nanoTime();
                                                                writeExecutionLog(planCounter,  endTime);

                                                                if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) || true) {
                                                                    planCounter++;
                                                                    System.out.println("PLAN 15");
                                                                    endTime = System.nanoTime();
                                                                    writeExecutionLog(planCounter,  endTime);

                                                                    if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) || true) {
                                                                        planCounter++;
                                                                        System.out.println("PLAN 16");
                                                                        endTime = System.nanoTime();
                                                                        writeExecutionLog(planCounter,  endTime);

                                                                        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) || true) {
                                                                            planCounter++;
                                                                            System.out.println("PLAN 17");
                                                                            endTime = System.nanoTime();
                                                                            writeExecutionLog(planCounter,  endTime);

                                                                            if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) || true) {
                                                                                planCounter++;
                                                                                System.out.println("PLAN 18");
                                                                                endTime = System.nanoTime();
                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) || true) {
                                                                                    planCounter++;
                                                                                    System.out.println("PLAN 19");
                                                                                    endTime = System.nanoTime();
                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                    if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) || true) {
                                                                                        planCounter++;
                                                                                        System.out.println("PLAN 20");
                                                                                        endTime = System.nanoTime();
                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) || true) {
                                                                                            planCounter++;
                                                                                            System.out.println("PLAN 21");

                                                                                            endTime = System.nanoTime();
                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                            if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) || true) {
                                                                                                planCounter++;
                                                                                                System.out.println("PLAN 22");

                                                                                                endTime = System.nanoTime();
                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low")) || true) {
                                                                                                    planCounter++;
                                                                                                    System.out.println("PLAN 23");

                                                                                                    endTime = System.nanoTime();
                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                    if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) || true) {
                                                                                                        planCounter++;
                                                                                                        System.out.println("PLAN 24");

                                                                                                        endTime = System.nanoTime();
                                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                                        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) || true) {
                                                                                                            planCounter++;
                                                                                                            System.out.println("PLAN 25");

                                                                                                            endTime = System.nanoTime();
                                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                                            if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) || true) {
                                                                                                                planCounter++;
                                                                                                                System.out.println("PLAN 26");

                                                                                                                endTime = System.nanoTime();
                                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                                if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) || true) {
                                                                                                                    planCounter++;
                                                                                                                    System.out.println("PLAN 27");

                                                                                                                    endTime = System.nanoTime();
                                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                                    if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) || true) {
                                                                                                                        planCounter++;
                                                                                                                        System.out.println("PLAN 28");

                                                                                                                        endTime = System.nanoTime();
                                                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                                                        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                                                                                                            planCounter++;
                                                                                                                            System.out.println("PLAN 29");

                                                                                                                            endTime = System.nanoTime();
                                                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                                                            if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                                                                                                                planCounter++;
                                                                                                                                System.out.println("PLAN 30");

                                                                                                                                endTime = System.nanoTime();
                                                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                                                if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("verylow")) || true) {
                                                                                                                                    planCounter++;
                                                                                                                                    System.out.println("PLAN 31");

                                                                                                                                    endTime = System.nanoTime();
                                                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                                                    if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) || true) {
                                                                                                                                        planCounter++;
                                                                                                                                        System.out.println("PLAN 32");

                                                                                                                                        endTime = System.nanoTime();
                                                                                                                                        writeExecutionLog(planCounter,  endTime);

                                                                                                                                        if ((isRed.equals("verylow") && isGreen.equals("ultrahigh") && isBlue.equals("medium")) || true) {
                                                                                                                                            planCounter++;
                                                                                                                                            System.out.println("PLAN 33");

                                                                                                                                            endTime = System.nanoTime();
                                                                                                                                            writeExecutionLog(planCounter,  endTime);

                                                                                                                                            if ((isRed.equals("verylow") && isGreen.equals("ultrahigh") && isBlue.equals("ultrahigh")) || true) {
                                                                                                                                                planCounter++;
                                                                                                                                                System.out.println("PLAN 34");

                                                                                                                                                endTime = System.nanoTime();
                                                                                                                                                writeExecutionLog(planCounter,  endTime);

                                                                                                                                                if ((isRed.equals("verylow") && isGreen.equals("ultramedium") && isBlue.equals("ultrahigh")) || true) {
                                                                                                                                                    planCounter++;
                                                                                                                                                    System.out.println("PLAN 35");

                                                                                                                                                    endTime = System.nanoTime();
                                                                                                                                                    writeExecutionLog(planCounter,  endTime);

                                                                                                                                                    if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) || true) {
                                                                                                                                                        planCounter++;
                                                                                                                                                        System.out.println("PLAN 36 ");

                                                                                                                                                        endTime = System.nanoTime();
                                                                                                                                                        writeExecutionLog(planCounter,  endTime);
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Total plans executed: " + planCounter);
    }


    private void decideColor18_36 (String isRed, String isGreen, String isBlue) {
        int planCounter = 0;
        long endTime;


        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium"))  && false ) {
            planCounter++;
            System.out.println("PLAN 1");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            System.out.println("PLAN 2");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            System.out.println("PLAN 3");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            System.out.println("PLAN 4");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            System.out.println("PLAN 5");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false ) {
            planCounter++;
            System.out.println("PLAN 6");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false ) {
            planCounter++;
            System.out.println("PLAN 7");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            System.out.println("PLAN 8");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            System.out.println("PLAN 9");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false ) {
            planCounter++;
            System.out.println("PLAN 10");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high"))&& false ) {
            planCounter++;
            System.out.println("PLAN 11");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) || true) {
            planCounter++;
            System.out.println("PLAN 12");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false ) {
            planCounter++;
            System.out.println("PLAN 13");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            System.out.println("PLAN 14");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false ) {
            planCounter++;
            System.out.println("PLAN 15");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            System.out.println("PLAN 16");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false ) {
            planCounter++;
            System.out.println("PLAN 17");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            System.out.println("PLAN 18");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }





        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) || true) {
            planCounter++;
            System.out.println("PLAN 19");
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);

            if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) || true) {
                planCounter++;
                System.out.println("PLAN 20");
                endTime = System.nanoTime();
                writeExecutionLog(planCounter, endTime);

                if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) || true) {
                    planCounter++;
                    System.out.println("PLAN 21");
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime);

                    if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) || true) {
                        planCounter++;
                        System.out.println("PLAN 22");
                        endTime = System.nanoTime();
                        writeExecutionLog(planCounter, endTime);

                        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low")) || true) {
                            planCounter++;
                            System.out.println("PLAN 23");
                            endTime = System.nanoTime();
                            writeExecutionLog(planCounter, endTime);

                            if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) || true) {
                                planCounter++;
                                System.out.println("PLAN 24");
                                endTime = System.nanoTime();
                                writeExecutionLog(planCounter, endTime);

                                if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) || true) {
                                    planCounter++;
                                    System.out.println("PLAN 25");
                                    endTime = System.nanoTime();
                                    writeExecutionLog(planCounter, endTime);

                                    if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) || true) {
                                        planCounter++;
                                        System.out.println("PLAN 26");
                                        endTime = System.nanoTime();
                                        writeExecutionLog(planCounter, endTime);

                                        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) || true) {
                                            planCounter++;
                                            System.out.println("PLAN 27");
                                            endTime = System.nanoTime();
                                            writeExecutionLog(planCounter, endTime);

                                            if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) || true) {
                                                planCounter++;
                                                System.out.println("PLAN 28");
                                                endTime = System.nanoTime();
                                                writeExecutionLog(planCounter, endTime);

                                                if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                                    planCounter++;
                                                    System.out.println("PLAN 29");
                                                    endTime = System.nanoTime();
                                                    writeExecutionLog(planCounter, endTime);

                                                    if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("low")) || true) {
                                                        planCounter++;
                                                        System.out.println("PLAN 30");
                                                        endTime = System.nanoTime();
                                                        writeExecutionLog(planCounter, endTime);

                                                        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("verylow")) || true) {
                                                            planCounter++;
                                                            System.out.println("PLAN 31");
                                                            endTime = System.nanoTime();
                                                            writeExecutionLog(planCounter, endTime);

                                                            if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) || true) {
                                                                planCounter++;
                                                                System.out.println("PLAN 32");
                                                                endTime = System.nanoTime();
                                                                writeExecutionLog(planCounter, endTime);

                                                                if ((isRed.equals("verylow") && isGreen.equals("ultrahigh") && isBlue.equals("medium")) || true) {
                                                                    planCounter++;
                                                                    System.out.println("PLAN 33");
                                                                    endTime = System.nanoTime();
                                                                    writeExecutionLog(planCounter, endTime);

                                                                    if ((isRed.equals("verylow") && isGreen.equals("ultrahigh") && isBlue.equals("ultrahigh")) || true) {
                                                                        planCounter++;
                                                                        System.out.println("PLAN 34");
                                                                        endTime = System.nanoTime();
                                                                        writeExecutionLog(planCounter, endTime);

                                                                        if ((isRed.equals("verylow") && isGreen.equals("ultramedium") && isBlue.equals("ultrahigh")) || true) {
                                                                            planCounter++;
                                                                            System.out.println("PLAN 35");
                                                                            endTime = System.nanoTime();
                                                                            writeExecutionLog(planCounter, endTime);

                                                                            if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) || true) {
                                                                                planCounter++;
                                                                                System.out.println("PLAN 36");
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
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }



    }

    private void decide108(String isRed, String isGreen, String isBlue) {
        int planCounter = 0;
        long endTime;


        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) || true) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
    }


    private void decide36(String isRed, String isGreen, String isBlue) {
        int planCounter = 0;
        long endTime;


        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) || true) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
    }


    private void decide360(String isRed, String isGreen, String isBlue) {
        int planCounter = 0;
        long endTime;


        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("medium") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("medium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("high") && isBlue.equals("high"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("high") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("medium") && isGreen.equals("ultrahigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultralow") && isBlue.equals("low"))&& false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("ultramedium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("high") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("low") && isGreen.equals("veryhigh") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("low") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("medium") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("low")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("high") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("medium")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("veryhigh") && isBlue.equals("high")) && false) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }
        if ((isRed.equals("verylow") && isGreen.equals("ultralow") && isBlue.equals("ultralow")) || true) {
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime);
        }

    }

///////////////////////











    private void sendPushMessage() {
        ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
        msg1.addReceiver(new AID("PushAgent", AID.ISLOCALNAME));
        msg1.setContent("push");
        send(msg1);
       // System.out.println("MESSAGE CALL");
    }

    private void sendBuildMessage() {
        ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
        msg1.addReceiver(new AID("BuildAgent", AID.ISLOCALNAME));
        msg1.setContent("build");
        send(msg1);
       // System.out.println("MESSAGE CALL");
    }



}
