module steven.inventoryproject {
    requires javafx.controls;
    requires javafx.fxml;

    exports steven.inventoryproject.controller;
    opens steven.inventoryproject.controller to javafx.fxml;
    exports steven.inventoryproject.main;
    opens steven.inventoryproject.main to javafx.fxml;
    opens steven.inventoryproject.model to javafx.base;

}