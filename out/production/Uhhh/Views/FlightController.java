package com.example.register;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightController {

    @FXML
    private DatePicker departDatePicker;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private Text flightsText;

    @FXML
    private Hyperlink returnToLoginLink;

    @FXML
    public void initialize() {
        returnToLoginLink.setOnAction(event -> returnToLogin());
    }

    @FXML
    public void searchFlights() {
        String from = fromField.getText();
        String to = toField.getText();
        String departDate = departDatePicker.getValue().toString();
        String returnDate = returnDatePicker.getValue().toString();

        flightsText.setText("Searching flights from " + from + " to " + to + ". Depart: " + departDate + ", Return: " + returnDate + ".");
    }

    private void returnToLogin() {
        try {
            Stage stage = (Stage) returnToLoginLink.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 473);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
