package logisticshub.model;

public class Document extends Item {
    private boolean isConfidential;
    private int estimatedDaysLimit;

    public Document(int itemId, String trackingNumber, double weight, String destinationAddress, boolean isConfidential,
            int estimatedDaysLimit) {
        super(itemId, trackingNumber, weight, destinationAddress);
        this.isConfidential = isConfidential;
        this.estimatedDaysLimit = estimatedDaysLimit;
    }

    @Override
    public double calculateShippingCost() {
        // Base envelope charge + premium security fee if confidential
        double cost = 5.0 + (getWeight() * 2.5);
        if (isConfidential) {
            cost += 15.0; // Security escrow fee
        }
        return cost;
    }

    @Override
    public void processDelivery() {
        updateTrackingStatus("Out for Courier Hand-delivery");
        System.out.println("📄 Delivering Document. Requires Physical Signature upon receipt.");
    }

    @Override
    public boolean isDelayed(int currentDaysElapsed) {
        return currentDaysElapsed > estimatedDaysLimit;
    }

    public boolean isConfidential() {
        return isConfidential;
    }

    @Override
    public String toString() {
        return "Document [" + super.toString() + " | Confidential: " + isConfidential + "]";
    }
}
