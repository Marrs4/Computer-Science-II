/**
 * Manages booking entries for members within the club. This class
 * allows for the addition, removal, and viewing of member bookings, such as hotel reservations.
 */
public class ManageMemberBooking {
    // Array to store booking entries
    private final BookingEntry[] bookingEntries = new BookingEntry[100];
    // Counter to track the current number of bookings
    private int entryCount = 0;
    // Reference to the MemberService for accessing member information
    private final MemberService memberService;

    /**
     * Constructs a ManageMemberBooking service with a reference to the MemberService.
     *
     * @param memberService The MemberService to use for accessing member details.
     */
    public ManageMemberBooking(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Adds a new booking entry for a member.
     *
     * @param date The date of the booking.
     * @param hotelName The name of the hotel where the booking is made.
     * @param memberId The ID of the member making the booking.
     */
    public void addBooking(String date, String hotelName, int memberId) {
        if (entryCount >= bookingEntries.length) {
            System.out.println("Booking list is full.");
            return;
        }
        bookingEntries[entryCount++] = new BookingEntry(date, hotelName, memberId);
        System.out.println("Booking added for Member ID: " + memberId + " at " + hotelName + " on " + date);
    }

    /**
     * Removes a booking entry based on the specified date, hotel name, and member ID.
     *
     * @param date The date of the booking to remove.
     * @param hotelName The name of the hotel of the booking to remove.
     * @param memberId The ID of the member whose booking is to be removed.
     */
    public void removeBooking(String date, String hotelName, int memberId) {
        for (int i = 0; i < entryCount; i++) {
            BookingEntry entry = bookingEntries[i];
            if (entry.date.equals(date) && entry.hotelName.equals(hotelName) && entry.memberId == memberId) {
                System.arraycopy(bookingEntries, i + 1, bookingEntries, i, entryCount - i - 1);
                bookingEntries[--entryCount] = null;
                System.out.println("Removed booking for Member ID: " + memberId + " at " + hotelName + " on " + date);
                return;
            }
        }
        System.out.println("No matching booking found for removal.");
    }

    /**
     * Displays all current bookings, along with member details for each booking.
     */
    public void viewBookings() {
        if (entryCount == 0) {
            System.out.println("No bookings.");
            return;
        }
        System.out.println("All Bookings:");
        for (int i = 0; i < entryCount; i++) {
            BookingEntry entry = bookingEntries[i];
            Member member = memberService.getMemberById(entry.memberId);
            if (member != null) {
                System.out.println(entry + ", Member Details: " + member);
            } else {
                System.out.println(entry + ", Member Details: Member ID not found.");
            }
        }
    }
}
