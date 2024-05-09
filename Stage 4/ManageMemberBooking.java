import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ManageMemberBooking implements Serializable {
    private List<BookingEntry> bookingEntries = new ArrayList<>(); // Use ArrayList instead of array
    private final MemberService memberService;
    private final String FILE_NAME = "booking_entries.ser";

    public ManageMemberBooking(MemberService memberService) {
        this.memberService = memberService;
        loadBookingEntriesFromFile();
    }

    public void addBooking(String date, String hotelName, int memberId) {
        bookingEntries.add(new BookingEntry(date, hotelName, memberId)); // Add to the ArrayList
        saveBookingEntriesToFile();
        System.out.println("Booking added for Member ID: " + memberId + " at " + hotelName + " on " + date);
    }

    public void removeBooking(String date, String hotelName, int memberId) {
        BookingEntry entryToRemove = null;
        for (BookingEntry entry : bookingEntries) {
            if (entry != null && entry.getDate().equals(date) && entry.getHotelName().equals(hotelName) && entry.getMemberId() == memberId) {
                entryToRemove = entry;
                break;
            }
        }
        if (entryToRemove != null) {
            bookingEntries.remove(entryToRemove); // Remove from the ArrayList
            saveBookingEntriesToFile();
            System.out.println("Removed booking for Member ID: " + memberId + " at " + hotelName + " on " + date);
        } else {
            System.out.println("No matching booking found for removal.");
        }
    }

    public List<BookingEntry> getFormattedBookings() {
        return new ArrayList<>(bookingEntries); // Return a copy of the ArrayList
    }
    public void viewBookings() {
        if (bookingEntries.isEmpty()) {
            System.out.println("No bookings.");
            return;
        }
        System.out.println("All Bookings:");
        for (BookingEntry entry : bookingEntries) {
            if (entry != null) {
                Member member = memberService.getMemberById(entry.getMemberId());
                if (member != null) {
                    System.out.println(entry + ", Member Details: " + member);
                } else {
                    System.out.println(entry + ", Member Details: Member ID not found.");
                }
            }
        }
    }
    private void saveBookingEntriesToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            outputStream.writeObject(bookingEntries.toArray(new BookingEntry[0])); // Convert ArrayList to array
        } catch (IOException e) {
            System.err.println("Error saving booking entries to file: " + e.getMessage());
        }
    }

    private void loadBookingEntriesFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            BookingEntry[] entries = (BookingEntry[]) inputStream.readObject();
            bookingEntries = new ArrayList<>(Arrays.asList(entries)); // Convert array to ArrayList
        } catch (FileNotFoundException e) {
            System.err.println("No previous booking entries found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading booking entries from file: " + e.getMessage());
        }
    }
}
