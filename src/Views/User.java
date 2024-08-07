package Views;

public class User {
    private String priceColumn;
    private String reservationColumn;
    private String whenToWhenColumn;
    private String whereToWhereColumn;

    public User(String priceColumn, String reservationColumn, String whenToWhenColumn, String whereToWhereColumn) {
        this.priceColumn = priceColumn;
        this.reservationColumn = reservationColumn;
        this.whenToWhenColumn = whenToWhenColumn;
        this.whereToWhereColumn = whereToWhereColumn;
    }

    public User(String customer, int i, String trip) {
    }

    public String getPriceColumn() {
        return priceColumn;
    }

    public String getReservationColumn() {return reservationColumn;}

    public String getWhenToWhen() {
        return whenToWhenColumn;
    }

    public String getWhereToWhere() {
        return whereToWhereColumn;
    }
}
