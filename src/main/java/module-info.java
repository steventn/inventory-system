module steven.inventoryproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens steven.inventoryproject to javafx.fxml;
    exports steven.inventoryproject;
    exports controller;
    opens controller to javafx.fxml;
}