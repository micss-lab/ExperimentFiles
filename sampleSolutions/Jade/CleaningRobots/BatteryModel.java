package JadeCleaningRobots;

public class BatteryModel {
    private double capacity; // Battery capacity in the range of 0-100
    private double k;        // Sensitivity factor for action effect

    public BatteryModel(double initialCapacity, double k) {
        this.capacity = initialCapacity;
        this.k = k;
    }

    // Simulate the battery decrease based on an action value
    public boolean performAction(double actionValue, double timeInterval) {
        // Calculate the battery decrease based on the exponential decay model
        double decreaseFactor = Math.exp(-k * actionValue * timeInterval);
        capacity *= decreaseFactor;
        // Ensure the battery capacity does not go below 0

        System.out.println("Capacity: " + capacity);
        if (capacity < 0) {
            capacity = 0;
            return false;
        }

        return true;
    }

    public double getCapacity() {
        return capacity;
    }


}
