package Models;

public class FlightModel {

    String flightID;
    int totalSeat;
    String depart;
    String arrive;
    String departTime;
    String arriveTime;
    String terminal;
    double price;

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

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

