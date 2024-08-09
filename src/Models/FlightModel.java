/**
 * @author: Dai/Vi Quach
 * @version 1.0
 * date 7/24/2024
 */
package Models;

/**
 * Entity Class for Flights.
 * Includes constructor classes, getters, setters for all attributes.
 */
public class FlightModel {

    String flightID;
    int totalSeat;
    String depart;
    String arrive;
    String departTime;
    String arriveTime;
    String terminal;
    double price;

    /**
     * Constructor with 8 arguments
     * @param flightID
     * @param totalSeat
     * @param depart
     * @param arrive
     * @param departTime
     * @param arriveTime
     * @param terminal
     * @param price
     */
    public FlightModel(String flightID,
                       int totalSeat,
                       String depart,
                       String arrive,
                       String departTime,
                       String arriveTime,
                       String terminal,
                       double price) {
        this.flightID = flightID;
        this.totalSeat = totalSeat;
        this.depart = depart;
        this.arrive = arrive;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.terminal = terminal;
        this.price = price;
    }

    /**
     * Constructor with no argument
     */
    public FlightModel() {
        this.flightID = null;
        this.totalSeat = 0;
        this.depart = null;
        this.arrive = null;
        this.departTime = null;
        this.arriveTime = null;
        this.terminal = null;
        this.price = 0;
    }

    /**
     * flightID getter
     * @return
     */
    public String getFlightID() {
        return flightID;
    }

    /**
     * flightID setter
     * @param flightID
     */
    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    /**
     * totalSeat getter
     * @return
     */
    public int getTotalSeat() {
        return totalSeat;
    }

    /**
     * totalSeat setter
     * @param totalSeat
     */
    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    /**
     * Depart getter
     * @return
     */
    public String getDepart() {
        return depart;
    }

    /**
     * Depart setter
     * @param depart
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }

    /**
     * Arrive getter
     * @return
     */
    public String getArrive() {
        return arrive;
    }

    /**
     * Arrive setter
     * @param arrive
     */
    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    /**
     * DepartTime getter
     * @return
     */
    public String getDepartTime() {
        return departTime;
    }

    /**
     * DepartTime setter
     * @param departTime
     */
    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    /**
     * ArriveTime getter
     * @return
     */
    public String getArriveTime() {
        return arriveTime;
    }

    /**
     * ArriveTime setter
     * @param arriveTime
     */
    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * Terminal getter
     * @return
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * Terminal setter
     * @param terminal
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal;
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
}

