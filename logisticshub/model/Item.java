package logisticshub.model;

public abstract class Item implements Shippable {
    public static final String COMPANY_NAME = "LogisticsHub Corp";
    private static int globalPackageCount = 0;

    private int itemId;
    private String trackingNumber;
    private double weight; // in kg
    private String destinationAddress;
    private String status;

    public Item(int itemId, String trackingNumber, double weight, String destinationAddress) {
        this.itemId = itemId;
        this.trackingNumber = trackingNumber;
        this.weight = weight;
        this.destinationAddress = destinationAddress;
        this.status = "Hub Sorting";
        globalPackageCount++;
    }

    // Abstract method to be overridden dynamically by children
    public abstract double calculateShippingCost();

    @Override
    public void updateTrackingStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("🔄 [Update] Item " + trackingNumber + " status shifted to: " + newStatus);
    }

    public static int getGlobalPackageCount() {
        return globalPackageCount;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public double getWeight() {
        return weight;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ID: " + itemId + " | Track: #" + trackingNumber + " | Weight: " + weight + "kg | Status: " + status;
    }
}
