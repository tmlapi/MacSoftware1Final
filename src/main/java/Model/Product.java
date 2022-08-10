package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Creates a public concrete Product class.
 */
public class Product {

    //I completely forgot to initilize this to FXCollections.observableArrayList() so it kept returning null and
    //crashing my program. Took an hour but fixed!
    /**
     * Creates associated parts list as well as introducing fields
     */
    private ObservableList <Part> associatedParts = FXCollections.observableArrayList();
    private int id, stock, min, max;
    private String name;
    private double price;

    /**
     * Product Constructor.
     * Creates a constructor for the Product class
     * @param id an Integer for the Product ID
     * @param stock an Integer for the Proudct stock
     * @param min and Integer for the Product min
     * @param max an Integer for the Product max
     * @param name a String for the Proudct name
     * @param price a Double for the Prodouct Price
     */
    public Product(int id, int stock, int min, int max, String name, double price) {
        this.id = id;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.price = price;
    }

    /**
     * Product ID getter.
     * Returns the private field, id
     * @return Returns the Product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Product ID.
     * Takes an Integer an sets it to id
     * @param id Integer that is correlated to Product id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Product stock getter.
     * Returns private product stock field.
     * @return Returns private product stock field
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets Product stock.
     * Takes a int and sets it to stock.
     * @param stock Integer that gets set to stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Product min getter.
     * Gets product min
     * @return Returns the private integer min
     */
    public int getMin() {
        return min;
    }

    /**
     * Sets Product Min.
     * Takes an int and sets it to min.
     * @param min Integer that gets set to min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Product max getter.
     * Gets product max.
     * @return Returns the private integer max
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets Product Max.
     * Takes an int and sets it to max.
     * @param max Integer that gets set to max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Product name getter.
     * Gets product name.
     * @return Returns a String of Product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets product name.
     * Take a String and sets it to name
     * @param name a String that gets tied to Prodcut name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Product price getter.
     * Gets the product price.
     * @return Returns a double tied to the Product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets product price.
     * Takes a double and sets it as Product price.
     * @param price a double that is correlated to Product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Adds part to list.
     * Takes a part object to add to associated parts list.
     * @param part A part object that is to be added to a list of associated parts
     */
    public void addAssociattedPart (Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes associated part.
     * Takes a Part object that is to be deleted and returns a bool
     * @param selectedAssociatedPart
     * @return Returns a boolean depeding on if part is found in associated parts list and is deleted
     */
    public boolean deleteAssociatedPart (Part selectedAssociatedPart) {
        if (selectedAssociatedPart == null) {
            return false;
        } else {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
    }

    /**
     * Gets Associated Parts list.
     * Returns a list of all associated parts.
     * @return Returns an observable list of all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
