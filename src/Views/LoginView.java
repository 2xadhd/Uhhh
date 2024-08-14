/**
 *  @author: Harrison Turner, Arpi Mangasaryan
 *  @version: 1.0
 *  date: 08/14/2024
 */
package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * LoginView class handles the main login interface for the application.
 * It extends the JavaFX Application class to create the main window and switch between different views.
 */
public class LoginView extends Application {

    private static Stage primaryStage;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned, and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 473);
        stage.setTitle("Welcome to our Airlines!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches the current scene to the flight search view.
     * This method loads the flight search FXML and sets it on the primary stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public static void switchToFlightSearch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("flight-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flight Search");
    }

    /**
     * The main method is the entry point of the application.
     * It calls the launch method, which in turn calls the start method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
