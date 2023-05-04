package steven.inventoryproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public Part lookupPart(int partId) {
        return allParts.get(partId);
    }

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> filteredList = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                filteredList.add(part);
            }
        }
        return filteredList;
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Product lookupProduct(int productId) {
        return allProducts.get(productId);
    }

    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> filteredList = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
