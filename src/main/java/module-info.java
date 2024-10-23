module dir {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens dir to javafx.fxml;
    opens dir.controller to javafx.fxml;
    exports dir;
    exports dir.controller;
    exports dir.model;
    exports dir.view;
}
