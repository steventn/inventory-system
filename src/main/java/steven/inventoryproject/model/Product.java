package steven.inventoryproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructs the new Product with the following parameters.
     *
     * @param id the unique ID of the product
     * @param name the name of the product
     * @param price the price per unit of the product
     * @param stock the current inventory level of the product
     * @param min the minimum allowed inventory level of the product
     * @param max the maximum allowed inventory level of the product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Retrieve the ID of the Product.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the Product.
     *
     * @param id the id of the Product to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieve the ID of the Product.
     *
     * @return the name of the Product
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the Product.
     *
     * @param name the name of the Product to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve the price per unit of the Product.
     *
     * @return the price of the Product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price per unit of the Product.
     *
     * @param price the price of the Product to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieve the stock of the Product.
     *
     * @return the stock of the Product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set the stock of the Product.
     *
     * @param stock the stock of the Product to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Retrieve the minimum allowed inventory level of the Product.
     *
     * @return the min of the Product
     */
    public int getMin() {
        return min;
    }

    /**
     * Set the minimum allowed inventory level of the Product.
     *
     * @param min the min of the Product to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Retrieve the maximum allowed inventory level of the Product.
     *
     * @return the max of the Product
     */
    public int getMax() {
        return max;
    }

    /**
     * Set the maximum allowed inventory level of the Product.
     *
     * @param max the max of the Product to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds the provided Part to the associated Part of the Product.
     *
     * @param part the Part to be added
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Retrieves the list of all Associated Parts of the Product.
     *
     * @return the list of Parts Associated with the Product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * Deletes the Part from the list of Associated Parts of the Product.
     *
     * @return true if the Part was deleted, otherwise false
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }
}
