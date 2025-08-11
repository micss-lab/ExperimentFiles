package JadeNetworkScaler;


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class containerMain {

    public static void main(String[] args) {


        try {
            Runtime runtime = Runtime.instance();
            Properties properties = new ExtendedProperties();
            properties.setProperty(Profile.GUI, "true");

            Profile profile = new ProfileImpl(properties);
            AgentContainer agentContainer=runtime.createMainContainer(profile);

            containerMain.start();
           // Connexion.container1.getCredentials();

            AgentController ScalerAgent=agentContainer.createNewAgent("ScalerAgent",
                    "JadeNetworkScaler.ScalerAgent",new Object[]{});
            ScalerAgent.start();




        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void start() {

        System.out.println("Starting....");





    }

}
