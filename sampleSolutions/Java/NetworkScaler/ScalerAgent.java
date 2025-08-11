package JavaNetworkScaler;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;


public class ScalerAgent {

    static int currentWorkLoad = 0;
    static int arrivedTurn = 0;
    static String responseTime="none";
    static String workLoad="none";
    static int arrivedWorkload;

    public static long startTime;

    private static void writeExecutionLog(int planNumber, long endTime) {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime();
        long elapsed = (end - startTime) / 1_000_000; // convert nanoseconds to milliseconds



        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad" +
                "\\Activities\\Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\WCET\\Yeni Olcumler\\NetworkScaler\\Java\\output.txt", true))) {
            //  try (BufferedWriter writer = new BufferedWriter(new FileWriter("/home/robot/productionLineEmpirical/src/java/output.txt", true))) {
            writer.write("PLANN " + planNumber + " | Execution time (ms): " + elapsed +  "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        ScalerAgent agent = new ScalerAgent();
        ScalerAgentEnvironment sae = new ScalerAgentEnvironment();


        try {
            sae.init(); // Initialize environment
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        while (sae.currentIndex < 499) {
            startTime = System.nanoTime();

            if ((currentWorkLoad == 0) || arrivedTurn == 0) {

                sae.getWorkLoadBool();

                sae.checkWorkLoadBool();
              //  Thread.sleep(1);
                arrangeResourceScale1_5(sae);

            } else if (currentWorkLoad != 0) {

                sae.checkWorkLoadBool();
             //   Thread.sleep(1);
                arrangeResourceScale1_5(sae);

            }

        }
    }


//            public static void arrangeResourceScale(ScalerAgentEnvironment sae ) {
//
//                String rule = "";
//                int factor = 0;
//
//                if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("good")) {
//                    rule = "RULE 1";
//                    factor = -15;
//                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("ok")) {
//                    rule = "RULE 2";
//                    factor = -10;
//                } else if (ScalerAgent.workLoad.equals("low") && ScalerAgent.responseTime.equals("bad")) {
//                    rule = "RULE 3";
//                    factor = 10;
//                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("good")) {
//                    rule = "RULE 4";
//                    factor = -10;
//                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("ok")) {
//                    rule = "RULE 5";
//                    factor = 0;
//                } else if (ScalerAgent.workLoad.equals("medium") && ScalerAgent.responseTime.equals("bad")) {
//                    rule = "RULE 6";
//                    factor = 10;
//                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("good")) {
//                    rule = "RULE 7";
//                    factor = 0;
//                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("ok")) {
//                    rule = "RULE 8";
//                    factor = 10;
//                } else if (ScalerAgent.workLoad.equals("high") && ScalerAgent.responseTime.equals("bad")) {
//                    rule = "RULE 9";
//                    factor = 15;
//                }
//
//
//
//                System.out.println(rule);
//                sae.scaleFactor(factor);
//                sae.consumeWorkLoad();
//            }
            
            // *****************

    public static void arrangeResourceScale(ScalerAgentEnvironment sae) {

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


    public static void arrangeResourceScale27(ScalerAgentEnvironment sae) {

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

    public static void arrangeResourceScale90(ScalerAgentEnvironment sae) {

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

    public static void arrangeResourceScale1_5(ScalerAgentEnvironment sae){


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


    public static void arrangeResourceScale1_9(ScalerAgentEnvironment sae) {

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

    public static void arrangeResourceScale5_9(ScalerAgentEnvironment sae) {

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







}

