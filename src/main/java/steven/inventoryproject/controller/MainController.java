package steven.inventoryproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import steven.inventoryproject.model.Inventory;
import steven.inventoryproject.model.Part;
import steven.inventoryproject.model.Product;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView allParts;
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

    void searchBarAction(String item) {

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