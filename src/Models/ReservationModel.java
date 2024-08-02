package Models;

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

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatNum2() {
        return seatNum2;
    }

    public void setSeatNum2(int seatNum2) {
        this.seatNum2 = seatNum2;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getFlightID1() {
        return flightID1;
    }

    public void setFlightID1(String flightID1) {
        this.flightID1 = flightID1;
    }

    public String getFlightID2() {
        return flightID2;
    }

    public void setFlightID2(String flightID2) {
        this.flightID2 = flightID2;
    }

    public boolean isRound() {
        return round;
    }

    public void setRound(boolean round) {
        this.round = round;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
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
}
