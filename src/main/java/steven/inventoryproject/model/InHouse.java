package steven.inventoryproject.model;

public class InHouse extends Part {
    private int machineId;

    /**
     * Constructs the new Inhouse part with the following parameters.
     *
     * @param id the unique ID of the part
     * @param name the name of the part
     * @param price the price per unit of the part
     * @param stock the current inventory level of the part
     * @param min the minimum allowed inventory level of the part
     * @param max the maximum allowed inventory level of the part
     * @param machineId the Machine ID of the port
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Sets the Machine ID of the InHouse Part.
     *
     * @param machineId the Machine ID to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Retrieves the Machine ID of the Inhouse Part
     *
     * @return the Machine ID of the part.
     */
    public int getMachineId() {
        return this.machineId;
    }

}
