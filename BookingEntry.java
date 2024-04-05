public class BookingEntry {
    String date;
    String hotelName;
    int memberId;

    public BookingEntry(String date, String hotelName, int memberId) {
        this.date = date;
        this.hotelName = hotelName;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "BookingEntry{" +
                "date='" + date + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}