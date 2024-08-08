package Controllers;

import Models.FlightModel;
import Views.FlightView;
import Views.LoginView;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


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

    public void onSearchButtonClick(ActionEvent event) throws IOException, ParseException {
        String to = toField.getText();
        String fr = fromField.getText();
        LocalDate departDate = departDatePicker.getValue();
        LocalDate arriveDate = returnDatePicker.getValue();
        ArrayList<FlightModel> searchList = new ArrayList<FlightModel>();
        for (FlightModel fl : flightList) {
            String convertDepartDate = fl.getDepartTime();
            String convertArriveDate = fl.getArriveTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");// Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
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

    public void onDeleteButtonClick(ActionEvent event) throws IOException {
        FlightModel fl = tableView.getSelectionModel().getSelectedItem();
        deleteFlight(fl);
        initialize();
    }

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

    public void deleteFlight(FlightModel fl) throws IOException {
        String path = new File("src/Database/flight.txt").getAbsolutePath();
        File file = new File(path);
        FileWriter fr = new FileWriter(file, false);
        BufferedWriter br = new BufferedWriter(fr);
        flightList.removeIf(flightModel -> flightModel.equals(fl));
        for (FlightModel flight : flightList) {
                br.write(flight.getFlightID() + "," + flight.getTotalSeat() + "," + flight.getDepart() + "," + flight.getArrive() + "," + flight.getDepartTime() + "," + flight.getArriveTime() + "," + flight.getTerminal() + "," + flight.getPrice() + "\n");
            }
        flightList.clear();
        br.close();
        fr.close();
    }}
