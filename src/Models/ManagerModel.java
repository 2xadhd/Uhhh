package Models;

public class ManagerModel {


    String firstName;
    String lastName;
    String userID;
    String pin;
    String email;
    String phoneNumber;


    public ManagerModel(String firstName, String lastName, String userID, String pin, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.pin = pin;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}



