package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ManagerMainPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("manager-mainpage.fxml"));
            Stage managerStage = new Stage();
            managerStage.initStyle(StageStyle.UNDECORATED);
            managerStage.setScene(new Scene(root, 394, 392));
            managerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
