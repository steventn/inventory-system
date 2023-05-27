package steven.inventoryproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import steven.inventoryproject.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static Part modifyPart;
    private static Product modifyProduct;

    @FXML
    private TableView<Part> allPartsTable;
    @FXML
    private TableColumn<Part, Integer> partId;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> partInventory;
    @FXML
    private TableColumn<Part, Double> partPrice;
    @FXML
    private TextField searchPartsField;
    @FXML
    private TableView<Product> allProductsTable;
    @FXML
    private TableColumn productId;
    @FXML
    private TableColumn productName;
    @FXML
    private TableColumn productInventory;
    @FXML
    private TableColumn productPrice;
    @FXML
    private TextField searchProductsField;
    @FXML
    private ObservableList<Part> partsList = FXCollections.observableArrayList();

    public static Part getPartToModify() {
        return modifyPart;
    }

    @FXML
    void addPartsAction(ActionEvent event) throws IOException {
        Pane addParts = FXMLLoader.load(getClass().getResource("/AddPartView.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void modifyPartsAction(ActionEvent event) throws IOException {
        modifyPart = allPartsTable.getSelectionModel().getSelectedItem();

        if (modifyPart == null) {
            displayAlert(3);
        } else {
            Pane addParts = FXMLLoader.load(getClass().getResource("/ModifyPartView.fxml"));
            Scene scene = new Scene(addParts);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    void deletePartsAction(ActionEvent event) throws IOException {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    @FXML
    void searchParts(ActionEvent event) throws IOException {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchText = searchPartsField.getText();

        if (searchText.isEmpty()) {
            ObservableList<Part> allParts = Inventory.getAllParts();
            allPartsTable.setItems(allParts);
            return;
        }

        try {
            Part idPartFound = Inventory.lookupPart(Integer.parseInt(searchText));
            if (idPartFound != null) {
                partsFound.add(idPartFound);
            }
        } catch (NumberFormatException e) {
            partsFound = Inventory.lookupPart(searchText);
        }

        allPartsTable.setItems(partsFound);


        if (partsFound.isEmpty()) {
            displayAlert(1);
        }
    }

    public static Product getProductToModify() {
        return modifyProduct;
    }

    @FXML
    void addProductsAction(ActionEvent event) throws IOException {
        Pane addParts = FXMLLoader.load(getClass().getResource("/AddProductView.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void modifyProductsAction(ActionEvent event) throws IOException {
        modifyProduct = allProductsTable.getSelectionModel().getSelectedItem();

        if (modifyProduct == null) {
            displayAlert(3);
        } else {
            Pane addParts = FXMLLoader.load(getClass().getResource("/ModifyProductView.fxml"));
            Scene scene = new Scene(addParts);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }

    @FXML
    void deleteProductsAction(ActionEvent event) throws IOException {
        Product selectedProduct = allProductsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            displayAlert(3);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }

    @FXML
    void searchProducts(ActionEvent event) throws IOException {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchText = searchProductsField.getText();

        if (searchText.isEmpty()) {
            ObservableList<Product> allProudcts = Inventory.getAllProducts();
            allProductsTable.setItems(allProudcts);
            return;
        }

        try {
            Product idProductFound = Inventory.lookupProduct(Integer.parseInt(searchText));
            if (idProductFound != null) {
                productsFound.add(idProductFound);
            }
        } catch (NumberFormatException e) {
            productsFound = Inventory.lookupProduct(searchText);
        }

        allProductsTable.setItems(productsFound);

        if (productsFound.isEmpty()) {
            displayAlert(1);
        }
    }

    @FXML
    void exitMainScreen(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Information");
                alert.setContentText("Info: Not found.");
                alert.setHeaderText("Part not found");
                break;
            case 2:
                alert.setTitle("Information");
                alert.setHeaderText("Info: Not found.");
                alert.setContentText("Product not found");
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("Error: Part is empty.");
                alertError.setContentText("Part not selected");
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("Error: Product is empty.");
                alertError.setContentText("Product not selected");
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("All parts must be removed from product before deletion.");
                break;
        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPartsTable.setItems(Inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        allProductsTable.setItems(Inventory.getAllProducts());
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}