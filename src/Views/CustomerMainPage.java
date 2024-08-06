package Views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMainPage extends Application implements Initializable {

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> priceColumn;

    @FXML
    private TableColumn<User, Integer> reservationColumn;

    @FXML
    private TableColumn<User, String> whenToWhenColumn;

    @FXML
    private TableColumn<User, String> whereToWhereColumn;

    ObservableList<User> list = FXCollections.observableArrayList(
            new User("xxx", 1, "xxx to xxx", "xxx to xxx"),
            new User("xxx", 1, "xxx to xxx", "xxx to xxx")
    );

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("customer-mainpage.fxml"));
            Stage customerStage = new Stage();
            customerStage.initStyle(StageStyle.UNDECORATED);
            customerStage.setScene(new Scene(root, 478, 456));
            customerStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceColumn.setCellValueFactory(new PropertyValueFactory<User, String>("price"));
        reservationColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("reservation"));
        whenToWhenColumn.setCellValueFactory(new PropertyValueFactory<User, String>("whenToWhen"));
        whereToWhereColumn.setCellValueFactory(new PropertyValueFactory<User, String>("whereToWhere"));

        table.setItems(list);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
class User {
    private String price;
    private int reservation;
    private String whenToWhen;
    private String whereToWhere;

    public User(String price, int reservation, String whenToWhen, String whereToWhere) {
        this.price = price;
        this.reservation = reservation;
        this.whenToWhen = whenToWhen;
        this.whereToWhere = whereToWhere;
    }

    public String getPrice() {
        return price;
    }

    public int getReservation() {
        return reservation;
    }

    public String getWhenToWhen() {
        return whenToWhen;
    }

    public String getWhereToWhere() {
        return whereToWhere;
    }
}
