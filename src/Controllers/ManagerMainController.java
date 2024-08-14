/**
 *  @author: Harrison Turner
 *  @version: 1.0
 *  date: 08/14/2024
 */
package Controllers;

import javafx.scene.Node;
import Views.ManagerMainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import Views.LoginView;
import javafx.scene.Scene;
import java.io.IOException;
import Views.ReservationManagerView;

/**
 * Controller class for managing the Manager Main Page functionality.
 * Handles user interactions within the Manager Main Page, such as navigating to different sections
 * and logging out of the application.
 */
public class ManagerMainController implements Initializable {

    @FXML
    private Label WelcomeLabel;

    @FXML
    private ToggleButton ReservationButton;

    @FXML
    private ToggleButton ReportButton;

    @FXML
    private ToggleButton CustomerButton;

    @FXML
    private ToggleButton ManagerButton;

    @FXML
    private Hyperlink ReturntoLoginHyper;

    @FXML
    private Hyperlink SignOutHyper;

    /**
     * Initializes the controller class.
     * This method is called automatically after the FXML file has been loaded.
     *
     * @param location the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resources the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any necessary components or variables here
    }

    /**
     * Handles actions performed on the menu buttons (Reservation, Report, Customer, Manager).
     * Currently, it captures the text of the selected buttons, but you can extend this method
     * to navigate to different sections based on the selected option.
     *
     * @param event the event triggered by interacting with one of the menu buttons
     */
    @FXML
    public void MenuOnAction(ActionEvent event) {
        String reservation = ReservationButton.getText();
        String report = ReportButton.getText();
        String customer = CustomerButton.getText();
        String manager = ManagerButton.getText();
        // Add your logic for handling menu actions here
    }

    /**
     * Handles the sign-out action.
     * This method logs the user out by navigating back to the login page.
     *
     * @param event the event triggered by clicking the "Sign Out" hyperlink
     */
    @FXML
    private void onSignOutClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("login-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the reservation button click action.
     * This method navigates the user to the reservation management view for managers.
     *
     * @param event the event triggered by clicking the "Reservation" button
     */
    @FXML
    private void onReservationButtonClick(ActionEvent event) {
        try {
            ReservationManagerView reservationView = new ReservationManagerView();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            reservationView.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}