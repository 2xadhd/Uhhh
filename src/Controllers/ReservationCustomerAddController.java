/**
 * @author Dai/Vi Quach
 * @version 1.0
 */
package Controllers;

import Models.CustomerModel;
import Models.FlightModel;
import Models.ReservationModel;
import Views.ReservationCustomerView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

/**
 * Controls the inner workings of reservation customer add panel
 */
public class ReservationCustomerAddController {

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField departDateField;

    @FXML
    private TextField expDateField;

    @FXML
    private TextField flightID1Field;

    @FXML
    private TextField flightID2Field;

    @FXML
    private TextField priceField;

    @FXML
    private TextField returnDateField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label errorLabel;

    ArrayList<FlightModel> flightList = new ArrayList<FlightModel>();
    ArrayList<CustomerModel> cusList = new ArrayList<CustomerModel>();

    /**
     * Initializes the add panel.
     */
    public void initialize() {
        parseFlightList();
        parseCustomerList();
        /**
         * Updates the departure and arrival date and price when text changes
         */
        flightID1Field.textProperty().addListener((obs, oldText, newText) -> {
            for (FlightModel fl:flightList)
            {   System.out.println(flightID1Field.getText().trim() + "  " + fl.getFlightID());
                if (Objects.equals(fl.getFlightID(), flightID1Field.getText().trim())) {
                    departDateField.setText(fl.getDepartTime());
                    if (Objects.equals(flightID2Field.getText(), ""))
                    {
                        returnDateField.setText(fl.getArriveTime());
                        priceField.setText(String.valueOf(fl.getPrice()));
                    }
                    else {
                        double price = 0;
                        for (FlightModel fl2:flightList){
                            if (Objects.equals(fl2.getFlightID(), flightID2Field.getText().trim()))
                                 price = fl2.getPrice()+fl.getPrice();
                        }
                        priceField.setText(String.valueOf(price));
                    }
                }
            }
        });

        flightID2Field.textProperty().addListener((obs, oldText, newText) -> {
            for (FlightModel fl:flightList)
            {
                if (Objects.equals(fl.getFlightID(), flightID2Field.getText().trim())) {
                    returnDateField.setText(fl.getArriveTime());
                    if (!Objects.equals(flightID1Field.getText(), ""))
                    {
                        returnDateField.setText(fl.getArriveTime());
                        double price = Double.parseDouble(priceField.getText());
                        price = price + fl.getPrice();
                        priceField.setText(String.valueOf(price));
                    }
                }

            }
        });

    }



