/**
 *  @author: Harrison Turner
 *  @version: 1.0
 *  date: 08/14/2024
 */
package Controllers;

import Models.ReservationModel;
import Views.LoginView;
import Views.ReservationCustomerView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller class for the Customer Main Page.
 * This class handles the user interactions and data display for the customer main page,
 * including loading flight and reservation data, and handling navigation events.
 */
public class CustomerMainController implements Initializable {

    @FXML
    private Button ListofReservationsButton;

    @FXML
    private Hyperlink MakeReservationHyper;

    @FXML
    private Hyperlink SignOutHyper;

    @FXML
    private TableView<ReservationModel> table;

    @FXML
    private TableColumn<ReservationModel, String> reservationColumn;

    @FXML
    private TableColumn<ReservationModel, String> whereToWhereColumn;

    @FXML
    private TableColumn<ReservationModel, String> whenToWhenColumn;

    @FXML
    private TableColumn<ReservationModel, Double> priceColumn;

    @FXML
    private Label welcomeText;

    private ObservableList<ReservationModel> reservationList = FXCollections.observableArrayList();
    private Map<String, String> flightRoutes = new HashMap<>();
    private String currentUsername;

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFlightsFromFile();
        if (currentUsername != null) {
            loadReservationsFromFile(currentUsername);
        }

        reservationColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        whereToWhereColumn.setCellValueFactory(cellData -> {
            String flightID1 = cellData.getValue().getFlightID1();
            String flightID2 = cellData.getValue().getFlightID2();
            String route = flightRoutes.getOrDefault(flightID1, "") + " to " + flightRoutes.getOrDefault(flightID2, "");
            return new SimpleStringProperty(route);
        });

        whenToWhenColumn.setCellValueFactory(cellData -> {
            String whenToWhen = cellData.getValue().getDepartTime() + " to " + cellData.getValue().getReturnTime();
            return new SimpleStringProperty(whenToWhen);
        });

        table.setItems(reservationList);
    }

    /**
     * Sets the current username and loads the corresponding reservations.
     *
     * @param username The username of the currently logged-in user.
     */
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
        if (currentUsername != null) {
            loadReservationsFromFile(currentUsername);
        }
    }

    /**
     * Loads flight data from a file and populates the flightRoutes map.
     * The flightRoutes map contains the flight ID as the key and the route (departure to arrival) as the value.
     */
    private void loadFlightsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/database/flight.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s*,\\s*");
                if (data.length >= 4) {
                    String flightID = data[0];
                    String route = data[2] + " to " + data[3];
                    flightRoutes.put(flightID, route);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads reservation data for the given username from a file and populates the reservationList.
     * Only reservations matching the current user's username are loaded.
     *
     * @param username The username for which to load reservations.
     */
    private void loadReservationsFromFile(String username) {
        reservationList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/database/reservation.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s*,\\s*");
                if (data.length == 12 && data[1].equals(username)) {
                    String reservationID = data[0];
                    int seatNum = Integer.parseInt(data[2]);
                    int seatNum2 = data[3].isEmpty() ? 0 : Integer.parseInt(data[3]);
                    double price = Double.parseDouble(data[4].replaceAll("\\s+", ""));
                    String departTime = data[5];
                    String returnTime = data[6];
                    boolean round = Boolean.parseBoolean(data[7]);
                    String flightID1 = data[8];
                    String flightID2 = data[9].isEmpty() ? "" : data[9];
                    String cardNumber = data[10];
                    String expDate = data[11];

                    ReservationModel reservation = new ReservationModel(
                            reservationID,
                            username,
                            seatNum,
                            seatNum2,
                            price,
                            departTime,
                            returnTime,
                            flightID1,
                            flightID2,
                            round,
                            cardNumber,
                            expDate
                    );

                    reservationList.add(reservation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the Sign Out action, returning the user to the login view.
     *
     * @param event The ActionEvent triggered by the user.
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
     * Handles the action of making a new reservation, switching the view to the reservation page.
     *
     * @param event The ActionEvent triggered by the user.
     */
    @FXML
    private void onMakeReservationClick(ActionEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            ReservationCustomerView reservationView = new ReservationCustomerView();
            reservationView.start(currentStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}