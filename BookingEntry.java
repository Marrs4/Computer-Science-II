/**
 * Represents a single booking entry for a member. This class captures all
 * relevant information for a booking, including the date of the booking,
 * the name of the hotel, and the ID of the member who made the booking.
 */
public class BookingEntry {
    // Date of the booking
    String date;
    // Name of the hotel where the booking is made
    String hotelName;
    // ID of the member who made the booking
    int memberId;

    /**
     * Constructs a new BookingEntry with the specified details.
     *
     * @param date The date of the booking.
     * @param hotelName The name of the hotel for the booking.
     * @param memberId The ID of the member who made the booking.
     */
    public BookingEntry(String date, String hotelName, int memberId) {
        this.date = date;
        this.hotelName = hotelName;
        this.memberId = memberId;
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
        return "BookingEntry" +
                "date='" + date + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", memberId=" + memberId +
                ' ';
    }
}
