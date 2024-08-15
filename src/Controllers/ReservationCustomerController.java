/**
 *  @author Dai/Vi Quach
 *  @version 1.0
 */
package Controllers;

import Models.FlightModel;
import Views.CustomerMainPage;
import Views.LoginView;
import Views.ManagerMainPage;
import Views.ReservationCustomerView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controls the inner workings of Reservation Page for Customers
 */
public class ReservationCustomerController {

    @FXML
    private Button addButton;

    @FXML
    private DatePicker arriveTimeDatePicker;

    @FXML
    private DatePicker departTimeDatePicker;

    @FXML
    private TextField fromField;

    @FXML
    private Hyperlink mainButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button searchButton;

    @FXML
    private Hyperlink signOutButton;

    @FXML
    private TableColumn<FlightModel, String> tableArrive;

    @FXML
    private TableColumn<FlightModel, String> tableArriveTime;

    @FXML
    private TableColumn<FlightModel, String> tableDepart;

    @FXML
    private TableColumn<FlightModel, String> tableDepartTime;

    @FXML
    private TableColumn<FlightModel, String> tableFLIGHTID;

    @FXML
    private TableColumn<FlightModel, String> tablePrice;

    @FXML
    private TableColumn<FlightModel, String> tableTerminal;

    @FXML
    private TableView<FlightModel> tableView;

    @FXML
    private TextField toField;
    ArrayList<FlightModel> flightList = new ArrayList<FlightModel>();

    /**
     * Initializes the list
     */
    public void initialize() {

        tableFLIGHTID.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flightID"));
        tableDepart.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Depart"));
        tableArrive.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Arrive"));
        tableDepartTime.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("DepartTime"));
        tableArriveTime.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("ArriveTime"));
        tableTerminal.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Terminal"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Price"));
        tableView.getItems().setAll(parseFlightList());

    }
    /**
     * Gets the list of ALL flights from the text database and returns it as an ArrayList
     * @return ArrayList
     */
    private ArrayList<FlightModel> parseFlightList() {
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
        return flightList;
    }

    /**
     * Closes the add panel and returns to reservation if success. Otherwise, do nothing.
     * Runs when the user clicks on the add button.
     * @param event
     * @throws IOException
     * @throws ParseException
     */
    @FXML
    public void onAddButtonClick(ActionEvent event) throws IOException, ParseException {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage popupStage = new Stage();
            ReservationCustomerView.popupAdd(popupStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns customer to main page.
     * Runs when the customer clicks on the main button.
     * @param event
     */
    @FXML
    public void onMainButtonClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage mainStage = new Stage();
            CustomerMainPage customerMainPage = new CustomerMainPage();
            customerMainPage.start(mainStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Refreshes the list.
     * Runs when the customer clicks on the refresh button.
     * @param event
     */
    @FXML
    public void onRefreshButtonClick (ActionEvent event)
    {
        flightList.clear();
        initialize();
    }
    /**
     * Searches from the list of flights.
     * Runs when the customer clicks on the search button.
     * @param event
     */
    @FXML
    public void onSearchButtonClick(ActionEvent event) throws IOException, ParseException {
        String to = toField.getText();
        String fr = fromField.getText();
        LocalDate departDate = null;
        LocalDate arriveDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        if (departTimeDatePicker.getValue()!=null) {
            departDate = departTimeDatePicker.getValue();
        }
        else {
            departDate = LocalDate.of(1900, Month.JANUARY, 1);
        }
        if (arriveTimeDatePicker.getValue()!=null) {
            arriveDate = arriveTimeDatePicker.getValue();
        }
        else {
            arriveDate = LocalDate.of(2100, Month.JANUARY, 1);
        }
        ArrayList<FlightModel> searchList = new ArrayList<FlightModel>();
        for (FlightModel fl : flightList) {
            String convertDepartDate = fl.getDepartTime();
            String convertArriveDate = fl.getArriveTime();
            LocalDate dDate = LocalDate.parse(convertDepartDate, formatter);
            LocalDate aDate = LocalDate.parse(convertArriveDate, formatter);
            if (fl.getDepart().contains(fr) && fl.getArrive().contains(to) && dDate.isAfter(departDate) && aDate.isBefore(arriveDate)) {
                searchList.add(fl);
            }
        }
        tableView.getItems().clear();
        tableFLIGHTID.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flightID"));
        tableDepart.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Depart"));
        tableArrive.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Arrive"));
        tableDepartTime.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("DepartTime"));
        tableArriveTime.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("ArriveTime"));
        tableTerminal.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Terminal"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Price"));
        tableView.getItems().setAll(searchList);

    }
    /**
     * Returns customer to login page.
     * Runs when the customer clicks on the sign-out button.
     * @param event
     */
    @FXML
    public void onSignOutButtonClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage loginStage = new Stage();
            LoginView loginView = new LoginView();
            loginView.start(loginStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
