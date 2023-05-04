package steven.inventoryproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static Part lookupPart(int partId) {
    }

    public static ObservableList<Part> lookupPart(String partName) {

    }

    public static void updatePart(int index) {

    }

    public static boolean deletePart(Part selectedPart) {

    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static void addPart(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Product lookupProduct(int productId) {
    }

    public static ObservableList<Product> lookupProduct(String productName) {

    }

    public static void updateProduct(int index) {

    }

    public static boolean deleteProduct(Product selectedProduct) {

    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
