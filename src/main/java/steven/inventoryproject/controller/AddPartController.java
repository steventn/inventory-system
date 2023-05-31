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

/**
 * The controller class that provides logic for the add part screen of the application.
 *
 * @author Steven Nguyen
 */
public class AddPartController implements Initializable {
    /**
     * The machine ID/company name label for the part.
     */
    @FXML
    private Label partIdNameLabel;
    /**
     * The machine ID/company name text-field for the part.
     */
    @FXML
    private TextField partIdNameText;
    /**
     * The radio button for an in-house part.
     */
    @FXML
    private RadioButton inHouseRadioButton;
    /**
     * The radio button for an outsource part.
     */
    @FXML
    private RadioButton outsourcedRadioButton;
    /**
     * The name text-field for the part.
     */
    @FXML
    private TextField partNameText;
    /**
     * The stock text-field for the part.
     */
    @FXML
    private TextField partInventoryText;
    /**
     * The price text-field for the part.
     */
    @FXML
    private TextField partPriceText;
    /**
     * The maximum inventory level text-field for the part.
     */
    @FXML
    private TextField partMaxText;
    /**
     * The minimum inventory level text-field for the part.
     */
    @FXML
    private TextField partMinText;

    /**
     * Sets the label to machine ID when the in-house radio button is selected.
     *
     * @param event In-house radio button action
     */
    @FXML
    void inHouseRadioButtonAction(ActionEvent event) {
        partIdNameLabel.setText("Machine ID");
    }

    /**
     * Sets the label to company name when the in-house radio button is selected.
     *
     * @param event Outsourced radio button action
     */
    @FXML
    void outsourcedRadioButtonAction(ActionEvent event) {
        partIdNameLabel.setText("Company Name");
    }

    /**
     * Sets a Part object depending on either In-house or Outsourced selection and adds it to the Inventory.
     *
     * @param event Save button action
     * @throws IOException from FXMLoader
     *
     * LOGICAL ERROR: Before adding the returns for each error scenario, alerts would pop up in succession rather than
     * the first issue that occurred. Adding a return now only displays one alert message.
     */
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

    /**
     * Cancels creation of Part object.
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

    /**
     * Initializes the controller and sets the part selection to an in-house part.
     *
     * @param location The location used to resolve relative path for the root object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inHouseRadioButton.setSelected(true);
    }
}