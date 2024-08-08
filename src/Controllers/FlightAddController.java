package Controllers;

import Models.FlightModel;
import Views.FlightView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

public class FlightAddController {

    String path = new File("src/Database/flight.txt").getAbsolutePath();
    String input;
    File file = new File(path);
    Scanner sc = null;
    @FXML
    private Button addAddButton;
    @FXML
    private TextField addArrive;
    @FXML
    private Label timeLabel;
    @FXML
    private Label totalSeatLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField addArriveTime;
    @FXML
    private TextField addDepart;
    @FXML
    private TextField addDepartTime;
    @FXML
    private TextField addPrice;
    @FXML
    private TextField addTerminal;
    @FXML
    private TextField addTotalSeat;

    @FXML
    void onAddAddButtonClick(ActionEvent event) throws IOException {
        String flightID = "";
        boolean addFlag = true;
        int totalSeat = 0;
        double price = 0;
        String arrive = addArrive.getText();
        String depart = addDepart.getText();
        LocalDateTime departDate = LocalDateTime.now();
        LocalDateTime arriveDate = LocalDateTime.now();
        String terminal = addTerminal.getText();

        try {
            totalSeatLabel.setText("");
            totalSeat = Integer.valueOf(addTotalSeat.getText());
        } catch (NumberFormatException e) {
            totalSeatLabel.setText("Number of seats must be a number");
            addFlag = false;
        }

        try {
            timeLabel.setText("");
            departDate = LocalDateTime.parse(addDepartTime.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            timeLabel.setText("Please follow the date format");
            addFlag = false;
        }

        try {
            timeLabel.setText("");
            arriveDate = LocalDateTime.parse(addArriveTime.getText(),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            timeLabel.setText("Please follow the date format");
            addFlag = false;
        }


        try {
            priceLabel.setText("");
            price = Double.valueOf(addPrice.getText());
        } catch (NumberFormatException e) {
            priceLabel.setText("Price must be a number");
            addFlag = false;
        }

         if (departDate.isAfter(arriveDate) || departDate.isEqual(LocalDateTime.now())) {
            timeLabel.setText("Departure must be earlier than arrival");
            addFlag = false;
        } else timeLabel.setText("");
        
        if (addFlag)
        {
                flightID = UUID.randomUUID().toString().substring(29);
                FlightModel fl = new FlightModel(flightID, totalSeat, depart, arrive, addDepartTime.getText().replace("-","/"), addArriveTime.getText().replace("-","/"), terminal, price);
                addFlight(fl);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                Stage FlightStage = new Stage();
                FlightView flightView = new FlightView();
                flightView.start(FlightStage);
        }
    }

    public void addFlight(FlightModel fl) throws IOException {
        String path = new File("src/Database/flight.txt").getAbsolutePath();
        File file = new File(path);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.newLine();
        br.write(fl.getFlightID() + "," + fl.getTotalSeat() + "," + fl.getDepart() + "," + fl.getArrive() + "," + fl.getDepartTime() + "," + fl.getArriveTime() + "," + fl.getTerminal() + "," + fl.getPrice());
        br.close();
        fr.close();
    }
}

