package steven.inventoryproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static int lastPartId = 1;
    private static int lastProductId = 1;
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static int getNewPartId() {
        return ++lastPartId;
    }

    public static int getPartId() {
        return lastPartId;
    }

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static Part lookupPart(int partId) {
        Part tempSearchPartID = null;
        for (Part part : allParts) {
            if (part.getId() == partId) {
                tempSearchPartID = part;
            }
        }
        return tempSearchPartID;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                partsFound.add(part);
            }
        }

        return partsFound;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static int getNewProductId() {
        return ++lastProductId;
    }

    public static int getProductId() {
        return lastProductId;
    }


    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Product lookupProduct(int productId) {
        Product tempSearchProductID = null;
        for (Product product : Inventory.getAllProducts()) {
            if (product.getId() == productId) {
                tempSearchProductID = product;
            }
        }
        return tempSearchProductID;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }

    public static void updateProduct(int id, Product newProduct) {
        int index = -1;
        for (Product Product : Inventory.getAllProducts()) {
            index++;
            if (Product.getId() == id) {
                Inventory.getAllProducts().set(index, newProduct);
            }
        }
    }

    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
