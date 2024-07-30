package Controllers;

import Views.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private ImageView userImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File userImageFile = new File("Images/account_logo.png");
        Image userImage = new Image(userImageFile.toURI().toString());
        userImageView.setImage(userImage);
    }

    @FXML
    public void registrationButtonOnAction(ActionEvent event) {
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.equals(confirmPassword)) {
            confirmPasswordLabel.setText("");
            registrationMessageLabel.setText("User has been registered successfully!");
            // Add logic to actually register the user here
        } else {
            confirmPasswordLabel.setText("Password does not match");
        }
    }

    public void closeButtonOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 473);
            stage.setTitle("Welcome to our Airlines!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
