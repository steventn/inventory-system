package steven.inventoryproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public TableColumn partId;
    public TableColumn partName;
    public TableView allParts;
    public TableColumn partInventoryLevel;
    public TableColumn partPricePerUnit;
    public TableView allProducts;
    public TableColumn productId;
    public TableColumn productName;
    public TableColumn productInventoryLevel;
    public TableColumn productPricePerUnit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}