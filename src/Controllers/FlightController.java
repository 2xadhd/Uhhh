package Controllers;

import Models.FlightModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;



public class FlightController  implements Initializable  {

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

    @FXML private TableView<FlightModel> tableView;
    @FXML private TableColumn<FlightModel, String> tableID;
    @FXML private TableColumn<FlightModel, String> tableSeat;
    @FXML private TableColumn<FlightModel, String> tableDepart;
    @FXML private TableColumn<FlightModel, String> tableArrive;
    @FXML private TableColumn<FlightModel, String> tableDepartTime;
    @FXML private TableColumn<FlightModel, String> tableArriveTime;
    @FXML private TableColumn<FlightModel, String> tableTerminal;
    @FXML private TableColumn<FlightModel, String> tablePrice;
    ArrayList<FlightModel> flightList = new ArrayList<FlightModel>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    private ArrayList<FlightModel> parseFlightList(){
        String path = new File("src/Database/flight.txt").getAbsolutePath();
        String input;
        File file = new File(path);
        Scanner sc;
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
        return flightList;
    }

    public void onSearchButtonClick(ActionEvent event) throws IOException {

    }

}