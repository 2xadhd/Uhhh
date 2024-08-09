/**
 *  @author: Dai/Vi Quach
 *  @version: 1.0
 *  date: 08/08/2024
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
 * Controls how to add a flight to the database
 *
 */
public class FlightAddController {

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

    /**
     * Runs when user clicks the add button.
     * It mainly does validation.
     * @param event
     * @throws IOException
     */
    public void onAddAddButtonClick(ActionEvent event) throws IOException {
        String flightID = "";
        boolean addFlag = true;
        int totalSeat = 0;
        double price = 0;
        String arrive = addArrive.getText();
        String depart = addDepart.getText();
        LocalDateTime departDate = LocalDateTime.now();
        LocalDateTime arriveDate = LocalDateTime.now();
        String terminal = addTerminal.getText();

        /**
         *  Integer validation
         */
        try {
            totalSeatLabel.setText("");
            totalSeat = Integer.valueOf(addTotalSeat.getText());
        } catch (NumberFormatException e) {
            totalSeatLabel.setText("Number of seats must be a number");
            addFlag = false;
        }
        /**
         *  Date format validation
         */
        try {
            timeLabel.setText("");
            departDate = LocalDateTime.parse(addDepartTime.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            timeLabel.setText("Please follow the date format");
            addFlag = false;
        }
        /**
         *  Date format validation
         */
        try {
            timeLabel.setText("");
            arriveDate = LocalDateTime.parse(addArriveTime.getText(),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException e) {
            timeLabel.setText("Please follow the date format");
            addFlag = false;
        }
        /**
         *  Double validation
         */

        try {
            priceLabel.setText("");
            price = Double.valueOf(addPrice.getText());
        } catch (NumberFormatException e) {
            priceLabel.setText("Price must be a number");
            addFlag = false;
        }
        /**
         *  Date validation (Depart date must be earlier than arrival date)
         */
         if (departDate.isAfter(arriveDate) || departDate.isEqual(LocalDateTime.now())) {
            timeLabel.setText("Departure must be earlier than arrival");
            addFlag = false;
        } else timeLabel.setText("");

        /**
         * If flag is true then we add
         * If not we do nothing
         * String is randomly assigned, the rest is entered by the customer.
         * Closes the add panel on success
         */
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

    /**
     * Writes the newly added flight into the database.
     * @param fl
     * @throws IOException
     */
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

