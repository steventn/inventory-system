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


public class ModifyProductController implements Initializable{
    private ObservableList<Part> associatedPartsTable = FXCollections.observableArrayList();
    Product modifyProduct = MainController.getProductToModify();

    @FXML
    private TextField searchPartsField;
    @FXML
    private TextField productIdText;
    @FXML
    private TextField productMaxText;
    @FXML
    private TextField productMinText;
    @FXML
    private TextField productNameText;
    @FXML
    private TextField productInventoryText;
    @FXML
    private TextField productPriceText;
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
    private TableView<Part> allAssociatedPartsTable;
    @FXML
    private TableColumn<Part, Integer> associatedPartsId;
    @FXML
    private TableColumn<Part, String> associatedPartsName;
    @FXML
    private TableColumn<Part, Integer> associatedPartsInventory;
    @FXML
    private TableColumn<Part, Double> associatedPartsPrice;

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

    @FXML
    void removeAssociatedPartButtonAction(ActionEvent event) throws IOException {
        Part selectedPart = allAssociatedPartsTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(5);
        } else {
            associatedPartsTable.remove(selectedPart);
            modifyProduct.deleteAssociatedPart(selectedPart);
            allAssociatedPartsTable.setItems(associatedPartsTable);
        }
    }

    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {
        try {
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

            try {
                modifyProduct.setName(name);
                modifyProduct.setMax(max);
                modifyProduct.setPrice(price);
                modifyProduct.setMin(min);
                modifyProduct.setStock(stock);

                for (Part part : associatedPartsTable) {
                    modifyProduct.addAssociatedPart(part);
                }

                partAddSuccessful = true;
            } catch (NumberFormatException e) {
                displayAlert(5);
                return;
            }


            if (partAddSuccessful) {
                returnToMainScreen(event);
            }
        } catch (NumberFormatException e) {
            displayAlert(6);
        }
    }

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

    private void returnToMainScreen(ActionEvent event) throws IOException {
        Pane myPane = FXMLLoader.load(getClass().getResource
                ("/MainView.fxml"));
        Scene scene = new Scene(myPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

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
                alert.setHeaderText("Error: Invalid Machine ID value");
                alert.setContentText("Machine ID may only contain numbers.");
                break;
            case 6:
                alert.setTitle("Error");
                alert.setHeaderText("Error: Adding Part");
                alert.setContentText("Form contains blank fields or invalid values.");
                break;
        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productIdText.setText(String.valueOf(modifyProduct.getId()));
        productNameText.setText(modifyProduct.getName());
        productInventoryText.setText(String.valueOf(modifyProduct.getStock()));
        productPriceText.setText(String.valueOf(modifyProduct.getPrice()));
        productMaxText.setText(String.valueOf(modifyProduct.getMax()));
        productMinText.setText(String.valueOf(modifyProduct.getMin()));

        allPartsTable.setItems(Inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        allAssociatedPartsTable.setItems(modifyProduct.getAllAssociatedParts());
        associatedPartsId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
