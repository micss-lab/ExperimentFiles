package JavaProductionLine;



public class BuildAgent {

    private static int buildStatus = 0;

    public BuildAgent() {
        // Equivalent to JADE's setup
        setup();
    }

    private void setup() {
        // Setup logic
        System.out.println("BuildAgent setup complete.");
    }

    // Simulates receiving a message and handling it
    public static void onMessageReceived(String messageContent) {
        if (messageContent != null) {
            System.out.println("Message: " + messageContent);

            if ("build".equalsIgnoreCase(messageContent)) {

                buildStatus++;
                System.out.println("K= " + buildStatus);
                checkState();
            }
        } else {
            System.out.println("No message received.");
        }
    }

    // Represents internal state checks
    public static void checkState() {
        if (buildStatus == 1) {
            System.out.println("First Red Press " + buildStatus);
        } else if (buildStatus == 2) {
            System.out.println("Second Red-Red Press " + buildStatus);
            eject();
        }
    }

    // Resets the state
    public static void eject() {
        System.out.println("Eject");
        buildStatus = 0;
    }

    // Getter and setter for buildStatus
    public int getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(int status) {
        this.buildStatus = status;
    }
}

