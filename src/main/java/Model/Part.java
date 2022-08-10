package Model;

/**
 * This creates an abstract class known as Part. This will be a superclass with subclasses of inHouse and outSourced
 */
public abstract class Part {

    private int id, stock, min, max;
    private String name;
    private double price;

    /** This is a Part Constructor.
     * The constructor allows us to update and add Parts without calling getter and setter each time.
     * @param id Auto-assigned integer
     * @param stock Currently inventory levels; integer
     * @param min Integer; minimum
     * @param max Integer; maximum
     * @param name String; Part name
     * @param price Integer; Part Price
     */
    public Part(int id, int stock, int min, int max, String name, double price) {
        this.id = id;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.name = name;
        this.price = price;
    }

    /**
     * Getter for ID.
     * Allows us to return ID which is private.
     * @return Returns ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for ID.
     * Allows us to access the private field, ID.
     * @param id Takes an integer and assigns it to ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for stock.
     * Allows us to access private stock field.
     * @return Returns stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for stock.
     * Allows us to set private stock field.
     * @param stock Takes in an integer to assign to Stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for min.
     * Allows us to get private field Min.
     * @return Returns integer Min
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for Min.
     * Allows us to set private min field.
     * @param min Takes an integer to assign to min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for max.
     * Allows us to get private field Max.
     * @return Return integer Max
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for Max.
     * Allows us to get private max field.
     * @param max Takes an integer to assign to max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Getter for name.
     * Allows us to get private name field.
     * @return Returns a String, name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     * Allows us to set private name field.
     * @param name Takes a String to assign to name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for price.
     * Allows us to get private price field.
     * @return Returns a Double, price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price.
     * Allows us to set private price field.
     * @param price Takes a double and assigns it to price
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
