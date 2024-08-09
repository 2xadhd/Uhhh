/**
 * @author Dai/Vi Quach
 * @version 1.0
 * Date 8/8/2024
 */
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
import java.util.UUID;

/**
 * Currently obsolete.
 * Controls how to edit a flight
 */
public class FlightEditController {

    @FXML
    private Button editBittpn;
    @FXML
    private TextField Arrive;
    @FXML
    private Label timeLabel;
    @FXML
    private Label totalSeatLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField ArriveTime;
    @FXML
    private TextField Depart;
    @FXML
    private TextField DepartTime;
    @FXML
    private TextField Price;
    @FXML
    private TextField Terminal;
    @FXML
    private TextField TotalSeat;

    @FXML
    void onEditButtonClick(ActionEvent event) throws IOException {
        String flightID = "";
        boolean addFlag = true;
        int totalSeat = 0;
        double price = 0;
        String arrive = Arrive.getText();
        String depart = Depart.getText();
        LocalDateTime departDate = LocalDateTime.now();
        LocalDateTime arriveDate = LocalDateTime.now();
        String terminal = Terminal.getText();

        try {
            totalSeatLabel.setText("");
            totalSeat = Integer.valueOf(TotalSeat.getText());
        } catch (NumberFormatException e) {
            totalSeatLabel.setText("Number of seats must be a number");
            addFlag = false;
        }

        try {
            timeLabel.setText("");
            departDate = LocalDateTime.parse(DepartTime.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            timeLabel.setText("Please follow the date format");
            addFlag = false;
        }

        try {
            timeLabel.setText("");
            arriveDate = LocalDateTime.parse(ArriveTime.getText(),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            timeLabel.setText("Please follow the date format");
            addFlag = false;
        }


        try {
            priceLabel.setText("");
            price = Double.valueOf(Price.getText());
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
            FlightModel fl = new FlightModel(flightID, totalSeat, depart, arrive, DepartTime.getText().replace("-","/"), ArriveTime.getText().replace("-","/"), terminal, price);
            editFlight(fl);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage FlightStage = new Stage();
            FlightView flightView = new FlightView();
            flightView.start(FlightStage);
        }
    }

    public void editFlight(FlightModel fl) throws IOException {
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

