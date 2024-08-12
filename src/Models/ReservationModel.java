/**
 * @author: Dai/Vi Quach
 * @version: 1.0
 * date 7/24/2024
 */
package Models;

/**
 * Entity class for Reservation.
 * Includes constructor classes, getters, setters for all attributes.
 */
public class ReservationModel {
    String reservationID;
    String username;
    int seatNum;
    int seatNum2;
    double price;
    String departTime;
    String returnTime;
    String flightID1;
    String flightID2;
    boolean round;
    String cardNumber;
    String expDate;

    /**
     * Constructor with 12 arguments
     * @param reservationID
     * @param username
     * @param seatNum
     * @param seatNum2
     * @param price
     * @param departTime
     * @param returnTime
     * @param flightID1
     * @param flightID2
     * @param round
     * @param cardNumber
     * @param expDate
     */
    public ReservationModel(String reservationID,
                            String username,
                            int seatNum,
                            int seatNum2,
                            double price,
                            String departTime,
                            String returnTime,
                            String flightID1,
                            String flightID2,
                            boolean round,
                            String cardNumber,
    String expDate) {
        this.reservationID = reservationID;
        this.username = username;
        this.seatNum = seatNum;
        this.seatNum2 = seatNum2;
        this.price = price;
        this.departTime = departTime;
        this.returnTime = returnTime;
        this.flightID1 = flightID1;
        this.flightID2 = flightID2;
        this.round = round;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
    }

    public ReservationModel() {
        this.reservationID = "";
        this.username = "";
        this.seatNum = 0;
        this.seatNum2 = 0;
        this.price = 0;
        this.departTime = "";
        this.returnTime = "";
        this.flightID1 = "";
        this.flightID2 = "";
        this.round = false;
        this.cardNumber = "";
        this.expDate = "";
    }

    /**
     * ID getter
     * @return
     */
    public String getReservationID() {
        return reservationID;
    }

    /**
     * ID setter
     * @param reservationID
     */
    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    /**
     * Price getter
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * SeatNum getter
     * @return
     */
    public int getSeatNum() {
        return seatNum;
    }

    /**
     * SeatNum setter
     * @param seatNum
     */
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    /**
     * SeatNum2 getter
     * @return
     */
    public int getSeatNum2() {
        return seatNum2;
    }

    /**
     * SeatNum2 setter
     * @param seatNum2
     */
    public void setSeatNum2(int seatNum2) {
        this.seatNum2 = seatNum2;
    }

    /** DepartTime getter
     *
     * @return
     */
    public String getDepartTime() {
        return departTime;
    }

    /** DepartTime setter
     *
     * @param departTime
     */
    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    /**
     * FlightID1 getter
     * @return
     */
    public String getFlightID1() {
        return flightID1;
    }

    /** FlightID1 setter
     *
     * @param flightID1
     */
    public void setFlightID1(String flightID1) {
        this.flightID1 = flightID1;
    }

    /**
     * FlightID2 getter
     * @return
     */
    public String getFlightID2() {
        return flightID2;
    }

    /**
     * FlightID2 setter
     * @param flightID2
     */
    public void setFlightID2(String flightID2) {
        this.flightID2 = flightID2;
    }

    /**
     * Round getter
     * @return
     */
    public boolean isRound() {
        return round;
    }

    /**
     * Round setter
     * @param round
     */
    public void setRound(boolean round) {
        this.round = round;
    }

    /**
     * UserName getter
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username setter
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * ReturnTime getter
     * @return
     */
    public String getReturnTime() {
        return returnTime;
    }

    /** ReturnTime setter
     *
     * @param returnTime
     */
    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * CardNumber getter
     * @return
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * CardNumber setter
     * @param cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * ExpDate getter
     * @return
     */
    public String getExpDate() {
        return expDate;
    }

    /**
     * ExpDate setter
     * @param expDate
     */
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
