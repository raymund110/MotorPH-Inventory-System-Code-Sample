package MotorPH_Inventory_System;

import java.util.LinkedList;
/*
This is a sample code implementation for the MotorPH inventory system
This meets the MotorPH requirements for an inventory management system
- Add new stocks
- Delete incorrect records
- Sort stocks according to brand
- Search for existing inventory using available information (Search by brand)

THIS CODE MAINLY FOCUSES ON THE FUNCTION OF AN INVENTORY SYSTEM FOR MOTORPH
*/

// Inventory data class
class InventoryData {
    String dateEntered;
    String stockLabel;
    String brand;
    String engineNumber;
    String status;

    public InventoryData(String dateEntered, String stockLabel, String brand, String engineNumber, String status) {
        this.dateEntered = dateEntered;
        this.stockLabel = stockLabel;
        this.brand = brand;
        this.engineNumber = engineNumber;
        this.status = status;
    }
    // Display the list in a line
    public void displayOneLine() {
        System.out.println("Date Entered: " + dateEntered +
                " || Stock Label: " + stockLabel +
                " || Brand: " + brand +
                " || Engine Number: " + engineNumber +
                " || Status: " + status);
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        // Initialize Linked list
        LinkedList<InventoryData> inventoryData = new LinkedList<InventoryData>();

        // Add stocks to inventory list
        inventoryData.addFirst(new InventoryData("2/1/2023", "Old",
                "Honda", "142QVTSIUR", "On-Hand"));
        inventoryData.addFirst(new InventoryData("2/1/2023", "Old",
                "Honda", "PZCT1S00XE", "Sold"));
        inventoryData.addFirst(new InventoryData("2/1/2023", "Old",
                "Kawasaki", "4VBTV8YNM7", "On-Hand"));
        inventoryData.addFirst(new InventoryData("2/1/2023", "Old",
                "Kymco", "PVAWKD51CE", "On-Hand"));
        inventoryData.addFirst(new InventoryData("2/1/2023", "Old",
                "Yamaha", "95AN3AWVF4", "On-Hand"));
        inventoryData.addFirst(new InventoryData("2/1/2023", "Old",
                "Yamaha", "5Q0EZG7WKB", "Sold"));
        inventoryData.addFirst(new InventoryData("2/1/2023", "Old",
                "Kymco", "483QHIM661", "Sold"));

        // Insertion Sort process by brand name
        for (int i = 1; i < inventoryData.size(); i++) {
            InventoryData key = inventoryData.get(i);
            int j = i - 1;
            // Compare engineNumber lexicographically
            while (j >= 0 && inventoryData.get(j).brand.compareTo(key.brand) > 0) {
                inventoryData.set(j + 1, inventoryData.get(j));
                j--;
            }
            inventoryData.set(j + 1, key);
        }

        System.out.println("\n========= After Sorting =============\n");
        // Print list after insertion sort
        for (InventoryData inventory : inventoryData) {
            inventory.displayOneLine();
            System.out.println("---------------------");
        }

        // Search function
        System.out.println("\n========= Search By Brand =============\n");
        // If the brand is found, it will print every product with the same brand
        String searchByBrand = "Kymco";
        for (InventoryData inventory : inventoryData) {
            if (inventory.brand.equals(searchByBrand)) {
                inventory.displayOneLine();
                System.out.println("---------------------\n");
            } 
        }

        // Delete function. Engine number as node identifier
        String itemToDelete = "95AN3AWVF4";
        InventoryData toRemove = null;
        // Check if object exist on the list
        for (InventoryData inventory : inventoryData) {
            if (inventory.engineNumber.equals(itemToDelete)) {
                toRemove = inventory;
                break;
            }
        } // If object was found, it will delete the node
        if (toRemove != null) {
            inventoryData.remove(toRemove);
            System.out.println("Item with engine number " + itemToDelete + " has been removed");
        } else {
            System.out.println("Item not found.");
        }

        // Print the list after the delete function
        System.out.println("\n========= After deletion =============\n");
        for (InventoryData inventory : inventoryData) {
            System.out.println("---------------------");
            inventory.displayOneLine();
            System.out.println("---------------------");
        }

        // Check deletion if successful
        String searchByEngine = "95AN3AWVF4";
        for (InventoryData inventory : inventoryData) {
            // If deletion fail there will be a result
            if (inventory.engineNumber.equals(searchByEngine)) {
                inventory.displayOneLine();
                break;
            } else { // If deletion was successful
                System.out.println("Engine number " + searchByEngine + " item not Found");
                break;
            }
        }
    }
}
