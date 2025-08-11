package JadeCleaningRobots;

public class VacuumBag {
    private double capacity;        // Maximum capacity of the vacuum bag
    private String agentName;    // Name of the agent using the vacuum bag
    protected double remainingCAP;     // Remaining load in the vacuum bag

    // Constructor
    public VacuumBag(double d, String agentName) {
        this.capacity = d;
        this.agentName = agentName;
        this.remainingCAP = this.capacity; // Initialize remaining load to capacity
    }

    // Method to add items to the vacuum bag
    public boolean addItems(double amount) {
        if (remainingCAP - amount >= 0) {
            remainingCAP -= amount;
            System.out.println("Vacuumed " + amount + " items. Remaining load: " + remainingCAP + ".");
            return true;
        } else {
            return false;
        }
    }

    // Method to get details of the vacuum bag
    public String getDetails() {
        return "Agent Name: " + agentName + ", Capacity: " + capacity + ", Current Load: " + remainingCAP;
    }

    // Method to check if the vacuum bag is full
    public boolean isFull() {
        return remainingCAP >= 0;
    }


}
