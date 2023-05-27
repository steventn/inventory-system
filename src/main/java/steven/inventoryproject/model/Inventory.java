package steven.inventoryproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static int lastPartId = 1;
    private static int lastProductId = 1;
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Retrieves a new unique Part ID by incrementing the last stored ID.
     *
     * @return the new Part ID
     */
    public static int getNewPartId() {
        return ++lastPartId;
    }

    /**
     * Retrieves the Part ID.
     *
     * @return the Part ID
     */
    public static int getPartId() {
        return lastPartId;
    }

    /**
     * Adds a new Part to the Inventory.
     *
     * @param newPart the part to be added
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Searches for a Part in the Inventory by the Part ID.
     *
     * @param partId the Part ID to search for
     * @return the Part object that corresponds to the Part ID
     */
    public static Part lookupPart(int partId) {
        Part tempSearchPartID = null;
        for (Part part : allParts) {
            if (part.getId() == partId) {
                tempSearchPartID = part;
            }
        }
        return tempSearchPartID;
    }

    /**
     * Searches for a Part in the Inventory by the Part name.
     *
     * @param partName the Part name to search for
     * @return the Part object that corresponds to the Part ID
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                partsFound.add(part);
            }
        }

        return partsFound;
    }

    /**
     * Updates the position of the Part in the Inventory.
     *
     * @param index the position of the Part in the Inventory
     * @param selectedPart the Part to be updated
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Deletes the specified Part in the Inventory.
     *
     * @param selectedPart the Part to be deleted
     * @return true if the Part was found and deleted, false if otherwise
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Retrieves all Parts in the Inventory.
     *
     * @return the list of all Parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Retrieves a new unique Product ID by incrementing the last stored ID.
     *
     * @return the new Product ID
     */
    public static int getNewProductId() {
        return ++lastProductId;
    }

    /**
     * Retrieves the Product ID.
     *
     * @return the Product ID
     */
    public static int getProductId() {
        return lastProductId;
    }

    /**
     * Adds a new Product to the Inventory.
     *
     * @param newProduct the product to be added
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Searches for a Product in the Inventory by the Product ID.
     *
     * @param productId the Product ID to search for
     * @return the Product object that corresponds to the Product ID
     */
    public static Product lookupProduct(int productId) {
        Product tempSearchProductID = null;
        for (Product product : Inventory.getAllProducts()) {
            if (product.getId() == productId) {
                tempSearchProductID = product;
            }
        }
        return tempSearchProductID;
    }

    /**
     * Searches for a Product in the Inventory by the Product name.
     *
     * @param productName the Product name ot search for
     * @return the Product object that corresponds to the Product ID
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }

    /**
     * Updates the position of the Product in the Inventory.
     *
     * @param id the position of the Product in the Inventory
     * @param selectedProduct the Product to be updated
     */
    public static void updateProduct(int id, Product selectedProduct) {
        int index = -1;
        for (Product Product : Inventory.getAllProducts()) {
            index++;
            if (Product.getId() == id) {
                Inventory.getAllProducts().set(index, selectedProduct);
            }
        }
    }

    /**
     * Deletes the specified Product in the Inventory.
     *
     * @param selectedProduct the Product to be deleted
     * @return true if the Product was found and deleted, false if otherwise
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Retrieves all Products in the Inventory.
     *
     * @return the list of all Products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