    /**
     * Gets all the flights from the database
     * @return
     */
    private void parseFlightList() {
        String path = new File("src/Database/flight.txt").getAbsolutePath();
        String input;
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                String[] names = input.split(",");
                if (names.length == 8) {
                    FlightModel fl = new FlightModel();
                    fl.setFlightID(names[0].trim());
                    fl.setTotalSeat(Integer.parseInt(names[1].trim()));
                    fl.setDepart(names[2].trim());
                    fl.setArrive(names[3].trim());
                    fl.setDepartTime(names[4].trim());
                    fl.setArriveTime(names[5].trim());
                    fl.setTerminal(names[6].trim());
                    fl.setPrice(Double.parseDouble(names[7].trim()));
                    flightList.add(fl);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    /**
     * Gets all the customers from the database
     */
    private void parseCustomerList() {
        String path = new File("src/Database/customer.txt").getAbsolutePath();
        String input;
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                String[] names = input.split(",");
                if (names.length == 6) {
                    CustomerModel cus = new CustomerModel();
                    cus.setUserID(names[0].trim());
                    cus.setPassword(names[1].trim());
                    cus.setFirstName(names[2].trim());
                    cus.setLastName(names[3].trim());
                    cus.setPhoneNumber(names[4].trim());
                    cus.setEmail(names[5].trim());
                    cusList.add(cus);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.close();
    }
    /**
     * Runs when user clicks the add button.
     * It mainly does validation.
     * @param event
     * @throws IOException
     */
    public void onAddButtonClick(ActionEvent event) throws IOException {
        boolean isRound = false;
        errorLabel.setText("");
        String reservationID = UUID.randomUUID().toString().substring(29);
        LocalDateTime departDate = LocalDateTime.now();
        LocalDateTime arriveDate = LocalDateTime.now();
        if (flightID1Field.getText()!="" && flightID2Field.getText()!="")
        {
            isRound = true;
        }
        boolean addFlag = false;
        int seatNum =0 ;
        int seatNum2 = 0;
        try {
            departDate = LocalDateTime.parse(departDateField.getText(),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        }
        catch (DateTimeParseException e)
        {
            errorLabel.setText("FlightID1 must not be empty");
        }
        try {
           arriveDate  = LocalDateTime.parse(returnDateField.getText(),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        }
        catch (DateTimeParseException e)
        {
            errorLabel.setText("FlightID1 must not be empty");
        }
        int totalSeat = 0;
        double price = 0;
        for (CustomerModel cus:cusList)
        {
            if (Objects.equals(usernameField.getText().trim(),cus.getUserID())) {
                addFlag = true;
            }
            System.out.println(usernameField.getText().trim() + " " + cus.getUserID());
        }
        if (!addFlag)
        {
            errorLabel.setText("Username doesn't exist");
        }


        /**
         *  Date validation (Depart date must be earlier than arrival date)
         */
        if (departDate.isAfter(arriveDate) || departDate.isEqual(arriveDate)) {
            errorLabel.setText("Departure must be earlier than arrival");
            addFlag = false;
        }
        for (FlightModel fl:flightList)
        {
            if (Objects.equals(fl.getFlightID(), flightID1Field.getText()))
            {
                seatNum = (int) Math.random()%fl.getTotalSeat();
            }

            if (Objects.equals(fl.getFlightID(), flightID2Field.getText()))
            {
                seatNum2 = (int) Math.random()%fl.getTotalSeat();
            }


        }
        if (cardNumberField.getText()=="") {
            errorLabel.setText("Card Number must not be empty");
            addFlag=false;
        }
        if (expDateField.getText()=="") {
            errorLabel.setText("Expiration Date must not be empty");
            addFlag=false;
        }
        /**
         * If flag is true then we add
         * If not we do nothing
         * String is randomly assigned, the rest is entered by the customer.
         * Closes the add panel on success
         */
        if (addFlag)
        {
            errorLabel.setText("");
            ReservationModel re = new ReservationModel(reservationID,usernameField.getText(), seatNum, seatNum2, Double.parseDouble(priceField.getText()),departDateField.getText(), returnDateField.getText(),
             flightID1Field.getText(), flightID2Field.getText(), isRound, cardNumberField.getText(), expDateField.getText());
            addReservation(re);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage reservationStage = new Stage();
            ReservationCustomerView view = new ReservationCustomerView();
            view.start(reservationStage);
        }
    }

    /**
     * Writes the newly added reservation into the database.
     * @param reservation
     * @throws IOException
     */
    public void addReservation(ReservationModel reservation) throws IOException {
        String path = new File("src/Database/reservation.txt").getAbsolutePath();
        File file = new File(path);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write(reservation.getReservationID() +  "," + reservation.getUsername() + "," + reservation.getSeatNum() + ","+ reservation.getSeatNum2()+ "," + reservation.getPrice()+ "," + reservation.getDepartTime()+ "," + reservation.getReturnTime()+ "," + reservation.isRound()+ "," + reservation.getFlightID1()+ "," + reservation.getFlightID2()+ "," + reservation.getCardNumber()+ "," + reservation.getExpDate() +  "\n");
        br.close();
        fr.close();
    }

    /**
     * Closes the add panel and returns to the reservation panel.
     * @param event
     */
    public void onCancelButtonClick (ActionEvent event)
    {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage reservationStage = new Stage();
            ReservationCustomerView view = new ReservationCustomerView();
            view.start(reservationStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
