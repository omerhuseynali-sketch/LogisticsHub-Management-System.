package logisticshub.service;

import logisticshub.model.Item;
import logisticshub.model.Parcel;
import java.util.ArrayList;
import java.util.List;

public class Hub {
    private String hubName;
    private List<Item> inventory;

    public Hub(String hubName) {
        this.hubName = hubName;
        this.inventory = new ArrayList<>();
    }

    public void acceptPackage(Item item) {
        inventory.add(item);
        System.out.println("📥 Package arrived at " + hubName + " Depot -> " + item.getTrackingNumber());
    }

    public double calculateTotalRevenue() {
        double revenue = 0.0;
        for (Item item : inventory) {
            revenue += item.calculateShippingCost(); // Polymorphism dynamically executes calculations
        }
        return revenue;
    }

    // Downcasting and Verification Engine
    public void auditHighVolumeParcels() {
        System.out.println("\n--- [Audit] Scanning for High Volume Parallels ---");
        for (Item item : inventory) {
            if (item instanceof Parcel) {
                Parcel box = (Parcel) item; // Downcasting
                if (box.getVolume() > 25000) {
                    System.out.println("⚠️ Large Volume Warning: #" + box.getTrackingNumber() + " takes "
                            + box.getVolume() + " cm³");
                }
            }
        }
    }

    public void displayHubInventory() {
        System.out.println("\n=== Current Inventory Matrix for " + hubName + " ===");
        for (Item item : inventory) {
            System.out.println(item + " | Est Shipping Cost: $" + String.format("%.2f", item.calculateShippingCost()));
        }
    }
}
