public class ManageMemberBooking {
    private final BookingEntry[] bookingEntries = new BookingEntry[10];
    private int entryCount = 0;
    private final MemberService memberService;

    public ManageMemberBooking(MemberService memberService) {
        this.memberService = memberService;
    }

    public void addBooking(String date, String hotelName, int memberId) {
        if (entryCount >= bookingEntries.length) {
            System.out.println("Booking list is full.");
            return;
        }
        bookingEntries[entryCount++] = new BookingEntry(date, hotelName, memberId);
        System.out.println("Booking added for Member ID: " + memberId + " at " + hotelName + " on " + date);
    }

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
