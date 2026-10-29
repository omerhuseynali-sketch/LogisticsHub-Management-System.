package logisticshub.service;

import logisticshub.model.Item;
import logisticshub.model.Document;
import logisticshub.exception.SecurityBreachException;
import java.util.ArrayList;
import java.util.List;

public class Courier {
    private String courierId;
    private String name;
    private String vehicleType; // "Bicycle", "Drone", "Armored Van"
    private List<Item> assignedManifest;

    public Courier(String courierId, String name, String vehicleType) {
        this.courierId = courierId;
        this.name = name;
        this.vehicleType = vehicleType;
        this.assignedManifest = new ArrayList<>();
    }

    // High Level Business Rule: Throw Exception if unsafe handling attempted
    public void assignPackage(Item item) throws SecurityBreachException {
        if (item instanceof Document) {
            Document doc = (Document) item;
            if (doc.isConfidential() && !this.vehicleType.equalsIgnoreCase("Armored Van")) {
                throw new SecurityBreachException("Confidential Document #" + item.getTrackingNumber() +
                        " cannot be assigned to Courier " + this.name + " using a " + this.vehicleType
                        + "! Armored Van needed.");
            }
        }
        assignedManifest.add(item);
        item.updateTrackingStatus("Assigned to Courier " + name);
    }

    public void processAllDeliveries() {
        System.out.println("\n=== Courier manifest execution for " + name + " ===");
        for (Item item : assignedManifest) {
            item.processDelivery();
            item.updateTrackingStatus("Delivered Securely");
        }
    }

    public String getName() {
        return name;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
