package Models;

class CustomerModel {
    String firstName;
    String lastName;
    String userID;
    String password;
    String email;
    String phoneNumber;
    String cardNumber;
    String expDate;


    public CustomerModel(String firstName, String lastName, String userID, String password, String email, String phoneNumber,
                         String cardNumber, String expDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cardNumber = cardNumber;
        this.expDate = expDate;

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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}



