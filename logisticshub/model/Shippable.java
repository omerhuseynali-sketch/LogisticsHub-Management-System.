package logisticshub.model;

public interface Shippable {
    void updateTrackingStatus(String newStatus);

    void processDelivery();

    boolean isDelayed(int currentDaysElapsed);
}
