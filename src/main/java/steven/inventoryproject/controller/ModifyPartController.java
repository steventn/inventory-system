package steven.inventoryproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
