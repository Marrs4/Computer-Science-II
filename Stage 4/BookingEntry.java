import java.io.Serializable;

public class BookingEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    private String date; // Date of the booking
    private String hotelName; // Name of the hotel where the booking is made
    private int memberId; // ID of the member who made the booking

    /**
     * Constructs a new BookingEntry with the specified details.
     *
     * @param date      The date of the booking.
     * @param hotelName The name of the hotel for the booking.
     * @param memberId  The ID of the member who made the booking.
     */
    public BookingEntry(String date, String hotelName, int memberId) {
        this.date = date;
        this.hotelName = hotelName;
        this.memberId = memberId;
    }

    /**
     * Returns the date of the booking.
     *
     * @return The date of the booking.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the name of the hotel where the booking is made.
     *
     * @return The name of the hotel.
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Returns the ID of the member who made the booking.
     *
     * @return The member ID.
     */
    public int getMemberId() {
        return memberId;
    }

    /**
     * Returns a string representation of the booking entry, containing
     * all its details in a readable format. This can be particularly
     * useful for logging or displaying the booking information to the user.
     *
     * @return A string representation of the BookingEntry.
     */
    @Override
    public String toString() {
        return "BookingEntry{" +
                "date='" + date + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}
