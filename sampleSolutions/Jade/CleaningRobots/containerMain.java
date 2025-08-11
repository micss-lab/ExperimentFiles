package JadeCleaningRobots;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class containerMain {
    static MarsEnv env;
    public static void main(String[] args) {


        try {
            Runtime runtime = Runtime.instance();
            Properties properties = new ExtendedProperties();
            properties.setProperty(Profile.GUI, "true");

            Profile profile = new ProfileImpl(properties);
            AgentContainer agentContainer=runtime.createMainContainer(profile);

            containerMain.start();
           // Connexion.container1.getCredentials();

            AgentController agentR1=agentContainer.createNewAgent("AgentR1",
                    "JadeCleaningRobots.AgentR1",new Object[]{});
            agentR1.start();
            AgentController agentR3=agentContainer.createNewAgent("AgentR3",
                    "JadeCleaningRobots.AgentR3",new Object[]{});
            agentR3.start();



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void start() {

        System.out.println("Starting....");

        env = new MarsEnv();
        env.init(new String[]{"10","5","4700","boolean"}); // 7 5 4700  // 10 5 4700 boolean - R3 exactly finishes at 7 6



    }

}
