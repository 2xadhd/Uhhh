

module src { requires javafx.fxml; requires javafx.controls;
    requires transitive javafx.graphics;
    exports Controllers; opens Controllers to javafx.fxml;
    exports Views;
    opens Views to javafx.fxml;
}