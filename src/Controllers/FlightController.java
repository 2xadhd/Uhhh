/**
 *  @author: Dai/Vi Quach
 *  @version: 1.0
 *  date: 08/08/2024
 */
package Controllers;

import Models.FlightModel;
import Views.FlightView;
import Views.LoginView;
import Views.ManagerMainPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Controller for the Flight Management Page (Only available to Managers). This is where
 * the program populates the TableView, do a search, add, delete.
 */

public class FlightController {

    @FXML
    private DatePicker departDatePicker;

    @FXML
    private Text flightsText;

    @FXML
    private TextField fromField;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private Hyperlink returnToLoginLink;

    @FXML
    private Button searchFlightsButton;

    @FXML
    private TextField toField;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Hyperlink signOutButton;
    @FXML
    private Button refreshButton;

    @FXML
    private TableView<FlightModel> tableView;
    @FXML
    private TableColumn<FlightModel, String> tableID;
    @FXML
    private TableColumn<FlightModel, String> tableSeat;
    @FXML
    private TableColumn<FlightModel, String> tableDepart;
    @FXML
    private TableColumn<FlightModel, String> tableArrive;
    @FXML
    private TableColumn<FlightModel, String> tableDepartTime;
    @FXML
    private TableColumn<FlightModel, String> tableArriveTime;
    @FXML
    private TableColumn<FlightModel, String> tableTerminal;
    @FXML
    private TableColumn<FlightModel, String> tablePrice;
    ArrayList<FlightModel> flightList = new ArrayList<FlightModel>();

    /**
     * Automatically set up our list of flights.
     */
    public void initialize() {

        tableID.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flightID"));
        tableSeat.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("totalSeat"));
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
     * Runs when the user clicks the search flights button.
     * @param event
     * @throws IOException
     * @throws ParseException
     */

    public void onSearchButtonClick(ActionEvent event) throws IOException, ParseException {
        String to = toField.getText();
        String fr = fromField.getText();
        LocalDate departDate = null;
        LocalDate arriveDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        if (departDatePicker.getValue()!=null) {
             departDate = departDatePicker.getValue();
        }
        else {
                departDate = LocalDate.of(1900, Month.JANUARY, 1);
        }
        if (returnDatePicker.getValue()!=null) {
         arriveDate = returnDatePicker.getValue();
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
        tableID.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("flightID"));
        tableSeat.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("totalSeat"));
        tableDepart.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Depart"));
        tableArrive.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Arrive"));
        tableDepartTime.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("DepartTime"));
        tableArriveTime.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("ArriveTime"));
        tableTerminal.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Terminal"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<FlightModel, String>("Price"));
        tableView.getItems().setAll(searchList);

    }

    /**
     * Runs when the user clicks on the Add button.
     * It will open a popup add panel, which is controlled by FlightAddController
     * @param event
     * @throws IOException
     * @throws ParseException
     */
    public void onAddButtonClick(ActionEvent event) throws IOException, ParseException {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage popupStage = new Stage();
            FlightView.popupAdd(popupStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs when the user clicks the delete button.
     * It requires the user to highlight a row on the flight table first,
     * otherwise it does nothing.
     * @param event
     * @throws IOException
     */
    public void onDeleteButtonClick(ActionEvent event) throws IOException {
        FlightModel fl = tableView.getSelectionModel().getSelectedItem();
        deleteFlight(fl);
        initialize();
    }

    /**
     * Runs when the user clisk the sign out button.
     * It will return the user back to the login page.
     * @param event
     */
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

    /**
     * Assists onDeleteButtonClick to delete a flight.
     * Using a text database this method is really dangerous
     * as it has to delete the whole file and rewrote it everytime
     * as there is no way to only edit a line in a file.
     * @param fl
     * @throws IOException
     */
    public void deleteFlight(FlightModel fl) throws IOException {
        String path = new File("src/Database/flight.txt").getAbsolutePath();
        File file = new File(path);
        flightList.removeIf(flightModel -> flightModel.equals(fl));
        FileWriter fr = new FileWriter(file, false);
        BufferedWriter br = new BufferedWriter(fr);
        for (FlightModel flight : flightList) {
                br.write(flight.getFlightID() + "," + flight.getTotalSeat() + "," + flight.getDepart() + "," + flight.getArrive() + "," + flight.getDepartTime() + "," + flight.getArriveTime() + "," + flight.getTerminal() + "," + flight.getPrice() + "\n");
            }
        flightList.clear();
        br.close();
        fr.close();
    }

    /**
     * Currently obsolete. Will update in the next increment.
     * Runs when the user clicks the edit button.
     * @param event
     */
    public void onEditButtonClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage popupStage = new Stage();
            FlightView.popupEdit(popupStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs when the user clicks the refresh button to refresh the table.
     * Usually done after a search action
     * @param event
     */
    public void onRefreshButtonClick (ActionEvent event)
    {
        flightList.clear();
        initialize();
    }

    /**
     * Returns user to main page.
     * Runs when user clicks the cancel button.
     * @param event
     * @throws IOException
     */
    public void onMainButtonClick(ActionEvent event) throws IOException{
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage mainStage = new Stage();
            ManagerMainPage managerMainPage = new ManagerMainPage();
            managerMainPage.start(mainStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

}}



