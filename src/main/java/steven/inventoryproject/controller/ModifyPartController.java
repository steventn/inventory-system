package steven.inventoryproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import steven.inventoryproject.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    @FXML
    private Label partIdNameLabel;
    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private ToggleGroup tgPartType;
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
            Part modifyPart = MainController.getPartToModify();

            int id = modifyPart.getId();
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

                    InHouse modifyInHousePart = (InHouse) modifyPart;
                    modifyInHousePart.setName(name);
                    modifyInHousePart.setMax(max);
                    modifyInHousePart.setPrice(price);
                    modifyInHousePart.setMin(min);
                    modifyInHousePart.setStock(stock);
                    modifyInHousePart.setMachineId(machineId);

                    partAddSuccessful = true;
                } catch (NumberFormatException e) {
                    displayAlert(5);
                    return;
                }
            }

            if (outsourcedRadioButton.isSelected()) {
                companyName = partIdNameText.getText();

                Outsourced modifyOutsourcedPart = (Outsourced) modifyPart;
                modifyOutsourcedPart.setName(name);
                modifyOutsourcedPart.setMax(max);
                modifyOutsourcedPart.setPrice(price);
                modifyOutsourcedPart.setMin(min);
                modifyOutsourcedPart.setStock(stock);
                modifyOutsourcedPart.setCompanyName(companyName);

                partAddSuccessful = true;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part modifyPart = MainController.getPartToModify();

        if (modifyPart instanceof InHouse) {
            inHouseRadioButton.setSelected(true);
            partIdNameLabel.setText("Machine ID");
            partIdNameText.setText(String.valueOf(((InHouse) modifyPart).getMachineId()));
        }

        if (modifyPart instanceof Outsourced){
            outsourcedRadioButton.setSelected(true);
            partIdNameLabel.setText("Company Name");
            partIdNameText.setText(((Outsourced) modifyPart).getCompanyName());
        }

        partIdText.setText(String.valueOf(modifyPart.getId()));
        partNameText.setText(modifyPart.getName());
        partInventoryText.setText(String.valueOf(modifyPart.getStock()));
        partPriceText.setText(String.valueOf(modifyPart.getPrice()));
        partMaxText.setText(String.valueOf(modifyPart.getMax()));
        partMinText.setText(String.valueOf(modifyPart.getMin()));
    }
}
