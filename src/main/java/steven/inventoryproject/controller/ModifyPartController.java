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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    /**
     * The machine ID/company name label for the part.
     */
    @FXML
    private Label partIdNameLabel;

    /**
     * The in-house radio button.
     */
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     * The toggle group for the radio buttons.
     */
    @FXML
    private ToggleGroup tgPartType;

    /**
     * The outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * The part ID text field.
     */
    @FXML
    private TextField partIdText;

    /**
     * The part name text field.
     */
    @FXML
    private TextField partNameText;

    /**
     * The part inventory text field.
     */
    @FXML
    private TextField partInventoryText;

    /**
     * The part price text field.
     */
    @FXML
    private TextField partPriceText;

    /**
     * The part maximum level text field.
     */
    @FXML
    private TextField partMaxText;

    /**
     * The machine ID/company name text field for the part.
     */
    @FXML
    private TextField partIdNameText;

    /**
     * The part minimum level text field.
     */
    @FXML
    private TextField partMinText;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
