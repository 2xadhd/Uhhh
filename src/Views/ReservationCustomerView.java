/**
 * @author: Dai/Vi Quach, Arpi Mangasaryan
 * @version: 1.0
 * date: 8/1/2024
 */
package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Reservation Customer GUI
 */
public class ReservationCustomerView extends Application {
    /**
     * Main Panel for Reservation
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("customer-reservation-page.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 752, 570));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Popup add panel for adding reservation
     * @param stage
     * @throws IOException
     */
    public static void popupAdd(Stage stage) throws IOException {
        Stage stagePopup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FlightView.class.getResource("add-reservation-customer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stagePopup.setScene(scene);
        stagePopup.show();
    }
}
