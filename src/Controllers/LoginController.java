/**
 *  @author: Harrison Turner
 *  @version: 1.0
 *  date: 08/14/2024
 */
package Controllers;

import Views.ManagerMainPage;
import Views.RegistrationView;
import Views.CustomerMainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Controller class for handling the login process.
 * This class manages the authentication of both customers and managers,
 * and navigates them to their respective main pages based on their credentials.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private Map<String, String> customers = new HashMap<>();
    private Map<String, String> managers = new HashMap<>();

    /**
     * Constructor for the LoginController.
     * Initializes the user maps by loading data from the Customer and Manager text files.
     */
    public LoginController() {
        loadUsersFromFile("src/database/Customer.txt", customers);
        loadUsersFromFile("src/database/Manager.txt", managers);
    }

    /**
     * Loads users from a specified file into the provided user map.
     *
     * @param filePath the path to the file containing user data
     * @param userMap the map to store the loaded user data
     */
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

    /**
     * Handles the login button click event.
     * Authenticates the user and navigates to the appropriate main page based on their credentials.
     */
    @FXML
    private void onHelloButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (authenticate(username, password, customers)) {
            System.out.println("Login successful for customer: " + username);
            switchToCustomerMainPage(username);
        } else if (authenticate(username, password, managers)) {
            System.out.println("Login successful for manager: " + username);
            switchToManagerMainPage();
        } else {
            System.out.println("Invalid credentials for user: " + username);
        }
    }

    /**
     * Authenticates a user by checking their credentials against the provided user map.
     *
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @param userMap the map containing valid user credentials
     * @return true if the credentials are valid, false otherwise
     */
    private boolean authenticate(String username, String password, Map<String, String> userMap) {
        return userMap.containsKey(username) && userMap.get(username).equals(password);
    }

    /**
     * Switches to the manager's main page after successful authentication.
     */
    private void switchToManagerMainPage() {
        try {
            ManagerMainPage managerMainPage = new ManagerMainPage();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            managerMainPage.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switches to the customer's main page after successful authentication.
     *
     * @param username the username of the logged-in customer
     */
    private void switchToCustomerMainPage(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerMainPage.class.getResource("customer-mainpage.fxml"));
            Parent root = loader.load();

            // Get the controller instance from the loader
            CustomerMainController controller = loader.getController();
            controller.setCurrentUsername(username); // Set the logged-in user

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current stage (login window)
            Stage currentStage = (Stage) usernameField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the register hyperlink click event.
     * Navigates to the registration page.
     *
     * @param event the event triggered by clicking the register hyperlink
     */
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

    /**
     * Handles the cancel button click event.
     * Closes the login window.
     *
     * @param event the event triggered by clicking the cancel button
     */
    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}