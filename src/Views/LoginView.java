package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 473);
        stage.setTitle("Welcome to our Airlines!");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchToFlightSearch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("flight-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flight Search");
    }

    public static void main(String[] args) {
        launch();
    }
}
