/**
 *  @author: Dai/Vi Quach, Arpi Mangasaryan
 *  @version: 1.0
 *  date: 08/08/2024
 */
package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Flight GUI
 */
public class FlightView extends Application {
    private static Stage primaryStage;

    /**
     * The main panel
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(FlightView.class.getResource("flight-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 692, 639);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Popup add panel for adding flights
     * @param stage
     * @throws IOException
     */
    public static void popupAdd(Stage stage) throws IOException {
        Stage stagePopup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FlightView.class.getResource("flight-add.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451, 466);
        stagePopup.setScene(scene);
        stagePopup.show();
    }

    /**
     * Currently obsolete. Popup edit panel for editing flights
     * @param stage
     * @throws IOException
     */
    public static void popupEdit(Stage stage) throws IOException {
        Stage stagePopup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FlightView.class.getResource("flight-edit.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451, 466);
        stagePopup.setScene(scene);
        stagePopup.show();
    }



}

