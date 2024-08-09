/**
 * @author: Dai/Vi Quach
 * @version: 1.0
 * date 7/24/2024
 */
package Models;

/**
 * Entity class for Manager.
 * Includes constructor classes, getters, setters for all attributes.
 */
public class ManagerModel {


    String firstName;
    String lastName;
    String userID;
    String pin;
    String email;
    String phoneNumber;

    /**
     * Constructor with 6 arguments
     * @param firstName
     * @param lastName
     * @param userID
     * @param pin
     * @param email
     * @param phoneNumber
     */
    public ManagerModel(String firstName, String lastName, String userID, String pin, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.pin = pin;
        this.email = email;
        this.phoneNumber = phoneNumber;

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
     * @param firstName
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
     * @param lastName
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
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * PhoneNumber getter
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * PhoneNumber setter
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Email getter
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email setter
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Pin setter
     *
     * @param pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Pin getter
     * @return
     */
    public String getPin()
    {
        return this.pin;
    }
}



