package Models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationController {

    @FXML
    private TextField searchFlightsTextField;

    @FXML
    private Text departureTextField1;

    @FXML
    private Text returnTextField1;

    @FXML
    private Text departureTextField2;

    @FXML
    private Text returnTextField2;

    @FXML
    private Hyperlink editFlight1;

    @FXML
    private Hyperlink editFlight2;

    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuItem mainMenuItem;

    @FXML
    private MenuItem flightsMenuItem;

    @FXML
    private MenuItem reportsMenuItem;

    @FXML
    private MenuItem signOutMenuItem;

    @FXML
    private Button addButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private void initialize() {
        setupMenuActions();
        setupHyperlinkActions();
        setupButtonActions();
    }

    private void setupMenuActions() {
        mainMenuItem.setOnAction(event -> navigateToPage("/path/to/main.fxml", "Main"));
        flightsMenuItem.setOnAction(event -> navigateToPage("/path/to/flights.fxml", "Flights"));
        reportsMenuItem.setOnAction(event -> navigateToPage("/path/to/reports.fxml", "Reports"));
        signOutMenuItem.setOnAction(event -> signOut());
    }

    private void setupHyperlinkActions() {
        editFlight1.setOnAction(event -> navigateToPage("/path/to/edit.fxml", "Edit Flight 1"));
        editFlight2.setOnAction(event -> navigateToPage("/path/to/edit.fxml", "Edit Flight 2"));
    }

    private void setupButtonActions() {
        addButton.setOnAction(event -> navigateToPage("/path/to/add.fxml", "Add Reservation"));
        saveButton.setOnAction(event -> saveReservation());
        cancelButton.setOnAction(event -> cancel());
    }

    private void navigateToPage(String fxmlPath, String title) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void signOut() {
        System.out.println("Signing out");
    }

    private void saveReservation() {
        System.out.println("Saving reservation");
    }

    private void cancel() {
        System.out.println("Cancelling");
    }
}
