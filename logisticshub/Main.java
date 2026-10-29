package logisticshub;

import logisticshub.model.*;
import logisticshub.service.*;
import logisticshub.exception.SecurityBreachException;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting " + Item.COMPANY_NAME + " System ===");

        // 1. Initialize Depots
        Hub centralHub = new Hub("Istanbul Distribution Center");

        // 2. Instantiate Polymorphic Objects (Mix of Documents and Freight)
        Item secureLetter = new Document(1, "TRK-DOC-901", 0.15, "Levent, Center", true, 2);
        Item standardLetter = new Document(2, "TRK-DOC-304", 0.05, "Kadikoy, Side", false, 5);

        Item normalBox = new Parcel(3, "TRK-BOX-880", 5.2, "Besiktas, Main St", 20, 20, 30, 4);
        Item massiveBox = new Parcel(4, "TRK-BOX-991", 45.0, "Ankara Logistics Zone", 50, 60, 40, 3);

        // 3. Process Arrivals
        centralHub.acceptPackage(secureLetter);
        centralHub.acceptPackage(standardLetter);
        centralHub.acceptPackage(normalBox);
        centralHub.acceptPackage(massiveBox);

        // 4. View Operational Dashboard Metrics
        centralHub.displayHubInventory();
        centralHub.auditHighVolumeParcels();

        System.out.println(
                "\nTotal Pending System Revenue: $" + String.format("%.2f", centralHub.calculateTotalRevenue()));

        // 5. Setup Couriers & Handle Real-World Business Rule Validation Exception
        Courier ecoRider = new Courier("C-08", "Can", "Bicycle");
        Courier armoredTruck = new Courier("C-01", "Mehmet", "Armored Van");

        System.out.println("\n=== Dispatching Courier Pipelines ===");

        // Let's test the custom exception check
        try {
            System.out.println("Attempting to hand secure letter to eco courier...");
            ecoRider.assignPackage(secureLetter); // This breaks safety parameters
        } catch (SecurityBreachException e) {
            System.out.println(e.getMessage());
            System.out.println("System recovered. Routing to armored carrier variant instead...");

            try {
                armoredTruck.assignPackage(secureLetter); // Secure asset routed safely
                armoredTruck.assignPackage(massiveBox);
                ecoRider.assignPackage(standardLetter); // Safe standard delivery
            } catch (SecurityBreachException ex) {
                System.out.println(ex.getMessage());
            }
        }

        // 6. Complete final delivery execution loop
        armoredTruck.processAllDeliveries();

        // 7. Verify Static Global Infrastructure Counts
        System.out.println("\n======================================");
        System.out.println("Total Packages Registered Across Network History: " + Item.getGlobalPackageCount());
    }
}
