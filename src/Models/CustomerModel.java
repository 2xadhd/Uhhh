/**
 * @author: Dai/Vi Quach
 * @version 1.0
 * date: 7/24/2024
 */
package Models;

/**
 * Entity class for Customer.
 * Includes constructor classes, getters, setters for all attributes.
 */
public class CustomerModel {
    String firstName;
    String lastName;
    String userID;
    String password;
    String email;
    String phoneNumber;

    /**
     * Contructor with 6 arguments
     * @param firstName
     * @param lastName
     * @param userID
     * @param password
     * @param email
     * @param phoneNumber
     */
    public CustomerModel(String firstName, String lastName, String userID, String password, String email, String phoneNumber
                         ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;


    }

    /**
     * Constructor with no argument
     */
    public CustomerModel() {
        this.firstName = null;
        this.lastName = null;
        this.userID = null;
        this.password = null;
        this.email = null;
        this.phoneNumber = null;
    }

    /**
     * FirstName getter
     * @return
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * FirstName setter
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * LastName getter
     * @return
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * LastName setter
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * userID getter
     * @return
     */
    public String getUserID() {
        return userID;
    }
    /**
     * userID setter
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    /**
     * phonenumber getter
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * phonenumber setter
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * email getter
     * @return
     */
    public String getEmail() {
        return email;
    }
    /**
     * email setter
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * password getter
     * @return
     */


    public String getPassword()
    {
        return password;
    }
    /**
     * password setter
     */
    public void setPassword(String password) {
        this.password = password;
    }

}



