import java.util.HashMap;

public class ActivityBookingsPortal<BookingDetails> {
    private HashMap<Integer, BookingDetails> bookingsMap;
    private ManageMemberSchedule memberScheduleManager;

    public ActivityBookingsPortal(ManageMemberSchedule memberScheduleManager) {
        bookingsMap = new HashMap<>();
        this.memberScheduleManager = memberScheduleManager;
    }

    public void makeBooking(int activityId, BookingDetails bookingDetails) {
        bookingsMap.put(activityId, bookingDetails);
        memberScheduleManager.addMemberToSchedule(((Object) bookingDetails).getActivityDate(), bookingDetails.getParticipantName(), activityId);
        System.out.println("Booking made for activity ID: " + activityId);
    }

    public void cancelBooking(int activityId) {
        if (bookingsMap.containsKey(activityId)) {
            bookingsMap.remove(activityId);
            memberScheduleManager.removeMemberFromSchedule(activityId);
            System.out.println("Booking canceled for activity ID: " + activityId);
        } else {
            System.out.println("No booking found for activity ID: " + activityId);
        }
    }

    public BookingDetails getBookingDetails(int activityId) {
        return bookingsMap.get(activityId);
    }

    // Getters and setters
    public HashMap<Integer, BookingDetails> getBookingsMap() {
        return bookingsMap;
    }

    public void setBookingsMap(HashMap<Integer, BookingDetails> bookingsMap) {
        this.bookingsMap = bookingsMap;
    }

    public ManageMemberSchedule getMemberScheduleManager() {
        return memberScheduleManager;
    }

    public void setMemberScheduleManager(ManageMemberSchedule memberScheduleManager) {
        this.memberScheduleManager = memberScheduleManager;
    }
}