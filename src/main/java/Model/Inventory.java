package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * Creates an Inventory Static Class.
 */
public class Inventory {

    // Problem here was I left the ObservableLists set to null. I didn't have them initialized to anything. Once I added
    //the FXColl code, it worked just fine

    /**
     * Creates to static private Observable Lists for Parts and Products
     */
    private static ObservableList <Part> allParts = FXCollections.observableArrayList();
    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();


    /**
     * Adds a part.
     * Takes a part and then adds it to the allParts list.
     * @param part Takes a part object and then adds it to a list containing allParts
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * Adds a product.
     * Takes a product and then adds it to the allProducts List.
     * @param product Takes a Product object and then adds it to a list containing allProducts
     */
    public static void addProduct (Product product) {
        allProducts.add(product);
    }

    // Lookup part/product using ID or Name
    // I found that doing it this way, prevented less redundancy in the controller itself, having to check for both
    //name and id while this way, it clumps it all together, converts to a string, and then checks for that value

    /**
     * Part lookup.
     * Allows us to lookup parts based on name or ID.
     * @param name is a String that allows us to search
     * @return Returns an observable list of parts that has been filtered given the search result
     */
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

    /**
     * Product lookup.
     * Allows us to lookup products based on name of ID.
     * @param name is a String that allows us to search
     * @return Returns an obserable list of products that has been filtered given the search result
     */
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

    /**
     * Updates Part.
     * Allows us to update parts within modify tables using a index of that part and a part object.
     * @param index an Integer of where the selected part is indexed using FXML
     * @param part a Part object of what is being updated
     */
        public static void updatePart (int index, Part part) {
            allParts.set(index, part);
        }

    /**
     * Updates Product.
     * Allows us to update products within modify tables using an index of that product and a project object.
     * @param index An integer of where the selected product is indexed using FXML
     * @param product a Product object of what is being updated
     */
    public static void updateProduct (int index, Product product) {
            allProducts.set(index, product);
        }



    // Delete Part, Product

    /**
     * Deletes part.
     * A boolen that takes a part and removes it from the allParts list.
     * @param part Part object of part to be deleted
     * @return Returns a bool depending on if part was found and deleted within the allParts list
     */
    public static boolean deletePart (Part part) {
        if (part == null) {
            return false;
        } else {
            getAllParts().remove(part);
            return true;
        }
    }

    /**
     * Deletes product.
     * A boolean that takes a product and remoes it form teh allProducts list.
     * @param product Products object of product to be deleted
     * @return Returns a bool depending on if product was found and deleted within the allProducts list
     */
    public static boolean deleteProduct (Product product) {
        if (product == null) {
            return false;
        } else {
            getAllProducts().remove(product);
            return true;
        }
    }

    /**
     * Getter for allParts list.
     * Allows us to access private field list of Allparts.
     * @return Returns an Observable list of allParts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Getter for allProducts list.
     * Allows us to access private field list for allProducts.
     * @return Returns an Observable list of allProducts.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
