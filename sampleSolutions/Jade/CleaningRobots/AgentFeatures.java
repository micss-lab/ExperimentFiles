package JadeCleaningRobots;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AgentFeatures {
    private MarsEnv.MarsModel model;
    private Object ag;
    String name;
    int x;
    int y;
    double batteryCapacity;
    double vacuumCapacity;
    boolean continue_signal=false;

    String batteryPower="full";
    String vacuumBag="empty";
    String dirtIntensity="none";


    public AgentFeatures(String name, MarsEnv.MarsModel model) {
        this.model = model;
        this.name = name;
    }




    protected void nextSlot() throws Exception {
        model.nextSlot();
        Location loc = model.getAgPos(Integer.parseInt(name.replace("r",""))-1);
        this.x = loc.x;
        this.y = loc.y;

        System.out.println("*****************************");
        System.out.println(this.name+" "+this.x + " " + this.y);
        System.out.println("BP"+this.batteryPower+" "+"VB"+this.vacuumBag+" ");
    }

    protected void nextSlotRev() throws Exception {
        model.nextSlotRev();
        Location loc = model.getAgPos(Integer.parseInt(name.replace("r",""))-1);
        this.x = loc.x;
        this.y = loc.y;

        System.out.println("*****************************");
        System.out.println(this.name+" "+this.x + " " + this.y);
    }

    protected void moveTowards(int x, int y) throws Exception {
        //   int x = (int) ((NumberTerm) action.getTerm(0)).solve();
        //   int y = (int) ((NumberTerm) action.getTerm(1)).solve();
        //    model.moveTowards(x, y);
    }

    protected void moveTowardsRev(int x, int y) throws Exception {
        //    int x = (int) ((NumberTerm) action.getTerm(0)).solve();
        //     int y = (int) ((NumberTerm) action.getTerm(1)).solve();
        model.moveTowardsRev(x, y);
    }

    protected void pickGarb() {
        model.pickGarb();
    }

    protected void dropGarb() {
        model.dropGarb();
    }

    protected void pickGarbRev() {
        model.pickGarbRev();
    }

    protected void dropGarbRev() {
        model.dropGarbRev();
    }

    protected void getDirtIntensityValues() {
        model.getDirtIntensityValues();
    }

    protected void checkStatus()
    {

    model.checkStatusBool(this);

    }

    private static void writeExecutionLog(int planNumber, long endTime, String agName) {


        System.out.println("ASD 1");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
          //  throw new RuntimeException(e);
        }
        long end = System.nanoTime();

        long elapsed=0;

        System.out.println(agName);

        if (agName.equals("Ag1"))
        {
             elapsed = (end - AgentR1.startTimeR1) / 1_000_000; // convert nanoseconds to milliseconds
        }


        if (agName.equals("Ag3"))
        {
             elapsed = (end - AgentR3.startTimeR3) / 1_000_000; // convert nanoseconds to milliseconds
        }

        System.out.println("ASD 2");


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\" +
                "WCET\\Yeni Olcumler\\CleaningRobots\\NoGUI\\PC\\Jade\\output"+agName+".txt", true))) {
            //  try (BufferedWriter writer = new BufferedWriter(new FileWriter("/home/robot/productionLineEmpirical/src/java/output.txt", true))) {
            writer.write("PLANN " + planNumber + " | Execution time (ms): " + elapsed +  "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void arrangeVacuumPower1_27(String agName) {

        int planCounter = 0;
        long endTime;

        System.out.println("VACUUUUUUM POWER");

        if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min") || true ) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 3");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 6");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 9");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

           if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 12");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

           if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

           if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 15");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);



           if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 18");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 21");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

       if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } }}}}}}}}}}}}}}}}}}}}}}}}}}

    }




    protected void arrangeVacuumPower(String agName) {

        int planCounter = 0;
        long endTime;

        System.out.println("VACUUUUUUM POWER "+agName);

        if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 3");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 6");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 9");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 12");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 15");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 18");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 21");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")|| true) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")|| true) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")|| true) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }

    }

    protected void arrangeVacuumPower1_15(String agName) {

        int planCounter = 0;
        long endTime;

        System.out.println("VACUUUUUUM POWER");

        if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 3");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 6");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 9");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 12");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

          if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 15");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);
            }}}}}}}}}}}}}}}

        if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min") && false) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")&& false) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")&& false) {
            System.out.println("RULE 18");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")&& false) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")&& false) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")&& false) {
            System.out.println("RULE 21");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")&& false) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")&& false) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")&& false) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")&& false) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")&& false) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }

        }

