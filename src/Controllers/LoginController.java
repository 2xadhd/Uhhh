package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label welcomeText;
    private Object StateStyle;

    @FXML
    protected void onHelloButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (Controllers.Authenticator.authenticate(username, password)) {
            welcomeText.setText("Welcome, " + username + "!");
        } else {
            welcomeText.setText("Invalid credentials. Please try again.");
        }

            }
        public void onRegisterMouseClick(ActionEvent event) {
            try {
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/Views/register.fxml"));
                Stage registerStage = new Stage();
                registerStage.initStyle(StageStyle.UNDECORATED);
                registerStage.setTitle("Register");
                registerStage.setScene(new Scene(root, 520, 420));
                registerStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}

