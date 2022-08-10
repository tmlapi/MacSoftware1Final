package Model;

/**
 * Creates a subclass from the superclass, Part.
 */
public class Outsourced extends Part{

    /**
     * New variable companyName; String.
     */
    private String companyName;

    /**
     * Constructor for outsourced parts.
     * Creates a constructor extending from Parts using super().
     * @param id integer of ID for Outsourced Parts
     * @param stock integer of Stock for Outsourced Parts
     * @param min integer of Min for Outsourced Parts
     * @param max integer of Max for Outsourced Parts
     * @param name String of Name for Outsourced Parts
     * @param price Double of Price for Outsourced Parts
     * @param companyName String of Companyname specific to Outsourced
     */
    public Outsourced(int id, int stock, int min, int max, String name, double price, String companyName) {
        super(id, stock, min, max, name, price);
        this.companyName = companyName;
    }

    /**
     * Getter for companyName.
     * Allows us to access private String, companyName.
     * @return the private String companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Setter for companyName.
     * Allows us to take a String and set it to companyName.
     * @param companyName Takes a String and sets it as companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
