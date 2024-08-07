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
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> priceColumn;

    @FXML
    private TableColumn<User, String> reservationColumn;

    @FXML
    private TableColumn<User, String> whenToWhenColumn;

    @FXML
    private TableColumn<User, String> whereToWhereColumn;

    ObservableList<User> list = FXCollections.observableArrayList(
            new User("Customer", 02, "Trip"),
            new User("Customer", 01, "Trip")
    );


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceColumn.setCellValueFactory(new PropertyValueFactory<User, String>("price"));
        reservationColumn.setCellValueFactory(new PropertyValueFactory<User, String>("reservation"));
        whenToWhenColumn.setCellValueFactory(new PropertyValueFactory<User, String>("whenToWhen"));
        whereToWhereColumn.setCellValueFactory(new PropertyValueFactory<User, String>("whereToWhere"));
        table.setItems(list);
    }

    public static void main(String[] args) { launch(args);
    }

    }
