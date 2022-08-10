package Model;

/**
 * Creates a subclass of Inhouse extending from Part superclass.
 */
public class InHouse extends Part {

    /**
     * Creates new private field, machineID, integer.
     */
    private int machineId;

    /**
     * Constructor for InHouse.
     * Creates a constructor using super() extending from Parts and adding new field, machineID.
     * @param id integer of ID for InHouse Parts
     * @param stock integer of stock for InHouse Parts
     * @param min integer of min for InHouse Parts
     * @param max integer of max for InHouse Parts
     * @param name String of name for InHouse Parts
     * @param price Double of price for InHouse Parts
     * @param machineId integer of machineID for InHouse Parts
     */
    public InHouse(int id, int stock, int min, int max, String name, double price, int machineId) {
        super(id, stock, min, max, name, price);
        this.machineId = machineId;
    }

    /**
     * Getter for machineID.
     * Allows us to get private machineId field.
     * @return Returns integer of machineID
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Setter for machineId.
     * Allows us to take integer and set it as machineID.
     * @param machineId takes an integer and assigns it to machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
