package Controllers;

import Models.ReservationModel;
import Views.LoginView;
import Views.ManagerMainPage;
import Views.ReservationManagerView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManagerController {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Hyperlink mainButton;

    @FXML
    private Hyperlink signOutButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField userNameField;

    @FXML
    private TableView<ReservationModel> tableView;
    @FXML
    private TableColumn<ReservationModel, String> tableUsername;
    @FXML
    private TableColumn<ReservationModel, String> tableFlightID1;
    @FXML
    private TableColumn<ReservationModel, String> tableFlightID2;
    @FXML
    private TableColumn<ReservationModel, String> tableDepart;
    @FXML
    private TableColumn<ReservationModel, String> tableArrive;
    @FXML
    private TableColumn<ReservationModel, String> tableDepartTime;
    @FXML
    private TableColumn<ReservationModel, String> tableArriveTime;
    @FXML
    private TableColumn<ReservationModel, String> tablePrice;
    ArrayList<ReservationModel> reservationList = new ArrayList<ReservationModel>();

    /**
     * Automatically set up our list of reservations.
     */
    public void initialize() {

        tableUsername.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("username"));
        tableFlightID1.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("FlightID1"));
        tableFlightID2.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("FlightID2"));
        tableDepartTime.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("DepartTime"));
        tableArriveTime.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("ReturnTime"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("Price"));
        tableView.getItems().setAll(parseReservationList());

    }

    /**
     * to get all the reservations from the database
     * @return
     */
    private ArrayList<ReservationModel> parseReservationList() {
        String path = new File("src/Database/reservation.txt").getAbsolutePath();
        String input;
        File file = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                String[] names = input.split(",");
                if (names.length == 12) {
                    ReservationModel re = new ReservationModel();
                    re.setReservationID(names[0].trim());
                    re.setUsername(names[1].trim());
                    re.setSeatNum(Integer.parseInt(names[2].trim()));
                    if (names[3].trim()=="")
                    {
                        re.setSeatNum2(0);
                    }
                    else re.setSeatNum2(Integer.parseInt(names[3].trim()));
                    names[4]= names[4].replaceAll("\\s+","");
                    re.setPrice(Double.parseDouble(names[4]));
                    re.setDepartTime(names[5].trim());
                    re.setReturnTime(names[6].trim());
                    re.setFlightID1(names[8].trim());
                    re.setFlightID2(names[9].trim());
                    re.setRound(Boolean.parseBoolean(names[7].trim()));
                    re.setCardNumber(names[10].trim());
                    re.setExpDate(names[11].trim());
                    reservationList.add(re);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.close();
        return reservationList;
    }

    @FXML
    void onAddButtonClick(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage popupStage = new Stage();
            ReservationManagerView.popupAdd(popupStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void onRefreshButtonClick(ActionEvent event) {
        reservationList.clear();
        initialize();
    }

    /**
     * Runs when the user clicks the search reservation button.
     * @param event
     * @throws IOException
     * @throws ParseException
     */
    @FXML
    public void onSearchButtonClick(ActionEvent event) throws IOException, ParseException {
        ArrayList<ReservationModel> searchList = new ArrayList<ReservationModel>();
        for (ReservationModel re : reservationList) {
            if (re.getUsername().contains(userNameField.getText())) {
                searchList.add(re);
            }
        }

        tableUsername.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("username"));
        tableFlightID1.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("FlightID1"));
        tableFlightID2.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("FlightID2"));
        tableDepartTime.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("DepartTime"));
        tableArriveTime.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("ReturnTime"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<ReservationModel, String>("Price"));
        tableView.getItems().setAll(searchList);

    }

    /**
     * Runs when the user clicks the delete button.
     * It requires the user to highlight a row on the flight table first,
     * otherwise it does nothing.
     * @param event
     * @throws IOException
     */
    @FXML
    public void onDeleteButtonClick(ActionEvent event) throws IOException {
        ReservationModel reservation = tableView.getSelectionModel().getSelectedItem();
        ReservationModel comparison = new ReservationModel();
        for (ReservationModel re: reservationList)
        {
            if (reservation.getUsername()==re.getUsername() && reservation.getPrice()==re.getPrice())
                comparison = re;
        }
        ReservationModel finalComparison = comparison;
        reservationList.removeIf(ReservationModel -> ReservationModel.equals(finalComparison));
        deleteReservation();
        initialize();
    }
    /**
     * Assists onDeleteButtonClick to delete a reservation.
     * Using a text database this method is really dangerous
     * as it has to delete the whole file and rewrote it everytime
     * as there is no way to only edit a line in a file.
     * @throws IOException
     */
    @FXML
    public void deleteReservation() throws IOException {
        String path = new File("src/Database/reservation.txt").getAbsolutePath();
        File file = new File(path);
        FileWriter fr = new FileWriter(file, false);
        BufferedWriter br = new BufferedWriter(fr);
        for (ReservationModel reservation  : reservationList) {
            br.write(reservation.getReservationID() +  "," + reservation.getUsername() + "," + reservation.getSeatNum() + ","+ reservation.getSeatNum2()+ "," + reservation.getPrice()+ "," + reservation.getDepartTime()+ "," + reservation.getReturnTime()+ "," + reservation.isRound()+ "," + reservation.getFlightID1()+ "," + reservation.getFlightID2()+ "," + reservation.getCardNumber()+ "," + reservation.getExpDate() +  "\n");
        }
        reservationList.clear();
        br.close();
        fr.close();
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
    @FXML
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
    }



}
