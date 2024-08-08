
package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightView extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(FlightView.class.getResource("flight-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 692, 639);
        stage.setScene(scene);
        stage.show();
    }

    public static void popupAdd(Stage stage) throws IOException {
        Stage stagePopup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(FlightView.class.getResource("flight-add.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 451, 466);
        stagePopup.setScene(scene);
        stagePopup.show();
    }

    public static void closeAdd() throws IOException {

    }

}

