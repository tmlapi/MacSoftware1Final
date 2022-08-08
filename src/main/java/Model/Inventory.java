package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    // Problem here was I left the ObservableLists set to null. I didn't have them initialized to anything. Once I added
    //the FXColl code, it worked just fine
    private static ObservableList <Part> allParts = FXCollections.observableArrayList();
    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();

    // Still have to add the rest of the code



    // Adding the adds for the above ObservableLists
    public static void newPart(Part part) {
        allParts.add(part);
    }
    public static void newProduct (Product product) {
        allProducts.add(product);
    }

    // Lookup part/product using ID or Name

    // Update Part and Product

    // Delete Part, Product


    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
