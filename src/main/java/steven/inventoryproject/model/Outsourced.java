package steven.inventoryproject.model;

public class Outsourced extends Part {
    private String companyName;

    /**
     * Constructs the new Outsourced part with the following parameters.
     *
     * @param id the unique ID of the part
     * @param name the name of the part
     * @param price the price per unit of the part
     * @param stock the current inventory level of the part
     * @param min the minimum allowed inventory level of the part
     * @param max the maximum allowed inventory level of the part
     * @param companyName the Company Name of the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Sets the Company Name of the Outsourced Part.
     *
     * @param companyName the Company Name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Retrieves the Company Name of the Outsourced Part.
     *
     * @return the Company Name of the part
     */
    public String getCompanyName() {
        return this.companyName;
    }

}
