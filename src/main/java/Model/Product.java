package Model;

import javafx.collections.ObservableList;

public class Product {

    private ObservableList <Part> associatedParts;
    private int id, stock, min, max;
    private String name;
    private double price;

    public Product(int id, int stock, int min, int max, String name, double price) {
        this.id = id;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addAssociattedPart (Part part) {
        associatedParts.add(part);
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart (Part selectedAssociatedPart) {
        if (selectedAssociatedPart == null) {
            return false;
        } else {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