//********

    protected void arrangeVacuumPower15_27(String agName) {

        int planCounter = 0;
        long endTime;

        System.out.println("VACUUUUUUM POWER");

        if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min") && false) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid") && false) {
                System.out.println("RULE 2");
                burnGarb(70, 1);

                planCounter++;
                endTime = System.nanoTime();
                writeExecutionLog(planCounter, endTime, agName);

        }else    if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max") && false) {
                    System.out.println("RULE 3");
                    burnGarb(90,1);

                    planCounter++;
                    endTime = System.nanoTime();
                    writeExecutionLog(planCounter, endTime, agName);

        }else    if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")&& false) {
                        System.out.println("RULE 4");
                        burnGarb(50, 1);

                        planCounter++;
                        endTime = System.nanoTime();
                        writeExecutionLog(planCounter, endTime, agName);

        }else   if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid") && false) {
                            System.out.println("RULE 5");
                            burnGarb(70, 1);

                            planCounter++;
                            endTime = System.nanoTime();
                            writeExecutionLog(planCounter, endTime, agName);

        }else  if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max") && false) {
                                System.out.println("RULE 6");
                                burnGarb(90,1);

                                planCounter++;
                                endTime = System.nanoTime();
                                writeExecutionLog(planCounter, endTime, agName);

        }else     if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min") && false) {
                                    System.out.println("RULE 7");
                                    burnGarb(50, 1);

                                    planCounter++;
                                    endTime = System.nanoTime();
                                    writeExecutionLog(planCounter, endTime, agName);

        }else      if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid") && false) {
                                        System.out.println("RULE 8");
                                        burnGarb(70, 1);

                                        planCounter++;
                                        endTime = System.nanoTime();
                                        writeExecutionLog(planCounter, endTime, agName);

        }else  if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max") && false) {
                                            System.out.println("RULE 9");
                                            burnGarb(90,1);

                                            planCounter++;
                                            endTime = System.nanoTime();
                                            writeExecutionLog(planCounter, endTime, agName);

        }else   if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min") && false) {
                                                System.out.println("RULE 10");
                                                burnGarb(50, 1);

                                                planCounter++;
                                                endTime = System.nanoTime();
                                                writeExecutionLog(planCounter, endTime, agName);

        }else   if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid") && false) {
                                                    System.out.println("RULE 11");
                                                    burnGarb(70, 1);

                                                    planCounter++;
                                                    endTime = System.nanoTime();
                                                    writeExecutionLog(planCounter, endTime, agName);

        }else   if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max") && false) {
                                                        System.out.println("RULE 12");
                                                        burnGarb(90,1);

                                                        planCounter++;
                                                        endTime = System.nanoTime();
                                                        writeExecutionLog(planCounter, endTime, agName);

        }else   if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min") && false) {
                                                            System.out.println("RULE 13");
                                                            burnGarb(50, 1);

                                                            planCounter++;
                                                            endTime = System.nanoTime();
                                                            writeExecutionLog(planCounter, endTime, agName);

        }else   if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid") || true) {
                                                                System.out.println("RULE 14");
                                                                burnGarb(70, 1);

                                                                planCounter++;
                                                                endTime = System.nanoTime();
                                                                writeExecutionLog(planCounter, endTime, agName);

        }


        if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max") || true) {
               System.out.println("RULE 15");
              burnGarb(90,1);
              planCounter++;
              endTime = System.nanoTime();
                writeExecutionLog(planCounter, endTime, agName);


        if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")  || true) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 18");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

       if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 21");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

       if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min") || true) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid") || true) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

         if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max") || true) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }}}}}}}}}}}}}


    }








    protected void arrangeVacuumPower81(String agName) {

        int planCounter = 0;
        long endTime;

        System.out.println("VACUUUUUUM POWER");

        if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        ///////
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }

        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90,1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }

    }

    protected void arrangeVacuumPower270(String agName) {

        int planCounter = 0;
        long endTime;

        System.out.println("VACUUUUUUM POWER");

        if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        ///////
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }
        else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 1");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 2");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 3");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 4");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 5");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 6");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 7");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 8");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("min") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 9");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 10");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 11");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 12");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 13");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 14");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 15");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 16");
            burnGarb(50, 1);
            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 17");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("mid") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 18");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("min")) {
            System.out.println("RULE 19");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 20");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("min") && dirtIntensity.equals("max")) {
            System.out.println("RULE 21");
            burnGarb(90, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("min")) {
            System.out.println("RULE 22");
            burnGarb(50, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 23");

            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("mid") && dirtIntensity.equals("max")) {
            System.out.println("RULE 24");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("min")) {
            System.out.println("RULE 25");
            burnGarb(70, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("mid")) {
            System.out.println("RULE 26");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        } else if (batteryPower.equals("max") && vacuumBag.equals("max") && dirtIntensity.equals("max")) {
            System.out.println("RULE 27");
            burnGarb(100, 1);

            planCounter++;
            endTime = System.nanoTime();
            writeExecutionLog(planCounter, endTime, agName);

        }



    }

    protected void burnGarb(int vacuumPowerLimit, int vacuumPowerConstant) {
        // double vacuumPowerLimit = (double) ((NumberTerm) action.getTerm(0)).solve();
        //  double vacuumPowerConstant = (double) ((NumberTerm) action.getTerm(1)).solve();

        System.out.println("BURN GARBAGE");
        model.burnGarb(this, vacuumPowerLimit, vacuumPowerConstant);
    }
}


