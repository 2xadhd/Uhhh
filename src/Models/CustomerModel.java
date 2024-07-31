package Models;

public class CustomerModel {
    String firstName;
    String lastName;
    String userID;
    String password;
    String email;
    String phoneNumber;


    public CustomerModel(String firstName, String lastName, String userID, String password, String email, String phoneNumber
                         ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;


    }

    public CustomerModel() {
        this.firstName = null;
        this.lastName = null;
        this.userID = null;
        this.password = null;
        this.email = null;
        this.phoneNumber = null;
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



    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}



