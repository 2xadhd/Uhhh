package Controllers;

import Views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> {
            try {
                handleLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(username, password)) {
            // If login is successful, switch to flight search view
            LoginView.switchToFlightSearch();
        } else {
            System.out.println("Invalid login");
        }
    }

    private boolean isValidLogin(String username, String password) {
        // Replace with actual database
        return "user".equals(username) && "pass".equals(password);
    }
}
