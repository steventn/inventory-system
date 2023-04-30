module steven.inventoryproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens steven.inventoryproject to javafx.fxml;
    exports steven.inventoryproject;
    exports steven.inventoryproject.controller;
    opens steven.inventoryproject.controller to javafx.fxml;
}