/**
 *  @author: Dai/Vi Quach
 *  @version: 1.0
 *  date: 08/08/2024
 */
package Controllers;

import Models.CustomerModel;
import Views.LoginView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Controls the inner working of the Register GUI
 */
public class RegisterController {
    String input;
    ArrayList<CustomerModel> cusList = new ArrayList<CustomerModel>();
    @FXML
    private ImageView userImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField passwordField;
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
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phonenumberTextField;

    /**
     * automatically gets the list of all customers for the purpose of validation
     */
    public void initialize() {
        File userImageFile = new File("Resources/account_logo.png");
        Image userImage = new Image(userImageFile.toURI().toString());
        userImageView.setImage(userImage);
        String path = new File("src/Database/customer.txt").getAbsolutePath();
        File file = new File(path);
        Scanner sc;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                String[] names = input.split(",");
                if (names.length == 6) {
                    CustomerModel cus = new CustomerModel();
                    cus.setUserID(names[0].trim());
                    cus.setPassword(names[1].trim());
                    cus.setFirstName(names[2].trim());
                    cus.setLastName(names[3].trim());
                    cus.setPhoneNumber(names[4].trim());
                    cus.setEmail(names[5].trim());
                    cusList.add(cus);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs when user clicks on the register button.
     * Mostly does validation.
     * @param event
     * @throws IOException
     */
    @FXML
    public void registrationButtonOnAction(ActionEvent event) throws IOException {
        boolean flag = true;
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String phone = phonenumberTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        /**
         * validation goes here
         */
        while (flag) {
            /**
             * check if username is taken
             */
            for (CustomerModel customerModel : cusList)
                if (Objects.equals(username, customerModel.getUserID())) {
                    registrationMessageLabel.setText("Username has been taken!");
                    flag = false;
                    break;
                }
            /**
             * check if password matches confirmPassword field
             */
            if (!password.equals(confirmPassword)) {
                registrationMessageLabel.setText("Password does not match");
                flag = false;
                break;
            }

            /**
             * if nothing is out of place, add Customer to our database
             */
            if (flag) {
                CustomerModel cus = new CustomerModel(firstname, lastname, username, password, email, phone);
                addCustomer(cus);
                registrationMessageLabel.setText("Registration was a success!");
                break;
            }
        }
    }

    /**
     * Runs when the customer click on the close button
     * will close the register panel and reopen login panel
     * @param event
     */
    public void closeButtonOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage loginStage = new Stage();
            LoginView login = new LoginView();
            login.start(loginStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * writes the newly added customer into our database
     * @param cus
     * @throws IOException
     */
    public void addCustomer(CustomerModel cus) throws IOException {
        String path = new File("src/Database/customer.txt").getAbsolutePath();
        File file = new File(path);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        System.out.println(cus.getUserID() + "," + cus.getPassword() + "," + cus.getFirstName() + "," + cus.getLastName() + "," + cus.getPhoneNumber() + "," + cus.getEmail());
        br.newLine();
        br.write(cus.getUserID() + "," + cus.getPassword() + "," + cus.getFirstName() + "," + cus.getLastName() + "," + cus.getPhoneNumber() + "," + cus.getEmail());
        br.close();
        fr.close();
    }


}