package JadeProductionLine;

import JadeCleaningRobots.MarsEnv;
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
            properties.setProperty(Profile.GUI, "false");

            Profile profile = new ProfileImpl(properties);
            AgentContainer agentContainer=runtime.createMainContainer(profile);

            containerMain.start();
           // Connexion.container1.getCredentials();

            AgentController BuildAgent=agentContainer.createNewAgent("BuildAgent",
                    "JadeProductionLine.BuildAgent",new Object[]{});
            BuildAgent.start();

            AgentController PushAgent=agentContainer.createNewAgent("PushAgent",
                    "JadeProductionLine.PushAgent",new Object[]{});
            PushAgent.start();

            AgentController SortAgent=agentContainer.createNewAgent("SortAgent",
                    "JadeProductionLine.SortAgent",new Object[]{});
            SortAgent.start();



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void start() {

        System.out.println("Starting....");

        AgentPLFeatures.parseDataFile();



    }

}
