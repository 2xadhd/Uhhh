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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any necessary components or variables here
    }

    @FXML
    public void MenuOnAction(ActionEvent event) {
        String reservation = ReservationButton.getText();
        String report = ReportButton.getText();
        String customer = CustomerButton.getText();
        String manager = ManagerButton.getText();
    }

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