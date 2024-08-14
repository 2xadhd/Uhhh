/**
 *  @author: Harrison Turner
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
 * The ManagerMainPage class represents the main page for managers in the application.
 * This class extends the JavaFX Application class and handles the initialization and display of the manager main page.
 */
public class ManagerMainPage extends Application {
    private static Stage primaryStage;

    /**
     * The main entry point for all JavaFX applications.
     * This method is called after the init method has returned, and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(ManagerMainPage.class.getResource("manager-mainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 394, 392);
        stage.setScene(scene);
        stage.show();
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