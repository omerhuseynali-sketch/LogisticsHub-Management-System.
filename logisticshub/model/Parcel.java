package logisticshub.model;

public class Parcel extends Item {
    private double length;
    private double width;
    private double height;
    private int maxTransitDays;

    public Parcel(int itemId, String trackingNumber, double weight, String destinationAddress, double length,
            double width, double height, int maxTransitDays) {
        super(itemId, trackingNumber, weight, destinationAddress);
        this.length = length;
        this.width = width;
        this.height = height;
        this.maxTransitDays = maxTransitDays;
    }

    @Override
    public double calculateShippingCost() {
        // Multi-layered calculations: Dimensional Weight vs Physical Weight
        double dimensionalWeight = (length * width * height) / 5000.0;
        double structuralWeight = Math.max(getWeight(), dimensionalWeight);
        return structuralWeight * 4.5;
    }

    @Override
    public void processDelivery() {
        updateTrackingStatus("Loaded into Delivery Fleet Cargo");
        System.out.println("📦 Delivering Freight Parcel. Scanning cargo metrics...");
    }

    @Override
    public boolean isDelayed(int currentDaysElapsed) {
        return currentDaysElapsed > maxTransitDays;
    }

    // Custom child business method
    public double getVolume() {
        return length * width * height;
    }

    @Override
    public String toString() {
        return "Parcel [" + super.toString() + " | Dimensions: " + length + "x" + width + "x" + height + " cm]";
    }
}
