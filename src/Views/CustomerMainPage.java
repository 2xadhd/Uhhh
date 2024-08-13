package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerMainPage extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("customer-mainpage.fxml"));

            // Set the stage style before showing the stage
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 478, 456));
            stage.show(); // Show the stage after setting the style and scene
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
