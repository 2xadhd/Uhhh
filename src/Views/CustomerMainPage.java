/**
 *  @author: Harrison Turner
 *  @version: 1.0
 *  date: 08/14/2024
 */
package Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The CustomerMainPage class represents the main page for customers in the application.
 * This class extends the JavaFX Application class and handles the initialization and display of the customer main page.
 */
public class CustomerMainPage extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * This method is called after the init method has returned, and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set
     */
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

    /**
     * The main method is the entry point of the application.
     * It calls the launch method, which in turn calls the start method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}