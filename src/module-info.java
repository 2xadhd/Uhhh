

module src {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires java.sql;
    requires org.junit.jupiter.api;
    exports Controllers; opens Controllers to javafx.fxml;
    exports Views;
    exports Models;
    opens Views to javafx.fxml;
}