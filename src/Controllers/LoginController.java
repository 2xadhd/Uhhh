package Controllers;

import Views.RegistrationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private Map<String, String> users = new HashMap<>();

    public LoginController() {
        loadUsersFromFile("src/database/Customer.txt");
        loadUsersFromFile("src/database/Manager.txt");
    }

    private void loadUsersFromFile(String filePath) {
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length >= 2) {
                    String username = data[0].trim();
                    String password = data[1].trim();
                    users.put(username, password);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onHelloButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (authenticate(username, password)) {
            System.out.println("Login successful for user: " + username);
        } else {
            System.out.println("Invalid credentials for user: " + username);
        }
    }

    private boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    @FXML
    private void onRegisterMouseClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            RegistrationView register = new RegistrationView();
            register.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}