package steven.inventoryproject.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView allParts;
    public TableView allProducts;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partInventoryLevel;
    public TableColumn partPricePerUnit;
    public TableColumn productId;
    public TableColumn productName;
    public TableColumn productInventoryLevel;
    public TableColumn productPricePerUnit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}