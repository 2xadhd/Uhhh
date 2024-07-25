package com.example.register;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (com.example.register.Authenticator.authenticate(username, password)) {
            welcomeText.setText("Welcome, " + username + "!");
        } else {
            welcomeText.setText("Invalid credentials. Please try again.");
        }
    }
}
