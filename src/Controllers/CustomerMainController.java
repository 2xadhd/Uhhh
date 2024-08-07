package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMainController implements Initializable {
    @FXML
    private Button ListofReservationsButton;
    @FXML
    private Hyperlink MakeReservationHyper;
    @FXML
    private Hyperlink SignOutHyper;
    @FXML
    private TableView table;
    @FXML
    private TableColumn PriceColumn;
    @FXML
    private TableColumn ReservationColumn;
    @FXML
    private TableColumn WhentoWhenColumn;
    @FXML
    private TableColumn WheretoWhereColumn;
    @FXML
    private Label welcomeText;

    protected void onMakeReservationMouseClick() {
        String table = TableView.getClassCssMetaData().toString();
        String price = PriceColumn.getText();
        String reservation = ReservationColumn.getText();
        String whentowhen = WhentoWhenColumn.getText();
        String wheretowhere =WheretoWhereColumn.getText();
        String welcome = welcomeText.getText();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
