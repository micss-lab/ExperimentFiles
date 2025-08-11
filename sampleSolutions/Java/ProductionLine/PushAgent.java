package JavaProductionLine;

public class PushAgent {

    // Simulating agent's setup method
    public void setup() {
        System.out.println("Agent is ready.");
    }

    // Simulating the reception and processing of messages
    public static void listenForMessages(String messageContent) {
        // Check if the message content is not empty
        if (messageContent != null && ("push".equalsIgnoreCase(messageContent)))  {
            System.out.println("Message: " + messageContent);


                productPushed();

        } else {
            // Simulate blocking when no message is received
            System.out.println("Waiting for message...");
        }
    }

    // Action that simulates pushing the product away
    public static void productPushed() {
        System.out.println("The Product is Pushed away.");
    }
}