package steven.inventoryproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import steven.inventoryproject.model.Inventory;
import steven.inventoryproject.model.Part;
import steven.inventoryproject.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView allParts;
    @FXML
    public Button addPartsButton;
    @FXML
    private TableColumn partId;
    public TableColumn partName;
    public TableColumn partInventoryLevel;
    public TableColumn partPricePerUnit;
    public TextField partSearchField;
    public TableView allProducts;
    public TableColumn productId;
    public TableColumn productName;
    public TableColumn productInventoryLevel;
    public TableColumn productPricePerUnit;
    public TextField productSearchField;
    private ObservableList<Part> partsList = FXCollections.observableArrayList();

    @FXML
    void addPartsClick(ActionEvent event) throws IOException {
        Parent addParts = FXMLLoader.load(getClass().getResource("../view/AddPartView.fxml"));
        Scene scene = new Scene(addParts);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        allParts.setItems(partsList);
//        partId.setCellFactory(new PropertyValueFactory<>("id"));
//
//        partSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            // Handle search text changes here
//        });
//        productSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            // Handle search text changes here
//        });
    }

}