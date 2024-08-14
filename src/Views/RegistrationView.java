/**
 * @author: Dai/Vi Quach, Deekshitha Chavalla
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
 * Registration GUI
 */
public class RegistrationView extends Application {
    /**
     * Main Panel for Registration
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 420, 535));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
