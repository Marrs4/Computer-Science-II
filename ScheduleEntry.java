/**
 * Represents an entry in the schedule of the club's members.
 * This class captures the date and activity for a scheduled event,
 * along with the ID of the member involved. It is used to manage and
 * track individual member schedules within the organization.
 */
public class ScheduleEntry {
    // The date of the scheduled activity.
    String date;
    // A description or name of the scheduled activity.
    String activity;
    // The ID of the member associated with this schedule entry.
    int memberId;

    /**
     * Constructs a new ScheduleEntry with the specified details.
     *
     * @param date The date of the scheduled activity.
     * @param activity The description or name of the activity.
     * @param memberId The ID of the member this entry is for.
     */
    public ScheduleEntry(String date, String activity, int memberId) {
        this.date = date;
        this.activity = activity;
        this.memberId = memberId;
    }

    /**
     * Provides a string representation of the schedule entry,
     * including the date, activity, and member ID. This is helpful
     * for logging, debugging, or displaying the entry information.
     *
     * @return A string representation of the ScheduleEntry.
     */
    @Override
    public String toString() {
        return "ScheduleEntry{" +
                "date='" + date + '\'' +
                ", activity='" + activity + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}