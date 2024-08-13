package Controllers;

import Views.ManagerMainPage;
import Views.RegistrationView;
import Views.CustomerMainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private Map<String, String> customers = new HashMap<>();
    private Map<String, String> managers = new HashMap<>();

    public LoginController() {
        loadUsersFromFile("src/database/Customer.txt", customers);
        loadUsersFromFile("src/database/Manager.txt", managers);
    }

    private void loadUsersFromFile(String filePath, Map<String, String> userMap) {
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length >= 2) {
                    String username = data[0].trim();
                    String password = data[1].trim();
                    userMap.put(username, password);
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
        if (authenticate(username, password, customers)) {
            System.out.println("Login successful for customer: " + username);
            switchToCustomerMainPage();
        } else if (authenticate(username, password, managers)) {
            System.out.println("Login successful for manager: " + username);
            switchToManagerMainPage();
        } else {
            System.out.println("Invalid credentials for user: " + username);
        }
    }

    private boolean authenticate(String username, String password, Map<String, String> userMap) {
        return userMap.containsKey(username) && userMap.get(username).equals(password);
    }

    private void switchToManagerMainPage() {
        try {
            ManagerMainPage managerMainPage = new ManagerMainPage();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            managerMainPage.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToCustomerMainPage() {
        try {
            CustomerMainPage customerMainPage = new CustomerMainPage();
            Stage newStage = new Stage();  // Create a new Stage
            customerMainPage.start(newStage);  // Use the new Stage
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();  // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
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