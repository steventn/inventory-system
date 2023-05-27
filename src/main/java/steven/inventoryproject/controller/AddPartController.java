package steven.inventoryproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import steven.inventoryproject.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddPartController implements Initializable {


    @FXML
    private Label partIdNameLabel;
    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private TextField partIdText;
    @FXML
    private TextField partNameText;
    @FXML
    private TextField partInventoryText;
    @FXML
    private TextField partPriceText;
    @FXML
    private TextField partMaxText;
    @FXML
    private TextField partIdNameText;
    @FXML
    private TextField partMinText;

    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {
        partIdNameLabel.setText("Machine ID");
    }

    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {
        partIdNameLabel.setText("Company Name");
    }

    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {
        try {
            int id = Inventory.getPartId();
            String name = partNameText.getText();
            Double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInventoryText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineId;
            String companyName;
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

            if (inHouseRadioButton.isSelected()) {
                try {
                    machineId = Integer.parseInt(partIdNameText.getText());
                    InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                    newInHousePart.setId(id);
                    Inventory.addPart(newInHousePart);
                    partAddSuccessful = true;
                } catch (NumberFormatException e) {
                    displayAlert(5);
                    return;
                }
            }

            if (outsourcedRadioButton.isSelected()) {
                companyName = partIdNameText.getText();
                Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                newOutsourcedPart.setId(Inventory.getPartId());
                Inventory.addPart(newOutsourcedPart);
                partAddSuccessful = true;
            }

            if (partAddSuccessful) {
                Inventory.getNewPartId();
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
        inHouseRadioButton.setSelected(true);
    }
}