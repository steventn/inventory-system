package steven.inventoryproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import steven.inventoryproject.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The controller class that provides logic for the add product screen of the application.
 *
 * @author Steven Nguyen
 */
public class AddProductController implements Initializable{
    /**
     * A list containing all parts that will be associated with the product object.
     */
    private ObservableList<Part> associatedPartsTable = FXCollections.observableArrayList();

    /**
     * The search field for a part in the inventory.
     */
    @FXML
    private TextField searchPartsField;
    /**
     * The maximum inventory level text-field for the product.
     */
    @FXML
    private TextField productMaxText;
    /**
     * The minimum inventory level text-field for the product.
     */
    @FXML
    private TextField productMinText;
    /**
     * The name text-field for the product.
     */
    @FXML
    private TextField productNameText;
    /**
     * The stock text-field for the product.
     */
    @FXML
    private TextField productInventoryText;
    /**
     * The price text-field for the product.
     */
    @FXML
    private TextField productPriceText;
    /**
     * The all parts table view.
     */
    @FXML
    private TableView<Part> allPartsTable;
    /**
     * The part ID column of the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partId;
    /**
     * The part name column of the all parts table.
     */
    @FXML
    private TableColumn<Part, String> partName;
    /**
     * The part stock column of the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventory;
    /**
     * The part price column of the all parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPrice;
    /**
     * The all associated parts table.
     */
    @FXML
    private TableView<Part> allAssociatedPartsTable;
    /**
     * The part ID column of the all associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartsId;
    /**
     * The part name column of the all associated parts table.
     */
    @FXML
    private TableColumn<Part, String> associatedPartsName;
    /**
     * The part stock column of the all associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartsInventory;
    /**
     * The part price column of the all associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> associatedPartsPrice;

    /**
     * Searches for a part using either part ID or by name.
     *
     * @param event Search part action
     * @throws IOException from FXMLoader
     *
     *
     * LOGICAL and RUNTIME ERROR: There was a bug before the try catch was implemented where the ID was getting passed
     * as a String and not an Integer. The second issue was before adding idPartFound != null, empty string searches
     * would throw another error because a null part object was added to the list.
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
     * Adds a part from the all parts table to the associated parts table.
     *
     * @param event Add button action
     * @throws IOException from FXMLoader
     */
    @FXML
    void addButtonAction(ActionEvent event) throws IOException {
        Part selectedPart = allPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {
            associatedPartsTable.add(selectedPart);
            allAssociatedPartsTable.setItems(associatedPartsTable);
        }
    }

    /**
     * Removes a part from the associated parts table.
     *
     * @param event Remove associated parts button action
     * @throws IOException from FXMLoader
     */
    @FXML
    void removeAssociatedPartButtonAction(ActionEvent event) throws IOException {
        Part selectedPart = allAssociatedPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {
            associatedPartsTable.remove(selectedPart);
            allAssociatedPartsTable.setItems(associatedPartsTable);
        }
    }

    /**
     * Saves a Product object with its associated parts and adds it to the Inventory.
     *
     * @param event Save button action
     * @throws IOException from FXMLoader
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {
        try {
            int id = Inventory.getProductId();
            String name = productNameText.getText();
            Double price = Double.parseDouble(productPriceText.getText());
            int stock = Integer.parseInt(productInventoryText.getText());
            int min = Integer.parseInt(productMinText.getText());
            int max = Integer.parseInt(productMaxText.getText());
            boolean partAddSuccessful = false;

            if (name.isEmpty()) {
                displayAlert(1);
                return;
            }

            if (min <= 0 || min >= max) {
                displayAlert(2);
                return;
            }
            if (stock < min || stock > max) {
                displayAlert(3);
                return;
            }

            if (price < 0) {
                displayAlert(4);
                return;
            }

            Product newProduct = new Product(id, name, price, stock, min, max);
            for (Part part : associatedPartsTable) {
                newProduct.addAssociatedPart(part);
            }
            newProduct.setId(id);
            Inventory.addProduct(newProduct);
            partAddSuccessful = true;

            if (partAddSuccessful) {
                Inventory.getNewProductId();
                returnToMainScreen(event);
            }
        } catch (NumberFormatException e) {
            displayAlert(6);
        }
    }

    /**
     * Cancels modification of Part object.
     *
     * @param event Cancel button action
     * @throws IOException from FXML Loader
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want cancel changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }

    /**
     * Loads the MainView screen to return to the Main Screen.
     *
     * @param event Main screen action
     * @throws IOException from FXML Loader
     */
    private void returnToMainScreen(ActionEvent event) throws IOException {
        Pane myPane = FXMLLoader.load(getClass().getResource
                ("/MainView.fxml"));
        Scene scene = new Scene(myPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays an alert depending on the error condition
     *
     * @param alertType Alert message condition
     */
    private void displayAlert(int alertType) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Empty or Invalid Field");
                alert.setContentText("Fields cannot be empty or invalid.");
                break;
            case 2:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Invalid Min value");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Invalid Inventory value");
                alert.setContentText("Inventory must be a number equal to or between Min and Max.");
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Invalid Price value");
                alert.setContentText("Price must be greater than 0.");
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Error: No Part selected");
                alert.setContentText("Part not selected");
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                break;
        }
        alert.showAndWait();
    }

    /**
     * Initializes the controller and adds existing parts to be displayed on the all parts table.
     * The associated parts table will be populated if a user adds a part to the product.
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

        associatedPartsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
