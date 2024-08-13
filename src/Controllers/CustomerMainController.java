package Controllers;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import Models.ReservationModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import Views.LoginView;
import javafx.scene.Scene;
import Views.ReservationCustomerView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFlightsFromFile();
        loadReservationsFromFile();

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

    private void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/database/reservation.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s*,\\s*");
                if (data.length == 12) {
                    String reservationID = data[0];
                    String username = data[1];
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

                    // Create the ReservationModel object using the full constructor
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

                    // Add to the list
                    reservationList.add(reservation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onMakeReservationMouseClick() {
        // Handle reservation logic
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
    private void onMakeReservationClick(ActionEvent event) {
        try {
            ReservationCustomerView reservationView = new ReservationCustomerView();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            reservationView.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
