import java.util.HashMap;

public class ActivityBookingsPortal<T extends BookingDetails> { // Use T as a generic type that extends BookingDetails
    private HashMap<Integer, T> bookingsMap;
    private ManageMemberSchedule memberScheduleManager;

    public ActivityBookingsPortal(ManageMemberSchedule memberScheduleManager) {
        bookingsMap = new HashMap<>();
        this.memberScheduleManager = memberScheduleManager;
    }

    public void makeBooking(int activityId, T bookingDetails) {
        bookingsMap.put(activityId, bookingDetails);
        // Correct method calls using the interface methods
        memberScheduleManager.addMemberToSchedule(bookingDetails.getActivityDate(), bookingDetails.getParticipantName(), activityId);
        System.out.println("Booking made for activity ID: " + activityId);
    }

    public void cancelBooking(int activityId) {
        if (bookingsMap.containsKey(activityId)) {
            bookingsMap.remove(activityId);
            T booking = bookingsMap.get(activityId);
            if (booking != null) {
                memberScheduleManager.removeMemberFromSchedule(booking.getActivityDate(), booking.getParticipantName(), activityId);
            }
            System.out.println("Booking canceled for activity ID: " + activityId);
        } else {
            System.out.println("No booking found for activity ID: " + activityId);
        }
    }

    public T getBookingDetails(int activityId) {
        return bookingsMap.get(activityId);
    }

    // Getters and setters
    public HashMap<Integer, T> getBookingsMap() {
        return bookingsMap;
    }

    public void setBookingsMap(HashMap<Integer, T> bookingsMap) {
        this.bookingsMap = bookingsMap;
    }

    public ManageMemberSchedule getMemberScheduleManager() {
        return memberScheduleManager;
    }

    public void setMemberScheduleManager(ManageMemberSchedule memberScheduleManager) {
        this.memberScheduleManager = memberScheduleManager;
    }
}
