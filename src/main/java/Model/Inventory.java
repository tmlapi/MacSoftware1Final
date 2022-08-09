package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class Inventory {

    // Problem here was I left the ObservableLists set to null. I didn't have them initialized to anything. Once I added
    //the FXColl code, it worked just fine

    private static ObservableList <Part> allParts = FXCollections.observableArrayList();
    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();

    // Adding the adds for the above ObservableLists

    public static void addPart(Part part) {
        allParts.add(part);
    }
    public static void addProduct (Product product) {
        allProducts.add(product);
    }

    // Lookup part/product using ID or Name
    // I found that doing it this way, pevented less redundancy in the controller itself, having to check for both
    //name and id while this way, it clumps it all together, converts to a string, and then checks for that value

    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> filteredPartsList = FXCollections.observableArrayList();

        // I modified this using String.valueOf to convert the id into a string so it can be compared
        for (Part p : Inventory.getAllParts()) {
            if (p.getName().contains(name) || String.valueOf(p.getId()).contains(name)) {
                filteredPartsList.add(p);
            }
        }
        if (filteredPartsList.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Matches Found");
            alert1.show();
        }
        return filteredPartsList;
    }

    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> filteredProductList = FXCollections.observableArrayList();

        // I modified this using String.valueOf to convert the id into a string so it can be compared
        for (Product p : Inventory.getAllProducts()) {
            if (p.getName().contains(name) || String.valueOf(p.getId()).contains(name)) {
                filteredProductList.add(p);
            }
        }
        if (filteredProductList.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Matches Found");
            alert1.show();
        }
        return filteredProductList;
    }



    // Update Part and Product
        public static void updatePart (int index, Part part) {
            allParts.set(index, part);
        }

        public static void updateProduct (int index, Product product) {
            allProducts.set(index, product);
        }



    // Delete Part, Product
    public static boolean deletePart (Part part) {
        if (part == null) {
            return false;
        } else {
            getAllParts().remove(part);
            return true;
        }
    }

    public static boolean deleteProduct (Product product) {
        if (product == null) {
            return false;
        } else {
            getAllProducts().remove(product);
            return true;
        }
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
