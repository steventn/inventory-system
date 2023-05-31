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

/**
 * The controller class that provides logic for the main screen of the application.
 *
 * @author Steven Nguyen
 */
public class MainController implements Initializable {
    /**
     * The selected part to be modified.
     */
    private static Part modifyPart;
    /**
     * The selected product be modified.
     */
    private static Product modifyProduct;

    /**
     * The all parts table view.
     */
    @FXML
    private TableView<Part> allPartsTable;
    /**
     * The part ID column of the all products table.
     */
    @FXML
    private TableColumn<Part, Integer> partId;
    /**
     * The part name column of the all products table.
     */
    @FXML
    private TableColumn<Part, String> partName;
    /**
     * The part stock column of the all products table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventory;
    /**
     * The part price column of the all products table.
     */
    @FXML
    private TableColumn<Part, Double> partPrice;
    /**
     * The search field for a part in the inventory.
     */
    @FXML
    private TextField searchPartsField;
    /**
     * The all products table view.
     */
    @FXML
    private TableView<Product> allProductsTable;
    /**
     * The product ID column of the all products table.
     */
    @FXML
    private TableColumn productId;
    /**
     * The product name column of the all products table.
     */
    @FXML
    private TableColumn productName;
    /**
     * The product stock column of the all products table.
     */
    @FXML
    private TableColumn productInventory;
    /**
     * The product price column of the all products table.
     */
    @FXML
    private TableColumn productPrice;
    /**
     * The search field for a product in the inventory.
     */
    @FXML
    private TextField searchProductsField;

    /**
     * Retrieves the part to be modified .
     *
     * @return modifyPart Part object
     */
    public static Part getPartToModify() {
        return modifyPart;
    }

    /**
     * Displays the add part scene.
     *
     * @param event Add part button action
     * @throws IOException from FXMLoader
     */
    @FXML
    void addPartsAction(ActionEvent event) throws IOException {
        Pane addParts = FXMLLoader.load(getClass().getResource("/AddPartView.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Displays the modify part scene.
     *
     * @param event Modify part button action
     * @throws IOException from FXMLoader
     */
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

    /**
     * Deletes a part from the all part table.
     *
     * @param event Delete part button action
     * @throws IOException from FXMLoader
     */
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

    /**
     * Searches for a part using either part ID or by name.
     *
     * @param event Search part action
     * @throws IOException from FXMLoader
     */
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

    /**
     * Retrieves the product to be modified .
     *
     * @return modifyProduct Product object
     */
    public static Product getProductToModify() {
        return modifyProduct;
    }

    /**
     * Displays the add product scene.
     *
     * @param event Add product button action
     * @throws IOException from FXMLoader
     */
    @FXML
    void addProductsAction(ActionEvent event) throws IOException {
        Pane addParts = FXMLLoader.load(getClass().getResource("/AddProductView.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Displays the modify product screen.
     *
     * @param event Modify product button action
     * @throws IOException from FXMLoader
     */
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

    /**
     * Deletes a product from the all products table.
     *
     * @param event Delete product button action
     * @throws IOException from FXMLoader
     */
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

    /**
     * Searches for a product using either product ID or by name.
     *
     * @param event Search product action
     * @throws IOException from FXMLoader
     */
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

    /**
     * Closes the application upon selecting Exit.
     *
     * @param event Exit button action
     */
    @FXML
    void exitMainScreen(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**
     * Displays an alert depending on the error condition
     *
     * @param alertType Alert message condition
     *
     *
     * FUTURE ENHANCEMENT: An optional String parameter could be passed in to use String.format() so less case scenarios
     * can be used and allow reusable code for the other Controller classes.
     */
    private void displayAlert(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

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
                alert.setTitle("Error");
                alert.setHeaderText("Error: Part is empty.");
                alert.setContentText("Part not selected");
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Product is empty.");
                alert.setContentText("Product not selected");
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Parts Associated");
                alert.setContentText("All parts must be removed from product before deletion.");
                break;
        }
        alert.showAndWait();
    }

    /**
     * Initializes the controller and adds existing parts/product to be displayed on the all parts/product table.
     *
     * @param location The location used to resolve relative path for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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