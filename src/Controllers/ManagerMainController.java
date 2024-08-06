package Controllers;

import Views.ManagerMainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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
    }}
